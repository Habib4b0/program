/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import com.stpl.app.arm.utils.ARMUtils.ADJUSTMENT_RESERVE_CONSTANTS;
import com.vaadin.ui.Component;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author
 */
public class AdjustmentReserveDTO implements Cloneable {

    /**
     * Fields For Search Table
     */
    private String companyNo = StringUtils.EMPTY;
    private String companyName;
    private String businessUnitNo;
    private String businessUnitName;
    private String deductionCategory;
    private String deductionType;
    private String deductionProgram;
    private String createdBy;
    private Date createdDate;
    private Date modifiedDate;
    private String source = StringUtils.EMPTY;
    private int searchMasterSid;

    /**
     * Fields For Add Table Reserve Detail
     */
    private Boolean checkRecord = Boolean.FALSE;
    private int adjustmentType;
    private int debitIndicator;
    private int creditIndicator;
    private int adjustmentLevel;
    private int accountCategory;
    private int accountType;
    private String account = StringUtils.EMPTY;
    private String accountDescription = StringUtils.EMPTY;
    private int accountIndictor;
    private int reportIndicator;
    private String division = StringUtils.EMPTY;
    private String businessUnit = StringUtils.EMPTY;
    private String costCenter = StringUtils.EMPTY;
    private String project = StringUtils.EMPTY;
    private String future1 = StringUtils.EMPTY;
    private String future2 = StringUtils.EMPTY;
    private String balanceType = StringUtils.EMPTY;
    private String database = StringUtils.EMPTY;
    private String dataAccessSet = StringUtils.EMPTY;
    private String chartOfAccounts = StringUtils.EMPTY;
    private String ledger = StringUtils.EMPTY;
    private String category = StringUtils.EMPTY;
    private int currency;
    private String journalName = StringUtils.EMPTY;
    private String journalDescription = StringUtils.EMPTY;
    private String reverseJournal = StringUtils.EMPTY;
    private int reversalPeriodDate;
    private String lineDescription = StringUtils.EMPTY;
    private int udc1;
    private int udc2;
    private int udc3;
    private int udc4;
    private int udc5;
    private int udc6;

    private String adjustmentTypestr;
    private String adjustmentLevelstr;
    private String accountCategorystr;
    private String accountTypestr;
    private String accountIndictorstr;
    private String currencystr;
    private String reversalPeriodDatestr;
    private String debitIndicatorstr;
    private String creditIndicatorstr;
    private String udc1Str;
    private String udc2Str;
    private String udc3Str;
    private String udc4Str;
    private String udc5Str;
    private String udc6Str;
    private String reportIndicatorStr;
    /**
     * Extra Fields For Add Table GTN Detail
     */
    private String gLCompanyNo;

    /**
     * Fields For binder in search screen
     */
    private int companyDdlbRes;
    private int businessDdlbRes;
    private int deductionCategoryDdlbRes;
    private int deductionProgramDdlbRes;
    private int deductionTypeDdlbRes;

    /**
     *
     * Fields for identity.
     */
    private int detailsId;

    private String adjSummaryAdjustmentType;
    private int adjSummaryAdjustmentTypeId;
    private int adjustmentSummarySid;

    private String versionNo;

    private int methodology;
    private Integer adjSummaryPipeLineAccrual;
    private String adjSummaryPipeLineAccrualAccount;
    private String adjSummaryPipeLineAccrualAccountValue;

    private Integer adjSummaryDemandAccrual;
    private String adjSummaryDemandAccrualAccount;
    private String adjSummaryDemandAccrualAccountValue;

    private Integer adjSummaryDemandForecast;
    private String adjSummaryDemandForecastAccount;
    private String adjSummaryDemandForecastAccountValue;

    private Integer adjSummaryCurrentPipleLine;
    private String adjSummaryCurrentPipleLineAccount;
    private String adjSummaryCurrentPipleLineAccountValue;

    private Integer adjSummarydemandPaymentRecon;
    private String adjSummarydemandPaymentReconAccount;
    private String adjSummarydemandPaymentReconAccountValue;

    private Integer adjSummaryCurrentBalance;
    private String adjSummaryCurrentBalanceAccount;
    private String adjSummaryCurrentBalanceAccountValue;

    private String startBalance;
    private String balSummaryAccount;
    private String balSummaryPipelineAccrual;
    private Integer balSummaryPipelineAccrualAdjustmentType;

    private String balSummaryDemandAccrual;
    private Integer balSummaryDemandAccrualAdjustmentType;

    private String balSummaryPipelinelineInventoryTrueup;
    private Integer balSummaryPipelinelineInventoryTrueupAdjustmentType;

    private String balSummaryDemandReforecast;
    private Integer balSummaryDemandReforecastAdjustmentType;

    private String balSummaryDemandTrueup;
    private Integer balSummaryDemandTrueupAdjustmentType;

    private String balSummaryFeesAccrual;
    private Integer balSummaryFeesAccrualAdjustmentType;

    private String balSummaryInflationAdjustment;
    private Integer balSummaryInflationAdjustmentAdjustmentType;

    private String balSummaryCreditCardFees;
    private Integer balSummaryCreditCardFeesAdjustmentType;

    private String balSummaryOtherFixedDollarFees;
    private Integer balSummaryOtherFixedDollarFeesAdjustmentType;

    private String balSummaryInventoryValuation;
    private Integer balSummaryInventoryValuationAdjustmentType;

    private String balSummaryPaymentTrueup;
    private Integer balSummaryPaymentTrueupAdjustmentType;

    private String balSummaryReturnReserve;
    private Integer balSummaryReturnReserveAdjustmentType;

    private int balanceSummarySid;

    private String balSummaryPipelineAccrualAdjustmentTypeDesc;

    private String balSummaryDemandAccrualAdjustmentTypeDesc;

    private String balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc;

    private String balSummaryDemandReforecastAdjustmentTypeDesc;

    private String balSummaryDemandTrueupAdjustmentTypeDesc;

    private String balSummaryFeesAccrualAdjustmentTypeDesc;

    private String balSummaryInflationAdjustmentAdjustmentTypeDesc;

    private String balSummaryCreditCardFeesAdjustmentTypeDesc;

    private String balSummaryOtherFixedDollarFeesAdjustmentTypeDesc;

    private String balSummaryInventoryValuationAdjustmentTypeDesc;

    private String balSummaryPaymentTrueupAdjustmentTypeDesc;

    private String balSummaryReturnReserveAdjustmentTypeDesc;

    private String adjSummaryPipeLineAccrualDesc;
    private String adjSummaryDemandAccrualDesc;
    private String adjSummaryDemandForecastDesc;
    private String adjSummaryCurrentPipleLineDesc;
    private String adjSummarydemandPaymentReconDesc;
    private String adjSummaryCurrentBalanceDesc;

