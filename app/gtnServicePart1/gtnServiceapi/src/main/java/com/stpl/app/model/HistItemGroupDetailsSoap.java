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

import com.stpl.app.service.persistence.HistItemGroupDetailsPK;

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
public class HistItemGroupDetailsSoap implements Serializable {
	public static HistItemGroupDetailsSoap toSoapModel(
		HistItemGroupDetails model) {
		HistItemGroupDetailsSoap soapModel = new HistItemGroupDetailsSoap();

		soapModel.setItemGroupDetailsSid(model.getItemGroupDetailsSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setActionDate(model.getActionDate());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setActionFlag(model.getActionFlag());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemGroupSid(model.getItemGroupSid());

		return soapModel;
	}

	public static HistItemGroupDetailsSoap[] toSoapModels(
		HistItemGroupDetails[] models) {
		HistItemGroupDetailsSoap[] soapModels = new HistItemGroupDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HistItemGroupDetailsSoap[][] toSoapModels(
		HistItemGroupDetails[][] models) {
		HistItemGroupDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HistItemGroupDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HistItemGroupDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HistItemGroupDetailsSoap[] toSoapModels(
		List<HistItemGroupDetails> models) {
		List<HistItemGroupDetailsSoap> soapModels = new ArrayList<HistItemGroupDetailsSoap>(models.size());

		for (HistItemGroupDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HistItemGroupDetailsSoap[soapModels.size()]);
	}

	public HistItemGroupDetailsSoap() {
	}

	public HistItemGroupDetailsPK getPrimaryKey() {
		return new HistItemGroupDetailsPK(_itemGroupDetailsSid, _actionFlag,
			_versionNo);
	}

	public void setPrimaryKey(HistItemGroupDetailsPK pk) {
		setItemGroupDetailsSid(pk.itemGroupDetailsSid);
		setActionFlag(pk.actionFlag);
		setVersionNo(pk.versionNo);
	}

	public int getItemGroupDetailsSid() {
		return _itemGroupDetailsSid;
	}

	public void setItemGroupDetailsSid(int itemGroupDetailsSid) {
		_itemGroupDetailsSid = itemGroupDetailsSid;
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

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getActionFlag() {
		return _actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		_actionFlag = actionFlag;
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

	public int getItemGroupSid() {
		return _itemGroupSid;
	}

	public void setItemGroupSid(int itemGroupSid) {
		_itemGroupSid = itemGroupSid;
	}

	private int _itemGroupDetailsSid;
	private Date _createdDate;
	private int _createdBy;
	private Date _actionDate;
	private int _itemMasterSid;
	private String _actionFlag;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _itemGroupSid;
}