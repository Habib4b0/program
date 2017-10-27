/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.arm.adjustmentrateconfiguration.AdjustmentRateUI;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.AdjustmentReserveTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.AdjustmentSummaryTableLogic;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic.BalSummaryConfigurationTableLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ExtCustomTable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author saranya.sekar
 */
public class ExcelExportLogic {

    protected static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(AbstractNotificationUtils.class);

    protected AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    ReserveSelection selection;
    long recordCount = 0;

    public void excelLogic(ExtCustomTable excelCustomTable, ExtPagedTable resultTable, int tabPosition, ReserveSelection selection) {
        this.selection = selection;
        final BeanItemContainer<AdjustmentReserveDTO> excelContainer = new BeanItemContainer<>(AdjustmentReserveDTO.class);
        excelCustomTable.setContainerDataSource(excelContainer);
        switch (tabPosition) {
            case 1:
                excelLogicForAdjustmentSummary(excelCustomTable, resultTable);
                break;
            case 2:
                excelLogicForBalanceSummary(excelCustomTable, resultTable);
                break;
            default:
                excelLogicForConfigurationDetail(excelCustomTable, resultTable);
                break;
        }
        AdjustmentRateUI.EXCEL_CLOSE = true;
        ExcelExport exp = new ExcelExport(new ExtCustomTableHolder(excelCustomTable), selection.getTabNameForExcel(), selection.getTabNameForExcel(), selection.getTabNameForExcel() + ".xls", false);
        exp.export();

    }

