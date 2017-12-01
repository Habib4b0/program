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
public class GcmItemDetailsSoap implements Serializable {
	public static GcmItemDetailsSoap toSoapModel(GcmItemDetails model) {
		GcmItemDetailsSoap soapModel = new GcmItemDetailsSoap();

		soapModel.setIfpDetailsEndDate(model.getIfpDetailsEndDate());
		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setIfpDetailsStartDate(model.getIfpDetailsStartDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setItemEndDate(model.getItemEndDate());
		soapModel.setGcmItemDetailsSid(model.getGcmItemDetailsSid());
		soapModel.setItemIfpDetailsSid(model.getItemIfpDetailsSid());
		soapModel.setItemId(model.getItemId());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setItemStartDate(model.getItemStartDate());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setItemName(model.getItemName());
		soapModel.setOperation(model.getOperation());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setItemStatusSid(model.getItemStatusSid());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setIfpModelSid(model.getIfpModelSid());
		soapModel.setTheraputicClass(model.getTheraputicClass());

		return soapModel;
	}

	public static GcmItemDetailsSoap[] toSoapModels(GcmItemDetails[] models) {
		GcmItemDetailsSoap[] soapModels = new GcmItemDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GcmItemDetailsSoap[][] toSoapModels(GcmItemDetails[][] models) {
		GcmItemDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GcmItemDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GcmItemDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GcmItemDetailsSoap[] toSoapModels(List<GcmItemDetails> models) {
		List<GcmItemDetailsSoap> soapModels = new ArrayList<GcmItemDetailsSoap>(models.size());

		for (GcmItemDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GcmItemDetailsSoap[soapModels.size()]);
	}

	public GcmItemDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _gcmItemDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setGcmItemDetailsSid(pk);
	}

	public Date getIfpDetailsEndDate() {
		return _ifpDetailsEndDate;
	}

	public void setIfpDetailsEndDate(Date ifpDetailsEndDate) {
		_ifpDetailsEndDate = ifpDetailsEndDate;
	}

	public String getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		_itemStatus = itemStatus;
	}

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	public Date getIfpDetailsStartDate() {
		return _ifpDetailsStartDate;
	}

	public void setIfpDetailsStartDate(Date ifpDetailsStartDate) {
		_ifpDetailsStartDate = ifpDetailsStartDate;
	}

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public Date getItemEndDate() {
		return _itemEndDate;
	}

	public void setItemEndDate(Date itemEndDate) {
		_itemEndDate = itemEndDate;
	}

	public int getGcmItemDetailsSid() {
		return _gcmItemDetailsSid;
	}

	public void setGcmItemDetailsSid(int gcmItemDetailsSid) {
		_gcmItemDetailsSid = gcmItemDetailsSid;
	}

	public int getItemIfpDetailsSid() {
		return _itemIfpDetailsSid;
	}

	public void setItemIfpDetailsSid(int itemIfpDetailsSid) {
		_itemIfpDetailsSid = itemIfpDetailsSid;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public Date getItemStartDate() {
		return _itemStartDate;
	}

	public void setItemStartDate(Date itemStartDate) {
		_itemStartDate = itemStartDate;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
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

	public int getItemStatusSid() {
		return _itemStatusSid;
	}

	public void setItemStatusSid(int itemStatusSid) {
		_itemStatusSid = itemStatusSid;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public int getIfpModelSid() {
		return _ifpModelSid;
	}

	public void setIfpModelSid(int ifpModelSid) {
		_ifpModelSid = ifpModelSid;
	}

	public String getTheraputicClass() {
		return _theraputicClass;
	}

	public void setTheraputicClass(String theraputicClass) {
		_theraputicClass = theraputicClass;
	}

	private Date _ifpDetailsEndDate;
	private String _itemStatus;
	private boolean _checkRecord;
	private Date _ifpDetailsStartDate;
	private int _userId;
	private int _itemMasterSid;
	private Date _itemEndDate;
	private int _gcmItemDetailsSid;
	private int _itemIfpDetailsSid;
	private String _itemId;
	private String _brandName;
	private Date _modifiedDate;
	private Date _createdDate;
	private int _createdBy;
	private Date _itemStartDate;
	private String _sessionId;
	private String _itemName;
	private String _operation;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _itemStatusSid;
	private String _itemNo;
	private int _ifpModelSid;
	private String _theraputicClass;
}