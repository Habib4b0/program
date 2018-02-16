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

import com.stpl.app.exception.NoSuchStChDiscountProjMasterException;
import com.stpl.app.model.StChDiscountProjMaster;
import com.stpl.app.model.impl.StChDiscountProjMasterImpl;
import com.stpl.app.model.impl.StChDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.StChDiscountProjMasterPK;
import com.stpl.app.service.persistence.StChDiscountProjMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.StChDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public class StChDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<StChDiscountProjMaster>
	implements StChDiscountProjMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StChDiscountProjMasterUtil} to access the st ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StChDiscountProjMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			StChDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			StChDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StChDiscountProjMasterPersistenceImpl() {
		setModelClass(StChDiscountProjMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("selectedPeriods", "SELECTED_PERIODS");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("priceGroupType", "PRICE_GROUP_TYPE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("netFlag", "NET_FLAG");
			dbColumnNames.put("projectedType", "PROJECTED_TYPE");
			dbColumnNames.put("baselinePeriods", "BASELINE_PERIODS");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("discountType", "DISCOUNT_TYPE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st ch discount proj master in the entity cache if it is enabled.
	 *
	 * @param stChDiscountProjMaster the st ch discount proj master
	 */
	@Override
	public void cacheResult(StChDiscountProjMaster stChDiscountProjMaster) {
		entityCache.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterImpl.class,
			stChDiscountProjMaster.getPrimaryKey(), stChDiscountProjMaster);

		stChDiscountProjMaster.resetOriginalValues();
	}

	/**
	 * Caches the st ch discount proj masters in the entity cache if it is enabled.
	 *
	 * @param stChDiscountProjMasters the st ch discount proj masters
	 */
	@Override
	public void cacheResult(
		List<StChDiscountProjMaster> stChDiscountProjMasters) {
		for (StChDiscountProjMaster stChDiscountProjMaster : stChDiscountProjMasters) {
			if (entityCache.getResult(
						StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						StChDiscountProjMasterImpl.class,
						stChDiscountProjMaster.getPrimaryKey()) == null) {
				cacheResult(stChDiscountProjMaster);
			}
			else {
				stChDiscountProjMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st ch discount proj masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StChDiscountProjMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st ch discount proj master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StChDiscountProjMaster stChDiscountProjMaster) {
		entityCache.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterImpl.class,
			stChDiscountProjMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StChDiscountProjMaster> stChDiscountProjMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StChDiscountProjMaster stChDiscountProjMaster : stChDiscountProjMasters) {
			entityCache.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				StChDiscountProjMasterImpl.class,
				stChDiscountProjMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st ch discount proj master with the primary key. Does not add the st ch discount proj master to the database.
	 *
	 * @param stChDiscountProjMasterPK the primary key for the new st ch discount proj master
	 * @return the new st ch discount proj master
	 */
	@Override
	public StChDiscountProjMaster create(
		StChDiscountProjMasterPK stChDiscountProjMasterPK) {
		StChDiscountProjMaster stChDiscountProjMaster = new StChDiscountProjMasterImpl();

		stChDiscountProjMaster.setNew(true);
		stChDiscountProjMaster.setPrimaryKey(stChDiscountProjMasterPK);

		return stChDiscountProjMaster;
	}

	/**
	 * Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	 * @return the st ch discount proj master that was removed
	 * @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster remove(
		StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws NoSuchStChDiscountProjMasterException {
		return remove((Serializable)stChDiscountProjMasterPK);
	}

	/**
	 * Removes the st ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st ch discount proj master
	 * @return the st ch discount proj master that was removed
	 * @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster remove(Serializable primaryKey)
		throws NoSuchStChDiscountProjMasterException {
		Session session = null;

		try {
			session = openSession();

			StChDiscountProjMaster stChDiscountProjMaster = (StChDiscountProjMaster)session.get(StChDiscountProjMasterImpl.class,
					primaryKey);

			if (stChDiscountProjMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stChDiscountProjMaster);
		}
		catch (NoSuchStChDiscountProjMasterException nsee) {
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
	protected StChDiscountProjMaster removeImpl(
		StChDiscountProjMaster stChDiscountProjMaster) {
		stChDiscountProjMaster = toUnwrappedModel(stChDiscountProjMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stChDiscountProjMaster)) {
				stChDiscountProjMaster = (StChDiscountProjMaster)session.get(StChDiscountProjMasterImpl.class,
						stChDiscountProjMaster.getPrimaryKeyObj());
			}

			if (stChDiscountProjMaster != null) {
				session.delete(stChDiscountProjMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stChDiscountProjMaster != null) {
			clearCache(stChDiscountProjMaster);
		}

		return stChDiscountProjMaster;
	}

	@Override
	public StChDiscountProjMaster updateImpl(
		StChDiscountProjMaster stChDiscountProjMaster) {
		stChDiscountProjMaster = toUnwrappedModel(stChDiscountProjMaster);

		boolean isNew = stChDiscountProjMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stChDiscountProjMaster.isNew()) {
				session.save(stChDiscountProjMaster);

				stChDiscountProjMaster.setNew(false);
			}
			else {
				stChDiscountProjMaster = (StChDiscountProjMaster)session.merge(stChDiscountProjMaster);
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

		entityCache.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			StChDiscountProjMasterImpl.class,
			stChDiscountProjMaster.getPrimaryKey(), stChDiscountProjMaster,
			false);

		stChDiscountProjMaster.resetOriginalValues();

		return stChDiscountProjMaster;
	}

	protected StChDiscountProjMaster toUnwrappedModel(
		StChDiscountProjMaster stChDiscountProjMaster) {
		if (stChDiscountProjMaster instanceof StChDiscountProjMasterImpl) {
			return stChDiscountProjMaster;
		}

		StChDiscountProjMasterImpl stChDiscountProjMasterImpl = new StChDiscountProjMasterImpl();

		stChDiscountProjMasterImpl.setNew(stChDiscountProjMaster.isNew());
		stChDiscountProjMasterImpl.setPrimaryKey(stChDiscountProjMaster.getPrimaryKey());

		stChDiscountProjMasterImpl.setCheckRecord(stChDiscountProjMaster.isCheckRecord());
		stChDiscountProjMasterImpl.setSelectedPeriods(stChDiscountProjMaster.getSelectedPeriods());
		stChDiscountProjMasterImpl.setLastModifiedDate(stChDiscountProjMaster.getLastModifiedDate());
		stChDiscountProjMasterImpl.setProjectionDetailsSid(stChDiscountProjMaster.getProjectionDetailsSid());
		stChDiscountProjMasterImpl.setPriceGroupType(stChDiscountProjMaster.getPriceGroupType());
		stChDiscountProjMasterImpl.setUserId(stChDiscountProjMaster.getUserId());
		stChDiscountProjMasterImpl.setNetFlag(stChDiscountProjMaster.getNetFlag());
		stChDiscountProjMasterImpl.setProjectedType(stChDiscountProjMaster.getProjectedType());
		stChDiscountProjMasterImpl.setBaselinePeriods(stChDiscountProjMaster.getBaselinePeriods());
		stChDiscountProjMasterImpl.setSessionId(stChDiscountProjMaster.getSessionId());
		stChDiscountProjMasterImpl.setMethodology(stChDiscountProjMaster.getMethodology());
		stChDiscountProjMasterImpl.setRsModelSid(stChDiscountProjMaster.getRsModelSid());
		stChDiscountProjMasterImpl.setDiscountType(stChDiscountProjMaster.getDiscountType());

		return stChDiscountProjMasterImpl;
	}

	/**
	 * Returns the st ch discount proj master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch discount proj master
	 * @return the st ch discount proj master
	 * @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStChDiscountProjMasterException {
		StChDiscountProjMaster stChDiscountProjMaster = fetchByPrimaryKey(primaryKey);

		if (stChDiscountProjMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stChDiscountProjMaster;
	}

	/**
	 * Returns the st ch discount proj master with the primary key or throws a {@link NoSuchStChDiscountProjMasterException} if it could not be found.
	 *
	 * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	 * @return the st ch discount proj master
	 * @throws NoSuchStChDiscountProjMasterException if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster findByPrimaryKey(
		StChDiscountProjMasterPK stChDiscountProjMasterPK)
		throws NoSuchStChDiscountProjMasterException {
		return findByPrimaryKey((Serializable)stChDiscountProjMasterPK);
	}

	/**
	 * Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch discount proj master
	 * @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				StChDiscountProjMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StChDiscountProjMaster stChDiscountProjMaster = (StChDiscountProjMaster)serializable;

		if (stChDiscountProjMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stChDiscountProjMaster = (StChDiscountProjMaster)session.get(StChDiscountProjMasterImpl.class,
						primaryKey);

				if (stChDiscountProjMaster != null) {
					cacheResult(stChDiscountProjMaster);
				}
				else {
					entityCache.putResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						StChDiscountProjMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					StChDiscountProjMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stChDiscountProjMaster;
	}

	/**
	 * Returns the st ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stChDiscountProjMasterPK the primary key of the st ch discount proj master
	 * @return the st ch discount proj master, or <code>null</code> if a st ch discount proj master with the primary key could not be found
	 */
	@Override
	public StChDiscountProjMaster fetchByPrimaryKey(
		StChDiscountProjMasterPK stChDiscountProjMasterPK) {
		return fetchByPrimaryKey((Serializable)stChDiscountProjMasterPK);
	}

	@Override
	public Map<Serializable, StChDiscountProjMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StChDiscountProjMaster> map = new HashMap<Serializable, StChDiscountProjMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StChDiscountProjMaster stChDiscountProjMaster = fetchByPrimaryKey(primaryKey);

			if (stChDiscountProjMaster != null) {
				map.put(primaryKey, stChDiscountProjMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st ch discount proj masters.
	 *
	 * @return the st ch discount proj masters
	 */
	@Override
	public List<StChDiscountProjMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch discount proj masters
	 * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	 * @return the range of st ch discount proj masters
	 */
	@Override
	public List<StChDiscountProjMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch discount proj masters
	 * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st ch discount proj masters
	 */
	@Override
	public List<StChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StChDiscountProjMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch discount proj masters
	 * @param end the upper bound of the range of st ch discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st ch discount proj masters
	 */
	@Override
	public List<StChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<StChDiscountProjMaster> orderByComparator,
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

		List<StChDiscountProjMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StChDiscountProjMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCHDISCOUNTPROJMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCHDISCOUNTPROJMASTER;

				if (pagination) {
					sql = sql.concat(StChDiscountProjMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StChDiscountProjMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StChDiscountProjMaster>)QueryUtil.list(q,
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
	 * Removes all the st ch discount proj masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StChDiscountProjMaster stChDiscountProjMaster : findAll()) {
			remove(stChDiscountProjMaster);
		}
	}

	/**
	 * Returns the number of st ch discount proj masters.
	 *
	 * @return the number of st ch discount proj masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCHDISCOUNTPROJMASTER);

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
		return StChDiscountProjMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st ch discount proj master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StChDiscountProjMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STCHDISCOUNTPROJMASTER = "SELECT stChDiscountProjMaster FROM StChDiscountProjMaster stChDiscountProjMaster";
	private static final String _SQL_COUNT_STCHDISCOUNTPROJMASTER = "SELECT COUNT(stChDiscountProjMaster) FROM StChDiscountProjMaster stChDiscountProjMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stChDiscountProjMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChDiscountProjMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StChDiscountProjMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "selectedPeriods", "lastModifiedDate",
				"projectionDetailsSid", "priceGroupType", "userId", "netFlag",
				"projectedType", "baselinePeriods", "sessionId", "methodology",
				"rsModelSid", "discountType"
			});
}