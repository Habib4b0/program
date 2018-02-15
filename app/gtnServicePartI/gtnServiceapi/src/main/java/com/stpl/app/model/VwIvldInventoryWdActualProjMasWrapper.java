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
 * This class is a wrapper for {@link VwIvldInventoryWdActualProjMas}.
 * </p>
 *
 * @author
 * @see VwIvldInventoryWdActualProjMas
 * @generated
 */
@ProviderType
public class VwIvldInventoryWdActualProjMasWrapper
	implements VwIvldInventoryWdActualProjMas,
		ModelWrapper<VwIvldInventoryWdActualProjMas> {
	public VwIvldInventoryWdActualProjMasWrapper(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		_vwIvldInventoryWdActualProjMas = vwIvldInventoryWdActualProjMas;
	}

	@Override
	public Class<?> getModelClass() {
		return VwIvldInventoryWdActualProjMas.class;
	}

	@Override
	public String getModelClassName() {
		return VwIvldInventoryWdActualProjMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ivldInventoryWdActualProjMasSid",
			getIvldInventoryWdActualProjMasSid());
		attributes.put("quantityOnOrder", getQuantityOnOrder());
		attributes.put("week", getWeek());
		attributes.put("price", getPrice());
		attributes.put("amountOnHand", getAmountOnHand());
		attributes.put("isMaster", getIsMaster());
		attributes.put("companyName", getCompanyName());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("day", getDay());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("unitsOnHand", getUnitsOnHand());
		attributes.put("amountReceived", getAmountReceived());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("month", getMonth());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("amountWithdrawn", getAmountWithdrawn());
		attributes.put("quantityReceived", getQuantityReceived());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("country", getCountry());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("isForecast", getIsForecast());
		attributes.put("inventoryWdActualProjMasIntfId",
			getInventoryWdActualProjMasIntfId());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("itemName", getItemName());
		attributes.put("errorField", getErrorField());
		attributes.put("amountOnOrder", getAmountOnOrder());
		attributes.put("forecastName", getForecastName());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer ivldInventoryWdActualProjMasSid = (Integer)attributes.get(
				"ivldInventoryWdActualProjMasSid");

		if (ivldInventoryWdActualProjMasSid != null) {
			setIvldInventoryWdActualProjMasSid(ivldInventoryWdActualProjMasSid);
		}

		String quantityOnOrder = (String)attributes.get("quantityOnOrder");

		if (quantityOnOrder != null) {
			setQuantityOnOrder(quantityOnOrder);
		}

		String week = (String)attributes.get("week");

		if (week != null) {
			setWeek(week);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		String amountOnHand = (String)attributes.get("amountOnHand");

		if (amountOnHand != null) {
			setAmountOnHand(amountOnHand);
		}

		String isMaster = (String)attributes.get("isMaster");

		if (isMaster != null) {
			setIsMaster(isMaster);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
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

		String organizationKey = (String)attributes.get("organizationKey");

		if (organizationKey != null) {
			setOrganizationKey(organizationKey);
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

		String day = (String)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String unitsOnHand = (String)attributes.get("unitsOnHand");

		if (unitsOnHand != null) {
			setUnitsOnHand(unitsOnHand);
		}

		String amountReceived = (String)attributes.get("amountReceived");

		if (amountReceived != null) {
			setAmountReceived(amountReceived);
		}

		String errorCode = (String)attributes.get("errorCode");

		if (errorCode != null) {
			setErrorCode(errorCode);
		}

		Date intfInsertedDate = (Date)attributes.get("intfInsertedDate");

		if (intfInsertedDate != null) {
			setIntfInsertedDate(intfInsertedDate);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String amountWithdrawn = (String)attributes.get("amountWithdrawn");

		if (amountWithdrawn != null) {
			setAmountWithdrawn(amountWithdrawn);
		}

		String quantityReceived = (String)attributes.get("quantityReceived");

		if (quantityReceived != null) {
			setQuantityReceived(quantityReceived);
		}

		String unitsWithdrawn = (String)attributes.get("unitsWithdrawn");

		if (unitsWithdrawn != null) {
			setUnitsWithdrawn(unitsWithdrawn);
		}

		String reasonForFailure = (String)attributes.get("reasonForFailure");

		if (reasonForFailure != null) {
			setReasonForFailure(reasonForFailure);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
		}

		String companyStringId = (String)attributes.get("companyStringId");

		if (companyStringId != null) {
			setCompanyStringId(companyStringId);
		}

		String isForecast = (String)attributes.get("isForecast");

		if (isForecast != null) {
			setIsForecast(isForecast);
		}

		String inventoryWdActualProjMasIntfId = (String)attributes.get(
				"inventoryWdActualProjMasIntfId");

		if (inventoryWdActualProjMasIntfId != null) {
			setInventoryWdActualProjMasIntfId(inventoryWdActualProjMasIntfId);
		}

		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String itemName = (String)attributes.get("itemName");

		if (itemName != null) {
			setItemName(itemName);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String amountOnOrder = (String)attributes.get("amountOnOrder");

		if (amountOnOrder != null) {
			setAmountOnOrder(amountOnOrder);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwIvldInventoryWdActualProjMasWrapper((VwIvldInventoryWdActualProjMas)_vwIvldInventoryWdActualProjMas.clone());
	}

	@Override
	public int compareTo(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		return _vwIvldInventoryWdActualProjMas.compareTo(vwIvldInventoryWdActualProjMas);
	}

	/**
	* Returns the add chg del indicator of this vw ivld inventory wd actual proj mas.
	*
	* @return the add chg del indicator of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwIvldInventoryWdActualProjMas.getAddChgDelIndicator();
	}

	/**
	* Returns the amount on hand of this vw ivld inventory wd actual proj mas.
	*
	* @return the amount on hand of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAmountOnHand() {
		return _vwIvldInventoryWdActualProjMas.getAmountOnHand();
	}

	/**
	* Returns the amount on order of this vw ivld inventory wd actual proj mas.
	*
	* @return the amount on order of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAmountOnOrder() {
		return _vwIvldInventoryWdActualProjMas.getAmountOnOrder();
	}

	/**
	* Returns the amount received of this vw ivld inventory wd actual proj mas.
	*
	* @return the amount received of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAmountReceived() {
		return _vwIvldInventoryWdActualProjMas.getAmountReceived();
	}

	/**
	* Returns the amount withdrawn of this vw ivld inventory wd actual proj mas.
	*
	* @return the amount withdrawn of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAmountWithdrawn() {
		return _vwIvldInventoryWdActualProjMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this vw ivld inventory wd actual proj mas.
	*
	* @return the batch ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwIvldInventoryWdActualProjMas.getBatchId();
	}

	/**
	* Returns the check record of this vw ivld inventory wd actual proj mas.
	*
	* @return the check record of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public boolean getCheckRecord() {
		return _vwIvldInventoryWdActualProjMas.getCheckRecord();
	}

	/**
	* Returns the company name of this vw ivld inventory wd actual proj mas.
	*
	* @return the company name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _vwIvldInventoryWdActualProjMas.getCompanyName();
	}

	/**
	* Returns the company string ID of this vw ivld inventory wd actual proj mas.
	*
	* @return the company string ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _vwIvldInventoryWdActualProjMas.getCompanyStringId();
	}

	/**
	* Returns the country of this vw ivld inventory wd actual proj mas.
	*
	* @return the country of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwIvldInventoryWdActualProjMas.getCountry();
	}

	/**
	* Returns the created by of this vw ivld inventory wd actual proj mas.
	*
	* @return the created by of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwIvldInventoryWdActualProjMas.getCreatedBy();
	}

	/**
	* Returns the created date of this vw ivld inventory wd actual proj mas.
	*
	* @return the created date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public Date getCreatedDate() {
		return _vwIvldInventoryWdActualProjMas.getCreatedDate();
	}

	/**
	* Returns the day of this vw ivld inventory wd actual proj mas.
	*
	* @return the day of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getDay() {
		return _vwIvldInventoryWdActualProjMas.getDay();
	}

	/**
	* Returns the error code of this vw ivld inventory wd actual proj mas.
	*
	* @return the error code of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _vwIvldInventoryWdActualProjMas.getErrorCode();
	}

	/**
	* Returns the error field of this vw ivld inventory wd actual proj mas.
	*
	* @return the error field of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getErrorField() {
		return _vwIvldInventoryWdActualProjMas.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwIvldInventoryWdActualProjMas.getExpandoBridge();
	}

	/**
	* Returns the forecast name of this vw ivld inventory wd actual proj mas.
	*
	* @return the forecast name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getForecastName() {
		return _vwIvldInventoryWdActualProjMas.getForecastName();
	}

	/**
	* Returns the forecast ver of this vw ivld inventory wd actual proj mas.
	*
	* @return the forecast ver of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _vwIvldInventoryWdActualProjMas.getForecastVer();
	}

	/**
	* Returns the intf inserted date of this vw ivld inventory wd actual proj mas.
	*
	* @return the intf inserted date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _vwIvldInventoryWdActualProjMas.getIntfInsertedDate();
	}

	/**
	* Returns the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas.
	*
	* @return the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getInventoryWdActualProjMasIntfId() {
		return _vwIvldInventoryWdActualProjMas.getInventoryWdActualProjMasIntfId();
	}

	/**
	* Returns the is forecast of this vw ivld inventory wd actual proj mas.
	*
	* @return the is forecast of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getIsForecast() {
		return _vwIvldInventoryWdActualProjMas.getIsForecast();
	}

	/**
	* Returns the is master of this vw ivld inventory wd actual proj mas.
	*
	* @return the is master of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getIsMaster() {
		return _vwIvldInventoryWdActualProjMas.getIsMaster();
	}

	/**
	* Returns the item ID of this vw ivld inventory wd actual proj mas.
	*
	* @return the item ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwIvldInventoryWdActualProjMas.getItemId();
	}

	/**
	* Returns the item name of this vw ivld inventory wd actual proj mas.
	*
	* @return the item name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwIvldInventoryWdActualProjMas.getItemName();
	}

	/**
	* Returns the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas.
	*
	* @return the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public int getIvldInventoryWdActualProjMasSid() {
		return _vwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid();
	}

	/**
	* Returns the modified by of this vw ivld inventory wd actual proj mas.
	*
	* @return the modified by of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwIvldInventoryWdActualProjMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw ivld inventory wd actual proj mas.
	*
	* @return the modified date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public Date getModifiedDate() {
		return _vwIvldInventoryWdActualProjMas.getModifiedDate();
	}

	/**
	* Returns the month of this vw ivld inventory wd actual proj mas.
	*
	* @return the month of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _vwIvldInventoryWdActualProjMas.getMonth();
	}

	/**
	* Returns the organization key of this vw ivld inventory wd actual proj mas.
	*
	* @return the organization key of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _vwIvldInventoryWdActualProjMas.getOrganizationKey();
	}

	/**
	* Returns the price of this vw ivld inventory wd actual proj mas.
	*
	* @return the price of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public double getPrice() {
		return _vwIvldInventoryWdActualProjMas.getPrice();
	}

	/**
	* Returns the primary key of this vw ivld inventory wd actual proj mas.
	*
	* @return the primary key of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public int getPrimaryKey() {
		return _vwIvldInventoryWdActualProjMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwIvldInventoryWdActualProjMas.getPrimaryKeyObj();
	}

	/**
	* Returns the quantity on order of this vw ivld inventory wd actual proj mas.
	*
	* @return the quantity on order of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getQuantityOnOrder() {
		return _vwIvldInventoryWdActualProjMas.getQuantityOnOrder();
	}

	/**
	* Returns the quantity received of this vw ivld inventory wd actual proj mas.
	*
	* @return the quantity received of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getQuantityReceived() {
		return _vwIvldInventoryWdActualProjMas.getQuantityReceived();
	}

	/**
	* Returns the reason for failure of this vw ivld inventory wd actual proj mas.
	*
	* @return the reason for failure of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _vwIvldInventoryWdActualProjMas.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this vw ivld inventory wd actual proj mas.
	*
	* @return the reprocessed flag of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _vwIvldInventoryWdActualProjMas.getReprocessedFlag();
	}

	/**
	* Returns the source of this vw ivld inventory wd actual proj mas.
	*
	* @return the source of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getSource() {
		return _vwIvldInventoryWdActualProjMas.getSource();
	}

	/**
	* Returns the units on hand of this vw ivld inventory wd actual proj mas.
	*
	* @return the units on hand of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getUnitsOnHand() {
		return _vwIvldInventoryWdActualProjMas.getUnitsOnHand();
	}

	/**
	* Returns the units withdrawn of this vw ivld inventory wd actual proj mas.
	*
	* @return the units withdrawn of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getUnitsWithdrawn() {
		return _vwIvldInventoryWdActualProjMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this vw ivld inventory wd actual proj mas.
	*
	* @return the week of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _vwIvldInventoryWdActualProjMas.getWeek();
	}

	/**
	* Returns the year of this vw ivld inventory wd actual proj mas.
	*
	* @return the year of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getYear() {
		return _vwIvldInventoryWdActualProjMas.getYear();
	}

	@Override
	public int hashCode() {
		return _vwIvldInventoryWdActualProjMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwIvldInventoryWdActualProjMas.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this vw ivld inventory wd actual proj mas is check record.
	*
	* @return <code>true</code> if this vw ivld inventory wd actual proj mas is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _vwIvldInventoryWdActualProjMas.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwIvldInventoryWdActualProjMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwIvldInventoryWdActualProjMas.isNew();
	}

	@Override
	public void persist() {
		_vwIvldInventoryWdActualProjMas.persist();
	}

	/**
	* Sets the add chg del indicator of this vw ivld inventory wd actual proj mas.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwIvldInventoryWdActualProjMas.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount on hand of this vw ivld inventory wd actual proj mas.
	*
	* @param amountOnHand the amount on hand of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setAmountOnHand(java.lang.String amountOnHand) {
		_vwIvldInventoryWdActualProjMas.setAmountOnHand(amountOnHand);
	}

	/**
	* Sets the amount on order of this vw ivld inventory wd actual proj mas.
	*
	* @param amountOnOrder the amount on order of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setAmountOnOrder(java.lang.String amountOnOrder) {
		_vwIvldInventoryWdActualProjMas.setAmountOnOrder(amountOnOrder);
	}

	/**
	* Sets the amount received of this vw ivld inventory wd actual proj mas.
	*
	* @param amountReceived the amount received of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setAmountReceived(java.lang.String amountReceived) {
		_vwIvldInventoryWdActualProjMas.setAmountReceived(amountReceived);
	}

	/**
	* Sets the amount withdrawn of this vw ivld inventory wd actual proj mas.
	*
	* @param amountWithdrawn the amount withdrawn of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
		_vwIvldInventoryWdActualProjMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this vw ivld inventory wd actual proj mas.
	*
	* @param batchId the batch ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwIvldInventoryWdActualProjMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwIvldInventoryWdActualProjMas.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this vw ivld inventory wd actual proj mas is check record.
	*
	* @param checkRecord the check record of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_vwIvldInventoryWdActualProjMas.setCheckRecord(checkRecord);
	}

	/**
	* Sets the company name of this vw ivld inventory wd actual proj mas.
	*
	* @param companyName the company name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_vwIvldInventoryWdActualProjMas.setCompanyName(companyName);
	}

	/**
	* Sets the company string ID of this vw ivld inventory wd actual proj mas.
	*
	* @param companyStringId the company string ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_vwIvldInventoryWdActualProjMas.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the country of this vw ivld inventory wd actual proj mas.
	*
	* @param country the country of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwIvldInventoryWdActualProjMas.setCountry(country);
	}

	/**
	* Sets the created by of this vw ivld inventory wd actual proj mas.
	*
	* @param createdBy the created by of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwIvldInventoryWdActualProjMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw ivld inventory wd actual proj mas.
	*
	* @param createdDate the created date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwIvldInventoryWdActualProjMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this vw ivld inventory wd actual proj mas.
	*
	* @param day the day of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_vwIvldInventoryWdActualProjMas.setDay(day);
	}

	/**
	* Sets the error code of this vw ivld inventory wd actual proj mas.
	*
	* @param errorCode the error code of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_vwIvldInventoryWdActualProjMas.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this vw ivld inventory wd actual proj mas.
	*
	* @param errorField the error field of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_vwIvldInventoryWdActualProjMas.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwIvldInventoryWdActualProjMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwIvldInventoryWdActualProjMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwIvldInventoryWdActualProjMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast name of this vw ivld inventory wd actual proj mas.
	*
	* @param forecastName the forecast name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_vwIvldInventoryWdActualProjMas.setForecastName(forecastName);
	}

	/**
	* Sets the forecast ver of this vw ivld inventory wd actual proj mas.
	*
	* @param forecastVer the forecast ver of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_vwIvldInventoryWdActualProjMas.setForecastVer(forecastVer);
	}

	/**
	* Sets the intf inserted date of this vw ivld inventory wd actual proj mas.
	*
	* @param intfInsertedDate the intf inserted date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_vwIvldInventoryWdActualProjMas.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas.
	*
	* @param inventoryWdActualProjMasIntfId the inventory wd actual proj mas intf ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setInventoryWdActualProjMasIntfId(
		java.lang.String inventoryWdActualProjMasIntfId) {
		_vwIvldInventoryWdActualProjMas.setInventoryWdActualProjMasIntfId(inventoryWdActualProjMasIntfId);
	}

	/**
	* Sets the is forecast of this vw ivld inventory wd actual proj mas.
	*
	* @param isForecast the is forecast of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setIsForecast(java.lang.String isForecast) {
		_vwIvldInventoryWdActualProjMas.setIsForecast(isForecast);
	}

	/**
	* Sets the is master of this vw ivld inventory wd actual proj mas.
	*
	* @param isMaster the is master of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setIsMaster(java.lang.String isMaster) {
		_vwIvldInventoryWdActualProjMas.setIsMaster(isMaster);
	}

	/**
	* Sets the item ID of this vw ivld inventory wd actual proj mas.
	*
	* @param itemId the item ID of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwIvldInventoryWdActualProjMas.setItemId(itemId);
	}

	/**
	* Sets the item name of this vw ivld inventory wd actual proj mas.
	*
	* @param itemName the item name of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwIvldInventoryWdActualProjMas.setItemName(itemName);
	}

	/**
	* Sets the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas.
	*
	* @param ivldInventoryWdActualProjMasSid the ivld inventory wd actual proj mas sid of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setIvldInventoryWdActualProjMasSid(
		int ivldInventoryWdActualProjMasSid) {
		_vwIvldInventoryWdActualProjMas.setIvldInventoryWdActualProjMasSid(ivldInventoryWdActualProjMasSid);
	}

	/**
	* Sets the modified by of this vw ivld inventory wd actual proj mas.
	*
	* @param modifiedBy the modified by of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwIvldInventoryWdActualProjMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw ivld inventory wd actual proj mas.
	*
	* @param modifiedDate the modified date of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwIvldInventoryWdActualProjMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this vw ivld inventory wd actual proj mas.
	*
	* @param month the month of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_vwIvldInventoryWdActualProjMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_vwIvldInventoryWdActualProjMas.setNew(n);
	}

	/**
	* Sets the organization key of this vw ivld inventory wd actual proj mas.
	*
	* @param organizationKey the organization key of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_vwIvldInventoryWdActualProjMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the price of this vw ivld inventory wd actual proj mas.
	*
	* @param price the price of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setPrice(double price) {
		_vwIvldInventoryWdActualProjMas.setPrice(price);
	}

	/**
	* Sets the primary key of this vw ivld inventory wd actual proj mas.
	*
	* @param primaryKey the primary key of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwIvldInventoryWdActualProjMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwIvldInventoryWdActualProjMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity on order of this vw ivld inventory wd actual proj mas.
	*
	* @param quantityOnOrder the quantity on order of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setQuantityOnOrder(java.lang.String quantityOnOrder) {
		_vwIvldInventoryWdActualProjMas.setQuantityOnOrder(quantityOnOrder);
	}

	/**
	* Sets the quantity received of this vw ivld inventory wd actual proj mas.
	*
	* @param quantityReceived the quantity received of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setQuantityReceived(java.lang.String quantityReceived) {
		_vwIvldInventoryWdActualProjMas.setQuantityReceived(quantityReceived);
	}

	/**
	* Sets the reason for failure of this vw ivld inventory wd actual proj mas.
	*
	* @param reasonForFailure the reason for failure of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_vwIvldInventoryWdActualProjMas.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this vw ivld inventory wd actual proj mas.
	*
	* @param reprocessedFlag the reprocessed flag of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_vwIvldInventoryWdActualProjMas.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this vw ivld inventory wd actual proj mas.
	*
	* @param source the source of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwIvldInventoryWdActualProjMas.setSource(source);
	}

	/**
	* Sets the units on hand of this vw ivld inventory wd actual proj mas.
	*
	* @param unitsOnHand the units on hand of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setUnitsOnHand(java.lang.String unitsOnHand) {
		_vwIvldInventoryWdActualProjMas.setUnitsOnHand(unitsOnHand);
	}

	/**
	* Sets the units withdrawn of this vw ivld inventory wd actual proj mas.
	*
	* @param unitsWithdrawn the units withdrawn of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
		_vwIvldInventoryWdActualProjMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this vw ivld inventory wd actual proj mas.
	*
	* @param week the week of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_vwIvldInventoryWdActualProjMas.setWeek(week);
	}

	/**
	* Sets the year of this vw ivld inventory wd actual proj mas.
	*
	* @param year the year of this vw ivld inventory wd actual proj mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_vwIvldInventoryWdActualProjMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwIvldInventoryWdActualProjMas> toCacheModel() {
		return _vwIvldInventoryWdActualProjMas.toCacheModel();
	}

	@Override
	public VwIvldInventoryWdActualProjMas toEscapedModel() {
		return new VwIvldInventoryWdActualProjMasWrapper(_vwIvldInventoryWdActualProjMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwIvldInventoryWdActualProjMas.toString();
	}

	@Override
	public VwIvldInventoryWdActualProjMas toUnescapedModel() {
		return new VwIvldInventoryWdActualProjMasWrapper(_vwIvldInventoryWdActualProjMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwIvldInventoryWdActualProjMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwIvldInventoryWdActualProjMasWrapper)) {
			return false;
		}

		VwIvldInventoryWdActualProjMasWrapper vwIvldInventoryWdActualProjMasWrapper =
			(VwIvldInventoryWdActualProjMasWrapper)obj;

		if (Objects.equals(_vwIvldInventoryWdActualProjMas,
					vwIvldInventoryWdActualProjMasWrapper._vwIvldInventoryWdActualProjMas)) {
			return true;
		}

		return false;
	}

	@Override
	public VwIvldInventoryWdActualProjMas getWrappedModel() {
		return _vwIvldInventoryWdActualProjMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwIvldInventoryWdActualProjMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwIvldInventoryWdActualProjMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwIvldInventoryWdActualProjMas.resetOriginalValues();
	}

	private final VwIvldInventoryWdActualProjMas _vwIvldInventoryWdActualProjMas;
}