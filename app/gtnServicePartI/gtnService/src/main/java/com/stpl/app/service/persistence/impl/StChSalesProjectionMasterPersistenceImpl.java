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

import com.stpl.app.exception.NoSuchStChSalesProjectionMasterException;
import com.stpl.app.model.StChSalesProjectionMaster;
import com.stpl.app.model.impl.StChSalesProjectionMasterImpl;
import com.stpl.app.model.impl.StChSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPK;
import com.stpl.app.service.persistence.StChSalesProjectionMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st ch sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.StChSalesProjectionMasterUtil
 * @generated
 */
@ProviderType
public class StChSalesProjectionMasterPersistenceImpl
	extends BasePersistenceImpl<StChSalesProjectionMaster>
	implements StChSalesProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StChSalesProjectionMasterUtil} to access the st ch sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StChSalesProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			StChSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			StChSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public StChSalesProjectionMasterPersistenceImpl() {
		setModelClass(StChSalesProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("calculationPeriods", "CALCULATION_PERIODS");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("sessionId", "SESSION_ID");
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
	 * Caches the st ch sales projection master in the entity cache if it is enabled.
	 *
	 * @param stChSalesProjectionMaster the st ch sales projection master
	 */
	@Override
	public void cacheResult(StChSalesProjectionMaster stChSalesProjectionMaster) {
		entityCache.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterImpl.class,
			stChSalesProjectionMaster.getPrimaryKey(), stChSalesProjectionMaster);

		stChSalesProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the st ch sales projection masters in the entity cache if it is enabled.
	 *
	 * @param stChSalesProjectionMasters the st ch sales projection masters
	 */
	@Override
	public void cacheResult(
		List<StChSalesProjectionMaster> stChSalesProjectionMasters) {
		for (StChSalesProjectionMaster stChSalesProjectionMaster : stChSalesProjectionMasters) {
			if (entityCache.getResult(
						StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						StChSalesProjectionMasterImpl.class,
						stChSalesProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(stChSalesProjectionMaster);
			}
			else {
				stChSalesProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st ch sales projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StChSalesProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st ch sales projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StChSalesProjectionMaster stChSalesProjectionMaster) {
		entityCache.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterImpl.class,
			stChSalesProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StChSalesProjectionMaster> stChSalesProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StChSalesProjectionMaster stChSalesProjectionMaster : stChSalesProjectionMasters) {
			entityCache.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				StChSalesProjectionMasterImpl.class,
				stChSalesProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st ch sales projection master with the primary key. Does not add the st ch sales projection master to the database.
	 *
	 * @param stChSalesProjectionMasterPK the primary key for the new st ch sales projection master
	 * @return the new st ch sales projection master
	 */
	@Override
	public StChSalesProjectionMaster create(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		StChSalesProjectionMaster stChSalesProjectionMaster = new StChSalesProjectionMasterImpl();

		stChSalesProjectionMaster.setNew(true);
		stChSalesProjectionMaster.setPrimaryKey(stChSalesProjectionMasterPK);

		return stChSalesProjectionMaster;
	}

	/**
	 * Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	 * @return the st ch sales projection master that was removed
	 * @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster remove(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws NoSuchStChSalesProjectionMasterException {
		return remove((Serializable)stChSalesProjectionMasterPK);
	}

	/**
	 * Removes the st ch sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st ch sales projection master
	 * @return the st ch sales projection master that was removed
	 * @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster remove(Serializable primaryKey)
		throws NoSuchStChSalesProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			StChSalesProjectionMaster stChSalesProjectionMaster = (StChSalesProjectionMaster)session.get(StChSalesProjectionMasterImpl.class,
					primaryKey);

			if (stChSalesProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stChSalesProjectionMaster);
		}
		catch (NoSuchStChSalesProjectionMasterException nsee) {
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
	protected StChSalesProjectionMaster removeImpl(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		stChSalesProjectionMaster = toUnwrappedModel(stChSalesProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stChSalesProjectionMaster)) {
				stChSalesProjectionMaster = (StChSalesProjectionMaster)session.get(StChSalesProjectionMasterImpl.class,
						stChSalesProjectionMaster.getPrimaryKeyObj());
			}

			if (stChSalesProjectionMaster != null) {
				session.delete(stChSalesProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stChSalesProjectionMaster != null) {
			clearCache(stChSalesProjectionMaster);
		}

		return stChSalesProjectionMaster;
	}

	@Override
	public StChSalesProjectionMaster updateImpl(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		stChSalesProjectionMaster = toUnwrappedModel(stChSalesProjectionMaster);

		boolean isNew = stChSalesProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stChSalesProjectionMaster.isNew()) {
				session.save(stChSalesProjectionMaster);

				stChSalesProjectionMaster.setNew(false);
			}
			else {
				stChSalesProjectionMaster = (StChSalesProjectionMaster)session.merge(stChSalesProjectionMaster);
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

		entityCache.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChSalesProjectionMasterImpl.class,
			stChSalesProjectionMaster.getPrimaryKey(),
			stChSalesProjectionMaster, false);

		stChSalesProjectionMaster.resetOriginalValues();

		return stChSalesProjectionMaster;
	}

	protected StChSalesProjectionMaster toUnwrappedModel(
		StChSalesProjectionMaster stChSalesProjectionMaster) {
		if (stChSalesProjectionMaster instanceof StChSalesProjectionMasterImpl) {
			return stChSalesProjectionMaster;
		}

		StChSalesProjectionMasterImpl stChSalesProjectionMasterImpl = new StChSalesProjectionMasterImpl();

		stChSalesProjectionMasterImpl.setNew(stChSalesProjectionMaster.isNew());
		stChSalesProjectionMasterImpl.setPrimaryKey(stChSalesProjectionMaster.getPrimaryKey());

		stChSalesProjectionMasterImpl.setLastModifiedDate(stChSalesProjectionMaster.getLastModifiedDate());
		stChSalesProjectionMasterImpl.setCheckRecord(stChSalesProjectionMaster.isCheckRecord());
		stChSalesProjectionMasterImpl.setCalculationPeriods(stChSalesProjectionMaster.getCalculationPeriods());
		stChSalesProjectionMasterImpl.setProjectionDetailsSid(stChSalesProjectionMaster.getProjectionDetailsSid());
		stChSalesProjectionMasterImpl.setUserId(stChSalesProjectionMaster.getUserId());
		stChSalesProjectionMasterImpl.setSessionId(stChSalesProjectionMaster.getSessionId());
		stChSalesProjectionMasterImpl.setMethodology(stChSalesProjectionMaster.getMethodology());

		return stChSalesProjectionMasterImpl;
	}

	/**
	 * Returns the st ch sales projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch sales projection master
	 * @return the st ch sales projection master
	 * @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStChSalesProjectionMasterException {
		StChSalesProjectionMaster stChSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (stChSalesProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStChSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stChSalesProjectionMaster;
	}

	/**
	 * Returns the st ch sales projection master with the primary key or throws a {@link NoSuchStChSalesProjectionMasterException} if it could not be found.
	 *
	 * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	 * @return the st ch sales projection master
	 * @throws NoSuchStChSalesProjectionMasterException if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster findByPrimaryKey(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK)
		throws NoSuchStChSalesProjectionMasterException {
		return findByPrimaryKey((Serializable)stChSalesProjectionMasterPK);
	}

	/**
	 * Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch sales projection master
	 * @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				StChSalesProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StChSalesProjectionMaster stChSalesProjectionMaster = (StChSalesProjectionMaster)serializable;

		if (stChSalesProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stChSalesProjectionMaster = (StChSalesProjectionMaster)session.get(StChSalesProjectionMasterImpl.class,
						primaryKey);

				if (stChSalesProjectionMaster != null) {
					cacheResult(stChSalesProjectionMaster);
				}
				else {
					entityCache.putResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						StChSalesProjectionMasterImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StChSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					StChSalesProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stChSalesProjectionMaster;
	}

	/**
	 * Returns the st ch sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stChSalesProjectionMasterPK the primary key of the st ch sales projection master
	 * @return the st ch sales projection master, or <code>null</code> if a st ch sales projection master with the primary key could not be found
	 */
	@Override
	public StChSalesProjectionMaster fetchByPrimaryKey(
		StChSalesProjectionMasterPK stChSalesProjectionMasterPK) {
		return fetchByPrimaryKey((Serializable)stChSalesProjectionMasterPK);
	}

	@Override
	public Map<Serializable, StChSalesProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StChSalesProjectionMaster> map = new HashMap<Serializable, StChSalesProjectionMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StChSalesProjectionMaster stChSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (stChSalesProjectionMaster != null) {
				map.put(primaryKey, stChSalesProjectionMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st ch sales projection masters.
	 *
	 * @return the st ch sales projection masters
	 */
	@Override
	public List<StChSalesProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch sales projection masters
	 * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	 * @return the range of st ch sales projection masters
	 */
	@Override
	public List<StChSalesProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch sales projection masters
	 * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st ch sales projection masters
	 */
	@Override
	public List<StChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<StChSalesProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st ch sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch sales projection masters
	 * @param end the upper bound of the range of st ch sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st ch sales projection masters
	 */
	@Override
	public List<StChSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<StChSalesProjectionMaster> orderByComparator,
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

		List<StChSalesProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StChSalesProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCHSALESPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCHSALESPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(StChSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StChSalesProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StChSalesProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the st ch sales projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StChSalesProjectionMaster stChSalesProjectionMaster : findAll()) {
			remove(stChSalesProjectionMaster);
		}
	}

	/**
	 * Returns the number of st ch sales projection masters.
	 *
	 * @return the number of st ch sales projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCHSALESPROJECTIONMASTER);

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
		return StChSalesProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st ch sales projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StChSalesProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STCHSALESPROJECTIONMASTER = "SELECT stChSalesProjectionMaster FROM StChSalesProjectionMaster stChSalesProjectionMaster";
	private static final String _SQL_COUNT_STCHSALESPROJECTIONMASTER = "SELECT COUNT(stChSalesProjectionMaster) FROM StChSalesProjectionMaster stChSalesProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stChSalesProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChSalesProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StChSalesProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "checkRecord", "calculationPeriods",
				"projectionDetailsSid", "userId", "sessionId", "methodology"
			});
}