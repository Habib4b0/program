/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.utils;

import com.stpl.ifs.util.constants.ARMConstants;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import org.apache.commons.lang.StringUtils;

/**
 *
 * Class to to hold all the variable names Which interns holds the name of
 * variable not the value
 *
 * @author karthikeyans
 */
public class VariableConstants {

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
    public static final String SINGLE_QUOTE = "'";
    public static final String DASH = " - ";
    public static final String COMMA = ",";
    public static final String GROUP = "group";
    public static final String PRODUCT_NO = "brand_item_no";
    public static final String PRODUCT_NAME = "brand_item_name";
    public static final String PRODUCT_MASTER_SID = "brand_item_masterSid";
    public static final String PRICE_OVERRIDE = "priceOverride.7";
    public static final String PRICE_OVERRIDE_INVENTORY = "priceOverride";
    public static final String CHILDREN_ALLOWED = "childrenAllowed";
    public static final String CUSTOMER_MASTER_ID = "customer_masterSid";
    public static final String SUMMARY_DEDUCTION_LEVEL = "summary_deductionLevel";
    public static final String SUMMARY_GL_DATE = "summary_glDate";
    public static final String RATE_DEDUCTION_LEVEL = "rate_DeductionLevel";
    public static final String DETAIL_VARIABLE = "detail_variables";
    public static final String DETAIL_LEVEL = "detail_Level";
    public static final String RATE_DEDUCTION_VALUE = "rate_DeductionValue";
    public static final String DETAIL_RESERVER_ACCOUNT = "detail_reserveAcount";
    public static final String DETAIL_AMOUNT_FILTER = "detail_amountFilter";
    public static final String SALES_VARIABLE = "sales_variables";
    public static final String SUMMARY_DEDUCTION_VALUE = "summary_deductionValues";
    public static final String SUMMARY_VARIABLES = "summary_variables";
    public static final String[] MONTHS = new DateFormatSymbols().getShortMonths();
    public static final String ARM = "ARM";
    public static final String SUMMARY_FREQUENCY = "summary_demand_frequency";
    public static final String SUMMARY_VIEW_TYPE = "summary_demand_view";
    public static final String SUMMARY_FROM_DATE = "summary_demand_fromDate";
    public static final String SUMMARY_TO_DATE = "summary_demand_toDate";
    public static final String INVENTORY_OPTION_GROUP = "inventoryOptionGroup";
    public static final String INVENTORY_DETAIL = "inventory_Details";
    public static final String INVENTORY_RESERVE_DATE = "inventory_reserveDate";
    public static final String SUMMARY_VARIABLES_INVENTORY = "summary_variables_inventory";
    public static final String PERCENT = "%";
    public static final String DIVISION_16 = "division.16";
    public static final String COST_CENTER_8 = "costCenter.8";
    public static final String H_ACCOUNT_TYPE_51 = "h_accountType.51";
    public static final String ACCOUNTING_DATE_9 = "accountingDate.9";
    public static final String override = "override";
    public static final String OVERRIDE = "Override";
    public static final String GLMONTH_2 = "glMonth.2";
    public static final String PRODUCT = "Product";
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

    public static final String[] AMOUNT_FILTER_OPTIONS = new String[]{"> 0.00", "= 0.00", "< 0.00"};

    public static final String[] AMOUNT_FILTER_OPTIONS_RESERVE = new String[]{"> 0.00"};

    public static final String[] AMOUNT_FILTER_OPTIONS_GTN = new String[]{"> 0.00", "< 0.00"};

    
      public static final String[] ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE = new String[]{"adjustmentType.0" , "balanceType.1" , "database.2" , "dataAccessSet.3" , 
        "chartOfAccounts.4" , "ledger.5" , "category.6" , "source.7" , "h_currency.8" , ACCOUNTING_DATE_9 , "batchName.10" , "journalName.11" , "journalDescription.12" ,
        "reverseJournal.13" , "h_reversalPeriodOrDate.14" , "company.15" , DIVISION_16 , "businessUnit.17" , "costCenter.18" , "account.19" , "brand.20" , "project.21" ,
        "future1.22" , "future2.23" , "debit.24" , "credit.25" , "lineDescription.26" , "udc1.27" , "h_udc2.28" , "h_udc3.29" , "h_udc4.30" , "h_udc5.31" , "h_udc6.32" ,
        "h_accountCategory.33" , "accountType.34" , "h_adjustmentLevel.35" , "h_accountIndicator.36" , "accountDescription.37" , "redemptionPeriodDate.38" , 
        "calculationPriodDate.39" , "workFlowId.40" , "workFlowName.41" , "h_workFlowStatus.42"};
    
