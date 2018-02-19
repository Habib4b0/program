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

import com.stpl.app.exception.NoSuchProjectionCustHierarchyException;
import com.stpl.app.model.ProjectionCustHierarchy;
import com.stpl.app.model.impl.ProjectionCustHierarchyImpl;
import com.stpl.app.model.impl.ProjectionCustHierarchyModelImpl;
import com.stpl.app.service.persistence.ProjectionCustHierarchyPersistence;

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
 * The persistence implementation for the projection cust hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionCustHierarchyPersistence
 * @see com.stpl.app.service.persistence.ProjectionCustHierarchyUtil
 * @generated
 */
@ProviderType
public class ProjectionCustHierarchyPersistenceImpl extends BasePersistenceImpl<ProjectionCustHierarchy>
	implements ProjectionCustHierarchyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionCustHierarchyUtil} to access the projection cust hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionCustHierarchyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
			ProjectionCustHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED,
			ProjectionCustHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionCustHierarchyPersistenceImpl() {
		setModelClass(ProjectionCustHierarchy.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("projectionCustHierarchySid",
				"PROJECTION_CUST_HIERARCHY_SID");
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
	 * Caches the projection cust hierarchy in the entity cache if it is enabled.
	 *
	 * @param projectionCustHierarchy the projection cust hierarchy
	 */
	@Override
	public void cacheResult(ProjectionCustHierarchy projectionCustHierarchy) {
		entityCache.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyImpl.class,
			projectionCustHierarchy.getPrimaryKey(), projectionCustHierarchy);

		projectionCustHierarchy.resetOriginalValues();
	}

	/**
	 * Caches the projection cust hierarchies in the entity cache if it is enabled.
	 *
	 * @param projectionCustHierarchies the projection cust hierarchies
	 */
	@Override
	public void cacheResult(
		List<ProjectionCustHierarchy> projectionCustHierarchies) {
		for (ProjectionCustHierarchy projectionCustHierarchy : projectionCustHierarchies) {
			if (entityCache.getResult(
						ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionCustHierarchyImpl.class,
						projectionCustHierarchy.getPrimaryKey()) == null) {
				cacheResult(projectionCustHierarchy);
			}
			else {
				projectionCustHierarchy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection cust hierarchies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionCustHierarchyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection cust hierarchy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionCustHierarchy projectionCustHierarchy) {
		entityCache.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyImpl.class,
			projectionCustHierarchy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ProjectionCustHierarchy> projectionCustHierarchies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionCustHierarchy projectionCustHierarchy : projectionCustHierarchies) {
			entityCache.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionCustHierarchyImpl.class,
				projectionCustHierarchy.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection cust hierarchy with the primary key. Does not add the projection cust hierarchy to the database.
	 *
	 * @param projectionCustHierarchySid the primary key for the new projection cust hierarchy
	 * @return the new projection cust hierarchy
	 */
	@Override
	public ProjectionCustHierarchy create(int projectionCustHierarchySid) {
		ProjectionCustHierarchy projectionCustHierarchy = new ProjectionCustHierarchyImpl();

		projectionCustHierarchy.setNew(true);
		projectionCustHierarchy.setPrimaryKey(projectionCustHierarchySid);

		return projectionCustHierarchy;
	}

	/**
	 * Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy that was removed
	 * @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy remove(int projectionCustHierarchySid)
		throws NoSuchProjectionCustHierarchyException {
		return remove((Serializable)projectionCustHierarchySid);
	}

	/**
	 * Removes the projection cust hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy that was removed
	 * @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy remove(Serializable primaryKey)
		throws NoSuchProjectionCustHierarchyException {
		Session session = null;

		try {
			session = openSession();

			ProjectionCustHierarchy projectionCustHierarchy = (ProjectionCustHierarchy)session.get(ProjectionCustHierarchyImpl.class,
					primaryKey);

			if (projectionCustHierarchy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionCustHierarchy);
		}
		catch (NoSuchProjectionCustHierarchyException nsee) {
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
	protected ProjectionCustHierarchy removeImpl(
		ProjectionCustHierarchy projectionCustHierarchy) {
		projectionCustHierarchy = toUnwrappedModel(projectionCustHierarchy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionCustHierarchy)) {
				projectionCustHierarchy = (ProjectionCustHierarchy)session.get(ProjectionCustHierarchyImpl.class,
						projectionCustHierarchy.getPrimaryKeyObj());
			}

			if (projectionCustHierarchy != null) {
				session.delete(projectionCustHierarchy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionCustHierarchy != null) {
			clearCache(projectionCustHierarchy);
		}

		return projectionCustHierarchy;
	}

	@Override
	public ProjectionCustHierarchy updateImpl(
		ProjectionCustHierarchy projectionCustHierarchy) {
		projectionCustHierarchy = toUnwrappedModel(projectionCustHierarchy);

		boolean isNew = projectionCustHierarchy.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionCustHierarchy.isNew()) {
				session.save(projectionCustHierarchy);

				projectionCustHierarchy.setNew(false);
			}
			else {
				projectionCustHierarchy = (ProjectionCustHierarchy)session.merge(projectionCustHierarchy);
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

		entityCache.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionCustHierarchyImpl.class,
			projectionCustHierarchy.getPrimaryKey(), projectionCustHierarchy,
			false);

		projectionCustHierarchy.resetOriginalValues();

		return projectionCustHierarchy;
	}

	protected ProjectionCustHierarchy toUnwrappedModel(
		ProjectionCustHierarchy projectionCustHierarchy) {
		if (projectionCustHierarchy instanceof ProjectionCustHierarchyImpl) {
			return projectionCustHierarchy;
		}

		ProjectionCustHierarchyImpl projectionCustHierarchyImpl = new ProjectionCustHierarchyImpl();

		projectionCustHierarchyImpl.setNew(projectionCustHierarchy.isNew());
		projectionCustHierarchyImpl.setPrimaryKey(projectionCustHierarchy.getPrimaryKey());

		projectionCustHierarchyImpl.setProjectionMasterSid(projectionCustHierarchy.getProjectionMasterSid());
		projectionCustHierarchyImpl.setProjectionCustHierarchySid(projectionCustHierarchy.getProjectionCustHierarchySid());
		projectionCustHierarchyImpl.setRelationshipLevelSid(projectionCustHierarchy.getRelationshipLevelSid());

		return projectionCustHierarchyImpl;
	}

	/**
	 * Returns the projection cust hierarchy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy
	 * @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionCustHierarchyException {
		ProjectionCustHierarchy projectionCustHierarchy = fetchByPrimaryKey(primaryKey);

		if (projectionCustHierarchy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionCustHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionCustHierarchy;
	}

	/**
	 * Returns the projection cust hierarchy with the primary key or throws a {@link NoSuchProjectionCustHierarchyException} if it could not be found.
	 *
	 * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy
	 * @throws NoSuchProjectionCustHierarchyException if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy findByPrimaryKey(
		int projectionCustHierarchySid)
		throws NoSuchProjectionCustHierarchyException {
		return findByPrimaryKey((Serializable)projectionCustHierarchySid);
	}

	/**
	 * Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionCustHierarchyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionCustHierarchy projectionCustHierarchy = (ProjectionCustHierarchy)serializable;

		if (projectionCustHierarchy == null) {
			Session session = null;

			try {
				session = openSession();

				projectionCustHierarchy = (ProjectionCustHierarchy)session.get(ProjectionCustHierarchyImpl.class,
						primaryKey);

				if (projectionCustHierarchy != null) {
					cacheResult(projectionCustHierarchy);
				}
				else {
					entityCache.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionCustHierarchyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustHierarchyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionCustHierarchy;
	}

	/**
	 * Returns the projection cust hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionCustHierarchySid the primary key of the projection cust hierarchy
	 * @return the projection cust hierarchy, or <code>null</code> if a projection cust hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionCustHierarchy fetchByPrimaryKey(
		int projectionCustHierarchySid) {
		return fetchByPrimaryKey((Serializable)projectionCustHierarchySid);
	}

	@Override
	public Map<Serializable, ProjectionCustHierarchy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionCustHierarchy> map = new HashMap<Serializable, ProjectionCustHierarchy>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionCustHierarchy projectionCustHierarchy = fetchByPrimaryKey(primaryKey);

			if (projectionCustHierarchy != null) {
				map.put(primaryKey, projectionCustHierarchy);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustHierarchyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionCustHierarchy)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONCUSTHIERARCHY_WHERE_PKS_IN);

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

			for (ProjectionCustHierarchy projectionCustHierarchy : (List<ProjectionCustHierarchy>)q.list()) {
				map.put(projectionCustHierarchy.getPrimaryKeyObj(),
					projectionCustHierarchy);

				cacheResult(projectionCustHierarchy);

				uncachedPrimaryKeys.remove(projectionCustHierarchy.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionCustHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionCustHierarchyImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection cust hierarchies.
	 *
	 * @return the projection cust hierarchies
	 */
	@Override
	public List<ProjectionCustHierarchy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust hierarchies
	 * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	 * @return the range of projection cust hierarchies
	 */
	@Override
	public List<ProjectionCustHierarchy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust hierarchies
	 * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection cust hierarchies
	 */
	@Override
	public List<ProjectionCustHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionCustHierarchy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection cust hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionCustHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection cust hierarchies
	 * @param end the upper bound of the range of projection cust hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection cust hierarchies
	 */
	@Override
	public List<ProjectionCustHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionCustHierarchy> orderByComparator,
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

		List<ProjectionCustHierarchy> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionCustHierarchy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONCUSTHIERARCHY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONCUSTHIERARCHY;

				if (pagination) {
					sql = sql.concat(ProjectionCustHierarchyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionCustHierarchy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionCustHierarchy>)QueryUtil.list(q,
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
	 * Removes all the projection cust hierarchies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionCustHierarchy projectionCustHierarchy : findAll()) {
			remove(projectionCustHierarchy);
		}
	}

	/**
	 * Returns the number of projection cust hierarchies.
	 *
	 * @return the number of projection cust hierarchies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONCUSTHIERARCHY);

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
		return ProjectionCustHierarchyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection cust hierarchy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionCustHierarchyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONCUSTHIERARCHY = "SELECT projectionCustHierarchy FROM ProjectionCustHierarchy projectionCustHierarchy";
	private static final String _SQL_SELECT_PROJECTIONCUSTHIERARCHY_WHERE_PKS_IN =
		"SELECT projectionCustHierarchy FROM ProjectionCustHierarchy projectionCustHierarchy WHERE PROJECTION_CUST_HIERARCHY_SID IN (";
	private static final String _SQL_COUNT_PROJECTIONCUSTHIERARCHY = "SELECT COUNT(projectionCustHierarchy) FROM ProjectionCustHierarchy projectionCustHierarchy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionCustHierarchy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionCustHierarchy exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionCustHierarchyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"projectionMasterSid", "projectionCustHierarchySid",
				"relationshipLevelSid"
			});
}