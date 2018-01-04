/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.ui.Table;
import java.util.Collection;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author Abhiram
 */
public class CommonExcelExportForNM extends CommonExcelExport {

    /**
     *
     */
    private static final long serialVersionUID = 4785141137378430008L;

    public CommonExcelExportForNM(Table table, String screenName, List<List> exportList) {
        this(table, screenName, exportList, false);
    }

    public CommonExcelExportForNM(Table table, String screenName, List<List> exportList, boolean appendRowWise) {
        this(table, screenName, screenName, screenName.trim().replaceAll(" ", "_") + ".xls", exportList, appendRowWise);
    }

    public CommonExcelExportForNM(Table table, String sheetName, String reportTitle,
            String exportFileName, List<List> exportList) {
        this(table, sheetName, reportTitle, exportFileName, exportList, false);
    }

    public CommonExcelExportForNM(Table table, String sheetName, String reportTitle,
            String exportFileName, List<List> exportList, boolean isAppendRowWise) {
        super(table, sheetName, reportTitle, exportFileName, exportList, isAppendRowWise, false);
    }
    public CommonExcelExportForNM(final TableHolder tableHolder, String screenName, List<List> exportList) {
        this(tableHolder, screenName, exportList, false);
    }

    public CommonExcelExportForNM(final TableHolder tableHolder, String screenName, List<List> exportList, boolean appendRowWise) {
        this(tableHolder, screenName, screenName, screenName.trim().replaceAll(" ", "_") + ".xls", exportList, appendRowWise);
    }

    public CommonExcelExportForNM(final TableHolder tableHolder, String sheetName, String reportTitle,
            String exportFileName, List<List> exportList) {
        this(tableHolder, sheetName, reportTitle, exportFileName, exportList, false);
    }

    public CommonExcelExportForNM(final TableHolder tableHolder, String sheetName, String reportTitle,
            String exportFileName, List<List> exportList, boolean isAppendRowWise) {
        super(tableHolder, sheetName, reportTitle, exportFileName, exportList, isAppendRowWise, false);
    }

    /**
     * For Hierarchical Containers, this method recursively adds root items and
     * child items. The child items are appropriately grouped using
     * grouping/outlining sheet functionality. Override this method to make any
     * changes. To change the CellStyle used for all Table data use
     * setDataStyle(). For different data cells to have different CellStyles,
     * override getDataStyle().
     *
     * @param sheetToAddTo
     * @param row the row
     *
     * @return the int
     */
    @Override
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

            if (count > 1) {

                sheet.groupRow(localRow + 1, (localRow + count) - 1);
                sheet.setRowGroupCollapsed(localRow + 1, true);
            }
            localRow = localRow + count;
        }
        return localRow;
    }

    /**
     * Used by addHierarchicalDataRows() to implement the recursive calls.
     *
     * @param rootItemId the root item id
     * @param row the row
     *
     * @return the int
     */
    private int addDataRowRecursively(final Sheet sheetToAddTo, final Object rootItemId,
            final int row) {
        int numberAdded = 0;
        int localRow = row;
        addDataRow(sheetToAddTo, rootItemId, row);
        numberAdded++;
        if (((Container.Hierarchical) getTableHolder().getContainerDataSource()).hasChildren(rootItemId)) {
            final Collection<?> children =
                    ((Container.Hierarchical) getTableHolder().getContainerDataSource())
                    .getChildren(rootItemId);
            for (final Object child : children) {
                localRow++;
                numberAdded = numberAdded + addDataRowRecursively(sheetToAddTo, child, localRow);
            }
        }
        return numberAdded;
    }
}
