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

import com.stpl.app.parttwo.exception.NoSuchCffViewMasterException;
import com.stpl.app.parttwo.model.CffViewMaster;
import com.stpl.app.parttwo.model.impl.CffViewMasterImpl;
import com.stpl.app.parttwo.model.impl.CffViewMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.CffViewMasterPersistence;

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
 * The persistence implementation for the cff view master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffViewMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffViewMasterUtil
 * @generated
 */
@ProviderType
public class CffViewMasterPersistenceImpl extends BasePersistenceImpl<CffViewMaster>
	implements CffViewMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffViewMasterUtil} to access the cff view master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffViewMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CffViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterModelImpl.FINDER_CACHE_ENABLED,
			CffViewMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffViewMasterPersistenceImpl() {
		setModelClass(CffViewMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("viewType", "VIEW_TYPE");
			dbColumnNames.put("cffViewMasterSid", "CFF_VIEW_MASTER_SID");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
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
	 * Caches the cff view master in the entity cache if it is enabled.
	 *
	 * @param cffViewMaster the cff view master
	 */
	@Override
	public void cacheResult(CffViewMaster cffViewMaster) {
		entityCache.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterImpl.class, cffViewMaster.getPrimaryKey(),
			cffViewMaster);

		cffViewMaster.resetOriginalValues();
	}

	/**
	 * Caches the cff view masters in the entity cache if it is enabled.
	 *
	 * @param cffViewMasters the cff view masters
	 */
	@Override
	public void cacheResult(List<CffViewMaster> cffViewMasters) {
		for (CffViewMaster cffViewMaster : cffViewMasters) {
			if (entityCache.getResult(
						CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffViewMasterImpl.class, cffViewMaster.getPrimaryKey()) == null) {
				cacheResult(cffViewMaster);
			}
			else {
				cffViewMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff view masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffViewMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff view master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffViewMaster cffViewMaster) {
		entityCache.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterImpl.class, cffViewMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffViewMaster> cffViewMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffViewMaster cffViewMaster : cffViewMasters) {
			entityCache.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffViewMasterImpl.class, cffViewMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff view master with the primary key. Does not add the cff view master to the database.
	 *
	 * @param cffViewMasterSid the primary key for the new cff view master
	 * @return the new cff view master
	 */
	@Override
	public CffViewMaster create(int cffViewMasterSid) {
		CffViewMaster cffViewMaster = new CffViewMasterImpl();

		cffViewMaster.setNew(true);
		cffViewMaster.setPrimaryKey(cffViewMasterSid);

		return cffViewMaster;
	}

	/**
	 * Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffViewMasterSid the primary key of the cff view master
	 * @return the cff view master that was removed
	 * @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster remove(int cffViewMasterSid)
		throws NoSuchCffViewMasterException {
		return remove((Serializable)cffViewMasterSid);
	}

	/**
	 * Removes the cff view master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff view master
	 * @return the cff view master that was removed
	 * @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster remove(Serializable primaryKey)
		throws NoSuchCffViewMasterException {
		Session session = null;

		try {
			session = openSession();

			CffViewMaster cffViewMaster = (CffViewMaster)session.get(CffViewMasterImpl.class,
					primaryKey);

			if (cffViewMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffViewMaster);
		}
		catch (NoSuchCffViewMasterException nsee) {
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
	protected CffViewMaster removeImpl(CffViewMaster cffViewMaster) {
		cffViewMaster = toUnwrappedModel(cffViewMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffViewMaster)) {
				cffViewMaster = (CffViewMaster)session.get(CffViewMasterImpl.class,
						cffViewMaster.getPrimaryKeyObj());
			}

			if (cffViewMaster != null) {
				session.delete(cffViewMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffViewMaster != null) {
			clearCache(cffViewMaster);
		}

		return cffViewMaster;
	}

	@Override
	public CffViewMaster updateImpl(CffViewMaster cffViewMaster) {
		cffViewMaster = toUnwrappedModel(cffViewMaster);

		boolean isNew = cffViewMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffViewMaster.isNew()) {
				session.save(cffViewMaster);

				cffViewMaster.setNew(false);
			}
			else {
				cffViewMaster = (CffViewMaster)session.merge(cffViewMaster);
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

		entityCache.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
			CffViewMasterImpl.class, cffViewMaster.getPrimaryKey(),
			cffViewMaster, false);

		cffViewMaster.resetOriginalValues();

		return cffViewMaster;
	}

	protected CffViewMaster toUnwrappedModel(CffViewMaster cffViewMaster) {
		if (cffViewMaster instanceof CffViewMasterImpl) {
			return cffViewMaster;
		}

		CffViewMasterImpl cffViewMasterImpl = new CffViewMasterImpl();

		cffViewMasterImpl.setNew(cffViewMaster.isNew());
		cffViewMasterImpl.setPrimaryKey(cffViewMaster.getPrimaryKey());

		cffViewMasterImpl.setCreatedDate(cffViewMaster.getCreatedDate());
		cffViewMasterImpl.setCreatedBy(cffViewMaster.getCreatedBy());
		cffViewMasterImpl.setViewType(cffViewMaster.getViewType());
		cffViewMasterImpl.setCffViewMasterSid(cffViewMaster.getCffViewMasterSid());
		cffViewMasterImpl.setCffMasterSid(cffViewMaster.getCffMasterSid());
		cffViewMasterImpl.setModifiedBy(cffViewMaster.getModifiedBy());
		cffViewMasterImpl.setModifiedDate(cffViewMaster.getModifiedDate());
		cffViewMasterImpl.setViewName(cffViewMaster.getViewName());

		return cffViewMasterImpl;
	}

	/**
	 * Returns the cff view master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff view master
	 * @return the cff view master
	 * @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffViewMasterException {
		CffViewMaster cffViewMaster = fetchByPrimaryKey(primaryKey);

		if (cffViewMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffViewMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffViewMaster;
	}

	/**
	 * Returns the cff view master with the primary key or throws a {@link NoSuchCffViewMasterException} if it could not be found.
	 *
	 * @param cffViewMasterSid the primary key of the cff view master
	 * @return the cff view master
	 * @throws NoSuchCffViewMasterException if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster findByPrimaryKey(int cffViewMasterSid)
		throws NoSuchCffViewMasterException {
		return findByPrimaryKey((Serializable)cffViewMasterSid);
	}

	/**
	 * Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff view master
	 * @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
				CffViewMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffViewMaster cffViewMaster = (CffViewMaster)serializable;

		if (cffViewMaster == null) {
			Session session = null;

			try {
				session = openSession();

				cffViewMaster = (CffViewMaster)session.get(CffViewMasterImpl.class,
						primaryKey);

				if (cffViewMaster != null) {
					cacheResult(cffViewMaster);
				}
				else {
					entityCache.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
						CffViewMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffViewMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffViewMaster;
	}

	/**
	 * Returns the cff view master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffViewMasterSid the primary key of the cff view master
	 * @return the cff view master, or <code>null</code> if a cff view master with the primary key could not be found
	 */
	@Override
	public CffViewMaster fetchByPrimaryKey(int cffViewMasterSid) {
		return fetchByPrimaryKey((Serializable)cffViewMasterSid);
	}

	@Override
	public Map<Serializable, CffViewMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffViewMaster> map = new HashMap<Serializable, CffViewMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffViewMaster cffViewMaster = fetchByPrimaryKey(primaryKey);

			if (cffViewMaster != null) {
				map.put(primaryKey, cffViewMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffViewMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffViewMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFVIEWMASTER_WHERE_PKS_IN);

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

			for (CffViewMaster cffViewMaster : (List<CffViewMaster>)q.list()) {
				map.put(cffViewMaster.getPrimaryKeyObj(), cffViewMaster);

				cacheResult(cffViewMaster);

				uncachedPrimaryKeys.remove(cffViewMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffViewMasterModelImpl.ENTITY_CACHE_ENABLED,
					CffViewMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff view masters.
	 *
	 * @return the cff view masters
	 */
	@Override
	public List<CffViewMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff view masters
	 * @param end the upper bound of the range of cff view masters (not inclusive)
	 * @return the range of cff view masters
	 */
	@Override
	public List<CffViewMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff view masters
	 * @param end the upper bound of the range of cff view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff view masters
	 */
	@Override
	public List<CffViewMaster> findAll(int start, int end,
		OrderByComparator<CffViewMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff view masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff view masters
	 * @param end the upper bound of the range of cff view masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff view masters
	 */
	@Override
	public List<CffViewMaster> findAll(int start, int end,
		OrderByComparator<CffViewMaster> orderByComparator,
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

		List<CffViewMaster> list = null;

		if (retrieveFromCache) {
			list = (List<CffViewMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFVIEWMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFVIEWMASTER;

				if (pagination) {
					sql = sql.concat(CffViewMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffViewMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffViewMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cff view masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffViewMaster cffViewMaster : findAll()) {
			remove(cffViewMaster);
		}
	}

	/**
	 * Returns the number of cff view masters.
	 *
	 * @return the number of cff view masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFVIEWMASTER);

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
		return CffViewMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff view master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffViewMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFVIEWMASTER = "SELECT cffViewMaster FROM CffViewMaster cffViewMaster";
	private static final String _SQL_SELECT_CFFVIEWMASTER_WHERE_PKS_IN = "SELECT cffViewMaster FROM CffViewMaster cffViewMaster WHERE CFF_VIEW_MASTER_SID IN (";
	private static final String _SQL_COUNT_CFFVIEWMASTER = "SELECT COUNT(cffViewMaster) FROM CffViewMaster cffViewMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffViewMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffViewMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffViewMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "viewType", "cffViewMasterSid",
				"cffMasterSid", "modifiedBy", "modifiedDate", "viewName"
			});
}