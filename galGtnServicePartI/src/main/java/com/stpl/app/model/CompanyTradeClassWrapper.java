package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CompanyTradeClass}.
 * </p>
 *
 * @author
 * @see CompanyTradeClass
 * @generated
 */
public class CompanyTradeClassWrapper implements CompanyTradeClass,
    ModelWrapper<CompanyTradeClass> {
    private CompanyTradeClass _companyTradeClass;

    public CompanyTradeClassWrapper(CompanyTradeClass companyTradeClass) {
        _companyTradeClass = companyTradeClass;
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyTradeClass.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("batchId", getBatchId());
        attributes.put("companyTradeClass", getCompanyTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer priorTradeClass = (Integer) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        Integer companyTradeClassSid = (Integer) attributes.get(
                "companyTradeClassSid");

        if (companyTradeClassSid != null) {
            setCompanyTradeClassSid(companyTradeClassSid);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date priorTradeClassStartDate = (Date) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyTradeClass = (Integer) attributes.get(
                "companyTradeClass");

        if (companyTradeClass != null) {
            setCompanyTradeClass(companyTradeClass);
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
    * Returns the primary key of this company trade class.
    *
    * @return the primary key of this company trade class
    */
    @Override
    public int getPrimaryKey() {
        return _companyTradeClass.getPrimaryKey();
    }

    /**
    * Sets the primary key of this company trade class.
    *
    * @param primaryKey the primary key of this company trade class
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _companyTradeClass.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the prior trade class of this company trade class.
    *
    * @return the prior trade class of this company trade class
    */
    @Override
    public int getPriorTradeClass() {
        return _companyTradeClass.getPriorTradeClass();
    }

    /**
    * Sets the prior trade class of this company trade class.
    *
    * @param priorTradeClass the prior trade class of this company trade class
    */
    @Override
    public void setPriorTradeClass(int priorTradeClass) {
        _companyTradeClass.setPriorTradeClass(priorTradeClass);
    }

    /**
    * Returns the company trade class sid of this company trade class.
    *
    * @return the company trade class sid of this company trade class
    */
    @Override
    public int getCompanyTradeClassSid() {
        return _companyTradeClass.getCompanyTradeClassSid();
    }

    /**
    * Sets the company trade class sid of this company trade class.
    *
    * @param companyTradeClassSid the company trade class sid of this company trade class
    */
    @Override
    public void setCompanyTradeClassSid(int companyTradeClassSid) {
        _companyTradeClass.setCompanyTradeClassSid(companyTradeClassSid);
    }

    /**
    * Returns the last updated date of this company trade class.
    *
    * @return the last updated date of this company trade class
    */
    @Override
    public java.util.Date getLastUpdatedDate() {
        return _companyTradeClass.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this company trade class.
    *
    * @param lastUpdatedDate the last updated date of this company trade class
    */
    @Override
    public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
        _companyTradeClass.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the prior trade class start date of this company trade class.
    *
    * @return the prior trade class start date of this company trade class
    */
    @Override
    public java.util.Date getPriorTradeClassStartDate() {
        return _companyTradeClass.getPriorTradeClassStartDate();
    }

    /**
    * Sets the prior trade class start date of this company trade class.
    *
    * @param priorTradeClassStartDate the prior trade class start date of this company trade class
    */
    @Override
    public void setPriorTradeClassStartDate(
        java.util.Date priorTradeClassStartDate) {
        _companyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
    }

    /**
    * Returns the modified date of this company trade class.
    *
    * @return the modified date of this company trade class
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _companyTradeClass.getModifiedDate();
    }

    /**
    * Sets the modified date of this company trade class.
    *
    * @param modifiedDate the modified date of this company trade class
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _companyTradeClass.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the trade class end date of this company trade class.
    *
    * @return the trade class end date of this company trade class
    */
    @Override
    public java.util.Date getTradeClassEndDate() {
        return _companyTradeClass.getTradeClassEndDate();
    }

    /**
    * Sets the trade class end date of this company trade class.
    *
    * @param tradeClassEndDate the trade class end date of this company trade class
    */
    @Override
    public void setTradeClassEndDate(java.util.Date tradeClassEndDate) {
        _companyTradeClass.setTradeClassEndDate(tradeClassEndDate);
    }

    /**
    * Returns the trade class start date of this company trade class.
    *
    * @return the trade class start date of this company trade class
    */
    @Override
    public java.util.Date getTradeClassStartDate() {
        return _companyTradeClass.getTradeClassStartDate();
    }

    /**
    * Sets the trade class start date of this company trade class.
    *
    * @param tradeClassStartDate the trade class start date of this company trade class
    */
    @Override
    public void setTradeClassStartDate(java.util.Date tradeClassStartDate) {
        _companyTradeClass.setTradeClassStartDate(tradeClassStartDate);
    }

    /**
    * Returns the record lock status of this company trade class.
    *
    * @return the record lock status of this company trade class
    */
    @Override
    public boolean getRecordLockStatus() {
        return _companyTradeClass.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this company trade class is record lock status.
    *
    * @return <code>true</code> if this company trade class is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _companyTradeClass.isRecordLockStatus();
    }

    /**
    * Sets whether this company trade class is record lock status.
    *
    * @param recordLockStatus the record lock status of this company trade class
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _companyTradeClass.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this company trade class.
    *
    * @return the created date of this company trade class
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _companyTradeClass.getCreatedDate();
    }

    /**
    * Sets the created date of this company trade class.
    *
    * @param createdDate the created date of this company trade class
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _companyTradeClass.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this company trade class.
    *
    * @return the source of this company trade class
    */
    @Override
    public java.lang.String getSource() {
        return _companyTradeClass.getSource();
    }

    /**
    * Sets the source of this company trade class.
    *
    * @param source the source of this company trade class
    */
    @Override
    public void setSource(java.lang.String source) {
        _companyTradeClass.setSource(source);
    }

    /**
    * Returns the created by of this company trade class.
    *
    * @return the created by of this company trade class
    */
    @Override
    public int getCreatedBy() {
        return _companyTradeClass.getCreatedBy();
    }

    /**
    * Sets the created by of this company trade class.
    *
    * @param createdBy the created by of this company trade class
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _companyTradeClass.setCreatedBy(createdBy);
    }

    /**
    * Returns the batch ID of this company trade class.
    *
    * @return the batch ID of this company trade class
    */
    @Override
    public java.lang.String getBatchId() {
        return _companyTradeClass.getBatchId();
    }

    /**
    * Sets the batch ID of this company trade class.
    *
    * @param batchId the batch ID of this company trade class
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _companyTradeClass.setBatchId(batchId);
    }

    /**
    * Returns the company trade class of this company trade class.
    *
    * @return the company trade class of this company trade class
    */
    @Override
    public int getCompanyTradeClass() {
        return _companyTradeClass.getCompanyTradeClass();
    }

    /**
    * Sets the company trade class of this company trade class.
    *
    * @param companyTradeClass the company trade class of this company trade class
    */
    @Override
    public void setCompanyTradeClass(int companyTradeClass) {
        _companyTradeClass.setCompanyTradeClass(companyTradeClass);
    }

    /**
    * Returns the modified by of this company trade class.
    *
    * @return the modified by of this company trade class
    */
    @Override
    public int getModifiedBy() {
        return _companyTradeClass.getModifiedBy();
    }

    /**
    * Sets the modified by of this company trade class.
    *
    * @param modifiedBy the modified by of this company trade class
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _companyTradeClass.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this company trade class.
    *
    * @return the inbound status of this company trade class
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _companyTradeClass.getInboundStatus();
    }

    /**
    * Sets the inbound status of this company trade class.
    *
    * @param inboundStatus the inbound status of this company trade class
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _companyTradeClass.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the company master sid of this company trade class.
    *
    * @return the company master sid of this company trade class
    */
    @Override
    public int getCompanyMasterSid() {
        return _companyTradeClass.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this company trade class.
    *
    * @param companyMasterSid the company master sid of this company trade class
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyTradeClass.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _companyTradeClass.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _companyTradeClass.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _companyTradeClass.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _companyTradeClass.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _companyTradeClass.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _companyTradeClass.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _companyTradeClass.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _companyTradeClass.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _companyTradeClass.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _companyTradeClass.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _companyTradeClass.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CompanyTradeClassWrapper((CompanyTradeClass) _companyTradeClass.clone());
    }

    @Override
    public int compareTo(CompanyTradeClass companyTradeClass) {
        return _companyTradeClass.compareTo(companyTradeClass);
    }

    @Override
    public int hashCode() {
        return _companyTradeClass.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CompanyTradeClass> toCacheModel() {
        return _companyTradeClass.toCacheModel();
    }

    @Override
    public CompanyTradeClass toEscapedModel() {
        return new CompanyTradeClassWrapper(_companyTradeClass.toEscapedModel());
    }

    @Override
    public CompanyTradeClass toUnescapedModel() {
        return new CompanyTradeClassWrapper(_companyTradeClass.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _companyTradeClass.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _companyTradeClass.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _companyTradeClass.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CompanyTradeClassWrapper)) {
            return false;
        }

        CompanyTradeClassWrapper companyTradeClassWrapper = (CompanyTradeClassWrapper) obj;

        if (Validator.equals(_companyTradeClass,
                    companyTradeClassWrapper._companyTradeClass)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CompanyTradeClass getWrappedCompanyTradeClass() {
        return _companyTradeClass;
    }

    @Override
    public CompanyTradeClass getWrappedModel() {
        return _companyTradeClass;
    }

    @Override
    public void resetOriginalValues() {
        _companyTradeClass.resetOriginalValues();
    }
}
