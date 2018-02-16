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

import com.stpl.app.exception.NoSuchNmPpaProjectionMasterException;
import com.stpl.app.model.NmPpaProjectionMaster;
import com.stpl.app.model.impl.NmPpaProjectionMasterImpl;
import com.stpl.app.model.impl.NmPpaProjectionMasterModelImpl;
import com.stpl.app.service.persistence.NmPpaProjectionMasterPersistence;

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
 * The persistence implementation for the nm ppa projection master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmPpaProjectionMasterPersistence
 * @see com.stpl.app.service.persistence.NmPpaProjectionMasterUtil
 * @generated
 */
@ProviderType
public class NmPpaProjectionMasterPersistenceImpl extends BasePersistenceImpl<NmPpaProjectionMaster>
	implements NmPpaProjectionMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NmPpaProjectionMasterUtil} to access the nm ppa projection master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NmPpaProjectionMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			NmPpaProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED,
			NmPpaProjectionMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NmPpaProjectionMasterPersistenceImpl() {
		setModelClass(NmPpaProjectionMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("userGroup", "USER_GROUP");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
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
	 * Caches the nm ppa projection master in the entity cache if it is enabled.
	 *
	 * @param nmPpaProjectionMaster the nm ppa projection master
	 */
	@Override
	public void cacheResult(NmPpaProjectionMaster nmPpaProjectionMaster) {
		entityCache.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterImpl.class,
			nmPpaProjectionMaster.getPrimaryKey(), nmPpaProjectionMaster);

		nmPpaProjectionMaster.resetOriginalValues();
	}

	/**
	 * Caches the nm ppa projection masters in the entity cache if it is enabled.
	 *
	 * @param nmPpaProjectionMasters the nm ppa projection masters
	 */
	@Override
	public void cacheResult(List<NmPpaProjectionMaster> nmPpaProjectionMasters) {
		for (NmPpaProjectionMaster nmPpaProjectionMaster : nmPpaProjectionMasters) {
			if (entityCache.getResult(
						NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmPpaProjectionMasterImpl.class,
						nmPpaProjectionMaster.getPrimaryKey()) == null) {
				cacheResult(nmPpaProjectionMaster);
			}
			else {
				nmPpaProjectionMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all nm ppa projection masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NmPpaProjectionMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the nm ppa projection master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NmPpaProjectionMaster nmPpaProjectionMaster) {
		entityCache.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterImpl.class,
			nmPpaProjectionMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NmPpaProjectionMaster> nmPpaProjectionMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NmPpaProjectionMaster nmPpaProjectionMaster : nmPpaProjectionMasters) {
			entityCache.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmPpaProjectionMasterImpl.class,
				nmPpaProjectionMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new nm ppa projection master with the primary key. Does not add the nm ppa projection master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new nm ppa projection master
	 * @return the new nm ppa projection master
	 */
	@Override
	public NmPpaProjectionMaster create(int projectionDetailsSid) {
		NmPpaProjectionMaster nmPpaProjectionMaster = new NmPpaProjectionMasterImpl();

		nmPpaProjectionMaster.setNew(true);
		nmPpaProjectionMaster.setPrimaryKey(projectionDetailsSid);

		return nmPpaProjectionMaster;
	}

	/**
	 * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the nm ppa projection master
	 * @return the nm ppa projection master that was removed
	 * @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster remove(int projectionDetailsSid)
		throws NoSuchNmPpaProjectionMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the nm ppa projection master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the nm ppa projection master
	 * @return the nm ppa projection master that was removed
	 * @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster remove(Serializable primaryKey)
		throws NoSuchNmPpaProjectionMasterException {
		Session session = null;

		try {
			session = openSession();

			NmPpaProjectionMaster nmPpaProjectionMaster = (NmPpaProjectionMaster)session.get(NmPpaProjectionMasterImpl.class,
					primaryKey);

			if (nmPpaProjectionMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nmPpaProjectionMaster);
		}
		catch (NoSuchNmPpaProjectionMasterException nsee) {
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
	protected NmPpaProjectionMaster removeImpl(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		nmPpaProjectionMaster = toUnwrappedModel(nmPpaProjectionMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nmPpaProjectionMaster)) {
				nmPpaProjectionMaster = (NmPpaProjectionMaster)session.get(NmPpaProjectionMasterImpl.class,
						nmPpaProjectionMaster.getPrimaryKeyObj());
			}

			if (nmPpaProjectionMaster != null) {
				session.delete(nmPpaProjectionMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nmPpaProjectionMaster != null) {
			clearCache(nmPpaProjectionMaster);
		}

		return nmPpaProjectionMaster;
	}

	@Override
	public NmPpaProjectionMaster updateImpl(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		nmPpaProjectionMaster = toUnwrappedModel(nmPpaProjectionMaster);

		boolean isNew = nmPpaProjectionMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nmPpaProjectionMaster.isNew()) {
				session.save(nmPpaProjectionMaster);

				nmPpaProjectionMaster.setNew(false);
			}
			else {
				nmPpaProjectionMaster = (NmPpaProjectionMaster)session.merge(nmPpaProjectionMaster);
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

		entityCache.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
			NmPpaProjectionMasterImpl.class,
			nmPpaProjectionMaster.getPrimaryKey(), nmPpaProjectionMaster, false);

		nmPpaProjectionMaster.resetOriginalValues();

		return nmPpaProjectionMaster;
	}

	protected NmPpaProjectionMaster toUnwrappedModel(
		NmPpaProjectionMaster nmPpaProjectionMaster) {
		if (nmPpaProjectionMaster instanceof NmPpaProjectionMasterImpl) {
			return nmPpaProjectionMaster;
		}

		NmPpaProjectionMasterImpl nmPpaProjectionMasterImpl = new NmPpaProjectionMasterImpl();

		nmPpaProjectionMasterImpl.setNew(nmPpaProjectionMaster.isNew());
		nmPpaProjectionMasterImpl.setPrimaryKey(nmPpaProjectionMaster.getPrimaryKey());

		nmPpaProjectionMasterImpl.setCheckRecord(nmPpaProjectionMaster.isCheckRecord());
		nmPpaProjectionMasterImpl.setUserGroup(nmPpaProjectionMaster.getUserGroup());
		nmPpaProjectionMasterImpl.setProjectionDetailsSid(nmPpaProjectionMaster.getProjectionDetailsSid());
		nmPpaProjectionMasterImpl.setPriceBasis(nmPpaProjectionMaster.getPriceBasis());
		nmPpaProjectionMasterImpl.setPriceProtectionEndDate(nmPpaProjectionMaster.getPriceProtectionEndDate());
		nmPpaProjectionMasterImpl.setPriceProtectionStartDate(nmPpaProjectionMaster.getPriceProtectionStartDate());
		nmPpaProjectionMasterImpl.setActualPriceCap(nmPpaProjectionMaster.getActualPriceCap());

		return nmPpaProjectionMasterImpl;
	}

	/**
	 * Returns the nm ppa projection master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm ppa projection master
	 * @return the nm ppa projection master
	 * @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNmPpaProjectionMasterException {
		NmPpaProjectionMaster nmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

		if (nmPpaProjectionMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNmPpaProjectionMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nmPpaProjectionMaster;
	}

	/**
	 * Returns the nm ppa projection master with the primary key or throws a {@link NoSuchNmPpaProjectionMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm ppa projection master
	 * @return the nm ppa projection master
	 * @throws NoSuchNmPpaProjectionMasterException if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchNmPpaProjectionMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the nm ppa projection master
	 * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
				NmPpaProjectionMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NmPpaProjectionMaster nmPpaProjectionMaster = (NmPpaProjectionMaster)serializable;

		if (nmPpaProjectionMaster == null) {
			Session session = null;

			try {
				session = openSession();

				nmPpaProjectionMaster = (NmPpaProjectionMaster)session.get(NmPpaProjectionMasterImpl.class,
						primaryKey);

				if (nmPpaProjectionMaster != null) {
					cacheResult(nmPpaProjectionMaster);
				}
				else {
					entityCache.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
						NmPpaProjectionMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmPpaProjectionMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nmPpaProjectionMaster;
	}

	/**
	 * Returns the nm ppa projection master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the nm ppa projection master
	 * @return the nm ppa projection master, or <code>null</code> if a nm ppa projection master with the primary key could not be found
	 */
	@Override
	public NmPpaProjectionMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, NmPpaProjectionMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NmPpaProjectionMaster> map = new HashMap<Serializable, NmPpaProjectionMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NmPpaProjectionMaster nmPpaProjectionMaster = fetchByPrimaryKey(primaryKey);

			if (nmPpaProjectionMaster != null) {
				map.put(primaryKey, nmPpaProjectionMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmPpaProjectionMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NmPpaProjectionMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NMPPAPROJECTIONMASTER_WHERE_PKS_IN);

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

			for (NmPpaProjectionMaster nmPpaProjectionMaster : (List<NmPpaProjectionMaster>)q.list()) {
				map.put(nmPpaProjectionMaster.getPrimaryKeyObj(),
					nmPpaProjectionMaster);

				cacheResult(nmPpaProjectionMaster);

				uncachedPrimaryKeys.remove(nmPpaProjectionMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NmPpaProjectionMasterModelImpl.ENTITY_CACHE_ENABLED,
					NmPpaProjectionMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the nm ppa projection masters.
	 *
	 * @return the nm ppa projection masters
	 */
	@Override
	public List<NmPpaProjectionMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projection masters
	 * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	 * @return the range of nm ppa projection masters
	 */
	@Override
	public List<NmPpaProjectionMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projection masters
	 * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of nm ppa projection masters
	 */
	@Override
	public List<NmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmPpaProjectionMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the nm ppa projection masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NmPpaProjectionMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of nm ppa projection masters
	 * @param end the upper bound of the range of nm ppa projection masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of nm ppa projection masters
	 */
	@Override
	public List<NmPpaProjectionMaster> findAll(int start, int end,
		OrderByComparator<NmPpaProjectionMaster> orderByComparator,
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

		List<NmPpaProjectionMaster> list = null;

		if (retrieveFromCache) {
			list = (List<NmPpaProjectionMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NMPPAPROJECTIONMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NMPPAPROJECTIONMASTER;

				if (pagination) {
					sql = sql.concat(NmPpaProjectionMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NmPpaProjectionMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NmPpaProjectionMaster>)QueryUtil.list(q,
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
	 * Removes all the nm ppa projection masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NmPpaProjectionMaster nmPpaProjectionMaster : findAll()) {
			remove(nmPpaProjectionMaster);
		}
	}

	/**
	 * Returns the number of nm ppa projection masters.
	 *
	 * @return the number of nm ppa projection masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NMPPAPROJECTIONMASTER);

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
		return NmPpaProjectionMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the nm ppa projection master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NmPpaProjectionMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NMPPAPROJECTIONMASTER = "SELECT nmPpaProjectionMaster FROM NmPpaProjectionMaster nmPpaProjectionMaster";
	private static final String _SQL_SELECT_NMPPAPROJECTIONMASTER_WHERE_PKS_IN = "SELECT nmPpaProjectionMaster FROM NmPpaProjectionMaster nmPpaProjectionMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_NMPPAPROJECTIONMASTER = "SELECT COUNT(nmPpaProjectionMaster) FROM NmPpaProjectionMaster nmPpaProjectionMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nmPpaProjectionMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NmPpaProjectionMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NmPpaProjectionMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"checkRecord", "userGroup", "projectionDetailsSid", "priceBasis",
				"priceProtectionEndDate", "priceProtectionStartDate",
				"actualPriceCap"
			});
}