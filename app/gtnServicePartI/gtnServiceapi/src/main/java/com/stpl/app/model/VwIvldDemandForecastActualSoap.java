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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class VwIvldDemandForecastActualSoap implements Serializable {
	public static VwIvldDemandForecastActualSoap toSoapModel(
		VwIvldDemandForecastActual model) {
		VwIvldDemandForecastActualSoap soapModel = new VwIvldDemandForecastActualSoap();

		soapModel.setDemandIntSid(model.getDemandIntSid());
		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setGrossUnits(model.getGrossUnits());
		soapModel.setBusinessUnitNo(model.getBusinessUnitNo());
		soapModel.setTotalDemandUnits(model.getTotalDemandUnits());
		soapModel.setBrandName(model.getBrandName());
		soapModel.setItemId(model.getItemId());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setSource(model.getSource());
		soapModel.setMarketShareRatio(model.getMarketShareRatio());
		soapModel.setIvldDemandActualForecastSid(model.getIvldDemandActualForecastSid());
		soapModel.setBusinessUnitName(model.getBusinessUnitName());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setMarketShareUnits(model.getMarketShareUnits());
		soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCountry(model.getCountry());
		soapModel.setForecastType(model.getForecastType());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setIsForecast(model.getIsForecast());
		soapModel.setUncapturedUnits(model.getUncapturedUnits());
		soapModel.setGrossPrice(model.getGrossPrice());
		soapModel.setIsActive(model.getIsActive());
		soapModel.setGrossAmount(model.getGrossAmount());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setItemName(model.getItemName());
		soapModel.setForecastMonth(model.getForecastMonth());
		soapModel.setErrorField(model.getErrorField());
		soapModel.setNetSalesPrice(model.getNetSalesPrice());
		soapModel.setNetSalesAmount(model.getNetSalesAmount());
		soapModel.setSegment(model.getSegment());
		soapModel.setTotalDemandAmount(model.getTotalDemandAmount());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setMarketSizeUnits(model.getMarketSizeUnits());
		soapModel.setCheckRecord(model.getCheckRecord());

		return soapModel;
	}

	public static VwIvldDemandForecastActualSoap[] toSoapModels(
		VwIvldDemandForecastActual[] models) {
		VwIvldDemandForecastActualSoap[] soapModels = new VwIvldDemandForecastActualSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwIvldDemandForecastActualSoap[][] toSoapModels(
		VwIvldDemandForecastActual[][] models) {
		VwIvldDemandForecastActualSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwIvldDemandForecastActualSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwIvldDemandForecastActualSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwIvldDemandForecastActualSoap[] toSoapModels(
		List<VwIvldDemandForecastActual> models) {
		List<VwIvldDemandForecastActualSoap> soapModels = new ArrayList<VwIvldDemandForecastActualSoap>(models.size());

		for (VwIvldDemandForecastActual model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwIvldDemandForecastActualSoap[soapModels.size()]);
	}

	public VwIvldDemandForecastActualSoap() {
	}

	public int getPrimaryKey() {
		return _ivldDemandActualForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldDemandActualForecastSid(pk);
	}

	public String getDemandIntSid() {
		return _demandIntSid;
	}

	public void setDemandIntSid(String demandIntSid) {
		_demandIntSid = demandIntSid;
	}

	public String getForecastYear() {
		return _forecastYear;
	}

	public void setForecastYear(String forecastYear) {
		_forecastYear = forecastYear;
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

	public String getTotalDemandUnits() {
		return _totalDemandUnits;
	}

	public void setTotalDemandUnits(String totalDemandUnits) {
		_totalDemandUnits = totalDemandUnits;
	}

	public String getBrandName() {
		return _brandName;
	}

	public void setBrandName(String brandName) {
		_brandName = brandName;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
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

	public String getMarketShareRatio() {
		return _marketShareRatio;
	}

	public void setMarketShareRatio(String marketShareRatio) {
		_marketShareRatio = marketShareRatio;
	}

	public int getIvldDemandActualForecastSid() {
		return _ivldDemandActualForecastSid;
	}

	public void setIvldDemandActualForecastSid(int ivldDemandActualForecastSid) {
		_ivldDemandActualForecastSid = ivldDemandActualForecastSid;
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

	public String getMarketShareUnits() {
		return _marketShareUnits;
	}

	public void setMarketShareUnits(String marketShareUnits) {
		_marketShareUnits = marketShareUnits;
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

	public String getIsActive() {
		return _isActive;
	}

	public void setIsActive(String isActive) {
		_isActive = isActive;
	}

	public String getGrossAmount() {
		return _grossAmount;
	}

	public void setGrossAmount(String grossAmount) {
		_grossAmount = grossAmount;
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

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public String getForecastMonth() {
		return _forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		_forecastMonth = forecastMonth;
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

	public String getTotalDemandAmount() {
		return _totalDemandAmount;
	}

	public void setTotalDemandAmount(String totalDemandAmount) {
		_totalDemandAmount = totalDemandAmount;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
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

	private String _demandIntSid;
	private String _forecastYear;
	private String _grossUnits;
	private String _businessUnitNo;
	private String _totalDemandUnits;
	private String _brandName;
	private String _itemId;
	private String _organizationKey;
	private String _source;
	private String _marketShareRatio;
	private int _ivldDemandActualForecastSid;
	private String _businessUnitName;
	private String _addChgDelIndicator;
	private String _errorCode;
	private String _marketShareUnits;
	private String _inventoryUnitChange;
	private String _reprocessedFlag;
	private String _uncapturedUnitsRatio;
	private String _reasonForFailure;
	private String _country;
	private String _forecastType;
	private String _brandId;
	private String _isForecast;
	private String _uncapturedUnits;
	private String _grossPrice;
	private String _isActive;
	private String _grossAmount;
	private String _batchId;
	private String _forecastVer;
	private String _itemName;
	private String _forecastMonth;
	private String _errorField;
	private String _netSalesPrice;
	private String _netSalesAmount;
	private String _segment;
	private String _totalDemandAmount;
	private String _forecastName;
	private String _marketSizeUnits;
	private boolean _checkRecord;
}