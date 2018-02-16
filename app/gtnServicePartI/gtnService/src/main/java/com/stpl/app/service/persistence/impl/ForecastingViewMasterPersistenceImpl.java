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

import com.stpl.app.exception.NoSuchForecastingViewMasterException;
import com.stpl.app.model.ForecastingViewMaster;
import com.stpl.app.model.impl.ForecastingViewMasterImpl;
import com.stpl.app.model.impl.ForecastingViewMasterModelImpl;
import com.stpl.app.service.persistence.ForecastingViewMasterPersistence;

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
 * The persistence implementation for the forecasting view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingViewMasterPersistence
 * @see com.stpl.app.service.persistence.ForecastingViewMasterUtil
 * @generated
 */
@ProviderType
public class ForecastingViewMasterPersistenceImpl extends BasePersistenceImpl<ForecastingViewMaster>
	implements ForecastingViewMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ForecastingViewMasterUtil} to access the forecasting view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ForecastingViewMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED,
			ForecastingViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED,
			ForecastingViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ForecastingViewMasterPersistenceImpl() {
		setModelClass(ForecastingViewMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("viewType", "VIEW_TYPE");
			dbColumnNames.put("viewId", "VIEW_ID");
			dbColumnNames.put("projectionId", "PROJECTION_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("viewName", "VIEW_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the forecasting view master in the entity cache if it is enabled.
	 *
	 * @param forecastingViewMaster the forecasting view master
	 */
	@Override
	public void cacheResult(ForecastingViewMaster forecastingViewMaster) {
		entityCache.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterImpl.class,
			forecastingViewMaster.getPrimaryKey(), forecastingViewMaster);

		forecastingViewMaster.resetOriginalValues();
	}

	/**
	 * Caches the forecasting view masters in the entity cache if it is enabled.
	 *
	 * @param forecastingViewMasters the forecasting view masters
	 */
	@Override
	public void cacheResult(List<ForecastingViewMaster> forecastingViewMasters) {
		for (ForecastingViewMaster forecastingViewMaster : forecastingViewMasters) {
			if (entityCache.getResult(
						ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						ForecastingViewMasterImpl.class,
						forecastingViewMaster.getPrimaryKey()) == null) {
				cacheResult(forecastingViewMaster);
			}
			else {
				forecastingViewMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all forecasting view masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ForecastingViewMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the forecasting view master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ForecastingViewMaster forecastingViewMaster) {
		entityCache.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterImpl.class,
			forecastingViewMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ForecastingViewMaster> forecastingViewMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ForecastingViewMaster forecastingViewMaster : forecastingViewMasters) {
			entityCache.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				ForecastingViewMasterImpl.class,
				forecastingViewMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new forecasting view master with the primary key. Does not add the forecasting view master to the database.
	 *
	 * @param viewId the primary key for the new forecasting view master
	 * @return the new forecasting view master
	 */
	@Override
	public ForecastingViewMaster create(int viewId) {
		ForecastingViewMaster forecastingViewMaster = new ForecastingViewMasterImpl();

		forecastingViewMaster.setNew(true);
		forecastingViewMaster.setPrimaryKey(viewId);

		return forecastingViewMaster;
	}

	/**
	 * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param viewId the primary key of the forecasting view master
	 * @return the forecasting view master that was removed
	 * @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster remove(int viewId)
		throws NoSuchForecastingViewMasterException {
		return remove((Serializable)viewId);
	}

	/**
	 * Removes the forecasting view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the forecasting view master
	 * @return the forecasting view master that was removed
	 * @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster remove(Serializable primaryKey)
		throws NoSuchForecastingViewMasterException {
		Session session = null;

		try {
			session = openSession();

			ForecastingViewMaster forecastingViewMaster = (ForecastingViewMaster)session.get(ForecastingViewMasterImpl.class,
					primaryKey);

			if (forecastingViewMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchForecastingViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(forecastingViewMaster);
		}
		catch (NoSuchForecastingViewMasterException nsee) {
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
	protected ForecastingViewMaster removeImpl(
		ForecastingViewMaster forecastingViewMaster) {
		forecastingViewMaster = toUnwrappedModel(forecastingViewMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(forecastingViewMaster)) {
				forecastingViewMaster = (ForecastingViewMaster)session.get(ForecastingViewMasterImpl.class,
						forecastingViewMaster.getPrimaryKeyObj());
			}

			if (forecastingViewMaster != null) {
				session.delete(forecastingViewMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (forecastingViewMaster != null) {
			clearCache(forecastingViewMaster);
		}

		return forecastingViewMaster;
	}

	@Override
	public ForecastingViewMaster updateImpl(
		ForecastingViewMaster forecastingViewMaster) {
		forecastingViewMaster = toUnwrappedModel(forecastingViewMaster);

		boolean isNew = forecastingViewMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (forecastingViewMaster.isNew()) {
				session.save(forecastingViewMaster);

				forecastingViewMaster.setNew(false);
			}
			else {
				forecastingViewMaster = (ForecastingViewMaster)session.merge(forecastingViewMaster);
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

		entityCache.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingViewMasterImpl.class,
			forecastingViewMaster.getPrimaryKey(), forecastingViewMaster, false);

		forecastingViewMaster.resetOriginalValues();

		return forecastingViewMaster;
	}

	protected ForecastingViewMaster toUnwrappedModel(
		ForecastingViewMaster forecastingViewMaster) {
		if (forecastingViewMaster instanceof ForecastingViewMasterImpl) {
			return forecastingViewMaster;
		}

		ForecastingViewMasterImpl forecastingViewMasterImpl = new ForecastingViewMasterImpl();

		forecastingViewMasterImpl.setNew(forecastingViewMaster.isNew());
		forecastingViewMasterImpl.setPrimaryKey(forecastingViewMaster.getPrimaryKey());

		forecastingViewMasterImpl.setCreatedDate(forecastingViewMaster.getCreatedDate());
		forecastingViewMasterImpl.setCreatedBy(forecastingViewMaster.getCreatedBy());
		forecastingViewMasterImpl.setViewType(forecastingViewMaster.getViewType());
		forecastingViewMasterImpl.setViewId(forecastingViewMaster.getViewId());
		forecastingViewMasterImpl.setProjectionId(forecastingViewMaster.getProjectionId());
		forecastingViewMasterImpl.setModifiedBy(forecastingViewMaster.getModifiedBy());
		forecastingViewMasterImpl.setModifiedDate(forecastingViewMaster.getModifiedDate());
		forecastingViewMasterImpl.setViewName(forecastingViewMaster.getViewName());

		return forecastingViewMasterImpl;
	}

	/**
	 * Returns the forecasting view master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecasting view master
	 * @return the forecasting view master
	 * @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchForecastingViewMasterException {
		ForecastingViewMaster forecastingViewMaster = fetchByPrimaryKey(primaryKey);

		if (forecastingViewMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchForecastingViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return forecastingViewMaster;
	}

	/**
	 * Returns the forecasting view master with the primary key or throws a {@link NoSuchForecastingViewMasterException} if it could not be found.
	 *
	 * @param viewId the primary key of the forecasting view master
	 * @return the forecasting view master
	 * @throws NoSuchForecastingViewMasterException if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster findByPrimaryKey(int viewId)
		throws NoSuchForecastingViewMasterException {
		return findByPrimaryKey((Serializable)viewId);
	}

	/**
	 * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecasting view master
	 * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				ForecastingViewMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ForecastingViewMaster forecastingViewMaster = (ForecastingViewMaster)serializable;

		if (forecastingViewMaster == null) {
			Session session = null;

			try {
				session = openSession();

				forecastingViewMaster = (ForecastingViewMaster)session.get(ForecastingViewMasterImpl.class,
						primaryKey);

				if (forecastingViewMaster != null) {
					cacheResult(forecastingViewMaster);
				}
				else {
					entityCache.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						ForecastingViewMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingViewMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return forecastingViewMaster;
	}

	/**
	 * Returns the forecasting view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param viewId the primary key of the forecasting view master
	 * @return the forecasting view master, or <code>null</code> if a forecasting view master with the primary key could not be found
	 */
	@Override
	public ForecastingViewMaster fetchByPrimaryKey(int viewId) {
		return fetchByPrimaryKey((Serializable)viewId);
	}

	@Override
	public Map<Serializable, ForecastingViewMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ForecastingViewMaster> map = new HashMap<Serializable, ForecastingViewMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ForecastingViewMaster forecastingViewMaster = fetchByPrimaryKey(primaryKey);

			if (forecastingViewMaster != null) {
				map.put(primaryKey, forecastingViewMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingViewMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ForecastingViewMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORECASTINGVIEWMASTER_WHERE_PKS_IN);

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

			for (ForecastingViewMaster forecastingViewMaster : (List<ForecastingViewMaster>)q.list()) {
				map.put(forecastingViewMaster.getPrimaryKeyObj(),
					forecastingViewMaster);

				cacheResult(forecastingViewMaster);

				uncachedPrimaryKeys.remove(forecastingViewMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ForecastingViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingViewMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the forecasting view masters.
	 *
	 * @return the forecasting view masters
	 */
	@Override
	public List<ForecastingViewMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the forecasting view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting view masters
	 * @param end the upper bound of the range of forecasting view masters (not inclusive)
	 * @return the range of forecasting view masters
	 */
	@Override
	public List<ForecastingViewMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the forecasting view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting view masters
	 * @param end the upper bound of the range of forecasting view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of forecasting view masters
	 */
	@Override
	public List<ForecastingViewMaster> findAll(int start, int end,
		OrderByComparator<ForecastingViewMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the forecasting view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting view masters
	 * @param end the upper bound of the range of forecasting view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of forecasting view masters
	 */
	@Override
	public List<ForecastingViewMaster> findAll(int start, int end,
		OrderByComparator<ForecastingViewMaster> orderByComparator,
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

		List<ForecastingViewMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ForecastingViewMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORECASTINGVIEWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORECASTINGVIEWMASTER;

				if (pagination) {
					sql = sql.concat(ForecastingViewMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ForecastingViewMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ForecastingViewMaster>)QueryUtil.list(q,
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
	 * Removes all the forecasting view masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ForecastingViewMaster forecastingViewMaster : findAll()) {
			remove(forecastingViewMaster);
		}
	}

	/**
	 * Returns the number of forecasting view masters.
	 *
	 * @return the number of forecasting view masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORECASTINGVIEWMASTER);

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
		return ForecastingViewMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the forecasting view master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ForecastingViewMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FORECASTINGVIEWMASTER = "SELECT forecastingViewMaster FROM ForecastingViewMaster forecastingViewMaster";
	private static final String _SQL_SELECT_FORECASTINGVIEWMASTER_WHERE_PKS_IN = "SELECT forecastingViewMaster FROM ForecastingViewMaster forecastingViewMaster WHERE VIEW_ID IN (";
	private static final String _SQL_COUNT_FORECASTINGVIEWMASTER = "SELECT COUNT(forecastingViewMaster) FROM ForecastingViewMaster forecastingViewMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "forecastingViewMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastingViewMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ForecastingViewMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "viewType", "viewId", "projectionId",
				"modifiedBy", "modifiedDate", "viewName"
			});
}