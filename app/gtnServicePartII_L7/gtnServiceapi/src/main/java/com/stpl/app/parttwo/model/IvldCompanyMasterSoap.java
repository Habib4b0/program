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
public class IvldCompanyMasterSoap implements Serializable {
	public static IvldCompanyMasterSoap toSoapModel(IvldCompanyMaster model) {
		IvldCompanyMasterSoap soapModel = new IvldCompanyMasterSoap();

		soapModel.setState(model.getState());
		soapModel.setFinancialSystem(model.getFinancialSystem());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setCompanyGroup(model.getCompanyGroup());
		soapModel.setCompanyCategory(model.getCompanyCategory());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setStatus(model.getStatus());
		soapModel.setIvldCompanyMasterSid(model.getIvldCompanyMasterSid());
		soapModel.setLives(model.getLives());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCompanyMasterIntfid(model.getCompanyMasterIntfid());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setZipCode(model.getZipCode());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCompanyIdString(model.getCompanyIdString());
		soapModel.setAddress1(model.getAddress1());
		soapModel.setCountry(model.getCountry());
		soapModel.setAddress2(model.getAddress2());
		soapModel.setCompanyType(model.getCompanyType());
		soapModel.setCompanyStartDate(model.getCompanyStartDate());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyStatus(model.getCompanyStatus());
		soapModel.setCompanyEndDate(model.getCompanyEndDate());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setCity(model.getCity());
		soapModel.setRegionCode(model.getRegionCode());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldCompanyMasterSoap[] toSoapModels(
		IvldCompanyMaster[] models) {
		IvldCompanyMasterSoap[] soapModels = new IvldCompanyMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldCompanyMasterSoap[][] toSoapModels(
		IvldCompanyMaster[][] models) {
		IvldCompanyMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldCompanyMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldCompanyMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldCompanyMasterSoap[] toSoapModels(
		List<IvldCompanyMaster> models) {
		List<IvldCompanyMasterSoap> soapModels = new ArrayList<IvldCompanyMasterSoap>(models.size());

		for (IvldCompanyMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldCompanyMasterSoap[soapModels.size()]);
	}

	public IvldCompanyMasterSoap() {
	}

	public int getPrimaryKey() {
		return _ivldCompanyMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldCompanyMasterSid(pk);
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		_state = state;
	}

	public String getFinancialSystem() {
		return _financialSystem;
	}

	public void setFinancialSystem(String financialSystem) {
		_financialSystem = financialSystem;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getCompanyGroup() {
		return _companyGroup;
	}

	public void setCompanyGroup(String companyGroup) {
		_companyGroup = companyGroup;
	}

	public String getCompanyCategory() {
		return _companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		_companyCategory = companyCategory;
	}

	public String getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
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

	public int getIvldCompanyMasterSid() {
		return _ivldCompanyMasterSid;
	}

	public void setIvldCompanyMasterSid(int ivldCompanyMasterSid) {
		_ivldCompanyMasterSid = ivldCompanyMasterSid;
	}

	public String getLives() {
		return _lives;
	}

	public void setLives(String lives) {
		_lives = lives;
	}

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
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

	public String getCompanyMasterIntfid() {
		return _companyMasterIntfid;
	}

	public void setCompanyMasterIntfid(String companyMasterIntfid) {
		_companyMasterIntfid = companyMasterIntfid;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getUdc6() {
		return _udc6;
	}

	public void setUdc6(String udc6) {
		_udc6 = udc6;
	}

	public String getUdc5() {
		return _udc5;
	}

	public void setUdc5(String udc5) {
		_udc5 = udc5;
	}

	public String getUdc4() {
		return _udc4;
	}

	public void setUdc4(String udc4) {
		_udc4 = udc4;
	}

	public String getUdc1() {
		return _udc1;
	}

	public void setUdc1(String udc1) {
		_udc1 = udc1;
	}

	public String getUdc2() {
		return _udc2;
	}

	public void setUdc2(String udc2) {
		_udc2 = udc2;
	}

	public String getZipCode() {
		return _zipCode;
	}

	public void setZipCode(String zipCode) {
		_zipCode = zipCode;
	}

	public String getUdc3() {
		return _udc3;
	}

	public void setUdc3(String udc3) {
		_udc3 = udc3;
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

	public String getAddress1() {
		return _address1;
	}

	public void setAddress1(String address1) {
		_address1 = address1;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getAddress2() {
		return _address2;
	}

	public void setAddress2(String address2) {
		_address2 = address2;
	}

	public String getCompanyType() {
		return _companyType;
	}

	public void setCompanyType(String companyType) {
		_companyType = companyType;
	}

	public String getCompanyStartDate() {
		return _companyStartDate;
	}

	public void setCompanyStartDate(String companyStartDate) {
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

	public String getCompanyStatus() {
		return _companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		_companyStatus = companyStatus;
	}

	public String getCompanyEndDate() {
		return _companyEndDate;
	}

	public void setCompanyEndDate(String companyEndDate) {
		_companyEndDate = companyEndDate;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
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

	public boolean getCheckRecord() {
		return _checkRecord;
	}

	public boolean isCheckRecord() {
		return _checkRecord;
	}

	public void setCheckRecord(boolean checkRecord) {
		_checkRecord = checkRecord;
	}

	private String _state;
	private String _financialSystem;
	private String _companyName;
	private String _companyGroup;
	private String _companyCategory;
	private String _lastUpdatedDate;
	private Date _modifiedDate;
	private String _status;
	private int _ivldCompanyMasterSid;
	private String _lives;
	private String _organizationKey;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _addChgDelIndicator;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _companyMasterIntfid;
	private String _reprocessedFlag;
	private String _udc6;
	private String _udc5;
	private String _udc4;
	private String _udc1;
	private String _udc2;
	private String _zipCode;
	private String _udc3;
	private String _reasonForFailure;
	private String _companyIdString;
	private String _address1;
	private String _country;
	private String _address2;
	private String _companyType;
	private String _companyStartDate;
	private String _companyNo;
	private String _batchId;
	private String _companyStatus;
	private String _companyEndDate;
	private String _errorField;
	private String _city;
	private String _regionCode;
	private boolean _checkRecord;
}