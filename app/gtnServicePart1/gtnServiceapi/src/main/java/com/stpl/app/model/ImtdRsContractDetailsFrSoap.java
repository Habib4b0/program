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
public class ImtdRsContractDetailsFrSoap implements Serializable {
	public static ImtdRsContractDetailsFrSoap toSoapModel(
		ImtdRsContractDetailsFr model) {
		ImtdRsContractDetailsFrSoap soapModel = new ImtdRsContractDetailsFrSoap();

		soapModel.setImtdRsContractDetailsFrSid(model.getImtdRsContractDetailsFrSid());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setRsContractDetailsFrSid(model.getRsContractDetailsFrSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRsContractDetailsSid(model.getRsContractDetailsSid());
		soapModel.setImtdItemPriceRebateDetailsSid(model.getImtdItemPriceRebateDetailsSid());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setImtdCreatedDate(model.getImtdCreatedDate());
		soapModel.setSessionId(model.getSessionId());
		soapModel.setUsersId(model.getUsersId());
		soapModel.setOperation(model.getOperation());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setFormulaId(model.getFormulaId());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static ImtdRsContractDetailsFrSoap[] toSoapModels(
		ImtdRsContractDetailsFr[] models) {
		ImtdRsContractDetailsFrSoap[] soapModels = new ImtdRsContractDetailsFrSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ImtdRsContractDetailsFrSoap[][] toSoapModels(
		ImtdRsContractDetailsFr[][] models) {
		ImtdRsContractDetailsFrSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ImtdRsContractDetailsFrSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ImtdRsContractDetailsFrSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ImtdRsContractDetailsFrSoap[] toSoapModels(
		List<ImtdRsContractDetailsFr> models) {
		List<ImtdRsContractDetailsFrSoap> soapModels = new ArrayList<ImtdRsContractDetailsFrSoap>(models.size());

		for (ImtdRsContractDetailsFr model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ImtdRsContractDetailsFrSoap[soapModels.size()]);
	}

	public ImtdRsContractDetailsFrSoap() {
	}

	public int getPrimaryKey() {
		return _imtdRsContractDetailsFrSid;
	}

	public void setPrimaryKey(int pk) {
		setImtdRsContractDetailsFrSid(pk);
	}

	public int getImtdRsContractDetailsFrSid() {
		return _imtdRsContractDetailsFrSid;
	}

	public void setImtdRsContractDetailsFrSid(int imtdRsContractDetailsFrSid) {
		_imtdRsContractDetailsFrSid = imtdRsContractDetailsFrSid;
	}

	public String getFormulaMethodId() {
		return _formulaMethodId;
	}

	public void setFormulaMethodId(String formulaMethodId) {
		_formulaMethodId = formulaMethodId;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getRsContractDetailsFrSid() {
		return _rsContractDetailsFrSid;
	}

	public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
		_rsContractDetailsFrSid = rsContractDetailsFrSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getRsContractDetailsSid() {
		return _rsContractDetailsSid;
	}

	public void setRsContractDetailsSid(int rsContractDetailsSid) {
		_rsContractDetailsSid = rsContractDetailsSid;
	}

	public int getImtdItemPriceRebateDetailsSid() {
		return _imtdItemPriceRebateDetailsSid;
	}

	public void setImtdItemPriceRebateDetailsSid(
		int imtdItemPriceRebateDetailsSid) {
		_imtdItemPriceRebateDetailsSid = imtdItemPriceRebateDetailsSid;
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

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
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

	public Date getImtdCreatedDate() {
		return _imtdCreatedDate;
	}

	public void setImtdCreatedDate(Date imtdCreatedDate) {
		_imtdCreatedDate = imtdCreatedDate;
	}

	public String getSessionId() {
		return _sessionId;
	}

	public void setSessionId(String sessionId) {
		_sessionId = sessionId;
	}

	public String getUsersId() {
		return _usersId;
	}

	public void setUsersId(String usersId) {
		_usersId = usersId;
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

	public int getFormulaId() {
		return _formulaId;
	}

	public void setFormulaId(int formulaId) {
		_formulaId = formulaId;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _imtdRsContractDetailsFrSid;
	private String _formulaMethodId;
	private int _itemMasterSid;
	private int _rsContractDetailsFrSid;
	private Date _modifiedDate;
	private int _rsContractDetailsSid;
	private int _imtdItemPriceRebateDetailsSid;
	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _batchId;
	private Date _imtdCreatedDate;
	private String _sessionId;
	private String _usersId;
	private String _operation;
	private int _modifiedBy;
	private int _formulaId;
	private String _inboundStatus;
}