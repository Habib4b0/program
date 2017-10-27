package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwItemPricing}.
 * </p>
 *
 * @author
 * @see VwItemPricing
 * @generated
 */
public class VwItemPricingWrapper implements VwItemPricing,
    ModelWrapper<VwItemPricing> {
    private VwItemPricing _vwItemPricing;

    public VwItemPricingWrapper(VwItemPricing vwItemPricing) {
        _vwItemPricing = vwItemPricing;
    }

    @Override
    public Class<?> getModelClass() {
        return VwItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return VwItemPricing.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pricingCodeQualifier", getPricingCodeQualifier());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("endDate", getEndDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("entityCode", getEntityCode());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemName", getItemName());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("itemPricingSid", getItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());
        attributes.put("pricingCodeQualifierName", getPricingCodeQualifierName());
        attributes.put("itemPriceprecision", getItemPriceprecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String pricingCodeQualifier = (String) attributes.get(
                "pricingCodeQualifier");

        if (pricingCodeQualifier != null) {
            setPricingCodeQualifier(pricingCodeQualifier);
        }

        String itemPrice = (String) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer itemPricingSid = (Integer) attributes.get("itemPricingSid");

        if (itemPricingSid != null) {
            setItemPricingSid(itemPricingSid);
        }

        String pricingCodeStatus = (String) attributes.get("pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }

        String pricingCodeQualifierName = (String) attributes.get(
                "pricingCodeQualifierName");

        if (pricingCodeQualifierName != null) {
            setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        Integer itemPriceprecision = (Integer) attributes.get(
                "itemPriceprecision");

        if (itemPriceprecision != null) {
            setItemPriceprecision(itemPriceprecision);
        }
    }

    /**
    * Returns the primary key of this vw item pricing.
    *
    * @return the primary key of this vw item pricing
    */
    @Override
    public int getPrimaryKey() {
        return _vwItemPricing.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw item pricing.
    *
    * @param primaryKey the primary key of this vw item pricing
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwItemPricing.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pricing code qualifier of this vw item pricing.
    *
    * @return the pricing code qualifier of this vw item pricing
    */
    @Override
    public java.lang.String getPricingCodeQualifier() {
        return _vwItemPricing.getPricingCodeQualifier();
    }

    /**
    * Sets the pricing code qualifier of this vw item pricing.
    *
    * @param pricingCodeQualifier the pricing code qualifier of this vw item pricing
    */
    @Override
    public void setPricingCodeQualifier(java.lang.String pricingCodeQualifier) {
        _vwItemPricing.setPricingCodeQualifier(pricingCodeQualifier);
    }

    /**
    * Returns the item price of this vw item pricing.
    *
    * @return the item price of this vw item pricing
    */
    @Override
    public java.lang.String getItemPrice() {
        return _vwItemPricing.getItemPrice();
    }

    /**
    * Sets the item price of this vw item pricing.
    *
    * @param itemPrice the item price of this vw item pricing
    */
    @Override
    public void setItemPrice(java.lang.String itemPrice) {
        _vwItemPricing.setItemPrice(itemPrice);
    }

    /**
    * Returns the end date of this vw item pricing.
    *
    * @return the end date of this vw item pricing
    */
    @Override
    public java.util.Date getEndDate() {
        return _vwItemPricing.getEndDate();
    }

    /**
    * Sets the end date of this vw item pricing.
    *
    * @param endDate the end date of this vw item pricing
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _vwItemPricing.setEndDate(endDate);
    }

    /**
    * Returns the item ID of this vw item pricing.
    *
    * @return the item ID of this vw item pricing
    */
    @Override
    public java.lang.String getItemId() {
        return _vwItemPricing.getItemId();
    }

    /**
    * Sets the item ID of this vw item pricing.
    *
    * @param itemId the item ID of this vw item pricing
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwItemPricing.setItemId(itemId);
    }

    /**
    * Returns the modified date of this vw item pricing.
    *
    * @return the modified date of this vw item pricing
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwItemPricing.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw item pricing.
    *
    * @param modifiedDate the modified date of this vw item pricing
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwItemPricing.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the entity code of this vw item pricing.
    *
    * @return the entity code of this vw item pricing
    */
    @Override
    public java.lang.String getEntityCode() {
        return _vwItemPricing.getEntityCode();
    }

    /**
    * Sets the entity code of this vw item pricing.
    *
    * @param entityCode the entity code of this vw item pricing
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _vwItemPricing.setEntityCode(entityCode);
    }

    /**
    * Returns the start date of this vw item pricing.
    *
    * @return the start date of this vw item pricing
    */
    @Override
    public java.util.Date getStartDate() {
        return _vwItemPricing.getStartDate();
    }

    /**
    * Sets the start date of this vw item pricing.
    *
    * @param startDate the start date of this vw item pricing
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _vwItemPricing.setStartDate(startDate);
    }

    /**
    * Returns the created date of this vw item pricing.
    *
    * @return the created date of this vw item pricing
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwItemPricing.getCreatedDate();
    }

    /**
    * Sets the created date of this vw item pricing.
    *
    * @param createdDate the created date of this vw item pricing
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwItemPricing.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw item pricing.
    *
    * @return the created by of this vw item pricing
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwItemPricing.getCreatedBy();
    }

    /**
    * Sets the created by of this vw item pricing.
    *
    * @param createdBy the created by of this vw item pricing
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwItemPricing.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this vw item pricing.
    *
    * @return the source of this vw item pricing
    */
    @Override
    public java.lang.String getSource() {
        return _vwItemPricing.getSource();
    }

    /**
    * Sets the source of this vw item pricing.
    *
    * @param source the source of this vw item pricing
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwItemPricing.setSource(source);
    }

    /**
    * Returns the batch ID of this vw item pricing.
    *
    * @return the batch ID of this vw item pricing
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwItemPricing.getBatchId();
    }

    /**
    * Sets the batch ID of this vw item pricing.
    *
    * @param batchId the batch ID of this vw item pricing
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwItemPricing.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this vw item pricing.
    *
    * @return the add chg del indicator of this vw item pricing
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwItemPricing.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw item pricing.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw item pricing
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwItemPricing.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the item name of this vw item pricing.
    *
    * @return the item name of this vw item pricing
    */
    @Override
    public java.lang.String getItemName() {
        return _vwItemPricing.getItemName();
    }

    /**
    * Sets the item name of this vw item pricing.
    *
    * @param itemName the item name of this vw item pricing
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwItemPricing.setItemName(itemName);
    }

    /**
    * Returns the item uom of this vw item pricing.
    *
    * @return the item uom of this vw item pricing
    */
    @Override
    public java.lang.String getItemUom() {
        return _vwItemPricing.getItemUom();
    }

    /**
    * Sets the item uom of this vw item pricing.
    *
    * @param itemUom the item uom of this vw item pricing
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _vwItemPricing.setItemUom(itemUom);
    }

    /**
    * Returns the modified by of this vw item pricing.
    *
    * @return the modified by of this vw item pricing
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwItemPricing.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw item pricing.
    *
    * @param modifiedBy the modified by of this vw item pricing
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwItemPricing.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the item no of this vw item pricing.
    *
    * @return the item no of this vw item pricing
    */
    @Override
    public java.lang.String getItemNo() {
        return _vwItemPricing.getItemNo();
    }

    /**
    * Sets the item no of this vw item pricing.
    *
    * @param itemNo the item no of this vw item pricing
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _vwItemPricing.setItemNo(itemNo);
    }

    /**
    * Returns the item pricing sid of this vw item pricing.
    *
    * @return the item pricing sid of this vw item pricing
    */
    @Override
    public int getItemPricingSid() {
        return _vwItemPricing.getItemPricingSid();
    }

    /**
    * Sets the item pricing sid of this vw item pricing.
    *
    * @param itemPricingSid the item pricing sid of this vw item pricing
    */
    @Override
    public void setItemPricingSid(int itemPricingSid) {
        _vwItemPricing.setItemPricingSid(itemPricingSid);
    }

    /**
    * Returns the pricing code status of this vw item pricing.
    *
    * @return the pricing code status of this vw item pricing
    */
    @Override
    public java.lang.String getPricingCodeStatus() {
        return _vwItemPricing.getPricingCodeStatus();
    }

    /**
    * Sets the pricing code status of this vw item pricing.
    *
    * @param pricingCodeStatus the pricing code status of this vw item pricing
    */
    @Override
    public void setPricingCodeStatus(java.lang.String pricingCodeStatus) {
        _vwItemPricing.setPricingCodeStatus(pricingCodeStatus);
    }

    /**
    * Returns the pricing code qualifier name of this vw item pricing.
    *
    * @return the pricing code qualifier name of this vw item pricing
    */
    @Override
    public java.lang.String getPricingCodeQualifierName() {
        return _vwItemPricing.getPricingCodeQualifierName();
    }

    /**
    * Sets the pricing code qualifier name of this vw item pricing.
    *
    * @param pricingCodeQualifierName the pricing code qualifier name of this vw item pricing
    */
    @Override
    public void setPricingCodeQualifierName(
        java.lang.String pricingCodeQualifierName) {
        _vwItemPricing.setPricingCodeQualifierName(pricingCodeQualifierName);
    }

    /**
    * Returns the item priceprecision of this vw item pricing.
    *
    * @return the item priceprecision of this vw item pricing
    */
    @Override
    public int getItemPriceprecision() {
        return _vwItemPricing.getItemPriceprecision();
    }

    /**
    * Sets the item priceprecision of this vw item pricing.
    *
    * @param itemPriceprecision the item priceprecision of this vw item pricing
    */
    @Override
    public void setItemPriceprecision(int itemPriceprecision) {
        _vwItemPricing.setItemPriceprecision(itemPriceprecision);
    }

    @Override
    public boolean isNew() {
        return _vwItemPricing.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwItemPricing.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwItemPricing.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwItemPricing.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwItemPricing.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwItemPricing.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwItemPricing.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwItemPricing.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwItemPricing.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwItemPricing.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwItemPricing.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwItemPricingWrapper((VwItemPricing) _vwItemPricing.clone());
    }

    @Override
    public int compareTo(VwItemPricing vwItemPricing) {
        return _vwItemPricing.compareTo(vwItemPricing);
    }

    @Override
    public int hashCode() {
        return _vwItemPricing.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwItemPricing> toCacheModel() {
        return _vwItemPricing.toCacheModel();
    }

    @Override
    public VwItemPricing toEscapedModel() {
        return new VwItemPricingWrapper(_vwItemPricing.toEscapedModel());
    }

    @Override
    public VwItemPricing toUnescapedModel() {
        return new VwItemPricingWrapper(_vwItemPricing.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwItemPricing.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwItemPricing.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwItemPricing.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwItemPricingWrapper)) {
            return false;
        }

        VwItemPricingWrapper vwItemPricingWrapper = (VwItemPricingWrapper) obj;

        if (Validator.equals(_vwItemPricing, vwItemPricingWrapper._vwItemPricing)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwItemPricing getWrappedVwItemPricing() {
        return _vwItemPricing;
    }

    @Override
    public VwItemPricing getWrappedModel() {
        return _vwItemPricing;
    }

    @Override
    public void resetOriginalValues() {
        _vwItemPricing.resetOriginalValues();
    }
}
