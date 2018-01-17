/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
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
 * @author maheshj
 */
public class ExportToExcel extends ExcelExport {

    protected Map<String, String> formatter = null;
    protected final CellStyle style1 = this.workbook.createCellStyle();
    protected final CellStyle style2 = this.workbook.createCellStyle();
    protected final CellStyle style3 = this.workbook.createCellStyle();
    protected final CellStyle style4 = this.workbook.createCellStyle();
    protected final CellStyle style5 = this.workbook.createCellStyle();
    protected final CellStyle style6 = this.workbook.createCellStyle();
    protected Object property;

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
        Object levelValue;
        for (int col = 0; col < getPropIds().size(); col++) {

            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            levelValue = getProperty(rootItemId, property).getValue();
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
                    if (null != value && !String.valueOf(value).equalsIgnoreCase(StringUtils.EMPTY) && !String.valueOf(value).equalsIgnoreCase(Constant.NULL)) {
                        d = Double.parseDouble(value.toString().replace("$", StringUtils.EMPTY).replace(",", StringUtils.EMPTY).replace(Constant.PERCENT, StringUtils.EMPTY));
                        sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                        if (!String.valueOf(levelValue).contains(CommonUtils.COL_PERCENTAGE) && !String.valueOf(propId).endsWith("Per")
                                && ((formatter.get(Constant.PERCENT_TWO_DECIMAL) != null
                                && (String.valueOf(levelValue).contains(formatter.get(Constant.PERCENT_TWO_DECIMAL))
                                || String.valueOf(propId).contains(formatter.get(Constant.PERCENT_TWO_DECIMAL))
                                || String.valueOf(propId).contains("Per")) || String.valueOf(propId).startsWith("Per"))
                                || (formatter.get(Constant.PERCENT_ONE_DECIMAL) != null
                                && (String.valueOf(levelValue).contains(formatter.get(Constant.PERCENT_ONE_DECIMAL))
                                || String.valueOf(propId).contains(formatter.get(Constant.PERCENT_ONE_DECIMAL))
                                || String.valueOf(propId).contains("DiscountSalesValue") || String.valueOf(propId).contains("DiscountSalesVar"))
                                || String.valueOf(propId).contains("totaldiscountper") || String.valueOf(propId).contains(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE)))) {
                            sheetCell.setCellValue(d / NumericConstants.HUNDRED);
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
                if (formatter.get(Constant.CURRENCY_TWO_DECIMAL) != null && ((String.valueOf(levelValue).contains(formatter.get(Constant.CURRENCY_TWO_DECIMAL)) && !String.valueOf(levelValue).contains(Constant.PERCENT)
                        && !String.valueOf(propId).contains(Constant.CONTRACT_SALES_WAC_VAR_PER) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PRIOR_PERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PROJECTEDPERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE))
                        || (String.valueOf(propId).contains(formatter.get(Constant.CURRENCY_TWO_DECIMAL)) && !String.valueOf(propId).contains(Constant.DISCOUNT_SMALL) && !String.valueOf(propId).contains(Constant.CONTRACT_SALES_WAC_VAR_PER) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PRIOR_PERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PROJECTEDPERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE))
                        
                        || "Discount $ Value".equals(String.valueOf(levelValue)) || "Discount $ Variance".equals(String.valueOf(levelValue))
                        || String.valueOf(propId).contains("GTSValue") || (String.valueOf(propId).contains("GTSVariance") && !String.valueOf(propId).contains("Per"))
                        || String.valueOf(propId).contains("DiscountAmountValue") || String.valueOf(propId).contains("DiscountAmountVar"))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get(Constant.ONE_DECIMAL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.ONE_DECIMAL))
                        || String.valueOf(propId).contains(formatter.get(Constant.ONE_DECIMAL)))) {
                    sheetCell.setCellStyle(style3);
                } else if (formatter.get(Constant.CURRENCY_DECIMAL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.CURRENCY_DECIMAL))
                        || (String.valueOf(propId).contains(formatter.get(Constant.CURRENCY_DECIMAL)) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PRIOR_PERCENTAGE)
                        && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PROJECTEDPERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE)))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get(Constant.PERCENT_TWO_DECIMAL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.PERCENT_TWO_DECIMAL))
                        || String.valueOf(propId).contains(formatter.get(Constant.PERCENT_TWO_DECIMAL))
                        || String.valueOf(propId).contains("Per"))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get(Constant.DISCOUNT_DOL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.DISCOUNT_DOL))
                        || (String.valueOf(propId).contains(formatter.get(Constant.DISCOUNT_DOL)) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PRIOR_PERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_PER_PROJECTEDPERCENTAGE) && !String.valueOf(propId).equals(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE)))) {
                    sheetCell.setCellStyle(style1);
                } else if (formatter.get(Constant.PERCENT2_DECIMAL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.PERCENT2_DECIMAL))
                        || String.valueOf(propId).contains(formatter.get(Constant.PERCENT2_DECIMAL)))) {
                    sheetCell.setCellStyle(style2);
                } else if (formatter.get(Constant.PERCENT_ONE_DECIMAL) != null && (String.valueOf(levelValue).contains(formatter.get(Constant.PERCENT_ONE_DECIMAL))
                        || String.valueOf(propId).contains(formatter.get(Constant.PERCENT_ONE_DECIMAL)) || String.valueOf(propId).contains("Per")
                        || String.valueOf(propId).contains("DiscountSalesValue") || String.valueOf(propId).contains("DiscountSalesVar")
                        || String.valueOf(propId).contains("totaldiscountper") || String.valueOf(propId).contains(Constant.TOTAL_DISCOUNT_CHANGE_PERCENTAGE))) {
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
