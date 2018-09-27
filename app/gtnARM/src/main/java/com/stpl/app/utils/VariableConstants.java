/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * Class to to hold all the variable names Which interns holds the name of
 * variable not the value
 *
 * @author karthikeyans
 */
public class VariableConstants {

    public static final String EMPTY_STRING = "";
    public static final String INVENTORY_DETAILS = "inventoryDetails";
    public static final String INVENTORY_DETAILS_DDLB = "inventoryDetailsDdlb";
    public static final String ADJUSTED_PRICE = "adjustedPrice";
    public static final String BASELINE_PRICE = "baselinePrice";
    public static final String TRX6_BASELINE_PRICE = "baseLinePrice";
    public static final String INVENTORY_CUSTOMER = "inventoryCustomer";
    public static final String INVENTORYCAL_CULATION = "inventoryCalculation";
    public static final String EXCLUSION_DETAILS = "exclusionDetails";
    public static final String PRICE = "price";
    public static final String RESERVE_DATE = "reserveDate";
    public static final String RATE_BASIS = "rateBasis";
    public static final String RATE_FREQUENCY = "rateFrequency";
    public static final String RATE_PERIOD = "ratePeriod";
    public static final String MONTH = "month";
    public static final String DATE_TYPE = "dateType";
    public static final String CHECK_RECORD = "checkRecord";
    public static final String SALES = "sales";
    public static final String INVENTORY = "inventory";
    public static final String RATE = "rates";
    public static final String SEARCH_ICON = "searchicon";
    public static final String DASH = " - ";
    public static final String COMMA = ARMConstants.getComma();
    public static final String GROUP = "group";
    public static final String PRODUCT_NO = "brand_item_no";
    public static final String PRODUCT_NAME = "brand_item_name";
    public static final String PRODUCT_MASTER_SID = "branditemmasterSid";
    public static final String PRICE_OVERRIDE = "priceOverride.7";
    public static final String PRICE_OVERRIDE_INVENTORY = "priceOverride";
    public static final String CHILDREN_ALLOWED = "childrenAllowed";
    public static final String CUSTOMER_MASTER_ID = "customer_masterSid";
    public static final String SUMMARY_DEDUCTION_LEVEL = "summarydeductionLevel";
    public static final String SUMMARY_GL_DATE = "summaryglDate";
    public static final String RATE_DEDUCTION_LEVEL = "rateDeductionLevel";
    public static final String RATES_OVERRIDE_FLAG = "ratesOverrideFlag";
    public static final String DETAIL_VARIABLE = "detailvariables";
    public static final String DETAIL_LEVEL = "detailLevel";
    public static final String RATE_DEDUCTION_VALUE = "rateDeductionValue";
    public static final String DETAIL_RESERVER_ACCOUNT = "detailreserveAcount";
    public static final String DETAIL_AMOUNT_FILTER = "detailamountFilter";
    public static final String SALES_VARIABLE = "salesvariables";
    public static final String SUMMARY_DEDUCTION_VALUE = "summarydeductionValues";
    public static final String SUMMARY_VARIABLES = "summaryvariables";
    private static final String[] MONTHS = new DateFormatSymbols().getShortMonths();
    public static final String ARM = "ARM";
    public static final String SUMMARY_FREQUENCY = "summarydemandfrequency";
    public static final String SUMMARY_VIEW_TYPE = "summarydemandview";
    public static final String SUMMARY_FROM_DATE = "summarydemandfromDate";
    public static final String SUMMARY_TO_DATE = "summarydemandtoDate";
    public static final String INVENTORY_OPTION_GROUP = "inventoryOptionGroup";
    public static final String INVENTORY_DETAIL = "inventoryDetails";
    public static final String INVENTORY_RESERVE_DATE = "inventoryreserveDate";
    public static final String SUMMARY_VARIABLES_INVENTORY = "summary_variables_inventory";
    public static final String PERCENT = "%";
    public static final String DIVISION_16 = "division.16";
    public static final String COST_CENTER_8 = "costCenter.8";
    public static final String H_ACCOUNT_TYPE_51 = "h_accountType.51";
    public static final String ACCOUNTING_DATE_9 = "accountingDate.9";
    public static final String OVERRIDE_VISIBLE_COLUMN = "override";
    public static final String OVERRIDE_HEADER = "Override";
    public static final String GLMONTH_2 = "glMonth.2";
    public static final String PRODUCT = "Product";
    public static final String PRODUCT_UPPER = "PRODUCT";
    public static final String CUSTOMER = "Customer";
    public static final String CUSTOMER_GROUP = "Customer Group";
    public static final String PUBLIC = "Public";
    public static final String PRIVATE = "Private";
    public static final String CUSTOMER_UPPERCASE = "CUSTOMER";
    public static final String BRAND_UPPERCASE = "BRAND";
    public static final String CONTRACT_UPPERCASE = "CONTRACT";
    public static final String DEDUCTION_UPPERCASE = "DEDUCTION";
    public static final String MONTHLY = "Monthly";
    public static final String ADMINISTRATOR = "Administrator";
    public static final String SELECT_ONE = "-Select One-";
    public static final String ADJUSTMENT_SUMMARY = "AdjustmnetSummary";
    public static final String DEDUCTION_PROGRAM = "Deduction Program";
    public static final String DEDUCTION_LEVELS = "DEDUCTION_LEVELS";
    public static final String DEDUCTION_CONTRACT = "Deduction Contract";
    public static final String DEDUCTION_PRODUCT_DB = "D,B,I";
    public static final String DEDUCTION_CUSTOMER_DB = "D,T,B,I";
    public static final String DEDUCTION_CUS_CON_DB = "D,T,C,B,I";
    public static final String CUSTOMER_DEDUC_DB = "T,D,B,I";

