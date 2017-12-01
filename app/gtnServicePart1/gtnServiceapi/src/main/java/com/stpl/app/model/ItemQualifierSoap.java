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
public class ItemQualifierSoap implements Serializable {
	public static ItemQualifierSoap toSoapModel(ItemQualifier model) {
		ItemQualifierSoap soapModel = new ItemQualifierSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemQualifierSid(model.getItemQualifierSid());
		soapModel.setSpecificEntityCode(model.getSpecificEntityCode());
		soapModel.setItemQualifierName(model.getItemQualifierName());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEffectiveDates(model.getEffectiveDates());
		soapModel.setNotes(model.getNotes());
		soapModel.setItemQualifierValue(model.getItemQualifierValue());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setSource(model.getSource());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static ItemQualifierSoap[] toSoapModels(ItemQualifier[] models) {
		ItemQualifierSoap[] soapModels = new ItemQualifierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemQualifierSoap[][] toSoapModels(ItemQualifier[][] models) {
		ItemQualifierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemQualifierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemQualifierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemQualifierSoap[] toSoapModels(List<ItemQualifier> models) {
		List<ItemQualifierSoap> soapModels = new ArrayList<ItemQualifierSoap>(models.size());

		for (ItemQualifier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemQualifierSoap[soapModels.size()]);
	}

	public ItemQualifierSoap() {
	}

	public int getPrimaryKey() {
		return _itemQualifierSid;
	}

	public void setPrimaryKey(int pk) {
		setItemQualifierSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getItemQualifierSid() {
		return _itemQualifierSid;
	}

	public void setItemQualifierSid(int itemQualifierSid) {
		_itemQualifierSid = itemQualifierSid;
	}

	public String getSpecificEntityCode() {
		return _specificEntityCode;
	}

	public void setSpecificEntityCode(String specificEntityCode) {
		_specificEntityCode = specificEntityCode;
	}

	public String getItemQualifierName() {
		return _itemQualifierName;
	}

	public void setItemQualifierName(String itemQualifierName) {
		_itemQualifierName = itemQualifierName;
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

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getEffectiveDates() {
		return _effectiveDates;
	}

	public void setEffectiveDates(String effectiveDates) {
		_effectiveDates = effectiveDates;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
	}

	public String getItemQualifierValue() {
		return _itemQualifierValue;
	}

	public void setItemQualifierValue(String itemQualifierValue) {
		_itemQualifierValue = itemQualifierValue;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _itemQualifierSid;
	private String _specificEntityCode;
	private String _itemQualifierName;
	private int _modifiedBy;
	private Date _createdDate;
	private String _batchId;
	private Date _modifiedDate;
	private String _effectiveDates;
	private String _notes;
	private String _itemQualifierValue;
	private boolean _recordLockStatus;
	private String _source;
	private String _inboundStatus;
}