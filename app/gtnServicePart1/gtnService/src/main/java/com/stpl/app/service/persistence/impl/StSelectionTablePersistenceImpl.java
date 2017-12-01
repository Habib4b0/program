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

import com.stpl.app.exception.NoSuchStSelectionTableException;
import com.stpl.app.model.StSelectionTable;
import com.stpl.app.model.impl.StSelectionTableImpl;
import com.stpl.app.model.impl.StSelectionTableModelImpl;
import com.stpl.app.service.persistence.StSelectionTablePK;
import com.stpl.app.service.persistence.StSelectionTablePersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st selection table service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StSelectionTablePersistence
 * @see com.stpl.app.service.persistence.StSelectionTableUtil
 * @generated
 */
@ProviderType
public class StSelectionTablePersistenceImpl extends BasePersistenceImpl<StSelectionTable>
	implements StSelectionTablePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StSelectionTableUtil} to access the st selection table persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StSelectionTableImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableModelImpl.FINDER_CACHE_ENABLED,
			StSelectionTableImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableModelImpl.FINDER_CACHE_ENABLED,
			StSelectionTableImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StSelectionTablePersistenceImpl() {
		setModelClass(StSelectionTable.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("selectionType", "SELECTION_TYPE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("companyItemSid", "COMPANY_ITEM_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st selection table in the entity cache if it is enabled.
	 *
	 * @param stSelectionTable the st selection table
	 */
	@Override
	public void cacheResult(StSelectionTable stSelectionTable) {
		entityCache.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableImpl.class, stSelectionTable.getPrimaryKey(),
			stSelectionTable);

		stSelectionTable.resetOriginalValues();
	}

	/**
	 * Caches the st selection tables in the entity cache if it is enabled.
	 *
	 * @param stSelectionTables the st selection tables
	 */
	@Override
	public void cacheResult(List<StSelectionTable> stSelectionTables) {
		for (StSelectionTable stSelectionTable : stSelectionTables) {
			if (entityCache.getResult(
						StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
						StSelectionTableImpl.class,
						stSelectionTable.getPrimaryKey()) == null) {
				cacheResult(stSelectionTable);
			}
			else {
				stSelectionTable.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st selection tables.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StSelectionTableImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st selection table.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StSelectionTable stSelectionTable) {
		entityCache.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableImpl.class, stSelectionTable.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StSelectionTable> stSelectionTables) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StSelectionTable stSelectionTable : stSelectionTables) {
			entityCache.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
				StSelectionTableImpl.class, stSelectionTable.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st selection table with the primary key. Does not add the st selection table to the database.
	 *
	 * @param stSelectionTablePK the primary key for the new st selection table
	 * @return the new st selection table
	 */
	@Override
	public StSelectionTable create(StSelectionTablePK stSelectionTablePK) {
		StSelectionTable stSelectionTable = new StSelectionTableImpl();

		stSelectionTable.setNew(true);
		stSelectionTable.setPrimaryKey(stSelectionTablePK);

		return stSelectionTable;
	}

	/**
	 * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stSelectionTablePK the primary key of the st selection table
	 * @return the st selection table that was removed
	 * @throws NoSuchStSelectionTableException if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable remove(StSelectionTablePK stSelectionTablePK)
		throws NoSuchStSelectionTableException {
		return remove((Serializable)stSelectionTablePK);
	}

	/**
	 * Removes the st selection table with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st selection table
	 * @return the st selection table that was removed
	 * @throws NoSuchStSelectionTableException if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable remove(Serializable primaryKey)
		throws NoSuchStSelectionTableException {
		Session session = null;

		try {
			session = openSession();

			StSelectionTable stSelectionTable = (StSelectionTable)session.get(StSelectionTableImpl.class,
					primaryKey);

			if (stSelectionTable == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStSelectionTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stSelectionTable);
		}
		catch (NoSuchStSelectionTableException nsee) {
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
	protected StSelectionTable removeImpl(StSelectionTable stSelectionTable) {
		stSelectionTable = toUnwrappedModel(stSelectionTable);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stSelectionTable)) {
				stSelectionTable = (StSelectionTable)session.get(StSelectionTableImpl.class,
						stSelectionTable.getPrimaryKeyObj());
			}

			if (stSelectionTable != null) {
				session.delete(stSelectionTable);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stSelectionTable != null) {
			clearCache(stSelectionTable);
		}

		return stSelectionTable;
	}

	@Override
	public StSelectionTable updateImpl(StSelectionTable stSelectionTable) {
		stSelectionTable = toUnwrappedModel(stSelectionTable);

		boolean isNew = stSelectionTable.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stSelectionTable.isNew()) {
				session.save(stSelectionTable);

				stSelectionTable.setNew(false);
			}
			else {
				stSelectionTable = (StSelectionTable)session.merge(stSelectionTable);
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

		entityCache.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
			StSelectionTableImpl.class, stSelectionTable.getPrimaryKey(),
			stSelectionTable, false);

		stSelectionTable.resetOriginalValues();

		return stSelectionTable;
	}

	protected StSelectionTable toUnwrappedModel(
		StSelectionTable stSelectionTable) {
		if (stSelectionTable instanceof StSelectionTableImpl) {
			return stSelectionTable;
		}

		StSelectionTableImpl stSelectionTableImpl = new StSelectionTableImpl();

		stSelectionTableImpl.setNew(stSelectionTable.isNew());
		stSelectionTableImpl.setPrimaryKey(stSelectionTable.getPrimaryKey());

		stSelectionTableImpl.setSelectionType(stSelectionTable.getSelectionType());
		stSelectionTableImpl.setUserId(stSelectionTable.getUserId());
		stSelectionTableImpl.setSessionId(stSelectionTable.getSessionId());
		stSelectionTableImpl.setCompanyItemSid(stSelectionTable.getCompanyItemSid());
		stSelectionTableImpl.setCheckRecord(stSelectionTable.isCheckRecord());

		return stSelectionTableImpl;
	}

	/**
	 * Returns the st selection table with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st selection table
	 * @return the st selection table
	 * @throws NoSuchStSelectionTableException if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStSelectionTableException {
		StSelectionTable stSelectionTable = fetchByPrimaryKey(primaryKey);

		if (stSelectionTable == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStSelectionTableException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stSelectionTable;
	}

	/**
	 * Returns the st selection table with the primary key or throws a {@link NoSuchStSelectionTableException} if it could not be found.
	 *
	 * @param stSelectionTablePK the primary key of the st selection table
	 * @return the st selection table
	 * @throws NoSuchStSelectionTableException if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable findByPrimaryKey(
		StSelectionTablePK stSelectionTablePK)
		throws NoSuchStSelectionTableException {
		return findByPrimaryKey((Serializable)stSelectionTablePK);
	}

	/**
	 * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st selection table
	 * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
				StSelectionTableImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StSelectionTable stSelectionTable = (StSelectionTable)serializable;

		if (stSelectionTable == null) {
			Session session = null;

			try {
				session = openSession();

				stSelectionTable = (StSelectionTable)session.get(StSelectionTableImpl.class,
						primaryKey);

				if (stSelectionTable != null) {
					cacheResult(stSelectionTable);
				}
				else {
					entityCache.putResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
						StSelectionTableImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StSelectionTableModelImpl.ENTITY_CACHE_ENABLED,
					StSelectionTableImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stSelectionTable;
	}

	/**
	 * Returns the st selection table with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stSelectionTablePK the primary key of the st selection table
	 * @return the st selection table, or <code>null</code> if a st selection table with the primary key could not be found
	 */
	@Override
	public StSelectionTable fetchByPrimaryKey(
		StSelectionTablePK stSelectionTablePK) {
		return fetchByPrimaryKey((Serializable)stSelectionTablePK);
	}

	@Override
	public Map<Serializable, StSelectionTable> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StSelectionTable> map = new HashMap<Serializable, StSelectionTable>();

		for (Serializable primaryKey : primaryKeys) {
			StSelectionTable stSelectionTable = fetchByPrimaryKey(primaryKey);

			if (stSelectionTable != null) {
				map.put(primaryKey, stSelectionTable);
			}
		}

		return map;
	}

	/**
	 * Returns all the st selection tables.
	 *
	 * @return the st selection tables
	 */
	@Override
	public List<StSelectionTable> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st selection tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st selection tables
	 * @param end the upper bound of the range of st selection tables (not inclusive)
	 * @return the range of st selection tables
	 */
	@Override
	public List<StSelectionTable> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st selection tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st selection tables
	 * @param end the upper bound of the range of st selection tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st selection tables
	 */
	@Override
	public List<StSelectionTable> findAll(int start, int end,
		OrderByComparator<StSelectionTable> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st selection tables.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StSelectionTableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st selection tables
	 * @param end the upper bound of the range of st selection tables (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st selection tables
	 */
	@Override
	public List<StSelectionTable> findAll(int start, int end,
		OrderByComparator<StSelectionTable> orderByComparator,
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

		List<StSelectionTable> list = null;

		if (retrieveFromCache) {
			list = (List<StSelectionTable>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STSELECTIONTABLE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STSELECTIONTABLE;

				if (pagination) {
					sql = sql.concat(StSelectionTableModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StSelectionTable>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StSelectionTable>)QueryUtil.list(q,
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
	 * Removes all the st selection tables from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StSelectionTable stSelectionTable : findAll()) {
			remove(stSelectionTable);
		}
	}

	/**
	 * Returns the number of st selection tables.
	 *
	 * @return the number of st selection tables
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STSELECTIONTABLE);

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
		return StSelectionTableModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st selection table persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StSelectionTableImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STSELECTIONTABLE = "SELECT stSelectionTable FROM StSelectionTable stSelectionTable";
	private static final String _SQL_COUNT_STSELECTIONTABLE = "SELECT COUNT(stSelectionTable) FROM StSelectionTable stSelectionTable";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stSelectionTable.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StSelectionTable exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StSelectionTablePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"selectionType", "userId", "sessionId", "companyItemSid",
				"checkRecord"
			});
}