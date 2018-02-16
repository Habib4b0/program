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
public class CompanyMasterSoap implements Serializable {
	public static CompanyMasterSoap toSoapModel(CompanyMaster model) {
		CompanyMasterSoap soapModel = new CompanyMasterSoap();

		soapModel.setState(model.getState());
		soapModel.setFinancialSystem(model.getFinancialSystem());
		soapModel.setCompanyGroup(model.getCompanyGroup());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setCompanyCategory(model.getCompanyCategory());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLives(model.getLives());
		soapModel.setAddress2(model.getAddress2());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setAddress1(model.getAddress1());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setZipCode(model.getZipCode());
		soapModel.setCompanyStringId(model.getCompanyStringId());
		soapModel.setCountry(model.getCountry());
		soapModel.setInternalNotes(model.getInternalNotes());
		soapModel.setOrgKey(model.getOrgKey());
		soapModel.setCompanyType(model.getCompanyType());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setCompanyStartDate(model.getCompanyStartDate());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyStatus(model.getCompanyStatus());
		soapModel.setCompanyEndDate(model.getCompanyEndDate());
		soapModel.setCity(model.getCity());
		soapModel.setRegionCode(model.getRegionCode());

		return soapModel;
	}

	public static CompanyMasterSoap[] toSoapModels(CompanyMaster[] models) {
		CompanyMasterSoap[] soapModels = new CompanyMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CompanyMasterSoap[][] toSoapModels(CompanyMaster[][] models) {
		CompanyMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CompanyMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CompanyMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CompanyMasterSoap[] toSoapModels(List<CompanyMaster> models) {
		List<CompanyMasterSoap> soapModels = new ArrayList<CompanyMasterSoap>(models.size());

		for (CompanyMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CompanyMasterSoap[soapModels.size()]);
	}

	public CompanyMasterSoap() {
	}

	public int getPrimaryKey() {
		return _companyMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyMasterSid(pk);
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public String getFinancialSystem() {
		return _financialSystem;
	}

	public void setFinancialSystem(String financialSystem) {
		_financialSystem = financialSystem;
	}

	public String getCompanyGroup() {
		return _companyGroup;
	}

	public void setCompanyGroup(String companyGroup) {
		_companyGroup = companyGroup;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public int getCompanyCategory() {
		return _companyCategory;
	}

	public void setCompanyCategory(int companyCategory) {
		_companyCategory = companyCategory;
	}

	public Date getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getLives() {
		return _lives;
	}

	public void setLives(int lives) {
		_lives = lives;
	}

	public String getAddress2() {
		return _address2;
	}

	public void setAddress2(String address2) {
		_address2 = address2;
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

	public String getAddress1() {
		return _address1;
	}

	public void setAddress1(String address1) {
		_address1 = address1;
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

	public String getZipCode() {
		return _zipCode;
	}

	public void setZipCode(String zipCode) {
		_zipCode = zipCode;
	}

	public String getCompanyStringId() {
		return _companyStringId;
	}

	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	public int getCountry() {
		return _country;
	}

	public void setCountry(int country) {
		_country = country;
	}

	public String getInternalNotes() {
		return _internalNotes;
	}

	public void setInternalNotes(String internalNotes) {
		_internalNotes = internalNotes;
	}

	public String getOrgKey() {
		return _orgKey;
	}

	public void setOrgKey(String orgKey) {
		_orgKey = orgKey;
	}

	public int getCompanyType() {
		return _companyType;
	}

	public void setCompanyType(int companyType) {
		_companyType = companyType;
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

	public Date getCompanyStartDate() {
		return _companyStartDate;
	}

	public void setCompanyStartDate(Date companyStartDate) {
		_companyStartDate = companyStartDate;
	}

	public String getCompanyNo() {
		return _companyNo;
	}

	public void setCompanyNo(String companyNo) {
		_companyNo = companyNo;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public int getCompanyStatus() {
		return _companyStatus;
	}

	public void setCompanyStatus(int companyStatus) {
		_companyStatus = companyStatus;
	}

	public Date getCompanyEndDate() {
		return _companyEndDate;
	}

	public void setCompanyEndDate(Date companyEndDate) {
		_companyEndDate = companyEndDate;
	}

	public String getCity() {
		return _city;
	}

	public void setCity(String city) {
		_city = city;
	}

	public String getRegionCode() {
		return _regionCode;
	}

	public void setRegionCode(String regionCode) {
		_regionCode = regionCode;
	}

	private int _state;
	private String _financialSystem;
	private String _companyGroup;
	private String _companyName;
	private int _companyCategory;
	private Date _lastUpdatedDate;
	private Date _modifiedDate;
	private int _lives;
	private String _address2;
	private Date _createdDate;
	private int _createdBy;
	private String _source;
	private String _address1;
	private int _modifiedBy;
	private String _inboundStatus;
	private int _companyMasterSid;
	private String _zipCode;
	private String _companyStringId;
	private int _country;
	private String _internalNotes;
	private String _orgKey;
	private int _companyType;
	private boolean _recordLockStatus;
	private Date _companyStartDate;
	private String _companyNo;
	private String _batchId;
	private int _companyStatus;
	private Date _companyEndDate;
	private String _city;
	private String _regionCode;
}