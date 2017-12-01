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

import com.stpl.app.exception.NoSuchStNmPpaProjectionException;
import com.stpl.app.model.StNmPpaProjection;
import com.stpl.app.model.impl.StNmPpaProjectionImpl;
import com.stpl.app.model.impl.StNmPpaProjectionModelImpl;
import com.stpl.app.service.persistence.StNmPpaProjectionPK;
import com.stpl.app.service.persistence.StNmPpaProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionPersistence
 * @see com.stpl.app.service.persistence.StNmPpaProjectionUtil
 * @generated
 */
@ProviderType
public class StNmPpaProjectionPersistenceImpl extends BasePersistenceImpl<StNmPpaProjection>
	implements StNmPpaProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmPpaProjectionUtil} to access the st nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmPpaProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
			StNmPpaProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
			StNmPpaProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmPpaProjectionPersistenceImpl() {
		setModelClass(StNmPpaProjection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("priceCap", "PRICE_CAP");
			dbColumnNames.put("projectionDiscountUnits",
				"PROJECTION_DISCOUNT_UNITS");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("projectionDiscountDollar",
				"PROJECTION_DISCOUNT_DOLLAR");
			dbColumnNames.put("reset", "RESET");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");
			dbColumnNames.put("projectionMap", "PROJECTION_MAP");
			dbColumnNames.put("resetPriceCap", "RESET_PRICE_CAP");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st nm ppa projection in the entity cache if it is enabled.
	 *
	 * @param stNmPpaProjection the st nm ppa projection
	 */
	@Override
	public void cacheResult(StNmPpaProjection stNmPpaProjection) {
		entityCache.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey(),
			stNmPpaProjection);

		stNmPpaProjection.resetOriginalValues();
	}

	/**
	 * Caches the st nm ppa projections in the entity cache if it is enabled.
	 *
	 * @param stNmPpaProjections the st nm ppa projections
	 */
	@Override
	public void cacheResult(List<StNmPpaProjection> stNmPpaProjections) {
		for (StNmPpaProjection stNmPpaProjection : stNmPpaProjections) {
			if (entityCache.getResult(
						StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
						StNmPpaProjectionImpl.class,
						stNmPpaProjection.getPrimaryKey()) == null) {
				cacheResult(stNmPpaProjection);
			}
			else {
				stNmPpaProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm ppa projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmPpaProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm ppa projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmPpaProjection stNmPpaProjection) {
		entityCache.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmPpaProjection> stNmPpaProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmPpaProjection stNmPpaProjection : stNmPpaProjections) {
			entityCache.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
				StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm ppa projection with the primary key. Does not add the st nm ppa projection to the database.
	 *
	 * @param stNmPpaProjectionPK the primary key for the new st nm ppa projection
	 * @return the new st nm ppa projection
	 */
	@Override
	public StNmPpaProjection create(StNmPpaProjectionPK stNmPpaProjectionPK) {
		StNmPpaProjection stNmPpaProjection = new StNmPpaProjectionImpl();

		stNmPpaProjection.setNew(true);
		stNmPpaProjection.setPrimaryKey(stNmPpaProjectionPK);

		return stNmPpaProjection;
	}

	/**
	 * Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	 * @return the st nm ppa projection that was removed
	 * @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection remove(StNmPpaProjectionPK stNmPpaProjectionPK)
		throws NoSuchStNmPpaProjectionException {
		return remove((Serializable)stNmPpaProjectionPK);
	}

	/**
	 * Removes the st nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection
	 * @return the st nm ppa projection that was removed
	 * @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection remove(Serializable primaryKey)
		throws NoSuchStNmPpaProjectionException {
		Session session = null;

		try {
			session = openSession();

			StNmPpaProjection stNmPpaProjection = (StNmPpaProjection)session.get(StNmPpaProjectionImpl.class,
					primaryKey);

			if (stNmPpaProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmPpaProjection);
		}
		catch (NoSuchStNmPpaProjectionException nsee) {
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
	protected StNmPpaProjection removeImpl(StNmPpaProjection stNmPpaProjection) {
		stNmPpaProjection = toUnwrappedModel(stNmPpaProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmPpaProjection)) {
				stNmPpaProjection = (StNmPpaProjection)session.get(StNmPpaProjectionImpl.class,
						stNmPpaProjection.getPrimaryKeyObj());
			}

			if (stNmPpaProjection != null) {
				session.delete(stNmPpaProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmPpaProjection != null) {
			clearCache(stNmPpaProjection);
		}

		return stNmPpaProjection;
	}

	@Override
	public StNmPpaProjection updateImpl(StNmPpaProjection stNmPpaProjection) {
		stNmPpaProjection = toUnwrappedModel(stNmPpaProjection);

		boolean isNew = stNmPpaProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmPpaProjection.isNew()) {
				session.save(stNmPpaProjection);

				stNmPpaProjection.setNew(false);
			}
			else {
				stNmPpaProjection = (StNmPpaProjection)session.merge(stNmPpaProjection);
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

		entityCache.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionImpl.class, stNmPpaProjection.getPrimaryKey(),
			stNmPpaProjection, false);

		stNmPpaProjection.resetOriginalValues();

		return stNmPpaProjection;
	}

	protected StNmPpaProjection toUnwrappedModel(
		StNmPpaProjection stNmPpaProjection) {
		if (stNmPpaProjection instanceof StNmPpaProjectionImpl) {
			return stNmPpaProjection;
		}

		StNmPpaProjectionImpl stNmPpaProjectionImpl = new StNmPpaProjectionImpl();

		stNmPpaProjectionImpl.setNew(stNmPpaProjection.isNew());
		stNmPpaProjectionImpl.setPrimaryKey(stNmPpaProjection.getPrimaryKey());

		stNmPpaProjectionImpl.setLastModifiedDate(stNmPpaProjection.getLastModifiedDate());
		stNmPpaProjectionImpl.setPeriodSid(stNmPpaProjection.getPeriodSid());
		stNmPpaProjectionImpl.setProjectionRate(stNmPpaProjection.getProjectionRate());
		stNmPpaProjectionImpl.setProjectionDetailsSid(stNmPpaProjection.getProjectionDetailsSid());
		stNmPpaProjectionImpl.setUserId(stNmPpaProjection.getUserId());
		stNmPpaProjectionImpl.setPriceCap(stNmPpaProjection.getPriceCap());
		stNmPpaProjectionImpl.setProjectionDiscountUnits(stNmPpaProjection.getProjectionDiscountUnits());
		stNmPpaProjectionImpl.setSessionId(stNmPpaProjection.getSessionId());
		stNmPpaProjectionImpl.setProjectionDiscountDollar(stNmPpaProjection.getProjectionDiscountDollar());
		stNmPpaProjectionImpl.setReset(stNmPpaProjection.isReset());
		stNmPpaProjectionImpl.setProjectionSales(stNmPpaProjection.getProjectionSales());
		stNmPpaProjectionImpl.setProjectionMap(stNmPpaProjection.getProjectionMap());
		stNmPpaProjectionImpl.setResetPriceCap(stNmPpaProjection.isResetPriceCap());

		return stNmPpaProjectionImpl;
	}

	/**
	 * Returns the st nm ppa projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection
	 * @return the st nm ppa projection
	 * @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmPpaProjectionException {
		StNmPpaProjection stNmPpaProjection = fetchByPrimaryKey(primaryKey);

		if (stNmPpaProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmPpaProjection;
	}

	/**
	 * Returns the st nm ppa projection with the primary key or throws a {@link NoSuchStNmPpaProjectionException} if it could not be found.
	 *
	 * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	 * @return the st nm ppa projection
	 * @throws NoSuchStNmPpaProjectionException if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection findByPrimaryKey(
		StNmPpaProjectionPK stNmPpaProjectionPK)
		throws NoSuchStNmPpaProjectionException {
		return findByPrimaryKey((Serializable)stNmPpaProjectionPK);
	}

	/**
	 * Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection
	 * @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
				StNmPpaProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmPpaProjection stNmPpaProjection = (StNmPpaProjection)serializable;

		if (stNmPpaProjection == null) {
			Session session = null;

			try {
				session = openSession();

				stNmPpaProjection = (StNmPpaProjection)session.get(StNmPpaProjectionImpl.class,
						primaryKey);

				if (stNmPpaProjection != null) {
					cacheResult(stNmPpaProjection);
				}
				else {
					entityCache.putResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
						StNmPpaProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
					StNmPpaProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmPpaProjection;
	}

	/**
	 * Returns the st nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmPpaProjectionPK the primary key of the st nm ppa projection
	 * @return the st nm ppa projection, or <code>null</code> if a st nm ppa projection with the primary key could not be found
	 */
	@Override
	public StNmPpaProjection fetchByPrimaryKey(
		StNmPpaProjectionPK stNmPpaProjectionPK) {
		return fetchByPrimaryKey((Serializable)stNmPpaProjectionPK);
	}

	@Override
	public Map<Serializable, StNmPpaProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmPpaProjection> map = new HashMap<Serializable, StNmPpaProjection>();

		for (Serializable primaryKey : primaryKeys) {
			StNmPpaProjection stNmPpaProjection = fetchByPrimaryKey(primaryKey);

			if (stNmPpaProjection != null) {
				map.put(primaryKey, stNmPpaProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm ppa projections.
	 *
	 * @return the st nm ppa projections
	 */
	@Override
	public List<StNmPpaProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projections
	 * @param end the upper bound of the range of st nm ppa projections (not inclusive)
	 * @return the range of st nm ppa projections
	 */
	@Override
	public List<StNmPpaProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projections
	 * @param end the upper bound of the range of st nm ppa projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm ppa projections
	 */
	@Override
	public List<StNmPpaProjection> findAll(int start, int end,
		OrderByComparator<StNmPpaProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projections
	 * @param end the upper bound of the range of st nm ppa projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm ppa projections
	 */
	@Override
	public List<StNmPpaProjection> findAll(int start, int end,
		OrderByComparator<StNmPpaProjection> orderByComparator,
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

		List<StNmPpaProjection> list = null;

		if (retrieveFromCache) {
			list = (List<StNmPpaProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMPPAPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMPPAPROJECTION;

				if (pagination) {
					sql = sql.concat(StNmPpaProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmPpaProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmPpaProjection>)QueryUtil.list(q,
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
	 * Removes all the st nm ppa projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmPpaProjection stNmPpaProjection : findAll()) {
			remove(stNmPpaProjection);
		}
	}

	/**
	 * Returns the number of st nm ppa projections.
	 *
	 * @return the number of st nm ppa projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMPPAPROJECTION);

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
		return StNmPpaProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm ppa projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmPpaProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMPPAPROJECTION = "SELECT stNmPpaProjection FROM StNmPpaProjection stNmPpaProjection";
	private static final String _SQL_COUNT_STNMPPAPROJECTION = "SELECT COUNT(stNmPpaProjection) FROM StNmPpaProjection stNmPpaProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmPpaProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmPpaProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmPpaProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "periodSid", "projectionRate",
				"projectionDetailsSid", "userId", "priceCap",
				"projectionDiscountUnits", "sessionId",
				"projectionDiscountDollar", "reset", "projectionSales",
				"projectionMap", "resetPriceCap"
			});
}