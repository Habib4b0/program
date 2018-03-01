/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.util;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.v7.data.Property;
import java.util.Date;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

/**
 *
 * @author lokeshwari
 */
public class CustomExcelExport extends ExcelExport {

    private Map<String, String> formatter = null;
    private final CellStyle style1 = this.workbook.createCellStyle();
    private final CellStyle style2 = this.workbook.createCellStyle();
    private final CellStyle style3 = this.workbook.createCellStyle();
    private final CellStyle style4 = this.workbook.createCellStyle();
    private final DataFormat hssfDataFormat = this.workbook.createDataFormat();

    public CustomExcelExport(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter) {
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
        style1.setDataFormat(hssfDataFormat.getFormat("$#,##0"));
        style1.setAlignment(CellStyle.ALIGN_RIGHT);
        style2.setDataFormat(hssfDataFormat.getFormat("#,##0.0"));
        style2.setAlignment(CellStyle.ALIGN_RIGHT);
        style3.setAlignment(CellStyle.ALIGN_RIGHT);
        style4.setDataFormat(hssfDataFormat.getFormat("#,##0.00%"));
        style4.setAlignment(CellStyle.ALIGN_RIGHT);

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
                    if (value != null) {
                        d = DataTypeConverter.convertStringToDouble(value.toString().replace("$", "").replace(",", "").replace("%", ""));
                        sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        sheetCell.setCellValue(d);
                    }
                } catch (final NumberFormatException nfe) {
                    if ("-".equals(String.valueOf(value)) || "...".equals(String.valueOf(value)) || "- - -".equals(String.valueOf(value))) {
                        sheetCell.setCellValue(createHelper.createRichTextString(String.valueOf(value)));
                        sheetCell.setCellStyle(style3);
                    } else {
                        sheetCell.setCellValue(createHelper.createRichTextString(String.valueOf(value)));
                    }
                    continue;
                }

                if (formatter.get("currencyNoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("currencyNoDecimal"))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get("unitOneDecimal") != null && String.valueOf(propId).endsWith(formatter.get("unitOneDecimal"))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get("perTwoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("perTwoDecimal"))) {
                    sheetCell.setCellStyle(style4);
                }
            } else {
                if (value != null) {
                    if (!isNumeric(prop.getType())) {
                        if (java.util.Date.class.isAssignableFrom(prop.getType())) {
                            sheetCell.setCellValue((Date) value);
                        } else {
                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }
                    } else {
                        try {
                            final Double d = DataTypeConverter.convertStringToDouble(value.toString());
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
