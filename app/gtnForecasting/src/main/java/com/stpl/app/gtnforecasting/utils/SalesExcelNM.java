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
 * @author Porchelvi.Gunasekara
 */
public class SalesExcelNM extends ExcelExport{
    
	protected boolean isAg;
    protected Map<String, String> formatter = null;
    protected final CellStyle style1 = this.workbook.createCellStyle();
    protected final CellStyle style2 = this.workbook.createCellStyle();
    protected final CellStyle style3 = this.workbook.createCellStyle();
    protected final CellStyle style4 = this.workbook.createCellStyle();
    protected final CellStyle style5 = this.workbook.createCellStyle();
    protected final CellStyle style6 = this.workbook.createCellStyle();
    protected DataFormat hssfDataFormat = this.workbook.createDataFormat();
    private static final Logger LOGGER = LoggerFactory.getLogger(SalesExcelNM.class);
    protected TableHolder tableHolder;
    private static final String UNIT_DECIMAL = "UNIT_DECIMAL";
    private static final String UNITTWODECIMAL = "UNITTWODECIMAL";
    private static final String PRODUCT_GROWTH_SUM = "PRODUCT_GROWTH_SUM";
    private static final String ACCOUNT_GROWTH_SUM = "ACCOUNT_GROWTH_SUM";
    
    public static final String COLUMN_FORMULA = "column formula{}";

    public SalesExcelNM(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, boolean hasTotalsRow, Map<String, String> formatter, boolean isAg) {
      
        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.tableHolder = tableHolder;
        this.isAg = isAg;
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

        Property propSalesExcel;
        Object propIdSalesExcel;
        Object valueSalesExcel = null;
        Cell sheetCellSalesExcel;
       style1.setDataFormat(hssfDataFormat.getFormat("$#,##0_);($#,##0)"));
       style2.setDataFormat(hssfDataFormat.getFormat("#,##0_);($#,##0)"));
       style3.setDataFormat(hssfDataFormat.getFormat("0.00%"));
       style4.setDataFormat(hssfDataFormat.getFormat("0.000%"));
       style5.setDataFormat(hssfDataFormat.getFormat("$#,##0.00"));
       style6.setDataFormat(hssfDataFormat.getFormat("$#,##0.00"));

        for (int col = 0; col < getPropIds().size(); col++) {

            propIdSalesExcel = getPropIds().get(col);
            propSalesExcel = getProperty(rootItemId, propIdSalesExcel);
            if (propSalesExcel != null) {
                valueSalesExcel = propSalesExcel.getValue();
            }
            sheetCellSalesExcel = sheetRow.createCell(col);

            final Short poiAlignment = getTableHolder().getCellAlignment(propIdSalesExcel);
            CellUtil.setAlignment(sheetCellSalesExcel, workbook, poiAlignment);

            if (this.formatter != null) {
                Double d = 0.0;
                try {
                    if (valueSalesExcel != null) {
                        d = dataConverter(valueSalesExcel);
                        
                        
                    }
                } catch (final NumberFormatException nfe) {
                	
                	sheetCellSalesExcel.setCellValue(createHelper.createRichTextString(valueSalesExcel.toString()));
                    continue;
                }

                sheetCellSalesExcel.setCellType(Cell.CELL_TYPE_NUMERIC);
                
                Double cellValue = d;
                cellValue = getCellValue(propIdSalesExcel, d, cellValue);
                sheetCellSalesExcel.setCellValue(cellValue);
                formatForCurrencyAndDecimal(propIdSalesExcel, sheetCellSalesExcel, rootItemId);

            } else {
                nonFormatPropSalesExcel(propSalesExcel, valueSalesExcel, sheetCellSalesExcel);
            }
        }
    }

    private void nonFormatPropSalesExcel(Property propSalesExcel, Object valueSalesExcel, Cell sheetCellSalesExcel) {
        if (propSalesExcel != null && valueSalesExcel != null) {
            nonFormatter(valueSalesExcel, propSalesExcel, sheetCellSalesExcel);
        }
    }

