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
public class CompanyIdentifierSoap implements Serializable {
	public static CompanyIdentifierSoap toSoapModel(CompanyIdentifier model) {
		CompanyIdentifierSoap soapModel = new CompanyIdentifierSoap();

		soapModel.setEndDate(model.getEndDate());
		soapModel.setCompanyStringIdentifierSid(model.getCompanyStringIdentifierSid());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setIdentifierStatus(model.getIdentifierStatus());
		soapModel.setEntityCode(model.getEntityCode());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyStringIdentifierValue(model.getCompanyStringIdentifierValue());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyQualifierSid(model.getCompanyQualifierSid());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());

		return soapModel;
	}

	public static CompanyIdentifierSoap[] toSoapModels(
		CompanyIdentifier[] models) {
		CompanyIdentifierSoap[] soapModels = new CompanyIdentifierSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyIdentifierSoap[][] toSoapModels(
		CompanyIdentifier[][] models) {
		CompanyIdentifierSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyIdentifierSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyIdentifierSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyIdentifierSoap[] toSoapModels(
		List<CompanyIdentifier> models) {
		List<CompanyIdentifierSoap> soapModels = new ArrayList<CompanyIdentifierSoap>(models.size());

		for (CompanyIdentifier model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyIdentifierSoap[soapModels.size()]);
	}

	public CompanyIdentifierSoap() {
	}

	public int getPrimaryKey() {
		return _companyStringIdentifierSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyStringIdentifierSid(pk);
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public int getCompanyStringIdentifierSid() {
		return _companyStringIdentifierSid;
	}

	public void setCompanyStringIdentifierSid(int companyStringIdentifierSid) {
		_companyStringIdentifierSid = companyStringIdentifierSid;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getIdentifierStatus() {
		return _identifierStatus;
	}

	public void setIdentifierStatus(int identifierStatus) {
		_identifierStatus = identifierStatus;
	}

	public String getEntityCode() {
		return _entityCode;
	}

	public void setEntityCode(String entityCode) {
		_entityCode = entityCode;
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

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
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

	public String getCompanyStringIdentifierValue() {
		return _companyStringIdentifierValue;
	}

	public void setCompanyStringIdentifierValue(
		String companyStringIdentifierValue) {
		_companyStringIdentifierValue = companyStringIdentifierValue;
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

	private Date _endDate;
	private int _companyStringIdentifierSid;
	private Date _modifiedDate;
	private int _identifierStatus;
	private String _entityCode;
	private boolean _recordLockStatus;
	private Date _startDate;
	private Date _createdDate;
	private String _source;
	private int _createdBy;
	private String _companyStringIdentifierValue;
	private String _batchId;
	private int _companyQualifierSid;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _companyMasterSid;
}