package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwCompanyParentDetails}.
 * </p>
 *
 * @author
 * @see VwCompanyParentDetails
 * @generated
 */
public class VwCompanyParentDetailsWrapper implements VwCompanyParentDetails,
    ModelWrapper<VwCompanyParentDetails> {
    private VwCompanyParentDetails _vwCompanyParentDetails;

    public VwCompanyParentDetailsWrapper(
        VwCompanyParentDetails vwCompanyParentDetails) {
        _vwCompanyParentDetails = vwCompanyParentDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyParentDetails.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyParentDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("parentCompanyId", getParentCompanyId());
        attributes.put("priorParentCompanyId", getPriorParentCompanyId());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("parentStartDate", getParentStartDate());

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

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

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

        Date priorParentStartDate = (Date) attributes.get(
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

        Integer companyParentDetailsSid = (Integer) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
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

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date parentStartDate = (Date) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }
    }

    /**
    * Returns the primary key of this vw company parent details.
    *
    * @return the primary key of this vw company parent details
    */
    @Override
    public int getPrimaryKey() {
        return _vwCompanyParentDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw company parent details.
    *
    * @param primaryKey the primary key of this vw company parent details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwCompanyParentDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the parent company ID of this vw company parent details.
    *
    * @return the parent company ID of this vw company parent details
    */
    @Override
    public java.lang.String getParentCompanyId() {
        return _vwCompanyParentDetails.getParentCompanyId();
    }

    /**
    * Sets the parent company ID of this vw company parent details.
    *
    * @param parentCompanyId the parent company ID of this vw company parent details
    */
    @Override
    public void setParentCompanyId(java.lang.String parentCompanyId) {
        _vwCompanyParentDetails.setParentCompanyId(parentCompanyId);
    }

    /**
    * Returns the prior parent company ID of this vw company parent details.
    *
    * @return the prior parent company ID of this vw company parent details
    */
    @Override
    public java.lang.String getPriorParentCompanyId() {
        return _vwCompanyParentDetails.getPriorParentCompanyId();
    }

    /**
    * Sets the prior parent company ID of this vw company parent details.
    *
    * @param priorParentCompanyId the prior parent company ID of this vw company parent details
    */
    @Override
    public void setPriorParentCompanyId(java.lang.String priorParentCompanyId) {
        _vwCompanyParentDetails.setPriorParentCompanyId(priorParentCompanyId);
    }

    /**
    * Returns the company ID of this vw company parent details.
    *
    * @return the company ID of this vw company parent details
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwCompanyParentDetails.getCompanyId();
    }

    /**
    * Sets the company ID of this vw company parent details.
    *
    * @param companyId the company ID of this vw company parent details
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwCompanyParentDetails.setCompanyId(companyId);
    }

    /**
    * Returns the last updated date of this vw company parent details.
    *
    * @return the last updated date of this vw company parent details
    */
    @Override
    public java.util.Date getLastUpdatedDate() {
        return _vwCompanyParentDetails.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this vw company parent details.
    *
    * @param lastUpdatedDate the last updated date of this vw company parent details
    */
    @Override
    public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
        _vwCompanyParentDetails.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the parent end date of this vw company parent details.
    *
    * @return the parent end date of this vw company parent details
    */
    @Override
    public java.util.Date getParentEndDate() {
        return _vwCompanyParentDetails.getParentEndDate();
    }

    /**
    * Sets the parent end date of this vw company parent details.
    *
    * @param parentEndDate the parent end date of this vw company parent details
    */
    @Override
    public void setParentEndDate(java.util.Date parentEndDate) {
        _vwCompanyParentDetails.setParentEndDate(parentEndDate);
    }

    /**
    * Returns the modified date of this vw company parent details.
    *
    * @return the modified date of this vw company parent details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwCompanyParentDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw company parent details.
    *
    * @param modifiedDate the modified date of this vw company parent details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwCompanyParentDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the prior parent start date of this vw company parent details.
    *
    * @return the prior parent start date of this vw company parent details
    */
    @Override
    public java.util.Date getPriorParentStartDate() {
        return _vwCompanyParentDetails.getPriorParentStartDate();
    }

    /**
    * Sets the prior parent start date of this vw company parent details.
    *
    * @param priorParentStartDate the prior parent start date of this vw company parent details
    */
    @Override
    public void setPriorParentStartDate(java.util.Date priorParentStartDate) {
        _vwCompanyParentDetails.setPriorParentStartDate(priorParentStartDate);
    }

    /**
    * Returns the source of this vw company parent details.
    *
    * @return the source of this vw company parent details
    */
    @Override
    public java.lang.String getSource() {
        return _vwCompanyParentDetails.getSource();
    }

    /**
    * Sets the source of this vw company parent details.
    *
    * @param source the source of this vw company parent details
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwCompanyParentDetails.setSource(source);
    }

    /**
    * Returns the created by of this vw company parent details.
    *
    * @return the created by of this vw company parent details
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwCompanyParentDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this vw company parent details.
    *
    * @param createdBy the created by of this vw company parent details
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwCompanyParentDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this vw company parent details.
    *
    * @return the created date of this vw company parent details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwCompanyParentDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this vw company parent details.
    *
    * @param createdDate the created date of this vw company parent details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwCompanyParentDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the company parent details sid of this vw company parent details.
    *
    * @return the company parent details sid of this vw company parent details
    */
    @Override
    public int getCompanyParentDetailsSid() {
        return _vwCompanyParentDetails.getCompanyParentDetailsSid();
    }

    /**
    * Sets the company parent details sid of this vw company parent details.
    *
    * @param companyParentDetailsSid the company parent details sid of this vw company parent details
    */
    @Override
    public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
        _vwCompanyParentDetails.setCompanyParentDetailsSid(companyParentDetailsSid);
    }

    /**
    * Returns the batch ID of this vw company parent details.
    *
    * @return the batch ID of this vw company parent details
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwCompanyParentDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this vw company parent details.
    *
    * @param batchId the batch ID of this vw company parent details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwCompanyParentDetails.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this vw company parent details.
    *
    * @return the add chg del indicator of this vw company parent details
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwCompanyParentDetails.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw company parent details.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw company parent details
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwCompanyParentDetails.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the modified by of this vw company parent details.
    *
    * @return the modified by of this vw company parent details
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwCompanyParentDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw company parent details.
    *
    * @param modifiedBy the modified by of this vw company parent details
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwCompanyParentDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the parent start date of this vw company parent details.
    *
    * @return the parent start date of this vw company parent details
    */
    @Override
    public java.util.Date getParentStartDate() {
        return _vwCompanyParentDetails.getParentStartDate();
    }

    /**
    * Sets the parent start date of this vw company parent details.
    *
    * @param parentStartDate the parent start date of this vw company parent details
    */
    @Override
    public void setParentStartDate(java.util.Date parentStartDate) {
        _vwCompanyParentDetails.setParentStartDate(parentStartDate);
    }

    @Override
    public boolean isNew() {
        return _vwCompanyParentDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwCompanyParentDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwCompanyParentDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwCompanyParentDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwCompanyParentDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwCompanyParentDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwCompanyParentDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwCompanyParentDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwCompanyParentDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwCompanyParentDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwCompanyParentDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwCompanyParentDetailsWrapper((VwCompanyParentDetails) _vwCompanyParentDetails.clone());
    }

    @Override
    public int compareTo(VwCompanyParentDetails vwCompanyParentDetails) {
        return _vwCompanyParentDetails.compareTo(vwCompanyParentDetails);
    }

    @Override
    public int hashCode() {
        return _vwCompanyParentDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwCompanyParentDetails> toCacheModel() {
        return _vwCompanyParentDetails.toCacheModel();
    }

    @Override
    public VwCompanyParentDetails toEscapedModel() {
        return new VwCompanyParentDetailsWrapper(_vwCompanyParentDetails.toEscapedModel());
    }

    @Override
    public VwCompanyParentDetails toUnescapedModel() {
        return new VwCompanyParentDetailsWrapper(_vwCompanyParentDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwCompanyParentDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwCompanyParentDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwCompanyParentDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwCompanyParentDetailsWrapper)) {
            return false;
        }

        VwCompanyParentDetailsWrapper vwCompanyParentDetailsWrapper = (VwCompanyParentDetailsWrapper) obj;

        if (Validator.equals(_vwCompanyParentDetails,
                    vwCompanyParentDetailsWrapper._vwCompanyParentDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwCompanyParentDetails getWrappedVwCompanyParentDetails() {
        return _vwCompanyParentDetails;
    }

    @Override
    public VwCompanyParentDetails getWrappedModel() {
        return _vwCompanyParentDetails;
    }

    @Override
    public void resetOriginalValues() {
        _vwCompanyParentDetails.resetOriginalValues();
    }
}
