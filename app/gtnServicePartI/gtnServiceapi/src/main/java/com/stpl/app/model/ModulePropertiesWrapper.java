/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link ModuleProperties}.
 * </p>
 *
 * @author
 * @see ModuleProperties
 * @generated
 */
@ProviderType
public class ModulePropertiesWrapper implements ModuleProperties,
	ModelWrapper<ModuleProperties> {
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
		Integer modulePropertySid = (Integer)attributes.get("modulePropertySid");

		if (modulePropertySid != null) {
			setModulePropertySid(modulePropertySid);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String nullFlag = (String)attributes.get("nullFlag");

		if (nullFlag != null) {
			setNullFlag(nullFlag);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer moduleSubmoduleSid = (Integer)attributes.get(
				"moduleSubmoduleSid");

		if (moduleSubmoduleSid != null) {
			setModuleSubmoduleSid(moduleSubmoduleSid);
		}

		String categoryName = (String)attributes.get("categoryName");

		if (categoryName != null) {
			setCategoryName(categoryName);
		}

		String propertyName = (String)attributes.get("propertyName");

		if (propertyName != null) {
			setPropertyName(propertyName);
		}

		String displayName = (String)attributes.get("displayName");

		if (displayName != null) {
			setDisplayName(displayName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ModulePropertiesWrapper((ModuleProperties)_moduleProperties.clone());
	}

	@Override
	public int compareTo(ModuleProperties moduleProperties) {
		return _moduleProperties.compareTo(moduleProperties);
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
	* Returns the created by of this module properties.
	*
	* @return the created by of this module properties
	*/
	@Override
	public int getCreatedBy() {
		return _moduleProperties.getCreatedBy();
	}

	/**
	* Returns the created date of this module properties.
	*
	* @return the created date of this module properties
	*/
	@Override
	public Date getCreatedDate() {
		return _moduleProperties.getCreatedDate();
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _moduleProperties.getExpandoBridge();
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
	* Returns the modified date of this module properties.
	*
	* @return the modified date of this module properties
	*/
	@Override
	public Date getModifiedDate() {
		return _moduleProperties.getModifiedDate();
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
	* Returns the module property sid of this module properties.
	*
	* @return the module property sid of this module properties
	*/
	@Override
	public int getModulePropertySid() {
		return _moduleProperties.getModulePropertySid();
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
	* Returns the null flag of this module properties.
	*
	* @return the null flag of this module properties
	*/
	@Override
	public java.lang.String getNullFlag() {
		return _moduleProperties.getNullFlag();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleProperties.getPrimaryKeyObj();
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
	* Returns the version no of this module properties.
	*
	* @return the version no of this module properties
	*/
	@Override
	public int getVersionNo() {
		return _moduleProperties.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _moduleProperties.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _moduleProperties.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _moduleProperties.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _moduleProperties.isNew();
	}

	@Override
	public void persist() {
		_moduleProperties.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_moduleProperties.setCachedModel(cachedModel);
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
	* Sets the created by of this module properties.
	*
	* @param createdBy the created by of this module properties
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_moduleProperties.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this module properties.
	*
	* @param createdDate the created date of this module properties
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_moduleProperties.setCreatedDate(createdDate);
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

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_moduleProperties.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_moduleProperties.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_moduleProperties.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified date of this module properties.
	*
	* @param modifiedDate the modified date of this module properties
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_moduleProperties.setModifiedDate(modifiedDate);
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
	* Sets the module property sid of this module properties.
	*
	* @param modulePropertySid the module property sid of this module properties
	*/
	@Override
	public void setModulePropertySid(int modulePropertySid) {
		_moduleProperties.setModulePropertySid(modulePropertySid);
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

	@Override
	public void setNew(boolean n) {
		_moduleProperties.setNew(n);
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
	* Sets the primary key of this module properties.
	*
	* @param primaryKey the primary key of this module properties
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_moduleProperties.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_moduleProperties.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the version no of this module properties.
	*
	* @param versionNo the version no of this module properties
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_moduleProperties.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ModuleProperties> toCacheModel() {
		return _moduleProperties.toCacheModel();
	}

	@Override
	public ModuleProperties toEscapedModel() {
		return new ModulePropertiesWrapper(_moduleProperties.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _moduleProperties.toString();
	}

	@Override
	public ModuleProperties toUnescapedModel() {
		return new ModulePropertiesWrapper(_moduleProperties.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _moduleProperties.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModulePropertiesWrapper)) {
			return false;
		}

		ModulePropertiesWrapper modulePropertiesWrapper = (ModulePropertiesWrapper)obj;

		if (Objects.equals(_moduleProperties,
					modulePropertiesWrapper._moduleProperties)) {
			return true;
		}

		return false;
	}

	@Override
	public ModuleProperties getWrappedModel() {
		return _moduleProperties;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _moduleProperties.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _moduleProperties.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_moduleProperties.resetOriginalValues();
	}

	private final ModuleProperties _moduleProperties;
}