/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author 
 */
public class AdjustmentReserveDTO implements Cloneable {

    /**
     * Fields For Search Table
     */
    public String companyNo = StringUtils.EMPTY;
    String companyName;
    String businessUnitNo;
    String businessUnitName;
    String deductionCategory;
    String deductionType;
    String deductionProgram;
    String createdBy;
    Date createdDate;
    Date modifiedDate;
    String source = StringUtils.EMPTY;
    int searchMasterSid;

    /**
     * Fields For Add Table Reserve Detail
     */
    Boolean checkRecord = Boolean.FALSE;
    int adjustmentType;
    int debitIndicator;
    int creditIndicator;
    int adjustmentLevel;
    int accountCategory;
    int accountType;
    String account = StringUtils.EMPTY;
    String accountDescription = StringUtils.EMPTY;
    int accountIndictor;
    String division = StringUtils.EMPTY;
    String businessUnit = StringUtils.EMPTY;
    String costCenter = StringUtils.EMPTY;
    String project = StringUtils.EMPTY;
    String future1 = StringUtils.EMPTY;
    String future2 = StringUtils.EMPTY;
    String balanceType = StringUtils.EMPTY;
    String database = StringUtils.EMPTY;
    String dataAccessSet = StringUtils.EMPTY;
    String chartOfAccounts = StringUtils.EMPTY;
    String ledger = StringUtils.EMPTY;
    String category = StringUtils.EMPTY;
    int currency;
    String journalName = StringUtils.EMPTY;
    String journalDescription = StringUtils.EMPTY;
    String reverseJournal = StringUtils.EMPTY;
    int reversalPeriodDate;
    String lineDescription = StringUtils.EMPTY;
    int UDC1;
    int UDC2;
    int UDC3;
    int UDC4;
    int UDC5;
    int UDC6;

    String adjustmentType_str;
    String adjustmentLevel_str;
    String accountCategory_str;
    String accountType_str;
    String accountIndictor_str;
    String currency_str;
    String reversalPeriodDate_str;
    String debitIndicator_str;
    String creditIndicator_str;
    String UDC1_str;
    String UDC2_str;
    String UDC3_str;
    String UDC4_str;
    String UDC5_str;
    String UDC6_str;
    /**
     * Extra Fields For Add Table GTN Detail
     */
    public String gLCompanyNo;

    /**
     * Fields For binder in search screen
     */
    int companyDdlbRes;
    int businessDdlbRes;
    int deductionCategoryDdlbRes;
    int deductionProgramDdlbRes;
    int deductionTypeDdlbRes;

    /**
     *
     * Fields for identity.
     */
    int detailsId;

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

    public int getUDC1() {
        return UDC1;
    }

    public void setUDC1(int UDC1) {
        this.UDC1 = UDC1;
    }

    public int getUDC2() {
        return UDC2;
    }

    public void setUDC2(int UDC2) {
        this.UDC2 = UDC2;
    }

    public int getUDC3() {
        return UDC3;
    }

    public void setUDC3(int UDC3) {
        this.UDC3 = UDC3;
    }

    public int getUDC4() {
        return UDC4;
    }

    public void setUDC4(int UDC4) {
        this.UDC4 = UDC4;
    }

    public int getUDC5() {
        return UDC5;
    }

    public void setUDC5(int UDC5) {
        this.UDC5 = UDC5;
    }

    public int getUDC6() {
        return UDC6;
    }

