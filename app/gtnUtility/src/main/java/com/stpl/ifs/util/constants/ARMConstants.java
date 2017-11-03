/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util.constants;

import com.stpl.app.util.service.PropertiesReader;

/**
 *
 * @author
 */
public class ARMConstants {

    private static final PropertiesReader.ExtProperties properties = PropertiesReader.getInstance().getConfirmationMessages();

    public static String getAdjustmentType() {
        return properties.getProperty("ADJUSTMENT_TYPE");
    }

    public static String getAdjustmentLevel() {
        return properties.getProperty("ADJUSTMENT_LEVEL");
    }

    public static String getAccountCategory() {
        return properties.getProperty("ACCOUNT_CATEGORY");
    }

    public static String getAccountType() {
        return properties.getProperty("ACCOUNT_TYPE");
    }

    public static String getAccount() {
        return properties.getProperty("ACCOUNT");
    }

    public static String getAccountDescription() {
        return properties.getProperty("ACCOUNT_DESCRIPTION");
    }

    public static String getAccountIndictor() {
        return properties.getProperty("ACCOUNT_INDICTOR");
    }

    public static String getCostCenter() {
        return properties.getProperty("COST_CENTER");
    }

    public static String getProject() {
        return properties.getProperty("PROJECT");
    }

    public static String getFuture1() {
        return properties.getProperty("FUTURE_1");
    }

    public static String getFuture2() {
        return properties.getProperty("FUTURE_2");
    }

    public static String getBalanceType() {
        return properties.getProperty("BALANCE_TYPE");
    }

    public static String getDatabase() {
        return properties.getProperty("DATABASE");
    }

    public static String getDataAccessSet() {
        return properties.getProperty("DATA_ACCESS_SET");
    }

    public static String getChartOfAccounts() {
        return properties.getProperty("CHART_OF_ACCOUNTS");
    }

    public static String getLedger() {
        return properties.getProperty("LEDGER");
    }

    public static String getCategory() {
        return properties.getProperty("CATEGORY");
    }

    public static String getSource() {
        return properties.getProperty("SOURCE");
    }

    public static String getCurrency() {
        return properties.getProperty("CURRENCY");
    }

    public static String getJournalName() {
        return properties.getProperty("JOURNAL_NAME");
    }

    public static String getJournalDescription() {
        return properties.getProperty("JOURNAL_DESCRIPTION");
    }

    public static String getReverseJournal() {
        return properties.getProperty("REVERSE_JOURNAL");
    }

    public static String getReversalPeriodDate() {
        return properties.getProperty("REVERSAL_PERIOD_DATE");
    }

    public static String getLineDescription() {
        return properties.getProperty("LINE_DESCRIPTION");
    }

    public static String getUDC1() {
        return properties.getProperty("UDC_1");
    }

    public static String getUDC2() {
        return properties.getProperty("UDC_2");
    }

    public static String getUDC3() {
        return properties.getProperty("UDC_3");
    }

    public static String getUDC4() {
        return properties.getProperty("UDC_4");
    }

    public static String getUDC5() {
        return properties.getProperty("UDC_5");
    }

    public static String getUDC6() {
        return properties.getProperty("UDC_6");
    }

    public static String getReserveDetails() {
        return GlobalConstants.getReserveDetail();
    }

    public static String getGTNDetails() {
        return GlobalConstants.getGTNDetail();
    }

    public static String getPipelineTrueUp() {
        return properties.getProperty("PIPELINE_TRUE_UP");
    }

    public static String getPipelineAccrual() {
        return properties.getProperty("PIPELINE_ACCRUAL");
    }

    public static String getMonthly() {
        return properties.getProperty("MONTHLY");
    }

    public static String getQuarterly() {
        return properties.getProperty("QUARTERLY");
    }

    public static String getSemiAnnually() {
        return properties.getProperty("SEMI_ANNUALLY");
    }

    public static String getAnnually() {
        return properties.getProperty("ANNUALLY");
    }

    public static String getInventoryCustomer() {
        return properties.getProperty("INVENTORY_CUSTOMER");
    }

