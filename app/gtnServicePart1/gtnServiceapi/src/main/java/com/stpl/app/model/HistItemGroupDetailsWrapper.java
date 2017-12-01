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
 * This class is a wrapper for {@link HistItemGroupDetails}.
 * </p>
 *
 * @author
 * @see HistItemGroupDetails
 * @generated
 */
@ProviderType
public class HistItemGroupDetailsWrapper implements HistItemGroupDetails,
	ModelWrapper<HistItemGroupDetails> {
	public HistItemGroupDetailsWrapper(
		HistItemGroupDetails histItemGroupDetails) {
		_histItemGroupDetails = histItemGroupDetails;
	}

	@Override
	public Class<?> getModelClass() {
		return HistItemGroupDetails.class;
	}

	@Override
	public String getModelClassName() {
		return HistItemGroupDetails.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemGroupDetailsSid", getItemGroupDetailsSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("actionDate", getActionDate());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("actionFlag", getActionFlag());
		attributes.put("versionNo", getVersionNo());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("itemGroupSid", getItemGroupSid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemGroupDetailsSid = (Integer)attributes.get(
				"itemGroupDetailsSid");

		if (itemGroupDetailsSid != null) {
			setItemGroupDetailsSid(itemGroupDetailsSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date actionDate = (Date)attributes.get("actionDate");

		if (actionDate != null) {
			setActionDate(actionDate);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String actionFlag = (String)attributes.get("actionFlag");

		if (actionFlag != null) {
			setActionFlag(actionFlag);
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

		Integer itemGroupSid = (Integer)attributes.get("itemGroupSid");

		if (itemGroupSid != null) {
			setItemGroupSid(itemGroupSid);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new HistItemGroupDetailsWrapper((HistItemGroupDetails)_histItemGroupDetails.clone());
	}

	@Override
	public int compareTo(HistItemGroupDetails histItemGroupDetails) {
		return _histItemGroupDetails.compareTo(histItemGroupDetails);
	}

	/**
	* Returns the action date of this hist item group details.
	*
	* @return the action date of this hist item group details
	*/
	@Override
	public Date getActionDate() {
		return _histItemGroupDetails.getActionDate();
	}

	/**
	* Returns the action flag of this hist item group details.
	*
	* @return the action flag of this hist item group details
	*/
	@Override
	public java.lang.String getActionFlag() {
		return _histItemGroupDetails.getActionFlag();
	}

	/**
	* Returns the created by of this hist item group details.
	*
	* @return the created by of this hist item group details
	*/
	@Override
	public int getCreatedBy() {
		return _histItemGroupDetails.getCreatedBy();
	}

	/**
	* Returns the created date of this hist item group details.
	*
	* @return the created date of this hist item group details
	*/
	@Override
	public Date getCreatedDate() {
		return _histItemGroupDetails.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _histItemGroupDetails.getExpandoBridge();
	}

	/**
	* Returns the item group details sid of this hist item group details.
	*
	* @return the item group details sid of this hist item group details
	*/
	@Override
	public int getItemGroupDetailsSid() {
		return _histItemGroupDetails.getItemGroupDetailsSid();
	}

	/**
	* Returns the item group sid of this hist item group details.
	*
	* @return the item group sid of this hist item group details
	*/
	@Override
	public int getItemGroupSid() {
		return _histItemGroupDetails.getItemGroupSid();
	}

	/**
	* Returns the item master sid of this hist item group details.
	*
	* @return the item master sid of this hist item group details
	*/
	@Override
	public int getItemMasterSid() {
		return _histItemGroupDetails.getItemMasterSid();
	}

	/**
	* Returns the modified by of this hist item group details.
	*
	* @return the modified by of this hist item group details
	*/
	@Override
	public int getModifiedBy() {
		return _histItemGroupDetails.getModifiedBy();
	}

	/**
	* Returns the modified date of this hist item group details.
	*
	* @return the modified date of this hist item group details
	*/
	@Override
	public Date getModifiedDate() {
		return _histItemGroupDetails.getModifiedDate();
	}

	/**
	* Returns the primary key of this hist item group details.
	*
	* @return the primary key of this hist item group details
	*/
	@Override
	public com.stpl.app.service.persistence.HistItemGroupDetailsPK getPrimaryKey() {
		return _histItemGroupDetails.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _histItemGroupDetails.getPrimaryKeyObj();
	}

	/**
	* Returns the version no of this hist item group details.
	*
	* @return the version no of this hist item group details
	*/
	@Override
	public int getVersionNo() {
		return _histItemGroupDetails.getVersionNo();
	}

	@Override
	public int hashCode() {
		return _histItemGroupDetails.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _histItemGroupDetails.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _histItemGroupDetails.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _histItemGroupDetails.isNew();
	}

	@Override
	public void persist() {
		_histItemGroupDetails.persist();
	}

	/**
	* Sets the action date of this hist item group details.
	*
	* @param actionDate the action date of this hist item group details
	*/
	@Override
	public void setActionDate(Date actionDate) {
		_histItemGroupDetails.setActionDate(actionDate);
	}

	/**
	* Sets the action flag of this hist item group details.
	*
	* @param actionFlag the action flag of this hist item group details
	*/
	@Override
	public void setActionFlag(java.lang.String actionFlag) {
		_histItemGroupDetails.setActionFlag(actionFlag);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_histItemGroupDetails.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this hist item group details.
	*
	* @param createdBy the created by of this hist item group details
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_histItemGroupDetails.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this hist item group details.
	*
	* @param createdDate the created date of this hist item group details
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_histItemGroupDetails.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_histItemGroupDetails.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_histItemGroupDetails.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_histItemGroupDetails.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the item group details sid of this hist item group details.
	*
	* @param itemGroupDetailsSid the item group details sid of this hist item group details
	*/
	@Override
	public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
		_histItemGroupDetails.setItemGroupDetailsSid(itemGroupDetailsSid);
	}

	/**
	* Sets the item group sid of this hist item group details.
	*
	* @param itemGroupSid the item group sid of this hist item group details
	*/
	@Override
	public void setItemGroupSid(int itemGroupSid) {
		_histItemGroupDetails.setItemGroupSid(itemGroupSid);
	}

	/**
	* Sets the item master sid of this hist item group details.
	*
	* @param itemMasterSid the item master sid of this hist item group details
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_histItemGroupDetails.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this hist item group details.
	*
	* @param modifiedBy the modified by of this hist item group details
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_histItemGroupDetails.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this hist item group details.
	*
	* @param modifiedDate the modified date of this hist item group details
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_histItemGroupDetails.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_histItemGroupDetails.setNew(n);
	}

	/**
	* Sets the primary key of this hist item group details.
	*
	* @param primaryKey the primary key of this hist item group details
	*/
	@Override
	public void setPrimaryKey(
		com.stpl.app.service.persistence.HistItemGroupDetailsPK primaryKey) {
		_histItemGroupDetails.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_histItemGroupDetails.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the version no of this hist item group details.
	*
	* @param versionNo the version no of this hist item group details
	*/
	@Override
	public void setVersionNo(int versionNo) {
		_histItemGroupDetails.setVersionNo(versionNo);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<HistItemGroupDetails> toCacheModel() {
		return _histItemGroupDetails.toCacheModel();
	}

	@Override
	public HistItemGroupDetails toEscapedModel() {
		return new HistItemGroupDetailsWrapper(_histItemGroupDetails.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _histItemGroupDetails.toString();
	}

	@Override
	public HistItemGroupDetails toUnescapedModel() {
		return new HistItemGroupDetailsWrapper(_histItemGroupDetails.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _histItemGroupDetails.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof HistItemGroupDetailsWrapper)) {
			return false;
		}

		HistItemGroupDetailsWrapper histItemGroupDetailsWrapper = (HistItemGroupDetailsWrapper)obj;

		if (Objects.equals(_histItemGroupDetails,
					histItemGroupDetailsWrapper._histItemGroupDetails)) {
			return true;
		}

		return false;
	}

	@Override
	public HistItemGroupDetails getWrappedModel() {
		return _histItemGroupDetails;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _histItemGroupDetails.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _histItemGroupDetails.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_histItemGroupDetails.resetOriginalValues();
	}

	private final HistItemGroupDetails _histItemGroupDetails;
}