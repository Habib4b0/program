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

import com.stpl.app.exception.NoSuchNationalAssumptionsException;
import com.stpl.app.model.NationalAssumptions;
import com.stpl.app.model.impl.NationalAssumptionsImpl;
import com.stpl.app.model.impl.NationalAssumptionsModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsPK;
import com.stpl.app.service.persistence.NationalAssumptionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the national assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsPersistence
 * @see com.stpl.app.service.persistence.NationalAssumptionsUtil
 * @generated
 */
@ProviderType
public class NationalAssumptionsPersistenceImpl extends BasePersistenceImpl<NationalAssumptions>
	implements NationalAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NationalAssumptionsUtil} to access the national assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NationalAssumptionsPersistenceImpl() {
		setModelClass(NationalAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("baselinePeriod", "BASELINE_PERIOD");
			dbColumnNames.put("frequency", "FREQUENCY");
			dbColumnNames.put("startPeriod", "START_PERIOD");
			dbColumnNames.put("forecastMethodology", "FORECAST_METHODOLOGY");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("endPeriod", "END_PERIOD");
			dbColumnNames.put("priceBasis", "PRICE_BASIS");
			dbColumnNames.put("naProjMasterSid", "NA_PROJ_MASTER_SID");
			dbColumnNames.put("rollingPeriod", "ROLLING_PERIOD");
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
	 * Caches the national assumptions in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptions the national assumptions
	 */
	@Override
	public void cacheResult(NationalAssumptions nationalAssumptions) {
		entityCache.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey(),
			nationalAssumptions);

		nationalAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the national assumptionses in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptionses the national assumptionses
	 */
	@Override
	public void cacheResult(List<NationalAssumptions> nationalAssumptionses) {
		for (NationalAssumptions nationalAssumptions : nationalAssumptionses) {
			if (entityCache.getResult(
						NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsImpl.class,
						nationalAssumptions.getPrimaryKey()) == null) {
				cacheResult(nationalAssumptions);
			}
			else {
				nationalAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all national assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NationalAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the national assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NationalAssumptions nationalAssumptions) {
		entityCache.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NationalAssumptions> nationalAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NationalAssumptions nationalAssumptions : nationalAssumptionses) {
			entityCache.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsImpl.class,
				nationalAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
	 *
	 * @param nationalAssumptionsPK the primary key for the new national assumptions
	 * @return the new national assumptions
	 */
	@Override
	public NationalAssumptions create(
		NationalAssumptionsPK nationalAssumptionsPK) {
		NationalAssumptions nationalAssumptions = new NationalAssumptionsImpl();

		nationalAssumptions.setNew(true);
		nationalAssumptions.setPrimaryKey(nationalAssumptionsPK);

		return nationalAssumptions;
	}

	/**
	 * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nationalAssumptionsPK the primary key of the national assumptions
	 * @return the national assumptions that was removed
	 * @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions remove(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws NoSuchNationalAssumptionsException {
		return remove((Serializable)nationalAssumptionsPK);
	}

	/**
	 * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the national assumptions
	 * @return the national assumptions that was removed
	 * @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions remove(Serializable primaryKey)
		throws NoSuchNationalAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			NationalAssumptions nationalAssumptions = (NationalAssumptions)session.get(NationalAssumptionsImpl.class,
					primaryKey);

			if (nationalAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nationalAssumptions);
		}
		catch (NoSuchNationalAssumptionsException nsee) {
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
	protected NationalAssumptions removeImpl(
		NationalAssumptions nationalAssumptions) {
		nationalAssumptions = toUnwrappedModel(nationalAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nationalAssumptions)) {
				nationalAssumptions = (NationalAssumptions)session.get(NationalAssumptionsImpl.class,
						nationalAssumptions.getPrimaryKeyObj());
			}

			if (nationalAssumptions != null) {
				session.delete(nationalAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nationalAssumptions != null) {
			clearCache(nationalAssumptions);
		}

		return nationalAssumptions;
	}

	@Override
	public NationalAssumptions updateImpl(
		NationalAssumptions nationalAssumptions) {
		nationalAssumptions = toUnwrappedModel(nationalAssumptions);

		boolean isNew = nationalAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nationalAssumptions.isNew()) {
				session.save(nationalAssumptions);

				nationalAssumptions.setNew(false);
			}
			else {
				nationalAssumptions = (NationalAssumptions)session.merge(nationalAssumptions);
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

		entityCache.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsImpl.class, nationalAssumptions.getPrimaryKey(),
			nationalAssumptions, false);

		nationalAssumptions.resetOriginalValues();

		return nationalAssumptions;
	}

	protected NationalAssumptions toUnwrappedModel(
		NationalAssumptions nationalAssumptions) {
		if (nationalAssumptions instanceof NationalAssumptionsImpl) {
			return nationalAssumptions;
		}

		NationalAssumptionsImpl nationalAssumptionsImpl = new NationalAssumptionsImpl();

		nationalAssumptionsImpl.setNew(nationalAssumptions.isNew());
		nationalAssumptionsImpl.setPrimaryKey(nationalAssumptions.getPrimaryKey());

		nationalAssumptionsImpl.setBaselinePeriod(nationalAssumptions.getBaselinePeriod());
		nationalAssumptionsImpl.setFrequency(nationalAssumptions.getFrequency());
		nationalAssumptionsImpl.setStartPeriod(nationalAssumptions.getStartPeriod());
		nationalAssumptionsImpl.setForecastMethodology(nationalAssumptions.getForecastMethodology());
		nationalAssumptionsImpl.setPriceType(nationalAssumptions.getPriceType());
		nationalAssumptionsImpl.setEndPeriod(nationalAssumptions.getEndPeriod());
		nationalAssumptionsImpl.setPriceBasis(nationalAssumptions.getPriceBasis());
		nationalAssumptionsImpl.setNaProjMasterSid(nationalAssumptions.getNaProjMasterSid());
		nationalAssumptionsImpl.setRollingPeriod(nationalAssumptions.getRollingPeriod());
		nationalAssumptionsImpl.setBaselineMethodology(nationalAssumptions.getBaselineMethodology());
		nationalAssumptionsImpl.setGrowthRate(nationalAssumptions.getGrowthRate());

		return nationalAssumptionsImpl;
	}

	/**
	 * Returns the national assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions
	 * @return the national assumptions
	 * @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNationalAssumptionsException {
		NationalAssumptions nationalAssumptions = fetchByPrimaryKey(primaryKey);

		if (nationalAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNationalAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nationalAssumptions;
	}

	/**
	 * Returns the national assumptions with the primary key or throws a {@link NoSuchNationalAssumptionsException} if it could not be found.
	 *
	 * @param nationalAssumptionsPK the primary key of the national assumptions
	 * @return the national assumptions
	 * @throws NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions findByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK)
		throws NoSuchNationalAssumptionsException {
		return findByPrimaryKey((Serializable)nationalAssumptionsPK);
	}

	/**
	 * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions
	 * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NationalAssumptions nationalAssumptions = (NationalAssumptions)serializable;

		if (nationalAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				nationalAssumptions = (NationalAssumptions)session.get(NationalAssumptionsImpl.class,
						primaryKey);

				if (nationalAssumptions != null) {
					cacheResult(nationalAssumptions);
				}
				else {
					entityCache.putResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NationalAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					NationalAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nationalAssumptions;
	}

	/**
	 * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nationalAssumptionsPK the primary key of the national assumptions
	 * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
	 */
	@Override
	public NationalAssumptions fetchByPrimaryKey(
		NationalAssumptionsPK nationalAssumptionsPK) {
		return fetchByPrimaryKey((Serializable)nationalAssumptionsPK);
	}

	@Override
	public Map<Serializable, NationalAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NationalAssumptions> map = new HashMap<Serializable, NationalAssumptions>();

		for (Serializable primaryKey : primaryKeys) {
			NationalAssumptions nationalAssumptions = fetchByPrimaryKey(primaryKey);

			if (nationalAssumptions != null) {
				map.put(primaryKey, nationalAssumptions);
			}
		}

		return map;
	}

	/**
	 * Returns all the national assumptionses.
	 *
	 * @return the national assumptionses
	 */
	@Override
	public List<NationalAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptionses
	 * @param end the upper bound of the range of national assumptionses (not inclusive)
	 * @return the range of national assumptionses
	 */
	@Override
	public List<NationalAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptionses
	 * @param end the upper bound of the range of national assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of national assumptionses
	 */
	@Override
	public List<NationalAssumptions> findAll(int start, int end,
		OrderByComparator<NationalAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the national assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptionses
	 * @param end the upper bound of the range of national assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of national assumptionses
	 */
	@Override
	public List<NationalAssumptions> findAll(int start, int end,
		OrderByComparator<NationalAssumptions> orderByComparator,
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

		List<NationalAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<NationalAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NATIONALASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NATIONALASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(NationalAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NationalAssumptions>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NationalAssumptions>)QueryUtil.list(q,
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
	 * Removes all the national assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NationalAssumptions nationalAssumptions : findAll()) {
			remove(nationalAssumptions);
		}
	}

	/**
	 * Returns the number of national assumptionses.
	 *
	 * @return the number of national assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONS);

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
		return NationalAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the national assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NationalAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NATIONALASSUMPTIONS = "SELECT nationalAssumptions FROM NationalAssumptions nationalAssumptions";
	private static final String _SQL_COUNT_NATIONALASSUMPTIONS = "SELECT COUNT(nationalAssumptions) FROM NationalAssumptions nationalAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NationalAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"baselinePeriod", "frequency", "startPeriod",
				"forecastMethodology", "priceType", "endPeriod", "priceBasis",
				"naProjMasterSid", "rollingPeriod", "baselineMethodology",
				"growthRate"
			});
}