    public static String getInventoryCalculation() {
        return properties.getProperty("INVENTORY_CALCULATION");
    }

    public static String getExclusionDetails() {
        return properties.getProperty("EXCLUSION_DETAILS");
    }

    public static String getPrice() {
        return properties.getProperty("PRICE");
    }

    public static String getReserveDate() {
        return properties.getProperty("RESERVE_DATE");
    }

    public static String getRateBasis() {
        return properties.getProperty("RATE_BASIS");
    }

    public static String getRateFrequency() {
        return properties.getProperty("RATE_FREQUENCY");
    }

    public static String getRatePeriod() {
        return properties.getProperty("RATE_PERIOD");
    }

    public static String getDateType() {
        return properties.getProperty("DATE_TYPE");
    }

    public static String getDemandRebateAccrual() {
        return properties.getProperty("DEMAND_REBATE_ACCRUAL");
    }

    public static String getDemandRebateReforecast() {
        return properties.getProperty("DEMAND_REBATE_REFORECAST");
    }

    public static String getDemandPaymentTrueUp() {
        return properties.getProperty("DEMAND_PAYMENT_TRUE_UP");
    }

    public static String getDeductionCategory() {
        return properties.getProperty("DEDUCTION_CATEGORY");
    }

    public static String getDeductionProgram() {
        return properties.getProperty("DEDUCTION_PROGRAM");
    }

    public static String getDeductionType() {
        return properties.getProperty("DEDUCTION_TYPE");
    }

    public static String getDeductionCategory2() {
        return properties.getProperty("DEDUCTION_CATEGORY2");
    }

    public static String getDeductionCategory3() {
        return properties.getProperty("DEDUCTION_CATEGORY3");
    }

    public static String getDeductionCategory4() {
        return properties.getProperty("DEDUCTION_CATEGORY4");
    }

    public static String getDeductionCategory5() {
        return properties.getProperty("DEDUCTION_CATEGORY5");
    }

    public static String getDeductionCategory6() {
        return properties.getProperty("DEDUCTION_CATEGORY6");
    }

    public static String getDeduction() {
        return properties.getProperty("DEDUCTION");
    }

    public static String getAccountId() {
        return properties.getProperty("ACCOUNT_ID");
    }

    public static String getContractId() {
        return properties.getProperty("CONTRACT_ID");
    }

    public static String getAccountName() {
        return properties.getProperty("ACCOUNT_NAME");
    }

    public static String getInventoryDetailsConstant() {
        return properties.getProperty("INVENTORY_DETAILS");
    }

    public static String getDemandAccrual() {
        return properties.getProperty("DEMAND_ACCRUAL");
    }

    public static String getPipelineInventoryTrueUp() {
        return properties.getProperty("PIPELINE_INVENTORY_TRUEUP");
    }

    public static String getDemandPaymentsRecon() {
        return properties.getProperty("DEMAND_PAYMENTS_RECON");
    }

    public static String getDemandReforecastTrueUp() {
        return properties.getProperty("DEMAND_REFORECAST_TRUEUP");
    }

    public static String getTransaction6() {
        return properties.getProperty("TRANSACTION_6");
    }

    public static String getTransaction7() {
        return properties.getProperty("TRANSACTION_7");
    }

    public static String getAccountingDate() {
        return properties.getProperty("ACCOUNTING_DATE");
    }

    public static String getBatchName() {
        return properties.getProperty("BATCH_NAME");
    }

    public static String getCompany() {
        return properties.getProperty("COMPANY");
    }

    public static String getDivision() {
        return properties.getProperty("DIVISION");
    }

    public static String getBrand() {
        return properties.getProperty("BRAND");
    }

    public static String getBusinessUnit() {
        return properties.getProperty("BUSINESS_UNIT");
    }

    public static String getDebit() {
        return properties.getProperty("DEBIT");
    }

    public static String getCredit() {
        return properties.getProperty("CREDIT");
    }

    public static String getGLCompanyId() {
        return properties.getProperty("GL_COMPANY_ID");
    }

    public static String getGLCompanyNo() {
        return properties.getProperty("GL_COMPANY_NO");
    }

