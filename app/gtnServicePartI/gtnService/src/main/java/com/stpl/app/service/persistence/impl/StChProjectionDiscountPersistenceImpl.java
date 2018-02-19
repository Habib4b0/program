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

import com.stpl.app.exception.NoSuchStChProjectionDiscountException;
import com.stpl.app.model.StChProjectionDiscount;
import com.stpl.app.model.impl.StChProjectionDiscountImpl;
import com.stpl.app.model.impl.StChProjectionDiscountModelImpl;
import com.stpl.app.service.persistence.StChProjectionDiscountPK;
import com.stpl.app.service.persistence.StChProjectionDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChProjectionDiscountPersistence
 * @see com.stpl.app.service.persistence.StChProjectionDiscountUtil
 * @generated
 */
@ProviderType
public class StChProjectionDiscountPersistenceImpl extends BasePersistenceImpl<StChProjectionDiscount>
	implements StChProjectionDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StChProjectionDiscountUtil} to access the st ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StChProjectionDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
			StChProjectionDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED,
			StChProjectionDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StChProjectionDiscountPersistenceImpl() {
		setModelClass(StChProjectionDiscount.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("adjustmentMethodology", "ADJUSTMENT_METHODOLOGY");
			dbColumnNames.put("productGrowth", "PRODUCT_GROWTH");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("accountGrowth", "ACCOUNT_GROWTH");
			dbColumnNames.put("discountAmount", "DISCOUNT_AMOUNT");
			dbColumnNames.put("discountRate", "DISCOUNT_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("adjustmentBasis", "ADJUSTMENT_BASIS");
			dbColumnNames.put("sessionId", "SESSION_ID");
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
	 * Caches the st ch projection discount in the entity cache if it is enabled.
	 *
	 * @param stChProjectionDiscount the st ch projection discount
	 */
	@Override
	public void cacheResult(StChProjectionDiscount stChProjectionDiscount) {
		entityCache.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountImpl.class,
			stChProjectionDiscount.getPrimaryKey(), stChProjectionDiscount);

		stChProjectionDiscount.resetOriginalValues();
	}

	/**
	 * Caches the st ch projection discounts in the entity cache if it is enabled.
	 *
	 * @param stChProjectionDiscounts the st ch projection discounts
	 */
	@Override
	public void cacheResult(
		List<StChProjectionDiscount> stChProjectionDiscounts) {
		for (StChProjectionDiscount stChProjectionDiscount : stChProjectionDiscounts) {
			if (entityCache.getResult(
						StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StChProjectionDiscountImpl.class,
						stChProjectionDiscount.getPrimaryKey()) == null) {
				cacheResult(stChProjectionDiscount);
			}
			else {
				stChProjectionDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st ch projection discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StChProjectionDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st ch projection discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StChProjectionDiscount stChProjectionDiscount) {
		entityCache.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountImpl.class,
			stChProjectionDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StChProjectionDiscount> stChProjectionDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StChProjectionDiscount stChProjectionDiscount : stChProjectionDiscounts) {
			entityCache.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StChProjectionDiscountImpl.class,
				stChProjectionDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
	 *
	 * @param stChProjectionDiscountPK the primary key for the new st ch projection discount
	 * @return the new st ch projection discount
	 */
	@Override
	public StChProjectionDiscount create(
		StChProjectionDiscountPK stChProjectionDiscountPK) {
		StChProjectionDiscount stChProjectionDiscount = new StChProjectionDiscountImpl();

		stChProjectionDiscount.setNew(true);
		stChProjectionDiscount.setPrimaryKey(stChProjectionDiscountPK);

		return stChProjectionDiscount;
	}

	/**
	 * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stChProjectionDiscountPK the primary key of the st ch projection discount
	 * @return the st ch projection discount that was removed
	 * @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount remove(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws NoSuchStChProjectionDiscountException {
		return remove((Serializable)stChProjectionDiscountPK);
	}

	/**
	 * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st ch projection discount
	 * @return the st ch projection discount that was removed
	 * @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount remove(Serializable primaryKey)
		throws NoSuchStChProjectionDiscountException {
		Session session = null;

		try {
			session = openSession();

			StChProjectionDiscount stChProjectionDiscount = (StChProjectionDiscount)session.get(StChProjectionDiscountImpl.class,
					primaryKey);

			if (stChProjectionDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stChProjectionDiscount);
		}
		catch (NoSuchStChProjectionDiscountException nsee) {
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
	protected StChProjectionDiscount removeImpl(
		StChProjectionDiscount stChProjectionDiscount) {
		stChProjectionDiscount = toUnwrappedModel(stChProjectionDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stChProjectionDiscount)) {
				stChProjectionDiscount = (StChProjectionDiscount)session.get(StChProjectionDiscountImpl.class,
						stChProjectionDiscount.getPrimaryKeyObj());
			}

			if (stChProjectionDiscount != null) {
				session.delete(stChProjectionDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stChProjectionDiscount != null) {
			clearCache(stChProjectionDiscount);
		}

		return stChProjectionDiscount;
	}

	@Override
	public StChProjectionDiscount updateImpl(
		StChProjectionDiscount stChProjectionDiscount) {
		stChProjectionDiscount = toUnwrappedModel(stChProjectionDiscount);

		boolean isNew = stChProjectionDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stChProjectionDiscount.isNew()) {
				session.save(stChProjectionDiscount);

				stChProjectionDiscount.setNew(false);
			}
			else {
				stChProjectionDiscount = (StChProjectionDiscount)session.merge(stChProjectionDiscount);
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

		entityCache.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
			StChProjectionDiscountImpl.class,
			stChProjectionDiscount.getPrimaryKey(), stChProjectionDiscount,
			false);

		stChProjectionDiscount.resetOriginalValues();

		return stChProjectionDiscount;
	}

	protected StChProjectionDiscount toUnwrappedModel(
		StChProjectionDiscount stChProjectionDiscount) {
		if (stChProjectionDiscount instanceof StChProjectionDiscountImpl) {
			return stChProjectionDiscount;
		}

		StChProjectionDiscountImpl stChProjectionDiscountImpl = new StChProjectionDiscountImpl();

		stChProjectionDiscountImpl.setNew(stChProjectionDiscount.isNew());
		stChProjectionDiscountImpl.setPrimaryKey(stChProjectionDiscount.getPrimaryKey());

		stChProjectionDiscountImpl.setLastModifiedDate(stChProjectionDiscount.getLastModifiedDate());
		stChProjectionDiscountImpl.setAdjustmentMethodology(stChProjectionDiscount.getAdjustmentMethodology());
		stChProjectionDiscountImpl.setProductGrowth(stChProjectionDiscount.getProductGrowth());
		stChProjectionDiscountImpl.setProjectionRate(stChProjectionDiscount.getProjectionRate());
		stChProjectionDiscountImpl.setProjectionDetailsSid(stChProjectionDiscount.getProjectionDetailsSid());
		stChProjectionDiscountImpl.setUserId(stChProjectionDiscount.getUserId());
		stChProjectionDiscountImpl.setAccountGrowth(stChProjectionDiscount.getAccountGrowth());
		stChProjectionDiscountImpl.setDiscountAmount(stChProjectionDiscount.getDiscountAmount());
		stChProjectionDiscountImpl.setDiscountRate(stChProjectionDiscount.getDiscountRate());
		stChProjectionDiscountImpl.setPeriodSid(stChProjectionDiscount.getPeriodSid());
		stChProjectionDiscountImpl.setAdjustmentBasis(stChProjectionDiscount.getAdjustmentBasis());
		stChProjectionDiscountImpl.setSessionId(stChProjectionDiscount.getSessionId());
		stChProjectionDiscountImpl.setAdjustmentValue(stChProjectionDiscount.getAdjustmentValue());
		stChProjectionDiscountImpl.setAdjustmentType(stChProjectionDiscount.getAdjustmentType());
		stChProjectionDiscountImpl.setRsModelSid(stChProjectionDiscount.getRsModelSid());
		stChProjectionDiscountImpl.setProjectionSales(stChProjectionDiscount.getProjectionSales());

		return stChProjectionDiscountImpl;
	}

	/**
	 * Returns the st ch projection discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch projection discount
	 * @return the st ch projection discount
	 * @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStChProjectionDiscountException {
		StChProjectionDiscount stChProjectionDiscount = fetchByPrimaryKey(primaryKey);

		if (stChProjectionDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStChProjectionDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stChProjectionDiscount;
	}

	/**
	 * Returns the st ch projection discount with the primary key or throws a {@link NoSuchStChProjectionDiscountException} if it could not be found.
	 *
	 * @param stChProjectionDiscountPK the primary key of the st ch projection discount
	 * @return the st ch projection discount
	 * @throws NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount findByPrimaryKey(
		StChProjectionDiscountPK stChProjectionDiscountPK)
		throws NoSuchStChProjectionDiscountException {
		return findByPrimaryKey((Serializable)stChProjectionDiscountPK);
	}

	/**
	 * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch projection discount
	 * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
				StChProjectionDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StChProjectionDiscount stChProjectionDiscount = (StChProjectionDiscount)serializable;

		if (stChProjectionDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				stChProjectionDiscount = (StChProjectionDiscount)session.get(StChProjectionDiscountImpl.class,
						primaryKey);

				if (stChProjectionDiscount != null) {
					cacheResult(stChProjectionDiscount);
				}
				else {
					entityCache.putResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
						StChProjectionDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StChProjectionDiscountModelImpl.ENTITY_CACHE_ENABLED,
					StChProjectionDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stChProjectionDiscount;
	}

	/**
	 * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stChProjectionDiscountPK the primary key of the st ch projection discount
	 * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
	 */
	@Override
	public StChProjectionDiscount fetchByPrimaryKey(
		StChProjectionDiscountPK stChProjectionDiscountPK) {
		return fetchByPrimaryKey((Serializable)stChProjectionDiscountPK);
	}

	@Override
	public Map<Serializable, StChProjectionDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StChProjectionDiscount> map = new HashMap<Serializable, StChProjectionDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			StChProjectionDiscount stChProjectionDiscount = fetchByPrimaryKey(primaryKey);

			if (stChProjectionDiscount != null) {
				map.put(primaryKey, stChProjectionDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the st ch projection discounts.
	 *
	 * @return the st ch projection discounts
	 */
	@Override
	public List<StChProjectionDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch projection discounts
	 * @param end the upper bound of the range of st ch projection discounts (not inclusive)
	 * @return the range of st ch projection discounts
	 */
	@Override
	public List<StChProjectionDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch projection discounts
	 * @param end the upper bound of the range of st ch projection discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st ch projection discounts
	 */
	@Override
	public List<StChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<StChProjectionDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st ch projection discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch projection discounts
	 * @param end the upper bound of the range of st ch projection discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st ch projection discounts
	 */
	@Override
	public List<StChProjectionDiscount> findAll(int start, int end,
		OrderByComparator<StChProjectionDiscount> orderByComparator,
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

		List<StChProjectionDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<StChProjectionDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCHPROJECTIONDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCHPROJECTIONDISCOUNT;

				if (pagination) {
					sql = sql.concat(StChProjectionDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StChProjectionDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StChProjectionDiscount>)QueryUtil.list(q,
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
	 * Removes all the st ch projection discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StChProjectionDiscount stChProjectionDiscount : findAll()) {
			remove(stChProjectionDiscount);
		}
	}

	/**
	 * Returns the number of st ch projection discounts.
	 *
	 * @return the number of st ch projection discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCHPROJECTIONDISCOUNT);

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
		return StChProjectionDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st ch projection discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StChProjectionDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STCHPROJECTIONDISCOUNT = "SELECT stChProjectionDiscount FROM StChProjectionDiscount stChProjectionDiscount";
	private static final String _SQL_COUNT_STCHPROJECTIONDISCOUNT = "SELECT COUNT(stChProjectionDiscount) FROM StChProjectionDiscount stChProjectionDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stChProjectionDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChProjectionDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StChProjectionDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "adjustmentMethodology", "productGrowth",
				"projectionRate", "projectionDetailsSid", "userId",
				"accountGrowth", "discountAmount", "discountRate", "periodSid",
				"adjustmentBasis", "sessionId", "adjustmentValue",
				"adjustmentType", "rsModelSid", "projectionSales"
			});
}