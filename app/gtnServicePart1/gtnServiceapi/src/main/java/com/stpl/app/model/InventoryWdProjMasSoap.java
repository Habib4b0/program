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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
@ProviderType
public class InventoryWdProjMasSoap implements Serializable {
	public static InventoryWdProjMasSoap toSoapModel(InventoryWdProjMas model) {
		InventoryWdProjMasSoap soapModel = new InventoryWdProjMasSoap();

		soapModel.setWeek(model.getWeek());
		soapModel.setItemMasterSid(model.getItemMasterSid());
		soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
		soapModel.setCountry(model.getCountry());
		soapModel.setYear(model.getYear());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setRecordLockStatus(model.getRecordLockStatus());
		soapModel.setItemIdentifierCodeQualifier(model.getItemIdentifierCodeQualifier());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setInventoryWdProjMasSid(model.getInventoryWdProjMasSid());
		soapModel.setDay(model.getDay());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemIdentifier(model.getItemIdentifier());
		soapModel.setInboundStatus(model.getInboundStatus());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMonth(model.getMonth());
		soapModel.setForecastName(model.getForecastName());
		soapModel.setAmountWithdrawn(model.getAmountWithdrawn());

		return soapModel;
	}

	public static InventoryWdProjMasSoap[] toSoapModels(
		InventoryWdProjMas[] models) {
		InventoryWdProjMasSoap[] soapModels = new InventoryWdProjMasSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static InventoryWdProjMasSoap[][] toSoapModels(
		InventoryWdProjMas[][] models) {
		InventoryWdProjMasSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new InventoryWdProjMasSoap[models.length][models[0].length];
		}
		else {
			soapModels = new InventoryWdProjMasSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static InventoryWdProjMasSoap[] toSoapModels(
		List<InventoryWdProjMas> models) {
		List<InventoryWdProjMasSoap> soapModels = new ArrayList<InventoryWdProjMasSoap>(models.size());

		for (InventoryWdProjMas model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new InventoryWdProjMasSoap[soapModels.size()]);
	}

	public InventoryWdProjMasSoap() {
	}

	public int getPrimaryKey() {
		return _inventoryWdProjMasSid;
	}

	public void setPrimaryKey(int pk) {
		setInventoryWdProjMasSid(pk);
	}

	public String getWeek() {
		return _week;
	}

	public void setWeek(String week) {
		_week = week;
	}

	public int getItemMasterSid() {
		return _itemMasterSid;
	}

	public void setItemMasterSid(int itemMasterSid) {
		_itemMasterSid = itemMasterSid;
	}

	public String getUnitsWithdrawn() {
		return _unitsWithdrawn;
	}

	public void setUnitsWithdrawn(String unitsWithdrawn) {
		_unitsWithdrawn = unitsWithdrawn;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getYear() {
		return _year;
	}

	public void setYear(String year) {
		_year = year;
	}

	public String getItemId() {
		return _itemId;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getOrganizationKey() {
		return _organizationKey;
	}

	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	public boolean getRecordLockStatus() {
		return _recordLockStatus;
	}

	public boolean isRecordLockStatus() {
		return _recordLockStatus;
	}

	public void setRecordLockStatus(boolean recordLockStatus) {
		_recordLockStatus = recordLockStatus;
	}

	public String getItemIdentifierCodeQualifier() {
		return _itemIdentifierCodeQualifier;
	}

	public void setItemIdentifierCodeQualifier(
		String itemIdentifierCodeQualifier) {
		_itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public int getInventoryWdProjMasSid() {
		return _inventoryWdProjMasSid;
	}

	public void setInventoryWdProjMasSid(int inventoryWdProjMasSid) {
		_inventoryWdProjMasSid = inventoryWdProjMasSid;
	}

	public String getDay() {
		return _day;
	}

	public void setDay(String day) {
		_day = day;
	}

	public String getForecastVer() {
		return _forecastVer;
	}

	public void setForecastVer(String forecastVer) {
		_forecastVer = forecastVer;
	}

	public String getBatchId() {
		return _batchId;
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public String getItemIdentifier() {
		return _itemIdentifier;
	}

	public void setItemIdentifier(String itemIdentifier) {
		_itemIdentifier = itemIdentifier;
	}

	public String getInboundStatus() {
		return _inboundStatus;
	}

	public void setInboundStatus(String inboundStatus) {
		_inboundStatus = inboundStatus;
	}

	public String getModifiedBy() {
		return _modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	public String getMonth() {
		return _month;
	}

	public void setMonth(String month) {
		_month = month;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	public String getAmountWithdrawn() {
		return _amountWithdrawn;
	}

	public void setAmountWithdrawn(String amountWithdrawn) {
		_amountWithdrawn = amountWithdrawn;
	}

	private String _week;
	private int _itemMasterSid;
	private String _unitsWithdrawn;
	private String _country;
	private String _year;
	private String _itemId;
	private Date _modifiedDate;
	private String _organizationKey;
	private boolean _recordLockStatus;
	private String _itemIdentifierCodeQualifier;
	private String _source;
	private Date _createdDate;
	private String _createdBy;
	private int _inventoryWdProjMasSid;
	private String _day;
	private String _forecastVer;
	private String _batchId;
	private String _itemIdentifier;
	private String _inboundStatus;
	private String _modifiedBy;
	private String _month;
	private String _forecastName;
	private String _amountWithdrawn;
}