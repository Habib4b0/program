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

import com.stpl.app.exception.NoSuchImtdPsDetailsException;
import com.stpl.app.model.ImtdPsDetails;
import com.stpl.app.model.impl.ImtdPsDetailsImpl;
import com.stpl.app.model.impl.ImtdPsDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdPsDetailsPersistence;

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
 * The persistence implementation for the imtd ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdPsDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdPsDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdPsDetailsPersistenceImpl extends BasePersistenceImpl<ImtdPsDetails>
	implements ImtdPsDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdPsDetailsUtil} to access the imtd ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdPsDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdPsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdPsDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdPsDetailsPersistenceImpl() {
		setModelClass(ImtdPsDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("psDetailsModifiedDate",
				"PS_DETAILS_MODIFIED_DATE");
			dbColumnNames.put("psDetailsSuggestedPrice",
				"PS_DETAILS_SUGGESTED_PRICE");
			dbColumnNames.put("psDetailsContractPrice",
				"PS_DETAILS_CONTRACT_PRICE");
			dbColumnNames.put("resetDate", "RESET_DATE");
			dbColumnNames.put("psDetailsAttachedStatus",
				"PS_DETAILS_ATTACHED_STATUS");
			dbColumnNames.put("imtdPsDetailsSid", "IMTD_PS_DETAILS_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("psDetailsCreatedBy", "PS_DETAILS_CREATED_BY");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("psDtlsContPriceEnddate",
				"PS_DTLS_CONT_PRICE_ENDDATE");
			dbColumnNames.put("psDetailsPricPrtcnStdate",
				"PS_DETAILS_PRIC_PRTCN_STDATE");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("netPriceTypeFormula", "NET_PRICE_TYPE_FORMULA");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("maxIncrementalChange", "MAX_INCREMENTAL_CHANGE");
			dbColumnNames.put("psDetailsPricePlanId", "PS_DETAILS_PRICE_PLAN_ID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("psDtlsPriceToleranceFreq",
				"PS_DTLS_PRICE_TOLERANCE_FREQ");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("resetFrequency", "RESET_FREQUENCY");
			dbColumnNames.put("psDtlsPriceToleranceType",
				"PS_DTLS_PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("psDetailsPricetype", "PS_DETAILS_PRICETYPE");
			dbColumnNames.put("psDetailsPriceRevision",
				"PS_DETAILS_PRICE_REVISION");
			dbColumnNames.put("resetInterval", "RESET_INTERVAL");
			dbColumnNames.put("ifpNo", "IFP_NO");
			dbColumnNames.put("psDetailsAttachedDate",
				"PS_DETAILS_ATTACHED_DATE");
			dbColumnNames.put("nepFormula", "NEP_FORMULA");
			dbColumnNames.put("psDetailsModifiedBy", "PS_DETAILS_MODIFIED_BY");
			dbColumnNames.put("psDtlsPriceToleranceIntrvl",
				"PS_DTLS_PRICE_TOLERANCE_INTRVL");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("resetType", "RESET_TYPE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("psDetailsPrice", "PS_DETAILS_PRICE");
			dbColumnNames.put("psDetailsCreatedDate", "PS_DETAILS_CREATED_DATE");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("psDetailsSid", "PS_DETAILS_SID");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("priceProtectionPriceType",
				"PRICE_PROTECTION_PRICE_TYPE");
			dbColumnNames.put("psDetailsBasePrice", "PS_DETAILS_BASE_PRICE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("psDetailsRevisionDate",
				"PS_DETAILS_REVISION_DATE");
			dbColumnNames.put("nep", "NEP");
			dbColumnNames.put("psDetailsPriceTolerance",
				"PS_DETAILS_PRICE_TOLERANCE");
			dbColumnNames.put("priceProtectionStatus", "PRICE_PROTECTION_STATUS");
			dbColumnNames.put("psDtlsContPriceStartdate",
				"PS_DTLS_CONT_PRICE_STARTDATE");
			dbColumnNames.put("resetEligible", "RESET_ELIGIBLE");
			dbColumnNames.put("netPriceType", "NET_PRICE_TYPE");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("psDetailsPricPrtcnEddate",
				"PS_DETAILS_PRIC_PRTCN_EDDATE");
			dbColumnNames.put("basePriceType", "BASE_PRICE_TYPE");
			dbColumnNames.put("basePriceEntry", "BASE_PRICE_ENTRY");
			dbColumnNames.put("basePriceDate", "BASE_PRICE_DATE");
			dbColumnNames.put("basePriceDdlb", "BASE_PRICE_DDLB");
			dbColumnNames.put("netBasePrice", "NET_BASE_PRICE");
			dbColumnNames.put("netBasePriceFormulaId",
				"NET_BASE_PRICE_FORMULA_ID");
			dbColumnNames.put("netBasePriceFormulaNo",
				"NET_BASE_PRICE_FORMULA_NO");
			dbColumnNames.put("netBasePriceFormulaName",
				"NET_BASE_PRICE_FORMULA_NAME");
			dbColumnNames.put("subsequentPeriodPriceType",
				"SUBSEQUENT_PERIOD_PRICE_TYPE");
			dbColumnNames.put("netSubsequentPeriodPrice",
				"NET_SUBSEQUENT_PERIOD_PRICE");
			dbColumnNames.put("netSubsequentPriceFormulaId",
				"NET_SUBSEQUENT_PRICE_FORMULA_ID");
			dbColumnNames.put("netSubsequentPriceFormulaNo",
				"NET_SUBSEQUENT_PRICE_FORMULA_NO");
			dbColumnNames.put("netSubsequentPriceFormulaName",
				"NET_SUBSEQUENT_PRICE_FORMULA_NAME");
			dbColumnNames.put("resetPriceType", "RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceType", "NET_RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceFormulaId",
				"NET_RESET_PRICE_FORMULA_ID");
			dbColumnNames.put("netResetPriceFormulaNo",
				"NET_RESET_PRICE_FORMULA_NO");
			dbColumnNames.put("netResetPriceFormulaName",
				"NET_RESET_PRICE_FORMULA_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the imtd ps details in the entity cache if it is enabled.
	 *
	 * @param imtdPsDetails the imtd ps details
	 */
	@Override
	public void cacheResult(ImtdPsDetails imtdPsDetails) {
		entityCache.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey(),
			imtdPsDetails);

		imtdPsDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd ps detailses in the entity cache if it is enabled.
	 *
	 * @param imtdPsDetailses the imtd ps detailses
	 */
	@Override
	public void cacheResult(List<ImtdPsDetails> imtdPsDetailses) {
		for (ImtdPsDetails imtdPsDetails : imtdPsDetailses) {
			if (entityCache.getResult(
						ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey()) == null) {
				cacheResult(imtdPsDetails);
			}
			else {
				imtdPsDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd ps detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdPsDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd ps details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdPsDetails imtdPsDetails) {
		entityCache.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdPsDetails> imtdPsDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdPsDetails imtdPsDetails : imtdPsDetailses) {
			entityCache.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
	 *
	 * @param imtdPsDetailsSid the primary key for the new imtd ps details
	 * @return the new imtd ps details
	 */
	@Override
	public ImtdPsDetails create(int imtdPsDetailsSid) {
		ImtdPsDetails imtdPsDetails = new ImtdPsDetailsImpl();

		imtdPsDetails.setNew(true);
		imtdPsDetails.setPrimaryKey(imtdPsDetailsSid);

		return imtdPsDetails;
	}

	/**
	 * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdPsDetailsSid the primary key of the imtd ps details
	 * @return the imtd ps details that was removed
	 * @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails remove(int imtdPsDetailsSid)
		throws NoSuchImtdPsDetailsException {
		return remove((Serializable)imtdPsDetailsSid);
	}

	/**
	 * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd ps details
	 * @return the imtd ps details that was removed
	 * @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails remove(Serializable primaryKey)
		throws NoSuchImtdPsDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdPsDetails imtdPsDetails = (ImtdPsDetails)session.get(ImtdPsDetailsImpl.class,
					primaryKey);

			if (imtdPsDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdPsDetails);
		}
		catch (NoSuchImtdPsDetailsException nsee) {
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
	protected ImtdPsDetails removeImpl(ImtdPsDetails imtdPsDetails) {
		imtdPsDetails = toUnwrappedModel(imtdPsDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdPsDetails)) {
				imtdPsDetails = (ImtdPsDetails)session.get(ImtdPsDetailsImpl.class,
						imtdPsDetails.getPrimaryKeyObj());
			}

			if (imtdPsDetails != null) {
				session.delete(imtdPsDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdPsDetails != null) {
			clearCache(imtdPsDetails);
		}

		return imtdPsDetails;
	}

	@Override
	public ImtdPsDetails updateImpl(ImtdPsDetails imtdPsDetails) {
		imtdPsDetails = toUnwrappedModel(imtdPsDetails);

		boolean isNew = imtdPsDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdPsDetails.isNew()) {
				session.save(imtdPsDetails);

				imtdPsDetails.setNew(false);
			}
			else {
				imtdPsDetails = (ImtdPsDetails)session.merge(imtdPsDetails);
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

		entityCache.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdPsDetailsImpl.class, imtdPsDetails.getPrimaryKey(),
			imtdPsDetails, false);

		imtdPsDetails.resetOriginalValues();

		return imtdPsDetails;
	}

	protected ImtdPsDetails toUnwrappedModel(ImtdPsDetails imtdPsDetails) {
		if (imtdPsDetails instanceof ImtdPsDetailsImpl) {
			return imtdPsDetails;
		}

		ImtdPsDetailsImpl imtdPsDetailsImpl = new ImtdPsDetailsImpl();

		imtdPsDetailsImpl.setNew(imtdPsDetails.isNew());
		imtdPsDetailsImpl.setPrimaryKey(imtdPsDetails.getPrimaryKey());

		imtdPsDetailsImpl.setPsDetailsModifiedDate(imtdPsDetails.getPsDetailsModifiedDate());
		imtdPsDetailsImpl.setPsDetailsSuggestedPrice(imtdPsDetails.getPsDetailsSuggestedPrice());
		imtdPsDetailsImpl.setPsDetailsContractPrice(imtdPsDetails.getPsDetailsContractPrice());
		imtdPsDetailsImpl.setResetDate(imtdPsDetails.getResetDate());
		imtdPsDetailsImpl.setPsDetailsAttachedStatus(imtdPsDetails.getPsDetailsAttachedStatus());
		imtdPsDetailsImpl.setImtdPsDetailsSid(imtdPsDetails.getImtdPsDetailsSid());
		imtdPsDetailsImpl.setModifiedDate(imtdPsDetails.getModifiedDate());
		imtdPsDetailsImpl.setPsDetailsCreatedBy(imtdPsDetails.getPsDetailsCreatedBy());
		imtdPsDetailsImpl.setContractMasterSid(imtdPsDetails.getContractMasterSid());
		imtdPsDetailsImpl.setPsDtlsContPriceEnddate(imtdPsDetails.getPsDtlsContPriceEnddate());
		imtdPsDetailsImpl.setPsDetailsPricPrtcnStdate(imtdPsDetails.getPsDetailsPricPrtcnStdate());
		imtdPsDetailsImpl.setImtdCreatedDate(imtdPsDetails.getImtdCreatedDate());
		imtdPsDetailsImpl.setNetPriceTypeFormula(imtdPsDetails.getNetPriceTypeFormula());
		imtdPsDetailsImpl.setModifiedBy(imtdPsDetails.getModifiedBy());
		imtdPsDetailsImpl.setMaxIncrementalChange(imtdPsDetails.getMaxIncrementalChange());
		imtdPsDetailsImpl.setPsDetailsPricePlanId(imtdPsDetails.getPsDetailsPricePlanId());
		imtdPsDetailsImpl.setCheckRecord(imtdPsDetails.isCheckRecord());
		imtdPsDetailsImpl.setPsDtlsPriceToleranceFreq(imtdPsDetails.getPsDtlsPriceToleranceFreq());
		imtdPsDetailsImpl.setItemName(imtdPsDetails.getItemName());
		imtdPsDetailsImpl.setSessionId(imtdPsDetails.getSessionId());
		imtdPsDetailsImpl.setResetFrequency(imtdPsDetails.getResetFrequency());
		imtdPsDetailsImpl.setPsDtlsPriceToleranceType(imtdPsDetails.getPsDtlsPriceToleranceType());
		imtdPsDetailsImpl.setPsDetailsPricetype(imtdPsDetails.getPsDetailsPricetype());
		imtdPsDetailsImpl.setPsDetailsPriceRevision(imtdPsDetails.getPsDetailsPriceRevision());
		imtdPsDetailsImpl.setResetInterval(imtdPsDetails.getResetInterval());
		imtdPsDetailsImpl.setIfpNo(imtdPsDetails.getIfpNo());
		imtdPsDetailsImpl.setPsDetailsAttachedDate(imtdPsDetails.getPsDetailsAttachedDate());
		imtdPsDetailsImpl.setNepFormula(imtdPsDetails.getNepFormula());
		imtdPsDetailsImpl.setPsDetailsModifiedBy(imtdPsDetails.getPsDetailsModifiedBy());
		imtdPsDetailsImpl.setPsDtlsPriceToleranceIntrvl(imtdPsDetails.getPsDtlsPriceToleranceIntrvl());
		imtdPsDetailsImpl.setItemMasterSid(imtdPsDetails.getItemMasterSid());
		imtdPsDetailsImpl.setResetType(imtdPsDetails.getResetType());
		imtdPsDetailsImpl.setItemId(imtdPsDetails.getItemId());
		imtdPsDetailsImpl.setStatus(imtdPsDetails.getStatus());
		imtdPsDetailsImpl.setBrandMasterSid(imtdPsDetails.getBrandMasterSid());
		imtdPsDetailsImpl.setPsDetailsPrice(imtdPsDetails.getPsDetailsPrice());
		imtdPsDetailsImpl.setPsDetailsCreatedDate(imtdPsDetails.getPsDetailsCreatedDate());
		imtdPsDetailsImpl.setUsersSid(imtdPsDetails.getUsersSid());
		imtdPsDetailsImpl.setCreatedBy(imtdPsDetails.getCreatedBy());
		imtdPsDetailsImpl.setCreatedDate(imtdPsDetails.getCreatedDate());
		imtdPsDetailsImpl.setPsDetailsSid(imtdPsDetails.getPsDetailsSid());
		imtdPsDetailsImpl.setPsModelSid(imtdPsDetails.getPsModelSid());
		imtdPsDetailsImpl.setPriceProtectionPriceType(imtdPsDetails.getPriceProtectionPriceType());
		imtdPsDetailsImpl.setPsDetailsBasePrice(imtdPsDetails.getPsDetailsBasePrice());
		imtdPsDetailsImpl.setItemNo(imtdPsDetails.getItemNo());
		imtdPsDetailsImpl.setIfpModelSid(imtdPsDetails.getIfpModelSid());
		imtdPsDetailsImpl.setPsDetailsRevisionDate(imtdPsDetails.getPsDetailsRevisionDate());
		imtdPsDetailsImpl.setNep(imtdPsDetails.getNep());
		imtdPsDetailsImpl.setPsDetailsPriceTolerance(imtdPsDetails.getPsDetailsPriceTolerance());
		imtdPsDetailsImpl.setPriceProtectionStatus(imtdPsDetails.getPriceProtectionStatus());
		imtdPsDetailsImpl.setPsDtlsContPriceStartdate(imtdPsDetails.getPsDtlsContPriceStartdate());
		imtdPsDetailsImpl.setResetEligible(imtdPsDetails.getResetEligible());
		imtdPsDetailsImpl.setNetPriceType(imtdPsDetails.getNetPriceType());
		imtdPsDetailsImpl.setOperation(imtdPsDetails.getOperation());
		imtdPsDetailsImpl.setCfpModelSid(imtdPsDetails.getCfpModelSid());
		imtdPsDetailsImpl.setPsDetailsPricPrtcnEddate(imtdPsDetails.getPsDetailsPricPrtcnEddate());
		imtdPsDetailsImpl.setBasePriceType(imtdPsDetails.getBasePriceType());
		imtdPsDetailsImpl.setBasePriceEntry(imtdPsDetails.getBasePriceEntry());
		imtdPsDetailsImpl.setBasePriceDate(imtdPsDetails.getBasePriceDate());
		imtdPsDetailsImpl.setBasePriceDdlb(imtdPsDetails.getBasePriceDdlb());
		imtdPsDetailsImpl.setNetBasePrice(imtdPsDetails.getNetBasePrice());
		imtdPsDetailsImpl.setNetBasePriceFormulaId(imtdPsDetails.getNetBasePriceFormulaId());
		imtdPsDetailsImpl.setNetBasePriceFormulaNo(imtdPsDetails.getNetBasePriceFormulaNo());
		imtdPsDetailsImpl.setNetBasePriceFormulaName(imtdPsDetails.getNetBasePriceFormulaName());
		imtdPsDetailsImpl.setSubsequentPeriodPriceType(imtdPsDetails.getSubsequentPeriodPriceType());
		imtdPsDetailsImpl.setNetSubsequentPeriodPrice(imtdPsDetails.getNetSubsequentPeriodPrice());
		imtdPsDetailsImpl.setNetSubsequentPriceFormulaId(imtdPsDetails.getNetSubsequentPriceFormulaId());
		imtdPsDetailsImpl.setNetSubsequentPriceFormulaNo(imtdPsDetails.getNetSubsequentPriceFormulaNo());
		imtdPsDetailsImpl.setNetSubsequentPriceFormulaName(imtdPsDetails.getNetSubsequentPriceFormulaName());
		imtdPsDetailsImpl.setResetPriceType(imtdPsDetails.getResetPriceType());
		imtdPsDetailsImpl.setNetResetPriceType(imtdPsDetails.getNetResetPriceType());
		imtdPsDetailsImpl.setNetResetPriceFormulaId(imtdPsDetails.getNetResetPriceFormulaId());
		imtdPsDetailsImpl.setNetResetPriceFormulaNo(imtdPsDetails.getNetResetPriceFormulaNo());
		imtdPsDetailsImpl.setNetResetPriceFormulaName(imtdPsDetails.getNetResetPriceFormulaName());

		return imtdPsDetailsImpl;
	}

	/**
	 * Returns the imtd ps details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd ps details
	 * @return the imtd ps details
	 * @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdPsDetailsException {
		ImtdPsDetails imtdPsDetails = fetchByPrimaryKey(primaryKey);

		if (imtdPsDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdPsDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdPsDetails;
	}

	/**
	 * Returns the imtd ps details with the primary key or throws a {@link NoSuchImtdPsDetailsException} if it could not be found.
	 *
	 * @param imtdPsDetailsSid the primary key of the imtd ps details
	 * @return the imtd ps details
	 * @throws NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails findByPrimaryKey(int imtdPsDetailsSid)
		throws NoSuchImtdPsDetailsException {
		return findByPrimaryKey((Serializable)imtdPsDetailsSid);
	}

	/**
	 * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd ps details
	 * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdPsDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdPsDetails imtdPsDetails = (ImtdPsDetails)serializable;

		if (imtdPsDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdPsDetails = (ImtdPsDetails)session.get(ImtdPsDetailsImpl.class,
						primaryKey);

				if (imtdPsDetails != null) {
					cacheResult(imtdPsDetails);
				}
				else {
					entityCache.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdPsDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdPsDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdPsDetails;
	}

	/**
	 * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdPsDetailsSid the primary key of the imtd ps details
	 * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
	 */
	@Override
	public ImtdPsDetails fetchByPrimaryKey(int imtdPsDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdPsDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdPsDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdPsDetails> map = new HashMap<Serializable, ImtdPsDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdPsDetails imtdPsDetails = fetchByPrimaryKey(primaryKey);

			if (imtdPsDetails != null) {
				map.put(primaryKey, imtdPsDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdPsDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdPsDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDPSDETAILS_WHERE_PKS_IN);

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

			for (ImtdPsDetails imtdPsDetails : (List<ImtdPsDetails>)q.list()) {
				map.put(imtdPsDetails.getPrimaryKeyObj(), imtdPsDetails);

				cacheResult(imtdPsDetails);

				uncachedPrimaryKeys.remove(imtdPsDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdPsDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdPsDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd ps detailses.
	 *
	 * @return the imtd ps detailses
	 */
	@Override
	public List<ImtdPsDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ps detailses
	 * @param end the upper bound of the range of imtd ps detailses (not inclusive)
	 * @return the range of imtd ps detailses
	 */
	@Override
	public List<ImtdPsDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ps detailses
	 * @param end the upper bound of the range of imtd ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd ps detailses
	 */
	@Override
	public List<ImtdPsDetails> findAll(int start, int end,
		OrderByComparator<ImtdPsDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd ps detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd ps detailses
	 * @param end the upper bound of the range of imtd ps detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd ps detailses
	 */
	@Override
	public List<ImtdPsDetails> findAll(int start, int end,
		OrderByComparator<ImtdPsDetails> orderByComparator,
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

		List<ImtdPsDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdPsDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDPSDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDPSDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdPsDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdPsDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdPsDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the imtd ps detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdPsDetails imtdPsDetails : findAll()) {
			remove(imtdPsDetails);
		}
	}

	/**
	 * Returns the number of imtd ps detailses.
	 *
	 * @return the number of imtd ps detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDPSDETAILS);

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
		return ImtdPsDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd ps details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdPsDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDPSDETAILS = "SELECT imtdPsDetails FROM ImtdPsDetails imtdPsDetails";
	private static final String _SQL_SELECT_IMTDPSDETAILS_WHERE_PKS_IN = "SELECT imtdPsDetails FROM ImtdPsDetails imtdPsDetails WHERE IMTD_PS_DETAILS_SID IN (";
	private static final String _SQL_COUNT_IMTDPSDETAILS = "SELECT COUNT(imtdPsDetails) FROM ImtdPsDetails imtdPsDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdPsDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdPsDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdPsDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"psDetailsModifiedDate", "psDetailsSuggestedPrice",
				"psDetailsContractPrice", "resetDate", "psDetailsAttachedStatus",
				"imtdPsDetailsSid", "modifiedDate", "psDetailsCreatedBy",
				"contractMasterSid", "psDtlsContPriceEnddate",
				"psDetailsPricPrtcnStdate", "imtdCreatedDate",
				"netPriceTypeFormula", "modifiedBy", "maxIncrementalChange",
				"psDetailsPricePlanId", "checkRecord",
				"psDtlsPriceToleranceFreq", "itemName", "sessionId",
				"resetFrequency", "psDtlsPriceToleranceType",
				"psDetailsPricetype", "psDetailsPriceRevision", "resetInterval",
				"ifpNo", "psDetailsAttachedDate", "nepFormula",
				"psDetailsModifiedBy", "psDtlsPriceToleranceIntrvl",
				"itemMasterSid", "resetType", "itemId", "status",
				"brandMasterSid", "psDetailsPrice", "psDetailsCreatedDate",
				"usersSid", "createdBy", "createdDate", "psDetailsSid",
				"psModelSid", "priceProtectionPriceType", "psDetailsBasePrice",
				"itemNo", "ifpModelSid", "psDetailsRevisionDate", "nep",
				"psDetailsPriceTolerance", "priceProtectionStatus",
				"psDtlsContPriceStartdate", "resetEligible", "netPriceType",
				"operation", "cfpModelSid", "psDetailsPricPrtcnEddate",
				"basePriceType", "basePriceEntry", "basePriceDate",
				"basePriceDdlb", "netBasePrice", "netBasePriceFormulaId",
				"netBasePriceFormulaNo", "netBasePriceFormulaName",
				"subsequentPeriodPriceType", "netSubsequentPeriodPrice",
				"netSubsequentPriceFormulaId", "netSubsequentPriceFormulaNo",
				"netSubsequentPriceFormulaName", "resetPriceType",
				"netResetPriceType", "netResetPriceFormulaId",
				"netResetPriceFormulaNo", "netResetPriceFormulaName"
			});
}