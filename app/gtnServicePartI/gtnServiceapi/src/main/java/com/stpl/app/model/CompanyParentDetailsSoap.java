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
public class CompanyParentDetailsSoap implements Serializable {
	public static CompanyParentDetailsSoap toSoapModel(
		CompanyParentDetails model) {
		CompanyParentDetailsSoap soapModel = new CompanyParentDetailsSoap();

		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setParentEndDate(model.getParentEndDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setParentCompanyMasterSid(model.getParentCompanyMasterSid());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setPriorParentStartDate(model.getPriorParentStartDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCompanyParentDetailsSid(model.getCompanyParentDetailsSid());
		soapModel.setPriorParentCmpyMasterSid(model.getPriorParentCmpyMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setParentStartDate(model.getParentStartDate());

		return soapModel;
	}

	public static CompanyParentDetailsSoap[] toSoapModels(
		CompanyParentDetails[] models) {
		CompanyParentDetailsSoap[] soapModels = new CompanyParentDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyParentDetailsSoap[][] toSoapModels(
		CompanyParentDetails[][] models) {
		CompanyParentDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyParentDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyParentDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyParentDetailsSoap[] toSoapModels(
		List<CompanyParentDetails> models) {
		List<CompanyParentDetailsSoap> soapModels = new ArrayList<CompanyParentDetailsSoap>(models.size());

		for (CompanyParentDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyParentDetailsSoap[soapModels.size()]);
	}

	public CompanyParentDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _companyParentDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyParentDetailsSid(pk);
	}

	public Date getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public Date getParentEndDate() {
		return _parentEndDate;
	}

	public void setParentEndDate(Date parentEndDate) {
		_parentEndDate = parentEndDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getParentCompanyMasterSid() {
		return _parentCompanyMasterSid;
	}

	public void setParentCompanyMasterSid(int parentCompanyMasterSid) {
		_parentCompanyMasterSid = parentCompanyMasterSid;
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

	public Date getPriorParentStartDate() {
		return _priorParentStartDate;
	}

	public void setPriorParentStartDate(Date priorParentStartDate) {
		_priorParentStartDate = priorParentStartDate;
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

	public int getCompanyParentDetailsSid() {
		return _companyParentDetailsSid;
	}

	public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
		_companyParentDetailsSid = companyParentDetailsSid;
	}

	public String getPriorParentCmpyMasterSid() {
		return _priorParentCmpyMasterSid;
	}

	public void setPriorParentCmpyMasterSid(String priorParentCmpyMasterSid) {
		_priorParentCmpyMasterSid = priorParentCmpyMasterSid;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
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

	public Date getParentStartDate() {
		return _parentStartDate;
	}

	public void setParentStartDate(Date parentStartDate) {
		_parentStartDate = parentStartDate;
	}

	private Date _lastUpdatedDate;
	private Date _parentEndDate;
	private Date _modifiedDate;
	private int _parentCompanyMasterSid;
	private boolean _recordLockStatus;
	private Date _priorParentStartDate;
	private Date _createdDate;
	private String _source;
	private int _createdBy;
	private int _companyParentDetailsSid;
	private String _priorParentCmpyMasterSid;
	private String _batchId;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _companyMasterSid;
	private Date _parentStartDate;
}