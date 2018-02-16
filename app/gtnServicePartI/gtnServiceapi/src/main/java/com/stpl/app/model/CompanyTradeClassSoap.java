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
public class CompanyTradeClassSoap implements Serializable {
	public static CompanyTradeClassSoap toSoapModel(CompanyTradeClass model) {
		CompanyTradeClassSoap soapModel = new CompanyTradeClassSoap();

		soapModel.setPriorTradeClass(model.getPriorTradeClass());
		soapModel.setCompanyTradeClassSid(model.getCompanyTradeClassSid());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setPriorTradeClassStartDate(model.getPriorTradeClassStartDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTradeClassEndDate(model.getTradeClassEndDate());
		soapModel.setTradeClassStartDate(model.getTradeClassStartDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyTradeClass(model.getCompanyTradeClass());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static CompanyTradeClassSoap[] toSoapModels(
		CompanyTradeClass[] models) {
		CompanyTradeClassSoap[] soapModels = new CompanyTradeClassSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyTradeClassSoap[][] toSoapModels(
		CompanyTradeClass[][] models) {
		CompanyTradeClassSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyTradeClassSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyTradeClassSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyTradeClassSoap[] toSoapModels(
		List<CompanyTradeClass> models) {
		List<CompanyTradeClassSoap> soapModels = new ArrayList<CompanyTradeClassSoap>(models.size());

		for (CompanyTradeClass model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyTradeClassSoap[soapModels.size()]);
	}

	public CompanyTradeClassSoap() {
	}

	public int getPrimaryKey() {
		return _companyTradeClassSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyTradeClassSid(pk);
	}

	public int getPriorTradeClass() {
		return _priorTradeClass;
	}

	public void setPriorTradeClass(int priorTradeClass) {
		_priorTradeClass = priorTradeClass;
	}

	public int getCompanyTradeClassSid() {
		return _companyTradeClassSid;
	}

	public void setCompanyTradeClassSid(int companyTradeClassSid) {
		_companyTradeClassSid = companyTradeClassSid;
	}

	public Date getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public Date getPriorTradeClassStartDate() {
		return _priorTradeClassStartDate;
	}

	public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
		_priorTradeClassStartDate = priorTradeClassStartDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public Date getTradeClassEndDate() {
		return _tradeClassEndDate;
	}

	public void setTradeClassEndDate(Date tradeClassEndDate) {
		_tradeClassEndDate = tradeClassEndDate;
	}

	public Date getTradeClassStartDate() {
		return _tradeClassStartDate;
	}

	public void setTradeClassStartDate(Date tradeClassStartDate) {
		_tradeClassStartDate = tradeClassStartDate;
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

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getCompanyTradeClass() {
		return _companyTradeClass;
	}

	public void setCompanyTradeClass(int companyTradeClass) {
		_companyTradeClass = companyTradeClass;
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

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
	}

	private int _priorTradeClass;
	private int _companyTradeClassSid;
	private Date _lastUpdatedDate;
	private Date _priorTradeClassStartDate;
	private Date _modifiedDate;
	private Date _tradeClassEndDate;
	private Date _tradeClassStartDate;
	private boolean _recordLockStatus;
	private Date _createdDate;
	private String _source;
	private int _createdBy;
	private String _batchId;
	private int _companyTradeClass;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _companyMasterSid;
}