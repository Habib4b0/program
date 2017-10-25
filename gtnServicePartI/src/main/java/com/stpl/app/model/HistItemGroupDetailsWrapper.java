package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistItemGroupDetails}.
 * </p>
 *
 * @author
 * @see HistItemGroupDetails
 * @generated
 */
public class HistItemGroupDetailsWrapper implements HistItemGroupDetails,
    ModelWrapper<HistItemGroupDetails> {
    private HistItemGroupDetails _histItemGroupDetails;

    public HistItemGroupDetailsWrapper(
        HistItemGroupDetails histItemGroupDetails) {
        _histItemGroupDetails = histItemGroupDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return HistItemGroupDetails.class;
    }

    @Override
    public String getModelClassName() {
        return HistItemGroupDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionDate", getActionDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("actionFlag", getActionFlag());
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

        Date actionDate = (Date) attributes.get("actionDate");

        if (actionDate != null) {
            setActionDate(actionDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
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
    * Returns the primary key of this hist item group details.
    *
    * @return the primary key of this hist item group details
    */
    @Override
    public com.stpl.app.service.persistence.HistItemGroupDetailsPK getPrimaryKey() {
        return _histItemGroupDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist item group details.
    *
    * @param primaryKey the primary key of this hist item group details
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistItemGroupDetailsPK primaryKey) {
        _histItemGroupDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item group details sid of this hist item group details.
    *
    * @return the item group details sid of this hist item group details
    */
    @Override
    public int getItemGroupDetailsSid() {
        return _histItemGroupDetails.getItemGroupDetailsSid();
    }

    /**
    * Sets the item group details sid of this hist item group details.
    *
    * @param itemGroupDetailsSid the item group details sid of this hist item group details
    */
    @Override
    public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
        _histItemGroupDetails.setItemGroupDetailsSid(itemGroupDetailsSid);
    }

    /**
    * Returns the created date of this hist item group details.
    *
    * @return the created date of this hist item group details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histItemGroupDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this hist item group details.
    *
    * @param createdDate the created date of this hist item group details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histItemGroupDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hist item group details.
    *
    * @return the created by of this hist item group details
    */
    @Override
    public int getCreatedBy() {
        return _histItemGroupDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this hist item group details.
    *
    * @param createdBy the created by of this hist item group details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histItemGroupDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the action date of this hist item group details.
    *
    * @return the action date of this hist item group details
    */
    @Override
    public java.util.Date getActionDate() {
        return _histItemGroupDetails.getActionDate();
    }

    /**
    * Sets the action date of this hist item group details.
    *
    * @param actionDate the action date of this hist item group details
    */
    @Override
    public void setActionDate(java.util.Date actionDate) {
        _histItemGroupDetails.setActionDate(actionDate);
    }

    /**
    * Returns the item master sid of this hist item group details.
    *
    * @return the item master sid of this hist item group details
    */
    @Override
    public int getItemMasterSid() {
        return _histItemGroupDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this hist item group details.
    *
    * @param itemMasterSid the item master sid of this hist item group details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _histItemGroupDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the action flag of this hist item group details.
    *
    * @return the action flag of this hist item group details
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histItemGroupDetails.getActionFlag();
    }

    /**
    * Sets the action flag of this hist item group details.
    *
    * @param actionFlag the action flag of this hist item group details
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histItemGroupDetails.setActionFlag(actionFlag);
    }

    /**
    * Returns the version no of this hist item group details.
    *
    * @return the version no of this hist item group details
    */
    @Override
    public int getVersionNo() {
        return _histItemGroupDetails.getVersionNo();
    }

    /**
    * Sets the version no of this hist item group details.
    *
    * @param versionNo the version no of this hist item group details
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histItemGroupDetails.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hist item group details.
    *
    * @return the modified by of this hist item group details
    */
    @Override
    public int getModifiedBy() {
        return _histItemGroupDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist item group details.
    *
    * @param modifiedBy the modified by of this hist item group details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histItemGroupDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this hist item group details.
    *
    * @return the modified date of this hist item group details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histItemGroupDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist item group details.
    *
    * @param modifiedDate the modified date of this hist item group details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histItemGroupDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the item group sid of this hist item group details.
    *
    * @return the item group sid of this hist item group details
    */
    @Override
    public int getItemGroupSid() {
        return _histItemGroupDetails.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this hist item group details.
    *
    * @param itemGroupSid the item group sid of this hist item group details
    */
    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _histItemGroupDetails.setItemGroupSid(itemGroupSid);
    }

    @Override
    public boolean isNew() {
        return _histItemGroupDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histItemGroupDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histItemGroupDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histItemGroupDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histItemGroupDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histItemGroupDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histItemGroupDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histItemGroupDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histItemGroupDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histItemGroupDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histItemGroupDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistItemGroupDetailsWrapper((HistItemGroupDetails) _histItemGroupDetails.clone());
    }

    @Override
    public int compareTo(HistItemGroupDetails histItemGroupDetails) {
        return _histItemGroupDetails.compareTo(histItemGroupDetails);
    }

    @Override
    public int hashCode() {
        return _histItemGroupDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistItemGroupDetails> toCacheModel() {
        return _histItemGroupDetails.toCacheModel();
    }

    @Override
    public HistItemGroupDetails toEscapedModel() {
        return new HistItemGroupDetailsWrapper(_histItemGroupDetails.toEscapedModel());
    }

    @Override
    public HistItemGroupDetails toUnescapedModel() {
        return new HistItemGroupDetailsWrapper(_histItemGroupDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histItemGroupDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histItemGroupDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histItemGroupDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistItemGroupDetailsWrapper)) {
            return false;
        }

        HistItemGroupDetailsWrapper histItemGroupDetailsWrapper = (HistItemGroupDetailsWrapper) obj;

        if (Validator.equals(_histItemGroupDetails,
                    histItemGroupDetailsWrapper._histItemGroupDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistItemGroupDetails getWrappedHistItemGroupDetails() {
        return _histItemGroupDetails;
    }

    @Override
    public HistItemGroupDetails getWrappedModel() {
        return _histItemGroupDetails;
    }

    @Override
    public void resetOriginalValues() {
        _histItemGroupDetails.resetOriginalValues();
    }
}
