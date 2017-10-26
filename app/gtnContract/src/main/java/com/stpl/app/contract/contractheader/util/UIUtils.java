package com.stpl.app.contract.contractheader.util;

import com.stpl.app.contract.util.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.HashMap;

/**
 * Class contains the Constants.
 */
public final class UIUtils {

    /**
     * String constant CONTRACT_TYPE.
     */
    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
    /**
     * String constant CONTRACT_STATUS.
     */
    public static final String CONTRACT_STATUS = "CONTRACT_STATUS";
    /**
     * String constant STATUS.
     */
    public static final String STATUS = "STATUS";
    /**
     * String constant TRADE_CLASS.
     */
    public static final String TRADE_CLASS = "CONTRACT_TRADE_CLASS";
    /**
     * String constant DOCUMENT_TYPE.
     */
    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
    /**
     * String constant DOCUMENT_CLASS.
     */
    public static final String DOCUMENT_CLASS = "DOCUMENT_CLASS";
    /**
     * String constant CONTRACT_ALIAS_TYPE.
     */
    public static final String CONTRACT_ALIAS_TYPE = "CONTRACT_ALIAS_TYPE";
    /**
     * String constant AWARD_STATUS.
     */
    public static final String AWARD_STATUS = "CONTRACT_AWARD_STATUS";
    /**
     * String constant PRICE_RESET_INDICATOR.
     */
    public static final String PRICE_RESET_INDICATOR = "PRICE_RESET_INDICATOR";
    /**
     * String constant PAYMENT_TERMS.
     */
    public static final String PAYMENT_TERMS = "CONTRACT_PAYMENT_TERMS";
    /**
     * String constant company type.
     */
    public static final String COMPANY_TYPE = "CompanyType";
    /**
     * Constant Object array SEARCH_COLUMNS.
     */
    public static final Object[] SEARCH_COLUMNS = {"contractId", "contractNo", "contractName", "contractStatus", "contractType", "tradeClass", "tradingPartnerName"};
    /**
     * Constant String array SEARCHCOLUMS_HEADER.
     */
    public static final String[] SEARCHCOLUMS_HEADER = {"Contract ID", "Contract No", "Contract Name", "Contract Status", "Contract Type", "Trade Class", "Trading Partner"};
    /**
     * Constant Object array ALIAS_COLUMNS.
     */
    public static final Object[] ALIAS_COLUMNS = {"tpCompanyMasterSid", "contractAliasNo", "contractAliasName", "contractAliasType", "startDate", "endDate"};

    /**
     * Constant String array ALIAS_COLUMNS_HEADER.
     */
    public static final String[] ALIAS_COLUMNS_HEADER = {"Trading Partner", "Contract Alias No", "Contract Alias Name", "Contract Alias Type", "Alias Start Date", "Alias End Date"};
    /**
     * Constant int PAGE_LENGTH.
     */
    public static final int PAGE_LENGTH = NumericConstants.SEVEN;
    /**
     * Constant Object array TRADING_LOOKUP_COLUMNS.
     */
    public static final Object[] TRADING_LOOKUP_COLUMNS = new Object[]{"companyId", "companyNo", "companyName", "companyStatus", "companyType"};
    /**
     * Constant String array TRADING_LOOKUP_HEADERS.
     */
    public static final String[] TRADING_LOOKUP_HEADERS = new String[]{"Company ID", "Company No", "Company Name", "Company Status", "Company Type"};
    /**
     * Constant Object array DASHBOARD_ALIAS_COLUMNS.
     */
    public static final Object[] DASHBOARD_ALIAS_COLUMNS = {"contractAliasDesc", "tradingName", "contractAliasNo", "contractAliasName", "startDate", "endDate"};
    /**
     * Constant String array DASHBOARD_ALIAS_COLUMNS_HEADER.
     */
    public static final String[] DASHBOARD_ALIAS_COLUMNS_HEADER = {"Alias Type", "Trading Partner", "Contract Alias No", "Contract Alias Name", "Alias Start Date", "Alias End Date"};

    /**
     * String constant PAYMENT_TERMS.
     */
    public static final String PAYMENT_TERMS_DDLB = "PAYMENT_TERMS";
    /**
     * String constant CFP_TYPE.
     */
    public static final String CFP_TYPE = "CFP_TYPE";
    /**
     * String constant IFP_TYPE.
     */
    public static final String IFP_TYPE = "IFP_TYPE";

    /**
     * String constant ITEM_THERP_CLASS.
     */
    public static final String ITEM_THERP_CLASS = "ITEM_THERP_CLASS";

