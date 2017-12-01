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

import com.stpl.app.exception.NoSuchHistHierarchyLevelDefnException;
import com.stpl.app.model.HistHierarchyLevelDefn;
import com.stpl.app.model.impl.HistHierarchyLevelDefnImpl;
import com.stpl.app.model.impl.HistHierarchyLevelDefnModelImpl;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPK;
import com.stpl.app.service.persistence.HistHierarchyLevelDefnPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist hierarchy level defn service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelDefnPersistence
 * @see com.stpl.app.service.persistence.HistHierarchyLevelDefnUtil
 * @generated
 */
@ProviderType
public class HistHierarchyLevelDefnPersistenceImpl extends BasePersistenceImpl<HistHierarchyLevelDefn>
	implements HistHierarchyLevelDefnPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistHierarchyLevelDefnUtil} to access the hist hierarchy level defn persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyLevelDefnImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyLevelDefnImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyLevelDefnImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistHierarchyLevelDefnPersistenceImpl() {
		setModelClass(HistHierarchyLevelDefn.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("tableName", "TABLE_NAME");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("fieldName", "FIELD_NAME");
			dbColumnNames.put("hierarchyLevelDefinitionSid",
				"HIERARCHY_LEVEL_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("levelValueReference", "LEVEL_VALUE_REFERENCE");
			dbColumnNames.put("levelNo", "LEVEL_NO");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("hierarchyDefinitionSid",
				"HIERARCHY_DEFINITION_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
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
	 * Caches the hist hierarchy level defn in the entity cache if it is enabled.
	 *
	 * @param histHierarchyLevelDefn the hist hierarchy level defn
	 */
	@Override
	public void cacheResult(HistHierarchyLevelDefn histHierarchyLevelDefn) {
		entityCache.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnImpl.class,
			histHierarchyLevelDefn.getPrimaryKey(), histHierarchyLevelDefn);

		histHierarchyLevelDefn.resetOriginalValues();
	}

	/**
	 * Caches the hist hierarchy level defns in the entity cache if it is enabled.
	 *
	 * @param histHierarchyLevelDefns the hist hierarchy level defns
	 */
	@Override
	public void cacheResult(
		List<HistHierarchyLevelDefn> histHierarchyLevelDefns) {
		for (HistHierarchyLevelDefn histHierarchyLevelDefn : histHierarchyLevelDefns) {
			if (entityCache.getResult(
						HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyLevelDefnImpl.class,
						histHierarchyLevelDefn.getPrimaryKey()) == null) {
				cacheResult(histHierarchyLevelDefn);
			}
			else {
				histHierarchyLevelDefn.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist hierarchy level defns.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistHierarchyLevelDefnImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist hierarchy level defn.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistHierarchyLevelDefn histHierarchyLevelDefn) {
		entityCache.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnImpl.class,
			histHierarchyLevelDefn.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HistHierarchyLevelDefn> histHierarchyLevelDefns) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistHierarchyLevelDefn histHierarchyLevelDefn : histHierarchyLevelDefns) {
			entityCache.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyLevelDefnImpl.class,
				histHierarchyLevelDefn.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist hierarchy level defn with the primary key. Does not add the hist hierarchy level defn to the database.
	 *
	 * @param histHierarchyLevelDefnPK the primary key for the new hist hierarchy level defn
	 * @return the new hist hierarchy level defn
	 */
	@Override
	public HistHierarchyLevelDefn create(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		HistHierarchyLevelDefn histHierarchyLevelDefn = new HistHierarchyLevelDefnImpl();

		histHierarchyLevelDefn.setNew(true);
		histHierarchyLevelDefn.setPrimaryKey(histHierarchyLevelDefnPK);

		return histHierarchyLevelDefn;
	}

	/**
	 * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn that was removed
	 * @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn remove(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws NoSuchHistHierarchyLevelDefnException {
		return remove((Serializable)histHierarchyLevelDefnPK);
	}

	/**
	 * Removes the hist hierarchy level defn with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn that was removed
	 * @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn remove(Serializable primaryKey)
		throws NoSuchHistHierarchyLevelDefnException {
		Session session = null;

		try {
			session = openSession();

			HistHierarchyLevelDefn histHierarchyLevelDefn = (HistHierarchyLevelDefn)session.get(HistHierarchyLevelDefnImpl.class,
					primaryKey);

			if (histHierarchyLevelDefn == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistHierarchyLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histHierarchyLevelDefn);
		}
		catch (NoSuchHistHierarchyLevelDefnException nsee) {
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
	protected HistHierarchyLevelDefn removeImpl(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		histHierarchyLevelDefn = toUnwrappedModel(histHierarchyLevelDefn);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histHierarchyLevelDefn)) {
				histHierarchyLevelDefn = (HistHierarchyLevelDefn)session.get(HistHierarchyLevelDefnImpl.class,
						histHierarchyLevelDefn.getPrimaryKeyObj());
			}

			if (histHierarchyLevelDefn != null) {
				session.delete(histHierarchyLevelDefn);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histHierarchyLevelDefn != null) {
			clearCache(histHierarchyLevelDefn);
		}

		return histHierarchyLevelDefn;
	}

	@Override
	public HistHierarchyLevelDefn updateImpl(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		histHierarchyLevelDefn = toUnwrappedModel(histHierarchyLevelDefn);

		boolean isNew = histHierarchyLevelDefn.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histHierarchyLevelDefn.isNew()) {
				session.save(histHierarchyLevelDefn);

				histHierarchyLevelDefn.setNew(false);
			}
			else {
				histHierarchyLevelDefn = (HistHierarchyLevelDefn)session.merge(histHierarchyLevelDefn);
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

		entityCache.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelDefnImpl.class,
			histHierarchyLevelDefn.getPrimaryKey(), histHierarchyLevelDefn,
			false);

		histHierarchyLevelDefn.resetOriginalValues();

		return histHierarchyLevelDefn;
	}

	protected HistHierarchyLevelDefn toUnwrappedModel(
		HistHierarchyLevelDefn histHierarchyLevelDefn) {
		if (histHierarchyLevelDefn instanceof HistHierarchyLevelDefnImpl) {
			return histHierarchyLevelDefn;
		}

		HistHierarchyLevelDefnImpl histHierarchyLevelDefnImpl = new HistHierarchyLevelDefnImpl();

		histHierarchyLevelDefnImpl.setNew(histHierarchyLevelDefn.isNew());
		histHierarchyLevelDefnImpl.setPrimaryKey(histHierarchyLevelDefn.getPrimaryKey());

		histHierarchyLevelDefnImpl.setTableName(histHierarchyLevelDefn.getTableName());
		histHierarchyLevelDefnImpl.setActionDate(histHierarchyLevelDefn.getActionDate());
		histHierarchyLevelDefnImpl.setFieldName(histHierarchyLevelDefn.getFieldName());
		histHierarchyLevelDefnImpl.setHierarchyLevelDefinitionSid(histHierarchyLevelDefn.getHierarchyLevelDefinitionSid());
		histHierarchyLevelDefnImpl.setVersionNo(histHierarchyLevelDefn.getVersionNo());
		histHierarchyLevelDefnImpl.setModifiedDate(histHierarchyLevelDefn.getModifiedDate());
		histHierarchyLevelDefnImpl.setCreatedBy(histHierarchyLevelDefn.getCreatedBy());
		histHierarchyLevelDefnImpl.setCreatedDate(histHierarchyLevelDefn.getCreatedDate());
		histHierarchyLevelDefnImpl.setLevelValueReference(histHierarchyLevelDefn.getLevelValueReference());
		histHierarchyLevelDefnImpl.setLevelNo(histHierarchyLevelDefn.getLevelNo());
		histHierarchyLevelDefnImpl.setActionFlag(histHierarchyLevelDefn.getActionFlag());
		histHierarchyLevelDefnImpl.setHierarchyDefinitionSid(histHierarchyLevelDefn.getHierarchyDefinitionSid());
		histHierarchyLevelDefnImpl.setModifiedBy(histHierarchyLevelDefn.getModifiedBy());
		histHierarchyLevelDefnImpl.setLevelName(histHierarchyLevelDefn.getLevelName());

		return histHierarchyLevelDefnImpl;
	}

	/**
	 * Returns the hist hierarchy level defn with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn
	 * @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistHierarchyLevelDefnException {
		HistHierarchyLevelDefn histHierarchyLevelDefn = fetchByPrimaryKey(primaryKey);

		if (histHierarchyLevelDefn == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistHierarchyLevelDefnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histHierarchyLevelDefn;
	}

	/**
	 * Returns the hist hierarchy level defn with the primary key or throws a {@link NoSuchHistHierarchyLevelDefnException} if it could not be found.
	 *
	 * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn
	 * @throws NoSuchHistHierarchyLevelDefnException if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn findByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK)
		throws NoSuchHistHierarchyLevelDefnException {
		return findByPrimaryKey((Serializable)histHierarchyLevelDefnPK);
	}

	/**
	 * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyLevelDefnImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistHierarchyLevelDefn histHierarchyLevelDefn = (HistHierarchyLevelDefn)serializable;

		if (histHierarchyLevelDefn == null) {
			Session session = null;

			try {
				session = openSession();

				histHierarchyLevelDefn = (HistHierarchyLevelDefn)session.get(HistHierarchyLevelDefnImpl.class,
						primaryKey);

				if (histHierarchyLevelDefn != null) {
					cacheResult(histHierarchyLevelDefn);
				}
				else {
					entityCache.putResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyLevelDefnImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistHierarchyLevelDefnModelImpl.ENTITY_CACHE_ENABLED,
					HistHierarchyLevelDefnImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histHierarchyLevelDefn;
	}

	/**
	 * Returns the hist hierarchy level defn with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histHierarchyLevelDefnPK the primary key of the hist hierarchy level defn
	 * @return the hist hierarchy level defn, or <code>null</code> if a hist hierarchy level defn with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelDefn fetchByPrimaryKey(
		HistHierarchyLevelDefnPK histHierarchyLevelDefnPK) {
		return fetchByPrimaryKey((Serializable)histHierarchyLevelDefnPK);
	}

	@Override
	public Map<Serializable, HistHierarchyLevelDefn> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistHierarchyLevelDefn> map = new HashMap<Serializable, HistHierarchyLevelDefn>();

		for (Serializable primaryKey : primaryKeys) {
			HistHierarchyLevelDefn histHierarchyLevelDefn = fetchByPrimaryKey(primaryKey);

			if (histHierarchyLevelDefn != null) {
				map.put(primaryKey, histHierarchyLevelDefn);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist hierarchy level defns.
	 *
	 * @return the hist hierarchy level defns
	 */
	@Override
	public List<HistHierarchyLevelDefn> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist hierarchy level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level defns
	 * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	 * @return the range of hist hierarchy level defns
	 */
	@Override
	public List<HistHierarchyLevelDefn> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level defns
	 * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist hierarchy level defns
	 */
	@Override
	public List<HistHierarchyLevelDefn> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelDefn> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy level defns.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelDefnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level defns
	 * @param end the upper bound of the range of hist hierarchy level defns (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist hierarchy level defns
	 */
	@Override
	public List<HistHierarchyLevelDefn> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelDefn> orderByComparator,
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

		List<HistHierarchyLevelDefn> list = null;

		if (retrieveFromCache) {
			list = (List<HistHierarchyLevelDefn>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTHIERARCHYLEVELDEFN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTHIERARCHYLEVELDEFN;

				if (pagination) {
					sql = sql.concat(HistHierarchyLevelDefnModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistHierarchyLevelDefn>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistHierarchyLevelDefn>)QueryUtil.list(q,
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
	 * Removes all the hist hierarchy level defns from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistHierarchyLevelDefn histHierarchyLevelDefn : findAll()) {
			remove(histHierarchyLevelDefn);
		}
	}

	/**
	 * Returns the number of hist hierarchy level defns.
	 *
	 * @return the number of hist hierarchy level defns
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYLEVELDEFN);

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
		return HistHierarchyLevelDefnModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist hierarchy level defn persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistHierarchyLevelDefnImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTHIERARCHYLEVELDEFN = "SELECT histHierarchyLevelDefn FROM HistHierarchyLevelDefn histHierarchyLevelDefn";
	private static final String _SQL_COUNT_HISTHIERARCHYLEVELDEFN = "SELECT COUNT(histHierarchyLevelDefn) FROM HistHierarchyLevelDefn histHierarchyLevelDefn";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyLevelDefn.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyLevelDefn exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistHierarchyLevelDefnPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"tableName", "actionDate", "fieldName",
				"hierarchyLevelDefinitionSid", "versionNo", "modifiedDate",
				"createdBy", "createdDate", "levelValueReference", "levelNo",
				"actionFlag", "hierarchyDefinitionSid", "modifiedBy",
				"levelName"
			});
}