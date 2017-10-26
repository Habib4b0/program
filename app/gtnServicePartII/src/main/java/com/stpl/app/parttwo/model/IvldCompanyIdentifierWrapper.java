package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldCompanyIdentifier}.
 * </p>
 *
 * @author
 * @see IvldCompanyIdentifier
 * @generated
 */
public class IvldCompanyIdentifierWrapper implements IvldCompanyIdentifier,
    ModelWrapper<IvldCompanyIdentifier> {
    private IvldCompanyIdentifier _ivldCompanyIdentifier;

    public IvldCompanyIdentifierWrapper(
        IvldCompanyIdentifier ivldCompanyIdentifier) {
        _ivldCompanyIdentifier = ivldCompanyIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("companyName", getCompanyName());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("companyIdentifier", getCompanyIdentifier());
        attributes.put("entityCode", getEntityCode());
        attributes.put("companyIdentifierIntfid", getCompanyIdentifierIntfid());
        attributes.put("startDate", getStartDate());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("ivldCompanyIdentifierSid", getIvldCompanyIdentifierSid());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String identifierStatus = (String) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String companyIdentifier = (String) attributes.get("companyIdentifier");

        if (companyIdentifier != null) {
            setCompanyIdentifier(companyIdentifier);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String companyIdentifierIntfid = (String) attributes.get(
                "companyIdentifierIntfid");

        if (companyIdentifierIntfid != null) {
            setCompanyIdentifierIntfid(companyIdentifierIntfid);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
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

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer ivldCompanyIdentifierSid = (Integer) attributes.get(
                "ivldCompanyIdentifierSid");

        if (ivldCompanyIdentifierSid != null) {
            setIvldCompanyIdentifierSid(ivldCompanyIdentifierSid);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld company identifier.
    *
    * @return the primary key of this ivld company identifier
    */
    @Override
    public int getPrimaryKey() {
        return _ivldCompanyIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld company identifier.
    *
    * @param primaryKey the primary key of this ivld company identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldCompanyIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the reason for failure of this ivld company identifier.
    *
    * @return the reason for failure of this ivld company identifier
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldCompanyIdentifier.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld company identifier.
    *
    * @param reasonForFailure the reason for failure of this ivld company identifier
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldCompanyIdentifier.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the company ID of this ivld company identifier.
    *
    * @return the company ID of this ivld company identifier
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldCompanyIdentifier.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld company identifier.
    *
    * @param companyId the company ID of this ivld company identifier
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldCompanyIdentifier.setCompanyId(companyId);
    }

    /**
    * Returns the company name of this ivld company identifier.
    *
    * @return the company name of this ivld company identifier
    */
    @Override
    public java.lang.String getCompanyName() {
        return _ivldCompanyIdentifier.getCompanyName();
    }

    /**
    * Sets the company name of this ivld company identifier.
    *
    * @param companyName the company name of this ivld company identifier
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _ivldCompanyIdentifier.setCompanyName(companyName);
    }

    /**
    * Returns the end date of this ivld company identifier.
    *
    * @return the end date of this ivld company identifier
    */
    @Override
    public java.lang.String getEndDate() {
        return _ivldCompanyIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this ivld company identifier.
    *
    * @param endDate the end date of this ivld company identifier
    */
    @Override
    public void setEndDate(java.lang.String endDate) {
        _ivldCompanyIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the modified date of this ivld company identifier.
    *
    * @return the modified date of this ivld company identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldCompanyIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld company identifier.
    *
    * @param modifiedDate the modified date of this ivld company identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldCompanyIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the identifier status of this ivld company identifier.
    *
    * @return the identifier status of this ivld company identifier
    */
    @Override
    public java.lang.String getIdentifierStatus() {
        return _ivldCompanyIdentifier.getIdentifierStatus();
    }

    /**
    * Sets the identifier status of this ivld company identifier.
    *
    * @param identifierStatus the identifier status of this ivld company identifier
    */
    @Override
    public void setIdentifierStatus(java.lang.String identifierStatus) {
        _ivldCompanyIdentifier.setIdentifierStatus(identifierStatus);
    }

    /**
    * Returns the company identifier of this ivld company identifier.
    *
    * @return the company identifier of this ivld company identifier
    */
    @Override
    public java.lang.String getCompanyIdentifier() {
        return _ivldCompanyIdentifier.getCompanyIdentifier();
    }

    /**
    * Sets the company identifier of this ivld company identifier.
    *
    * @param companyIdentifier the company identifier of this ivld company identifier
    */
    @Override
    public void setCompanyIdentifier(java.lang.String companyIdentifier) {
        _ivldCompanyIdentifier.setCompanyIdentifier(companyIdentifier);
    }

    /**
    * Returns the entity code of this ivld company identifier.
    *
    * @return the entity code of this ivld company identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _ivldCompanyIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this ivld company identifier.
    *
    * @param entityCode the entity code of this ivld company identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _ivldCompanyIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the company identifier intfid of this ivld company identifier.
    *
    * @return the company identifier intfid of this ivld company identifier
    */
    @Override
    public java.lang.String getCompanyIdentifierIntfid() {
        return _ivldCompanyIdentifier.getCompanyIdentifierIntfid();
    }

    /**
    * Sets the company identifier intfid of this ivld company identifier.
    *
    * @param companyIdentifierIntfid the company identifier intfid of this ivld company identifier
    */
    @Override
    public void setCompanyIdentifierIntfid(
        java.lang.String companyIdentifierIntfid) {
        _ivldCompanyIdentifier.setCompanyIdentifierIntfid(companyIdentifierIntfid);
    }

    /**
    * Returns the start date of this ivld company identifier.
    *
    * @return the start date of this ivld company identifier
    */
    @Override
    public java.lang.String getStartDate() {
        return _ivldCompanyIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this ivld company identifier.
    *
    * @param startDate the start date of this ivld company identifier
    */
    @Override
    public void setStartDate(java.lang.String startDate) {
        _ivldCompanyIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the source of this ivld company identifier.
    *
    * @return the source of this ivld company identifier
    */
    @Override
    public java.lang.String getSource() {
        return _ivldCompanyIdentifier.getSource();
    }

    /**
    * Sets the source of this ivld company identifier.
    *
    * @param source the source of this ivld company identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldCompanyIdentifier.setSource(source);
    }

    /**
    * Returns the created date of this ivld company identifier.
    *
    * @return the created date of this ivld company identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldCompanyIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld company identifier.
    *
    * @param createdDate the created date of this ivld company identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldCompanyIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld company identifier.
    *
    * @return the created by of this ivld company identifier
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldCompanyIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld company identifier.
    *
    * @param createdBy the created by of this ivld company identifier
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldCompanyIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the company no of this ivld company identifier.
    *
    * @return the company no of this ivld company identifier
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _ivldCompanyIdentifier.getCompanyNo();
    }

    /**
    * Sets the company no of this ivld company identifier.
    *
    * @param companyNo the company no of this ivld company identifier
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _ivldCompanyIdentifier.setCompanyNo(companyNo);
    }

    /**
    * Returns the add chg del indicator of this ivld company identifier.
    *
    * @return the add chg del indicator of this ivld company identifier
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldCompanyIdentifier.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld company identifier.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld company identifier
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldCompanyIdentifier.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the batch ID of this ivld company identifier.
    *
    * @return the batch ID of this ivld company identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldCompanyIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld company identifier.
    *
    * @param batchId the batch ID of this ivld company identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldCompanyIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the error field of this ivld company identifier.
    *
    * @return the error field of this ivld company identifier
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldCompanyIdentifier.getErrorField();
    }

    /**
    * Sets the error field of this ivld company identifier.
    *
    * @param errorField the error field of this ivld company identifier
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldCompanyIdentifier.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld company identifier.
    *
    * @return the error code of this ivld company identifier
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldCompanyIdentifier.getErrorCode();
    }

    /**
    * Sets the error code of this ivld company identifier.
    *
    * @param errorCode the error code of this ivld company identifier
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldCompanyIdentifier.setErrorCode(errorCode);
    }

    /**
    * Returns the identifier code qualifier name of this ivld company identifier.
    *
    * @return the identifier code qualifier name of this ivld company identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifierName() {
        return _ivldCompanyIdentifier.getIdentifierCodeQualifierName();
    }

    /**
    * Sets the identifier code qualifier name of this ivld company identifier.
    *
    * @param identifierCodeQualifierName the identifier code qualifier name of this ivld company identifier
    */
    @Override
    public void setIdentifierCodeQualifierName(
        java.lang.String identifierCodeQualifierName) {
        _ivldCompanyIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
    }

    /**
    * Returns the intf inserted date of this ivld company identifier.
    *
    * @return the intf inserted date of this ivld company identifier
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldCompanyIdentifier.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld company identifier.
    *
    * @param intfInsertedDate the intf inserted date of this ivld company identifier
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldCompanyIdentifier.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld company identifier.
    *
    * @return the modified by of this ivld company identifier
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldCompanyIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld company identifier.
    *
    * @param modifiedBy the modified by of this ivld company identifier
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldCompanyIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the ivld company identifier sid of this ivld company identifier.
    *
    * @return the ivld company identifier sid of this ivld company identifier
    */
    @Override
    public int getIvldCompanyIdentifierSid() {
        return _ivldCompanyIdentifier.getIvldCompanyIdentifierSid();
    }

    /**
    * Sets the ivld company identifier sid of this ivld company identifier.
    *
    * @param ivldCompanyIdentifierSid the ivld company identifier sid of this ivld company identifier
    */
    @Override
    public void setIvldCompanyIdentifierSid(int ivldCompanyIdentifierSid) {
        _ivldCompanyIdentifier.setIvldCompanyIdentifierSid(ivldCompanyIdentifierSid);
    }

    /**
    * Returns the reprocessed flag of this ivld company identifier.
    *
    * @return the reprocessed flag of this ivld company identifier
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldCompanyIdentifier.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld company identifier.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld company identifier
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldCompanyIdentifier.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the identifier code qualifier of this ivld company identifier.
    *
    * @return the identifier code qualifier of this ivld company identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _ivldCompanyIdentifier.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this ivld company identifier.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this ivld company identifier
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _ivldCompanyIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    /**
    * Returns the check record of this ivld company identifier.
    *
    * @return the check record of this ivld company identifier
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldCompanyIdentifier.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld company identifier is check record.
    *
    * @return <code>true</code> if this ivld company identifier is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldCompanyIdentifier.isCheckRecord();
    }

    /**
    * Sets whether this ivld company identifier is check record.
    *
    * @param checkRecord the check record of this ivld company identifier
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldCompanyIdentifier.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldCompanyIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldCompanyIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldCompanyIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldCompanyIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldCompanyIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldCompanyIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldCompanyIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldCompanyIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldCompanyIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldCompanyIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldCompanyIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldCompanyIdentifierWrapper((IvldCompanyIdentifier) _ivldCompanyIdentifier.clone());
    }

    @Override
    public int compareTo(IvldCompanyIdentifier ivldCompanyIdentifier) {
        return _ivldCompanyIdentifier.compareTo(ivldCompanyIdentifier);
    }

    @Override
    public int hashCode() {
        return _ivldCompanyIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldCompanyIdentifier> toCacheModel() {
        return _ivldCompanyIdentifier.toCacheModel();
    }

    @Override
    public IvldCompanyIdentifier toEscapedModel() {
        return new IvldCompanyIdentifierWrapper(_ivldCompanyIdentifier.toEscapedModel());
    }

    @Override
    public IvldCompanyIdentifier toUnescapedModel() {
        return new IvldCompanyIdentifierWrapper(_ivldCompanyIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldCompanyIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldCompanyIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldCompanyIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldCompanyIdentifierWrapper)) {
            return false;
        }

        IvldCompanyIdentifierWrapper ivldCompanyIdentifierWrapper = (IvldCompanyIdentifierWrapper) obj;

        if (Validator.equals(_ivldCompanyIdentifier,
                    ivldCompanyIdentifierWrapper._ivldCompanyIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldCompanyIdentifier getWrappedIvldCompanyIdentifier() {
        return _ivldCompanyIdentifier;
    }

    @Override
    public IvldCompanyIdentifier getWrappedModel() {
        return _ivldCompanyIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _ivldCompanyIdentifier.resetOriginalValues();
    }
}
