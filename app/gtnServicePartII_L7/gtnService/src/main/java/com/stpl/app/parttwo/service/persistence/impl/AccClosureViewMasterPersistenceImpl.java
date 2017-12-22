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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchAccClosureViewMasterException;
import com.stpl.app.parttwo.model.AccClosureViewMaster;
import com.stpl.app.parttwo.model.impl.AccClosureViewMasterImpl;
import com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureViewMasterPersistence;

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
 * The persistence implementation for the acc closure view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureViewMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.AccClosureViewMasterUtil
 * @generated
 */
@ProviderType
public class AccClosureViewMasterPersistenceImpl extends BasePersistenceImpl<AccClosureViewMaster>
	implements AccClosureViewMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccClosureViewMasterUtil} to access the acc closure view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccClosureViewMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED,
			AccClosureViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED,
			AccClosureViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccClosureViewMasterPersistenceImpl() {
		setModelClass(AccClosureViewMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("viewType", "VIEW_TYPE");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("accClosureViewMasterSid",
				"ACC_CLOSURE_VIEW_MASTER_SID");
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
	 * Caches the acc closure view master in the entity cache if it is enabled.
	 *
	 * @param accClosureViewMaster the acc closure view master
	 */
	@Override
	public void cacheResult(AccClosureViewMaster accClosureViewMaster) {
		entityCache.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterImpl.class,
			accClosureViewMaster.getPrimaryKey(), accClosureViewMaster);

		accClosureViewMaster.resetOriginalValues();
	}

	/**
	 * Caches the acc closure view masters in the entity cache if it is enabled.
	 *
	 * @param accClosureViewMasters the acc closure view masters
	 */
	@Override
	public void cacheResult(List<AccClosureViewMaster> accClosureViewMasters) {
		for (AccClosureViewMaster accClosureViewMaster : accClosureViewMasters) {
			if (entityCache.getResult(
						AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureViewMasterImpl.class,
						accClosureViewMaster.getPrimaryKey()) == null) {
				cacheResult(accClosureViewMaster);
			}
			else {
				accClosureViewMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all acc closure view masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccClosureViewMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the acc closure view master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccClosureViewMaster accClosureViewMaster) {
		entityCache.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterImpl.class, accClosureViewMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccClosureViewMaster> accClosureViewMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccClosureViewMaster accClosureViewMaster : accClosureViewMasters) {
			entityCache.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureViewMasterImpl.class,
				accClosureViewMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
	 *
	 * @param accClosureViewMasterSid the primary key for the new acc closure view master
	 * @return the new acc closure view master
	 */
	@Override
	public AccClosureViewMaster create(int accClosureViewMasterSid) {
		AccClosureViewMaster accClosureViewMaster = new AccClosureViewMasterImpl();

		accClosureViewMaster.setNew(true);
		accClosureViewMaster.setPrimaryKey(accClosureViewMasterSid);

		return accClosureViewMaster;
	}

	/**
	 * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accClosureViewMasterSid the primary key of the acc closure view master
	 * @return the acc closure view master that was removed
	 * @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster remove(int accClosureViewMasterSid)
		throws NoSuchAccClosureViewMasterException {
		return remove((Serializable)accClosureViewMasterSid);
	}

	/**
	 * Removes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the acc closure view master
	 * @return the acc closure view master that was removed
	 * @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster remove(Serializable primaryKey)
		throws NoSuchAccClosureViewMasterException {
		Session session = null;

		try {
			session = openSession();

			AccClosureViewMaster accClosureViewMaster = (AccClosureViewMaster)session.get(AccClosureViewMasterImpl.class,
					primaryKey);

			if (accClosureViewMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccClosureViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accClosureViewMaster);
		}
		catch (NoSuchAccClosureViewMasterException nsee) {
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
	protected AccClosureViewMaster removeImpl(
		AccClosureViewMaster accClosureViewMaster) {
		accClosureViewMaster = toUnwrappedModel(accClosureViewMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accClosureViewMaster)) {
				accClosureViewMaster = (AccClosureViewMaster)session.get(AccClosureViewMasterImpl.class,
						accClosureViewMaster.getPrimaryKeyObj());
			}

			if (accClosureViewMaster != null) {
				session.delete(accClosureViewMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accClosureViewMaster != null) {
			clearCache(accClosureViewMaster);
		}

		return accClosureViewMaster;
	}

	@Override
	public AccClosureViewMaster updateImpl(
		AccClosureViewMaster accClosureViewMaster) {
		accClosureViewMaster = toUnwrappedModel(accClosureViewMaster);

		boolean isNew = accClosureViewMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (accClosureViewMaster.isNew()) {
				session.save(accClosureViewMaster);

				accClosureViewMaster.setNew(false);
			}
			else {
				accClosureViewMaster = (AccClosureViewMaster)session.merge(accClosureViewMaster);
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

		entityCache.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureViewMasterImpl.class,
			accClosureViewMaster.getPrimaryKey(), accClosureViewMaster, false);

		accClosureViewMaster.resetOriginalValues();

		return accClosureViewMaster;
	}

	protected AccClosureViewMaster toUnwrappedModel(
		AccClosureViewMaster accClosureViewMaster) {
		if (accClosureViewMaster instanceof AccClosureViewMasterImpl) {
			return accClosureViewMaster;
		}

		AccClosureViewMasterImpl accClosureViewMasterImpl = new AccClosureViewMasterImpl();

		accClosureViewMasterImpl.setNew(accClosureViewMaster.isNew());
		accClosureViewMasterImpl.setPrimaryKey(accClosureViewMaster.getPrimaryKey());

		accClosureViewMasterImpl.setCreatedDate(accClosureViewMaster.getCreatedDate());
		accClosureViewMasterImpl.setCreatedBy(accClosureViewMaster.getCreatedBy());
		accClosureViewMasterImpl.setViewType(accClosureViewMaster.getViewType());
		accClosureViewMasterImpl.setAccClosureMasterSid(accClosureViewMaster.getAccClosureMasterSid());
		accClosureViewMasterImpl.setModifiedBy(accClosureViewMaster.getModifiedBy());
		accClosureViewMasterImpl.setAccClosureViewMasterSid(accClosureViewMaster.getAccClosureViewMasterSid());
		accClosureViewMasterImpl.setModifiedDate(accClosureViewMaster.getModifiedDate());
		accClosureViewMasterImpl.setViewName(accClosureViewMaster.getViewName());

		return accClosureViewMasterImpl;
	}

	/**
	 * Returns the acc closure view master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure view master
	 * @return the acc closure view master
	 * @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccClosureViewMasterException {
		AccClosureViewMaster accClosureViewMaster = fetchByPrimaryKey(primaryKey);

		if (accClosureViewMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccClosureViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accClosureViewMaster;
	}

	/**
	 * Returns the acc closure view master with the primary key or throws a {@link NoSuchAccClosureViewMasterException} if it could not be found.
	 *
	 * @param accClosureViewMasterSid the primary key of the acc closure view master
	 * @return the acc closure view master
	 * @throws NoSuchAccClosureViewMasterException if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster findByPrimaryKey(int accClosureViewMasterSid)
		throws NoSuchAccClosureViewMasterException {
		return findByPrimaryKey((Serializable)accClosureViewMasterSid);
	}

	/**
	 * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure view master
	 * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureViewMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccClosureViewMaster accClosureViewMaster = (AccClosureViewMaster)serializable;

		if (accClosureViewMaster == null) {
			Session session = null;

			try {
				session = openSession();

				accClosureViewMaster = (AccClosureViewMaster)session.get(AccClosureViewMasterImpl.class,
						primaryKey);

				if (accClosureViewMaster != null) {
					cacheResult(accClosureViewMaster);
				}
				else {
					entityCache.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureViewMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureViewMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accClosureViewMaster;
	}

	/**
	 * Returns the acc closure view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accClosureViewMasterSid the primary key of the acc closure view master
	 * @return the acc closure view master, or <code>null</code> if a acc closure view master with the primary key could not be found
	 */
	@Override
	public AccClosureViewMaster fetchByPrimaryKey(int accClosureViewMasterSid) {
		return fetchByPrimaryKey((Serializable)accClosureViewMasterSid);
	}

	@Override
	public Map<Serializable, AccClosureViewMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccClosureViewMaster> map = new HashMap<Serializable, AccClosureViewMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccClosureViewMaster accClosureViewMaster = fetchByPrimaryKey(primaryKey);

			if (accClosureViewMaster != null) {
				map.put(primaryKey, accClosureViewMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureViewMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccClosureViewMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCCLOSUREVIEWMASTER_WHERE_PKS_IN);

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

			for (AccClosureViewMaster accClosureViewMaster : (List<AccClosureViewMaster>)q.list()) {
				map.put(accClosureViewMaster.getPrimaryKeyObj(),
					accClosureViewMaster);

				cacheResult(accClosureViewMaster);

				uncachedPrimaryKeys.remove(accClosureViewMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccClosureViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureViewMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the acc closure view masters.
	 *
	 * @return the acc closure view masters
	 */
	@Override
	public List<AccClosureViewMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the acc closure view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure view masters
	 * @param end the upper bound of the range of acc closure view masters (not inclusive)
	 * @return the range of acc closure view masters
	 */
	@Override
	public List<AccClosureViewMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the acc closure view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure view masters
	 * @param end the upper bound of the range of acc closure view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of acc closure view masters
	 */
	@Override
	public List<AccClosureViewMaster> findAll(int start, int end,
		OrderByComparator<AccClosureViewMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the acc closure view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure view masters
	 * @param end the upper bound of the range of acc closure view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of acc closure view masters
	 */
	@Override
	public List<AccClosureViewMaster> findAll(int start, int end,
		OrderByComparator<AccClosureViewMaster> orderByComparator,
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

		List<AccClosureViewMaster> list = null;

		if (retrieveFromCache) {
			list = (List<AccClosureViewMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCCLOSUREVIEWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCCLOSUREVIEWMASTER;

				if (pagination) {
					sql = sql.concat(AccClosureViewMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccClosureViewMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccClosureViewMaster>)QueryUtil.list(q,
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
	 * Removes all the acc closure view masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccClosureViewMaster accClosureViewMaster : findAll()) {
			remove(accClosureViewMaster);
		}
	}

	/**
	 * Returns the number of acc closure view masters.
	 *
	 * @return the number of acc closure view masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREVIEWMASTER);

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
		return AccClosureViewMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the acc closure view master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccClosureViewMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACCCLOSUREVIEWMASTER = "SELECT accClosureViewMaster FROM AccClosureViewMaster accClosureViewMaster";
	private static final String _SQL_SELECT_ACCCLOSUREVIEWMASTER_WHERE_PKS_IN = "SELECT accClosureViewMaster FROM AccClosureViewMaster accClosureViewMaster WHERE ACC_CLOSURE_VIEW_MASTER_SID IN (";
	private static final String _SQL_COUNT_ACCCLOSUREVIEWMASTER = "SELECT COUNT(accClosureViewMaster) FROM AccClosureViewMaster accClosureViewMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureViewMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureViewMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccClosureViewMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "viewType", "accClosureMasterSid",
				"modifiedBy", "accClosureViewMasterSid", "modifiedDate",
				"viewName"
			});
}