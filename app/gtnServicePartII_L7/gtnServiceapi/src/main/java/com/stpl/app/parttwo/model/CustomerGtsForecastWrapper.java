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
 * This class is a wrapper for {@link CustomerGtsForecast}.
 * </p>
 *
 * @author
 * @see CustomerGtsForecast
 * @generated
 */
@ProviderType
public class CustomerGtsForecastWrapper implements CustomerGtsForecast,
	ModelWrapper<CustomerGtsForecast> {
	public CustomerGtsForecastWrapper(CustomerGtsForecast customerGtsForecast) {
		_customerGtsForecast = customerGtsForecast;
	}

	@Override
	public Class<?> getModelClass() {
		return CustomerGtsForecast.class;
	}

	@Override
	public String getModelClassName() {
		return CustomerGtsForecast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("price", getPrice());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("forecastYear", getForecastYear());
		attributes.put("deductionAmount", getDeductionAmount());
		attributes.put("deductionId", getDeductionId());
		attributes.put("forecastDate", getForecastDate());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("brandMasterSid", getBrandMasterSid());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("salesAmount", getSalesAmount());
		attributes.put("deductionType", getDeductionType());
		attributes.put("companyMasterSid", getCompanyMasterSid());
		attributes.put("units", getUnits());
		attributes.put("deductionRate", getDeductionRate());
		attributes.put("customerGtsForecastSid", getCustomerGtsForecastSid());
		attributes.put("country", getCountry());
		attributes.put("companyId", getCompanyId());
		attributes.put("forecastValueType", getForecastValueType());
		attributes.put("deductionCategory", getDeductionCategory());
		attributes.put("adjustmentCode", getAdjustmentCode());
		attributes.put("deductionProgramType", getDeductionProgramType());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("salesInclusion", getSalesInclusion());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("priceType", getPriceType());
		attributes.put("forecastMonth", getForecastMonth());
		attributes.put("deductionInclusion", getDeductionInclusion());
		attributes.put("segment", getSegment());
		attributes.put("brand", getBrand());
		attributes.put("forecastName", getForecastName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String price = (String)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String forecastYear = (String)attributes.get("forecastYear");

		if (forecastYear != null) {
			setForecastYear(forecastYear);
		}

		String deductionAmount = (String)attributes.get("deductionAmount");

		if (deductionAmount != null) {
			setDeductionAmount(deductionAmount);
		}

		String deductionId = (String)attributes.get("deductionId");

		if (deductionId != null) {
			setDeductionId(deductionId);
		}

		Date forecastDate = (Date)attributes.get("forecastDate");

		if (forecastDate != null) {
			setForecastDate(forecastDate);
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

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String salesAmount = (String)attributes.get("salesAmount");

		if (salesAmount != null) {
			setSalesAmount(salesAmount);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		Integer companyMasterSid = (Integer)attributes.get("companyMasterSid");

		if (companyMasterSid != null) {
			setCompanyMasterSid(companyMasterSid);
		}

		String units = (String)attributes.get("units");

		if (units != null) {
			setUnits(units);
		}

		String deductionRate = (String)attributes.get("deductionRate");

		if (deductionRate != null) {
			setDeductionRate(deductionRate);
		}

		Integer customerGtsForecastSid = (Integer)attributes.get(
				"customerGtsForecastSid");

		if (customerGtsForecastSid != null) {
			setCustomerGtsForecastSid(customerGtsForecastSid);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String companyId = (String)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String forecastValueType = (String)attributes.get("forecastValueType");

		if (forecastValueType != null) {
			setForecastValueType(forecastValueType);
		}

		String deductionCategory = (String)attributes.get("deductionCategory");

		if (deductionCategory != null) {
			setDeductionCategory(deductionCategory);
		}

		String adjustmentCode = (String)attributes.get("adjustmentCode");

		if (adjustmentCode != null) {
			setAdjustmentCode(adjustmentCode);
		}

		String deductionProgramType = (String)attributes.get(
				"deductionProgramType");

		if (deductionProgramType != null) {
			setDeductionProgramType(deductionProgramType);
		}

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String salesInclusion = (String)attributes.get("salesInclusion");

		if (salesInclusion != null) {
			setSalesInclusion(salesInclusion);
		}

		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String priceType = (String)attributes.get("priceType");

		if (priceType != null) {
			setPriceType(priceType);
		}

		String forecastMonth = (String)attributes.get("forecastMonth");

		if (forecastMonth != null) {
			setForecastMonth(forecastMonth);
		}

		String deductionInclusion = (String)attributes.get("deductionInclusion");

		if (deductionInclusion != null) {
			setDeductionInclusion(deductionInclusion);
		}

		String segment = (String)attributes.get("segment");

		if (segment != null) {
			setSegment(segment);
		}

		String brand = (String)attributes.get("brand");

		if (brand != null) {
			setBrand(brand);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new CustomerGtsForecastWrapper((CustomerGtsForecast)_customerGtsForecast.clone());
	}

	@Override
	public int compareTo(CustomerGtsForecast customerGtsForecast) {
		return _customerGtsForecast.compareTo(customerGtsForecast);
	}

	/**
	* Returns the add chg del indicator of this customer gts forecast.
	*
	* @return the add chg del indicator of this customer gts forecast
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _customerGtsForecast.getAddChgDelIndicator();
	}

	/**
	* Returns the adjustment code of this customer gts forecast.
	*
	* @return the adjustment code of this customer gts forecast
	*/
	@Override
	public java.lang.String getAdjustmentCode() {
		return _customerGtsForecast.getAdjustmentCode();
	}

	/**
	* Returns the batch ID of this customer gts forecast.
	*
	* @return the batch ID of this customer gts forecast
	*/
	@Override
	public java.lang.String getBatchId() {
		return _customerGtsForecast.getBatchId();
	}

	/**
	* Returns the brand of this customer gts forecast.
	*
	* @return the brand of this customer gts forecast
	*/
	@Override
	public java.lang.String getBrand() {
		return _customerGtsForecast.getBrand();
	}

	/**
	* Returns the brand master sid of this customer gts forecast.
	*
	* @return the brand master sid of this customer gts forecast
	*/
	@Override
	public int getBrandMasterSid() {
		return _customerGtsForecast.getBrandMasterSid();
	}

	/**
	* Returns the company ID of this customer gts forecast.
	*
	* @return the company ID of this customer gts forecast
	*/
	@Override
	public java.lang.String getCompanyId() {
		return _customerGtsForecast.getCompanyId();
	}

	/**
	* Returns the company master sid of this customer gts forecast.
	*
	* @return the company master sid of this customer gts forecast
	*/
	@Override
	public int getCompanyMasterSid() {
		return _customerGtsForecast.getCompanyMasterSid();
	}

	/**
	* Returns the country of this customer gts forecast.
	*
	* @return the country of this customer gts forecast
	*/
	@Override
	public java.lang.String getCountry() {
		return _customerGtsForecast.getCountry();
	}

	/**
	* Returns the created by of this customer gts forecast.
	*
	* @return the created by of this customer gts forecast
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _customerGtsForecast.getCreatedBy();
	}

	/**
	* Returns the created date of this customer gts forecast.
	*
	* @return the created date of this customer gts forecast
	*/
	@Override
	public Date getCreatedDate() {
		return _customerGtsForecast.getCreatedDate();
	}

	/**
	* Returns the customer gts forecast sid of this customer gts forecast.
	*
	* @return the customer gts forecast sid of this customer gts forecast
	*/
	@Override
	public int getCustomerGtsForecastSid() {
		return _customerGtsForecast.getCustomerGtsForecastSid();
	}

	/**
	* Returns the deduction amount of this customer gts forecast.
	*
	* @return the deduction amount of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionAmount() {
		return _customerGtsForecast.getDeductionAmount();
	}

	/**
	* Returns the deduction category of this customer gts forecast.
	*
	* @return the deduction category of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionCategory() {
		return _customerGtsForecast.getDeductionCategory();
	}

	/**
	* Returns the deduction ID of this customer gts forecast.
	*
	* @return the deduction ID of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionId() {
		return _customerGtsForecast.getDeductionId();
	}

	/**
	* Returns the deduction inclusion of this customer gts forecast.
	*
	* @return the deduction inclusion of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionInclusion() {
		return _customerGtsForecast.getDeductionInclusion();
	}

	/**
	* Returns the deduction program type of this customer gts forecast.
	*
	* @return the deduction program type of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionProgramType() {
		return _customerGtsForecast.getDeductionProgramType();
	}

	/**
	* Returns the deduction rate of this customer gts forecast.
	*
	* @return the deduction rate of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionRate() {
		return _customerGtsForecast.getDeductionRate();
	}

	/**
	* Returns the deduction type of this customer gts forecast.
	*
	* @return the deduction type of this customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionType() {
		return _customerGtsForecast.getDeductionType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _customerGtsForecast.getExpandoBridge();
	}

	/**
	* Returns the forecast date of this customer gts forecast.
	*
	* @return the forecast date of this customer gts forecast
	*/
	@Override
	public Date getForecastDate() {
		return _customerGtsForecast.getForecastDate();
	}

	/**
	* Returns the forecast month of this customer gts forecast.
	*
	* @return the forecast month of this customer gts forecast
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _customerGtsForecast.getForecastMonth();
	}

	/**
	* Returns the forecast name of this customer gts forecast.
	*
	* @return the forecast name of this customer gts forecast
	*/
	@Override
	public java.lang.String getForecastName() {
		return _customerGtsForecast.getForecastName();
	}

	/**
	* Returns the forecast value type of this customer gts forecast.
	*
	* @return the forecast value type of this customer gts forecast
	*/
	@Override
	public java.lang.String getForecastValueType() {
		return _customerGtsForecast.getForecastValueType();
	}

	/**
	* Returns the forecast ver of this customer gts forecast.
	*
	* @return the forecast ver of this customer gts forecast
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _customerGtsForecast.getForecastVer();
	}

	/**
	* Returns the forecast year of this customer gts forecast.
	*
	* @return the forecast year of this customer gts forecast
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _customerGtsForecast.getForecastYear();
	}

	/**
	* Returns the inbound status of this customer gts forecast.
	*
	* @return the inbound status of this customer gts forecast
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _customerGtsForecast.getInboundStatus();
	}

	/**
	* Returns the item ID of this customer gts forecast.
	*
	* @return the item ID of this customer gts forecast
	*/
	@Override
	public java.lang.String getItemId() {
		return _customerGtsForecast.getItemId();
	}

	/**
	* Returns the item master sid of this customer gts forecast.
	*
	* @return the item master sid of this customer gts forecast
	*/
	@Override
	public int getItemMasterSid() {
		return _customerGtsForecast.getItemMasterSid();
	}

	/**
	* Returns the modified by of this customer gts forecast.
	*
	* @return the modified by of this customer gts forecast
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _customerGtsForecast.getModifiedBy();
	}

	/**
	* Returns the modified date of this customer gts forecast.
	*
	* @return the modified date of this customer gts forecast
	*/
	@Override
	public Date getModifiedDate() {
		return _customerGtsForecast.getModifiedDate();
	}

	/**
	* Returns the price of this customer gts forecast.
	*
	* @return the price of this customer gts forecast
	*/
	@Override
	public java.lang.String getPrice() {
		return _customerGtsForecast.getPrice();
	}

	/**
	* Returns the price type of this customer gts forecast.
	*
	* @return the price type of this customer gts forecast
	*/
	@Override
	public java.lang.String getPriceType() {
		return _customerGtsForecast.getPriceType();
	}

	/**
	* Returns the primary key of this customer gts forecast.
	*
	* @return the primary key of this customer gts forecast
	*/
	@Override
	public int getPrimaryKey() {
		return _customerGtsForecast.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _customerGtsForecast.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this customer gts forecast.
	*
	* @return the record lock status of this customer gts forecast
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _customerGtsForecast.getRecordLockStatus();
	}

	/**
	* Returns the sales amount of this customer gts forecast.
	*
	* @return the sales amount of this customer gts forecast
	*/
	@Override
	public java.lang.String getSalesAmount() {
		return _customerGtsForecast.getSalesAmount();
	}

	/**
	* Returns the sales inclusion of this customer gts forecast.
	*
	* @return the sales inclusion of this customer gts forecast
	*/
	@Override
	public java.lang.String getSalesInclusion() {
		return _customerGtsForecast.getSalesInclusion();
	}

	/**
	* Returns the segment of this customer gts forecast.
	*
	* @return the segment of this customer gts forecast
	*/
	@Override
	public java.lang.String getSegment() {
		return _customerGtsForecast.getSegment();
	}

	/**
	* Returns the source of this customer gts forecast.
	*
	* @return the source of this customer gts forecast
	*/
	@Override
	public java.lang.String getSource() {
		return _customerGtsForecast.getSource();
	}

	/**
	* Returns the units of this customer gts forecast.
	*
	* @return the units of this customer gts forecast
	*/
	@Override
	public java.lang.String getUnits() {
		return _customerGtsForecast.getUnits();
	}

	@Override
	public int hashCode() {
		return _customerGtsForecast.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _customerGtsForecast.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _customerGtsForecast.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _customerGtsForecast.isNew();
	}

	/**
	* Returns <code>true</code> if this customer gts forecast is record lock status.
	*
	* @return <code>true</code> if this customer gts forecast is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _customerGtsForecast.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_customerGtsForecast.persist();
	}

	/**
	* Sets the add chg del indicator of this customer gts forecast.
	*
	* @param addChgDelIndicator the add chg del indicator of this customer gts forecast
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_customerGtsForecast.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the adjustment code of this customer gts forecast.
	*
	* @param adjustmentCode the adjustment code of this customer gts forecast
	*/
	@Override
	public void setAdjustmentCode(java.lang.String adjustmentCode) {
		_customerGtsForecast.setAdjustmentCode(adjustmentCode);
	}

	/**
	* Sets the batch ID of this customer gts forecast.
	*
	* @param batchId the batch ID of this customer gts forecast
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_customerGtsForecast.setBatchId(batchId);
	}

	/**
	* Sets the brand of this customer gts forecast.
	*
	* @param brand the brand of this customer gts forecast
	*/
	@Override
	public void setBrand(java.lang.String brand) {
		_customerGtsForecast.setBrand(brand);
	}

	/**
	* Sets the brand master sid of this customer gts forecast.
	*
	* @param brandMasterSid the brand master sid of this customer gts forecast
	*/
	@Override
	public void setBrandMasterSid(int brandMasterSid) {
		_customerGtsForecast.setBrandMasterSid(brandMasterSid);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_customerGtsForecast.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this customer gts forecast.
	*
	* @param companyId the company ID of this customer gts forecast
	*/
	@Override
	public void setCompanyId(java.lang.String companyId) {
		_customerGtsForecast.setCompanyId(companyId);
	}

	/**
	* Sets the company master sid of this customer gts forecast.
	*
	* @param companyMasterSid the company master sid of this customer gts forecast
	*/
	@Override
	public void setCompanyMasterSid(int companyMasterSid) {
		_customerGtsForecast.setCompanyMasterSid(companyMasterSid);
	}

	/**
	* Sets the country of this customer gts forecast.
	*
	* @param country the country of this customer gts forecast
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_customerGtsForecast.setCountry(country);
	}

	/**
	* Sets the created by of this customer gts forecast.
	*
	* @param createdBy the created by of this customer gts forecast
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_customerGtsForecast.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this customer gts forecast.
	*
	* @param createdDate the created date of this customer gts forecast
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_customerGtsForecast.setCreatedDate(createdDate);
	}

	/**
	* Sets the customer gts forecast sid of this customer gts forecast.
	*
	* @param customerGtsForecastSid the customer gts forecast sid of this customer gts forecast
	*/
	@Override
	public void setCustomerGtsForecastSid(int customerGtsForecastSid) {
		_customerGtsForecast.setCustomerGtsForecastSid(customerGtsForecastSid);
	}

	/**
	* Sets the deduction amount of this customer gts forecast.
	*
	* @param deductionAmount the deduction amount of this customer gts forecast
	*/
	@Override
	public void setDeductionAmount(java.lang.String deductionAmount) {
		_customerGtsForecast.setDeductionAmount(deductionAmount);
	}

	/**
	* Sets the deduction category of this customer gts forecast.
	*
	* @param deductionCategory the deduction category of this customer gts forecast
	*/
	@Override
	public void setDeductionCategory(java.lang.String deductionCategory) {
		_customerGtsForecast.setDeductionCategory(deductionCategory);
	}

	/**
	* Sets the deduction ID of this customer gts forecast.
	*
	* @param deductionId the deduction ID of this customer gts forecast
	*/
	@Override
	public void setDeductionId(java.lang.String deductionId) {
		_customerGtsForecast.setDeductionId(deductionId);
	}

	/**
	* Sets the deduction inclusion of this customer gts forecast.
	*
	* @param deductionInclusion the deduction inclusion of this customer gts forecast
	*/
	@Override
	public void setDeductionInclusion(java.lang.String deductionInclusion) {
		_customerGtsForecast.setDeductionInclusion(deductionInclusion);
	}

	/**
	* Sets the deduction program type of this customer gts forecast.
	*
	* @param deductionProgramType the deduction program type of this customer gts forecast
	*/
	@Override
	public void setDeductionProgramType(java.lang.String deductionProgramType) {
		_customerGtsForecast.setDeductionProgramType(deductionProgramType);
	}

	/**
	* Sets the deduction rate of this customer gts forecast.
	*
	* @param deductionRate the deduction rate of this customer gts forecast
	*/
	@Override
	public void setDeductionRate(java.lang.String deductionRate) {
		_customerGtsForecast.setDeductionRate(deductionRate);
	}

	/**
	* Sets the deduction type of this customer gts forecast.
	*
	* @param deductionType the deduction type of this customer gts forecast
	*/
	@Override
	public void setDeductionType(java.lang.String deductionType) {
		_customerGtsForecast.setDeductionType(deductionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_customerGtsForecast.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_customerGtsForecast.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_customerGtsForecast.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast date of this customer gts forecast.
	*
	* @param forecastDate the forecast date of this customer gts forecast
	*/
	@Override
	public void setForecastDate(Date forecastDate) {
		_customerGtsForecast.setForecastDate(forecastDate);
	}

	/**
	* Sets the forecast month of this customer gts forecast.
	*
	* @param forecastMonth the forecast month of this customer gts forecast
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_customerGtsForecast.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast name of this customer gts forecast.
	*
	* @param forecastName the forecast name of this customer gts forecast
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_customerGtsForecast.setForecastName(forecastName);
	}

	/**
	* Sets the forecast value type of this customer gts forecast.
	*
	* @param forecastValueType the forecast value type of this customer gts forecast
	*/
	@Override
	public void setForecastValueType(java.lang.String forecastValueType) {
		_customerGtsForecast.setForecastValueType(forecastValueType);
	}

	/**
	* Sets the forecast ver of this customer gts forecast.
	*
	* @param forecastVer the forecast ver of this customer gts forecast
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_customerGtsForecast.setForecastVer(forecastVer);
	}

	/**
	* Sets the forecast year of this customer gts forecast.
	*
	* @param forecastYear the forecast year of this customer gts forecast
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_customerGtsForecast.setForecastYear(forecastYear);
	}

	/**
	* Sets the inbound status of this customer gts forecast.
	*
	* @param inboundStatus the inbound status of this customer gts forecast
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_customerGtsForecast.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the item ID of this customer gts forecast.
	*
	* @param itemId the item ID of this customer gts forecast
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_customerGtsForecast.setItemId(itemId);
	}

	/**
	* Sets the item master sid of this customer gts forecast.
	*
	* @param itemMasterSid the item master sid of this customer gts forecast
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_customerGtsForecast.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this customer gts forecast.
	*
	* @param modifiedBy the modified by of this customer gts forecast
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_customerGtsForecast.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this customer gts forecast.
	*
	* @param modifiedDate the modified date of this customer gts forecast
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_customerGtsForecast.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_customerGtsForecast.setNew(n);
	}

	/**
	* Sets the price of this customer gts forecast.
	*
	* @param price the price of this customer gts forecast
	*/
	@Override
	public void setPrice(java.lang.String price) {
		_customerGtsForecast.setPrice(price);
	}

	/**
	* Sets the price type of this customer gts forecast.
	*
	* @param priceType the price type of this customer gts forecast
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_customerGtsForecast.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this customer gts forecast.
	*
	* @param primaryKey the primary key of this customer gts forecast
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_customerGtsForecast.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_customerGtsForecast.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this customer gts forecast is record lock status.
	*
	* @param recordLockStatus the record lock status of this customer gts forecast
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_customerGtsForecast.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the sales amount of this customer gts forecast.
	*
	* @param salesAmount the sales amount of this customer gts forecast
	*/
	@Override
	public void setSalesAmount(java.lang.String salesAmount) {
		_customerGtsForecast.setSalesAmount(salesAmount);
	}

	/**
	* Sets the sales inclusion of this customer gts forecast.
	*
	* @param salesInclusion the sales inclusion of this customer gts forecast
	*/
	@Override
	public void setSalesInclusion(java.lang.String salesInclusion) {
		_customerGtsForecast.setSalesInclusion(salesInclusion);
	}

	/**
	* Sets the segment of this customer gts forecast.
	*
	* @param segment the segment of this customer gts forecast
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_customerGtsForecast.setSegment(segment);
	}

	/**
	* Sets the source of this customer gts forecast.
	*
	* @param source the source of this customer gts forecast
	*/
	@Override
	public void setSource(java.lang.String source) {
		_customerGtsForecast.setSource(source);
	}

	/**
	* Sets the units of this customer gts forecast.
	*
	* @param units the units of this customer gts forecast
	*/
	@Override
	public void setUnits(java.lang.String units) {
		_customerGtsForecast.setUnits(units);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<CustomerGtsForecast> toCacheModel() {
		return _customerGtsForecast.toCacheModel();
	}

	@Override
	public CustomerGtsForecast toEscapedModel() {
		return new CustomerGtsForecastWrapper(_customerGtsForecast.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _customerGtsForecast.toString();
	}

	@Override
	public CustomerGtsForecast toUnescapedModel() {
		return new CustomerGtsForecastWrapper(_customerGtsForecast.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _customerGtsForecast.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CustomerGtsForecastWrapper)) {
			return false;
		}

		CustomerGtsForecastWrapper customerGtsForecastWrapper = (CustomerGtsForecastWrapper)obj;

		if (Objects.equals(_customerGtsForecast,
					customerGtsForecastWrapper._customerGtsForecast)) {
			return true;
		}

		return false;
	}

	@Override
	public CustomerGtsForecast getWrappedModel() {
		return _customerGtsForecast;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _customerGtsForecast.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _customerGtsForecast.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_customerGtsForecast.resetOriginalValues();
	}

	private final CustomerGtsForecast _customerGtsForecast;
}