/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.utils.GridUtils;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

/**
 *
 * @author Karthik.Raja
 */
public class GtnUIFrameWorkTreeGridExcelExportAction implements GtnUIFrameWorkAction {

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkExcelExportAction.class);
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";
    public int excelRowCount=0;

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// Overridden method
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(0);
		List<GtnWsRecordBean> exportList = new ArrayList<>();
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData(inputBean.getExportTableId(), componentId);
		PagedTreeGrid treeGrid = (PagedTreeGrid) componentData.getCustomData();
		List<Object> propertyIds = new LinkedList<>(treeGrid.getTableConfig().getVisibleColumns());
		List<String> headers = new LinkedList<>(treeGrid.getTableConfig().getColumnHeaders());
		excludeColumnList(inputBean, propertyIds, headers);

		gtnLogger.info(propertyIds.size() + "");
		XSSFWorkbook workBook = writeInExcel(inputBean, exportList, propertyIds, headers, treeGrid);
		sendTheExcelToUser(inputBean.getExportFileName(), workBook);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private XSSFWorkbook writeInExcel(GtnUIFrameworkExcelButtonConfig inputBean, List<GtnWsRecordBean> resultList,
			List<Object> visibleColumns, List<String> headers, PagedTreeGrid resultTable) {
		CellStyle defaultHeadersCellStyle = null;
		CellStyle defaultTitleCellStyle = null;

		String exportFileName = inputBean.getExportFileName();
		// Create Work Book
		XSSFWorkbook workBook = new XSSFWorkbook();
		// Create Sheet
		XSSFSheet sheet = createsheet(workBook, exportFileName);
		int rowCount = 0;
		// Create Title Row Only For Tree Table
		if (inputBean.isIsTreeTable() || inputBean.isTitleNeeded()) {
			rowCount = createTitleRow(sheet, visibleColumns.size(), exportFileName, rowCount, defaultTitleCellStyle,
					workBook);
		}
		rowCount = createHeaderRow(sheet, headers, rowCount, defaultHeadersCellStyle, workBook, resultTable);
		createDataRows(sheet, visibleColumns, workBook, resultTable, inputBean, rowCount);
		handleAutoWidth(sheet, headers, true);
		// Grouping Rows needed for Tree Table

		return workBook;
	}

	/**
	 * If Your Sheet is to large, disable auto width on sheets
	 *
	 * @param sheet
	 * @param headers
	 * @param isNeedToAutoWidth
	 */
	private void handleAutoWidth(XSSFSheet sheet, List<String> headers, boolean isNeedToAutoWidth) {
		for (int i = 0; i < headers.size() && isNeedToAutoWidth; i++) {
			sheet.autoSizeColumn(i);
		}

	}

	private XSSFSheet createsheet(XSSFWorkbook workbook, String exportFileName) {
		return workbook.createSheet(exportFileName);

	}

	/**
	 * Used to grouping rows in Excel based on the Additional properties present in
	 * the DTO getAdditionalProperties (0) true if grouping Needed
	 * getAdditionalProperties (1) row no of start row to be Grouped
	 * getAdditionalProperties (2) row no of End row to be Grouped
	 *
	 * @param sheet
	 * @param resultList
	 */
	private void groupRowsForTreeTable(XSSFSheet sheet, List<GtnWsRecordBean> resultList, boolean collapseNeeeded) {
		for (GtnWsRecordBean resultDTO : resultList) {
			if (!resultDTO.getAdditionalProperties().isEmpty()
					&& Boolean.parseBoolean(resultDTO.getAdditionalProperties().get(5).toString())) {
				groupExcelRow(sheet, Integer.parseInt(resultDTO.getAdditionalProperties().get(3).toString()),
						Integer.parseInt(resultDTO.getAdditionalProperties().get(4).toString()), collapseNeeeded);
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

	private void groupExcelRow(XSSFSheet sheet, int startRow, int endRow, boolean collapse) {
		sheet.groupRow(startRow, endRow);
		sheet.setRowGroupCollapsed(startRow, collapse);
	}

	@SuppressWarnings("deprecation")
	public void sendTheExcelToUser(String exportFileName, XSSFWorkbook workBook) {
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

	private int createTitleRow(XSSFSheet sheet, int columnSize, String exportFileName, int rowCount,
			CellStyle defaultTitleCellStyle, XSSFWorkbook workBook) {
		int count = rowCount;
		Row titlerow = sheet.createRow(count++);
		putValueInCell(titlerow, exportFileName, 0, defaultTitleCellStyle(defaultTitleCellStyle, workBook));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnSize - 1));
		return count;
	}

	private int createHeaderRow(XSSFSheet sheet, List<String> headers, int rowCount, CellStyle defaultHeadersCellStyle,
			XSSFWorkbook workBook, PagedTreeGrid resultTable) {
		int count = rowCount;

		GtnUIFrameworkPagedTreeTableConfig config = resultTable.getTableConfig();
		count = addTripleHeader(config, sheet, count, defaultHeadersCellStyle, workBook);
		count = addDoubleHeader(config, sheet, count, defaultHeadersCellStyle, workBook);

		Row headerRow = sheet.createRow(count++);

		for (int j = 0; j < headers.size(); j++) {
			sheet.autoSizeColumn(j);
			putValueInCell(headerRow, String.valueOf(headers.get(j)), j,
					defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
		}
		headerRow.setHeight((short) 600);

		return count;

	}

	private int addDoubleHeader(GtnUIFrameworkPagedTreeTableConfig config, XSSFSheet sheet, int count,
			CellStyle defaultHeadersCellStyle, XSSFWorkbook workBook) {
		if (config.isDoubleHeaderVisible()) {
			Row doubleHeader = sheet.createRow(count++);
			int rightStart = 1;
			int i = 0;
			for (Object column : config.getRightTableDoubleHeaderVisibleColumns()) {
				int size = config.getRightTableDoubleHeaderMap().get(column).length;
				putValueInCell(doubleHeader, config.getRightTableDoubleVisibleHeaders().get(i++), rightStart,
						defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
				if (size == 1) {
					rightStart++;
					continue;
				}
				sheet.addMergedRegion(new CellRangeAddress(doubleHeader.getRowNum(), doubleHeader.getRowNum(),
						rightStart, rightStart + size - 1));
				rightStart += size;
			}
		}
		return count;
	}

	private int addTripleHeader(GtnUIFrameworkPagedTreeTableConfig config, XSSFSheet sheet, int count,
			CellStyle defaultHeadersCellStyle, XSSFWorkbook workBook) {
		if (config.isTripleHeaderVisible()) {
			Row tripleHeader = sheet.createRow(count++);

			int rightStart = 1;
			int i = 0;
			for (Object column : config.getRightTableTripleHeaderVisibleColumns()) {
				Object[] doubleHeaders = config.getRightTableTripleHeaderMap().get(column);
				int size = 0;
				for (Object doubleCol : doubleHeaders) {
					size += config.getRightTableDoubleHeaderMap().get(doubleCol).length;
				}
				putValueInCell(tripleHeader, config.getRightTableTripleVisibleHeaders().get(i++), rightStart,
						defaultHeadersCellStyle(defaultHeadersCellStyle, workBook));
				if (size == 1) {
					rightStart++;
					continue;
				}

				sheet.addMergedRegion(new CellRangeAddress(tripleHeader.getRowNum(), tripleHeader.getRowNum(),
						rightStart, rightStart + size - 1));
				rightStart += size;
			}
		}
		return count;
	}

	private int createDataRows(XSSFSheet sheet, List<Object> propertyIds, XSSFWorkbook workBook,
			PagedTreeGrid resultTable, GtnUIFrameworkExcelButtonConfig inputBean, int headerCount) {
		List<GtnWsRecordBean> resultList = resultTable.fetchAll();
		List<GtnWsRecordBean> child = resultList.stream()
				.filter(row -> GridUtils.getLevelNo(row) == resultTable.getTableConfig().getLevelNo())
				.collect(Collectors.toList());
		List<GtnWsRecordBean> tree = new ArrayList<>(resultList.size());
		excelRowCount = headerCount;
		buildTree(resultList, child, tree);
		resultList = null;// not needed anymore
		Integer count = headerCount;

		CellStyle defaultDataCellStyle = defaultDataCellStyle(workBook);
		for (GtnWsRecordBean resultDTO : tree) {
			Row row = sheet.createRow(count++);
			for (int j = 0; j < propertyIds.size(); j++) {
				String propertyId = String.valueOf(propertyIds.get(j));
				Object value = resultDTO.getPropertyValue(propertyId);
				putValueInCell(row, value, j, defaultDataCellStyle);
			}
			row.setHeight((short) 400);
		}
		if (inputBean.isIsTreeTable()) {
			groupRowsForTreeTable(sheet, tree, inputBean.isNeedTobeCollapsed());
			sheet.setRowSumsBelow(false);
		}
		return count;
	}

	void buildTree(List<GtnWsRecordBean> input, List<GtnWsRecordBean> child, List<GtnWsRecordBean> output) {
		for (GtnWsRecordBean bean : child) {
			output.add(bean);
			excelRowCount++;
			if (GridUtils.hasChildren(bean)) {
				int levelNo = GridUtils.getLevelNo(bean);
				String hierNo = GridUtils.getHierarchyNo(bean);
				child = input.stream().filter(row -> GridUtils.getLevelNo(row) == levelNo + 1
						&& GridUtils.getHierarchyNo(row).startsWith(hierNo)).collect(Collectors.toList());
				long totalChildCount = input.stream().filter(row -> GridUtils.getHierarchyNo(row).startsWith(hierNo))
						.count();
				bean.addAdditionalProperties(3, excelRowCount);
				bean.addAdditionalProperties(4, excelRowCount + totalChildCount - 2);
				bean.addAdditionalProperties(5, Boolean.TRUE);
				buildTree(input, child, output);
			} else {
				bean.addAdditionalProperties(5, Boolean.FALSE);
			}
		}
	}

	public Object getFormattedValue(Object value) {

		return value;

	}

	private String checkPropertyNullvalue(Object value) {
		String stringValue = String.valueOf(value);
		return GtnFrameworkCommonStringConstants.STRING_NULL.equalsIgnoreCase(stringValue)
				? GtnFrameworkCommonStringConstants.STRING_EMPTY
				: stringValue;
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

	protected CellStyle defaultDataCellStyle(XSSFWorkbook workBook) {
		CellStyle defaultDataCellStyle = workBook.createCellStyle();
		defaultDataCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		setAllBordersThin(defaultDataCellStyle);
		return defaultDataCellStyle;
	}

	protected CellStyle defaultHeadersCellStyle(CellStyle defaultHeadersCellStyle, XSSFWorkbook workBook) {
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

	protected CellStyle defaultTitleCellStyle(CellStyle defaultTitleCellStyle, XSSFWorkbook workBook) {
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

	private void excludeColumnList(GtnUIFrameworkExcelButtonConfig inputBean, List<Object> propertyIds,
			List<String> headers) {
		Optional<List<String>> excludeColumnsList = Optional.ofNullable(inputBean.getExcludeColumnsList());
		excludeColumnsList.ifPresent(list -> {
			for (String row : list) {
				int index = propertyIds.indexOf(row);
				if (index >= 0) {
					propertyIds.remove(index);
					headers.remove(index);
				}
			}
		});
	}

}
