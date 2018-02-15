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
public class GcmCompanyDetailsSoap implements Serializable {
	public static GcmCompanyDetailsSoap toSoapModel(GcmCompanyDetails model) {
		GcmCompanyDetailsSoap soapModel = new GcmCompanyDetailsSoap();

		soapModel.setCheckRecord(model.getCheckRecord());
		soapModel.setUserId(model.getUserId());
		soapModel.setModuleName(model.getModuleName());
		soapModel.setCompanyStringId(model.getCompanyStringId());
		soapModel.setCfpDetailsTradeClass(model.getCfpDetailsTradeClass());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGcmCompanyDetailsSid(model.getGcmCompanyDetailsSid());
		soapModel.setItemCfpDetailsSid(model.getItemCfpDetailsSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyStartDate(model.getCompanyStartDate());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setCompanyStatus(model.getCompanyStatus());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setCompanyEndDate(model.getCompanyEndDate());
		soapModel.setCfpDetailsStartDate(model.getCfpDetailsStartDate());
		soapModel.setOperation(model.getOperation());
		soapModel.setCfpModelSid(model.getCfpModelSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setSubModuleName(model.getSubModuleName());
		soapModel.setCfpDetailsEndDate(model.getCfpDetailsEndDate());
		soapModel.setCompanyStatusSid(model.getCompanyStatusSid());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static GcmCompanyDetailsSoap[] toSoapModels(
		GcmCompanyDetails[] models) {
		GcmCompanyDetailsSoap[] soapModels = new GcmCompanyDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GcmCompanyDetailsSoap[][] toSoapModels(
		GcmCompanyDetails[][] models) {
		GcmCompanyDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GcmCompanyDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GcmCompanyDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GcmCompanyDetailsSoap[] toSoapModels(
		List<GcmCompanyDetails> models) {
		List<GcmCompanyDetailsSoap> soapModels = new ArrayList<GcmCompanyDetailsSoap>(models.size());

		for (GcmCompanyDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GcmCompanyDetailsSoap[soapModels.size()]);
	}

	public GcmCompanyDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _gcmCompanyDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setGcmCompanyDetailsSid(pk);
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

	public int getUserId() {
		return _userId;
	}

	public void setUserId(int userId) {
		_userId = userId;
	}

	public String getModuleName() {
		return _moduleName;
	}

	public void setModuleName(String moduleName) {
		_moduleName = moduleName;
	}

	public String getCompanyStringId() {
		return _companyStringId;
	}

	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	public String getCfpDetailsTradeClass() {
		return _cfpDetailsTradeClass;
	}

	public void setCfpDetailsTradeClass(String cfpDetailsTradeClass) {
		_cfpDetailsTradeClass = cfpDetailsTradeClass;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getGcmCompanyDetailsSid() {
		return _gcmCompanyDetailsSid;
	}

	public void setGcmCompanyDetailsSid(int gcmCompanyDetailsSid) {
		_gcmCompanyDetailsSid = gcmCompanyDetailsSid;
	}

	public int getItemCfpDetailsSid() {
		return _itemCfpDetailsSid;
	}

	public void setItemCfpDetailsSid(int itemCfpDetailsSid) {
		_itemCfpDetailsSid = itemCfpDetailsSid;
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

	public Date getCompanyStartDate() {
		return _companyStartDate;
	}

	public void setCompanyStartDate(Date companyStartDate) {
		_companyStartDate = companyStartDate;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getCompanyStatus() {
		return _companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		_companyStatus = companyStatus;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public Date getCompanyEndDate() {
		return _companyEndDate;
	}

	public void setCompanyEndDate(Date companyEndDate) {
		_companyEndDate = companyEndDate;
	}

	public Date getCfpDetailsStartDate() {
		return _cfpDetailsStartDate;
	}

	public void setCfpDetailsStartDate(Date cfpDetailsStartDate) {
		_cfpDetailsStartDate = cfpDetailsStartDate;
	}

	public String getOperation() {
		return _operation;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	public int getCfpModelSid() {
		return _cfpModelSid;
	}

	public void setCfpModelSid(int cfpModelSid) {
		_cfpModelSid = cfpModelSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getSubModuleName() {
		return _subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		_subModuleName = subModuleName;
	}

	public Date getCfpDetailsEndDate() {
		return _cfpDetailsEndDate;
	}

	public void setCfpDetailsEndDate(Date cfpDetailsEndDate) {
		_cfpDetailsEndDate = cfpDetailsEndDate;
	}

	public int getCompanyStatusSid() {
		return _companyStatusSid;
	}

	public void setCompanyStatusSid(int companyStatusSid) {
		_companyStatusSid = companyStatusSid;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private boolean _checkRecord;
	private int _userId;
	private String _moduleName;
	private String _companyStringId;
	private String _cfpDetailsTradeClass;
	private String _companyName;
	private Date _modifiedDate;
	private int _gcmCompanyDetailsSid;
	private int _itemCfpDetailsSid;
	private Date _createdDate;
	private int _createdBy;
	private Date _companyStartDate;
	private String _companyNo;
	private String _companyStatus;
	private String _sessionId;
	private Date _companyEndDate;
	private Date _cfpDetailsStartDate;
	private String _operation;
	private int _cfpModelSid;
	private int _modifiedBy;
	private String _subModuleName;
	private Date _cfpDetailsEndDate;
	private int _companyStatusSid;
	private int _companyMasterSid;
}