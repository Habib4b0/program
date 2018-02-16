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

import com.stpl.app.exception.NoSuchChActualSalesException;
import com.stpl.app.model.ChActualSales;
import com.stpl.app.model.impl.ChActualSalesImpl;
import com.stpl.app.model.impl.ChActualSalesModelImpl;
import com.stpl.app.service.persistence.ChActualSalesPK;
import com.stpl.app.service.persistence.ChActualSalesPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ch actual sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualSalesPersistence
 * @see com.stpl.app.service.persistence.ChActualSalesUtil
 * @generated
 */
@ProviderType
public class ChActualSalesPersistenceImpl extends BasePersistenceImpl<ChActualSales>
	implements ChActualSalesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChActualSalesUtil} to access the ch actual sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChActualSalesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesModelImpl.FINDER_CACHE_ENABLED,
			ChActualSalesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesModelImpl.FINDER_CACHE_ENABLED,
			ChActualSalesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChActualSalesPersistenceImpl() {
		setModelClass(ChActualSales.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("contractUnits", "CONTRACT_UNITS");
			dbColumnNames.put("perOfBusiness", "PER_OF_BUSINESS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("contractSales", "CONTRACT_SALES");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("gtsSales", "GTS_SALES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ch actual sales in the entity cache if it is enabled.
	 *
	 * @param chActualSales the ch actual sales
	 */
	@Override
	public void cacheResult(ChActualSales chActualSales) {
		entityCache.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesImpl.class, chActualSales.getPrimaryKey(),
			chActualSales);

		chActualSales.resetOriginalValues();
	}

	/**
	 * Caches the ch actual saleses in the entity cache if it is enabled.
	 *
	 * @param chActualSaleses the ch actual saleses
	 */
	@Override
	public void cacheResult(List<ChActualSales> chActualSaleses) {
		for (ChActualSales chActualSales : chActualSaleses) {
			if (entityCache.getResult(
						ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
						ChActualSalesImpl.class, chActualSales.getPrimaryKey()) == null) {
				cacheResult(chActualSales);
			}
			else {
				chActualSales.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch actual saleses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChActualSalesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch actual sales.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChActualSales chActualSales) {
		entityCache.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesImpl.class, chActualSales.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChActualSales> chActualSaleses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChActualSales chActualSales : chActualSaleses) {
			entityCache.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
				ChActualSalesImpl.class, chActualSales.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch actual sales with the primary key. Does not add the ch actual sales to the database.
	 *
	 * @param chActualSalesPK the primary key for the new ch actual sales
	 * @return the new ch actual sales
	 */
	@Override
	public ChActualSales create(ChActualSalesPK chActualSalesPK) {
		ChActualSales chActualSales = new ChActualSalesImpl();

		chActualSales.setNew(true);
		chActualSales.setPrimaryKey(chActualSalesPK);

		return chActualSales;
	}

	/**
	 * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chActualSalesPK the primary key of the ch actual sales
	 * @return the ch actual sales that was removed
	 * @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales remove(ChActualSalesPK chActualSalesPK)
		throws NoSuchChActualSalesException {
		return remove((Serializable)chActualSalesPK);
	}

	/**
	 * Removes the ch actual sales with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch actual sales
	 * @return the ch actual sales that was removed
	 * @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales remove(Serializable primaryKey)
		throws NoSuchChActualSalesException {
		Session session = null;

		try {
			session = openSession();

			ChActualSales chActualSales = (ChActualSales)session.get(ChActualSalesImpl.class,
					primaryKey);

			if (chActualSales == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChActualSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chActualSales);
		}
		catch (NoSuchChActualSalesException nsee) {
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
	protected ChActualSales removeImpl(ChActualSales chActualSales) {
		chActualSales = toUnwrappedModel(chActualSales);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chActualSales)) {
				chActualSales = (ChActualSales)session.get(ChActualSalesImpl.class,
						chActualSales.getPrimaryKeyObj());
			}

			if (chActualSales != null) {
				session.delete(chActualSales);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chActualSales != null) {
			clearCache(chActualSales);
		}

		return chActualSales;
	}

	@Override
	public ChActualSales updateImpl(ChActualSales chActualSales) {
		chActualSales = toUnwrappedModel(chActualSales);

		boolean isNew = chActualSales.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chActualSales.isNew()) {
				session.save(chActualSales);

				chActualSales.setNew(false);
			}
			else {
				chActualSales = (ChActualSales)session.merge(chActualSales);
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

		entityCache.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
			ChActualSalesImpl.class, chActualSales.getPrimaryKey(),
			chActualSales, false);

		chActualSales.resetOriginalValues();

		return chActualSales;
	}

	protected ChActualSales toUnwrappedModel(ChActualSales chActualSales) {
		if (chActualSales instanceof ChActualSalesImpl) {
			return chActualSales;
		}

		ChActualSalesImpl chActualSalesImpl = new ChActualSalesImpl();

		chActualSalesImpl.setNew(chActualSales.isNew());
		chActualSalesImpl.setPrimaryKey(chActualSales.getPrimaryKey());

		chActualSalesImpl.setContractUnits(chActualSales.getContractUnits());
		chActualSalesImpl.setPerOfBusiness(chActualSales.getPerOfBusiness());
		chActualSalesImpl.setPeriodSid(chActualSales.getPeriodSid());
		chActualSalesImpl.setContractSales(chActualSales.getContractSales());
		chActualSalesImpl.setProjectionDetailsSid(chActualSales.getProjectionDetailsSid());
		chActualSalesImpl.setGtsSales(chActualSales.getGtsSales());

		return chActualSalesImpl;
	}

	/**
	 * Returns the ch actual sales with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch actual sales
	 * @return the ch actual sales
	 * @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChActualSalesException {
		ChActualSales chActualSales = fetchByPrimaryKey(primaryKey);

		if (chActualSales == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChActualSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chActualSales;
	}

	/**
	 * Returns the ch actual sales with the primary key or throws a {@link NoSuchChActualSalesException} if it could not be found.
	 *
	 * @param chActualSalesPK the primary key of the ch actual sales
	 * @return the ch actual sales
	 * @throws NoSuchChActualSalesException if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales findByPrimaryKey(ChActualSalesPK chActualSalesPK)
		throws NoSuchChActualSalesException {
		return findByPrimaryKey((Serializable)chActualSalesPK);
	}

	/**
	 * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch actual sales
	 * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
				ChActualSalesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChActualSales chActualSales = (ChActualSales)serializable;

		if (chActualSales == null) {
			Session session = null;

			try {
				session = openSession();

				chActualSales = (ChActualSales)session.get(ChActualSalesImpl.class,
						primaryKey);

				if (chActualSales != null) {
					cacheResult(chActualSales);
				}
				else {
					entityCache.putResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
						ChActualSalesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChActualSalesModelImpl.ENTITY_CACHE_ENABLED,
					ChActualSalesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chActualSales;
	}

	/**
	 * Returns the ch actual sales with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chActualSalesPK the primary key of the ch actual sales
	 * @return the ch actual sales, or <code>null</code> if a ch actual sales with the primary key could not be found
	 */
	@Override
	public ChActualSales fetchByPrimaryKey(ChActualSalesPK chActualSalesPK) {
		return fetchByPrimaryKey((Serializable)chActualSalesPK);
	}

	@Override
	public Map<Serializable, ChActualSales> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChActualSales> map = new HashMap<Serializable, ChActualSales>();

		for (Serializable primaryKey : primaryKeys) {
			ChActualSales chActualSales = fetchByPrimaryKey(primaryKey);

			if (chActualSales != null) {
				map.put(primaryKey, chActualSales);
			}
		}

		return map;
	}

	/**
	 * Returns all the ch actual saleses.
	 *
	 * @return the ch actual saleses
	 */
	@Override
	public List<ChActualSales> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch actual saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual saleses
	 * @param end the upper bound of the range of ch actual saleses (not inclusive)
	 * @return the range of ch actual saleses
	 */
	@Override
	public List<ChActualSales> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch actual saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual saleses
	 * @param end the upper bound of the range of ch actual saleses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch actual saleses
	 */
	@Override
	public List<ChActualSales> findAll(int start, int end,
		OrderByComparator<ChActualSales> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch actual saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChActualSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch actual saleses
	 * @param end the upper bound of the range of ch actual saleses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch actual saleses
	 */
	@Override
	public List<ChActualSales> findAll(int start, int end,
		OrderByComparator<ChActualSales> orderByComparator,
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

		List<ChActualSales> list = null;

		if (retrieveFromCache) {
			list = (List<ChActualSales>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHACTUALSALES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHACTUALSALES;

				if (pagination) {
					sql = sql.concat(ChActualSalesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChActualSales>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChActualSales>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ch actual saleses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChActualSales chActualSales : findAll()) {
			remove(chActualSales);
		}
	}

	/**
	 * Returns the number of ch actual saleses.
	 *
	 * @return the number of ch actual saleses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHACTUALSALES);

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
		return ChActualSalesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch actual sales persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChActualSalesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHACTUALSALES = "SELECT chActualSales FROM ChActualSales chActualSales";
	private static final String _SQL_COUNT_CHACTUALSALES = "SELECT COUNT(chActualSales) FROM ChActualSales chActualSales";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chActualSales.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChActualSales exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChActualSalesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"contractUnits", "perOfBusiness", "periodSid", "contractSales",
				"projectionDetailsSid", "gtsSales"
			});
}