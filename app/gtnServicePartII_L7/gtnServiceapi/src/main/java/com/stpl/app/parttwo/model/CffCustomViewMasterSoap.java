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
public class CffCustomViewMasterSoap implements Serializable {
	public static CffCustomViewMasterSoap toSoapModel(CffCustomViewMaster model) {
		CffCustomViewMasterSoap soapModel = new CffCustomViewMasterSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCffMasterSid(model.getCffMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCffCustomViewMasterSid(model.getCffCustomViewMasterSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setViewName(model.getViewName());

		return soapModel;
	}

	public static CffCustomViewMasterSoap[] toSoapModels(
		CffCustomViewMaster[] models) {
		CffCustomViewMasterSoap[] soapModels = new CffCustomViewMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CffCustomViewMasterSoap[][] toSoapModels(
		CffCustomViewMaster[][] models) {
		CffCustomViewMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CffCustomViewMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CffCustomViewMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CffCustomViewMasterSoap[] toSoapModels(
		List<CffCustomViewMaster> models) {
		List<CffCustomViewMasterSoap> soapModels = new ArrayList<CffCustomViewMasterSoap>(models.size());

		for (CffCustomViewMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CffCustomViewMasterSoap[soapModels.size()]);
	}

	public CffCustomViewMasterSoap() {
	}

	public int getPrimaryKey() {
		return _cffCustomViewMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCffCustomViewMasterSid(pk);
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

	public int getCffMasterSid() {
		return _cffMasterSid;
	}

	public void setCffMasterSid(int cffMasterSid) {
		_cffMasterSid = cffMasterSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getCffCustomViewMasterSid() {
		return _cffCustomViewMasterSid;
	}

	public void setCffCustomViewMasterSid(int cffCustomViewMasterSid) {
		_cffCustomViewMasterSid = cffCustomViewMasterSid;
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
	private int _cffMasterSid;
	private int _modifiedBy;
	private int _cffCustomViewMasterSid;
	private Date _modifiedDate;
	private String _viewName;
}