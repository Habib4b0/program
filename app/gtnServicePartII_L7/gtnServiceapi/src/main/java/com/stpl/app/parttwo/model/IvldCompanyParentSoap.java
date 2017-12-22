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
public class IvldCompanyParentSoap implements Serializable {
	public static IvldCompanyParentSoap toSoapModel(IvldCompanyParent model) {
		IvldCompanyParentSoap soapModel = new IvldCompanyParentSoap();

		soapModel.setParentcompanyId(model.getParentcompanyId());
		soapModel.setPriorParentcompanyId(model.getPriorParentcompanyId());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCompanyIdString(model.getCompanyIdString());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setParentEndDate(model.getParentEndDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentDetailsIntfid(model.getParentDetailsIntfid());
		soapModel.setPriorParentStartDate(model.getPriorParentStartDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setIvldCompanyParentSid(model.getIvldCompanyParentSid());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setParentStartDate(model.getParentStartDate());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldCompanyParentSoap[] toSoapModels(
		IvldCompanyParent[] models) {
		IvldCompanyParentSoap[] soapModels = new IvldCompanyParentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldCompanyParentSoap[][] toSoapModels(
		IvldCompanyParent[][] models) {
		IvldCompanyParentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldCompanyParentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldCompanyParentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldCompanyParentSoap[] toSoapModels(
		List<IvldCompanyParent> models) {
		List<IvldCompanyParentSoap> soapModels = new ArrayList<IvldCompanyParentSoap>(models.size());

		for (IvldCompanyParent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldCompanyParentSoap[soapModels.size()]);
	}

	public IvldCompanyParentSoap() {
	}

	public int getPrimaryKey() {
		return _ivldCompanyParentSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldCompanyParentSid(pk);
	}

	public String getParentcompanyId() {
		return _parentcompanyId;
	}

	public void setParentcompanyId(String parentcompanyId) {
		_parentcompanyId = parentcompanyId;
	}

	public String getPriorParentcompanyId() {
		return _priorParentcompanyId;
	}

	public void setPriorParentcompanyId(String priorParentcompanyId) {
		_priorParentcompanyId = priorParentcompanyId;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getCompanyIdString() {
		return _companyIdString;
	}

	public void setCompanyIdString(String companyIdString) {
		_companyIdString = companyIdString;
	}

	public String getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public String getParentEndDate() {
		return _parentEndDate;
	}

	public void setParentEndDate(String parentEndDate) {
		_parentEndDate = parentEndDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getParentDetailsIntfid() {
		return _parentDetailsIntfid;
	}

	public void setParentDetailsIntfid(String parentDetailsIntfid) {
		_parentDetailsIntfid = parentDetailsIntfid;
	}

	public String getPriorParentStartDate() {
		return _priorParentStartDate;
	}

	public void setPriorParentStartDate(String priorParentStartDate) {
		_priorParentStartDate = priorParentStartDate;
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

	public int getIvldCompanyParentSid() {
		return _ivldCompanyParentSid;
	}

	public void setIvldCompanyParentSid(int ivldCompanyParentSid) {
		_ivldCompanyParentSid = ivldCompanyParentSid;
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

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getParentStartDate() {
		return _parentStartDate;
	}

	public void setParentStartDate(String parentStartDate) {
		_parentStartDate = parentStartDate;
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

	private String _parentcompanyId;
	private String _priorParentcompanyId;
	private String _reasonForFailure;
	private String _companyIdString;
	private String _lastUpdatedDate;
	private String _parentEndDate;
	private Date _modifiedDate;
	private String _parentDetailsIntfid;
	private String _priorParentStartDate;
	private String _source;
	private String _createdBy;
	private Date _createdDate;
	private String _addChgDelIndicator;
	private String _batchId;
	private int _ivldCompanyParentSid;
	private String _errorField;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _reprocessedFlag;
	private String _parentStartDate;
	private boolean _checkRecord;
}