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
 * This class is a wrapper for {@link UsergroupDomainMaster}.
 * </p>
 *
 * @author
 * @see UsergroupDomainMaster
 * @generated
 */
@ProviderType
public class UsergroupDomainMasterWrapper implements UsergroupDomainMaster,
	ModelWrapper<UsergroupDomainMaster> {
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
		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer usergroupDomainSid = (Integer)attributes.get(
				"usergroupDomainSid");

		if (usergroupDomainSid != null) {
			setUsergroupDomainSid(usergroupDomainSid);
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

		Integer domainId = (Integer)attributes.get("domainId");

		if (domainId != null) {
			setDomainId(domainId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new UsergroupDomainMasterWrapper((UsergroupDomainMaster)_usergroupDomainMaster.clone());
	}

	@Override
	public int compareTo(UsergroupDomainMaster usergroupDomainMaster) {
		return _usergroupDomainMaster.compareTo(usergroupDomainMaster);
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
	* Returns the created date of this usergroup domain master.
	*
	* @return the created date of this usergroup domain master
	*/
	@Override
	public Date getCreatedDate() {
		return _usergroupDomainMaster.getCreatedDate();
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

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _usergroupDomainMaster.getExpandoBridge();
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
	* Returns the modified by of this usergroup domain master.
	*
	* @return the modified by of this usergroup domain master
	*/
	@Override
	public int getModifiedBy() {
		return _usergroupDomainMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this usergroup domain master.
	*
	* @return the modified date of this usergroup domain master
	*/
	@Override
	public Date getModifiedDate() {
		return _usergroupDomainMaster.getModifiedDate();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _usergroupDomainMaster.getPrimaryKeyObj();
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
	* Returns the usergroup domain sid of this usergroup domain master.
	*
	* @return the usergroup domain sid of this usergroup domain master
	*/
	@Override
	public int getUsergroupDomainSid() {
		return _usergroupDomainMaster.getUsergroupDomainSid();
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
	* Returns the users sid of this usergroup domain master.
	*
	* @return the users sid of this usergroup domain master
	*/
	@Override
	public int getUsersSid() {
		return _usergroupDomainMaster.getUsersSid();
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

	@Override
	public int hashCode() {
		return _usergroupDomainMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _usergroupDomainMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _usergroupDomainMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _usergroupDomainMaster.isNew();
	}

	@Override
	public void persist() {
		_usergroupDomainMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_usergroupDomainMaster.setCachedModel(cachedModel);
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
	* Sets the created date of this usergroup domain master.
	*
	* @param createdDate the created date of this usergroup domain master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_usergroupDomainMaster.setCreatedDate(createdDate);
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

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_usergroupDomainMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_usergroupDomainMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_usergroupDomainMaster.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the modified by of this usergroup domain master.
	*
	* @param modifiedBy the modified by of this usergroup domain master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_usergroupDomainMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this usergroup domain master.
	*
	* @param modifiedDate the modified date of this usergroup domain master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_usergroupDomainMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_usergroupDomainMaster.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_usergroupDomainMaster.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the usergroup domain sid of this usergroup domain master.
	*
	* @param usergroupDomainSid the usergroup domain sid of this usergroup domain master
	*/
	@Override
	public void setUsergroupDomainSid(int usergroupDomainSid) {
		_usergroupDomainMaster.setUsergroupDomainSid(usergroupDomainSid);
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
	* Sets the users sid of this usergroup domain master.
	*
	* @param usersSid the users sid of this usergroup domain master
	*/
	@Override
	public void setUsersSid(int usersSid) {
		_usergroupDomainMaster.setUsersSid(usersSid);
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

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UsergroupDomainMaster> toCacheModel() {
		return _usergroupDomainMaster.toCacheModel();
	}

	@Override
	public UsergroupDomainMaster toEscapedModel() {
		return new UsergroupDomainMasterWrapper(_usergroupDomainMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _usergroupDomainMaster.toString();
	}

	@Override
	public UsergroupDomainMaster toUnescapedModel() {
		return new UsergroupDomainMasterWrapper(_usergroupDomainMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _usergroupDomainMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UsergroupDomainMasterWrapper)) {
			return false;
		}

		UsergroupDomainMasterWrapper usergroupDomainMasterWrapper = (UsergroupDomainMasterWrapper)obj;

		if (Objects.equals(_usergroupDomainMaster,
					usergroupDomainMasterWrapper._usergroupDomainMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public UsergroupDomainMaster getWrappedModel() {
		return _usergroupDomainMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _usergroupDomainMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _usergroupDomainMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_usergroupDomainMaster.resetOriginalValues();
	}

	private final UsergroupDomainMaster _usergroupDomainMaster;
}