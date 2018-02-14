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
 * This class is a wrapper for {@link HistItemGroup}.
 * </p>
 *
 * @author
 * @see HistItemGroup
 * @generated
 */
@ProviderType
public class HistItemGroupWrapper implements HistItemGroup,
	ModelWrapper<HistItemGroup> {
	public HistItemGroupWrapper(HistItemGroup histItemGroup) {
		_histItemGroup = histItemGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return HistItemGroup.class;
	}

	@Override
	public String getModelClassName() {
		return HistItemGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lastModifiedDate", getLastModifiedDate());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("itemGroupNo", getItemGroupNo());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("itemGroupDescription", getItemGroupDescription());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemGroupName", getItemGroupName());
		attributes.put("itemGroupSid", getItemGroupSid());
		attributes.put("companyMasterSid", getCompanyMasterSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Date lastModifiedDate = (Date)attributes.get("lastModifiedDate");

		if (lastModifiedDate != null) {
			setLastModifiedDate(lastModifiedDate);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
		}

		String itemGroupNo = (String)attributes.get("itemGroupNo");

		if (itemGroupNo != null) {
			setItemGroupNo(itemGroupNo);
		}

		Integer versionNo = (Integer)attributes.get("versionNo");

		if (versionNo != null) {
			setVersionNo(versionNo);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String itemGroupDescription = (String)attributes.get(
				"itemGroupDescription");

		if (itemGroupDescription != null) {
			setItemGroupDescription(itemGroupDescription);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String itemGroupName = (String)attributes.get("itemGroupName");

		if (itemGroupName != null) {
			setItemGroupName(itemGroupName);
		}

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistItemGroupWrapper((HistItemGroup)_histItemGroup.clone());
	}

	@Override
	public int compareTo(HistItemGroup histItemGroup) {
		return _histItemGroup.compareTo(histItemGroup);
	}

	/**
	* Returns the action flag of this hist item group.
	*
	* @return the action flag of this hist item group
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histItemGroup.getActionFlag();
	}

	/**
	* Returns the company master sid of this hist item group.
	*
	* @return the company master sid of this hist item group
	*/
	@Override
	public int getCompanyMasterSid() {
		return _histItemGroup.getCompanyMasterSid();
	}

	/**
	* Returns the created by of this hist item group.
	*
	* @return the created by of this hist item group
	*/
	@Override
	public int getCreatedBy() {
		return _histItemGroup.getCreatedBy();
	}

	/**
	* Returns the created date of this hist item group.
	*
	* @return the created date of this hist item group
	*/
	@Override
	public Date getCreatedDate() {
		return _histItemGroup.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histItemGroup.getExpandoBridge();
	}

	/**
	* Returns the item group description of this hist item group.
	*
	* @return the item group description of this hist item group
	*/
	@Override
	public java.lang.String getItemGroupDescription() {
		return _histItemGroup.getItemGroupDescription();
	}

	/**
	* Returns the item group name of this hist item group.
	*
	* @return the item group name of this hist item group
	*/
	@Override
	public java.lang.String getItemGroupName() {
		return _histItemGroup.getItemGroupName();
	}

	/**
	* Returns the item group no of this hist item group.
	*
	* @return the item group no of this hist item group
	*/
	@Override
	public java.lang.String getItemGroupNo() {
		return _histItemGroup.getItemGroupNo();
	}

	/**
	* Returns the item group sid of this hist item group.
	*
	* @return the item group sid of this hist item group
	*/
	@Override
	public int getItemGroupSid() {
		return _histItemGroup.getItemGroupSid();
	}

	/**
	* Returns the last modified date of this hist item group.
	*
	* @return the last modified date of this hist item group
	*/
	@Override
	public Date getLastModifiedDate() {
		return _histItemGroup.getLastModifiedDate();
	}

	/**
	* Returns the modified by of this hist item group.
	*
	* @return the modified by of this hist item group
	*/
	@Override
	public int getModifiedBy() {
		return _histItemGroup.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist item group.
	*
	* @return the modified date of this hist item group
	*/
	@Override
	public Date getModifiedDate() {
		return _histItemGroup.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist item group.
	*
	* @return the primary key of this hist item group
	*/
	@Override
	public com.stpl.app.service.persistence.HistItemGroupPK getPrimaryKey() {
		return _histItemGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histItemGroup.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist item group.
	*
	* @return the version no of this hist item group
	*/
	@Override
	public int getVersionNo() {
		return _histItemGroup.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histItemGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histItemGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histItemGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histItemGroup.isNew();
	}

	@Override
	public void persist() {
		_histItemGroup.persist();
	}

	/**
	* Sets the action flag of this hist item group.
	*
	* @param actionFlag the action flag of this hist item group
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histItemGroup.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histItemGroup.setCachedModel(cachedModel);
	}

	/**
	* Sets the company master sid of this hist item group.
	*
	* @param companyMasterSid the company master sid of this hist item group
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_histItemGroup.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the created by of this hist item group.
	*
	* @param createdBy the created by of this hist item group
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histItemGroup.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist item group.
	*
	* @param createdDate the created date of this hist item group
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histItemGroup.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histItemGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histItemGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histItemGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item group description of this hist item group.
	*
	* @param itemGroupDescription the item group description of this hist item group
	*/
	@Override
	public void setItemGroupDescription(java.lang.String itemGroupDescription) {
		_histItemGroup.setItemGroupDescription(itemGroupDescription);
	}

	/**
	* Sets the item group name of this hist item group.
	*
	* @param itemGroupName the item group name of this hist item group
	*/
	@Override
	public void setItemGroupName(java.lang.String itemGroupName) {
		_histItemGroup.setItemGroupName(itemGroupName);
	}

	/**
	* Sets the item group no of this hist item group.
	*
	* @param itemGroupNo the item group no of this hist item group
	*/
	@Override
	public void setItemGroupNo(java.lang.String itemGroupNo) {
		_histItemGroup.setItemGroupNo(itemGroupNo);
	}

	/**
	* Sets the item group sid of this hist item group.
	*
	* @param itemGroupSid the item group sid of this hist item group
	*/
	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_histItemGroup.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the last modified date of this hist item group.
	*
	* @param lastModifiedDate the last modified date of this hist item group
	*/
	@Override
	public void setLastModifiedDate(Date lastModifiedDate) {
		_histItemGroup.setLastModifiedDate(lastModifiedDate);
	}

	/**
	* Sets the modified by of this hist item group.
	*
	* @param modifiedBy the modified by of this hist item group
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histItemGroup.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist item group.
	*
	* @param modifiedDate the modified date of this hist item group
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histItemGroup.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histItemGroup.setNew(n);
	}

	/**
	* Sets the primary key of this hist item group.
	*
	* @param primaryKey the primary key of this hist item group
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistItemGroupPK primaryKey) {
		_histItemGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histItemGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist item group.
	*
	* @param versionNo the version no of this hist item group
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histItemGroup.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistItemGroup> toCacheModel() {
		return _histItemGroup.toCacheModel();
	}

	@Override
	public HistItemGroup toEscapedModel() {
		return new HistItemGroupWrapper(_histItemGroup.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histItemGroup.toString();
	}

	@Override
	public HistItemGroup toUnescapedModel() {
		return new HistItemGroupWrapper(_histItemGroup.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histItemGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistItemGroupWrapper)) {
			return false;
		}

		HistItemGroupWrapper histItemGroupWrapper = (HistItemGroupWrapper)obj;

		if (Objects.equals(_histItemGroup, histItemGroupWrapper._histItemGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public HistItemGroup getWrappedModel() {
		return _histItemGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histItemGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histItemGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histItemGroup.resetOriginalValues();
	}

	private final HistItemGroup _histItemGroup;
}