    private static final String[] AMOUNTFILTEROPTIONS = new String[]{CommonConstant.GREATER, "= 0.00", "< 0.00"};

    private static final String[] AMOUNTFILTEROPTIONSRESERVE = new String[]{CommonConstant.GREATER};

    private static final String[] AMOUNTFILTEROPTIONSGTN = new String[]{CommonConstant.GREATER, "< 0.00"};
    private static final String H_ACCOUNT_INDICATOR36 = "h_accountIndicator.36";
    private static final String HACCOUNT_CATEGORY = "h_accountCategory.33";
    private static final String H_WORK_FLOW_STATUS = "h_workFlowStatus.42";
    private static final String ACCOUNT_DESCRIPTION37 = "accountDescription.37";
    private static final String H_ADJUSTMENT_LEVEL = "h_adjustmentLevel.35";
    private static final String H_UDC6 = "h_udc6.32";
    private static final String H_UDC5 = "h_udc5.31";
    private static final String H_UDC4 = "h_udc4.30";
    private static final String H_UDC3 = "h_udc3.29";
    private static final String H_UDC2 = "h_udc2.28";
    private static final String H_REVERSAL_PERIOD_OR_DATE = "h_reversalPeriodOrDate.14";
    private static final String CURRENCY = "h_currency.8";
    private static final String BATCH_NAME = "batchName.10";
    private static final String JOURNAL_NAME1 = "journalName.11";

    private static final String[] ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE = new String[]{CommonConstant.ADJUSTMENT_TYPE0, "balanceType.1", "database.2", "dataAccessSet.3",
        "chartOfAccounts.4", "ledger.5", "category.6", "source.7", CURRENCY, ACCOUNTING_DATE_9, BATCH_NAME, JOURNAL_NAME1, "journalDescription.12",
        "reverseJournal.13", H_REVERSAL_PERIOD_OR_DATE, CommonConstant.COMPANY15, DIVISION_16, "businessUnit.17", CommonConstant.COST_CENTER18, CommonConstant.ACCOUNT19, CommonConstant.BRAND20, CommonConstant.PROJECT21, CommonConstant.FUTURE122, CommonConstant.FUTURE223, CommonConstant.DEBIT24, CommonConstant.CREDIT25, CommonConstant.LINE_DESCRIPTION26, CommonConstant.UDC127, H_UDC2, H_UDC3, H_UDC4, H_UDC5, H_UDC6, HACCOUNT_CATEGORY, CommonConstant.ACCOUNT_TYPE34, H_ADJUSTMENT_LEVEL, H_ACCOUNT_INDICATOR36, ACCOUNT_DESCRIPTION37, "redemptionPeriodDate.38",
        "calculationPriodDate.39", "workFlowId.40", "workFlowName.41", H_WORK_FLOW_STATUS};
    private static final String H_UDC246 = "h_udc2.46";
    private static final String H_DEDUCTION_UDC239 = "h_deductionUDC2.39";
    private static final String HDEDUCTION_UDC = "h_deductionUDC4.41";
    private static final String HDEDUCTION_UDC5 = "h_deductionUDC5.42";
    private static final String HWORK_FLOW_STATUS = "h_workFlowStatus.59";
    private static final String HACCOUNT_INDICATOR = "h_accountIndicator.55";
    private static final String HADJUSTMENT_LEVEL = "h_adjustmentLevel.53";
    private static final String HACCOUNT_CATEGORY5 = "h_accountCategory.52";
    private static final String HDEDUCTION_PROGRAM = "h_deductionProgram.36";
    private static final String HUDC = "h_udc6.50";
    private static final String HUDC5 = "h_udc5.49";
    private static final String HUDC4 = "h_udc4.48";
    private static final String HUDC3 = "h_udc3.47";
    private static final String HDEDUCTION_UDC6 = "h_deductionUDC6.43";
    private static final String H_DEDUCTION_UDC3 = "h_deductionUDC3.40";
    private static final String H_DEDUCTION_UDC1 = "h_deductionUDC1.38";
    private static final String H_DEDUCTION_CATEGORY3 = "h_deductionCategory.35";
    private static final String DEDUCTION_NO33 = "deductionNo.33";

