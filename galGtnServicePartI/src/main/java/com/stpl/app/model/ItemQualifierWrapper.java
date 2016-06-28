package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemQualifier}.
 * </p>
 *
 * @author
 * @see ItemQualifier
 * @generated
 */
public class ItemQualifierWrapper implements ItemQualifier,
    ModelWrapper<ItemQualifier> {
    private ItemQualifier _itemQualifier;

    public ItemQualifierWrapper(ItemQualifier itemQualifier) {
        _itemQualifier = itemQualifier;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemQualifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemQualifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemQualifierSid", getItemQualifierSid());
        attributes.put("specificEntityCode", getSpecificEntityCode());
        attributes.put("itemQualifierName", getItemQualifierName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("effectiveDates", getEffectiveDates());
        attributes.put("notes", getNotes());
        attributes.put("itemQualifierValue", getItemQualifierValue());
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

        Integer itemQualifierSid = (Integer) attributes.get("itemQualifierSid");

        if (itemQualifierSid != null) {
            setItemQualifierSid(itemQualifierSid);
        }

        String specificEntityCode = (String) attributes.get(
                "specificEntityCode");

        if (specificEntityCode != null) {
            setSpecificEntityCode(specificEntityCode);
        }

        String itemQualifierName = (String) attributes.get("itemQualifierName");

        if (itemQualifierName != null) {
            setItemQualifierName(itemQualifierName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String effectiveDates = (String) attributes.get("effectiveDates");

        if (effectiveDates != null) {
            setEffectiveDates(effectiveDates);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        String itemQualifierValue = (String) attributes.get(
                "itemQualifierValue");

        if (itemQualifierValue != null) {
            setItemQualifierValue(itemQualifierValue);
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
    * Returns the primary key of this item qualifier.
    *
    * @return the primary key of this item qualifier
    */
    @Override
    public int getPrimaryKey() {
        return _itemQualifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item qualifier.
    *
    * @param primaryKey the primary key of this item qualifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemQualifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this item qualifier.
    *
    * @return the created by of this item qualifier
    */
    @Override
    public int getCreatedBy() {
        return _itemQualifier.getCreatedBy();
    }

    /**
    * Sets the created by of this item qualifier.
    *
    * @param createdBy the created by of this item qualifier
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemQualifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the item qualifier sid of this item qualifier.
    *
    * @return the item qualifier sid of this item qualifier
    */
    @Override
    public int getItemQualifierSid() {
        return _itemQualifier.getItemQualifierSid();
    }

    /**
    * Sets the item qualifier sid of this item qualifier.
    *
    * @param itemQualifierSid the item qualifier sid of this item qualifier
    */
    @Override
    public void setItemQualifierSid(int itemQualifierSid) {
        _itemQualifier.setItemQualifierSid(itemQualifierSid);
    }

    /**
    * Returns the specific entity code of this item qualifier.
    *
    * @return the specific entity code of this item qualifier
    */
    @Override
    public java.lang.String getSpecificEntityCode() {
        return _itemQualifier.getSpecificEntityCode();
    }

    /**
    * Sets the specific entity code of this item qualifier.
    *
    * @param specificEntityCode the specific entity code of this item qualifier
    */
    @Override
    public void setSpecificEntityCode(java.lang.String specificEntityCode) {
        _itemQualifier.setSpecificEntityCode(specificEntityCode);
    }

    /**
    * Returns the item qualifier name of this item qualifier.
    *
    * @return the item qualifier name of this item qualifier
    */
    @Override
    public java.lang.String getItemQualifierName() {
        return _itemQualifier.getItemQualifierName();
    }

    /**
    * Sets the item qualifier name of this item qualifier.
    *
    * @param itemQualifierName the item qualifier name of this item qualifier
    */
    @Override
    public void setItemQualifierName(java.lang.String itemQualifierName) {
        _itemQualifier.setItemQualifierName(itemQualifierName);
    }

    /**
    * Returns the modified by of this item qualifier.
    *
    * @return the modified by of this item qualifier
    */
    @Override
    public int getModifiedBy() {
        return _itemQualifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this item qualifier.
    *
    * @param modifiedBy the modified by of this item qualifier
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemQualifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this item qualifier.
    *
    * @return the created date of this item qualifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemQualifier.getCreatedDate();
    }

    /**
    * Sets the created date of this item qualifier.
    *
    * @param createdDate the created date of this item qualifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemQualifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the batch ID of this item qualifier.
    *
    * @return the batch ID of this item qualifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _itemQualifier.getBatchId();
    }

    /**
    * Sets the batch ID of this item qualifier.
    *
    * @param batchId the batch ID of this item qualifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _itemQualifier.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this item qualifier.
    *
    * @return the modified date of this item qualifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemQualifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this item qualifier.
    *
    * @param modifiedDate the modified date of this item qualifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemQualifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the effective dates of this item qualifier.
    *
    * @return the effective dates of this item qualifier
    */
    @Override
    public java.lang.String getEffectiveDates() {
        return _itemQualifier.getEffectiveDates();
    }

    /**
    * Sets the effective dates of this item qualifier.
    *
    * @param effectiveDates the effective dates of this item qualifier
    */
    @Override
    public void setEffectiveDates(java.lang.String effectiveDates) {
        _itemQualifier.setEffectiveDates(effectiveDates);
    }

    /**
    * Returns the notes of this item qualifier.
    *
    * @return the notes of this item qualifier
    */
    @Override
    public java.lang.String getNotes() {
        return _itemQualifier.getNotes();
    }

    /**
    * Sets the notes of this item qualifier.
    *
    * @param notes the notes of this item qualifier
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _itemQualifier.setNotes(notes);
    }

    /**
    * Returns the item qualifier value of this item qualifier.
    *
    * @return the item qualifier value of this item qualifier
    */
    @Override
    public java.lang.String getItemQualifierValue() {
        return _itemQualifier.getItemQualifierValue();
    }

    /**
    * Sets the item qualifier value of this item qualifier.
    *
    * @param itemQualifierValue the item qualifier value of this item qualifier
    */
    @Override
    public void setItemQualifierValue(java.lang.String itemQualifierValue) {
        _itemQualifier.setItemQualifierValue(itemQualifierValue);
    }

    /**
    * Returns the record lock status of this item qualifier.
    *
    * @return the record lock status of this item qualifier
    */
    @Override
    public boolean getRecordLockStatus() {
        return _itemQualifier.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this item qualifier is record lock status.
    *
    * @return <code>true</code> if this item qualifier is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _itemQualifier.isRecordLockStatus();
    }

    /**
    * Sets whether this item qualifier is record lock status.
    *
    * @param recordLockStatus the record lock status of this item qualifier
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _itemQualifier.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this item qualifier.
    *
    * @return the source of this item qualifier
    */
    @Override
    public java.lang.String getSource() {
        return _itemQualifier.getSource();
    }

    /**
    * Sets the source of this item qualifier.
    *
    * @param source the source of this item qualifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _itemQualifier.setSource(source);
    }

    /**
    * Returns the inbound status of this item qualifier.
    *
    * @return the inbound status of this item qualifier
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _itemQualifier.getInboundStatus();
    }

    /**
    * Sets the inbound status of this item qualifier.
    *
    * @param inboundStatus the inbound status of this item qualifier
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _itemQualifier.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _itemQualifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemQualifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemQualifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemQualifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemQualifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemQualifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemQualifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemQualifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemQualifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemQualifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemQualifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemQualifierWrapper((ItemQualifier) _itemQualifier.clone());
    }

    @Override
    public int compareTo(ItemQualifier itemQualifier) {
        return _itemQualifier.compareTo(itemQualifier);
    }

    @Override
    public int hashCode() {
        return _itemQualifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemQualifier> toCacheModel() {
        return _itemQualifier.toCacheModel();
    }

    @Override
    public ItemQualifier toEscapedModel() {
        return new ItemQualifierWrapper(_itemQualifier.toEscapedModel());
    }

    @Override
    public ItemQualifier toUnescapedModel() {
        return new ItemQualifierWrapper(_itemQualifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemQualifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemQualifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemQualifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemQualifierWrapper)) {
            return false;
        }

        ItemQualifierWrapper itemQualifierWrapper = (ItemQualifierWrapper) obj;

        if (Validator.equals(_itemQualifier, itemQualifierWrapper._itemQualifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemQualifier getWrappedItemQualifier() {
        return _itemQualifier;
    }

    @Override
    public ItemQualifier getWrappedModel() {
        return _itemQualifier;
    }

    @Override
    public void resetOriginalValues() {
        _itemQualifier.resetOriginalValues();
    }
}
