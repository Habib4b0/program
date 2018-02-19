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

import com.stpl.app.exception.NoSuchRelationshipBuilderException;
import com.stpl.app.model.RelationshipBuilder;
import com.stpl.app.model.impl.RelationshipBuilderImpl;
import com.stpl.app.model.impl.RelationshipBuilderModelImpl;
import com.stpl.app.service.persistence.RelationshipBuilderPersistence;

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
 * The persistence implementation for the relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RelationshipBuilderPersistence
 * @see com.stpl.app.service.persistence.RelationshipBuilderUtil
 * @generated
 */
@ProviderType
public class RelationshipBuilderPersistenceImpl extends BasePersistenceImpl<RelationshipBuilder>
	implements RelationshipBuilderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RelationshipBuilderUtil} to access the relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RelationshipBuilderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
			RelationshipBuilderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
			RelationshipBuilderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RelationshipBuilderPersistenceImpl() {
		setModelClass(RelationshipBuilder.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("relationshipDescription",
				"RELATIONSHIP_DESCRIPTION");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("relationshipName", "RELATIONSHIP_NAME");
			dbColumnNames.put("relationshipBuilderSid",
				"RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("hierarchyVersion", "HIERARCHY_VERSION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("deductionRelation", "DEDUCTION_RELATION");
			dbColumnNames.put("relationshipType", "RELATIONSHIP_TYPE");
			dbColumnNames.put("buildType", "BUILD_TYPE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the relationship builder in the entity cache if it is enabled.
	 *
	 * @param relationshipBuilder the relationship builder
	 */
	@Override
	public void cacheResult(RelationshipBuilder relationshipBuilder) {
		entityCache.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey(),
			relationshipBuilder);

		relationshipBuilder.resetOriginalValues();
	}

	/**
	 * Caches the relationship builders in the entity cache if it is enabled.
	 *
	 * @param relationshipBuilders the relationship builders
	 */
	@Override
	public void cacheResult(List<RelationshipBuilder> relationshipBuilders) {
		for (RelationshipBuilder relationshipBuilder : relationshipBuilders) {
			if (entityCache.getResult(
						RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
						RelationshipBuilderImpl.class,
						relationshipBuilder.getPrimaryKey()) == null) {
				cacheResult(relationshipBuilder);
			}
			else {
				relationshipBuilder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all relationship builders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RelationshipBuilderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the relationship builder.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RelationshipBuilder relationshipBuilder) {
		entityCache.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RelationshipBuilder> relationshipBuilders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RelationshipBuilder relationshipBuilder : relationshipBuilders) {
			entityCache.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
				RelationshipBuilderImpl.class,
				relationshipBuilder.getPrimaryKey());
		}
	}

	/**
	 * Creates a new relationship builder with the primary key. Does not add the relationship builder to the database.
	 *
	 * @param relationshipBuilderSid the primary key for the new relationship builder
	 * @return the new relationship builder
	 */
	@Override
	public RelationshipBuilder create(int relationshipBuilderSid) {
		RelationshipBuilder relationshipBuilder = new RelationshipBuilderImpl();

		relationshipBuilder.setNew(true);
		relationshipBuilder.setPrimaryKey(relationshipBuilderSid);

		return relationshipBuilder;
	}

	/**
	 * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param relationshipBuilderSid the primary key of the relationship builder
	 * @return the relationship builder that was removed
	 * @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder remove(int relationshipBuilderSid)
		throws NoSuchRelationshipBuilderException {
		return remove((Serializable)relationshipBuilderSid);
	}

	/**
	 * Removes the relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the relationship builder
	 * @return the relationship builder that was removed
	 * @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder remove(Serializable primaryKey)
		throws NoSuchRelationshipBuilderException {
		Session session = null;

		try {
			session = openSession();

			RelationshipBuilder relationshipBuilder = (RelationshipBuilder)session.get(RelationshipBuilderImpl.class,
					primaryKey);

			if (relationshipBuilder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(relationshipBuilder);
		}
		catch (NoSuchRelationshipBuilderException nsee) {
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
	protected RelationshipBuilder removeImpl(
		RelationshipBuilder relationshipBuilder) {
		relationshipBuilder = toUnwrappedModel(relationshipBuilder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(relationshipBuilder)) {
				relationshipBuilder = (RelationshipBuilder)session.get(RelationshipBuilderImpl.class,
						relationshipBuilder.getPrimaryKeyObj());
			}

			if (relationshipBuilder != null) {
				session.delete(relationshipBuilder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (relationshipBuilder != null) {
			clearCache(relationshipBuilder);
		}

		return relationshipBuilder;
	}

	@Override
	public RelationshipBuilder updateImpl(
		RelationshipBuilder relationshipBuilder) {
		relationshipBuilder = toUnwrappedModel(relationshipBuilder);

		boolean isNew = relationshipBuilder.isNew();

		Session session = null;

		try {
			session = openSession();

			if (relationshipBuilder.isNew()) {
				session.save(relationshipBuilder);

				relationshipBuilder.setNew(false);
			}
			else {
				relationshipBuilder = (RelationshipBuilder)session.merge(relationshipBuilder);
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

		entityCache.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			RelationshipBuilderImpl.class, relationshipBuilder.getPrimaryKey(),
			relationshipBuilder, false);

		relationshipBuilder.resetOriginalValues();

		return relationshipBuilder;
	}

	protected RelationshipBuilder toUnwrappedModel(
		RelationshipBuilder relationshipBuilder) {
		if (relationshipBuilder instanceof RelationshipBuilderImpl) {
			return relationshipBuilder;
		}

		RelationshipBuilderImpl relationshipBuilderImpl = new RelationshipBuilderImpl();

		relationshipBuilderImpl.setNew(relationshipBuilder.isNew());
		relationshipBuilderImpl.setPrimaryKey(relationshipBuilder.getPrimaryKey());

		relationshipBuilderImpl.setStartDate(relationshipBuilder.getStartDate());
		relationshipBuilderImpl.setCreatedDate(relationshipBuilder.getCreatedDate());
		relationshipBuilderImpl.setCreatedBy(relationshipBuilder.getCreatedBy());
		relationshipBuilderImpl.setRelationshipDescription(relationshipBuilder.getRelationshipDescription());
		relationshipBuilderImpl.setHierarchyDefinitionSid(relationshipBuilder.getHierarchyDefinitionSid());
		relationshipBuilderImpl.setVersionNo(relationshipBuilder.getVersionNo());
		relationshipBuilderImpl.setRelationshipName(relationshipBuilder.getRelationshipName());
		relationshipBuilderImpl.setRelationshipBuilderSid(relationshipBuilder.getRelationshipBuilderSid());
		relationshipBuilderImpl.setHierarchyVersion(relationshipBuilder.getHierarchyVersion());
		relationshipBuilderImpl.setModifiedBy(relationshipBuilder.getModifiedBy());
		relationshipBuilderImpl.setModifiedDate(relationshipBuilder.getModifiedDate());
		relationshipBuilderImpl.setDeductionRelation(relationshipBuilder.getDeductionRelation());
		relationshipBuilderImpl.setRelationshipType(relationshipBuilder.getRelationshipType());
		relationshipBuilderImpl.setBuildType(relationshipBuilder.getBuildType());

		return relationshipBuilderImpl;
	}

	/**
	 * Returns the relationship builder with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the relationship builder
	 * @return the relationship builder
	 * @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRelationshipBuilderException {
		RelationshipBuilder relationshipBuilder = fetchByPrimaryKey(primaryKey);

		if (relationshipBuilder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return relationshipBuilder;
	}

	/**
	 * Returns the relationship builder with the primary key or throws a {@link NoSuchRelationshipBuilderException} if it could not be found.
	 *
	 * @param relationshipBuilderSid the primary key of the relationship builder
	 * @return the relationship builder
	 * @throws NoSuchRelationshipBuilderException if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder findByPrimaryKey(int relationshipBuilderSid)
		throws NoSuchRelationshipBuilderException {
		return findByPrimaryKey((Serializable)relationshipBuilderSid);
	}

	/**
	 * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the relationship builder
	 * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
				RelationshipBuilderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RelationshipBuilder relationshipBuilder = (RelationshipBuilder)serializable;

		if (relationshipBuilder == null) {
			Session session = null;

			try {
				session = openSession();

				relationshipBuilder = (RelationshipBuilder)session.get(RelationshipBuilderImpl.class,
						primaryKey);

				if (relationshipBuilder != null) {
					cacheResult(relationshipBuilder);
				}
				else {
					entityCache.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
						RelationshipBuilderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipBuilderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return relationshipBuilder;
	}

	/**
	 * Returns the relationship builder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param relationshipBuilderSid the primary key of the relationship builder
	 * @return the relationship builder, or <code>null</code> if a relationship builder with the primary key could not be found
	 */
	@Override
	public RelationshipBuilder fetchByPrimaryKey(int relationshipBuilderSid) {
		return fetchByPrimaryKey((Serializable)relationshipBuilderSid);
	}

	@Override
	public Map<Serializable, RelationshipBuilder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RelationshipBuilder> map = new HashMap<Serializable, RelationshipBuilder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RelationshipBuilder relationshipBuilder = fetchByPrimaryKey(primaryKey);

			if (relationshipBuilder != null) {
				map.put(primaryKey, relationshipBuilder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipBuilderImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RelationshipBuilder)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RELATIONSHIPBUILDER_WHERE_PKS_IN);

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

			for (RelationshipBuilder relationshipBuilder : (List<RelationshipBuilder>)q.list()) {
				map.put(relationshipBuilder.getPrimaryKeyObj(),
					relationshipBuilder);

				cacheResult(relationshipBuilder);

				uncachedPrimaryKeys.remove(relationshipBuilder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
					RelationshipBuilderImpl.class, primaryKey, nullModel);
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
	 * Returns all the relationship builders.
	 *
	 * @return the relationship builders
	 */
	@Override
	public List<RelationshipBuilder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship builders
	 * @param end the upper bound of the range of relationship builders (not inclusive)
	 * @return the range of relationship builders
	 */
	@Override
	public List<RelationshipBuilder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship builders
	 * @param end the upper bound of the range of relationship builders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of relationship builders
	 */
	@Override
	public List<RelationshipBuilder> findAll(int start, int end,
		OrderByComparator<RelationshipBuilder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of relationship builders
	 * @param end the upper bound of the range of relationship builders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of relationship builders
	 */
	@Override
	public List<RelationshipBuilder> findAll(int start, int end,
		OrderByComparator<RelationshipBuilder> orderByComparator,
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

		List<RelationshipBuilder> list = null;

		if (retrieveFromCache) {
			list = (List<RelationshipBuilder>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RELATIONSHIPBUILDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RELATIONSHIPBUILDER;

				if (pagination) {
					sql = sql.concat(RelationshipBuilderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RelationshipBuilder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RelationshipBuilder>)QueryUtil.list(q,
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
	 * Removes all the relationship builders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RelationshipBuilder relationshipBuilder : findAll()) {
			remove(relationshipBuilder);
		}
	}

	/**
	 * Returns the number of relationship builders.
	 *
	 * @return the number of relationship builders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RELATIONSHIPBUILDER);

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
		return RelationshipBuilderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the relationship builder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RelationshipBuilderImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RELATIONSHIPBUILDER = "SELECT relationshipBuilder FROM RelationshipBuilder relationshipBuilder";
	private static final String _SQL_SELECT_RELATIONSHIPBUILDER_WHERE_PKS_IN = "SELECT relationshipBuilder FROM RelationshipBuilder relationshipBuilder WHERE RELATIONSHIP_BUILDER_SID IN (";
	private static final String _SQL_COUNT_RELATIONSHIPBUILDER = "SELECT COUNT(relationshipBuilder) FROM RelationshipBuilder relationshipBuilder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "relationshipBuilder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RelationshipBuilder exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RelationshipBuilderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"startDate", "createdDate", "createdBy",
				"relationshipDescription", "hierarchyDefinitionSid", "versionNo",
				"relationshipName", "relationshipBuilderSid", "hierarchyVersion",
				"modifiedBy", "modifiedDate", "deductionRelation",
				"relationshipType", "buildType"
			});
}