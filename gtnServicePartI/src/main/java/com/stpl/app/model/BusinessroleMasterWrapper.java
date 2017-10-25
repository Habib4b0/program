package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BusinessroleMaster}.
 * </p>
 *
 * @author
 * @see BusinessroleMaster
 * @generated
 */
public class BusinessroleMasterWrapper implements BusinessroleMaster,
    ModelWrapper<BusinessroleMaster> {
    private BusinessroleMaster _businessroleMaster;

    public BusinessroleMasterWrapper(BusinessroleMaster businessroleMaster) {
        _businessroleMaster = businessroleMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return BusinessroleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return BusinessroleMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("hierarchyLevel", getHierarchyLevel());
        attributes.put("processed", getProcessed());
        attributes.put("businessroleName", getBusinessroleName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("businessroleDesc", getBusinessroleDesc());
        attributes.put("isActive", getIsActive());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        Integer hierarchyLevel = (Integer) attributes.get("hierarchyLevel");

        if (hierarchyLevel != null) {
            setHierarchyLevel(hierarchyLevel);
        }

        String processed = (String) attributes.get("processed");

        if (processed != null) {
            setProcessed(processed);
        }

        String businessroleName = (String) attributes.get("businessroleName");

        if (businessroleName != null) {
            setBusinessroleName(businessroleName);
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

        String businessroleDesc = (String) attributes.get("businessroleDesc");

        if (businessroleDesc != null) {
            setBusinessroleDesc(businessroleDesc);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }
    }

    /**
    * Returns the primary key of this businessrole master.
    *
    * @return the primary key of this businessrole master
    */
    @Override
    public int getPrimaryKey() {
        return _businessroleMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this businessrole master.
    *
    * @param primaryKey the primary key of this businessrole master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _businessroleMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the businessrole master sid of this businessrole master.
    *
    * @return the businessrole master sid of this businessrole master
    */
    @Override
    public int getBusinessroleMasterSid() {
        return _businessroleMaster.getBusinessroleMasterSid();
    }

    /**
    * Sets the businessrole master sid of this businessrole master.
    *
    * @param businessroleMasterSid the businessrole master sid of this businessrole master
    */
    @Override
    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleMaster.setBusinessroleMasterSid(businessroleMasterSid);
    }

    /**
    * Returns the created date of this businessrole master.
    *
    * @return the created date of this businessrole master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _businessroleMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this businessrole master.
    *
    * @param createdDate the created date of this businessrole master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _businessroleMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this businessrole master.
    *
    * @return the created by of this businessrole master
    */
    @Override
    public int getCreatedBy() {
        return _businessroleMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this businessrole master.
    *
    * @param createdBy the created by of this businessrole master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _businessroleMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the users sid of this businessrole master.
    *
    * @return the users sid of this businessrole master
    */
    @Override
    public int getUsersSid() {
        return _businessroleMaster.getUsersSid();
    }

    /**
    * Sets the users sid of this businessrole master.
    *
    * @param usersSid the users sid of this businessrole master
    */
    @Override
    public void setUsersSid(int usersSid) {
        _businessroleMaster.setUsersSid(usersSid);
    }

    /**
    * Returns the hierarchy level of this businessrole master.
    *
    * @return the hierarchy level of this businessrole master
    */
    @Override
    public int getHierarchyLevel() {
        return _businessroleMaster.getHierarchyLevel();
    }

    /**
    * Sets the hierarchy level of this businessrole master.
    *
    * @param hierarchyLevel the hierarchy level of this businessrole master
    */
    @Override
    public void setHierarchyLevel(int hierarchyLevel) {
        _businessroleMaster.setHierarchyLevel(hierarchyLevel);
    }

    /**
    * Returns the processed of this businessrole master.
    *
    * @return the processed of this businessrole master
    */
    @Override
    public java.lang.String getProcessed() {
        return _businessroleMaster.getProcessed();
    }

    /**
    * Sets the processed of this businessrole master.
    *
    * @param processed the processed of this businessrole master
    */
    @Override
    public void setProcessed(java.lang.String processed) {
        _businessroleMaster.setProcessed(processed);
    }

    /**
    * Returns the businessrole name of this businessrole master.
    *
    * @return the businessrole name of this businessrole master
    */
    @Override
    public java.lang.String getBusinessroleName() {
        return _businessroleMaster.getBusinessroleName();
    }

    /**
    * Sets the businessrole name of this businessrole master.
    *
    * @param businessroleName the businessrole name of this businessrole master
    */
    @Override
    public void setBusinessroleName(java.lang.String businessroleName) {
        _businessroleMaster.setBusinessroleName(businessroleName);
    }

    /**
    * Returns the version no of this businessrole master.
    *
    * @return the version no of this businessrole master
    */
    @Override
    public int getVersionNo() {
        return _businessroleMaster.getVersionNo();
    }

    /**
    * Sets the version no of this businessrole master.
    *
    * @param versionNo the version no of this businessrole master
    */
    @Override
    public void setVersionNo(int versionNo) {
        _businessroleMaster.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this businessrole master.
    *
    * @return the modified by of this businessrole master
    */
    @Override
    public int getModifiedBy() {
        return _businessroleMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this businessrole master.
    *
    * @param modifiedBy the modified by of this businessrole master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _businessroleMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this businessrole master.
    *
    * @return the modified date of this businessrole master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _businessroleMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this businessrole master.
    *
    * @param modifiedDate the modified date of this businessrole master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _businessroleMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the businessrole desc of this businessrole master.
    *
    * @return the businessrole desc of this businessrole master
    */
    @Override
    public java.lang.String getBusinessroleDesc() {
        return _businessroleMaster.getBusinessroleDesc();
    }

    /**
    * Sets the businessrole desc of this businessrole master.
    *
    * @param businessroleDesc the businessrole desc of this businessrole master
    */
    @Override
    public void setBusinessroleDesc(java.lang.String businessroleDesc) {
        _businessroleMaster.setBusinessroleDesc(businessroleDesc);
    }

    /**
    * Returns the is active of this businessrole master.
    *
    * @return the is active of this businessrole master
    */
    @Override
    public java.lang.String getIsActive() {
        return _businessroleMaster.getIsActive();
    }

    /**
    * Sets the is active of this businessrole master.
    *
    * @param isActive the is active of this businessrole master
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _businessroleMaster.setIsActive(isActive);
    }

    @Override
    public boolean isNew() {
        return _businessroleMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _businessroleMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _businessroleMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _businessroleMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _businessroleMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _businessroleMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _businessroleMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _businessroleMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _businessroleMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _businessroleMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _businessroleMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BusinessroleMasterWrapper((BusinessroleMaster) _businessroleMaster.clone());
    }

    @Override
    public int compareTo(BusinessroleMaster businessroleMaster) {
        return _businessroleMaster.compareTo(businessroleMaster);
    }

    @Override
    public int hashCode() {
        return _businessroleMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<BusinessroleMaster> toCacheModel() {
        return _businessroleMaster.toCacheModel();
    }

    @Override
    public BusinessroleMaster toEscapedModel() {
        return new BusinessroleMasterWrapper(_businessroleMaster.toEscapedModel());
    }

    @Override
    public BusinessroleMaster toUnescapedModel() {
        return new BusinessroleMasterWrapper(_businessroleMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _businessroleMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _businessroleMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _businessroleMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BusinessroleMasterWrapper)) {
            return false;
        }

        BusinessroleMasterWrapper businessroleMasterWrapper = (BusinessroleMasterWrapper) obj;

        if (Validator.equals(_businessroleMaster,
                    businessroleMasterWrapper._businessroleMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BusinessroleMaster getWrappedBusinessroleMaster() {
        return _businessroleMaster;
    }

    @Override
    public BusinessroleMaster getWrappedModel() {
        return _businessroleMaster;
    }

    @Override
    public void resetOriginalValues() {
        _businessroleMaster.resetOriginalValues();
    }
}
