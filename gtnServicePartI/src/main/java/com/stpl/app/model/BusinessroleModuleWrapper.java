package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BusinessroleModule}.
 * </p>
 *
 * @author
 * @see BusinessroleModule
 * @generated
 */
public class BusinessroleModuleWrapper implements BusinessroleModule,
    ModelWrapper<BusinessroleModule> {
    private BusinessroleModule _businessroleModule;

    public BusinessroleModuleWrapper(BusinessroleModule businessroleModule) {
        _businessroleModule = businessroleModule;
    }

    @Override
    public Class<?> getModelClass() {
        return BusinessroleModule.class;
    }

    @Override
    public String getModelClassName() {
        return BusinessroleModule.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("businessroleModuleSid", getBusinessroleModuleSid());
        attributes.put("businessroleMasterSid", getBusinessroleMasterSid());
        attributes.put("addFlag", getAddFlag());
        attributes.put("viewFlag", getViewFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("submodulePropertyId", getSubmodulePropertyId());
        attributes.put("editFlag", getEditFlag());
        attributes.put("versionNo", getVersionNo());
        attributes.put("accessModule", getAccessModule());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer businessroleModuleSid = (Integer) attributes.get(
                "businessroleModuleSid");

        if (businessroleModuleSid != null) {
            setBusinessroleModuleSid(businessroleModuleSid);
        }

        Integer businessroleMasterSid = (Integer) attributes.get(
                "businessroleMasterSid");

        if (businessroleMasterSid != null) {
            setBusinessroleMasterSid(businessroleMasterSid);
        }

        String addFlag = (String) attributes.get("addFlag");

        if (addFlag != null) {
            setAddFlag(addFlag);
        }

        String viewFlag = (String) attributes.get("viewFlag");

        if (viewFlag != null) {
            setViewFlag(viewFlag);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer submodulePropertyId = (Integer) attributes.get(
                "submodulePropertyId");

        if (submodulePropertyId != null) {
            setSubmodulePropertyId(submodulePropertyId);
        }

        String editFlag = (String) attributes.get("editFlag");

        if (editFlag != null) {
            setEditFlag(editFlag);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        String accessModule = (String) attributes.get("accessModule");

        if (accessModule != null) {
            setAccessModule(accessModule);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this businessrole module.
    *
    * @return the primary key of this businessrole module
    */
    @Override
    public int getPrimaryKey() {
        return _businessroleModule.getPrimaryKey();
    }

    /**
    * Sets the primary key of this businessrole module.
    *
    * @param primaryKey the primary key of this businessrole module
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _businessroleModule.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this businessrole module.
    *
    * @return the created by of this businessrole module
    */
    @Override
    public int getCreatedBy() {
        return _businessroleModule.getCreatedBy();
    }

    /**
    * Sets the created by of this businessrole module.
    *
    * @param createdBy the created by of this businessrole module
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _businessroleModule.setCreatedBy(createdBy);
    }

    /**
    * Returns the businessrole module sid of this businessrole module.
    *
    * @return the businessrole module sid of this businessrole module
    */
    @Override
    public int getBusinessroleModuleSid() {
        return _businessroleModule.getBusinessroleModuleSid();
    }

    /**
    * Sets the businessrole module sid of this businessrole module.
    *
    * @param businessroleModuleSid the businessrole module sid of this businessrole module
    */
    @Override
    public void setBusinessroleModuleSid(int businessroleModuleSid) {
        _businessroleModule.setBusinessroleModuleSid(businessroleModuleSid);
    }

    /**
    * Returns the businessrole master sid of this businessrole module.
    *
    * @return the businessrole master sid of this businessrole module
    */
    @Override
    public int getBusinessroleMasterSid() {
        return _businessroleModule.getBusinessroleMasterSid();
    }

    /**
    * Sets the businessrole master sid of this businessrole module.
    *
    * @param businessroleMasterSid the businessrole master sid of this businessrole module
    */
    @Override
    public void setBusinessroleMasterSid(int businessroleMasterSid) {
        _businessroleModule.setBusinessroleMasterSid(businessroleMasterSid);
    }

    /**
    * Returns the add flag of this businessrole module.
    *
    * @return the add flag of this businessrole module
    */
    @Override
    public java.lang.String getAddFlag() {
        return _businessroleModule.getAddFlag();
    }

    /**
    * Sets the add flag of this businessrole module.
    *
    * @param addFlag the add flag of this businessrole module
    */
    @Override
    public void setAddFlag(java.lang.String addFlag) {
        _businessroleModule.setAddFlag(addFlag);
    }

    /**
    * Returns the view flag of this businessrole module.
    *
    * @return the view flag of this businessrole module
    */
    @Override
    public java.lang.String getViewFlag() {
        return _businessroleModule.getViewFlag();
    }

    /**
    * Sets the view flag of this businessrole module.
    *
    * @param viewFlag the view flag of this businessrole module
    */
    @Override
    public void setViewFlag(java.lang.String viewFlag) {
        _businessroleModule.setViewFlag(viewFlag);
    }

    /**
    * Returns the modified by of this businessrole module.
    *
    * @return the modified by of this businessrole module
    */
    @Override
    public int getModifiedBy() {
        return _businessroleModule.getModifiedBy();
    }

    /**
    * Sets the modified by of this businessrole module.
    *
    * @param modifiedBy the modified by of this businessrole module
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _businessroleModule.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this businessrole module.
    *
    * @return the created date of this businessrole module
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _businessroleModule.getCreatedDate();
    }

    /**
    * Sets the created date of this businessrole module.
    *
    * @param createdDate the created date of this businessrole module
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _businessroleModule.setCreatedDate(createdDate);
    }

    /**
    * Returns the submodule property ID of this businessrole module.
    *
    * @return the submodule property ID of this businessrole module
    */
    @Override
    public int getSubmodulePropertyId() {
        return _businessroleModule.getSubmodulePropertyId();
    }

    /**
    * Sets the submodule property ID of this businessrole module.
    *
    * @param submodulePropertyId the submodule property ID of this businessrole module
    */
    @Override
    public void setSubmodulePropertyId(int submodulePropertyId) {
        _businessroleModule.setSubmodulePropertyId(submodulePropertyId);
    }

    /**
    * Returns the edit flag of this businessrole module.
    *
    * @return the edit flag of this businessrole module
    */
    @Override
    public java.lang.String getEditFlag() {
        return _businessroleModule.getEditFlag();
    }

    /**
    * Sets the edit flag of this businessrole module.
    *
    * @param editFlag the edit flag of this businessrole module
    */
    @Override
    public void setEditFlag(java.lang.String editFlag) {
        _businessroleModule.setEditFlag(editFlag);
    }

    /**
    * Returns the version no of this businessrole module.
    *
    * @return the version no of this businessrole module
    */
    @Override
    public int getVersionNo() {
        return _businessroleModule.getVersionNo();
    }

    /**
    * Sets the version no of this businessrole module.
    *
    * @param versionNo the version no of this businessrole module
    */
    @Override
    public void setVersionNo(int versionNo) {
        _businessroleModule.setVersionNo(versionNo);
    }

    /**
    * Returns the access module of this businessrole module.
    *
    * @return the access module of this businessrole module
    */
    @Override
    public java.lang.String getAccessModule() {
        return _businessroleModule.getAccessModule();
    }

    /**
    * Sets the access module of this businessrole module.
    *
    * @param accessModule the access module of this businessrole module
    */
    @Override
    public void setAccessModule(java.lang.String accessModule) {
        _businessroleModule.setAccessModule(accessModule);
    }

    /**
    * Returns the modified date of this businessrole module.
    *
    * @return the modified date of this businessrole module
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _businessroleModule.getModifiedDate();
    }

    /**
    * Sets the modified date of this businessrole module.
    *
    * @param modifiedDate the modified date of this businessrole module
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _businessroleModule.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _businessroleModule.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _businessroleModule.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _businessroleModule.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _businessroleModule.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _businessroleModule.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _businessroleModule.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _businessroleModule.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _businessroleModule.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _businessroleModule.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _businessroleModule.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _businessroleModule.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BusinessroleModuleWrapper((BusinessroleModule) _businessroleModule.clone());
    }

    @Override
    public int compareTo(BusinessroleModule businessroleModule) {
        return _businessroleModule.compareTo(businessroleModule);
    }

    @Override
    public int hashCode() {
        return _businessroleModule.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<BusinessroleModule> toCacheModel() {
        return _businessroleModule.toCacheModel();
    }

    @Override
    public BusinessroleModule toEscapedModel() {
        return new BusinessroleModuleWrapper(_businessroleModule.toEscapedModel());
    }

    @Override
    public BusinessroleModule toUnescapedModel() {
        return new BusinessroleModuleWrapper(_businessroleModule.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _businessroleModule.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _businessroleModule.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _businessroleModule.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BusinessroleModuleWrapper)) {
            return false;
        }

        BusinessroleModuleWrapper businessroleModuleWrapper = (BusinessroleModuleWrapper) obj;

        if (Validator.equals(_businessroleModule,
                    businessroleModuleWrapper._businessroleModule)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BusinessroleModule getWrappedBusinessroleModule() {
        return _businessroleModule;
    }

    @Override
    public BusinessroleModule getWrappedModel() {
        return _businessroleModule;
    }

    @Override
    public void resetOriginalValues() {
        _businessroleModule.resetOriginalValues();
    }
}
