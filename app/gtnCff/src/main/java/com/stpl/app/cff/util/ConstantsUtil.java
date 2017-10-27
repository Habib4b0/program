/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Manasa
 */
public class ConstantsUtil {

    /**
     * The session id.
     */
    public final static String SESSION_ID = "sessionId";
    
    public static final String DOLLAR_OR = "^.|.$";
    /**
     * The WorkFlowStatus list name.
     */
    public final static String WORKFLOW_STATUS = "WorkFlowStatus";
    /**
     * The annual.
     */
    public static final String ANNUAL = "Annual";
    public static final String ANNUALLY = "Annually";
    /**
     * The quarterly.
     */
    public static final String QUARTERLY = "Quarterly";
    public static final String SEMI_ANNUALLY = "Semi-Annually";
    /**
     * The semi annualy.
     */
    public static final String SEMI_ANNUALY = "Semi-Annual";
    /**
     * The monthly.
     */
    public static final String MONTHLY = "Monthly";
    /**
     * The user id.
     */
    public final static String USER_ID = "userId";
    /**
     * The SELECT_ONE.
     */
    public static final String SELECT_ONE = "-Select One-";
    /**
     * The CFF_TYPE.
     */
    public static final String CFF_TYPE = "CFF_TYPE";
    /**
     * The LOCKED_STATUS.
     */
    public final static String LOCKED_STATUS = "LOCKED_STATUS";
    /**
     * The description.
     */
    public final static String DESCRIPTION = "description";
    /**
     * The Show all.
     */
    public final static String SHOW_ALL = "Show all";
    /**
     * The list name.
     */
    public final static String LIST_NAME = "listName";
    /**
     * The Constant ZERO_NUM.
     */
    public static final int ZERO_NUM = 0;
    /**
     * The null.
     */
    public final static String NULL = "null";
    /**
     * The counter value
     */
    public static String COUNTER_VALUE = "counterValue";
    /**
     * The Constant ERROR.
     */
    public static final String ERROR = "Error";
    /**
     * The Constant Inbound status for save
     */
    public static final String INBOUND_STATUS_ADD = "A";
    /**
     * The Constant Inbound status for update
     */
    public static final String INBOUND_STATUS_UPDATE = "C";
    /**
     * The Constant Inbound status for delete
     */
    public static final String INBOUND_STATUS_DELETE = "D";
    /**
     * Reg ex for alphaNumericChars
     */
    public static final String ALPHA_NUM_CHARS = "([0-9|a-z|A-Z|\\ |\\*])*";
    /**
     * The date format.
     */
    public final static String DATE_FORMAT = "MM/dd/yyyy";
    /**
     * The date format.
     */
    public final static String DATE_FIEILD_CENTER = "dateFieldCenter";
    /**
     * The date des.
     */
    public final static String DATE_DES = "Date";
    /**
     * The quote.
     */
    public final static String QUOTE = "\"";
    public static String EMPTY = "";
    public static final String FORECAST_YEAR = "forecastYear";
    public static final String CFF_MASTER = "CFF_MASTER";
    public static final String CFF_APPROVE_MASTER = "CFF_APPROVAL_DETAILS";
    public static final String CFF_DETAILS = "CFF_DETAILS";
    public static final String ACTUAL = "Actual";
    public static final String ACCRUAL = "Accrual";
    public static final String OPEN_PARANTHESIS = "(";
    public static final String CLOSE_PARANTHESIS = ")";
    public static String COMMA=",";
    public static final String DETAIL = "Detail";
    public static final String NET_EXFACT_SALES = "Net Ex-Factory Sales";
    public static final String NET_EXFACT_SALES_PER_EXFACT = "Net Ex-Factory Sales as % of Ex-Factory Sales";
    public static final String NET_EXFACT_SALES_COLUMN_VALUE = "NEFSValue";
    public static final String NET_EXFACT_SALES_HEADER_VALUE = "Net Ex-Factory Sales Value";
    public static final String NET_EXFACT_SALES_COLUMN_VARIANCE = "NEFSVariance";
    public static final String NET_EXFACT_SALES_HEADER_VARIANCE = "Net Ex-Factory Sales Variance";
    public static final String NET_EXFACT_SALES_COLUMN_PER_CHANGE = "NEFSPerChange";
    public static final String NET_EXFACT_SALES_HEADER_PER_CHANGE = "Net Ex-Factory Sales %Change";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_VALUE = "NEFSPEFValue";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_VALUE = "Net Ex-Factory Sales as % of Ex-Factory Sales Value";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_VARIANCE = "NEFSPEFVariance";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_VARIANCE = "Net Ex-Factory Sales as % of Ex-Factory Sales Variance";
    public static final String NET_EXFACT_SALES_PER_EXFACT_COLUMN_PER_CHANGE = "NEFSPEFPerChange";
    public static final String NET_EXFACT_SALES_PER_EXFACT_HEADER_PER_CHANGE = "Net Ex-Factory Sales as % of Ex-Factory Sales %Change";
    public static final String TOTAL_DISCOUNT = "Total Discount";
    

