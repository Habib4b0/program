/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.entity.transaction;

import java.util.Date;

import com.stpl.gtn.gtn2o.ws.entity.HelperTable;

/**
 *
 * @author Asha.Ravi
 */
@SuppressWarnings("serial")
public class AccrualDetails implements java.io.Serializable {

    private int accrualDetailsSid;
    private String accountId;
    private Date recordCreatedDate;
    private String postingIndicator;
    private String brandName;
    private Date accrualPeriodEndDate;
    private Date modifiedDate;
    private String salesMasterId;
    private String source;
    private int contractMasterSid;
    private String documentType;
    private String inboundStatus;
    private String modifiedBy;
    private String deductionType;
    private int companyMasterSid;
    private String contractName;
    private String accountNo;
    private String accountName;
    private int versionNo;
    private String provisionId;
    private String companyIdentifierCodeQualifier;
    private Double amount;
    private String subLedger;
    private boolean recordLockStatus;
    private String itemIdentifierCodeQualifier;
    private String companyNo;
    private String postingStatus;
    private String itemName;
    private int rsModelSid;
    private int itemMasterSid;
    private String itemId;
    private int brandMasterSid;
    private int glCompanyMasterSid;
    private String createdBy;
    private Date createdDate;
    private Date accrualPeriodStartDate;
    private String subLedgerType;
    private String programNo;
    private String categoryId;
    private String itemNo;
    private String deductionSubType;
    private String acctIdentifierCodeQualifier;
    private String contractId;
    private String accrualId;
    private String companyId;
    private String accrualType;
    private String brandId;
    private Date postingDate;
    private Date glDate;
    private String companyCostCenter;
    private String glAccount;
    private String batchId;
    private int programType;
    private String contractNo;
    private HelperTable accrualTypeSid;

    public int getAccrualDetailsSid() {
        return accrualDetailsSid;
    }

    public void setAccrualDetailsSid(int accrualDetailsSid) {
        this.accrualDetailsSid = accrualDetailsSid;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getRecordCreatedDate() {
        return recordCreatedDate;
    }

    public void setRecordCreatedDate(Date recordCreatedDate) {
        this.recordCreatedDate = recordCreatedDate;
    }

    public String getPostingIndicator() {
        return postingIndicator;
    }

    public void setPostingIndicator(String postingIndicator) {
        this.postingIndicator = postingIndicator;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Date getAccrualPeriodEndDate() {
        return accrualPeriodEndDate;
    }

    public void setAccrualPeriodEndDate(Date accrualPeriodEndDate) {
        this.accrualPeriodEndDate = accrualPeriodEndDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSalesMasterId() {
        return salesMasterId;
    }

    public void setSalesMasterId(String salesMasterId) {
        this.salesMasterId = salesMasterId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getContractMasterSid() {
        return contractMasterSid;
    }

    public void setContractMasterSid(int contractMasterSid) {
        this.contractMasterSid = contractMasterSid;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getInboundStatus() {
        return inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        this.inboundStatus = inboundStatus;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public int getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(int companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    public String getProvisionId() {
        return provisionId;
    }

    public void setProvisionId(String provisionId) {
        this.provisionId = provisionId;
    }

    public String getCompanyIdentifierCodeQualifier() {
        return companyIdentifierCodeQualifier;
    }

    public void setCompanyIdentifierCodeQualifier(String companyIdentifierCodeQualifier) {
        this.companyIdentifierCodeQualifier = companyIdentifierCodeQualifier;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSubLedger() {
        return subLedger;
    }

    public void setSubLedger(String subLedger) {
        this.subLedger = subLedger;
    }

    public boolean isRecordLockStatus() {
        return recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        this.recordLockStatus = recordLockStatus;
    }

    public String getItemIdentifierCodeQualifier() {
        return itemIdentifierCodeQualifier;
    }

    public void setItemIdentifierCodeQualifier(String itemIdentifierCodeQualifier) {
        this.itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getPostingStatus() {
        return postingStatus;
    }

    public void setPostingStatus(String postingStatus) {
        this.postingStatus = postingStatus;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getRsModelSid() {
        return rsModelSid;
    }

    public void setRsModelSid(int rsModelSid) {
        this.rsModelSid = rsModelSid;
    }

    public int getItemMasterSid() {
        return itemMasterSid;
    }

    public void setItemMasterSid(int itemMasterSid) {
        this.itemMasterSid = itemMasterSid;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getBrandMasterSid() {
        return brandMasterSid;
    }

    public void setBrandMasterSid(int brandMasterSid) {
        this.brandMasterSid = brandMasterSid;
    }

    public int getGlCompanyMasterSid() {
        return glCompanyMasterSid;
    }

    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        this.glCompanyMasterSid = glCompanyMasterSid;
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

    public Date getAccrualPeriodStartDate() {
        return accrualPeriodStartDate;
    }

    public void setAccrualPeriodStartDate(Date accrualPeriodStartDate) {
        this.accrualPeriodStartDate = accrualPeriodStartDate;
    }

    public String getSubLedgerType() {
        return subLedgerType;
    }

    public void setSubLedgerType(String subLedgerType) {
        this.subLedgerType = subLedgerType;
    }

    public String getProgramNo() {
        return programNo;
    }

    public void setProgramNo(String programNo) {
        this.programNo = programNo;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getDeductionSubType() {
        return deductionSubType;
    }

    public void setDeductionSubType(String deductionSubType) {
        this.deductionSubType = deductionSubType;
    }

    public String getAcctIdentifierCodeQualifier() {
        return acctIdentifierCodeQualifier;
    }

    public void setAcctIdentifierCodeQualifier(String acctIdentifierCodeQualifier) {
        this.acctIdentifierCodeQualifier = acctIdentifierCodeQualifier;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getAccrualId() {
        return accrualId;
    }

    public void setAccrualId(String accrualId) {
        this.accrualId = accrualId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAccrualType() {
        return accrualType;
    }

    public void setAccrualType(String accrualType) {
        this.accrualType = accrualType;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Date getGlDate() {
        return glDate;
    }

    public void setGlDate(Date glDate) {
        this.glDate = glDate;
    }

    public String getCompanyCostCenter() {
        return companyCostCenter;
    }

    public void setCompanyCostCenter(String companyCostCenter) {
        this.companyCostCenter = companyCostCenter;
    }

    public String getGlAccount() {
        return glAccount;
    }

    public void setGlAccount(String glAccount) {
        this.glAccount = glAccount;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getProgramType() {
        return programType;
    }

    public void setProgramType(int programType) {
        this.programType = programType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public HelperTable getAccrualTypeSid() {
        return accrualTypeSid;
    }

    public void setAccrualTypeSid(HelperTable accrualTypeSid) {
        this.accrualTypeSid = accrualTypeSid;
    }

}
