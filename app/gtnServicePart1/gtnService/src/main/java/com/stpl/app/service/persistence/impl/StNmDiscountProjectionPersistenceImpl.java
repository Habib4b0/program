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

import com.stpl.app.exception.NoSuchStNmDiscountProjectionException;
import com.stpl.app.model.StNmDiscountProjection;
import com.stpl.app.model.impl.StNmDiscountProjectionImpl;
import com.stpl.app.model.impl.StNmDiscountProjectionModelImpl;
import com.stpl.app.service.persistence.StNmDiscountProjectionPK;
import com.stpl.app.service.persistence.StNmDiscountProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjectionPersistence
 * @see com.stpl.app.service.persistence.StNmDiscountProjectionUtil
 * @generated
 */
@ProviderType
public class StNmDiscountProjectionPersistenceImpl extends BasePersistenceImpl<StNmDiscountProjection>
	implements StNmDiscountProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmDiscountProjectionUtil} to access the st nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmDiscountProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
			StNmDiscountProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
			StNmDiscountProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmDiscountProjectionPersistenceImpl() {
		setModelClass(StNmDiscountProjection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("adjustmentValue", "ADJUSTMENT_VALUE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("discountRate", "DISCOUNT_RATE");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("adjustmentBasis", "ADJUSTMENT_BASIS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("adjustmentMethodology", "ADJUSTMENT_METHODOLOGY");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
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
	 * Caches the st nm discount projection in the entity cache if it is enabled.
	 *
	 * @param stNmDiscountProjection the st nm discount projection
	 */
	@Override
	public void cacheResult(StNmDiscountProjection stNmDiscountProjection) {
		entityCache.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionImpl.class,
			stNmDiscountProjection.getPrimaryKey(), stNmDiscountProjection);

		stNmDiscountProjection.resetOriginalValues();
	}

	/**
	 * Caches the st nm discount projections in the entity cache if it is enabled.
	 *
	 * @param stNmDiscountProjections the st nm discount projections
	 */
	@Override
	public void cacheResult(
		List<StNmDiscountProjection> stNmDiscountProjections) {
		for (StNmDiscountProjection stNmDiscountProjection : stNmDiscountProjections) {
			if (entityCache.getResult(
						StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
						StNmDiscountProjectionImpl.class,
						stNmDiscountProjection.getPrimaryKey()) == null) {
				cacheResult(stNmDiscountProjection);
			}
			else {
				stNmDiscountProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm discount projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmDiscountProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm discount projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmDiscountProjection stNmDiscountProjection) {
		entityCache.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionImpl.class,
			stNmDiscountProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmDiscountProjection> stNmDiscountProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmDiscountProjection stNmDiscountProjection : stNmDiscountProjections) {
			entityCache.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
				StNmDiscountProjectionImpl.class,
				stNmDiscountProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
	 *
	 * @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
	 * @return the new st nm discount projection
	 */
	@Override
	public StNmDiscountProjection create(
		StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		StNmDiscountProjection stNmDiscountProjection = new StNmDiscountProjectionImpl();

		stNmDiscountProjection.setNew(true);
		stNmDiscountProjection.setPrimaryKey(stNmDiscountProjectionPK);

		return stNmDiscountProjection;
	}

	/**
	 * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	 * @return the st nm discount projection that was removed
	 * @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection remove(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws NoSuchStNmDiscountProjectionException {
		return remove((Serializable)stNmDiscountProjectionPK);
	}

	/**
	 * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm discount projection
	 * @return the st nm discount projection that was removed
	 * @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection remove(Serializable primaryKey)
		throws NoSuchStNmDiscountProjectionException {
		Session session = null;

		try {
			session = openSession();

			StNmDiscountProjection stNmDiscountProjection = (StNmDiscountProjection)session.get(StNmDiscountProjectionImpl.class,
					primaryKey);

			if (stNmDiscountProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmDiscountProjection);
		}
		catch (NoSuchStNmDiscountProjectionException nsee) {
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
	protected StNmDiscountProjection removeImpl(
		StNmDiscountProjection stNmDiscountProjection) {
		stNmDiscountProjection = toUnwrappedModel(stNmDiscountProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmDiscountProjection)) {
				stNmDiscountProjection = (StNmDiscountProjection)session.get(StNmDiscountProjectionImpl.class,
						stNmDiscountProjection.getPrimaryKeyObj());
			}

			if (stNmDiscountProjection != null) {
				session.delete(stNmDiscountProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmDiscountProjection != null) {
			clearCache(stNmDiscountProjection);
		}

		return stNmDiscountProjection;
	}

	@Override
	public StNmDiscountProjection updateImpl(
		StNmDiscountProjection stNmDiscountProjection) {
		stNmDiscountProjection = toUnwrappedModel(stNmDiscountProjection);

		boolean isNew = stNmDiscountProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmDiscountProjection.isNew()) {
				session.save(stNmDiscountProjection);

				stNmDiscountProjection.setNew(false);
			}
			else {
				stNmDiscountProjection = (StNmDiscountProjection)session.merge(stNmDiscountProjection);
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

		entityCache.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjectionImpl.class,
			stNmDiscountProjection.getPrimaryKey(), stNmDiscountProjection,
			false);

		stNmDiscountProjection.resetOriginalValues();

		return stNmDiscountProjection;
	}

	protected StNmDiscountProjection toUnwrappedModel(
		StNmDiscountProjection stNmDiscountProjection) {
		if (stNmDiscountProjection instanceof StNmDiscountProjectionImpl) {
			return stNmDiscountProjection;
		}

		StNmDiscountProjectionImpl stNmDiscountProjectionImpl = new StNmDiscountProjectionImpl();

		stNmDiscountProjectionImpl.setNew(stNmDiscountProjection.isNew());
		stNmDiscountProjectionImpl.setPrimaryKey(stNmDiscountProjection.getPrimaryKey());

		stNmDiscountProjectionImpl.setProjectionRate(stNmDiscountProjection.getProjectionRate());
		stNmDiscountProjectionImpl.setAdjustmentValue(stNmDiscountProjection.getAdjustmentValue());
		stNmDiscountProjectionImpl.setUserId(stNmDiscountProjection.getUserId());
		stNmDiscountProjectionImpl.setLastModifiedDate(stNmDiscountProjection.getLastModifiedDate());
		stNmDiscountProjectionImpl.setDiscountRate(stNmDiscountProjection.getDiscountRate());
		stNmDiscountProjectionImpl.setProjectionSales(stNmDiscountProjection.getProjectionSales());
		stNmDiscountProjectionImpl.setAdjustmentType(stNmDiscountProjection.getAdjustmentType());
		stNmDiscountProjectionImpl.setAdjustmentBasis(stNmDiscountProjection.getAdjustmentBasis());
		stNmDiscountProjectionImpl.setPeriodSid(stNmDiscountProjection.getPeriodSid());
		stNmDiscountProjectionImpl.setAdjustmentMethodology(stNmDiscountProjection.getAdjustmentMethodology());
		stNmDiscountProjectionImpl.setProjectionDetailsSid(stNmDiscountProjection.getProjectionDetailsSid());
		stNmDiscountProjectionImpl.setRsModelSid(stNmDiscountProjection.getRsModelSid());
		stNmDiscountProjectionImpl.setSessionId(stNmDiscountProjection.getSessionId());

		return stNmDiscountProjectionImpl;
	}

	/**
	 * Returns the st nm discount projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm discount projection
	 * @return the st nm discount projection
	 * @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmDiscountProjectionException {
		StNmDiscountProjection stNmDiscountProjection = fetchByPrimaryKey(primaryKey);

		if (stNmDiscountProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmDiscountProjection;
	}

	/**
	 * Returns the st nm discount projection with the primary key or throws a {@link NoSuchStNmDiscountProjectionException} if it could not be found.
	 *
	 * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	 * @return the st nm discount projection
	 * @throws NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection findByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK)
		throws NoSuchStNmDiscountProjectionException {
		return findByPrimaryKey((Serializable)stNmDiscountProjectionPK);
	}

	/**
	 * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm discount projection
	 * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
				StNmDiscountProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmDiscountProjection stNmDiscountProjection = (StNmDiscountProjection)serializable;

		if (stNmDiscountProjection == null) {
			Session session = null;

			try {
				session = openSession();

				stNmDiscountProjection = (StNmDiscountProjection)session.get(StNmDiscountProjectionImpl.class,
						primaryKey);

				if (stNmDiscountProjection != null) {
					cacheResult(stNmDiscountProjection);
				}
				else {
					entityCache.putResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
						StNmDiscountProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
					StNmDiscountProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmDiscountProjection;
	}

	/**
	 * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
	 * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjection fetchByPrimaryKey(
		StNmDiscountProjectionPK stNmDiscountProjectionPK) {
		return fetchByPrimaryKey((Serializable)stNmDiscountProjectionPK);
	}

	@Override
	public Map<Serializable, StNmDiscountProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmDiscountProjection> map = new HashMap<Serializable, StNmDiscountProjection>();

		for (Serializable primaryKey : primaryKeys) {
			StNmDiscountProjection stNmDiscountProjection = fetchByPrimaryKey(primaryKey);

			if (stNmDiscountProjection != null) {
				map.put(primaryKey, stNmDiscountProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm discount projections.
	 *
	 * @return the st nm discount projections
	 */
	@Override
	public List<StNmDiscountProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount projections
	 * @param end the upper bound of the range of st nm discount projections (not inclusive)
	 * @return the range of st nm discount projections
	 */
	@Override
	public List<StNmDiscountProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount projections
	 * @param end the upper bound of the range of st nm discount projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm discount projections
	 */
	@Override
	public List<StNmDiscountProjection> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount projections
	 * @param end the upper bound of the range of st nm discount projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm discount projections
	 */
	@Override
	public List<StNmDiscountProjection> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjection> orderByComparator,
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

		List<StNmDiscountProjection> list = null;

		if (retrieveFromCache) {
			list = (List<StNmDiscountProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMDISCOUNTPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMDISCOUNTPROJECTION;

				if (pagination) {
					sql = sql.concat(StNmDiscountProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmDiscountProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmDiscountProjection>)QueryUtil.list(q,
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
	 * Removes all the st nm discount projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmDiscountProjection stNmDiscountProjection : findAll()) {
			remove(stNmDiscountProjection);
		}
	}

	/**
	 * Returns the number of st nm discount projections.
	 *
	 * @return the number of st nm discount projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMDISCOUNTPROJECTION);

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
		return StNmDiscountProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm discount projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmDiscountProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMDISCOUNTPROJECTION = "SELECT stNmDiscountProjection FROM StNmDiscountProjection stNmDiscountProjection";
	private static final String _SQL_COUNT_STNMDISCOUNTPROJECTION = "SELECT COUNT(stNmDiscountProjection) FROM StNmDiscountProjection stNmDiscountProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmDiscountProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmDiscountProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmDiscountProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"projectionRate", "adjustmentValue", "userId",
				"lastModifiedDate", "discountRate", "projectionSales",
				"adjustmentType", "adjustmentBasis", "periodSid",
				"adjustmentMethodology", "projectionDetailsSid", "rsModelSid",
				"sessionId"
			});
}