/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.common.CommonFilterLogic;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.COMMA;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.VariableConstants.SINGLE_QUOTE;
import com.stpl.app.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram.LeelaRam
 * @param <T>
 */
public abstract class AbstractAdjustmentDetailsLogic<T extends AdjustmentDTO> extends AbstractBPLogic<T> {

    public static final Logger LOGGERDETAILLOGIC = Logger.getLogger(AbstractAdjustmentDetailsLogic.class);

    protected HelperListUtil helperId = HelperListUtil.getInstance();

    @Override
    public int getCount(Criteria criteria) {
        String query = getQuery(criteria.getSelectionDto(), Boolean.TRUE, 0, 0,criteria.getSortByColumns());
        if (!query.isEmpty()) {
            List count = QueryUtils.executeSelect(query);
            return count == null || count.isEmpty() ? 0 : Integer.valueOf(String.valueOf(count.get(0)));
        } else {
            return 0;
        }
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        String query = getQuery(criteria.getSelectionDto(), Boolean.FALSE, criteria.getStart(), criteria.getOffset() , criteria.getSortByColumns());
        List list = QueryUtils.executeSelect(query);
        return customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO", criteria.getSelectionDto().getDetailvariables(), list);
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
            LOGGERDETAILLOGIC.error("Error in generateButtonCheck :"+e);
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
    public String getQuery(SelectionDTO selection, Boolean isCount, int start, int offset ,List<SortByColumn> sortByColumns  ) {
        LOGGERDETAILLOGIC.debug(" Inside GetQuery method ");
        String sb;
        StringBuilder category = new StringBuilder();
        StringBuilder type = new StringBuilder();
        StringBuilder account = new StringBuilder();
        String account1 = (selection.getDetailreserveAcount().get(0).split(" - "))[2];
        String account2 = selection.getDetailreserveAcount().size() > 1 ? (selection.getDetailreserveAcount().get(1).split(" - "))[2] : StringUtils.EMPTY;
        String[] adjustmentLevel = null;
        if (selection.getDetailreserveAcount() != null) {
            for (int i = 0; i < selection.getDetailreserveAcount().size(); i++) {
                String[] str = selection.getDetailreserveAcount().get(i).split(" - ");
                if (i == 0) {
                    category = new StringBuilder(str[0].trim());
                    type = new StringBuilder(str[NumericConstants.ONE].trim());
                    account = new StringBuilder(SINGLE_QUOTE);
                    account.append(str[NumericConstants.TWO].trim()).append(SINGLE_QUOTE);
                } else {
                    category.append(COMMA).append(str[0].trim());
                    type.append(COMMA).append(str[NumericConstants.ONE].trim());
                    account.append(COMMA).append(SINGLE_QUOTE).append(str[NumericConstants.TWO].trim()).append(SINGLE_QUOTE);
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
        String queryName ;
        String tableName ;
        String tableNameTx6;
        if (GlobalConstants.getReserveDetail().equals(selection.getDetailLevel())) {
            queryName = isCount ? getReserveQuery() + "For count" : getReserveQuery() + "For data";
            sb = QueryUtils.getQuery(Collections.emptyList(), queryName);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", isView ? CommonConstant.ARM_ADJUSTMENTS : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS))
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ")")
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ")")
                    .replace("@ACC_VAL1", account1)
                    .replace("@ACC_VAL2", account2)
                    .replace("@ACCOUNT_CONDITION", StringUtils.EMPTY.equalsIgnoreCase(account.toString()) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (@ACCOUNT2,@ACCOUNT1)")
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@ADJUSTMENTLEVELBRAND", adjustmentLevel != null ? adjustmentLevel[0] : StringUtils.EMPTY)
                    .replace("@ADJUSTMENTLEVELTOTAL", adjustmentLevel != null ? adjustmentLevel[1] : StringUtils.EMPTY)
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetailamountFilter(), "A.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetailamountFilter(), "A."))
                    .replace("@PAGINATION", isCount ? StringUtils.EMPTY : CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, ARMUtils.loadViewFilterMapForAdjustment(), "  AAD.LINE_DESCRIPTION,UDC1.DESCRIPTION,HT.DESCRIPTION,BRAND_ID ").toString()+ " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ");
        } else {
            queryName = isCount ? getGTNQuery() + "For count" : getGTNQuery() + "For data";
            if (isView) {
                tableName = getTableNameForView();
                if ("Adjustment GTN Details Trx6 query ".equals(getGTNQuery())) {
                    tableNameTx6 = "ARM_INFLATION_INVENTORY";
                }else{
                    tableNameTx6 = "ARM_ADJ_RES_CCP";
                }
            } else {

                tableName = selection.getSessionDTO().getCurrentTableNames().get(getTableNameForEdit());
                if ("Adjustment GTN Details Trx6 query ".equals(getGTNQuery())) {
                    tableNameTx6 = selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY");
                }else{
                    tableNameTx6 = "ARM_ADJ_RES_CCP";
                }
            }
            sb = QueryUtils.getQuery(Collections.emptyList(), queryName);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", tableName)
                    .replace("@TABLE_2", tableNameTx6)
                    .replace("@TABLE_3", isView ? CommonConstant.ARM_ADJUSTMENTS : selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS))
                    .replace("@RATE_COLUMN", getRateColumn())
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@GLCOMP", String.valueOf(selection.getDataSelectionDTO().getCompanyMasterSid()))
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ")")
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type.toString()) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ")")
                    .replace("@ACCOUNT", StringUtils.EMPTY.equalsIgnoreCase(account.toString()) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (" + account + ")")
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetailamountFilter(), "TP.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetailamountFilter(), "TP."))
                    .replace("@PAGINATION", isCount ? StringUtils.EMPTY : CommonFilterLogic.getInstance().orderByQueryGenerator(sortByColumns, ARMUtils.loadViewFilterMapForAdjustmentDetailGTN(),"  UDC_1,DEDUCTION_TYPE,AC.TRANSACTION_NAME,BRAND_ID").toString() + " OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY; ");
        }
        if (category.toString().isEmpty() || type.toString().isEmpty() || account.toString().isEmpty()) {
            return StringUtils.EMPTY;
        }
        LOGGERDETAILLOGIC.debug(" Ending GetQuery method ");
        return sb;
    }

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
        if(selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)){
            return false;
        }
        String sql = SQlUtil.getQuery("creditDebitEqualCheck");
        sql = sql.replace(CommonConstant.ST_ARM_ADJUSTMENTS, selection.getSessionDTO().getCurrentTableNames().get(CommonConstant.ST_ARM_ADJUSTMENTS));
        List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return NumericConstants.ONE == (Integer) list.get(0);
    }
        
}
