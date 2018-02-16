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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchHistRelationshipBuilderException;
import com.stpl.app.model.HistRelationshipBuilder;
import com.stpl.app.model.impl.HistRelationshipBuilderImpl;
import com.stpl.app.model.impl.HistRelationshipBuilderModelImpl;
import com.stpl.app.service.persistence.HistRelationshipBuilderPK;
import com.stpl.app.service.persistence.HistRelationshipBuilderPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist relationship builder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipBuilderPersistence
 * @see com.stpl.app.service.persistence.HistRelationshipBuilderUtil
 * @generated
 */
@ProviderType
public class HistRelationshipBuilderPersistenceImpl extends BasePersistenceImpl<HistRelationshipBuilder>
	implements HistRelationshipBuilderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistRelationshipBuilderUtil} to access the hist relationship builder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistRelationshipBuilderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
			HistRelationshipBuilderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED,
			HistRelationshipBuilderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistRelationshipBuilderPersistenceImpl() {
		setModelClass(HistRelationshipBuilder.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("relationshipDescription",
				"RELATIONSHIP_DESCRIPTION");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("relationshipName", "RELATIONSHIP_NAME");
			dbColumnNames.put("relationshipBuilderSid",
				"RELATIONSHIP_BUILDER_SID");
			dbColumnNames.put("hierarchyVersion", "HIERARCHY_VERSION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
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
	 * Caches the hist relationship builder in the entity cache if it is enabled.
	 *
	 * @param histRelationshipBuilder the hist relationship builder
	 */
	@Override
	public void cacheResult(HistRelationshipBuilder histRelationshipBuilder) {
		entityCache.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderImpl.class,
			histRelationshipBuilder.getPrimaryKey(), histRelationshipBuilder);

		histRelationshipBuilder.resetOriginalValues();
	}

	/**
	 * Caches the hist relationship builders in the entity cache if it is enabled.
	 *
	 * @param histRelationshipBuilders the hist relationship builders
	 */
	@Override
	public void cacheResult(
		List<HistRelationshipBuilder> histRelationshipBuilders) {
		for (HistRelationshipBuilder histRelationshipBuilder : histRelationshipBuilders) {
			if (entityCache.getResult(
						HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
						HistRelationshipBuilderImpl.class,
						histRelationshipBuilder.getPrimaryKey()) == null) {
				cacheResult(histRelationshipBuilder);
			}
			else {
				histRelationshipBuilder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist relationship builders.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistRelationshipBuilderImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist relationship builder.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistRelationshipBuilder histRelationshipBuilder) {
		entityCache.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderImpl.class,
			histRelationshipBuilder.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HistRelationshipBuilder> histRelationshipBuilders) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistRelationshipBuilder histRelationshipBuilder : histRelationshipBuilders) {
			entityCache.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
				HistRelationshipBuilderImpl.class,
				histRelationshipBuilder.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist relationship builder with the primary key. Does not add the hist relationship builder to the database.
	 *
	 * @param histRelationshipBuilderPK the primary key for the new hist relationship builder
	 * @return the new hist relationship builder
	 */
	@Override
	public HistRelationshipBuilder create(
		HistRelationshipBuilderPK histRelationshipBuilderPK) {
		HistRelationshipBuilder histRelationshipBuilder = new HistRelationshipBuilderImpl();

		histRelationshipBuilder.setNew(true);
		histRelationshipBuilder.setPrimaryKey(histRelationshipBuilderPK);

		return histRelationshipBuilder;
	}

	/**
	 * Removes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histRelationshipBuilderPK the primary key of the hist relationship builder
	 * @return the hist relationship builder that was removed
	 * @throws NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder remove(
		HistRelationshipBuilderPK histRelationshipBuilderPK)
		throws NoSuchHistRelationshipBuilderException {
		return remove((Serializable)histRelationshipBuilderPK);
	}

	/**
	 * Removes the hist relationship builder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist relationship builder
	 * @return the hist relationship builder that was removed
	 * @throws NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder remove(Serializable primaryKey)
		throws NoSuchHistRelationshipBuilderException {
		Session session = null;

		try {
			session = openSession();

			HistRelationshipBuilder histRelationshipBuilder = (HistRelationshipBuilder)session.get(HistRelationshipBuilderImpl.class,
					primaryKey);

			if (histRelationshipBuilder == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histRelationshipBuilder);
		}
		catch (NoSuchHistRelationshipBuilderException nsee) {
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
	protected HistRelationshipBuilder removeImpl(
		HistRelationshipBuilder histRelationshipBuilder) {
		histRelationshipBuilder = toUnwrappedModel(histRelationshipBuilder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histRelationshipBuilder)) {
				histRelationshipBuilder = (HistRelationshipBuilder)session.get(HistRelationshipBuilderImpl.class,
						histRelationshipBuilder.getPrimaryKeyObj());
			}

			if (histRelationshipBuilder != null) {
				session.delete(histRelationshipBuilder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histRelationshipBuilder != null) {
			clearCache(histRelationshipBuilder);
		}

		return histRelationshipBuilder;
	}

	@Override
	public HistRelationshipBuilder updateImpl(
		HistRelationshipBuilder histRelationshipBuilder) {
		histRelationshipBuilder = toUnwrappedModel(histRelationshipBuilder);

		boolean isNew = histRelationshipBuilder.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histRelationshipBuilder.isNew()) {
				session.save(histRelationshipBuilder);

				histRelationshipBuilder.setNew(false);
			}
			else {
				histRelationshipBuilder = (HistRelationshipBuilder)session.merge(histRelationshipBuilder);
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

		entityCache.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipBuilderImpl.class,
			histRelationshipBuilder.getPrimaryKey(), histRelationshipBuilder,
			false);

		histRelationshipBuilder.resetOriginalValues();

		return histRelationshipBuilder;
	}

	protected HistRelationshipBuilder toUnwrappedModel(
		HistRelationshipBuilder histRelationshipBuilder) {
		if (histRelationshipBuilder instanceof HistRelationshipBuilderImpl) {
			return histRelationshipBuilder;
		}

		HistRelationshipBuilderImpl histRelationshipBuilderImpl = new HistRelationshipBuilderImpl();

		histRelationshipBuilderImpl.setNew(histRelationshipBuilder.isNew());
		histRelationshipBuilderImpl.setPrimaryKey(histRelationshipBuilder.getPrimaryKey());

		histRelationshipBuilderImpl.setStartDate(histRelationshipBuilder.getStartDate());
		histRelationshipBuilderImpl.setCreatedDate(histRelationshipBuilder.getCreatedDate());
		histRelationshipBuilderImpl.setCreatedBy(histRelationshipBuilder.getCreatedBy());
		histRelationshipBuilderImpl.setRelationshipDescription(histRelationshipBuilder.getRelationshipDescription());
		histRelationshipBuilderImpl.setActionDate(histRelationshipBuilder.getActionDate());
		histRelationshipBuilderImpl.setActionFlag(histRelationshipBuilder.getActionFlag());
		histRelationshipBuilderImpl.setHierarchyDefinitionSid(histRelationshipBuilder.getHierarchyDefinitionSid());
		histRelationshipBuilderImpl.setVersionNo(histRelationshipBuilder.getVersionNo());
		histRelationshipBuilderImpl.setRelationshipName(histRelationshipBuilder.getRelationshipName());
		histRelationshipBuilderImpl.setRelationshipBuilderSid(histRelationshipBuilder.getRelationshipBuilderSid());
		histRelationshipBuilderImpl.setHierarchyVersion(histRelationshipBuilder.getHierarchyVersion());
		histRelationshipBuilderImpl.setModifiedBy(histRelationshipBuilder.getModifiedBy());
		histRelationshipBuilderImpl.setModifiedDate(histRelationshipBuilder.getModifiedDate());
		histRelationshipBuilderImpl.setRelationshipType(histRelationshipBuilder.getRelationshipType());
		histRelationshipBuilderImpl.setBuildType(histRelationshipBuilder.getBuildType());

		return histRelationshipBuilderImpl;
	}

	/**
	 * Returns the hist relationship builder with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist relationship builder
	 * @return the hist relationship builder
	 * @throws NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistRelationshipBuilderException {
		HistRelationshipBuilder histRelationshipBuilder = fetchByPrimaryKey(primaryKey);

		if (histRelationshipBuilder == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistRelationshipBuilderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histRelationshipBuilder;
	}

	/**
	 * Returns the hist relationship builder with the primary key or throws a {@link NoSuchHistRelationshipBuilderException} if it could not be found.
	 *
	 * @param histRelationshipBuilderPK the primary key of the hist relationship builder
	 * @return the hist relationship builder
	 * @throws NoSuchHistRelationshipBuilderException if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder findByPrimaryKey(
		HistRelationshipBuilderPK histRelationshipBuilderPK)
		throws NoSuchHistRelationshipBuilderException {
		return findByPrimaryKey((Serializable)histRelationshipBuilderPK);
	}

	/**
	 * Returns the hist relationship builder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist relationship builder
	 * @return the hist relationship builder, or <code>null</code> if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
				HistRelationshipBuilderImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistRelationshipBuilder histRelationshipBuilder = (HistRelationshipBuilder)serializable;

		if (histRelationshipBuilder == null) {
			Session session = null;

			try {
				session = openSession();

				histRelationshipBuilder = (HistRelationshipBuilder)session.get(HistRelationshipBuilderImpl.class,
						primaryKey);

				if (histRelationshipBuilder != null) {
					cacheResult(histRelationshipBuilder);
				}
				else {
					entityCache.putResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
						HistRelationshipBuilderImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistRelationshipBuilderModelImpl.ENTITY_CACHE_ENABLED,
					HistRelationshipBuilderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histRelationshipBuilder;
	}

	/**
	 * Returns the hist relationship builder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histRelationshipBuilderPK the primary key of the hist relationship builder
	 * @return the hist relationship builder, or <code>null</code> if a hist relationship builder with the primary key could not be found
	 */
	@Override
	public HistRelationshipBuilder fetchByPrimaryKey(
		HistRelationshipBuilderPK histRelationshipBuilderPK) {
		return fetchByPrimaryKey((Serializable)histRelationshipBuilderPK);
	}

	@Override
	public Map<Serializable, HistRelationshipBuilder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistRelationshipBuilder> map = new HashMap<Serializable, HistRelationshipBuilder>();

		for (Serializable primaryKey : primaryKeys) {
			HistRelationshipBuilder histRelationshipBuilder = fetchByPrimaryKey(primaryKey);

			if (histRelationshipBuilder != null) {
				map.put(primaryKey, histRelationshipBuilder);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist relationship builders.
	 *
	 * @return the hist relationship builders
	 */
	@Override
	public List<HistRelationshipBuilder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship builders
	 * @param end the upper bound of the range of hist relationship builders (not inclusive)
	 * @return the range of hist relationship builders
	 */
	@Override
	public List<HistRelationshipBuilder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship builders
	 * @param end the upper bound of the range of hist relationship builders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist relationship builders
	 */
	@Override
	public List<HistRelationshipBuilder> findAll(int start, int end,
		OrderByComparator<HistRelationshipBuilder> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist relationship builders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipBuilderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship builders
	 * @param end the upper bound of the range of hist relationship builders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist relationship builders
	 */
	@Override
	public List<HistRelationshipBuilder> findAll(int start, int end,
		OrderByComparator<HistRelationshipBuilder> orderByComparator,
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

		List<HistRelationshipBuilder> list = null;

		if (retrieveFromCache) {
			list = (List<HistRelationshipBuilder>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTRELATIONSHIPBUILDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTRELATIONSHIPBUILDER;

				if (pagination) {
					sql = sql.concat(HistRelationshipBuilderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistRelationshipBuilder>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistRelationshipBuilder>)QueryUtil.list(q,
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
	 * Removes all the hist relationship builders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistRelationshipBuilder histRelationshipBuilder : findAll()) {
			remove(histRelationshipBuilder);
		}
	}

	/**
	 * Returns the number of hist relationship builders.
	 *
	 * @return the number of hist relationship builders
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTRELATIONSHIPBUILDER);

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
		return HistRelationshipBuilderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist relationship builder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistRelationshipBuilderImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTRELATIONSHIPBUILDER = "SELECT histRelationshipBuilder FROM HistRelationshipBuilder histRelationshipBuilder";
	private static final String _SQL_COUNT_HISTRELATIONSHIPBUILDER = "SELECT COUNT(histRelationshipBuilder) FROM HistRelationshipBuilder histRelationshipBuilder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histRelationshipBuilder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistRelationshipBuilder exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistRelationshipBuilderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"startDate", "createdDate", "createdBy",
				"relationshipDescription", "actionDate", "actionFlag",
				"hierarchyDefinitionSid", "versionNo", "relationshipName",
				"relationshipBuilderSid", "hierarchyVersion", "modifiedBy",
				"modifiedDate", "relationshipType", "buildType"
			});
}