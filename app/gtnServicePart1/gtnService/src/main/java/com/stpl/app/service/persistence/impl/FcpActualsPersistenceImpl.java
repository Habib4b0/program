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

import com.stpl.app.exception.NoSuchFcpActualsException;
import com.stpl.app.model.FcpActuals;
import com.stpl.app.model.impl.FcpActualsImpl;
import com.stpl.app.model.impl.FcpActualsModelImpl;
import com.stpl.app.service.persistence.FcpActualsPK;
import com.stpl.app.service.persistence.FcpActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the fcp actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpActualsPersistence
 * @see com.stpl.app.service.persistence.FcpActualsUtil
 * @generated
 */
@ProviderType
public class FcpActualsPersistenceImpl extends BasePersistenceImpl<FcpActuals>
	implements FcpActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FcpActualsUtil} to access the fcp actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FcpActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsModelImpl.FINDER_CACHE_ENABLED, FcpActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsModelImpl.FINDER_CACHE_ENABLED, FcpActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public FcpActualsPersistenceImpl() {
		setModelClass(FcpActuals.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("actualPrice", "ACTUAL_PRICE");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the fcp actuals in the entity cache if it is enabled.
	 *
	 * @param fcpActuals the fcp actuals
	 */
	@Override
	public void cacheResult(FcpActuals fcpActuals) {
		entityCache.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsImpl.class, fcpActuals.getPrimaryKey(), fcpActuals);

		fcpActuals.resetOriginalValues();
	}

	/**
	 * Caches the fcp actualses in the entity cache if it is enabled.
	 *
	 * @param fcpActualses the fcp actualses
	 */
	@Override
	public void cacheResult(List<FcpActuals> fcpActualses) {
		for (FcpActuals fcpActuals : fcpActualses) {
			if (entityCache.getResult(
						FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
						FcpActualsImpl.class, fcpActuals.getPrimaryKey()) == null) {
				cacheResult(fcpActuals);
			}
			else {
				fcpActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all fcp actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FcpActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the fcp actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FcpActuals fcpActuals) {
		entityCache.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsImpl.class, fcpActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FcpActuals> fcpActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FcpActuals fcpActuals : fcpActualses) {
			entityCache.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
				FcpActualsImpl.class, fcpActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new fcp actuals with the primary key. Does not add the fcp actuals to the database.
	 *
	 * @param fcpActualsPK the primary key for the new fcp actuals
	 * @return the new fcp actuals
	 */
	@Override
	public FcpActuals create(FcpActualsPK fcpActualsPK) {
		FcpActuals fcpActuals = new FcpActualsImpl();

		fcpActuals.setNew(true);
		fcpActuals.setPrimaryKey(fcpActualsPK);

		return fcpActuals;
	}

	/**
	 * Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fcpActualsPK the primary key of the fcp actuals
	 * @return the fcp actuals that was removed
	 * @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals remove(FcpActualsPK fcpActualsPK)
		throws NoSuchFcpActualsException {
		return remove((Serializable)fcpActualsPK);
	}

	/**
	 * Removes the fcp actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fcp actuals
	 * @return the fcp actuals that was removed
	 * @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals remove(Serializable primaryKey)
		throws NoSuchFcpActualsException {
		Session session = null;

		try {
			session = openSession();

			FcpActuals fcpActuals = (FcpActuals)session.get(FcpActualsImpl.class,
					primaryKey);

			if (fcpActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFcpActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fcpActuals);
		}
		catch (NoSuchFcpActualsException nsee) {
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
	protected FcpActuals removeImpl(FcpActuals fcpActuals) {
		fcpActuals = toUnwrappedModel(fcpActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fcpActuals)) {
				fcpActuals = (FcpActuals)session.get(FcpActualsImpl.class,
						fcpActuals.getPrimaryKeyObj());
			}

			if (fcpActuals != null) {
				session.delete(fcpActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fcpActuals != null) {
			clearCache(fcpActuals);
		}

		return fcpActuals;
	}

	@Override
	public FcpActuals updateImpl(FcpActuals fcpActuals) {
		fcpActuals = toUnwrappedModel(fcpActuals);

		boolean isNew = fcpActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (fcpActuals.isNew()) {
				session.save(fcpActuals);

				fcpActuals.setNew(false);
			}
			else {
				fcpActuals = (FcpActuals)session.merge(fcpActuals);
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

		entityCache.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
			FcpActualsImpl.class, fcpActuals.getPrimaryKey(), fcpActuals, false);

		fcpActuals.resetOriginalValues();

		return fcpActuals;
	}

	protected FcpActuals toUnwrappedModel(FcpActuals fcpActuals) {
		if (fcpActuals instanceof FcpActualsImpl) {
			return fcpActuals;
		}

		FcpActualsImpl fcpActualsImpl = new FcpActualsImpl();

		fcpActualsImpl.setNew(fcpActuals.isNew());
		fcpActualsImpl.setPrimaryKey(fcpActuals.getPrimaryKey());

		fcpActualsImpl.setPeriodSid(fcpActuals.getPeriodSid());
		fcpActualsImpl.setPriceType(fcpActuals.getPriceType());
		fcpActualsImpl.setActualPrice(fcpActuals.getActualPrice());
		fcpActualsImpl.setNotes(fcpActuals.getNotes());
		fcpActualsImpl.setNaProjDetailsSid(fcpActuals.getNaProjDetailsSid());

		return fcpActualsImpl;
	}

	/**
	 * Returns the fcp actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the fcp actuals
	 * @return the fcp actuals
	 * @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFcpActualsException {
		FcpActuals fcpActuals = fetchByPrimaryKey(primaryKey);

		if (fcpActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFcpActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fcpActuals;
	}

	/**
	 * Returns the fcp actuals with the primary key or throws a {@link NoSuchFcpActualsException} if it could not be found.
	 *
	 * @param fcpActualsPK the primary key of the fcp actuals
	 * @return the fcp actuals
	 * @throws NoSuchFcpActualsException if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals findByPrimaryKey(FcpActualsPK fcpActualsPK)
		throws NoSuchFcpActualsException {
		return findByPrimaryKey((Serializable)fcpActualsPK);
	}

	/**
	 * Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fcp actuals
	 * @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
				FcpActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FcpActuals fcpActuals = (FcpActuals)serializable;

		if (fcpActuals == null) {
			Session session = null;

			try {
				session = openSession();

				fcpActuals = (FcpActuals)session.get(FcpActualsImpl.class,
						primaryKey);

				if (fcpActuals != null) {
					cacheResult(fcpActuals);
				}
				else {
					entityCache.putResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
						FcpActualsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FcpActualsModelImpl.ENTITY_CACHE_ENABLED,
					FcpActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fcpActuals;
	}

	/**
	 * Returns the fcp actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fcpActualsPK the primary key of the fcp actuals
	 * @return the fcp actuals, or <code>null</code> if a fcp actuals with the primary key could not be found
	 */
	@Override
	public FcpActuals fetchByPrimaryKey(FcpActualsPK fcpActualsPK) {
		return fetchByPrimaryKey((Serializable)fcpActualsPK);
	}

	@Override
	public Map<Serializable, FcpActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FcpActuals> map = new HashMap<Serializable, FcpActuals>();

		for (Serializable primaryKey : primaryKeys) {
			FcpActuals fcpActuals = fetchByPrimaryKey(primaryKey);

			if (fcpActuals != null) {
				map.put(primaryKey, fcpActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the fcp actualses.
	 *
	 * @return the fcp actualses
	 */
	@Override
	public List<FcpActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fcp actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp actualses
	 * @param end the upper bound of the range of fcp actualses (not inclusive)
	 * @return the range of fcp actualses
	 */
	@Override
	public List<FcpActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fcp actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp actualses
	 * @param end the upper bound of the range of fcp actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fcp actualses
	 */
	@Override
	public List<FcpActuals> findAll(int start, int end,
		OrderByComparator<FcpActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fcp actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp actualses
	 * @param end the upper bound of the range of fcp actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of fcp actualses
	 */
	@Override
	public List<FcpActuals> findAll(int start, int end,
		OrderByComparator<FcpActuals> orderByComparator,
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

		List<FcpActuals> list = null;

		if (retrieveFromCache) {
			list = (List<FcpActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FCPACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FCPACTUALS;

				if (pagination) {
					sql = sql.concat(FcpActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FcpActuals>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FcpActuals>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the fcp actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FcpActuals fcpActuals : findAll()) {
			remove(fcpActuals);
		}
	}

	/**
	 * Returns the number of fcp actualses.
	 *
	 * @return the number of fcp actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FCPACTUALS);

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
		return FcpActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fcp actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FcpActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FCPACTUALS = "SELECT fcpActuals FROM FcpActuals fcpActuals";
	private static final String _SQL_COUNT_FCPACTUALS = "SELECT COUNT(fcpActuals) FROM FcpActuals fcpActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fcpActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FcpActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(FcpActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "priceType", "actualPrice", "notes",
				"naProjDetailsSid"
			});
}