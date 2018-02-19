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
public class CpiIndexMasterSoap implements Serializable {
	public static CpiIndexMasterSoap toSoapModel(CpiIndexMaster model) {
		CpiIndexMasterSoap soapModel = new CpiIndexMasterSoap();

		soapModel.setEffectiveDate(model.getEffectiveDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCpiIndexMasterSid(model.getCpiIndexMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setIndexType(model.getIndexType());
		soapModel.setIndexId(model.getIndexId());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setIndexValue(model.getIndexValue());
		soapModel.setSource(model.getSource());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static CpiIndexMasterSoap[] toSoapModels(CpiIndexMaster[] models) {
		CpiIndexMasterSoap[] soapModels = new CpiIndexMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CpiIndexMasterSoap[][] toSoapModels(CpiIndexMaster[][] models) {
		CpiIndexMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CpiIndexMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CpiIndexMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CpiIndexMasterSoap[] toSoapModels(List<CpiIndexMaster> models) {
		List<CpiIndexMasterSoap> soapModels = new ArrayList<CpiIndexMasterSoap>(models.size());

		for (CpiIndexMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CpiIndexMasterSoap[soapModels.size()]);
	}

	public CpiIndexMasterSoap() {
	}

	public int getPrimaryKey() {
		return _cpiIndexMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCpiIndexMasterSid(pk);
	}

	public Date getEffectiveDate() {
		return _effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		_effectiveDate = effectiveDate;
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

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public int getCpiIndexMasterSid() {
		return _cpiIndexMasterSid;
	}

	public void setCpiIndexMasterSid(int cpiIndexMasterSid) {
		_cpiIndexMasterSid = cpiIndexMasterSid;
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

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getIndexType() {
		return _indexType;
	}

	public void setIndexType(String indexType) {
		_indexType = indexType;
	}

	public String getIndexId() {
		return _indexId;
	}

	public void setIndexId(String indexId) {
		_indexId = indexId;
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

	public String getIndexValue() {
		return _indexValue;
	}

	public void setIndexValue(String indexValue) {
		_indexValue = indexValue;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private Date _effectiveDate;
	private int _createdBy;
	private int _modifiedBy;
	private Date _createdDate;
	private int _cpiIndexMasterSid;
	private String _batchId;
	private Date _modifiedDate;
	private String _status;
	private String _indexType;
	private String _indexId;
	private boolean _recordLockStatus;
	private String _indexValue;
	private String _source;
	private String _inboundStatus;
}