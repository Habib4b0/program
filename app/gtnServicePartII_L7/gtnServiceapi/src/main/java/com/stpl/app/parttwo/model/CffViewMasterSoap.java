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
public class CffViewMasterSoap implements Serializable {
	public static CffViewMasterSoap toSoapModel(CffViewMaster model) {
		CffViewMasterSoap soapModel = new CffViewMasterSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setViewType(model.getViewType());
		soapModel.setCffViewMasterSid(model.getCffViewMasterSid());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setViewName(model.getViewName());

		return soapModel;
	}

	public static CffViewMasterSoap[] toSoapModels(CffViewMaster[] models) {
		CffViewMasterSoap[] soapModels = new CffViewMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffViewMasterSoap[][] toSoapModels(CffViewMaster[][] models) {
		CffViewMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffViewMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffViewMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffViewMasterSoap[] toSoapModels(List<CffViewMaster> models) {
		List<CffViewMasterSoap> soapModels = new ArrayList<CffViewMasterSoap>(models.size());

		for (CffViewMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffViewMasterSoap[soapModels.size()]);
	}

	public CffViewMasterSoap() {
	}

	public int getPrimaryKey() {
		return _cffViewMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCffViewMasterSid(pk);
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

	public int getCffViewMasterSid() {
		return _cffViewMasterSid;
	}

	public void setCffViewMasterSid(int cffViewMasterSid) {
		_cffViewMasterSid = cffViewMasterSid;
	}

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
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
	private int _cffViewMasterSid;
	private int _cffMasterSid;
	private String _modifiedBy;
	private Date _modifiedDate;
	private String _viewName;
}