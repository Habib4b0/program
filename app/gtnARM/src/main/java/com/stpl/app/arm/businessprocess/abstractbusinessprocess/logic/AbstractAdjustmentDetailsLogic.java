/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.supercode.Criteria;
import com.stpl.app.arm.supercode.DataResult;
import com.stpl.app.arm.supercode.SelectionDTO;
import com.stpl.app.arm.utils.ARMUtils;
import static com.stpl.app.arm.utils.ARMUtils.COMMA;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.VariableConstants.SINGLE_QUOTE;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.GlobalConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram.LeelaRam
 * @param <T>
 */
public abstract class AbstractAdjustmentDetailsLogic<T extends AdjustmentDTO> extends AbstractBPLogic<T> {

    public static final Logger LOGGER = Logger.getLogger(AbstractAdjustmentDetailsLogic.class);

    protected HelperListUtil helperId = HelperListUtil.getInstance();

    @Override
    public int getCount(Criteria criteria) {
        String query = getQuery(criteria.getSelectionDto(), Boolean.TRUE, 0, 0);
        if (!query.isEmpty()) {
            List count = QueryUtils.executeSelect(query);
            return count == null || count.isEmpty() ? 0 : Integer.valueOf(String.valueOf(count.get(0)));
        } else {
            return 0;
        }
    }

    @Override
    public DataResult<T> getData(Criteria criteria) {
        String query = getQuery(criteria.getSelectionDto(), Boolean.FALSE, criteria.getStart(), criteria.getOffset());
        List list = QueryUtils.executeSelect(query);
        return customizier("com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO", criteria.getSelectionDto().getDetail_variables(), list);
    }

