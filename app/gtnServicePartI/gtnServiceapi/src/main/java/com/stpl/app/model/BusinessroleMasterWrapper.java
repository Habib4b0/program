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
 * This class is a wrapper for {@link BusinessroleMaster}.
 * </p>
 *
 * @author
 * @see BusinessroleMaster
 * @generated
 */
@ProviderType
public class BusinessroleMasterWrapper implements BusinessroleMaster,
	ModelWrapper<BusinessroleMaster> {
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
		Integer businessroleMasterSid = (Integer)attributes.get(
				"businessroleMasterSid");

		if (businessroleMasterSid != null) {
			setBusinessroleMasterSid(businessroleMasterSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer usersSid = (Integer)attributes.get("usersSid");

		if (usersSid != null) {
			setUsersSid(usersSid);
		}

		Integer hierarchyLevel = (Integer)attributes.get("hierarchyLevel");

		if (hierarchyLevel != null) {
			setHierarchyLevel(hierarchyLevel);
		}

		String processed = (String)attributes.get("processed");

		if (processed != null) {
			setProcessed(processed);
		}

		String businessroleName = (String)attributes.get("businessroleName");

		if (businessroleName != null) {
			setBusinessroleName(businessroleName);
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

		String businessroleDesc = (String)attributes.get("businessroleDesc");

		if (businessroleDesc != null) {
			setBusinessroleDesc(businessroleDesc);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BusinessroleMasterWrapper((BusinessroleMaster)_businessroleMaster.clone());
	}

	@Override
	public int compareTo(BusinessroleMaster businessroleMaster) {
		return _businessroleMaster.compareTo(businessroleMaster);
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
	* Returns the businessrole master sid of this businessrole master.
	*
	* @return the businessrole master sid of this businessrole master
	*/
	@Override
	public int getBusinessroleMasterSid() {
		return _businessroleMaster.getBusinessroleMasterSid();
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
	* Returns the created by of this businessrole master.
	*
	* @return the created by of this businessrole master
	*/
	@Override
	public int getCreatedBy() {
		return _businessroleMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this businessrole master.
	*
	* @return the created date of this businessrole master
	*/
	@Override
	public Date getCreatedDate() {
		return _businessroleMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _businessroleMaster.getExpandoBridge();
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
	* Returns the is active of this businessrole master.
	*
	* @return the is active of this businessrole master
	*/
	@Override
	public java.lang.String getIsActive() {
		return _businessroleMaster.getIsActive();
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
	* Returns the modified date of this businessrole master.
	*
	* @return the modified date of this businessrole master
	*/
	@Override
	public Date getModifiedDate() {
		return _businessroleMaster.getModifiedDate();
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

	@Override
	public Serializable getPrimaryKeyObj() {
		return _businessroleMaster.getPrimaryKeyObj();
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
	* Returns the users sid of this businessrole master.
	*
	* @return the users sid of this businessrole master
	*/
	@Override
	public int getUsersSid() {
		return _businessroleMaster.getUsersSid();
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

	@Override
	public int hashCode() {
		return _businessroleMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _businessroleMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _businessroleMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _businessroleMaster.isNew();
	}

	@Override
	public void persist() {
		_businessroleMaster.persist();
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
	* Sets the businessrole master sid of this businessrole master.
	*
	* @param businessroleMasterSid the businessrole master sid of this businessrole master
	*/
	@Override
	public void setBusinessroleMasterSid(int businessroleMasterSid) {
		_businessroleMaster.setBusinessroleMasterSid(businessroleMasterSid);
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

	@Override
	public void setCachedModel(boolean cachedModel) {
		_businessroleMaster.setCachedModel(cachedModel);
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
	* Sets the created date of this businessrole master.
	*
	* @param createdDate the created date of this businessrole master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_businessroleMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_businessroleMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_businessroleMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_businessroleMaster.setExpandoBridgeAttributes(serviceContext);
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
	* Sets the is active of this businessrole master.
	*
	* @param isActive the is active of this businessrole master
	*/
	@Override
	public void setIsActive(java.lang.String isActive) {
		_businessroleMaster.setIsActive(isActive);
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
	* Sets the modified date of this businessrole master.
	*
	* @param modifiedDate the modified date of this businessrole master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_businessroleMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_businessroleMaster.setNew(n);
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

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_businessroleMaster.setPrimaryKeyObj(primaryKeyObj);
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
	* Sets the users sid of this businessrole master.
	*
	* @param usersSid the users sid of this businessrole master
	*/
	@Override
	public void setUsersSid(int usersSid) {
		_businessroleMaster.setUsersSid(usersSid);
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

	@Override
	public com.liferay.portal.kernel.model.CacheModel<BusinessroleMaster> toCacheModel() {
		return _businessroleMaster.toCacheModel();
	}

	@Override
	public BusinessroleMaster toEscapedModel() {
		return new BusinessroleMasterWrapper(_businessroleMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _businessroleMaster.toString();
	}

	@Override
	public BusinessroleMaster toUnescapedModel() {
		return new BusinessroleMasterWrapper(_businessroleMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _businessroleMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BusinessroleMasterWrapper)) {
			return false;
		}

		BusinessroleMasterWrapper businessroleMasterWrapper = (BusinessroleMasterWrapper)obj;

		if (Objects.equals(_businessroleMaster,
					businessroleMasterWrapper._businessroleMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public BusinessroleMaster getWrappedModel() {
		return _businessroleMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _businessroleMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _businessroleMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_businessroleMaster.resetOriginalValues();
	}

	private final BusinessroleMaster _businessroleMaster;
}