    private static final String[] ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE = new String[]{CommonConstant.ADJUSTMENT_TYPE0, CommonConstant.DEDUCTION_TYPE1, GLMONTH_2, CommonConstant.GL_YEAR3, CommonConstant.GL_STRING4, CommonConstant.GL_COMPANY_ID5, CommonConstant.DIVISION6, "businessUnitID.7", COST_CENTER_8, CommonConstant.ACCOUNT9, CommonConstant.BRAND_ID10, CommonConstant.PROJECT11, CommonConstant.FUTURE112, CommonConstant.FUTURE213, CommonConstant.ITEM_NO14, CommonConstant.DEDUCTION_AMOUNT15, "glCompanyNo.16", "glCompanyName.17", "businessUnitNo.18", "businessUnitName.19",
        "glDate.20", "createdDate.21", "redemptionPeriodDate.22", "contractID.23", "contractNo.24", "contractName.25", "companyID.26", "companyNo.27",
        "companyName.28", "itemID.29", "itemName.30", "brandName.31", "deductionID.32", DEDUCTION_NO33, "deductionName.34", H_DEDUCTION_CATEGORY3, HDEDUCTION_PROGRAM, "h_deductionInclusion.37", H_DEDUCTION_UDC1, H_DEDUCTION_UDC239, H_DEDUCTION_UDC3, HDEDUCTION_UDC, HDEDUCTION_UDC5, HDEDUCTION_UDC6, "deductionRate.44", CommonConstant.UDC145, H_UDC246, HUDC3, HUDC4, HUDC5, HUDC,
        H_ACCOUNT_TYPE_51, HACCOUNT_CATEGORY5, HADJUSTMENT_LEVEL, "accountDescription.54", HACCOUNT_INDICATOR, "calculationPriodDate.56",
        "workFlowId.57", "workFlowName.58", HWORK_FLOW_STATUS};
    public static final String PROJECTION_ID = "@projectionId";
    public static final String A_PRIVATE = "Private";
    public static final String PRIVATE_VIEW = "privateView";
    public static final String GROUP_RETURNS = "group";
    public static final String PRODUCT_RETURNS = "PRODUCT";
    public static final String ERROR_IN_SUBMIT_BTNADD_CLICK_LISTENER = "Error in submitBtn.addClickListener";
    public static final String ERROR_IN_MAIL_NOTIFICATION_ARCHIVE = "Error in mailNotificationArchive :";
    public static final String CONTRACT_DETAILS = "Contract Details";
    public static final String CONTRACT_TERMS = "Contract Terms";
    public static final String CALCULATED_RETURNS = "Calculated";
    public static final String AND = " AND ";
    public static final String SLASH_SPACE = "\\ ";
    public static final String TOTAL_TOTAL_PERIOD_ADJUSTMENT = "TotalTotalPeriodAdjustment";
    public static final String TOTAL_PIPELINE_ACCRUAL = "TotalPipelineAccrual";
    public static final String TOTAL_ENDING_BALANCE = "TotalEndingBalance";
    public static final String TOTAL_PIPELINE_INVENTORY_TRUE_UP = "TotalPipelineInventoryTrueUp";
    public static final String TOTAL_DEMAND_ACCRUAL = "TotalDemandAccrual";
    public static final String DEMAND_ACCRUAL = "DemandAccrual";
    public static final String TOTAL_STARTING_BALANCE = "TotalStartingBalance";
    public static final String TOTAL_RETURN_RESERVE = "TotalReturnReserve";
    public static final String TOTAL_DEMAND_REFORECAST = "TotalDemandReforecast";
    public static final String PIPELINE_INVENTORY_TRUE_UP = "PipelineInventoryTrueUp";
    public static final String DEMAND_REFORECAST = "DemandReforecast";
    public static final String PAYMENT_TRUE_UP = "PaymentTrueup";
    public static final String TOTAL_PAYMENT_TRUE_UP = "TotalPaymentTrueup";
    public static final String ACTUAL_PAYMENTS = "ActualPayments";
    public static final String TOTAL_ACTUAL_PAYMENTS = "TotalActualPayments";
    public static final String PERIOD_BALANCE = "PeriodBalance";
    public static final String TOTAL_PERIOD_BALANCE = "TotalPeriodBalance";
    public static final String PAYMENT_RATIO = "PaymentRatio";
    public static final String TOTAL_PAYMENT_RATIO = "TotalPaymentRatio";
    public static final String NO_COLUMN = "NoColumn";
    public static final String FEES_ACCRUAL = "FeesAccrual";
    public static final String INFLATION_ADJUSTMENT = "InflationAdjustment";
    public static final String CREDIT_CARD_FEES = "CreditCardFees";
    public static final String OTHER_FIXED_DOLLAR_FEES = "OtherFixedDollarFees";
    public static final String INVENTORY_VALUATION = "InventoryValuation";
    public static final String PAYMENTS = "Payments";
    public static final String TOTAL_FEES_ACCRUAL = "TotalFeesAccrual";
    public static final String TOTAL_INFLATION_ADJUSTMENT = "TotalInflationAdjustment";
    public static final String TOTAL_CREDIT_CARD_FEES = "TotalCreditCardFees";
    public static final String TOTAL_OTHER_FIXED_DOLLAR_FEES = "TotalOtherFixedDollarFees";
    public static final String TOTAL_INVENTORY_VALUATION = "TotalInventoryValuation";
    public static final String TOTAL_PAYMENTS = "TotalPayments";
    public static final String BEGINNING_BALANCE = "BeginningBalance";
    public static final String BU_COMPANY_MASTER_SID = "bu.COMPANY_MASTER_SID";
    public static final String CRFIRST_NAME_CRLAST_NAME = "(cr.firstName + ' ' + cr.lastName)";
    public static final String COMP_COMPANY_MASTER_SID = "comp.COMPANY_MASTER_SID";
    public static final String ACCOUNT = "ACCOUNT";
    public static final String CHECK_RECORD1 = "CHECK_RECORD";
    public static final String COMPANY_ID_WITH_NAME = "companyIdWithName";
    public static final String COST_CENTER = "COST_CENTER";
    public static final String BRAND_DDLB = "brandDdlb";
    public static final String BU_ID_WITH_NAME = "buIdWithName";
    public static final String COMPANY_NO_HELPER_DTO = "companyNoHelperDto";
    public static final String BUSINESS_NO_HELPER_DTO = "businessNoHelperDto";
    public static final String BRAND_WITH_ID_NAME = "brandWithIdName";
    public static final String LINE_DESCRIPTION = "LINE_DESCRIPTION";
    public static final String ACCOUNT_DESC = "ACCOUNT_DESC";
    public static final String CHART_OF_ACCOUNTS = "CHART_OF_ACCOUNTS";
    public static final String DATA_ACCESS_SET = "DATA_ACCESS_SET";
    public static final String REVERSE_JOURNAL = "REVERSE_JOURNAL";
    public static final String CUTRANSACTION_NAME = "CU.TRANSACTION_NAME";
    public static final String LEDGER = "LEDGER";
    public static final String BALANCE_TYPE = "BALANCE_TYPE";
    public static final String JOURNAL_NAME = "JOURNAL_NAME";
    public static final String JOURNAL_DESCRIPTION = "JOURNAL_DESCRIPTION";

