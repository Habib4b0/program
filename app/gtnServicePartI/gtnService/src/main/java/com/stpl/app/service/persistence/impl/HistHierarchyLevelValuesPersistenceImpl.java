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

import com.stpl.app.exception.NoSuchHistHierarchyLevelValuesException;
import com.stpl.app.model.HistHierarchyLevelValues;
import com.stpl.app.model.impl.HistHierarchyLevelValuesImpl;
import com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPK;
import com.stpl.app.service.persistence.HistHierarchyLevelValuesPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValuesPersistence
 * @see com.stpl.app.service.persistence.HistHierarchyLevelValuesUtil
 * @generated
 */
@ProviderType
public class HistHierarchyLevelValuesPersistenceImpl extends BasePersistenceImpl<HistHierarchyLevelValues>
	implements HistHierarchyLevelValuesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistHierarchyLevelValuesUtil} to access the hist hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistHierarchyLevelValuesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyLevelValuesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			HistHierarchyLevelValuesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistHierarchyLevelValuesPersistenceImpl() {
		setModelClass(HistHierarchyLevelValues.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("levelValues", "LEVEL_VALUES");
			dbColumnNames.put("hierarchyLevelValuesSid",
				"HIERARCHY_LEVEL_VALUES_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
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
	 * Caches the hist hierarchy level values in the entity cache if it is enabled.
	 *
	 * @param histHierarchyLevelValues the hist hierarchy level values
	 */
	@Override
	public void cacheResult(HistHierarchyLevelValues histHierarchyLevelValues) {
		entityCache.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesImpl.class,
			histHierarchyLevelValues.getPrimaryKey(), histHierarchyLevelValues);

		histHierarchyLevelValues.resetOriginalValues();
	}

	/**
	 * Caches the hist hierarchy level valueses in the entity cache if it is enabled.
	 *
	 * @param histHierarchyLevelValueses the hist hierarchy level valueses
	 */
	@Override
	public void cacheResult(
		List<HistHierarchyLevelValues> histHierarchyLevelValueses) {
		for (HistHierarchyLevelValues histHierarchyLevelValues : histHierarchyLevelValueses) {
			if (entityCache.getResult(
						HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyLevelValuesImpl.class,
						histHierarchyLevelValues.getPrimaryKey()) == null) {
				cacheResult(histHierarchyLevelValues);
			}
			else {
				histHierarchyLevelValues.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist hierarchy level valueses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistHierarchyLevelValuesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist hierarchy level values.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistHierarchyLevelValues histHierarchyLevelValues) {
		entityCache.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesImpl.class,
			histHierarchyLevelValues.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<HistHierarchyLevelValues> histHierarchyLevelValueses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistHierarchyLevelValues histHierarchyLevelValues : histHierarchyLevelValueses) {
			entityCache.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyLevelValuesImpl.class,
				histHierarchyLevelValues.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist hierarchy level values with the primary key. Does not add the hist hierarchy level values to the database.
	 *
	 * @param histHierarchyLevelValuesPK the primary key for the new hist hierarchy level values
	 * @return the new hist hierarchy level values
	 */
	@Override
	public HistHierarchyLevelValues create(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK) {
		HistHierarchyLevelValues histHierarchyLevelValues = new HistHierarchyLevelValuesImpl();

		histHierarchyLevelValues.setNew(true);
		histHierarchyLevelValues.setPrimaryKey(histHierarchyLevelValuesPK);

		return histHierarchyLevelValues;
	}

	/**
	 * Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values that was removed
	 * @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues remove(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
		throws NoSuchHistHierarchyLevelValuesException {
		return remove((Serializable)histHierarchyLevelValuesPK);
	}

	/**
	 * Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values that was removed
	 * @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues remove(Serializable primaryKey)
		throws NoSuchHistHierarchyLevelValuesException {
		Session session = null;

		try {
			session = openSession();

			HistHierarchyLevelValues histHierarchyLevelValues = (HistHierarchyLevelValues)session.get(HistHierarchyLevelValuesImpl.class,
					primaryKey);

			if (histHierarchyLevelValues == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histHierarchyLevelValues);
		}
		catch (NoSuchHistHierarchyLevelValuesException nsee) {
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
	protected HistHierarchyLevelValues removeImpl(
		HistHierarchyLevelValues histHierarchyLevelValues) {
		histHierarchyLevelValues = toUnwrappedModel(histHierarchyLevelValues);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histHierarchyLevelValues)) {
				histHierarchyLevelValues = (HistHierarchyLevelValues)session.get(HistHierarchyLevelValuesImpl.class,
						histHierarchyLevelValues.getPrimaryKeyObj());
			}

			if (histHierarchyLevelValues != null) {
				session.delete(histHierarchyLevelValues);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histHierarchyLevelValues != null) {
			clearCache(histHierarchyLevelValues);
		}

		return histHierarchyLevelValues;
	}

	@Override
	public HistHierarchyLevelValues updateImpl(
		HistHierarchyLevelValues histHierarchyLevelValues) {
		histHierarchyLevelValues = toUnwrappedModel(histHierarchyLevelValues);

		boolean isNew = histHierarchyLevelValues.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histHierarchyLevelValues.isNew()) {
				session.save(histHierarchyLevelValues);

				histHierarchyLevelValues.setNew(false);
			}
			else {
				histHierarchyLevelValues = (HistHierarchyLevelValues)session.merge(histHierarchyLevelValues);
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

		entityCache.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			HistHierarchyLevelValuesImpl.class,
			histHierarchyLevelValues.getPrimaryKey(), histHierarchyLevelValues,
			false);

		histHierarchyLevelValues.resetOriginalValues();

		return histHierarchyLevelValues;
	}

	protected HistHierarchyLevelValues toUnwrappedModel(
		HistHierarchyLevelValues histHierarchyLevelValues) {
		if (histHierarchyLevelValues instanceof HistHierarchyLevelValuesImpl) {
			return histHierarchyLevelValues;
		}

		HistHierarchyLevelValuesImpl histHierarchyLevelValuesImpl = new HistHierarchyLevelValuesImpl();

		histHierarchyLevelValuesImpl.setNew(histHierarchyLevelValues.isNew());
		histHierarchyLevelValuesImpl.setPrimaryKey(histHierarchyLevelValues.getPrimaryKey());

		histHierarchyLevelValuesImpl.setLevelValues(histHierarchyLevelValues.getLevelValues());
		histHierarchyLevelValuesImpl.setHierarchyLevelValuesSid(histHierarchyLevelValues.getHierarchyLevelValuesSid());
		histHierarchyLevelValuesImpl.setCreatedDate(histHierarchyLevelValues.getCreatedDate());
		histHierarchyLevelValuesImpl.setCreatedBy(histHierarchyLevelValues.getCreatedBy());
		histHierarchyLevelValuesImpl.setActionDate(histHierarchyLevelValues.getActionDate());
		histHierarchyLevelValuesImpl.setActionFlag(histHierarchyLevelValues.getActionFlag());
		histHierarchyLevelValuesImpl.setHierarchyLevelDefinitionSid(histHierarchyLevelValues.getHierarchyLevelDefinitionSid());
		histHierarchyLevelValuesImpl.setVersionNo(histHierarchyLevelValues.getVersionNo());
		histHierarchyLevelValuesImpl.setModifiedBy(histHierarchyLevelValues.getModifiedBy());
		histHierarchyLevelValuesImpl.setModifiedDate(histHierarchyLevelValues.getModifiedDate());

		return histHierarchyLevelValuesImpl;
	}

	/**
	 * Returns the hist hierarchy level values with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values
	 * @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistHierarchyLevelValuesException {
		HistHierarchyLevelValues histHierarchyLevelValues = fetchByPrimaryKey(primaryKey);

		if (histHierarchyLevelValues == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistHierarchyLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histHierarchyLevelValues;
	}

	/**
	 * Returns the hist hierarchy level values with the primary key or throws a {@link NoSuchHistHierarchyLevelValuesException} if it could not be found.
	 *
	 * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values
	 * @throws NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues findByPrimaryKey(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
		throws NoSuchHistHierarchyLevelValuesException {
		return findByPrimaryKey((Serializable)histHierarchyLevelValuesPK);
	}

	/**
	 * Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				HistHierarchyLevelValuesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistHierarchyLevelValues histHierarchyLevelValues = (HistHierarchyLevelValues)serializable;

		if (histHierarchyLevelValues == null) {
			Session session = null;

			try {
				session = openSession();

				histHierarchyLevelValues = (HistHierarchyLevelValues)session.get(HistHierarchyLevelValuesImpl.class,
						primaryKey);

				if (histHierarchyLevelValues != null) {
					cacheResult(histHierarchyLevelValues);
				}
				else {
					entityCache.putResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						HistHierarchyLevelValuesImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistHierarchyLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					HistHierarchyLevelValuesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histHierarchyLevelValues;
	}

	/**
	 * Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
	 * @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
	 */
	@Override
	public HistHierarchyLevelValues fetchByPrimaryKey(
		HistHierarchyLevelValuesPK histHierarchyLevelValuesPK) {
		return fetchByPrimaryKey((Serializable)histHierarchyLevelValuesPK);
	}

	@Override
	public Map<Serializable, HistHierarchyLevelValues> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistHierarchyLevelValues> map = new HashMap<Serializable, HistHierarchyLevelValues>();

		for (Serializable primaryKey : primaryKeys) {
			HistHierarchyLevelValues histHierarchyLevelValues = fetchByPrimaryKey(primaryKey);

			if (histHierarchyLevelValues != null) {
				map.put(primaryKey, histHierarchyLevelValues);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist hierarchy level valueses.
	 *
	 * @return the hist hierarchy level valueses
	 */
	@Override
	public List<HistHierarchyLevelValues> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level valueses
	 * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	 * @return the range of hist hierarchy level valueses
	 */
	@Override
	public List<HistHierarchyLevelValues> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level valueses
	 * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist hierarchy level valueses
	 */
	@Override
	public List<HistHierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelValues> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist hierarchy level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist hierarchy level valueses
	 * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist hierarchy level valueses
	 */
	@Override
	public List<HistHierarchyLevelValues> findAll(int start, int end,
		OrderByComparator<HistHierarchyLevelValues> orderByComparator,
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

		List<HistHierarchyLevelValues> list = null;

		if (retrieveFromCache) {
			list = (List<HistHierarchyLevelValues>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTHIERARCHYLEVELVALUES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTHIERARCHYLEVELVALUES;

				if (pagination) {
					sql = sql.concat(HistHierarchyLevelValuesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistHierarchyLevelValues>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistHierarchyLevelValues>)QueryUtil.list(q,
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
	 * Removes all the hist hierarchy level valueses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistHierarchyLevelValues histHierarchyLevelValues : findAll()) {
			remove(histHierarchyLevelValues);
		}
	}

	/**
	 * Returns the number of hist hierarchy level valueses.
	 *
	 * @return the number of hist hierarchy level valueses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTHIERARCHYLEVELVALUES);

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
		return HistHierarchyLevelValuesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist hierarchy level values persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistHierarchyLevelValuesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTHIERARCHYLEVELVALUES = "SELECT histHierarchyLevelValues FROM HistHierarchyLevelValues histHierarchyLevelValues";
	private static final String _SQL_COUNT_HISTHIERARCHYLEVELVALUES = "SELECT COUNT(histHierarchyLevelValues) FROM HistHierarchyLevelValues histHierarchyLevelValues";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histHierarchyLevelValues.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistHierarchyLevelValues exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistHierarchyLevelValuesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"levelValues", "hierarchyLevelValuesSid", "createdDate",
				"createdBy", "actionDate", "actionFlag",
				"hierarchyLevelDefinitionSid", "versionNo", "modifiedBy",
				"modifiedDate"
			});
}