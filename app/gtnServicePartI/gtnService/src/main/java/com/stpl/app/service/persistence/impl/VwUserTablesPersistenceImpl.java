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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchVwUserTablesException;
import com.stpl.app.model.VwUserTables;
import com.stpl.app.model.impl.VwUserTablesImpl;
import com.stpl.app.model.impl.VwUserTablesModelImpl;
import com.stpl.app.service.persistence.VwUserTablesPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the vw user tables service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwUserTablesPersistence
 * @see com.stpl.app.service.persistence.VwUserTablesUtil
 * @generated
 */
@ProviderType
public class VwUserTablesPersistenceImpl extends BasePersistenceImpl<VwUserTables>
	implements VwUserTablesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwUserTablesUtil} to access the vw user tables persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwUserTablesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesModelImpl.FINDER_CACHE_ENABLED, VwUserTablesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesModelImpl.FINDER_CACHE_ENABLED, VwUserTablesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwUserTablesPersistenceImpl() {
		setModelClass(VwUserTables.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uniqueId", "UNIQUE_ID");
			dbColumnNames.put("tableName", "TABLE_NAME");
			dbColumnNames.put("columnName", "COLUMN_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw user tables in the entity cache if it is enabled.
	 *
	 * @param vwUserTables the vw user tables
	 */
	@Override
	public void cacheResult(VwUserTables vwUserTables) {
		entityCache.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesImpl.class, vwUserTables.getPrimaryKey(), vwUserTables);

		vwUserTables.resetOriginalValues();
	}

	/**
	 * Caches the vw user tableses in the entity cache if it is enabled.
	 *
	 * @param vwUserTableses the vw user tableses
	 */
	@Override
	public void cacheResult(List<VwUserTables> vwUserTableses) {
		for (VwUserTables vwUserTables : vwUserTableses) {
			if (entityCache.getResult(
						VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
						VwUserTablesImpl.class, vwUserTables.getPrimaryKey()) == null) {
				cacheResult(vwUserTables);
			}
			else {
				vwUserTables.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw user tableses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwUserTablesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw user tables.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwUserTables vwUserTables) {
		entityCache.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesImpl.class, vwUserTables.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwUserTables> vwUserTableses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwUserTables vwUserTables : vwUserTableses) {
			entityCache.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
				VwUserTablesImpl.class, vwUserTables.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw user tables with the primary key. Does not add the vw user tables to the database.
	 *
	 * @param uniqueId the primary key for the new vw user tables
	 * @return the new vw user tables
	 */
	@Override
	public VwUserTables create(int uniqueId) {
		VwUserTables vwUserTables = new VwUserTablesImpl();

		vwUserTables.setNew(true);
		vwUserTables.setPrimaryKey(uniqueId);

		return vwUserTables;
	}

	/**
	 * Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param uniqueId the primary key of the vw user tables
	 * @return the vw user tables that was removed
	 * @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables remove(int uniqueId) throws NoSuchVwUserTablesException {
		return remove((Serializable)uniqueId);
	}

	/**
	 * Removes the vw user tables with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw user tables
	 * @return the vw user tables that was removed
	 * @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables remove(Serializable primaryKey)
		throws NoSuchVwUserTablesException {
		Session session = null;

		try {
			session = openSession();

			VwUserTables vwUserTables = (VwUserTables)session.get(VwUserTablesImpl.class,
					primaryKey);

			if (vwUserTables == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwUserTablesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwUserTables);
		}
		catch (NoSuchVwUserTablesException nsee) {
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
	protected VwUserTables removeImpl(VwUserTables vwUserTables) {
		vwUserTables = toUnwrappedModel(vwUserTables);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwUserTables)) {
				vwUserTables = (VwUserTables)session.get(VwUserTablesImpl.class,
						vwUserTables.getPrimaryKeyObj());
			}

			if (vwUserTables != null) {
				session.delete(vwUserTables);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwUserTables != null) {
			clearCache(vwUserTables);
		}

		return vwUserTables;
	}

	@Override
	public VwUserTables updateImpl(VwUserTables vwUserTables) {
		vwUserTables = toUnwrappedModel(vwUserTables);

		boolean isNew = vwUserTables.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwUserTables.isNew()) {
				session.save(vwUserTables);

				vwUserTables.setNew(false);
			}
			else {
				vwUserTables = (VwUserTables)session.merge(vwUserTables);
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

		entityCache.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
			VwUserTablesImpl.class, vwUserTables.getPrimaryKey(), vwUserTables,
			false);

		vwUserTables.resetOriginalValues();

		return vwUserTables;
	}

	protected VwUserTables toUnwrappedModel(VwUserTables vwUserTables) {
		if (vwUserTables instanceof VwUserTablesImpl) {
			return vwUserTables;
		}

		VwUserTablesImpl vwUserTablesImpl = new VwUserTablesImpl();

		vwUserTablesImpl.setNew(vwUserTables.isNew());
		vwUserTablesImpl.setPrimaryKey(vwUserTables.getPrimaryKey());

		vwUserTablesImpl.setUniqueId(vwUserTables.getUniqueId());
		vwUserTablesImpl.setTableName(vwUserTables.getTableName());
		vwUserTablesImpl.setColumnName(vwUserTables.getColumnName());

		return vwUserTablesImpl;
	}

	/**
	 * Returns the vw user tables with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw user tables
	 * @return the vw user tables
	 * @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwUserTablesException {
		VwUserTables vwUserTables = fetchByPrimaryKey(primaryKey);

		if (vwUserTables == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwUserTablesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwUserTables;
	}

	/**
	 * Returns the vw user tables with the primary key or throws a {@link NoSuchVwUserTablesException} if it could not be found.
	 *
	 * @param uniqueId the primary key of the vw user tables
	 * @return the vw user tables
	 * @throws NoSuchVwUserTablesException if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables findByPrimaryKey(int uniqueId)
		throws NoSuchVwUserTablesException {
		return findByPrimaryKey((Serializable)uniqueId);
	}

	/**
	 * Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw user tables
	 * @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
				VwUserTablesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwUserTables vwUserTables = (VwUserTables)serializable;

		if (vwUserTables == null) {
			Session session = null;

			try {
				session = openSession();

				vwUserTables = (VwUserTables)session.get(VwUserTablesImpl.class,
						primaryKey);

				if (vwUserTables != null) {
					cacheResult(vwUserTables);
				}
				else {
					entityCache.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
						VwUserTablesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
					VwUserTablesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwUserTables;
	}

	/**
	 * Returns the vw user tables with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param uniqueId the primary key of the vw user tables
	 * @return the vw user tables, or <code>null</code> if a vw user tables with the primary key could not be found
	 */
	@Override
	public VwUserTables fetchByPrimaryKey(int uniqueId) {
		return fetchByPrimaryKey((Serializable)uniqueId);
	}

	@Override
	public Map<Serializable, VwUserTables> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwUserTables> map = new HashMap<Serializable, VwUserTables>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwUserTables vwUserTables = fetchByPrimaryKey(primaryKey);

			if (vwUserTables != null) {
				map.put(primaryKey, vwUserTables);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
					VwUserTablesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwUserTables)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWUSERTABLES_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (VwUserTables vwUserTables : (List<VwUserTables>)q.list()) {
				map.put(vwUserTables.getPrimaryKeyObj(), vwUserTables);

				cacheResult(vwUserTables);

				uncachedPrimaryKeys.remove(vwUserTables.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwUserTablesModelImpl.ENTITY_CACHE_ENABLED,
					VwUserTablesImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the vw user tableses.
	 *
	 * @return the vw user tableses
	 */
	@Override
	public List<VwUserTables> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw user tableses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw user tableses
	 * @param end the upper bound of the range of vw user tableses (not inclusive)
	 * @return the range of vw user tableses
	 */
	@Override
	public List<VwUserTables> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw user tableses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw user tableses
	 * @param end the upper bound of the range of vw user tableses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw user tableses
	 */
	@Override
	public List<VwUserTables> findAll(int start, int end,
		OrderByComparator<VwUserTables> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw user tableses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwUserTablesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw user tableses
	 * @param end the upper bound of the range of vw user tableses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw user tableses
	 */
	@Override
	public List<VwUserTables> findAll(int start, int end,
		OrderByComparator<VwUserTables> orderByComparator,
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

		List<VwUserTables> list = null;

		if (retrieveFromCache) {
			list = (List<VwUserTables>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWUSERTABLES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWUSERTABLES;

				if (pagination) {
					sql = sql.concat(VwUserTablesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwUserTables>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwUserTables>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the vw user tableses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwUserTables vwUserTables : findAll()) {
			remove(vwUserTables);
		}
	}

	/**
	 * Returns the number of vw user tableses.
	 *
	 * @return the number of vw user tableses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWUSERTABLES);

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
		return VwUserTablesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw user tables persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwUserTablesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWUSERTABLES = "SELECT vwUserTables FROM VwUserTables vwUserTables";
	private static final String _SQL_SELECT_VWUSERTABLES_WHERE_PKS_IN = "SELECT vwUserTables FROM VwUserTables vwUserTables WHERE UNIQUE_ID IN (";
	private static final String _SQL_COUNT_VWUSERTABLES = "SELECT COUNT(vwUserTables) FROM VwUserTables vwUserTables";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwUserTables.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwUserTables exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwUserTablesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uniqueId", "tableName", "columnName"
			});
}