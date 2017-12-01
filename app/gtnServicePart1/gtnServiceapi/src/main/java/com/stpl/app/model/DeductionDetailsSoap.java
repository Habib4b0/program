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
public class DeductionDetailsSoap implements Serializable {
	public static DeductionDetailsSoap toSoapModel(DeductionDetails model) {
		DeductionDetailsSoap soapModel = new DeductionDetailsSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setNetSalesFormulaMasterSid(model.getNetSalesFormulaMasterSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setRsContractSid(model.getRsContractSid());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setContractMasterSid(model.getContractMasterSid());
		soapModel.setDeductionDetailsSid(model.getDeductionDetailsSid());
		soapModel.setIndicator(model.getIndicator());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setSource(model.getSource());
		soapModel.setCdrModelSid(model.getCdrModelSid());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setDeductionSubType(model.getDeductionSubType());
		soapModel.setDeductionType(model.getDeductionType());
		soapModel.setDeductionCategory(model.getDeductionCategory());

		return soapModel;
	}

	public static DeductionDetailsSoap[] toSoapModels(DeductionDetails[] models) {
		DeductionDetailsSoap[] soapModels = new DeductionDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DeductionDetailsSoap[][] toSoapModels(
		DeductionDetails[][] models) {
		DeductionDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DeductionDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DeductionDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DeductionDetailsSoap[] toSoapModels(
		List<DeductionDetails> models) {
		List<DeductionDetailsSoap> soapModels = new ArrayList<DeductionDetailsSoap>(models.size());

		for (DeductionDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DeductionDetailsSoap[soapModels.size()]);
	}

	public DeductionDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _deductionDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setDeductionDetailsSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getNetSalesFormulaMasterSid() {
		return _netSalesFormulaMasterSid;
	}

	public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
		_netSalesFormulaMasterSid = netSalesFormulaMasterSid;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getRsContractSid() {
		return _rsContractSid;
	}

	public void setRsContractSid(int rsContractSid) {
		_rsContractSid = rsContractSid;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getContractMasterSid() {
		return _contractMasterSid;
	}

	public void setContractMasterSid(int contractMasterSid) {
		_contractMasterSid = contractMasterSid;
	}

	public int getDeductionDetailsSid() {
		return _deductionDetailsSid;
	}

	public void setDeductionDetailsSid(int deductionDetailsSid) {
		_deductionDetailsSid = deductionDetailsSid;
	}

	public String getIndicator() {
		return _indicator;
	}

	public void setIndicator(String indicator) {
		_indicator = indicator;
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

	public int getCdrModelSid() {
		return _cdrModelSid;
	}

	public void setCdrModelSid(int cdrModelSid) {
		_cdrModelSid = cdrModelSid;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getDeductionSubType() {
		return _deductionSubType;
	}

	public void setDeductionSubType(String deductionSubType) {
		_deductionSubType = deductionSubType;
	}

	public String getDeductionType() {
		return _deductionType;
	}

	public void setDeductionType(String deductionType) {
		_deductionType = deductionType;
	}

	public String getDeductionCategory() {
		return _deductionCategory;
	}

	public void setDeductionCategory(String deductionCategory) {
		_deductionCategory = deductionCategory;
	}

	private int _createdBy;
	private int _netSalesFormulaMasterSid;
	private int _modifiedBy;
	private int _rsContractSid;
	private Date _createdDate;
	private int _contractMasterSid;
	private int _deductionDetailsSid;
	private String _indicator;
	private Date _modifiedDate;
	private boolean _recordLockStatus;
	private String _source;
	private int _cdrModelSid;
	private String _inboundStatus;
	private String _deductionSubType;
	private String _deductionType;
	private String _deductionCategory;
}