    public void excelLogicForBalanceSummary(ExtCustomTable excelCustomTable, ExtPagedTable balanceSummaryTable) {
        Object[] newVisibleCol = addingNewProperyIdForExcel(balanceSummaryTable.getVisibleColumns());
        excelCustomTable.setVisibleColumns((Arrays.asList(newVisibleCol).subList(1, newVisibleCol.length)).toArray());
        excelCustomTable.setColumnHeaders(Arrays.asList(balanceSummaryTable.getColumnHeaders()).subList(1, balanceSummaryTable.getColumnHeaders().length).toArray(new String[balanceSummaryTable.getColumnHeaders().length - 1]));
        excelCustomTable.setDoubleHeaderVisible(true);
        excelCustomTable.setDoubleHeaderVisibleColumns(Arrays.asList(balanceSummaryTable.getDoubleHeaderVisibleColumns()).subList(1, balanceSummaryTable.getDoubleHeaderVisibleColumns().length).toArray());
        excelCustomTable.setDoubleHeaderColumnHeaders(Arrays.asList(balanceSummaryTable.getDoubleHeaderColumnHeaders()).subList(1, balanceSummaryTable.getDoubleHeaderColumnHeaders().length).toArray(new String[balanceSummaryTable.getDoubleHeaderColumnHeaders().length - 1]));
        Map<Object, Object[]> headerMap = new HashMap<>();
        Map<String, String[]> oldheaderMap = balanceSummaryTable.getDoubleHeaderMap();
        for (Map.Entry<String, String[]> entry : oldheaderMap.entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue());
        }
        headerMap.remove(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CHECK_RECORD.getConstant());
        excelCustomTable.setDoubleHeaderMap(headerMap);
        loadData(balanceSummaryTable, excelCustomTable);
        headerMap.clear();
        for (Map.Entry<String, String[]> entry : oldheaderMap.entrySet()) {
            headerMap.put(entry.getKey(), entry.getValue());
        }
        balanceSummaryTable.setDoubleHeaderMap(headerMap);

    }

    private void loadData(ExtPagedTable balanceSummaryTable, ExtCustomTable excelCustomTable) {
        BalSummaryConfigurationTableLogic balSummaryConfigurationTableLogic = (BalSummaryConfigurationTableLogic) balanceSummaryTable.getContainerLogic();
        int recordCountForBal = balSummaryConfigurationTableLogic.getCount();
        if (recordCountForBal > 0) {
            List<AdjustmentReserveDTO> searchList = BalanceSummaryLogic.getInstance().getBalanceSummaryData(selection, 0, recordCountForBal, balSummaryConfigurationTableLogic.getFilters(), balSummaryConfigurationTableLogic.getSortByColumns(), true);
            loadSetData(searchList, excelCustomTable);
        }
    }

    private void excelLogicForAdjustmentSummary(ExtCustomTable excelCustomTable, ExtPagedTable resultTable) {
        Object[] visibleCol = resultTable.getVisibleColumns();
        Object[] newVisibleCol = new Object[resultTable.getVisibleColumns().length];
        for (int i = 0; i < visibleCol.length; i++) {
            String replacableValue = visibleCol[i].toString();
            if (!"adjSummaryAdjustmentType".equals(replacableValue) && replacableValue.contains("adjSummary") && !replacableValue.contains("Account")) {
                replacableValue += "Desc";
            }
            newVisibleCol[i] = replacableValue;
        }
        excelCustomTable.setVisibleColumns((Arrays.asList(newVisibleCol).subList(1, newVisibleCol.length)).toArray());
        excelCustomTable.setColumnHeaders(Arrays.asList(resultTable.getColumnHeaders()).subList(1, resultTable.getColumnHeaders().length).toArray(new String[resultTable.getColumnHeaders().length - 1]));
        AdjustmentSummaryTableLogic adjustmentSummaryTableLogic = (AdjustmentSummaryTableLogic) resultTable.getContainerLogic();
        int recordCountForAdj = adjustmentSummaryTableLogic.getCount();
        if (recordCountForAdj > 0) {
            List<AdjustmentReserveDTO> searchList = AdjustmentSummaryConfigLogic.getInstance().getAdjSummaryConfigurationData(selection, 0, recordCountForAdj, adjustmentSummaryTableLogic.getFilters(), adjustmentSummaryTableLogic.getSortByColumns(), true);
            loadSetData(searchList, excelCustomTable);
        }
    }

    private void excelLogicForConfigurationDetail(ExtCustomTable excelCustomTable, ExtPagedTable resultTable) {
        if (selection.isIsGTNDetails()) {
            excelCustomTable.setVisibleColumns(ARMUtils.getAdjustmentReserveAddColumnsGtnDetailsForExcel());

        } else {
            excelCustomTable.setVisibleColumns(ARMUtils.getAdjustmentReserveAddColumnsReserveDetailsForExcel());

        }
        excelCustomTable.setColumnHeaders(Arrays.asList(resultTable.getColumnHeaders()).subList(1, resultTable.getColumnHeaders().length).toArray(new String[resultTable.getColumnHeaders().length - 1]));
        AdjustmentReserveTableLogic adjustmentReserveTableLogic = (AdjustmentReserveTableLogic) resultTable.getContainerLogic();
        int recordCountForConfig = logic.getReserveEditCount(selection, adjustmentReserveTableLogic.getFilters());

        if (recordCountForConfig > 0) {
            List<AdjustmentReserveDTO> searchList = logic.getReserveData(selection, 0, recordCountForConfig, adjustmentReserveTableLogic.getFilters(), adjustmentReserveTableLogic.getSortByColumns());
            loadSetData(searchList, excelCustomTable);

        }
    }

    private void loadSetData(List<AdjustmentReserveDTO> searchList, ExtCustomTable excelCustomTable) {
        BeanItemContainer<AdjustmentReserveDTO> excelContainer = (BeanItemContainer<AdjustmentReserveDTO>) excelCustomTable.getContainerDataSource();
        excelContainer.addAll(searchList);

    }

    private Object[] addingNewProperyIdForExcel(final Object[] visibleCol) {
        Object[] newVisibleCol = new Object[visibleCol.length];
        for (int i = 0; i < visibleCol.length; i++) {
            String replacableValue = visibleCol[i].toString();
            if (!"balSummaryAccount".equals(replacableValue) && replacableValue.contains("AdjustmentType")) {
                replacableValue += "Desc";
            }
            newVisibleCol[i] = replacableValue;
        }
        return newVisibleCol;
    }

}
