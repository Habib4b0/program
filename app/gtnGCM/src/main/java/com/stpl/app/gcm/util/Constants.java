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
    public static final String COMPANYSTATUS = "Company Status";
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
    public static final String CFP_ID = "CFPId";
    public static final String IFP_ID_COL = "IFPId";
    public static final String DASHBOARD_NUMBER = "dashboardNumber";
    public static final String MARKET_TYPE = "marketType";
    public static final String ERROR = "Error";
    public static final String SAVED_SYSTEM_ID = "savedSystemId";
    public static final String MODEL_ID = "modelId";
    public static final String PROJ_ID_LINK = "projectionIdLink";
    public static final String WORKFLOW_STATUS = "workFlowStatus";
    public static final String CFP_NO = "cfpNO";
    public static final String IFP_NO_COLUMN = "ifpNo";
    public static final String PS_NO_COLUMN = "psNo";
    public static final String RS_NO_COLUMN = "rsNo";
    public static final String START_DATE_HEADER = "Start Date";
    public static final String RAR_CATEGORY_COLUMN = "rarCategory";
    public static final String CHECK_RECORD = "checkRecord";
    public static final String CFP_NAME_HEADER = "CFP Name";
    public static final String CONTRACT_HOLDER_HEADER = "Contract Holder";
    public static final String END_DATE_HEADER = "End Date";
    public static final String RAR_CATEGORY_HEADER = "RAR Category";
    public static final String CFP_NO_HEADER = "CFP No";
    public static final String MARKET_TYPE_HEADER = "Market Type";
    public static final String CONTRACT_NO_HEADER = "Contract No";
    public static final String PROJECTION_ID_HEADER = "Projection ID";
    public static final String PS_NAME_FIELD = "PS Name";
    public static final String RS_NO_HEADER = "RS No";
    public static final String WORK_FLOW_STATUS_HEADER = "WorkFlow Status";
    public static final String FROM = "from";
    public static final String TO = "to";
    
    public static final String IFP_STATUS = "IFP Status";
    public final String[] cfpSearch = {CFP_NO_HEADER, CFP_NAME_HEADER, "CFP ID", "CFP Status", "CFP Type", "Company ID", "Company No", "Company Name", COMPANYSTATUS,
         "Company Type", "Company Category", "Trade Class"};
    public static final String IFP_TYPE_LABEL = "IFP Type";
    public final String[] ifpSearch = {Constants.IFP_NO, Constants.IFP_NAME_LABEL, Constants.IFP_ID, IFP_STATUS, IFP_TYPE_LABEL, "Item ID", "Item No", "Item Name",
         "Item Status", "Item Type", "Brand", "Form", "Strength", "Therapy Class", "Item Start Date", "Item End Date"};
    public static final String PS_NO_LABEL = "PS No";
    public final String[] psSearch = {PS_NO_LABEL, PS_NAME_FIELD, "PS ID", "PS Status", "PS Type"};
    public final String[] rsSearch = {RS_NO_HEADER, "RS Name", "RS ID", "RS Status", "RS Type"};
    public static final String ID = "ID :";
    public static final String NAME = "Name :";
    public static final String NUMBER = "Number :";
    public static final String STATUS = "STATUS";
    public static final String COMP_TRADE_CLASS = "COMP_TRADECLASS";
    public static final String COMPANY_CATEGORY = "COMP_CATEGORY";
    public static final String COMPANY_TYPE = "COMP_TYPE";
    public static final String RS_PROGRAM_TYPE = "RS_PROGRAM_TYPE";
    public static final String ALPHA_NUM_VALIDATION = "([0-9|a-z|A-Z|\\_|\\*|\\s])*";
    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String COMMA = ",";
    public final String[] itemSearch = {Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_STATUS, Constants.ITEM_TYPE, Constants.BRAND, Constants.FORM, Constants.STRENGTH, Constants.THERAPY_CLASS, Constants.ITEM_START_DATE, Constants.ITEM_END_DATE};
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
    public static final String CURRENT_CONTRACT_LABEL = "Current Contract";
    public final String[] companySearch = {Constants.COMPANY_ID, Constants.COMPANYNAME, Constants.COMPANYNO, COMPANYSTATUS, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS};
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
    public static final String SINGLE_QUOTES = "'";
    public static final String ASTRICK = "*";
    public static final String CONTRACT_NAME_HEADER = "Contract Name";
    /**
     * The date format.
     */
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    /**
     * The date format.
     */
    public static final String DATE_FIEILD_CENTER = "dateFieldCenter";
    public static final String DATE_FIEILD_CENTERED = "datefieldcentered";
    public static final String ANNUALLY = "Annually";
    public static final String SEMIANNUALLY = "Semi-Annually";
    public static final String QUARTERLY = "Quarterly";
    public static final String MONTHLY = "Monthly";
    public static final String SPACE = " ";
    public static final String SHOW_ALL = "Show All";
    public static final String BOOTSTRAP = "bootstrap";
    public static final String BOOTSTRAP_FORECAST_BOOTSTRAP_NM = "bootstrap-forecast bootstrap-nm";
    public static final String IFPTYPE = IFP_TYPE_LABEL;

    public static final String COMPANYCATEGORY = "Company Category";
    public static final String COMPANYTYPE = "Company Type";
    public static final String FORM = "Form";
    public static final String CFP_NAME = "cfpName";
    public static final String IFPNAME = "ifpName";
    public static final String PSNAME = "psName";
    public static final String RSNAME = "rsName";
    public static final String RS_START_DATE = "rsStartDate";
    public static final String RS_END_DATE = "rsEndDate";
    public static final String CFP_START_DATE = "cfpStartDate";
    public static final String CFP_END_DATE = "cfpEndDate";
    public static final String PS_START_DATE = "psStartDate";
    public static final String PS_END_DATE = "psEndDate";
    public static final String IFP_START_DATE = "ifpStartDate";
    public static final String IFP_END_DATE = "ifpEndDate";
    public static final String COMPANY_ID = "Company ID";
    public static final String COMPANYNAME = "Company Name";
    public static final String COMPANYNO = "Company No";
    public static final String TRADECLASS = "Trade Class";
    public static final String ITEM_ID = "Item ID";
    public static final String ITEM_NAME = "Item Name";
    public static final String ITEM_NO = "Item No";
    public static final String ITEM_STATUS = "Item Status";
    public static final String ITEM_TYPE = "Item Type";
    public static final String BRAND = "Brand";
    public static final String STRENGTH = "Strength";
    public static final String THERAPY_CLASS = "Therapy Class";
    public static final String ITEM_START_DATE = "Item Start Date";
    public static final String ITEM_END_DATE = "Item End Date";
    public static final String IFP_ID = "IFP ID";
    public static final String IFP_NAME_LABEL = "IFP Name";
    public static final String IFP_NO = "IFP No";
    public static final String TRUE = "true";
    public static final String IS_ORDERED = "isOrdered";
    public static final String CFP_CONTRACT_SID = "cfpContractSid";
    public static final String IFP_CONTRACT_SID = "ifpContractSid";
    public static final String DBDATE_FORMAT = "yyyy-MM-dd";
    public static final String MASS_UPDATE_ERROR = "Mass Update Error";
    public static final String PS_CONT_SID = "psContSid";
    public static final String RS_CONT_SID = "rsContSid";
    public static final String CORRECT_NODE_ALERT = "Please Select Correct Node";
    public static final String STATUS_FIELD = "Status";
    public static final String PS_NO = PS_NO_LABEL;
    public static final String PS_NAME_LABEL = " PS Name";
    public static final String RS_NAME_LABEL = "RS Name";
    public static final String IFP_STATUS_PROPERTY = "ifpStatus";
    public static final String COMP_START_DATE_PROPERTY = "compStartDate";
    public static final String CONT_START_DATE_PROPERTY = "contStartDate";
    public static final String RS_NAME_PROPERTY = "rSName";

    public static final String ITEM_NAME_PROPERTY = "itemName";
    public static final String ATTACHED_DATE_PROPERTY = "attachedDate";
    public static final String ITEM_STATUS_PROPERTY = "itemStatus";
    public static final String ITEM_NO_PROPERTY = "itemNo";
    public static final String BRAND_PROPERTY = "brand";
    public static final String BUNDLE_NO_PROPERTY = "bundleNo";
    public static final String ITEM_END_DATE_PROPERTY = "itemEndDate";
    public static final String COMP_END_DATE_PROPERTY = "compEndDate";
    public static final String CONT_END_DATE_PROPERTY = "contEndDate";
    public static final String PS_NAME_PROPERTY = "pSName";
    public static final String RAR_CATEGORY_PROPERTY = "rARCategory";
    public static final String CONTRACT_TYPE_PROPERTY = "contractType";
    public static final String BRAND_NAME = "brandName";
    public static final String MANUFACTURER = "Manufacturer";
    public static final String BUSINESS_PROCESS_TYPE_NONMANDATED = "Non Mandated";

    public final Object summaryContractSelectionColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, Constants.CFP_NAME,
        Constants.IFPNAME, PS_NAME_PROPERTY, RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY, "tpstatus", COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public final Object summaryTransferTpColumns[] = new Object[]{
        CHECK_RECORD, "contractHolder", "contractNo", "contractName", CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, "cfpName",
        "ifpName", PS_NAME_PROPERTY, RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY, "statusString", COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public static final String COMPANY_START_DATE_LABEL = "Company Start Date";
    public static final String COMPANY_END_DATE_LABEL = "Company End Date";
    public static final String CONTRACT_END_DATE_LABEL = "Contract End Date";
    public static final String CONTRACT_START_DATE_LABEL = "Contract Start Date";
    public final String summaryContractSelectionHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, CONTRACT_START_DATE_LABEL, CONTRACT_END_DATE_LABEL, CFP_NAME_HEADER,
        Constants.IFP_NAME_LABEL, PS_NAME_FIELD, RS_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};

    public static final String CFP_NO_PROPERTY = "cfpNo";
    public static final String WORKFLOW_STATUS_PROPERTY = "workflowStatus";
    public final Object contractSelectionColumns[] = new Object[]{
        CHECK_RECORD, PROJ_ID_LINK, WORKFLOW_STATUS_PROPERTY, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, STATUS_S, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY, Constants.CFP_NAME, CFP_NO_PROPERTY, Constants.IFPNAME, IFP_NO_COLUMN, PS_NAME_PROPERTY, "pSNo", RS_NAME_PROPERTY, "rSNo", RAR_CATEGORY_PROPERTY};
    public static final String WORKFLOW_STATUS_LABEL = "Workflow Status";
    public final String contractSelectionHeaders[] = new String[]{
        "", "Projection ID", WORKFLOW_STATUS_LABEL, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL, CFP_NAME_HEADER,
        CFP_NO_HEADER, Constants.IFP_NAME_LABEL, Constants.IFP_NO, PS_NAME_FIELD, PS_NO_LABEL, RS_NAME_LABEL, RS_NO_HEADER,
        RAR_CATEGORY_HEADER,};
    public final Object addTpContractSelectionColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, Constants.CFP_NAME, CFP_NO_PROPERTY, Constants.IFPNAME, IFP_NO_COLUMN, PS_NAME_PROPERTY, "pSNo", RS_NAME_PROPERTY, "rSNo", RAR_CATEGORY_PROPERTY, STATUS_S, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public final String addTpContractSelectionHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, CONTRACT_START_DATE_LABEL, CONTRACT_END_DATE_LABEL, CFP_NAME_HEADER,
        CFP_NO_HEADER, Constants.IFP_NAME_LABEL, Constants.IFP_NO, PS_NAME_FIELD, PS_NO_LABEL, RS_NAME_LABEL, RS_NO_HEADER,
        RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public static final String STATE_PROPERTY = "state";
    public static final String ADDRESS2_PROPERTY = "address2";
    public static final String ADDRESS1_PROPERTY = "address1";
    public static final String COMPANY_TYPE_PROPERTY = "companyType";
    public static final String COMPANY_CATEGORY_PROPERTY = "companyCategory";
    public static final String COMPANY_ID_PROPERTY = "companyId";
    public static final String TRADE_CLASS_PROPERTY = "tradeClass";
    public static final String COMPANY_SYSTEM_ID_PROPERTY = "companySystemId";
    public final Object promoteTpResultsColumns[] = new Object[]{
        COMPANY_SYSTEM_ID_PROPERTY, COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, TRADE_CLASS_PROPERTY, ADDRESS1_PROPERTY, ADDRESS2_PROPERTY, "city", STATE_PROPERTY, "zip"};
    public static final String SYSTEM_ID_LABEL = "System ID";
    public static final String ADDRESS_1_LABEL = "Address 1";
    public static final String ADDRESS_2_LABEL = "Address 2";
    public static final String STATE_LABEL = "State";
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public final String promoteTpResultsHeaders[] = new String[]{
        SYSTEM_ID_LABEL, Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS, ADDRESS_1_LABEL, ADDRESS_2_LABEL, "City", STATE_LABEL, "Zip"};
    public static final String PARENT_NAME_PROPERTY = "parentName";
    public static final String PARENT_NO_PROPERTY = "parentNo";
    public final Object tpCompanySearchColumns[] = new Object[]{
        COMPANY_SYSTEM_ID_PROPERTY, COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, TRADE_CLASS_PROPERTY, ADDRESS1_PROPERTY, ADDRESS2_PROPERTY, "city", STATE_PROPERTY, "zip", PARENT_NO_PROPERTY, PARENT_NAME_PROPERTY};
    public static final String PARENT_NAME_LABEL = "Parent Name";
    public static final String PARENT_NO_LABEL = "Parent No";
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public final String tpCompanySearchHeaders[] = new String[]{
        SYSTEM_ID_LABEL, Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS, ADDRESS_1_LABEL, ADDRESS_2_LABEL, "City", STATE_LABEL, "Zip", PARENT_NO_LABEL, PARENT_NAME_LABEL};
    public final Object discountSearchColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME};
    public final String discountSearchHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER,
        END_DATE_HEADER, COMPANY_FAMILY_PLAN, ITEM_FAMILY_PLAN, Constants.PRICE_SCHEDULE, REBATE_SCHEDULE};
    public static final String BASIS_PROPERTY = "basis";
    public static final String RAR_TYPE_PROPERTY = "rarType";
    public static final String CONTRACT_ID_PROPERTY = "contractId";
    public final Object componentResultsColumns[] = new Object[]{
        CONTRACT_ID_PROPERTY, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, "contractStatus", Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, "frequency", RAR_TYPE_PROPERTY, BASIS_PROPERTY};
    public static final String NUMBER_LABEL = "Number";
    public static final String BASIS_LABEL = "Basis";
    public static final String RAR_TYPE_LABEL = "RAR Type";
    public static final String FREQUENCY_LABEL = "Frequency";

    public final String componentResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, "Name", STATUS_FIELD, START_DATE_HEADER,
        END_DATE_HEADER, "CFP", "IFP", "PS", FREQUENCY_LABEL, RAR_TYPE_LABEL, BASIS_LABEL};

    public static final String NUMBER_PROPERTY = "number";
    public final Object treeColumns[] = new Object[]{
        CATEGORY, "id", NUMBER_PROPERTY, "name"};
    public final String treeHeaders[] = new String[]{
        "Component", "ID", NUMBER_LABEL, "Name"};

    public static final String ITEM_START_DATE_PROPERTY = "itemStartDate";
    public static final String FORMULA_TYPE_PROPERTY = "formulaType";
    public static final String REBATE_AMOUNT_PROPERTY = "rebateAmount";
    public static final String REBATE_PLAN_ID_PROPERTY = "rebatePlanId";
    public static final String THERAPY_CLASS_PROPERTY = "therapyClass";
    public static final String FORMULA_NAME_PROPERTY = "formulaName";
    public static final String REBATE_PLAN_PROPERTY = "rebatePlan";
    public static final String REBATE_PLAN_NAME_PROPERTY = "rebatePlanName";
    public static final String FORMULA_ID_PROPERTY = "formulaId";

    public final Object selectedResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY, FORMULA_NAME_PROPERTY, FORMULA_TYPE_PROPERTY, REBATE_PLAN_ID_PROPERTY, REBATE_PLAN_NAME_PROPERTY, REBATE_AMOUNT_PROPERTY, BUNDLE_NO_PROPERTY, ATTACHED_DATE_PROPERTY};
    public static final String ATTACHED_DATE_FIELD = "Attached Date";
    public static final String FORMULA_TYPE_LABEL = "Formula Type";
    public static final String FORMULA_NAME_LABEL = "Formula Name";
    public static final String REBATE_PLAN_ID_LABEL = "Rebate Plan ID";
    public static final String FORMULA_ID_LABEL = "Formula ID";
    public static final String REBATE_PLAN_LABEL = "Rebate Plan";
    public static final String REBATE_PLAN_NAME_LABEL = "Rebate Plan Name";
    public static final String BUNDLE_NO_LABEL = "Bundle No";
    public final String selectedResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL, FORMULA_NAME_LABEL, FORMULA_TYPE_LABEL, REBATE_PLAN_ID_LABEL, REBATE_PLAN_NAME_LABEL, "Rebate amount", BUNDLE_NO_LABEL, ATTACHED_DATE_FIELD};
    public final Object componentDetailsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    /**
     * The Constant COMPONENT_DETAILS_HEADERS.
     */
    public final String componentDetailsHeaders[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object tpComponentDetailsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String tpComponentDetailsHeaders[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    /**
     * The Constant CONTRACT_COMP_RESULTS_COLUMNS.
     */
    public final Object contractCompResultsColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME,
        Constants.PSNAME, Constants.RSNAME};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public final String contractCompResultsHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, "IFP",
        "PS", "RS"};
    public final Object contractDashboardResultsColumns[] = new Object[]{
        CATEGORY, "id", NUMBER_PROPERTY, "name"};
    public static final String CATEGORY_LABEL = "Category";
    /**
     * The Constant CONTRACT_DASHBOARD_RESULTS_HEADERS.
     */
    public final String contractDashboardResultsHeaders[] = new String[]{
        CATEGORY_LABEL, "ID", NUMBER_LABEL, "Name"};
    public static final String ITEM_ID_PROPERTY = "itemId";
    public static final String STRENGTH_PROPERTY = "strength";
    public final Object compItemResultsColumns[] = new Object[]{
        CHECK_RECORD, ITEM_ID_PROPERTY, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, STATUS_S,
        Constants.START_DATE, Constants.END_DATE};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public final String compItemResultsHeaders[] = new String[]{
        "", "ID", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object addTradingPartnerColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "cfp",
        "ifp", "ps", "rs"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public final String addTradingPartnerHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER,
        "CFP", "IFP", "PS", "RS"};
    public final Object contractDashboardColumns[] = new Object[]{
        "component", "id", NUMBER_PROPERTY, "name"};
    /**
     * The Constant PROMOTE_TP_RESULTS_HEADERS.
     */
    public final String contractDashboardHeaders[] = new String[]{
        "Component", "ID", NUMBER_LABEL, "Name"};
    public final Object itemSearchResultsColumns[] = new Object[]{
        CHECK_RECORD, ITEM_ID_PROPERTY, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String itemSearchResultsHeaders[] = new String[]{
        "", "Id", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM,
        Constants.STRENGTH, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object contractComponentDetailsResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String contractComponentDetailsResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object rsResultsColumns[] = new Object[]{
        "id", NUMBER_PROPERTY, "name", STATUS_S, Constants.START_DATE,
        Constants.END_DATE, Constants.IFPNAME, "frequency", RAR_TYPE_PROPERTY, BASIS_PROPERTY};
    public final String rsResultsHeaders[] = new String[]{
        "Id", NUMBER_LABEL, "Name", STATUS_FIELD,
        START_DATE_HEADER, END_DATE_HEADER, "IFP", FREQUENCY_LABEL, RAR_TYPE_LABEL, BASIS_LABEL};
    public final Object summaryTpResultsColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.CFP_NAME,
        Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME, RAR_CATEGORY_COLUMN, STATUS_S, COMPANY_START_DATE, COMPANY_END_DATE};
    /**
     * The Constant SUMMARY_TP_RESULTS_HEADERS.
     */
    public final String summaryTpResultsHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, CFP_NAME_HEADER,
        Constants.IFP_NAME_LABEL, PS_NAME_FIELD, RS_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public static final String REBATE_SCHEDULE_NAME_PROPERTY = "rebateScheduleName";
    public static final String REBATE_SCHEDULE_NO_PROPERTY = "rebateScheduleNo";
    /**
     * CURRENT_TRADING_PARTNER_COLUMNS
     */
    public final Object currentTradingPartnerColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, REBATE_SCHEDULE_NO_PROPERTY, REBATE_SCHEDULE_NAME_PROPERTY, RAR_CATEGORY_COLUMN, STATUS_S, COMPANY_START_DATE, COMPANY_END_DATE};
    public static final String REBATE_SCHEDULE_NAME_LABEL = "Rebate Schedule Name";
    public static final String REBATE_SCHEDULE_NO_LABEL = "Rebate Schedule No";
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public final String currentTradingPartnerHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, REBATE_SCHEDULE_NO_LABEL, REBATE_SCHEDULE_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public static final String STATUS_ID_PROPERTY = "statusId";
    public final Object[] ptpComponentInfoColumnsIfp = new Object[]{CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_ID_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String ptpComponentInfoHeadersIfp[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object[] ptpComponentInfoColumnsPs = new Object[]{CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_ID_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String ptpComponentInfoHeadersPs[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object[] ptpComponentInfoColumns = new Object[]{CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_ID_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY};
    public final String ptpComponentInfoHeaders[] = new String[]{" ", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL};
    public final Object tpResultsColumns[] = new Object[]{
        COMPANY_SYSTEM_ID_PROPERTY, COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, TRADE_CLASS_PROPERTY, ADDRESS1_PROPERTY, ADDRESS2_PROPERTY, "city", STATE_PROPERTY, "zip"
    };
    public final String tpResultsHeaders[] = new String[]{
        SYSTEM_ID_LABEL, Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS, ADDRESS_1_LABEL, ADDRESS_2_LABEL, "City", STATE_LABEL, "Zip"
    };
    public final Object tpComponentInformationColumnsRs[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, FORMULA_TYPE_PROPERTY, FORMULA_ID_PROPERTY, FORMULA_NAME_PROPERTY, REBATE_PLAN_ID_PROPERTY, REBATE_PLAN_NAME_PROPERTY, REBATE_AMOUNT_PROPERTY, BUNDLE_NO_PROPERTY, ATTACHED_DATE_PROPERTY};
    public static final String REBATE_AMOUNT_LABEL = "Rebate Amount";
    public static final String REBATE_PLAN_ID_FIELD = "Rebate Plan Id";
    public static final String FORMULA_ID_FIELD = "Formula Id";
    public final String tpComponentInformationHeadersRs[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, FORMULA_TYPE_LABEL, FORMULA_ID_FIELD, FORMULA_NAME_LABEL, REBATE_PLAN_ID_FIELD, REBATE_PLAN_NAME_LABEL, REBATE_AMOUNT_LABEL, BUNDLE_NO_LABEL, ATTACHED_DATE_FIELD};
    public static final String RESET_ELIGIBLE_PROPERTY = "resetEligible";
    public static final String MAX_INCREMENTAL_CHANGE_PROPERTY = "maxIncrementalChange";
    public static final String PRICE_TOLERANCE_FREQUENCY_PROPERTY = "priceToleranceFrequency";
    public static final String PRICE_TOLERANCE_PROPERTY = "priceTolerance";
    public static final String PRICE_TYPE_PROPERTY = "priceType";
    public static final String PRICE_PLAN_NO_PROPERTY = "pricePlanNo";
    public static final String PRICE_TOLERANCE_TYPE_PROPERTY = "priceToleranceType";
    public static final String PRICE_PLAN_NAME_PROPERTY = "pricePlanName";
    public static final String PRICE_PROTECTION_PRICE_TYPE_PROPERTY = "priceProtectionPriceType";
    public static final String PRICE_PROTECTION_END_DATE_PROPERTY = "priceProtectionEndDate";
    public static final String PRICE_PROTECTION_START_DATE_PROPERTY = "priceProtectionStartDate";
    public static final String PRICE_TOLERANCE_INTERVAL = "priceToleranceInterval";
    public static final String RESET_FREQUENCY_PROPERTY = "resetFrequency";
    public static final String PRICE_PROTECTION_STATUS_PROPERTY = "priceProtectionStatus";
    public static final String RESET_TYPE_PROPERTY = "resetType";
    public static final String RESET_DATE_PROPERTY = "resetDate";
    public static final String RESET_INTERVAL_PROPERTY = "resetInterval";
    public static final String BASE_PRICE_PROPERTY = "basePriceType";
    public static final String NEP_PROPERTY = "nep";
    public static final String NEP_FORMULA_PROPERTY = "nepFormula";
    public static final String BASELINE_WAC_PROPERTY = "baselineWAC";
    public static final String BASELINE_NET_WAC_PROPERTY = "baselineNetWAC";
    public static final String NET_PRICE_TYPE_PROPERTY = "netPriceType";
    public static final String NET_PRICE_TYPE_FORMULA_PROPERTY = "netPriceTypeFormula";
    public static final String RESET_PRICE_TYPE_PROPERTY = "resetPriceType";
    public static final String NET_RESET_PRICE_TYPE_PROPERTY = "netResetPriceType";
    public static final String SUBSEQUENT_PERIOD_PRICE_TYPE_PROPERTY = "subsequentPeriodPriceType";
    public static final String MEASUREMENT_PRICE_PROPERTY = "measurementPrice";
    public static final String NET_SUBSEQUENT_PERIOD_PRICE_PROPERTY = "netSubsequentPeriodPrice";
    public static final String NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_PROPERTY = "netSubsequentPeriodPriceFormula";
    public static final String NET_BASELINE_WAC_FORMULA_PROPERTY = "netBaselineWACFormula";
    public static final String NET_RESET_PRICE_FORMULA_PROPERTY = "netResetPriceFormula";

    public final Object tpComponentInformationColumnsPs[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S,
        Constants.START_DATE, Constants.END_DATE, PRICE_TYPE_PROPERTY, PRICE_PLAN_NO_PROPERTY,
        PRICE_PLAN_NAME_PROPERTY, PRICE_PROTECTION_STATUS_PROPERTY, PRICE_PROTECTION_START_DATE_PROPERTY,
        PRICE_PROTECTION_END_DATE_PROPERTY, PRICE_PROTECTION_PRICE_TYPE_PROPERTY, PRICE_TOLERANCE_INTERVAL,
        PRICE_TOLERANCE_FREQUENCY_PROPERTY, PRICE_TOLERANCE_TYPE_PROPERTY, MAX_INCREMENTAL_CHANGE_PROPERTY,
        PRICE_TOLERANCE_PROPERTY, RESET_ELIGIBLE_PROPERTY, RESET_TYPE_PROPERTY, RESET_DATE_PROPERTY, RESET_INTERVAL_PROPERTY,
        RESET_FREQUENCY_PROPERTY, ATTACHED_DATE_PROPERTY};
    public static final String RESET_INTERVAL_LABEL = "Reset Interval";
    public static final String PRICE_TYPE_LABEL = "Price Type";
    public static final String RESET_FREQUENCY_LABEL = "Reset Frequency";
    public static final String PRICE_PLAN_NO_LABEL = "Price Plan No";
    public static final String PRICE_PLAN_NAME_LABEL = "Price Plan Name";
    public static final String PRICE_TOLERANCE_FREQUENCY_LABEL = "Price Tolerance Frequency";
    public static final String RESET_DATE_LABEL = "Reset Date";
    public static final String PRICE_PROTECTION_STATUS_LABEL = "Price Protection Status";
    public static final String PRICE_TOLERANCE_INTERVAL_LABEL = "Price Tolerance Interval";
    public static final String PRICE_PROTECTION_END_DATE_LABEL = "Price Protection End Date";
    public static final String RESET_TYPE_LABEL = "Reset Type";
    public static final String PRICE_TOLERANCE_LABEL = "Price Tolerance";
    public static final String MAX_INCREMENTAL_CHANGE_LABEL = "Max Incremental Change";
    public static final String PRICE_TOLERANCE_TYPE_LABEL = "Price Tolerance Type";
    public static final String PRICE_PROTECTION_START_DATE_LABEL = "Price Protection Start Date";
    public static final String PRICE_PROTECTION_PRICE_TYPE_LABEL = "Price Protection Price Type";
    public final String tpComponentInformationHeadersPs[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PLAN_NO_LABEL, PRICE_PLAN_NAME_LABEL, PRICE_PROTECTION_STATUS_LABEL, PRICE_PROTECTION_START_DATE_LABEL, PRICE_PROTECTION_END_DATE_LABEL,
        PRICE_PROTECTION_PRICE_TYPE_LABEL, PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_TYPE_LABEL, MAX_INCREMENTAL_CHANGE_LABEL, PRICE_TOLERANCE_LABEL,
        RESET_ELIGIBLE_LABLE_NAME, RESET_TYPE_LABEL, RESET_DATE_LABEL, RESET_INTERVAL_LABEL, RESET_FREQUENCY_LABEL, ATTACHED_DATE_FIELD};
    public final Object tpComponentInformationColumnsIfp[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, ATTACHED_DATE_PROPERTY};
    public final String tpComponentInformationHeadersIfp[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, ATTACHED_DATE_FIELD};
    public final Object tpComponentInformationColumnsCfp[] = new Object[]{
        COMPANY_NO, COMPANY_NAME, COMPANY_STATUS, Constants.START_DATE, Constants.END_DATE, STATUS_S, TRADE_CLASS_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String tpComponentInformationHeadersCfp[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, COMPANYSTATUS, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD,
        Constants.TRADECLASS, ATTACHED_DATE_FIELD};
    public final Object existingSelectedResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY};
    public final String existingSelectedResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL};
    public final Object parentCompanySearchColumns[] = new Object[]{
        COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_STATUS, COMPANY_TYPE_PROPERTY};
    public final String parentCompanySearchHeaders[] = new String[]{
        Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, COMPANYSTATUS, Constants.COMPANYTYPE};
    public final Object promoteTpContractDashboardTreeColumns[] = new Object[]{
        CATEGORY, CONTRACT_ID_PROPERTY, Constants.CONTRACT_NO, Constants.CONTRACT_NAME};
    public final String promoteTpContractDashboardTreeHeaders[] = new String[]{
        CATEGORY_LABEL, "ID", NUMBER_LABEL, "Name"};
    public final Object cfpSearchColumns[] = new Object[]{
        "cfpId", CFP_NO_PROPERTY, Constants.CFP_NAME, "cfpType", "cfpCategory", "cfpDesignation",
        "cfpPlanId", "cfpPlanName", "cfpStatus", "cfpTradeClass", Constants.CFP_START_DATE, Constants.CFP_END_DATE};
    public final String cfpSearchHeaders[] = new String[]{
        "CFP ID", CFP_NO_HEADER, CFP_NAME_HEADER, "CFP Type", "CFP Category", "CFP Designation",
        "CFP Plan ID", "CFP Plan Name", "CFP Status", "CFP Trade Class", "CFP Start Date", "CFP End Date"};
    public final Object ifpSearchColumns[] = new Object[]{
        IFP_NO_COLUMN, Constants.IFPNAME, "ifpType", "ifpCategory", "ifpDesignation",
        "ifpPlanId", "ifpPlanName", IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public final String ifpSearchHeaders[] = new String[]{
        Constants.IFP_NO, Constants.IFP_NAME_LABEL, IFP_TYPE_LABEL, "IFP Category", "IFP Designation",
        "IFP Plan ID", "IFP Plan Name", IFP_STATUS, "IFP Start Date", "IFP End Date"};
    public final Object psSearchColumns[] = new Object[]{
        "psNo", Constants.PSNAME, "psType", "psCategory", "psTradeClass", "psDesignation", "parentPsId",
        "parentPsName", "psStatus", Constants.PS_START_DATE, Constants.PS_END_DATE};
    public final String psSearchHeaders[] = new String[]{
        PS_NO_LABEL, PS_NAME_FIELD, "PS Type", "PS Category", "PS Trade Class", "PS Designation",
        "Parent PS ID", "Parent PS Name", "PS Status", "PS Start Date", "PS End Date"};
    public final Object rsSearchColumns[] = new Object[]{
        "rsId", "rsNo", Constants.RSNAME, "rsType", "rebateProgramType", "rsCategory", "rsTradeClass",
        "rsDesignation", "parentrsId", "parentrsName", "rsStatus", Constants.RS_START_DATE, Constants.RS_END_DATE};
    public final String rsSearchHeaders[] = new String[]{
        "RS ID", RS_NO_HEADER, RS_NAME_LABEL, "RS Type", "Rebate Program Type", "RS Category", Constants.TRADECLASS,
        "RS Designation", "Parent RS ID", "Parent RS Name", "RS Status", "RS Start Date", "RS End Date"};
    public final Object chSearchColumns[] = new Object[]{
        "chId", "chNo", "chName", "chStatus", "chType"};
    public final String chSearchHeaders[] = new String[]{
        "Contract Holder ID", "Contract Holder No", "Contract Holder Name", "Contract Holder Status", "Contract Holder Type"};
    public final Object custSearchColumns[] = new Object[]{
        "custId", "custNo", "custName", "custStatus", "custType"};
    public final String custSearchHeaders[] = new String[]{
        "Customer ID", "Customer No", "Customer Name", "Customer Status", "Customer Type"};
    public final Object itemSearchColumns[] = new Object[]{
        ITEM_ID_PROPERTY, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, ITEM_STATUS_PROPERTY, "itemType"};
    public final String itemSearchHeaders[] = new String[]{
        Constants.ITEM_ID, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.ITEM_STATUS, Constants.ITEM_TYPE};
    public final Object promoteTpContractDashboardTreeColumnsTransfer[] = new Object[]{
        CATEGORY, DASHBOARD_ID, DASHBOARD_NUMBER, DASHBOARD_NAME};
    public static final HelperDTO HELPER_DTO = new HelperDTO(0, Constants.IndicatorConstants.SELECT_ONE.getConstant());
    public final Object excelContractSelectionColumns[] = new Object[]{
        ConstantsUtil.PROJECTION_ID, WORKFLOW_STATUS_PROPERTY, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, STATUS_S , COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY, Constants.CFP_NAME, CFP_NO_PROPERTY, 
        Constants.IFPNAME, IFP_NO_COLUMN,  PS_NAME_PROPERTY, "pSNo", RS_NAME_PROPERTY, "rSNo", RAR_CATEGORY_PROPERTY};
    public static final String PROJECTION_ID_LABEL = "Projection Id";
    public final String excelContractSelectionHeaders[] = new String[]{
        PROJECTION_ID_LABEL, WORKFLOW_STATUS_LABEL, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL, CFP_NAME_HEADER, CFP_NO_HEADER, 
        Constants.IFP_NAME_LABEL, Constants.IFP_NO, PS_NAME_FIELD, PS_NO_LABEL,  RS_NAME_LABEL, RS_NO_HEADER,
        RAR_CATEGORY_HEADER};
    public final Object companySearchColumns[] = new Object[]{
        CHECK, COMPANY_SYSTEM_ID_PROPERTY, COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, TRADE_CLASS_PROPERTY, ADDRESS1_PROPERTY, ADDRESS2_PROPERTY, "city", STATE_PROPERTY, "zip", PARENT_NO_PROPERTY, PARENT_NAME_PROPERTY};
    public final String companySearchHeaders[] = new String[]{
        "", SYSTEM_ID_LABEL, Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS, ADDRESS_1_LABEL, ADDRESS_2_LABEL, "City", STATE_LABEL, "Zip", PARENT_NO_LABEL, PARENT_NAME_LABEL};
    public final Object companySearchColumnsWithoutCheck[] = new Object[]{
        COMPANY_ID_PROPERTY, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, TRADE_CLASS_PROPERTY, ADDRESS1_PROPERTY, ADDRESS2_PROPERTY, "city", STATE_PROPERTY, "zip", PARENT_NO_PROPERTY, PARENT_NAME_PROPERTY};
    public final String companySearchHeadersWithoutCheck[] = new String[]{
        Constants.COMPANY_ID, Constants.COMPANYNO, Constants.COMPANYNAME, Constants.COMPANYTYPE, Constants.COMPANYCATEGORY, Constants.TRADECLASS, ADDRESS_1_LABEL, ADDRESS_2_LABEL, "City", STATE_LABEL, "Zip", PARENT_NO_LABEL, PARENT_NAME_LABEL};
    public final Object linkedCompanyColumns[] = new Object[]{
        CHECK, "fromCompanyId", "fromCompanyNo", "fromCompanyName", "toCompanyId", "toCompanyNo", "toCompanyName"};
    public final String linkedCompanyHeaders[] = new String[]{
        "", "Company ID - From", "Company No - From", "Company Name - From", "Company ID - To", "Company No - To", "Company Name - To",};
    public final Object ptpComponentDetailsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_ID_PROPERTY, Constants.START_DATE, Constants.END_DATE};
    /**
     * The Constant COMPONENT_DETAILS_HEADERS.
     */
    public final String ptpComponentDetailsHeaders[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object exlCurrentTradingPartnerColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, REBATE_SCHEDULE_NO_PROPERTY, REBATE_SCHEDULE_NAME_PROPERTY, RAR_CATEGORY_COLUMN, STATUS_S, COMPANY_START_DATE, COMPANY_END_DATE};
    /**
     * The Constant CURRENT_TRADING_PARTNER_HEADERS.
     */
    public final String exlCurrentTradingPartnerHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, REBATE_SCHEDULE_NO_LABEL, REBATE_SCHEDULE_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public final Object exlContractCompResultsColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME,
        Constants.PSNAME, Constants.RSNAME};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public final String exlContractCompResultsHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, "IFP",
        "PS", "RS"};
    public final Object exlCompItemResultsColumns[] = new Object[]{
        ITEM_ID_PROPERTY, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, STATUS_S, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    /**
     * The Constant CONTRACT_COMP_RESULTS_HEADERS.
     */
    public final String exlCompItemResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object exlPtpComponentInfoColumns[] = new Object[]{ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S, "itemStDate", "itemEdDate", REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY};
    public final String exlPtpComponentInfoHeaders[] = new String[]{Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL};
    private static String ADDBY = "addBy";
    public static final String LEVEL_VALUE_PROPERTY = "levelValue";
    public static final String AMOUNT_LABEL = "Amount";
    public static final String SALES_LABEL = "Sales";
    public static final String GROUP_PROPERTY = "group";
    public static final String UNITS_LABEL = "Units";
    public static final String ITEM_CATEGORY_LABEL = "Item Category";
    public static final String PLACE_HOLDER_LABEL = "Place Holder";
    public static final String THERAPEUTIC_CLASS_LABEL = "Therapeutic Class";
    public static final String ITEM_DESC_LABEL = "Item Desc";
    public static final String COMPANY_LABEL = "Company";
    public static final String ITEM_TYPE_PROPERTY = "itemType";
    public static final String COMPANY_PROPERTY = "company";
    public static final String ITEM_CATEGORY_PROPERTY = "itemCategory";
    public static final String ITEM_DESC_PROPERTY = "itemDesc";
    public static final String SYSTEM_ID_PROPERTY = "systemId";
    public static final String PLACE_HOLDER_PROPERTY = "placeHolder";
    public static final String EXCEL_BTN_INFO = "excelBtnInfo";
    public static final String SEARCH_BTN = "searchBtn";
    public static final String GCM_CUSTOMER_MANAGEMENT = "GCM-Customer Management";
    public static final String POPULATE_BTN = "populateBtn";
    public static final String SUBMIT_BTN = "submitBtn";
    public static final String EXCEL_BTN = "excelBtn";
    public static final String TP_RESET_BTN = "tpResetBtn";
    public static final String RESET_BTN = "resetBtn";
    public static final String NO_VALUE = "No Value";
    public static final String NO_VALUE_ENTERED_FOR_MASS_POPULATE = "No value entered for mass populate.";
    public static final String DUPLICATE_CRITERIA = "Duplicate Criteria";
    public static final String PLEASE_ENSURE_ALL_MANDATORY_FIELDS = "Please ensure all mandatory fields are completed for the Component and Component Details.";
    public static final String PP_S_DATE = "ppSDate";
    public static final String SELECT_THE_SAME_ITEMS = "Please Select the same items that are in IFP";
    public static final String ADD_TO_TREE = "Add to Tree";
    public static final String E_DATE = "eDate";
    public static final String ALREADY_ADDED = " Already Added";
    public static final String CRITERIA_MISMATCH = "Criteria Mismatch";
    public static final String ITEM_MASTER_SID_PROPERTY = "itemMasterSid";
    public static final String REMOVE_HEADER = "Remove";
    public static final String TABLE_HEADER_NORMAL = "table-header-normal";
    public static final String TRANSFER_CONFIRMATION = "Transfer confirmation";
    public static final String DATE_FIELD_CENTERED = "datefieldcentered";
    public static final String DATE_FIELD_CENTER = "dateFieldCenter";
    public static final String UPDATE_ERROR = "Update Error";
    public static final String SUBMIT_ERROR = "Submit error";

    public static final String ITEM_MASTER_SID = "ITEM_MASTER_SID";
    public static final String COMPANY_MASTER_SID = "COMPANY_MASTER_SID";
    public static final String NON_MANDATED = "Non Mandated";
    public static final String PRC_M_DISCOUNT_INSERT = "PRC_M_DISCOUNT_INSERT";
    public static final String PRC_M_SUPP_INSERT = "PRC_M_SUPP_INSERT";
    public static final String MANDATED = "Mandated";
    public static final String PRC_M_SUPP_PROJECTION = "PRC_M_SUPP_PROJECTION";
    public static final String PROJECTION_SALES = "PROJECTION_SALES";
    public static final String PRC_MANDATED_SALES_INSERT = "Prc_mandated_sales_insert";
    public static final String JAVA_JBOSS_DATA_SOURCES_JDBCAPP_DATA_POOL = "java:jboss/datasources/jdbc/appDataPool";
    public static final String USER_DEFINED = "User Defined";
    public static final String FIELD = "?FIELD";
    public static final String ORDER_BYRS_TRADE_CLASS = "orderBy~rsTradeClass";
    public static final String FILTERRS_START_DATEFROM = "filter~rsStartDatefrom";
    public static final String FILTERRS_TYPE = "filter~rsType";
    public static final String ORDER_BYRS_DESIGNATION = "orderBy~rsDesignation";
    public static final String FILTERRS_ID = "filter~rsId";
    public static final String ORDER_BYRS_ID = "orderBy~rsId";
    public static final String ORDER_BYRS_TYPE = "orderBy~rsType";
    public static final String ORDER_BYRS_CATEGORY = "orderBy~rsCategory";
    public static final String ORDER_BYRS_STATUS = "orderBy~rsStatus";
    public static final String ORDER_BYREBATE_PROGRAM_TYPE = "orderBy~rebateProgramType";
    public static final String FILTERRS_STATUS = "filter~rsStatus";
    public static final String FILTERREBATE_PROGRAM_TYPE = "filter~rebateProgramType";
    public static final String FILTERRS_CATEGORY = "filter~rsCategory";
    public static final String ORDER_BYRS_NO = "orderBy~rsNo";
    public static final String ORDER_BYRS_END_DATE = "orderBy~rsEndDate";
    public static final String FILTERRS_TRADE_CLASS = "filter~rsTradeClass";
    public static final String ORDER_BYRS_START_DATE = "orderBy~rsStartDate";
    public static final String FILTERRS_START_DATETO = "filter~rsStartDateto";
    public static final String FILTERRS_END_DATEFROM = "filter~rsEndDatefrom";
    public static final String FILTERRS_DESIGNATION = "filter~rsDesignation";
    public static final String FILTERRS_END_DATETO = "filter~rsEndDateto";
    public static final String FILTERRS_NO = "filter~rsNo";
    public static final String FILTERRS_NAME = "filter~rsName";
    public static final String FILTERPS_END_DATE = "filter~psEndDate";
    public static final String FILTERCFP_START_DATE = "filter~cfpStartDate";
    public static final String FILTERPS_START_DATE = "filter~psStartDate";
    public static final String WHERE_USER_SID = "'  WHERE USERS_SID=  '";
    public static final String AND_SESSION_ID = "'AND SESSION_ID like '";
    public static final String AND_OPERATION = "'AND OPERATION like '";
    public static final String AND_CHECK_RECORD = "AND CHECK_RECORD = 1";
    
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
        TAB_CONTRACT_SELECTION(CURRENT_CONTRACT_LABEL),
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
        TAB_CURRENT_CONTRACT(CURRENT_CONTRACT_LABEL),
        EXCEL_IMAGE_PATH("../../icons/excel.png"),
        CURRENT_CONTRACT(CURRENT_CONTRACT_LABEL),
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
        DATE_FORMAT(MM_DD_YYYY),
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
        PROMOTE_TP_REBATE_SCHEDULE_NAME(REBATE_SCHEDULE_NAME_LABEL),
        PROMOTE_TP_REBATE_FREQUENCY(QUARTERLY),
        PROMOTE_TP_SEARCH_FIELD("Brand Name"),
        TAB_PROMOTE_TP_EXISTING_COMPONENTS("Existing Components"),
        BRAND("Brand Name"),
        STATUS(CFP_STATUS),
        RS_TYPE(REBATE_SCHEDULE_TYPE),
        CFP_TYPE("CFP_TYPE"),
        IFP_TYPE("IFP_TYPE"),
        TP_COMPANY_END_DATE(COMPANY_END_DATE_LABEL),
        INDICATOR_CONTRACT_HOLDER("Contract Holder"),
        PS_TYPE("PS_TYPE"),
        RSCONTRACTSID("rsContractSid"),
        RS_IFP_FOR_ADD_DISCOUNT("RS_IFP_FOR_ADD_DISCOUNT"),
        PS_IFP_FOR_ADD_DISCOUNT("PS_IFP_FOR_ADD_DISCOUNT"),
        SELECTED_RS_IFP_FOR_ADD_DISCOUNT("SELECTED_RS_IFP_FOR_ADD_DISCOUNT"),
        SELECTED_PS_IFP_FOR_ADD_DISCOUNT("SELECTED_PS_IFP_FOR_ADD_DISCOUNT"),
        PR_TP_STATUS(STATUS_FIELD),
        PR_TP_START_DATE(START_DATE_HEADER),
        PR_TP_END_DATE(END_DATE_HEADER),
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
        private final String constant;

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
        RAR_TYPE(RAR_TYPE_PROPERTY),
        BASIS(BASIS_PROPERTY),
        END_DATE(Constants.END_DATE);
        /* The constant */
        private final String constant;

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
    public final String copycontractResultsHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, COMPANY_FAMILY_PLAN, ITEM_FAMILY_PLAN, Constants.PRICE_SCHEDULE,
        REBATE_SCHEDULE
    };
    public final Object copycontractResultsColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "CFPname", IFP_NAME, PS_NAME, REBATE_SCHEDULE_NAME_PROPERTY};
    public final String copycontractRbResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, "Name", THERAPY_CLASS_PROPERTY, Constants.BRAND, Constants.FORM, Constants.STRENGTH, STATUS_S, START_DATE_HEADER,
        END_DATE_HEADER
    };
    public final Object copycontractRbResultsColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, "CFPname", IFP_NAME, PS_NAME, REBATE_SCHEDULE_NAME_PROPERTY};
    public final String copycontractDashboardResultsHeaders[] = new String[]{
        CATEGORY_LABEL, "ID", NUMBER_LABEL, "Name"
    };
    public final Object copycontractDashboardResultsColumns[] = new Object[]{
        CATEGORY, DASHBOARD_ID, DASHBOARD_NUMBER, DASHBOARD_NAME
    };
    public final String copycontractCfpResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER
    };
    public final Object copycontractCfpResultsColumns[] = new Object[]{
        "companyFamilyPlanId", "companyFamilyPlanNo", "companyFamilyPlanStatusValue",
        "companyFamilyPlanStartDate", "companyFamilyPlanEndDate"
    };
    public final String copycontractIfpResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER
    };
    public final Object copycontractIfpResultsColumns[] = new Object[]{
        "itemFamilyplanId", "itemFamilyplanNo", "displayIFPStatus",
        "ifpStartDate", "ifpEndDate"
    };
    public final String copycontractPsResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, "IFP", "Type", CATEGORY_LABEL, "Designation"
    };
    public final Object copycontractPsResultsColumns[] = new Object[]{
        "priceScheduleIdValue", "priceScheduleNoValue", "priceScheduleStatusValue",
        "priceScheduleStartDate", "priceScheduleEndDate", "itemFamilyplanName", "priceScheduleTypeValue", "priceScheduleCategoryValue", "priceScheduleDesignationValue"
    };
    public final String copycontractRsResultsHeaders[] = new String[]{
        "ID", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, "IFP", FREQUENCY_LABEL, RAR_TYPE_LABEL, BASIS_LABEL};
    public final Object copycontractRsResultsColumns[] = new Object[]{
        "rebateScheduleId", REBATE_SCHEDULE_NO_PROPERTY, "statusRebate", "itemRebateStartDate", "itemRebateEndDate", Constants.IFPNAME, "paymentFrequency", "rebateProgramType", "interestBearingBasis"
    };
    public final String copycontractCfpCompanyResultsHeaders[] = new String[]{
        Constants.COMPANYNAME, Constants.COMPANYNO, STATUS_S, START_DATE_HEADER, END_DATE_HEADER
    };
    public final Object copycontractCfpCompanyResultsColumns[] = new Object[]{
        COMPANY_NAME, COMPANY_NO, "companyStatusValue", "tradeClassStartDate", "tradeClassEndDate"
    };
    public final String copycontractIfpItemResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER
    };
    public final String THERAPEUTIC_CLASS_PROPERTY = "therapeuticClass";
    public final Object copycontractIfpItemResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPEUTIC_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String copycontractPsItemResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PROTECTION_START_DATE_LABEL};
    public final Object copycontractPsItemResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPEUTIC_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, PRICE_TYPE_PROPERTY, PRICE_PROTECTION_START_DATE_PROPERTY};
    public final String copycontractRsItemResultsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL};
    public final Object copycontractRsItemResultsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPEUTIC_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY};
    public final String itemContractSelectionDetailsHeader[] = new String[]{
        "", "ID", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, STATUS_S, START_DATE_HEADER,
        END_DATE_HEADER,};
    public final Object itemContractSelectionDetailsVisibleColumn[] = new Object[]{
        Constants.CHECK_RECORD, "id", NUMBER_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final Object contractSelectionCurrentVisibleColumn[] = new Object[]{
        Constants.CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE, Constants.END_DATE, COMPANY_NO, COMPANY_NAME, REBATE_SCHEDULE_NO_PROPERTY, REBATE_SCHEDULE_NAME_PROPERTY, RAR_CATEGORY_COLUMN, STATUS_S, COMPANY_START_DATE, COMPANY_END_DATE
    };
    public final String contractSelectionCurrentHeader[] = new String[]{
        StringUtils.EMPTY, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, Constants.COMPANYNO, Constants.COMPANYNAME, REBATE_SCHEDULE_NO_LABEL, REBATE_SCHEDULE_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public final Object currentIfpVisibleColumn[] = new Object[]{
        Constants.CHECK_RECORD, "ifpId", "ifpNumber", Constants.IFPNAME, IFP_STATUS_PROPERTY, "ifpType"
    };
    public final String currentIfpHeader[] = new String[]{
        "", "IFP Id", "IFP Number", Constants.IFP_NAME_LABEL, IFP_STATUS, IFP_TYPE_LABEL};
    public final String ifpItemHeader[] = new String[]{
        "", "ID", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, STATUS_S, START_DATE_HEADER,
        END_DATE_HEADER
    };
    public final Object ifpItemColumn[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_ID_PROPERTY, "itemNumber", ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final Object adCfpIfpResultsColumns[] = new Object[]{
        "id", NUMBER_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE};
    public final String adCfpIfpResultsHeaders[] = new String[]{
        "Id", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object adPsResultsColumns[] = new Object[]{
        "id", NUMBER_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, Constants.IFPNAME, "type", "psCategory", "designation"};
    public final String adPsResultsHeaders[] = new String[]{
        "Id", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, "IFP", "Type", CATEGORY_LABEL, "Designation"};
    public final Object adComponentDetailsColumnsCfp[] = new Object[]{
        "tpNo", "tpName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, STATUS_S, TRADE_CLASS_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String TP_CONTRACT_NO_LABEL = "TP Contract No";
    public static final String TRADING_PARTNER_NAME_LABEL = "Trading Partner Name";
    public final String adComponentDetailsHeadersCfp[] = new String[]{
        "Trading Partner No", TRADING_PARTNER_NAME_LABEL, TP_CONTRACT_NO_LABEL, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, Constants.TRADECLASS, ATTACHED_DATE_FIELD};
    public final Object adComponentDetailsColumnsPs[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, PRICE_TYPE_PROPERTY, PRICE_PLAN_NO_PROPERTY, PRICE_PLAN_NAME_PROPERTY, PRICE_PROTECTION_STATUS_PROPERTY, PRICE_PROTECTION_START_DATE_PROPERTY, PRICE_PROTECTION_END_DATE_PROPERTY, PRICE_PROTECTION_PRICE_TYPE_PROPERTY, PRICE_TOLERANCE_INTERVAL, PRICE_TOLERANCE_FREQUENCY_PROPERTY, PRICE_TOLERANCE_TYPE_PROPERTY, MAX_INCREMENTAL_CHANGE_PROPERTY, PRICE_TOLERANCE_PROPERTY, "resetEligibility", RESET_TYPE_PROPERTY, RESET_DATE_PROPERTY, RESET_INTERVAL_PROPERTY, RESET_FREQUENCY_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String adComponentDetailsHeadersPs[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PLAN_NO_LABEL, PRICE_PLAN_NAME_LABEL, "Price Protection  Status", PRICE_PROTECTION_START_DATE_LABEL, PRICE_PROTECTION_END_DATE_LABEL, " Price Protection Price Type ", PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_TYPE_LABEL, MAX_INCREMENTAL_CHANGE_LABEL, PRICE_TOLERANCE_LABEL,
        "Reset Eligibility", RESET_TYPE_LABEL, RESET_DATE_LABEL, RESET_INTERVAL_LABEL, RESET_FREQUENCY_LABEL, ATTACHED_DATE_FIELD};
    public final Object contractColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY};
    public final String contractHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND,
        STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object summarySearchColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, Constants.START_DATE,
        Constants.END_DATE, Constants.CFP_NAME, Constants.IFPNAME, Constants.PSNAME, Constants.RSNAME};
    public final String summarySearchHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER,
        END_DATE_HEADER, COMPANY_FAMILY_PLAN, ITEM_FAMILY_PLAN, Constants.PRICE_SCHEDULE, REBATE_SCHEDULE};
    public final Object addtpTreeColumns[] = new Object[]{
        CATEGORY, CONTRACT_ID_PROPERTY, Constants.CONTRACT_NO, Constants.CONTRACT_NAME};
    public final Object contractInfoColumns[] = new Object[]{
        COMPANY_NO, COMPANY_NAME, COMPANY_STATUS, COMPANY_START_DATE, COMPANY_END_DATE};
    public final String contractInfoHeaders[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, COMPANYSTATUS, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public final Object contractComponentColumns[] = new Object[]{
        CHECK, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, Constants.MARKET_TYPE, "contractStartDate", "contractEndDate", IFP_NAME, PS_NAME, "RSname"};
    public final String contractComponentHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, "IFP", "PS", "RS"};
    public final Object ccComponentDetailsColumns[] = new Object[]{
        COMPANY_NO, COMPANY_NAME, COMPANY_STATUS, COMPANY_START_DATE, COMPANY_END_DATE};
    public final String ccComponentDetailsHeaders[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, COMPANYSTATUS, START_DATE_HEADER, COMPANY_END_DATE_LABEL};
    public final Object componentDetailsItemColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public final String componentDetailsItemHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object componentDetailsPsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE, PRICE_TYPE_PROPERTY, "ppStartDate"};
    public final String componentDetailsPsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PROTECTION_START_DATE_LABEL};
    public final Object componentDetailsRsColumns[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE, REBATE_PLAN_PROPERTY, FORMULA_ID_PROPERTY};
    public final String componentDetailsRsHeaders[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_FIELD};
    public final Object adComponentDetailsColumnsIfp[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, ATTACHED_DATE_PROPERTY};
    public final String adComponentDetailsHeadersIfp[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, ATTACHED_DATE_FIELD};
    public final Object[] adComponentDetailsRsColumn = {ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, FORMULA_TYPE_PROPERTY, FORMULA_ID_PROPERTY, FORMULA_NAME_PROPERTY, REBATE_PLAN_ID_PROPERTY, REBATE_PLAN_NAME_PROPERTY, REBATE_AMOUNT_PROPERTY, BUNDLE_NO_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String[] adComponentDetailsRsHeader = {Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, FORMULA_TYPE_LABEL, FORMULA_ID_LABEL, FORMULA_NAME_LABEL, REBATE_PLAN_ID_LABEL,
        "RebatePlan Name", REBATE_AMOUNT_LABEL, BUNDLE_NO_LABEL, ATTACHED_DATE_FIELD};
    public final String[] adComponentDetailsPsHeader = {Constants.ITEM_NO, MAX_INCREMENTAL_CHANGE_LABEL, PRICE_TOLERANCE_LABEL, RESET_ELIGIBLE_LABLE_NAME, RESET_TYPE_LABEL, RESET_DATE_LABEL, RESET_INTERVAL_LABEL, RESET_FREQUENCY_LABEL, ATTACHED_DATE_FIELD};
    public final Object adComponentDetailsPsColumn[] = new Object[]{
        ITEM_NO_PROPERTY, MAX_INCREMENTAL_CHANGE_PROPERTY, PRICE_TOLERANCE_PROPERTY, RESET_ELIGIBLE_PROPERTY, RESET_TYPE_PROPERTY, RESET_DATE_PROPERTY, RESET_INTERVAL_PROPERTY, RESET_FREQUENCY_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final Object componentInfoColumnsCfp[] = new Object[]{
        COMPANY_NO, COMPANY_NAME, Constants.CONTRACT_NO, Constants.START_DATE, Constants.END_DATE, STATUS_S, TRADE_CLASS_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String componentInfoHeadersCfp[] = new String[]{
        Constants.COMPANYNO, Constants.COMPANYNAME, "COMPANY Contract No", START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, Constants.TRADECLASS, ATTACHED_DATE_FIELD};
    public final Object componentItemSearchColumnsPs[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, PRICE_TYPE_PROPERTY, PRICE_PLAN_NO_PROPERTY, PRICE_PLAN_NAME_PROPERTY, PRICE_PROTECTION_STATUS_PROPERTY, PRICE_PROTECTION_START_DATE_PROPERTY, PRICE_PROTECTION_END_DATE_PROPERTY, PRICE_PROTECTION_PRICE_TYPE_PROPERTY, PRICE_TOLERANCE_INTERVAL, PRICE_TOLERANCE_FREQUENCY_PROPERTY, PRICE_TOLERANCE_PROPERTY, RESET_ELIGIBLE_PROPERTY, RESET_TYPE_PROPERTY, RESET_DATE_PROPERTY, RESET_INTERVAL_PROPERTY, RESET_FREQUENCY_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String componentItemSearchHeadersPs[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PLAN_NO_LABEL, PRICE_PLAN_NAME_LABEL, PRICE_PROTECTION_STATUS_LABEL, PRICE_PROTECTION_START_DATE_LABEL, PRICE_PROTECTION_END_DATE_LABEL, PRICE_PROTECTION_PRICE_TYPE_LABEL, PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_LABEL,
        "Reset Eligiblity", RESET_TYPE_LABEL, RESET_DATE_LABEL, RESET_INTERVAL_LABEL, RESET_FREQUENCY_LABEL, ATTACHED_DATE_FIELD};
    public final Object componentItemSearchColumnsRs[] = new Object[]{
        Constants.CHECK_RECORD, "ifpId", Constants.IFPNAME, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, FORMULA_TYPE_PROPERTY, FORMULA_ID_PROPERTY, FORMULA_NAME_PROPERTY, REBATE_PLAN_ID_PROPERTY, REBATE_PLAN_NAME_PROPERTY, REBATE_AMOUNT_PROPERTY, BUNDLE_NO_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String componentItemSearchHeadersRs[] = new String[]{
        "", Constants.IFP_ID, Constants.IFP_NAME_LABEL, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, FORMULA_TYPE_LABEL, FORMULA_ID_FIELD, FORMULA_NAME_LABEL, REBATE_PLAN_ID_FIELD, REBATE_PLAN_NAME_LABEL, REBATE_AMOUNT_LABEL, BUNDLE_NO_LABEL, ATTACHED_DATE_FIELD};

    public static final String TRANSFER_ERROR_LABEL = "Transfer Error";

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
        NO_TP_SELECTED(TRANSFER_ERROR_LABEL),
        NO_TP_SELECTED_BODY("Please select a value in the Results list view then try again."),
        SELECT_CONTRACT_TITLE(TRANSFER_ERROR_LABEL),
        SELECT_CONTRACT_BODY("Please Select a contract and try again."),
        SEARCHC_COMPONENT_TITLE("Search Error"),
        SELECT_COMPONENT_BODY("Please select a Component and try again."),
        SELECT_FIELD_BODY("Please select a search field and try again."),
        NO_TP_SELECTED_TO_PROMOTE(TRANSFER_ERROR_LABEL),
        EMPTY_SPACE(""),
        NO_SEARCH_CRITERIA_TP("Please enter search criteria."),
        TP_NO_ROW_SELECTED("Error"),
        SELECT_VALUE_BODY("Please enter value and try again."),
        RD_NO_SEARCH_CRITERIA_BODY("Please enter value.");

        private final String constant;

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

    /**
     * Enum for Date format constants
     */
    public enum DateFormatConstants {

        DEFOULT_JAVA_DATE_FORMAT("EEE MMM dd HH:mm:ss z yyyy"),
        DEFOULT_SQL_DATE_FORMAT("yyyy-MM-dd HH:mm:ss.SSS"),
        MMDDYYYY(DATE_FORMAT),
        YYYYMMDDHHMMSSSSS("yyyy-MM-dd hh:mm:ss.SSS"),
        MMDDYYYYHHMMSS("MM/dd/yyyy hh:mm:ss");
        private final String constant;

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
    public final String itemContractSelectionDetailsHeaderAdd[] = new String[]{
        StringUtils.EMPTY, Constants.ITEM_NO, "ItemName", "Item id", "UOM", "Package Size", START_DATE_HEADER,
        END_DATE_HEADER, PRICE_TYPE_LABEL, "Contract Price", "CP StartDate", "CP EndDate", Constants.ITEM_STATUS, PRICE_TOLERANCE_LABEL,
        "Price Prodection StartDate", "Price Prodection EndDate", "Price Tolerence Type", "Price Tolerence Interval", "Price Tolerence Frequency",
        "BasePrice", "Revision Date", "Attached Status", "AttachedDate"
    };
    public final Object itemContractSelectionDetailsVisibleColumnAdd[] = new Object[]{
        Constants.CHECK_RECORD, NUMBER_PROPERTY, ITEM_NAME_PROPERTY, "id", "UOM", "packageSize", ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, PRICE_TYPE_PROPERTY, "contractPrice", "cpStartDate", "cpEndDate", ITEM_STATUS_PROPERTY, PRICE_TOLERANCE_PROPERTY, PRICE_PROTECTION_START_DATE_PROPERTY, PRICE_PROTECTION_END_DATE_PROPERTY, PRICE_TOLERANCE_TYPE_PROPERTY, PRICE_TOLERANCE_INTERVAL, PRICE_TOLERANCE_FREQUENCY_PROPERTY, "basePrice", "revisionDate", "attachedStatus", ATTACHED_DATE_PROPERTY};
    public final Object adIfpResultsColumns[] = new Object[]{
        "id", NUMBER_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE};
    public final String adIfpResultsHeaders[] = new String[]{
        "Id", NUMBER_LABEL, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public static final String E_DATE_PROPERTY = "eDate";
    public static final String S_DATE_PROPERTY = "sDate";
    /**
     * PTP_COMPONENT_INFO_COLUMNS
     */
    public final Object adNdRsComponentDetailsColumns[] = new Object[]{Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S,
        S_DATE_PROPERTY, E_DATE_PROPERTY, REBATE_PLAN_PROPERTY, FORMULA_NAME_PROPERTY};
    /**
     * The Constant PTP_COMPONENT_INFO_HEADERS.
     */
    public final String adNdRsComponentDetailsHeaders[] = new String[]{"", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, REBATE_PLAN_LABEL, FORMULA_ID_LABEL};
    public final String ifpAddItemHeader[] = new String[]{
        "", "ID", NUMBER_LABEL, "Name", Constants.THERAPY_CLASS, Constants.BRAND, Constants.FORM, Constants.STRENGTH, STATUS_FIELD, START_DATE_HEADER,
        END_DATE_HEADER, IFP_STATUS, "IFP Start Date", "IFP End Date"
    };
    public final Object ifpAddItemColumn[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_ID_PROPERTY, "itemNumber", ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, "form", STRENGTH_PROPERTY, ITEM_STATUS_PROPERTY, ITEM_START_DATE_PROPERTY, ITEM_END_DATE_PROPERTY, IFP_STATUS_PROPERTY, Constants.IFP_START_DATE, Constants.IFP_END_DATE};
    public final Object[] idenFormColOrder = new Object[]{
        "qualifier", "companyIdentifier", "identifierStatusName", Constants.START_DATE, Constants.END_DATE, "createdDate", "createdBy", "modifiedBy", "modifiedDate"
    };
    /**
     * The Constant IDEN_FORM_COL_HEADER.
     */
    public final String[] idenFormColHeader = new String[]{
        "Company Qualifier Name", "Company Identifier", "Identifier Status", START_DATE_HEADER, END_DATE_HEADER, "Created Date", "Created By", "Modified By", "Modified Date"};
    /**
     * The Constant TRADE_CLASS_COLUMNS.
     */
    public final Object[] tradeClassColumns = new Object[]{
        "tradeClassName", "tradeStartDate", "tradeEndDate", "createdDate", "createdBy", "modifiedBy", "modifiedDate"
    };
    /**
     * The Constant TRADE_CLASS_HEADERS.
     */
    public final String[] tradeClassHeaders = new String[]{
        Constants.TRADECLASS, "Trade Class Start Date", "Trade Class End Date", "Created Date", "Created By", "Modified By", "Modified Date"
    };
    public final Object newCompanyDetailsColumns[] = new Object[]{
        CHECK, COMPANY_ID_PROPERTY, COMPANY_NAME, COMPANY_NO, Constants.PS_START_DATE, Constants.PS_END_DATE, COMPANY_STATUS, TRADE_CLASS_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String newCompanyDetailsHeaders[] = new String[]{
        "", "Trading Partner No ", TRADING_PARTNER_NAME_LABEL, TP_CONTRACT_NO_LABEL, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, Constants.TRADECLASS, ATTACHED_DATE_FIELD};
    public final Object newIfpDetailsColumns[] = new Object[]{
        CHECK, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, Constants.PS_START_DATE, Constants.PS_END_DATE, ATTACHED_DATE_PROPERTY};
    public final String newIfpDetailsHeaders[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, ATTACHED_DATE_FIELD};
    public final Object newPsDetailsColumns[] = new Object[]{
        Constants.CHECK, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, Constants.PS_START_DATE, Constants.PS_END_DATE, PRICE_TYPE_PROPERTY, PRICE_PLAN_NO_PROPERTY, PRICE_PLAN_NAME_PROPERTY, PRICE_PROTECTION_STATUS_PROPERTY, COMPANY_START_DATE, COMPANY_END_DATE, PRICE_PROTECTION_PRICE_TYPE_PROPERTY, PRICE_TOLERANCE_INTERVAL, PRICE_TOLERANCE_FREQUENCY_PROPERTY, PRICE_TOLERANCE_TYPE_PROPERTY, MAX_INCREMENTAL_CHANGE_PROPERTY, PRICE_TOLERANCE_PROPERTY, "reset", "eligibility", RESET_TYPE_PROPERTY, RESET_DATE_PROPERTY, "resetIntervel", RESET_FREQUENCY_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String newPsDetailsHeaders[] = new String[]{
        Constants.EMPTY, Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PLAN_NO_LABEL, PRICE_PLAN_NAME_LABEL, PRICE_PROTECTION_STATUS_LABEL, PRICE_PROTECTION_START_DATE_LABEL, PRICE_PROTECTION_END_DATE_LABEL, PRICE_PROTECTION_PRICE_TYPE_LABEL, PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_TYPE_LABEL, MAX_INCREMENTAL_CHANGE_LABEL, PRICE_TOLERANCE_LABEL, "Reset", "Eligibility", RESET_TYPE_LABEL, RESET_DATE_LABEL, RESET_INTERVAL_LABEL, RESET_FREQUENCY_LABEL, ATTACHED_DATE_FIELD};
    public final Object adNdPsComponentDetailsColumns[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S, S_DATE_PROPERTY, E_DATE_PROPERTY, PRICE_TYPE_PROPERTY, "ppSDate"};
    public final Object newRsDetailsColumns[] = new Object[]{
        Constants.CHECK, IFP_NO_COLUMN, Constants.IFPNAME, BRAND_PROPERTY, ITEM_STATUS_PROPERTY, Constants.PS_START_DATE, Constants.PS_END_DATE, FORMULA_TYPE_PROPERTY, FORMULA_ID_PROPERTY, FORMULA_NAME_PROPERTY, REBATE_PLAN_ID_PROPERTY, REBATE_PLAN_NAME_PROPERTY, REBATE_AMOUNT_PROPERTY, BUNDLE_NO_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String newRsDetailsHeaders[] = new String[]{
        Constants.EMPTY, Constants.IFP_NO, Constants.IFP_NAME_LABEL, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, FORMULA_TYPE_LABEL, FORMULA_ID_LABEL, FORMULA_NAME_LABEL, REBATE_PLAN_ID_LABEL, REBATE_PLAN_NAME_LABEL, REBATE_AMOUNT_LABEL, BUNDLE_NO_LABEL, ATTACHED_DATE_FIELD};
    public final String adNdPsComponentDetailsHeaders[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, PRICE_TYPE_LABEL, PRICE_PROTECTION_START_DATE_LABEL};
    public final Object formulaSearchColumns[] = new Object[]{
        FORMULA_ID_PROPERTY, "formulaNo", FORMULA_NAME_PROPERTY};
    public final String formulaSearchHeaders[] = new String[]{
        FORMULA_ID_FIELD, "Formula No", FORMULA_NAME_LABEL};
    public final Object rebateSearchColumns[] = new Object[]{
        REBATE_PLAN_ID_PROPERTY, "rebatePlanNo", REBATE_PLAN_NAME_PROPERTY, "rebatePlanStatus", "rebatePlanType"};
    public final String rebateSearchHeaders[] = new String[]{
        REBATE_PLAN_ID_FIELD, "Rebate Plan No", REBATE_PLAN_NAME_LABEL, "Rebate Plan Status", "Rebate Plan Type"};
    public final Object adSelectedResultsColumnsIfp[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, THERAPY_CLASS_PROPERTY, BRAND_PROPERTY, STATUS_S, S_DATE_PROPERTY, E_DATE_PROPERTY};
    public final String adSelectedResultsHeadersIfp[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.THERAPY_CLASS, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER,};
    public final Object adSearchResultsColumnsIfp[] = new Object[]{
        Constants.CHECK_RECORD, ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, ATTACHED_DATE_PROPERTY};
    public final String adSearchResultsHeadersIfp[] = new String[]{
        "", Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, ATTACHED_DATE_FIELD};
    public final Object removeTpContractSelectionColumns[] = new Object[]{
        CHECK_RECORD, PROJ_ID_LINK, WORKFLOW_STATUS_PROPERTY, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY, CFP_NO_PROPERTY,
        Constants.CFP_NAME, IFP_NO_COLUMN, Constants.IFPNAME, "pSNo", PS_NAME_PROPERTY, "rSNo", RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY};
    public final String removeTpSelectionHeaders[] = new String[]{
        "", PROJECTION_ID_LABEL, WORKFLOW_STATUS_LABEL, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL, CFP_NO_HEADER,
        CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, PS_NO_LABEL, PS_NAME_FIELD, RS_NO_HEADER, RS_NAME_LABEL, RAR_CATEGORY_HEADER};
    public static final String STATUS_DESCRIPTION_PROPERTY = "statusDescription";
    public final Object removeTpSummaryContractSelectionColumns[] = new Object[]{
        CHECK_RECORD, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, Constants.CFP_NAME,
        Constants.IFPNAME, PS_NAME_PROPERTY, RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY, STATUS_DESCRIPTION_PROPERTY, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public final String removeTpContractSelectionHeaders[] = new String[]{
        "", CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, CFP_NAME_HEADER,
        Constants.IFP_NAME_LABEL, PS_NAME_FIELD, RS_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public final Object excelRemoveTpSummaryContractSelectionColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, Constants.CFP_NAME,
        Constants.IFPNAME, PS_NAME_PROPERTY, RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY, STATUS_DESCRIPTION_PROPERTY, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public final String excelRemoveTpContractSelectionHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, CFP_NAME_HEADER,
        Constants.IFP_NAME_LABEL, PS_NAME_FIELD, RS_NAME_LABEL, RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    public final Object adComponentDetailsColumnsIfpNew[] = new Object[]{
        ITEM_NO_PROPERTY, ITEM_NAME_PROPERTY, BRAND_PROPERTY, STATUS_S, Constants.START_DATE, Constants.END_DATE, ATTACHED_DATE_PROPERTY};
    public final String adComponentDetailsHeadersIfpNew[] = new String[]{
        Constants.ITEM_NO, Constants.ITEM_NAME, Constants.BRAND, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER, ATTACHED_DATE_FIELD};
    public final Object transferCustomerColumns[] = new Object[]{
        CHECK_RECORD, PROJ_ID_LINK, WORKFLOW_STATUS_PROPERTY, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, STATUS_S, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY, CFP_NO_PROPERTY,
        Constants.CFP_NAME, IFP_NO_COLUMN, Constants.IFPNAME, "pSNo", PS_NAME_PROPERTY, "rSNo", RS_NAME_PROPERTY, RAR_CATEGORY_PROPERTY};
    public final String transferCustomerHeaders[] = new String[]{
        "", PROJECTION_ID_LABEL, WORKFLOW_STATUS_LABEL, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL, CFP_NO_HEADER,
        CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, PS_NO_LABEL, PS_NAME_FIELD, RS_NO_HEADER, RS_NAME_LABEL, RAR_CATEGORY_HEADER};

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
        private final String frequencyValue;

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
    public final Object[] adSearchResultsColumnsCfp = new Object[]{Constants.CHECK_RECORD, "tradingPartnerNo", "tradingPartnerName", "tpContractNo", Constants.START_DATE, Constants.END_DATE, STATUS_S, TRADE_CLASS_PROPERTY, ATTACHED_DATE_PROPERTY};
    public final String[] adSearchResultsHeadersCfp = {"", "Trading Partner No", TRADING_PARTNER_NAME_LABEL, TP_CONTRACT_NO_LABEL, START_DATE_HEADER, END_DATE_HEADER, STATUS_FIELD, Constants.TRADECLASS, ATTACHED_DATE_FIELD};
    public final Object excelRemoveTpContractSelectionColumns[] = new Object[]{
        ConstantsUtil.PROJECTION_ID, WORKFLOW_STATUS_PROPERTY, Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY, CFP_NO_PROPERTY,
        Constants.CFP_NAME, IFP_NO_COLUMN, Constants.IFPNAME, "pSNo", PS_NAME_PROPERTY, "rSNo"};
    public final String excelRemoveTpSelectionHeaders[] = new String[]{
        PROJECTION_ID_LABEL, WORKFLOW_STATUS_LABEL, CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, START_DATE_HEADER, END_DATE_HEADER, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL, CFP_NO_HEADER,
        CFP_NAME_HEADER, Constants.IFP_NO, Constants.IFP_NAME_LABEL, PS_NO_LABEL, PS_NAME_FIELD, RS_NO_HEADER};
    public final Object adSelectedResultsColumnsCfp[] = new Object[]{
        Constants.CHECK_RECORD, COMPANY_NO, COMPANY_NAME, STATUS_S, S_DATE_PROPERTY, E_DATE_PROPERTY};
    public final String adSelectedResultsHeadersCfp[] = new String[]{
        "", Constants.COMPANYNO, Constants.COMPANYNAME, STATUS_FIELD, START_DATE_HEADER, END_DATE_HEADER};
    public final Object excelSummaryContractSelectionColumns[] = new Object[]{
        Constants.CONTRACT_HOLDER, Constants.CONTRACT_NO, Constants.CONTRACT_NAME, CONTRACT_TYPE_PROPERTY, CONT_START_DATE_PROPERTY, CONT_END_DATE_PROPERTY, Constants.CFP_NAME, CFP_NO_PROPERTY, Constants.IFPNAME, IFP_NO_COLUMN, PS_NAME_PROPERTY, "pSNo", RS_NAME_PROPERTY, "rSNo", RAR_CATEGORY_PROPERTY, STATUS_DESCRIPTION_PROPERTY, COMP_START_DATE_PROPERTY, COMP_END_DATE_PROPERTY};
    public final String excelSummaryContractSelectionHeaders[] = new String[]{
        CONTRACT_HOLDER_HEADER, CONTRACT_NO_HEADER, CONTRACT_NAME_HEADER, MARKET_TYPE_HEADER, CONTRACT_START_DATE_LABEL, CONTRACT_END_DATE_LABEL, CFP_NAME_HEADER,
        CFP_NO_HEADER, Constants.IFP_NAME_LABEL, Constants.IFP_NO, PS_NAME_FIELD, PS_NO_LABEL, RS_NAME_LABEL, RS_NO_HEADER,
        RAR_CATEGORY_HEADER, STATUS_FIELD, COMPANY_START_DATE_LABEL, COMPANY_END_DATE_LABEL};
    private static Constants object;
    /**
     * Constructor
     */
    private Constants() {
        /*
            Constructor
        */
    }

    public static Constants getInstance() {
        if (object == null) {
            object = new Constants();
        }
        return object;
    }
    
    public static String getADDBY() {
		return ADDBY;
	}

	public static void setADDBY(String aDDBY) {
		ADDBY = aDDBY;
	}

	/*
            Constants for Item Management
     */
    public static final String MEASUREMENT_PRICE_LABLE_NAME = "Measurement Price";
    public static final String NEP_LABLE_NAME = "NEP";
    public static final String NEP_FORMULA_LABLE_NAME = "NEP Formula";
    public static final String BASE_PRICE_TYPE_LABLE_NAME = "Base Price Type";
    public static final String NET_PRICE_TYPE_LABLE_NAME = "Net Price Type";
    public static final String NET_RESET_PRICE_TYPE_LABLE_NAME = "Net Reset Price Type";
    public static final String RESET_ELIGIBLE_LABLE_NAME = "Reset Eligible";
    public static final String NET_SUBSEQUENT_PERIOD_PRICE_LABLE_NAME = "Net Subsequent Period Price";
    public static final String RESET_FREQUENCY_LABLE_NAME = "Reset Frequency";
    public static final String RESET_PRICE_TYPE_LABLE_NAME = "Reset Price Type";
    public static final String RESET_INTERVAL_LABLE_NAME = "Reset Interval";
    public static final String RESET_TYPE_LABLE_NAME = "Reset Type";
    public static final String RESET_DATE_LABLE_NAME = "Reset Date";
    public static final String BASELINE_NET_WAC_LABLE_NAME = "Baseline Net WAC";
    public static final String NET_PRICE_TYPE_FORMULA_LABLE_NAME = "Net Price Type Formula";
    public static final String NET_SUBSEQUENT_PERIOD_PRICE_FORMULA_LABLE_NAME = "Net Subsequent Period Price Formula";
    public static final String NET_BASELINE_WAC_FORMULA_LABLE_NAME = "Net Baseline WAC Formula";
    public static final String MAX_INCREMENTAL_CHANGE_LABLE_NAME = "Max Incremental Change";
    public static final String SUBSEQUENT_PERIOD_PRICE_TYPE_LABLE_NAME = "Subsequent Period Price Type";
    public static final String NET_RESET_PRICE_FORMULA_LABLE_NAME = "Net Reset Price Formula";
    public static final String BASELINE_WAC_LABLE_NAME = "Baseline WAC";
    public static final String MANUAL_LABLE_NAME = "Manual"; 
    public static final String DATE_LABLE_NAME = "Date";
    public static final String BASELINE_WAC_MANUAL_LABLE_NAME = "baseLineWacManual";
    public static final String BASELINE_WAC_DATE_LABLE_NAME = "baseLineWacDate";
    public static final String BASELINE_WAC_PRICE_TYPE_LABLE_NAME = "baseLineWacPriceType";
    /*
            Constants for Item Management Column
     */

    public static final String NEP_COLUMN_NAME = "NEP";
    public static final String MAX_INCREMENTAL_CHANGE_COLUMN_NAME = "MAX_INCREMENTAL_CHANGE";
    public static final String RESET_ELIGIBLE_COLUMN_NAME = "RESET_ELIGIBLE";
    public static final String RESET_TYPE_COLUMN_NAME = "RESET_TYPE";
    public static final String RESET_DATE_COLUMN_NAME = "RESET_DATE";
    public static final String RESET_INTERVAL_COLUMN_NAME = "RESET_INTERVAL";
    public static final String RESET_FREQUENCY_COLUMN_NAME = "RESET_FREQUENCY";
    public static final String NET_PRICE_TYPE_COLUMN_NAME = "NET_PRICE_TYPE";
    public static final String NET_PRICE_TYPE_FORMULA_COLUMN_NAME = "NET_PRICE_TYPE_FORMULA";
    public static final String RESET_PRICE_TYPE_COLUMN_NAME = "RESET_PRICE_TYPE";
    public static final String NET_RESET_PRICE_TYPE_COLUMN_NAME = "NET_RESET_PRICE_TYPE";
    public static final String NET_RESET_PRICE_FORMULA_COLUMN_NAME = "NET_RESET_PRICE_FORMULA_ID";
    public static final String BASE_PRICE_TYPE_COLUMN_NAME = "BASE_PRICE_TYPE";
    public static final String SUBSEQUENT_PERIOD_PRICE_TYPE_COLUMN_NAME = "SUBSEQUENT_PERIOD_PRICE_TYPE";
    public static final String NET_SUBSEQUENT_PERIOD_PRICE_COLUMN_NAME = "NET_SUBSEQUENT_PERIOD_PRICE";
    public static final String NET_SUBSEQUENT_PRICE_FORMULA_COLUMN_NAME = "NET_SUBSEQUENT_PRICE_FORMULA_ID";
    public static final String NET_BASELINE_WAC_FORMULA_COLUMN_NAME = "NET_BASELINE_WAC_FORMULA_ID";
    public static final String PRICE_TYPE_COLUMN_NAME = "PRICE_TYPE";
    public static final String MEASUREMENT_PRICE_COLUMN_NAME = "PRICE_PROTECTION_PRICE_TYPE";
    public static final String NEP_FORMULA_COLUMN_NAME = "NEP_FORMULA";
    public static final String PRICE_PROTECTION_STATUS_COLUMN_NAME = "PRICE_PROTECTION_STATUS";
    public static final String BASELINE_NET_WAC_COLUMN_NAME = "NET_BASE_PRICE";
    public static final String BASELINE_WAC_DATE_COLUMN_NAME = "BASELINE_WAC_DATE";
    public static final String BASELINE_WAC_MANUAL_COLUMN_NAME = "BASELINE_WAC_MANUAL";
    public static final String BASELINE_WAC_PRICE_TYPE_COLUMN_NAME = "BASELINE_WAC_PRICE_TYPE";

    /*
            Constants for Item Management List Names
     */
    public static final String LOCKED_STATUS_LISTNAME = "LOCKED_STATUS";
    public static final String RESET_TYPE_LISTNAME = "RESET_TYPE";
    public static final String BASE_PRICE_TYPE_LISTNAME = "BASE_PRICE_TYPE";
    public static final String NS_FORMULA_TYPE_LISTNAME = "NS_FORMULA_TYPE";
    
    public static final String SEARCH_ICON = "searchicon";
    
    public static final String HTTP = "http://";
    public static final String WEB_WORKFLOW = "/web/guest/workflow-inbox";
    public static final String HEIGHT_WIDTH = "height=800,width=1000,resizable,scrollbars=1";
    public static final String TOOL_BAR = "toolbar=no,scrollbars=1,location=no";
    public static final String PROJECTION_MASTER_SID = "projectionMasterSid";  
    
}
