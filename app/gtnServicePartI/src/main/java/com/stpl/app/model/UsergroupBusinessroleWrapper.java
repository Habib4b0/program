package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UsergroupBusinessrole}.
 * </p>
 *
 * @author
 * @see UsergroupBusinessrole
 * @generated
 */
public class UsergroupBusinessroleWrapper implements UsergroupBusinessrole,
    ModelWrapper<UsergroupBusinessrole> {
    private UsergroupBusinessrole _usergroupBusinessrole;

    public UsergroupBusinessroleWrapper(
        UsergroupBusinessrole usergroupBusinessrole) {
        _usergroupBusinessrole = usergroupBusinessrole;
    }

    @Override
    public Class<?> getModelClass() {
        return UsergroupBusinessrole.class;
    }

    @Override
    public String getModelClassName() {
        return UsergroupBusinessrole.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("processed", getProcessed());
        attributes.put("usergroupId", getUsergroupId());
        attributes.put("versionNo", getVersionNo());
        attributes.put("isActive", getIsActive());
        attributes.put("usergroupBusinessroleSid", getUsergroupBusinessroleSid());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String processed = (String) attributes.get("processed");

        if (processed != null) {
            setProcessed(processed);
        }

        Integer usergroupId = (Integer) attributes.get("usergroupId");

        if (usergroupId != null) {
            setUsergroupId(usergroupId);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        Integer usergroupBusinessroleSid = (Integer) attributes.get(
                "usergroupBusinessroleSid");

        if (usergroupBusinessroleSid != null) {
            setUsergroupBusinessroleSid(usergroupBusinessroleSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this usergroup businessrole.
    *
    * @return the primary key of this usergroup businessrole
    */
    @Override
    public int getPrimaryKey() {
        return _usergroupBusinessrole.getPrimaryKey();
    }

    /**
    * Sets the primary key of this usergroup businessrole.
    *
    * @param primaryKey the primary key of this usergroup businessrole
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _usergroupBusinessrole.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this usergroup businessrole.
    *
    * @return the created by of this usergroup businessrole
    */
    @Override
    public int getCreatedBy() {
        return _usergroupBusinessrole.getCreatedBy();
    }

    /**
    * Sets the created by of this usergroup businessrole.
    *
    * @param createdBy the created by of this usergroup businessrole
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _usergroupBusinessrole.setCreatedBy(createdBy);
    }

    /**
    * Returns the businessrole master sid of this usergroup businessrole.
    *
    * @return the businessrole master sid of this usergroup businessrole
    */
    @Override
    public int getBusinessroleMasterSid() {
        return _usergroupBusinessrole.getBusinessroleMasterSid();
    }

    /**
    * Sets the businessrole master sid of this usergroup businessrole.
    *
    * @param businessroleMasterSid the businessrole master sid of this usergroup businessrole
    */
    @Override
    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _usergroupBusinessrole.setBusinessroleMasterSid(businessroleMasterSid);
    }

    /**
    * Returns the users sid of this usergroup businessrole.
    *
    * @return the users sid of this usergroup businessrole
    */
    @Override
    public int getUsersSid() {
        return _usergroupBusinessrole.getUsersSid();
    }

    /**
    * Sets the users sid of this usergroup businessrole.
    *
    * @param usersSid the users sid of this usergroup businessrole
    */
    @Override
    public void setUsersSid(int usersSid) {
        _usergroupBusinessrole.setUsersSid(usersSid);
    }

    /**
    * Returns the modified by of this usergroup businessrole.
    *
    * @return the modified by of this usergroup businessrole
    */
    @Override
    public int getModifiedBy() {
        return _usergroupBusinessrole.getModifiedBy();
    }

    /**
    * Sets the modified by of this usergroup businessrole.
    *
    * @param modifiedBy the modified by of this usergroup businessrole
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _usergroupBusinessrole.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this usergroup businessrole.
    *
    * @return the created date of this usergroup businessrole
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _usergroupBusinessrole.getCreatedDate();
    }

    /**
    * Sets the created date of this usergroup businessrole.
    *
    * @param createdDate the created date of this usergroup businessrole
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _usergroupBusinessrole.setCreatedDate(createdDate);
    }

    /**
    * Returns the processed of this usergroup businessrole.
    *
    * @return the processed of this usergroup businessrole
    */
    @Override
    public java.lang.String getProcessed() {
        return _usergroupBusinessrole.getProcessed();
    }

    /**
    * Sets the processed of this usergroup businessrole.
    *
    * @param processed the processed of this usergroup businessrole
    */
    @Override
    public void setProcessed(java.lang.String processed) {
        _usergroupBusinessrole.setProcessed(processed);
    }

    /**
    * Returns the usergroup ID of this usergroup businessrole.
    *
    * @return the usergroup ID of this usergroup businessrole
    */
    @Override
    public int getUsergroupId() {
        return _usergroupBusinessrole.getUsergroupId();
    }

    /**
    * Sets the usergroup ID of this usergroup businessrole.
    *
    * @param usergroupId the usergroup ID of this usergroup businessrole
    */
    @Override
    public void setUsergroupId(int usergroupId) {
        _usergroupBusinessrole.setUsergroupId(usergroupId);
    }

    /**
    * Returns the version no of this usergroup businessrole.
    *
    * @return the version no of this usergroup businessrole
    */
    @Override
    public int getVersionNo() {
        return _usergroupBusinessrole.getVersionNo();
    }

    /**
    * Sets the version no of this usergroup businessrole.
    *
    * @param versionNo the version no of this usergroup businessrole
    */
    @Override
    public void setVersionNo(int versionNo) {
        _usergroupBusinessrole.setVersionNo(versionNo);
    }

    /**
    * Returns the is active of this usergroup businessrole.
    *
    * @return the is active of this usergroup businessrole
    */
    @Override
    public java.lang.String getIsActive() {
        return _usergroupBusinessrole.getIsActive();
    }

    /**
    * Sets the is active of this usergroup businessrole.
    *
    * @param isActive the is active of this usergroup businessrole
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _usergroupBusinessrole.setIsActive(isActive);
    }

    /**
    * Returns the usergroup businessrole sid of this usergroup businessrole.
    *
    * @return the usergroup businessrole sid of this usergroup businessrole
    */
    @Override
    public int getUsergroupBusinessroleSid() {
        return _usergroupBusinessrole.getUsergroupBusinessroleSid();
    }

    /**
    * Sets the usergroup businessrole sid of this usergroup businessrole.
    *
    * @param usergroupBusinessroleSid the usergroup businessrole sid of this usergroup businessrole
    */
    @Override
    public void setUsergroupBusinessroleSid(int usergroupBusinessroleSid) {
        _usergroupBusinessrole.setUsergroupBusinessroleSid(usergroupBusinessroleSid);
    }

    /**
    * Returns the modified date of this usergroup businessrole.
    *
    * @return the modified date of this usergroup businessrole
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _usergroupBusinessrole.getModifiedDate();
    }

    /**
    * Sets the modified date of this usergroup businessrole.
    *
    * @param modifiedDate the modified date of this usergroup businessrole
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _usergroupBusinessrole.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _usergroupBusinessrole.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _usergroupBusinessrole.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _usergroupBusinessrole.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _usergroupBusinessrole.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _usergroupBusinessrole.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _usergroupBusinessrole.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _usergroupBusinessrole.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _usergroupBusinessrole.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _usergroupBusinessrole.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _usergroupBusinessrole.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _usergroupBusinessrole.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new UsergroupBusinessroleWrapper((UsergroupBusinessrole) _usergroupBusinessrole.clone());
    }

    @Override
    public int compareTo(UsergroupBusinessrole usergroupBusinessrole) {
        return _usergroupBusinessrole.compareTo(usergroupBusinessrole);
    }

    @Override
    public int hashCode() {
        return _usergroupBusinessrole.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<UsergroupBusinessrole> toCacheModel() {
        return _usergroupBusinessrole.toCacheModel();
    }

    @Override
    public UsergroupBusinessrole toEscapedModel() {
        return new UsergroupBusinessroleWrapper(_usergroupBusinessrole.toEscapedModel());
    }

    @Override
    public UsergroupBusinessrole toUnescapedModel() {
        return new UsergroupBusinessroleWrapper(_usergroupBusinessrole.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _usergroupBusinessrole.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _usergroupBusinessrole.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _usergroupBusinessrole.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UsergroupBusinessroleWrapper)) {
            return false;
        }

        UsergroupBusinessroleWrapper usergroupBusinessroleWrapper = (UsergroupBusinessroleWrapper) obj;

        if (Validator.equals(_usergroupBusinessrole,
                    usergroupBusinessroleWrapper._usergroupBusinessrole)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public UsergroupBusinessrole getWrappedUsergroupBusinessrole() {
        return _usergroupBusinessrole;
    }

    @Override
    public UsergroupBusinessrole getWrappedModel() {
        return _usergroupBusinessrole;
    }

    @Override
    public void resetOriginalValues() {
        _usergroupBusinessrole.resetOriginalValues();
    }
}
