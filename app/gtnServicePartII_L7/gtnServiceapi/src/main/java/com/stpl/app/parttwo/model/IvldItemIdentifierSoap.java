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
public class IvldItemIdentifierSoap implements Serializable {
	public static IvldItemIdentifierSoap toSoapModel(IvldItemIdentifier model) {
		IvldItemIdentifierSoap soapModel = new IvldItemIdentifierSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setIdentifierCodeQualifierName(model.getIdentifierCodeQualifierName());
		soapModel.setIvldItemIdentifierSid(model.getIvldItemIdentifierSid());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());
		soapModel.setItemId(model.getItemId());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemName(model.getItemName());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setItemStatus(model.getItemStatus());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setSource(model.getSource());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setEntityCode(model.getEntityCode());
		soapModel.setItemIdentifierIntfid(model.getItemIdentifierIntfid());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldItemIdentifierSoap[] toSoapModels(
		IvldItemIdentifier[] models) {
		IvldItemIdentifierSoap[] soapModels = new IvldItemIdentifierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldItemIdentifierSoap[][] toSoapModels(
		IvldItemIdentifier[][] models) {
		IvldItemIdentifierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldItemIdentifierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldItemIdentifierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldItemIdentifierSoap[] toSoapModels(
		List<IvldItemIdentifier> models) {
		List<IvldItemIdentifierSoap> soapModels = new ArrayList<IvldItemIdentifierSoap>(models.size());

		for (IvldItemIdentifier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldItemIdentifierSoap[soapModels.size()]);
	}

	public IvldItemIdentifierSoap() {
	}

	public int getPrimaryKey() {
		return _ivldItemIdentifierSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldItemIdentifierSid(pk);
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public String getIdentifierCodeQualifierName() {
		return _identifierCodeQualifierName;
	}

	public void setIdentifierCodeQualifierName(
		String identifierCodeQualifierName) {
		_identifierCodeQualifierName = identifierCodeQualifierName;
	}

	public int getIvldItemIdentifierSid() {
		return _ivldItemIdentifierSid;
	}

	public void setIvldItemIdentifierSid(int ivldItemIdentifierSid) {
		_ivldItemIdentifierSid = ivldItemIdentifierSid;
	}

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getIdentifierCodeQualifier() {
		return _identifierCodeQualifier;
	}

	public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
		_identifierCodeQualifier = identifierCodeQualifier;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getEndDate() {
		return _endDate;
	}

	public void setEndDate(String endDate) {
		_endDate = endDate;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
	}

	public String getStartDate() {
		return _startDate;
	}

	public void setStartDate(String startDate) {
		_startDate = startDate;
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

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public void setErrorCode(String errorCode) {
		_errorCode = errorCode;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	public String getItemStatus() {
		return _itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		_itemStatus = itemStatus;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getEntityCode() {
		return _entityCode;
	}

	public void setEntityCode(String entityCode) {
		_entityCode = entityCode;
	}

	public String getItemIdentifierIntfid() {
		return _itemIdentifierIntfid;
	}

	public void setItemIdentifierIntfid(String itemIdentifierIntfid) {
		_itemIdentifierIntfid = itemIdentifierIntfid;
	}

	public Date getIntfInsertedDate() {
		return _intfInsertedDate;
	}

	public void setIntfInsertedDate(Date intfInsertedDate) {
		_intfInsertedDate = intfInsertedDate;
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

	private String _createdBy;
	private String _identifierCodeQualifierName;
	private int _ivldItemIdentifierSid;
	private String _itemNo;
	private String _modifiedBy;
	private Date _createdDate;
	private String _identifierCodeQualifier;
	private String _itemId;
	private String _endDate;
	private String _errorField;
	private String _startDate;
	private String _batchId;
	private Date _modifiedDate;
	private String _itemName;
	private String _errorCode;
	private String _reprocessedFlag;
	private String _itemIdentifier;
	private String _itemStatus;
	private String _reasonForFailure;
	private String _source;
	private String _addChgDelIndicator;
	private String _entityCode;
	private String _itemIdentifierIntfid;
	private Date _intfInsertedDate;
	private boolean _checkRecord;
}