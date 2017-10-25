package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemGroupDetails}.
 * </p>
 *
 * @author
 * @see ItemGroupDetails
 * @generated
 */
public class ItemGroupDetailsWrapper implements ItemGroupDetails,
    ModelWrapper<ItemGroupDetails> {
    private ItemGroupDetails _itemGroupDetails;

    public ItemGroupDetailsWrapper(ItemGroupDetails itemGroupDetails) {
        _itemGroupDetails = itemGroupDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ItemGroupDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemGroupSid", getItemGroupSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemGroupDetailsSid = (Integer) attributes.get(
                "itemGroupDetailsSid");

        if (itemGroupDetailsSid != null) {
            setItemGroupDetailsSid(itemGroupDetailsSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }
    }

    /**
    * Returns the primary key of this item group details.
    *
    * @return the primary key of this item group details
    */
    @Override
    public int getPrimaryKey() {
        return _itemGroupDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item group details.
    *
    * @param primaryKey the primary key of this item group details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemGroupDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item group details sid of this item group details.
    *
    * @return the item group details sid of this item group details
    */
    @Override
    public int getItemGroupDetailsSid() {
        return _itemGroupDetails.getItemGroupDetailsSid();
    }

    /**
    * Sets the item group details sid of this item group details.
    *
    * @param itemGroupDetailsSid the item group details sid of this item group details
    */
    @Override
    public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
        _itemGroupDetails.setItemGroupDetailsSid(itemGroupDetailsSid);
    }

    /**
    * Returns the created date of this item group details.
    *
    * @return the created date of this item group details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemGroupDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this item group details.
    *
    * @param createdDate the created date of this item group details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemGroupDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this item group details.
    *
    * @return the created by of this item group details
    */
    @Override
    public int getCreatedBy() {
        return _itemGroupDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this item group details.
    *
    * @param createdBy the created by of this item group details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemGroupDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the item master sid of this item group details.
    *
    * @return the item master sid of this item group details
    */
    @Override
    public int getItemMasterSid() {
        return _itemGroupDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this item group details.
    *
    * @param itemMasterSid the item master sid of this item group details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemGroupDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the version no of this item group details.
    *
    * @return the version no of this item group details
    */
    @Override
    public int getVersionNo() {
        return _itemGroupDetails.getVersionNo();
    }

    /**
    * Sets the version no of this item group details.
    *
    * @param versionNo the version no of this item group details
    */
    @Override
    public void setVersionNo(int versionNo) {
        _itemGroupDetails.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this item group details.
    *
    * @return the modified by of this item group details
    */
    @Override
    public int getModifiedBy() {
        return _itemGroupDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this item group details.
    *
    * @param modifiedBy the modified by of this item group details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemGroupDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this item group details.
    *
    * @return the modified date of this item group details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemGroupDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this item group details.
    *
    * @param modifiedDate the modified date of this item group details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemGroupDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the item group sid of this item group details.
    *
    * @return the item group sid of this item group details
    */
    @Override
    public int getItemGroupSid() {
        return _itemGroupDetails.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this item group details.
    *
    * @param itemGroupSid the item group sid of this item group details
    */
    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _itemGroupDetails.setItemGroupSid(itemGroupSid);
    }

    @Override
    public boolean isNew() {
        return _itemGroupDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemGroupDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemGroupDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemGroupDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemGroupDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemGroupDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemGroupDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemGroupDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemGroupDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemGroupDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemGroupDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemGroupDetailsWrapper((ItemGroupDetails) _itemGroupDetails.clone());
    }

    @Override
    public int compareTo(ItemGroupDetails itemGroupDetails) {
        return _itemGroupDetails.compareTo(itemGroupDetails);
    }

    @Override
    public int hashCode() {
        return _itemGroupDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemGroupDetails> toCacheModel() {
        return _itemGroupDetails.toCacheModel();
    }

    @Override
    public ItemGroupDetails toEscapedModel() {
        return new ItemGroupDetailsWrapper(_itemGroupDetails.toEscapedModel());
    }

    @Override
    public ItemGroupDetails toUnescapedModel() {
        return new ItemGroupDetailsWrapper(_itemGroupDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemGroupDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemGroupDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemGroupDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemGroupDetailsWrapper)) {
            return false;
        }

        ItemGroupDetailsWrapper itemGroupDetailsWrapper = (ItemGroupDetailsWrapper) obj;

        if (Validator.equals(_itemGroupDetails,
                    itemGroupDetailsWrapper._itemGroupDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemGroupDetails getWrappedItemGroupDetails() {
        return _itemGroupDetails;
    }

    @Override
    public ItemGroupDetails getWrappedModel() {
        return _itemGroupDetails;
    }

    @Override
    public void resetOriginalValues() {
        _itemGroupDetails.resetOriginalValues();
    }
}
