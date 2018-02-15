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
public class CustomViewMasterSoap implements Serializable {
	public static CustomViewMasterSoap toSoapModel(CustomViewMaster model) {
		CustomViewMasterSoap soapModel = new CustomViewMasterSoap();

		soapModel.setCustomViewMasterSid(model.getCustomViewMasterSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setProjectionMasterSid(model.getProjectionMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setViewName(model.getViewName());

		return soapModel;
	}

	public static CustomViewMasterSoap[] toSoapModels(CustomViewMaster[] models) {
		CustomViewMasterSoap[] soapModels = new CustomViewMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CustomViewMasterSoap[][] toSoapModels(
		CustomViewMaster[][] models) {
		CustomViewMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CustomViewMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CustomViewMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CustomViewMasterSoap[] toSoapModels(
		List<CustomViewMaster> models) {
		List<CustomViewMasterSoap> soapModels = new ArrayList<CustomViewMasterSoap>(models.size());

		for (CustomViewMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CustomViewMasterSoap[soapModels.size()]);
	}

	public CustomViewMasterSoap() {
	}

	public int getPrimaryKey() {
		return _customViewMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCustomViewMasterSid(pk);
	}

	public int getCustomViewMasterSid() {
		return _customViewMasterSid;
	}

	public void setCustomViewMasterSid(int customViewMasterSid) {
		_customViewMasterSid = customViewMasterSid;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getProjectionMasterSid() {
		return _projectionMasterSid;
	}

	public void setProjectionMasterSid(int projectionMasterSid) {
		_projectionMasterSid = projectionMasterSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
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

	private int _customViewMasterSid;
	private Date _createdDate;
	private int _createdBy;
	private int _projectionMasterSid;
	private int _modifiedBy;
	private Date _modifiedDate;
	private String _viewName;
}