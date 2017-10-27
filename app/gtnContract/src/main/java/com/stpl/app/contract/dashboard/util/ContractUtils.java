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
    public final Date CURRENT_DATE = new Date();
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
    public static final String INBOUND_STATUS = "inboundStatus";

    /**
     * Inbound Status A
     */
    public static final String INBOUND_STATUS_A = "A";

    /**
     * Inbound Status D
     */
    public static final String INBOUND_STATUS_D = "D";
    /**
     * Inbound Status C
     */
    public static final String INBOUND_STATUS_C = "C";

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
    public final String[] ifpColHeader = new String[]{"item Familyplan Id", "item Familyplan No", "item Familyplan Name", "item Familyplan Status", "item Familyplan Type",
        "item Familyplan Category", "item Familyplan Start Date", "item Familyplan End Date", "item Familyplan Designation", "total Dollar Commitment", "commitment Period",
        "total Volume Commitment", "total Market share Commitment"};
    /**
     * The Constant IFP_SEARCH_TABLE.
     */
    public final Object[] ifpSearchTable = new Object[]{"itemFamilyplanId", "itemFamilyplanNo", "itemFamilyplanName", "itemFamilyplanStatus", "itemFamilyplanType",
        "itemFamilyplanCategory", "itemFamilyplanStartDate", "itemFamilyplanEndDate", "itemFamilyplanDesignation", "totalDollarCommitment", "commitmentPeriod", "totalVolumeCommitment",
        "totalMarketshareCommitment"};
    public static final String BRAND = "brand";
    public static final String STRENGTH = "strength";
    public static final String ITEM_DESC_PROPERTY = "itemDesc";
    /**
     * The Constant AVAILABLE_ITEM_COL.
     */
    public final Object[] availableItemCol = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, ITEM_DESC_PROPERTY, "itemStatus", "form", STRENGTH, "therapeuticClass", BRAND};
    public static final String ITEM_STATUS_LABEL = "Item Status";
    public static final String ITEM_DESC_LABEL = "Item Desc";
    public static final String ITEM_NO_LABEL = "Item No";
    public static final String BRAND_LABEL = "Brand";
    public static final String ITEM_NAME_LABEL = "Item Name";
    public static final String THERAPEUTIC_CLASS = "Therapeutic Class";
    public static final String STRENGTH_LABEL = "Strength";
    /**
     * The Constant AVAILABLE_ITEM_COL_HEADER.
     */
    public final String[] availableItemColHeader = new String[]{ITEM_NO_LABEL, ITEM_NAME_LABEL, ITEM_DESC_LABEL, ITEM_STATUS_LABEL, "Form", STRENGTH_LABEL, THERAPEUTIC_CLASS, BRAND_LABEL};
    /**
     * The Constant SELECTED_ITEM_COL.
     */
    public final Object[] selectedItemCol = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, ITEM_DESC_PROPERTY, "itemStatus", "form", STRENGTH, "therapeuticClass", BRAND};
    /**
     * The Constant SELECTED_ITEM_COL_HEADER.
     */
    public final String[] selectedItemColHeader = new String[]{ITEM_NO_LABEL, ITEM_NAME_LABEL, ITEM_DESC_LABEL, ITEM_STATUS_LABEL, "Form", STRENGTH_LABEL, THERAPEUTIC_CLASS, BRAND_LABEL};
    public static final String RECORD_TYPE = "recordType";
    /**
     * The Constant ITEM_DETAILS_COL.
     */

    public final Object[] itemDetailsCol = new Object[]{ConstantsUtils.CHECK_BOX, RECORD_TYPE, "itemId", Constants.ITEM_NO, Constants.ITEM_NAME, BRAND, "globalitemstatus", "cpStartDate", "cpEndDate", Constants.PRICE_TYPE, Constants.PRICE,
         "suggestedPrice","source",ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE, Constants.ATTACHED_DATE};
    
    public static final String PRICE_TOLERANCE_FREQUENCY_LABEL = "Price Tolerance Frequency";
    public static final String PRICE_TOLERANCE_INTERVAL_LABEL = "Price Tolerance Interval";
    public static final String END_DATE_LABEL = "End Date";
    public static final String START_DATE_LABEL = "Start Date";
    public static final String BASE_PRICE = "Base Price";
    public static final String REVISION_DATE = "Revision Date";
    public static final String PRICE_TOLERANCE_TYPE_LABEL = "Price Tolerance Type";
    public static final String COMPANY_GROUP = "companyGroup";
    public static final String PRICE_TYPE_LABEL = "Price Type";
    public static final String PRICE_TOLERANCE_LABEL = "Price Tolerance";
    public static final String ITEM_ID = "Item ID";
    public static final String PRICE_PROTECTION_END_DATE = "Price Protection End Date";
    public static final String PRICE_PROTECTION_START_DATE = "Price Protection Start Date";

    /**
     * The Constant ITEM_DETAILS_COL_HEADER.
     */
    public final String[] itemDetailsColHeader = new String[]{"", ITEM_NO_LABEL, ITEM_NAME_LABEL, ITEM_ID, "UOM", "Package Size", START_DATE_LABEL, END_DATE_LABEL, "Price", PRICE_TYPE_LABEL, "CP Start Date", "CP End Date", "Item status", PRICE_TOLERANCE_LABEL, PRICE_PROTECTION_START_DATE, PRICE_PROTECTION_END_DATE, PRICE_TOLERANCE_TYPE_LABEL, PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, BASE_PRICE, REVISION_DATE, "Attached Status", Constants.ATTACHEDDATE};
    public static final String COMPANY_CATEGORY_PROPERTY = "companyCategory";
    
    /**
     * The Constant AVAILABLE_COMPANY_COL.
     */
    public final Object[] availableCompanyCol = new Object[]{Constants.COMPANY_ID, Constants.COMPANY_NO, Constants.COMPANY_NAME, "displayCompanyStatus", "displayCompanyType",  Constants.TRADE_CLASS, COMPANY_CATEGORY_PROPERTY, COMPANY_GROUP,};
    public static final String COMPANY_NO_LABEL = "Company No";
    public static final String COMPANY_CATEGORY_LABEL = "Company Category";
    public static final String COMPANY_NAME_LABEL = "Company Name";
    public static final String COMPANY_TYPE_LABEL = "Company Type";
    public static final String COMPANY_STATUS_LABEL = "Company Status";
    /**
     * The Constant AVAILABLE_COMPANY_COL_HEADER.
     */
    public final String[] availableCompanyColHeader = new String[]{COMPANY_NO_LABEL, COMPANY_NAME_LABEL, COMPANY_TYPE_LABEL, COMPANY_STATUS_LABEL,Constants.COMPANY_ID,Constants.TRADE_CLASS, COMPANY_CATEGORY_LABEL,"Company Group",};
    public static final String COMPANY_TYPE_PROPERTY = "companyType";
    /**
     * The Constant SELECTED_COMPANY_COL.
     */
    public final Object[] selectedCompanyCoil = new Object[]{Constants.COMPANY_ID, Constants.COMPANY_NO, Constants.COMPANY_NAME, Constants.COMPANY_STATUS, COMPANY_TYPE_PROPERTY,  Constants.TRADE_CLASS, COMPANY_CATEGORY_PROPERTY, COMPANY_GROUP};
    /**
     * The Constant SELECTED_COMPANY_COL_HEADER.
     */
    public final String[] selectedCompanyColHeader = new String[]{COMPANY_NO_LABEL, COMPANY_NAME_LABEL, COMPANY_TYPE_LABEL, COMPANY_STATUS_LABEL,Constants.COMPANY_ID,Constants.TRADE_CLASS, COMPANY_CATEGORY_PROPERTY, COMPANY_GROUP};
    public static final String MODIFIED_DATE = "modifiedDate";
    /**
     * The Constant CFP_ITEM_DETAILS_COL.
     */
    public final Object[] cfpItemDetailsCol = new Object[]{ConstantsUtils.CHECK_BOX, RECORD_TYPE, "companyId", Constants.COMPANY_NO, Constants.COMPANY_NAME, "companyFamilyPlanStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", Constants.COMPANY_STATUS, COMPANY_TYPE_PROPERTY, "cfpDetailsTradeClass", COMPANY_CATEGORY_PROPERTY, 
       "tradingPartnerContractNo", Constants.ATTACHED_DATE, MODIFIED_DATE, "cfpDetailsModifiedBy", ConstantsUtils.CREATEDDATE, ConstantsUtils.CREATEDBY};
    public static final String MODIFIED_BY_LABEL = "Modified By";
    public static final String MODIFIED_DATE_LABEL = "Modified Date";
    /**
     * The Constant CFP_ITEM_DETAILS_COL_HEADER.
     */
    public final String[] cfpItemDetailsColHeader = new String[]{"", "Trading Partner ID", COMPANY_NO_LABEL, COMPANY_NAME_LABEL, "Company Family Plan Status", "Company Family Plan Start Date", "Company Family Plan End Date", COMPANY_STATUS_LABEL, COMPANY_TYPE_LABEL, COMPANY_CATEGORY_LABEL, "Company TradeClass", "Company Start Date", "Company End Date",
        "Trading Partner Contract No", Constants.ATTACHEDDATE, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL};
    /**
     * The Constant CFP_ITEM_DETAILS_VIEW_COL.
     */
    public final Object[] cfpItemDetailsViewCol = new Object[]{"companyId", Constants.COMPANY_NO, Constants.COMPANY_NAME, "companyFamilyPlanStatus", "companyFamilyPlanStartDate", "companyFamilyPlanEndDate", Constants.COMPANY_STATUS, COMPANY_TYPE_PROPERTY, COMPANY_CATEGORY_PROPERTY, "cfpDetailsTradeClass", "companyStartDate",
        "companyEndDate", "tradingPartnerContractNo", Constants.ATTACHED_DATE, MODIFIED_DATE, "cfpDetailsModifiedBy"};
    /**
     * The Constant CFP_ITEM_DETAILS_VIEW_COL_HEADER.
     */
    public final String[] cfpItemDetailsViewColHeader = new String[]{"Trading Partner ID", COMPANY_NO_LABEL, COMPANY_NAME_LABEL, "Company Family Plan Status", "Company Family Plan Start Date", "Company Family Plan End Date", COMPANY_STATUS_LABEL, COMPANY_TYPE_LABEL, COMPANY_CATEGORY_LABEL, "Company TradeClass", "Company Start Date", "Company End Date",
        "Trading Partner Contract No", Constants.ATTACHEDDATE, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL};
    /**
     * The Constant ITEM_DETAILS_VEIW_COL.
     */
    public final Object[] itemDetailsViewCol = new Object[]{"itemId", RECORD_TYPE, Constants.ITEM_NO, Constants.ITEM_NAME, BRAND,  "globalitemstatus", "cpStartDate", "cpEndDate", Constants.PRICE_TYPE, Constants.PRICE,
           "suggestedPrice", "source", ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE, Constants.ATTACHED_DATE};
    
    /**
     * The Constant ITEM_DETAILS_VIEW_COL_HEADER.
     */
    public final String[] itemDetailsViewColHeader = new String[]{ITEM_NO_LABEL, ITEM_NAME_LABEL, "Item Id", "Uom", "Package Size", START_DATE_LABEL, END_DATE_LABEL, "Price", PRICE_TYPE_LABEL, "CP Start Date", "CP End Date", ITEM_STATUS_LABEL, PRICE_TOLERANCE_LABEL, PRICE_PROTECTION_START_DATE, PRICE_PROTECTION_END_DATE, PRICE_TOLERANCE_TYPE_LABEL, PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, BASE_PRICE, REVISION_DATE, "Attached Status", Constants.ATTACHEDDATE};
    
    public static final String REBATE_PLAN_NAME = "rebatePlanName";
    public static final String FORMULA_NAME = "formulaName";
    public static final String REBATE_END_DATE = "rebateEndDate";
    public static final String REBATE_START_DATE = "rebateStartDate";
    /**
     * The Constant ITEM_DETAILS_VIEW_COLUMNS_IN_RS.
     */
    public final Object[] itemDetailsViewColumnsInRs = new Object[]{RECORD_TYPE,Constants.ITEM_NO, Constants.ITEM_NAME, "itemType", "formulaId", FORMULA_NAME, REBATE_PLAN_NAME, REBATE_START_DATE, REBATE_END_DATE, "rebateRevisionDate"};
    public static final String FORMUL_NAME_LABEL = "Formula Name";
    public static final String REBATE_PLAN_NAME_LABEL = "Rebate Plan Name";
    public static final String FORMULA_ID_LABEL = "Formula ID";
    /**
     * The Constant ITEM_DETAILS_VIEW_HEADER_IN_RS.
     */
    public final String[] itemDetailsViewHeadersinRs = new String[]{ITEM_NO_LABEL, ITEM_NAME_LABEL, "Item Type", FORMULA_ID_LABEL, FORMUL_NAME_LABEL, REBATE_PLAN_NAME_LABEL, START_DATE_LABEL, END_DATE_LABEL, REVISION_DATE};

    /**
     * The Constant ITEM_DETAILS_VIEW_COLUMNS_IN_RS.
     */
    public final Object[] itemDetailsViewColumnsInRsWithBundle = new Object[]{Constants.ITEM_NO, Constants.ITEM_NAME, "itemType", "formulaId", FORMULA_NAME, "bundleNo", REBATE_PLAN_NAME, REBATE_START_DATE, REBATE_END_DATE, "rebateRevisionDate"};
    /**
     * The Constant ITEM_DETAILS_VIEW_HEADER_IN_RS.
     */
    public final String[] itemDetailsViewHeaderInRsWithBundle = new String[]{ITEM_NO_LABEL, ITEM_NAME_LABEL, "Item Type", FORMULA_ID_LABEL, FORMUL_NAME_LABEL, "Bundle No", REBATE_PLAN_NAME_LABEL, START_DATE_LABEL, END_DATE_LABEL, REVISION_DATE};

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
    public static final String ATTACHED_DATE = "attachedDate";
    public static final String ITEM_NAME = "itemName";
    public static final String ITEM_NO = "itemNo";
    
    /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public final Object[] priceProtectionCol = new Object[]{ConstantsUtils.CHECK_BOX, RECORD_TYPE,
        "itemID", ITEM_NO, ITEM_NAME, BRAND, "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType",
        "nep", "nepFormula", "basePriceType", "basePriceValue",
        "netBasePrice", "netBasePriceFormula", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormula",
        "ppPriceToleranceInterval", "ppPriceToleranceFrequency", "ppPriceToleranceType",
        "priceTolerance", "maxIncrementalChange", "resetEligible", "resetType",
        "resetDate", "resetInterval", "resetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceFormula",
        "netPriceType", "netPriceTypeFormula", ATTACHED_DATE};
    public static final String RECORD_TYPE_LABEL = "Record Type";
    public static final String ATTACHED_DATE_LABEL = "Attached Date";

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public final String[] priceProtectionColHeader = new String[]{
        "", RECORD_TYPE_LABEL, ITEM_ID, ITEM_NO_LABEL, ITEM_NAME_LABEL, BRAND_LABEL, "Price Protection Status", PRICE_PROTECTION_START_DATE, PRICE_PROTECTION_END_DATE, "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type", BASE_PRICE, "Net Base Price",
        "Net Base Price Formula", "Subsequent Period Price Type", "Net Subsequent Period Price", "Net Subsequent Period Price Formula", PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_TYPE_LABEL, PRICE_TOLERANCE_LABEL, "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula", ATTACHED_DATE_LABEL};

    /**
     * The Constant PRICE_PROTECTION_COL.
     */
    public final Object[] priceProtectionColView = new Object[]{RECORD_TYPE,
        "itemID", ITEM_NO, ITEM_NAME, BRAND, "priceProtectionStatus",
        "priceProtectionStartDate", "priceProtectionEndDate", "priceProtectionPriceType",
        "nep", "nepFormula", "basePriceType", "basePriceValue",
        "netBasePrice", "netBasePriceFormula", "subsequentPeriodPriceType", "netSubsequentPeriodPrice", "netSubsequentPriceFormula",
        "ppPriceToleranceInterval", "ppPriceToleranceFrequency", "ppPriceToleranceType",
        "priceTolerance", "maxIncrementalChange", "resetEligible", "resetType",
        "resetDate", "resetInterval", "resetFrequency", "resetPriceType", "netResetPriceType", "netResetPriceFormula",
        "netPriceType", "netPriceTypeFormula", ATTACHED_DATE};

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public final String[] priceProtectionColHeaderView = new String[]{
        RECORD_TYPE_LABEL, ITEM_ID, ITEM_NO_LABEL, ITEM_NAME_LABEL, BRAND_LABEL, "Price Protection Status", PRICE_PROTECTION_START_DATE, PRICE_PROTECTION_END_DATE, "Price Protection Price Type",
        "NEP", "NEP Formula", "Base Price Type", BASE_PRICE, "Net Base Price",
        "Net Base Price Formula", "Subsequent Period Price Type", "Net Subsequent Period Price", "Net Subsequent Period Price Formula", PRICE_TOLERANCE_INTERVAL_LABEL, PRICE_TOLERANCE_FREQUENCY_LABEL, PRICE_TOLERANCE_TYPE_LABEL, PRICE_TOLERANCE_LABEL, "Max Incremental Change", "Reset Eligible", "Reset Type",
        "Reset Date", "Reset Interval", "Reset Frequency", "Reset Price Type", "Net Reset Price Type", "Net Reset Price Formula",
        "Net Price Type", "Net Price Type Formula", ATTACHED_DATE_LABEL};
    
    public final Object[] priceProtectionHistory = new Object[]{"priceScheduleId",
        "priceScheduleNo", "priceScheduleName", "priceScheduleStatus", "priceScheduleStartDate", "priceScheduleEndDate",
        "priceScheduleDesignation", "parentPriceScheduleId", "parentPriceScheduleName",
        "priceScheduleType", ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE, "priceScheduleCategory",
        ConstantsUtils.MODIFIEDBY, MODIFIED_DATE, "priceScheduleTradeClass"};
    public static final String CREATED_BY_LABEL = "Created By";

    /**
     * The Constant PRICE_PROTECTION_COL_HEADER.
     */
    public final String[] priceProtectionHistoryHeader = new String[]{
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name", "Price Schedule Status", "Price Schedule Start Date", "Price Schedule End Date",
        "Price Schedule Designation", "Parent Price Schedule ID", "Parent Price Schedule Name",
        "Price Schedule Type", CREATED_BY_LABEL, ConstantsUtils.CREATED_DATE1, "Price Schedule Category", MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL, "Price Schedule  Trade Class"};
    public final Object[] nsrColumn = new Object[]{"ruleType", "ruleNo", "ruleName", "ruleCategoryString"};

    public final String[] nsrHeader = new String[]{"Rule Type", "Rule No", "Rule Name", "Rule Category"};

    public final Object[] nsrDetailsColumn = new Object[]{"lineType", "association", "keyword", "operator", "value", "comparison", "logicalOperator"};

    public final String[] nsrDetailsHeader = new String[]{"Line Type", "Item/Group/Association", "Keyword", "Operator", "Value", "Comparison", "Operator"};

    public static final String CALC_FORMULA = "Formula";
    public static final String CALC_REBATE_PLAN = "Rebate Plan";
    public static final String CALC_DEDUCTION_CALENDAR = "Deduction Calendar";
    public static final String NET_SALES_FORMULA = "Net Sales Formula";
    public static final String NET_SALES_RULE = "Net Sales Rule";
    public static final String EVALUATION_RULE = "Evaluation Rule";
    public static final String CALCULATION_RULE = "Calculation Rule";
    public static final String EVALUATION_RULE_BUNDLE = "Evaluation Rule Bundle";
    public static final String CALCULATION_RULE_BUNDLE = "Calculation Rule Bundle";
    public static final String FORMULA_TYPE = "Formula Type";
    public static final String IFP_STATUS = "IFP Status";
    public static final String ATTACHED_STATUS_PROPERTY = "attachedStatus";
    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] rebateSetupDefault = new Object[]{
        ConstantsUtils.CHECK_BOX, RECORD_TYPE, ITEM_NO, ITEM_NAME, ATTACHED_STATUS_PROPERTY, REBATE_START_DATE, REBATE_END_DATE};
    public static final String RS_START_DATE = "RS Start Date";
    public static final String RS_END_DATE = "RS End Date";
    public static final String RS_STATUS_LABEL = "RS Status";

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public final String[] rebateSetupDefaultHeader = new String[]{
        "", ITEM_NO_LABEL, ITEM_NAME_LABEL, RS_STATUS_LABEL, RS_START_DATE, RS_END_DATE};
    public static final String CALCULATION_RULE_PROPERTY = "calculationRule";
    public static final String EVALUATION_RULE_BUNDLE_PROPERTY = "evaluationRuleBundle";
    public static final String EVALUATION_RULE_PROPERTY = "evaluationRule";
    public static final String CALCULATION_RULE_BUNDLE_PROPERTY = "calculationRuleBundle";
    public static final String NET_SALES_RULE_PROPERTY = "netSalesRule";
    public static final String NET_SALES_FORMULA_NO = "netSalesFormulaNo";

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] rebateSetupFormula = new Object[]{
        ConstantsUtils.CHECK_BOX, RECORD_TYPE, ITEM_NO, ITEM_NAME, ATTACHED_STATUS_PROPERTY, REBATE_START_DATE, REBATE_END_DATE, "formulaType", "formulaNo", FORMULA_NAME, NET_SALES_FORMULA_NO, NET_SALES_RULE_PROPERTY, EVALUATION_RULE_PROPERTY, EVALUATION_RULE_BUNDLE_PROPERTY, CALCULATION_RULE_PROPERTY, CALCULATION_RULE_BUNDLE_PROPERTY, ATTACHED_DATE};

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public final String[] reabteSetupFormulaHeader = new String[]{
        "", ITEM_NO_LABEL, ITEM_NAME_LABEL, RS_STATUS_LABEL, RS_START_DATE, RS_END_DATE, FORMULA_TYPE, "Formula No", FORMUL_NAME_LABEL, NET_SALES_FORMULA, NET_SALES_RULE, EVALUATION_RULE, EVALUATION_RULE_BUNDLE, CALCULATION_RULE, CALCULATION_RULE_BUNDLE, ATTACHED_DATE_LABEL};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] rebateSetupDeductionCalender = new Object[]{
        ConstantsUtils.CHECK_BOX, RECORD_TYPE, ITEM_NO, ITEM_NAME, ATTACHED_STATUS_PROPERTY, REBATE_START_DATE, REBATE_END_DATE, "deductionCalendarNo","deductionCalendarName", EVALUATION_RULE_PROPERTY, EVALUATION_RULE_BUNDLE_PROPERTY, CALCULATION_RULE_PROPERTY, CALCULATION_RULE_BUNDLE_PROPERTY, ATTACHED_DATE};

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS.
     */
    public final String[] rebateSetupDeductionCalendarHeader = new String[]{
        "", ITEM_NO_LABEL, ITEM_NAME_LABEL, RS_STATUS_LABEL, RS_START_DATE, RS_END_DATE, "Deduction Calendar No", "Deduction Calendar Name",EVALUATION_RULE, EVALUATION_RULE_BUNDLE, CALCULATION_RULE, CALCULATION_RULE_BUNDLE, ATTACHED_DATE_LABEL};
    public static final String REBATE_PLAN_NO_PROPERTY = "rebatePlanNo";

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] rebateSetupRebatePlan = new Object[]{
        ConstantsUtils.CHECK_BOX, RECORD_TYPE, ITEM_NO, ITEM_NAME, ATTACHED_STATUS_PROPERTY, REBATE_START_DATE, REBATE_END_DATE, "bundleNo", REBATE_PLAN_NO_PROPERTY, REBATE_PLAN_NAME, NET_SALES_FORMULA_NO, NET_SALES_RULE_PROPERTY, EVALUATION_RULE_PROPERTY, EVALUATION_RULE_BUNDLE_PROPERTY, CALCULATION_RULE_PROPERTY, CALCULATION_RULE_BUNDLE_PROPERTY, ATTACHED_DATE};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final String[] rebateSetupRebatePlanHeader = new String[]{
        "", ITEM_NO_LABEL, ITEM_NAME_LABEL, RS_STATUS_LABEL, RS_START_DATE, RS_END_DATE, "Bundle No", "Rebate Plan No", REBATE_PLAN_NAME_LABEL, NET_SALES_FORMULA, ATTACHED_DATE_LABEL};

    /**
     * The Constant NEP FORMULA LOOKUP COLUMNS IN PS.
     */
    public final Object[] nepFormulaLookup = new Object[]{
        "nepFormulaType", "nepFormulaID", "nepFormulaNo", "nepFormulaName", ConstantsUtils.CREATEDBY, ConstantsUtils.CREATEDDATE, ConstantsUtils.MODIFIEDBY, MODIFIED_DATE};

    /**
     * The Constant NEP FORMULA LOOKUP HEADER IN PS.
     */
    public final String[] nepFormulaLookupHeader = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name", CREATED_BY_LABEL, ConstantsUtils.CREATED_DATE1, MODIFIED_BY_LABEL, MODIFIED_DATE_LABEL};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] formulaLookup = new Object[]{
        "formulaType", "formulaID", "formulaNo", FORMULA_NAME, "version"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final String[] formulaLookupHeader = new String[]{
        FORMULA_TYPE, FORMULA_ID_LABEL, "Formula No", FORMUL_NAME_LABEL, "Version"};

    /**
     * The Constant REBATE_PLAN_TYPE.
     */
    public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";

    public static final String PERCENCTAGE = "%";

    public final Object[] netSalesLookup = new Object[]{
        "netSalesFormulaType", "netSalesFormulaId", NET_SALES_FORMULA_NO, "netSalesFormulaName", "nsfcreatedDate", "nsfcreatedBy", "nsfmodifiedDate", "nsfmodifiedBy"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final String[] netSalesLookupHeader = new String[]{
        "Net Sales Formula Type", "Net Sales Formula ID", "Net Sales Formula No", "Net Sales Formula Name", "Creation Date", CREATED_BY_LABEL, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL};

    /**
     * The quote.
     */
    public static final String QUOTE = "\"";

    public final Object[] rebatePlanLookup = new Object[]{
        "rebatePlanId", REBATE_PLAN_NO_PROPERTY, REBATE_PLAN_NAME, "rebatePlanStatus", "rebatePlanType", "rebateStructure", "rangeBasedOn", "netSalesFormula", NET_SALES_RULE_PROPERTY, "rebateBasedOn", ConstantsUtils.CREATEDDATE, ConstantsUtils.CREATEDBY, MODIFIED_DATE, ConstantsUtils.MODIFIEDBY};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final String[] rebatePlanLookupHeader = new String[]{
        "Rebate Plan ID", "Rebate Plan No", REBATE_PLAN_NAME_LABEL, "Rebate Plan Status", "Rebate Plan Type", "Rebate Structure", "Range Based On", NET_SALES_FORMULA, NET_SALES_RULE, "Rebate Based On", ConstantsUtils.CREATED_DATE1, CREATED_BY_LABEL, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL};

   public final Object[] ifpItemsCol = new Object[]{"ifpId","ifpNo","ifpName", "ifpStatus","ifpStartDate","ifpEndDate","ifpDesignation","parentIfpId","parentIfpName","ifpType",ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE,"ifpCategory"};
    /**
     * The Constant PS_SEARCH_TABLE.
     */
    public final Object[] psSearchTable = new Object[]{"psSystemId",
        "priceScheduleId", "priceScheduleNo", "priceScheduleName",
        "priceScheduleType", "priceScheduleStatus",
        "priceScheduleCategory", "priceScheduleStartDate",
        "priceScheduleEndDate", "priceScheduleDesignation", "parentId", "parentName", "tradeClass"};

    /**
     * The Constant PS_COL_HEADERS.
     */
    public final String[] psColHeaders = new String[]{"System ID",
        "Price Schedule ID", "Price Schedule No", "Price Schedule Name",
        "Price Schedule Type", "Price Schedule Status",
        "Price Schedule Category", START_DATE_LABEL, END_DATE_LABEL, "Price Schedule Designation", "Parent ID", "Parent Name",
        "Trade Class"};
    public final Object[] ifpitemDetailsCol = new Object[]{ConstantsUtils.CHECK_BOX, RECORD_TYPE,Constants.ITEM_NO, Constants.ITEM_NAME, ITEM_DESC_PROPERTY, "ifpStatus",START_DATE, END_DATE, "itemsStatus",
        "form", STRENGTH, "therapyClass", BRAND,Constants.ATTACHED_DATE, MODIFIED_DATE,ConstantsUtils.MODIFIEDBY,ConstantsUtils.CREATEDDATE,ConstantsUtils.CREATEDBY};
    public static final String IFP_NAME_LABEL = "IFP Name";
    public static final String IFP_END_DATE_LABEL = "IFP End Date";
    public static final String IFP_START_DATE_LABEL = "IFP Start Date";

    public final String[] ifpItemsHeader = new String[]{"IFP ID", "IFP NO", IFP_NAME_LABEL, IFP_STATUS, IFP_START_DATE_LABEL, IFP_END_DATE_LABEL,"IFP Designation","Parent IFP ID","Parent IFP Name","IFP Type", CREATED_BY_LABEL,ConstantsUtils.CREATED_DATE1,"IFP Category"};
    public final String[] ifpItemDetailsColHeader = new String[]{"", RECORD_TYPE_LABEL, ITEM_NO_LABEL, ITEM_NAME_LABEL, ITEM_DESC_LABEL, IFP_STATUS, IFP_START_DATE_LABEL, IFP_END_DATE_LABEL, ITEM_STATUS_LABEL, "Form", STRENGTH_LABEL, "Therapy Class", BRAND_LABEL,Constants.ATTACHEDDATE, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL,ConstantsUtils.CREATED_DATE1, CREATED_BY_LABEL};
    static final Map<String, String> rebatePlanLookupColumnMap = new ConcurrentHashMap<>();
    static final Map<String, String> columnMap = new ConcurrentHashMap<>();
    public static final String SHOW_ALL = "Show All";
    public static final String FIELD_PRICE_TYPE = PRICE_TYPE_LABEL;
    public static final String DELIMITER = "~~";
   /** The Constant IFP_SEARCH_TABLE. */
public final Object[] ifpSearchLookup = new Object[] {
		"itemFamilyplanSystemId","itemFamilyplanId", "itemFamilyplanNo", "itemFamilyplanName",
		"ifpType", "ifPStatus", "ifpCategory", "startDate", "endDate","ifpDesignation","parentItemFamilyplanId",
                "parentItemFamilyplanName",
		ConstantsUtils.CREATEDBY,ConstantsUtils.CREATEDDATE};
/** The Constant IFP_COL_HEADERS. */
public final String[] ifpLookupHeaders = new String[] {
	"System ID","IFP ID", "IFP No", IFP_NAME_LABEL,
	"IFP Type", IFP_STATUS,"IFP Category", IFP_START_DATE_LABEL, IFP_END_DATE_LABEL,"IFP Designation","Parent ID",
        "Parent Name", CREATED_BY_LABEL,ConstantsUtils.CREATED_DATE1};
    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public DashboardDAO getDao() {
        return dao;
    }
    /**
     * Empty Constructor
     */
    private ContractUtils() {
        //Empty Constructor
    }
    
    private static ContractUtils object;

    public static ContractUtils getInstance() {
        if (object == null) {
            object = new ContractUtils();
        }
        return object;
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
        final List<String> list = new ArrayList<>();
        list.add(Constants.SELECT_ONE);
        if (isIFP) {
            list.add("IFP No");
            list.add(IFP_NAME_LABEL);
        } else{
            list.add("Brand Name");
            list.add("Form");
            list.add("Item Description");
            list.add(ITEM_NAME_LABEL);
            list.add(ITEM_NO_LABEL);
            list.add("NDC 8");
            list.add("NDC 9");
            list.add(STRENGTH_LABEL);
            list.add(THERAPEUTIC_CLASS);
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<String> returnList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        final List<HelperDTO> helperList = new ArrayList<>();
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
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> helpList = new ArrayList<>();
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
            Date date;
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
           LOGGER.error(ex);
        }
        return null;
    }
    public static void loadRebatePlanLookupColumnMap() {
        rebatePlanLookupColumnMap.put("rebatePlanId", "RP.REBATE_PLAN_ID");
        rebatePlanLookupColumnMap.put(REBATE_PLAN_NO_PROPERTY, "RP.REBATE_PLAN_NO");
        rebatePlanLookupColumnMap.put(REBATE_PLAN_NAME, "RP.REBATE_PLAN_NAME");
        rebatePlanLookupColumnMap.put("rebatePlanStatus", "RP.REBATE_PLAN_STATUS");
        rebatePlanLookupColumnMap.put("rebatePlanType", "RP.REBATE_PLAN_TYPE");
        rebatePlanLookupColumnMap.put("rebateStructure", "RP.REBATE_STRUCTURE");
        rebatePlanLookupColumnMap.put("rebateBasedOn", "RP.REBATE_BASED_ON");
        rebatePlanLookupColumnMap.put("rangeBasedOn", "RP.REBATE_RANGE_BASED_ON");
        rebatePlanLookupColumnMap.put(ConstantsUtils.CREATEDBY, " RP.CREATED_BY");
        rebatePlanLookupColumnMap.put(ConstantsUtils.CREATEDDATE, "RP.CREATED_DATE");
        rebatePlanLookupColumnMap.put(ConstantsUtils.MODIFIEDBY, "RP.MODIFIED_BY");
        rebatePlanLookupColumnMap.put(MODIFIED_DATE, " RP.MODIFIED_DATE");
        rebatePlanLookupColumnMap.put("netSalesFormula", "NSF.NET_SALES_FORMULA_NAME");
        rebatePlanLookupColumnMap.put(NET_SALES_RULE_PROPERTY, "CDR.RULE_NAME");
}

    public static String getRebatePlanLookupColumnMap(String key) {
        return rebatePlanLookupColumnMap.get(key);
    }

    /**
     * The Constant DEDUCTION_LOOKUP_COLUMN.
     */
    public final Object[] deductionLookupColumn = new Object[]{
        "deductionNo", "deductionName", "deductionDesc", "deductionCategory", "creationDate", ConstantsUtils.CREATEDBY, MODIFIED_DATE, ConstantsUtils.MODIFIEDBY};

    /**
     * The Constant DEDUCTION_LOOKUP_HEADER.
     */
    public final String[] deductionLookUpHeader = new String[]{
        "Deduction Calendar No", "Deduction Calendar Name", "Deduction Calendar Desc", "Category", "Creation Date", CREATED_BY_LABEL, MODIFIED_DATE_LABEL, MODIFIED_BY_LABEL};

    public static final String REBATE_AMOUNT = "Rebate Amount";

    /**
     * The Constant itemRebateStartDate.
     */
    public static final String ITEM_REBATE_START_DATE = START_DATE_LABEL;

    /**
     * The Constant itemRebateEndDate.
     */
    public static final String ITEM_REBATE_END_DATE = END_DATE_LABEL;

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
