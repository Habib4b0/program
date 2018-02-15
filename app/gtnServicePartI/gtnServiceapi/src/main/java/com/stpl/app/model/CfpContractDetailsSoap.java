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
public class CfpContractDetailsSoap implements Serializable {
	public static CfpContractDetailsSoap toSoapModel(CfpContractDetails model) {
		CfpContractDetailsSoap soapModel = new CfpContractDetailsSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setTradeClass(model.getTradeClass());
		soapModel.setTradeClassEndDate(model.getTradeClassEndDate());
		soapModel.setCfpContractSid(model.getCfpContractSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCompanyStartDate(model.getCompanyStartDate());
		soapModel.setTradeClassStartDate(model.getTradeClassStartDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCfpContractAttachedDate(model.getCfpContractAttachedDate());
		soapModel.setCompanyEndDate(model.getCompanyEndDate());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setSource(model.getSource());
		soapModel.setCfpContractDetailsSid(model.getCfpContractDetailsSid());
		soapModel.setCfpContractAttachedStatus(model.getCfpContractAttachedStatus());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static CfpContractDetailsSoap[] toSoapModels(
		CfpContractDetails[] models) {
		CfpContractDetailsSoap[] soapModels = new CfpContractDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CfpContractDetailsSoap[][] toSoapModels(
		CfpContractDetails[][] models) {
		CfpContractDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CfpContractDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CfpContractDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CfpContractDetailsSoap[] toSoapModels(
		List<CfpContractDetails> models) {
		List<CfpContractDetailsSoap> soapModels = new ArrayList<CfpContractDetailsSoap>(models.size());

		for (CfpContractDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CfpContractDetailsSoap[soapModels.size()]);
	}

	public CfpContractDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _cfpContractDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCfpContractDetailsSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getTradeClass() {
		return _tradeClass;
	}

	public void setTradeClass(int tradeClass) {
		_tradeClass = tradeClass;
	}

	public Date getTradeClassEndDate() {
		return _tradeClassEndDate;
	}

	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_tradeClassEndDate = tradeClassEndDate;
	}

	public int getCfpContractSid() {
		return _cfpContractSid;
	}

	public void setCfpContractSid(int cfpContractSid) {
		_cfpContractSid = cfpContractSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCompanyStartDate() {
		return _companyStartDate;
	}

	public void setCompanyStartDate(Date companyStartDate) {
		_companyStartDate = companyStartDate;
	}

	public Date getTradeClassStartDate() {
		return _tradeClassStartDate;
	}

	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_tradeClassStartDate = tradeClassStartDate;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public Date getCfpContractAttachedDate() {
		return _cfpContractAttachedDate;
	}

	public void setCfpContractAttachedDate(Date cfpContractAttachedDate) {
		_cfpContractAttachedDate = cfpContractAttachedDate;
	}

	public Date getCompanyEndDate() {
		return _companyEndDate;
	}

	public void setCompanyEndDate(Date companyEndDate) {
		_companyEndDate = companyEndDate;
	}

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getCfpContractDetailsSid() {
		return _cfpContractDetailsSid;
	}

	public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
		_cfpContractDetailsSid = cfpContractDetailsSid;
	}

	public int getCfpContractAttachedStatus() {
		return _cfpContractAttachedStatus;
	}

	public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
		_cfpContractAttachedStatus = cfpContractAttachedStatus;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _tradeClass;
	private Date _tradeClassEndDate;
	private int _cfpContractSid;
	private int _modifiedBy;
	private Date _companyStartDate;
	private Date _tradeClassStartDate;
	private Date _createdDate;
	private Date _cfpContractAttachedDate;
	private Date _companyEndDate;
	private int _companyMasterSid;
	private String _batchId;
	private Date _modifiedDate;
	private boolean _recordLockStatus;
	private String _source;
	private int _cfpContractDetailsSid;
	private int _cfpContractAttachedStatus;
	private String _inboundStatus;
}