    public static final String[] ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE = new String[]{"adjustmentType.0" , "deductionType.1" , GLMONTH_2 ,
        "glYear.3" , "glString.4" , "glCompanyID.5" , "division.6" , "businessUnitID.7" , COST_CENTER_8 , "account.9" , "brandID.10" , "project.11" ,
        "future1.12" , "future2.13" , "itemNo.14" , "deductionAmount.15" , "glCompanyNo.16" , "glCompanyName.17" , "businessUnitNo.18" , "businessUnitName.19" , 
        "glDate.20" , "createdDate.21" , "redemptionPeriodDate.22" , "contractID.23" , "contractNo.24" , "contractName.25" , "companyID.26" , "companyNo.27" , 
        "companyName.28" , "itemID.29" , "itemName.30" , "brandName.31" , "deductionID.32" , "deductionNo.33" , "deductionName.34" , "h_deductionCategory.35" , 
        "h_deductionProgram.36" , "h_deductionInclusion.37" , "h_deductionUDC1.38" , "h_deductionUDC2.39" , "h_deductionUDC3.40" , "h_deductionUDC4.41" , 
        "h_deductionUDC5.42" , "h_deductionUDC6.43" , "deductionRate.44" , "udc1.45" , "h_udc2.46" , "h_udc3.47" , "h_udc4.48" , "h_udc5.49" , "h_udc6.50" ,
        H_ACCOUNT_TYPE_51 , "h_accountCategory.52" , "h_adjustmentLevel.53" , "accountDescription.54" , "h_accountIndicator.55", "calculationPriodDate.56" , 
        "workFlowId.57" , "workFlowName.58" , "h_workFlowStatus.59" 
    };

    public static final String[] ADJUSTMENT_DEMAND_PIPELINE_RESERVE_VARIABLE_DEFAULT_SELECTION = new String[]{ACCOUNTING_DATE_9, "company.15", DIVISION_16, "costCenter.18", "account.19", "brand.20", "project.21", "future1.22", "future2.23", "debit.24", "credit.25", "lineDescription.26", "udc1.27", "accountType.34"};
    public static final String[] ADJUSTMENT_DEMAND_PIPELINE_GTN_VARIABLE_DEFAULT_SELECTION = new String[]{"adjustmentType.0", "deductionType.1", GLMONTH_2, "glYear.3", "glString.4", "glCompanyID.5", "division.6", COST_CENTER_8, "account.9", "brandID.10", "project.11", "future1.12", "future2.13", "itemNo.14", "deductionAmount.15", "udc1.45", H_ACCOUNT_TYPE_51};
    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE = new String[]{"adjustmentType.0", "balanceType.1", "database.2", "dataAccessSet.3",
        "chartOfAccounts.4", "ledger.5", "category.6", "source.7", "h_currency.8", ACCOUNTING_DATE_9, "batchName.10", "journalName.11", "journalDescription.12",
        "reverseJournal.13", "h_reversalPeriodOrDate.14", "company.15", DIVISION_16, "businessUnit.17", "costCenter.18", "account.19", "brand.20", "project.21",
        "future1.22", "future2.23", "debit.24", "credit.25", "lineDescription.26", "udc1.27", "h_udc2.28", "h_udc3.29", "h_udc4.30", "h_udc5.31", "h_udc6.32",
        "h_accountCategory.33", "accountType.34", "h_adjustmentLevel.35", "h_accountIndicator.36", "accountDescription.37", "redemptionPeriodDate.38",
        "calculationPriodDate.39", "workFlowId.40", "workFlowName.41", "h_workFlowStatus.42"};
    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE = new String[]{"adjustmentType.0", "deductionType.1", GLMONTH_2,
        "glYear.3", "glString.4", "glCompanyID.5", "division.6", "businessUnitID.7", COST_CENTER_8, "account.9", "brandID.10", "project.11",
        "future1.12", "future2.13", "itemNo.14", "deductionAmount.15", "glCompanyNo.16", "glCompanyName.17", "businessUnitNo.18", "businessUnitName.19",
        "glDate.20", "createdDate.21", "redemptionPeriodDate.22", "contractID.23", "contractNo.24", "contractName.25", "companyID.26", "companyNo.27",
        "companyName.28", "itemID.29", "itemName.30", "brandName.31", "deductionID.32", "deductionNo.33", "deductionName.34", "h_deductionCategory.35",
        "h_deductionProgram.36", "deductionInclusion.37", "h_deductionUDC1.38", "h_deductionUDC2.39", "h_deductionUDC3.40", "h_deductionUDC4.41",
        "h_deductionUDC5.42", "h_deductionUDC6.43", "deductionRate.44", "udc1.45", "h_udc2.46", "h_udc3.47", "h_udc4.48", "h_udc5.49", "h_udc6.50",
        H_ACCOUNT_TYPE_51, "h_accountCategory.52", "h_adjustmentLevel.53", "accountDescription.54", "h_accountIndicator.55", "calculationPriodDate.56",
        "workFlowId.57", "workFlowName.58", "h_workFlowStatus.59"};
    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_RESERVE_VARIABLE_DEFAULT_SELECTION = new String[]{ACCOUNTING_DATE_9, "company.15", DIVISION_16, "costCenter.18", "account.19", "brand.20", "project.21", "future1.22", "future2.23", "debit.24", "credit.25", "lineDescription.26", "udc1.27", "accountType.34"};
    public static final String[] ADJUSTMENT_DEMAND_SUMMARY_GTN_VARIABLE_DEFAULT_SELECTION = new String[]{"adjustmentType.0", "deductionType.1", GLMONTH_2, "glYear.3", "glString.4", "glCompanyID.5", "division.6", COST_CENTER_8, "account.9", "brandID.10", "project.11", "future1.12", "future2.13", "itemNo.14", "deductionAmount.15", "udc1.45", H_ACCOUNT_TYPE_51};

