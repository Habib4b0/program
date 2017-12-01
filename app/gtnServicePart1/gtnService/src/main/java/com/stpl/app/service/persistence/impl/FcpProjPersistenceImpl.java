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

import com.stpl.app.exception.NoSuchFcpProjException;
import com.stpl.app.model.FcpProj;
import com.stpl.app.model.impl.FcpProjImpl;
import com.stpl.app.model.impl.FcpProjModelImpl;
import com.stpl.app.service.persistence.FcpProjPK;
import com.stpl.app.service.persistence.FcpProjPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the fcp proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FcpProjPersistence
 * @see com.stpl.app.service.persistence.FcpProjUtil
 * @generated
 */
@ProviderType
public class FcpProjPersistenceImpl extends BasePersistenceImpl<FcpProj>
	implements FcpProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FcpProjUtil} to access the fcp proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FcpProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjModelImpl.FINDER_CACHE_ENABLED, FcpProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjModelImpl.FINDER_CACHE_ENABLED, FcpProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public FcpProjPersistenceImpl() {
		setModelClass(FcpProj.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustment", "ADJUSTMENT");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("projectionPrice", "PROJECTION_PRICE");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the fcp proj in the entity cache if it is enabled.
	 *
	 * @param fcpProj the fcp proj
	 */
	@Override
	public void cacheResult(FcpProj fcpProj) {
		entityCache.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjImpl.class, fcpProj.getPrimaryKey(), fcpProj);

		fcpProj.resetOriginalValues();
	}

	/**
	 * Caches the fcp projs in the entity cache if it is enabled.
	 *
	 * @param fcpProjs the fcp projs
	 */
	@Override
	public void cacheResult(List<FcpProj> fcpProjs) {
		for (FcpProj fcpProj : fcpProjs) {
			if (entityCache.getResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
						FcpProjImpl.class, fcpProj.getPrimaryKey()) == null) {
				cacheResult(fcpProj);
			}
			else {
				fcpProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all fcp projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FcpProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the fcp proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FcpProj fcpProj) {
		entityCache.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjImpl.class, fcpProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FcpProj> fcpProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FcpProj fcpProj : fcpProjs) {
			entityCache.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
				FcpProjImpl.class, fcpProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new fcp proj with the primary key. Does not add the fcp proj to the database.
	 *
	 * @param fcpProjPK the primary key for the new fcp proj
	 * @return the new fcp proj
	 */
	@Override
	public FcpProj create(FcpProjPK fcpProjPK) {
		FcpProj fcpProj = new FcpProjImpl();

		fcpProj.setNew(true);
		fcpProj.setPrimaryKey(fcpProjPK);

		return fcpProj;
	}

	/**
	 * Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fcpProjPK the primary key of the fcp proj
	 * @return the fcp proj that was removed
	 * @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj remove(FcpProjPK fcpProjPK) throws NoSuchFcpProjException {
		return remove((Serializable)fcpProjPK);
	}

	/**
	 * Removes the fcp proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fcp proj
	 * @return the fcp proj that was removed
	 * @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj remove(Serializable primaryKey)
		throws NoSuchFcpProjException {
		Session session = null;

		try {
			session = openSession();

			FcpProj fcpProj = (FcpProj)session.get(FcpProjImpl.class, primaryKey);

			if (fcpProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFcpProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(fcpProj);
		}
		catch (NoSuchFcpProjException nsee) {
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
	protected FcpProj removeImpl(FcpProj fcpProj) {
		fcpProj = toUnwrappedModel(fcpProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fcpProj)) {
				fcpProj = (FcpProj)session.get(FcpProjImpl.class,
						fcpProj.getPrimaryKeyObj());
			}

			if (fcpProj != null) {
				session.delete(fcpProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fcpProj != null) {
			clearCache(fcpProj);
		}

		return fcpProj;
	}

	@Override
	public FcpProj updateImpl(FcpProj fcpProj) {
		fcpProj = toUnwrappedModel(fcpProj);

		boolean isNew = fcpProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (fcpProj.isNew()) {
				session.save(fcpProj);

				fcpProj.setNew(false);
			}
			else {
				fcpProj = (FcpProj)session.merge(fcpProj);
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

		entityCache.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
			FcpProjImpl.class, fcpProj.getPrimaryKey(), fcpProj, false);

		fcpProj.resetOriginalValues();

		return fcpProj;
	}

	protected FcpProj toUnwrappedModel(FcpProj fcpProj) {
		if (fcpProj instanceof FcpProjImpl) {
			return fcpProj;
		}

		FcpProjImpl fcpProjImpl = new FcpProjImpl();

		fcpProjImpl.setNew(fcpProj.isNew());
		fcpProjImpl.setPrimaryKey(fcpProj.getPrimaryKey());

		fcpProjImpl.setAdjustment(fcpProj.getAdjustment());
		fcpProjImpl.setPeriodSid(fcpProj.getPeriodSid());
		fcpProjImpl.setPriceType(fcpProj.getPriceType());
		fcpProjImpl.setProjectionPrice(fcpProj.getProjectionPrice());
		fcpProjImpl.setNotes(fcpProj.getNotes());
		fcpProjImpl.setNaProjDetailsSid(fcpProj.getNaProjDetailsSid());

		return fcpProjImpl;
	}

	/**
	 * Returns the fcp proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the fcp proj
	 * @return the fcp proj
	 * @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFcpProjException {
		FcpProj fcpProj = fetchByPrimaryKey(primaryKey);

		if (fcpProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFcpProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return fcpProj;
	}

	/**
	 * Returns the fcp proj with the primary key or throws a {@link NoSuchFcpProjException} if it could not be found.
	 *
	 * @param fcpProjPK the primary key of the fcp proj
	 * @return the fcp proj
	 * @throws NoSuchFcpProjException if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj findByPrimaryKey(FcpProjPK fcpProjPK)
		throws NoSuchFcpProjException {
		return findByPrimaryKey((Serializable)fcpProjPK);
	}

	/**
	 * Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fcp proj
	 * @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
				FcpProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FcpProj fcpProj = (FcpProj)serializable;

		if (fcpProj == null) {
			Session session = null;

			try {
				session = openSession();

				fcpProj = (FcpProj)session.get(FcpProjImpl.class, primaryKey);

				if (fcpProj != null) {
					cacheResult(fcpProj);
				}
				else {
					entityCache.putResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
						FcpProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FcpProjModelImpl.ENTITY_CACHE_ENABLED,
					FcpProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fcpProj;
	}

	/**
	 * Returns the fcp proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fcpProjPK the primary key of the fcp proj
	 * @return the fcp proj, or <code>null</code> if a fcp proj with the primary key could not be found
	 */
	@Override
	public FcpProj fetchByPrimaryKey(FcpProjPK fcpProjPK) {
		return fetchByPrimaryKey((Serializable)fcpProjPK);
	}

	@Override
	public Map<Serializable, FcpProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FcpProj> map = new HashMap<Serializable, FcpProj>();

		for (Serializable primaryKey : primaryKeys) {
			FcpProj fcpProj = fetchByPrimaryKey(primaryKey);

			if (fcpProj != null) {
				map.put(primaryKey, fcpProj);
			}
		}

		return map;
	}

	/**
	 * Returns all the fcp projs.
	 *
	 * @return the fcp projs
	 */
	@Override
	public List<FcpProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fcp projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp projs
	 * @param end the upper bound of the range of fcp projs (not inclusive)
	 * @return the range of fcp projs
	 */
	@Override
	public List<FcpProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fcp projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp projs
	 * @param end the upper bound of the range of fcp projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fcp projs
	 */
	@Override
	public List<FcpProj> findAll(int start, int end,
		OrderByComparator<FcpProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fcp projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FcpProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of fcp projs
	 * @param end the upper bound of the range of fcp projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of fcp projs
	 */
	@Override
	public List<FcpProj> findAll(int start, int end,
		OrderByComparator<FcpProj> orderByComparator, boolean retrieveFromCache) {
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

		List<FcpProj> list = null;

		if (retrieveFromCache) {
			list = (List<FcpProj>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FCPPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FCPPROJ;

				if (pagination) {
					sql = sql.concat(FcpProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FcpProj>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FcpProj>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the fcp projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FcpProj fcpProj : findAll()) {
			remove(fcpProj);
		}
	}

	/**
	 * Returns the number of fcp projs.
	 *
	 * @return the number of fcp projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FCPPROJ);

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
		return FcpProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fcp proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FcpProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FCPPROJ = "SELECT fcpProj FROM FcpProj fcpProj";
	private static final String _SQL_COUNT_FCPPROJ = "SELECT COUNT(fcpProj) FROM FcpProj fcpProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "fcpProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FcpProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(FcpProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustment", "periodSid", "priceType", "projectionPrice",
				"notes", "naProjDetailsSid"
			});
}