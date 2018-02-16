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

import com.stpl.app.exception.NoSuchChProjectionDiscountException;
import com.stpl.app.model.ChProjectionDiscount;
import com.stpl.app.model.impl.ChProjectionDiscountImpl;
import com.stpl.app.model.impl.ChProjectionDiscountModelImpl;
import com.stpl.app.service.persistence.ChProjectionDiscountPK;
import com.stpl.app.service.persistence.ChProjectionDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionDiscountPersistence
 * @see com.stpl.app.service.persistence.ChProjectionDiscountUtil
 * @generated
 */
@ProviderType
public class ChProjectionDiscountPersistenceImpl extends BasePersistenceImpl<ChProjectionDiscount>
	implements ChProjectionDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChProjectionDiscountUtil} to access the ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChProjectionDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
			ChProjectionDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
			ChProjectionDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChProjectionDiscountPersistenceImpl() {
		setModelClass(ChProjectionDiscount.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustmentMethodology", "ADJUSTMENT_METHODOLOGY");
			dbColumnNames.put("productGrowth", "PRODUCT_GROWTH");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("accountGrowth", "ACCOUNT_GROWTH");
			dbColumnNames.put("discountAmount", "DISCOUNT_AMOUNT");
			dbColumnNames.put("discountRate", "DISCOUNT_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("adjustmentBasis", "ADJUSTMENT_BASIS");
			dbColumnNames.put("adjustmentValue", "ADJUSTMENT_VALUE");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ch projection discount in the entity cache if it is enabled.
	 *
	 * @param chProjectionDiscount the ch projection discount
	 */
	@Override
	public void cacheResult(ChProjectionDiscount chProjectionDiscount) {
		entityCache.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountImpl.class,
			chProjectionDiscount.getPrimaryKey(), chProjectionDiscount);

		chProjectionDiscount.resetOriginalValues();
	}

	/**
	 * Caches the ch projection discounts in the entity cache if it is enabled.
	 *
	 * @param chProjectionDiscounts the ch projection discounts
	 */
	@Override
	public void cacheResult(List<ChProjectionDiscount> chProjectionDiscounts) {
		for (ChProjectionDiscount chProjectionDiscount : chProjectionDiscounts) {
			if (entityCache.getResult(
						ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
						ChProjectionDiscountImpl.class,
						chProjectionDiscount.getPrimaryKey()) == null) {
				cacheResult(chProjectionDiscount);
			}
			else {
				chProjectionDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch projection discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChProjectionDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch projection discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChProjectionDiscount chProjectionDiscount) {
		entityCache.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountImpl.class, chProjectionDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChProjectionDiscount> chProjectionDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChProjectionDiscount chProjectionDiscount : chProjectionDiscounts) {
			entityCache.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
				ChProjectionDiscountImpl.class,
				chProjectionDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
	 *
	 * @param chProjectionDiscountPK the primary key for the new ch projection discount
	 * @return the new ch projection discount
	 */
	@Override
	public ChProjectionDiscount create(
		ChProjectionDiscountPK chProjectionDiscountPK) {
		ChProjectionDiscount chProjectionDiscount = new ChProjectionDiscountImpl();

		chProjectionDiscount.setNew(true);
		chProjectionDiscount.setPrimaryKey(chProjectionDiscountPK);

		return chProjectionDiscount;
	}

	/**
	 * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chProjectionDiscountPK the primary key of the ch projection discount
	 * @return the ch projection discount that was removed
	 * @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount remove(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws NoSuchChProjectionDiscountException {
		return remove((Serializable)chProjectionDiscountPK);
	}

	/**
	 * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch projection discount
	 * @return the ch projection discount that was removed
	 * @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount remove(Serializable primaryKey)
		throws NoSuchChProjectionDiscountException {
		Session session = null;

		try {
			session = openSession();

			ChProjectionDiscount chProjectionDiscount = (ChProjectionDiscount)session.get(ChProjectionDiscountImpl.class,
					primaryKey);

			if (chProjectionDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chProjectionDiscount);
		}
		catch (NoSuchChProjectionDiscountException nsee) {
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
	protected ChProjectionDiscount removeImpl(
		ChProjectionDiscount chProjectionDiscount) {
		chProjectionDiscount = toUnwrappedModel(chProjectionDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chProjectionDiscount)) {
				chProjectionDiscount = (ChProjectionDiscount)session.get(ChProjectionDiscountImpl.class,
						chProjectionDiscount.getPrimaryKeyObj());
			}

			if (chProjectionDiscount != null) {
				session.delete(chProjectionDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chProjectionDiscount != null) {
			clearCache(chProjectionDiscount);
		}

		return chProjectionDiscount;
	}

	@Override
	public ChProjectionDiscount updateImpl(
		ChProjectionDiscount chProjectionDiscount) {
		chProjectionDiscount = toUnwrappedModel(chProjectionDiscount);

		boolean isNew = chProjectionDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chProjectionDiscount.isNew()) {
				session.save(chProjectionDiscount);

				chProjectionDiscount.setNew(false);
			}
			else {
				chProjectionDiscount = (ChProjectionDiscount)session.merge(chProjectionDiscount);
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

		entityCache.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			ChProjectionDiscountImpl.class,
			chProjectionDiscount.getPrimaryKey(), chProjectionDiscount, false);

		chProjectionDiscount.resetOriginalValues();

		return chProjectionDiscount;
	}

	protected ChProjectionDiscount toUnwrappedModel(
		ChProjectionDiscount chProjectionDiscount) {
		if (chProjectionDiscount instanceof ChProjectionDiscountImpl) {
			return chProjectionDiscount;
		}

		ChProjectionDiscountImpl chProjectionDiscountImpl = new ChProjectionDiscountImpl();

		chProjectionDiscountImpl.setNew(chProjectionDiscount.isNew());
		chProjectionDiscountImpl.setPrimaryKey(chProjectionDiscount.getPrimaryKey());

		chProjectionDiscountImpl.setAdjustmentMethodology(chProjectionDiscount.getAdjustmentMethodology());
		chProjectionDiscountImpl.setProductGrowth(chProjectionDiscount.getProductGrowth());
		chProjectionDiscountImpl.setProjectionRate(chProjectionDiscount.getProjectionRate());
		chProjectionDiscountImpl.setProjectionDetailsSid(chProjectionDiscount.getProjectionDetailsSid());
		chProjectionDiscountImpl.setAccountGrowth(chProjectionDiscount.getAccountGrowth());
		chProjectionDiscountImpl.setDiscountAmount(chProjectionDiscount.getDiscountAmount());
		chProjectionDiscountImpl.setDiscountRate(chProjectionDiscount.getDiscountRate());
		chProjectionDiscountImpl.setPeriodSid(chProjectionDiscount.getPeriodSid());
		chProjectionDiscountImpl.setAdjustmentBasis(chProjectionDiscount.getAdjustmentBasis());
		chProjectionDiscountImpl.setAdjustmentValue(chProjectionDiscount.getAdjustmentValue());
		chProjectionDiscountImpl.setAdjustmentType(chProjectionDiscount.getAdjustmentType());
		chProjectionDiscountImpl.setRsModelSid(chProjectionDiscount.getRsModelSid());
		chProjectionDiscountImpl.setProjectionSales(chProjectionDiscount.getProjectionSales());

		return chProjectionDiscountImpl;
	}

	/**
	 * Returns the ch projection discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch projection discount
	 * @return the ch projection discount
	 * @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChProjectionDiscountException {
		ChProjectionDiscount chProjectionDiscount = fetchByPrimaryKey(primaryKey);

		if (chProjectionDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chProjectionDiscount;
	}

	/**
	 * Returns the ch projection discount with the primary key or throws a {@link NoSuchChProjectionDiscountException} if it could not be found.
	 *
	 * @param chProjectionDiscountPK the primary key of the ch projection discount
	 * @return the ch projection discount
	 * @throws NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount findByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK)
		throws NoSuchChProjectionDiscountException {
		return findByPrimaryKey((Serializable)chProjectionDiscountPK);
	}

	/**
	 * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch projection discount
	 * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
				ChProjectionDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChProjectionDiscount chProjectionDiscount = (ChProjectionDiscount)serializable;

		if (chProjectionDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				chProjectionDiscount = (ChProjectionDiscount)session.get(ChProjectionDiscountImpl.class,
						primaryKey);

				if (chProjectionDiscount != null) {
					cacheResult(chProjectionDiscount);
				}
				else {
					entityCache.putResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
						ChProjectionDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
					ChProjectionDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chProjectionDiscount;
	}

	/**
	 * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chProjectionDiscountPK the primary key of the ch projection discount
	 * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
	 */
	@Override
	public ChProjectionDiscount fetchByPrimaryKey(
		ChProjectionDiscountPK chProjectionDiscountPK) {
		return fetchByPrimaryKey((Serializable)chProjectionDiscountPK);
	}

	@Override
	public Map<Serializable, ChProjectionDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChProjectionDiscount> map = new HashMap<Serializable, ChProjectionDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			ChProjectionDiscount chProjectionDiscount = fetchByPrimaryKey(primaryKey);

			if (chProjectionDiscount != null) {
				map.put(primaryKey, chProjectionDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the ch projection discounts.
	 *
	 * @return the ch projection discounts
	 */
	@Override
	public List<ChProjectionDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection discounts
	 * @param end the upper bound of the range of ch projection discounts (not inclusive)
	 * @return the range of ch projection discounts
	 */
	@Override
	public List<ChProjectionDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection discounts
	 * @param end the upper bound of the range of ch projection discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch projection discounts
	 */
	@Override
	public List<ChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<ChProjectionDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch projection discounts
	 * @param end the upper bound of the range of ch projection discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch projection discounts
	 */
	@Override
	public List<ChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<ChProjectionDiscount> orderByComparator,
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

		List<ChProjectionDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<ChProjectionDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHPROJECTIONDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHPROJECTIONDISCOUNT;

				if (pagination) {
					sql = sql.concat(ChProjectionDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChProjectionDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChProjectionDiscount>)QueryUtil.list(q,
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
	 * Removes all the ch projection discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChProjectionDiscount chProjectionDiscount : findAll()) {
			remove(chProjectionDiscount);
		}
	}

	/**
	 * Returns the number of ch projection discounts.
	 *
	 * @return the number of ch projection discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHPROJECTIONDISCOUNT);

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
		return ChProjectionDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch projection discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChProjectionDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHPROJECTIONDISCOUNT = "SELECT chProjectionDiscount FROM ChProjectionDiscount chProjectionDiscount";
	private static final String _SQL_COUNT_CHPROJECTIONDISCOUNT = "SELECT COUNT(chProjectionDiscount) FROM ChProjectionDiscount chProjectionDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chProjectionDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChProjectionDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChProjectionDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustmentMethodology", "productGrowth", "projectionRate",
				"projectionDetailsSid", "accountGrowth", "discountAmount",
				"discountRate", "periodSid", "adjustmentBasis",
				"adjustmentValue", "adjustmentType", "rsModelSid",
				"projectionSales"
			});
}