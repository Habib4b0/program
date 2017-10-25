package com.stpl.app.contract.dashboard.util;

import com.stpl.app.contract.dao.impl.DashboardLogicDAOImpl;
import com.stpl.app.contract.global.logic.ItemSearchLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.domain.contract.contractdashboard.DashboardDAO;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * The Class ContractUtils.
 */
public class ContractUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ContractUtils.class);
    /**
     * Contract search component
     */
    public static final String CONTRACT_DETAILS_COMPONENT = "Contract";
    /**
     * CFP search Component
     */
    public static final String CFP_COMPONENT = "Company Family Plan";

    /**
     * IFP search Component
     */
    public static final String IFP_COMPONENT = "Item Family Plan";

    /**
     * PS search Component
     */
    public static final String PS_COMPONENT = "Price Schedule";

    /**
     * RS search Component
     */
    public static final String RS_COMPONENT = "Rebate Schedule";

    /**
     * The Constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The Constant STRING_ASTERISK.
     */
    public static final String STRING_ASTERISK = "*";
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    /**
     * The Constant MMDDYYYYHHMMMSS.
     */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * The Constant STRING_NULL.
     */
    public static final String STRING_NULL = "null";
    /**
     * The Constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant PRICE_TYPE.
     */
    public static final String PRICE_TYPE = "PriceType";
    /**
     * The Constant PRICE_TOLERANCE.
     */
    public static final String PRICE_TOLERANCE = "PriceTolerance";
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
     * The Constant CURRENT_DATE.
     */
    public static final Date CURRENT_DATE = new Date();
    /**
     * The Constant REBUILD_SUCCESS_MSG.
     */
    public static final String REBUILD_SUCCESS_MSG = "The selected contract Rebuilt successfully";
    /**
     * The Constant DISASSEMBLED_SUCCESS_MSG.
     */
    public static final String DISASSEMBLED_SUCCESS_MSG = "The selected contract disassembled successfully";
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    /**
     * The dao.
     */
    private final DashboardDAO dao = new DashboardLogicDAOImpl();
    /**
     * The Constant START_DATE.
     */
    public static final String START_DATE = "startDate";
    /**
     * The Constant END_DATE.
     */
    public static final String END_DATE = "endDate";
    /**
     * The Constant TRADINGPARTNER_SYSTEMID.
     */
    public static final String TRADINGPARTNER_SYSTEMID = "tradingPartnerSystemId";
    /**
     * The Constant CONTRACT_NO.
     */
    public static final String CONTRACT_NO = "contractNo";

    /**
     * Inbound Status
     */
    public final static String INBOUND_STATUS = "inboundStatus";

    /**
     * Inbound Status A
     */
    public final static String INBOUND_STATUS_A = "A";

    /**
     * Inbound Status D
     */
    public final static String INBOUND_STATUS_D = "D";
    /**
     * Inbound Status C
     */
    public final static String INBOUND_STATUS_C = "C";

    /**
     * The Constant PROCESS_STATUS.
     */
    public static final String PROCESS_STATUS = "processStatus";
    /**
     * The Constant DRAFT.
     */
    public static final String DRAFT = "Draft";
    /**
     * The Constant COMPANY_FAMILY_PLANNO.
     */
    public static final String COMPANY_FAMILY_PLANNO = "cfpNo";
    /**
     * The Constant COMPANY_FAMILY_PLANNO.
     */
    public static final String RS_CONTRACT = "RS_CONTRACT";
    /**
     * The Constant ITEMFAMILY_PLANNO.
     */
    public static final String ITEMFAMILY_PLANNO = "ifpNo";
    /**
     * The Constant PRICE_SCHEDULE_NO.
     */
    public static final String PRICE_SCHEDULE_NO = "psNo";
    /**
     * The Constant REBATE_SCHEDULE_NO.
     */
    public static final String REBATE_SCHEDULE_NO = "rsNo";
    /**
     * The Constant USER_ID.
     */
    public static final String USER_ID = "userId";
    /**
     * The Constant SAVED.
     */
    public static final String SAVED = "Saved";
    /**
     * The Constant UNCHECKED.
     */
    public static final String UNCHECKED = "unchecked";
    /**
     * The Constant IFP_COL_HEADERS.
     */
    public static final String[] IFP_COL_HEADERS = new String[]{"item Familyplan Id", "item Familyplan No", "item Familyplan Name", "item Familyplan Status", "item Familyplan Type",
        "item Familyplan Category", "item Familyplan Start Date", "item Familyplan End Date", "item Familyplan Designation", "total Dollar Commitment", "commitment Period",
        "total Volume Commitment", "total Market share Commitment"};
    /**
     * The Constant IFP_SEARCH_TABLE.
     */
    public static final Object[] IFP_SEARCH_TABLE = new Object[]{"itemFamilyplanId", "itemFamilyplanNo", "itemFamilyplanName", "itemFamilyplanStatus", "itemFamilyplanType",
        "itemFamilyplanCategory", "itemFamilyplanStartDate", "itemFamilyplanEndDate", "itemFamilyplanDesignation", "totalDollarCommitment", "commitmentPeriod", "totalVolumeCommitment",
        "totalMarketshareCommitment"};
    /**
     * The Constant AVAILABLE_ITEM_COL.
     */
    public static final Object[] AVAILABLE_ITEM_COL = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, "itemDesc", "itemStatus", "form", "strength", "therapeuticClass", "brand"};
    /**
     * The Constant AVAILABLE_ITEM_COL_HEADER.
     */
    public static final String[] AVAILABLE_ITEM_COL_HEADER = new String[]{"Item No", "Item Name", "Item Desc", "Item Status", "Form", "Strength", "Therapeutic Class", "Brand"};
    /**
     * The Constant SELECTED_ITEM_COL.
     */
    public static final Object[] SELECTED_ITEM_COL = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, "itemDesc", "itemStatus", "form", "strength", "therapeuticClass", "brand"};
    /**
     * The Constant SELECTED_ITEM_COL_HEADER.
     */
    public static final String[] SELECTED_ITEM_COL_HEADER = new String[]{"Item No", "Item Name", "Item Desc", "Item Status", "Form", "Strength", "Therapeutic Class", "Brand"};
    /**
     * The Constant ITEM_DETAILS_COL.
     */

    public static final Object[] ITEM_DETAILS_COL = new Object[]{"checkbox", "recordType", "itemId", Constants.ITEM_NO, Constants.ITEM_NAME, "brand", "globalitemstatus", "cpStartDate", "cpEndDate", Constants.PRICE_TYPE, Constants.PRICE,
         "suggestedPrice","source","createdBy","createdDate", Constants.ATTACHED_DATE};

    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public static final String[] ITEM_DETAILS_COL_HEADER = new String[]{"", "Item No", "Item Name", "Item ID", "UOM", "Package Size", "Start Date", "End Date", "Price",
        "Price Type", "CP Start Date", "CP End Date", "Item status", "Price Tolerance", "Price Protection Start Date", "Price Protection End Date",
        "Price Tolerance Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Base Price", "Revision Date", "Attached Status", Constants.ATTACHEDDATE};
    /**
     * The Constant AVAILABLE_COMPANY_COL.
     */
    public static final Object[] AVAILABLE_COMPANY_COL = new Object[]{Constants.COMPANY_ID, Constants.COMPANY_NO, Constants.COMPANY_NAME, "displayCompanyStatus", "displayCompanyType",  Constants.TRADE_CLASS,"companyCategory","companyGroup",};
    /**
     * The Constant AVAILABLE_COMPANY_COL_HEADER.
     */
    public static final String[] AVAILABLE_COMPANY_COL_HEADER = new String[]{"Company No", "Company Name", "Company Type", "Company Status",Constants.COMPANY_ID,Constants.TRADE_CLASS,"Company Category","Company Group",};
    /**
     * The Constant SELECTED_COMPANY_COL.
     */
    public static final Object[] SELECTED_COMPANY_COL = new Object[]{Constants.COMPANY_ID, Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, "companyType",  Constants.TRADE_CLASS,"companyCategory","companyGroup",};
    /**
     * The Constant SELECTED_COMPANY_COL_HEADER.
     */
    public static final String[] SELECTED_COMPANY_COL_HEADER = new String[]{"Company No", "Company Name", "Company Type", "Company Status",Constants.COMPANY_ID,Constants.TRADE_CLASS,"companyCategory","companyGroup",};
    /**
     * The Constant CFP_ITEM_DETAILS_COL.
     */
    public static final Object[] CFP_ITEM_DETAILS_COL = new Object[]{"checkbox", "recordType", "companyId", Constants.COMPANY_NO, Constants.COMPANY_NAME, "companyFamilyPlanStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", Constants.COMPANY_STATUS, "companyType", "cfpDetailsTradeClass", "companyCategory", 
       "tradingPartnerContractNo", Constants.ATTACHED_DATE, "modifiedDate", "cfpDetailsModifiedBy", "createdDate", "createdBy"};
    /**
     * The Constant CFP_ITEM_DETAILS_COL_HEADER.
     */
    public static final String[] CFP_ITEM_DETAILS_COL_HEADER = new String[]{"", "Trading Partner ID", "Company No", "Company Name", "Company Family Plan Status", "Company Family Plan Start Date", "Company Family Plan End Date", "Company Status", "Company Type", "Company Category", "Company TradeClass", "Company Start Date", "Company End Date",
        "Trading Partner Contract No", Constants.ATTACHEDDATE, "Modified Date", "Modified By"};
    /**
     * The Constant CFP_ITEM_DETAILS_VIEW_COL.
     */
    public static final Object[] CFP_ITEM_DETAILS_VIEW_COL = new Object[]{"companyId", Constants.COMPANY_NO, Constants.COMPANY_NAME, "companyFamilyPlanStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", Constants.COMPANY_STATUS, "companyType", "companyCategory", "cfpDetailsTradeClass", "companyStartDate",
        "companyEndDate", "tradingPartnerContractNo", Constants.ATTACHED_DATE, "modifiedDate", "cfpDetailsModifiedBy"};
    /**
     * The Constant CFP_ITEM_DETAILS_VIEW_COL_HEADER.
     */
    public static final String[] CFP_ITEM_DETAILS_VIEW_COL_HEADER = new String[]{"Trading Partner ID", "Company No", "Company Name", "Company Family Plan Status", "Company Family Plan Start Date", "Company Family Plan End Date", "Company Status", "Company Type", "Company Category", "Company TradeClass", "Company Start Date", "Company End Date",
        "Trading Partner Contract No", Constants.ATTACHEDDATE, "Modified Date", "Modified By"};
    /**
     * The Constant ITEM_DETAILS_VEIW_COL.
     */
    public static final Object[] ITEM_DETAILS_VEIW_COL = new Object[]{"itemId", "recordType", Constants.ITEM_NO, Constants.ITEM_NAME,  "brand",  "globalitemstatus", "cpStartDate", "cpEndDate", Constants.PRICE_TYPE, Constants.PRICE,
           "suggestedPrice", "source", "createdBy", "createdDate", Constants.ATTACHED_DATE};
    
    /**
     * The Constant ITEM_DETAILS_VIEW_COL_HEADER.
     */
    public static final String[] ITEM_DETAILS_VIEW_COL_HEADER = new String[]{"Item No", "Item Name", "Item Id", "Uom", "Package Size", "Start Date", "End Date", "Price",
        "Price Type", "CP Start Date", "CP End Date", "Item Status", "Price Tolerance", "Price Protection Start Date", "Price Protection End Date",
        "Price Tolerance Type", "Price Tolerance Interval", "Price Tolerance Frequency", "Base Price", "Revision Date", "Attached Status", Constants.ATTACHEDDATE};
    /**
     * The Constant ITEM_DETAILS_VIEW_COLUMNS_IN_RS.
     */
    public static final Object[] ITEM_DETAILS_VIEW_COLUMNS_IN_RS = new Object[]{"recordType",Constants.ITEM_NO, Constants.ITEM_NAME, "itemType", "formulaId", "formulaName", "rebatePlanName",
        "rebateStartDate", "rebateEndDate", "rebateRevisionDate"};
    /**
     * The Constant ITEM_DETAILS_VIEW_HEADER_IN_RS.
     */
    public static final String[] ITEM_DETAILS_VIEW_HEADER_IN_RS = new String[]{"Item No", "Item Name", "Item Type", "Formula ID", "Formula Name", "Rebate Plan Name",
        "Start Date", "End Date", "Revision Date"};

    /**
     * The Constant ITEM_DETAILS_VIEW_COLUMNS_IN_RS.
     */
    public static final Object[] ITEM_DETAILS_VIEW_COLUMNS_IN_RS_WITH_BUNDLE = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, "itemType", "formulaId", "formulaName", "bundleNo", "rebatePlanName",
        "rebateStartDate", "rebateEndDate", "rebateRevisionDate"};
    /**
     * The Constant ITEM_DETAILS_VIEW_HEADER_IN_RS.
     */
    public static final String[] ITEM_DETAILS_VIEW_HEADER_IN_RS_WITH_BUNDLE = new String[]{"Item No", "Item Name", "Item Type", "Formula ID", "Formula Name", "Bundle No", "Rebate Plan Name",
        "Start Date", "End Date", "Revision Date"};

    /**
     * The Constant APOSTROPHE_SP_CHAR.
     */
    public static final String APOSTROPHE_SP_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\*|\\@|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\'\\'\\)])*";

    /**
     * The Constant SPECIAL_CHAR_MSGS.
     */
    public static final String SPECIAL_CHAR_MSGS = "Allowed Special characters are @,#,.,%,*,$,&,_,-,(,),/,!,'";

    /**
     * The Constant SPECIAL_CHAR.
     */
    public static final String SPECIAL_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\-|\\@|\\*|\\#|\\$|\\&|\\%|\\s|\\/|\\(|\\!|\\)])*";

    /**
     * The Constant SPECIAL_CHAR_MSG.
     */
    public static final String SPECIAL_CHAR_MSG = "Allowed Special characters are @,#,.,%,*,$,&,_,-,(,),/,!";

    /**
     * The Constant SEARCH_SP_CHAR.
     */
    public static final String SEARCH_SP_CHAR = "([0-9|a-z|A-Z|\\.|\\,|\\_|\\@|\\*|\\#|\\$|\\&|\\-|\\s])*";

    /**
     * The Constant SEARCH_SPCHAR_MSG.
     */
    public static final String SEARCH_SPCHAR_MSG = "Allowed Special characters are @,*,#,.,$,&,_,-";
    
    /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public static final Object[] PRICE_PROTECTION_COL = new Object[]{"checkbox","recordType",
        "itemID", "itemNo", "itemName", "brand", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType",
        "nep", "nepFormula", "basePriceType", "basePriceValue",
        "netBasePrice", "netBasePriceFormula", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormula",
        "ppPriceToleranceInterval", "ppPriceToleranceFrequency", "ppPriceToleranceType",
        "priceTolerance", "maxIncrementalChange", "resetEligible", "resetType",
        "resetDate", "resetInterval", "resetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceFormula",
        "netPriceType", "netPriceTypeFormula",
        "attachedDate"};

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public static final String[] PRICE_PROTECTION_COL_HEADER = new String[]{
        "", "Record Type", "Item ID", "Item No", "Item Name", "Brand", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type", "Base Price", "Net Base Price",
        "Net Base Price Formula", "Subsequent Period Price Type", "Net Subsequent Period Price", "Net Subsequent Period Price Formula",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type",
        "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula",
        "Attached Date"};

    /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public static final Object[] PRICE_PROTECTION_COL_VIEW = new Object[]{"recordType",
        "itemID", "itemNo", "itemName", "brand", "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType",
        "nep", "nepFormula", "basePriceType", "basePriceValue",
        "netBasePrice", "netBasePriceFormula", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormula",
        "ppPriceToleranceInterval", "ppPriceToleranceFrequency", "ppPriceToleranceType",
        "priceTolerance", "maxIncrementalChange", "resetEligible", "resetType",
        "resetDate", "resetInterval", "resetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceFormula",
        "netPriceType", "netPriceTypeFormula",
        "attachedDate"};

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public static final String[] PRICE_PROTECTION_COL_HEADER_VIEW = new String[]{
        "Record Type", "Item ID", "Item No", "Item Name", "Brand", "Price Protection Status",
        "Price Protection Start Date", "Price Protection End Date", "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type", "Base Price", "Net Base Price",
        "Net Base Price Formula", "Subsequent Period Price Type", "Net Subsequent Period Price", "Net Subsequent Period Price Formula",
        "Price Tolerance Interval", "Price Tolerance Frequency", "Price Tolerance Type",
        "Price Tolerance", "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula",
        "Attached Date"};
    
    public static final Object[] PRICE_PROTECTION_HISTORY = new Object[]{"priceScheduleId",
        "priceScheduleNo", "priceScheduleName", "priceScheduleStatus", "priceScheduleStartDate", "priceScheduleEndDate",
        "priceScheduleDesignation", "parentPriceScheduleId", "parentPriceScheduleName",
        "priceScheduleType", "createdBy", "createdDate", "priceScheduleCategory",
        "modifiedBy", "modifiedDate", "priceScheduleTradeClass"};

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public static final String[] PRICE_PROTECTION_HISTORY_HEADER = new String[]{
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name", "Price Schedule Status", "Price Schedule Start Date", "Price Schedule End Date",
        "Price Schedule Designation", "Parent Price Schedule ID", "Parent Price Schedule Name",
        "Price Schedule Type", "Created By", "Created Date", "Price Schedule Category", "Modified By",
        "Modified Date", "Price Schedule  Trade Class"};
    public static final Object[] NSR_COLUMN = new Object[]{"ruleType", "ruleNo", "ruleName", "ruleCategoryString"};

    public static final String[] NSR_HEADER = new String[]{"Rule Type", "Rule No", "Rule Name", "Rule Category"};

    public static final Object[] NSR_DETAILS_COLUMN = new Object[]{"lineType", "association", "keyword", "operator", "value", "comparison", "logicalOperator"};

    public static final String[] NSR_DETAILS_HEADER = new String[]{"Line Type", "Item/Group/Association", "Keyword", "Operator", "Value", "Comparison", "Operator"};

    public final static String CALC_FORMULA = "Formula";
    public final static String CALC_REBATE_PLAN = "Rebate Plan";
    public final static String CALC_DEDUCTION_CALENDAR = "Deduction Calendar";
    public final static String NET_SALES_FORMULA = "Net Sales Formula";
    public final static String NET_SALES_RULE = "Net Sales Rule";
    public final static String EVALUATION_RULE = "Evaluation Rule";
    public final static String CALCULATION_RULE = "Calculation Rule";
    public final static String EVALUATION_RULE_BUNDLE = "Evaluation Rule Bundle";
    public final static String CALCULATION_RULE_BUNDLE = "Calculation Rule Bundle";
    public final static String FORMULA_TYPE = "Formula Type";
    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final Object[] REBATE_SETUP_DEFAULT = new Object[]{
        "checkbox", "recordType", "itemNo", "itemName", "attachedStatus", "rebateStartDate", "rebateEndDate"};

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public static final String[] REBATE_SETUP_DEFAULT_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status", "RS Start Date", "RS End Date"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final Object[] REBATE_SETUP_FORMULA = new Object[]{
        "checkbox","recordType", "itemNo", "itemName", "attachedStatus", "rebateStartDate", "rebateEndDate", "formulaType", "formulaNo", "formulaName", "netSalesFormulaNo", "netSalesRule", "evaluationRule", "evaluationRuleBundle", "calculationRule", "calculationRuleBundle", "attachedDate"};

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public static final String[] REBATE_SETUP_FORMULA_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status", "RS Start Date", "RS End Date", "Formula Type", "Formula No", "Formula Name", "Net Sales Formula", "Net Sales Rule", "Evaluation Rule", "Evaluation Rule Bundle", "Calculation Rule", "Calculation Rule Bundle", "Attached Date"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final Object[] REBATE_SETUP_DEDUCTION_CALENDAR = new Object[]{
        "checkbox", "recordType","itemNo", "itemName", "attachedStatus", "rebateStartDate", "rebateEndDate", "deductionCalendarNo", "evaluationRule", "evaluationRuleBundle", "calculationRule", "calculationRuleBundle", "attachedDate"};

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public static final String[] REBATE_SETUP_DEDUCTION_CALENDAR_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status", "RS Start Date", "RS End Date", "Deduction Calendar No", "Evaluation Rule", "Evaluation Rule Bundle", "Calculation Rule", "Calculation Rule Bundle", "Attached Date"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final Object[] REBATE_SETUP_REBATE_PLAN = new Object[]{
        "checkbox", "recordType", "itemNo", "itemName", "attachedStatus", "rebateStartDate", "rebateEndDate", "bundleNo", "rebatePlanNo", "rebatePlanName", "netSalesFormulaNo", "netSalesRule", "evaluationRule", "evaluationRuleBundle", "calculationRule", "calculationRuleBundle", "attachedDate"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final String[] REBATE_SETUP_REBATE_PLAN_HEADER = new String[]{
        "", "Item No", "Item Name", "RS Status", "RS Start Date", "RS End Date", "Bundle No", "Rebate Plan No", "Rebate Plan Name", "Net Sales Formula", "Attached Date"};

    /**
     * The Constant NEP FORMULA LOOKUP COLUMNS IN PS.
     */
    public static final Object[] NEP_FORMULA_LOOKUP = new Object[]{
        "nepFormulaType", "nepFormulaID", "nepFormulaNo", "nepFormulaName", "createdBy", "createdDate", "modifiedBy", "modifiedDate"};

    /**
     * The Constant NEP FORMULA LOOKUP HEADER IN PS.
     */
    public static final String[] NEP_FORMULA_LOOKUP_HEADER = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name", "Created By", "Created Date", "Modified By", "Modified Date"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final Object[] FORMULA_LOOKUP = new Object[]{
        "formulaType", "formulaID", "formulaNo", "formulaName", "version"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final String[] FORMULA_LOOKUP_HEADER = new String[]{
        "Formula Type", "Formula ID", "Formula No", "Formula Name", "Version"};

    /**
     * The Constant REBATE_PLAN_TYPE.
     */
    public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";

    public static final String PERCENCTAGE = "%";

    public static final Object[] NET_SALES_LOOKUP = new Object[]{
        "netSalesFormulaType", "netSalesFormulaId", "netSalesFormulaNo", "netSalesFormulaName", "nsfcreatedDate", "nsfcreatedBy", "nsfmodifiedDate", "nsfmodifiedBy"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final String[] NET_SALES_LOOKUP_HEADER = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name", "Creation Date", "Created By", "Modified Date", "Modified By"};

    /**
     * The quote.
     */
    public final static String QUOTE = "\"";

    public static final Object[] REBATE_PLAN_LOOKUP = new Object[]{
        "rebatePlanId", "rebatePlanNo", "rebatePlanName", "rebatePlanStatus", "rebatePlanType", "rebateStructure", "rangeBasedOn", "netSalesFormula", "netSalesRule", "rebateBasedOn", "createdDate", "createdBy", "modifiedDate", "modifiedBy"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public static final String[] REBATE_PLAN_LOOKUP_HEADER = new String[]{
        "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name", "Rebate Plan Status", "Rebate Plan Type", "Rebate Structure", "Range Based On", "Net Sales Formula", "Net Sales Rule", "Rebate Based On", "Created Date", "Created By", "Modified Date", "Modified By"};

   public static final Object[] IFP_ITEMS_COL = new Object[]{"ifpId","ifpNo","ifpName", "ifpStatus","ifpStartDate","ifpEndDate","ifpDesignation","parentIfpId","parentIfpName","ifpType","createdBy","createdDate","ifpCategory"};
    /**
     * The Constant PS_SEARCH_TABLE.
     */
    public static final Object[] PS_SEARCH_TABLE = new Object[]{"psSystemId",
        "priceScheduleId", "priceScheduleNo", "priceScheduleName",
        "priceScheduleType", "priceScheduleStatus",
        "priceScheduleCategory", "priceScheduleStartDate",
        "priceScheduleEndDate", "priceScheduleDesignation", "parentId", "parentName", "tradeClass"};

    /**
     * The Constant PS_COL_HEADERS.
     */
    public static final String[] PS_COL_HEADERS = new String[]{"System ID",
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name",
        "Price Schedule Type", "Price Schedule Status",
        "Price Schedule Category", "Start Date",
        "End Date", "Price Schedule Designation", "Parent ID", "Parent Name",
        "Trade Class"};
    public static final Object[] IFP_ITEM_DETAILS_COL = new Object[]{"checkbox","recordType" ,Constants.ITEM_NO, Constants.ITEM_NAME, "itemDesc", "ifpStatus",START_DATE, END_DATE, "itemsStatus",
        "form", "strength", "therapyClass", "brand" ,Constants.ATTACHED_DATE, "modifiedDate","modifiedBy","createdDate","createdBy"};

    public static final String[] IFP_ITEMS_HEADER = new String[]{"IFP ID", "IFP NO", "IFP Name", "IFP Status","IFP Start Date","IFP End Date","IFP Designation","Parent IFP ID","Parent IFP Name","IFP Type","Created By","Created Date","IFP Category"};
    public static final String[] IFP_ITEM_DETAILS_COL_HEADER = new String[]{"", "Record Type", "Item No", "Item Name", "Item Desc","IFP Status", "IFP Start Date", "IFP End Date",
        "Item Status", "Form", "Strength", "Therapy Class", "Brand",Constants.ATTACHEDDATE,"Modified Date","Modified By","Created Date","Created By"};
    final static Map<String, String> rebatePlanLookupColumnMap = new ConcurrentHashMap<>();
    final static Map<String, String> columnMap = new ConcurrentHashMap<>();
    public final static String SHOW_ALL = "Show All";
    public static final String FIELD_PRICE_TYPE = "Price Type";
    public static final String DELIMITER = "~~";
   /** The Constant IFP_SEARCH_TABLE. */
public static final Object[] IFP_SEARCH_LOOKUP = new Object[] {
		"itemFamilyplanSystemId","itemFamilyplanId", "itemFamilyplanNo", "itemFamilyplanName",
		"ifpType", "ifPStatus", "ifpCategory", "startDate", "endDate","ifpDesignation","parentItemFamilyplanId",
                "parentItemFamilyplanName",
		"createdBy","createdDate"};
/** The Constant IFP_COL_HEADERS. */
public static final String[] IFP_LOOKUP_HEADERS = new String[] {
	"System ID","IFP ID", "IFP No", "IFP Name",
	"IFP Type","IFP Status","IFP Category", "IFP Start Date", "IFP End Date","IFP Designation","Parent ID",
        "Parent Name",
        "Created By","Created Date"};
    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public DashboardDAO getDao() {
        return dao;
    }

    /**
     * Gets the native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }
        return select;
    }

    /**
     * Gets the price type native.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the price type native
     */
    public NativeSelect getPriceTypeNative(final NativeSelect select, final List<HelperDTO> helperList) {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(String.valueOf(helperDTO.getId()));
            select.setItemCaption(String.valueOf(helperDTO.getId()), helperDTO.getDescription());
        }
        return select;
    }

    /**
     * Search fields.
     *
     * @return the container
     */
    public Container searchFields(boolean isIFP) {
        LOGGER.debug("Entering searchFields method");
        final List<String> list = new ArrayList<String>();
        list.add(Constants.SELECT_ONE);
        if (isIFP) {
            list.add("IFP No");
            list.add("IFP Name");
        } else{
            list.add("Brand Name");
            list.add("Form");
            list.add("Item Description");
            list.add("Item Name");
            list.add("Item No");
            list.add("NDC 8");
            list.add("NDC 9");
            list.add("Strength");
            list.add("Therapeutic Class");
        }
        
        LOGGER.debug("End of searchFields method");
        return new IndexedContainer(list);
    }

    /**
     * Gets the selet null.
     *
     * @param select the select
     * @return the selet null
     */
    public static NativeSelect getSeletNull(final NativeSelect select) {
        LOGGER.debug("Entering getSeletNull method");
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.SELECT_ONE);
        LOGGER.debug("End of getSeletNull method");
        return select;
    }

    /**
     * Gets the native select for identifier.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select for identifier
     */
    public NativeSelect getNativeSelectForIdentifier(final NativeSelect select, final List<HelperDTO> helperList) {
        LOGGER.debug("Entering getNativeSelectForIdentifier method");
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }

        select.addItem("EditList");
        LOGGER.debug("End of getNativeSelectForIdentifier method");
        return select;
    }

    /**
     * Gets the item status select.
     *
     * @param select the select
     * @return the item status select
     */
    public NativeSelect getItemStatusSelect(final NativeSelect select) throws SystemException {
        LOGGER.debug("Entering getItemStatusSelect method");
        final ItemSearchLogic itemLogic = new ItemSearchLogic();
        final List<HelperDTO> stsLst = itemLogic.getItemType(UIUtil.ITEM_STATUS);
        for (int i = 0; i < stsLst.size(); i++) {
            final HelperDTO helper = (HelperDTO) stsLst.get(i);
            select.addItem(helper.getDescription());
        }
        LOGGER.debug("End of getItemStatusSelect method");
        return select;
    }

    /**
     * Gets the item status select.
     *
     * @param select the select
     * @return the item status select
     */
    public ComboBox getItemStatusSelect(final ComboBox select) throws SystemException {
        LOGGER.debug("Entering getItemStatusSelect method");
        final ItemSearchLogic itemLogic = new ItemSearchLogic();
        final List<HelperDTO> stsLst = itemLogic.getItemType(UIUtil.ITEM_STATUS);
        for (int i = 0; i < stsLst.size(); i++) {
            final HelperDTO helper = (HelperDTO) stsLst.get(i);
            select.addItem(helper.getDescription());
        }
        LOGGER.debug("End of getItemStatusSelect method");
        return select;
    }

    /**
     * to get Price Type
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getPriceType() throws SystemException {
        LOGGER.debug("Entering getPriceType method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery itemPricingDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        final List<ItemPricingQualifier> list = dao.itemPricingDynamicQuery(itemPricingDynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if(i==0){
                    helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
                }
                helperList.add(new HelperDTO(list.get(i).getItemPricingQualifierSid(), String.valueOf(list.get(i).getItemPricingQualifierName())));
            }
        }
        LOGGER.debug("End of getPriceType method with helperList SIZE=" + helperList.size());
        return helperList;
    }

    /**
     * to get Price Type
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getFilterPriceType() throws SystemException {
        LOGGER.debug("Entering getPriceType method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery itemPricingDynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class);
        final List<ItemPricingQualifier> list = dao.itemPricingDynamicQuery(itemPricingDynamicQuery);
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {

                helperList.add(new HelperDTO(list.get(i).getItemPricingQualifierSid(), String.valueOf(list.get(i).getItemPricingQualifierName())));
            }
        }
        LOGGER.debug("End of getPriceType method with helperList SIZE=" + helperList.size());
        return helperList;
    }
    /**
     * Returns the list of String from the HelperTable based on the given list
     * type.
     *
     * @param listType -
     * @return List of String.
     */
    public List<String> getHelperDetailsDesc(final String listType) {
        LOGGER.debug("Entering getHelperDetails()");
        final List<String> returnList = new ArrayList<String>();
        try {
            final List<HelperTable> list = dao.findByHelperTableDetails(listType);

            returnList.add(Constants.SELECT_ONE);
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    final HelperTable helperTable = (HelperTable) list.get(i);
                    returnList.add(helperTable.getDescription());
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.debug("End of getHelperDetails() with returnList size=" + returnList.size());
        return returnList;
    }
    /**
     * Gets the price tolerance type.
     *
     * @return the price tolerance type
     */
    public List<HelperDTO> getPriceToleranceType() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceType method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_TYPE);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.debug("End of getPriceToleranceType method");
        return helperList;
    }
    public List<HelperDTO> getFilterPriceToleranceType() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceType method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_TYPE);
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.debug("End of getPriceToleranceType method");
        return helperList;
    }
    public List<HelperDTO> getItemStatus() throws SystemException {
        LOGGER.debug("Entering getItemStatus method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails("STATUS");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }
        LOGGER.debug("End of getItemStatus method");
        return helperList;
    }

    /**
     * to get Price Tolerence Intervel
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getPriceToleranceInterval() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceInterval method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_INTERVAL);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }

        LOGGER.debug("End of getPriceToleranceInterval method");
        return helperList;
    }

     /**
     * to get Price Tolerence Intervel
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getFilterPriceToleranceInterval() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceInterval method");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_INTERVAL);
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }
        }

        LOGGER.debug("End of getPriceToleranceInterval method");
        return helperList;
    }
    /**
     * to get Price Tolerance Frequency
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getPriceToleranceFrequency() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceFrequency method");
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_FRERQUENCY);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }

        }
        return helperList;
    }

    /**
     * to get Price Tolerance Frequency
     *
     * @return
     * @throws SystemException
     */
    public List<HelperDTO> getFilterPriceToleranceFrequency() throws SystemException {
        LOGGER.debug("Entering getPriceToleranceFrequency method");
        final List<HelperTable> list = dao.findByHelperTableDetails(PRICE_TOLERANCE_FRERQUENCY);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        helperList.add(new HelperDTO(0, Constants.SELECT_ONE));
        if (list != null) {

            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
            }

        }
        return helperList;
    }
    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public NativeSelect getStatusSelect(final NativeSelect select) {
        LOGGER.debug("Entering getStatusSelect method");
        select.addItem("Active");
        select.addItem("InActive");
        LOGGER.debug("End of getStatusSelect method");
        return select;
    }
    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public ComboBox getStatusSelect(final ComboBox select) {
        LOGGER.debug("Entering getStatusSelect method");
        select.addItem(Constants.SELECT_ONE);
        select.addItem("Active");
        select.addItem("InActive");
        LOGGER.debug("End of getStatusSelect method");
        return select;
    }
    /**
     * Gets the native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the Combo boz select
     */
    public ComboBox getComboBox(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.debug("Entering getNativeSelect method");
        final HelperDTO defaultValue = new HelperDTO(0, Constants.SELECT_ONE);
        select.setNullSelectionAllowed(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(Constants.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> helpList = new ArrayList<HelperDTO>();
        helpList.add(defaultValue);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            helpList.add(helperDTO);
        }
        resultContainer.addAll(helpList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.markAsDirty();
        LOGGER.debug("End of getNativeSelect method");
        return select;
    }
    
    /**
     * Gets the price type native.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the price type native
     */
    public ComboBox getPriceTypeNative(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.debug("Entering getPriceTypeNative method");
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(String.valueOf(helperDTO.getId()));
            select.setItemCaption(String.valueOf(helperDTO.getId()), helperDTO.getDescription());
        }
        LOGGER.debug("End of getPriceTypeNative method");
        return select;
    }
    public static ComboBox getCustomizedComboBox(ComboBox select) {
        select.select("10");
        select.removeItem("25");
        select.removeItem("50");
        select.removeItem("100");
        select.removeItem("600");
        return select;
    }

    /**
     * Convert date to string.
     *
     * @param aDate the a date
     * @return the string
     */
    public static final String convertDateToString(final Date aDate)  {
        return getDateTime(MMDDYYYY, aDate);
    }
    /**
     * Gets the date time.
     *
     * @param aMask the a mask
     * @param aDate the a date
     * @return the date time
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        if (aDate != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            return dateFormat.format(aDate);
        }
        return EMPTY;
    }
    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
           LOGGER.error(ex);
        }
        return null;
    }
    public static void loadRebatePlanLookupColumnMap() {
        rebatePlanLookupColumnMap.put("rebatePlanId", "RP.REBATE_PLAN_ID");
        rebatePlanLookupColumnMap.put("rebatePlanNo", "RP.REBATE_PLAN_NO");
        rebatePlanLookupColumnMap.put("rebatePlanName", "RP.REBATE_PLAN_NAME");
        rebatePlanLookupColumnMap.put("rebatePlanStatus", "RP.REBATE_PLAN_STATUS");
        rebatePlanLookupColumnMap.put("rebatePlanType", "RP.REBATE_PLAN_TYPE");
        rebatePlanLookupColumnMap.put("rebateStructure", "RP.REBATE_STRUCTURE");
        rebatePlanLookupColumnMap.put("rebateBasedOn", "RP.REBATE_BASED_ON");
        rebatePlanLookupColumnMap.put("rangeBasedOn", "RP.REBATE_RANGE_BASED_ON");
        rebatePlanLookupColumnMap.put("createdBy", " RP.CREATED_BY");
        rebatePlanLookupColumnMap.put("createdDate", "RP.CREATED_DATE");
        rebatePlanLookupColumnMap.put("modifiedBy", "RP.MODIFIED_BY");
        rebatePlanLookupColumnMap.put("modifiedDate", " RP.MODIFIED_DATE");
        rebatePlanLookupColumnMap.put("netSalesFormula", "NSF.NET_SALES_FORMULA_NAME");
        rebatePlanLookupColumnMap.put("netSalesRule", "CDR.RULE_NAME");
}

    public static String getRebatePlanLookupColumnMap(String key) {
        return rebatePlanLookupColumnMap.get(key);
    }

    /**
     * The Constant DEDUCTION_LOOKUP_COLUMN.
     */
    public static final Object[] DEDUCTION_LOOKUP_COLUMN = new Object[]{
        "deductionNo", "deductionName", "deductionDesc", "deductionCategory", "creationDate", "createdBy", "modifiedDate", "modifiedBy"};

    /**
     * The Constant DEDUCTION_LOOKUP_HEADER.
     */
    public static final String[] DEDUCTION_LOOKUP_HEADER = new String[]{
        "Deduction Calendar No", "Deduction Calendar Name", "Deduction Calendar Desc", "Category", "Creation Date", "Created By", "Modified Date", "Modified By"};

    public static final String REBATE_AMOUNT = "Rebate Amount";

    /**
     * The Constant itemRebateStartDate.
     */
    public static final String ITEM_REBATE_START_DATE = "Start Date";

    /**
     * The Constant itemRebateEndDate.
     */
    public static final String ITEM_REBATE_END_DATE = "End Date";

    /**
     * Returns true if the object is null or string object is empty.
     *
     * @param object
     * @return
     */
    public static boolean checkNullValues(Object object) {
        boolean isNullOrEmpty;
        if (object instanceof String) {
            String checkString = (String) object;
            isNullOrEmpty = StringUtils.isBlank(checkString) || ConstantsUtils.NULL.equals(checkString);
        } else {
            isNullOrEmpty = object == null;
        }
        return isNullOrEmpty;
    }

}
