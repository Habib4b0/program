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

import com.stpl.app.exception.NoSuchHistRelationshipLevelDefnException;
import com.stpl.app.model.HistRelationshipLevelDefn;
import com.stpl.app.model.impl.HistRelationshipLevelDefnImpl;
import com.stpl.app.model.impl.HistRelationshipLevelDefnModelImpl;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPK;
import com.stpl.app.service.persistence.HistRelationshipLevelDefnPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist relationship level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistRelationshipLevelDefnPersistence
 * @see com.stpl.app.service.persistence.HistRelationshipLevelDefnUtil
 * @generated
 */
@ProviderType
public class HistRelationshipLevelDefnPersistenceImpl
	extends BasePersistenceImpl<HistRelationshipLevelDefn>
	implements HistRelationshipLevelDefnPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistRelationshipLevelDefnUtil} to access the hist relationship level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistRelationshipLevelDefnImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
			HistRelationshipLevelDefnImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
			HistRelationshipLevelDefnImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public HistRelationshipLevelDefnPersistenceImpl() {
		setModelClass(HistRelationshipLevelDefn.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("relationshipLevelValues",
				"RELATIONSHIP_LEVEL_VALUES");
			dbColumnNames.put("actionDate", "ACTION_DATE");
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
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("hierarchyNo", "HIERARCHY_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("relationshipLevelSid", "RELATIONSHIP_LEVEL_SID");
			dbColumnNames.put("flag", "FLAG");
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
	 * Caches the hist relationship level defn in the entity cache if it is enabled.
	 *
	 * @param histRelationshipLevelDefn the hist relationship level defn
	 */
	@Override
	public void cacheResult(HistRelationshipLevelDefn histRelationshipLevelDefn) {
		entityCache.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnImpl.class,
			histRelationshipLevelDefn.getPrimaryKey(), histRelationshipLevelDefn);

		histRelationshipLevelDefn.resetOriginalValues();
	}

	/**
	 * Caches the hist relationship level defns in the entity cache if it is enabled.
	 *
	 * @param histRelationshipLevelDefns the hist relationship level defns
	 */
	@Override
	public void cacheResult(
		List<HistRelationshipLevelDefn> histRelationshipLevelDefns) {
		for (HistRelationshipLevelDefn histRelationshipLevelDefn : histRelationshipLevelDefns) {
			if (entityCache.getResult(
						HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
						HistRelationshipLevelDefnImpl.class,
						histRelationshipLevelDefn.getPrimaryKey()) == null) {
				cacheResult(histRelationshipLevelDefn);
			}
			else {
				histRelationshipLevelDefn.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist relationship level defns.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistRelationshipLevelDefnImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist relationship level defn.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistRelationshipLevelDefn histRelationshipLevelDefn) {
		entityCache.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnImpl.class,
			histRelationshipLevelDefn.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HistRelationshipLevelDefn> histRelationshipLevelDefns) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistRelationshipLevelDefn histRelationshipLevelDefn : histRelationshipLevelDefns) {
			entityCache.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
				HistRelationshipLevelDefnImpl.class,
				histRelationshipLevelDefn.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist relationship level defn with the primary key. Does not add the hist relationship level defn to the database.
	 *
	 * @param histRelationshipLevelDefnPK the primary key for the new hist relationship level defn
	 * @return the new hist relationship level defn
	 */
	@Override
	public HistRelationshipLevelDefn create(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		HistRelationshipLevelDefn histRelationshipLevelDefn = new HistRelationshipLevelDefnImpl();

		histRelationshipLevelDefn.setNew(true);
		histRelationshipLevelDefn.setPrimaryKey(histRelationshipLevelDefnPK);

		return histRelationshipLevelDefn;
	}

	/**
	 * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	 * @return the hist relationship level defn that was removed
	 * @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn remove(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws NoSuchHistRelationshipLevelDefnException {
		return remove((Serializable)histRelationshipLevelDefnPK);
	}

	/**
	 * Removes the hist relationship level defn with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist relationship level defn
	 * @return the hist relationship level defn that was removed
	 * @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn remove(Serializable primaryKey)
		throws NoSuchHistRelationshipLevelDefnException {
		Session session = null;

		try {
			session = openSession();

			HistRelationshipLevelDefn histRelationshipLevelDefn = (HistRelationshipLevelDefn)session.get(HistRelationshipLevelDefnImpl.class,
					primaryKey);

			if (histRelationshipLevelDefn == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistRelationshipLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histRelationshipLevelDefn);
		}
		catch (NoSuchHistRelationshipLevelDefnException nsee) {
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
	protected HistRelationshipLevelDefn removeImpl(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		histRelationshipLevelDefn = toUnwrappedModel(histRelationshipLevelDefn);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histRelationshipLevelDefn)) {
				histRelationshipLevelDefn = (HistRelationshipLevelDefn)session.get(HistRelationshipLevelDefnImpl.class,
						histRelationshipLevelDefn.getPrimaryKeyObj());
			}

			if (histRelationshipLevelDefn != null) {
				session.delete(histRelationshipLevelDefn);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histRelationshipLevelDefn != null) {
			clearCache(histRelationshipLevelDefn);
		}

		return histRelationshipLevelDefn;
	}

	@Override
	public HistRelationshipLevelDefn updateImpl(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		histRelationshipLevelDefn = toUnwrappedModel(histRelationshipLevelDefn);

		boolean isNew = histRelationshipLevelDefn.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histRelationshipLevelDefn.isNew()) {
				session.save(histRelationshipLevelDefn);

				histRelationshipLevelDefn.setNew(false);
			}
			else {
				histRelationshipLevelDefn = (HistRelationshipLevelDefn)session.merge(histRelationshipLevelDefn);
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

		entityCache.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistRelationshipLevelDefnImpl.class,
			histRelationshipLevelDefn.getPrimaryKey(),
			histRelationshipLevelDefn, false);

		histRelationshipLevelDefn.resetOriginalValues();

		return histRelationshipLevelDefn;
	}

	protected HistRelationshipLevelDefn toUnwrappedModel(
		HistRelationshipLevelDefn histRelationshipLevelDefn) {
		if (histRelationshipLevelDefn instanceof HistRelationshipLevelDefnImpl) {
			return histRelationshipLevelDefn;
		}

		HistRelationshipLevelDefnImpl histRelationshipLevelDefnImpl = new HistRelationshipLevelDefnImpl();

		histRelationshipLevelDefnImpl.setNew(histRelationshipLevelDefn.isNew());
		histRelationshipLevelDefnImpl.setPrimaryKey(histRelationshipLevelDefn.getPrimaryKey());

		histRelationshipLevelDefnImpl.setRelationshipLevelValues(histRelationshipLevelDefn.getRelationshipLevelValues());
		histRelationshipLevelDefnImpl.setActionDate(histRelationshipLevelDefn.getActionDate());
		histRelationshipLevelDefnImpl.setHierarchyLevelDefinitionSid(histRelationshipLevelDefn.getHierarchyLevelDefinitionSid());
		histRelationshipLevelDefnImpl.setParentNode(histRelationshipLevelDefn.getParentNode());
		histRelationshipLevelDefnImpl.setVersionNo(histRelationshipLevelDefn.getVersionNo());
		histRelationshipLevelDefnImpl.setRelationshipBuilderSid(histRelationshipLevelDefn.getRelationshipBuilderSid());
		histRelationshipLevelDefnImpl.setModifiedDate(histRelationshipLevelDefn.getModifiedDate());
		histRelationshipLevelDefnImpl.setCreatedBy(histRelationshipLevelDefn.getCreatedBy());
		histRelationshipLevelDefnImpl.setCreatedDate(histRelationshipLevelDefn.getCreatedDate());
		histRelationshipLevelDefnImpl.setLevelNo(histRelationshipLevelDefn.getLevelNo());
		histRelationshipLevelDefnImpl.setActionFlag(histRelationshipLevelDefn.getActionFlag());
		histRelationshipLevelDefnImpl.setHierarchyNo(histRelationshipLevelDefn.getHierarchyNo());
		histRelationshipLevelDefnImpl.setModifiedBy(histRelationshipLevelDefn.getModifiedBy());
		histRelationshipLevelDefnImpl.setRelationshipLevelSid(histRelationshipLevelDefn.getRelationshipLevelSid());
		histRelationshipLevelDefnImpl.setFlag(histRelationshipLevelDefn.getFlag());
		histRelationshipLevelDefnImpl.setLevelName(histRelationshipLevelDefn.getLevelName());

		return histRelationshipLevelDefnImpl;
	}

	/**
	 * Returns the hist relationship level defn with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist relationship level defn
	 * @return the hist relationship level defn
	 * @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistRelationshipLevelDefnException {
		HistRelationshipLevelDefn histRelationshipLevelDefn = fetchByPrimaryKey(primaryKey);

		if (histRelationshipLevelDefn == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistRelationshipLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histRelationshipLevelDefn;
	}

	/**
	 * Returns the hist relationship level defn with the primary key or throws a {@link NoSuchHistRelationshipLevelDefnException} if it could not be found.
	 *
	 * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	 * @return the hist relationship level defn
	 * @throws NoSuchHistRelationshipLevelDefnException if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn findByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK)
		throws NoSuchHistRelationshipLevelDefnException {
		return findByPrimaryKey((Serializable)histRelationshipLevelDefnPK);
	}

	/**
	 * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist relationship level defn
	 * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
				HistRelationshipLevelDefnImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistRelationshipLevelDefn histRelationshipLevelDefn = (HistRelationshipLevelDefn)serializable;

		if (histRelationshipLevelDefn == null) {
			Session session = null;

			try {
				session = openSession();

				histRelationshipLevelDefn = (HistRelationshipLevelDefn)session.get(HistRelationshipLevelDefnImpl.class,
						primaryKey);

				if (histRelationshipLevelDefn != null) {
					cacheResult(histRelationshipLevelDefn);
				}
				else {
					entityCache.putResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
						HistRelationshipLevelDefnImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistRelationshipLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
					HistRelationshipLevelDefnImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histRelationshipLevelDefn;
	}

	/**
	 * Returns the hist relationship level defn with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histRelationshipLevelDefnPK the primary key of the hist relationship level defn
	 * @return the hist relationship level defn, or <code>null</code> if a hist relationship level defn with the primary key could not be found
	 */
	@Override
	public HistRelationshipLevelDefn fetchByPrimaryKey(
		HistRelationshipLevelDefnPK histRelationshipLevelDefnPK) {
		return fetchByPrimaryKey((Serializable)histRelationshipLevelDefnPK);
	}

	@Override
	public Map<Serializable, HistRelationshipLevelDefn> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistRelationshipLevelDefn> map = new HashMap<Serializable, HistRelationshipLevelDefn>();

		for (Serializable primaryKey : primaryKeys) {
			HistRelationshipLevelDefn histRelationshipLevelDefn = fetchByPrimaryKey(primaryKey);

			if (histRelationshipLevelDefn != null) {
				map.put(primaryKey, histRelationshipLevelDefn);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist relationship level defns.
	 *
	 * @return the hist relationship level defns
	 */
	@Override
	public List<HistRelationshipLevelDefn> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist relationship level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship level defns
	 * @param end the upper bound of the range of hist relationship level defns (not inclusive)
	 * @return the range of hist relationship level defns
	 */
	@Override
	public List<HistRelationshipLevelDefn> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist relationship level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship level defns
	 * @param end the upper bound of the range of hist relationship level defns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist relationship level defns
	 */
	@Override
	public List<HistRelationshipLevelDefn> findAll(int start, int end,
		OrderByComparator<HistRelationshipLevelDefn> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist relationship level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistRelationshipLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist relationship level defns
	 * @param end the upper bound of the range of hist relationship level defns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist relationship level defns
	 */
	@Override
	public List<HistRelationshipLevelDefn> findAll(int start, int end,
		OrderByComparator<HistRelationshipLevelDefn> orderByComparator,
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

		List<HistRelationshipLevelDefn> list = null;

		if (retrieveFromCache) {
			list = (List<HistRelationshipLevelDefn>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTRELATIONSHIPLEVELDEFN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTRELATIONSHIPLEVELDEFN;

				if (pagination) {
					sql = sql.concat(HistRelationshipLevelDefnModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistRelationshipLevelDefn>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistRelationshipLevelDefn>)QueryUtil.list(q,
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
	 * Removes all the hist relationship level defns from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistRelationshipLevelDefn histRelationshipLevelDefn : findAll()) {
			remove(histRelationshipLevelDefn);
		}
	}

	/**
	 * Returns the number of hist relationship level defns.
	 *
	 * @return the number of hist relationship level defns
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTRELATIONSHIPLEVELDEFN);

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
		return HistRelationshipLevelDefnModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist relationship level defn persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistRelationshipLevelDefnImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTRELATIONSHIPLEVELDEFN = "SELECT histRelationshipLevelDefn FROM HistRelationshipLevelDefn histRelationshipLevelDefn";
	private static final String _SQL_COUNT_HISTRELATIONSHIPLEVELDEFN = "SELECT COUNT(histRelationshipLevelDefn) FROM HistRelationshipLevelDefn histRelationshipLevelDefn";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histRelationshipLevelDefn.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistRelationshipLevelDefn exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistRelationshipLevelDefnPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"relationshipLevelValues", "actionDate",
				"hierarchyLevelDefinitionSid", "parentNode", "versionNo",
				"relationshipBuilderSid", "modifiedDate", "createdBy",
				"createdDate", "levelNo", "actionFlag", "hierarchyNo",
				"modifiedBy", "relationshipLevelSid", "flag", "levelName"
			});
}