    /**
     * Enum for common constants
     */
    public enum PipelineSummaryVariables {

        CPipelineAccrual(ARMConstants.getCPipelineAccrual()),
        PPipelineAccrual(ARMConstants.getPPipelineAccrual()),
        Variance(ARMConstants.getVariance()),
        Override(ARMConstants.getOverride()),
        Adjustment(ARMConstants.getAdjustment());
        private String constant;

        private PipelineSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PipelineSummaryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    /**
     * Enum for common constants
     */
    public enum PISummaryVariables {

        CPipelineAccrual(ARMConstants.getCPipelineAccrual()),
        PPipelineAccrual(ARMConstants.getPPipelineAccrual()),
        Variance(ARMConstants.getVariance()),
        PipelineRatio(ARMConstants.getPipelineRatio()),
        Override(ARMConstants.getOverride()),
        Adjustment(ARMConstants.getAdjustment());
        private String constant;

        private PISummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PISummaryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public enum PISummaryDeductionVariables {

        CPipelineAccrual(ARMConstants.getCPipelineAccrual()),
        PPipelineAccrual(ARMConstants.getPPipelineAccrual()),
        PipelineRatio(ARMConstants.getPipelineRatio()),
        Variance(ARMConstants.getVariance()),
        Override(ARMConstants.getOverride()),
        Adjustment(ARMConstants.getAdjustment());
        private String constant;

        private PISummaryDeductionVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(PISummaryDeductionVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    /**
     * Enum for common constants
     */
    public enum DemandSummaryVariables {

        DemandAccrual(ARMConstants.getDemandAccrualConstant()),
        DemandAccrualReforecast(ARMConstants.getDemandAccrualReforecast()),
        TotalDemandAccrual(ARMConstants.getTotalDemandAccrual()),
        ProjectedTotalDemandAccrual(ARMConstants.getProjectedTotalDemandAccrual()),
        DemandAccuralRatio(ARMConstants.getDemandAccrualRatio()),
        Variance(ARMConstants.getVariance()),
        Override(ARMConstants.getOverride()),
        Adjustment(ARMConstants.getAdjustment());

        private String constant;

        private DemandSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(DemandSummaryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public enum DemandPaymentSummaryVariables {

        DemandAccrual(ARMConstants.getDemandAccrualConstant()),
        DemandAccrualReforecast(ARMConstants.getDemandAccrualReforecast()),
        DemandPaymentRecon(ARMConstants.getDemandPaymentReconVariable()),
        TotalDemandAccrual(ARMConstants.getTotalDemandAccrual()),
        ActualPayments(ARMConstants.getActualPayments()),
        PaymentRatio(ARMConstants.getPaymentRatio()),
        Variance(ARMConstants.getVariance()),
        Override(ARMConstants.getOverride()),
        Adjustment(ARMConstants.getAdjustment());

        private String constant;

        private DemandPaymentSummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(DemandPaymentSummaryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public static enum SalesVariables {

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
            return Arrays.toString(SalesVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

    public static enum PipelineInventoryVariables {

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
            return Arrays.toString(PipelineInventoryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }
    public static final String[] VARIABLE_VISIBLE_COLUMN = new String[]{"cPipelineAccrual", "pPipelineAccrual", "variance"};
    public static final String[] VARIABLE_VISIBLE_COLUMN_DEDUTION = new String[]{"cPipelineAccrual", "pPipelineAccrual", "variance", override, "adjustment"};
    public static final String[] VARIABLE_VISIBLE_COLUMN_PI = new String[]{"cPipelineAccrual", "pPipelineAccrual", "pipelineRatio", "variance"};
    public static final String[] VARIABLE_VISIBLE_COLUMN_DEDUTION_PI = new String[]{"cPipelineAccrual", "pPipelineAccrual", "pipelineRatio", "variance", override, "adjustment"};
    public static final String[] VARIABLE_SALES_VISIBLE_COLUMN = new String[]{"totalUnits.0", "totalSales.1", "excludedUnits.2", "excludedSales.3", "netUnits.4", "netSales.5", "price.6", "priceOverride.7", "netCalculatedSales.8", "salesVariance.9", "salesVariancePer.10"};
    public static final String[] VISIBLE_COLUMN_DEMAND_ACCRUAL = new String[]{"demandAccrual", "demandAccrualReforecast", "totalDemandAccrual", "projectedTotalDemandAccrual", "demandAccrualRatio", "variance"};
    public static final String[] VISIBLE_COLUMN_DEMAND_ACCRUAL_DEDUCTION = new String[]{"demandAccrual", "demandAccrualReforecast", "totalDemandAccrual", "projectedTotalDemandAccrual", "demandAccrualRatio", "variance", override, "adjustment"};
    public static final String[] VARIABLE_INVENTORY_VISIBLE_COLUMN = new String[]{"totalInventory", "weeksOnHand", "unitsPerRetail", "price", "priceOverride", "returnReserve", "netPipelineValue"};
    public static final String[] VISIBLE_COLUMN_DEMAND_PAYMENT_DEDUCTION = new String[]{"demandAccrual", "demandAccrualReforecast", "demandPaymentRecon", "totalDemandAccrual", "actualPayments", "paymentRatio", "variance", override, "adjustment"};
    public static final String[] VISIBLE_COLUMN_DEMAND_PAYMENT = new String[]{"demandAccrual", "demandAccrualReforecast", "demandPaymentRecon", "totalDemandAccrual", "actualPayments", "paymentRatio", "variance"};

    public static final String[] VARIABLE_VISIBLE_COLUMN_TRX7 = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", "variance"};
    public static final String[] VARIABLE_VISIBLE_COLUMN_TRX7_DEDUCTION = new String[]{"currentBalance", "calculatedAdjustment", "adjustmentRatio", "variance", override, "adjustment"};
    public static final String LEVEL_NAME = "levelNames";
    public static final String COMPANY_SID = "compSids";
    public static final String BRNAD_ID = "brandSids";

    public enum Trx7SummaryVariables {

        currentBalance(ARMConstants.getTrx7CurrentBalance()),
        calculatedAdjustment(ARMConstants.getTrx7CalculatedAdjustment()),
        adjustmentRatio(ARMConstants.getTrx7AdjustmentRatio()),
        variance(ARMConstants.getTrx7Variance()),
        override(ARMConstants.getTrx7Override()),
        Adjustment(ARMConstants.getTrx7Adjustment());
        private String constant;

        private Trx7SummaryVariables(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant;
        }

        public static String[] names() {
            return Arrays.toString(Trx7SummaryVariables.values()).replaceAll("^.|.$", StringUtils.EMPTY).split(",");
        }

    }

}
