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

import com.stpl.app.parttwo.exception.NoSuchSlaCalendarDetailsException;
import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.model.impl.SlaCalendarDetailsImpl;
import com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsPersistence;

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
 * The persistence implementation for the sla calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.SlaCalendarDetailsUtil
 * @generated
 */
@ProviderType
public class SlaCalendarDetailsPersistenceImpl extends BasePersistenceImpl<SlaCalendarDetails>
	implements SlaCalendarDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SlaCalendarDetailsUtil} to access the sla calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SlaCalendarDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			SlaCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			SlaCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SlaCalendarDetailsPersistenceImpl() {
		setModelClass(SlaCalendarDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("slaCalendarMasterSid", "SLA_CALENDAR_MASTER_SID");
			dbColumnNames.put("holidayYear", "HOLIDAY_YEAR");
			dbColumnNames.put("slaCalendarDetailsSid",
				"SLA_CALENDAR_DETAILS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("holidayDay", "HOLIDAY_DAY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("holidayCombined", "HOLIDAY_COMBINED");
			dbColumnNames.put("holidayMonth", "HOLIDAY_MONTH");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the sla calendar details in the entity cache if it is enabled.
	 *
	 * @param slaCalendarDetails the sla calendar details
	 */
	@Override
	public void cacheResult(SlaCalendarDetails slaCalendarDetails) {
		entityCache.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey(),
			slaCalendarDetails);

		slaCalendarDetails.resetOriginalValues();
	}

	/**
	 * Caches the sla calendar detailses in the entity cache if it is enabled.
	 *
	 * @param slaCalendarDetailses the sla calendar detailses
	 */
	@Override
	public void cacheResult(List<SlaCalendarDetails> slaCalendarDetailses) {
		for (SlaCalendarDetails slaCalendarDetails : slaCalendarDetailses) {
			if (entityCache.getResult(
						SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						SlaCalendarDetailsImpl.class,
						slaCalendarDetails.getPrimaryKey()) == null) {
				cacheResult(slaCalendarDetails);
			}
			else {
				slaCalendarDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sla calendar detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SlaCalendarDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sla calendar details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SlaCalendarDetails slaCalendarDetails) {
		entityCache.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SlaCalendarDetails> slaCalendarDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SlaCalendarDetails slaCalendarDetails : slaCalendarDetailses) {
			entityCache.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
	 *
	 * @param slaCalendarDetailsSid the primary key for the new sla calendar details
	 * @return the new sla calendar details
	 */
	@Override
	public SlaCalendarDetails create(int slaCalendarDetailsSid) {
		SlaCalendarDetails slaCalendarDetails = new SlaCalendarDetailsImpl();

		slaCalendarDetails.setNew(true);
		slaCalendarDetails.setPrimaryKey(slaCalendarDetailsSid);

		return slaCalendarDetails;
	}

	/**
	 * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param slaCalendarDetailsSid the primary key of the sla calendar details
	 * @return the sla calendar details that was removed
	 * @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails remove(int slaCalendarDetailsSid)
		throws NoSuchSlaCalendarDetailsException {
		return remove((Serializable)slaCalendarDetailsSid);
	}

	/**
	 * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sla calendar details
	 * @return the sla calendar details that was removed
	 * @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails remove(Serializable primaryKey)
		throws NoSuchSlaCalendarDetailsException {
		Session session = null;

		try {
			session = openSession();

			SlaCalendarDetails slaCalendarDetails = (SlaCalendarDetails)session.get(SlaCalendarDetailsImpl.class,
					primaryKey);

			if (slaCalendarDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSlaCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(slaCalendarDetails);
		}
		catch (NoSuchSlaCalendarDetailsException nsee) {
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
	protected SlaCalendarDetails removeImpl(
		SlaCalendarDetails slaCalendarDetails) {
		slaCalendarDetails = toUnwrappedModel(slaCalendarDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(slaCalendarDetails)) {
				slaCalendarDetails = (SlaCalendarDetails)session.get(SlaCalendarDetailsImpl.class,
						slaCalendarDetails.getPrimaryKeyObj());
			}

			if (slaCalendarDetails != null) {
				session.delete(slaCalendarDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (slaCalendarDetails != null) {
			clearCache(slaCalendarDetails);
		}

		return slaCalendarDetails;
	}

	@Override
	public SlaCalendarDetails updateImpl(SlaCalendarDetails slaCalendarDetails) {
		slaCalendarDetails = toUnwrappedModel(slaCalendarDetails);

		boolean isNew = slaCalendarDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (slaCalendarDetails.isNew()) {
				session.save(slaCalendarDetails);

				slaCalendarDetails.setNew(false);
			}
			else {
				slaCalendarDetails = (SlaCalendarDetails)session.merge(slaCalendarDetails);
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

		entityCache.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SlaCalendarDetailsImpl.class, slaCalendarDetails.getPrimaryKey(),
			slaCalendarDetails, false);

		slaCalendarDetails.resetOriginalValues();

		return slaCalendarDetails;
	}

	protected SlaCalendarDetails toUnwrappedModel(
		SlaCalendarDetails slaCalendarDetails) {
		if (slaCalendarDetails instanceof SlaCalendarDetailsImpl) {
			return slaCalendarDetails;
		}

		SlaCalendarDetailsImpl slaCalendarDetailsImpl = new SlaCalendarDetailsImpl();

		slaCalendarDetailsImpl.setNew(slaCalendarDetails.isNew());
		slaCalendarDetailsImpl.setPrimaryKey(slaCalendarDetails.getPrimaryKey());

		slaCalendarDetailsImpl.setCreatedDate(slaCalendarDetails.getCreatedDate());
		slaCalendarDetailsImpl.setCreatedBy(slaCalendarDetails.getCreatedBy());
		slaCalendarDetailsImpl.setSlaCalendarMasterSid(slaCalendarDetails.getSlaCalendarMasterSid());
		slaCalendarDetailsImpl.setHolidayYear(slaCalendarDetails.getHolidayYear());
		slaCalendarDetailsImpl.setSlaCalendarDetailsSid(slaCalendarDetails.getSlaCalendarDetailsSid());
		slaCalendarDetailsImpl.setModifiedBy(slaCalendarDetails.getModifiedBy());
		slaCalendarDetailsImpl.setInboundStatus(slaCalendarDetails.getInboundStatus());
		slaCalendarDetailsImpl.setHolidayDay(slaCalendarDetails.getHolidayDay());
		slaCalendarDetailsImpl.setModifiedDate(slaCalendarDetails.getModifiedDate());
		slaCalendarDetailsImpl.setHolidayCombined(slaCalendarDetails.getHolidayCombined());
		slaCalendarDetailsImpl.setHolidayMonth(slaCalendarDetails.getHolidayMonth());

		return slaCalendarDetailsImpl;
	}

	/**
	 * Returns the sla calendar details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sla calendar details
	 * @return the sla calendar details
	 * @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSlaCalendarDetailsException {
		SlaCalendarDetails slaCalendarDetails = fetchByPrimaryKey(primaryKey);

		if (slaCalendarDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSlaCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return slaCalendarDetails;
	}

	/**
	 * Returns the sla calendar details with the primary key or throws a {@link NoSuchSlaCalendarDetailsException} if it could not be found.
	 *
	 * @param slaCalendarDetailsSid the primary key of the sla calendar details
	 * @return the sla calendar details
	 * @throws NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails findByPrimaryKey(int slaCalendarDetailsSid)
		throws NoSuchSlaCalendarDetailsException {
		return findByPrimaryKey((Serializable)slaCalendarDetailsSid);
	}

	/**
	 * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sla calendar details
	 * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				SlaCalendarDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SlaCalendarDetails slaCalendarDetails = (SlaCalendarDetails)serializable;

		if (slaCalendarDetails == null) {
			Session session = null;

			try {
				session = openSession();

				slaCalendarDetails = (SlaCalendarDetails)session.get(SlaCalendarDetailsImpl.class,
						primaryKey);

				if (slaCalendarDetails != null) {
					cacheResult(slaCalendarDetails);
				}
				else {
					entityCache.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						SlaCalendarDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return slaCalendarDetails;
	}

	/**
	 * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param slaCalendarDetailsSid the primary key of the sla calendar details
	 * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
	 */
	@Override
	public SlaCalendarDetails fetchByPrimaryKey(int slaCalendarDetailsSid) {
		return fetchByPrimaryKey((Serializable)slaCalendarDetailsSid);
	}

	@Override
	public Map<Serializable, SlaCalendarDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SlaCalendarDetails> map = new HashMap<Serializable, SlaCalendarDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SlaCalendarDetails slaCalendarDetails = fetchByPrimaryKey(primaryKey);

			if (slaCalendarDetails != null) {
				map.put(primaryKey, slaCalendarDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SlaCalendarDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SLACALENDARDETAILS_WHERE_PKS_IN);

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

			for (SlaCalendarDetails slaCalendarDetails : (List<SlaCalendarDetails>)q.list()) {
				map.put(slaCalendarDetails.getPrimaryKeyObj(),
					slaCalendarDetails);

				cacheResult(slaCalendarDetails);

				uncachedPrimaryKeys.remove(slaCalendarDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SlaCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SlaCalendarDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the sla calendar detailses.
	 *
	 * @return the sla calendar detailses
	 */
	@Override
	public List<SlaCalendarDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sla calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar detailses
	 * @param end the upper bound of the range of sla calendar detailses (not inclusive)
	 * @return the range of sla calendar detailses
	 */
	@Override
	public List<SlaCalendarDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sla calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar detailses
	 * @param end the upper bound of the range of sla calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sla calendar detailses
	 */
	@Override
	public List<SlaCalendarDetails> findAll(int start, int end,
		OrderByComparator<SlaCalendarDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sla calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sla calendar detailses
	 * @param end the upper bound of the range of sla calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sla calendar detailses
	 */
	@Override
	public List<SlaCalendarDetails> findAll(int start, int end,
		OrderByComparator<SlaCalendarDetails> orderByComparator,
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

		List<SlaCalendarDetails> list = null;

		if (retrieveFromCache) {
			list = (List<SlaCalendarDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SLACALENDARDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SLACALENDARDETAILS;

				if (pagination) {
					sql = sql.concat(SlaCalendarDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SlaCalendarDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SlaCalendarDetails>)QueryUtil.list(q,
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
	 * Removes all the sla calendar detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SlaCalendarDetails slaCalendarDetails : findAll()) {
			remove(slaCalendarDetails);
		}
	}

	/**
	 * Returns the number of sla calendar detailses.
	 *
	 * @return the number of sla calendar detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SLACALENDARDETAILS);

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
		return SlaCalendarDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sla calendar details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SlaCalendarDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SLACALENDARDETAILS = "SELECT slaCalendarDetails FROM SlaCalendarDetails slaCalendarDetails";
	private static final String _SQL_SELECT_SLACALENDARDETAILS_WHERE_PKS_IN = "SELECT slaCalendarDetails FROM SlaCalendarDetails slaCalendarDetails WHERE SLA_CALENDAR_DETAILS_SID IN (";
	private static final String _SQL_COUNT_SLACALENDARDETAILS = "SELECT COUNT(slaCalendarDetails) FROM SlaCalendarDetails slaCalendarDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "slaCalendarDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SlaCalendarDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SlaCalendarDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "createdBy", "slaCalendarMasterSid",
				"holidayYear", "slaCalendarDetailsSid", "modifiedBy",
				"inboundStatus", "holidayDay", "modifiedDate", "holidayCombined",
				"holidayMonth"
			});
}