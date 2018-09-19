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
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
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
        return resultList.size();
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
        if (filters != null && !filters.isEmpty()) {
            joinHelperTable(inputList, filters);
        } else {
            inputList.add(StringUtils.EMPTY);
        }
        inputList.add(selection.getReportType());
        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGeneratorForBal(filters);
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
            data.setBalanceSummarySid(CommonLogic.getIntegerValue(0, objects));
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setBalSummaryAccount(objects[2] != null ? (String) objects[2] : GlobalConstants.getSelectOne());
            data.setBalSummaryPipelineAccrualAdjustmentType(CommonLogic.getIntegerValue(3, objects));
            data.setBalSummaryDemandAccrualAdjustmentType(CommonLogic.getIntegerValue(4, objects));
            data.setBalSummaryPipelinelineInventoryTrueupAdjustmentType(CommonLogic.getIntegerValue(5, objects));
            data.setBalSummaryDemandReforecastAdjustmentType(CommonLogic.getIntegerValue(6, objects));
            data.setBalSummaryPaymentTrueupAdjustmentType(CommonLogic.getIntegerValue(7, objects));
            data.setBalSummaryDemandTrueupAdjustmentType(CommonLogic.getIntegerValue(7, objects));
            data.setBalSummaryFeesAccrualAdjustmentType(CommonLogic.getIntegerValue(8, objects));
            data.setBalSummaryInflationAdjustmentAdjustmentType(CommonLogic.getIntegerValue(9, objects));
            data.setBalSummaryCreditCardFeesAdjustmentType(CommonLogic.getIntegerValue(10, objects));
            data.setBalSummaryOtherFixedDollarFeesAdjustmentType(CommonLogic.getIntegerValue(11, objects));
            data.setBalSummaryInventoryValuationAdjustmentType(CommonLogic.getIntegerValue(12, objects));
            data.setBalSummaryReturnReserveAdjustmentType(CommonLogic.getIntegerValue(13, objects));
            data.setVersionNo(CommonLogic.getStringValue(14, objects));
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
            data.setBalanceSummarySid(CommonLogic.getIntegerValue(0, objects));
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setBalSummaryAccount(objects[2] != null ? (String) objects[2] : GlobalConstants.getSelectOne());
            data.setBalSummaryPipelineAccrualAdjustmentTypeDesc(CommonLogic.getStringValue(3, objects));
            data.setBalSummaryDemandAccrualAdjustmentTypeDesc(CommonLogic.getStringValue(4, objects));
            data.setBalSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc(CommonLogic.getStringValue(5, objects));
            data.setBalSummaryDemandReforecastAdjustmentTypeDesc(CommonLogic.getStringValue(6, objects));
            data.setBalSummaryPaymentTrueupAdjustmentTypeDesc(CommonLogic.getStringValue(7, objects));
            data.setBalSummaryDemandTrueupAdjustmentTypeDesc(CommonLogic.getStringValue(7, objects));
            data.setBalSummaryFeesAccrualAdjustmentTypeDesc(CommonLogic.getStringValue(8, objects));
            data.setBalSummaryInflationAdjustmentAdjustmentTypeDesc(CommonLogic.getStringValue(9, objects));
            data.setBalSummaryCreditCardFeesAdjustmentTypeDesc(CommonLogic.getStringValue(10, objects));
            data.setBalSummaryOtherFixedDollarFeesAdjustmentTypeDesc(CommonLogic.getStringValue(11, objects));
            data.setBalSummaryInventoryValuationAdjustmentTypeDesc(CommonLogic.getStringValue(12, objects));
            data.setBalSummaryReturnReserveAdjustmentTypeDesc(CommonLogic.getStringValue(13, objects));
            data.setVersionNo(CommonLogic.getStringValue(14, objects));
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
            values = StringUtils.EMPTY + ARMUtils.SINGLE_QUOTES + value + ARMUtils.SINGLE_QUOTES;
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

    private void joinHelperTable(List inputList, Set<Container.Filter> filters) {
        StringBuilder joinQuery = new StringBuilder("");
        int count = 0;
        for (Container.Filter filter : filters) {
            if (filter instanceof SimpleStringFilter) {
                SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                String stringFilters = stringFilter.getPropertyId().toString();
                if (!stringFilters.equals("balSummaryAccount")) {
                    joinQuery.append("LEFT JOIN dbo.ARM_ADJUSTMENT_CONFIG CU").append(count).append("  ON  CU");
                    joinQuery.append(count).append(".ARM_ADJUSTMENT_CONFIG_SID =");
                    if (ARMUtils.getBalanceSummaryVisibleToDBColumnMap().get(stringFilters) != null && !ARMUtils.getBalanceSummaryVisibleToDBColumnMap().get(stringFilters).isEmpty()) {
                        joinQuery.append("A.").append(ARMUtils.getBalanceSummaryVisibleToDBColumnMap().get(stringFilters)).append(ARMUtils.SPACE);
                    }
                    count++;
                }
            }
        }
        inputList.add(joinQuery.toString());
    }

}