    public enum PVVariables {

        CHECK_ALL("Check All"),
        EX_FACTORY_PRODUCT("Ex-Factory Product"),
        EX_FACTORY_CUSTOMER("Ex-Factory Customer"),
        DEMAND_SALES("Demand"),
        ADJUSTED_DEMAND("Adjusted Demand"),
        INVENTORY_SUMMARY("Inventory Withdrawal Summary"),
        INVENTORY_DETAILS("Inventory Withdrawal Details"),
        PER_EX_FACTORY_PRODUCT("% Of Ex-Factory Product"),
        PER_EX_FACTORY_CUSTOMER("% Of Ex-Factory Customer"),
        PER_DEMAND("% Of Demand"),
        PER_ADJUSTED_DEMAND("% Of Adjusted Demand"),
        PER_INVENORY_WITHDRAW_SUMMARY("% Of Inventory Withdrawal Summary"),
        PER_INVENORY_WITHDRAW_DETAILS("% Of Inventory Withdrawal Details"),
        VAR_CONTRACT_SALES("Contract Sales @ WAC"),
        VAR_CONTRACT_UNITS("Contract Units"),
        VAR_DIS_AMOUNT("Discount $"),
        VAR_DIS_RATE("Discount %"),
        VAR_RPU("RPU"),
        DISCOUNT_PER_EX_FACTORY("Discount % of Ex-Factory"),
        VAR_NETSALES("Net Sales"),
        NET_SALES_PER_EX_FACTORY("Net Sales % of Ex-Factory"),
        NET_EX_FACTORY_SALES(NET_EXFACT_SALES),
        NET_EX_FACTORY_SALES_PER_EX_FACTORY(NET_EXFACT_SALES_PER_EXFACT),
        VAR_COGS("COGS"),
        VAR_NET_PROFITE("Net Profit");
        private String constant;

        private PVVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PVVariables.values()).replaceAll(DOLLAR_OR, "").split(",");
        }

        public static String[] getCheckAllVariables() {
            return Arrays.toString(ArrayUtils.removeElement(PVVariables.values(), CHECK_ALL)).replaceAll(DOLLAR_OR, "").split(",");
        }
    }
    
    
     public enum PVVariableCategory {

        COL_VALUE("Value"),
        COL_VARIANCE("Variance"),
        COL_PERCENTAGE("%Change"),
        COL_ACTUALS("Actuals"),
        COL_ACCRUALS(StringConstantsUtil.ACCRUALS);
        private String constant;

        private PVVariableCategory(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            String[] temp = Arrays.toString(PVVariableCategory.values()).replaceAll(DOLLAR_OR, StringUtils.EMPTY).split(",");
            List tempList = new ArrayList(Arrays.asList(temp));
            if (!CommonUtils.isValueEligibleForLoading()) {
                tempList.remove(" "+StringConstantsUtil.ACCRUALS);
            }
            return Arrays.copyOf(tempList.toArray(), tempList.toArray().length,String[].class);
        }
    }
}
