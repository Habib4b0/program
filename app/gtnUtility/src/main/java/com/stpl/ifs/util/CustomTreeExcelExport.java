/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.Table;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
/**
 *
 * @author Abhiram
 */
public class CustomTreeExcelExport extends ExcelExport {
    private static final Logger LOGGER = Logger.getLogger(CustomTreeExcelExport.class.getName());
    /**
     * At minimum, we need a Table to export. Everything else has default settings.
     *
     * @param table the table
     */
    public CustomTreeExcelExport(final Table table) {
        this(table, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param table     the table
     * @param sheetName the sheet name
     */
    public CustomTreeExcelExport(final Table table, final String sheetName) {
        this(table, sheetName, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param table       the table
     * @param sheetName   the sheet name
     * @param reportTitle the report title
     */
    public CustomTreeExcelExport(final Table table, final String sheetName, final String reportTitle) {
        this(table, sheetName, reportTitle, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param table          the table
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     */
    public CustomTreeExcelExport(final Table table, final String sheetName, final String reportTitle,
                       final String exportFileName) {
        super(table, sheetName, reportTitle, exportFileName);
    }

    

    /**
     * At minimum, we need a Table to export. Everything else has default settings.
     *
     * @param tableHolder the tableHolder
     */
    public CustomTreeExcelExport(final TableHolder tableHolder) {
        this(tableHolder, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder the tableHolder
     * @param sheetName   the sheet name
     */
    public CustomTreeExcelExport(final TableHolder tableHolder, final String sheetName) {
        this(tableHolder, sheetName, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder the tableHolder
     * @param sheetName   the sheet name
     * @param reportTitle the report title
     */
    public CustomTreeExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle) {
        this(tableHolder, sheetName, reportTitle, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder    the tableHolder
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     */
    public CustomTreeExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
                       final String exportFileName) {
        super(tableHolder, sheetName, reportTitle, exportFileName);
    }

    int rowIndex = 1;
    /**
     * Used by addHierarchicalDataRows() to implement the recursive calls.
     *
     * @param sheetToAddTo
     * @param rootItemId the root item id
     * @param row        the row
     * @return the int
     */
    @Override
    protected int addDataRowRecursively(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
        int numberAdded = 0;
        int localRow = row;
        addDataRow(sheetToAddTo, rootItemId, row);
        numberAdded++;
        if (((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)) {
            final Collection<?> children = ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                    .getChildren(rootItemId);
            for (final Object child : children) {
                localRow++;
                int value = rowIndex;
                int count = addDataRowRecursively(sheetToAddTo, child, localRow);
                numberAdded = numberAdded + count;
                if (count > 1) {
                    sheet.groupRow(value + NumericConstants.TWO, rowIndex + 1);
                    sheet.setRowGroupCollapsed(value + NumericConstants.TWO, true);
                }
            }
        }
        return numberAdded;
    }
    
    /**
     * This method is ultimately used by either addDataRows() or addHierarchicalDataRows() to
     * actually add the data to the Sheet.
     * @param sheetToAddTo
     * @param rootItemId the root item id
     * @param row the row
     */
    @Override
    protected void addDataRow(final Sheet sheetToAddTo, final Object rootItemId, final int row){
        

        HSSFDataFormat df = (HSSFDataFormat) workbook.createDataFormat();
        HSSFCellStyle stylepercentFormat = (HSSFCellStyle) workbook.createCellStyle();
        stylepercentFormat.setDataFormat(df.getFormat("#0.0%"));
                
        HSSFCellStyle styleUnitFormat = (HSSFCellStyle) workbook.createCellStyle();
        styleUnitFormat.setDataFormat(df.getFormat("#,##0.0"));
        
        HSSFCellStyle styleCurrencyFormat = (HSSFCellStyle)workbook.createCellStyle();
        styleCurrencyFormat.setDataFormat((short) NumericConstants.SIX); //6 = "($#,##0);[Red]($#,##0)"
        
        rowIndex++;
        final Row sheetRow = sheet.createRow(rowIndex);
        Property prop;
        Object propId;
        Object value;
        Cell sheetCell;
        for (int col = 0; col < getPropIds().size(); col++) {
            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            if (null == prop) {
                value = null;
            } else {
                value = prop.getValue();              
            }
            sheetCell = sheetRow.createCell(col);
            final CellStyle cs = getCellStyle(rootItemId, row, col, false);
            sheetCell.setCellStyle(cs);
            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(sheetCell, workbook, poiAlignment);
            
            if (null != value) {
                if (!isNumeric(prop.getType())) {
                    if (java.util.Date.class.isAssignableFrom(prop.getType())) {
                        sheetCell.setCellValue((Date) value);
                    } else {
                           if(String.valueOf(propId).startsWith("check") ||String.valueOf(propId).equals("graphComponent") || String.valueOf(propId).equals("revisionDate")){
                           if( String.valueOf(propId).equals("revisionDate")){
                               Button revisiondate= (Button)value; 
                               String date="null".equals(revisiondate.getCaption())? "" : revisiondate.getCaption();
                               sheetCell.setCellValue(createHelper.createRichTextString(date));
                    }
                        } 
                           else if(String.valueOf(propId).equals("revisionDateComponent")){
                               Button revisiondate= (Button)value; 
                               sheetCell.setCellValue(createHelper.createRichTextString(revisiondate.getCaption()));
                    } 

                           else{
                               String tempValue="null".equals(value.toString())? "" : value.toString();
                        sheetCell.setCellValue(createHelper.createRichTextString(tempValue));
                           }
                    }
                }  else {
                        try {
                        Double d = Double.parseDouble(value.toString());
                        String str = String.valueOf(propId);
                        if (str.endsWith("Rate")|| str.endsWith("Growth") || str.endsWith("Cap")||str.endsWith("Percent")) {
                                d = d / NumericConstants.HUNDRED;
                                sheetCell.setCellStyle(stylepercentFormat);
                            
                            }
                        else if (str.endsWith("Amount") || str.endsWith("Sales")||str.endsWith("Dollar")) {
                           
                            sheetCell.setCellStyle(styleCurrencyFormat);
                            
                        }
                         else if(str.endsWith("Units")){
                            
                                sheetCell.setCellStyle(styleUnitFormat);
                            }
                     
                                sheetCell.setCellValue(d);

                        } catch (final NumberFormatException nfe) {
                            LOGGER.warning("NumberFormatException parsing a numeric value: " + nfe);
                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }
                    }
                }
            }
        }


}
