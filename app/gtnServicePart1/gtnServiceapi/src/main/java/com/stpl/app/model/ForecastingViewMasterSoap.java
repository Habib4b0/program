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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class ForecastingViewMasterSoap implements Serializable {
	public static ForecastingViewMasterSoap toSoapModel(
		ForecastingViewMaster model) {
		ForecastingViewMasterSoap soapModel = new ForecastingViewMasterSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setViewType(model.getViewType());
		soapModel.setViewId(model.getViewId());
		soapModel.setProjectionId(model.getProjectionId());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setViewName(model.getViewName());

		return soapModel;
	}

	public static ForecastingViewMasterSoap[] toSoapModels(
		ForecastingViewMaster[] models) {
		ForecastingViewMasterSoap[] soapModels = new ForecastingViewMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ForecastingViewMasterSoap[][] toSoapModels(
		ForecastingViewMaster[][] models) {
		ForecastingViewMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ForecastingViewMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ForecastingViewMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ForecastingViewMasterSoap[] toSoapModels(
		List<ForecastingViewMaster> models) {
		List<ForecastingViewMasterSoap> soapModels = new ArrayList<ForecastingViewMasterSoap>(models.size());

		for (ForecastingViewMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ForecastingViewMasterSoap[soapModels.size()]);
	}

	public ForecastingViewMasterSoap() {
	}

	public int getPrimaryKey() {
		return _viewId;
	}

	public void setPrimaryKey(int pk) {
		setViewId(pk);
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public String getViewType() {
		return _viewType;
	}

	public void setViewType(String viewType) {
		_viewType = viewType;
	}

	public int getViewId() {
		return _viewId;
	}

	public void setViewId(int viewId) {
		_viewId = viewId;
	}

	public int getProjectionId() {
		return _projectionId;
	}

	public void setProjectionId(int projectionId) {
		_projectionId = projectionId;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getViewName() {
		return _viewName;
	}

	public void setViewName(String viewName) {
		_viewName = viewName;
	}

	private Date _createdDate;
	private String _createdBy;
	private String _viewType;
	private int _viewId;
	private int _projectionId;
	private String _modifiedBy;
	private Date _modifiedDate;
	private String _viewName;
}