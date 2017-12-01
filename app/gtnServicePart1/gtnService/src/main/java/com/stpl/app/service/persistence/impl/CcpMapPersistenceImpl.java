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

import com.stpl.app.exception.NoSuchCcpMapException;
import com.stpl.app.model.CcpMap;
import com.stpl.app.model.impl.CcpMapImpl;
import com.stpl.app.model.impl.CcpMapModelImpl;
import com.stpl.app.service.persistence.CcpMapPersistence;

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
 * The persistence implementation for the ccp map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpMapPersistence
 * @see com.stpl.app.service.persistence.CcpMapUtil
 * @generated
 */
@ProviderType
public class CcpMapPersistenceImpl extends BasePersistenceImpl<CcpMap>
	implements CcpMapPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CcpMapUtil} to access the ccp map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CcpMapImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapModelImpl.FINDER_CACHE_ENABLED, CcpMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapModelImpl.FINDER_CACHE_ENABLED, CcpMapImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CcpMapPersistenceImpl() {
		setModelClass(CcpMap.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("relationshipLevelSid", "RELATIONSHIP_LEVEL_SID");
			dbColumnNames.put("ccpMapSid", "CCP_MAP_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ccp map in the entity cache if it is enabled.
	 *
	 * @param ccpMap the ccp map
	 */
	@Override
	public void cacheResult(CcpMap ccpMap) {
		entityCache.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapImpl.class, ccpMap.getPrimaryKey(), ccpMap);

		ccpMap.resetOriginalValues();
	}

	/**
	 * Caches the ccp maps in the entity cache if it is enabled.
	 *
	 * @param ccpMaps the ccp maps
	 */
	@Override
	public void cacheResult(List<CcpMap> ccpMaps) {
		for (CcpMap ccpMap : ccpMaps) {
			if (entityCache.getResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
						CcpMapImpl.class, ccpMap.getPrimaryKey()) == null) {
				cacheResult(ccpMap);
			}
			else {
				ccpMap.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ccp maps.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CcpMapImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ccp map.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CcpMap ccpMap) {
		entityCache.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapImpl.class, ccpMap.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CcpMap> ccpMaps) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CcpMap ccpMap : ccpMaps) {
			entityCache.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
				CcpMapImpl.class, ccpMap.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ccp map with the primary key. Does not add the ccp map to the database.
	 *
	 * @param ccpMapSid the primary key for the new ccp map
	 * @return the new ccp map
	 */
	@Override
	public CcpMap create(int ccpMapSid) {
		CcpMap ccpMap = new CcpMapImpl();

		ccpMap.setNew(true);
		ccpMap.setPrimaryKey(ccpMapSid);

		return ccpMap;
	}

	/**
	 * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ccpMapSid the primary key of the ccp map
	 * @return the ccp map that was removed
	 * @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap remove(int ccpMapSid) throws NoSuchCcpMapException {
		return remove((Serializable)ccpMapSid);
	}

	/**
	 * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ccp map
	 * @return the ccp map that was removed
	 * @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap remove(Serializable primaryKey) throws NoSuchCcpMapException {
		Session session = null;

		try {
			session = openSession();

			CcpMap ccpMap = (CcpMap)session.get(CcpMapImpl.class, primaryKey);

			if (ccpMap == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCcpMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ccpMap);
		}
		catch (NoSuchCcpMapException nsee) {
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
	protected CcpMap removeImpl(CcpMap ccpMap) {
		ccpMap = toUnwrappedModel(ccpMap);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ccpMap)) {
				ccpMap = (CcpMap)session.get(CcpMapImpl.class,
						ccpMap.getPrimaryKeyObj());
			}

			if (ccpMap != null) {
				session.delete(ccpMap);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ccpMap != null) {
			clearCache(ccpMap);
		}

		return ccpMap;
	}

	@Override
	public CcpMap updateImpl(CcpMap ccpMap) {
		ccpMap = toUnwrappedModel(ccpMap);

		boolean isNew = ccpMap.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ccpMap.isNew()) {
				session.save(ccpMap);

				ccpMap.setNew(false);
			}
			else {
				ccpMap = (CcpMap)session.merge(ccpMap);
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

		entityCache.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
			CcpMapImpl.class, ccpMap.getPrimaryKey(), ccpMap, false);

		ccpMap.resetOriginalValues();

		return ccpMap;
	}

	protected CcpMap toUnwrappedModel(CcpMap ccpMap) {
		if (ccpMap instanceof CcpMapImpl) {
			return ccpMap;
		}

		CcpMapImpl ccpMapImpl = new CcpMapImpl();

		ccpMapImpl.setNew(ccpMap.isNew());
		ccpMapImpl.setPrimaryKey(ccpMap.getPrimaryKey());

		ccpMapImpl.setCcpDetailsSid(ccpMap.getCcpDetailsSid());
		ccpMapImpl.setRelationshipLevelSid(ccpMap.getRelationshipLevelSid());
		ccpMapImpl.setCcpMapSid(ccpMap.getCcpMapSid());

		return ccpMapImpl;
	}

	/**
	 * Returns the ccp map with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ccp map
	 * @return the ccp map
	 * @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCcpMapException {
		CcpMap ccpMap = fetchByPrimaryKey(primaryKey);

		if (ccpMap == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCcpMapException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ccpMap;
	}

	/**
	 * Returns the ccp map with the primary key or throws a {@link NoSuchCcpMapException} if it could not be found.
	 *
	 * @param ccpMapSid the primary key of the ccp map
	 * @return the ccp map
	 * @throws NoSuchCcpMapException if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap findByPrimaryKey(int ccpMapSid) throws NoSuchCcpMapException {
		return findByPrimaryKey((Serializable)ccpMapSid);
	}

	/**
	 * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ccp map
	 * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
				CcpMapImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CcpMap ccpMap = (CcpMap)serializable;

		if (ccpMap == null) {
			Session session = null;

			try {
				session = openSession();

				ccpMap = (CcpMap)session.get(CcpMapImpl.class, primaryKey);

				if (ccpMap != null) {
					cacheResult(ccpMap);
				}
				else {
					entityCache.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
						CcpMapImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
					CcpMapImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ccpMap;
	}

	/**
	 * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ccpMapSid the primary key of the ccp map
	 * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
	 */
	@Override
	public CcpMap fetchByPrimaryKey(int ccpMapSid) {
		return fetchByPrimaryKey((Serializable)ccpMapSid);
	}

	@Override
	public Map<Serializable, CcpMap> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CcpMap> map = new HashMap<Serializable, CcpMap>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CcpMap ccpMap = fetchByPrimaryKey(primaryKey);

			if (ccpMap != null) {
				map.put(primaryKey, ccpMap);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
					CcpMapImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CcpMap)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CCPMAP_WHERE_PKS_IN);

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

			for (CcpMap ccpMap : (List<CcpMap>)q.list()) {
				map.put(ccpMap.getPrimaryKeyObj(), ccpMap);

				cacheResult(ccpMap);

				uncachedPrimaryKeys.remove(ccpMap.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CcpMapModelImpl.ENTITY_CACHE_ENABLED,
					CcpMapImpl.class, primaryKey, nullModel);
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
	 * Returns all the ccp maps.
	 *
	 * @return the ccp maps
	 */
	@Override
	public List<CcpMap> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ccp maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp maps
	 * @param end the upper bound of the range of ccp maps (not inclusive)
	 * @return the range of ccp maps
	 */
	@Override
	public List<CcpMap> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ccp maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp maps
	 * @param end the upper bound of the range of ccp maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ccp maps
	 */
	@Override
	public List<CcpMap> findAll(int start, int end,
		OrderByComparator<CcpMap> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ccp maps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp maps
	 * @param end the upper bound of the range of ccp maps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ccp maps
	 */
	@Override
	public List<CcpMap> findAll(int start, int end,
		OrderByComparator<CcpMap> orderByComparator, boolean retrieveFromCache) {
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

		List<CcpMap> list = null;

		if (retrieveFromCache) {
			list = (List<CcpMap>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CCPMAP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CCPMAP;

				if (pagination) {
					sql = sql.concat(CcpMapModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CcpMap>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CcpMap>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the ccp maps from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CcpMap ccpMap : findAll()) {
			remove(ccpMap);
		}
	}

	/**
	 * Returns the number of ccp maps.
	 *
	 * @return the number of ccp maps
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CCPMAP);

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
		return CcpMapModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ccp map persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CcpMapImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CCPMAP = "SELECT ccpMap FROM CcpMap ccpMap";
	private static final String _SQL_SELECT_CCPMAP_WHERE_PKS_IN = "SELECT ccpMap FROM CcpMap ccpMap WHERE CCP_MAP_SID IN (";
	private static final String _SQL_COUNT_CCPMAP = "SELECT COUNT(ccpMap) FROM CcpMap ccpMap";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ccpMap.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CcpMap exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CcpMapPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ccpDetailsSid", "relationshipLevelSid", "ccpMapSid"
			});
}