    /**
     * HashMap contractHeaderColumns
     */
    private static HashMap<String, String> contractHeaderColumns = new HashMap<String, String>();

    /**
     * The Constant QUOTE.
     */
    public static final String QUOTE = "\"";

    /**
     * The comma.
     */
    public final static String COMMA = ",";

    /**
     * The Constant COMPANY_TYPE.
     */
    public final static String COMP_TYPE = "COMPANY_TYPE";

    /**
     * The Price schedule type
     */
    public final static String PS_TYPE = "PS_TYPE";

    /**
     * The Rebate schedule type
     */
    public final static String RS_TYPE = "RS_TYPE";

    /**
     * The Rebate schedule type
     */
    public final static String RS_UDC_2 = "RS_UDC2";
    public static final String NS_FORMULA_TYPE = "NS_FORMULA_TYPE";
    public static final String RULE_CATEGORY = "RULE_CATEGORY";
    public static final String LINE_TYPE = "LINE_TYPE";
    public static final String ITEM_GROUP_MS_ASSOCIATION = "ITEM_GROUP_MS_ASSOCIATION";
    public static final String KEYWORD = "KEYWORD";
    public static String OPERATOR = "OPERATOR";
    public static String COMPARISON = "COMPARISON";
    public static String LOGICAL_OPERATOR = "LOGICAL_OPERATOR";
    public static final String DEDUCTION_CALENDAR_CATEGORY = "DEDUCTION_CALENDAR_CATEGORY";

    /**
     * The Constant PRICE_TOLERANCE_TYPE.
     */
    public static final String PRICE_TOLERANCE_TYPE = "PRICE_TOLERANCE_TYPE";
    /**
     * The Constant PRICE_TOLERANCE_INTERVAL.
     */
    public static final String PRICE_TOLERANCE_INTERVAL = "PRICE_TOLERANCE_INTERVAL";
    /**
     * The Constant PRICE_TOLERANCE_FRERQUENCY.
     */
    public static final String PRICE_TOLERANCE_FRERQUENCY = "PRICE_TOLERANCE_FREQUENCY";
    /**
     * The Constant RESET_TYPE.
     */
    public static final String RESET_TYPE = "RESET_TYPE";
    /**
     * The Constant REBATE_SCHEDULE_TYPE.
     */
    public static final String REBATE_SCHEDULE_TYPE = "RS_TYPE";

    /**
     * The Constant REBATE_PROGRAM_TYPE.
     */
    public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";

    /**
     * The Constant REBATE_SCHEDULE_CATEGORY.
     */
    public static final String REBATE_SCHEDULE_CATEGORY = "RS_CATEGORY";

    /**
     * The Constant REBATE_SCHEDULE_DESIGNATION.
     */
    public static final String REBATE_SCHEDULE_DESIGNATION = "RS_DESIGNATION";

    /**
     * The Constant CALENDAR.
     */
    public static final String CALENDAR = "RS_CALENDAR";

    /**
     * The Constant PAYMENT_METHOD.
     */
    public static final String PAYMENT_METHOD = "PAYMENT_METHOD";

    /**
     * The Constant PAYMENT_FREQUENCY.
     */
    public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";

    /**
     * The Constant CALCULATION_TYPE.
     */
    public static final String CALCULATION_TYPE = "CALCULATION_TYPE";

    /**
     * The Constant CALCULATION_LEVEL.
     */
    public static final String CALCULATION_LEVEL = "CALCULATION_LEVEL";
    /**
     * The Constant REBATE_FREQUENCY.
     */
    public static final String REBATE_FREQUENCY = "REBATE_FREQUENCY";
    /**
     * The Constant CALCULATION_TYPE.
     */
    public static final String INTEREST_BEARING_INDICATOR = "INTEREST_BEARING_INDICATOR";
    /**
     * The Constant REBATE_RULE_TYPE.
     */
    public static final String REBATE_RULE_TYPE = "REBATE_RULE_TYPE";
    /**
     * The Constant CALCULATION_LEVEL.
     */
    public static final String INTEREST_BEARING_BASIS = "INTEREST_BEARING_BASIS";
    /**
     * The Constant UDC1.
     */
    public static final String UDC1 = "RS_UDC1";

    /**
     * The Constant UDC2.
     */
    public static final String UDC2 = "RS_UDC2";

    /**
     * The Constant UDC3.
     */
    public static final String UDC3 = "RS_UDC3";

    /**
     * The Constant UDC4.
     */
    public static final String UDC4 = "RS_UDC4";

    /**
     * The Constant UDC5.
     */
    public static final String UDC5 = "RS_UDC5";

