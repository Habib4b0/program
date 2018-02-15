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
public class AccClosureViewMasterSoap implements Serializable {
	public static AccClosureViewMasterSoap toSoapModel(
		AccClosureViewMaster model) {
		AccClosureViewMasterSoap soapModel = new AccClosureViewMasterSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setViewType(model.getViewType());
		soapModel.setAccClosureMasterSid(model.getAccClosureMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setAccClosureViewMasterSid(model.getAccClosureViewMasterSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setViewName(model.getViewName());

		return soapModel;
	}

	public static AccClosureViewMasterSoap[] toSoapModels(
		AccClosureViewMaster[] models) {
		AccClosureViewMasterSoap[] soapModels = new AccClosureViewMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccClosureViewMasterSoap[][] toSoapModels(
		AccClosureViewMaster[][] models) {
		AccClosureViewMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccClosureViewMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccClosureViewMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccClosureViewMasterSoap[] toSoapModels(
		List<AccClosureViewMaster> models) {
		List<AccClosureViewMasterSoap> soapModels = new ArrayList<AccClosureViewMasterSoap>(models.size());

		for (AccClosureViewMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccClosureViewMasterSoap[soapModels.size()]);
	}

	public AccClosureViewMasterSoap() {
	}

	public int getPrimaryKey() {
		return _accClosureViewMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setAccClosureViewMasterSid(pk);
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

	public String getViewType() {
		return _viewType;
	}

	public void setViewType(String viewType) {
		_viewType = viewType;
	}

	public int getAccClosureMasterSid() {
		return _accClosureMasterSid;
	}

	public void setAccClosureMasterSid(int accClosureMasterSid) {
		_accClosureMasterSid = accClosureMasterSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getAccClosureViewMasterSid() {
		return _accClosureViewMasterSid;
	}

	public void setAccClosureViewMasterSid(int accClosureViewMasterSid) {
		_accClosureViewMasterSid = accClosureViewMasterSid;
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
	private int _createdBy;
	private String _viewType;
	private int _accClosureMasterSid;
	private int _modifiedBy;
	private int _accClosureViewMasterSid;
	private Date _modifiedDate;
	private String _viewName;
}