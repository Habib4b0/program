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
 * This class is a wrapper for {@link CustomViewMaster}.
 * </p>
 *
 * @author
 * @see CustomViewMaster
 * @generated
 */
@ProviderType
public class CustomViewMasterWrapper implements CustomViewMaster,
	ModelWrapper<CustomViewMaster> {
	public CustomViewMasterWrapper(CustomViewMaster customViewMaster) {
		_customViewMaster = customViewMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return CustomViewMaster.class;
	}

	@Override
	public String getModelClassName() {
		return CustomViewMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("customViewMasterSid", getCustomViewMasterSid());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("projectionMasterSid", getProjectionMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("viewName", getViewName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer customViewMasterSid = (Integer)attributes.get(
				"customViewMasterSid");

		if (customViewMasterSid != null) {
			setCustomViewMasterSid(customViewMasterSid);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		Integer createdBy = (Integer)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Integer projectionMasterSid = (Integer)attributes.get(
				"projectionMasterSid");

		if (projectionMasterSid != null) {
			setProjectionMasterSid(projectionMasterSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String viewName = (String)attributes.get("viewName");

		if (viewName != null) {
			setViewName(viewName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CustomViewMasterWrapper((CustomViewMaster)_customViewMaster.clone());
	}

	@Override
	public int compareTo(CustomViewMaster customViewMaster) {
		return _customViewMaster.compareTo(customViewMaster);
	}

	/**
	* Returns the created by of this custom view master.
	*
	* @return the created by of this custom view master
	*/
	@Override
	public int getCreatedBy() {
		return _customViewMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this custom view master.
	*
	* @return the created date of this custom view master
	*/
	@Override
	public Date getCreatedDate() {
		return _customViewMaster.getCreatedDate();
	}

	/**
	* Returns the custom view master sid of this custom view master.
	*
	* @return the custom view master sid of this custom view master
	*/
	@Override
	public int getCustomViewMasterSid() {
		return _customViewMaster.getCustomViewMasterSid();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _customViewMaster.getExpandoBridge();
	}

	/**
	* Returns the modified by of this custom view master.
	*
	* @return the modified by of this custom view master
	*/
	@Override
	public int getModifiedBy() {
		return _customViewMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this custom view master.
	*
	* @return the modified date of this custom view master
	*/
	@Override
	public Date getModifiedDate() {
		return _customViewMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this custom view master.
	*
	* @return the primary key of this custom view master
	*/
	@Override
	public int getPrimaryKey() {
		return _customViewMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customViewMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection master sid of this custom view master.
	*
	* @return the projection master sid of this custom view master
	*/
	@Override
	public int getProjectionMasterSid() {
		return _customViewMaster.getProjectionMasterSid();
	}

	/**
	* Returns the view name of this custom view master.
	*
	* @return the view name of this custom view master
	*/
	@Override
	public java.lang.String getViewName() {
		return _customViewMaster.getViewName();
	}

	@Override
	public int hashCode() {
		return _customViewMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _customViewMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _customViewMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _customViewMaster.isNew();
	}

	@Override
	public void persist() {
		_customViewMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_customViewMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this custom view master.
	*
	* @param createdBy the created by of this custom view master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_customViewMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this custom view master.
	*
	* @param createdDate the created date of this custom view master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_customViewMaster.setCreatedDate(createdDate);
	}

	/**
	* Sets the custom view master sid of this custom view master.
	*
	* @param customViewMasterSid the custom view master sid of this custom view master
	*/
	@Override
	public void setCustomViewMasterSid(int customViewMasterSid) {
		_customViewMaster.setCustomViewMasterSid(customViewMasterSid);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_customViewMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_customViewMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_customViewMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this custom view master.
	*
	* @param modifiedBy the modified by of this custom view master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_customViewMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this custom view master.
	*
	* @param modifiedDate the modified date of this custom view master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_customViewMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_customViewMaster.setNew(n);
	}

	/**
	* Sets the primary key of this custom view master.
	*
	* @param primaryKey the primary key of this custom view master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_customViewMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_customViewMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection master sid of this custom view master.
	*
	* @param projectionMasterSid the projection master sid of this custom view master
	*/
	@Override
	public void setProjectionMasterSid(int projectionMasterSid) {
		_customViewMaster.setProjectionMasterSid(projectionMasterSid);
	}

	/**
	* Sets the view name of this custom view master.
	*
	* @param viewName the view name of this custom view master
	*/
	@Override
	public void setViewName(java.lang.String viewName) {
		_customViewMaster.setViewName(viewName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CustomViewMaster> toCacheModel() {
		return _customViewMaster.toCacheModel();
	}

	@Override
	public CustomViewMaster toEscapedModel() {
		return new CustomViewMasterWrapper(_customViewMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _customViewMaster.toString();
	}

	@Override
	public CustomViewMaster toUnescapedModel() {
		return new CustomViewMasterWrapper(_customViewMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _customViewMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomViewMasterWrapper)) {
			return false;
		}

		CustomViewMasterWrapper customViewMasterWrapper = (CustomViewMasterWrapper)obj;

		if (Objects.equals(_customViewMaster,
					customViewMasterWrapper._customViewMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public CustomViewMaster getWrappedModel() {
		return _customViewMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _customViewMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _customViewMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_customViewMaster.resetOriginalValues();
	}

	private final CustomViewMaster _customViewMaster;
}