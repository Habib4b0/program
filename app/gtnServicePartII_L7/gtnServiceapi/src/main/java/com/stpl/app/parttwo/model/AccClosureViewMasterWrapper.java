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

package com.stpl.app.parttwo.model;

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
 * This class is a wrapper for {@link AccClosureViewMaster}.
 * </p>
 *
 * @author
 * @see AccClosureViewMaster
 * @generated
 */
@ProviderType
public class AccClosureViewMasterWrapper implements AccClosureViewMaster,
	ModelWrapper<AccClosureViewMaster> {
	public AccClosureViewMasterWrapper(
		AccClosureViewMaster accClosureViewMaster) {
		_accClosureViewMaster = accClosureViewMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return AccClosureViewMaster.class;
	}

	@Override
	public String getModelClassName() {
		return AccClosureViewMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("viewType", getViewType());
		attributes.put("accClosureMasterSid", getAccClosureMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("accClosureViewMasterSid", getAccClosureViewMasterSid());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("viewName", getViewName());

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

		String viewType = (String)attributes.get("viewType");

		if (viewType != null) {
			setViewType(viewType);
		}

		Integer accClosureMasterSid = (Integer)attributes.get(
				"accClosureMasterSid");

		if (accClosureMasterSid != null) {
			setAccClosureMasterSid(accClosureMasterSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer accClosureViewMasterSid = (Integer)attributes.get(
				"accClosureViewMasterSid");

		if (accClosureViewMasterSid != null) {
			setAccClosureViewMasterSid(accClosureViewMasterSid);
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
		return new AccClosureViewMasterWrapper((AccClosureViewMaster)_accClosureViewMaster.clone());
	}

	@Override
	public int compareTo(AccClosureViewMaster accClosureViewMaster) {
		return _accClosureViewMaster.compareTo(accClosureViewMaster);
	}

	/**
	* Returns the acc closure master sid of this acc closure view master.
	*
	* @return the acc closure master sid of this acc closure view master
	*/
	@Override
	public int getAccClosureMasterSid() {
		return _accClosureViewMaster.getAccClosureMasterSid();
	}

	/**
	* Returns the acc closure view master sid of this acc closure view master.
	*
	* @return the acc closure view master sid of this acc closure view master
	*/
	@Override
	public int getAccClosureViewMasterSid() {
		return _accClosureViewMaster.getAccClosureViewMasterSid();
	}

	/**
	* Returns the created by of this acc closure view master.
	*
	* @return the created by of this acc closure view master
	*/
	@Override
	public int getCreatedBy() {
		return _accClosureViewMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this acc closure view master.
	*
	* @return the created date of this acc closure view master
	*/
	@Override
	public Date getCreatedDate() {
		return _accClosureViewMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _accClosureViewMaster.getExpandoBridge();
	}

	/**
	* Returns the modified by of this acc closure view master.
	*
	* @return the modified by of this acc closure view master
	*/
	@Override
	public int getModifiedBy() {
		return _accClosureViewMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this acc closure view master.
	*
	* @return the modified date of this acc closure view master
	*/
	@Override
	public Date getModifiedDate() {
		return _accClosureViewMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this acc closure view master.
	*
	* @return the primary key of this acc closure view master
	*/
	@Override
	public int getPrimaryKey() {
		return _accClosureViewMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accClosureViewMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the view name of this acc closure view master.
	*
	* @return the view name of this acc closure view master
	*/
	@Override
	public java.lang.String getViewName() {
		return _accClosureViewMaster.getViewName();
	}

	/**
	* Returns the view type of this acc closure view master.
	*
	* @return the view type of this acc closure view master
	*/
	@Override
	public java.lang.String getViewType() {
		return _accClosureViewMaster.getViewType();
	}

	@Override
	public int hashCode() {
		return _accClosureViewMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _accClosureViewMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _accClosureViewMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _accClosureViewMaster.isNew();
	}

	@Override
	public void persist() {
		_accClosureViewMaster.persist();
	}

	/**
	* Sets the acc closure master sid of this acc closure view master.
	*
	* @param accClosureMasterSid the acc closure master sid of this acc closure view master
	*/
	@Override
	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureViewMaster.setAccClosureMasterSid(accClosureMasterSid);
	}

	/**
	* Sets the acc closure view master sid of this acc closure view master.
	*
	* @param accClosureViewMasterSid the acc closure view master sid of this acc closure view master
	*/
	@Override
	public void setAccClosureViewMasterSid(int accClosureViewMasterSid) {
		_accClosureViewMaster.setAccClosureViewMasterSid(accClosureViewMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_accClosureViewMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this acc closure view master.
	*
	* @param createdBy the created by of this acc closure view master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_accClosureViewMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this acc closure view master.
	*
	* @param createdDate the created date of this acc closure view master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_accClosureViewMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_accClosureViewMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_accClosureViewMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_accClosureViewMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this acc closure view master.
	*
	* @param modifiedBy the modified by of this acc closure view master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_accClosureViewMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this acc closure view master.
	*
	* @param modifiedDate the modified date of this acc closure view master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_accClosureViewMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_accClosureViewMaster.setNew(n);
	}

	/**
	* Sets the primary key of this acc closure view master.
	*
	* @param primaryKey the primary key of this acc closure view master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_accClosureViewMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_accClosureViewMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the view name of this acc closure view master.
	*
	* @param viewName the view name of this acc closure view master
	*/
	@Override
	public void setViewName(java.lang.String viewName) {
		_accClosureViewMaster.setViewName(viewName);
	}

	/**
	* Sets the view type of this acc closure view master.
	*
	* @param viewType the view type of this acc closure view master
	*/
	@Override
	public void setViewType(java.lang.String viewType) {
		_accClosureViewMaster.setViewType(viewType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AccClosureViewMaster> toCacheModel() {
		return _accClosureViewMaster.toCacheModel();
	}

	@Override
	public AccClosureViewMaster toEscapedModel() {
		return new AccClosureViewMasterWrapper(_accClosureViewMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _accClosureViewMaster.toString();
	}

	@Override
	public AccClosureViewMaster toUnescapedModel() {
		return new AccClosureViewMasterWrapper(_accClosureViewMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _accClosureViewMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccClosureViewMasterWrapper)) {
			return false;
		}

		AccClosureViewMasterWrapper accClosureViewMasterWrapper = (AccClosureViewMasterWrapper)obj;

		if (Objects.equals(_accClosureViewMaster,
					accClosureViewMasterWrapper._accClosureViewMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public AccClosureViewMaster getWrappedModel() {
		return _accClosureViewMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _accClosureViewMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _accClosureViewMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_accClosureViewMaster.resetOriginalValues();
	}

	private final AccClosureViewMaster _accClosureViewMaster;
}