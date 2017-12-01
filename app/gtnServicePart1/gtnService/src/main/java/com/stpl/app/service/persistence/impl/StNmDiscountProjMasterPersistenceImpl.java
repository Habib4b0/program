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

import com.stpl.app.exception.NoSuchStNmDiscountProjMasterException;
import com.stpl.app.model.StNmDiscountProjMaster;
import com.stpl.app.model.impl.StNmDiscountProjMasterImpl;
import com.stpl.app.model.impl.StNmDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPK;
import com.stpl.app.service.persistence.StNmDiscountProjMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.StNmDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public class StNmDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<StNmDiscountProjMaster>
	implements StNmDiscountProjMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmDiscountProjMasterUtil} to access the st nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmDiscountProjMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			StNmDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			StNmDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmDiscountProjMasterPersistenceImpl() {
		setModelClass(StNmDiscountProjMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("selectedPeriods", "SELECTED_PERIODS");
			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("netFlag", "NET_FLAG");
			dbColumnNames.put("priceGroupType", "PRICE_GROUP_TYPE");
			dbColumnNames.put("userGroup", "USER_GROUP");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("baselinePeriods", "BASELINE_PERIODS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st nm discount proj master in the entity cache if it is enabled.
	 *
	 * @param stNmDiscountProjMaster the st nm discount proj master
	 */
	@Override
	public void cacheResult(StNmDiscountProjMaster stNmDiscountProjMaster) {
		entityCache.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterImpl.class,
			stNmDiscountProjMaster.getPrimaryKey(), stNmDiscountProjMaster);

		stNmDiscountProjMaster.resetOriginalValues();
	}

	/**
	 * Caches the st nm discount proj masters in the entity cache if it is enabled.
	 *
	 * @param stNmDiscountProjMasters the st nm discount proj masters
	 */
	@Override
	public void cacheResult(
		List<StNmDiscountProjMaster> stNmDiscountProjMasters) {
		for (StNmDiscountProjMaster stNmDiscountProjMaster : stNmDiscountProjMasters) {
			if (entityCache.getResult(
						StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						StNmDiscountProjMasterImpl.class,
						stNmDiscountProjMaster.getPrimaryKey()) == null) {
				cacheResult(stNmDiscountProjMaster);
			}
			else {
				stNmDiscountProjMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm discount proj masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmDiscountProjMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm discount proj master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmDiscountProjMaster stNmDiscountProjMaster) {
		entityCache.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterImpl.class,
			stNmDiscountProjMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmDiscountProjMaster> stNmDiscountProjMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmDiscountProjMaster stNmDiscountProjMaster : stNmDiscountProjMasters) {
			entityCache.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				StNmDiscountProjMasterImpl.class,
				stNmDiscountProjMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm discount proj master with the primary key. Does not add the st nm discount proj master to the database.
	 *
	 * @param stNmDiscountProjMasterPK the primary key for the new st nm discount proj master
	 * @return the new st nm discount proj master
	 */
	@Override
	public StNmDiscountProjMaster create(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		StNmDiscountProjMaster stNmDiscountProjMaster = new StNmDiscountProjMasterImpl();

		stNmDiscountProjMaster.setNew(true);
		stNmDiscountProjMaster.setPrimaryKey(stNmDiscountProjMasterPK);

		return stNmDiscountProjMaster;
	}

	/**
	 * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	 * @return the st nm discount proj master that was removed
	 * @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster remove(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws NoSuchStNmDiscountProjMasterException {
		return remove((Serializable)stNmDiscountProjMasterPK);
	}

	/**
	 * Removes the st nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm discount proj master
	 * @return the st nm discount proj master that was removed
	 * @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster remove(Serializable primaryKey)
		throws NoSuchStNmDiscountProjMasterException {
		Session session = null;

		try {
			session = openSession();

			StNmDiscountProjMaster stNmDiscountProjMaster = (StNmDiscountProjMaster)session.get(StNmDiscountProjMasterImpl.class,
					primaryKey);

			if (stNmDiscountProjMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmDiscountProjMaster);
		}
		catch (NoSuchStNmDiscountProjMasterException nsee) {
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
	protected StNmDiscountProjMaster removeImpl(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		stNmDiscountProjMaster = toUnwrappedModel(stNmDiscountProjMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmDiscountProjMaster)) {
				stNmDiscountProjMaster = (StNmDiscountProjMaster)session.get(StNmDiscountProjMasterImpl.class,
						stNmDiscountProjMaster.getPrimaryKeyObj());
			}

			if (stNmDiscountProjMaster != null) {
				session.delete(stNmDiscountProjMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmDiscountProjMaster != null) {
			clearCache(stNmDiscountProjMaster);
		}

		return stNmDiscountProjMaster;
	}

	@Override
	public StNmDiscountProjMaster updateImpl(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		stNmDiscountProjMaster = toUnwrappedModel(stNmDiscountProjMaster);

		boolean isNew = stNmDiscountProjMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmDiscountProjMaster.isNew()) {
				session.save(stNmDiscountProjMaster);

				stNmDiscountProjMaster.setNew(false);
			}
			else {
				stNmDiscountProjMaster = (StNmDiscountProjMaster)session.merge(stNmDiscountProjMaster);
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

		entityCache.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmDiscountProjMasterImpl.class,
			stNmDiscountProjMaster.getPrimaryKey(), stNmDiscountProjMaster,
			false);

		stNmDiscountProjMaster.resetOriginalValues();

		return stNmDiscountProjMaster;
	}

	protected StNmDiscountProjMaster toUnwrappedModel(
		StNmDiscountProjMaster stNmDiscountProjMaster) {
		if (stNmDiscountProjMaster instanceof StNmDiscountProjMasterImpl) {
			return stNmDiscountProjMaster;
		}

		StNmDiscountProjMasterImpl stNmDiscountProjMasterImpl = new StNmDiscountProjMasterImpl();

		stNmDiscountProjMasterImpl.setNew(stNmDiscountProjMaster.isNew());
		stNmDiscountProjMasterImpl.setPrimaryKey(stNmDiscountProjMaster.getPrimaryKey());

		stNmDiscountProjMasterImpl.setSelectedPeriods(stNmDiscountProjMaster.getSelectedPeriods());
		stNmDiscountProjMasterImpl.setMethodology(stNmDiscountProjMaster.getMethodology());
		stNmDiscountProjMasterImpl.setNetFlag(stNmDiscountProjMaster.getNetFlag());
		stNmDiscountProjMasterImpl.setPriceGroupType(stNmDiscountProjMaster.getPriceGroupType());
		stNmDiscountProjMasterImpl.setUserGroup(stNmDiscountProjMaster.getUserGroup());
		stNmDiscountProjMasterImpl.setUserId(stNmDiscountProjMaster.getUserId());
		stNmDiscountProjMasterImpl.setLastModifiedDate(stNmDiscountProjMaster.getLastModifiedDate());
		stNmDiscountProjMasterImpl.setProjectionDetailsSid(stNmDiscountProjMaster.getProjectionDetailsSid());
		stNmDiscountProjMasterImpl.setRsModelSid(stNmDiscountProjMaster.getRsModelSid());
		stNmDiscountProjMasterImpl.setSessionId(stNmDiscountProjMaster.getSessionId());
		stNmDiscountProjMasterImpl.setCheckRecord(stNmDiscountProjMaster.isCheckRecord());
		stNmDiscountProjMasterImpl.setBaselinePeriods(stNmDiscountProjMaster.getBaselinePeriods());

		return stNmDiscountProjMasterImpl;
	}

	/**
	 * Returns the st nm discount proj master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm discount proj master
	 * @return the st nm discount proj master
	 * @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmDiscountProjMasterException {
		StNmDiscountProjMaster stNmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

		if (stNmDiscountProjMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmDiscountProjMaster;
	}

	/**
	 * Returns the st nm discount proj master with the primary key or throws a {@link NoSuchStNmDiscountProjMasterException} if it could not be found.
	 *
	 * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	 * @return the st nm discount proj master
	 * @throws NoSuchStNmDiscountProjMasterException if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster findByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK)
		throws NoSuchStNmDiscountProjMasterException {
		return findByPrimaryKey((Serializable)stNmDiscountProjMasterPK);
	}

	/**
	 * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm discount proj master
	 * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				StNmDiscountProjMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmDiscountProjMaster stNmDiscountProjMaster = (StNmDiscountProjMaster)serializable;

		if (stNmDiscountProjMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stNmDiscountProjMaster = (StNmDiscountProjMaster)session.get(StNmDiscountProjMasterImpl.class,
						primaryKey);

				if (stNmDiscountProjMaster != null) {
					cacheResult(stNmDiscountProjMaster);
				}
				else {
					entityCache.putResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						StNmDiscountProjMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					StNmDiscountProjMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmDiscountProjMaster;
	}

	/**
	 * Returns the st nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmDiscountProjMasterPK the primary key of the st nm discount proj master
	 * @return the st nm discount proj master, or <code>null</code> if a st nm discount proj master with the primary key could not be found
	 */
	@Override
	public StNmDiscountProjMaster fetchByPrimaryKey(
		StNmDiscountProjMasterPK stNmDiscountProjMasterPK) {
		return fetchByPrimaryKey((Serializable)stNmDiscountProjMasterPK);
	}

	@Override
	public Map<Serializable, StNmDiscountProjMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmDiscountProjMaster> map = new HashMap<Serializable, StNmDiscountProjMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StNmDiscountProjMaster stNmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

			if (stNmDiscountProjMaster != null) {
				map.put(primaryKey, stNmDiscountProjMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm discount proj masters.
	 *
	 * @return the st nm discount proj masters
	 */
	@Override
	public List<StNmDiscountProjMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount proj masters
	 * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	 * @return the range of st nm discount proj masters
	 */
	@Override
	public List<StNmDiscountProjMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount proj masters
	 * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm discount proj masters
	 */
	@Override
	public List<StNmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm discount proj masters
	 * @param end the upper bound of the range of st nm discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm discount proj masters
	 */
	@Override
	public List<StNmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StNmDiscountProjMaster> orderByComparator,
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

		List<StNmDiscountProjMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StNmDiscountProjMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMDISCOUNTPROJMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMDISCOUNTPROJMASTER;

				if (pagination) {
					sql = sql.concat(StNmDiscountProjMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmDiscountProjMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmDiscountProjMaster>)QueryUtil.list(q,
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
	 * Removes all the st nm discount proj masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmDiscountProjMaster stNmDiscountProjMaster : findAll()) {
			remove(stNmDiscountProjMaster);
		}
	}

	/**
	 * Returns the number of st nm discount proj masters.
	 *
	 * @return the number of st nm discount proj masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMDISCOUNTPROJMASTER);

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
		return StNmDiscountProjMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm discount proj master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmDiscountProjMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMDISCOUNTPROJMASTER = "SELECT stNmDiscountProjMaster FROM StNmDiscountProjMaster stNmDiscountProjMaster";
	private static final String _SQL_COUNT_STNMDISCOUNTPROJMASTER = "SELECT COUNT(stNmDiscountProjMaster) FROM StNmDiscountProjMaster stNmDiscountProjMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmDiscountProjMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmDiscountProjMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmDiscountProjMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"selectedPeriods", "methodology", "netFlag", "priceGroupType",
				"userGroup", "userId", "lastModifiedDate",
				"projectionDetailsSid", "rsModelSid", "sessionId", "checkRecord",
				"baselinePeriods"
			});
}