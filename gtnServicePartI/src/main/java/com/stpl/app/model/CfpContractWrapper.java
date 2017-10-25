package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CfpContract}.
 * </p>
 *
 * @author
 * @see CfpContract
 * @generated
 */
public class CfpContractWrapper implements CfpContract,
    ModelWrapper<CfpContract> {
    private CfpContract _cfpContract;

    public CfpContractWrapper(CfpContract cfpContract) {
        _cfpContract = cfpContract;
    }

    @Override
    public Class<?> getModelClass() {
        return CfpContract.class;
    }

    @Override
    public String getModelClassName() {
        return CfpContract.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("cfpType", getCfpType());
        attributes.put("cfpTradeClass", getCfpTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("cfpDesignation", getCfpDesignation());
        attributes.put("cfpName", getCfpName());
        attributes.put("cfpNo", getCfpNo());
        attributes.put("cfpCategory", getCfpCategory());
        attributes.put("source", getSource());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("parentCfpId", getParentCfpId());
        attributes.put("cfpContractAttachedStatus",
            getCfpContractAttachedStatus());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("parentCfpName", getParentCfpName());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("salesInclusion", getSalesInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Integer cfpType = (Integer) attributes.get("cfpType");

        if (cfpType != null) {
            setCfpType(cfpType);
        }

        Integer cfpTradeClass = (Integer) attributes.get("cfpTradeClass");

        if (cfpTradeClass != null) {
            setCfpTradeClass(cfpTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Date cfpContractAttachedDate = (Date) attributes.get(
                "cfpContractAttachedDate");

        if (cfpContractAttachedDate != null) {
            setCfpContractAttachedDate(cfpContractAttachedDate);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String cfpDesignation = (String) attributes.get("cfpDesignation");

        if (cfpDesignation != null) {
            setCfpDesignation(cfpDesignation);
        }

        String cfpName = (String) attributes.get("cfpName");

        if (cfpName != null) {
            setCfpName(cfpName);
        }

        String cfpNo = (String) attributes.get("cfpNo");

        if (cfpNo != null) {
            setCfpNo(cfpNo);
        }

        Integer cfpCategory = (Integer) attributes.get("cfpCategory");

        if (cfpCategory != null) {
            setCfpCategory(cfpCategory);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer cfpStatus = (Integer) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer parentCfpId = (Integer) attributes.get("parentCfpId");

        if (parentCfpId != null) {
            setParentCfpId(parentCfpId);
        }

        Integer cfpContractAttachedStatus = (Integer) attributes.get(
                "cfpContractAttachedStatus");

        if (cfpContractAttachedStatus != null) {
            setCfpContractAttachedStatus(cfpContractAttachedStatus);
        }

        Date cfpStartDate = (Date) attributes.get("cfpStartDate");

        if (cfpStartDate != null) {
            setCfpStartDate(cfpStartDate);
        }

        Date cfpEndDate = (Date) attributes.get("cfpEndDate");

        if (cfpEndDate != null) {
            setCfpEndDate(cfpEndDate);
        }

        String parentCfpName = (String) attributes.get("parentCfpName");

        if (parentCfpName != null) {
            setParentCfpName(parentCfpName);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer salesInclusion = (Integer) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }
    }

    /**
    * Returns the primary key of this cfp contract.
    *
    * @return the primary key of this cfp contract
    */
    @Override
    public int getPrimaryKey() {
        return _cfpContract.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cfp contract.
    *
    * @param primaryKey the primary key of this cfp contract
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cfpContract.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cfp contract.
    *
    * @return the created by of this cfp contract
    */
    @Override
    public int getCreatedBy() {
        return _cfpContract.getCreatedBy();
    }

    /**
    * Sets the created by of this cfp contract.
    *
    * @param createdBy the created by of this cfp contract
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cfpContract.setCreatedBy(createdBy);
    }

    /**
    * Returns the cfp contract sid of this cfp contract.
    *
    * @return the cfp contract sid of this cfp contract
    */
    @Override
    public int getCfpContractSid() {
        return _cfpContract.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this cfp contract.
    *
    * @param cfpContractSid the cfp contract sid of this cfp contract
    */
    @Override
    public void setCfpContractSid(int cfpContractSid) {
        _cfpContract.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the cfp type of this cfp contract.
    *
    * @return the cfp type of this cfp contract
    */
    @Override
    public int getCfpType() {
        return _cfpContract.getCfpType();
    }

    /**
    * Sets the cfp type of this cfp contract.
    *
    * @param cfpType the cfp type of this cfp contract
    */
    @Override
    public void setCfpType(int cfpType) {
        _cfpContract.setCfpType(cfpType);
    }

    /**
    * Returns the cfp trade class of this cfp contract.
    *
    * @return the cfp trade class of this cfp contract
    */
    @Override
    public int getCfpTradeClass() {
        return _cfpContract.getCfpTradeClass();
    }

    /**
    * Sets the cfp trade class of this cfp contract.
    *
    * @param cfpTradeClass the cfp trade class of this cfp contract
    */
    @Override
    public void setCfpTradeClass(int cfpTradeClass) {
        _cfpContract.setCfpTradeClass(cfpTradeClass);
    }

    /**
    * Returns the modified by of this cfp contract.
    *
    * @return the modified by of this cfp contract
    */
    @Override
    public int getModifiedBy() {
        return _cfpContract.getModifiedBy();
    }

    /**
    * Sets the modified by of this cfp contract.
    *
    * @param modifiedBy the modified by of this cfp contract
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cfpContract.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this cfp contract.
    *
    * @return the created date of this cfp contract
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cfpContract.getCreatedDate();
    }

    /**
    * Sets the created date of this cfp contract.
    *
    * @param createdDate the created date of this cfp contract
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cfpContract.setCreatedDate(createdDate);
    }

    /**
    * Returns the contract master sid of this cfp contract.
    *
    * @return the contract master sid of this cfp contract
    */
    @Override
    public int getContractMasterSid() {
        return _cfpContract.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this cfp contract.
    *
    * @param contractMasterSid the contract master sid of this cfp contract
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _cfpContract.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the cfp contract attached date of this cfp contract.
    *
    * @return the cfp contract attached date of this cfp contract
    */
    @Override
    public java.util.Date getCfpContractAttachedDate() {
        return _cfpContract.getCfpContractAttachedDate();
    }

    /**
    * Sets the cfp contract attached date of this cfp contract.
    *
    * @param cfpContractAttachedDate the cfp contract attached date of this cfp contract
    */
    @Override
    public void setCfpContractAttachedDate(
        java.util.Date cfpContractAttachedDate) {
        _cfpContract.setCfpContractAttachedDate(cfpContractAttachedDate);
    }

    /**
    * Returns the cfp model sid of this cfp contract.
    *
    * @return the cfp model sid of this cfp contract
    */
    @Override
    public int getCfpModelSid() {
        return _cfpContract.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this cfp contract.
    *
    * @param cfpModelSid the cfp model sid of this cfp contract
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpContract.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the batch ID of this cfp contract.
    *
    * @return the batch ID of this cfp contract
    */
    @Override
    public java.lang.String getBatchId() {
        return _cfpContract.getBatchId();
    }

    /**
    * Sets the batch ID of this cfp contract.
    *
    * @param batchId the batch ID of this cfp contract
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _cfpContract.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this cfp contract.
    *
    * @return the modified date of this cfp contract
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cfpContract.getModifiedDate();
    }

    /**
    * Sets the modified date of this cfp contract.
    *
    * @param modifiedDate the modified date of this cfp contract
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cfpContract.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this cfp contract.
    *
    * @return the record lock status of this cfp contract
    */
    @Override
    public boolean getRecordLockStatus() {
        return _cfpContract.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this cfp contract is record lock status.
    *
    * @return <code>true</code> if this cfp contract is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _cfpContract.isRecordLockStatus();
    }

    /**
    * Sets whether this cfp contract is record lock status.
    *
    * @param recordLockStatus the record lock status of this cfp contract
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _cfpContract.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the cfp designation of this cfp contract.
    *
    * @return the cfp designation of this cfp contract
    */
    @Override
    public java.lang.String getCfpDesignation() {
        return _cfpContract.getCfpDesignation();
    }

    /**
    * Sets the cfp designation of this cfp contract.
    *
    * @param cfpDesignation the cfp designation of this cfp contract
    */
    @Override
    public void setCfpDesignation(java.lang.String cfpDesignation) {
        _cfpContract.setCfpDesignation(cfpDesignation);
    }

    /**
    * Returns the cfp name of this cfp contract.
    *
    * @return the cfp name of this cfp contract
    */
    @Override
    public java.lang.String getCfpName() {
        return _cfpContract.getCfpName();
    }

    /**
    * Sets the cfp name of this cfp contract.
    *
    * @param cfpName the cfp name of this cfp contract
    */
    @Override
    public void setCfpName(java.lang.String cfpName) {
        _cfpContract.setCfpName(cfpName);
    }

    /**
    * Returns the cfp no of this cfp contract.
    *
    * @return the cfp no of this cfp contract
    */
    @Override
    public java.lang.String getCfpNo() {
        return _cfpContract.getCfpNo();
    }

    /**
    * Sets the cfp no of this cfp contract.
    *
    * @param cfpNo the cfp no of this cfp contract
    */
    @Override
    public void setCfpNo(java.lang.String cfpNo) {
        _cfpContract.setCfpNo(cfpNo);
    }

    /**
    * Returns the cfp category of this cfp contract.
    *
    * @return the cfp category of this cfp contract
    */
    @Override
    public int getCfpCategory() {
        return _cfpContract.getCfpCategory();
    }

    /**
    * Sets the cfp category of this cfp contract.
    *
    * @param cfpCategory the cfp category of this cfp contract
    */
    @Override
    public void setCfpCategory(int cfpCategory) {
        _cfpContract.setCfpCategory(cfpCategory);
    }

    /**
    * Returns the source of this cfp contract.
    *
    * @return the source of this cfp contract
    */
    @Override
    public java.lang.String getSource() {
        return _cfpContract.getSource();
    }

    /**
    * Sets the source of this cfp contract.
    *
    * @param source the source of this cfp contract
    */
    @Override
    public void setSource(java.lang.String source) {
        _cfpContract.setSource(source);
    }

    /**
    * Returns the cfp status of this cfp contract.
    *
    * @return the cfp status of this cfp contract
    */
    @Override
    public int getCfpStatus() {
        return _cfpContract.getCfpStatus();
    }

    /**
    * Sets the cfp status of this cfp contract.
    *
    * @param cfpStatus the cfp status of this cfp contract
    */
    @Override
    public void setCfpStatus(int cfpStatus) {
        _cfpContract.setCfpStatus(cfpStatus);
    }

    /**
    * Returns the parent cfp ID of this cfp contract.
    *
    * @return the parent cfp ID of this cfp contract
    */
    @Override
    public int getParentCfpId() {
        return _cfpContract.getParentCfpId();
    }

    /**
    * Sets the parent cfp ID of this cfp contract.
    *
    * @param parentCfpId the parent cfp ID of this cfp contract
    */
    @Override
    public void setParentCfpId(int parentCfpId) {
        _cfpContract.setParentCfpId(parentCfpId);
    }

    /**
    * Returns the cfp contract attached status of this cfp contract.
    *
    * @return the cfp contract attached status of this cfp contract
    */
    @Override
    public int getCfpContractAttachedStatus() {
        return _cfpContract.getCfpContractAttachedStatus();
    }

    /**
    * Sets the cfp contract attached status of this cfp contract.
    *
    * @param cfpContractAttachedStatus the cfp contract attached status of this cfp contract
    */
    @Override
    public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
        _cfpContract.setCfpContractAttachedStatus(cfpContractAttachedStatus);
    }

    /**
    * Returns the cfp start date of this cfp contract.
    *
    * @return the cfp start date of this cfp contract
    */
    @Override
    public java.util.Date getCfpStartDate() {
        return _cfpContract.getCfpStartDate();
    }

    /**
    * Sets the cfp start date of this cfp contract.
    *
    * @param cfpStartDate the cfp start date of this cfp contract
    */
    @Override
    public void setCfpStartDate(java.util.Date cfpStartDate) {
        _cfpContract.setCfpStartDate(cfpStartDate);
    }

    /**
    * Returns the cfp end date of this cfp contract.
    *
    * @return the cfp end date of this cfp contract
    */
    @Override
    public java.util.Date getCfpEndDate() {
        return _cfpContract.getCfpEndDate();
    }

    /**
    * Sets the cfp end date of this cfp contract.
    *
    * @param cfpEndDate the cfp end date of this cfp contract
    */
    @Override
    public void setCfpEndDate(java.util.Date cfpEndDate) {
        _cfpContract.setCfpEndDate(cfpEndDate);
    }

    /**
    * Returns the parent cfp name of this cfp contract.
    *
    * @return the parent cfp name of this cfp contract
    */
    @Override
    public java.lang.String getParentCfpName() {
        return _cfpContract.getParentCfpName();
    }

    /**
    * Sets the parent cfp name of this cfp contract.
    *
    * @param parentCfpName the parent cfp name of this cfp contract
    */
    @Override
    public void setParentCfpName(java.lang.String parentCfpName) {
        _cfpContract.setParentCfpName(parentCfpName);
    }

    /**
    * Returns the inbound status of this cfp contract.
    *
    * @return the inbound status of this cfp contract
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cfpContract.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cfp contract.
    *
    * @param inboundStatus the inbound status of this cfp contract
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cfpContract.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the sales inclusion of this cfp contract.
    *
    * @return the sales inclusion of this cfp contract
    */
    @Override
    public int getSalesInclusion() {
        return _cfpContract.getSalesInclusion();
    }

    /**
    * Sets the sales inclusion of this cfp contract.
    *
    * @param salesInclusion the sales inclusion of this cfp contract
    */
    @Override
    public void setSalesInclusion(int salesInclusion) {
        _cfpContract.setSalesInclusion(salesInclusion);
    }

    @Override
    public boolean isNew() {
        return _cfpContract.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cfpContract.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cfpContract.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cfpContract.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cfpContract.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cfpContract.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cfpContract.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cfpContract.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cfpContract.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cfpContract.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cfpContract.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CfpContractWrapper((CfpContract) _cfpContract.clone());
    }

    @Override
    public int compareTo(CfpContract cfpContract) {
        return _cfpContract.compareTo(cfpContract);
    }

    @Override
    public int hashCode() {
        return _cfpContract.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CfpContract> toCacheModel() {
        return _cfpContract.toCacheModel();
    }

    @Override
    public CfpContract toEscapedModel() {
        return new CfpContractWrapper(_cfpContract.toEscapedModel());
    }

    @Override
    public CfpContract toUnescapedModel() {
        return new CfpContractWrapper(_cfpContract.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cfpContract.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cfpContract.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cfpContract.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CfpContractWrapper)) {
            return false;
        }

        CfpContractWrapper cfpContractWrapper = (CfpContractWrapper) obj;

        if (Validator.equals(_cfpContract, cfpContractWrapper._cfpContract)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CfpContract getWrappedCfpContract() {
        return _cfpContract;
    }

    @Override
    public CfpContract getWrappedModel() {
        return _cfpContract;
    }

    @Override
    public void resetOriginalValues() {
        _cfpContract.resetOriginalValues();
    }
}
