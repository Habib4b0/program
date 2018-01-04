/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.stpl.addons.tableexport.TableExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Item;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.ObjectProperty;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.Table;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;

/**
 *
 * @author Abhiram
 */
public class CommonExcelExport extends TableExport{
    
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -8404407996727936497L;
    private static final Logger LOGGER = Logger.getLogger(CommonExcelExport.class.getName());

    /**
     * The name of the sheet in the workbook the table contents will be written to.
     */
    protected String sheetName;

    /**
     * The title of the "report" of the table contents.
     */
    protected String reportTitle;

    /**
     * The filename of the workbook that will be sent to the user.
     */
    protected String exportFileName;

    /**
     * Flag indicating whether we will add a totals row to the Table. A totals row in the Table is
     * typically implemented as a footer and therefore is not part of the data source.
     */
    protected boolean displayTotals;

    /**
     * Flag indicating whether the first column should be treated as row headers. They will then be
     * formatted either like the column headers or a special row headers CellStyle can be specified.
     */
    protected boolean rowHeaders = false;

    /**
     * Flag indicating whether we should use table.formatPropertyValue() as the cell value instead
     * of the property value using the specified data formats.
     */
    protected boolean useTableFormatPropertyValue = false;

    /**
     * The workbook that contains the sheet containing the report with the table contents.
     */
    protected final Workbook workbook;

    /**
     * The Sheet object that will contain the table contents report.
     */
    protected Sheet sheet;
    protected Sheet hierarchicalTotalsSheet = null;

    /**
     * The POI cell creation helper.
     */
    protected CreationHelper createHelper;
    protected DataFormat dataFormat;

    /**
     * Various styles that are used in report generation. These can be set by the user if the
     * default style is not desired to be used.
     */
    protected CellStyle dateCellStyle, doubleCellStyle, integerCellStyle, totalsDoubleCellStyle,
            totalsIntegerCellStyle, columnHeaderCellStyle, titleCellStyle;
    protected Short dateDataFormat, doubleDataFormat, integerDataFormat;
    protected Map<Short, CellStyle> dataFormatCellStylesMap = new HashMap<Short, CellStyle>();

    /**
     * The default row header style is null and, if row headers are specified with
     * setRowHeaders(true), then the column headers style is used. setRowHeaderStyle() allows the
     * user to specify a different row header style.
     */
    protected CellStyle rowHeaderCellStyle = null;

    /**
     * The totals row.
     */
    protected Row titleRow, headerRow, totalsRow;
    protected Row hierarchicalTotalsRow;
    // This let's the user specify the data format of the property in case the formatting of the property
    // will not be properly identified by the class of the property. In this case, the specified format is
    // used.  However, all other cell stylings will be those of the
    protected Map<Object, String> propertyExcelFormatMap = new HashMap<Object, String>();
    private List<String> percentList;
    private List<String> dollarList;
    private List<String> unitsList;
    private boolean appendRowWise;
    private Object beanObject;
    private String groupValue;

    public Object getBeanObject() {
        return beanObject;
    }

    public void setBeanObject(Object beanObject) {
        this.beanObject = beanObject;
    }

    public void setAppendRowWise(boolean appendRowWise) {
        this.appendRowWise = appendRowWise;
    }

