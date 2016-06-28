package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemIdentifier}.
 * </p>
 *
 * @author
 * @see ItemIdentifier
 * @generated
 */
public class ItemIdentifierWrapper implements ItemIdentifier,
    ModelWrapper<ItemIdentifier> {
    private ItemIdentifier _itemIdentifier;

    public ItemIdentifierWrapper(ItemIdentifier itemIdentifier) {
        _itemIdentifier = itemIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemIdentifierSid", getItemIdentifierSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemIdentifierValue", getItemIdentifierValue());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemQualifierSid", getItemQualifierSid());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemIdentifierSid = (Integer) attributes.get(
                "itemIdentifierSid");

        if (itemIdentifierSid != null) {
            setItemIdentifierSid(itemIdentifierSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer identifierStatus = (Integer) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemIdentifierValue = (String) attributes.get(
                "itemIdentifierValue");

        if (itemIdentifierValue != null) {
            setItemIdentifierValue(itemIdentifierValue);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer itemQualifierSid = (Integer) attributes.get("itemQualifierSid");

        if (itemQualifierSid != null) {
            setItemQualifierSid(itemQualifierSid);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
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

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this item identifier.
    *
    * @return the primary key of this item identifier
    */
    @Override
    public int getPrimaryKey() {
        return _itemIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item identifier.
    *
    * @param primaryKey the primary key of this item identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item identifier sid of this item identifier.
    *
    * @return the item identifier sid of this item identifier
    */
    @Override
    public int getItemIdentifierSid() {
        return _itemIdentifier.getItemIdentifierSid();
    }

    /**
    * Sets the item identifier sid of this item identifier.
    *
    * @param itemIdentifierSid the item identifier sid of this item identifier
    */
    @Override
    public void setItemIdentifierSid(int itemIdentifierSid) {
        _itemIdentifier.setItemIdentifierSid(itemIdentifierSid);
    }

    /**
    * Returns the item master sid of this item identifier.
    *
    * @return the item master sid of this item identifier
    */
    @Override
    public int getItemMasterSid() {
        return _itemIdentifier.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this item identifier.
    *
    * @param itemMasterSid the item master sid of this item identifier
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemIdentifier.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the end date of this item identifier.
    *
    * @return the end date of this item identifier
    */
    @Override
    public java.util.Date getEndDate() {
        return _itemIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this item identifier.
    *
    * @param endDate the end date of this item identifier
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _itemIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the modified date of this item identifier.
    *
    * @return the modified date of this item identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this item identifier.
    *
    * @param modifiedDate the modified date of this item identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the identifier status of this item identifier.
    *
    * @return the identifier status of this item identifier
    */
    @Override
    public int getIdentifierStatus() {
        return _itemIdentifier.getIdentifierStatus();
    }

    /**
    * Sets the identifier status of this item identifier.
    *
    * @param identifierStatus the identifier status of this item identifier
    */
    @Override
    public void setIdentifierStatus(int identifierStatus) {
        _itemIdentifier.setIdentifierStatus(identifierStatus);
    }

    /**
    * Returns the entity code of this item identifier.
    *
    * @return the entity code of this item identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _itemIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this item identifier.
    *
    * @param entityCode the entity code of this item identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _itemIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the item identifier value of this item identifier.
    *
    * @return the item identifier value of this item identifier
    */
    @Override
    public java.lang.String getItemIdentifierValue() {
        return _itemIdentifier.getItemIdentifierValue();
    }

    /**
    * Sets the item identifier value of this item identifier.
    *
    * @param itemIdentifierValue the item identifier value of this item identifier
    */
    @Override
    public void setItemIdentifierValue(java.lang.String itemIdentifierValue) {
        _itemIdentifier.setItemIdentifierValue(itemIdentifierValue);
    }

    /**
    * Returns the record lock status of this item identifier.
    *
    * @return the record lock status of this item identifier
    */
    @Override
    public boolean getRecordLockStatus() {
        return _itemIdentifier.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this item identifier is record lock status.
    *
    * @return <code>true</code> if this item identifier is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _itemIdentifier.isRecordLockStatus();
    }

    /**
    * Sets whether this item identifier is record lock status.
    *
    * @param recordLockStatus the record lock status of this item identifier
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _itemIdentifier.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the item qualifier sid of this item identifier.
    *
    * @return the item qualifier sid of this item identifier
    */
    @Override
    public int getItemQualifierSid() {
        return _itemIdentifier.getItemQualifierSid();
    }

    /**
    * Sets the item qualifier sid of this item identifier.
    *
    * @param itemQualifierSid the item qualifier sid of this item identifier
    */
    @Override
    public void setItemQualifierSid(int itemQualifierSid) {
        _itemIdentifier.setItemQualifierSid(itemQualifierSid);
    }

    /**
    * Returns the start date of this item identifier.
    *
    * @return the start date of this item identifier
    */
    @Override
    public java.util.Date getStartDate() {
        return _itemIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this item identifier.
    *
    * @param startDate the start date of this item identifier
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _itemIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the created date of this item identifier.
    *
    * @return the created date of this item identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this item identifier.
    *
    * @param createdDate the created date of this item identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this item identifier.
    *
    * @return the source of this item identifier
    */
    @Override
    public java.lang.String getSource() {
        return _itemIdentifier.getSource();
    }

    /**
    * Sets the source of this item identifier.
    *
    * @param source the source of this item identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _itemIdentifier.setSource(source);
    }

    /**
    * Returns the created by of this item identifier.
    *
    * @return the created by of this item identifier
    */
    @Override
    public int getCreatedBy() {
        return _itemIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this item identifier.
    *
    * @param createdBy the created by of this item identifier
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the batch ID of this item identifier.
    *
    * @return the batch ID of this item identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _itemIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this item identifier.
    *
    * @param batchId the batch ID of this item identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _itemIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the modified by of this item identifier.
    *
    * @return the modified by of this item identifier
    */
    @Override
    public int getModifiedBy() {
        return _itemIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this item identifier.
    *
    * @param modifiedBy the modified by of this item identifier
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this item identifier.
    *
    * @return the inbound status of this item identifier
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _itemIdentifier.getInboundStatus();
    }

    /**
    * Sets the inbound status of this item identifier.
    *
    * @param inboundStatus the inbound status of this item identifier
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _itemIdentifier.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _itemIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemIdentifierWrapper((ItemIdentifier) _itemIdentifier.clone());
    }

    @Override
    public int compareTo(ItemIdentifier itemIdentifier) {
        return _itemIdentifier.compareTo(itemIdentifier);
    }

    @Override
    public int hashCode() {
        return _itemIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemIdentifier> toCacheModel() {
        return _itemIdentifier.toCacheModel();
    }

    @Override
    public ItemIdentifier toEscapedModel() {
        return new ItemIdentifierWrapper(_itemIdentifier.toEscapedModel());
    }

    @Override
    public ItemIdentifier toUnescapedModel() {
        return new ItemIdentifierWrapper(_itemIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemIdentifierWrapper)) {
            return false;
        }

        ItemIdentifierWrapper itemIdentifierWrapper = (ItemIdentifierWrapper) obj;

        if (Validator.equals(_itemIdentifier,
                    itemIdentifierWrapper._itemIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemIdentifier getWrappedItemIdentifier() {
        return _itemIdentifier;
    }

    @Override
    public ItemIdentifier getWrappedModel() {
        return _itemIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _itemIdentifier.resetOriginalValues();
    }
}