    /**
     * The Constant UDC6.
     */
    public static final String UDC6 = "RS_UDC6";
    /**
     * The Constant REBATE_BASED_ON.
     */
    public static final String REBATE_BASED_ON = "REBATE_BASED_ON";
    /**
     * The Constant REBATE_STRUCTURE.
     */
    public static final String REBATE_STRUCTURE = "REBATE_STRUCTURE";

    /**
     * The Constant REBATE_RANGE_BASED_ON.
     */
    public static final String REBATE_RANGE_BASED_ON = "REBATE_RANGE_BASED_ON";
    /**
     * The Constant REBATE_PLAN_TYPE.
     */
    public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";
    public static final String LOCKED_STATUS = "LOCKED_STATUS";
    public static final String BASE_PRICE_TYPE="BASE_PRICE_TYPE";
    /** The Constant CFP_STATUS. */
    public static final String CFP_STATUS="STATUS";
    /** The Constant CFP_SEARCH. */
    public static final Object[] CFP_SEARCH = new Object[] {"systemId",
                    "companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanName","companyFamilyPlanType",
                    "companyFamilyPlanStatus","companyFamilyPlanCategory",ConstantsUtils.CFP_START_DATE, ConstantsUtils.CFP_END_DATE ,
                    "companyFamilyPlanDesignation",
                    "parentCompanyFamilyPlanId", "parentCompanyFamilyPlanName",ConstantsUtils.MODIFIEDDATE,ConstantsUtils.MODIFIEDBY,
                     ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};

    /** The Constant CFP_SEARCH_HEADER. */
    public static final String[] CFP_SEARCH_HEADER = new String[] {"System ID", "CFP ID", Constants.COMPANYFAMILYPLANNO,
                    Constants.COMPANYFAMILYPLANNAME, Constants.COMPANYFAMILYPLANTYPE,Constants.COMPANYFAMILYPLANSTATUS,Constants.COMPANYFAMILYPLANCATEGORY, "Start Date", 
                    "End Date",Constants.COMPANYFAMILYPLANDESIGNATION,"Parent ID","Parent Name",ConstantsUtils.MODIFIED_DATE,ConstantsUtils.MODIFIED_BY,
                    ConstantsUtils.CREATED_BY,ConstantsUtils.CREATED_DATE1};
    
    /** The Constant CFP_DESIGNATION. */
    public static final String CFP_DESIGNATION="CFP_DESIGNATION";
    public static final String COMPANY_CATEGORY_LIST_NAME = "COMPANY_CATEGORY";
    public static final String COMPANY_TRADE_CLASS = "COMPANY_TRADE_CLASS";
    /** The Constant CFP_CATAGORY. */
    public static final String CFP_CATAGORY="CFP_CATEGORY";
    public static final String COMPANY_TYPE_LIST_NAME = "COMPANY_TYPE";
    /** The Constant COMPANY_GROUP. */
    public static final String COMPANY_GROUP = "CompanyGroup";
    /**
     * The Constant PS_DESIGNATION.
     */
    public static final String PS_DESIGNATION = "PS_DESIGNATION";

    /**
     * The Constant PS_CATEGORY.
     */
    public static final String PS_CATEGORY = "PS_CATEGORY";

    /**
     * The Constant TRADE_CLASS.
     */
    public static final String PS_TRADE_CLASS = "PS_TRADE_CLASS";
    public static final String CFP_CATEGORY = "CFP_CATEGORY";

    public static final String CFP_TRADE_CLASS = "CFP_TRADE_CLASS";
    /**
     * loadColumnName
     *
     * @return
     */
    public static HashMap<String, String> loadColumnName() {
        contractHeaderColumns.put(Constants.CONTRACT_ID, "contractId");
        contractHeaderColumns.put(Constants.CONTRACT_NO, "contractNo");
        contractHeaderColumns.put(Constants.CONTRACT_NAME, "contractName");
        contractHeaderColumns.put(Constants.CONTRACT_STATUS, "contractStatus");
        contractHeaderColumns.put(Constants.CONTRACT_TYPE, "contractType");
        contractHeaderColumns.put(Constants.TRADE_CLASS, "tradeClass");
        contractHeaderColumns.put(Constants.TRADING_PARTNER_NAME, "contHoldCompanyMasterSid");

        return contractHeaderColumns;
    }

    /**
     * getDBColumnName
     *
     * @param visibleColumnName
     * @return
     */
    public static String getDBColumnName(String visibleColumnName) {
        return contractHeaderColumns.get(visibleColumnName);
    }

    /**
     * Empty Constructor
     */
    private UIUtils() {
        //Empty Constructor
    }
}
