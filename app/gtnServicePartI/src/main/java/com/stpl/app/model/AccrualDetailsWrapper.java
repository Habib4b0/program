package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccrualDetails}.
 * </p>
 *
 * @author
 * @see AccrualDetails
 * @generated
 */
public class AccrualDetailsWrapper implements AccrualDetails,
    ModelWrapper<AccrualDetails> {
    private AccrualDetails _accrualDetails;

    public AccrualDetailsWrapper(AccrualDetails accrualDetails) {
        _accrualDetails = accrualDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return AccrualDetails.class;
    }

    @Override
    public String getModelClassName() {
        return AccrualDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accountId", getAccountId());
        attributes.put("recordCreatedDate", getRecordCreatedDate());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("brandName", getBrandName());
        attributes.put("accrualPeriodEndDate", getAccrualPeriodEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("salesMasterId", getSalesMasterId());
        attributes.put("source", getSource());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("accrualDetailsSid", getAccrualDetailsSid());
        attributes.put("documentType", getDocumentType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionType", getDeductionType());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("accountNo", getAccountNo());
        attributes.put("accountName", getAccountName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("provisionId", getProvisionId());
        attributes.put("companyIdentifierCodeQualifier",
            getCompanyIdentifierCodeQualifier());
        attributes.put("amount", getAmount());
        attributes.put("subLedger", getSubLedger());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("postingStatus", getPostingStatus());
        attributes.put("itemName", getItemName());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("programNo", getProgramNo());
        attributes.put("categoryId", getCategoryId());
        attributes.put("itemNo", getItemNo());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyId", getCompanyId());
        attributes.put("accrualType", getAccrualType());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("glDate", getGlDate());
        attributes.put("companyCostCenter", getCompanyCostCenter());
        attributes.put("glAccount", getGlAccount());
        attributes.put("batchId", getBatchId());
        attributes.put("programType", getProgramType());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date recordCreatedDate = (Date) attributes.get("recordCreatedDate");

        if (recordCreatedDate != null) {
            setRecordCreatedDate(recordCreatedDate);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date accrualPeriodEndDate = (Date) attributes.get(
                "accrualPeriodEndDate");

        if (accrualPeriodEndDate != null) {
            setAccrualPeriodEndDate(accrualPeriodEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String salesMasterId = (String) attributes.get("salesMasterId");

        if (salesMasterId != null) {
            setSalesMasterId(salesMasterId);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer accrualDetailsSid = (Integer) attributes.get(
                "accrualDetailsSid");

        if (accrualDetailsSid != null) {
            setAccrualDetailsSid(accrualDetailsSid);
        }

        String documentType = (String) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String companyIdentifierCodeQualifier = (String) attributes.get(
                "companyIdentifierCodeQualifier");

        if (companyIdentifierCodeQualifier != null) {
            setCompanyIdentifierCodeQualifier(companyIdentifierCodeQualifier);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String subLedger = (String) attributes.get("subLedger");

        if (subLedger != null) {
            setSubLedger(subLedger);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String postingStatus = (String) attributes.get("postingStatus");

        if (postingStatus != null) {
            setPostingStatus(postingStatus);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer brandMasterSid = (Integer) attributes.get("brandMasterSid");

        if (brandMasterSid != null) {
            setBrandMasterSid(brandMasterSid);
        }

        String glCompanyMasterSid = (String) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date accrualPeriodStartDate = (Date) attributes.get(
                "accrualPeriodStartDate");

        if (accrualPeriodStartDate != null) {
            setAccrualPeriodStartDate(accrualPeriodStartDate);
        }

        String subLedgerType = (String) attributes.get("subLedgerType");

        if (subLedgerType != null) {
            setSubLedgerType(subLedgerType);
        }

        String programNo = (String) attributes.get("programNo");

        if (programNo != null) {
            setProgramNo(programNo);
        }

        String categoryId = (String) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String accrualId = (String) attributes.get("accrualId");

        if (accrualId != null) {
            setAccrualId(accrualId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String accrualType = (String) attributes.get("accrualType");

        if (accrualType != null) {
            setAccrualType(accrualType);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Date postingDate = (Date) attributes.get("postingDate");

        if (postingDate != null) {
            setPostingDate(postingDate);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
        }

        String glAccount = (String) attributes.get("glAccount");

        if (glAccount != null) {
            setGlAccount(glAccount);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer programType = (Integer) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this accrual details.
    *
    * @return the primary key of this accrual details
    */
    @Override
    public int getPrimaryKey() {
        return _accrualDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this accrual details.
    *
    * @param primaryKey the primary key of this accrual details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _accrualDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the account ID of this accrual details.
    *
    * @return the account ID of this accrual details
    */
    @Override
    public java.lang.String getAccountId() {
        return _accrualDetails.getAccountId();
    }

    /**
    * Sets the account ID of this accrual details.
    *
    * @param accountId the account ID of this accrual details
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _accrualDetails.setAccountId(accountId);
    }

    /**
    * Returns the record created date of this accrual details.
    *
    * @return the record created date of this accrual details
    */
    @Override
    public java.util.Date getRecordCreatedDate() {
        return _accrualDetails.getRecordCreatedDate();
    }

    /**
    * Sets the record created date of this accrual details.
    *
    * @param recordCreatedDate the record created date of this accrual details
    */
    @Override
    public void setRecordCreatedDate(java.util.Date recordCreatedDate) {
        _accrualDetails.setRecordCreatedDate(recordCreatedDate);
    }

    /**
    * Returns the posting indicator of this accrual details.
    *
    * @return the posting indicator of this accrual details
    */
    @Override
    public java.lang.String getPostingIndicator() {
        return _accrualDetails.getPostingIndicator();
    }

    /**
    * Sets the posting indicator of this accrual details.
    *
    * @param postingIndicator the posting indicator of this accrual details
    */
    @Override
    public void setPostingIndicator(java.lang.String postingIndicator) {
        _accrualDetails.setPostingIndicator(postingIndicator);
    }

    /**
    * Returns the brand name of this accrual details.
    *
    * @return the brand name of this accrual details
    */
    @Override
    public java.lang.String getBrandName() {
        return _accrualDetails.getBrandName();
    }

    /**
    * Sets the brand name of this accrual details.
    *
    * @param brandName the brand name of this accrual details
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _accrualDetails.setBrandName(brandName);
    }

    /**
    * Returns the accrual period end date of this accrual details.
    *
    * @return the accrual period end date of this accrual details
    */
    @Override
    public java.util.Date getAccrualPeriodEndDate() {
        return _accrualDetails.getAccrualPeriodEndDate();
    }

    /**
    * Sets the accrual period end date of this accrual details.
    *
    * @param accrualPeriodEndDate the accrual period end date of this accrual details
    */
    @Override
    public void setAccrualPeriodEndDate(java.util.Date accrualPeriodEndDate) {
        _accrualDetails.setAccrualPeriodEndDate(accrualPeriodEndDate);
    }

    /**
    * Returns the modified date of this accrual details.
    *
    * @return the modified date of this accrual details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _accrualDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this accrual details.
    *
    * @param modifiedDate the modified date of this accrual details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _accrualDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the sales master ID of this accrual details.
    *
    * @return the sales master ID of this accrual details
    */
    @Override
    public java.lang.String getSalesMasterId() {
        return _accrualDetails.getSalesMasterId();
    }

    /**
    * Sets the sales master ID of this accrual details.
    *
    * @param salesMasterId the sales master ID of this accrual details
    */
    @Override
    public void setSalesMasterId(java.lang.String salesMasterId) {
        _accrualDetails.setSalesMasterId(salesMasterId);
    }

    /**
    * Returns the source of this accrual details.
    *
    * @return the source of this accrual details
    */
    @Override
    public java.lang.String getSource() {
        return _accrualDetails.getSource();
    }

    /**
    * Sets the source of this accrual details.
    *
    * @param source the source of this accrual details
    */
    @Override
    public void setSource(java.lang.String source) {
        _accrualDetails.setSource(source);
    }

    /**
    * Returns the contract master sid of this accrual details.
    *
    * @return the contract master sid of this accrual details
    */
    @Override
    public int getContractMasterSid() {
        return _accrualDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this accrual details.
    *
    * @param contractMasterSid the contract master sid of this accrual details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _accrualDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the accrual details sid of this accrual details.
    *
    * @return the accrual details sid of this accrual details
    */
    @Override
    public int getAccrualDetailsSid() {
        return _accrualDetails.getAccrualDetailsSid();
    }

    /**
    * Sets the accrual details sid of this accrual details.
    *
    * @param accrualDetailsSid the accrual details sid of this accrual details
    */
    @Override
    public void setAccrualDetailsSid(int accrualDetailsSid) {
        _accrualDetails.setAccrualDetailsSid(accrualDetailsSid);
    }

    /**
    * Returns the document type of this accrual details.
    *
    * @return the document type of this accrual details
    */
    @Override
    public java.lang.String getDocumentType() {
        return _accrualDetails.getDocumentType();
    }

    /**
    * Sets the document type of this accrual details.
    *
    * @param documentType the document type of this accrual details
    */
    @Override
    public void setDocumentType(java.lang.String documentType) {
        _accrualDetails.setDocumentType(documentType);
    }

    /**
    * Returns the inbound status of this accrual details.
    *
    * @return the inbound status of this accrual details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _accrualDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this accrual details.
    *
    * @param inboundStatus the inbound status of this accrual details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _accrualDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this accrual details.
    *
    * @return the modified by of this accrual details
    */
    @Override
    public int getModifiedBy() {
        return _accrualDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this accrual details.
    *
    * @param modifiedBy the modified by of this accrual details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _accrualDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the deduction type of this accrual details.
    *
    * @return the deduction type of this accrual details
    */
    @Override
    public java.lang.String getDeductionType() {
        return _accrualDetails.getDeductionType();
    }

    /**
    * Sets the deduction type of this accrual details.
    *
    * @param deductionType the deduction type of this accrual details
    */
    @Override
    public void setDeductionType(java.lang.String deductionType) {
        _accrualDetails.setDeductionType(deductionType);
    }

    /**
    * Returns the company master sid of this accrual details.
    *
    * @return the company master sid of this accrual details
    */
    @Override
    public int getCompanyMasterSid() {
        return _accrualDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this accrual details.
    *
    * @param companyMasterSid the company master sid of this accrual details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _accrualDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the contract name of this accrual details.
    *
    * @return the contract name of this accrual details
    */
    @Override
    public java.lang.String getContractName() {
        return _accrualDetails.getContractName();
    }

    /**
    * Sets the contract name of this accrual details.
    *
    * @param contractName the contract name of this accrual details
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _accrualDetails.setContractName(contractName);
    }

    /**
    * Returns the account no of this accrual details.
    *
    * @return the account no of this accrual details
    */
    @Override
    public java.lang.String getAccountNo() {
        return _accrualDetails.getAccountNo();
    }

    /**
    * Sets the account no of this accrual details.
    *
    * @param accountNo the account no of this accrual details
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _accrualDetails.setAccountNo(accountNo);
    }

    /**
    * Returns the account name of this accrual details.
    *
    * @return the account name of this accrual details
    */
    @Override
    public java.lang.String getAccountName() {
        return _accrualDetails.getAccountName();
    }

    /**
    * Sets the account name of this accrual details.
    *
    * @param accountName the account name of this accrual details
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _accrualDetails.setAccountName(accountName);
    }

    /**
    * Returns the version no of this accrual details.
    *
    * @return the version no of this accrual details
    */
    @Override
    public int getVersionNo() {
        return _accrualDetails.getVersionNo();
    }

    /**
    * Sets the version no of this accrual details.
    *
    * @param versionNo the version no of this accrual details
    */
    @Override
    public void setVersionNo(int versionNo) {
        _accrualDetails.setVersionNo(versionNo);
    }

    /**
    * Returns the provision ID of this accrual details.
    *
    * @return the provision ID of this accrual details
    */
    @Override
    public java.lang.String getProvisionId() {
        return _accrualDetails.getProvisionId();
    }

    /**
    * Sets the provision ID of this accrual details.
    *
    * @param provisionId the provision ID of this accrual details
    */
    @Override
    public void setProvisionId(java.lang.String provisionId) {
        _accrualDetails.setProvisionId(provisionId);
    }

    /**
    * Returns the company identifier code qualifier of this accrual details.
    *
    * @return the company identifier code qualifier of this accrual details
    */
    @Override
    public java.lang.String getCompanyIdentifierCodeQualifier() {
        return _accrualDetails.getCompanyIdentifierCodeQualifier();
    }

    /**
    * Sets the company identifier code qualifier of this accrual details.
    *
    * @param companyIdentifierCodeQualifier the company identifier code qualifier of this accrual details
    */
    @Override
    public void setCompanyIdentifierCodeQualifier(
        java.lang.String companyIdentifierCodeQualifier) {
        _accrualDetails.setCompanyIdentifierCodeQualifier(companyIdentifierCodeQualifier);
    }

    /**
    * Returns the amount of this accrual details.
    *
    * @return the amount of this accrual details
    */
    @Override
    public double getAmount() {
        return _accrualDetails.getAmount();
    }

    /**
    * Sets the amount of this accrual details.
    *
    * @param amount the amount of this accrual details
    */
    @Override
    public void setAmount(double amount) {
        _accrualDetails.setAmount(amount);
    }

    /**
    * Returns the sub ledger of this accrual details.
    *
    * @return the sub ledger of this accrual details
    */
    @Override
    public java.lang.String getSubLedger() {
        return _accrualDetails.getSubLedger();
    }

    /**
    * Sets the sub ledger of this accrual details.
    *
    * @param subLedger the sub ledger of this accrual details
    */
    @Override
    public void setSubLedger(java.lang.String subLedger) {
        _accrualDetails.setSubLedger(subLedger);
    }

    /**
    * Returns the record lock status of this accrual details.
    *
    * @return the record lock status of this accrual details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _accrualDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this accrual details is record lock status.
    *
    * @return <code>true</code> if this accrual details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _accrualDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this accrual details is record lock status.
    *
    * @param recordLockStatus the record lock status of this accrual details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _accrualDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the item identifier code qualifier of this accrual details.
    *
    * @return the item identifier code qualifier of this accrual details
    */
    @Override
    public java.lang.String getItemIdentifierCodeQualifier() {
        return _accrualDetails.getItemIdentifierCodeQualifier();
    }

    /**
    * Sets the item identifier code qualifier of this accrual details.
    *
    * @param itemIdentifierCodeQualifier the item identifier code qualifier of this accrual details
    */
    @Override
    public void setItemIdentifierCodeQualifier(
        java.lang.String itemIdentifierCodeQualifier) {
        _accrualDetails.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
    }

    /**
    * Returns the company no of this accrual details.
    *
    * @return the company no of this accrual details
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _accrualDetails.getCompanyNo();
    }

    /**
    * Sets the company no of this accrual details.
    *
    * @param companyNo the company no of this accrual details
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _accrualDetails.setCompanyNo(companyNo);
    }

    /**
    * Returns the posting status of this accrual details.
    *
    * @return the posting status of this accrual details
    */
    @Override
    public java.lang.String getPostingStatus() {
        return _accrualDetails.getPostingStatus();
    }

    /**
    * Sets the posting status of this accrual details.
    *
    * @param postingStatus the posting status of this accrual details
    */
    @Override
    public void setPostingStatus(java.lang.String postingStatus) {
        _accrualDetails.setPostingStatus(postingStatus);
    }

    /**
    * Returns the item name of this accrual details.
    *
    * @return the item name of this accrual details
    */
    @Override
    public java.lang.String getItemName() {
        return _accrualDetails.getItemName();
    }

    /**
    * Sets the item name of this accrual details.
    *
    * @param itemName the item name of this accrual details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _accrualDetails.setItemName(itemName);
    }

    /**
    * Returns the rs model sid of this accrual details.
    *
    * @return the rs model sid of this accrual details
    */
    @Override
    public int getRsModelSid() {
        return _accrualDetails.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this accrual details.
    *
    * @param rsModelSid the rs model sid of this accrual details
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _accrualDetails.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the item master sid of this accrual details.
    *
    * @return the item master sid of this accrual details
    */
    @Override
    public int getItemMasterSid() {
        return _accrualDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this accrual details.
    *
    * @param itemMasterSid the item master sid of this accrual details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _accrualDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item ID of this accrual details.
    *
    * @return the item ID of this accrual details
    */
    @Override
    public java.lang.String getItemId() {
        return _accrualDetails.getItemId();
    }

    /**
    * Sets the item ID of this accrual details.
    *
    * @param itemId the item ID of this accrual details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _accrualDetails.setItemId(itemId);
    }

    /**
    * Returns the brand master sid of this accrual details.
    *
    * @return the brand master sid of this accrual details
    */
    @Override
    public int getBrandMasterSid() {
        return _accrualDetails.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this accrual details.
    *
    * @param brandMasterSid the brand master sid of this accrual details
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _accrualDetails.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the gl company master sid of this accrual details.
    *
    * @return the gl company master sid of this accrual details
    */
    @Override
    public java.lang.String getGlCompanyMasterSid() {
        return _accrualDetails.getGlCompanyMasterSid();
    }

    /**
    * Sets the gl company master sid of this accrual details.
    *
    * @param glCompanyMasterSid the gl company master sid of this accrual details
    */
    @Override
    public void setGlCompanyMasterSid(java.lang.String glCompanyMasterSid) {
        _accrualDetails.setGlCompanyMasterSid(glCompanyMasterSid);
    }

    /**
    * Returns the created by of this accrual details.
    *
    * @return the created by of this accrual details
    */
    @Override
    public int getCreatedBy() {
        return _accrualDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this accrual details.
    *
    * @param createdBy the created by of this accrual details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _accrualDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this accrual details.
    *
    * @return the created date of this accrual details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _accrualDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this accrual details.
    *
    * @param createdDate the created date of this accrual details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _accrualDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the accrual period start date of this accrual details.
    *
    * @return the accrual period start date of this accrual details
    */
    @Override
    public java.util.Date getAccrualPeriodStartDate() {
        return _accrualDetails.getAccrualPeriodStartDate();
    }

    /**
    * Sets the accrual period start date of this accrual details.
    *
    * @param accrualPeriodStartDate the accrual period start date of this accrual details
    */
    @Override
    public void setAccrualPeriodStartDate(java.util.Date accrualPeriodStartDate) {
        _accrualDetails.setAccrualPeriodStartDate(accrualPeriodStartDate);
    }

    /**
    * Returns the sub ledger type of this accrual details.
    *
    * @return the sub ledger type of this accrual details
    */
    @Override
    public java.lang.String getSubLedgerType() {
        return _accrualDetails.getSubLedgerType();
    }

    /**
    * Sets the sub ledger type of this accrual details.
    *
    * @param subLedgerType the sub ledger type of this accrual details
    */
    @Override
    public void setSubLedgerType(java.lang.String subLedgerType) {
        _accrualDetails.setSubLedgerType(subLedgerType);
    }

    /**
    * Returns the program no of this accrual details.
    *
    * @return the program no of this accrual details
    */
    @Override
    public java.lang.String getProgramNo() {
        return _accrualDetails.getProgramNo();
    }

    /**
    * Sets the program no of this accrual details.
    *
    * @param programNo the program no of this accrual details
    */
    @Override
    public void setProgramNo(java.lang.String programNo) {
        _accrualDetails.setProgramNo(programNo);
    }

    /**
    * Returns the category ID of this accrual details.
    *
    * @return the category ID of this accrual details
    */
    @Override
    public java.lang.String getCategoryId() {
        return _accrualDetails.getCategoryId();
    }

    /**
    * Sets the category ID of this accrual details.
    *
    * @param categoryId the category ID of this accrual details
    */
    @Override
    public void setCategoryId(java.lang.String categoryId) {
        _accrualDetails.setCategoryId(categoryId);
    }

    /**
    * Returns the item no of this accrual details.
    *
    * @return the item no of this accrual details
    */
    @Override
    public java.lang.String getItemNo() {
        return _accrualDetails.getItemNo();
    }

    /**
    * Sets the item no of this accrual details.
    *
    * @param itemNo the item no of this accrual details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _accrualDetails.setItemNo(itemNo);
    }

    /**
    * Returns the deduction sub type of this accrual details.
    *
    * @return the deduction sub type of this accrual details
    */
    @Override
    public java.lang.String getDeductionSubType() {
        return _accrualDetails.getDeductionSubType();
    }

    /**
    * Sets the deduction sub type of this accrual details.
    *
    * @param deductionSubType the deduction sub type of this accrual details
    */
    @Override
    public void setDeductionSubType(java.lang.String deductionSubType) {
        _accrualDetails.setDeductionSubType(deductionSubType);
    }

    /**
    * Returns the acct identifier code qualifier of this accrual details.
    *
    * @return the acct identifier code qualifier of this accrual details
    */
    @Override
    public java.lang.String getAcctIdentifierCodeQualifier() {
        return _accrualDetails.getAcctIdentifierCodeQualifier();
    }

    /**
    * Sets the acct identifier code qualifier of this accrual details.
    *
    * @param acctIdentifierCodeQualifier the acct identifier code qualifier of this accrual details
    */
    @Override
    public void setAcctIdentifierCodeQualifier(
        java.lang.String acctIdentifierCodeQualifier) {
        _accrualDetails.setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
    }

    /**
    * Returns the contract ID of this accrual details.
    *
    * @return the contract ID of this accrual details
    */
    @Override
    public java.lang.String getContractId() {
        return _accrualDetails.getContractId();
    }

    /**
    * Sets the contract ID of this accrual details.
    *
    * @param contractId the contract ID of this accrual details
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _accrualDetails.setContractId(contractId);
    }

    /**
    * Returns the accrual ID of this accrual details.
    *
    * @return the accrual ID of this accrual details
    */
    @Override
    public java.lang.String getAccrualId() {
        return _accrualDetails.getAccrualId();
    }

    /**
    * Sets the accrual ID of this accrual details.
    *
    * @param accrualId the accrual ID of this accrual details
    */
    @Override
    public void setAccrualId(java.lang.String accrualId) {
        _accrualDetails.setAccrualId(accrualId);
    }

    /**
    * Returns the company ID of this accrual details.
    *
    * @return the company ID of this accrual details
    */
    @Override
    public java.lang.String getCompanyId() {
        return _accrualDetails.getCompanyId();
    }

    /**
    * Sets the company ID of this accrual details.
    *
    * @param companyId the company ID of this accrual details
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _accrualDetails.setCompanyId(companyId);
    }

    /**
    * Returns the accrual type of this accrual details.
    *
    * @return the accrual type of this accrual details
    */
    @Override
    public java.lang.String getAccrualType() {
        return _accrualDetails.getAccrualType();
    }

    /**
    * Sets the accrual type of this accrual details.
    *
    * @param accrualType the accrual type of this accrual details
    */
    @Override
    public void setAccrualType(java.lang.String accrualType) {
        _accrualDetails.setAccrualType(accrualType);
    }

    /**
    * Returns the brand ID of this accrual details.
    *
    * @return the brand ID of this accrual details
    */
    @Override
    public java.lang.String getBrandId() {
        return _accrualDetails.getBrandId();
    }

    /**
    * Sets the brand ID of this accrual details.
    *
    * @param brandId the brand ID of this accrual details
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _accrualDetails.setBrandId(brandId);
    }

    /**
    * Returns the posting date of this accrual details.
    *
    * @return the posting date of this accrual details
    */
    @Override
    public java.util.Date getPostingDate() {
        return _accrualDetails.getPostingDate();
    }

    /**
    * Sets the posting date of this accrual details.
    *
    * @param postingDate the posting date of this accrual details
    */
    @Override
    public void setPostingDate(java.util.Date postingDate) {
        _accrualDetails.setPostingDate(postingDate);
    }

    /**
    * Returns the gl date of this accrual details.
    *
    * @return the gl date of this accrual details
    */
    @Override
    public java.util.Date getGlDate() {
        return _accrualDetails.getGlDate();
    }

    /**
    * Sets the gl date of this accrual details.
    *
    * @param glDate the gl date of this accrual details
    */
    @Override
    public void setGlDate(java.util.Date glDate) {
        _accrualDetails.setGlDate(glDate);
    }

    /**
    * Returns the company cost center of this accrual details.
    *
    * @return the company cost center of this accrual details
    */
    @Override
    public java.lang.String getCompanyCostCenter() {
        return _accrualDetails.getCompanyCostCenter();
    }

    /**
    * Sets the company cost center of this accrual details.
    *
    * @param companyCostCenter the company cost center of this accrual details
    */
    @Override
    public void setCompanyCostCenter(java.lang.String companyCostCenter) {
        _accrualDetails.setCompanyCostCenter(companyCostCenter);
    }

    /**
    * Returns the gl account of this accrual details.
    *
    * @return the gl account of this accrual details
    */
    @Override
    public java.lang.String getGlAccount() {
        return _accrualDetails.getGlAccount();
    }

    /**
    * Sets the gl account of this accrual details.
    *
    * @param glAccount the gl account of this accrual details
    */
    @Override
    public void setGlAccount(java.lang.String glAccount) {
        _accrualDetails.setGlAccount(glAccount);
    }

    /**
    * Returns the batch ID of this accrual details.
    *
    * @return the batch ID of this accrual details
    */
    @Override
    public java.lang.String getBatchId() {
        return _accrualDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this accrual details.
    *
    * @param batchId the batch ID of this accrual details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _accrualDetails.setBatchId(batchId);
    }

    /**
    * Returns the program type of this accrual details.
    *
    * @return the program type of this accrual details
    */
    @Override
    public int getProgramType() {
        return _accrualDetails.getProgramType();
    }

    /**
    * Sets the program type of this accrual details.
    *
    * @param programType the program type of this accrual details
    */
    @Override
    public void setProgramType(int programType) {
        _accrualDetails.setProgramType(programType);
    }

    /**
    * Returns the contract no of this accrual details.
    *
    * @return the contract no of this accrual details
    */
    @Override
    public java.lang.String getContractNo() {
        return _accrualDetails.getContractNo();
    }

    /**
    * Sets the contract no of this accrual details.
    *
    * @param contractNo the contract no of this accrual details
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _accrualDetails.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _accrualDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _accrualDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _accrualDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _accrualDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _accrualDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _accrualDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _accrualDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _accrualDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _accrualDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _accrualDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _accrualDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AccrualDetailsWrapper((AccrualDetails) _accrualDetails.clone());
    }

    @Override
    public int compareTo(AccrualDetails accrualDetails) {
        return _accrualDetails.compareTo(accrualDetails);
    }

    @Override
    public int hashCode() {
        return _accrualDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AccrualDetails> toCacheModel() {
        return _accrualDetails.toCacheModel();
    }

    @Override
    public AccrualDetails toEscapedModel() {
        return new AccrualDetailsWrapper(_accrualDetails.toEscapedModel());
    }

    @Override
    public AccrualDetails toUnescapedModel() {
        return new AccrualDetailsWrapper(_accrualDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _accrualDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _accrualDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _accrualDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccrualDetailsWrapper)) {
            return false;
        }

        AccrualDetailsWrapper accrualDetailsWrapper = (AccrualDetailsWrapper) obj;

        if (Validator.equals(_accrualDetails,
                    accrualDetailsWrapper._accrualDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AccrualDetails getWrappedAccrualDetails() {
        return _accrualDetails;
    }

    @Override
    public AccrualDetails getWrappedModel() {
        return _accrualDetails;
    }

    @Override
    public void resetOriginalValues() {
        _accrualDetails.resetOriginalValues();
    }
}
