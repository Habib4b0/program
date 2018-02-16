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
import com.liferay.portal.kernel.dao.orm.QueryPos;
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

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentReserveDetailException;
import com.stpl.app.parttwo.model.StAdjustmentReserveDetail;
import com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailImpl;
import com.stpl.app.parttwo.model.impl.StAdjustmentReserveDetailModelImpl;
import com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailPersistence;

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
 * The persistence implementation for the st adjustment reserve detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentReserveDetailPersistence
 * @see com.stpl.app.parttwo.service.persistence.StAdjustmentReserveDetailUtil
 * @generated
 */
@ProviderType
public class StAdjustmentReserveDetailPersistenceImpl
	extends BasePersistenceImpl<StAdjustmentReserveDetail>
	implements StAdjustmentReserveDetailPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StAdjustmentReserveDetailUtil} to access the st adjustment reserve detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StAdjustmentReserveDetailImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
			StAdjustmentReserveDetailImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
			StAdjustmentReserveDetailImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public StAdjustmentReserveDetailPersistenceImpl() {
		setModelClass(StAdjustmentReserveDetail.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustmentCreatedDate", "ADJUSTMENT_CREATED_DATE");
			dbColumnNames.put("etlCheckRecord", "ETL_CHECK_RECORD");
			dbColumnNames.put("postingIndicator", "POSTING_INDICATOR");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("credit", "CREDIT");
			dbColumnNames.put("workflowApprovedDate", "WORKFLOW_APPROVED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("lineDescription", "LINE_DESCRIPTION");
			dbColumnNames.put("ledger", "LEDGER");
			dbColumnNames.put("udc6", "UDC_6");
			dbColumnNames.put("udc5", "UDC_5");
			dbColumnNames.put("udc4", "UDC_4");
			dbColumnNames.put("workflowCreatedDate", "WORKFLOW_CREATED_DATE");
			dbColumnNames.put("udc3", "UDC_3");
			dbColumnNames.put("udc2", "UDC_2");
			dbColumnNames.put("udc1", "UDC_1");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("glCompanyName", "COMPANY");
			dbColumnNames.put("division", "DIVISION");
			dbColumnNames.put("balanceType", "BALANCE_TYPE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("journalName", "JOURNAL_NAME");
			dbColumnNames.put("project", "PROJECT");
			dbColumnNames.put("debit", "DEBIT");
			dbColumnNames.put("accountType", "ACCOUNT_TYPE");
			dbColumnNames.put("journalDescription", "JOURNAL_DESCRIPTION");
			dbColumnNames.put("category", "CATEGORY");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("businessUnitId", "BUSINESS_UNIT");
			dbColumnNames.put("reversalPeriodDate", "REVERSAL_PERIOD_DATE");
			dbColumnNames.put("workflowId", "WORKFLOW_ID");
			dbColumnNames.put("chartOfAccounts", "CHART_OF_ACCOUNTS");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("batchName", "BATCH_NAME");
			dbColumnNames.put("database", "ARD_DB");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("outboundStatus", "OUTBOUND_STATUS");
			dbColumnNames.put("dataAccessSet", "DATA_ACCESS_SET");
			dbColumnNames.put("future1", "FUTURE_1");
			dbColumnNames.put("future2", "FUTURE_2");
			dbColumnNames.put("workflowName", "WORKFLOW_NAME");
			dbColumnNames.put("workflowCreatedBy", "WORKFLOW_CREATED_BY");
			dbColumnNames.put("currency", "CURRENCY");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("accountCategory", "ACCOUNT_CATEGORY");
			dbColumnNames.put("reverseJournal", "REVERSE_JOURNAL");
			dbColumnNames.put("workflowApprovedBy", "WORKFLOW_APPROVED_BY");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("accountingDate", "ACCOUNTING_DATE");
			dbColumnNames.put("redemptionPeriod", "REDEMPTION_PERIOD");
			dbColumnNames.put("originalBatchId", "ORIGINAL_BATCH_ID");
			dbColumnNames.put("adjustmentLevel", "ADJUSTMENT_LEVEL");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st adjustment reserve detail in the entity cache if it is enabled.
	 *
	 * @param stAdjustmentReserveDetail the st adjustment reserve detail
	 */
	@Override
	public void cacheResult(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		entityCache.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailImpl.class,
			stAdjustmentReserveDetail.getPrimaryKey(), stAdjustmentReserveDetail);

		stAdjustmentReserveDetail.resetOriginalValues();
	}

	/**
	 * Caches the st adjustment reserve details in the entity cache if it is enabled.
	 *
	 * @param stAdjustmentReserveDetails the st adjustment reserve details
	 */
	@Override
	public void cacheResult(
		List<StAdjustmentReserveDetail> stAdjustmentReserveDetails) {
		for (StAdjustmentReserveDetail stAdjustmentReserveDetail : stAdjustmentReserveDetails) {
			if (entityCache.getResult(
						StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
						StAdjustmentReserveDetailImpl.class,
						stAdjustmentReserveDetail.getPrimaryKey()) == null) {
				cacheResult(stAdjustmentReserveDetail);
			}
			else {
				stAdjustmentReserveDetail.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st adjustment reserve details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StAdjustmentReserveDetailImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st adjustment reserve detail.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		entityCache.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailImpl.class,
			stAdjustmentReserveDetail.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StAdjustmentReserveDetail> stAdjustmentReserveDetails) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StAdjustmentReserveDetail stAdjustmentReserveDetail : stAdjustmentReserveDetails) {
			entityCache.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
				StAdjustmentReserveDetailImpl.class,
				stAdjustmentReserveDetail.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st adjustment reserve detail with the primary key. Does not add the st adjustment reserve detail to the database.
	 *
	 * @param workflowId the primary key for the new st adjustment reserve detail
	 * @return the new st adjustment reserve detail
	 */
	@Override
	public StAdjustmentReserveDetail create(String workflowId) {
		StAdjustmentReserveDetail stAdjustmentReserveDetail = new StAdjustmentReserveDetailImpl();

		stAdjustmentReserveDetail.setNew(true);
		stAdjustmentReserveDetail.setPrimaryKey(workflowId);

		return stAdjustmentReserveDetail;
	}

	/**
	 * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowId the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail that was removed
	 * @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail remove(String workflowId)
		throws NoSuchStAdjustmentReserveDetailException {
		return remove((Serializable)workflowId);
	}

	/**
	 * Removes the st adjustment reserve detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail that was removed
	 * @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail remove(Serializable primaryKey)
		throws NoSuchStAdjustmentReserveDetailException {
		Session session = null;

		try {
			session = openSession();

			StAdjustmentReserveDetail stAdjustmentReserveDetail = (StAdjustmentReserveDetail)session.get(StAdjustmentReserveDetailImpl.class,
					primaryKey);

			if (stAdjustmentReserveDetail == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStAdjustmentReserveDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stAdjustmentReserveDetail);
		}
		catch (NoSuchStAdjustmentReserveDetailException nsee) {
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
	protected StAdjustmentReserveDetail removeImpl(
		StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		stAdjustmentReserveDetail = toUnwrappedModel(stAdjustmentReserveDetail);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stAdjustmentReserveDetail)) {
				stAdjustmentReserveDetail = (StAdjustmentReserveDetail)session.get(StAdjustmentReserveDetailImpl.class,
						stAdjustmentReserveDetail.getPrimaryKeyObj());
			}

			if (stAdjustmentReserveDetail != null) {
				session.delete(stAdjustmentReserveDetail);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stAdjustmentReserveDetail != null) {
			clearCache(stAdjustmentReserveDetail);
		}

		return stAdjustmentReserveDetail;
	}

	@Override
	public StAdjustmentReserveDetail updateImpl(
		StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		stAdjustmentReserveDetail = toUnwrappedModel(stAdjustmentReserveDetail);

		boolean isNew = stAdjustmentReserveDetail.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stAdjustmentReserveDetail.isNew()) {
				session.save(stAdjustmentReserveDetail);

				stAdjustmentReserveDetail.setNew(false);
			}
			else {
				stAdjustmentReserveDetail = (StAdjustmentReserveDetail)session.merge(stAdjustmentReserveDetail);
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

		entityCache.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentReserveDetailImpl.class,
			stAdjustmentReserveDetail.getPrimaryKey(),
			stAdjustmentReserveDetail, false);

		stAdjustmentReserveDetail.resetOriginalValues();

		return stAdjustmentReserveDetail;
	}

	protected StAdjustmentReserveDetail toUnwrappedModel(
		StAdjustmentReserveDetail stAdjustmentReserveDetail) {
		if (stAdjustmentReserveDetail instanceof StAdjustmentReserveDetailImpl) {
			return stAdjustmentReserveDetail;
		}

		StAdjustmentReserveDetailImpl stAdjustmentReserveDetailImpl = new StAdjustmentReserveDetailImpl();

		stAdjustmentReserveDetailImpl.setNew(stAdjustmentReserveDetail.isNew());
		stAdjustmentReserveDetailImpl.setPrimaryKey(stAdjustmentReserveDetail.getPrimaryKey());

		stAdjustmentReserveDetailImpl.setAdjustmentCreatedDate(stAdjustmentReserveDetail.getAdjustmentCreatedDate());
		stAdjustmentReserveDetailImpl.setEtlCheckRecord(stAdjustmentReserveDetail.isEtlCheckRecord());
		stAdjustmentReserveDetailImpl.setPostingIndicator(stAdjustmentReserveDetail.getPostingIndicator());
		stAdjustmentReserveDetailImpl.setModifiedDate(stAdjustmentReserveDetail.getModifiedDate());
		stAdjustmentReserveDetailImpl.setAccount(stAdjustmentReserveDetail.getAccount());
		stAdjustmentReserveDetailImpl.setCredit(stAdjustmentReserveDetail.getCredit());
		stAdjustmentReserveDetailImpl.setWorkflowApprovedDate(stAdjustmentReserveDetail.getWorkflowApprovedDate());
		stAdjustmentReserveDetailImpl.setSource(stAdjustmentReserveDetail.getSource());
		stAdjustmentReserveDetailImpl.setLineDescription(stAdjustmentReserveDetail.getLineDescription());
		stAdjustmentReserveDetailImpl.setLedger(stAdjustmentReserveDetail.getLedger());
		stAdjustmentReserveDetailImpl.setUdc6(stAdjustmentReserveDetail.getUdc6());
		stAdjustmentReserveDetailImpl.setUdc5(stAdjustmentReserveDetail.getUdc5());
		stAdjustmentReserveDetailImpl.setUdc4(stAdjustmentReserveDetail.getUdc4());
		stAdjustmentReserveDetailImpl.setWorkflowCreatedDate(stAdjustmentReserveDetail.getWorkflowCreatedDate());
		stAdjustmentReserveDetailImpl.setUdc3(stAdjustmentReserveDetail.getUdc3());
		stAdjustmentReserveDetailImpl.setUdc2(stAdjustmentReserveDetail.getUdc2());
		stAdjustmentReserveDetailImpl.setUdc1(stAdjustmentReserveDetail.getUdc1());
		stAdjustmentReserveDetailImpl.setAdjustmentType(stAdjustmentReserveDetail.getAdjustmentType());
		stAdjustmentReserveDetailImpl.setModifiedBy(stAdjustmentReserveDetail.getModifiedBy());
		stAdjustmentReserveDetailImpl.setCheckRecord(stAdjustmentReserveDetail.isCheckRecord());
		stAdjustmentReserveDetailImpl.setGlCompanyName(stAdjustmentReserveDetail.getGlCompanyName());
		stAdjustmentReserveDetailImpl.setDivision(stAdjustmentReserveDetail.getDivision());
		stAdjustmentReserveDetailImpl.setBalanceType(stAdjustmentReserveDetail.getBalanceType());
		stAdjustmentReserveDetailImpl.setSessionId(stAdjustmentReserveDetail.getSessionId());
		stAdjustmentReserveDetailImpl.setJournalName(stAdjustmentReserveDetail.getJournalName());
		stAdjustmentReserveDetailImpl.setProject(stAdjustmentReserveDetail.getProject());
		stAdjustmentReserveDetailImpl.setDebit(stAdjustmentReserveDetail.getDebit());
		stAdjustmentReserveDetailImpl.setAccountType(stAdjustmentReserveDetail.getAccountType());
		stAdjustmentReserveDetailImpl.setJournalDescription(stAdjustmentReserveDetail.getJournalDescription());
		stAdjustmentReserveDetailImpl.setCategory(stAdjustmentReserveDetail.getCategory());
		stAdjustmentReserveDetailImpl.setCreatedBy(stAdjustmentReserveDetail.getCreatedBy());
		stAdjustmentReserveDetailImpl.setCreatedDate(stAdjustmentReserveDetail.getCreatedDate());
		stAdjustmentReserveDetailImpl.setBusinessUnitId(stAdjustmentReserveDetail.getBusinessUnitId());
		stAdjustmentReserveDetailImpl.setReversalPeriodDate(stAdjustmentReserveDetail.getReversalPeriodDate());
		stAdjustmentReserveDetailImpl.setWorkflowId(stAdjustmentReserveDetail.getWorkflowId());
		stAdjustmentReserveDetailImpl.setChartOfAccounts(stAdjustmentReserveDetail.getChartOfAccounts());
		stAdjustmentReserveDetailImpl.setUserId(stAdjustmentReserveDetail.getUserId());
		stAdjustmentReserveDetailImpl.setBatchName(stAdjustmentReserveDetail.getBatchName());
		stAdjustmentReserveDetailImpl.setDatabase(stAdjustmentReserveDetail.getDatabase());
		stAdjustmentReserveDetailImpl.setCostCenter(stAdjustmentReserveDetail.getCostCenter());
		stAdjustmentReserveDetailImpl.setOutboundStatus(stAdjustmentReserveDetail.getOutboundStatus());
		stAdjustmentReserveDetailImpl.setDataAccessSet(stAdjustmentReserveDetail.getDataAccessSet());
		stAdjustmentReserveDetailImpl.setFuture1(stAdjustmentReserveDetail.getFuture1());
		stAdjustmentReserveDetailImpl.setFuture2(stAdjustmentReserveDetail.getFuture2());
		stAdjustmentReserveDetailImpl.setWorkflowName(stAdjustmentReserveDetail.getWorkflowName());
		stAdjustmentReserveDetailImpl.setWorkflowCreatedBy(stAdjustmentReserveDetail.getWorkflowCreatedBy());
		stAdjustmentReserveDetailImpl.setCurrency(stAdjustmentReserveDetail.getCurrency());
		stAdjustmentReserveDetailImpl.setBatchId(stAdjustmentReserveDetail.getBatchId());
		stAdjustmentReserveDetailImpl.setAccountCategory(stAdjustmentReserveDetail.getAccountCategory());
		stAdjustmentReserveDetailImpl.setReverseJournal(stAdjustmentReserveDetail.getReverseJournal());
		stAdjustmentReserveDetailImpl.setWorkflowApprovedBy(stAdjustmentReserveDetail.getWorkflowApprovedBy());
		stAdjustmentReserveDetailImpl.setBrand(stAdjustmentReserveDetail.getBrand());
		stAdjustmentReserveDetailImpl.setAccountingDate(stAdjustmentReserveDetail.getAccountingDate());
		stAdjustmentReserveDetailImpl.setRedemptionPeriod(stAdjustmentReserveDetail.getRedemptionPeriod());
		stAdjustmentReserveDetailImpl.setOriginalBatchId(stAdjustmentReserveDetail.getOriginalBatchId());
		stAdjustmentReserveDetailImpl.setAdjustmentLevel(stAdjustmentReserveDetail.getAdjustmentLevel());

		return stAdjustmentReserveDetailImpl;
	}

	/**
	 * Returns the st adjustment reserve detail with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail
	 * @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStAdjustmentReserveDetailException {
		StAdjustmentReserveDetail stAdjustmentReserveDetail = fetchByPrimaryKey(primaryKey);

		if (stAdjustmentReserveDetail == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStAdjustmentReserveDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stAdjustmentReserveDetail;
	}

	/**
	 * Returns the st adjustment reserve detail with the primary key or throws a {@link NoSuchStAdjustmentReserveDetailException} if it could not be found.
	 *
	 * @param workflowId the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail
	 * @throws NoSuchStAdjustmentReserveDetailException if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail findByPrimaryKey(String workflowId)
		throws NoSuchStAdjustmentReserveDetailException {
		return findByPrimaryKey((Serializable)workflowId);
	}

	/**
	 * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
				StAdjustmentReserveDetailImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StAdjustmentReserveDetail stAdjustmentReserveDetail = (StAdjustmentReserveDetail)serializable;

		if (stAdjustmentReserveDetail == null) {
			Session session = null;

			try {
				session = openSession();

				stAdjustmentReserveDetail = (StAdjustmentReserveDetail)session.get(StAdjustmentReserveDetailImpl.class,
						primaryKey);

				if (stAdjustmentReserveDetail != null) {
					cacheResult(stAdjustmentReserveDetail);
				}
				else {
					entityCache.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
						StAdjustmentReserveDetailImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentReserveDetailImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stAdjustmentReserveDetail;
	}

	/**
	 * Returns the st adjustment reserve detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowId the primary key of the st adjustment reserve detail
	 * @return the st adjustment reserve detail, or <code>null</code> if a st adjustment reserve detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentReserveDetail fetchByPrimaryKey(String workflowId) {
		return fetchByPrimaryKey((Serializable)workflowId);
	}

	@Override
	public Map<Serializable, StAdjustmentReserveDetail> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StAdjustmentReserveDetail> map = new HashMap<Serializable, StAdjustmentReserveDetail>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StAdjustmentReserveDetail stAdjustmentReserveDetail = fetchByPrimaryKey(primaryKey);

			if (stAdjustmentReserveDetail != null) {
				map.put(primaryKey, stAdjustmentReserveDetail);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentReserveDetailImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StAdjustmentReserveDetail)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STADJUSTMENTRESERVEDETAIL_WHERE_PKS_IN);

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			query.append(StringPool.QUESTION);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				qPos.add((String)primaryKey);
			}

			for (StAdjustmentReserveDetail stAdjustmentReserveDetail : (List<StAdjustmentReserveDetail>)q.list()) {
				map.put(stAdjustmentReserveDetail.getPrimaryKeyObj(),
					stAdjustmentReserveDetail);

				cacheResult(stAdjustmentReserveDetail);

				uncachedPrimaryKeys.remove(stAdjustmentReserveDetail.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StAdjustmentReserveDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentReserveDetailImpl.class, primaryKey, nullModel);
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
	 * Returns all the st adjustment reserve details.
	 *
	 * @return the st adjustment reserve details
	 */
	@Override
	public List<StAdjustmentReserveDetail> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st adjustment reserve details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment reserve details
	 * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	 * @return the range of st adjustment reserve details
	 */
	@Override
	public List<StAdjustmentReserveDetail> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st adjustment reserve details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment reserve details
	 * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st adjustment reserve details
	 */
	@Override
	public List<StAdjustmentReserveDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentReserveDetail> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st adjustment reserve details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentReserveDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment reserve details
	 * @param end the upper bound of the range of st adjustment reserve details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st adjustment reserve details
	 */
	@Override
	public List<StAdjustmentReserveDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentReserveDetail> orderByComparator,
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

		List<StAdjustmentReserveDetail> list = null;

		if (retrieveFromCache) {
			list = (List<StAdjustmentReserveDetail>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STADJUSTMENTRESERVEDETAIL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STADJUSTMENTRESERVEDETAIL;

				if (pagination) {
					sql = sql.concat(StAdjustmentReserveDetailModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StAdjustmentReserveDetail>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StAdjustmentReserveDetail>)QueryUtil.list(q,
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
	 * Removes all the st adjustment reserve details from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StAdjustmentReserveDetail stAdjustmentReserveDetail : findAll()) {
			remove(stAdjustmentReserveDetail);
		}
	}

	/**
	 * Returns the number of st adjustment reserve details.
	 *
	 * @return the number of st adjustment reserve details
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STADJUSTMENTRESERVEDETAIL);

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
		return StAdjustmentReserveDetailModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st adjustment reserve detail persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StAdjustmentReserveDetailImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STADJUSTMENTRESERVEDETAIL = "SELECT stAdjustmentReserveDetail FROM StAdjustmentReserveDetail stAdjustmentReserveDetail";
	private static final String _SQL_SELECT_STADJUSTMENTRESERVEDETAIL_WHERE_PKS_IN =
		"SELECT stAdjustmentReserveDetail FROM StAdjustmentReserveDetail stAdjustmentReserveDetail WHERE WORKFLOW_ID IN (";
	private static final String _SQL_COUNT_STADJUSTMENTRESERVEDETAIL = "SELECT COUNT(stAdjustmentReserveDetail) FROM StAdjustmentReserveDetail stAdjustmentReserveDetail";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stAdjustmentReserveDetail.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAdjustmentReserveDetail exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StAdjustmentReserveDetailPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustmentCreatedDate", "etlCheckRecord", "postingIndicator",
				"modifiedDate", "account", "credit", "workflowApprovedDate",
				"source", "lineDescription", "ledger", "udc6", "udc5", "udc4",
				"workflowCreatedDate", "udc3", "udc2", "udc1", "adjustmentType",
				"modifiedBy", "checkRecord", "glCompanyName", "division",
				"balanceType", "sessionId", "journalName", "project", "debit",
				"accountType", "journalDescription", "category", "createdBy",
				"createdDate", "businessUnitId", "reversalPeriodDate",
				"workflowId", "chartOfAccounts", "userId", "batchName",
				"database", "costCenter", "outboundStatus", "dataAccessSet",
				"future1", "future2", "workflowName", "workflowCreatedBy",
				"currency", "batchId", "accountCategory", "reverseJournal",
				"workflowApprovedBy", "brand", "accountingDate",
				"redemptionPeriod", "originalBatchId", "adjustmentLevel"
			});
}