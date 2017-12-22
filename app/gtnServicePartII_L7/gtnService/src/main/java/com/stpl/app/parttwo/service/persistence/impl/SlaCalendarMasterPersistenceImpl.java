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

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarMasterException;
import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.model.impl.SlaCalendarMasterImpl;
import com.stpl.app.parttwo.model.impl.SlaCalendarMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.SlaCalendarMasterPersistence;

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
 * The persistence implementation for the sla calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.SlaCalendarMasterUtil
 * @generated
 */
@ProviderType
public class SlaCalendarMasterPersistenceImpl extends BasePersistenceImpl<SlaCalendarMaster>
	implements SlaCalendarMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SlaCalendarMasterUtil} to access the sla calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SlaCalendarMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
			SlaCalendarMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
			SlaCalendarMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SlaCalendarMasterPersistenceImpl() {
		setModelClass(SlaCalendarMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("slaCalendarMasterSid", "SLA_CALENDAR_MASTER_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("defaultHolidays", "DEFAULT_HOLIDAYS");
			dbColumnNames.put("calendarName", "CALENDAR_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the sla calendar master in the entity cache if it is enabled.
	 *
	 * @param slaCalendarMaster the sla calendar master
	 */
	@Override
	public void cacheResult(SlaCalendarMaster slaCalendarMaster) {
		entityCache.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey(),
			slaCalendarMaster);

		slaCalendarMaster.resetOriginalValues();
	}

	/**
	 * Caches the sla calendar masters in the entity cache if it is enabled.
	 *
	 * @param slaCalendarMasters the sla calendar masters
	 */
	@Override
	public void cacheResult(List<SlaCalendarMaster> slaCalendarMasters) {
		for (SlaCalendarMaster slaCalendarMaster : slaCalendarMasters) {
			if (entityCache.getResult(
						SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
						SlaCalendarMasterImpl.class,
						slaCalendarMaster.getPrimaryKey()) == null) {
				cacheResult(slaCalendarMaster);
			}
			else {
				slaCalendarMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sla calendar masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SlaCalendarMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sla calendar master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SlaCalendarMaster slaCalendarMaster) {
		entityCache.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SlaCalendarMaster> slaCalendarMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SlaCalendarMaster slaCalendarMaster : slaCalendarMasters) {
			entityCache.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
				SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sla calendar master with the primary key. Does not add the sla calendar master to the database.
	 *
	 * @param slaCalendarMasterSid the primary key for the new sla calendar master
	 * @return the new sla calendar master
	 */
	@Override
	public SlaCalendarMaster create(int slaCalendarMasterSid) {
		SlaCalendarMaster slaCalendarMaster = new SlaCalendarMasterImpl();

		slaCalendarMaster.setNew(true);
		slaCalendarMaster.setPrimaryKey(slaCalendarMasterSid);

		return slaCalendarMaster;
	}

	/**
	 * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slaCalendarMasterSid the primary key of the sla calendar master
	 * @return the sla calendar master that was removed
	 * @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster remove(int slaCalendarMasterSid)
		throws NoSuchSlaCalendarMasterException {
		return remove((Serializable)slaCalendarMasterSid);
	}

	/**
	 * Removes the sla calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sla calendar master
	 * @return the sla calendar master that was removed
	 * @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster remove(Serializable primaryKey)
		throws NoSuchSlaCalendarMasterException {
		Session session = null;

		try {
			session = openSession();

			SlaCalendarMaster slaCalendarMaster = (SlaCalendarMaster)session.get(SlaCalendarMasterImpl.class,
					primaryKey);

			if (slaCalendarMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSlaCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(slaCalendarMaster);
		}
		catch (NoSuchSlaCalendarMasterException nsee) {
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
	protected SlaCalendarMaster removeImpl(SlaCalendarMaster slaCalendarMaster) {
		slaCalendarMaster = toUnwrappedModel(slaCalendarMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(slaCalendarMaster)) {
				slaCalendarMaster = (SlaCalendarMaster)session.get(SlaCalendarMasterImpl.class,
						slaCalendarMaster.getPrimaryKeyObj());
			}

			if (slaCalendarMaster != null) {
				session.delete(slaCalendarMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (slaCalendarMaster != null) {
			clearCache(slaCalendarMaster);
		}

		return slaCalendarMaster;
	}

	@Override
	public SlaCalendarMaster updateImpl(SlaCalendarMaster slaCalendarMaster) {
		slaCalendarMaster = toUnwrappedModel(slaCalendarMaster);

		boolean isNew = slaCalendarMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (slaCalendarMaster.isNew()) {
				session.save(slaCalendarMaster);

				slaCalendarMaster.setNew(false);
			}
			else {
				slaCalendarMaster = (SlaCalendarMaster)session.merge(slaCalendarMaster);
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

		entityCache.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarMasterImpl.class, slaCalendarMaster.getPrimaryKey(),
			slaCalendarMaster, false);

		slaCalendarMaster.resetOriginalValues();

		return slaCalendarMaster;
	}

	protected SlaCalendarMaster toUnwrappedModel(
		SlaCalendarMaster slaCalendarMaster) {
		if (slaCalendarMaster instanceof SlaCalendarMasterImpl) {
			return slaCalendarMaster;
		}

		SlaCalendarMasterImpl slaCalendarMasterImpl = new SlaCalendarMasterImpl();

		slaCalendarMasterImpl.setNew(slaCalendarMaster.isNew());
		slaCalendarMasterImpl.setPrimaryKey(slaCalendarMaster.getPrimaryKey());

		slaCalendarMasterImpl.setCreatedBy(slaCalendarMaster.getCreatedBy());
		slaCalendarMasterImpl.setModifiedBy(slaCalendarMaster.getModifiedBy());
		slaCalendarMasterImpl.setSlaCalendarMasterSid(slaCalendarMaster.getSlaCalendarMasterSid());
		slaCalendarMasterImpl.setCreatedDate(slaCalendarMaster.getCreatedDate());
		slaCalendarMasterImpl.setDefaultHolidays(slaCalendarMaster.isDefaultHolidays());
		slaCalendarMasterImpl.setCalendarName(slaCalendarMaster.getCalendarName());
		slaCalendarMasterImpl.setModifiedDate(slaCalendarMaster.getModifiedDate());
		slaCalendarMasterImpl.setInboundStatus(slaCalendarMaster.getInboundStatus());

		return slaCalendarMasterImpl;
	}

	/**
	 * Returns the sla calendar master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sla calendar master
	 * @return the sla calendar master
	 * @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSlaCalendarMasterException {
		SlaCalendarMaster slaCalendarMaster = fetchByPrimaryKey(primaryKey);

		if (slaCalendarMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSlaCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return slaCalendarMaster;
	}

	/**
	 * Returns the sla calendar master with the primary key or throws a {@link NoSuchSlaCalendarMasterException} if it could not be found.
	 *
	 * @param slaCalendarMasterSid the primary key of the sla calendar master
	 * @return the sla calendar master
	 * @throws NoSuchSlaCalendarMasterException if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster findByPrimaryKey(int slaCalendarMasterSid)
		throws NoSuchSlaCalendarMasterException {
		return findByPrimaryKey((Serializable)slaCalendarMasterSid);
	}

	/**
	 * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sla calendar master
	 * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
				SlaCalendarMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SlaCalendarMaster slaCalendarMaster = (SlaCalendarMaster)serializable;

		if (slaCalendarMaster == null) {
			Session session = null;

			try {
				session = openSession();

				slaCalendarMaster = (SlaCalendarMaster)session.get(SlaCalendarMasterImpl.class,
						primaryKey);

				if (slaCalendarMaster != null) {
					cacheResult(slaCalendarMaster);
				}
				else {
					entityCache.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
						SlaCalendarMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return slaCalendarMaster;
	}

	/**
	 * Returns the sla calendar master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slaCalendarMasterSid the primary key of the sla calendar master
	 * @return the sla calendar master, or <code>null</code> if a sla calendar master with the primary key could not be found
	 */
	@Override
	public SlaCalendarMaster fetchByPrimaryKey(int slaCalendarMasterSid) {
		return fetchByPrimaryKey((Serializable)slaCalendarMasterSid);
	}

	@Override
	public Map<Serializable, SlaCalendarMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SlaCalendarMaster> map = new HashMap<Serializable, SlaCalendarMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SlaCalendarMaster slaCalendarMaster = fetchByPrimaryKey(primaryKey);

			if (slaCalendarMaster != null) {
				map.put(primaryKey, slaCalendarMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SlaCalendarMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SLACALENDARMASTER_WHERE_PKS_IN);

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

			for (SlaCalendarMaster slaCalendarMaster : (List<SlaCalendarMaster>)q.list()) {
				map.put(slaCalendarMaster.getPrimaryKeyObj(), slaCalendarMaster);

				cacheResult(slaCalendarMaster);

				uncachedPrimaryKeys.remove(slaCalendarMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SlaCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the sla calendar masters.
	 *
	 * @return the sla calendar masters
	 */
	@Override
	public List<SlaCalendarMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sla calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar masters
	 * @param end the upper bound of the range of sla calendar masters (not inclusive)
	 * @return the range of sla calendar masters
	 */
	@Override
	public List<SlaCalendarMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sla calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar masters
	 * @param end the upper bound of the range of sla calendar masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sla calendar masters
	 */
	@Override
	public List<SlaCalendarMaster> findAll(int start, int end,
		OrderByComparator<SlaCalendarMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sla calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar masters
	 * @param end the upper bound of the range of sla calendar masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sla calendar masters
	 */
	@Override
	public List<SlaCalendarMaster> findAll(int start, int end,
		OrderByComparator<SlaCalendarMaster> orderByComparator,
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

		List<SlaCalendarMaster> list = null;

		if (retrieveFromCache) {
			list = (List<SlaCalendarMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SLACALENDARMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SLACALENDARMASTER;

				if (pagination) {
					sql = sql.concat(SlaCalendarMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SlaCalendarMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SlaCalendarMaster>)QueryUtil.list(q,
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
	 * Removes all the sla calendar masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SlaCalendarMaster slaCalendarMaster : findAll()) {
			remove(slaCalendarMaster);
		}
	}

	/**
	 * Returns the number of sla calendar masters.
	 *
	 * @return the number of sla calendar masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SLACALENDARMASTER);

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
		return SlaCalendarMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sla calendar master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SlaCalendarMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SLACALENDARMASTER = "SELECT slaCalendarMaster FROM SlaCalendarMaster slaCalendarMaster";
	private static final String _SQL_SELECT_SLACALENDARMASTER_WHERE_PKS_IN = "SELECT slaCalendarMaster FROM SlaCalendarMaster slaCalendarMaster WHERE SLA_CALENDAR_MASTER_SID IN (";
	private static final String _SQL_COUNT_SLACALENDARMASTER = "SELECT COUNT(slaCalendarMaster) FROM SlaCalendarMaster slaCalendarMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "slaCalendarMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SlaCalendarMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SlaCalendarMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "modifiedBy", "slaCalendarMasterSid", "createdDate",
				"defaultHolidays", "calendarName", "modifiedDate",
				"inboundStatus"
			});
}