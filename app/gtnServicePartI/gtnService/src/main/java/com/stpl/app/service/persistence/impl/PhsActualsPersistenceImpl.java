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

import com.stpl.app.exception.NoSuchPhsActualsException;
import com.stpl.app.model.PhsActuals;
import com.stpl.app.model.impl.PhsActualsImpl;
import com.stpl.app.model.impl.PhsActualsModelImpl;
import com.stpl.app.service.persistence.PhsActualsPK;
import com.stpl.app.service.persistence.PhsActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the phs actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PhsActualsPersistence
 * @see com.stpl.app.service.persistence.PhsActualsUtil
 * @generated
 */
@ProviderType
public class PhsActualsPersistenceImpl extends BasePersistenceImpl<PhsActuals>
	implements PhsActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PhsActualsUtil} to access the phs actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PhsActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsModelImpl.FINDER_CACHE_ENABLED, PhsActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsModelImpl.FINDER_CACHE_ENABLED, PhsActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PhsActualsPersistenceImpl() {
		setModelClass(PhsActuals.class);

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
	 * Caches the phs actuals in the entity cache if it is enabled.
	 *
	 * @param phsActuals the phs actuals
	 */
	@Override
	public void cacheResult(PhsActuals phsActuals) {
		entityCache.putResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsImpl.class, phsActuals.getPrimaryKey(), phsActuals);

		phsActuals.resetOriginalValues();
	}

	/**
	 * Caches the phs actualses in the entity cache if it is enabled.
	 *
	 * @param phsActualses the phs actualses
	 */
	@Override
	public void cacheResult(List<PhsActuals> phsActualses) {
		for (PhsActuals phsActuals : phsActualses) {
			if (entityCache.getResult(
						PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
						PhsActualsImpl.class, phsActuals.getPrimaryKey()) == null) {
				cacheResult(phsActuals);
			}
			else {
				phsActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all phs actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PhsActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the phs actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PhsActuals phsActuals) {
		entityCache.removeResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsImpl.class, phsActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PhsActuals> phsActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PhsActuals phsActuals : phsActualses) {
			entityCache.removeResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
				PhsActualsImpl.class, phsActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new phs actuals with the primary key. Does not add the phs actuals to the database.
	 *
	 * @param phsActualsPK the primary key for the new phs actuals
	 * @return the new phs actuals
	 */
	@Override
	public PhsActuals create(PhsActualsPK phsActualsPK) {
		PhsActuals phsActuals = new PhsActualsImpl();

		phsActuals.setNew(true);
		phsActuals.setPrimaryKey(phsActualsPK);

		return phsActuals;
	}

	/**
	 * Removes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param phsActualsPK the primary key of the phs actuals
	 * @return the phs actuals that was removed
	 * @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals remove(PhsActualsPK phsActualsPK)
		throws NoSuchPhsActualsException {
		return remove((Serializable)phsActualsPK);
	}

	/**
	 * Removes the phs actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the phs actuals
	 * @return the phs actuals that was removed
	 * @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals remove(Serializable primaryKey)
		throws NoSuchPhsActualsException {
		Session session = null;

		try {
			session = openSession();

			PhsActuals phsActuals = (PhsActuals)session.get(PhsActualsImpl.class,
					primaryKey);

			if (phsActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPhsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(phsActuals);
		}
		catch (NoSuchPhsActualsException nsee) {
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
	protected PhsActuals removeImpl(PhsActuals phsActuals) {
		phsActuals = toUnwrappedModel(phsActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(phsActuals)) {
				phsActuals = (PhsActuals)session.get(PhsActualsImpl.class,
						phsActuals.getPrimaryKeyObj());
			}

			if (phsActuals != null) {
				session.delete(phsActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (phsActuals != null) {
			clearCache(phsActuals);
		}

		return phsActuals;
	}

	@Override
	public PhsActuals updateImpl(PhsActuals phsActuals) {
		phsActuals = toUnwrappedModel(phsActuals);

		boolean isNew = phsActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (phsActuals.isNew()) {
				session.save(phsActuals);

				phsActuals.setNew(false);
			}
			else {
				phsActuals = (PhsActuals)session.merge(phsActuals);
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

		entityCache.putResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
			PhsActualsImpl.class, phsActuals.getPrimaryKey(), phsActuals, false);

		phsActuals.resetOriginalValues();

		return phsActuals;
	}

	protected PhsActuals toUnwrappedModel(PhsActuals phsActuals) {
		if (phsActuals instanceof PhsActualsImpl) {
			return phsActuals;
		}

		PhsActualsImpl phsActualsImpl = new PhsActualsImpl();

		phsActualsImpl.setNew(phsActuals.isNew());
		phsActualsImpl.setPrimaryKey(phsActuals.getPrimaryKey());

		phsActualsImpl.setPeriodSid(phsActuals.getPeriodSid());
		phsActualsImpl.setPriceType(phsActuals.getPriceType());
		phsActualsImpl.setActualPrice(phsActuals.getActualPrice());
		phsActualsImpl.setNotes(phsActuals.getNotes());
		phsActualsImpl.setNaProjDetailsSid(phsActuals.getNaProjDetailsSid());

		return phsActualsImpl;
	}

	/**
	 * Returns the phs actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the phs actuals
	 * @return the phs actuals
	 * @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPhsActualsException {
		PhsActuals phsActuals = fetchByPrimaryKey(primaryKey);

		if (phsActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPhsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return phsActuals;
	}

	/**
	 * Returns the phs actuals with the primary key or throws a {@link NoSuchPhsActualsException} if it could not be found.
	 *
	 * @param phsActualsPK the primary key of the phs actuals
	 * @return the phs actuals
	 * @throws NoSuchPhsActualsException if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals findByPrimaryKey(PhsActualsPK phsActualsPK)
		throws NoSuchPhsActualsException {
		return findByPrimaryKey((Serializable)phsActualsPK);
	}

	/**
	 * Returns the phs actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the phs actuals
	 * @return the phs actuals, or <code>null</code> if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
				PhsActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PhsActuals phsActuals = (PhsActuals)serializable;

		if (phsActuals == null) {
			Session session = null;

			try {
				session = openSession();

				phsActuals = (PhsActuals)session.get(PhsActualsImpl.class,
						primaryKey);

				if (phsActuals != null) {
					cacheResult(phsActuals);
				}
				else {
					entityCache.putResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
						PhsActualsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PhsActualsModelImpl.ENTITY_CACHE_ENABLED,
					PhsActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return phsActuals;
	}

	/**
	 * Returns the phs actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param phsActualsPK the primary key of the phs actuals
	 * @return the phs actuals, or <code>null</code> if a phs actuals with the primary key could not be found
	 */
	@Override
	public PhsActuals fetchByPrimaryKey(PhsActualsPK phsActualsPK) {
		return fetchByPrimaryKey((Serializable)phsActualsPK);
	}

	@Override
	public Map<Serializable, PhsActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PhsActuals> map = new HashMap<Serializable, PhsActuals>();

		for (Serializable primaryKey : primaryKeys) {
			PhsActuals phsActuals = fetchByPrimaryKey(primaryKey);

			if (phsActuals != null) {
				map.put(primaryKey, phsActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the phs actualses.
	 *
	 * @return the phs actualses
	 */
	@Override
	public List<PhsActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the phs actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs actualses
	 * @param end the upper bound of the range of phs actualses (not inclusive)
	 * @return the range of phs actualses
	 */
	@Override
	public List<PhsActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the phs actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs actualses
	 * @param end the upper bound of the range of phs actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of phs actualses
	 */
	@Override
	public List<PhsActuals> findAll(int start, int end,
		OrderByComparator<PhsActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the phs actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PhsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of phs actualses
	 * @param end the upper bound of the range of phs actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of phs actualses
	 */
	@Override
	public List<PhsActuals> findAll(int start, int end,
		OrderByComparator<PhsActuals> orderByComparator,
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

		List<PhsActuals> list = null;

		if (retrieveFromCache) {
			list = (List<PhsActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PHSACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PHSACTUALS;

				if (pagination) {
					sql = sql.concat(PhsActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PhsActuals>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PhsActuals>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the phs actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PhsActuals phsActuals : findAll()) {
			remove(phsActuals);
		}
	}

	/**
	 * Returns the number of phs actualses.
	 *
	 * @return the number of phs actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PHSACTUALS);

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
		return PhsActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the phs actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PhsActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PHSACTUALS = "SELECT phsActuals FROM PhsActuals phsActuals";
	private static final String _SQL_COUNT_PHSACTUALS = "SELECT COUNT(phsActuals) FROM PhsActuals phsActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "phsActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PhsActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(PhsActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "priceType", "actualPrice", "notes",
				"naProjDetailsSid"
			});
}