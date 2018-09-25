/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentDetailsTableLogic;
import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.utils.VariableConstants;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.GtnWsCsvExportUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram.LeelaRam
 * @param <T>
 */
public abstract class AbstractAdjustmentDetailsLogic<T extends AdjustmentDTO> extends AbstractBPLogic<T> {

    public static final Logger LOGGERDETAILLOGIC = LoggerFactory.getLogger(AbstractAdjustmentDetailsLogic.class);

    protected HelperListUtil helperId = HelperListUtil.getInstance();
    private String queryName = StringUtils.EMPTY;
    private String tableName = StringUtils.EMPTY;
    private String orderBy = StringUtils.EMPTY;
    private String configType = StringUtils.EMPTY;
    private List<String> accounts = new ArrayList<>();
    private List<String> amountFilter = new ArrayList<>();

    @Override
    public int getCount(Criteria criteria) {
        setData(criteria.getSelectionDto());
        insertForAdjustmentDetails(criteria);
        String sql = SQlUtil.getQuery("Adjustment Details Count");
        sql = sql.replace(CONFIGTYPETEMPTABLE, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(tableName));
        sql = sql.replace(PAGINATION, StringUtils.EMPTY);
        List count = QueryUtils.executeSelect(sql);
        return count == null || count.isEmpty() ? 0 : ARMUtils.getIntegerValue(String.valueOf(count.get(0)));
    }
    private static final String PAGINATION = "@PAGINATION";
    private static final String CONFIGTYPETEMPTABLE = "@CONFIGTYPETEMPTABLE";