    /**
     * At minimum, we need a Table to export. Everything else has default settings.
     *
     * @param table the table
     */
    public CommonExcelExport(final Table table) {
        this(table, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param table     the table
     * @param sheetName the sheet name
     */
    public CommonExcelExport(final Table table, final String sheetName) {
        this(table, sheetName, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param table       the table
     * @param sheetName   the sheet name
     * @param reportTitle the report title
     */
    public CommonExcelExport(final Table table, final String sheetName, final String reportTitle) {
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
    public CommonExcelExport(final Table table, final String sheetName, final String reportTitle,
                       final String exportFileName) {
        this(table, sheetName, reportTitle, exportFileName, null);
    }
    /**
     * Instantiates a new TableExport class.
     *
     * @param table          the table
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     */
    public CommonExcelExport(final Table table, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist) {
        this(table, sheetName, reportTitle, exportFileName,exportlist, false);
    }
    /**
     * Instantiates a new TableExport class.
     *
     * @param table          the table
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     * @param appendRowWise
     */
    public CommonExcelExport(final Table table, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist, boolean appendRowWise) {
        this(table, sheetName, reportTitle, exportFileName,exportlist, appendRowWise, true);
    }
    /**
     * Instantiates a new TableExport class. This is the final constructor that all other
     * constructors end up calling. If the other constructors were called then they pass in the
     * default parameters.
     *
     * @param table          the table
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     * @param appendRowWise
     * @param hasTotalsRow   flag indicating whether we should create a totals row
     */
    public CommonExcelExport(final Table table, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist, boolean appendRowWise, final boolean hasTotalsRow) {
        this(table, new HSSFWorkbook(), sheetName, reportTitle, exportFileName,exportlist, appendRowWise, hasTotalsRow);
    }
     /**
     * Instantiates a new TableExport class. This is the final constructor that all other
     * constructors end up calling. If the other constructors were called then they pass in the
     * default parameters.
     *
     * @param table          the table
     * @param wkbk
     * @param shtName
     * @param rptTitle
     * @param xptFileName
     * @param exportlist
     * @param appendRowWise
     * @param hasTotalsRow   flag indicating whether we should create a totals row
     */
    public CommonExcelExport(final Table table, final Workbook wkbk, final String shtName, final String rptTitle,
                       final String xptFileName, List<List> exportlist, boolean appendRowWise, final boolean hasTotalsRow) {
        super(table);
        this.workbook = wkbk;
        init(shtName, rptTitle, xptFileName,exportlist, appendRowWise, hasTotalsRow);
    }

    /**
     * At minimum, we need a Table to export. Everything else has default settings.
     *
     * @param tableHolder the tableHolder
     */
    public CommonExcelExport(final TableHolder tableHolder) {
        this(tableHolder, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder the tableHolder
     * @param sheetName   the sheet name
     */
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName) {
        this(tableHolder, sheetName, null);
    }

    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder the tableHolder
     * @param sheetName   the sheet name
     * @param reportTitle the report title
     */
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle) {
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
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
                       final String exportFileName) {
        this(tableHolder, sheetName, reportTitle, exportFileName, null);
    }
    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder    the tableHolder
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     */
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist) {
        this(tableHolder, sheetName, reportTitle, exportFileName,exportlist, false);
    }
    /**
     * Instantiates a new TableExport class.
     *
     * @param tableHolder    the tableHolder
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     * @param appendRowWise
     */
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist, boolean appendRowWise) {
        this(tableHolder, sheetName, reportTitle, exportFileName,exportlist,appendRowWise, true);
    }
    /**
     * Instantiates a new TableExport class. This is the final constructor that all other
     * constructors end up calling. If the other constructors were called then they pass in the
     * default parameters.
     *
     * @param tableHolder    the tableHolder
     * @param sheetName      the sheet name
     * @param reportTitle    the report title
     * @param exportFileName the export file name
     * @param exportlist
     * @param appendRowWise
     * @param hasTotalsRow   flag indicating whether we should create a totals row
     */
    public CommonExcelExport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
                       final String exportFileName, List<List> exportlist, boolean appendRowWise, final boolean hasTotalsRow) {
        this(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName,exportlist, appendRowWise, hasTotalsRow);
    }
    /**
     * Instantiates a new TableExport class. This is the final constructor that all other
     * constructors end up calling. If the other constructors were called then they pass in the
     * default parameters.
     *
     * @param tableHolder    the tableHolder
     * @param wkbk
     * @param shtName
     * @param rptTitle
     * @param xptFileName
     * @param exportlist
     * @param appendRowWise
     * @param hasTotalsRow   flag indicating whether we should create a totals row
     */
    public CommonExcelExport(final TableHolder tableHolder, final Workbook wkbk, final String shtName,
                       final String rptTitle, final String xptFileName, List<List> exportlist, boolean appendRowWise, final boolean hasTotalsRow) {
        super(tableHolder);
        this.workbook = wkbk;
        init(shtName, rptTitle, xptFileName,exportlist, appendRowWise, hasTotalsRow);
    }

    private void init(final String shtName, final String rptTitle, final String xptFileName, List<List> exportlist, boolean appendRowWise,
                      final boolean hasTotalsRow) {
        int exportlistsize = exportlist.size();
        if (exportlistsize > 0) {
            dollarList = exportlist.get(0);
        }
        if (exportlistsize > 1) {
            unitsList = exportlist.get(1);
        }
        if (exportlistsize > NumericConstants.TWO) {
            percentList = exportlist.get(NumericConstants.TWO);
        }
        setAppendRowWise(appendRowWise);
        if ((null == shtName) || ("".equals(shtName))) {
            this.sheetName = "Table Export";
        } else {
            this.sheetName = shtName;
        }
        if (null == rptTitle) {
            this.reportTitle = "";
        } else {
            this.reportTitle = rptTitle;
        }
        if ((null == xptFileName) || ("".equals(xptFileName))) {
            this.exportFileName = "Table-Export.xls";
        } else {
            this.exportFileName = xptFileName;
        }
        this.displayTotals = hasTotalsRow;

        this.sheet = this.workbook.createSheet(this.sheetName);
        this.createHelper = this.workbook.getCreationHelper();
        this.dataFormat = this.workbook.createDataFormat();
        this.dateDataFormat = defaultDateDataFormat(this.workbook);
        this.doubleDataFormat = defaultDoubleDataFormat(this.workbook);
        this.integerDataFormat = defaultIntegerDataFormat(this.workbook);

        this.doubleCellStyle = defaultDataCellStyle(this.workbook);
        this.doubleCellStyle.setDataFormat(doubleDataFormat);
        this.dataFormatCellStylesMap.put(doubleDataFormat, doubleCellStyle);

        this.integerCellStyle = defaultDataCellStyle(this.workbook);
        this.integerCellStyle.setDataFormat(integerDataFormat);
        this.dataFormatCellStylesMap.put(integerDataFormat, integerCellStyle);

        this.dateCellStyle = defaultDataCellStyle(this.workbook);
        this.dateCellStyle.setDataFormat(this.dateDataFormat);
        this.dataFormatCellStylesMap.put(this.dateDataFormat, this.dateCellStyle);

        this.totalsDoubleCellStyle = defaultTotalsDoubleCellStyle(this.workbook);
        this.totalsIntegerCellStyle = defaultTotalsIntegerCellStyle(this.workbook);
        this.columnHeaderCellStyle = defaultHeaderCellStyle(this.workbook);
        this.titleCellStyle = defaultTitleCellStyle(this.workbook);
    }

    /*
     * Set a new table to be exported in another workbook tab / sheet.
     */
    public void setNextTable(final Table table, final String sheetName) {
        setTable(table);
        sheet = workbook.createSheet(sheetName);
    }

    public void setNextTableHolder(final TableHolder tableHolder, final String sheetName) {
        setTableHolder(tableHolder);
        sheet = workbook.createSheet(sheetName);
    }

    /*
     * This will exclude columns from the export that are not visible due to them being collapsed.
     * This should be called before convertTable() is called.
     */
    public void excludeCollapsedColumns() {
        final Iterator<Object> iterator = getPropIds().iterator();
        while (iterator.hasNext()) {
            final Object propId = iterator.next();
            if (getTableHolder().isColumnCollapsed(propId)) {
                iterator.remove();
            }
        }
    }

    /**
     * Creates the workbook containing the exported table data, without exporting it to the user.
     */
    @Override
    public void convertTable() {
        final int startRow;
        // initial setup
        initialSheetSetup();

        // add title row
        startRow = addTitleRow();
        int row = startRow;

        // add header row
        addHeaderRow(row);
        row++;

        // add data rows
        if (isHierarchical()) {
            row = addHierarchicalDataRows(sheet, row);
        } else {
            row = addDataRows(sheet, row);
        }

        // add totals row
        if (displayTotals) {
            addTotalsRow(row, startRow);
        }

        // final sheet format before export
        finalSheetFormat();
    }

    /**
     * Export the workbook to the end-user.
     * <p/>
     * Code obtained from: http://vaadin.com/forum/-/message_boards/view_message/159583
     *
     * @return true, if successful
     */
    @Override
    public boolean sendConverted() {
        File tempFile = null;
        FileOutputStream fileOut = null;
        try {
            tempFile = File.createTempFile("tmp", ".xls");
            fileOut = new FileOutputStream(tempFile);
            workbook.write(fileOut);
            if (null == mimeType) {
                setMimeType(EXCEL_MIME_TYPE);
            }
            final boolean success = super.sendConvertedFileToUser(getTableHolder().getUI(), tempFile, exportFileName);
            return success;
        } catch (final IOException e) {
            LOGGER.warning("Converting to XLS failed with IOException " + e);
            return false;
        } finally {
            tempFile.deleteOnExit();
            try {
                fileOut.close();
            } catch (final IOException e) {
                LOGGER.warning("IO Exception"+e);
            }
        }
    }

    /**
     * Initial sheet setup. Override this method to specifically change initial, sheet-wide,
     * settings.
     */
    protected void initialSheetSetup() {
        final PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        if ((isHierarchical()) && (displayTotals)) {
            hierarchicalTotalsSheet = workbook.createSheet("tempHts");
        }
    }

    /**
     * Adds the title row. Override this method to change title-related aspects of the workbook.
     * Alternately, the title Row Object is accessible via getTitleRow() after report creation. To
     * change title text use setReportTitle(). To change title CellStyle use setTitleStyle().
     *
     * @return the int
     */
    protected int addTitleRow() {
        if ((null == reportTitle) || ("".equals(reportTitle))) {
            return 0;
        }
        titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(NumericConstants.FORTY_FIVE);
        final Cell titleCell;
        final CellRangeAddress cra;
        if (rowHeaders) {
            titleCell = titleRow.createCell(1);
            cra = new CellRangeAddress(0, 0, 1, getPropIds().size() - 1);
            sheet.addMergedRegion(cra);
        } else {
            titleCell = titleRow.createCell(0);
            cra = new CellRangeAddress(0, 0, 0, getPropIds().size() - 1);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, getPropIds().size() - 1));
        }
        titleCell.setCellValue(reportTitle);
        titleCell.setCellStyle(titleCellStyle);
        // cell borders don't work on merged ranges so, if there are borders
        // we apply them to the merged range here.
        if (titleCellStyle.getBorderLeft() != CellStyle.BORDER_NONE) {
            RegionUtil.setBorderLeft(titleCellStyle.getBorderLeft(), cra, sheet, workbook);
        }
        if (titleCellStyle.getBorderRight() != CellStyle.BORDER_NONE) {
            RegionUtil.setBorderRight(titleCellStyle.getBorderRight(), cra, sheet, workbook);
        }
        if (titleCellStyle.getBorderTop() != CellStyle.BORDER_NONE) {
            RegionUtil.setBorderTop(titleCellStyle.getBorderTop(), cra, sheet, workbook);
        }
        if (titleCellStyle.getBorderBottom() != CellStyle.BORDER_NONE) {
            RegionUtil.setBorderBottom(titleCellStyle.getBorderBottom(), cra, sheet, workbook);
        }
        return 1;
    }

