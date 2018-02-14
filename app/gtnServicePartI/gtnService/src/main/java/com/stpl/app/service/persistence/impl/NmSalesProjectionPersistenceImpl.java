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

import com.stpl.app.exception.NoSuchNmSalesProjectionException;
import com.stpl.app.model.NmSalesProjection;
import com.stpl.app.model.impl.NmSalesProjectionImpl;
import com.stpl.app.model.impl.NmSalesProjectionModelImpl;
import com.stpl.app.service.persistence.NmSalesProjectionPK;
import com.stpl.app.service.persistence.NmSalesProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the nm sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionPersistence
 * @see com.stpl.app.service.persistence.NmSalesProjectionUtil
 * @generated
 */
@ProviderType
public class NmSalesProjectionPersistenceImpl extends BasePersistenceImpl<NmSalesProjection>
	implements NmSalesProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmSalesProjectionUtil} to access the nm sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmSalesProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmSalesProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmSalesProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmSalesProjectionPersistenceImpl() {
		setModelClass(NmSalesProjection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("productGrowth", "PRODUCT_GROWTH");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("accountGrowth", "ACCOUNT_GROWTH");
			dbColumnNames.put("projectionUnits", "PROJECTION_UNITS");
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
	 * Caches the nm sales projection in the entity cache if it is enabled.
	 *
	 * @param nmSalesProjection the nm sales projection
	 */
	@Override
	public void cacheResult(NmSalesProjection nmSalesProjection) {
		entityCache.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey(),
			nmSalesProjection);

		nmSalesProjection.resetOriginalValues();
	}

	/**
	 * Caches the nm sales projections in the entity cache if it is enabled.
	 *
	 * @param nmSalesProjections the nm sales projections
	 */
	@Override
	public void cacheResult(List<NmSalesProjection> nmSalesProjections) {
		for (NmSalesProjection nmSalesProjection : nmSalesProjections) {
			if (entityCache.getResult(
						NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmSalesProjectionImpl.class,
						nmSalesProjection.getPrimaryKey()) == null) {
				cacheResult(nmSalesProjection);
			}
			else {
				nmSalesProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm sales projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmSalesProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm sales projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmSalesProjection nmSalesProjection) {
		entityCache.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmSalesProjection> nmSalesProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmSalesProjection nmSalesProjection : nmSalesProjections) {
			entityCache.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
	 *
	 * @param nmSalesProjectionPK the primary key for the new nm sales projection
	 * @return the new nm sales projection
	 */
	@Override
	public NmSalesProjection create(NmSalesProjectionPK nmSalesProjectionPK) {
		NmSalesProjection nmSalesProjection = new NmSalesProjectionImpl();

		nmSalesProjection.setNew(true);
		nmSalesProjection.setPrimaryKey(nmSalesProjectionPK);

		return nmSalesProjection;
	}

	/**
	 * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmSalesProjectionPK the primary key of the nm sales projection
	 * @return the nm sales projection that was removed
	 * @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection remove(NmSalesProjectionPK nmSalesProjectionPK)
		throws NoSuchNmSalesProjectionException {
		return remove((Serializable)nmSalesProjectionPK);
	}

	/**
	 * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm sales projection
	 * @return the nm sales projection that was removed
	 * @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection remove(Serializable primaryKey)
		throws NoSuchNmSalesProjectionException {
		Session session = null;

		try {
			session = openSession();

			NmSalesProjection nmSalesProjection = (NmSalesProjection)session.get(NmSalesProjectionImpl.class,
					primaryKey);

			if (nmSalesProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmSalesProjection);
		}
		catch (NoSuchNmSalesProjectionException nsee) {
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
	protected NmSalesProjection removeImpl(NmSalesProjection nmSalesProjection) {
		nmSalesProjection = toUnwrappedModel(nmSalesProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmSalesProjection)) {
				nmSalesProjection = (NmSalesProjection)session.get(NmSalesProjectionImpl.class,
						nmSalesProjection.getPrimaryKeyObj());
			}

			if (nmSalesProjection != null) {
				session.delete(nmSalesProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmSalesProjection != null) {
			clearCache(nmSalesProjection);
		}

		return nmSalesProjection;
	}

	@Override
	public NmSalesProjection updateImpl(NmSalesProjection nmSalesProjection) {
		nmSalesProjection = toUnwrappedModel(nmSalesProjection);

		boolean isNew = nmSalesProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmSalesProjection.isNew()) {
				session.save(nmSalesProjection);

				nmSalesProjection.setNew(false);
			}
			else {
				nmSalesProjection = (NmSalesProjection)session.merge(nmSalesProjection);
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

		entityCache.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionImpl.class, nmSalesProjection.getPrimaryKey(),
			nmSalesProjection, false);

		nmSalesProjection.resetOriginalValues();

		return nmSalesProjection;
	}

	protected NmSalesProjection toUnwrappedModel(
		NmSalesProjection nmSalesProjection) {
		if (nmSalesProjection instanceof NmSalesProjectionImpl) {
			return nmSalesProjection;
		}

		NmSalesProjectionImpl nmSalesProjectionImpl = new NmSalesProjectionImpl();

		nmSalesProjectionImpl.setNew(nmSalesProjection.isNew());
		nmSalesProjectionImpl.setPrimaryKey(nmSalesProjection.getPrimaryKey());

		nmSalesProjectionImpl.setPeriodSid(nmSalesProjection.getPeriodSid());
		nmSalesProjectionImpl.setProductGrowth(nmSalesProjection.getProductGrowth());
		nmSalesProjectionImpl.setProjectionDetailsSid(nmSalesProjection.getProjectionDetailsSid());
		nmSalesProjectionImpl.setAccountGrowth(nmSalesProjection.getAccountGrowth());
		nmSalesProjectionImpl.setProjectionUnits(nmSalesProjection.getProjectionUnits());
		nmSalesProjectionImpl.setProjectionSales(nmSalesProjection.getProjectionSales());

		return nmSalesProjectionImpl;
	}

	/**
	 * Returns the nm sales projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm sales projection
	 * @return the nm sales projection
	 * @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmSalesProjectionException {
		NmSalesProjection nmSalesProjection = fetchByPrimaryKey(primaryKey);

		if (nmSalesProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmSalesProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmSalesProjection;
	}

	/**
	 * Returns the nm sales projection with the primary key or throws a {@link NoSuchNmSalesProjectionException} if it could not be found.
	 *
	 * @param nmSalesProjectionPK the primary key of the nm sales projection
	 * @return the nm sales projection
	 * @throws NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection findByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK)
		throws NoSuchNmSalesProjectionException {
		return findByPrimaryKey((Serializable)nmSalesProjectionPK);
	}

	/**
	 * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm sales projection
	 * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmSalesProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmSalesProjection nmSalesProjection = (NmSalesProjection)serializable;

		if (nmSalesProjection == null) {
			Session session = null;

			try {
				session = openSession();

				nmSalesProjection = (NmSalesProjection)session.get(NmSalesProjectionImpl.class,
						primaryKey);

				if (nmSalesProjection != null) {
					cacheResult(nmSalesProjection);
				}
				else {
					entityCache.putResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmSalesProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmSalesProjectionModelImpl.ENTITY_CACHE_ENABLED,
					NmSalesProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmSalesProjection;
	}

	/**
	 * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmSalesProjectionPK the primary key of the nm sales projection
	 * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
	 */
	@Override
	public NmSalesProjection fetchByPrimaryKey(
		NmSalesProjectionPK nmSalesProjectionPK) {
		return fetchByPrimaryKey((Serializable)nmSalesProjectionPK);
	}

	@Override
	public Map<Serializable, NmSalesProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmSalesProjection> map = new HashMap<Serializable, NmSalesProjection>();

		for (Serializable primaryKey : primaryKeys) {
			NmSalesProjection nmSalesProjection = fetchByPrimaryKey(primaryKey);

			if (nmSalesProjection != null) {
				map.put(primaryKey, nmSalesProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the nm sales projections.
	 *
	 * @return the nm sales projections
	 */
	@Override
	public List<NmSalesProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projections
	 * @param end the upper bound of the range of nm sales projections (not inclusive)
	 * @return the range of nm sales projections
	 */
	@Override
	public List<NmSalesProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projections
	 * @param end the upper bound of the range of nm sales projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm sales projections
	 */
	@Override
	public List<NmSalesProjection> findAll(int start, int end,
		OrderByComparator<NmSalesProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm sales projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projections
	 * @param end the upper bound of the range of nm sales projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm sales projections
	 */
	@Override
	public List<NmSalesProjection> findAll(int start, int end,
		OrderByComparator<NmSalesProjection> orderByComparator,
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

		List<NmSalesProjection> list = null;

		if (retrieveFromCache) {
			list = (List<NmSalesProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMSALESPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMSALESPROJECTION;

				if (pagination) {
					sql = sql.concat(NmSalesProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmSalesProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmSalesProjection>)QueryUtil.list(q,
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
	 * Removes all the nm sales projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmSalesProjection nmSalesProjection : findAll()) {
			remove(nmSalesProjection);
		}
	}

	/**
	 * Returns the number of nm sales projections.
	 *
	 * @return the number of nm sales projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMSALESPROJECTION);

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
		return NmSalesProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm sales projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmSalesProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMSALESPROJECTION = "SELECT nmSalesProjection FROM NmSalesProjection nmSalesProjection";
	private static final String _SQL_COUNT_NMSALESPROJECTION = "SELECT COUNT(nmSalesProjection) FROM NmSalesProjection nmSalesProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmSalesProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmSalesProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmSalesProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "productGrowth", "projectionDetailsSid",
				"accountGrowth", "projectionUnits", "projectionSales"
			});
}