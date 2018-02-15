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
public class DemandForecastSoap implements Serializable {
	public static DemandForecastSoap toSoapModel(DemandForecast model) {
		DemandForecastSoap soapModel = new DemandForecastSoap();

		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setTotalDemandUnits(model.getTotalDemandUnits());
		soapModel.setBrandMasterSid(model.getBrandMasterSid());
		soapModel.setMarketShareUnits(model.getMarketShareUnits());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setGrossAmount(model.getGrossAmount());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setGrossUnits(model.getGrossUnits());
		soapModel.setCountry(model.getCountry());
		soapModel.setDemandForecastSid(model.getDemandForecastSid());
		soapModel.setForecastType(model.getForecastType());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setTotalDemandAmount(model.getTotalDemandAmount());
		soapModel.setForecastMonth(model.getForecastMonth());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setMarketSizeUnits(model.getMarketSizeUnits());
		soapModel.setSegment(model.getSegment());
		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setItemId(model.getItemId());
		soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
		soapModel.setGrossPrice(model.getGrossPrice());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setNetSalesAmount(model.getNetSalesAmount());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
		soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
		soapModel.setMarketShareRatio(model.getMarketShareRatio());
		soapModel.setSource(model.getSource());
		soapModel.setUncapturedUnits(model.getUncapturedUnits());
		soapModel.setNetSalesPrice(model.getNetSalesPrice());
		soapModel.setInboundStatus(model.getInboundStatus());

		return soapModel;
	}

	public static DemandForecastSoap[] toSoapModels(DemandForecast[] models) {
		DemandForecastSoap[] soapModels = new DemandForecastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DemandForecastSoap[][] toSoapModels(DemandForecast[][] models) {
		DemandForecastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DemandForecastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DemandForecastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DemandForecastSoap[] toSoapModels(List<DemandForecast> models) {
		List<DemandForecastSoap> soapModels = new ArrayList<DemandForecastSoap>(models.size());

		for (DemandForecast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DemandForecastSoap[soapModels.size()]);
	}

	public DemandForecastSoap() {
	}

	public int getPrimaryKey() {
		return _demandForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setDemandForecastSid(pk);
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public double getTotalDemandUnits() {
		return _totalDemandUnits;
	}

	public void setTotalDemandUnits(double totalDemandUnits) {
		_totalDemandUnits = totalDemandUnits;
	}

	public int getBrandMasterSid() {
		return _brandMasterSid;
	}

	public void setBrandMasterSid(int brandMasterSid) {
		_brandMasterSid = brandMasterSid;
	}

	public double getMarketShareUnits() {
		return _marketShareUnits;
	}

	public void setMarketShareUnits(double marketShareUnits) {
		_marketShareUnits = marketShareUnits;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public double getGrossAmount() {
		return _grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		_grossAmount = grossAmount;
	}

	public String getForecastVer() {
		return _forecastVer;
	}

	public void setForecastVer(String forecastVer) {
		_forecastVer = forecastVer;
	}

	public String getBrandId() {
		return _brandId;
	}

	public void setBrandId(String brandId) {
		_brandId = brandId;
	}

	public double getGrossUnits() {
		return _grossUnits;
	}

	public void setGrossUnits(double grossUnits) {
		_grossUnits = grossUnits;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public int getDemandForecastSid() {
		return _demandForecastSid;
	}

	public void setDemandForecastSid(int demandForecastSid) {
		_demandForecastSid = demandForecastSid;
	}

	public String getForecastType() {
		return _forecastType;
	}

	public void setForecastType(String forecastType) {
		_forecastType = forecastType;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public double getTotalDemandAmount() {
		return _totalDemandAmount;
	}

	public void setTotalDemandAmount(double totalDemandAmount) {
		_totalDemandAmount = totalDemandAmount;
	}

	public String getForecastMonth() {
		return _forecastMonth;
	}

	public void setForecastMonth(String forecastMonth) {
		_forecastMonth = forecastMonth;
	}

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public double getMarketSizeUnits() {
		return _marketSizeUnits;
	}

	public void setMarketSizeUnits(double marketSizeUnits) {
		_marketSizeUnits = marketSizeUnits;
	}

	public String getSegment() {
		return _segment;
	}

	public void setSegment(String segment) {
		_segment = segment;
	}

	public String getForecastYear() {
		return _forecastYear;
	}

	public void setForecastYear(String forecastYear) {
		_forecastYear = forecastYear;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public double getInventoryUnitChange() {
		return _inventoryUnitChange;
	}

	public void setInventoryUnitChange(double inventoryUnitChange) {
		_inventoryUnitChange = inventoryUnitChange;
	}

	public double getGrossPrice() {
		return _grossPrice;
	}

	public void setGrossPrice(double grossPrice) {
		_grossPrice = grossPrice;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	public double getNetSalesAmount() {
		return _netSalesAmount;
	}

	public void setNetSalesAmount(double netSalesAmount) {
		_netSalesAmount = netSalesAmount;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
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

	public String getUncapturedUnitsRatio() {
		return _uncapturedUnitsRatio;
	}

	public void setUncapturedUnitsRatio(String uncapturedUnitsRatio) {
		_uncapturedUnitsRatio = uncapturedUnitsRatio;
	}

	public String getItemIdentifierCodeQualifier() {
		return _itemIdentifierCodeQualifier;
	}

	public void setItemIdentifierCodeQualifier(
		String itemIdentifierCodeQualifier) {
		_itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
	}

	public String getMarketShareRatio() {
		return _marketShareRatio;
	}

	public void setMarketShareRatio(String marketShareRatio) {
		_marketShareRatio = marketShareRatio;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public double getUncapturedUnits() {
		return _uncapturedUnits;
	}

	public void setUncapturedUnits(double uncapturedUnits) {
		_uncapturedUnits = uncapturedUnits;
	}

	public double getNetSalesPrice() {
		return _netSalesPrice;
	}

	public void setNetSalesPrice(double netSalesPrice) {
		_netSalesPrice = netSalesPrice;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	private String _modifiedBy;
	private Date _createdDate;
	private double _totalDemandUnits;
	private int _brandMasterSid;
	private double _marketShareUnits;
	private String _batchId;
	private double _grossAmount;
	private String _forecastVer;
	private String _brandId;
	private double _grossUnits;
	private String _country;
	private int _demandForecastSid;
	private String _forecastType;
	private int _itemMasterSid;
	private double _totalDemandAmount;
	private String _forecastMonth;
	private String _organizationKey;
	private String _createdBy;
	private double _marketSizeUnits;
	private String _segment;
	private String _forecastYear;
	private String _itemId;
	private double _inventoryUnitChange;
	private double _grossPrice;
	private String _forecastName;
	private double _netSalesAmount;
	private Date _modifiedDate;
	private String _itemIdentifier;
	private boolean _recordLockStatus;
	private String _uncapturedUnitsRatio;
	private String _itemIdentifierCodeQualifier;
	private String _marketShareRatio;
	private String _source;
	private double _uncapturedUnits;
	private double _netSalesPrice;
	private String _inboundStatus;
}