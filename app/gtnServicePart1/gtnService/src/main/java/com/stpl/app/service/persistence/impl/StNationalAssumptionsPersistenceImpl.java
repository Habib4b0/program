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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchStNationalAssumptionsException;
import com.stpl.app.model.StNationalAssumptions;
import com.stpl.app.model.impl.StNationalAssumptionsImpl;
import com.stpl.app.model.impl.StNationalAssumptionsModelImpl;
import com.stpl.app.service.persistence.StNationalAssumptionsPK;
import com.stpl.app.service.persistence.StNationalAssumptionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNationalAssumptionsPersistence
 * @see com.stpl.app.service.persistence.StNationalAssumptionsUtil
 * @generated
 */
@ProviderType
public class StNationalAssumptionsPersistenceImpl extends BasePersistenceImpl<StNationalAssumptions>
	implements StNationalAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNationalAssumptionsUtil} to access the st national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNationalAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StNationalAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StNationalAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNationalAssumptionsPersistenceImpl() {
		setModelClass(StNationalAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("baselinePeriod", "BASELINE_PERIOD");
			dbColumnNames.put("startPeriod", "START_PERIOD");
			dbColumnNames.put("frequency", "FREQUENCY");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("endPeriod", "END_PERIOD");
			dbColumnNames.put("naProjMasterSid", "NA_PROJ_MASTER_SID");
			dbColumnNames.put("rollingPeriod", "ROLLING_PERIOD");
			dbColumnNames.put("forecastMethodology", "FORECAST_METHODOLOGY");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("priceBasis", "PRICE_BASIS");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("baselineMethodology", "BASELINE_METHODOLOGY");
			dbColumnNames.put("growthRate", "GROWTH_RATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st national assumptions in the entity cache if it is enabled.
	 *
	 * @param stNationalAssumptions the st national assumptions
	 */
	@Override
	public void cacheResult(StNationalAssumptions stNationalAssumptions) {
		entityCache.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsImpl.class,
			stNationalAssumptions.getPrimaryKey(), stNationalAssumptions);

		stNationalAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the st national assumptionses in the entity cache if it is enabled.
	 *
	 * @param stNationalAssumptionses the st national assumptionses
	 */
	@Override
	public void cacheResult(List<StNationalAssumptions> stNationalAssumptionses) {
		for (StNationalAssumptions stNationalAssumptions : stNationalAssumptionses) {
			if (entityCache.getResult(
						StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StNationalAssumptionsImpl.class,
						stNationalAssumptions.getPrimaryKey()) == null) {
				cacheResult(stNationalAssumptions);
			}
			else {
				stNationalAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st national assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNationalAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st national assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNationalAssumptions stNationalAssumptions) {
		entityCache.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsImpl.class,
			stNationalAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNationalAssumptions> stNationalAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNationalAssumptions stNationalAssumptions : stNationalAssumptionses) {
			entityCache.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StNationalAssumptionsImpl.class,
				stNationalAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
	 *
	 * @param stNationalAssumptionsPK the primary key for the new st national assumptions
	 * @return the new st national assumptions
	 */
	@Override
	public StNationalAssumptions create(
		StNationalAssumptionsPK stNationalAssumptionsPK) {
		StNationalAssumptions stNationalAssumptions = new StNationalAssumptionsImpl();

		stNationalAssumptions.setNew(true);
		stNationalAssumptions.setPrimaryKey(stNationalAssumptionsPK);

		return stNationalAssumptions;
	}

	/**
	 * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNationalAssumptionsPK the primary key of the st national assumptions
	 * @return the st national assumptions that was removed
	 * @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions remove(
		StNationalAssumptionsPK stNationalAssumptionsPK)
		throws NoSuchStNationalAssumptionsException {
		return remove((Serializable)stNationalAssumptionsPK);
	}

	/**
	 * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st national assumptions
	 * @return the st national assumptions that was removed
	 * @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions remove(Serializable primaryKey)
		throws NoSuchStNationalAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			StNationalAssumptions stNationalAssumptions = (StNationalAssumptions)session.get(StNationalAssumptionsImpl.class,
					primaryKey);

			if (stNationalAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNationalAssumptions);
		}
		catch (NoSuchStNationalAssumptionsException nsee) {
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
	protected StNationalAssumptions removeImpl(
		StNationalAssumptions stNationalAssumptions) {
		stNationalAssumptions = toUnwrappedModel(stNationalAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNationalAssumptions)) {
				stNationalAssumptions = (StNationalAssumptions)session.get(StNationalAssumptionsImpl.class,
						stNationalAssumptions.getPrimaryKeyObj());
			}

			if (stNationalAssumptions != null) {
				session.delete(stNationalAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNationalAssumptions != null) {
			clearCache(stNationalAssumptions);
		}

		return stNationalAssumptions;
	}

	@Override
	public StNationalAssumptions updateImpl(
		StNationalAssumptions stNationalAssumptions) {
		stNationalAssumptions = toUnwrappedModel(stNationalAssumptions);

		boolean isNew = stNationalAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNationalAssumptions.isNew()) {
				session.save(stNationalAssumptions);

				stNationalAssumptions.setNew(false);
			}
			else {
				stNationalAssumptions = (StNationalAssumptions)session.merge(stNationalAssumptions);
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

		entityCache.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNationalAssumptionsImpl.class,
			stNationalAssumptions.getPrimaryKey(), stNationalAssumptions, false);

		stNationalAssumptions.resetOriginalValues();

		return stNationalAssumptions;
	}

	protected StNationalAssumptions toUnwrappedModel(
		StNationalAssumptions stNationalAssumptions) {
		if (stNationalAssumptions instanceof StNationalAssumptionsImpl) {
			return stNationalAssumptions;
		}

		StNationalAssumptionsImpl stNationalAssumptionsImpl = new StNationalAssumptionsImpl();

		stNationalAssumptionsImpl.setNew(stNationalAssumptions.isNew());
		stNationalAssumptionsImpl.setPrimaryKey(stNationalAssumptions.getPrimaryKey());

		stNationalAssumptionsImpl.setLastModifiedDate(stNationalAssumptions.getLastModifiedDate());
		stNationalAssumptionsImpl.setBaselinePeriod(stNationalAssumptions.getBaselinePeriod());
		stNationalAssumptionsImpl.setStartPeriod(stNationalAssumptions.getStartPeriod());
		stNationalAssumptionsImpl.setFrequency(stNationalAssumptions.getFrequency());
		stNationalAssumptionsImpl.setUserId(stNationalAssumptions.getUserId());
		stNationalAssumptionsImpl.setEndPeriod(stNationalAssumptions.getEndPeriod());
		stNationalAssumptionsImpl.setNaProjMasterSid(stNationalAssumptions.getNaProjMasterSid());
		stNationalAssumptionsImpl.setRollingPeriod(stNationalAssumptions.getRollingPeriod());
		stNationalAssumptionsImpl.setForecastMethodology(stNationalAssumptions.getForecastMethodology());
		stNationalAssumptionsImpl.setPriceType(stNationalAssumptions.getPriceType());
		stNationalAssumptionsImpl.setPriceBasis(stNationalAssumptions.getPriceBasis());
		stNationalAssumptionsImpl.setSessionId(stNationalAssumptions.getSessionId());
		stNationalAssumptionsImpl.setBaselineMethodology(stNationalAssumptions.getBaselineMethodology());
		stNationalAssumptionsImpl.setGrowthRate(stNationalAssumptions.getGrowthRate());

		return stNationalAssumptionsImpl;
	}

	/**
	 * Returns the st national assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st national assumptions
	 * @return the st national assumptions
	 * @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNationalAssumptionsException {
		StNationalAssumptions stNationalAssumptions = fetchByPrimaryKey(primaryKey);

		if (stNationalAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNationalAssumptions;
	}

	/**
	 * Returns the st national assumptions with the primary key or throws a {@link NoSuchStNationalAssumptionsException} if it could not be found.
	 *
	 * @param stNationalAssumptionsPK the primary key of the st national assumptions
	 * @return the st national assumptions
	 * @throws NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions findByPrimaryKey(
		StNationalAssumptionsPK stNationalAssumptionsPK)
		throws NoSuchStNationalAssumptionsException {
		return findByPrimaryKey((Serializable)stNationalAssumptionsPK);
	}

	/**
	 * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st national assumptions
	 * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StNationalAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNationalAssumptions stNationalAssumptions = (StNationalAssumptions)serializable;

		if (stNationalAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				stNationalAssumptions = (StNationalAssumptions)session.get(StNationalAssumptionsImpl.class,
						primaryKey);

				if (stNationalAssumptions != null) {
					cacheResult(stNationalAssumptions);
				}
				else {
					entityCache.putResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StNationalAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					StNationalAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNationalAssumptions;
	}

	/**
	 * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNationalAssumptionsPK the primary key of the st national assumptions
	 * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
	 */
	@Override
	public StNationalAssumptions fetchByPrimaryKey(
		StNationalAssumptionsPK stNationalAssumptionsPK) {
		return fetchByPrimaryKey((Serializable)stNationalAssumptionsPK);
	}

	@Override
	public Map<Serializable, StNationalAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNationalAssumptions> map = new HashMap<Serializable, StNationalAssumptions>();

		for (Serializable primaryKey : primaryKeys) {
			StNationalAssumptions stNationalAssumptions = fetchByPrimaryKey(primaryKey);

			if (stNationalAssumptions != null) {
				map.put(primaryKey, stNationalAssumptions);
			}
		}

		return map;
	}

	/**
	 * Returns all the st national assumptionses.
	 *
	 * @return the st national assumptionses
	 */
	@Override
	public List<StNationalAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st national assumptionses
	 * @param end the upper bound of the range of st national assumptionses (not inclusive)
	 * @return the range of st national assumptionses
	 */
	@Override
	public List<StNationalAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st national assumptionses
	 * @param end the upper bound of the range of st national assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st national assumptionses
	 */
	@Override
	public List<StNationalAssumptions> findAll(int start, int end,
		OrderByComparator<StNationalAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st national assumptionses
	 * @param end the upper bound of the range of st national assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st national assumptionses
	 */
	@Override
	public List<StNationalAssumptions> findAll(int start, int end,
		OrderByComparator<StNationalAssumptions> orderByComparator,
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

		List<StNationalAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<StNationalAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNATIONALASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNATIONALASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(StNationalAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNationalAssumptions>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNationalAssumptions>)QueryUtil.list(q,
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
	 * Removes all the st national assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNationalAssumptions stNationalAssumptions : findAll()) {
			remove(stNationalAssumptions);
		}
	}

	/**
	 * Returns the number of st national assumptionses.
	 *
	 * @return the number of st national assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNATIONALASSUMPTIONS);

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
		return StNationalAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st national assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNationalAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNATIONALASSUMPTIONS = "SELECT stNationalAssumptions FROM StNationalAssumptions stNationalAssumptions";
	private static final String _SQL_COUNT_STNATIONALASSUMPTIONS = "SELECT COUNT(stNationalAssumptions) FROM StNationalAssumptions stNationalAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNationalAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNationalAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNationalAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "baselinePeriod", "startPeriod", "frequency",
				"userId", "endPeriod", "naProjMasterSid", "rollingPeriod",
				"forecastMethodology", "priceType", "priceBasis", "sessionId",
				"baselineMethodology", "growthRate"
			});
}