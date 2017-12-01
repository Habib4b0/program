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
public class NaProjMasterSoap implements Serializable {
	public static NaProjMasterSoap toSoapModel(NaProjMaster model) {
		NaProjMasterSoap soapModel = new NaProjMasterSoap();

		soapModel.setNaProjName(model.getNaProjName());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSaveFlag(model.getSaveFlag());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNaProjMasterSid(model.getNaProjMasterSid());
		soapModel.setItemGroupSid(model.getItemGroupSid());
		soapModel.setTherapeuticClass(model.getTherapeuticClass());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setBusinessUnit(model.getBusinessUnit());

		return soapModel;
	}

	public static NaProjMasterSoap[] toSoapModels(NaProjMaster[] models) {
		NaProjMasterSoap[] soapModels = new NaProjMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NaProjMasterSoap[][] toSoapModels(NaProjMaster[][] models) {
		NaProjMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new NaProjMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new NaProjMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NaProjMasterSoap[] toSoapModels(List<NaProjMaster> models) {
		List<NaProjMasterSoap> soapModels = new ArrayList<NaProjMasterSoap>(models.size());

		for (NaProjMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new NaProjMasterSoap[soapModels.size()]);
	}

	public NaProjMasterSoap() {
	}

	public int getPrimaryKey() {
		return _naProjMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setNaProjMasterSid(pk);
	}

	public String getNaProjName() {
		return _naProjName;
	}

	public void setNaProjName(String naProjName) {
		_naProjName = naProjName;
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

	public boolean getSaveFlag() {
		return _saveFlag;
	}

	public boolean isSaveFlag() {
		return _saveFlag;
	}

	public void setSaveFlag(boolean saveFlag) {
		_saveFlag = saveFlag;
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

	public int getNaProjMasterSid() {
		return _naProjMasterSid;
	}

	public void setNaProjMasterSid(int naProjMasterSid) {
		_naProjMasterSid = naProjMasterSid;
	}

	public int getItemGroupSid() {
		return _itemGroupSid;
	}

	public void setItemGroupSid(int itemGroupSid) {
		_itemGroupSid = itemGroupSid;
	}

	public int getTherapeuticClass() {
		return _therapeuticClass;
	}

	public void setTherapeuticClass(int therapeuticClass) {
		_therapeuticClass = therapeuticClass;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	public int getBusinessUnit() {
		return _businessUnit;
	}

	public void setBusinessUnit(int businessUnit) {
		_businessUnit = businessUnit;
	}

	private String _naProjName;
	private Date _createdDate;
	private int _createdBy;
	private boolean _saveFlag;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _naProjMasterSid;
	private int _itemGroupSid;
	private int _therapeuticClass;
	private int _companyMasterSid;
	private int _businessUnit;
}