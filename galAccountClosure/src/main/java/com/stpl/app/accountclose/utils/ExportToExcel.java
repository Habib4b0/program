/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.data.Property;
import java.util.Date;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;

/**
 *
 * @author kasiammal.m
 */
public class ExportToExcel extends ExcelExport {

    Map<String, String> formatter = null;
    final CellStyle style1 = this.workbook.createCellStyle();
    final CellStyle style2 = this.workbook.createCellStyle();
    final CellStyle style3 = this.workbook.createCellStyle();
    final CellStyle style4 = this.workbook.createCellStyle();
    final CellStyle style5 = this.workbook.createCellStyle();
    final CellStyle style6 = this.workbook.createCellStyle();
    Object property;

    public ExportToExcel(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter, Object property) {
        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.formatter = formatter;
        this.property = property;
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

        DataFormat hssfDataFormat = this.workbook.createDataFormat();
        style1.setDataFormat(hssfDataFormat.getFormat("$#,##0"));
        style1.setAlignment(CellStyle.ALIGN_RIGHT);
        style2.setDataFormat(hssfDataFormat.getFormat("0.00%"));
        style2.setAlignment(CellStyle.ALIGN_RIGHT);
        style3.setDataFormat(hssfDataFormat.getFormat("#,##0.0"));
        style3.setAlignment(CellStyle.ALIGN_RIGHT);
        style4.setDataFormat(hssfDataFormat.getFormat("0.0%"));
        style4.setAlignment(CellStyle.ALIGN_RIGHT);
        style5.setDataFormat(hssfDataFormat.getFormat("#,##0"));
        style5.setAlignment(CellStyle.ALIGN_RIGHT);
        style6.setAlignment(CellStyle.ALIGN_RIGHT);
        Object levelValue = StringUtils.EMPTY;
        for (int col = 0; col < getPropIds().size(); col++) {

            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            levelValue = getProperty(rootItemId, property);
            if (null == prop) {
                value = StringUtils.EMPTY;
            } else {
                value = prop.getValue();
            }
            sheetCell = sheetRow.createCell(col);
            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(sheetCell, workbook, poiAlignment);
            if (this.formatter != null) {
                Double d = 0.0;
                try {
                    if (null != value && !String.valueOf(value).equalsIgnoreCase(StringUtils.EMPTY) && !String.valueOf(value).equalsIgnoreCase("null")) {
                        d = Double.parseDouble(value.toString().replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace("%", StringUtils.EMPTY));
                        sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        if ((formatter.get("percentTwoDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("percentTwoDecimal"))
                                || String.valueOf(propId).contains(formatter.get("percentTwoDecimal"))
                                || String.valueOf(propId).contains("Per"))) || (formatter.get("percentOneDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("percentOneDecimal"))
                                || String.valueOf(propId).contains(formatter.get("percentOneDecimal")) || String.valueOf(propId).contains("Per")
                                || String.valueOf(propId).contains("DiscountSalesValue") || String.valueOf(propId).contains("DiscountSalesVar"))
                                || String.valueOf(propId).contains("totaldiscountper") || String.valueOf(propId).contains("totaldiscountchangepercentage"))) {
                            sheetCell.setCellValue(d / 100);
                        } else {
                            sheetCell.setCellValue(d);
                        }
                    } else {
                        sheetCell.setCellValue(createHelper.createRichTextString(StringUtils.EMPTY));
                    }
                } catch (final NumberFormatException nfe) {
                    if ("-".equals(String.valueOf(value)) || "...".equals(String.valueOf(value)) || "- - -".equals(String.valueOf(value))) {
                        sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        sheetCell.setCellStyle(style6);
                    } else {
                        sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                    }
                    continue;
                }
                if (formatter.get("currencyTwoDecimal") != null && ((String.valueOf(levelValue).contains(formatter.get("currencyTwoDecimal")) && !String.valueOf(levelValue).contains("%")
                        && !String.valueOf(propId).contains("ContractSalesWACVarPer") && !String.valueOf(propId).equals("totaldiscountperpriorpercentage") && !String.valueOf(propId).equals("totaldiscountperprojectedpercentage") && !String.valueOf(propId).equals("totaldiscountchangepercentage"))
                        || "Discount $ Value".equals(String.valueOf(levelValue)) || "Discount $ Variance".equals(String.valueOf(levelValue))
                        || String.valueOf(propId).contains("GTSValue") || (String.valueOf(propId).contains("GTSVariance") && !String.valueOf(propId).contains("Per"))
                        || String.valueOf(propId).contains("DiscountAmountValue") || String.valueOf(propId).contains("DiscountAmountVar"))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get("oneDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("oneDecimal"))
                        || String.valueOf(propId).contains(formatter.get("oneDecimal")))) {
                    sheetCell.setCellStyle(style3);
                } else if (formatter.get("currencyDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("currencyDecimal"))
                        || (String.valueOf(propId).contains(formatter.get("currencyDecimal")) && !String.valueOf(propId).equals("totaldiscountperpriorpercentage")
                        && !String.valueOf(propId).equals("totaldiscountperprojectedpercentage") && !String.valueOf(propId).equals("totaldiscountchangepercentage")))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get("percentTwoDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("percentTwoDecimal"))
                        || String.valueOf(propId).contains(formatter.get("percentTwoDecimal"))
                        || String.valueOf(propId).contains("Per"))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get("discountDol") != null && (String.valueOf(levelValue).contains(formatter.get("discountDol"))
                        || (String.valueOf(propId).contains(formatter.get("discountDol")) && !String.valueOf(propId).equals("totaldiscountperpriorpercentage") && !String.valueOf(propId).equals("totaldiscountperprojectedpercentage") && !String.valueOf(propId).equals("totaldiscountchangepercentage")))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get("percent2Decimal") != null && (String.valueOf(levelValue).contains(formatter.get("percent2Decimal"))
                        || String.valueOf(propId).contains(formatter.get("percent2Decimal")))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get("percentOneDecimal") != null && (String.valueOf(levelValue).contains(formatter.get("percentOneDecimal"))
                        || String.valueOf(propId).contains(formatter.get("percentOneDecimal")) || String.valueOf(propId).contains("Per")
                        || String.valueOf(propId).contains("DiscountSalesValue") || String.valueOf(propId).contains("DiscountSalesVar")
                        || String.valueOf(propId).contains("totaldiscountper") || String.valueOf(propId).contains("totaldiscountchangepercentage"))) {
                    sheetCell.setCellStyle(style4);
                } else if ("Contract Units Value".equals(String.valueOf(levelValue)) || "Contract Units Variance".equals(String.valueOf(levelValue))
                        || (String.valueOf(propId).contains("ContractUnits") && !String.valueOf(propId).contains("Per"))) {
                    sheetCell.setCellStyle(style5);
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
