/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.CellUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;

/**
 *
 * @author maheshj
 */
public class CustomExcelNM extends ExcelExport {

    protected Map<String, String> formatter = null;
    protected boolean isRate;
    protected boolean isRPU;
    protected boolean isCustom;
    protected final CellStyle style1 = this.workbook.createCellStyle();
    protected final CellStyle style2 = this.workbook.createCellStyle();
    protected final CellStyle style3 = this.workbook.createCellStyle();
    protected final CellStyle style4 = this.workbook.createCellStyle();
    protected final CellStyle style5 = this.workbook.createCellStyle();
    protected final CellStyle style6 = this.workbook.createCellStyle();
    protected final CellStyle style7 = this.workbook.createCellStyle();
    protected DataFormat hssfDataFormat = this.workbook.createDataFormat();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExcelNM.class);
    private final TableHolder tableHolder;
    public static final String CURRENCY_TWO_DECIMAL = "currencyTwoDecimal";
    public static final String AMOUNT_TWO_DECIMAL = "amountTwoDecimal";
    public static final String GROWTH = "Growth";
    public static final String GROWTH_SUM = "GrowthSum";
    public static final String CHILD_COUNT = "ChildCount";
    
    
    public CustomExcelNM(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter,boolean isRate,boolean isRPU,boolean isCustom) {

        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.tableHolder = tableHolder;
        this.formatter = formatter;
        this.isRate = isRate;
        this.isRPU = isRPU;
        this.isCustom = isCustom;
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
        Object value = null;
        Cell sheetCell;
       style1.setDataFormat(hssfDataFormat.getFormat("$#,##0_);($#,##0)"));
       style2.setDataFormat(hssfDataFormat.getFormat("#,##0"));
       style3.setDataFormat(hssfDataFormat.getFormat("0.00%"));
       style4.setDataFormat(hssfDataFormat.getFormat("0.000%"));
       style5.setDataFormat(hssfDataFormat.getFormat("$#,##0.00"));
       style6.setDataFormat(hssfDataFormat.getFormat("$#,##0.00"));

        for (int col = 0; col < getPropIds().size(); col++) {

            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            if (prop != null) {
                value = prop.getValue();
            }
            sheetCell = sheetRow.createCell(col);

            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(sheetCell, workbook, poiAlignment);

            if (this.formatter != null) {
                Double d = 0.0;
                try {
                    if (value != null) {
                        d = dataConverter(value);
                    }
                } catch (final NumberFormatException nfe) {
                    sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                    continue;
                }

                sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                
                Double cellValue = d;
                cellValue = getCellValue(propId, d, cellValue);
                sheetCell.setCellValue(cellValue);
                formatForCurrency(propId, sheetCell, rootItemId);

            } else {
                nonFormatter(value, prop, sheetCell);
            }
        }
    }

    private void formatForCurrency(Object propId, Cell sheetCell, final Object rootItemId) throws FormulaParseException {
        if(!isCustom){
            formatForCurrencyAndDecimal(propId, sheetCell, rootItemId);
        }else{
            formatForCurrencyAndDecimalCustom(propId, sheetCell, rootItemId);
        }
    }

    private Double getCellValue(Object propId, Double d, Double cellValue) {
    	
    	Double getCellValue = (double) 0;
    	
        if ((formatter.get(Constant.PERCENT_THREE_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_THREE_DECIMAL))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        else if ((formatter.get(GROWTH) != null && String.valueOf(propId).endsWith(formatter.get(GROWTH))) && (d > 0)) {
        	
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        else if ((formatter.get(GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(GROWTH_SUM))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        return getCellValue;
    }

    private Double dataConverter(Object value) throws NumberFormatException {
        Double d;
        String str = String.valueOf(value);
        if (str.contains("$") || str.contains(",") || str.contains("%")) {
            str = str.replace("$", "").replace(",", "");
            d = Double.valueOf(str);
        } else {
            d = DataTypeConverter.convertObjectToDouble(value);
        }
        return d;
    }

    private void formatForCurrencyAndDecimal(Object propId, Cell sheetCell, final Object rootItemId) throws FormulaParseException {
         if (formatter.get(Constant.PERCENT_THREE_DECIMAL)!=null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_THREE_DECIMAL))) {
            sheetCell.setCellStyle(style4);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
                String value = ",\"0.000%\")";
                int columnIndex = isRate && isRPU ? sheetCell.getColumnIndex() + 2 : sheetCell.getColumnIndex() + 1;
                String form = getColumnLetter(sheetCell,columnIndex)+"/"+getColumnLetter(sheetCell,columnIndex + 1);
                sheetCell.setCellFormula("IF("+getColumnLetter(sheetCell,columnIndex + 1)+"<>0,"+form+value);
                sheetCell.setCellStyle(style4);
            }
        } else if (formatter.get(CURRENCY_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(CURRENCY_TWO_DECIMAL))) {
            sheetCell.setCellStyle(style5);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
                String value = ",\"0.00$\")";
                int columnIndex = isRate && isRPU ? sheetCell.getColumnIndex() + 3 : sheetCell.getColumnIndex() + 2;
                String form = getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 1)+"/"+getColumnLetter(sheetCell,columnIndex);
                sheetCell.setCellFormula("IF("+getColumnLetter(sheetCell,columnIndex)+"<>0,"+form+value);
                sheetCell.setCellStyle(style5);
            }
        } else if (formatter.get(AMOUNT_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(AMOUNT_TWO_DECIMAL))) {
            sheetCell.setCellStyle(style6);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
                String formula = getFormula(sheetCell, rootItemId,sheetCell.getColumnIndex());
                sheetCell.setCellStyle(style6);
                sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
            }
        } else if (formatter.get("sales") != null && String.valueOf(propId).endsWith(formatter.get("sales"))) {
            sheetCell.setCellStyle(style4);
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
                String formula = getFormula(sheetCell, rootItemId,sheetCell.getColumnIndex());
                sheetCell.setCellStyle(style4);
                sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
            }
        } else if (formatter.get("units") != null && String.valueOf(propId).endsWith(formatter.get("units"))) {
            sheetCell.setCellStyle(style4);
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
                String formula = getFormula(sheetCell, rootItemId,sheetCell.getColumnIndex());
                sheetCell.setCellStyle(style4);
                sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
            }
        } else if (formatter.get(GROWTH) != null && String.valueOf(propId).endsWith(formatter.get(GROWTH))) {
            sheetCell.setCellStyle(style4);
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            	String formula = getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 1) + "/" + getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 2);
                sheetCell.setCellStyle(style4);
                sheetCell.setCellFormula(formula);
            }
        }
        //Added Formula to Growth_SUM column  
        else if (formatter.get(GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(GROWTH_SUM))) {
            sheetCell.setCellStyle(style4);
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            	String formula = getFormula(sheetCell, rootItemId,sheetCell.getColumnIndex());
            	sheetCell.setCellStyle(style4);
                sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
            }
        }
       //Added Formula to Child Count column
        else if (formatter.get(CHILD_COUNT) != null && String.valueOf(propId).endsWith(formatter.get(CHILD_COUNT))) {
        	sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            	String formula = getFormula(sheetCell, rootItemId,sheetCell.getColumnIndex());
                sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
            }
        	else
        	{
        		// Setting 1 to children
        		sheetCell.setCellValue(1);
        	}
        }
    }
    private void formatForCurrencyAndDecimalCustom(Object propId, Cell sheetCell, final Object rootItemId) throws FormulaParseException {
        if (formatter.get(Constant.PERCENT_THREE_DECIMAL)!=null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_THREE_DECIMAL))) {
            sheetCell.setCellStyle(style1);
        } else if (formatter.get(CURRENCY_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(CURRENCY_TWO_DECIMAL))) {
            sheetCell.setCellStyle(style4);
        } else if (formatter.get(AMOUNT_TWO_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(AMOUNT_TWO_DECIMAL))) {
            sheetCell.setCellStyle(style6);
        } else if (formatter.get(GROWTH) != null && String.valueOf(propId).endsWith(formatter.get(GROWTH))) {
            sheetCell.setCellStyle(style4);
        }

    }

    private void nonFormatter(Object value, Property prop, Cell sheetCell) {
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
                    final Double d = DataTypeConverter.convertObjectToDouble(value);
                    sheetCell.setCellValue(d);
                    sheetCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                } catch (final NumberFormatException nfe) {
                    
                    sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                }
            }
        }
    }
    
     @Override
    protected void finalSheetFormat() {
        final FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        if (isHierarchical()) {
            /*
             * evaluateInCell() is equivalent to paste special -> value. The formula refers to cells
             * in the other sheet we are going to delete. We sum in the other sheet because if we
             * summed in the main sheet, we would double count. Subtotal with hidden rows is not yet
             * implemented in POI.
             */
            workbook.setActiveSheet(workbook.getSheetIndex(sheet));
            if (hierarchicalTotalsSheet != null) {
                workbook.removeSheetAt(workbook.getSheetIndex(hierarchicalTotalsSheet));
            }
        } else {
            evaluator.evaluateAll();
        }
        for (int col = 0; col < getPropIds(0).size(); col++) {
            sheet.autoSizeColumn(col);
        }
    }

    private String getFormula(Cell sheetCell, final Object rootItemId,int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        final Collection<?> children = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                .getChildren(rootItemId);
        int rowNo = sheetCell.getRowIndex() + 2;
        StringBuilder formula = new StringBuilder();
        int i = 0;
        if (children.size() == 1) {
            formula.append(columnLetter).append(rowNo);
            return formula.toString();
        }
        for (Object object : children) {
            
            if (i == 0) {
                formula.append(columnLetter).append(rowNo);
            } else if (i == children.size() - 1) {
                return formula.toString();
            }
            formula.append(',');
            rowNo = displayNodeValues(object, rowNo) + 1;
            formula.append(columnLetter).append(rowNo);
            i++;
        }
        return formula.toString();
    }
    private String getFormulaExample(Cell sheetCell, final Object rootItemId,int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        final Collection<?> children = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                .getChildren(rootItemId);
        int rowNo = sheetCell.getRowIndex() + 2;
        StringBuilder formula = new StringBuilder();
        int i = 0;
        if (children.size() == 1) {
            formula.append(columnLetter).append(rowNo);
            return formula.toString();
        }
        for (Object object : children) {
            
            if (i == 0) {
                formula.append(columnLetter).append(rowNo);
            } else if (i == children.size() - 1) {
                return formula.toString();
            }
            formula.append(',');
            rowNo = displayNodeValues(object, rowNo) + 1;
            formula.append(columnLetter).append(rowNo);
            i++;
        }
        return formula.toString();
    }

    private int displayNodeValues(Object children, int rownum) {

        final Collection<?> child = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                .getChildren(children);
        int tempRowNum = rownum;
        if (child != null) {
            for (Object innerchild : child) {
                tempRowNum++;
                tempRowNum = displayNodeValues(innerchild, tempRowNum);
            }
        }
        return tempRowNum;
    }

    public String getColumnLetter(Cell sheetCell,int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        int rowNo = sheetCell.getRowIndex() + 1;
        return columnLetter+rowNo;
    }

    public String getAppendedFormula(String[] value) {
        boolean isappend = true;
        List<String> str = new ArrayList<>();
        String s = "";
        for (int i = 0; i < value.length; i++) {
            s = s + "," + value[i];
            if ((i + 1) % 30 == 0 && i != 0) {
                str.add(s);
                s="";
            }
        }
        if(!s.equals("")){
        str.add(s);
        }
        String formula ="";
         for (int j = 0; j < str.size(); j++) {
             
             String string = str.get(j);
             string = string.replaceFirst(",", "");
             
             if(isappend){
                 formula = "SUM("+string+")";
             }else{
                 formula += "+SUM("+string+")";
             }
             isappend= false;
             
         }
         return formula;
    }

}
