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
public class HelperTableSoap implements Serializable {
	public static HelperTableSoap toSoapModel(HelperTable model) {
		HelperTableSoap soapModel = new HelperTableSoap();

		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setDescription(model.getDescription());
		soapModel.setRefCount(model.getRefCount());
		soapModel.setListName(model.getListName());
		soapModel.setHelperTableSid(model.getHelperTableSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAliasName(model.getAliasName());

		return soapModel;
	}

	public static HelperTableSoap[] toSoapModels(HelperTable[] models) {
		HelperTableSoap[] soapModels = new HelperTableSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HelperTableSoap[][] toSoapModels(HelperTable[][] models) {
		HelperTableSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HelperTableSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HelperTableSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HelperTableSoap[] toSoapModels(List<HelperTable> models) {
		List<HelperTableSoap> soapModels = new ArrayList<HelperTableSoap>(models.size());

		for (HelperTable model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HelperTableSoap[soapModels.size()]);
	}

	public HelperTableSoap() {
	}

	public int getPrimaryKey() {
		return _helperTableSid;
	}

	public void setPrimaryKey(int pk) {
		setHelperTableSid(pk);
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

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getRefCount() {
		return _refCount;
	}

	public void setRefCount(int refCount) {
		_refCount = refCount;
	}

	public String getListName() {
		return _listName;
	}

	public void setListName(String listName) {
		_listName = listName;
	}

	public int getHelperTableSid() {
		return _helperTableSid;
	}

	public void setHelperTableSid(int helperTableSid) {
		_helperTableSid = helperTableSid;
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

	public String getAliasName() {
		return _aliasName;
	}

	public void setAliasName(String aliasName) {
		_aliasName = aliasName;
	}

	private Date _createdDate;
	private int _createdBy;
	private String _description;
	private int _refCount;
	private String _listName;
	private int _helperTableSid;
	private int _modifiedBy;
	private Date _modifiedDate;
	private String _aliasName;
}