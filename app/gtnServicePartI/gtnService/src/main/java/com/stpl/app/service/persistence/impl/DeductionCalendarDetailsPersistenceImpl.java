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

import com.stpl.app.exception.NoSuchDeductionCalendarDetailsException;
import com.stpl.app.model.DeductionCalendarDetails;
import com.stpl.app.model.impl.DeductionCalendarDetailsImpl;
import com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPK;
import com.stpl.app.service.persistence.DeductionCalendarDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the deduction calendar details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarDetailsPersistence
 * @see com.stpl.app.service.persistence.DeductionCalendarDetailsUtil
 * @generated
 */
@ProviderType
public class DeductionCalendarDetailsPersistenceImpl extends BasePersistenceImpl<DeductionCalendarDetails>
	implements DeductionCalendarDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeductionCalendarDetailsUtil} to access the deduction calendar details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeductionCalendarDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			DeductionCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED,
			DeductionCalendarDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DeductionCalendarDetailsPersistenceImpl() {
		setModelClass(DeductionCalendarDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("adjustmentBasis", "ADJUSTMENT_BASIS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("adjustmentValue", "ADJUSTMENT_VALUE");
			dbColumnNames.put("adjustmentAllocationMethodology",
				"ADJUSTMENT_ALLOCATION_METHODOLOGY");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("discountAmount", "DISCOUNT_AMOUNT");
			dbColumnNames.put("adjustmentVariable", "ADJUSTMENT_VARIABLE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
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
	 * Caches the deduction calendar details in the entity cache if it is enabled.
	 *
	 * @param deductionCalendarDetails the deduction calendar details
	 */
	@Override
	public void cacheResult(DeductionCalendarDetails deductionCalendarDetails) {
		entityCache.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsImpl.class,
			deductionCalendarDetails.getPrimaryKey(), deductionCalendarDetails);

		deductionCalendarDetails.resetOriginalValues();
	}

	/**
	 * Caches the deduction calendar detailses in the entity cache if it is enabled.
	 *
	 * @param deductionCalendarDetailses the deduction calendar detailses
	 */
	@Override
	public void cacheResult(
		List<DeductionCalendarDetails> deductionCalendarDetailses) {
		for (DeductionCalendarDetails deductionCalendarDetails : deductionCalendarDetailses) {
			if (entityCache.getResult(
						DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DeductionCalendarDetailsImpl.class,
						deductionCalendarDetails.getPrimaryKey()) == null) {
				cacheResult(deductionCalendarDetails);
			}
			else {
				deductionCalendarDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deduction calendar detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeductionCalendarDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deduction calendar details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeductionCalendarDetails deductionCalendarDetails) {
		entityCache.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsImpl.class,
			deductionCalendarDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<DeductionCalendarDetails> deductionCalendarDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeductionCalendarDetails deductionCalendarDetails : deductionCalendarDetailses) {
			entityCache.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DeductionCalendarDetailsImpl.class,
				deductionCalendarDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new deduction calendar details with the primary key. Does not add the deduction calendar details to the database.
	 *
	 * @param deductionCalendarDetailsPK the primary key for the new deduction calendar details
	 * @return the new deduction calendar details
	 */
	@Override
	public DeductionCalendarDetails create(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK) {
		DeductionCalendarDetails deductionCalendarDetails = new DeductionCalendarDetailsImpl();

		deductionCalendarDetails.setNew(true);
		deductionCalendarDetails.setPrimaryKey(deductionCalendarDetailsPK);

		return deductionCalendarDetails;
	}

	/**
	 * Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	 * @return the deduction calendar details that was removed
	 * @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails remove(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK)
		throws NoSuchDeductionCalendarDetailsException {
		return remove((Serializable)deductionCalendarDetailsPK);
	}

	/**
	 * Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deduction calendar details
	 * @return the deduction calendar details that was removed
	 * @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails remove(Serializable primaryKey)
		throws NoSuchDeductionCalendarDetailsException {
		Session session = null;

		try {
			session = openSession();

			DeductionCalendarDetails deductionCalendarDetails = (DeductionCalendarDetails)session.get(DeductionCalendarDetailsImpl.class,
					primaryKey);

			if (deductionCalendarDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deductionCalendarDetails);
		}
		catch (NoSuchDeductionCalendarDetailsException nsee) {
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
	protected DeductionCalendarDetails removeImpl(
		DeductionCalendarDetails deductionCalendarDetails) {
		deductionCalendarDetails = toUnwrappedModel(deductionCalendarDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deductionCalendarDetails)) {
				deductionCalendarDetails = (DeductionCalendarDetails)session.get(DeductionCalendarDetailsImpl.class,
						deductionCalendarDetails.getPrimaryKeyObj());
			}

			if (deductionCalendarDetails != null) {
				session.delete(deductionCalendarDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deductionCalendarDetails != null) {
			clearCache(deductionCalendarDetails);
		}

		return deductionCalendarDetails;
	}

	@Override
	public DeductionCalendarDetails updateImpl(
		DeductionCalendarDetails deductionCalendarDetails) {
		deductionCalendarDetails = toUnwrappedModel(deductionCalendarDetails);

		boolean isNew = deductionCalendarDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (deductionCalendarDetails.isNew()) {
				session.save(deductionCalendarDetails);

				deductionCalendarDetails.setNew(false);
			}
			else {
				deductionCalendarDetails = (DeductionCalendarDetails)session.merge(deductionCalendarDetails);
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

		entityCache.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionCalendarDetailsImpl.class,
			deductionCalendarDetails.getPrimaryKey(), deductionCalendarDetails,
			false);

		deductionCalendarDetails.resetOriginalValues();

		return deductionCalendarDetails;
	}

	protected DeductionCalendarDetails toUnwrappedModel(
		DeductionCalendarDetails deductionCalendarDetails) {
		if (deductionCalendarDetails instanceof DeductionCalendarDetailsImpl) {
			return deductionCalendarDetails;
		}

		DeductionCalendarDetailsImpl deductionCalendarDetailsImpl = new DeductionCalendarDetailsImpl();

		deductionCalendarDetailsImpl.setNew(deductionCalendarDetails.isNew());
		deductionCalendarDetailsImpl.setPrimaryKey(deductionCalendarDetails.getPrimaryKey());

		deductionCalendarDetailsImpl.setDeductionCalendarMasterSid(deductionCalendarDetails.getDeductionCalendarMasterSid());
		deductionCalendarDetailsImpl.setAdjustmentBasis(deductionCalendarDetails.getAdjustmentBasis());
		deductionCalendarDetailsImpl.setPeriodSid(deductionCalendarDetails.getPeriodSid());
		deductionCalendarDetailsImpl.setAdjustmentValue(deductionCalendarDetails.getAdjustmentValue());
		deductionCalendarDetailsImpl.setAdjustmentAllocationMethodology(deductionCalendarDetails.getAdjustmentAllocationMethodology());
		deductionCalendarDetailsImpl.setCompanyMasterSid(deductionCalendarDetails.getCompanyMasterSid());
		deductionCalendarDetailsImpl.setDiscountAmount(deductionCalendarDetails.getDiscountAmount());
		deductionCalendarDetailsImpl.setAdjustmentVariable(deductionCalendarDetails.getAdjustmentVariable());
		deductionCalendarDetailsImpl.setItemMasterSid(deductionCalendarDetails.getItemMasterSid());
		deductionCalendarDetailsImpl.setAdjustmentType(deductionCalendarDetails.getAdjustmentType());
		deductionCalendarDetailsImpl.setCheckRecord(deductionCalendarDetails.isCheckRecord());

		return deductionCalendarDetailsImpl;
	}

	/**
	 * Returns the deduction calendar details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction calendar details
	 * @return the deduction calendar details
	 * @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeductionCalendarDetailsException {
		DeductionCalendarDetails deductionCalendarDetails = fetchByPrimaryKey(primaryKey);

		if (deductionCalendarDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeductionCalendarDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deductionCalendarDetails;
	}

	/**
	 * Returns the deduction calendar details with the primary key or throws a {@link NoSuchDeductionCalendarDetailsException} if it could not be found.
	 *
	 * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	 * @return the deduction calendar details
	 * @throws NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails findByPrimaryKey(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK)
		throws NoSuchDeductionCalendarDetailsException {
		return findByPrimaryKey((Serializable)deductionCalendarDetailsPK);
	}

	/**
	 * Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction calendar details
	 * @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DeductionCalendarDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeductionCalendarDetails deductionCalendarDetails = (DeductionCalendarDetails)serializable;

		if (deductionCalendarDetails == null) {
			Session session = null;

			try {
				session = openSession();

				deductionCalendarDetails = (DeductionCalendarDetails)session.get(DeductionCalendarDetailsImpl.class,
						primaryKey);

				if (deductionCalendarDetails != null) {
					cacheResult(deductionCalendarDetails);
				}
				else {
					entityCache.putResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DeductionCalendarDetailsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeductionCalendarDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DeductionCalendarDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deductionCalendarDetails;
	}

	/**
	 * Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
	 * @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
	 */
	@Override
	public DeductionCalendarDetails fetchByPrimaryKey(
		DeductionCalendarDetailsPK deductionCalendarDetailsPK) {
		return fetchByPrimaryKey((Serializable)deductionCalendarDetailsPK);
	}

	@Override
	public Map<Serializable, DeductionCalendarDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeductionCalendarDetails> map = new HashMap<Serializable, DeductionCalendarDetails>();

		for (Serializable primaryKey : primaryKeys) {
			DeductionCalendarDetails deductionCalendarDetails = fetchByPrimaryKey(primaryKey);

			if (deductionCalendarDetails != null) {
				map.put(primaryKey, deductionCalendarDetails);
			}
		}

		return map;
	}

	/**
	 * Returns all the deduction calendar detailses.
	 *
	 * @return the deduction calendar detailses
	 */
	@Override
	public List<DeductionCalendarDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar detailses
	 * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	 * @return the range of deduction calendar detailses
	 */
	@Override
	public List<DeductionCalendarDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar detailses
	 * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deduction calendar detailses
	 */
	@Override
	public List<DeductionCalendarDetails> findAll(int start, int end,
		OrderByComparator<DeductionCalendarDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deduction calendar detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction calendar detailses
	 * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deduction calendar detailses
	 */
	@Override
	public List<DeductionCalendarDetails> findAll(int start, int end,
		OrderByComparator<DeductionCalendarDetails> orderByComparator,
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

		List<DeductionCalendarDetails> list = null;

		if (retrieveFromCache) {
			list = (List<DeductionCalendarDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DEDUCTIONCALENDARDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEDUCTIONCALENDARDETAILS;

				if (pagination) {
					sql = sql.concat(DeductionCalendarDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeductionCalendarDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeductionCalendarDetails>)QueryUtil.list(q,
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
	 * Removes all the deduction calendar detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeductionCalendarDetails deductionCalendarDetails : findAll()) {
			remove(deductionCalendarDetails);
		}
	}

	/**
	 * Returns the number of deduction calendar detailses.
	 *
	 * @return the number of deduction calendar detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEDUCTIONCALENDARDETAILS);

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
		return DeductionCalendarDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deduction calendar details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeductionCalendarDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DEDUCTIONCALENDARDETAILS = "SELECT deductionCalendarDetails FROM DeductionCalendarDetails deductionCalendarDetails";
	private static final String _SQL_COUNT_DEDUCTIONCALENDARDETAILS = "SELECT COUNT(deductionCalendarDetails) FROM DeductionCalendarDetails deductionCalendarDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deductionCalendarDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionCalendarDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DeductionCalendarDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"deductionCalendarMasterSid", "adjustmentBasis", "periodSid",
				"adjustmentValue", "adjustmentAllocationMethodology",
				"companyMasterSid", "discountAmount", "adjustmentVariable",
				"itemMasterSid", "adjustmentType", "checkRecord"
			});
}