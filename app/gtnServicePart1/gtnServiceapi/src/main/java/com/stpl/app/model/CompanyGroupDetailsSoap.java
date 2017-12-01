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
public class CompanyGroupDetailsSoap implements Serializable {
	public static CompanyGroupDetailsSoap toSoapModel(CompanyGroupDetails model) {
		CompanyGroupDetailsSoap soapModel = new CompanyGroupDetailsSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyParentDetailsSid(model.getCompanyParentDetailsSid());
		soapModel.setCompanyTradeclassSid(model.getCompanyTradeclassSid());
		soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setCompanyGroupDetailsSid(model.getCompanyGroupDetailsSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static CompanyGroupDetailsSoap[] toSoapModels(
		CompanyGroupDetails[] models) {
		CompanyGroupDetailsSoap[] soapModels = new CompanyGroupDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyGroupDetailsSoap[][] toSoapModels(
		CompanyGroupDetails[][] models) {
		CompanyGroupDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyGroupDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyGroupDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyGroupDetailsSoap[] toSoapModels(
		List<CompanyGroupDetails> models) {
		List<CompanyGroupDetailsSoap> soapModels = new ArrayList<CompanyGroupDetailsSoap>(models.size());

		for (CompanyGroupDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyGroupDetailsSoap[soapModels.size()]);
	}

	public CompanyGroupDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _companyGroupDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyGroupDetailsSid(pk);
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

	public String getCompanyParentDetailsSid() {
		return _companyParentDetailsSid;
	}

	public void setCompanyParentDetailsSid(String companyParentDetailsSid) {
		_companyParentDetailsSid = companyParentDetailsSid;
	}

	public int getCompanyTradeclassSid() {
		return _companyTradeclassSid;
	}

	public void setCompanyTradeclassSid(int companyTradeclassSid) {
		_companyTradeclassSid = companyTradeclassSid;
	}

	public int getCompanyGroupSid() {
		return _companyGroupSid;
	}

	public void setCompanyGroupSid(int companyGroupSid) {
		_companyGroupSid = companyGroupSid;
	}

	public int getVersionNo() {
		return _versionNo;
	}

	public void setVersionNo(int versionNo) {
		_versionNo = versionNo;
	}

	public int getCompanyGroupDetailsSid() {
		return _companyGroupDetailsSid;
	}

	public void setCompanyGroupDetailsSid(int companyGroupDetailsSid) {
		_companyGroupDetailsSid = companyGroupDetailsSid;
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

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private Date _createdDate;
	private int _createdBy;
	private String _companyParentDetailsSid;
	private int _companyTradeclassSid;
	private int _companyGroupSid;
	private int _versionNo;
	private int _companyGroupDetailsSid;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _companyMasterSid;
}