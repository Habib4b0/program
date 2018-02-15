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

import com.stpl.app.exception.NoSuchRsDetailsFrException;
import com.stpl.app.model.RsDetailsFr;
import com.stpl.app.model.impl.RsDetailsFrImpl;
import com.stpl.app.model.impl.RsDetailsFrModelImpl;
import com.stpl.app.service.persistence.RsDetailsFrPersistence;

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
 * The persistence implementation for the rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsFrPersistence
 * @see com.stpl.app.service.persistence.RsDetailsFrUtil
 * @generated
 */
@ProviderType
public class RsDetailsFrPersistenceImpl extends BasePersistenceImpl<RsDetailsFr>
	implements RsDetailsFrPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RsDetailsFrUtil} to access the rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RsDetailsFrImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, RsDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, RsDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RsDetailsFrPersistenceImpl() {
		setModelClass(RsDetailsFr.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rsDetailsSid", "RS_DETAILS_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsDetailsFrSid", "RS_DETAILS_FR_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rs details fr in the entity cache if it is enabled.
	 *
	 * @param rsDetailsFr the rs details fr
	 */
	@Override
	public void cacheResult(RsDetailsFr rsDetailsFr) {
		entityCache.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey(), rsDetailsFr);

		rsDetailsFr.resetOriginalValues();
	}

	/**
	 * Caches the rs details frs in the entity cache if it is enabled.
	 *
	 * @param rsDetailsFrs the rs details frs
	 */
	@Override
	public void cacheResult(List<RsDetailsFr> rsDetailsFrs) {
		for (RsDetailsFr rsDetailsFr : rsDetailsFrs) {
			if (entityCache.getResult(
						RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey()) == null) {
				cacheResult(rsDetailsFr);
			}
			else {
				rsDetailsFr.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rs details frs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RsDetailsFrImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rs details fr.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RsDetailsFr rsDetailsFr) {
		entityCache.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RsDetailsFr> rsDetailsFrs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RsDetailsFr rsDetailsFr : rsDetailsFrs) {
			entityCache.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
	 *
	 * @param rsDetailsFrSid the primary key for the new rs details fr
	 * @return the new rs details fr
	 */
	@Override
	public RsDetailsFr create(int rsDetailsFrSid) {
		RsDetailsFr rsDetailsFr = new RsDetailsFrImpl();

		rsDetailsFr.setNew(true);
		rsDetailsFr.setPrimaryKey(rsDetailsFrSid);

		return rsDetailsFr;
	}

	/**
	 * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rsDetailsFrSid the primary key of the rs details fr
	 * @return the rs details fr that was removed
	 * @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr remove(int rsDetailsFrSid)
		throws NoSuchRsDetailsFrException {
		return remove((Serializable)rsDetailsFrSid);
	}

	/**
	 * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rs details fr
	 * @return the rs details fr that was removed
	 * @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr remove(Serializable primaryKey)
		throws NoSuchRsDetailsFrException {
		Session session = null;

		try {
			session = openSession();

			RsDetailsFr rsDetailsFr = (RsDetailsFr)session.get(RsDetailsFrImpl.class,
					primaryKey);

			if (rsDetailsFr == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rsDetailsFr);
		}
		catch (NoSuchRsDetailsFrException nsee) {
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
	protected RsDetailsFr removeImpl(RsDetailsFr rsDetailsFr) {
		rsDetailsFr = toUnwrappedModel(rsDetailsFr);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rsDetailsFr)) {
				rsDetailsFr = (RsDetailsFr)session.get(RsDetailsFrImpl.class,
						rsDetailsFr.getPrimaryKeyObj());
			}

			if (rsDetailsFr != null) {
				session.delete(rsDetailsFr);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rsDetailsFr != null) {
			clearCache(rsDetailsFr);
		}

		return rsDetailsFr;
	}

	@Override
	public RsDetailsFr updateImpl(RsDetailsFr rsDetailsFr) {
		rsDetailsFr = toUnwrappedModel(rsDetailsFr);

		boolean isNew = rsDetailsFr.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rsDetailsFr.isNew()) {
				session.save(rsDetailsFr);

				rsDetailsFr.setNew(false);
			}
			else {
				rsDetailsFr = (RsDetailsFr)session.merge(rsDetailsFr);
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

		entityCache.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsFrImpl.class, rsDetailsFr.getPrimaryKey(), rsDetailsFr,
			false);

		rsDetailsFr.resetOriginalValues();

		return rsDetailsFr;
	}

	protected RsDetailsFr toUnwrappedModel(RsDetailsFr rsDetailsFr) {
		if (rsDetailsFr instanceof RsDetailsFrImpl) {
			return rsDetailsFr;
		}

		RsDetailsFrImpl rsDetailsFrImpl = new RsDetailsFrImpl();

		rsDetailsFrImpl.setNew(rsDetailsFr.isNew());
		rsDetailsFrImpl.setPrimaryKey(rsDetailsFr.getPrimaryKey());

		rsDetailsFrImpl.setRecordLockStatus(rsDetailsFr.isRecordLockStatus());
		rsDetailsFrImpl.setCreatedDate(rsDetailsFr.getCreatedDate());
		rsDetailsFrImpl.setCreatedBy(rsDetailsFr.getCreatedBy());
		rsDetailsFrImpl.setSource(rsDetailsFr.getSource());
		rsDetailsFrImpl.setFormulaMethodId(rsDetailsFr.getFormulaMethodId());
		rsDetailsFrImpl.setBatchId(rsDetailsFr.getBatchId());
		rsDetailsFrImpl.setModifiedBy(rsDetailsFr.getModifiedBy());
		rsDetailsFrImpl.setInboundStatus(rsDetailsFr.getInboundStatus());
		rsDetailsFrImpl.setFormulaId(rsDetailsFr.getFormulaId());
		rsDetailsFrImpl.setItemMasterSid(rsDetailsFr.getItemMasterSid());
		rsDetailsFrImpl.setRsDetailsSid(rsDetailsFr.getRsDetailsSid());
		rsDetailsFrImpl.setModifiedDate(rsDetailsFr.getModifiedDate());
		rsDetailsFrImpl.setRsDetailsFrSid(rsDetailsFr.getRsDetailsFrSid());

		return rsDetailsFrImpl;
	}

	/**
	 * Returns the rs details fr with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs details fr
	 * @return the rs details fr
	 * @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRsDetailsFrException {
		RsDetailsFr rsDetailsFr = fetchByPrimaryKey(primaryKey);

		if (rsDetailsFr == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rsDetailsFr;
	}

	/**
	 * Returns the rs details fr with the primary key or throws a {@link NoSuchRsDetailsFrException} if it could not be found.
	 *
	 * @param rsDetailsFrSid the primary key of the rs details fr
	 * @return the rs details fr
	 * @throws NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr findByPrimaryKey(int rsDetailsFrSid)
		throws NoSuchRsDetailsFrException {
		return findByPrimaryKey((Serializable)rsDetailsFrSid);
	}

	/**
	 * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs details fr
	 * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				RsDetailsFrImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RsDetailsFr rsDetailsFr = (RsDetailsFr)serializable;

		if (rsDetailsFr == null) {
			Session session = null;

			try {
				session = openSession();

				rsDetailsFr = (RsDetailsFr)session.get(RsDetailsFrImpl.class,
						primaryKey);

				if (rsDetailsFr != null) {
					cacheResult(rsDetailsFr);
				}
				else {
					entityCache.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						RsDetailsFrImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsFrImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rsDetailsFr;
	}

	/**
	 * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rsDetailsFrSid the primary key of the rs details fr
	 * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
	 */
	@Override
	public RsDetailsFr fetchByPrimaryKey(int rsDetailsFrSid) {
		return fetchByPrimaryKey((Serializable)rsDetailsFrSid);
	}

	@Override
	public Map<Serializable, RsDetailsFr> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RsDetailsFr> map = new HashMap<Serializable, RsDetailsFr>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RsDetailsFr rsDetailsFr = fetchByPrimaryKey(primaryKey);

			if (rsDetailsFr != null) {
				map.put(primaryKey, rsDetailsFr);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsFrImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RsDetailsFr)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RSDETAILSFR_WHERE_PKS_IN);

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

			for (RsDetailsFr rsDetailsFr : (List<RsDetailsFr>)q.list()) {
				map.put(rsDetailsFr.getPrimaryKeyObj(), rsDetailsFr);

				cacheResult(rsDetailsFr);

				uncachedPrimaryKeys.remove(rsDetailsFr.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsFrImpl.class, primaryKey, nullModel);
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
	 * Returns all the rs details frs.
	 *
	 * @return the rs details frs
	 */
	@Override
	public List<RsDetailsFr> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs details frs
	 * @param end the upper bound of the range of rs details frs (not inclusive)
	 * @return the range of rs details frs
	 */
	@Override
	public List<RsDetailsFr> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs details frs
	 * @param end the upper bound of the range of rs details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rs details frs
	 */
	@Override
	public List<RsDetailsFr> findAll(int start, int end,
		OrderByComparator<RsDetailsFr> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs details frs
	 * @param end the upper bound of the range of rs details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rs details frs
	 */
	@Override
	public List<RsDetailsFr> findAll(int start, int end,
		OrderByComparator<RsDetailsFr> orderByComparator,
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

		List<RsDetailsFr> list = null;

		if (retrieveFromCache) {
			list = (List<RsDetailsFr>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RSDETAILSFR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RSDETAILSFR;

				if (pagination) {
					sql = sql.concat(RsDetailsFrModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RsDetailsFr>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsDetailsFr>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the rs details frs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RsDetailsFr rsDetailsFr : findAll()) {
			remove(rsDetailsFr);
		}
	}

	/**
	 * Returns the number of rs details frs.
	 *
	 * @return the number of rs details frs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RSDETAILSFR);

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
		return RsDetailsFrModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rs details fr persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RsDetailsFrImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RSDETAILSFR = "SELECT rsDetailsFr FROM RsDetailsFr rsDetailsFr";
	private static final String _SQL_SELECT_RSDETAILSFR_WHERE_PKS_IN = "SELECT rsDetailsFr FROM RsDetailsFr rsDetailsFr WHERE RS_DETAILS_FR_SID IN (";
	private static final String _SQL_COUNT_RSDETAILSFR = "SELECT COUNT(rsDetailsFr) FROM RsDetailsFr rsDetailsFr";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rsDetailsFr.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsDetailsFr exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RsDetailsFrPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"recordLockStatus", "createdDate", "createdBy", "source",
				"formulaMethodId", "batchId", "modifiedBy", "inboundStatus",
				"formulaId", "itemMasterSid", "rsDetailsSid", "modifiedDate",
				"rsDetailsFrSid"
			});
}