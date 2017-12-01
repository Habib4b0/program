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

import com.stpl.app.exception.NoSuchProjectionProdHierarchyException;
import com.stpl.app.model.ProjectionProdHierarchy;
import com.stpl.app.model.impl.ProjectionProdHierarchyImpl;
import com.stpl.app.model.impl.ProjectionProdHierarchyModelImpl;
import com.stpl.app.service.persistence.ProjectionProdHierarchyPersistence;

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
 * The persistence implementation for the projection prod hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionProdHierarchyPersistence
 * @see com.stpl.app.service.persistence.ProjectionProdHierarchyUtil
 * @generated
 */
@ProviderType
public class ProjectionProdHierarchyPersistenceImpl extends BasePersistenceImpl<ProjectionProdHierarchy>
	implements ProjectionProdHierarchyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionProdHierarchyUtil} to access the projection prod hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionProdHierarchyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
			ProjectionProdHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED,
			ProjectionProdHierarchyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionProdHierarchyPersistenceImpl() {
		setModelClass(ProjectionProdHierarchy.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("projectionProdHierarchySid",
				"PROJECTION_PROD_HIERARCHY_SID");
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
	 * Caches the projection prod hierarchy in the entity cache if it is enabled.
	 *
	 * @param projectionProdHierarchy the projection prod hierarchy
	 */
	@Override
	public void cacheResult(ProjectionProdHierarchy projectionProdHierarchy) {
		entityCache.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyImpl.class,
			projectionProdHierarchy.getPrimaryKey(), projectionProdHierarchy);

		projectionProdHierarchy.resetOriginalValues();
	}

	/**
	 * Caches the projection prod hierarchies in the entity cache if it is enabled.
	 *
	 * @param projectionProdHierarchies the projection prod hierarchies
	 */
	@Override
	public void cacheResult(
		List<ProjectionProdHierarchy> projectionProdHierarchies) {
		for (ProjectionProdHierarchy projectionProdHierarchy : projectionProdHierarchies) {
			if (entityCache.getResult(
						ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionProdHierarchyImpl.class,
						projectionProdHierarchy.getPrimaryKey()) == null) {
				cacheResult(projectionProdHierarchy);
			}
			else {
				projectionProdHierarchy.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection prod hierarchies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionProdHierarchyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection prod hierarchy.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionProdHierarchy projectionProdHierarchy) {
		entityCache.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyImpl.class,
			projectionProdHierarchy.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ProjectionProdHierarchy> projectionProdHierarchies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionProdHierarchy projectionProdHierarchy : projectionProdHierarchies) {
			entityCache.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionProdHierarchyImpl.class,
				projectionProdHierarchy.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection prod hierarchy with the primary key. Does not add the projection prod hierarchy to the database.
	 *
	 * @param projectionProdHierarchySid the primary key for the new projection prod hierarchy
	 * @return the new projection prod hierarchy
	 */
	@Override
	public ProjectionProdHierarchy create(int projectionProdHierarchySid) {
		ProjectionProdHierarchy projectionProdHierarchy = new ProjectionProdHierarchyImpl();

		projectionProdHierarchy.setNew(true);
		projectionProdHierarchy.setPrimaryKey(projectionProdHierarchySid);

		return projectionProdHierarchy;
	}

	/**
	 * Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy that was removed
	 * @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy remove(int projectionProdHierarchySid)
		throws NoSuchProjectionProdHierarchyException {
		return remove((Serializable)projectionProdHierarchySid);
	}

	/**
	 * Removes the projection prod hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy that was removed
	 * @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy remove(Serializable primaryKey)
		throws NoSuchProjectionProdHierarchyException {
		Session session = null;

		try {
			session = openSession();

			ProjectionProdHierarchy projectionProdHierarchy = (ProjectionProdHierarchy)session.get(ProjectionProdHierarchyImpl.class,
					primaryKey);

			if (projectionProdHierarchy == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionProdHierarchy);
		}
		catch (NoSuchProjectionProdHierarchyException nsee) {
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
	protected ProjectionProdHierarchy removeImpl(
		ProjectionProdHierarchy projectionProdHierarchy) {
		projectionProdHierarchy = toUnwrappedModel(projectionProdHierarchy);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionProdHierarchy)) {
				projectionProdHierarchy = (ProjectionProdHierarchy)session.get(ProjectionProdHierarchyImpl.class,
						projectionProdHierarchy.getPrimaryKeyObj());
			}

			if (projectionProdHierarchy != null) {
				session.delete(projectionProdHierarchy);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionProdHierarchy != null) {
			clearCache(projectionProdHierarchy);
		}

		return projectionProdHierarchy;
	}

	@Override
	public ProjectionProdHierarchy updateImpl(
		ProjectionProdHierarchy projectionProdHierarchy) {
		projectionProdHierarchy = toUnwrappedModel(projectionProdHierarchy);

		boolean isNew = projectionProdHierarchy.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionProdHierarchy.isNew()) {
				session.save(projectionProdHierarchy);

				projectionProdHierarchy.setNew(false);
			}
			else {
				projectionProdHierarchy = (ProjectionProdHierarchy)session.merge(projectionProdHierarchy);
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

		entityCache.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionProdHierarchyImpl.class,
			projectionProdHierarchy.getPrimaryKey(), projectionProdHierarchy,
			false);

		projectionProdHierarchy.resetOriginalValues();

		return projectionProdHierarchy;
	}

	protected ProjectionProdHierarchy toUnwrappedModel(
		ProjectionProdHierarchy projectionProdHierarchy) {
		if (projectionProdHierarchy instanceof ProjectionProdHierarchyImpl) {
			return projectionProdHierarchy;
		}

		ProjectionProdHierarchyImpl projectionProdHierarchyImpl = new ProjectionProdHierarchyImpl();

		projectionProdHierarchyImpl.setNew(projectionProdHierarchy.isNew());
		projectionProdHierarchyImpl.setPrimaryKey(projectionProdHierarchy.getPrimaryKey());

		projectionProdHierarchyImpl.setProjectionMasterSid(projectionProdHierarchy.getProjectionMasterSid());
		projectionProdHierarchyImpl.setProjectionProdHierarchySid(projectionProdHierarchy.getProjectionProdHierarchySid());
		projectionProdHierarchyImpl.setRelationshipLevelSid(projectionProdHierarchy.getRelationshipLevelSid());

		return projectionProdHierarchyImpl;
	}

	/**
	 * Returns the projection prod hierarchy with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy
	 * @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionProdHierarchyException {
		ProjectionProdHierarchy projectionProdHierarchy = fetchByPrimaryKey(primaryKey);

		if (projectionProdHierarchy == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionProdHierarchyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionProdHierarchy;
	}

	/**
	 * Returns the projection prod hierarchy with the primary key or throws a {@link NoSuchProjectionProdHierarchyException} if it could not be found.
	 *
	 * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy
	 * @throws NoSuchProjectionProdHierarchyException if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy findByPrimaryKey(
		int projectionProdHierarchySid)
		throws NoSuchProjectionProdHierarchyException {
		return findByPrimaryKey((Serializable)projectionProdHierarchySid);
	}

	/**
	 * Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionProdHierarchyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionProdHierarchy projectionProdHierarchy = (ProjectionProdHierarchy)serializable;

		if (projectionProdHierarchy == null) {
			Session session = null;

			try {
				session = openSession();

				projectionProdHierarchy = (ProjectionProdHierarchy)session.get(ProjectionProdHierarchyImpl.class,
						primaryKey);

				if (projectionProdHierarchy != null) {
					cacheResult(projectionProdHierarchy);
				}
				else {
					entityCache.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionProdHierarchyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdHierarchyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionProdHierarchy;
	}

	/**
	 * Returns the projection prod hierarchy with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionProdHierarchySid the primary key of the projection prod hierarchy
	 * @return the projection prod hierarchy, or <code>null</code> if a projection prod hierarchy with the primary key could not be found
	 */
	@Override
	public ProjectionProdHierarchy fetchByPrimaryKey(
		int projectionProdHierarchySid) {
		return fetchByPrimaryKey((Serializable)projectionProdHierarchySid);
	}

	@Override
	public Map<Serializable, ProjectionProdHierarchy> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionProdHierarchy> map = new HashMap<Serializable, ProjectionProdHierarchy>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionProdHierarchy projectionProdHierarchy = fetchByPrimaryKey(primaryKey);

			if (projectionProdHierarchy != null) {
				map.put(primaryKey, projectionProdHierarchy);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdHierarchyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionProdHierarchy)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONPRODHIERARCHY_WHERE_PKS_IN);

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

			for (ProjectionProdHierarchy projectionProdHierarchy : (List<ProjectionProdHierarchy>)q.list()) {
				map.put(projectionProdHierarchy.getPrimaryKeyObj(),
					projectionProdHierarchy);

				cacheResult(projectionProdHierarchy);

				uncachedPrimaryKeys.remove(projectionProdHierarchy.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionProdHierarchyModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionProdHierarchyImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection prod hierarchies.
	 *
	 * @return the projection prod hierarchies
	 */
	@Override
	public List<ProjectionProdHierarchy> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod hierarchies
	 * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	 * @return the range of projection prod hierarchies
	 */
	@Override
	public List<ProjectionProdHierarchy> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod hierarchies
	 * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection prod hierarchies
	 */
	@Override
	public List<ProjectionProdHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionProdHierarchy> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection prod hierarchies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionProdHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection prod hierarchies
	 * @param end the upper bound of the range of projection prod hierarchies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection prod hierarchies
	 */
	@Override
	public List<ProjectionProdHierarchy> findAll(int start, int end,
		OrderByComparator<ProjectionProdHierarchy> orderByComparator,
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

		List<ProjectionProdHierarchy> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionProdHierarchy>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONPRODHIERARCHY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONPRODHIERARCHY;

				if (pagination) {
					sql = sql.concat(ProjectionProdHierarchyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionProdHierarchy>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionProdHierarchy>)QueryUtil.list(q,
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
	 * Removes all the projection prod hierarchies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionProdHierarchy projectionProdHierarchy : findAll()) {
			remove(projectionProdHierarchy);
		}
	}

	/**
	 * Returns the number of projection prod hierarchies.
	 *
	 * @return the number of projection prod hierarchies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONPRODHIERARCHY);

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
		return ProjectionProdHierarchyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection prod hierarchy persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionProdHierarchyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONPRODHIERARCHY = "SELECT projectionProdHierarchy FROM ProjectionProdHierarchy projectionProdHierarchy";
	private static final String _SQL_SELECT_PROJECTIONPRODHIERARCHY_WHERE_PKS_IN =
		"SELECT projectionProdHierarchy FROM ProjectionProdHierarchy projectionProdHierarchy WHERE PROJECTION_PROD_HIERARCHY_SID IN (";
	private static final String _SQL_COUNT_PROJECTIONPRODHIERARCHY = "SELECT COUNT(projectionProdHierarchy) FROM ProjectionProdHierarchy projectionProdHierarchy";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionProdHierarchy.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionProdHierarchy exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionProdHierarchyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"projectionMasterSid", "projectionProdHierarchySid",
				"relationshipLevelSid"
			});
}