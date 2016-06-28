package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CompanyParentDetails}.
 * </p>
 *
 * @author
 * @see CompanyParentDetails
 * @generated
 */
public class CompanyParentDetailsWrapper implements CompanyParentDetails,
    ModelWrapper<CompanyParentDetails> {
    private CompanyParentDetails _companyParentDetails;

    public CompanyParentDetailsWrapper(
        CompanyParentDetails companyParentDetails) {
        _companyParentDetails = companyParentDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyParentDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyParentDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("parentCompanyMasterSid", getParentCompanyMasterSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("priorParentCmpyMasterSid", getPriorParentCmpyMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("parentStartDate", getParentStartDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date parentEndDate = (Date) attributes.get("parentEndDate");

        if (parentEndDate != null) {
            setParentEndDate(parentEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer parentCompanyMasterSid = (Integer) attributes.get(
                "parentCompanyMasterSid");

        if (parentCompanyMasterSid != null) {
            setParentCompanyMasterSid(parentCompanyMasterSid);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date priorParentStartDate = (Date) attributes.get(
                "priorParentStartDate");

        if (priorParentStartDate != null) {
            setPriorParentStartDate(priorParentStartDate);
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

        Integer companyParentDetailsSid = (Integer) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
        }

        String priorParentCmpyMasterSid = (String) attributes.get(
                "priorParentCmpyMasterSid");

        if (priorParentCmpyMasterSid != null) {
            setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
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

        Date parentStartDate = (Date) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }
    }

    /**
    * Returns the primary key of this company parent details.
    *
    * @return the primary key of this company parent details
    */
    @Override
    public int getPrimaryKey() {
        return _companyParentDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this company parent details.
    *
    * @param primaryKey the primary key of this company parent details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _companyParentDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the last updated date of this company parent details.
    *
    * @return the last updated date of this company parent details
    */
    @Override
    public java.util.Date getLastUpdatedDate() {
        return _companyParentDetails.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this company parent details.
    *
    * @param lastUpdatedDate the last updated date of this company parent details
    */
    @Override
    public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
        _companyParentDetails.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the parent end date of this company parent details.
    *
    * @return the parent end date of this company parent details
    */
    @Override
    public java.util.Date getParentEndDate() {
        return _companyParentDetails.getParentEndDate();
    }

    /**
    * Sets the parent end date of this company parent details.
    *
    * @param parentEndDate the parent end date of this company parent details
    */
    @Override
    public void setParentEndDate(java.util.Date parentEndDate) {
        _companyParentDetails.setParentEndDate(parentEndDate);
    }

    /**
    * Returns the modified date of this company parent details.
    *
    * @return the modified date of this company parent details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _companyParentDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this company parent details.
    *
    * @param modifiedDate the modified date of this company parent details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _companyParentDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the parent company master sid of this company parent details.
    *
    * @return the parent company master sid of this company parent details
    */
    @Override
    public int getParentCompanyMasterSid() {
        return _companyParentDetails.getParentCompanyMasterSid();
    }

    /**
    * Sets the parent company master sid of this company parent details.
    *
    * @param parentCompanyMasterSid the parent company master sid of this company parent details
    */
    @Override
    public void setParentCompanyMasterSid(int parentCompanyMasterSid) {
        _companyParentDetails.setParentCompanyMasterSid(parentCompanyMasterSid);
    }

    /**
    * Returns the record lock status of this company parent details.
    *
    * @return the record lock status of this company parent details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _companyParentDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this company parent details is record lock status.
    *
    * @return <code>true</code> if this company parent details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _companyParentDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this company parent details is record lock status.
    *
    * @param recordLockStatus the record lock status of this company parent details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _companyParentDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the prior parent start date of this company parent details.
    *
    * @return the prior parent start date of this company parent details
    */
    @Override
    public java.util.Date getPriorParentStartDate() {
        return _companyParentDetails.getPriorParentStartDate();
    }

    /**
    * Sets the prior parent start date of this company parent details.
    *
    * @param priorParentStartDate the prior parent start date of this company parent details
    */
    @Override
    public void setPriorParentStartDate(java.util.Date priorParentStartDate) {
        _companyParentDetails.setPriorParentStartDate(priorParentStartDate);
    }

    /**
    * Returns the created date of this company parent details.
    *
    * @return the created date of this company parent details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _companyParentDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this company parent details.
    *
    * @param createdDate the created date of this company parent details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _companyParentDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this company parent details.
    *
    * @return the source of this company parent details
    */
    @Override
    public java.lang.String getSource() {
        return _companyParentDetails.getSource();
    }

    /**
    * Sets the source of this company parent details.
    *
    * @param source the source of this company parent details
    */
    @Override
    public void setSource(java.lang.String source) {
        _companyParentDetails.setSource(source);
    }

    /**
    * Returns the created by of this company parent details.
    *
    * @return the created by of this company parent details
    */
    @Override
    public int getCreatedBy() {
        return _companyParentDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this company parent details.
    *
    * @param createdBy the created by of this company parent details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _companyParentDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the company parent details sid of this company parent details.
    *
    * @return the company parent details sid of this company parent details
    */
    @Override
    public int getCompanyParentDetailsSid() {
        return _companyParentDetails.getCompanyParentDetailsSid();
    }

    /**
    * Sets the company parent details sid of this company parent details.
    *
    * @param companyParentDetailsSid the company parent details sid of this company parent details
    */
    @Override
    public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
        _companyParentDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
    }

    /**
    * Returns the prior parent cmpy master sid of this company parent details.
    *
    * @return the prior parent cmpy master sid of this company parent details
    */
    @Override
    public java.lang.String getPriorParentCmpyMasterSid() {
        return _companyParentDetails.getPriorParentCmpyMasterSid();
    }

    /**
    * Sets the prior parent cmpy master sid of this company parent details.
    *
    * @param priorParentCmpyMasterSid the prior parent cmpy master sid of this company parent details
    */
    @Override
    public void setPriorParentCmpyMasterSid(
        java.lang.String priorParentCmpyMasterSid) {
        _companyParentDetails.setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
    }

    /**
    * Returns the batch ID of this company parent details.
    *
    * @return the batch ID of this company parent details
    */
    @Override
    public java.lang.String getBatchId() {
        return _companyParentDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this company parent details.
    *
    * @param batchId the batch ID of this company parent details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _companyParentDetails.setBatchId(batchId);
    }

    /**
    * Returns the modified by of this company parent details.
    *
    * @return the modified by of this company parent details
    */
    @Override
    public int getModifiedBy() {
        return _companyParentDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this company parent details.
    *
    * @param modifiedBy the modified by of this company parent details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _companyParentDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this company parent details.
    *
    * @return the inbound status of this company parent details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _companyParentDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this company parent details.
    *
    * @param inboundStatus the inbound status of this company parent details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _companyParentDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the company master sid of this company parent details.
    *
    * @return the company master sid of this company parent details
    */
    @Override
    public int getCompanyMasterSid() {
        return _companyParentDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this company parent details.
    *
    * @param companyMasterSid the company master sid of this company parent details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyParentDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the parent start date of this company parent details.
    *
    * @return the parent start date of this company parent details
    */
    @Override
    public java.util.Date getParentStartDate() {
        return _companyParentDetails.getParentStartDate();
    }

    /**
    * Sets the parent start date of this company parent details.
    *
    * @param parentStartDate the parent start date of this company parent details
    */
    @Override
    public void setParentStartDate(java.util.Date parentStartDate) {
        _companyParentDetails.setParentStartDate(parentStartDate);
    }

    @Override
    public boolean isNew() {
        return _companyParentDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _companyParentDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _companyParentDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _companyParentDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _companyParentDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _companyParentDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _companyParentDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _companyParentDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _companyParentDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _companyParentDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _companyParentDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CompanyParentDetailsWrapper((CompanyParentDetails) _companyParentDetails.clone());
    }

    @Override
    public int compareTo(CompanyParentDetails companyParentDetails) {
        return _companyParentDetails.compareTo(companyParentDetails);
    }

    @Override
    public int hashCode() {
        return _companyParentDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CompanyParentDetails> toCacheModel() {
        return _companyParentDetails.toCacheModel();
    }

    @Override
    public CompanyParentDetails toEscapedModel() {
        return new CompanyParentDetailsWrapper(_companyParentDetails.toEscapedModel());
    }

    @Override
    public CompanyParentDetails toUnescapedModel() {
        return new CompanyParentDetailsWrapper(_companyParentDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _companyParentDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _companyParentDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _companyParentDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CompanyParentDetailsWrapper)) {
            return false;
        }

        CompanyParentDetailsWrapper companyParentDetailsWrapper = (CompanyParentDetailsWrapper) obj;

        if (Validator.equals(_companyParentDetails,
                    companyParentDetailsWrapper._companyParentDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CompanyParentDetails getWrappedCompanyParentDetails() {
        return _companyParentDetails;
    }

    @Override
    public CompanyParentDetails getWrappedModel() {
        return _companyParentDetails;
    }

    @Override
    public void resetOriginalValues() {
        _companyParentDetails.resetOriginalValues();
    }
}
