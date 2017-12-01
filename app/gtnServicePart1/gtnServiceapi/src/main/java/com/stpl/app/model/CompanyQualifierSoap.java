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
public class CompanyQualifierSoap implements Serializable {
	public static CompanyQualifierSoap toSoapModel(CompanyQualifier model) {
		CompanyQualifierSoap soapModel = new CompanyQualifierSoap();

		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setCompanyQualifierValue(model.getCompanyQualifierValue());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyQualifierSid(model.getCompanyQualifierSid());
		soapModel.setCompanyQualifierName(model.getCompanyQualifierName());
		soapModel.setEffectiveDates(model.getEffectiveDates());
		soapModel.setNotes(model.getNotes());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedDate(model.getModifiedDate());

		return soapModel;
	}

	public static CompanyQualifierSoap[] toSoapModels(CompanyQualifier[] models) {
		CompanyQualifierSoap[] soapModels = new CompanyQualifierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyQualifierSoap[][] toSoapModels(
		CompanyQualifier[][] models) {
		CompanyQualifierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyQualifierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyQualifierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyQualifierSoap[] toSoapModels(
		List<CompanyQualifier> models) {
		List<CompanyQualifierSoap> soapModels = new ArrayList<CompanyQualifierSoap>(models.size());

		for (CompanyQualifier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyQualifierSoap[soapModels.size()]);
	}

	public CompanyQualifierSoap() {
	}

	public int getPrimaryKey() {
		return _companyQualifierSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyQualifierSid(pk);
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

	public String getCompanyQualifierValue() {
		return _companyQualifierValue;
	}

	public void setCompanyQualifierValue(String companyQualifierValue) {
		_companyQualifierValue = companyQualifierValue;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getCompanyQualifierSid() {
		return _companyQualifierSid;
	}

	public void setCompanyQualifierSid(int companyQualifierSid) {
		_companyQualifierSid = companyQualifierSid;
	}

	public String getCompanyQualifierName() {
		return _companyQualifierName;
	}

	public void setCompanyQualifierName(String companyQualifierName) {
		_companyQualifierName = companyQualifierName;
	}

	public String getEffectiveDates() {
		return _effectiveDates;
	}

	public void setEffectiveDates(String effectiveDates) {
		_effectiveDates = effectiveDates;
	}

	public String getNotes() {
		return _notes;
	}

	public void setNotes(String notes) {
		_notes = notes;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private boolean _recordLockStatus;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _companyQualifierValue;
	private String _batchId;
	private int _companyQualifierSid;
	private String _companyQualifierName;
	private String _effectiveDates;
	private String _notes;
	private int _modifiedBy;
	private String _inboundStatus;
	private Date _modifiedDate;
}