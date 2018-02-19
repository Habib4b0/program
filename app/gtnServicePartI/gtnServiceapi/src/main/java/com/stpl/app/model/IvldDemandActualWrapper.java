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
 * This class is a wrapper for {@link IvldDemandActual}.
 * </p>
 *
 * @author
 * @see IvldDemandActual
 * @generated
 */
@ProviderType
public class IvldDemandActualWrapper implements IvldDemandActual,
	ModelWrapper<IvldDemandActual> {
	public IvldDemandActualWrapper(IvldDemandActual ivldDemandActual) {
		_ivldDemandActual = ivldDemandActual;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldDemandActual.class;
	}

	@Override
	public String getModelClassName() {
		return IvldDemandActual.class.getName();
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
		attributes.put("source", getSource());
		attributes.put("marketShareRatio", getMarketShareRatio());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("demandActualInterfaceId", getDemandActualInterfaceId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("marketShareUnits", getMarketShareUnits());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("country", getCountry());
		attributes.put("forecastType", getForecastType());
		attributes.put("brandId", getBrandId());
		attributes.put("grossPrice", getGrossPrice());
		attributes.put("ivldDemandActualSid", getIvldDemandActualSid());
		attributes.put("grossAmount", getGrossAmount());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("batchId", getBatchId());
		attributes.put("forecastMonth", getForecastMonth());
		attributes.put("errorField", getErrorField());
		attributes.put("netSalesPrice", getNetSalesPrice());
		attributes.put("netSalesAmount", getNetSalesAmount());
		attributes.put("segment", getSegment());
		attributes.put("totalDemandAmount", getTotalDemandAmount());
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

		String demandActualInterfaceId = (String)attributes.get(
				"demandActualInterfaceId");

		if (demandActualInterfaceId != null) {
			setDemandActualInterfaceId(demandActualInterfaceId);
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

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String marketShareUnits = (String)attributes.get("marketShareUnits");

		if (marketShareUnits != null) {
			setMarketShareUnits(marketShareUnits);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
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

		String grossPrice = (String)attributes.get("grossPrice");

		if (grossPrice != null) {
			setGrossPrice(grossPrice);
		}

		Integer ivldDemandActualSid = (Integer)attributes.get(
				"ivldDemandActualSid");

		if (ivldDemandActualSid != null) {
			setIvldDemandActualSid(ivldDemandActualSid);
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
		return new IvldDemandActualWrapper((IvldDemandActual)_ivldDemandActual.clone());
	}

	@Override
	public int compareTo(IvldDemandActual ivldDemandActual) {
		return _ivldDemandActual.compareTo(ivldDemandActual);
	}

	/**
	* Returns the add chg del indicator of this ivld demand actual.
	*
	* @return the add chg del indicator of this ivld demand actual
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldDemandActual.getAddChgDelIndicator();
	}

	/**
	* Returns the batch ID of this ivld demand actual.
	*
	* @return the batch ID of this ivld demand actual
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldDemandActual.getBatchId();
	}

	/**
	* Returns the brand ID of this ivld demand actual.
	*
	* @return the brand ID of this ivld demand actual
	*/
	@Override
	public java.lang.String getBrandId() {
		return _ivldDemandActual.getBrandId();
	}

	/**
	* Returns the check record of this ivld demand actual.
	*
	* @return the check record of this ivld demand actual
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldDemandActual.getCheckRecord();
	}

	/**
	* Returns the country of this ivld demand actual.
	*
	* @return the country of this ivld demand actual
	*/
	@Override
	public java.lang.String getCountry() {
		return _ivldDemandActual.getCountry();
	}

	/**
	* Returns the created by of this ivld demand actual.
	*
	* @return the created by of this ivld demand actual
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldDemandActual.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld demand actual.
	*
	* @return the created date of this ivld demand actual
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldDemandActual.getCreatedDate();
	}

	/**
	* Returns the demand actual interface ID of this ivld demand actual.
	*
	* @return the demand actual interface ID of this ivld demand actual
	*/
	@Override
	public java.lang.String getDemandActualInterfaceId() {
		return _ivldDemandActual.getDemandActualInterfaceId();
	}

	/**
	* Returns the error code of this ivld demand actual.
	*
	* @return the error code of this ivld demand actual
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldDemandActual.getErrorCode();
	}

	/**
	* Returns the error field of this ivld demand actual.
	*
	* @return the error field of this ivld demand actual
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldDemandActual.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldDemandActual.getExpandoBridge();
	}

	/**
	* Returns the forecast month of this ivld demand actual.
	*
	* @return the forecast month of this ivld demand actual
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _ivldDemandActual.getForecastMonth();
	}

	/**
	* Returns the forecast type of this ivld demand actual.
	*
	* @return the forecast type of this ivld demand actual
	*/
	@Override
	public java.lang.String getForecastType() {
		return _ivldDemandActual.getForecastType();
	}

	/**
	* Returns the forecast year of this ivld demand actual.
	*
	* @return the forecast year of this ivld demand actual
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _ivldDemandActual.getForecastYear();
	}

	/**
	* Returns the gross amount of this ivld demand actual.
	*
	* @return the gross amount of this ivld demand actual
	*/
	@Override
	public java.lang.String getGrossAmount() {
		return _ivldDemandActual.getGrossAmount();
	}

	/**
	* Returns the gross price of this ivld demand actual.
	*
	* @return the gross price of this ivld demand actual
	*/
	@Override
	public java.lang.String getGrossPrice() {
		return _ivldDemandActual.getGrossPrice();
	}

	/**
	* Returns the gross units of this ivld demand actual.
	*
	* @return the gross units of this ivld demand actual
	*/
	@Override
	public java.lang.String getGrossUnits() {
		return _ivldDemandActual.getGrossUnits();
	}

	/**
	* Returns the intf inserted date of this ivld demand actual.
	*
	* @return the intf inserted date of this ivld demand actual
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldDemandActual.getIntfInsertedDate();
	}

	/**
	* Returns the item ID of this ivld demand actual.
	*
	* @return the item ID of this ivld demand actual
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldDemandActual.getItemId();
	}

	/**
	* Returns the item identifier of this ivld demand actual.
	*
	* @return the item identifier of this ivld demand actual
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ivldDemandActual.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this ivld demand actual.
	*
	* @return the item identifier code qualifier of this ivld demand actual
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _ivldDemandActual.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the ivld demand actual sid of this ivld demand actual.
	*
	* @return the ivld demand actual sid of this ivld demand actual
	*/
	@Override
	public int getIvldDemandActualSid() {
		return _ivldDemandActual.getIvldDemandActualSid();
	}

	/**
	* Returns the market share ratio of this ivld demand actual.
	*
	* @return the market share ratio of this ivld demand actual
	*/
	@Override
	public java.lang.String getMarketShareRatio() {
		return _ivldDemandActual.getMarketShareRatio();
	}

	/**
	* Returns the market share units of this ivld demand actual.
	*
	* @return the market share units of this ivld demand actual
	*/
	@Override
	public java.lang.String getMarketShareUnits() {
		return _ivldDemandActual.getMarketShareUnits();
	}

	/**
	* Returns the market size units of this ivld demand actual.
	*
	* @return the market size units of this ivld demand actual
	*/
	@Override
	public java.lang.String getMarketSizeUnits() {
		return _ivldDemandActual.getMarketSizeUnits();
	}

	/**
	* Returns the modified by of this ivld demand actual.
	*
	* @return the modified by of this ivld demand actual
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldDemandActual.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld demand actual.
	*
	* @return the modified date of this ivld demand actual
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldDemandActual.getModifiedDate();
	}

	/**
	* Returns the net sales amount of this ivld demand actual.
	*
	* @return the net sales amount of this ivld demand actual
	*/
	@Override
	public java.lang.String getNetSalesAmount() {
		return _ivldDemandActual.getNetSalesAmount();
	}

	/**
	* Returns the net sales price of this ivld demand actual.
	*
	* @return the net sales price of this ivld demand actual
	*/
	@Override
	public java.lang.String getNetSalesPrice() {
		return _ivldDemandActual.getNetSalesPrice();
	}

	/**
	* Returns the organization key of this ivld demand actual.
	*
	* @return the organization key of this ivld demand actual
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldDemandActual.getOrganizationKey();
	}

	/**
	* Returns the primary key of this ivld demand actual.
	*
	* @return the primary key of this ivld demand actual
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldDemandActual.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldDemandActual.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld demand actual.
	*
	* @return the reason for failure of this ivld demand actual
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldDemandActual.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld demand actual.
	*
	* @return the reprocessed flag of this ivld demand actual
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldDemandActual.getReprocessedFlag();
	}

	/**
	* Returns the segment of this ivld demand actual.
	*
	* @return the segment of this ivld demand actual
	*/
	@Override
	public java.lang.String getSegment() {
		return _ivldDemandActual.getSegment();
	}

	/**
	* Returns the source of this ivld demand actual.
	*
	* @return the source of this ivld demand actual
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldDemandActual.getSource();
	}

	/**
	* Returns the total demand amount of this ivld demand actual.
	*
	* @return the total demand amount of this ivld demand actual
	*/
	@Override
	public java.lang.String getTotalDemandAmount() {
		return _ivldDemandActual.getTotalDemandAmount();
	}

	/**
	* Returns the total demand units of this ivld demand actual.
	*
	* @return the total demand units of this ivld demand actual
	*/
	@Override
	public java.lang.String getTotalDemandUnits() {
		return _ivldDemandActual.getTotalDemandUnits();
	}

	@Override
	public int hashCode() {
		return _ivldDemandActual.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldDemandActual.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld demand actual is check record.
	*
	* @return <code>true</code> if this ivld demand actual is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldDemandActual.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldDemandActual.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldDemandActual.isNew();
	}

	@Override
	public void persist() {
		_ivldDemandActual.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld demand actual.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld demand actual
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldDemandActual.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the batch ID of this ivld demand actual.
	*
	* @param batchId the batch ID of this ivld demand actual
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldDemandActual.setBatchId(batchId);
	}

	/**
	* Sets the brand ID of this ivld demand actual.
	*
	* @param brandId the brand ID of this ivld demand actual
	*/
	@Override
	public void setBrandId(java.lang.String brandId) {
		_ivldDemandActual.setBrandId(brandId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldDemandActual.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld demand actual is check record.
	*
	* @param checkRecord the check record of this ivld demand actual
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldDemandActual.setCheckRecord(checkRecord);
	}

	/**
	* Sets the country of this ivld demand actual.
	*
	* @param country the country of this ivld demand actual
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_ivldDemandActual.setCountry(country);
	}

	/**
	* Sets the created by of this ivld demand actual.
	*
	* @param createdBy the created by of this ivld demand actual
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldDemandActual.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld demand actual.
	*
	* @param createdDate the created date of this ivld demand actual
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldDemandActual.setCreatedDate(createdDate);
	}

	/**
	* Sets the demand actual interface ID of this ivld demand actual.
	*
	* @param demandActualInterfaceId the demand actual interface ID of this ivld demand actual
	*/
	@Override
	public void setDemandActualInterfaceId(
		java.lang.String demandActualInterfaceId) {
		_ivldDemandActual.setDemandActualInterfaceId(demandActualInterfaceId);
	}

	/**
	* Sets the error code of this ivld demand actual.
	*
	* @param errorCode the error code of this ivld demand actual
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldDemandActual.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld demand actual.
	*
	* @param errorField the error field of this ivld demand actual
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldDemandActual.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldDemandActual.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldDemandActual.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldDemandActual.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast month of this ivld demand actual.
	*
	* @param forecastMonth the forecast month of this ivld demand actual
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_ivldDemandActual.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast type of this ivld demand actual.
	*
	* @param forecastType the forecast type of this ivld demand actual
	*/
	@Override
	public void setForecastType(java.lang.String forecastType) {
		_ivldDemandActual.setForecastType(forecastType);
	}

	/**
	* Sets the forecast year of this ivld demand actual.
	*
	* @param forecastYear the forecast year of this ivld demand actual
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_ivldDemandActual.setForecastYear(forecastYear);
	}

	/**
	* Sets the gross amount of this ivld demand actual.
	*
	* @param grossAmount the gross amount of this ivld demand actual
	*/
	@Override
	public void setGrossAmount(java.lang.String grossAmount) {
		_ivldDemandActual.setGrossAmount(grossAmount);
	}

	/**
	* Sets the gross price of this ivld demand actual.
	*
	* @param grossPrice the gross price of this ivld demand actual
	*/
	@Override
	public void setGrossPrice(java.lang.String grossPrice) {
		_ivldDemandActual.setGrossPrice(grossPrice);
	}

	/**
	* Sets the gross units of this ivld demand actual.
	*
	* @param grossUnits the gross units of this ivld demand actual
	*/
	@Override
	public void setGrossUnits(java.lang.String grossUnits) {
		_ivldDemandActual.setGrossUnits(grossUnits);
	}

	/**
	* Sets the intf inserted date of this ivld demand actual.
	*
	* @param intfInsertedDate the intf inserted date of this ivld demand actual
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldDemandActual.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the item ID of this ivld demand actual.
	*
	* @param itemId the item ID of this ivld demand actual
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldDemandActual.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ivld demand actual.
	*
	* @param itemIdentifier the item identifier of this ivld demand actual
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ivldDemandActual.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this ivld demand actual.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld demand actual
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_ivldDemandActual.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the ivld demand actual sid of this ivld demand actual.
	*
	* @param ivldDemandActualSid the ivld demand actual sid of this ivld demand actual
	*/
	@Override
	public void setIvldDemandActualSid(int ivldDemandActualSid) {
		_ivldDemandActual.setIvldDemandActualSid(ivldDemandActualSid);
	}

	/**
	* Sets the market share ratio of this ivld demand actual.
	*
	* @param marketShareRatio the market share ratio of this ivld demand actual
	*/
	@Override
	public void setMarketShareRatio(java.lang.String marketShareRatio) {
		_ivldDemandActual.setMarketShareRatio(marketShareRatio);
	}

	/**
	* Sets the market share units of this ivld demand actual.
	*
	* @param marketShareUnits the market share units of this ivld demand actual
	*/
	@Override
	public void setMarketShareUnits(java.lang.String marketShareUnits) {
		_ivldDemandActual.setMarketShareUnits(marketShareUnits);
	}

	/**
	* Sets the market size units of this ivld demand actual.
	*
	* @param marketSizeUnits the market size units of this ivld demand actual
	*/
	@Override
	public void setMarketSizeUnits(java.lang.String marketSizeUnits) {
		_ivldDemandActual.setMarketSizeUnits(marketSizeUnits);
	}

	/**
	* Sets the modified by of this ivld demand actual.
	*
	* @param modifiedBy the modified by of this ivld demand actual
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldDemandActual.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld demand actual.
	*
	* @param modifiedDate the modified date of this ivld demand actual
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldDemandActual.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the net sales amount of this ivld demand actual.
	*
	* @param netSalesAmount the net sales amount of this ivld demand actual
	*/
	@Override
	public void setNetSalesAmount(java.lang.String netSalesAmount) {
		_ivldDemandActual.setNetSalesAmount(netSalesAmount);
	}

	/**
	* Sets the net sales price of this ivld demand actual.
	*
	* @param netSalesPrice the net sales price of this ivld demand actual
	*/
	@Override
	public void setNetSalesPrice(java.lang.String netSalesPrice) {
		_ivldDemandActual.setNetSalesPrice(netSalesPrice);
	}

	@Override
	public void setNew(boolean n) {
		_ivldDemandActual.setNew(n);
	}

	/**
	* Sets the organization key of this ivld demand actual.
	*
	* @param organizationKey the organization key of this ivld demand actual
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldDemandActual.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this ivld demand actual.
	*
	* @param primaryKey the primary key of this ivld demand actual
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldDemandActual.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldDemandActual.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld demand actual.
	*
	* @param reasonForFailure the reason for failure of this ivld demand actual
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldDemandActual.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld demand actual.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld demand actual
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldDemandActual.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the segment of this ivld demand actual.
	*
	* @param segment the segment of this ivld demand actual
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_ivldDemandActual.setSegment(segment);
	}

	/**
	* Sets the source of this ivld demand actual.
	*
	* @param source the source of this ivld demand actual
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldDemandActual.setSource(source);
	}

	/**
	* Sets the total demand amount of this ivld demand actual.
	*
	* @param totalDemandAmount the total demand amount of this ivld demand actual
	*/
	@Override
	public void setTotalDemandAmount(java.lang.String totalDemandAmount) {
		_ivldDemandActual.setTotalDemandAmount(totalDemandAmount);
	}

	/**
	* Sets the total demand units of this ivld demand actual.
	*
	* @param totalDemandUnits the total demand units of this ivld demand actual
	*/
	@Override
	public void setTotalDemandUnits(java.lang.String totalDemandUnits) {
		_ivldDemandActual.setTotalDemandUnits(totalDemandUnits);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldDemandActual> toCacheModel() {
		return _ivldDemandActual.toCacheModel();
	}

	@Override
	public IvldDemandActual toEscapedModel() {
		return new IvldDemandActualWrapper(_ivldDemandActual.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldDemandActual.toString();
	}

	@Override
	public IvldDemandActual toUnescapedModel() {
		return new IvldDemandActualWrapper(_ivldDemandActual.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldDemandActual.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldDemandActualWrapper)) {
			return false;
		}

		IvldDemandActualWrapper ivldDemandActualWrapper = (IvldDemandActualWrapper)obj;

		if (Objects.equals(_ivldDemandActual,
					ivldDemandActualWrapper._ivldDemandActual)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldDemandActual getWrappedModel() {
		return _ivldDemandActual;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldDemandActual.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldDemandActual.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldDemandActual.resetOriginalValues();
	}

	private final IvldDemandActual _ivldDemandActual;
}