    /**
     * Adds the header row. Override this method to change header-row-related aspects of the
     * workbook. Alternately, the header Row Object is accessible via getHeaderRow() after report
     * creation. To change header CellStyle, though, use setHeaderStyle().
     *
     * @param row the row
     */
    protected void addHeaderRow(final int row) {
        headerRow = sheet.createRow(row);
        Cell headerCell;
        Object propId;
        headerRow.setHeightInPoints(NumericConstants.FORTY);
        for (int col = 0; col < getPropIds().size(); col++) {
            propId = getPropIds().get(col);
            headerCell = headerRow.createCell(col);
            headerCell.setCellValue(createHelper.createRichTextString(getTableHolder().getColumnHeader(propId)
                    .toString()));
            headerCell.setCellStyle(getColumnHeaderStyle(row, col));

            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(headerCell, workbook, poiAlignment);
        }
    }

    /**
     * This method is called by addTotalsRow() to determine what CellStyle to use. By default we
     * just return totalsCellStyle which is either set to the default totals style, or can be
     * overriden by the user using setTotalsStyle(). However, if the user wants to have different
     * total items have different styles, then this method should be overriden. The parameters
     * passed in are all potentially relevant items that may be used to determine what formatting to
     * return, that are not accessible globally.
     *
     * @param row the row
     * @param col the current column
     * @return the header style
     */
    protected CellStyle getColumnHeaderStyle(final int row, final int col) {
        if ((rowHeaders) && (col == 0)) {
            return titleCellStyle;
        }
        return columnHeaderCellStyle;
    }

    /**
     * For Hierarchical Containers, this method recursively adds root items and child items. The
     * child items are appropriately grouped using grouping/outlining sheet functionality. Override
     * this method to make any changes. To change the CellStyle used for all Table data use
     * setDataStyle(). For different data cells to have different CellStyles, override
     * getDataStyle().
     *
     * @param row the row
     * @return the int
     */
    protected int addHierarchicalDataRows(final Sheet sheetToAddTo, final int row) {
        final Collection<?> roots;
        int localRow = row;
        roots = ((Container.Hierarchical) getTableHolder().getContainerDataSource()).rootItemIds();
        /*
         * For Hierarchical Containers, the outlining/grouping in the sheet is with the summary row
         * at the top and the grouped/outlined subcategories below.
         */
        sheet.setRowSumsBelow(false);
        int count = 0;
        for (final Object rootId : roots) {
            count = addDataRowRecursively(sheetToAddTo, rootId, localRow);
            // for totals purposes, we just want to add rootIds which contain totals
            // so we store just the totals in a separate sheet.
            if (displayTotals) {
                addDataRow(hierarchicalTotalsSheet, rootId, localRow);
            }
            if (count > 1) {
                sheet.groupRow(localRow + 1, (localRow + count) - 1);
                sheet.setRowGroupCollapsed(localRow + 1, true);
            }
            localRow = localRow + count;
        }
        return localRow;
    }

