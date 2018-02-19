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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link VwAdjustDemandForecastAct}.
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastAct
 * @generated
 */
@ProviderType
public class VwAdjustDemandForecastActWrapper
	implements VwAdjustDemandForecastAct,
		ModelWrapper<VwAdjustDemandForecastAct> {
	public VwAdjustDemandForecastActWrapper(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		_vwAdjustDemandForecastAct = vwAdjustDemandForecastAct;
	}

	@Override
	public Class<?> getModelClass() {
		return VwAdjustDemandForecastAct.class;
	}

	@Override
	public String getModelClassName() {
		return VwAdjustDemandForecastAct.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastVer", getForecastVer());
		attributes.put("grossUnits", getGrossUnits());
		attributes.put("businessUnitNo", getBusinessUnitNo());
		attributes.put("forecastYear", getForecastYear());
		attributes.put("brandName", getBrandName());
		attributes.put("itemId", getItemId());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("marketShareRatio", getMarketShareRatio());
		attributes.put("businessUnitName", getBusinessUnitName());
		attributes.put("marketShareUnits", getMarketShareUnits());
		attributes.put("forecastMonth", getForecastMonth());
		attributes.put("inventoryUnitChange", getInventoryUnitChange());
		attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
		attributes.put("country", getCountry());
		attributes.put("forecastType", getForecastType());
		attributes.put("totalDemandUnits", getTotalDemandUnits());
		attributes.put("brandId", getBrandId());
		attributes.put("isForecast", getIsForecast());
		attributes.put("totalDemandAmount", getTotalDemandAmount());
		attributes.put("uncapturedUnits", getUncapturedUnits());
		attributes.put("grossPrice", getGrossPrice());
		attributes.put("grossAmount", getGrossAmount());
		attributes.put("batchId", getBatchId());
		attributes.put("adjustedDemandForecastId", getAdjustedDemandForecastId());
		attributes.put("itemName", getItemName());
		attributes.put("netSalesPrice", getNetSalesPrice());
		attributes.put("netSalesAmount", getNetSalesAmount());
		attributes.put("segment", getSegment());
		attributes.put("forecastName", getForecastName());
		attributes.put("marketSizeUnits", getMarketSizeUnits());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		Double grossUnits = (Double)attributes.get("grossUnits");

		if (grossUnits != null) {
			setGrossUnits(grossUnits);
		}

		String businessUnitNo = (String)attributes.get("businessUnitNo");

		if (businessUnitNo != null) {
			setBusinessUnitNo(businessUnitNo);
		}

		String forecastYear = (String)attributes.get("forecastYear");

		if (forecastYear != null) {
			setForecastYear(forecastYear);
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

		Integer marketShareRatio = (Integer)attributes.get("marketShareRatio");

		if (marketShareRatio != null) {
			setMarketShareRatio(marketShareRatio);
		}

		String businessUnitName = (String)attributes.get("businessUnitName");

		if (businessUnitName != null) {
			setBusinessUnitName(businessUnitName);
		}

		Double marketShareUnits = (Double)attributes.get("marketShareUnits");

		if (marketShareUnits != null) {
			setMarketShareUnits(marketShareUnits);
		}

		String forecastMonth = (String)attributes.get("forecastMonth");

		if (forecastMonth != null) {
			setForecastMonth(forecastMonth);
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

		Double totalDemandUnits = (Double)attributes.get("totalDemandUnits");

		if (totalDemandUnits != null) {
			setTotalDemandUnits(totalDemandUnits);
		}

		String brandId = (String)attributes.get("brandId");

		if (brandId != null) {
			setBrandId(brandId);
		}

		String isForecast = (String)attributes.get("isForecast");

		if (isForecast != null) {
			setIsForecast(isForecast);
		}

		Double totalDemandAmount = (Double)attributes.get("totalDemandAmount");

		if (totalDemandAmount != null) {
			setTotalDemandAmount(totalDemandAmount);
		}

		Double uncapturedUnits = (Double)attributes.get("uncapturedUnits");

		if (uncapturedUnits != null) {
			setUncapturedUnits(uncapturedUnits);
		}

		Double grossPrice = (Double)attributes.get("grossPrice");

		if (grossPrice != null) {
			setGrossPrice(grossPrice);
		}

		Double grossAmount = (Double)attributes.get("grossAmount");

		if (grossAmount != null) {
			setGrossAmount(grossAmount);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		Integer adjustedDemandForecastId = (Integer)attributes.get(
				"adjustedDemandForecastId");

		if (adjustedDemandForecastId != null) {
			setAdjustedDemandForecastId(adjustedDemandForecastId);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
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
		return new VwAdjustDemandForecastActWrapper((VwAdjustDemandForecastAct)_vwAdjustDemandForecastAct.clone());
	}

	@Override
	public int compareTo(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		return _vwAdjustDemandForecastAct.compareTo(vwAdjustDemandForecastAct);
	}

	/**
	* Returns the adjusted demand forecast ID of this vw adjust demand forecast act.
	*
	* @return the adjusted demand forecast ID of this vw adjust demand forecast act
	*/
	@Override
	public int getAdjustedDemandForecastId() {
		return _vwAdjustDemandForecastAct.getAdjustedDemandForecastId();
	}

	/**
	* Returns the batch ID of this vw adjust demand forecast act.
	*
	* @return the batch ID of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwAdjustDemandForecastAct.getBatchId();
	}

	/**
	* Returns the brand ID of this vw adjust demand forecast act.
	*
	* @return the brand ID of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getBrandId() {
		return _vwAdjustDemandForecastAct.getBrandId();
	}

	/**
	* Returns the brand name of this vw adjust demand forecast act.
	*
	* @return the brand name of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getBrandName() {
		return _vwAdjustDemandForecastAct.getBrandName();
	}

	/**
	* Returns the business unit name of this vw adjust demand forecast act.
	*
	* @return the business unit name of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getBusinessUnitName() {
		return _vwAdjustDemandForecastAct.getBusinessUnitName();
	}

	/**
	* Returns the business unit no of this vw adjust demand forecast act.
	*
	* @return the business unit no of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getBusinessUnitNo() {
		return _vwAdjustDemandForecastAct.getBusinessUnitNo();
	}

	/**
	* Returns the country of this vw adjust demand forecast act.
	*
	* @return the country of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwAdjustDemandForecastAct.getCountry();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwAdjustDemandForecastAct.getExpandoBridge();
	}

	/**
	* Returns the forecast month of this vw adjust demand forecast act.
	*
	* @return the forecast month of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _vwAdjustDemandForecastAct.getForecastMonth();
	}

	/**
	* Returns the forecast name of this vw adjust demand forecast act.
	*
	* @return the forecast name of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getForecastName() {
		return _vwAdjustDemandForecastAct.getForecastName();
	}

	/**
	* Returns the forecast type of this vw adjust demand forecast act.
	*
	* @return the forecast type of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getForecastType() {
		return _vwAdjustDemandForecastAct.getForecastType();
	}

	/**
	* Returns the forecast ver of this vw adjust demand forecast act.
	*
	* @return the forecast ver of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _vwAdjustDemandForecastAct.getForecastVer();
	}

	/**
	* Returns the forecast year of this vw adjust demand forecast act.
	*
	* @return the forecast year of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _vwAdjustDemandForecastAct.getForecastYear();
	}

	/**
	* Returns the gross amount of this vw adjust demand forecast act.
	*
	* @return the gross amount of this vw adjust demand forecast act
	*/
	@Override
	public double getGrossAmount() {
		return _vwAdjustDemandForecastAct.getGrossAmount();
	}

	/**
	* Returns the gross price of this vw adjust demand forecast act.
	*
	* @return the gross price of this vw adjust demand forecast act
	*/
	@Override
	public double getGrossPrice() {
		return _vwAdjustDemandForecastAct.getGrossPrice();
	}

	/**
	* Returns the gross units of this vw adjust demand forecast act.
	*
	* @return the gross units of this vw adjust demand forecast act
	*/
	@Override
	public double getGrossUnits() {
		return _vwAdjustDemandForecastAct.getGrossUnits();
	}

	/**
	* Returns the inventory unit change of this vw adjust demand forecast act.
	*
	* @return the inventory unit change of this vw adjust demand forecast act
	*/
	@Override
	public double getInventoryUnitChange() {
		return _vwAdjustDemandForecastAct.getInventoryUnitChange();
	}

	/**
	* Returns the is forecast of this vw adjust demand forecast act.
	*
	* @return the is forecast of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getIsForecast() {
		return _vwAdjustDemandForecastAct.getIsForecast();
	}

	/**
	* Returns the item ID of this vw adjust demand forecast act.
	*
	* @return the item ID of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwAdjustDemandForecastAct.getItemId();
	}

	/**
	* Returns the item name of this vw adjust demand forecast act.
	*
	* @return the item name of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwAdjustDemandForecastAct.getItemName();
	}

	/**
	* Returns the market share ratio of this vw adjust demand forecast act.
	*
	* @return the market share ratio of this vw adjust demand forecast act
	*/
	@Override
	public int getMarketShareRatio() {
		return _vwAdjustDemandForecastAct.getMarketShareRatio();
	}

	/**
	* Returns the market share units of this vw adjust demand forecast act.
	*
	* @return the market share units of this vw adjust demand forecast act
	*/
	@Override
	public double getMarketShareUnits() {
		return _vwAdjustDemandForecastAct.getMarketShareUnits();
	}

	/**
	* Returns the market size units of this vw adjust demand forecast act.
	*
	* @return the market size units of this vw adjust demand forecast act
	*/
	@Override
	public double getMarketSizeUnits() {
		return _vwAdjustDemandForecastAct.getMarketSizeUnits();
	}

	/**
	* Returns the net sales amount of this vw adjust demand forecast act.
	*
	* @return the net sales amount of this vw adjust demand forecast act
	*/
	@Override
	public double getNetSalesAmount() {
		return _vwAdjustDemandForecastAct.getNetSalesAmount();
	}

	/**
	* Returns the net sales price of this vw adjust demand forecast act.
	*
	* @return the net sales price of this vw adjust demand forecast act
	*/
	@Override
	public double getNetSalesPrice() {
		return _vwAdjustDemandForecastAct.getNetSalesPrice();
	}

	/**
	* Returns the organization key of this vw adjust demand forecast act.
	*
	* @return the organization key of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _vwAdjustDemandForecastAct.getOrganizationKey();
	}

	/**
	* Returns the primary key of this vw adjust demand forecast act.
	*
	* @return the primary key of this vw adjust demand forecast act
	*/
	@Override
	public int getPrimaryKey() {
		return _vwAdjustDemandForecastAct.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwAdjustDemandForecastAct.getPrimaryKeyObj();
	}

	/**
	* Returns the segment of this vw adjust demand forecast act.
	*
	* @return the segment of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getSegment() {
		return _vwAdjustDemandForecastAct.getSegment();
	}

	/**
	* Returns the source of this vw adjust demand forecast act.
	*
	* @return the source of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getSource() {
		return _vwAdjustDemandForecastAct.getSource();
	}

	/**
	* Returns the total demand amount of this vw adjust demand forecast act.
	*
	* @return the total demand amount of this vw adjust demand forecast act
	*/
	@Override
	public double getTotalDemandAmount() {
		return _vwAdjustDemandForecastAct.getTotalDemandAmount();
	}

	/**
	* Returns the total demand units of this vw adjust demand forecast act.
	*
	* @return the total demand units of this vw adjust demand forecast act
	*/
	@Override
	public double getTotalDemandUnits() {
		return _vwAdjustDemandForecastAct.getTotalDemandUnits();
	}

	/**
	* Returns the uncaptured units of this vw adjust demand forecast act.
	*
	* @return the uncaptured units of this vw adjust demand forecast act
	*/
	@Override
	public double getUncapturedUnits() {
		return _vwAdjustDemandForecastAct.getUncapturedUnits();
	}

	/**
	* Returns the uncaptured units ratio of this vw adjust demand forecast act.
	*
	* @return the uncaptured units ratio of this vw adjust demand forecast act
	*/
	@Override
	public java.lang.String getUncapturedUnitsRatio() {
		return _vwAdjustDemandForecastAct.getUncapturedUnitsRatio();
	}

	@Override
	public int hashCode() {
		return _vwAdjustDemandForecastAct.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwAdjustDemandForecastAct.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwAdjustDemandForecastAct.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwAdjustDemandForecastAct.isNew();
	}

	@Override
	public void persist() {
		_vwAdjustDemandForecastAct.persist();
	}

	/**
	* Sets the adjusted demand forecast ID of this vw adjust demand forecast act.
	*
	* @param adjustedDemandForecastId the adjusted demand forecast ID of this vw adjust demand forecast act
	*/
	@Override
	public void setAdjustedDemandForecastId(int adjustedDemandForecastId) {
		_vwAdjustDemandForecastAct.setAdjustedDemandForecastId(adjustedDemandForecastId);
	}

	/**
	* Sets the batch ID of this vw adjust demand forecast act.
	*
	* @param batchId the batch ID of this vw adjust demand forecast act
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwAdjustDemandForecastAct.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this vw adjust demand forecast act.
	*
	* @param brandId the brand ID of this vw adjust demand forecast act
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_vwAdjustDemandForecastAct.setBrandId(brandId);
	}

	/**
	* Sets the brand name of this vw adjust demand forecast act.
	*
	* @param brandName the brand name of this vw adjust demand forecast act
	*/
	@Override
	public void setBrandName(java.lang.String brandName) {
		_vwAdjustDemandForecastAct.setBrandName(brandName);
	}

	/**
	* Sets the business unit name of this vw adjust demand forecast act.
	*
	* @param businessUnitName the business unit name of this vw adjust demand forecast act
	*/
	@Override
	public void setBusinessUnitName(java.lang.String businessUnitName) {
		_vwAdjustDemandForecastAct.setBusinessUnitName(businessUnitName);
	}

	/**
	* Sets the business unit no of this vw adjust demand forecast act.
	*
	* @param businessUnitNo the business unit no of this vw adjust demand forecast act
	*/
	@Override
	public void setBusinessUnitNo(java.lang.String businessUnitNo) {
		_vwAdjustDemandForecastAct.setBusinessUnitNo(businessUnitNo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwAdjustDemandForecastAct.setCachedModel(cachedModel);
	}

	/**
	* Sets the country of this vw adjust demand forecast act.
	*
	* @param country the country of this vw adjust demand forecast act
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwAdjustDemandForecastAct.setCountry(country);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwAdjustDemandForecastAct.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwAdjustDemandForecastAct.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwAdjustDemandForecastAct.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast month of this vw adjust demand forecast act.
	*
	* @param forecastMonth the forecast month of this vw adjust demand forecast act
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_vwAdjustDemandForecastAct.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast name of this vw adjust demand forecast act.
	*
	* @param forecastName the forecast name of this vw adjust demand forecast act
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_vwAdjustDemandForecastAct.setForecastName(forecastName);
	}

	/**
	* Sets the forecast type of this vw adjust demand forecast act.
	*
	* @param forecastType the forecast type of this vw adjust demand forecast act
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_vwAdjustDemandForecastAct.setForecastType(forecastType);
	}

	/**
	* Sets the forecast ver of this vw adjust demand forecast act.
	*
	* @param forecastVer the forecast ver of this vw adjust demand forecast act
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_vwAdjustDemandForecastAct.setForecastVer(forecastVer);
	}

	/**
	* Sets the forecast year of this vw adjust demand forecast act.
	*
	* @param forecastYear the forecast year of this vw adjust demand forecast act
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_vwAdjustDemandForecastAct.setForecastYear(forecastYear);
	}

	/**
	* Sets the gross amount of this vw adjust demand forecast act.
	*
	* @param grossAmount the gross amount of this vw adjust demand forecast act
	*/
	@Override
	public void setGrossAmount(double grossAmount) {
		_vwAdjustDemandForecastAct.setGrossAmount(grossAmount);
	}

	/**
	* Sets the gross price of this vw adjust demand forecast act.
	*
	* @param grossPrice the gross price of this vw adjust demand forecast act
	*/
	@Override
	public void setGrossPrice(double grossPrice) {
		_vwAdjustDemandForecastAct.setGrossPrice(grossPrice);
	}

	/**
	* Sets the gross units of this vw adjust demand forecast act.
	*
	* @param grossUnits the gross units of this vw adjust demand forecast act
	*/
	@Override
	public void setGrossUnits(double grossUnits) {
		_vwAdjustDemandForecastAct.setGrossUnits(grossUnits);
	}

	/**
	* Sets the inventory unit change of this vw adjust demand forecast act.
	*
	* @param inventoryUnitChange the inventory unit change of this vw adjust demand forecast act
	*/
	@Override
	public void setInventoryUnitChange(double inventoryUnitChange) {
		_vwAdjustDemandForecastAct.setInventoryUnitChange(inventoryUnitChange);
	}

	/**
	* Sets the is forecast of this vw adjust demand forecast act.
	*
	* @param isForecast the is forecast of this vw adjust demand forecast act
	*/
	@Override
	public void setIsForecast(java.lang.String isForecast) {
		_vwAdjustDemandForecastAct.setIsForecast(isForecast);
	}

	/**
	* Sets the item ID of this vw adjust demand forecast act.
	*
	* @param itemId the item ID of this vw adjust demand forecast act
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwAdjustDemandForecastAct.setItemId(itemId);
	}

	/**
	* Sets the item name of this vw adjust demand forecast act.
	*
	* @param itemName the item name of this vw adjust demand forecast act
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwAdjustDemandForecastAct.setItemName(itemName);
	}

	/**
	* Sets the market share ratio of this vw adjust demand forecast act.
	*
	* @param marketShareRatio the market share ratio of this vw adjust demand forecast act
	*/
	@Override
	public void setMarketShareRatio(int marketShareRatio) {
		_vwAdjustDemandForecastAct.setMarketShareRatio(marketShareRatio);
	}

	/**
	* Sets the market share units of this vw adjust demand forecast act.
	*
	* @param marketShareUnits the market share units of this vw adjust demand forecast act
	*/
	@Override
	public void setMarketShareUnits(double marketShareUnits) {
		_vwAdjustDemandForecastAct.setMarketShareUnits(marketShareUnits);
	}

	/**
	* Sets the market size units of this vw adjust demand forecast act.
	*
	* @param marketSizeUnits the market size units of this vw adjust demand forecast act
	*/
	@Override
	public void setMarketSizeUnits(double marketSizeUnits) {
		_vwAdjustDemandForecastAct.setMarketSizeUnits(marketSizeUnits);
	}

	/**
	* Sets the net sales amount of this vw adjust demand forecast act.
	*
	* @param netSalesAmount the net sales amount of this vw adjust demand forecast act
	*/
	@Override
	public void setNetSalesAmount(double netSalesAmount) {
		_vwAdjustDemandForecastAct.setNetSalesAmount(netSalesAmount);
	}

	/**
	* Sets the net sales price of this vw adjust demand forecast act.
	*
	* @param netSalesPrice the net sales price of this vw adjust demand forecast act
	*/
	@Override
	public void setNetSalesPrice(double netSalesPrice) {
		_vwAdjustDemandForecastAct.setNetSalesPrice(netSalesPrice);
	}

	@Override
	public void setNew(boolean n) {
		_vwAdjustDemandForecastAct.setNew(n);
	}

	/**
	* Sets the organization key of this vw adjust demand forecast act.
	*
	* @param organizationKey the organization key of this vw adjust demand forecast act
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_vwAdjustDemandForecastAct.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this vw adjust demand forecast act.
	*
	* @param primaryKey the primary key of this vw adjust demand forecast act
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwAdjustDemandForecastAct.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwAdjustDemandForecastAct.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the segment of this vw adjust demand forecast act.
	*
	* @param segment the segment of this vw adjust demand forecast act
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_vwAdjustDemandForecastAct.setSegment(segment);
	}

	/**
	* Sets the source of this vw adjust demand forecast act.
	*
	* @param source the source of this vw adjust demand forecast act
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwAdjustDemandForecastAct.setSource(source);
	}

	/**
	* Sets the total demand amount of this vw adjust demand forecast act.
	*
	* @param totalDemandAmount the total demand amount of this vw adjust demand forecast act
	*/
	@Override
	public void setTotalDemandAmount(double totalDemandAmount) {
		_vwAdjustDemandForecastAct.setTotalDemandAmount(totalDemandAmount);
	}

	/**
	* Sets the total demand units of this vw adjust demand forecast act.
	*
	* @param totalDemandUnits the total demand units of this vw adjust demand forecast act
	*/
	@Override
	public void setTotalDemandUnits(double totalDemandUnits) {
		_vwAdjustDemandForecastAct.setTotalDemandUnits(totalDemandUnits);
	}

	/**
	* Sets the uncaptured units of this vw adjust demand forecast act.
	*
	* @param uncapturedUnits the uncaptured units of this vw adjust demand forecast act
	*/
	@Override
	public void setUncapturedUnits(double uncapturedUnits) {
		_vwAdjustDemandForecastAct.setUncapturedUnits(uncapturedUnits);
	}

	/**
	* Sets the uncaptured units ratio of this vw adjust demand forecast act.
	*
	* @param uncapturedUnitsRatio the uncaptured units ratio of this vw adjust demand forecast act
	*/
	@Override
	public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
		_vwAdjustDemandForecastAct.setUncapturedUnitsRatio(uncapturedUnitsRatio);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwAdjustDemandForecastAct> toCacheModel() {
		return _vwAdjustDemandForecastAct.toCacheModel();
	}

	@Override
	public VwAdjustDemandForecastAct toEscapedModel() {
		return new VwAdjustDemandForecastActWrapper(_vwAdjustDemandForecastAct.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwAdjustDemandForecastAct.toString();
	}

	@Override
	public VwAdjustDemandForecastAct toUnescapedModel() {
		return new VwAdjustDemandForecastActWrapper(_vwAdjustDemandForecastAct.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwAdjustDemandForecastAct.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwAdjustDemandForecastActWrapper)) {
			return false;
		}

		VwAdjustDemandForecastActWrapper vwAdjustDemandForecastActWrapper = (VwAdjustDemandForecastActWrapper)obj;

		if (Objects.equals(_vwAdjustDemandForecastAct,
					vwAdjustDemandForecastActWrapper._vwAdjustDemandForecastAct)) {
			return true;
		}

		return false;
	}

	@Override
	public VwAdjustDemandForecastAct getWrappedModel() {
		return _vwAdjustDemandForecastAct;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwAdjustDemandForecastAct.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwAdjustDemandForecastAct.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwAdjustDemandForecastAct.resetOriginalValues();
	}

	private final VwAdjustDemandForecastAct _vwAdjustDemandForecastAct;
}