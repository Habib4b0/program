/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.utils;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.data.Property;
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
 * @author maheshj
 */
public class CustomExcelNM extends ExcelExport {

    Map<String, String> formatter = null;
    final CellStyle style1 = this.workbook.createCellStyle();
    final CellStyle style2 = this.workbook.createCellStyle();
    final CellStyle style3 = this.workbook.createCellStyle();
    DataFormat hssfDataFormat = this.workbook.createDataFormat();


    public CustomExcelNM(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter) {
      
        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.formatter = formatter;
    }

    /**
     * This method is ultimately used by either addDataRows() or
     * addHierarchicalDataRows() to actually add the data to the Sheet.
     *
     * @param rootItemId the root item id
     * @param row the row
     */
    @Override
    protected void addDataRow(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
        final Row sheetRow = sheetToAddTo.createRow(row);

        Property prop;
        Object propId;
        Object value;
        Cell sheetCell;
       style1.setDataFormat(hssfDataFormat.getFormat("$#,##0_);($#,##0)"));
       style2.setDataFormat(hssfDataFormat.getFormat("#,##0"));
       style3.setDataFormat(hssfDataFormat.getFormat("0.00%"));

        for (int col = 0; col < getPropIds().size(); col++) {

            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            if (null == prop) {
                value = null;
            } else {
                value = prop.getValue();
            }
            sheetCell = sheetRow.createCell(col);

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
                if (formatter.get("percentTwoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("percentTwoDecimal"))) {
                    if (d > 0) {
                        cellValue = cellValue / 100;
                    }
                }
                sheetCell.setCellValue(cellValue);
                
                if (formatter.get("currencyNoDecimal")!=null && String.valueOf(propId).endsWith(formatter.get("currencyNoDecimal"))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get("unitNoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("unitNoDecimal"))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get("percentTwoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("percentTwoDecimal"))) {
                    sheetCell.setCellStyle(style3);
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