    public Boolean generateButtonCheck(SelectionDTO selection) {
        LOGGER.debug("Inside generate ButtonClick Btn");
        try {
            if (GlobalConstants.getReserveDetail().equalsIgnoreCase(selection.getDetail_Level()) && (StringUtils.isBlank(selection.getDetail_Level()) || selection.getDetail_variables() == null || selection.getDetail_variables().isEmpty()
                    || (selection.getDetail_reserveAcount() == null) || (selection.getDetail_reserveAcount().isEmpty()))) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        } catch (Exception e) {
           LOGGER.error(e);
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
     * @return
     */
    public String getQuery(SelectionDTO selection, Boolean isCount, int start, int offset) {
        LOGGER.debug(" Inside GetQuery method ");
        String sb = null;
        String category = StringUtils.EMPTY;
        String type = StringUtils.EMPTY;
        String account = StringUtils.EMPTY;
        String[] adjustmentLevel = null;
        if (selection.getDetail_reserveAcount() != null) {
            for (int i = 0; i < selection.getDetail_reserveAcount().size(); i++) {
                String[] str = selection.getDetail_reserveAcount().get(i).split(" - ");
                if (i == 0) {
                    category = str[0].trim();
                    type = str[NumericConstants.ONE].trim();
                    account = SINGLE_QUOTE + str[NumericConstants.TWO].trim() + SINGLE_QUOTE;
                } else {
                    category += COMMA + str[0].trim();
                    type += COMMA + str[NumericConstants.ONE].trim();
                    account += COMMA + SINGLE_QUOTE + str[NumericConstants.TWO].trim() + SINGLE_QUOTE;
                }
            }
        }
        List<HelperDTO> list = helperId.getListNameMap().get("ARM_RES_ADJUSTMENT_LEVEL");
        if (list != null) {
            adjustmentLevel = new String[NumericConstants.TWO];
            for (HelperDTO dto : list) {
                if (dto.getDescription().equalsIgnoreCase("BRAND")) {
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
        String queryName = null;
        String tableName = null;
        String tableName_Tx6 = StringUtils.EMPTY;
        if (GlobalConstants.getReserveDetail().equals(selection.getDetail_Level())) {
            queryName = isCount ? getReserveQuery() + "For count" : getReserveQuery() + "For data";
            if (isView) {
                tableName = getTableNameForView();
            } else {

                tableName = selection.getSessionDTO().getCurrentTableNames().get(getTableNameForEdit());
            }
            sb = QueryUtils.getQuery(Collections.EMPTY_LIST, queryName);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", tableName)
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ")")
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ")")
                    .replace("@ACCOUNT", StringUtils.EMPTY.equalsIgnoreCase(account) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (" + account + ")")
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@ADJUSTMENTLEVELBRAND", adjustmentLevel != null ? adjustmentLevel[0] : StringUtils.EMPTY)
                    .replace("@ADJUSTMENTLEVELTOTAL", adjustmentLevel != null ? adjustmentLevel[1] : StringUtils.EMPTY)
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetail_amountFilter(), "A.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetail_amountFilter(), "A."))
                    .replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY AAD.LINE_DESCRIPTION,UDC1.DESCRIPTION,HT.DESCRIPTION,BRAND_ID OFFSET " + String.valueOf(start) + " ROWS FETCH NEXT " + String.valueOf(offset) + " ROWS ONLY; ");
        } else {
            queryName = isCount ? getGTNQuery() + "For count" : getGTNQuery() + "For data";
            if (isView) {
                tableName = getTableNameForView();
                tableName_Tx6 = "ARM_INFLATION_INVENTORY";
            } else {

                tableName = selection.getSessionDTO().getCurrentTableNames().get(getTableNameForEdit());
                if (getGTNQuery().equals("Adjustment GTN Details Trx6 query ")) {
                    tableName_Tx6 = selection.getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY");
                }
            }
            sb = QueryUtils.getQuery(Collections.EMPTY_LIST, queryName);
            sb = sb.replace("@SESSION_DECLARE", StringUtils.EMPTY)
                    .replace("@SESSION_INCLUDE", StringUtils.EMPTY)
                    .replace("@TABLE_1", tableName)
                    .replace("@TABLE_2", tableName_Tx6)
                    .replace("@RATE_COLUMN", getRateColumn())
                    .replace("@PROJECTIONMASTER", String.valueOf(selection.getDataSelectionDTO().getProjectionId()))
                    .replace("@GLCOMP", String.valueOf(selection.getDataSelectionDTO().getCompanyMasterSid()))
                    .replace("@ADJUSTMENT_TYPE", String.valueOf(selection.getDataSelectionDTO().getAdjustmentId()))
                    .replace("@USER_REF", "" + selection.getSessionDTO().getUserId())
                    .replace("@SESSION_REF", "" + selection.getSessionDTO().getSessionId())
                    .replace("@CATEGORY", StringUtils.EMPTY.equalsIgnoreCase(category) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_CATEGORY IN (" + category + ")")
                    .replace("@TYPE", StringUtils.EMPTY.equalsIgnoreCase(type) ? StringUtils.EMPTY : "AND AAD.ACCOUNT_TYPE IN (" + type + ")")
                    .replace("@ACCOUNT", StringUtils.EMPTY.equalsIgnoreCase(account) ? StringUtils.EMPTY : " AND AAD.ACCOUNT IN (" + account + ")")
                    .replace("?AMOUNTCONDITION", StringUtils.EMPTY.equals(getAmountFilterCondition(selection.getDetail_amountFilter(), "TP.")) ? StringUtils.EMPTY : getAmountFilterCondition(selection.getDetail_amountFilter(), "TP."))
                    .replace("@PAGINATION", isCount ? StringUtils.EMPTY : " ORDER BY UDC_1,DEDUCTION_TYPE,AC.TRANSACTION_NAME,BRAND_ID OFFSET " + String.valueOf(start) + " ROWS FETCH NEXT " + String.valueOf(offset) + " ROWS ONLY; ");
        }
        if (category.isEmpty() || type.isEmpty() || account.isEmpty()) {
            return StringUtils.EMPTY;
        }
        LOGGER.debug(" Ending GetQuery method ");
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
        return "Adjustment Reserve Details common query ";
    }

    /**
     * For adjustment details GTN query TRX6 only having change so we have
     * overrided the method in trx6.
     *
     * @return
     */
    protected String getGTNQuery() {
        return "Adjustment GTN Details common query ";
    }

    /**
     * This method will return the Amount Filter Condition based on the
     * selection
     *
     * @return
     */
    protected abstract String getAmountFilterCondition(List<String> condition, String tableAliasName);

}
