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

import com.stpl.app.exception.NoSuchChSalesProjectionMasterException;
import com.stpl.app.model.ChSalesProjectionMaster;
import com.stpl.app.model.impl.ChSalesProjectionMasterImpl;
import com.stpl.app.model.impl.ChSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.ChSalesProjectionMasterPersistence;

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
 * The persistence implementation for the ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.ChSalesProjectionMasterUtil
 * @generated
 */
@ProviderType
public class ChSalesProjectionMasterPersistenceImpl extends BasePersistenceImpl<ChSalesProjectionMaster>
	implements ChSalesProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChSalesProjectionMasterUtil} to access the ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChSalesProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			ChSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			ChSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChSalesProjectionMasterPersistenceImpl() {
		setModelClass(ChSalesProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("calculationPeriods", "CALCULATION_PERIODS");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("methodology", "METHODOLOGY");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ch sales projection master in the entity cache if it is enabled.
	 *
	 * @param chSalesProjectionMaster the ch sales projection master
	 */
	@Override
	public void cacheResult(ChSalesProjectionMaster chSalesProjectionMaster) {
		entityCache.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterImpl.class,
			chSalesProjectionMaster.getPrimaryKey(), chSalesProjectionMaster);

		chSalesProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the ch sales projection masters in the entity cache if it is enabled.
	 *
	 * @param chSalesProjectionMasters the ch sales projection masters
	 */
	@Override
	public void cacheResult(
		List<ChSalesProjectionMaster> chSalesProjectionMasters) {
		for (ChSalesProjectionMaster chSalesProjectionMaster : chSalesProjectionMasters) {
			if (entityCache.getResult(
						ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						ChSalesProjectionMasterImpl.class,
						chSalesProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(chSalesProjectionMaster);
			}
			else {
				chSalesProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch sales projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChSalesProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch sales projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChSalesProjectionMaster chSalesProjectionMaster) {
		entityCache.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterImpl.class,
			chSalesProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ChSalesProjectionMaster> chSalesProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChSalesProjectionMaster chSalesProjectionMaster : chSalesProjectionMasters) {
			entityCache.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				ChSalesProjectionMasterImpl.class,
				chSalesProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch sales projection master with the primary key. Does not add the ch sales projection master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new ch sales projection master
	 * @return the new ch sales projection master
	 */
	@Override
	public ChSalesProjectionMaster create(int projectionDetailsSid) {
		ChSalesProjectionMaster chSalesProjectionMaster = new ChSalesProjectionMasterImpl();

		chSalesProjectionMaster.setNew(true);
		chSalesProjectionMaster.setPrimaryKey(projectionDetailsSid);

		return chSalesProjectionMaster;
	}

	/**
	 * Removes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the ch sales projection master
	 * @return the ch sales projection master that was removed
	 * @throws NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster remove(int projectionDetailsSid)
		throws NoSuchChSalesProjectionMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch sales projection master
	 * @return the ch sales projection master that was removed
	 * @throws NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster remove(Serializable primaryKey)
		throws NoSuchChSalesProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			ChSalesProjectionMaster chSalesProjectionMaster = (ChSalesProjectionMaster)session.get(ChSalesProjectionMasterImpl.class,
					primaryKey);

			if (chSalesProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chSalesProjectionMaster);
		}
		catch (NoSuchChSalesProjectionMasterException nsee) {
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
	protected ChSalesProjectionMaster removeImpl(
		ChSalesProjectionMaster chSalesProjectionMaster) {
		chSalesProjectionMaster = toUnwrappedModel(chSalesProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chSalesProjectionMaster)) {
				chSalesProjectionMaster = (ChSalesProjectionMaster)session.get(ChSalesProjectionMasterImpl.class,
						chSalesProjectionMaster.getPrimaryKeyObj());
			}

			if (chSalesProjectionMaster != null) {
				session.delete(chSalesProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chSalesProjectionMaster != null) {
			clearCache(chSalesProjectionMaster);
		}

		return chSalesProjectionMaster;
	}

	@Override
	public ChSalesProjectionMaster updateImpl(
		ChSalesProjectionMaster chSalesProjectionMaster) {
		chSalesProjectionMaster = toUnwrappedModel(chSalesProjectionMaster);

		boolean isNew = chSalesProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chSalesProjectionMaster.isNew()) {
				session.save(chSalesProjectionMaster);

				chSalesProjectionMaster.setNew(false);
			}
			else {
				chSalesProjectionMaster = (ChSalesProjectionMaster)session.merge(chSalesProjectionMaster);
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

		entityCache.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChSalesProjectionMasterImpl.class,
			chSalesProjectionMaster.getPrimaryKey(), chSalesProjectionMaster,
			false);

		chSalesProjectionMaster.resetOriginalValues();

		return chSalesProjectionMaster;
	}

	protected ChSalesProjectionMaster toUnwrappedModel(
		ChSalesProjectionMaster chSalesProjectionMaster) {
		if (chSalesProjectionMaster instanceof ChSalesProjectionMasterImpl) {
			return chSalesProjectionMaster;
		}

		ChSalesProjectionMasterImpl chSalesProjectionMasterImpl = new ChSalesProjectionMasterImpl();

		chSalesProjectionMasterImpl.setNew(chSalesProjectionMaster.isNew());
		chSalesProjectionMasterImpl.setPrimaryKey(chSalesProjectionMaster.getPrimaryKey());

		chSalesProjectionMasterImpl.setCheckRecord(chSalesProjectionMaster.isCheckRecord());
		chSalesProjectionMasterImpl.setCalculationPeriods(chSalesProjectionMaster.getCalculationPeriods());
		chSalesProjectionMasterImpl.setProjectionDetailsSid(chSalesProjectionMaster.getProjectionDetailsSid());
		chSalesProjectionMasterImpl.setMethodology(chSalesProjectionMaster.getMethodology());

		return chSalesProjectionMasterImpl;
	}

	/**
	 * Returns the ch sales projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch sales projection master
	 * @return the ch sales projection master
	 * @throws NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChSalesProjectionMasterException {
		ChSalesProjectionMaster chSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (chSalesProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chSalesProjectionMaster;
	}

	/**
	 * Returns the ch sales projection master with the primary key or throws a {@link NoSuchChSalesProjectionMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the ch sales projection master
	 * @return the ch sales projection master
	 * @throws NoSuchChSalesProjectionMasterException if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchChSalesProjectionMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch sales projection master
	 * @return the ch sales projection master, or <code>null</code> if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				ChSalesProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChSalesProjectionMaster chSalesProjectionMaster = (ChSalesProjectionMaster)serializable;

		if (chSalesProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				chSalesProjectionMaster = (ChSalesProjectionMaster)session.get(ChSalesProjectionMasterImpl.class,
						primaryKey);

				if (chSalesProjectionMaster != null) {
					cacheResult(chSalesProjectionMaster);
				}
				else {
					entityCache.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						ChSalesProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ChSalesProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chSalesProjectionMaster;
	}

	/**
	 * Returns the ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the ch sales projection master
	 * @return the ch sales projection master, or <code>null</code> if a ch sales projection master with the primary key could not be found
	 */
	@Override
	public ChSalesProjectionMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, ChSalesProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChSalesProjectionMaster> map = new HashMap<Serializable, ChSalesProjectionMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ChSalesProjectionMaster chSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (chSalesProjectionMaster != null) {
				map.put(primaryKey, chSalesProjectionMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ChSalesProjectionMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ChSalesProjectionMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CHSALESPROJECTIONMASTER_WHERE_PKS_IN);

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

			for (ChSalesProjectionMaster chSalesProjectionMaster : (List<ChSalesProjectionMaster>)q.list()) {
				map.put(chSalesProjectionMaster.getPrimaryKeyObj(),
					chSalesProjectionMaster);

				cacheResult(chSalesProjectionMaster);

				uncachedPrimaryKeys.remove(chSalesProjectionMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					ChSalesProjectionMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ch sales projection masters.
	 *
	 * @return the ch sales projection masters
	 */
	@Override
	public List<ChSalesProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projection masters
	 * @param end the upper bound of the range of ch sales projection masters (not inclusive)
	 * @return the range of ch sales projection masters
	 */
	@Override
	public List<ChSalesProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projection masters
	 * @param end the upper bound of the range of ch sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch sales projection masters
	 */
	@Override
	public List<ChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<ChSalesProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch sales projection masters
	 * @param end the upper bound of the range of ch sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch sales projection masters
	 */
	@Override
	public List<ChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<ChSalesProjectionMaster> orderByComparator,
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

		List<ChSalesProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ChSalesProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHSALESPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHSALESPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(ChSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChSalesProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChSalesProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the ch sales projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChSalesProjectionMaster chSalesProjectionMaster : findAll()) {
			remove(chSalesProjectionMaster);
		}
	}

	/**
	 * Returns the number of ch sales projection masters.
	 *
	 * @return the number of ch sales projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHSALESPROJECTIONMASTER);

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
		return ChSalesProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch sales projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChSalesProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHSALESPROJECTIONMASTER = "SELECT chSalesProjectionMaster FROM ChSalesProjectionMaster chSalesProjectionMaster";
	private static final String _SQL_SELECT_CHSALESPROJECTIONMASTER_WHERE_PKS_IN =
		"SELECT chSalesProjectionMaster FROM ChSalesProjectionMaster chSalesProjectionMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CHSALESPROJECTIONMASTER = "SELECT COUNT(chSalesProjectionMaster) FROM ChSalesProjectionMaster chSalesProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chSalesProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChSalesProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChSalesProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "calculationPeriods", "projectionDetailsSid",
				"methodology"
			});
}