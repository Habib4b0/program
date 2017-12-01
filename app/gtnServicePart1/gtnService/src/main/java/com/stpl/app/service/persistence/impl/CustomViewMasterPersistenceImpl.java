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

import com.stpl.app.exception.NoSuchCustomViewMasterException;
import com.stpl.app.model.CustomViewMaster;
import com.stpl.app.model.impl.CustomViewMasterImpl;
import com.stpl.app.model.impl.CustomViewMasterModelImpl;
import com.stpl.app.service.persistence.CustomViewMasterPersistence;

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
 * The persistence implementation for the custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewMasterPersistence
 * @see com.stpl.app.service.persistence.CustomViewMasterUtil
 * @generated
 */
@ProviderType
public class CustomViewMasterPersistenceImpl extends BasePersistenceImpl<CustomViewMaster>
	implements CustomViewMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomViewMasterUtil} to access the custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomViewMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CustomViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CustomViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CustomViewMasterPersistenceImpl() {
		setModelClass(CustomViewMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("customViewMasterSid", "CUSTOM_VIEW_MASTER_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("viewName", "VIEW_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the custom view master in the entity cache if it is enabled.
	 *
	 * @param customViewMaster the custom view master
	 */
	@Override
	public void cacheResult(CustomViewMaster customViewMaster) {
		entityCache.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterImpl.class, customViewMaster.getPrimaryKey(),
			customViewMaster);

		customViewMaster.resetOriginalValues();
	}

	/**
	 * Caches the custom view masters in the entity cache if it is enabled.
	 *
	 * @param customViewMasters the custom view masters
	 */
	@Override
	public void cacheResult(List<CustomViewMaster> customViewMasters) {
		for (CustomViewMaster customViewMaster : customViewMasters) {
			if (entityCache.getResult(
						CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CustomViewMasterImpl.class,
						customViewMaster.getPrimaryKey()) == null) {
				cacheResult(customViewMaster);
			}
			else {
				customViewMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all custom view masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomViewMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the custom view master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomViewMaster customViewMaster) {
		entityCache.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterImpl.class, customViewMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CustomViewMaster> customViewMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomViewMaster customViewMaster : customViewMasters) {
			entityCache.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CustomViewMasterImpl.class, customViewMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new custom view master with the primary key. Does not add the custom view master to the database.
	 *
	 * @param customViewMasterSid the primary key for the new custom view master
	 * @return the new custom view master
	 */
	@Override
	public CustomViewMaster create(int customViewMasterSid) {
		CustomViewMaster customViewMaster = new CustomViewMasterImpl();

		customViewMaster.setNew(true);
		customViewMaster.setPrimaryKey(customViewMasterSid);

		return customViewMaster;
	}

	/**
	 * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customViewMasterSid the primary key of the custom view master
	 * @return the custom view master that was removed
	 * @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster remove(int customViewMasterSid)
		throws NoSuchCustomViewMasterException {
		return remove((Serializable)customViewMasterSid);
	}

	/**
	 * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the custom view master
	 * @return the custom view master that was removed
	 * @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster remove(Serializable primaryKey)
		throws NoSuchCustomViewMasterException {
		Session session = null;

		try {
			session = openSession();

			CustomViewMaster customViewMaster = (CustomViewMaster)session.get(CustomViewMasterImpl.class,
					primaryKey);

			if (customViewMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customViewMaster);
		}
		catch (NoSuchCustomViewMasterException nsee) {
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
	protected CustomViewMaster removeImpl(CustomViewMaster customViewMaster) {
		customViewMaster = toUnwrappedModel(customViewMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customViewMaster)) {
				customViewMaster = (CustomViewMaster)session.get(CustomViewMasterImpl.class,
						customViewMaster.getPrimaryKeyObj());
			}

			if (customViewMaster != null) {
				session.delete(customViewMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customViewMaster != null) {
			clearCache(customViewMaster);
		}

		return customViewMaster;
	}

	@Override
	public CustomViewMaster updateImpl(CustomViewMaster customViewMaster) {
		customViewMaster = toUnwrappedModel(customViewMaster);

		boolean isNew = customViewMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (customViewMaster.isNew()) {
				session.save(customViewMaster);

				customViewMaster.setNew(false);
			}
			else {
				customViewMaster = (CustomViewMaster)session.merge(customViewMaster);
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

		entityCache.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CustomViewMasterImpl.class, customViewMaster.getPrimaryKey(),
			customViewMaster, false);

		customViewMaster.resetOriginalValues();

		return customViewMaster;
	}

	protected CustomViewMaster toUnwrappedModel(
		CustomViewMaster customViewMaster) {
		if (customViewMaster instanceof CustomViewMasterImpl) {
			return customViewMaster;
		}

		CustomViewMasterImpl customViewMasterImpl = new CustomViewMasterImpl();

		customViewMasterImpl.setNew(customViewMaster.isNew());
		customViewMasterImpl.setPrimaryKey(customViewMaster.getPrimaryKey());

		customViewMasterImpl.setCustomViewMasterSid(customViewMaster.getCustomViewMasterSid());
		customViewMasterImpl.setCreatedDate(customViewMaster.getCreatedDate());
		customViewMasterImpl.setCreatedBy(customViewMaster.getCreatedBy());
		customViewMasterImpl.setProjectionMasterSid(customViewMaster.getProjectionMasterSid());
		customViewMasterImpl.setModifiedBy(customViewMaster.getModifiedBy());
		customViewMasterImpl.setModifiedDate(customViewMaster.getModifiedDate());
		customViewMasterImpl.setViewName(customViewMaster.getViewName());

		return customViewMasterImpl;
	}

	/**
	 * Returns the custom view master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom view master
	 * @return the custom view master
	 * @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomViewMasterException {
		CustomViewMaster customViewMaster = fetchByPrimaryKey(primaryKey);

		if (customViewMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customViewMaster;
	}

	/**
	 * Returns the custom view master with the primary key or throws a {@link NoSuchCustomViewMasterException} if it could not be found.
	 *
	 * @param customViewMasterSid the primary key of the custom view master
	 * @return the custom view master
	 * @throws NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster findByPrimaryKey(int customViewMasterSid)
		throws NoSuchCustomViewMasterException {
		return findByPrimaryKey((Serializable)customViewMasterSid);
	}

	/**
	 * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the custom view master
	 * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CustomViewMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomViewMaster customViewMaster = (CustomViewMaster)serializable;

		if (customViewMaster == null) {
			Session session = null;

			try {
				session = openSession();

				customViewMaster = (CustomViewMaster)session.get(CustomViewMasterImpl.class,
						primaryKey);

				if (customViewMaster != null) {
					cacheResult(customViewMaster);
				}
				else {
					entityCache.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CustomViewMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customViewMaster;
	}

	/**
	 * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customViewMasterSid the primary key of the custom view master
	 * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
	 */
	@Override
	public CustomViewMaster fetchByPrimaryKey(int customViewMasterSid) {
		return fetchByPrimaryKey((Serializable)customViewMasterSid);
	}

	@Override
	public Map<Serializable, CustomViewMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomViewMaster> map = new HashMap<Serializable, CustomViewMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomViewMaster customViewMaster = fetchByPrimaryKey(primaryKey);

			if (customViewMaster != null) {
				map.put(primaryKey, customViewMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomViewMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMVIEWMASTER_WHERE_PKS_IN);

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

			for (CustomViewMaster customViewMaster : (List<CustomViewMaster>)q.list()) {
				map.put(customViewMaster.getPrimaryKeyObj(), customViewMaster);

				cacheResult(customViewMaster);

				uncachedPrimaryKeys.remove(customViewMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CustomViewMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the custom view masters.
	 *
	 * @return the custom view masters
	 */
	@Override
	public List<CustomViewMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view masters
	 * @param end the upper bound of the range of custom view masters (not inclusive)
	 * @return the range of custom view masters
	 */
	@Override
	public List<CustomViewMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view masters
	 * @param end the upper bound of the range of custom view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of custom view masters
	 */
	@Override
	public List<CustomViewMaster> findAll(int start, int end,
		OrderByComparator<CustomViewMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of custom view masters
	 * @param end the upper bound of the range of custom view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of custom view masters
	 */
	@Override
	public List<CustomViewMaster> findAll(int start, int end,
		OrderByComparator<CustomViewMaster> orderByComparator,
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

		List<CustomViewMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CustomViewMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMVIEWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMVIEWMASTER;

				if (pagination) {
					sql = sql.concat(CustomViewMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomViewMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomViewMaster>)QueryUtil.list(q,
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
	 * Removes all the custom view masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomViewMaster customViewMaster : findAll()) {
			remove(customViewMaster);
		}
	}

	/**
	 * Returns the number of custom view masters.
	 *
	 * @return the number of custom view masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMVIEWMASTER);

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
		return CustomViewMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the custom view master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomViewMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CUSTOMVIEWMASTER = "SELECT customViewMaster FROM CustomViewMaster customViewMaster";
	private static final String _SQL_SELECT_CUSTOMVIEWMASTER_WHERE_PKS_IN = "SELECT customViewMaster FROM CustomViewMaster customViewMaster WHERE CUSTOM_VIEW_MASTER_SID IN (";
	private static final String _SQL_COUNT_CUSTOMVIEWMASTER = "SELECT COUNT(customViewMaster) FROM CustomViewMaster customViewMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customViewMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomViewMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CustomViewMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"customViewMasterSid", "createdDate", "createdBy",
				"projectionMasterSid", "modifiedBy", "modifiedDate", "viewName"
			});
}