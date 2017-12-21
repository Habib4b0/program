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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchStAdjustmentGtnDetailException;
import com.stpl.app.parttwo.model.StAdjustmentGtnDetail;
import com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailImpl;
import com.stpl.app.parttwo.model.impl.StAdjustmentGtnDetailModelImpl;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPersistence;

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
 * The persistence implementation for the st adjustment gtn detail service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAdjustmentGtnDetailPersistence
 * @see com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailUtil
 * @generated
 */
@ProviderType
public class StAdjustmentGtnDetailPersistenceImpl extends BasePersistenceImpl<StAdjustmentGtnDetail>
	implements StAdjustmentGtnDetailPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StAdjustmentGtnDetailUtil} to access the st adjustment gtn detail persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StAdjustmentGtnDetailImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED,
			StAdjustmentGtnDetailImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED,
			StAdjustmentGtnDetailImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StAdjustmentGtnDetailPersistenceImpl() {
		setModelClass(StAdjustmentGtnDetail.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjustmentCreatedDate", "ADJUSTMENT_CREATED_DATE");
			dbColumnNames.put("etlCheckRecord", "ETL_CHECK_RECORD");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("redemptionPeriod", "REDEMPTION_PERIOD");
			dbColumnNames.put("deductionId", "DEDUCTION_ID");
			dbColumnNames.put("glYear", "GL_YEAR");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("workflowApprovedDate", "WORKFLOW_APPROVED_DATE");
			dbColumnNames.put("udc6", "UDC_6");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("udc5", "UDC_5");
			dbColumnNames.put("workflowCreatedDate", "WORKFLOW_CREATED_DATE");
			dbColumnNames.put("udc4", "UDC_4");
			dbColumnNames.put("udc3", "UDC_3");
			dbColumnNames.put("udc2", "UDC_2");
			dbColumnNames.put("udc1", "UDC_1");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("deductionRate", "DEDUCTION_RATE");
			dbColumnNames.put("deductionCategory", "DEDUCTION_CATEGORY");
			dbColumnNames.put("deductionNo", "DEDUCTION_NO");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("glcompanyId", "GL_COMPANY_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("deductionInclusion", "DEDUCTION_INCLUSION");
			dbColumnNames.put("deductionAmount", "DEDUCTION_AMOUNT");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("project", "PROJECT");
			dbColumnNames.put("deductionUdc3", "DEDUCTION_UDC_3");
			dbColumnNames.put("deductionUdc4", "DEDUCTION_UDC_4");
			dbColumnNames.put("deductionUdc1", "DEDUCTION_UDC_1");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("deductionUdc2", "DEDUCTION_UDC_2");
			dbColumnNames.put("accountType", "ACCOUNT_TYPE");
			dbColumnNames.put("glString", "GL_STRING");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionUdc6", "DEDUCTION_UDC_6");
			dbColumnNames.put("deductionUdc5", "DEDUCTION_UDC_5");
			dbColumnNames.put("glCompanyName", "GL_COMPANY_NAME");
			dbColumnNames.put("workflowId", "WORKFLOW_ID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("deductionProgram", "DEDUCTION_PROGRAM");
			dbColumnNames.put("businessUnitId", "BUSINESS_UNIT_ID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("outboundStatus", "OUTBOUND_STATUS");
			dbColumnNames.put("future1", "FUTURE_1");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("deductionName", "DEDUCTION_NAME");
			dbColumnNames.put("future2", "FUTURE_2");
			dbColumnNames.put("workflowName", "WORKFLOW_NAME");
			dbColumnNames.put("glDate", "GL_DATE");
			dbColumnNames.put("workflowCreatedBy", "WORKFLOW_CREATED_BY");
			dbColumnNames.put("glMonth", "GL_MONTH");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("accountCategory", "ACCOUNT_CATEGORY");
			dbColumnNames.put("glCompanyNo", "GL_COMPANY_NO");
			dbColumnNames.put("workflowApprovedBy", "WORKFLOW_APPROVED_BY");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
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
	 * Caches the st adjustment gtn detail in the entity cache if it is enabled.
	 *
	 * @param stAdjustmentGtnDetail the st adjustment gtn detail
	 */
	@Override
	public void cacheResult(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		entityCache.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailImpl.class,
			stAdjustmentGtnDetail.getPrimaryKey(), stAdjustmentGtnDetail);

		stAdjustmentGtnDetail.resetOriginalValues();
	}

	/**
	 * Caches the st adjustment gtn details in the entity cache if it is enabled.
	 *
	 * @param stAdjustmentGtnDetails the st adjustment gtn details
	 */
	@Override
	public void cacheResult(List<StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
		for (StAdjustmentGtnDetail stAdjustmentGtnDetail : stAdjustmentGtnDetails) {
			if (entityCache.getResult(
						StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
						StAdjustmentGtnDetailImpl.class,
						stAdjustmentGtnDetail.getPrimaryKey()) == null) {
				cacheResult(stAdjustmentGtnDetail);
			}
			else {
				stAdjustmentGtnDetail.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st adjustment gtn details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StAdjustmentGtnDetailImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st adjustment gtn detail.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		entityCache.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailImpl.class,
			stAdjustmentGtnDetail.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StAdjustmentGtnDetail> stAdjustmentGtnDetails) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StAdjustmentGtnDetail stAdjustmentGtnDetail : stAdjustmentGtnDetails) {
			entityCache.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
				StAdjustmentGtnDetailImpl.class,
				stAdjustmentGtnDetail.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st adjustment gtn detail with the primary key. Does not add the st adjustment gtn detail to the database.
	 *
	 * @param workflowId the primary key for the new st adjustment gtn detail
	 * @return the new st adjustment gtn detail
	 */
	@Override
	public StAdjustmentGtnDetail create(String workflowId) {
		StAdjustmentGtnDetail stAdjustmentGtnDetail = new StAdjustmentGtnDetailImpl();

		stAdjustmentGtnDetail.setNew(true);
		stAdjustmentGtnDetail.setPrimaryKey(workflowId);

		stAdjustmentGtnDetail.setCompanyId(companyProvider.getCompanyId());

		return stAdjustmentGtnDetail;
	}

	/**
	 * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param workflowId the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail that was removed
	 * @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail remove(String workflowId)
		throws NoSuchStAdjustmentGtnDetailException {
		return remove((Serializable)workflowId);
	}

	/**
	 * Removes the st adjustment gtn detail with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail that was removed
	 * @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail remove(Serializable primaryKey)
		throws NoSuchStAdjustmentGtnDetailException {
		Session session = null;

		try {
			session = openSession();

			StAdjustmentGtnDetail stAdjustmentGtnDetail = (StAdjustmentGtnDetail)session.get(StAdjustmentGtnDetailImpl.class,
					primaryKey);

			if (stAdjustmentGtnDetail == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStAdjustmentGtnDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stAdjustmentGtnDetail);
		}
		catch (NoSuchStAdjustmentGtnDetailException nsee) {
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
	protected StAdjustmentGtnDetail removeImpl(
		StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		stAdjustmentGtnDetail = toUnwrappedModel(stAdjustmentGtnDetail);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stAdjustmentGtnDetail)) {
				stAdjustmentGtnDetail = (StAdjustmentGtnDetail)session.get(StAdjustmentGtnDetailImpl.class,
						stAdjustmentGtnDetail.getPrimaryKeyObj());
			}

			if (stAdjustmentGtnDetail != null) {
				session.delete(stAdjustmentGtnDetail);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stAdjustmentGtnDetail != null) {
			clearCache(stAdjustmentGtnDetail);
		}

		return stAdjustmentGtnDetail;
	}

	@Override
	public StAdjustmentGtnDetail updateImpl(
		StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		stAdjustmentGtnDetail = toUnwrappedModel(stAdjustmentGtnDetail);

		boolean isNew = stAdjustmentGtnDetail.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stAdjustmentGtnDetail.isNew()) {
				session.save(stAdjustmentGtnDetail);

				stAdjustmentGtnDetail.setNew(false);
			}
			else {
				stAdjustmentGtnDetail = (StAdjustmentGtnDetail)session.merge(stAdjustmentGtnDetail);
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

		entityCache.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
			StAdjustmentGtnDetailImpl.class,
			stAdjustmentGtnDetail.getPrimaryKey(), stAdjustmentGtnDetail, false);

		stAdjustmentGtnDetail.resetOriginalValues();

		return stAdjustmentGtnDetail;
	}

	protected StAdjustmentGtnDetail toUnwrappedModel(
		StAdjustmentGtnDetail stAdjustmentGtnDetail) {
		if (stAdjustmentGtnDetail instanceof StAdjustmentGtnDetailImpl) {
			return stAdjustmentGtnDetail;
		}

		StAdjustmentGtnDetailImpl stAdjustmentGtnDetailImpl = new StAdjustmentGtnDetailImpl();

		stAdjustmentGtnDetailImpl.setNew(stAdjustmentGtnDetail.isNew());
		stAdjustmentGtnDetailImpl.setPrimaryKey(stAdjustmentGtnDetail.getPrimaryKey());

		stAdjustmentGtnDetailImpl.setAdjustmentCreatedDate(stAdjustmentGtnDetail.getAdjustmentCreatedDate());
		stAdjustmentGtnDetailImpl.setEtlCheckRecord(stAdjustmentGtnDetail.isEtlCheckRecord());
		stAdjustmentGtnDetailImpl.setBusinessUnitNo(stAdjustmentGtnDetail.getBusinessUnitNo());
		stAdjustmentGtnDetailImpl.setRedemptionPeriod(stAdjustmentGtnDetail.getRedemptionPeriod());
		stAdjustmentGtnDetailImpl.setDeductionId(stAdjustmentGtnDetail.getDeductionId());
		stAdjustmentGtnDetailImpl.setGlYear(stAdjustmentGtnDetail.getGlYear());
		stAdjustmentGtnDetailImpl.setBrandName(stAdjustmentGtnDetail.getBrandName());
		stAdjustmentGtnDetailImpl.setModifiedDate(stAdjustmentGtnDetail.getModifiedDate());
		stAdjustmentGtnDetailImpl.setAccount(stAdjustmentGtnDetail.getAccount());
		stAdjustmentGtnDetailImpl.setSource(stAdjustmentGtnDetail.getSource());
		stAdjustmentGtnDetailImpl.setWorkflowApprovedDate(stAdjustmentGtnDetail.getWorkflowApprovedDate());
		stAdjustmentGtnDetailImpl.setUdc6(stAdjustmentGtnDetail.getUdc6());
		stAdjustmentGtnDetailImpl.setBusinessUnitName(stAdjustmentGtnDetail.getBusinessUnitName());
		stAdjustmentGtnDetailImpl.setUdc5(stAdjustmentGtnDetail.getUdc5());
		stAdjustmentGtnDetailImpl.setWorkflowCreatedDate(stAdjustmentGtnDetail.getWorkflowCreatedDate());
		stAdjustmentGtnDetailImpl.setUdc4(stAdjustmentGtnDetail.getUdc4());
		stAdjustmentGtnDetailImpl.setUdc3(stAdjustmentGtnDetail.getUdc3());
		stAdjustmentGtnDetailImpl.setUdc2(stAdjustmentGtnDetail.getUdc2());
		stAdjustmentGtnDetailImpl.setUdc1(stAdjustmentGtnDetail.getUdc1());
		stAdjustmentGtnDetailImpl.setAdjustmentType(stAdjustmentGtnDetail.getAdjustmentType());
		stAdjustmentGtnDetailImpl.setModifiedBy(stAdjustmentGtnDetail.getModifiedBy());
		stAdjustmentGtnDetailImpl.setDeductionType(stAdjustmentGtnDetail.getDeductionType());
		stAdjustmentGtnDetailImpl.setCheckRecord(stAdjustmentGtnDetail.isCheckRecord());
		stAdjustmentGtnDetailImpl.setContractName(stAdjustmentGtnDetail.getContractName());
		stAdjustmentGtnDetailImpl.setDeductionRate(stAdjustmentGtnDetail.getDeductionRate());
		stAdjustmentGtnDetailImpl.setDeductionCategory(stAdjustmentGtnDetail.getDeductionCategory());
		stAdjustmentGtnDetailImpl.setDeductionNo(stAdjustmentGtnDetail.getDeductionNo());
		stAdjustmentGtnDetailImpl.setCompanyNo(stAdjustmentGtnDetail.getCompanyNo());
		stAdjustmentGtnDetailImpl.setSessionId(stAdjustmentGtnDetail.getSessionId());
		stAdjustmentGtnDetailImpl.setGlcompanyId(stAdjustmentGtnDetail.getGlcompanyId());
		stAdjustmentGtnDetailImpl.setItemName(stAdjustmentGtnDetail.getItemName());
		stAdjustmentGtnDetailImpl.setDeductionInclusion(stAdjustmentGtnDetail.getDeductionInclusion());
		stAdjustmentGtnDetailImpl.setDeductionAmount(stAdjustmentGtnDetail.getDeductionAmount());
		stAdjustmentGtnDetailImpl.setCompanyName(stAdjustmentGtnDetail.getCompanyName());
		stAdjustmentGtnDetailImpl.setProject(stAdjustmentGtnDetail.getProject());
		stAdjustmentGtnDetailImpl.setDeductionUdc3(stAdjustmentGtnDetail.getDeductionUdc3());
		stAdjustmentGtnDetailImpl.setDeductionUdc4(stAdjustmentGtnDetail.getDeductionUdc4());
		stAdjustmentGtnDetailImpl.setDeductionUdc1(stAdjustmentGtnDetail.getDeductionUdc1());
		stAdjustmentGtnDetailImpl.setItemId(stAdjustmentGtnDetail.getItemId());
		stAdjustmentGtnDetailImpl.setDeductionUdc2(stAdjustmentGtnDetail.getDeductionUdc2());
		stAdjustmentGtnDetailImpl.setAccountType(stAdjustmentGtnDetail.getAccountType());
		stAdjustmentGtnDetailImpl.setGlString(stAdjustmentGtnDetail.getGlString());
		stAdjustmentGtnDetailImpl.setCreatedDate(stAdjustmentGtnDetail.getCreatedDate());
		stAdjustmentGtnDetailImpl.setCreatedBy(stAdjustmentGtnDetail.getCreatedBy());
		stAdjustmentGtnDetailImpl.setDeductionUdc6(stAdjustmentGtnDetail.getDeductionUdc6());
		stAdjustmentGtnDetailImpl.setDeductionUdc5(stAdjustmentGtnDetail.getDeductionUdc5());
		stAdjustmentGtnDetailImpl.setGlCompanyName(stAdjustmentGtnDetail.getGlCompanyName());
		stAdjustmentGtnDetailImpl.setWorkflowId(stAdjustmentGtnDetail.getWorkflowId());
		stAdjustmentGtnDetailImpl.setItemNo(stAdjustmentGtnDetail.getItemNo());
		stAdjustmentGtnDetailImpl.setContractId(stAdjustmentGtnDetail.getContractId());
		stAdjustmentGtnDetailImpl.setDeductionProgram(stAdjustmentGtnDetail.getDeductionProgram());
		stAdjustmentGtnDetailImpl.setBusinessUnitId(stAdjustmentGtnDetail.getBusinessUnitId());
		stAdjustmentGtnDetailImpl.setUserId(stAdjustmentGtnDetail.getUserId());
		stAdjustmentGtnDetailImpl.setCostCenter(stAdjustmentGtnDetail.getCostCenter());
		stAdjustmentGtnDetailImpl.setCompanyId(stAdjustmentGtnDetail.getCompanyId());
		stAdjustmentGtnDetailImpl.setOutboundStatus(stAdjustmentGtnDetail.getOutboundStatus());
		stAdjustmentGtnDetailImpl.setFuture1(stAdjustmentGtnDetail.getFuture1());
		stAdjustmentGtnDetailImpl.setBrandId(stAdjustmentGtnDetail.getBrandId());
		stAdjustmentGtnDetailImpl.setDeductionName(stAdjustmentGtnDetail.getDeductionName());
		stAdjustmentGtnDetailImpl.setFuture2(stAdjustmentGtnDetail.getFuture2());
		stAdjustmentGtnDetailImpl.setWorkflowName(stAdjustmentGtnDetail.getWorkflowName());
		stAdjustmentGtnDetailImpl.setGlDate(stAdjustmentGtnDetail.getGlDate());
		stAdjustmentGtnDetailImpl.setWorkflowCreatedBy(stAdjustmentGtnDetail.getWorkflowCreatedBy());
		stAdjustmentGtnDetailImpl.setGlMonth(stAdjustmentGtnDetail.getGlMonth());
		stAdjustmentGtnDetailImpl.setBatchId(stAdjustmentGtnDetail.getBatchId());
		stAdjustmentGtnDetailImpl.setAccountCategory(stAdjustmentGtnDetail.getAccountCategory());
		stAdjustmentGtnDetailImpl.setGlCompanyNo(stAdjustmentGtnDetail.getGlCompanyNo());
		stAdjustmentGtnDetailImpl.setWorkflowApprovedBy(stAdjustmentGtnDetail.getWorkflowApprovedBy());
		stAdjustmentGtnDetailImpl.setContractNo(stAdjustmentGtnDetail.getContractNo());
		stAdjustmentGtnDetailImpl.setOriginalBatchId(stAdjustmentGtnDetail.getOriginalBatchId());
		stAdjustmentGtnDetailImpl.setAdjustmentLevel(stAdjustmentGtnDetail.getAdjustmentLevel());

		return stAdjustmentGtnDetailImpl;
	}

	/**
	 * Returns the st adjustment gtn detail with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail
	 * @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStAdjustmentGtnDetailException {
		StAdjustmentGtnDetail stAdjustmentGtnDetail = fetchByPrimaryKey(primaryKey);

		if (stAdjustmentGtnDetail == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStAdjustmentGtnDetailException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stAdjustmentGtnDetail;
	}

	/**
	 * Returns the st adjustment gtn detail with the primary key or throws a {@link NoSuchStAdjustmentGtnDetailException} if it could not be found.
	 *
	 * @param workflowId the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail
	 * @throws NoSuchStAdjustmentGtnDetailException if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail findByPrimaryKey(String workflowId)
		throws NoSuchStAdjustmentGtnDetailException {
		return findByPrimaryKey((Serializable)workflowId);
	}

	/**
	 * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
				StAdjustmentGtnDetailImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StAdjustmentGtnDetail stAdjustmentGtnDetail = (StAdjustmentGtnDetail)serializable;

		if (stAdjustmentGtnDetail == null) {
			Session session = null;

			try {
				session = openSession();

				stAdjustmentGtnDetail = (StAdjustmentGtnDetail)session.get(StAdjustmentGtnDetailImpl.class,
						primaryKey);

				if (stAdjustmentGtnDetail != null) {
					cacheResult(stAdjustmentGtnDetail);
				}
				else {
					entityCache.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
						StAdjustmentGtnDetailImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentGtnDetailImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stAdjustmentGtnDetail;
	}

	/**
	 * Returns the st adjustment gtn detail with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param workflowId the primary key of the st adjustment gtn detail
	 * @return the st adjustment gtn detail, or <code>null</code> if a st adjustment gtn detail with the primary key could not be found
	 */
	@Override
	public StAdjustmentGtnDetail fetchByPrimaryKey(String workflowId) {
		return fetchByPrimaryKey((Serializable)workflowId);
	}

	@Override
	public Map<Serializable, StAdjustmentGtnDetail> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StAdjustmentGtnDetail> map = new HashMap<Serializable, StAdjustmentGtnDetail>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StAdjustmentGtnDetail stAdjustmentGtnDetail = fetchByPrimaryKey(primaryKey);

			if (stAdjustmentGtnDetail != null) {
				map.put(primaryKey, stAdjustmentGtnDetail);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentGtnDetailImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StAdjustmentGtnDetail)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STADJUSTMENTGTNDETAIL_WHERE_PKS_IN);

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

			for (StAdjustmentGtnDetail stAdjustmentGtnDetail : (List<StAdjustmentGtnDetail>)q.list()) {
				map.put(stAdjustmentGtnDetail.getPrimaryKeyObj(),
					stAdjustmentGtnDetail);

				cacheResult(stAdjustmentGtnDetail);

				uncachedPrimaryKeys.remove(stAdjustmentGtnDetail.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StAdjustmentGtnDetailModelImpl.ENTITY_CACHE_ENABLED,
					StAdjustmentGtnDetailImpl.class, primaryKey, nullModel);
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
	 * Returns all the st adjustment gtn details.
	 *
	 * @return the st adjustment gtn details
	 */
	@Override
	public List<StAdjustmentGtnDetail> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st adjustment gtn details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment gtn details
	 * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	 * @return the range of st adjustment gtn details
	 */
	@Override
	public List<StAdjustmentGtnDetail> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st adjustment gtn details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment gtn details
	 * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st adjustment gtn details
	 */
	@Override
	public List<StAdjustmentGtnDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentGtnDetail> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st adjustment gtn details.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StAdjustmentGtnDetailModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st adjustment gtn details
	 * @param end the upper bound of the range of st adjustment gtn details (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st adjustment gtn details
	 */
	@Override
	public List<StAdjustmentGtnDetail> findAll(int start, int end,
		OrderByComparator<StAdjustmentGtnDetail> orderByComparator,
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

		List<StAdjustmentGtnDetail> list = null;

		if (retrieveFromCache) {
			list = (List<StAdjustmentGtnDetail>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STADJUSTMENTGTNDETAIL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STADJUSTMENTGTNDETAIL;

				if (pagination) {
					sql = sql.concat(StAdjustmentGtnDetailModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StAdjustmentGtnDetail>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StAdjustmentGtnDetail>)QueryUtil.list(q,
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
	 * Removes all the st adjustment gtn details from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StAdjustmentGtnDetail stAdjustmentGtnDetail : findAll()) {
			remove(stAdjustmentGtnDetail);
		}
	}

	/**
	 * Returns the number of st adjustment gtn details.
	 *
	 * @return the number of st adjustment gtn details
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STADJUSTMENTGTNDETAIL);

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
		return StAdjustmentGtnDetailModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st adjustment gtn detail persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StAdjustmentGtnDetailImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STADJUSTMENTGTNDETAIL = "SELECT stAdjustmentGtnDetail FROM StAdjustmentGtnDetail stAdjustmentGtnDetail";
	private static final String _SQL_SELECT_STADJUSTMENTGTNDETAIL_WHERE_PKS_IN = "SELECT stAdjustmentGtnDetail FROM StAdjustmentGtnDetail stAdjustmentGtnDetail WHERE WORKFLOW_ID IN (";
	private static final String _SQL_COUNT_STADJUSTMENTGTNDETAIL = "SELECT COUNT(stAdjustmentGtnDetail) FROM StAdjustmentGtnDetail stAdjustmentGtnDetail";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stAdjustmentGtnDetail.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StAdjustmentGtnDetail exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StAdjustmentGtnDetailPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjustmentCreatedDate", "etlCheckRecord", "businessUnitNo",
				"redemptionPeriod", "deductionId", "glYear", "brandName",
				"modifiedDate", "account", "source", "workflowApprovedDate",
				"udc6", "businessUnitName", "udc5", "workflowCreatedDate",
				"udc4", "udc3", "udc2", "udc1", "adjustmentType", "modifiedBy",
				"deductionType", "checkRecord", "contractName", "deductionRate",
				"deductionCategory", "deductionNo", "companyNo", "sessionId",
				"glcompanyId", "itemName", "deductionInclusion",
				"deductionAmount", "companyName", "project", "deductionUdc3",
				"deductionUdc4", "deductionUdc1", "itemId", "deductionUdc2",
				"accountType", "glString", "createdDate", "createdBy",
				"deductionUdc6", "deductionUdc5", "glCompanyName", "workflowId",
				"itemNo", "contractId", "deductionProgram", "businessUnitId",
				"userId", "costCenter", "companyId", "outboundStatus", "future1",
				"brandId", "deductionName", "future2", "workflowName", "glDate",
				"workflowCreatedBy", "glMonth", "batchId", "accountCategory",
				"glCompanyNo", "workflowApprovedBy", "contractNo",
				"originalBatchId", "adjustmentLevel"
			});
}