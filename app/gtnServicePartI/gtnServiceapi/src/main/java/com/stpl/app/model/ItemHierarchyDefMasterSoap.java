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
public class ItemHierarchyDefMasterSoap implements Serializable {
	public static ItemHierarchyDefMasterSoap toSoapModel(
		ItemHierarchyDefMaster model) {
		ItemHierarchyDefMasterSoap soapModel = new ItemHierarchyDefMasterSoap();

		soapModel.setItemHierarchyDefMasterSid(model.getItemHierarchyDefMasterSid());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setAlias(model.getAlias());
		soapModel.setSource(model.getSource());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setBpiLvl(model.getBpiLvl());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMember(model.getMember());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static ItemHierarchyDefMasterSoap[] toSoapModels(
		ItemHierarchyDefMaster[] models) {
		ItemHierarchyDefMasterSoap[] soapModels = new ItemHierarchyDefMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ItemHierarchyDefMasterSoap[][] toSoapModels(
		ItemHierarchyDefMaster[][] models) {
		ItemHierarchyDefMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ItemHierarchyDefMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ItemHierarchyDefMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ItemHierarchyDefMasterSoap[] toSoapModels(
		List<ItemHierarchyDefMaster> models) {
		List<ItemHierarchyDefMasterSoap> soapModels = new ArrayList<ItemHierarchyDefMasterSoap>(models.size());

		for (ItemHierarchyDefMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ItemHierarchyDefMasterSoap[soapModels.size()]);
	}

	public ItemHierarchyDefMasterSoap() {
	}

	public int getPrimaryKey() {
		return _itemHierarchyDefMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setItemHierarchyDefMasterSid(pk);
	}

	public int getItemHierarchyDefMasterSid() {
		return _itemHierarchyDefMasterSid;
	}

	public void setItemHierarchyDefMasterSid(int itemHierarchyDefMasterSid) {
		_itemHierarchyDefMasterSid = itemHierarchyDefMasterSid;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
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

	public String getAlias() {
		return _alias;
	}

	public void setAlias(String alias) {
		_alias = alias;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getBpiLvl() {
		return _bpiLvl;
	}

	public void setBpiLvl(String bpiLvl) {
		_bpiLvl = bpiLvl;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getMember() {
		return _member;
	}

	public void setMember(String member) {
		_member = member;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _itemHierarchyDefMasterSid;
	private int _createdBy;
	private boolean _recordLockStatus;
	private int _modifiedBy;
	private Date _createdDate;
	private String _alias;
	private String _source;
	private String _batchId;
	private String _bpiLvl;
	private Date _modifiedDate;
	private String _member;
	private String _inboundStatus;
}