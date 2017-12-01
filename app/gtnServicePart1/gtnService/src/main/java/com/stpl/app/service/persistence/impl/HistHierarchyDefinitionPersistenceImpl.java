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

import com.stpl.app.exception.NoSuchHistHierarchyDefinitionException;
import com.stpl.app.model.HistHierarchyDefinition;
import com.stpl.app.model.impl.HistHierarchyDefinitionImpl;
import com.stpl.app.model.impl.HistHierarchyDefinitionModelImpl;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPK;
import com.stpl.app.service.persistence.HistHierarchyDefinitionPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist hierarchy definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyDefinitionPersistence
 * @see com.stpl.app.service.persistence.HistHierarchyDefinitionUtil
 * @generated
 */
@ProviderType
public class HistHierarchyDefinitionPersistenceImpl extends BasePersistenceImpl<HistHierarchyDefinition>
	implements HistHierarchyDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistHierarchyDefinitionUtil} to access the hist hierarchy definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistHierarchyDefinitionPersistenceImpl() {
		setModelClass(HistHierarchyDefinition.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("noOfLevels", "NO_OF_LEVELS");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("hierarchyType", "HIERARCHY_TYPE");
			dbColumnNames.put("hierarchyCategory", "HIERARCHY_CATEGORY");
			dbColumnNames.put("hierarchyName", "HIERARCHY_NAME");
			dbColumnNames.put("versionNo", "VERSION_NO");
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
	 * Caches the hist hierarchy definition in the entity cache if it is enabled.
	 *
	 * @param histHierarchyDefinition the hist hierarchy definition
	 */
	@Override
	public void cacheResult(HistHierarchyDefinition histHierarchyDefinition) {
		entityCache.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionImpl.class,
			histHierarchyDefinition.getPrimaryKey(), histHierarchyDefinition);

		histHierarchyDefinition.resetOriginalValues();
	}

	/**
	 * Caches the hist hierarchy definitions in the entity cache if it is enabled.
	 *
	 * @param histHierarchyDefinitions the hist hierarchy definitions
	 */
	@Override
	public void cacheResult(
		List<HistHierarchyDefinition> histHierarchyDefinitions) {
		for (HistHierarchyDefinition histHierarchyDefinition : histHierarchyDefinitions) {
			if (entityCache.getResult(
						HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyDefinitionImpl.class,
						histHierarchyDefinition.getPrimaryKey()) == null) {
				cacheResult(histHierarchyDefinition);
			}
			else {
				histHierarchyDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist hierarchy definitions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistHierarchyDefinitionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist hierarchy definition.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistHierarchyDefinition histHierarchyDefinition) {
		entityCache.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionImpl.class,
			histHierarchyDefinition.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HistHierarchyDefinition> histHierarchyDefinitions) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistHierarchyDefinition histHierarchyDefinition : histHierarchyDefinitions) {
			entityCache.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyDefinitionImpl.class,
				histHierarchyDefinition.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist hierarchy definition with the primary key. Does not add the hist hierarchy definition to the database.
	 *
	 * @param histHierarchyDefinitionPK the primary key for the new hist hierarchy definition
	 * @return the new hist hierarchy definition
	 */
	@Override
	public HistHierarchyDefinition create(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		HistHierarchyDefinition histHierarchyDefinition = new HistHierarchyDefinitionImpl();

		histHierarchyDefinition.setNew(true);
		histHierarchyDefinition.setPrimaryKey(histHierarchyDefinitionPK);

		return histHierarchyDefinition;
	}

	/**
	 * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition that was removed
	 * @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition remove(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws NoSuchHistHierarchyDefinitionException {
		return remove((Serializable)histHierarchyDefinitionPK);
	}

	/**
	 * Removes the hist hierarchy definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition that was removed
	 * @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition remove(Serializable primaryKey)
		throws NoSuchHistHierarchyDefinitionException {
		Session session = null;

		try {
			session = openSession();

			HistHierarchyDefinition histHierarchyDefinition = (HistHierarchyDefinition)session.get(HistHierarchyDefinitionImpl.class,
					primaryKey);

			if (histHierarchyDefinition == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histHierarchyDefinition);
		}
		catch (NoSuchHistHierarchyDefinitionException nsee) {
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
	protected HistHierarchyDefinition removeImpl(
		HistHierarchyDefinition histHierarchyDefinition) {
		histHierarchyDefinition = toUnwrappedModel(histHierarchyDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histHierarchyDefinition)) {
				histHierarchyDefinition = (HistHierarchyDefinition)session.get(HistHierarchyDefinitionImpl.class,
						histHierarchyDefinition.getPrimaryKeyObj());
			}

			if (histHierarchyDefinition != null) {
				session.delete(histHierarchyDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histHierarchyDefinition != null) {
			clearCache(histHierarchyDefinition);
		}

		return histHierarchyDefinition;
	}

	@Override
	public HistHierarchyDefinition updateImpl(
		HistHierarchyDefinition histHierarchyDefinition) {
		histHierarchyDefinition = toUnwrappedModel(histHierarchyDefinition);

		boolean isNew = histHierarchyDefinition.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histHierarchyDefinition.isNew()) {
				session.save(histHierarchyDefinition);

				histHierarchyDefinition.setNew(false);
			}
			else {
				histHierarchyDefinition = (HistHierarchyDefinition)session.merge(histHierarchyDefinition);
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

		entityCache.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyDefinitionImpl.class,
			histHierarchyDefinition.getPrimaryKey(), histHierarchyDefinition,
			false);

		histHierarchyDefinition.resetOriginalValues();

		return histHierarchyDefinition;
	}

	protected HistHierarchyDefinition toUnwrappedModel(
		HistHierarchyDefinition histHierarchyDefinition) {
		if (histHierarchyDefinition instanceof HistHierarchyDefinitionImpl) {
			return histHierarchyDefinition;
		}

		HistHierarchyDefinitionImpl histHierarchyDefinitionImpl = new HistHierarchyDefinitionImpl();

		histHierarchyDefinitionImpl.setNew(histHierarchyDefinition.isNew());
		histHierarchyDefinitionImpl.setPrimaryKey(histHierarchyDefinition.getPrimaryKey());

		histHierarchyDefinitionImpl.setNoOfLevels(histHierarchyDefinition.getNoOfLevels());
		histHierarchyDefinitionImpl.setCreatedBy(histHierarchyDefinition.getCreatedBy());
		histHierarchyDefinitionImpl.setActionDate(histHierarchyDefinition.getActionDate());
		histHierarchyDefinitionImpl.setActionFlag(histHierarchyDefinition.getActionFlag());
		histHierarchyDefinitionImpl.setModifiedBy(histHierarchyDefinition.getModifiedBy());
		histHierarchyDefinitionImpl.setHierarchyDefinitionSid(histHierarchyDefinition.getHierarchyDefinitionSid());
		histHierarchyDefinitionImpl.setCreatedDate(histHierarchyDefinition.getCreatedDate());
		histHierarchyDefinitionImpl.setHierarchyType(histHierarchyDefinition.getHierarchyType());
		histHierarchyDefinitionImpl.setHierarchyCategory(histHierarchyDefinition.getHierarchyCategory());
		histHierarchyDefinitionImpl.setHierarchyName(histHierarchyDefinition.getHierarchyName());
		histHierarchyDefinitionImpl.setVersionNo(histHierarchyDefinition.getVersionNo());
		histHierarchyDefinitionImpl.setModifiedDate(histHierarchyDefinition.getModifiedDate());

		return histHierarchyDefinitionImpl;
	}

	/**
	 * Returns the hist hierarchy definition with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition
	 * @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistHierarchyDefinitionException {
		HistHierarchyDefinition histHierarchyDefinition = fetchByPrimaryKey(primaryKey);

		if (histHierarchyDefinition == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistHierarchyDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histHierarchyDefinition;
	}

	/**
	 * Returns the hist hierarchy definition with the primary key or throws a {@link NoSuchHistHierarchyDefinitionException} if it could not be found.
	 *
	 * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition
	 * @throws NoSuchHistHierarchyDefinitionException if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition findByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK)
		throws NoSuchHistHierarchyDefinitionException {
		return findByPrimaryKey((Serializable)histHierarchyDefinitionPK);
	}

	/**
	 * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyDefinitionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistHierarchyDefinition histHierarchyDefinition = (HistHierarchyDefinition)serializable;

		if (histHierarchyDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				histHierarchyDefinition = (HistHierarchyDefinition)session.get(HistHierarchyDefinitionImpl.class,
						primaryKey);

				if (histHierarchyDefinition != null) {
					cacheResult(histHierarchyDefinition);
				}
				else {
					entityCache.putResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyDefinitionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistHierarchyDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					HistHierarchyDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histHierarchyDefinition;
	}

	/**
	 * Returns the hist hierarchy definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histHierarchyDefinitionPK the primary key of the hist hierarchy definition
	 * @return the hist hierarchy definition, or <code>null</code> if a hist hierarchy definition with the primary key could not be found
	 */
	@Override
	public HistHierarchyDefinition fetchByPrimaryKey(
		HistHierarchyDefinitionPK histHierarchyDefinitionPK) {
		return fetchByPrimaryKey((Serializable)histHierarchyDefinitionPK);
	}

	@Override
	public Map<Serializable, HistHierarchyDefinition> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistHierarchyDefinition> map = new HashMap<Serializable, HistHierarchyDefinition>();

		for (Serializable primaryKey : primaryKeys) {
			HistHierarchyDefinition histHierarchyDefinition = fetchByPrimaryKey(primaryKey);

			if (histHierarchyDefinition != null) {
				map.put(primaryKey, histHierarchyDefinition);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist hierarchy definitions.
	 *
	 * @return the hist hierarchy definitions
	 */
	@Override
	public List<HistHierarchyDefinition> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy definitions
	 * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	 * @return the range of hist hierarchy definitions
	 */
	@Override
	public List<HistHierarchyDefinition> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy definitions
	 * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist hierarchy definitions
	 */
	@Override
	public List<HistHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HistHierarchyDefinition> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy definitions
	 * @param end the upper bound of the range of hist hierarchy definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist hierarchy definitions
	 */
	@Override
	public List<HistHierarchyDefinition> findAll(int start, int end,
		OrderByComparator<HistHierarchyDefinition> orderByComparator,
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

		List<HistHierarchyDefinition> list = null;

		if (retrieveFromCache) {
			list = (List<HistHierarchyDefinition>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTHIERARCHYDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTHIERARCHYDEFINITION;

				if (pagination) {
					sql = sql.concat(HistHierarchyDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistHierarchyDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistHierarchyDefinition>)QueryUtil.list(q,
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
	 * Removes all the hist hierarchy definitions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistHierarchyDefinition histHierarchyDefinition : findAll()) {
			remove(histHierarchyDefinition);
		}
	}

	/**
	 * Returns the number of hist hierarchy definitions.
	 *
	 * @return the number of hist hierarchy definitions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYDEFINITION);

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
		return HistHierarchyDefinitionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist hierarchy definition persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistHierarchyDefinitionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTHIERARCHYDEFINITION = "SELECT histHierarchyDefinition FROM HistHierarchyDefinition histHierarchyDefinition";
	private static final String _SQL_COUNT_HISTHIERARCHYDEFINITION = "SELECT COUNT(histHierarchyDefinition) FROM HistHierarchyDefinition histHierarchyDefinition";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyDefinition exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistHierarchyDefinitionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"noOfLevels", "createdBy", "actionDate", "actionFlag",
				"modifiedBy", "hierarchyDefinitionSid", "createdDate",
				"hierarchyType", "hierarchyCategory", "hierarchyName",
				"versionNo", "modifiedDate"
			});
}