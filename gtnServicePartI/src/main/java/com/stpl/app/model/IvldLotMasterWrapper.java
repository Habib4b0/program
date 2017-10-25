package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldLotMaster}.
 * </p>
 *
 * @author
 * @see IvldLotMaster
 * @generated
 */
public class IvldLotMasterWrapper implements IvldLotMaster,
    ModelWrapper<IvldLotMaster> {
    private IvldLotMaster _ivldLotMaster;

    public IvldLotMasterWrapper(IvldLotMaster ivldLotMaster) {
        _ivldLotMaster = ivldLotMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldLotMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldLotMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("itemId", getItemId());
        attributes.put("ivldLotMasterSid", getIvldLotMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("lastLotSoldDate", getLastLotSoldDate());
        attributes.put("lotExpirationDate", getLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("lotNo", getLotNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("lotMasterIntfid", getLotMasterIntfid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer ivldLotMasterSid = (Integer) attributes.get("ivldLotMasterSid");

        if (ivldLotMasterSid != null) {
            setIvldLotMasterSid(ivldLotMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String lastLotSoldDate = (String) attributes.get("lastLotSoldDate");

        if (lastLotSoldDate != null) {
            setLastLotSoldDate(lastLotSoldDate);
        }

        String lotExpirationDate = (String) attributes.get("lotExpirationDate");

        if (lotExpirationDate != null) {
            setLotExpirationDate(lotExpirationDate);
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

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String lotMasterIntfid = (String) attributes.get("lotMasterIntfid");

        if (lotMasterIntfid != null) {
            setLotMasterIntfid(lotMasterIntfid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld lot master.
    *
    * @return the primary key of this ivld lot master
    */
    @Override
    public int getPrimaryKey() {
        return _ivldLotMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld lot master.
    *
    * @param primaryKey the primary key of this ivld lot master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldLotMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the reason for failure of this ivld lot master.
    *
    * @return the reason for failure of this ivld lot master
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldLotMaster.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld lot master.
    *
    * @param reasonForFailure the reason for failure of this ivld lot master
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldLotMaster.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the item ID of this ivld lot master.
    *
    * @return the item ID of this ivld lot master
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldLotMaster.getItemId();
    }

    /**
    * Sets the item ID of this ivld lot master.
    *
    * @param itemId the item ID of this ivld lot master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldLotMaster.setItemId(itemId);
    }

    /**
    * Returns the ivld lot master sid of this ivld lot master.
    *
    * @return the ivld lot master sid of this ivld lot master
    */
    @Override
    public int getIvldLotMasterSid() {
        return _ivldLotMaster.getIvldLotMasterSid();
    }

    /**
    * Sets the ivld lot master sid of this ivld lot master.
    *
    * @param ivldLotMasterSid the ivld lot master sid of this ivld lot master
    */
    @Override
    public void setIvldLotMasterSid(int ivldLotMasterSid) {
        _ivldLotMaster.setIvldLotMasterSid(ivldLotMasterSid);
    }

    /**
    * Returns the modified date of this ivld lot master.
    *
    * @return the modified date of this ivld lot master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldLotMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld lot master.
    *
    * @param modifiedDate the modified date of this ivld lot master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldLotMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this ivld lot master.
    *
    * @return the created by of this ivld lot master
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldLotMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld lot master.
    *
    * @param createdBy the created by of this ivld lot master
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldLotMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld lot master.
    *
    * @return the created date of this ivld lot master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldLotMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld lot master.
    *
    * @param createdDate the created date of this ivld lot master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldLotMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld lot master.
    *
    * @return the source of this ivld lot master
    */
    @Override
    public java.lang.String getSource() {
        return _ivldLotMaster.getSource();
    }

    /**
    * Sets the source of this ivld lot master.
    *
    * @param source the source of this ivld lot master
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldLotMaster.setSource(source);
    }

    /**
    * Returns the last lot sold date of this ivld lot master.
    *
    * @return the last lot sold date of this ivld lot master
    */
    @Override
    public java.lang.String getLastLotSoldDate() {
        return _ivldLotMaster.getLastLotSoldDate();
    }

    /**
    * Sets the last lot sold date of this ivld lot master.
    *
    * @param lastLotSoldDate the last lot sold date of this ivld lot master
    */
    @Override
    public void setLastLotSoldDate(java.lang.String lastLotSoldDate) {
        _ivldLotMaster.setLastLotSoldDate(lastLotSoldDate);
    }

    /**
    * Returns the lot expiration date of this ivld lot master.
    *
    * @return the lot expiration date of this ivld lot master
    */
    @Override
    public java.lang.String getLotExpirationDate() {
        return _ivldLotMaster.getLotExpirationDate();
    }

    /**
    * Sets the lot expiration date of this ivld lot master.
    *
    * @param lotExpirationDate the lot expiration date of this ivld lot master
    */
    @Override
    public void setLotExpirationDate(java.lang.String lotExpirationDate) {
        _ivldLotMaster.setLotExpirationDate(lotExpirationDate);
    }

    /**
    * Returns the batch ID of this ivld lot master.
    *
    * @return the batch ID of this ivld lot master
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldLotMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld lot master.
    *
    * @param batchId the batch ID of this ivld lot master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldLotMaster.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this ivld lot master.
    *
    * @return the add chg del indicator of this ivld lot master
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldLotMaster.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld lot master.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld lot master
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldLotMaster.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error field of this ivld lot master.
    *
    * @return the error field of this ivld lot master
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldLotMaster.getErrorField();
    }

    /**
    * Sets the error field of this ivld lot master.
    *
    * @param errorField the error field of this ivld lot master
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldLotMaster.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld lot master.
    *
    * @return the error code of this ivld lot master
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldLotMaster.getErrorCode();
    }

    /**
    * Sets the error code of this ivld lot master.
    *
    * @param errorCode the error code of this ivld lot master
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldLotMaster.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld lot master.
    *
    * @return the intf inserted date of this ivld lot master
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldLotMaster.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld lot master.
    *
    * @param intfInsertedDate the intf inserted date of this ivld lot master
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldLotMaster.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld lot master.
    *
    * @return the modified by of this ivld lot master
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldLotMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld lot master.
    *
    * @param modifiedBy the modified by of this ivld lot master
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldLotMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the lot no of this ivld lot master.
    *
    * @return the lot no of this ivld lot master
    */
    @Override
    public java.lang.String getLotNo() {
        return _ivldLotMaster.getLotNo();
    }

    /**
    * Sets the lot no of this ivld lot master.
    *
    * @param lotNo the lot no of this ivld lot master
    */
    @Override
    public void setLotNo(java.lang.String lotNo) {
        _ivldLotMaster.setLotNo(lotNo);
    }

    /**
    * Returns the reprocessed flag of this ivld lot master.
    *
    * @return the reprocessed flag of this ivld lot master
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldLotMaster.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld lot master.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld lot master
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldLotMaster.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the lot master intfid of this ivld lot master.
    *
    * @return the lot master intfid of this ivld lot master
    */
    @Override
    public java.lang.String getLotMasterIntfid() {
        return _ivldLotMaster.getLotMasterIntfid();
    }

    /**
    * Sets the lot master intfid of this ivld lot master.
    *
    * @param lotMasterIntfid the lot master intfid of this ivld lot master
    */
    @Override
    public void setLotMasterIntfid(java.lang.String lotMasterIntfid) {
        _ivldLotMaster.setLotMasterIntfid(lotMasterIntfid);
    }

    /**
    * Returns the check record of this ivld lot master.
    *
    * @return the check record of this ivld lot master
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldLotMaster.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld lot master is check record.
    *
    * @return <code>true</code> if this ivld lot master is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldLotMaster.isCheckRecord();
    }

    /**
    * Sets whether this ivld lot master is check record.
    *
    * @param checkRecord the check record of this ivld lot master
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldLotMaster.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldLotMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldLotMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldLotMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldLotMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldLotMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldLotMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldLotMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldLotMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldLotMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldLotMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldLotMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldLotMasterWrapper((IvldLotMaster) _ivldLotMaster.clone());
    }

    @Override
    public int compareTo(IvldLotMaster ivldLotMaster) {
        return _ivldLotMaster.compareTo(ivldLotMaster);
    }

    @Override
    public int hashCode() {
        return _ivldLotMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldLotMaster> toCacheModel() {
        return _ivldLotMaster.toCacheModel();
    }

    @Override
    public IvldLotMaster toEscapedModel() {
        return new IvldLotMasterWrapper(_ivldLotMaster.toEscapedModel());
    }

    @Override
    public IvldLotMaster toUnescapedModel() {
        return new IvldLotMasterWrapper(_ivldLotMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldLotMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldLotMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldLotMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldLotMasterWrapper)) {
            return false;
        }

        IvldLotMasterWrapper ivldLotMasterWrapper = (IvldLotMasterWrapper) obj;

        if (Validator.equals(_ivldLotMaster, ivldLotMasterWrapper._ivldLotMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldLotMaster getWrappedIvldLotMaster() {
        return _ivldLotMaster;
    }

    @Override
    public IvldLotMaster getWrappedModel() {
        return _ivldLotMaster;
    }

    @Override
    public void resetOriginalValues() {
        _ivldLotMaster.resetOriginalValues();
    }
}
