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
public class AdjustedDemandForecastSoap implements Serializable {
	public static AdjustedDemandForecastSoap toSoapModel(
		AdjustedDemandForecast model) {
		AdjustedDemandForecastSoap soapModel = new AdjustedDemandForecastSoap();

		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setAdjustedDemandForecastSid(model.getAdjustedDemandForecastSid());
		soapModel.setGrossUnits(model.getGrossUnits());
		soapModel.setTotalDemandUnits(model.getTotalDemandUnits());
		soapModel.setYear(model.getYear());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setMarketShareRatio(model.getMarketShareRatio());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMarketShareUnits(model.getMarketShareUnits());
		soapModel.setMonth(model.getMonth());
		soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
		soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
		soapModel.setCountry(model.getCountry());
		soapModel.setForecastType(model.getForecastType());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setUncapturedUnits(model.getUncapturedUnits());
		soapModel.setGrossPrice(model.getGrossPrice());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setGrossAmount(model.getGrossAmount());
		soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setNetSalesPrice(model.getNetSalesPrice());
		soapModel.setNetSalesAmount(model.getNetSalesAmount());
		soapModel.setSegment(model.getSegment());
		soapModel.setTotalDemandAmount(model.getTotalDemandAmount());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setMarketSizeUnits(model.getMarketSizeUnits());

		return soapModel;
	}

	public static AdjustedDemandForecastSoap[] toSoapModels(
		AdjustedDemandForecast[] models) {
		AdjustedDemandForecastSoap[] soapModels = new AdjustedDemandForecastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AdjustedDemandForecastSoap[][] toSoapModels(
		AdjustedDemandForecast[][] models) {
		AdjustedDemandForecastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AdjustedDemandForecastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AdjustedDemandForecastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AdjustedDemandForecastSoap[] toSoapModels(
		List<AdjustedDemandForecast> models) {
		List<AdjustedDemandForecastSoap> soapModels = new ArrayList<AdjustedDemandForecastSoap>(models.size());

		for (AdjustedDemandForecast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AdjustedDemandForecastSoap[soapModels.size()]);
	}

	public AdjustedDemandForecastSoap() {
	}

	public int getPrimaryKey() {
		return _adjustedDemandForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setAdjustedDemandForecastSid(pk);
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public int getAdjustedDemandForecastSid() {
		return _adjustedDemandForecastSid;
	}

	public void setAdjustedDemandForecastSid(int adjustedDemandForecastSid) {
		_adjustedDemandForecastSid = adjustedDemandForecastSid;
	}

	public double getGrossUnits() {
		return _grossUnits;
	}

	public void setGrossUnits(double grossUnits) {
		_grossUnits = grossUnits;
	}

	public double getTotalDemandUnits() {
		return _totalDemandUnits;
	}

	public void setTotalDemandUnits(double totalDemandUnits) {
		_totalDemandUnits = totalDemandUnits;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
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

	public String getMarketShareRatio() {
		return _marketShareRatio;
	}

	public void setMarketShareRatio(String marketShareRatio) {
		_marketShareRatio = marketShareRatio;
	}

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public double getMarketShareUnits() {
		return _marketShareUnits;
	}

	public void setMarketShareUnits(double marketShareUnits) {
		_marketShareUnits = marketShareUnits;
	}

	public String getMonth() {
		return _month;
	}

	public void setMonth(String month) {
		_month = month;
	}

	public double getInventoryUnitChange() {
		return _inventoryUnitChange;
	}

	public void setInventoryUnitChange(double inventoryUnitChange) {
		_inventoryUnitChange = inventoryUnitChange;
	}

	public String getUncapturedUnitsRatio() {
		return _uncapturedUnitsRatio;
	}

	public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
		_uncapturedUnitsRatio = uncapturedUnitsRatio;
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

	public double getUncapturedUnits() {
		return _uncapturedUnits;
	}

	public void setUncapturedUnits(double uncapturedUnits) {
		_uncapturedUnits = uncapturedUnits;
	}

	public double getGrossPrice() {
		return _grossPrice;
	}

	public void setGrossPrice(double grossPrice) {
		_grossPrice = grossPrice;
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

	public double getGrossAmount() {
		return _grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		_grossAmount = grossAmount;
	}

	public String getItemIdentifierCodeQualifier() {
		return _itemIdentifierCodeQualifier;
	}

	public void setItemIdentifierCodeQualifier(
		String itemIdentifierCodeQualifier) {
		_itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
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

	public double getNetSalesPrice() {
		return _netSalesPrice;
	}

	public void setNetSalesPrice(double netSalesPrice) {
		_netSalesPrice = netSalesPrice;
	}

	public double getNetSalesAmount() {
		return _netSalesAmount;
	}

	public void setNetSalesAmount(double netSalesAmount) {
		_netSalesAmount = netSalesAmount;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	public double getTotalDemandAmount() {
		return _totalDemandAmount;
	}

	public void setTotalDemandAmount(double totalDemandAmount) {
		_totalDemandAmount = totalDemandAmount;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	public double getMarketSizeUnits() {
		return _marketSizeUnits;
	}

	public void setMarketSizeUnits(double marketSizeUnits) {
		_marketSizeUnits = marketSizeUnits;
	}

	private int _itemMasterSid;
	private int _adjustedDemandForecastSid;
	private double _grossUnits;
	private double _totalDemandUnits;
	private String _year;
	private String _itemId;
	private Date _modifiedDate;
	private int _brandMasterSid;
	private String _organizationKey;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private String _marketShareRatio;
	private String _itemIdentifier;
	private String _inboundStatus;
	private String _modifiedBy;
	private double _marketShareUnits;
	private String _month;
	private double _inventoryUnitChange;
	private String _uncapturedUnitsRatio;
	private String _country;
	private String _forecastType;
	private String _brandId;
	private double _uncapturedUnits;
	private double _grossPrice;
	private boolean _recordLockStatus;
	private double _grossAmount;
	private String _itemIdentifierCodeQualifier;
	private String _forecastVer;
	private String _batchId;
	private double _netSalesPrice;
	private double _netSalesAmount;
	private String _segment;
	private double _totalDemandAmount;
	private String _forecastName;
	private double _marketSizeUnits;
}