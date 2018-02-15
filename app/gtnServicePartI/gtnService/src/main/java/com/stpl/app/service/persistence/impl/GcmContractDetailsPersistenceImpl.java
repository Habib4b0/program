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

import com.stpl.app.exception.NoSuchGcmContractDetailsException;
import com.stpl.app.model.GcmContractDetails;
import com.stpl.app.model.impl.GcmContractDetailsImpl;
import com.stpl.app.model.impl.GcmContractDetailsModelImpl;
import com.stpl.app.service.persistence.GcmContractDetailsPersistence;

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
 * The persistence implementation for the gcm contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmContractDetailsPersistence
 * @see com.stpl.app.service.persistence.GcmContractDetailsUtil
 * @generated
 */
@ProviderType
public class GcmContractDetailsPersistenceImpl extends BasePersistenceImpl<GcmContractDetails>
	implements GcmContractDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GcmContractDetailsUtil} to access the gcm contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GcmContractDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GcmContractDetailsPersistenceImpl() {
		setModelClass(GcmContractDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("paymentMethod", "PAYMENT_METHOD");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("paymentFrequency", "PAYMENT_FREQUENCY");
			dbColumnNames.put("gcmContractDetailsSid",
				"GCM_CONTRACT_DETAILS_SID");
			dbColumnNames.put("componentId", "COMPONENT_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("componentName", "COMPONENT_NAME");
			dbColumnNames.put("rsCalendar", "RS_CALENDAR");
			dbColumnNames.put("fileName", "FILE_NAME");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("planLevel", "PLAN_LEVEL");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("componentNo", "COMPONENT_NO");
			dbColumnNames.put("programType", "PROGRAM_TYPE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("componentStatus", "COMPONENT_STATUS");
			dbColumnNames.put("componentType", "COMPONENT_TYPE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the gcm contract details in the entity cache if it is enabled.
	 *
	 * @param gcmContractDetails the gcm contract details
	 */
	@Override
	public void cacheResult(GcmContractDetails gcmContractDetails) {
		entityCache.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey(),
			gcmContractDetails);

		gcmContractDetails.resetOriginalValues();
	}

	/**
	 * Caches the gcm contract detailses in the entity cache if it is enabled.
	 *
	 * @param gcmContractDetailses the gcm contract detailses
	 */
	@Override
	public void cacheResult(List<GcmContractDetails> gcmContractDetailses) {
		for (GcmContractDetails gcmContractDetails : gcmContractDetailses) {
			if (entityCache.getResult(
						GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmContractDetailsImpl.class,
						gcmContractDetails.getPrimaryKey()) == null) {
				cacheResult(gcmContractDetails);
			}
			else {
				gcmContractDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gcm contract detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GcmContractDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gcm contract details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GcmContractDetails gcmContractDetails) {
		entityCache.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GcmContractDetails> gcmContractDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GcmContractDetails gcmContractDetails : gcmContractDetailses) {
			entityCache.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
	 *
	 * @param gcmContractDetailsSid the primary key for the new gcm contract details
	 * @return the new gcm contract details
	 */
	@Override
	public GcmContractDetails create(int gcmContractDetailsSid) {
		GcmContractDetails gcmContractDetails = new GcmContractDetailsImpl();

		gcmContractDetails.setNew(true);
		gcmContractDetails.setPrimaryKey(gcmContractDetailsSid);

		return gcmContractDetails;
	}

	/**
	 * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gcmContractDetailsSid the primary key of the gcm contract details
	 * @return the gcm contract details that was removed
	 * @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails remove(int gcmContractDetailsSid)
		throws NoSuchGcmContractDetailsException {
		return remove((Serializable)gcmContractDetailsSid);
	}

	/**
	 * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gcm contract details
	 * @return the gcm contract details that was removed
	 * @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails remove(Serializable primaryKey)
		throws NoSuchGcmContractDetailsException {
		Session session = null;

		try {
			session = openSession();

			GcmContractDetails gcmContractDetails = (GcmContractDetails)session.get(GcmContractDetailsImpl.class,
					primaryKey);

			if (gcmContractDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGcmContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gcmContractDetails);
		}
		catch (NoSuchGcmContractDetailsException nsee) {
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
	protected GcmContractDetails removeImpl(
		GcmContractDetails gcmContractDetails) {
		gcmContractDetails = toUnwrappedModel(gcmContractDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gcmContractDetails)) {
				gcmContractDetails = (GcmContractDetails)session.get(GcmContractDetailsImpl.class,
						gcmContractDetails.getPrimaryKeyObj());
			}

			if (gcmContractDetails != null) {
				session.delete(gcmContractDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gcmContractDetails != null) {
			clearCache(gcmContractDetails);
		}

		return gcmContractDetails;
	}

	@Override
	public GcmContractDetails updateImpl(GcmContractDetails gcmContractDetails) {
		gcmContractDetails = toUnwrappedModel(gcmContractDetails);

		boolean isNew = gcmContractDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (gcmContractDetails.isNew()) {
				session.save(gcmContractDetails);

				gcmContractDetails.setNew(false);
			}
			else {
				gcmContractDetails = (GcmContractDetails)session.merge(gcmContractDetails);
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

		entityCache.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmContractDetailsImpl.class, gcmContractDetails.getPrimaryKey(),
			gcmContractDetails, false);

		gcmContractDetails.resetOriginalValues();

		return gcmContractDetails;
	}

	protected GcmContractDetails toUnwrappedModel(
		GcmContractDetails gcmContractDetails) {
		if (gcmContractDetails instanceof GcmContractDetailsImpl) {
			return gcmContractDetails;
		}

		GcmContractDetailsImpl gcmContractDetailsImpl = new GcmContractDetailsImpl();

		gcmContractDetailsImpl.setNew(gcmContractDetails.isNew());
		gcmContractDetailsImpl.setPrimaryKey(gcmContractDetails.getPrimaryKey());

		gcmContractDetailsImpl.setPaymentMethod(gcmContractDetails.getPaymentMethod());
		gcmContractDetailsImpl.setUserId(gcmContractDetails.getUserId());
		gcmContractDetailsImpl.setEndDate(gcmContractDetails.getEndDate());
		gcmContractDetailsImpl.setPaymentFrequency(gcmContractDetails.getPaymentFrequency());
		gcmContractDetailsImpl.setGcmContractDetailsSid(gcmContractDetails.getGcmContractDetailsSid());
		gcmContractDetailsImpl.setComponentId(gcmContractDetails.getComponentId());
		gcmContractDetailsImpl.setModifiedDate(gcmContractDetails.getModifiedDate());
		gcmContractDetailsImpl.setComponentName(gcmContractDetails.getComponentName());
		gcmContractDetailsImpl.setRsCalendar(gcmContractDetails.getRsCalendar());
		gcmContractDetailsImpl.setFileName(gcmContractDetails.getFileName());
		gcmContractDetailsImpl.setStartDate(gcmContractDetails.getStartDate());
		gcmContractDetailsImpl.setPlanLevel(gcmContractDetails.getPlanLevel());
		gcmContractDetailsImpl.setCreatedDate(gcmContractDetails.getCreatedDate());
		gcmContractDetailsImpl.setCreatedBy(gcmContractDetails.getCreatedBy());
		gcmContractDetailsImpl.setComponentNo(gcmContractDetails.getComponentNo());
		gcmContractDetailsImpl.setProgramType(gcmContractDetails.getProgramType());
		gcmContractDetailsImpl.setSessionId(gcmContractDetails.getSessionId());
		gcmContractDetailsImpl.setModifiedBy(gcmContractDetails.getModifiedBy());
		gcmContractDetailsImpl.setComponentStatus(gcmContractDetails.getComponentStatus());
		gcmContractDetailsImpl.setComponentType(gcmContractDetails.getComponentType());

		return gcmContractDetailsImpl;
	}

	/**
	 * Returns the gcm contract details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm contract details
	 * @return the gcm contract details
	 * @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGcmContractDetailsException {
		GcmContractDetails gcmContractDetails = fetchByPrimaryKey(primaryKey);

		if (gcmContractDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGcmContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gcmContractDetails;
	}

	/**
	 * Returns the gcm contract details with the primary key or throws a {@link NoSuchGcmContractDetailsException} if it could not be found.
	 *
	 * @param gcmContractDetailsSid the primary key of the gcm contract details
	 * @return the gcm contract details
	 * @throws NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails findByPrimaryKey(int gcmContractDetailsSid)
		throws NoSuchGcmContractDetailsException {
		return findByPrimaryKey((Serializable)gcmContractDetailsSid);
	}

	/**
	 * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm contract details
	 * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmContractDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GcmContractDetails gcmContractDetails = (GcmContractDetails)serializable;

		if (gcmContractDetails == null) {
			Session session = null;

			try {
				session = openSession();

				gcmContractDetails = (GcmContractDetails)session.get(GcmContractDetailsImpl.class,
						primaryKey);

				if (gcmContractDetails != null) {
					cacheResult(gcmContractDetails);
				}
				else {
					entityCache.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmContractDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmContractDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gcmContractDetails;
	}

	/**
	 * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gcmContractDetailsSid the primary key of the gcm contract details
	 * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
	 */
	@Override
	public GcmContractDetails fetchByPrimaryKey(int gcmContractDetailsSid) {
		return fetchByPrimaryKey((Serializable)gcmContractDetailsSid);
	}

	@Override
	public Map<Serializable, GcmContractDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GcmContractDetails> map = new HashMap<Serializable, GcmContractDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GcmContractDetails gcmContractDetails = fetchByPrimaryKey(primaryKey);

			if (gcmContractDetails != null) {
				map.put(primaryKey, gcmContractDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmContractDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GcmContractDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GCMCONTRACTDETAILS_WHERE_PKS_IN);

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

			for (GcmContractDetails gcmContractDetails : (List<GcmContractDetails>)q.list()) {
				map.put(gcmContractDetails.getPrimaryKeyObj(),
					gcmContractDetails);

				cacheResult(gcmContractDetails);

				uncachedPrimaryKeys.remove(gcmContractDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GcmContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmContractDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the gcm contract detailses.
	 *
	 * @return the gcm contract detailses
	 */
	@Override
	public List<GcmContractDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gcm contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm contract detailses
	 * @param end the upper bound of the range of gcm contract detailses (not inclusive)
	 * @return the range of gcm contract detailses
	 */
	@Override
	public List<GcmContractDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gcm contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm contract detailses
	 * @param end the upper bound of the range of gcm contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gcm contract detailses
	 */
	@Override
	public List<GcmContractDetails> findAll(int start, int end,
		OrderByComparator<GcmContractDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gcm contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm contract detailses
	 * @param end the upper bound of the range of gcm contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of gcm contract detailses
	 */
	@Override
	public List<GcmContractDetails> findAll(int start, int end,
		OrderByComparator<GcmContractDetails> orderByComparator,
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

		List<GcmContractDetails> list = null;

		if (retrieveFromCache) {
			list = (List<GcmContractDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GCMCONTRACTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GCMCONTRACTDETAILS;

				if (pagination) {
					sql = sql.concat(GcmContractDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GcmContractDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GcmContractDetails>)QueryUtil.list(q,
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
	 * Removes all the gcm contract detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GcmContractDetails gcmContractDetails : findAll()) {
			remove(gcmContractDetails);
		}
	}

	/**
	 * Returns the number of gcm contract detailses.
	 *
	 * @return the number of gcm contract detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GCMCONTRACTDETAILS);

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
		return GcmContractDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gcm contract details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GcmContractDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GCMCONTRACTDETAILS = "SELECT gcmContractDetails FROM GcmContractDetails gcmContractDetails";
	private static final String _SQL_SELECT_GCMCONTRACTDETAILS_WHERE_PKS_IN = "SELECT gcmContractDetails FROM GcmContractDetails gcmContractDetails WHERE GCM_CONTRACT_DETAILS_SID IN (";
	private static final String _SQL_COUNT_GCMCONTRACTDETAILS = "SELECT COUNT(gcmContractDetails) FROM GcmContractDetails gcmContractDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gcmContractDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmContractDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GcmContractDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"paymentMethod", "userId", "endDate", "paymentFrequency",
				"gcmContractDetailsSid", "componentId", "modifiedDate",
				"componentName", "rsCalendar", "fileName", "startDate",
				"planLevel", "createdDate", "createdBy", "componentNo",
				"programType", "sessionId", "modifiedBy", "componentStatus",
				"componentType"
			});
}