    /**
     * this method adds row items for non-Hierarchical Containers. Override this method to make any
     * changes. To change the CellStyle used for all Table data use setDataStyle(). For different
     * data cells to have different CellStyles, override getDataStyle().
     *
     * @param row the row
     * @return the int
     */
    protected int addDataRows(final Sheet sheetToAddTo, final int row) {
        final Collection<?> itemIds = getTableHolder().getContainerDataSource().getItemIds();
        int localRow = row;
        int count = 0;
        for (final Object itemId : itemIds) {
            addDataRow(sheetToAddTo, itemId, localRow);
            count = 1;
            if (count > 1) {
                sheet.groupRow(localRow + 1, (localRow + count) - 1);
                sheet.setRowGroupCollapsed(localRow + 1, true);
            }
            localRow = localRow + count;
        }
        return localRow;
    }
    int rowIndex = 1;
    /**
     * Used by addHierarchicalDataRows() to implement the recursive calls.
     *
     * @param rootItemId the root item id
     * @param row        the row
     * @return the int
     */
    private int addDataRowRecursively(final Sheet sheetToAddTo, final Object rootItemId, final int row) {
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
    protected void addDataRow(final Sheet sheetToAddTo, final Object rootItemId, final int row){
        rowIndex++;
        final Row sheetRow = sheet.createRow(rowIndex);
        Property prop;
        Object propId;
        Object value;
        Cell sheetCell;

        HSSFDataFormat df = (HSSFDataFormat) workbook.createDataFormat();
        HSSFCellStyle stylepercentFormat = (HSSFCellStyle) workbook.createCellStyle();
        stylepercentFormat.setDataFormat(df.getFormat("#0.0%"));
        applyFormat(stylepercentFormat);

        HSSFCellStyle styleDollarFormat = (HSSFCellStyle) workbook.createCellStyle();
        styleDollarFormat.setDataFormat((short) NumericConstants.SIX);
        applyFormat(styleDollarFormat);
        
        HSSFCellStyle styleUnitFormat = (HSSFCellStyle) workbook.createCellStyle();
        styleUnitFormat.setDataFormat(df.getFormat("#,##0.0"));
        applyFormat(styleUnitFormat);
        
        HSSFCellStyle styleDefaultFormat = (HSSFCellStyle) workbook.createCellStyle();
        styleDefaultFormat.setDataFormat(df.getFormat("#,##0"));
        applyFormat(styleDefaultFormat);

        for (int col = 0; col < getPropIds().size(); col++) {
            propId = getPropIds().get(col);
            prop = getProperty(rootItemId, propId);
            if (null == prop) {
                value = null;
            } else {
                value = prop.getValue();
                if (col == 0) {
                    if (value instanceof Label) {
                        Label headerLabel = (Label) value;
                        groupValue = headerLabel.getValue().toString();
                        groupValue = groupValue.replace("<b>", "");
                        groupValue = groupValue.replace("</b>", "");
                    } else if (value instanceof Button) {
                        Button revisiondate = (Button) value;
                        groupValue = "null".equals(revisiondate.getCaption()) ? "" : revisiondate.getCaption();
                    } else {
                        groupValue = String.valueOf(value);
            }
                }
            }
            sheetCell = sheetRow.createCell(col);
            final CellStyle cs = getCellStyle(rootItemId, row, col, false);
            sheetCell.setCellStyle(cs);
            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            
            if (null != value) {
                double d;
                String propIdValue = String.valueOf(propId);
                if (!isNumeric(prop.getType())) {
                    if (java.util.Date.class.isAssignableFrom(prop.getType())) {
                        if (dollarList != null && dollarList.contains("AdminConsole")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
                        sheetCell.setCellValue(sdf.format(value));
                      }else{
                       SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                       sheetCell.setCellValue(sdf.format(value));
                      }
                    } else {
                        sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                    }
                     if (dollarList != null && dollarList.contains("StringToDouble")) {
                        String changeValue = String.valueOf(value);
                        Pattern pattern = Pattern.compile(".*[^0-9].*");
                        if ((String.valueOf(value).startsWith("$") || String.valueOf(value).startsWith("-$")) && ((!(String.valueOf(value)).isEmpty()) && !(String.valueOf(value)).equals("") && !"null".equals(String.valueOf(value)))) {
                            changeValue = changeValue.replace("$", "");
                            changeValue = changeValue.replace(",", "");
                            double changedDouble = Double.parseDouble(changeValue);
                            sheetCell.setCellStyle(styleDollarFormat);
                            sheetCell.setCellValue(changedDouble);
                        } else if ((String.valueOf(value).endsWith("%") || (String.valueOf(value).endsWith("%") && String.valueOf(value).startsWith("-"))) && ((!(String.valueOf(value)).isEmpty()) && !(String.valueOf(value)).equals("") && !"null".equals(String.valueOf(value)))) {
                            changeValue = changeValue.replace("%", "");
                            changeValue = changeValue.replace(",", "");
                            double newvalue = Double.parseDouble(changeValue);
                            newvalue = newvalue / NumericConstants.HUNDRED;
                            sheetCell.setCellStyle(stylepercentFormat);
                            sheetCell.setCellValue(newvalue);
                        } else if ((String.valueOf(getSheetName()).equals("Phasing Sales projection") || String.valueOf(getSheetName()).equals("Phased Projection")) && 
                                (String.valueOf(value).contains(".") || String.valueOf(value).contains(",") || String.valueOf(value).contains("-") 
                                || !pattern.matcher(changeValue).matches()) && 
                                (!String.valueOf(value).isEmpty() && !"null".equals(String.valueOf(value)) && !String.valueOf(value).equals(""))) {
                            changeValue = changeValue.replace(",", "");
                            double newdecimal = Double.parseDouble(changeValue);
                            sheetCell.setCellStyle(styleUnitFormat);
                            sheetCell.setCellValue(newdecimal);
                        } 
                    } 
                } else {
                   if (!appendRowWise) {
                        try {
                            d = Double.parseDouble(value.toString());
                            if (percentList != null && percentList.contains(propIdValue)) {
                                d = d / NumericConstants.HUNDRED;
                                sheetCell.setCellStyle(stylepercentFormat);
                            } else if (dollarList != null && dollarList.contains(propIdValue)) {
                                sheetCell.setCellStyle(styleDollarFormat);
                            } else if (unitsList != null && unitsList.contains(propIdValue)) {
                                sheetCell.setCellStyle(styleUnitFormat);
                            }else{
                                sheetCell.setCellStyle(styleDefaultFormat);
                            }
                            sheetCell.setCellValue(d);
                           

                        } catch (final NumberFormatException nfe) {
                            LOGGER.warning("NumberFormatException parsing a numeric value: " + nfe);
                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }
                    } else {
                        try {
                            
                            d = Double.parseDouble(value.toString());
                            boolean rowHide = true;
                            if (dollarList != null && (dollarList.contains(groupValue) || isChildBelongsToParentList(rootItemId, dollarList))) {
                                sheetCell.setCellStyle(styleDollarFormat);
                                rowHide = false;
                            } else if (percentList != null && (percentList.contains(groupValue) || isChildBelongsToParentList(rootItemId, percentList))) {
                                d = d / NumericConstants.HUNDRED;
                                sheetCell.setCellStyle(stylepercentFormat);
                                rowHide = false;
                            }
                            if (unitsList != null && unitsList.contains(groupValue)) {
                                sheetCell.setCellStyle(styleUnitFormat);
                                rowHide = false;
                            }
                            if (!rowHide) {
                                sheetCell.setCellValue(d);
                            }

                        } catch (final NumberFormatException nfe) {
                            LOGGER.warning("NumberFormatException parsing a numeric value: " + nfe);
                            sheetCell.setCellValue(createHelper.createRichTextString(value.toString()));
                        }

                    }
                }
            }
            CellUtil.setAlignment(sheetCell, workbook, poiAlignment);
        }
    }

    /**
     * Check whether the child belongs to parent having parentColumn
     *
     * @param childItemId
     * @param parentColumn
     * @return
     */
    boolean isChildBelongsToParentList(Object childItemId, List<String> parentList) {
        if (childItemId != null) {
            Container tableContainer = getTableHolder().getContainerDataSource();
            Object parentItemId = ((Container.Hierarchical) tableContainer).getParent(childItemId);
            Object grandParentItemId = ((Container.Hierarchical) tableContainer).getParent(parentItemId);

            String parentGroup = "";
            String grandParentGroup = "";

            if (parentItemId != null) {
                Item parentItem = ((Container.Hierarchical) tableContainer).getItem(parentItemId);

                if (parentItem.getItemProperty(HeaderUtils.GROUP) != null) {
                    parentGroup = String.valueOf(parentItem.getItemProperty(HeaderUtils.GROUP).getValue());

                }
            }
            if (grandParentItemId != null) {
                Item grandParentItem = ((Container.Hierarchical) tableContainer).getItem(grandParentItemId);

                if (grandParentItem.getItemProperty(HeaderUtils.GROUP) != null) {
                    grandParentGroup = String.valueOf(grandParentItem.getItemProperty(HeaderUtils.GROUP).getValue());
                }
            }
            for (String parentColumn : parentList) {
                if (!parentGroup.equals("") && parentGroup.equals(parentColumn)) {
                    return true;
                }
                if (!grandParentGroup.equals("") && grandParentGroup.equals(parentColumn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Property getProperty(final Object rootItemId, final Object propId) {
        Property prop;
        if (getTableHolder().isGeneratedColumn(propId)) {
            prop = getTableHolder().getPropertyForGeneratedColumn(propId, rootItemId);
        } else {
            prop = getTableHolder().getContainerDataSource().getContainerProperty(rootItemId, propId);
            if (useTableFormatPropertyValue) {
                if (getTableHolder().isExportableFormattedProperty()) {
                    final String formattedProp = getTableHolder().getFormattedPropertyValue(rootItemId, propId, prop);
                    if (null == prop) {
                        prop = new ObjectProperty<String>(formattedProp, String.class);
                    } else {
                        final Object val = prop.getValue();
                        if (null == val) {
                            prop = new ObjectProperty<String>(formattedProp, String.class);
                        } else {
                            if (!val.toString().equals(formattedProp)) {
                                prop = new ObjectProperty<String>(formattedProp, String.class);
                            }
                        }
                    }
                } else {
                    LOGGER.warning("Cannot use Table formatted property unless Table is instance of " +
                            "ExportableFormattedProperty");
                }
            }
        }
        return prop;
    }

    private Class<?> getPropertyType(final Object propId) {
        Class<?> classType;
        if (getTableHolder().isGeneratedColumn(propId)) {
            classType = getTableHolder().getPropertyTypeForGeneratedColumn(propId);
        } else {
            classType = getTableHolder().getContainerDataSource().getType(propId);
        }
        return classType;
    }

    public void setExcelFormatOfProperty(final Object propertyId, final String excelFormat) {
        if (this.propertyExcelFormatMap.containsKey(propertyId)) {
            this.propertyExcelFormatMap.remove(propertyId);
        }
        this.propertyExcelFormatMap.put(propertyId.toString(), excelFormat);
    }

    /**
     * This method is called by addDataRow() to determine what CellStyle to use. By default we just
     * return dataStyle which is either set to the default data style, or can be overriden by the
     * user using setDataStyle(). However, if the user wants to have different data items have
     * different styles, then this method should be overriden. The parameters passed in are all
     * potentially relevant items that may be used to determine what formatting to return, that are
     * not accessible globally.
     *
     * @param rootItemId the root item id
     * @param row        the row
     * @param col        the col
     * @return the data style
     */
    protected CellStyle getCellStyle(final Object rootItemId, final int row, final int col, final boolean totalsRow) {
        final Object propId = getPropIds().get(col);
        // get the basic style for the type of cell (i.e. data, header, total)
        if ((rowHeaders) && (col == 0)) {
            if (null == rowHeaderCellStyle) {
                return columnHeaderCellStyle;
            }
            return rowHeaderCellStyle;
        }
        final Class<?> propType = getPropertyType(propId);
        if (totalsRow) {
            if (this.propertyExcelFormatMap.containsKey(propId)) {
                final short df = dataFormat.getFormat(propertyExcelFormatMap.get(propId));
                final CellStyle customTotalStyle = workbook.createCellStyle();
                customTotalStyle.cloneStyleFrom(totalsDoubleCellStyle);
                customTotalStyle.setDataFormat(df);
                return customTotalStyle;
            }
            if (isIntegerLongShortOrBigDecimal(propType)) {
                return totalsIntegerCellStyle;
            }
            return totalsDoubleCellStyle;
        }
        // Check if the user has over-ridden that data format of this property
        if (this.propertyExcelFormatMap.containsKey(propId)) {
            final short df = dataFormat.getFormat(propertyExcelFormatMap.get(propId));
            if (dataFormatCellStylesMap.containsKey(df)) {
                return dataFormatCellStylesMap.get(df);
            }
            // if it hasn't already been created for re-use, we create a cell style and override the data format
            // For data cells, each data format corresponds to a single complete cell style
            final CellStyle retStyle = workbook.createCellStyle();
            retStyle.cloneStyleFrom(dataFormatCellStylesMap.get(doubleDataFormat));
            retStyle.setDataFormat(df);
            dataFormatCellStylesMap.put(df, retStyle);
            return retStyle;
        }
        // if not over-ridden, use the overall setting
        if (isDoubleOrFloat(propType)) {
            return dataFormatCellStylesMap.get(doubleDataFormat);
        } else {
            if (isIntegerLongShortOrBigDecimal(propType)) {
                return dataFormatCellStylesMap.get(integerDataFormat);
            } else {
                if (java.util.Date.class.isAssignableFrom(propType)) {
                    return dataFormatCellStylesMap.get(dateDataFormat);
                }
            }
        }
        return dataFormatCellStylesMap.get(doubleDataFormat);
    }

    /**
     * Adds the totals row to the report. Override this method to make any changes. Alternately, the
     * totals Row Object is accessible via getTotalsRow() after report creation. To change the
     * CellStyle used for the totals row, use setFormulaStyle. For different totals cells to have
     * different CellStyles, override getTotalsStyle().
     *
     * @param currentRow the current row
     * @param startRow   the start row
     */
    protected void addTotalsRow(final int currentRow, final int startRow) {
        totalsRow = sheet.createRow(currentRow);
        totalsRow.setHeightInPoints(NumericConstants.THIRTY);
        Cell cell;
        CellRangeAddress cra;
        for (int col = 0; col < getPropIds().size(); col++) {
            final Object propId = getPropIds().get(col);
            cell = totalsRow.createCell(col);
            cell.setCellStyle(getCellStyle(currentRow, startRow, col, true));
            final Short poiAlignment = getTableHolder().getCellAlignment(propId);
            CellUtil.setAlignment(cell, workbook, poiAlignment);
            final Class<?> propType = getPropertyType(propId);
            if (isNumeric(propType)) {
                cra = new CellRangeAddress(startRow, currentRow - 1, col, col);
                if (isHierarchical()) {
                    // 9 & 109 are for sum. 9 means include hidden cells, 109 means exclude.
                    // this will show the wrong value if the user expands an outlined category, so
                    // we will range value it first
                    cell.setCellFormula("SUM(" + cra.formatAsString(hierarchicalTotalsSheet.getSheetName(),
                            true) + ")");
                } else {
                    cell.setCellFormula("SUM(" + cra.formatAsString() + ")");
                }
            } else {
                if (0 == col) {
                    cell.setCellValue(createHelper.createRichTextString("Total"));
                }
            }
        }
    }

    /**
     * Final formatting of the sheet upon completion of writing the data. For example, we can only
     * size the column widths once the data is in the report and the sheet knows how wide the data
     * is.
     */
    protected void finalSheetFormat() {
        final FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        if (isHierarchical()) {
            /*
             * evaluateInCell() is equivalent to paste special -> value. The formula refers to cells
             * in the other sheet we are going to delete. We sum in the other sheet because if we
             * summed in the main sheet, we would double count. Subtotal with hidden rows is not yet
             * implemented in POI.
             */
            for (final Row r : sheet) {
                for (final Cell c : r) {
                    if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
                        evaluator.evaluateInCell(c);
                    }
                }
            }
            workbook.setActiveSheet(workbook.getSheetIndex(sheet));
            if (hierarchicalTotalsSheet != null) {
                workbook.removeSheetAt(workbook.getSheetIndex(hierarchicalTotalsSheet));
            }
        } else {
            evaluator.evaluateAll();
        }
        for (int col = 0; col < getPropIds().size(); col++) {
            sheet.autoSizeColumn(col);
        }
    }

    /**
     * Returns the default title style. Obtained from: http://svn.apache.org/repos/asf/poi
     * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
     *
     * @param wb the wb
     * @return the cell style
     */
    protected CellStyle defaultTitleCellStyle(final Workbook wb) {
        CellStyle style;
        final Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) NumericConstants.EIGHTEEN);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFont);
        return style;
    }

    /**
     * Returns the default header style. Obtained from: http://svn.apache.org/repos/asf/poi
     * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
     *
     * @param wb the wb
     * @return the cell style
     */
    protected CellStyle defaultHeaderCellStyle(final Workbook wb) {
        CellStyle style;
        final Font monthFont = wb.createFont();
        monthFont.setFontHeightInPoints((short) NumericConstants.ELEVEN);
        monthFont.setColor(IndexedColors.WHITE.getIndex());
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(monthFont);
        style.setWrapText(true);
        return style;
    }

    /**
     * Returns the default data cell style. Obtained from: http://svn.apache.org/repos/asf/poi
     * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
     *
     * @param wb the wb
     * @return the cell style
     */
    protected CellStyle defaultDataCellStyle(final Workbook wb) {
        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setDataFormat(doubleDataFormat);
        return style;
    }
   
    /**
     * Returns the default totals row style for Double data. Obtained from: http://svn.apache.org/repos/asf/poi
     * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
     *
     * @param wb the wb
     * @return the cell style
     */
    protected CellStyle defaultTotalsDoubleCellStyle(final Workbook wb) {
        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(doubleDataFormat);
        return style;
    }

    /**
     * Returns the default totals row style for Integer data. Obtained from: http://svn.apache.org/repos/asf/poi
     * /trunk/src/examples/src/org/apache/poi/ss/examples/TimesheetDemo.java
     *
     * @param wb the wb
     * @return the cell style
     */
    protected CellStyle defaultTotalsIntegerCellStyle(final Workbook wb) {
        CellStyle style;
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(integerDataFormat);
        return style;
    }

    protected short defaultDoubleDataFormat(final Workbook wb) {
        return createHelper.createDataFormat().getFormat("0.00");
    }

    protected short defaultIntegerDataFormat(final Workbook wb) {
        return createHelper.createDataFormat().getFormat("0");
    }

    protected short defaultDateDataFormat(final Workbook wb) {
        return createHelper.createDataFormat().getFormat("mm/dd/yyyy");
    }

    public void setDoubleDataFormat(final String excelDoubleFormat) {
        CellStyle prevDoubleDataStyle = null;
        if (dataFormatCellStylesMap.containsKey(doubleDataFormat)) {
            prevDoubleDataStyle = dataFormatCellStylesMap.get(doubleDataFormat);
            dataFormatCellStylesMap.remove(doubleDataFormat);
        }
        doubleDataFormat = createHelper.createDataFormat().getFormat(excelDoubleFormat);
        if (null != prevDoubleDataStyle) {
            doubleCellStyle = prevDoubleDataStyle;
            doubleCellStyle.setDataFormat(doubleDataFormat);
            dataFormatCellStylesMap.put(doubleDataFormat, doubleCellStyle);
        }
    }

    public void setIntegerDataFormat(final String excelIntegerFormat) {
        CellStyle prevIntegerDataStyle = null;
        if (dataFormatCellStylesMap.containsKey(integerDataFormat)) {
            prevIntegerDataStyle = dataFormatCellStylesMap.get(integerDataFormat);
            dataFormatCellStylesMap.remove(integerDataFormat);
        }
        integerDataFormat = createHelper.createDataFormat().getFormat(excelIntegerFormat);
        if (null != prevIntegerDataStyle) {
            integerCellStyle = prevIntegerDataStyle;
            integerCellStyle.setDataFormat(integerDataFormat);
            dataFormatCellStylesMap.put(integerDataFormat, integerCellStyle);
        }
    }

    public void setDateDataFormat(final String excelDateFormat) {
        CellStyle prevDateDataStyle = null;
        if (dataFormatCellStylesMap.containsKey(dateDataFormat)) {
            prevDateDataStyle = dataFormatCellStylesMap.get(dateDataFormat);
            dataFormatCellStylesMap.remove(dateDataFormat);
        }
        dateDataFormat = createHelper.createDataFormat().getFormat(excelDateFormat);
        if (null != prevDateDataStyle) {
            dateCellStyle = prevDateDataStyle;
            dateCellStyle.setDataFormat(dateDataFormat);
            dataFormatCellStylesMap.put(dateDataFormat, dateCellStyle);
        }
    }

    /**
     * Utility method to determine whether value being put in the Cell is numeric.
     *
     * @param type the type
     * @return true, if is numeric
     */
    private boolean isNumeric(final Class<?> type) {
        if (isIntegerLongShortOrBigDecimal(type)) {
            return true;
        }
        if (isDoubleOrFloat(type)) {
            return true;
        }
        return false;
    }

    /**
     * Utility method to determine whether value being put in the Cell is integer-like type.
     *
     * @param type the type
     * @return true, if is integer-like
     */
    private boolean isIntegerLongShortOrBigDecimal(final Class<?> type) {
        if (Integer.class.equals(type) || (int.class.equals(type))) {
            return true;
        }
        if (Long.class.equals(type) || (long.class.equals(type))) {
            return true;
        }
        if ((Short.class.equals(type)) || (short.class.equals(type))) {
            return true;
        }
        if ((BigDecimal.class.equals(type)) || (BigDecimal.class.equals(type))) {
            return true;
        }
        return false;
    }

    /**
     * Utility method to determine whether value being put in the Cell is double-like type.
     *
     * @param type the type
     * @return true, if is double-like
     */
    private boolean isDoubleOrFloat(final Class<?> type) {
        if ((Double.class.equals(type)) || (double.class.equals(type))) {
            return true;
        }
        if ((Float.class.equals(type)) || (float.class.equals(type))) {
            return true;
        }
        return false;
    }

    /**
     * Gets the workbook.
     *
     * @return the workbook
     */
    public Workbook getWorkbook() {
        return this.workbook;
    }

    /**
     * Gets the sheet name.
     *
     * @return the sheet name
     */
    public String getSheetName() {
        return this.sheetName;
    }

    /**
     * Gets the report title.
     *
     * @return the report title
     */
    public String getReportTitle() {
        return this.reportTitle;
    }

    /**
     * Gets the export file name.
     *
     * @return the export file name
     */
    public String getExportFileName() {
        return this.exportFileName;
    }

    /**
     * Gets the cell style used for report data..
     *
     * @return the cell style
     */
    public CellStyle getDoubleDataStyle() {
        return this.doubleCellStyle;
    }

    /**
     * Gets the cell style used for report data..
     *
     * @return the cell style
     */
    public CellStyle getIntegerDataStyle() {
        return this.integerCellStyle;
    }

    public CellStyle getDateDataStyle() {
        return this.dateCellStyle;
    }

    /**
     * Gets the cell style used for the report headers.
     *
     * @return the column header style
     */
    public CellStyle getColumnHeaderStyle() {
        return this.columnHeaderCellStyle;
    }

    /**
     * Gets the cell title used for the report title.
     *
     * @return the title style
     */
    public CellStyle getTitleStyle() {
        return this.titleCellStyle;
    }

    /**
     * Sets the text used for the report title.
     *
     * @param reportTitle the new report title
     */
    public void setReportTitle(final String reportTitle) {
        this.reportTitle = reportTitle;
    }

    /**
     * Sets the export file name.
     *
     * @param exportFileName the new export file name
     */
    public void setExportFileName(final String exportFileName) {
        this.exportFileName = exportFileName;
    }

    /**
     * Sets the cell style used for report data.
     *
     * @param doubleDataStyle the new data style
     */
    public void setDoubleDataStyle(final CellStyle doubleDataStyle) {
        this.doubleCellStyle = doubleDataStyle;
    }

    /**
     * Sets the cell style used for report data.
     *
     * @param integerDataStyle the new data style
     */
    public void setIntegerDataStyle(final CellStyle integerDataStyle) {
        this.integerCellStyle = integerDataStyle;
    }

    /**
     * Sets the cell style used for report data.
     *
     * @param dateDataStyle the new data style
     */
    public void setDateDataStyle(final CellStyle dateDataStyle) {
        this.dateCellStyle = dateDataStyle;
    }

    /**
     * Sets the cell style used for the report headers.
     *
     * @param columnHeaderStyle CellStyle
     */
    public void setColumnHeaderStyle(final CellStyle columnHeaderStyle) {
        this.columnHeaderCellStyle = columnHeaderStyle;
    }

    /**
     * Sets the cell style used for the report title.
     *
     * @param titleStyle the new title style
     */
    public void setTitleStyle(final CellStyle titleStyle) {
        this.titleCellStyle = titleStyle;
    }

    /**
     * Gets the title row.
     *
     * @return the title row
     */
    public Row getTitleRow() {
        return this.titleRow;
    }

    /**
     * Gets the header row.
     *
     * @return the header row
     */
    public Row getHeaderRow() {
        return this.headerRow;
    }

    /**
     * Gets the totals row.
     *
     * @return the totals row
     */
    public Row getTotalsRow() {
        return this.totalsRow;
    }

    /**
     * Gets the cell style used for the totals row.
     *
     * @return the totals style
     */
    public CellStyle getTotalsDoubleStyle() {
        return this.totalsDoubleCellStyle;
    }

    /**
     * Sets the cell style used for the totals row.
     *
     * @param totalsDoubleStyle the new totals style
     */
    public void setTotalsDoubleStyle(final CellStyle totalsDoubleStyle) {
        this.totalsDoubleCellStyle = totalsDoubleStyle;
    }

    /**
     * Gets the cell style used for the totals row.
     *
     * @return the totals style
     */
    public CellStyle getTotalsIntegerStyle() {
        return this.totalsIntegerCellStyle;
    }

    /**
     * Sets the cell style used for the totals row.
     *
     * @param totalsIntegerStyle the new totals style
     */
    public void setTotalsIntegerStyle(final CellStyle totalsIntegerStyle) {
        this.totalsIntegerCellStyle = totalsIntegerStyle;
    }

    /**
     * Flag indicating whether a totals row will be added to the report or not.
     *
     * @return true, if totals row will be added
     */
    public boolean isDisplayTotals() {
        return this.displayTotals;
    }

    /**
     * Sets the flag indicating whether a totals row will be added to the report or not.
     *
     * @param displayTotals boolean
     */
    public void setDisplayTotals(final boolean displayTotals) {
        this.displayTotals = displayTotals;
    }

    public void setUseTableFormatPropertyValue(final boolean useFormatPropertyValue) {
        this.useTableFormatPropertyValue = useFormatPropertyValue;
    }

    /**
     * See value of flag indicating whether the first column should be treated as row headers.
     *
     * @return boolean
     */
    public boolean hasRowHeaders() {
        return this.rowHeaders;
    }

    /**
     * Method getRowHeaderStyle.
     *
     * @return CellStyle
     */
    public CellStyle getRowHeaderStyle() {
        return this.rowHeaderCellStyle;
    }

    /**
     * Set value of flag indicating whether the first column should be treated as row headers.
     *
     * @param rowHeaders boolean
     */
    public void setRowHeaders(final boolean rowHeaders) {
        this.rowHeaders = rowHeaders;
    }

    /**
     * Method setRowHeaderStyle.
     *
     * @param rowHeaderStyle CellStyle
     */
    public void setRowHeaderStyle(final CellStyle rowHeaderStyle) {
        this.rowHeaderCellStyle = rowHeaderStyle;
    }
    public void applyFormat(HSSFCellStyle format){
        format.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        format.setBorderTop(HSSFCellStyle.BORDER_THIN);
        format.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        format.setBorderRight(HSSFCellStyle.BORDER_THIN);
    }
}
