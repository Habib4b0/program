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

import com.stpl.app.exception.NoSuchRelationshipLevelDefinitionException;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.model.impl.RelationshipLevelDefinitionImpl;
import com.stpl.app.model.impl.RelationshipLevelDefinitionModelImpl;
import com.stpl.app.service.persistence.RelationshipLevelDefinitionPersistence;

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
 * The persistence implementation for the relationship level definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipLevelDefinitionPersistence
 * @see com.stpl.app.service.persistence.RelationshipLevelDefinitionUtil
 * @generated
 */
@ProviderType
public class RelationshipLevelDefinitionPersistenceImpl
	extends BasePersistenceImpl<RelationshipLevelDefinition>
	implements RelationshipLevelDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RelationshipLevelDefinitionUtil} to access the relationship level definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RelationshipLevelDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
			RelationshipLevelDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
			RelationshipLevelDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public RelationshipLevelDefinitionPersistenceImpl() {
		setModelClass(RelationshipLevelDefinition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("relationshipLevelValues",
				"RELATIONSHIP_LEVEL_VALUES");
			dbColumnNames.put("hierarchyLevelDefinitionSid",
				"HIERARCHY_LEVEL_DEFINITION_SID");
			dbColumnNames.put("parentNode", "PARENT_NODE");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("relationshipBuilderSid",
				"RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("levelNo", "LEVEL_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("hierarchyNo", "HIERARCHY_NO");
			dbColumnNames.put("relationshipLevelSid", "RELATIONSHIP_LEVEL_SID");
			dbColumnNames.put("flag", "FLAG");
			dbColumnNames.put("levelName", "LEVEL_NAME");
			dbColumnNames.put("parentHierarchyNo", "PARENT_HIERARCHY_NO");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the relationship level definition in the entity cache if it is enabled.
	 *
	 * @param relationshipLevelDefinition the relationship level definition
	 */
	@Override
	public void cacheResult(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		entityCache.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionImpl.class,
			relationshipLevelDefinition.getPrimaryKey(),
			relationshipLevelDefinition);

		relationshipLevelDefinition.resetOriginalValues();
	}

	/**
	 * Caches the relationship level definitions in the entity cache if it is enabled.
	 *
	 * @param relationshipLevelDefinitions the relationship level definitions
	 */
	@Override
	public void cacheResult(
		List<RelationshipLevelDefinition> relationshipLevelDefinitions) {
		for (RelationshipLevelDefinition relationshipLevelDefinition : relationshipLevelDefinitions) {
			if (entityCache.getResult(
						RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						RelationshipLevelDefinitionImpl.class,
						relationshipLevelDefinition.getPrimaryKey()) == null) {
				cacheResult(relationshipLevelDefinition);
			}
			else {
				relationshipLevelDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all relationship level definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RelationshipLevelDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the relationship level definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		entityCache.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionImpl.class,
			relationshipLevelDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<RelationshipLevelDefinition> relationshipLevelDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RelationshipLevelDefinition relationshipLevelDefinition : relationshipLevelDefinitions) {
			entityCache.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				RelationshipLevelDefinitionImpl.class,
				relationshipLevelDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new relationship level definition with the primary key. Does not add the relationship level definition to the database.
	 *
	 * @param relationshipLevelSid the primary key for the new relationship level definition
	 * @return the new relationship level definition
	 */
	@Override
	public RelationshipLevelDefinition create(int relationshipLevelSid) {
		RelationshipLevelDefinition relationshipLevelDefinition = new RelationshipLevelDefinitionImpl();

		relationshipLevelDefinition.setNew(true);
		relationshipLevelDefinition.setPrimaryKey(relationshipLevelSid);

		return relationshipLevelDefinition;
	}

	/**
	 * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param relationshipLevelSid the primary key of the relationship level definition
	 * @return the relationship level definition that was removed
	 * @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition remove(int relationshipLevelSid)
		throws NoSuchRelationshipLevelDefinitionException {
		return remove((Serializable)relationshipLevelSid);
	}

	/**
	 * Removes the relationship level definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the relationship level definition
	 * @return the relationship level definition that was removed
	 * @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition remove(Serializable primaryKey)
		throws NoSuchRelationshipLevelDefinitionException {
		Session session = null;

		try {
			session = openSession();

			RelationshipLevelDefinition relationshipLevelDefinition = (RelationshipLevelDefinition)session.get(RelationshipLevelDefinitionImpl.class,
					primaryKey);

			if (relationshipLevelDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(relationshipLevelDefinition);
		}
		catch (NoSuchRelationshipLevelDefinitionException nsee) {
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
	protected RelationshipLevelDefinition removeImpl(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		relationshipLevelDefinition = toUnwrappedModel(relationshipLevelDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(relationshipLevelDefinition)) {
				relationshipLevelDefinition = (RelationshipLevelDefinition)session.get(RelationshipLevelDefinitionImpl.class,
						relationshipLevelDefinition.getPrimaryKeyObj());
			}

			if (relationshipLevelDefinition != null) {
				session.delete(relationshipLevelDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (relationshipLevelDefinition != null) {
			clearCache(relationshipLevelDefinition);
		}

		return relationshipLevelDefinition;
	}

	@Override
	public RelationshipLevelDefinition updateImpl(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		relationshipLevelDefinition = toUnwrappedModel(relationshipLevelDefinition);

		boolean isNew = relationshipLevelDefinition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (relationshipLevelDefinition.isNew()) {
				session.save(relationshipLevelDefinition);

				relationshipLevelDefinition.setNew(false);
			}
			else {
				relationshipLevelDefinition = (RelationshipLevelDefinition)session.merge(relationshipLevelDefinition);
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

		entityCache.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipLevelDefinitionImpl.class,
			relationshipLevelDefinition.getPrimaryKey(),
			relationshipLevelDefinition, false);

		relationshipLevelDefinition.resetOriginalValues();

		return relationshipLevelDefinition;
	}

	protected RelationshipLevelDefinition toUnwrappedModel(
		RelationshipLevelDefinition relationshipLevelDefinition) {
		if (relationshipLevelDefinition instanceof RelationshipLevelDefinitionImpl) {
			return relationshipLevelDefinition;
		}

		RelationshipLevelDefinitionImpl relationshipLevelDefinitionImpl = new RelationshipLevelDefinitionImpl();

		relationshipLevelDefinitionImpl.setNew(relationshipLevelDefinition.isNew());
		relationshipLevelDefinitionImpl.setPrimaryKey(relationshipLevelDefinition.getPrimaryKey());

		relationshipLevelDefinitionImpl.setRelationshipLevelValues(relationshipLevelDefinition.getRelationshipLevelValues());
		relationshipLevelDefinitionImpl.setHierarchyLevelDefinitionSid(relationshipLevelDefinition.getHierarchyLevelDefinitionSid());
		relationshipLevelDefinitionImpl.setParentNode(relationshipLevelDefinition.getParentNode());
		relationshipLevelDefinitionImpl.setVersionNo(relationshipLevelDefinition.getVersionNo());
		relationshipLevelDefinitionImpl.setRelationshipBuilderSid(relationshipLevelDefinition.getRelationshipBuilderSid());
		relationshipLevelDefinitionImpl.setModifiedDate(relationshipLevelDefinition.getModifiedDate());
		relationshipLevelDefinitionImpl.setCreatedBy(relationshipLevelDefinition.getCreatedBy());
		relationshipLevelDefinitionImpl.setCreatedDate(relationshipLevelDefinition.getCreatedDate());
		relationshipLevelDefinitionImpl.setLevelNo(relationshipLevelDefinition.getLevelNo());
		relationshipLevelDefinitionImpl.setModifiedBy(relationshipLevelDefinition.getModifiedBy());
		relationshipLevelDefinitionImpl.setHierarchyNo(relationshipLevelDefinition.getHierarchyNo());
		relationshipLevelDefinitionImpl.setRelationshipLevelSid(relationshipLevelDefinition.getRelationshipLevelSid());
		relationshipLevelDefinitionImpl.setFlag(relationshipLevelDefinition.getFlag());
		relationshipLevelDefinitionImpl.setLevelName(relationshipLevelDefinition.getLevelName());
		relationshipLevelDefinitionImpl.setParentHierarchyNo(relationshipLevelDefinition.getParentHierarchyNo());

		return relationshipLevelDefinitionImpl;
	}

	/**
	 * Returns the relationship level definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the relationship level definition
	 * @return the relationship level definition
	 * @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationshipLevelDefinitionException {
		RelationshipLevelDefinition relationshipLevelDefinition = fetchByPrimaryKey(primaryKey);

		if (relationshipLevelDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationshipLevelDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return relationshipLevelDefinition;
	}

	/**
	 * Returns the relationship level definition with the primary key or throws a {@link NoSuchRelationshipLevelDefinitionException} if it could not be found.
	 *
	 * @param relationshipLevelSid the primary key of the relationship level definition
	 * @return the relationship level definition
	 * @throws NoSuchRelationshipLevelDefinitionException if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition findByPrimaryKey(
		int relationshipLevelSid)
		throws NoSuchRelationshipLevelDefinitionException {
		return findByPrimaryKey((Serializable)relationshipLevelSid);
	}

	/**
	 * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the relationship level definition
	 * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				RelationshipLevelDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RelationshipLevelDefinition relationshipLevelDefinition = (RelationshipLevelDefinition)serializable;

		if (relationshipLevelDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				relationshipLevelDefinition = (RelationshipLevelDefinition)session.get(RelationshipLevelDefinitionImpl.class,
						primaryKey);

				if (relationshipLevelDefinition != null) {
					cacheResult(relationshipLevelDefinition);
				}
				else {
					entityCache.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						RelationshipLevelDefinitionImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipLevelDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return relationshipLevelDefinition;
	}

	/**
	 * Returns the relationship level definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param relationshipLevelSid the primary key of the relationship level definition
	 * @return the relationship level definition, or <code>null</code> if a relationship level definition with the primary key could not be found
	 */
	@Override
	public RelationshipLevelDefinition fetchByPrimaryKey(
		int relationshipLevelSid) {
		return fetchByPrimaryKey((Serializable)relationshipLevelSid);
	}

	@Override
	public Map<Serializable, RelationshipLevelDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RelationshipLevelDefinition> map = new HashMap<Serializable, RelationshipLevelDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RelationshipLevelDefinition relationshipLevelDefinition = fetchByPrimaryKey(primaryKey);

			if (relationshipLevelDefinition != null) {
				map.put(primaryKey, relationshipLevelDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipLevelDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(RelationshipLevelDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RELATIONSHIPLEVELDEFINITION_WHERE_PKS_IN);

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

			for (RelationshipLevelDefinition relationshipLevelDefinition : (List<RelationshipLevelDefinition>)q.list()) {
				map.put(relationshipLevelDefinition.getPrimaryKeyObj(),
					relationshipLevelDefinition);

				cacheResult(relationshipLevelDefinition);

				uncachedPrimaryKeys.remove(relationshipLevelDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RelationshipLevelDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipLevelDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the relationship level definitions.
	 *
	 * @return the relationship level definitions
	 */
	@Override
	public List<RelationshipLevelDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the relationship level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship level definitions
	 * @param end the upper bound of the range of relationship level definitions (not inclusive)
	 * @return the range of relationship level definitions
	 */
	@Override
	public List<RelationshipLevelDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the relationship level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship level definitions
	 * @param end the upper bound of the range of relationship level definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of relationship level definitions
	 */
	@Override
	public List<RelationshipLevelDefinition> findAll(int start, int end,
		OrderByComparator<RelationshipLevelDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the relationship level definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipLevelDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship level definitions
	 * @param end the upper bound of the range of relationship level definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of relationship level definitions
	 */
	@Override
	public List<RelationshipLevelDefinition> findAll(int start, int end,
		OrderByComparator<RelationshipLevelDefinition> orderByComparator,
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

		List<RelationshipLevelDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<RelationshipLevelDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RELATIONSHIPLEVELDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RELATIONSHIPLEVELDEFINITION;

				if (pagination) {
					sql = sql.concat(RelationshipLevelDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RelationshipLevelDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RelationshipLevelDefinition>)QueryUtil.list(q,
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
	 * Removes all the relationship level definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RelationshipLevelDefinition relationshipLevelDefinition : findAll()) {
			remove(relationshipLevelDefinition);
		}
	}

	/**
	 * Returns the number of relationship level definitions.
	 *
	 * @return the number of relationship level definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RELATIONSHIPLEVELDEFINITION);

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
		return RelationshipLevelDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the relationship level definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RelationshipLevelDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RELATIONSHIPLEVELDEFINITION = "SELECT relationshipLevelDefinition FROM RelationshipLevelDefinition relationshipLevelDefinition";
	private static final String _SQL_SELECT_RELATIONSHIPLEVELDEFINITION_WHERE_PKS_IN =
		"SELECT relationshipLevelDefinition FROM RelationshipLevelDefinition relationshipLevelDefinition WHERE RELATIONSHIP_LEVEL_SID IN (";
	private static final String _SQL_COUNT_RELATIONSHIPLEVELDEFINITION = "SELECT COUNT(relationshipLevelDefinition) FROM RelationshipLevelDefinition relationshipLevelDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "relationshipLevelDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RelationshipLevelDefinition exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RelationshipLevelDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"relationshipLevelValues", "hierarchyLevelDefinitionSid",
				"parentNode", "versionNo", "relationshipBuilderSid",
				"modifiedDate", "createdBy", "createdDate", "levelNo",
				"modifiedBy", "hierarchyNo", "relationshipLevelSid", "flag",
				"levelName", "parentHierarchyNo"
			});
}