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
 * This class is a wrapper for {@link VwCustomerGtsForecast}.
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecast
 * @generated
 */
@ProviderType
public class VwCustomerGtsForecastWrapper implements VwCustomerGtsForecast,
	ModelWrapper<VwCustomerGtsForecast> {
	public VwCustomerGtsForecastWrapper(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		_vwCustomerGtsForecast = vwCustomerGtsForecast;
	}

	@Override
	public Class<?> getModelClass() {
		return VwCustomerGtsForecast.class;
	}

	@Override
	public String getModelClassName() {
		return VwCustomerGtsForecast.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("price", getPrice());
		attributes.put("forecastYear", getForecastYear());
		attributes.put("deductionAmount", getDeductionAmount());
		attributes.put("deductionId", getDeductionId());
		attributes.put("forecastDate", getForecastDate());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("salesAmount", getSalesAmount());
		attributes.put("udc6", getUdc6());
		attributes.put("udc5", getUdc5());
		attributes.put("deductionType", getDeductionType());
		attributes.put("udc4", getUdc4());
		attributes.put("units", getUnits());
		attributes.put("deductionRate", getDeductionRate());
		attributes.put("udc1", getUdc1());
		attributes.put("customerGtsForecastSid", getCustomerGtsForecastSid());
		attributes.put("udc2", getUdc2());
		attributes.put("udc3", getUdc3());
		attributes.put("country", getCountry());
		attributes.put("companyIdString", getCompanyIdString());
		attributes.put("forecastValueType", getForecastValueType());
		attributes.put("deductionCategory", getDeductionCategory());
		attributes.put("adjustmentCode", getAdjustmentCode());
		attributes.put("deductionProgramType", getDeductionProgramType());
		attributes.put("customerGtsForecastIntfId",
			getCustomerGtsForecastIntfId());
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
		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		String forecastYear = (String)attributes.get("forecastYear");

		if (forecastYear != null) {
			setForecastYear(forecastYear);
		}

		Double deductionAmount = (Double)attributes.get("deductionAmount");

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

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
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

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		Double salesAmount = (Double)attributes.get("salesAmount");

		if (salesAmount != null) {
			setSalesAmount(salesAmount);
		}

		String udc6 = (String)attributes.get("udc6");

		if (udc6 != null) {
			setUdc6(udc6);
		}

		String udc5 = (String)attributes.get("udc5");

		if (udc5 != null) {
			setUdc5(udc5);
		}

		String deductionType = (String)attributes.get("deductionType");

		if (deductionType != null) {
			setDeductionType(deductionType);
		}

		String udc4 = (String)attributes.get("udc4");

		if (udc4 != null) {
			setUdc4(udc4);
		}

		Double units = (Double)attributes.get("units");

		if (units != null) {
			setUnits(units);
		}

		Double deductionRate = (Double)attributes.get("deductionRate");

		if (deductionRate != null) {
			setDeductionRate(deductionRate);
		}

		String udc1 = (String)attributes.get("udc1");

		if (udc1 != null) {
			setUdc1(udc1);
		}

		Integer customerGtsForecastSid = (Integer)attributes.get(
				"customerGtsForecastSid");

		if (customerGtsForecastSid != null) {
			setCustomerGtsForecastSid(customerGtsForecastSid);
		}

		String udc2 = (String)attributes.get("udc2");

		if (udc2 != null) {
			setUdc2(udc2);
		}

		String udc3 = (String)attributes.get("udc3");

		if (udc3 != null) {
			setUdc3(udc3);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String companyIdString = (String)attributes.get("companyIdString");

		if (companyIdString != null) {
			setCompanyIdString(companyIdString);
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

		Integer customerGtsForecastIntfId = (Integer)attributes.get(
				"customerGtsForecastIntfId");

		if (customerGtsForecastIntfId != null) {
			setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
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
		return new VwCustomerGtsForecastWrapper((VwCustomerGtsForecast)_vwCustomerGtsForecast.clone());
	}

	@Override
	public int compareTo(VwCustomerGtsForecast vwCustomerGtsForecast) {
		return _vwCustomerGtsForecast.compareTo(vwCustomerGtsForecast);
	}

	/**
	* Returns the add chg del indicator of this vw customer gts forecast.
	*
	* @return the add chg del indicator of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwCustomerGtsForecast.getAddChgDelIndicator();
	}

	/**
	* Returns the adjustment code of this vw customer gts forecast.
	*
	* @return the adjustment code of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getAdjustmentCode() {
		return _vwCustomerGtsForecast.getAdjustmentCode();
	}

	/**
	* Returns the batch ID of this vw customer gts forecast.
	*
	* @return the batch ID of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwCustomerGtsForecast.getBatchId();
	}

	/**
	* Returns the brand of this vw customer gts forecast.
	*
	* @return the brand of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getBrand() {
		return _vwCustomerGtsForecast.getBrand();
	}

	/**
	* Returns the company ID string of this vw customer gts forecast.
	*
	* @return the company ID string of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getCompanyIdString() {
		return _vwCustomerGtsForecast.getCompanyIdString();
	}

	/**
	* Returns the country of this vw customer gts forecast.
	*
	* @return the country of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwCustomerGtsForecast.getCountry();
	}

	/**
	* Returns the created by of this vw customer gts forecast.
	*
	* @return the created by of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwCustomerGtsForecast.getCreatedBy();
	}

	/**
	* Returns the created date of this vw customer gts forecast.
	*
	* @return the created date of this vw customer gts forecast
	*/
	@Override
	public Date getCreatedDate() {
		return _vwCustomerGtsForecast.getCreatedDate();
	}

	/**
	* Returns the customer gts forecast intf ID of this vw customer gts forecast.
	*
	* @return the customer gts forecast intf ID of this vw customer gts forecast
	*/
	@Override
	public int getCustomerGtsForecastIntfId() {
		return _vwCustomerGtsForecast.getCustomerGtsForecastIntfId();
	}

	/**
	* Returns the customer gts forecast sid of this vw customer gts forecast.
	*
	* @return the customer gts forecast sid of this vw customer gts forecast
	*/
	@Override
	public int getCustomerGtsForecastSid() {
		return _vwCustomerGtsForecast.getCustomerGtsForecastSid();
	}

	/**
	* Returns the deduction amount of this vw customer gts forecast.
	*
	* @return the deduction amount of this vw customer gts forecast
	*/
	@Override
	public double getDeductionAmount() {
		return _vwCustomerGtsForecast.getDeductionAmount();
	}

	/**
	* Returns the deduction category of this vw customer gts forecast.
	*
	* @return the deduction category of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionCategory() {
		return _vwCustomerGtsForecast.getDeductionCategory();
	}

	/**
	* Returns the deduction ID of this vw customer gts forecast.
	*
	* @return the deduction ID of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionId() {
		return _vwCustomerGtsForecast.getDeductionId();
	}

	/**
	* Returns the deduction inclusion of this vw customer gts forecast.
	*
	* @return the deduction inclusion of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionInclusion() {
		return _vwCustomerGtsForecast.getDeductionInclusion();
	}

	/**
	* Returns the deduction program type of this vw customer gts forecast.
	*
	* @return the deduction program type of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionProgramType() {
		return _vwCustomerGtsForecast.getDeductionProgramType();
	}

	/**
	* Returns the deduction rate of this vw customer gts forecast.
	*
	* @return the deduction rate of this vw customer gts forecast
	*/
	@Override
	public double getDeductionRate() {
		return _vwCustomerGtsForecast.getDeductionRate();
	}

	/**
	* Returns the deduction type of this vw customer gts forecast.
	*
	* @return the deduction type of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getDeductionType() {
		return _vwCustomerGtsForecast.getDeductionType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwCustomerGtsForecast.getExpandoBridge();
	}

	/**
	* Returns the forecast date of this vw customer gts forecast.
	*
	* @return the forecast date of this vw customer gts forecast
	*/
	@Override
	public Date getForecastDate() {
		return _vwCustomerGtsForecast.getForecastDate();
	}

	/**
	* Returns the forecast month of this vw customer gts forecast.
	*
	* @return the forecast month of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getForecastMonth() {
		return _vwCustomerGtsForecast.getForecastMonth();
	}

	/**
	* Returns the forecast name of this vw customer gts forecast.
	*
	* @return the forecast name of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getForecastName() {
		return _vwCustomerGtsForecast.getForecastName();
	}

	/**
	* Returns the forecast value type of this vw customer gts forecast.
	*
	* @return the forecast value type of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getForecastValueType() {
		return _vwCustomerGtsForecast.getForecastValueType();
	}

	/**
	* Returns the forecast ver of this vw customer gts forecast.
	*
	* @return the forecast ver of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _vwCustomerGtsForecast.getForecastVer();
	}

	/**
	* Returns the forecast year of this vw customer gts forecast.
	*
	* @return the forecast year of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getForecastYear() {
		return _vwCustomerGtsForecast.getForecastYear();
	}

	/**
	* Returns the item ID of this vw customer gts forecast.
	*
	* @return the item ID of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwCustomerGtsForecast.getItemId();
	}

	/**
	* Returns the modified by of this vw customer gts forecast.
	*
	* @return the modified by of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwCustomerGtsForecast.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw customer gts forecast.
	*
	* @return the modified date of this vw customer gts forecast
	*/
	@Override
	public Date getModifiedDate() {
		return _vwCustomerGtsForecast.getModifiedDate();
	}

	/**
	* Returns the price of this vw customer gts forecast.
	*
	* @return the price of this vw customer gts forecast
	*/
	@Override
	public double getPrice() {
		return _vwCustomerGtsForecast.getPrice();
	}

	/**
	* Returns the price type of this vw customer gts forecast.
	*
	* @return the price type of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getPriceType() {
		return _vwCustomerGtsForecast.getPriceType();
	}

	/**
	* Returns the primary key of this vw customer gts forecast.
	*
	* @return the primary key of this vw customer gts forecast
	*/
	@Override
	public int getPrimaryKey() {
		return _vwCustomerGtsForecast.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwCustomerGtsForecast.getPrimaryKeyObj();
	}

	/**
	* Returns the sales amount of this vw customer gts forecast.
	*
	* @return the sales amount of this vw customer gts forecast
	*/
	@Override
	public double getSalesAmount() {
		return _vwCustomerGtsForecast.getSalesAmount();
	}

	/**
	* Returns the sales inclusion of this vw customer gts forecast.
	*
	* @return the sales inclusion of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getSalesInclusion() {
		return _vwCustomerGtsForecast.getSalesInclusion();
	}

	/**
	* Returns the segment of this vw customer gts forecast.
	*
	* @return the segment of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getSegment() {
		return _vwCustomerGtsForecast.getSegment();
	}

	/**
	* Returns the source of this vw customer gts forecast.
	*
	* @return the source of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getSource() {
		return _vwCustomerGtsForecast.getSource();
	}

	/**
	* Returns the udc1 of this vw customer gts forecast.
	*
	* @return the udc1 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc1() {
		return _vwCustomerGtsForecast.getUdc1();
	}

	/**
	* Returns the udc2 of this vw customer gts forecast.
	*
	* @return the udc2 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc2() {
		return _vwCustomerGtsForecast.getUdc2();
	}

	/**
	* Returns the udc3 of this vw customer gts forecast.
	*
	* @return the udc3 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc3() {
		return _vwCustomerGtsForecast.getUdc3();
	}

	/**
	* Returns the udc4 of this vw customer gts forecast.
	*
	* @return the udc4 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc4() {
		return _vwCustomerGtsForecast.getUdc4();
	}

	/**
	* Returns the udc5 of this vw customer gts forecast.
	*
	* @return the udc5 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc5() {
		return _vwCustomerGtsForecast.getUdc5();
	}

	/**
	* Returns the udc6 of this vw customer gts forecast.
	*
	* @return the udc6 of this vw customer gts forecast
	*/
	@Override
	public java.lang.String getUdc6() {
		return _vwCustomerGtsForecast.getUdc6();
	}

	/**
	* Returns the units of this vw customer gts forecast.
	*
	* @return the units of this vw customer gts forecast
	*/
	@Override
	public double getUnits() {
		return _vwCustomerGtsForecast.getUnits();
	}

	@Override
	public int hashCode() {
		return _vwCustomerGtsForecast.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwCustomerGtsForecast.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwCustomerGtsForecast.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwCustomerGtsForecast.isNew();
	}

	@Override
	public void persist() {
		_vwCustomerGtsForecast.persist();
	}

	/**
	* Sets the add chg del indicator of this vw customer gts forecast.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw customer gts forecast
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwCustomerGtsForecast.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the adjustment code of this vw customer gts forecast.
	*
	* @param adjustmentCode the adjustment code of this vw customer gts forecast
	*/
	@Override
	public void setAdjustmentCode(java.lang.String adjustmentCode) {
		_vwCustomerGtsForecast.setAdjustmentCode(adjustmentCode);
	}

	/**
	* Sets the batch ID of this vw customer gts forecast.
	*
	* @param batchId the batch ID of this vw customer gts forecast
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwCustomerGtsForecast.setBatchId(batchId);
	}

	/**
	* Sets the brand of this vw customer gts forecast.
	*
	* @param brand the brand of this vw customer gts forecast
	*/
	@Override
	public void setBrand(java.lang.String brand) {
		_vwCustomerGtsForecast.setBrand(brand);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwCustomerGtsForecast.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID string of this vw customer gts forecast.
	*
	* @param companyIdString the company ID string of this vw customer gts forecast
	*/
	@Override
	public void setCompanyIdString(java.lang.String companyIdString) {
		_vwCustomerGtsForecast.setCompanyIdString(companyIdString);
	}

	/**
	* Sets the country of this vw customer gts forecast.
	*
	* @param country the country of this vw customer gts forecast
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwCustomerGtsForecast.setCountry(country);
	}

	/**
	* Sets the created by of this vw customer gts forecast.
	*
	* @param createdBy the created by of this vw customer gts forecast
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwCustomerGtsForecast.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw customer gts forecast.
	*
	* @param createdDate the created date of this vw customer gts forecast
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwCustomerGtsForecast.setCreatedDate(createdDate);
	}

	/**
	* Sets the customer gts forecast intf ID of this vw customer gts forecast.
	*
	* @param customerGtsForecastIntfId the customer gts forecast intf ID of this vw customer gts forecast
	*/
	@Override
	public void setCustomerGtsForecastIntfId(int customerGtsForecastIntfId) {
		_vwCustomerGtsForecast.setCustomerGtsForecastIntfId(customerGtsForecastIntfId);
	}

	/**
	* Sets the customer gts forecast sid of this vw customer gts forecast.
	*
	* @param customerGtsForecastSid the customer gts forecast sid of this vw customer gts forecast
	*/
	@Override
	public void setCustomerGtsForecastSid(int customerGtsForecastSid) {
		_vwCustomerGtsForecast.setCustomerGtsForecastSid(customerGtsForecastSid);
	}

	/**
	* Sets the deduction amount of this vw customer gts forecast.
	*
	* @param deductionAmount the deduction amount of this vw customer gts forecast
	*/
	@Override
	public void setDeductionAmount(double deductionAmount) {
		_vwCustomerGtsForecast.setDeductionAmount(deductionAmount);
	}

	/**
	* Sets the deduction category of this vw customer gts forecast.
	*
	* @param deductionCategory the deduction category of this vw customer gts forecast
	*/
	@Override
	public void setDeductionCategory(java.lang.String deductionCategory) {
		_vwCustomerGtsForecast.setDeductionCategory(deductionCategory);
	}

	/**
	* Sets the deduction ID of this vw customer gts forecast.
	*
	* @param deductionId the deduction ID of this vw customer gts forecast
	*/
	@Override
	public void setDeductionId(java.lang.String deductionId) {
		_vwCustomerGtsForecast.setDeductionId(deductionId);
	}

	/**
	* Sets the deduction inclusion of this vw customer gts forecast.
	*
	* @param deductionInclusion the deduction inclusion of this vw customer gts forecast
	*/
	@Override
	public void setDeductionInclusion(java.lang.String deductionInclusion) {
		_vwCustomerGtsForecast.setDeductionInclusion(deductionInclusion);
	}

	/**
	* Sets the deduction program type of this vw customer gts forecast.
	*
	* @param deductionProgramType the deduction program type of this vw customer gts forecast
	*/
	@Override
	public void setDeductionProgramType(java.lang.String deductionProgramType) {
		_vwCustomerGtsForecast.setDeductionProgramType(deductionProgramType);
	}

	/**
	* Sets the deduction rate of this vw customer gts forecast.
	*
	* @param deductionRate the deduction rate of this vw customer gts forecast
	*/
	@Override
	public void setDeductionRate(double deductionRate) {
		_vwCustomerGtsForecast.setDeductionRate(deductionRate);
	}

	/**
	* Sets the deduction type of this vw customer gts forecast.
	*
	* @param deductionType the deduction type of this vw customer gts forecast
	*/
	@Override
	public void setDeductionType(java.lang.String deductionType) {
		_vwCustomerGtsForecast.setDeductionType(deductionType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwCustomerGtsForecast.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwCustomerGtsForecast.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwCustomerGtsForecast.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast date of this vw customer gts forecast.
	*
	* @param forecastDate the forecast date of this vw customer gts forecast
	*/
	@Override
	public void setForecastDate(Date forecastDate) {
		_vwCustomerGtsForecast.setForecastDate(forecastDate);
	}

	/**
	* Sets the forecast month of this vw customer gts forecast.
	*
	* @param forecastMonth the forecast month of this vw customer gts forecast
	*/
	@Override
	public void setForecastMonth(java.lang.String forecastMonth) {
		_vwCustomerGtsForecast.setForecastMonth(forecastMonth);
	}

	/**
	* Sets the forecast name of this vw customer gts forecast.
	*
	* @param forecastName the forecast name of this vw customer gts forecast
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_vwCustomerGtsForecast.setForecastName(forecastName);
	}

	/**
	* Sets the forecast value type of this vw customer gts forecast.
	*
	* @param forecastValueType the forecast value type of this vw customer gts forecast
	*/
	@Override
	public void setForecastValueType(java.lang.String forecastValueType) {
		_vwCustomerGtsForecast.setForecastValueType(forecastValueType);
	}

	/**
	* Sets the forecast ver of this vw customer gts forecast.
	*
	* @param forecastVer the forecast ver of this vw customer gts forecast
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_vwCustomerGtsForecast.setForecastVer(forecastVer);
	}

	/**
	* Sets the forecast year of this vw customer gts forecast.
	*
	* @param forecastYear the forecast year of this vw customer gts forecast
	*/
	@Override
	public void setForecastYear(java.lang.String forecastYear) {
		_vwCustomerGtsForecast.setForecastYear(forecastYear);
	}

	/**
	* Sets the item ID of this vw customer gts forecast.
	*
	* @param itemId the item ID of this vw customer gts forecast
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwCustomerGtsForecast.setItemId(itemId);
	}

	/**
	* Sets the modified by of this vw customer gts forecast.
	*
	* @param modifiedBy the modified by of this vw customer gts forecast
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwCustomerGtsForecast.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw customer gts forecast.
	*
	* @param modifiedDate the modified date of this vw customer gts forecast
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwCustomerGtsForecast.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_vwCustomerGtsForecast.setNew(n);
	}

	/**
	* Sets the price of this vw customer gts forecast.
	*
	* @param price the price of this vw customer gts forecast
	*/
	@Override
	public void setPrice(double price) {
		_vwCustomerGtsForecast.setPrice(price);
	}

	/**
	* Sets the price type of this vw customer gts forecast.
	*
	* @param priceType the price type of this vw customer gts forecast
	*/
	@Override
	public void setPriceType(java.lang.String priceType) {
		_vwCustomerGtsForecast.setPriceType(priceType);
	}

	/**
	* Sets the primary key of this vw customer gts forecast.
	*
	* @param primaryKey the primary key of this vw customer gts forecast
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwCustomerGtsForecast.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwCustomerGtsForecast.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sales amount of this vw customer gts forecast.
	*
	* @param salesAmount the sales amount of this vw customer gts forecast
	*/
	@Override
	public void setSalesAmount(double salesAmount) {
		_vwCustomerGtsForecast.setSalesAmount(salesAmount);
	}

	/**
	* Sets the sales inclusion of this vw customer gts forecast.
	*
	* @param salesInclusion the sales inclusion of this vw customer gts forecast
	*/
	@Override
	public void setSalesInclusion(java.lang.String salesInclusion) {
		_vwCustomerGtsForecast.setSalesInclusion(salesInclusion);
	}

	/**
	* Sets the segment of this vw customer gts forecast.
	*
	* @param segment the segment of this vw customer gts forecast
	*/
	@Override
	public void setSegment(java.lang.String segment) {
		_vwCustomerGtsForecast.setSegment(segment);
	}

	/**
	* Sets the source of this vw customer gts forecast.
	*
	* @param source the source of this vw customer gts forecast
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwCustomerGtsForecast.setSource(source);
	}

	/**
	* Sets the udc1 of this vw customer gts forecast.
	*
	* @param udc1 the udc1 of this vw customer gts forecast
	*/
	@Override
	public void setUdc1(java.lang.String udc1) {
		_vwCustomerGtsForecast.setUdc1(udc1);
	}

	/**
	* Sets the udc2 of this vw customer gts forecast.
	*
	* @param udc2 the udc2 of this vw customer gts forecast
	*/
	@Override
	public void setUdc2(java.lang.String udc2) {
		_vwCustomerGtsForecast.setUdc2(udc2);
	}

	/**
	* Sets the udc3 of this vw customer gts forecast.
	*
	* @param udc3 the udc3 of this vw customer gts forecast
	*/
	@Override
	public void setUdc3(java.lang.String udc3) {
		_vwCustomerGtsForecast.setUdc3(udc3);
	}

	/**
	* Sets the udc4 of this vw customer gts forecast.
	*
	* @param udc4 the udc4 of this vw customer gts forecast
	*/
	@Override
	public void setUdc4(java.lang.String udc4) {
		_vwCustomerGtsForecast.setUdc4(udc4);
	}

	/**
	* Sets the udc5 of this vw customer gts forecast.
	*
	* @param udc5 the udc5 of this vw customer gts forecast
	*/
	@Override
	public void setUdc5(java.lang.String udc5) {
		_vwCustomerGtsForecast.setUdc5(udc5);
	}

	/**
	* Sets the udc6 of this vw customer gts forecast.
	*
	* @param udc6 the udc6 of this vw customer gts forecast
	*/
	@Override
	public void setUdc6(java.lang.String udc6) {
		_vwCustomerGtsForecast.setUdc6(udc6);
	}

	/**
	* Sets the units of this vw customer gts forecast.
	*
	* @param units the units of this vw customer gts forecast
	*/
	@Override
	public void setUnits(double units) {
		_vwCustomerGtsForecast.setUnits(units);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwCustomerGtsForecast> toCacheModel() {
		return _vwCustomerGtsForecast.toCacheModel();
	}

	@Override
	public VwCustomerGtsForecast toEscapedModel() {
		return new VwCustomerGtsForecastWrapper(_vwCustomerGtsForecast.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwCustomerGtsForecast.toString();
	}

	@Override
	public VwCustomerGtsForecast toUnescapedModel() {
		return new VwCustomerGtsForecastWrapper(_vwCustomerGtsForecast.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwCustomerGtsForecast.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwCustomerGtsForecastWrapper)) {
			return false;
		}

		VwCustomerGtsForecastWrapper vwCustomerGtsForecastWrapper = (VwCustomerGtsForecastWrapper)obj;

		if (Objects.equals(_vwCustomerGtsForecast,
					vwCustomerGtsForecastWrapper._vwCustomerGtsForecast)) {
			return true;
		}

		return false;
	}

	@Override
	public VwCustomerGtsForecast getWrappedModel() {
		return _vwCustomerGtsForecast;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwCustomerGtsForecast.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwCustomerGtsForecast.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwCustomerGtsForecast.resetOriginalValues();
	}

	private final VwCustomerGtsForecast _vwCustomerGtsForecast;
}