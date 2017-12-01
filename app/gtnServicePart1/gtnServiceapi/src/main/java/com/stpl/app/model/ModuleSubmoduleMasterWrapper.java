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
 * This class is a wrapper for {@link ModuleSubmoduleMaster}.
 * </p>
 *
 * @author
 * @see ModuleSubmoduleMaster
 * @generated
 */
@ProviderType
public class ModuleSubmoduleMasterWrapper implements ModuleSubmoduleMaster,
	ModelWrapper<ModuleSubmoduleMaster> {
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
		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String category = (String)attributes.get("category");

		if (category != null) {
			setCategory(category);
		}

		Integer moduleSubmoduleSid = (Integer)attributes.get(
				"moduleSubmoduleSid");

		if (moduleSubmoduleSid != null) {
			setModuleSubmoduleSid(moduleSubmoduleSid);
		}

		String submoduleName = (String)attributes.get("submoduleName");

		if (submoduleName != null) {
			setSubmoduleName(submoduleName);
		}

		String moduleName = (String)attributes.get("moduleName");

		if (moduleName != null) {
			setModuleName(moduleName);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new ModuleSubmoduleMasterWrapper((ModuleSubmoduleMaster)_moduleSubmoduleMaster.clone());
	}

	@Override
	public int compareTo(ModuleSubmoduleMaster moduleSubmoduleMaster) {
		return _moduleSubmoduleMaster.compareTo(moduleSubmoduleMaster);
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
	* Returns the created by of this module submodule master.
	*
	* @return the created by of this module submodule master
	*/
	@Override
	public int getCreatedBy() {
		return _moduleSubmoduleMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this module submodule master.
	*
	* @return the created date of this module submodule master
	*/
	@Override
	public Date getCreatedDate() {
		return _moduleSubmoduleMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _moduleSubmoduleMaster.getExpandoBridge();
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
	* Returns the modified date of this module submodule master.
	*
	* @return the modified date of this module submodule master
	*/
	@Override
	public Date getModifiedDate() {
		return _moduleSubmoduleMaster.getModifiedDate();
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
	* Returns the module submodule sid of this module submodule master.
	*
	* @return the module submodule sid of this module submodule master
	*/
	@Override
	public int getModuleSubmoduleSid() {
		return _moduleSubmoduleMaster.getModuleSubmoduleSid();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _moduleSubmoduleMaster.getPrimaryKeyObj();
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
	* Returns the version no of this module submodule master.
	*
	* @return the version no of this module submodule master
	*/
	@Override
	public int getVersionNo() {
		return _moduleSubmoduleMaster.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _moduleSubmoduleMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _moduleSubmoduleMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _moduleSubmoduleMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _moduleSubmoduleMaster.isNew();
	}

	@Override
	public void persist() {
		_moduleSubmoduleMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_moduleSubmoduleMaster.setCachedModel(cachedModel);
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
	* Sets the created by of this module submodule master.
	*
	* @param createdBy the created by of this module submodule master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_moduleSubmoduleMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this module submodule master.
	*
	* @param createdDate the created date of this module submodule master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_moduleSubmoduleMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_moduleSubmoduleMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_moduleSubmoduleMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_moduleSubmoduleMaster.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified date of this module submodule master.
	*
	* @param modifiedDate the modified date of this module submodule master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_moduleSubmoduleMaster.setModifiedDate(modifiedDate);
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
	* Sets the module submodule sid of this module submodule master.
	*
	* @param moduleSubmoduleSid the module submodule sid of this module submodule master
	*/
	@Override
	public void setModuleSubmoduleSid(int moduleSubmoduleSid) {
		_moduleSubmoduleMaster.setModuleSubmoduleSid(moduleSubmoduleSid);
	}

	@Override
	public void setNew(boolean n) {
		_moduleSubmoduleMaster.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_moduleSubmoduleMaster.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the version no of this module submodule master.
	*
	* @param versionNo the version no of this module submodule master
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_moduleSubmoduleMaster.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ModuleSubmoduleMaster> toCacheModel() {
		return _moduleSubmoduleMaster.toCacheModel();
	}

	@Override
	public ModuleSubmoduleMaster toEscapedModel() {
		return new ModuleSubmoduleMasterWrapper(_moduleSubmoduleMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _moduleSubmoduleMaster.toString();
	}

	@Override
	public ModuleSubmoduleMaster toUnescapedModel() {
		return new ModuleSubmoduleMasterWrapper(_moduleSubmoduleMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _moduleSubmoduleMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ModuleSubmoduleMasterWrapper)) {
			return false;
		}

		ModuleSubmoduleMasterWrapper moduleSubmoduleMasterWrapper = (ModuleSubmoduleMasterWrapper)obj;

		if (Objects.equals(_moduleSubmoduleMaster,
					moduleSubmoduleMasterWrapper._moduleSubmoduleMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ModuleSubmoduleMaster getWrappedModel() {
		return _moduleSubmoduleMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _moduleSubmoduleMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _moduleSubmoduleMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_moduleSubmoduleMaster.resetOriginalValues();
	}

	private final ModuleSubmoduleMaster _moduleSubmoduleMaster;
}