
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.arm.abstractforms.AbstractFilter;
import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.arm.utils.ReserveSelection;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.SysDataSourceConnection;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.filter.SimpleStringFilter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.ArrayUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 
 */
public class AdjustmentReserveLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentReserveLogic.class);

    private static final AdjustmentReserveLogic logic = new AdjustmentReserveLogic();

    private AdjustmentReserveLogic() {

    }

    public static AdjustmentReserveLogic getInstance() {
        return logic;

    }

    /**
     * This method is used to get count of the record that need to be loaded.
     *
     * @param selection
     * @return count of data to loaded
     */
    public int getReserveEditCount(ReserveSelection selection, Set<Container.Filter> filter) {
        List input = new ArrayList();
        String queryName;
        if (filter != null && !filter.isEmpty()) {
            joinHelperTable(input, filter);
        } else {
            input.add(StringUtils.EMPTY);
        }
        if (!selection.isIsCurrent()) {
            input.add(selection.getMasterSID());
            StringBuilder sql = AbstractFilter.getInstance().getFilterForReserves(filter);
            if (sql == null) {
                input.add(StringUtils.EMPTY);

            } else {
                input.add(sql);
            }
            queryName = "Load_Reserve_Details_count_Hist";
        } else if (selection.isIsViewMode()) {
            input.add(selection.getMasterSID());
            StringBuilder sql = AbstractFilter.getInstance().getFilterForReserves(filter);
            if (sql == null) {
                input.add(StringUtils.EMPTY);

            } else {
                input.add(sql);
            }
            queryName = "Load_Reserve_Details_count_For_view";
        } else {
            input = getInputForLoadData(selection, filter);
            queryName = "Load_Reserve_Details_count";
        }

        return CommonLogic.getCount(QueryUtils.getItemData(input, queryName, null));
    }

    /**
     * This method is used to load the data from DB
     *
     * @param selection
     * @param start
     * @param offset
     * @param filter
     * @param sortByColumns
     * @return list data to loaded.
     */
    public List getReserveData(ReserveSelection selection, int start, int offset, Set<Container.Filter> filter, List<SortByColumn> sortByColumns) {
        List input = new ArrayList();
        if (selection.isIsViewMode()) {
            getInputs(filter, input, selection, start, offset);
            return getCustomizedReserveGtn(selection, sortByColumns, input);
        } else if (selection.isIsCurrent()) {
            input = getInputForLoadData(selection, filter);
            input.add(start);
            input.add(offset);
            return getCustomizedResGtn(selection, sortByColumns, input);
        } else {
            getInputs(filter, input, selection, start, offset);
            return getHistAdjResAndGtn(selection, sortByColumns, input);
        }
    }

    private void getInputs(Set<Container.Filter> filter, List input, ReserveSelection selection, int start, int offset) {
        if (filter != null && !filter.isEmpty()) {
            joinHelperTable(input, filter);
        } else {
            input.add(StringUtils.EMPTY);
        }
        input.add(selection.getMasterSID());
        StringBuilder sql = AbstractFilter.getInstance().getFilterForReserves(filter);
        if (sql == null) {
            input.add(StringUtils.EMPTY);
        } else {
            input.add(sql);
        }
        input.add(start);
        input.add(offset);
    }

    private List getHistAdjResAndGtn(ReserveSelection selection, List<SortByColumn> sortByColumns, List input) {
        if (selection.isIsGTNDetails()) {
            forARCSortingView(NumericConstants.FOUR, sortByColumns, input);
            return customizeGTN(QueryUtils.getItemData(input, "Load_GTN_Details_Data_For_Hist", null), selection);
        } else {
            forARCSortingView(NumericConstants.FOUR, sortByColumns, input);
            return customizeReserve(QueryUtils.getItemData(input, "Load_Reserve_Details_Data_For_Hist", null), selection);
        }
    }

    private List getCustomizedResGtn(ReserveSelection selection, List<SortByColumn> sortByColumns, List input) {
        if (selection.isIsGTNDetails()) {
            forARCSorting(NumericConstants.FIVE, sortByColumns, input);
            return customizeGTN(QueryUtils.getItemData(input, "Load_GTN_Details_Data", null), selection);
        } else {
            forARCSorting(NumericConstants.FIVE, sortByColumns, input);
            return customizeReserve(QueryUtils.getItemData(input, "Load_Reserve_Details_Data", null), selection);
        }
    }

    private List getCustomizedReserveGtn(ReserveSelection selection, List<SortByColumn> sortByColumns, List input) {
        if (selection.isIsCurrent()) {
            if (selection.isIsGTNDetails()) {
                forARCSortingView(NumericConstants.FOUR, sortByColumns, input);
                return customizeGTN(QueryUtils.getItemData(input, "Load_GTN_Details_Data_For_view", null), selection);
            } else {
                forARCSortingView(NumericConstants.FOUR, sortByColumns, input);
                return customizeReserve(QueryUtils.getItemData(input, "Load_Reserve_Details_Data_For_view", null), selection);
            }
        } else {
            return getHistAdjResAndGtn(selection, sortByColumns, input);
        }
    }

    /**
     * This method is used to Update the table values to DB tables.
     *
     * @param value
     * @param propertyId
     * @param itemId
     */
    public void updateTableValues(Object value, Object propertyId, Object itemId, ReserveSelection selection) {
        List input = getInputForUpdateValues((AdjustmentReserveDTO) itemId, value, propertyId, selection);
        QueryUtils.itemUpdate(input, "Update_values_To_tempTable");
    }

    /**
     * This method is used to add the line into db tables
     *
     * @param selection
     * @return
     */
    public int addLineLogic(ReserveSelection selection) {
        List input1 = getInputForAddLine(selection);
        List dataList1 = QueryUtils.getItemData(input1, "ADD_Line_to_Temp_table", null);
        if (!dataList1.isEmpty()) {
            return ARMUtils.getIntegerValue(String.valueOf(dataList1.get(0)));
        } else {
            return 0;
        }
    }

    /**
     * Used to get DB input for inserting line.
     *
     * @return input List
     */
    private List getInputForAddLineForMasterTable(ReserveSelection selection, int confiType) {
        List input = new ArrayList();
        input.add(selection.getWindowBinderDTO().getCompanyDdlbRes());
        input.add(selection.getWindowBinderDTO().getBusinessDdlbRes());
        input.add(selection.getWindowBinderDTO().getDeductionCategoryDdlbRes());
        input.add(selection.getWindowBinderDTO().getDeductionTypeDdlbRes());
        input.add(selection.getWindowBinderDTO().getDeductionProgramDdlbRes());
        input.add(confiType);
        input.add(selection.getSession().getUserId());
        input.add(ARMUtils.DB_CURRENT_DATE);
        input.add(selection.getSession().getUserId());
        input.add(selection.getSession().getSessionId());
        return input;
    }

    private List getInputForAddLine(ReserveSelection selection) {
        List input = new ArrayList<>(NumericConstants.THREE);
        input.add(selection.getTempTableName());
        input.add(selection.getMasterSID());
        input.add(selection.getSession().getUserId());
        return input;
    }

    private List getInputForUpdateValues(AdjustmentReserveDTO dto, Object value, Object propertyId, ReserveSelection selection) {
        List input = new ArrayList(NumericConstants.FOUR);
        input.add(selection.getTempTableName());
        input.add(ARMUtils.getVisibleToDBColumnMap().get(propertyId.toString()));
        String values = null;
        if (value != null) {
            if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.COST_CENTER.getConstant().equals(propertyId) && value.toString().trim().equals(StringUtils.EMPTY)) {
                values = "null";
            } else {
                values = ARMUtils.SINGLE_QUOTES + value.toString() + ARMUtils.SINGLE_QUOTES;
            }
        }
        input.add(values);
        input.add(dto.getDetailsId());
        return input;
    }

    public int addLineForMaster(ReserveSelection selection, int isGTN) {
        List input = getInputForAddLineForMasterTable(selection, isGTN);
        List dataList = QueryUtils.getItemData(input, "Add_Line_to_Temp_For_Master_Table", null);
        if (dataList.isEmpty()) {
            return 0;
        }
        return ARMUtils.getIntegerValue(String.valueOf(dataList.get(0)));
    }

    private List getInputForLoadData(ReserveSelection selection, Set<Container.Filter> filter) {
        List dataInput = new ArrayList(NumericConstants.THREE);
        dataInput.add(selection.getTempTableName());
        if (filter != null && !filter.isEmpty()) {
            joinHelperTable(dataInput, filter);
        } else {
            dataInput.add(StringUtils.EMPTY);
        }
        dataInput.add(selection.getMasterSID());
        StringBuilder sql = AbstractFilter.getInstance().getFilterForReserves(filter);
        if (sql == null) {
            dataInput.add(StringUtils.EMPTY);

        } else {
            dataInput.add(sql);
        }
        return dataInput;
    }

    private List customizeReserve(List<Object[]> itemData, ReserveSelection selection) {
        List<AdjustmentReserveDTO> finalResult = new ArrayList<>();
        for (Object[] str : itemData) {
            AdjustmentReserveDTO reserveDto = new AdjustmentReserveDTO();
            reserveDto.setDetailsId(CommonLogic.getIntegerValue(NumericConstants.ZERO, str));
            reserveDto.setAdjustmentType(CommonLogic.getIntegerValue(NumericConstants.ONE, str));
            reserveDto.setAdjustmentLevel(CommonLogic.getIntegerValue(NumericConstants.TWO, str));
            reserveDto.setAccountCategory(CommonLogic.getIntegerValue(NumericConstants.THREE, str));
            reserveDto.setAccountType(CommonLogic.getIntegerValue(NumericConstants.FOUR, str));
            reserveDto.setAccount(CommonLogic.getStringValue(NumericConstants.FIVE, str));
            reserveDto.setAccountDescription(CommonLogic.getStringValue(NumericConstants.SIX, str));
            reserveDto.setAccountIndictor(CommonLogic.getIntegerValue(NumericConstants.SEVEN, str));
            reserveDto.setCostCenter(CommonLogic.getStringValue(NumericConstants.EIGHT, str));
            reserveDto.setProject(CommonLogic.getStringValue(NumericConstants.NINE, str));
            reserveDto.setFuture1(CommonLogic.getStringValue(NumericConstants.TEN, str));
            reserveDto.setFuture2(CommonLogic.getStringValue(NumericConstants.ELEVEN, str));
            reserveDto.setBalanceType(CommonLogic.getStringValue(NumericConstants.TWELVE, str));
            reserveDto.setDatabase(CommonLogic.getStringValue(NumericConstants.THIRTEEN, str));
            reserveDto.setDataAccessSet(CommonLogic.getStringValue(NumericConstants.FOURTEEN, str));
            reserveDto.setChartOfAccounts(CommonLogic.getStringValue(NumericConstants.FIFTEEN, str));
            reserveDto.setLedger(CommonLogic.getStringValue(NumericConstants.SIXTEEN, str));
            reserveDto.setCategory(CommonLogic.getStringValue(NumericConstants.SEVENTEEN, str));
            reserveDto.setSource(CommonLogic.getStringValue(NumericConstants.EIGHTEEN, str));
            reserveDto.setCurrency(CommonLogic.getIntegerValue(NumericConstants.NINETEEN, str));
            reserveDto.setJournalName(CommonLogic.getStringValue(NumericConstants.TWENTY, str));
            reserveDto.setJournalDescription(CommonLogic.getStringValue(NumericConstants.TWENTY_ONE, str));
            reserveDto.setReverseJournal(CommonLogic.getStringValue(NumericConstants.TWENTY_TWO, str));
            reserveDto.setReversalPeriodDate(CommonLogic.getIntegerValue(NumericConstants.TWENTY_THREE, str));
            reserveDto.setLineDescription(CommonLogic.getStringValue(NumericConstants.TWENTY_FOUR, str));
            reserveDto.setUdc1(CommonLogic.getIntegerValue(NumericConstants.TWENTY_FIVE, str));
            reserveDto.setUdc2(CommonLogic.getIntegerValue(NumericConstants.TWENTY_SIX, str));
            reserveDto.setUdc3(CommonLogic.getIntegerValue(NumericConstants.TWENTY_SEVEN, str));
            reserveDto.setUdc4(CommonLogic.getIntegerValue(NumericConstants.TWENTY_EIGHT, str));
            reserveDto.setUdc5(CommonLogic.getIntegerValue(NumericConstants.TWENTY_NINE, str));
            reserveDto.setUdc6(CommonLogic.getIntegerValue(NumericConstants.THIRTY, str));
            reserveDto.setCheckRecord(str[NumericConstants.THIRTY_ONE] == null ? Boolean.FALSE : (Boolean) str[NumericConstants.THIRTY_ONE]);
            customizeReserveIndicators(str, reserveDto, selection);
            reserveDto.setBusinessUnit(selection.getBusUnit() == null ? StringUtils.EMPTY : selection.getBusUnit());
            reserveDto.setAdjustmentTypestr(CommonLogic.getComboDes(reserveDto.getAdjustmentType(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant()))); // Added for GAL-5382
            reserveDto.setAdjustmentLevelstr(CommonLogic.getComboDes(reserveDto.getAdjustmentLevel(), "ARM_RES_ADJUSTMENT_LEVEL"));
            reserveDto.setAccountCategorystr(CommonLogic.getComboDes(reserveDto.getAccountCategory(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant())));
            reserveDto.setAccountTypestr(CommonLogic.getComboDes(reserveDto.getAccountType(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant())));
            reserveDto.setAccountIndictorstr(CommonLogic.getComboDes(reserveDto.getAccountIndictor(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant())));
            reserveDto.setCurrencystr(CommonLogic.getComboDes(reserveDto.getCurrency(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant())));
            reserveDto.setReversalPeriodDatestr(CommonLogic.getComboDes(reserveDto.getReversalPeriodDate(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant())));
            reserveDto.setDebitIndicatorstr(CommonLogic.getComboDes(reserveDto.getDebitIndicator(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.DEBIT_INDICATOR.getConstant())));
            reserveDto.setCreditIndicatorstr(CommonLogic.getComboDes(reserveDto.getCreditIndicator(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CREDIT_INDICATOR.getConstant())));
            reserveDto.setReportIndicatorStr(CommonLogic.getComboDes(reserveDto.getReportIndicator(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REPORT_INDICATOR.getConstant())));
            reserveDto.setUdc1Str(CommonLogic.getComboDes(reserveDto.getUdc1(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant())));
            reserveDto.setUdc2Str(CommonLogic.getComboDes(reserveDto.getUdc2(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant())));
            reserveDto.setUdc3Str(CommonLogic.getComboDes(reserveDto.getUdc3(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant())));
            reserveDto.setUdc4Str(CommonLogic.getComboDes(reserveDto.getUdc4(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant())));
            reserveDto.setUdc5Str(CommonLogic.getComboDes(reserveDto.getUdc5(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant())));
            reserveDto.setUdc6Str(CommonLogic.getComboDes(reserveDto.getUdc6(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant())));//Ends here

            finalResult.add(reserveDto);
        }
        return finalResult;
    }

    private void customizeReserveIndicators(Object[] str, AdjustmentReserveDTO dto, ReserveSelection selection) {
        if (str[NumericConstants.THIRTY_THREE] == null) {
            dto.setDebitIndicator(0);
        } else {
            dto.setDebitIndicator((boolean) str[NumericConstants.THIRTY_THREE] ? 1 : -1);
        }
        if (str[NumericConstants.THIRTY_TWO] == null) {
            dto.setCreditIndicator(0);
        } else {
            dto.setCreditIndicator((boolean) str[NumericConstants.THIRTY_TWO] ? 1 : -1);
        }

        dto.setCompanyNo(selection.getCompanyNo() == null ? StringUtils.EMPTY : selection.getCompanyNo());
        dto.setDivision(str[NumericConstants.THIRTY_FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THIRTY_FOUR]));
        if (str[NumericConstants.THIRTY_FIVE] == null) {
            dto.setReportIndicator(0);
        } else {
            dto.setReportIndicator((boolean) str[NumericConstants.THIRTY_FIVE] ? 1 : -1);
        }
    }

    private List customizeGTN(List<Object[]> itemData, ReserveSelection selection) {
        List<AdjustmentReserveDTO> finalResult = new ArrayList<>();
        for (Object[] str : itemData) {
            AdjustmentReserveDTO dto = new AdjustmentReserveDTO();
            dto.setDetailsId(CommonLogic.getIntegerValue(0, str));
            dto.setAdjustmentType(CommonLogic.getIntegerValue(NumericConstants.ONE, str));
            dto.setAdjustmentLevel(CommonLogic.getIntegerValue(NumericConstants.TWO, str));
            dto.setAccountCategory(CommonLogic.getIntegerValue(NumericConstants.THREE, str));
            dto.setAccountType(CommonLogic.getIntegerValue(NumericConstants.FOUR, str));
            dto.setAccount(CommonLogic.getStringValue(NumericConstants.FIVE, str));
            dto.setAccountDescription(CommonLogic.getStringValue(NumericConstants.SIX, str));
            dto.setAccountIndictor(CommonLogic.getIntegerValue(NumericConstants.SEVEN, str));
            dto.setCostCenter(CommonLogic.getStringValue(NumericConstants.EIGHT, str));
            dto.setProject(CommonLogic.getStringValue(NumericConstants.NINE, str));
            dto.setFuture1(CommonLogic.getStringValue(NumericConstants.TEN, str));
            dto.setFuture2(CommonLogic.getStringValue(NumericConstants.ELEVEN, str));
            dto.setUdc1(CommonLogic.getIntegerValue(NumericConstants.TWELVE, str));
            dto.setUdc2(CommonLogic.getIntegerValue(NumericConstants.THIRTEEN, str));
            dto.setUdc3(CommonLogic.getIntegerValue(NumericConstants.FOURTEEN, str));
            dto.setUdc4(CommonLogic.getIntegerValue(NumericConstants.FIFTEEN, str));
            dto.setUdc5(CommonLogic.getIntegerValue(NumericConstants.SIXTEEN, str));
            dto.setUdc6(CommonLogic.getIntegerValue(NumericConstants.SEVENTEEN, str));
            dto.setCheckRecord(str[NumericConstants.EIGHTEEN] == null ? Boolean.FALSE : (Boolean) str[NumericConstants.EIGHTEEN]);
            dto.setCompanyNo(selection.getCompanyNo() == null ? StringUtils.EMPTY : selection.getCompanyNo());
            dto.setDivision(CommonLogic.getStringValue(NumericConstants.NINETEEN, str));
            dto.setBusinessUnit(selection.getBusUnit() == null ? StringUtils.EMPTY : selection.getBusUnit());
            dto.setAdjustmentTypestr(CommonLogic.getComboDes(dto.getAdjustmentType(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.getConstant())));
            dto.setAdjustmentLevelstr(CommonLogic.getComboDes(dto.getAdjustmentLevel(), "ARM_GTN_ADJUSTMENT_LEVEL"));
            dto.setAccountCategorystr(CommonLogic.getComboDes(dto.getAccountCategory(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_CATEGORY.getConstant())));
            dto.setAccountTypestr(CommonLogic.getComboDes(dto.getAccountType(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNT_TYPE.getConstant())));
            dto.setAccountIndictorstr(CommonLogic.getComboDes(dto.getAccountIndictor(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ACCOUNTINDICTOR.getConstant())));
            dto.setCurrencystr(CommonLogic.getComboDes(dto.getCurrency(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.CURRENCY.getConstant())));
            dto.setReversalPeriodDatestr(CommonLogic.getComboDes(dto.getReversalPeriodDate(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.REVERSAL_PERIOD_DATE.getConstant())));

            //** This map need to changeed
            dto.setUdc1Str(CommonLogic.getComboDes(dto.getUdc1(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC1.getConstant())));
            dto.setUdc2Str(CommonLogic.getComboDes(dto.getUdc2(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC2.getConstant())));
            dto.setUdc3Str(CommonLogic.getComboDes(dto.getUdc3(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC3.getConstant())));
            dto.setUdc4Str(CommonLogic.getComboDes(dto.getUdc4(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC4.getConstant())));
            dto.setUdc5Str(CommonLogic.getComboDes(dto.getUdc5(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC5.getConstant())));
            dto.setUdc6Str(CommonLogic.getComboDes(dto.getUdc6(), ARMUtils.getDropDownMap().get(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.UDC6.getConstant())));

            finalResult.add(dto);
        }
        return finalResult;
    }

    public void massUpdateValue(Object value, ReserveSelection selection, Object properyId) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(ARMUtils.getVisibleToDBColumnMap().get(properyId));
        String values = null;
        if (value != null) {
            values = ARMUtils.SINGLE_QUOTES + value.toString() + ARMUtils.SINGLE_QUOTES;
        }
        input.add(values);
        input.add(selection.getMasterSID());
        QueryUtils.itemUpdate(input, "MassUpdate_reserve");

    }

    public void deleteDataFromDB(ReserveSelection selection) {
        List input = getSessionInput(selection);
        QueryUtils.itemUpdate(input, "Delete_row_query");
    }

    public void tempToMainSaveLogic(ReserveSelection selection, AdjustmentReserveDTO binderDto) {
        mainTableLogic(selection, binderDto);
        List input = new ArrayList(NumericConstants.TWO);
        input.add(selection.getTempTableName());
        QueryUtils.itemUpdate(input, "Main_save_query");
    }

    public void copyLineInDB(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(selection.getTempTableName());
        input.add(selection.getMasterSID());
        QueryUtils.itemUpdate(input, "CopyLine_query");
    }

    private List getSessionInput(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(selection.getMasterSID());
        return input;
    }

    public void resetDBRecord(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        List masterSids = new ArrayList();
        masterSids.add(selection.getGtnDetailsMasterSid());
        masterSids.add(selection.getReserveMasterSid());
        input.add(CommonLogic.stringListToString(masterSids));
        QueryUtils.itemUpdate(input, "Reset_table_in_DB");
    }

    public boolean updateMasterTable(ReserveSelection selection, AdjustmentReserveDTO binderDto) {
        List input = getBinderInput(binderDto);
        input.add(selection.getMasterSID());
        return QueryUtils.itemUpdate(input, "Update_data_in_MasterTable");

    }

    public boolean madatoryCheckReserveANDDetails(ReserveSelection selection, String reserveQuery, String gtnquery) {

        Boolean reserveInput = null;
        Boolean detailsInput = null;
        if (selection.getReserveMasterSid() != 0) {
            List reserveList = new ArrayList(NumericConstants.THREE);
            reserveList.add(selection.getReserveMasterSid());
            reserveList.add(selection.getTempTableName());
            reserveList.add(selection.getTempTableName());
            reserveInput = CommonLogic.getCountValue(QueryUtils.getItemData(reserveList, reserveQuery, null));
            reserveList.clear();
        }
        if (selection.getGtnDetailsMasterSid() != 0) {
            List gtnList = new ArrayList(NumericConstants.THREE);
            gtnList.add(selection.getGtnDetailsMasterSid());
            gtnList.add(selection.getTempTableName());
            gtnList.add(selection.getTempTableName());
            detailsInput = CommonLogic.getCountValue(QueryUtils.getItemData(gtnList, gtnquery, null));
            gtnList.clear();
        }

        if (reserveInput != null && detailsInput != null) {
            return reserveInput && detailsInput;
        } else if (reserveInput != null) {
            return reserveInput && detailsInput == null;
        } else if (detailsInput != null) {
            return detailsInput && reserveInput == null;
        }
        return false;
    }

    public boolean madatoryForAtleastOneRecordtosave(ReserveSelection selection, String reserveandGtnQuery) {
        Boolean reserveInput = Boolean.FALSE;
        if (selection.getReserveMasterSid() != 0) {
            List reserveList = new ArrayList(NumericConstants.TWO);
            reserveList.add(selection.getTempTableName());
            reserveList.add(selection.getReserveMasterSid());
            reserveInput = CommonLogic.getCountValueForSave(QueryUtils.getItemData(reserveList, reserveandGtnQuery, null));
            reserveList.clear();
        }
        return reserveInput;
    }

    public boolean madatoryCheckCreditDebit(ReserveSelection selection, String reserveQuery) {

        Boolean reserveInput = null;
        if (selection.getReserveMasterSid() != 0) {
            List input = getReserveInput(selection);
            reserveInput = CommonLogic.getCountValue(QueryUtils.getItemData(input, reserveQuery, null));
        }
        return reserveInput;
    }

    public boolean equalCheckCreditDebit(ReserveSelection selection, String reserveQuery) {

        Boolean reserveInput = null;
        if (selection.getReserveMasterSid() != 0) {
            List input = getReserveInput(selection);
            reserveInput = CommonLogic.getduplicateCount(QueryUtils.getItemData(input, reserveQuery, null));
        }
        return reserveInput;
    }

    private List getReserveInput(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(selection.getReserveMasterSid());
        return input;
    }

    public int getSearchCount(AdjustmentReserveDTO binderDto, Set<Container.Filter> filter) {
        List input = getSearchInput(binderDto, filter);
        if (filter.isEmpty()) {
            return CommonLogic.getCount(QueryUtils.getItemData(input, "Reserve_Details_search_count", null));
        } else {
            return CommonLogic.getCount(QueryUtils.getItemData(input, "Reserve_Details_search_count_filter", null));
        }

    }

    public List getSearchResults(AdjustmentReserveDTO binderDto, int start, int offset, Set<Container.Filter> filter, List<SortByColumn> sortByColumns) {
        List input = getSearchInput(binderDto, filter);
        if (!sortByColumns.isEmpty()) {
            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = iterator.next();
                String columnId = orderByColumn.getName();
                Map<String, String> columnDetails = ARMUtils.getVisibleToDBColumnMap();
                if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                    input.add(columnDetails.get(columnId) + " asc");
                } else {
                    input.add(columnDetails.get(columnId) + CommonConstant.DESC);
                }
            }
        } else {
            input.add("ARM_ADJ_RES_CONFIG_MASTER_SID");
        }
        input.add(start);
        input.add(offset);
        if (filter.isEmpty()) {
            return customizeReserveSearch(QueryUtils.getItemData(input, "Reserve_Details_search_Data", null));
        } else {
            return customizeReserveSearch(QueryUtils.getItemData(input, "Reserve_Details_search_Data_Filter", null));
        }
    }

    private List getSearchInput(AdjustmentReserveDTO binderDto, Set<Container.Filter> filter) {
        List input = new ArrayList();
        input.add(binderDto.getCompanyDdlbRes() == 0 ? ARMUtils.CHAR_PERCENT : binderDto.getCompanyDdlbRes());
        input.add(binderDto.getBusinessDdlbRes() == 0 ? ARMUtils.CHAR_PERCENT : binderDto.getBusinessDdlbRes());
        input.add(binderDto.getDeductionCategoryDdlbRes() == 0 ? ARMUtils.CHAR_PERCENT : binderDto.getDeductionCategoryDdlbRes());
        input.add(binderDto.getDeductionTypeDdlbRes() == 0 ? ARMUtils.CHAR_PERCENT : binderDto.getDeductionTypeDdlbRes());
        input.add(binderDto.getDeductionProgramDdlbRes() == 0 ? ARMUtils.CHAR_PERCENT : binderDto.getDeductionProgramDdlbRes());
        if (!filter.isEmpty()) {
            try (Connection con = SysDataSourceConnection.getConnection()) {
                input.add(con.getCatalog());
            } catch (SQLException ex) {
                LOGGER.error("Error in ", ex);
            }
        }
        StringBuilder sql = AbstractFilter.getInstance().getFilterCustomerLookUp(filter);
        if (sql == null) {
            input.add(StringUtils.EMPTY);

        } else {
            input.add(sql);
        }
        return input;
    }

    private List customizeReserveSearch(List<Object[]> itemData) {
        List<AdjustmentReserveDTO> finalResult = new ArrayList<>();
        for (Object[] str : itemData) {
            AdjustmentReserveDTO dto = new AdjustmentReserveDTO();
            customizeReserveSearch1(str, dto);
            dto.setCreatedBy(CommonLogic.getUser(str[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.EIGHT])).getFullName());
            dto.setCreatedDate(str[NumericConstants.NINE] == null ? null : CommonUtils.convertStringToDate(String.valueOf(str[NumericConstants.NINE])));
            dto.setModifiedDate(str[NumericConstants.TEN] == null ? null : CommonUtils.convertStringToDate(String.valueOf(str[NumericConstants.TEN])));
            dto.setSource(CommonLogic.getStringValue(NumericConstants.ELEVEN, str));
            dto.setCompanyDdlbRes(CommonLogic.getIntegerValue(NumericConstants.TWELVE, str));
            dto.setBusinessDdlbRes(CommonLogic.getIntegerValue(NumericConstants.THIRTEEN, str));
            dto.setDeductionCategoryDdlbRes(CommonLogic.getIntegerValue(NumericConstants.FOURTEEN, str));
            dto.setDeductionTypeDdlbRes(CommonLogic.getIntegerValue(NumericConstants.FIFTEEN, str));
            dto.setDeductionProgramDdlbRes(CommonLogic.getIntegerValue(NumericConstants.SIXTEEN, str));
            finalResult.add(dto);
        }
        return finalResult;
    }

    private void customizeReserveSearch1(Object[] str, AdjustmentReserveDTO dto) {
        dto.setSearchMasterSid(str[0] == null ? 0 : ARMUtils.getIntegerValue(String.valueOf(str[0])));
        dto.setCompanyNo(str[NumericConstants.ONE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.ONE]));
        dto.setCompanyName(str[NumericConstants.TWO] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.TWO]));
        dto.setBusinessUnitNo(str[NumericConstants.THREE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.THREE]));
        dto.setBusinessUnitName(str[NumericConstants.FOUR] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FOUR]));
        dto.setDeductionCategory(str[NumericConstants.FIVE] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.FIVE]));
        dto.setDeductionType(str[NumericConstants.SIX] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SIX]));
        dto.setDeductionProgram(str[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : String.valueOf(str[NumericConstants.SEVEN]));
    }

    public boolean isDuplicateCompany(AdjustmentReserveDTO binderDto) {
        List input = new ArrayList();
        input.add(binderDto.getCompanyDdlbRes());
        input.add(binderDto.getBusinessDdlbRes());
        input.add(binderDto.getDeductionCategoryDdlbRes());
        input.add(binderDto.getDeductionTypeDdlbRes());
        input.add(binderDto.getDeductionProgramDdlbRes());
        return CommonLogic.getCount(QueryUtils.getItemData(input, "Same_Combination_of_Company_selected", null)) == 1;

    }

    public void updateAllCheckRecord(boolean checked, ReserveSelection selection) {
        List input = new ArrayList<>();
        input.add(selection.getTempTableName());
        input.add(checked ? 1 : 0);
        input.add(selection.getMasterSID());
        QueryUtils.itemUpdate(input, "UPdate_all_check_record");
    }

    public int getMasterSids(ReserveSelection selection) {
        List input = getBinderInput(selection.getSearchBinderDTO());
        return CommonLogic.getCount(QueryUtils.getItemData(input, "GetReserveMasterSid", null));
    }

    private List getBinderInput(AdjustmentReserveDTO binderDto) {
        List input = new ArrayList();
        input.add(binderDto.getCompanyDdlbRes());
        input.add(binderDto.getBusinessDdlbRes());
        input.add(binderDto.getDeductionCategoryDdlbRes());
        input.add(binderDto.getDeductionTypeDdlbRes());
        input.add(binderDto.getDeductionProgramDdlbRes());
        return input;
    }

    public void insertToTempTable(ReserveSelection selection) {
        if (selection.getTempTableName() == null) {
            selection.getSession().setCurrentTableNames(QueryUtils.createTempTables(selection.getSession().getScreenName(), selection.getSession().getProjectionId(), selection.getSession().getUserId().toString(), selection.getSession().getSessionId().toString()));
            selection.setTempTableName(selection.getSession().getCurrentTableNames().get("ST_ARM_ADJ_RES_CONFIG_DETAIL"));
            selection.setAdjustmentSummaryTempTableName(selection.getSession().getCurrentTableNames().get("ST_ARM_ADJ_SUMMARY_CONFIG_DETAILS"));
            selection.setBalanceSummaryTempTableName(selection.getSession().getCurrentTableNames().get("ST_ARM_BALANCE_SUMMARY_CONFIG"));
            LOGGER.info("Map -------------Edit---- >> {}", selection.getSession().getCurrentTableNames());
        }

        List input = new ArrayList();
        input.add(selection.getTempTableName());
        List masterSids = new ArrayList();
        masterSids.add(selection.getGtnDetailsMasterSid());
        masterSids.add(selection.getReserveMasterSid());
        input.add(CommonLogic.stringListToString(masterSids));
        QueryUtils.itemUpdate(input, "InsertMainToTemp");
    }

    /**
     * This is the method to check the unique valiation of master combinations
     *
     * @param binderDto
     * @return
     */
    public boolean combinationIsSelected(AdjustmentReserveDTO binderDto) {
        List<Integer> ddlbValues = new ArrayList<>();
        ddlbValues.add(binderDto.getCompanyDdlbRes());
        ddlbValues.add(binderDto.getBusinessDdlbRes());
        ddlbValues.add(binderDto.getDeductionCategoryDdlbRes());
        ddlbValues.add(binderDto.getDeductionTypeDdlbRes());
        ddlbValues.add(binderDto.getDeductionProgramDdlbRes());
        return !ddlbValues.contains(0);

    }

    public void tempToMainUpdateLogic(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        List inputMasterid = new ArrayList<>();
        inputMasterid.add(selection.getReserveMasterSid());
        inputMasterid.add(selection.getGtnDetailsMasterSid());
        input.add(CommonLogic.stringListToString(inputMasterid));
        QueryUtils.itemUpdate(input, "UpdateMainToTemp");
    }

    public void deleteReserveMaster(int searchMasterSid) {
        List input = new ArrayList<>();
        input.add(searchMasterSid);
        input.add(searchMasterSid);
        QueryUtils.itemUpdate(input, "Delete Reserve");
    }

    public void deleteAdjustmentSummary(int reserveMasterSid) {
        List input = new ArrayList<>();
        input.add(reserveMasterSid);
        QueryUtils.itemUpdate(input, "Delete_Adjustment_Summary");
    }

    public void deleteBalanceSummary(int reserveMasterSid) {
        List input = new ArrayList<>();
        input.add(reserveMasterSid);
        QueryUtils.itemUpdate(input, "Delete_Balance_Summary");
    }

    public void updateMasterSid(AdjustmentReserveDTO selectedDto, ReserveSelection selection, int revMas) {
        List input = new ArrayList(NumericConstants.SIX);
        input.add(selection.getTempTableName());
        input.add(selection.getReserveMasterSid());
        input.add(revMas);
        input.add(selection.getTempTableName());
        input.add(selection.getGtnDetailsMasterSid());
        input.add(selectedDto.getSearchMasterSid());
        QueryUtils.itemUpdate(input, "Update_ST_Master_sid");

    }

    private void mainTableLogic(ReserveSelection selection, AdjustmentReserveDTO binderDto) {
        List input = new ArrayList();
        input.add(binderDto.getCompanyDdlbRes());
        input.add(binderDto.getBusinessDdlbRes());
        input.add(binderDto.getDeductionCategoryDdlbRes());
        input.add(binderDto.getDeductionTypeDdlbRes());
        input.add(binderDto.getDeductionProgramDdlbRes());
        input.add(0);
        input.add(selection.getReserveMasterSid());
        input.add(selection.getSession().getUserId());
        input.add(selection.getTempTableName());
        int revId = CommonLogic.getCount(QueryUtils.getItemData(input, "Update_ST_Master_sid_FOr_Add_mode", null));
        if (revId != 0) {
            selection.setReserveMasterSid(revId);
        }
        input.remove(input.size() - 1);
        input.remove(input.size() - 1);
        input.remove(input.size() - 1);
        input.remove(input.size() - 1);
        input.add(1);
        input.add(selection.getGtnDetailsMasterSid());
        input.add(selection.getSession().getUserId());
        input.add(selection.getTempTableName());
        int gtnId = CommonLogic.getCount(QueryUtils.getItemData(input, "Update_ST_Master_sid_FOr_Add_mode", null));
        if (gtnId != 0) {
            selection.setGtnDetailsMasterSid(gtnId);
        }
        if (selection.isIsGTNDetails()) {
            selection.setMasterSID(gtnId != 0 ? gtnId : selection.getGtnDetailsMasterSid());
        } else {
            selection.setMasterSID(revId != 0 ? revId : selection.getReserveMasterSid());
        }
    }

    public void deleteTempTableData(ReserveSelection selection) {
        CommonLogic.dropDynamicTables(selection.getSession().getSessionId().toString(), selection.getSession().getUserId().toString());
    }

    public void forARCSorting(int i, List<SortByColumn> sortByColumns, List input) {
        Map<String, String> columnDetails = ARMUtils.getVisibleToDBColumnMap();
        if (!sortByColumns.isEmpty()) {
            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = iterator.next();
                getInputs(input, columnDetails, orderByColumn, i);
            }
        } else {
            input.add(2, StringUtils.EMPTY);
            input.add(i, " ARC.ARM_ADJ_RES_CONFIG_DETAIL_SID ASC ");
        }
    }

    private void getInputs(List input, Map<String, String> columnDetails, SortByColumn orderByColumn, int i) {
        String columnId = orderByColumn.getName();
        if (!columnId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant())
                && !columnId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant())) {
            if (!ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.toString().equals(columnId) && ArrayUtils.contains(ARMUtils.getAdjustmentReserveCombobox(), columnId)) {
                input.add(2, " LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID = " + columnDetails.get(columnId));
                getOrderByColumn(orderByColumn, input, i, " HT.DESCRIPTION ");
            } else if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.toString().equals(columnId)) {
                input.add(2, StringUtils.EMPTY);
                getOrderByColumn(orderByColumn, input, i, " ADC.TRANSACTION_NAME  ");
            } else {
                input.add(2, StringUtils.EMPTY);
                getOrderByColumn(orderByColumn, input, i, columnDetails.get(columnId));
            }
        } else {
            input.add(2, StringUtils.EMPTY);
            input.add(i, " ARM_ADJ_RES_CONFIG_MASTER_SID ");
        }
    }

    private void getOrderByColumn(SortByColumn orderByColumn, List input, int i, String desc) {
        if (orderByColumn.getType() == SortByColumn.Type.ASC) {
            input.add(i, desc + " asc");
        } else {
            input.add(i, desc + " desc");
        }
    }

    public void forARCSortingView(int i, List<SortByColumn> sortByColumns, List input) {
        Map<String, String> columnDetails = ARMUtils.getVisibleToDBColumnMap();
        if (!sortByColumns.isEmpty()) {
            for (Iterator<SortByColumn> iterator = sortByColumns.iterator(); iterator.hasNext();) {
                SortByColumn orderByColumn = iterator.next();
                getViewInputs(orderByColumn, input, columnDetails, i);
            }
        } else {
            input.add(1, StringUtils.EMPTY);
            input.add(i, " ARC.ARM_ADJ_RES_CONFIG_DETAIL_SID ASC ");
        }
    }

    private void getViewInputs(SortByColumn orderByColumn, List input, Map<String, String> columnDetails, int i) {
        String columnId = orderByColumn.getName();
        if (!columnId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.COMPANYNO.getConstant())
                && !columnId.equals(ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.BUSINESS_UNIT.getConstant())) {
            if (!ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.toString().equals(columnId) && ArrayUtils.contains(ARMUtils.getAdjustmentReserveCombobox(), columnId)) {
                input.add(1, " LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID = " + columnDetails.get(columnId));
                getOrderByColumn(orderByColumn, input, i, " HT.DESCRIPTION ");
            } else if (ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS.ADJUSTMENT_TYPE.toString().equals(columnId)) {
                input.add(1, " LEFT JOIN ARM_ADJUSTMENT_CONFIG ARM on ARM.ARM_ADJUSTMENT_CONFIG_SID = " + columnDetails.get(columnId));
                getOrderByColumn(orderByColumn, input, i, " ADC.TRANSACTION_NAME  ");
            } else {
                input.add(1, StringUtils.EMPTY);
                getOrderByColumn(orderByColumn, input, i, columnDetails.get(columnId));
            }
        } else {
            input.add(1, StringUtils.EMPTY);
            input.add(i, " ARM_ADJ_RES_CONFIG_MASTER_SID ");
        }
    }

    public List<Object> getTypeValuesBasedOnCategory(int category) {
        List<Object> resultList = new ArrayList<>();
        if (category != 0) {
            String query = SQlUtil.getQuery("adjustmentSummaryLoadRSType");
            query = query.replace(ARMUtils.CHAR_QUS, String.valueOf(category));
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        }
        return resultList;
    }

    public List<Object> getTypeValuesBasedOnType(int category, int type) {
        List<Object> resultList = new ArrayList<>();
        if (category != 0) {
            String query = SQlUtil.getQuery("adjustmentSummaryLoadRebateProgramType");
            query = query.replaceAll("@RS_CATEGORY", String.valueOf(category));
            query = query.replaceAll("@RS_TYPE", String.valueOf(type));
            resultList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        }
        return resultList;
    }

    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.debug("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        Map<Integer, String> userMap = CommonUtils.getUserMap();
        for (User user : userList) {
            userMap.put((int) user.getUserId(), user.getFullName());
        }
        CommonUtils.setUserMap(userMap);
        LOGGER.debug("End of getUserName method");
        return userMap;
    }

    public List headerCheckAll(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        if (selection.isIsGTNDetails()) {
            input.add(selection.getGtnDetailsMasterSid());
        } else {
            input.add(selection.getReserveMasterSid());
        }
        return QueryUtils.getItemData(input, "List_view_items_check_details", null);
    }

    public List getCheckedRecords(ReserveSelection selection) {
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        if (selection.isIsGTNDetails()) {
            input.add(selection.getGtnDetailsMasterSid());
        } else {
            input.add(selection.getReserveMasterSid());
        }
        return QueryUtils.getItemData(input, "Checked_Records", null);
    }

    public void insertToTempTable(ReserveSelection tempSelection, ReserveSelection selection) { //Added for GAL-7973
        List input = new ArrayList();
        input.add(selection.getTempTableName());
        input.add(selection.getReserveMasterSid());
        List masterSids = new ArrayList();
        masterSids.add(tempSelection.getReserveMasterSid());
        input.add(CommonLogic.stringListToString(masterSids));
        QueryUtils.itemUpdate(input, "InsertMainToTempForReset");
        List input1 = new ArrayList();
        input1.add(selection.getTempTableName());
        input1.add(selection.getGtnDetailsMasterSid());
        List masterSids1 = new ArrayList();
        masterSids1.add(tempSelection.getGtnDetailsMasterSid());
        input1.add(CommonLogic.stringListToString(masterSids1));
        QueryUtils.itemUpdate(input1, "InsertMainToTempForReset");
    }

    /**
     * The query provides the duplicate values for the unique key constraint.
     *
     * @param selection
     * @param duplicateQuery
     * @return
     */
    public boolean deleteAdjustmentDetailsCheck(AdjustmentReserveDTO dto) {
        String query = SQlUtil.getQuery("adjustment_details_check");
        query = query.replace(ARMUtils.CHAR_QUS, dto.getSearchMasterSid() + StringUtils.EMPTY);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        int val = Integer.parseInt(list.get(0).toString());
        return val > 0;
    }

    public void masterUpdateModifiedDate(ReserveSelection selection) {
        List input = new ArrayList();
        List inputMasterid = new ArrayList<>();
        inputMasterid.add(selection.getReserveMasterSid());
        inputMasterid.add(selection.getGtnDetailsMasterSid());
        input.add(CommonLogic.stringListToString(inputMasterid));
        QueryUtils.itemUpdate(input, "UpdateMasterModifiedDate");
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param reserveOrGtnQuery
     * @return
     */
    public boolean isOnlyValidNoOfRecordsAreAvailableForAdjustmentType(String tableName, String reserveOrGtnQuery) {
        List input = new ArrayList<>();
        input.add(tableName);
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, reserveOrGtnQuery, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param reserveOrGtnQuery
     * @return
     */
    public boolean isOnlyValidNoOfRecordsAvailableForAdjustmentType(String tableName, String reserveOrGtnQuery) {
        List input = new ArrayList<>();
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, reserveOrGtnQuery, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param query
     * @return
     */
    public boolean isDuplicateAccount(String tableName, String query) {
        List input = new ArrayList<>();
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, query, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param query
     * @return
     */
    public boolean isGTNRecordsAreAvailableInReserve(String tableName, String query) {
        List input = new ArrayList<>();
        input.add(tableName);
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, query, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param query
     * @return
     */
    public boolean isCreditDebitAreOppositeForSameAdjustmentType(int reserveSid, String tableName, String query) {
        List input = new ArrayList<>();
        input.add(tableName);
        input.add(reserveSid);

        List resultList = QueryUtils.getItemData(input, query, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    /**
     * This is the method to check that there are exactly two records presents
     * in Reserve details for the particular adjustment type and for GTN exactly
     * one record has to be present for the particular record
     *
     * @param tableName
     * @param query
     * @return
     */
    public boolean isDuplicateReportIndicator(String tableName, String query) {
        List input = new ArrayList<>();
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, query, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    public boolean isCheckToContainMorethanOneRecordSameAdjTypeInGTN(String tableName, String reserveOrGtnQuery) {
        List input = new ArrayList<>();
        input.add(tableName);
        input.add(tableName);
        List resultList = QueryUtils.getItemData(input, reserveOrGtnQuery, null);
        input.clear();
        return CommonLogic.getCount(resultList) == 0;
    }

    private void joinHelperTable(List inputList, Set<Container.Filter> filters) {
        StringBuilder joinQuery = new StringBuilder();
        int count = 0;
        for (Container.Filter filter : filters) {
            if (filter instanceof SimpleStringFilter) {
                SimpleStringFilter stringFilter = (SimpleStringFilter) filter;
                String stringFilters = stringFilter.getPropertyId().toString();
                if (ARMUtils.getDescriptionForFilter().containsKey(stringFilter.getPropertyId().toString())) {
                    joinQuery.append("LEFT JOIN HELPER_TABLE HET").append(count).append("  ON  HET");
                    joinQuery.append(count).append(".HELPER_TABLE_SID =");
                    if (ARMUtils.getDescriptionForFilter().get(stringFilters) != null && !ARMUtils.getDescriptionForFilter().get(stringFilters).isEmpty()) {
                        joinQuery.append("ARC.").append(ARMUtils.getDescriptionForFilter().get(stringFilters)).append(ARMUtils.SPACE);
                    }
                    count++;
                }
            }
        }
        inputList.add(joinQuery.toString());
    }

}