    public static String[] getMONTHS() {
        return MONTHS.clone();
    }

    public static String[] getAmountfilteroptions() {
        return AMOUNTFILTEROPTIONS.clone();
    }

    public static String[] getAmountFilterOptionsReserve() {
        return AMOUNTFILTEROPTIONSRESERVE.clone();
    }

    public static String[] getAmountFilterOptionsGtn() {
        return AMOUNTFILTEROPTIONSGTN.clone();
    }

    public static String[] getAdjustmentDemandPipelineReserveVariable() {
        return ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE.clone();
    }

    private static final String[] ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_DEFAULT_SELECTION = new String[]{ACCOUNTING_DATE_9, CommonConstant.COMPANY15, DIVISION_16, CommonConstant.COST_CENTER18, CommonConstant.ACCOUNT19, CommonConstant.BRAND20, CommonConstant.PROJECT21, CommonConstant.FUTURE122, CommonConstant.FUTURE223, CommonConstant.DEBIT24, CommonConstant.CREDIT25, CommonConstant.LINE_DESCRIPTION26, CommonConstant.UDC127, CommonConstant.ACCOUNT_TYPE34};
    private static final String[] ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_DEFAULT_SELECTION = new String[]{CommonConstant.ADJUSTMENT_TYPE0, CommonConstant.DEDUCTION_TYPE1, GLMONTH_2, CommonConstant.GL_YEAR3, CommonConstant.GL_STRING4, CommonConstant.GL_COMPANY_ID5, CommonConstant.DIVISION6, COST_CENTER_8, CommonConstant.ACCOUNT9, CommonConstant.BRAND_ID10, CommonConstant.PROJECT11, CommonConstant.FUTURE112, CommonConstant.FUTURE213, CommonConstant.ITEM_NO14, CommonConstant.DEDUCTION_AMOUNT15, CommonConstant.UDC145, H_ACCOUNT_TYPE_51};
    private static final String[] ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE = new String[]{CommonConstant.ADJUSTMENT_TYPE0, "balanceType.1", "database.2", "dataAccessSet.3",
        "chartOfAccounts.4", "ledger.5", "category.6", "source.7", CURRENCY, ACCOUNTING_DATE_9, BATCH_NAME, JOURNAL_NAME1, "journalDescription.12",
        "reverseJournal.13", H_REVERSAL_PERIOD_OR_DATE, CommonConstant.COMPANY15, DIVISION_16, "businessUnit.17", CommonConstant.COST_CENTER18, CommonConstant.ACCOUNT19, CommonConstant.BRAND20, CommonConstant.PROJECT21, CommonConstant.FUTURE122, CommonConstant.FUTURE223, CommonConstant.DEBIT24, CommonConstant.CREDIT25, CommonConstant.LINE_DESCRIPTION26, CommonConstant.UDC127, H_UDC2, H_UDC3, H_UDC4, H_UDC5, H_UDC6, HACCOUNT_CATEGORY, CommonConstant.ACCOUNT_TYPE34, H_ADJUSTMENT_LEVEL, H_ACCOUNT_INDICATOR36, ACCOUNT_DESCRIPTION37, "redemptionPeriodDate.38",
        "calculationPriodDate.39", "workFlowId.40", "workFlowName.41", H_WORK_FLOW_STATUS};
    private static final String[] ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE = new String[]{CommonConstant.ADJUSTMENT_TYPE0, CommonConstant.DEDUCTION_TYPE1, GLMONTH_2, CommonConstant.GL_YEAR3, CommonConstant.GL_STRING4, CommonConstant.GL_COMPANY_ID5, CommonConstant.DIVISION6, "businessUnitID.7", COST_CENTER_8, CommonConstant.ACCOUNT9, CommonConstant.BRAND_ID10, CommonConstant.PROJECT11, CommonConstant.FUTURE112, CommonConstant.FUTURE213, CommonConstant.ITEM_NO14, CommonConstant.DEDUCTION_AMOUNT15, "glCompanyNo.16", "glCompanyName.17", "businessUnitNo.18", "businessUnitName.19",
        "glDate.20", "createdDate.21", "redemptionPeriodDate.22", "contractID.23", "contractNo.24", "contractName.25", "companyID.26", "companyNo.27",
        "companyName.28", "itemID.29", "itemName.30", "brandName.31", "deductionID.32", DEDUCTION_NO33, "deductionName.34", H_DEDUCTION_CATEGORY3, HDEDUCTION_PROGRAM, "deductionInclusion.37", H_DEDUCTION_UDC1, H_DEDUCTION_UDC239, H_DEDUCTION_UDC3, HDEDUCTION_UDC, HDEDUCTION_UDC5, HDEDUCTION_UDC6, "deductionRate.44", CommonConstant.UDC145, H_UDC246, HUDC3, HUDC4, HUDC5, HUDC,
        H_ACCOUNT_TYPE_51, HACCOUNT_CATEGORY5, HADJUSTMENT_LEVEL, "accountDescription.54", HACCOUNT_INDICATOR, "calculationPriodDate.56",
        "workFlowId.57", "workFlowName.58", HWORK_FLOW_STATUS};
    private static final String[] ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE_DEFAULT_SELECTION = new String[]{ACCOUNTING_DATE_9, CommonConstant.COMPANY15, DIVISION_16, CommonConstant.COST_CENTER18, CommonConstant.ACCOUNT19, CommonConstant.BRAND20, CommonConstant.PROJECT21, CommonConstant.FUTURE122, CommonConstant.FUTURE223, CommonConstant.DEBIT24, CommonConstant.CREDIT25, CommonConstant.LINE_DESCRIPTION26, CommonConstant.UDC127, CommonConstant.ACCOUNT_TYPE34};
    private static final String[] ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE_DEFAULT_SELECTION = new String[]{CommonConstant.ADJUSTMENT_TYPE0, CommonConstant.DEDUCTION_TYPE1, GLMONTH_2, CommonConstant.GL_YEAR3, CommonConstant.GL_STRING4, CommonConstant.GL_COMPANY_ID5, CommonConstant.DIVISION6, COST_CENTER_8, CommonConstant.ACCOUNT9, CommonConstant.BRAND_ID10, CommonConstant.PROJECT11, CommonConstant.FUTURE112, CommonConstant.FUTURE213, CommonConstant.ITEM_NO14, CommonConstant.DEDUCTION_AMOUNT15, CommonConstant.UDC145, H_ACCOUNT_TYPE_51};

