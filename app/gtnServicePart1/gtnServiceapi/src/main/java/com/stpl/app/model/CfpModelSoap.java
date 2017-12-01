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
public class CfpModelSoap implements Serializable {
	public static CfpModelSoap toSoapModel(CfpModel model) {
		CfpModelSoap soapModel = new CfpModelSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCfpType(model.getCfpType());
		soapModel.setCfpTradeClass(model.getCfpTradeClass());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCfpNo(model.getCfpNo());
		soapModel.setCfpModelSid(model.getCfpModelSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setInternalNotes(model.getInternalNotes());
		soapModel.setCfpDesignation(model.getCfpDesignation());
		soapModel.setSalesInclusion(model.getSalesInclusion());
		soapModel.setCfpName(model.getCfpName());
		soapModel.setCfpCategory(model.getCfpCategory());
		soapModel.setSource(model.getSource());
		soapModel.setCfpId(model.getCfpId());
		soapModel.setCfpStatus(model.getCfpStatus());
		soapModel.setParentCfpId(model.getParentCfpId());
		soapModel.setCfpStartDate(model.getCfpStartDate());
		soapModel.setCfpEndDate(model.getCfpEndDate());
		soapModel.setParentCfpName(model.getParentCfpName());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static CfpModelSoap[] toSoapModels(CfpModel[] models) {
		CfpModelSoap[] soapModels = new CfpModelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CfpModelSoap[][] toSoapModels(CfpModel[][] models) {
		CfpModelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CfpModelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CfpModelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CfpModelSoap[] toSoapModels(List<CfpModel> models) {
		List<CfpModelSoap> soapModels = new ArrayList<CfpModelSoap>(models.size());

		for (CfpModel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CfpModelSoap[soapModels.size()]);
	}

	public CfpModelSoap() {
	}

	public int getPrimaryKey() {
		return _cfpModelSid;
	}

	public void setPrimaryKey(int pk) {
		setCfpModelSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getCfpType() {
		return _cfpType;
	}

	public void setCfpType(int cfpType) {
		_cfpType = cfpType;
	}

	public int getCfpTradeClass() {
		return _cfpTradeClass;
	}

	public void setCfpTradeClass(int cfpTradeClass) {
		_cfpTradeClass = cfpTradeClass;
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

	public String getCfpNo() {
		return _cfpNo;
	}

	public void setCfpNo(String cfpNo) {
		_cfpNo = cfpNo;
	}

	public int getCfpModelSid() {
		return _cfpModelSid;
	}

	public void setCfpModelSid(int cfpModelSid) {
		_cfpModelSid = cfpModelSid;
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

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getInternalNotes() {
		return _internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	public String getCfpDesignation() {
		return _cfpDesignation;
	}

	public void setCfpDesignation(String cfpDesignation) {
		_cfpDesignation = cfpDesignation;
	}

	public int getSalesInclusion() {
		return _salesInclusion;
	}

	public void setSalesInclusion(int salesInclusion) {
		_salesInclusion = salesInclusion;
	}

	public String getCfpName() {
		return _cfpName;
	}

	public void setCfpName(String cfpName) {
		_cfpName = cfpName;
	}

	public int getCfpCategory() {
		return _cfpCategory;
	}

	public void setCfpCategory(int cfpCategory) {
		_cfpCategory = cfpCategory;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getCfpId() {
		return _cfpId;
	}

	public void setCfpId(String cfpId) {
		_cfpId = cfpId;
	}

	public int getCfpStatus() {
		return _cfpStatus;
	}

	public void setCfpStatus(int cfpStatus) {
		_cfpStatus = cfpStatus;
	}

	public int getParentCfpId() {
		return _parentCfpId;
	}

	public void setParentCfpId(int parentCfpId) {
		_parentCfpId = parentCfpId;
	}

	public Date getCfpStartDate() {
		return _cfpStartDate;
	}

	public void setCfpStartDate(Date cfpStartDate) {
		_cfpStartDate = cfpStartDate;
	}

	public Date getCfpEndDate() {
		return _cfpEndDate;
	}

	public void setCfpEndDate(Date cfpEndDate) {
		_cfpEndDate = cfpEndDate;
	}

	public String getParentCfpName() {
		return _parentCfpName;
	}

	public void setParentCfpName(String parentCfpName) {
		_parentCfpName = parentCfpName;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _cfpType;
	private int _cfpTradeClass;
	private int _modifiedBy;
	private Date _createdDate;
	private String _cfpNo;
	private int _cfpModelSid;
	private String _batchId;
	private Date _modifiedDate;
	private boolean _recordLockStatus;
	private String _internalNotes;
	private String _cfpDesignation;
	private int _salesInclusion;
	private String _cfpName;
	private int _cfpCategory;
	private String _source;
	private String _cfpId;
	private int _cfpStatus;
	private int _parentCfpId;
	private Date _cfpStartDate;
	private Date _cfpEndDate;
	private String _parentCfpName;
	private String _inboundStatus;
}