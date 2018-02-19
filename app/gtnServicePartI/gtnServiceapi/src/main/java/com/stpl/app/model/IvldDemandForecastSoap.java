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
public class IvldDemandForecastSoap implements Serializable {
	public static IvldDemandForecastSoap toSoapModel(IvldDemandForecast model) {
		IvldDemandForecastSoap soapModel = new IvldDemandForecastSoap();

		soapModel.setForecastYear(model.getForecastYear());
		soapModel.setGrossUnits(model.getGrossUnits());
		soapModel.setTotalDemandUnits(model.getTotalDemandUnits());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setIvldDemandForecastSid(model.getIvldDemandForecastSid());
		soapModel.setSource(model.getSource());
		soapModel.setMarketShareRatio(model.getMarketShareRatio());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setErrorCode(model.getErrorCode());
		soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMarketShareUnits(model.getMarketShareUnits());
		soapModel.setInventoryUnitChange(model.getInventoryUnitChange());
		soapModel.setReprocessedFlag(model.getReprocessedFlag());
		soapModel.setUncapturedUnitsRatio(model.getUncapturedUnitsRatio());
		soapModel.setReasonForFailure(model.getReasonForFailure());
		soapModel.setCountry(model.getCountry());
		soapModel.setForecastType(model.getForecastType());
		soapModel.setBrandId(model.getBrandId());
		soapModel.setDemandForecastInterfaceId(model.getDemandForecastInterfaceId());
		soapModel.setUncapturedUnits(model.getUncapturedUnits());
		soapModel.setGrossPrice(model.getGrossPrice());
		soapModel.setGrossAmount(model.getGrossAmount());
		soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBatchId(model.getBatchId());
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

	public static IvldDemandForecastSoap[] toSoapModels(
		IvldDemandForecast[] models) {
		IvldDemandForecastSoap[] soapModels = new IvldDemandForecastSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static IvldDemandForecastSoap[][] toSoapModels(
		IvldDemandForecast[][] models) {
		IvldDemandForecastSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new IvldDemandForecastSoap[models.length][models[0].length];
		}
		else {
			soapModels = new IvldDemandForecastSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static IvldDemandForecastSoap[] toSoapModels(
		List<IvldDemandForecast> models) {
		List<IvldDemandForecastSoap> soapModels = new ArrayList<IvldDemandForecastSoap>(models.size());

		for (IvldDemandForecast model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new IvldDemandForecastSoap[soapModels.size()]);
	}

	public IvldDemandForecastSoap() {
	}

	public int getPrimaryKey() {
		return _ivldDemandForecastSid;
	}

	public void setPrimaryKey(int pk) {
		setIvldDemandForecastSid(pk);
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

	public String getTotalDemandUnits() {
		return _totalDemandUnits;
	}

	public void setTotalDemandUnits(String totalDemandUnits) {
		_totalDemandUnits = totalDemandUnits;
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

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public int getIvldDemandForecastSid() {
		return _ivldDemandForecastSid;
	}

	public void setIvldDemandForecastSid(int ivldDemandForecastSid) {
		_ivldDemandForecastSid = ivldDemandForecastSid;
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

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
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

	public String getIntfInsertedDate() {
		return _intfInsertedDate;
	}

	public void setIntfInsertedDate(String intfInsertedDate) {
		_intfInsertedDate = intfInsertedDate;
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

	public String getDemandForecastInterfaceId() {
		return _demandForecastInterfaceId;
	}

	public void setDemandForecastInterfaceId(String demandForecastInterfaceId) {
		_demandForecastInterfaceId = demandForecastInterfaceId;
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

	private String _forecastYear;
	private String _grossUnits;
	private String _totalDemandUnits;
	private String _itemId;
	private Date _modifiedDate;
	private String _organizationKey;
	private int _ivldDemandForecastSid;
	private String _source;
	private String _marketShareRatio;
	private String _createdBy;
	private Date _createdDate;
	private String _addChgDelIndicator;
	private String _itemIdentifier;
	private String _errorCode;
	private String _intfInsertedDate;
	private String _modifiedBy;
	private String _marketShareUnits;
	private String _inventoryUnitChange;
	private String _reprocessedFlag;
	private String _uncapturedUnitsRatio;
	private String _reasonForFailure;
	private String _country;
	private String _forecastType;
	private String _brandId;
	private String _demandForecastInterfaceId;
	private String _uncapturedUnits;
	private String _grossPrice;
	private String _grossAmount;
	private String _itemIdentifierCodeQualifier;
	private String _forecastVer;
	private String _batchId;
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