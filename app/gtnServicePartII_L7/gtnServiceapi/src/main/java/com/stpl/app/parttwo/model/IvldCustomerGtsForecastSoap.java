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
public class IvldCustomerGtsForecastSoap implements Serializable {
	public static IvldCustomerGtsForecastSoap toSoapModel(
		IvldCustomerGtsForecast model) {
		IvldCustomerGtsForecastSoap soapModel = new IvldCustomerGtsForecastSoap();

		soapModel.setPrice(model.getPrice());
		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setDeductionAmount(model.getDeductionAmount());
		soapModel.setIvldCustomerGtsForecastSid(model.getIvldCustomerGtsForecastSid());
		soapModel.setDeductionId(model.getDeductionId());
		soapModel.setForecastDate(model.getForecastDate());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setSalesAmount(model.getSalesAmount());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUdc6(model.getUdc6());
		soapModel.setUdc5(model.getUdc5());
		soapModel.setDeductionType(model.getDeductionType());
		soapModel.setUdc4(model.getUdc4());
		soapModel.setUdc1(model.getUdc1());
		soapModel.setUnits(model.getUnits());
		soapModel.setDeductionRate(model.getDeductionRate());
		soapModel.setUdc2(model.getUdc2());
		soapModel.setUdc3(model.getUdc3());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCountry(model.getCountry());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setForecastValueType(model.getForecastValueType());
		soapModel.setDeductionCategory(model.getDeductionCategory());
		soapModel.setAdjustmentCode(model.getAdjustmentCode());
		soapModel.setDeductionProgramType(model.getDeductionProgramType());
		soapModel.setCustomerGtsForecastIntfId(model.getCustomerGtsForecastIntfId());
		soapModel.setSalesInclusion(model.getSalesInclusion());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setPriceType(model.getPriceType());
		soapModel.setForecastMonth(model.getForecastMonth());
		soapModel.setDeductionInclusion(model.getDeductionInclusion());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setSegment(model.getSegment());
		soapModel.setBrand(model.getBrand());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldCustomerGtsForecastSoap[] toSoapModels(
		IvldCustomerGtsForecast[] models) {
		IvldCustomerGtsForecastSoap[] soapModels = new IvldCustomerGtsForecastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldCustomerGtsForecastSoap[][] toSoapModels(
		IvldCustomerGtsForecast[][] models) {
		IvldCustomerGtsForecastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldCustomerGtsForecastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldCustomerGtsForecastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldCustomerGtsForecastSoap[] toSoapModels(
		List<IvldCustomerGtsForecast> models) {
		List<IvldCustomerGtsForecastSoap> soapModels = new ArrayList<IvldCustomerGtsForecastSoap>(models.size());

		for (IvldCustomerGtsForecast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldCustomerGtsForecastSoap[soapModels.size()]);
	}

	public IvldCustomerGtsForecastSoap() {
	}

	public int getPrimaryKey() {
		return _ivldCustomerGtsForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldCustomerGtsForecastSid(pk);
	}

	public String getPrice() {
		return _price;
	}

	public void setPrice(String price) {
		_price = price;
	}

	public String getForecastYear() {
		return _forecastYear;
	}

	public void setForecastYear(String forecastYear) {
		_forecastYear = forecastYear;
	}

	public String getDeductionAmount() {
		return _deductionAmount;
	}

	public void setDeductionAmount(String deductionAmount) {
		_deductionAmount = deductionAmount;
	}

	public int getIvldCustomerGtsForecastSid() {
		return _ivldCustomerGtsForecastSid;
	}

	public void setIvldCustomerGtsForecastSid(int ivldCustomerGtsForecastSid) {
		_ivldCustomerGtsForecastSid = ivldCustomerGtsForecastSid;
	}

	public String getDeductionId() {
		return _deductionId;
	}

	public void setDeductionId(String deductionId) {
		_deductionId = deductionId;
	}

	public Date getForecastDate() {
		return _forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		_forecastDate = forecastDate;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public String getSalesAmount() {
		return _salesAmount;
	}

	public void setSalesAmount(String salesAmount) {
		_salesAmount = salesAmount;
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

	public String getDeductionType() {
		return _deductionType;
	}

	public void setDeductionType(String deductionType) {
		_deductionType = deductionType;
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

	public String getDeductionRate() {
		return _deductionRate;
	}

	public void setDeductionRate(String deductionRate) {
		_deductionRate = deductionRate;
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

	public String getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(String companyId) {
		_companyId = companyId;
	}

	public String getForecastValueType() {
		return _forecastValueType;
	}

	public void setForecastValueType(String forecastValueType) {
		_forecastValueType = forecastValueType;
	}

	public String getDeductionCategory() {
		return _deductionCategory;
	}

	public void setDeductionCategory(String deductionCategory) {
		_deductionCategory = deductionCategory;
	}

	public String getAdjustmentCode() {
		return _adjustmentCode;
	}

	public void setAdjustmentCode(String adjustmentCode) {
		_adjustmentCode = adjustmentCode;
	}

	public String getDeductionProgramType() {
		return _deductionProgramType;
	}

	public void setDeductionProgramType(String deductionProgramType) {
		_deductionProgramType = deductionProgramType;
	}

	public int getCustomerGtsForecastIntfId() {
		return _customerGtsForecastIntfId;
	}

	public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId) {
		_customerGtsForecastIntfId = customerGtsForecastIntfId;
	}

	public String getSalesInclusion() {
		return _salesInclusion;
	}

	public void setSalesInclusion(String salesInclusion) {
		_salesInclusion = salesInclusion;
	}

	public String getForecastVer() {
		return _forecastVer;
	}

	public void setForecastVer(String forecastVer) {
		_forecastVer = forecastVer;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getPriceType() {
		return _priceType;
	}

	public void setPriceType(String priceType) {
		_priceType = priceType;
	}

	public String getForecastMonth() {
		return _forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		_forecastMonth = forecastMonth;
	}

	public String getDeductionInclusion() {
		return _deductionInclusion;
	}

	public void setDeductionInclusion(String deductionInclusion) {
		_deductionInclusion = deductionInclusion;
	}

	public String getErrorField() {
		return _errorField;
	}

	public void setErrorField(String errorField) {
		_errorField = errorField;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String brand) {
		_brand = brand;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
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

	private String _price;
	private String _forecastYear;
	private String _deductionAmount;
	private int _ivldCustomerGtsForecastSid;
	private String _deductionId;
	private Date _forecastDate;
	private String _itemId;
	private Date _modifiedDate;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _addChgDelIndicator;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _salesAmount;
	private String _reprocessedFlag;
	private String _udc6;
	private String _udc5;
	private String _deductionType;
	private String _udc4;
	private String _udc1;
	private String _units;
	private String _deductionRate;
	private String _udc2;
	private String _udc3;
	private String _reasonForFailure;
	private String _country;
	private String _companyId;
	private String _forecastValueType;
	private String _deductionCategory;
	private String _adjustmentCode;
	private String _deductionProgramType;
	private int _customerGtsForecastIntfId;
	private String _salesInclusion;
	private String _forecastVer;
	private String _batchId;
	private String _priceType;
	private String _forecastMonth;
	private String _deductionInclusion;
	private String _errorField;
	private String _segment;
	private String _brand;
	private String _forecastName;
	private boolean _checkRecord;
}