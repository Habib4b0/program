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

import com.stpl.app.exception.NoSuchStNmPpaProjectionMasterException;
import com.stpl.app.model.StNmPpaProjectionMaster;
import com.stpl.app.model.impl.StNmPpaProjectionMasterImpl;
import com.stpl.app.model.impl.StNmPpaProjectionMasterModelImpl;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPK;
import com.stpl.app.service.persistence.StNmPpaProjectionMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmPpaProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.StNmPpaProjectionMasterUtil
 * @generated
 */
@ProviderType
public class StNmPpaProjectionMasterPersistenceImpl extends BasePersistenceImpl<StNmPpaProjectionMaster>
	implements StNmPpaProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmPpaProjectionMasterUtil} to access the st nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmPpaProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			StNmPpaProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			StNmPpaProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmPpaProjectionMasterPersistenceImpl() {
		setModelClass(StNmPpaProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("userGroup", "USER_GROUP");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("priceBasis", "PRICE_BASIS");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("actualPriceCap", "ACTUAL_PRICE_CAP");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st nm ppa projection master in the entity cache if it is enabled.
	 *
	 * @param stNmPpaProjectionMaster the st nm ppa projection master
	 */
	@Override
	public void cacheResult(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		entityCache.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterImpl.class,
			stNmPpaProjectionMaster.getPrimaryKey(), stNmPpaProjectionMaster);

		stNmPpaProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the st nm ppa projection masters in the entity cache if it is enabled.
	 *
	 * @param stNmPpaProjectionMasters the st nm ppa projection masters
	 */
	@Override
	public void cacheResult(
		List<StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
		for (StNmPpaProjectionMaster stNmPpaProjectionMaster : stNmPpaProjectionMasters) {
			if (entityCache.getResult(
						StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						StNmPpaProjectionMasterImpl.class,
						stNmPpaProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(stNmPpaProjectionMaster);
			}
			else {
				stNmPpaProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm ppa projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmPpaProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm ppa projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		entityCache.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterImpl.class,
			stNmPpaProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StNmPpaProjectionMaster> stNmPpaProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmPpaProjectionMaster stNmPpaProjectionMaster : stNmPpaProjectionMasters) {
			entityCache.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				StNmPpaProjectionMasterImpl.class,
				stNmPpaProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm ppa projection master with the primary key. Does not add the st nm ppa projection master to the database.
	 *
	 * @param stNmPpaProjectionMasterPK the primary key for the new st nm ppa projection master
	 * @return the new st nm ppa projection master
	 */
	@Override
	public StNmPpaProjectionMaster create(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		StNmPpaProjectionMaster stNmPpaProjectionMaster = new StNmPpaProjectionMasterImpl();

		stNmPpaProjectionMaster.setNew(true);
		stNmPpaProjectionMaster.setPrimaryKey(stNmPpaProjectionMasterPK);

		return stNmPpaProjectionMaster;
	}

	/**
	 * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master that was removed
	 * @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster remove(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws NoSuchStNmPpaProjectionMasterException {
		return remove((Serializable)stNmPpaProjectionMasterPK);
	}

	/**
	 * Removes the st nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master that was removed
	 * @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster remove(Serializable primaryKey)
		throws NoSuchStNmPpaProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			StNmPpaProjectionMaster stNmPpaProjectionMaster = (StNmPpaProjectionMaster)session.get(StNmPpaProjectionMasterImpl.class,
					primaryKey);

			if (stNmPpaProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmPpaProjectionMaster);
		}
		catch (NoSuchStNmPpaProjectionMasterException nsee) {
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
	protected StNmPpaProjectionMaster removeImpl(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		stNmPpaProjectionMaster = toUnwrappedModel(stNmPpaProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmPpaProjectionMaster)) {
				stNmPpaProjectionMaster = (StNmPpaProjectionMaster)session.get(StNmPpaProjectionMasterImpl.class,
						stNmPpaProjectionMaster.getPrimaryKeyObj());
			}

			if (stNmPpaProjectionMaster != null) {
				session.delete(stNmPpaProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmPpaProjectionMaster != null) {
			clearCache(stNmPpaProjectionMaster);
		}

		return stNmPpaProjectionMaster;
	}

	@Override
	public StNmPpaProjectionMaster updateImpl(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		stNmPpaProjectionMaster = toUnwrappedModel(stNmPpaProjectionMaster);

		boolean isNew = stNmPpaProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmPpaProjectionMaster.isNew()) {
				session.save(stNmPpaProjectionMaster);

				stNmPpaProjectionMaster.setNew(false);
			}
			else {
				stNmPpaProjectionMaster = (StNmPpaProjectionMaster)session.merge(stNmPpaProjectionMaster);
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

		entityCache.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			StNmPpaProjectionMasterImpl.class,
			stNmPpaProjectionMaster.getPrimaryKey(), stNmPpaProjectionMaster,
			false);

		stNmPpaProjectionMaster.resetOriginalValues();

		return stNmPpaProjectionMaster;
	}

	protected StNmPpaProjectionMaster toUnwrappedModel(
		StNmPpaProjectionMaster stNmPpaProjectionMaster) {
		if (stNmPpaProjectionMaster instanceof StNmPpaProjectionMasterImpl) {
			return stNmPpaProjectionMaster;
		}

		StNmPpaProjectionMasterImpl stNmPpaProjectionMasterImpl = new StNmPpaProjectionMasterImpl();

		stNmPpaProjectionMasterImpl.setNew(stNmPpaProjectionMaster.isNew());
		stNmPpaProjectionMasterImpl.setPrimaryKey(stNmPpaProjectionMaster.getPrimaryKey());

		stNmPpaProjectionMasterImpl.setLastModifiedDate(stNmPpaProjectionMaster.getLastModifiedDate());
		stNmPpaProjectionMasterImpl.setCheckRecord(stNmPpaProjectionMaster.isCheckRecord());
		stNmPpaProjectionMasterImpl.setUserGroup(stNmPpaProjectionMaster.getUserGroup());
		stNmPpaProjectionMasterImpl.setProjectionDetailsSid(stNmPpaProjectionMaster.getProjectionDetailsSid());
		stNmPpaProjectionMasterImpl.setUserId(stNmPpaProjectionMaster.getUserId());
		stNmPpaProjectionMasterImpl.setSessionId(stNmPpaProjectionMaster.getSessionId());
		stNmPpaProjectionMasterImpl.setPriceBasis(stNmPpaProjectionMaster.getPriceBasis());
		stNmPpaProjectionMasterImpl.setPriceProtectionEndDate(stNmPpaProjectionMaster.getPriceProtectionEndDate());
		stNmPpaProjectionMasterImpl.setPriceProtectionStartDate(stNmPpaProjectionMaster.getPriceProtectionStartDate());
		stNmPpaProjectionMasterImpl.setActualPriceCap(stNmPpaProjectionMaster.getActualPriceCap());

		return stNmPpaProjectionMasterImpl;
	}

	/**
	 * Returns the st nm ppa projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master
	 * @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmPpaProjectionMasterException {
		StNmPpaProjectionMaster stNmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (stNmPpaProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmPpaProjectionMaster;
	}

	/**
	 * Returns the st nm ppa projection master with the primary key or throws a {@link NoSuchStNmPpaProjectionMasterException} if it could not be found.
	 *
	 * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master
	 * @throws NoSuchStNmPpaProjectionMasterException if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster findByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK)
		throws NoSuchStNmPpaProjectionMasterException {
		return findByPrimaryKey((Serializable)stNmPpaProjectionMasterPK);
	}

	/**
	 * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				StNmPpaProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmPpaProjectionMaster stNmPpaProjectionMaster = (StNmPpaProjectionMaster)serializable;

		if (stNmPpaProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stNmPpaProjectionMaster = (StNmPpaProjectionMaster)session.get(StNmPpaProjectionMasterImpl.class,
						primaryKey);

				if (stNmPpaProjectionMaster != null) {
					cacheResult(stNmPpaProjectionMaster);
				}
				else {
					entityCache.putResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						StNmPpaProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					StNmPpaProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmPpaProjectionMaster;
	}

	/**
	 * Returns the st nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmPpaProjectionMasterPK the primary key of the st nm ppa projection master
	 * @return the st nm ppa projection master, or <code>null</code> if a st nm ppa projection master with the primary key could not be found
	 */
	@Override
	public StNmPpaProjectionMaster fetchByPrimaryKey(
		StNmPpaProjectionMasterPK stNmPpaProjectionMasterPK) {
		return fetchByPrimaryKey((Serializable)stNmPpaProjectionMasterPK);
	}

	@Override
	public Map<Serializable, StNmPpaProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmPpaProjectionMaster> map = new HashMap<Serializable, StNmPpaProjectionMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StNmPpaProjectionMaster stNmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (stNmPpaProjectionMaster != null) {
				map.put(primaryKey, stNmPpaProjectionMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm ppa projection masters.
	 *
	 * @return the st nm ppa projection masters
	 */
	@Override
	public List<StNmPpaProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projection masters
	 * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	 * @return the range of st nm ppa projection masters
	 */
	@Override
	public List<StNmPpaProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projection masters
	 * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm ppa projection masters
	 */
	@Override
	public List<StNmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<StNmPpaProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm ppa projection masters
	 * @param end the upper bound of the range of st nm ppa projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm ppa projection masters
	 */
	@Override
	public List<StNmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<StNmPpaProjectionMaster> orderByComparator,
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

		List<StNmPpaProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StNmPpaProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMPPAPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMPPAPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(StNmPpaProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmPpaProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmPpaProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the st nm ppa projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmPpaProjectionMaster stNmPpaProjectionMaster : findAll()) {
			remove(stNmPpaProjectionMaster);
		}
	}

	/**
	 * Returns the number of st nm ppa projection masters.
	 *
	 * @return the number of st nm ppa projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMPPAPROJECTIONMASTER);

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
		return StNmPpaProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm ppa projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmPpaProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMPPAPROJECTIONMASTER = "SELECT stNmPpaProjectionMaster FROM StNmPpaProjectionMaster stNmPpaProjectionMaster";
	private static final String _SQL_COUNT_STNMPPAPROJECTIONMASTER = "SELECT COUNT(stNmPpaProjectionMaster) FROM StNmPpaProjectionMaster stNmPpaProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmPpaProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmPpaProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmPpaProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "checkRecord", "userGroup",
				"projectionDetailsSid", "userId", "sessionId", "priceBasis",
				"priceProtectionEndDate", "priceProtectionStartDate",
				"actualPriceCap"
			});
}