    @Override
    public DataResult<T> getData(Criteria criteria) {
        String sql = SQlUtil.getQuery(queryName);
        sql = sql.replace(CONFIGTYPETEMPTABLE, criteria.getSelectionDto().getSessionDTO().getCurrentTableNames().get(tableName));
        sql = sql.replace(PAGINATION, CommonFilterLogic.getInstance().orderByQueryGenerator(criteria.getSortByColumns(), ARMUtils.loadViewFilterMapForAdjustment(), orderBy).toString() + " OFFSET " + criteria.getStart() + " ROWS FETCH NEXT " + criteria.getOffset() + " ROWS ONLY; ");
        List list = QueryUtils.executeSelect(sql);
        return customizier(criteria.getSelectionDto().getDetailvariables(), list);
    }

    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGERDETAILLOGIC.debug("Inside generate ButtonClick Btn");
        try {
            if (GlobalConstants.getReserveDetail().equalsIgnoreCase(selection.getDetailLevel()) && (StringUtils.isBlank(selection.getDetailLevel()) || selection.getDetailvariables() == null || selection.getDetailvariables().isEmpty()
                    || (selection.getDetailreserveAcount() == null) || (selection.getDetailreserveAcount().isEmpty()))) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
            LOGGERDETAILLOGIC.error("Error in generateButtonCheck :", e);
        }
        return Boolean.FALSE;
    }

    public abstract List<List> getReserveAccountDetails(AbstractSelectionDTO selection, Boolean isReserve);

    protected Boolean isValid(Object obj) {
        if (obj == null || StringUtils.isBlank(String.valueOf(obj))) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * This method will give you the query for all the transactions. for both
     * Reserve and GTN.
     *
     * @param selection
     * @param isCount
     * @param start
     * @param offset
     * @param sortByColumns
     * @return
     */
    public String getQuery(SelectionDTO selection) {
        LOGGERDETAILLOGIC.debug(" Inside GetQuery method ");
        String sb;
        StringBuilder category = new StringBuilder();
        StringBuilder type = new StringBuilder();
        StringBuilder account = new StringBuilder();
        String[] adjustmentLevel = null;
        if (selection.getDetailreserveAcount() != null) {
            for (int i = 0; i < selection.getDetailreserveAcount().size(); i++) {
                String[] str = selection.getDetailreserveAcount().get(i).split(" - ");
                if (i == 0) {
                    category = new StringBuilder(str[0].trim());
                    type = new StringBuilder(str[NumericConstants.ONE].trim());
                    if (GlobalConstants.getReserveDetail().equals(selection.getDetailLevel())) {
                        account.append(str[NumericConstants.TWO].trim());
                    } else {
                        account = new StringBuilder().append(ARMUtils.SINGLE_QUOTES);
                        account.append(str[NumericConstants.TWO].trim()).append(ARMUtils.SINGLE_QUOTES);
                    }
                } else {
                    category.append(ARMUtils.COMMA_CHAR).append(str[0].trim());
                    type.append(ARMUtils.COMMA_CHAR).append(str[NumericConstants.ONE].trim());
                    if (GlobalConstants.getReserveDetail().equals(selection.getDetailLevel())) {
                        account.append(ARMUtils.COMMA_CHAR).append(str[NumericConstants.TWO].trim());
                    } else {
                        account.append(ARMUtils.COMMA_CHAR).append(ARMUtils.SINGLE_QUOTES).append(str[NumericConstants.TWO].trim()).append(ARMUtils.SINGLE_QUOTES);
                    }
                }
            }
        }
        List<HelperDTO> list = helperId.getListNameMap().get("ARM_RES_ADJUSTMENT_LEVEL");
        if (list != null) {
            adjustmentLevel = new String[NumericConstants.TWO];
            for (HelperDTO dto : list) {
                if ("BRAND".equalsIgnoreCase(dto.getDescription())) {
                    adjustmentLevel[0] = String.valueOf(dto.getId());
                } else {
                    adjustmentLevel[1] = String.valueOf(dto.getId());
                }
            }
        }
        boolean isView = selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL);
        if (isView) {
            String query = QueryUtils.getQuery(Arrays.asList(new String[]{String.valueOf(selection.getDataSelectionDTO().getProjectionId())}), "getloadworkflowAdjustmentType");
            List adjustmentType = HelperTableLocalServiceUtil.executeSelectQuery(query);
            selection.getDataSelectionDTO().setAdjustmentId(Integer.parseInt(String.valueOf(adjustmentType.get(0))));
        }
        String insertQuery;
        String insertTable;
        String tableNameTx6;
        if (GlobalConstants.getReserveDetail().equals(selection.getDetailLevel())) {
            insertQuery = getReserveQuery() + "For data new";
            sb = QueryUtils.getQuery(Collections.emptyList(), insertQuery);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", isView ? CommonConstant.ARM_ADJUSTMENTS : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS))
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ARMUtils.CLOSE_PARANTHESIS)
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ARMUtils.CLOSE_PARANTHESIS)
                    .replace("@ACC_VAL1", account)
                    .replace("@ACCOUNT_CONDITION", StringUtils.EMPTY.equalsIgnoreCase(account.toString()) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (SELECT ACC_ID FROM #ARM_ACC)")
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@ADJUSTMENTLEVELBRAND", adjustmentLevel != null ? adjustmentLevel[0] : StringUtils.EMPTY)
                    .replace("@ADJUSTMENTLEVELTOTAL", adjustmentLevel != null ? adjustmentLevel[1] : StringUtils.EMPTY)
                    .replace(CONFIGTYPETEMPTABLE, selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_ADJ_RES_DETAIL_EXCEL))
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetailamountFilter(), "A.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetailamountFilter(), "A."));
        } else {
            insertQuery = getGTNQuery() + "For data new";
            if (isView) {
                insertTable = getTableNameForView();
                if ("Adjustment GTN Details Trx6 query ".equals(getGTNQuery())) {
                    tableNameTx6 = "ARM_INFLATION_INVENTORY";
                } else {
                    tableNameTx6 = "ARM_ADJ_RES_CCP";
                }
            } else {

                insertTable = selection.getSessionDTO().getCurrentTableNames().get(getTableNameForEdit());
                if ("Adjustment GTN Details Trx6 query ".equals(getGTNQuery())) {
                    tableNameTx6 = selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY");
                } else {
                    tableNameTx6 = "ARM_ADJ_RES_CCP";
                }
            }
            sb = QueryUtils.getQuery(Collections.emptyList(), insertQuery);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", insertTable)
                    .replace("@TABLE_2", tableNameTx6)
                    .replace("@TABLE_3", isView ? CommonConstant.ARM_ADJUSTMENTS : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS))
                    .replace("@RATE_COLUMN", getRateColumn())
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@GLCOMP", String.valueOf(selection.getDataSelectionDTO().getCompanyMasterSid()))
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ARMUtils.CLOSE_PARANTHESIS)
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ARMUtils.CLOSE_PARANTHESIS)
                    .replace("@ACCOUNT", StringUtils.EMPTY.equalsIgnoreCase(account.toString()) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (" + account + ARMUtils.CLOSE_PARANTHESIS)
                    .replace(CONFIGTYPETEMPTABLE, selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_ADJ_GTN_DETAIL_EXCEL))
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetailamountFilter(), "TP.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetailamountFilter(), "TP."));
        }
        if (category.toString().isEmpty() || type.toString().isEmpty() || account.toString().isEmpty()) {
            return StringUtils.EMPTY;
        }
        LOGGERDETAILLOGIC.debug(" Ending GetQuery method ");
        return sb;
    }
    private static final String ST_ARM_ADJ_RES_DETAIL_EXCEL = "ST_ARM_ADJ_RES_DETAIL_EXCEL";
    private static final String ST_ARM_ADJ_GTN_DETAIL_EXCEL = "ST_ARM_ADJ_GTN_DETAIL_EXCEL";

    protected abstract String getTableNameForView();

    protected abstract String getTableNameForEdit();

    /**
     * In all the GTN Queries Rates column is needed. every module having
     * different rate column for getting the rate columns name this method is
     * used.
     *
     * @return
     */
    protected abstract CharSequence getRateColumn();

    protected String getReserveQuery() {
        return CommonConstant.ADJUSTMENT_RESERVE_DETAILS_COMMON_QUERY;
    }

    /**
     * For adjustment details GTN query TRX6 only having change so we have
     * overrided the method in trx6.
     *
     * @return
     */
    protected String getGTNQuery() {
        return CommonConstant.ADJUSTMENT_GTN_DETAILS_COMMON_QUERY;
    }

    /**
     * This method will return the Amount Filter Condition based on the
     * selection
     *
     * @return
     */
    protected abstract String getAmountFilterCondition(List<String> condition, String tableAliasName);

    public boolean cerditDebitEqualCheck(AbstractSelectionDTO selection) {
        if (selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            return false;
        }
        String sql = SQlUtil.getQuery("creditDebitEqualCheck");
        sql = sql.replace(CommonConstant.ST_ARM_ADJUSTMENTS, selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return NumericConstants.ONE == (Integer) list.get(0);
    }

    private void setData(SelectionDTO selectionDTO) {
        if (GlobalConstants.getReserveDetail().equals(selectionDTO.getDetailLevel())) {
            queryName = "Adjustment Reserve Details Data";
            tableName = ST_ARM_ADJ_RES_DETAIL_EXCEL;
            orderBy = "LINE_DESCRIPTION,UDC_1,ACCOUNT_TYPE,BRAND,ARM_ADJ_RES_DETAIL_EXCEL_SID";
        } else {
            queryName = "Adjustment GTN Details Data";
            tableName = ST_ARM_ADJ_GTN_DETAIL_EXCEL;
            orderBy = "UDC_1,DEDUCTION_TYPE,ADJUSTMENT_TYPE,BRAND_ID,ARM_ADJ_GTN_DETAIL_EXCEL_SID";
        }
    }

    private void insertForAdjustmentDetails(Criteria criteria) {
        if (getConditionForInsert(criteria.getSelectionDto())) {
            String query = getQuery(criteria.getSelectionDto());
            HelperTableLocalServiceUtil.executeUpdateQuery(query);
        }
        configType = criteria.getSelectionDto().getDetailLevel();
        accounts = criteria.getSelectionDto().getDetailreserveAcount();
        amountFilter = criteria.getSelectionDto().getDetailamountFilter();
    }

    private boolean getConditionForInsert(SelectionDTO selectionDTO) {
        if (!selectionDTO.isExcelDetials()) {
            return configTypeCheck(selectionDTO) || accountsCheck(selectionDTO) || amountCheck(selectionDTO);
        }
        return false;
    }

    private boolean configTypeCheck(SelectionDTO selectionDTO) {
        return configType.isEmpty() || !configType.equals(selectionDTO.getDetailLevel());
    }

    private boolean accountsCheck(SelectionDTO selectionDTO) {
        return compareList(selectionDTO.getDetailreserveAcount(), accounts);
    }

    private boolean amountCheck(SelectionDTO selectionDTO) {
        return compareList(selectionDTO.getDetailamountFilter(), amountFilter);
    }

    private boolean compareList(List sourceList, List listToCompare) {
        List list1 = new ArrayList<>(sourceList);
        if (listToCompare.size() == sourceList.size()) {
            list1.retainAll(listToCompare);
            return list1.size() != listToCompare.size();
        }
        return true;
    }

    public void bcpExcelLogic(AbstractSelectionDTO selection, String[] visibleHeaders, AdjustmentDetailsTableLogic tableLogic) {
        Map<String, String> selectMap;
        Map<String, String> joinsMap;
        Map<String, String> filterMap;
        String baseQuery = "SELECT @VARIABLES FROM @CONFIGTYPETEMPTABLE A \n @JOINS @PAGINATION";
        boolean isReserve = ARMConstants.getReserveDetails().equals(selection.getDetailLevel());
        String query = isReserve ? baseQuery : SQlUtil.getQuery("Adjustment GTN Details HelperInsert") + baseQuery;
        joinsMap = VariableConstants.loadJoinsForAdjustmentExcel();
        if (isReserve) {
            query = query.replace(CONFIGTYPETEMPTABLE, selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_ADJ_RES_DETAIL_EXCEL));
            selectMap = ARMUtils.loadSelectForReserveAdjustmentExcel();
            filterMap = ARMUtils.loadViewFilterMapForAdjustment();
        } else {
            query = query.replace(CONFIGTYPETEMPTABLE, selection.getSessionDTO().getCurrentTableNames().get(ST_ARM_ADJ_GTN_DETAIL_EXCEL));
            selectMap = ARMUtils.loadSelectForGTNAdjustmentExcel();
            filterMap = ARMUtils.loadViewFilterMapForAdjustmentDetailGTN();
        }
        StringBuilder variables = new StringBuilder();
        String countQuery=new String(query);
        countQuery=countQuery.replace("@VARIABLES", "Count(*)");
        countQuery=countQuery.replace("@JOINS", "");
        countQuery=countQuery.replace(PAGINATION, "");
        StringBuilder join = new StringBuilder();
        query = query.replace(PAGINATION, CommonFilterLogic.getInstance().orderByQueryGenerator(tableLogic.getSortByColumns(), ARMUtils.loadViewFilterMapForAdjustment(), orderBy).toString());
        for (String object : selection.getSavedetailvariables()) {
            if (CommonConstant.DEBIT24.equals(object) || CommonConstant.CREDIT25.equals(object) || CommonConstant.DEDUCTION_AMOUNT15.equals(object)
                    || "deductionRate.44".equals(object)) {
                variables.append(", \n").append(selectMap.get(object)).append(" AS ").append(filterMap.get(object));
            } else {
                variables.append(", \n" + "QUOTENAME( CHAR(9) + ").append(selectMap.get(object)).append("+ CHAR(9),CHAR(34))" + " AS ").append(filterMap.get(object));
                String val = joinsMap.get(object);
                join.append(val != null || !"null".equals(String.valueOf(val).trim()) ? "\n" + val : StringUtils.EMPTY);
            }
        }
        String var = variables.toString().substring(1);
        query = query.replace("@VARIABLES", var);
        query = query.replace("@JOINS", join.toString());
        query   =  query +" "+" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        long exportBeginTime = System.currentTimeMillis();
        SessionDTO sessionDto = selection.getSessionDTO();
	String fileAbsolutePath = GtnWsCsvExportUtil.getExportFileName(
		"ADJUSTMENT_DETAILS", countQuery, query,Arrays.asList(visibleHeaders), 
                sessionDto.getUserId().toString(), sessionDto.getSessionId().toString(),1);
	GtnWsCsvExportUtil.sendTheExcelToUser("ADJUSTMENT_DETAILS",
						fileAbsolutePath, true, sessionDto.getUserId().toString(), sessionDto.getSessionId().toString());
        long exportEndTime = System.currentTimeMillis();
        LOGGER.info("BCP Export took {}", (exportEndTime - exportBeginTime) + " milliseconds");
    }

    
}
