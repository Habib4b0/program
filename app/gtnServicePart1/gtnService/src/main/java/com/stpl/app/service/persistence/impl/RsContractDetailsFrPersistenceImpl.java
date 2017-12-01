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

import com.stpl.app.exception.NoSuchRsContractDetailsFrException;
import com.stpl.app.model.RsContractDetailsFr;
import com.stpl.app.model.impl.RsContractDetailsFrImpl;
import com.stpl.app.model.impl.RsContractDetailsFrModelImpl;
import com.stpl.app.service.persistence.RsContractDetailsFrPersistence;

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
 * The persistence implementation for the rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractDetailsFrPersistence
 * @see com.stpl.app.service.persistence.RsContractDetailsFrUtil
 * @generated
 */
@ProviderType
public class RsContractDetailsFrPersistenceImpl extends BasePersistenceImpl<RsContractDetailsFr>
	implements RsContractDetailsFrPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RsContractDetailsFrUtil} to access the rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RsContractDetailsFrImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			RsContractDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RsContractDetailsFrPersistenceImpl() {
		setModelClass(RsContractDetailsFr.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("rsContractDetailsFrSid",
				"RS_CONTRACT_DETAILS_FR_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsContractDetailsSid", "RS_CONTRACT_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rs contract details fr in the entity cache if it is enabled.
	 *
	 * @param rsContractDetailsFr the rs contract details fr
	 */
	@Override
	public void cacheResult(RsContractDetailsFr rsContractDetailsFr) {
		entityCache.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey(),
			rsContractDetailsFr);

		rsContractDetailsFr.resetOriginalValues();
	}

	/**
	 * Caches the rs contract details frs in the entity cache if it is enabled.
	 *
	 * @param rsContractDetailsFrs the rs contract details frs
	 */
	@Override
	public void cacheResult(List<RsContractDetailsFr> rsContractDetailsFrs) {
		for (RsContractDetailsFr rsContractDetailsFr : rsContractDetailsFrs) {
			if (entityCache.getResult(
						RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						RsContractDetailsFrImpl.class,
						rsContractDetailsFr.getPrimaryKey()) == null) {
				cacheResult(rsContractDetailsFr);
			}
			else {
				rsContractDetailsFr.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rs contract details frs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RsContractDetailsFrImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rs contract details fr.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RsContractDetailsFr rsContractDetailsFr) {
		entityCache.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RsContractDetailsFr> rsContractDetailsFrs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RsContractDetailsFr rsContractDetailsFr : rsContractDetailsFrs) {
			entityCache.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				RsContractDetailsFrImpl.class,
				rsContractDetailsFr.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rs contract details fr with the primary key. Does not add the rs contract details fr to the database.
	 *
	 * @param rsContractDetailsFrSid the primary key for the new rs contract details fr
	 * @return the new rs contract details fr
	 */
	@Override
	public RsContractDetailsFr create(int rsContractDetailsFrSid) {
		RsContractDetailsFr rsContractDetailsFr = new RsContractDetailsFrImpl();

		rsContractDetailsFr.setNew(true);
		rsContractDetailsFr.setPrimaryKey(rsContractDetailsFrSid);

		return rsContractDetailsFr;
	}

	/**
	 * Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rsContractDetailsFrSid the primary key of the rs contract details fr
	 * @return the rs contract details fr that was removed
	 * @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr remove(int rsContractDetailsFrSid)
		throws NoSuchRsContractDetailsFrException {
		return remove((Serializable)rsContractDetailsFrSid);
	}

	/**
	 * Removes the rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rs contract details fr
	 * @return the rs contract details fr that was removed
	 * @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr remove(Serializable primaryKey)
		throws NoSuchRsContractDetailsFrException {
		Session session = null;

		try {
			session = openSession();

			RsContractDetailsFr rsContractDetailsFr = (RsContractDetailsFr)session.get(RsContractDetailsFrImpl.class,
					primaryKey);

			if (rsContractDetailsFr == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rsContractDetailsFr);
		}
		catch (NoSuchRsContractDetailsFrException nsee) {
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
	protected RsContractDetailsFr removeImpl(
		RsContractDetailsFr rsContractDetailsFr) {
		rsContractDetailsFr = toUnwrappedModel(rsContractDetailsFr);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rsContractDetailsFr)) {
				rsContractDetailsFr = (RsContractDetailsFr)session.get(RsContractDetailsFrImpl.class,
						rsContractDetailsFr.getPrimaryKeyObj());
			}

			if (rsContractDetailsFr != null) {
				session.delete(rsContractDetailsFr);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rsContractDetailsFr != null) {
			clearCache(rsContractDetailsFr);
		}

		return rsContractDetailsFr;
	}

	@Override
	public RsContractDetailsFr updateImpl(
		RsContractDetailsFr rsContractDetailsFr) {
		rsContractDetailsFr = toUnwrappedModel(rsContractDetailsFr);

		boolean isNew = rsContractDetailsFr.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rsContractDetailsFr.isNew()) {
				session.save(rsContractDetailsFr);

				rsContractDetailsFr.setNew(false);
			}
			else {
				rsContractDetailsFr = (RsContractDetailsFr)session.merge(rsContractDetailsFr);
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

		entityCache.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsContractDetailsFrImpl.class, rsContractDetailsFr.getPrimaryKey(),
			rsContractDetailsFr, false);

		rsContractDetailsFr.resetOriginalValues();

		return rsContractDetailsFr;
	}

	protected RsContractDetailsFr toUnwrappedModel(
		RsContractDetailsFr rsContractDetailsFr) {
		if (rsContractDetailsFr instanceof RsContractDetailsFrImpl) {
			return rsContractDetailsFr;
		}

		RsContractDetailsFrImpl rsContractDetailsFrImpl = new RsContractDetailsFrImpl();

		rsContractDetailsFrImpl.setNew(rsContractDetailsFr.isNew());
		rsContractDetailsFrImpl.setPrimaryKey(rsContractDetailsFr.getPrimaryKey());

		rsContractDetailsFrImpl.setRecordLockStatus(rsContractDetailsFr.isRecordLockStatus());
		rsContractDetailsFrImpl.setCreatedDate(rsContractDetailsFr.getCreatedDate());
		rsContractDetailsFrImpl.setCreatedBy(rsContractDetailsFr.getCreatedBy());
		rsContractDetailsFrImpl.setSource(rsContractDetailsFr.getSource());
		rsContractDetailsFrImpl.setFormulaMethodId(rsContractDetailsFr.getFormulaMethodId());
		rsContractDetailsFrImpl.setItemMasterSid(rsContractDetailsFr.getItemMasterSid());
		rsContractDetailsFrImpl.setBatchId(rsContractDetailsFr.getBatchId());
		rsContractDetailsFrImpl.setRsContractDetailsFrSid(rsContractDetailsFr.getRsContractDetailsFrSid());
		rsContractDetailsFrImpl.setModifiedBy(rsContractDetailsFr.getModifiedBy());
		rsContractDetailsFrImpl.setInboundStatus(rsContractDetailsFr.getInboundStatus());
		rsContractDetailsFrImpl.setFormulaId(rsContractDetailsFr.getFormulaId());
		rsContractDetailsFrImpl.setModifiedDate(rsContractDetailsFr.getModifiedDate());
		rsContractDetailsFrImpl.setRsContractDetailsSid(rsContractDetailsFr.getRsContractDetailsSid());

		return rsContractDetailsFrImpl;
	}

	/**
	 * Returns the rs contract details fr with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract details fr
	 * @return the rs contract details fr
	 * @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRsContractDetailsFrException {
		RsContractDetailsFr rsContractDetailsFr = fetchByPrimaryKey(primaryKey);

		if (rsContractDetailsFr == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rsContractDetailsFr;
	}

	/**
	 * Returns the rs contract details fr with the primary key or throws a {@link NoSuchRsContractDetailsFrException} if it could not be found.
	 *
	 * @param rsContractDetailsFrSid the primary key of the rs contract details fr
	 * @return the rs contract details fr
	 * @throws NoSuchRsContractDetailsFrException if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr findByPrimaryKey(int rsContractDetailsFrSid)
		throws NoSuchRsContractDetailsFrException {
		return findByPrimaryKey((Serializable)rsContractDetailsFrSid);
	}

	/**
	 * Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract details fr
	 * @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				RsContractDetailsFrImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RsContractDetailsFr rsContractDetailsFr = (RsContractDetailsFr)serializable;

		if (rsContractDetailsFr == null) {
			Session session = null;

			try {
				session = openSession();

				rsContractDetailsFr = (RsContractDetailsFr)session.get(RsContractDetailsFrImpl.class,
						primaryKey);

				if (rsContractDetailsFr != null) {
					cacheResult(rsContractDetailsFr);
				}
				else {
					entityCache.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						RsContractDetailsFrImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsFrImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rsContractDetailsFr;
	}

	/**
	 * Returns the rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rsContractDetailsFrSid the primary key of the rs contract details fr
	 * @return the rs contract details fr, or <code>null</code> if a rs contract details fr with the primary key could not be found
	 */
	@Override
	public RsContractDetailsFr fetchByPrimaryKey(int rsContractDetailsFrSid) {
		return fetchByPrimaryKey((Serializable)rsContractDetailsFrSid);
	}

	@Override
	public Map<Serializable, RsContractDetailsFr> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RsContractDetailsFr> map = new HashMap<Serializable, RsContractDetailsFr>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RsContractDetailsFr rsContractDetailsFr = fetchByPrimaryKey(primaryKey);

			if (rsContractDetailsFr != null) {
				map.put(primaryKey, rsContractDetailsFr);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsFrImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RsContractDetailsFr)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RSCONTRACTDETAILSFR_WHERE_PKS_IN);

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

			for (RsContractDetailsFr rsContractDetailsFr : (List<RsContractDetailsFr>)q.list()) {
				map.put(rsContractDetailsFr.getPrimaryKeyObj(),
					rsContractDetailsFr);

				cacheResult(rsContractDetailsFr);

				uncachedPrimaryKeys.remove(rsContractDetailsFr.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsContractDetailsFrImpl.class, primaryKey, nullModel);
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
	 * Returns all the rs contract details frs.
	 *
	 * @return the rs contract details frs
	 */
	@Override
	public List<RsContractDetailsFr> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract details frs
	 * @param end the upper bound of the range of rs contract details frs (not inclusive)
	 * @return the range of rs contract details frs
	 */
	@Override
	public List<RsContractDetailsFr> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract details frs
	 * @param end the upper bound of the range of rs contract details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rs contract details frs
	 */
	@Override
	public List<RsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<RsContractDetailsFr> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contract details frs
	 * @param end the upper bound of the range of rs contract details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rs contract details frs
	 */
	@Override
	public List<RsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<RsContractDetailsFr> orderByComparator,
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

		List<RsContractDetailsFr> list = null;

		if (retrieveFromCache) {
			list = (List<RsContractDetailsFr>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RSCONTRACTDETAILSFR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RSCONTRACTDETAILSFR;

				if (pagination) {
					sql = sql.concat(RsContractDetailsFrModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RsContractDetailsFr>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsContractDetailsFr>)QueryUtil.list(q,
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
	 * Removes all the rs contract details frs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RsContractDetailsFr rsContractDetailsFr : findAll()) {
			remove(rsContractDetailsFr);
		}
	}

	/**
	 * Returns the number of rs contract details frs.
	 *
	 * @return the number of rs contract details frs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RSCONTRACTDETAILSFR);

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
		return RsContractDetailsFrModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rs contract details fr persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RsContractDetailsFrImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RSCONTRACTDETAILSFR = "SELECT rsContractDetailsFr FROM RsContractDetailsFr rsContractDetailsFr";
	private static final String _SQL_SELECT_RSCONTRACTDETAILSFR_WHERE_PKS_IN = "SELECT rsContractDetailsFr FROM RsContractDetailsFr rsContractDetailsFr WHERE RS_CONTRACT_DETAILS_FR_SID IN (";
	private static final String _SQL_COUNT_RSCONTRACTDETAILSFR = "SELECT COUNT(rsContractDetailsFr) FROM RsContractDetailsFr rsContractDetailsFr";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rsContractDetailsFr.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsContractDetailsFr exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RsContractDetailsFrPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"recordLockStatus", "createdDate", "createdBy", "source",
				"formulaMethodId", "itemMasterSid", "batchId",
				"rsContractDetailsFrSid", "modifiedBy", "inboundStatus",
				"formulaId", "modifiedDate", "rsContractDetailsSid"
			});
}