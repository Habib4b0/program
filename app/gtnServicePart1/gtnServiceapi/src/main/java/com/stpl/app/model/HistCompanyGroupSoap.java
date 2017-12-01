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

import com.stpl.app.service.persistence.HistCompanyGroupPK;

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
public class HistCompanyGroupSoap implements Serializable {
	public static HistCompanyGroupSoap toSoapModel(HistCompanyGroup model) {
		HistCompanyGroupSoap soapModel = new HistCompanyGroupSoap();

		soapModel.setCompanyGroupNo(model.getCompanyGroupNo());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setActionFlag(model.getActionFlag());
		soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
		soapModel.setCompanyGroupDescription(model.getCompanyGroupDescription());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyGroupName(model.getCompanyGroupName());
		soapModel.setCompanyFilter(model.getCompanyFilter());

		return soapModel;
	}

	public static HistCompanyGroupSoap[] toSoapModels(HistCompanyGroup[] models) {
		HistCompanyGroupSoap[] soapModels = new HistCompanyGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistCompanyGroupSoap[][] toSoapModels(
		HistCompanyGroup[][] models) {
		HistCompanyGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistCompanyGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistCompanyGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistCompanyGroupSoap[] toSoapModels(
		List<HistCompanyGroup> models) {
		List<HistCompanyGroupSoap> soapModels = new ArrayList<HistCompanyGroupSoap>(models.size());

		for (HistCompanyGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistCompanyGroupSoap[soapModels.size()]);
	}

	public HistCompanyGroupSoap() {
	}

	public HistCompanyGroupPK getPrimaryKey() {
		return new HistCompanyGroupPK(_actionFlag, _companyGroupSid, _versionNo);
	}

	public void setPrimaryKey(HistCompanyGroupPK pk) {
		setActionFlag(pk.actionFlag);
		setCompanyGroupSid(pk.companyGroupSid);
		setVersionNo(pk.versionNo);
	}

	public String getCompanyGroupNo() {
		return _companyGroupNo;
	}

	public void setCompanyGroupNo(String companyGroupNo) {
		_companyGroupNo = companyGroupNo;
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

	public Date getActionDate() {
		return _actionDate;
	}

	public void setActionDate(Date actionDate) {
		_actionDate = actionDate;
	}

	public String getActionFlag() {
		return _actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		_actionFlag = actionFlag;
	}

	public int getCompanyGroupSid() {
		return _companyGroupSid;
	}

	public void setCompanyGroupSid(int companyGroupSid) {
		_companyGroupSid = companyGroupSid;
	}

	public String getCompanyGroupDescription() {
		return _companyGroupDescription;
	}

	public void setCompanyGroupDescription(String companyGroupDescription) {
		_companyGroupDescription = companyGroupDescription;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getCompanyGroupName() {
		return _companyGroupName;
	}

	public void setCompanyGroupName(String companyGroupName) {
		_companyGroupName = companyGroupName;
	}

	public String getCompanyFilter() {
		return _companyFilter;
	}

	public void setCompanyFilter(String companyFilter) {
		_companyFilter = companyFilter;
	}

	private String _companyGroupNo;
	private Date _createdDate;
	private int _createdBy;
	private Date _actionDate;
	private String _actionFlag;
	private int _companyGroupSid;
	private String _companyGroupDescription;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private String _companyGroupName;
	private String _companyFilter;
}