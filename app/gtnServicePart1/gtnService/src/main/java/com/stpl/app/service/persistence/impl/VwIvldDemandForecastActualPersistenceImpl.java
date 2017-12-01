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

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchVwIvldDemandForecastActualException;
import com.stpl.app.model.VwIvldDemandForecastActual;
import com.stpl.app.model.impl.VwIvldDemandForecastActualImpl;
import com.stpl.app.model.impl.VwIvldDemandForecastActualModelImpl;
import com.stpl.app.service.persistence.VwIvldDemandForecastActualPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the vw ivld demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldDemandForecastActualPersistence
 * @see com.stpl.app.service.persistence.VwIvldDemandForecastActualUtil
 * @generated
 */
@ProviderType
public class VwIvldDemandForecastActualPersistenceImpl
	extends BasePersistenceImpl<VwIvldDemandForecastActual>
	implements VwIvldDemandForecastActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwIvldDemandForecastActualUtil} to access the vw ivld demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwIvldDemandForecastActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
			VwIvldDemandForecastActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
			VwIvldDemandForecastActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public VwIvldDemandForecastActualPersistenceImpl() {
		setModelClass(VwIvldDemandForecastActual.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("demandIntSid", "DEMAND_INT_SID");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("ivldDemandActualForecastSid",
				"IVLD_DEMAND_ACTUAL_FORECAST_SID");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("isForecast", "IS_FORECAST");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("marketSizeUnits", "MARKET_SIZE_UNITS");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw ivld demand forecast actual in the entity cache if it is enabled.
	 *
	 * @param vwIvldDemandForecastActual the vw ivld demand forecast actual
	 */
	@Override
	public void cacheResult(
		VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		entityCache.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualImpl.class,
			vwIvldDemandForecastActual.getPrimaryKey(),
			vwIvldDemandForecastActual);

		vwIvldDemandForecastActual.resetOriginalValues();
	}

	/**
	 * Caches the vw ivld demand forecast actuals in the entity cache if it is enabled.
	 *
	 * @param vwIvldDemandForecastActuals the vw ivld demand forecast actuals
	 */
	@Override
	public void cacheResult(
		List<VwIvldDemandForecastActual> vwIvldDemandForecastActuals) {
		for (VwIvldDemandForecastActual vwIvldDemandForecastActual : vwIvldDemandForecastActuals) {
			if (entityCache.getResult(
						VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldDemandForecastActualImpl.class,
						vwIvldDemandForecastActual.getPrimaryKey()) == null) {
				cacheResult(vwIvldDemandForecastActual);
			}
			else {
				vwIvldDemandForecastActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw ivld demand forecast actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwIvldDemandForecastActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw ivld demand forecast actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		entityCache.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualImpl.class,
			vwIvldDemandForecastActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwIvldDemandForecastActual> vwIvldDemandForecastActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwIvldDemandForecastActual vwIvldDemandForecastActual : vwIvldDemandForecastActuals) {
			entityCache.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldDemandForecastActualImpl.class,
				vwIvldDemandForecastActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw ivld demand forecast actual with the primary key. Does not add the vw ivld demand forecast actual to the database.
	 *
	 * @param ivldDemandActualForecastSid the primary key for the new vw ivld demand forecast actual
	 * @return the new vw ivld demand forecast actual
	 */
	@Override
	public VwIvldDemandForecastActual create(int ivldDemandActualForecastSid) {
		VwIvldDemandForecastActual vwIvldDemandForecastActual = new VwIvldDemandForecastActualImpl();

		vwIvldDemandForecastActual.setNew(true);
		vwIvldDemandForecastActual.setPrimaryKey(ivldDemandActualForecastSid);

		return vwIvldDemandForecastActual;
	}

	/**
	 * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual that was removed
	 * @throws NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual remove(int ivldDemandActualForecastSid)
		throws NoSuchVwIvldDemandForecastActualException {
		return remove((Serializable)ivldDemandActualForecastSid);
	}

	/**
	 * Removes the vw ivld demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual that was removed
	 * @throws NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual remove(Serializable primaryKey)
		throws NoSuchVwIvldDemandForecastActualException {
		Session session = null;

		try {
			session = openSession();

			VwIvldDemandForecastActual vwIvldDemandForecastActual = (VwIvldDemandForecastActual)session.get(VwIvldDemandForecastActualImpl.class,
					primaryKey);

			if (vwIvldDemandForecastActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwIvldDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwIvldDemandForecastActual);
		}
		catch (NoSuchVwIvldDemandForecastActualException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected VwIvldDemandForecastActual removeImpl(
		VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		vwIvldDemandForecastActual = toUnwrappedModel(vwIvldDemandForecastActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwIvldDemandForecastActual)) {
				vwIvldDemandForecastActual = (VwIvldDemandForecastActual)session.get(VwIvldDemandForecastActualImpl.class,
						vwIvldDemandForecastActual.getPrimaryKeyObj());
			}

			if (vwIvldDemandForecastActual != null) {
				session.delete(vwIvldDemandForecastActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwIvldDemandForecastActual != null) {
			clearCache(vwIvldDemandForecastActual);
		}

		return vwIvldDemandForecastActual;
	}

	@Override
	public VwIvldDemandForecastActual updateImpl(
		VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		vwIvldDemandForecastActual = toUnwrappedModel(vwIvldDemandForecastActual);

		boolean isNew = vwIvldDemandForecastActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwIvldDemandForecastActual.isNew()) {
				session.save(vwIvldDemandForecastActual);

				vwIvldDemandForecastActual.setNew(false);
			}
			else {
				vwIvldDemandForecastActual = (VwIvldDemandForecastActual)session.merge(vwIvldDemandForecastActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldDemandForecastActualImpl.class,
			vwIvldDemandForecastActual.getPrimaryKey(),
			vwIvldDemandForecastActual, false);

		vwIvldDemandForecastActual.resetOriginalValues();

		return vwIvldDemandForecastActual;
	}

	protected VwIvldDemandForecastActual toUnwrappedModel(
		VwIvldDemandForecastActual vwIvldDemandForecastActual) {
		if (vwIvldDemandForecastActual instanceof VwIvldDemandForecastActualImpl) {
			return vwIvldDemandForecastActual;
		}

		VwIvldDemandForecastActualImpl vwIvldDemandForecastActualImpl = new VwIvldDemandForecastActualImpl();

		vwIvldDemandForecastActualImpl.setNew(vwIvldDemandForecastActual.isNew());
		vwIvldDemandForecastActualImpl.setPrimaryKey(vwIvldDemandForecastActual.getPrimaryKey());

		vwIvldDemandForecastActualImpl.setDemandIntSid(vwIvldDemandForecastActual.getDemandIntSid());
		vwIvldDemandForecastActualImpl.setForecastYear(vwIvldDemandForecastActual.getForecastYear());
		vwIvldDemandForecastActualImpl.setGrossUnits(vwIvldDemandForecastActual.getGrossUnits());
		vwIvldDemandForecastActualImpl.setBusinessUnitNo(vwIvldDemandForecastActual.getBusinessUnitNo());
		vwIvldDemandForecastActualImpl.setTotalDemandUnits(vwIvldDemandForecastActual.getTotalDemandUnits());
		vwIvldDemandForecastActualImpl.setBrandName(vwIvldDemandForecastActual.getBrandName());
		vwIvldDemandForecastActualImpl.setItemId(vwIvldDemandForecastActual.getItemId());
		vwIvldDemandForecastActualImpl.setOrganizationKey(vwIvldDemandForecastActual.getOrganizationKey());
		vwIvldDemandForecastActualImpl.setSource(vwIvldDemandForecastActual.getSource());
		vwIvldDemandForecastActualImpl.setMarketShareRatio(vwIvldDemandForecastActual.getMarketShareRatio());
		vwIvldDemandForecastActualImpl.setIvldDemandActualForecastSid(vwIvldDemandForecastActual.getIvldDemandActualForecastSid());
		vwIvldDemandForecastActualImpl.setBusinessUnitName(vwIvldDemandForecastActual.getBusinessUnitName());
		vwIvldDemandForecastActualImpl.setAddChgDelIndicator(vwIvldDemandForecastActual.getAddChgDelIndicator());
		vwIvldDemandForecastActualImpl.setErrorCode(vwIvldDemandForecastActual.getErrorCode());
		vwIvldDemandForecastActualImpl.setMarketShareUnits(vwIvldDemandForecastActual.getMarketShareUnits());
		vwIvldDemandForecastActualImpl.setInventoryUnitChange(vwIvldDemandForecastActual.getInventoryUnitChange());
		vwIvldDemandForecastActualImpl.setReprocessedFlag(vwIvldDemandForecastActual.getReprocessedFlag());
		vwIvldDemandForecastActualImpl.setUncapturedUnitsRatio(vwIvldDemandForecastActual.getUncapturedUnitsRatio());
		vwIvldDemandForecastActualImpl.setReasonForFailure(vwIvldDemandForecastActual.getReasonForFailure());
		vwIvldDemandForecastActualImpl.setCountry(vwIvldDemandForecastActual.getCountry());
		vwIvldDemandForecastActualImpl.setForecastType(vwIvldDemandForecastActual.getForecastType());
		vwIvldDemandForecastActualImpl.setBrandId(vwIvldDemandForecastActual.getBrandId());
		vwIvldDemandForecastActualImpl.setIsForecast(vwIvldDemandForecastActual.getIsForecast());
		vwIvldDemandForecastActualImpl.setUncapturedUnits(vwIvldDemandForecastActual.getUncapturedUnits());
		vwIvldDemandForecastActualImpl.setGrossPrice(vwIvldDemandForecastActual.getGrossPrice());
		vwIvldDemandForecastActualImpl.setIsActive(vwIvldDemandForecastActual.getIsActive());
		vwIvldDemandForecastActualImpl.setGrossAmount(vwIvldDemandForecastActual.getGrossAmount());
		vwIvldDemandForecastActualImpl.setBatchId(vwIvldDemandForecastActual.getBatchId());
		vwIvldDemandForecastActualImpl.setForecastVer(vwIvldDemandForecastActual.getForecastVer());
		vwIvldDemandForecastActualImpl.setItemName(vwIvldDemandForecastActual.getItemName());
		vwIvldDemandForecastActualImpl.setForecastMonth(vwIvldDemandForecastActual.getForecastMonth());
		vwIvldDemandForecastActualImpl.setErrorField(vwIvldDemandForecastActual.getErrorField());
		vwIvldDemandForecastActualImpl.setNetSalesPrice(vwIvldDemandForecastActual.getNetSalesPrice());
		vwIvldDemandForecastActualImpl.setNetSalesAmount(vwIvldDemandForecastActual.getNetSalesAmount());
		vwIvldDemandForecastActualImpl.setSegment(vwIvldDemandForecastActual.getSegment());
		vwIvldDemandForecastActualImpl.setTotalDemandAmount(vwIvldDemandForecastActual.getTotalDemandAmount());
		vwIvldDemandForecastActualImpl.setForecastName(vwIvldDemandForecastActual.getForecastName());
		vwIvldDemandForecastActualImpl.setMarketSizeUnits(vwIvldDemandForecastActual.getMarketSizeUnits());
		vwIvldDemandForecastActualImpl.setCheckRecord(vwIvldDemandForecastActual.isCheckRecord());

		return vwIvldDemandForecastActualImpl;
	}

	/**
	 * Returns the vw ivld demand forecast actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual
	 * @throws NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwIvldDemandForecastActualException {
		VwIvldDemandForecastActual vwIvldDemandForecastActual = fetchByPrimaryKey(primaryKey);

		if (vwIvldDemandForecastActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwIvldDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwIvldDemandForecastActual;
	}

	/**
	 * Returns the vw ivld demand forecast actual with the primary key or throws a {@link NoSuchVwIvldDemandForecastActualException} if it could not be found.
	 *
	 * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual
	 * @throws NoSuchVwIvldDemandForecastActualException if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual findByPrimaryKey(
		int ivldDemandActualForecastSid)
		throws NoSuchVwIvldDemandForecastActualException {
		return findByPrimaryKey((Serializable)ivldDemandActualForecastSid);
	}

	/**
	 * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldDemandForecastActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwIvldDemandForecastActual vwIvldDemandForecastActual = (VwIvldDemandForecastActual)serializable;

		if (vwIvldDemandForecastActual == null) {
			Session session = null;

			try {
				session = openSession();

				vwIvldDemandForecastActual = (VwIvldDemandForecastActual)session.get(VwIvldDemandForecastActualImpl.class,
						primaryKey);

				if (vwIvldDemandForecastActual != null) {
					cacheResult(vwIvldDemandForecastActual);
				}
				else {
					entityCache.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldDemandForecastActualImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldDemandForecastActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwIvldDemandForecastActual;
	}

	/**
	 * Returns the vw ivld demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldDemandActualForecastSid the primary key of the vw ivld demand forecast actual
	 * @return the vw ivld demand forecast actual, or <code>null</code> if a vw ivld demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwIvldDemandForecastActual fetchByPrimaryKey(
		int ivldDemandActualForecastSid) {
		return fetchByPrimaryKey((Serializable)ivldDemandActualForecastSid);
	}

	@Override
	public Map<Serializable, VwIvldDemandForecastActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwIvldDemandForecastActual> map = new HashMap<Serializable, VwIvldDemandForecastActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwIvldDemandForecastActual vwIvldDemandForecastActual = fetchByPrimaryKey(primaryKey);

			if (vwIvldDemandForecastActual != null) {
				map.put(primaryKey, vwIvldDemandForecastActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldDemandForecastActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwIvldDemandForecastActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (VwIvldDemandForecastActual vwIvldDemandForecastActual : (List<VwIvldDemandForecastActual>)q.list()) {
				map.put(vwIvldDemandForecastActual.getPrimaryKeyObj(),
					vwIvldDemandForecastActual);

				cacheResult(vwIvldDemandForecastActual);

				uncachedPrimaryKeys.remove(vwIvldDemandForecastActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwIvldDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldDemandForecastActualImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the vw ivld demand forecast actuals.
	 *
	 * @return the vw ivld demand forecast actuals
	 */
	@Override
	public List<VwIvldDemandForecastActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw ivld demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld demand forecast actuals
	 * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
	 * @return the range of vw ivld demand forecast actuals
	 */
	@Override
	public List<VwIvldDemandForecastActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw ivld demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld demand forecast actuals
	 * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw ivld demand forecast actuals
	 */
	@Override
	public List<VwIvldDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwIvldDemandForecastActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw ivld demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld demand forecast actuals
	 * @param end the upper bound of the range of vw ivld demand forecast actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw ivld demand forecast actuals
	 */
	@Override
	public List<VwIvldDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwIvldDemandForecastActual> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<VwIvldDemandForecastActual> list = null;

		if (retrieveFromCache) {
			list = (List<VwIvldDemandForecastActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL;

				if (pagination) {
					sql = sql.concat(VwIvldDemandForecastActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwIvldDemandForecastActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwIvldDemandForecastActual>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the vw ivld demand forecast actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwIvldDemandForecastActual vwIvldDemandForecastActual : findAll()) {
			remove(vwIvldDemandForecastActual);
		}
	}

	/**
	 * Returns the number of vw ivld demand forecast actuals.
	 *
	 * @return the number of vw ivld demand forecast actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWIVLDDEMANDFORECASTACTUAL);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return VwIvldDemandForecastActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw ivld demand forecast actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwIvldDemandForecastActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL = "SELECT vwIvldDemandForecastActual FROM VwIvldDemandForecastActual vwIvldDemandForecastActual";
	private static final String _SQL_SELECT_VWIVLDDEMANDFORECASTACTUAL_WHERE_PKS_IN =
		"SELECT vwIvldDemandForecastActual FROM VwIvldDemandForecastActual vwIvldDemandForecastActual WHERE IVLD_DEMAND_ACTUAL_FORECAST_SID IN (";
	private static final String _SQL_COUNT_VWIVLDDEMANDFORECASTACTUAL = "SELECT COUNT(vwIvldDemandForecastActual) FROM VwIvldDemandForecastActual vwIvldDemandForecastActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldDemandForecastActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldDemandForecastActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwIvldDemandForecastActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"demandIntSid", "forecastYear", "grossUnits", "businessUnitNo",
				"totalDemandUnits", "brandName", "itemId", "organizationKey",
				"source", "marketShareRatio", "ivldDemandActualForecastSid",
				"businessUnitName", "addChgDelIndicator", "errorCode",
				"marketShareUnits", "inventoryUnitChange", "reprocessedFlag",
				"uncapturedUnitsRatio", "reasonForFailure", "country",
				"forecastType", "brandId", "isForecast", "uncapturedUnits",
				"grossPrice", "isActive", "grossAmount", "batchId",
				"forecastVer", "itemName", "forecastMonth", "errorField",
				"netSalesPrice", "netSalesAmount", "segment",
				"totalDemandAmount", "forecastName", "marketSizeUnits",
				"checkRecord"
			});
}