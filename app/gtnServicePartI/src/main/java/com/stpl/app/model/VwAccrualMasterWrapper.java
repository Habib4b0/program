package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwAccrualMaster}.
 * </p>
 *
 * @author
 * @see VwAccrualMaster
 * @generated
 */
public class VwAccrualMasterWrapper implements VwAccrualMaster,
    ModelWrapper<VwAccrualMaster> {
    private VwAccrualMaster _vwAccrualMaster;

    public VwAccrualMasterWrapper(VwAccrualMaster vwAccrualMaster) {
        _vwAccrualMaster = vwAccrualMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return VwAccrualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwAccrualMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemQualifier", getItemQualifier());
        attributes.put("businessUnitQualifier", getBusinessUnitQualifier());
        attributes.put("itemNo", getItemNo());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("costCenter", getCostCenter());
        attributes.put("subLedger", getSubLedger());
        attributes.put("accrualPeriodEd", getAccrualPeriodEd());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyQualifier", getCompanyQualifier());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("salesId", getSalesId());
        attributes.put("amount", getAmount());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("documentType", getDocumentType());
        attributes.put("accuralType", getAccuralType());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("programNo", getProgramNo());
        attributes.put("customerId", getCustomerId());
        attributes.put("itemId", getItemId());
        attributes.put("accrualMasterSid", getAccrualMasterSid());
        attributes.put("contractId", getContractId());
        attributes.put("contractName", getContractName());
        attributes.put("glAccount", getGlAccount());
        attributes.put("glDate", getGlDate());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("programType", getProgramType());
        attributes.put("customerName", getCustomerName());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("source", getSource());
        attributes.put("accrualPeriodSd", getAccrualPeriodSd());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemQualifier = (String) attributes.get("itemQualifier");

        if (itemQualifier != null) {
            setItemQualifier(itemQualifier);
        }

        String businessUnitQualifier = (String) attributes.get(
                "businessUnitQualifier");

        if (businessUnitQualifier != null) {
            setBusinessUnitQualifier(businessUnitQualifier);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String subLedger = (String) attributes.get("subLedger");

        if (subLedger != null) {
            setSubLedger(subLedger);
        }

        Date accrualPeriodEd = (Date) attributes.get("accrualPeriodEd");

        if (accrualPeriodEd != null) {
            setAccrualPeriodEd(accrualPeriodEd);
        }

        String accrualId = (String) attributes.get("accrualId");

        if (accrualId != null) {
            setAccrualId(accrualId);
        }

        String companyQualifier = (String) attributes.get("companyQualifier");

        if (companyQualifier != null) {
            setCompanyQualifier(companyQualifier);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Date postingDate = (Date) attributes.get("postingDate");

        if (postingDate != null) {
            setPostingDate(postingDate);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String subLedgerType = (String) attributes.get("subLedgerType");

        if (subLedgerType != null) {
            setSubLedgerType(subLedgerType);
        }

        String documentType = (String) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String accuralType = (String) attributes.get("accuralType");

        if (accuralType != null) {
            setAccuralType(accuralType);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String programNo = (String) attributes.get("programNo");

        if (programNo != null) {
            setProgramNo(programNo);
        }

        String customerId = (String) attributes.get("customerId");

        if (customerId != null) {
            setCustomerId(customerId);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer accrualMasterSid = (Integer) attributes.get("accrualMasterSid");

        if (accrualMasterSid != null) {
            setAccrualMasterSid(accrualMasterSid);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String glAccount = (String) attributes.get("glAccount");

        if (glAccount != null) {
            setGlAccount(glAccount);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date accrualPeriodSd = (Date) attributes.get("accrualPeriodSd");

        if (accrualPeriodSd != null) {
            setAccrualPeriodSd(accrualPeriodSd);
        }
    }

    /**
    * Returns the primary key of this vw accrual master.
    *
    * @return the primary key of this vw accrual master
    */
    @Override
    public int getPrimaryKey() {
        return _vwAccrualMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw accrual master.
    *
    * @param primaryKey the primary key of this vw accrual master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwAccrualMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item qualifier of this vw accrual master.
    *
    * @return the item qualifier of this vw accrual master
    */
    @Override
    public java.lang.String getItemQualifier() {
        return _vwAccrualMaster.getItemQualifier();
    }

    /**
    * Sets the item qualifier of this vw accrual master.
    *
    * @param itemQualifier the item qualifier of this vw accrual master
    */
    @Override
    public void setItemQualifier(java.lang.String itemQualifier) {
        _vwAccrualMaster.setItemQualifier(itemQualifier);
    }

    /**
    * Returns the business unit qualifier of this vw accrual master.
    *
    * @return the business unit qualifier of this vw accrual master
    */
    @Override
    public java.lang.String getBusinessUnitQualifier() {
        return _vwAccrualMaster.getBusinessUnitQualifier();
    }

    /**
    * Sets the business unit qualifier of this vw accrual master.
    *
    * @param businessUnitQualifier the business unit qualifier of this vw accrual master
    */
    @Override
    public void setBusinessUnitQualifier(java.lang.String businessUnitQualifier) {
        _vwAccrualMaster.setBusinessUnitQualifier(businessUnitQualifier);
    }

    /**
    * Returns the item no of this vw accrual master.
    *
    * @return the item no of this vw accrual master
    */
    @Override
    public java.lang.String getItemNo() {
        return _vwAccrualMaster.getItemNo();
    }

    /**
    * Sets the item no of this vw accrual master.
    *
    * @param itemNo the item no of this vw accrual master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _vwAccrualMaster.setItemNo(itemNo);
    }

    /**
    * Returns the posting indicator of this vw accrual master.
    *
    * @return the posting indicator of this vw accrual master
    */
    @Override
    public java.lang.String getPostingIndicator() {
        return _vwAccrualMaster.getPostingIndicator();
    }

    /**
    * Sets the posting indicator of this vw accrual master.
    *
    * @param postingIndicator the posting indicator of this vw accrual master
    */
    @Override
    public void setPostingIndicator(java.lang.String postingIndicator) {
        _vwAccrualMaster.setPostingIndicator(postingIndicator);
    }

    /**
    * Returns the created date of this vw accrual master.
    *
    * @return the created date of this vw accrual master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwAccrualMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this vw accrual master.
    *
    * @param createdDate the created date of this vw accrual master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwAccrualMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the cost center of this vw accrual master.
    *
    * @return the cost center of this vw accrual master
    */
    @Override
    public java.lang.String getCostCenter() {
        return _vwAccrualMaster.getCostCenter();
    }

    /**
    * Sets the cost center of this vw accrual master.
    *
    * @param costCenter the cost center of this vw accrual master
    */
    @Override
    public void setCostCenter(java.lang.String costCenter) {
        _vwAccrualMaster.setCostCenter(costCenter);
    }

    /**
    * Returns the sub ledger of this vw accrual master.
    *
    * @return the sub ledger of this vw accrual master
    */
    @Override
    public java.lang.String getSubLedger() {
        return _vwAccrualMaster.getSubLedger();
    }

    /**
    * Sets the sub ledger of this vw accrual master.
    *
    * @param subLedger the sub ledger of this vw accrual master
    */
    @Override
    public void setSubLedger(java.lang.String subLedger) {
        _vwAccrualMaster.setSubLedger(subLedger);
    }

    /**
    * Returns the accrual period ed of this vw accrual master.
    *
    * @return the accrual period ed of this vw accrual master
    */
    @Override
    public java.util.Date getAccrualPeriodEd() {
        return _vwAccrualMaster.getAccrualPeriodEd();
    }

    /**
    * Sets the accrual period ed of this vw accrual master.
    *
    * @param accrualPeriodEd the accrual period ed of this vw accrual master
    */
    @Override
    public void setAccrualPeriodEd(java.util.Date accrualPeriodEd) {
        _vwAccrualMaster.setAccrualPeriodEd(accrualPeriodEd);
    }

    /**
    * Returns the accrual ID of this vw accrual master.
    *
    * @return the accrual ID of this vw accrual master
    */
    @Override
    public java.lang.String getAccrualId() {
        return _vwAccrualMaster.getAccrualId();
    }

    /**
    * Sets the accrual ID of this vw accrual master.
    *
    * @param accrualId the accrual ID of this vw accrual master
    */
    @Override
    public void setAccrualId(java.lang.String accrualId) {
        _vwAccrualMaster.setAccrualId(accrualId);
    }

    /**
    * Returns the company qualifier of this vw accrual master.
    *
    * @return the company qualifier of this vw accrual master
    */
    @Override
    public java.lang.String getCompanyQualifier() {
        return _vwAccrualMaster.getCompanyQualifier();
    }

    /**
    * Sets the company qualifier of this vw accrual master.
    *
    * @param companyQualifier the company qualifier of this vw accrual master
    */
    @Override
    public void setCompanyQualifier(java.lang.String companyQualifier) {
        _vwAccrualMaster.setCompanyQualifier(companyQualifier);
    }

    /**
    * Returns the contract no of this vw accrual master.
    *
    * @return the contract no of this vw accrual master
    */
    @Override
    public java.lang.String getContractNo() {
        return _vwAccrualMaster.getContractNo();
    }

    /**
    * Sets the contract no of this vw accrual master.
    *
    * @param contractNo the contract no of this vw accrual master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _vwAccrualMaster.setContractNo(contractNo);
    }

    /**
    * Returns the batch ID of this vw accrual master.
    *
    * @return the batch ID of this vw accrual master
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwAccrualMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this vw accrual master.
    *
    * @param batchId the batch ID of this vw accrual master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwAccrualMaster.setBatchId(batchId);
    }

    /**
    * Returns the item name of this vw accrual master.
    *
    * @return the item name of this vw accrual master
    */
    @Override
    public java.lang.String getItemName() {
        return _vwAccrualMaster.getItemName();
    }

    /**
    * Sets the item name of this vw accrual master.
    *
    * @param itemName the item name of this vw accrual master
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwAccrualMaster.setItemName(itemName);
    }

    /**
    * Returns the brand ID of this vw accrual master.
    *
    * @return the brand ID of this vw accrual master
    */
    @Override
    public java.lang.String getBrandId() {
        return _vwAccrualMaster.getBrandId();
    }

    /**
    * Sets the brand ID of this vw accrual master.
    *
    * @param brandId the brand ID of this vw accrual master
    */
    @Override
    public void setBrandId(java.lang.String brandId) {
        _vwAccrualMaster.setBrandId(brandId);
    }

    /**
    * Returns the posting date of this vw accrual master.
    *
    * @return the posting date of this vw accrual master
    */
    @Override
    public java.util.Date getPostingDate() {
        return _vwAccrualMaster.getPostingDate();
    }

    /**
    * Sets the posting date of this vw accrual master.
    *
    * @param postingDate the posting date of this vw accrual master
    */
    @Override
    public void setPostingDate(java.util.Date postingDate) {
        _vwAccrualMaster.setPostingDate(postingDate);
    }

    /**
    * Returns the business unit name of this vw accrual master.
    *
    * @return the business unit name of this vw accrual master
    */
    @Override
    public java.lang.String getBusinessUnitName() {
        return _vwAccrualMaster.getBusinessUnitName();
    }

    /**
    * Sets the business unit name of this vw accrual master.
    *
    * @param businessUnitName the business unit name of this vw accrual master
    */
    @Override
    public void setBusinessUnitName(java.lang.String businessUnitName) {
        _vwAccrualMaster.setBusinessUnitName(businessUnitName);
    }

    /**
    * Returns the sales ID of this vw accrual master.
    *
    * @return the sales ID of this vw accrual master
    */
    @Override
    public java.lang.String getSalesId() {
        return _vwAccrualMaster.getSalesId();
    }

    /**
    * Sets the sales ID of this vw accrual master.
    *
    * @param salesId the sales ID of this vw accrual master
    */
    @Override
    public void setSalesId(java.lang.String salesId) {
        _vwAccrualMaster.setSalesId(salesId);
    }

    /**
    * Returns the amount of this vw accrual master.
    *
    * @return the amount of this vw accrual master
    */
    @Override
    public double getAmount() {
        return _vwAccrualMaster.getAmount();
    }

    /**
    * Sets the amount of this vw accrual master.
    *
    * @param amount the amount of this vw accrual master
    */
    @Override
    public void setAmount(double amount) {
        _vwAccrualMaster.setAmount(amount);
    }

    /**
    * Returns the business unit no of this vw accrual master.
    *
    * @return the business unit no of this vw accrual master
    */
    @Override
    public java.lang.String getBusinessUnitNo() {
        return _vwAccrualMaster.getBusinessUnitNo();
    }

    /**
    * Sets the business unit no of this vw accrual master.
    *
    * @param businessUnitNo the business unit no of this vw accrual master
    */
    @Override
    public void setBusinessUnitNo(java.lang.String businessUnitNo) {
        _vwAccrualMaster.setBusinessUnitNo(businessUnitNo);
    }

    /**
    * Returns the sub ledger type of this vw accrual master.
    *
    * @return the sub ledger type of this vw accrual master
    */
    @Override
    public java.lang.String getSubLedgerType() {
        return _vwAccrualMaster.getSubLedgerType();
    }

    /**
    * Sets the sub ledger type of this vw accrual master.
    *
    * @param subLedgerType the sub ledger type of this vw accrual master
    */
    @Override
    public void setSubLedgerType(java.lang.String subLedgerType) {
        _vwAccrualMaster.setSubLedgerType(subLedgerType);
    }

    /**
    * Returns the document type of this vw accrual master.
    *
    * @return the document type of this vw accrual master
    */
    @Override
    public java.lang.String getDocumentType() {
        return _vwAccrualMaster.getDocumentType();
    }

    /**
    * Sets the document type of this vw accrual master.
    *
    * @param documentType the document type of this vw accrual master
    */
    @Override
    public void setDocumentType(java.lang.String documentType) {
        _vwAccrualMaster.setDocumentType(documentType);
    }

    /**
    * Returns the accural type of this vw accrual master.
    *
    * @return the accural type of this vw accrual master
    */
    @Override
    public java.lang.String getAccuralType() {
        return _vwAccrualMaster.getAccuralType();
    }

    /**
    * Sets the accural type of this vw accrual master.
    *
    * @param accuralType the accural type of this vw accrual master
    */
    @Override
    public void setAccuralType(java.lang.String accuralType) {
        _vwAccrualMaster.setAccuralType(accuralType);
    }

    /**
    * Returns the created by of this vw accrual master.
    *
    * @return the created by of this vw accrual master
    */
    @Override
    public int getCreatedBy() {
        return _vwAccrualMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this vw accrual master.
    *
    * @param createdBy the created by of this vw accrual master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _vwAccrualMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the program no of this vw accrual master.
    *
    * @return the program no of this vw accrual master
    */
    @Override
    public java.lang.String getProgramNo() {
        return _vwAccrualMaster.getProgramNo();
    }

    /**
    * Sets the program no of this vw accrual master.
    *
    * @param programNo the program no of this vw accrual master
    */
    @Override
    public void setProgramNo(java.lang.String programNo) {
        _vwAccrualMaster.setProgramNo(programNo);
    }

    /**
    * Returns the customer ID of this vw accrual master.
    *
    * @return the customer ID of this vw accrual master
    */
    @Override
    public java.lang.String getCustomerId() {
        return _vwAccrualMaster.getCustomerId();
    }

    /**
    * Sets the customer ID of this vw accrual master.
    *
    * @param customerId the customer ID of this vw accrual master
    */
    @Override
    public void setCustomerId(java.lang.String customerId) {
        _vwAccrualMaster.setCustomerId(customerId);
    }

    /**
    * Returns the item ID of this vw accrual master.
    *
    * @return the item ID of this vw accrual master
    */
    @Override
    public java.lang.String getItemId() {
        return _vwAccrualMaster.getItemId();
    }

    /**
    * Sets the item ID of this vw accrual master.
    *
    * @param itemId the item ID of this vw accrual master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwAccrualMaster.setItemId(itemId);
    }

    /**
    * Returns the accrual master sid of this vw accrual master.
    *
    * @return the accrual master sid of this vw accrual master
    */
    @Override
    public int getAccrualMasterSid() {
        return _vwAccrualMaster.getAccrualMasterSid();
    }

    /**
    * Sets the accrual master sid of this vw accrual master.
    *
    * @param accrualMasterSid the accrual master sid of this vw accrual master
    */
    @Override
    public void setAccrualMasterSid(int accrualMasterSid) {
        _vwAccrualMaster.setAccrualMasterSid(accrualMasterSid);
    }

    /**
    * Returns the contract ID of this vw accrual master.
    *
    * @return the contract ID of this vw accrual master
    */
    @Override
    public java.lang.String getContractId() {
        return _vwAccrualMaster.getContractId();
    }

    /**
    * Sets the contract ID of this vw accrual master.
    *
    * @param contractId the contract ID of this vw accrual master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _vwAccrualMaster.setContractId(contractId);
    }

    /**
    * Returns the contract name of this vw accrual master.
    *
    * @return the contract name of this vw accrual master
    */
    @Override
    public java.lang.String getContractName() {
        return _vwAccrualMaster.getContractName();
    }

    /**
    * Sets the contract name of this vw accrual master.
    *
    * @param contractName the contract name of this vw accrual master
    */
    @Override
    public void setContractName(java.lang.String contractName) {
        _vwAccrualMaster.setContractName(contractName);
    }

    /**
    * Returns the gl account of this vw accrual master.
    *
    * @return the gl account of this vw accrual master
    */
    @Override
    public java.lang.String getGlAccount() {
        return _vwAccrualMaster.getGlAccount();
    }

    /**
    * Sets the gl account of this vw accrual master.
    *
    * @param glAccount the gl account of this vw accrual master
    */
    @Override
    public void setGlAccount(java.lang.String glAccount) {
        _vwAccrualMaster.setGlAccount(glAccount);
    }

    /**
    * Returns the gl date of this vw accrual master.
    *
    * @return the gl date of this vw accrual master
    */
    @Override
    public java.util.Date getGlDate() {
        return _vwAccrualMaster.getGlDate();
    }

    /**
    * Sets the gl date of this vw accrual master.
    *
    * @param glDate the gl date of this vw accrual master
    */
    @Override
    public void setGlDate(java.util.Date glDate) {
        _vwAccrualMaster.setGlDate(glDate);
    }

    /**
    * Returns the business unit ID of this vw accrual master.
    *
    * @return the business unit ID of this vw accrual master
    */
    @Override
    public java.lang.String getBusinessUnitId() {
        return _vwAccrualMaster.getBusinessUnitId();
    }

    /**
    * Sets the business unit ID of this vw accrual master.
    *
    * @param businessUnitId the business unit ID of this vw accrual master
    */
    @Override
    public void setBusinessUnitId(java.lang.String businessUnitId) {
        _vwAccrualMaster.setBusinessUnitId(businessUnitId);
    }

    /**
    * Returns the program type of this vw accrual master.
    *
    * @return the program type of this vw accrual master
    */
    @Override
    public java.lang.String getProgramType() {
        return _vwAccrualMaster.getProgramType();
    }

    /**
    * Sets the program type of this vw accrual master.
    *
    * @param programType the program type of this vw accrual master
    */
    @Override
    public void setProgramType(java.lang.String programType) {
        _vwAccrualMaster.setProgramType(programType);
    }

    /**
    * Returns the customer name of this vw accrual master.
    *
    * @return the customer name of this vw accrual master
    */
    @Override
    public java.lang.String getCustomerName() {
        return _vwAccrualMaster.getCustomerName();
    }

    /**
    * Sets the customer name of this vw accrual master.
    *
    * @param customerName the customer name of this vw accrual master
    */
    @Override
    public void setCustomerName(java.lang.String customerName) {
        _vwAccrualMaster.setCustomerName(customerName);
    }

    /**
    * Returns the customer no of this vw accrual master.
    *
    * @return the customer no of this vw accrual master
    */
    @Override
    public java.lang.String getCustomerNo() {
        return _vwAccrualMaster.getCustomerNo();
    }

    /**
    * Sets the customer no of this vw accrual master.
    *
    * @param customerNo the customer no of this vw accrual master
    */
    @Override
    public void setCustomerNo(java.lang.String customerNo) {
        _vwAccrualMaster.setCustomerNo(customerNo);
    }

    /**
    * Returns the source of this vw accrual master.
    *
    * @return the source of this vw accrual master
    */
    @Override
    public java.lang.String getSource() {
        return _vwAccrualMaster.getSource();
    }

    /**
    * Sets the source of this vw accrual master.
    *
    * @param source the source of this vw accrual master
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwAccrualMaster.setSource(source);
    }

    /**
    * Returns the accrual period sd of this vw accrual master.
    *
    * @return the accrual period sd of this vw accrual master
    */
    @Override
    public java.util.Date getAccrualPeriodSd() {
        return _vwAccrualMaster.getAccrualPeriodSd();
    }

    /**
    * Sets the accrual period sd of this vw accrual master.
    *
    * @param accrualPeriodSd the accrual period sd of this vw accrual master
    */
    @Override
    public void setAccrualPeriodSd(java.util.Date accrualPeriodSd) {
        _vwAccrualMaster.setAccrualPeriodSd(accrualPeriodSd);
    }

    @Override
    public boolean isNew() {
        return _vwAccrualMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwAccrualMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwAccrualMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwAccrualMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwAccrualMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwAccrualMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwAccrualMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwAccrualMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwAccrualMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwAccrualMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwAccrualMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwAccrualMasterWrapper((VwAccrualMaster) _vwAccrualMaster.clone());
    }

    @Override
    public int compareTo(VwAccrualMaster vwAccrualMaster) {
        return _vwAccrualMaster.compareTo(vwAccrualMaster);
    }

    @Override
    public int hashCode() {
        return _vwAccrualMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwAccrualMaster> toCacheModel() {
        return _vwAccrualMaster.toCacheModel();
    }

    @Override
    public VwAccrualMaster toEscapedModel() {
        return new VwAccrualMasterWrapper(_vwAccrualMaster.toEscapedModel());
    }

    @Override
    public VwAccrualMaster toUnescapedModel() {
        return new VwAccrualMasterWrapper(_vwAccrualMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwAccrualMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwAccrualMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwAccrualMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwAccrualMasterWrapper)) {
            return false;
        }

        VwAccrualMasterWrapper vwAccrualMasterWrapper = (VwAccrualMasterWrapper) obj;

        if (Validator.equals(_vwAccrualMaster,
                    vwAccrualMasterWrapper._vwAccrualMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwAccrualMaster getWrappedVwAccrualMaster() {
        return _vwAccrualMaster;
    }

    @Override
    public VwAccrualMaster getWrappedModel() {
        return _vwAccrualMaster;
    }

    @Override
    public void resetOriginalValues() {
        _vwAccrualMaster.resetOriginalValues();
    }
}