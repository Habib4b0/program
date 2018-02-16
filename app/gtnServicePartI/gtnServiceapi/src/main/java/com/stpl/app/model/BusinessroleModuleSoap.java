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
public class BusinessroleModuleSoap implements Serializable {
	public static BusinessroleModuleSoap toSoapModel(BusinessroleModule model) {
		BusinessroleModuleSoap soapModel = new BusinessroleModuleSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setBusinessroleModuleSid(model.getBusinessroleModuleSid());
		soapModel.setBusinessroleMasterSid(model.getBusinessroleMasterSid());
		soapModel.setAddFlag(model.getAddFlag());
		soapModel.setViewFlag(model.getViewFlag());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSubmodulePropertyId(model.getSubmodulePropertyId());
		soapModel.setEditFlag(model.getEditFlag());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setAccessModule(model.getAccessModule());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static BusinessroleModuleSoap[] toSoapModels(
		BusinessroleModule[] models) {
		BusinessroleModuleSoap[] soapModels = new BusinessroleModuleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BusinessroleModuleSoap[][] toSoapModels(
		BusinessroleModule[][] models) {
		BusinessroleModuleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BusinessroleModuleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BusinessroleModuleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BusinessroleModuleSoap[] toSoapModels(
		List<BusinessroleModule> models) {
		List<BusinessroleModuleSoap> soapModels = new ArrayList<BusinessroleModuleSoap>(models.size());

		for (BusinessroleModule model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BusinessroleModuleSoap[soapModels.size()]);
	}

	public BusinessroleModuleSoap() {
	}

	public int getPrimaryKey() {
		return _businessroleModuleSid;
	}

	public void setPrimaryKey(int pk) {
		setBusinessroleModuleSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getBusinessroleModuleSid() {
		return _businessroleModuleSid;
	}

	public void setBusinessroleModuleSid(int businessroleModuleSid) {
		_businessroleModuleSid = businessroleModuleSid;
	}

	public int getBusinessroleMasterSid() {
		return _businessroleMasterSid;
	}

	public void setBusinessroleMasterSid(int businessroleMasterSid) {
		_businessroleMasterSid = businessroleMasterSid;
	}

	public String getAddFlag() {
		return _addFlag;
	}

	public void setAddFlag(String addFlag) {
		_addFlag = addFlag;
	}

	public String getViewFlag() {
		return _viewFlag;
	}

	public void setViewFlag(String viewFlag) {
		_viewFlag = viewFlag;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getSubmodulePropertyId() {
		return _submodulePropertyId;
	}

	public void setSubmodulePropertyId(int submodulePropertyId) {
		_submodulePropertyId = submodulePropertyId;
	}

	public String getEditFlag() {
		return _editFlag;
	}

	public void setEditFlag(String editFlag) {
		_editFlag = editFlag;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public String getAccessModule() {
		return _accessModule;
	}

	public void setAccessModule(String accessModule) {
		_accessModule = accessModule;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private int _createdBy;
	private int _businessroleModuleSid;
	private int _businessroleMasterSid;
	private String _addFlag;
	private String _viewFlag;
	private int _modifiedBy;
	private Date _createdDate;
	private int _submodulePropertyId;
	private String _editFlag;
	private int _versionNo;
	private String _accessModule;
	private Date _modifiedDate;
}