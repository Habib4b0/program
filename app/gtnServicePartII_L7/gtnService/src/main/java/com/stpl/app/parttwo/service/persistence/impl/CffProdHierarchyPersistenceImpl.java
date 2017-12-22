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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchCffProdHierarchyException;
import com.stpl.app.parttwo.model.CffProdHierarchy;
import com.stpl.app.parttwo.model.impl.CffProdHierarchyImpl;
import com.stpl.app.parttwo.model.impl.CffProdHierarchyModelImpl;
import com.stpl.app.parttwo.service.persistence.CffProdHierarchyPersistence;

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
 * The persistence implementation for the cff prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffProdHierarchyPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffProdHierarchyUtil
 * @generated
 */
@ProviderType
public class CffProdHierarchyPersistenceImpl extends BasePersistenceImpl<CffProdHierarchy>
	implements CffProdHierarchyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffProdHierarchyUtil} to access the cff prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffProdHierarchyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
			CffProdHierarchyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
			CffProdHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffProdHierarchyPersistenceImpl() {
		setModelClass(CffProdHierarchy.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("relationshipLevelSid", "RELATIONSHIP_LEVEL_SID");
			dbColumnNames.put("cffProdHierarchySid", "CFF_PROD_HIERARCHY_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff prod hierarchy in the entity cache if it is enabled.
	 *
	 * @param cffProdHierarchy the cff prod hierarchy
	 */
	@Override
	public void cacheResult(CffProdHierarchy cffProdHierarchy) {
		entityCache.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey(),
			cffProdHierarchy);

		cffProdHierarchy.resetOriginalValues();
	}

	/**
	 * Caches the cff prod hierarchies in the entity cache if it is enabled.
	 *
	 * @param cffProdHierarchies the cff prod hierarchies
	 */
	@Override
	public void cacheResult(List<CffProdHierarchy> cffProdHierarchies) {
		for (CffProdHierarchy cffProdHierarchy : cffProdHierarchies) {
			if (entityCache.getResult(
						CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						CffProdHierarchyImpl.class,
						cffProdHierarchy.getPrimaryKey()) == null) {
				cacheResult(cffProdHierarchy);
			}
			else {
				cffProdHierarchy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff prod hierarchies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffProdHierarchyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff prod hierarchy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffProdHierarchy cffProdHierarchy) {
		entityCache.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffProdHierarchy> cffProdHierarchies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffProdHierarchy cffProdHierarchy : cffProdHierarchies) {
			entityCache.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff prod hierarchy with the primary key. Does not add the cff prod hierarchy to the database.
	 *
	 * @param cffProdHierarchySid the primary key for the new cff prod hierarchy
	 * @return the new cff prod hierarchy
	 */
	@Override
	public CffProdHierarchy create(int cffProdHierarchySid) {
		CffProdHierarchy cffProdHierarchy = new CffProdHierarchyImpl();

		cffProdHierarchy.setNew(true);
		cffProdHierarchy.setPrimaryKey(cffProdHierarchySid);

		return cffProdHierarchy;
	}

	/**
	 * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffProdHierarchySid the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy that was removed
	 * @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy remove(int cffProdHierarchySid)
		throws NoSuchCffProdHierarchyException {
		return remove((Serializable)cffProdHierarchySid);
	}

	/**
	 * Removes the cff prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy that was removed
	 * @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy remove(Serializable primaryKey)
		throws NoSuchCffProdHierarchyException {
		Session session = null;

		try {
			session = openSession();

			CffProdHierarchy cffProdHierarchy = (CffProdHierarchy)session.get(CffProdHierarchyImpl.class,
					primaryKey);

			if (cffProdHierarchy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffProdHierarchy);
		}
		catch (NoSuchCffProdHierarchyException nsee) {
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
	protected CffProdHierarchy removeImpl(CffProdHierarchy cffProdHierarchy) {
		cffProdHierarchy = toUnwrappedModel(cffProdHierarchy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffProdHierarchy)) {
				cffProdHierarchy = (CffProdHierarchy)session.get(CffProdHierarchyImpl.class,
						cffProdHierarchy.getPrimaryKeyObj());
			}

			if (cffProdHierarchy != null) {
				session.delete(cffProdHierarchy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffProdHierarchy != null) {
			clearCache(cffProdHierarchy);
		}

		return cffProdHierarchy;
	}

	@Override
	public CffProdHierarchy updateImpl(CffProdHierarchy cffProdHierarchy) {
		cffProdHierarchy = toUnwrappedModel(cffProdHierarchy);

		boolean isNew = cffProdHierarchy.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffProdHierarchy.isNew()) {
				session.save(cffProdHierarchy);

				cffProdHierarchy.setNew(false);
			}
			else {
				cffProdHierarchy = (CffProdHierarchy)session.merge(cffProdHierarchy);
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

		entityCache.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffProdHierarchyImpl.class, cffProdHierarchy.getPrimaryKey(),
			cffProdHierarchy, false);

		cffProdHierarchy.resetOriginalValues();

		return cffProdHierarchy;
	}

	protected CffProdHierarchy toUnwrappedModel(
		CffProdHierarchy cffProdHierarchy) {
		if (cffProdHierarchy instanceof CffProdHierarchyImpl) {
			return cffProdHierarchy;
		}

		CffProdHierarchyImpl cffProdHierarchyImpl = new CffProdHierarchyImpl();

		cffProdHierarchyImpl.setNew(cffProdHierarchy.isNew());
		cffProdHierarchyImpl.setPrimaryKey(cffProdHierarchy.getPrimaryKey());

		cffProdHierarchyImpl.setCffMasterSid(cffProdHierarchy.getCffMasterSid());
		cffProdHierarchyImpl.setRelationshipLevelSid(cffProdHierarchy.getRelationshipLevelSid());
		cffProdHierarchyImpl.setCffProdHierarchySid(cffProdHierarchy.getCffProdHierarchySid());

		return cffProdHierarchyImpl;
	}

	/**
	 * Returns the cff prod hierarchy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy
	 * @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffProdHierarchyException {
		CffProdHierarchy cffProdHierarchy = fetchByPrimaryKey(primaryKey);

		if (cffProdHierarchy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffProdHierarchy;
	}

	/**
	 * Returns the cff prod hierarchy with the primary key or throws a {@link NoSuchCffProdHierarchyException} if it could not be found.
	 *
	 * @param cffProdHierarchySid the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy
	 * @throws NoSuchCffProdHierarchyException if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy findByPrimaryKey(int cffProdHierarchySid)
		throws NoSuchCffProdHierarchyException {
		return findByPrimaryKey((Serializable)cffProdHierarchySid);
	}

	/**
	 * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				CffProdHierarchyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffProdHierarchy cffProdHierarchy = (CffProdHierarchy)serializable;

		if (cffProdHierarchy == null) {
			Session session = null;

			try {
				session = openSession();

				cffProdHierarchy = (CffProdHierarchy)session.get(CffProdHierarchyImpl.class,
						primaryKey);

				if (cffProdHierarchy != null) {
					cacheResult(cffProdHierarchy);
				}
				else {
					entityCache.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						CffProdHierarchyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffProdHierarchyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffProdHierarchy;
	}

	/**
	 * Returns the cff prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffProdHierarchySid the primary key of the cff prod hierarchy
	 * @return the cff prod hierarchy, or <code>null</code> if a cff prod hierarchy with the primary key could not be found
	 */
	@Override
	public CffProdHierarchy fetchByPrimaryKey(int cffProdHierarchySid) {
		return fetchByPrimaryKey((Serializable)cffProdHierarchySid);
	}

	@Override
	public Map<Serializable, CffProdHierarchy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffProdHierarchy> map = new HashMap<Serializable, CffProdHierarchy>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffProdHierarchy cffProdHierarchy = fetchByPrimaryKey(primaryKey);

			if (cffProdHierarchy != null) {
				map.put(primaryKey, cffProdHierarchy);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffProdHierarchyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffProdHierarchy)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFPRODHIERARCHY_WHERE_PKS_IN);

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

			for (CffProdHierarchy cffProdHierarchy : (List<CffProdHierarchy>)q.list()) {
				map.put(cffProdHierarchy.getPrimaryKeyObj(), cffProdHierarchy);

				cacheResult(cffProdHierarchy);

				uncachedPrimaryKeys.remove(cffProdHierarchy.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffProdHierarchyImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff prod hierarchies.
	 *
	 * @return the cff prod hierarchies
	 */
	@Override
	public List<CffProdHierarchy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff prod hierarchies
	 * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	 * @return the range of cff prod hierarchies
	 */
	@Override
	public List<CffProdHierarchy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff prod hierarchies
	 * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff prod hierarchies
	 */
	@Override
	public List<CffProdHierarchy> findAll(int start, int end,
		OrderByComparator<CffProdHierarchy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff prod hierarchies
	 * @param end the upper bound of the range of cff prod hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff prod hierarchies
	 */
	@Override
	public List<CffProdHierarchy> findAll(int start, int end,
		OrderByComparator<CffProdHierarchy> orderByComparator,
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

		List<CffProdHierarchy> list = null;

		if (retrieveFromCache) {
			list = (List<CffProdHierarchy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFPRODHIERARCHY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFPRODHIERARCHY;

				if (pagination) {
					sql = sql.concat(CffProdHierarchyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffProdHierarchy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffProdHierarchy>)QueryUtil.list(q,
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
	 * Removes all the cff prod hierarchies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffProdHierarchy cffProdHierarchy : findAll()) {
			remove(cffProdHierarchy);
		}
	}

	/**
	 * Returns the number of cff prod hierarchies.
	 *
	 * @return the number of cff prod hierarchies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFPRODHIERARCHY);

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
		return CffProdHierarchyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff prod hierarchy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffProdHierarchyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFPRODHIERARCHY = "SELECT cffProdHierarchy FROM CffProdHierarchy cffProdHierarchy";
	private static final String _SQL_SELECT_CFFPRODHIERARCHY_WHERE_PKS_IN = "SELECT cffProdHierarchy FROM CffProdHierarchy cffProdHierarchy WHERE CFF_PROD_HIERARCHY_SID IN (";
	private static final String _SQL_COUNT_CFFPRODHIERARCHY = "SELECT COUNT(cffProdHierarchy) FROM CffProdHierarchy cffProdHierarchy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffProdHierarchy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffProdHierarchy exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffProdHierarchyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"cffMasterSid", "relationshipLevelSid", "cffProdHierarchySid"
			});
}