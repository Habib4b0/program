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
public class CompanyGroupSoap implements Serializable {
	public static CompanyGroupSoap toSoapModel(CompanyGroup model) {
		CompanyGroupSoap soapModel = new CompanyGroupSoap();

		soapModel.setCompanyGroupNo(model.getCompanyGroupNo());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyFilter(model.getCompanyFilter());
		soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
		soapModel.setCompanyGroupDescription(model.getCompanyGroupDescription());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyGroupName(model.getCompanyGroupName());

		return soapModel;
	}

	public static CompanyGroupSoap[] toSoapModels(CompanyGroup[] models) {
		CompanyGroupSoap[] soapModels = new CompanyGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyGroupSoap[][] toSoapModels(CompanyGroup[][] models) {
		CompanyGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyGroupSoap[] toSoapModels(List<CompanyGroup> models) {
		List<CompanyGroupSoap> soapModels = new ArrayList<CompanyGroupSoap>(models.size());

		for (CompanyGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyGroupSoap[soapModels.size()]);
	}

	public CompanyGroupSoap() {
	}

	public int getPrimaryKey() {
		return _companyGroupSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyGroupSid(pk);
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

	public String getCompanyFilter() {
		return _companyFilter;
	}

	public void setCompanyFilter(String companyFilter) {
		_companyFilter = companyFilter;
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

	private String _companyGroupNo;
	private Date _createdDate;
	private int _createdBy;
	private String _companyFilter;
	private int _companyGroupSid;
	private String _companyGroupDescription;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private String _companyGroupName;
}