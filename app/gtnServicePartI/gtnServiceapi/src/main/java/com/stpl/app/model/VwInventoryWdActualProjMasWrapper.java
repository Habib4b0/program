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
 * This class is a wrapper for {@link VwInventoryWdActualProjMas}.
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMas
 * @generated
 */
@ProviderType
public class VwInventoryWdActualProjMasWrapper
	implements VwInventoryWdActualProjMas,
		ModelWrapper<VwInventoryWdActualProjMas> {
	public VwInventoryWdActualProjMasWrapper(
		VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		_vwInventoryWdActualProjMas = vwInventoryWdActualProjMas;
	}

	@Override
	public Class<?> getModelClass() {
		return VwInventoryWdActualProjMas.class;
	}

	@Override
	public String getModelClassName() {
		return VwInventoryWdActualProjMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

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
		attributes.put("inventoryWdActualProjMasSid",
			getInventoryWdActualProjMasSid());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("day", getDay());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("unitsOnHand", getUnitsOnHand());
		attributes.put("amountReceived", getAmountReceived());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("month", getMonth());
		attributes.put("amountWithdrawn", getAmountWithdrawn());
		attributes.put("quantityReceived", getQuantityReceived());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("country", getCountry());
		attributes.put("companyStringId", getCompanyStringId());
		attributes.put("isForecast", getIsForecast());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("itemName", getItemName());
		attributes.put("amountOnOrder", getAmountOnOrder());
		attributes.put("forecastName", getForecastName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Double quantityOnOrder = (Double)attributes.get("quantityOnOrder");

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

		Double amountOnHand = (Double)attributes.get("amountOnHand");

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

		Integer inventoryWdActualProjMasSid = (Integer)attributes.get(
				"inventoryWdActualProjMasSid");

		if (inventoryWdActualProjMasSid != null) {
			setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);
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

		Double unitsOnHand = (Double)attributes.get("unitsOnHand");

		if (unitsOnHand != null) {
			setUnitsOnHand(unitsOnHand);
		}

		Double amountReceived = (Double)attributes.get("amountReceived");

		if (amountReceived != null) {
			setAmountReceived(amountReceived);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		Double amountWithdrawn = (Double)attributes.get("amountWithdrawn");

		if (amountWithdrawn != null) {
			setAmountWithdrawn(amountWithdrawn);
		}

		Double quantityReceived = (Double)attributes.get("quantityReceived");

		if (quantityReceived != null) {
			setQuantityReceived(quantityReceived);
		}

		Double unitsWithdrawn = (Double)attributes.get("unitsWithdrawn");

		if (unitsWithdrawn != null) {
			setUnitsWithdrawn(unitsWithdrawn);
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

		Double amountOnOrder = (Double)attributes.get("amountOnOrder");

		if (amountOnOrder != null) {
			setAmountOnOrder(amountOnOrder);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new VwInventoryWdActualProjMasWrapper((VwInventoryWdActualProjMas)_vwInventoryWdActualProjMas.clone());
	}

	@Override
	public int compareTo(VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		return _vwInventoryWdActualProjMas.compareTo(vwInventoryWdActualProjMas);
	}

	/**
	* Returns the add chg del indicator of this vw inventory wd actual proj mas.
	*
	* @return the add chg del indicator of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _vwInventoryWdActualProjMas.getAddChgDelIndicator();
	}

	/**
	* Returns the amount on hand of this vw inventory wd actual proj mas.
	*
	* @return the amount on hand of this vw inventory wd actual proj mas
	*/
	@Override
	public double getAmountOnHand() {
		return _vwInventoryWdActualProjMas.getAmountOnHand();
	}

	/**
	* Returns the amount on order of this vw inventory wd actual proj mas.
	*
	* @return the amount on order of this vw inventory wd actual proj mas
	*/
	@Override
	public double getAmountOnOrder() {
		return _vwInventoryWdActualProjMas.getAmountOnOrder();
	}

	/**
	* Returns the amount received of this vw inventory wd actual proj mas.
	*
	* @return the amount received of this vw inventory wd actual proj mas
	*/
	@Override
	public double getAmountReceived() {
		return _vwInventoryWdActualProjMas.getAmountReceived();
	}

	/**
	* Returns the amount withdrawn of this vw inventory wd actual proj mas.
	*
	* @return the amount withdrawn of this vw inventory wd actual proj mas
	*/
	@Override
	public double getAmountWithdrawn() {
		return _vwInventoryWdActualProjMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this vw inventory wd actual proj mas.
	*
	* @return the batch ID of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _vwInventoryWdActualProjMas.getBatchId();
	}

	/**
	* Returns the company name of this vw inventory wd actual proj mas.
	*
	* @return the company name of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCompanyName() {
		return _vwInventoryWdActualProjMas.getCompanyName();
	}

	/**
	* Returns the company string ID of this vw inventory wd actual proj mas.
	*
	* @return the company string ID of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCompanyStringId() {
		return _vwInventoryWdActualProjMas.getCompanyStringId();
	}

	/**
	* Returns the country of this vw inventory wd actual proj mas.
	*
	* @return the country of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _vwInventoryWdActualProjMas.getCountry();
	}

	/**
	* Returns the created by of this vw inventory wd actual proj mas.
	*
	* @return the created by of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _vwInventoryWdActualProjMas.getCreatedBy();
	}

	/**
	* Returns the created date of this vw inventory wd actual proj mas.
	*
	* @return the created date of this vw inventory wd actual proj mas
	*/
	@Override
	public Date getCreatedDate() {
		return _vwInventoryWdActualProjMas.getCreatedDate();
	}

	/**
	* Returns the day of this vw inventory wd actual proj mas.
	*
	* @return the day of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getDay() {
		return _vwInventoryWdActualProjMas.getDay();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _vwInventoryWdActualProjMas.getExpandoBridge();
	}

	/**
	* Returns the forecast name of this vw inventory wd actual proj mas.
	*
	* @return the forecast name of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getForecastName() {
		return _vwInventoryWdActualProjMas.getForecastName();
	}

	/**
	* Returns the forecast ver of this vw inventory wd actual proj mas.
	*
	* @return the forecast ver of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _vwInventoryWdActualProjMas.getForecastVer();
	}

	/**
	* Returns the inventory wd actual proj mas sid of this vw inventory wd actual proj mas.
	*
	* @return the inventory wd actual proj mas sid of this vw inventory wd actual proj mas
	*/
	@Override
	public int getInventoryWdActualProjMasSid() {
		return _vwInventoryWdActualProjMas.getInventoryWdActualProjMasSid();
	}

	/**
	* Returns the is forecast of this vw inventory wd actual proj mas.
	*
	* @return the is forecast of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getIsForecast() {
		return _vwInventoryWdActualProjMas.getIsForecast();
	}

	/**
	* Returns the is master of this vw inventory wd actual proj mas.
	*
	* @return the is master of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getIsMaster() {
		return _vwInventoryWdActualProjMas.getIsMaster();
	}

	/**
	* Returns the item ID of this vw inventory wd actual proj mas.
	*
	* @return the item ID of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _vwInventoryWdActualProjMas.getItemId();
	}

	/**
	* Returns the item name of this vw inventory wd actual proj mas.
	*
	* @return the item name of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getItemName() {
		return _vwInventoryWdActualProjMas.getItemName();
	}

	/**
	* Returns the modified by of this vw inventory wd actual proj mas.
	*
	* @return the modified by of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _vwInventoryWdActualProjMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this vw inventory wd actual proj mas.
	*
	* @return the modified date of this vw inventory wd actual proj mas
	*/
	@Override
	public Date getModifiedDate() {
		return _vwInventoryWdActualProjMas.getModifiedDate();
	}

	/**
	* Returns the month of this vw inventory wd actual proj mas.
	*
	* @return the month of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _vwInventoryWdActualProjMas.getMonth();
	}

	/**
	* Returns the organization key of this vw inventory wd actual proj mas.
	*
	* @return the organization key of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _vwInventoryWdActualProjMas.getOrganizationKey();
	}

	/**
	* Returns the price of this vw inventory wd actual proj mas.
	*
	* @return the price of this vw inventory wd actual proj mas
	*/
	@Override
	public double getPrice() {
		return _vwInventoryWdActualProjMas.getPrice();
	}

	/**
	* Returns the primary key of this vw inventory wd actual proj mas.
	*
	* @return the primary key of this vw inventory wd actual proj mas
	*/
	@Override
	public int getPrimaryKey() {
		return _vwInventoryWdActualProjMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _vwInventoryWdActualProjMas.getPrimaryKeyObj();
	}

	/**
	* Returns the quantity on order of this vw inventory wd actual proj mas.
	*
	* @return the quantity on order of this vw inventory wd actual proj mas
	*/
	@Override
	public double getQuantityOnOrder() {
		return _vwInventoryWdActualProjMas.getQuantityOnOrder();
	}

	/**
	* Returns the quantity received of this vw inventory wd actual proj mas.
	*
	* @return the quantity received of this vw inventory wd actual proj mas
	*/
	@Override
	public double getQuantityReceived() {
		return _vwInventoryWdActualProjMas.getQuantityReceived();
	}

	/**
	* Returns the source of this vw inventory wd actual proj mas.
	*
	* @return the source of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getSource() {
		return _vwInventoryWdActualProjMas.getSource();
	}

	/**
	* Returns the units on hand of this vw inventory wd actual proj mas.
	*
	* @return the units on hand of this vw inventory wd actual proj mas
	*/
	@Override
	public double getUnitsOnHand() {
		return _vwInventoryWdActualProjMas.getUnitsOnHand();
	}

	/**
	* Returns the units withdrawn of this vw inventory wd actual proj mas.
	*
	* @return the units withdrawn of this vw inventory wd actual proj mas
	*/
	@Override
	public double getUnitsWithdrawn() {
		return _vwInventoryWdActualProjMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this vw inventory wd actual proj mas.
	*
	* @return the week of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _vwInventoryWdActualProjMas.getWeek();
	}

	/**
	* Returns the year of this vw inventory wd actual proj mas.
	*
	* @return the year of this vw inventory wd actual proj mas
	*/
	@Override
	public java.lang.String getYear() {
		return _vwInventoryWdActualProjMas.getYear();
	}

	@Override
	public int hashCode() {
		return _vwInventoryWdActualProjMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _vwInventoryWdActualProjMas.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _vwInventoryWdActualProjMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _vwInventoryWdActualProjMas.isNew();
	}

	@Override
	public void persist() {
		_vwInventoryWdActualProjMas.persist();
	}

	/**
	* Sets the add chg del indicator of this vw inventory wd actual proj mas.
	*
	* @param addChgDelIndicator the add chg del indicator of this vw inventory wd actual proj mas
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_vwInventoryWdActualProjMas.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount on hand of this vw inventory wd actual proj mas.
	*
	* @param amountOnHand the amount on hand of this vw inventory wd actual proj mas
	*/
	@Override
	public void setAmountOnHand(double amountOnHand) {
		_vwInventoryWdActualProjMas.setAmountOnHand(amountOnHand);
	}

	/**
	* Sets the amount on order of this vw inventory wd actual proj mas.
	*
	* @param amountOnOrder the amount on order of this vw inventory wd actual proj mas
	*/
	@Override
	public void setAmountOnOrder(double amountOnOrder) {
		_vwInventoryWdActualProjMas.setAmountOnOrder(amountOnOrder);
	}

	/**
	* Sets the amount received of this vw inventory wd actual proj mas.
	*
	* @param amountReceived the amount received of this vw inventory wd actual proj mas
	*/
	@Override
	public void setAmountReceived(double amountReceived) {
		_vwInventoryWdActualProjMas.setAmountReceived(amountReceived);
	}

	/**
	* Sets the amount withdrawn of this vw inventory wd actual proj mas.
	*
	* @param amountWithdrawn the amount withdrawn of this vw inventory wd actual proj mas
	*/
	@Override
	public void setAmountWithdrawn(double amountWithdrawn) {
		_vwInventoryWdActualProjMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this vw inventory wd actual proj mas.
	*
	* @param batchId the batch ID of this vw inventory wd actual proj mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_vwInventoryWdActualProjMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_vwInventoryWdActualProjMas.setCachedModel(cachedModel);
	}

	/**
	* Sets the company name of this vw inventory wd actual proj mas.
	*
	* @param companyName the company name of this vw inventory wd actual proj mas
	*/
	@Override
	public void setCompanyName(java.lang.String companyName) {
		_vwInventoryWdActualProjMas.setCompanyName(companyName);
	}

	/**
	* Sets the company string ID of this vw inventory wd actual proj mas.
	*
	* @param companyStringId the company string ID of this vw inventory wd actual proj mas
	*/
	@Override
	public void setCompanyStringId(java.lang.String companyStringId) {
		_vwInventoryWdActualProjMas.setCompanyStringId(companyStringId);
	}

	/**
	* Sets the country of this vw inventory wd actual proj mas.
	*
	* @param country the country of this vw inventory wd actual proj mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_vwInventoryWdActualProjMas.setCountry(country);
	}

	/**
	* Sets the created by of this vw inventory wd actual proj mas.
	*
	* @param createdBy the created by of this vw inventory wd actual proj mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_vwInventoryWdActualProjMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this vw inventory wd actual proj mas.
	*
	* @param createdDate the created date of this vw inventory wd actual proj mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_vwInventoryWdActualProjMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this vw inventory wd actual proj mas.
	*
	* @param day the day of this vw inventory wd actual proj mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_vwInventoryWdActualProjMas.setDay(day);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_vwInventoryWdActualProjMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_vwInventoryWdActualProjMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_vwInventoryWdActualProjMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast name of this vw inventory wd actual proj mas.
	*
	* @param forecastName the forecast name of this vw inventory wd actual proj mas
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_vwInventoryWdActualProjMas.setForecastName(forecastName);
	}

	/**
	* Sets the forecast ver of this vw inventory wd actual proj mas.
	*
	* @param forecastVer the forecast ver of this vw inventory wd actual proj mas
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_vwInventoryWdActualProjMas.setForecastVer(forecastVer);
	}

	/**
	* Sets the inventory wd actual proj mas sid of this vw inventory wd actual proj mas.
	*
	* @param inventoryWdActualProjMasSid the inventory wd actual proj mas sid of this vw inventory wd actual proj mas
	*/
	@Override
	public void setInventoryWdActualProjMasSid(int inventoryWdActualProjMasSid) {
		_vwInventoryWdActualProjMas.setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);
	}

	/**
	* Sets the is forecast of this vw inventory wd actual proj mas.
	*
	* @param isForecast the is forecast of this vw inventory wd actual proj mas
	*/
	@Override
	public void setIsForecast(java.lang.String isForecast) {
		_vwInventoryWdActualProjMas.setIsForecast(isForecast);
	}

	/**
	* Sets the is master of this vw inventory wd actual proj mas.
	*
	* @param isMaster the is master of this vw inventory wd actual proj mas
	*/
	@Override
	public void setIsMaster(java.lang.String isMaster) {
		_vwInventoryWdActualProjMas.setIsMaster(isMaster);
	}

	/**
	* Sets the item ID of this vw inventory wd actual proj mas.
	*
	* @param itemId the item ID of this vw inventory wd actual proj mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_vwInventoryWdActualProjMas.setItemId(itemId);
	}

	/**
	* Sets the item name of this vw inventory wd actual proj mas.
	*
	* @param itemName the item name of this vw inventory wd actual proj mas
	*/
	@Override
	public void setItemName(java.lang.String itemName) {
		_vwInventoryWdActualProjMas.setItemName(itemName);
	}

	/**
	* Sets the modified by of this vw inventory wd actual proj mas.
	*
	* @param modifiedBy the modified by of this vw inventory wd actual proj mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_vwInventoryWdActualProjMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this vw inventory wd actual proj mas.
	*
	* @param modifiedDate the modified date of this vw inventory wd actual proj mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_vwInventoryWdActualProjMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this vw inventory wd actual proj mas.
	*
	* @param month the month of this vw inventory wd actual proj mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_vwInventoryWdActualProjMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_vwInventoryWdActualProjMas.setNew(n);
	}

	/**
	* Sets the organization key of this vw inventory wd actual proj mas.
	*
	* @param organizationKey the organization key of this vw inventory wd actual proj mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_vwInventoryWdActualProjMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the price of this vw inventory wd actual proj mas.
	*
	* @param price the price of this vw inventory wd actual proj mas
	*/
	@Override
	public void setPrice(double price) {
		_vwInventoryWdActualProjMas.setPrice(price);
	}

	/**
	* Sets the primary key of this vw inventory wd actual proj mas.
	*
	* @param primaryKey the primary key of this vw inventory wd actual proj mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_vwInventoryWdActualProjMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_vwInventoryWdActualProjMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity on order of this vw inventory wd actual proj mas.
	*
	* @param quantityOnOrder the quantity on order of this vw inventory wd actual proj mas
	*/
	@Override
	public void setQuantityOnOrder(double quantityOnOrder) {
		_vwInventoryWdActualProjMas.setQuantityOnOrder(quantityOnOrder);
	}

	/**
	* Sets the quantity received of this vw inventory wd actual proj mas.
	*
	* @param quantityReceived the quantity received of this vw inventory wd actual proj mas
	*/
	@Override
	public void setQuantityReceived(double quantityReceived) {
		_vwInventoryWdActualProjMas.setQuantityReceived(quantityReceived);
	}

	/**
	* Sets the source of this vw inventory wd actual proj mas.
	*
	* @param source the source of this vw inventory wd actual proj mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_vwInventoryWdActualProjMas.setSource(source);
	}

	/**
	* Sets the units on hand of this vw inventory wd actual proj mas.
	*
	* @param unitsOnHand the units on hand of this vw inventory wd actual proj mas
	*/
	@Override
	public void setUnitsOnHand(double unitsOnHand) {
		_vwInventoryWdActualProjMas.setUnitsOnHand(unitsOnHand);
	}

	/**
	* Sets the units withdrawn of this vw inventory wd actual proj mas.
	*
	* @param unitsWithdrawn the units withdrawn of this vw inventory wd actual proj mas
	*/
	@Override
	public void setUnitsWithdrawn(double unitsWithdrawn) {
		_vwInventoryWdActualProjMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this vw inventory wd actual proj mas.
	*
	* @param week the week of this vw inventory wd actual proj mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_vwInventoryWdActualProjMas.setWeek(week);
	}

	/**
	* Sets the year of this vw inventory wd actual proj mas.
	*
	* @param year the year of this vw inventory wd actual proj mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_vwInventoryWdActualProjMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<VwInventoryWdActualProjMas> toCacheModel() {
		return _vwInventoryWdActualProjMas.toCacheModel();
	}

	@Override
	public VwInventoryWdActualProjMas toEscapedModel() {
		return new VwInventoryWdActualProjMasWrapper(_vwInventoryWdActualProjMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _vwInventoryWdActualProjMas.toString();
	}

	@Override
	public VwInventoryWdActualProjMas toUnescapedModel() {
		return new VwInventoryWdActualProjMasWrapper(_vwInventoryWdActualProjMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _vwInventoryWdActualProjMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwInventoryWdActualProjMasWrapper)) {
			return false;
		}

		VwInventoryWdActualProjMasWrapper vwInventoryWdActualProjMasWrapper = (VwInventoryWdActualProjMasWrapper)obj;

		if (Objects.equals(_vwInventoryWdActualProjMas,
					vwInventoryWdActualProjMasWrapper._vwInventoryWdActualProjMas)) {
			return true;
		}

		return false;
	}

	@Override
	public VwInventoryWdActualProjMas getWrappedModel() {
		return _vwInventoryWdActualProjMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _vwInventoryWdActualProjMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _vwInventoryWdActualProjMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_vwInventoryWdActualProjMas.resetOriginalValues();
	}

	private final VwInventoryWdActualProjMas _vwInventoryWdActualProjMas;
}