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

import com.stpl.app.exception.NoSuchStChActualDiscountException;
import com.stpl.app.model.StChActualDiscount;
import com.stpl.app.model.impl.StChActualDiscountImpl;
import com.stpl.app.model.impl.StChActualDiscountModelImpl;
import com.stpl.app.service.persistence.StChActualDiscountPK;
import com.stpl.app.service.persistence.StChActualDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChActualDiscountPersistence
 * @see com.stpl.app.service.persistence.StChActualDiscountUtil
 * @generated
 */
@ProviderType
public class StChActualDiscountPersistenceImpl extends BasePersistenceImpl<StChActualDiscount>
	implements StChActualDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StChActualDiscountUtil} to access the st ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StChActualDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			StChActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			StChActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StChActualDiscountPersistenceImpl() {
		setModelClass(StChActualDiscount.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
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
	 * Caches the st ch actual discount in the entity cache if it is enabled.
	 *
	 * @param stChActualDiscount the st ch actual discount
	 */
	@Override
	public void cacheResult(StChActualDiscount stChActualDiscount) {
		entityCache.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey(),
			stChActualDiscount);

		stChActualDiscount.resetOriginalValues();
	}

	/**
	 * Caches the st ch actual discounts in the entity cache if it is enabled.
	 *
	 * @param stChActualDiscounts the st ch actual discounts
	 */
	@Override
	public void cacheResult(List<StChActualDiscount> stChActualDiscounts) {
		for (StChActualDiscount stChActualDiscount : stChActualDiscounts) {
			if (entityCache.getResult(
						StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StChActualDiscountImpl.class,
						stChActualDiscount.getPrimaryKey()) == null) {
				cacheResult(stChActualDiscount);
			}
			else {
				stChActualDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st ch actual discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StChActualDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st ch actual discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StChActualDiscount stChActualDiscount) {
		entityCache.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StChActualDiscount> stChActualDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StChActualDiscount stChActualDiscount : stChActualDiscounts) {
			entityCache.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
	 *
	 * @param stChActualDiscountPK the primary key for the new st ch actual discount
	 * @return the new st ch actual discount
	 */
	@Override
	public StChActualDiscount create(StChActualDiscountPK stChActualDiscountPK) {
		StChActualDiscount stChActualDiscount = new StChActualDiscountImpl();

		stChActualDiscount.setNew(true);
		stChActualDiscount.setPrimaryKey(stChActualDiscountPK);

		return stChActualDiscount;
	}

	/**
	 * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stChActualDiscountPK the primary key of the st ch actual discount
	 * @return the st ch actual discount that was removed
	 * @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount remove(StChActualDiscountPK stChActualDiscountPK)
		throws NoSuchStChActualDiscountException {
		return remove((Serializable)stChActualDiscountPK);
	}

	/**
	 * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st ch actual discount
	 * @return the st ch actual discount that was removed
	 * @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount remove(Serializable primaryKey)
		throws NoSuchStChActualDiscountException {
		Session session = null;

		try {
			session = openSession();

			StChActualDiscount stChActualDiscount = (StChActualDiscount)session.get(StChActualDiscountImpl.class,
					primaryKey);

			if (stChActualDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stChActualDiscount);
		}
		catch (NoSuchStChActualDiscountException nsee) {
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
	protected StChActualDiscount removeImpl(
		StChActualDiscount stChActualDiscount) {
		stChActualDiscount = toUnwrappedModel(stChActualDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stChActualDiscount)) {
				stChActualDiscount = (StChActualDiscount)session.get(StChActualDiscountImpl.class,
						stChActualDiscount.getPrimaryKeyObj());
			}

			if (stChActualDiscount != null) {
				session.delete(stChActualDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stChActualDiscount != null) {
			clearCache(stChActualDiscount);
		}

		return stChActualDiscount;
	}

	@Override
	public StChActualDiscount updateImpl(StChActualDiscount stChActualDiscount) {
		stChActualDiscount = toUnwrappedModel(stChActualDiscount);

		boolean isNew = stChActualDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stChActualDiscount.isNew()) {
				session.save(stChActualDiscount);

				stChActualDiscount.setNew(false);
			}
			else {
				stChActualDiscount = (StChActualDiscount)session.merge(stChActualDiscount);
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

		entityCache.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChActualDiscountImpl.class, stChActualDiscount.getPrimaryKey(),
			stChActualDiscount, false);

		stChActualDiscount.resetOriginalValues();

		return stChActualDiscount;
	}

	protected StChActualDiscount toUnwrappedModel(
		StChActualDiscount stChActualDiscount) {
		if (stChActualDiscount instanceof StChActualDiscountImpl) {
			return stChActualDiscount;
		}

		StChActualDiscountImpl stChActualDiscountImpl = new StChActualDiscountImpl();

		stChActualDiscountImpl.setNew(stChActualDiscount.isNew());
		stChActualDiscountImpl.setPrimaryKey(stChActualDiscount.getPrimaryKey());

		stChActualDiscountImpl.setLastModifiedDate(stChActualDiscount.getLastModifiedDate());
		stChActualDiscountImpl.setActualRate(stChActualDiscount.getActualRate());
		stChActualDiscountImpl.setPeriodSid(stChActualDiscount.getPeriodSid());
		stChActualDiscountImpl.setProjectionDetailsSid(stChActualDiscount.getProjectionDetailsSid());
		stChActualDiscountImpl.setUserId(stChActualDiscount.getUserId());
		stChActualDiscountImpl.setSessionId(stChActualDiscount.getSessionId());
		stChActualDiscountImpl.setRsModelSid(stChActualDiscount.getRsModelSid());
		stChActualDiscountImpl.setActualSales(stChActualDiscount.getActualSales());

		return stChActualDiscountImpl;
	}

	/**
	 * Returns the st ch actual discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch actual discount
	 * @return the st ch actual discount
	 * @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStChActualDiscountException {
		StChActualDiscount stChActualDiscount = fetchByPrimaryKey(primaryKey);

		if (stChActualDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stChActualDiscount;
	}

	/**
	 * Returns the st ch actual discount with the primary key or throws a {@link NoSuchStChActualDiscountException} if it could not be found.
	 *
	 * @param stChActualDiscountPK the primary key of the st ch actual discount
	 * @return the st ch actual discount
	 * @throws NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount findByPrimaryKey(
		StChActualDiscountPK stChActualDiscountPK)
		throws NoSuchStChActualDiscountException {
		return findByPrimaryKey((Serializable)stChActualDiscountPK);
	}

	/**
	 * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch actual discount
	 * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StChActualDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StChActualDiscount stChActualDiscount = (StChActualDiscount)serializable;

		if (stChActualDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				stChActualDiscount = (StChActualDiscount)session.get(StChActualDiscountImpl.class,
						primaryKey);

				if (stChActualDiscount != null) {
					cacheResult(stChActualDiscount);
				}
				else {
					entityCache.putResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StChActualDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
					StChActualDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stChActualDiscount;
	}

	/**
	 * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stChActualDiscountPK the primary key of the st ch actual discount
	 * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
	 */
	@Override
	public StChActualDiscount fetchByPrimaryKey(
		StChActualDiscountPK stChActualDiscountPK) {
		return fetchByPrimaryKey((Serializable)stChActualDiscountPK);
	}

	@Override
	public Map<Serializable, StChActualDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StChActualDiscount> map = new HashMap<Serializable, StChActualDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			StChActualDiscount stChActualDiscount = fetchByPrimaryKey(primaryKey);

			if (stChActualDiscount != null) {
				map.put(primaryKey, stChActualDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the st ch actual discounts.
	 *
	 * @return the st ch actual discounts
	 */
	@Override
	public List<StChActualDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch actual discounts
	 * @param end the upper bound of the range of st ch actual discounts (not inclusive)
	 * @return the range of st ch actual discounts
	 */
	@Override
	public List<StChActualDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch actual discounts
	 * @param end the upper bound of the range of st ch actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st ch actual discounts
	 */
	@Override
	public List<StChActualDiscount> findAll(int start, int end,
		OrderByComparator<StChActualDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch actual discounts
	 * @param end the upper bound of the range of st ch actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st ch actual discounts
	 */
	@Override
	public List<StChActualDiscount> findAll(int start, int end,
		OrderByComparator<StChActualDiscount> orderByComparator,
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

		List<StChActualDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<StChActualDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCHACTUALDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCHACTUALDISCOUNT;

				if (pagination) {
					sql = sql.concat(StChActualDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StChActualDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StChActualDiscount>)QueryUtil.list(q,
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
	 * Removes all the st ch actual discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StChActualDiscount stChActualDiscount : findAll()) {
			remove(stChActualDiscount);
		}
	}

	/**
	 * Returns the number of st ch actual discounts.
	 *
	 * @return the number of st ch actual discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCHACTUALDISCOUNT);

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
		return StChActualDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st ch actual discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StChActualDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STCHACTUALDISCOUNT = "SELECT stChActualDiscount FROM StChActualDiscount stChActualDiscount";
	private static final String _SQL_COUNT_STCHACTUALDISCOUNT = "SELECT COUNT(stChActualDiscount) FROM StChActualDiscount stChActualDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stChActualDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChActualDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StChActualDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "actualRate", "periodSid",
				"projectionDetailsSid", "userId", "sessionId", "rsModelSid",
				"actualSales"
			});
}