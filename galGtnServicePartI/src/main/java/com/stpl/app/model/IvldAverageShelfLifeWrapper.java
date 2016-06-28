package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldAverageShelfLife}.
 * </p>
 *
 * @author
 * @see IvldAverageShelfLife
 * @generated
 */
public class IvldAverageShelfLifeWrapper implements IvldAverageShelfLife,
    ModelWrapper<IvldAverageShelfLife> {
    private IvldAverageShelfLife _ivldAverageShelfLife;

    public IvldAverageShelfLifeWrapper(
        IvldAverageShelfLife ivldAverageShelfLife) {
        _ivldAverageShelfLife = ivldAverageShelfLife;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldAverageShelfLife.class;
    }

    @Override
    public String getModelClassName() {
        return IvldAverageShelfLife.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("ivldAverageShelfLifeSid", getIvldAverageShelfLifeSid());
        attributes.put("itemId", getItemId());
        attributes.put("avgShelfLife", getAvgShelfLife());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("itemIdType", getItemIdType());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("averageShelfLifeIntfid", getAverageShelfLifeIntfid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        Integer ivldAverageShelfLifeSid = (Integer) attributes.get(
                "ivldAverageShelfLifeSid");

        if (ivldAverageShelfLifeSid != null) {
            setIvldAverageShelfLifeSid(ivldAverageShelfLifeSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String avgShelfLife = (String) attributes.get("avgShelfLife");

        if (avgShelfLife != null) {
            setAvgShelfLife(avgShelfLife);
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

        String itemIdType = (String) attributes.get("itemIdType");

        if (itemIdType != null) {
            setItemIdType(itemIdType);
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

        String averageShelfLifeIntfid = (String) attributes.get(
                "averageShelfLifeIntfid");

        if (averageShelfLifeIntfid != null) {
            setAverageShelfLifeIntfid(averageShelfLifeIntfid);
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

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }
    }

    /**
    * Returns the primary key of this ivld average shelf life.
    *
    * @return the primary key of this ivld average shelf life
    */
    @Override
    public int getPrimaryKey() {
        return _ivldAverageShelfLife.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld average shelf life.
    *
    * @param primaryKey the primary key of this ivld average shelf life
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldAverageShelfLife.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the reason for failure of this ivld average shelf life.
    *
    * @return the reason for failure of this ivld average shelf life
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldAverageShelfLife.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld average shelf life.
    *
    * @param reasonForFailure the reason for failure of this ivld average shelf life
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldAverageShelfLife.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the ivld average shelf life sid of this ivld average shelf life.
    *
    * @return the ivld average shelf life sid of this ivld average shelf life
    */
    @Override
    public int getIvldAverageShelfLifeSid() {
        return _ivldAverageShelfLife.getIvldAverageShelfLifeSid();
    }

    /**
    * Sets the ivld average shelf life sid of this ivld average shelf life.
    *
    * @param ivldAverageShelfLifeSid the ivld average shelf life sid of this ivld average shelf life
    */
    @Override
    public void setIvldAverageShelfLifeSid(int ivldAverageShelfLifeSid) {
        _ivldAverageShelfLife.setIvldAverageShelfLifeSid(ivldAverageShelfLifeSid);
    }

    /**
    * Returns the item ID of this ivld average shelf life.
    *
    * @return the item ID of this ivld average shelf life
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldAverageShelfLife.getItemId();
    }

    /**
    * Sets the item ID of this ivld average shelf life.
    *
    * @param itemId the item ID of this ivld average shelf life
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldAverageShelfLife.setItemId(itemId);
    }

    /**
    * Returns the avg shelf life of this ivld average shelf life.
    *
    * @return the avg shelf life of this ivld average shelf life
    */
    @Override
    public java.lang.String getAvgShelfLife() {
        return _ivldAverageShelfLife.getAvgShelfLife();
    }

    /**
    * Sets the avg shelf life of this ivld average shelf life.
    *
    * @param avgShelfLife the avg shelf life of this ivld average shelf life
    */
    @Override
    public void setAvgShelfLife(java.lang.String avgShelfLife) {
        _ivldAverageShelfLife.setAvgShelfLife(avgShelfLife);
    }

    /**
    * Returns the modified date of this ivld average shelf life.
    *
    * @return the modified date of this ivld average shelf life
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldAverageShelfLife.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld average shelf life.
    *
    * @param modifiedDate the modified date of this ivld average shelf life
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldAverageShelfLife.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this ivld average shelf life.
    *
    * @return the created by of this ivld average shelf life
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldAverageShelfLife.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld average shelf life.
    *
    * @param createdBy the created by of this ivld average shelf life
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldAverageShelfLife.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld average shelf life.
    *
    * @return the created date of this ivld average shelf life
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldAverageShelfLife.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld average shelf life.
    *
    * @param createdDate the created date of this ivld average shelf life
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldAverageShelfLife.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld average shelf life.
    *
    * @return the source of this ivld average shelf life
    */
    @Override
    public java.lang.String getSource() {
        return _ivldAverageShelfLife.getSource();
    }

    /**
    * Sets the source of this ivld average shelf life.
    *
    * @param source the source of this ivld average shelf life
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldAverageShelfLife.setSource(source);
    }

    /**
    * Returns the item ID type of this ivld average shelf life.
    *
    * @return the item ID type of this ivld average shelf life
    */
    @Override
    public java.lang.String getItemIdType() {
        return _ivldAverageShelfLife.getItemIdType();
    }

    /**
    * Sets the item ID type of this ivld average shelf life.
    *
    * @param itemIdType the item ID type of this ivld average shelf life
    */
    @Override
    public void setItemIdType(java.lang.String itemIdType) {
        _ivldAverageShelfLife.setItemIdType(itemIdType);
    }

    /**
    * Returns the batch ID of this ivld average shelf life.
    *
    * @return the batch ID of this ivld average shelf life
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldAverageShelfLife.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld average shelf life.
    *
    * @param batchId the batch ID of this ivld average shelf life
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldAverageShelfLife.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this ivld average shelf life.
    *
    * @return the add chg del indicator of this ivld average shelf life
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldAverageShelfLife.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld average shelf life.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld average shelf life
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldAverageShelfLife.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the average shelf life intfid of this ivld average shelf life.
    *
    * @return the average shelf life intfid of this ivld average shelf life
    */
    @Override
    public java.lang.String getAverageShelfLifeIntfid() {
        return _ivldAverageShelfLife.getAverageShelfLifeIntfid();
    }

    /**
    * Sets the average shelf life intfid of this ivld average shelf life.
    *
    * @param averageShelfLifeIntfid the average shelf life intfid of this ivld average shelf life
    */
    @Override
    public void setAverageShelfLifeIntfid(
        java.lang.String averageShelfLifeIntfid) {
        _ivldAverageShelfLife.setAverageShelfLifeIntfid(averageShelfLifeIntfid);
    }

    /**
    * Returns the error field of this ivld average shelf life.
    *
    * @return the error field of this ivld average shelf life
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldAverageShelfLife.getErrorField();
    }

    /**
    * Sets the error field of this ivld average shelf life.
    *
    * @param errorField the error field of this ivld average shelf life
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldAverageShelfLife.setErrorField(errorField);
    }

    /**
    * Returns the error code of this ivld average shelf life.
    *
    * @return the error code of this ivld average shelf life
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldAverageShelfLife.getErrorCode();
    }

    /**
    * Sets the error code of this ivld average shelf life.
    *
    * @param errorCode the error code of this ivld average shelf life
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldAverageShelfLife.setErrorCode(errorCode);
    }

    /**
    * Returns the intf inserted date of this ivld average shelf life.
    *
    * @return the intf inserted date of this ivld average shelf life
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldAverageShelfLife.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld average shelf life.
    *
    * @param intfInsertedDate the intf inserted date of this ivld average shelf life
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldAverageShelfLife.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld average shelf life.
    *
    * @return the modified by of this ivld average shelf life
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldAverageShelfLife.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld average shelf life.
    *
    * @param modifiedBy the modified by of this ivld average shelf life
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldAverageShelfLife.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld average shelf life.
    *
    * @return the reprocessed flag of this ivld average shelf life
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldAverageShelfLife.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld average shelf life.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld average shelf life
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldAverageShelfLife.setReprocessedFlag(reprocessedFlag);
    }

    @Override
    public boolean isNew() {
        return _ivldAverageShelfLife.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldAverageShelfLife.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldAverageShelfLife.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldAverageShelfLife.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldAverageShelfLife.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldAverageShelfLife.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldAverageShelfLife.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldAverageShelfLife.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldAverageShelfLife.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldAverageShelfLife.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldAverageShelfLife.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldAverageShelfLifeWrapper((IvldAverageShelfLife) _ivldAverageShelfLife.clone());
    }

    @Override
    public int compareTo(IvldAverageShelfLife ivldAverageShelfLife) {
        return _ivldAverageShelfLife.compareTo(ivldAverageShelfLife);
    }

    @Override
    public int hashCode() {
        return _ivldAverageShelfLife.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldAverageShelfLife> toCacheModel() {
        return _ivldAverageShelfLife.toCacheModel();
    }

    @Override
    public IvldAverageShelfLife toEscapedModel() {
        return new IvldAverageShelfLifeWrapper(_ivldAverageShelfLife.toEscapedModel());
    }

    @Override
    public IvldAverageShelfLife toUnescapedModel() {
        return new IvldAverageShelfLifeWrapper(_ivldAverageShelfLife.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldAverageShelfLife.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldAverageShelfLife.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldAverageShelfLife.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldAverageShelfLifeWrapper)) {
            return false;
        }

        IvldAverageShelfLifeWrapper ivldAverageShelfLifeWrapper = (IvldAverageShelfLifeWrapper) obj;

        if (Validator.equals(_ivldAverageShelfLife,
                    ivldAverageShelfLifeWrapper._ivldAverageShelfLife)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldAverageShelfLife getWrappedIvldAverageShelfLife() {
        return _ivldAverageShelfLife;
    }

    @Override
    public IvldAverageShelfLife getWrappedModel() {
        return _ivldAverageShelfLife;
    }

    @Override
    public void resetOriginalValues() {
        _ivldAverageShelfLife.resetOriginalValues();
    }
}
