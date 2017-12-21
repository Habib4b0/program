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
 * This class is a wrapper for {@link CffViewMaster}.
 * </p>
 *
 * @author
 * @see CffViewMaster
 * @generated
 */
@ProviderType
public class CffViewMasterWrapper implements CffViewMaster,
	ModelWrapper<CffViewMaster> {
	public CffViewMasterWrapper(CffViewMaster cffViewMaster) {
		_cffViewMaster = cffViewMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return CffViewMaster.class;
	}

	@Override
	public String getModelClassName() {
		return CffViewMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("viewType", getViewType());
		attributes.put("cffViewMasterSid", getCffViewMasterSid());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
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

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String viewType = (String)attributes.get("viewType");

		if (viewType != null) {
			setViewType(viewType);
		}

		Integer cffViewMasterSid = (Integer)attributes.get("cffViewMasterSid");

		if (cffViewMasterSid != null) {
			setCffViewMasterSid(cffViewMasterSid);
		}

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

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
		return new CffViewMasterWrapper((CffViewMaster)_cffViewMaster.clone());
	}

	@Override
	public int compareTo(CffViewMaster cffViewMaster) {
		return _cffViewMaster.compareTo(cffViewMaster);
	}

	/**
	* Returns the cff master sid of this cff view master.
	*
	* @return the cff master sid of this cff view master
	*/
	@Override
	public int getCffMasterSid() {
		return _cffViewMaster.getCffMasterSid();
	}

	/**
	* Returns the cff view master sid of this cff view master.
	*
	* @return the cff view master sid of this cff view master
	*/
	@Override
	public int getCffViewMasterSid() {
		return _cffViewMaster.getCffViewMasterSid();
	}

	/**
	* Returns the created by of this cff view master.
	*
	* @return the created by of this cff view master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _cffViewMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this cff view master.
	*
	* @return the created date of this cff view master
	*/
	@Override
	public Date getCreatedDate() {
		return _cffViewMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffViewMaster.getExpandoBridge();
	}

	/**
	* Returns the modified by of this cff view master.
	*
	* @return the modified by of this cff view master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _cffViewMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this cff view master.
	*
	* @return the modified date of this cff view master
	*/
	@Override
	public Date getModifiedDate() {
		return _cffViewMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this cff view master.
	*
	* @return the primary key of this cff view master
	*/
	@Override
	public int getPrimaryKey() {
		return _cffViewMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffViewMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the view name of this cff view master.
	*
	* @return the view name of this cff view master
	*/
	@Override
	public java.lang.String getViewName() {
		return _cffViewMaster.getViewName();
	}

	/**
	* Returns the view type of this cff view master.
	*
	* @return the view type of this cff view master
	*/
	@Override
	public java.lang.String getViewType() {
		return _cffViewMaster.getViewType();
	}

	@Override
	public int hashCode() {
		return _cffViewMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffViewMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffViewMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffViewMaster.isNew();
	}

	@Override
	public void persist() {
		_cffViewMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffViewMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff master sid of this cff view master.
	*
	* @param cffMasterSid the cff master sid of this cff view master
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffViewMaster.setCffMasterSid(cffMasterSid);
	}

	/**
	* Sets the cff view master sid of this cff view master.
	*
	* @param cffViewMasterSid the cff view master sid of this cff view master
	*/
	@Override
	public void setCffViewMasterSid(int cffViewMasterSid) {
		_cffViewMaster.setCffViewMasterSid(cffViewMasterSid);
	}

	/**
	* Sets the created by of this cff view master.
	*
	* @param createdBy the created by of this cff view master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_cffViewMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cff view master.
	*
	* @param createdDate the created date of this cff view master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cffViewMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffViewMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffViewMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffViewMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this cff view master.
	*
	* @param modifiedBy the modified by of this cff view master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_cffViewMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cff view master.
	*
	* @param modifiedDate the modified date of this cff view master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cffViewMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cffViewMaster.setNew(n);
	}

	/**
	* Sets the primary key of this cff view master.
	*
	* @param primaryKey the primary key of this cff view master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffViewMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffViewMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the view name of this cff view master.
	*
	* @param viewName the view name of this cff view master
	*/
	@Override
	public void setViewName(java.lang.String viewName) {
		_cffViewMaster.setViewName(viewName);
	}

	/**
	* Sets the view type of this cff view master.
	*
	* @param viewType the view type of this cff view master
	*/
	@Override
	public void setViewType(java.lang.String viewType) {
		_cffViewMaster.setViewType(viewType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffViewMaster> toCacheModel() {
		return _cffViewMaster.toCacheModel();
	}

	@Override
	public CffViewMaster toEscapedModel() {
		return new CffViewMasterWrapper(_cffViewMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffViewMaster.toString();
	}

	@Override
	public CffViewMaster toUnescapedModel() {
		return new CffViewMasterWrapper(_cffViewMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffViewMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffViewMasterWrapper)) {
			return false;
		}

		CffViewMasterWrapper cffViewMasterWrapper = (CffViewMasterWrapper)obj;

		if (Objects.equals(_cffViewMaster, cffViewMasterWrapper._cffViewMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public CffViewMaster getWrappedModel() {
		return _cffViewMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffViewMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffViewMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffViewMaster.resetOriginalValues();
	}

	private final CffViewMaster _cffViewMaster;
}