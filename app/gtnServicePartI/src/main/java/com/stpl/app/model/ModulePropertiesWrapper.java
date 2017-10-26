package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModuleProperties}.
 * </p>
 *
 * @author
 * @see ModuleProperties
 * @generated
 */
public class ModulePropertiesWrapper implements ModuleProperties,
    ModelWrapper<ModuleProperties> {
    private ModuleProperties _moduleProperties;

    public ModulePropertiesWrapper(ModuleProperties moduleProperties) {
        _moduleProperties = moduleProperties;
    }

    @Override
    public Class<?> getModelClass() {
        return ModuleProperties.class;
    }

    @Override
    public String getModelClassName() {
        return ModuleProperties.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modulePropertySid", getModulePropertySid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("moduleName", getModuleName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("nullFlag", getNullFlag());
        attributes.put("versionNo", getVersionNo());
        attributes.put("moduleSubmoduleSid", getModuleSubmoduleSid());
        attributes.put("categoryName", getCategoryName());
        attributes.put("propertyName", getPropertyName());
        attributes.put("displayName", getDisplayName());
        attributes.put("modifiedDate", getModifiedDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer modulePropertySid = (Integer) attributes.get(
                "modulePropertySid");

        if (modulePropertySid != null) {
            setModulePropertySid(modulePropertySid);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String nullFlag = (String) attributes.get("nullFlag");

        if (nullFlag != null) {
            setNullFlag(nullFlag);
        }

        Integer versionNo = (Integer) attributes.get("versionNo");

        if (versionNo != null) {
            setVersionNo(versionNo);
        }

        Integer moduleSubmoduleSid = (Integer) attributes.get(
                "moduleSubmoduleSid");

        if (moduleSubmoduleSid != null) {
            setModuleSubmoduleSid(moduleSubmoduleSid);
        }

        String categoryName = (String) attributes.get("categoryName");

        if (categoryName != null) {
            setCategoryName(categoryName);
        }

        String propertyName = (String) attributes.get("propertyName");

        if (propertyName != null) {
            setPropertyName(propertyName);
        }

        String displayName = (String) attributes.get("displayName");

        if (displayName != null) {
            setDisplayName(displayName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }
    }

    /**
    * Returns the primary key of this module properties.
    *
    * @return the primary key of this module properties
    */
    @Override
    public int getPrimaryKey() {
        return _moduleProperties.getPrimaryKey();
    }

    /**
    * Sets the primary key of this module properties.
    *
    * @param primaryKey the primary key of this module properties
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _moduleProperties.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the module property sid of this module properties.
    *
    * @return the module property sid of this module properties
    */
    @Override
    public int getModulePropertySid() {
        return _moduleProperties.getModulePropertySid();
    }

    /**
    * Sets the module property sid of this module properties.
    *
    * @param modulePropertySid the module property sid of this module properties
    */
    @Override
    public void setModulePropertySid(int modulePropertySid) {
        _moduleProperties.setModulePropertySid(modulePropertySid);
    }

    /**
    * Returns the created by of this module properties.
    *
    * @return the created by of this module properties
    */
    @Override
    public int getCreatedBy() {
        return _moduleProperties.getCreatedBy();
    }

    /**
    * Sets the created by of this module properties.
    *
    * @param createdBy the created by of this module properties
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _moduleProperties.setCreatedBy(createdBy);
    }

    /**
    * Returns the module name of this module properties.
    *
    * @return the module name of this module properties
    */
    @Override
    public java.lang.String getModuleName() {
        return _moduleProperties.getModuleName();
    }

    /**
    * Sets the module name of this module properties.
    *
    * @param moduleName the module name of this module properties
    */
    @Override
    public void setModuleName(java.lang.String moduleName) {
        _moduleProperties.setModuleName(moduleName);
    }

    /**
    * Returns the modified by of this module properties.
    *
    * @return the modified by of this module properties
    */
    @Override
    public int getModifiedBy() {
        return _moduleProperties.getModifiedBy();
    }

    /**
    * Sets the modified by of this module properties.
    *
    * @param modifiedBy the modified by of this module properties
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _moduleProperties.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this module properties.
    *
    * @return the created date of this module properties
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _moduleProperties.getCreatedDate();
    }

    /**
    * Sets the created date of this module properties.
    *
    * @param createdDate the created date of this module properties
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _moduleProperties.setCreatedDate(createdDate);
    }

    /**
    * Returns the null flag of this module properties.
    *
    * @return the null flag of this module properties
    */
    @Override
    public java.lang.String getNullFlag() {
        return _moduleProperties.getNullFlag();
    }

    /**
    * Sets the null flag of this module properties.
    *
    * @param nullFlag the null flag of this module properties
    */
    @Override
    public void setNullFlag(java.lang.String nullFlag) {
        _moduleProperties.setNullFlag(nullFlag);
    }

    /**
    * Returns the version no of this module properties.
    *
    * @return the version no of this module properties
    */
    @Override
    public int getVersionNo() {
        return _moduleProperties.getVersionNo();
    }

    /**
    * Sets the version no of this module properties.
    *
    * @param versionNo the version no of this module properties
    */
    @Override
    public void setVersionNo(int versionNo) {
        _moduleProperties.setVersionNo(versionNo);
    }

    /**
    * Returns the module submodule sid of this module properties.
    *
    * @return the module submodule sid of this module properties
    */
    @Override
    public int getModuleSubmoduleSid() {
        return _moduleProperties.getModuleSubmoduleSid();
    }

    /**
    * Sets the module submodule sid of this module properties.
    *
    * @param moduleSubmoduleSid the module submodule sid of this module properties
    */
    @Override
    public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
        _moduleProperties.setModuleSubmoduleSid(moduleSubmoduleSid);
    }

    /**
    * Returns the category name of this module properties.
    *
    * @return the category name of this module properties
    */
    @Override
    public java.lang.String getCategoryName() {
        return _moduleProperties.getCategoryName();
    }

    /**
    * Sets the category name of this module properties.
    *
    * @param categoryName the category name of this module properties
    */
    @Override
    public void setCategoryName(java.lang.String categoryName) {
        _moduleProperties.setCategoryName(categoryName);
    }

    /**
    * Returns the property name of this module properties.
    *
    * @return the property name of this module properties
    */
    @Override
    public java.lang.String getPropertyName() {
        return _moduleProperties.getPropertyName();
    }

    /**
    * Sets the property name of this module properties.
    *
    * @param propertyName the property name of this module properties
    */
    @Override
    public void setPropertyName(java.lang.String propertyName) {
        _moduleProperties.setPropertyName(propertyName);
    }

    /**
    * Returns the display name of this module properties.
    *
    * @return the display name of this module properties
    */
    @Override
    public java.lang.String getDisplayName() {
        return _moduleProperties.getDisplayName();
    }

    /**
    * Sets the display name of this module properties.
    *
    * @param displayName the display name of this module properties
    */
    @Override
    public void setDisplayName(java.lang.String displayName) {
        _moduleProperties.setDisplayName(displayName);
    }

    /**
    * Returns the modified date of this module properties.
    *
    * @return the modified date of this module properties
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _moduleProperties.getModifiedDate();
    }

    /**
    * Sets the modified date of this module properties.
    *
    * @param modifiedDate the modified date of this module properties
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _moduleProperties.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _moduleProperties.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _moduleProperties.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _moduleProperties.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _moduleProperties.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _moduleProperties.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _moduleProperties.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _moduleProperties.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _moduleProperties.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _moduleProperties.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _moduleProperties.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _moduleProperties.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModulePropertiesWrapper((ModuleProperties) _moduleProperties.clone());
    }

    @Override
    public int compareTo(ModuleProperties moduleProperties) {
        return _moduleProperties.compareTo(moduleProperties);
    }

    @Override
    public int hashCode() {
        return _moduleProperties.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ModuleProperties> toCacheModel() {
        return _moduleProperties.toCacheModel();
    }

    @Override
    public ModuleProperties toEscapedModel() {
        return new ModulePropertiesWrapper(_moduleProperties.toEscapedModel());
    }

    @Override
    public ModuleProperties toUnescapedModel() {
        return new ModulePropertiesWrapper(_moduleProperties.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _moduleProperties.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _moduleProperties.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _moduleProperties.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModulePropertiesWrapper)) {
            return false;
        }

        ModulePropertiesWrapper modulePropertiesWrapper = (ModulePropertiesWrapper) obj;

        if (Validator.equals(_moduleProperties,
                    modulePropertiesWrapper._moduleProperties)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModuleProperties getWrappedModuleProperties() {
        return _moduleProperties;
    }

    @Override
    public ModuleProperties getWrappedModel() {
        return _moduleProperties;
    }

    @Override
    public void resetOriginalValues() {
        _moduleProperties.resetOriginalValues();
    }
}
