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
public class VwInventoryWdActualProjMasSoap implements Serializable {
	public static VwInventoryWdActualProjMasSoap toSoapModel(
		VwInventoryWdActualProjMas model) {
		VwInventoryWdActualProjMasSoap soapModel = new VwInventoryWdActualProjMasSoap();

		soapModel.setQuantityOnOrder(model.getQuantityOnOrder());
		soapModel.setWeek(model.getWeek());
		soapModel.setPrice(model.getPrice());
		soapModel.setAmountOnHand(model.getAmountOnHand());
		soapModel.setIsMaster(model.getIsMaster());
		soapModel.setCompanyName(model.getCompanyName());
		soapModel.setYear(model.getYear());
		soapModel.setItemId(model.getItemId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOrganizationKey(model.getOrganizationKey());
		soapModel.setInventoryWdActualProjMasSid(model.getInventoryWdActualProjMasSid());
		soapModel.setSource(model.getSource());
		soapModel.setCreatedBy(model.getCreatedBy());
		soapModel.setCreatedDate(model.getCreatedDate());
		soapModel.setDay(model.getDay());
		soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
		soapModel.setUnitsOnHand(model.getUnitsOnHand());
		soapModel.setAmountReceived(model.getAmountReceived());
		soapModel.setModifiedBy(model.getModifiedBy());
		soapModel.setMonth(model.getMonth());
		soapModel.setAmountWithdrawn(model.getAmountWithdrawn());
		soapModel.setQuantityReceived(model.getQuantityReceived());
		soapModel.setUnitsWithdrawn(model.getUnitsWithdrawn());
		soapModel.setCountry(model.getCountry());
		soapModel.setCompanyStringId(model.getCompanyStringId());
		soapModel.setIsForecast(model.getIsForecast());
		soapModel.setForecastVer(model.getForecastVer());
		soapModel.setBatchId(model.getBatchId());
		soapModel.setItemName(model.getItemName());
		soapModel.setAmountOnOrder(model.getAmountOnOrder());
		soapModel.setForecastName(model.getForecastName());

		return soapModel;
	}

	public static VwInventoryWdActualProjMasSoap[] toSoapModels(
		VwInventoryWdActualProjMas[] models) {
		VwInventoryWdActualProjMasSoap[] soapModels = new VwInventoryWdActualProjMasSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VwInventoryWdActualProjMasSoap[][] toSoapModels(
		VwInventoryWdActualProjMas[][] models) {
		VwInventoryWdActualProjMasSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VwInventoryWdActualProjMasSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VwInventoryWdActualProjMasSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VwInventoryWdActualProjMasSoap[] toSoapModels(
		List<VwInventoryWdActualProjMas> models) {
		List<VwInventoryWdActualProjMasSoap> soapModels = new ArrayList<VwInventoryWdActualProjMasSoap>(models.size());

		for (VwInventoryWdActualProjMas model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VwInventoryWdActualProjMasSoap[soapModels.size()]);
	}

	public VwInventoryWdActualProjMasSoap() {
	}

	public int getPrimaryKey() {
		return _inventoryWdActualProjMasSid;
	}

	public void setPrimaryKey(int pk) {
		setInventoryWdActualProjMasSid(pk);
	}

	public double getQuantityOnOrder() {
		return _quantityOnOrder;
	}

	public void setQuantityOnOrder(double quantityOnOrder) {
		_quantityOnOrder = quantityOnOrder;
	}

	public String getWeek() {
		return _week;
	}

	public void setWeek(String week) {
		_week = week;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public double getAmountOnHand() {
		return _amountOnHand;
	}

	public void setAmountOnHand(double amountOnHand) {
		_amountOnHand = amountOnHand;
	}

	public String getIsMaster() {
		return _isMaster;
	}

	public void setIsMaster(String isMaster) {
		_isMaster = isMaster;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
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

	public int getInventoryWdActualProjMasSid() {
		return _inventoryWdActualProjMasSid;
	}

	public void setInventoryWdActualProjMasSid(int inventoryWdActualProjMasSid) {
		_inventoryWdActualProjMasSid = inventoryWdActualProjMasSid;
	}

	public String getSource() {
		return _source;
	}

	public void setSource(String source) {
		_source = source;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	public String getDay() {
		return _day;
	}

	public void setDay(String day) {
		_day = day;
	}

	public String getAddChgDelIndicator() {
		return _addChgDelIndicator;
	}

	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	public double getUnitsOnHand() {
		return _unitsOnHand;
	}

	public void setUnitsOnHand(double unitsOnHand) {
		_unitsOnHand = unitsOnHand;
	}

	public double getAmountReceived() {
		return _amountReceived;
	}

	public void setAmountReceived(double amountReceived) {
		_amountReceived = amountReceived;
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

	public double getAmountWithdrawn() {
		return _amountWithdrawn;
	}

	public void setAmountWithdrawn(double amountWithdrawn) {
		_amountWithdrawn = amountWithdrawn;
	}

	public double getQuantityReceived() {
		return _quantityReceived;
	}

	public void setQuantityReceived(double quantityReceived) {
		_quantityReceived = quantityReceived;
	}

	public double getUnitsWithdrawn() {
		return _unitsWithdrawn;
	}

	public void setUnitsWithdrawn(double unitsWithdrawn) {
		_unitsWithdrawn = unitsWithdrawn;
	}

	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	public String getCompanyStringId() {
		return _companyStringId;
	}

	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	public String getIsForecast() {
		return _isForecast;
	}

	public void setIsForecast(String isForecast) {
		_isForecast = isForecast;
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

	public String getItemName() {
		return _itemName;
	}

	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	public double getAmountOnOrder() {
		return _amountOnOrder;
	}

	public void setAmountOnOrder(double amountOnOrder) {
		_amountOnOrder = amountOnOrder;
	}

	public String getForecastName() {
		return _forecastName;
	}

	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	private double _quantityOnOrder;
	private String _week;
	private double _price;
	private double _amountOnHand;
	private String _isMaster;
	private String _companyName;
	private String _year;
	private String _itemId;
	private Date _modifiedDate;
	private String _organizationKey;
	private int _inventoryWdActualProjMasSid;
	private String _source;
	private String _createdBy;
	private Date _createdDate;
	private String _day;
	private String _addChgDelIndicator;
	private double _unitsOnHand;
	private double _amountReceived;
	private String _modifiedBy;
	private String _month;
	private double _amountWithdrawn;
	private double _quantityReceived;
	private double _unitsWithdrawn;
	private String _country;
	private String _companyStringId;
	private String _isForecast;
	private String _forecastVer;
	private String _batchId;
	private String _itemName;
	private double _amountOnOrder;
	private String _forecastName;
}