package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCompanyParent}.
 * </p>
 *
 * @author
 * @see IvldCompanyParent
 * @generated
 */
public class IvldCompanyParentWrapper implements IvldCompanyParent,
    ModelWrapper<IvldCompanyParent> {
    private IvldCompanyParent _ivldCompanyParent;

    public IvldCompanyParentWrapper(IvldCompanyParent ivldCompanyParent) {
        _ivldCompanyParent = ivldCompanyParent;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyParent.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyParent.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parentCompanyId", getParentCompanyId());
        attributes.put("priorParentCompanyId", getPriorParentCompanyId());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("parentDetailsIntfid", getParentDetailsIntfid());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("ivldCompanyParentSid", getIvldCompanyParentSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("parentStartDate", getParentStartDate());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String parentCompanyId = (String) attributes.get("parentCompanyId");

        if (parentCompanyId != null) {
            setParentCompanyId(parentCompanyId);
        }

        String priorParentCompanyId = (String) attributes.get(
                "priorParentCompanyId");

        if (priorParentCompanyId != null) {
            setPriorParentCompanyId(priorParentCompanyId);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String parentEndDate = (String) attributes.get("parentEndDate");

        if (parentEndDate != null) {
            setParentEndDate(parentEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String parentDetailsIntfid = (String) attributes.get(
                "parentDetailsIntfid");

        if (parentDetailsIntfid != null) {
            setParentDetailsIntfid(parentDetailsIntfid);
        }

        String priorParentStartDate = (String) attributes.get(
                "priorParentStartDate");

        if (priorParentStartDate != null) {
            setPriorParentStartDate(priorParentStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
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

        Integer ivldCompanyParentSid = (Integer) attributes.get(
                "ivldCompanyParentSid");

        if (ivldCompanyParentSid != null) {
            setIvldCompanyParentSid(ivldCompanyParentSid);
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

        String parentStartDate = (String) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld company parent.
    *
    * @return the primary key of this ivld company parent
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCompanyParent.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld company parent.
    *
    * @param primaryKey the primary key of this ivld company parent
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCompanyParent.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the parent company ID of this ivld company parent.
    *
    * @return the parent company ID of this ivld company parent
    */
    @Override
    public java.lang.String getParentCompanyId() {
        return _ivldCompanyParent.getParentCompanyId();
    }

    /**
    * Sets the parent company ID of this ivld company parent.
    *
    * @param parentCompanyId the parent company ID of this ivld company parent
    */
    @Override
    public void setParentCompanyId(java.lang.String parentCompanyId) {
        _ivldCompanyParent.setParentCompanyId(parentCompanyId);
    }

    /**
    * Returns the prior parent company ID of this ivld company parent.
    *
    * @return the prior parent company ID of this ivld company parent
    */
    @Override
    public java.lang.String getPriorParentCompanyId() {
        return _ivldCompanyParent.getPriorParentCompanyId();
    }

    /**
    * Sets the prior parent company ID of this ivld company parent.
    *
    * @param priorParentCompanyId the prior parent company ID of this ivld company parent
    */
    @Override
    public void setPriorParentCompanyId(java.lang.String priorParentCompanyId) {
        _ivldCompanyParent.setPriorParentCompanyId(priorParentCompanyId);
    }

    /**
    * Returns the reason for failure of this ivld company parent.
    *
    * @return the reason for failure of this ivld company parent
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCompanyParent.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld company parent.
    *
    * @param reasonForFailure the reason for failure of this ivld company parent
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCompanyParent.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the company ID of this ivld company parent.
    *
    * @return the company ID of this ivld company parent
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldCompanyParent.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld company parent.
    *
    * @param companyId the company ID of this ivld company parent
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldCompanyParent.setCompanyId(companyId);
    }

    /**
    * Returns the last updated date of this ivld company parent.
    *
    * @return the last updated date of this ivld company parent
    */
    @Override
    public java.lang.String getLastUpdatedDate() {
        return _ivldCompanyParent.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this ivld company parent.
    *
    * @param lastUpdatedDate the last updated date of this ivld company parent
    */
    @Override
    public void setLastUpdatedDate(java.lang.String lastUpdatedDate) {
        _ivldCompanyParent.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the parent end date of this ivld company parent.
    *
    * @return the parent end date of this ivld company parent
    */
    @Override
    public java.lang.String getParentEndDate() {
        return _ivldCompanyParent.getParentEndDate();
    }

    /**
    * Sets the parent end date of this ivld company parent.
    *
    * @param parentEndDate the parent end date of this ivld company parent
    */
    @Override
    public void setParentEndDate(java.lang.String parentEndDate) {
        _ivldCompanyParent.setParentEndDate(parentEndDate);
    }

    /**
    * Returns the modified date of this ivld company parent.
    *
    * @return the modified date of this ivld company parent
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCompanyParent.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld company parent.
    *
    * @param modifiedDate the modified date of this ivld company parent
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCompanyParent.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the parent details intfid of this ivld company parent.
    *
    * @return the parent details intfid of this ivld company parent
    */
    @Override
    public java.lang.String getParentDetailsIntfid() {
        return _ivldCompanyParent.getParentDetailsIntfid();
    }

    /**
    * Sets the parent details intfid of this ivld company parent.
    *
    * @param parentDetailsIntfid the parent details intfid of this ivld company parent
    */
    @Override
    public void setParentDetailsIntfid(java.lang.String parentDetailsIntfid) {
        _ivldCompanyParent.setParentDetailsIntfid(parentDetailsIntfid);
    }

    /**
    * Returns the prior parent start date of this ivld company parent.
    *
    * @return the prior parent start date of this ivld company parent
    */
    @Override
    public java.lang.String getPriorParentStartDate() {
        return _ivldCompanyParent.getPriorParentStartDate();
    }

    /**
    * Sets the prior parent start date of this ivld company parent.
    *
    * @param priorParentStartDate the prior parent start date of this ivld company parent
    */
    @Override
    public void setPriorParentStartDate(java.lang.String priorParentStartDate) {
        _ivldCompanyParent.setPriorParentStartDate(priorParentStartDate);
    }

    /**
    * Returns the source of this ivld company parent.
    *
    * @return the source of this ivld company parent
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCompanyParent.getSource();
    }

    /**
    * Sets the source of this ivld company parent.
    *
    * @param source the source of this ivld company parent
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCompanyParent.setSource(source);
    }

    /**
    * Returns the created by of this ivld company parent.
    *
    * @return the created by of this ivld company parent
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCompanyParent.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld company parent.
    *
    * @param createdBy the created by of this ivld company parent
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCompanyParent.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld company parent.
    *
    * @return the created date of this ivld company parent
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldCompanyParent.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld company parent.
    *
    * @param createdDate the created date of this ivld company parent
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldCompanyParent.setCreatedDate(createdDate);
    }

    /**
    * Returns the add chg del indicator of this ivld company parent.
    *
    * @return the add chg del indicator of this ivld company parent
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCompanyParent.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld company parent.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld company parent
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCompanyParent.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the batch ID of this ivld company parent.
    *
    * @return the batch ID of this ivld company parent
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCompanyParent.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld company parent.
    *
    * @param batchId the batch ID of this ivld company parent
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCompanyParent.setBatchId(batchId);
    }

    /**
    * Returns the ivld company parent sid of this ivld company parent.
    *
    * @return the ivld company parent sid of this ivld company parent
    */
    @Override
    public int getIvldCompanyParentSid() {
        return _ivldCompanyParent.getIvldCompanyParentSid();
    }

    /**
    * Sets the ivld company parent sid of this ivld company parent.
    *
    * @param ivldCompanyParentSid the ivld company parent sid of this ivld company parent
    */
    @Override
    public void setIvldCompanyParentSid(int ivldCompanyParentSid) {
        _ivldCompanyParent.setIvldCompanyParentSid(ivldCompanyParentSid);
    }

    /**
    * Returns the error field of this ivld company parent.
    *
    * @return the error field of this ivld company parent
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCompanyParent.getErrorField();
    }

    /**
    * Sets the error field of this ivld company parent.
    *
    * @param errorField the error field of this ivld company parent
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCompanyParent.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld company parent.
    *
    * @return the error code of this ivld company parent
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCompanyParent.getErrorCode();
    }

    /**
    * Sets the error code of this ivld company parent.
    *
    * @param errorCode the error code of this ivld company parent
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCompanyParent.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld company parent.
    *
    * @return the intf inserted date of this ivld company parent
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCompanyParent.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld company parent.
    *
    * @param intfInsertedDate the intf inserted date of this ivld company parent
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCompanyParent.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld company parent.
    *
    * @return the modified by of this ivld company parent
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCompanyParent.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld company parent.
    *
    * @param modifiedBy the modified by of this ivld company parent
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCompanyParent.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld company parent.
    *
    * @return the reprocessed flag of this ivld company parent
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCompanyParent.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld company parent.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld company parent
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCompanyParent.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the parent start date of this ivld company parent.
    *
    * @return the parent start date of this ivld company parent
    */
    @Override
    public java.lang.String getParentStartDate() {
        return _ivldCompanyParent.getParentStartDate();
    }

    /**
    * Sets the parent start date of this ivld company parent.
    *
    * @param parentStartDate the parent start date of this ivld company parent
    */
    @Override
    public void setParentStartDate(java.lang.String parentStartDate) {
        _ivldCompanyParent.setParentStartDate(parentStartDate);
    }

    /**
    * Returns the check record of this ivld company parent.
    *
    * @return the check record of this ivld company parent
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldCompanyParent.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld company parent is check record.
    *
    * @return <code>true</code> if this ivld company parent is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldCompanyParent.isCheckRecord();
    }

    /**
    * Sets whether this ivld company parent is check record.
    *
    * @param checkRecord the check record of this ivld company parent
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldCompanyParent.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldCompanyParent.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCompanyParent.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCompanyParent.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCompanyParent.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCompanyParent.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCompanyParent.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCompanyParent.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCompanyParent.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCompanyParent.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCompanyParent.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCompanyParent.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCompanyParentWrapper((IvldCompanyParent) _ivldCompanyParent.clone());
    }

    @Override
    public int compareTo(IvldCompanyParent ivldCompanyParent) {
        return _ivldCompanyParent.compareTo(ivldCompanyParent);
    }

    @Override
    public int hashCode() {
        return _ivldCompanyParent.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCompanyParent> toCacheModel() {
        return _ivldCompanyParent.toCacheModel();
    }

    @Override
    public IvldCompanyParent toEscapedModel() {
        return new IvldCompanyParentWrapper(_ivldCompanyParent.toEscapedModel());
    }

    @Override
    public IvldCompanyParent toUnescapedModel() {
        return new IvldCompanyParentWrapper(_ivldCompanyParent.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCompanyParent.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCompanyParent.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCompanyParent.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCompanyParentWrapper)) {
            return false;
        }

        IvldCompanyParentWrapper ivldCompanyParentWrapper = (IvldCompanyParentWrapper) obj;

        if (Validator.equals(_ivldCompanyParent,
                    ivldCompanyParentWrapper._ivldCompanyParent)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCompanyParent getWrappedIvldCompanyParent() {
        return _ivldCompanyParent;
    }

    @Override
    public IvldCompanyParent getWrappedModel() {
        return _ivldCompanyParent;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCompanyParent.resetOriginalValues();
    }
}
