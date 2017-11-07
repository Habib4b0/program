/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nimisha.Rakesh
 */
public class ArmOutboundDTO {
 private boolean checkRecord= false;
 private String adjustmentType = StringUtils.EMPTY;
 private String glCompanyName = StringUtils.EMPTY;
 private String division = StringUtils.EMPTY;
 private String businessUnitId = StringUtils.EMPTY;
 private String journalName = StringUtils.EMPTY;
 private String journalDescription = StringUtils.EMPTY;
 private String brand = StringUtils.EMPTY;
 private String brandName = StringUtils.EMPTY;
 private String credit = StringUtils.EMPTY;
 private String debit = StringUtils.EMPTY;
 private String currency = StringUtils.EMPTY;
 private Date accountingDate;
 private String costCenter = StringUtils.EMPTY;
 private String project = StringUtils.EMPTY;
 private String future1 = StringUtils.EMPTY;
 private String future2 = StringUtils.EMPTY;
 private String category = StringUtils.EMPTY;
 private String balanceType = StringUtils.EMPTY;
 private String database = StringUtils.EMPTY;
 private String dataAccessSet = StringUtils.EMPTY;
 private String chartOfAccounts = StringUtils.EMPTY;
 private String ledger = StringUtils.EMPTY;
 private String source = StringUtils.EMPTY;
 private String batchName = StringUtils.EMPTY;
 private String batchId = StringUtils.EMPTY;
 private String reverseJournal = StringUtils.EMPTY;
 private Date reversalPeriodDate;
 private String lineDescription = StringUtils.EMPTY;
 private String udc1 = StringUtils.EMPTY;
 private String udc2 = StringUtils.EMPTY;
 private String udc3 = StringUtils.EMPTY;
 private String udc4 = StringUtils.EMPTY;
 private String udc5 = StringUtils.EMPTY;
 private String udc6 = StringUtils.EMPTY;
 private String deductionUdc6 = StringUtils.EMPTY;
 private String deductionUdc5 = StringUtils.EMPTY;
 private String deductionUdc4 = StringUtils.EMPTY;
 private String deductionUdc3 = StringUtils.EMPTY;
 private String deductionUdc2 = StringUtils.EMPTY;
 private String deductionUdc1 = StringUtils.EMPTY;
 private String deductionInclusion = StringUtils.EMPTY;
 private String deductionProgram = StringUtils.EMPTY;
 private String deductionType = StringUtils.EMPTY;
 private String deductionCategory = StringUtils.EMPTY;
 private String deductionName = StringUtils.EMPTY;
 private String deductionNo = StringUtils.EMPTY;
 private String deductionId = StringUtils.EMPTY;
 private String brandId = StringUtils.EMPTY;
 private String itemId = StringUtils.EMPTY;
 private String itemNo = StringUtils.EMPTY;
 private String itemName = StringUtils.EMPTY;
 private String companyName = StringUtils.EMPTY;
 private String companyNo = StringUtils.EMPTY;
 private String companyId = StringUtils.EMPTY;
 private String contractNo = StringUtils.EMPTY;
 private String contractName = StringUtils.EMPTY;
 private String contractId = StringUtils.EMPTY;
 private String deductionRate = StringUtils.EMPTY;
 private String deductionAmount = StringUtils.EMPTY;
 private int glMonth = 0;
 private String postingIndicator =StringUtils.EMPTY;
 private Date glDate ;
 private Date redemptionPeriod;
 private int glYear = 0;
 private String businessUnitName = StringUtils.EMPTY;
 private String businessUnitNo = StringUtils.EMPTY;
 private String glCompanyNo = StringUtils.EMPTY;
 private String glCompanyId = StringUtils.EMPTY;
 private String accountType = StringUtils.EMPTY;
 private String account = StringUtils.EMPTY;
 private String adjustmentLevel = StringUtils.EMPTY;
 private String accountCategory = StringUtils.EMPTY;
 private Date workflowApprovedDate;
 private String workflowApprovedBy = StringUtils.EMPTY;
 private String workflowId= StringUtils.EMPTY;
 private String workflowName = StringUtils.EMPTY;
 private Date workflowCreatedDate;
 private String workflowCreatedBy = StringUtils.EMPTY;
 private String originalBatchId = StringUtils.EMPTY;
 private Date adjustmentCreatedDate;
 private Date calculationPeriod;

    public boolean isCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public String getGlCompanyName() {
        return glCompanyName;
    }

