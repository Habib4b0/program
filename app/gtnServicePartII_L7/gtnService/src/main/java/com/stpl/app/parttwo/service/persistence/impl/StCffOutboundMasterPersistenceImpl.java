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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchStCffOutboundMasterException;
import com.stpl.app.parttwo.model.StCffOutboundMaster;
import com.stpl.app.parttwo.model.impl.StCffOutboundMasterImpl;
import com.stpl.app.parttwo.model.impl.StCffOutboundMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st cff outbound master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StCffOutboundMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.StCffOutboundMasterUtil
 * @generated
 */
@ProviderType
public class StCffOutboundMasterPersistenceImpl extends BasePersistenceImpl<StCffOutboundMaster>
	implements StCffOutboundMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StCffOutboundMasterUtil} to access the st cff outbound master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StCffOutboundMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED,
			StCffOutboundMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED,
			StCffOutboundMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StCffOutboundMasterPersistenceImpl() {
		setModelClass(StCffOutboundMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("etlCheckRecord", "ETL_CHECK_RECORD");
			dbColumnNames.put("customerName", "CUSTOMER_NAME");
			dbColumnNames.put("contractHolderId", "CONTRACT_HOLDER_ID");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("financialForecastApprovalDate",
				"FINANCIAL_FORECAST_APPROVAL_DATE");
			dbColumnNames.put("deductionId", "DEDUCTION_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("deductionPerUnit", "DEDUCTION_PER_UNIT");
			dbColumnNames.put("cogsPerUnit", "COGS_PER_UNIT");
			dbColumnNames.put("contractType", "CONTRACT_TYPE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("financialForecastId", "FINANCIAL_FORECAST_ID");
			dbColumnNames.put("projectId", "PROJECT_ID");
			dbColumnNames.put("customerNo", "CUSTOMER_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("salesDollars", "SALES_DOLLARS");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("cffDetailsSid", "CFF_DETAILS_SID");
			dbColumnNames.put("type", "TYPE");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("deductionRate", "DEDUCTION_RATE");
			dbColumnNames.put("deductionCategory", "DEDUCTION_CATEGORY");
			dbColumnNames.put("cogsAmount", "COGS_AMOUNT");
			dbColumnNames.put("deductionNo", "DEDUCTION_NO");
			dbColumnNames.put("financialForecastCreationDate",
				"FINANCIAL_FORECAST_CREATION_DATE");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("salesUnits", "SALES_UNITS");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("deductionInclusion", "DEDUCTION_INCLUSION");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("contractHolderName", "CONTRACT_HOLDER_NAME");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("customerId", "CUSTOMER_ID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("netProfitDollars", "NET_PROFIT_DOLLARS");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionCategory1", "DEDUCTION_CATEGORY1");
			dbColumnNames.put("deductionCategory2", "DEDUCTION_CATEGORY2");
			dbColumnNames.put("contractHolderNo", "CONTRACT_HOLDER_NO");
			dbColumnNames.put("deductionCategory3", "DEDUCTION_CATEGORY3");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("deductionCategory4", "DEDUCTION_CATEGORY4");
			dbColumnNames.put("deductionCategory5", "DEDUCTION_CATEGORY5");
			dbColumnNames.put("deductionCategory6", "DEDUCTION_CATEGORY6");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("deductionProgram", "DEDUCTION_PROGRAM");
			dbColumnNames.put("businessUnitId", "BUSINESS_UNIT_ID");
			dbColumnNames.put("projectionName", "PROJECTION_NAME");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("outboundStatus", "OUTBOUND_STATUS");
			dbColumnNames.put("originalBatchId", "ORIGINAL_BATCH_ID");
			dbColumnNames.put("deductionName", "DEDUCTION_NAME");
			dbColumnNames.put("netProfitPerUnit", "NET_PROFIT_PER_UNIT");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("salesInclusion", "SALES_INCLUSION");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("financialForecastName", "FINANCIAL_FORECAST_NAME");
			dbColumnNames.put("netSalesDollar", "NET_SALES_DOLLAR");
			dbColumnNames.put("deductionDollars", "DEDUCTION_DOLLARS");
			dbColumnNames.put("contractNo", "CONTRACT_NO");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st cff outbound master in the entity cache if it is enabled.
	 *
	 * @param stCffOutboundMaster the st cff outbound master
	 */
	@Override
	public void cacheResult(StCffOutboundMaster stCffOutboundMaster) {
		entityCache.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey(),
			stCffOutboundMaster);

		stCffOutboundMaster.resetOriginalValues();
	}

	/**
	 * Caches the st cff outbound masters in the entity cache if it is enabled.
	 *
	 * @param stCffOutboundMasters the st cff outbound masters
	 */
	@Override
	public void cacheResult(List<StCffOutboundMaster> stCffOutboundMasters) {
		for (StCffOutboundMaster stCffOutboundMaster : stCffOutboundMasters) {
			if (entityCache.getResult(
						StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
						StCffOutboundMasterImpl.class,
						stCffOutboundMaster.getPrimaryKey()) == null) {
				cacheResult(stCffOutboundMaster);
			}
			else {
				stCffOutboundMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st cff outbound masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StCffOutboundMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st cff outbound master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StCffOutboundMaster stCffOutboundMaster) {
		entityCache.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StCffOutboundMaster> stCffOutboundMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StCffOutboundMaster stCffOutboundMaster : stCffOutboundMasters) {
			entityCache.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
				StCffOutboundMasterImpl.class,
				stCffOutboundMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st cff outbound master with the primary key. Does not add the st cff outbound master to the database.
	 *
	 * @param stCffOutboundMasterPK the primary key for the new st cff outbound master
	 * @return the new st cff outbound master
	 */
	@Override
	public StCffOutboundMaster create(
		StCffOutboundMasterPK stCffOutboundMasterPK) {
		StCffOutboundMaster stCffOutboundMaster = new StCffOutboundMasterImpl();

		stCffOutboundMaster.setNew(true);
		stCffOutboundMaster.setPrimaryKey(stCffOutboundMasterPK);

		stCffOutboundMaster.setCompanyId(companyProvider.getCompanyId());

		return stCffOutboundMaster;
	}

	/**
	 * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stCffOutboundMasterPK the primary key of the st cff outbound master
	 * @return the st cff outbound master that was removed
	 * @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster remove(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws NoSuchStCffOutboundMasterException {
		return remove((Serializable)stCffOutboundMasterPK);
	}

	/**
	 * Removes the st cff outbound master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st cff outbound master
	 * @return the st cff outbound master that was removed
	 * @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster remove(Serializable primaryKey)
		throws NoSuchStCffOutboundMasterException {
		Session session = null;

		try {
			session = openSession();

			StCffOutboundMaster stCffOutboundMaster = (StCffOutboundMaster)session.get(StCffOutboundMasterImpl.class,
					primaryKey);

			if (stCffOutboundMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStCffOutboundMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stCffOutboundMaster);
		}
		catch (NoSuchStCffOutboundMasterException nsee) {
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
	protected StCffOutboundMaster removeImpl(
		StCffOutboundMaster stCffOutboundMaster) {
		stCffOutboundMaster = toUnwrappedModel(stCffOutboundMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stCffOutboundMaster)) {
				stCffOutboundMaster = (StCffOutboundMaster)session.get(StCffOutboundMasterImpl.class,
						stCffOutboundMaster.getPrimaryKeyObj());
			}

			if (stCffOutboundMaster != null) {
				session.delete(stCffOutboundMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stCffOutboundMaster != null) {
			clearCache(stCffOutboundMaster);
		}

		return stCffOutboundMaster;
	}

	@Override
	public StCffOutboundMaster updateImpl(
		StCffOutboundMaster stCffOutboundMaster) {
		stCffOutboundMaster = toUnwrappedModel(stCffOutboundMaster);

		boolean isNew = stCffOutboundMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stCffOutboundMaster.isNew()) {
				session.save(stCffOutboundMaster);

				stCffOutboundMaster.setNew(false);
			}
			else {
				stCffOutboundMaster = (StCffOutboundMaster)session.merge(stCffOutboundMaster);
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

		entityCache.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
			StCffOutboundMasterImpl.class, stCffOutboundMaster.getPrimaryKey(),
			stCffOutboundMaster, false);

		stCffOutboundMaster.resetOriginalValues();

		return stCffOutboundMaster;
	}

	protected StCffOutboundMaster toUnwrappedModel(
		StCffOutboundMaster stCffOutboundMaster) {
		if (stCffOutboundMaster instanceof StCffOutboundMasterImpl) {
			return stCffOutboundMaster;
		}

		StCffOutboundMasterImpl stCffOutboundMasterImpl = new StCffOutboundMasterImpl();

		stCffOutboundMasterImpl.setNew(stCffOutboundMaster.isNew());
		stCffOutboundMasterImpl.setPrimaryKey(stCffOutboundMaster.getPrimaryKey());

		stCffOutboundMasterImpl.setEtlCheckRecord(stCffOutboundMaster.isEtlCheckRecord());
		stCffOutboundMasterImpl.setCustomerName(stCffOutboundMaster.getCustomerName());
		stCffOutboundMasterImpl.setContractHolderId(stCffOutboundMaster.getContractHolderId());
		stCffOutboundMasterImpl.setBusinessUnitNo(stCffOutboundMaster.getBusinessUnitNo());
		stCffOutboundMasterImpl.setYear(stCffOutboundMaster.getYear());
		stCffOutboundMasterImpl.setFinancialForecastApprovalDate(stCffOutboundMaster.getFinancialForecastApprovalDate());
		stCffOutboundMasterImpl.setDeductionId(stCffOutboundMaster.getDeductionId());
		stCffOutboundMasterImpl.setModifiedDate(stCffOutboundMaster.getModifiedDate());
		stCffOutboundMasterImpl.setDeductionPerUnit(stCffOutboundMaster.getDeductionPerUnit());
		stCffOutboundMasterImpl.setCogsPerUnit(stCffOutboundMaster.getCogsPerUnit());
		stCffOutboundMasterImpl.setContractType(stCffOutboundMaster.getContractType());
		stCffOutboundMasterImpl.setSource(stCffOutboundMaster.getSource());
		stCffOutboundMasterImpl.setBusinessUnitName(stCffOutboundMaster.getBusinessUnitName());
		stCffOutboundMasterImpl.setContractMasterSid(stCffOutboundMaster.getContractMasterSid());
		stCffOutboundMasterImpl.setFinancialForecastId(stCffOutboundMaster.getFinancialForecastId());
		stCffOutboundMasterImpl.setProjectId(stCffOutboundMaster.getProjectId());
		stCffOutboundMasterImpl.setCustomerNo(stCffOutboundMaster.getCustomerNo());
		stCffOutboundMasterImpl.setModifiedBy(stCffOutboundMaster.getModifiedBy());
		stCffOutboundMasterImpl.setSalesDollars(stCffOutboundMaster.getSalesDollars());
		stCffOutboundMasterImpl.setMonth(stCffOutboundMaster.getMonth());
		stCffOutboundMasterImpl.setCffDetailsSid(stCffOutboundMaster.getCffDetailsSid());
		stCffOutboundMasterImpl.setType(stCffOutboundMaster.getType());
		stCffOutboundMasterImpl.setDeductionType(stCffOutboundMaster.getDeductionType());
		stCffOutboundMasterImpl.setCompanyMasterSid(stCffOutboundMaster.getCompanyMasterSid());
		stCffOutboundMasterImpl.setCheckRecord(stCffOutboundMaster.isCheckRecord());
		stCffOutboundMasterImpl.setContractName(stCffOutboundMaster.getContractName());
		stCffOutboundMasterImpl.setDeductionRate(stCffOutboundMaster.getDeductionRate());
		stCffOutboundMasterImpl.setDeductionCategory(stCffOutboundMaster.getDeductionCategory());
		stCffOutboundMasterImpl.setCogsAmount(stCffOutboundMaster.getCogsAmount());
		stCffOutboundMasterImpl.setDeductionNo(stCffOutboundMaster.getDeductionNo());
		stCffOutboundMasterImpl.setFinancialForecastCreationDate(stCffOutboundMaster.getFinancialForecastCreationDate());
		stCffOutboundMasterImpl.setCompanyNo(stCffOutboundMaster.getCompanyNo());
		stCffOutboundMasterImpl.setSalesUnits(stCffOutboundMaster.getSalesUnits());
		stCffOutboundMasterImpl.setSessionId(stCffOutboundMaster.getSessionId());
		stCffOutboundMasterImpl.setItemName(stCffOutboundMaster.getItemName());
		stCffOutboundMasterImpl.setDeductionInclusion(stCffOutboundMaster.getDeductionInclusion());
		stCffOutboundMasterImpl.setRsModelSid(stCffOutboundMaster.getRsModelSid());
		stCffOutboundMasterImpl.setContractHolderName(stCffOutboundMaster.getContractHolderName());
		stCffOutboundMasterImpl.setItemMasterSid(stCffOutboundMaster.getItemMasterSid());
		stCffOutboundMasterImpl.setCompanyName(stCffOutboundMaster.getCompanyName());
		stCffOutboundMasterImpl.setCustomerId(stCffOutboundMaster.getCustomerId());
		stCffOutboundMasterImpl.setItemId(stCffOutboundMaster.getItemId());
		stCffOutboundMasterImpl.setNetProfitDollars(stCffOutboundMaster.getNetProfitDollars());
		stCffOutboundMasterImpl.setGlCompanyMasterSid(stCffOutboundMaster.getGlCompanyMasterSid());
		stCffOutboundMasterImpl.setCreatedDate(stCffOutboundMaster.getCreatedDate());
		stCffOutboundMasterImpl.setCreatedBy(stCffOutboundMaster.getCreatedBy());
		stCffOutboundMasterImpl.setDeductionCategory1(stCffOutboundMaster.getDeductionCategory1());
		stCffOutboundMasterImpl.setDeductionCategory2(stCffOutboundMaster.getDeductionCategory2());
		stCffOutboundMasterImpl.setContractHolderNo(stCffOutboundMaster.getContractHolderNo());
		stCffOutboundMasterImpl.setDeductionCategory3(stCffOutboundMaster.getDeductionCategory3());
		stCffOutboundMasterImpl.setItemNo(stCffOutboundMaster.getItemNo());
		stCffOutboundMasterImpl.setDeductionCategory4(stCffOutboundMaster.getDeductionCategory4());
		stCffOutboundMasterImpl.setDeductionCategory5(stCffOutboundMaster.getDeductionCategory5());
		stCffOutboundMasterImpl.setDeductionCategory6(stCffOutboundMaster.getDeductionCategory6());
		stCffOutboundMasterImpl.setContractId(stCffOutboundMaster.getContractId());
		stCffOutboundMasterImpl.setDeductionProgram(stCffOutboundMaster.getDeductionProgram());
		stCffOutboundMasterImpl.setBusinessUnitId(stCffOutboundMaster.getBusinessUnitId());
		stCffOutboundMasterImpl.setProjectionName(stCffOutboundMaster.getProjectionName());
		stCffOutboundMasterImpl.setUserId(stCffOutboundMaster.getUserId());
		stCffOutboundMasterImpl.setCompanyId(stCffOutboundMaster.getCompanyId());
		stCffOutboundMasterImpl.setOutboundStatus(stCffOutboundMaster.getOutboundStatus());
		stCffOutboundMasterImpl.setOriginalBatchId(stCffOutboundMaster.getOriginalBatchId());
		stCffOutboundMasterImpl.setDeductionName(stCffOutboundMaster.getDeductionName());
		stCffOutboundMasterImpl.setNetProfitPerUnit(stCffOutboundMaster.getNetProfitPerUnit());
		stCffOutboundMasterImpl.setPeriodSid(stCffOutboundMaster.getPeriodSid());
		stCffOutboundMasterImpl.setSalesInclusion(stCffOutboundMaster.getSalesInclusion());
		stCffOutboundMasterImpl.setBatchId(stCffOutboundMaster.getBatchId());
		stCffOutboundMasterImpl.setFinancialForecastName(stCffOutboundMaster.getFinancialForecastName());
		stCffOutboundMasterImpl.setNetSalesDollar(stCffOutboundMaster.getNetSalesDollar());
		stCffOutboundMasterImpl.setDeductionDollars(stCffOutboundMaster.getDeductionDollars());
		stCffOutboundMasterImpl.setContractNo(stCffOutboundMaster.getContractNo());

		return stCffOutboundMasterImpl;
	}

	/**
	 * Returns the st cff outbound master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st cff outbound master
	 * @return the st cff outbound master
	 * @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStCffOutboundMasterException {
		StCffOutboundMaster stCffOutboundMaster = fetchByPrimaryKey(primaryKey);

		if (stCffOutboundMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStCffOutboundMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stCffOutboundMaster;
	}

	/**
	 * Returns the st cff outbound master with the primary key or throws a {@link NoSuchStCffOutboundMasterException} if it could not be found.
	 *
	 * @param stCffOutboundMasterPK the primary key of the st cff outbound master
	 * @return the st cff outbound master
	 * @throws NoSuchStCffOutboundMasterException if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster findByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK)
		throws NoSuchStCffOutboundMasterException {
		return findByPrimaryKey((Serializable)stCffOutboundMasterPK);
	}

	/**
	 * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st cff outbound master
	 * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
				StCffOutboundMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StCffOutboundMaster stCffOutboundMaster = (StCffOutboundMaster)serializable;

		if (stCffOutboundMaster == null) {
			Session session = null;

			try {
				session = openSession();

				stCffOutboundMaster = (StCffOutboundMaster)session.get(StCffOutboundMasterImpl.class,
						primaryKey);

				if (stCffOutboundMaster != null) {
					cacheResult(stCffOutboundMaster);
				}
				else {
					entityCache.putResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
						StCffOutboundMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StCffOutboundMasterModelImpl.ENTITY_CACHE_ENABLED,
					StCffOutboundMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stCffOutboundMaster;
	}

	/**
	 * Returns the st cff outbound master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stCffOutboundMasterPK the primary key of the st cff outbound master
	 * @return the st cff outbound master, or <code>null</code> if a st cff outbound master with the primary key could not be found
	 */
	@Override
	public StCffOutboundMaster fetchByPrimaryKey(
		StCffOutboundMasterPK stCffOutboundMasterPK) {
		return fetchByPrimaryKey((Serializable)stCffOutboundMasterPK);
	}

	@Override
	public Map<Serializable, StCffOutboundMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StCffOutboundMaster> map = new HashMap<Serializable, StCffOutboundMaster>();

		for (Serializable primaryKey : primaryKeys) {
			StCffOutboundMaster stCffOutboundMaster = fetchByPrimaryKey(primaryKey);

			if (stCffOutboundMaster != null) {
				map.put(primaryKey, stCffOutboundMaster);
			}
		}

		return map;
	}

	/**
	 * Returns all the st cff outbound masters.
	 *
	 * @return the st cff outbound masters
	 */
	@Override
	public List<StCffOutboundMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st cff outbound masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st cff outbound masters
	 * @param end the upper bound of the range of st cff outbound masters (not inclusive)
	 * @return the range of st cff outbound masters
	 */
	@Override
	public List<StCffOutboundMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st cff outbound masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st cff outbound masters
	 * @param end the upper bound of the range of st cff outbound masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st cff outbound masters
	 */
	@Override
	public List<StCffOutboundMaster> findAll(int start, int end,
		OrderByComparator<StCffOutboundMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st cff outbound masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StCffOutboundMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st cff outbound masters
	 * @param end the upper bound of the range of st cff outbound masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st cff outbound masters
	 */
	@Override
	public List<StCffOutboundMaster> findAll(int start, int end,
		OrderByComparator<StCffOutboundMaster> orderByComparator,
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

		List<StCffOutboundMaster> list = null;

		if (retrieveFromCache) {
			list = (List<StCffOutboundMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCFFOUTBOUNDMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCFFOUTBOUNDMASTER;

				if (pagination) {
					sql = sql.concat(StCffOutboundMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StCffOutboundMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StCffOutboundMaster>)QueryUtil.list(q,
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
	 * Removes all the st cff outbound masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StCffOutboundMaster stCffOutboundMaster : findAll()) {
			remove(stCffOutboundMaster);
		}
	}

	/**
	 * Returns the number of st cff outbound masters.
	 *
	 * @return the number of st cff outbound masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCFFOUTBOUNDMASTER);

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
		return StCffOutboundMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st cff outbound master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StCffOutboundMasterImpl.class.getName());
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
	private static final String _SQL_SELECT_STCFFOUTBOUNDMASTER = "SELECT stCffOutboundMaster FROM StCffOutboundMaster stCffOutboundMaster";
	private static final String _SQL_COUNT_STCFFOUTBOUNDMASTER = "SELECT COUNT(stCffOutboundMaster) FROM StCffOutboundMaster stCffOutboundMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stCffOutboundMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StCffOutboundMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StCffOutboundMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"etlCheckRecord", "customerName", "contractHolderId",
				"businessUnitNo", "year", "financialForecastApprovalDate",
				"deductionId", "modifiedDate", "deductionPerUnit", "cogsPerUnit",
				"contractType", "source", "businessUnitName",
				"contractMasterSid", "financialForecastId", "projectId",
				"customerNo", "modifiedBy", "salesDollars", "month",
				"cffDetailsSid", "type", "deductionType", "companyMasterSid",
				"checkRecord", "contractName", "deductionRate",
				"deductionCategory", "cogsAmount", "deductionNo",
				"financialForecastCreationDate", "companyNo", "salesUnits",
				"sessionId", "itemName", "deductionInclusion", "rsModelSid",
				"contractHolderName", "itemMasterSid", "companyName",
				"customerId", "itemId", "netProfitDollars", "glCompanyMasterSid",
				"createdDate", "createdBy", "deductionCategory1",
				"deductionCategory2", "contractHolderNo", "deductionCategory3",
				"itemNo", "deductionCategory4", "deductionCategory5",
				"deductionCategory6", "contractId", "deductionProgram",
				"businessUnitId", "projectionName", "userId", "companyId",
				"outboundStatus", "originalBatchId", "deductionName",
				"netProfitPerUnit", "periodSid", "salesInclusion", "batchId",
				"financialForecastName", "netSalesDollar", "deductionDollars",
				"contractNo"
			});
}