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
public class BrandMasterSoap implements Serializable {
	public static BrandMasterSoap toSoapModel(BrandMaster model) {
		BrandMasterSoap soapModel = new BrandMasterSoap();

		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setDisplayBrand(model.getDisplayBrand());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setBrandDesc(model.getBrandDesc());
		soapModel.setSource(model.getSource());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static BrandMasterSoap[] toSoapModels(BrandMaster[] models) {
		BrandMasterSoap[] soapModels = new BrandMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BrandMasterSoap[][] toSoapModels(BrandMaster[][] models) {
		BrandMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BrandMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BrandMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BrandMasterSoap[] toSoapModels(List<BrandMaster> models) {
		List<BrandMasterSoap> soapModels = new ArrayList<BrandMasterSoap>(models.size());

		for (BrandMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BrandMasterSoap[soapModels.size()]);
	}

	public BrandMasterSoap() {
	}

	public int getPrimaryKey() {
		return _brandMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setBrandMasterSid(pk);
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

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
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

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public String getDisplayBrand() {
		return _displayBrand;
	}

	public void setDisplayBrand(String displayBrand) {
		_displayBrand = displayBrand;
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

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public String getBrandDesc() {
		return _brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		_brandDesc = brandDesc;
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

	private int _createdBy;
	private int _modifiedBy;
	private Date _createdDate;
	private int _brandMasterSid;
	private String _batchId;
	private Date _modifiedDate;
	private String _brandId;
	private String _displayBrand;
	private boolean _recordLockStatus;
	private String _brandName;
	private String _brandDesc;
	private String _source;
	private String _inboundStatus;
}