    public static String[] getAdjustmentDemandPipelineGtnVariable() {
        return ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE.clone();
    }

    public static String[] getAdjustmentDemandPipelineReserveVariableDefaultSelection() {
        return ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_DEFAULT_SELECTION.clone();
    }

    public static String[] getAdjustmentDemandPipelineGtnVariableDefaultSelection() {
        return ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_DEFAULT_SELECTION.clone();
    }

    public static String[] getAdjustmentDemandSummaryReserveVariable() {
        return ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE.clone();
    }

    public static String[] getAdjustmentDemandSummaryGtnVariable() {
        return ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE.clone();
    }

    public static String[] getAdjustmentDemandSummaryReserveVariableDefaultSelection() {
        return ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE_DEFAULT_SELECTION.clone();
    }

    public static String[] getAdjustmentDemandSummaryGtnVariableDefaultSelection() {
        return ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE_DEFAULT_SELECTION.clone();
    }

    /**
     * Enum for common constants
     */
    public enum PipelineSummaryVariables {

        CPIPELINEACRUAL(ARMConstants.getCPipelineAccrual()),
        PPIPELINEACRUAL(ARMConstants.getPPipelineAccrual()),
        VARIANCE(ARMConstants.getVariance()),
        OVERRIDE(ARMConstants.getOverride()),
        ADJUSTMENT(ARMConstants.getAdjustment());
        private final String constant;

        PipelineSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PipelineSummaryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    /**
     * Enum for common constants
     */
    public enum PISummaryVariables {

        CPIPELINEACCRUAL(ARMConstants.getCPipelineAccrual()),
        PPIPELINEACCRUAL(ARMConstants.getPPipelineAccrual()),
        VARIANCE(ARMConstants.getVariance()),
        PIPELINERATIO(ARMConstants.getPipelineRatio()),
        OVERRIDE(ARMConstants.getOverride()),
        ADJUSTMENT(ARMConstants.getAdjustment());
        private String constant;

        PISummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PISummaryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    public enum PISummaryDeductionVariables {

        CPIPELINEACCRUAL(ARMConstants.getCPipelineAccrual()),
        PPIPELINEACCRUAL(ARMConstants.getPPipelineAccrual()),
        PIPELINERATIO(ARMConstants.getPipelineRatio()),
        VARIANCE(ARMConstants.getVariance()),
        OVERRIDE(ARMConstants.getOverride()),
        ADJUSTMENT(ARMConstants.getAdjustment());
        private String constant;

        PISummaryDeductionVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PISummaryDeductionVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    /**
     * Enum for common constants
     */
    public enum DemandSummaryVariables {

        DEMANDACCRUAL(ARMConstants.getDemandAccrualConstant()),
        DEMANDACCRUALREFORECAST(ARMConstants.getDemandAccrualReforecast()),
        TOTALDEMANDACCRUAL(ARMConstants.getTotalDemandAccrual()),
        PROJECTEDTOTALDEMANDACCRUAL(ARMConstants.getProjectedTotalDemandAccrual()),
        DEMANDACCRUALRATIO(ARMConstants.getDemandAccrualRatio()),
        VARIANCE(ARMConstants.getVariance()),
        OVERRIDE(ARMConstants.getOverride()),
        ADJUSTMENT(ARMConstants.getAdjustment());

        protected String constant;

        private DemandSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(DemandSummaryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    public enum DemandPaymentSummaryVariables {

        DEMANDACCRUAL(ARMConstants.getDemandAccrualConstant()),
        DEMANDACCRUALREFORECAST(ARMConstants.getDemandAccrualReforecast()),
        DEMANDACCRUALRECON(ARMConstants.getDemandPaymentReconVariable()),
        TOTALDEMANDACCRUAL(ARMConstants.getTotalDemandAccrual()),
        ACTUALPAYMENTS(ARMConstants.getActualPayments()),
        PAYMENTRATIO(ARMConstants.getPaymentRatio()),
        VARIANCE(ARMConstants.getVariance()),
        OVERRIDE(ARMConstants.getOverride()),
        ADJUSTMENT(ARMConstants.getAdjustment());

        private String constant;

        DemandPaymentSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(DemandPaymentSummaryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    public enum SalesVariables {

        TOTAL_UNITS("Total Units"),
        TOTAL_SALES("Total Sales"),
        EXCLUDED_UNITS("Excluded Units"),
        EXCLUDED_SALES("Excluded Sales"),
        NET_UNITS("Net Units"),
        NET_SALES("Net Sales"),
        PRICE("Price"),
        PRICE_OVERRIDE("Price Override"),
        NET_CALCULATED_SALES("Net Calculated Sales"),
        SALES_VARIANCE("Sales Variance"),
        SALES_VARIANCE_PER("Sales Variance %");
        private String constant;

        private SalesVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(SalesVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    public enum PipelineInventoryVariables {

        TOTAL_UNITS("Total Inventory"),
        TOTAL_SALES("Weeks On Hand"),
        EXCLUDED_UNITS("Units Per Retail"),
        EXCLUDED_SALES("Price"),
        NET_UNITS("Price Override"),
        NET_SALES("Return Reserve"),
        PRICE("Net Pipeline Value");
        private String constant;

        private PipelineInventoryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PipelineInventoryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    private static final String[] VARIABLE_VISIBLE_COLUMN = new String[]{CommonConstant.C_PIPELINE_ACCRUAL, CommonConstant.P_PIPELINE_ACCRUAL, CommonConstant.VARIANCE};

    private static final String[] VARIABLE_VISIBLE_COLUMN_DEDUTION = new String[]{CommonConstant.C_PIPELINE_ACCRUAL, CommonConstant.P_PIPELINE_ACCRUAL, CommonConstant.VARIANCE, OVERRIDE_VISIBLE_COLUMN, CommonConstant.ADJUSTMENT};
    private static final String[] VARIABLE_VISIBLE_COLUMN_PI = new String[]{CommonConstant.C_PIPELINE_ACCRUAL, CommonConstant.P_PIPELINE_ACCRUAL, "pipelineRatio", CommonConstant.VARIANCE};
    private static final String[] VARIABLE_VISIBLE_COLUMN_DEDUTION_PI = new String[]{CommonConstant.C_PIPELINE_ACCRUAL, CommonConstant.P_PIPELINE_ACCRUAL, "pipelineRatio", CommonConstant.VARIANCE, OVERRIDE_VISIBLE_COLUMN, CommonConstant.ADJUSTMENT};
    private static final String[] VARIABLE_SALES_VISIBLE_COLUMN = new String[]{"totalUnits.0", "totalSales.1", "excludedUnits.2", "excludedSales.3", "netUnits.4", "netSales.5", "price.6", "priceOverride.7", "netCalculatedSales.8", "salesVariance.9", "salesVariancePer.10"};

    private static final String[] VISIBLE_COLUMN_DEMAND_ACCRUAL = new String[]{CommonConstant.DEMAND_ACCRUAL, CommonConstant.DEMAND_ACCRUAL_REFORECAST, CommonConstant.TOTAL_DEMAND_ACCRUAL, "projectedTotalDemandAccrual", "demandAccrualRatio", CommonConstant.VARIANCE};

    private static final String[] VISIBLE_COLUMN_DEMAND_ACCRUAL_DEDUCTION = new String[]{CommonConstant.DEMAND_ACCRUAL, CommonConstant.DEMAND_ACCRUAL_REFORECAST, CommonConstant.TOTAL_DEMAND_ACCRUAL, "projectedTotalDemandAccrual", "demandAccrualRatio", CommonConstant.VARIANCE, OVERRIDE_VISIBLE_COLUMN, CommonConstant.ADJUSTMENT};
    private static final String[] VARIABLE_INVENTORY_VISIBLE_COLUMN = new String[]{"totalInventory", "weeksOnHand", "unitsPerRetail", "price", "priceOverride", "returnReserve", "netPipelineValue"};
    private static final String[] VISIBLE_COLUMN_DEMAND_PAYMENT_DEDUCTION = new String[]{CommonConstant.DEMAND_ACCRUAL, CommonConstant.DEMAND_ACCRUAL_REFORECAST, "demandPaymentRecon", CommonConstant.TOTAL_DEMAND_ACCRUAL, "actualPayments", "paymentRatio", CommonConstant.VARIANCE, OVERRIDE_VISIBLE_COLUMN, CommonConstant.ADJUSTMENT};
    private static final String[] VISIBLE_COLUMN_DEMAND_PAYMENT = new String[]{CommonConstant.DEMAND_ACCRUAL, CommonConstant.DEMAND_ACCRUAL_REFORECAST, "demandPaymentRecon", CommonConstant.TOTAL_DEMAND_ACCRUAL, "actualPayments", "paymentRatio", CommonConstant.VARIANCE};

    private static final String[] VARIABLE_VISIBLE_COLUMN_TRX7 = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", CommonConstant.VARIANCE};
    private static final String[] VARIABLE_VISIBLE_COLUMN_TRX7_DEDUCTION = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", CommonConstant.VARIANCE, OVERRIDE_VISIBLE_COLUMN, CommonConstant.ADJUSTMENT};

    public static String[] getVariableVisibleColumn() {
        return VARIABLE_VISIBLE_COLUMN.clone();
    }

    public static String[] getVariableVisibleColumnDedution() {
        return VARIABLE_VISIBLE_COLUMN_DEDUTION.clone();
    }

    public static String[] getVariableVisibleColumnPi() {
        return VARIABLE_VISIBLE_COLUMN_PI.clone();
    }

    public static String[] getVariableVisibleColumnDedutionPi() {
        return VARIABLE_VISIBLE_COLUMN_DEDUTION_PI.clone();
    }

    public static String[] getVariableSalesVisibleColumn() {
        return VARIABLE_SALES_VISIBLE_COLUMN.clone();
    }

    public static String[] getVisibleColumnDemandAccrual() {
        return VISIBLE_COLUMN_DEMAND_ACCRUAL.clone();
    }

    public static String[] getVisibleColumnDemandAccrualDeduction() {
        return VISIBLE_COLUMN_DEMAND_ACCRUAL_DEDUCTION.clone();
    }

    public static String[] getVariableInventoryVisibleColumn() {
        return VARIABLE_INVENTORY_VISIBLE_COLUMN.clone();
    }

    public static String[] getVisibleColumnDemandPaymentDeduction() {
        return VISIBLE_COLUMN_DEMAND_PAYMENT_DEDUCTION.clone();
    }

    public static String[] getVisibleColumnDemandPayment() {
        return VISIBLE_COLUMN_DEMAND_PAYMENT.clone();
    }

    public static String[] getVariableVisibleColumnTrx7() {
        return VARIABLE_VISIBLE_COLUMN_TRX7.clone();
    }

    public static String[] getVariableVisibleColumnTrx7Deduction() {
        return VARIABLE_VISIBLE_COLUMN_TRX7_DEDUCTION.clone();
    }
    public static final String LEVEL_NAME = "levelNames";
    public static final String COMPANY_SID = "compSids";
    public static final String BRNAD_ID = "brandSids";

    public enum Trx7SummaryVariables {

        CURRENTBALANCE(ARMConstants.getTrx7CurrentBalance()),
        CALCULATEDADJUSTMENT(ARMConstants.getTrx7CalculatedAdjustment()),
        ADJUSTEMNTRATIO(ARMConstants.getTrx7AdjustmentRatio()),
        VARIANCE(ARMConstants.getTrx7Variance()),
        OVERRIDE(ARMConstants.getTrx7Override()),
        ADJUSTMENT(ARMConstants.getTrx7Adjustment());
        private String constant;

        Trx7SummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(Trx7SummaryVariables.values()).replaceAll(CommonConstant.DOLLAR, StringUtils.EMPTY).split(",");
        }

    }

    public static final String RETURNS_DATA_VARIABLES = "returnsdataSelectedvariables";
    public static final String RETURN_RESERVE_DATA_VARIABLES = "returnReserveDataVariables";
    public static final String ORIGINAL_SALE_LIMITER_VAL = "originalSaleLimiterVal";
    public static final String REMOVE_CLOSED_BATCHES = "removeClosedBatches";
    public static final String EXCLUDE_BASED_ON_LOE_DATE = "excludeBasedOnLoeDate";
    public static final String RETURNS_DATA_VARIABLES_FIELD = "'returnsdataSelectedvariables'";
    public static final String RETURN_RESERVE_DATA_VARIABLES_FIELD = "'returnReserveDataVariables'";
    public static final String ORIGINAL_SALE_LIMITER_VAL_FIELD = "'originalSaleLimiterVal'";
    public static final String REMOVE_CLOSED_BATCHES_FIELD = "'removeClosedBatches'";
    public static final String EXCLUDE_BASED_ON_LOE_DATE_FIELD = "'excludeBasedOnLoeDate'";
    public static final String RATE_DEDUCTION_LEVEL_FIELD = "'rateDeductionLevel'";
    public static final String RATE_DEDUCTION_VALUE_FIELD = "'rateDeductionValue'";
    public static final String RATE_BASIS_FIELD = "'rateBasis'";
    public static final String SUMMARY_DEDUCTION_VALUES_FIELD = "'summarydeductionValues'";
    public static final String SUMMARY_VARIABLES_FIELD = "'summaryvariables'";
    public static final String SUMMARY_DEDUCTION_LEVEL_FIELD = "'summarydeductionLevel'";
    public static final String SUMMARY_GL_DATE_FIELD = "'summaryglDate'";
    private static final Map<String, String> VIEW_JOIN_MAP = new HashMap<>();

    public static Map<String, String> loadJoinsForAdjustmentExcel() {
        if (VIEW_JOIN_MAP.isEmpty()) {
            VIEW_JOIN_MAP.put(CURRENCY, "LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CURRENCY");
            VIEW_JOIN_MAP.put(H_REVERSAL_PERIOD_OR_DATE, "LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID = REVERSAL_PERIOD_DATE");
            VIEW_JOIN_MAP.put(H_UDC2, "LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID = UDC_2");
            VIEW_JOIN_MAP.put(H_UDC3, "LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID = UDC_3");
            VIEW_JOIN_MAP.put(H_UDC4, "LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID = UDC_4");
            VIEW_JOIN_MAP.put(H_UDC5, "LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID = UDC_5");
            VIEW_JOIN_MAP.put(H_UDC6, "LEFT JOIN HELPER_TABLE HT6 ON HT6.HELPER_TABLE_SID = UDC_6");
            VIEW_JOIN_MAP.put(HACCOUNT_CATEGORY, "LEFT JOIN HELPER_TABLE HT7 ON HT7.HELPER_TABLE_SID = ACCOUNT_CATEGORY");
            VIEW_JOIN_MAP.put(H_ADJUSTMENT_LEVEL, "LEFT JOIN HELPER_TABLE HT8 ON HT8.HELPER_TABLE_SID = ADJUSTMENT_LEVEL");
            VIEW_JOIN_MAP.put(H_ACCOUNT_INDICATOR36, "LEFT JOIN HELPER_TABLE HT9 ON HT9.HELPER_TABLE_SID = ACCOUNT_INDICATOR");
            VIEW_JOIN_MAP.put(H_WORK_FLOW_STATUS, "LEFT JOIN HELPER_TABLE HT10 ON HT10.HELPER_TABLE_SID = WORKFLOW_STATUS");

            VIEW_JOIN_MAP.put(H_DEDUCTION_CATEGORY3, "LEFT JOIN #TEMP_HELPER HT11 ON HT11.HELPER_TABLE_SID = DEDUCTION_CATEGORY");
            VIEW_JOIN_MAP.put(HDEDUCTION_PROGRAM, "LEFT JOIN #TEMP_HELPER HT12 ON HT12.HELPER_TABLE_SID = DEDUCTION_PROGRAM");
            VIEW_JOIN_MAP.put("h_deductionInclusion.37", "LEFT JOIN #TEMP_HELPER HT13 ON HT13.HELPER_TABLE_SID = DEDUCTION_INCLUSION");
            VIEW_JOIN_MAP.put(H_DEDUCTION_UDC1, "LEFT JOIN #TEMP_HELPER HT14 ON HT14.HELPER_TABLE_SID = DEDUCTION_UDC_1");
            VIEW_JOIN_MAP.put(H_DEDUCTION_UDC239, "LEFT JOIN #TEMP_HELPER HT15 ON HT15.HELPER_TABLE_SID = DEDUCTION_UDC_2");
            VIEW_JOIN_MAP.put(H_DEDUCTION_UDC3, "LEFT JOIN #TEMP_HELPER HT16 ON HT16.HELPER_TABLE_SID = DEDUCTION_UDC_3");
            VIEW_JOIN_MAP.put(HDEDUCTION_UDC, "LEFT JOIN #TEMP_HELPER HT17 ON HT17.HELPER_TABLE_SID = DEDUCTION_UDC_4");
            VIEW_JOIN_MAP.put(HDEDUCTION_UDC5, "LEFT JOIN #TEMP_HELPER HT18 ON HT18.HELPER_TABLE_SID = DEDUCTION_UDC_5");
            VIEW_JOIN_MAP.put(HDEDUCTION_UDC6, "LEFT JOIN #TEMP_HELPER HT19 ON HT19.HELPER_TABLE_SID = DEDUCTION_UDC_6");
            VIEW_JOIN_MAP.put(H_UDC246, "LEFT JOIN #TEMP_HELPER HT20 ON HT20.HELPER_TABLE_SID = UDC_2");
            VIEW_JOIN_MAP.put(HUDC3, "LEFT JOIN #TEMP_HELPER HT21 ON HT21.HELPER_TABLE_SID = UDC_3");
            VIEW_JOIN_MAP.put(HUDC4, "LEFT JOIN #TEMP_HELPER HT22 ON HT22.HELPER_TABLE_SID = UDC_4");
            VIEW_JOIN_MAP.put(HUDC5, "LEFT JOIN #TEMP_HELPER HT23 ON HT23.HELPER_TABLE_SID = UDC_5");
            VIEW_JOIN_MAP.put(HUDC, "LEFT JOIN #TEMP_HELPER HT24 ON HT24.HELPER_TABLE_SID = UDC_6");
            VIEW_JOIN_MAP.put(H_ACCOUNT_TYPE_51, "LEFT JOIN #TEMP_HELPER HT25 ON HT25.HELPER_TABLE_SID = ACCOUNT_TYPE");
            VIEW_JOIN_MAP.put(HACCOUNT_CATEGORY5, "LEFT JOIN #TEMP_HELPER HT26 ON HT26.HELPER_TABLE_SID = ACCOUNT_CATEGORY");
            VIEW_JOIN_MAP.put(HADJUSTMENT_LEVEL, "LEFT JOIN #TEMP_HELPER HT27 ON HT27.HELPER_TABLE_SID = ADJUSTMENT_LEVEL");
            VIEW_JOIN_MAP.put(HACCOUNT_INDICATOR, "LEFT JOIN #TEMP_HELPER HT28 ON HT28.HELPER_TABLE_SID = ACCOUNT_INDICATOR");
            VIEW_JOIN_MAP.put(HWORK_FLOW_STATUS, "LEFT JOIN #TEMP_HELPER HT29 ON HT29.HELPER_TABLE_SID = WORKFLOW_STATUS");
        }
        return VIEW_JOIN_MAP;
    }

}
