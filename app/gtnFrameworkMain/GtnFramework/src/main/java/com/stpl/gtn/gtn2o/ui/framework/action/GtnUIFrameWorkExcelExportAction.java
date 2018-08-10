/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

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

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
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
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.converter.Converter;
import com.vaadin.server.Page;
import com.vaadin.v7.ui.ComboBox;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.ui.UI;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameWorkExcelExportAction implements GtnUIFrameWorkAction {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkExcelExportAction.class);
	public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

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
		boolean isExportFromTable = inputBean.isExportFromTable();
		List<Object> propertyIds = new ArrayList<>();
		List<String> headers = new ArrayList<>();
		List<GtnWsRecordBean> exportList = new ArrayList<>();
		ExtCustomTable resultTable = null;
		GtnWsExcelHeaderBean headerBean = null;
		if (isExportFromTable) {
			GtnUIFrameworkComponentData customData = (GtnUIFrameworkComponentData) (GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(inputBean.getExportTableId())).getComponent().getData();
			resultTable = (ExtCustomTable) customData.getCustomData();
			propertyIds = new LinkedList<>(Arrays.asList(resultTable.getVisibleColumns()));
			headers = new LinkedList<>(Arrays.asList(resultTable.getColumnHeaders()));
			if (inputBean.isIsNewTreeTable()) {
				exportList = inputBean.getExportList();
			} else {
				exportList = setFilters(exportList, customData);
			}

		} else {
			if (inputBean.getExportTableId() != null && !inputBean.getExportTableId().isEmpty()) {
				GtnUIFrameworkComponentData customData = (GtnUIFrameworkComponentData) (GtnUIFrameworkGlobalUI
						.getVaadinBaseComponent(inputBean.getExportTableId())).getComponent().getData();
				resultTable = (ExtCustomTable) customData.getCustomData();
				propertyIds = new LinkedList<>(Arrays.asList(resultTable.getVisibleColumns()));
				headers = new LinkedList<>(Arrays.asList(resultTable.getColumnHeaders()));
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
			HSSFWorkbook workBookFramework = writeSplitWorksheetExcel(inputBean, exportList, propertyIds, headers, resultTable,
					headerBean);
			sendTheExcelToUser(inputBean.getExportFileName(), workBookFramework);
		}

	}

	public GtnWsExcelHeaderBean getExcelHeaders(String componentIdFrameWork,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfigFrameWork) {
		GtnUIFrameworkExcelButtonConfig inputBeanFrameWork = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfigFrameWork
				.getActionParameterList().get(0);
		// create Web Service Request
		GtnUIFrameworkWebserviceRequest serviceRequestExcelHeaders = createExcelServiceRequest(inputBeanFrameWork, componentIdFrameWork);
		// Call webService for Load Data
		GtnWsExcelResponse response = callWebService(inputBeanFrameWork.getHeaderServiceUrl(), inputBeanFrameWork.getServiceType(),
				serviceRequestExcelHeaders);
		return response.getExcelHeaderBean();
	}

	public List<GtnWsRecordBean> getExcelDataList(String componentIdFrameWork,
			GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfigFrameWork) {

		GtnUIFrameworkExcelButtonConfig inputBeanDataList = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfigFrameWork
				.getActionParameterList().get(0);
		// create Web Service Request
		GtnUIFrameworkWebserviceRequest serviceRequest = createExcelServiceRequest(inputBeanDataList, componentIdFrameWork);
		// Call webService for Load Data
		GtnWsExcelResponse response = callWebService(inputBeanDataList.getLoadDataServiceUrl(), inputBeanDataList.getServiceType(),
				serviceRequest);

		return response.getResultBeanList();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private HSSFWorkbook writeInExcel(GtnUIFrameworkExcelButtonConfig inputBeanWriteExcel, List<GtnWsRecordBean> resultList,
			List<Object> visibleColumnsWrite, List<String> headers, ExtCustomTable resultTable) {
		CellStyle defaultHeadersCellStyle = null;
		CellStyle defaultTitleCellStyle = null;

		String exportFileName = inputBeanWriteExcel.getExportFileName();
		// Create Work Book
		HSSFWorkbook workBookWriteExcel = new HSSFWorkbook();
		// Create Sheet
		HSSFSheet sheet = createsheet(workBookWriteExcel, exportFileName);
		int rowCount = 0;
		// Create Title Row Only For Tree Table
		if (inputBeanWriteExcel.isIsTreeTable() || inputBeanWriteExcel.isTitleNeeded()) {
			rowCount = createTitleRow(sheet, visibleColumnsWrite.size(), exportFileName, rowCount, defaultTitleCellStyle,
					workBookWriteExcel);
		}
		rowCount = createHeaderRow(sheet, headers, rowCount, defaultHeadersCellStyle, workBookWriteExcel);
		createDataRows(sheet, resultList, visibleColumnsWrite, rowCount, workBookWriteExcel, resultTable, inputBeanWriteExcel);
		handleAutoWidth(sheet, headers, true);
		// Grouping Rows needed for Tree Table
		if (inputBeanWriteExcel.isIsTreeTable()) {
			groupRowsForTreeTable(sheet, resultList, inputBeanWriteExcel.isNeedTobeCollapsed());
			sheet.setRowSumsBelow(false);
		}
		return workBookWriteExcel;
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

	private HSSFWorkbook writeSplitWorksheetExcel(GtnUIFrameworkExcelButtonConfig inputBeanWriteSplit,
			List<GtnWsRecordBean> resultListWriteSplit, List<Object> visibleColumnsWriteSplit, List<String> headersWriteSplit,
			ExtCustomTable resultTableWriteSplit, GtnWsExcelHeaderBean headerBeanWriteSplit) {
		CellStyle defaultHeadersCellStyle = null;
		CellStyle defaultTitleCellStyle = null;

		// Create Work Book
		HSSFWorkbook workBookWriteSplit = new HSSFWorkbook();

		int index = 0;

		for (String excelHeader : headerBeanWriteSplit.getExcelSplitWorksheetName()) {
			// Create Sheet
			HSSFSheet sheetWriteSplit = createsheet(workBookWriteSplit, excelHeader);
			int rowCountWriteSplit = 0;

			int splitIndexWriteSplit = index++;

			int titleCellSize = headerBeanWriteSplit.getExcelSplitIndexList().get(splitIndexWriteSplit)[1]
					- headerBeanWriteSplit.getExcelSplitIndexList().get(splitIndexWriteSplit)[0] + headerBeanWriteSplit.getExcelLeftTableEndIndex()
					+ 1;

			// Create Title Row Only For Tree Table
			if (inputBeanWriteSplit.isIsTreeTable() || inputBeanWriteSplit.isTitleNeeded()) {
				rowCountWriteSplit += createTitleRow(sheetWriteSplit, titleCellSize, inputBeanWriteSplit.getExportFileName(), rowCountWriteSplit,
						defaultTitleCellStyle, workBookWriteSplit);
			}

			rowCountWriteSplit += createSplitHeaderRow(sheetWriteSplit, headersWriteSplit, rowCountWriteSplit, defaultHeadersCellStyle, workBookWriteSplit, headerBeanWriteSplit,
					splitIndexWriteSplit);
			List<Object> inputList = Arrays.asList(resultListWriteSplit, visibleColumnsWriteSplit, splitIndexWriteSplit);
			createSplitDataRows(sheetWriteSplit, inputList, rowCountWriteSplit, workBookWriteSplit, resultTableWriteSplit, inputBeanWriteSplit, headerBeanWriteSplit);
			// Grouping Rows needed for Tree Table
			if (inputBeanWriteSplit.isIsTreeTable()) {
				groupRowsForTreeTable(sheetWriteSplit, resultListWriteSplit, inputBeanWriteSplit.isNeedTobeCollapsed());
				sheetWriteSplit.setRowSumsBelow(false);
			}
		}
		return workBookWriteSplit;
	}

	private HSSFSheet createsheet(HSSFWorkbook workbookCreateSheet, String exportFileName) {
		return workbookCreateSheet.createSheet(exportFileName);

	}

	/**
	 * Used to grouping rows in Excel based on the Additional properties present
	 * in the DTO getAdditionalProperties (0) true if grouping Needed
	 * getAdditionalProperties (1) row no of start row to be Grouped
	 * getAdditionalProperties (2) row no of End row to be Grouped
	 *
	 * @param sheetGroupRows
	 * @param resultListGroupRows
	 */
	private void groupRowsForTreeTable(HSSFSheet sheetGroupRows, List<GtnWsRecordBean> resultListGroupRows, boolean isNeedtoCollapsedGroupRows) {
		for (int i = 0; i < resultListGroupRows.size(); i++) {
			GtnWsRecordBean resultDTOGroupRows = resultListGroupRows.get(i);
			if (!resultDTOGroupRows.getAdditionalProperties().isEmpty()
					&& Boolean.parseBoolean(resultDTOGroupRows.getAdditionalProperties().get(0).toString())) {
				groupExcelRow(sheetGroupRows, Integer.parseInt(resultDTOGroupRows.getAdditionalProperties().get(1).toString()),
						Integer.parseInt(resultDTOGroupRows.getAdditionalProperties().get(2).toString()), isNeedtoCollapsedGroupRows);
			}
		}
	}

	/**
	 * * Creates a Cell and Sets the Value and Style
	 *
	 * @param sheet
	 * @param rowInCell
	 * @param valueInCell
	 * @param cellNoInCell
	 * @param cellStyleInCell
	 */
	private void putValueInCell(Row rowInCell, Object valueInCell, int cellNoInCell, CellStyle cellStyleInCell) {
		Cell cell = rowInCell.createCell(cellNoInCell);
		cell.setCellValue(checkPropertyNullvalue(valueInCell));
		cell.setCellStyle(cellStyleInCell);
	}

	private void groupExcelRow(HSSFSheet sheetExcelRow, int startRow, int endRow, boolean isNeedtoCollapsed) {
		sheetExcelRow.groupRow(startRow + 2, endRow + 2);
		sheetExcelRow.setRowGroupCollapsed(startRow + 2, isNeedtoCollapsed);
	}

	private GtnUIFrameworkWebserviceRequest createExcelServiceRequest(GtnUIFrameworkExcelButtonConfig inputBeanCreateExcel,
			String componentIdCreateExcel) {

		Object[] inputComponentsCreateExcel = inputBeanCreateExcel.getServiceInput();
		boolean isTreeTableCreateExcel = inputBeanCreateExcel.isIsTreeTable();
		GtnUIFrameworkWebserviceRequest serviceRequestCreateExcel = new GtnUIFrameworkWebserviceRequest();
		Object[] serviceInputArrayCreateExcel = new Object[inputComponentsCreateExcel.length];
		System.arraycopy(inputComponentsCreateExcel, 0, serviceInputArrayCreateExcel, 0, inputComponentsCreateExcel.length);
		if (isTreeTableCreateExcel) {
			GtnUIFrameworkComponentData gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinComponentData(inputComponentsCreateExcel[0].toString(), componentIdCreateExcel);
			serviceInputArrayCreateExcel[0] = gtnUIFrameworkComponentData.getCustomData();
		}
		GtnwsExcelRequest gtnwsExcelRequestCreateExcel = new GtnwsExcelRequest();

		gtnwsExcelRequestCreateExcel.setInputs(serviceInputArrayCreateExcel);
		serviceRequestCreateExcel.setGtnwsExcelRequest(gtnwsExcelRequestCreateExcel);
		return serviceRequestCreateExcel;
	}

	private GtnWsExcelResponse callWebService(String urlCallService, String serviceTypeCallService,
			GtnUIFrameworkWebserviceRequest serviceRequestCallService) {
		GtnUIFrameworkWebServiceClient wsclientCallService = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceResponse responseCallService = wsclientCallService.callGtnWebServiceUrl(urlCallService, serviceTypeCallService, serviceRequestCallService,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		return responseCallService.getGtnWsExcelResponse();
	}

	@SuppressWarnings("deprecation")
	public void sendTheExcelToUser(String exportFileNameSendExcel, HSSFWorkbook workBookSendExcel) {
		String exportFileSendExcel = exportFileNameSendExcel;
		File tempFileSendExcel = null;
		try {
			tempFileSendExcel = File.createTempFile(GtnFrameworkCommonStringConstants.TMP,
					GtnFrameworkCommonStringConstants.DOT_XLS);
		} catch (IOException e) {
			gtnLogger.error(exportFileSendExcel, e);
		}
		TemporaryFileDownloadResource resourceSendExcel;
		try (FileOutputStream fileOut = new FileOutputStream(tempFileSendExcel);) {
			if (Page.getCurrent().getWebBrowser().isFirefox()) {
				exportFileSendExcel = exportFileSendExcel.replace(' ', '_');
			}
			workBookSendExcel.write(fileOut);
			resourceSendExcel = new TemporaryFileDownloadResource(null, exportFileSendExcel + GtnFrameworkCommonStringConstants.DOT_XLS,
					EXCEL_MIME_TYPE, tempFileSendExcel);
			UI.getCurrent().getPage().open(resourceSendExcel, GtnFrameworkCommonStringConstants.UNDERSCORE_BLANK, false);
		} catch (final IOException e) {
			gtnLogger.error(exportFileSendExcel, e);
		} finally {
			if (tempFileSendExcel != null) {
				tempFileSendExcel.deleteOnExit();
			}
		}
	}

	private int createTitleRow(HSSFSheet sheetTitleRow, int columnSizeTitleRow, String exportFileNameTitleRow, int rowCount,
			CellStyle defaultTitleCellStyle, HSSFWorkbook workBook) {
		int countTitleRow = rowCount;
		Row titlerow = sheetTitleRow.createRow(countTitleRow++);
		putValueInCell(titlerow, exportFileNameTitleRow, 0, defaultTitleCellStyle(defaultTitleCellStyle, workBook));
		sheetTitleRow.addMergedRegion(new CellRangeAddress(0, 0, 0, columnSizeTitleRow - 1));
		return countTitleRow;
	}

	private int createHeaderRow(HSSFSheet sheetHeaderRow, List<String> headerssheetHeaderRow, int rowCountHeaderRow, CellStyle defaultHeadersCellStyleHeaderRow,
			HSSFWorkbook workBookHeaderRow) {
		int countHeaderRow = rowCountHeaderRow;
		Row headerRow = sheetHeaderRow.createRow(countHeaderRow++);

		for (int j = 0; j < headerssheetHeaderRow.size(); j++) {
			sheetHeaderRow.autoSizeColumn(j);
			putValueInCell(headerRow, String.valueOf(headerssheetHeaderRow.get(j)), j,
					defaultHeadersCellStyle(defaultHeadersCellStyleHeaderRow, workBookHeaderRow));
		}
		headerRow.setHeight((short) 600);

		return countHeaderRow;

	}

	private int createSplitHeaderRow(HSSFSheet sheetCreateSplit, List<String> headersCreateSplit, int rowCountCreateSplit,
			CellStyle defaultHeadersCellStyleCreateSplit, HSSFWorkbook workBookCreateSplit, GtnWsExcelHeaderBean headerBeanCreateSplit,
			int splitWorksheetIndex) {
		int countCreateSplit = rowCountCreateSplit;
		Row headerRow = sheetCreateSplit.createRow(countCreateSplit++);

		for (int j = 0; j < headerBeanCreateSplit.getExcelLeftTableEndIndex(); j++) {
			putValueInCell(headerRow, String.valueOf(headersCreateSplit.get(j)), j,
					defaultHeadersCellStyle(defaultHeadersCellStyleCreateSplit, workBookCreateSplit));
		}

		int headerStartIndex = headerBeanCreateSplit.getExcelSplitIndexList().get(splitWorksheetIndex)[0];
		int headerEndIndex = headerBeanCreateSplit.getExcelSplitIndexList().get(splitWorksheetIndex)[1];

		int cellStart = headerBeanCreateSplit.getExcelLeftTableEndIndex();
		for (int j = headerStartIndex; j <= headerEndIndex; j++) {
			putValueInCell(headerRow, String.valueOf(headersCreateSplit.get(j)), cellStart++,
					defaultHeadersCellStyle(defaultHeadersCellStyleCreateSplit, workBookCreateSplit));
		}
		headerRow.setHeight((short) 600);

		return countCreateSplit;

	}

	private int createDataRows(HSSFSheet sheet, List<GtnWsRecordBean> resultList, List<Object> propertyIds,
			int rowCount, HSSFWorkbook workBook, ExtCustomTable resultTable,
			GtnUIFrameworkExcelButtonConfig inputBean) {
		int count = rowCount;
		List<String> componentMappedPropertyIdList = inputBean.getHelperTableMapedPropertyIdList();
		CellStyle defaultDataCellStyle = defaultDataCellStyle(workBook);
		for (int i = 0; i < resultList.size(); i++) {
			GtnWsRecordBean resultDTO = resultList.get(i);
			Row row = sheet.createRow(count++);
			for (int j = 0; j < propertyIds.size(); j++) {
				String propertyId = String.valueOf(propertyIds.get(j));
				Object value = resultDTO.getPropertyValue(propertyId);
				if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))
						&& value != null) {
					String componentId = inputBean.getExportTableId() + 0 + propertyId;
					ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
							.getComponent();
					String componenetIdCaption = component.getItemCaption(value);
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
	private int createSplitDataRows(HSSFSheet sheetDataRows, List<Object> inputListDataRows, int rowCountDataRows, HSSFWorkbook workBookDataRows, ExtCustomTable resultTableDataRows, GtnUIFrameworkExcelButtonConfig inputBeanDataRows, GtnWsExcelHeaderBean headerBeanDataRows) {
		int count = rowCountDataRows;
		List<GtnWsRecordBean> resultList = (List<GtnWsRecordBean>) inputListDataRows.get(0);
		List<Object> propertyIds = (List<Object>) inputListDataRows.get(1);
		int splitWorksheetIndex = (int) inputListDataRows.get(2);
		List<String> componentMappedPropertyIdList = inputBeanDataRows.getHelperTableMapedPropertyIdList();
		CellStyle defaultDataCellStyle = defaultDataCellStyle(workBookDataRows);

		int headerStartIndex = headerBeanDataRows.getExcelSplitIndexList().get(splitWorksheetIndex)[0];
		int headerEndIndex = headerBeanDataRows.getExcelSplitIndexList().get(splitWorksheetIndex)[1];

		for (int i = 0; i < resultList.size(); i++) {
			GtnWsRecordBean resultDTO = resultList.get(i);
			Row row = sheetDataRows.createRow(count++);
			for (int j = 0; j < headerBeanDataRows.getExcelLeftTableEndIndex(); j++) {
				String propertyIdDataRows = String.valueOf(propertyIds.get(j));
				Object valueDataRows = resultDTO.getPropertyValue(propertyIdDataRows);
				if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyIdDataRows))) {
					String componentId = inputBeanDataRows.getExportTableId() + 0 + propertyIdDataRows;
					ComboBox componentDataRows = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
							.getComponent();
					String componenetIdCaption = componentDataRows.getItemCaption(valueDataRows);
					valueDataRows = getComboBoxValue(componenetIdCaption);
				}
				putValueInCell(row, getFormattedValue(valueDataRows, propertyIdDataRows, resultTableDataRows), j, defaultDataCellStyle);
			}
			int cellStart = headerBeanDataRows.getExcelLeftTableEndIndex();
			for (int j = headerStartIndex; j <= headerEndIndex; j++) {
				String propertyId = String.valueOf(propertyIds.get(j));
				Object value = resultDTO.getPropertyValue(propertyId);
				if ((componentMappedPropertyIdList != null) && (componentMappedPropertyIdList.contains(propertyId))) {
					String componentId = inputBeanDataRows.getExportTableId() + 0 + propertyId;
					ComboBox component = (ComboBox) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
							.getComponent();
					String componenetIdCaption = component.getItemCaption(value);
					value = getComboBoxValue(componenetIdCaption);
				}
				putValueInCell(row, getFormattedValue(value, propertyId, resultTableDataRows), cellStart++,
						defaultDataCellStyle);
			}

			row.setHeight((short) 400);
		}
		return count;
	}

	public Object getFormattedValue(Object uiFormatedValue, String propertyId, ExtCustomTable resultTable) {
		if (resultTable != null) {
			Converter<String, Object> converter = resultTable.getConverter(propertyId);
			if (converter != null) {
				return converter.convertToPresentation(uiFormatedValue, String.class, resultTable.getLocale());
			}
		}

		return uiFormatedValue;

	}

	private String checkPropertyNullvalue(Object checkPropvalue) {
		String checkPropsStringValue = String.valueOf(checkPropvalue);
		return GtnFrameworkCommonStringConstants.STRING_NULL.equalsIgnoreCase(checkPropsStringValue)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY : checkPropsStringValue;
	}

	private CellStyle setAllBordersThin(CellStyle cellStyleAllBorders) {
		cellStyleAllBorders.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleAllBorders.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleAllBorders.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyleAllBorders.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleAllBorders.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleAllBorders.setTopBorderColor(IndexedColors.BLACK.getIndex());
		cellStyleAllBorders.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleAllBorders.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return cellStyleAllBorders;
	}

	protected CellStyle defaultDataCellStyle(HSSFWorkbook workBookDefault) {
		CellStyle defaultDataCellStyleExcel = workBookDefault.createCellStyle();
		defaultDataCellStyleExcel.setAlignment(CellStyle.ALIGN_CENTER);
		setAllBordersThin(defaultDataCellStyleExcel);
		return defaultDataCellStyleExcel;
	}

	protected CellStyle defaultHeadersCellStyle(CellStyle defaultHeadersCellStyleExcel, HSSFWorkbook workBookExcel) {
		CellStyle defaultHeadersStyleExcel = defaultHeadersCellStyleExcel;

		if (defaultHeadersStyleExcel == null) {
			defaultHeadersStyleExcel = workBookExcel.createCellStyle();
		}
		Font font = workBookExcel.createFont();
		font.setColor(HSSFColor.WHITE.index);
		defaultHeadersStyleExcel.setFont(font);
		defaultHeadersStyleExcel.setAlignment(CellStyle.ALIGN_CENTER);
		defaultHeadersStyleExcel.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		defaultHeadersStyleExcel.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		defaultHeadersStyleExcel.setFillPattern(CellStyle.SOLID_FOREGROUND);
		defaultHeadersStyleExcel.setWrapText(true);
		setAllBordersThin(defaultHeadersStyleExcel);
		return defaultHeadersStyleExcel;
	}

	protected CellStyle defaultTitleCellStyle(CellStyle defaultTitleCellStyleExcel, HSSFWorkbook workBookExcel) {
		CellStyle defaultTitleStyleExcel = defaultTitleCellStyleExcel;
		if (defaultTitleStyleExcel == null) {
			final Font titleFontExcel = workBookExcel.createFont();
			titleFontExcel.setFontHeightInPoints((short) 18);
			titleFontExcel.setBoldweight(Font.BOLDWEIGHT_BOLD);
			defaultTitleStyleExcel = workBookExcel.createCellStyle();
			defaultTitleStyleExcel.setAlignment(CellStyle.ALIGN_CENTER);
			defaultTitleStyleExcel.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			defaultTitleStyleExcel.setFont(titleFontExcel);

		}
		return defaultTitleStyleExcel;
	}

	private List<GtnWsRecordBean> setFilters(List<GtnWsRecordBean> exportListUi, GtnUIFrameworkComponentData customDataUi) {
		List<GtnWsRecordBean> localExportListUi = new ArrayList<>(exportListUi);
		GtnUIFrameworkPagedTableLogic tableLogicUi = customDataUi.getCurrentPageTableLogic();
		Set<Container.Filter> filters = tableLogicUi.getFilters();
		tableLogicUi.clearFilters();
		int countUiFramework = tableLogicUi.getCount();
		if (countUiFramework > 0) {
			localExportListUi = tableLogicUi.loadData(0, countUiFramework);
		}
		tableLogicUi.setFilters(filters);
		return localExportListUi;
	}

	private void excludeColumnList(GtnUIFrameworkExcelButtonConfig inputBeanUi, List<Object> propertyIdsUi,
			List<String> headers) {
		List<String> excludeColumnsListUi = inputBeanUi.getExcludeColumnsList();
		if (excludeColumnsListUi != null && !excludeColumnsListUi.isEmpty()) {
			for (int i = 0; i < excludeColumnsListUi.size(); i++) {
				int index = propertyIdsUi.indexOf(excludeColumnsListUi.get(i));
				if (index >= 0) {
					propertyIdsUi.remove(index);
					headers.remove(index);
				}
			}
		}
	}

	private Object getComboBoxValue(String componenetIdCaptionUi) {
		return componenetIdCaptionUi.equalsIgnoreCase(GtnFrameworkCommonStringConstants.SELECT_ONE)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY : componenetIdCaptionUi;
	}
}
