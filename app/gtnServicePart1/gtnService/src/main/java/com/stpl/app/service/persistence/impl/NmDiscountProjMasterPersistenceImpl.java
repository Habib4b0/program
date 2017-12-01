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

import com.stpl.app.exception.NoSuchNmDiscountProjMasterException;
import com.stpl.app.model.NmDiscountProjMaster;
import com.stpl.app.model.impl.NmDiscountProjMasterImpl;
import com.stpl.app.model.impl.NmDiscountProjMasterModelImpl;
import com.stpl.app.service.persistence.NmDiscountProjMasterPersistence;

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
 * The persistence implementation for the nm discount proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjMasterPersistence
 * @see com.stpl.app.service.persistence.NmDiscountProjMasterUtil
 * @generated
 */
@ProviderType
public class NmDiscountProjMasterPersistenceImpl extends BasePersistenceImpl<NmDiscountProjMaster>
	implements NmDiscountProjMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmDiscountProjMasterUtil} to access the nm discount proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmDiscountProjMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			NmDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED,
			NmDiscountProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmDiscountProjMasterPersistenceImpl() {
		setModelClass(NmDiscountProjMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("discountId", "DISCOUNT_ID");
			dbColumnNames.put("userGroup", "USER_GROUP");
			dbColumnNames.put("priceGroupType", "PRICE_GROUP_TYPE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("netFlag", "NET_FLAG");
			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("discountName", "DISCOUNT_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the nm discount proj master in the entity cache if it is enabled.
	 *
	 * @param nmDiscountProjMaster the nm discount proj master
	 */
	@Override
	public void cacheResult(NmDiscountProjMaster nmDiscountProjMaster) {
		entityCache.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterImpl.class,
			nmDiscountProjMaster.getPrimaryKey(), nmDiscountProjMaster);

		nmDiscountProjMaster.resetOriginalValues();
	}

	/**
	 * Caches the nm discount proj masters in the entity cache if it is enabled.
	 *
	 * @param nmDiscountProjMasters the nm discount proj masters
	 */
	@Override
	public void cacheResult(List<NmDiscountProjMaster> nmDiscountProjMasters) {
		for (NmDiscountProjMaster nmDiscountProjMaster : nmDiscountProjMasters) {
			if (entityCache.getResult(
						NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmDiscountProjMasterImpl.class,
						nmDiscountProjMaster.getPrimaryKey()) == null) {
				cacheResult(nmDiscountProjMaster);
			}
			else {
				nmDiscountProjMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm discount proj masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmDiscountProjMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm discount proj master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmDiscountProjMaster nmDiscountProjMaster) {
		entityCache.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterImpl.class, nmDiscountProjMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmDiscountProjMaster> nmDiscountProjMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmDiscountProjMaster nmDiscountProjMaster : nmDiscountProjMasters) {
			entityCache.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmDiscountProjMasterImpl.class,
				nmDiscountProjMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm discount proj master with the primary key. Does not add the nm discount proj master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new nm discount proj master
	 * @return the new nm discount proj master
	 */
	@Override
	public NmDiscountProjMaster create(int projectionDetailsSid) {
		NmDiscountProjMaster nmDiscountProjMaster = new NmDiscountProjMasterImpl();

		nmDiscountProjMaster.setNew(true);
		nmDiscountProjMaster.setPrimaryKey(projectionDetailsSid);

		return nmDiscountProjMaster;
	}

	/**
	 * Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the nm discount proj master
	 * @return the nm discount proj master that was removed
	 * @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster remove(int projectionDetailsSid)
		throws NoSuchNmDiscountProjMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the nm discount proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm discount proj master
	 * @return the nm discount proj master that was removed
	 * @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster remove(Serializable primaryKey)
		throws NoSuchNmDiscountProjMasterException {
		Session session = null;

		try {
			session = openSession();

			NmDiscountProjMaster nmDiscountProjMaster = (NmDiscountProjMaster)session.get(NmDiscountProjMasterImpl.class,
					primaryKey);

			if (nmDiscountProjMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmDiscountProjMaster);
		}
		catch (NoSuchNmDiscountProjMasterException nsee) {
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
	protected NmDiscountProjMaster removeImpl(
		NmDiscountProjMaster nmDiscountProjMaster) {
		nmDiscountProjMaster = toUnwrappedModel(nmDiscountProjMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmDiscountProjMaster)) {
				nmDiscountProjMaster = (NmDiscountProjMaster)session.get(NmDiscountProjMasterImpl.class,
						nmDiscountProjMaster.getPrimaryKeyObj());
			}

			if (nmDiscountProjMaster != null) {
				session.delete(nmDiscountProjMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmDiscountProjMaster != null) {
			clearCache(nmDiscountProjMaster);
		}

		return nmDiscountProjMaster;
	}

	@Override
	public NmDiscountProjMaster updateImpl(
		NmDiscountProjMaster nmDiscountProjMaster) {
		nmDiscountProjMaster = toUnwrappedModel(nmDiscountProjMaster);

		boolean isNew = nmDiscountProjMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmDiscountProjMaster.isNew()) {
				session.save(nmDiscountProjMaster);

				nmDiscountProjMaster.setNew(false);
			}
			else {
				nmDiscountProjMaster = (NmDiscountProjMaster)session.merge(nmDiscountProjMaster);
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

		entityCache.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmDiscountProjMasterImpl.class,
			nmDiscountProjMaster.getPrimaryKey(), nmDiscountProjMaster, false);

		nmDiscountProjMaster.resetOriginalValues();

		return nmDiscountProjMaster;
	}

	protected NmDiscountProjMaster toUnwrappedModel(
		NmDiscountProjMaster nmDiscountProjMaster) {
		if (nmDiscountProjMaster instanceof NmDiscountProjMasterImpl) {
			return nmDiscountProjMaster;
		}

		NmDiscountProjMasterImpl nmDiscountProjMasterImpl = new NmDiscountProjMasterImpl();

		nmDiscountProjMasterImpl.setNew(nmDiscountProjMaster.isNew());
		nmDiscountProjMasterImpl.setPrimaryKey(nmDiscountProjMaster.getPrimaryKey());

		nmDiscountProjMasterImpl.setCheckRecord(nmDiscountProjMaster.isCheckRecord());
		nmDiscountProjMasterImpl.setDiscountId(nmDiscountProjMaster.getDiscountId());
		nmDiscountProjMasterImpl.setUserGroup(nmDiscountProjMaster.getUserGroup());
		nmDiscountProjMasterImpl.setPriceGroupType(nmDiscountProjMaster.getPriceGroupType());
		nmDiscountProjMasterImpl.setProjectionDetailsSid(nmDiscountProjMaster.getProjectionDetailsSid());
		nmDiscountProjMasterImpl.setNetFlag(nmDiscountProjMaster.getNetFlag());
		nmDiscountProjMasterImpl.setMethodology(nmDiscountProjMaster.getMethodology());
		nmDiscountProjMasterImpl.setDiscountName(nmDiscountProjMaster.getDiscountName());

		return nmDiscountProjMasterImpl;
	}

	/**
	 * Returns the nm discount proj master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm discount proj master
	 * @return the nm discount proj master
	 * @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmDiscountProjMasterException {
		NmDiscountProjMaster nmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

		if (nmDiscountProjMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmDiscountProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmDiscountProjMaster;
	}

	/**
	 * Returns the nm discount proj master with the primary key or throws a {@link NoSuchNmDiscountProjMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm discount proj master
	 * @return the nm discount proj master
	 * @throws NoSuchNmDiscountProjMasterException if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchNmDiscountProjMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm discount proj master
	 * @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmDiscountProjMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmDiscountProjMaster nmDiscountProjMaster = (NmDiscountProjMaster)serializable;

		if (nmDiscountProjMaster == null) {
			Session session = null;

			try {
				session = openSession();

				nmDiscountProjMaster = (NmDiscountProjMaster)session.get(NmDiscountProjMasterImpl.class,
						primaryKey);

				if (nmDiscountProjMaster != null) {
					cacheResult(nmDiscountProjMaster);
				}
				else {
					entityCache.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmDiscountProjMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmDiscountProjMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmDiscountProjMaster;
	}

	/**
	 * Returns the nm discount proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm discount proj master
	 * @return the nm discount proj master, or <code>null</code> if a nm discount proj master with the primary key could not be found
	 */
	@Override
	public NmDiscountProjMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, NmDiscountProjMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmDiscountProjMaster> map = new HashMap<Serializable, NmDiscountProjMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NmDiscountProjMaster nmDiscountProjMaster = fetchByPrimaryKey(primaryKey);

			if (nmDiscountProjMaster != null) {
				map.put(primaryKey, nmDiscountProjMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmDiscountProjMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NmDiscountProjMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NMDISCOUNTPROJMASTER_WHERE_PKS_IN);

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

			for (NmDiscountProjMaster nmDiscountProjMaster : (List<NmDiscountProjMaster>)q.list()) {
				map.put(nmDiscountProjMaster.getPrimaryKeyObj(),
					nmDiscountProjMaster);

				cacheResult(nmDiscountProjMaster);

				uncachedPrimaryKeys.remove(nmDiscountProjMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NmDiscountProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmDiscountProjMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the nm discount proj masters.
	 *
	 * @return the nm discount proj masters
	 */
	@Override
	public List<NmDiscountProjMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount proj masters
	 * @param end the upper bound of the range of nm discount proj masters (not inclusive)
	 * @return the range of nm discount proj masters
	 */
	@Override
	public List<NmDiscountProjMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount proj masters
	 * @param end the upper bound of the range of nm discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm discount proj masters
	 */
	@Override
	public List<NmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<NmDiscountProjMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm discount proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmDiscountProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm discount proj masters
	 * @param end the upper bound of the range of nm discount proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm discount proj masters
	 */
	@Override
	public List<NmDiscountProjMaster> findAll(int start, int end,
		OrderByComparator<NmDiscountProjMaster> orderByComparator,
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

		List<NmDiscountProjMaster> list = null;

		if (retrieveFromCache) {
			list = (List<NmDiscountProjMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMDISCOUNTPROJMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMDISCOUNTPROJMASTER;

				if (pagination) {
					sql = sql.concat(NmDiscountProjMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmDiscountProjMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmDiscountProjMaster>)QueryUtil.list(q,
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
	 * Removes all the nm discount proj masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmDiscountProjMaster nmDiscountProjMaster : findAll()) {
			remove(nmDiscountProjMaster);
		}
	}

	/**
	 * Returns the number of nm discount proj masters.
	 *
	 * @return the number of nm discount proj masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMDISCOUNTPROJMASTER);

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
		return NmDiscountProjMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm discount proj master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmDiscountProjMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMDISCOUNTPROJMASTER = "SELECT nmDiscountProjMaster FROM NmDiscountProjMaster nmDiscountProjMaster";
	private static final String _SQL_SELECT_NMDISCOUNTPROJMASTER_WHERE_PKS_IN = "SELECT nmDiscountProjMaster FROM NmDiscountProjMaster nmDiscountProjMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_NMDISCOUNTPROJMASTER = "SELECT COUNT(nmDiscountProjMaster) FROM NmDiscountProjMaster nmDiscountProjMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmDiscountProjMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmDiscountProjMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmDiscountProjMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "discountId", "userGroup", "priceGroupType",
				"projectionDetailsSid", "netFlag", "methodology", "discountName"
			});
}