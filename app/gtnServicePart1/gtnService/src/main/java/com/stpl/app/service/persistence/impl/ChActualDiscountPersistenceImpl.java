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

import com.stpl.app.exception.NoSuchChActualDiscountException;
import com.stpl.app.model.ChActualDiscount;
import com.stpl.app.model.impl.ChActualDiscountImpl;
import com.stpl.app.model.impl.ChActualDiscountModelImpl;
import com.stpl.app.service.persistence.ChActualDiscountPK;
import com.stpl.app.service.persistence.ChActualDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualDiscountPersistence
 * @see com.stpl.app.service.persistence.ChActualDiscountUtil
 * @generated
 */
@ProviderType
public class ChActualDiscountPersistenceImpl extends BasePersistenceImpl<ChActualDiscount>
	implements ChActualDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChActualDiscountUtil} to access the ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChActualDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			ChActualDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			ChActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChActualDiscountPersistenceImpl() {
		setModelClass(ChActualDiscount.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
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
	 * Caches the ch actual discount in the entity cache if it is enabled.
	 *
	 * @param chActualDiscount the ch actual discount
	 */
	@Override
	public void cacheResult(ChActualDiscount chActualDiscount) {
		entityCache.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey(),
			chActualDiscount);

		chActualDiscount.resetOriginalValues();
	}

	/**
	 * Caches the ch actual discounts in the entity cache if it is enabled.
	 *
	 * @param chActualDiscounts the ch actual discounts
	 */
	@Override
	public void cacheResult(List<ChActualDiscount> chActualDiscounts) {
		for (ChActualDiscount chActualDiscount : chActualDiscounts) {
			if (entityCache.getResult(
						ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						ChActualDiscountImpl.class,
						chActualDiscount.getPrimaryKey()) == null) {
				cacheResult(chActualDiscount);
			}
			else {
				chActualDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch actual discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChActualDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch actual discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChActualDiscount chActualDiscount) {
		entityCache.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChActualDiscount> chActualDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChActualDiscount chActualDiscount : chActualDiscounts) {
			entityCache.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
	 *
	 * @param chActualDiscountPK the primary key for the new ch actual discount
	 * @return the new ch actual discount
	 */
	@Override
	public ChActualDiscount create(ChActualDiscountPK chActualDiscountPK) {
		ChActualDiscount chActualDiscount = new ChActualDiscountImpl();

		chActualDiscount.setNew(true);
		chActualDiscount.setPrimaryKey(chActualDiscountPK);

		return chActualDiscount;
	}

	/**
	 * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chActualDiscountPK the primary key of the ch actual discount
	 * @return the ch actual discount that was removed
	 * @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount remove(ChActualDiscountPK chActualDiscountPK)
		throws NoSuchChActualDiscountException {
		return remove((Serializable)chActualDiscountPK);
	}

	/**
	 * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch actual discount
	 * @return the ch actual discount that was removed
	 * @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount remove(Serializable primaryKey)
		throws NoSuchChActualDiscountException {
		Session session = null;

		try {
			session = openSession();

			ChActualDiscount chActualDiscount = (ChActualDiscount)session.get(ChActualDiscountImpl.class,
					primaryKey);

			if (chActualDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chActualDiscount);
		}
		catch (NoSuchChActualDiscountException nsee) {
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
	protected ChActualDiscount removeImpl(ChActualDiscount chActualDiscount) {
		chActualDiscount = toUnwrappedModel(chActualDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chActualDiscount)) {
				chActualDiscount = (ChActualDiscount)session.get(ChActualDiscountImpl.class,
						chActualDiscount.getPrimaryKeyObj());
			}

			if (chActualDiscount != null) {
				session.delete(chActualDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chActualDiscount != null) {
			clearCache(chActualDiscount);
		}

		return chActualDiscount;
	}

	@Override
	public ChActualDiscount updateImpl(ChActualDiscount chActualDiscount) {
		chActualDiscount = toUnwrappedModel(chActualDiscount);

		boolean isNew = chActualDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chActualDiscount.isNew()) {
				session.save(chActualDiscount);

				chActualDiscount.setNew(false);
			}
			else {
				chActualDiscount = (ChActualDiscount)session.merge(chActualDiscount);
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

		entityCache.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChActualDiscountImpl.class, chActualDiscount.getPrimaryKey(),
			chActualDiscount, false);

		chActualDiscount.resetOriginalValues();

		return chActualDiscount;
	}

	protected ChActualDiscount toUnwrappedModel(
		ChActualDiscount chActualDiscount) {
		if (chActualDiscount instanceof ChActualDiscountImpl) {
			return chActualDiscount;
		}

		ChActualDiscountImpl chActualDiscountImpl = new ChActualDiscountImpl();

		chActualDiscountImpl.setNew(chActualDiscount.isNew());
		chActualDiscountImpl.setPrimaryKey(chActualDiscount.getPrimaryKey());

		chActualDiscountImpl.setActualRate(chActualDiscount.getActualRate());
		chActualDiscountImpl.setPeriodSid(chActualDiscount.getPeriodSid());
		chActualDiscountImpl.setProjectionDetailsSid(chActualDiscount.getProjectionDetailsSid());
		chActualDiscountImpl.setRsModelSid(chActualDiscount.getRsModelSid());
		chActualDiscountImpl.setActualSales(chActualDiscount.getActualSales());

		return chActualDiscountImpl;
	}

	/**
	 * Returns the ch actual discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch actual discount
	 * @return the ch actual discount
	 * @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChActualDiscountException {
		ChActualDiscount chActualDiscount = fetchByPrimaryKey(primaryKey);

		if (chActualDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chActualDiscount;
	}

	/**
	 * Returns the ch actual discount with the primary key or throws a {@link NoSuchChActualDiscountException} if it could not be found.
	 *
	 * @param chActualDiscountPK the primary key of the ch actual discount
	 * @return the ch actual discount
	 * @throws NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount findByPrimaryKey(
		ChActualDiscountPK chActualDiscountPK)
		throws NoSuchChActualDiscountException {
		return findByPrimaryKey((Serializable)chActualDiscountPK);
	}

	/**
	 * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch actual discount
	 * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				ChActualDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChActualDiscount chActualDiscount = (ChActualDiscount)serializable;

		if (chActualDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				chActualDiscount = (ChActualDiscount)session.get(ChActualDiscountImpl.class,
						primaryKey);

				if (chActualDiscount != null) {
					cacheResult(chActualDiscount);
				}
				else {
					entityCache.putResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						ChActualDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
					ChActualDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chActualDiscount;
	}

	/**
	 * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chActualDiscountPK the primary key of the ch actual discount
	 * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
	 */
	@Override
	public ChActualDiscount fetchByPrimaryKey(
		ChActualDiscountPK chActualDiscountPK) {
		return fetchByPrimaryKey((Serializable)chActualDiscountPK);
	}

	@Override
	public Map<Serializable, ChActualDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChActualDiscount> map = new HashMap<Serializable, ChActualDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			ChActualDiscount chActualDiscount = fetchByPrimaryKey(primaryKey);

			if (chActualDiscount != null) {
				map.put(primaryKey, chActualDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the ch actual discounts.
	 *
	 * @return the ch actual discounts
	 */
	@Override
	public List<ChActualDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual discounts
	 * @param end the upper bound of the range of ch actual discounts (not inclusive)
	 * @return the range of ch actual discounts
	 */
	@Override
	public List<ChActualDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual discounts
	 * @param end the upper bound of the range of ch actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch actual discounts
	 */
	@Override
	public List<ChActualDiscount> findAll(int start, int end,
		OrderByComparator<ChActualDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual discounts
	 * @param end the upper bound of the range of ch actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch actual discounts
	 */
	@Override
	public List<ChActualDiscount> findAll(int start, int end,
		OrderByComparator<ChActualDiscount> orderByComparator,
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

		List<ChActualDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<ChActualDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHACTUALDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHACTUALDISCOUNT;

				if (pagination) {
					sql = sql.concat(ChActualDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChActualDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChActualDiscount>)QueryUtil.list(q,
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
	 * Removes all the ch actual discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChActualDiscount chActualDiscount : findAll()) {
			remove(chActualDiscount);
		}
	}

	/**
	 * Returns the number of ch actual discounts.
	 *
	 * @return the number of ch actual discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHACTUALDISCOUNT);

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
		return ChActualDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch actual discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChActualDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHACTUALDISCOUNT = "SELECT chActualDiscount FROM ChActualDiscount chActualDiscount";
	private static final String _SQL_COUNT_CHACTUALDISCOUNT = "SELECT COUNT(chActualDiscount) FROM ChActualDiscount chActualDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chActualDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChActualDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChActualDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualRate", "periodSid", "projectionDetailsSid", "rsModelSid",
				"actualSales"
			});
}