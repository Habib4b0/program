package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CfpDetails}.
 * </p>
 *
 * @author
 * @see CfpDetails
 * @generated
 */
public class CfpDetailsWrapper implements CfpDetails, ModelWrapper<CfpDetails> {
    private CfpDetails _cfpDetails;

    public CfpDetailsWrapper(CfpDetails cfpDetails) {
        _cfpDetails = cfpDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CfpDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyCfpAttachedStatus", getCompanyCfpAttachedStatus());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("companyCfpAttachedDate", getCompanyCfpAttachedDate());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("cfpDetailsSid", getCfpDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer companyCfpAttachedStatus = (Integer) attributes.get(
                "companyCfpAttachedStatus");

        if (companyCfpAttachedStatus != null) {
            setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
        }

        Integer tradeClass = (Integer) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Date companyCfpAttachedDate = (Date) attributes.get(
                "companyCfpAttachedDate");

        if (companyCfpAttachedDate != null) {
            setCompanyCfpAttachedDate(companyCfpAttachedDate);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer cfpDetailsSid = (Integer) attributes.get("cfpDetailsSid");

        if (cfpDetailsSid != null) {
            setCfpDetailsSid(cfpDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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
    * Returns the primary key of this cfp details.
    *
    * @return the primary key of this cfp details
    */
    @Override
    public int getPrimaryKey() {
        return _cfpDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cfp details.
    *
    * @param primaryKey the primary key of this cfp details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cfpDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cfp details.
    *
    * @return the created by of this cfp details
    */
    @Override
    public int getCreatedBy() {
        return _cfpDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this cfp details.
    *
    * @param createdBy the created by of this cfp details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cfpDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the company cfp attached status of this cfp details.
    *
    * @return the company cfp attached status of this cfp details
    */
    @Override
    public int getCompanyCfpAttachedStatus() {
        return _cfpDetails.getCompanyCfpAttachedStatus();
    }

    /**
    * Sets the company cfp attached status of this cfp details.
    *
    * @param companyCfpAttachedStatus the company cfp attached status of this cfp details
    */
    @Override
    public void setCompanyCfpAttachedStatus(int companyCfpAttachedStatus) {
        _cfpDetails.setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
    }

    /**
    * Returns the trade class of this cfp details.
    *
    * @return the trade class of this cfp details
    */
    @Override
    public int getTradeClass() {
        return _cfpDetails.getTradeClass();
    }

    /**
    * Sets the trade class of this cfp details.
    *
    * @param tradeClass the trade class of this cfp details
    */
    @Override
    public void setTradeClass(int tradeClass) {
        _cfpDetails.setTradeClass(tradeClass);
    }

    /**
    * Returns the trade class end date of this cfp details.
    *
    * @return the trade class end date of this cfp details
    */
    @Override
    public java.util.Date getTradeClassEndDate() {
        return _cfpDetails.getTradeClassEndDate();
    }

    /**
    * Sets the trade class end date of this cfp details.
    *
    * @param tradeClassEndDate the trade class end date of this cfp details
    */
    @Override
    public void setTradeClassEndDate(java.util.Date tradeClassEndDate) {
        _cfpDetails.setTradeClassEndDate(tradeClassEndDate);
    }

    /**
    * Returns the modified by of this cfp details.
    *
    * @return the modified by of this cfp details
    */
    @Override
    public int getModifiedBy() {
        return _cfpDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this cfp details.
    *
    * @param modifiedBy the modified by of this cfp details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cfpDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the company start date of this cfp details.
    *
    * @return the company start date of this cfp details
    */
    @Override
    public java.util.Date getCompanyStartDate() {
        return _cfpDetails.getCompanyStartDate();
    }

    /**
    * Sets the company start date of this cfp details.
    *
    * @param companyStartDate the company start date of this cfp details
    */
    @Override
    public void setCompanyStartDate(java.util.Date companyStartDate) {
        _cfpDetails.setCompanyStartDate(companyStartDate);
    }

    /**
    * Returns the trade class start date of this cfp details.
    *
    * @return the trade class start date of this cfp details
    */
    @Override
    public java.util.Date getTradeClassStartDate() {
        return _cfpDetails.getTradeClassStartDate();
    }

    /**
    * Sets the trade class start date of this cfp details.
    *
    * @param tradeClassStartDate the trade class start date of this cfp details
    */
    @Override
    public void setTradeClassStartDate(java.util.Date tradeClassStartDate) {
        _cfpDetails.setTradeClassStartDate(tradeClassStartDate);
    }

    /**
    * Returns the created date of this cfp details.
    *
    * @return the created date of this cfp details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cfpDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this cfp details.
    *
    * @param createdDate the created date of this cfp details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cfpDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the company end date of this cfp details.
    *
    * @return the company end date of this cfp details
    */
    @Override
    public java.util.Date getCompanyEndDate() {
        return _cfpDetails.getCompanyEndDate();
    }

    /**
    * Sets the company end date of this cfp details.
    *
    * @param companyEndDate the company end date of this cfp details
    */
    @Override
    public void setCompanyEndDate(java.util.Date companyEndDate) {
        _cfpDetails.setCompanyEndDate(companyEndDate);
    }

    /**
    * Returns the company master sid of this cfp details.
    *
    * @return the company master sid of this cfp details
    */
    @Override
    public int getCompanyMasterSid() {
        return _cfpDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this cfp details.
    *
    * @param companyMasterSid the company master sid of this cfp details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _cfpDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the company cfp attached date of this cfp details.
    *
    * @return the company cfp attached date of this cfp details
    */
    @Override
    public java.util.Date getCompanyCfpAttachedDate() {
        return _cfpDetails.getCompanyCfpAttachedDate();
    }

    /**
    * Sets the company cfp attached date of this cfp details.
    *
    * @param companyCfpAttachedDate the company cfp attached date of this cfp details
    */
    @Override
    public void setCompanyCfpAttachedDate(java.util.Date companyCfpAttachedDate) {
        _cfpDetails.setCompanyCfpAttachedDate(companyCfpAttachedDate);
    }

    /**
    * Returns the cfp model sid of this cfp details.
    *
    * @return the cfp model sid of this cfp details
    */
    @Override
    public int getCfpModelSid() {
        return _cfpDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this cfp details.
    *
    * @param cfpModelSid the cfp model sid of this cfp details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpDetails.setCfpModelSid(cfpModelSid);
    }

    /**
    * Returns the batch ID of this cfp details.
    *
    * @return the batch ID of this cfp details
    */
    @Override
    public java.lang.String getBatchId() {
        return _cfpDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this cfp details.
    *
    * @param batchId the batch ID of this cfp details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _cfpDetails.setBatchId(batchId);
    }

    /**
    * Returns the cfp details sid of this cfp details.
    *
    * @return the cfp details sid of this cfp details
    */
    @Override
    public int getCfpDetailsSid() {
        return _cfpDetails.getCfpDetailsSid();
    }

    /**
    * Sets the cfp details sid of this cfp details.
    *
    * @param cfpDetailsSid the cfp details sid of this cfp details
    */
    @Override
    public void setCfpDetailsSid(int cfpDetailsSid) {
        _cfpDetails.setCfpDetailsSid(cfpDetailsSid);
    }

    /**
    * Returns the modified date of this cfp details.
    *
    * @return the modified date of this cfp details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cfpDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this cfp details.
    *
    * @param modifiedDate the modified date of this cfp details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cfpDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this cfp details.
    *
    * @return the record lock status of this cfp details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _cfpDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this cfp details is record lock status.
    *
    * @return <code>true</code> if this cfp details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _cfpDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this cfp details is record lock status.
    *
    * @param recordLockStatus the record lock status of this cfp details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _cfpDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this cfp details.
    *
    * @return the source of this cfp details
    */
    @Override
    public java.lang.String getSource() {
        return _cfpDetails.getSource();
    }

    /**
    * Sets the source of this cfp details.
    *
    * @param source the source of this cfp details
    */
    @Override
    public void setSource(java.lang.String source) {
        _cfpDetails.setSource(source);
    }

    /**
    * Returns the inbound status of this cfp details.
    *
    * @return the inbound status of this cfp details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cfpDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cfp details.
    *
    * @param inboundStatus the inbound status of this cfp details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cfpDetails.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _cfpDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cfpDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cfpDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cfpDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cfpDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cfpDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cfpDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cfpDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cfpDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cfpDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cfpDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CfpDetailsWrapper((CfpDetails) _cfpDetails.clone());
    }

    @Override
    public int compareTo(CfpDetails cfpDetails) {
        return _cfpDetails.compareTo(cfpDetails);
    }

    @Override
    public int hashCode() {
        return _cfpDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CfpDetails> toCacheModel() {
        return _cfpDetails.toCacheModel();
    }

    @Override
    public CfpDetails toEscapedModel() {
        return new CfpDetailsWrapper(_cfpDetails.toEscapedModel());
    }

    @Override
    public CfpDetails toUnescapedModel() {
        return new CfpDetailsWrapper(_cfpDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cfpDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cfpDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cfpDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CfpDetailsWrapper)) {
            return false;
        }

        CfpDetailsWrapper cfpDetailsWrapper = (CfpDetailsWrapper) obj;

        if (Validator.equals(_cfpDetails, cfpDetailsWrapper._cfpDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CfpDetails getWrappedCfpDetails() {
        return _cfpDetails;
    }

    @Override
    public CfpDetails getWrappedModel() {
        return _cfpDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cfpDetails.resetOriginalValues();
    }
}
