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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchStDeductionCalendarDetailsException;
import com.stpl.app.model.StDeductionCalendarDetails;
import com.stpl.app.model.impl.StDeductionCalendarDetailsImpl;
import com.stpl.app.model.impl.StDeductionCalendarDetailsModelImpl;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPK;
import com.stpl.app.service.persistence.StDeductionCalendarDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StDeductionCalendarDetailsPersistence
 * @see com.stpl.app.service.persistence.StDeductionCalendarDetailsUtil
 * @generated
 */
@ProviderType
public class StDeductionCalendarDetailsPersistenceImpl
	extends BasePersistenceImpl<StDeductionCalendarDetails>
	implements StDeductionCalendarDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StDeductionCalendarDetailsUtil} to access the st deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StDeductionCalendarDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			StDeductionCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			StDeductionCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public StDeductionCalendarDetailsPersistenceImpl() {
		setModelClass(StDeductionCalendarDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustmentBasis", "ADJUSTMENT_BASIS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("adjustmentValue", "ADJUSTMENT_VALUE");
			dbColumnNames.put("adjustmentAllocationMethodology",
				"ADJUSTMENT_ALLOCATION_METHODOLOGY");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("discountAmount", "DISCOUNT_AMOUNT");
			dbColumnNames.put("adjustmentVariable", "ADJUSTMENT_VARIABLE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st deduction calendar details in the entity cache if it is enabled.
	 *
	 * @param stDeductionCalendarDetails the st deduction calendar details
	 */
	@Override
	public void cacheResult(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		entityCache.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsImpl.class,
			stDeductionCalendarDetails.getPrimaryKey(),
			stDeductionCalendarDetails);

		stDeductionCalendarDetails.resetOriginalValues();
	}

	/**
	 * Caches the st deduction calendar detailses in the entity cache if it is enabled.
	 *
	 * @param stDeductionCalendarDetailses the st deduction calendar detailses
	 */
	@Override
	public void cacheResult(
		List<StDeductionCalendarDetails> stDeductionCalendarDetailses) {
		for (StDeductionCalendarDetails stDeductionCalendarDetails : stDeductionCalendarDetailses) {
			if (entityCache.getResult(
						StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						StDeductionCalendarDetailsImpl.class,
						stDeductionCalendarDetails.getPrimaryKey()) == null) {
				cacheResult(stDeductionCalendarDetails);
			}
			else {
				stDeductionCalendarDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st deduction calendar detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StDeductionCalendarDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st deduction calendar details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		entityCache.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsImpl.class,
			stDeductionCalendarDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StDeductionCalendarDetails> stDeductionCalendarDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StDeductionCalendarDetails stDeductionCalendarDetails : stDeductionCalendarDetailses) {
			entityCache.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				StDeductionCalendarDetailsImpl.class,
				stDeductionCalendarDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st deduction calendar details with the primary key. Does not add the st deduction calendar details to the database.
	 *
	 * @param stDeductionCalendarDetailsPK the primary key for the new st deduction calendar details
	 * @return the new st deduction calendar details
	 */
	@Override
	public StDeductionCalendarDetails create(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK) {
		StDeductionCalendarDetails stDeductionCalendarDetails = new StDeductionCalendarDetailsImpl();

		stDeductionCalendarDetails.setNew(true);
		stDeductionCalendarDetails.setPrimaryKey(stDeductionCalendarDetailsPK);

		return stDeductionCalendarDetails;
	}

	/**
	 * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	 * @return the st deduction calendar details that was removed
	 * @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails remove(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
		throws NoSuchStDeductionCalendarDetailsException {
		return remove((Serializable)stDeductionCalendarDetailsPK);
	}

	/**
	 * Removes the st deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st deduction calendar details
	 * @return the st deduction calendar details that was removed
	 * @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails remove(Serializable primaryKey)
		throws NoSuchStDeductionCalendarDetailsException {
		Session session = null;

		try {
			session = openSession();

			StDeductionCalendarDetails stDeductionCalendarDetails = (StDeductionCalendarDetails)session.get(StDeductionCalendarDetailsImpl.class,
					primaryKey);

			if (stDeductionCalendarDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stDeductionCalendarDetails);
		}
		catch (NoSuchStDeductionCalendarDetailsException nsee) {
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
	protected StDeductionCalendarDetails removeImpl(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		stDeductionCalendarDetails = toUnwrappedModel(stDeductionCalendarDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stDeductionCalendarDetails)) {
				stDeductionCalendarDetails = (StDeductionCalendarDetails)session.get(StDeductionCalendarDetailsImpl.class,
						stDeductionCalendarDetails.getPrimaryKeyObj());
			}

			if (stDeductionCalendarDetails != null) {
				session.delete(stDeductionCalendarDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stDeductionCalendarDetails != null) {
			clearCache(stDeductionCalendarDetails);
		}

		return stDeductionCalendarDetails;
	}

	@Override
	public StDeductionCalendarDetails updateImpl(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		stDeductionCalendarDetails = toUnwrappedModel(stDeductionCalendarDetails);

		boolean isNew = stDeductionCalendarDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stDeductionCalendarDetails.isNew()) {
				session.save(stDeductionCalendarDetails);

				stDeductionCalendarDetails.setNew(false);
			}
			else {
				stDeductionCalendarDetails = (StDeductionCalendarDetails)session.merge(stDeductionCalendarDetails);
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

		entityCache.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			StDeductionCalendarDetailsImpl.class,
			stDeductionCalendarDetails.getPrimaryKey(),
			stDeductionCalendarDetails, false);

		stDeductionCalendarDetails.resetOriginalValues();

		return stDeductionCalendarDetails;
	}

	protected StDeductionCalendarDetails toUnwrappedModel(
		StDeductionCalendarDetails stDeductionCalendarDetails) {
		if (stDeductionCalendarDetails instanceof StDeductionCalendarDetailsImpl) {
			return stDeductionCalendarDetails;
		}

		StDeductionCalendarDetailsImpl stDeductionCalendarDetailsImpl = new StDeductionCalendarDetailsImpl();

		stDeductionCalendarDetailsImpl.setNew(stDeductionCalendarDetails.isNew());
		stDeductionCalendarDetailsImpl.setPrimaryKey(stDeductionCalendarDetails.getPrimaryKey());

		stDeductionCalendarDetailsImpl.setAdjustmentBasis(stDeductionCalendarDetails.getAdjustmentBasis());
		stDeductionCalendarDetailsImpl.setPeriodSid(stDeductionCalendarDetails.getPeriodSid());
		stDeductionCalendarDetailsImpl.setAdjustmentValue(stDeductionCalendarDetails.getAdjustmentValue());
		stDeductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(stDeductionCalendarDetails.getAdjustmentAllocationMethodology());
		stDeductionCalendarDetailsImpl.setCompanyMasterSid(stDeductionCalendarDetails.getCompanyMasterSid());
		stDeductionCalendarDetailsImpl.setDiscountAmount(stDeductionCalendarDetails.getDiscountAmount());
		stDeductionCalendarDetailsImpl.setAdjustmentVariable(stDeductionCalendarDetails.getAdjustmentVariable());
		stDeductionCalendarDetailsImpl.setUserId(stDeductionCalendarDetails.getUserId());
		stDeductionCalendarDetailsImpl.setItemMasterSid(stDeductionCalendarDetails.getItemMasterSid());
		stDeductionCalendarDetailsImpl.setAdjustmentType(stDeductionCalendarDetails.getAdjustmentType());
		stDeductionCalendarDetailsImpl.setSessionId(stDeductionCalendarDetails.getSessionId());
		stDeductionCalendarDetailsImpl.setCheckRecord(stDeductionCalendarDetails.isCheckRecord());

		return stDeductionCalendarDetailsImpl;
	}

	/**
	 * Returns the st deduction calendar details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st deduction calendar details
	 * @return the st deduction calendar details
	 * @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStDeductionCalendarDetailsException {
		StDeductionCalendarDetails stDeductionCalendarDetails = fetchByPrimaryKey(primaryKey);

		if (stDeductionCalendarDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stDeductionCalendarDetails;
	}

	/**
	 * Returns the st deduction calendar details with the primary key or throws a {@link NoSuchStDeductionCalendarDetailsException} if it could not be found.
	 *
	 * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	 * @return the st deduction calendar details
	 * @throws NoSuchStDeductionCalendarDetailsException if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails findByPrimaryKey(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK)
		throws NoSuchStDeductionCalendarDetailsException {
		return findByPrimaryKey((Serializable)stDeductionCalendarDetailsPK);
	}

	/**
	 * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st deduction calendar details
	 * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				StDeductionCalendarDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StDeductionCalendarDetails stDeductionCalendarDetails = (StDeductionCalendarDetails)serializable;

		if (stDeductionCalendarDetails == null) {
			Session session = null;

			try {
				session = openSession();

				stDeductionCalendarDetails = (StDeductionCalendarDetails)session.get(StDeductionCalendarDetailsImpl.class,
						primaryKey);

				if (stDeductionCalendarDetails != null) {
					cacheResult(stDeductionCalendarDetails);
				}
				else {
					entityCache.putResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						StDeductionCalendarDetailsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StDeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
					StDeductionCalendarDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stDeductionCalendarDetails;
	}

	/**
	 * Returns the st deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stDeductionCalendarDetailsPK the primary key of the st deduction calendar details
	 * @return the st deduction calendar details, or <code>null</code> if a st deduction calendar details with the primary key could not be found
	 */
	@Override
	public StDeductionCalendarDetails fetchByPrimaryKey(
		StDeductionCalendarDetailsPK stDeductionCalendarDetailsPK) {
		return fetchByPrimaryKey((Serializable)stDeductionCalendarDetailsPK);
	}

	@Override
	public Map<Serializable, StDeductionCalendarDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StDeductionCalendarDetails> map = new HashMap<Serializable, StDeductionCalendarDetails>();

		for (Serializable primaryKey : primaryKeys) {
			StDeductionCalendarDetails stDeductionCalendarDetails = fetchByPrimaryKey(primaryKey);

			if (stDeductionCalendarDetails != null) {
				map.put(primaryKey, stDeductionCalendarDetails);
			}
		}

		return map;
	}

	/**
	 * Returns all the st deduction calendar detailses.
	 *
	 * @return the st deduction calendar detailses
	 */
	@Override
	public List<StDeductionCalendarDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st deduction calendar detailses
	 * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	 * @return the range of st deduction calendar detailses
	 */
	@Override
	public List<StDeductionCalendarDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st deduction calendar detailses
	 * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st deduction calendar detailses
	 */
	@Override
	public List<StDeductionCalendarDetails> findAll(int start, int end,
		OrderByComparator<StDeductionCalendarDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StDeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st deduction calendar detailses
	 * @param end the upper bound of the range of st deduction calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st deduction calendar detailses
	 */
	@Override
	public List<StDeductionCalendarDetails> findAll(int start, int end,
		OrderByComparator<StDeductionCalendarDetails> orderByComparator,
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

		List<StDeductionCalendarDetails> list = null;

		if (retrieveFromCache) {
			list = (List<StDeductionCalendarDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STDEDUCTIONCALENDARDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STDEDUCTIONCALENDARDETAILS;

				if (pagination) {
					sql = sql.concat(StDeductionCalendarDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StDeductionCalendarDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StDeductionCalendarDetails>)QueryUtil.list(q,
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
	 * Removes all the st deduction calendar detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StDeductionCalendarDetails stDeductionCalendarDetails : findAll()) {
			remove(stDeductionCalendarDetails);
		}
	}

	/**
	 * Returns the number of st deduction calendar detailses.
	 *
	 * @return the number of st deduction calendar detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STDEDUCTIONCALENDARDETAILS);

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
		return StDeductionCalendarDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st deduction calendar details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StDeductionCalendarDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STDEDUCTIONCALENDARDETAILS = "SELECT stDeductionCalendarDetails FROM StDeductionCalendarDetails stDeductionCalendarDetails";
	private static final String _SQL_COUNT_STDEDUCTIONCALENDARDETAILS = "SELECT COUNT(stDeductionCalendarDetails) FROM StDeductionCalendarDetails stDeductionCalendarDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stDeductionCalendarDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StDeductionCalendarDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StDeductionCalendarDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustmentBasis", "periodSid", "adjustmentValue",
				"adjustmentAllocationMethodology", "companyMasterSid",
				"discountAmount", "adjustmentVariable", "userId",
				"itemMasterSid", "adjustmentType", "sessionId", "checkRecord"
			});
}