package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CpiIndexMaster}.
 * </p>
 *
 * @author
 * @see CpiIndexMaster
 * @generated
 */
public class CpiIndexMasterWrapper implements CpiIndexMaster,
    ModelWrapper<CpiIndexMaster> {
    private CpiIndexMaster _cpiIndexMaster;

    public CpiIndexMasterWrapper(CpiIndexMaster cpiIndexMaster) {
        _cpiIndexMaster = cpiIndexMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return CpiIndexMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CpiIndexMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("effectiveDate", getEffectiveDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cpiIndexMasterSid", getCpiIndexMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("status", getStatus());
        attributes.put("indexType", getIndexType());
        attributes.put("indexId", getIndexId());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("indexValue", getIndexValue());
        attributes.put("source", getSource());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date effectiveDate = (Date) attributes.get("effectiveDate");

        if (effectiveDate != null) {
            setEffectiveDate(effectiveDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer cpiIndexMasterSid = (Integer) attributes.get(
                "cpiIndexMasterSid");

        if (cpiIndexMasterSid != null) {
            setCpiIndexMasterSid(cpiIndexMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String indexType = (String) attributes.get("indexType");

        if (indexType != null) {
            setIndexType(indexType);
        }

        String indexId = (String) attributes.get("indexId");

        if (indexId != null) {
            setIndexId(indexId);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String indexValue = (String) attributes.get("indexValue");

        if (indexValue != null) {
            setIndexValue(indexValue);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this cpi index master.
    *
    * @return the primary key of this cpi index master
    */
    @Override
    public int getPrimaryKey() {
        return _cpiIndexMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cpi index master.
    *
    * @param primaryKey the primary key of this cpi index master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cpiIndexMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the effective date of this cpi index master.
    *
    * @return the effective date of this cpi index master
    */
    @Override
    public java.util.Date getEffectiveDate() {
        return _cpiIndexMaster.getEffectiveDate();
    }

    /**
    * Sets the effective date of this cpi index master.
    *
    * @param effectiveDate the effective date of this cpi index master
    */
    @Override
    public void setEffectiveDate(java.util.Date effectiveDate) {
        _cpiIndexMaster.setEffectiveDate(effectiveDate);
    }

    /**
    * Returns the created by of this cpi index master.
    *
    * @return the created by of this cpi index master
    */
    @Override
    public int getCreatedBy() {
        return _cpiIndexMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this cpi index master.
    *
    * @param createdBy the created by of this cpi index master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cpiIndexMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the modified by of this cpi index master.
    *
    * @return the modified by of this cpi index master
    */
    @Override
    public int getModifiedBy() {
        return _cpiIndexMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this cpi index master.
    *
    * @param modifiedBy the modified by of this cpi index master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cpiIndexMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this cpi index master.
    *
    * @return the created date of this cpi index master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cpiIndexMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this cpi index master.
    *
    * @param createdDate the created date of this cpi index master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cpiIndexMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the cpi index master sid of this cpi index master.
    *
    * @return the cpi index master sid of this cpi index master
    */
    @Override
    public int getCpiIndexMasterSid() {
        return _cpiIndexMaster.getCpiIndexMasterSid();
    }

    /**
    * Sets the cpi index master sid of this cpi index master.
    *
    * @param cpiIndexMasterSid the cpi index master sid of this cpi index master
    */
    @Override
    public void setCpiIndexMasterSid(int cpiIndexMasterSid) {
        _cpiIndexMaster.setCpiIndexMasterSid(cpiIndexMasterSid);
    }

    /**
    * Returns the batch ID of this cpi index master.
    *
    * @return the batch ID of this cpi index master
    */
    @Override
    public java.lang.String getBatchId() {
        return _cpiIndexMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this cpi index master.
    *
    * @param batchId the batch ID of this cpi index master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _cpiIndexMaster.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this cpi index master.
    *
    * @return the modified date of this cpi index master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cpiIndexMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this cpi index master.
    *
    * @param modifiedDate the modified date of this cpi index master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cpiIndexMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the status of this cpi index master.
    *
    * @return the status of this cpi index master
    */
    @Override
    public java.lang.String getStatus() {
        return _cpiIndexMaster.getStatus();
    }

    /**
    * Sets the status of this cpi index master.
    *
    * @param status the status of this cpi index master
    */
    @Override
    public void setStatus(java.lang.String status) {
        _cpiIndexMaster.setStatus(status);
    }

    /**
    * Returns the index type of this cpi index master.
    *
    * @return the index type of this cpi index master
    */
    @Override
    public java.lang.String getIndexType() {
        return _cpiIndexMaster.getIndexType();
    }

    /**
    * Sets the index type of this cpi index master.
    *
    * @param indexType the index type of this cpi index master
    */
    @Override
    public void setIndexType(java.lang.String indexType) {
        _cpiIndexMaster.setIndexType(indexType);
    }

    /**
    * Returns the index ID of this cpi index master.
    *
    * @return the index ID of this cpi index master
    */
    @Override
    public java.lang.String getIndexId() {
        return _cpiIndexMaster.getIndexId();
    }

    /**
    * Sets the index ID of this cpi index master.
    *
    * @param indexId the index ID of this cpi index master
    */
    @Override
    public void setIndexId(java.lang.String indexId) {
        _cpiIndexMaster.setIndexId(indexId);
    }

    /**
    * Returns the record lock status of this cpi index master.
    *
    * @return the record lock status of this cpi index master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _cpiIndexMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this cpi index master is record lock status.
    *
    * @return <code>true</code> if this cpi index master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _cpiIndexMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this cpi index master is record lock status.
    *
    * @param recordLockStatus the record lock status of this cpi index master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _cpiIndexMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the index value of this cpi index master.
    *
    * @return the index value of this cpi index master
    */
    @Override
    public java.lang.String getIndexValue() {
        return _cpiIndexMaster.getIndexValue();
    }

    /**
    * Sets the index value of this cpi index master.
    *
    * @param indexValue the index value of this cpi index master
    */
    @Override
    public void setIndexValue(java.lang.String indexValue) {
        _cpiIndexMaster.setIndexValue(indexValue);
    }

    /**
    * Returns the source of this cpi index master.
    *
    * @return the source of this cpi index master
    */
    @Override
    public java.lang.String getSource() {
        return _cpiIndexMaster.getSource();
    }

    /**
    * Sets the source of this cpi index master.
    *
    * @param source the source of this cpi index master
    */
    @Override
    public void setSource(java.lang.String source) {
        _cpiIndexMaster.setSource(source);
    }

    /**
    * Returns the inbound status of this cpi index master.
    *
    * @return the inbound status of this cpi index master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cpiIndexMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cpi index master.
    *
    * @param inboundStatus the inbound status of this cpi index master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cpiIndexMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _cpiIndexMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cpiIndexMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cpiIndexMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cpiIndexMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cpiIndexMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cpiIndexMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cpiIndexMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cpiIndexMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cpiIndexMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cpiIndexMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cpiIndexMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CpiIndexMasterWrapper((CpiIndexMaster) _cpiIndexMaster.clone());
    }

    @Override
    public int compareTo(CpiIndexMaster cpiIndexMaster) {
        return _cpiIndexMaster.compareTo(cpiIndexMaster);
    }

    @Override
    public int hashCode() {
        return _cpiIndexMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CpiIndexMaster> toCacheModel() {
        return _cpiIndexMaster.toCacheModel();
    }

    @Override
    public CpiIndexMaster toEscapedModel() {
        return new CpiIndexMasterWrapper(_cpiIndexMaster.toEscapedModel());
    }

    @Override
    public CpiIndexMaster toUnescapedModel() {
        return new CpiIndexMasterWrapper(_cpiIndexMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cpiIndexMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cpiIndexMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cpiIndexMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CpiIndexMasterWrapper)) {
            return false;
        }

        CpiIndexMasterWrapper cpiIndexMasterWrapper = (CpiIndexMasterWrapper) obj;

        if (Validator.equals(_cpiIndexMaster,
                    cpiIndexMasterWrapper._cpiIndexMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CpiIndexMaster getWrappedCpiIndexMaster() {
        return _cpiIndexMaster;
    }

    @Override
    public CpiIndexMaster getWrappedModel() {
        return _cpiIndexMaster;
    }

    @Override
    public void resetOriginalValues() {
        _cpiIndexMaster.resetOriginalValues();
    }
}
