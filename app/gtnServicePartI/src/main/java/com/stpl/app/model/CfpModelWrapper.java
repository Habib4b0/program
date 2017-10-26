package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CfpModel}.
 * </p>
 *
 * @author
 * @see CfpModel
 * @generated
 */
public class CfpModelWrapper implements CfpModel, ModelWrapper<CfpModel> {
    private CfpModel _cfpModel;

    public CfpModelWrapper(CfpModel cfpModel) {
        _cfpModel = cfpModel;
    }

    @Override
    public Class<?> getModelClass() {
        return CfpModel.class;
    }

    @Override
    public String getModelClassName() {
        return CfpModel.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("cfpType", getCfpType());
        attributes.put("cfpTradeClass", getCfpTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpNo", getCfpNo());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("cfpDesignation", getCfpDesignation());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("cfpName", getCfpName());
        attributes.put("cfpCategory", getCfpCategory());
        attributes.put("source", getSource());
        attributes.put("cfpId", getCfpId());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("parentCfpId", getParentCfpId());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("parentCfpName", getParentCfpName());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
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

        String cfpNo = (String) attributes.get("cfpNo");

        if (cfpNo != null) {
            setCfpNo(cfpNo);
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

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String cfpDesignation = (String) attributes.get("cfpDesignation");

        if (cfpDesignation != null) {
            setCfpDesignation(cfpDesignation);
        }

        Integer salesInclusion = (Integer) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String cfpName = (String) attributes.get("cfpName");

        if (cfpName != null) {
            setCfpName(cfpName);
        }

        Integer cfpCategory = (Integer) attributes.get("cfpCategory");

        if (cfpCategory != null) {
            setCfpCategory(cfpCategory);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String cfpId = (String) attributes.get("cfpId");

        if (cfpId != null) {
            setCfpId(cfpId);
        }

        Integer cfpStatus = (Integer) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer parentCfpId = (Integer) attributes.get("parentCfpId");

        if (parentCfpId != null) {
            setParentCfpId(parentCfpId);
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
    }

    /**
    * Returns the primary key of this cfp model.
    *
    * @return the primary key of this cfp model
    */
    @Override
    public int getPrimaryKey() {
        return _cfpModel.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cfp model.
    *
    * @param primaryKey the primary key of this cfp model
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cfpModel.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cfp model.
    *
    * @return the created by of this cfp model
    */
    @Override
    public int getCreatedBy() {
        return _cfpModel.getCreatedBy();
    }

    /**
    * Sets the created by of this cfp model.
    *
    * @param createdBy the created by of this cfp model
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cfpModel.setCreatedBy(createdBy);
    }

    /**
    * Returns the cfp type of this cfp model.
    *
    * @return the cfp type of this cfp model
    */
    @Override
    public int getCfpType() {
        return _cfpModel.getCfpType();
    }

    /**
    * Sets the cfp type of this cfp model.
    *
    * @param cfpType the cfp type of this cfp model
    */
    @Override
    public void setCfpType(int cfpType) {
        _cfpModel.setCfpType(cfpType);
    }

    /**
    * Returns the cfp trade class of this cfp model.
    *
    * @return the cfp trade class of this cfp model
    */
    @Override
    public int getCfpTradeClass() {
        return _cfpModel.getCfpTradeClass();
    }

    /**
    * Sets the cfp trade class of this cfp model.
    *
    * @param cfpTradeClass the cfp trade class of this cfp model
    */
    @Override
    public void setCfpTradeClass(int cfpTradeClass) {
        _cfpModel.setCfpTradeClass(cfpTradeClass);
    }

    /**
    * Returns the modified by of this cfp model.
    *
    * @return the modified by of this cfp model
    */
    @Override
    public int getModifiedBy() {
        return _cfpModel.getModifiedBy();
    }

    /**
    * Sets the modified by of this cfp model.
    *
    * @param modifiedBy the modified by of this cfp model
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cfpModel.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this cfp model.
    *
    * @return the created date of this cfp model
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cfpModel.getCreatedDate();
    }

    /**
    * Sets the created date of this cfp model.
    *
    * @param createdDate the created date of this cfp model
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cfpModel.setCreatedDate(createdDate);
    }

    /**
    * Returns the cfp no of this cfp model.
    *
    * @return the cfp no of this cfp model
    */
    @Override
    public java.lang.String getCfpNo() {
        return _cfpModel.getCfpNo();
    }

    /**
    * Sets the cfp no of this cfp model.
    *
    * @param cfpNo the cfp no of this cfp model
    */
    @Override
    public void setCfpNo(java.lang.String cfpNo) {
        _cfpModel.setCfpNo(cfpNo);
    }

    /**
    * Returns the cfp model sid of this cfp model.
    *
    * @return the cfp model sid of this cfp model
    */
    @Override
    public int getCfpModelSid() {
        return _cfpModel.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this cfp model.
    *
    * @param cfpModelSid the cfp model sid of this cfp model
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModel.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the batch ID of this cfp model.
    *
    * @return the batch ID of this cfp model
    */
    @Override
    public java.lang.String getBatchId() {
        return _cfpModel.getBatchId();
    }

    /**
    * Sets the batch ID of this cfp model.
    *
    * @param batchId the batch ID of this cfp model
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _cfpModel.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this cfp model.
    *
    * @return the modified date of this cfp model
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cfpModel.getModifiedDate();
    }

    /**
    * Sets the modified date of this cfp model.
    *
    * @param modifiedDate the modified date of this cfp model
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cfpModel.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this cfp model.
    *
    * @return the record lock status of this cfp model
    */
    @Override
    public boolean getRecordLockStatus() {
        return _cfpModel.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this cfp model is record lock status.
    *
    * @return <code>true</code> if this cfp model is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _cfpModel.isRecordLockStatus();
    }

    /**
    * Sets whether this cfp model is record lock status.
    *
    * @param recordLockStatus the record lock status of this cfp model
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _cfpModel.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the internal notes of this cfp model.
    *
    * @return the internal notes of this cfp model
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _cfpModel.getInternalNotes();
    }

    /**
    * Sets the internal notes of this cfp model.
    *
    * @param internalNotes the internal notes of this cfp model
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _cfpModel.setInternalNotes(internalNotes);
    }

    /**
    * Returns the cfp designation of this cfp model.
    *
    * @return the cfp designation of this cfp model
    */
    @Override
    public java.lang.String getCfpDesignation() {
        return _cfpModel.getCfpDesignation();
    }

    /**
    * Sets the cfp designation of this cfp model.
    *
    * @param cfpDesignation the cfp designation of this cfp model
    */
    @Override
    public void setCfpDesignation(java.lang.String cfpDesignation) {
        _cfpModel.setCfpDesignation(cfpDesignation);
    }

    /**
    * Returns the sales inclusion of this cfp model.
    *
    * @return the sales inclusion of this cfp model
    */
    @Override
    public int getSalesInclusion() {
        return _cfpModel.getSalesInclusion();
    }

    /**
    * Sets the sales inclusion of this cfp model.
    *
    * @param salesInclusion the sales inclusion of this cfp model
    */
    @Override
    public void setSalesInclusion(int salesInclusion) {
        _cfpModel.setSalesInclusion(salesInclusion);
    }

    /**
    * Returns the cfp name of this cfp model.
    *
    * @return the cfp name of this cfp model
    */
    @Override
    public java.lang.String getCfpName() {
        return _cfpModel.getCfpName();
    }

    /**
    * Sets the cfp name of this cfp model.
    *
    * @param cfpName the cfp name of this cfp model
    */
    @Override
    public void setCfpName(java.lang.String cfpName) {
        _cfpModel.setCfpName(cfpName);
    }

    /**
    * Returns the cfp category of this cfp model.
    *
    * @return the cfp category of this cfp model
    */
    @Override
    public int getCfpCategory() {
        return _cfpModel.getCfpCategory();
    }

    /**
    * Sets the cfp category of this cfp model.
    *
    * @param cfpCategory the cfp category of this cfp model
    */
    @Override
    public void setCfpCategory(int cfpCategory) {
        _cfpModel.setCfpCategory(cfpCategory);
    }

    /**
    * Returns the source of this cfp model.
    *
    * @return the source of this cfp model
    */
    @Override
    public java.lang.String getSource() {
        return _cfpModel.getSource();
    }

    /**
    * Sets the source of this cfp model.
    *
    * @param source the source of this cfp model
    */
    @Override
    public void setSource(java.lang.String source) {
        _cfpModel.setSource(source);
    }

    /**
    * Returns the cfp ID of this cfp model.
    *
    * @return the cfp ID of this cfp model
    */
    @Override
    public java.lang.String getCfpId() {
        return _cfpModel.getCfpId();
    }

    /**
    * Sets the cfp ID of this cfp model.
    *
    * @param cfpId the cfp ID of this cfp model
    */
    @Override
    public void setCfpId(java.lang.String cfpId) {
        _cfpModel.setCfpId(cfpId);
    }

    /**
    * Returns the cfp status of this cfp model.
    *
    * @return the cfp status of this cfp model
    */
    @Override
    public int getCfpStatus() {
        return _cfpModel.getCfpStatus();
    }

    /**
    * Sets the cfp status of this cfp model.
    *
    * @param cfpStatus the cfp status of this cfp model
    */
    @Override
    public void setCfpStatus(int cfpStatus) {
        _cfpModel.setCfpStatus(cfpStatus);
    }

    /**
    * Returns the parent cfp ID of this cfp model.
    *
    * @return the parent cfp ID of this cfp model
    */
    @Override
    public int getParentCfpId() {
        return _cfpModel.getParentCfpId();
    }

    /**
    * Sets the parent cfp ID of this cfp model.
    *
    * @param parentCfpId the parent cfp ID of this cfp model
    */
    @Override
    public void setParentCfpId(int parentCfpId) {
        _cfpModel.setParentCfpId(parentCfpId);
    }

    /**
    * Returns the cfp start date of this cfp model.
    *
    * @return the cfp start date of this cfp model
    */
    @Override
    public java.util.Date getCfpStartDate() {
        return _cfpModel.getCfpStartDate();
    }

    /**
    * Sets the cfp start date of this cfp model.
    *
    * @param cfpStartDate the cfp start date of this cfp model
    */
    @Override
    public void setCfpStartDate(java.util.Date cfpStartDate) {
        _cfpModel.setCfpStartDate(cfpStartDate);
    }

    /**
    * Returns the cfp end date of this cfp model.
    *
    * @return the cfp end date of this cfp model
    */
    @Override
    public java.util.Date getCfpEndDate() {
        return _cfpModel.getCfpEndDate();
    }

    /**
    * Sets the cfp end date of this cfp model.
    *
    * @param cfpEndDate the cfp end date of this cfp model
    */
    @Override
    public void setCfpEndDate(java.util.Date cfpEndDate) {
        _cfpModel.setCfpEndDate(cfpEndDate);
    }

    /**
    * Returns the parent cfp name of this cfp model.
    *
    * @return the parent cfp name of this cfp model
    */
    @Override
    public java.lang.String getParentCfpName() {
        return _cfpModel.getParentCfpName();
    }

    /**
    * Sets the parent cfp name of this cfp model.
    *
    * @param parentCfpName the parent cfp name of this cfp model
    */
    @Override
    public void setParentCfpName(java.lang.String parentCfpName) {
        _cfpModel.setParentCfpName(parentCfpName);
    }

    /**
    * Returns the inbound status of this cfp model.
    *
    * @return the inbound status of this cfp model
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cfpModel.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cfp model.
    *
    * @param inboundStatus the inbound status of this cfp model
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cfpModel.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _cfpModel.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cfpModel.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cfpModel.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cfpModel.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cfpModel.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cfpModel.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cfpModel.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cfpModel.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cfpModel.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cfpModel.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cfpModel.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CfpModelWrapper((CfpModel) _cfpModel.clone());
    }

    @Override
    public int compareTo(CfpModel cfpModel) {
        return _cfpModel.compareTo(cfpModel);
    }

    @Override
    public int hashCode() {
        return _cfpModel.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CfpModel> toCacheModel() {
        return _cfpModel.toCacheModel();
    }

    @Override
    public CfpModel toEscapedModel() {
        return new CfpModelWrapper(_cfpModel.toEscapedModel());
    }

    @Override
    public CfpModel toUnescapedModel() {
        return new CfpModelWrapper(_cfpModel.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cfpModel.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cfpModel.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cfpModel.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CfpModelWrapper)) {
            return false;
        }

        CfpModelWrapper cfpModelWrapper = (CfpModelWrapper) obj;

        if (Validator.equals(_cfpModel, cfpModelWrapper._cfpModel)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CfpModel getWrappedCfpModel() {
        return _cfpModel;
    }

    @Override
    public CfpModel getWrappedModel() {
        return _cfpModel;
    }

    @Override
    public void resetOriginalValues() {
        _cfpModel.resetOriginalValues();
    }
}
