package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemPricing}.
 * </p>
 *
 * @author
 * @see ItemPricing
 * @generated
 */
public class ItemPricingWrapper implements ItemPricing,
    ModelWrapper<ItemPricing> {
    private ItemPricing _itemPricing;

    public ItemPricingWrapper(ItemPricing itemPricing) {
        _itemPricing = itemPricing;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return ItemPricing.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("entityCode", getEntityCode());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemPricingSid", getItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        Double itemPrice = (Double) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer itemUom = (Integer) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemPricingSid = (Integer) attributes.get("itemPricingSid");

        if (itemPricingSid != null) {
            setItemPricingSid(itemPricingSid);
        }

        Integer pricingCodeStatus = (Integer) attributes.get(
                "pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }
    }

    /**
    * Returns the primary key of this item pricing.
    *
    * @return the primary key of this item pricing
    */
    @Override
    public int getPrimaryKey() {
        return _itemPricing.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item pricing.
    *
    * @param primaryKey the primary key of this item pricing
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemPricing.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item master sid of this item pricing.
    *
    * @return the item master sid of this item pricing
    */
    @Override
    public int getItemMasterSid() {
        return _itemPricing.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this item pricing.
    *
    * @param itemMasterSid the item master sid of this item pricing
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemPricing.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the item pricing qualifier sid of this item pricing.
    *
    * @return the item pricing qualifier sid of this item pricing
    */
    @Override
    public int getItemPricingQualifierSid() {
        return _itemPricing.getItemPricingQualifierSid();
    }

    /**
    * Sets the item pricing qualifier sid of this item pricing.
    *
    * @param itemPricingQualifierSid the item pricing qualifier sid of this item pricing
    */
    @Override
    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricing.setItemPricingQualifierSid(itemPricingQualifierSid);
    }

    /**
    * Returns the item price of this item pricing.
    *
    * @return the item price of this item pricing
    */
    @Override
    public double getItemPrice() {
        return _itemPricing.getItemPrice();
    }

    /**
    * Sets the item price of this item pricing.
    *
    * @param itemPrice the item price of this item pricing
    */
    @Override
    public void setItemPrice(double itemPrice) {
        _itemPricing.setItemPrice(itemPrice);
    }

    /**
    * Returns the end date of this item pricing.
    *
    * @return the end date of this item pricing
    */
    @Override
    public java.util.Date getEndDate() {
        return _itemPricing.getEndDate();
    }

    /**
    * Sets the end date of this item pricing.
    *
    * @param endDate the end date of this item pricing
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _itemPricing.setEndDate(endDate);
    }

    /**
    * Returns the modified date of this item pricing.
    *
    * @return the modified date of this item pricing
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemPricing.getModifiedDate();
    }

    /**
    * Sets the modified date of this item pricing.
    *
    * @param modifiedDate the modified date of this item pricing
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemPricing.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the entity code of this item pricing.
    *
    * @return the entity code of this item pricing
    */
    @Override
    public java.lang.String getEntityCode() {
        return _itemPricing.getEntityCode();
    }

    /**
    * Sets the entity code of this item pricing.
    *
    * @param entityCode the entity code of this item pricing
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _itemPricing.setEntityCode(entityCode);
    }

    /**
    * Returns the record lock status of this item pricing.
    *
    * @return the record lock status of this item pricing
    */
    @Override
    public boolean getRecordLockStatus() {
        return _itemPricing.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this item pricing is record lock status.
    *
    * @return <code>true</code> if this item pricing is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _itemPricing.isRecordLockStatus();
    }

    /**
    * Sets whether this item pricing is record lock status.
    *
    * @param recordLockStatus the record lock status of this item pricing
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _itemPricing.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the start date of this item pricing.
    *
    * @return the start date of this item pricing
    */
    @Override
    public java.util.Date getStartDate() {
        return _itemPricing.getStartDate();
    }

    /**
    * Sets the start date of this item pricing.
    *
    * @param startDate the start date of this item pricing
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _itemPricing.setStartDate(startDate);
    }

    /**
    * Returns the created date of this item pricing.
    *
    * @return the created date of this item pricing
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemPricing.getCreatedDate();
    }

    /**
    * Sets the created date of this item pricing.
    *
    * @param createdDate the created date of this item pricing
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemPricing.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this item pricing.
    *
    * @return the created by of this item pricing
    */
    @Override
    public int getCreatedBy() {
        return _itemPricing.getCreatedBy();
    }

    /**
    * Sets the created by of this item pricing.
    *
    * @param createdBy the created by of this item pricing
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemPricing.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this item pricing.
    *
    * @return the source of this item pricing
    */
    @Override
    public java.lang.String getSource() {
        return _itemPricing.getSource();
    }

    /**
    * Sets the source of this item pricing.
    *
    * @param source the source of this item pricing
    */
    @Override
    public void setSource(java.lang.String source) {
        _itemPricing.setSource(source);
    }

    /**
    * Returns the batch ID of this item pricing.
    *
    * @return the batch ID of this item pricing
    */
    @Override
    public java.lang.String getBatchId() {
        return _itemPricing.getBatchId();
    }

    /**
    * Sets the batch ID of this item pricing.
    *
    * @param batchId the batch ID of this item pricing
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _itemPricing.setBatchId(batchId);
    }

    /**
    * Returns the item uom of this item pricing.
    *
    * @return the item uom of this item pricing
    */
    @Override
    public int getItemUom() {
        return _itemPricing.getItemUom();
    }

    /**
    * Sets the item uom of this item pricing.
    *
    * @param itemUom the item uom of this item pricing
    */
    @Override
    public void setItemUom(int itemUom) {
        _itemPricing.setItemUom(itemUom);
    }

    /**
    * Returns the modified by of this item pricing.
    *
    * @return the modified by of this item pricing
    */
    @Override
    public int getModifiedBy() {
        return _itemPricing.getModifiedBy();
    }

    /**
    * Sets the modified by of this item pricing.
    *
    * @param modifiedBy the modified by of this item pricing
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemPricing.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this item pricing.
    *
    * @return the inbound status of this item pricing
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _itemPricing.getInboundStatus();
    }

    /**
    * Sets the inbound status of this item pricing.
    *
    * @param inboundStatus the inbound status of this item pricing
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _itemPricing.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the item pricing sid of this item pricing.
    *
    * @return the item pricing sid of this item pricing
    */
    @Override
    public int getItemPricingSid() {
        return _itemPricing.getItemPricingSid();
    }

    /**
    * Sets the item pricing sid of this item pricing.
    *
    * @param itemPricingSid the item pricing sid of this item pricing
    */
    @Override
    public void setItemPricingSid(int itemPricingSid) {
        _itemPricing.setItemPricingSid(itemPricingSid);
    }

    /**
    * Returns the pricing code status of this item pricing.
    *
    * @return the pricing code status of this item pricing
    */
    @Override
    public int getPricingCodeStatus() {
        return _itemPricing.getPricingCodeStatus();
    }

    /**
    * Sets the pricing code status of this item pricing.
    *
    * @param pricingCodeStatus the pricing code status of this item pricing
    */
    @Override
    public void setPricingCodeStatus(int pricingCodeStatus) {
        _itemPricing.setPricingCodeStatus(pricingCodeStatus);
    }

    @Override
    public boolean isNew() {
        return _itemPricing.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemPricing.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemPricing.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemPricing.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemPricing.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemPricing.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemPricing.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemPricing.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemPricing.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemPricing.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemPricing.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemPricingWrapper((ItemPricing) _itemPricing.clone());
    }

    @Override
    public int compareTo(ItemPricing itemPricing) {
        return _itemPricing.compareTo(itemPricing);
    }

    @Override
    public int hashCode() {
        return _itemPricing.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemPricing> toCacheModel() {
        return _itemPricing.toCacheModel();
    }

    @Override
    public ItemPricing toEscapedModel() {
        return new ItemPricingWrapper(_itemPricing.toEscapedModel());
    }

    @Override
    public ItemPricing toUnescapedModel() {
        return new ItemPricingWrapper(_itemPricing.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemPricing.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemPricing.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemPricing.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemPricingWrapper)) {
            return false;
        }

        ItemPricingWrapper itemPricingWrapper = (ItemPricingWrapper) obj;

        if (Validator.equals(_itemPricing, itemPricingWrapper._itemPricing)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemPricing getWrappedItemPricing() {
        return _itemPricing;
    }

    @Override
    public ItemPricing getWrappedModel() {
        return _itemPricing;
    }

    @Override
    public void resetOriginalValues() {
        _itemPricing.resetOriginalValues();
    }
}