    public AdjustmentReserveDTO(AdjustmentReserveDTO session) {
     this.companyNo = session.getCompanyNo();
     this.companyName= session.getCompanyName();
     this.businessUnitNo= session.getBusinessUnitNo();
     this.businessUnitName= session.getBusinessUnitName();
     this.deductionCategory= session.getDeductionCategory();
     this.deductionType= session.getDeductionType();
     this.deductionProgram= session.getDeductionProgram();
     this.createdBy= session.getCreatedBy();
     this.createdDate= session.getCreatedDate();
     this.modifiedDate= session.getModifiedDate();
     this.source= session.getSource();
     this.searchMasterSid= session.getSearchMasterSid();
     this.checkRecord= session.getCheckRecord();
     this.adjustmentType= session.getAdjustmentType();
     this.debitIndicator= session.getDebitIndicator();
     this.creditIndicator= session.getCreditIndicator();
     this.adjustmentLevel= session.getAdjustmentLevel();
     this.accountCategory= session.getAccountCategory();
     this.accountType= session.getAccountType();
     this.account= session.getAccount();
     this.accountDescription= session.getAccountDescription();
     this.accountIndictor= session.getAccountIndictor();
     this.reportIndicator= session.getReportIndicator();
     this.division= session.getDivision();
     this.businessUnit= session.getBusinessUnit();
     this.costCenter= session.getCostCenter();
     this.project= session.getProject();
     this.future1= session.getFuture1();
     this.future2= session.getFuture2();
     this.balanceType= session.getBalanceType();
     this.database= session.getDatabase();
     this.dataAccessSet= session.getDataAccessSet();
     this.chartOfAccounts= session.getChartOfAccounts();
     this.ledger= session.getLedger();
     this.category= session.getCategory();
     this.currency= session.getCurrency();
     this.journalName= session.getJournalName();
     this.journalDescription= session.getJournalDescription();
     this.reverseJournal= session.getReverseJournal();
     this.reversalPeriodDate= session.getReversalPeriodDate();
     this.lineDescription= session.getLineDescription();
     this.udc1= session.getUdc1();
     this.udc2= session.getUdc2();
     this.udc3= session.getUdc3();
     this.udc4= session.getUdc4();
     this.udc5= session.getUdc5();
     this.udc6= session.getUdc6();
     this.adjustmentTypestr= session.getAdjustmentTypestr();
     this.adjustmentLevelstr= session.getAdjustmentLevelstr();
     this.accountCategorystr= session.getAccountCategorystr();
     this.accountTypestr= session.getAccountTypestr();
     this.accountIndictorstr= session.getAccountIndictorstr();
     this.currencystr= session.getCurrencystr();
     this.reversalPeriodDatestr= session.getReversalPeriodDatestr();
     this.debitIndicatorstr= session.getDebitIndicatorstr();
     this.creditIndicatorstr= session.getCreditIndicatorstr();
     this.udc1Str= session.getUdc1Str();
     this.udc2Str= session.getUdc2Str();
     this.udc3Str= session.getUdc3Str();
     this.udc4Str= session.getUdc4Str();
     this.udc5Str= session.getUdc5Str();
     this.udc6Str= session.getUdc6Str();
     this.reportIndicatorStr= session.getReportIndicatorStr();
     this.gLCompanyNo= session.getgLCompanyNo();
     this.companyDdlbRes= session.getCompanyDdlbRes();
     this.businessDdlbRes= session.getBusinessDdlbRes();
     this.deductionCategoryDdlbRes= session.getDeductionCategoryDdlbRes();
     this.deductionProgramDdlbRes= session.getDeductionProgramDdlbRes();
     this.deductionTypeDdlbRes= session.getDeductionTypeDdlbRes();
     this.detailsId= session.getDetailsId();
     this.adjSummaryAdjustmentType= session.getAdjSummaryAdjustmentType();
     this.adjSummaryAdjustmentTypeId=session.getAdjSummaryAdjustmentTypeId();
     this.adjustmentSummarySid= session.getAdjustmentSummarySid();
     this.versionNo= session.getVersionNo();
     this.methodology= session.getMethodology();
     this.adjSummaryPipeLineAccrual= session.getAdjSummaryPipeLineAccrual();
     this.adjSummaryPipeLineAccrualAccount= session.getAdjSummaryPipeLineAccrualAccount();
     this.adjSummaryPipeLineAccrualAccountValue= session.getAdjSummaryPipeLineAccrualAccountValue();
     this.adjSummaryDemandAccrual= session.getAdjSummaryDemandAccrual();
     this.adjSummaryDemandAccrualAccount= session.getAdjSummaryDemandAccrualAccount();
     this.adjSummaryDemandAccrualAccountValue= session.getAdjSummaryDemandAccrualAccountValue();
     this.adjSummaryDemandForecast= session.getAdjSummaryDemandForecast();
     this.adjSummaryDemandForecastAccount= session.getAdjSummaryDemandForecastAccount();
     this.adjSummaryDemandForecastAccountValue= session.getAdjSummaryDemandForecastAccountValue();
     this.adjSummaryCurrentPipleLine= session.getAdjSummaryCurrentPipleLine();
     this.adjSummaryCurrentPipleLineAccount= session.getAdjSummaryCurrentPipleLineAccount();
     this.adjSummaryCurrentPipleLineAccountValue= session.getAdjSummaryCurrentPipleLineAccountValue();
     this.adjSummarydemandPaymentRecon= session.getAdjSummarydemandPaymentRecon();
     this.adjSummarydemandPaymentReconAccount= session.getAdjSummarydemandPaymentReconAccount();
     this.adjSummarydemandPaymentReconAccountValue= session.getAdjSummarydemandPaymentReconAccountValue();
     this.adjSummaryCurrentBalance= session.getAdjSummaryCurrentBalance();
     this.adjSummaryCurrentBalanceAccount= session.getAdjSummaryCurrentBalanceAccount();
     this.adjSummaryCurrentBalanceAccountValue= session.getAdjSummaryCurrentBalanceAccountValue();
     this.startBalance= session.getStartBalance();
     this.balSummaryAccount= session.getBalSummaryAccount();
     this.balSummaryPipelineAccrual= session.getBalSummaryPipelineAccrual();
     this.balSummaryPipelineAccrualAdjustmentType= session.getBalSummaryPipelineAccrualAdjustmentType();
     this.balSummaryDemandAccrual= session.getBalSummaryDemandAccrual();
     this.balSummaryDemandAccrualAdjustmentType= session.getBalSummaryDemandAccrualAdjustmentType();
     this.balSummaryPipelinelineInventoryTrueup= session.getBalSummaryPipelinelineInventoryTrueup();
     this.balSummaryPipelinelineInventoryTrueupAdjustmentType= session.getBalSummaryPipelinelineInventoryTrueupAdjustmentType();
     this.balSummaryDemandReforecast= session.getBalSummaryDemandReforecast();
     this.balSummaryDemandReforecastAdjustmentType= session.getBalSummaryDemandReforecastAdjustmentType();
     this.balSummaryDemandTrueup= session.getBalSummaryDemandTrueup();
     this.balSummaryDemandTrueupAdjustmentType= session.getBalSummaryDemandTrueupAdjustmentType();
     this.balSummaryFeesAccrual= session.getBalSummaryFeesAccrual();
     this.balSummaryFeesAccrualAdjustmentType= session.getBalSummaryFeesAccrualAdjustmentType();
     this.balSummaryInflationAdjustment= session.getBalSummaryInflationAdjustment();
     this.balSummaryInflationAdjustmentAdjustmentType= session.getBalSummaryInflationAdjustmentAdjustmentType();
     this.balSummaryCreditCardFees= session.getBalSummaryCreditCardFees();
     this.balSummaryCreditCardFeesAdjustmentType= session.getBalSummaryCreditCardFeesAdjustmentType();
     this.balSummaryOtherFixedDollarFees= session.getBalSummaryOtherFixedDollarFees();
     this.balSummaryOtherFixedDollarFeesAdjustmentType= session.getBalSummaryOtherFixedDollarFeesAdjustmentType();
     this.balSummaryInventoryValuation= session.getBalSummaryInventoryValuation();
     this.balSummaryInventoryValuationAdjustmentType= session.getBalSummaryInventoryValuationAdjustmentType();
     this.balSummaryPaymentTrueup= session.getBalSummaryPaymentTrueup();
     this.balSummaryPaymentTrueupAdjustmentType= session.getBalSummaryPaymentTrueupAdjustmentType();
     this.balSummaryReturnReserve= session.getBalSummaryReturnReserve();
     this.balSummaryReturnReserveAdjustmentType= session.getBalSummaryReturnReserveAdjustmentType();
     this.balanceSummarySid= session.getBalanceSummarySid();
     this.balSummaryPipelineAccrualAdjustmentTypeDesc= session.getBalSummaryPipelineAccrualAdjustmentTypeDesc();
     this.balSummaryDemandAccrualAdjustmentTypeDesc= session.getBalSummaryDemandAccrualAdjustmentTypeDesc();
     this.balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc= session.getBalSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc();
     this.balSummaryDemandReforecastAdjustmentTypeDesc= session.getBalSummaryDemandReforecastAdjustmentTypeDesc();
     this.balSummaryDemandTrueupAdjustmentTypeDesc= session.getBalSummaryDemandTrueupAdjustmentTypeDesc();
     this.balSummaryFeesAccrualAdjustmentTypeDesc= session.getBalSummaryFeesAccrualAdjustmentTypeDesc();
     this.balSummaryInflationAdjustmentAdjustmentTypeDesc= session.getBalSummaryInflationAdjustmentAdjustmentTypeDesc();
     this.balSummaryCreditCardFeesAdjustmentTypeDesc= session.getBalSummaryCreditCardFeesAdjustmentTypeDesc();
     this.balSummaryOtherFixedDollarFeesAdjustmentTypeDesc= session.getBalSummaryOtherFixedDollarFeesAdjustmentTypeDesc();
     this.balSummaryInventoryValuationAdjustmentTypeDesc= session.getBalSummaryInventoryValuationAdjustmentTypeDesc();
     this.balSummaryPaymentTrueupAdjustmentTypeDesc= session.getBalSummaryPaymentTrueupAdjustmentTypeDesc();
     this.balSummaryReturnReserveAdjustmentTypeDesc= session.getBalSummaryReturnReserveAdjustmentTypeDesc();
     this.adjSummaryPipeLineAccrualDesc= session.getAdjSummaryPipeLineAccrualDesc();
     this.adjSummaryDemandAccrualDesc= session.getAdjSummaryDemandAccrualDesc();
     this.adjSummaryDemandForecastDesc= session.getAdjSummaryDemandForecastDesc();
     this.adjSummaryCurrentPipleLineDesc= session.getAdjSummaryCurrentPipleLineDesc();
     this.adjSummarydemandPaymentReconDesc= session.getAdjSummarydemandPaymentReconDesc();
     this.adjSummaryCurrentBalanceDesc= session.getAdjSummaryCurrentBalanceDesc();

    }

