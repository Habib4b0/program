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

import com.stpl.app.exception.NoSuchStNmActualPpaException;
import com.stpl.app.model.StNmActualPpa;
import com.stpl.app.model.impl.StNmActualPpaImpl;
import com.stpl.app.model.impl.StNmActualPpaModelImpl;
import com.stpl.app.service.persistence.StNmActualPpaPK;
import com.stpl.app.service.persistence.StNmActualPpaPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm actual ppa service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualPpaPersistence
 * @see com.stpl.app.service.persistence.StNmActualPpaUtil
 * @generated
 */
@ProviderType
public class StNmActualPpaPersistenceImpl extends BasePersistenceImpl<StNmActualPpa>
	implements StNmActualPpaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmActualPpaUtil} to access the st nm actual ppa persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmActualPpaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaModelImpl.FINDER_CACHE_ENABLED,
			StNmActualPpaImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaModelImpl.FINDER_CACHE_ENABLED,
			StNmActualPpaImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmActualPpaPersistenceImpl() {
		setModelClass(StNmActualPpa.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("actualProjDiscountDollar",
				"ACTUAL_PROJ_DISCOUNT_DOLLAR");
			dbColumnNames.put("actualProjectionSales", "ACTUAL_PROJECTION_SALES");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("actualProjectionRate", "ACTUAL_PROJECTION_RATE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("actualProjDiscountUnits",
				"ACTUAL_PROJ_DISCOUNT_UNITS");
			dbColumnNames.put("actualDiscountDollar", "ACTUAL_DISCOUNT_DOLLAR");
			dbColumnNames.put("actualDiscountUnits", "ACTUAL_DISCOUNT_UNITS");
			dbColumnNames.put("actualSales", "ACTUAL_SALES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st nm actual ppa in the entity cache if it is enabled.
	 *
	 * @param stNmActualPpa the st nm actual ppa
	 */
	@Override
	public void cacheResult(StNmActualPpa stNmActualPpa) {
		entityCache.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey(),
			stNmActualPpa);

		stNmActualPpa.resetOriginalValues();
	}

	/**
	 * Caches the st nm actual ppas in the entity cache if it is enabled.
	 *
	 * @param stNmActualPpas the st nm actual ppas
	 */
	@Override
	public void cacheResult(List<StNmActualPpa> stNmActualPpas) {
		for (StNmActualPpa stNmActualPpa : stNmActualPpas) {
			if (entityCache.getResult(
						StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
						StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey()) == null) {
				cacheResult(stNmActualPpa);
			}
			else {
				stNmActualPpa.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm actual ppas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmActualPpaImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm actual ppa.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmActualPpa stNmActualPpa) {
		entityCache.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmActualPpa> stNmActualPpas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmActualPpa stNmActualPpa : stNmActualPpas) {
			entityCache.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
				StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm actual ppa with the primary key. Does not add the st nm actual ppa to the database.
	 *
	 * @param stNmActualPpaPK the primary key for the new st nm actual ppa
	 * @return the new st nm actual ppa
	 */
	@Override
	public StNmActualPpa create(StNmActualPpaPK stNmActualPpaPK) {
		StNmActualPpa stNmActualPpa = new StNmActualPpaImpl();

		stNmActualPpa.setNew(true);
		stNmActualPpa.setPrimaryKey(stNmActualPpaPK);

		return stNmActualPpa;
	}

	/**
	 * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmActualPpaPK the primary key of the st nm actual ppa
	 * @return the st nm actual ppa that was removed
	 * @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa remove(StNmActualPpaPK stNmActualPpaPK)
		throws NoSuchStNmActualPpaException {
		return remove((Serializable)stNmActualPpaPK);
	}

	/**
	 * Removes the st nm actual ppa with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm actual ppa
	 * @return the st nm actual ppa that was removed
	 * @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa remove(Serializable primaryKey)
		throws NoSuchStNmActualPpaException {
		Session session = null;

		try {
			session = openSession();

			StNmActualPpa stNmActualPpa = (StNmActualPpa)session.get(StNmActualPpaImpl.class,
					primaryKey);

			if (stNmActualPpa == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmActualPpa);
		}
		catch (NoSuchStNmActualPpaException nsee) {
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
	protected StNmActualPpa removeImpl(StNmActualPpa stNmActualPpa) {
		stNmActualPpa = toUnwrappedModel(stNmActualPpa);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmActualPpa)) {
				stNmActualPpa = (StNmActualPpa)session.get(StNmActualPpaImpl.class,
						stNmActualPpa.getPrimaryKeyObj());
			}

			if (stNmActualPpa != null) {
				session.delete(stNmActualPpa);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmActualPpa != null) {
			clearCache(stNmActualPpa);
		}

		return stNmActualPpa;
	}

	@Override
	public StNmActualPpa updateImpl(StNmActualPpa stNmActualPpa) {
		stNmActualPpa = toUnwrappedModel(stNmActualPpa);

		boolean isNew = stNmActualPpa.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmActualPpa.isNew()) {
				session.save(stNmActualPpa);

				stNmActualPpa.setNew(false);
			}
			else {
				stNmActualPpa = (StNmActualPpa)session.merge(stNmActualPpa);
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

		entityCache.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualPpaImpl.class, stNmActualPpa.getPrimaryKey(),
			stNmActualPpa, false);

		stNmActualPpa.resetOriginalValues();

		return stNmActualPpa;
	}

	protected StNmActualPpa toUnwrappedModel(StNmActualPpa stNmActualPpa) {
		if (stNmActualPpa instanceof StNmActualPpaImpl) {
			return stNmActualPpa;
		}

		StNmActualPpaImpl stNmActualPpaImpl = new StNmActualPpaImpl();

		stNmActualPpaImpl.setNew(stNmActualPpa.isNew());
		stNmActualPpaImpl.setPrimaryKey(stNmActualPpa.getPrimaryKey());

		stNmActualPpaImpl.setLastModifiedDate(stNmActualPpa.getLastModifiedDate());
		stNmActualPpaImpl.setActualRate(stNmActualPpa.getActualRate());
		stNmActualPpaImpl.setPeriodSid(stNmActualPpa.getPeriodSid());
		stNmActualPpaImpl.setActualProjDiscountDollar(stNmActualPpa.getActualProjDiscountDollar());
		stNmActualPpaImpl.setActualProjectionSales(stNmActualPpa.getActualProjectionSales());
		stNmActualPpaImpl.setProjectionDetailsSid(stNmActualPpa.getProjectionDetailsSid());
		stNmActualPpaImpl.setUserId(stNmActualPpa.getUserId());
		stNmActualPpaImpl.setActualProjectionRate(stNmActualPpa.getActualProjectionRate());
		stNmActualPpaImpl.setSessionId(stNmActualPpa.getSessionId());
		stNmActualPpaImpl.setActualProjDiscountUnits(stNmActualPpa.getActualProjDiscountUnits());
		stNmActualPpaImpl.setActualDiscountDollar(stNmActualPpa.getActualDiscountDollar());
		stNmActualPpaImpl.setActualDiscountUnits(stNmActualPpa.getActualDiscountUnits());
		stNmActualPpaImpl.setActualSales(stNmActualPpa.getActualSales());

		return stNmActualPpaImpl;
	}

	/**
	 * Returns the st nm actual ppa with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm actual ppa
	 * @return the st nm actual ppa
	 * @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmActualPpaException {
		StNmActualPpa stNmActualPpa = fetchByPrimaryKey(primaryKey);

		if (stNmActualPpa == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmActualPpaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmActualPpa;
	}

	/**
	 * Returns the st nm actual ppa with the primary key or throws a {@link NoSuchStNmActualPpaException} if it could not be found.
	 *
	 * @param stNmActualPpaPK the primary key of the st nm actual ppa
	 * @return the st nm actual ppa
	 * @throws NoSuchStNmActualPpaException if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa findByPrimaryKey(StNmActualPpaPK stNmActualPpaPK)
		throws NoSuchStNmActualPpaException {
		return findByPrimaryKey((Serializable)stNmActualPpaPK);
	}

	/**
	 * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm actual ppa
	 * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
				StNmActualPpaImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmActualPpa stNmActualPpa = (StNmActualPpa)serializable;

		if (stNmActualPpa == null) {
			Session session = null;

			try {
				session = openSession();

				stNmActualPpa = (StNmActualPpa)session.get(StNmActualPpaImpl.class,
						primaryKey);

				if (stNmActualPpa != null) {
					cacheResult(stNmActualPpa);
				}
				else {
					entityCache.putResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
						StNmActualPpaImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmActualPpaModelImpl.ENTITY_CACHE_ENABLED,
					StNmActualPpaImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmActualPpa;
	}

	/**
	 * Returns the st nm actual ppa with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmActualPpaPK the primary key of the st nm actual ppa
	 * @return the st nm actual ppa, or <code>null</code> if a st nm actual ppa with the primary key could not be found
	 */
	@Override
	public StNmActualPpa fetchByPrimaryKey(StNmActualPpaPK stNmActualPpaPK) {
		return fetchByPrimaryKey((Serializable)stNmActualPpaPK);
	}

	@Override
	public Map<Serializable, StNmActualPpa> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmActualPpa> map = new HashMap<Serializable, StNmActualPpa>();

		for (Serializable primaryKey : primaryKeys) {
			StNmActualPpa stNmActualPpa = fetchByPrimaryKey(primaryKey);

			if (stNmActualPpa != null) {
				map.put(primaryKey, stNmActualPpa);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm actual ppas.
	 *
	 * @return the st nm actual ppas
	 */
	@Override
	public List<StNmActualPpa> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual ppas
	 * @param end the upper bound of the range of st nm actual ppas (not inclusive)
	 * @return the range of st nm actual ppas
	 */
	@Override
	public List<StNmActualPpa> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual ppas
	 * @param end the upper bound of the range of st nm actual ppas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm actual ppas
	 */
	@Override
	public List<StNmActualPpa> findAll(int start, int end,
		OrderByComparator<StNmActualPpa> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm actual ppas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualPpaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual ppas
	 * @param end the upper bound of the range of st nm actual ppas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm actual ppas
	 */
	@Override
	public List<StNmActualPpa> findAll(int start, int end,
		OrderByComparator<StNmActualPpa> orderByComparator,
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

		List<StNmActualPpa> list = null;

		if (retrieveFromCache) {
			list = (List<StNmActualPpa>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMACTUALPPA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMACTUALPPA;

				if (pagination) {
					sql = sql.concat(StNmActualPpaModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmActualPpa>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmActualPpa>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the st nm actual ppas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmActualPpa stNmActualPpa : findAll()) {
			remove(stNmActualPpa);
		}
	}

	/**
	 * Returns the number of st nm actual ppas.
	 *
	 * @return the number of st nm actual ppas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMACTUALPPA);

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
		return StNmActualPpaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm actual ppa persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmActualPpaImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMACTUALPPA = "SELECT stNmActualPpa FROM StNmActualPpa stNmActualPpa";
	private static final String _SQL_COUNT_STNMACTUALPPA = "SELECT COUNT(stNmActualPpa) FROM StNmActualPpa stNmActualPpa";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmActualPpa.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmActualPpa exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmActualPpaPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "actualRate", "periodSid",
				"actualProjDiscountDollar", "actualProjectionSales",
				"projectionDetailsSid", "userId", "actualProjectionRate",
				"sessionId", "actualProjDiscountUnits", "actualDiscountDollar",
				"actualDiscountUnits", "actualSales"
			});
}