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

import com.stpl.app.exception.NoSuchMSalesProjectionMasterException;
import com.stpl.app.model.MSalesProjectionMaster;
import com.stpl.app.model.impl.MSalesProjectionMasterImpl;
import com.stpl.app.model.impl.MSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.MSalesProjectionMasterPersistence;

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
 * The persistence implementation for the m sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.MSalesProjectionMasterUtil
 * @generated
 */
@ProviderType
public class MSalesProjectionMasterPersistenceImpl extends BasePersistenceImpl<MSalesProjectionMaster>
	implements MSalesProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSalesProjectionMasterUtil} to access the m sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSalesProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			MSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			MSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MSalesProjectionMasterPersistenceImpl() {
		setModelClass(MSalesProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("calculationPeriods", "CALCULATION_PERIODS");
			dbColumnNames.put("calculationBased", "CALCULATION_BASED");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the m sales projection master in the entity cache if it is enabled.
	 *
	 * @param mSalesProjectionMaster the m sales projection master
	 */
	@Override
	public void cacheResult(MSalesProjectionMaster mSalesProjectionMaster) {
		entityCache.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterImpl.class,
			mSalesProjectionMaster.getPrimaryKey(), mSalesProjectionMaster);

		mSalesProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the m sales projection masters in the entity cache if it is enabled.
	 *
	 * @param mSalesProjectionMasters the m sales projection masters
	 */
	@Override
	public void cacheResult(
		List<MSalesProjectionMaster> mSalesProjectionMasters) {
		for (MSalesProjectionMaster mSalesProjectionMaster : mSalesProjectionMasters) {
			if (entityCache.getResult(
						MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						MSalesProjectionMasterImpl.class,
						mSalesProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(mSalesProjectionMaster);
			}
			else {
				mSalesProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m sales projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSalesProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m sales projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSalesProjectionMaster mSalesProjectionMaster) {
		entityCache.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterImpl.class,
			mSalesProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MSalesProjectionMaster> mSalesProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSalesProjectionMaster mSalesProjectionMaster : mSalesProjectionMasters) {
			entityCache.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				MSalesProjectionMasterImpl.class,
				mSalesProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m sales projection master with the primary key. Does not add the m sales projection master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new m sales projection master
	 * @return the new m sales projection master
	 */
	@Override
	public MSalesProjectionMaster create(int projectionDetailsSid) {
		MSalesProjectionMaster mSalesProjectionMaster = new MSalesProjectionMasterImpl();

		mSalesProjectionMaster.setNew(true);
		mSalesProjectionMaster.setPrimaryKey(projectionDetailsSid);

		return mSalesProjectionMaster;
	}

	/**
	 * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the m sales projection master
	 * @return the m sales projection master that was removed
	 * @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster remove(int projectionDetailsSid)
		throws NoSuchMSalesProjectionMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the m sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m sales projection master
	 * @return the m sales projection master that was removed
	 * @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster remove(Serializable primaryKey)
		throws NoSuchMSalesProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			MSalesProjectionMaster mSalesProjectionMaster = (MSalesProjectionMaster)session.get(MSalesProjectionMasterImpl.class,
					primaryKey);

			if (mSalesProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mSalesProjectionMaster);
		}
		catch (NoSuchMSalesProjectionMasterException nsee) {
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
	protected MSalesProjectionMaster removeImpl(
		MSalesProjectionMaster mSalesProjectionMaster) {
		mSalesProjectionMaster = toUnwrappedModel(mSalesProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mSalesProjectionMaster)) {
				mSalesProjectionMaster = (MSalesProjectionMaster)session.get(MSalesProjectionMasterImpl.class,
						mSalesProjectionMaster.getPrimaryKeyObj());
			}

			if (mSalesProjectionMaster != null) {
				session.delete(mSalesProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mSalesProjectionMaster != null) {
			clearCache(mSalesProjectionMaster);
		}

		return mSalesProjectionMaster;
	}

	@Override
	public MSalesProjectionMaster updateImpl(
		MSalesProjectionMaster mSalesProjectionMaster) {
		mSalesProjectionMaster = toUnwrappedModel(mSalesProjectionMaster);

		boolean isNew = mSalesProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mSalesProjectionMaster.isNew()) {
				session.save(mSalesProjectionMaster);

				mSalesProjectionMaster.setNew(false);
			}
			else {
				mSalesProjectionMaster = (MSalesProjectionMaster)session.merge(mSalesProjectionMaster);
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

		entityCache.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSalesProjectionMasterImpl.class,
			mSalesProjectionMaster.getPrimaryKey(), mSalesProjectionMaster,
			false);

		mSalesProjectionMaster.resetOriginalValues();

		return mSalesProjectionMaster;
	}

	protected MSalesProjectionMaster toUnwrappedModel(
		MSalesProjectionMaster mSalesProjectionMaster) {
		if (mSalesProjectionMaster instanceof MSalesProjectionMasterImpl) {
			return mSalesProjectionMaster;
		}

		MSalesProjectionMasterImpl mSalesProjectionMasterImpl = new MSalesProjectionMasterImpl();

		mSalesProjectionMasterImpl.setNew(mSalesProjectionMaster.isNew());
		mSalesProjectionMasterImpl.setPrimaryKey(mSalesProjectionMaster.getPrimaryKey());

		mSalesProjectionMasterImpl.setMethodology(mSalesProjectionMaster.getMethodology());
		mSalesProjectionMasterImpl.setCalculationPeriods(mSalesProjectionMaster.getCalculationPeriods());
		mSalesProjectionMasterImpl.setCalculationBased(mSalesProjectionMaster.getCalculationBased());
		mSalesProjectionMasterImpl.setProjectionDetailsSid(mSalesProjectionMaster.getProjectionDetailsSid());
		mSalesProjectionMasterImpl.setCheckRecord(mSalesProjectionMaster.isCheckRecord());

		return mSalesProjectionMasterImpl;
	}

	/**
	 * Returns the m sales projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m sales projection master
	 * @return the m sales projection master
	 * @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMSalesProjectionMasterException {
		MSalesProjectionMaster mSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (mSalesProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mSalesProjectionMaster;
	}

	/**
	 * Returns the m sales projection master with the primary key or throws a {@link NoSuchMSalesProjectionMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m sales projection master
	 * @return the m sales projection master
	 * @throws NoSuchMSalesProjectionMasterException if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchMSalesProjectionMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m sales projection master
	 * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				MSalesProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSalesProjectionMaster mSalesProjectionMaster = (MSalesProjectionMaster)serializable;

		if (mSalesProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				mSalesProjectionMaster = (MSalesProjectionMaster)session.get(MSalesProjectionMasterImpl.class,
						primaryKey);

				if (mSalesProjectionMaster != null) {
					cacheResult(mSalesProjectionMaster);
				}
				else {
					entityCache.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						MSalesProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSalesProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mSalesProjectionMaster;
	}

	/**
	 * Returns the m sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m sales projection master
	 * @return the m sales projection master, or <code>null</code> if a m sales projection master with the primary key could not be found
	 */
	@Override
	public MSalesProjectionMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, MSalesProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSalesProjectionMaster> map = new HashMap<Serializable, MSalesProjectionMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MSalesProjectionMaster mSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (mSalesProjectionMaster != null) {
				map.put(primaryKey, mSalesProjectionMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSalesProjectionMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MSalesProjectionMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MSALESPROJECTIONMASTER_WHERE_PKS_IN);

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

			for (MSalesProjectionMaster mSalesProjectionMaster : (List<MSalesProjectionMaster>)q.list()) {
				map.put(mSalesProjectionMaster.getPrimaryKeyObj(),
					mSalesProjectionMaster);

				cacheResult(mSalesProjectionMaster);

				uncachedPrimaryKeys.remove(mSalesProjectionMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSalesProjectionMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the m sales projection masters.
	 *
	 * @return the m sales projection masters
	 */
	@Override
	public List<MSalesProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m sales projection masters
	 * @param end the upper bound of the range of m sales projection masters (not inclusive)
	 * @return the range of m sales projection masters
	 */
	@Override
	public List<MSalesProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m sales projection masters
	 * @param end the upper bound of the range of m sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m sales projection masters
	 */
	@Override
	public List<MSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<MSalesProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m sales projection masters
	 * @param end the upper bound of the range of m sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m sales projection masters
	 */
	@Override
	public List<MSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<MSalesProjectionMaster> orderByComparator,
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

		List<MSalesProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<MSalesProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSALESPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSALESPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(MSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSalesProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSalesProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the m sales projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSalesProjectionMaster mSalesProjectionMaster : findAll()) {
			remove(mSalesProjectionMaster);
		}
	}

	/**
	 * Returns the number of m sales projection masters.
	 *
	 * @return the number of m sales projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSALESPROJECTIONMASTER);

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
		return MSalesProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m sales projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSalesProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MSALESPROJECTIONMASTER = "SELECT mSalesProjectionMaster FROM MSalesProjectionMaster mSalesProjectionMaster";
	private static final String _SQL_SELECT_MSALESPROJECTIONMASTER_WHERE_PKS_IN = "SELECT mSalesProjectionMaster FROM MSalesProjectionMaster mSalesProjectionMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_MSALESPROJECTIONMASTER = "SELECT COUNT(mSalesProjectionMaster) FROM MSalesProjectionMaster mSalesProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mSalesProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSalesProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MSalesProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"methodology", "calculationPeriods", "calculationBased",
				"projectionDetailsSid", "checkRecord"
			});
}