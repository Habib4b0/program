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
public class ForecastingMasterSoap implements Serializable {
	public static ForecastingMasterSoap toSoapModel(ForecastingMaster model) {
		ForecastingMasterSoap soapModel = new ForecastingMasterSoap();

		soapModel.setForecastValueType(model.getForecastValueType());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setPercentageEstimate(model.getPercentageEstimate());
		soapModel.setActualSalesPercentageMonth(model.getActualSalesPercentageMonth());
		soapModel.setNdc(model.getNdc());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setCountry(model.getCountry());
		soapModel.setProduct(model.getProduct());
		soapModel.setForecastStartDate(model.getForecastStartDate());
		soapModel.setForecastedSalesPercentage(model.getForecastedSalesPercentage());
		soapModel.setUnits(model.getUnits());
		soapModel.setDollars(model.getDollars());
		soapModel.setForecastMonth(model.getForecastMonth());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSegment(model.getSegment());
		soapModel.setPrice(model.getPrice());
		soapModel.setActualSalesPercentage(model.getActualSalesPercentage());
		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setForecastDate(model.getForecastDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBrand(model.getBrand());
		soapModel.setForecastValue(model.getForecastValue());
		soapModel.setPercentageEstimateYear(model.getPercentageEstimateYear());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setSource(model.getSource());
		soapModel.setForecastMasterSid(model.getForecastMasterSid());
		soapModel.setForecastedSalesPercentMonth(model.getForecastedSalesPercentMonth());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setBusinessUnit(model.getBusinessUnit());

		return soapModel;
	}

	public static ForecastingMasterSoap[] toSoapModels(
		ForecastingMaster[] models) {
		ForecastingMasterSoap[] soapModels = new ForecastingMasterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ForecastingMasterSoap[][] toSoapModels(
		ForecastingMaster[][] models) {
		ForecastingMasterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ForecastingMasterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ForecastingMasterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ForecastingMasterSoap[] toSoapModels(
		List<ForecastingMaster> models) {
		List<ForecastingMasterSoap> soapModels = new ArrayList<ForecastingMasterSoap>(models.size());

		for (ForecastingMaster model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ForecastingMasterSoap[soapModels.size()]);
	}

	public ForecastingMasterSoap() {
	}

	public int getPrimaryKey() {
		return _forecastMasterSid;
	}

	public void setPrimaryKey(int pk) {
		setForecastMasterSid(pk);
	}

	public String getForecastValueType() {
		return _forecastValueType;
	}

	public void setForecastValueType(String forecastValueType) {
		_forecastValueType = forecastValueType;
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

	public String getPercentageEstimate() {
		return _percentageEstimate;
	}

	public void setPercentageEstimate(String percentageEstimate) {
		_percentageEstimate = percentageEstimate;
	}

	public int getActualSalesPercentageMonth() {
		return _actualSalesPercentageMonth;
	}

	public void setActualSalesPercentageMonth(int actualSalesPercentageMonth) {
		_actualSalesPercentageMonth = actualSalesPercentageMonth;
	}

	public String getNdc() {
		return _ndc;
	}

	public void setNdc(String ndc) {
		_ndc = ndc;
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

	public Date getForecastStartDate() {
		return _forecastStartDate;
	}

	public void setForecastStartDate(Date forecastStartDate) {
		_forecastStartDate = forecastStartDate;
	}

	public String getForecastedSalesPercentage() {
		return _forecastedSalesPercentage;
	}

	public void setForecastedSalesPercentage(String forecastedSalesPercentage) {
		_forecastedSalesPercentage = forecastedSalesPercentage;
	}

	public double getUnits() {
		return _units;
	}

	public void setUnits(double units) {
		_units = units;
	}

	public double getDollars() {
		return _dollars;
	}

	public void setDollars(double dollars) {
		_dollars = dollars;
	}

	public String getForecastMonth() {
		return _forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		_forecastMonth = forecastMonth;
	}

	public int getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(int createdBy) {
		_createdBy = createdBy;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public String getActualSalesPercentage() {
		return _actualSalesPercentage;
	}

	public void setActualSalesPercentage(String actualSalesPercentage) {
		_actualSalesPercentage = actualSalesPercentage;
	}

	public String getForecastYear() {
		return _forecastYear;
	}

	public void setForecastYear(String forecastYear) {
		_forecastYear = forecastYear;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	public Date getForecastDate() {
		return _forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		_forecastDate = forecastDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getBrand() {
		return _brand;
	}

	public void setBrand(String brand) {
		_brand = brand;
	}

	public String getForecastValue() {
		return _forecastValue;
	}

	public void setForecastValue(String forecastValue) {
		_forecastValue = forecastValue;
	}

	public int getPercentageEstimateYear() {
		return _percentageEstimateYear;
	}

	public void setPercentageEstimateYear(int percentageEstimateYear) {
		_percentageEstimateYear = percentageEstimateYear;
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

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public int getForecastMasterSid() {
		return _forecastMasterSid;
	}

	public void setForecastMasterSid(int forecastMasterSid) {
		_forecastMasterSid = forecastMasterSid;
	}

	public int getForecastedSalesPercentMonth() {
		return _forecastedSalesPercentMonth;
	}

	public void setForecastedSalesPercentMonth(int forecastedSalesPercentMonth) {
		_forecastedSalesPercentMonth = forecastedSalesPercentMonth;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public int getBusinessUnit() {
		return _businessUnit;
	}

	public void setBusinessUnit(int businessUnit) {
		_businessUnit = businessUnit;
	}

	private String _forecastValueType;
	private int _modifiedBy;
	private Date _createdDate;
	private String _percentageEstimate;
	private int _actualSalesPercentageMonth;
	private String _ndc;
	private String _batchId;
	private String _forecastVer;
	private String _country;
	private String _product;
	private Date _forecastStartDate;
	private String _forecastedSalesPercentage;
	private double _units;
	private double _dollars;
	private String _forecastMonth;
	private int _createdBy;
	private String _segment;
	private double _price;
	private String _actualSalesPercentage;
	private String _forecastYear;
	private String _forecastName;
	private Date _forecastDate;
	private Date _modifiedDate;
	private String _brand;
	private String _forecastValue;
	private int _percentageEstimateYear;
	private boolean _recordLockStatus;
	private String _source;
	private int _forecastMasterSid;
	private int _forecastedSalesPercentMonth;
	private String _inboundStatus;
	private int _businessUnit;
}