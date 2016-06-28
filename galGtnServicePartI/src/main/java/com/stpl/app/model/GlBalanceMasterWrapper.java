package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link GlBalanceMaster}.
 * </p>
 *
 * @author
 * @see GlBalanceMaster
 * @generated
 */
public class GlBalanceMasterWrapper implements GlBalanceMaster,
    ModelWrapper<GlBalanceMaster> {
    private GlBalanceMaster _glBalanceMaster;

    public GlBalanceMasterWrapper(GlBalanceMaster glBalanceMaster) {
        _glBalanceMaster = glBalanceMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return GlBalanceMaster.class;
    }

    @Override
    public String getModelClassName() {
        return GlBalanceMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accountId", getAccountId());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("glBalanceMasterSid", getGlBalanceMasterSid());
        attributes.put("isActive", getIsActive());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("balance", getBalance());
        attributes.put("closeIndicator", getCloseIndicator());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("year", getYear());
        attributes.put("period", getPeriod());
        attributes.put("source", getSource());
        attributes.put("insertedDate", getInsertedDate());
        attributes.put("accountNo", getAccountNo());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer glBalanceMasterSid = (Integer) attributes.get(
                "glBalanceMasterSid");

        if (glBalanceMasterSid != null) {
            setGlBalanceMasterSid(glBalanceMasterSid);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String balance = (String) attributes.get("balance");

        if (balance != null) {
            setBalance(balance);
        }

        String closeIndicator = (String) attributes.get("closeIndicator");

        if (closeIndicator != null) {
            setCloseIndicator(closeIndicator);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date insertedDate = (Date) attributes.get("insertedDate");

        if (insertedDate != null) {
            setInsertedDate(insertedDate);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this gl balance master.
    *
    * @return the primary key of this gl balance master
    */
    @Override
    public int getPrimaryKey() {
        return _glBalanceMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this gl balance master.
    *
    * @param primaryKey the primary key of this gl balance master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _glBalanceMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this gl balance master.
    *
    * @return the created by of this gl balance master
    */
    @Override
    public int getCreatedBy() {
        return _glBalanceMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this gl balance master.
    *
    * @param createdBy the created by of this gl balance master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _glBalanceMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the modified by of this gl balance master.
    *
    * @return the modified by of this gl balance master
    */
    @Override
    public int getModifiedBy() {
        return _glBalanceMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this gl balance master.
    *
    * @param modifiedBy the modified by of this gl balance master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _glBalanceMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the account ID of this gl balance master.
    *
    * @return the account ID of this gl balance master
    */
    @Override
    public java.lang.String getAccountId() {
        return _glBalanceMaster.getAccountId();
    }

    /**
    * Sets the account ID of this gl balance master.
    *
    * @param accountId the account ID of this gl balance master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _glBalanceMaster.setAccountId(accountId);
    }

    /**
    * Returns the upload date of this gl balance master.
    *
    * @return the upload date of this gl balance master
    */
    @Override
    public java.util.Date getUploadDate() {
        return _glBalanceMaster.getUploadDate();
    }

    /**
    * Sets the upload date of this gl balance master.
    *
    * @param uploadDate the upload date of this gl balance master
    */
    @Override
    public void setUploadDate(java.util.Date uploadDate) {
        _glBalanceMaster.setUploadDate(uploadDate);
    }

    /**
    * Returns the created date of this gl balance master.
    *
    * @return the created date of this gl balance master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _glBalanceMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this gl balance master.
    *
    * @param createdDate the created date of this gl balance master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _glBalanceMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the gl balance master sid of this gl balance master.
    *
    * @return the gl balance master sid of this gl balance master
    */
    @Override
    public int getGlBalanceMasterSid() {
        return _glBalanceMaster.getGlBalanceMasterSid();
    }

    /**
    * Sets the gl balance master sid of this gl balance master.
    *
    * @param glBalanceMasterSid the gl balance master sid of this gl balance master
    */
    @Override
    public void setGlBalanceMasterSid(int glBalanceMasterSid) {
        _glBalanceMaster.setGlBalanceMasterSid(glBalanceMasterSid);
    }

    /**
    * Returns the is active of this gl balance master.
    *
    * @return the is active of this gl balance master
    */
    @Override
    public java.lang.String getIsActive() {
        return _glBalanceMaster.getIsActive();
    }

    /**
    * Sets the is active of this gl balance master.
    *
    * @param isActive the is active of this gl balance master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _glBalanceMaster.setIsActive(isActive);
    }

    /**
    * Returns the batch ID of this gl balance master.
    *
    * @return the batch ID of this gl balance master
    */
    @Override
    public java.lang.String getBatchId() {
        return _glBalanceMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this gl balance master.
    *
    * @param batchId the batch ID of this gl balance master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _glBalanceMaster.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this gl balance master.
    *
    * @return the modified date of this gl balance master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _glBalanceMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this gl balance master.
    *
    * @param modifiedDate the modified date of this gl balance master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _glBalanceMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the balance of this gl balance master.
    *
    * @return the balance of this gl balance master
    */
    @Override
    public java.lang.String getBalance() {
        return _glBalanceMaster.getBalance();
    }

    /**
    * Sets the balance of this gl balance master.
    *
    * @param balance the balance of this gl balance master
    */
    @Override
    public void setBalance(java.lang.String balance) {
        _glBalanceMaster.setBalance(balance);
    }

    /**
    * Returns the close indicator of this gl balance master.
    *
    * @return the close indicator of this gl balance master
    */
    @Override
    public java.lang.String getCloseIndicator() {
        return _glBalanceMaster.getCloseIndicator();
    }

    /**
    * Sets the close indicator of this gl balance master.
    *
    * @param closeIndicator the close indicator of this gl balance master
    */
    @Override
    public void setCloseIndicator(java.lang.String closeIndicator) {
        _glBalanceMaster.setCloseIndicator(closeIndicator);
    }

    /**
    * Returns the record lock status of this gl balance master.
    *
    * @return the record lock status of this gl balance master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _glBalanceMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this gl balance master is record lock status.
    *
    * @return <code>true</code> if this gl balance master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _glBalanceMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this gl balance master is record lock status.
    *
    * @param recordLockStatus the record lock status of this gl balance master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _glBalanceMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the year of this gl balance master.
    *
    * @return the year of this gl balance master
    */
    @Override
    public java.lang.String getYear() {
        return _glBalanceMaster.getYear();
    }

    /**
    * Sets the year of this gl balance master.
    *
    * @param year the year of this gl balance master
    */
    @Override
    public void setYear(java.lang.String year) {
        _glBalanceMaster.setYear(year);
    }

    /**
    * Returns the period of this gl balance master.
    *
    * @return the period of this gl balance master
    */
    @Override
    public java.lang.String getPeriod() {
        return _glBalanceMaster.getPeriod();
    }

    /**
    * Sets the period of this gl balance master.
    *
    * @param period the period of this gl balance master
    */
    @Override
    public void setPeriod(java.lang.String period) {
        _glBalanceMaster.setPeriod(period);
    }

    /**
    * Returns the source of this gl balance master.
    *
    * @return the source of this gl balance master
    */
    @Override
    public java.lang.String getSource() {
        return _glBalanceMaster.getSource();
    }

    /**
    * Sets the source of this gl balance master.
    *
    * @param source the source of this gl balance master
    */
    @Override
    public void setSource(java.lang.String source) {
        _glBalanceMaster.setSource(source);
    }

    /**
    * Returns the inserted date of this gl balance master.
    *
    * @return the inserted date of this gl balance master
    */
    @Override
    public java.util.Date getInsertedDate() {
        return _glBalanceMaster.getInsertedDate();
    }

    /**
    * Sets the inserted date of this gl balance master.
    *
    * @param insertedDate the inserted date of this gl balance master
    */
    @Override
    public void setInsertedDate(java.util.Date insertedDate) {
        _glBalanceMaster.setInsertedDate(insertedDate);
    }

    /**
    * Returns the account no of this gl balance master.
    *
    * @return the account no of this gl balance master
    */
    @Override
    public java.lang.String getAccountNo() {
        return _glBalanceMaster.getAccountNo();
    }

    /**
    * Sets the account no of this gl balance master.
    *
    * @param accountNo the account no of this gl balance master
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _glBalanceMaster.setAccountNo(accountNo);
    }

    /**
    * Returns the inbound status of this gl balance master.
    *
    * @return the inbound status of this gl balance master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _glBalanceMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this gl balance master.
    *
    * @param inboundStatus the inbound status of this gl balance master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _glBalanceMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _glBalanceMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _glBalanceMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _glBalanceMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _glBalanceMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _glBalanceMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _glBalanceMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _glBalanceMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _glBalanceMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _glBalanceMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _glBalanceMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _glBalanceMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new GlBalanceMasterWrapper((GlBalanceMaster) _glBalanceMaster.clone());
    }

    @Override
    public int compareTo(GlBalanceMaster glBalanceMaster) {
        return _glBalanceMaster.compareTo(glBalanceMaster);
    }

    @Override
    public int hashCode() {
        return _glBalanceMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<GlBalanceMaster> toCacheModel() {
        return _glBalanceMaster.toCacheModel();
    }

    @Override
    public GlBalanceMaster toEscapedModel() {
        return new GlBalanceMasterWrapper(_glBalanceMaster.toEscapedModel());
    }

    @Override
    public GlBalanceMaster toUnescapedModel() {
        return new GlBalanceMasterWrapper(_glBalanceMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _glBalanceMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _glBalanceMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _glBalanceMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof GlBalanceMasterWrapper)) {
            return false;
        }

        GlBalanceMasterWrapper glBalanceMasterWrapper = (GlBalanceMasterWrapper) obj;

        if (Validator.equals(_glBalanceMaster,
                    glBalanceMasterWrapper._glBalanceMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public GlBalanceMaster getWrappedGlBalanceMaster() {
        return _glBalanceMaster;
    }

    @Override
    public GlBalanceMaster getWrappedModel() {
        return _glBalanceMaster;
    }

    @Override
    public void resetOriginalValues() {
        _glBalanceMaster.resetOriginalValues();
    }
}
