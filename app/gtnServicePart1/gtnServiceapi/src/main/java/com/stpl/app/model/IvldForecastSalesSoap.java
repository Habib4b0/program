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
public class IvldForecastSalesSoap implements Serializable {
	public static IvldForecastSalesSoap toSoapModel(IvldForecastSales model) {
		IvldForecastSalesSoap soapModel = new IvldForecastSalesSoap();

		soapModel.setPrice(model.getPrice());
		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setForecastDate(model.getForecastDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setForecastValue(model.getForecastValue());
		soapModel.setForecastIntfid(model.getForecastIntfid());
		soapModel.setDollars(model.getDollars());
		soapModel.setNdc(model.getNdc());
		soapModel.setActualSalesPercentage(model.getActualSalesPercentage());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setActualSalesPercentageMonth(model.getActualSalesPercentageMonth());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setPercentageEstimate(model.getPercentageEstimate());
		soapModel.setPercentageEstimateYear(model.getPercentageEstimateYear());
		soapModel.setUnits(model.getUnits());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setForecastStartDate(model.getForecastStartDate());
		soapModel.setForecastValueType(model.getForecastValueType());
		soapModel.setForecastedSalesPercentMonth(model.getForecastedSalesPercentMonth());
		soapModel.setCountry(model.getCountry());
		soapModel.setProduct(model.getProduct());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setForecastMonth(model.getForecastMonth());
		soapModel.setIvldForecastSalesSid(model.getIvldForecastSalesSid());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setSegment(model.getSegment());
		soapModel.setBrand(model.getBrand());
		soapModel.setForecastedSalesPercentage(model.getForecastedSalesPercentage());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static IvldForecastSalesSoap[] toSoapModels(
		IvldForecastSales[] models) {
		IvldForecastSalesSoap[] soapModels = new IvldForecastSalesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldForecastSalesSoap[][] toSoapModels(
		IvldForecastSales[][] models) {
		IvldForecastSalesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldForecastSalesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldForecastSalesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldForecastSalesSoap[] toSoapModels(
		List<IvldForecastSales> models) {
		List<IvldForecastSalesSoap> soapModels = new ArrayList<IvldForecastSalesSoap>(models.size());

		for (IvldForecastSales model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldForecastSalesSoap[soapModels.size()]);
	}

	public IvldForecastSalesSoap() {
	}

	public int getPrimaryKey() {
		return _ivldForecastSalesSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldForecastSalesSid(pk);
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

	public String getForecastDate() {
		return _forecastDate;
	}

	public void setForecastDate(String forecastDate) {
		_forecastDate = forecastDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getForecastValue() {
		return _forecastValue;
	}

	public void setForecastValue(String forecastValue) {
		_forecastValue = forecastValue;
	}

	public String getForecastIntfid() {
		return _forecastIntfid;
	}

	public void setForecastIntfid(String forecastIntfid) {
		_forecastIntfid = forecastIntfid;
	}

	public String getDollars() {
		return _dollars;
	}

	public void setDollars(String dollars) {
		_dollars = dollars;
	}

	public String getNdc() {
		return _ndc;
	}

	public void setNdc(String ndc) {
		_ndc = ndc;
	}

	public String getActualSalesPercentage() {
		return _actualSalesPercentage;
	}

	public void setActualSalesPercentage(String actualSalesPercentage) {
		_actualSalesPercentage = actualSalesPercentage;
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

	public String getActualSalesPercentageMonth() {
		return _actualSalesPercentageMonth;
	}

	public void setActualSalesPercentageMonth(String actualSalesPercentageMonth) {
		_actualSalesPercentageMonth = actualSalesPercentageMonth;
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

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getPercentageEstimate() {
		return _percentageEstimate;
	}

	public void setPercentageEstimate(String percentageEstimate) {
		_percentageEstimate = percentageEstimate;
	}

	public String getPercentageEstimateYear() {
		return _percentageEstimateYear;
	}

	public void setPercentageEstimateYear(String percentageEstimateYear) {
		_percentageEstimateYear = percentageEstimateYear;
	}

	public String getUnits() {
		return _units;
	}

	public void setUnits(String units) {
		_units = units;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getForecastStartDate() {
		return _forecastStartDate;
	}

	public void setForecastStartDate(String forecastStartDate) {
		_forecastStartDate = forecastStartDate;
	}

	public String getForecastValueType() {
		return _forecastValueType;
	}

	public void setForecastValueType(String forecastValueType) {
		_forecastValueType = forecastValueType;
	}

	public String getForecastedSalesPercentMonth() {
		return _forecastedSalesPercentMonth;
	}

	public void setForecastedSalesPercentMonth(
		String forecastedSalesPercentMonth) {
		_forecastedSalesPercentMonth = forecastedSalesPercentMonth;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getProduct() {
		return _product;
	}

	public void setProduct(String product) {
		_product = product;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getForecastVer() {
		return _forecastVer;
	}

	public void setForecastVer(String forecastVer) {
		_forecastVer = forecastVer;
	}

	public String getForecastMonth() {
		return _forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		_forecastMonth = forecastMonth;
	}

	public int getIvldForecastSalesSid() {
		return _ivldForecastSalesSid;
	}

	public void setIvldForecastSalesSid(int ivldForecastSalesSid) {
		_ivldForecastSalesSid = ivldForecastSalesSid;
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

	public String getForecastedSalesPercentage() {
		return _forecastedSalesPercentage;
	}

	public void setForecastedSalesPercentage(String forecastedSalesPercentage) {
		_forecastedSalesPercentage = forecastedSalesPercentage;
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
	private String _forecastDate;
	private Date _modifiedDate;
	private String _forecastValue;
	private String _forecastIntfid;
	private String _dollars;
	private String _ndc;
	private String _actualSalesPercentage;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _addChgDelIndicator;
	private String _actualSalesPercentageMonth;
	private String _errorCode;
	private Date _intfInsertedDate;
	private String _modifiedBy;
	private String _reprocessedFlag;
	private String _percentageEstimate;
	private String _percentageEstimateYear;
	private String _units;
	private String _reasonForFailure;
	private String _forecastStartDate;
	private String _forecastValueType;
	private String _forecastedSalesPercentMonth;
	private String _country;
	private String _product;
	private String _batchId;
	private String _forecastVer;
	private String _forecastMonth;
	private int _ivldForecastSalesSid;
	private String _errorField;
	private String _segment;
	private String _brand;
	private String _forecastedSalesPercentage;
	private String _forecastName;
	private boolean _checkRecord;
}