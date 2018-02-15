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

import com.stpl.app.exception.NoSuchHierarchyLevelValuesException;
import com.stpl.app.model.HierarchyLevelValues;
import com.stpl.app.model.impl.HierarchyLevelValuesImpl;
import com.stpl.app.model.impl.HierarchyLevelValuesModelImpl;
import com.stpl.app.service.persistence.HierarchyLevelValuesPersistence;

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
 * The persistence implementation for the hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelValuesPersistence
 * @see com.stpl.app.service.persistence.HierarchyLevelValuesUtil
 * @generated
 */
@ProviderType
public class HierarchyLevelValuesPersistenceImpl extends BasePersistenceImpl<HierarchyLevelValues>
	implements HierarchyLevelValuesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HierarchyLevelValuesUtil} to access the hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HierarchyLevelValuesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			HierarchyLevelValuesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			HierarchyLevelValuesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HierarchyLevelValuesPersistenceImpl() {
		setModelClass(HierarchyLevelValues.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("levelValues", "LEVEL_VALUES");
			dbColumnNames.put("hierarchyLevelValuesSid",
				"HIERARCHY_LEVEL_VALUES_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("hierarchyLevelDefinitionSid",
				"HIERARCHY_LEVEL_DEFINITION_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
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
	 * Caches the hierarchy level values in the entity cache if it is enabled.
	 *
	 * @param hierarchyLevelValues the hierarchy level values
	 */
	@Override
	public void cacheResult(HierarchyLevelValues hierarchyLevelValues) {
		entityCache.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesImpl.class,
			hierarchyLevelValues.getPrimaryKey(), hierarchyLevelValues);

		hierarchyLevelValues.resetOriginalValues();
	}

	/**
	 * Caches the hierarchy level valueses in the entity cache if it is enabled.
	 *
	 * @param hierarchyLevelValueses the hierarchy level valueses
	 */
	@Override
	public void cacheResult(List<HierarchyLevelValues> hierarchyLevelValueses) {
		for (HierarchyLevelValues hierarchyLevelValues : hierarchyLevelValueses) {
			if (entityCache.getResult(
						HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyLevelValuesImpl.class,
						hierarchyLevelValues.getPrimaryKey()) == null) {
				cacheResult(hierarchyLevelValues);
			}
			else {
				hierarchyLevelValues.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hierarchy level valueses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HierarchyLevelValuesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hierarchy level values.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HierarchyLevelValues hierarchyLevelValues) {
		entityCache.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesImpl.class, hierarchyLevelValues.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HierarchyLevelValues> hierarchyLevelValueses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HierarchyLevelValues hierarchyLevelValues : hierarchyLevelValueses) {
			entityCache.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyLevelValuesImpl.class,
				hierarchyLevelValues.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
	 *
	 * @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
	 * @return the new hierarchy level values
	 */
	@Override
	public HierarchyLevelValues create(int hierarchyLevelValuesSid) {
		HierarchyLevelValues hierarchyLevelValues = new HierarchyLevelValuesImpl();

		hierarchyLevelValues.setNew(true);
		hierarchyLevelValues.setPrimaryKey(hierarchyLevelValuesSid);

		return hierarchyLevelValues;
	}

	/**
	 * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	 * @return the hierarchy level values that was removed
	 * @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues remove(int hierarchyLevelValuesSid)
		throws NoSuchHierarchyLevelValuesException {
		return remove((Serializable)hierarchyLevelValuesSid);
	}

	/**
	 * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hierarchy level values
	 * @return the hierarchy level values that was removed
	 * @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues remove(Serializable primaryKey)
		throws NoSuchHierarchyLevelValuesException {
		Session session = null;

		try {
			session = openSession();

			HierarchyLevelValues hierarchyLevelValues = (HierarchyLevelValues)session.get(HierarchyLevelValuesImpl.class,
					primaryKey);

			if (hierarchyLevelValues == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(hierarchyLevelValues);
		}
		catch (NoSuchHierarchyLevelValuesException nsee) {
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
	protected HierarchyLevelValues removeImpl(
		HierarchyLevelValues hierarchyLevelValues) {
		hierarchyLevelValues = toUnwrappedModel(hierarchyLevelValues);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(hierarchyLevelValues)) {
				hierarchyLevelValues = (HierarchyLevelValues)session.get(HierarchyLevelValuesImpl.class,
						hierarchyLevelValues.getPrimaryKeyObj());
			}

			if (hierarchyLevelValues != null) {
				session.delete(hierarchyLevelValues);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (hierarchyLevelValues != null) {
			clearCache(hierarchyLevelValues);
		}

		return hierarchyLevelValues;
	}

	@Override
	public HierarchyLevelValues updateImpl(
		HierarchyLevelValues hierarchyLevelValues) {
		hierarchyLevelValues = toUnwrappedModel(hierarchyLevelValues);

		boolean isNew = hierarchyLevelValues.isNew();

		Session session = null;

		try {
			session = openSession();

			if (hierarchyLevelValues.isNew()) {
				session.save(hierarchyLevelValues);

				hierarchyLevelValues.setNew(false);
			}
			else {
				hierarchyLevelValues = (HierarchyLevelValues)session.merge(hierarchyLevelValues);
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

		entityCache.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HierarchyLevelValuesImpl.class,
			hierarchyLevelValues.getPrimaryKey(), hierarchyLevelValues, false);

		hierarchyLevelValues.resetOriginalValues();

		return hierarchyLevelValues;
	}

	protected HierarchyLevelValues toUnwrappedModel(
		HierarchyLevelValues hierarchyLevelValues) {
		if (hierarchyLevelValues instanceof HierarchyLevelValuesImpl) {
			return hierarchyLevelValues;
		}

		HierarchyLevelValuesImpl hierarchyLevelValuesImpl = new HierarchyLevelValuesImpl();

		hierarchyLevelValuesImpl.setNew(hierarchyLevelValues.isNew());
		hierarchyLevelValuesImpl.setPrimaryKey(hierarchyLevelValues.getPrimaryKey());

		hierarchyLevelValuesImpl.setLevelValues(hierarchyLevelValues.getLevelValues());
		hierarchyLevelValuesImpl.setHierarchyLevelValuesSid(hierarchyLevelValues.getHierarchyLevelValuesSid());
		hierarchyLevelValuesImpl.setCreatedDate(hierarchyLevelValues.getCreatedDate());
		hierarchyLevelValuesImpl.setCreatedBy(hierarchyLevelValues.getCreatedBy());
		hierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(hierarchyLevelValues.getHierarchyLevelDefinitionSid());
		hierarchyLevelValuesImpl.setVersionNo(hierarchyLevelValues.getVersionNo());
		hierarchyLevelValuesImpl.setModifiedBy(hierarchyLevelValues.getModifiedBy());
		hierarchyLevelValuesImpl.setModifiedDate(hierarchyLevelValues.getModifiedDate());

		return hierarchyLevelValuesImpl;
	}

	/**
	 * Returns the hierarchy level values with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy level values
	 * @return the hierarchy level values
	 * @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHierarchyLevelValuesException {
		HierarchyLevelValues hierarchyLevelValues = fetchByPrimaryKey(primaryKey);

		if (hierarchyLevelValues == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return hierarchyLevelValues;
	}

	/**
	 * Returns the hierarchy level values with the primary key or throws a {@link NoSuchHierarchyLevelValuesException} if it could not be found.
	 *
	 * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	 * @return the hierarchy level values
	 * @throws NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues findByPrimaryKey(int hierarchyLevelValuesSid)
		throws NoSuchHierarchyLevelValuesException {
		return findByPrimaryKey((Serializable)hierarchyLevelValuesSid);
	}

	/**
	 * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hierarchy level values
	 * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				HierarchyLevelValuesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HierarchyLevelValues hierarchyLevelValues = (HierarchyLevelValues)serializable;

		if (hierarchyLevelValues == null) {
			Session session = null;

			try {
				session = openSession();

				hierarchyLevelValues = (HierarchyLevelValues)session.get(HierarchyLevelValuesImpl.class,
						primaryKey);

				if (hierarchyLevelValues != null) {
					cacheResult(hierarchyLevelValues);
				}
				else {
					entityCache.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						HierarchyLevelValuesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelValuesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return hierarchyLevelValues;
	}

	/**
	 * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
	 * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
	 */
	@Override
	public HierarchyLevelValues fetchByPrimaryKey(int hierarchyLevelValuesSid) {
		return fetchByPrimaryKey((Serializable)hierarchyLevelValuesSid);
	}

	@Override
	public Map<Serializable, HierarchyLevelValues> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HierarchyLevelValues> map = new HashMap<Serializable, HierarchyLevelValues>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			HierarchyLevelValues hierarchyLevelValues = fetchByPrimaryKey(primaryKey);

			if (hierarchyLevelValues != null) {
				map.put(primaryKey, hierarchyLevelValues);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelValuesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (HierarchyLevelValues)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_HIERARCHYLEVELVALUES_WHERE_PKS_IN);

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

			for (HierarchyLevelValues hierarchyLevelValues : (List<HierarchyLevelValues>)q.list()) {
				map.put(hierarchyLevelValues.getPrimaryKeyObj(),
					hierarchyLevelValues);

				cacheResult(hierarchyLevelValues);

				uncachedPrimaryKeys.remove(hierarchyLevelValues.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(HierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					HierarchyLevelValuesImpl.class, primaryKey, nullModel);
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
	 * Returns all the hierarchy level valueses.
	 *
	 * @return the hierarchy level valueses
	 */
	@Override
	public List<HierarchyLevelValues> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level valueses
	 * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	 * @return the range of hierarchy level valueses
	 */
	@Override
	public List<HierarchyLevelValues> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level valueses
	 * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hierarchy level valueses
	 */
	@Override
	public List<HierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HierarchyLevelValues> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hierarchy level valueses
	 * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hierarchy level valueses
	 */
	@Override
	public List<HierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HierarchyLevelValues> orderByComparator,
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

		List<HierarchyLevelValues> list = null;

		if (retrieveFromCache) {
			list = (List<HierarchyLevelValues>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HIERARCHYLEVELVALUES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HIERARCHYLEVELVALUES;

				if (pagination) {
					sql = sql.concat(HierarchyLevelValuesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HierarchyLevelValues>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HierarchyLevelValues>)QueryUtil.list(q,
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
	 * Removes all the hierarchy level valueses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HierarchyLevelValues hierarchyLevelValues : findAll()) {
			remove(hierarchyLevelValues);
		}
	}

	/**
	 * Returns the number of hierarchy level valueses.
	 *
	 * @return the number of hierarchy level valueses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HIERARCHYLEVELVALUES);

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
		return HierarchyLevelValuesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hierarchy level values persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HierarchyLevelValuesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HIERARCHYLEVELVALUES = "SELECT hierarchyLevelValues FROM HierarchyLevelValues hierarchyLevelValues";
	private static final String _SQL_SELECT_HIERARCHYLEVELVALUES_WHERE_PKS_IN = "SELECT hierarchyLevelValues FROM HierarchyLevelValues hierarchyLevelValues WHERE HIERARCHY_LEVEL_VALUES_SID IN (";
	private static final String _SQL_COUNT_HIERARCHYLEVELVALUES = "SELECT COUNT(hierarchyLevelValues) FROM HierarchyLevelValues hierarchyLevelValues";
	private static final String _ORDER_BY_ENTITY_ALIAS = "hierarchyLevelValues.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HierarchyLevelValues exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HierarchyLevelValuesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"levelValues", "hierarchyLevelValuesSid", "createdDate",
				"createdBy", "hierarchyLevelDefinitionSid", "versionNo",
				"modifiedBy", "modifiedDate"
			});
}