    public AdjustmentReserveDTO() {
        /*
        Empty COnstructor
        */
    }
    

    public String getAdjSummaryPipeLineAccrualDesc() {
        return adjSummaryPipeLineAccrualDesc;
    }

    public void setAdjSummaryPipeLineAccrualDesc(String adjSummaryPipeLineAccrualDesc) {
        this.adjSummaryPipeLineAccrualDesc = adjSummaryPipeLineAccrualDesc;
    }

    public String getAdjSummaryDemandAccrualDesc() {
        return adjSummaryDemandAccrualDesc;
    }

    public void setAdjSummaryDemandAccrualDesc(String adjSummaryDemandAccrualDesc) {
        this.adjSummaryDemandAccrualDesc = adjSummaryDemandAccrualDesc;
    }

    public String getAdjSummaryDemandForecastDesc() {
        return adjSummaryDemandForecastDesc;
    }

    public void setAdjSummaryDemandForecastDesc(String adjSummaryDemandForecastDesc) {
        this.adjSummaryDemandForecastDesc = adjSummaryDemandForecastDesc;
    }

    public String getAdjSummaryCurrentPipleLineDesc() {
        return adjSummaryCurrentPipleLineDesc;
    }

    public void setAdjSummaryCurrentPipleLineDesc(String adjSummaryCurrentPipleLineDesc) {
        this.adjSummaryCurrentPipleLineDesc = adjSummaryCurrentPipleLineDesc;
    }

    public String getAdjSummarydemandPaymentReconDesc() {
        return adjSummarydemandPaymentReconDesc;
    }

    public void setAdjSummarydemandPaymentReconDesc(String adjSummarydemandPaymentReconDesc) {
        this.adjSummarydemandPaymentReconDesc = adjSummarydemandPaymentReconDesc;
    }

    public String getAdjSummaryCurrentBalanceDesc() {
        return adjSummaryCurrentBalanceDesc;
    }

    public void setAdjSummaryCurrentBalanceDesc(String adjSummaryCurrentBalanceDesc) {
        this.adjSummaryCurrentBalanceDesc = adjSummaryCurrentBalanceDesc;
    }

    public String getAdjSummaryDemandAccrualAccount() {
        return adjSummaryDemandAccrualAccount;
    }

    public void setAdjSummaryDemandAccrualAccount(String adjSummaryDemandAccrualAccount) {
        this.adjSummaryDemandAccrualAccount = adjSummaryDemandAccrualAccount;
    }

    public String getAdjSummaryDemandAccrualAccountValue() {
        return adjSummaryDemandAccrualAccountValue;
    }

    public void setAdjSummaryDemandAccrualAccountValue(String adjSummaryDemandAccrualAccountValue) {
        this.adjSummaryDemandAccrualAccountValue = adjSummaryDemandAccrualAccountValue;
    }

    public int getUdc1() {
        return udc1;
    }

    public void setUdc1(int udc1) {
        this.udc1 = udc1;
    }

    public int getUdc2() {
        return udc2;
    }

    public void setUdc2(int udc2) {
        this.udc2 = udc2;
    }

    public int getUdc3() {
        return udc3;
    }

    public void setUdc3(int udc3) {
        this.udc3 = udc3;
    }

