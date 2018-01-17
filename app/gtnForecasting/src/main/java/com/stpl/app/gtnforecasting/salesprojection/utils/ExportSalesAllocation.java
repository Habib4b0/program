/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.utils;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
import java.util.Date;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

/**
 *
 * @author karthikraja.k
 */
public class ExportSalesAllocation extends ExcelExport {

    protected Map<String, String> formatter = null;

    public ExportSalesAllocation(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter) {
        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.formatter = formatter;
    }

    /**
     * This method is ultimately used by either addDataRows() or
     * addHierarchicalDataRows() to actually add the data to the Sheet.
     *
     * @param sheetToAddTo
     * @param rootItemId the root item id
     * @param row the row
     */
    protected final CellStyle style1 = this.workbook.createCellStyle();
    protected final CellStyle style2 = this.workbook.createCellStyle();
    protected DataFormat hssfDataFormat = this.workbook.createDataFormat();

    @Override
    protected void addDataRow(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
        final Row sheetRow = sheetToAddTo.createRow(row);
        for (int col = 0; col < getPropIds().size(); col++) {
            Object value;
            Object propId = getPropIds().get(col);
            Property prop = getProperty(rootItemId, propId);
            if (null == prop) {
                value = null;
            } else {
                value = prop.getValue();
            }
            Cell sheetCell = sheetRow.createCell(col);
            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(sheetCell, workbook, poiAlignment);

            if (this.formatter != null) {
                Double d = 0.0;
                try {
                    if (null != value) {
                        d = Double.parseDouble(value.toString());
                    }
                } catch (final NumberFormatException nfe) {
                    sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                    continue;
                }

                sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);

                Double cellValue = d;
                if (formatter.get(Constant.PERCENT_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_TWO_DECIMAL))) {

                    cellValue = cellValue / NumericConstants.HUNDRED;

                }

                sheetCell.setCellValue(cellValue);
                style1.setDataFormat(hssfDataFormat.getFormat("$#,##0_);($#,##0)"));
                style1.setAlignment(CellStyle.ALIGN_RIGHT);
                style2.setDataFormat(hssfDataFormat.getFormat("0.00%"));
                style2.setAlignment(CellStyle.ALIGN_RIGHT);
                if (formatter.get("currencyTwoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("currencyTwoDecimal"))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get(Constant.PERCENT_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_TWO_DECIMAL))) {
                    sheetCell.setCellStyle(style2);
                }

            } else {
                if (null != value) {
                    if (!isNumeric(prop.getType())) {
                        if (java.util.Date.class.isAssignableFrom(prop.getType())) {
                            sheetCell.setCellValue((Date) value);
                        } else {
                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }
                    } else {
                        try {
                            // parse all numbers as double, the format will determine how they appear
                            final Double d = Double.parseDouble(value.toString());
                            sheetCell.setCellValue(d);
                            sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        } catch (final NumberFormatException nfe) {

                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }
                    }
                }

            }

        }
    }
}
