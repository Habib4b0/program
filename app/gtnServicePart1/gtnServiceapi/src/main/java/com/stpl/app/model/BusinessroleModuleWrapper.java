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
 * This class is a wrapper for {@link BusinessroleModule}.
 * </p>
 *
 * @author
 * @see BusinessroleModule
 * @generated
 */
@ProviderType
public class BusinessroleModuleWrapper implements BusinessroleModule,
	ModelWrapper<BusinessroleModule> {
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
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer businessroleModuleSid = (Integer)attributes.get(
				"businessroleModuleSid");

		if (businessroleModuleSid != null) {
			setBusinessroleModuleSid(businessroleModuleSid);
		}

		Integer businessroleMasterSid = (Integer)attributes.get(
				"businessroleMasterSid");

		if (businessroleMasterSid != null) {
			setBusinessroleMasterSid(businessroleMasterSid);
		}

		String addFlag = (String)attributes.get("addFlag");

		if (addFlag != null) {
			setAddFlag(addFlag);
		}

		String viewFlag = (String)attributes.get("viewFlag");

		if (viewFlag != null) {
			setViewFlag(viewFlag);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer submodulePropertyId = (Integer)attributes.get(
				"submodulePropertyId");

		if (submodulePropertyId != null) {
			setSubmodulePropertyId(submodulePropertyId);
		}

		String editFlag = (String)attributes.get("editFlag");

		if (editFlag != null) {
			setEditFlag(editFlag);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String accessModule = (String)attributes.get("accessModule");

		if (accessModule != null) {
			setAccessModule(accessModule);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BusinessroleModuleWrapper((BusinessroleModule)_businessroleModule.clone());
	}

	@Override
	public int compareTo(BusinessroleModule businessroleModule) {
		return _businessroleModule.compareTo(businessroleModule);
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
	* Returns the add flag of this businessrole module.
	*
	* @return the add flag of this businessrole module
	*/
	@Override
	public java.lang.String getAddFlag() {
		return _businessroleModule.getAddFlag();
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
	* Returns the businessrole module sid of this businessrole module.
	*
	* @return the businessrole module sid of this businessrole module
	*/
	@Override
	public int getBusinessroleModuleSid() {
		return _businessroleModule.getBusinessroleModuleSid();
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
	* Returns the created date of this businessrole module.
	*
	* @return the created date of this businessrole module
	*/
	@Override
	public Date getCreatedDate() {
		return _businessroleModule.getCreatedDate();
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _businessroleModule.getExpandoBridge();
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
	* Returns the modified date of this businessrole module.
	*
	* @return the modified date of this businessrole module
	*/
	@Override
	public Date getModifiedDate() {
		return _businessroleModule.getModifiedDate();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _businessroleModule.getPrimaryKeyObj();
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
	* Returns the version no of this businessrole module.
	*
	* @return the version no of this businessrole module
	*/
	@Override
	public int getVersionNo() {
		return _businessroleModule.getVersionNo();
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

	@Override
	public int hashCode() {
		return _businessroleModule.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _businessroleModule.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _businessroleModule.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _businessroleModule.isNew();
	}

	@Override
	public void persist() {
		_businessroleModule.persist();
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
	* Sets the add flag of this businessrole module.
	*
	* @param addFlag the add flag of this businessrole module
	*/
	@Override
	public void setAddFlag(java.lang.String addFlag) {
		_businessroleModule.setAddFlag(addFlag);
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
	* Sets the businessrole module sid of this businessrole module.
	*
	* @param businessroleModuleSid the businessrole module sid of this businessrole module
	*/
	@Override
	public void setBusinessroleModuleSid(int businessroleModuleSid) {
		_businessroleModule.setBusinessroleModuleSid(businessroleModuleSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_businessroleModule.setCachedModel(cachedModel);
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
	* Sets the created date of this businessrole module.
	*
	* @param createdDate the created date of this businessrole module
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_businessroleModule.setCreatedDate(createdDate);
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

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_businessroleModule.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_businessroleModule.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_businessroleModule.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified date of this businessrole module.
	*
	* @param modifiedDate the modified date of this businessrole module
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_businessroleModule.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_businessroleModule.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_businessroleModule.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the version no of this businessrole module.
	*
	* @param versionNo the version no of this businessrole module
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_businessroleModule.setVersionNo(versionNo);
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

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BusinessroleModule> toCacheModel() {
		return _businessroleModule.toCacheModel();
	}

	@Override
	public BusinessroleModule toEscapedModel() {
		return new BusinessroleModuleWrapper(_businessroleModule.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _businessroleModule.toString();
	}

	@Override
	public BusinessroleModule toUnescapedModel() {
		return new BusinessroleModuleWrapper(_businessroleModule.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _businessroleModule.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BusinessroleModuleWrapper)) {
			return false;
		}

		BusinessroleModuleWrapper businessroleModuleWrapper = (BusinessroleModuleWrapper)obj;

		if (Objects.equals(_businessroleModule,
					businessroleModuleWrapper._businessroleModule)) {
			return true;
		}

		return false;
	}

	@Override
	public BusinessroleModule getWrappedModel() {
		return _businessroleModule;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _businessroleModule.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _businessroleModule.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_businessroleModule.resetOriginalValues();
	}

	private final BusinessroleModule _businessroleModule;
}