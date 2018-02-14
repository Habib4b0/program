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
public class IfpDetailsSoap implements Serializable {
	public static IfpDetailsSoap toSoapModel(IfpDetails model) {
		IfpDetailsSoap soapModel = new IfpDetailsSoap();

		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemIfpAttachedDate(model.getItemIfpAttachedDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setIfpDetailsSid(model.getIfpDetailsSid());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setItemIfpAttachedStatus(model.getItemIfpAttachedStatus());

		return soapModel;
	}

	public static IfpDetailsSoap[] toSoapModels(IfpDetails[] models) {
		IfpDetailsSoap[] soapModels = new IfpDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IfpDetailsSoap[][] toSoapModels(IfpDetails[][] models) {
		IfpDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IfpDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IfpDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IfpDetailsSoap[] toSoapModels(List<IfpDetails> models) {
		List<IfpDetailsSoap> soapModels = new ArrayList<IfpDetailsSoap>(models.size());

		for (IfpDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IfpDetailsSoap[soapModels.size()]);
	}

	public IfpDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _ifpDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setIfpDetailsSid(pk);
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public Date getItemIfpAttachedDate() {
		return _itemIfpAttachedDate;
	}

	public void setItemIfpAttachedDate(Date itemIfpAttachedDate) {
		_itemIfpAttachedDate = itemIfpAttachedDate;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getIfpDetailsSid() {
		return _ifpDetailsSid;
	}

	public void setIfpDetailsSid(int ifpDetailsSid) {
		_ifpDetailsSid = ifpDetailsSid;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public int getItemIfpAttachedStatus() {
		return _itemIfpAttachedStatus;
	}

	public void setItemIfpAttachedStatus(int itemIfpAttachedStatus) {
		_itemIfpAttachedStatus = itemIfpAttachedStatus;
	}

	private int _itemMasterSid;
	private Date _endDate;
	private Date _modifiedDate;
	private boolean _recordLockStatus;
	private Date _startDate;
	private Date _createdDate;
	private String _source;
	private int _createdBy;
	private Date _itemIfpAttachedDate;
	private String _batchId;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _ifpDetailsSid;
	private int _ifpModelSid;
	private int _itemIfpAttachedStatus;
}