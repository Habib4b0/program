package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModuleSubmoduleMaster}.
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMaster
 * @generated
 */
public class ModuleSubmoduleMasterWrapper implements ModuleSubmoduleMaster,
    ModelWrapper<ModuleSubmoduleMaster> {
    private ModuleSubmoduleMaster _moduleSubmoduleMaster;

    public ModuleSubmoduleMasterWrapper(
        ModuleSubmoduleMaster moduleSubmoduleMaster) {
        _moduleSubmoduleMaster = moduleSubmoduleMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return ModuleSubmoduleMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ModuleSubmoduleMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("category", getCategory());
        attributes.put("moduleSubmoduleSid", getModuleSubmoduleSid());
        attributes.put("submoduleName", getSubmoduleName());
        attributes.put("moduleName", getModuleName());
        attributes.put("versionNo", getVersionNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());

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

        String category = (String) attributes.get("category");

        if (category != null) {
            setCategory(category);
        }

        Integer moduleSubmoduleSid = (Integer) attributes.get(
                "moduleSubmoduleSid");

        if (moduleSubmoduleSid != null) {
            setModuleSubmoduleSid(moduleSubmoduleSid);
        }

        String submoduleName = (String) attributes.get("submoduleName");

        if (submoduleName != null) {
            setSubmoduleName(submoduleName);
        }

        String moduleName = (String) attributes.get("moduleName");

        if (moduleName != null) {
            setModuleName(moduleName);
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
    }

    /**
    * Returns the primary key of this module submodule master.
    *
    * @return the primary key of this module submodule master
    */
    @Override
    public int getPrimaryKey() {
        return _moduleSubmoduleMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this module submodule master.
    *
    * @param primaryKey the primary key of this module submodule master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _moduleSubmoduleMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created date of this module submodule master.
    *
    * @return the created date of this module submodule master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _moduleSubmoduleMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this module submodule master.
    *
    * @param createdDate the created date of this module submodule master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _moduleSubmoduleMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this module submodule master.
    *
    * @return the created by of this module submodule master
    */
    @Override
    public int getCreatedBy() {
        return _moduleSubmoduleMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this module submodule master.
    *
    * @param createdBy the created by of this module submodule master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _moduleSubmoduleMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the category of this module submodule master.
    *
    * @return the category of this module submodule master
    */
    @Override
    public java.lang.String getCategory() {
        return _moduleSubmoduleMaster.getCategory();
    }

    /**
    * Sets the category of this module submodule master.
    *
    * @param category the category of this module submodule master
    */
    @Override
    public void setCategory(java.lang.String category) {
        _moduleSubmoduleMaster.setCategory(category);
    }

    /**
    * Returns the module submodule sid of this module submodule master.
    *
    * @return the module submodule sid of this module submodule master
    */
    @Override
    public int getModuleSubmoduleSid() {
        return _moduleSubmoduleMaster.getModuleSubmoduleSid();
    }

    /**
    * Sets the module submodule sid of this module submodule master.
    *
    * @param moduleSubmoduleSid the module submodule sid of this module submodule master
    */
    @Override
    public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
        _moduleSubmoduleMaster.setModuleSubmoduleSid(moduleSubmoduleSid);
    }

    /**
    * Returns the submodule name of this module submodule master.
    *
    * @return the submodule name of this module submodule master
    */
    @Override
    public java.lang.String getSubmoduleName() {
        return _moduleSubmoduleMaster.getSubmoduleName();
    }

    /**
    * Sets the submodule name of this module submodule master.
    *
    * @param submoduleName the submodule name of this module submodule master
    */
    @Override
    public void setSubmoduleName(java.lang.String submoduleName) {
        _moduleSubmoduleMaster.setSubmoduleName(submoduleName);
    }

    /**
    * Returns the module name of this module submodule master.
    *
    * @return the module name of this module submodule master
    */
    @Override
    public java.lang.String getModuleName() {
        return _moduleSubmoduleMaster.getModuleName();
    }

    /**
    * Sets the module name of this module submodule master.
    *
    * @param moduleName the module name of this module submodule master
    */
    @Override
    public void setModuleName(java.lang.String moduleName) {
        _moduleSubmoduleMaster.setModuleName(moduleName);
    }

    /**
    * Returns the version no of this module submodule master.
    *
    * @return the version no of this module submodule master
    */
    @Override
    public int getVersionNo() {
        return _moduleSubmoduleMaster.getVersionNo();
    }

    /**
    * Sets the version no of this module submodule master.
    *
    * @param versionNo the version no of this module submodule master
    */
    @Override
    public void setVersionNo(int versionNo) {
        _moduleSubmoduleMaster.setVersionNo(versionNo);
    }

    /**
    * Returns the modified by of this module submodule master.
    *
    * @return the modified by of this module submodule master
    */
    @Override
    public int getModifiedBy() {
        return _moduleSubmoduleMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this module submodule master.
    *
    * @param modifiedBy the modified by of this module submodule master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _moduleSubmoduleMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this module submodule master.
    *
    * @return the modified date of this module submodule master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _moduleSubmoduleMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this module submodule master.
    *
    * @param modifiedDate the modified date of this module submodule master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _moduleSubmoduleMaster.setModifiedDate(modifiedDate);
    }

    @Override
    public boolean isNew() {
        return _moduleSubmoduleMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _moduleSubmoduleMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _moduleSubmoduleMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _moduleSubmoduleMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _moduleSubmoduleMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _moduleSubmoduleMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _moduleSubmoduleMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _moduleSubmoduleMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _moduleSubmoduleMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _moduleSubmoduleMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _moduleSubmoduleMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModuleSubmoduleMasterWrapper((ModuleSubmoduleMaster) _moduleSubmoduleMaster.clone());
    }

    @Override
    public int compareTo(ModuleSubmoduleMaster moduleSubmoduleMaster) {
        return _moduleSubmoduleMaster.compareTo(moduleSubmoduleMaster);
    }

    @Override
    public int hashCode() {
        return _moduleSubmoduleMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ModuleSubmoduleMaster> toCacheModel() {
        return _moduleSubmoduleMaster.toCacheModel();
    }

    @Override
    public ModuleSubmoduleMaster toEscapedModel() {
        return new ModuleSubmoduleMasterWrapper(_moduleSubmoduleMaster.toEscapedModel());
    }

    @Override
    public ModuleSubmoduleMaster toUnescapedModel() {
        return new ModuleSubmoduleMasterWrapper(_moduleSubmoduleMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _moduleSubmoduleMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _moduleSubmoduleMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _moduleSubmoduleMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModuleSubmoduleMasterWrapper)) {
            return false;
        }

        ModuleSubmoduleMasterWrapper moduleSubmoduleMasterWrapper = (ModuleSubmoduleMasterWrapper) obj;

        if (Validator.equals(_moduleSubmoduleMaster,
                    moduleSubmoduleMasterWrapper._moduleSubmoduleMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModuleSubmoduleMaster getWrappedModuleSubmoduleMaster() {
        return _moduleSubmoduleMaster;
    }

    @Override
    public ModuleSubmoduleMaster getWrappedModel() {
        return _moduleSubmoduleMaster;
    }

    @Override
    public void resetOriginalValues() {
        _moduleSubmoduleMaster.resetOriginalValues();
    }
}