    public int getUdc4() {
        return udc4;
    }

    public void setUdc4(int udc4) {
        this.udc4 = udc4;
    }

    public int getUdc5() {
        return udc5;
    }

    public void setUdc5(int udc5) {
        this.udc5 = udc5;
    }

    public int getUdc6() {
        return udc6;
    }

    public void setUdc6(int udc6) {
        this.udc6 = udc6;
    }

    public String getAdjustmentTypestr() {
        return adjustmentTypestr;
    }

    public void setAdjustmentTypestr(String adjustmentTypestr) {
        this.adjustmentTypestr = adjustmentTypestr;
    }

    public String getAdjustmentLevelstr() {
        return adjustmentLevelstr;
    }

    public void setAdjustmentLevelstr(String adjustmentLevelstr) {
        this.adjustmentLevelstr = adjustmentLevelstr;
    }

    public String getAccountCategorystr() {
        return accountCategorystr;
    }

    public void setAccountCategorystr(String accountCategorystr) {
        this.accountCategorystr = accountCategorystr;
    }

    public String getAccountTypestr() {
        return accountTypestr;
    }

    public void setAccountTypestr(String accountTypestr) {
        this.accountTypestr = accountTypestr;
    }

    public String getAccountIndictorstr() {
        return accountIndictorstr;
    }

    public void setAccountIndictorstr(String accountIndictorstr) {
        this.accountIndictorstr = accountIndictorstr;
    }

    public String getCurrencystr() {
        return currencystr;
    }

    public void setCurrencystr(String currencystr) {
        this.currencystr = currencystr;
    }

    public String getReversalPeriodDatestr() {
        return reversalPeriodDatestr;
    }

    public void setReversalPeriodDatestr(String reversalPeriodDatestr) {
        this.reversalPeriodDatestr = reversalPeriodDatestr;
    }

    public String getDebitIndicatorstr() {
        return debitIndicatorstr;
    }

    public void setDebitIndicatorstr(String debitIndicatorstr) {
        this.debitIndicatorstr = debitIndicatorstr;
    }

    public String getReportIndicatorStr() {
        return reportIndicatorStr;
    }

    public void setReportIndicatorStr(String reportIndicatorStr) {
        this.reportIndicatorStr = reportIndicatorStr;
    }

    public String getCreditIndicatorstr() {
        return creditIndicatorstr;
    }

    public void setCreditIndicatorstr(String creditIndicatorstr) {
        this.creditIndicatorstr = creditIndicatorstr;
    }

    public String getUdc1Str() {
        return udc1Str;
    }

    public void setUdc1Str(String udc1Str) {
        this.udc1Str = udc1Str;
    }

    public String getUdc2Str() {
        return udc2Str;
    }

    public void setUdc2Str(String udc2Str) {
        this.udc2Str = udc2Str;
    }

    public String getUdc3Str() {
        return udc3Str;
    }

    public void setUdc3Str(String udc3Str) {
        this.udc3Str = udc3Str;
    }

    public String getUdc4Str() {
        return udc4Str;
    }

    public void setUdc4Str(String udc4Str) {
        this.udc4Str = udc4Str;
    }

    public String getUdc5Str() {
        return udc5Str;
    }

    public void setUdc5Str(String udc5Str) {
        this.udc5Str = udc5Str;
    }

    public String getUdc6Str() {
        return udc6Str;
    }

    public void setUdc6Str(String udc6Str) {
        this.udc6Str = udc6Str;
    }

    public String getAdjSummaryPipeLineAccrualAccount() {
        return adjSummaryPipeLineAccrualAccount;
    }

    public void setAdjSummaryPipeLineAccrualAccount(String adjSummaryPipeLineAccrualAccount) {
        this.adjSummaryPipeLineAccrualAccount = adjSummaryPipeLineAccrualAccount;
    }

    public String getAdjSummaryPipeLineAccrualAccountValue() {
        return adjSummaryPipeLineAccrualAccountValue;
    }

    public void setAdjSummaryPipeLineAccrualAccountValue(String adjSummaryPipeLineAccrualAccountValue) {
        this.adjSummaryPipeLineAccrualAccountValue = adjSummaryPipeLineAccrualAccountValue;
    }

    public String getAdjSummaryDemandForecastAccount() {
        return adjSummaryDemandForecastAccount;
    }

    public void setAdjSummaryDemandForecastAccount(String adjSummaryDemandForecastAccount) {
        this.adjSummaryDemandForecastAccount = adjSummaryDemandForecastAccount;
    }

    public String getAdjSummaryDemandForecastAccountValue() {
        return adjSummaryDemandForecastAccountValue;
    }

    public void setAdjSummaryDemandForecastAccountValue(String adjSummaryDemandForecastAccountValue) {
        this.adjSummaryDemandForecastAccountValue = adjSummaryDemandForecastAccountValue;
    }

    public String getAdjSummaryCurrentPipleLineAccount() {
        return adjSummaryCurrentPipleLineAccount;
    }

    public void setAdjSummaryCurrentPipleLineAccount(String adjSummaryCurrentPipleLineAccount) {
        this.adjSummaryCurrentPipleLineAccount = adjSummaryCurrentPipleLineAccount;
    }

    public String getAdjSummaryCurrentPipleLineAccountValue() {
        return adjSummaryCurrentPipleLineAccountValue;
    }

    public void setAdjSummaryCurrentPipleLineAccountValue(String adjSummaryCurrentPipleLineAccountValue) {
        this.adjSummaryCurrentPipleLineAccountValue = adjSummaryCurrentPipleLineAccountValue;
    }

    public String getAdjSummarydemandPaymentReconAccount() {
        return adjSummarydemandPaymentReconAccount;
    }

    public void setAdjSummarydemandPaymentReconAccount(String adjSummarydemandPaymentReconAccount) {
        this.adjSummarydemandPaymentReconAccount = adjSummarydemandPaymentReconAccount;
    }

    public String getAdjSummarydemandPaymentReconAccountValue() {
        return adjSummarydemandPaymentReconAccountValue;
    }

    public void setAdjSummarydemandPaymentReconAccountValue(String adjSummarydemandPaymentReconAccountValue) {
        this.adjSummarydemandPaymentReconAccountValue = adjSummarydemandPaymentReconAccountValue;
    }

    public String getAdjSummaryCurrentBalanceAccount() {
        return adjSummaryCurrentBalanceAccount;
    }

    public void setAdjSummaryCurrentBalanceAccount(String adjSummaryCurrentBalanceAccount) {
        this.adjSummaryCurrentBalanceAccount = adjSummaryCurrentBalanceAccount;
    }

    public String getAdjSummaryCurrentBalanceAccountValue() {
        return adjSummaryCurrentBalanceAccountValue;
    }

    public void setAdjSummaryCurrentBalanceAccountValue(String adjSummaryCurrentBalanceAccountValue) {
        this.adjSummaryCurrentBalanceAccountValue = adjSummaryCurrentBalanceAccountValue;
    }

    public int getReportIndicator() {
        return reportIndicator;
    }

