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
public class IvldcompanyIdentifierSoap implements Serializable {
	public static IvldcompanyIdentifierSoap toSoapModel(
		IvldcompanyIdentifier model) {
		IvldcompanyIdentifierSoap soapModel = new IvldcompanyIdentifierSoap();

		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setIdentifierStatus(model.getIdentifierStatus());
		soapModel.setCompanyIdentifier(model.getCompanyIdentifier());
		soapModel.setEntityCode(model.getEntityCode());
		soapModel.setCompanyIdentifierIntfid(model.getCompanyIdentifierIntfid());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIdentifierCodeQualifierName(model.getIdentifierCodeQualifierName());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setIvldcompanyIdentifierSid(model.getIvldcompanyIdentifierSid());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldcompanyIdentifierSoap[] toSoapModels(
		IvldcompanyIdentifier[] models) {
		IvldcompanyIdentifierSoap[] soapModels = new IvldcompanyIdentifierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldcompanyIdentifierSoap[][] toSoapModels(
		IvldcompanyIdentifier[][] models) {
		IvldcompanyIdentifierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldcompanyIdentifierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldcompanyIdentifierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldcompanyIdentifierSoap[] toSoapModels(
		List<IvldcompanyIdentifier> models) {
		List<IvldcompanyIdentifierSoap> soapModels = new ArrayList<IvldcompanyIdentifierSoap>(models.size());

		for (IvldcompanyIdentifier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldcompanyIdentifierSoap[soapModels.size()]);
	}

	public IvldcompanyIdentifierSoap() {
	}

	public int getPrimaryKey() {
		return _ivldcompanyIdentifierSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldcompanyIdentifierSid(pk);
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getEndDate() {
		return _endDate;
	}

	public void setEndDate(String endDate) {
		_endDate = endDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getIdentifierStatus() {
		return _identifierStatus;
	}

	public void setIdentifierStatus(String identifierStatus) {
		_identifierStatus = identifierStatus;
	}

	public String getCompanyIdentifier() {
		return _companyIdentifier;
	}

	public void setCompanyIdentifier(String companyIdentifier) {
		_companyIdentifier = companyIdentifier;
	}

	public String getEntityCode() {
		return _entityCode;
	}

	public void setEntityCode(String entityCode) {
		_entityCode = entityCode;
	}

	public String getCompanyIdentifierIntfid() {
		return _companyIdentifierIntfid;
	}

	public void setCompanyIdentifierIntfid(String companyIdentifierIntfid) {
		_companyIdentifierIntfid = companyIdentifierIntfid;
	}

	public String getStartDate() {
		return _startDate;
	}

	public void setStartDate(String startDate) {
		_startDate = startDate;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
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

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public void setErrorCode(String errorCode) {
		_errorCode = errorCode;
	}

	public String getIdentifierCodeQualifierName() {
		return _identifierCodeQualifierName;
	}

	public void setIdentifierCodeQualifierName(
		String identifierCodeQualifierName) {
		_identifierCodeQualifierName = identifierCodeQualifierName;
	}

	public Date getIntfInsertedDate() {
		return _intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		_intfInsertedDate = intfInsertedDate;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public int getIvldcompanyIdentifierSid() {
		return _ivldcompanyIdentifierSid;
	}

	public void setIvldcompanyIdentifierSid(int ivldcompanyIdentifierSid) {
		_ivldcompanyIdentifierSid = ivldcompanyIdentifierSid;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getIdentifierCodeQualifier() {
		return _identifierCodeQualifier;
	}

	public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
		_identifierCodeQualifier = identifierCodeQualifier;
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

	private String _reasonForFailure;
	private String _companyId;
	private String _companyName;
	private String _endDate;
	private Date _modifiedDate;
	private String _identifierStatus;
	private String _companyIdentifier;
	private String _entityCode;
	private String _companyIdentifierIntfid;
	private String _startDate;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _companyNo;
	private String _addChgDelIndicator;
	private String _batchId;
	private String _errorField;
	private String _errorCode;
	private String _identifierCodeQualifierName;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private int _ivldcompanyIdentifierSid;
	private String _reprocessedFlag;
	private String _identifierCodeQualifier;
	private boolean _checkRecord;
}