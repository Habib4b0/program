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

import com.stpl.app.exception.NoSuchHierarchyLevelDefinitionException;
import com.stpl.app.model.HierarchyLevelDefinition;
import com.stpl.app.model.impl.HierarchyLevelDefinitionImpl;
import com.stpl.app.model.impl.HierarchyLevelDefinitionModelImpl;
import com.stpl.app.service.persistence.HierarchyLevelDefinitionPersistence;

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
 * The persistence implementation for the hierarchy level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelDefinitionPersistence
 * @see com.stpl.app.service.persistence.HierarchyLevelDefinitionUtil
 * @generated
 */
@ProviderType
public class HierarchyLevelDefinitionPersistenceImpl extends BasePersistenceImpl<HierarchyLevelDefinition>
	implements HierarchyLevelDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HierarchyLevelDefinitionUtil} to access the hierarchy level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HierarchyLevelDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HierarchyLevelDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HierarchyLevelDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HierarchyLevelDefinitionPersistenceImpl() {
		setModelClass(HierarchyLevelDefinition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("tableName", "TABLE_NAME");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("levelValueReference", "LEVEL_VALUE_REFERENCE");
			dbColumnNames.put("fieldName", "FIELD_NAME");
			dbColumnNames.put("levelNo", "LEVEL_NO");
			dbColumnNames.put("hierarchyLevelDefinitionSid",
				"HIERARCHY_LEVEL_DEFINITION_SID");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("levelName", "LEVEL_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hierarchy level definition in the entity cache if it is enabled.
	 *
	 * @param hierarchyLevelDefinition the hierarchy level definition
	 */
	@Override
	public void cacheResult(HierarchyLevelDefinition hierarchyLevelDefinition) {
		entityCache.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionImpl.class,
			hierarchyLevelDefinition.getPrimaryKey(), hierarchyLevelDefinition);

		hierarchyLevelDefinition.resetOriginalValues();
	}

	/**
	 * Caches the hierarchy level definitions in the entity cache if it is enabled.
	 *
	 * @param hierarchyLevelDefinitions the hierarchy level definitions
	 */
	@Override
	public void cacheResult(
		List<HierarchyLevelDefinition> hierarchyLevelDefinitions) {
		for (HierarchyLevelDefinition hierarchyLevelDefinition : hierarchyLevelDefinitions) {
			if (entityCache.getResult(
						HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyLevelDefinitionImpl.class,
						hierarchyLevelDefinition.getPrimaryKey()) == null) {
				cacheResult(hierarchyLevelDefinition);
			}
			else {
				hierarchyLevelDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hierarchy level definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HierarchyLevelDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hierarchy level definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HierarchyLevelDefinition hierarchyLevelDefinition) {
		entityCache.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionImpl.class,
			hierarchyLevelDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HierarchyLevelDefinition> hierarchyLevelDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HierarchyLevelDefinition hierarchyLevelDefinition : hierarchyLevelDefinitions) {
			entityCache.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyLevelDefinitionImpl.class,
				hierarchyLevelDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hierarchy level definition with the primary key. Does not add the hierarchy level definition to the database.
	 *
	 * @param hierarchyLevelDefinitionSid the primary key for the new hierarchy level definition
	 * @return the new hierarchy level definition
	 */
	@Override
	public HierarchyLevelDefinition create(int hierarchyLevelDefinitionSid) {
		HierarchyLevelDefinition hierarchyLevelDefinition = new HierarchyLevelDefinitionImpl();

		hierarchyLevelDefinition.setNew(true);
		hierarchyLevelDefinition.setPrimaryKey(hierarchyLevelDefinitionSid);

		return hierarchyLevelDefinition;
	}

	/**
	 * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	 * @return the hierarchy level definition that was removed
	 * @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition remove(int hierarchyLevelDefinitionSid)
		throws NoSuchHierarchyLevelDefinitionException {
		return remove((Serializable)hierarchyLevelDefinitionSid);
	}

	/**
	 * Removes the hierarchy level definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hierarchy level definition
	 * @return the hierarchy level definition that was removed
	 * @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition remove(Serializable primaryKey)
		throws NoSuchHierarchyLevelDefinitionException {
		Session session = null;

		try {
			session = openSession();

			HierarchyLevelDefinition hierarchyLevelDefinition = (HierarchyLevelDefinition)session.get(HierarchyLevelDefinitionImpl.class,
					primaryKey);

			if (hierarchyLevelDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHierarchyLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(hierarchyLevelDefinition);
		}
		catch (NoSuchHierarchyLevelDefinitionException nsee) {
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
	protected HierarchyLevelDefinition removeImpl(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		hierarchyLevelDefinition = toUnwrappedModel(hierarchyLevelDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(hierarchyLevelDefinition)) {
				hierarchyLevelDefinition = (HierarchyLevelDefinition)session.get(HierarchyLevelDefinitionImpl.class,
						hierarchyLevelDefinition.getPrimaryKeyObj());
			}

			if (hierarchyLevelDefinition != null) {
				session.delete(hierarchyLevelDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (hierarchyLevelDefinition != null) {
			clearCache(hierarchyLevelDefinition);
		}

		return hierarchyLevelDefinition;
	}

	@Override
	public HierarchyLevelDefinition updateImpl(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		hierarchyLevelDefinition = toUnwrappedModel(hierarchyLevelDefinition);

		boolean isNew = hierarchyLevelDefinition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (hierarchyLevelDefinition.isNew()) {
				session.save(hierarchyLevelDefinition);

				hierarchyLevelDefinition.setNew(false);
			}
			else {
				hierarchyLevelDefinition = (HierarchyLevelDefinition)session.merge(hierarchyLevelDefinition);
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

		entityCache.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelDefinitionImpl.class,
			hierarchyLevelDefinition.getPrimaryKey(), hierarchyLevelDefinition,
			false);

		hierarchyLevelDefinition.resetOriginalValues();

		return hierarchyLevelDefinition;
	}

	protected HierarchyLevelDefinition toUnwrappedModel(
		HierarchyLevelDefinition hierarchyLevelDefinition) {
		if (hierarchyLevelDefinition instanceof HierarchyLevelDefinitionImpl) {
			return hierarchyLevelDefinition;
		}

		HierarchyLevelDefinitionImpl hierarchyLevelDefinitionImpl = new HierarchyLevelDefinitionImpl();

		hierarchyLevelDefinitionImpl.setNew(hierarchyLevelDefinition.isNew());
		hierarchyLevelDefinitionImpl.setPrimaryKey(hierarchyLevelDefinition.getPrimaryKey());

		hierarchyLevelDefinitionImpl.setTableName(hierarchyLevelDefinition.getTableName());
		hierarchyLevelDefinitionImpl.setCreatedDate(hierarchyLevelDefinition.getCreatedDate());
		hierarchyLevelDefinitionImpl.setCreatedBy(hierarchyLevelDefinition.getCreatedBy());
		hierarchyLevelDefinitionImpl.setLevelValueReference(hierarchyLevelDefinition.getLevelValueReference());
		hierarchyLevelDefinitionImpl.setFieldName(hierarchyLevelDefinition.getFieldName());
		hierarchyLevelDefinitionImpl.setLevelNo(hierarchyLevelDefinition.getLevelNo());
		hierarchyLevelDefinitionImpl.setHierarchyLevelDefinitionSid(hierarchyLevelDefinition.getHierarchyLevelDefinitionSid());
		hierarchyLevelDefinitionImpl.setHierarchyDefinitionSid(hierarchyLevelDefinition.getHierarchyDefinitionSid());
		hierarchyLevelDefinitionImpl.setVersionNo(hierarchyLevelDefinition.getVersionNo());
		hierarchyLevelDefinitionImpl.setModifiedBy(hierarchyLevelDefinition.getModifiedBy());
		hierarchyLevelDefinitionImpl.setModifiedDate(hierarchyLevelDefinition.getModifiedDate());
		hierarchyLevelDefinitionImpl.setLevelName(hierarchyLevelDefinition.getLevelName());

		return hierarchyLevelDefinitionImpl;
	}

	/**
	 * Returns the hierarchy level definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy level definition
	 * @return the hierarchy level definition
	 * @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHierarchyLevelDefinitionException {
		HierarchyLevelDefinition hierarchyLevelDefinition = fetchByPrimaryKey(primaryKey);

		if (hierarchyLevelDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHierarchyLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return hierarchyLevelDefinition;
	}

	/**
	 * Returns the hierarchy level definition with the primary key or throws a {@link NoSuchHierarchyLevelDefinitionException} if it could not be found.
	 *
	 * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	 * @return the hierarchy level definition
	 * @throws NoSuchHierarchyLevelDefinitionException if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition findByPrimaryKey(
		int hierarchyLevelDefinitionSid)
		throws NoSuchHierarchyLevelDefinitionException {
		return findByPrimaryKey((Serializable)hierarchyLevelDefinitionSid);
	}

	/**
	 * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy level definition
	 * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyLevelDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HierarchyLevelDefinition hierarchyLevelDefinition = (HierarchyLevelDefinition)serializable;

		if (hierarchyLevelDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				hierarchyLevelDefinition = (HierarchyLevelDefinition)session.get(HierarchyLevelDefinitionImpl.class,
						primaryKey);

				if (hierarchyLevelDefinition != null) {
					cacheResult(hierarchyLevelDefinition);
				}
				else {
					entityCache.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyLevelDefinitionImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return hierarchyLevelDefinition;
	}

	/**
	 * Returns the hierarchy level definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hierarchyLevelDefinitionSid the primary key of the hierarchy level definition
	 * @return the hierarchy level definition, or <code>null</code> if a hierarchy level definition with the primary key could not be found
	 */
	@Override
	public HierarchyLevelDefinition fetchByPrimaryKey(
		int hierarchyLevelDefinitionSid) {
		return fetchByPrimaryKey((Serializable)hierarchyLevelDefinitionSid);
	}

	@Override
	public Map<Serializable, HierarchyLevelDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HierarchyLevelDefinition> map = new HashMap<Serializable, HierarchyLevelDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HierarchyLevelDefinition hierarchyLevelDefinition = fetchByPrimaryKey(primaryKey);

			if (hierarchyLevelDefinition != null) {
				map.put(primaryKey, hierarchyLevelDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HierarchyLevelDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HIERARCHYLEVELDEFINITION_WHERE_PKS_IN);

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

			for (HierarchyLevelDefinition hierarchyLevelDefinition : (List<HierarchyLevelDefinition>)q.list()) {
				map.put(hierarchyLevelDefinition.getPrimaryKeyObj(),
					hierarchyLevelDefinition);

				cacheResult(hierarchyLevelDefinition);

				uncachedPrimaryKeys.remove(hierarchyLevelDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HierarchyLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the hierarchy level definitions.
	 *
	 * @return the hierarchy level definitions
	 */
	@Override
	public List<HierarchyLevelDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hierarchy level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level definitions
	 * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	 * @return the range of hierarchy level definitions
	 */
	@Override
	public List<HierarchyLevelDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hierarchy level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level definitions
	 * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hierarchy level definitions
	 */
	@Override
	public List<HierarchyLevelDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyLevelDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hierarchy level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level definitions
	 * @param end the upper bound of the range of hierarchy level definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hierarchy level definitions
	 */
	@Override
	public List<HierarchyLevelDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyLevelDefinition> orderByComparator,
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

		List<HierarchyLevelDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<HierarchyLevelDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HIERARCHYLEVELDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HIERARCHYLEVELDEFINITION;

				if (pagination) {
					sql = sql.concat(HierarchyLevelDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HierarchyLevelDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HierarchyLevelDefinition>)QueryUtil.list(q,
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
	 * Removes all the hierarchy level definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HierarchyLevelDefinition hierarchyLevelDefinition : findAll()) {
			remove(hierarchyLevelDefinition);
		}
	}

	/**
	 * Returns the number of hierarchy level definitions.
	 *
	 * @return the number of hierarchy level definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HIERARCHYLEVELDEFINITION);

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
		return HierarchyLevelDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hierarchy level definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HierarchyLevelDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HIERARCHYLEVELDEFINITION = "SELECT hierarchyLevelDefinition FROM HierarchyLevelDefinition hierarchyLevelDefinition";
	private static final String _SQL_SELECT_HIERARCHYLEVELDEFINITION_WHERE_PKS_IN =
		"SELECT hierarchyLevelDefinition FROM HierarchyLevelDefinition hierarchyLevelDefinition WHERE HIERARCHY_LEVEL_DEFINITION_SID IN (";
	private static final String _SQL_COUNT_HIERARCHYLEVELDEFINITION = "SELECT COUNT(hierarchyLevelDefinition) FROM HierarchyLevelDefinition hierarchyLevelDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyLevelDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyLevelDefinition exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HierarchyLevelDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"tableName", "createdDate", "createdBy", "levelValueReference",
				"fieldName", "levelNo", "hierarchyLevelDefinitionSid",
				"hierarchyDefinitionSid", "versionNo", "modifiedBy",
				"modifiedDate", "levelName"
			});
}