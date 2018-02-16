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

import com.stpl.app.parttwo.exception.NoSuchCffCustHierarchyException;
import com.stpl.app.parttwo.model.CffCustHierarchy;
import com.stpl.app.parttwo.model.impl.CffCustHierarchyImpl;
import com.stpl.app.parttwo.model.impl.CffCustHierarchyModelImpl;
import com.stpl.app.parttwo.service.persistence.CffCustHierarchyPersistence;

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
 * The persistence implementation for the cff cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustHierarchyPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffCustHierarchyUtil
 * @generated
 */
@ProviderType
public class CffCustHierarchyPersistenceImpl extends BasePersistenceImpl<CffCustHierarchy>
	implements CffCustHierarchyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffCustHierarchyUtil} to access the cff cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffCustHierarchyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
			CffCustHierarchyImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
			CffCustHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffCustHierarchyPersistenceImpl() {
		setModelClass(CffCustHierarchy.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("cffCustHierarchySid", "CFF_CUST_HIERARCHY_SID");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("relationshipLevelSid", "RELATIONSHIP_LEVEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff cust hierarchy in the entity cache if it is enabled.
	 *
	 * @param cffCustHierarchy the cff cust hierarchy
	 */
	@Override
	public void cacheResult(CffCustHierarchy cffCustHierarchy) {
		entityCache.putResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyImpl.class, cffCustHierarchy.getPrimaryKey(),
			cffCustHierarchy);

		cffCustHierarchy.resetOriginalValues();
	}

	/**
	 * Caches the cff cust hierarchies in the entity cache if it is enabled.
	 *
	 * @param cffCustHierarchies the cff cust hierarchies
	 */
	@Override
	public void cacheResult(List<CffCustHierarchy> cffCustHierarchies) {
		for (CffCustHierarchy cffCustHierarchy : cffCustHierarchies) {
			if (entityCache.getResult(
						CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						CffCustHierarchyImpl.class,
						cffCustHierarchy.getPrimaryKey()) == null) {
				cacheResult(cffCustHierarchy);
			}
			else {
				cffCustHierarchy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff cust hierarchies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffCustHierarchyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff cust hierarchy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffCustHierarchy cffCustHierarchy) {
		entityCache.removeResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyImpl.class, cffCustHierarchy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffCustHierarchy> cffCustHierarchies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffCustHierarchy cffCustHierarchy : cffCustHierarchies) {
			entityCache.removeResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				CffCustHierarchyImpl.class, cffCustHierarchy.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff cust hierarchy with the primary key. Does not add the cff cust hierarchy to the database.
	 *
	 * @param cffCustHierarchySid the primary key for the new cff cust hierarchy
	 * @return the new cff cust hierarchy
	 */
	@Override
	public CffCustHierarchy create(int cffCustHierarchySid) {
		CffCustHierarchy cffCustHierarchy = new CffCustHierarchyImpl();

		cffCustHierarchy.setNew(true);
		cffCustHierarchy.setPrimaryKey(cffCustHierarchySid);

		return cffCustHierarchy;
	}

	/**
	 * Removes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffCustHierarchySid the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy that was removed
	 * @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy remove(int cffCustHierarchySid)
		throws NoSuchCffCustHierarchyException {
		return remove((Serializable)cffCustHierarchySid);
	}

	/**
	 * Removes the cff cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy that was removed
	 * @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy remove(Serializable primaryKey)
		throws NoSuchCffCustHierarchyException {
		Session session = null;

		try {
			session = openSession();

			CffCustHierarchy cffCustHierarchy = (CffCustHierarchy)session.get(CffCustHierarchyImpl.class,
					primaryKey);

			if (cffCustHierarchy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffCustHierarchy);
		}
		catch (NoSuchCffCustHierarchyException nsee) {
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
	protected CffCustHierarchy removeImpl(CffCustHierarchy cffCustHierarchy) {
		cffCustHierarchy = toUnwrappedModel(cffCustHierarchy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffCustHierarchy)) {
				cffCustHierarchy = (CffCustHierarchy)session.get(CffCustHierarchyImpl.class,
						cffCustHierarchy.getPrimaryKeyObj());
			}

			if (cffCustHierarchy != null) {
				session.delete(cffCustHierarchy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffCustHierarchy != null) {
			clearCache(cffCustHierarchy);
		}

		return cffCustHierarchy;
	}

	@Override
	public CffCustHierarchy updateImpl(CffCustHierarchy cffCustHierarchy) {
		cffCustHierarchy = toUnwrappedModel(cffCustHierarchy);

		boolean isNew = cffCustHierarchy.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffCustHierarchy.isNew()) {
				session.save(cffCustHierarchy);

				cffCustHierarchy.setNew(false);
			}
			else {
				cffCustHierarchy = (CffCustHierarchy)session.merge(cffCustHierarchy);
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

		entityCache.putResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			CffCustHierarchyImpl.class, cffCustHierarchy.getPrimaryKey(),
			cffCustHierarchy, false);

		cffCustHierarchy.resetOriginalValues();

		return cffCustHierarchy;
	}

	protected CffCustHierarchy toUnwrappedModel(
		CffCustHierarchy cffCustHierarchy) {
		if (cffCustHierarchy instanceof CffCustHierarchyImpl) {
			return cffCustHierarchy;
		}

		CffCustHierarchyImpl cffCustHierarchyImpl = new CffCustHierarchyImpl();

		cffCustHierarchyImpl.setNew(cffCustHierarchy.isNew());
		cffCustHierarchyImpl.setPrimaryKey(cffCustHierarchy.getPrimaryKey());

		cffCustHierarchyImpl.setCffCustHierarchySid(cffCustHierarchy.getCffCustHierarchySid());
		cffCustHierarchyImpl.setCffMasterSid(cffCustHierarchy.getCffMasterSid());
		cffCustHierarchyImpl.setRelationshipLevelSid(cffCustHierarchy.getRelationshipLevelSid());

		return cffCustHierarchyImpl;
	}

	/**
	 * Returns the cff cust hierarchy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy
	 * @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffCustHierarchyException {
		CffCustHierarchy cffCustHierarchy = fetchByPrimaryKey(primaryKey);

		if (cffCustHierarchy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffCustHierarchy;
	}

	/**
	 * Returns the cff cust hierarchy with the primary key or throws a {@link NoSuchCffCustHierarchyException} if it could not be found.
	 *
	 * @param cffCustHierarchySid the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy
	 * @throws NoSuchCffCustHierarchyException if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy findByPrimaryKey(int cffCustHierarchySid)
		throws NoSuchCffCustHierarchyException {
		return findByPrimaryKey((Serializable)cffCustHierarchySid);
	}

	/**
	 * Returns the cff cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy, or <code>null</code> if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				CffCustHierarchyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffCustHierarchy cffCustHierarchy = (CffCustHierarchy)serializable;

		if (cffCustHierarchy == null) {
			Session session = null;

			try {
				session = openSession();

				cffCustHierarchy = (CffCustHierarchy)session.get(CffCustHierarchyImpl.class,
						primaryKey);

				if (cffCustHierarchy != null) {
					cacheResult(cffCustHierarchy);
				}
				else {
					entityCache.putResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						CffCustHierarchyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffCustHierarchyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffCustHierarchy;
	}

	/**
	 * Returns the cff cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffCustHierarchySid the primary key of the cff cust hierarchy
	 * @return the cff cust hierarchy, or <code>null</code> if a cff cust hierarchy with the primary key could not be found
	 */
	@Override
	public CffCustHierarchy fetchByPrimaryKey(int cffCustHierarchySid) {
		return fetchByPrimaryKey((Serializable)cffCustHierarchySid);
	}

	@Override
	public Map<Serializable, CffCustHierarchy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffCustHierarchy> map = new HashMap<Serializable, CffCustHierarchy>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffCustHierarchy cffCustHierarchy = fetchByPrimaryKey(primaryKey);

			if (cffCustHierarchy != null) {
				map.put(primaryKey, cffCustHierarchy);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffCustHierarchyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffCustHierarchy)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFCUSTHIERARCHY_WHERE_PKS_IN);

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

			for (CffCustHierarchy cffCustHierarchy : (List<CffCustHierarchy>)q.list()) {
				map.put(cffCustHierarchy.getPrimaryKeyObj(), cffCustHierarchy);

				cacheResult(cffCustHierarchy);

				uncachedPrimaryKeys.remove(cffCustHierarchy.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					CffCustHierarchyImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff cust hierarchies.
	 *
	 * @return the cff cust hierarchies
	 */
	@Override
	public List<CffCustHierarchy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff cust hierarchies
	 * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	 * @return the range of cff cust hierarchies
	 */
	@Override
	public List<CffCustHierarchy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff cust hierarchies
	 * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff cust hierarchies
	 */
	@Override
	public List<CffCustHierarchy> findAll(int start, int end,
		OrderByComparator<CffCustHierarchy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff cust hierarchies
	 * @param end the upper bound of the range of cff cust hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff cust hierarchies
	 */
	@Override
	public List<CffCustHierarchy> findAll(int start, int end,
		OrderByComparator<CffCustHierarchy> orderByComparator,
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

		List<CffCustHierarchy> list = null;

		if (retrieveFromCache) {
			list = (List<CffCustHierarchy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFCUSTHIERARCHY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFCUSTHIERARCHY;

				if (pagination) {
					sql = sql.concat(CffCustHierarchyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffCustHierarchy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffCustHierarchy>)QueryUtil.list(q,
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
	 * Removes all the cff cust hierarchies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffCustHierarchy cffCustHierarchy : findAll()) {
			remove(cffCustHierarchy);
		}
	}

	/**
	 * Returns the number of cff cust hierarchies.
	 *
	 * @return the number of cff cust hierarchies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFCUSTHIERARCHY);

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
		return CffCustHierarchyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff cust hierarchy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffCustHierarchyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFCUSTHIERARCHY = "SELECT cffCustHierarchy FROM CffCustHierarchy cffCustHierarchy";
	private static final String _SQL_SELECT_CFFCUSTHIERARCHY_WHERE_PKS_IN = "SELECT cffCustHierarchy FROM CffCustHierarchy cffCustHierarchy WHERE CFF_CUST_HIERARCHY_SID IN (";
	private static final String _SQL_COUNT_CFFCUSTHIERARCHY = "SELECT COUNT(cffCustHierarchy) FROM CffCustHierarchy cffCustHierarchy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffCustHierarchy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffCustHierarchy exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffCustHierarchyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"cffCustHierarchySid", "cffMasterSid", "relationshipLevelSid"
			});
}