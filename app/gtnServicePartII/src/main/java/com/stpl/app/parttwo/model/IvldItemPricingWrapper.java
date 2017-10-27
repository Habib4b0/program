package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldItemPricing}.
 * </p>
 *
 * @author
 * @see IvldItemPricing
 * @generated
 */
public class IvldItemPricingWrapper implements IvldItemPricing,
    ModelWrapper<IvldItemPricing> {
    private IvldItemPricing _ivldItemPricing;

    public IvldItemPricingWrapper(IvldItemPricing ivldItemPricing) {
        _ivldItemPricing = ivldItemPricing;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemPricing.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("pricingCodeQualifierName", getPricingCodeQualifierName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("endDate", getEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorCode", getErrorCode());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("itemPricingIntfid", getItemPricingIntfid());
        attributes.put("ivldItemPricingSid", getIvldItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemId", getItemId());
        attributes.put("errorField", getErrorField());
        attributes.put("startDate", getStartDate());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("source", getSource());
        attributes.put("pricingCodeQualifier", getPricingCodeQualifier());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("itemPriceprecision", getItemPriceprecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String pricingCodeQualifierName = (String) attributes.get(
                "pricingCodeQualifierName");

        if (pricingCodeQualifierName != null) {
            setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String itemPricingIntfid = (String) attributes.get("itemPricingIntfid");

        if (itemPricingIntfid != null) {
            setItemPricingIntfid(itemPricingIntfid);
        }

        Integer ivldItemPricingSid = (Integer) attributes.get(
                "ivldItemPricingSid");

        if (ivldItemPricingSid != null) {
            setIvldItemPricingSid(ivldItemPricingSid);
        }

        String pricingCodeStatus = (String) attributes.get("pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String pricingCodeQualifier = (String) attributes.get(
                "pricingCodeQualifier");

        if (pricingCodeQualifier != null) {
            setPricingCodeQualifier(pricingCodeQualifier);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemPrice = (String) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String itemPriceprecision = (String) attributes.get(
                "itemPriceprecision");

        if (itemPriceprecision != null) {
            setItemPriceprecision(itemPriceprecision);
        }
    }

    /**
    * Returns the primary key of this ivld item pricing.
    *
    * @return the primary key of this ivld item pricing
    */
    @Override
    public int getPrimaryKey() {
        return _ivldItemPricing.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld item pricing.
    *
    * @param primaryKey the primary key of this ivld item pricing
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldItemPricing.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item no of this ivld item pricing.
    *
    * @return the item no of this ivld item pricing
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldItemPricing.getItemNo();
    }

    /**
    * Sets the item no of this ivld item pricing.
    *
    * @param itemNo the item no of this ivld item pricing
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldItemPricing.setItemNo(itemNo);
    }

    /**
    * Returns the modified by of this ivld item pricing.
    *
    * @return the modified by of this ivld item pricing
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldItemPricing.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld item pricing.
    *
    * @param modifiedBy the modified by of this ivld item pricing
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldItemPricing.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the pricing code qualifier name of this ivld item pricing.
    *
    * @return the pricing code qualifier name of this ivld item pricing
    */
    @Override
    public java.lang.String getPricingCodeQualifierName() {
        return _ivldItemPricing.getPricingCodeQualifierName();
    }

    /**
    * Sets the pricing code qualifier name of this ivld item pricing.
    *
    * @param pricingCodeQualifierName the pricing code qualifier name of this ivld item pricing
    */
    @Override
    public void setPricingCodeQualifierName(
        java.lang.String pricingCodeQualifierName) {
        _ivldItemPricing.setPricingCodeQualifierName(pricingCodeQualifierName);
    }

    /**
    * Returns the created date of this ivld item pricing.
    *
    * @return the created date of this ivld item pricing
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldItemPricing.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld item pricing.
    *
    * @param createdDate the created date of this ivld item pricing
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldItemPricing.setCreatedDate(createdDate);
    }

    /**
    * Returns the end date of this ivld item pricing.
    *
    * @return the end date of this ivld item pricing
    */
    @Override
    public java.lang.String getEndDate() {
        return _ivldItemPricing.getEndDate();
    }

    /**
    * Sets the end date of this ivld item pricing.
    *
    * @param endDate the end date of this ivld item pricing
    */
    @Override
    public void setEndDate(java.lang.String endDate) {
        _ivldItemPricing.setEndDate(endDate);
    }

    /**
    * Returns the batch ID of this ivld item pricing.
    *
    * @return the batch ID of this ivld item pricing
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldItemPricing.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld item pricing.
    *
    * @param batchId the batch ID of this ivld item pricing
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldItemPricing.setBatchId(batchId);
    }

    /**
    * Returns the item name of this ivld item pricing.
    *
    * @return the item name of this ivld item pricing
    */
    @Override
    public java.lang.String getItemName() {
        return _ivldItemPricing.getItemName();
    }

    /**
    * Sets the item name of this ivld item pricing.
    *
    * @param itemName the item name of this ivld item pricing
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _ivldItemPricing.setItemName(itemName);
    }

    /**
    * Returns the error code of this ivld item pricing.
    *
    * @return the error code of this ivld item pricing
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldItemPricing.getErrorCode();
    }

    /**
    * Sets the error code of this ivld item pricing.
    *
    * @param errorCode the error code of this ivld item pricing
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldItemPricing.setErrorCode(errorCode);
    }

    /**
    * Returns the reprocessed flag of this ivld item pricing.
    *
    * @return the reprocessed flag of this ivld item pricing
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldItemPricing.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld item pricing.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld item pricing
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldItemPricing.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the item pricing intfid of this ivld item pricing.
    *
    * @return the item pricing intfid of this ivld item pricing
    */
    @Override
    public java.lang.String getItemPricingIntfid() {
        return _ivldItemPricing.getItemPricingIntfid();
    }

    /**
    * Sets the item pricing intfid of this ivld item pricing.
    *
    * @param itemPricingIntfid the item pricing intfid of this ivld item pricing
    */
    @Override
    public void setItemPricingIntfid(java.lang.String itemPricingIntfid) {
        _ivldItemPricing.setItemPricingIntfid(itemPricingIntfid);
    }

    /**
    * Returns the ivld item pricing sid of this ivld item pricing.
    *
    * @return the ivld item pricing sid of this ivld item pricing
    */
    @Override
    public int getIvldItemPricingSid() {
        return _ivldItemPricing.getIvldItemPricingSid();
    }

    /**
    * Sets the ivld item pricing sid of this ivld item pricing.
    *
    * @param ivldItemPricingSid the ivld item pricing sid of this ivld item pricing
    */
    @Override
    public void setIvldItemPricingSid(int ivldItemPricingSid) {
        _ivldItemPricing.setIvldItemPricingSid(ivldItemPricingSid);
    }

    /**
    * Returns the pricing code status of this ivld item pricing.
    *
    * @return the pricing code status of this ivld item pricing
    */
    @Override
    public java.lang.String getPricingCodeStatus() {
        return _ivldItemPricing.getPricingCodeStatus();
    }

    /**
    * Sets the pricing code status of this ivld item pricing.
    *
    * @param pricingCodeStatus the pricing code status of this ivld item pricing
    */
    @Override
    public void setPricingCodeStatus(java.lang.String pricingCodeStatus) {
        _ivldItemPricing.setPricingCodeStatus(pricingCodeStatus);
    }

    /**
    * Returns the created by of this ivld item pricing.
    *
    * @return the created by of this ivld item pricing
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldItemPricing.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld item pricing.
    *
    * @param createdBy the created by of this ivld item pricing
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldItemPricing.setCreatedBy(createdBy);
    }

    /**
    * Returns the item ID of this ivld item pricing.
    *
    * @return the item ID of this ivld item pricing
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldItemPricing.getItemId();
    }

    /**
    * Sets the item ID of this ivld item pricing.
    *
    * @param itemId the item ID of this ivld item pricing
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldItemPricing.setItemId(itemId);
    }

    /**
    * Returns the error field of this ivld item pricing.
    *
    * @return the error field of this ivld item pricing
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldItemPricing.getErrorField();
    }

    /**
    * Sets the error field of this ivld item pricing.
    *
    * @param errorField the error field of this ivld item pricing
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldItemPricing.setErrorField(errorField);
    }

    /**
    * Returns the start date of this ivld item pricing.
    *
    * @return the start date of this ivld item pricing
    */
    @Override
    public java.lang.String getStartDate() {
        return _ivldItemPricing.getStartDate();
    }

    /**
    * Sets the start date of this ivld item pricing.
    *
    * @param startDate the start date of this ivld item pricing
    */
    @Override
    public void setStartDate(java.lang.String startDate) {
        _ivldItemPricing.setStartDate(startDate);
    }

    /**
    * Returns the item uom of this ivld item pricing.
    *
    * @return the item uom of this ivld item pricing
    */
    @Override
    public java.lang.String getItemUom() {
        return _ivldItemPricing.getItemUom();
    }

    /**
    * Sets the item uom of this ivld item pricing.
    *
    * @param itemUom the item uom of this ivld item pricing
    */
    @Override
    public void setItemUom(java.lang.String itemUom) {
        _ivldItemPricing.setItemUom(itemUom);
    }

    /**
    * Returns the modified date of this ivld item pricing.
    *
    * @return the modified date of this ivld item pricing
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldItemPricing.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld item pricing.
    *
    * @param modifiedDate the modified date of this ivld item pricing
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldItemPricing.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the reason for failure of this ivld item pricing.
    *
    * @return the reason for failure of this ivld item pricing
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldItemPricing.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld item pricing.
    *
    * @param reasonForFailure the reason for failure of this ivld item pricing
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldItemPricing.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the source of this ivld item pricing.
    *
    * @return the source of this ivld item pricing
    */
    @Override
    public java.lang.String getSource() {
        return _ivldItemPricing.getSource();
    }

    /**
    * Sets the source of this ivld item pricing.
    *
    * @param source the source of this ivld item pricing
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldItemPricing.setSource(source);
    }

    /**
    * Returns the pricing code qualifier of this ivld item pricing.
    *
    * @return the pricing code qualifier of this ivld item pricing
    */
    @Override
    public java.lang.String getPricingCodeQualifier() {
        return _ivldItemPricing.getPricingCodeQualifier();
    }

    /**
    * Sets the pricing code qualifier of this ivld item pricing.
    *
    * @param pricingCodeQualifier the pricing code qualifier of this ivld item pricing
    */
    @Override
    public void setPricingCodeQualifier(java.lang.String pricingCodeQualifier) {
        _ivldItemPricing.setPricingCodeQualifier(pricingCodeQualifier);
    }

    /**
    * Returns the add chg del indicator of this ivld item pricing.
    *
    * @return the add chg del indicator of this ivld item pricing
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldItemPricing.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld item pricing.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld item pricing
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldItemPricing.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the entity code of this ivld item pricing.
    *
    * @return the entity code of this ivld item pricing
    */
    @Override
    public java.lang.String getEntityCode() {
        return _ivldItemPricing.getEntityCode();
    }

    /**
    * Sets the entity code of this ivld item pricing.
    *
    * @param entityCode the entity code of this ivld item pricing
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _ivldItemPricing.setEntityCode(entityCode);
    }

    /**
    * Returns the item price of this ivld item pricing.
    *
    * @return the item price of this ivld item pricing
    */
    @Override
    public java.lang.String getItemPrice() {
        return _ivldItemPricing.getItemPrice();
    }

    /**
    * Sets the item price of this ivld item pricing.
    *
    * @param itemPrice the item price of this ivld item pricing
    */
    @Override
    public void setItemPrice(java.lang.String itemPrice) {
        _ivldItemPricing.setItemPrice(itemPrice);
    }

    /**
    * Returns the intf inserted date of this ivld item pricing.
    *
    * @return the intf inserted date of this ivld item pricing
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldItemPricing.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld item pricing.
    *
    * @param intfInsertedDate the intf inserted date of this ivld item pricing
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldItemPricing.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the check record of this ivld item pricing.
    *
    * @return the check record of this ivld item pricing
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldItemPricing.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld item pricing is check record.
    *
    * @return <code>true</code> if this ivld item pricing is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldItemPricing.isCheckRecord();
    }

    /**
    * Sets whether this ivld item pricing is check record.
    *
    * @param checkRecord the check record of this ivld item pricing
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldItemPricing.setCheckRecord(checkRecord);
    }

    /**
    * Returns the item priceprecision of this ivld item pricing.
    *
    * @return the item priceprecision of this ivld item pricing
    */
    @Override
    public java.lang.String getItemPriceprecision() {
        return _ivldItemPricing.getItemPriceprecision();
    }

    /**
    * Sets the item priceprecision of this ivld item pricing.
    *
    * @param itemPriceprecision the item priceprecision of this ivld item pricing
    */
    @Override
    public void setItemPriceprecision(java.lang.String itemPriceprecision) {
        _ivldItemPricing.setItemPriceprecision(itemPriceprecision);
    }

    @Override
    public boolean isNew() {
        return _ivldItemPricing.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldItemPricing.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldItemPricing.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldItemPricing.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldItemPricing.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldItemPricing.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldItemPricing.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldItemPricing.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldItemPricing.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldItemPricing.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldItemPricing.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldItemPricingWrapper((IvldItemPricing) _ivldItemPricing.clone());
    }

    @Override
    public int compareTo(IvldItemPricing ivldItemPricing) {
        return _ivldItemPricing.compareTo(ivldItemPricing);
    }

    @Override
    public int hashCode() {
        return _ivldItemPricing.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldItemPricing> toCacheModel() {
        return _ivldItemPricing.toCacheModel();
    }

    @Override
    public IvldItemPricing toEscapedModel() {
        return new IvldItemPricingWrapper(_ivldItemPricing.toEscapedModel());
    }

    @Override
    public IvldItemPricing toUnescapedModel() {
        return new IvldItemPricingWrapper(_ivldItemPricing.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldItemPricing.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldItemPricing.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldItemPricing.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemPricingWrapper)) {
            return false;
        }

        IvldItemPricingWrapper ivldItemPricingWrapper = (IvldItemPricingWrapper) obj;

        if (Validator.equals(_ivldItemPricing,
                    ivldItemPricingWrapper._ivldItemPricing)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldItemPricing getWrappedIvldItemPricing() {
        return _ivldItemPricing;
    }

    @Override
    public IvldItemPricing getWrappedModel() {
        return _ivldItemPricing;
    }

    @Override
    public void resetOriginalValues() {
        _ivldItemPricing.resetOriginalValues();
    }
}