    public void setGlCompanyName(String glCompanyName) {
        this.glCompanyName = glCompanyName;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(String businessUnitId) {
        this.businessUnitId = businessUnitId;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getUdc1() {
        return udc1;
    }

    public void setUdc1(String udc1) {
        this.udc1 = udc1;
    }

    public String getUdc2() {
        return udc2;
    }

    public void setUdc2(String udc2) {
        this.udc2 = udc2;
    }

    public String getUdc3() {
        return udc3;
    }

    public void setUdc3(String udc3) {
        this.udc3 = udc3;
    }

    public String getUdc4() {
        return udc4;
    }

    public void setUdc4(String udc4) {
        this.udc4 = udc4;
    }

    public String getUdc5() {
        return udc5;
    }

    public void setUdc5(String udc5) {
        this.udc5 = udc5;
    }

    public String getUdc6() {
        return udc6;
    }

    public void setUdc6(String udc6) {
        this.udc6 = udc6;
    }

    public String getDeductionUdc6() {
        return deductionUdc6;
    }

    public void setDeductionUdc6(String deductionUdc6) {
        this.deductionUdc6 = deductionUdc6;
    }

    public String getDeductionUdc5() {
        return deductionUdc5;
    }

    public void setDeductionUdc5(String deductionUdc5) {
        this.deductionUdc5 = deductionUdc5;
    }

    public String getDeductionUdc4() {
        return deductionUdc4;
    }

    public void setDeductionUdc4(String deductionUdc4) {
        this.deductionUdc4 = deductionUdc4;
    }

    public String getDeductionUdc3() {
        return deductionUdc3;
    }

    public void setDeductionUdc3(String deductionUdc3) {
        this.deductionUdc3 = deductionUdc3;
    }

    public String getDeductionUdc2() {
        return deductionUdc2;
    }

    public void setDeductionUdc2(String deductionUdc2) {
        this.deductionUdc2 = deductionUdc2;
    }

    public String getDeductionUdc1() {
        return deductionUdc1;
    }

    public void setDeductionUdc1(String deductionUdc1) {
        this.deductionUdc1 = deductionUdc1;
    }

    public String getDeductionInclusion() {
        return deductionInclusion;
    }

    public void setDeductionInclusion(String deductionInclusion) {
        this.deductionInclusion = deductionInclusion;
    }

    public String getDeductionProgram() {
        return deductionProgram;
    }

    public void setDeductionProgram(String deductionProgram) {
        this.deductionProgram = deductionProgram;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionCategory() {
        return deductionCategory;
    }

    public void setDeductionCategory(String deductionCategory) {
        this.deductionCategory = deductionCategory;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public String getDeductionNo() {
        return deductionNo;
    }

    public void setDeductionNo(String deductionNo) {
        this.deductionNo = deductionNo;
    }

    public String getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(String deductionId) {
        this.deductionId = deductionId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(String deductionRate) {
        this.deductionRate = deductionRate;
    }

    public String getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(String deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public int getGlMonth() {
        return glMonth;
    }

    public void setGlMonth(int glMonth) {
        this.glMonth = glMonth;
    }

    public int getGlYear() {
        return glYear;
    }

    public void setGlYear(int glYear) {
        this.glYear = glYear;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getGlCompanyNo() {
        return glCompanyNo;
    }

    public void setGlCompanyNo(String glCompanyNo) {
        this.glCompanyNo = glCompanyNo;
    }

    public String getGlCompanyId() {
        return glCompanyId;
    }

    public void setGlCompanyId(String glCompanyId) {
        this.glCompanyId = glCompanyId;
    }

    public String getAdjustmentType() {
        return adjustmentType;
    }

    public void setAdjustmentType(String adjustmentType) {
        this.adjustmentType = adjustmentType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAdjustmentLevel() {
        return adjustmentLevel;
    }

    public void setAdjustmentLevel(String adjustmentLevel) {
        this.adjustmentLevel = adjustmentLevel;
    }

    public String getAccountCategory() {
        return accountCategory;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    public String getWorkflowApprovedBy() {
        return workflowApprovedBy;
    }

    public void setWorkflowApprovedBy(String workflowApprovedBy) {
        this.workflowApprovedBy = workflowApprovedBy;
    }

    public String getWorkflowCreatedBy() {
        return workflowCreatedBy;
    }

    public void setWorkflowCreatedBy(String workflowCreatedBy) {
        this.workflowCreatedBy = workflowCreatedBy;
    }

    public String getOriginalBatchId() {
        return originalBatchId;
    }

    public void setOriginalBatchId(String originalBatchId) {
        this.originalBatchId = originalBatchId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }
    
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(String postingIndicator) {
        this.postingIndicator = postingIndicator;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getReverseJournal() {
        return reverseJournal;
    }

    public void setReverseJournal(String reverseJournal) {
        this.reverseJournal = reverseJournal;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public Date getRedemptionPeriod() {
        return redemptionPeriod;
    }

    public void setRedemptionPeriod(Date redemptionPeriod) {
        this.redemptionPeriod = redemptionPeriod;
    }

    public Date getReversalPeriodDate() {
        return reversalPeriodDate;
    }

    public void setReversalPeriodDate(Date reversalPeriodDate) {
        this.reversalPeriodDate = reversalPeriodDate;
    }

    public Date getAdjustmentCreatedDate() {
        return adjustmentCreatedDate;
    }

    public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
        this.adjustmentCreatedDate = adjustmentCreatedDate;
    }

    public Date getWorkflowApprovedDate() {
        return workflowApprovedDate;
    }

    public void setWorkflowApprovedDate(Date workflowApprovedDate) {
        this.workflowApprovedDate = workflowApprovedDate;
    }

    public Date getWorkflowCreatedDate() {
        return workflowCreatedDate;
    }

    public void setWorkflowCreatedDate(Date workflowCreatedDate) {
        this.workflowCreatedDate = workflowCreatedDate;
    }

    public Date getCalculationPeriod() {
        return calculationPeriod;
    }

    public void setCalculationPeriod(Date calculationPeriod) {
        this.calculationPeriod = calculationPeriod;
    }
 
 
}
