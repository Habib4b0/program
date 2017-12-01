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

import com.stpl.app.exception.NoSuchChDiscountProjMasterException;
import com.stpl.app.model.ChDiscountProjMaster;
import com.stpl.app.model.impl.ChDiscountProjMasterImpl;
import com.stpl.app.model.impl.ChDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.ChDiscountProjMasterPK;
import com.stpl.app.service.persistence.ChDiscountProjMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the ch discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.ChDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public class ChDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<ChDiscountProjMaster>
	implements ChDiscountProjMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChDiscountProjMasterUtil} to access the ch discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChDiscountProjMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			ChDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			ChDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChDiscountProjMasterPersistenceImpl() {
		setModelClass(ChDiscountProjMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("selectedPeriods", "SELECTED_PERIODS");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("priceGroupType", "PRICE_GROUP_TYPE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("baselinePeriods", "BASELINE_PERIODS");
			dbColumnNames.put("netFlag", "NET_FLAG");
			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("discountType", "DISCOUNT_TYPE");
			dbColumnNames.put("projectedType", "PROJECTED_TYPE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ch discount proj master in the entity cache if it is enabled.
	 *
	 * @param chDiscountProjMaster the ch discount proj master
	 */
	@Override
	public void cacheResult(ChDiscountProjMaster chDiscountProjMaster) {
		entityCache.putResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterImpl.class,
			chDiscountProjMaster.getPrimaryKey(), chDiscountProjMaster);

		chDiscountProjMaster.resetOriginalValues();
	}

	/**
	 * Caches the ch discount proj masters in the entity cache if it is enabled.
	 *
	 * @param chDiscountProjMasters the ch discount proj masters
	 */
	@Override
	public void cacheResult(List<ChDiscountProjMaster> chDiscountProjMasters) {
		for (ChDiscountProjMaster chDiscountProjMaster : chDiscountProjMasters) {
			if (entityCache.getResult(
						ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						ChDiscountProjMasterImpl.class,
						chDiscountProjMaster.getPrimaryKey()) == null) {
				cacheResult(chDiscountProjMaster);
			}
			else {
				chDiscountProjMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch discount proj masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChDiscountProjMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch discount proj master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChDiscountProjMaster chDiscountProjMaster) {
		entityCache.removeResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterImpl.class, chDiscountProjMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChDiscountProjMaster> chDiscountProjMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChDiscountProjMaster chDiscountProjMaster : chDiscountProjMasters) {
			entityCache.removeResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				ChDiscountProjMasterImpl.class,
				chDiscountProjMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch discount proj master with the primary key. Does not add the ch discount proj master to the database.
	 *
	 * @param chDiscountProjMasterPK the primary key for the new ch discount proj master
	 * @return the new ch discount proj master
	 */
	@Override
	public ChDiscountProjMaster create(
		ChDiscountProjMasterPK chDiscountProjMasterPK) {
		ChDiscountProjMaster chDiscountProjMaster = new ChDiscountProjMasterImpl();

		chDiscountProjMaster.setNew(true);
		chDiscountProjMaster.setPrimaryKey(chDiscountProjMasterPK);

		return chDiscountProjMaster;
	}

	/**
	 * Removes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chDiscountProjMasterPK the primary key of the ch discount proj master
	 * @return the ch discount proj master that was removed
	 * @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster remove(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws NoSuchChDiscountProjMasterException {
		return remove((Serializable)chDiscountProjMasterPK);
	}

	/**
	 * Removes the ch discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch discount proj master
	 * @return the ch discount proj master that was removed
	 * @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster remove(Serializable primaryKey)
		throws NoSuchChDiscountProjMasterException {
		Session session = null;

		try {
			session = openSession();

			ChDiscountProjMaster chDiscountProjMaster = (ChDiscountProjMaster)session.get(ChDiscountProjMasterImpl.class,
					primaryKey);

			if (chDiscountProjMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chDiscountProjMaster);
		}
		catch (NoSuchChDiscountProjMasterException nsee) {
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
	protected ChDiscountProjMaster removeImpl(
		ChDiscountProjMaster chDiscountProjMaster) {
		chDiscountProjMaster = toUnwrappedModel(chDiscountProjMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chDiscountProjMaster)) {
				chDiscountProjMaster = (ChDiscountProjMaster)session.get(ChDiscountProjMasterImpl.class,
						chDiscountProjMaster.getPrimaryKeyObj());
			}

			if (chDiscountProjMaster != null) {
				session.delete(chDiscountProjMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chDiscountProjMaster != null) {
			clearCache(chDiscountProjMaster);
		}

		return chDiscountProjMaster;
	}

	@Override
	public ChDiscountProjMaster updateImpl(
		ChDiscountProjMaster chDiscountProjMaster) {
		chDiscountProjMaster = toUnwrappedModel(chDiscountProjMaster);

		boolean isNew = chDiscountProjMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chDiscountProjMaster.isNew()) {
				session.save(chDiscountProjMaster);

				chDiscountProjMaster.setNew(false);
			}
			else {
				chDiscountProjMaster = (ChDiscountProjMaster)session.merge(chDiscountProjMaster);
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

		entityCache.putResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			ChDiscountProjMasterImpl.class,
			chDiscountProjMaster.getPrimaryKey(), chDiscountProjMaster, false);

		chDiscountProjMaster.resetOriginalValues();

		return chDiscountProjMaster;
	}

	protected ChDiscountProjMaster toUnwrappedModel(
		ChDiscountProjMaster chDiscountProjMaster) {
		if (chDiscountProjMaster instanceof ChDiscountProjMasterImpl) {
			return chDiscountProjMaster;
		}

		ChDiscountProjMasterImpl chDiscountProjMasterImpl = new ChDiscountProjMasterImpl();

		chDiscountProjMasterImpl.setNew(chDiscountProjMaster.isNew());
		chDiscountProjMasterImpl.setPrimaryKey(chDiscountProjMaster.getPrimaryKey());

		chDiscountProjMasterImpl.setSelectedPeriods(chDiscountProjMaster.getSelectedPeriods());
		chDiscountProjMasterImpl.setCheckRecord(chDiscountProjMaster.isCheckRecord());
		chDiscountProjMasterImpl.setPriceGroupType(chDiscountProjMaster.getPriceGroupType());
		chDiscountProjMasterImpl.setProjectionDetailsSid(chDiscountProjMaster.getProjectionDetailsSid());
		chDiscountProjMasterImpl.setBaselinePeriods(chDiscountProjMaster.getBaselinePeriods());
		chDiscountProjMasterImpl.setNetFlag(chDiscountProjMaster.getNetFlag());
		chDiscountProjMasterImpl.setMethodology(chDiscountProjMaster.getMethodology());
		chDiscountProjMasterImpl.setRsModelSid(chDiscountProjMaster.getRsModelSid());
		chDiscountProjMasterImpl.setDiscountType(chDiscountProjMaster.getDiscountType());
		chDiscountProjMasterImpl.setProjectedType(chDiscountProjMaster.getProjectedType());

		return chDiscountProjMasterImpl;
	}

	/**
	 * Returns the ch discount proj master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch discount proj master
	 * @return the ch discount proj master
	 * @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChDiscountProjMasterException {
		ChDiscountProjMaster chDiscountProjMaster = fetchByPrimaryKey(primaryKey);

		if (chDiscountProjMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chDiscountProjMaster;
	}

	/**
	 * Returns the ch discount proj master with the primary key or throws a {@link NoSuchChDiscountProjMasterException} if it could not be found.
	 *
	 * @param chDiscountProjMasterPK the primary key of the ch discount proj master
	 * @return the ch discount proj master
	 * @throws NoSuchChDiscountProjMasterException if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster findByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK)
		throws NoSuchChDiscountProjMasterException {
		return findByPrimaryKey((Serializable)chDiscountProjMasterPK);
	}

	/**
	 * Returns the ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch discount proj master
	 * @return the ch discount proj master, or <code>null</code> if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				ChDiscountProjMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChDiscountProjMaster chDiscountProjMaster = (ChDiscountProjMaster)serializable;

		if (chDiscountProjMaster == null) {
			Session session = null;

			try {
				session = openSession();

				chDiscountProjMaster = (ChDiscountProjMaster)session.get(ChDiscountProjMasterImpl.class,
						primaryKey);

				if (chDiscountProjMaster != null) {
					cacheResult(chDiscountProjMaster);
				}
				else {
					entityCache.putResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						ChDiscountProjMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					ChDiscountProjMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chDiscountProjMaster;
	}

	/**
	 * Returns the ch discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chDiscountProjMasterPK the primary key of the ch discount proj master
	 * @return the ch discount proj master, or <code>null</code> if a ch discount proj master with the primary key could not be found
	 */
	@Override
	public ChDiscountProjMaster fetchByPrimaryKey(
		ChDiscountProjMasterPK chDiscountProjMasterPK) {
		return fetchByPrimaryKey((Serializable)chDiscountProjMasterPK);
	}

	@Override
	public Map<Serializable, ChDiscountProjMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChDiscountProjMaster> map = new HashMap<Serializable, ChDiscountProjMaster>();

		for (Serializable primaryKey : primaryKeys) {
			ChDiscountProjMaster chDiscountProjMaster = fetchByPrimaryKey(primaryKey);

			if (chDiscountProjMaster != null) {
				map.put(primaryKey, chDiscountProjMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the ch discount proj masters.
	 *
	 * @return the ch discount proj masters
	 */
	@Override
	public List<ChDiscountProjMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch discount proj masters
	 * @param end the upper bound of the range of ch discount proj masters (not inclusive)
	 * @return the range of ch discount proj masters
	 */
	@Override
	public List<ChDiscountProjMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch discount proj masters
	 * @param end the upper bound of the range of ch discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch discount proj masters
	 */
	@Override
	public List<ChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<ChDiscountProjMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch discount proj masters
	 * @param end the upper bound of the range of ch discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch discount proj masters
	 */
	@Override
	public List<ChDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<ChDiscountProjMaster> orderByComparator,
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

		List<ChDiscountProjMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ChDiscountProjMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHDISCOUNTPROJMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHDISCOUNTPROJMASTER;

				if (pagination) {
					sql = sql.concat(ChDiscountProjMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChDiscountProjMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChDiscountProjMaster>)QueryUtil.list(q,
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
	 * Removes all the ch discount proj masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChDiscountProjMaster chDiscountProjMaster : findAll()) {
			remove(chDiscountProjMaster);
		}
	}

	/**
	 * Returns the number of ch discount proj masters.
	 *
	 * @return the number of ch discount proj masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHDISCOUNTPROJMASTER);

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
		return ChDiscountProjMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch discount proj master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChDiscountProjMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHDISCOUNTPROJMASTER = "SELECT chDiscountProjMaster FROM ChDiscountProjMaster chDiscountProjMaster";
	private static final String _SQL_COUNT_CHDISCOUNTPROJMASTER = "SELECT COUNT(chDiscountProjMaster) FROM ChDiscountProjMaster chDiscountProjMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chDiscountProjMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChDiscountProjMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChDiscountProjMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"selectedPeriods", "checkRecord", "priceGroupType",
				"projectionDetailsSid", "baselinePeriods", "netFlag",
				"methodology", "rsModelSid", "discountType", "projectedType"
			});
}