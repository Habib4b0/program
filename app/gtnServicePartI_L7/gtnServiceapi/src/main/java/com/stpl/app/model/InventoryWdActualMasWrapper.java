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
 * This class is a wrapper for {@link InventoryWdActualMas}.
 * </p>
 *
 * @author
 * @see InventoryWdActualMas
 * @generated
 */
@ProviderType
public class InventoryWdActualMasWrapper implements InventoryWdActualMas,
	ModelWrapper<InventoryWdActualMas> {
	public InventoryWdActualMasWrapper(
		InventoryWdActualMas inventoryWdActualMas) {
		_inventoryWdActualMas = inventoryWdActualMas;
	}

	@Override
	public Class<?> getModelClass() {
		return InventoryWdActualMas.class;
	}

	@Override
	public String getModelClassName() {
		return InventoryWdActualMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("quantityOnOrder", getQuantityOnOrder());
		attributes.put("week", getWeek());
		attributes.put("amountOnHand", getAmountOnHand());
		attributes.put("itemMasterSid", getItemMasterSid());
		attributes.put("inventoryWdActualMasSid", getInventoryWdActualMasSid());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("source", getSource());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("day", getDay());
		attributes.put("unitsOnHand", getUnitsOnHand());
		attributes.put("amountReceived", getAmountReceived());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("inboundStatus", getInboundStatus());
		attributes.put("month", getMonth());
		attributes.put("amountWithdrawn", getAmountWithdrawn());
		attributes.put("quantityReceived", getQuantityReceived());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("country", getCountry());
		attributes.put("recordLockStatus", getRecordLockStatus());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("batchId", getBatchId());
		attributes.put("amountOnOrder", getAmountOnOrder());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String quantityOnOrder = (String)attributes.get("quantityOnOrder");

		if (quantityOnOrder != null) {
			setQuantityOnOrder(quantityOnOrder);
		}

		String week = (String)attributes.get("week");

		if (week != null) {
			setWeek(week);
		}

		String amountOnHand = (String)attributes.get("amountOnHand");

		if (amountOnHand != null) {
			setAmountOnHand(amountOnHand);
		}

		Integer itemMasterSid = (Integer)attributes.get("itemMasterSid");

		if (itemMasterSid != null) {
			setItemMasterSid(itemMasterSid);
		}

		Integer inventoryWdActualMasSid = (Integer)attributes.get(
				"inventoryWdActualMasSid");

		if (inventoryWdActualMasSid != null) {
			setInventoryWdActualMasSid(inventoryWdActualMasSid);
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

		String unitsOnHand = (String)attributes.get("unitsOnHand");

		if (unitsOnHand != null) {
			setUnitsOnHand(unitsOnHand);
		}

		String amountReceived = (String)attributes.get("amountReceived");

		if (amountReceived != null) {
			setAmountReceived(amountReceived);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String modifiedBy = (String)attributes.get("modifiedBy");

		if (modifiedBy != null) {
			setModifiedBy(modifiedBy);
		}

		String inboundStatus = (String)attributes.get("inboundStatus");

		if (inboundStatus != null) {
			setInboundStatus(inboundStatus);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
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

		String country = (String)attributes.get("country");

		if (country != null) {
			setCountry(country);
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

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String amountOnOrder = (String)attributes.get("amountOnOrder");

		if (amountOnOrder != null) {
			setAmountOnOrder(amountOnOrder);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new InventoryWdActualMasWrapper((InventoryWdActualMas)_inventoryWdActualMas.clone());
	}

	@Override
	public int compareTo(InventoryWdActualMas inventoryWdActualMas) {
		return _inventoryWdActualMas.compareTo(inventoryWdActualMas);
	}

	/**
	* Returns the amount on hand of this inventory wd actual mas.
	*
	* @return the amount on hand of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountOnHand() {
		return _inventoryWdActualMas.getAmountOnHand();
	}

	/**
	* Returns the amount on order of this inventory wd actual mas.
	*
	* @return the amount on order of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountOnOrder() {
		return _inventoryWdActualMas.getAmountOnOrder();
	}

	/**
	* Returns the amount received of this inventory wd actual mas.
	*
	* @return the amount received of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountReceived() {
		return _inventoryWdActualMas.getAmountReceived();
	}

	/**
	* Returns the amount withdrawn of this inventory wd actual mas.
	*
	* @return the amount withdrawn of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountWithdrawn() {
		return _inventoryWdActualMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this inventory wd actual mas.
	*
	* @return the batch ID of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _inventoryWdActualMas.getBatchId();
	}

	/**
	* Returns the country of this inventory wd actual mas.
	*
	* @return the country of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _inventoryWdActualMas.getCountry();
	}

	/**
	* Returns the created by of this inventory wd actual mas.
	*
	* @return the created by of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _inventoryWdActualMas.getCreatedBy();
	}

	/**
	* Returns the created date of this inventory wd actual mas.
	*
	* @return the created date of this inventory wd actual mas
	*/
	@Override
	public Date getCreatedDate() {
		return _inventoryWdActualMas.getCreatedDate();
	}

	/**
	* Returns the day of this inventory wd actual mas.
	*
	* @return the day of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getDay() {
		return _inventoryWdActualMas.getDay();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _inventoryWdActualMas.getExpandoBridge();
	}

	/**
	* Returns the inbound status of this inventory wd actual mas.
	*
	* @return the inbound status of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getInboundStatus() {
		return _inventoryWdActualMas.getInboundStatus();
	}

	/**
	* Returns the inventory wd actual mas sid of this inventory wd actual mas.
	*
	* @return the inventory wd actual mas sid of this inventory wd actual mas
	*/
	@Override
	public int getInventoryWdActualMasSid() {
		return _inventoryWdActualMas.getInventoryWdActualMasSid();
	}

	/**
	* Returns the item ID of this inventory wd actual mas.
	*
	* @return the item ID of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _inventoryWdActualMas.getItemId();
	}

	/**
	* Returns the item identifier of this inventory wd actual mas.
	*
	* @return the item identifier of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _inventoryWdActualMas.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this inventory wd actual mas.
	*
	* @return the item identifier code qualifier of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _inventoryWdActualMas.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the item master sid of this inventory wd actual mas.
	*
	* @return the item master sid of this inventory wd actual mas
	*/
	@Override
	public int getItemMasterSid() {
		return _inventoryWdActualMas.getItemMasterSid();
	}

	/**
	* Returns the modified by of this inventory wd actual mas.
	*
	* @return the modified by of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _inventoryWdActualMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this inventory wd actual mas.
	*
	* @return the modified date of this inventory wd actual mas
	*/
	@Override
	public Date getModifiedDate() {
		return _inventoryWdActualMas.getModifiedDate();
	}

	/**
	* Returns the month of this inventory wd actual mas.
	*
	* @return the month of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _inventoryWdActualMas.getMonth();
	}

	/**
	* Returns the organization key of this inventory wd actual mas.
	*
	* @return the organization key of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _inventoryWdActualMas.getOrganizationKey();
	}

	/**
	* Returns the primary key of this inventory wd actual mas.
	*
	* @return the primary key of this inventory wd actual mas
	*/
	@Override
	public int getPrimaryKey() {
		return _inventoryWdActualMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _inventoryWdActualMas.getPrimaryKeyObj();
	}

	/**
	* Returns the quantity on order of this inventory wd actual mas.
	*
	* @return the quantity on order of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getQuantityOnOrder() {
		return _inventoryWdActualMas.getQuantityOnOrder();
	}

	/**
	* Returns the quantity received of this inventory wd actual mas.
	*
	* @return the quantity received of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getQuantityReceived() {
		return _inventoryWdActualMas.getQuantityReceived();
	}

	/**
	* Returns the record lock status of this inventory wd actual mas.
	*
	* @return the record lock status of this inventory wd actual mas
	*/
	@Override
	public boolean getRecordLockStatus() {
		return _inventoryWdActualMas.getRecordLockStatus();
	}

	/**
	* Returns the source of this inventory wd actual mas.
	*
	* @return the source of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getSource() {
		return _inventoryWdActualMas.getSource();
	}

	/**
	* Returns the units on hand of this inventory wd actual mas.
	*
	* @return the units on hand of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getUnitsOnHand() {
		return _inventoryWdActualMas.getUnitsOnHand();
	}

	/**
	* Returns the units withdrawn of this inventory wd actual mas.
	*
	* @return the units withdrawn of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getUnitsWithdrawn() {
		return _inventoryWdActualMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this inventory wd actual mas.
	*
	* @return the week of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _inventoryWdActualMas.getWeek();
	}

	/**
	* Returns the year of this inventory wd actual mas.
	*
	* @return the year of this inventory wd actual mas
	*/
	@Override
	public java.lang.String getYear() {
		return _inventoryWdActualMas.getYear();
	}

	@Override
	public int hashCode() {
		return _inventoryWdActualMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _inventoryWdActualMas.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _inventoryWdActualMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _inventoryWdActualMas.isNew();
	}

	/**
	* Returns <code>true</code> if this inventory wd actual mas is record lock status.
	*
	* @return <code>true</code> if this inventory wd actual mas is record lock status; <code>false</code> otherwise
	*/
	@Override
	public boolean isRecordLockStatus() {
		return _inventoryWdActualMas.isRecordLockStatus();
	}

	@Override
	public void persist() {
		_inventoryWdActualMas.persist();
	}

	/**
	* Sets the amount on hand of this inventory wd actual mas.
	*
	* @param amountOnHand the amount on hand of this inventory wd actual mas
	*/
	@Override
	public void setAmountOnHand(java.lang.String amountOnHand) {
		_inventoryWdActualMas.setAmountOnHand(amountOnHand);
	}

	/**
	* Sets the amount on order of this inventory wd actual mas.
	*
	* @param amountOnOrder the amount on order of this inventory wd actual mas
	*/
	@Override
	public void setAmountOnOrder(java.lang.String amountOnOrder) {
		_inventoryWdActualMas.setAmountOnOrder(amountOnOrder);
	}

	/**
	* Sets the amount received of this inventory wd actual mas.
	*
	* @param amountReceived the amount received of this inventory wd actual mas
	*/
	@Override
	public void setAmountReceived(java.lang.String amountReceived) {
		_inventoryWdActualMas.setAmountReceived(amountReceived);
	}

	/**
	* Sets the amount withdrawn of this inventory wd actual mas.
	*
	* @param amountWithdrawn the amount withdrawn of this inventory wd actual mas
	*/
	@Override
	public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
		_inventoryWdActualMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this inventory wd actual mas.
	*
	* @param batchId the batch ID of this inventory wd actual mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_inventoryWdActualMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_inventoryWdActualMas.setCachedModel(cachedModel);
	}

	/**
	* Sets the country of this inventory wd actual mas.
	*
	* @param country the country of this inventory wd actual mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_inventoryWdActualMas.setCountry(country);
	}

	/**
	* Sets the created by of this inventory wd actual mas.
	*
	* @param createdBy the created by of this inventory wd actual mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_inventoryWdActualMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this inventory wd actual mas.
	*
	* @param createdDate the created date of this inventory wd actual mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_inventoryWdActualMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this inventory wd actual mas.
	*
	* @param day the day of this inventory wd actual mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_inventoryWdActualMas.setDay(day);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_inventoryWdActualMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_inventoryWdActualMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_inventoryWdActualMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the inbound status of this inventory wd actual mas.
	*
	* @param inboundStatus the inbound status of this inventory wd actual mas
	*/
	@Override
	public void setInboundStatus(java.lang.String inboundStatus) {
		_inventoryWdActualMas.setInboundStatus(inboundStatus);
	}

	/**
	* Sets the inventory wd actual mas sid of this inventory wd actual mas.
	*
	* @param inventoryWdActualMasSid the inventory wd actual mas sid of this inventory wd actual mas
	*/
	@Override
	public void setInventoryWdActualMasSid(int inventoryWdActualMasSid) {
		_inventoryWdActualMas.setInventoryWdActualMasSid(inventoryWdActualMasSid);
	}

	/**
	* Sets the item ID of this inventory wd actual mas.
	*
	* @param itemId the item ID of this inventory wd actual mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_inventoryWdActualMas.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this inventory wd actual mas.
	*
	* @param itemIdentifier the item identifier of this inventory wd actual mas
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_inventoryWdActualMas.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this inventory wd actual mas.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this inventory wd actual mas
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_inventoryWdActualMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the item master sid of this inventory wd actual mas.
	*
	* @param itemMasterSid the item master sid of this inventory wd actual mas
	*/
	@Override
	public void setItemMasterSid(int itemMasterSid) {
		_inventoryWdActualMas.setItemMasterSid(itemMasterSid);
	}

	/**
	* Sets the modified by of this inventory wd actual mas.
	*
	* @param modifiedBy the modified by of this inventory wd actual mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_inventoryWdActualMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this inventory wd actual mas.
	*
	* @param modifiedDate the modified date of this inventory wd actual mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_inventoryWdActualMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this inventory wd actual mas.
	*
	* @param month the month of this inventory wd actual mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_inventoryWdActualMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_inventoryWdActualMas.setNew(n);
	}

	/**
	* Sets the organization key of this inventory wd actual mas.
	*
	* @param organizationKey the organization key of this inventory wd actual mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_inventoryWdActualMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this inventory wd actual mas.
	*
	* @param primaryKey the primary key of this inventory wd actual mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_inventoryWdActualMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_inventoryWdActualMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity on order of this inventory wd actual mas.
	*
	* @param quantityOnOrder the quantity on order of this inventory wd actual mas
	*/
	@Override
	public void setQuantityOnOrder(java.lang.String quantityOnOrder) {
		_inventoryWdActualMas.setQuantityOnOrder(quantityOnOrder);
	}

	/**
	* Sets the quantity received of this inventory wd actual mas.
	*
	* @param quantityReceived the quantity received of this inventory wd actual mas
	*/
	@Override
	public void setQuantityReceived(java.lang.String quantityReceived) {
		_inventoryWdActualMas.setQuantityReceived(quantityReceived);
	}

	/**
	* Sets whether this inventory wd actual mas is record lock status.
	*
	* @param recordLockStatus the record lock status of this inventory wd actual mas
	*/
	@Override
	public void setRecordLockStatus(boolean recordLockStatus) {
		_inventoryWdActualMas.setRecordLockStatus(recordLockStatus);
	}

	/**
	* Sets the source of this inventory wd actual mas.
	*
	* @param source the source of this inventory wd actual mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_inventoryWdActualMas.setSource(source);
	}

	/**
	* Sets the units on hand of this inventory wd actual mas.
	*
	* @param unitsOnHand the units on hand of this inventory wd actual mas
	*/
	@Override
	public void setUnitsOnHand(java.lang.String unitsOnHand) {
		_inventoryWdActualMas.setUnitsOnHand(unitsOnHand);
	}

	/**
	* Sets the units withdrawn of this inventory wd actual mas.
	*
	* @param unitsWithdrawn the units withdrawn of this inventory wd actual mas
	*/
	@Override
	public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
		_inventoryWdActualMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this inventory wd actual mas.
	*
	* @param week the week of this inventory wd actual mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_inventoryWdActualMas.setWeek(week);
	}

	/**
	* Sets the year of this inventory wd actual mas.
	*
	* @param year the year of this inventory wd actual mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_inventoryWdActualMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<InventoryWdActualMas> toCacheModel() {
		return _inventoryWdActualMas.toCacheModel();
	}

	@Override
	public InventoryWdActualMas toEscapedModel() {
		return new InventoryWdActualMasWrapper(_inventoryWdActualMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _inventoryWdActualMas.toString();
	}

	@Override
	public InventoryWdActualMas toUnescapedModel() {
		return new InventoryWdActualMasWrapper(_inventoryWdActualMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _inventoryWdActualMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof InventoryWdActualMasWrapper)) {
			return false;
		}

		InventoryWdActualMasWrapper inventoryWdActualMasWrapper = (InventoryWdActualMasWrapper)obj;

		if (Objects.equals(_inventoryWdActualMas,
					inventoryWdActualMasWrapper._inventoryWdActualMas)) {
			return true;
		}

		return false;
	}

	@Override
	public InventoryWdActualMas getWrappedModel() {
		return _inventoryWdActualMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _inventoryWdActualMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _inventoryWdActualMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_inventoryWdActualMas.resetOriginalValues();
	}

	private final InventoryWdActualMas _inventoryWdActualMas;
}