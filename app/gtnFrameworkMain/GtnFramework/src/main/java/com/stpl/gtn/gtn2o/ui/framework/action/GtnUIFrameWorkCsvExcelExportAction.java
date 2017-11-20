package com.stpl.gtn.gtn2o.ui.framework.action;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.asi.ui.extfilteringtable.ExtFilterTable;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnFileNameUtils;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsGeneralResponse;
import com.vaadin.data.Container;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.UI;

public class GtnUIFrameWorkCsvExcelExportAction implements GtnUIFrameWorkAction {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkExcelButtonComponent.class);
	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		int count = 0;
		GtnUIFrameworkComponentData customData = (GtnUIFrameworkComponentData) (GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(inputBean.getExportTableId())).getComponent().getData();
		GtnUIFrameworkPagedTableLogic tableLogic = customData.getCurrentPageTableLogic();
		ExtCustomTable resultTable = (ExtCustomTable) customData.getCustomData();
		boolean isDatatable = customData.getCustomData() instanceof ExtFilterTable;

		List<Object> propertyIds = new LinkedList<>(Arrays.asList(resultTable.getVisibleColumns()));
		List<String> headers = new LinkedList<>(Arrays.asList(resultTable.getColumnHeaders()));
		if (tableLogic != null) {
			Set<Container.Filter> filters = tableLogic.getFilters();
			tableLogic.clearFilters();
			count = tableLogic.getCount();
			tableLogic.setFilters(filters);
		} else if (isDatatable) {
			ExtFilterTable table = (ExtFilterTable) customData.getCustomData();
			@SuppressWarnings("unchecked")
			Collection<GtnWsRecordBean> container = (Collection<GtnWsRecordBean>) table.getContainerDataSource()
					.getItemIds();
			List<GtnWsRecordBean> resultList = new ArrayList<>();
			resultList.addAll(container);
			String filePath = writeFile(resultList, headers, propertyIds, inputBean);
			sendTheExcelToUser(inputBean.getExportFileName(), filePath, inputBean.isWriteFileInWebService());
			return;
		}

		List<String> excludeColumnsList = inputBean.getExcludeColumnsList();
		if (excludeColumnsList != null && !excludeColumnsList.isEmpty()) {
			for (int i = 0; i < excludeColumnsList.size(); i++) {
				int index = propertyIds.indexOf(excludeColumnsList.get(i));
				if (index >= 0) {
					propertyIds.remove(index);
					headers.remove(index);

				}
			}
		}
		if (count > 0 && tableLogic != null) {
			String filePath = inputBean.isWriteFileInWebService() ? tableLogic.loadDataForExcel(0, count, headers)
					: writeFile(tableLogic.loadData(0, count), headers, propertyIds, inputBean);
			sendTheExcelToUser(inputBean.getExportFileName(), filePath, inputBean.isWriteFileInWebService());
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@SuppressWarnings("deprecation")
	public void sendTheExcelToUser(String exportFileName, String filePath, boolean isWriteFileInWebService) {
		String exportFile = exportFileName;
		TemporaryFileDownloadResource resource;
		File tempFile = GtnFileNameUtils.getFile(filePath);
		try {
			if (Page.getCurrent().getWebBrowser().isFirefox()) {
				exportFile = exportFile.replace(GtnFrameworkCommonStringConstants.SPACE,
						GtnFrameworkCommonStringConstants.UNDERSCORE);
			}

			resource = new TemporaryFileDownloadResource(null,
					exportFile + GtnFrameworkCommonStringConstants.CSV_EXTENSION, EXCEL_MIME_TYPE, tempFile);
			UI.getCurrent().getPage().open(resource, GtnFrameworkCommonStringConstants.UNDERSCORE_BLANK, false);
		} catch (final IOException e) {
			gtnLogger.error(exportFile, e);
		} finally {
			deleteTempFile(tempFile.getAbsolutePath(), isWriteFileInWebService);
		}
	}

	private void deleteTempFile(String filepath, boolean isWriteFileInWebService) {
		if (isWriteFileInWebService) {
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsGeneralRequest request = new GtnWsGeneralRequest();
			request.setExtraParameter(filepath);
			serviceRequest.setGtnWsGeneralRequest(request);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_TEMP_EXCEL_FILE_DELETE,
					serviceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsGeneralResponse generalResponse = response.getGtnWsGeneralResponse();
			if (!generalResponse.isSucess()) {
				gtnLogger.error("Error while deleting Temp file---", generalResponse.getGtnGeneralException());
			}
		}

	}

	public String writeFile(List<GtnWsRecordBean> resultList, List<String> headers, List<Object> visibleColumns,
			GtnUIFrameworkExcelButtonConfig inputBean) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile(GtnFrameworkCommonStringConstants.TMP,
					GtnFrameworkCommonStringConstants.DOT_XLS);
			writeFile(resultList, headers, visibleColumns, inputBean, tempFile);
			return tempFile.getAbsolutePath();
		} catch (IOException e) {
			gtnLogger.error("Error in File Handling IO Exception- ", e);
			return GtnFrameworkCommonStringConstants.STRING_EMPTY;
		}
	}

	private void writeFile(List<GtnWsRecordBean> resultList, List<String> headers, List<Object> visibleColumns,
			GtnUIFrameworkExcelButtonConfig inputBean, File tempFile) {
		try (FileWriter writer = new FileWriter(tempFile, true); PrintWriter printWriter = new PrintWriter(writer)) {
			createHeaderRow(printWriter, headers);
			createDataRows(printWriter, resultList, visibleColumns, inputBean);
			printWriter.flush();
		} catch (Exception e) {
			gtnLogger.error("Error in File writing ", e);
		}
	}

	/**
	 * Used to create header in excel sheet
	 *
	 * @param pw
	 * @param header
	 */
	private static void createHeaderRow(PrintWriter pw, List<String> headers) {

		int headerListSize = headers.size() - 1;
		for (int headerCount = 0; headerCount < headerListSize; headerCount++) {

			pw.print(headers.get(headerCount) + GtnFrameworkCommonStringConstants.STRING_COMMA);

		}
		pw.println(headers.get(headerListSize));
	}

	private void createDataRows(PrintWriter printWriter, List<GtnWsRecordBean> resultList, List<Object> propertyIds,
			GtnUIFrameworkExcelButtonConfig inputBean) {

		for (int rowCount = 0; rowCount < resultList.size(); rowCount++) {

			GtnWsRecordBean records = resultList.get(rowCount);
			int lastItem = propertyIds.size() - 1;
			for (int i = 0; i < propertyIds.size(); i++) {
				String propertyId = String.valueOf(propertyIds.get(i));

				Object value = records.getPropertyValue(propertyId) == null
						? GtnFrameworkCommonStringConstants.STRING_EMPTY
						: records.getPropertyValue(propertyId);
				value = findHelperMappedValue(value, inputBean, propertyId);
				if (i == lastItem) {
					addLastItem(value, printWriter);
				} else if (value instanceof Date) {

					printWriter
							.print((getFormattedDate(value) + GtnFrameworkCommonStringConstants.STRING_COMMA).trim());

				} else {

					printWriter.print((GtnFrameworkCommonStringConstants.QUOTE + replaceDoubleQuotes(value)
							+ GtnFrameworkCommonStringConstants.QUOTE + GtnFrameworkCommonStringConstants.STRING_COMMA)
									.trim());

				}
			}
		}
		printWriter.close();

	}

	private Object findHelperMappedValue(Object value, GtnUIFrameworkExcelButtonConfig inputBean, String propertyId) {
		Object mappedValue = value;
		List<String> componentMappedPropertyIdList = inputBean.getHelperTableMapedPropertyIdList();
		if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))) {
			String componentId = inputBean.getExportTableId() + 0 + propertyId;
			ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponent();
			String componenetIdCaption = component.getItemCaption(value);
			mappedValue = componenetIdCaption.equalsIgnoreCase(GtnFrameworkCommonStringConstants.SELECT_ONE)
					? GtnFrameworkCommonStringConstants.STRING_EMPTY
					: componenetIdCaption;
		}
		return mappedValue;
	}

	public static String replaceDoubleQuotes(Object rawObj) {
		String excelString = String.valueOf(rawObj);
		if (GtnFrameworkCommonStringConstants.STRING_EMPTY.equals(excelString)) {
			excelString = excelString.replace(GtnFrameworkCommonStringConstants.QUOTE,
					GtnFrameworkCommonStringConstants.DOUBLE_QUOTE);
		}
		return excelString;

	}

	private String getFormattedDate(Object ob) {
		return dateFormat.format((Date) ob);
	}

	private void addLastItem(Object value, PrintWriter printWriter) {
		if (value instanceof Date) {
			printWriter.println(getFormattedDate(value).trim());
		} else {
			printWriter.println(replaceDoubleQuotes(value).trim());
		}
	}

}