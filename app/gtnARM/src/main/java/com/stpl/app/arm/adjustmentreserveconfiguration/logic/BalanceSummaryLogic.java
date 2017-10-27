/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Mohamed.Shahul
 */
public class BalanceSummaryLogic {

    private static final BalanceSummaryLogic logic = new BalanceSummaryLogic();

    private BalanceSummaryLogic() {

    }

    public static BalanceSummaryLogic getInstance() {
        return logic;
    }

    /**
     * Method to check whether the report type is already available or not in
     * the temp table
     *
     * @param selection
     * @return boolean
     */
    public boolean isReportTypeAvailable(ReserveSelection selection) {
        List inputList = getSessionReportTypeInput(selection);
        return CommonLogic.getCount(QueryUtils.getItemData(inputList, "isReportTypeAvailableInTemp", null)) == 0;
    }

    /**
     * This is the method to insert tables for the very first time in balance
     * summary
     *
     * @param selection
     */
    public void insertBalanceSummaryToTempTable(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        inputList.add(selection.getReportType());
        inputList.add(selection.getSession().getUserId());
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getBalanceSummaryTempTableName());
        QueryUtils.itemUpdate(inputList, "insertBalanceSummaryToTempTable");
    }

    /**
     * This is the input method to get Two inputs which we will through out
     * everywhere
     *
     * @param selection
     * @return
     */
    private List getSessionReportTypeInput(final ReserveSelection selection) {
        List inputList = new ArrayList<>(2);
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReportType());
        return inputList;

    }

    /**
     * Method to get count of balance summary count
     *
     * @param selection
     * @param filters
     */
    public int getBalanceSummaryCount(ReserveSelection selection, Set<Container.Filter> filters) {
        List inputList = getInputDataList(selection, filters);
        List<Object[]> resultList = QueryUtils.getItemData(inputList, "getBalanceSummaryCount", null);
        inputList.clear();
        return CommonLogic.getCount(resultList);
    }

    /**
     * Method to retrieve balance summary record in from db
     *
     * @param selection
     * @param start
     * @param offset
     * @param filters
     * @param sortByColumns
     * @return List of data
     */
    public List getBalanceSummaryData(ReserveSelection selection, int start, int offset, Set<Container.Filter> filters, List<SortByColumn> sortByColumns, boolean isExcel) {
        List inputList = getInputDataList(selection, filters);
        StringBuilder orderByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, ARMUtils.getBalanceSummaryVisibleToDBColumnMap(), " SUBSTRING(VERSION_NO,1,1),LEN(VERSION_NO)-LEN(REPLACE(VERSION_NO,'.',''))");
        inputList.add(orderByQuery);
        inputList.add(start);
        inputList.add(offset);
        List<Object[]> resultList = QueryUtils.getItemData(inputList, isExcel ? "getBalanceSummaryExcelData" : "getBalanceSummaryData", null);
        inputList.clear();
        return isExcel ? customizingExcelDBData(resultList) : customizingDBData(resultList);
    }

    /**
     * getInputDataList
     *
     * @param selection
     * @param filters
     * @return
     */
    private List getInputDataList(final ReserveSelection selection, Set<Container.Filter> filters) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReportType());
        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGenerator(filters, ARMUtils.getBalanceSummaryVisibleToDBColumnMap());
        if (sql != null) {
            inputList.add(sql.toString().replace("where", " AND "));
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        return inputList;
    }

    /**
     * Customization of Data for Adjustment Summary
     *
     * @param resultList
     * @return
     */
    private List<AdjustmentReserveDTO> customizingDBData(final List<Object[]> resultList) {
        List<AdjustmentReserveDTO> dataList = new ArrayList<>();
        for (Object[] objects : resultList) {
            AdjustmentReserveDTO data = new AdjustmentReserveDTO();
            data.setBalanceSummarySid(objects[0] != null ? Integer.valueOf(String.valueOf(objects[0])) : 0);
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setBalSummaryAccount(objects[2] != null ? (String) objects[2] : GlobalConstants.getSelectOne());
            data.setBalSummaryPipelineAccrualAdjustmentType(objects[3] != null ? Integer.valueOf(String.valueOf(objects[3])) : 0);
            data.setBalSummaryDemandAccrualAdjustmentType(objects[4] != null ? Integer.valueOf(String.valueOf(objects[4])) : 0);
            data.setBalSummaryPipelinelineInventoryTrueupAdjustmentType(objects[5] != null ? Integer.valueOf(String.valueOf(objects[5])) : 0);
            data.setBalSummaryDemandReforecastAdjustmentType(objects[6] != null ? Integer.valueOf(String.valueOf(objects[6])) : 0);
            data.setBalSummaryPaymentTrueupAdjustmentType(objects[7] != null ? Integer.valueOf(String.valueOf(objects[7])) : 0);
            data.setBalSummaryDemandTrueupAdjustmentType(objects[7] != null ? Integer.valueOf(String.valueOf(objects[7])) : 0);
            data.setBalSummaryFeesAccrualAdjustmentType(objects[8] != null ? Integer.valueOf(String.valueOf(objects[8])) : 0);
            data.setBalSummaryInflationAdjustmentAdjustmentType(objects[9] != null ? Integer.valueOf(String.valueOf(objects[9])) : 0);
            data.setBalSummaryCreditCardFeesAdjustmentType(objects[10] != null ? Integer.valueOf(String.valueOf(objects[10])) : 0);
            data.setBalSummaryOtherFixedDollarFeesAdjustmentType(objects[11] != null ? Integer.valueOf(String.valueOf(objects[11])) : 0);
            data.setBalSummaryInventoryValuationAdjustmentType(objects[12] != null ? Integer.valueOf(String.valueOf(objects[12])) : 0);
            data.setBalSummaryReturnReserveAdjustmentType(objects[13] != null ? Integer.valueOf(String.valueOf(objects[13])) : 0);
            data.setVersionNo(objects[14] != null ? (String) objects[14] : StringUtils.EMPTY);
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * Customization of Data for Adjustment Summary
     *
     * @param resultList
     * @return
     */
    private List<AdjustmentReserveDTO> customizingExcelDBData(final List<Object[]> resultList) {
        List<AdjustmentReserveDTO> dataList = new ArrayList<>();
        for (Object[] objects : resultList) {
            AdjustmentReserveDTO data = new AdjustmentReserveDTO();
            data.setBalanceSummarySid(objects[0] != null ? Integer.valueOf(String.valueOf(objects[0])) : 0);
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setBalSummaryAccount(objects[2] != null ? (String) objects[2] : GlobalConstants.getSelectOne());
            data.setBalSummaryPipelineAccrualAdjustmentTypeDesc(objects[3] != null ? (String.valueOf(objects[3])) : StringUtils.EMPTY);
            data.setBalSummaryDemandAccrualAdjustmentTypeDesc(objects[4] != null ? (String.valueOf(objects[4])) : StringUtils.EMPTY);
            data.setBalSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc(objects[5] != null ? (String.valueOf(objects[5])) : StringUtils.EMPTY);
            data.setBalSummaryDemandReforecastAdjustmentTypeDesc(objects[6] != null ? (String.valueOf(objects[6])) : StringUtils.EMPTY);
            data.setBalSummaryPaymentTrueupAdjustmentTypeDesc(objects[7] != null ? (String.valueOf(objects[7])) : StringUtils.EMPTY);
            data.setBalSummaryDemandTrueupAdjustmentTypeDesc(objects[7] != null ? (String.valueOf(objects[7])) : StringUtils.EMPTY);
            data.setBalSummaryFeesAccrualAdjustmentTypeDesc(objects[8] != null ? (String.valueOf(objects[8])) : StringUtils.EMPTY);
            data.setBalSummaryInflationAdjustmentAdjustmentTypeDesc(objects[9] != null ? (String.valueOf(objects[9])) : StringUtils.EMPTY);
            data.setBalSummaryCreditCardFeesAdjustmentTypeDesc(objects[10] != null ? (String.valueOf(objects[10])) : StringUtils.EMPTY);
            data.setBalSummaryOtherFixedDollarFeesAdjustmentTypeDesc(objects[11] != null ? (String.valueOf(objects[11])) : StringUtils.EMPTY);
            data.setBalSummaryInventoryValuationAdjustmentTypeDesc(objects[12] != null ? (String.valueOf(objects[12])) : StringUtils.EMPTY);
            data.setBalSummaryReturnReserveAdjustmentTypeDesc(objects[13] != null ? (String.valueOf(objects[13])) : StringUtils.EMPTY);
            data.setVersionNo(objects[14] != null ? (String) objects[14] : StringUtils.EMPTY);
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * Method to update the table values when it is been changed.
     *
     * @param dto
     * @param value
     * @param column
     * @param tempTableName
     */
    public void updateTableValues(Integer balanceSummarySid, Object value, String column, String tempTableName) {
        List inputList = new ArrayList<>(4);
        inputList.add(tempTableName);
        inputList.add(column);
        Object values;
        if (value != null) {
            values = "'" + value + "'";
        } else {
            values = value;
        }
        inputList.add(values);
        inputList.add(balanceSummarySid);
        QueryUtils.itemUpdate(inputList, "update_Balance_Summary_Temp_TableData");
    }

    /**
     * Method to add a new line in the adjustment summary tab
     *
     * @param selection
     */
    public void addLineLogic(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getSession().getUserId());
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReportType());
        QueryUtils.itemUpdate(inputList, "addLine_Balance_Summary_Temp_TableData");
    }

    /**
     * THis is the method of getting input to check remove line which is
     * applicable for both adjustment and balance summary
     *
     * @param selection
     */
    public List commonInputForSummaryTabs(ReserveSelection selection) {
        List inputList = new ArrayList(3);
        if (selection.getTabIndex() == 1) {
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            inputList.add("METHODOLOGY");
            inputList.add(selection.getMethodology());
        } else {
            inputList.add(selection.getBalanceSummaryTempTableName());
            inputList.add("REPORT_TYPE");
            inputList.add(selection.getReportType());
        }
        return inputList;
    }

    /**
     * Method to save the balance summary information from temp table to main
     * table
     *
     * @param selection
     */
    public void saveTempToMainBalanceSummaryData(ReserveSelection selection) {
        List inputList = new ArrayList<>(2);
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        QueryUtils.itemUpdate(inputList, "saveBalanceSummaryTempToMain");
    }

    /**
     * This is the method to re insert values from main to temp tables
     *
     * @param selection
     */
    public void insertBalanceSummaryToTempTableFromMainTable(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        if (selection.isResetLine()) {
            inputList.add("AND REPORT_TYPE =" + selection.getReportType());
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        QueryUtils.itemUpdate(inputList, "insertBalanceSummaryToTempTableFromMainTable");
    }

    /**
     * resetAdjSummary
     *
     * @param selection
     */
    public void resetAdjSummary(ReserveSelection selection) {
        List input = getSessionReportTypeInput(selection);
        QueryUtils.itemUpdate(input, "BalanceSummary_DeleteAddedLines");
    }

    /**
     * resetAdjSummary
     *
     * @param selection
     */
    public void deleteTempTableRecords(ReserveSelection selection) {
        List input = getSessionReportTypeInput(selection);
        QueryUtils.itemUpdate(input, "deleteTempTableBalSummaryRecords");
    }

}
