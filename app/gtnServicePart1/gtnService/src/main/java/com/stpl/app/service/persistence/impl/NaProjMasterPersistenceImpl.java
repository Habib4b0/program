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

import com.stpl.app.exception.NoSuchNaProjMasterException;
import com.stpl.app.model.NaProjMaster;
import com.stpl.app.model.impl.NaProjMasterImpl;
import com.stpl.app.model.impl.NaProjMasterModelImpl;
import com.stpl.app.service.persistence.NaProjMasterPersistence;

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
 * The persistence implementation for the na proj master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjMasterPersistence
 * @see com.stpl.app.service.persistence.NaProjMasterUtil
 * @generated
 */
@ProviderType
public class NaProjMasterPersistenceImpl extends BasePersistenceImpl<NaProjMaster>
	implements NaProjMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NaProjMasterUtil} to access the na proj master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NaProjMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterModelImpl.FINDER_CACHE_ENABLED, NaProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterModelImpl.FINDER_CACHE_ENABLED, NaProjMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NaProjMasterPersistenceImpl() {
		setModelClass(NaProjMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("naProjName", "NA_PROJ_NAME");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("saveFlag", "SAVE_FLAG");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("naProjMasterSid", "NA_PROJ_MASTER_SID");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");
			dbColumnNames.put("therapeuticClass", "THERAPEUTIC_CLASS");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("businessUnit", "BUSINESS_UNIT");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the na proj master in the entity cache if it is enabled.
	 *
	 * @param naProjMaster the na proj master
	 */
	@Override
	public void cacheResult(NaProjMaster naProjMaster) {
		entityCache.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterImpl.class, naProjMaster.getPrimaryKey(), naProjMaster);

		naProjMaster.resetOriginalValues();
	}

	/**
	 * Caches the na proj masters in the entity cache if it is enabled.
	 *
	 * @param naProjMasters the na proj masters
	 */
	@Override
	public void cacheResult(List<NaProjMaster> naProjMasters) {
		for (NaProjMaster naProjMaster : naProjMasters) {
			if (entityCache.getResult(
						NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						NaProjMasterImpl.class, naProjMaster.getPrimaryKey()) == null) {
				cacheResult(naProjMaster);
			}
			else {
				naProjMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all na proj masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NaProjMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the na proj master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NaProjMaster naProjMaster) {
		entityCache.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterImpl.class, naProjMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NaProjMaster> naProjMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NaProjMaster naProjMaster : naProjMasters) {
			entityCache.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				NaProjMasterImpl.class, naProjMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new na proj master with the primary key. Does not add the na proj master to the database.
	 *
	 * @param naProjMasterSid the primary key for the new na proj master
	 * @return the new na proj master
	 */
	@Override
	public NaProjMaster create(int naProjMasterSid) {
		NaProjMaster naProjMaster = new NaProjMasterImpl();

		naProjMaster.setNew(true);
		naProjMaster.setPrimaryKey(naProjMasterSid);

		return naProjMaster;
	}

	/**
	 * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param naProjMasterSid the primary key of the na proj master
	 * @return the na proj master that was removed
	 * @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster remove(int naProjMasterSid)
		throws NoSuchNaProjMasterException {
		return remove((Serializable)naProjMasterSid);
	}

	/**
	 * Removes the na proj master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the na proj master
	 * @return the na proj master that was removed
	 * @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster remove(Serializable primaryKey)
		throws NoSuchNaProjMasterException {
		Session session = null;

		try {
			session = openSession();

			NaProjMaster naProjMaster = (NaProjMaster)session.get(NaProjMasterImpl.class,
					primaryKey);

			if (naProjMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNaProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(naProjMaster);
		}
		catch (NoSuchNaProjMasterException nsee) {
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
	protected NaProjMaster removeImpl(NaProjMaster naProjMaster) {
		naProjMaster = toUnwrappedModel(naProjMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(naProjMaster)) {
				naProjMaster = (NaProjMaster)session.get(NaProjMasterImpl.class,
						naProjMaster.getPrimaryKeyObj());
			}

			if (naProjMaster != null) {
				session.delete(naProjMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (naProjMaster != null) {
			clearCache(naProjMaster);
		}

		return naProjMaster;
	}

	@Override
	public NaProjMaster updateImpl(NaProjMaster naProjMaster) {
		naProjMaster = toUnwrappedModel(naProjMaster);

		boolean isNew = naProjMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (naProjMaster.isNew()) {
				session.save(naProjMaster);

				naProjMaster.setNew(false);
			}
			else {
				naProjMaster = (NaProjMaster)session.merge(naProjMaster);
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

		entityCache.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
			NaProjMasterImpl.class, naProjMaster.getPrimaryKey(), naProjMaster,
			false);

		naProjMaster.resetOriginalValues();

		return naProjMaster;
	}

	protected NaProjMaster toUnwrappedModel(NaProjMaster naProjMaster) {
		if (naProjMaster instanceof NaProjMasterImpl) {
			return naProjMaster;
		}

		NaProjMasterImpl naProjMasterImpl = new NaProjMasterImpl();

		naProjMasterImpl.setNew(naProjMaster.isNew());
		naProjMasterImpl.setPrimaryKey(naProjMaster.getPrimaryKey());

		naProjMasterImpl.setNaProjName(naProjMaster.getNaProjName());
		naProjMasterImpl.setCreatedDate(naProjMaster.getCreatedDate());
		naProjMasterImpl.setCreatedBy(naProjMaster.getCreatedBy());
		naProjMasterImpl.setSaveFlag(naProjMaster.isSaveFlag());
		naProjMasterImpl.setModifiedBy(naProjMaster.getModifiedBy());
		naProjMasterImpl.setModifiedDate(naProjMaster.getModifiedDate());
		naProjMasterImpl.setNaProjMasterSid(naProjMaster.getNaProjMasterSid());
		naProjMasterImpl.setItemGroupSid(naProjMaster.getItemGroupSid());
		naProjMasterImpl.setTherapeuticClass(naProjMaster.getTherapeuticClass());
		naProjMasterImpl.setCompanyMasterSid(naProjMaster.getCompanyMasterSid());
		naProjMasterImpl.setBusinessUnit(naProjMaster.getBusinessUnit());

		return naProjMasterImpl;
	}

	/**
	 * Returns the na proj master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the na proj master
	 * @return the na proj master
	 * @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNaProjMasterException {
		NaProjMaster naProjMaster = fetchByPrimaryKey(primaryKey);

		if (naProjMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNaProjMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return naProjMaster;
	}

	/**
	 * Returns the na proj master with the primary key or throws a {@link NoSuchNaProjMasterException} if it could not be found.
	 *
	 * @param naProjMasterSid the primary key of the na proj master
	 * @return the na proj master
	 * @throws NoSuchNaProjMasterException if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster findByPrimaryKey(int naProjMasterSid)
		throws NoSuchNaProjMasterException {
		return findByPrimaryKey((Serializable)naProjMasterSid);
	}

	/**
	 * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the na proj master
	 * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
				NaProjMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NaProjMaster naProjMaster = (NaProjMaster)serializable;

		if (naProjMaster == null) {
			Session session = null;

			try {
				session = openSession();

				naProjMaster = (NaProjMaster)session.get(NaProjMasterImpl.class,
						primaryKey);

				if (naProjMaster != null) {
					cacheResult(naProjMaster);
				}
				else {
					entityCache.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
						NaProjMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NaProjMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return naProjMaster;
	}

	/**
	 * Returns the na proj master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param naProjMasterSid the primary key of the na proj master
	 * @return the na proj master, or <code>null</code> if a na proj master with the primary key could not be found
	 */
	@Override
	public NaProjMaster fetchByPrimaryKey(int naProjMasterSid) {
		return fetchByPrimaryKey((Serializable)naProjMasterSid);
	}

	@Override
	public Map<Serializable, NaProjMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NaProjMaster> map = new HashMap<Serializable, NaProjMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NaProjMaster naProjMaster = fetchByPrimaryKey(primaryKey);

			if (naProjMaster != null) {
				map.put(primaryKey, naProjMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NaProjMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NaProjMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NAPROJMASTER_WHERE_PKS_IN);

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

			for (NaProjMaster naProjMaster : (List<NaProjMaster>)q.list()) {
				map.put(naProjMaster.getPrimaryKeyObj(), naProjMaster);

				cacheResult(naProjMaster);

				uncachedPrimaryKeys.remove(naProjMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NaProjMasterModelImpl.ENTITY_CACHE_ENABLED,
					NaProjMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the na proj masters.
	 *
	 * @return the na proj masters
	 */
	@Override
	public List<NaProjMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the na proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj masters
	 * @param end the upper bound of the range of na proj masters (not inclusive)
	 * @return the range of na proj masters
	 */
	@Override
	public List<NaProjMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the na proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj masters
	 * @param end the upper bound of the range of na proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of na proj masters
	 */
	@Override
	public List<NaProjMaster> findAll(int start, int end,
		OrderByComparator<NaProjMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the na proj masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj masters
	 * @param end the upper bound of the range of na proj masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of na proj masters
	 */
	@Override
	public List<NaProjMaster> findAll(int start, int end,
		OrderByComparator<NaProjMaster> orderByComparator,
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

		List<NaProjMaster> list = null;

		if (retrieveFromCache) {
			list = (List<NaProjMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NAPROJMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NAPROJMASTER;

				if (pagination) {
					sql = sql.concat(NaProjMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NaProjMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NaProjMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the na proj masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NaProjMaster naProjMaster : findAll()) {
			remove(naProjMaster);
		}
	}

	/**
	 * Returns the number of na proj masters.
	 *
	 * @return the number of na proj masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NAPROJMASTER);

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
		return NaProjMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the na proj master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NaProjMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NAPROJMASTER = "SELECT naProjMaster FROM NaProjMaster naProjMaster";
	private static final String _SQL_SELECT_NAPROJMASTER_WHERE_PKS_IN = "SELECT naProjMaster FROM NaProjMaster naProjMaster WHERE NA_PROJ_MASTER_SID IN (";
	private static final String _SQL_COUNT_NAPROJMASTER = "SELECT COUNT(naProjMaster) FROM NaProjMaster naProjMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "naProjMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NaProjMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"naProjName", "createdDate", "createdBy", "saveFlag",
				"modifiedBy", "modifiedDate", "naProjMasterSid", "itemGroupSid",
				"therapeuticClass", "companyMasterSid", "businessUnit"
			});
}