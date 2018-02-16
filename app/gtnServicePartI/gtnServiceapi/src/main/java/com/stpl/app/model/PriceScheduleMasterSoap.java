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
public class PriceScheduleMasterSoap implements Serializable {
	public static PriceScheduleMasterSoap toSoapModel(PriceScheduleMaster model) {
		PriceScheduleMasterSoap soapModel = new PriceScheduleMasterSoap();

		soapModel.setParentPriceScheduleName(model.getParentPriceScheduleName());
		soapModel.setParentPriceScheduleId(model.getParentPriceScheduleId());
		soapModel.setPriceScheduleStartDate(model.getPriceScheduleStartDate());
		soapModel.setPriceScheduleNo(model.getPriceScheduleNo());
		soapModel.setPriceScheduleName(model.getPriceScheduleName());
		soapModel.setPriceScheduleId(model.getPriceScheduleId());
		soapModel.setPriceScheduleType(model.getPriceScheduleType());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPriceScheduleSystemId(model.getPriceScheduleSystemId());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setPriceScheduleDesignation(model.getPriceScheduleDesignation());
		soapModel.setPriceScheduleEndDate(model.getPriceScheduleEndDate());
		soapModel.setPriceScheduleStatus(model.getPriceScheduleStatus());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setPriceScheduleCategory(model.getPriceScheduleCategory());
		soapModel.setTradeClass(model.getTradeClass());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedBy(model.getModifiedBy());

		return soapModel;
	}

	public static PriceScheduleMasterSoap[] toSoapModels(
		PriceScheduleMaster[] models) {
		PriceScheduleMasterSoap[] soapModels = new PriceScheduleMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PriceScheduleMasterSoap[][] toSoapModels(
		PriceScheduleMaster[][] models) {
		PriceScheduleMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PriceScheduleMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PriceScheduleMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PriceScheduleMasterSoap[] toSoapModels(
		List<PriceScheduleMaster> models) {
		List<PriceScheduleMasterSoap> soapModels = new ArrayList<PriceScheduleMasterSoap>(models.size());

		for (PriceScheduleMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PriceScheduleMasterSoap[soapModels.size()]);
	}

	public PriceScheduleMasterSoap() {
	}

	public int getPrimaryKey() {
		return _priceScheduleSystemId;
	}

	public void setPrimaryKey(int pk) {
		setPriceScheduleSystemId(pk);
	}

	public String getParentPriceScheduleName() {
		return _parentPriceScheduleName;
	}

	public void setParentPriceScheduleName(String parentPriceScheduleName) {
		_parentPriceScheduleName = parentPriceScheduleName;
	}

	public String getParentPriceScheduleId() {
		return _parentPriceScheduleId;
	}

	public void setParentPriceScheduleId(String parentPriceScheduleId) {
		_parentPriceScheduleId = parentPriceScheduleId;
	}

	public Date getPriceScheduleStartDate() {
		return _priceScheduleStartDate;
	}

	public void setPriceScheduleStartDate(Date priceScheduleStartDate) {
		_priceScheduleStartDate = priceScheduleStartDate;
	}

	public String getPriceScheduleNo() {
		return _priceScheduleNo;
	}

	public void setPriceScheduleNo(String priceScheduleNo) {
		_priceScheduleNo = priceScheduleNo;
	}

	public String getPriceScheduleName() {
		return _priceScheduleName;
	}

	public void setPriceScheduleName(String priceScheduleName) {
		_priceScheduleName = priceScheduleName;
	}

	public String getPriceScheduleId() {
		return _priceScheduleId;
	}

	public void setPriceScheduleId(String priceScheduleId) {
		_priceScheduleId = priceScheduleId;
	}

	public String getPriceScheduleType() {
		return _priceScheduleType;
	}

	public void setPriceScheduleType(String priceScheduleType) {
		_priceScheduleType = priceScheduleType;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getPriceScheduleSystemId() {
		return _priceScheduleSystemId;
	}

	public void setPriceScheduleSystemId(int priceScheduleSystemId) {
		_priceScheduleSystemId = priceScheduleSystemId;
	}

	public String getRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(String recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public String getPriceScheduleDesignation() {
		return _priceScheduleDesignation;
	}

	public void setPriceScheduleDesignation(String priceScheduleDesignation) {
		_priceScheduleDesignation = priceScheduleDesignation;
	}

	public Date getPriceScheduleEndDate() {
		return _priceScheduleEndDate;
	}

	public void setPriceScheduleEndDate(Date priceScheduleEndDate) {
		_priceScheduleEndDate = priceScheduleEndDate;
	}

	public String getPriceScheduleStatus() {
		return _priceScheduleStatus;
	}

	public void setPriceScheduleStatus(String priceScheduleStatus) {
		_priceScheduleStatus = priceScheduleStatus;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getPriceScheduleCategory() {
		return _priceScheduleCategory;
	}

	public void setPriceScheduleCategory(String priceScheduleCategory) {
		_priceScheduleCategory = priceScheduleCategory;
	}

	public String getTradeClass() {
		return _tradeClass;
	}

	public void setTradeClass(String tradeClass) {
		_tradeClass = tradeClass;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	private String _parentPriceScheduleName;
	private String _parentPriceScheduleId;
	private Date _priceScheduleStartDate;
	private String _priceScheduleNo;
	private String _priceScheduleName;
	private String _priceScheduleId;
	private String _priceScheduleType;
	private Date _modifiedDate;
	private int _priceScheduleSystemId;
	private String _recordLockStatus;
	private Date _createdDate;
	private String _createdBy;
	private String _priceScheduleDesignation;
	private Date _priceScheduleEndDate;
	private String _priceScheduleStatus;
	private String _batchId;
	private String _priceScheduleCategory;
	private String _tradeClass;
	private String _inboundStatus;
	private String _modifiedBy;
}