    private Double getCellValue(Object propId, Double d, Double cellValue) {
    	
    	Double getCellValue = cellValue;
    	
        if ((formatter.get(Constant.PERCENT_THREE_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(Constant.PERCENT_THREE_DECIMAL))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        if ((formatter.get(UNIT_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(UNIT_DECIMAL))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        if ((formatter.get(UNITTWODECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(UNITTWODECIMAL))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        if ((formatter.get(PRODUCT_GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(PRODUCT_GROWTH_SUM))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        if ((formatter.get(ACCOUNT_GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(ACCOUNT_GROWTH_SUM))) && (d > 0)) {
        	getCellValue = cellValue / NumericConstants.HUNDRED;
        }
        return getCellValue;
    }

    private Double dataConverter(Object valueSales) {
        Double salesdoubleValue;
        String strValueSales = String.valueOf(valueSales);
        if (strValueSales.contains("$") || strValueSales.contains(",") || strValueSales.contains("%")) {
            strValueSales = strValueSales.replace("$", "").replace(",", "");
            salesdoubleValue = Double.valueOf(strValueSales);
        } else {
            salesdoubleValue = DataTypeConverter.convertObjectToDouble(valueSales);
        }
        return salesdoubleValue;
    }

    private void formatForCurrencyAndDecimal(Object propId, Cell sheetCell, final Object rootItemId) throws FormulaParseException {
        if (currencyNoDecimalFormat(propId)) {
            sheetCell.setCellStyle(style1);
            currencyWithNoDecimal(rootItemId, sheetCell);
        } else if (formatter.get("unitNoDecimal") != null && String.valueOf(propId).endsWith(formatter.get("unitNoDecimal"))) {
            sheetCell.setCellStyle(style2);
            unitWithNoDecimal(rootItemId, sheetCell);
        }else if (formatter.get(UNITTWODECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(UNITTWODECIMAL))) {
            sheetCell.setCellStyle(style3);
            unitWithTwoDecimal(rootItemId, sheetCell);
        }else if (formatter.get(UNIT_DECIMAL) != null && String.valueOf(propId).endsWith(formatter.get(UNIT_DECIMAL))) {
            sheetCell.setCellStyle(style3);
            unitDecimal(rootItemId, sheetCell);
        }
        //Added Formula to PG_SUM, AG_SUM column  
        else if ((formatter.get(PRODUCT_GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(PRODUCT_GROWTH_SUM))) ||
                (formatter.get(ACCOUNT_GROWTH_SUM) != null && String.valueOf(propId).endsWith(formatter.get(ACCOUNT_GROWTH_SUM)))) {
            sheetCell.setCellStyle(style3);
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            growthSum(rootItemId, sheetCell);
        }
        //Added Formula to Child Count column
        else if (formatter.get("CHILD_COUNT") != null && String.valueOf(propId).endsWith(formatter.get("CHILD_COUNT"))) {
            sheet.setColumnHidden(sheetCell.getColumnIndex(), true);
            forChildCountColumn(rootItemId, sheetCell);
        }
    }

    private void forChildCountColumn(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if (((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)) {
            String formula = getFormula(sheetCell, rootItemId);
            LOGGER.info(COLUMN_FORMULA, getAppendedFormulaForPG_AG_Sum(formula.split(",")));
            sheetCell.setCellFormula(getAppendedFormulaForPG_AG_Sum(formula.split(",")));
        } else {
            // Setting 1 to children
            sheetCell.setCellValue(1);
        }
    }

    private void growthSum(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            String formula = getFormula(sheetCell, rootItemId);
            LOGGER.info(COLUMN_FORMULA , getAppendedFormulaForPG_AG_Sum(formula.split(",")));
            sheetCell.setCellFormula(getAppendedFormulaForPG_AG_Sum(formula.split(",")));
        }
    }

    private void unitDecimal(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            int columnIndex = isAg ? sheetCell.getColumnIndex() + 4 : sheetCell.getColumnIndex() + 2;
            String pgformula = getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 1) + "/" + getColumnLetter(sheetCell,columnIndex);
            LOGGER.info(COLUMN_FORMULA , pgformula);
            sheetCell.setCellFormula(pgformula);
        }
    }

    private void unitWithTwoDecimal(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            String agformula = getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 1) + "/" + getColumnLetter(sheetCell,sheetCell.getColumnIndex() + 2);
            LOGGER.info(COLUMN_FORMULA , agformula);
            sheetCell.setCellFormula(agformula);
        }
    }

    private void unitWithNoDecimal(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            String formula = getFormula(sheetCell, rootItemId);
            sheetCell.setCellStyle(style2);
            LOGGER.info(COLUMN_FORMULA , getAppendedFormula(formula.split(",")));
            sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
        }
    }

    private void currencyWithNoDecimal(final Object rootItemId, Cell sheetCell) throws FormulaParseException {
        if(((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)){
            String formula = getFormula(sheetCell, rootItemId);
            sheetCell.setCellStyle(style1);
            LOGGER.info(COLUMN_FORMULA , getAppendedFormula(formula.split(",")));
            sheetCell.setCellFormula(getAppendedFormula(formula.split(",")));
        }
    }
    public String getColumnLetter(Cell sheetCell,int columnIndex) {
        String columnLetter = CellReference.convertNumToColString(columnIndex);
        int rowNo = sheetCell.getRowIndex() + 1;
        return columnLetter+rowNo;
    }

	private boolean currencyNoDecimalFormat(Object propId) {
		return formatter.get("currencyNoDecimal")!=null && String.valueOf(propId).endsWith(formatter.get("currencyNoDecimal"));
	}

    private void nonFormatter(Object valueFormatt, Property propFormatt, Cell sheetCellFormatt) {
            if (!isNumeric(propFormatt.getType())) {
                if (java.util.Date.class.isAssignableFrom(propFormatt.getType())) {
                    sheetCellFormatt.setCellValue((Date) valueFormatt);
                } else {
                    sheetCellFormatt.setCellValue(createHelper.createRichTextString(valueFormatt.toString()));
                }
            } else {
                try {
                    // parse all numbers as double, the format will determine how they appear
                    final Double dFormatt = DataTypeConverter.convertObjectToDouble(valueFormatt);
                    sheetCellFormatt.setCellValue(dFormatt);
                    sheetCellFormatt.setCellType(Cell.CELL_TYPE_NUMERIC);
                } catch (final NumberFormatException nfe) {
                    
                    sheetCellFormatt.setCellValue(createHelper.createRichTextString(valueFormatt.toString()));
                }
        }
    }
    
     @Override
    protected void finalSheetFormat() {
        final FormulaEvaluator evaluatorSheet = workbook.getCreationHelper().createFormulaEvaluator();
        if (isHierarchical()) {
            /*
             * evaluateInCell() is equivalent to paste special -> value. The formula refers to cells.
             * in the other sheet we are going to delete. We sum in the other sheet because if we.
             * summed in the main sheet, we would double count. Subtotal with hidden rows is not yet.
             * implemented in POI
             */
            workbook.setActiveSheet(workbook.getSheetIndex(sheet));
            if (hierarchicalTotalsSheet != null) {
                workbook.removeSheetAt(workbook.getSheetIndex(hierarchicalTotalsSheet));
            }
        } else {
            evaluatorSheet.evaluateAll();
        }
        for (int colSheet = 0; colSheet < getPropIds(0).size(); colSheet++) {
            sheet.autoSizeColumn(colSheet);
        }
    }

    private String getFormula(Cell sheetCellFormula, final Object rootItemIdFormula) {
        String columnLetterFormula = CellReference.convertNumToColString(sheetCellFormula.getColumnIndex());
        final Collection<?> childrenFormula = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                .getChildren(rootItemIdFormula);
        int rowNoFormula = sheetCellFormula.getRowIndex() + 2;
        StringBuilder formulaBuilder = new StringBuilder();
        int k = 0;
        if (childrenFormula.size() == 1) {
            formulaBuilder.append(columnLetterFormula).append(rowNoFormula);
            return formulaBuilder.toString();
        }
        for (Object objectFormula : childrenFormula) {
            
            if (k == 0) {
                formulaBuilder.append(columnLetterFormula).append(rowNoFormula);
            } else if (k == childrenFormula.size() - 1) {
                return formulaBuilder.toString();
            }
            formulaBuilder.append(',');
            rowNoFormula = displayNodeValues(objectFormula, rowNoFormula) + 1;
            formulaBuilder.append(columnLetterFormula).append(rowNoFormula);
            k++;
        }
        return formulaBuilder.toString();
    }

    private int displayNodeValues(Object childrenNode, int rownumNode) {

        final Collection<?> childNode = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                .getChildren(childrenNode);
        int tempRowNumNode = rownumNode;
        if (childNode != null) {
            for (Object innerchildNode : childNode) {
                tempRowNumNode++;
                tempRowNumNode = displayNodeValues(innerchildNode, tempRowNumNode);
            }
        } 
        return tempRowNumNode;
    }
    public String getAppendedFormula(String[] value){
       boolean isappend = true;
        List<String> str=new ArrayList<>();
        String s="";
        for (int i = 0; i < value.length; i++) {
            s = s.concat(",").concat(value[i]);
            if ((i+1) % 30 == 0 && i != 0) {
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
                 formula = "SUM(".concat(string).concat(")");
             }else{
                 formula = formula.concat("+SUM(".concat(string).concat(")"));
             }
             isappend= false;
         }
         return formula;
    }
    // Created formula for PG_SUM and AG_SUM column 
    public String getAppendedFormulaForPG_AG_Sum(String[] value){
        boolean isappend = true;
         List<String> str=new ArrayList<>();
         String strVal="";
         for (int i = 0; i < value.length; i++) {
             strVal = strVal.concat(",").concat(value[i]);
             if ((i+1) % 30 == 0 && i != 0) {
                 str.add(strVal);
                 strVal="";
             }
         }
         if(!strVal.equals("")){
         str.add(strVal);
         }
         String formulaVal ="";
          for (int j = 0; j < str.size(); j++) {
              
              String string = str.get(j);
              string = string.replaceFirst(",", "");
              
              if (isappend) {
                  formulaVal = "SUM(".concat(string).concat(")");
              } else {
                  formulaVal = formulaVal.concat("+SUM(".concat(string).concat(")"));
              }
              isappend= false;
          }
          return formulaVal;
     }
    
}
