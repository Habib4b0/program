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

import com.stpl.app.exception.NoSuchNmActualDiscountException;
import com.stpl.app.model.NmActualDiscount;
import com.stpl.app.model.impl.NmActualDiscountImpl;
import com.stpl.app.model.impl.NmActualDiscountModelImpl;
import com.stpl.app.service.persistence.NmActualDiscountPK;
import com.stpl.app.service.persistence.NmActualDiscountPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualDiscountPersistence
 * @see com.stpl.app.service.persistence.NmActualDiscountUtil
 * @generated
 */
@ProviderType
public class NmActualDiscountPersistenceImpl extends BasePersistenceImpl<NmActualDiscount>
	implements NmActualDiscountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmActualDiscountUtil} to access the nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmActualDiscountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			NmActualDiscountImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountModelImpl.FINDER_CACHE_ENABLED,
			NmActualDiscountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmActualDiscountPersistenceImpl() {
		setModelClass(NmActualDiscount.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
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
	 * Caches the nm actual discount in the entity cache if it is enabled.
	 *
	 * @param nmActualDiscount the nm actual discount
	 */
	@Override
	public void cacheResult(NmActualDiscount nmActualDiscount) {
		entityCache.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey(),
			nmActualDiscount);

		nmActualDiscount.resetOriginalValues();
	}

	/**
	 * Caches the nm actual discounts in the entity cache if it is enabled.
	 *
	 * @param nmActualDiscounts the nm actual discounts
	 */
	@Override
	public void cacheResult(List<NmActualDiscount> nmActualDiscounts) {
		for (NmActualDiscount nmActualDiscount : nmActualDiscounts) {
			if (entityCache.getResult(
						NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						NmActualDiscountImpl.class,
						nmActualDiscount.getPrimaryKey()) == null) {
				cacheResult(nmActualDiscount);
			}
			else {
				nmActualDiscount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm actual discounts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmActualDiscountImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm actual discount.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmActualDiscount nmActualDiscount) {
		entityCache.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmActualDiscount> nmActualDiscounts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmActualDiscount nmActualDiscount : nmActualDiscounts) {
			entityCache.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
	 *
	 * @param nmActualDiscountPK the primary key for the new nm actual discount
	 * @return the new nm actual discount
	 */
	@Override
	public NmActualDiscount create(NmActualDiscountPK nmActualDiscountPK) {
		NmActualDiscount nmActualDiscount = new NmActualDiscountImpl();

		nmActualDiscount.setNew(true);
		nmActualDiscount.setPrimaryKey(nmActualDiscountPK);

		return nmActualDiscount;
	}

	/**
	 * Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmActualDiscountPK the primary key of the nm actual discount
	 * @return the nm actual discount that was removed
	 * @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount remove(NmActualDiscountPK nmActualDiscountPK)
		throws NoSuchNmActualDiscountException {
		return remove((Serializable)nmActualDiscountPK);
	}

	/**
	 * Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm actual discount
	 * @return the nm actual discount that was removed
	 * @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount remove(Serializable primaryKey)
		throws NoSuchNmActualDiscountException {
		Session session = null;

		try {
			session = openSession();

			NmActualDiscount nmActualDiscount = (NmActualDiscount)session.get(NmActualDiscountImpl.class,
					primaryKey);

			if (nmActualDiscount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmActualDiscount);
		}
		catch (NoSuchNmActualDiscountException nsee) {
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
	protected NmActualDiscount removeImpl(NmActualDiscount nmActualDiscount) {
		nmActualDiscount = toUnwrappedModel(nmActualDiscount);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmActualDiscount)) {
				nmActualDiscount = (NmActualDiscount)session.get(NmActualDiscountImpl.class,
						nmActualDiscount.getPrimaryKeyObj());
			}

			if (nmActualDiscount != null) {
				session.delete(nmActualDiscount);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmActualDiscount != null) {
			clearCache(nmActualDiscount);
		}

		return nmActualDiscount;
	}

	@Override
	public NmActualDiscount updateImpl(NmActualDiscount nmActualDiscount) {
		nmActualDiscount = toUnwrappedModel(nmActualDiscount);

		boolean isNew = nmActualDiscount.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmActualDiscount.isNew()) {
				session.save(nmActualDiscount);

				nmActualDiscount.setNew(false);
			}
			else {
				nmActualDiscount = (NmActualDiscount)session.merge(nmActualDiscount);
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

		entityCache.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
			NmActualDiscountImpl.class, nmActualDiscount.getPrimaryKey(),
			nmActualDiscount, false);

		nmActualDiscount.resetOriginalValues();

		return nmActualDiscount;
	}

	protected NmActualDiscount toUnwrappedModel(
		NmActualDiscount nmActualDiscount) {
		if (nmActualDiscount instanceof NmActualDiscountImpl) {
			return nmActualDiscount;
		}

		NmActualDiscountImpl nmActualDiscountImpl = new NmActualDiscountImpl();

		nmActualDiscountImpl.setNew(nmActualDiscount.isNew());
		nmActualDiscountImpl.setPrimaryKey(nmActualDiscount.getPrimaryKey());

		nmActualDiscountImpl.setActualRate(nmActualDiscount.getActualRate());
		nmActualDiscountImpl.setPeriodSid(nmActualDiscount.getPeriodSid());
		nmActualDiscountImpl.setProjectionDetailsSid(nmActualDiscount.getProjectionDetailsSid());
		nmActualDiscountImpl.setActualSales(nmActualDiscount.getActualSales());

		return nmActualDiscountImpl;
	}

	/**
	 * Returns the nm actual discount with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm actual discount
	 * @return the nm actual discount
	 * @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmActualDiscountException {
		NmActualDiscount nmActualDiscount = fetchByPrimaryKey(primaryKey);

		if (nmActualDiscount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmActualDiscountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmActualDiscount;
	}

	/**
	 * Returns the nm actual discount with the primary key or throws a {@link NoSuchNmActualDiscountException} if it could not be found.
	 *
	 * @param nmActualDiscountPK the primary key of the nm actual discount
	 * @return the nm actual discount
	 * @throws NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount findByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK)
		throws NoSuchNmActualDiscountException {
		return findByPrimaryKey((Serializable)nmActualDiscountPK);
	}

	/**
	 * Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm actual discount
	 * @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
				NmActualDiscountImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmActualDiscount nmActualDiscount = (NmActualDiscount)serializable;

		if (nmActualDiscount == null) {
			Session session = null;

			try {
				session = openSession();

				nmActualDiscount = (NmActualDiscount)session.get(NmActualDiscountImpl.class,
						primaryKey);

				if (nmActualDiscount != null) {
					cacheResult(nmActualDiscount);
				}
				else {
					entityCache.putResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
						NmActualDiscountImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmActualDiscountModelImpl.ENTITY_CACHE_ENABLED,
					NmActualDiscountImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmActualDiscount;
	}

	/**
	 * Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmActualDiscountPK the primary key of the nm actual discount
	 * @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
	 */
	@Override
	public NmActualDiscount fetchByPrimaryKey(
		NmActualDiscountPK nmActualDiscountPK) {
		return fetchByPrimaryKey((Serializable)nmActualDiscountPK);
	}

	@Override
	public Map<Serializable, NmActualDiscount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmActualDiscount> map = new HashMap<Serializable, NmActualDiscount>();

		for (Serializable primaryKey : primaryKeys) {
			NmActualDiscount nmActualDiscount = fetchByPrimaryKey(primaryKey);

			if (nmActualDiscount != null) {
				map.put(primaryKey, nmActualDiscount);
			}
		}

		return map;
	}

	/**
	 * Returns all the nm actual discounts.
	 *
	 * @return the nm actual discounts
	 */
	@Override
	public List<NmActualDiscount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual discounts
	 * @param end the upper bound of the range of nm actual discounts (not inclusive)
	 * @return the range of nm actual discounts
	 */
	@Override
	public List<NmActualDiscount> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual discounts
	 * @param end the upper bound of the range of nm actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm actual discounts
	 */
	@Override
	public List<NmActualDiscount> findAll(int start, int end,
		OrderByComparator<NmActualDiscount> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm actual discounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm actual discounts
	 * @param end the upper bound of the range of nm actual discounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm actual discounts
	 */
	@Override
	public List<NmActualDiscount> findAll(int start, int end,
		OrderByComparator<NmActualDiscount> orderByComparator,
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

		List<NmActualDiscount> list = null;

		if (retrieveFromCache) {
			list = (List<NmActualDiscount>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMACTUALDISCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMACTUALDISCOUNT;

				if (pagination) {
					sql = sql.concat(NmActualDiscountModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmActualDiscount>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmActualDiscount>)QueryUtil.list(q,
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
	 * Removes all the nm actual discounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmActualDiscount nmActualDiscount : findAll()) {
			remove(nmActualDiscount);
		}
	}

	/**
	 * Returns the number of nm actual discounts.
	 *
	 * @return the number of nm actual discounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMACTUALDISCOUNT);

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
		return NmActualDiscountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm actual discount persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmActualDiscountImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMACTUALDISCOUNT = "SELECT nmActualDiscount FROM NmActualDiscount nmActualDiscount";
	private static final String _SQL_COUNT_NMACTUALDISCOUNT = "SELECT COUNT(nmActualDiscount) FROM NmActualDiscount nmActualDiscount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmActualDiscount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmActualDiscount exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmActualDiscountPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualRate", "periodSid", "projectionDetailsSid", "actualSales"
			});
}