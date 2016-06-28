package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link UsergroupDomainMaster}.
 * </p>
 *
 * @author
 * @see UsergroupDomainMaster
 * @generated
 */
public class UsergroupDomainMasterWrapper implements UsergroupDomainMaster,
    ModelWrapper<UsergroupDomainMaster> {
    private UsergroupDomainMaster _usergroupDomainMaster;

    public UsergroupDomainMasterWrapper(
        UsergroupDomainMaster usergroupDomainMaster) {
        _usergroupDomainMaster = usergroupDomainMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return UsergroupDomainMaster.class;
    }

    @Override
    public String getModelClassName() {
        return UsergroupDomainMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("usergroupDomainSid", getUsergroupDomainSid());
        attributes.put("usersSid", getUsersSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("domainId", getDomainId());
        attributes.put("processed", getProcessed());
        attributes.put("usergroupId", getUsergroupId());
        attributes.put("versionNo", getVersionNo());
        attributes.put("isActive", getIsActive());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usergroupDomainSid = (Integer) attributes.get(
                "usergroupDomainSid");

        if (usergroupDomainSid != null) {
            setUsergroupDomainSid(usergroupDomainSid);
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

        Integer domainId = (Integer) attributes.get("domainId");

        if (domainId != null) {
            setDomainId(domainId);
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

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this usergroup domain master.
    *
    * @return the primary key of this usergroup domain master
    */
    @Override
    public int getPrimaryKey() {
        return _usergroupDomainMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this usergroup domain master.
    *
    * @param primaryKey the primary key of this usergroup domain master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _usergroupDomainMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this usergroup domain master.
    *
    * @return the created by of this usergroup domain master
    */
    @Override
    public int getCreatedBy() {
        return _usergroupDomainMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this usergroup domain master.
    *
    * @param createdBy the created by of this usergroup domain master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _usergroupDomainMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the usergroup domain sid of this usergroup domain master.
    *
    * @return the usergroup domain sid of this usergroup domain master
    */
    @Override
    public int getUsergroupDomainSid() {
        return _usergroupDomainMaster.getUsergroupDomainSid();
    }

    /**
    * Sets the usergroup domain sid of this usergroup domain master.
    *
    * @param usergroupDomainSid the usergroup domain sid of this usergroup domain master
    */
    @Override
    public void setUsergroupDomainSid(int usergroupDomainSid) {
        _usergroupDomainMaster.setUsergroupDomainSid(usergroupDomainSid);
    }

    /**
    * Returns the users sid of this usergroup domain master.
    *
    * @return the users sid of this usergroup domain master
    */
    @Override
    public int getUsersSid() {
        return _usergroupDomainMaster.getUsersSid();
    }

    /**
    * Sets the users sid of this usergroup domain master.
    *
    * @param usersSid the users sid of this usergroup domain master
    */
    @Override
    public void setUsersSid(int usersSid) {
        _usergroupDomainMaster.setUsersSid(usersSid);
    }

    /**
    * Returns the modified by of this usergroup domain master.
    *
    * @return the modified by of this usergroup domain master
    */
    @Override
    public int getModifiedBy() {
        return _usergroupDomainMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this usergroup domain master.
    *
    * @param modifiedBy the modified by of this usergroup domain master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _usergroupDomainMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this usergroup domain master.
    *
    * @return the created date of this usergroup domain master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _usergroupDomainMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this usergroup domain master.
    *
    * @param createdDate the created date of this usergroup domain master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _usergroupDomainMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the domain ID of this usergroup domain master.
    *
    * @return the domain ID of this usergroup domain master
    */
    @Override
    public int getDomainId() {
        return _usergroupDomainMaster.getDomainId();
    }

    /**
    * Sets the domain ID of this usergroup domain master.
    *
    * @param domainId the domain ID of this usergroup domain master
    */
    @Override
    public void setDomainId(int domainId) {
        _usergroupDomainMaster.setDomainId(domainId);
    }

    /**
    * Returns the processed of this usergroup domain master.
    *
    * @return the processed of this usergroup domain master
    */
    @Override
    public java.lang.String getProcessed() {
        return _usergroupDomainMaster.getProcessed();
    }

    /**
    * Sets the processed of this usergroup domain master.
    *
    * @param processed the processed of this usergroup domain master
    */
    @Override
    public void setProcessed(java.lang.String processed) {
        _usergroupDomainMaster.setProcessed(processed);
    }

    /**
    * Returns the usergroup ID of this usergroup domain master.
    *
    * @return the usergroup ID of this usergroup domain master
    */
    @Override
    public int getUsergroupId() {
        return _usergroupDomainMaster.getUsergroupId();
    }

    /**
    * Sets the usergroup ID of this usergroup domain master.
    *
    * @param usergroupId the usergroup ID of this usergroup domain master
    */
    @Override
    public void setUsergroupId(int usergroupId) {
        _usergroupDomainMaster.setUsergroupId(usergroupId);
    }

    /**
    * Returns the version no of this usergroup domain master.
    *
    * @return the version no of this usergroup domain master
    */
    @Override
    public int getVersionNo() {
        return _usergroupDomainMaster.getVersionNo();
    }

    /**
    * Sets the version no of this usergroup domain master.
    *
    * @param versionNo the version no of this usergroup domain master
    */
    @Override
    public void setVersionNo(int versionNo) {
        _usergroupDomainMaster.setVersionNo(versionNo);
    }

    /**
    * Returns the is active of this usergroup domain master.
    *
    * @return the is active of this usergroup domain master
    */
    @Override
    public java.lang.String getIsActive() {
        return _usergroupDomainMaster.getIsActive();
    }

    /**
    * Sets the is active of this usergroup domain master.
    *
    * @param isActive the is active of this usergroup domain master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _usergroupDomainMaster.setIsActive(isActive);
    }

    /**
    * Returns the modified date of this usergroup domain master.
    *
    * @return the modified date of this usergroup domain master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _usergroupDomainMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this usergroup domain master.
    *
    * @param modifiedDate the modified date of this usergroup domain master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _usergroupDomainMaster.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _usergroupDomainMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _usergroupDomainMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _usergroupDomainMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _usergroupDomainMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _usergroupDomainMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _usergroupDomainMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _usergroupDomainMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _usergroupDomainMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _usergroupDomainMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _usergroupDomainMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _usergroupDomainMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new UsergroupDomainMasterWrapper((UsergroupDomainMaster) _usergroupDomainMaster.clone());
    }

    @Override
    public int compareTo(UsergroupDomainMaster usergroupDomainMaster) {
        return _usergroupDomainMaster.compareTo(usergroupDomainMaster);
    }

    @Override
    public int hashCode() {
        return _usergroupDomainMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<UsergroupDomainMaster> toCacheModel() {
        return _usergroupDomainMaster.toCacheModel();
    }

    @Override
    public UsergroupDomainMaster toEscapedModel() {
        return new UsergroupDomainMasterWrapper(_usergroupDomainMaster.toEscapedModel());
    }

    @Override
    public UsergroupDomainMaster toUnescapedModel() {
        return new UsergroupDomainMasterWrapper(_usergroupDomainMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _usergroupDomainMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _usergroupDomainMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _usergroupDomainMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof UsergroupDomainMasterWrapper)) {
            return false;
        }

        UsergroupDomainMasterWrapper usergroupDomainMasterWrapper = (UsergroupDomainMasterWrapper) obj;

        if (Validator.equals(_usergroupDomainMaster,
                    usergroupDomainMasterWrapper._usergroupDomainMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public UsergroupDomainMaster getWrappedUsergroupDomainMaster() {
        return _usergroupDomainMaster;
    }

    @Override
    public UsergroupDomainMaster getWrappedModel() {
        return _usergroupDomainMaster;
    }

    @Override
    public void resetOriginalValues() {
        _usergroupDomainMaster.resetOriginalValues();
    }
}
