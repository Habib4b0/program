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

import com.stpl.app.exception.NoSuchImtdItemPriceRebateDetailsException;
import com.stpl.app.model.ImtdItemPriceRebateDetails;
import com.stpl.app.model.impl.ImtdItemPriceRebateDetailsImpl;
import com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsPersistence;

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
 * The persistence implementation for the imtd item price rebate details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdItemPriceRebateDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdItemPriceRebateDetailsPersistenceImpl
	extends BasePersistenceImpl<ImtdItemPriceRebateDetails>
	implements ImtdItemPriceRebateDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdItemPriceRebateDetailsUtil} to access the imtd item price rebate details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdItemPriceRebateDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public ImtdItemPriceRebateDetailsPersistenceImpl() {
		setModelClass(ImtdItemPriceRebateDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("itemPriceRevisionDate",
				"ITEM_PRICE_REVISION_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsCheckRecord", "RS_CHECK_RECORD");
			dbColumnNames.put("rebateRevisionDate", "REBATE_REVISION_DATE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("contractPriceEndDate", "CONTRACT_PRICE_END_DATE");
			dbColumnNames.put("totalVolumeCommitment", "TOTAL_VOLUME_COMMITMENT");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("rebateProgramType", "REBATE_PROGRAM_TYPE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("priceRevision", "PRICE_REVISION");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("rsAttachedStatus", "RS_ATTACHED_STATUS");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("totalDollarCommitment", "TOTAL_DOLLAR_COMMITMENT");
			dbColumnNames.put("itemType", "ITEM_TYPE");
			dbColumnNames.put("totalMarketShareCommitmnet",
				"TOTAL_MARKET_SHARE_COMMITMNET");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("basePrice", "BASE_PRICE");
			dbColumnNames.put("bundleNo", "BUNDLE_NO");
			dbColumnNames.put("formulaName", "FORMULA_NAME");
			dbColumnNames.put("psStatus", "PS_STATUS");
			dbColumnNames.put("priceTolerance", "PRICE_TOLERANCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("psDetailsSid", "PS_DETAILS_SID");
			dbColumnNames.put("suggestedPrice", "SUGGESTED_PRICE");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("commitmentPeriod", "COMMITMENT_PERIOD");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("ifpDetailsSid", "IFP_DETAILS_SID");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("priceToleranceType", "PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("rebateAmount", "REBATE_AMOUNT");
			dbColumnNames.put("contractPriceStartDate",
				"CONTRACT_PRICE_START_DATE");
			dbColumnNames.put("rebateScheduleType", "REBATE_SCHEDULE_TYPE");
			dbColumnNames.put("priceToleranceFrequency",
				"PRICE_TOLERANCE_FREQUENCY");
			dbColumnNames.put("imtdItemPriceRebateSid",
				"IMTD_ITEM_PRICE_REBATE_SID");
			dbColumnNames.put("rebatePlanSystemId", "REBATE_PLAN_SYSTEM_ID");
			dbColumnNames.put("attachedDate", "ATTACHED_DATE");
			dbColumnNames.put("pricePlanId", "PRICE_PLAN_ID");
			dbColumnNames.put("itemRebateEndDate", "ITEM_REBATE_END_DATE");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("priceToleranceInterval",
				"PRICE_TOLERANCE_INTERVAL");
			dbColumnNames.put("rsAttachedDate", "RS_ATTACHED_DATE");
			dbColumnNames.put("itemRebateStartDate", "ITEM_REBATE_START_DATE");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("rsDetailsSid", "RS_DETAILS_SID");
			dbColumnNames.put("attachedStatus", "ATTACHED_STATUS");
			dbColumnNames.put("primaryUom", "PRIMARY_UOM");
			dbColumnNames.put("packageSize", "PACKAGE_SIZE");
			dbColumnNames.put("deductionCalendarMasterSid",
				"DEDUCTION_CALENDAR_MASTER_SID");
			dbColumnNames.put("rsContractDetailsDeductionCalendarNo",
				"RS_CONTRACT_DETAILS_DEDUCTION_CALENDAR_NO");
			dbColumnNames.put("rsContractDetailsDeductionCalendarName",
				"RS_CONTRACT_DETAILS_DEDUCTION_CALENDAR_NAME");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("rsContractDetailsNetSalesFormulaNo",
				"RS_CONTRACT_DETAILS_NET_SALES_FORMULA_NO");
			dbColumnNames.put("rsContractDetailsNetSalesFormulaName",
				"RS_CONTRACT_DETAILS_NET_SALES_FORMULA_NAME");
			dbColumnNames.put("formulaType", "FORMULA_TYPE");
			dbColumnNames.put("netSalesRule", "NET_SALES_RULE");
			dbColumnNames.put("evaluationRule", "EVALUATION_RULE");
			dbColumnNames.put("evaluationRuleBundle", "EVALUATION_RULE_BUNDLE");
			dbColumnNames.put("calculationRule", "CALCULATION_RULE");
			dbColumnNames.put("calculationRuleBundle", "CALCULATION_RULE_BUNDLE");
			dbColumnNames.put("maxIncrementalChange", "MAX_INCREMENTAL_CHANGE");
			dbColumnNames.put("resetEligible", "RESET_ELIGIBLE");
			dbColumnNames.put("resetType", "RESET_TYPE");
			dbColumnNames.put("resetDate", "RESET_DATE");
			dbColumnNames.put("resetInterval", "RESET_INTERVAL");
			dbColumnNames.put("resetFrequency", "RESET_FREQUENCY");
			dbColumnNames.put("netPriceType", "NET_PRICE_TYPE");
			dbColumnNames.put("netPriceTypeFormula", "NET_PRICE_TYPE_FORMULA");
			dbColumnNames.put("priceProtectionPriceType",
				"PRICE_PROTECTION_PRICE_TYPE");
			dbColumnNames.put("nep", "NEP");
			dbColumnNames.put("nepFormula", "NEP_FORMULA");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("priceProtectionStatus", "PRICE_PROTECTION_STATUS");
			dbColumnNames.put("basePriceType", "BASE_PRICE_TYPE");
			dbColumnNames.put("basePriceEntry", "BASE_PRICE_ENTRY");
			dbColumnNames.put("basePriceDate", "BASE_PRICE_DATE");
			dbColumnNames.put("basePriceDdlb", "BASE_PRICE_DDLB");
			dbColumnNames.put("netBasePrice", "NET_BASE_PRICE");
			dbColumnNames.put("subsequentPeriodPriceType",
				"SUBSEQUENT_PERIOD_PRICE_TYPE");
			dbColumnNames.put("resetPriceType", "RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceType", "NET_RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceFormulaId",
				"NET_RESET_PRICE_FORMULA_ID");
			dbColumnNames.put("netBasePriceFormulaId",
				"NET_BASE_PRICE_FORMULA_ID");
			dbColumnNames.put("netSubsequentPriceFormulaId",
				"NET_SUBSEQUENT_PRICE_FORMULA_ID");
			dbColumnNames.put("netSubsequentPeriodPrice",
				"NET_SUBSEQUENT_PERIOD_PRICE");
			dbColumnNames.put("rsContractDetailsRebatePlanName",
				"RS_CONTRACT_DETAILS_REBATE_PLAN_NAME");
			dbColumnNames.put("rsContractDetailsFormulaNo",
				"RS_CONTRACT_DETAILS_FORMULA_NO");
			dbColumnNames.put("source", "SOURCE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd item price rebate details in the entity cache if it is enabled.
	 *
	 * @param imtdItemPriceRebateDetails the imtd item price rebate details
	 */
	@Override
	public void cacheResult(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		entityCache.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsImpl.class,
			imtdItemPriceRebateDetails.getPrimaryKey(),
			imtdItemPriceRebateDetails);

		imtdItemPriceRebateDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd item price rebate detailses in the entity cache if it is enabled.
	 *
	 * @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
	 */
	@Override
	public void cacheResult(
		List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
		for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : imtdItemPriceRebateDetailses) {
			if (entityCache.getResult(
						ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdItemPriceRebateDetailsImpl.class,
						imtdItemPriceRebateDetails.getPrimaryKey()) == null) {
				cacheResult(imtdItemPriceRebateDetails);
			}
			else {
				imtdItemPriceRebateDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd item price rebate detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdItemPriceRebateDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd item price rebate details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		entityCache.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsImpl.class,
			imtdItemPriceRebateDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : imtdItemPriceRebateDetailses) {
			entityCache.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdItemPriceRebateDetailsImpl.class,
				imtdItemPriceRebateDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
	 *
	 * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
	 * @return the new imtd item price rebate details
	 */
	@Override
	public ImtdItemPriceRebateDetails create(int imtdItemPriceRebateSid) {
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = new ImtdItemPriceRebateDetailsImpl();

		imtdItemPriceRebateDetails.setNew(true);
		imtdItemPriceRebateDetails.setPrimaryKey(imtdItemPriceRebateSid);

		return imtdItemPriceRebateDetails;
	}

	/**
	 * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details that was removed
	 * @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails remove(int imtdItemPriceRebateSid)
		throws NoSuchImtdItemPriceRebateDetailsException {
		return remove((Serializable)imtdItemPriceRebateSid);
	}

	/**
	 * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details that was removed
	 * @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails remove(Serializable primaryKey)
		throws NoSuchImtdItemPriceRebateDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails)session.get(ImtdItemPriceRebateDetailsImpl.class,
					primaryKey);

			if (imtdItemPriceRebateDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdItemPriceRebateDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdItemPriceRebateDetails);
		}
		catch (NoSuchImtdItemPriceRebateDetailsException nsee) {
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
	protected ImtdItemPriceRebateDetails removeImpl(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		imtdItemPriceRebateDetails = toUnwrappedModel(imtdItemPriceRebateDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdItemPriceRebateDetails)) {
				imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails)session.get(ImtdItemPriceRebateDetailsImpl.class,
						imtdItemPriceRebateDetails.getPrimaryKeyObj());
			}

			if (imtdItemPriceRebateDetails != null) {
				session.delete(imtdItemPriceRebateDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdItemPriceRebateDetails != null) {
			clearCache(imtdItemPriceRebateDetails);
		}

		return imtdItemPriceRebateDetails;
	}

	@Override
	public ImtdItemPriceRebateDetails updateImpl(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		imtdItemPriceRebateDetails = toUnwrappedModel(imtdItemPriceRebateDetails);

		boolean isNew = imtdItemPriceRebateDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdItemPriceRebateDetails.isNew()) {
				session.save(imtdItemPriceRebateDetails);

				imtdItemPriceRebateDetails.setNew(false);
			}
			else {
				imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails)session.merge(imtdItemPriceRebateDetails);
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

		entityCache.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdItemPriceRebateDetailsImpl.class,
			imtdItemPriceRebateDetails.getPrimaryKey(),
			imtdItemPriceRebateDetails, false);

		imtdItemPriceRebateDetails.resetOriginalValues();

		return imtdItemPriceRebateDetails;
	}

	protected ImtdItemPriceRebateDetails toUnwrappedModel(
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
		if (imtdItemPriceRebateDetails instanceof ImtdItemPriceRebateDetailsImpl) {
			return imtdItemPriceRebateDetails;
		}

		ImtdItemPriceRebateDetailsImpl imtdItemPriceRebateDetailsImpl = new ImtdItemPriceRebateDetailsImpl();

		imtdItemPriceRebateDetailsImpl.setNew(imtdItemPriceRebateDetails.isNew());
		imtdItemPriceRebateDetailsImpl.setPrimaryKey(imtdItemPriceRebateDetails.getPrimaryKey());

		imtdItemPriceRebateDetailsImpl.setFormulaMethodId(imtdItemPriceRebateDetails.getFormulaMethodId());
		imtdItemPriceRebateDetailsImpl.setEndDate(imtdItemPriceRebateDetails.getEndDate());
		imtdItemPriceRebateDetailsImpl.setPriceProtectionStartDate(imtdItemPriceRebateDetails.getPriceProtectionStartDate());
		imtdItemPriceRebateDetailsImpl.setItemPriceRevisionDate(imtdItemPriceRebateDetails.getItemPriceRevisionDate());
		imtdItemPriceRebateDetailsImpl.setModifiedDate(imtdItemPriceRebateDetails.getModifiedDate());
		imtdItemPriceRebateDetailsImpl.setRsCheckRecord(imtdItemPriceRebateDetails.isRsCheckRecord());
		imtdItemPriceRebateDetailsImpl.setRebateRevisionDate(imtdItemPriceRebateDetails.getRebateRevisionDate());
		imtdItemPriceRebateDetailsImpl.setContractMasterSid(imtdItemPriceRebateDetails.getContractMasterSid());
		imtdItemPriceRebateDetailsImpl.setImtdCreatedDate(imtdItemPriceRebateDetails.getImtdCreatedDate());
		imtdItemPriceRebateDetailsImpl.setModifiedBy(imtdItemPriceRebateDetails.getModifiedBy());
		imtdItemPriceRebateDetailsImpl.setUdc6(imtdItemPriceRebateDetails.getUdc6());
		imtdItemPriceRebateDetailsImpl.setUdc5(imtdItemPriceRebateDetails.getUdc5());
		imtdItemPriceRebateDetailsImpl.setUdc4(imtdItemPriceRebateDetails.getUdc4());
		imtdItemPriceRebateDetailsImpl.setCheckRecord(imtdItemPriceRebateDetails.isCheckRecord());
		imtdItemPriceRebateDetailsImpl.setUdc1(imtdItemPriceRebateDetails.getUdc1());
		imtdItemPriceRebateDetailsImpl.setUdc2(imtdItemPriceRebateDetails.getUdc2());
		imtdItemPriceRebateDetailsImpl.setUdc3(imtdItemPriceRebateDetails.getUdc3());
		imtdItemPriceRebateDetailsImpl.setContractPriceEndDate(imtdItemPriceRebateDetails.getContractPriceEndDate());
		imtdItemPriceRebateDetailsImpl.setTotalVolumeCommitment(imtdItemPriceRebateDetails.getTotalVolumeCommitment());
		imtdItemPriceRebateDetailsImpl.setPriceProtectionEndDate(imtdItemPriceRebateDetails.getPriceProtectionEndDate());
		imtdItemPriceRebateDetailsImpl.setRecordLockStatus(imtdItemPriceRebateDetails.getRecordLockStatus());
		imtdItemPriceRebateDetailsImpl.setStartDate(imtdItemPriceRebateDetails.getStartDate());
		imtdItemPriceRebateDetailsImpl.setRebateProgramType(imtdItemPriceRebateDetails.getRebateProgramType());
		imtdItemPriceRebateDetailsImpl.setSessionId(imtdItemPriceRebateDetails.getSessionId());
		imtdItemPriceRebateDetailsImpl.setItemName(imtdItemPriceRebateDetails.getItemName());
		imtdItemPriceRebateDetailsImpl.setPriceRevision(imtdItemPriceRebateDetails.getPriceRevision());
		imtdItemPriceRebateDetailsImpl.setRsModelSid(imtdItemPriceRebateDetails.getRsModelSid());
		imtdItemPriceRebateDetailsImpl.setPrice(imtdItemPriceRebateDetails.getPrice());
		imtdItemPriceRebateDetailsImpl.setRsAttachedStatus(imtdItemPriceRebateDetails.getRsAttachedStatus());
		imtdItemPriceRebateDetailsImpl.setItemMasterSid(imtdItemPriceRebateDetails.getItemMasterSid());
		imtdItemPriceRebateDetailsImpl.setTotalDollarCommitment(imtdItemPriceRebateDetails.getTotalDollarCommitment());
		imtdItemPriceRebateDetailsImpl.setItemType(imtdItemPriceRebateDetails.getItemType());
		imtdItemPriceRebateDetailsImpl.setTotalMarketShareCommitmnet(imtdItemPriceRebateDetails.getTotalMarketShareCommitmnet());
		imtdItemPriceRebateDetailsImpl.setItemId(imtdItemPriceRebateDetails.getItemId());
		imtdItemPriceRebateDetailsImpl.setBasePrice(imtdItemPriceRebateDetails.getBasePrice());
		imtdItemPriceRebateDetailsImpl.setBundleNo(imtdItemPriceRebateDetails.getBundleNo());
		imtdItemPriceRebateDetailsImpl.setFormulaName(imtdItemPriceRebateDetails.getFormulaName());
		imtdItemPriceRebateDetailsImpl.setPsStatus(imtdItemPriceRebateDetails.getPsStatus());
		imtdItemPriceRebateDetailsImpl.setPriceTolerance(imtdItemPriceRebateDetails.getPriceTolerance());
		imtdItemPriceRebateDetailsImpl.setCreatedDate(imtdItemPriceRebateDetails.getCreatedDate());
		imtdItemPriceRebateDetailsImpl.setCreatedBy(imtdItemPriceRebateDetails.getCreatedBy());
		imtdItemPriceRebateDetailsImpl.setUsersSid(imtdItemPriceRebateDetails.getUsersSid());
		imtdItemPriceRebateDetailsImpl.setPsDetailsSid(imtdItemPriceRebateDetails.getPsDetailsSid());
		imtdItemPriceRebateDetailsImpl.setSuggestedPrice(imtdItemPriceRebateDetails.getSuggestedPrice());
		imtdItemPriceRebateDetailsImpl.setPsModelSid(imtdItemPriceRebateDetails.getPsModelSid());
		imtdItemPriceRebateDetailsImpl.setFormulaId(imtdItemPriceRebateDetails.getFormulaId());
		imtdItemPriceRebateDetailsImpl.setCommitmentPeriod(imtdItemPriceRebateDetails.getCommitmentPeriod());
		imtdItemPriceRebateDetailsImpl.setItemNo(imtdItemPriceRebateDetails.getItemNo());
		imtdItemPriceRebateDetailsImpl.setContractPrice(imtdItemPriceRebateDetails.getContractPrice());
		imtdItemPriceRebateDetailsImpl.setIfpDetailsSid(imtdItemPriceRebateDetails.getIfpDetailsSid());
		imtdItemPriceRebateDetailsImpl.setIfpModelSid(imtdItemPriceRebateDetails.getIfpModelSid());
		imtdItemPriceRebateDetailsImpl.setPriceToleranceType(imtdItemPriceRebateDetails.getPriceToleranceType());
		imtdItemPriceRebateDetailsImpl.setRebateAmount(imtdItemPriceRebateDetails.getRebateAmount());
		imtdItemPriceRebateDetailsImpl.setContractPriceStartDate(imtdItemPriceRebateDetails.getContractPriceStartDate());
		imtdItemPriceRebateDetailsImpl.setRebateScheduleType(imtdItemPriceRebateDetails.getRebateScheduleType());
		imtdItemPriceRebateDetailsImpl.setPriceToleranceFrequency(imtdItemPriceRebateDetails.getPriceToleranceFrequency());
		imtdItemPriceRebateDetailsImpl.setImtdItemPriceRebateSid(imtdItemPriceRebateDetails.getImtdItemPriceRebateSid());
		imtdItemPriceRebateDetailsImpl.setRebatePlanSystemId(imtdItemPriceRebateDetails.getRebatePlanSystemId());
		imtdItemPriceRebateDetailsImpl.setAttachedDate(imtdItemPriceRebateDetails.getAttachedDate());
		imtdItemPriceRebateDetailsImpl.setPricePlanId(imtdItemPriceRebateDetails.getPricePlanId());
		imtdItemPriceRebateDetailsImpl.setItemRebateEndDate(imtdItemPriceRebateDetails.getItemRebateEndDate());
		imtdItemPriceRebateDetailsImpl.setPriceType(imtdItemPriceRebateDetails.getPriceType());
		imtdItemPriceRebateDetailsImpl.setPriceToleranceInterval(imtdItemPriceRebateDetails.getPriceToleranceInterval());
		imtdItemPriceRebateDetailsImpl.setRsAttachedDate(imtdItemPriceRebateDetails.getRsAttachedDate());
		imtdItemPriceRebateDetailsImpl.setItemRebateStartDate(imtdItemPriceRebateDetails.getItemRebateStartDate());
		imtdItemPriceRebateDetailsImpl.setOperation(imtdItemPriceRebateDetails.getOperation());
		imtdItemPriceRebateDetailsImpl.setCfpModelSid(imtdItemPriceRebateDetails.getCfpModelSid());
		imtdItemPriceRebateDetailsImpl.setRsDetailsSid(imtdItemPriceRebateDetails.getRsDetailsSid());
		imtdItemPriceRebateDetailsImpl.setAttachedStatus(imtdItemPriceRebateDetails.getAttachedStatus());
		imtdItemPriceRebateDetailsImpl.setPrimaryUom(imtdItemPriceRebateDetails.getPrimaryUom());
		imtdItemPriceRebateDetailsImpl.setPackageSize(imtdItemPriceRebateDetails.getPackageSize());
		imtdItemPriceRebateDetailsImpl.setDeductionCalendarMasterSid(imtdItemPriceRebateDetails.getDeductionCalendarMasterSid());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarNo(imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarNo());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsDeductionCalendarName(imtdItemPriceRebateDetails.getRsContractDetailsDeductionCalendarName());
		imtdItemPriceRebateDetailsImpl.setNetSalesFormulaMasterSid(imtdItemPriceRebateDetails.getNetSalesFormulaMasterSid());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaNo(imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaNo());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsNetSalesFormulaName(imtdItemPriceRebateDetails.getRsContractDetailsNetSalesFormulaName());
		imtdItemPriceRebateDetailsImpl.setFormulaType(imtdItemPriceRebateDetails.getFormulaType());
		imtdItemPriceRebateDetailsImpl.setNetSalesRule(imtdItemPriceRebateDetails.getNetSalesRule());
		imtdItemPriceRebateDetailsImpl.setEvaluationRule(imtdItemPriceRebateDetails.getEvaluationRule());
		imtdItemPriceRebateDetailsImpl.setEvaluationRuleBundle(imtdItemPriceRebateDetails.getEvaluationRuleBundle());
		imtdItemPriceRebateDetailsImpl.setCalculationRule(imtdItemPriceRebateDetails.getCalculationRule());
		imtdItemPriceRebateDetailsImpl.setCalculationRuleBundle(imtdItemPriceRebateDetails.getCalculationRuleBundle());
		imtdItemPriceRebateDetailsImpl.setMaxIncrementalChange(imtdItemPriceRebateDetails.getMaxIncrementalChange());
		imtdItemPriceRebateDetailsImpl.setResetEligible(imtdItemPriceRebateDetails.getResetEligible());
		imtdItemPriceRebateDetailsImpl.setResetType(imtdItemPriceRebateDetails.getResetType());
		imtdItemPriceRebateDetailsImpl.setResetDate(imtdItemPriceRebateDetails.getResetDate());
		imtdItemPriceRebateDetailsImpl.setResetInterval(imtdItemPriceRebateDetails.getResetInterval());
		imtdItemPriceRebateDetailsImpl.setResetFrequency(imtdItemPriceRebateDetails.getResetFrequency());
		imtdItemPriceRebateDetailsImpl.setNetPriceType(imtdItemPriceRebateDetails.getNetPriceType());
		imtdItemPriceRebateDetailsImpl.setNetPriceTypeFormula(imtdItemPriceRebateDetails.getNetPriceTypeFormula());
		imtdItemPriceRebateDetailsImpl.setPriceProtectionPriceType(imtdItemPriceRebateDetails.getPriceProtectionPriceType());
		imtdItemPriceRebateDetailsImpl.setNep(imtdItemPriceRebateDetails.getNep());
		imtdItemPriceRebateDetailsImpl.setNepFormula(imtdItemPriceRebateDetails.getNepFormula());
		imtdItemPriceRebateDetailsImpl.setBrandMasterSid(imtdItemPriceRebateDetails.getBrandMasterSid());
		imtdItemPriceRebateDetailsImpl.setPriceProtectionStatus(imtdItemPriceRebateDetails.getPriceProtectionStatus());
		imtdItemPriceRebateDetailsImpl.setBasePriceType(imtdItemPriceRebateDetails.getBasePriceType());
		imtdItemPriceRebateDetailsImpl.setBasePriceEntry(imtdItemPriceRebateDetails.getBasePriceEntry());
		imtdItemPriceRebateDetailsImpl.setBasePriceDate(imtdItemPriceRebateDetails.getBasePriceDate());
		imtdItemPriceRebateDetailsImpl.setBasePriceDdlb(imtdItemPriceRebateDetails.getBasePriceDdlb());
		imtdItemPriceRebateDetailsImpl.setNetBasePrice(imtdItemPriceRebateDetails.getNetBasePrice());
		imtdItemPriceRebateDetailsImpl.setSubsequentPeriodPriceType(imtdItemPriceRebateDetails.getSubsequentPeriodPriceType());
		imtdItemPriceRebateDetailsImpl.setResetPriceType(imtdItemPriceRebateDetails.getResetPriceType());
		imtdItemPriceRebateDetailsImpl.setNetResetPriceType(imtdItemPriceRebateDetails.getNetResetPriceType());
		imtdItemPriceRebateDetailsImpl.setNetResetPriceFormulaId(imtdItemPriceRebateDetails.getNetResetPriceFormulaId());
		imtdItemPriceRebateDetailsImpl.setNetBasePriceFormulaId(imtdItemPriceRebateDetails.getNetBasePriceFormulaId());
		imtdItemPriceRebateDetailsImpl.setNetSubsequentPriceFormulaId(imtdItemPriceRebateDetails.getNetSubsequentPriceFormulaId());
		imtdItemPriceRebateDetailsImpl.setNetSubsequentPeriodPrice(imtdItemPriceRebateDetails.getNetSubsequentPeriodPrice());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsRebatePlanName(imtdItemPriceRebateDetails.getRsContractDetailsRebatePlanName());
		imtdItemPriceRebateDetailsImpl.setRsContractDetailsFormulaNo(imtdItemPriceRebateDetails.getRsContractDetailsFormulaNo());
		imtdItemPriceRebateDetailsImpl.setSource(imtdItemPriceRebateDetails.getSource());

		return imtdItemPriceRebateDetailsImpl;
	}

	/**
	 * Returns the imtd item price rebate details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details
	 * @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdItemPriceRebateDetailsException {
		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = fetchByPrimaryKey(primaryKey);

		if (imtdItemPriceRebateDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdItemPriceRebateDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdItemPriceRebateDetails;
	}

	/**
	 * Returns the imtd item price rebate details with the primary key or throws a {@link NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
	 *
	 * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details
	 * @throws NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails findByPrimaryKey(
		int imtdItemPriceRebateSid)
		throws NoSuchImtdItemPriceRebateDetailsException {
		return findByPrimaryKey((Serializable)imtdItemPriceRebateSid);
	}

	/**
	 * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdItemPriceRebateDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails)serializable;

		if (imtdItemPriceRebateDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdItemPriceRebateDetails = (ImtdItemPriceRebateDetails)session.get(ImtdItemPriceRebateDetailsImpl.class,
						primaryKey);

				if (imtdItemPriceRebateDetails != null) {
					cacheResult(imtdItemPriceRebateDetails);
				}
				else {
					entityCache.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdItemPriceRebateDetailsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdItemPriceRebateDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdItemPriceRebateDetails;
	}

	/**
	 * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
	 * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
	 */
	@Override
	public ImtdItemPriceRebateDetails fetchByPrimaryKey(
		int imtdItemPriceRebateSid) {
		return fetchByPrimaryKey((Serializable)imtdItemPriceRebateSid);
	}

	@Override
	public Map<Serializable, ImtdItemPriceRebateDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdItemPriceRebateDetails> map = new HashMap<Serializable, ImtdItemPriceRebateDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdItemPriceRebateDetails imtdItemPriceRebateDetails = fetchByPrimaryKey(primaryKey);

			if (imtdItemPriceRebateDetails != null) {
				map.put(primaryKey, imtdItemPriceRebateDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdItemPriceRebateDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdItemPriceRebateDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDITEMPRICEREBATEDETAILS_WHERE_PKS_IN);

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

			for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : (List<ImtdItemPriceRebateDetails>)q.list()) {
				map.put(imtdItemPriceRebateDetails.getPrimaryKeyObj(),
					imtdItemPriceRebateDetails);

				cacheResult(imtdItemPriceRebateDetails);

				uncachedPrimaryKeys.remove(imtdItemPriceRebateDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdItemPriceRebateDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdItemPriceRebateDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd item price rebate detailses.
	 *
	 * @return the imtd item price rebate detailses
	 */
	@Override
	public List<ImtdItemPriceRebateDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd item price rebate detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd item price rebate detailses
	 * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	 * @return the range of imtd item price rebate detailses
	 */
	@Override
	public List<ImtdItemPriceRebateDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd item price rebate detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd item price rebate detailses
	 * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd item price rebate detailses
	 */
	@Override
	public List<ImtdItemPriceRebateDetails> findAll(int start, int end,
		OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd item price rebate detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd item price rebate detailses
	 * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd item price rebate detailses
	 */
	@Override
	public List<ImtdItemPriceRebateDetails> findAll(int start, int end,
		OrderByComparator<ImtdItemPriceRebateDetails> orderByComparator,
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

		List<ImtdItemPriceRebateDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdItemPriceRebateDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDITEMPRICEREBATEDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDITEMPRICEREBATEDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdItemPriceRebateDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdItemPriceRebateDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdItemPriceRebateDetails>)QueryUtil.list(q,
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
	 * Removes all the imtd item price rebate detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdItemPriceRebateDetails imtdItemPriceRebateDetails : findAll()) {
			remove(imtdItemPriceRebateDetails);
		}
	}

	/**
	 * Returns the number of imtd item price rebate detailses.
	 *
	 * @return the number of imtd item price rebate detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDITEMPRICEREBATEDETAILS);

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
		return ImtdItemPriceRebateDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd item price rebate details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdItemPriceRebateDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDITEMPRICEREBATEDETAILS = "SELECT imtdItemPriceRebateDetails FROM ImtdItemPriceRebateDetails imtdItemPriceRebateDetails";
	private static final String _SQL_SELECT_IMTDITEMPRICEREBATEDETAILS_WHERE_PKS_IN =
		"SELECT imtdItemPriceRebateDetails FROM ImtdItemPriceRebateDetails imtdItemPriceRebateDetails WHERE IMTD_ITEM_PRICE_REBATE_SID IN (";
	private static final String _SQL_COUNT_IMTDITEMPRICEREBATEDETAILS = "SELECT COUNT(imtdItemPriceRebateDetails) FROM ImtdItemPriceRebateDetails imtdItemPriceRebateDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdItemPriceRebateDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdItemPriceRebateDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdItemPriceRebateDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"formulaMethodId", "endDate", "priceProtectionStartDate",
				"itemPriceRevisionDate", "modifiedDate", "rsCheckRecord",
				"rebateRevisionDate", "contractMasterSid", "imtdCreatedDate",
				"modifiedBy", "udc6", "udc5", "udc4", "checkRecord", "udc1",
				"udc2", "udc3", "contractPriceEndDate", "totalVolumeCommitment",
				"priceProtectionEndDate", "recordLockStatus", "startDate",
				"rebateProgramType", "sessionId", "itemName", "priceRevision",
				"rsModelSid", "price", "rsAttachedStatus", "itemMasterSid",
				"totalDollarCommitment", "itemType",
				"totalMarketShareCommitmnet", "itemId", "basePrice", "bundleNo",
				"formulaName", "psStatus", "priceTolerance", "createdDate",
				"createdBy", "usersSid", "psDetailsSid", "suggestedPrice",
				"psModelSid", "formulaId", "commitmentPeriod", "itemNo",
				"contractPrice", "ifpDetailsSid", "ifpModelSid",
				"priceToleranceType", "rebateAmount", "contractPriceStartDate",
				"rebateScheduleType", "priceToleranceFrequency",
				"imtdItemPriceRebateSid", "rebatePlanSystemId", "attachedDate",
				"pricePlanId", "itemRebateEndDate", "priceType",
				"priceToleranceInterval", "rsAttachedDate",
				"itemRebateStartDate", "operation", "cfpModelSid",
				"rsDetailsSid", "attachedStatus", "primaryUom", "packageSize",
				"deductionCalendarMasterSid",
				"rsContractDetailsDeductionCalendarNo",
				"rsContractDetailsDeductionCalendarName",
				"netSalesFormulaMasterSid", "rsContractDetailsNetSalesFormulaNo",
				"rsContractDetailsNetSalesFormulaName", "formulaType",
				"netSalesRule", "evaluationRule", "evaluationRuleBundle",
				"calculationRule", "calculationRuleBundle",
				"maxIncrementalChange", "resetEligible", "resetType",
				"resetDate", "resetInterval", "resetFrequency", "netPriceType",
				"netPriceTypeFormula", "priceProtectionPriceType", "nep",
				"nepFormula", "brandMasterSid", "priceProtectionStatus",
				"basePriceType", "basePriceEntry", "basePriceDate",
				"basePriceDdlb", "netBasePrice", "subsequentPeriodPriceType",
				"resetPriceType", "netResetPriceType", "netResetPriceFormulaId",
				"netBasePriceFormulaId", "netSubsequentPriceFormulaId",
				"netSubsequentPeriodPrice", "rsContractDetailsRebatePlanName",
				"rsContractDetailsFormulaNo", "source"
			});
}