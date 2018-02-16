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

import com.stpl.app.exception.NoSuchForecastConfigException;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.impl.ForecastConfigImpl;
import com.stpl.app.model.impl.ForecastConfigModelImpl;
import com.stpl.app.service.persistence.ForecastConfigPersistence;

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
 * The persistence implementation for the forecast config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastConfigPersistence
 * @see com.stpl.app.service.persistence.ForecastConfigUtil
 * @generated
 */
@ProviderType
public class ForecastConfigPersistenceImpl extends BasePersistenceImpl<ForecastConfig>
	implements ForecastConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ForecastConfigUtil} to access the forecast config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ForecastConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigModelImpl.FINDER_CACHE_ENABLED,
			ForecastConfigImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigModelImpl.FINDER_CACHE_ENABLED,
			ForecastConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ForecastConfigPersistenceImpl() {
		setModelClass(ForecastConfig.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("processType", "PROCESS_TYPE");
			dbColumnNames.put("toDate", "TO_DATE");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("forecastConfigSid", "FORECAST_CONFIG_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("fromDate", "FROM_DATE");
			dbColumnNames.put("projValue", "PROJ_VALUE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("projFreq", "PROJ_FREQ");
			dbColumnNames.put("histValue", "HIST_VALUE");
			dbColumnNames.put("businessProcessType", "BUSINESS_PROCESS_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("histFreq", "HIST_FREQ");
			dbColumnNames.put("activeStartDate", "ACTIVE_START_DATE");
			dbColumnNames.put("activeEndDate", "ACTIVE_END_DATE");
			dbColumnNames.put("processMode", "PROCESS_MODE");
			dbColumnNames.put("historicalDataIntervalFrom",
				"HISTORICAL_DATA_INTERVAL_FROM");
			dbColumnNames.put("historicalTimePeriodFrom",
				"HISTORICAL_TIME_PERIOD_FROM");
			dbColumnNames.put("projHistFreq", "PROJ_HIST_FREQ");
			dbColumnNames.put("futureTimePeriodFrom", "FUTURE_TIME_PERIOD_FROM");
			dbColumnNames.put("historicalDataIntervalTo",
				"HISTORICAL_DATA_INTERVAL_TO");
			dbColumnNames.put("projHistValue", "PROJ_HIST_VALUE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the forecast config in the entity cache if it is enabled.
	 *
	 * @param forecastConfig the forecast config
	 */
	@Override
	public void cacheResult(ForecastConfig forecastConfig) {
		entityCache.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigImpl.class, forecastConfig.getPrimaryKey(),
			forecastConfig);

		forecastConfig.resetOriginalValues();
	}

	/**
	 * Caches the forecast configs in the entity cache if it is enabled.
	 *
	 * @param forecastConfigs the forecast configs
	 */
	@Override
	public void cacheResult(List<ForecastConfig> forecastConfigs) {
		for (ForecastConfig forecastConfig : forecastConfigs) {
			if (entityCache.getResult(
						ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
						ForecastConfigImpl.class, forecastConfig.getPrimaryKey()) == null) {
				cacheResult(forecastConfig);
			}
			else {
				forecastConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all forecast configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ForecastConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the forecast config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ForecastConfig forecastConfig) {
		entityCache.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigImpl.class, forecastConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ForecastConfig> forecastConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ForecastConfig forecastConfig : forecastConfigs) {
			entityCache.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
				ForecastConfigImpl.class, forecastConfig.getPrimaryKey());
		}
	}

	/**
	 * Creates a new forecast config with the primary key. Does not add the forecast config to the database.
	 *
	 * @param forecastConfigSid the primary key for the new forecast config
	 * @return the new forecast config
	 */
	@Override
	public ForecastConfig create(int forecastConfigSid) {
		ForecastConfig forecastConfig = new ForecastConfigImpl();

		forecastConfig.setNew(true);
		forecastConfig.setPrimaryKey(forecastConfigSid);

		return forecastConfig;
	}

	/**
	 * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param forecastConfigSid the primary key of the forecast config
	 * @return the forecast config that was removed
	 * @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig remove(int forecastConfigSid)
		throws NoSuchForecastConfigException {
		return remove((Serializable)forecastConfigSid);
	}

	/**
	 * Removes the forecast config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the forecast config
	 * @return the forecast config that was removed
	 * @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig remove(Serializable primaryKey)
		throws NoSuchForecastConfigException {
		Session session = null;

		try {
			session = openSession();

			ForecastConfig forecastConfig = (ForecastConfig)session.get(ForecastConfigImpl.class,
					primaryKey);

			if (forecastConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchForecastConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(forecastConfig);
		}
		catch (NoSuchForecastConfigException nsee) {
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
	protected ForecastConfig removeImpl(ForecastConfig forecastConfig) {
		forecastConfig = toUnwrappedModel(forecastConfig);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(forecastConfig)) {
				forecastConfig = (ForecastConfig)session.get(ForecastConfigImpl.class,
						forecastConfig.getPrimaryKeyObj());
			}

			if (forecastConfig != null) {
				session.delete(forecastConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (forecastConfig != null) {
			clearCache(forecastConfig);
		}

		return forecastConfig;
	}

	@Override
	public ForecastConfig updateImpl(ForecastConfig forecastConfig) {
		forecastConfig = toUnwrappedModel(forecastConfig);

		boolean isNew = forecastConfig.isNew();

		Session session = null;

		try {
			session = openSession();

			if (forecastConfig.isNew()) {
				session.save(forecastConfig);

				forecastConfig.setNew(false);
			}
			else {
				forecastConfig = (ForecastConfig)session.merge(forecastConfig);
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

		entityCache.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
			ForecastConfigImpl.class, forecastConfig.getPrimaryKey(),
			forecastConfig, false);

		forecastConfig.resetOriginalValues();

		return forecastConfig;
	}

	protected ForecastConfig toUnwrappedModel(ForecastConfig forecastConfig) {
		if (forecastConfig instanceof ForecastConfigImpl) {
			return forecastConfig;
		}

		ForecastConfigImpl forecastConfigImpl = new ForecastConfigImpl();

		forecastConfigImpl.setNew(forecastConfig.isNew());
		forecastConfigImpl.setPrimaryKey(forecastConfig.getPrimaryKey());

		forecastConfigImpl.setProcessType(forecastConfig.isProcessType());
		forecastConfigImpl.setToDate(forecastConfig.getToDate());
		forecastConfigImpl.setVersionNo(forecastConfig.getVersionNo());
		forecastConfigImpl.setForecastConfigSid(forecastConfig.getForecastConfigSid());
		forecastConfigImpl.setModifiedDate(forecastConfig.getModifiedDate());
		forecastConfigImpl.setFromDate(forecastConfig.getFromDate());
		forecastConfigImpl.setProjValue(forecastConfig.getProjValue());
		forecastConfigImpl.setCreatedBy(forecastConfig.getCreatedBy());
		forecastConfigImpl.setCreatedDate(forecastConfig.getCreatedDate());
		forecastConfigImpl.setProjFreq(forecastConfig.getProjFreq());
		forecastConfigImpl.setHistValue(forecastConfig.getHistValue());
		forecastConfigImpl.setBusinessProcessType(forecastConfig.getBusinessProcessType());
		forecastConfigImpl.setModifiedBy(forecastConfig.getModifiedBy());
		forecastConfigImpl.setHistFreq(forecastConfig.getHistFreq());
		forecastConfigImpl.setActiveStartDate(forecastConfig.getActiveStartDate());
		forecastConfigImpl.setActiveEndDate(forecastConfig.getActiveEndDate());
		forecastConfigImpl.setProcessMode(forecastConfig.isProcessMode());
		forecastConfigImpl.setHistoricalDataIntervalFrom(forecastConfig.getHistoricalDataIntervalFrom());
		forecastConfigImpl.setHistoricalTimePeriodFrom(forecastConfig.getHistoricalTimePeriodFrom());
		forecastConfigImpl.setProjHistFreq(forecastConfig.getProjHistFreq());
		forecastConfigImpl.setFutureTimePeriodFrom(forecastConfig.getFutureTimePeriodFrom());
		forecastConfigImpl.setHistoricalDataIntervalTo(forecastConfig.getHistoricalDataIntervalTo());
		forecastConfigImpl.setProjHistValue(forecastConfig.getProjHistValue());

		return forecastConfigImpl;
	}

	/**
	 * Returns the forecast config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecast config
	 * @return the forecast config
	 * @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchForecastConfigException {
		ForecastConfig forecastConfig = fetchByPrimaryKey(primaryKey);

		if (forecastConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchForecastConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return forecastConfig;
	}

	/**
	 * Returns the forecast config with the primary key or throws a {@link NoSuchForecastConfigException} if it could not be found.
	 *
	 * @param forecastConfigSid the primary key of the forecast config
	 * @return the forecast config
	 * @throws NoSuchForecastConfigException if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig findByPrimaryKey(int forecastConfigSid)
		throws NoSuchForecastConfigException {
		return findByPrimaryKey((Serializable)forecastConfigSid);
	}

	/**
	 * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecast config
	 * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
				ForecastConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ForecastConfig forecastConfig = (ForecastConfig)serializable;

		if (forecastConfig == null) {
			Session session = null;

			try {
				session = openSession();

				forecastConfig = (ForecastConfig)session.get(ForecastConfigImpl.class,
						primaryKey);

				if (forecastConfig != null) {
					cacheResult(forecastConfig);
				}
				else {
					entityCache.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
						ForecastConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
					ForecastConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return forecastConfig;
	}

	/**
	 * Returns the forecast config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param forecastConfigSid the primary key of the forecast config
	 * @return the forecast config, or <code>null</code> if a forecast config with the primary key could not be found
	 */
	@Override
	public ForecastConfig fetchByPrimaryKey(int forecastConfigSid) {
		return fetchByPrimaryKey((Serializable)forecastConfigSid);
	}

	@Override
	public Map<Serializable, ForecastConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ForecastConfig> map = new HashMap<Serializable, ForecastConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ForecastConfig forecastConfig = fetchByPrimaryKey(primaryKey);

			if (forecastConfig != null) {
				map.put(primaryKey, forecastConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
					ForecastConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ForecastConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORECASTCONFIG_WHERE_PKS_IN);

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

			for (ForecastConfig forecastConfig : (List<ForecastConfig>)q.list()) {
				map.put(forecastConfig.getPrimaryKeyObj(), forecastConfig);

				cacheResult(forecastConfig);

				uncachedPrimaryKeys.remove(forecastConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ForecastConfigModelImpl.ENTITY_CACHE_ENABLED,
					ForecastConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the forecast configs.
	 *
	 * @return the forecast configs
	 */
	@Override
	public List<ForecastConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the forecast configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecast configs
	 * @param end the upper bound of the range of forecast configs (not inclusive)
	 * @return the range of forecast configs
	 */
	@Override
	public List<ForecastConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the forecast configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecast configs
	 * @param end the upper bound of the range of forecast configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of forecast configs
	 */
	@Override
	public List<ForecastConfig> findAll(int start, int end,
		OrderByComparator<ForecastConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the forecast configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecast configs
	 * @param end the upper bound of the range of forecast configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of forecast configs
	 */
	@Override
	public List<ForecastConfig> findAll(int start, int end,
		OrderByComparator<ForecastConfig> orderByComparator,
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

		List<ForecastConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ForecastConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORECASTCONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORECASTCONFIG;

				if (pagination) {
					sql = sql.concat(ForecastConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ForecastConfig>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ForecastConfig>)QueryUtil.list(q,
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
	 * Removes all the forecast configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ForecastConfig forecastConfig : findAll()) {
			remove(forecastConfig);
		}
	}

	/**
	 * Returns the number of forecast configs.
	 *
	 * @return the number of forecast configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORECASTCONFIG);

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
		return ForecastConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the forecast config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ForecastConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FORECASTCONFIG = "SELECT forecastConfig FROM ForecastConfig forecastConfig";
	private static final String _SQL_SELECT_FORECASTCONFIG_WHERE_PKS_IN = "SELECT forecastConfig FROM ForecastConfig forecastConfig WHERE FORECAST_CONFIG_SID IN (";
	private static final String _SQL_COUNT_FORECASTCONFIG = "SELECT COUNT(forecastConfig) FROM ForecastConfig forecastConfig";
	private static final String _ORDER_BY_ENTITY_ALIAS = "forecastConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastConfig exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ForecastConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"processType", "toDate", "versionNo", "forecastConfigSid",
				"modifiedDate", "fromDate", "projValue", "createdBy",
				"createdDate", "projFreq", "histValue", "businessProcessType",
				"modifiedBy", "histFreq", "activeStartDate", "activeEndDate",
				"processMode", "historicalDataIntervalFrom",
				"historicalTimePeriodFrom", "projHistFreq",
				"futureTimePeriodFrom", "historicalDataIntervalTo",
				"projHistValue"
			});
}