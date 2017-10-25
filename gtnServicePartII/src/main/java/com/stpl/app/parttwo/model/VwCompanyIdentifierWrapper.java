package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwCompanyIdentifier}.
 * </p>
 *
 * @author
 * @see VwCompanyIdentifier
 * @generated
 */
public class VwCompanyIdentifierWrapper implements VwCompanyIdentifier,
    ModelWrapper<VwCompanyIdentifier> {
    private VwCompanyIdentifier _vwCompanyIdentifier;

    public VwCompanyIdentifierWrapper(VwCompanyIdentifier vwCompanyIdentifier) {
        _vwCompanyIdentifier = vwCompanyIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("companyId", getCompanyId());
        attributes.put("companyName", getCompanyName());
        attributes.put("endDate", getEndDate());
        attributes.put("companyIdentifierSid", getCompanyIdentifierSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("companyIdentifier", getCompanyIdentifier());
        attributes.put("entityCode", getEntityCode());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

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

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }
    }

    /**
    * Returns the primary key of this vw company identifier.
    *
    * @return the primary key of this vw company identifier
    */
    @Override
    public int getPrimaryKey() {
        return _vwCompanyIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw company identifier.
    *
    * @param primaryKey the primary key of this vw company identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwCompanyIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the company ID of this vw company identifier.
    *
    * @return the company ID of this vw company identifier
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwCompanyIdentifier.getCompanyId();
    }

    /**
    * Sets the company ID of this vw company identifier.
    *
    * @param companyId the company ID of this vw company identifier
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwCompanyIdentifier.setCompanyId(companyId);
    }

    /**
    * Returns the company name of this vw company identifier.
    *
    * @return the company name of this vw company identifier
    */
    @Override
    public java.lang.String getCompanyName() {
        return _vwCompanyIdentifier.getCompanyName();
    }

    /**
    * Sets the company name of this vw company identifier.
    *
    * @param companyName the company name of this vw company identifier
    */
    @Override
    public void setCompanyName(java.lang.String companyName) {
        _vwCompanyIdentifier.setCompanyName(companyName);
    }

    /**
    * Returns the end date of this vw company identifier.
    *
    * @return the end date of this vw company identifier
    */
    @Override
    public java.util.Date getEndDate() {
        return _vwCompanyIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this vw company identifier.
    *
    * @param endDate the end date of this vw company identifier
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _vwCompanyIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the company identifier sid of this vw company identifier.
    *
    * @return the company identifier sid of this vw company identifier
    */
    @Override
    public int getCompanyIdentifierSid() {
        return _vwCompanyIdentifier.getCompanyIdentifierSid();
    }

    /**
    * Sets the company identifier sid of this vw company identifier.
    *
    * @param companyIdentifierSid the company identifier sid of this vw company identifier
    */
    @Override
    public void setCompanyIdentifierSid(int companyIdentifierSid) {
        _vwCompanyIdentifier.setCompanyIdentifierSid(companyIdentifierSid);
    }

    /**
    * Returns the modified date of this vw company identifier.
    *
    * @return the modified date of this vw company identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwCompanyIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw company identifier.
    *
    * @param modifiedDate the modified date of this vw company identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwCompanyIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the identifier status of this vw company identifier.
    *
    * @return the identifier status of this vw company identifier
    */
    @Override
    public java.lang.String getIdentifierStatus() {
        return _vwCompanyIdentifier.getIdentifierStatus();
    }

    /**
    * Sets the identifier status of this vw company identifier.
    *
    * @param identifierStatus the identifier status of this vw company identifier
    */
    @Override
    public void setIdentifierStatus(java.lang.String identifierStatus) {
        _vwCompanyIdentifier.setIdentifierStatus(identifierStatus);
    }

    /**
    * Returns the company identifier of this vw company identifier.
    *
    * @return the company identifier of this vw company identifier
    */
    @Override
    public java.lang.String getCompanyIdentifier() {
        return _vwCompanyIdentifier.getCompanyIdentifier();
    }

    /**
    * Sets the company identifier of this vw company identifier.
    *
    * @param companyIdentifier the company identifier of this vw company identifier
    */
    @Override
    public void setCompanyIdentifier(java.lang.String companyIdentifier) {
        _vwCompanyIdentifier.setCompanyIdentifier(companyIdentifier);
    }

    /**
    * Returns the entity code of this vw company identifier.
    *
    * @return the entity code of this vw company identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _vwCompanyIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this vw company identifier.
    *
    * @param entityCode the entity code of this vw company identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _vwCompanyIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the start date of this vw company identifier.
    *
    * @return the start date of this vw company identifier
    */
    @Override
    public java.util.Date getStartDate() {
        return _vwCompanyIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this vw company identifier.
    *
    * @param startDate the start date of this vw company identifier
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _vwCompanyIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the created date of this vw company identifier.
    *
    * @return the created date of this vw company identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwCompanyIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this vw company identifier.
    *
    * @param createdDate the created date of this vw company identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwCompanyIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw company identifier.
    *
    * @return the created by of this vw company identifier
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwCompanyIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this vw company identifier.
    *
    * @param createdBy the created by of this vw company identifier
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwCompanyIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this vw company identifier.
    *
    * @return the source of this vw company identifier
    */
    @Override
    public java.lang.String getSource() {
        return _vwCompanyIdentifier.getSource();
    }

    /**
    * Sets the source of this vw company identifier.
    *
    * @param source the source of this vw company identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwCompanyIdentifier.setSource(source);
    }

    /**
    * Returns the company no of this vw company identifier.
    *
    * @return the company no of this vw company identifier
    */
    @Override
    public java.lang.String getCompanyNo() {
        return _vwCompanyIdentifier.getCompanyNo();
    }

    /**
    * Sets the company no of this vw company identifier.
    *
    * @param companyNo the company no of this vw company identifier
    */
    @Override
    public void setCompanyNo(java.lang.String companyNo) {
        _vwCompanyIdentifier.setCompanyNo(companyNo);
    }

    /**
    * Returns the batch ID of this vw company identifier.
    *
    * @return the batch ID of this vw company identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwCompanyIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this vw company identifier.
    *
    * @param batchId the batch ID of this vw company identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwCompanyIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this vw company identifier.
    *
    * @return the add chg del indicator of this vw company identifier
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwCompanyIdentifier.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw company identifier.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw company identifier
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwCompanyIdentifier.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the identifier code qualifier name of this vw company identifier.
    *
    * @return the identifier code qualifier name of this vw company identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifierName() {
        return _vwCompanyIdentifier.getIdentifierCodeQualifierName();
    }

    /**
    * Sets the identifier code qualifier name of this vw company identifier.
    *
    * @param identifierCodeQualifierName the identifier code qualifier name of this vw company identifier
    */
    @Override
    public void setIdentifierCodeQualifierName(
        java.lang.String identifierCodeQualifierName) {
        _vwCompanyIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
    }

    /**
    * Returns the modified by of this vw company identifier.
    *
    * @return the modified by of this vw company identifier
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwCompanyIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw company identifier.
    *
    * @param modifiedBy the modified by of this vw company identifier
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwCompanyIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the identifier code qualifier of this vw company identifier.
    *
    * @return the identifier code qualifier of this vw company identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _vwCompanyIdentifier.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this vw company identifier.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this vw company identifier
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _vwCompanyIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    @Override
    public boolean isNew() {
        return _vwCompanyIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwCompanyIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwCompanyIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwCompanyIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwCompanyIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwCompanyIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwCompanyIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwCompanyIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwCompanyIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwCompanyIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwCompanyIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwCompanyIdentifierWrapper((VwCompanyIdentifier) _vwCompanyIdentifier.clone());
    }

    @Override
    public int compareTo(VwCompanyIdentifier vwCompanyIdentifier) {
        return _vwCompanyIdentifier.compareTo(vwCompanyIdentifier);
    }

    @Override
    public int hashCode() {
        return _vwCompanyIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwCompanyIdentifier> toCacheModel() {
        return _vwCompanyIdentifier.toCacheModel();
    }

    @Override
    public VwCompanyIdentifier toEscapedModel() {
        return new VwCompanyIdentifierWrapper(_vwCompanyIdentifier.toEscapedModel());
    }

    @Override
    public VwCompanyIdentifier toUnescapedModel() {
        return new VwCompanyIdentifierWrapper(_vwCompanyIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwCompanyIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwCompanyIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwCompanyIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwCompanyIdentifierWrapper)) {
            return false;
        }

        VwCompanyIdentifierWrapper vwCompanyIdentifierWrapper = (VwCompanyIdentifierWrapper) obj;

        if (Validator.equals(_vwCompanyIdentifier,
                    vwCompanyIdentifierWrapper._vwCompanyIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwCompanyIdentifier getWrappedVwCompanyIdentifier() {
        return _vwCompanyIdentifier;
    }

    @Override
    public VwCompanyIdentifier getWrappedModel() {
        return _vwCompanyIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _vwCompanyIdentifier.resetOriginalValues();
    }
}
