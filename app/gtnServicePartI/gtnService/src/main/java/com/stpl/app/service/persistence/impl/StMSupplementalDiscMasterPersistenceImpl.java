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

import com.stpl.app.exception.NoSuchStMSupplementalDiscMasterException;
import com.stpl.app.model.StMSupplementalDiscMaster;
import com.stpl.app.model.impl.StMSupplementalDiscMasterImpl;
import com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPK;
import com.stpl.app.service.persistence.StMSupplementalDiscMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscMasterPersistence
 * @see com.stpl.app.service.persistence.StMSupplementalDiscMasterUtil
 * @generated
 */
@ProviderType
public class StMSupplementalDiscMasterPersistenceImpl
	extends BasePersistenceImpl<StMSupplementalDiscMaster>
	implements StMSupplementalDiscMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StMSupplementalDiscMasterUtil} to access the st m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StMSupplementalDiscMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public StMSupplementalDiscMasterPersistenceImpl() {
		setModelClass(StMSupplementalDiscMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualDiscountRate2", "ACTUAL_DISCOUNT_RATE2");
			dbColumnNames.put("actualDiscountRate1", "ACTUAL_DISCOUNT_RATE1");
			dbColumnNames.put("marketType", "MARKET_TYPE");
			dbColumnNames.put("actualMethodology", "ACTUAL_METHODOLOGY");
			dbColumnNames.put("actualContractPrice", "ACTUAL_CONTRACT_PRICE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("actualDiscount", "ACTUAL_DISCOUNT");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractEndDate", "CONTRACT_END_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st m supplemental disc master in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscMaster the st m supplemental disc master
	 */
	@Override
	public void cacheResult(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		entityCache.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterImpl.class,
			stMSupplementalDiscMaster.getPrimaryKey(), stMSupplementalDiscMaster);

		stMSupplementalDiscMaster.resetOriginalValues();
	}

	/**
	 * Caches the st m supplemental disc masters in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscMasters the st m supplemental disc masters
	 */
	@Override
	public void cacheResult(
		List<StMSupplementalDiscMaster> stMSupplementalDiscMasters) {
		for (StMSupplementalDiscMaster stMSupplementalDiscMaster : stMSupplementalDiscMasters) {
			if (entityCache.getResult(
						StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscMasterImpl.class,
						stMSupplementalDiscMaster.getPrimaryKey()) == null) {
				cacheResult(stMSupplementalDiscMaster);
			}
			else {
				stMSupplementalDiscMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st m supplemental disc masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StMSupplementalDiscMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st m supplemental disc master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		entityCache.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterImpl.class,
			stMSupplementalDiscMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StMSupplementalDiscMaster> stMSupplementalDiscMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StMSupplementalDiscMaster stMSupplementalDiscMaster : stMSupplementalDiscMasters) {
			entityCache.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscMasterImpl.class,
				stMSupplementalDiscMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
	 *
	 * @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
	 * @return the new st m supplemental disc master
	 */
	@Override
	public StMSupplementalDiscMaster create(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
		StMSupplementalDiscMaster stMSupplementalDiscMaster = new StMSupplementalDiscMasterImpl();

		stMSupplementalDiscMaster.setNew(true);
		stMSupplementalDiscMaster.setPrimaryKey(stMSupplementalDiscMasterPK);

		return stMSupplementalDiscMaster;
	}

	/**
	 * Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master that was removed
	 * @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster remove(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
		throws NoSuchStMSupplementalDiscMasterException {
		return remove((Serializable)stMSupplementalDiscMasterPK);
	}

	/**
	 * Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master that was removed
	 * @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster remove(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscMasterException {
		Session session = null;

		try {
			session = openSession();

			StMSupplementalDiscMaster stMSupplementalDiscMaster = (StMSupplementalDiscMaster)session.get(StMSupplementalDiscMasterImpl.class,
					primaryKey);

			if (stMSupplementalDiscMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stMSupplementalDiscMaster);
		}
		catch (NoSuchStMSupplementalDiscMasterException nsee) {
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
	protected StMSupplementalDiscMaster removeImpl(
		StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		stMSupplementalDiscMaster = toUnwrappedModel(stMSupplementalDiscMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stMSupplementalDiscMaster)) {
				stMSupplementalDiscMaster = (StMSupplementalDiscMaster)session.get(StMSupplementalDiscMasterImpl.class,
						stMSupplementalDiscMaster.getPrimaryKeyObj());
			}

			if (stMSupplementalDiscMaster != null) {
				session.delete(stMSupplementalDiscMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stMSupplementalDiscMaster != null) {
			clearCache(stMSupplementalDiscMaster);
		}

		return stMSupplementalDiscMaster;
	}

	@Override
	public StMSupplementalDiscMaster updateImpl(
		StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		stMSupplementalDiscMaster = toUnwrappedModel(stMSupplementalDiscMaster);

		boolean isNew = stMSupplementalDiscMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stMSupplementalDiscMaster.isNew()) {
				session.save(stMSupplementalDiscMaster);

				stMSupplementalDiscMaster.setNew(false);
			}
			else {
				stMSupplementalDiscMaster = (StMSupplementalDiscMaster)session.merge(stMSupplementalDiscMaster);
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

		entityCache.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscMasterImpl.class,
			stMSupplementalDiscMaster.getPrimaryKey(),
			stMSupplementalDiscMaster, false);

		stMSupplementalDiscMaster.resetOriginalValues();

		return stMSupplementalDiscMaster;
	}

	protected StMSupplementalDiscMaster toUnwrappedModel(
		StMSupplementalDiscMaster stMSupplementalDiscMaster) {
		if (stMSupplementalDiscMaster instanceof StMSupplementalDiscMasterImpl) {
			return stMSupplementalDiscMaster;
		}

		StMSupplementalDiscMasterImpl stMSupplementalDiscMasterImpl = new StMSupplementalDiscMasterImpl();

		stMSupplementalDiscMasterImpl.setNew(stMSupplementalDiscMaster.isNew());
		stMSupplementalDiscMasterImpl.setPrimaryKey(stMSupplementalDiscMaster.getPrimaryKey());

		stMSupplementalDiscMasterImpl.setActualDiscountRate2(stMSupplementalDiscMaster.getActualDiscountRate2());
		stMSupplementalDiscMasterImpl.setActualDiscountRate1(stMSupplementalDiscMaster.getActualDiscountRate1());
		stMSupplementalDiscMasterImpl.setMarketType(stMSupplementalDiscMaster.getMarketType());
		stMSupplementalDiscMasterImpl.setActualMethodology(stMSupplementalDiscMaster.getActualMethodology());
		stMSupplementalDiscMasterImpl.setActualContractPrice(stMSupplementalDiscMaster.getActualContractPrice());
		stMSupplementalDiscMasterImpl.setUserId(stMSupplementalDiscMaster.getUserId());
		stMSupplementalDiscMasterImpl.setLastModifiedDate(stMSupplementalDiscMaster.getLastModifiedDate());
		stMSupplementalDiscMasterImpl.setProjectionDetailsSid(stMSupplementalDiscMaster.getProjectionDetailsSid());
		stMSupplementalDiscMasterImpl.setActualDiscount(stMSupplementalDiscMaster.getActualDiscount());
		stMSupplementalDiscMasterImpl.setSessionId(stMSupplementalDiscMaster.getSessionId());
		stMSupplementalDiscMasterImpl.setCheckRecord(stMSupplementalDiscMaster.getCheckRecord());
		stMSupplementalDiscMasterImpl.setContractEndDate(stMSupplementalDiscMaster.getContractEndDate());

		return stMSupplementalDiscMasterImpl;
	}

	/**
	 * Returns the st m supplemental disc master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master
	 * @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscMasterException {
		StMSupplementalDiscMaster stMSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

		if (stMSupplementalDiscMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stMSupplementalDiscMaster;
	}

	/**
	 * Returns the st m supplemental disc master with the primary key or throws a {@link NoSuchStMSupplementalDiscMasterException} if it could not be found.
	 *
	 * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master
	 * @throws NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster findByPrimaryKey(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
		throws NoSuchStMSupplementalDiscMasterException {
		return findByPrimaryKey((Serializable)stMSupplementalDiscMasterPK);
	}

	/**
	 * Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StMSupplementalDiscMaster stMSupplementalDiscMaster = (StMSupplementalDiscMaster)serializable;

		if (stMSupplementalDiscMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stMSupplementalDiscMaster = (StMSupplementalDiscMaster)session.get(StMSupplementalDiscMasterImpl.class,
						primaryKey);

				if (stMSupplementalDiscMaster != null) {
					cacheResult(stMSupplementalDiscMaster);
				}
				else {
					entityCache.putResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscMasterImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StMSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
					StMSupplementalDiscMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stMSupplementalDiscMaster;
	}

	/**
	 * Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
	 * @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscMaster fetchByPrimaryKey(
		StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
		return fetchByPrimaryKey((Serializable)stMSupplementalDiscMasterPK);
	}

	@Override
	public Map<Serializable, StMSupplementalDiscMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StMSupplementalDiscMaster> map = new HashMap<Serializable, StMSupplementalDiscMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StMSupplementalDiscMaster stMSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

			if (stMSupplementalDiscMaster != null) {
				map.put(primaryKey, stMSupplementalDiscMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st m supplemental disc masters.
	 *
	 * @return the st m supplemental disc masters
	 */
	@Override
	public List<StMSupplementalDiscMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc masters
	 * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	 * @return the range of st m supplemental disc masters
	 */
	@Override
	public List<StMSupplementalDiscMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc masters
	 * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st m supplemental disc masters
	 */
	@Override
	public List<StMSupplementalDiscMaster> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc masters
	 * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st m supplemental disc masters
	 */
	@Override
	public List<StMSupplementalDiscMaster> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscMaster> orderByComparator,
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

		List<StMSupplementalDiscMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StMSupplementalDiscMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STMSUPPLEMENTALDISCMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STMSUPPLEMENTALDISCMASTER;

				if (pagination) {
					sql = sql.concat(StMSupplementalDiscMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StMSupplementalDiscMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StMSupplementalDiscMaster>)QueryUtil.list(q,
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
	 * Removes all the st m supplemental disc masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StMSupplementalDiscMaster stMSupplementalDiscMaster : findAll()) {
			remove(stMSupplementalDiscMaster);
		}
	}

	/**
	 * Returns the number of st m supplemental disc masters.
	 *
	 * @return the number of st m supplemental disc masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STMSUPPLEMENTALDISCMASTER);

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
		return StMSupplementalDiscMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st m supplemental disc master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StMSupplementalDiscMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STMSUPPLEMENTALDISCMASTER = "SELECT stMSupplementalDiscMaster FROM StMSupplementalDiscMaster stMSupplementalDiscMaster";
	private static final String _SQL_COUNT_STMSUPPLEMENTALDISCMASTER = "SELECT COUNT(stMSupplementalDiscMaster) FROM StMSupplementalDiscMaster stMSupplementalDiscMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stMSupplementalDiscMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMSupplementalDiscMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StMSupplementalDiscMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualDiscountRate2", "actualDiscountRate1", "marketType",
				"actualMethodology", "actualContractPrice", "userId",
				"lastModifiedDate", "projectionDetailsSid", "actualDiscount",
				"sessionId", "checkRecord", "contractEndDate"
			});
}