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

import com.stpl.app.exception.NoSuchHierarchyDefinitionException;
import com.stpl.app.model.HierarchyDefinition;
import com.stpl.app.model.impl.HierarchyDefinitionImpl;
import com.stpl.app.model.impl.HierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.HierarchyDefinitionPersistence;

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
 * The persistence implementation for the hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.HierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public class HierarchyDefinitionPersistenceImpl extends BasePersistenceImpl<HierarchyDefinition>
	implements HierarchyDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HierarchyDefinitionUtil} to access the hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HierarchyDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HierarchyDefinitionPersistenceImpl() {
		setModelClass(HierarchyDefinition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("noOfLevels", "NO_OF_LEVELS");
			dbColumnNames.put("hierarchyType", "HIERARCHY_TYPE");
			dbColumnNames.put("hierarchyName", "HIERARCHY_NAME");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("hierarchyCategory", "HIERARCHY_CATEGORY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hierarchy definition in the entity cache if it is enabled.
	 *
	 * @param hierarchyDefinition the hierarchy definition
	 */
	@Override
	public void cacheResult(HierarchyDefinition hierarchyDefinition) {
		entityCache.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey(),
			hierarchyDefinition);

		hierarchyDefinition.resetOriginalValues();
	}

	/**
	 * Caches the hierarchy definitions in the entity cache if it is enabled.
	 *
	 * @param hierarchyDefinitions the hierarchy definitions
	 */
	@Override
	public void cacheResult(List<HierarchyDefinition> hierarchyDefinitions) {
		for (HierarchyDefinition hierarchyDefinition : hierarchyDefinitions) {
			if (entityCache.getResult(
						HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyDefinitionImpl.class,
						hierarchyDefinition.getPrimaryKey()) == null) {
				cacheResult(hierarchyDefinition);
			}
			else {
				hierarchyDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hierarchy definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HierarchyDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hierarchy definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HierarchyDefinition hierarchyDefinition) {
		entityCache.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HierarchyDefinition> hierarchyDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HierarchyDefinition hierarchyDefinition : hierarchyDefinitions) {
			entityCache.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyDefinitionImpl.class,
				hierarchyDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hierarchy definition with the primary key. Does not add the hierarchy definition to the database.
	 *
	 * @param hierarchyDefinitionSid the primary key for the new hierarchy definition
	 * @return the new hierarchy definition
	 */
	@Override
	public HierarchyDefinition create(int hierarchyDefinitionSid) {
		HierarchyDefinition hierarchyDefinition = new HierarchyDefinitionImpl();

		hierarchyDefinition.setNew(true);
		hierarchyDefinition.setPrimaryKey(hierarchyDefinitionSid);

		return hierarchyDefinition;
	}

	/**
	 * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hierarchyDefinitionSid the primary key of the hierarchy definition
	 * @return the hierarchy definition that was removed
	 * @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition remove(int hierarchyDefinitionSid)
		throws NoSuchHierarchyDefinitionException {
		return remove((Serializable)hierarchyDefinitionSid);
	}

	/**
	 * Removes the hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hierarchy definition
	 * @return the hierarchy definition that was removed
	 * @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition remove(Serializable primaryKey)
		throws NoSuchHierarchyDefinitionException {
		Session session = null;

		try {
			session = openSession();

			HierarchyDefinition hierarchyDefinition = (HierarchyDefinition)session.get(HierarchyDefinitionImpl.class,
					primaryKey);

			if (hierarchyDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(hierarchyDefinition);
		}
		catch (NoSuchHierarchyDefinitionException nsee) {
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
	protected HierarchyDefinition removeImpl(
		HierarchyDefinition hierarchyDefinition) {
		hierarchyDefinition = toUnwrappedModel(hierarchyDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(hierarchyDefinition)) {
				hierarchyDefinition = (HierarchyDefinition)session.get(HierarchyDefinitionImpl.class,
						hierarchyDefinition.getPrimaryKeyObj());
			}

			if (hierarchyDefinition != null) {
				session.delete(hierarchyDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (hierarchyDefinition != null) {
			clearCache(hierarchyDefinition);
		}

		return hierarchyDefinition;
	}

	@Override
	public HierarchyDefinition updateImpl(
		HierarchyDefinition hierarchyDefinition) {
		hierarchyDefinition = toUnwrappedModel(hierarchyDefinition);

		boolean isNew = hierarchyDefinition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (hierarchyDefinition.isNew()) {
				session.save(hierarchyDefinition);

				hierarchyDefinition.setNew(false);
			}
			else {
				hierarchyDefinition = (HierarchyDefinition)session.merge(hierarchyDefinition);
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

		entityCache.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyDefinitionImpl.class, hierarchyDefinition.getPrimaryKey(),
			hierarchyDefinition, false);

		hierarchyDefinition.resetOriginalValues();

		return hierarchyDefinition;
	}

	protected HierarchyDefinition toUnwrappedModel(
		HierarchyDefinition hierarchyDefinition) {
		if (hierarchyDefinition instanceof HierarchyDefinitionImpl) {
			return hierarchyDefinition;
		}

		HierarchyDefinitionImpl hierarchyDefinitionImpl = new HierarchyDefinitionImpl();

		hierarchyDefinitionImpl.setNew(hierarchyDefinition.isNew());
		hierarchyDefinitionImpl.setPrimaryKey(hierarchyDefinition.getPrimaryKey());

		hierarchyDefinitionImpl.setCreatedDate(hierarchyDefinition.getCreatedDate());
		hierarchyDefinitionImpl.setCreatedBy(hierarchyDefinition.getCreatedBy());
		hierarchyDefinitionImpl.setNoOfLevels(hierarchyDefinition.getNoOfLevels());
		hierarchyDefinitionImpl.setHierarchyType(hierarchyDefinition.getHierarchyType());
		hierarchyDefinitionImpl.setHierarchyName(hierarchyDefinition.getHierarchyName());
		hierarchyDefinitionImpl.setHierarchyDefinitionSid(hierarchyDefinition.getHierarchyDefinitionSid());
		hierarchyDefinitionImpl.setVersionNo(hierarchyDefinition.getVersionNo());
		hierarchyDefinitionImpl.setModifiedBy(hierarchyDefinition.getModifiedBy());
		hierarchyDefinitionImpl.setModifiedDate(hierarchyDefinition.getModifiedDate());
		hierarchyDefinitionImpl.setHierarchyCategory(hierarchyDefinition.getHierarchyCategory());

		return hierarchyDefinitionImpl;
	}

	/**
	 * Returns the hierarchy definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy definition
	 * @return the hierarchy definition
	 * @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHierarchyDefinitionException {
		HierarchyDefinition hierarchyDefinition = fetchByPrimaryKey(primaryKey);

		if (hierarchyDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return hierarchyDefinition;
	}

	/**
	 * Returns the hierarchy definition with the primary key or throws a {@link NoSuchHierarchyDefinitionException} if it could not be found.
	 *
	 * @param hierarchyDefinitionSid the primary key of the hierarchy definition
	 * @return the hierarchy definition
	 * @throws NoSuchHierarchyDefinitionException if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition findByPrimaryKey(int hierarchyDefinitionSid)
		throws NoSuchHierarchyDefinitionException {
		return findByPrimaryKey((Serializable)hierarchyDefinitionSid);
	}

	/**
	 * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy definition
	 * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HierarchyDefinition hierarchyDefinition = (HierarchyDefinition)serializable;

		if (hierarchyDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				hierarchyDefinition = (HierarchyDefinition)session.get(HierarchyDefinitionImpl.class,
						primaryKey);

				if (hierarchyDefinition != null) {
					cacheResult(hierarchyDefinition);
				}
				else {
					entityCache.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyDefinitionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return hierarchyDefinition;
	}

	/**
	 * Returns the hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hierarchyDefinitionSid the primary key of the hierarchy definition
	 * @return the hierarchy definition, or <code>null</code> if a hierarchy definition with the primary key could not be found
	 */
	@Override
	public HierarchyDefinition fetchByPrimaryKey(int hierarchyDefinitionSid) {
		return fetchByPrimaryKey((Serializable)hierarchyDefinitionSid);
	}

	@Override
	public Map<Serializable, HierarchyDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HierarchyDefinition> map = new HashMap<Serializable, HierarchyDefinition>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HierarchyDefinition hierarchyDefinition = fetchByPrimaryKey(primaryKey);

			if (hierarchyDefinition != null) {
				map.put(primaryKey, hierarchyDefinition);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyDefinitionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HierarchyDefinition)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HIERARCHYDEFINITION_WHERE_PKS_IN);

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

			for (HierarchyDefinition hierarchyDefinition : (List<HierarchyDefinition>)q.list()) {
				map.put(hierarchyDefinition.getPrimaryKeyObj(),
					hierarchyDefinition);

				cacheResult(hierarchyDefinition);

				uncachedPrimaryKeys.remove(hierarchyDefinition.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyDefinitionImpl.class, primaryKey, nullModel);
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
	 * Returns all the hierarchy definitions.
	 *
	 * @return the hierarchy definitions
	 */
	@Override
	public List<HierarchyDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy definitions
	 * @param end the upper bound of the range of hierarchy definitions (not inclusive)
	 * @return the range of hierarchy definitions
	 */
	@Override
	public List<HierarchyDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy definitions
	 * @param end the upper bound of the range of hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hierarchy definitions
	 */
	@Override
	public List<HierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy definitions
	 * @param end the upper bound of the range of hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hierarchy definitions
	 */
	@Override
	public List<HierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HierarchyDefinition> orderByComparator,
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

		List<HierarchyDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<HierarchyDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HIERARCHYDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HIERARCHYDEFINITION;

				if (pagination) {
					sql = sql.concat(HierarchyDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HierarchyDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HierarchyDefinition>)QueryUtil.list(q,
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
	 * Removes all the hierarchy definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HierarchyDefinition hierarchyDefinition : findAll()) {
			remove(hierarchyDefinition);
		}
	}

	/**
	 * Returns the number of hierarchy definitions.
	 *
	 * @return the number of hierarchy definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HIERARCHYDEFINITION);

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
		return HierarchyDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hierarchy definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HierarchyDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HIERARCHYDEFINITION = "SELECT hierarchyDefinition FROM HierarchyDefinition hierarchyDefinition";
	private static final String _SQL_SELECT_HIERARCHYDEFINITION_WHERE_PKS_IN = "SELECT hierarchyDefinition FROM HierarchyDefinition hierarchyDefinition WHERE HIERARCHY_DEFINITION_SID IN (";
	private static final String _SQL_COUNT_HIERARCHYDEFINITION = "SELECT COUNT(hierarchyDefinition) FROM HierarchyDefinition hierarchyDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyDefinition exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HierarchyDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "noOfLevels", "hierarchyType",
				"hierarchyName", "hierarchyDefinitionSid", "versionNo",
				"modifiedBy", "modifiedDate", "hierarchyCategory"
			});
}