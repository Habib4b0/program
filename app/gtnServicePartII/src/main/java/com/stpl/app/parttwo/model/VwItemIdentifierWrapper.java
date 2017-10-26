package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwItemIdentifier}.
 * </p>
 *
 * @author
 * @see VwItemIdentifier
 * @generated
 */
public class VwItemIdentifierWrapper implements VwItemIdentifier,
    ModelWrapper<VwItemIdentifier> {
    private VwItemIdentifier _vwItemIdentifier;

    public VwItemIdentifierWrapper(VwItemIdentifier vwItemIdentifier) {
        _vwItemIdentifier = vwItemIdentifier;
    }

    @Override
    public Class<?> getModelClass() {
        return VwItemIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return VwItemIdentifier.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("itemIdentifierSid", getItemIdentifierSid());
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
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemStatus = (String) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Integer itemIdentifierSid = (Integer) attributes.get(
                "itemIdentifierSid");

        if (itemIdentifierSid != null) {
            setItemIdentifierSid(itemIdentifierSid);
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

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }
    }

    /**
    * Returns the primary key of this vw item identifier.
    *
    * @return the primary key of this vw item identifier
    */
    @Override
    public int getPrimaryKey() {
        return _vwItemIdentifier.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw item identifier.
    *
    * @param primaryKey the primary key of this vw item identifier
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwItemIdentifier.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item status of this vw item identifier.
    *
    * @return the item status of this vw item identifier
    */
    @Override
    public java.lang.String getItemStatus() {
        return _vwItemIdentifier.getItemStatus();
    }

    /**
    * Sets the item status of this vw item identifier.
    *
    * @param itemStatus the item status of this vw item identifier
    */
    @Override
    public void setItemStatus(java.lang.String itemStatus) {
        _vwItemIdentifier.setItemStatus(itemStatus);
    }

    /**
    * Returns the item identifier sid of this vw item identifier.
    *
    * @return the item identifier sid of this vw item identifier
    */
    @Override
    public int getItemIdentifierSid() {
        return _vwItemIdentifier.getItemIdentifierSid();
    }

    /**
    * Sets the item identifier sid of this vw item identifier.
    *
    * @param itemIdentifierSid the item identifier sid of this vw item identifier
    */
    @Override
    public void setItemIdentifierSid(int itemIdentifierSid) {
        _vwItemIdentifier.setItemIdentifierSid(itemIdentifierSid);
    }

    /**
    * Returns the end date of this vw item identifier.
    *
    * @return the end date of this vw item identifier
    */
    @Override
    public java.util.Date getEndDate() {
        return _vwItemIdentifier.getEndDate();
    }

    /**
    * Sets the end date of this vw item identifier.
    *
    * @param endDate the end date of this vw item identifier
    */
    @Override
    public void setEndDate(java.util.Date endDate) {
        _vwItemIdentifier.setEndDate(endDate);
    }

    /**
    * Returns the item ID of this vw item identifier.
    *
    * @return the item ID of this vw item identifier
    */
    @Override
    public java.lang.String getItemId() {
        return _vwItemIdentifier.getItemId();
    }

    /**
    * Sets the item ID of this vw item identifier.
    *
    * @param itemId the item ID of this vw item identifier
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _vwItemIdentifier.setItemId(itemId);
    }

    /**
    * Returns the modified date of this vw item identifier.
    *
    * @return the modified date of this vw item identifier
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwItemIdentifier.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw item identifier.
    *
    * @param modifiedDate the modified date of this vw item identifier
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwItemIdentifier.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the entity code of this vw item identifier.
    *
    * @return the entity code of this vw item identifier
    */
    @Override
    public java.lang.String getEntityCode() {
        return _vwItemIdentifier.getEntityCode();
    }

    /**
    * Sets the entity code of this vw item identifier.
    *
    * @param entityCode the entity code of this vw item identifier
    */
    @Override
    public void setEntityCode(java.lang.String entityCode) {
        _vwItemIdentifier.setEntityCode(entityCode);
    }

    /**
    * Returns the start date of this vw item identifier.
    *
    * @return the start date of this vw item identifier
    */
    @Override
    public java.util.Date getStartDate() {
        return _vwItemIdentifier.getStartDate();
    }

    /**
    * Sets the start date of this vw item identifier.
    *
    * @param startDate the start date of this vw item identifier
    */
    @Override
    public void setStartDate(java.util.Date startDate) {
        _vwItemIdentifier.setStartDate(startDate);
    }

    /**
    * Returns the created date of this vw item identifier.
    *
    * @return the created date of this vw item identifier
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwItemIdentifier.getCreatedDate();
    }

    /**
    * Sets the created date of this vw item identifier.
    *
    * @param createdDate the created date of this vw item identifier
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwItemIdentifier.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this vw item identifier.
    *
    * @return the created by of this vw item identifier
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwItemIdentifier.getCreatedBy();
    }

    /**
    * Sets the created by of this vw item identifier.
    *
    * @param createdBy the created by of this vw item identifier
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwItemIdentifier.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this vw item identifier.
    *
    * @return the source of this vw item identifier
    */
    @Override
    public java.lang.String getSource() {
        return _vwItemIdentifier.getSource();
    }

    /**
    * Sets the source of this vw item identifier.
    *
    * @param source the source of this vw item identifier
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwItemIdentifier.setSource(source);
    }

    /**
    * Returns the batch ID of this vw item identifier.
    *
    * @return the batch ID of this vw item identifier
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwItemIdentifier.getBatchId();
    }

    /**
    * Sets the batch ID of this vw item identifier.
    *
    * @param batchId the batch ID of this vw item identifier
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwItemIdentifier.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this vw item identifier.
    *
    * @return the add chg del indicator of this vw item identifier
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwItemIdentifier.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw item identifier.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw item identifier
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwItemIdentifier.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the item name of this vw item identifier.
    *
    * @return the item name of this vw item identifier
    */
    @Override
    public java.lang.String getItemName() {
        return _vwItemIdentifier.getItemName();
    }

    /**
    * Sets the item name of this vw item identifier.
    *
    * @param itemName the item name of this vw item identifier
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _vwItemIdentifier.setItemName(itemName);
    }

    /**
    * Returns the item identifier of this vw item identifier.
    *
    * @return the item identifier of this vw item identifier
    */
    @Override
    public java.lang.String getItemIdentifier() {
        return _vwItemIdentifier.getItemIdentifier();
    }

    /**
    * Sets the item identifier of this vw item identifier.
    *
    * @param itemIdentifier the item identifier of this vw item identifier
    */
    @Override
    public void setItemIdentifier(java.lang.String itemIdentifier) {
        _vwItemIdentifier.setItemIdentifier(itemIdentifier);
    }

    /**
    * Returns the identifier code qualifier name of this vw item identifier.
    *
    * @return the identifier code qualifier name of this vw item identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifierName() {
        return _vwItemIdentifier.getIdentifierCodeQualifierName();
    }

    /**
    * Sets the identifier code qualifier name of this vw item identifier.
    *
    * @param identifierCodeQualifierName the identifier code qualifier name of this vw item identifier
    */
    @Override
    public void setIdentifierCodeQualifierName(
        java.lang.String identifierCodeQualifierName) {
        _vwItemIdentifier.setIdentifierCodeQualifierName(identifierCodeQualifierName);
    }

    /**
    * Returns the modified by of this vw item identifier.
    *
    * @return the modified by of this vw item identifier
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwItemIdentifier.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw item identifier.
    *
    * @param modifiedBy the modified by of this vw item identifier
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwItemIdentifier.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the item no of this vw item identifier.
    *
    * @return the item no of this vw item identifier
    */
    @Override
    public java.lang.String getItemNo() {
        return _vwItemIdentifier.getItemNo();
    }

    /**
    * Sets the item no of this vw item identifier.
    *
    * @param itemNo the item no of this vw item identifier
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _vwItemIdentifier.setItemNo(itemNo);
    }

    /**
    * Returns the identifier code qualifier of this vw item identifier.
    *
    * @return the identifier code qualifier of this vw item identifier
    */
    @Override
    public java.lang.String getIdentifierCodeQualifier() {
        return _vwItemIdentifier.getIdentifierCodeQualifier();
    }

    /**
    * Sets the identifier code qualifier of this vw item identifier.
    *
    * @param identifierCodeQualifier the identifier code qualifier of this vw item identifier
    */
    @Override
    public void setIdentifierCodeQualifier(
        java.lang.String identifierCodeQualifier) {
        _vwItemIdentifier.setIdentifierCodeQualifier(identifierCodeQualifier);
    }

    @Override
    public boolean isNew() {
        return _vwItemIdentifier.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwItemIdentifier.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwItemIdentifier.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwItemIdentifier.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwItemIdentifier.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwItemIdentifier.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwItemIdentifier.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwItemIdentifier.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwItemIdentifier.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwItemIdentifier.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwItemIdentifier.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwItemIdentifierWrapper((VwItemIdentifier) _vwItemIdentifier.clone());
    }

    @Override
    public int compareTo(VwItemIdentifier vwItemIdentifier) {
        return _vwItemIdentifier.compareTo(vwItemIdentifier);
    }

    @Override
    public int hashCode() {
        return _vwItemIdentifier.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwItemIdentifier> toCacheModel() {
        return _vwItemIdentifier.toCacheModel();
    }

    @Override
    public VwItemIdentifier toEscapedModel() {
        return new VwItemIdentifierWrapper(_vwItemIdentifier.toEscapedModel());
    }

    @Override
    public VwItemIdentifier toUnescapedModel() {
        return new VwItemIdentifierWrapper(_vwItemIdentifier.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwItemIdentifier.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwItemIdentifier.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwItemIdentifier.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwItemIdentifierWrapper)) {
            return false;
        }

        VwItemIdentifierWrapper vwItemIdentifierWrapper = (VwItemIdentifierWrapper) obj;

        if (Validator.equals(_vwItemIdentifier,
                    vwItemIdentifierWrapper._vwItemIdentifier)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwItemIdentifier getWrappedVwItemIdentifier() {
        return _vwItemIdentifier;
    }

    @Override
    public VwItemIdentifier getWrappedModel() {
        return _vwItemIdentifier;
    }

    @Override
    public void resetOriginalValues() {
        _vwItemIdentifier.resetOriginalValues();
    }
}
