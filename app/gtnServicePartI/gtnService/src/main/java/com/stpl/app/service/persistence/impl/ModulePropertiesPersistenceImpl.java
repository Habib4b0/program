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

import com.stpl.app.exception.NoSuchModulePropertiesException;
import com.stpl.app.model.ModuleProperties;
import com.stpl.app.model.impl.ModulePropertiesImpl;
import com.stpl.app.model.impl.ModulePropertiesModelImpl;
import com.stpl.app.service.persistence.ModulePropertiesPersistence;

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
 * The persistence implementation for the module properties service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ModulePropertiesPersistence
 * @see com.stpl.app.service.persistence.ModulePropertiesUtil
 * @generated
 */
@ProviderType
public class ModulePropertiesPersistenceImpl extends BasePersistenceImpl<ModuleProperties>
	implements ModulePropertiesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModulePropertiesUtil} to access the module properties persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ModulePropertiesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesModelImpl.FINDER_CACHE_ENABLED,
			ModulePropertiesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesModelImpl.FINDER_CACHE_ENABLED,
			ModulePropertiesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ModulePropertiesPersistenceImpl() {
		setModelClass(ModuleProperties.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("modulePropertySid", "MODULE_PROPERTY_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("nullFlag", "NULL_FLAG");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("moduleSubmoduleSid", "MODULE_SUBMODULE_SID");
			dbColumnNames.put("categoryName", "CATEGORY_NAME");
			dbColumnNames.put("propertyName", "PROPERTY_NAME");
			dbColumnNames.put("displayName", "DISPLAY_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the module properties in the entity cache if it is enabled.
	 *
	 * @param moduleProperties the module properties
	 */
	@Override
	public void cacheResult(ModuleProperties moduleProperties) {
		entityCache.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesImpl.class, moduleProperties.getPrimaryKey(),
			moduleProperties);

		moduleProperties.resetOriginalValues();
	}

	/**
	 * Caches the module propertieses in the entity cache if it is enabled.
	 *
	 * @param modulePropertieses the module propertieses
	 */
	@Override
	public void cacheResult(List<ModuleProperties> modulePropertieses) {
		for (ModuleProperties moduleProperties : modulePropertieses) {
			if (entityCache.getResult(
						ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
						ModulePropertiesImpl.class,
						moduleProperties.getPrimaryKey()) == null) {
				cacheResult(moduleProperties);
			}
			else {
				moduleProperties.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all module propertieses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ModulePropertiesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the module properties.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ModuleProperties moduleProperties) {
		entityCache.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesImpl.class, moduleProperties.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ModuleProperties> modulePropertieses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ModuleProperties moduleProperties : modulePropertieses) {
			entityCache.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
				ModulePropertiesImpl.class, moduleProperties.getPrimaryKey());
		}
	}

	/**
	 * Creates a new module properties with the primary key. Does not add the module properties to the database.
	 *
	 * @param modulePropertySid the primary key for the new module properties
	 * @return the new module properties
	 */
	@Override
	public ModuleProperties create(int modulePropertySid) {
		ModuleProperties moduleProperties = new ModulePropertiesImpl();

		moduleProperties.setNew(true);
		moduleProperties.setPrimaryKey(modulePropertySid);

		return moduleProperties;
	}

	/**
	 * Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param modulePropertySid the primary key of the module properties
	 * @return the module properties that was removed
	 * @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties remove(int modulePropertySid)
		throws NoSuchModulePropertiesException {
		return remove((Serializable)modulePropertySid);
	}

	/**
	 * Removes the module properties with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the module properties
	 * @return the module properties that was removed
	 * @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties remove(Serializable primaryKey)
		throws NoSuchModulePropertiesException {
		Session session = null;

		try {
			session = openSession();

			ModuleProperties moduleProperties = (ModuleProperties)session.get(ModulePropertiesImpl.class,
					primaryKey);

			if (moduleProperties == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModulePropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(moduleProperties);
		}
		catch (NoSuchModulePropertiesException nsee) {
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
	protected ModuleProperties removeImpl(ModuleProperties moduleProperties) {
		moduleProperties = toUnwrappedModel(moduleProperties);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(moduleProperties)) {
				moduleProperties = (ModuleProperties)session.get(ModulePropertiesImpl.class,
						moduleProperties.getPrimaryKeyObj());
			}

			if (moduleProperties != null) {
				session.delete(moduleProperties);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (moduleProperties != null) {
			clearCache(moduleProperties);
		}

		return moduleProperties;
	}

	@Override
	public ModuleProperties updateImpl(ModuleProperties moduleProperties) {
		moduleProperties = toUnwrappedModel(moduleProperties);

		boolean isNew = moduleProperties.isNew();

		Session session = null;

		try {
			session = openSession();

			if (moduleProperties.isNew()) {
				session.save(moduleProperties);

				moduleProperties.setNew(false);
			}
			else {
				moduleProperties = (ModuleProperties)session.merge(moduleProperties);
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

		entityCache.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
			ModulePropertiesImpl.class, moduleProperties.getPrimaryKey(),
			moduleProperties, false);

		moduleProperties.resetOriginalValues();

		return moduleProperties;
	}

	protected ModuleProperties toUnwrappedModel(
		ModuleProperties moduleProperties) {
		if (moduleProperties instanceof ModulePropertiesImpl) {
			return moduleProperties;
		}

		ModulePropertiesImpl modulePropertiesImpl = new ModulePropertiesImpl();

		modulePropertiesImpl.setNew(moduleProperties.isNew());
		modulePropertiesImpl.setPrimaryKey(moduleProperties.getPrimaryKey());

		modulePropertiesImpl.setModulePropertySid(moduleProperties.getModulePropertySid());
		modulePropertiesImpl.setCreatedBy(moduleProperties.getCreatedBy());
		modulePropertiesImpl.setModuleName(moduleProperties.getModuleName());
		modulePropertiesImpl.setModifiedBy(moduleProperties.getModifiedBy());
		modulePropertiesImpl.setCreatedDate(moduleProperties.getCreatedDate());
		modulePropertiesImpl.setNullFlag(moduleProperties.getNullFlag());
		modulePropertiesImpl.setVersionNo(moduleProperties.getVersionNo());
		modulePropertiesImpl.setModuleSubmoduleSid(moduleProperties.getModuleSubmoduleSid());
		modulePropertiesImpl.setCategoryName(moduleProperties.getCategoryName());
		modulePropertiesImpl.setPropertyName(moduleProperties.getPropertyName());
		modulePropertiesImpl.setDisplayName(moduleProperties.getDisplayName());
		modulePropertiesImpl.setModifiedDate(moduleProperties.getModifiedDate());

		return modulePropertiesImpl;
	}

	/**
	 * Returns the module properties with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the module properties
	 * @return the module properties
	 * @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModulePropertiesException {
		ModuleProperties moduleProperties = fetchByPrimaryKey(primaryKey);

		if (moduleProperties == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchModulePropertiesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return moduleProperties;
	}

	/**
	 * Returns the module properties with the primary key or throws a {@link NoSuchModulePropertiesException} if it could not be found.
	 *
	 * @param modulePropertySid the primary key of the module properties
	 * @return the module properties
	 * @throws NoSuchModulePropertiesException if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties findByPrimaryKey(int modulePropertySid)
		throws NoSuchModulePropertiesException {
		return findByPrimaryKey((Serializable)modulePropertySid);
	}

	/**
	 * Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the module properties
	 * @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
				ModulePropertiesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ModuleProperties moduleProperties = (ModuleProperties)serializable;

		if (moduleProperties == null) {
			Session session = null;

			try {
				session = openSession();

				moduleProperties = (ModuleProperties)session.get(ModulePropertiesImpl.class,
						primaryKey);

				if (moduleProperties != null) {
					cacheResult(moduleProperties);
				}
				else {
					entityCache.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
						ModulePropertiesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
					ModulePropertiesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return moduleProperties;
	}

	/**
	 * Returns the module properties with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param modulePropertySid the primary key of the module properties
	 * @return the module properties, or <code>null</code> if a module properties with the primary key could not be found
	 */
	@Override
	public ModuleProperties fetchByPrimaryKey(int modulePropertySid) {
		return fetchByPrimaryKey((Serializable)modulePropertySid);
	}

	@Override
	public Map<Serializable, ModuleProperties> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ModuleProperties> map = new HashMap<Serializable, ModuleProperties>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ModuleProperties moduleProperties = fetchByPrimaryKey(primaryKey);

			if (moduleProperties != null) {
				map.put(primaryKey, moduleProperties);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
					ModulePropertiesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ModuleProperties)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MODULEPROPERTIES_WHERE_PKS_IN);

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

			for (ModuleProperties moduleProperties : (List<ModuleProperties>)q.list()) {
				map.put(moduleProperties.getPrimaryKeyObj(), moduleProperties);

				cacheResult(moduleProperties);

				uncachedPrimaryKeys.remove(moduleProperties.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ModulePropertiesModelImpl.ENTITY_CACHE_ENABLED,
					ModulePropertiesImpl.class, primaryKey, nullModel);
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
	 * Returns all the module propertieses.
	 *
	 * @return the module propertieses
	 */
	@Override
	public List<ModuleProperties> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the module propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module propertieses
	 * @param end the upper bound of the range of module propertieses (not inclusive)
	 * @return the range of module propertieses
	 */
	@Override
	public List<ModuleProperties> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the module propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module propertieses
	 * @param end the upper bound of the range of module propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of module propertieses
	 */
	@Override
	public List<ModuleProperties> findAll(int start, int end,
		OrderByComparator<ModuleProperties> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the module propertieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ModulePropertiesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of module propertieses
	 * @param end the upper bound of the range of module propertieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of module propertieses
	 */
	@Override
	public List<ModuleProperties> findAll(int start, int end,
		OrderByComparator<ModuleProperties> orderByComparator,
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

		List<ModuleProperties> list = null;

		if (retrieveFromCache) {
			list = (List<ModuleProperties>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MODULEPROPERTIES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODULEPROPERTIES;

				if (pagination) {
					sql = sql.concat(ModulePropertiesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ModuleProperties>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ModuleProperties>)QueryUtil.list(q,
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
	 * Removes all the module propertieses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ModuleProperties moduleProperties : findAll()) {
			remove(moduleProperties);
		}
	}

	/**
	 * Returns the number of module propertieses.
	 *
	 * @return the number of module propertieses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MODULEPROPERTIES);

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
		return ModulePropertiesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the module properties persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ModulePropertiesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MODULEPROPERTIES = "SELECT moduleProperties FROM ModuleProperties moduleProperties";
	private static final String _SQL_SELECT_MODULEPROPERTIES_WHERE_PKS_IN = "SELECT moduleProperties FROM ModuleProperties moduleProperties WHERE MODULE_PROPERTY_SID IN (";
	private static final String _SQL_COUNT_MODULEPROPERTIES = "SELECT COUNT(moduleProperties) FROM ModuleProperties moduleProperties";
	private static final String _ORDER_BY_ENTITY_ALIAS = "moduleProperties.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModuleProperties exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ModulePropertiesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"modulePropertySid", "createdBy", "moduleName", "modifiedBy",
				"createdDate", "nullFlag", "versionNo", "moduleSubmoduleSid",
				"categoryName", "propertyName", "displayName", "modifiedDate"
			});
}