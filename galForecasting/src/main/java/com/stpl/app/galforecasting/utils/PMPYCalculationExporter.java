/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.stpl.app.galforecasting.dto.PMPYCalculationExporterDTO;
import com.stpl.app.galforecasting.dto.PMPYCalculatorDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class PMPYCalculationExporter.
 *
 * @author lokeshwari
 */
public class PMPYCalculationExporter {

    /**
     * The Constant XLS_FORMAT.
     */
    public static final String XLS_FORMAT = ".xls";

    /**
     * The Constant FILE_NAME.
     */
    public static final String FILE_NAME = "PMPY Calculator";

    /**
     * The Constant EXCEL_MIME_TYPE.
     */
    public static final String EXCEL_MIME_TYPE = "application/vnd.ms-excel";

    /**
     * The Constant WINDOW_NAME.
     */
    public static final String WINDOW_NAME = "_blank";

    /**
     * The Constant EXCEL_ICON.
     */
    public static final String EXCEL_ICON = "../../icons/excel.png";

    /**
     * The Constant CHART_ICON.
     */
    public static final String CHART_ICON = "../../icons/chart.png";

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(PMPYCalculationExporter.class);

    /**
     * Export the calculation to workSheet.
     *
     * @param exporterDto the exporter dto
     * @throws SystemException the system exception
     */
    public void export(final PMPYCalculationExporterDTO exporterDto) throws SystemException {
        try {
            LOGGER.info("Entering export method ");

            final File tempFile = File.createTempFile(FILE_NAME, XLS_FORMAT);
            final FileOutputStream fileOut = new FileOutputStream(tempFile);
            final HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFCell cellA1;
            HSSFRow dataRow;
            HSSFRow headerRow;
            final HSSFCellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillPattern(HSSFCellStyle.NO_FILL);
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            final HSSFSheet worksheet = workbook.createSheet(FILE_NAME + " Worksheet");
            headerRow = worksheet.createRow(0);
            cellA1 = headerRow.createCell(0);
            cellA1.setCellValue(FILE_NAME);
            cellA1.setCellStyle(cellStyle);
            worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));

            final HSSFCellStyle cellStyleCurrency = workbook.createCellStyle();
            cellStyleCurrency.setDataFormat(Short.valueOf(String.valueOf(6)));

            final HSSFCellStyle cellStylePercentage = workbook.createCellStyle();
            final HSSFDataFormat dateformat = workbook.createDataFormat();
            cellStylePercentage.setDataFormat(dateformat.getFormat("#0.0%"));

            final HSSFCellStyle cellStyleUnit = workbook.createCellStyle();
            final HSSFDataFormat dataFormat = workbook.createDataFormat();
            cellStyleUnit.setDataFormat(dataFormat.getFormat("#0.0"));

            for (int i = 1; i < 10; i++) {
                dataRow = worksheet.createRow(i);
                if (i == Constant.ONE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Variable");
                    cellA1 = dataRow.createCell(1);
                    cellA1.setCellValue(exporterDto.getVariable());
                }
                if (i == Constant.TWO) {

                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Sales/Units");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getSales();
                    if (changeValue.isEmpty()) {

                        cellA1.setCellValue(exporterDto.getSales());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        if (String.valueOf(exporterDto.getVariable()).equals(Constant.SALES)) {
                            cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            cellA1.setCellStyle(cellStyleCurrency);
                            cellA1.setCellValue(value);
                        } else {

                            cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            cellA1.setCellStyle(cellStyleUnit);
                            cellA1.setCellValue(value);
                        }
                    }
                }
                if (i == Constant.THREE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Market Share (%)");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getMarketShare();

                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getMarketShare());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY);
                        double value = Double.parseDouble(changeValue);
                        value = Double.parseDouble(changeValue) / 100;
                        cellStylePercentage.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStylePercentage);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == Constant.FOUR) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Analog Lives");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getAnalogLives();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getAnalogLives());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleUnit);
                        cellA1.setCellValue(value);
                    }
                }

                if (i == Constant.FIVE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Value Per Life");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getValuePerLife();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getValuePerLife());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == Constant.SIX) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Market Share (%)");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getMarketShare1().replace(Constant.PERCENT, StringUtils.EMPTY);

                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getMarketShare1());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY);

                        final double value = Double.parseDouble(changeValue) / 100;
                        cellStylePercentage.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

                        cellA1.setCellStyle(cellStylePercentage);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == Constant.SEVEN) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Projected Lives");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getProjectedLives();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getProjectedLives());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                        cellA1.setCellStyle(cellStyleUnit);
                        cellA1.setCellValue(value);
                    }
                }
                if (i == Constant.EIGHT) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Total Sales");
                    cellA1 = dataRow.createCell(1);

                    String changeValue = exporterDto.getTotalSales();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getTotalSales());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        if (String.valueOf(exporterDto.getVariable()).equals(Constant.SALES)) {
                            cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            cellA1.setCellStyle(cellStyleCurrency);
                            cellA1.setCellValue(value);
                        } else {

                            cellStyleUnit.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                            cellA1.setCellStyle(cellStyleUnit);
                            cellA1.setCellValue(value);
                        }
                    }
                }
                if (i == Constant.NINE) {
                    cellA1 = dataRow.createCell(0);
                    cellA1.setCellValue("Projection Period Total");
                    cellA1 = dataRow.createCell(1);
                    String changeValue = exporterDto.getProjectionPeriodTotal();
                    if (changeValue.isEmpty()) {
                        cellA1.setCellValue(exporterDto.getProjectionPeriodTotal());
                    } else {
                        changeValue = changeValue.replaceAll(PMPYCalculatorDTO.COMMA, StringUtils.EMPTY).replace(Constant.$, StringUtils.EMPTY);
                        final double value = Double.parseDouble(changeValue);
                        cellStyleCurrency.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

                        cellA1.setCellStyle(cellStyleCurrency);
                        cellA1.setCellValue(value);

                    }
                }
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();

            Page.getCurrent().open(new TemporaryFileDownloadResource(UI.getCurrent(), FILE_NAME + ".xls", EXCEL_MIME_TYPE, tempFile), WINDOW_NAME, true);
            tempFile.deleteOnExit();
            LOGGER.info("End of export method");
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
