/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.ifs.util;

import com.stpl.addons.tableexport.TableHolder;
import com.vaadin.v7.ui.Table;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Abhiram
 */
public class MandatedHierarchialExcelExport extends CommonExcelExport{

    public MandatedHierarchialExcelExport(TableHolder tableHolder, Workbook wkbk, String shtName, String rptTitle, String xptFileName, List<List> exportlist, boolean appendRowWise, boolean hasTotalsRow) {
        super(tableHolder, wkbk, shtName, rptTitle, xptFileName, exportlist, appendRowWise, hasTotalsRow);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, List<List> exportlist, boolean appendRowWise, boolean hasTotalsRow) {
        super(tableHolder, sheetName, reportTitle, exportFileName, exportlist, appendRowWise, hasTotalsRow);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, List<List> exportlist, boolean appendRowWise) {
        super(tableHolder, sheetName, reportTitle, exportFileName, exportlist, appendRowWise);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName, List<List> exportlist) {
        super(tableHolder, sheetName, reportTitle, exportFileName, exportlist);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName, String reportTitle, String exportFileName) {
        super(tableHolder, sheetName, reportTitle, exportFileName);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName, String reportTitle) {
        super(tableHolder, sheetName, reportTitle);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder, String sheetName) {
        super(tableHolder, sheetName);
    }

    public MandatedHierarchialExcelExport(TableHolder tableHolder) {
        super(tableHolder);
    }

    public MandatedHierarchialExcelExport(Table table, Workbook wkbk, String shtName, String rptTitle, String xptFileName, List<List> exportlist, boolean appendRowWise, boolean hasTotalsRow) {
        super(table, wkbk, shtName, rptTitle, xptFileName, exportlist, appendRowWise, hasTotalsRow);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName, String reportTitle, String exportFileName, List<List> exportlist, boolean appendRowWise, boolean hasTotalsRow) {
        super(table, sheetName, reportTitle, exportFileName, exportlist, appendRowWise, hasTotalsRow);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName, String reportTitle, String exportFileName, List<List> exportlist, boolean appendRowWise) {
        super(table, sheetName, reportTitle, exportFileName, exportlist, appendRowWise);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName, String reportTitle, String exportFileName, List<List> exportlist) {
        super(table, sheetName, reportTitle, exportFileName, exportlist);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName, String reportTitle, String exportFileName) {
        super(table, sheetName, reportTitle, exportFileName);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName, String reportTitle) {
        super(table, sheetName, reportTitle);
    }

    public MandatedHierarchialExcelExport(Table table, String sheetName) {
        super(table, sheetName);
    }

    public MandatedHierarchialExcelExport(Table table) {
        super(table);
    }
    
}
