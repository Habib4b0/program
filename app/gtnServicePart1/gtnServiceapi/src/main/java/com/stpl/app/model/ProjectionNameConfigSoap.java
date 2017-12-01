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
public class ProjectionNameConfigSoap implements Serializable {
	public static ProjectionNameConfigSoap toSoapModel(
		ProjectionNameConfig model) {
		ProjectionNameConfigSoap soapModel = new ProjectionNameConfigSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setBusinessProcessType(model.getBusinessProcessType());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setProjectionNameConfigSid(model.getProjectionNameConfigSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSelectedAttributes(model.getSelectedAttributes());

		return soapModel;
	}

	public static ProjectionNameConfigSoap[] toSoapModels(
		ProjectionNameConfig[] models) {
		ProjectionNameConfigSoap[] soapModels = new ProjectionNameConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectionNameConfigSoap[][] toSoapModels(
		ProjectionNameConfig[][] models) {
		ProjectionNameConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectionNameConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectionNameConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectionNameConfigSoap[] toSoapModels(
		List<ProjectionNameConfig> models) {
		List<ProjectionNameConfigSoap> soapModels = new ArrayList<ProjectionNameConfigSoap>(models.size());

		for (ProjectionNameConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectionNameConfigSoap[soapModels.size()]);
	}

	public ProjectionNameConfigSoap() {
	}

	public int getPrimaryKey() {
		return _projectionNameConfigSid;
	}

	public void setPrimaryKey(int pk) {
		setProjectionNameConfigSid(pk);
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

	public String getBusinessProcessType() {
		return _businessProcessType;
	}

	public void setBusinessProcessType(String businessProcessType) {
		_businessProcessType = businessProcessType;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getProjectionNameConfigSid() {
		return _projectionNameConfigSid;
	}

	public void setProjectionNameConfigSid(int projectionNameConfigSid) {
		_projectionNameConfigSid = projectionNameConfigSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getSelectedAttributes() {
		return _selectedAttributes;
	}

	public void setSelectedAttributes(String selectedAttributes) {
		_selectedAttributes = selectedAttributes;
	}

	private Date _createdDate;
	private int _createdBy;
	private String _businessProcessType;
	private int _versionNo;
	private int _modifiedBy;
	private int _projectionNameConfigSid;
	private Date _modifiedDate;
	private String _selectedAttributes;
}