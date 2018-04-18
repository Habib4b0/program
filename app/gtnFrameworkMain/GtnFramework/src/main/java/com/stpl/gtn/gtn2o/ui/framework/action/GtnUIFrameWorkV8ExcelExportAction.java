/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.framework.action;


import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsExcelHeaderBean;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnwsExcelRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnWsExcelResponse;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameWorkV8ExcelExportAction implements GtnUIFrameWorkAction {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkExcelExportAction.class);
	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		boolean isExportFromTable = inputBean.isExportFromTable();
		List<Object> propertyIds = new ArrayList<>();
		List<String> headers = new ArrayList<>();
		List<GtnWsRecordBean> exportList = new ArrayList<>();
		PagedGrid resultTable = null;
		GtnWsExcelHeaderBean headerBean = null;
		if (isExportFromTable) {
			GtnUIFrameworkComponentData customData = (GtnUIFrameworkComponentData) (GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(inputBean.getExportTableId())).getComponent().getData();
			resultTable =  customData.getPagedGrid();
			propertyIds = new LinkedList<>(Arrays.asList(resultTable.getTableConfig().getTableColumnMappingId()));
			headers = new LinkedList<>(resultTable.getTableConfig().getColumnHeaders());
			if (inputBean.isIsNewTreeTable()) {
				exportList = inputBean.getExportList();
			} else {
				exportList = setFilters(exportList, customData);
			}

		} else {
			if (inputBean.getExportTableId() != null && !inputBean.getExportTableId().isEmpty()) {
				GtnUIFrameworkComponentData customData = (GtnUIFrameworkComponentData) (GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(inputBean.getExportTableId())).getComponent().getData();
			resultTable =  customData.getPagedGrid();
			propertyIds = new LinkedList<>(Arrays.asList(resultTable.getTableConfig().getTableColumnMappingId()));
			headers = new LinkedList<>(resultTable.getTableConfig().getColumnHeaders());
				resultTable = null;
			} else {
				headerBean = getExcelHeaders(componentId, gtnUIFrameWorkActionConfig);
				// Call webService for table columns and Headers
				propertyIds.addAll(headerBean.getSingleColumns());
				headers.addAll(headerBean.getSingleHeaders());

			}
			exportList = getExcelDataList(componentId, gtnUIFrameWorkActionConfig);
		}
		excludeColumnList(inputBean, propertyIds, headers);

		if (propertyIds.size() < 255) {
			// Write Result List in Excel
			HSSFWorkbook workBook = writeInExcel(inputBean, exportList, propertyIds, headers, resultTable);
			sendTheExcelToUser(inputBean.getExportFileName(), workBook);
		} else {
			// Write Result List in Excel
			HSSFWorkbook workBook = writeSplitWorksheetExcel(inputBean, exportList, propertyIds, headers, resultTable,
					headerBean);
			sendTheExcelToUser(inputBean.getExportFileName(), workBook);
		}

	}

	public GtnWsExcelHeaderBean getExcelHeaders(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		// create Web Service Request
		GtnUIFrameworkWebserviceRequest serviceRequest = createExcelServiceRequest(inputBean, componentId);
		// Call webService for Load Data
		GtnWsExcelResponse response = callWebService(inputBean.getHeaderServiceUrl(), inputBean.getServiceType(),
				serviceRequest);
		return response.getExcelHeaderBean();
	}

	public List<GtnWsRecordBean> getExcelDataList(String componentId,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {

		GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		// create Web Service Request
		GtnUIFrameworkWebserviceRequest serviceRequest = createExcelServiceRequest(inputBean, componentId);
		// Call webService for Load Data
		GtnWsExcelResponse response = callWebService(inputBean.getLoadDataServiceUrl(), inputBean.getServiceType(),
				serviceRequest);

		return response.getResultBeanList();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private HSSFWorkbook writeInExcel(GtnUIFrameworkExcelButtonConfig inputBean, List<GtnWsRecordBean> resultList,
			List<Object> visibleColumns, List<String> headers, PagedGrid resultTable) {
		CellStyle defaultHeadersCellStyle = null;
		CellStyle defaultTitleCellStyle = null;

		String exportFileName = inputBean.getExportFileName();
		// Create Work Book
		HSSFWorkbook workBook = new HSSFWorkbook();
		// Create Sheet
		HSSFSheet sheet = createsheet(workBook, exportFileName);
		int rowCount = 0;
		// Create Title Row Only For Tree Table
		if (inputBean.isIsTreeTable() || inputBean.isTitleNeeded()) {
			rowCount = createTitleRow(sheet, visibleColumns.size(), exportFileName, rowCount, defaultTitleCellStyle,
					workBook);
		}
		rowCount = createHeaderRow(sheet, headers, rowCount, defaultHeadersCellStyle, workBook);
		createDataRows(sheet, resultList, visibleColumns, rowCount, workBook, resultTable, inputBean);
		handleAutoWidth(sheet, headers, true);
		// Grouping Rows needed for Tree Table
		if (inputBean.isIsTreeTable()) {
			groupRowsForTreeTable(sheet, resultList, inputBean.isNeedTobeCollapsed());
			sheet.setRowSumsBelow(false);
		}
		return workBook;
	}

	/**
	 * If Your Sheet is to large, disable auto width on sheets
	 * 
	 * @param sheet
	 * @param headers
	 * @param isNeedToAutoWidth
	 */
	private void handleAutoWidth(HSSFSheet sheet, List<String> headers, boolean isNeedToAutoWidth) {
		for (int i = 0; i < headers.size() && isNeedToAutoWidth; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	private HSSFWorkbook writeSplitWorksheetExcel(GtnUIFrameworkExcelButtonConfig inputBean,
			List<GtnWsRecordBean> resultList, List<Object> visibleColumns, List<String> headers,
			PagedGrid resultTable, GtnWsExcelHeaderBean headerBean) {
		CellStyle defaultHeadersCellStyle = null;
		CellStyle defaultTitleCellStyle = null;

		// Create Work Book
		HSSFWorkbook workBook = new HSSFWorkbook();

		int index = 0;

		for (String excelHeader : headerBean.getExcelSplitWorksheetName()) {
			// Create Sheet
			HSSFSheet sheet = createsheet(workBook, excelHeader);
			int rowCount = 0;

			int splitIndex = index++;

			int titleCellSize = headerBean.getExcelSplitIndexList().get(splitIndex)[1]
					- headerBean.getExcelSplitIndexList().get(splitIndex)[0] + headerBean.getExcelLeftTableEndIndex()
					+ 1;

			// Create Title Row Only For Tree Table
			if (inputBean.isIsTreeTable() || inputBean.isTitleNeeded()) {
				rowCount += createTitleRow(sheet, titleCellSize, inputBean.getExportFileName(), rowCount,
						defaultTitleCellStyle, workBook);
			}

			rowCount += createSplitHeaderRow(sheet, headers, rowCount, defaultHeadersCellStyle, workBook, headerBean,
					splitIndex);
			List<Object> inputList = Arrays.asList(resultList, visibleColumns, splitIndex);
			createSplitDataRows(sheet, inputList, rowCount, workBook, resultTable, inputBean, headerBean);
			// Grouping Rows needed for Tree Table
			if (inputBean.isIsTreeTable()) {
				groupRowsForTreeTable(sheet, resultList, inputBean.isNeedTobeCollapsed());
				sheet.setRowSumsBelow(false);
			}
		}
		return workBook;
	}

	private HSSFSheet createsheet(HSSFWorkbook workbook, String exportFileName) {
		return workbook.createSheet(exportFileName);

	}

	/**
	 * Used to grouping rows in Excel based on the Additional properties present
	 * in the DTO getAdditionalProperties (0) true if grouping Needed
	 * getAdditionalProperties (1) row no of start row to be Grouped
	 * getAdditionalProperties (2) row no of End row to be Grouped
	 *
	 * @param sheet
	 * @param resultList
	 */
	private void groupRowsForTreeTable(HSSFSheet sheet, List<GtnWsRecordBean> resultList, boolean isNeedtoCollapsed) {
		for (int i = 0; i < resultList.size(); i++) {
			GtnWsRecordBean resultDTO = resultList.get(i);
			if (!resultDTO.getAdditionalProperties().isEmpty()
					&& Boolean.valueOf(resultDTO.getAdditionalProperties().get(0).toString())) {
				groupExcelRow(sheet, Integer.valueOf(resultDTO.getAdditionalProperties().get(1).toString()),
						Integer.valueOf(resultDTO.getAdditionalProperties().get(2).toString()), isNeedtoCollapsed);
			}
		}
	}

	/**
	 * * Creates a Cell and Sets the Value and Style
	 *
	 * @param sheet
	 * @param row
	 * @param value
	 * @param cellNo
	 * @param cellStyle
	 */
	private void putValueInCell(Row row, Object value, int cellNo, CellStyle cellStyle) {
		Cell cell = row.createCell(cellNo);
		cell.setCellValue(checkPropertyNullvalue(value));
		cell.setCellStyle(cellStyle);
	}

	private void groupExcelRow(HSSFSheet sheet, int startRow, int endRow, boolean isNeedtoCollapsed) {
		sheet.groupRow(startRow + 2, endRow + 2);
		sheet.setRowGroupCollapsed(startRow + 2, isNeedtoCollapsed);
	}

	private GtnUIFrameworkWebserviceRequest createExcelServiceRequest(GtnUIFrameworkExcelButtonConfig inputBean,
			String componentId) {

		Object[] inputComponents = inputBean.getServiceInput();
		boolean isTreeTable = inputBean.isIsTreeTable();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		Object[] serviceInputArray = new Object[inputComponents.length];
		System.arraycopy(inputComponents, 0, serviceInputArray, 0, inputComponents.length);
		if (isTreeTable) {
			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(inputComponents[0].toString(), componentId);
			serviceInputArray[0] = gtnUIFrameworkComponentData.getCustomData();
		}
		GtnwsExcelRequest gtnwsExcelRequest = new GtnwsExcelRequest();

		gtnwsExcelRequest.setInputs(serviceInputArray);
		serviceRequest.setGtnwsExcelRequest(gtnwsExcelRequest);
		return serviceRequest;
	}

	private GtnWsExcelResponse callWebService(String url, String serviceType,
			GtnUIFrameworkWebserviceRequest serviceRequest) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(url, serviceType, serviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return response.getGtnWsExcelResponse();
	}

	@SuppressWarnings("deprecation")
	public void sendTheExcelToUser(String exportFileName, HSSFWorkbook workBook) {
		String exportFile = exportFileName;
		File tempFile = null;
		try {
			tempFile = File.createTempFile(GtnFrameworkCommonStringConstants.TMP,
					GtnFrameworkCommonStringConstants.DOT_XLS);
		} catch (IOException e) {
			gtnLogger.error(exportFile, e);
		}
		TemporaryFileDownloadResource resource;
		try (FileOutputStream fileOut = new FileOutputStream(tempFile);) {
			if (Page.getCurrent().getWebBrowser().isFirefox()) {
				exportFile = exportFile.replace(' ', '_');
			}
			workBook.write(fileOut);
			resource = new TemporaryFileDownloadResource(null, exportFile + GtnFrameworkCommonStringConstants.DOT_XLS,
					EXCEL_MIME_TYPE, tempFile);
			UI.getCurrent().getPage().open(resource, GtnFrameworkCommonStringConstants.UNDERSCORE_BLANK, false);
		} catch (final IOException e) {
			gtnLogger.error(exportFile, e);
		} finally {
			if (tempFile != null) {
				tempFile.deleteOnExit();
			}
		}
	}

	private int createTitleRow(HSSFSheet sheet, int columnSize, String exportFileName, int rowCount,
			CellStyle defaultTitleCellStyle, HSSFWorkbook workBook) {
		int count = rowCount;
		Row titlerow = sheet.createRow(count++);
		putValueInCell(titlerow, exportFileName, 0, defaultTitleCellStyle(defaultTitleCellStyle, workBook));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnSize - 1));
		return count;
	}

	private int createHeaderRow(HSSFSheet sheet, List<String> headers, int rowCount, CellStyle defaultHeadersCellStyle,
			HSSFWorkbook workBook) {
		int count = rowCount;
		Row headerRow = sheet.createRow(count++);

		for (int j = 0; j < headers.size(); j++) {
			sheet.autoSizeColumn(j);
			putValueInCell(headerRow, String.valueOf(headers.get(j)), j,
					defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
		}
		headerRow.setHeight((short) 600);

		return count;

	}

	private int createSplitHeaderRow(HSSFSheet sheet, List<String> headers, int rowCount,
			CellStyle defaultHeadersCellStyle, HSSFWorkbook workBook, GtnWsExcelHeaderBean headerBean,
			int splitWorksheetIndex) {
		int count = rowCount;
		Row headerRow = sheet.createRow(count++);

		for (int j = 0; j < headerBean.getExcelLeftTableEndIndex(); j++) {
			putValueInCell(headerRow, String.valueOf(headers.get(j)), j,
					defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
		}

		int headerStartIndex = headerBean.getExcelSplitIndexList().get(splitWorksheetIndex)[0];
		int headerEndIndex = headerBean.getExcelSplitIndexList().get(splitWorksheetIndex)[1];

		int cellStart = headerBean.getExcelLeftTableEndIndex();
		for (int j = headerStartIndex; j <= headerEndIndex; j++) {
			putValueInCell(headerRow, String.valueOf(headers.get(j)), cellStart++,
					defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
		}
		headerRow.setHeight((short) 600);

		return count;

	}

	private int createDataRows(HSSFSheet sheet, List<GtnWsRecordBean> resultList, List<Object> propertyIds,
			int rowCount, HSSFWorkbook workBook, PagedGrid resultTable,
			GtnUIFrameworkExcelButtonConfig inputBean) {
		int count = rowCount;
		List<String> componentMappedPropertyIdList = inputBean.getHelperTableMapedPropertyIdList();
		CellStyle defaultDataCellStyle = defaultDataCellStyle(workBook);
            for (GtnWsRecordBean resultDTO : resultList) {
                Row row = sheet.createRow(count++);
                for (int j = 0; j < propertyIds.size(); j++) {
                    String propertyId = String.valueOf(propertyIds.get(j));
                    Object value = resultDTO.getPropertyValue(propertyId);
                    if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))
                            && value != null) {
                        String componentId = inputBean.getExportTableId() + 0 + propertyId;
                        ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
                                .getComponent();
                        String componenetIdCaption = component.getValue().toString();
                        value = componenetIdCaption.equalsIgnoreCase(GtnFrameworkCommonStringConstants.SELECT_ONE)
                                ? GtnFrameworkCommonStringConstants.STRING_EMPTY : componenetIdCaption;
                    }
                    putValueInCell(row, getFormattedValue(value, propertyId, resultTable), j, defaultDataCellStyle);
                }
                row.setHeight((short) 400);
            }
		return count;
	}

	@SuppressWarnings("unchecked")
	private int createSplitDataRows(HSSFSheet sheet, List<Object> inputList, int rowCount, HSSFWorkbook workBook,
			PagedGrid resultTable, GtnUIFrameworkExcelButtonConfig inputBean, GtnWsExcelHeaderBean headerBean) {
		int count = rowCount;
		List<GtnWsRecordBean> resultList = (List<GtnWsRecordBean>) inputList.get(0);
		List<Object> propertyIds = (List<Object>) inputList.get(1);
		int splitWorksheetIndex = (int) inputList.get(2);
		List<String> componentMappedPropertyIdList = inputBean.getHelperTableMapedPropertyIdList();
		CellStyle defaultDataCellStyle = defaultDataCellStyle(workBook);

		int headerStartIndex = headerBean.getExcelSplitIndexList().get(splitWorksheetIndex)[0];
		int headerEndIndex = headerBean.getExcelSplitIndexList().get(splitWorksheetIndex)[1];

		for (int i = 0; i < resultList.size(); i++) {
			GtnWsRecordBean resultDTO = resultList.get(i);
			Row row = sheet.createRow(count++);
			for (int j = 0; j < headerBean.getExcelLeftTableEndIndex(); j++) {
				String propertyId = String.valueOf(propertyIds.get(j));
				Object value = resultDTO.getPropertyValue(propertyId);
				if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))) {
					String componentId = inputBean.getExportTableId() + 0 + propertyId;
					ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
							.getComponent();
					String componenetIdCaption = component.getValue().toString();
					value = getComboBoxValue(componenetIdCaption);
				}
				putValueInCell(row, getFormattedValue(value, propertyId, resultTable), j, defaultDataCellStyle);
			}
			int cellStart = headerBean.getExcelLeftTableEndIndex();
			for (int j = headerStartIndex; j <= headerEndIndex; j++) {
				String propertyId = String.valueOf(propertyIds.get(j));
				Object value = resultDTO.getPropertyValue(propertyId);
				if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))) {
					String componentId = inputBean.getExportTableId() + 0 + propertyId;
					ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
							.getComponent();
					String componenetIdCaption = component.getValue().toString();
					value = getComboBoxValue(componenetIdCaption);
				}
				putValueInCell(row, getFormattedValue(value, propertyId, resultTable), cellStart++,
						defaultDataCellStyle);
			}

			row.setHeight((short) 400);
		}
		return count;
	}

	public Object getFormattedValue(Object value, String propertyId, PagedGrid resultTable) {
//		if (resultTable != null) {
//			Converter<String, Object> converter = resultTable.getGrid().getConverter(propertyId);
//			if (converter != null) {
//				return converter.convertToPresentation(value, String.class, resultTable.getLocale());
//			}
//		}

		return value;

	}

	private String checkPropertyNullvalue(Object value) {
		String stringValue = String.valueOf(value);
		return GtnFrameworkCommonStringConstants.STRING_NULL.equalsIgnoreCase(stringValue)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY : stringValue;
	}

	private CellStyle setAllBordersThin(CellStyle cellStyle) {
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyle;
	}

	protected CellStyle defaultDataCellStyle(HSSFWorkbook workBook) {
		CellStyle defaultDataCellStyle = workBook.createCellStyle();
		defaultDataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		setAllBordersThin(defaultDataCellStyle);
		return defaultDataCellStyle;
	}

	protected CellStyle defaultHeadersCellStyle(CellStyle defaultHeadersCellStyle, HSSFWorkbook workBook) {
		CellStyle defaultHeadersStyle = defaultHeadersCellStyle;

		if (defaultHeadersStyle == null) {
			defaultHeadersStyle = workBook.createCellStyle();
		}
		Font font = workBook.createFont();
		font.setColor(HSSFColor.WHITE.index);
		defaultHeadersStyle.setFont(font);
		defaultHeadersStyle.setAlignment(CellStyle.ALIGN_CENTER);
		defaultHeadersStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		defaultHeadersStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		defaultHeadersStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		defaultHeadersStyle.setWrapText(true);
		setAllBordersThin(defaultHeadersStyle);
		return defaultHeadersStyle;
	}

	protected CellStyle defaultTitleCellStyle(CellStyle defaultTitleCellStyle, HSSFWorkbook workBook) {
		CellStyle defaultTitleStyle = defaultTitleCellStyle;
		if (defaultTitleStyle == null) {
			final Font titleFont = workBook.createFont();
			titleFont.setFontHeightInPoints((short) 18);
			titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
			defaultTitleStyle = workBook.createCellStyle();
			defaultTitleStyle.setAlignment(CellStyle.ALIGN_CENTER);
			defaultTitleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			defaultTitleStyle.setFont(titleFont);

		}
		return defaultTitleStyle;
	}

	private List<GtnWsRecordBean> setFilters(List<GtnWsRecordBean> exportList, GtnUIFrameworkComponentData customData) {
		List<GtnWsRecordBean> localExportList = new ArrayList<>(exportList);
		GtnUIFrameworkPagedTableLogic tableLogic = customData.getCurrentPageTableLogic();
//		Set<Container.Filter> filters = tableLogic.getFilters();
		tableLogic.clearFilters();
		int count = tableLogic.getCount();
		if (count > 0) {
			localExportList = tableLogic.loadData(0, count);
		}
//		tableLogic.setFilters(filters);
		return localExportList;
	}

	private void excludeColumnList(GtnUIFrameworkExcelButtonConfig inputBean, List<Object> propertyIds,
			List<String> headers) {
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
	}

	private Object getComboBoxValue(String componenetIdCaption) {
		return componenetIdCaption.equalsIgnoreCase(GtnFrameworkCommonStringConstants.SELECT_ONE)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY : componenetIdCaption;
	}
}
