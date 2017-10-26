package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ItemHierarchyDefMaster}.
 * </p>
 *
 * @author
 * @see ItemHierarchyDefMaster
 * @generated
 */
public class ItemHierarchyDefMasterWrapper implements ItemHierarchyDefMaster,
    ModelWrapper<ItemHierarchyDefMaster> {
    private ItemHierarchyDefMaster _itemHierarchyDefMaster;

    public ItemHierarchyDefMasterWrapper(
        ItemHierarchyDefMaster itemHierarchyDefMaster) {
        _itemHierarchyDefMaster = itemHierarchyDefMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ItemHierarchyDefMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ItemHierarchyDefMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemHierarchyDefMasterSid",
            getItemHierarchyDefMasterSid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("alias", getAlias());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("bpiLvl", getBpiLvl());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("member", getMember());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemHierarchyDefMasterSid = (Integer) attributes.get(
                "itemHierarchyDefMasterSid");

        if (itemHierarchyDefMasterSid != null) {
            setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String alias = (String) attributes.get("alias");

        if (alias != null) {
            setAlias(alias);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String bpiLvl = (String) attributes.get("bpiLvl");

        if (bpiLvl != null) {
            setBpiLvl(bpiLvl);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String member = (String) attributes.get("member");

        if (member != null) {
            setMember(member);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this item hierarchy def master.
    *
    * @return the primary key of this item hierarchy def master
    */
    @Override
    public int getPrimaryKey() {
        return _itemHierarchyDefMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this item hierarchy def master.
    *
    * @param primaryKey the primary key of this item hierarchy def master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _itemHierarchyDefMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item hierarchy def master sid of this item hierarchy def master.
    *
    * @return the item hierarchy def master sid of this item hierarchy def master
    */
    @Override
    public int getItemHierarchyDefMasterSid() {
        return _itemHierarchyDefMaster.getItemHierarchyDefMasterSid();
    }

    /**
    * Sets the item hierarchy def master sid of this item hierarchy def master.
    *
    * @param itemHierarchyDefMasterSid the item hierarchy def master sid of this item hierarchy def master
    */
    @Override
    public void setItemHierarchyDefMasterSid(int itemHierarchyDefMasterSid) {
        _itemHierarchyDefMaster.setItemHierarchyDefMasterSid(itemHierarchyDefMasterSid);
    }

    /**
    * Returns the created by of this item hierarchy def master.
    *
    * @return the created by of this item hierarchy def master
    */
    @Override
    public int getCreatedBy() {
        return _itemHierarchyDefMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this item hierarchy def master.
    *
    * @param createdBy the created by of this item hierarchy def master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _itemHierarchyDefMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the record lock status of this item hierarchy def master.
    *
    * @return the record lock status of this item hierarchy def master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _itemHierarchyDefMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this item hierarchy def master is record lock status.
    *
    * @return <code>true</code> if this item hierarchy def master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _itemHierarchyDefMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this item hierarchy def master is record lock status.
    *
    * @param recordLockStatus the record lock status of this item hierarchy def master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _itemHierarchyDefMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the modified by of this item hierarchy def master.
    *
    * @return the modified by of this item hierarchy def master
    */
    @Override
    public int getModifiedBy() {
        return _itemHierarchyDefMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this item hierarchy def master.
    *
    * @param modifiedBy the modified by of this item hierarchy def master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _itemHierarchyDefMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this item hierarchy def master.
    *
    * @return the created date of this item hierarchy def master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _itemHierarchyDefMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this item hierarchy def master.
    *
    * @param createdDate the created date of this item hierarchy def master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _itemHierarchyDefMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the alias of this item hierarchy def master.
    *
    * @return the alias of this item hierarchy def master
    */
    @Override
    public java.lang.String getAlias() {
        return _itemHierarchyDefMaster.getAlias();
    }

    /**
    * Sets the alias of this item hierarchy def master.
    *
    * @param alias the alias of this item hierarchy def master
    */
    @Override
    public void setAlias(java.lang.String alias) {
        _itemHierarchyDefMaster.setAlias(alias);
    }

    /**
    * Returns the source of this item hierarchy def master.
    *
    * @return the source of this item hierarchy def master
    */
    @Override
    public java.lang.String getSource() {
        return _itemHierarchyDefMaster.getSource();
    }

    /**
    * Sets the source of this item hierarchy def master.
    *
    * @param source the source of this item hierarchy def master
    */
    @Override
    public void setSource(java.lang.String source) {
        _itemHierarchyDefMaster.setSource(source);
    }

    /**
    * Returns the batch ID of this item hierarchy def master.
    *
    * @return the batch ID of this item hierarchy def master
    */
    @Override
    public java.lang.String getBatchId() {
        return _itemHierarchyDefMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this item hierarchy def master.
    *
    * @param batchId the batch ID of this item hierarchy def master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _itemHierarchyDefMaster.setBatchId(batchId);
    }

    /**
    * Returns the bpi lvl of this item hierarchy def master.
    *
    * @return the bpi lvl of this item hierarchy def master
    */
    @Override
    public java.lang.String getBpiLvl() {
        return _itemHierarchyDefMaster.getBpiLvl();
    }

    /**
    * Sets the bpi lvl of this item hierarchy def master.
    *
    * @param bpiLvl the bpi lvl of this item hierarchy def master
    */
    @Override
    public void setBpiLvl(java.lang.String bpiLvl) {
        _itemHierarchyDefMaster.setBpiLvl(bpiLvl);
    }

    /**
    * Returns the modified date of this item hierarchy def master.
    *
    * @return the modified date of this item hierarchy def master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _itemHierarchyDefMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this item hierarchy def master.
    *
    * @param modifiedDate the modified date of this item hierarchy def master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _itemHierarchyDefMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the member of this item hierarchy def master.
    *
    * @return the member of this item hierarchy def master
    */
    @Override
    public java.lang.String getMember() {
        return _itemHierarchyDefMaster.getMember();
    }

    /**
    * Sets the member of this item hierarchy def master.
    *
    * @param member the member of this item hierarchy def master
    */
    @Override
    public void setMember(java.lang.String member) {
        _itemHierarchyDefMaster.setMember(member);
    }

    /**
    * Returns the inbound status of this item hierarchy def master.
    *
    * @return the inbound status of this item hierarchy def master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _itemHierarchyDefMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this item hierarchy def master.
    *
    * @param inboundStatus the inbound status of this item hierarchy def master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _itemHierarchyDefMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _itemHierarchyDefMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _itemHierarchyDefMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _itemHierarchyDefMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _itemHierarchyDefMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _itemHierarchyDefMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _itemHierarchyDefMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _itemHierarchyDefMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _itemHierarchyDefMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _itemHierarchyDefMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _itemHierarchyDefMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _itemHierarchyDefMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ItemHierarchyDefMasterWrapper((ItemHierarchyDefMaster) _itemHierarchyDefMaster.clone());
    }

    @Override
    public int compareTo(ItemHierarchyDefMaster itemHierarchyDefMaster) {
        return _itemHierarchyDefMaster.compareTo(itemHierarchyDefMaster);
    }

    @Override
    public int hashCode() {
        return _itemHierarchyDefMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ItemHierarchyDefMaster> toCacheModel() {
        return _itemHierarchyDefMaster.toCacheModel();
    }

    @Override
    public ItemHierarchyDefMaster toEscapedModel() {
        return new ItemHierarchyDefMasterWrapper(_itemHierarchyDefMaster.toEscapedModel());
    }

    @Override
    public ItemHierarchyDefMaster toUnescapedModel() {
        return new ItemHierarchyDefMasterWrapper(_itemHierarchyDefMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _itemHierarchyDefMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _itemHierarchyDefMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _itemHierarchyDefMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ItemHierarchyDefMasterWrapper)) {
            return false;
        }

        ItemHierarchyDefMasterWrapper itemHierarchyDefMasterWrapper = (ItemHierarchyDefMasterWrapper) obj;

        if (Validator.equals(_itemHierarchyDefMaster,
                    itemHierarchyDefMasterWrapper._itemHierarchyDefMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ItemHierarchyDefMaster getWrappedItemHierarchyDefMaster() {
        return _itemHierarchyDefMaster;
    }

    @Override
    public ItemHierarchyDefMaster getWrappedModel() {
        return _itemHierarchyDefMaster;
    }

    @Override
    public void resetOriginalValues() {
        _itemHierarchyDefMaster.resetOriginalValues();
    }
}
