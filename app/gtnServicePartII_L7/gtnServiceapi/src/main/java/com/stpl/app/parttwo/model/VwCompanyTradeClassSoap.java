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

package com.stpl.app.parttwo.model;

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
public class VwCompanyTradeClassSoap implements Serializable {
	public static VwCompanyTradeClassSoap toSoapModel(VwCompanyTradeClass model) {
		VwCompanyTradeClassSoap soapModel = new VwCompanyTradeClassSoap();

		soapModel.setPriorTradeClass(model.getPriorTradeClass());
		soapModel.setCompanyTradeClassSid(model.getCompanyTradeClassSid());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setPriorTradeClassStartDate(model.getPriorTradeClassStartDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTradeClassEndDate(model.getTradeClassEndDate());
		soapModel.setTradeClassStartDate(model.getTradeClassStartDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCompanyTradeClass(model.getCompanyTradeClass());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setModifiedBy(model.getModifiedBy());

		return soapModel;
	}

	public static VwCompanyTradeClassSoap[] toSoapModels(
		VwCompanyTradeClass[] models) {
		VwCompanyTradeClassSoap[] soapModels = new VwCompanyTradeClassSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyTradeClassSoap[][] toSoapModels(
		VwCompanyTradeClass[][] models) {
		VwCompanyTradeClassSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwCompanyTradeClassSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwCompanyTradeClassSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyTradeClassSoap[] toSoapModels(
		List<VwCompanyTradeClass> models) {
		List<VwCompanyTradeClassSoap> soapModels = new ArrayList<VwCompanyTradeClassSoap>(models.size());

		for (VwCompanyTradeClass model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwCompanyTradeClassSoap[soapModels.size()]);
	}

	public VwCompanyTradeClassSoap() {
	}

	public int getPrimaryKey() {
		return _companyTradeClassSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyTradeClassSid(pk);
	}

	public String getPriorTradeClass() {
		return _priorTradeClass;
	}

	public void setPriorTradeClass(String priorTradeClass) {
		_priorTradeClass = priorTradeClass;
	}

	public int getCompanyTradeClassSid() {
		return _companyTradeClassSid;
	}

	public void setCompanyTradeClassSid(int companyTradeClassSid) {
		_companyTradeClassSid = companyTradeClassSid;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCompanyTradeClass() {
		return _companyTradeClass;
	}

	public void setCompanyTradeClass(String companyTradeClass) {
		_companyTradeClass = companyTradeClass;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	private String _priorTradeClass;
	private int _companyTradeClassSid;
	private String _companyId;
	private Date _lastUpdatedDate;
	private Date _priorTradeClassStartDate;
	private Date _modifiedDate;
	private Date _tradeClassEndDate;
	private Date _tradeClassStartDate;
	private String _source;
	private String _createdBy;
	private Date _createdDate;
	private String _companyTradeClass;
	private String _batchId;
	private String _addChgDelIndicator;
	private String _modifiedBy;
}