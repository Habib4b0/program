package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCpi}.
 * </p>
 *
 * @author
 * @see IvldCpi
 * @generated
 */
public class IvldCpiWrapper implements IvldCpi, ModelWrapper<IvldCpi> {
    private IvldCpi _ivldCpi;

    public IvldCpiWrapper(IvldCpi ivldCpi) {
        _ivldCpi = ivldCpi;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCpi.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCpi.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("effectiveDate", getEffectiveDate());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("indexType", getIndexType());
        attributes.put("status", getStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("cpiIntfid", getCpiIntfid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("indexValue", getIndexValue());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("ivldCpiSid", getIvldCpiSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("indexId", getIndexId());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String effectiveDate = (String) attributes.get("effectiveDate");

        if (effectiveDate != null) {
            setEffectiveDate(effectiveDate);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String indexType = (String) attributes.get("indexType");

        if (indexType != null) {
            setIndexType(indexType);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String cpiIntfid = (String) attributes.get("cpiIntfid");

        if (cpiIntfid != null) {
            setCpiIntfid(cpiIntfid);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String indexValue = (String) attributes.get("indexValue");

        if (indexValue != null) {
            setIndexValue(indexValue);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer ivldCpiSid = (Integer) attributes.get("ivldCpiSid");

        if (ivldCpiSid != null) {
            setIvldCpiSid(ivldCpiSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String indexId = (String) attributes.get("indexId");

        if (indexId != null) {
            setIndexId(indexId);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld cpi.
    *
    * @return the primary key of this ivld cpi
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCpi.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld cpi.
    *
    * @param primaryKey the primary key of this ivld cpi
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCpi.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the effective date of this ivld cpi.
    *
    * @return the effective date of this ivld cpi
    */
    @Override
    public java.lang.String getEffectiveDate() {
        return _ivldCpi.getEffectiveDate();
    }

    /**
    * Sets the effective date of this ivld cpi.
    *
    * @param effectiveDate the effective date of this ivld cpi
    */
    @Override
    public void setEffectiveDate(java.lang.String effectiveDate) {
        _ivldCpi.setEffectiveDate(effectiveDate);
    }

    /**
    * Returns the reason for failure of this ivld cpi.
    *
    * @return the reason for failure of this ivld cpi
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCpi.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld cpi.
    *
    * @param reasonForFailure the reason for failure of this ivld cpi
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCpi.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the index type of this ivld cpi.
    *
    * @return the index type of this ivld cpi
    */
    @Override
    public java.lang.String getIndexType() {
        return _ivldCpi.getIndexType();
    }

    /**
    * Sets the index type of this ivld cpi.
    *
    * @param indexType the index type of this ivld cpi
    */
    @Override
    public void setIndexType(java.lang.String indexType) {
        _ivldCpi.setIndexType(indexType);
    }

    /**
    * Returns the status of this ivld cpi.
    *
    * @return the status of this ivld cpi
    */
    @Override
    public java.lang.String getStatus() {
        return _ivldCpi.getStatus();
    }

    /**
    * Sets the status of this ivld cpi.
    *
    * @param status the status of this ivld cpi
    */
    @Override
    public void setStatus(java.lang.String status) {
        _ivldCpi.setStatus(status);
    }

    /**
    * Returns the modified date of this ivld cpi.
    *
    * @return the modified date of this ivld cpi
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCpi.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld cpi.
    *
    * @param modifiedDate the modified date of this ivld cpi
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCpi.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the cpi intfid of this ivld cpi.
    *
    * @return the cpi intfid of this ivld cpi
    */
    @Override
    public java.lang.String getCpiIntfid() {
        return _ivldCpi.getCpiIntfid();
    }

    /**
    * Sets the cpi intfid of this ivld cpi.
    *
    * @param cpiIntfid the cpi intfid of this ivld cpi
    */
    @Override
    public void setCpiIntfid(java.lang.String cpiIntfid) {
        _ivldCpi.setCpiIntfid(cpiIntfid);
    }

    /**
    * Returns the created by of this ivld cpi.
    *
    * @return the created by of this ivld cpi
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCpi.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld cpi.
    *
    * @param createdBy the created by of this ivld cpi
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCpi.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld cpi.
    *
    * @return the created date of this ivld cpi
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldCpi.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld cpi.
    *
    * @param createdDate the created date of this ivld cpi
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldCpi.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld cpi.
    *
    * @return the source of this ivld cpi
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCpi.getSource();
    }

    /**
    * Sets the source of this ivld cpi.
    *
    * @param source the source of this ivld cpi
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCpi.setSource(source);
    }

    /**
    * Returns the index value of this ivld cpi.
    *
    * @return the index value of this ivld cpi
    */
    @Override
    public java.lang.String getIndexValue() {
        return _ivldCpi.getIndexValue();
    }

    /**
    * Sets the index value of this ivld cpi.
    *
    * @param indexValue the index value of this ivld cpi
    */
    @Override
    public void setIndexValue(java.lang.String indexValue) {
        _ivldCpi.setIndexValue(indexValue);
    }

    /**
    * Returns the add chg del indicator of this ivld cpi.
    *
    * @return the add chg del indicator of this ivld cpi
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCpi.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld cpi.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld cpi
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCpi.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the batch ID of this ivld cpi.
    *
    * @return the batch ID of this ivld cpi
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCpi.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld cpi.
    *
    * @param batchId the batch ID of this ivld cpi
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCpi.setBatchId(batchId);
    }

    /**
    * Returns the ivld cpi sid of this ivld cpi.
    *
    * @return the ivld cpi sid of this ivld cpi
    */
    @Override
    public int getIvldCpiSid() {
        return _ivldCpi.getIvldCpiSid();
    }

    /**
    * Sets the ivld cpi sid of this ivld cpi.
    *
    * @param ivldCpiSid the ivld cpi sid of this ivld cpi
    */
    @Override
    public void setIvldCpiSid(int ivldCpiSid) {
        _ivldCpi.setIvldCpiSid(ivldCpiSid);
    }

    /**
    * Returns the error field of this ivld cpi.
    *
    * @return the error field of this ivld cpi
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCpi.getErrorField();
    }

    /**
    * Sets the error field of this ivld cpi.
    *
    * @param errorField the error field of this ivld cpi
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCpi.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld cpi.
    *
    * @return the error code of this ivld cpi
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCpi.getErrorCode();
    }

    /**
    * Sets the error code of this ivld cpi.
    *
    * @param errorCode the error code of this ivld cpi
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCpi.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld cpi.
    *
    * @return the intf inserted date of this ivld cpi
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCpi.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld cpi.
    *
    * @param intfInsertedDate the intf inserted date of this ivld cpi
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCpi.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld cpi.
    *
    * @return the modified by of this ivld cpi
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCpi.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld cpi.
    *
    * @param modifiedBy the modified by of this ivld cpi
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCpi.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld cpi.
    *
    * @return the reprocessed flag of this ivld cpi
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCpi.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld cpi.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld cpi
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCpi.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the index ID of this ivld cpi.
    *
    * @return the index ID of this ivld cpi
    */
    @Override
    public java.lang.String getIndexId() {
        return _ivldCpi.getIndexId();
    }

    /**
    * Sets the index ID of this ivld cpi.
    *
    * @param indexId the index ID of this ivld cpi
    */
    @Override
    public void setIndexId(java.lang.String indexId) {
        _ivldCpi.setIndexId(indexId);
    }

    /**
    * Returns the check record of this ivld cpi.
    *
    * @return the check record of this ivld cpi
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldCpi.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld cpi is check record.
    *
    * @return <code>true</code> if this ivld cpi is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldCpi.isCheckRecord();
    }

    /**
    * Sets whether this ivld cpi is check record.
    *
    * @param checkRecord the check record of this ivld cpi
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldCpi.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldCpi.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCpi.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCpi.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCpi.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCpi.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCpi.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCpi.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCpi.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCpi.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCpi.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCpi.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCpiWrapper((IvldCpi) _ivldCpi.clone());
    }

    @Override
    public int compareTo(IvldCpi ivldCpi) {
        return _ivldCpi.compareTo(ivldCpi);
    }

    @Override
    public int hashCode() {
        return _ivldCpi.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCpi> toCacheModel() {
        return _ivldCpi.toCacheModel();
    }

    @Override
    public IvldCpi toEscapedModel() {
        return new IvldCpiWrapper(_ivldCpi.toEscapedModel());
    }

    @Override
    public IvldCpi toUnescapedModel() {
        return new IvldCpiWrapper(_ivldCpi.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCpi.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCpi.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCpi.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCpiWrapper)) {
            return false;
        }

        IvldCpiWrapper ivldCpiWrapper = (IvldCpiWrapper) obj;

        if (Validator.equals(_ivldCpi, ivldCpiWrapper._ivldCpi)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCpi getWrappedIvldCpi() {
        return _ivldCpi;
    }

    @Override
    public IvldCpi getWrappedModel() {
        return _ivldCpi;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCpi.resetOriginalValues();
    }
}
