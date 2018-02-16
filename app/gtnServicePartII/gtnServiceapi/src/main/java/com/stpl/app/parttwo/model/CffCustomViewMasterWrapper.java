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
 * This class is a wrapper for {@link CffCustomViewMaster}.
 * </p>
 *
 * @author
 * @see CffCustomViewMaster
 * @generated
 */
@ProviderType
public class CffCustomViewMasterWrapper implements CffCustomViewMaster,
	ModelWrapper<CffCustomViewMaster> {
	public CffCustomViewMasterWrapper(CffCustomViewMaster cffCustomViewMaster) {
		_cffCustomViewMaster = cffCustomViewMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return CffCustomViewMaster.class;
	}

	@Override
	public String getModelClassName() {
		return CffCustomViewMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("cffMasterSid", getCffMasterSid());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("cffCustomViewMasterSid", getCffCustomViewMasterSid());
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

		Integer cffMasterSid = (Integer)attributes.get("cffMasterSid");

		if (cffMasterSid != null) {
			setCffMasterSid(cffMasterSid);
		}

		Integer modifiedBy = (Integer)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Integer cffCustomViewMasterSid = (Integer)attributes.get(
				"cffCustomViewMasterSid");

		if (cffCustomViewMasterSid != null) {
			setCffCustomViewMasterSid(cffCustomViewMasterSid);
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
		return new CffCustomViewMasterWrapper((CffCustomViewMaster)_cffCustomViewMaster.clone());
	}

	@Override
	public int compareTo(CffCustomViewMaster cffCustomViewMaster) {
		return _cffCustomViewMaster.compareTo(cffCustomViewMaster);
	}

	/**
	* Returns the cff custom view master sid of this cff custom view master.
	*
	* @return the cff custom view master sid of this cff custom view master
	*/
	@Override
	public int getCffCustomViewMasterSid() {
		return _cffCustomViewMaster.getCffCustomViewMasterSid();
	}

	/**
	* Returns the cff master sid of this cff custom view master.
	*
	* @return the cff master sid of this cff custom view master
	*/
	@Override
	public int getCffMasterSid() {
		return _cffCustomViewMaster.getCffMasterSid();
	}

	/**
	* Returns the created by of this cff custom view master.
	*
	* @return the created by of this cff custom view master
	*/
	@Override
	public int getCreatedBy() {
		return _cffCustomViewMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this cff custom view master.
	*
	* @return the created date of this cff custom view master
	*/
	@Override
	public Date getCreatedDate() {
		return _cffCustomViewMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _cffCustomViewMaster.getExpandoBridge();
	}

	/**
	* Returns the modified by of this cff custom view master.
	*
	* @return the modified by of this cff custom view master
	*/
	@Override
	public int getModifiedBy() {
		return _cffCustomViewMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this cff custom view master.
	*
	* @return the modified date of this cff custom view master
	*/
	@Override
	public Date getModifiedDate() {
		return _cffCustomViewMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this cff custom view master.
	*
	* @return the primary key of this cff custom view master
	*/
	@Override
	public int getPrimaryKey() {
		return _cffCustomViewMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _cffCustomViewMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the view name of this cff custom view master.
	*
	* @return the view name of this cff custom view master
	*/
	@Override
	public java.lang.String getViewName() {
		return _cffCustomViewMaster.getViewName();
	}

	@Override
	public int hashCode() {
		return _cffCustomViewMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _cffCustomViewMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _cffCustomViewMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _cffCustomViewMaster.isNew();
	}

	@Override
	public void persist() {
		_cffCustomViewMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_cffCustomViewMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the cff custom view master sid of this cff custom view master.
	*
	* @param cffCustomViewMasterSid the cff custom view master sid of this cff custom view master
	*/
	@Override
	public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
		_cffCustomViewMaster.setCffCustomViewMasterSid(cffCustomViewMasterSid);
	}

	/**
	* Sets the cff master sid of this cff custom view master.
	*
	* @param cffMasterSid the cff master sid of this cff custom view master
	*/
	@Override
	public void setCffMasterSid(int cffMasterSid) {
		_cffCustomViewMaster.setCffMasterSid(cffMasterSid);
	}

	/**
	* Sets the created by of this cff custom view master.
	*
	* @param createdBy the created by of this cff custom view master
	*/
	@Override
	public void setCreatedBy(int createdBy) {
		_cffCustomViewMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this cff custom view master.
	*
	* @param createdDate the created date of this cff custom view master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_cffCustomViewMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_cffCustomViewMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_cffCustomViewMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_cffCustomViewMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this cff custom view master.
	*
	* @param modifiedBy the modified by of this cff custom view master
	*/
	@Override
	public void setModifiedBy(int modifiedBy) {
		_cffCustomViewMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this cff custom view master.
	*
	* @param modifiedDate the modified date of this cff custom view master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_cffCustomViewMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_cffCustomViewMaster.setNew(n);
	}

	/**
	* Sets the primary key of this cff custom view master.
	*
	* @param primaryKey the primary key of this cff custom view master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_cffCustomViewMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_cffCustomViewMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the view name of this cff custom view master.
	*
	* @param viewName the view name of this cff custom view master
	*/
	@Override
	public void setViewName(java.lang.String viewName) {
		_cffCustomViewMaster.setViewName(viewName);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CffCustomViewMaster> toCacheModel() {
		return _cffCustomViewMaster.toCacheModel();
	}

	@Override
	public CffCustomViewMaster toEscapedModel() {
		return new CffCustomViewMasterWrapper(_cffCustomViewMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _cffCustomViewMaster.toString();
	}

	@Override
	public CffCustomViewMaster toUnescapedModel() {
		return new CffCustomViewMasterWrapper(_cffCustomViewMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _cffCustomViewMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CffCustomViewMasterWrapper)) {
			return false;
		}

		CffCustomViewMasterWrapper cffCustomViewMasterWrapper = (CffCustomViewMasterWrapper)obj;

		if (Objects.equals(_cffCustomViewMaster,
					cffCustomViewMasterWrapper._cffCustomViewMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public CffCustomViewMaster getWrappedModel() {
		return _cffCustomViewMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _cffCustomViewMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _cffCustomViewMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_cffCustomViewMaster.resetOriginalValues();
	}

	private final CffCustomViewMaster _cffCustomViewMaster;
}