    public static String getGLCompanyName() {
        return properties.getProperty("GL_COMPANY_NAME");
    }

    public static String getBusinessUnitId() {
        return properties.getProperty("BUSINESS_UNIT_ID");
    }

    public static String getBusinessUnitNo() {
        return properties.getProperty("BUSINESS_UNIT_NO");
    }

    public static String getBusinessUnitName() {
        return properties.getProperty("BUSINESS_UNIT_NAME");
    }

    public static String getGLYear() {
        return properties.getProperty("GL_YEAR");
    }

    public static String getGLMonth() {
        return properties.getProperty("GL_MONTH");
    }

    public static String getGLDate() {
        return properties.getProperty("GL_DATE");
    }

    public static String getGLString() {
        return properties.getProperty("GL_STRING");
    }

    public static String getCreatedDate() {
        return properties.getProperty("CREATED_DATE");
    }

    public static String getRedemptionPeriod() {
        return properties.getProperty("REDEMPTION_PERIOD");
    }

    public static String getDetailContractId() {
        return properties.getProperty("DETAIL_CONTRACT_ID");
    }

    public static String getDetailContractNo() {
        return properties.getProperty("DETAIL_CONTRACT_NO");
    }

    public static String getDetailContractName() {
        return properties.getProperty("DETAIL_CONTRACT_NAME");
    }

    public static String getCompanyId() {
        return properties.getProperty("COMPANY_ID");
    }

    public static String getCompanyNo() {
        return properties.getProperty("COMPANY_NO");
    }

    public static String getCompanyName() {
        return properties.getProperty("COMPANY_NAME");
    }

    public static String getItemId() {
        return properties.getProperty("ITEM_ID");
    }

    public static String getItemNo() {
        return properties.getProperty("ITEM_NO");
    }

    public static String getItemName() {
        return properties.getProperty("ITEM_NAME");
    }

    public static String getBrandId() {
        return properties.getProperty("BRAND_ID");
    }

    public static String getBrandName() {
        return properties.getProperty("BRAND_NAME");
    }

    public static String getDeductionId() {
        return properties.getProperty("DEDUCTION_ID");
    }

    public static String getDeductionNo() {
        return properties.getProperty("DEDUCTION_NO");
    }

    public static String getDeductionName() {
        return properties.getProperty("DEDUCTION_NAME");
    }

    public static String getDeductionInclusion() {
        return properties.getProperty("DEDUCTION_INCLUSION");
    }

    public static String getDeductionUDC1() {
        return properties.getProperty("DEDUCTION_UDC_1");
    }

    public static String getDeductionUDC2() {
        return properties.getProperty("DEDUCTION_UDC_2");
    }

    public static String getDeductionUDC3() {
        return properties.getProperty("DEDUCTION_UDC_3");
    }

    public static String getDeductionUDC4() {
        return properties.getProperty("DEDUCTION_UDC_4");
    }

    public static String getDeductionUDC5() {
        return properties.getProperty("DEDUCTION_UDC_5");
    }

    public static String getDeductionUDC6() {
        return properties.getProperty("DEDUCTION_UDC_6");
    }

    public static String getDeductionAmount() {
        return properties.getProperty("DEDUCTION_AMOUNT");
    }

    public static String getDeductionRate() {
        return properties.getProperty("DEDUCTION_RATE");
    }

    public static String getCPipelineAccrual() {
        return properties.getProperty("C_PIPELINE_ACCRUAL");
    }

    public static String getPPipelineAccrual() {
        return properties.getProperty("P_PIPELINE_ACCRUAL");
    }

    public static String getVariance() {
        return properties.getProperty("VARIANCE");
    }

    public static String getOverride() {
        return properties.getProperty("OVERRIDE");
    }

    public static String getDeductionProduct() {
        return properties.getProperty("DEDUCTION_PRODUCT");
    }

    public static String getDeductionCustomer() {
        return properties.getProperty("DEDUCTION_CUSTOMER");
    }

    public static String getCustomerDedection() {
        return properties.getProperty("CUSTOMER_DEDUCTION");
    }

