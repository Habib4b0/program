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

import com.stpl.app.parttwo.exception.NoSuchAdjustedDemandForecastException;
import com.stpl.app.parttwo.model.AdjustedDemandForecast;
import com.stpl.app.parttwo.model.impl.AdjustedDemandForecastImpl;
import com.stpl.app.parttwo.model.impl.AdjustedDemandForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastPersistence;

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
 * The persistence implementation for the adjusted demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AdjustedDemandForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.AdjustedDemandForecastUtil
 * @generated
 */
@ProviderType
public class AdjustedDemandForecastPersistenceImpl extends BasePersistenceImpl<AdjustedDemandForecast>
	implements AdjustedDemandForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AdjustedDemandForecastUtil} to access the adjusted demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AdjustedDemandForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED,
			AdjustedDemandForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED,
			AdjustedDemandForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AdjustedDemandForecastPersistenceImpl() {
		setModelClass(AdjustedDemandForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("adjustedDemandForecastSid",
				"ADJUSTED_DEMAND_FORECAST_SID");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
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
	 * Caches the adjusted demand forecast in the entity cache if it is enabled.
	 *
	 * @param adjustedDemandForecast the adjusted demand forecast
	 */
	@Override
	public void cacheResult(AdjustedDemandForecast adjustedDemandForecast) {
		entityCache.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastImpl.class,
			adjustedDemandForecast.getPrimaryKey(), adjustedDemandForecast);

		adjustedDemandForecast.resetOriginalValues();
	}

	/**
	 * Caches the adjusted demand forecasts in the entity cache if it is enabled.
	 *
	 * @param adjustedDemandForecasts the adjusted demand forecasts
	 */
	@Override
	public void cacheResult(
		List<AdjustedDemandForecast> adjustedDemandForecasts) {
		for (AdjustedDemandForecast adjustedDemandForecast : adjustedDemandForecasts) {
			if (entityCache.getResult(
						AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						AdjustedDemandForecastImpl.class,
						adjustedDemandForecast.getPrimaryKey()) == null) {
				cacheResult(adjustedDemandForecast);
			}
			else {
				adjustedDemandForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all adjusted demand forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AdjustedDemandForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the adjusted demand forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AdjustedDemandForecast adjustedDemandForecast) {
		entityCache.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastImpl.class,
			adjustedDemandForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AdjustedDemandForecast> adjustedDemandForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AdjustedDemandForecast adjustedDemandForecast : adjustedDemandForecasts) {
			entityCache.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				AdjustedDemandForecastImpl.class,
				adjustedDemandForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new adjusted demand forecast with the primary key. Does not add the adjusted demand forecast to the database.
	 *
	 * @param adjustedDemandForecastSid the primary key for the new adjusted demand forecast
	 * @return the new adjusted demand forecast
	 */
	@Override
	public AdjustedDemandForecast create(int adjustedDemandForecastSid) {
		AdjustedDemandForecast adjustedDemandForecast = new AdjustedDemandForecastImpl();

		adjustedDemandForecast.setNew(true);
		adjustedDemandForecast.setPrimaryKey(adjustedDemandForecastSid);

		return adjustedDemandForecast;
	}

	/**
	 * Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast that was removed
	 * @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast remove(int adjustedDemandForecastSid)
		throws NoSuchAdjustedDemandForecastException {
		return remove((Serializable)adjustedDemandForecastSid);
	}

	/**
	 * Removes the adjusted demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast that was removed
	 * @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast remove(Serializable primaryKey)
		throws NoSuchAdjustedDemandForecastException {
		Session session = null;

		try {
			session = openSession();

			AdjustedDemandForecast adjustedDemandForecast = (AdjustedDemandForecast)session.get(AdjustedDemandForecastImpl.class,
					primaryKey);

			if (adjustedDemandForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAdjustedDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(adjustedDemandForecast);
		}
		catch (NoSuchAdjustedDemandForecastException nsee) {
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
	protected AdjustedDemandForecast removeImpl(
		AdjustedDemandForecast adjustedDemandForecast) {
		adjustedDemandForecast = toUnwrappedModel(adjustedDemandForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(adjustedDemandForecast)) {
				adjustedDemandForecast = (AdjustedDemandForecast)session.get(AdjustedDemandForecastImpl.class,
						adjustedDemandForecast.getPrimaryKeyObj());
			}

			if (adjustedDemandForecast != null) {
				session.delete(adjustedDemandForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (adjustedDemandForecast != null) {
			clearCache(adjustedDemandForecast);
		}

		return adjustedDemandForecast;
	}

	@Override
	public AdjustedDemandForecast updateImpl(
		AdjustedDemandForecast adjustedDemandForecast) {
		adjustedDemandForecast = toUnwrappedModel(adjustedDemandForecast);

		boolean isNew = adjustedDemandForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (adjustedDemandForecast.isNew()) {
				session.save(adjustedDemandForecast);

				adjustedDemandForecast.setNew(false);
			}
			else {
				adjustedDemandForecast = (AdjustedDemandForecast)session.merge(adjustedDemandForecast);
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

		entityCache.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			AdjustedDemandForecastImpl.class,
			adjustedDemandForecast.getPrimaryKey(), adjustedDemandForecast,
			false);

		adjustedDemandForecast.resetOriginalValues();

		return adjustedDemandForecast;
	}

	protected AdjustedDemandForecast toUnwrappedModel(
		AdjustedDemandForecast adjustedDemandForecast) {
		if (adjustedDemandForecast instanceof AdjustedDemandForecastImpl) {
			return adjustedDemandForecast;
		}

		AdjustedDemandForecastImpl adjustedDemandForecastImpl = new AdjustedDemandForecastImpl();

		adjustedDemandForecastImpl.setNew(adjustedDemandForecast.isNew());
		adjustedDemandForecastImpl.setPrimaryKey(adjustedDemandForecast.getPrimaryKey());

		adjustedDemandForecastImpl.setItemMasterSid(adjustedDemandForecast.getItemMasterSid());
		adjustedDemandForecastImpl.setAdjustedDemandForecastSid(adjustedDemandForecast.getAdjustedDemandForecastSid());
		adjustedDemandForecastImpl.setGrossUnits(adjustedDemandForecast.getGrossUnits());
		adjustedDemandForecastImpl.setTotalDemandUnits(adjustedDemandForecast.getTotalDemandUnits());
		adjustedDemandForecastImpl.setYear(adjustedDemandForecast.getYear());
		adjustedDemandForecastImpl.setItemId(adjustedDemandForecast.getItemId());
		adjustedDemandForecastImpl.setModifiedDate(adjustedDemandForecast.getModifiedDate());
		adjustedDemandForecastImpl.setBrandMasterSid(adjustedDemandForecast.getBrandMasterSid());
		adjustedDemandForecastImpl.setOrganizationKey(adjustedDemandForecast.getOrganizationKey());
		adjustedDemandForecastImpl.setSource(adjustedDemandForecast.getSource());
		adjustedDemandForecastImpl.setCreatedDate(adjustedDemandForecast.getCreatedDate());
		adjustedDemandForecastImpl.setCreatedBy(adjustedDemandForecast.getCreatedBy());
		adjustedDemandForecastImpl.setMarketShareRatio(adjustedDemandForecast.getMarketShareRatio());
		adjustedDemandForecastImpl.setItemIdentifier(adjustedDemandForecast.getItemIdentifier());
		adjustedDemandForecastImpl.setInboundStatus(adjustedDemandForecast.getInboundStatus());
		adjustedDemandForecastImpl.setModifiedBy(adjustedDemandForecast.getModifiedBy());
		adjustedDemandForecastImpl.setMarketShareUnits(adjustedDemandForecast.getMarketShareUnits());
		adjustedDemandForecastImpl.setMonth(adjustedDemandForecast.getMonth());
		adjustedDemandForecastImpl.setInventoryUnitChange(adjustedDemandForecast.getInventoryUnitChange());
		adjustedDemandForecastImpl.setUncapturedUnitsRatio(adjustedDemandForecast.getUncapturedUnitsRatio());
		adjustedDemandForecastImpl.setCountry(adjustedDemandForecast.getCountry());
		adjustedDemandForecastImpl.setForecastType(adjustedDemandForecast.getForecastType());
		adjustedDemandForecastImpl.setBrandId(adjustedDemandForecast.getBrandId());
		adjustedDemandForecastImpl.setUncapturedUnits(adjustedDemandForecast.getUncapturedUnits());
		adjustedDemandForecastImpl.setGrossPrice(adjustedDemandForecast.getGrossPrice());
		adjustedDemandForecastImpl.setRecordLockStatus(adjustedDemandForecast.isRecordLockStatus());
		adjustedDemandForecastImpl.setGrossAmount(adjustedDemandForecast.getGrossAmount());
		adjustedDemandForecastImpl.setItemIdentifierCodeQualifier(adjustedDemandForecast.getItemIdentifierCodeQualifier());
		adjustedDemandForecastImpl.setForecastVer(adjustedDemandForecast.getForecastVer());
		adjustedDemandForecastImpl.setBatchId(adjustedDemandForecast.getBatchId());
		adjustedDemandForecastImpl.setNetSalesPrice(adjustedDemandForecast.getNetSalesPrice());
		adjustedDemandForecastImpl.setNetSalesAmount(adjustedDemandForecast.getNetSalesAmount());
		adjustedDemandForecastImpl.setSegment(adjustedDemandForecast.getSegment());
		adjustedDemandForecastImpl.setTotalDemandAmount(adjustedDemandForecast.getTotalDemandAmount());
		adjustedDemandForecastImpl.setForecastName(adjustedDemandForecast.getForecastName());
		adjustedDemandForecastImpl.setMarketSizeUnits(adjustedDemandForecast.getMarketSizeUnits());

		return adjustedDemandForecastImpl;
	}

	/**
	 * Returns the adjusted demand forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast
	 * @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAdjustedDemandForecastException {
		AdjustedDemandForecast adjustedDemandForecast = fetchByPrimaryKey(primaryKey);

		if (adjustedDemandForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAdjustedDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return adjustedDemandForecast;
	}

	/**
	 * Returns the adjusted demand forecast with the primary key or throws a {@link NoSuchAdjustedDemandForecastException} if it could not be found.
	 *
	 * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast
	 * @throws NoSuchAdjustedDemandForecastException if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast findByPrimaryKey(
		int adjustedDemandForecastSid)
		throws NoSuchAdjustedDemandForecastException {
		return findByPrimaryKey((Serializable)adjustedDemandForecastSid);
	}

	/**
	 * Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				AdjustedDemandForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AdjustedDemandForecast adjustedDemandForecast = (AdjustedDemandForecast)serializable;

		if (adjustedDemandForecast == null) {
			Session session = null;

			try {
				session = openSession();

				adjustedDemandForecast = (AdjustedDemandForecast)session.get(AdjustedDemandForecastImpl.class,
						primaryKey);

				if (adjustedDemandForecast != null) {
					cacheResult(adjustedDemandForecast);
				}
				else {
					entityCache.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						AdjustedDemandForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					AdjustedDemandForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return adjustedDemandForecast;
	}

	/**
	 * Returns the adjusted demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param adjustedDemandForecastSid the primary key of the adjusted demand forecast
	 * @return the adjusted demand forecast, or <code>null</code> if a adjusted demand forecast with the primary key could not be found
	 */
	@Override
	public AdjustedDemandForecast fetchByPrimaryKey(
		int adjustedDemandForecastSid) {
		return fetchByPrimaryKey((Serializable)adjustedDemandForecastSid);
	}

	@Override
	public Map<Serializable, AdjustedDemandForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AdjustedDemandForecast> map = new HashMap<Serializable, AdjustedDemandForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AdjustedDemandForecast adjustedDemandForecast = fetchByPrimaryKey(primaryKey);

			if (adjustedDemandForecast != null) {
				map.put(primaryKey, adjustedDemandForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					AdjustedDemandForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AdjustedDemandForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ADJUSTEDDEMANDFORECAST_WHERE_PKS_IN);

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

			for (AdjustedDemandForecast adjustedDemandForecast : (List<AdjustedDemandForecast>)q.list()) {
				map.put(adjustedDemandForecast.getPrimaryKeyObj(),
					adjustedDemandForecast);

				cacheResult(adjustedDemandForecast);

				uncachedPrimaryKeys.remove(adjustedDemandForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AdjustedDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					AdjustedDemandForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the adjusted demand forecasts.
	 *
	 * @return the adjusted demand forecasts
	 */
	@Override
	public List<AdjustedDemandForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the adjusted demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of adjusted demand forecasts
	 * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	 * @return the range of adjusted demand forecasts
	 */
	@Override
	public List<AdjustedDemandForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the adjusted demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of adjusted demand forecasts
	 * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of adjusted demand forecasts
	 */
	@Override
	public List<AdjustedDemandForecast> findAll(int start, int end,
		OrderByComparator<AdjustedDemandForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the adjusted demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AdjustedDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of adjusted demand forecasts
	 * @param end the upper bound of the range of adjusted demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of adjusted demand forecasts
	 */
	@Override
	public List<AdjustedDemandForecast> findAll(int start, int end,
		OrderByComparator<AdjustedDemandForecast> orderByComparator,
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

		List<AdjustedDemandForecast> list = null;

		if (retrieveFromCache) {
			list = (List<AdjustedDemandForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ADJUSTEDDEMANDFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ADJUSTEDDEMANDFORECAST;

				if (pagination) {
					sql = sql.concat(AdjustedDemandForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AdjustedDemandForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AdjustedDemandForecast>)QueryUtil.list(q,
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
	 * Removes all the adjusted demand forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AdjustedDemandForecast adjustedDemandForecast : findAll()) {
			remove(adjustedDemandForecast);
		}
	}

	/**
	 * Returns the number of adjusted demand forecasts.
	 *
	 * @return the number of adjusted demand forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ADJUSTEDDEMANDFORECAST);

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
		return AdjustedDemandForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the adjusted demand forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AdjustedDemandForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ADJUSTEDDEMANDFORECAST = "SELECT adjustedDemandForecast FROM AdjustedDemandForecast adjustedDemandForecast";
	private static final String _SQL_SELECT_ADJUSTEDDEMANDFORECAST_WHERE_PKS_IN = "SELECT adjustedDemandForecast FROM AdjustedDemandForecast adjustedDemandForecast WHERE ADJUSTED_DEMAND_FORECAST_SID IN (";
	private static final String _SQL_COUNT_ADJUSTEDDEMANDFORECAST = "SELECT COUNT(adjustedDemandForecast) FROM AdjustedDemandForecast adjustedDemandForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "adjustedDemandForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AdjustedDemandForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AdjustedDemandForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "adjustedDemandForecastSid", "grossUnits",
				"totalDemandUnits", "year", "itemId", "modifiedDate",
				"brandMasterSid", "organizationKey", "source", "createdDate",
				"createdBy", "marketShareRatio", "itemIdentifier",
				"inboundStatus", "modifiedBy", "marketShareUnits", "month",
				"inventoryUnitChange", "uncapturedUnitsRatio", "country",
				"forecastType", "brandId", "uncapturedUnits", "grossPrice",
				"recordLockStatus", "grossAmount", "itemIdentifierCodeQualifier",
				"forecastVer", "batchId", "netSalesPrice", "netSalesAmount",
				"segment", "totalDemandAmount", "forecastName",
				"marketSizeUnits"
			});
}