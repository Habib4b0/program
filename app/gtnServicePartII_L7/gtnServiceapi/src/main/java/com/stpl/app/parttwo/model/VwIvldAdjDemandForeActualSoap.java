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
public class VwIvldAdjDemandForeActualSoap implements Serializable {
	public static VwIvldAdjDemandForeActualSoap toSoapModel(
		VwIvldAdjDemandForeActual model) {
		VwIvldAdjDemandForeActualSoap soapModel = new VwIvldAdjDemandForeActualSoap();

		soapModel.setForecastVersion(model.getForecastVersion());
		soapModel.setGrossUnits(model.getGrossUnits());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setYear(model.getYear());
		soapModel.setItemId(model.getItemId());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setSource(model.getSource());
		soapModel.setMarketShareRatio(model.getMarketShareRatio());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMarketShareUnits(model.getMarketShareUnits());
		soapModel.setMonth(model.getMonth());
		soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setAdjustedDemandForecastIntfId(model.getAdjustedDemandForecastIntfId());
		soapModel.setCountry(model.getCountry());
		soapModel.setForecastType(model.getForecastType());
		soapModel.setTotalAdjustedDemandUnits(model.getTotalAdjustedDemandUnits());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setIsForecast(model.getIsForecast());
		soapModel.setTotalAdjustedDemandAmount(model.getTotalAdjustedDemandAmount());
		soapModel.setUncapturedUnits(model.getUncapturedUnits());
		soapModel.setGrossPrice(model.getGrossPrice());
		soapModel.setGrossAmount(model.getGrossAmount());
		soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemName(model.getItemName());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setNetSalesPrice(model.getNetSalesPrice());
		soapModel.setNetSalesAmount(model.getNetSalesAmount());
		soapModel.setSegment(model.getSegment());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setIvldAdjustedDemandForecastSid(model.getIvldAdjustedDemandForecastSid());
		soapModel.setMarketSizeUnits(model.getMarketSizeUnits());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static VwIvldAdjDemandForeActualSoap[] toSoapModels(
		VwIvldAdjDemandForeActual[] models) {
		VwIvldAdjDemandForeActualSoap[] soapModels = new VwIvldAdjDemandForeActualSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwIvldAdjDemandForeActualSoap[][] toSoapModels(
		VwIvldAdjDemandForeActual[][] models) {
		VwIvldAdjDemandForeActualSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwIvldAdjDemandForeActualSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwIvldAdjDemandForeActualSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwIvldAdjDemandForeActualSoap[] toSoapModels(
		List<VwIvldAdjDemandForeActual> models) {
		List<VwIvldAdjDemandForeActualSoap> soapModels = new ArrayList<VwIvldAdjDemandForeActualSoap>(models.size());

		for (VwIvldAdjDemandForeActual model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwIvldAdjDemandForeActualSoap[soapModels.size()]);
	}

	public VwIvldAdjDemandForeActualSoap() {
	}

	public int getPrimaryKey() {
		return _ivldAdjustedDemandForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldAdjustedDemandForecastSid(pk);
	}

	public String getForecastVersion() {
		return _forecastVersion;
	}

	public void setForecastVersion(String forecastVersion) {
		_forecastVersion = forecastVersion;
	}

	public String getGrossUnits() {
		return _grossUnits;
	}

	public void setGrossUnits(String grossUnits) {
		_grossUnits = grossUnits;
	}

	public String getBusinessUnitNo() {
		return _businessUnitNo;
	}

	public void setBusinessUnitNo(String businessUnitNo) {
		_businessUnitNo = businessUnitNo;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String year) {
		_year = year;
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

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
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

	public String getMarketShareRatio() {
		return _marketShareRatio;
	}

	public void setMarketShareRatio(String marketShareRatio) {
		_marketShareRatio = marketShareRatio;
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

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	public String getErrorCode() {
		return _errorCode;
	}

	public void setErrorCode(String errorCode) {
		_errorCode = errorCode;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getMarketShareUnits() {
		return _marketShareUnits;
	}

	public void setMarketShareUnits(String marketShareUnits) {
		_marketShareUnits = marketShareUnits;
	}

	public String getMonth() {
		return _month;
	}

	public void setMonth(String month) {
		_month = month;
	}

	public String getInventoryUnitChange() {
		return _inventoryUnitChange;
	}

	public void setInventoryUnitChange(String inventoryUnitChange) {
		_inventoryUnitChange = inventoryUnitChange;
	}

	public String getReprocessedFlag() {
		return _reprocessedFlag;
	}

	public void setReprocessedFlag(String reprocessedFlag) {
		_reprocessedFlag = reprocessedFlag;
	}

	public String getUncapturedUnitsRatio() {
		return _uncapturedUnitsRatio;
	}

	public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
		_uncapturedUnitsRatio = uncapturedUnitsRatio;
	}

	public String getReasonForFailure() {
		return _reasonForFailure;
	}

	public void setReasonForFailure(String reasonForFailure) {
		_reasonForFailure = reasonForFailure;
	}

	public String getAdjustedDemandForecastIntfId() {
		return _adjustedDemandForecastIntfId;
	}

	public void setAdjustedDemandForecastIntfId(
		String adjustedDemandForecastIntfId) {
		_adjustedDemandForecastIntfId = adjustedDemandForecastIntfId;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getForecastType() {
		return _forecastType;
	}

	public void setForecastType(String forecastType) {
		_forecastType = forecastType;
	}

	public String getTotalAdjustedDemandUnits() {
		return _totalAdjustedDemandUnits;
	}

	public void setTotalAdjustedDemandUnits(String totalAdjustedDemandUnits) {
		_totalAdjustedDemandUnits = totalAdjustedDemandUnits;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public String getIsForecast() {
		return _isForecast;
	}

	public void setIsForecast(String isForecast) {
		_isForecast = isForecast;
	}

	public String getTotalAdjustedDemandAmount() {
		return _totalAdjustedDemandAmount;
	}

	public void setTotalAdjustedDemandAmount(String totalAdjustedDemandAmount) {
		_totalAdjustedDemandAmount = totalAdjustedDemandAmount;
	}

	public String getUncapturedUnits() {
		return _uncapturedUnits;
	}

	public void setUncapturedUnits(String uncapturedUnits) {
		_uncapturedUnits = uncapturedUnits;
	}

	public String getGrossPrice() {
		return _grossPrice;
	}

	public void setGrossPrice(String grossPrice) {
		_grossPrice = grossPrice;
	}

	public String getGrossAmount() {
		return _grossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		_grossAmount = grossAmount;
	}

	public String getItemIdentifierCodeQualifier() {
		return _itemIdentifierCodeQualifier;
	}

	public void setItemIdentifierCodeQualifier(
		String itemIdentifierCodeQualifier) {
		_itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
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

	public String getNetSalesPrice() {
		return _netSalesPrice;
	}

	public void setNetSalesPrice(String netSalesPrice) {
		_netSalesPrice = netSalesPrice;
	}

	public String getNetSalesAmount() {
		return _netSalesAmount;
	}

	public void setNetSalesAmount(String netSalesAmount) {
		_netSalesAmount = netSalesAmount;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	public int getIvldAdjustedDemandForecastSid() {
		return _ivldAdjustedDemandForecastSid;
	}

	public void setIvldAdjustedDemandForecastSid(
		int ivldAdjustedDemandForecastSid) {
		_ivldAdjustedDemandForecastSid = ivldAdjustedDemandForecastSid;
	}

	public String getMarketSizeUnits() {
		return _marketSizeUnits;
	}

	public void setMarketSizeUnits(String marketSizeUnits) {
		_marketSizeUnits = marketSizeUnits;
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

	private String _forecastVersion;
	private String _grossUnits;
	private String _businessUnitNo;
	private String _year;
	private String _itemId;
	private String _brandName;
	private Date _modifiedDate;
	private String _organizationKey;
	private Date _createdDate;
	private String _createdBy;
	private String _source;
	private String _marketShareRatio;
	private String _businessUnitName;
	private String _addChgDelIndicator;
	private String _itemIdentifier;
	private String _errorCode;
	private String _modifiedBy;
	private String _marketShareUnits;
	private String _month;
	private String _inventoryUnitChange;
	private String _reprocessedFlag;
	private String _uncapturedUnitsRatio;
	private String _reasonForFailure;
	private String _adjustedDemandForecastIntfId;
	private String _country;
	private String _forecastType;
	private String _totalAdjustedDemandUnits;
	private String _brandId;
	private String _isForecast;
	private String _totalAdjustedDemandAmount;
	private String _uncapturedUnits;
	private String _grossPrice;
	private String _grossAmount;
	private String _itemIdentifierCodeQualifier;
	private String _batchId;
	private String _itemName;
	private String _errorField;
	private String _netSalesPrice;
	private String _netSalesAmount;
	private String _segment;
	private String _forecastName;
	private int _ivldAdjustedDemandForecastSid;
	private String _marketSizeUnits;
	private boolean _checkRecord;
}