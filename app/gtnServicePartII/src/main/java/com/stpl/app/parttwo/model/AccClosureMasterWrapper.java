package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccClosureMaster}.
 * </p>
 *
 * @author
 * @see AccClosureMaster
 * @generated
 */
public class AccClosureMasterWrapper implements AccClosureMaster,
    ModelWrapper<AccClosureMaster> {
    private AccClosureMaster _accClosureMaster;

    public AccClosureMasterWrapper(AccClosureMaster accClosureMaster) {
        _accClosureMaster = accClosureMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return AccClosureMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AccClosureMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("saveFlag", getSaveFlag());
        attributes.put("accountNo", getAccountNo());
        attributes.put("toDate", getToDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("description", getDescription());
        attributes.put("reportName", getReportName());
        attributes.put("rsType", getRsType());
        attributes.put("productIdentifier", getProductIdentifier());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("workflowStatus", getWorkflowStatus());
        attributes.put("moduleType", getModuleType());
        attributes.put("fromDate", getFromDate());
        attributes.put("contractType", getContractType());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("accrualPeriod", getAccrualPeriod());
        attributes.put("companyGroupSid", getCompanyGroupSid());
        attributes.put("accClosureMasterSid", getAccClosureMasterSid());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("rebateProgramType", getRebateProgramType());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        Date toDate = (Date) attributes.get("toDate");

        if (toDate != null) {
            setToDate(toDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String reportName = (String) attributes.get("reportName");

        if (reportName != null) {
            setReportName(reportName);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        String productIdentifier = (String) attributes.get("productIdentifier");

        if (productIdentifier != null) {
            setProductIdentifier(productIdentifier);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer workflowStatus = (Integer) attributes.get("workflowStatus");

        if (workflowStatus != null) {
            setWorkflowStatus(workflowStatus);
        }

        String moduleType = (String) attributes.get("moduleType");

        if (moduleType != null) {
            setModuleType(moduleType);
        }

        Date fromDate = (Date) attributes.get("fromDate");

        if (fromDate != null) {
            setFromDate(fromDate);
        }

        Integer contractType = (Integer) attributes.get("contractType");

        if (contractType != null) {
            setContractType(contractType);
        }

        Integer glCompanyMasterSid = (Integer) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String accrualPeriod = (String) attributes.get("accrualPeriod");

        if (accrualPeriod != null) {
            setAccrualPeriod(accrualPeriod);
        }

        String companyGroupSid = (String) attributes.get("companyGroupSid");

        if (companyGroupSid != null) {
            setCompanyGroupSid(companyGroupSid);
        }

        Integer accClosureMasterSid = (Integer) attributes.get(
                "accClosureMasterSid");

        if (accClosureMasterSid != null) {
            setAccClosureMasterSid(accClosureMasterSid);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer adjustmentType = (Integer) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemGroupSid = (String) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }
    }

    /**
    * Returns the primary key of this acc closure master.
    *
    * @return the primary key of this acc closure master
    */
    @Override
    public int getPrimaryKey() {
        return _accClosureMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this acc closure master.
    *
    * @param primaryKey the primary key of this acc closure master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _accClosureMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the save flag of this acc closure master.
    *
    * @return the save flag of this acc closure master
    */
    @Override
    public boolean getSaveFlag() {
        return _accClosureMaster.getSaveFlag();
    }

    /**
    * Returns <code>true</code> if this acc closure master is save flag.
    *
    * @return <code>true</code> if this acc closure master is save flag; <code>false</code> otherwise
    */
    @Override
    public boolean isSaveFlag() {
        return _accClosureMaster.isSaveFlag();
    }

    /**
    * Sets whether this acc closure master is save flag.
    *
    * @param saveFlag the save flag of this acc closure master
    */
    @Override
    public void setSaveFlag(boolean saveFlag) {
        _accClosureMaster.setSaveFlag(saveFlag);
    }

    /**
    * Returns the account no of this acc closure master.
    *
    * @return the account no of this acc closure master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _accClosureMaster.getAccountNo();
    }

    /**
    * Sets the account no of this acc closure master.
    *
    * @param accountNo the account no of this acc closure master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _accClosureMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the to date of this acc closure master.
    *
    * @return the to date of this acc closure master
    */
    @Override
    public java.util.Date getToDate() {
        return _accClosureMaster.getToDate();
    }

    /**
    * Sets the to date of this acc closure master.
    *
    * @param toDate the to date of this acc closure master
    */
    @Override
    public void setToDate(java.util.Date toDate) {
        _accClosureMaster.setToDate(toDate);
    }

    /**
    * Returns the item master sid of this acc closure master.
    *
    * @return the item master sid of this acc closure master
    */
    @Override
    public int getItemMasterSid() {
        return _accClosureMaster.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this acc closure master.
    *
    * @param itemMasterSid the item master sid of this acc closure master
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _accClosureMaster.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the description of this acc closure master.
    *
    * @return the description of this acc closure master
    */
    @Override
    public java.lang.String getDescription() {
        return _accClosureMaster.getDescription();
    }

    /**
    * Sets the description of this acc closure master.
    *
    * @param description the description of this acc closure master
    */
    @Override
    public void setDescription(java.lang.String description) {
        _accClosureMaster.setDescription(description);
    }

    /**
    * Returns the report name of this acc closure master.
    *
    * @return the report name of this acc closure master
    */
    @Override
    public java.lang.String getReportName() {
        return _accClosureMaster.getReportName();
    }

    /**
    * Sets the report name of this acc closure master.
    *
    * @param reportName the report name of this acc closure master
    */
    @Override
    public void setReportName(java.lang.String reportName) {
        _accClosureMaster.setReportName(reportName);
    }

    /**
    * Returns the rs type of this acc closure master.
    *
    * @return the rs type of this acc closure master
    */
    @Override
    public int getRsType() {
        return _accClosureMaster.getRsType();
    }

    /**
    * Sets the rs type of this acc closure master.
    *
    * @param rsType the rs type of this acc closure master
    */
    @Override
    public void setRsType(int rsType) {
        _accClosureMaster.setRsType(rsType);
    }

    /**
    * Returns the product identifier of this acc closure master.
    *
    * @return the product identifier of this acc closure master
    */
    @Override
    public java.lang.String getProductIdentifier() {
        return _accClosureMaster.getProductIdentifier();
    }

    /**
    * Sets the product identifier of this acc closure master.
    *
    * @param productIdentifier the product identifier of this acc closure master
    */
    @Override
    public void setProductIdentifier(java.lang.String productIdentifier) {
        _accClosureMaster.setProductIdentifier(productIdentifier);
    }

    /**
    * Returns the modified date of this acc closure master.
    *
    * @return the modified date of this acc closure master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _accClosureMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this acc closure master.
    *
    * @param modifiedDate the modified date of this acc closure master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _accClosureMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the workflow status of this acc closure master.
    *
    * @return the workflow status of this acc closure master
    */
    @Override
    public int getWorkflowStatus() {
        return _accClosureMaster.getWorkflowStatus();
    }

    /**
    * Sets the workflow status of this acc closure master.
    *
    * @param workflowStatus the workflow status of this acc closure master
    */
    @Override
    public void setWorkflowStatus(int workflowStatus) {
        _accClosureMaster.setWorkflowStatus(workflowStatus);
    }

    /**
    * Returns the module type of this acc closure master.
    *
    * @return the module type of this acc closure master
    */
    @Override
    public java.lang.String getModuleType() {
        return _accClosureMaster.getModuleType();
    }

    /**
    * Sets the module type of this acc closure master.
    *
    * @param moduleType the module type of this acc closure master
    */
    @Override
    public void setModuleType(java.lang.String moduleType) {
        _accClosureMaster.setModuleType(moduleType);
    }

    /**
    * Returns the from date of this acc closure master.
    *
    * @return the from date of this acc closure master
    */
    @Override
    public java.util.Date getFromDate() {
        return _accClosureMaster.getFromDate();
    }

    /**
    * Sets the from date of this acc closure master.
    *
    * @param fromDate the from date of this acc closure master
    */
    @Override
    public void setFromDate(java.util.Date fromDate) {
        _accClosureMaster.setFromDate(fromDate);
    }

    /**
    * Returns the contract type of this acc closure master.
    *
    * @return the contract type of this acc closure master
    */
    @Override
    public int getContractType() {
        return _accClosureMaster.getContractType();
    }

    /**
    * Sets the contract type of this acc closure master.
    *
    * @param contractType the contract type of this acc closure master
    */
    @Override
    public void setContractType(int contractType) {
        _accClosureMaster.setContractType(contractType);
    }

    /**
    * Returns the gl company master sid of this acc closure master.
    *
    * @return the gl company master sid of this acc closure master
    */
    @Override
    public int getGlCompanyMasterSid() {
        return _accClosureMaster.getGlCompanyMasterSid();
    }

    /**
    * Sets the gl company master sid of this acc closure master.
    *
    * @param glCompanyMasterSid the gl company master sid of this acc closure master
    */
    @Override
    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _accClosureMaster.setGlCompanyMasterSid(glCompanyMasterSid);
    }

    /**
    * Returns the created date of this acc closure master.
    *
    * @return the created date of this acc closure master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _accClosureMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this acc closure master.
    *
    * @param createdDate the created date of this acc closure master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _accClosureMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this acc closure master.
    *
    * @return the created by of this acc closure master
    */
    @Override
    public int getCreatedBy() {
        return _accClosureMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this acc closure master.
    *
    * @param createdBy the created by of this acc closure master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _accClosureMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the contract master sid of this acc closure master.
    *
    * @return the contract master sid of this acc closure master
    */
    @Override
    public int getContractMasterSid() {
        return _accClosureMaster.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this acc closure master.
    *
    * @param contractMasterSid the contract master sid of this acc closure master
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _accClosureMaster.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the accrual period of this acc closure master.
    *
    * @return the accrual period of this acc closure master
    */
    @Override
    public java.lang.String getAccrualPeriod() {
        return _accClosureMaster.getAccrualPeriod();
    }

    /**
    * Sets the accrual period of this acc closure master.
    *
    * @param accrualPeriod the accrual period of this acc closure master
    */
    @Override
    public void setAccrualPeriod(java.lang.String accrualPeriod) {
        _accClosureMaster.setAccrualPeriod(accrualPeriod);
    }

    /**
    * Returns the company group sid of this acc closure master.
    *
    * @return the company group sid of this acc closure master
    */
    @Override
    public java.lang.String getCompanyGroupSid() {
        return _accClosureMaster.getCompanyGroupSid();
    }

    /**
    * Sets the company group sid of this acc closure master.
    *
    * @param companyGroupSid the company group sid of this acc closure master
    */
    @Override
    public void setCompanyGroupSid(java.lang.String companyGroupSid) {
        _accClosureMaster.setCompanyGroupSid(companyGroupSid);
    }

    /**
    * Returns the acc closure master sid of this acc closure master.
    *
    * @return the acc closure master sid of this acc closure master
    */
    @Override
    public int getAccClosureMasterSid() {
        return _accClosureMaster.getAccClosureMasterSid();
    }

    /**
    * Sets the acc closure master sid of this acc closure master.
    *
    * @param accClosureMasterSid the acc closure master sid of this acc closure master
    */
    @Override
    public void setAccClosureMasterSid(int accClosureMasterSid) {
        _accClosureMaster.setAccClosureMasterSid(accClosureMasterSid);
    }

    /**
    * Returns the rs category of this acc closure master.
    *
    * @return the rs category of this acc closure master
    */
    @Override
    public int getRsCategory() {
        return _accClosureMaster.getRsCategory();
    }

    /**
    * Sets the rs category of this acc closure master.
    *
    * @param rsCategory the rs category of this acc closure master
    */
    @Override
    public void setRsCategory(int rsCategory) {
        _accClosureMaster.setRsCategory(rsCategory);
    }

    /**
    * Returns the adjustment type of this acc closure master.
    *
    * @return the adjustment type of this acc closure master
    */
    @Override
    public int getAdjustmentType() {
        return _accClosureMaster.getAdjustmentType();
    }

    /**
    * Sets the adjustment type of this acc closure master.
    *
    * @param adjustmentType the adjustment type of this acc closure master
    */
    @Override
    public void setAdjustmentType(int adjustmentType) {
        _accClosureMaster.setAdjustmentType(adjustmentType);
    }

    /**
    * Returns the modified by of this acc closure master.
    *
    * @return the modified by of this acc closure master
    */
    @Override
    public int getModifiedBy() {
        return _accClosureMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this acc closure master.
    *
    * @param modifiedBy the modified by of this acc closure master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _accClosureMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the item group sid of this acc closure master.
    *
    * @return the item group sid of this acc closure master
    */
    @Override
    public java.lang.String getItemGroupSid() {
        return _accClosureMaster.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this acc closure master.
    *
    * @param itemGroupSid the item group sid of this acc closure master
    */
    @Override
    public void setItemGroupSid(java.lang.String itemGroupSid) {
        _accClosureMaster.setItemGroupSid(itemGroupSid);
    }

    /**
    * Returns the rebate program type of this acc closure master.
    *
    * @return the rebate program type of this acc closure master
    */
    @Override
    public int getRebateProgramType() {
        return _accClosureMaster.getRebateProgramType();
    }

    /**
    * Sets the rebate program type of this acc closure master.
    *
    * @param rebateProgramType the rebate program type of this acc closure master
    */
    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _accClosureMaster.setRebateProgramType(rebateProgramType);
    }

    @Override
    public boolean isNew() {
        return _accClosureMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _accClosureMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _accClosureMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _accClosureMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _accClosureMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _accClosureMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _accClosureMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _accClosureMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _accClosureMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _accClosureMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _accClosureMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new AccClosureMasterWrapper((AccClosureMaster) _accClosureMaster.clone());
    }

    @Override
    public int compareTo(AccClosureMaster accClosureMaster) {
        return _accClosureMaster.compareTo(accClosureMaster);
    }

    @Override
    public int hashCode() {
        return _accClosureMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<AccClosureMaster> toCacheModel() {
        return _accClosureMaster.toCacheModel();
    }

    @Override
    public AccClosureMaster toEscapedModel() {
        return new AccClosureMasterWrapper(_accClosureMaster.toEscapedModel());
    }

    @Override
    public AccClosureMaster toUnescapedModel() {
        return new AccClosureMasterWrapper(_accClosureMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _accClosureMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _accClosureMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _accClosureMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AccClosureMasterWrapper)) {
            return false;
        }

        AccClosureMasterWrapper accClosureMasterWrapper = (AccClosureMasterWrapper) obj;

        if (Validator.equals(_accClosureMaster,
                    accClosureMasterWrapper._accClosureMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public AccClosureMaster getWrappedAccClosureMaster() {
        return _accClosureMaster;
    }

    @Override
    public AccClosureMaster getWrappedModel() {
        return _accClosureMaster;
    }

    @Override
    public void resetOriginalValues() {
        _accClosureMaster.resetOriginalValues();
    }
}
