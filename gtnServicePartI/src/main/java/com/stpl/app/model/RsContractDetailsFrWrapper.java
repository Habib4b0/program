package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RsContractDetailsFr}.
 * </p>
 *
 * @author
 * @see RsContractDetailsFr
 * @generated
 */
public class RsContractDetailsFrWrapper implements RsContractDetailsFr,
    ModelWrapper<RsContractDetailsFr> {
    private RsContractDetailsFr _rsContractDetailsFr;

    public RsContractDetailsFrWrapper(RsContractDetailsFr rsContractDetailsFr) {
        _rsContractDetailsFr = rsContractDetailsFr;
    }

    @Override
    public Class<?> getModelClass() {
        return RsContractDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return RsContractDetailsFr.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("rsContractDetailsFrSid", getRsContractDetailsFrSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsContractDetailsSid", getRsContractDetailsSid());

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

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer rsContractDetailsFrSid = (Integer) attributes.get(
                "rsContractDetailsFrSid");

        if (rsContractDetailsFrSid != null) {
            setRsContractDetailsFrSid(rsContractDetailsFrSid);
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

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsContractDetailsSid = (Integer) attributes.get(
                "rsContractDetailsSid");

        if (rsContractDetailsSid != null) {
            setRsContractDetailsSid(rsContractDetailsSid);
        }
    }

    /**
    * Returns the primary key of this rs contract details fr.
    *
    * @return the primary key of this rs contract details fr
    */
    @Override
    public int getPrimaryKey() {
        return _rsContractDetailsFr.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rs contract details fr.
    *
    * @param primaryKey the primary key of this rs contract details fr
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rsContractDetailsFr.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the record lock status of this rs contract details fr.
    *
    * @return the record lock status of this rs contract details fr
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rsContractDetailsFr.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rs contract details fr is record lock status.
    *
    * @return <code>true</code> if this rs contract details fr is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rsContractDetailsFr.isRecordLockStatus();
    }

    /**
    * Sets whether this rs contract details fr is record lock status.
    *
    * @param recordLockStatus the record lock status of this rs contract details fr
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rsContractDetailsFr.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this rs contract details fr.
    *
    * @return the created date of this rs contract details fr
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rsContractDetailsFr.getCreatedDate();
    }

    /**
    * Sets the created date of this rs contract details fr.
    *
    * @param createdDate the created date of this rs contract details fr
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rsContractDetailsFr.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this rs contract details fr.
    *
    * @return the created by of this rs contract details fr
    */
    @Override
    public int getCreatedBy() {
        return _rsContractDetailsFr.getCreatedBy();
    }

    /**
    * Sets the created by of this rs contract details fr.
    *
    * @param createdBy the created by of this rs contract details fr
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rsContractDetailsFr.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this rs contract details fr.
    *
    * @return the source of this rs contract details fr
    */
    @Override
    public java.lang.String getSource() {
        return _rsContractDetailsFr.getSource();
    }

    /**
    * Sets the source of this rs contract details fr.
    *
    * @param source the source of this rs contract details fr
    */
    @Override
    public void setSource(java.lang.String source) {
        _rsContractDetailsFr.setSource(source);
    }

    /**
    * Returns the formula method ID of this rs contract details fr.
    *
    * @return the formula method ID of this rs contract details fr
    */
    @Override
    public java.lang.String getFormulaMethodId() {
        return _rsContractDetailsFr.getFormulaMethodId();
    }

    /**
    * Sets the formula method ID of this rs contract details fr.
    *
    * @param formulaMethodId the formula method ID of this rs contract details fr
    */
    @Override
    public void setFormulaMethodId(java.lang.String formulaMethodId) {
        _rsContractDetailsFr.setFormulaMethodId(formulaMethodId);
    }

    /**
    * Returns the item master sid of this rs contract details fr.
    *
    * @return the item master sid of this rs contract details fr
    */
    @Override
    public int getItemMasterSid() {
        return _rsContractDetailsFr.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this rs contract details fr.
    *
    * @param itemMasterSid the item master sid of this rs contract details fr
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _rsContractDetailsFr.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the batch ID of this rs contract details fr.
    *
    * @return the batch ID of this rs contract details fr
    */
    @Override
    public java.lang.String getBatchId() {
        return _rsContractDetailsFr.getBatchId();
    }

    /**
    * Sets the batch ID of this rs contract details fr.
    *
    * @param batchId the batch ID of this rs contract details fr
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rsContractDetailsFr.setBatchId(batchId);
    }

    /**
    * Returns the rs contract details fr sid of this rs contract details fr.
    *
    * @return the rs contract details fr sid of this rs contract details fr
    */
    @Override
    public int getRsContractDetailsFrSid() {
        return _rsContractDetailsFr.getRsContractDetailsFrSid();
    }

    /**
    * Sets the rs contract details fr sid of this rs contract details fr.
    *
    * @param rsContractDetailsFrSid the rs contract details fr sid of this rs contract details fr
    */
    @Override
    public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
        _rsContractDetailsFr.setRsContractDetailsFrSid(rsContractDetailsFrSid);
    }

    /**
    * Returns the modified by of this rs contract details fr.
    *
    * @return the modified by of this rs contract details fr
    */
    @Override
    public int getModifiedBy() {
        return _rsContractDetailsFr.getModifiedBy();
    }

    /**
    * Sets the modified by of this rs contract details fr.
    *
    * @param modifiedBy the modified by of this rs contract details fr
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rsContractDetailsFr.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this rs contract details fr.
    *
    * @return the inbound status of this rs contract details fr
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rsContractDetailsFr.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rs contract details fr.
    *
    * @param inboundStatus the inbound status of this rs contract details fr
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rsContractDetailsFr.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the formula ID of this rs contract details fr.
    *
    * @return the formula ID of this rs contract details fr
    */
    @Override
    public int getFormulaId() {
        return _rsContractDetailsFr.getFormulaId();
    }

    /**
    * Sets the formula ID of this rs contract details fr.
    *
    * @param formulaId the formula ID of this rs contract details fr
    */
    @Override
    public void setFormulaId(int formulaId) {
        _rsContractDetailsFr.setFormulaId(formulaId);
    }

    /**
    * Returns the modified date of this rs contract details fr.
    *
    * @return the modified date of this rs contract details fr
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rsContractDetailsFr.getModifiedDate();
    }

    /**
    * Sets the modified date of this rs contract details fr.
    *
    * @param modifiedDate the modified date of this rs contract details fr
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rsContractDetailsFr.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rs contract details sid of this rs contract details fr.
    *
    * @return the rs contract details sid of this rs contract details fr
    */
    @Override
    public int getRsContractDetailsSid() {
        return _rsContractDetailsFr.getRsContractDetailsSid();
    }

    /**
    * Sets the rs contract details sid of this rs contract details fr.
    *
    * @param rsContractDetailsSid the rs contract details sid of this rs contract details fr
    */
    @Override
    public void setRsContractDetailsSid(int rsContractDetailsSid) {
        _rsContractDetailsFr.setRsContractDetailsSid(rsContractDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _rsContractDetailsFr.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rsContractDetailsFr.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rsContractDetailsFr.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rsContractDetailsFr.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rsContractDetailsFr.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rsContractDetailsFr.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rsContractDetailsFr.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rsContractDetailsFr.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rsContractDetailsFr.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rsContractDetailsFr.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rsContractDetailsFr.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RsContractDetailsFrWrapper((RsContractDetailsFr) _rsContractDetailsFr.clone());
    }

    @Override
    public int compareTo(RsContractDetailsFr rsContractDetailsFr) {
        return _rsContractDetailsFr.compareTo(rsContractDetailsFr);
    }

    @Override
    public int hashCode() {
        return _rsContractDetailsFr.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RsContractDetailsFr> toCacheModel() {
        return _rsContractDetailsFr.toCacheModel();
    }

    @Override
    public RsContractDetailsFr toEscapedModel() {
        return new RsContractDetailsFrWrapper(_rsContractDetailsFr.toEscapedModel());
    }

    @Override
    public RsContractDetailsFr toUnescapedModel() {
        return new RsContractDetailsFrWrapper(_rsContractDetailsFr.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rsContractDetailsFr.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rsContractDetailsFr.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rsContractDetailsFr.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RsContractDetailsFrWrapper)) {
            return false;
        }

        RsContractDetailsFrWrapper rsContractDetailsFrWrapper = (RsContractDetailsFrWrapper) obj;

        if (Validator.equals(_rsContractDetailsFr,
                    rsContractDetailsFrWrapper._rsContractDetailsFr)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RsContractDetailsFr getWrappedRsContractDetailsFr() {
        return _rsContractDetailsFr;
    }

    @Override
    public RsContractDetailsFr getWrappedModel() {
        return _rsContractDetailsFr;
    }

    @Override
    public void resetOriginalValues() {
        _rsContractDetailsFr.resetOriginalValues();
    }
}
