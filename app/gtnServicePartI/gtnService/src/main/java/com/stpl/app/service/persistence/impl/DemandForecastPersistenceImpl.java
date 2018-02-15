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

import com.stpl.app.exception.NoSuchDemandForecastException;
import com.stpl.app.model.DemandForecast;
import com.stpl.app.model.impl.DemandForecastImpl;
import com.stpl.app.model.impl.DemandForecastModelImpl;
import com.stpl.app.service.persistence.DemandForecastPersistence;

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
 * The persistence implementation for the demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DemandForecastPersistence
 * @see com.stpl.app.service.persistence.DemandForecastUtil
 * @generated
 */
@ProviderType
public class DemandForecastPersistenceImpl extends BasePersistenceImpl<DemandForecast>
	implements DemandForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DemandForecastUtil} to access the demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DemandForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastModelImpl.FINDER_CACHE_ENABLED,
			DemandForecastImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastModelImpl.FINDER_CACHE_ENABLED,
			DemandForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DemandForecastPersistenceImpl() {
		setModelClass(DemandForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("demandForecastSid", "DEMAND_FORECAST_SID");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("marketSizeUnits", "MARKET_SIZE_UNITS");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the demand forecast in the entity cache if it is enabled.
	 *
	 * @param demandForecast the demand forecast
	 */
	@Override
	public void cacheResult(DemandForecast demandForecast) {
		entityCache.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastImpl.class, demandForecast.getPrimaryKey(),
			demandForecast);

		demandForecast.resetOriginalValues();
	}

	/**
	 * Caches the demand forecasts in the entity cache if it is enabled.
	 *
	 * @param demandForecasts the demand forecasts
	 */
	@Override
	public void cacheResult(List<DemandForecast> demandForecasts) {
		for (DemandForecast demandForecast : demandForecasts) {
			if (entityCache.getResult(
						DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						DemandForecastImpl.class, demandForecast.getPrimaryKey()) == null) {
				cacheResult(demandForecast);
			}
			else {
				demandForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all demand forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DemandForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the demand forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DemandForecast demandForecast) {
		entityCache.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastImpl.class, demandForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DemandForecast> demandForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DemandForecast demandForecast : demandForecasts) {
			entityCache.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				DemandForecastImpl.class, demandForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new demand forecast with the primary key. Does not add the demand forecast to the database.
	 *
	 * @param demandForecastSid the primary key for the new demand forecast
	 * @return the new demand forecast
	 */
	@Override
	public DemandForecast create(int demandForecastSid) {
		DemandForecast demandForecast = new DemandForecastImpl();

		demandForecast.setNew(true);
		demandForecast.setPrimaryKey(demandForecastSid);

		return demandForecast;
	}

	/**
	 * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param demandForecastSid the primary key of the demand forecast
	 * @return the demand forecast that was removed
	 * @throws NoSuchDemandForecastException if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast remove(int demandForecastSid)
		throws NoSuchDemandForecastException {
		return remove((Serializable)demandForecastSid);
	}

	/**
	 * Removes the demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the demand forecast
	 * @return the demand forecast that was removed
	 * @throws NoSuchDemandForecastException if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast remove(Serializable primaryKey)
		throws NoSuchDemandForecastException {
		Session session = null;

		try {
			session = openSession();

			DemandForecast demandForecast = (DemandForecast)session.get(DemandForecastImpl.class,
					primaryKey);

			if (demandForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(demandForecast);
		}
		catch (NoSuchDemandForecastException nsee) {
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
	protected DemandForecast removeImpl(DemandForecast demandForecast) {
		demandForecast = toUnwrappedModel(demandForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(demandForecast)) {
				demandForecast = (DemandForecast)session.get(DemandForecastImpl.class,
						demandForecast.getPrimaryKeyObj());
			}

			if (demandForecast != null) {
				session.delete(demandForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (demandForecast != null) {
			clearCache(demandForecast);
		}

		return demandForecast;
	}

	@Override
	public DemandForecast updateImpl(DemandForecast demandForecast) {
		demandForecast = toUnwrappedModel(demandForecast);

		boolean isNew = demandForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (demandForecast.isNew()) {
				session.save(demandForecast);

				demandForecast.setNew(false);
			}
			else {
				demandForecast = (DemandForecast)session.merge(demandForecast);
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

		entityCache.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			DemandForecastImpl.class, demandForecast.getPrimaryKey(),
			demandForecast, false);

		demandForecast.resetOriginalValues();

		return demandForecast;
	}

	protected DemandForecast toUnwrappedModel(DemandForecast demandForecast) {
		if (demandForecast instanceof DemandForecastImpl) {
			return demandForecast;
		}

		DemandForecastImpl demandForecastImpl = new DemandForecastImpl();

		demandForecastImpl.setNew(demandForecast.isNew());
		demandForecastImpl.setPrimaryKey(demandForecast.getPrimaryKey());

		demandForecastImpl.setModifiedBy(demandForecast.getModifiedBy());
		demandForecastImpl.setCreatedDate(demandForecast.getCreatedDate());
		demandForecastImpl.setTotalDemandUnits(demandForecast.getTotalDemandUnits());
		demandForecastImpl.setBrandMasterSid(demandForecast.getBrandMasterSid());
		demandForecastImpl.setMarketShareUnits(demandForecast.getMarketShareUnits());
		demandForecastImpl.setBatchId(demandForecast.getBatchId());
		demandForecastImpl.setGrossAmount(demandForecast.getGrossAmount());
		demandForecastImpl.setForecastVer(demandForecast.getForecastVer());
		demandForecastImpl.setBrandId(demandForecast.getBrandId());
		demandForecastImpl.setGrossUnits(demandForecast.getGrossUnits());
		demandForecastImpl.setCountry(demandForecast.getCountry());
		demandForecastImpl.setDemandForecastSid(demandForecast.getDemandForecastSid());
		demandForecastImpl.setForecastType(demandForecast.getForecastType());
		demandForecastImpl.setItemMasterSid(demandForecast.getItemMasterSid());
		demandForecastImpl.setTotalDemandAmount(demandForecast.getTotalDemandAmount());
		demandForecastImpl.setForecastMonth(demandForecast.getForecastMonth());
		demandForecastImpl.setOrganizationKey(demandForecast.getOrganizationKey());
		demandForecastImpl.setCreatedBy(demandForecast.getCreatedBy());
		demandForecastImpl.setMarketSizeUnits(demandForecast.getMarketSizeUnits());
		demandForecastImpl.setSegment(demandForecast.getSegment());
		demandForecastImpl.setForecastYear(demandForecast.getForecastYear());
		demandForecastImpl.setItemId(demandForecast.getItemId());
		demandForecastImpl.setInventoryUnitChange(demandForecast.getInventoryUnitChange());
		demandForecastImpl.setGrossPrice(demandForecast.getGrossPrice());
		demandForecastImpl.setForecastName(demandForecast.getForecastName());
		demandForecastImpl.setNetSalesAmount(demandForecast.getNetSalesAmount());
		demandForecastImpl.setModifiedDate(demandForecast.getModifiedDate());
		demandForecastImpl.setItemIdentifier(demandForecast.getItemIdentifier());
		demandForecastImpl.setRecordLockStatus(demandForecast.isRecordLockStatus());
		demandForecastImpl.setUncapturedUnitsRatio(demandForecast.getUncapturedUnitsRatio());
		demandForecastImpl.setItemIdentifierCodeQualifier(demandForecast.getItemIdentifierCodeQualifier());
		demandForecastImpl.setMarketShareRatio(demandForecast.getMarketShareRatio());
		demandForecastImpl.setSource(demandForecast.getSource());
		demandForecastImpl.setUncapturedUnits(demandForecast.getUncapturedUnits());
		demandForecastImpl.setNetSalesPrice(demandForecast.getNetSalesPrice());
		demandForecastImpl.setInboundStatus(demandForecast.getInboundStatus());

		return demandForecastImpl;
	}

	/**
	 * Returns the demand forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the demand forecast
	 * @return the demand forecast
	 * @throws NoSuchDemandForecastException if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDemandForecastException {
		DemandForecast demandForecast = fetchByPrimaryKey(primaryKey);

		if (demandForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return demandForecast;
	}

	/**
	 * Returns the demand forecast with the primary key or throws a {@link NoSuchDemandForecastException} if it could not be found.
	 *
	 * @param demandForecastSid the primary key of the demand forecast
	 * @return the demand forecast
	 * @throws NoSuchDemandForecastException if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast findByPrimaryKey(int demandForecastSid)
		throws NoSuchDemandForecastException {
		return findByPrimaryKey((Serializable)demandForecastSid);
	}

	/**
	 * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the demand forecast
	 * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				DemandForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DemandForecast demandForecast = (DemandForecast)serializable;

		if (demandForecast == null) {
			Session session = null;

			try {
				session = openSession();

				demandForecast = (DemandForecast)session.get(DemandForecastImpl.class,
						primaryKey);

				if (demandForecast != null) {
					cacheResult(demandForecast);
				}
				else {
					entityCache.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						DemandForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					DemandForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return demandForecast;
	}

	/**
	 * Returns the demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param demandForecastSid the primary key of the demand forecast
	 * @return the demand forecast, or <code>null</code> if a demand forecast with the primary key could not be found
	 */
	@Override
	public DemandForecast fetchByPrimaryKey(int demandForecastSid) {
		return fetchByPrimaryKey((Serializable)demandForecastSid);
	}

	@Override
	public Map<Serializable, DemandForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DemandForecast> map = new HashMap<Serializable, DemandForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DemandForecast demandForecast = fetchByPrimaryKey(primaryKey);

			if (demandForecast != null) {
				map.put(primaryKey, demandForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					DemandForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DemandForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DEMANDFORECAST_WHERE_PKS_IN);

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

			for (DemandForecast demandForecast : (List<DemandForecast>)q.list()) {
				map.put(demandForecast.getPrimaryKeyObj(), demandForecast);

				cacheResult(demandForecast);

				uncachedPrimaryKeys.remove(demandForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					DemandForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the demand forecasts.
	 *
	 * @return the demand forecasts
	 */
	@Override
	public List<DemandForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of demand forecasts
	 * @param end the upper bound of the range of demand forecasts (not inclusive)
	 * @return the range of demand forecasts
	 */
	@Override
	public List<DemandForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of demand forecasts
	 * @param end the upper bound of the range of demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of demand forecasts
	 */
	@Override
	public List<DemandForecast> findAll(int start, int end,
		OrderByComparator<DemandForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of demand forecasts
	 * @param end the upper bound of the range of demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of demand forecasts
	 */
	@Override
	public List<DemandForecast> findAll(int start, int end,
		OrderByComparator<DemandForecast> orderByComparator,
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

		List<DemandForecast> list = null;

		if (retrieveFromCache) {
			list = (List<DemandForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DEMANDFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEMANDFORECAST;

				if (pagination) {
					sql = sql.concat(DemandForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DemandForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DemandForecast>)QueryUtil.list(q,
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
	 * Removes all the demand forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DemandForecast demandForecast : findAll()) {
			remove(demandForecast);
		}
	}

	/**
	 * Returns the number of demand forecasts.
	 *
	 * @return the number of demand forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEMANDFORECAST);

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
		return DemandForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the demand forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DemandForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DEMANDFORECAST = "SELECT demandForecast FROM DemandForecast demandForecast";
	private static final String _SQL_SELECT_DEMANDFORECAST_WHERE_PKS_IN = "SELECT demandForecast FROM DemandForecast demandForecast WHERE DEMAND_FORECAST_SID IN (";
	private static final String _SQL_COUNT_DEMANDFORECAST = "SELECT COUNT(demandForecast) FROM DemandForecast demandForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "demandForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DemandForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DemandForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"modifiedBy", "createdDate", "totalDemandUnits",
				"brandMasterSid", "marketShareUnits", "batchId", "grossAmount",
				"forecastVer", "brandId", "grossUnits", "country",
				"demandForecastSid", "forecastType", "itemMasterSid",
				"totalDemandAmount", "forecastMonth", "organizationKey",
				"createdBy", "marketSizeUnits", "segment", "forecastYear",
				"itemId", "inventoryUnitChange", "grossPrice", "forecastName",
				"netSalesAmount", "modifiedDate", "itemIdentifier",
				"recordLockStatus", "uncapturedUnitsRatio",
				"itemIdentifierCodeQualifier", "marketShareRatio", "source",
				"uncapturedUnits", "netSalesPrice", "inboundStatus"
			});
}