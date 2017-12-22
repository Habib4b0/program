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
public class VwCompanyMasterSoap implements Serializable {
	public static VwCompanyMasterSoap toSoapModel(VwCompanyMaster model) {
		VwCompanyMasterSoap soapModel = new VwCompanyMasterSoap();

		soapModel.setState(model.getState());
		soapModel.setFinancialSystem(model.getFinancialSystem());
		soapModel.setCompanyGroup(model.getCompanyGroup());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setLastUpdatedDate(model.getLastUpdatedDate());
		soapModel.setCompanyCategory(model.getCompanyCategory());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setLives(model.getLives());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setAddress2(model.getAddress2());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setAddress1(model.getAddress1());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setCompanyMasterSid(model.getCompanyMasterSid());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setZipCode(model.getZipCode());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCountry(model.getCountry());
		soapModel.setCompanyType(model.getCompanyType());
		soapModel.setCompanyStartDate(model.getCompanyStartDate());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setCompanyStatus(model.getCompanyStatus());
		soapModel.setCompanyEndDate(model.getCompanyEndDate());
		soapModel.setCity(model.getCity());
		soapModel.setRegionCode(model.getRegionCode());

		return soapModel;
	}

	public static VwCompanyMasterSoap[] toSoapModels(VwCompanyMaster[] models) {
		VwCompanyMasterSoap[] soapModels = new VwCompanyMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyMasterSoap[][] toSoapModels(
		VwCompanyMaster[][] models) {
		VwCompanyMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwCompanyMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwCompanyMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwCompanyMasterSoap[] toSoapModels(
		List<VwCompanyMaster> models) {
		List<VwCompanyMasterSoap> soapModels = new ArrayList<VwCompanyMasterSoap>(models.size());

		for (VwCompanyMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwCompanyMasterSoap[soapModels.size()]);
	}

	public VwCompanyMasterSoap() {
	}

	public int getPrimaryKey() {
		return _companyMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setCompanyMasterSid(pk);
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

	public Date getLastUpdatedDate() {
		return _lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		_lastUpdatedDate = lastUpdatedDate;
	}

	public String getCompanyCategory() {
		return _companyCategory;
	}

	public void setCompanyCategory(String companyCategory) {
		_companyCategory = companyCategory;
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

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
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

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
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

	public int getCompanyMasterSid() {
		return _companyMasterSid;
	}

	public void setCompanyMasterSid(int companyMasterSid) {
		_companyMasterSid = companyMasterSid;
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

	public String getZipCode() {
		return _zipCode;
	}

	public void setZipCode(String zipCode) {
		_zipCode = zipCode;
	}

	public String getUdc2() {
		return _udc2;
	}

	public void setUdc2(String udc2) {
		_udc2 = udc2;
	}

	public String getUdc3() {
		return _udc3;
	}

	public void setUdc3(String udc3) {
		_udc3 = udc3;
	}

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getCompanyType() {
		return _companyType;
	}

	public void setCompanyType(String companyType) {
		_companyType = companyType;
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

	public String getCompanyStatus() {
		return _companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
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

	private String _state;
	private String _financialSystem;
	private String _companyGroup;
	private String _companyName;
	private Date _lastUpdatedDate;
	private String _companyCategory;
	private Date _modifiedDate;
	private int _lives;
	private String _organizationKey;
	private String _address2;
	private Date _createdDate;
	private String _createdBy;
	private String _source;
	private String _address1;
	private String _addChgDelIndicator;
	private String _modifiedBy;
	private String _udc6;
	private String _udc5;
	private int _companyMasterSid;
	private String _udc4;
	private String _udc1;
	private String _zipCode;
	private String _udc2;
	private String _udc3;
	private String _companyId;
	private String _country;
	private String _companyType;
	private Date _companyStartDate;
	private String _companyNo;
	private String _batchId;
	private String _companyStatus;
	private Date _companyEndDate;
	private String _city;
	private String _regionCode;
}