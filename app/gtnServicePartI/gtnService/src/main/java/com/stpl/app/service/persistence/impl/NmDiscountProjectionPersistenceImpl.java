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

import com.stpl.app.exception.NoSuchNmDiscountProjectionException;
import com.stpl.app.model.NmDiscountProjection;
import com.stpl.app.model.impl.NmDiscountProjectionImpl;
import com.stpl.app.model.impl.NmDiscountProjectionModelImpl;
import com.stpl.app.service.persistence.NmDiscountProjectionPK;
import com.stpl.app.service.persistence.NmDiscountProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjectionPersistence
 * @see com.stpl.app.service.persistence.NmDiscountProjectionUtil
 * @generated
 */
@ProviderType
public class NmDiscountProjectionPersistenceImpl extends BasePersistenceImpl<NmDiscountProjection>
	implements NmDiscountProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmDiscountProjectionUtil} to access the nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmDiscountProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmDiscountProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmDiscountProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmDiscountProjectionPersistenceImpl() {
		setModelClass(NmDiscountProjection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
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
	 * Caches the nm discount projection in the entity cache if it is enabled.
	 *
	 * @param nmDiscountProjection the nm discount projection
	 */
	@Override
	public void cacheResult(NmDiscountProjection nmDiscountProjection) {
		entityCache.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionImpl.class,
			nmDiscountProjection.getPrimaryKey(), nmDiscountProjection);

		nmDiscountProjection.resetOriginalValues();
	}

	/**
	 * Caches the nm discount projections in the entity cache if it is enabled.
	 *
	 * @param nmDiscountProjections the nm discount projections
	 */
	@Override
	public void cacheResult(List<NmDiscountProjection> nmDiscountProjections) {
		for (NmDiscountProjection nmDiscountProjection : nmDiscountProjections) {
			if (entityCache.getResult(
						NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmDiscountProjectionImpl.class,
						nmDiscountProjection.getPrimaryKey()) == null) {
				cacheResult(nmDiscountProjection);
			}
			else {
				nmDiscountProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm discount projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmDiscountProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm discount projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmDiscountProjection nmDiscountProjection) {
		entityCache.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionImpl.class, nmDiscountProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmDiscountProjection> nmDiscountProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmDiscountProjection nmDiscountProjection : nmDiscountProjections) {
			entityCache.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmDiscountProjectionImpl.class,
				nmDiscountProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
	 *
	 * @param nmDiscountProjectionPK the primary key for the new nm discount projection
	 * @return the new nm discount projection
	 */
	@Override
	public NmDiscountProjection create(
		NmDiscountProjectionPK nmDiscountProjectionPK) {
		NmDiscountProjection nmDiscountProjection = new NmDiscountProjectionImpl();

		nmDiscountProjection.setNew(true);
		nmDiscountProjection.setPrimaryKey(nmDiscountProjectionPK);

		return nmDiscountProjection;
	}

	/**
	 * Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmDiscountProjectionPK the primary key of the nm discount projection
	 * @return the nm discount projection that was removed
	 * @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection remove(
		NmDiscountProjectionPK nmDiscountProjectionPK)
		throws NoSuchNmDiscountProjectionException {
		return remove((Serializable)nmDiscountProjectionPK);
	}

	/**
	 * Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm discount projection
	 * @return the nm discount projection that was removed
	 * @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection remove(Serializable primaryKey)
		throws NoSuchNmDiscountProjectionException {
		Session session = null;

		try {
			session = openSession();

			NmDiscountProjection nmDiscountProjection = (NmDiscountProjection)session.get(NmDiscountProjectionImpl.class,
					primaryKey);

			if (nmDiscountProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmDiscountProjection);
		}
		catch (NoSuchNmDiscountProjectionException nsee) {
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
	protected NmDiscountProjection removeImpl(
		NmDiscountProjection nmDiscountProjection) {
		nmDiscountProjection = toUnwrappedModel(nmDiscountProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmDiscountProjection)) {
				nmDiscountProjection = (NmDiscountProjection)session.get(NmDiscountProjectionImpl.class,
						nmDiscountProjection.getPrimaryKeyObj());
			}

			if (nmDiscountProjection != null) {
				session.delete(nmDiscountProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmDiscountProjection != null) {
			clearCache(nmDiscountProjection);
		}

		return nmDiscountProjection;
	}

	@Override
	public NmDiscountProjection updateImpl(
		NmDiscountProjection nmDiscountProjection) {
		nmDiscountProjection = toUnwrappedModel(nmDiscountProjection);

		boolean isNew = nmDiscountProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmDiscountProjection.isNew()) {
				session.save(nmDiscountProjection);

				nmDiscountProjection.setNew(false);
			}
			else {
				nmDiscountProjection = (NmDiscountProjection)session.merge(nmDiscountProjection);
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

		entityCache.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjectionImpl.class,
			nmDiscountProjection.getPrimaryKey(), nmDiscountProjection, false);

		nmDiscountProjection.resetOriginalValues();

		return nmDiscountProjection;
	}

	protected NmDiscountProjection toUnwrappedModel(
		NmDiscountProjection nmDiscountProjection) {
		if (nmDiscountProjection instanceof NmDiscountProjectionImpl) {
			return nmDiscountProjection;
		}

		NmDiscountProjectionImpl nmDiscountProjectionImpl = new NmDiscountProjectionImpl();

		nmDiscountProjectionImpl.setNew(nmDiscountProjection.isNew());
		nmDiscountProjectionImpl.setPrimaryKey(nmDiscountProjection.getPrimaryKey());

		nmDiscountProjectionImpl.setPeriodSid(nmDiscountProjection.getPeriodSid());
		nmDiscountProjectionImpl.setProjectionRate(nmDiscountProjection.getProjectionRate());
		nmDiscountProjectionImpl.setProjectionDetailsSid(nmDiscountProjection.getProjectionDetailsSid());
		nmDiscountProjectionImpl.setProjectionSales(nmDiscountProjection.getProjectionSales());

		return nmDiscountProjectionImpl;
	}

	/**
	 * Returns the nm discount projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm discount projection
	 * @return the nm discount projection
	 * @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmDiscountProjectionException {
		NmDiscountProjection nmDiscountProjection = fetchByPrimaryKey(primaryKey);

		if (nmDiscountProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmDiscountProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmDiscountProjection;
	}

	/**
	 * Returns the nm discount projection with the primary key or throws a {@link NoSuchNmDiscountProjectionException} if it could not be found.
	 *
	 * @param nmDiscountProjectionPK the primary key of the nm discount projection
	 * @return the nm discount projection
	 * @throws NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection findByPrimaryKey(
		NmDiscountProjectionPK nmDiscountProjectionPK)
		throws NoSuchNmDiscountProjectionException {
		return findByPrimaryKey((Serializable)nmDiscountProjectionPK);
	}

	/**
	 * Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm discount projection
	 * @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmDiscountProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmDiscountProjection nmDiscountProjection = (NmDiscountProjection)serializable;

		if (nmDiscountProjection == null) {
			Session session = null;

			try {
				session = openSession();

				nmDiscountProjection = (NmDiscountProjection)session.get(NmDiscountProjectionImpl.class,
						primaryKey);

				if (nmDiscountProjection != null) {
					cacheResult(nmDiscountProjection);
				}
				else {
					entityCache.putResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmDiscountProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmDiscountProjectionModelImpl.ENTITY_CACHE_ENABLED,
					NmDiscountProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmDiscountProjection;
	}

	/**
	 * Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmDiscountProjectionPK the primary key of the nm discount projection
	 * @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
	 */
	@Override
	public NmDiscountProjection fetchByPrimaryKey(
		NmDiscountProjectionPK nmDiscountProjectionPK) {
		return fetchByPrimaryKey((Serializable)nmDiscountProjectionPK);
	}

	@Override
	public Map<Serializable, NmDiscountProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmDiscountProjection> map = new HashMap<Serializable, NmDiscountProjection>();

		for (Serializable primaryKey : primaryKeys) {
			NmDiscountProjection nmDiscountProjection = fetchByPrimaryKey(primaryKey);

			if (nmDiscountProjection != null) {
				map.put(primaryKey, nmDiscountProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the nm discount projections.
	 *
	 * @return the nm discount projections
	 */
	@Override
	public List<NmDiscountProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount projections
	 * @param end the upper bound of the range of nm discount projections (not inclusive)
	 * @return the range of nm discount projections
	 */
	@Override
	public List<NmDiscountProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount projections
	 * @param end the upper bound of the range of nm discount projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm discount projections
	 */
	@Override
	public List<NmDiscountProjection> findAll(int start, int end,
		OrderByComparator<NmDiscountProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm discount projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount projections
	 * @param end the upper bound of the range of nm discount projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm discount projections
	 */
	@Override
	public List<NmDiscountProjection> findAll(int start, int end,
		OrderByComparator<NmDiscountProjection> orderByComparator,
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

		List<NmDiscountProjection> list = null;

		if (retrieveFromCache) {
			list = (List<NmDiscountProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMDISCOUNTPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMDISCOUNTPROJECTION;

				if (pagination) {
					sql = sql.concat(NmDiscountProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmDiscountProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmDiscountProjection>)QueryUtil.list(q,
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
	 * Removes all the nm discount projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmDiscountProjection nmDiscountProjection : findAll()) {
			remove(nmDiscountProjection);
		}
	}

	/**
	 * Returns the number of nm discount projections.
	 *
	 * @return the number of nm discount projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMDISCOUNTPROJECTION);

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
		return NmDiscountProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm discount projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmDiscountProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMDISCOUNTPROJECTION = "SELECT nmDiscountProjection FROM NmDiscountProjection nmDiscountProjection";
	private static final String _SQL_COUNT_NMDISCOUNTPROJECTION = "SELECT COUNT(nmDiscountProjection) FROM NmDiscountProjection nmDiscountProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmDiscountProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmDiscountProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmDiscountProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "projectionRate", "projectionDetailsSid",
				"projectionSales"
			});
}