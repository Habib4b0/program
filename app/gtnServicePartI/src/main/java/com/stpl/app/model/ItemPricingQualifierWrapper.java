package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemPricingQualifier}.
 * </p>
 *
 * @author
 * @see ItemPricingQualifier
 * @generated
 */
public class ItemPricingQualifierWrapper implements ItemPricingQualifier,
    ModelWrapper<ItemPricingQualifier> {
    private ItemPricingQualifier _itemPricingQualifier;

    public ItemPricingQualifierWrapper(
        ItemPricingQualifier itemPricingQualifier) {
        _itemPricingQualifier = itemPricingQualifier;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemPricingQualifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemPricingQualifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("specificEntityCode", getSpecificEntityCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("effectiveDates", getEffectiveDates());
        attributes.put("notes", getNotes());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("pricingQualifier", getPricingQualifier());
        attributes.put("itemPricingQualifierName", getItemPricingQualifierName());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        String specificEntityCode = (String) attributes.get(
                "specificEntityCode");

        if (specificEntityCode != null) {
            setSpecificEntityCode(specificEntityCode);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String pricingQualifier = (String) attributes.get("pricingQualifier");

        if (pricingQualifier != null) {
            setPricingQualifier(pricingQualifier);
        }

        String itemPricingQualifierName = (String) attributes.get(
                "itemPricingQualifierName");

        if (itemPricingQualifierName != null) {
            setItemPricingQualifierName(itemPricingQualifierName);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this item pricing qualifier.
    *
    * @return the primary key of this item pricing qualifier
    */
    @Override
    public int getPrimaryKey() {
        return _itemPricingQualifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item pricing qualifier.
    *
    * @param primaryKey the primary key of this item pricing qualifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemPricingQualifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this item pricing qualifier.
    *
    * @return the created by of this item pricing qualifier
    */
    @Override
    public int getCreatedBy() {
        return _itemPricingQualifier.getCreatedBy();
    }

    /**
    * Sets the created by of this item pricing qualifier.
    *
    * @param createdBy the created by of this item pricing qualifier
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemPricingQualifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the item pricing qualifier sid of this item pricing qualifier.
    *
    * @return the item pricing qualifier sid of this item pricing qualifier
    */
    @Override
    public int getItemPricingQualifierSid() {
        return _itemPricingQualifier.getItemPricingQualifierSid();
    }

    /**
    * Sets the item pricing qualifier sid of this item pricing qualifier.
    *
    * @param itemPricingQualifierSid the item pricing qualifier sid of this item pricing qualifier
    */
    @Override
    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifier.setItemPricingQualifierSid(itemPricingQualifierSid);
    }

    /**
    * Returns the specific entity code of this item pricing qualifier.
    *
    * @return the specific entity code of this item pricing qualifier
    */
    @Override
    public java.lang.String getSpecificEntityCode() {
        return _itemPricingQualifier.getSpecificEntityCode();
    }

    /**
    * Sets the specific entity code of this item pricing qualifier.
    *
    * @param specificEntityCode the specific entity code of this item pricing qualifier
    */
    @Override
    public void setSpecificEntityCode(java.lang.String specificEntityCode) {
        _itemPricingQualifier.setSpecificEntityCode(specificEntityCode);
    }

    /**
    * Returns the modified by of this item pricing qualifier.
    *
    * @return the modified by of this item pricing qualifier
    */
    @Override
    public int getModifiedBy() {
        return _itemPricingQualifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this item pricing qualifier.
    *
    * @param modifiedBy the modified by of this item pricing qualifier
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemPricingQualifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this item pricing qualifier.
    *
    * @return the created date of this item pricing qualifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemPricingQualifier.getCreatedDate();
    }

    /**
    * Sets the created date of this item pricing qualifier.
    *
    * @param createdDate the created date of this item pricing qualifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemPricingQualifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the batch ID of this item pricing qualifier.
    *
    * @return the batch ID of this item pricing qualifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _itemPricingQualifier.getBatchId();
    }

    /**
    * Sets the batch ID of this item pricing qualifier.
    *
    * @param batchId the batch ID of this item pricing qualifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _itemPricingQualifier.setBatchId(batchId);
    }

    /**
    * Returns the modified date of this item pricing qualifier.
    *
    * @return the modified date of this item pricing qualifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemPricingQualifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this item pricing qualifier.
    *
    * @param modifiedDate the modified date of this item pricing qualifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemPricingQualifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the effective dates of this item pricing qualifier.
    *
    * @return the effective dates of this item pricing qualifier
    */
    @Override
    public java.lang.String getEffectiveDates() {
        return _itemPricingQualifier.getEffectiveDates();
    }

    /**
    * Sets the effective dates of this item pricing qualifier.
    *
    * @param effectiveDates the effective dates of this item pricing qualifier
    */
    @Override
    public void setEffectiveDates(java.lang.String effectiveDates) {
        _itemPricingQualifier.setEffectiveDates(effectiveDates);
    }

    /**
    * Returns the notes of this item pricing qualifier.
    *
    * @return the notes of this item pricing qualifier
    */
    @Override
    public java.lang.String getNotes() {
        return _itemPricingQualifier.getNotes();
    }

    /**
    * Sets the notes of this item pricing qualifier.
    *
    * @param notes the notes of this item pricing qualifier
    */
    @Override
    public void setNotes(java.lang.String notes) {
        _itemPricingQualifier.setNotes(notes);
    }

    /**
    * Returns the record lock status of this item pricing qualifier.
    *
    * @return the record lock status of this item pricing qualifier
    */
    @Override
    public boolean getRecordLockStatus() {
        return _itemPricingQualifier.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this item pricing qualifier is record lock status.
    *
    * @return <code>true</code> if this item pricing qualifier is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _itemPricingQualifier.isRecordLockStatus();
    }

    /**
    * Sets whether this item pricing qualifier is record lock status.
    *
    * @param recordLockStatus the record lock status of this item pricing qualifier
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _itemPricingQualifier.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the source of this item pricing qualifier.
    *
    * @return the source of this item pricing qualifier
    */
    @Override
    public java.lang.String getSource() {
        return _itemPricingQualifier.getSource();
    }

    /**
    * Sets the source of this item pricing qualifier.
    *
    * @param source the source of this item pricing qualifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _itemPricingQualifier.setSource(source);
    }

    /**
    * Returns the pricing qualifier of this item pricing qualifier.
    *
    * @return the pricing qualifier of this item pricing qualifier
    */
    @Override
    public java.lang.String getPricingQualifier() {
        return _itemPricingQualifier.getPricingQualifier();
    }

    /**
    * Sets the pricing qualifier of this item pricing qualifier.
    *
    * @param pricingQualifier the pricing qualifier of this item pricing qualifier
    */
    @Override
    public void setPricingQualifier(java.lang.String pricingQualifier) {
        _itemPricingQualifier.setPricingQualifier(pricingQualifier);
    }

    /**
    * Returns the item pricing qualifier name of this item pricing qualifier.
    *
    * @return the item pricing qualifier name of this item pricing qualifier
    */
    @Override
    public java.lang.String getItemPricingQualifierName() {
        return _itemPricingQualifier.getItemPricingQualifierName();
    }

    /**
    * Sets the item pricing qualifier name of this item pricing qualifier.
    *
    * @param itemPricingQualifierName the item pricing qualifier name of this item pricing qualifier
    */
    @Override
    public void setItemPricingQualifierName(
        java.lang.String itemPricingQualifierName) {
        _itemPricingQualifier.setItemPricingQualifierName(itemPricingQualifierName);
    }

    /**
    * Returns the inbound status of this item pricing qualifier.
    *
    * @return the inbound status of this item pricing qualifier
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _itemPricingQualifier.getInboundStatus();
    }

    /**
    * Sets the inbound status of this item pricing qualifier.
    *
    * @param inboundStatus the inbound status of this item pricing qualifier
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _itemPricingQualifier.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _itemPricingQualifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemPricingQualifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemPricingQualifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemPricingQualifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemPricingQualifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemPricingQualifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemPricingQualifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemPricingQualifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemPricingQualifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemPricingQualifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemPricingQualifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemPricingQualifierWrapper((ItemPricingQualifier) _itemPricingQualifier.clone());
    }

    @Override
    public int compareTo(ItemPricingQualifier itemPricingQualifier) {
        return _itemPricingQualifier.compareTo(itemPricingQualifier);
    }

    @Override
    public int hashCode() {
        return _itemPricingQualifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemPricingQualifier> toCacheModel() {
        return _itemPricingQualifier.toCacheModel();
    }

    @Override
    public ItemPricingQualifier toEscapedModel() {
        return new ItemPricingQualifierWrapper(_itemPricingQualifier.toEscapedModel());
    }

    @Override
    public ItemPricingQualifier toUnescapedModel() {
        return new ItemPricingQualifierWrapper(_itemPricingQualifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemPricingQualifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemPricingQualifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemPricingQualifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemPricingQualifierWrapper)) {
            return false;
        }

        ItemPricingQualifierWrapper itemPricingQualifierWrapper = (ItemPricingQualifierWrapper) obj;

        if (Validator.equals(_itemPricingQualifier,
                    itemPricingQualifierWrapper._itemPricingQualifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemPricingQualifier getWrappedItemPricingQualifier() {
        return _itemPricingQualifier;
    }

    @Override
    public ItemPricingQualifier getWrappedModel() {
        return _itemPricingQualifier;
    }

    @Override
    public void resetOriginalValues() {
        _itemPricingQualifier.resetOriginalValues();
    }
}
