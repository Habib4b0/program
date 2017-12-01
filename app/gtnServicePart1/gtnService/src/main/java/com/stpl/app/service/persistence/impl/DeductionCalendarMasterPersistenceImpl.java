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

import com.stpl.app.exception.NoSuchDeductionCalendarMasterException;
import com.stpl.app.model.DeductionCalendarMaster;
import com.stpl.app.model.impl.DeductionCalendarMasterImpl;
import com.stpl.app.model.impl.DeductionCalendarMasterModelImpl;
import com.stpl.app.service.persistence.DeductionCalendarMasterPersistence;

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
 * The persistence implementation for the deduction calendar master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarMasterPersistence
 * @see com.stpl.app.service.persistence.DeductionCalendarMasterUtil
 * @generated
 */
@ProviderType
public class DeductionCalendarMasterPersistenceImpl extends BasePersistenceImpl<DeductionCalendarMaster>
	implements DeductionCalendarMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeductionCalendarMasterUtil} to access the deduction calendar master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeductionCalendarMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
			DeductionCalendarMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED,
			DeductionCalendarMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DeductionCalendarMasterPersistenceImpl() {
		setModelClass(DeductionCalendarMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("deductionCalendarNo", "DEDUCTION_CALENDAR_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("category", "CATEGORY");
			dbColumnNames.put("additionalNotes", "ADDITIONAL_NOTES");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("deductionCalendarName", "DEDUCTION_CALENDAR_NAME");
			dbColumnNames.put("deductionCalendarDesc", "DEDUCTION_CALENDAR_DESC");
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
	 * Caches the deduction calendar master in the entity cache if it is enabled.
	 *
	 * @param deductionCalendarMaster the deduction calendar master
	 */
	@Override
	public void cacheResult(DeductionCalendarMaster deductionCalendarMaster) {
		entityCache.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterImpl.class,
			deductionCalendarMaster.getPrimaryKey(), deductionCalendarMaster);

		deductionCalendarMaster.resetOriginalValues();
	}

	/**
	 * Caches the deduction calendar masters in the entity cache if it is enabled.
	 *
	 * @param deductionCalendarMasters the deduction calendar masters
	 */
	@Override
	public void cacheResult(
		List<DeductionCalendarMaster> deductionCalendarMasters) {
		for (DeductionCalendarMaster deductionCalendarMaster : deductionCalendarMasters) {
			if (entityCache.getResult(
						DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
						DeductionCalendarMasterImpl.class,
						deductionCalendarMaster.getPrimaryKey()) == null) {
				cacheResult(deductionCalendarMaster);
			}
			else {
				deductionCalendarMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deduction calendar masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeductionCalendarMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deduction calendar master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeductionCalendarMaster deductionCalendarMaster) {
		entityCache.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterImpl.class,
			deductionCalendarMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<DeductionCalendarMaster> deductionCalendarMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeductionCalendarMaster deductionCalendarMaster : deductionCalendarMasters) {
			entityCache.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
				DeductionCalendarMasterImpl.class,
				deductionCalendarMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new deduction calendar master with the primary key. Does not add the deduction calendar master to the database.
	 *
	 * @param deductionCalendarMasterSid the primary key for the new deduction calendar master
	 * @return the new deduction calendar master
	 */
	@Override
	public DeductionCalendarMaster create(int deductionCalendarMasterSid) {
		DeductionCalendarMaster deductionCalendarMaster = new DeductionCalendarMasterImpl();

		deductionCalendarMaster.setNew(true);
		deductionCalendarMaster.setPrimaryKey(deductionCalendarMasterSid);

		return deductionCalendarMaster;
	}

	/**
	 * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deductionCalendarMasterSid the primary key of the deduction calendar master
	 * @return the deduction calendar master that was removed
	 * @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster remove(int deductionCalendarMasterSid)
		throws NoSuchDeductionCalendarMasterException {
		return remove((Serializable)deductionCalendarMasterSid);
	}

	/**
	 * Removes the deduction calendar master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deduction calendar master
	 * @return the deduction calendar master that was removed
	 * @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster remove(Serializable primaryKey)
		throws NoSuchDeductionCalendarMasterException {
		Session session = null;

		try {
			session = openSession();

			DeductionCalendarMaster deductionCalendarMaster = (DeductionCalendarMaster)session.get(DeductionCalendarMasterImpl.class,
					primaryKey);

			if (deductionCalendarMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeductionCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deductionCalendarMaster);
		}
		catch (NoSuchDeductionCalendarMasterException nsee) {
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
	protected DeductionCalendarMaster removeImpl(
		DeductionCalendarMaster deductionCalendarMaster) {
		deductionCalendarMaster = toUnwrappedModel(deductionCalendarMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deductionCalendarMaster)) {
				deductionCalendarMaster = (DeductionCalendarMaster)session.get(DeductionCalendarMasterImpl.class,
						deductionCalendarMaster.getPrimaryKeyObj());
			}

			if (deductionCalendarMaster != null) {
				session.delete(deductionCalendarMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deductionCalendarMaster != null) {
			clearCache(deductionCalendarMaster);
		}

		return deductionCalendarMaster;
	}

	@Override
	public DeductionCalendarMaster updateImpl(
		DeductionCalendarMaster deductionCalendarMaster) {
		deductionCalendarMaster = toUnwrappedModel(deductionCalendarMaster);

		boolean isNew = deductionCalendarMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (deductionCalendarMaster.isNew()) {
				session.save(deductionCalendarMaster);

				deductionCalendarMaster.setNew(false);
			}
			else {
				deductionCalendarMaster = (DeductionCalendarMaster)session.merge(deductionCalendarMaster);
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

		entityCache.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarMasterImpl.class,
			deductionCalendarMaster.getPrimaryKey(), deductionCalendarMaster,
			false);

		deductionCalendarMaster.resetOriginalValues();

		return deductionCalendarMaster;
	}

	protected DeductionCalendarMaster toUnwrappedModel(
		DeductionCalendarMaster deductionCalendarMaster) {
		if (deductionCalendarMaster instanceof DeductionCalendarMasterImpl) {
			return deductionCalendarMaster;
		}

		DeductionCalendarMasterImpl deductionCalendarMasterImpl = new DeductionCalendarMasterImpl();

		deductionCalendarMasterImpl.setNew(deductionCalendarMaster.isNew());
		deductionCalendarMasterImpl.setPrimaryKey(deductionCalendarMaster.getPrimaryKey());

		deductionCalendarMasterImpl.setCreatedBy(deductionCalendarMaster.getCreatedBy());
		deductionCalendarMasterImpl.setDeductionCalendarMasterSid(deductionCalendarMaster.getDeductionCalendarMasterSid());
		deductionCalendarMasterImpl.setDeductionCalendarNo(deductionCalendarMaster.getDeductionCalendarNo());
		deductionCalendarMasterImpl.setModifiedBy(deductionCalendarMaster.getModifiedBy());
		deductionCalendarMasterImpl.setCreatedDate(deductionCalendarMaster.getCreatedDate());
		deductionCalendarMasterImpl.setCategory(deductionCalendarMaster.getCategory());
		deductionCalendarMasterImpl.setAdditionalNotes(deductionCalendarMaster.getAdditionalNotes());
		deductionCalendarMasterImpl.setUserId(deductionCalendarMaster.getUserId());
		deductionCalendarMasterImpl.setDeductionCalendarName(deductionCalendarMaster.getDeductionCalendarName());
		deductionCalendarMasterImpl.setDeductionCalendarDesc(deductionCalendarMaster.getDeductionCalendarDesc());
		deductionCalendarMasterImpl.setModifiedDate(deductionCalendarMaster.getModifiedDate());
		deductionCalendarMasterImpl.setInboundStatus(deductionCalendarMaster.getInboundStatus());

		return deductionCalendarMasterImpl;
	}

	/**
	 * Returns the deduction calendar master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction calendar master
	 * @return the deduction calendar master
	 * @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeductionCalendarMasterException {
		DeductionCalendarMaster deductionCalendarMaster = fetchByPrimaryKey(primaryKey);

		if (deductionCalendarMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeductionCalendarMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deductionCalendarMaster;
	}

	/**
	 * Returns the deduction calendar master with the primary key or throws a {@link NoSuchDeductionCalendarMasterException} if it could not be found.
	 *
	 * @param deductionCalendarMasterSid the primary key of the deduction calendar master
	 * @return the deduction calendar master
	 * @throws NoSuchDeductionCalendarMasterException if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster findByPrimaryKey(
		int deductionCalendarMasterSid)
		throws NoSuchDeductionCalendarMasterException {
		return findByPrimaryKey((Serializable)deductionCalendarMasterSid);
	}

	/**
	 * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction calendar master
	 * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
				DeductionCalendarMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeductionCalendarMaster deductionCalendarMaster = (DeductionCalendarMaster)serializable;

		if (deductionCalendarMaster == null) {
			Session session = null;

			try {
				session = openSession();

				deductionCalendarMaster = (DeductionCalendarMaster)session.get(DeductionCalendarMasterImpl.class,
						primaryKey);

				if (deductionCalendarMaster != null) {
					cacheResult(deductionCalendarMaster);
				}
				else {
					entityCache.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
						DeductionCalendarMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					DeductionCalendarMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deductionCalendarMaster;
	}

	/**
	 * Returns the deduction calendar master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deductionCalendarMasterSid the primary key of the deduction calendar master
	 * @return the deduction calendar master, or <code>null</code> if a deduction calendar master with the primary key could not be found
	 */
	@Override
	public DeductionCalendarMaster fetchByPrimaryKey(
		int deductionCalendarMasterSid) {
		return fetchByPrimaryKey((Serializable)deductionCalendarMasterSid);
	}

	@Override
	public Map<Serializable, DeductionCalendarMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeductionCalendarMaster> map = new HashMap<Serializable, DeductionCalendarMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeductionCalendarMaster deductionCalendarMaster = fetchByPrimaryKey(primaryKey);

			if (deductionCalendarMaster != null) {
				map.put(primaryKey, deductionCalendarMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					DeductionCalendarMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeductionCalendarMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DEDUCTIONCALENDARMASTER_WHERE_PKS_IN);

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

			for (DeductionCalendarMaster deductionCalendarMaster : (List<DeductionCalendarMaster>)q.list()) {
				map.put(deductionCalendarMaster.getPrimaryKeyObj(),
					deductionCalendarMaster);

				cacheResult(deductionCalendarMaster);

				uncachedPrimaryKeys.remove(deductionCalendarMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeductionCalendarMasterModelImpl.ENTITY_CACHE_ENABLED,
					DeductionCalendarMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the deduction calendar masters.
	 *
	 * @return the deduction calendar masters
	 */
	@Override
	public List<DeductionCalendarMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deduction calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar masters
	 * @param end the upper bound of the range of deduction calendar masters (not inclusive)
	 * @return the range of deduction calendar masters
	 */
	@Override
	public List<DeductionCalendarMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deduction calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar masters
	 * @param end the upper bound of the range of deduction calendar masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deduction calendar masters
	 */
	@Override
	public List<DeductionCalendarMaster> findAll(int start, int end,
		OrderByComparator<DeductionCalendarMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deduction calendar masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar masters
	 * @param end the upper bound of the range of deduction calendar masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deduction calendar masters
	 */
	@Override
	public List<DeductionCalendarMaster> findAll(int start, int end,
		OrderByComparator<DeductionCalendarMaster> orderByComparator,
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

		List<DeductionCalendarMaster> list = null;

		if (retrieveFromCache) {
			list = (List<DeductionCalendarMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DEDUCTIONCALENDARMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEDUCTIONCALENDARMASTER;

				if (pagination) {
					sql = sql.concat(DeductionCalendarMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeductionCalendarMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeductionCalendarMaster>)QueryUtil.list(q,
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
	 * Removes all the deduction calendar masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeductionCalendarMaster deductionCalendarMaster : findAll()) {
			remove(deductionCalendarMaster);
		}
	}

	/**
	 * Returns the number of deduction calendar masters.
	 *
	 * @return the number of deduction calendar masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEDUCTIONCALENDARMASTER);

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
		return DeductionCalendarMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deduction calendar master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeductionCalendarMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DEDUCTIONCALENDARMASTER = "SELECT deductionCalendarMaster FROM DeductionCalendarMaster deductionCalendarMaster";
	private static final String _SQL_SELECT_DEDUCTIONCALENDARMASTER_WHERE_PKS_IN =
		"SELECT deductionCalendarMaster FROM DeductionCalendarMaster deductionCalendarMaster WHERE DEDUCTION_CALENDAR_MASTER_SID IN (";
	private static final String _SQL_COUNT_DEDUCTIONCALENDARMASTER = "SELECT COUNT(deductionCalendarMaster) FROM DeductionCalendarMaster deductionCalendarMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deductionCalendarMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionCalendarMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DeductionCalendarMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "deductionCalendarMasterSid", "deductionCalendarNo",
				"modifiedBy", "createdDate", "category", "additionalNotes",
				"userId", "deductionCalendarName", "deductionCalendarDesc",
				"modifiedDate", "inboundStatus"
			});
}