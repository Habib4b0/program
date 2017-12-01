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

import com.stpl.app.exception.NoSuchNmPpaProjectionException;
import com.stpl.app.model.NmPpaProjection;
import com.stpl.app.model.impl.NmPpaProjectionImpl;
import com.stpl.app.model.impl.NmPpaProjectionModelImpl;
import com.stpl.app.service.persistence.NmPpaProjectionPK;
import com.stpl.app.service.persistence.NmPpaProjectionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the nm ppa projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionPersistence
 * @see com.stpl.app.service.persistence.NmPpaProjectionUtil
 * @generated
 */
@ProviderType
public class NmPpaProjectionPersistenceImpl extends BasePersistenceImpl<NmPpaProjection>
	implements NmPpaProjectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmPpaProjectionUtil} to access the nm ppa projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmPpaProjectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmPpaProjectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED,
			NmPpaProjectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmPpaProjectionPersistenceImpl() {
		setModelClass(NmPpaProjection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("priceCap", "PRICE_CAP");
			dbColumnNames.put("projectionDiscountUnits",
				"PROJECTION_DISCOUNT_UNITS");
			dbColumnNames.put("projectionDiscountDollar",
				"PROJECTION_DISCOUNT_DOLLAR");
			dbColumnNames.put("reset", "RESET");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");
			dbColumnNames.put("projectionMap", "PROJECTION_MAP");
			dbColumnNames.put("resetPriceCap", "RESET_PRICE_CAP");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the nm ppa projection in the entity cache if it is enabled.
	 *
	 * @param nmPpaProjection the nm ppa projection
	 */
	@Override
	public void cacheResult(NmPpaProjection nmPpaProjection) {
		entityCache.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey(),
			nmPpaProjection);

		nmPpaProjection.resetOriginalValues();
	}

	/**
	 * Caches the nm ppa projections in the entity cache if it is enabled.
	 *
	 * @param nmPpaProjections the nm ppa projections
	 */
	@Override
	public void cacheResult(List<NmPpaProjection> nmPpaProjections) {
		for (NmPpaProjection nmPpaProjection : nmPpaProjections) {
			if (entityCache.getResult(
						NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmPpaProjectionImpl.class,
						nmPpaProjection.getPrimaryKey()) == null) {
				cacheResult(nmPpaProjection);
			}
			else {
				nmPpaProjection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm ppa projections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmPpaProjectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm ppa projection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmPpaProjection nmPpaProjection) {
		entityCache.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmPpaProjection> nmPpaProjections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmPpaProjection nmPpaProjection : nmPpaProjections) {
			entityCache.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm ppa projection with the primary key. Does not add the nm ppa projection to the database.
	 *
	 * @param nmPpaProjectionPK the primary key for the new nm ppa projection
	 * @return the new nm ppa projection
	 */
	@Override
	public NmPpaProjection create(NmPpaProjectionPK nmPpaProjectionPK) {
		NmPpaProjection nmPpaProjection = new NmPpaProjectionImpl();

		nmPpaProjection.setNew(true);
		nmPpaProjection.setPrimaryKey(nmPpaProjectionPK);

		return nmPpaProjection;
	}

	/**
	 * Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nmPpaProjectionPK the primary key of the nm ppa projection
	 * @return the nm ppa projection that was removed
	 * @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection remove(NmPpaProjectionPK nmPpaProjectionPK)
		throws NoSuchNmPpaProjectionException {
		return remove((Serializable)nmPpaProjectionPK);
	}

	/**
	 * Removes the nm ppa projection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm ppa projection
	 * @return the nm ppa projection that was removed
	 * @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection remove(Serializable primaryKey)
		throws NoSuchNmPpaProjectionException {
		Session session = null;

		try {
			session = openSession();

			NmPpaProjection nmPpaProjection = (NmPpaProjection)session.get(NmPpaProjectionImpl.class,
					primaryKey);

			if (nmPpaProjection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmPpaProjection);
		}
		catch (NoSuchNmPpaProjectionException nsee) {
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
	protected NmPpaProjection removeImpl(NmPpaProjection nmPpaProjection) {
		nmPpaProjection = toUnwrappedModel(nmPpaProjection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmPpaProjection)) {
				nmPpaProjection = (NmPpaProjection)session.get(NmPpaProjectionImpl.class,
						nmPpaProjection.getPrimaryKeyObj());
			}

			if (nmPpaProjection != null) {
				session.delete(nmPpaProjection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmPpaProjection != null) {
			clearCache(nmPpaProjection);
		}

		return nmPpaProjection;
	}

	@Override
	public NmPpaProjection updateImpl(NmPpaProjection nmPpaProjection) {
		nmPpaProjection = toUnwrappedModel(nmPpaProjection);

		boolean isNew = nmPpaProjection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmPpaProjection.isNew()) {
				session.save(nmPpaProjection);

				nmPpaProjection.setNew(false);
			}
			else {
				nmPpaProjection = (NmPpaProjection)session.merge(nmPpaProjection);
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

		entityCache.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionImpl.class, nmPpaProjection.getPrimaryKey(),
			nmPpaProjection, false);

		nmPpaProjection.resetOriginalValues();

		return nmPpaProjection;
	}

	protected NmPpaProjection toUnwrappedModel(NmPpaProjection nmPpaProjection) {
		if (nmPpaProjection instanceof NmPpaProjectionImpl) {
			return nmPpaProjection;
		}

		NmPpaProjectionImpl nmPpaProjectionImpl = new NmPpaProjectionImpl();

		nmPpaProjectionImpl.setNew(nmPpaProjection.isNew());
		nmPpaProjectionImpl.setPrimaryKey(nmPpaProjection.getPrimaryKey());

		nmPpaProjectionImpl.setPeriodSid(nmPpaProjection.getPeriodSid());
		nmPpaProjectionImpl.setProjectionRate(nmPpaProjection.getProjectionRate());
		nmPpaProjectionImpl.setProjectionDetailsSid(nmPpaProjection.getProjectionDetailsSid());
		nmPpaProjectionImpl.setPriceCap(nmPpaProjection.getPriceCap());
		nmPpaProjectionImpl.setProjectionDiscountUnits(nmPpaProjection.getProjectionDiscountUnits());
		nmPpaProjectionImpl.setProjectionDiscountDollar(nmPpaProjection.getProjectionDiscountDollar());
		nmPpaProjectionImpl.setReset(nmPpaProjection.isReset());
		nmPpaProjectionImpl.setProjectionSales(nmPpaProjection.getProjectionSales());
		nmPpaProjectionImpl.setProjectionMap(nmPpaProjection.getProjectionMap());
		nmPpaProjectionImpl.setResetPriceCap(nmPpaProjection.isResetPriceCap());

		return nmPpaProjectionImpl;
	}

	/**
	 * Returns the nm ppa projection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm ppa projection
	 * @return the nm ppa projection
	 * @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmPpaProjectionException {
		NmPpaProjection nmPpaProjection = fetchByPrimaryKey(primaryKey);

		if (nmPpaProjection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmPpaProjectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmPpaProjection;
	}

	/**
	 * Returns the nm ppa projection with the primary key or throws a {@link NoSuchNmPpaProjectionException} if it could not be found.
	 *
	 * @param nmPpaProjectionPK the primary key of the nm ppa projection
	 * @return the nm ppa projection
	 * @throws NoSuchNmPpaProjectionException if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection findByPrimaryKey(NmPpaProjectionPK nmPpaProjectionPK)
		throws NoSuchNmPpaProjectionException {
		return findByPrimaryKey((Serializable)nmPpaProjectionPK);
	}

	/**
	 * Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm ppa projection
	 * @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
				NmPpaProjectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmPpaProjection nmPpaProjection = (NmPpaProjection)serializable;

		if (nmPpaProjection == null) {
			Session session = null;

			try {
				session = openSession();

				nmPpaProjection = (NmPpaProjection)session.get(NmPpaProjectionImpl.class,
						primaryKey);

				if (nmPpaProjection != null) {
					cacheResult(nmPpaProjection);
				}
				else {
					entityCache.putResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
						NmPpaProjectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmPpaProjectionModelImpl.ENTITY_CACHE_ENABLED,
					NmPpaProjectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmPpaProjection;
	}

	/**
	 * Returns the nm ppa projection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nmPpaProjectionPK the primary key of the nm ppa projection
	 * @return the nm ppa projection, or <code>null</code> if a nm ppa projection with the primary key could not be found
	 */
	@Override
	public NmPpaProjection fetchByPrimaryKey(
		NmPpaProjectionPK nmPpaProjectionPK) {
		return fetchByPrimaryKey((Serializable)nmPpaProjectionPK);
	}

	@Override
	public Map<Serializable, NmPpaProjection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmPpaProjection> map = new HashMap<Serializable, NmPpaProjection>();

		for (Serializable primaryKey : primaryKeys) {
			NmPpaProjection nmPpaProjection = fetchByPrimaryKey(primaryKey);

			if (nmPpaProjection != null) {
				map.put(primaryKey, nmPpaProjection);
			}
		}

		return map;
	}

	/**
	 * Returns all the nm ppa projections.
	 *
	 * @return the nm ppa projections
	 */
	@Override
	public List<NmPpaProjection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projections
	 * @param end the upper bound of the range of nm ppa projections (not inclusive)
	 * @return the range of nm ppa projections
	 */
	@Override
	public List<NmPpaProjection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projections
	 * @param end the upper bound of the range of nm ppa projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm ppa projections
	 */
	@Override
	public List<NmPpaProjection> findAll(int start, int end,
		OrderByComparator<NmPpaProjection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm ppa projections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projections
	 * @param end the upper bound of the range of nm ppa projections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm ppa projections
	 */
	@Override
	public List<NmPpaProjection> findAll(int start, int end,
		OrderByComparator<NmPpaProjection> orderByComparator,
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

		List<NmPpaProjection> list = null;

		if (retrieveFromCache) {
			list = (List<NmPpaProjection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMPPAPROJECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMPPAPROJECTION;

				if (pagination) {
					sql = sql.concat(NmPpaProjectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmPpaProjection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmPpaProjection>)QueryUtil.list(q,
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
	 * Removes all the nm ppa projections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmPpaProjection nmPpaProjection : findAll()) {
			remove(nmPpaProjection);
		}
	}

	/**
	 * Returns the number of nm ppa projections.
	 *
	 * @return the number of nm ppa projections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMPPAPROJECTION);

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
		return NmPpaProjectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm ppa projection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmPpaProjectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMPPAPROJECTION = "SELECT nmPpaProjection FROM NmPpaProjection nmPpaProjection";
	private static final String _SQL_COUNT_NMPPAPROJECTION = "SELECT COUNT(nmPpaProjection) FROM NmPpaProjection nmPpaProjection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmPpaProjection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmPpaProjection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmPpaProjectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "projectionRate", "projectionDetailsSid",
				"priceCap", "projectionDiscountUnits",
				"projectionDiscountDollar", "reset", "projectionSales",
				"projectionMap", "resetPriceCap"
			});
}