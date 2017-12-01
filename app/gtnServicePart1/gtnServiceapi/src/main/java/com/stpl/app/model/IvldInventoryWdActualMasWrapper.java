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
 * This class is a wrapper for {@link IvldInventoryWdActualMas}.
 * </p>
 *
 * @author
 * @see IvldInventoryWdActualMas
 * @generated
 */
@ProviderType
public class IvldInventoryWdActualMasWrapper implements IvldInventoryWdActualMas,
	ModelWrapper<IvldInventoryWdActualMas> {
	public IvldInventoryWdActualMasWrapper(
		IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		_ivldInventoryWdActualMas = ivldInventoryWdActualMas;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldInventoryWdActualMas.class;
	}

	@Override
	public String getModelClassName() {
		return IvldInventoryWdActualMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("quantityOnOrder", getQuantityOnOrder());
		attributes.put("week", getWeek());
		attributes.put("amountOnHand", getAmountOnHand());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("source", getSource());
		attributes.put("ivldInventoryWdActualMasSid",
			getIvldInventoryWdActualMasSid());
		attributes.put("day", getDay());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("unitsOnHand", getUnitsOnHand());
		attributes.put("amountReceived", getAmountReceived());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("month", getMonth());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("amountWithdrawn", getAmountWithdrawn());
		attributes.put("inventoryWdActualMasIntfId",
			getInventoryWdActualMasIntfId());
		attributes.put("quantityReceived", getQuantityReceived());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("country", getCountry());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("batchId", getBatchId());
		attributes.put("errorField", getErrorField());
		attributes.put("amountOnOrder", getAmountOnOrder());
		attributes.put("checkRecord", getCheckRecord());

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

		String createdBy = (String)attributes.get("createdBy");

		if (createdBy != null) {
			setCreatedBy(createdBy);
		}

		Date createdDate = (Date)attributes.get("createdDate");

		if (createdDate != null) {
			setCreatedDate(createdDate);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer ivldInventoryWdActualMasSid = (Integer)attributes.get(
				"ivldInventoryWdActualMasSid");

		if (ivldInventoryWdActualMasSid != null) {
			setIvldInventoryWdActualMasSid(ivldInventoryWdActualMasSid);
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

		Integer inventoryWdActualMasIntfId = (Integer)attributes.get(
				"inventoryWdActualMasIntfId");

		if (inventoryWdActualMasIntfId != null) {
			setInventoryWdActualMasIntfId(inventoryWdActualMasIntfId);
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

		String itemIdentifierCodeQualifier = (String)attributes.get(
				"itemIdentifierCodeQualifier");

		if (itemIdentifierCodeQualifier != null) {
			setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
		}

		String batchId = (String)attributes.get("batchId");

		if (batchId != null) {
			setBatchId(batchId);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
		}

		String amountOnOrder = (String)attributes.get("amountOnOrder");

		if (amountOnOrder != null) {
			setAmountOnOrder(amountOnOrder);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldInventoryWdActualMasWrapper((IvldInventoryWdActualMas)_ivldInventoryWdActualMas.clone());
	}

	@Override
	public int compareTo(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
		return _ivldInventoryWdActualMas.compareTo(ivldInventoryWdActualMas);
	}

	/**
	* Returns the add chg del indicator of this ivld inventory wd actual mas.
	*
	* @return the add chg del indicator of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldInventoryWdActualMas.getAddChgDelIndicator();
	}

	/**
	* Returns the amount on hand of this ivld inventory wd actual mas.
	*
	* @return the amount on hand of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountOnHand() {
		return _ivldInventoryWdActualMas.getAmountOnHand();
	}

	/**
	* Returns the amount on order of this ivld inventory wd actual mas.
	*
	* @return the amount on order of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountOnOrder() {
		return _ivldInventoryWdActualMas.getAmountOnOrder();
	}

	/**
	* Returns the amount received of this ivld inventory wd actual mas.
	*
	* @return the amount received of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountReceived() {
		return _ivldInventoryWdActualMas.getAmountReceived();
	}

	/**
	* Returns the amount withdrawn of this ivld inventory wd actual mas.
	*
	* @return the amount withdrawn of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getAmountWithdrawn() {
		return _ivldInventoryWdActualMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this ivld inventory wd actual mas.
	*
	* @return the batch ID of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldInventoryWdActualMas.getBatchId();
	}

	/**
	* Returns the check record of this ivld inventory wd actual mas.
	*
	* @return the check record of this ivld inventory wd actual mas
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldInventoryWdActualMas.getCheckRecord();
	}

	/**
	* Returns the country of this ivld inventory wd actual mas.
	*
	* @return the country of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _ivldInventoryWdActualMas.getCountry();
	}

	/**
	* Returns the created by of this ivld inventory wd actual mas.
	*
	* @return the created by of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldInventoryWdActualMas.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld inventory wd actual mas.
	*
	* @return the created date of this ivld inventory wd actual mas
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldInventoryWdActualMas.getCreatedDate();
	}

	/**
	* Returns the day of this ivld inventory wd actual mas.
	*
	* @return the day of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getDay() {
		return _ivldInventoryWdActualMas.getDay();
	}

	/**
	* Returns the error code of this ivld inventory wd actual mas.
	*
	* @return the error code of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldInventoryWdActualMas.getErrorCode();
	}

	/**
	* Returns the error field of this ivld inventory wd actual mas.
	*
	* @return the error field of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldInventoryWdActualMas.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldInventoryWdActualMas.getExpandoBridge();
	}

	/**
	* Returns the intf inserted date of this ivld inventory wd actual mas.
	*
	* @return the intf inserted date of this ivld inventory wd actual mas
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldInventoryWdActualMas.getIntfInsertedDate();
	}

	/**
	* Returns the inventory wd actual mas intf ID of this ivld inventory wd actual mas.
	*
	* @return the inventory wd actual mas intf ID of this ivld inventory wd actual mas
	*/
	@Override
	public int getInventoryWdActualMasIntfId() {
		return _ivldInventoryWdActualMas.getInventoryWdActualMasIntfId();
	}

	/**
	* Returns the item ID of this ivld inventory wd actual mas.
	*
	* @return the item ID of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldInventoryWdActualMas.getItemId();
	}

	/**
	* Returns the item identifier of this ivld inventory wd actual mas.
	*
	* @return the item identifier of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ivldInventoryWdActualMas.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this ivld inventory wd actual mas.
	*
	* @return the item identifier code qualifier of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _ivldInventoryWdActualMas.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the ivld inventory wd actual mas sid of this ivld inventory wd actual mas.
	*
	* @return the ivld inventory wd actual mas sid of this ivld inventory wd actual mas
	*/
	@Override
	public int getIvldInventoryWdActualMasSid() {
		return _ivldInventoryWdActualMas.getIvldInventoryWdActualMasSid();
	}

	/**
	* Returns the modified by of this ivld inventory wd actual mas.
	*
	* @return the modified by of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldInventoryWdActualMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld inventory wd actual mas.
	*
	* @return the modified date of this ivld inventory wd actual mas
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldInventoryWdActualMas.getModifiedDate();
	}

	/**
	* Returns the month of this ivld inventory wd actual mas.
	*
	* @return the month of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _ivldInventoryWdActualMas.getMonth();
	}

	/**
	* Returns the organization key of this ivld inventory wd actual mas.
	*
	* @return the organization key of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldInventoryWdActualMas.getOrganizationKey();
	}

	/**
	* Returns the primary key of this ivld inventory wd actual mas.
	*
	* @return the primary key of this ivld inventory wd actual mas
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldInventoryWdActualMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldInventoryWdActualMas.getPrimaryKeyObj();
	}

	/**
	* Returns the quantity on order of this ivld inventory wd actual mas.
	*
	* @return the quantity on order of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getQuantityOnOrder() {
		return _ivldInventoryWdActualMas.getQuantityOnOrder();
	}

	/**
	* Returns the quantity received of this ivld inventory wd actual mas.
	*
	* @return the quantity received of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getQuantityReceived() {
		return _ivldInventoryWdActualMas.getQuantityReceived();
	}

	/**
	* Returns the reason for failure of this ivld inventory wd actual mas.
	*
	* @return the reason for failure of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldInventoryWdActualMas.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld inventory wd actual mas.
	*
	* @return the reprocessed flag of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldInventoryWdActualMas.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld inventory wd actual mas.
	*
	* @return the source of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldInventoryWdActualMas.getSource();
	}

	/**
	* Returns the units on hand of this ivld inventory wd actual mas.
	*
	* @return the units on hand of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getUnitsOnHand() {
		return _ivldInventoryWdActualMas.getUnitsOnHand();
	}

	/**
	* Returns the units withdrawn of this ivld inventory wd actual mas.
	*
	* @return the units withdrawn of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getUnitsWithdrawn() {
		return _ivldInventoryWdActualMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this ivld inventory wd actual mas.
	*
	* @return the week of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _ivldInventoryWdActualMas.getWeek();
	}

	/**
	* Returns the year of this ivld inventory wd actual mas.
	*
	* @return the year of this ivld inventory wd actual mas
	*/
	@Override
	public java.lang.String getYear() {
		return _ivldInventoryWdActualMas.getYear();
	}

	@Override
	public int hashCode() {
		return _ivldInventoryWdActualMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldInventoryWdActualMas.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld inventory wd actual mas is check record.
	*
	* @return <code>true</code> if this ivld inventory wd actual mas is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldInventoryWdActualMas.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldInventoryWdActualMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldInventoryWdActualMas.isNew();
	}

	@Override
	public void persist() {
		_ivldInventoryWdActualMas.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld inventory wd actual mas.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld inventory wd actual mas
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldInventoryWdActualMas.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount on hand of this ivld inventory wd actual mas.
	*
	* @param amountOnHand the amount on hand of this ivld inventory wd actual mas
	*/
	@Override
	public void setAmountOnHand(java.lang.String amountOnHand) {
		_ivldInventoryWdActualMas.setAmountOnHand(amountOnHand);
	}

	/**
	* Sets the amount on order of this ivld inventory wd actual mas.
	*
	* @param amountOnOrder the amount on order of this ivld inventory wd actual mas
	*/
	@Override
	public void setAmountOnOrder(java.lang.String amountOnOrder) {
		_ivldInventoryWdActualMas.setAmountOnOrder(amountOnOrder);
	}

	/**
	* Sets the amount received of this ivld inventory wd actual mas.
	*
	* @param amountReceived the amount received of this ivld inventory wd actual mas
	*/
	@Override
	public void setAmountReceived(java.lang.String amountReceived) {
		_ivldInventoryWdActualMas.setAmountReceived(amountReceived);
	}

	/**
	* Sets the amount withdrawn of this ivld inventory wd actual mas.
	*
	* @param amountWithdrawn the amount withdrawn of this ivld inventory wd actual mas
	*/
	@Override
	public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
		_ivldInventoryWdActualMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this ivld inventory wd actual mas.
	*
	* @param batchId the batch ID of this ivld inventory wd actual mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldInventoryWdActualMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldInventoryWdActualMas.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld inventory wd actual mas is check record.
	*
	* @param checkRecord the check record of this ivld inventory wd actual mas
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldInventoryWdActualMas.setCheckRecord(checkRecord);
	}

	/**
	* Sets the country of this ivld inventory wd actual mas.
	*
	* @param country the country of this ivld inventory wd actual mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_ivldInventoryWdActualMas.setCountry(country);
	}

	/**
	* Sets the created by of this ivld inventory wd actual mas.
	*
	* @param createdBy the created by of this ivld inventory wd actual mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldInventoryWdActualMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld inventory wd actual mas.
	*
	* @param createdDate the created date of this ivld inventory wd actual mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldInventoryWdActualMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this ivld inventory wd actual mas.
	*
	* @param day the day of this ivld inventory wd actual mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_ivldInventoryWdActualMas.setDay(day);
	}

	/**
	* Sets the error code of this ivld inventory wd actual mas.
	*
	* @param errorCode the error code of this ivld inventory wd actual mas
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldInventoryWdActualMas.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld inventory wd actual mas.
	*
	* @param errorField the error field of this ivld inventory wd actual mas
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldInventoryWdActualMas.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldInventoryWdActualMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldInventoryWdActualMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldInventoryWdActualMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the intf inserted date of this ivld inventory wd actual mas.
	*
	* @param intfInsertedDate the intf inserted date of this ivld inventory wd actual mas
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldInventoryWdActualMas.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the inventory wd actual mas intf ID of this ivld inventory wd actual mas.
	*
	* @param inventoryWdActualMasIntfId the inventory wd actual mas intf ID of this ivld inventory wd actual mas
	*/
	@Override
	public void setInventoryWdActualMasIntfId(int inventoryWdActualMasIntfId) {
		_ivldInventoryWdActualMas.setInventoryWdActualMasIntfId(inventoryWdActualMasIntfId);
	}

	/**
	* Sets the item ID of this ivld inventory wd actual mas.
	*
	* @param itemId the item ID of this ivld inventory wd actual mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldInventoryWdActualMas.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ivld inventory wd actual mas.
	*
	* @param itemIdentifier the item identifier of this ivld inventory wd actual mas
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ivldInventoryWdActualMas.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this ivld inventory wd actual mas.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld inventory wd actual mas
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_ivldInventoryWdActualMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the ivld inventory wd actual mas sid of this ivld inventory wd actual mas.
	*
	* @param ivldInventoryWdActualMasSid the ivld inventory wd actual mas sid of this ivld inventory wd actual mas
	*/
	@Override
	public void setIvldInventoryWdActualMasSid(int ivldInventoryWdActualMasSid) {
		_ivldInventoryWdActualMas.setIvldInventoryWdActualMasSid(ivldInventoryWdActualMasSid);
	}

	/**
	* Sets the modified by of this ivld inventory wd actual mas.
	*
	* @param modifiedBy the modified by of this ivld inventory wd actual mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldInventoryWdActualMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld inventory wd actual mas.
	*
	* @param modifiedDate the modified date of this ivld inventory wd actual mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldInventoryWdActualMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this ivld inventory wd actual mas.
	*
	* @param month the month of this ivld inventory wd actual mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_ivldInventoryWdActualMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_ivldInventoryWdActualMas.setNew(n);
	}

	/**
	* Sets the organization key of this ivld inventory wd actual mas.
	*
	* @param organizationKey the organization key of this ivld inventory wd actual mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldInventoryWdActualMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this ivld inventory wd actual mas.
	*
	* @param primaryKey the primary key of this ivld inventory wd actual mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldInventoryWdActualMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldInventoryWdActualMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the quantity on order of this ivld inventory wd actual mas.
	*
	* @param quantityOnOrder the quantity on order of this ivld inventory wd actual mas
	*/
	@Override
	public void setQuantityOnOrder(java.lang.String quantityOnOrder) {
		_ivldInventoryWdActualMas.setQuantityOnOrder(quantityOnOrder);
	}

	/**
	* Sets the quantity received of this ivld inventory wd actual mas.
	*
	* @param quantityReceived the quantity received of this ivld inventory wd actual mas
	*/
	@Override
	public void setQuantityReceived(java.lang.String quantityReceived) {
		_ivldInventoryWdActualMas.setQuantityReceived(quantityReceived);
	}

	/**
	* Sets the reason for failure of this ivld inventory wd actual mas.
	*
	* @param reasonForFailure the reason for failure of this ivld inventory wd actual mas
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldInventoryWdActualMas.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld inventory wd actual mas.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld inventory wd actual mas
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldInventoryWdActualMas.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld inventory wd actual mas.
	*
	* @param source the source of this ivld inventory wd actual mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldInventoryWdActualMas.setSource(source);
	}

	/**
	* Sets the units on hand of this ivld inventory wd actual mas.
	*
	* @param unitsOnHand the units on hand of this ivld inventory wd actual mas
	*/
	@Override
	public void setUnitsOnHand(java.lang.String unitsOnHand) {
		_ivldInventoryWdActualMas.setUnitsOnHand(unitsOnHand);
	}

	/**
	* Sets the units withdrawn of this ivld inventory wd actual mas.
	*
	* @param unitsWithdrawn the units withdrawn of this ivld inventory wd actual mas
	*/
	@Override
	public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
		_ivldInventoryWdActualMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this ivld inventory wd actual mas.
	*
	* @param week the week of this ivld inventory wd actual mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_ivldInventoryWdActualMas.setWeek(week);
	}

	/**
	* Sets the year of this ivld inventory wd actual mas.
	*
	* @param year the year of this ivld inventory wd actual mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_ivldInventoryWdActualMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldInventoryWdActualMas> toCacheModel() {
		return _ivldInventoryWdActualMas.toCacheModel();
	}

	@Override
	public IvldInventoryWdActualMas toEscapedModel() {
		return new IvldInventoryWdActualMasWrapper(_ivldInventoryWdActualMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldInventoryWdActualMas.toString();
	}

	@Override
	public IvldInventoryWdActualMas toUnescapedModel() {
		return new IvldInventoryWdActualMasWrapper(_ivldInventoryWdActualMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldInventoryWdActualMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldInventoryWdActualMasWrapper)) {
			return false;
		}

		IvldInventoryWdActualMasWrapper ivldInventoryWdActualMasWrapper = (IvldInventoryWdActualMasWrapper)obj;

		if (Objects.equals(_ivldInventoryWdActualMas,
					ivldInventoryWdActualMasWrapper._ivldInventoryWdActualMas)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldInventoryWdActualMas getWrappedModel() {
		return _ivldInventoryWdActualMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldInventoryWdActualMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldInventoryWdActualMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldInventoryWdActualMas.resetOriginalValues();
	}

	private final IvldInventoryWdActualMas _ivldInventoryWdActualMas;
}