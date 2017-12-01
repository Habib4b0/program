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

package com.stpl.app.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.VwInventoryWdActualProjMasModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the VwInventoryWdActualProjMas service. Represents a row in the &quot;VW_INVENTORY_WD_ACTUAL_PROJ_MAS&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link VwInventoryWdActualProjMasModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link VwInventoryWdActualProjMasImpl}.
 * </p>
 *
 * @author
 * @see VwInventoryWdActualProjMasImpl
 * @see VwInventoryWdActualProjMas
 * @see VwInventoryWdActualProjMasModel
 * @generated
 */
@ProviderType
public class VwInventoryWdActualProjMasModelImpl extends BaseModelImpl<VwInventoryWdActualProjMas>
	implements VwInventoryWdActualProjMasModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a vw inventory wd actual proj mas model instance should use the {@link VwInventoryWdActualProjMas} interface instead.
	 */
	public static final String TABLE_NAME = "VW_INVENTORY_WD_ACTUAL_PROJ_MAS";
	public static final Object[][] TABLE_COLUMNS = {
			{ "QUANTITY_ON_ORDER", Types.DOUBLE },
			{ "WEEK", Types.VARCHAR },
			{ "PRICE", Types.DOUBLE },
			{ "AMOUNT_ON_HAND", Types.DOUBLE },
			{ "IS_MASTER", Types.VARCHAR },
			{ "COMPANY_NAME", Types.VARCHAR },
			{ "YEAR", Types.VARCHAR },
			{ "ITEM_ID", Types.VARCHAR },
			{ "MODIFIED_DATE", Types.TIMESTAMP },
			{ "ORGANIZATION_KEY", Types.VARCHAR },
			{ "INVENTORY_WD_ACTUAL_PROJ_MAS_SID", Types.INTEGER },
			{ "SOURCE", Types.VARCHAR },
			{ "CREATED_BY", Types.VARCHAR },
			{ "CREATED_DATE", Types.TIMESTAMP },
			{ "DAY", Types.VARCHAR },
			{ "ADD_CHG_DEL_INDICATOR", Types.VARCHAR },
			{ "UNITS_ON_HAND", Types.DOUBLE },
			{ "AMOUNT_RECEIVED", Types.DOUBLE },
			{ "MODIFIED_BY", Types.VARCHAR },
			{ "MONTH", Types.VARCHAR },
			{ "AMOUNT_WITHDRAWN", Types.DOUBLE },
			{ "QUANTITY_RECEIVED", Types.DOUBLE },
			{ "UNITS_WITHDRAWN", Types.DOUBLE },
			{ "COUNTRY", Types.VARCHAR },
			{ "COMPANY_ID", Types.VARCHAR },
			{ "IS_FORECAST", Types.VARCHAR },
			{ "FORECAST_VER", Types.VARCHAR },
			{ "BATCH_ID", Types.VARCHAR },
			{ "ITEM_NAME", Types.VARCHAR },
			{ "AMOUNT_ON_ORDER", Types.DOUBLE },
			{ "FORECAST_NAME", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("QUANTITY_ON_ORDER", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("WEEK", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("PRICE", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("AMOUNT_ON_HAND", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("IS_MASTER", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("YEAR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MODIFIED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("ORGANIZATION_KEY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("INVENTORY_WD_ACTUAL_PROJ_MAS_SID", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("SOURCE", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_BY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CREATED_DATE", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("DAY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ADD_CHG_DEL_INDICATOR", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("UNITS_ON_HAND", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("AMOUNT_RECEIVED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("MODIFIED_BY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("MONTH", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("AMOUNT_WITHDRAWN", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("QUANTITY_RECEIVED", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("UNITS_WITHDRAWN", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("COUNTRY", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("COMPANY_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("IS_FORECAST", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("FORECAST_VER", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("BATCH_ID", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("ITEM_NAME", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("AMOUNT_ON_ORDER", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("FORECAST_NAME", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table VW_INVENTORY_WD_ACTUAL_PROJ_MAS (QUANTITY_ON_ORDER DOUBLE,WEEK VARCHAR(75) null,PRICE DOUBLE,AMOUNT_ON_HAND DOUBLE,IS_MASTER VARCHAR(75) null,COMPANY_NAME VARCHAR(75) null,YEAR VARCHAR(75) null,ITEM_ID VARCHAR(75) null,MODIFIED_DATE DATE null,ORGANIZATION_KEY VARCHAR(75) null,INVENTORY_WD_ACTUAL_PROJ_MAS_SID INTEGER not null primary key,SOURCE VARCHAR(75) null,CREATED_BY VARCHAR(75) null,CREATED_DATE DATE null,DAY VARCHAR(75) null,ADD_CHG_DEL_INDICATOR VARCHAR(75) null,UNITS_ON_HAND DOUBLE,AMOUNT_RECEIVED DOUBLE,MODIFIED_BY VARCHAR(75) null,MONTH VARCHAR(75) null,AMOUNT_WITHDRAWN DOUBLE,QUANTITY_RECEIVED DOUBLE,UNITS_WITHDRAWN DOUBLE,COUNTRY VARCHAR(75) null,COMPANY_ID VARCHAR(75) null,IS_FORECAST VARCHAR(75) null,FORECAST_VER VARCHAR(75) null,BATCH_ID VARCHAR(75) null,ITEM_NAME VARCHAR(75) null,AMOUNT_ON_ORDER DOUBLE,FORECAST_NAME VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table VW_INVENTORY_WD_ACTUAL_PROJ_MAS";
	public static final String ORDER_BY_JPQL = " ORDER BY vwInventoryWdActualProjMas.inventoryWdActualProjMasSid ASC";
	public static final String ORDER_BY_SQL = " ORDER BY VW_INVENTORY_WD_ACTUAL_PROJ_MAS.INVENTORY_WD_ACTUAL_PROJ_MAS_SID ASC";
	public static final String DATA_SOURCE = "extDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.stpl.app.model.VwInventoryWdActualProjMas"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.stpl.app.service.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.stpl.app.model.VwInventoryWdActualProjMas"),
			false);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.stpl.app.service.util.PropsUtil.get(
				"lock.expiration.time.com.stpl.app.model.VwInventoryWdActualProjMas"));

	public VwInventoryWdActualProjMasModelImpl() {
	}

	@Override
	public int getPrimaryKey() {
		return _inventoryWdActualProjMasSid;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setInventoryWdActualProjMasSid(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _inventoryWdActualProjMasSid;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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
	public double getQuantityOnOrder() {
		return _quantityOnOrder;
	}

	@Override
	public void setQuantityOnOrder(double quantityOnOrder) {
		_quantityOnOrder = quantityOnOrder;
	}

	@Override
	public String getWeek() {
		if (_week == null) {
			return StringPool.BLANK;
		}
		else {
			return _week;
		}
	}

	@Override
	public void setWeek(String week) {
		_week = week;
	}

	@Override
	public double getPrice() {
		return _price;
	}

	@Override
	public void setPrice(double price) {
		_price = price;
	}

	@Override
	public double getAmountOnHand() {
		return _amountOnHand;
	}

	@Override
	public void setAmountOnHand(double amountOnHand) {
		_amountOnHand = amountOnHand;
	}

	@Override
	public String getIsMaster() {
		if (_isMaster == null) {
			return StringPool.BLANK;
		}
		else {
			return _isMaster;
		}
	}

	@Override
	public void setIsMaster(String isMaster) {
		_isMaster = isMaster;
	}

	@Override
	public String getCompanyName() {
		if (_companyName == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyName;
		}
	}

	@Override
	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	@Override
	public String getYear() {
		if (_year == null) {
			return StringPool.BLANK;
		}
		else {
			return _year;
		}
	}

	@Override
	public void setYear(String year) {
		_year = year;
	}

	@Override
	public String getItemId() {
		if (_itemId == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemId;
		}
	}

	@Override
	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public String getOrganizationKey() {
		if (_organizationKey == null) {
			return StringPool.BLANK;
		}
		else {
			return _organizationKey;
		}
	}

	@Override
	public void setOrganizationKey(String organizationKey) {
		_organizationKey = organizationKey;
	}

	@Override
	public int getInventoryWdActualProjMasSid() {
		return _inventoryWdActualProjMasSid;
	}

	@Override
	public void setInventoryWdActualProjMasSid(int inventoryWdActualProjMasSid) {
		_inventoryWdActualProjMasSid = inventoryWdActualProjMasSid;
	}

	@Override
	public String getSource() {
		if (_source == null) {
			return StringPool.BLANK;
		}
		else {
			return _source;
		}
	}

	@Override
	public void setSource(String source) {
		_source = source;
	}

	@Override
	public String getCreatedBy() {
		if (_createdBy == null) {
			return StringPool.BLANK;
		}
		else {
			return _createdBy;
		}
	}

	@Override
	public void setCreatedBy(String createdBy) {
		_createdBy = createdBy;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	@Override
	public String getDay() {
		if (_day == null) {
			return StringPool.BLANK;
		}
		else {
			return _day;
		}
	}

	@Override
	public void setDay(String day) {
		_day = day;
	}

	@Override
	public String getAddChgDelIndicator() {
		if (_addChgDelIndicator == null) {
			return StringPool.BLANK;
		}
		else {
			return _addChgDelIndicator;
		}
	}

	@Override
	public void setAddChgDelIndicator(String addChgDelIndicator) {
		_addChgDelIndicator = addChgDelIndicator;
	}

	@Override
	public double getUnitsOnHand() {
		return _unitsOnHand;
	}

	@Override
	public void setUnitsOnHand(double unitsOnHand) {
		_unitsOnHand = unitsOnHand;
	}

	@Override
	public double getAmountReceived() {
		return _amountReceived;
	}

	@Override
	public void setAmountReceived(double amountReceived) {
		_amountReceived = amountReceived;
	}

	@Override
	public String getModifiedBy() {
		if (_modifiedBy == null) {
			return StringPool.BLANK;
		}
		else {
			return _modifiedBy;
		}
	}

	@Override
	public void setModifiedBy(String modifiedBy) {
		_modifiedBy = modifiedBy;
	}

	@Override
	public String getMonth() {
		if (_month == null) {
			return StringPool.BLANK;
		}
		else {
			return _month;
		}
	}

	@Override
	public void setMonth(String month) {
		_month = month;
	}

	@Override
	public double getAmountWithdrawn() {
		return _amountWithdrawn;
	}

	@Override
	public void setAmountWithdrawn(double amountWithdrawn) {
		_amountWithdrawn = amountWithdrawn;
	}

	@Override
	public double getQuantityReceived() {
		return _quantityReceived;
	}

	@Override
	public void setQuantityReceived(double quantityReceived) {
		_quantityReceived = quantityReceived;
	}

	@Override
	public double getUnitsWithdrawn() {
		return _unitsWithdrawn;
	}

	@Override
	public void setUnitsWithdrawn(double unitsWithdrawn) {
		_unitsWithdrawn = unitsWithdrawn;
	}

	@Override
	public String getCountry() {
		if (_country == null) {
			return StringPool.BLANK;
		}
		else {
			return _country;
		}
	}

	@Override
	public void setCountry(String country) {
		_country = country;
	}

	@Override
	public String getCompanyStringId() {
		if (_companyStringId == null) {
			return StringPool.BLANK;
		}
		else {
			return _companyStringId;
		}
	}

	@Override
	public void setCompanyStringId(String companyStringId) {
		_companyStringId = companyStringId;
	}

	@Override
	public String getIsForecast() {
		if (_isForecast == null) {
			return StringPool.BLANK;
		}
		else {
			return _isForecast;
		}
	}

	@Override
	public void setIsForecast(String isForecast) {
		_isForecast = isForecast;
	}

	@Override
	public String getForecastVer() {
		if (_forecastVer == null) {
			return StringPool.BLANK;
		}
		else {
			return _forecastVer;
		}
	}

	@Override
	public void setForecastVer(String forecastVer) {
		_forecastVer = forecastVer;
	}

	@Override
	public String getBatchId() {
		if (_batchId == null) {
			return StringPool.BLANK;
		}
		else {
			return _batchId;
		}
	}

	@Override
	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	@Override
	public String getItemName() {
		if (_itemName == null) {
			return StringPool.BLANK;
		}
		else {
			return _itemName;
		}
	}

	@Override
	public void setItemName(String itemName) {
		_itemName = itemName;
	}

	@Override
	public double getAmountOnOrder() {
		return _amountOnOrder;
	}

	@Override
	public void setAmountOnOrder(double amountOnOrder) {
		_amountOnOrder = amountOnOrder;
	}

	@Override
	public String getForecastName() {
		if (_forecastName == null) {
			return StringPool.BLANK;
		}
		else {
			return _forecastName;
		}
	}

	@Override
	public void setForecastName(String forecastName) {
		_forecastName = forecastName;
	}

	@Override
	public VwInventoryWdActualProjMas toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (VwInventoryWdActualProjMas)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		VwInventoryWdActualProjMasImpl vwInventoryWdActualProjMasImpl = new VwInventoryWdActualProjMasImpl();

		vwInventoryWdActualProjMasImpl.setQuantityOnOrder(getQuantityOnOrder());
		vwInventoryWdActualProjMasImpl.setWeek(getWeek());
		vwInventoryWdActualProjMasImpl.setPrice(getPrice());
		vwInventoryWdActualProjMasImpl.setAmountOnHand(getAmountOnHand());
		vwInventoryWdActualProjMasImpl.setIsMaster(getIsMaster());
		vwInventoryWdActualProjMasImpl.setCompanyName(getCompanyName());
		vwInventoryWdActualProjMasImpl.setYear(getYear());
		vwInventoryWdActualProjMasImpl.setItemId(getItemId());
		vwInventoryWdActualProjMasImpl.setModifiedDate(getModifiedDate());
		vwInventoryWdActualProjMasImpl.setOrganizationKey(getOrganizationKey());
		vwInventoryWdActualProjMasImpl.setInventoryWdActualProjMasSid(getInventoryWdActualProjMasSid());
		vwInventoryWdActualProjMasImpl.setSource(getSource());
		vwInventoryWdActualProjMasImpl.setCreatedBy(getCreatedBy());
		vwInventoryWdActualProjMasImpl.setCreatedDate(getCreatedDate());
		vwInventoryWdActualProjMasImpl.setDay(getDay());
		vwInventoryWdActualProjMasImpl.setAddChgDelIndicator(getAddChgDelIndicator());
		vwInventoryWdActualProjMasImpl.setUnitsOnHand(getUnitsOnHand());
		vwInventoryWdActualProjMasImpl.setAmountReceived(getAmountReceived());
		vwInventoryWdActualProjMasImpl.setModifiedBy(getModifiedBy());
		vwInventoryWdActualProjMasImpl.setMonth(getMonth());
		vwInventoryWdActualProjMasImpl.setAmountWithdrawn(getAmountWithdrawn());
		vwInventoryWdActualProjMasImpl.setQuantityReceived(getQuantityReceived());
		vwInventoryWdActualProjMasImpl.setUnitsWithdrawn(getUnitsWithdrawn());
		vwInventoryWdActualProjMasImpl.setCountry(getCountry());
		vwInventoryWdActualProjMasImpl.setCompanyStringId(getCompanyStringId());
		vwInventoryWdActualProjMasImpl.setIsForecast(getIsForecast());
		vwInventoryWdActualProjMasImpl.setForecastVer(getForecastVer());
		vwInventoryWdActualProjMasImpl.setBatchId(getBatchId());
		vwInventoryWdActualProjMasImpl.setItemName(getItemName());
		vwInventoryWdActualProjMasImpl.setAmountOnOrder(getAmountOnOrder());
		vwInventoryWdActualProjMasImpl.setForecastName(getForecastName());

		vwInventoryWdActualProjMasImpl.resetOriginalValues();

		return vwInventoryWdActualProjMasImpl;
	}

	@Override
	public int compareTo(VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
		int primaryKey = vwInventoryWdActualProjMas.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof VwInventoryWdActualProjMas)) {
			return false;
		}

		VwInventoryWdActualProjMas vwInventoryWdActualProjMas = (VwInventoryWdActualProjMas)obj;

		int primaryKey = vwInventoryWdActualProjMas.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<VwInventoryWdActualProjMas> toCacheModel() {
		VwInventoryWdActualProjMasCacheModel vwInventoryWdActualProjMasCacheModel =
			new VwInventoryWdActualProjMasCacheModel();

		vwInventoryWdActualProjMasCacheModel.quantityOnOrder = getQuantityOnOrder();

		vwInventoryWdActualProjMasCacheModel.week = getWeek();

		String week = vwInventoryWdActualProjMasCacheModel.week;

		if ((week != null) && (week.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.week = null;
		}

		vwInventoryWdActualProjMasCacheModel.price = getPrice();

		vwInventoryWdActualProjMasCacheModel.amountOnHand = getAmountOnHand();

		vwInventoryWdActualProjMasCacheModel.isMaster = getIsMaster();

		String isMaster = vwInventoryWdActualProjMasCacheModel.isMaster;

		if ((isMaster != null) && (isMaster.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.isMaster = null;
		}

		vwInventoryWdActualProjMasCacheModel.companyName = getCompanyName();

		String companyName = vwInventoryWdActualProjMasCacheModel.companyName;

		if ((companyName != null) && (companyName.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.companyName = null;
		}

		vwInventoryWdActualProjMasCacheModel.year = getYear();

		String year = vwInventoryWdActualProjMasCacheModel.year;

		if ((year != null) && (year.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.year = null;
		}

		vwInventoryWdActualProjMasCacheModel.itemId = getItemId();

		String itemId = vwInventoryWdActualProjMasCacheModel.itemId;

		if ((itemId != null) && (itemId.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.itemId = null;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			vwInventoryWdActualProjMasCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			vwInventoryWdActualProjMasCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		vwInventoryWdActualProjMasCacheModel.organizationKey = getOrganizationKey();

		String organizationKey = vwInventoryWdActualProjMasCacheModel.organizationKey;

		if ((organizationKey != null) && (organizationKey.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.organizationKey = null;
		}

		vwInventoryWdActualProjMasCacheModel.inventoryWdActualProjMasSid = getInventoryWdActualProjMasSid();

		vwInventoryWdActualProjMasCacheModel.source = getSource();

		String source = vwInventoryWdActualProjMasCacheModel.source;

		if ((source != null) && (source.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.source = null;
		}

		vwInventoryWdActualProjMasCacheModel.createdBy = getCreatedBy();

		String createdBy = vwInventoryWdActualProjMasCacheModel.createdBy;

		if ((createdBy != null) && (createdBy.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.createdBy = null;
		}

		Date createdDate = getCreatedDate();

		if (createdDate != null) {
			vwInventoryWdActualProjMasCacheModel.createdDate = createdDate.getTime();
		}
		else {
			vwInventoryWdActualProjMasCacheModel.createdDate = Long.MIN_VALUE;
		}

		vwInventoryWdActualProjMasCacheModel.day = getDay();

		String day = vwInventoryWdActualProjMasCacheModel.day;

		if ((day != null) && (day.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.day = null;
		}

		vwInventoryWdActualProjMasCacheModel.addChgDelIndicator = getAddChgDelIndicator();

		String addChgDelIndicator = vwInventoryWdActualProjMasCacheModel.addChgDelIndicator;

		if ((addChgDelIndicator != null) && (addChgDelIndicator.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.addChgDelIndicator = null;
		}

		vwInventoryWdActualProjMasCacheModel.unitsOnHand = getUnitsOnHand();

		vwInventoryWdActualProjMasCacheModel.amountReceived = getAmountReceived();

		vwInventoryWdActualProjMasCacheModel.modifiedBy = getModifiedBy();

		String modifiedBy = vwInventoryWdActualProjMasCacheModel.modifiedBy;

		if ((modifiedBy != null) && (modifiedBy.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.modifiedBy = null;
		}

		vwInventoryWdActualProjMasCacheModel.month = getMonth();

		String month = vwInventoryWdActualProjMasCacheModel.month;

		if ((month != null) && (month.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.month = null;
		}

		vwInventoryWdActualProjMasCacheModel.amountWithdrawn = getAmountWithdrawn();

		vwInventoryWdActualProjMasCacheModel.quantityReceived = getQuantityReceived();

		vwInventoryWdActualProjMasCacheModel.unitsWithdrawn = getUnitsWithdrawn();

		vwInventoryWdActualProjMasCacheModel.country = getCountry();

		String country = vwInventoryWdActualProjMasCacheModel.country;

		if ((country != null) && (country.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.country = null;
		}

		vwInventoryWdActualProjMasCacheModel.companyStringId = getCompanyStringId();

		String companyStringId = vwInventoryWdActualProjMasCacheModel.companyStringId;

		if ((companyStringId != null) && (companyStringId.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.companyStringId = null;
		}

		vwInventoryWdActualProjMasCacheModel.isForecast = getIsForecast();

		String isForecast = vwInventoryWdActualProjMasCacheModel.isForecast;

		if ((isForecast != null) && (isForecast.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.isForecast = null;
		}

		vwInventoryWdActualProjMasCacheModel.forecastVer = getForecastVer();

		String forecastVer = vwInventoryWdActualProjMasCacheModel.forecastVer;

		if ((forecastVer != null) && (forecastVer.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.forecastVer = null;
		}

		vwInventoryWdActualProjMasCacheModel.batchId = getBatchId();

		String batchId = vwInventoryWdActualProjMasCacheModel.batchId;

		if ((batchId != null) && (batchId.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.batchId = null;
		}

		vwInventoryWdActualProjMasCacheModel.itemName = getItemName();

		String itemName = vwInventoryWdActualProjMasCacheModel.itemName;

		if ((itemName != null) && (itemName.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.itemName = null;
		}

		vwInventoryWdActualProjMasCacheModel.amountOnOrder = getAmountOnOrder();

		vwInventoryWdActualProjMasCacheModel.forecastName = getForecastName();

		String forecastName = vwInventoryWdActualProjMasCacheModel.forecastName;

		if ((forecastName != null) && (forecastName.length() == 0)) {
			vwInventoryWdActualProjMasCacheModel.forecastName = null;
		}

		return vwInventoryWdActualProjMasCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(63);

		sb.append("{quantityOnOrder=");
		sb.append(getQuantityOnOrder());
		sb.append(", week=");
		sb.append(getWeek());
		sb.append(", price=");
		sb.append(getPrice());
		sb.append(", amountOnHand=");
		sb.append(getAmountOnHand());
		sb.append(", isMaster=");
		sb.append(getIsMaster());
		sb.append(", companyName=");
		sb.append(getCompanyName());
		sb.append(", year=");
		sb.append(getYear());
		sb.append(", itemId=");
		sb.append(getItemId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", organizationKey=");
		sb.append(getOrganizationKey());
		sb.append(", inventoryWdActualProjMasSid=");
		sb.append(getInventoryWdActualProjMasSid());
		sb.append(", source=");
		sb.append(getSource());
		sb.append(", createdBy=");
		sb.append(getCreatedBy());
		sb.append(", createdDate=");
		sb.append(getCreatedDate());
		sb.append(", day=");
		sb.append(getDay());
		sb.append(", addChgDelIndicator=");
		sb.append(getAddChgDelIndicator());
		sb.append(", unitsOnHand=");
		sb.append(getUnitsOnHand());
		sb.append(", amountReceived=");
		sb.append(getAmountReceived());
		sb.append(", modifiedBy=");
		sb.append(getModifiedBy());
		sb.append(", month=");
		sb.append(getMonth());
		sb.append(", amountWithdrawn=");
		sb.append(getAmountWithdrawn());
		sb.append(", quantityReceived=");
		sb.append(getQuantityReceived());
		sb.append(", unitsWithdrawn=");
		sb.append(getUnitsWithdrawn());
		sb.append(", country=");
		sb.append(getCountry());
		sb.append(", companyStringId=");
		sb.append(getCompanyStringId());
		sb.append(", isForecast=");
		sb.append(getIsForecast());
		sb.append(", forecastVer=");
		sb.append(getForecastVer());
		sb.append(", batchId=");
		sb.append(getBatchId());
		sb.append(", itemName=");
		sb.append(getItemName());
		sb.append(", amountOnOrder=");
		sb.append(getAmountOnOrder());
		sb.append(", forecastName=");
		sb.append(getForecastName());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(97);

		sb.append("<model><model-name>");
		sb.append("com.stpl.app.model.VwInventoryWdActualProjMas");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>quantityOnOrder</column-name><column-value><![CDATA[");
		sb.append(getQuantityOnOrder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>week</column-name><column-value><![CDATA[");
		sb.append(getWeek());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>price</column-name><column-value><![CDATA[");
		sb.append(getPrice());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountOnHand</column-name><column-value><![CDATA[");
		sb.append(getAmountOnHand());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isMaster</column-name><column-value><![CDATA[");
		sb.append(getIsMaster());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyName</column-name><column-value><![CDATA[");
		sb.append(getCompanyName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>year</column-name><column-value><![CDATA[");
		sb.append(getYear());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemId</column-name><column-value><![CDATA[");
		sb.append(getItemId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>organizationKey</column-name><column-value><![CDATA[");
		sb.append(getOrganizationKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inventoryWdActualProjMasSid</column-name><column-value><![CDATA[");
		sb.append(getInventoryWdActualProjMasSid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>source</column-name><column-value><![CDATA[");
		sb.append(getSource());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdBy</column-name><column-value><![CDATA[");
		sb.append(getCreatedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createdDate</column-name><column-value><![CDATA[");
		sb.append(getCreatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>day</column-name><column-value><![CDATA[");
		sb.append(getDay());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
		sb.append(getAddChgDelIndicator());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>unitsOnHand</column-name><column-value><![CDATA[");
		sb.append(getUnitsOnHand());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountReceived</column-name><column-value><![CDATA[");
		sb.append(getAmountReceived());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
		sb.append(getModifiedBy());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>month</column-name><column-value><![CDATA[");
		sb.append(getMonth());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
		sb.append(getAmountWithdrawn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>quantityReceived</column-name><column-value><![CDATA[");
		sb.append(getQuantityReceived());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>unitsWithdrawn</column-name><column-value><![CDATA[");
		sb.append(getUnitsWithdrawn());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>country</column-name><column-value><![CDATA[");
		sb.append(getCountry());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyStringId</column-name><column-value><![CDATA[");
		sb.append(getCompanyStringId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isForecast</column-name><column-value><![CDATA[");
		sb.append(getIsForecast());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forecastVer</column-name><column-value><![CDATA[");
		sb.append(getForecastVer());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>batchId</column-name><column-value><![CDATA[");
		sb.append(getBatchId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>itemName</column-name><column-value><![CDATA[");
		sb.append(getItemName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>amountOnOrder</column-name><column-value><![CDATA[");
		sb.append(getAmountOnOrder());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>forecastName</column-name><column-value><![CDATA[");
		sb.append(getForecastName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = VwInventoryWdActualProjMas.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			VwInventoryWdActualProjMas.class
		};
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
	private VwInventoryWdActualProjMas _escapedModel;
}