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
 * This class is a wrapper for {@link InventoryWdProjMas}.
 * </p>
 *
 * @author
 * @see InventoryWdProjMas
 * @generated
 */
@ProviderType
public class InventoryWdProjMasWrapper implements InventoryWdProjMas,
	ModelWrapper<InventoryWdProjMas> {
	public InventoryWdProjMasWrapper(InventoryWdProjMas inventoryWdProjMas) {
		_inventoryWdProjMas = inventoryWdProjMas;
	}

	@Override
	public Class<?> getModelClass() {
		return InventoryWdProjMas.class;
	}

	@Override
	public String getModelClassName() {
		return InventoryWdProjMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("week", getWeek());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("country", getCountry());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("inventoryWdProjMasSid", getInventoryWdProjMasSid());
		attributes.put("day", getDay());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("month", getMonth());
		attributes.put("forecastName", getForecastName());
		attributes.put("amountWithdrawn", getAmountWithdrawn());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String week = (String)attributes.get("week");

		if (week != null) {
			setWeek(week);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		String unitsWithdrawn = (String)attributes.get("unitsWithdrawn");

		if (unitsWithdrawn != null) {
			setUnitsWithdrawn(unitsWithdrawn);
		}

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
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

		Boolean recordLockStatus = (Boolean)attributes.get("recordLockStatus");

		if (recordLockStatus != null) {
			setRecordLockStatus(recordLockStatus);
		}

		String itemIdentifierCodeQualifier = (String)attributes.get(
				"itemIdentifierCodeQualifier");

		if (itemIdentifierCodeQualifier != null) {
			setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
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

		Integer inventoryWdProjMasSid = (Integer)attributes.get(
				"inventoryWdProjMasSid");

		if (inventoryWdProjMasSid != null) {
			setInventoryWdProjMasSid(inventoryWdProjMasSid);
		}

		String day = (String)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		String forecastVer = (String)attributes.get("forecastVer");

		if (forecastVer != null) {
			setForecastVer(forecastVer);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
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

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		String amountWithdrawn = (String)attributes.get("amountWithdrawn");

		if (amountWithdrawn != null) {
			setAmountWithdrawn(amountWithdrawn);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new InventoryWdProjMasWrapper((InventoryWdProjMas)_inventoryWdProjMas.clone());
	}

	@Override
	public int compareTo(InventoryWdProjMas inventoryWdProjMas) {
		return _inventoryWdProjMas.compareTo(inventoryWdProjMas);
	}

	/**
	* Returns the amount withdrawn of this inventory wd proj mas.
	*
	* @return the amount withdrawn of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getAmountWithdrawn() {
		return _inventoryWdProjMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this inventory wd proj mas.
	*
	* @return the batch ID of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _inventoryWdProjMas.getBatchId();
	}

	/**
	* Returns the country of this inventory wd proj mas.
	*
	* @return the country of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _inventoryWdProjMas.getCountry();
	}

	/**
	* Returns the created by of this inventory wd proj mas.
	*
	* @return the created by of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _inventoryWdProjMas.getCreatedBy();
	}

	/**
	* Returns the created date of this inventory wd proj mas.
	*
	* @return the created date of this inventory wd proj mas
	*/
	@Override
	public Date getCreatedDate() {
		return _inventoryWdProjMas.getCreatedDate();
	}

	/**
	* Returns the day of this inventory wd proj mas.
	*
	* @return the day of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getDay() {
		return _inventoryWdProjMas.getDay();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _inventoryWdProjMas.getExpandoBridge();
	}

	/**
	* Returns the forecast name of this inventory wd proj mas.
	*
	* @return the forecast name of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getForecastName() {
		return _inventoryWdProjMas.getForecastName();
	}

	/**
	* Returns the forecast ver of this inventory wd proj mas.
	*
	* @return the forecast ver of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _inventoryWdProjMas.getForecastVer();
	}

	/**
	* Returns the inbound status of this inventory wd proj mas.
	*
	* @return the inbound status of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _inventoryWdProjMas.getInboundStatus();
	}

	/**
	* Returns the inventory wd proj mas sid of this inventory wd proj mas.
	*
	* @return the inventory wd proj mas sid of this inventory wd proj mas
	*/
	@Override
	public int getInventoryWdProjMasSid() {
		return _inventoryWdProjMas.getInventoryWdProjMasSid();
	}

	/**
	* Returns the item ID of this inventory wd proj mas.
	*
	* @return the item ID of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _inventoryWdProjMas.getItemId();
	}

	/**
	* Returns the item identifier of this inventory wd proj mas.
	*
	* @return the item identifier of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _inventoryWdProjMas.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this inventory wd proj mas.
	*
	* @return the item identifier code qualifier of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _inventoryWdProjMas.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the item master sid of this inventory wd proj mas.
	*
	* @return the item master sid of this inventory wd proj mas
	*/
	@Override
	public int getItemMasterSid() {
		return _inventoryWdProjMas.getItemMasterSid();
	}

	/**
	* Returns the modified by of this inventory wd proj mas.
	*
	* @return the modified by of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _inventoryWdProjMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this inventory wd proj mas.
	*
	* @return the modified date of this inventory wd proj mas
	*/
	@Override
	public Date getModifiedDate() {
		return _inventoryWdProjMas.getModifiedDate();
	}

	/**
	* Returns the month of this inventory wd proj mas.
	*
	* @return the month of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _inventoryWdProjMas.getMonth();
	}

	/**
	* Returns the organization key of this inventory wd proj mas.
	*
	* @return the organization key of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _inventoryWdProjMas.getOrganizationKey();
	}

	/**
	* Returns the primary key of this inventory wd proj mas.
	*
	* @return the primary key of this inventory wd proj mas
	*/
	@Override
	public int getPrimaryKey() {
		return _inventoryWdProjMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _inventoryWdProjMas.getPrimaryKeyObj();
	}

	/**
	* Returns the record lock status of this inventory wd proj mas.
	*
	* @return the record lock status of this inventory wd proj mas
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _inventoryWdProjMas.getRecordLockStatus();
	}

	/**
	* Returns the source of this inventory wd proj mas.
	*
	* @return the source of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getSource() {
		return _inventoryWdProjMas.getSource();
	}

	/**
	* Returns the units withdrawn of this inventory wd proj mas.
	*
	* @return the units withdrawn of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getUnitsWithdrawn() {
		return _inventoryWdProjMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this inventory wd proj mas.
	*
	* @return the week of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _inventoryWdProjMas.getWeek();
	}

	/**
	* Returns the year of this inventory wd proj mas.
	*
	* @return the year of this inventory wd proj mas
	*/
	@Override
	public java.lang.String getYear() {
		return _inventoryWdProjMas.getYear();
	}

	@Override
	public int hashCode() {
		return _inventoryWdProjMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _inventoryWdProjMas.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _inventoryWdProjMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _inventoryWdProjMas.isNew();
	}

	/**
	* Returns <code>true</code> if this inventory wd proj mas is record lock status.
	*
	* @return <code>true</code> if this inventory wd proj mas is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _inventoryWdProjMas.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_inventoryWdProjMas.persist();
	}

	/**
	* Sets the amount withdrawn of this inventory wd proj mas.
	*
	* @param amountWithdrawn the amount withdrawn of this inventory wd proj mas
	*/
	@Override
	public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
		_inventoryWdProjMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this inventory wd proj mas.
	*
	* @param batchId the batch ID of this inventory wd proj mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_inventoryWdProjMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_inventoryWdProjMas.setCachedModel(cachedModel);
	}

	/**
	* Sets the country of this inventory wd proj mas.
	*
	* @param country the country of this inventory wd proj mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_inventoryWdProjMas.setCountry(country);
	}

	/**
	* Sets the created by of this inventory wd proj mas.
	*
	* @param createdBy the created by of this inventory wd proj mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_inventoryWdProjMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this inventory wd proj mas.
	*
	* @param createdDate the created date of this inventory wd proj mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_inventoryWdProjMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this inventory wd proj mas.
	*
	* @param day the day of this inventory wd proj mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_inventoryWdProjMas.setDay(day);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_inventoryWdProjMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_inventoryWdProjMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_inventoryWdProjMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast name of this inventory wd proj mas.
	*
	* @param forecastName the forecast name of this inventory wd proj mas
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_inventoryWdProjMas.setForecastName(forecastName);
	}

	/**
	* Sets the forecast ver of this inventory wd proj mas.
	*
	* @param forecastVer the forecast ver of this inventory wd proj mas
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_inventoryWdProjMas.setForecastVer(forecastVer);
	}

	/**
	* Sets the inbound status of this inventory wd proj mas.
	*
	* @param inboundStatus the inbound status of this inventory wd proj mas
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_inventoryWdProjMas.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the inventory wd proj mas sid of this inventory wd proj mas.
	*
	* @param inventoryWdProjMasSid the inventory wd proj mas sid of this inventory wd proj mas
	*/
	@Override
	public void setInventoryWdProjMasSid(int inventoryWdProjMasSid) {
		_inventoryWdProjMas.setInventoryWdProjMasSid(inventoryWdProjMasSid);
	}

	/**
	* Sets the item ID of this inventory wd proj mas.
	*
	* @param itemId the item ID of this inventory wd proj mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_inventoryWdProjMas.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this inventory wd proj mas.
	*
	* @param itemIdentifier the item identifier of this inventory wd proj mas
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_inventoryWdProjMas.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this inventory wd proj mas.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this inventory wd proj mas
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_inventoryWdProjMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the item master sid of this inventory wd proj mas.
	*
	* @param itemMasterSid the item master sid of this inventory wd proj mas
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_inventoryWdProjMas.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this inventory wd proj mas.
	*
	* @param modifiedBy the modified by of this inventory wd proj mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_inventoryWdProjMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this inventory wd proj mas.
	*
	* @param modifiedDate the modified date of this inventory wd proj mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_inventoryWdProjMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this inventory wd proj mas.
	*
	* @param month the month of this inventory wd proj mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_inventoryWdProjMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_inventoryWdProjMas.setNew(n);
	}

	/**
	* Sets the organization key of this inventory wd proj mas.
	*
	* @param organizationKey the organization key of this inventory wd proj mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_inventoryWdProjMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this inventory wd proj mas.
	*
	* @param primaryKey the primary key of this inventory wd proj mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_inventoryWdProjMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_inventoryWdProjMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this inventory wd proj mas is record lock status.
	*
	* @param recordLockStatus the record lock status of this inventory wd proj mas
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_inventoryWdProjMas.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this inventory wd proj mas.
	*
	* @param source the source of this inventory wd proj mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_inventoryWdProjMas.setSource(source);
	}

	/**
	* Sets the units withdrawn of this inventory wd proj mas.
	*
	* @param unitsWithdrawn the units withdrawn of this inventory wd proj mas
	*/
	@Override
	public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
		_inventoryWdProjMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this inventory wd proj mas.
	*
	* @param week the week of this inventory wd proj mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_inventoryWdProjMas.setWeek(week);
	}

	/**
	* Sets the year of this inventory wd proj mas.
	*
	* @param year the year of this inventory wd proj mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_inventoryWdProjMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<InventoryWdProjMas> toCacheModel() {
		return _inventoryWdProjMas.toCacheModel();
	}

	@Override
	public InventoryWdProjMas toEscapedModel() {
		return new InventoryWdProjMasWrapper(_inventoryWdProjMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _inventoryWdProjMas.toString();
	}

	@Override
	public InventoryWdProjMas toUnescapedModel() {
		return new InventoryWdProjMasWrapper(_inventoryWdProjMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _inventoryWdProjMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InventoryWdProjMasWrapper)) {
			return false;
		}

		InventoryWdProjMasWrapper inventoryWdProjMasWrapper = (InventoryWdProjMasWrapper)obj;

		if (Objects.equals(_inventoryWdProjMas,
					inventoryWdProjMasWrapper._inventoryWdProjMas)) {
			return true;
		}

		return false;
	}

	@Override
	public InventoryWdProjMas getWrappedModel() {
		return _inventoryWdProjMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _inventoryWdProjMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _inventoryWdProjMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_inventoryWdProjMas.resetOriginalValues();
	}

	private final InventoryWdProjMas _inventoryWdProjMas;
}