    public static String getDeductionCustomerContract() {
        return properties.getProperty("DEDUCTION_CUSTOMER_CONTRACT");
    }

    public static String getAdjustmentSummary() {
        return properties.getProperty("ADJUSTMENT_SUMMARY");
    }

    public static String getDataSelection() {
        return properties.getProperty("DATA_SELECTION");
    }

    public static String getLevel1Brand() {
        return properties.getProperty("LEVEL_1_BRAND");
    }

    public static String getLevel2Item() {
        return properties.getProperty("LEVEL_2_ITEM");
    }

    public static String getLevel1Customer() {
        return properties.getProperty("LEVEL_1_CUSTOMER");
    }

    public static String getLevel2Brand() {
        return properties.getProperty("LEVEL_2_BRAND");
    }

    public static String getLevel3Item() {
        return properties.getProperty("LEVEL_3_ITEM");
    }

    public static String getLevel2Deduction() {
        return properties.getProperty("LEVEL_2_DEDUCTION");
    }

    public static String getLevel3Brand() {
        return properties.getProperty("LEVEL_3_BRAND");
    }

    public static String getLevel4Item() {
        return properties.getProperty("LEVEL_4_ITEM");
    }

    public static String getLevel1Deduction() {
        return properties.getProperty("LEVEL_1_DEDUCTION");
    }

    public static String getLevel2Customer() {
        return properties.getProperty("LEVEL_2_CUSTOMER");
    }

    public static String getLevel3Contract() {
        return properties.getProperty("LEVEL_3_CONTRACT");
    }

    public static String getLevel4Brand() {
        return properties.getProperty("LEVEL_4_BRAND");
    }

    public static String getLevel5Item() {
        return properties.getProperty("LEVEL_5_ITEM");
    }

    public static String getCustomer() {
        return properties.getProperty("CUSTOMER");
    }

    public static String getContract() {
        return properties.getProperty("CONTRACT_CON");
    }

    public static String getItem() {
        return properties.getProperty("ITEM");
    }

    public static String getAdjustment() {
        return properties.getProperty("ADJUSTMENT");
    }

    public static String getDemandAccrualReforecast() {
        return properties.getProperty("DEMAND_ACCRUAL_REFORECAST");
    }

    public static String getTotalDemandAccrual() {
        return properties.getProperty("TOTAL_DEMAND_ACCRUAL");
    }

    public static String getProjectedTotalDemandAccrual() {
        return properties.getProperty("PROJECTED_TOTAL_DEMAND_ACCRUAL");
    }

    public static String getDemandAccrualRatio() {
        return properties.getProperty("DEMAND_ACCRUAL_RATIO");
    }

    public static String getPipelineRatio() {
        return properties.getProperty("PIPELINE_RATIO");
    }

    public static String getWorkFlowID() {
        return properties.getProperty("WORKFLOW_ID");
    }

    public static String getWorkFlowStatus() {
        return properties.getProperty("WORKFLOW_STATUS");
    }

    public static String getWorkFlowName() {
        return properties.getProperty("WORKFLOW_NAME");
    }

    public static String getActualPayments() {
        return properties.getProperty("ACTUAL_PAYMENTS");
    }

    public static String getPaymentRatio() {
        return properties.getProperty("PAYMENT_RATIO");
    }

    public static String getSinglePeriod() {
        return properties.getProperty("SINGLE_PERIOD");
    }

    public static String getMultiplePeriod() {
        return properties.getProperty("MULTIPLE_PERIOD");
    }

    public static String getAddMode() {
        return properties.getProperty("ADD");
    }

    public static String getEditMode() {
        return properties.getProperty("EDIT");
    }

    public static String getYes() {
        return properties.getProperty("YES");
    }

    public static String getNo() {
        return properties.getProperty("NO");
    }

    public static String getDemandAccrualConstant() {
        return properties.getProperty("DEMAND_ACCRUAL_CONSTANT");
    }

    public static String getDemandPaymentReconVariable() {
        return properties.getProperty("DEMAND_PAYMENTS_RECON_VARIABLE");
    }

    public static String getDate() {
        return properties.getProperty("DATE");
    }

