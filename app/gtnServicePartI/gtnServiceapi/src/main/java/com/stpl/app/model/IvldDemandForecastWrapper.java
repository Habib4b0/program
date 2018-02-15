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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link IvldDemandForecast}.
 * </p>
 *
 * @author
 * @see IvldDemandForecast
 * @generated
 */
@ProviderType
public class IvldDemandForecastWrapper implements IvldDemandForecast,
	ModelWrapper<IvldDemandForecast> {
	public IvldDemandForecastWrapper(IvldDemandForecast ivldDemandForecast) {
		_ivldDemandForecast = ivldDemandForecast;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldDemandForecast.class;
	}

	@Override
	public String getModelClassName() {
		return IvldDemandForecast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("forecastYear", getForecastYear());
		attributes.put("grossUnits", getGrossUnits());
		attributes.put("totalDemandUnits", getTotalDemandUnits());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("ivldDemandForecastSid", getIvldDemandForecastSid());
		attributes.put("source", getSource());
		attributes.put("marketShareRatio", getMarketShareRatio());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("marketShareUnits", getMarketShareUnits());
		attributes.put("inventoryUnitChange", getInventoryUnitChange());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("uncapturedUnitsRatio", getUncapturedUnitsRatio());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("country", getCountry());
		attributes.put("forecastType", getForecastType());
		attributes.put("brandId", getBrandId());
		attributes.put("demandForecastInterfaceId",
			getDemandForecastInterfaceId());
		attributes.put("uncapturedUnits", getUncapturedUnits());
		attributes.put("grossPrice", getGrossPrice());
		attributes.put("grossAmount", getGrossAmount());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("forecastMonth", getForecastMonth());
		attributes.put("errorField", getErrorField());
		attributes.put("netSalesPrice", getNetSalesPrice());
		attributes.put("netSalesAmount", getNetSalesAmount());
		attributes.put("segment", getSegment());
		attributes.put("totalDemandAmount", getTotalDemandAmount());
		attributes.put("forecastName", getForecastName());
		attributes.put("marketSizeUnits", getMarketSizeUnits());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String forecastYear = (String)attributes.get("forecastYear");

		if (forecastYear != null) {
			setForecastYear(forecastYear);
		}

		String grossUnits = (String)attributes.get("grossUnits");

		if (grossUnits != null) {
			setGrossUnits(grossUnits);
		}

		String totalDemandUnits = (String)attributes.get("totalDemandUnits");

		if (totalDemandUnits != null) {
			setTotalDemandUnits(totalDemandUnits);
		}

		String itemId = (String)attributes.get("itemId");

		if (itemId != null) {
			setItemId(itemId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
		}

		Integer ivldDemandForecastSid = (Integer)attributes.get(
				"ivldDemandForecastSid");

		if (ivldDemandForecastSid != null) {
			setIvldDemandForecastSid(ivldDemandForecastSid);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		String marketShareRatio = (String)attributes.get("marketShareRatio");

		if (marketShareRatio != null) {
			setMarketShareRatio(marketShareRatio);
		}

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		String intfInsertedDate = (String)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String marketShareUnits = (String)attributes.get("marketShareUnits");

		if (marketShareUnits != null) {
			setMarketShareUnits(marketShareUnits);
		}

		String inventoryUnitChange = (String)attributes.get(
				"inventoryUnitChange");

		if (inventoryUnitChange != null) {
			setInventoryUnitChange(inventoryUnitChange);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String uncapturedUnitsRatio = (String)attributes.get(
				"uncapturedUnitsRatio");

		if (uncapturedUnitsRatio != null) {
			setUncapturedUnitsRatio(uncapturedUnitsRatio);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
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

		String demandForecastInterfaceId = (String)attributes.get(
				"demandForecastInterfaceId");

		if (demandForecastInterfaceId != null) {
			setDemandForecastInterfaceId(demandForecastInterfaceId);
		}

		String uncapturedUnits = (String)attributes.get("uncapturedUnits");

		if (uncapturedUnits != null) {
			setUncapturedUnits(uncapturedUnits);
		}

		String grossPrice = (String)attributes.get("grossPrice");

		if (grossPrice != null) {
			setGrossPrice(grossPrice);
		}

		String grossAmount = (String)attributes.get("grossAmount");

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

		String forecastMonth = (String)attributes.get("forecastMonth");

		if (forecastMonth != null) {
			setForecastMonth(forecastMonth);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String netSalesPrice = (String)attributes.get("netSalesPrice");

		if (netSalesPrice != null) {
			setNetSalesPrice(netSalesPrice);
		}

		String netSalesAmount = (String)attributes.get("netSalesAmount");

		if (netSalesAmount != null) {
			setNetSalesAmount(netSalesAmount);
		}

		String segment = (String)attributes.get("segment");

		if (segment != null) {
			setSegment(segment);
		}

		String totalDemandAmount = (String)attributes.get("totalDemandAmount");

		if (totalDemandAmount != null) {
			setTotalDemandAmount(totalDemandAmount);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		String marketSizeUnits = (String)attributes.get("marketSizeUnits");

		if (marketSizeUnits != null) {
			setMarketSizeUnits(marketSizeUnits);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldDemandForecastWrapper((IvldDemandForecast)_ivldDemandForecast.clone());
	}

	@Override
	public int compareTo(IvldDemandForecast ivldDemandForecast) {
		return _ivldDemandForecast.compareTo(ivldDemandForecast);
	}

	/**
	* Returns the add chg del indicator of this ivld demand forecast.
	*
	* @return the add chg del indicator of this ivld demand forecast
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldDemandForecast.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld demand forecast.
	*
	* @return the batch ID of this ivld demand forecast
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldDemandForecast.getBatchId();
	}

	/**
	* Returns the brand ID of this ivld demand forecast.
	*
	* @return the brand ID of this ivld demand forecast
	*/
	@Override
	public java.lang.String getBrandId() {
		return _ivldDemandForecast.getBrandId();
	}

	/**
	* Returns the check record of this ivld demand forecast.
	*
	* @return the check record of this ivld demand forecast
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldDemandForecast.getCheckRecord();
	}

	/**
	* Returns the country of this ivld demand forecast.
	*
	* @return the country of this ivld demand forecast
	*/
	@Override
	public java.lang.String getCountry() {
		return _ivldDemandForecast.getCountry();
	}

	/**
	* Returns the created by of this ivld demand forecast.
	*
	* @return the created by of this ivld demand forecast
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldDemandForecast.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld demand forecast.
	*
	* @return the created date of this ivld demand forecast
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldDemandForecast.getCreatedDate();
	}

	/**
	* Returns the demand forecast interface ID of this ivld demand forecast.
	*
	* @return the demand forecast interface ID of this ivld demand forecast
	*/
	@Override
	public java.lang.String getDemandForecastInterfaceId() {
		return _ivldDemandForecast.getDemandForecastInterfaceId();
	}

	/**
	* Returns the error code of this ivld demand forecast.
	*
	* @return the error code of this ivld demand forecast
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldDemandForecast.getErrorCode();
	}

	/**
	* Returns the error field of this ivld demand forecast.
	*
	* @return the error field of this ivld demand forecast
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldDemandForecast.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldDemandForecast.getExpandoBridge();
	}

	/**
	* Returns the forecast month of this ivld demand forecast.
	*
	* @return the forecast month of this ivld demand forecast
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _ivldDemandForecast.getForecastMonth();
	}

	/**
	* Returns the forecast name of this ivld demand forecast.
	*
	* @return the forecast name of this ivld demand forecast
	*/
	@Override
	public java.lang.String getForecastName() {
		return _ivldDemandForecast.getForecastName();
	}

	/**
	* Returns the forecast type of this ivld demand forecast.
	*
	* @return the forecast type of this ivld demand forecast
	*/
	@Override
	public java.lang.String getForecastType() {
		return _ivldDemandForecast.getForecastType();
	}

	/**
	* Returns the forecast ver of this ivld demand forecast.
	*
	* @return the forecast ver of this ivld demand forecast
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _ivldDemandForecast.getForecastVer();
	}

	/**
	* Returns the forecast year of this ivld demand forecast.
	*
	* @return the forecast year of this ivld demand forecast
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _ivldDemandForecast.getForecastYear();
	}

	/**
	* Returns the gross amount of this ivld demand forecast.
	*
	* @return the gross amount of this ivld demand forecast
	*/
	@Override
	public java.lang.String getGrossAmount() {
		return _ivldDemandForecast.getGrossAmount();
	}

	/**
	* Returns the gross price of this ivld demand forecast.
	*
	* @return the gross price of this ivld demand forecast
	*/
	@Override
	public java.lang.String getGrossPrice() {
		return _ivldDemandForecast.getGrossPrice();
	}

	/**
	* Returns the gross units of this ivld demand forecast.
	*
	* @return the gross units of this ivld demand forecast
	*/
	@Override
	public java.lang.String getGrossUnits() {
		return _ivldDemandForecast.getGrossUnits();
	}

	/**
	* Returns the intf inserted date of this ivld demand forecast.
	*
	* @return the intf inserted date of this ivld demand forecast
	*/
	@Override
	public java.lang.String getIntfInsertedDate() {
		return _ivldDemandForecast.getIntfInsertedDate();
	}

	/**
	* Returns the inventory unit change of this ivld demand forecast.
	*
	* @return the inventory unit change of this ivld demand forecast
	*/
	@Override
	public java.lang.String getInventoryUnitChange() {
		return _ivldDemandForecast.getInventoryUnitChange();
	}

	/**
	* Returns the item ID of this ivld demand forecast.
	*
	* @return the item ID of this ivld demand forecast
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldDemandForecast.getItemId();
	}

	/**
	* Returns the item identifier of this ivld demand forecast.
	*
	* @return the item identifier of this ivld demand forecast
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ivldDemandForecast.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this ivld demand forecast.
	*
	* @return the item identifier code qualifier of this ivld demand forecast
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _ivldDemandForecast.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the ivld demand forecast sid of this ivld demand forecast.
	*
	* @return the ivld demand forecast sid of this ivld demand forecast
	*/
	@Override
	public int getIvldDemandForecastSid() {
		return _ivldDemandForecast.getIvldDemandForecastSid();
	}

	/**
	* Returns the market share ratio of this ivld demand forecast.
	*
	* @return the market share ratio of this ivld demand forecast
	*/
	@Override
	public java.lang.String getMarketShareRatio() {
		return _ivldDemandForecast.getMarketShareRatio();
	}

	/**
	* Returns the market share units of this ivld demand forecast.
	*
	* @return the market share units of this ivld demand forecast
	*/
	@Override
	public java.lang.String getMarketShareUnits() {
		return _ivldDemandForecast.getMarketShareUnits();
	}

	/**
	* Returns the market size units of this ivld demand forecast.
	*
	* @return the market size units of this ivld demand forecast
	*/
	@Override
	public java.lang.String getMarketSizeUnits() {
		return _ivldDemandForecast.getMarketSizeUnits();
	}

	/**
	* Returns the modified by of this ivld demand forecast.
	*
	* @return the modified by of this ivld demand forecast
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldDemandForecast.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld demand forecast.
	*
	* @return the modified date of this ivld demand forecast
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldDemandForecast.getModifiedDate();
	}

	/**
	* Returns the net sales amount of this ivld demand forecast.
	*
	* @return the net sales amount of this ivld demand forecast
	*/
	@Override
	public java.lang.String getNetSalesAmount() {
		return _ivldDemandForecast.getNetSalesAmount();
	}

	/**
	* Returns the net sales price of this ivld demand forecast.
	*
	* @return the net sales price of this ivld demand forecast
	*/
	@Override
	public java.lang.String getNetSalesPrice() {
		return _ivldDemandForecast.getNetSalesPrice();
	}

	/**
	* Returns the organization key of this ivld demand forecast.
	*
	* @return the organization key of this ivld demand forecast
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldDemandForecast.getOrganizationKey();
	}

	/**
	* Returns the primary key of this ivld demand forecast.
	*
	* @return the primary key of this ivld demand forecast
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldDemandForecast.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldDemandForecast.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld demand forecast.
	*
	* @return the reason for failure of this ivld demand forecast
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldDemandForecast.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld demand forecast.
	*
	* @return the reprocessed flag of this ivld demand forecast
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldDemandForecast.getReprocessedFlag();
	}

	/**
	* Returns the segment of this ivld demand forecast.
	*
	* @return the segment of this ivld demand forecast
	*/
	@Override
	public java.lang.String getSegment() {
		return _ivldDemandForecast.getSegment();
	}

	/**
	* Returns the source of this ivld demand forecast.
	*
	* @return the source of this ivld demand forecast
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldDemandForecast.getSource();
	}

	/**
	* Returns the total demand amount of this ivld demand forecast.
	*
	* @return the total demand amount of this ivld demand forecast
	*/
	@Override
	public java.lang.String getTotalDemandAmount() {
		return _ivldDemandForecast.getTotalDemandAmount();
	}

	/**
	* Returns the total demand units of this ivld demand forecast.
	*
	* @return the total demand units of this ivld demand forecast
	*/
	@Override
	public java.lang.String getTotalDemandUnits() {
		return _ivldDemandForecast.getTotalDemandUnits();
	}

	/**
	* Returns the uncaptured units of this ivld demand forecast.
	*
	* @return the uncaptured units of this ivld demand forecast
	*/
	@Override
	public java.lang.String getUncapturedUnits() {
		return _ivldDemandForecast.getUncapturedUnits();
	}

	/**
	* Returns the uncaptured units ratio of this ivld demand forecast.
	*
	* @return the uncaptured units ratio of this ivld demand forecast
	*/
	@Override
	public java.lang.String getUncapturedUnitsRatio() {
		return _ivldDemandForecast.getUncapturedUnitsRatio();
	}

	@Override
	public int hashCode() {
		return _ivldDemandForecast.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldDemandForecast.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld demand forecast is check record.
	*
	* @return <code>true</code> if this ivld demand forecast is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldDemandForecast.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldDemandForecast.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldDemandForecast.isNew();
	}

	@Override
	public void persist() {
		_ivldDemandForecast.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld demand forecast.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld demand forecast
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldDemandForecast.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld demand forecast.
	*
	* @param batchId the batch ID of this ivld demand forecast
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldDemandForecast.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this ivld demand forecast.
	*
	* @param brandId the brand ID of this ivld demand forecast
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_ivldDemandForecast.setBrandId(brandId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldDemandForecast.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld demand forecast is check record.
	*
	* @param checkRecord the check record of this ivld demand forecast
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldDemandForecast.setCheckRecord(checkRecord);
	}

	/**
	* Sets the country of this ivld demand forecast.
	*
	* @param country the country of this ivld demand forecast
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_ivldDemandForecast.setCountry(country);
	}

	/**
	* Sets the created by of this ivld demand forecast.
	*
	* @param createdBy the created by of this ivld demand forecast
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldDemandForecast.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld demand forecast.
	*
	* @param createdDate the created date of this ivld demand forecast
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldDemandForecast.setCreatedDate(createdDate);
	}

	/**
	* Sets the demand forecast interface ID of this ivld demand forecast.
	*
	* @param demandForecastInterfaceId the demand forecast interface ID of this ivld demand forecast
	*/
	@Override
	public void setDemandForecastInterfaceId(
		java.lang.String demandForecastInterfaceId) {
		_ivldDemandForecast.setDemandForecastInterfaceId(demandForecastInterfaceId);
	}

	/**
	* Sets the error code of this ivld demand forecast.
	*
	* @param errorCode the error code of this ivld demand forecast
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldDemandForecast.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld demand forecast.
	*
	* @param errorField the error field of this ivld demand forecast
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldDemandForecast.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldDemandForecast.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldDemandForecast.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldDemandForecast.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast month of this ivld demand forecast.
	*
	* @param forecastMonth the forecast month of this ivld demand forecast
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_ivldDemandForecast.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast name of this ivld demand forecast.
	*
	* @param forecastName the forecast name of this ivld demand forecast
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_ivldDemandForecast.setForecastName(forecastName);
	}

	/**
	* Sets the forecast type of this ivld demand forecast.
	*
	* @param forecastType the forecast type of this ivld demand forecast
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_ivldDemandForecast.setForecastType(forecastType);
	}

	/**
	* Sets the forecast ver of this ivld demand forecast.
	*
	* @param forecastVer the forecast ver of this ivld demand forecast
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_ivldDemandForecast.setForecastVer(forecastVer);
	}

	/**
	* Sets the forecast year of this ivld demand forecast.
	*
	* @param forecastYear the forecast year of this ivld demand forecast
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_ivldDemandForecast.setForecastYear(forecastYear);
	}

	/**
	* Sets the gross amount of this ivld demand forecast.
	*
	* @param grossAmount the gross amount of this ivld demand forecast
	*/
	@Override
	public void setGrossAmount(java.lang.String grossAmount) {
		_ivldDemandForecast.setGrossAmount(grossAmount);
	}

	/**
	* Sets the gross price of this ivld demand forecast.
	*
	* @param grossPrice the gross price of this ivld demand forecast
	*/
	@Override
	public void setGrossPrice(java.lang.String grossPrice) {
		_ivldDemandForecast.setGrossPrice(grossPrice);
	}

	/**
	* Sets the gross units of this ivld demand forecast.
	*
	* @param grossUnits the gross units of this ivld demand forecast
	*/
	@Override
	public void setGrossUnits(java.lang.String grossUnits) {
		_ivldDemandForecast.setGrossUnits(grossUnits);
	}

	/**
	* Sets the intf inserted date of this ivld demand forecast.
	*
	* @param intfInsertedDate the intf inserted date of this ivld demand forecast
	*/
	@Override
	public void setIntfInsertedDate(java.lang.String intfInsertedDate) {
		_ivldDemandForecast.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the inventory unit change of this ivld demand forecast.
	*
	* @param inventoryUnitChange the inventory unit change of this ivld demand forecast
	*/
	@Override
	public void setInventoryUnitChange(java.lang.String inventoryUnitChange) {
		_ivldDemandForecast.setInventoryUnitChange(inventoryUnitChange);
	}

	/**
	* Sets the item ID of this ivld demand forecast.
	*
	* @param itemId the item ID of this ivld demand forecast
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldDemandForecast.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ivld demand forecast.
	*
	* @param itemIdentifier the item identifier of this ivld demand forecast
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ivldDemandForecast.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this ivld demand forecast.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld demand forecast
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_ivldDemandForecast.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the ivld demand forecast sid of this ivld demand forecast.
	*
	* @param ivldDemandForecastSid the ivld demand forecast sid of this ivld demand forecast
	*/
	@Override
	public void setIvldDemandForecastSid(int ivldDemandForecastSid) {
		_ivldDemandForecast.setIvldDemandForecastSid(ivldDemandForecastSid);
	}

	/**
	* Sets the market share ratio of this ivld demand forecast.
	*
	* @param marketShareRatio the market share ratio of this ivld demand forecast
	*/
	@Override
	public void setMarketShareRatio(java.lang.String marketShareRatio) {
		_ivldDemandForecast.setMarketShareRatio(marketShareRatio);
	}

	/**
	* Sets the market share units of this ivld demand forecast.
	*
	* @param marketShareUnits the market share units of this ivld demand forecast
	*/
	@Override
	public void setMarketShareUnits(java.lang.String marketShareUnits) {
		_ivldDemandForecast.setMarketShareUnits(marketShareUnits);
	}

	/**
	* Sets the market size units of this ivld demand forecast.
	*
	* @param marketSizeUnits the market size units of this ivld demand forecast
	*/
	@Override
	public void setMarketSizeUnits(java.lang.String marketSizeUnits) {
		_ivldDemandForecast.setMarketSizeUnits(marketSizeUnits);
	}

	/**
	* Sets the modified by of this ivld demand forecast.
	*
	* @param modifiedBy the modified by of this ivld demand forecast
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldDemandForecast.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld demand forecast.
	*
	* @param modifiedDate the modified date of this ivld demand forecast
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldDemandForecast.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales amount of this ivld demand forecast.
	*
	* @param netSalesAmount the net sales amount of this ivld demand forecast
	*/
	@Override
	public void setNetSalesAmount(java.lang.String netSalesAmount) {
		_ivldDemandForecast.setNetSalesAmount(netSalesAmount);
	}

	/**
	* Sets the net sales price of this ivld demand forecast.
	*
	* @param netSalesPrice the net sales price of this ivld demand forecast
	*/
	@Override
	public void setNetSalesPrice(java.lang.String netSalesPrice) {
		_ivldDemandForecast.setNetSalesPrice(netSalesPrice);
	}

	@Override
	public void setNew(boolean n) {
		_ivldDemandForecast.setNew(n);
	}

	/**
	* Sets the organization key of this ivld demand forecast.
	*
	* @param organizationKey the organization key of this ivld demand forecast
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldDemandForecast.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this ivld demand forecast.
	*
	* @param primaryKey the primary key of this ivld demand forecast
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldDemandForecast.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldDemandForecast.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld demand forecast.
	*
	* @param reasonForFailure the reason for failure of this ivld demand forecast
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldDemandForecast.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld demand forecast.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld demand forecast
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldDemandForecast.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the segment of this ivld demand forecast.
	*
	* @param segment the segment of this ivld demand forecast
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_ivldDemandForecast.setSegment(segment);
	}

	/**
	* Sets the source of this ivld demand forecast.
	*
	* @param source the source of this ivld demand forecast
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldDemandForecast.setSource(source);
	}

	/**
	* Sets the total demand amount of this ivld demand forecast.
	*
	* @param totalDemandAmount the total demand amount of this ivld demand forecast
	*/
	@Override
	public void setTotalDemandAmount(java.lang.String totalDemandAmount) {
		_ivldDemandForecast.setTotalDemandAmount(totalDemandAmount);
	}

	/**
	* Sets the total demand units of this ivld demand forecast.
	*
	* @param totalDemandUnits the total demand units of this ivld demand forecast
	*/
	@Override
	public void setTotalDemandUnits(java.lang.String totalDemandUnits) {
		_ivldDemandForecast.setTotalDemandUnits(totalDemandUnits);
	}

	/**
	* Sets the uncaptured units of this ivld demand forecast.
	*
	* @param uncapturedUnits the uncaptured units of this ivld demand forecast
	*/
	@Override
	public void setUncapturedUnits(java.lang.String uncapturedUnits) {
		_ivldDemandForecast.setUncapturedUnits(uncapturedUnits);
	}

	/**
	* Sets the uncaptured units ratio of this ivld demand forecast.
	*
	* @param uncapturedUnitsRatio the uncaptured units ratio of this ivld demand forecast
	*/
	@Override
	public void setUncapturedUnitsRatio(java.lang.String uncapturedUnitsRatio) {
		_ivldDemandForecast.setUncapturedUnitsRatio(uncapturedUnitsRatio);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldDemandForecast> toCacheModel() {
		return _ivldDemandForecast.toCacheModel();
	}

	@Override
	public IvldDemandForecast toEscapedModel() {
		return new IvldDemandForecastWrapper(_ivldDemandForecast.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldDemandForecast.toString();
	}

	@Override
	public IvldDemandForecast toUnescapedModel() {
		return new IvldDemandForecastWrapper(_ivldDemandForecast.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldDemandForecast.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldDemandForecastWrapper)) {
			return false;
		}

		IvldDemandForecastWrapper ivldDemandForecastWrapper = (IvldDemandForecastWrapper)obj;

		if (Objects.equals(_ivldDemandForecast,
					ivldDemandForecastWrapper._ivldDemandForecast)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldDemandForecast getWrappedModel() {
		return _ivldDemandForecast;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldDemandForecast.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldDemandForecast.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldDemandForecast.resetOriginalValues();
	}

	private final IvldDemandForecast _ivldDemandForecast;
}