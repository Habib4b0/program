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

import com.stpl.app.exception.NoSuchVwDemandForecastActualException;
import com.stpl.app.model.VwDemandForecastActual;
import com.stpl.app.model.impl.VwDemandForecastActualImpl;
import com.stpl.app.model.impl.VwDemandForecastActualModelImpl;
import com.stpl.app.service.persistence.VwDemandForecastActualPersistence;

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
 * The persistence implementation for the vw demand forecast actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwDemandForecastActualPersistence
 * @see com.stpl.app.service.persistence.VwDemandForecastActualUtil
 * @generated
 */
@ProviderType
public class VwDemandForecastActualPersistenceImpl extends BasePersistenceImpl<VwDemandForecastActual>
	implements VwDemandForecastActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwDemandForecastActualUtil} to access the vw demand forecast actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwDemandForecastActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
			VwDemandForecastActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED,
			VwDemandForecastActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwDemandForecastActualPersistenceImpl() {
		setModelClass(VwDemandForecastActual.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("demandForecastActualSid",
				"DEMAND_FORECAST_ACTUAL_SID");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
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
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("marketSizeUnits", "MARKET_SIZE_UNITS");
			dbColumnNames.put("demandId", "DEMAND_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw demand forecast actual in the entity cache if it is enabled.
	 *
	 * @param vwDemandForecastActual the vw demand forecast actual
	 */
	@Override
	public void cacheResult(VwDemandForecastActual vwDemandForecastActual) {
		entityCache.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualImpl.class,
			vwDemandForecastActual.getPrimaryKey(), vwDemandForecastActual);

		vwDemandForecastActual.resetOriginalValues();
	}

	/**
	 * Caches the vw demand forecast actuals in the entity cache if it is enabled.
	 *
	 * @param vwDemandForecastActuals the vw demand forecast actuals
	 */
	@Override
	public void cacheResult(
		List<VwDemandForecastActual> vwDemandForecastActuals) {
		for (VwDemandForecastActual vwDemandForecastActual : vwDemandForecastActuals) {
			if (entityCache.getResult(
						VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
						VwDemandForecastActualImpl.class,
						vwDemandForecastActual.getPrimaryKey()) == null) {
				cacheResult(vwDemandForecastActual);
			}
			else {
				vwDemandForecastActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw demand forecast actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwDemandForecastActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw demand forecast actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwDemandForecastActual vwDemandForecastActual) {
		entityCache.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualImpl.class,
			vwDemandForecastActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwDemandForecastActual> vwDemandForecastActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwDemandForecastActual vwDemandForecastActual : vwDemandForecastActuals) {
			entityCache.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
				VwDemandForecastActualImpl.class,
				vwDemandForecastActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw demand forecast actual with the primary key. Does not add the vw demand forecast actual to the database.
	 *
	 * @param demandForecastActualSid the primary key for the new vw demand forecast actual
	 * @return the new vw demand forecast actual
	 */
	@Override
	public VwDemandForecastActual create(int demandForecastActualSid) {
		VwDemandForecastActual vwDemandForecastActual = new VwDemandForecastActualImpl();

		vwDemandForecastActual.setNew(true);
		vwDemandForecastActual.setPrimaryKey(demandForecastActualSid);

		return vwDemandForecastActual;
	}

	/**
	 * Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param demandForecastActualSid the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual that was removed
	 * @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual remove(int demandForecastActualSid)
		throws NoSuchVwDemandForecastActualException {
		return remove((Serializable)demandForecastActualSid);
	}

	/**
	 * Removes the vw demand forecast actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual that was removed
	 * @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual remove(Serializable primaryKey)
		throws NoSuchVwDemandForecastActualException {
		Session session = null;

		try {
			session = openSession();

			VwDemandForecastActual vwDemandForecastActual = (VwDemandForecastActual)session.get(VwDemandForecastActualImpl.class,
					primaryKey);

			if (vwDemandForecastActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwDemandForecastActual);
		}
		catch (NoSuchVwDemandForecastActualException nsee) {
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
	protected VwDemandForecastActual removeImpl(
		VwDemandForecastActual vwDemandForecastActual) {
		vwDemandForecastActual = toUnwrappedModel(vwDemandForecastActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwDemandForecastActual)) {
				vwDemandForecastActual = (VwDemandForecastActual)session.get(VwDemandForecastActualImpl.class,
						vwDemandForecastActual.getPrimaryKeyObj());
			}

			if (vwDemandForecastActual != null) {
				session.delete(vwDemandForecastActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwDemandForecastActual != null) {
			clearCache(vwDemandForecastActual);
		}

		return vwDemandForecastActual;
	}

	@Override
	public VwDemandForecastActual updateImpl(
		VwDemandForecastActual vwDemandForecastActual) {
		vwDemandForecastActual = toUnwrappedModel(vwDemandForecastActual);

		boolean isNew = vwDemandForecastActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwDemandForecastActual.isNew()) {
				session.save(vwDemandForecastActual);

				vwDemandForecastActual.setNew(false);
			}
			else {
				vwDemandForecastActual = (VwDemandForecastActual)session.merge(vwDemandForecastActual);
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

		entityCache.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
			VwDemandForecastActualImpl.class,
			vwDemandForecastActual.getPrimaryKey(), vwDemandForecastActual,
			false);

		vwDemandForecastActual.resetOriginalValues();

		return vwDemandForecastActual;
	}

	protected VwDemandForecastActual toUnwrappedModel(
		VwDemandForecastActual vwDemandForecastActual) {
		if (vwDemandForecastActual instanceof VwDemandForecastActualImpl) {
			return vwDemandForecastActual;
		}

		VwDemandForecastActualImpl vwDemandForecastActualImpl = new VwDemandForecastActualImpl();

		vwDemandForecastActualImpl.setNew(vwDemandForecastActual.isNew());
		vwDemandForecastActualImpl.setPrimaryKey(vwDemandForecastActual.getPrimaryKey());

		vwDemandForecastActualImpl.setForecastYear(vwDemandForecastActual.getForecastYear());
		vwDemandForecastActualImpl.setGrossUnits(vwDemandForecastActual.getGrossUnits());
		vwDemandForecastActualImpl.setBusinessUnitNo(vwDemandForecastActual.getBusinessUnitNo());
		vwDemandForecastActualImpl.setTotalDemandUnits(vwDemandForecastActual.getTotalDemandUnits());
		vwDemandForecastActualImpl.setBrandName(vwDemandForecastActual.getBrandName());
		vwDemandForecastActualImpl.setItemId(vwDemandForecastActual.getItemId());
		vwDemandForecastActualImpl.setOrganizationKey(vwDemandForecastActual.getOrganizationKey());
		vwDemandForecastActualImpl.setSource(vwDemandForecastActual.getSource());
		vwDemandForecastActualImpl.setMarketShareRatio(vwDemandForecastActual.getMarketShareRatio());
		vwDemandForecastActualImpl.setBusinessUnitName(vwDemandForecastActual.getBusinessUnitName());
		vwDemandForecastActualImpl.setDemandForecastActualSid(vwDemandForecastActual.getDemandForecastActualSid());
		vwDemandForecastActualImpl.setMarketShareUnits(vwDemandForecastActual.getMarketShareUnits());
		vwDemandForecastActualImpl.setInventoryUnitChange(vwDemandForecastActual.getInventoryUnitChange());
		vwDemandForecastActualImpl.setUncapturedUnitsRatio(vwDemandForecastActual.getUncapturedUnitsRatio());
		vwDemandForecastActualImpl.setCountry(vwDemandForecastActual.getCountry());
		vwDemandForecastActualImpl.setForecastType(vwDemandForecastActual.getForecastType());
		vwDemandForecastActualImpl.setBrandId(vwDemandForecastActual.getBrandId());
		vwDemandForecastActualImpl.setIsForecast(vwDemandForecastActual.getIsForecast());
		vwDemandForecastActualImpl.setUncapturedUnits(vwDemandForecastActual.getUncapturedUnits());
		vwDemandForecastActualImpl.setGrossPrice(vwDemandForecastActual.getGrossPrice());
		vwDemandForecastActualImpl.setIsActive(vwDemandForecastActual.getIsActive());
		vwDemandForecastActualImpl.setGrossAmount(vwDemandForecastActual.getGrossAmount());
		vwDemandForecastActualImpl.setBatchId(vwDemandForecastActual.getBatchId());
		vwDemandForecastActualImpl.setForecastVer(vwDemandForecastActual.getForecastVer());
		vwDemandForecastActualImpl.setItemName(vwDemandForecastActual.getItemName());
		vwDemandForecastActualImpl.setForecastMonth(vwDemandForecastActual.getForecastMonth());
		vwDemandForecastActualImpl.setNetSalesPrice(vwDemandForecastActual.getNetSalesPrice());
		vwDemandForecastActualImpl.setNetSalesAmount(vwDemandForecastActual.getNetSalesAmount());
		vwDemandForecastActualImpl.setSegment(vwDemandForecastActual.getSegment());
		vwDemandForecastActualImpl.setTotalDemandAmount(vwDemandForecastActual.getTotalDemandAmount());
		vwDemandForecastActualImpl.setForecastName(vwDemandForecastActual.getForecastName());
		vwDemandForecastActualImpl.setMarketSizeUnits(vwDemandForecastActual.getMarketSizeUnits());
		vwDemandForecastActualImpl.setDemandId(vwDemandForecastActual.getDemandId());

		return vwDemandForecastActualImpl;
	}

	/**
	 * Returns the vw demand forecast actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual
	 * @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwDemandForecastActualException {
		VwDemandForecastActual vwDemandForecastActual = fetchByPrimaryKey(primaryKey);

		if (vwDemandForecastActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwDemandForecastActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwDemandForecastActual;
	}

	/**
	 * Returns the vw demand forecast actual with the primary key or throws a {@link NoSuchVwDemandForecastActualException} if it could not be found.
	 *
	 * @param demandForecastActualSid the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual
	 * @throws NoSuchVwDemandForecastActualException if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual findByPrimaryKey(int demandForecastActualSid)
		throws NoSuchVwDemandForecastActualException {
		return findByPrimaryKey((Serializable)demandForecastActualSid);
	}

	/**
	 * Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
				VwDemandForecastActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwDemandForecastActual vwDemandForecastActual = (VwDemandForecastActual)serializable;

		if (vwDemandForecastActual == null) {
			Session session = null;

			try {
				session = openSession();

				vwDemandForecastActual = (VwDemandForecastActual)session.get(VwDemandForecastActualImpl.class,
						primaryKey);

				if (vwDemandForecastActual != null) {
					cacheResult(vwDemandForecastActual);
				}
				else {
					entityCache.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
						VwDemandForecastActualImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwDemandForecastActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwDemandForecastActual;
	}

	/**
	 * Returns the vw demand forecast actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param demandForecastActualSid the primary key of the vw demand forecast actual
	 * @return the vw demand forecast actual, or <code>null</code> if a vw demand forecast actual with the primary key could not be found
	 */
	@Override
	public VwDemandForecastActual fetchByPrimaryKey(int demandForecastActualSid) {
		return fetchByPrimaryKey((Serializable)demandForecastActualSid);
	}

	@Override
	public Map<Serializable, VwDemandForecastActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwDemandForecastActual> map = new HashMap<Serializable, VwDemandForecastActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwDemandForecastActual vwDemandForecastActual = fetchByPrimaryKey(primaryKey);

			if (vwDemandForecastActual != null) {
				map.put(primaryKey, vwDemandForecastActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwDemandForecastActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwDemandForecastActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWDEMANDFORECASTACTUAL_WHERE_PKS_IN);

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

			for (VwDemandForecastActual vwDemandForecastActual : (List<VwDemandForecastActual>)q.list()) {
				map.put(vwDemandForecastActual.getPrimaryKeyObj(),
					vwDemandForecastActual);

				cacheResult(vwDemandForecastActual);

				uncachedPrimaryKeys.remove(vwDemandForecastActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwDemandForecastActualModelImpl.ENTITY_CACHE_ENABLED,
					VwDemandForecastActualImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw demand forecast actuals.
	 *
	 * @return the vw demand forecast actuals
	 */
	@Override
	public List<VwDemandForecastActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw demand forecast actuals
	 * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	 * @return the range of vw demand forecast actuals
	 */
	@Override
	public List<VwDemandForecastActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw demand forecast actuals
	 * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw demand forecast actuals
	 */
	@Override
	public List<VwDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwDemandForecastActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw demand forecast actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwDemandForecastActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw demand forecast actuals
	 * @param end the upper bound of the range of vw demand forecast actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw demand forecast actuals
	 */
	@Override
	public List<VwDemandForecastActual> findAll(int start, int end,
		OrderByComparator<VwDemandForecastActual> orderByComparator,
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

		List<VwDemandForecastActual> list = null;

		if (retrieveFromCache) {
			list = (List<VwDemandForecastActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWDEMANDFORECASTACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWDEMANDFORECASTACTUAL;

				if (pagination) {
					sql = sql.concat(VwDemandForecastActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwDemandForecastActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwDemandForecastActual>)QueryUtil.list(q,
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
	 * Removes all the vw demand forecast actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwDemandForecastActual vwDemandForecastActual : findAll()) {
			remove(vwDemandForecastActual);
		}
	}

	/**
	 * Returns the number of vw demand forecast actuals.
	 *
	 * @return the number of vw demand forecast actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWDEMANDFORECASTACTUAL);

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
		return VwDemandForecastActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw demand forecast actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwDemandForecastActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWDEMANDFORECASTACTUAL = "SELECT vwDemandForecastActual FROM VwDemandForecastActual vwDemandForecastActual";
	private static final String _SQL_SELECT_VWDEMANDFORECASTACTUAL_WHERE_PKS_IN = "SELECT vwDemandForecastActual FROM VwDemandForecastActual vwDemandForecastActual WHERE DEMAND_FORECAST_ACTUAL_SID IN (";
	private static final String _SQL_COUNT_VWDEMANDFORECASTACTUAL = "SELECT COUNT(vwDemandForecastActual) FROM VwDemandForecastActual vwDemandForecastActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwDemandForecastActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwDemandForecastActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwDemandForecastActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastYear", "grossUnits", "businessUnitNo",
				"totalDemandUnits", "brandName", "itemId", "organizationKey",
				"source", "marketShareRatio", "businessUnitName",
				"demandForecastActualSid", "marketShareUnits",
				"inventoryUnitChange", "uncapturedUnitsRatio", "country",
				"forecastType", "brandId", "isForecast", "uncapturedUnits",
				"grossPrice", "isActive", "grossAmount", "batchId",
				"forecastVer", "itemName", "forecastMonth", "netSalesPrice",
				"netSalesAmount", "segment", "totalDemandAmount", "forecastName",
				"marketSizeUnits", "demandId"
			});
}