package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CfpContractDetails}.
 * </p>
 *
 * @author
 * @see CfpContractDetails
 * @generated
 */
public class CfpContractDetailsWrapper implements CfpContractDetails,
    ModelWrapper<CfpContractDetails> {
    private CfpContractDetails _cfpContractDetails;

    public CfpContractDetailsWrapper(CfpContractDetails cfpContractDetails) {
        _cfpContractDetails = cfpContractDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CfpContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CfpContractDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("cfpContractAttachedStatus",
            getCfpContractAttachedStatus());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer tradeClass = (Integer) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
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

        Date cfpContractAttachedDate = (Date) attributes.get(
                "cfpContractAttachedDate");

        if (cfpContractAttachedDate != null) {
            setCfpContractAttachedDate(cfpContractAttachedDate);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
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

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        Integer cfpContractAttachedStatus = (Integer) attributes.get(
                "cfpContractAttachedStatus");

        if (cfpContractAttachedStatus != null) {
            setCfpContractAttachedStatus(cfpContractAttachedStatus);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this cfp contract details.
    *
    * @return the primary key of this cfp contract details
    */
    @Override
    public int getPrimaryKey() {
        return _cfpContractDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cfp contract details.
    *
    * @param primaryKey the primary key of this cfp contract details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cfpContractDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cfp contract details.
    *
    * @return the created by of this cfp contract details
    */
    @Override
    public int getCreatedBy() {
        return _cfpContractDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this cfp contract details.
    *
    * @param createdBy the created by of this cfp contract details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cfpContractDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the trade class of this cfp contract details.
    *
    * @return the trade class of this cfp contract details
    */
    @Override
    public int getTradeClass() {
        return _cfpContractDetails.getTradeClass();
    }

    /**
    * Sets the trade class of this cfp contract details.
    *
    * @param tradeClass the trade class of this cfp contract details
    */
    @Override
    public void setTradeClass(int tradeClass) {
        _cfpContractDetails.setTradeClass(tradeClass);
    }

    /**
    * Returns the trade class end date of this cfp contract details.
    *
    * @return the trade class end date of this cfp contract details
    */
    @Override
    public java.util.Date getTradeClassEndDate() {
        return _cfpContractDetails.getTradeClassEndDate();
    }

    /**
    * Sets the trade class end date of this cfp contract details.
    *
    * @param tradeClassEndDate the trade class end date of this cfp contract details
    */
    @Override
    public void setTradeClassEndDate(java.util.Date tradeClassEndDate) {
        _cfpContractDetails.setTradeClassEndDate(tradeClassEndDate);
    }

    /**
    * Returns the cfp contract sid of this cfp contract details.
    *
    * @return the cfp contract sid of this cfp contract details
    */
    @Override
    public int getCfpContractSid() {
        return _cfpContractDetails.getCfpContractSid();
    }

    /**
    * Sets the cfp contract sid of this cfp contract details.
    *
    * @param cfpContractSid the cfp contract sid of this cfp contract details
    */
    @Override
    public void setCfpContractSid(int cfpContractSid) {
        _cfpContractDetails.setCfpContractSid(cfpContractSid);
    }

    /**
    * Returns the modified by of this cfp contract details.
    *
    * @return the modified by of this cfp contract details
    */
    @Override
    public int getModifiedBy() {
        return _cfpContractDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this cfp contract details.
    *
    * @param modifiedBy the modified by of this cfp contract details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cfpContractDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the company start date of this cfp contract details.
    *
    * @return the company start date of this cfp contract details
    */
    @Override
    public java.util.Date getCompanyStartDate() {
        return _cfpContractDetails.getCompanyStartDate();
    }

    /**
    * Sets the company start date of this cfp contract details.
    *
    * @param companyStartDate the company start date of this cfp contract details
    */
    @Override
    public void setCompanyStartDate(java.util.Date companyStartDate) {
        _cfpContractDetails.setCompanyStartDate(companyStartDate);
    }

    /**
    * Returns the trade class start date of this cfp contract details.
    *
    * @return the trade class start date of this cfp contract details
    */
    @Override
    public java.util.Date getTradeClassStartDate() {
        return _cfpContractDetails.getTradeClassStartDate();
    }

    /**
    * Sets the trade class start date of this cfp contract details.
    *
    * @param tradeClassStartDate the trade class start date of this cfp contract details
    */
    @Override
    public void setTradeClassStartDate(java.util.Date tradeClassStartDate) {
        _cfpContractDetails.setTradeClassStartDate(tradeClassStartDate);
    }

    /**
    * Returns the created date of this cfp contract details.
    *
    * @return the created date of this cfp contract details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cfpContractDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this cfp contract details.
    *
    * @param createdDate the created date of this cfp contract details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cfpContractDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the cfp contract attached date of this cfp contract details.
    *
    * @return the cfp contract attached date of this cfp contract details
    */
    @Override
    public java.util.Date getCfpContractAttachedDate() {
        return _cfpContractDetails.getCfpContractAttachedDate();
    }

    /**
    * Sets the cfp contract attached date of this cfp contract details.
    *
    * @param cfpContractAttachedDate the cfp contract attached date of this cfp contract details
    */
    @Override
    public void setCfpContractAttachedDate(
        java.util.Date cfpContractAttachedDate) {
        _cfpContractDetails.setCfpContractAttachedDate(cfpContractAttachedDate);
    }

    /**
    * Returns the company end date of this cfp contract details.
    *
    * @return the company end date of this cfp contract details
    */
    @Override
    public java.util.Date getCompanyEndDate() {
        return _cfpContractDetails.getCompanyEndDate();
    }

    /**
    * Sets the company end date of this cfp contract details.
    *
    * @param companyEndDate the company end date of this cfp contract details
    */
    @Override
    public void setCompanyEndDate(java.util.Date companyEndDate) {
        _cfpContractDetails.setCompanyEndDate(companyEndDate);
    }

    /**
    * Returns the company master sid of this cfp contract details.
    *
    * @return the company master sid of this cfp contract details
    */
    @Override
    public int getCompanyMasterSid() {
        return _cfpContractDetails.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this cfp contract details.
    *
    * @param companyMasterSid the company master sid of this cfp contract details
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _cfpContractDetails.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the batch ID of this cfp contract details.
    *
    * @return the batch ID of this cfp contract details
    */
    @Override
    public java.lang.String getBatchId() {
        return _cfpContractDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this cfp contract details.
    *
    * @param batchId the batch ID of this cfp contract details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _cfpContractDetails.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this cfp contract details.
    *
    * @return the modified date of this cfp contract details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cfpContractDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this cfp contract details.
    *
    * @param modifiedDate the modified date of this cfp contract details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cfpContractDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this cfp contract details.
    *
    * @return the record lock status of this cfp contract details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _cfpContractDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this cfp contract details is record lock status.
    *
    * @return <code>true</code> if this cfp contract details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _cfpContractDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this cfp contract details is record lock status.
    *
    * @param recordLockStatus the record lock status of this cfp contract details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _cfpContractDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this cfp contract details.
    *
    * @return the source of this cfp contract details
    */
    @Override
    public java.lang.String getSource() {
        return _cfpContractDetails.getSource();
    }

    /**
    * Sets the source of this cfp contract details.
    *
    * @param source the source of this cfp contract details
    */
    @Override
    public void setSource(java.lang.String source) {
        _cfpContractDetails.setSource(source);
    }

    /**
    * Returns the cfp contract details sid of this cfp contract details.
    *
    * @return the cfp contract details sid of this cfp contract details
    */
    @Override
    public int getCfpContractDetailsSid() {
        return _cfpContractDetails.getCfpContractDetailsSid();
    }

    /**
    * Sets the cfp contract details sid of this cfp contract details.
    *
    * @param cfpContractDetailsSid the cfp contract details sid of this cfp contract details
    */
    @Override
    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _cfpContractDetails.setCfpContractDetailsSid(cfpContractDetailsSid);
    }

    /**
    * Returns the cfp contract attached status of this cfp contract details.
    *
    * @return the cfp contract attached status of this cfp contract details
    */
    @Override
    public int getCfpContractAttachedStatus() {
        return _cfpContractDetails.getCfpContractAttachedStatus();
    }

    /**
    * Sets the cfp contract attached status of this cfp contract details.
    *
    * @param cfpContractAttachedStatus the cfp contract attached status of this cfp contract details
    */
    @Override
    public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
        _cfpContractDetails.setCfpContractAttachedStatus(cfpContractAttachedStatus);
    }

    /**
    * Returns the inbound status of this cfp contract details.
    *
    * @return the inbound status of this cfp contract details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cfpContractDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cfp contract details.
    *
    * @param inboundStatus the inbound status of this cfp contract details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cfpContractDetails.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _cfpContractDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cfpContractDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cfpContractDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cfpContractDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cfpContractDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cfpContractDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cfpContractDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cfpContractDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cfpContractDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cfpContractDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cfpContractDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CfpContractDetailsWrapper((CfpContractDetails) _cfpContractDetails.clone());
    }

    @Override
    public int compareTo(CfpContractDetails cfpContractDetails) {
        return _cfpContractDetails.compareTo(cfpContractDetails);
    }

    @Override
    public int hashCode() {
        return _cfpContractDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CfpContractDetails> toCacheModel() {
        return _cfpContractDetails.toCacheModel();
    }

    @Override
    public CfpContractDetails toEscapedModel() {
        return new CfpContractDetailsWrapper(_cfpContractDetails.toEscapedModel());
    }

    @Override
    public CfpContractDetails toUnescapedModel() {
        return new CfpContractDetailsWrapper(_cfpContractDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cfpContractDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cfpContractDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cfpContractDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CfpContractDetailsWrapper)) {
            return false;
        }

        CfpContractDetailsWrapper cfpContractDetailsWrapper = (CfpContractDetailsWrapper) obj;

        if (Validator.equals(_cfpContractDetails,
                    cfpContractDetailsWrapper._cfpContractDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CfpContractDetails getWrappedCfpContractDetails() {
        return _cfpContractDetails;
    }

    @Override
    public CfpContractDetails getWrappedModel() {
        return _cfpContractDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cfpContractDetails.resetOriginalValues();
    }
}
