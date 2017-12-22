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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AdjustedDemandForecast}.
 * </p>
 *
 * @author
 * @see AdjustedDemandForecast
 * @generated
 */
@ProviderType
public class AdjustedDemandForecastWrapper implements AdjustedDemandForecast,
	ModelWrapper<AdjustedDemandForecast> {
	public AdjustedDemandForecastWrapper(
		AdjustedDemandForecast adjustedDemandForecast) {
		_adjustedDemandForecast = adjustedDemandForecast;
	}

	@Override
	public Class<?> getModelClass() {
		return AdjustedDemandForecast.class;
	}

	@Override
	public String getModelClassName() {
		return AdjustedDemandForecast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("adjustedDemandForecastSid",
			getAdjustedDemandForecastSid());
		attributes.put("grossUnits", getGrossUnits());
		attributes.put("totalDemandUnits", getTotalDemandUnits());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("marketShareRatio", getMarketShareRatio());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("marketShareUnits", getMarketShareUnits());
		attributes.put("month", getMonth());
		attributes.put("inventoryUnitChange", getInventoryUnitChange());
		attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
		attributes.put("country", getCountry());
		attributes.put("forecastType", getForecastType());
		attributes.put("brandId", getBrandId());
		attributes.put("uncapturedUnits", getUncapturedUnits());
		attributes.put("grossPrice", getGrossPrice());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("grossAmount", getGrossAmount());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("netSalesPrice", getNetSalesPrice());
		attributes.put("netSalesAmount", getNetSalesAmount());
		attributes.put("segment", getSegment());
		attributes.put("totalDemandAmount", getTotalDemandAmount());
		attributes.put("forecastName", getForecastName());
		attributes.put("marketSizeUnits", getMarketSizeUnits());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer adjustedDemandForecastSid = (Integer)attributes.get(
				"adjustedDemandForecastSid");

		if (adjustedDemandForecastSid != null) {
			setAdjustedDemandForecastSid(adjustedDemandForecastSid);
		}

		Double grossUnits = (Double)attributes.get("grossUnits");

		if (grossUnits != null) {
			setGrossUnits(grossUnits);
		}

		Double totalDemandUnits = (Double)attributes.get("totalDemandUnits");

		if (totalDemandUnits != null) {
			setTotalDemandUnits(totalDemandUnits);
		}

		String year = (String)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Integer brandMasterSid = (Integer)attributes.get("brandMasterSid");

		if (brandMasterSid != null) {
			setBrandMasterSid(brandMasterSid);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		String marketShareRatio = (String)attributes.get("marketShareRatio");

		if (marketShareRatio != null) {
			setMarketShareRatio(marketShareRatio);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Double marketShareUnits = (Double)attributes.get("marketShareUnits");

		if (marketShareUnits != null) {
			setMarketShareUnits(marketShareUnits);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		Double inventoryUnitChange = (Double)attributes.get(
				"inventoryUnitChange");

		if (inventoryUnitChange != null) {
			setInventoryUnitChange(inventoryUnitChange);
		}

		String uncapturedUnitsRatio = (String)attributes.get(
				"uncapturedUnitsRatio");

		if (uncapturedUnitsRatio != null) {
			setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String forecastType = (String)attributes.get("forecastType");

		if (forecastType != null) {
			setForecastType(forecastType);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		Double uncapturedUnits = (Double)attributes.get("uncapturedUnits");

		if (uncapturedUnits != null) {
			setUncapturedUnits(uncapturedUnits);
		}

		Double grossPrice = (Double)attributes.get("grossPrice");

		if (grossPrice != null) {
			setGrossPrice(grossPrice);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		Double grossAmount = (Double)attributes.get("grossAmount");

		if (grossAmount != null) {
			setGrossAmount(grossAmount);
		}

		String itemIdentifierCodeQualifier = (String)attributes.get(
				"itemIdentifierCodeQualifier");

		if (itemIdentifierCodeQualifier != null) {
			setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Double netSalesPrice = (Double)attributes.get("netSalesPrice");

		if (netSalesPrice != null) {
			setNetSalesPrice(netSalesPrice);
		}

		Double netSalesAmount = (Double)attributes.get("netSalesAmount");

		if (netSalesAmount != null) {
			setNetSalesAmount(netSalesAmount);
		}

		String segment = (String)attributes.get("segment");

		if (segment != null) {
			setSegment(segment);
		}

		Double totalDemandAmount = (Double)attributes.get("totalDemandAmount");

		if (totalDemandAmount != null) {
			setTotalDemandAmount(totalDemandAmount);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		Double marketSizeUnits = (Double)attributes.get("marketSizeUnits");

		if (marketSizeUnits != null) {
			setMarketSizeUnits(marketSizeUnits);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AdjustedDemandForecastWrapper((AdjustedDemandForecast)_adjustedDemandForecast.clone());
	}

	@Override
	public int compareTo(AdjustedDemandForecast adjustedDemandForecast) {
		return _adjustedDemandForecast.compareTo(adjustedDemandForecast);
	}

	/**
	* Returns the adjusted demand forecast sid of this adjusted demand forecast.
	*
	* @return the adjusted demand forecast sid of this adjusted demand forecast
	*/
	@Override
	public int getAdjustedDemandForecastSid() {
		return _adjustedDemandForecast.getAdjustedDemandForecastSid();
	}

	/**
	* Returns the batch ID of this adjusted demand forecast.
	*
	* @return the batch ID of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getBatchId() {
		return _adjustedDemandForecast.getBatchId();
	}

	/**
	* Returns the brand ID of this adjusted demand forecast.
	*
	* @return the brand ID of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getBrandId() {
		return _adjustedDemandForecast.getBrandId();
	}

	/**
	* Returns the brand master sid of this adjusted demand forecast.
	*
	* @return the brand master sid of this adjusted demand forecast
	*/
	@Override
	public int getBrandMasterSid() {
		return _adjustedDemandForecast.getBrandMasterSid();
	}

	/**
	* Returns the country of this adjusted demand forecast.
	*
	* @return the country of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getCountry() {
		return _adjustedDemandForecast.getCountry();
	}

	/**
	* Returns the created by of this adjusted demand forecast.
	*
	* @return the created by of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _adjustedDemandForecast.getCreatedBy();
	}

	/**
	* Returns the created date of this adjusted demand forecast.
	*
	* @return the created date of this adjusted demand forecast
	*/
	@Override
	public Date getCreatedDate() {
		return _adjustedDemandForecast.getCreatedDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _adjustedDemandForecast.getExpandoBridge();
	}

	/**
	* Returns the forecast name of this adjusted demand forecast.
	*
	* @return the forecast name of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getForecastName() {
		return _adjustedDemandForecast.getForecastName();
	}

	/**
	* Returns the forecast type of this adjusted demand forecast.
	*
	* @return the forecast type of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getForecastType() {
		return _adjustedDemandForecast.getForecastType();
	}

	/**
	* Returns the forecast ver of this adjusted demand forecast.
	*
	* @return the forecast ver of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _adjustedDemandForecast.getForecastVer();
	}

	/**
	* Returns the gross amount of this adjusted demand forecast.
	*
	* @return the gross amount of this adjusted demand forecast
	*/
	@Override
	public double getGrossAmount() {
		return _adjustedDemandForecast.getGrossAmount();
	}

	/**
	* Returns the gross price of this adjusted demand forecast.
	*
	* @return the gross price of this adjusted demand forecast
	*/
	@Override
	public double getGrossPrice() {
		return _adjustedDemandForecast.getGrossPrice();
	}

	/**
	* Returns the gross units of this adjusted demand forecast.
	*
	* @return the gross units of this adjusted demand forecast
	*/
	@Override
	public double getGrossUnits() {
		return _adjustedDemandForecast.getGrossUnits();
	}

	/**
	* Returns the inbound status of this adjusted demand forecast.
	*
	* @return the inbound status of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _adjustedDemandForecast.getInboundStatus();
	}

	/**
	* Returns the inventory unit change of this adjusted demand forecast.
	*
	* @return the inventory unit change of this adjusted demand forecast
	*/
	@Override
	public double getInventoryUnitChange() {
		return _adjustedDemandForecast.getInventoryUnitChange();
	}

	/**
	* Returns the item ID of this adjusted demand forecast.
	*
	* @return the item ID of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getItemId() {
		return _adjustedDemandForecast.getItemId();
	}

	/**
	* Returns the item identifier of this adjusted demand forecast.
	*
	* @return the item identifier of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _adjustedDemandForecast.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this adjusted demand forecast.
	*
	* @return the item identifier code qualifier of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _adjustedDemandForecast.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the item master sid of this adjusted demand forecast.
	*
	* @return the item master sid of this adjusted demand forecast
	*/
	@Override
	public int getItemMasterSid() {
		return _adjustedDemandForecast.getItemMasterSid();
	}

	/**
	* Returns the market share ratio of this adjusted demand forecast.
	*
	* @return the market share ratio of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getMarketShareRatio() {
		return _adjustedDemandForecast.getMarketShareRatio();
	}

	/**
	* Returns the market share units of this adjusted demand forecast.
	*
	* @return the market share units of this adjusted demand forecast
	*/
	@Override
	public double getMarketShareUnits() {
		return _adjustedDemandForecast.getMarketShareUnits();
	}

	/**
	* Returns the market size units of this adjusted demand forecast.
	*
	* @return the market size units of this adjusted demand forecast
	*/
	@Override
	public double getMarketSizeUnits() {
		return _adjustedDemandForecast.getMarketSizeUnits();
	}

	/**
	* Returns the modified by of this adjusted demand forecast.
	*
	* @return the modified by of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _adjustedDemandForecast.getModifiedBy();
	}

	/**
	* Returns the modified date of this adjusted demand forecast.
	*
	* @return the modified date of this adjusted demand forecast
	*/
	@Override
	public Date getModifiedDate() {
		return _adjustedDemandForecast.getModifiedDate();
	}

	/**
	* Returns the month of this adjusted demand forecast.
	*
	* @return the month of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getMonth() {
		return _adjustedDemandForecast.getMonth();
	}

	/**
	* Returns the net sales amount of this adjusted demand forecast.
	*
	* @return the net sales amount of this adjusted demand forecast
	*/
	@Override
	public double getNetSalesAmount() {
		return _adjustedDemandForecast.getNetSalesAmount();
	}

	/**
	* Returns the net sales price of this adjusted demand forecast.
	*
	* @return the net sales price of this adjusted demand forecast
	*/
	@Override
	public double getNetSalesPrice() {
		return _adjustedDemandForecast.getNetSalesPrice();
	}

	/**
	* Returns the organization key of this adjusted demand forecast.
	*
	* @return the organization key of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _adjustedDemandForecast.getOrganizationKey();
	}

	/**
	* Returns the primary key of this adjusted demand forecast.
	*
	* @return the primary key of this adjusted demand forecast
	*/
	@Override
	public int getPrimaryKey() {
		return _adjustedDemandForecast.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _adjustedDemandForecast.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this adjusted demand forecast.
	*
	* @return the record lock status of this adjusted demand forecast
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _adjustedDemandForecast.getRecordLockStatus();
	}

	/**
	* Returns the segment of this adjusted demand forecast.
	*
	* @return the segment of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getSegment() {
		return _adjustedDemandForecast.getSegment();
	}

	/**
	* Returns the source of this adjusted demand forecast.
	*
	* @return the source of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getSource() {
		return _adjustedDemandForecast.getSource();
	}

	/**
	* Returns the total demand amount of this adjusted demand forecast.
	*
	* @return the total demand amount of this adjusted demand forecast
	*/
	@Override
	public double getTotalDemandAmount() {
		return _adjustedDemandForecast.getTotalDemandAmount();
	}

	/**
	* Returns the total demand units of this adjusted demand forecast.
	*
	* @return the total demand units of this adjusted demand forecast
	*/
	@Override
	public double getTotalDemandUnits() {
		return _adjustedDemandForecast.getTotalDemandUnits();
	}

	/**
	* Returns the uncaptured units of this adjusted demand forecast.
	*
	* @return the uncaptured units of this adjusted demand forecast
	*/
	@Override
	public double getUncapturedUnits() {
		return _adjustedDemandForecast.getUncapturedUnits();
	}

	/**
	* Returns the uncaptured units ratio of this adjusted demand forecast.
	*
	* @return the uncaptured units ratio of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getUncapturedUnitsRatio() {
		return _adjustedDemandForecast.getUncapturedUnitsRatio();
	}

	/**
	* Returns the year of this adjusted demand forecast.
	*
	* @return the year of this adjusted demand forecast
	*/
	@Override
	public java.lang.String getYear() {
		return _adjustedDemandForecast.getYear();
	}

	@Override
	public int hashCode() {
		return _adjustedDemandForecast.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _adjustedDemandForecast.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _adjustedDemandForecast.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _adjustedDemandForecast.isNew();
	}

	/**
	* Returns <code>true</code> if this adjusted demand forecast is record lock status.
	*
	* @return <code>true</code> if this adjusted demand forecast is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _adjustedDemandForecast.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_adjustedDemandForecast.persist();
	}

	/**
	* Sets the adjusted demand forecast sid of this adjusted demand forecast.
	*
	* @param adjustedDemandForecastSid the adjusted demand forecast sid of this adjusted demand forecast
	*/
	@Override
	public void setAdjustedDemandForecastSid(int adjustedDemandForecastSid) {
		_adjustedDemandForecast.setAdjustedDemandForecastSid(adjustedDemandForecastSid);
	}

	/**
	* Sets the batch ID of this adjusted demand forecast.
	*
	* @param batchId the batch ID of this adjusted demand forecast
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_adjustedDemandForecast.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this adjusted demand forecast.
	*
	* @param brandId the brand ID of this adjusted demand forecast
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_adjustedDemandForecast.setBrandId(brandId);
	}

	/**
	* Sets the brand master sid of this adjusted demand forecast.
	*
	* @param brandMasterSid the brand master sid of this adjusted demand forecast
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_adjustedDemandForecast.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_adjustedDemandForecast.setCachedModel(cachedModel);
	}

	/**
	* Sets the country of this adjusted demand forecast.
	*
	* @param country the country of this adjusted demand forecast
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_adjustedDemandForecast.setCountry(country);
	}

	/**
	* Sets the created by of this adjusted demand forecast.
	*
	* @param createdBy the created by of this adjusted demand forecast
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_adjustedDemandForecast.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this adjusted demand forecast.
	*
	* @param createdDate the created date of this adjusted demand forecast
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_adjustedDemandForecast.setCreatedDate(createdDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_adjustedDemandForecast.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_adjustedDemandForecast.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_adjustedDemandForecast.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast name of this adjusted demand forecast.
	*
	* @param forecastName the forecast name of this adjusted demand forecast
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_adjustedDemandForecast.setForecastName(forecastName);
	}

	/**
	* Sets the forecast type of this adjusted demand forecast.
	*
	* @param forecastType the forecast type of this adjusted demand forecast
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_adjustedDemandForecast.setForecastType(forecastType);
	}

	/**
	* Sets the forecast ver of this adjusted demand forecast.
	*
	* @param forecastVer the forecast ver of this adjusted demand forecast
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_adjustedDemandForecast.setForecastVer(forecastVer);
	}

	/**
	* Sets the gross amount of this adjusted demand forecast.
	*
	* @param grossAmount the gross amount of this adjusted demand forecast
	*/
	@Override
	public void setGrossAmount(double grossAmount) {
		_adjustedDemandForecast.setGrossAmount(grossAmount);
	}

	/**
	* Sets the gross price of this adjusted demand forecast.
	*
	* @param grossPrice the gross price of this adjusted demand forecast
	*/
	@Override
	public void setGrossPrice(double grossPrice) {
		_adjustedDemandForecast.setGrossPrice(grossPrice);
	}

	/**
	* Sets the gross units of this adjusted demand forecast.
	*
	* @param grossUnits the gross units of this adjusted demand forecast
	*/
	@Override
	public void setGrossUnits(double grossUnits) {
		_adjustedDemandForecast.setGrossUnits(grossUnits);
	}

	/**
	* Sets the inbound status of this adjusted demand forecast.
	*
	* @param inboundStatus the inbound status of this adjusted demand forecast
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_adjustedDemandForecast.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the inventory unit change of this adjusted demand forecast.
	*
	* @param inventoryUnitChange the inventory unit change of this adjusted demand forecast
	*/
	@Override
	public void setInventoryUnitChange(double inventoryUnitChange) {
		_adjustedDemandForecast.setInventoryUnitChange(inventoryUnitChange);
	}

	/**
	* Sets the item ID of this adjusted demand forecast.
	*
	* @param itemId the item ID of this adjusted demand forecast
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_adjustedDemandForecast.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this adjusted demand forecast.
	*
	* @param itemIdentifier the item identifier of this adjusted demand forecast
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_adjustedDemandForecast.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this adjusted demand forecast.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this adjusted demand forecast
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_adjustedDemandForecast.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the item master sid of this adjusted demand forecast.
	*
	* @param itemMasterSid the item master sid of this adjusted demand forecast
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_adjustedDemandForecast.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the market share ratio of this adjusted demand forecast.
	*
	* @param marketShareRatio the market share ratio of this adjusted demand forecast
	*/
	@Override
	public void setMarketShareRatio(java.lang.String marketShareRatio) {
		_adjustedDemandForecast.setMarketShareRatio(marketShareRatio);
	}

	/**
	* Sets the market share units of this adjusted demand forecast.
	*
	* @param marketShareUnits the market share units of this adjusted demand forecast
	*/
	@Override
	public void setMarketShareUnits(double marketShareUnits) {
		_adjustedDemandForecast.setMarketShareUnits(marketShareUnits);
	}

	/**
	* Sets the market size units of this adjusted demand forecast.
	*
	* @param marketSizeUnits the market size units of this adjusted demand forecast
	*/
	@Override
	public void setMarketSizeUnits(double marketSizeUnits) {
		_adjustedDemandForecast.setMarketSizeUnits(marketSizeUnits);
	}

	/**
	* Sets the modified by of this adjusted demand forecast.
	*
	* @param modifiedBy the modified by of this adjusted demand forecast
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_adjustedDemandForecast.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this adjusted demand forecast.
	*
	* @param modifiedDate the modified date of this adjusted demand forecast
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_adjustedDemandForecast.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this adjusted demand forecast.
	*
	* @param month the month of this adjusted demand forecast
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_adjustedDemandForecast.setMonth(month);
	}

	/**
	* Sets the net sales amount of this adjusted demand forecast.
	*
	* @param netSalesAmount the net sales amount of this adjusted demand forecast
	*/
	@Override
	public void setNetSalesAmount(double netSalesAmount) {
		_adjustedDemandForecast.setNetSalesAmount(netSalesAmount);
	}

	/**
	* Sets the net sales price of this adjusted demand forecast.
	*
	* @param netSalesPrice the net sales price of this adjusted demand forecast
	*/
	@Override
	public void setNetSalesPrice(double netSalesPrice) {
		_adjustedDemandForecast.setNetSalesPrice(netSalesPrice);
	}

	@Override
	public void setNew(boolean n) {
		_adjustedDemandForecast.setNew(n);
	}

	/**
	* Sets the organization key of this adjusted demand forecast.
	*
	* @param organizationKey the organization key of this adjusted demand forecast
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_adjustedDemandForecast.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this adjusted demand forecast.
	*
	* @param primaryKey the primary key of this adjusted demand forecast
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_adjustedDemandForecast.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_adjustedDemandForecast.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this adjusted demand forecast is record lock status.
	*
	* @param recordLockStatus the record lock status of this adjusted demand forecast
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_adjustedDemandForecast.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the segment of this adjusted demand forecast.
	*
	* @param segment the segment of this adjusted demand forecast
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_adjustedDemandForecast.setSegment(segment);
	}

	/**
	* Sets the source of this adjusted demand forecast.
	*
	* @param source the source of this adjusted demand forecast
	*/
	@Override
	public void setSource(java.lang.String source) {
		_adjustedDemandForecast.setSource(source);
	}

	/**
	* Sets the total demand amount of this adjusted demand forecast.
	*
	* @param totalDemandAmount the total demand amount of this adjusted demand forecast
	*/
	@Override
	public void setTotalDemandAmount(double totalDemandAmount) {
		_adjustedDemandForecast.setTotalDemandAmount(totalDemandAmount);
	}

	/**
	* Sets the total demand units of this adjusted demand forecast.
	*
	* @param totalDemandUnits the total demand units of this adjusted demand forecast
	*/
	@Override
	public void setTotalDemandUnits(double totalDemandUnits) {
		_adjustedDemandForecast.setTotalDemandUnits(totalDemandUnits);
	}

	/**
	* Sets the uncaptured units of this adjusted demand forecast.
	*
	* @param uncapturedUnits the uncaptured units of this adjusted demand forecast
	*/
	@Override
	public void setUncapturedUnits(double uncapturedUnits) {
		_adjustedDemandForecast.setUncapturedUnits(uncapturedUnits);
	}

	/**
	* Sets the uncaptured units ratio of this adjusted demand forecast.
	*
	* @param uncapturedUnitsRatio the uncaptured units ratio of this adjusted demand forecast
	*/
	@Override
	public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
		_adjustedDemandForecast.setUncapturedUnitsRatio(uncapturedUnitsRatio);
	}

	/**
	* Sets the year of this adjusted demand forecast.
	*
	* @param year the year of this adjusted demand forecast
	*/
	@Override
	public void setYear(java.lang.String year) {
		_adjustedDemandForecast.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AdjustedDemandForecast> toCacheModel() {
		return _adjustedDemandForecast.toCacheModel();
	}

	@Override
	public AdjustedDemandForecast toEscapedModel() {
		return new AdjustedDemandForecastWrapper(_adjustedDemandForecast.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _adjustedDemandForecast.toString();
	}

	@Override
	public AdjustedDemandForecast toUnescapedModel() {
		return new AdjustedDemandForecastWrapper(_adjustedDemandForecast.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _adjustedDemandForecast.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AdjustedDemandForecastWrapper)) {
			return false;
		}

		AdjustedDemandForecastWrapper adjustedDemandForecastWrapper = (AdjustedDemandForecastWrapper)obj;

		if (Objects.equals(_adjustedDemandForecast,
					adjustedDemandForecastWrapper._adjustedDemandForecast)) {
			return true;
		}

		return false;
	}

	@Override
	public AdjustedDemandForecast getWrappedModel() {
		return _adjustedDemandForecast;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _adjustedDemandForecast.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _adjustedDemandForecast.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_adjustedDemandForecast.resetOriginalValues();
	}

	private final AdjustedDemandForecast _adjustedDemandForecast;
}