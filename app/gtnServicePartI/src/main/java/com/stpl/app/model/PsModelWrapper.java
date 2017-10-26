package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PsModel}.
 * </p>
 *
 * @author
 * @see PsModel
 * @generated
 */
public class PsModelWrapper implements PsModel, ModelWrapper<PsModel> {
    private PsModel _psModel;

    public PsModelWrapper(PsModel psModel) {
        _psModel = psModel;
    }

    @Override
    public Class<?> getModelClass() {
        return PsModel.class;
    }

    @Override
    public String getModelClassName() {
        return PsModel.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("psId", getPsId());
        attributes.put("psName", getPsName());
        attributes.put("psType", getPsType());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("psCategory", getPsCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("psStatus", getPsStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("psNo", getPsNo());
        attributes.put("psDesignation", getPsDesignation());
        attributes.put("parentPsId", getParentPsId());
        attributes.put("batchId", getBatchId());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("psEndDate", getPsEndDate());
        attributes.put("psTradeClass", getPsTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("psStartDate", getPsStartDate());
        attributes.put("parentPsName", getParentPsName());
        attributes.put("internalNotes", getInternalNotes());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String psId = (String) attributes.get("psId");

        if (psId != null) {
            setPsId(psId);
        }

        String psName = (String) attributes.get("psName");

        if (psName != null) {
            setPsName(psName);
        }

        Integer psType = (Integer) attributes.get("psType");

        if (psType != null) {
            setPsType(psType);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer psCategory = (Integer) attributes.get("psCategory");

        if (psCategory != null) {
            setPsCategory(psCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer psStatus = (Integer) attributes.get("psStatus");

        if (psStatus != null) {
            setPsStatus(psStatus);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String psNo = (String) attributes.get("psNo");

        if (psNo != null) {
            setPsNo(psNo);
        }

        String psDesignation = (String) attributes.get("psDesignation");

        if (psDesignation != null) {
            setPsDesignation(psDesignation);
        }

        String parentPsId = (String) attributes.get("parentPsId");

        if (parentPsId != null) {
            setParentPsId(parentPsId);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Date psEndDate = (Date) attributes.get("psEndDate");

        if (psEndDate != null) {
            setPsEndDate(psEndDate);
        }

        Integer psTradeClass = (Integer) attributes.get("psTradeClass");

        if (psTradeClass != null) {
            setPsTradeClass(psTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Date psStartDate = (Date) attributes.get("psStartDate");

        if (psStartDate != null) {
            setPsStartDate(psStartDate);
        }

        String parentPsName = (String) attributes.get("parentPsName");

        if (parentPsName != null) {
            setParentPsName(parentPsName);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }
    }

    /**
    * Returns the primary key of this ps model.
    *
    * @return the primary key of this ps model
    */
    @Override
    public int getPrimaryKey() {
        return _psModel.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ps model.
    *
    * @param primaryKey the primary key of this ps model
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _psModel.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ps ID of this ps model.
    *
    * @return the ps ID of this ps model
    */
    @Override
    public java.lang.String getPsId() {
        return _psModel.getPsId();
    }

    /**
    * Sets the ps ID of this ps model.
    *
    * @param psId the ps ID of this ps model
    */
    @Override
    public void setPsId(java.lang.String psId) {
        _psModel.setPsId(psId);
    }

    /**
    * Returns the ps name of this ps model.
    *
    * @return the ps name of this ps model
    */
    @Override
    public java.lang.String getPsName() {
        return _psModel.getPsName();
    }

    /**
    * Sets the ps name of this ps model.
    *
    * @param psName the ps name of this ps model
    */
    @Override
    public void setPsName(java.lang.String psName) {
        _psModel.setPsName(psName);
    }

    /**
    * Returns the ps type of this ps model.
    *
    * @return the ps type of this ps model
    */
    @Override
    public int getPsType() {
        return _psModel.getPsType();
    }

    /**
    * Sets the ps type of this ps model.
    *
    * @param psType the ps type of this ps model
    */
    @Override
    public void setPsType(int psType) {
        _psModel.setPsType(psType);
    }

    /**
    * Returns the modified date of this ps model.
    *
    * @return the modified date of this ps model
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _psModel.getModifiedDate();
    }

    /**
    * Sets the modified date of this ps model.
    *
    * @param modifiedDate the modified date of this ps model
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _psModel.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ps category of this ps model.
    *
    * @return the ps category of this ps model
    */
    @Override
    public int getPsCategory() {
        return _psModel.getPsCategory();
    }

    /**
    * Sets the ps category of this ps model.
    *
    * @param psCategory the ps category of this ps model
    */
    @Override
    public void setPsCategory(int psCategory) {
        _psModel.setPsCategory(psCategory);
    }

    /**
    * Returns the record lock status of this ps model.
    *
    * @return the record lock status of this ps model
    */
    @Override
    public boolean getRecordLockStatus() {
        return _psModel.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this ps model is record lock status.
    *
    * @return <code>true</code> if this ps model is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _psModel.isRecordLockStatus();
    }

    /**
    * Sets whether this ps model is record lock status.
    *
    * @param recordLockStatus the record lock status of this ps model
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _psModel.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the ps status of this ps model.
    *
    * @return the ps status of this ps model
    */
    @Override
    public int getPsStatus() {
        return _psModel.getPsStatus();
    }

    /**
    * Sets the ps status of this ps model.
    *
    * @param psStatus the ps status of this ps model
    */
    @Override
    public void setPsStatus(int psStatus) {
        _psModel.setPsStatus(psStatus);
    }

    /**
    * Returns the created date of this ps model.
    *
    * @return the created date of this ps model
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _psModel.getCreatedDate();
    }

    /**
    * Sets the created date of this ps model.
    *
    * @param createdDate the created date of this ps model
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _psModel.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ps model.
    *
    * @return the created by of this ps model
    */
    @Override
    public int getCreatedBy() {
        return _psModel.getCreatedBy();
    }

    /**
    * Sets the created by of this ps model.
    *
    * @param createdBy the created by of this ps model
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _psModel.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this ps model.
    *
    * @return the source of this ps model
    */
    @Override
    public java.lang.String getSource() {
        return _psModel.getSource();
    }

    /**
    * Sets the source of this ps model.
    *
    * @param source the source of this ps model
    */
    @Override
    public void setSource(java.lang.String source) {
        _psModel.setSource(source);
    }

    /**
    * Returns the ps no of this ps model.
    *
    * @return the ps no of this ps model
    */
    @Override
    public java.lang.String getPsNo() {
        return _psModel.getPsNo();
    }

    /**
    * Sets the ps no of this ps model.
    *
    * @param psNo the ps no of this ps model
    */
    @Override
    public void setPsNo(java.lang.String psNo) {
        _psModel.setPsNo(psNo);
    }

    /**
    * Returns the ps designation of this ps model.
    *
    * @return the ps designation of this ps model
    */
    @Override
    public java.lang.String getPsDesignation() {
        return _psModel.getPsDesignation();
    }

    /**
    * Sets the ps designation of this ps model.
    *
    * @param psDesignation the ps designation of this ps model
    */
    @Override
    public void setPsDesignation(java.lang.String psDesignation) {
        _psModel.setPsDesignation(psDesignation);
    }

    /**
    * Returns the parent ps ID of this ps model.
    *
    * @return the parent ps ID of this ps model
    */
    @Override
    public java.lang.String getParentPsId() {
        return _psModel.getParentPsId();
    }

    /**
    * Sets the parent ps ID of this ps model.
    *
    * @param parentPsId the parent ps ID of this ps model
    */
    @Override
    public void setParentPsId(java.lang.String parentPsId) {
        _psModel.setParentPsId(parentPsId);
    }

    /**
    * Returns the batch ID of this ps model.
    *
    * @return the batch ID of this ps model
    */
    @Override
    public java.lang.String getBatchId() {
        return _psModel.getBatchId();
    }

    /**
    * Sets the batch ID of this ps model.
    *
    * @param batchId the batch ID of this ps model
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _psModel.setBatchId(batchId);
    }

    /**
    * Returns the ps model sid of this ps model.
    *
    * @return the ps model sid of this ps model
    */
    @Override
    public int getPsModelSid() {
        return _psModel.getPsModelSid();
    }

    /**
    * Sets the ps model sid of this ps model.
    *
    * @param psModelSid the ps model sid of this ps model
    */
    @Override
    public void setPsModelSid(int psModelSid) {
        _psModel.setPsModelSid(psModelSid);
    }

    /**
    * Returns the ps end date of this ps model.
    *
    * @return the ps end date of this ps model
    */
    @Override
    public java.util.Date getPsEndDate() {
        return _psModel.getPsEndDate();
    }

    /**
    * Sets the ps end date of this ps model.
    *
    * @param psEndDate the ps end date of this ps model
    */
    @Override
    public void setPsEndDate(java.util.Date psEndDate) {
        _psModel.setPsEndDate(psEndDate);
    }

    /**
    * Returns the ps trade class of this ps model.
    *
    * @return the ps trade class of this ps model
    */
    @Override
    public int getPsTradeClass() {
        return _psModel.getPsTradeClass();
    }

    /**
    * Sets the ps trade class of this ps model.
    *
    * @param psTradeClass the ps trade class of this ps model
    */
    @Override
    public void setPsTradeClass(int psTradeClass) {
        _psModel.setPsTradeClass(psTradeClass);
    }

    /**
    * Returns the modified by of this ps model.
    *
    * @return the modified by of this ps model
    */
    @Override
    public int getModifiedBy() {
        return _psModel.getModifiedBy();
    }

    /**
    * Sets the modified by of this ps model.
    *
    * @param modifiedBy the modified by of this ps model
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _psModel.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this ps model.
    *
    * @return the inbound status of this ps model
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _psModel.getInboundStatus();
    }

    /**
    * Sets the inbound status of this ps model.
    *
    * @param inboundStatus the inbound status of this ps model
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _psModel.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the ps start date of this ps model.
    *
    * @return the ps start date of this ps model
    */
    @Override
    public java.util.Date getPsStartDate() {
        return _psModel.getPsStartDate();
    }

    /**
    * Sets the ps start date of this ps model.
    *
    * @param psStartDate the ps start date of this ps model
    */
    @Override
    public void setPsStartDate(java.util.Date psStartDate) {
        _psModel.setPsStartDate(psStartDate);
    }

    /**
    * Returns the parent ps name of this ps model.
    *
    * @return the parent ps name of this ps model
    */
    @Override
    public java.lang.String getParentPsName() {
        return _psModel.getParentPsName();
    }

    /**
    * Sets the parent ps name of this ps model.
    *
    * @param parentPsName the parent ps name of this ps model
    */
    @Override
    public void setParentPsName(java.lang.String parentPsName) {
        _psModel.setParentPsName(parentPsName);
    }

    /**
    * Returns the internal notes of this ps model.
    *
    * @return the internal notes of this ps model
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _psModel.getInternalNotes();
    }

    /**
    * Sets the internal notes of this ps model.
    *
    * @param internalNotes the internal notes of this ps model
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _psModel.setInternalNotes(internalNotes);
    }

    @Override
    public boolean isNew() {
        return _psModel.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _psModel.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _psModel.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _psModel.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _psModel.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _psModel.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _psModel.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _psModel.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _psModel.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _psModel.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _psModel.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PsModelWrapper((PsModel) _psModel.clone());
    }

    @Override
    public int compareTo(PsModel psModel) {
        return _psModel.compareTo(psModel);
    }

    @Override
    public int hashCode() {
        return _psModel.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<PsModel> toCacheModel() {
        return _psModel.toCacheModel();
    }

    @Override
    public PsModel toEscapedModel() {
        return new PsModelWrapper(_psModel.toEscapedModel());
    }

    @Override
    public PsModel toUnescapedModel() {
        return new PsModelWrapper(_psModel.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _psModel.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _psModel.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _psModel.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PsModelWrapper)) {
            return false;
        }

        PsModelWrapper psModelWrapper = (PsModelWrapper) obj;

        if (Validator.equals(_psModel, psModelWrapper._psModel)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PsModel getWrappedPsModel() {
        return _psModel;
    }

    @Override
    public PsModel getWrappedModel() {
        return _psModel;
    }

    @Override
    public void resetOriginalValues() {
        _psModel.resetOriginalValues();
    }
}
