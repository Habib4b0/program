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

import com.stpl.app.exception.NoSuchNmSalesProjectionMasterException;
import com.stpl.app.model.NmSalesProjectionMaster;
import com.stpl.app.model.impl.NmSalesProjectionMasterImpl;
import com.stpl.app.model.impl.NmSalesProjectionMasterModelImpl;
import com.stpl.app.service.persistence.NmSalesProjectionMasterPersistence;

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
 * The persistence implementation for the nm sales projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.NmSalesProjectionMasterUtil
 * @generated
 */
@ProviderType
public class NmSalesProjectionMasterPersistenceImpl extends BasePersistenceImpl<NmSalesProjectionMaster>
	implements NmSalesProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmSalesProjectionMasterUtil} to access the nm sales projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmSalesProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			NmSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			NmSalesProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmSalesProjectionMasterPersistenceImpl() {
		setModelClass(NmSalesProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("calculationPeriods", "CALCULATION_PERIODS");
			dbColumnNames.put("userGroup", "USER_GROUP");
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
	 * Caches the nm sales projection master in the entity cache if it is enabled.
	 *
	 * @param nmSalesProjectionMaster the nm sales projection master
	 */
	@Override
	public void cacheResult(NmSalesProjectionMaster nmSalesProjectionMaster) {
		entityCache.putResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterImpl.class,
			nmSalesProjectionMaster.getPrimaryKey(), nmSalesProjectionMaster);

		nmSalesProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the nm sales projection masters in the entity cache if it is enabled.
	 *
	 * @param nmSalesProjectionMasters the nm sales projection masters
	 */
	@Override
	public void cacheResult(
		List<NmSalesProjectionMaster> nmSalesProjectionMasters) {
		for (NmSalesProjectionMaster nmSalesProjectionMaster : nmSalesProjectionMasters) {
			if (entityCache.getResult(
						NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmSalesProjectionMasterImpl.class,
						nmSalesProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(nmSalesProjectionMaster);
			}
			else {
				nmSalesProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm sales projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmSalesProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm sales projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmSalesProjectionMaster nmSalesProjectionMaster) {
		entityCache.removeResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterImpl.class,
			nmSalesProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<NmSalesProjectionMaster> nmSalesProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmSalesProjectionMaster nmSalesProjectionMaster : nmSalesProjectionMasters) {
			entityCache.removeResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmSalesProjectionMasterImpl.class,
				nmSalesProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm sales projection master with the primary key. Does not add the nm sales projection master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new nm sales projection master
	 * @return the new nm sales projection master
	 */
	@Override
	public NmSalesProjectionMaster create(int projectionDetailsSid) {
		NmSalesProjectionMaster nmSalesProjectionMaster = new NmSalesProjectionMasterImpl();

		nmSalesProjectionMaster.setNew(true);
		nmSalesProjectionMaster.setPrimaryKey(projectionDetailsSid);

		return nmSalesProjectionMaster;
	}

	/**
	 * Removes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the nm sales projection master
	 * @return the nm sales projection master that was removed
	 * @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster remove(int projectionDetailsSid)
		throws NoSuchNmSalesProjectionMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the nm sales projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm sales projection master
	 * @return the nm sales projection master that was removed
	 * @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster remove(Serializable primaryKey)
		throws NoSuchNmSalesProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			NmSalesProjectionMaster nmSalesProjectionMaster = (NmSalesProjectionMaster)session.get(NmSalesProjectionMasterImpl.class,
					primaryKey);

			if (nmSalesProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmSalesProjectionMaster);
		}
		catch (NoSuchNmSalesProjectionMasterException nsee) {
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
	protected NmSalesProjectionMaster removeImpl(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		nmSalesProjectionMaster = toUnwrappedModel(nmSalesProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmSalesProjectionMaster)) {
				nmSalesProjectionMaster = (NmSalesProjectionMaster)session.get(NmSalesProjectionMasterImpl.class,
						nmSalesProjectionMaster.getPrimaryKeyObj());
			}

			if (nmSalesProjectionMaster != null) {
				session.delete(nmSalesProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmSalesProjectionMaster != null) {
			clearCache(nmSalesProjectionMaster);
		}

		return nmSalesProjectionMaster;
	}

	@Override
	public NmSalesProjectionMaster updateImpl(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		nmSalesProjectionMaster = toUnwrappedModel(nmSalesProjectionMaster);

		boolean isNew = nmSalesProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmSalesProjectionMaster.isNew()) {
				session.save(nmSalesProjectionMaster);

				nmSalesProjectionMaster.setNew(false);
			}
			else {
				nmSalesProjectionMaster = (NmSalesProjectionMaster)session.merge(nmSalesProjectionMaster);
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

		entityCache.putResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmSalesProjectionMasterImpl.class,
			nmSalesProjectionMaster.getPrimaryKey(), nmSalesProjectionMaster,
			false);

		nmSalesProjectionMaster.resetOriginalValues();

		return nmSalesProjectionMaster;
	}

	protected NmSalesProjectionMaster toUnwrappedModel(
		NmSalesProjectionMaster nmSalesProjectionMaster) {
		if (nmSalesProjectionMaster instanceof NmSalesProjectionMasterImpl) {
			return nmSalesProjectionMaster;
		}

		NmSalesProjectionMasterImpl nmSalesProjectionMasterImpl = new NmSalesProjectionMasterImpl();

		nmSalesProjectionMasterImpl.setNew(nmSalesProjectionMaster.isNew());
		nmSalesProjectionMasterImpl.setPrimaryKey(nmSalesProjectionMaster.getPrimaryKey());

		nmSalesProjectionMasterImpl.setCheckRecord(nmSalesProjectionMaster.isCheckRecord());
		nmSalesProjectionMasterImpl.setCalculationPeriods(nmSalesProjectionMaster.getCalculationPeriods());
		nmSalesProjectionMasterImpl.setUserGroup(nmSalesProjectionMaster.getUserGroup());
		nmSalesProjectionMasterImpl.setProjectionDetailsSid(nmSalesProjectionMaster.getProjectionDetailsSid());
		nmSalesProjectionMasterImpl.setMethodology(nmSalesProjectionMaster.getMethodology());

		return nmSalesProjectionMasterImpl;
	}

	/**
	 * Returns the nm sales projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm sales projection master
	 * @return the nm sales projection master
	 * @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmSalesProjectionMasterException {
		NmSalesProjectionMaster nmSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (nmSalesProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmSalesProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmSalesProjectionMaster;
	}

	/**
	 * Returns the nm sales projection master with the primary key or throws a {@link NoSuchNmSalesProjectionMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm sales projection master
	 * @return the nm sales projection master
	 * @throws NoSuchNmSalesProjectionMasterException if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchNmSalesProjectionMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the nm sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm sales projection master
	 * @return the nm sales projection master, or <code>null</code> if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmSalesProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmSalesProjectionMaster nmSalesProjectionMaster = (NmSalesProjectionMaster)serializable;

		if (nmSalesProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				nmSalesProjectionMaster = (NmSalesProjectionMaster)session.get(NmSalesProjectionMasterImpl.class,
						primaryKey);

				if (nmSalesProjectionMaster != null) {
					cacheResult(nmSalesProjectionMaster);
				}
				else {
					entityCache.putResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmSalesProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmSalesProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmSalesProjectionMaster;
	}

	/**
	 * Returns the nm sales projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm sales projection master
	 * @return the nm sales projection master, or <code>null</code> if a nm sales projection master with the primary key could not be found
	 */
	@Override
	public NmSalesProjectionMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, NmSalesProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmSalesProjectionMaster> map = new HashMap<Serializable, NmSalesProjectionMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NmSalesProjectionMaster nmSalesProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (nmSalesProjectionMaster != null) {
				map.put(primaryKey, nmSalesProjectionMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmSalesProjectionMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NmSalesProjectionMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NMSALESPROJECTIONMASTER_WHERE_PKS_IN);

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

			for (NmSalesProjectionMaster nmSalesProjectionMaster : (List<NmSalesProjectionMaster>)q.list()) {
				map.put(nmSalesProjectionMaster.getPrimaryKeyObj(),
					nmSalesProjectionMaster);

				cacheResult(nmSalesProjectionMaster);

				uncachedPrimaryKeys.remove(nmSalesProjectionMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NmSalesProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmSalesProjectionMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the nm sales projection masters.
	 *
	 * @return the nm sales projection masters
	 */
	@Override
	public List<NmSalesProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projection masters
	 * @param end the upper bound of the range of nm sales projection masters (not inclusive)
	 * @return the range of nm sales projection masters
	 */
	@Override
	public List<NmSalesProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projection masters
	 * @param end the upper bound of the range of nm sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm sales projection masters
	 */
	@Override
	public List<NmSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmSalesProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm sales projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmSalesProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm sales projection masters
	 * @param end the upper bound of the range of nm sales projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm sales projection masters
	 */
	@Override
	public List<NmSalesProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmSalesProjectionMaster> orderByComparator,
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

		List<NmSalesProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<NmSalesProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMSALESPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMSALESPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(NmSalesProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmSalesProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmSalesProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the nm sales projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmSalesProjectionMaster nmSalesProjectionMaster : findAll()) {
			remove(nmSalesProjectionMaster);
		}
	}

	/**
	 * Returns the number of nm sales projection masters.
	 *
	 * @return the number of nm sales projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMSALESPROJECTIONMASTER);

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
		return NmSalesProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm sales projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmSalesProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMSALESPROJECTIONMASTER = "SELECT nmSalesProjectionMaster FROM NmSalesProjectionMaster nmSalesProjectionMaster";
	private static final String _SQL_SELECT_NMSALESPROJECTIONMASTER_WHERE_PKS_IN =
		"SELECT nmSalesProjectionMaster FROM NmSalesProjectionMaster nmSalesProjectionMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_NMSALESPROJECTIONMASTER = "SELECT COUNT(nmSalesProjectionMaster) FROM NmSalesProjectionMaster nmSalesProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmSalesProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmSalesProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmSalesProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "calculationPeriods", "userGroup",
				"projectionDetailsSid", "methodology"
			});
}