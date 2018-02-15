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

import com.stpl.app.exception.NoSuchChSalesProjectionException;
import com.stpl.app.model.ChSalesProjection;
import com.stpl.app.model.impl.ChSalesProjectionImpl;
import com.stpl.app.model.impl.ChSalesProjectionModelImpl;
import com.stpl.app.service.persistence.ChSalesProjectionPK;
import com.stpl.app.service.persistence.ChSalesProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ch sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionPersistence
 * @see com.stpl.app.service.persistence.ChSalesProjectionUtil
 * @generated
 */
@ProviderType
public class ChSalesProjectionPersistenceImpl extends BasePersistenceImpl<ChSalesProjection>
	implements ChSalesProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChSalesProjectionUtil} to access the ch sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChSalesProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
			ChSalesProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
			ChSalesProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChSalesProjectionPersistenceImpl() {
		setModelClass(ChSalesProjection.class);

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
	 * Caches the ch sales projection in the entity cache if it is enabled.
	 *
	 * @param chSalesProjection the ch sales projection
	 */
	@Override
	public void cacheResult(ChSalesProjection chSalesProjection) {
		entityCache.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey(),
			chSalesProjection);

		chSalesProjection.resetOriginalValues();
	}

	/**
	 * Caches the ch sales projections in the entity cache if it is enabled.
	 *
	 * @param chSalesProjections the ch sales projections
	 */
	@Override
	public void cacheResult(List<ChSalesProjection> chSalesProjections) {
		for (ChSalesProjection chSalesProjection : chSalesProjections) {
			if (entityCache.getResult(
						ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
						ChSalesProjectionImpl.class,
						chSalesProjection.getPrimaryKey()) == null) {
				cacheResult(chSalesProjection);
			}
			else {
				chSalesProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch sales projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChSalesProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch sales projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChSalesProjection chSalesProjection) {
		entityCache.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChSalesProjection> chSalesProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChSalesProjection chSalesProjection : chSalesProjections) {
			entityCache.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
				ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch sales projection with the primary key. Does not add the ch sales projection to the database.
	 *
	 * @param chSalesProjectionPK the primary key for the new ch sales projection
	 * @return the new ch sales projection
	 */
	@Override
	public ChSalesProjection create(ChSalesProjectionPK chSalesProjectionPK) {
		ChSalesProjection chSalesProjection = new ChSalesProjectionImpl();

		chSalesProjection.setNew(true);
		chSalesProjection.setPrimaryKey(chSalesProjectionPK);

		return chSalesProjection;
	}

	/**
	 * Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chSalesProjectionPK the primary key of the ch sales projection
	 * @return the ch sales projection that was removed
	 * @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection remove(ChSalesProjectionPK chSalesProjectionPK)
		throws NoSuchChSalesProjectionException {
		return remove((Serializable)chSalesProjectionPK);
	}

	/**
	 * Removes the ch sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch sales projection
	 * @return the ch sales projection that was removed
	 * @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection remove(Serializable primaryKey)
		throws NoSuchChSalesProjectionException {
		Session session = null;

		try {
			session = openSession();

			ChSalesProjection chSalesProjection = (ChSalesProjection)session.get(ChSalesProjectionImpl.class,
					primaryKey);

			if (chSalesProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chSalesProjection);
		}
		catch (NoSuchChSalesProjectionException nsee) {
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
	protected ChSalesProjection removeImpl(ChSalesProjection chSalesProjection) {
		chSalesProjection = toUnwrappedModel(chSalesProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chSalesProjection)) {
				chSalesProjection = (ChSalesProjection)session.get(ChSalesProjectionImpl.class,
						chSalesProjection.getPrimaryKeyObj());
			}

			if (chSalesProjection != null) {
				session.delete(chSalesProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chSalesProjection != null) {
			clearCache(chSalesProjection);
		}

		return chSalesProjection;
	}

	@Override
	public ChSalesProjection updateImpl(ChSalesProjection chSalesProjection) {
		chSalesProjection = toUnwrappedModel(chSalesProjection);

		boolean isNew = chSalesProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chSalesProjection.isNew()) {
				session.save(chSalesProjection);

				chSalesProjection.setNew(false);
			}
			else {
				chSalesProjection = (ChSalesProjection)session.merge(chSalesProjection);
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

		entityCache.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionImpl.class, chSalesProjection.getPrimaryKey(),
			chSalesProjection, false);

		chSalesProjection.resetOriginalValues();

		return chSalesProjection;
	}

	protected ChSalesProjection toUnwrappedModel(
		ChSalesProjection chSalesProjection) {
		if (chSalesProjection instanceof ChSalesProjectionImpl) {
			return chSalesProjection;
		}

		ChSalesProjectionImpl chSalesProjectionImpl = new ChSalesProjectionImpl();

		chSalesProjectionImpl.setNew(chSalesProjection.isNew());
		chSalesProjectionImpl.setPrimaryKey(chSalesProjection.getPrimaryKey());

		chSalesProjectionImpl.setContractUnits(chSalesProjection.getContractUnits());
		chSalesProjectionImpl.setPerOfBusiness(chSalesProjection.getPerOfBusiness());
		chSalesProjectionImpl.setPeriodSid(chSalesProjection.getPeriodSid());
		chSalesProjectionImpl.setContractSales(chSalesProjection.getContractSales());
		chSalesProjectionImpl.setProjectionDetailsSid(chSalesProjection.getProjectionDetailsSid());
		chSalesProjectionImpl.setGtsSales(chSalesProjection.getGtsSales());

		return chSalesProjectionImpl;
	}

	/**
	 * Returns the ch sales projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch sales projection
	 * @return the ch sales projection
	 * @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChSalesProjectionException {
		ChSalesProjection chSalesProjection = fetchByPrimaryKey(primaryKey);

		if (chSalesProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chSalesProjection;
	}

	/**
	 * Returns the ch sales projection with the primary key or throws a {@link NoSuchChSalesProjectionException} if it could not be found.
	 *
	 * @param chSalesProjectionPK the primary key of the ch sales projection
	 * @return the ch sales projection
	 * @throws NoSuchChSalesProjectionException if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection findByPrimaryKey(
		ChSalesProjectionPK chSalesProjectionPK)
		throws NoSuchChSalesProjectionException {
		return findByPrimaryKey((Serializable)chSalesProjectionPK);
	}

	/**
	 * Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch sales projection
	 * @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
				ChSalesProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChSalesProjection chSalesProjection = (ChSalesProjection)serializable;

		if (chSalesProjection == null) {
			Session session = null;

			try {
				session = openSession();

				chSalesProjection = (ChSalesProjection)session.get(ChSalesProjectionImpl.class,
						primaryKey);

				if (chSalesProjection != null) {
					cacheResult(chSalesProjection);
				}
				else {
					entityCache.putResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
						ChSalesProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
					ChSalesProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chSalesProjection;
	}

	/**
	 * Returns the ch sales projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chSalesProjectionPK the primary key of the ch sales projection
	 * @return the ch sales projection, or <code>null</code> if a ch sales projection with the primary key could not be found
	 */
	@Override
	public ChSalesProjection fetchByPrimaryKey(
		ChSalesProjectionPK chSalesProjectionPK) {
		return fetchByPrimaryKey((Serializable)chSalesProjectionPK);
	}

	@Override
	public Map<Serializable, ChSalesProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChSalesProjection> map = new HashMap<Serializable, ChSalesProjection>();

		for (Serializable primaryKey : primaryKeys) {
			ChSalesProjection chSalesProjection = fetchByPrimaryKey(primaryKey);

			if (chSalesProjection != null) {
				map.put(primaryKey, chSalesProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the ch sales projections.
	 *
	 * @return the ch sales projections
	 */
	@Override
	public List<ChSalesProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projections
	 * @param end the upper bound of the range of ch sales projections (not inclusive)
	 * @return the range of ch sales projections
	 */
	@Override
	public List<ChSalesProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projections
	 * @param end the upper bound of the range of ch sales projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch sales projections
	 */
	@Override
	public List<ChSalesProjection> findAll(int start, int end,
		OrderByComparator<ChSalesProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projections
	 * @param end the upper bound of the range of ch sales projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch sales projections
	 */
	@Override
	public List<ChSalesProjection> findAll(int start, int end,
		OrderByComparator<ChSalesProjection> orderByComparator,
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

		List<ChSalesProjection> list = null;

		if (retrieveFromCache) {
			list = (List<ChSalesProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHSALESPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHSALESPROJECTION;

				if (pagination) {
					sql = sql.concat(ChSalesProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChSalesProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChSalesProjection>)QueryUtil.list(q,
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
	 * Removes all the ch sales projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChSalesProjection chSalesProjection : findAll()) {
			remove(chSalesProjection);
		}
	}

	/**
	 * Returns the number of ch sales projections.
	 *
	 * @return the number of ch sales projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHSALESPROJECTION);

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
		return ChSalesProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch sales projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChSalesProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHSALESPROJECTION = "SELECT chSalesProjection FROM ChSalesProjection chSalesProjection";
	private static final String _SQL_COUNT_CHSALESPROJECTION = "SELECT COUNT(chSalesProjection) FROM ChSalesProjection chSalesProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chSalesProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChSalesProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChSalesProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"contractUnits", "perOfBusiness", "periodSid", "contractSales",
				"projectionDetailsSid", "gtsSales"
			});
}