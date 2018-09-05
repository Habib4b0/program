/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action;

import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GtnUIFrameWorkPagedGridExcelExportAction implements GtnUIFrameWorkAction {

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkPagedGridExcelExportAction.class);
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        //No need to configure
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {

        GtnUIFrameworkExcelButtonConfig inputBean = (GtnUIFrameworkExcelButtonConfig) gtnUIFrameWorkActionConfig
                .getActionParameterList().get(0);

        GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
                .getVaadinComponentData(inputBean.getExportTableId(), componentId);
        PagedGrid pagedGrid = (PagedGrid) componentData.getCustomData();
        List<Object> propertyIds = new LinkedList<>(pagedGrid.getTableConfig().getColumnHeaders());
        List<String> headers = new LinkedList<>(pagedGrid.getTableConfig().getColumnHeaders());
        excludeColumnList(inputBean, propertyIds, headers);
        XSSFWorkbook workBook;
        try {
            workBook = writeInExcel(inputBean, propertyIds, headers, pagedGrid);
            sendTheExcelToUser(inputBean.getExportFileName(), workBook);
        } catch (IOException ex) {
            Logger.getLogger(GtnUIFrameWorkPagedGridExcelExportAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

    private void excludeColumnList(GtnUIFrameworkExcelButtonConfig inputBean, List<Object> propertyIds, List<String> headers) {
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

    private XSSFWorkbook writeInExcel(GtnUIFrameworkExcelButtonConfig inputBean, List<Object> propertyIds, List<String> headers, PagedGrid pagedGrid) throws IOException {
        CellStyle defaultHeadersCellStyle = null;
        CellStyle defaultTitleCellStyle = null;

        String exportFileName = inputBean.getExportFileName();
        XSSFWorkbook workBook = new XSSFWorkbook();
        XSSFSheet sheet = createsheet(workBook, exportFileName);

        int rowCount = 0;
        if (inputBean.isIsTreeTable() || inputBean.isTitleNeeded()) {
            rowCount = createTitleRow(sheet, propertyIds.size(), exportFileName, rowCount, defaultTitleCellStyle,
                    workBook);
        }

        rowCount = createHeaderRow(sheet, headers, rowCount, defaultHeadersCellStyle, workBook);
        createDataRows(sheet, workBook, pagedGrid, rowCount);
        handleAutoWidth(sheet, headers, true);

        return workBook;

    }

    private void sendTheExcelToUser(String exportFileName, XSSFWorkbook workBook) {
        String gridExportFile = exportFileName;
        File excelGridTempFile = null;
        try {
            excelGridTempFile = File.createTempFile(GtnFrameworkCommonStringConstants.TMP,
                    GtnFrameworkCommonStringConstants.DOT_XLS);
        } catch (IOException e) {
            gtnLogger.error(gridExportFile, e);
        }
        TemporaryFileDownloadResource excelGridResource;
        try (FileOutputStream fileOut = new FileOutputStream(excelGridTempFile);) {
            if (Page.getCurrent().getWebBrowser().isFirefox()) {
                gridExportFile = gridExportFile.replace(' ', '_');
            }
            workBook.write(fileOut);
            excelGridResource = new TemporaryFileDownloadResource(null, gridExportFile + GtnFrameworkCommonStringConstants.DOT_XLS,
                    EXCEL_MIME_TYPE, excelGridTempFile);
            UI.getCurrent().getPage().open(excelGridResource, GtnFrameworkCommonStringConstants.UNDERSCORE_BLANK, false);
        } catch (final IOException e) {
            gtnLogger.error(gridExportFile, e);
        } finally {
            if (excelGridTempFile != null) {
                excelGridTempFile.deleteOnExit();
            }
        }
    }

    private XSSFSheet createsheet(XSSFWorkbook workBook, String exportFileName) {
        return workBook.createSheet(exportFileName);

    }

    private int createTitleRow(XSSFSheet sheet, int size, String exportFileName, int rowCount, CellStyle defaultTitleCellStyle, XSSFWorkbook workBook) {
        int count = rowCount;
        Row titlerow = sheet.createRow(count++);
        putValueInCell(titlerow, exportFileName, 0, defaultTitleCellStyle(defaultTitleCellStyle, workBook));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, size - 1));
        return count;

    }

    protected CellStyle defaultTitleCellStyle(CellStyle defaultTitleCellStyle, XSSFWorkbook workSheet) {
        CellStyle treeGridDefaultTitleStyle = defaultTitleCellStyle;
        if (treeGridDefaultTitleStyle == null) {
            final Font treeTitleFont = workSheet.createFont();
            treeTitleFont.setFontHeightInPoints((short) 18);
            treeTitleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            treeGridDefaultTitleStyle = workSheet.createCellStyle();
            treeGridDefaultTitleStyle.setAlignment(CellStyle.ALIGN_CENTER);
            treeGridDefaultTitleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            treeGridDefaultTitleStyle.setFont(treeTitleFont);

        }
        return treeGridDefaultTitleStyle;
    }

    private void putValueInCell(Row titlerow, String exportFileName, int i, CellStyle defaultTitleCellStyle) {
        Cell cell = titlerow.createCell(i);
        cell.setCellValue(checkPropertyNullvalue(exportFileName));
        cell.setCellStyle(defaultTitleCellStyle);
    }

    private String checkPropertyNullvalue(Object exportFileName) {
        String excelGridStringValue = String.valueOf(exportFileName);
        return GtnFrameworkCommonStringConstants.STRING_NULL.equalsIgnoreCase(excelGridStringValue)
                ? GtnFrameworkCommonStringConstants.STRING_EMPTY : excelGridStringValue;
    }

    private int createHeaderRow(XSSFSheet sheet, List<String> headers, int rowCount, CellStyle defaultHeadersCellStyle, XSSFWorkbook workBook) {
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

    private CellStyle defaultHeadersCellStyle(CellStyle defaultHeadersCellStyle, XSSFWorkbook excelBook) {
        CellStyle gridExcelDefaultHeadersStyle = defaultHeadersCellStyle;

        if (gridExcelDefaultHeadersStyle == null) {
            gridExcelDefaultHeadersStyle = excelBook.createCellStyle();
        }
        Font cellStyleFont = excelBook.createFont();
        cellStyleFont.setColor(HSSFColor.WHITE.index);
        gridExcelDefaultHeadersStyle.setFont(cellStyleFont);
        gridExcelDefaultHeadersStyle.setAlignment(CellStyle.ALIGN_CENTER);
        gridExcelDefaultHeadersStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        gridExcelDefaultHeadersStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        gridExcelDefaultHeadersStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        gridExcelDefaultHeadersStyle.setWrapText(true);
        setAllBordersThin(gridExcelDefaultHeadersStyle);
        return gridExcelDefaultHeadersStyle;
    }

    private CellStyle setAllBordersThin(CellStyle gridExcelDefaultHeadersStyle) {
        gridExcelDefaultHeadersStyle.setBorderRight(CellStyle.BORDER_THIN);
        gridExcelDefaultHeadersStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        gridExcelDefaultHeadersStyle.setBorderLeft(CellStyle.BORDER_THIN);
        gridExcelDefaultHeadersStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        gridExcelDefaultHeadersStyle.setBorderTop(CellStyle.BORDER_THIN);
        gridExcelDefaultHeadersStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        gridExcelDefaultHeadersStyle.setBorderBottom(CellStyle.BORDER_THIN);
        gridExcelDefaultHeadersStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        return gridExcelDefaultHeadersStyle;
    }

    private void createDataRows(XSSFSheet sheet, XSSFWorkbook workBook, PagedGrid pagedGrid, int rowCount) throws IOException {
        File excelGridTempFile = File.createTempFile(GtnFrameworkCommonStringConstants.TMP,
                GtnFrameworkCommonStringConstants.DOT_XLS);
        ListDataProvider<GtnWsRecordBean> gridData = (ListDataProvider<GtnWsRecordBean>) pagedGrid.getGrid().getDataProvider();
        ArrayList<GtnWsRecordBean> gridItems = (ArrayList<GtnWsRecordBean>) gridData.getItems();
        List<GtnWsRecordBean> resultList = new ArrayList<>(gridItems);

        for (int i = 0; i < resultList.size(); i++) {
            Row row = sheet.createRow(rowCount++);
            GtnWsRecordBean recordBean = resultList.get(i);
            for (int j = 0; j < recordBean.getProperties().size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(String.valueOf(recordBean.getPropertyValueByIndex(j)));
            }
        }
        FileOutputStream outputStream = new FileOutputStream(excelGridTempFile);
        workBook.write(outputStream);
    }

    private void handleAutoWidth(XSSFSheet sheet, List<String> headers, boolean isNeedToAutoWidth) {
        for (int i = 0; i < headers.size() && isNeedToAutoWidth; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
