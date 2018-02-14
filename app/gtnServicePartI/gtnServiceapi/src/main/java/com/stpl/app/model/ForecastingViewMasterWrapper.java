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
 * This class is a wrapper for {@link ForecastingViewMaster}.
 * </p>
 *
 * @author
 * @see ForecastingViewMaster
 * @generated
 */
@ProviderType
public class ForecastingViewMasterWrapper implements ForecastingViewMaster,
	ModelWrapper<ForecastingViewMaster> {
	public ForecastingViewMasterWrapper(
		ForecastingViewMaster forecastingViewMaster) {
		_forecastingViewMaster = forecastingViewMaster;
	}

	@Override
	public Class<?> getModelClass() {
		return ForecastingViewMaster.class;
	}

	@Override
	public String getModelClassName() {
		return ForecastingViewMaster.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("viewType", getViewType());
		attributes.put("viewId", getViewId());
		attributes.put("projectionId", getProjectionId());
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

		Integer viewId = (Integer)attributes.get("viewId");

		if (viewId != null) {
			setViewId(viewId);
		}

		Integer projectionId = (Integer)attributes.get("projectionId");

		if (projectionId != null) {
			setProjectionId(projectionId);
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
		return new ForecastingViewMasterWrapper((ForecastingViewMaster)_forecastingViewMaster.clone());
	}

	@Override
	public int compareTo(ForecastingViewMaster forecastingViewMaster) {
		return _forecastingViewMaster.compareTo(forecastingViewMaster);
	}

	/**
	* Returns the created by of this forecasting view master.
	*
	* @return the created by of this forecasting view master
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _forecastingViewMaster.getCreatedBy();
	}

	/**
	* Returns the created date of this forecasting view master.
	*
	* @return the created date of this forecasting view master
	*/
	@Override
	public Date getCreatedDate() {
		return _forecastingViewMaster.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _forecastingViewMaster.getExpandoBridge();
	}

	/**
	* Returns the modified by of this forecasting view master.
	*
	* @return the modified by of this forecasting view master
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _forecastingViewMaster.getModifiedBy();
	}

	/**
	* Returns the modified date of this forecasting view master.
	*
	* @return the modified date of this forecasting view master
	*/
	@Override
	public Date getModifiedDate() {
		return _forecastingViewMaster.getModifiedDate();
	}

	/**
	* Returns the primary key of this forecasting view master.
	*
	* @return the primary key of this forecasting view master
	*/
	@Override
	public int getPrimaryKey() {
		return _forecastingViewMaster.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _forecastingViewMaster.getPrimaryKeyObj();
	}

	/**
	* Returns the projection ID of this forecasting view master.
	*
	* @return the projection ID of this forecasting view master
	*/
	@Override
	public int getProjectionId() {
		return _forecastingViewMaster.getProjectionId();
	}

	/**
	* Returns the view ID of this forecasting view master.
	*
	* @return the view ID of this forecasting view master
	*/
	@Override
	public int getViewId() {
		return _forecastingViewMaster.getViewId();
	}

	/**
	* Returns the view name of this forecasting view master.
	*
	* @return the view name of this forecasting view master
	*/
	@Override
	public java.lang.String getViewName() {
		return _forecastingViewMaster.getViewName();
	}

	/**
	* Returns the view type of this forecasting view master.
	*
	* @return the view type of this forecasting view master
	*/
	@Override
	public java.lang.String getViewType() {
		return _forecastingViewMaster.getViewType();
	}

	@Override
	public int hashCode() {
		return _forecastingViewMaster.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _forecastingViewMaster.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _forecastingViewMaster.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _forecastingViewMaster.isNew();
	}

	@Override
	public void persist() {
		_forecastingViewMaster.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_forecastingViewMaster.setCachedModel(cachedModel);
	}

	/**
	* Sets the created by of this forecasting view master.
	*
	* @param createdBy the created by of this forecasting view master
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_forecastingViewMaster.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this forecasting view master.
	*
	* @param createdDate the created date of this forecasting view master
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_forecastingViewMaster.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_forecastingViewMaster.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_forecastingViewMaster.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_forecastingViewMaster.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified by of this forecasting view master.
	*
	* @param modifiedBy the modified by of this forecasting view master
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_forecastingViewMaster.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this forecasting view master.
	*
	* @param modifiedDate the modified date of this forecasting view master
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_forecastingViewMaster.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_forecastingViewMaster.setNew(n);
	}

	/**
	* Sets the primary key of this forecasting view master.
	*
	* @param primaryKey the primary key of this forecasting view master
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_forecastingViewMaster.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_forecastingViewMaster.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the projection ID of this forecasting view master.
	*
	* @param projectionId the projection ID of this forecasting view master
	*/
	@Override
	public void setProjectionId(int projectionId) {
		_forecastingViewMaster.setProjectionId(projectionId);
	}

	/**
	* Sets the view ID of this forecasting view master.
	*
	* @param viewId the view ID of this forecasting view master
	*/
	@Override
	public void setViewId(int viewId) {
		_forecastingViewMaster.setViewId(viewId);
	}

	/**
	* Sets the view name of this forecasting view master.
	*
	* @param viewName the view name of this forecasting view master
	*/
	@Override
	public void setViewName(java.lang.String viewName) {
		_forecastingViewMaster.setViewName(viewName);
	}

	/**
	* Sets the view type of this forecasting view master.
	*
	* @param viewType the view type of this forecasting view master
	*/
	@Override
	public void setViewType(java.lang.String viewType) {
		_forecastingViewMaster.setViewType(viewType);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ForecastingViewMaster> toCacheModel() {
		return _forecastingViewMaster.toCacheModel();
	}

	@Override
	public ForecastingViewMaster toEscapedModel() {
		return new ForecastingViewMasterWrapper(_forecastingViewMaster.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _forecastingViewMaster.toString();
	}

	@Override
	public ForecastingViewMaster toUnescapedModel() {
		return new ForecastingViewMasterWrapper(_forecastingViewMaster.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _forecastingViewMaster.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ForecastingViewMasterWrapper)) {
			return false;
		}

		ForecastingViewMasterWrapper forecastingViewMasterWrapper = (ForecastingViewMasterWrapper)obj;

		if (Objects.equals(_forecastingViewMaster,
					forecastingViewMasterWrapper._forecastingViewMaster)) {
			return true;
		}

		return false;
	}

	@Override
	public ForecastingViewMaster getWrappedModel() {
		return _forecastingViewMaster;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _forecastingViewMaster.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _forecastingViewMaster.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_forecastingViewMaster.resetOriginalValues();
	}

	private final ForecastingViewMaster _forecastingViewMaster;
}