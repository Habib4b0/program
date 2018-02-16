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

import com.stpl.app.exception.NoSuchProjectionNameConfigException;
import com.stpl.app.model.ProjectionNameConfig;
import com.stpl.app.model.impl.ProjectionNameConfigImpl;
import com.stpl.app.model.impl.ProjectionNameConfigModelImpl;
import com.stpl.app.service.persistence.ProjectionNameConfigPersistence;

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
 * The persistence implementation for the projection name config service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ProjectionNameConfigPersistence
 * @see com.stpl.app.service.persistence.ProjectionNameConfigUtil
 * @generated
 */
@ProviderType
public class ProjectionNameConfigPersistenceImpl extends BasePersistenceImpl<ProjectionNameConfig>
	implements ProjectionNameConfigPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ProjectionNameConfigUtil} to access the projection name config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ProjectionNameConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED,
			ProjectionNameConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED,
			ProjectionNameConfigImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ProjectionNameConfigPersistenceImpl() {
		setModelClass(ProjectionNameConfig.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("businessProcessType", "BUSINESS_PROCESS_TYPE");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("projectionNameConfigSid",
				"PROJECTION_NAME_CONFIG_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("selectedAttributes", "SELECTED_ATTRIBUTES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the projection name config in the entity cache if it is enabled.
	 *
	 * @param projectionNameConfig the projection name config
	 */
	@Override
	public void cacheResult(ProjectionNameConfig projectionNameConfig) {
		entityCache.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigImpl.class,
			projectionNameConfig.getPrimaryKey(), projectionNameConfig);

		projectionNameConfig.resetOriginalValues();
	}

	/**
	 * Caches the projection name configs in the entity cache if it is enabled.
	 *
	 * @param projectionNameConfigs the projection name configs
	 */
	@Override
	public void cacheResult(List<ProjectionNameConfig> projectionNameConfigs) {
		for (ProjectionNameConfig projectionNameConfig : projectionNameConfigs) {
			if (entityCache.getResult(
						ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionNameConfigImpl.class,
						projectionNameConfig.getPrimaryKey()) == null) {
				cacheResult(projectionNameConfig);
			}
			else {
				projectionNameConfig.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all projection name configs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ProjectionNameConfigImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the projection name config.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ProjectionNameConfig projectionNameConfig) {
		entityCache.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigImpl.class, projectionNameConfig.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ProjectionNameConfig> projectionNameConfigs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ProjectionNameConfig projectionNameConfig : projectionNameConfigs) {
			entityCache.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionNameConfigImpl.class,
				projectionNameConfig.getPrimaryKey());
		}
	}

	/**
	 * Creates a new projection name config with the primary key. Does not add the projection name config to the database.
	 *
	 * @param projectionNameConfigSid the primary key for the new projection name config
	 * @return the new projection name config
	 */
	@Override
	public ProjectionNameConfig create(int projectionNameConfigSid) {
		ProjectionNameConfig projectionNameConfig = new ProjectionNameConfigImpl();

		projectionNameConfig.setNew(true);
		projectionNameConfig.setPrimaryKey(projectionNameConfigSid);

		return projectionNameConfig;
	}

	/**
	 * Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionNameConfigSid the primary key of the projection name config
	 * @return the projection name config that was removed
	 * @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig remove(int projectionNameConfigSid)
		throws NoSuchProjectionNameConfigException {
		return remove((Serializable)projectionNameConfigSid);
	}

	/**
	 * Removes the projection name config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the projection name config
	 * @return the projection name config that was removed
	 * @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig remove(Serializable primaryKey)
		throws NoSuchProjectionNameConfigException {
		Session session = null;

		try {
			session = openSession();

			ProjectionNameConfig projectionNameConfig = (ProjectionNameConfig)session.get(ProjectionNameConfigImpl.class,
					primaryKey);

			if (projectionNameConfig == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchProjectionNameConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(projectionNameConfig);
		}
		catch (NoSuchProjectionNameConfigException nsee) {
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
	protected ProjectionNameConfig removeImpl(
		ProjectionNameConfig projectionNameConfig) {
		projectionNameConfig = toUnwrappedModel(projectionNameConfig);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(projectionNameConfig)) {
				projectionNameConfig = (ProjectionNameConfig)session.get(ProjectionNameConfigImpl.class,
						projectionNameConfig.getPrimaryKeyObj());
			}

			if (projectionNameConfig != null) {
				session.delete(projectionNameConfig);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (projectionNameConfig != null) {
			clearCache(projectionNameConfig);
		}

		return projectionNameConfig;
	}

	@Override
	public ProjectionNameConfig updateImpl(
		ProjectionNameConfig projectionNameConfig) {
		projectionNameConfig = toUnwrappedModel(projectionNameConfig);

		boolean isNew = projectionNameConfig.isNew();

		Session session = null;

		try {
			session = openSession();

			if (projectionNameConfig.isNew()) {
				session.save(projectionNameConfig);

				projectionNameConfig.setNew(false);
			}
			else {
				projectionNameConfig = (ProjectionNameConfig)session.merge(projectionNameConfig);
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

		entityCache.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
			ProjectionNameConfigImpl.class,
			projectionNameConfig.getPrimaryKey(), projectionNameConfig, false);

		projectionNameConfig.resetOriginalValues();

		return projectionNameConfig;
	}

	protected ProjectionNameConfig toUnwrappedModel(
		ProjectionNameConfig projectionNameConfig) {
		if (projectionNameConfig instanceof ProjectionNameConfigImpl) {
			return projectionNameConfig;
		}

		ProjectionNameConfigImpl projectionNameConfigImpl = new ProjectionNameConfigImpl();

		projectionNameConfigImpl.setNew(projectionNameConfig.isNew());
		projectionNameConfigImpl.setPrimaryKey(projectionNameConfig.getPrimaryKey());

		projectionNameConfigImpl.setCreatedDate(projectionNameConfig.getCreatedDate());
		projectionNameConfigImpl.setCreatedBy(projectionNameConfig.getCreatedBy());
		projectionNameConfigImpl.setBusinessProcessType(projectionNameConfig.getBusinessProcessType());
		projectionNameConfigImpl.setVersionNo(projectionNameConfig.getVersionNo());
		projectionNameConfigImpl.setModifiedBy(projectionNameConfig.getModifiedBy());
		projectionNameConfigImpl.setProjectionNameConfigSid(projectionNameConfig.getProjectionNameConfigSid());
		projectionNameConfigImpl.setModifiedDate(projectionNameConfig.getModifiedDate());
		projectionNameConfigImpl.setSelectedAttributes(projectionNameConfig.getSelectedAttributes());

		return projectionNameConfigImpl;
	}

	/**
	 * Returns the projection name config with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection name config
	 * @return the projection name config
	 * @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchProjectionNameConfigException {
		ProjectionNameConfig projectionNameConfig = fetchByPrimaryKey(primaryKey);

		if (projectionNameConfig == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchProjectionNameConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return projectionNameConfig;
	}

	/**
	 * Returns the projection name config with the primary key or throws a {@link NoSuchProjectionNameConfigException} if it could not be found.
	 *
	 * @param projectionNameConfigSid the primary key of the projection name config
	 * @return the projection name config
	 * @throws NoSuchProjectionNameConfigException if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig findByPrimaryKey(int projectionNameConfigSid)
		throws NoSuchProjectionNameConfigException {
		return findByPrimaryKey((Serializable)projectionNameConfigSid);
	}

	/**
	 * Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the projection name config
	 * @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
				ProjectionNameConfigImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ProjectionNameConfig projectionNameConfig = (ProjectionNameConfig)serializable;

		if (projectionNameConfig == null) {
			Session session = null;

			try {
				session = openSession();

				projectionNameConfig = (ProjectionNameConfig)session.get(ProjectionNameConfigImpl.class,
						primaryKey);

				if (projectionNameConfig != null) {
					cacheResult(projectionNameConfig);
				}
				else {
					entityCache.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
						ProjectionNameConfigImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionNameConfigImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return projectionNameConfig;
	}

	/**
	 * Returns the projection name config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionNameConfigSid the primary key of the projection name config
	 * @return the projection name config, or <code>null</code> if a projection name config with the primary key could not be found
	 */
	@Override
	public ProjectionNameConfig fetchByPrimaryKey(int projectionNameConfigSid) {
		return fetchByPrimaryKey((Serializable)projectionNameConfigSid);
	}

	@Override
	public Map<Serializable, ProjectionNameConfig> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ProjectionNameConfig> map = new HashMap<Serializable, ProjectionNameConfig>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ProjectionNameConfig projectionNameConfig = fetchByPrimaryKey(primaryKey);

			if (projectionNameConfig != null) {
				map.put(primaryKey, projectionNameConfig);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionNameConfigImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ProjectionNameConfig)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PROJECTIONNAMECONFIG_WHERE_PKS_IN);

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

			for (ProjectionNameConfig projectionNameConfig : (List<ProjectionNameConfig>)q.list()) {
				map.put(projectionNameConfig.getPrimaryKeyObj(),
					projectionNameConfig);

				cacheResult(projectionNameConfig);

				uncachedPrimaryKeys.remove(projectionNameConfig.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ProjectionNameConfigModelImpl.ENTITY_CACHE_ENABLED,
					ProjectionNameConfigImpl.class, primaryKey, nullModel);
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
	 * Returns all the projection name configs.
	 *
	 * @return the projection name configs
	 */
	@Override
	public List<ProjectionNameConfig> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the projection name configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection name configs
	 * @param end the upper bound of the range of projection name configs (not inclusive)
	 * @return the range of projection name configs
	 */
	@Override
	public List<ProjectionNameConfig> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the projection name configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection name configs
	 * @param end the upper bound of the range of projection name configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of projection name configs
	 */
	@Override
	public List<ProjectionNameConfig> findAll(int start, int end,
		OrderByComparator<ProjectionNameConfig> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the projection name configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectionNameConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of projection name configs
	 * @param end the upper bound of the range of projection name configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of projection name configs
	 */
	@Override
	public List<ProjectionNameConfig> findAll(int start, int end,
		OrderByComparator<ProjectionNameConfig> orderByComparator,
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

		List<ProjectionNameConfig> list = null;

		if (retrieveFromCache) {
			list = (List<ProjectionNameConfig>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PROJECTIONNAMECONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PROJECTIONNAMECONFIG;

				if (pagination) {
					sql = sql.concat(ProjectionNameConfigModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ProjectionNameConfig>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ProjectionNameConfig>)QueryUtil.list(q,
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
	 * Removes all the projection name configs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ProjectionNameConfig projectionNameConfig : findAll()) {
			remove(projectionNameConfig);
		}
	}

	/**
	 * Returns the number of projection name configs.
	 *
	 * @return the number of projection name configs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PROJECTIONNAMECONFIG);

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
		return ProjectionNameConfigModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the projection name config persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ProjectionNameConfigImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PROJECTIONNAMECONFIG = "SELECT projectionNameConfig FROM ProjectionNameConfig projectionNameConfig";
	private static final String _SQL_SELECT_PROJECTIONNAMECONFIG_WHERE_PKS_IN = "SELECT projectionNameConfig FROM ProjectionNameConfig projectionNameConfig WHERE PROJECTION_NAME_CONFIG_SID IN (";
	private static final String _SQL_COUNT_PROJECTIONNAMECONFIG = "SELECT COUNT(projectionNameConfig) FROM ProjectionNameConfig projectionNameConfig";
	private static final String _ORDER_BY_ENTITY_ALIAS = "projectionNameConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProjectionNameConfig exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ProjectionNameConfigPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "businessProcessType", "versionNo",
				"modifiedBy", "projectionNameConfigSid", "modifiedDate",
				"selectedAttributes"
			});
}