    public static String getAmount() {
        return properties.getProperty("AMOUNT");
    }

    public static String getRate() {
        return properties.getProperty("RATE");
    }

    public static String getAdjusted_price() {
        return properties.getProperty("ADJUSTED_PRICE");
    }

    public static String getBase_line_price() {
        return properties.getProperty("BASE_LINE_PRICE");
    }

    public static String getTrx7CurrentBalance() {
        return properties.getProperty("TRX7_CURRENT_BALANCE");
    }

    public static String getTrx7CalculatedAdjustment() {
        return properties.getProperty("TRX7_CALCULATED_ADJUSTMENT");
    }

    public static String getTrx7Variance() {
        return properties.getProperty("VARIANCE");
    }

    public static String getTrx7AdjustmentRatio() {
        return properties.getProperty("TRX7_ADJUSTMENT_RATIO");
    }

    public static String getTrx7Override() {
        return properties.getProperty("OVERRIDE");
    }

    public static String getTrx7Adjustment() {
        return properties.getProperty("ADJUSTMENT");
    }

    public static String getTwoDecFormat() {
        return properties.getProperty("TWO_DEC_FORMAT");
    }

    public static String getThreeDecFormat() {
        return properties.getProperty("THREE_DEC_FORMAT");
    }

    public static String getNoDecFormat() {
        return properties.getProperty("NO_DEC_FORMAT");
    }

    public static String getCurrNoDecFormat() {
        return properties.getProperty("CURR_NO_DEC_FORMAT");
    }

    public static String getDeductionContract() {
        return properties.getProperty("DEDUCTION_CONTRACT");
    }

    public static String getCalculation_Period() {
        return properties.getProperty("CALCULATION_PERIOD");
    }

    public static String getCurrNoDecPerFormat() {
        return properties.getProperty("CURR_NO_DEC_PER_FORMAT");
    }

    public static String getSingelPeriodView() {
        return properties.getProperty("SINGLE_PERIOD_VIEW");
    }

    public static String getBalanceSummaryReport() {
        return properties.getProperty("BALANCE_SUMMARY_REPORT");
    }

    public static String getDemand() {
        return properties.getProperty("DEMAND");
    }

    public static String getTransaction8() {
        return properties.getProperty("TRANSACTION_8");
    }

    public static String getStartBalance() {
        return properties.getProperty("STARTING_BALANCE");
    }

    public static String getFeesAcrual() {
        return properties.getProperty("FEES_ACCRAUL");
    }

    public static String getInflationAdjustment() {
        return properties.getProperty("INFLATION_ADJUSTMENT");
    }

    public static String getCreditCardfees() {
        return properties.getProperty("CREDIT_CARD_FEES");
    }

    public static String getOtherFixedDollarAdjustment() {
        return properties.getProperty("OTHER_FIXED_DOLLER_ADJUSTMENT");
    }

    public static String getInventoryValuation() {
        return properties.getProperty("INVENTORY_VALUATION");
    }

    public static String getPaymentTrueUp() {
        return properties.getProperty("PAYMENT_TRUP_UP");
    }

    public static String getReturnReserve() {
        return properties.getProperty("RETURN_RESERVE");
    }

    public static String getPipelineAccrualHeader() {
        return properties.getProperty("PIPELINE_ACCRUAL_HEADER");
    }

    public static String getPipelineInverntorytrueupHeader() {
        return properties.getProperty("PIPELINE_INVENTORY_TRUPUP_HEADER");
    }

    public static String getDemandReforecastHeader() {
        return properties.getProperty("DEMAND_REFORECAST_HEADER");
    }

    public static String getDemandPaymenttrueupHeader() {
        return properties.getProperty("DEMAND_PAYMENT_TRUEUP_HEADER");
    }

    public static String getOctalValue() {
        return properties.getProperty("OCTAL_VALUE");
    }
    public static String getCurrentPipeline() {
        return properties.getProperty("C_PIPELINE");
    }

    public static String getDemandPayment() {
        return properties.getProperty("DEMAND_PAYMENTS_RECON_VAR");
    }
    
}
