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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link VwDemandForecastActual}.
 * </p>
 *
 * @author
 * @see VwDemandForecastActual
 * @generated
 */
@ProviderType
public class VwDemandForecastActualWrapper implements VwDemandForecastActual,
	ModelWrapper<VwDemandForecastActual> {
	public VwDemandForecastActualWrapper(
		VwDemandForecastActual vwDemandForecastActual) {
		_vwDemandForecastActual = vwDemandForecastActual;
	}

	@Override
	public Class<?> getModelClass() {
		return VwDemandForecastActual.class;
	}

	@Override
	public String getModelClassName() {
		return VwDemandForecastActual.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastYear", getForecastYear());
		attributes.put("grossUnits", getGrossUnits());
		attributes.put("businessUnitNo", getBusinessUnitNo());
		attributes.put("totalDemandUnits", getTotalDemandUnits());
		attributes.put("brandName", getBrandName());
		attributes.put("itemId", getItemId());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("marketShareRatio", getMarketShareRatio());
		attributes.put("businessUnitName", getBusinessUnitName());
		attributes.put("demandForecastActualSid", getDemandForecastActualSid());
		attributes.put("marketShareUnits", getMarketShareUnits());
		attributes.put("inventoryUnitChange", getInventoryUnitChange());
		attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
		attributes.put("country", getCountry());
		attributes.put("forecastType", getForecastType());
		attributes.put("brandId", getBrandId());
		attributes.put("isForecast", getIsForecast());
		attributes.put("uncapturedUnits", getUncapturedUnits());
		attributes.put("grossPrice", getGrossPrice());
		attributes.put("isActive", getIsActive());
		attributes.put("grossAmount", getGrossAmount());
		attributes.put("batchId", getBatchId());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("itemName", getItemName());
		attributes.put("forecastMonth", getForecastMonth());
		attributes.put("netSalesPrice", getNetSalesPrice());
		attributes.put("netSalesAmount", getNetSalesAmount());
		attributes.put("segment", getSegment());
		attributes.put("totalDemandAmount", getTotalDemandAmount());
		attributes.put("forecastName", getForecastName());
		attributes.put("marketSizeUnits", getMarketSizeUnits());
		attributes.put("demandId", getDemandId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String forecastYear = (String)attributes.get("forecastYear");

		if (forecastYear != null) {
			setForecastYear(forecastYear);
		}

		Double grossUnits = (Double)attributes.get("grossUnits");

		if (grossUnits != null) {
			setGrossUnits(grossUnits);
		}

		String businessUnitNo = (String)attributes.get("businessUnitNo");

		if (businessUnitNo != null) {
			setBusinessUnitNo(businessUnitNo);
		}

		Double totalDemandUnits = (Double)attributes.get("totalDemandUnits");

		if (totalDemandUnits != null) {
			setTotalDemandUnits(totalDemandUnits);
		}

		String brandName = (String)attributes.get("brandName");

		if (brandName != null) {
			setBrandName(brandName);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String marketShareRatio = (String)attributes.get("marketShareRatio");

		if (marketShareRatio != null) {
			setMarketShareRatio(marketShareRatio);
		}

		String businessUnitName = (String)attributes.get("businessUnitName");

		if (businessUnitName != null) {
			setBusinessUnitName(businessUnitName);
		}

		Integer demandForecastActualSid = (Integer)attributes.get(
				"demandForecastActualSid");

		if (demandForecastActualSid != null) {
			setDemandForecastActualSid(demandForecastActualSid);
		}

		Double marketShareUnits = (Double)attributes.get("marketShareUnits");

		if (marketShareUnits != null) {
			setMarketShareUnits(marketShareUnits);
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

		String isForecast = (String)attributes.get("isForecast");

		if (isForecast != null) {
			setIsForecast(isForecast);
		}

		Double uncapturedUnits = (Double)attributes.get("uncapturedUnits");

		if (uncapturedUnits != null) {
			setUncapturedUnits(uncapturedUnits);
		}

		Double grossPrice = (Double)attributes.get("grossPrice");

		if (grossPrice != null) {
			setGrossPrice(grossPrice);
		}

		String isActive = (String)attributes.get("isActive");

		if (isActive != null) {
			setIsActive(isActive);
		}

		Double grossAmount = (Double)attributes.get("grossAmount");

		if (grossAmount != null) {
			setGrossAmount(grossAmount);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String forecastMonth = (String)attributes.get("forecastMonth");

		if (forecastMonth != null) {
			setForecastMonth(forecastMonth);
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

		Integer demandId = (Integer)attributes.get("demandId");

		if (demandId != null) {
			setDemandId(demandId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwDemandForecastActualWrapper((VwDemandForecastActual)_vwDemandForecastActual.clone());
	}

	@Override
	public int compareTo(VwDemandForecastActual vwDemandForecastActual) {
		return _vwDemandForecastActual.compareTo(vwDemandForecastActual);
	}

	/**
	* Returns the batch ID of this vw demand forecast actual.
	*
	* @return the batch ID of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwDemandForecastActual.getBatchId();
	}

	/**
	* Returns the brand ID of this vw demand forecast actual.
	*
	* @return the brand ID of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getBrandId() {
		return _vwDemandForecastActual.getBrandId();
	}

	/**
	* Returns the brand name of this vw demand forecast actual.
	*
	* @return the brand name of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getBrandName() {
		return _vwDemandForecastActual.getBrandName();
	}

	/**
	* Returns the business unit name of this vw demand forecast actual.
	*
	* @return the business unit name of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getBusinessUnitName() {
		return _vwDemandForecastActual.getBusinessUnitName();
	}

	/**
	* Returns the business unit no of this vw demand forecast actual.
	*
	* @return the business unit no of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getBusinessUnitNo() {
		return _vwDemandForecastActual.getBusinessUnitNo();
	}

	/**
	* Returns the country of this vw demand forecast actual.
	*
	* @return the country of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwDemandForecastActual.getCountry();
	}

	/**
	* Returns the demand forecast actual sid of this vw demand forecast actual.
	*
	* @return the demand forecast actual sid of this vw demand forecast actual
	*/
	@Override
	public int getDemandForecastActualSid() {
		return _vwDemandForecastActual.getDemandForecastActualSid();
	}

	/**
	* Returns the demand ID of this vw demand forecast actual.
	*
	* @return the demand ID of this vw demand forecast actual
	*/
	@Override
	public int getDemandId() {
		return _vwDemandForecastActual.getDemandId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwDemandForecastActual.getExpandoBridge();
	}

	/**
	* Returns the forecast month of this vw demand forecast actual.
	*
	* @return the forecast month of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _vwDemandForecastActual.getForecastMonth();
	}

	/**
	* Returns the forecast name of this vw demand forecast actual.
	*
	* @return the forecast name of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getForecastName() {
		return _vwDemandForecastActual.getForecastName();
	}

	/**
	* Returns the forecast type of this vw demand forecast actual.
	*
	* @return the forecast type of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getForecastType() {
		return _vwDemandForecastActual.getForecastType();
	}

	/**
	* Returns the forecast ver of this vw demand forecast actual.
	*
	* @return the forecast ver of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _vwDemandForecastActual.getForecastVer();
	}

	/**
	* Returns the forecast year of this vw demand forecast actual.
	*
	* @return the forecast year of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _vwDemandForecastActual.getForecastYear();
	}

	/**
	* Returns the gross amount of this vw demand forecast actual.
	*
	* @return the gross amount of this vw demand forecast actual
	*/
	@Override
	public double getGrossAmount() {
		return _vwDemandForecastActual.getGrossAmount();
	}

	/**
	* Returns the gross price of this vw demand forecast actual.
	*
	* @return the gross price of this vw demand forecast actual
	*/
	@Override
	public double getGrossPrice() {
		return _vwDemandForecastActual.getGrossPrice();
	}

	/**
	* Returns the gross units of this vw demand forecast actual.
	*
	* @return the gross units of this vw demand forecast actual
	*/
	@Override
	public double getGrossUnits() {
		return _vwDemandForecastActual.getGrossUnits();
	}

	/**
	* Returns the inventory unit change of this vw demand forecast actual.
	*
	* @return the inventory unit change of this vw demand forecast actual
	*/
	@Override
	public double getInventoryUnitChange() {
		return _vwDemandForecastActual.getInventoryUnitChange();
	}

	/**
	* Returns the is active of this vw demand forecast actual.
	*
	* @return the is active of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getIsActive() {
		return _vwDemandForecastActual.getIsActive();
	}

	/**
	* Returns the is forecast of this vw demand forecast actual.
	*
	* @return the is forecast of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getIsForecast() {
		return _vwDemandForecastActual.getIsForecast();
	}

	/**
	* Returns the item ID of this vw demand forecast actual.
	*
	* @return the item ID of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwDemandForecastActual.getItemId();
	}

	/**
	* Returns the item name of this vw demand forecast actual.
	*
	* @return the item name of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwDemandForecastActual.getItemName();
	}

	/**
	* Returns the market share ratio of this vw demand forecast actual.
	*
	* @return the market share ratio of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getMarketShareRatio() {
		return _vwDemandForecastActual.getMarketShareRatio();
	}

	/**
	* Returns the market share units of this vw demand forecast actual.
	*
	* @return the market share units of this vw demand forecast actual
	*/
	@Override
	public double getMarketShareUnits() {
		return _vwDemandForecastActual.getMarketShareUnits();
	}

	/**
	* Returns the market size units of this vw demand forecast actual.
	*
	* @return the market size units of this vw demand forecast actual
	*/
	@Override
	public double getMarketSizeUnits() {
		return _vwDemandForecastActual.getMarketSizeUnits();
	}

	/**
	* Returns the net sales amount of this vw demand forecast actual.
	*
	* @return the net sales amount of this vw demand forecast actual
	*/
	@Override
	public double getNetSalesAmount() {
		return _vwDemandForecastActual.getNetSalesAmount();
	}

	/**
	* Returns the net sales price of this vw demand forecast actual.
	*
	* @return the net sales price of this vw demand forecast actual
	*/
	@Override
	public double getNetSalesPrice() {
		return _vwDemandForecastActual.getNetSalesPrice();
	}

	/**
	* Returns the organization key of this vw demand forecast actual.
	*
	* @return the organization key of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _vwDemandForecastActual.getOrganizationKey();
	}

	/**
	* Returns the primary key of this vw demand forecast actual.
	*
	* @return the primary key of this vw demand forecast actual
	*/
	@Override
	public int getPrimaryKey() {
		return _vwDemandForecastActual.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwDemandForecastActual.getPrimaryKeyObj();
	}

	/**
	* Returns the segment of this vw demand forecast actual.
	*
	* @return the segment of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getSegment() {
		return _vwDemandForecastActual.getSegment();
	}

	/**
	* Returns the source of this vw demand forecast actual.
	*
	* @return the source of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getSource() {
		return _vwDemandForecastActual.getSource();
	}

	/**
	* Returns the total demand amount of this vw demand forecast actual.
	*
	* @return the total demand amount of this vw demand forecast actual
	*/
	@Override
	public double getTotalDemandAmount() {
		return _vwDemandForecastActual.getTotalDemandAmount();
	}

	/**
	* Returns the total demand units of this vw demand forecast actual.
	*
	* @return the total demand units of this vw demand forecast actual
	*/
	@Override
	public double getTotalDemandUnits() {
		return _vwDemandForecastActual.getTotalDemandUnits();
	}

	/**
	* Returns the uncaptured units of this vw demand forecast actual.
	*
	* @return the uncaptured units of this vw demand forecast actual
	*/
	@Override
	public double getUncapturedUnits() {
		return _vwDemandForecastActual.getUncapturedUnits();
	}

	/**
	* Returns the uncaptured units ratio of this vw demand forecast actual.
	*
	* @return the uncaptured units ratio of this vw demand forecast actual
	*/
	@Override
	public java.lang.String getUncapturedUnitsRatio() {
		return _vwDemandForecastActual.getUncapturedUnitsRatio();
	}

	@Override
	public int hashCode() {
		return _vwDemandForecastActual.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwDemandForecastActual.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwDemandForecastActual.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwDemandForecastActual.isNew();
	}

	@Override
	public void persist() {
		_vwDemandForecastActual.persist();
	}

	/**
	* Sets the batch ID of this vw demand forecast actual.
	*
	* @param batchId the batch ID of this vw demand forecast actual
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwDemandForecastActual.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this vw demand forecast actual.
	*
	* @param brandId the brand ID of this vw demand forecast actual
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_vwDemandForecastActual.setBrandId(brandId);
	}

	/**
	* Sets the brand name of this vw demand forecast actual.
	*
	* @param brandName the brand name of this vw demand forecast actual
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_vwDemandForecastActual.setBrandName(brandName);
	}

	/**
	* Sets the business unit name of this vw demand forecast actual.
	*
	* @param businessUnitName the business unit name of this vw demand forecast actual
	*/
	@Override
	public void setBusinessUnitName(java.lang.String businessUnitName) {
		_vwDemandForecastActual.setBusinessUnitName(businessUnitName);
	}

	/**
	* Sets the business unit no of this vw demand forecast actual.
	*
	* @param businessUnitNo the business unit no of this vw demand forecast actual
	*/
	@Override
	public void setBusinessUnitNo(java.lang.String businessUnitNo) {
		_vwDemandForecastActual.setBusinessUnitNo(businessUnitNo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwDemandForecastActual.setCachedModel(cachedModel);
	}

	/**
	* Sets the country of this vw demand forecast actual.
	*
	* @param country the country of this vw demand forecast actual
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwDemandForecastActual.setCountry(country);
	}

	/**
	* Sets the demand forecast actual sid of this vw demand forecast actual.
	*
	* @param demandForecastActualSid the demand forecast actual sid of this vw demand forecast actual
	*/
	@Override
	public void setDemandForecastActualSid(int demandForecastActualSid) {
		_vwDemandForecastActual.setDemandForecastActualSid(demandForecastActualSid);
	}

	/**
	* Sets the demand ID of this vw demand forecast actual.
	*
	* @param demandId the demand ID of this vw demand forecast actual
	*/
	@Override
	public void setDemandId(int demandId) {
		_vwDemandForecastActual.setDemandId(demandId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwDemandForecastActual.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwDemandForecastActual.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwDemandForecastActual.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast month of this vw demand forecast actual.
	*
	* @param forecastMonth the forecast month of this vw demand forecast actual
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_vwDemandForecastActual.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast name of this vw demand forecast actual.
	*
	* @param forecastName the forecast name of this vw demand forecast actual
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_vwDemandForecastActual.setForecastName(forecastName);
	}

	/**
	* Sets the forecast type of this vw demand forecast actual.
	*
	* @param forecastType the forecast type of this vw demand forecast actual
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_vwDemandForecastActual.setForecastType(forecastType);
	}

	/**
	* Sets the forecast ver of this vw demand forecast actual.
	*
	* @param forecastVer the forecast ver of this vw demand forecast actual
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_vwDemandForecastActual.setForecastVer(forecastVer);
	}

	/**
	* Sets the forecast year of this vw demand forecast actual.
	*
	* @param forecastYear the forecast year of this vw demand forecast actual
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_vwDemandForecastActual.setForecastYear(forecastYear);
	}

	/**
	* Sets the gross amount of this vw demand forecast actual.
	*
	* @param grossAmount the gross amount of this vw demand forecast actual
	*/
	@Override
	public void setGrossAmount(double grossAmount) {
		_vwDemandForecastActual.setGrossAmount(grossAmount);
	}

	/**
	* Sets the gross price of this vw demand forecast actual.
	*
	* @param grossPrice the gross price of this vw demand forecast actual
	*/
	@Override
	public void setGrossPrice(double grossPrice) {
		_vwDemandForecastActual.setGrossPrice(grossPrice);
	}

	/**
	* Sets the gross units of this vw demand forecast actual.
	*
	* @param grossUnits the gross units of this vw demand forecast actual
	*/
	@Override
	public void setGrossUnits(double grossUnits) {
		_vwDemandForecastActual.setGrossUnits(grossUnits);
	}

	/**
	* Sets the inventory unit change of this vw demand forecast actual.
	*
	* @param inventoryUnitChange the inventory unit change of this vw demand forecast actual
	*/
	@Override
	public void setInventoryUnitChange(double inventoryUnitChange) {
		_vwDemandForecastActual.setInventoryUnitChange(inventoryUnitChange);
	}

	/**
	* Sets the is active of this vw demand forecast actual.
	*
	* @param isActive the is active of this vw demand forecast actual
	*/
	@Override
	public void setIsActive(java.lang.String isActive) {
		_vwDemandForecastActual.setIsActive(isActive);
	}

	/**
	* Sets the is forecast of this vw demand forecast actual.
	*
	* @param isForecast the is forecast of this vw demand forecast actual
	*/
	@Override
	public void setIsForecast(java.lang.String isForecast) {
		_vwDemandForecastActual.setIsForecast(isForecast);
	}

	/**
	* Sets the item ID of this vw demand forecast actual.
	*
	* @param itemId the item ID of this vw demand forecast actual
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwDemandForecastActual.setItemId(itemId);
	}

	/**
	* Sets the item name of this vw demand forecast actual.
	*
	* @param itemName the item name of this vw demand forecast actual
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwDemandForecastActual.setItemName(itemName);
	}

	/**
	* Sets the market share ratio of this vw demand forecast actual.
	*
	* @param marketShareRatio the market share ratio of this vw demand forecast actual
	*/
	@Override
	public void setMarketShareRatio(java.lang.String marketShareRatio) {
		_vwDemandForecastActual.setMarketShareRatio(marketShareRatio);
	}

	/**
	* Sets the market share units of this vw demand forecast actual.
	*
	* @param marketShareUnits the market share units of this vw demand forecast actual
	*/
	@Override
	public void setMarketShareUnits(double marketShareUnits) {
		_vwDemandForecastActual.setMarketShareUnits(marketShareUnits);
	}

	/**
	* Sets the market size units of this vw demand forecast actual.
	*
	* @param marketSizeUnits the market size units of this vw demand forecast actual
	*/
	@Override
	public void setMarketSizeUnits(double marketSizeUnits) {
		_vwDemandForecastActual.setMarketSizeUnits(marketSizeUnits);
	}

	/**
	* Sets the net sales amount of this vw demand forecast actual.
	*
	* @param netSalesAmount the net sales amount of this vw demand forecast actual
	*/
	@Override
	public void setNetSalesAmount(double netSalesAmount) {
		_vwDemandForecastActual.setNetSalesAmount(netSalesAmount);
	}

	/**
	* Sets the net sales price of this vw demand forecast actual.
	*
	* @param netSalesPrice the net sales price of this vw demand forecast actual
	*/
	@Override
	public void setNetSalesPrice(double netSalesPrice) {
		_vwDemandForecastActual.setNetSalesPrice(netSalesPrice);
	}

	@Override
	public void setNew(boolean n) {
		_vwDemandForecastActual.setNew(n);
	}

	/**
	* Sets the organization key of this vw demand forecast actual.
	*
	* @param organizationKey the organization key of this vw demand forecast actual
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_vwDemandForecastActual.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this vw demand forecast actual.
	*
	* @param primaryKey the primary key of this vw demand forecast actual
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwDemandForecastActual.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwDemandForecastActual.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the segment of this vw demand forecast actual.
	*
	* @param segment the segment of this vw demand forecast actual
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_vwDemandForecastActual.setSegment(segment);
	}

	/**
	* Sets the source of this vw demand forecast actual.
	*
	* @param source the source of this vw demand forecast actual
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwDemandForecastActual.setSource(source);
	}

	/**
	* Sets the total demand amount of this vw demand forecast actual.
	*
	* @param totalDemandAmount the total demand amount of this vw demand forecast actual
	*/
	@Override
	public void setTotalDemandAmount(double totalDemandAmount) {
		_vwDemandForecastActual.setTotalDemandAmount(totalDemandAmount);
	}

	/**
	* Sets the total demand units of this vw demand forecast actual.
	*
	* @param totalDemandUnits the total demand units of this vw demand forecast actual
	*/
	@Override
	public void setTotalDemandUnits(double totalDemandUnits) {
		_vwDemandForecastActual.setTotalDemandUnits(totalDemandUnits);
	}

	/**
	* Sets the uncaptured units of this vw demand forecast actual.
	*
	* @param uncapturedUnits the uncaptured units of this vw demand forecast actual
	*/
	@Override
	public void setUncapturedUnits(double uncapturedUnits) {
		_vwDemandForecastActual.setUncapturedUnits(uncapturedUnits);
	}

	/**
	* Sets the uncaptured units ratio of this vw demand forecast actual.
	*
	* @param uncapturedUnitsRatio the uncaptured units ratio of this vw demand forecast actual
	*/
	@Override
	public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
		_vwDemandForecastActual.setUncapturedUnitsRatio(uncapturedUnitsRatio);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwDemandForecastActual> toCacheModel() {
		return _vwDemandForecastActual.toCacheModel();
	}

	@Override
	public VwDemandForecastActual toEscapedModel() {
		return new VwDemandForecastActualWrapper(_vwDemandForecastActual.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwDemandForecastActual.toString();
	}

	@Override
	public VwDemandForecastActual toUnescapedModel() {
		return new VwDemandForecastActualWrapper(_vwDemandForecastActual.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwDemandForecastActual.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwDemandForecastActualWrapper)) {
			return false;
		}

		VwDemandForecastActualWrapper vwDemandForecastActualWrapper = (VwDemandForecastActualWrapper)obj;

		if (Objects.equals(_vwDemandForecastActual,
					vwDemandForecastActualWrapper._vwDemandForecastActual)) {
			return true;
		}

		return false;
	}

	@Override
	public VwDemandForecastActual getWrappedModel() {
		return _vwDemandForecastActual;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwDemandForecastActual.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwDemandForecastActual.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwDemandForecastActual.resetOriginalValues();
	}

	private final VwDemandForecastActual _vwDemandForecastActual;
}