    public void setReportIndicator(int reportIndicator) {
        this.reportIndicator = reportIndicator;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    private Map<String, Component> fieldFactoryComponentMap = new HashMap<>();

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public String getBalSummaryAccount() {
        return balSummaryAccount;
    }

    public void setBalSummaryAccount(String balSummaryAccount) {
        this.balSummaryAccount = balSummaryAccount;
    }

    public String getBalSummaryPipelineAccrual() {
        return balSummaryPipelineAccrual;
    }

    public void setBalSummaryPipelineAccrual(String balSummaryPipelineAccrual) {
        this.balSummaryPipelineAccrual = balSummaryPipelineAccrual;
    }

    public String getBalSummaryDemandAccrual() {
        return balSummaryDemandAccrual;
    }

    public void setBalSummaryDemandAccrual(String balSummaryDemandAccrual) {
        this.balSummaryDemandAccrual = balSummaryDemandAccrual;
    }

    public String getBalSummaryPipelinelineInventoryTrueup() {
        return balSummaryPipelinelineInventoryTrueup;
    }

    public void setBalSummaryPipelinelineInventoryTrueup(String balSummaryPipelinelineInventoryTrueup) {
        this.balSummaryPipelinelineInventoryTrueup = balSummaryPipelinelineInventoryTrueup;
    }

    public String getBalSummaryDemandReforecast() {
        return balSummaryDemandReforecast;
    }

    public void setBalSummaryDemandReforecast(String balSummaryDemandReforecast) {
        this.balSummaryDemandReforecast = balSummaryDemandReforecast;
    }

    public String getBalSummaryDemandTrueup() {
        return balSummaryDemandTrueup;
    }

    public void setBalSummaryDemandTrueup(String balSummaryDemandTrueup) {
        this.balSummaryDemandTrueup = balSummaryDemandTrueup;
    }

    public String getBalSummaryFeesAccrual() {
        return balSummaryFeesAccrual;
    }

    public void setBalSummaryFeesAccrual(String balSummaryFeesAccrual) {
        this.balSummaryFeesAccrual = balSummaryFeesAccrual;
    }

    public String getBalSummaryInflationAdjustment() {
        return balSummaryInflationAdjustment;
    }

    public void setBalSummaryInflationAdjustment(String balSummaryInflationAdjustment) {
        this.balSummaryInflationAdjustment = balSummaryInflationAdjustment;
    }

    public String getBalSummaryCreditCardFees() {
        return balSummaryCreditCardFees;
    }

    public void setBalSummaryCreditCardFees(String balSummaryCreditCardFees) {
        this.balSummaryCreditCardFees = balSummaryCreditCardFees;
    }

    public String getBalSummaryOtherFixedDollarFees() {
        return balSummaryOtherFixedDollarFees;
    }

    public void setBalSummaryOtherFixedDollarFees(String balSummaryOtherFixedDollarFees) {
        this.balSummaryOtherFixedDollarFees = balSummaryOtherFixedDollarFees;
    }

    public String getBalSummaryInventoryValuation() {
        return balSummaryInventoryValuation;
    }

    public void setBalSummaryInventoryValuation(String balSummaryInventoryValuation) {
        this.balSummaryInventoryValuation = balSummaryInventoryValuation;
    }

    public String getBalSummaryPaymentTrueup() {
        return balSummaryPaymentTrueup;
    }

    public void setBalSummaryPaymentTrueup(String balSummaryPaymentTrueup) {
        this.balSummaryPaymentTrueup = balSummaryPaymentTrueup;
    }

    public String getBalSummaryReturnReserve() {
        return balSummaryReturnReserve;
    }

    public void setBalSummaryReturnReserve(String balSummaryReturnReserve) {
        this.balSummaryReturnReserve = balSummaryReturnReserve;
    }

    public int getSearchMasterSid() {
        return searchMasterSid;
    }

    public void setSearchMasterSid(int searchMasterSid) {
        this.searchMasterSid = searchMasterSid;
    }

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionProgram() {
        return deductionProgram;
    }

    public void setDeductionProgram(String deductionProgram) {
        this.deductionProgram = deductionProgram;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    //Fields For Add Table Reserve Details
    public Boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(Boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getFuture1() {
        return future1;
    }

    public void setFuture1(String future1) {
        this.future1 = future1;
    }

    public String getFuture2() {
        return future2;
    }

    public void setFuture2(String future2) {
        this.future2 = future2;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDataAccessSet() {
        return dataAccessSet;
    }

    public void setDataAccessSet(String dataAccessSet) {
        this.dataAccessSet = dataAccessSet;
    }

    public String getChartOfAccounts() {
        return chartOfAccounts;
    }

    public void setChartOfAccounts(String chartOfAccounts) {
        this.chartOfAccounts = chartOfAccounts;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getJournalDescription() {
        return journalDescription;
    }

    public void setJournalDescription(String journalDescription) {
        this.journalDescription = journalDescription;
    }

    public String getReverseJournal() {
        return reverseJournal;
    }

    public void setReverseJournal(String reverseJournal) {
        this.reverseJournal = reverseJournal;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    //Extra Fields For GTN Details.
    public String getgLCompanyNo() {
        return gLCompanyNo;
    }

    public void setgLCompanyNo(String gLCompanyNo) {
        this.gLCompanyNo = gLCompanyNo;
    }

    public int getCompanyDdlbRes() {
        return companyDdlbRes;
    }

    public void setCompanyDdlbRes(int companyDdlbRes) {
        this.companyDdlbRes = companyDdlbRes;
    }

    public int getBusinessDdlbRes() {
        return businessDdlbRes;
    }

    public void setBusinessDdlbRes(int businessDdlbRes) {
        this.businessDdlbRes = businessDdlbRes;
    }

    public int getDeductionCategoryDdlbRes() {
        return deductionCategoryDdlbRes;
    }

    public void setDeductionCategoryDdlbRes(int deductionCategoryDdlbRes) {
        this.deductionCategoryDdlbRes = deductionCategoryDdlbRes;
    }

    public int getDeductionProgramDdlbRes() {
        return deductionProgramDdlbRes;
    }

    public void setDeductionProgramDdlbRes(int deductionProgramDdlbRes) {
        this.deductionProgramDdlbRes = deductionProgramDdlbRes;
    }

    public int getDeductionTypeDdlbRes() {
        return deductionTypeDdlbRes;
    }

    public void setDeductionTypeDdlbRes(int deductionTypeDdlbRes) {
        this.deductionTypeDdlbRes = deductionTypeDdlbRes;
    }

    public int getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(int adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public int getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(int accountCategory) {
        this.accountCategory = accountCategory;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getAccountIndictor() {
        return accountIndictor;
    }

    public void setAccountIndictor(int accountIndictor) {
        this.accountIndictor = accountIndictor;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public int getReversalPeriodDate() {
        return reversalPeriodDate;
    }

    public void setReversalPeriodDate(int reversalPeriodDate) {
        this.reversalPeriodDate = reversalPeriodDate;
    }

    public int getAdjustmentLevel() {
        return adjustmentLevel;
    }

    public void setAdjustmentLevel(int adjustmentLevel) {
        this.adjustmentLevel = adjustmentLevel;
    }

    public int getDebitIndicator() {
        return debitIndicator;
    }

    public void setDebitIndicator(int debitIndicator) {
        this.debitIndicator = debitIndicator;
    }

    public int getCreditIndicator() {
        return creditIndicator;
    }

    public void setCreditIndicator(int creditIndicator) {
        this.creditIndicator = creditIndicator;
    }

    public String getAdjSummaryAdjustmentType() {
        return adjSummaryAdjustmentType;
    }

    public void setAdjSummaryAdjustmentType(String adjSummaryAdjustmentType) {
        this.adjSummaryAdjustmentType = adjSummaryAdjustmentType;
    }

    public Integer getAdjSummaryPipeLineAccrual() {
        return adjSummaryPipeLineAccrual;
    }

    public void setAdjSummaryPipeLineAccrual(Integer adjSummaryPipeLineAccrual) {
        this.adjSummaryPipeLineAccrual = adjSummaryPipeLineAccrual;
    }

    public Integer getAdjSummaryDemandAccrual() {
        return adjSummaryDemandAccrual;
    }

    public void setAdjSummaryDemandAccrual(Integer adjSummaryDemandAccrual) {
        this.adjSummaryDemandAccrual = adjSummaryDemandAccrual;
    }

    public Integer getAdjSummaryDemandForecast() {
        return adjSummaryDemandForecast;
    }

    public void setAdjSummaryDemandForecast(Integer adjSummaryDemandForecast) {
        this.adjSummaryDemandForecast = adjSummaryDemandForecast;
    }

    public Integer getAdjSummaryCurrentPipleLine() {
        return adjSummaryCurrentPipleLine;
    }

    public void setAdjSummaryCurrentPipleLine(Integer adjSummaryCurrentPipleLine) {
        this.adjSummaryCurrentPipleLine = adjSummaryCurrentPipleLine;
    }

    public Integer getAdjSummarydemandPaymentRecon() {
        return adjSummarydemandPaymentRecon;
    }

    public void setAdjSummarydemandPaymentRecon(Integer adjSummarydemandPaymentRecon) {
        this.adjSummarydemandPaymentRecon = adjSummarydemandPaymentRecon;
    }

    public Integer getAdjSummaryCurrentBalance() {
        return adjSummaryCurrentBalance;
    }

    public void setAdjSummaryCurrentBalance(Integer adjSummaryCurrentBalance) {
        this.adjSummaryCurrentBalance = adjSummaryCurrentBalance;
    }

    public Map<String, Component> getFieldFactoryComponentMap() {
        return Collections.unmodifiableMap(fieldFactoryComponentMap);
    }

    public Component getFieldFactoryComponent(String propertyId) {
        if (propertyId == null) {
            throw new NullPointerException("Property for getting the component is null");
        }
        return fieldFactoryComponentMap.get(propertyId);
    }

    public void setFieldFactoryComponentMap(Map<String, Component> fieldFactoryComponentMap) {
        this.fieldFactoryComponentMap = fieldFactoryComponentMap;
    }

    public void addFieldFactoryMap(String propertyId, Component value) {
        this.fieldFactoryComponentMap.put(propertyId, value);
    }

    public int getAdjustmentSummarySid() {
        return adjustmentSummarySid;
    }

    public void setAdjustmentSummarySid(int adjustmentSummarySid) {
        this.adjustmentSummarySid = adjustmentSummarySid;
    }

    public int getMethodology() {
        return methodology;
    }

    public void setMethodology(int methodology) {
        this.methodology = methodology;
    }

    public int getAdjSummaryAdjustmentTypeId() {
        return adjSummaryAdjustmentTypeId;
    }

    public void setAdjSummaryAdjustmentTypeId(int adjSummaryAdjustmentTypeId) {
        this.adjSummaryAdjustmentTypeId = adjSummaryAdjustmentTypeId;
    }

    public Integer getBalSummaryPipelineAccrualAdjustmentType() {
        return balSummaryPipelineAccrualAdjustmentType;
    }

    public void setBalSummaryPipelineAccrualAdjustmentType(Integer balSummaryPipelineAccrualAdjustmentType) {
        this.balSummaryPipelineAccrualAdjustmentType = balSummaryPipelineAccrualAdjustmentType;
    }

    public Integer getBalSummaryDemandAccrualAdjustmentType() {
        return balSummaryDemandAccrualAdjustmentType;
    }

    public void setBalSummaryDemandAccrualAdjustmentType(Integer balSummaryDemandAccrualAdjustmentType) {
        this.balSummaryDemandAccrualAdjustmentType = balSummaryDemandAccrualAdjustmentType;
    }

    public Integer getBalSummaryPipelinelineInventoryTrueupAdjustmentType() {
        return balSummaryPipelinelineInventoryTrueupAdjustmentType;
    }

    public void setBalSummaryPipelinelineInventoryTrueupAdjustmentType(Integer balSummaryPipelinelineInventoryTrueupAdjustmentType) {
        this.balSummaryPipelinelineInventoryTrueupAdjustmentType = balSummaryPipelinelineInventoryTrueupAdjustmentType;
    }

    public Integer getBalSummaryDemandReforecastAdjustmentType() {
        return balSummaryDemandReforecastAdjustmentType;
    }

    public void setBalSummaryDemandReforecastAdjustmentType(Integer balSummaryDemandReforecastAdjustmentType) {
        this.balSummaryDemandReforecastAdjustmentType = balSummaryDemandReforecastAdjustmentType;
    }

    public Integer getBalSummaryDemandTrueupAdjustmentType() {
        return balSummaryDemandTrueupAdjustmentType;
    }

    public void setBalSummaryDemandTrueupAdjustmentType(Integer balSummaryDemandTrueupAdjustmentType) {
        this.balSummaryDemandTrueupAdjustmentType = balSummaryDemandTrueupAdjustmentType;
    }

    public Integer getBalSummaryFeesAccrualAdjustmentType() {
        return balSummaryFeesAccrualAdjustmentType;
    }

    public void setBalSummaryFeesAccrualAdjustmentType(Integer balSummaryFeesAccrualAdjustmentType) {
        this.balSummaryFeesAccrualAdjustmentType = balSummaryFeesAccrualAdjustmentType;
    }

    public Integer getBalSummaryInflationAdjustmentAdjustmentType() {
        return balSummaryInflationAdjustmentAdjustmentType;
    }

    public void setBalSummaryInflationAdjustmentAdjustmentType(Integer balSummaryInflationAdjustmentAdjustmentType) {
        this.balSummaryInflationAdjustmentAdjustmentType = balSummaryInflationAdjustmentAdjustmentType;
    }

    public Integer getBalSummaryCreditCardFeesAdjustmentType() {
        return balSummaryCreditCardFeesAdjustmentType;
    }

    public void setBalSummaryCreditCardFeesAdjustmentType(Integer balSummaryCreditCardFeesAdjustmentType) {
        this.balSummaryCreditCardFeesAdjustmentType = balSummaryCreditCardFeesAdjustmentType;
    }

    public Integer getBalSummaryOtherFixedDollarFeesAdjustmentType() {
        return balSummaryOtherFixedDollarFeesAdjustmentType;
    }

    public void setBalSummaryOtherFixedDollarFeesAdjustmentType(Integer balSummaryOtherFixedDollarFeesAdjustmentType) {
        this.balSummaryOtherFixedDollarFeesAdjustmentType = balSummaryOtherFixedDollarFeesAdjustmentType;
    }

    public Integer getBalSummaryInventoryValuationAdjustmentType() {
        return balSummaryInventoryValuationAdjustmentType;
    }

    public void setBalSummaryInventoryValuationAdjustmentType(Integer balSummaryInventoryValuationAdjustmentType) {
        this.balSummaryInventoryValuationAdjustmentType = balSummaryInventoryValuationAdjustmentType;
    }

    public Integer getBalSummaryPaymentTrueupAdjustmentType() {
        return balSummaryPaymentTrueupAdjustmentType;
    }

    public void setBalSummaryPaymentTrueupAdjustmentType(Integer balSummaryPaymentTrueupAdjustmentType) {
        this.balSummaryPaymentTrueupAdjustmentType = balSummaryPaymentTrueupAdjustmentType;
    }

    public Integer getBalSummaryReturnReserveAdjustmentType() {
        return balSummaryReturnReserveAdjustmentType;
    }

    public void setBalSummaryReturnReserveAdjustmentType(Integer balSummaryReturnReserveAdjustmentType) {
        this.balSummaryReturnReserveAdjustmentType = balSummaryReturnReserveAdjustmentType;
    }

    public int getBalanceSummarySid() {
        return balanceSummarySid;
    }

    public void setBalanceSummarySid(int balanceSummarySid) {
        this.balanceSummarySid = balanceSummarySid;
    }

    public String getBalSummaryPipelineAccrualAdjustmentTypeDesc() {
        return balSummaryPipelineAccrualAdjustmentTypeDesc;
    }

    public void setBalSummaryPipelineAccrualAdjustmentTypeDesc(String balSummaryPipelineAccrualAdjustmentTypeDesc) {
        this.balSummaryPipelineAccrualAdjustmentTypeDesc = balSummaryPipelineAccrualAdjustmentTypeDesc;
    }

    public String getBalSummaryDemandAccrualAdjustmentTypeDesc() {
        return balSummaryDemandAccrualAdjustmentTypeDesc;
    }

    public void setBalSummaryDemandAccrualAdjustmentTypeDesc(String balSummaryDemandAccrualAdjustmentTypeDesc) {
        this.balSummaryDemandAccrualAdjustmentTypeDesc = balSummaryDemandAccrualAdjustmentTypeDesc;
    }

    public String getBalSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc() {
        return balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc;
    }

    public void setBalSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc(String balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc) {
        this.balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc = balSummaryPipelinelineInventoryTrueupAdjustmentTypeDesc;
    }

    public String getBalSummaryDemandReforecastAdjustmentTypeDesc() {
        return balSummaryDemandReforecastAdjustmentTypeDesc;
    }

    public void setBalSummaryDemandReforecastAdjustmentTypeDesc(String balSummaryDemandReforecastAdjustmentTypeDesc) {
        this.balSummaryDemandReforecastAdjustmentTypeDesc = balSummaryDemandReforecastAdjustmentTypeDesc;
    }

    public String getBalSummaryDemandTrueupAdjustmentTypeDesc() {
        return balSummaryDemandTrueupAdjustmentTypeDesc;
    }

    public void setBalSummaryDemandTrueupAdjustmentTypeDesc(String balSummaryDemandTrueupAdjustmentTypeDesc) {
        this.balSummaryDemandTrueupAdjustmentTypeDesc = balSummaryDemandTrueupAdjustmentTypeDesc;
    }

    public String getBalSummaryFeesAccrualAdjustmentTypeDesc() {
        return balSummaryFeesAccrualAdjustmentTypeDesc;
    }

    public void setBalSummaryFeesAccrualAdjustmentTypeDesc(String balSummaryFeesAccrualAdjustmentTypeDesc) {
        this.balSummaryFeesAccrualAdjustmentTypeDesc = balSummaryFeesAccrualAdjustmentTypeDesc;
    }

    public String getBalSummaryInflationAdjustmentAdjustmentTypeDesc() {
        return balSummaryInflationAdjustmentAdjustmentTypeDesc;
    }

    public void setBalSummaryInflationAdjustmentAdjustmentTypeDesc(String balSummaryInflationAdjustmentAdjustmentTypeDesc) {
        this.balSummaryInflationAdjustmentAdjustmentTypeDesc = balSummaryInflationAdjustmentAdjustmentTypeDesc;
    }

    public String getBalSummaryCreditCardFeesAdjustmentTypeDesc() {
        return balSummaryCreditCardFeesAdjustmentTypeDesc;
    }

    public void setBalSummaryCreditCardFeesAdjustmentTypeDesc(String balSummaryCreditCardFeesAdjustmentTypeDesc) {
        this.balSummaryCreditCardFeesAdjustmentTypeDesc = balSummaryCreditCardFeesAdjustmentTypeDesc;
    }

    public String getBalSummaryOtherFixedDollarFeesAdjustmentTypeDesc() {
        return balSummaryOtherFixedDollarFeesAdjustmentTypeDesc;
    }

    public void setBalSummaryOtherFixedDollarFeesAdjustmentTypeDesc(String balSummaryOtherFixedDollarFeesAdjustmentTypeDesc) {
        this.balSummaryOtherFixedDollarFeesAdjustmentTypeDesc = balSummaryOtherFixedDollarFeesAdjustmentTypeDesc;
    }

    public String getBalSummaryInventoryValuationAdjustmentTypeDesc() {
        return balSummaryInventoryValuationAdjustmentTypeDesc;
    }

    public void setBalSummaryInventoryValuationAdjustmentTypeDesc(String balSummaryInventoryValuationAdjustmentTypeDesc) {
        this.balSummaryInventoryValuationAdjustmentTypeDesc = balSummaryInventoryValuationAdjustmentTypeDesc;
    }

    public String getBalSummaryPaymentTrueupAdjustmentTypeDesc() {
        return balSummaryPaymentTrueupAdjustmentTypeDesc;
    }

    public void setBalSummaryPaymentTrueupAdjustmentTypeDesc(String balSummaryPaymentTrueupAdjustmentTypeDesc) {
        this.balSummaryPaymentTrueupAdjustmentTypeDesc = balSummaryPaymentTrueupAdjustmentTypeDesc;
    }

    public String getBalSummaryReturnReserveAdjustmentTypeDesc() {
        return balSummaryReturnReserveAdjustmentTypeDesc;
    }

    public void setBalSummaryReturnReserveAdjustmentTypeDesc(String balSummaryReturnReserveAdjustmentTypeDesc) {
        this.balSummaryReturnReserveAdjustmentTypeDesc = balSummaryReturnReserveAdjustmentTypeDesc;
    }

    public String getAccountValueById(String propertyId) {
        if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_PIPELINE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryPipeLineAccrualAccountValue;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryDemandAccrualAccountValue;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_REFORECAST_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryDemandForecastAccountValue;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_PAYMENT_RECON_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummarydemandPaymentReconAccountValue;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_CURRENT_BALANCE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryCurrentBalanceAccountValue;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_CURRENT_PIPELINE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryCurrentPipleLineAccountValue;
        }
        return StringUtils.EMPTY;
    }

    public Integer getBalanceColumnById(String propertyId) {
        if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_PIPELINE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryPipeLineAccrual;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryDemandAccrual;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_REFORECAST_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryDemandForecast;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_DEMAND_PAYMENT_RECON_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummarydemandPaymentRecon;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_CURRENT_BALANCE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryCurrentBalance;
        } else if (ADJUSTMENT_RESERVE_CONSTANTS.ADJ_SUMMARY_CURRENT_PIPELINE_ACCOUNT.getConstant().equals(propertyId)) {
            return this.adjSummaryCurrentPipleLine;
        }
        return 0;
    }

    public AdjustmentReserveDTO getAdjustmentReserveDTO(AdjustmentReserveDTO session)  {
        AdjustmentReserveDTO selection = new AdjustmentReserveDTO(session);
        selection.setCompanyNo(companyNo);
        selection.setCompanyName(companyName);
        selection.setBusinessUnitNo(businessUnitNo);
        selection.setBusinessUnit(businessUnit);
        selection.setBusinessUnitName(businessUnitName);
        selection.setDeductionCategory(deductionCategory);
        selection.setDeductionProgram(deductionProgram);
        selection.setDeductionType(deductionType);
        selection.setCreatedBy(createdBy);
        selection.setCreatedDate(createdDate);
        selection.setModifiedDate(modifiedDate);
        selection.setSource(source);
        selection.setCheckRecord(checkRecord);
        selection.setAdjustmentType(adjustmentType);
        selection.setAccountCategory(accountCategory);
        selection.setAccountType(accountType);
        selection.setAccount(account);
        selection.setAccountDescription(accountDescription);
        selection.setAccountIndictor(accountIndictor);
        selection.setDivision(division);
        selection.setBusinessUnit(businessUnit);
        selection.setCostCenter(costCenter);
        selection.setProject(project);
        selection.setFuture1(future1);
        selection.setFuture2(future2);
        selection.setBalanceType(balanceType);
        selection.setDatabase(database);
        selection.setDataAccessSet(dataAccessSet);
        selection.setChartOfAccounts(chartOfAccounts);
        selection.setLedger(ledger);
        selection.setCategory(category);
        selection.setCurrency(currency);
        selection.setJournalName(journalName);
        selection.setJournalDescription(journalDescription);
        selection.setReverseJournal(reverseJournal);
        selection.setReversalPeriodDate(reversalPeriodDate);
        selection.setLineDescription(lineDescription);
        selection.setUdc1(udc1);
        selection.setUdc2(udc2);
        selection.setUdc3(udc3);
        selection.setUdc4(udc4);
        selection.setUdc5(udc5);
        selection.setUdc6(udc6);
        selection.setCompanyDdlbRes(companyDdlbRes);
        selection.setBusinessDdlbRes(businessDdlbRes);
        selection.setDeductionCategoryDdlbRes(deductionCategoryDdlbRes);
        selection.setDeductionType(deductionType);
        selection.setDeductionProgramDdlbRes(deductionProgramDdlbRes);
        selection.setAccountCategorystr(accountCategorystr);
        selection.setAdjustmentTypestr(adjustmentTypestr);
        selection.setAccountTypestr(accountTypestr);
        selection.setAccountIndictorstr(accountIndictorstr);
        selection.setCurrencystr(currencystr);
        selection.setReversalPeriodDatestr(reversalPeriodDatestr);
        selection.setUdc1Str(udc1Str);
        selection.setUdc2Str(udc2Str);
        selection.setUdc3Str(udc3Str);
        selection.setUdc4Str(udc4Str);
        selection.setUdc5Str(udc5Str);
        selection.setUdc6Str(udc6Str);
        selection.setAdjustmentLevel(adjustmentLevel);
        selection.setDebitIndicator(debitIndicator);
        selection.setCreditIndicator(creditIndicator);
        selection.setAdjustmentLevel(adjustmentLevel);
        selection.setDebitIndicator(debitIndicator);
        selection.setCreditIndicator(creditIndicator);
        selection.setAdjSummaryAdjustmentType(adjSummaryAdjustmentType);
        selection.setAdjSummaryPipeLineAccrual(adjSummaryPipeLineAccrual);
        selection.setAdjSummaryPipeLineAccrualAccount(adjSummaryPipeLineAccrualAccount);
        selection.setAdjSummaryDemandAccrual(adjSummaryDemandAccrual);
        selection.setAdjSummaryDemandAccrualAccount(adjSummaryDemandAccrualAccount);
        selection.setAdjSummaryDemandForecast(adjSummaryDemandForecast);
        selection.setAdjSummaryDemandForecastAccount(adjSummaryDemandForecastAccount);
        selection.setAdjSummaryCurrentPipleLine(adjSummaryCurrentPipleLine);
        selection.setAdjSummaryCurrentPipleLineAccount(adjSummaryCurrentPipleLineAccount);
        selection.setAdjSummarydemandPaymentRecon(adjSummarydemandPaymentRecon);
        selection.setAdjSummarydemandPaymentReconAccount(adjSummarydemandPaymentReconAccount);
        selection.setAdjSummaryCurrentBalance(adjSummaryCurrentBalance);
        selection.setAdjSummaryCurrentBalanceAccount(adjSummaryCurrentBalanceAccount);
        selection.setStartBalance(startBalance);
        selection.setBalSummaryAccount(balSummaryAccount);
        selection.setBalSummaryPipelineAccrual(balSummaryPipelineAccrual);
        selection.setBalSummaryDemandAccrual(balSummaryDemandAccrual);
        selection.setBalSummaryPipelinelineInventoryTrueup(balSummaryPipelinelineInventoryTrueup);
        selection.setBalSummaryDemandReforecast(balSummaryDemandReforecast);
        selection.setBalSummaryDemandTrueup(balSummaryDemandTrueup);
        selection.setBalSummaryFeesAccrual(balSummaryFeesAccrual);
        selection.setBalSummaryInflationAdjustment(balSummaryInflationAdjustment);
        selection.setBalSummaryCreditCardFees(balSummaryCreditCardFees);
        selection.setBalSummaryOtherFixedDollarFees(balSummaryOtherFixedDollarFees);
        selection.setBalSummaryInventoryValuation(balSummaryInventoryValuation);
        selection.setBalSummaryPaymentTrueup(balSummaryPaymentTrueup);
        selection.setBalSummaryReturnReserve(balSummaryReturnReserve);

        return selection;
    }

}
