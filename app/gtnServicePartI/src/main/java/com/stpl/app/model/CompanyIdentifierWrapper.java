package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CompanyIdentifier}.
 * </p>
 *
 * @author
 * @see CompanyIdentifier
 * @generated
 */
public class CompanyIdentifierWrapper implements CompanyIdentifier,
    ModelWrapper<CompanyIdentifier> {
    private CompanyIdentifier _companyIdentifier;

    public CompanyIdentifierWrapper(CompanyIdentifier companyIdentifier) {
        _companyIdentifier = companyIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("endDate", getEndDate());
        attributes.put("companyIdentifierSid", getCompanyIdentifierSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("entityCode", getEntityCode());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyIdentifierValue", getCompanyIdentifierValue());
        attributes.put("batchId", getBatchId());
        attributes.put("companyQualifierSid", getCompanyQualifierSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Integer companyIdentifierSid = (Integer) attributes.get(
                "companyIdentifierSid");

        if (companyIdentifierSid != null) {
            setCompanyIdentifierSid(companyIdentifierSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer identifierStatus = (Integer) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String companyIdentifierValue = (String) attributes.get(
                "companyIdentifierValue");

        if (companyIdentifierValue != null) {
            setCompanyIdentifierValue(companyIdentifierValue);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyQualifierSid = (Integer) attributes.get(
                "companyQualifierSid");

        if (companyQualifierSid != null) {
            setCompanyQualifierSid(companyQualifierSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this company identifier.
    *
    * @return the primary key of this company identifier
    */
    @Override
    public int getPrimaryKey() {
        return _companyIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this company identifier.
    *
    * @param primaryKey the primary key of this company identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _companyIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the end date of this company identifier.
    *
    * @return the end date of this company identifier
    */
    @Override
    public java.util.Date getEndDate() {
        return _companyIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this company identifier.
    *
    * @param endDate the end date of this company identifier
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _companyIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the company identifier sid of this company identifier.
    *
    * @return the company identifier sid of this company identifier
    */
    @Override
    public int getCompanyIdentifierSid() {
        return _companyIdentifier.getCompanyIdentifierSid();
    }

    /**
    * Sets the company identifier sid of this company identifier.
    *
    * @param companyIdentifierSid the company identifier sid of this company identifier
    */
    @Override
    public void setCompanyIdentifierSid(int companyIdentifierSid) {
        _companyIdentifier.setCompanyIdentifierSid(companyIdentifierSid);
    }

    /**
    * Returns the modified date of this company identifier.
    *
    * @return the modified date of this company identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _companyIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this company identifier.
    *
    * @param modifiedDate the modified date of this company identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _companyIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the identifier status of this company identifier.
    *
    * @return the identifier status of this company identifier
    */
    @Override
    public int getIdentifierStatus() {
        return _companyIdentifier.getIdentifierStatus();
    }

    /**
    * Sets the identifier status of this company identifier.
    *
    * @param identifierStatus the identifier status of this company identifier
    */
    @Override
    public void setIdentifierStatus(int identifierStatus) {
        _companyIdentifier.setIdentifierStatus(identifierStatus);
    }

    /**
    * Returns the entity code of this company identifier.
    *
    * @return the entity code of this company identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _companyIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this company identifier.
    *
    * @param entityCode the entity code of this company identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _companyIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the record lock status of this company identifier.
    *
    * @return the record lock status of this company identifier
    */
    @Override
    public boolean getRecordLockStatus() {
        return _companyIdentifier.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this company identifier is record lock status.
    *
    * @return <code>true</code> if this company identifier is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _companyIdentifier.isRecordLockStatus();
    }

    /**
    * Sets whether this company identifier is record lock status.
    *
    * @param recordLockStatus the record lock status of this company identifier
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _companyIdentifier.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the start date of this company identifier.
    *
    * @return the start date of this company identifier
    */
    @Override
    public java.util.Date getStartDate() {
        return _companyIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this company identifier.
    *
    * @param startDate the start date of this company identifier
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _companyIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the created date of this company identifier.
    *
    * @return the created date of this company identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _companyIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this company identifier.
    *
    * @param createdDate the created date of this company identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _companyIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this company identifier.
    *
    * @return the source of this company identifier
    */
    @Override
    public java.lang.String getSource() {
        return _companyIdentifier.getSource();
    }

    /**
    * Sets the source of this company identifier.
    *
    * @param source the source of this company identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _companyIdentifier.setSource(source);
    }

    /**
    * Returns the created by of this company identifier.
    *
    * @return the created by of this company identifier
    */
    @Override
    public int getCreatedBy() {
        return _companyIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this company identifier.
    *
    * @param createdBy the created by of this company identifier
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _companyIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the company identifier value of this company identifier.
    *
    * @return the company identifier value of this company identifier
    */
    @Override
    public java.lang.String getCompanyIdentifierValue() {
        return _companyIdentifier.getCompanyIdentifierValue();
    }

    /**
    * Sets the company identifier value of this company identifier.
    *
    * @param companyIdentifierValue the company identifier value of this company identifier
    */
    @Override
    public void setCompanyIdentifierValue(
        java.lang.String companyIdentifierValue) {
        _companyIdentifier.setCompanyIdentifierValue(companyIdentifierValue);
    }

    /**
    * Returns the batch ID of this company identifier.
    *
    * @return the batch ID of this company identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _companyIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this company identifier.
    *
    * @param batchId the batch ID of this company identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _companyIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the company qualifier sid of this company identifier.
    *
    * @return the company qualifier sid of this company identifier
    */
    @Override
    public int getCompanyQualifierSid() {
        return _companyIdentifier.getCompanyQualifierSid();
    }

    /**
    * Sets the company qualifier sid of this company identifier.
    *
    * @param companyQualifierSid the company qualifier sid of this company identifier
    */
    @Override
    public void setCompanyQualifierSid(int companyQualifierSid) {
        _companyIdentifier.setCompanyQualifierSid(companyQualifierSid);
    }

    /**
    * Returns the modified by of this company identifier.
    *
    * @return the modified by of this company identifier
    */
    @Override
    public int getModifiedBy() {
        return _companyIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this company identifier.
    *
    * @param modifiedBy the modified by of this company identifier
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _companyIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this company identifier.
    *
    * @return the inbound status of this company identifier
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _companyIdentifier.getInboundStatus();
    }

    /**
    * Sets the inbound status of this company identifier.
    *
    * @param inboundStatus the inbound status of this company identifier
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _companyIdentifier.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the company master sid of this company identifier.
    *
    * @return the company master sid of this company identifier
    */
    @Override
    public int getCompanyMasterSid() {
        return _companyIdentifier.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this company identifier.
    *
    * @param companyMasterSid the company master sid of this company identifier
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyIdentifier.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _companyIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _companyIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _companyIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _companyIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _companyIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _companyIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _companyIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _companyIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _companyIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _companyIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _companyIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CompanyIdentifierWrapper((CompanyIdentifier) _companyIdentifier.clone());
    }

    @Override
    public int compareTo(CompanyIdentifier companyIdentifier) {
        return _companyIdentifier.compareTo(companyIdentifier);
    }

    @Override
    public int hashCode() {
        return _companyIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CompanyIdentifier> toCacheModel() {
        return _companyIdentifier.toCacheModel();
    }

    @Override
    public CompanyIdentifier toEscapedModel() {
        return new CompanyIdentifierWrapper(_companyIdentifier.toEscapedModel());
    }

    @Override
    public CompanyIdentifier toUnescapedModel() {
        return new CompanyIdentifierWrapper(_companyIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _companyIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _companyIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _companyIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CompanyIdentifierWrapper)) {
            return false;
        }

        CompanyIdentifierWrapper companyIdentifierWrapper = (CompanyIdentifierWrapper) obj;

        if (Validator.equals(_companyIdentifier,
                    companyIdentifierWrapper._companyIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CompanyIdentifier getWrappedCompanyIdentifier() {
        return _companyIdentifier;
    }

    @Override
    public CompanyIdentifier getWrappedModel() {
        return _companyIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _companyIdentifier.resetOriginalValues();
    }
}
