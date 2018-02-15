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

import com.stpl.app.exception.NoSuchStMSupplementalDiscActualsException;
import com.stpl.app.model.StMSupplementalDiscActuals;
import com.stpl.app.model.impl.StMSupplementalDiscActualsImpl;
import com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPK;
import com.stpl.app.service.persistence.StMSupplementalDiscActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st m supplemental disc actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscActualsPersistence
 * @see com.stpl.app.service.persistence.StMSupplementalDiscActualsUtil
 * @generated
 */
@ProviderType
public class StMSupplementalDiscActualsPersistenceImpl
	extends BasePersistenceImpl<StMSupplementalDiscActuals>
	implements StMSupplementalDiscActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StMSupplementalDiscActualsUtil} to access the st m supplemental disc actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StMSupplementalDiscActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public StMSupplementalDiscActualsPersistenceImpl() {
		setModelClass(StMSupplementalDiscActuals.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualSales", "ACTUAL_SALES");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("actualProjectionSales", "ACTUAL_PROJECTION_SALES");
			dbColumnNames.put("actualProjectionRate", "ACTUAL_PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st m supplemental disc actuals in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscActuals the st m supplemental disc actuals
	 */
	@Override
	public void cacheResult(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		entityCache.putResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsImpl.class,
			stMSupplementalDiscActuals.getPrimaryKey(),
			stMSupplementalDiscActuals);

		stMSupplementalDiscActuals.resetOriginalValues();
	}

	/**
	 * Caches the st m supplemental disc actualses in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscActualses the st m supplemental disc actualses
	 */
	@Override
	public void cacheResult(
		List<StMSupplementalDiscActuals> stMSupplementalDiscActualses) {
		for (StMSupplementalDiscActuals stMSupplementalDiscActuals : stMSupplementalDiscActualses) {
			if (entityCache.getResult(
						StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscActualsImpl.class,
						stMSupplementalDiscActuals.getPrimaryKey()) == null) {
				cacheResult(stMSupplementalDiscActuals);
			}
			else {
				stMSupplementalDiscActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st m supplemental disc actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StMSupplementalDiscActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st m supplemental disc actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		entityCache.removeResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsImpl.class,
			stMSupplementalDiscActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StMSupplementalDiscActuals> stMSupplementalDiscActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StMSupplementalDiscActuals stMSupplementalDiscActuals : stMSupplementalDiscActualses) {
			entityCache.removeResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscActualsImpl.class,
				stMSupplementalDiscActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st m supplemental disc actuals with the primary key. Does not add the st m supplemental disc actuals to the database.
	 *
	 * @param stMSupplementalDiscActualsPK the primary key for the new st m supplemental disc actuals
	 * @return the new st m supplemental disc actuals
	 */
	@Override
	public StMSupplementalDiscActuals create(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		StMSupplementalDiscActuals stMSupplementalDiscActuals = new StMSupplementalDiscActualsImpl();

		stMSupplementalDiscActuals.setNew(true);
		stMSupplementalDiscActuals.setPrimaryKey(stMSupplementalDiscActualsPK);

		return stMSupplementalDiscActuals;
	}

	/**
	 * Removes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals that was removed
	 * @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals remove(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws NoSuchStMSupplementalDiscActualsException {
		return remove((Serializable)stMSupplementalDiscActualsPK);
	}

	/**
	 * Removes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals that was removed
	 * @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals remove(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscActualsException {
		Session session = null;

		try {
			session = openSession();

			StMSupplementalDiscActuals stMSupplementalDiscActuals = (StMSupplementalDiscActuals)session.get(StMSupplementalDiscActualsImpl.class,
					primaryKey);

			if (stMSupplementalDiscActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stMSupplementalDiscActuals);
		}
		catch (NoSuchStMSupplementalDiscActualsException nsee) {
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
	protected StMSupplementalDiscActuals removeImpl(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		stMSupplementalDiscActuals = toUnwrappedModel(stMSupplementalDiscActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stMSupplementalDiscActuals)) {
				stMSupplementalDiscActuals = (StMSupplementalDiscActuals)session.get(StMSupplementalDiscActualsImpl.class,
						stMSupplementalDiscActuals.getPrimaryKeyObj());
			}

			if (stMSupplementalDiscActuals != null) {
				session.delete(stMSupplementalDiscActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stMSupplementalDiscActuals != null) {
			clearCache(stMSupplementalDiscActuals);
		}

		return stMSupplementalDiscActuals;
	}

	@Override
	public StMSupplementalDiscActuals updateImpl(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		stMSupplementalDiscActuals = toUnwrappedModel(stMSupplementalDiscActuals);

		boolean isNew = stMSupplementalDiscActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stMSupplementalDiscActuals.isNew()) {
				session.save(stMSupplementalDiscActuals);

				stMSupplementalDiscActuals.setNew(false);
			}
			else {
				stMSupplementalDiscActuals = (StMSupplementalDiscActuals)session.merge(stMSupplementalDiscActuals);
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

		entityCache.putResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscActualsImpl.class,
			stMSupplementalDiscActuals.getPrimaryKey(),
			stMSupplementalDiscActuals, false);

		stMSupplementalDiscActuals.resetOriginalValues();

		return stMSupplementalDiscActuals;
	}

	protected StMSupplementalDiscActuals toUnwrappedModel(
		StMSupplementalDiscActuals stMSupplementalDiscActuals) {
		if (stMSupplementalDiscActuals instanceof StMSupplementalDiscActualsImpl) {
			return stMSupplementalDiscActuals;
		}

		StMSupplementalDiscActualsImpl stMSupplementalDiscActualsImpl = new StMSupplementalDiscActualsImpl();

		stMSupplementalDiscActualsImpl.setNew(stMSupplementalDiscActuals.isNew());
		stMSupplementalDiscActualsImpl.setPrimaryKey(stMSupplementalDiscActuals.getPrimaryKey());

		stMSupplementalDiscActualsImpl.setActualSales(stMSupplementalDiscActuals.getActualSales());
		stMSupplementalDiscActualsImpl.setPeriodSid(stMSupplementalDiscActuals.getPeriodSid());
		stMSupplementalDiscActualsImpl.setActualRate(stMSupplementalDiscActuals.getActualRate());
		stMSupplementalDiscActualsImpl.setUserId(stMSupplementalDiscActuals.getUserId());
		stMSupplementalDiscActualsImpl.setLastModifiedDate(stMSupplementalDiscActuals.getLastModifiedDate());
		stMSupplementalDiscActualsImpl.setActualProjectionSales(stMSupplementalDiscActuals.getActualProjectionSales());
		stMSupplementalDiscActualsImpl.setActualProjectionRate(stMSupplementalDiscActuals.getActualProjectionRate());
		stMSupplementalDiscActualsImpl.setProjectionDetailsSid(stMSupplementalDiscActuals.getProjectionDetailsSid());
		stMSupplementalDiscActualsImpl.setSessionId(stMSupplementalDiscActuals.getSessionId());

		return stMSupplementalDiscActualsImpl;
	}

	/**
	 * Returns the st m supplemental disc actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals
	 * @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscActualsException {
		StMSupplementalDiscActuals stMSupplementalDiscActuals = fetchByPrimaryKey(primaryKey);

		if (stMSupplementalDiscActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stMSupplementalDiscActuals;
	}

	/**
	 * Returns the st m supplemental disc actuals with the primary key or throws a {@link NoSuchStMSupplementalDiscActualsException} if it could not be found.
	 *
	 * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals
	 * @throws NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals findByPrimaryKey(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
		throws NoSuchStMSupplementalDiscActualsException {
		return findByPrimaryKey((Serializable)stMSupplementalDiscActualsPK);
	}

	/**
	 * Returns the st m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals, or <code>null</code> if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StMSupplementalDiscActuals stMSupplementalDiscActuals = (StMSupplementalDiscActuals)serializable;

		if (stMSupplementalDiscActuals == null) {
			Session session = null;

			try {
				session = openSession();

				stMSupplementalDiscActuals = (StMSupplementalDiscActuals)session.get(StMSupplementalDiscActualsImpl.class,
						primaryKey);

				if (stMSupplementalDiscActuals != null) {
					cacheResult(stMSupplementalDiscActuals);
				}
				else {
					entityCache.putResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscActualsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StMSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
					StMSupplementalDiscActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stMSupplementalDiscActuals;
	}

	/**
	 * Returns the st m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
	 * @return the st m supplemental disc actuals, or <code>null</code> if a st m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscActuals fetchByPrimaryKey(
		StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
		return fetchByPrimaryKey((Serializable)stMSupplementalDiscActualsPK);
	}

	@Override
	public Map<Serializable, StMSupplementalDiscActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StMSupplementalDiscActuals> map = new HashMap<Serializable, StMSupplementalDiscActuals>();

		for (Serializable primaryKey : primaryKeys) {
			StMSupplementalDiscActuals stMSupplementalDiscActuals = fetchByPrimaryKey(primaryKey);

			if (stMSupplementalDiscActuals != null) {
				map.put(primaryKey, stMSupplementalDiscActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the st m supplemental disc actualses.
	 *
	 * @return the st m supplemental disc actualses
	 */
	@Override
	public List<StMSupplementalDiscActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc actualses
	 * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	 * @return the range of st m supplemental disc actualses
	 */
	@Override
	public List<StMSupplementalDiscActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc actualses
	 * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st m supplemental disc actualses
	 */
	@Override
	public List<StMSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc actualses
	 * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st m supplemental disc actualses
	 */
	@Override
	public List<StMSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscActuals> orderByComparator,
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

		List<StMSupplementalDiscActuals> list = null;

		if (retrieveFromCache) {
			list = (List<StMSupplementalDiscActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STMSUPPLEMENTALDISCACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STMSUPPLEMENTALDISCACTUALS;

				if (pagination) {
					sql = sql.concat(StMSupplementalDiscActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StMSupplementalDiscActuals>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StMSupplementalDiscActuals>)QueryUtil.list(q,
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
	 * Removes all the st m supplemental disc actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StMSupplementalDiscActuals stMSupplementalDiscActuals : findAll()) {
			remove(stMSupplementalDiscActuals);
		}
	}

	/**
	 * Returns the number of st m supplemental disc actualses.
	 *
	 * @return the number of st m supplemental disc actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STMSUPPLEMENTALDISCACTUALS);

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
		return StMSupplementalDiscActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st m supplemental disc actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StMSupplementalDiscActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STMSUPPLEMENTALDISCACTUALS = "SELECT stMSupplementalDiscActuals FROM StMSupplementalDiscActuals stMSupplementalDiscActuals";
	private static final String _SQL_COUNT_STMSUPPLEMENTALDISCACTUALS = "SELECT COUNT(stMSupplementalDiscActuals) FROM StMSupplementalDiscActuals stMSupplementalDiscActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stMSupplementalDiscActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMSupplementalDiscActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StMSupplementalDiscActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualSales", "periodSid", "actualRate", "userId",
				"lastModifiedDate", "actualProjectionSales",
				"actualProjectionRate", "projectionDetailsSid", "sessionId"
			});
}