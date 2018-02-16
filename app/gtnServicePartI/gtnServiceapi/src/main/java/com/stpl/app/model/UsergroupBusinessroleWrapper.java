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
 * This class is a wrapper for {@link UsergroupBusinessrole}.
 * </p>
 *
 * @author
 * @see UsergroupBusinessrole
 * @generated
 */
@ProviderType
public class UsergroupBusinessroleWrapper implements UsergroupBusinessrole,
	ModelWrapper<UsergroupBusinessrole> {
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
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer businessroleMasterSid = (Integer)attributes.get(
				"businessroleMasterSid");

		if (businessroleMasterSid != null) {
			setBusinessroleMasterSid(businessroleMasterSid);
		}

		Integer usersSid = (Integer)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String processed = (String)attributes.get("processed");

		if (processed != null) {
			setProcessed(processed);
		}

		Integer usergroupId = (Integer)attributes.get("usergroupId");

		if (usergroupId != null) {
			setUsergroupId(usergroupId);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		Integer usergroupBusinessroleSid = (Integer)attributes.get(
				"usergroupBusinessroleSid");

		if (usergroupBusinessroleSid != null) {
			setUsergroupBusinessroleSid(usergroupBusinessroleSid);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new UsergroupBusinessroleWrapper((UsergroupBusinessrole)_usergroupBusinessrole.clone());
	}

	@Override
	public int compareTo(UsergroupBusinessrole usergroupBusinessrole) {
		return _usergroupBusinessrole.compareTo(usergroupBusinessrole);
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
	* Returns the created by of this usergroup businessrole.
	*
	* @return the created by of this usergroup businessrole
	*/
	@Override
	public int getCreatedBy() {
		return _usergroupBusinessrole.getCreatedBy();
	}

	/**
	* Returns the created date of this usergroup businessrole.
	*
	* @return the created date of this usergroup businessrole
	*/
	@Override
	public Date getCreatedDate() {
		return _usergroupBusinessrole.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _usergroupBusinessrole.getExpandoBridge();
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
	* Returns the modified by of this usergroup businessrole.
	*
	* @return the modified by of this usergroup businessrole
	*/
	@Override
	public int getModifiedBy() {
		return _usergroupBusinessrole.getModifiedBy();
	}

	/**
	* Returns the modified date of this usergroup businessrole.
	*
	* @return the modified date of this usergroup businessrole
	*/
	@Override
	public Date getModifiedDate() {
		return _usergroupBusinessrole.getModifiedDate();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _usergroupBusinessrole.getPrimaryKeyObj();
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
	* Returns the usergroup businessrole sid of this usergroup businessrole.
	*
	* @return the usergroup businessrole sid of this usergroup businessrole
	*/
	@Override
	public int getUsergroupBusinessroleSid() {
		return _usergroupBusinessrole.getUsergroupBusinessroleSid();
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
	* Returns the users sid of this usergroup businessrole.
	*
	* @return the users sid of this usergroup businessrole
	*/
	@Override
	public int getUsersSid() {
		return _usergroupBusinessrole.getUsersSid();
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

	@Override
	public int hashCode() {
		return _usergroupBusinessrole.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _usergroupBusinessrole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _usergroupBusinessrole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _usergroupBusinessrole.isNew();
	}

	@Override
	public void persist() {
		_usergroupBusinessrole.persist();
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

	@Override
	public void setCachedModel(boolean cachedModel) {
		_usergroupBusinessrole.setCachedModel(cachedModel);
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
	* Sets the created date of this usergroup businessrole.
	*
	* @param createdDate the created date of this usergroup businessrole
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_usergroupBusinessrole.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_usergroupBusinessrole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_usergroupBusinessrole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_usergroupBusinessrole.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified by of this usergroup businessrole.
	*
	* @param modifiedBy the modified by of this usergroup businessrole
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_usergroupBusinessrole.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this usergroup businessrole.
	*
	* @param modifiedDate the modified date of this usergroup businessrole
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_usergroupBusinessrole.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_usergroupBusinessrole.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_usergroupBusinessrole.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the usergroup businessrole sid of this usergroup businessrole.
	*
	* @param usergroupBusinessroleSid the usergroup businessrole sid of this usergroup businessrole
	*/
	@Override
	public void setUsergroupBusinessroleSid(int usergroupBusinessroleSid) {
		_usergroupBusinessrole.setUsergroupBusinessroleSid(usergroupBusinessroleSid);
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
	* Sets the users sid of this usergroup businessrole.
	*
	* @param usersSid the users sid of this usergroup businessrole
	*/
	@Override
	public void setUsersSid(int usersSid) {
		_usergroupBusinessrole.setUsersSid(usersSid);
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

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UsergroupBusinessrole> toCacheModel() {
		return _usergroupBusinessrole.toCacheModel();
	}

	@Override
	public UsergroupBusinessrole toEscapedModel() {
		return new UsergroupBusinessroleWrapper(_usergroupBusinessrole.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _usergroupBusinessrole.toString();
	}

	@Override
	public UsergroupBusinessrole toUnescapedModel() {
		return new UsergroupBusinessroleWrapper(_usergroupBusinessrole.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _usergroupBusinessrole.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UsergroupBusinessroleWrapper)) {
			return false;
		}

		UsergroupBusinessroleWrapper usergroupBusinessroleWrapper = (UsergroupBusinessroleWrapper)obj;

		if (Objects.equals(_usergroupBusinessrole,
					usergroupBusinessroleWrapper._usergroupBusinessrole)) {
			return true;
		}

		return false;
	}

	@Override
	public UsergroupBusinessrole getWrappedModel() {
		return _usergroupBusinessrole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _usergroupBusinessrole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _usergroupBusinessrole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_usergroupBusinessrole.resetOriginalValues();
	}

	private final UsergroupBusinessrole _usergroupBusinessrole;
}