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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchVwAdjustDemandForecastActException;
import com.stpl.app.parttwo.model.VwAdjustDemandForecastAct;
import com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActImpl;
import com.stpl.app.parttwo.model.impl.VwAdjustDemandForecastActModelImpl;
import com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActPersistence;

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
 * The persistence implementation for the vw adjust demand forecast act service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAdjustDemandForecastActPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwAdjustDemandForecastActUtil
 * @generated
 */
@ProviderType
public class VwAdjustDemandForecastActPersistenceImpl
	extends BasePersistenceImpl<VwAdjustDemandForecastAct>
	implements VwAdjustDemandForecastActPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwAdjustDemandForecastActUtil} to access the vw adjust demand forecast act persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwAdjustDemandForecastActImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
			VwAdjustDemandForecastActImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
			VwAdjustDemandForecastActImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public VwAdjustDemandForecastActPersistenceImpl() {
		setModelClass(VwAdjustDemandForecastAct.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("isForecast", "IS_FORECAST");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("adjustedDemandForecastId",
				"ADJUSTED_DEMAND_FORECAST_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("marketSizeUnits", "MARKET_SIZE_UNITS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw adjust demand forecast act in the entity cache if it is enabled.
	 *
	 * @param vwAdjustDemandForecastAct the vw adjust demand forecast act
	 */
	@Override
	public void cacheResult(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		entityCache.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActImpl.class,
			vwAdjustDemandForecastAct.getPrimaryKey(), vwAdjustDemandForecastAct);

		vwAdjustDemandForecastAct.resetOriginalValues();
	}

	/**
	 * Caches the vw adjust demand forecast acts in the entity cache if it is enabled.
	 *
	 * @param vwAdjustDemandForecastActs the vw adjust demand forecast acts
	 */
	@Override
	public void cacheResult(
		List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
		for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : vwAdjustDemandForecastActs) {
			if (entityCache.getResult(
						VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
						VwAdjustDemandForecastActImpl.class,
						vwAdjustDemandForecastAct.getPrimaryKey()) == null) {
				cacheResult(vwAdjustDemandForecastAct);
			}
			else {
				vwAdjustDemandForecastAct.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw adjust demand forecast acts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwAdjustDemandForecastActImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw adjust demand forecast act.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		entityCache.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActImpl.class,
			vwAdjustDemandForecastAct.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwAdjustDemandForecastAct> vwAdjustDemandForecastActs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : vwAdjustDemandForecastActs) {
			entityCache.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
				VwAdjustDemandForecastActImpl.class,
				vwAdjustDemandForecastAct.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw adjust demand forecast act with the primary key. Does not add the vw adjust demand forecast act to the database.
	 *
	 * @param adjustedDemandForecastId the primary key for the new vw adjust demand forecast act
	 * @return the new vw adjust demand forecast act
	 */
	@Override
	public VwAdjustDemandForecastAct create(int adjustedDemandForecastId) {
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct = new VwAdjustDemandForecastActImpl();

		vwAdjustDemandForecastAct.setNew(true);
		vwAdjustDemandForecastAct.setPrimaryKey(adjustedDemandForecastId);

		return vwAdjustDemandForecastAct;
	}

	/**
	 * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act that was removed
	 * @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct remove(int adjustedDemandForecastId)
		throws NoSuchVwAdjustDemandForecastActException {
		return remove((Serializable)adjustedDemandForecastId);
	}

	/**
	 * Removes the vw adjust demand forecast act with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act that was removed
	 * @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct remove(Serializable primaryKey)
		throws NoSuchVwAdjustDemandForecastActException {
		Session session = null;

		try {
			session = openSession();

			VwAdjustDemandForecastAct vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct)session.get(VwAdjustDemandForecastActImpl.class,
					primaryKey);

			if (vwAdjustDemandForecastAct == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwAdjustDemandForecastActException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwAdjustDemandForecastAct);
		}
		catch (NoSuchVwAdjustDemandForecastActException nsee) {
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
	protected VwAdjustDemandForecastAct removeImpl(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		vwAdjustDemandForecastAct = toUnwrappedModel(vwAdjustDemandForecastAct);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwAdjustDemandForecastAct)) {
				vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct)session.get(VwAdjustDemandForecastActImpl.class,
						vwAdjustDemandForecastAct.getPrimaryKeyObj());
			}

			if (vwAdjustDemandForecastAct != null) {
				session.delete(vwAdjustDemandForecastAct);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwAdjustDemandForecastAct != null) {
			clearCache(vwAdjustDemandForecastAct);
		}

		return vwAdjustDemandForecastAct;
	}

	@Override
	public VwAdjustDemandForecastAct updateImpl(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		vwAdjustDemandForecastAct = toUnwrappedModel(vwAdjustDemandForecastAct);

		boolean isNew = vwAdjustDemandForecastAct.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwAdjustDemandForecastAct.isNew()) {
				session.save(vwAdjustDemandForecastAct);

				vwAdjustDemandForecastAct.setNew(false);
			}
			else {
				vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct)session.merge(vwAdjustDemandForecastAct);
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

		entityCache.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
			VwAdjustDemandForecastActImpl.class,
			vwAdjustDemandForecastAct.getPrimaryKey(),
			vwAdjustDemandForecastAct, false);

		vwAdjustDemandForecastAct.resetOriginalValues();

		return vwAdjustDemandForecastAct;
	}

	protected VwAdjustDemandForecastAct toUnwrappedModel(
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct) {
		if (vwAdjustDemandForecastAct instanceof VwAdjustDemandForecastActImpl) {
			return vwAdjustDemandForecastAct;
		}

		VwAdjustDemandForecastActImpl vwAdjustDemandForecastActImpl = new VwAdjustDemandForecastActImpl();

		vwAdjustDemandForecastActImpl.setNew(vwAdjustDemandForecastAct.isNew());
		vwAdjustDemandForecastActImpl.setPrimaryKey(vwAdjustDemandForecastAct.getPrimaryKey());

		vwAdjustDemandForecastActImpl.setForecastVer(vwAdjustDemandForecastAct.getForecastVer());
		vwAdjustDemandForecastActImpl.setGrossUnits(vwAdjustDemandForecastAct.getGrossUnits());
		vwAdjustDemandForecastActImpl.setBusinessUnitNo(vwAdjustDemandForecastAct.getBusinessUnitNo());
		vwAdjustDemandForecastActImpl.setForecastYear(vwAdjustDemandForecastAct.getForecastYear());
		vwAdjustDemandForecastActImpl.setBrandName(vwAdjustDemandForecastAct.getBrandName());
		vwAdjustDemandForecastActImpl.setItemId(vwAdjustDemandForecastAct.getItemId());
		vwAdjustDemandForecastActImpl.setOrganizationKey(vwAdjustDemandForecastAct.getOrganizationKey());
		vwAdjustDemandForecastActImpl.setSource(vwAdjustDemandForecastAct.getSource());
		vwAdjustDemandForecastActImpl.setMarketShareRatio(vwAdjustDemandForecastAct.getMarketShareRatio());
		vwAdjustDemandForecastActImpl.setBusinessUnitName(vwAdjustDemandForecastAct.getBusinessUnitName());
		vwAdjustDemandForecastActImpl.setMarketShareUnits(vwAdjustDemandForecastAct.getMarketShareUnits());
		vwAdjustDemandForecastActImpl.setForecastMonth(vwAdjustDemandForecastAct.getForecastMonth());
		vwAdjustDemandForecastActImpl.setInventoryUnitChange(vwAdjustDemandForecastAct.getInventoryUnitChange());
		vwAdjustDemandForecastActImpl.setUncapturedUnitsRatio(vwAdjustDemandForecastAct.getUncapturedUnitsRatio());
		vwAdjustDemandForecastActImpl.setCountry(vwAdjustDemandForecastAct.getCountry());
		vwAdjustDemandForecastActImpl.setForecastType(vwAdjustDemandForecastAct.getForecastType());
		vwAdjustDemandForecastActImpl.setTotalDemandUnits(vwAdjustDemandForecastAct.getTotalDemandUnits());
		vwAdjustDemandForecastActImpl.setBrandId(vwAdjustDemandForecastAct.getBrandId());
		vwAdjustDemandForecastActImpl.setIsForecast(vwAdjustDemandForecastAct.getIsForecast());
		vwAdjustDemandForecastActImpl.setTotalDemandAmount(vwAdjustDemandForecastAct.getTotalDemandAmount());
		vwAdjustDemandForecastActImpl.setUncapturedUnits(vwAdjustDemandForecastAct.getUncapturedUnits());
		vwAdjustDemandForecastActImpl.setGrossPrice(vwAdjustDemandForecastAct.getGrossPrice());
		vwAdjustDemandForecastActImpl.setGrossAmount(vwAdjustDemandForecastAct.getGrossAmount());
		vwAdjustDemandForecastActImpl.setBatchId(vwAdjustDemandForecastAct.getBatchId());
		vwAdjustDemandForecastActImpl.setAdjustedDemandForecastId(vwAdjustDemandForecastAct.getAdjustedDemandForecastId());
		vwAdjustDemandForecastActImpl.setItemName(vwAdjustDemandForecastAct.getItemName());
		vwAdjustDemandForecastActImpl.setNetSalesPrice(vwAdjustDemandForecastAct.getNetSalesPrice());
		vwAdjustDemandForecastActImpl.setNetSalesAmount(vwAdjustDemandForecastAct.getNetSalesAmount());
		vwAdjustDemandForecastActImpl.setSegment(vwAdjustDemandForecastAct.getSegment());
		vwAdjustDemandForecastActImpl.setForecastName(vwAdjustDemandForecastAct.getForecastName());
		vwAdjustDemandForecastActImpl.setMarketSizeUnits(vwAdjustDemandForecastAct.getMarketSizeUnits());

		return vwAdjustDemandForecastActImpl;
	}

	/**
	 * Returns the vw adjust demand forecast act with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act
	 * @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwAdjustDemandForecastActException {
		VwAdjustDemandForecastAct vwAdjustDemandForecastAct = fetchByPrimaryKey(primaryKey);

		if (vwAdjustDemandForecastAct == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwAdjustDemandForecastActException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwAdjustDemandForecastAct;
	}

	/**
	 * Returns the vw adjust demand forecast act with the primary key or throws a {@link NoSuchVwAdjustDemandForecastActException} if it could not be found.
	 *
	 * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act
	 * @throws NoSuchVwAdjustDemandForecastActException if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct findByPrimaryKey(
		int adjustedDemandForecastId)
		throws NoSuchVwAdjustDemandForecastActException {
		return findByPrimaryKey((Serializable)adjustedDemandForecastId);
	}

	/**
	 * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
				VwAdjustDemandForecastActImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwAdjustDemandForecastAct vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct)serializable;

		if (vwAdjustDemandForecastAct == null) {
			Session session = null;

			try {
				session = openSession();

				vwAdjustDemandForecastAct = (VwAdjustDemandForecastAct)session.get(VwAdjustDemandForecastActImpl.class,
						primaryKey);

				if (vwAdjustDemandForecastAct != null) {
					cacheResult(vwAdjustDemandForecastAct);
				}
				else {
					entityCache.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
						VwAdjustDemandForecastActImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
					VwAdjustDemandForecastActImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwAdjustDemandForecastAct;
	}

	/**
	 * Returns the vw adjust demand forecast act with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param adjustedDemandForecastId the primary key of the vw adjust demand forecast act
	 * @return the vw adjust demand forecast act, or <code>null</code> if a vw adjust demand forecast act with the primary key could not be found
	 */
	@Override
	public VwAdjustDemandForecastAct fetchByPrimaryKey(
		int adjustedDemandForecastId) {
		return fetchByPrimaryKey((Serializable)adjustedDemandForecastId);
	}

	@Override
	public Map<Serializable, VwAdjustDemandForecastAct> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwAdjustDemandForecastAct> map = new HashMap<Serializable, VwAdjustDemandForecastAct>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwAdjustDemandForecastAct vwAdjustDemandForecastAct = fetchByPrimaryKey(primaryKey);

			if (vwAdjustDemandForecastAct != null) {
				map.put(primaryKey, vwAdjustDemandForecastAct);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
					VwAdjustDemandForecastActImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwAdjustDemandForecastAct)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWADJUSTDEMANDFORECASTACT_WHERE_PKS_IN);

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

			for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : (List<VwAdjustDemandForecastAct>)q.list()) {
				map.put(vwAdjustDemandForecastAct.getPrimaryKeyObj(),
					vwAdjustDemandForecastAct);

				cacheResult(vwAdjustDemandForecastAct);

				uncachedPrimaryKeys.remove(vwAdjustDemandForecastAct.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwAdjustDemandForecastActModelImpl.ENTITY_CACHE_ENABLED,
					VwAdjustDemandForecastActImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw adjust demand forecast acts.
	 *
	 * @return the vw adjust demand forecast acts
	 */
	@Override
	public List<VwAdjustDemandForecastAct> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw adjust demand forecast acts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw adjust demand forecast acts
	 * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	 * @return the range of vw adjust demand forecast acts
	 */
	@Override
	public List<VwAdjustDemandForecastAct> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw adjust demand forecast acts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw adjust demand forecast acts
	 * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw adjust demand forecast acts
	 */
	@Override
	public List<VwAdjustDemandForecastAct> findAll(int start, int end,
		OrderByComparator<VwAdjustDemandForecastAct> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw adjust demand forecast acts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAdjustDemandForecastActModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw adjust demand forecast acts
	 * @param end the upper bound of the range of vw adjust demand forecast acts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw adjust demand forecast acts
	 */
	@Override
	public List<VwAdjustDemandForecastAct> findAll(int start, int end,
		OrderByComparator<VwAdjustDemandForecastAct> orderByComparator,
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

		List<VwAdjustDemandForecastAct> list = null;

		if (retrieveFromCache) {
			list = (List<VwAdjustDemandForecastAct>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWADJUSTDEMANDFORECASTACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWADJUSTDEMANDFORECASTACT;

				if (pagination) {
					sql = sql.concat(VwAdjustDemandForecastActModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwAdjustDemandForecastAct>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwAdjustDemandForecastAct>)QueryUtil.list(q,
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
	 * Removes all the vw adjust demand forecast acts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwAdjustDemandForecastAct vwAdjustDemandForecastAct : findAll()) {
			remove(vwAdjustDemandForecastAct);
		}
	}

	/**
	 * Returns the number of vw adjust demand forecast acts.
	 *
	 * @return the number of vw adjust demand forecast acts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWADJUSTDEMANDFORECASTACT);

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
		return VwAdjustDemandForecastActModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw adjust demand forecast act persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwAdjustDemandForecastActImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWADJUSTDEMANDFORECASTACT = "SELECT vwAdjustDemandForecastAct FROM VwAdjustDemandForecastAct vwAdjustDemandForecastAct";
	private static final String _SQL_SELECT_VWADJUSTDEMANDFORECASTACT_WHERE_PKS_IN =
		"SELECT vwAdjustDemandForecastAct FROM VwAdjustDemandForecastAct vwAdjustDemandForecastAct WHERE ADJUSTED_DEMAND_FORECAST_ID IN (";
	private static final String _SQL_COUNT_VWADJUSTDEMANDFORECASTACT = "SELECT COUNT(vwAdjustDemandForecastAct) FROM VwAdjustDemandForecastAct vwAdjustDemandForecastAct";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwAdjustDemandForecastAct.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwAdjustDemandForecastAct exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwAdjustDemandForecastActPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastVer", "grossUnits", "businessUnitNo", "forecastYear",
				"brandName", "itemId", "organizationKey", "source",
				"marketShareRatio", "businessUnitName", "marketShareUnits",
				"forecastMonth", "inventoryUnitChange", "uncapturedUnitsRatio",
				"country", "forecastType", "totalDemandUnits", "brandId",
				"isForecast", "totalDemandAmount", "uncapturedUnits",
				"grossPrice", "grossAmount", "batchId",
				"adjustedDemandForecastId", "itemName", "netSalesPrice",
				"netSalesAmount", "segment", "forecastName", "marketSizeUnits"
			});
}