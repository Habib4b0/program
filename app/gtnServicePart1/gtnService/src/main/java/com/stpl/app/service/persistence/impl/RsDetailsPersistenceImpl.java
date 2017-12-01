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

import com.stpl.app.exception.NoSuchRsDetailsException;
import com.stpl.app.model.RsDetails;
import com.stpl.app.model.impl.RsDetailsImpl;
import com.stpl.app.model.impl.RsDetailsModelImpl;
import com.stpl.app.service.persistence.RsDetailsPersistence;

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
 * The persistence implementation for the rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsPersistence
 * @see com.stpl.app.service.persistence.RsDetailsUtil
 * @generated
 */
@ProviderType
public class RsDetailsPersistenceImpl extends BasePersistenceImpl<RsDetails>
	implements RsDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RsDetailsUtil} to access the rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RsDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsModelImpl.FINDER_CACHE_ENABLED, RsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsModelImpl.FINDER_CACHE_ENABLED, RsDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RsDetailsPersistenceImpl() {
		setModelClass(RsDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("rebateAmount", "REBATE_AMOUNT");
			dbColumnNames.put("itemRsAttachedStatus", "ITEM_RS_ATTACHED_STATUS");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rebatePlanMasterSid", "REBATE_PLAN_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("bundleNo", "BUNDLE_NO");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("itemRebateEndDate", "ITEM_REBATE_END_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemRebateStartDate", "ITEM_REBATE_START_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("rsDetailsSid", "RS_DETAILS_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("itemRsAttachedDate", "ITEM_RS_ATTACHED_DATE");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("evaluationRule", "EVALUATION_RULE");
			dbColumnNames.put("netSalesRule", "NET_SALES_RULE");
			dbColumnNames.put("formulaType", "FORMULA_TYPE");
			dbColumnNames.put("calculationRule", "CALCULATION_RULE");
			dbColumnNames.put("calculationRuleBundle", "CALCULATION_RULE_BUNDLE");
			dbColumnNames.put("evaluationRuleBundle", "EVALUATION_RULE_BUNDLE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rs details in the entity cache if it is enabled.
	 *
	 * @param rsDetails the rs details
	 */
	@Override
	public void cacheResult(RsDetails rsDetails) {
		entityCache.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsImpl.class, rsDetails.getPrimaryKey(), rsDetails);

		rsDetails.resetOriginalValues();
	}

	/**
	 * Caches the rs detailses in the entity cache if it is enabled.
	 *
	 * @param rsDetailses the rs detailses
	 */
	@Override
	public void cacheResult(List<RsDetails> rsDetailses) {
		for (RsDetails rsDetails : rsDetailses) {
			if (entityCache.getResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						RsDetailsImpl.class, rsDetails.getPrimaryKey()) == null) {
				cacheResult(rsDetails);
			}
			else {
				rsDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rs detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RsDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rs details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RsDetails rsDetails) {
		entityCache.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsImpl.class, rsDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RsDetails> rsDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RsDetails rsDetails : rsDetailses) {
			entityCache.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				RsDetailsImpl.class, rsDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rs details with the primary key. Does not add the rs details to the database.
	 *
	 * @param rsDetailsSid the primary key for the new rs details
	 * @return the new rs details
	 */
	@Override
	public RsDetails create(int rsDetailsSid) {
		RsDetails rsDetails = new RsDetailsImpl();

		rsDetails.setNew(true);
		rsDetails.setPrimaryKey(rsDetailsSid);

		return rsDetails;
	}

	/**
	 * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rsDetailsSid the primary key of the rs details
	 * @return the rs details that was removed
	 * @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails remove(int rsDetailsSid) throws NoSuchRsDetailsException {
		return remove((Serializable)rsDetailsSid);
	}

	/**
	 * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rs details
	 * @return the rs details that was removed
	 * @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails remove(Serializable primaryKey)
		throws NoSuchRsDetailsException {
		Session session = null;

		try {
			session = openSession();

			RsDetails rsDetails = (RsDetails)session.get(RsDetailsImpl.class,
					primaryKey);

			if (rsDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rsDetails);
		}
		catch (NoSuchRsDetailsException nsee) {
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
	protected RsDetails removeImpl(RsDetails rsDetails) {
		rsDetails = toUnwrappedModel(rsDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rsDetails)) {
				rsDetails = (RsDetails)session.get(RsDetailsImpl.class,
						rsDetails.getPrimaryKeyObj());
			}

			if (rsDetails != null) {
				session.delete(rsDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rsDetails != null) {
			clearCache(rsDetails);
		}

		return rsDetails;
	}

	@Override
	public RsDetails updateImpl(RsDetails rsDetails) {
		rsDetails = toUnwrappedModel(rsDetails);

		boolean isNew = rsDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rsDetails.isNew()) {
				session.save(rsDetails);

				rsDetails.setNew(false);
			}
			else {
				rsDetails = (RsDetails)session.merge(rsDetails);
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

		entityCache.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			RsDetailsImpl.class, rsDetails.getPrimaryKey(), rsDetails, false);

		rsDetails.resetOriginalValues();

		return rsDetails;
	}

	protected RsDetails toUnwrappedModel(RsDetails rsDetails) {
		if (rsDetails instanceof RsDetailsImpl) {
			return rsDetails;
		}

		RsDetailsImpl rsDetailsImpl = new RsDetailsImpl();

		rsDetailsImpl.setNew(rsDetails.isNew());
		rsDetailsImpl.setPrimaryKey(rsDetails.getPrimaryKey());

		rsDetailsImpl.setRebateAmount(rsDetails.getRebateAmount());
		rsDetailsImpl.setItemRsAttachedStatus(rsDetails.getItemRsAttachedStatus());
		rsDetailsImpl.setFormulaMethodId(rsDetails.getFormulaMethodId());
		rsDetailsImpl.setItemMasterSid(rsDetails.getItemMasterSid());
		rsDetailsImpl.setRebatePlanMasterSid(rsDetails.getRebatePlanMasterSid());
		rsDetailsImpl.setModifiedDate(rsDetails.getModifiedDate());
		rsDetailsImpl.setBundleNo(rsDetails.getBundleNo());
		rsDetailsImpl.setRecordLockStatus(rsDetails.isRecordLockStatus());
		rsDetailsImpl.setCreatedDate(rsDetails.getCreatedDate());
		rsDetailsImpl.setCreatedBy(rsDetails.getCreatedBy());
		rsDetailsImpl.setSource(rsDetails.getSource());
		rsDetailsImpl.setItemRebateEndDate(rsDetails.getItemRebateEndDate());
		rsDetailsImpl.setBatchId(rsDetails.getBatchId());
		rsDetailsImpl.setItemRebateStartDate(rsDetails.getItemRebateStartDate());
		rsDetailsImpl.setModifiedBy(rsDetails.getModifiedBy());
		rsDetailsImpl.setInboundStatus(rsDetails.getInboundStatus());
		rsDetailsImpl.setRsDetailsSid(rsDetails.getRsDetailsSid());
		rsDetailsImpl.setRsModelSid(rsDetails.getRsModelSid());
		rsDetailsImpl.setFormulaId(rsDetails.getFormulaId());
		rsDetailsImpl.setItemRsAttachedDate(rsDetails.getItemRsAttachedDate());
		rsDetailsImpl.setIfpModelSid(rsDetails.getIfpModelSid());
		rsDetailsImpl.setDeductionCalendarMasterSid(rsDetails.getDeductionCalendarMasterSid());
		rsDetailsImpl.setNetSalesFormulaMasterSid(rsDetails.getNetSalesFormulaMasterSid());
		rsDetailsImpl.setEvaluationRule(rsDetails.getEvaluationRule());
		rsDetailsImpl.setNetSalesRule(rsDetails.getNetSalesRule());
		rsDetailsImpl.setFormulaType(rsDetails.getFormulaType());
		rsDetailsImpl.setCalculationRule(rsDetails.getCalculationRule());
		rsDetailsImpl.setCalculationRuleBundle(rsDetails.getCalculationRuleBundle());
		rsDetailsImpl.setEvaluationRuleBundle(rsDetails.getEvaluationRuleBundle());

		return rsDetailsImpl;
	}

	/**
	 * Returns the rs details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs details
	 * @return the rs details
	 * @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRsDetailsException {
		RsDetails rsDetails = fetchByPrimaryKey(primaryKey);

		if (rsDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rsDetails;
	}

	/**
	 * Returns the rs details with the primary key or throws a {@link NoSuchRsDetailsException} if it could not be found.
	 *
	 * @param rsDetailsSid the primary key of the rs details
	 * @return the rs details
	 * @throws NoSuchRsDetailsException if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails findByPrimaryKey(int rsDetailsSid)
		throws NoSuchRsDetailsException {
		return findByPrimaryKey((Serializable)rsDetailsSid);
	}

	/**
	 * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs details
	 * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				RsDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RsDetails rsDetails = (RsDetails)serializable;

		if (rsDetails == null) {
			Session session = null;

			try {
				session = openSession();

				rsDetails = (RsDetails)session.get(RsDetailsImpl.class,
						primaryKey);

				if (rsDetails != null) {
					cacheResult(rsDetails);
				}
				else {
					entityCache.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						RsDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rsDetails;
	}

	/**
	 * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rsDetailsSid the primary key of the rs details
	 * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
	 */
	@Override
	public RsDetails fetchByPrimaryKey(int rsDetailsSid) {
		return fetchByPrimaryKey((Serializable)rsDetailsSid);
	}

	@Override
	public Map<Serializable, RsDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RsDetails> map = new HashMap<Serializable, RsDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RsDetails rsDetails = fetchByPrimaryKey(primaryKey);

			if (rsDetails != null) {
				map.put(primaryKey, rsDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RsDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RSDETAILS_WHERE_PKS_IN);

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

			for (RsDetails rsDetails : (List<RsDetails>)q.list()) {
				map.put(rsDetails.getPrimaryKeyObj(), rsDetails);

				cacheResult(rsDetails);

				uncachedPrimaryKeys.remove(rsDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					RsDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the rs detailses.
	 *
	 * @return the rs detailses
	 */
	@Override
	public List<RsDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs detailses
	 * @param end the upper bound of the range of rs detailses (not inclusive)
	 * @return the range of rs detailses
	 */
	@Override
	public List<RsDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs detailses
	 * @param end the upper bound of the range of rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rs detailses
	 */
	@Override
	public List<RsDetails> findAll(int start, int end,
		OrderByComparator<RsDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs detailses
	 * @param end the upper bound of the range of rs detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rs detailses
	 */
	@Override
	public List<RsDetails> findAll(int start, int end,
		OrderByComparator<RsDetails> orderByComparator,
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

		List<RsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<RsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RSDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RSDETAILS;

				if (pagination) {
					sql = sql.concat(RsDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the rs detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RsDetails rsDetails : findAll()) {
			remove(rsDetails);
		}
	}

	/**
	 * Returns the number of rs detailses.
	 *
	 * @return the number of rs detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RSDETAILS);

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
		return RsDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rs details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RsDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RSDETAILS = "SELECT rsDetails FROM RsDetails rsDetails";
	private static final String _SQL_SELECT_RSDETAILS_WHERE_PKS_IN = "SELECT rsDetails FROM RsDetails rsDetails WHERE RS_DETAILS_SID IN (";
	private static final String _SQL_COUNT_RSDETAILS = "SELECT COUNT(rsDetails) FROM RsDetails rsDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rsDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RsDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"rebateAmount", "itemRsAttachedStatus", "formulaMethodId",
				"itemMasterSid", "rebatePlanMasterSid", "modifiedDate",
				"bundleNo", "recordLockStatus", "createdDate", "createdBy",
				"source", "itemRebateEndDate", "batchId", "itemRebateStartDate",
				"modifiedBy", "inboundStatus", "rsDetailsSid", "rsModelSid",
				"formulaId", "itemRsAttachedDate", "ifpModelSid",
				"deductionCalendarMasterSid", "netSalesFormulaMasterSid",
				"evaluationRule", "netSalesRule", "formulaType",
				"calculationRule", "calculationRuleBundle",
				"evaluationRuleBundle"
			});
}