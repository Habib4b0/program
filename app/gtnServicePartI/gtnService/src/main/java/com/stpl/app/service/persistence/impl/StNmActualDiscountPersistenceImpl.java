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

import com.stpl.app.exception.NoSuchStNmActualDiscountException;
import com.stpl.app.model.StNmActualDiscount;
import com.stpl.app.model.impl.StNmActualDiscountImpl;
import com.stpl.app.model.impl.StNmActualDiscountModelImpl;
import com.stpl.app.service.persistence.StNmActualDiscountPK;
import com.stpl.app.service.persistence.StNmActualDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmActualDiscountPersistence
 * @see com.stpl.app.service.persistence.StNmActualDiscountUtil
 * @generated
 */
@ProviderType
public class StNmActualDiscountPersistenceImpl extends BasePersistenceImpl<StNmActualDiscount>
	implements StNmActualDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmActualDiscountUtil} to access the st nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmActualDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			StNmActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			StNmActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmActualDiscountPersistenceImpl() {
		setModelClass(StNmActualDiscount.class);

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
	 * Caches the st nm actual discount in the entity cache if it is enabled.
	 *
	 * @param stNmActualDiscount the st nm actual discount
	 */
	@Override
	public void cacheResult(StNmActualDiscount stNmActualDiscount) {
		entityCache.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey(),
			stNmActualDiscount);

		stNmActualDiscount.resetOriginalValues();
	}

	/**
	 * Caches the st nm actual discounts in the entity cache if it is enabled.
	 *
	 * @param stNmActualDiscounts the st nm actual discounts
	 */
	@Override
	public void cacheResult(List<StNmActualDiscount> stNmActualDiscounts) {
		for (StNmActualDiscount stNmActualDiscount : stNmActualDiscounts) {
			if (entityCache.getResult(
						StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StNmActualDiscountImpl.class,
						stNmActualDiscount.getPrimaryKey()) == null) {
				cacheResult(stNmActualDiscount);
			}
			else {
				stNmActualDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm actual discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmActualDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm actual discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmActualDiscount stNmActualDiscount) {
		entityCache.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmActualDiscount> stNmActualDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmActualDiscount stNmActualDiscount : stNmActualDiscounts) {
			entityCache.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm actual discount with the primary key. Does not add the st nm actual discount to the database.
	 *
	 * @param stNmActualDiscountPK the primary key for the new st nm actual discount
	 * @return the new st nm actual discount
	 */
	@Override
	public StNmActualDiscount create(StNmActualDiscountPK stNmActualDiscountPK) {
		StNmActualDiscount stNmActualDiscount = new StNmActualDiscountImpl();

		stNmActualDiscount.setNew(true);
		stNmActualDiscount.setPrimaryKey(stNmActualDiscountPK);

		return stNmActualDiscount;
	}

	/**
	 * Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmActualDiscountPK the primary key of the st nm actual discount
	 * @return the st nm actual discount that was removed
	 * @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount remove(StNmActualDiscountPK stNmActualDiscountPK)
		throws NoSuchStNmActualDiscountException {
		return remove((Serializable)stNmActualDiscountPK);
	}

	/**
	 * Removes the st nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm actual discount
	 * @return the st nm actual discount that was removed
	 * @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount remove(Serializable primaryKey)
		throws NoSuchStNmActualDiscountException {
		Session session = null;

		try {
			session = openSession();

			StNmActualDiscount stNmActualDiscount = (StNmActualDiscount)session.get(StNmActualDiscountImpl.class,
					primaryKey);

			if (stNmActualDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmActualDiscount);
		}
		catch (NoSuchStNmActualDiscountException nsee) {
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
	protected StNmActualDiscount removeImpl(
		StNmActualDiscount stNmActualDiscount) {
		stNmActualDiscount = toUnwrappedModel(stNmActualDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmActualDiscount)) {
				stNmActualDiscount = (StNmActualDiscount)session.get(StNmActualDiscountImpl.class,
						stNmActualDiscount.getPrimaryKeyObj());
			}

			if (stNmActualDiscount != null) {
				session.delete(stNmActualDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmActualDiscount != null) {
			clearCache(stNmActualDiscount);
		}

		return stNmActualDiscount;
	}

	@Override
	public StNmActualDiscount updateImpl(StNmActualDiscount stNmActualDiscount) {
		stNmActualDiscount = toUnwrappedModel(stNmActualDiscount);

		boolean isNew = stNmActualDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmActualDiscount.isNew()) {
				session.save(stNmActualDiscount);

				stNmActualDiscount.setNew(false);
			}
			else {
				stNmActualDiscount = (StNmActualDiscount)session.merge(stNmActualDiscount);
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

		entityCache.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StNmActualDiscountImpl.class, stNmActualDiscount.getPrimaryKey(),
			stNmActualDiscount, false);

		stNmActualDiscount.resetOriginalValues();

		return stNmActualDiscount;
	}

	protected StNmActualDiscount toUnwrappedModel(
		StNmActualDiscount stNmActualDiscount) {
		if (stNmActualDiscount instanceof StNmActualDiscountImpl) {
			return stNmActualDiscount;
		}

		StNmActualDiscountImpl stNmActualDiscountImpl = new StNmActualDiscountImpl();

		stNmActualDiscountImpl.setNew(stNmActualDiscount.isNew());
		stNmActualDiscountImpl.setPrimaryKey(stNmActualDiscount.getPrimaryKey());

		stNmActualDiscountImpl.setActualSales(stNmActualDiscount.getActualSales());
		stNmActualDiscountImpl.setPeriodSid(stNmActualDiscount.getPeriodSid());
		stNmActualDiscountImpl.setActualRate(stNmActualDiscount.getActualRate());
		stNmActualDiscountImpl.setUserId(stNmActualDiscount.getUserId());
		stNmActualDiscountImpl.setLastModifiedDate(stNmActualDiscount.getLastModifiedDate());
		stNmActualDiscountImpl.setActualProjectionSales(stNmActualDiscount.getActualProjectionSales());
		stNmActualDiscountImpl.setActualProjectionRate(stNmActualDiscount.getActualProjectionRate());
		stNmActualDiscountImpl.setProjectionDetailsSid(stNmActualDiscount.getProjectionDetailsSid());
		stNmActualDiscountImpl.setRsModelSid(stNmActualDiscount.getRsModelSid());
		stNmActualDiscountImpl.setSessionId(stNmActualDiscount.getSessionId());

		return stNmActualDiscountImpl;
	}

	/**
	 * Returns the st nm actual discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm actual discount
	 * @return the st nm actual discount
	 * @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmActualDiscountException {
		StNmActualDiscount stNmActualDiscount = fetchByPrimaryKey(primaryKey);

		if (stNmActualDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmActualDiscount;
	}

	/**
	 * Returns the st nm actual discount with the primary key or throws a {@link NoSuchStNmActualDiscountException} if it could not be found.
	 *
	 * @param stNmActualDiscountPK the primary key of the st nm actual discount
	 * @return the st nm actual discount
	 * @throws NoSuchStNmActualDiscountException if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount findByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK)
		throws NoSuchStNmActualDiscountException {
		return findByPrimaryKey((Serializable)stNmActualDiscountPK);
	}

	/**
	 * Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm actual discount
	 * @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StNmActualDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmActualDiscount stNmActualDiscount = (StNmActualDiscount)serializable;

		if (stNmActualDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				stNmActualDiscount = (StNmActualDiscount)session.get(StNmActualDiscountImpl.class,
						primaryKey);

				if (stNmActualDiscount != null) {
					cacheResult(stNmActualDiscount);
				}
				else {
					entityCache.putResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StNmActualDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
					StNmActualDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmActualDiscount;
	}

	/**
	 * Returns the st nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmActualDiscountPK the primary key of the st nm actual discount
	 * @return the st nm actual discount, or <code>null</code> if a st nm actual discount with the primary key could not be found
	 */
	@Override
	public StNmActualDiscount fetchByPrimaryKey(
		StNmActualDiscountPK stNmActualDiscountPK) {
		return fetchByPrimaryKey((Serializable)stNmActualDiscountPK);
	}

	@Override
	public Map<Serializable, StNmActualDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmActualDiscount> map = new HashMap<Serializable, StNmActualDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			StNmActualDiscount stNmActualDiscount = fetchByPrimaryKey(primaryKey);

			if (stNmActualDiscount != null) {
				map.put(primaryKey, stNmActualDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm actual discounts.
	 *
	 * @return the st nm actual discounts
	 */
	@Override
	public List<StNmActualDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual discounts
	 * @param end the upper bound of the range of st nm actual discounts (not inclusive)
	 * @return the range of st nm actual discounts
	 */
	@Override
	public List<StNmActualDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual discounts
	 * @param end the upper bound of the range of st nm actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm actual discounts
	 */
	@Override
	public List<StNmActualDiscount> findAll(int start, int end,
		OrderByComparator<StNmActualDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm actual discounts
	 * @param end the upper bound of the range of st nm actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm actual discounts
	 */
	@Override
	public List<StNmActualDiscount> findAll(int start, int end,
		OrderByComparator<StNmActualDiscount> orderByComparator,
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

		List<StNmActualDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<StNmActualDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMACTUALDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMACTUALDISCOUNT;

				if (pagination) {
					sql = sql.concat(StNmActualDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmActualDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmActualDiscount>)QueryUtil.list(q,
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
	 * Removes all the st nm actual discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmActualDiscount stNmActualDiscount : findAll()) {
			remove(stNmActualDiscount);
		}
	}

	/**
	 * Returns the number of st nm actual discounts.
	 *
	 * @return the number of st nm actual discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMACTUALDISCOUNT);

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
		return StNmActualDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm actual discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmActualDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMACTUALDISCOUNT = "SELECT stNmActualDiscount FROM StNmActualDiscount stNmActualDiscount";
	private static final String _SQL_COUNT_STNMACTUALDISCOUNT = "SELECT COUNT(stNmActualDiscount) FROM StNmActualDiscount stNmActualDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmActualDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmActualDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmActualDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualSales", "periodSid", "actualRate", "userId",
				"lastModifiedDate", "actualProjectionSales",
				"actualProjectionRate", "projectionDetailsSid", "rsModelSid",
				"sessionId"
			});
}