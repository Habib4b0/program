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
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdjustmentSummaryConfigLogic {

    private static final AdjustmentSummaryConfigLogic logic = new AdjustmentSummaryConfigLogic();
    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummaryConfigLogic.class);

    private AdjustmentSummaryConfigLogic() {

    }

    public static AdjustmentSummaryConfigLogic getInstance() {
        return logic;

    }

    /**
     * This is the method to get the count for AdjustmentSummaryConfig table
     *
     * @param addedFilters
     * @return
     */
    public int getAdjSummaryConfigurationCount(final ReserveSelection selection, final Set<Container.Filter> addedFilters) {
        List inputList = getAdjSummaryInputList(selection, addedFilters);
        List<Object[]> resultList = QueryUtils.getItemData(inputList, "selectAdjSummary_BasedOn_Methodology_COUNT", null);
        inputList.clear();
        return CommonLogic.getCount(resultList);
    }

    /**
     * *
     * This is the method to get the count for AdjustmentSummaryConfig table
     *
     * @param start
     * @param end
     * @param addedFilters
     * @param sortByColumns
     * @return
     */
    public List<AdjustmentReserveDTO> getAdjSummaryConfigurationData(final ReserveSelection selection, int start, int end, final Set<Container.Filter> addedFilters, List<SortByColumn> sortByColumns, boolean isExcel) {
        LOGGER.debug("Enter getAdjSummaryConfigurationData");
        List inputList = getAdjSummaryInputList(selection, addedFilters);
        StringBuilder orderByQuery = CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, ARMUtils.getAdjustmentSummaryVisibleToDBColumnMapForFilter(), " SUBSTRING(VERSION_NO,1,1),LEN(VERSION_NO)-LEN(REPLACE(VERSION_NO,'.',''))");
        inputList.add(orderByQuery);
        inputList.add(start);
        inputList.add(end);
        List<Object[]> resultList = QueryUtils.getItemData(inputList, !isExcel ? "selectAdjSummary_BasedOn_Methodology_DATA" : "selectAdjSummary_BasedOn_Methodology_Excel", null);
        inputList.clear();
        return isExcel ? customizingExcelDBData(resultList) : customizingDBData(resultList);
    }

    /**
     * This is the input method to the query of loading grid in adjustment
     * summary tab
     *
     * @param selection
     * @return List of inputs
     */
    private List getAdjSummaryInputList(ReserveSelection selection, final Set<Container.Filter> addedFilters) {
        List inputList = new ArrayList(3);
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getMethodology());
        StringBuilder sql = CommonFilterLogic.getInstance().filterQueryGenerator(addedFilters, ARMUtils.getAdjustmentSummaryVisibleToDBColumnMapForFilter());
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
            data.setAdjustmentSummarySid(CommonLogic.getIntegerValue(0, objects));
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setAdjSummaryAdjustmentType(objects[2] != null && !isAddedLineAdjustmentType(objects[12]) ? (String) objects[2] : StringUtils.EMPTY);
            data.setAdjSummaryPipeLineAccrual(CommonLogic.getIntegerValue(3, objects));
            data.setAdjSummaryPipeLineAccrualAccountValue(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryCurrentBalance(CommonLogic.getIntegerValue(3, objects));
            data.setAdjSummaryCurrentBalanceAccountValue(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryCurrentPipleLine(CommonLogic.getIntegerValue(3, objects));
            data.setAdjSummaryCurrentPipleLineAccountValue(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryDemandAccrual(CommonLogic.getIntegerValue(5, objects));
            data.setAdjSummaryDemandAccrualAccountValue(CommonLogic.getStringValue(6, objects));
            data.setAdjSummaryDemandForecast(CommonLogic.getIntegerValue(7, objects));
            data.setAdjSummaryDemandForecastAccountValue(CommonLogic.getStringValue(8, objects));
            data.setAdjSummarydemandPaymentRecon(CommonLogic.getIntegerValue(9, objects));
            data.setAdjSummarydemandPaymentReconAccountValue(CommonLogic.getStringValue(10, objects));
            data.setMethodology(CommonLogic.getIntegerValue(11, objects));
            data.setVersionNo(CommonLogic.getStringValue(12, objects));
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
            data.setAdjustmentSummarySid(CommonLogic.getIntegerValue(0, objects));
            data.setCheckRecord(objects[1] == null ? Boolean.FALSE : (Boolean) objects[1]);
            data.setAdjSummaryAdjustmentType(objects[2] != null && !isAddedLineAdjustmentType(objects[12]) ? (String) objects[2] : StringUtils.EMPTY);
            data.setAdjSummaryPipeLineAccrualDesc(CommonLogic.getStringValue(3, objects));
            data.setAdjSummaryPipeLineAccrualAccount(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryCurrentBalanceDesc(CommonLogic.getStringValue(3, objects));
            data.setAdjSummaryCurrentBalanceAccount(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryCurrentPipleLineDesc(CommonLogic.getStringValue(3, objects));
            data.setAdjSummaryCurrentPipleLineAccount(CommonLogic.getStringValue(4, objects));
            data.setAdjSummaryDemandAccrualDesc(CommonLogic.getStringValue(5, objects));
            data.setAdjSummaryDemandAccrualAccount(CommonLogic.getStringValue(6, objects));
            data.setAdjSummaryDemandForecastDesc(CommonLogic.getStringValue(7, objects));
            data.setAdjSummaryDemandForecastAccount(CommonLogic.getStringValue(8, objects));
            data.setAdjSummarydemandPaymentReconDesc(CommonLogic.getStringValue(9, objects));
            data.setAdjSummarydemandPaymentReconAccount(CommonLogic.getStringValue(10, objects));
            data.setMethodology(CommonLogic.getIntegerValue(11, objects));
            data.setVersionNo(CommonLogic.getStringValue(12, objects));
            dataList.add(data);
        }
        return dataList;
    }

    /**
     *
     * InsertRecord to temp table based on selection
     *
     */
    public void insertAdjSummaryToTempTable(ReserveSelection selection) {
        if (isSummaryRecordsAvailableForMethodolody(selection)) {
            List inputList = new ArrayList();
            inputList.add(selection.getMethodology());
            inputList.add(selection.getAdjustmentSummaryTempTableName());
            inputList.add(selection.getTempTableName());
            QueryUtils.itemUpdate(inputList, "insertAdjSummaryToTempTable");
            inputList.clear();
        }

    }

    /**
     * checking already inserted
     *
     * @return
     */
    private Boolean isSummaryRecordsAvailableForMethodolody(ReserveSelection selection) {
        List inputList = new ArrayList();
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getMethodology());
        Boolean isRecordAvailable = CommonLogic.getCount(QueryUtils.getItemData(inputList, "isSummaryRecordsAvailableForMethodolody", null)) == 0;
        inputList.clear();
        return isRecordAvailable;
    }

    /**
     * Method to remove a row when the user clicks remvoe line at 2nd tab
     *
     * @param selection
     */
    public void deleteDataFromDB(ReserveSelection selection) {
        List input = BalanceSummaryLogic.getInstance().commonInputForSummaryTabs(selection);
        QueryUtils.itemUpdate(input, "removeLine_Summary_Temp_TableData");
    }

    /**
     * The session input to query from deleteDataFromDB() method.
     *
     * @param selection
     * @return List of
     */
    /**
     * Method to update the table values when it is been changed.
     *
     * @param dto
     * @param value
     * @param column
     * @param tempTableName
     */
    public void updateTableValues(AdjustmentReserveDTO dto, Object value, String column, String tempTableName) {
        List inputList = new ArrayList<>(4);
        inputList.add(tempTableName);
        inputList.add(column);
        String values = null;
        if (value != null) {
            values = ARMUtils.SINGLE_QUOTES + value.toString() + ARMUtils.SINGLE_QUOTES;
        }
        inputList.add(values);
        inputList.add(dto.getAdjustmentSummarySid());
        QueryUtils.itemUpdate(inputList, "update_Adjustment_Summary_Temp_TableData");
    }

    /**
     * Method to add a new line in the adjustment summary tab
     *
     * @param selection
     */
    public void addLineLogic(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getMethodology());
        QueryUtils.itemUpdate(inputList, "addLine_Adjustment_Summary_Temp_TableData");
    }

    /**
     * Method to check whether the line is cloned by added line or loaded from
     * Config tab
     *
     * @param obj
     * @return
     */
    private Boolean isAddedLineAdjustmentType(Object obj) {
        if (obj != null && String.valueOf(obj).contains(".")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * This is the method to insert data from config tab to Adjustment summary
     * tab
     *
     * @param listInput
     * @return
     */
    public Boolean mergeAdjSummaryDataFromConfigureTab(final List listInput) {
        return QueryUtils.itemUpdate(listInput, "Merge_Insert_To_AdjustmentTemp_From_ConfigDetails");
    }

    /**
     * Save Temp to main adjustment summary data
     *
     * @param selection
     * @throws Exception
     */
    public void saveTempToMainAdjSummaryData(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        QueryUtils.itemUpdate(inputList, "saveTempToMainAdjSummaryData");
    }

    /**
     * insertAdjSummaryToTempTableFromMainTable
     *
     * @param selection
     */
    public void insertAdjSummaryToTempTableFromMainTable(ReserveSelection selection) {
        List inputList = new ArrayList<>();
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        if (selection.isResetLine()) {
            inputList.add("AND METHODOLOGY =" + selection.getMethodology());
            selection.setResetLine(false);
        } else {
            inputList.add(StringUtils.EMPTY);
        }

        QueryUtils.itemUpdate(inputList, "insertAdjSummaryToTempTableFromMainTable");
    }

    /**
     * resetAdjSummary
     *
     * @param selection
     */
    public void resetAdjSummary(ReserveSelection selection) {
        List input = new ArrayList<>();
        input.add(selection.getAdjustmentSummaryTempTableName());
        input.add(selection.getMethodology());
        QueryUtils.itemUpdate(input, "AdjustmentSummary_DeleteAddedLines");
    }

    /**
     * resetAdjSummary
     *
     * @param selection
     */
    public void deleteTempTableRecords(ReserveSelection selection) {
        List input = new ArrayList<>();
        input.add(selection.getAdjustmentSummaryTempTableName());
        input.add(selection.getMethodology());
        QueryUtils.itemUpdate(input, "deleteTempTableRecords");
    }

    /**
     * This is the method to update the reserve master sid based on the
     * combination changes in copy mode
     *
     */
    public void updateReserveMasterSid(ReserveSelection selection) {
        List inputList = new ArrayList<>(2);
        inputList.add(selection.getAdjustmentSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReserveMasterSid());
        QueryUtils.itemUpdate(inputList, "updateReserveMasterSid");
        inputList.clear();
    }

    /**
     * Logic to update check record column to 0 or 1 based on check all
     * selection
     *
     * @param checked
     * @param selection
     */
    public void updateAllCheckRecord(boolean checked, ReserveSelection selection) {
        List inputList = new ArrayList();
        inputList.add(checked ? 1 : 0);
        inputList.addAll(BalanceSummaryLogic.getInstance().commonInputForSummaryTabs(selection));
        QueryUtils.itemUpdate(inputList, "Update_all_check_record_Summary");
        inputList.clear();
    }

    /**
     * Is a method to check whethere all the check boxes are chcekd to make the
     * column header is checked/unchecked
     *
     * @param selection
     * @return List
     */
    public List isAllCheckBoxesAreChecked(ReserveSelection selection) {
        List inputList = BalanceSummaryLogic.getInstance().commonInputForSummaryTabs(selection);
        List returnList = QueryUtils.getItemData(inputList, "is_All_Records_Are_Checked", null);
        inputList.clear();
        return returnList;
    }

    /**
     * This is the input method to get Two inputs which we will through out
     * everywhere
     *
     * @param selection
     * @return
     */
    public List getSessionMethodologyInput(final ReserveSelection selection) {
        List inputList = new ArrayList<>(2);
        inputList.add(selection.getBalanceSummaryTempTableName());
        inputList.add(selection.getReportType());
        return inputList;

    }
}
