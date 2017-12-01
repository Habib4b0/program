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

import com.stpl.app.exception.NoSuchImtdLevelValuesException;
import com.stpl.app.model.ImtdLevelValues;
import com.stpl.app.model.impl.ImtdLevelValuesImpl;
import com.stpl.app.model.impl.ImtdLevelValuesModelImpl;
import com.stpl.app.service.persistence.ImtdLevelValuesPersistence;

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
 * The persistence implementation for the imtd level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdLevelValuesPersistence
 * @see com.stpl.app.service.persistence.ImtdLevelValuesUtil
 * @generated
 */
@ProviderType
public class ImtdLevelValuesPersistenceImpl extends BasePersistenceImpl<ImtdLevelValues>
	implements ImtdLevelValuesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdLevelValuesUtil} to access the imtd level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdLevelValuesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			ImtdLevelValuesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED,
			ImtdLevelValuesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdLevelValuesPersistenceImpl() {
		setModelClass(ImtdLevelValues.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("levelValues", "LEVEL_VALUES");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("imtdLevelValuesSid", "IMTD_LEVEL_VALUES_SID");
			dbColumnNames.put("levelNo", "LEVEL_NO");
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
	 * Caches the imtd level values in the entity cache if it is enabled.
	 *
	 * @param imtdLevelValues the imtd level values
	 */
	@Override
	public void cacheResult(ImtdLevelValues imtdLevelValues) {
		entityCache.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey(),
			imtdLevelValues);

		imtdLevelValues.resetOriginalValues();
	}

	/**
	 * Caches the imtd level valueses in the entity cache if it is enabled.
	 *
	 * @param imtdLevelValueses the imtd level valueses
	 */
	@Override
	public void cacheResult(List<ImtdLevelValues> imtdLevelValueses) {
		for (ImtdLevelValues imtdLevelValues : imtdLevelValueses) {
			if (entityCache.getResult(
						ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						ImtdLevelValuesImpl.class,
						imtdLevelValues.getPrimaryKey()) == null) {
				cacheResult(imtdLevelValues);
			}
			else {
				imtdLevelValues.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd level valueses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdLevelValuesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd level values.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdLevelValues imtdLevelValues) {
		entityCache.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdLevelValues> imtdLevelValueses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdLevelValues imtdLevelValues : imtdLevelValueses) {
			entityCache.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
	 *
	 * @param imtdLevelValuesSid the primary key for the new imtd level values
	 * @return the new imtd level values
	 */
	@Override
	public ImtdLevelValues create(int imtdLevelValuesSid) {
		ImtdLevelValues imtdLevelValues = new ImtdLevelValuesImpl();

		imtdLevelValues.setNew(true);
		imtdLevelValues.setPrimaryKey(imtdLevelValuesSid);

		return imtdLevelValues;
	}

	/**
	 * Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdLevelValuesSid the primary key of the imtd level values
	 * @return the imtd level values that was removed
	 * @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues remove(int imtdLevelValuesSid)
		throws NoSuchImtdLevelValuesException {
		return remove((Serializable)imtdLevelValuesSid);
	}

	/**
	 * Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd level values
	 * @return the imtd level values that was removed
	 * @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues remove(Serializable primaryKey)
		throws NoSuchImtdLevelValuesException {
		Session session = null;

		try {
			session = openSession();

			ImtdLevelValues imtdLevelValues = (ImtdLevelValues)session.get(ImtdLevelValuesImpl.class,
					primaryKey);

			if (imtdLevelValues == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdLevelValues);
		}
		catch (NoSuchImtdLevelValuesException nsee) {
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
	protected ImtdLevelValues removeImpl(ImtdLevelValues imtdLevelValues) {
		imtdLevelValues = toUnwrappedModel(imtdLevelValues);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdLevelValues)) {
				imtdLevelValues = (ImtdLevelValues)session.get(ImtdLevelValuesImpl.class,
						imtdLevelValues.getPrimaryKeyObj());
			}

			if (imtdLevelValues != null) {
				session.delete(imtdLevelValues);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdLevelValues != null) {
			clearCache(imtdLevelValues);
		}

		return imtdLevelValues;
	}

	@Override
	public ImtdLevelValues updateImpl(ImtdLevelValues imtdLevelValues) {
		imtdLevelValues = toUnwrappedModel(imtdLevelValues);

		boolean isNew = imtdLevelValues.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdLevelValues.isNew()) {
				session.save(imtdLevelValues);

				imtdLevelValues.setNew(false);
			}
			else {
				imtdLevelValues = (ImtdLevelValues)session.merge(imtdLevelValues);
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

		entityCache.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
			ImtdLevelValuesImpl.class, imtdLevelValues.getPrimaryKey(),
			imtdLevelValues, false);

		imtdLevelValues.resetOriginalValues();

		return imtdLevelValues;
	}

	protected ImtdLevelValues toUnwrappedModel(ImtdLevelValues imtdLevelValues) {
		if (imtdLevelValues instanceof ImtdLevelValuesImpl) {
			return imtdLevelValues;
		}

		ImtdLevelValuesImpl imtdLevelValuesImpl = new ImtdLevelValuesImpl();

		imtdLevelValuesImpl.setNew(imtdLevelValues.isNew());
		imtdLevelValuesImpl.setPrimaryKey(imtdLevelValues.getPrimaryKey());

		imtdLevelValuesImpl.setLevelValues(imtdLevelValues.getLevelValues());
		imtdLevelValuesImpl.setCreatedDate(imtdLevelValues.getCreatedDate());
		imtdLevelValuesImpl.setCreatedBy(imtdLevelValues.getCreatedBy());
		imtdLevelValuesImpl.setImtdLevelValuesSid(imtdLevelValues.getImtdLevelValuesSid());
		imtdLevelValuesImpl.setLevelNo(imtdLevelValues.getLevelNo());
		imtdLevelValuesImpl.setVersionNo(imtdLevelValues.getVersionNo());
		imtdLevelValuesImpl.setModifiedBy(imtdLevelValues.getModifiedBy());
		imtdLevelValuesImpl.setModifiedDate(imtdLevelValues.getModifiedDate());
		imtdLevelValuesImpl.setLevelName(imtdLevelValues.getLevelName());

		return imtdLevelValuesImpl;
	}

	/**
	 * Returns the imtd level values with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd level values
	 * @return the imtd level values
	 * @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdLevelValuesException {
		ImtdLevelValues imtdLevelValues = fetchByPrimaryKey(primaryKey);

		if (imtdLevelValues == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdLevelValuesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdLevelValues;
	}

	/**
	 * Returns the imtd level values with the primary key or throws a {@link NoSuchImtdLevelValuesException} if it could not be found.
	 *
	 * @param imtdLevelValuesSid the primary key of the imtd level values
	 * @return the imtd level values
	 * @throws NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues findByPrimaryKey(int imtdLevelValuesSid)
		throws NoSuchImtdLevelValuesException {
		return findByPrimaryKey((Serializable)imtdLevelValuesSid);
	}

	/**
	 * Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd level values
	 * @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
				ImtdLevelValuesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdLevelValues imtdLevelValues = (ImtdLevelValues)serializable;

		if (imtdLevelValues == null) {
			Session session = null;

			try {
				session = openSession();

				imtdLevelValues = (ImtdLevelValues)session.get(ImtdLevelValuesImpl.class,
						primaryKey);

				if (imtdLevelValues != null) {
					cacheResult(imtdLevelValues);
				}
				else {
					entityCache.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
						ImtdLevelValuesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					ImtdLevelValuesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdLevelValues;
	}

	/**
	 * Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdLevelValuesSid the primary key of the imtd level values
	 * @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
	 */
	@Override
	public ImtdLevelValues fetchByPrimaryKey(int imtdLevelValuesSid) {
		return fetchByPrimaryKey((Serializable)imtdLevelValuesSid);
	}

	@Override
	public Map<Serializable, ImtdLevelValues> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdLevelValues> map = new HashMap<Serializable, ImtdLevelValues>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdLevelValues imtdLevelValues = fetchByPrimaryKey(primaryKey);

			if (imtdLevelValues != null) {
				map.put(primaryKey, imtdLevelValues);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					ImtdLevelValuesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdLevelValues)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDLEVELVALUES_WHERE_PKS_IN);

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

			for (ImtdLevelValues imtdLevelValues : (List<ImtdLevelValues>)q.list()) {
				map.put(imtdLevelValues.getPrimaryKeyObj(), imtdLevelValues);

				cacheResult(imtdLevelValues);

				uncachedPrimaryKeys.remove(imtdLevelValues.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdLevelValuesModelImpl.ENTITY_CACHE_ENABLED,
					ImtdLevelValuesImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd level valueses.
	 *
	 * @return the imtd level valueses
	 */
	@Override
	public List<ImtdLevelValues> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd level valueses
	 * @param end the upper bound of the range of imtd level valueses (not inclusive)
	 * @return the range of imtd level valueses
	 */
	@Override
	public List<ImtdLevelValues> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd level valueses
	 * @param end the upper bound of the range of imtd level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd level valueses
	 */
	@Override
	public List<ImtdLevelValues> findAll(int start, int end,
		OrderByComparator<ImtdLevelValues> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd level valueses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd level valueses
	 * @param end the upper bound of the range of imtd level valueses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd level valueses
	 */
	@Override
	public List<ImtdLevelValues> findAll(int start, int end,
		OrderByComparator<ImtdLevelValues> orderByComparator,
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

		List<ImtdLevelValues> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdLevelValues>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDLEVELVALUES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDLEVELVALUES;

				if (pagination) {
					sql = sql.concat(ImtdLevelValuesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdLevelValues>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdLevelValues>)QueryUtil.list(q,
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
	 * Removes all the imtd level valueses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdLevelValues imtdLevelValues : findAll()) {
			remove(imtdLevelValues);
		}
	}

	/**
	 * Returns the number of imtd level valueses.
	 *
	 * @return the number of imtd level valueses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDLEVELVALUES);

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
		return ImtdLevelValuesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd level values persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdLevelValuesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDLEVELVALUES = "SELECT imtdLevelValues FROM ImtdLevelValues imtdLevelValues";
	private static final String _SQL_SELECT_IMTDLEVELVALUES_WHERE_PKS_IN = "SELECT imtdLevelValues FROM ImtdLevelValues imtdLevelValues WHERE IMTD_LEVEL_VALUES_SID IN (";
	private static final String _SQL_COUNT_IMTDLEVELVALUES = "SELECT COUNT(imtdLevelValues) FROM ImtdLevelValues imtdLevelValues";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdLevelValues.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdLevelValues exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdLevelValuesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"levelValues", "createdDate", "createdBy", "imtdLevelValuesSid",
				"levelNo", "versionNo", "modifiedBy", "modifiedDate",
				"levelName"
			});
}