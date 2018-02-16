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
public class VwIvldReturnReserveSoap implements Serializable {
	public static VwIvldReturnReserveSoap toSoapModel(VwIvldReturnReserve model) {
		VwIvldReturnReserveSoap soapModel = new VwIvldReturnReserveSoap();

		soapModel.setIvldReturnReserveSid(model.getIvldReturnReserveSid());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setYear(model.getYear());
		soapModel.setProject(model.getProject());
		soapModel.setItemId(model.getItemId());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAccount(model.getAccount());
		soapModel.setReturnReserveIntfId(model.getReturnReserveIntfId());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setBusinessUnit(model.getBusinessUnit());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setItemNo(model.getItemNo());
		soapModel.setMonth(model.getMonth());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setUnits(model.getUnits());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCountry(model.getCountry());
		soapModel.setCompanyIdString(model.getCompanyIdString());
		soapModel.setCostCenter(model.getCostCenter());
		soapModel.setGlCompany(model.getGlCompany());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setFuture1(model.getFuture1());
		soapModel.setFuture2(model.getFuture2());
		soapModel.setAmount(model.getAmount());
		soapModel.setDivision(model.getDivision());
		soapModel.setCompanyNo(model.getCompanyNo());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemName(model.getItemName());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static VwIvldReturnReserveSoap[] toSoapModels(
		VwIvldReturnReserve[] models) {
		VwIvldReturnReserveSoap[] soapModels = new VwIvldReturnReserveSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwIvldReturnReserveSoap[][] toSoapModels(
		VwIvldReturnReserve[][] models) {
		VwIvldReturnReserveSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwIvldReturnReserveSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwIvldReturnReserveSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwIvldReturnReserveSoap[] toSoapModels(
		List<VwIvldReturnReserve> models) {
		List<VwIvldReturnReserveSoap> soapModels = new ArrayList<VwIvldReturnReserveSoap>(models.size());

		for (VwIvldReturnReserve model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwIvldReturnReserveSoap[soapModels.size()]);
	}

	public VwIvldReturnReserveSoap() {
	}

	public int getPrimaryKey() {
		return _ivldReturnReserveSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldReturnReserveSid(pk);
	}

	public int getIvldReturnReserveSid() {
		return _ivldReturnReserveSid;
	}

	public void setIvldReturnReserveSid(int ivldReturnReserveSid) {
		_ivldReturnReserveSid = ivldReturnReserveSid;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String year) {
		_year = year;
	}

	public String getProject() {
		return _project;
	}

	public void setProject(String project) {
		_project = project;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAccount() {
		return _account;
	}

	public void setAccount(String account) {
		_account = account;
	}

	public String getReturnReserveIntfId() {
		return _returnReserveIntfId;
	}

	public void setReturnReserveIntfId(String returnReserveIntfId) {
		_returnReserveIntfId = returnReserveIntfId;
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

	public String getBusinessUnit() {
		return _businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		_businessUnit = businessUnit;
	}

	public String getBusinessUnitName() {
		return _businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		_businessUnitName = businessUnitName;
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

	public String getItemNo() {
		return _itemNo;
	}

	public void setItemNo(String itemNo) {
		_itemNo = itemNo;
	}

	public String getMonth() {
		return _month;
	}

	public void setMonth(String month) {
		_month = month;
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

	public String getUnits() {
		return _units;
	}

	public void setUnits(String units) {
		_units = units;
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

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getCompanyIdString() {
		return _companyIdString;
	}

	public void setCompanyIdString(String companyIdString) {
		_companyIdString = companyIdString;
	}

	public String getCostCenter() {
		return _costCenter;
	}

	public void setCostCenter(String costCenter) {
		_costCenter = costCenter;
	}

	public String getGlCompany() {
		return _glCompany;
	}

	public void setGlCompany(String glCompany) {
		_glCompany = glCompany;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public String getFuture1() {
		return _future1;
	}

	public void setFuture1(String future1) {
		_future1 = future1;
	}

	public String getFuture2() {
		return _future2;
	}

	public void setFuture2(String future2) {
		_future2 = future2;
	}

	public String getAmount() {
		return _amount;
	}

	public void setAmount(String amount) {
		_amount = amount;
	}

	public String getDivision() {
		return _division;
	}

	public void setDivision(String division) {
		_division = division;
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

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
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

	private int _ivldReturnReserveSid;
	private String _companyName;
	private String _year;
	private String _project;
	private String _itemId;
	private String _brandName;
	private Date _modifiedDate;
	private String _account;
	private String _returnReserveIntfId;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _businessUnit;
	private String _businessUnitName;
	private String _addChgDelIndicator;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _itemNo;
	private String _month;
	private String _reprocessedFlag;
	private String _udc6;
	private String _udc5;
	private String _udc4;
	private String _udc1;
	private String _units;
	private String _udc2;
	private String _udc3;
	private String _reasonForFailure;
	private String _country;
	private String _companyIdString;
	private String _costCenter;
	private String _glCompany;
	private String _brandId;
	private String _future1;
	private String _future2;
	private String _amount;
	private String _division;
	private String _companyNo;
	private String _batchId;
	private String _itemName;
	private String _errorField;
	private boolean _checkRecord;
}