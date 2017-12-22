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
public class VwCompanyParentDetailsSoap implements Serializable {
	public static VwCompanyParentDetailsSoap toSoapModel(
		VwCompanyParentDetails model) {
		VwCompanyParentDetailsSoap soapModel = new VwCompanyParentDetailsSoap();

		soapModel.setParentcompanyId(model.getParentcompanyId());
		soapModel.setPriorParentcompanyId(model.getPriorParentcompanyId());
		soapModel.setCompanyIdString(model.getCompanyIdString());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setParentEndDate(model.getParentEndDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPriorParentStartDate(model.getPriorParentStartDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCompanyParentDetailsSid(model.getCompanyParentDetailsSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setParentStartDate(model.getParentStartDate());

		return soapModel;
	}

	public static VwCompanyParentDetailsSoap[] toSoapModels(
		VwCompanyParentDetails[] models) {
		VwCompanyParentDetailsSoap[] soapModels = new VwCompanyParentDetailsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyParentDetailsSoap[][] toSoapModels(
		VwCompanyParentDetails[][] models) {
		VwCompanyParentDetailsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwCompanyParentDetailsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwCompanyParentDetailsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyParentDetailsSoap[] toSoapModels(
		List<VwCompanyParentDetails> models) {
		List<VwCompanyParentDetailsSoap> soapModels = new ArrayList<VwCompanyParentDetailsSoap>(models.size());

		for (VwCompanyParentDetails model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwCompanyParentDetailsSoap[soapModels.size()]);
	}

	public VwCompanyParentDetailsSoap() {
	}

	public int getPrimaryKey() {
		return _companyParentDetailsSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyParentDetailsSid(pk);
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

	public String getCompanyIdString() {
		return _companyIdString;
	}

	public void setCompanyIdString(String companyIdString) {
		_companyIdString = companyIdString;
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

	public Date getPriorParentStartDate() {
		return _priorParentStartDate;
	}

	public void setPriorParentStartDate(Date priorParentStartDate) {
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

	public int getCompanyParentDetailsSid() {
		return _companyParentDetailsSid;
	}

	public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
		_companyParentDetailsSid = companyParentDetailsSid;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getParentStartDate() {
		return _parentStartDate;
	}

	public void setParentStartDate(Date parentStartDate) {
		_parentStartDate = parentStartDate;
	}

	private String _parentcompanyId;
	private String _priorParentcompanyId;
	private String _companyIdString;
	private Date _lastUpdatedDate;
	private Date _parentEndDate;
	private Date _modifiedDate;
	private Date _priorParentStartDate;
	private String _source;
	private String _createdBy;
	private Date _createdDate;
	private int _companyParentDetailsSid;
	private String _batchId;
	private String _addChgDelIndicator;
	private String _modifiedBy;
	private Date _parentStartDate;
}