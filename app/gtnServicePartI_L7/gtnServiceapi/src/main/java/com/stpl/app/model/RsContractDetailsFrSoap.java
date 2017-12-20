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
public class RsContractDetailsFrSoap implements Serializable {
	public static RsContractDetailsFrSoap toSoapModel(RsContractDetailsFr model) {
		RsContractDetailsFrSoap soapModel = new RsContractDetailsFrSoap();

		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setFormulaMethodId(model.getFormulaMethodId());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setRsContractDetailsFrSid(model.getRsContractDetailsFrSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setFormulaId(model.getFormulaId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRsContractDetailsSid(model.getRsContractDetailsSid());

		return soapModel;
	}

	public static RsContractDetailsFrSoap[] toSoapModels(
		RsContractDetailsFr[] models) {
		RsContractDetailsFrSoap[] soapModels = new RsContractDetailsFrSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RsContractDetailsFrSoap[][] toSoapModels(
		RsContractDetailsFr[][] models) {
		RsContractDetailsFrSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RsContractDetailsFrSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RsContractDetailsFrSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RsContractDetailsFrSoap[] toSoapModels(
		List<RsContractDetailsFr> models) {
		List<RsContractDetailsFrSoap> soapModels = new ArrayList<RsContractDetailsFrSoap>(models.size());

		for (RsContractDetailsFr model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RsContractDetailsFrSoap[soapModels.size()]);
	}

	public RsContractDetailsFrSoap() {
	}

	public int getPrimaryKey() {
		return _rsContractDetailsFrSid;
	}

	public void setPrimaryKey(int pk) {
		setRsContractDetailsFrSid(pk);
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

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getRsContractDetailsFrSid() {
		return _rsContractDetailsFrSid;
	}

	public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
		_rsContractDetailsFrSid = rsContractDetailsFrSid;
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

	public int getFormulaId() {
		return _formulaId;
	}

	public void setFormulaId(int formulaId) {
		_formulaId = formulaId;
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

	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _formulaMethodId;
	private int _itemMasterSid;
	private String _batchId;
	private int _rsContractDetailsFrSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _formulaId;
	private Date _modifiedDate;
	private int _rsContractDetailsSid;
}