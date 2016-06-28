package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccrualMaster}.
 * </p>
 *
 * @author
 * @see AccrualMaster
 * @generated
 */
public class AccrualMasterWrapper implements AccrualMaster,
    ModelWrapper<AccrualMaster> {
    private AccrualMaster _accrualMaster;

    public AccrualMasterWrapper(AccrualMaster accrualMaster) {
        _accrualMaster = accrualMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return AccrualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccrualMaster.class.getName();
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
        attributes.put("documentType", getDocumentType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("acctIdentCodeQualifier", getAcctIdentCodeQualifier());
        attributes.put("compIdentCodeQualifier", getCompIdentCodeQualifier());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("contractName", getContractName());
        attributes.put("accountNo", getAccountNo());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("subLedger", getSubLedger());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("itemName", getItemName());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("accrualMasterSid", getAccrualMasterSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemId", getItemId());
        attributes.put("brandMasterSid", getBrandMasterSid());
        attributes.put("itemIdentCodeQualifier", getItemIdentCodeQualifier());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("accrualPeriodStartDate", getAccrualPeriodStartDate());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("programNo", getProgramNo());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("categoryId", getCategoryId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractId", getContractId());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyId", getCompanyId());
        attributes.put("accrualType", getAccrualType());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("glDate", getGlDate());
        attributes.put("glAmount", getGlAmount());
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

        String acctIdentCodeQualifier = (String) attributes.get(
                "acctIdentCodeQualifier");

        if (acctIdentCodeQualifier != null) {
            setAcctIdentCodeQualifier(acctIdentCodeQualifier);
        }

        String compIdentCodeQualifier = (String) attributes.get(
                "compIdentCodeQualifier");

        if (compIdentCodeQualifier != null) {
            setCompIdentCodeQualifier(compIdentCodeQualifier);
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

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
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

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer accrualMasterSid = (Integer) attributes.get("accrualMasterSid");

        if (accrualMasterSid != null) {
            setAccrualMasterSid(accrualMasterSid);
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

        String itemIdentCodeQualifier = (String) attributes.get(
                "itemIdentCodeQualifier");

        if (itemIdentCodeQualifier != null) {
            setItemIdentCodeQualifier(itemIdentCodeQualifier);
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

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String categoryId = (String) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
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

        Double glAmount = (Double) attributes.get("glAmount");

        if (glAmount != null) {
            setGlAmount(glAmount);
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

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this accrual master.
    *
    * @return the primary key of this accrual master
    */
    @Override
    public int getPrimaryKey() {
        return _accrualMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this accrual master.
    *
    * @param primaryKey the primary key of this accrual master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _accrualMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the account ID of this accrual master.
    *
    * @return the account ID of this accrual master
    */
    @Override
    public java.lang.String getAccountId() {
        return _accrualMaster.getAccountId();
    }

    /**
    * Sets the account ID of this accrual master.
    *
    * @param accountId the account ID of this accrual master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _accrualMaster.setAccountId(accountId);
    }

    /**
    * Returns the record created date of this accrual master.
    *
    * @return the record created date of this accrual master
    */
    @Override
    public java.util.Date getRecordCreatedDate() {
        return _accrualMaster.getRecordCreatedDate();
    }

    /**
    * Sets the record created date of this accrual master.
    *
    * @param recordCreatedDate the record created date of this accrual master
    */
    @Override
    public void setRecordCreatedDate(java.util.Date recordCreatedDate) {
        _accrualMaster.setRecordCreatedDate(recordCreatedDate);
    }

    /**
    * Returns the posting indicator of this accrual master.
    *
    * @return the posting indicator of this accrual master
    */
    @Override
    public java.lang.String getPostingIndicator() {
        return _accrualMaster.getPostingIndicator();
    }

    /**
    * Sets the posting indicator of this accrual master.
    *
    * @param postingIndicator the posting indicator of this accrual master
    */
    @Override
    public void setPostingIndicator(java.lang.String postingIndicator) {
        _accrualMaster.setPostingIndicator(postingIndicator);
    }

    /**
    * Returns the brand name of this accrual master.
    *
    * @return the brand name of this accrual master
    */
    @Override
    public java.lang.String getBrandName() {
        return _accrualMaster.getBrandName();
    }

    /**
    * Sets the brand name of this accrual master.
    *
    * @param brandName the brand name of this accrual master
    */
    @Override
    public void setBrandName(java.lang.String brandName) {
        _accrualMaster.setBrandName(brandName);
    }

    /**
    * Returns the accrual period end date of this accrual master.
    *
    * @return the accrual period end date of this accrual master
    */
    @Override
    public java.util.Date getAccrualPeriodEndDate() {
        return _accrualMaster.getAccrualPeriodEndDate();
    }

    /**
    * Sets the accrual period end date of this accrual master.
    *
    * @param accrualPeriodEndDate the accrual period end date of this accrual master
    */
    @Override
    public void setAccrualPeriodEndDate(java.util.Date accrualPeriodEndDate) {
        _accrualMaster.setAccrualPeriodEndDate(accrualPeriodEndDate);
    }

    /**
    * Returns the modified date of this accrual master.
    *
    * @return the modified date of this accrual master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _accrualMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this accrual master.
    *
    * @param modifiedDate the modified date of this accrual master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _accrualMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the sales master ID of this accrual master.
    *
    * @return the sales master ID of this accrual master
    */
    @Override
    public java.lang.String getSalesMasterId() {
        return _accrualMaster.getSalesMasterId();
    }

    /**
    * Sets the sales master ID of this accrual master.
    *
    * @param salesMasterId the sales master ID of this accrual master
    */
    @Override
    public void setSalesMasterId(java.lang.String salesMasterId) {
        _accrualMaster.setSalesMasterId(salesMasterId);
    }

    /**
    * Returns the source of this accrual master.
    *
    * @return the source of this accrual master
    */
    @Override
    public java.lang.String getSource() {
        return _accrualMaster.getSource();
    }

    /**
    * Sets the source of this accrual master.
    *
    * @param source the source of this accrual master
    */
    @Override
    public void setSource(java.lang.String source) {
        _accrualMaster.setSource(source);
    }

    /**
    * Returns the contract master sid of this accrual master.
    *
    * @return the contract master sid of this accrual master
    */
    @Override
    public int getContractMasterSid() {
        return _accrualMaster.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this accrual master.
    *
    * @param contractMasterSid the contract master sid of this accrual master
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _accrualMaster.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the document type of this accrual master.
    *
    * @return the document type of this accrual master
    */
    @Override
    public java.lang.String getDocumentType() {
        return _accrualMaster.getDocumentType();
    }

    /**
    * Sets the document type of this accrual master.
    *
    * @param documentType the document type of this accrual master
    */
    @Override
    public void setDocumentType(java.lang.String documentType) {
        _accrualMaster.setDocumentType(documentType);
    }

    /**
    * Returns the inbound status of this accrual master.
    *
    * @return the inbound status of this accrual master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _accrualMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this accrual master.
    *
    * @param inboundStatus the inbound status of this accrual master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _accrualMaster.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the modified by of this accrual master.
    *
    * @return the modified by of this accrual master
    */
    @Override
    public int getModifiedBy() {
        return _accrualMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this accrual master.
    *
    * @param modifiedBy the modified by of this accrual master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _accrualMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the acct ident code qualifier of this accrual master.
    *
    * @return the acct ident code qualifier of this accrual master
    */
    @Override
    public java.lang.String getAcctIdentCodeQualifier() {
        return _accrualMaster.getAcctIdentCodeQualifier();
    }

    /**
    * Sets the acct ident code qualifier of this accrual master.
    *
    * @param acctIdentCodeQualifier the acct ident code qualifier of this accrual master
    */
    @Override
    public void setAcctIdentCodeQualifier(
        java.lang.String acctIdentCodeQualifier) {
        _accrualMaster.setAcctIdentCodeQualifier(acctIdentCodeQualifier);
    }

    /**
    * Returns the comp ident code qualifier of this accrual master.
    *
    * @return the comp ident code qualifier of this accrual master
    */
    @Override
    public java.lang.String getCompIdentCodeQualifier() {
        return _accrualMaster.getCompIdentCodeQualifier();
    }

    /**
    * Sets the comp ident code qualifier of this accrual master.
    *
    * @param compIdentCodeQualifier the comp ident code qualifier of this accrual master
    */
    @Override
    public void setCompIdentCodeQualifier(
        java.lang.String compIdentCodeQualifier) {
        _accrualMaster.setCompIdentCodeQualifier(compIdentCodeQualifier);
    }

    /**
    * Returns the company master sid of this accrual master.
    *
    * @return the company master sid of this accrual master
    */
    @Override
    public int getCompanyMasterSid() {
        return _accrualMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this accrual master.
    *
    * @param companyMasterSid the company master sid of this accrual master
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _accrualMaster.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the contract name of this accrual master.
    *
    * @return the contract name of this accrual master
    */
    @Override
    public java.lang.String getContractName() {
        return _accrualMaster.getContractName();
    }

    /**
    * Sets the contract name of this accrual master.
    *
    * @param contractName the contract name of this accrual master
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _accrualMaster.setContractName(contractName);
    }

    /**
    * Returns the account no of this accrual master.
    *
    * @return the account no of this accrual master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _accrualMaster.getAccountNo();
    }

    /**
    * Sets the account no of this accrual master.
    *
    * @param accountNo the account no of this accrual master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _accrualMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the account name of this accrual master.
    *
    * @return the account name of this accrual master
    */
    @Override
    public java.lang.String getAccountName() {
        return _accrualMaster.getAccountName();
    }

    /**
    * Sets the account name of this accrual master.
    *
    * @param accountName the account name of this accrual master
    */
    @Override
    public void setAccountName(java.lang.String accountName) {
        _accrualMaster.setAccountName(accountName);
    }

    /**
    * Returns the provision ID of this accrual master.
    *
    * @return the provision ID of this accrual master
    */
    @Override
    public java.lang.String getProvisionId() {
        return _accrualMaster.getProvisionId();
    }

    /**
    * Sets the provision ID of this accrual master.
    *
    * @param provisionId the provision ID of this accrual master
    */
    @Override
    public void setProvisionId(java.lang.String provisionId) {
        _accrualMaster.setProvisionId(provisionId);
    }

    /**
    * Returns the amount of this accrual master.
    *
    * @return the amount of this accrual master
    */
    @Override
    public double getAmount() {
        return _accrualMaster.getAmount();
    }

    /**
    * Sets the amount of this accrual master.
    *
    * @param amount the amount of this accrual master
    */
    @Override
    public void setAmount(double amount) {
        _accrualMaster.setAmount(amount);
    }

    /**
    * Returns the sub ledger of this accrual master.
    *
    * @return the sub ledger of this accrual master
    */
    @Override
    public java.lang.String getSubLedger() {
        return _accrualMaster.getSubLedger();
    }

    /**
    * Sets the sub ledger of this accrual master.
    *
    * @param subLedger the sub ledger of this accrual master
    */
    @Override
    public void setSubLedger(java.lang.String subLedger) {
        _accrualMaster.setSubLedger(subLedger);
    }

    /**
    * Returns the record lock status of this accrual master.
    *
    * @return the record lock status of this accrual master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _accrualMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this accrual master is record lock status.
    *
    * @return <code>true</code> if this accrual master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _accrualMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this accrual master is record lock status.
    *
    * @param recordLockStatus the record lock status of this accrual master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _accrualMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the company no of this accrual master.
    *
    * @return the company no of this accrual master
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _accrualMaster.getCompanyNo();
    }

    /**
    * Sets the company no of this accrual master.
    *
    * @param companyNo the company no of this accrual master
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _accrualMaster.setCompanyNo(companyNo);
    }

    /**
    * Returns the item name of this accrual master.
    *
    * @return the item name of this accrual master
    */
    @Override
    public java.lang.String getItemName() {
        return _accrualMaster.getItemName();
    }

    /**
    * Sets the item name of this accrual master.
    *
    * @param itemName the item name of this accrual master
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _accrualMaster.setItemName(itemName);
    }

    /**
    * Returns the rs model sid of this accrual master.
    *
    * @return the rs model sid of this accrual master
    */
    @Override
    public int getRsModelSid() {
        return _accrualMaster.getRsModelSid();
    }

    /**
    * Sets the rs model sid of this accrual master.
    *
    * @param rsModelSid the rs model sid of this accrual master
    */
    @Override
    public void setRsModelSid(int rsModelSid) {
        _accrualMaster.setRsModelSid(rsModelSid);
    }

    /**
    * Returns the accrual master sid of this accrual master.
    *
    * @return the accrual master sid of this accrual master
    */
    @Override
    public int getAccrualMasterSid() {
        return _accrualMaster.getAccrualMasterSid();
    }

    /**
    * Sets the accrual master sid of this accrual master.
    *
    * @param accrualMasterSid the accrual master sid of this accrual master
    */
    @Override
    public void setAccrualMasterSid(int accrualMasterSid) {
        _accrualMaster.setAccrualMasterSid(accrualMasterSid);
    }

    /**
    * Returns the item master sid of this accrual master.
    *
    * @return the item master sid of this accrual master
    */
    @Override
    public int getItemMasterSid() {
        return _accrualMaster.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this accrual master.
    *
    * @param itemMasterSid the item master sid of this accrual master
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _accrualMaster.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item ID of this accrual master.
    *
    * @return the item ID of this accrual master
    */
    @Override
    public java.lang.String getItemId() {
        return _accrualMaster.getItemId();
    }

    /**
    * Sets the item ID of this accrual master.
    *
    * @param itemId the item ID of this accrual master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _accrualMaster.setItemId(itemId);
    }

    /**
    * Returns the brand master sid of this accrual master.
    *
    * @return the brand master sid of this accrual master
    */
    @Override
    public int getBrandMasterSid() {
        return _accrualMaster.getBrandMasterSid();
    }

    /**
    * Sets the brand master sid of this accrual master.
    *
    * @param brandMasterSid the brand master sid of this accrual master
    */
    @Override
    public void setBrandMasterSid(int brandMasterSid) {
        _accrualMaster.setBrandMasterSid(brandMasterSid);
    }

    /**
    * Returns the item ident code qualifier of this accrual master.
    *
    * @return the item ident code qualifier of this accrual master
    */
    @Override
    public java.lang.String getItemIdentCodeQualifier() {
        return _accrualMaster.getItemIdentCodeQualifier();
    }

    /**
    * Sets the item ident code qualifier of this accrual master.
    *
    * @param itemIdentCodeQualifier the item ident code qualifier of this accrual master
    */
    @Override
    public void setItemIdentCodeQualifier(
        java.lang.String itemIdentCodeQualifier) {
        _accrualMaster.setItemIdentCodeQualifier(itemIdentCodeQualifier);
    }

    /**
    * Returns the gl company master sid of this accrual master.
    *
    * @return the gl company master sid of this accrual master
    */
    @Override
    public java.lang.String getGlCompanyMasterSid() {
        return _accrualMaster.getGlCompanyMasterSid();
    }

    /**
    * Sets the gl company master sid of this accrual master.
    *
    * @param glCompanyMasterSid the gl company master sid of this accrual master
    */
    @Override
    public void setGlCompanyMasterSid(java.lang.String glCompanyMasterSid) {
        _accrualMaster.setGlCompanyMasterSid(glCompanyMasterSid);
    }

    /**
    * Returns the created by of this accrual master.
    *
    * @return the created by of this accrual master
    */
    @Override
    public int getCreatedBy() {
        return _accrualMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this accrual master.
    *
    * @param createdBy the created by of this accrual master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _accrualMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this accrual master.
    *
    * @return the created date of this accrual master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _accrualMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this accrual master.
    *
    * @param createdDate the created date of this accrual master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _accrualMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the accrual period start date of this accrual master.
    *
    * @return the accrual period start date of this accrual master
    */
    @Override
    public java.util.Date getAccrualPeriodStartDate() {
        return _accrualMaster.getAccrualPeriodStartDate();
    }

    /**
    * Sets the accrual period start date of this accrual master.
    *
    * @param accrualPeriodStartDate the accrual period start date of this accrual master
    */
    @Override
    public void setAccrualPeriodStartDate(java.util.Date accrualPeriodStartDate) {
        _accrualMaster.setAccrualPeriodStartDate(accrualPeriodStartDate);
    }

    /**
    * Returns the sub ledger type of this accrual master.
    *
    * @return the sub ledger type of this accrual master
    */
    @Override
    public java.lang.String getSubLedgerType() {
        return _accrualMaster.getSubLedgerType();
    }

    /**
    * Sets the sub ledger type of this accrual master.
    *
    * @param subLedgerType the sub ledger type of this accrual master
    */
    @Override
    public void setSubLedgerType(java.lang.String subLedgerType) {
        _accrualMaster.setSubLedgerType(subLedgerType);
    }

    /**
    * Returns the program no of this accrual master.
    *
    * @return the program no of this accrual master
    */
    @Override
    public java.lang.String getProgramNo() {
        return _accrualMaster.getProgramNo();
    }

    /**
    * Sets the program no of this accrual master.
    *
    * @param programNo the program no of this accrual master
    */
    @Override
    public void setProgramNo(java.lang.String programNo) {
        _accrualMaster.setProgramNo(programNo);
    }

    /**
    * Returns the gl company name of this accrual master.
    *
    * @return the gl company name of this accrual master
    */
    @Override
    public java.lang.String getGlCompanyName() {
        return _accrualMaster.getGlCompanyName();
    }

    /**
    * Sets the gl company name of this accrual master.
    *
    * @param glCompanyName the gl company name of this accrual master
    */
    @Override
    public void setGlCompanyName(java.lang.String glCompanyName) {
        _accrualMaster.setGlCompanyName(glCompanyName);
    }

    /**
    * Returns the category ID of this accrual master.
    *
    * @return the category ID of this accrual master
    */
    @Override
    public java.lang.String getCategoryId() {
        return _accrualMaster.getCategoryId();
    }

    /**
    * Sets the category ID of this accrual master.
    *
    * @param categoryId the category ID of this accrual master
    */
    @Override
    public void setCategoryId(java.lang.String categoryId) {
        _accrualMaster.setCategoryId(categoryId);
    }

    /**
    * Returns the item no of this accrual master.
    *
    * @return the item no of this accrual master
    */
    @Override
    public java.lang.String getItemNo() {
        return _accrualMaster.getItemNo();
    }

    /**
    * Sets the item no of this accrual master.
    *
    * @param itemNo the item no of this accrual master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _accrualMaster.setItemNo(itemNo);
    }

    /**
    * Returns the contract ID of this accrual master.
    *
    * @return the contract ID of this accrual master
    */
    @Override
    public java.lang.String getContractId() {
        return _accrualMaster.getContractId();
    }

    /**
    * Sets the contract ID of this accrual master.
    *
    * @param contractId the contract ID of this accrual master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _accrualMaster.setContractId(contractId);
    }

    /**
    * Returns the accrual ID of this accrual master.
    *
    * @return the accrual ID of this accrual master
    */
    @Override
    public java.lang.String getAccrualId() {
        return _accrualMaster.getAccrualId();
    }

    /**
    * Sets the accrual ID of this accrual master.
    *
    * @param accrualId the accrual ID of this accrual master
    */
    @Override
    public void setAccrualId(java.lang.String accrualId) {
        _accrualMaster.setAccrualId(accrualId);
    }

    /**
    * Returns the company ID of this accrual master.
    *
    * @return the company ID of this accrual master
    */
    @Override
    public java.lang.String getCompanyId() {
        return _accrualMaster.getCompanyId();
    }

    /**
    * Sets the company ID of this accrual master.
    *
    * @param companyId the company ID of this accrual master
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _accrualMaster.setCompanyId(companyId);
    }

    /**
    * Returns the accrual type of this accrual master.
    *
    * @return the accrual type of this accrual master
    */
    @Override
    public java.lang.String getAccrualType() {
        return _accrualMaster.getAccrualType();
    }

    /**
    * Sets the accrual type of this accrual master.
    *
    * @param accrualType the accrual type of this accrual master
    */
    @Override
    public void setAccrualType(java.lang.String accrualType) {
        _accrualMaster.setAccrualType(accrualType);
    }

    /**
    * Returns the brand ID of this accrual master.
    *
    * @return the brand ID of this accrual master
    */
    @Override
    public java.lang.String getBrandId() {
        return _accrualMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this accrual master.
    *
    * @param brandId the brand ID of this accrual master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _accrualMaster.setBrandId(brandId);
    }

    /**
    * Returns the posting date of this accrual master.
    *
    * @return the posting date of this accrual master
    */
    @Override
    public java.util.Date getPostingDate() {
        return _accrualMaster.getPostingDate();
    }

    /**
    * Sets the posting date of this accrual master.
    *
    * @param postingDate the posting date of this accrual master
    */
    @Override
    public void setPostingDate(java.util.Date postingDate) {
        _accrualMaster.setPostingDate(postingDate);
    }

    /**
    * Returns the gl date of this accrual master.
    *
    * @return the gl date of this accrual master
    */
    @Override
    public java.util.Date getGlDate() {
        return _accrualMaster.getGlDate();
    }

    /**
    * Sets the gl date of this accrual master.
    *
    * @param glDate the gl date of this accrual master
    */
    @Override
    public void setGlDate(java.util.Date glDate) {
        _accrualMaster.setGlDate(glDate);
    }

    /**
    * Returns the gl amount of this accrual master.
    *
    * @return the gl amount of this accrual master
    */
    @Override
    public double getGlAmount() {
        return _accrualMaster.getGlAmount();
    }

    /**
    * Sets the gl amount of this accrual master.
    *
    * @param glAmount the gl amount of this accrual master
    */
    @Override
    public void setGlAmount(double glAmount) {
        _accrualMaster.setGlAmount(glAmount);
    }

    /**
    * Returns the company cost center of this accrual master.
    *
    * @return the company cost center of this accrual master
    */
    @Override
    public java.lang.String getCompanyCostCenter() {
        return _accrualMaster.getCompanyCostCenter();
    }

    /**
    * Sets the company cost center of this accrual master.
    *
    * @param companyCostCenter the company cost center of this accrual master
    */
    @Override
    public void setCompanyCostCenter(java.lang.String companyCostCenter) {
        _accrualMaster.setCompanyCostCenter(companyCostCenter);
    }

    /**
    * Returns the gl account of this accrual master.
    *
    * @return the gl account of this accrual master
    */
    @Override
    public java.lang.String getGlAccount() {
        return _accrualMaster.getGlAccount();
    }

    /**
    * Sets the gl account of this accrual master.
    *
    * @param glAccount the gl account of this accrual master
    */
    @Override
    public void setGlAccount(java.lang.String glAccount) {
        _accrualMaster.setGlAccount(glAccount);
    }

    /**
    * Returns the batch ID of this accrual master.
    *
    * @return the batch ID of this accrual master
    */
    @Override
    public java.lang.String getBatchId() {
        return _accrualMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this accrual master.
    *
    * @param batchId the batch ID of this accrual master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _accrualMaster.setBatchId(batchId);
    }

    /**
    * Returns the program type of this accrual master.
    *
    * @return the program type of this accrual master
    */
    @Override
    public java.lang.String getProgramType() {
        return _accrualMaster.getProgramType();
    }

    /**
    * Sets the program type of this accrual master.
    *
    * @param programType the program type of this accrual master
    */
    @Override
    public void setProgramType(java.lang.String programType) {
        _accrualMaster.setProgramType(programType);
    }

    /**
    * Returns the contract no of this accrual master.
    *
    * @return the contract no of this accrual master
    */
    @Override
    public java.lang.String getContractNo() {
        return _accrualMaster.getContractNo();
    }

    /**
    * Sets the contract no of this accrual master.
    *
    * @param contractNo the contract no of this accrual master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _accrualMaster.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _accrualMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _accrualMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _accrualMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _accrualMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _accrualMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _accrualMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _accrualMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _accrualMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _accrualMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _accrualMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _accrualMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AccrualMasterWrapper((AccrualMaster) _accrualMaster.clone());
    }

    @Override
    public int compareTo(AccrualMaster accrualMaster) {
        return _accrualMaster.compareTo(accrualMaster);
    }

    @Override
    public int hashCode() {
        return _accrualMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AccrualMaster> toCacheModel() {
        return _accrualMaster.toCacheModel();
    }

    @Override
    public AccrualMaster toEscapedModel() {
        return new AccrualMasterWrapper(_accrualMaster.toEscapedModel());
    }

    @Override
    public AccrualMaster toUnescapedModel() {
        return new AccrualMasterWrapper(_accrualMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _accrualMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _accrualMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _accrualMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccrualMasterWrapper)) {
            return false;
        }

        AccrualMasterWrapper accrualMasterWrapper = (AccrualMasterWrapper) obj;

        if (Validator.equals(_accrualMaster, accrualMasterWrapper._accrualMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AccrualMaster getWrappedAccrualMaster() {
        return _accrualMaster;
    }

    @Override
    public AccrualMaster getWrappedModel() {
        return _accrualMaster;
    }

    @Override
    public void resetOriginalValues() {
        _accrualMaster.resetOriginalValues();
    }
}
