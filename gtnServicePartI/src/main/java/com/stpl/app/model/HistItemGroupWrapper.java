package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link HistItemGroup}.
 * </p>
 *
 * @author
 * @see HistItemGroup
 * @generated
 */
public class HistItemGroupWrapper implements HistItemGroup,
    ModelWrapper<HistItemGroup> {
    private HistItemGroup _histItemGroup;

    public HistItemGroupWrapper(HistItemGroup histItemGroup) {
        _histItemGroup = histItemGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return HistItemGroup.class;
    }

    @Override
    public String getModelClassName() {
        return HistItemGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("actionFlag", getActionFlag());
        attributes.put("itemGroupNo", getItemGroupNo());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemGroupDescription", getItemGroupDescription());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("itemGroupName", getItemGroupName());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String actionFlag = (String) attributes.get("actionFlag");

        if (actionFlag != null) {
            setActionFlag(actionFlag);
        }

        String itemGroupNo = (String) attributes.get("itemGroupNo");

        if (itemGroupNo != null) {
            setItemGroupNo(itemGroupNo);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemGroupDescription = (String) attributes.get(
                "itemGroupDescription");

        if (itemGroupDescription != null) {
            setItemGroupDescription(itemGroupDescription);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String itemGroupName = (String) attributes.get("itemGroupName");

        if (itemGroupName != null) {
            setItemGroupName(itemGroupName);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    /**
    * Returns the primary key of this hist item group.
    *
    * @return the primary key of this hist item group
    */
    @Override
    public com.stpl.app.service.persistence.HistItemGroupPK getPrimaryKey() {
        return _histItemGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this hist item group.
    *
    * @param primaryKey the primary key of this hist item group
    */
    @Override
    public void setPrimaryKey(
        com.stpl.app.service.persistence.HistItemGroupPK primaryKey) {
        _histItemGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this hist item group.
    *
    * @return the created date of this hist item group
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _histItemGroup.getCreatedDate();
    }

    /**
    * Sets the created date of this hist item group.
    *
    * @param createdDate the created date of this hist item group
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _histItemGroup.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this hist item group.
    *
    * @return the created by of this hist item group
    */
    @Override
    public int getCreatedBy() {
        return _histItemGroup.getCreatedBy();
    }

    /**
    * Sets the created by of this hist item group.
    *
    * @param createdBy the created by of this hist item group
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _histItemGroup.setCreatedBy(createdBy);
    }

    /**
    * Returns the action flag of this hist item group.
    *
    * @return the action flag of this hist item group
    */
    @Override
    public java.lang.String getActionFlag() {
        return _histItemGroup.getActionFlag();
    }

    /**
    * Sets the action flag of this hist item group.
    *
    * @param actionFlag the action flag of this hist item group
    */
    @Override
    public void setActionFlag(java.lang.String actionFlag) {
        _histItemGroup.setActionFlag(actionFlag);
    }

    /**
    * Returns the item group no of this hist item group.
    *
    * @return the item group no of this hist item group
    */
    @Override
    public java.lang.String getItemGroupNo() {
        return _histItemGroup.getItemGroupNo();
    }

    /**
    * Sets the item group no of this hist item group.
    *
    * @param itemGroupNo the item group no of this hist item group
    */
    @Override
    public void setItemGroupNo(java.lang.String itemGroupNo) {
        _histItemGroup.setItemGroupNo(itemGroupNo);
    }

    /**
    * Returns the version no of this hist item group.
    *
    * @return the version no of this hist item group
    */
    @Override
    public int getVersionNo() {
        return _histItemGroup.getVersionNo();
    }

    /**
    * Sets the version no of this hist item group.
    *
    * @param versionNo the version no of this hist item group
    */
    @Override
    public void setVersionNo(int versionNo) {
        _histItemGroup.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this hist item group.
    *
    * @return the modified by of this hist item group
    */
    @Override
    public int getModifiedBy() {
        return _histItemGroup.getModifiedBy();
    }

    /**
    * Sets the modified by of this hist item group.
    *
    * @param modifiedBy the modified by of this hist item group
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _histItemGroup.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the item group description of this hist item group.
    *
    * @return the item group description of this hist item group
    */
    @Override
    public java.lang.String getItemGroupDescription() {
        return _histItemGroup.getItemGroupDescription();
    }

    /**
    * Sets the item group description of this hist item group.
    *
    * @param itemGroupDescription the item group description of this hist item group
    */
    @Override
    public void setItemGroupDescription(java.lang.String itemGroupDescription) {
        _histItemGroup.setItemGroupDescription(itemGroupDescription);
    }

    /**
    * Returns the modified date of this hist item group.
    *
    * @return the modified date of this hist item group
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _histItemGroup.getModifiedDate();
    }

    /**
    * Sets the modified date of this hist item group.
    *
    * @param modifiedDate the modified date of this hist item group
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _histItemGroup.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the item group name of this hist item group.
    *
    * @return the item group name of this hist item group
    */
    @Override
    public java.lang.String getItemGroupName() {
        return _histItemGroup.getItemGroupName();
    }

    /**
    * Sets the item group name of this hist item group.
    *
    * @param itemGroupName the item group name of this hist item group
    */
    @Override
    public void setItemGroupName(java.lang.String itemGroupName) {
        _histItemGroup.setItemGroupName(itemGroupName);
    }

    /**
    * Returns the item group sid of this hist item group.
    *
    * @return the item group sid of this hist item group
    */
    @Override
    public int getItemGroupSid() {
        return _histItemGroup.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this hist item group.
    *
    * @param itemGroupSid the item group sid of this hist item group
    */
    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _histItemGroup.setItemGroupSid(itemGroupSid);
    }

    /**
    * Returns the company master sid of this hist item group.
    *
    * @return the company master sid of this hist item group
    */
    @Override
    public int getCompanyMasterSid() {
        return _histItemGroup.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this hist item group.
    *
    * @param companyMasterSid the company master sid of this hist item group
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _histItemGroup.setCompanyMasterSid(companyMasterSid);
    }

    @Override
    public boolean isNew() {
        return _histItemGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _histItemGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _histItemGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _histItemGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _histItemGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _histItemGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _histItemGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _histItemGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _histItemGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _histItemGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _histItemGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new HistItemGroupWrapper((HistItemGroup) _histItemGroup.clone());
    }

    @Override
    public int compareTo(HistItemGroup histItemGroup) {
        return _histItemGroup.compareTo(histItemGroup);
    }

    @Override
    public int hashCode() {
        return _histItemGroup.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<HistItemGroup> toCacheModel() {
        return _histItemGroup.toCacheModel();
    }

    @Override
    public HistItemGroup toEscapedModel() {
        return new HistItemGroupWrapper(_histItemGroup.toEscapedModel());
    }

    @Override
    public HistItemGroup toUnescapedModel() {
        return new HistItemGroupWrapper(_histItemGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _histItemGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _histItemGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _histItemGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof HistItemGroupWrapper)) {
            return false;
        }

        HistItemGroupWrapper histItemGroupWrapper = (HistItemGroupWrapper) obj;

        if (Validator.equals(_histItemGroup, histItemGroupWrapper._histItemGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public HistItemGroup getWrappedHistItemGroup() {
        return _histItemGroup;
    }

    @Override
    public HistItemGroup getWrappedModel() {
        return _histItemGroup;
    }

    @Override
    public void resetOriginalValues() {
        _histItemGroup.resetOriginalValues();
    }
}
