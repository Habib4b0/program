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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchCffCustomViewMasterException;
import com.stpl.app.parttwo.model.CffCustomViewMaster;
import com.stpl.app.parttwo.model.impl.CffCustomViewMasterImpl;
import com.stpl.app.parttwo.model.impl.CffCustomViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffCustomViewMasterPersistence;

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
 * The persistence implementation for the cff custom view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffCustomViewMasterUtil
 * @generated
 */
@ProviderType
public class CffCustomViewMasterPersistenceImpl extends BasePersistenceImpl<CffCustomViewMaster>
	implements CffCustomViewMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffCustomViewMasterUtil} to access the cff custom view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffCustomViewMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CffCustomViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CffCustomViewMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffCustomViewMasterPersistenceImpl() {
		setModelClass(CffCustomViewMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("cffCustomViewMasterSid",
				"CFF_CUSTOM_VIEW_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("viewName", "VIEW_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff custom view master in the entity cache if it is enabled.
	 *
	 * @param cffCustomViewMaster the cff custom view master
	 */
	@Override
	public void cacheResult(CffCustomViewMaster cffCustomViewMaster) {
		entityCache.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey(),
			cffCustomViewMaster);

		cffCustomViewMaster.resetOriginalValues();
	}

	/**
	 * Caches the cff custom view masters in the entity cache if it is enabled.
	 *
	 * @param cffCustomViewMasters the cff custom view masters
	 */
	@Override
	public void cacheResult(List<CffCustomViewMaster> cffCustomViewMasters) {
		for (CffCustomViewMaster cffCustomViewMaster : cffCustomViewMasters) {
			if (entityCache.getResult(
						CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffCustomViewMasterImpl.class,
						cffCustomViewMaster.getPrimaryKey()) == null) {
				cacheResult(cffCustomViewMaster);
			}
			else {
				cffCustomViewMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff custom view masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffCustomViewMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff custom view master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffCustomViewMaster cffCustomViewMaster) {
		entityCache.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffCustomViewMaster> cffCustomViewMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffCustomViewMaster cffCustomViewMaster : cffCustomViewMasters) {
			entityCache.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffCustomViewMasterImpl.class,
				cffCustomViewMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff custom view master with the primary key. Does not add the cff custom view master to the database.
	 *
	 * @param cffCustomViewMasterSid the primary key for the new cff custom view master
	 * @return the new cff custom view master
	 */
	@Override
	public CffCustomViewMaster create(int cffCustomViewMasterSid) {
		CffCustomViewMaster cffCustomViewMaster = new CffCustomViewMasterImpl();

		cffCustomViewMaster.setNew(true);
		cffCustomViewMaster.setPrimaryKey(cffCustomViewMasterSid);

		return cffCustomViewMaster;
	}

	/**
	 * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffCustomViewMasterSid the primary key of the cff custom view master
	 * @return the cff custom view master that was removed
	 * @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster remove(int cffCustomViewMasterSid)
		throws NoSuchCffCustomViewMasterException {
		return remove((Serializable)cffCustomViewMasterSid);
	}

	/**
	 * Removes the cff custom view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff custom view master
	 * @return the cff custom view master that was removed
	 * @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster remove(Serializable primaryKey)
		throws NoSuchCffCustomViewMasterException {
		Session session = null;

		try {
			session = openSession();

			CffCustomViewMaster cffCustomViewMaster = (CffCustomViewMaster)session.get(CffCustomViewMasterImpl.class,
					primaryKey);

			if (cffCustomViewMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffCustomViewMaster);
		}
		catch (NoSuchCffCustomViewMasterException nsee) {
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
	protected CffCustomViewMaster removeImpl(
		CffCustomViewMaster cffCustomViewMaster) {
		cffCustomViewMaster = toUnwrappedModel(cffCustomViewMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffCustomViewMaster)) {
				cffCustomViewMaster = (CffCustomViewMaster)session.get(CffCustomViewMasterImpl.class,
						cffCustomViewMaster.getPrimaryKeyObj());
			}

			if (cffCustomViewMaster != null) {
				session.delete(cffCustomViewMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffCustomViewMaster != null) {
			clearCache(cffCustomViewMaster);
		}

		return cffCustomViewMaster;
	}

	@Override
	public CffCustomViewMaster updateImpl(
		CffCustomViewMaster cffCustomViewMaster) {
		cffCustomViewMaster = toUnwrappedModel(cffCustomViewMaster);

		boolean isNew = cffCustomViewMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffCustomViewMaster.isNew()) {
				session.save(cffCustomViewMaster);

				cffCustomViewMaster.setNew(false);
			}
			else {
				cffCustomViewMaster = (CffCustomViewMaster)session.merge(cffCustomViewMaster);
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

		entityCache.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewMasterImpl.class, cffCustomViewMaster.getPrimaryKey(),
			cffCustomViewMaster, false);

		cffCustomViewMaster.resetOriginalValues();

		return cffCustomViewMaster;
	}

	protected CffCustomViewMaster toUnwrappedModel(
		CffCustomViewMaster cffCustomViewMaster) {
		if (cffCustomViewMaster instanceof CffCustomViewMasterImpl) {
			return cffCustomViewMaster;
		}

		CffCustomViewMasterImpl cffCustomViewMasterImpl = new CffCustomViewMasterImpl();

		cffCustomViewMasterImpl.setNew(cffCustomViewMaster.isNew());
		cffCustomViewMasterImpl.setPrimaryKey(cffCustomViewMaster.getPrimaryKey());

		cffCustomViewMasterImpl.setCreatedDate(cffCustomViewMaster.getCreatedDate());
		cffCustomViewMasterImpl.setCreatedBy(cffCustomViewMaster.getCreatedBy());
		cffCustomViewMasterImpl.setCffMasterSid(cffCustomViewMaster.getCffMasterSid());
		cffCustomViewMasterImpl.setModifiedBy(cffCustomViewMaster.getModifiedBy());
		cffCustomViewMasterImpl.setCffCustomViewMasterSid(cffCustomViewMaster.getCffCustomViewMasterSid());
		cffCustomViewMasterImpl.setModifiedDate(cffCustomViewMaster.getModifiedDate());
		cffCustomViewMasterImpl.setViewName(cffCustomViewMaster.getViewName());

		return cffCustomViewMasterImpl;
	}

	/**
	 * Returns the cff custom view master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff custom view master
	 * @return the cff custom view master
	 * @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffCustomViewMasterException {
		CffCustomViewMaster cffCustomViewMaster = fetchByPrimaryKey(primaryKey);

		if (cffCustomViewMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffCustomViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffCustomViewMaster;
	}

	/**
	 * Returns the cff custom view master with the primary key or throws a {@link NoSuchCffCustomViewMasterException} if it could not be found.
	 *
	 * @param cffCustomViewMasterSid the primary key of the cff custom view master
	 * @return the cff custom view master
	 * @throws NoSuchCffCustomViewMasterException if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster findByPrimaryKey(int cffCustomViewMasterSid)
		throws NoSuchCffCustomViewMasterException {
		return findByPrimaryKey((Serializable)cffCustomViewMasterSid);
	}

	/**
	 * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff custom view master
	 * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffCustomViewMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffCustomViewMaster cffCustomViewMaster = (CffCustomViewMaster)serializable;

		if (cffCustomViewMaster == null) {
			Session session = null;

			try {
				session = openSession();

				cffCustomViewMaster = (CffCustomViewMaster)session.get(CffCustomViewMasterImpl.class,
						primaryKey);

				if (cffCustomViewMaster != null) {
					cacheResult(cffCustomViewMaster);
				}
				else {
					entityCache.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffCustomViewMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffCustomViewMaster;
	}

	/**
	 * Returns the cff custom view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffCustomViewMasterSid the primary key of the cff custom view master
	 * @return the cff custom view master, or <code>null</code> if a cff custom view master with the primary key could not be found
	 */
	@Override
	public CffCustomViewMaster fetchByPrimaryKey(int cffCustomViewMasterSid) {
		return fetchByPrimaryKey((Serializable)cffCustomViewMasterSid);
	}

	@Override
	public Map<Serializable, CffCustomViewMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffCustomViewMaster> map = new HashMap<Serializable, CffCustomViewMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffCustomViewMaster cffCustomViewMaster = fetchByPrimaryKey(primaryKey);

			if (cffCustomViewMaster != null) {
				map.put(primaryKey, cffCustomViewMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffCustomViewMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFCUSTOMVIEWMASTER_WHERE_PKS_IN);

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

			for (CffCustomViewMaster cffCustomViewMaster : (List<CffCustomViewMaster>)q.list()) {
				map.put(cffCustomViewMaster.getPrimaryKeyObj(),
					cffCustomViewMaster);

				cacheResult(cffCustomViewMaster);

				uncachedPrimaryKeys.remove(cffCustomViewMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffCustomViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff custom view masters.
	 *
	 * @return the cff custom view masters
	 */
	@Override
	public List<CffCustomViewMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view masters
	 * @param end the upper bound of the range of cff custom view masters (not inclusive)
	 * @return the range of cff custom view masters
	 */
	@Override
	public List<CffCustomViewMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view masters
	 * @param end the upper bound of the range of cff custom view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff custom view masters
	 */
	@Override
	public List<CffCustomViewMaster> findAll(int start, int end,
		OrderByComparator<CffCustomViewMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff custom view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view masters
	 * @param end the upper bound of the range of cff custom view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff custom view masters
	 */
	@Override
	public List<CffCustomViewMaster> findAll(int start, int end,
		OrderByComparator<CffCustomViewMaster> orderByComparator,
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

		List<CffCustomViewMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CffCustomViewMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFCUSTOMVIEWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFCUSTOMVIEWMASTER;

				if (pagination) {
					sql = sql.concat(CffCustomViewMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffCustomViewMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffCustomViewMaster>)QueryUtil.list(q,
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
	 * Removes all the cff custom view masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffCustomViewMaster cffCustomViewMaster : findAll()) {
			remove(cffCustomViewMaster);
		}
	}

	/**
	 * Returns the number of cff custom view masters.
	 *
	 * @return the number of cff custom view masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFCUSTOMVIEWMASTER);

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
		return CffCustomViewMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff custom view master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffCustomViewMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFCUSTOMVIEWMASTER = "SELECT cffCustomViewMaster FROM CffCustomViewMaster cffCustomViewMaster";
	private static final String _SQL_SELECT_CFFCUSTOMVIEWMASTER_WHERE_PKS_IN = "SELECT cffCustomViewMaster FROM CffCustomViewMaster cffCustomViewMaster WHERE CFF_CUSTOM_VIEW_MASTER_SID IN (";
	private static final String _SQL_COUNT_CFFCUSTOMVIEWMASTER = "SELECT COUNT(cffCustomViewMaster) FROM CffCustomViewMaster cffCustomViewMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffCustomViewMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffCustomViewMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffCustomViewMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "cffMasterSid", "modifiedBy",
				"cffCustomViewMasterSid", "modifiedDate", "viewName"
			});
}