    public void setUDC6(int UDC6) {
        this.UDC6 = UDC6;
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

    public String getAdjustmentType_str() {
        return adjustmentType_str;
    }

    public void setAdjustmentType_str(String adjustmentType_str) {
        this.adjustmentType_str = adjustmentType_str;
    }

    public String getAdjustmentLevel_str() {
        return adjustmentLevel_str;
    }

    public void setAdjustmentLevel_str(String adjustmentLevel_str) {
        this.adjustmentLevel_str = adjustmentLevel_str;
    }

    public String getAccountCategory_str() {
        return accountCategory_str;
    }

    public void setAccountCategory_str(String accountCategory_str) {
        this.accountCategory_str = accountCategory_str;
    }

    public String getAccountType_str() {
        return accountType_str;
    }

    public void setAccountType_str(String accountType_str) {
        this.accountType_str = accountType_str;
    }

    public String getAccountIndictor_str() {
        return accountIndictor_str;
    }

    public void setAccountIndictor_str(String accountIndictor_str) {
        this.accountIndictor_str = accountIndictor_str;
    }

    public String getCurrency_str() {
        return currency_str;
    }

    public void setCurrency_str(String currency_str) {
        this.currency_str = currency_str;
    }

    public String getReversalPeriodDate_str() {
        return reversalPeriodDate_str;
    }

    public void setReversalPeriodDate_str(String reversalPeriodDate_str) {
        this.reversalPeriodDate_str = reversalPeriodDate_str;
    }

    public String getUDC1_str() {
        return UDC1_str;
    }

    public void setUDC1_str(String UDC1_str) {
        this.UDC1_str = UDC1_str;
    }

    public String getUDC2_str() {
        return UDC2_str;
    }

    public void setUDC2_str(String UDC2_str) {
        this.UDC2_str = UDC2_str;
    }

    public String getUDC3_str() {
        return UDC3_str;
    }

    public void setUDC3_str(String UDC3_str) {
        this.UDC3_str = UDC3_str;
    }

    public String getUDC4_str() {
        return UDC4_str;
    }

    public void setUDC4_str(String UDC4_str) {
        this.UDC4_str = UDC4_str;
    }

    public String getUDC5_str() {
        return UDC5_str;
    }

    public void setUDC5_str(String UDC5_str) {
        this.UDC5_str = UDC5_str;
    }

    public String getUDC6_str() {
        return UDC6_str;
    }

    public void setUDC6_str(String UDC6_str) {
        this.UDC6_str = UDC6_str;
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

    public String getDebitIndicator_str() {
        return debitIndicator_str;
    }

    public void setDebitIndicator_str(String debitIndicator_str) {
        this.debitIndicator_str = debitIndicator_str;
    }

    public String getCreditIndicator_str() {
        return creditIndicator_str;
    }

    public void setCreditIndicator_str(String creditIndicator_str) {
        this.creditIndicator_str = creditIndicator_str;
    }
    

    @Override
    public AdjustmentReserveDTO clone() throws CloneNotSupportedException {
        AdjustmentReserveDTO selection = (AdjustmentReserveDTO) super.clone();
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
        selection.setUDC1(UDC1);
        selection.setUDC2(UDC2);
        selection.setUDC3(UDC3);
        selection.setUDC4(UDC4);
        selection.setUDC5(UDC5);
        selection.setUDC6(UDC6);
        selection.setCompanyDdlbRes(companyDdlbRes);
        selection.setBusinessDdlbRes(businessDdlbRes);
        selection.setDeductionCategoryDdlbRes(deductionCategoryDdlbRes);
        selection.setDeductionType(deductionType);
        selection.setDeductionProgramDdlbRes(deductionProgramDdlbRes);
        selection.setAccountCategory_str(accountCategory_str);
        selection.setAdjustmentType_str(adjustmentType_str);
        selection.setAccountType_str(accountType_str);
        selection.setAccountIndictor_str(accountIndictor_str);
        selection.setCurrency_str(currency_str);
        selection.setReversalPeriodDate_str(reversalPeriodDate_str);
        selection.setUDC1_str(UDC1_str);
        selection.setUDC2_str(UDC2_str);
        selection.setUDC3_str(UDC3_str);
        selection.setUDC4_str(UDC4_str);
        selection.setUDC5_str(UDC5_str);
        selection.setUDC6_str(UDC6_str);
        selection.setAdjustmentLevel(adjustmentLevel);
        selection.setDebitIndicator(debitIndicator);
        selection.setCreditIndicator(creditIndicator);

        return selection;
    }

}
