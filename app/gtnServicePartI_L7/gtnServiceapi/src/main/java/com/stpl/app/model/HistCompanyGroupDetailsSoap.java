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

import com.stpl.app.service.persistence.HistCompanyGroupDetailsPK;

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
public class HistCompanyGroupDetailsSoap implements Serializable {
	public static HistCompanyGroupDetailsSoap toSoapModel(
		HistCompanyGroupDetails model) {
		HistCompanyGroupDetailsSoap soapModel = new HistCompanyGroupDetailsSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setCompanyParentDetailsSid(model.getCompanyParentDetailsSid());
		soapModel.setCompanyTradeclassSid(model.getCompanyTradeclassSid());
		soapModel.setActionFlag(model.getActionFlag());
		soapModel.setCompanyGroupSid(model.getCompanyGroupSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setCompanyGroupDetailsSid(model.getCompanyGroupDetailsSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static HistCompanyGroupDetailsSoap[] toSoapModels(
		HistCompanyGroupDetails[] models) {
		HistCompanyGroupDetailsSoap[] soapModels = new HistCompanyGroupDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistCompanyGroupDetailsSoap[][] toSoapModels(
		HistCompanyGroupDetails[][] models) {
		HistCompanyGroupDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistCompanyGroupDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistCompanyGroupDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistCompanyGroupDetailsSoap[] toSoapModels(
		List<HistCompanyGroupDetails> models) {
		List<HistCompanyGroupDetailsSoap> soapModels = new ArrayList<HistCompanyGroupDetailsSoap>(models.size());

		for (HistCompanyGroupDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistCompanyGroupDetailsSoap[soapModels.size()]);
	}

	public HistCompanyGroupDetailsSoap() {
	}

	public HistCompanyGroupDetailsPK getPrimaryKey() {
		return new HistCompanyGroupDetailsPK(_actionFlag, _versionNo,
			_companyGroupDetailsSid);
	}

	public void setPrimaryKey(HistCompanyGroupDetailsPK pk) {
		setActionFlag(pk.actionFlag);
		setVersionNo(pk.versionNo);
		setCompanyGroupDetailsSid(pk.companyGroupDetailsSid);
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
	private Date _actionDate;
	private String _companyParentDetailsSid;
	private int _companyTradeclassSid;
	private String _actionFlag;
	private int _companyGroupSid;
	private int _versionNo;
	private int _companyGroupDetailsSid;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _companyMasterSid;
}