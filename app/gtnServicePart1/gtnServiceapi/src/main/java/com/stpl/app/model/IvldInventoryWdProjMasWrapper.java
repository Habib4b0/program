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
 * This class is a wrapper for {@link IvldInventoryWdProjMas}.
 * </p>
 *
 * @author
 * @see IvldInventoryWdProjMas
 * @generated
 */
@ProviderType
public class IvldInventoryWdProjMasWrapper implements IvldInventoryWdProjMas,
	ModelWrapper<IvldInventoryWdProjMas> {
	public IvldInventoryWdProjMasWrapper(
		IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		_ivldInventoryWdProjMas = ivldInventoryWdProjMas;
	}

	@Override
	public Class<?> getModelClass() {
		return IvldInventoryWdProjMas.class;
	}

	@Override
	public String getModelClassName() {
		return IvldInventoryWdProjMas.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("inventoryWdProjMasIntfId", getInventoryWdProjMasIntfId());
		attributes.put("week", getWeek());
		attributes.put("unitsWithdrawn", getUnitsWithdrawn());
		attributes.put("reasonForFailure", getReasonForFailure());
		attributes.put("country", getCountry());
		attributes.put("year", getYear());
		attributes.put("itemId", getItemId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("organizationKey", getOrganizationKey());
		attributes.put("itemIdentifierCodeQualifier",
			getItemIdentifierCodeQualifier());
		attributes.put("source", getSource());
		attributes.put("createdDate", getCreatedDate());
		attributes.put("createdBy", getCreatedBy());
		attributes.put("day", getDay());
		attributes.put("forecastVer", getForecastVer());
		attributes.put("batchId", getBatchId());
		attributes.put("addChgDelIndicator", getAddChgDelIndicator());
		attributes.put("itemIdentifier", getItemIdentifier());
		attributes.put("errorField", getErrorField());
		attributes.put("errorCode", getErrorCode());
		attributes.put("intfInsertedDate", getIntfInsertedDate());
		attributes.put("modifiedBy", getModifiedBy());
		attributes.put("ivldInventoryWdProjMasSid",
			getIvldInventoryWdProjMasSid());
		attributes.put("month", getMonth());
		attributes.put("reprocessedFlag", getReprocessedFlag());
		attributes.put("forecastName", getForecastName());
		attributes.put("amountWithdrawn", getAmountWithdrawn());
		attributes.put("checkRecord", getCheckRecord());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer inventoryWdProjMasIntfId = (Integer)attributes.get(
				"inventoryWdProjMasIntfId");

		if (inventoryWdProjMasIntfId != null) {
			setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);
		}

		String week = (String)attributes.get("week");

		if (week != null) {
			setWeek(week);
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

		String addChgDelIndicator = (String)attributes.get("addChgDelIndicator");

		if (addChgDelIndicator != null) {
			setAddChgDelIndicator(addChgDelIndicator);
		}

		String itemIdentifier = (String)attributes.get("itemIdentifier");

		if (itemIdentifier != null) {
			setItemIdentifier(itemIdentifier);
		}

		String errorField = (String)attributes.get("errorField");

		if (errorField != null) {
			setErrorField(errorField);
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

		Integer ivldInventoryWdProjMasSid = (Integer)attributes.get(
				"ivldInventoryWdProjMasSid");

		if (ivldInventoryWdProjMasSid != null) {
			setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);
		}

		String month = (String)attributes.get("month");

		if (month != null) {
			setMonth(month);
		}

		String reprocessedFlag = (String)attributes.get("reprocessedFlag");

		if (reprocessedFlag != null) {
			setReprocessedFlag(reprocessedFlag);
		}

		String forecastName = (String)attributes.get("forecastName");

		if (forecastName != null) {
			setForecastName(forecastName);
		}

		String amountWithdrawn = (String)attributes.get("amountWithdrawn");

		if (amountWithdrawn != null) {
			setAmountWithdrawn(amountWithdrawn);
		}

		Boolean checkRecord = (Boolean)attributes.get("checkRecord");

		if (checkRecord != null) {
			setCheckRecord(checkRecord);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new IvldInventoryWdProjMasWrapper((IvldInventoryWdProjMas)_ivldInventoryWdProjMas.clone());
	}

	@Override
	public int compareTo(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
		return _ivldInventoryWdProjMas.compareTo(ivldInventoryWdProjMas);
	}

	/**
	* Returns the add chg del indicator of this ivld inventory wd proj mas.
	*
	* @return the add chg del indicator of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getAddChgDelIndicator() {
		return _ivldInventoryWdProjMas.getAddChgDelIndicator();
	}

	/**
	* Returns the amount withdrawn of this ivld inventory wd proj mas.
	*
	* @return the amount withdrawn of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getAmountWithdrawn() {
		return _ivldInventoryWdProjMas.getAmountWithdrawn();
	}

	/**
	* Returns the batch ID of this ivld inventory wd proj mas.
	*
	* @return the batch ID of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getBatchId() {
		return _ivldInventoryWdProjMas.getBatchId();
	}

	/**
	* Returns the check record of this ivld inventory wd proj mas.
	*
	* @return the check record of this ivld inventory wd proj mas
	*/
	@Override
	public boolean getCheckRecord() {
		return _ivldInventoryWdProjMas.getCheckRecord();
	}

	/**
	* Returns the country of this ivld inventory wd proj mas.
	*
	* @return the country of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getCountry() {
		return _ivldInventoryWdProjMas.getCountry();
	}

	/**
	* Returns the created by of this ivld inventory wd proj mas.
	*
	* @return the created by of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getCreatedBy() {
		return _ivldInventoryWdProjMas.getCreatedBy();
	}

	/**
	* Returns the created date of this ivld inventory wd proj mas.
	*
	* @return the created date of this ivld inventory wd proj mas
	*/
	@Override
	public Date getCreatedDate() {
		return _ivldInventoryWdProjMas.getCreatedDate();
	}

	/**
	* Returns the day of this ivld inventory wd proj mas.
	*
	* @return the day of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getDay() {
		return _ivldInventoryWdProjMas.getDay();
	}

	/**
	* Returns the error code of this ivld inventory wd proj mas.
	*
	* @return the error code of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getErrorCode() {
		return _ivldInventoryWdProjMas.getErrorCode();
	}

	/**
	* Returns the error field of this ivld inventory wd proj mas.
	*
	* @return the error field of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getErrorField() {
		return _ivldInventoryWdProjMas.getErrorField();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _ivldInventoryWdProjMas.getExpandoBridge();
	}

	/**
	* Returns the forecast name of this ivld inventory wd proj mas.
	*
	* @return the forecast name of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getForecastName() {
		return _ivldInventoryWdProjMas.getForecastName();
	}

	/**
	* Returns the forecast ver of this ivld inventory wd proj mas.
	*
	* @return the forecast ver of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getForecastVer() {
		return _ivldInventoryWdProjMas.getForecastVer();
	}

	/**
	* Returns the intf inserted date of this ivld inventory wd proj mas.
	*
	* @return the intf inserted date of this ivld inventory wd proj mas
	*/
	@Override
	public Date getIntfInsertedDate() {
		return _ivldInventoryWdProjMas.getIntfInsertedDate();
	}

	/**
	* Returns the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
	*
	* @return the inventory wd proj mas intf ID of this ivld inventory wd proj mas
	*/
	@Override
	public int getInventoryWdProjMasIntfId() {
		return _ivldInventoryWdProjMas.getInventoryWdProjMasIntfId();
	}

	/**
	* Returns the item ID of this ivld inventory wd proj mas.
	*
	* @return the item ID of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemId() {
		return _ivldInventoryWdProjMas.getItemId();
	}

	/**
	* Returns the item identifier of this ivld inventory wd proj mas.
	*
	* @return the item identifier of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemIdentifier() {
		return _ivldInventoryWdProjMas.getItemIdentifier();
	}

	/**
	* Returns the item identifier code qualifier of this ivld inventory wd proj mas.
	*
	* @return the item identifier code qualifier of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getItemIdentifierCodeQualifier() {
		return _ivldInventoryWdProjMas.getItemIdentifierCodeQualifier();
	}

	/**
	* Returns the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
	*
	* @return the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
	*/
	@Override
	public int getIvldInventoryWdProjMasSid() {
		return _ivldInventoryWdProjMas.getIvldInventoryWdProjMasSid();
	}

	/**
	* Returns the modified by of this ivld inventory wd proj mas.
	*
	* @return the modified by of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getModifiedBy() {
		return _ivldInventoryWdProjMas.getModifiedBy();
	}

	/**
	* Returns the modified date of this ivld inventory wd proj mas.
	*
	* @return the modified date of this ivld inventory wd proj mas
	*/
	@Override
	public Date getModifiedDate() {
		return _ivldInventoryWdProjMas.getModifiedDate();
	}

	/**
	* Returns the month of this ivld inventory wd proj mas.
	*
	* @return the month of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getMonth() {
		return _ivldInventoryWdProjMas.getMonth();
	}

	/**
	* Returns the organization key of this ivld inventory wd proj mas.
	*
	* @return the organization key of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getOrganizationKey() {
		return _ivldInventoryWdProjMas.getOrganizationKey();
	}

	/**
	* Returns the primary key of this ivld inventory wd proj mas.
	*
	* @return the primary key of this ivld inventory wd proj mas
	*/
	@Override
	public int getPrimaryKey() {
		return _ivldInventoryWdProjMas.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ivldInventoryWdProjMas.getPrimaryKeyObj();
	}

	/**
	* Returns the reason for failure of this ivld inventory wd proj mas.
	*
	* @return the reason for failure of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getReasonForFailure() {
		return _ivldInventoryWdProjMas.getReasonForFailure();
	}

	/**
	* Returns the reprocessed flag of this ivld inventory wd proj mas.
	*
	* @return the reprocessed flag of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getReprocessedFlag() {
		return _ivldInventoryWdProjMas.getReprocessedFlag();
	}

	/**
	* Returns the source of this ivld inventory wd proj mas.
	*
	* @return the source of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getSource() {
		return _ivldInventoryWdProjMas.getSource();
	}

	/**
	* Returns the units withdrawn of this ivld inventory wd proj mas.
	*
	* @return the units withdrawn of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getUnitsWithdrawn() {
		return _ivldInventoryWdProjMas.getUnitsWithdrawn();
	}

	/**
	* Returns the week of this ivld inventory wd proj mas.
	*
	* @return the week of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getWeek() {
		return _ivldInventoryWdProjMas.getWeek();
	}

	/**
	* Returns the year of this ivld inventory wd proj mas.
	*
	* @return the year of this ivld inventory wd proj mas
	*/
	@Override
	public java.lang.String getYear() {
		return _ivldInventoryWdProjMas.getYear();
	}

	@Override
	public int hashCode() {
		return _ivldInventoryWdProjMas.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _ivldInventoryWdProjMas.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this ivld inventory wd proj mas is check record.
	*
	* @return <code>true</code> if this ivld inventory wd proj mas is check record; <code>false</code> otherwise
	*/
	@Override
	public boolean isCheckRecord() {
		return _ivldInventoryWdProjMas.isCheckRecord();
	}

	@Override
	public boolean isEscapedModel() {
		return _ivldInventoryWdProjMas.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _ivldInventoryWdProjMas.isNew();
	}

	@Override
	public void persist() {
		_ivldInventoryWdProjMas.persist();
	}

	/**
	* Sets the add chg del indicator of this ivld inventory wd proj mas.
	*
	* @param addChgDelIndicator the add chg del indicator of this ivld inventory wd proj mas
	*/
	@Override
	public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
		_ivldInventoryWdProjMas.setAddChgDelIndicator(addChgDelIndicator);
	}

	/**
	* Sets the amount withdrawn of this ivld inventory wd proj mas.
	*
	* @param amountWithdrawn the amount withdrawn of this ivld inventory wd proj mas
	*/
	@Override
	public void setAmountWithdrawn(java.lang.String amountWithdrawn) {
		_ivldInventoryWdProjMas.setAmountWithdrawn(amountWithdrawn);
	}

	/**
	* Sets the batch ID of this ivld inventory wd proj mas.
	*
	* @param batchId the batch ID of this ivld inventory wd proj mas
	*/
	@Override
	public void setBatchId(java.lang.String batchId) {
		_ivldInventoryWdProjMas.setBatchId(batchId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_ivldInventoryWdProjMas.setCachedModel(cachedModel);
	}

	/**
	* Sets whether this ivld inventory wd proj mas is check record.
	*
	* @param checkRecord the check record of this ivld inventory wd proj mas
	*/
	@Override
	public void setCheckRecord(boolean checkRecord) {
		_ivldInventoryWdProjMas.setCheckRecord(checkRecord);
	}

	/**
	* Sets the country of this ivld inventory wd proj mas.
	*
	* @param country the country of this ivld inventory wd proj mas
	*/
	@Override
	public void setCountry(java.lang.String country) {
		_ivldInventoryWdProjMas.setCountry(country);
	}

	/**
	* Sets the created by of this ivld inventory wd proj mas.
	*
	* @param createdBy the created by of this ivld inventory wd proj mas
	*/
	@Override
	public void setCreatedBy(java.lang.String createdBy) {
		_ivldInventoryWdProjMas.setCreatedBy(createdBy);
	}

	/**
	* Sets the created date of this ivld inventory wd proj mas.
	*
	* @param createdDate the created date of this ivld inventory wd proj mas
	*/
	@Override
	public void setCreatedDate(Date createdDate) {
		_ivldInventoryWdProjMas.setCreatedDate(createdDate);
	}

	/**
	* Sets the day of this ivld inventory wd proj mas.
	*
	* @param day the day of this ivld inventory wd proj mas
	*/
	@Override
	public void setDay(java.lang.String day) {
		_ivldInventoryWdProjMas.setDay(day);
	}

	/**
	* Sets the error code of this ivld inventory wd proj mas.
	*
	* @param errorCode the error code of this ivld inventory wd proj mas
	*/
	@Override
	public void setErrorCode(java.lang.String errorCode) {
		_ivldInventoryWdProjMas.setErrorCode(errorCode);
	}

	/**
	* Sets the error field of this ivld inventory wd proj mas.
	*
	* @param errorField the error field of this ivld inventory wd proj mas
	*/
	@Override
	public void setErrorField(java.lang.String errorField) {
		_ivldInventoryWdProjMas.setErrorField(errorField);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_ivldInventoryWdProjMas.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_ivldInventoryWdProjMas.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_ivldInventoryWdProjMas.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the forecast name of this ivld inventory wd proj mas.
	*
	* @param forecastName the forecast name of this ivld inventory wd proj mas
	*/
	@Override
	public void setForecastName(java.lang.String forecastName) {
		_ivldInventoryWdProjMas.setForecastName(forecastName);
	}

	/**
	* Sets the forecast ver of this ivld inventory wd proj mas.
	*
	* @param forecastVer the forecast ver of this ivld inventory wd proj mas
	*/
	@Override
	public void setForecastVer(java.lang.String forecastVer) {
		_ivldInventoryWdProjMas.setForecastVer(forecastVer);
	}

	/**
	* Sets the intf inserted date of this ivld inventory wd proj mas.
	*
	* @param intfInsertedDate the intf inserted date of this ivld inventory wd proj mas
	*/
	@Override
	public void setIntfInsertedDate(Date intfInsertedDate) {
		_ivldInventoryWdProjMas.setIntfInsertedDate(intfInsertedDate);
	}

	/**
	* Sets the inventory wd proj mas intf ID of this ivld inventory wd proj mas.
	*
	* @param inventoryWdProjMasIntfId the inventory wd proj mas intf ID of this ivld inventory wd proj mas
	*/
	@Override
	public void setInventoryWdProjMasIntfId(int inventoryWdProjMasIntfId) {
		_ivldInventoryWdProjMas.setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);
	}

	/**
	* Sets the item ID of this ivld inventory wd proj mas.
	*
	* @param itemId the item ID of this ivld inventory wd proj mas
	*/
	@Override
	public void setItemId(java.lang.String itemId) {
		_ivldInventoryWdProjMas.setItemId(itemId);
	}

	/**
	* Sets the item identifier of this ivld inventory wd proj mas.
	*
	* @param itemIdentifier the item identifier of this ivld inventory wd proj mas
	*/
	@Override
	public void setItemIdentifier(java.lang.String itemIdentifier) {
		_ivldInventoryWdProjMas.setItemIdentifier(itemIdentifier);
	}

	/**
	* Sets the item identifier code qualifier of this ivld inventory wd proj mas.
	*
	* @param itemIdentifierCodeQualifier the item identifier code qualifier of this ivld inventory wd proj mas
	*/
	@Override
	public void setItemIdentifierCodeQualifier(
		java.lang.String itemIdentifierCodeQualifier) {
		_ivldInventoryWdProjMas.setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
	}

	/**
	* Sets the ivld inventory wd proj mas sid of this ivld inventory wd proj mas.
	*
	* @param ivldInventoryWdProjMasSid the ivld inventory wd proj mas sid of this ivld inventory wd proj mas
	*/
	@Override
	public void setIvldInventoryWdProjMasSid(int ivldInventoryWdProjMasSid) {
		_ivldInventoryWdProjMas.setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);
	}

	/**
	* Sets the modified by of this ivld inventory wd proj mas.
	*
	* @param modifiedBy the modified by of this ivld inventory wd proj mas
	*/
	@Override
	public void setModifiedBy(java.lang.String modifiedBy) {
		_ivldInventoryWdProjMas.setModifiedBy(modifiedBy);
	}

	/**
	* Sets the modified date of this ivld inventory wd proj mas.
	*
	* @param modifiedDate the modified date of this ivld inventory wd proj mas
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_ivldInventoryWdProjMas.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the month of this ivld inventory wd proj mas.
	*
	* @param month the month of this ivld inventory wd proj mas
	*/
	@Override
	public void setMonth(java.lang.String month) {
		_ivldInventoryWdProjMas.setMonth(month);
	}

	@Override
	public void setNew(boolean n) {
		_ivldInventoryWdProjMas.setNew(n);
	}

	/**
	* Sets the organization key of this ivld inventory wd proj mas.
	*
	* @param organizationKey the organization key of this ivld inventory wd proj mas
	*/
	@Override
	public void setOrganizationKey(java.lang.String organizationKey) {
		_ivldInventoryWdProjMas.setOrganizationKey(organizationKey);
	}

	/**
	* Sets the primary key of this ivld inventory wd proj mas.
	*
	* @param primaryKey the primary key of this ivld inventory wd proj mas
	*/
	@Override
	public void setPrimaryKey(int primaryKey) {
		_ivldInventoryWdProjMas.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_ivldInventoryWdProjMas.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the reason for failure of this ivld inventory wd proj mas.
	*
	* @param reasonForFailure the reason for failure of this ivld inventory wd proj mas
	*/
	@Override
	public void setReasonForFailure(java.lang.String reasonForFailure) {
		_ivldInventoryWdProjMas.setReasonForFailure(reasonForFailure);
	}

	/**
	* Sets the reprocessed flag of this ivld inventory wd proj mas.
	*
	* @param reprocessedFlag the reprocessed flag of this ivld inventory wd proj mas
	*/
	@Override
	public void setReprocessedFlag(java.lang.String reprocessedFlag) {
		_ivldInventoryWdProjMas.setReprocessedFlag(reprocessedFlag);
	}

	/**
	* Sets the source of this ivld inventory wd proj mas.
	*
	* @param source the source of this ivld inventory wd proj mas
	*/
	@Override
	public void setSource(java.lang.String source) {
		_ivldInventoryWdProjMas.setSource(source);
	}

	/**
	* Sets the units withdrawn of this ivld inventory wd proj mas.
	*
	* @param unitsWithdrawn the units withdrawn of this ivld inventory wd proj mas
	*/
	@Override
	public void setUnitsWithdrawn(java.lang.String unitsWithdrawn) {
		_ivldInventoryWdProjMas.setUnitsWithdrawn(unitsWithdrawn);
	}

	/**
	* Sets the week of this ivld inventory wd proj mas.
	*
	* @param week the week of this ivld inventory wd proj mas
	*/
	@Override
	public void setWeek(java.lang.String week) {
		_ivldInventoryWdProjMas.setWeek(week);
	}

	/**
	* Sets the year of this ivld inventory wd proj mas.
	*
	* @param year the year of this ivld inventory wd proj mas
	*/
	@Override
	public void setYear(java.lang.String year) {
		_ivldInventoryWdProjMas.setYear(year);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<IvldInventoryWdProjMas> toCacheModel() {
		return _ivldInventoryWdProjMas.toCacheModel();
	}

	@Override
	public IvldInventoryWdProjMas toEscapedModel() {
		return new IvldInventoryWdProjMasWrapper(_ivldInventoryWdProjMas.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _ivldInventoryWdProjMas.toString();
	}

	@Override
	public IvldInventoryWdProjMas toUnescapedModel() {
		return new IvldInventoryWdProjMasWrapper(_ivldInventoryWdProjMas.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _ivldInventoryWdProjMas.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof IvldInventoryWdProjMasWrapper)) {
			return false;
		}

		IvldInventoryWdProjMasWrapper ivldInventoryWdProjMasWrapper = (IvldInventoryWdProjMasWrapper)obj;

		if (Objects.equals(_ivldInventoryWdProjMas,
					ivldInventoryWdProjMasWrapper._ivldInventoryWdProjMas)) {
			return true;
		}

		return false;
	}

	@Override
	public IvldInventoryWdProjMas getWrappedModel() {
		return _ivldInventoryWdProjMas;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _ivldInventoryWdProjMas.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _ivldInventoryWdProjMas.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_ivldInventoryWdProjMas.resetOriginalValues();
	}

	private final IvldInventoryWdProjMas _ivldInventoryWdProjMas;
}