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
public class ItemGroupDetailsSoap implements Serializable {
	public static ItemGroupDetailsSoap toSoapModel(ItemGroupDetails model) {
		ItemGroupDetailsSoap soapModel = new ItemGroupDetailsSoap();

		soapModel.setItemGroupDetailsSid(model.getItemGroupDetailsSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setVersionNo(model.getVersionNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemGroupSid(model.getItemGroupSid());

		return soapModel;
	}

	public static ItemGroupDetailsSoap[] toSoapModels(ItemGroupDetails[] models) {
		ItemGroupDetailsSoap[] soapModels = new ItemGroupDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemGroupDetailsSoap[][] toSoapModels(
		ItemGroupDetails[][] models) {
		ItemGroupDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemGroupDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemGroupDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemGroupDetailsSoap[] toSoapModels(
		List<ItemGroupDetails> models) {
		List<ItemGroupDetailsSoap> soapModels = new ArrayList<ItemGroupDetailsSoap>(models.size());

		for (ItemGroupDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemGroupDetailsSoap[soapModels.size()]);
	}

	public ItemGroupDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _itemGroupDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setItemGroupDetailsSid(pk);
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

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
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
	private int _itemMasterSid;
	private int _versionNo;
	private int _modifiedBy;
	private Date _modifiedDate;
	private int _itemGroupSid;
}