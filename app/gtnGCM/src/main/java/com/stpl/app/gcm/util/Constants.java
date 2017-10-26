package com.stpl.app.gcm.util;

import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.ifs.util.HelperDTO;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author alok.v
 */
public class Constants {

    /**
     * The null.
     */
    public static final String NULL = "null";
    /**
     * The session id
     */
    public static final String SESSION_ID = "sessionId";
    /**
     * The empty.
     */
    public static final String EMPTY = "";
    /**
     * The user id.
     */
    public static final String USER_ID = "userId";
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    public static final String CONTRACT_TYPE = "CONTRACT_TYPE";
    public static final String LIST_NAME = "listName";
    public static final String ALL = "ALL";
    public static final String ZEROSTRING = "0";
    public static final String CONTRACT_ALIAS_TYPE = "CONTRACT_ALIAS_TYPE";
    public static final String CFP_STATUS = "STATUS";
    public static final String CFP_TYPE = "CFP_TYPE";
    public static final String CFP_CATAGORY = "CFP_CATEGORY";
    public static final String TRADE_CLASS = "CFP_TRADECLASS";
    public static final String CFP_DESIGNATION = "CFP_DESIGN";
    public static final String DESCRIPTION = "description";
    public static final String SELECT_ONE = "-Select One-";
    public static final String IFP_TYPE = "IFP_TYPE";
    public static final String PS_TYPE = "PS_TYPE";
    public static final String RS_TYPE = "RS_TYPE";
    public static final String COMPANY_FAMILY_PLAN = "Company Family Plan";
    public static final String ITEM_FAMILY_PLAN = "Item Family Plan";
    public static final String PRICE_SCHEDULE = "Price Schedule";
    public static final String REBATE_SCHEDULE = "Rebate Schedule";
    public static final String CHECK = "check";
    public static final String LEVELNO = "levelNo";
    public static final String HIDDEN_ID = "hiddenId";
    public static final String CATEGORY = "category";
    public static final String COMPANY_NO = "companyNo";
    public static final String DASHBOARD_ID = "dashboardId";
    public static final String DASHBOARD_NAME = "dashboardName";
    public static final String CFP_Id = "CFPId";
    public static final String IFP_Id = "IFPId";
    public static final String DASHBOARD_NUMBER = "dashboardNumber";
    public static final String MARKET_TYPE = "marketType";
    public static final String ERROR = "Error";
    public static final String SAVED_SYSTEM_ID = "savedSystemId";
    public static final String MODEL_ID = "modelId";
    public static final String[] CFP_SEARCH = {"CFP No", "CFP Name", "CFP ID", "CFP Status", "CFP Type", "Company ID", "Company No", "Company Name", "Company Status"
            ,"Company Type", "Company Category", "Trade Class"};
    public static final String[] IFP_SEARCH = {Constants.IFP_NO, Constants.IfpNAME, Constants.IFP_ID, "IFP Status", "IFP Type", "Item ID", "Item No", "Item Name"
    ,"Item Status", "Item Type", "Brand", "Form", "Strength", "Therapy Class", "Item Start Date", "Item End Date"};
    public static final String[] PS_SEARCH = {"PS No", "PS Name", "PS ID", "PS Status", "PS Type"};
    public static final String[] RS_SEARCH = {"RS No", "RS Name", "RS ID", "RS Status", "RS Type"};
    public static final String ID = "ID :";
    public static final String NAME = "Name :";
    public static final String NUMBER = "Number :";
    public static final String STATUS = "STATUS";
    public final static String COMP_TRADE_CLASS = "COMP_TRADECLASS";
    public final static String COMPANY_CATEGORY = "COMP_CATEGORY";
    public final static String COMPANY_TYPE = "COMP_TYPE";
    public static final String RS_PROGRAM_TYPE = "RS_PROGRAM_TYPE";
    public static final String ALPHA_NUM_VALIDATION = "([0-9|a-z|A-Z|\\_|\\*|\\s])*";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String COMMA = ",";
    public static final String[] ITEM_SEARCH = {Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_STATUS, Constants.ITEM_TYPE, Constants.BRAND, Constants.FORM, Constants.STRENGTH, Constants.THERAPY_CLASS, Constants.ITEM_START_DATE, Constants.ITEM_END_DATE};
    public static final String CFP = "CFP";
    public static final String IFP = "IFP";
    public static final String PS = "PS";
    public static final String RS = "RS";
    public static final String COMPANY_NAME = "companyName";
    public static final String COMPANY_STATUS = "companyStatus";
    public static final String COMPANY_START_DATE = "companyStartDate";
    public static final String COMPANY_END_DATE = "companyEndDate";
    public static final String CONTRACT_START_DATE = "contractStartDate";
    public static final String CONTRACT_END_DATE = "contractEndDate";
    public static final String PS_NAME = "PSname";
    public static final String RS_NAME = "RSname";
    public static final String HUNDRED_PERCENT = "100%";
    public static final String NINE_SEVENTY_PXL = "970px";
    public static final String PS_ID = "PSId";
    public static final String RS_ID = "RSId";
    public static final String A = "A";
    public static final String ALIAS_TYPE = "aliasType";
    public static final String ALIAS_NUMBER = "aliasNumber";
    public static final String ALIAS_START_DATE = "aliasstartdate";
    public static final String ALIAS_END_DATE = "aliasenddate";
    public static final String SELECT_CORRECT_NODE = "Please Select Correct Node";
    public static final String STATUS_S = "status";
    public static final String[] COMPANY_SEARCH = {Constants.COMPANY_ID, Constants.COMPANYNAME, Constants.COMPANYNO, "Company Status", Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS};
    /**
     * The Constant REBATE_PLAN_LEVEL.
     */
    public static final String REBATE_PLAN_LEVEL = "RS_RP_LEVEL";
    /**
     * The Constant PAYMENT_FREQUENCY.
     */
    public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
    /**
     * The Constant PAYMENT_METHOD.
     */
    public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
    /**
     * The Constant REBATE_SCHEDULE_TYPE.
     */
    public static final String REBATE_SCHEDULE_TYPE = "RS_TYPE";
    public static final String CONTRACT_HOLDER = "contractHolder";
    public static final String CONTRACT_NO = "contractNo";
    public static final String CONTRACT_NAME = "contractName";
    public static final String IFP_NAME = "IFPname";
    public static final String START_DATE = "startDate";
    public static final String END_DATE = "endDate";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    public static final String PERCENT = "%";
    /**
     * The date format.
     */
    public final static String DATE_FORMAT = "MM/dd/yyyy";
    /**
     * The date format.
     */
    public final static String DATE_FIEILD_CENTER = "dateFieldCenter";
    public static final String ANNUALLY = "Annually";
    public static final String SEMIANNUALLY = "Semi-Annually";
    public static final String QUARTERLY = "Quarterly";
    public static final String MONTHLY = "Monthly";
    public static final String SPACE = " ";
    public final static String SHOW_ALL = "Show All";
    public final static String bootstrap = "bootstrap";
    public final static String bootstrap_forecast_bootstrap_nm = "bootstrap-forecast bootstrap-nm";
    public final static String IFP_STATUS = "IFP Status";
    public final static String IFPTYPE = "IFP Type";
    public final static String COMPANYSTATUS = "Company Status";
    public final static String COMPANYCATEGORY = "Company Category";
    public final static String COMPANYTYPE = "Company Type";
    public final static String FORM = "Form";
    public final static String CFP_NAME = "cfpName";
    public final static String IFPNAME = "ifpName";
    public final static String PSNAME = "psName";
    public final static String RSNAME = "rsName";
    public final static String RS_START_DATE = "rsStartDate";
    public final static String RS_END_DATE = "rsEndDate";
    public final static String CFP_START_DATE = "cfpStartDate";
    public final static String CFP_END_DATE = "cfpEndDate";
    public final static String PS_START_DATE = "psStartDate";
    public final static String PS_END_DATE = "psEndDate";
    public final static String IFP_START_DATE = "ifpStartDate";
    public final static String IFP_END_DATE = "ifpEndDate";
    public final static String COMPANY_ID = "Company ID";
    public final static String COMPANYNAME = "Company Name";
    public final static String COMPANYNO = "Company No";
    public final static String TRADECLASS = "Trade Class";
    public final static String ITEM_ID = "Item ID";
    public final static String ITEM_NAME = "Item Name";
    public final static String ITEM_NO = "Item No";
    public final static String ITEM_STATUS = "Item Status";
    public final static String ITEM_TYPE = "Item Type";
    public final static String BRAND = "Brand";
    public final static String STRENGTH = "Strength";
    public final static String THERAPY_CLASS = "Therapy Class";
    public final static String ITEM_START_DATE = "Item Start Date";
    public final static String ITEM_END_DATE = "Item End Date";
    public final static String IFP_ID = "IFP ID";
    public final static String IfpNAME = "IFP Name";
    public final static String IFP_NO = "IFP No";
    public final static String TRUE = "true";
    public final static String IS_ORDERED = "isOrdered";
    public final static String CFP_CONTRACT_SID = "cfpContractSid";
    public final static String IFP_CONTRACT_SID = "ifpContractSid";
    public final static String DBDATE_FORMAT = "yyyy-MM-dd";
    public static final Object SUMMARY_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", Constants.CFP_NAME,
        Constants.IFPNAME, "pSName", "rSName", "rARCategory", "tpstatus", "compStartDate", "compEndDate"};
    public static final Object SUMMARY_TRANSFER_TP_COLUMNS[] = new Object[]{
        "checkRecord", "contractHolder", "contractNo", "contractName", "contractType", "contStartDate", "contEndDate", "cfpName",
        "ifpName", "pSName", "rSName", "rARCategory", "statusString", "compStartDate", "compEndDate"};
    public static final String SUMMARY_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Contract Start Date", "Contract End Date", "CFP Name",
        Constants.IfpNAME, "PS Name", "RS Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        "checkRecord", "projectionIdLink", "workflowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", "status", "compStartDate", "compEndDate", Constants.CFP_NAME,
        "cfpNo", Constants.IFPNAME, "ifpNo", "pSName", "pSNo", "rSName", "rSNo",
        "rARCategory"};
    public static final String CONTRACT_SELECTION_HEADERS[] = new String[]{
        "", "Projection ID", "Workflow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", "Company Start Date", "Company End Date", "CFP Name",
        "CFP No", Constants.IfpNAME, Constants.IFP_NO, "PS Name", "PS No", "RS Name", "RS No",
        "RAR Category",};
    public static final Object ADD_TP_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", Constants.CFP_NAME,
        "cfpNo", Constants.IFPNAME, "ifpNo", "pSName", "pSNo", "rSName", "rSNo",
        "rARCategory", "status", "compStartDate", "compEndDate"};
    public static final String ADD_TP_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Contract Start Date", "Contract End Date", "CFP Name",
        "CFP No", Constants.IfpNAME, Constants.IFP_NO, "PS Name", "PS No", "RS Name", "RS No",
        "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object PROMOTE_TP_RESULTS_COLUMNS[] = new Object[]{
        "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public static final String PROMOTE_TP_RESULTS_HEADERS[] = new String[]{
        "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip"};
    public static final Object TP_COMPANY_SEARCH_COLUMNS[] = new Object[]{
        "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip", "parentNo", "parentName"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public static final String TP_COMPANY_SEARCH_HEADERS[] = new String[]{
        "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip", "Parent No", "Parent Name"};
    public static final Object DISCOUNT_SEARCH_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME};
    public static final String DISCOUNT_SEARCH_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date",
        "End Date", "Company Family Plan", "Item Family Plan", Constants.PRICE_SCHEDULE, "Rebate Schedule"};
    public static final Object COMPONENT_RESULTS_COLUMNS[] = new Object[]{
        "contractId", Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractStatus", Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, "frequency", "rarType", "basis"};
    public static final String COMPONENT_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Name", "Status", "Start Date",
        "End Date", "CFP", "IFP", "PS", "Frequency", "RAR Type", "Basis"};
    public static final Object TREE_COLUMNS[] = new Object[]{
        "category", "id", "number", "name"};
    public static final String TREE_HEADERS[] = new String[]{
        "Component", "ID", "Number", "Name"};
    public static final Object SELECTED_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand",
        "itemStatus", "itemStartDate", "itemEndDate", "rebatePlan", "formulaId", "formulaName", "formulaType", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String SELECTED_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID", "Formula Name", "Formula Type", "Rebate Plan ID", "Rebate Plan Name", "Rebate amount", "Bundle No", "Attached Date"};
    public static final Object COMPONENT_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "status", "itemStartDate", "itemEndDate"};
    /**
     * The Constant COMPONENT_DETAILS_HEADERS.
     */
    public static final String COMPONENT_DETAILS_HEADERS[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object TP_COMPONENT_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "status", "itemStartDate", "itemEndDate"};
    public static final String TP_COMPONENT_DETAILS_HEADERS[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    /**
     * The Constant CONTRACT_COMP_RESULTS_COLUMNS.
     */
    public static final Object CONTRACT_COMP_RESULTS_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME,
        Constants.PSNAME, Constants.RSNAME};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public static final String CONTRACT_COMP_RESULTS_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "IFP",
        "PS", "RS"};
    public static final Object CONTRACT_DASHBOARD_RESULTS_COLUMNS[] = new Object[]{
        "category", "id", "number", "name"};
    /**
     * The Constant CONTRACT_DASHBOARD_RESULTS_HEADERS.
     */
    public static final String CONTRACT_DASHBOARD_RESULTS_HEADERS[] = new String[]{
        "Category", "ID", "Number", "Name"};
    public static final Object COMP_ITEM_RESULTS_COLUMNS[] = new Object[]{
        "checkRecord", "itemId", "itemNo", "itemName", "therapyClass", "brand", "form", "strength", "status",
        Constants.START_DATE, Constants.END_DATE};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public static final String COMP_ITEM_RESULTS_HEADERS[] = new String[]{
        "", "ID", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
        "Status", "Start Date", "End Date"};
    public static final Object ADD_TRADING_PARTNER_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "cfp",
        "ifp", "ps", "rs"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public static final String ADD_TRADING_PARTNER_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date",
        "CFP", "IFP", "PS", "RS"};
    public static final Object CONTRACT_DASHBOARD_COLUMNS[] = new Object[]{
        "component", "id", "number", "name"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public static final String CONTRACT_DASHBOARD_HEADERS[] = new String[]{
        "Component", "ID", "Number", "Name"};
    public static final Object ITEM_SEARCH_RESULTS_COLUMNS[] = new Object[]{
        "checkRecord", "itemId", "itemNo", "itemName", "therapyClass", "brand", "form",
        "strength", "itemStatus", "itemStartDate", "itemEndDate"};
    public static final String ITEM_SEARCH_RESULTS_HEADERS[] = new String[]{
        "", "Id", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM,
        Constants.STRENGTH, "Status", "Start Date", "End Date"};
    public static final Object CONTRACT_COMPONENT_DETAILS_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand",
        "itemStatus", "itemStartDate", "itemEndDate"};
    public static final String CONTRACT_COMPONENT_DETAILS_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        "Status", "Start Date", "End Date"};
    public static final Object RS_RESULTS_COLUMNS[] = new Object[]{
        "id", "number", "name", "status", Constants.START_DATE,
        Constants.END_DATE, Constants.IFPNAME, "frequency", "rarType", "basis"};
    public static final String RS_RESULTS_HEADERS[] = new String[]{
        "Id", "Number", "Name", "Status",
        "Start Date", "End Date", "IFP", "Frequency", "RAR Type", "Basis"};
    public static final Object SUMMARY_TP_RESULTS_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.CFP_NAME,
        Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME, "rarCategory", "status", "companyStartDate", "companyEndDate"};
    /**
     * The Constant SUMMARY_TP_RESULTS_HEADERS.
     */
    public static final String SUMMARY_TP_RESULTS_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "CFP Name",
        Constants.IfpNAME, "PS Name", "RS Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    /**
     * CURRENT_TRADING_PARTNER_COLUMNS
     */
    public static final Object CURRENT_TRADING_PARTNER_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "rebateScheduleNo",
        "rebateScheduleName", "rarCategory", "status", "companyStartDate", "companyEndDate"};
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public static final String CURRENT_TRADING_PARTNER_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Rebate Schedule No",
        "Rebate Schedule Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object[] PTP_COMPONENT_INFO_COLUMNS_IFP = new Object[]{"checkRecord", "itemNo", "itemName", "therapyClass", "brand", "statusId", "itemStartDate", "itemEndDate"};
    public static final String PTP_COMPONENT_INFO_HEADERS_IFP[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object[] PTP_COMPONENT_INFO_COLUMNS_PS = new Object[]{"checkRecord", "itemNo", "itemName", "therapyClass", "brand", "statusId", "itemStartDate", "itemEndDate"};
    public static final String PTP_COMPONENT_INFO_HEADERS_PS[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object[] PTP_COMPONENT_INFO_COLUMNS = new Object[]{"checkRecord", "itemNo", "itemName", "therapyClass", "brand", "statusId", "itemStartDate", "itemEndDate", "rebatePlan", "formulaId"};
    public static final String PTP_COMPONENT_INFO_HEADERS[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"};
    public static final Object TP_RESULTS_COLUMNS[] = new Object[]{
        "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip"
    };
    public static final String TP_RESULTS_HEADERS[] = new String[]{
        "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip"
    };
    public static final Object TP_COMPONENT_INFORMATION_COLUMNS_RS[] = new Object[]{
        "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String TP_COMPONENT_INFORMATION_HEADERS_RS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Formula Type", "Formula Id", "Formula Name", "Rebate Plan Id", "Rebate Plan Name", "Rebate Amount", "Bundle No", "Attached Date"};
    public static final Object TP_COMPONENT_INFORMATION_COLUMNS_PS[] = new Object[]{
        "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate",
        "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType", "maxIncrementalChange", "priceTolerance",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "attachedDate"};
    public static final String TP_COMPONENT_INFORMATION_HEADERS_PS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
        "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Max Incremental Change", "Price Tolerance",
        "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object TP_COMPONENT_INFORMATION_COLUMNS_IFP[] = new Object[]{
        "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "attachedDate"};
    public static final String TP_COMPONENT_INFORMATION_HEADERS_IFP[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Attached Date"};
    public static final Object TP_COMPONENT_INFORMATION_COLUMNS_CFP[] = new Object[]{
        "companyNo", "companyName", "companyStatus", Constants.START_DATE, Constants.END_DATE, "status",
        "tradeClass", "attachedDate"};
    public static final String TP_COMPONENT_INFORMATION_HEADERS_CFP[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, "Company Status", "Start Date", "End Date", "Status",
        Constants.TRADECLASS, "Attached Date"};
    public static final Object EXISTING_SELECTED_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand",
        "itemStatus", "itemStartDate", "itemEndDate", "rebatePlan", "formulaId"};
    public static final String EXISTING_SELECTED_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"};
    public static final Object PARENT_COMPANY_SEARCH_COLUMNS[] = new Object[]{
        "companyId", "companyNo", "companyName", "companyStatus", "companyType"};
    public static final String PARENT_COMPANY_SEARCH_HEADERS[] = new String[]{
        Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, "Company Status", Constants.COMPANYTYPE};
    public static final Object PROMOTE_TP_CONTRACT_DASHBOARD_TREE_COLUMNS[] = new Object[]{
        "category", "contractId", Constants.CONTRACT_NO, Constants.CONTRACT_NAME};
    public static final String PROMOTE_TP_CONTRACT_DASHBOARD_TREE_HEADERS[] = new String[]{
        "Category", "ID", "Number", "Name"};
    public static final Object CFP_SEARCH_COLUMNS[] = new Object[]{
        "cfpId", "cfpNo", Constants.CFP_NAME, "cfpType", "cfpCategory", "cfpDesignation",
        "cfpPlanId", "cfpPlanName", "cfpStatus", "cfpTradeClass", Constants.CFP_START_DATE, Constants.CFP_END_DATE};
    public static final String CFP_SEARCH_HEADERS[] = new String[]{
        "CFP ID", "CFP No", "CFP Name", "CFP Type", "CFP Category", "CFP Designation",
        "CFP Plan ID", "CFP Plan Name", "CFP Status", "CFP Trade Class", "CFP Start Date", "CFP End Date"};
    public static final Object IFP_SEARCH_COLUMNS[] = new Object[]{
        "ifpNo", Constants.IFPNAME, "ifpType", "ifpCategory", "ifpDesignation",
        "ifpPlanId", "ifpPlanName", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public static final String IFP_SEARCH_HEADERS[] = new String[]{
        Constants.IFP_NO, Constants.IfpNAME, "IFP Type", "IFP Category", "IFP Designation",
        "IFP Plan ID", "IFP Plan Name", "IFP Status", "IFP Start Date", "IFP End Date"};
    public static final Object PS_SEARCH_COLUMNS[] = new Object[]{
        "psNo", Constants.PSNAME, "psType", "psCategory", "psTradeClass", "psDesignation", "parentPsId",
        "parentPsName", "psStatus", Constants.PS_START_DATE, Constants.PS_END_DATE};
    public static final String PS_SEARCH_HEADERS[] = new String[]{
        "PS No", "PS Name", "PS Type", "PS Category", "PS Trade Class", "PS Designation",
        "Parent PS ID", "Parent PS Name", "PS Status", "PS Start Date", "PS End Date"};
    public static final Object RS_SEARCH_COLUMNS[] = new Object[]{
        "rsId", "rsNo", Constants.RSNAME, "rsType", "rebateProgramType", "rsCategory", "rsTradeClass",
        "rsDesignation", "parentrsId", "parentrsName", "rsStatus", Constants.RS_START_DATE, Constants.RS_END_DATE};
    public static final String RS_SEARCH_HEADERS[] = new String[]{
        "RS ID", "RS No", "RS Name", "RS Type", "Rebate Program Type", "RS Category", Constants.TRADECLASS,
        "RS Designation", "Parent RS ID", "Parent RS Name", "RS Status", "RS Start Date", "RS End Date"};
    public static final Object CH_SEARCH_COLUMNS[] = new Object[]{
        "chId", "chNo", "chName", "chStatus", "chType"};
    public static final String CH_SEARCH_HEADERS[] = new String[]{
        "Contract Holder ID", "Contract Holder No", "Contract Holder Name", "Contract Holder Status", "Contract Holder Type"};
    public static final Object CUST_SEARCH_COLUMNS[] = new Object[]{
        "custId", "custNo", "custName", "custStatus", "custType"};
    public static final String CUST_SEARCH_HEADERS[] = new String[]{
        "Customer ID", "Customer No", "Customer Name", "Customer Status", "Customer Type"};
    public static final Object ITEM_SEARCH_COLUMNS[] = new Object[]{
        "itemId", "itemNo", "itemName", "itemStatus", "itemType"};
    public static final String ITEM_SEARCH_HEADERS[] = new String[]{
        Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_STATUS, Constants.ITEM_TYPE};
    public static final Object PROMOTE_TP_CONTRACT_DASHBOARD_TREE_COLUMNS_TRANSFER[] = new Object[]{
        "category", "dashboardId", "dashboardNumber", "dashboardName"};
    public static final HelperDTO HELPER_DTO = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    public static final Object EXCEL_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        ConstantsUtil.PROJECTION_ID, "workflowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", "compStartDate", "compEndDate", "cfpNo", Constants.CFP_NAME,
        "ifpNo", Constants.IFPNAME, "pSNo", "pSName", "rSName", "rSNo",
        "rARCategory"};
    public static final String EXCEL_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "Projection Id", "Workflow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Company Start Date", "Company End Date", "CFP No", "CFP Name",
        Constants.IFP_NO, Constants.IfpNAME, "PS No", "PS Name", "RS Name", "RS No",
        "RAR Category"};
    public static final Object COMPANY_SEARCH_COLUMNS[] = new Object[]{
        "check", "companySystemId", "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip", "parentNo", "parentName"};
    public static final String COMPANY_SEARCH_HEADERS[] = new String[]{
        "", "System ID", Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip", "Parent No", "Parent Name"};
    public static final Object COMPANY_SEARCH_COLUMNS_WITHOUT_CHECK[] = new Object[]{
        "companyId", "companyNo", "companyName", "companyType", "companyCategory", "tradeClass",
        "address1", "address2", "city", "state", "zip", "parentNo", "parentName"};
    public static final String COMPANY_SEARCH_HEADERS_WITHOUT_CHECK[] = new String[]{
        Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS,
        "Address 1", "Address 2", "City", "State", "Zip", "Parent No", "Parent Name"};
    public static final Object LINKED_COMPANY_COLUMNS[] = new Object[]{
        "check", "fromCompanyId", "fromCompanyNo", "fromCompanyName", "toCompanyId", "toCompanyNo", "toCompanyName"};
    public static final String LINKED_COMPANY_HEADERS[] = new String[]{
        "", "Company ID - From", "Company No - From", "Company Name - From", "Company ID - To", "Company No - To", "Company Name - To",};
    public static final Object PTP_COMPONENT_DETAILS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "statusId", Constants.START_DATE, Constants.END_DATE};
    /**
     * The Constant COMPONENT_DETAILS_HEADERS.
     */
    public static final String PTP_COMPONENT_DETAILS_HEADERS[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object EXL_CURRENT_TRADING_PARTNER_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "rebateScheduleNo",
        "rebateScheduleName", "rarCategory", "status", "companyStartDate", "companyEndDate"};
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public static final String EXL_CURRENT_TRADING_PARTNER_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Rebate Schedule No",
        "Rebate Schedule Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object EXL_CONTRACT_COMP_RESULTS_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME,
        Constants.PSNAME, Constants.RSNAME};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public static final String EXL_CONTRACT_COMP_RESULTS_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "IFP",
        "PS", "RS"};
    public static final Object EXL_COMP_ITEM_RESULTS_COLUMNS[] = new Object[]{
        "itemId", "itemNo", "itemName", "therapyClass", "brand", "form", "strength", "status",
        "itemStartDate", "itemEndDate"};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public static final String EXL_COMP_ITEM_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
        "Status", "Start Date", "End Date"};
    public static final Object EXL_PTP_COMPONENT_INFO_COLUMNS[] = new Object[]{"itemNo", "itemName", "therapyClass", "brand", "status", "itemStDate", "itemEdDate", "rebatePlan", "formulaId"};
    public static final String EXL_PTP_COMPONENT_INFO_HEADERS[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"};
    public static String ADDBY = "addBy";

    /**
     * Enum for Module/Screen/Functionality indicator constants.
     */
    public enum IndicatorConstants {

        PROMOTE_TP_TO_CONTRACT_HOLDER_INDEX("PromoteTpToContractHolder"),
        GLOBAL_CHANGE_INDEX("GlobalChangeManagement"),
        CUSTOMER_EXISTING("Existing"),
        CUSTOMER_NEW("New"),
        COMPANY_FAMILY_PLAN("Company Family Plan"),
        ITEM_FAMILY_PLAN("Item Family Plan"),
        CONTRACT("Contract"),
        ITEM_CONTRACT("Item/Contract"),
        SELECT_ONE("-Select One-"),
        CUSTOMER_MANAGEMENT("Customer Management"),
        CONTRACT_MANAGEMENT("Contract Management"),
        ITEM_MANAGEMENT("Item Management"),
        PROMOTE_TRADING_PARTNER("Promote Customer"),
        CUSTOMER_CONTRACT("Customer/Contract"),
        REMOVE_DISCOUNT("Remove Discount"),
        ADD_DISCOUNT("Add Discount"),
        TAB_CONTRACT_SELECTION("Current Contract"),
        TAB_TRANSFER_CONTRACT("Transfer Contract"),
        TAB_SUMMARY("Summary"),
        ADD_TRADING_PARTNER("Add Customer"),
        TRANSFER_TRADING_PARTNER("Transfer Customer"),
        PROJECTION_DETAILS_TRANSFER("Projection Details Transfer"),
        TRADING_PARTNER_REMOVE("Remove Customer"),
        TRADING_PARTNER_UPDATE("Update Customer"),
        ITEM_UPDATE("Item Update"),
        COPY_CONTRACT("Copy Contract"),
        CONTRACT_TRANSFER("Contract Transfer"),
        TAB_CUSTOMER_SELECTION("Customer Selection"),
        TAB_CURRENT_CONTRACT("Current Contract"),
        EXCEL_IMAGE_PATH("../../icons/excel.png"),
        CURRENT_CONTRACT("Current Contract"),
        CONTRACT_SELECTION("Contract Selection"),
        ENABLE("Enable"),
        DISABLE("Disable"),
        TRADING_PARTNER_DETAILS("Customer Details"),
        SALES("Sales"),
        UNITS("Units"),
        SALES_PROJECTION("Sales Projection"),
        REBATES_PROJECTION("Rebates Projection"),
        SALES_AND_REBATES_PROJECTION("Sales & Rebates Projection"),
        SALES_TRANSFER("Sales Transfer"),
        AMOUNT("Amount"),
        RATE("Rate"),
        YES("Yes"),
        NO("No"),
        CONTRACT_ALIAS_TYPE("CONTRACT_ALIAS_TYPE"),
        TAB_PROMOTE_TP_TRANSFER_COMPONENTS("Transfer Components"),
        TAB_PROMOTE_TP_NEW_COMPONENTS("New Components"),
        TAB_SUMMARY_TP_DETAILS("Customer Details"),
        TAB_SALES_PROJECTION_TRANSFER("Sales Projection Transfer"),
        ACTIVE("Active"),
        IN_ACTIVE("Inactive"),
        /**
         * The date format.
         */
        DATE_FORMAT("MM/dd/yyyy"),
        CHAR_PERCENT("%"),
        /**
         * The char asterisk.
         */
        CHAR_ASTERISK("*"),
        /**
         * The cfp.
         */
        CFP("CFP"),
        /**
         * The ifp.
         */
        IFP("IFP"),
        /**
         * The ps value.
         */
        PS_VALUE("PS"),
        /**
         * The rs value.
         */
        RS_VALUE("RS"),
        /**
         * The primary key item family plan system id.
         */
        IFP_CONTRACT_SID("ifpContractSid"),
        /**
         * The primary key price schedule system id.
         */
        PS_CONTRACT_SID("psContractSid"),
        /**
         * The primary key contract system id.
         */
        CONTRACT_MASTER_SID("contractMasterSid"),
        /**
         * The primary key company plan system id.
         */
        CFP_CONTRACT_SID("cfpContractSid"),
        INBOUND_STATUS("inboundStatus"),
        PRICE_SCHEDULE(Constants.PRICE_SCHEDULE),
        REBATE_SCHEDULE("Rebate Schedule"),
        ITEM_MASTER("Item Master"),
        PROMOTE_TP_REBATE_SCHEDULE_NAME("Rebate Schedule Name"),
        PROMOTE_TP_REBATE_FREQUENCY("Quarterly"),
        PROMOTE_TP_SEARCH_FIELD("Brand Name"),
        TAB_PROMOTE_TP_EXISTING_COMPONENTS("Existing Components"),
        BRAND("Brand Name"),
        STATUS("STATUS"),
        RS_TYPE("RS_TYPE"),
        CFP_TYPE("CFP_TYPE"),
        IFP_TYPE("IFP_TYPE"),
        TP_COMPANY_END_DATE("Company End Date"),
        INDICATOR_CONTRACT_HOLDER("Contract Holder"),
        PS_TYPE("PS_TYPE"),
        RSCONTRACTSID("rsContractSid"),
        RS_IFP_FOR_ADD_DISCOUNT("RS_IFP_FOR_ADD_DISCOUNT"),
        PS_IFP_FOR_ADD_DISCOUNT("PS_IFP_FOR_ADD_DISCOUNT"),
        SELECTED_RS_IFP_FOR_ADD_DISCOUNT("SELECTED_RS_IFP_FOR_ADD_DISCOUNT"),
        SELECTED_PS_IFP_FOR_ADD_DISCOUNT("SELECTED_PS_IFP_FOR_ADD_DISCOUNT"),
        PR_TP_STATUS("Status"),
        PR_TP_START_DATE("Start Date"),
        PR_TP_END_DATE("End Date"),
        ITEMS_FOR_IFP_IN_ADD_DISCOUNT("ITEMS_FOR_IFP_IN_ADD_DISCOUNT"),
        COMPANYS_FOR_CFP_IN_ADD_DISCOUNT("COMPANYS_FOR_CFP_IN_ADD_DISCOUNT"),
        SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT("SELECTED_ITEMS_FOR_IFP_IN_ADD_DISCOUNT"),
        SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT("SELECTED_COMPANYS_FOR_CFP_IN_ADD_DISCOUNT"),
        COMP_CATEGORY("COMP_CATEGORY"),
        COMP_TRADECLASS("COMP_TRADECLASS"),
        FORM("FORM"),
        STRENGTH("STRENGTH"),
        ITEM_THERP_CLASS("ITEM_THERP_CLASS"),
        MANDATED("Mandated"),
        NON_MANDATED("Non Mandated");
        /**
         * The constant.
         */
        private String constant;

        /**
         * Instantiates a new indicator constants.
         *
         * @param constant the constant
         */
        private IndicatorConstants(String constant) {
            this.constant = constant;
        }

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        public String getConstant() {
            return constant;
        }

        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return constant;
        }
    }

    public enum FieldConstants {

        RS_NUMBER("rsNumber"),
        STATUS("status"),
        REBATE_FREQUENCY("rebateFrequency"),
        START_DATE(Constants.START_DATE),
        RS_ID("rsId"),
        RS_NAME(Constants.RSNAME),
        RAR_TYPE("rarType"),
        BASIS("basis"),
        END_DATE(Constants.END_DATE);
        /* The constant */
        private String constant;

        /**
         * Instantiates a new indicator constants.
         *
         * @param constant the constant
         */
        private FieldConstants(String constant) {
            this.constant = constant;
        }

        /**
         * Gets the constant.
         *
         * @return the constant
         */
        public String getConstant() {
            return constant;
        }

        /**
         * To string.
         *
         * @return the string
         */
        @Override
        public String toString() {
            return constant;
        }
    }
    public static final String COPYCONTRACT_RESULTS_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Company Family Plan", "Item Family Plan", Constants.PRICE_SCHEDULE,
        "Rebate Schedule"
    };
    public static final Object COPYCONTRACT_RESULTS_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "CFPname", "IFPname", "PSname", "rebateScheduleName"
    };
    public static final String COPYCONTRACT_RB_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Name", "therapyClass", Constants.BRAND, Constants.FORM, Constants.STRENGTH, "status", "Start Date",
        "End Date"
    };
    public static final Object COPYCONTRACT_RB_RESULTS_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "CFPname", "IFPname", "PSname", "rebateScheduleName"
    };
    public static final String COPYCONTRACT_DASHBOARD_RESULTS_HEADERS[] = new String[]{
        "Category", "ID", "Number", "Name"
    };
    public static final Object COPYCONTRACT_DASHBOARD_RESULTS_COLUMNS[] = new Object[]{
        "category", "dashboardId", "dashboardNumber", "dashboardName"
    };
    public static final String COPYCONTRACT_CFP_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Status", "Start Date", "End Date"
    };
    public static final Object COPYCONTRACT_CFP_RESULTS_COLUMNS[] = new Object[]{
        "companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanStatusValue",
        "companyFamilyPlanStartDate", "companyFamilyPlanEndDate"
    };
    public static final String COPYCONTRACT_IFP_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Status", "Start Date", "End Date"
    };
    public static final Object COPYCONTRACT_IFP_RESULTS_COLUMNS[] = new Object[]{
        "itemFamilyplanId", "itemFamilyplanNo", "displayIFPStatus",
        "ifpStartDate", "ifpEndDate"
    };
    public static final String COPYCONTRACT_PS_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Status", "Start Date", "End Date", "IFP", "Type", "Category", "Designation"
    };
    public static final Object COPYCONTRACT_PS_RESULTS_COLUMNS[] = new Object[]{
        "priceScheduleIdValue", "priceScheduleNoValue", "priceScheduleStatusValue",
        "priceScheduleStartDate", "priceScheduleEndDate", "itemFamilyplanName", "priceScheduleTypeValue", "priceScheduleCategoryValue", "priceScheduleDesignationValue"
    };
    public static final String COPYCONTRACT_RS_RESULTS_HEADERS[] = new String[]{
        "ID", "Number", "Status", "Start Date", "End Date", "IFP", "Frequency", "RAR Type", "Basis"
    };
    public static final Object COPYCONTRACT_RS_RESULTS_COLUMNS[] = new Object[]{
        "rebateScheduleId", "rebateScheduleNo", "statusRebate", "itemRebateStartDate", "itemRebateEndDate", Constants.IFPNAME, "paymentFrequency", "rebateProgramType", "interestBearingBasis"
    };
    public static final String COPYCONTRACT_CFP_COMPANY_RESULTS_HEADERS[] = new String[]{
        Constants.COMPANYNAME, Constants.COMPANYNO, "status", "Start Date", "End Date"
    };
    public static final Object COPYCONTRACT_CFP_COMPANY_RESULTS_COLUMNS[] = new Object[]{
        "companyName", "companyNo", "companyStatusValue", "tradeClassStartDate", "tradeClassEndDate"
    };
    public static final String COPYCONTRACT_IFP_ITEM_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"
    };
    public static final Object COPYCONTRACT_IFP_ITEM_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapeuticClass", "brand", "itemStatus", "itemStartDate", "itemEndDate"};
    public static final String COPYCONTRACT_PS_ITEM_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type",
        "Price Protection Start Date"
    };
    public static final Object COPYCONTRACT_PS_ITEM_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapeuticClass", "brand", "itemStatus", "itemStartDate", "itemEndDate", "priceType",
        "priceProtectionStartDate"};
    public static final String COPYCONTRACT_RS_ITEM_RESULTS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"
    };
    public static final Object COPYCONTRACT_RS_ITEM_RESULTS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapeuticClass", "brand", "itemStatus", "itemStartDate", "itemEndDate", "rebatePlan", "formulaId"
    };
    public static final String ITEM_CONTRACT_SELECTION_DETAILS_HEADER[] = new String[]{
        "", "ID", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, "status", "Start Date",
        "End Date",};
    public static final Object ITEM_CONTRACT_SELECTION_DETAILS_VISIBLE_COLUMN[] = new Object[]{
        Constants.CHECK_RECORD, "id", "number", "itemName", "therapyClass", "brand", "form",
        "strength", "itemStatus", "itemStartDate", "itemEndDate"};
    public static final Object CONTRACT_SELECTION_CURRENT_VISIBLE_COLUMN[] = new Object[]{
        Constants.CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "companyNo", "companyName", "rebateScheduleNo",
        "rebateScheduleName", "rarCategory", "status", "companyStartDate", "companyEndDate"
    };
    public static final String CONTRACT_SELECTION_CURRENT_HEADER[] = new String[]{
        StringUtils.EMPTY, "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", Constants.COMPANYNO, Constants.COMPANYNAME, "Rebate Schedule No",
        "Rebate Schedule Name", "RAR Category", "Status", "Company Start Date", "Company End Date"
    };
    public static final Object CURRENT_IFP_VISIBLE_COLUMN[] = new Object[]{
        Constants.CHECK_RECORD, "ifpId", "ifpNumber", Constants.IFPNAME, "ifpStatus", "ifpType"
    };
    public static final String CURRENT_IFP_HEADER[] = new String[]{
        "", "IFP Id", "IFP Number", Constants.IfpNAME, "IFP Status", "IFP Type"
    };
    public static final String IFP_ITEM_HEADER[] = new String[]{
        "", "ID", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, "status", "Start Date",
        "End Date"
    };
    public static final Object IFP_ITEM_COLUMN[] = new Object[]{
        Constants.CHECK_RECORD, "itemId", "itemNumber", "itemName", "therapyClass", "brand", "form",
        "strength", "itemStatus", "itemStartDate", "itemEndDate"};
    public static final Object AD_CFP_IFP_RESULTS_COLUMNS[] = new Object[]{
        "id", "number", "status", Constants.START_DATE, Constants.END_DATE};
    public static final String AD_CFP_IFP_RESULTS_HEADERS[] = new String[]{
        "Id", "Number", "Status", "Start Date", "End Date"};
    public static final Object AD_PS_RESULTS_COLUMNS[] = new Object[]{
        "id", "number", "status", Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME, "type", "psCategory", "designation"};
    public static final String AD_PS_RESULTS_HEADERS[] = new String[]{
        "Id", "Number", "Status", "Start Date", "End Date", "IFP", "Type", "Category", "Designation"};
    public static final Object AD_COMPONENT_DETAILS_COLUMNS_CFP[] = new Object[]{
        "tpNo", "tpName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, "status", "tradeClass",
        "attachedDate"};
    public static final String AD_COMPONENT_DETAILS_HEADERS_CFP[] = new String[]{
        "Trading Partner No", "Trading Partner Name", "TP Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS,
        "Attached Date"};
    public static final Object AD_COMPONENT_DETAILS_COLUMNS_PS[] = new Object[]{
        "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate",
        "priceProtectionEndDate", "priceProtectionPriceType", "priceToleranceInterval",
        "priceToleranceFrequency", "priceToleranceType", "maxIncrementalChange", "priceTolerance", "resetEligibility",
        "resetType", "resetDate", "resetInterval", "resetFrequency", "attachedDate"};
    public static final String AD_COMPONENT_DETAILS_HEADERS_PS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Price Type", "Price Plan No", "Price Plan Name", "Price Protection  Status", "Price Protection Start Date",
        "Price Protection End Date", " Price Protection Price Type ", "Price Tolerance Interval",
        "Price Tolerance Frequency", "Price Tolerance Type", "Max Incremental Change", "Price Tolerance",
        "Reset Eligibility", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency",
        "Attached Date"};
    public static final Object CONTRACT_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand",
        "itemStatus", "itemStartDate", "itemEndDate"};
    public static final String CONTRACT_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        "Status", "Start Date", "End Date"};
    public static final Object SUMMARY_SEARCH_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME};
    public static final String SUMMARY_SEARCH_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date",
        "End Date", "Company Family Plan", "Item Family Plan", Constants.PRICE_SCHEDULE, "Rebate Schedule"};
    public static final Object ADDTP_TREE_COLUMNS[] = new Object[]{
        "category", "contractId", Constants.CONTRACT_NO, Constants.CONTRACT_NAME};
    public static final Object CONTRACT_INFO_COLUMNS[] = new Object[]{
        "companyNo", "companyName", "companyStatus", "companyStartDate", "companyEndDate"};
    public static final String CONTRACT_INFO_HEADERS[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, "Company Status", "Company Start Date", "Company End Date"};
    public static final Object CONTRACT_COMPONENT_COLUMNS[] = new Object[]{
        "check", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, "contractStartDate", "contractEndDate", "IFPname", "PSname", "RSname"};
    public static final String CONTRACT_COMPONENT_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "IFP", "PS", "RS"};
    public static final Object CC_COMPONENT_DETAILS_COLUMNS[] = new Object[]{
        "companyNo", "companyName", "companyStatus", "companyStartDate", "companyEndDate"};
    public static final String CC_COMPONENT_DETAILS_HEADERS[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, "Company Status", "Start Date", "Company End Date"};
    public static final Object COMPONENT_DETAILS_ITEM_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public static final String COMPONENT_DETAILS_ITEM_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date"};
    public static final Object COMPONENT_DETAILS_PS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE, "priceType", "ppStartDate"};
    public static final String COMPONENT_DETAILS_PS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Protection Start Date"};
    public static final Object COMPONENT_DETAILS_RS_COLUMNS[] = new Object[]{
        "itemNo", "itemName", "therapyClass", "brand", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE, "rebatePlan", "formulaId"};
    public static final String COMPONENT_DETAILS_RS_HEADERS[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula Id"};
    public static final Object AD_COMPONENT_DETAILS_COLUMNS_IFP[] = new Object[]{
        Constants.CHECK_RECORD, "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "attachedDate"};
    public static final String AD_COMPONENT_DETAILS_HEADERS_IFP[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Attached Date"};
    public static final Object[] AD_COMPONENT_DETAILS_RS_COLUMN = {"itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId",
        "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String[] AD_COMPONENT_DETAILS_RS_HEADER = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID",
        "RebatePlan Name", "Rebate Amount", "Bundle No", "Attached Date"};
    public static final String[] AD_COMPONENT_DETAILS_PS_HEADER = {Constants.ITEM_NO, "Max Incremental Change", "Price Tolerance", "Reset Eligible", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object AD_COMPONENT_DETAILS_PS_COLUMN[] = new Object[]{
        "itemNo", "maxIncrementalChange", "priceTolerance", "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "attachedDate"};
    public static final Object COMPONENT_INFO_COLUMNS_CFP[] = new Object[]{
        "companyNo", "companyName", Constants.CONTRACT_NO, Constants.START_DATE, Constants.END_DATE, "status", "tradeClass",
        "attachedDate"};
    public static final String COMPONENT_INFO_HEADERS_CFP[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, "COMPANY Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS,
        "Attached Date"};
    public static final Object COMPONENT_ITEM_SEARCH_COLUMNS_PS[] = new Object[]{
        Constants.CHECK_RECORD, "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "priceProtectionStartDate", "priceProtectionEndDate",
        "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceTolerance",
        "resetEligible", "resetType", "resetDate", "resetInterval", "resetFrequency", "attachedDate"};
    public static final String COMPONENT_ITEM_SEARCH_HEADERS_PS[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
        "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance",
        "Reset Eligiblity", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object COMPONENT_ITEM_SEARCH_COLUMNS_RS[] = new Object[]{
        Constants.CHECK_RECORD, "ifpId", Constants.IFPNAME, "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String COMPONENT_ITEM_SEARCH_HEADERS_RS[] = new String[]{
        "", Constants.IFP_ID, Constants.IfpNAME, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula Id", "Formula Name", "Rebate Plan Id", "Rebate Plan Name", "Rebate Amount", "Bundle No", "Attached Date"};

    /**
     * Enum for Message constants
     */
    public enum MessageConstants {

        SEARCH_COMPLETED("Search Completed"),
        NO_SEARCH_RESULTS("No results found"),
        NO_SEARCH_CRITERIA_TITLE("No Search Criteria"),
        NO_SEARCH_CRITERIA_BODY("No search criteria were found. Please enter search criteria and try again."),
        CONFIRM_DELETION_TITLE("Confirm Deletion"),
        CONFIRM_DELETION_BODY("Are you sure you want to delete record ?#?"),
        NO_RECORDS_TITLE("No Matching Records"),
        NO_TP_SELECTED("Transfer Error"),
        NO_TP_SELECTED_BODY("Please select a value in the Results list view then try again."),
        SELECT_CONTRACT_TITLE("Transfer Error"),
        SELECT_CONTRACT_BODY("Please Select a contract and try again."),
        SEARCHC_COMPONENT_TITLE("Search Error"),
        SELECT_COMPONENT_BODY("Please select a Component and try again."),
        SELECT_FIELD_BODY("Please select a search field and try again."),
        NO_TP_SELECTED_TO_PROMOTE("Transfer Error"),
        EMPTY_SPACE(""),
        NO_SEARCH_CRITERIA_TP("Please enter search criteria."),
        TP_NO_ROW_SELECTED("Error"),
        SELECT_VALUE_BODY("Please enter value and try again."),
        RD_NO_SEARCH_CRITERIA_BODY("Please enter value.");
        private String constant;

        private MessageConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return String.valueOf(constant);
        }
    }
    public static final String CHECK_RECORD = "checkRecord";

    /**
     * Enum for Date format constants
     */
    public enum DateFormatConstants {

        DEFOULT_JAVA_DATE_FORMAT("EEE MMM dd HH:mm:ss z yyyy"),
        DEFOULT_SQL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss.SSS"),
        MMddyyyy("MM/dd/yyyy"),
        yyyyMMddhhmmssSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMddyyyyhhmmss("MM/dd/yyyy hh:mm:ss");
        private String constant;

        private DateFormatConstants(String constant) {
            this.constant = constant;
        }

        public String getConstant() {
            return constant;
        }

        @Override
        public String toString() {
            return constant;
        }
    }
    public static final String ITEM_MANAGEMENT = "Item Management";
    public static final String ITEM_INDEX = "Item Index";
    public static final String ITEM_CONTRACT_SELECTION_DETAILS_HEADER_ADD[] = new String[]{
        StringUtils.EMPTY, Constants.ITEM_NO, "ItemName", "Item id", "UOM", "Package Size", "Start Date",
        "End Date", "Price Type", "Contract Price", "CP StartDate", "CP EndDate", Constants.ITEM_STATUS, "Price Tolerance",
        "Price Prodection StartDate", "Price Prodection EndDate", "Price Tolerence Type", "Price Tolerence Interval", "Price Tolerence Frequency",
        "BasePrice", "Revision Date", "Attached Status", "AttachedDate"
    };
    public static final Object ITEM_CONTRACT_SELECTION_DETAILS_VISIBLE_COLUMN_ADD[] = new Object[]{
        Constants.CHECK_RECORD, "number", "itemName", "id", "UOM", "packageSize", "itemStartDate",
        "itemEndDate", "priceType", "contractPrice", "cpStartDate", "cpEndDate", "itemStatus", "priceTolerance",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceToleranceType", "priceToleranceInterval",
        "priceToleranceFrequency", "basePrice", "revisionDate", "attachedStatus", "attachedDate"};
    public static final Object AD_IFP_RESULTS_COLUMNS[] = new Object[]{
        "id", "number", "status", Constants.START_DATE, Constants.END_DATE};
    public static final String AD_IFP_RESULTS_HEADERS[] = new String[]{
        "Id", "Number", "Status", "Start Date", "End Date"};
    /**
     * PTP_COMPONENT_INFO_COLUMNS
     */
    public static final Object AD_ND_RS_COMPONENT_DETAILS_COLUMNS[] = new Object[]{Constants.CHECK_RECORD, "itemNo", "itemName", "therapyClass", "brand", "status", "sDate", "eDate", "rebatePlan", "formulaName"};
    /**
     * The Constant PTP_COMPONENT_INFO_HEADERS.
     */
    public static final String AD_ND_RS_COMPONENT_DETAILS_HEADERS[] = new String[]{"", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date", "Rebate Plan", "Formula ID"};
    public static final String IFP_ADD_ITEM_HEADER[] = new String[]{
        "", "ID", "Number", "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, "Status", "Start Date",
        "End Date", "IFP Status", "IFP Start Date", "IFP End Date"
    };
    public static final Object IFP_ADD_ITEM_COLUMN[] = new Object[]{
        Constants.CHECK_RECORD, "itemId", "itemNumber", "itemName", "therapyClass", "brand", "form",
        "strength", "itemStatus", "itemStartDate", "itemEndDate", "ifpStatus", Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public static final Object[] IDEN_FORM_COL_ORDER = new Object[]{
        "qualifier", "companyIdentifier", "identifierStatusName", Constants.START_DATE, Constants.END_DATE, "createdDate", "createdBy", "modifiedBy", "modifiedDate"
    };
    /**
     * The Constant IDEN_FORM_COL_HEADER.
     */
    public static final String[] IDEN_FORM_COL_HEADER = new String[]{
        "Company Qualifier Name", "Company Identifier", "Identifier Status", "Start Date", "End Date", "Created Date", "Created By", "Modified By", "Modified Date"};
    /**
     * The Constant TRADE_CLASS_COLUMNS.
     */
    public static final Object[] TRADE_CLASS_COLUMNS = new Object[]{
        "tradeClassName", "tradeStartDate", "tradeEndDate", "createdDate", "createdBy", "modifiedBy", "modifiedDate"
    };
    /**
     * The Constant TRADE_CLASS_HEADERS.
     */
    public static final String[] TRADE_CLASS_HEADERS = new String[]{
        Constants.TRADECLASS, "Trade Class Start Date", "Trade Class End Date", "Created Date", "Created By", "Modified By", "Modified Date"
    };
    public static final Object NEW_COMPANY_DETAILS_COLUMNS[] = new Object[]{
        "check", "companyId", "companyName", "companyNo", Constants.PS_START_DATE, Constants.PS_END_DATE, "companyStatus", "tradeClass", "attachedDate"};
    public static final String NEW_COMPANY_DETAILS_HEADERS[] = new String[]{
        "", "Trading Partner No ", "Trading Partner Name", "TP Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS, "Attached Date"};
    public static final Object NEW_IFP_DETAILS_COLUMNS[] = new Object[]{
        "check", "itemNo", "itemName", "brand", "itemStatus", Constants.PS_START_DATE, Constants.PS_END_DATE, "attachedDate"};
    public static final String NEW_IFP_DETAILS_HEADERS[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Attached Date"};
    public static final Object NEW_PS_DETAILS_COLUMNS[] = new Object[]{
        Constants.CHECK, "itemNo", "itemName", "brand", "itemStatus", Constants.PS_START_DATE, Constants.PS_END_DATE, "priceType", "pricePlanNo", "pricePlanName", "priceProtectionStatus", "companyStartDate", "companyEndDate",
        "priceProtectionPriceType", "priceToleranceInterval", "priceToleranceFrequency", "priceToleranceType", "maxIncrementalChange", "priceTolerance", "reset", "eligibility", "resetType",
        "resetDate", "resetIntervel", "resetFrequency", "attachedDate"};
    public static final String NEW_PS_DETAILS_HEADERS[] = new String[]{
        Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date", "Price Type", "Price Plan No", "Price Plan Name", "Price Protection Status", "Price Protection Start Date", "Price Protection End Date",
        "Price Protection Price Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type", "Max Incremental Change", "Price Tolerance", "Reset", "Eligibility", "Reset Type", "Reset Date", "Reset Interval", "Reset Frequency", "Attached Date"};
    public static final Object AD_ND_PS_COMPONENT_DETAILS_COLUMNS[] = new Object[]{
        Constants.CHECK_RECORD, "itemNo", "itemName", "therapyClass", "brand", "status", "sDate", "eDate",
        "priceType", "ppSDate"};
    public static final Object NEW_RS_DETAILS_COLUMNS[] = new Object[]{
        Constants.CHECK, "ifpNo", Constants.IFPNAME, "brand", "itemStatus", Constants.PS_START_DATE, Constants.PS_END_DATE, "formulaType", "formulaId", "formulaName", "rebatePlanId", "rebatePlanName", "rebateAmount", "bundleNo", "attachedDate"};
    public static final String NEW_RS_DETAILS_HEADERS[] = new String[]{
        Constants.EMPTY, Constants.IFP_NO, Constants.IfpNAME, Constants.BRAND, "Status", "Start Date", "End Date", "Formula Type", "Formula ID", "Formula Name", "Rebate Plan ID", "Rebate Plan Name", "Rebate Amount", "Bundle No", "Attached Date"};
    public static final String AD_ND_PS_COMPONENT_DETAILS_HEADERS[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date",
        "Price Type", "Price Protection Start Date"};
    public static final Object FORMULA_SEARCH_COLUMNS[] = new Object[]{
        "formulaId", "formulaNo", "formulaName"};
    public static final String FORMULA_SEARCH_HEADERS[] = new String[]{
        "Formula Id", "Formula No", "Formula Name"};
    public static final Object REBATE_SEARCH_COLUMNS[] = new Object[]{
        "rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType"};
    public static final String REBATE_SEARCH_HEADERS[] = new String[]{
        "Rebate Plan Id", "Rebate Plan No", "Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type"};
    public static final Object AD_SELECTED_RESULTS_COLUMNS_IFP[] = new Object[]{
        Constants.CHECK_RECORD, "itemNo", "itemName", "therapyClass", "brand", "status", "sDate", "eDate",};
    public static final String AD_SELECTED_RESULTS_HEADERS_IFP[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, "Status", "Start Date", "End Date",};
    public static final Object AD_SEARCH_RESULTS_COLUMNS_IFP[] = new Object[]{
        Constants.CHECK_RECORD, "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE,
        "attachedDate"};
    public static final String AD_SEARCH_RESULTS_HEADERS_IFP[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Attached Date"};
    public static final Object REMOVE_TP_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        "checkRecord", "projectionIdLink", "workflowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", "compStartDate", "compEndDate", "cfpNo",
        Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "pSNo", "pSName", "rSNo", "rSName", "rARCategory"};
    public static final String REMOVE_TP_SELECTION_HEADERS[] = new String[]{
        "", "Projection Id", "Workflow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Company Start Date", "Company End Date", "CFP No",
        "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", "PS Name", "RS No", "RS Name", "RAR Category"};
    public static final Object REMOVE_TP_SUMMARY_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        "checkRecord", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", Constants.CFP_NAME,
        Constants.IFPNAME, "pSName", "rSName", "rARCategory", "statusDescription", "compStartDate", "compEndDate"};
    public static final String REMOVE_TP_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "CFP Name",
        Constants.IfpNAME, "PS Name", "RS Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object EXCEL_REMOVE_TP_SUMMARY_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", Constants.CFP_NAME,
        Constants.IFPNAME, "pSName", "rSName", "rARCategory", "statusDescription", "compStartDate", "compEndDate"};
    public static final String EXCEL_REMOVE_TP_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "CFP Name",
        Constants.IfpNAME, "PS Name", "RS Name", "RAR Category", "Status", "Company Start Date", "Company End Date"};
    public static final Object AD_COMPONENT_DETAILS_COLUMNS_IFP_NEW[] = new Object[]{
        "itemNo", "itemName", "brand", "status", Constants.START_DATE, Constants.END_DATE, "attachedDate"};
    public static final String AD_COMPONENT_DETAILS_HEADERS_IFP_NEW[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, "Status", "Start Date", "End Date",
        "Attached Date"};
    public static final Object TRANSFER_CUSTOMER_COLUMNS[] = new Object[]{
        "checkRecord", "projectionIdLink", "workflowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", "status", "compStartDate", "compEndDate", "cfpNo",
        Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "pSNo", "pSName", "rSNo", "rSName", "rARCategory"};
    public static final String TRANSFER_CUSTOMER_HEADERS[] = new String[]{
        "", "Projection Id", "Workflow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Status", "Company Start Date", "Company End Date", "CFP No",
        "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", "PS Name", "RS No", "RS Name", "RAR Category"};

    /**
     * Enum for Frequency constants
     */
    public enum FrequencyConstants {

        ANNUAL("Annual"),
        ANNUALLY("Annually"),
        YEAR("Year"),
        YEARS("Years"),
        SEMI_ANNUAL("Semi-Annual"),
        SEMI_ANNUALLY("Semi-Annually"),
        QUARTERLY("Quarterly"),
        MONTHLY("Monthly"),
        MONTHS("Months"),
        QUARTERS("Quarters");
        private String frequencyValue;

        private FrequencyConstants(String frequencyValue) {
            this.frequencyValue = frequencyValue;
        }

        public String getConstant() {
            return frequencyValue;
        }

        @Override
        public String toString() {
            return frequencyValue;
        }
    }
    public static final Object[] AD_SEARCH_RESULTS_COLUMNS_CFP = new Object[]{Constants.CHECK_RECORD, "tradingPartnerNo", "tradingPartnerName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, "status", "tradeClass", "attachedDate"};
    public static final String[] AD_SEARCH_RESULTS_HEADERS_CFP = {"", "Trading Partner No", "Trading Partner Name", "TP Contract No", "Start Date", "End Date", "Status", Constants.TRADECLASS, "Attached Date"};
    public static final Object EXCEL_REMOVE_TP_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        ConstantsUtil.PROJECTION_ID, "workflowStatus", Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", "compStartDate", "compEndDate", "cfpNo",
        Constants.CFP_NAME, "ifpNo", Constants.IFPNAME, "pSNo", "pSName", "rSNo"};
    public static final String EXCEL_REMOVE_TP_SELECTION_HEADERS[] = new String[]{
        "Projection Id", "Workflow Status", "Contract Holder", "Contract No", "Contract Name", "Market Type", "Start Date", "End Date", "Company Start Date", "Company End Date", "CFP No",
        "CFP Name", Constants.IFP_NO, Constants.IfpNAME, "PS No", "PS Name", "RS No"};
    public static final Object AD_SELECTED_RESULTS_COLUMNS_CFP[] = new Object[]{
        Constants.CHECK_RECORD, "companyNo", "companyName", "status", "sDate", "eDate"};
    public static final String AD_SELECTED_RESULTS_HEADERS_CFP[] = new String[]{
        "", Constants.COMPANYNO, Constants.COMPANYNAME, "Status", "Start Date", "End Date"};
    public static final Object EXCEL_SUMMARY_CONTRACT_SELECTION_COLUMNS[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractType", "contStartDate", "contEndDate", Constants.CFP_NAME,
        "cfpNo", Constants.IFPNAME, "ifpNo", "pSName", "pSNo", "rSName", "rSNo",
        "rARCategory", "statusDescription", "compStartDate", "compEndDate"};
    public static final String EXCEL_SUMMARY_CONTRACT_SELECTION_HEADERS[] = new String[]{
        "Contract Holder", "Contract No", "Contract Name", "Market Type", "Contract Start Date", "Contract End Date", "CFP Name",
        "CFP No", Constants.IfpNAME, Constants.IFP_NO, "PS Name", "PS No", "RS Name", "RS No",
        "RAR Category", "Status", "Company Start Date", "Company End Date"};
}
