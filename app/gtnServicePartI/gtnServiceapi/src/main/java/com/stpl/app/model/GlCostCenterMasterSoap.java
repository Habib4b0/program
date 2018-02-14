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
public class GlCostCenterMasterSoap implements Serializable {
	public static GlCostCenterMasterSoap toSoapModel(GlCostCenterMaster model) {
		GlCostCenterMasterSoap soapModel = new GlCostCenterMasterSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setUploadDate(model.getUploadDate());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setGlCostCenterMasterSid(model.getGlCostCenterMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setNdc8(model.getNdc8());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCompanyCode(model.getCompanyCode());
		soapModel.setSource(model.getSource());
		soapModel.setCompanyCostCenter(model.getCompanyCostCenter());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static GlCostCenterMasterSoap[] toSoapModels(
		GlCostCenterMaster[] models) {
		GlCostCenterMasterSoap[] soapModels = new GlCostCenterMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GlCostCenterMasterSoap[][] toSoapModels(
		GlCostCenterMaster[][] models) {
		GlCostCenterMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GlCostCenterMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GlCostCenterMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GlCostCenterMasterSoap[] toSoapModels(
		List<GlCostCenterMaster> models) {
		List<GlCostCenterMasterSoap> soapModels = new ArrayList<GlCostCenterMasterSoap>(models.size());

		for (GlCostCenterMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GlCostCenterMasterSoap[soapModels.size()]);
	}

	public GlCostCenterMasterSoap() {
	}

	public int getPrimaryKey() {
		return _glCostCenterMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setGlCostCenterMasterSid(pk);
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public int getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getUploadDate() {
		return _uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		_uploadDate = uploadDate;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getGlCostCenterMasterSid() {
		return _glCostCenterMasterSid;
	}

	public void setGlCostCenterMasterSid(int glCostCenterMasterSid) {
		_glCostCenterMasterSid = glCostCenterMasterSid;
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

	public String getNdc8() {
		return _ndc8;
	}

	public void setNdc8(String ndc8) {
		_ndc8 = ndc8;
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

	public String getCompanyCode() {
		return _companyCode;
	}

	public void setCompanyCode(String companyCode) {
		_companyCode = companyCode;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getCompanyCostCenter() {
		return _companyCostCenter;
	}

	public void setCompanyCostCenter(String companyCostCenter) {
		_companyCostCenter = companyCostCenter;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private int _createdBy;
	private int _modifiedBy;
	private Date _uploadDate;
	private Date _createdDate;
	private int _glCostCenterMasterSid;
	private String _batchId;
	private Date _modifiedDate;
	private String _ndc8;
	private boolean _recordLockStatus;
	private String _companyCode;
	private String _source;
	private String _companyCostCenter;
	private String _inboundStatus;
}