package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsDetailsFr}.
 * </p>
 *
 * @author
 * @see RsDetailsFr
 * @generated
 */
public class RsDetailsFrWrapper implements RsDetailsFr,
    ModelWrapper<RsDetailsFr> {
    private RsDetailsFr _rsDetailsFr;

    public RsDetailsFrWrapper(RsDetailsFr rsDetailsFr) {
        _rsDetailsFr = rsDetailsFr;
    }

    @Override
    public Class<?> getModelClass() {
        return RsDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return RsDetailsFr.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsDetailsFrSid", getRsDetailsFrSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
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

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsDetailsFrSid = (Integer) attributes.get("rsDetailsFrSid");

        if (rsDetailsFrSid != null) {
            setRsDetailsFrSid(rsDetailsFrSid);
        }
    }

    /**
    * Returns the primary key of this rs details fr.
    *
    * @return the primary key of this rs details fr
    */
    @Override
    public int getPrimaryKey() {
        return _rsDetailsFr.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs details fr.
    *
    * @param primaryKey the primary key of this rs details fr
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsDetailsFr.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the record lock status of this rs details fr.
    *
    * @return the record lock status of this rs details fr
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsDetailsFr.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs details fr is record lock status.
    *
    * @return <code>true</code> if this rs details fr is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsDetailsFr.isRecordLockStatus();
    }

    /**
    * Sets whether this rs details fr is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs details fr
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsDetailsFr.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this rs details fr.
    *
    * @return the created date of this rs details fr
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsDetailsFr.getCreatedDate();
    }

    /**
    * Sets the created date of this rs details fr.
    *
    * @param createdDate the created date of this rs details fr
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsDetailsFr.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this rs details fr.
    *
    * @return the created by of this rs details fr
    */
    @Override
    public int getCreatedBy() {
        return _rsDetailsFr.getCreatedBy();
    }

    /**
    * Sets the created by of this rs details fr.
    *
    * @param createdBy the created by of this rs details fr
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsDetailsFr.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this rs details fr.
    *
    * @return the source of this rs details fr
    */
    @Override
    public java.lang.String getSource() {
        return _rsDetailsFr.getSource();
    }

    /**
    * Sets the source of this rs details fr.
    *
    * @param source the source of this rs details fr
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsDetailsFr.setSource(source);
    }

    /**
    * Returns the formula method ID of this rs details fr.
    *
    * @return the formula method ID of this rs details fr
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsDetailsFr.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs details fr.
    *
    * @param formulaMethodId the formula method ID of this rs details fr
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsDetailsFr.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the batch ID of this rs details fr.
    *
    * @return the batch ID of this rs details fr
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsDetailsFr.getBatchId();
    }

    /**
    * Sets the batch ID of this rs details fr.
    *
    * @param batchId the batch ID of this rs details fr
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsDetailsFr.setBatchId(batchId);
    }

    /**
    * Returns the modified by of this rs details fr.
    *
    * @return the modified by of this rs details fr
    */
    @Override
    public int getModifiedBy() {
        return _rsDetailsFr.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs details fr.
    *
    * @param modifiedBy the modified by of this rs details fr
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsDetailsFr.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this rs details fr.
    *
    * @return the inbound status of this rs details fr
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsDetailsFr.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs details fr.
    *
    * @param inboundStatus the inbound status of this rs details fr
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsDetailsFr.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the formula ID of this rs details fr.
    *
    * @return the formula ID of this rs details fr
    */
    @Override
    public int getFormulaId() {
        return _rsDetailsFr.getFormulaId();
    }

    /**
    * Sets the formula ID of this rs details fr.
    *
    * @param formulaId the formula ID of this rs details fr
    */
    @Override
    public void setFormulaId(int formulaId) {
        _rsDetailsFr.setFormulaId(formulaId);
    }

    /**
    * Returns the item master sid of this rs details fr.
    *
    * @return the item master sid of this rs details fr
    */
    @Override
    public int getItemMasterSid() {
        return _rsDetailsFr.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this rs details fr.
    *
    * @param itemMasterSid the item master sid of this rs details fr
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _rsDetailsFr.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the rs details sid of this rs details fr.
    *
    * @return the rs details sid of this rs details fr
    */
    @Override
    public int getRsDetailsSid() {
        return _rsDetailsFr.getRsDetailsSid();
    }

    /**
    * Sets the rs details sid of this rs details fr.
    *
    * @param rsDetailsSid the rs details sid of this rs details fr
    */
    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsFr.setRsDetailsSid(rsDetailsSid);
    }

    /**
    * Returns the modified date of this rs details fr.
    *
    * @return the modified date of this rs details fr
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsDetailsFr.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs details fr.
    *
    * @param modifiedDate the modified date of this rs details fr
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsDetailsFr.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rs details fr sid of this rs details fr.
    *
    * @return the rs details fr sid of this rs details fr
    */
    @Override
    public int getRsDetailsFrSid() {
        return _rsDetailsFr.getRsDetailsFrSid();
    }

    /**
    * Sets the rs details fr sid of this rs details fr.
    *
    * @param rsDetailsFrSid the rs details fr sid of this rs details fr
    */
    @Override
    public void setRsDetailsFrSid(int rsDetailsFrSid) {
        _rsDetailsFr.setRsDetailsFrSid(rsDetailsFrSid);
    }

    @Override
    public boolean isNew() {
        return _rsDetailsFr.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsDetailsFr.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsDetailsFr.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsDetailsFr.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsDetailsFr.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsDetailsFr.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsDetailsFr.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsDetailsFr.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsDetailsFr.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsDetailsFr.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsDetailsFr.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsDetailsFrWrapper((RsDetailsFr) _rsDetailsFr.clone());
    }

    @Override
    public int compareTo(RsDetailsFr rsDetailsFr) {
        return _rsDetailsFr.compareTo(rsDetailsFr);
    }

    @Override
    public int hashCode() {
        return _rsDetailsFr.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsDetailsFr> toCacheModel() {
        return _rsDetailsFr.toCacheModel();
    }

    @Override
    public RsDetailsFr toEscapedModel() {
        return new RsDetailsFrWrapper(_rsDetailsFr.toEscapedModel());
    }

    @Override
    public RsDetailsFr toUnescapedModel() {
        return new RsDetailsFrWrapper(_rsDetailsFr.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsDetailsFr.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsDetailsFr.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsDetailsFr.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsDetailsFrWrapper)) {
            return false;
        }

        RsDetailsFrWrapper rsDetailsFrWrapper = (RsDetailsFrWrapper) obj;

        if (Validator.equals(_rsDetailsFr, rsDetailsFrWrapper._rsDetailsFr)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsDetailsFr getWrappedRsDetailsFr() {
        return _rsDetailsFr;
    }

    @Override
    public RsDetailsFr getWrappedModel() {
        return _rsDetailsFr;
    }

    @Override
    public void resetOriginalValues() {
        _rsDetailsFr.resetOriginalValues();
    }
}
