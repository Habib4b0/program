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

import com.stpl.app.exception.NoSuchGcmGlobalDetailsException;
import com.stpl.app.model.GcmGlobalDetails;
import com.stpl.app.model.impl.GcmGlobalDetailsImpl;
import com.stpl.app.model.impl.GcmGlobalDetailsModelImpl;
import com.stpl.app.service.persistence.GcmGlobalDetailsPersistence;

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
 * The persistence implementation for the gcm global details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmGlobalDetailsPersistence
 * @see com.stpl.app.service.persistence.GcmGlobalDetailsUtil
 * @generated
 */
@ProviderType
public class GcmGlobalDetailsPersistenceImpl extends BasePersistenceImpl<GcmGlobalDetails>
	implements GcmGlobalDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link GcmGlobalDetailsUtil} to access the gcm global details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = GcmGlobalDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmGlobalDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED,
			GcmGlobalDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public GcmGlobalDetailsPersistenceImpl() {
		setModelClass(GcmGlobalDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("paymentFrequency", "PAYMENT_FREQUENCY");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("cfpStartDate", "CFP_START_DATE");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("tempItemMasterSid", "TEMP_ITEM_MASTER_SID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("subModuleName", "SUB_MODULE_NAME");
			dbColumnNames.put("theraputicClass", "THERAPUTIC_CLASS");
			dbColumnNames.put("gcmGlobalDetailsSid", "GCM_GLOBAL_DETAILS_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("paymentMethod", "PAYMENT_METHOD");
			dbColumnNames.put("contractPriceEndDate", "CONTRACT_PRICE_END_DATE");
			dbColumnNames.put("psContractSid", "PS_CONTRACT_SID");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("screenName", "SCREEN_NAME");
			dbColumnNames.put("rsContractSid", "RS_CONTRACT_SID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("cfpStatus", "CFP_STATUS");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("cfpContractSid", "CFP_CONTRACT_SID");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("tempEndDate", "TEMP_END_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemType", "ITEM_TYPE");
			dbColumnNames.put("forecastingType", "FORECASTING_TYPE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("basePrice", "BASE_PRICE");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("formulaName", "FORMULA_NAME");
			dbColumnNames.put("workflowMasterSid", "WORKFLOW_MASTER_SID");
			dbColumnNames.put("priceTolerance", "PRICE_TOLERANCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("tempStartDate", "TEMP_START_DATE");
			dbColumnNames.put("cfpEndDate", "CFP_END_DATE");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");
			dbColumnNames.put("priceToleranceType", "PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("rebateAmount", "REBATE_AMOUNT");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("contractPriceStartDate",
				"CONTRACT_PRICE_START_DATE");
			dbColumnNames.put("priceToleranceFrequency",
				"PRICE_TOLERANCE_FREQUENCY");
			dbColumnNames.put("ifpContractAttachedStatus",
				"IFP_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("rebatePlanSystemId", "REBATE_PLAN_SYSTEM_ID");
			dbColumnNames.put("rebatePlanName", "REBATE_PLAN_NAME");
			dbColumnNames.put("calendar", "CALENDAR");
			dbColumnNames.put("pricingQualifierSid", "PRICING_QUALIFIER_SID");
			dbColumnNames.put("tempStatus", "TEMP_STATUS");
			dbColumnNames.put("itemRebateEndDate", "ITEM_REBATE_END_DATE");
			dbColumnNames.put("priceToleranceInterval",
				"PRICE_TOLERANCE_INTERVAL");
			dbColumnNames.put("itemRebateStartDate", "ITEM_REBATE_START_DATE");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("itemStatusSid", "ITEM_STATUS_SID");
			dbColumnNames.put("ifpContractSid", "IFP_CONTRACT_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the gcm global details in the entity cache if it is enabled.
	 *
	 * @param gcmGlobalDetails the gcm global details
	 */
	@Override
	public void cacheResult(GcmGlobalDetails gcmGlobalDetails) {
		entityCache.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey(),
			gcmGlobalDetails);

		gcmGlobalDetails.resetOriginalValues();
	}

	/**
	 * Caches the gcm global detailses in the entity cache if it is enabled.
	 *
	 * @param gcmGlobalDetailses the gcm global detailses
	 */
	@Override
	public void cacheResult(List<GcmGlobalDetails> gcmGlobalDetailses) {
		for (GcmGlobalDetails gcmGlobalDetails : gcmGlobalDetailses) {
			if (entityCache.getResult(
						GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmGlobalDetailsImpl.class,
						gcmGlobalDetails.getPrimaryKey()) == null) {
				cacheResult(gcmGlobalDetails);
			}
			else {
				gcmGlobalDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all gcm global detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(GcmGlobalDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the gcm global details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(GcmGlobalDetails gcmGlobalDetails) {
		entityCache.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<GcmGlobalDetails> gcmGlobalDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (GcmGlobalDetails gcmGlobalDetails : gcmGlobalDetailses) {
			entityCache.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new gcm global details with the primary key. Does not add the gcm global details to the database.
	 *
	 * @param gcmGlobalDetailsSid the primary key for the new gcm global details
	 * @return the new gcm global details
	 */
	@Override
	public GcmGlobalDetails create(int gcmGlobalDetailsSid) {
		GcmGlobalDetails gcmGlobalDetails = new GcmGlobalDetailsImpl();

		gcmGlobalDetails.setNew(true);
		gcmGlobalDetails.setPrimaryKey(gcmGlobalDetailsSid);

		return gcmGlobalDetails;
	}

	/**
	 * Removes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param gcmGlobalDetailsSid the primary key of the gcm global details
	 * @return the gcm global details that was removed
	 * @throws NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails remove(int gcmGlobalDetailsSid)
		throws NoSuchGcmGlobalDetailsException {
		return remove((Serializable)gcmGlobalDetailsSid);
	}

	/**
	 * Removes the gcm global details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the gcm global details
	 * @return the gcm global details that was removed
	 * @throws NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails remove(Serializable primaryKey)
		throws NoSuchGcmGlobalDetailsException {
		Session session = null;

		try {
			session = openSession();

			GcmGlobalDetails gcmGlobalDetails = (GcmGlobalDetails)session.get(GcmGlobalDetailsImpl.class,
					primaryKey);

			if (gcmGlobalDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchGcmGlobalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(gcmGlobalDetails);
		}
		catch (NoSuchGcmGlobalDetailsException nsee) {
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
	protected GcmGlobalDetails removeImpl(GcmGlobalDetails gcmGlobalDetails) {
		gcmGlobalDetails = toUnwrappedModel(gcmGlobalDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(gcmGlobalDetails)) {
				gcmGlobalDetails = (GcmGlobalDetails)session.get(GcmGlobalDetailsImpl.class,
						gcmGlobalDetails.getPrimaryKeyObj());
			}

			if (gcmGlobalDetails != null) {
				session.delete(gcmGlobalDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (gcmGlobalDetails != null) {
			clearCache(gcmGlobalDetails);
		}

		return gcmGlobalDetails;
	}

	@Override
	public GcmGlobalDetails updateImpl(GcmGlobalDetails gcmGlobalDetails) {
		gcmGlobalDetails = toUnwrappedModel(gcmGlobalDetails);

		boolean isNew = gcmGlobalDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (gcmGlobalDetails.isNew()) {
				session.save(gcmGlobalDetails);

				gcmGlobalDetails.setNew(false);
			}
			else {
				gcmGlobalDetails = (GcmGlobalDetails)session.merge(gcmGlobalDetails);
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

		entityCache.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			GcmGlobalDetailsImpl.class, gcmGlobalDetails.getPrimaryKey(),
			gcmGlobalDetails, false);

		gcmGlobalDetails.resetOriginalValues();

		return gcmGlobalDetails;
	}

	protected GcmGlobalDetails toUnwrappedModel(
		GcmGlobalDetails gcmGlobalDetails) {
		if (gcmGlobalDetails instanceof GcmGlobalDetailsImpl) {
			return gcmGlobalDetails;
		}

		GcmGlobalDetailsImpl gcmGlobalDetailsImpl = new GcmGlobalDetailsImpl();

		gcmGlobalDetailsImpl.setNew(gcmGlobalDetails.isNew());
		gcmGlobalDetailsImpl.setPrimaryKey(gcmGlobalDetails.getPrimaryKey());

		gcmGlobalDetailsImpl.setItemStatus(gcmGlobalDetails.getItemStatus());
		gcmGlobalDetailsImpl.setFormulaMethodId(gcmGlobalDetails.getFormulaMethodId());
		gcmGlobalDetailsImpl.setModuleName(gcmGlobalDetails.getModuleName());
		gcmGlobalDetailsImpl.setPaymentFrequency(gcmGlobalDetails.getPaymentFrequency());
		gcmGlobalDetailsImpl.setEndDate(gcmGlobalDetails.getEndDate());
		gcmGlobalDetailsImpl.setCfpStartDate(gcmGlobalDetails.getCfpStartDate());
		gcmGlobalDetailsImpl.setPriceProtectionStartDate(gcmGlobalDetails.getPriceProtectionStartDate());
		gcmGlobalDetailsImpl.setTempItemMasterSid(gcmGlobalDetails.getTempItemMasterSid());
		gcmGlobalDetailsImpl.setBrandName(gcmGlobalDetails.getBrandName());
		gcmGlobalDetailsImpl.setModifiedDate(gcmGlobalDetails.getModifiedDate());
		gcmGlobalDetailsImpl.setContractMasterSid(gcmGlobalDetails.getContractMasterSid());
		gcmGlobalDetailsImpl.setModifiedBy(gcmGlobalDetails.getModifiedBy());
		gcmGlobalDetailsImpl.setSubModuleName(gcmGlobalDetails.getSubModuleName());
		gcmGlobalDetailsImpl.setTheraputicClass(gcmGlobalDetails.getTheraputicClass());
		gcmGlobalDetailsImpl.setGcmGlobalDetailsSid(gcmGlobalDetails.getGcmGlobalDetailsSid());
		gcmGlobalDetailsImpl.setCheckRecord(gcmGlobalDetails.isCheckRecord());
		gcmGlobalDetailsImpl.setPaymentMethod(gcmGlobalDetails.getPaymentMethod());
		gcmGlobalDetailsImpl.setContractPriceEndDate(gcmGlobalDetails.getContractPriceEndDate());
		gcmGlobalDetailsImpl.setPsContractSid(gcmGlobalDetails.getPsContractSid());
		gcmGlobalDetailsImpl.setPriceProtectionEndDate(gcmGlobalDetails.getPriceProtectionEndDate());
		gcmGlobalDetailsImpl.setStartDate(gcmGlobalDetails.getStartDate());
		gcmGlobalDetailsImpl.setScreenName(gcmGlobalDetails.getScreenName());
		gcmGlobalDetailsImpl.setRsContractSid(gcmGlobalDetails.getRsContractSid());
		gcmGlobalDetailsImpl.setItemName(gcmGlobalDetails.getItemName());
		gcmGlobalDetailsImpl.setSessionId(gcmGlobalDetails.getSessionId());
		gcmGlobalDetailsImpl.setCfpStatus(gcmGlobalDetails.getCfpStatus());
		gcmGlobalDetailsImpl.setRsModelSid(gcmGlobalDetails.getRsModelSid());
		gcmGlobalDetailsImpl.setCfpContractSid(gcmGlobalDetails.getCfpContractSid());
		gcmGlobalDetailsImpl.setPrice(gcmGlobalDetails.getPrice());
		gcmGlobalDetailsImpl.setTempEndDate(gcmGlobalDetails.getTempEndDate());
		gcmGlobalDetailsImpl.setItemMasterSid(gcmGlobalDetails.getItemMasterSid());
		gcmGlobalDetailsImpl.setItemType(gcmGlobalDetails.getItemType());
		gcmGlobalDetailsImpl.setForecastingType(gcmGlobalDetails.getForecastingType());
		gcmGlobalDetailsImpl.setItemId(gcmGlobalDetails.getItemId());
		gcmGlobalDetailsImpl.setBasePrice(gcmGlobalDetails.getBasePrice());
		gcmGlobalDetailsImpl.setStatus(gcmGlobalDetails.getStatus());
		gcmGlobalDetailsImpl.setFormulaName(gcmGlobalDetails.getFormulaName());
		gcmGlobalDetailsImpl.setWorkflowMasterSid(gcmGlobalDetails.getWorkflowMasterSid());
		gcmGlobalDetailsImpl.setPriceTolerance(gcmGlobalDetails.getPriceTolerance());
		gcmGlobalDetailsImpl.setCreatedBy(gcmGlobalDetails.getCreatedBy());
		gcmGlobalDetailsImpl.setCreatedDate(gcmGlobalDetails.getCreatedDate());
		gcmGlobalDetailsImpl.setTempStartDate(gcmGlobalDetails.getTempStartDate());
		gcmGlobalDetailsImpl.setCfpEndDate(gcmGlobalDetails.getCfpEndDate());
		gcmGlobalDetailsImpl.setPsModelSid(gcmGlobalDetails.getPsModelSid());
		gcmGlobalDetailsImpl.setFormulaId(gcmGlobalDetails.getFormulaId());
		gcmGlobalDetailsImpl.setItemNo(gcmGlobalDetails.getItemNo());
		gcmGlobalDetailsImpl.setContractPrice(gcmGlobalDetails.getContractPrice());
		gcmGlobalDetailsImpl.setIfpModelSid(gcmGlobalDetails.getIfpModelSid());
		gcmGlobalDetailsImpl.setPriceToleranceType(gcmGlobalDetails.getPriceToleranceType());
		gcmGlobalDetailsImpl.setRebateAmount(gcmGlobalDetails.getRebateAmount());
		gcmGlobalDetailsImpl.setUserId(gcmGlobalDetails.getUserId());
		gcmGlobalDetailsImpl.setProjectionMasterSid(gcmGlobalDetails.getProjectionMasterSid());
		gcmGlobalDetailsImpl.setContractPriceStartDate(gcmGlobalDetails.getContractPriceStartDate());
		gcmGlobalDetailsImpl.setPriceToleranceFrequency(gcmGlobalDetails.getPriceToleranceFrequency());
		gcmGlobalDetailsImpl.setIfpContractAttachedStatus(gcmGlobalDetails.getIfpContractAttachedStatus());
		gcmGlobalDetailsImpl.setRebatePlanSystemId(gcmGlobalDetails.getRebatePlanSystemId());
		gcmGlobalDetailsImpl.setRebatePlanName(gcmGlobalDetails.getRebatePlanName());
		gcmGlobalDetailsImpl.setCalendar(gcmGlobalDetails.getCalendar());
		gcmGlobalDetailsImpl.setPricingQualifierSid(gcmGlobalDetails.getPricingQualifierSid());
		gcmGlobalDetailsImpl.setTempStatus(gcmGlobalDetails.getTempStatus());
		gcmGlobalDetailsImpl.setItemRebateEndDate(gcmGlobalDetails.getItemRebateEndDate());
		gcmGlobalDetailsImpl.setPriceToleranceInterval(gcmGlobalDetails.getPriceToleranceInterval());
		gcmGlobalDetailsImpl.setItemRebateStartDate(gcmGlobalDetails.getItemRebateStartDate());
		gcmGlobalDetailsImpl.setOperation(gcmGlobalDetails.getOperation());
		gcmGlobalDetailsImpl.setCfpModelSid(gcmGlobalDetails.getCfpModelSid());
		gcmGlobalDetailsImpl.setItemStatusSid(gcmGlobalDetails.getItemStatusSid());
		gcmGlobalDetailsImpl.setIfpContractSid(gcmGlobalDetails.getIfpContractSid());

		return gcmGlobalDetailsImpl;
	}

	/**
	 * Returns the gcm global details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm global details
	 * @return the gcm global details
	 * @throws NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchGcmGlobalDetailsException {
		GcmGlobalDetails gcmGlobalDetails = fetchByPrimaryKey(primaryKey);

		if (gcmGlobalDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchGcmGlobalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return gcmGlobalDetails;
	}

	/**
	 * Returns the gcm global details with the primary key or throws a {@link NoSuchGcmGlobalDetailsException} if it could not be found.
	 *
	 * @param gcmGlobalDetailsSid the primary key of the gcm global details
	 * @return the gcm global details
	 * @throws NoSuchGcmGlobalDetailsException if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails findByPrimaryKey(int gcmGlobalDetailsSid)
		throws NoSuchGcmGlobalDetailsException {
		return findByPrimaryKey((Serializable)gcmGlobalDetailsSid);
	}

	/**
	 * Returns the gcm global details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the gcm global details
	 * @return the gcm global details, or <code>null</code> if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
				GcmGlobalDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		GcmGlobalDetails gcmGlobalDetails = (GcmGlobalDetails)serializable;

		if (gcmGlobalDetails == null) {
			Session session = null;

			try {
				session = openSession();

				gcmGlobalDetails = (GcmGlobalDetails)session.get(GcmGlobalDetailsImpl.class,
						primaryKey);

				if (gcmGlobalDetails != null) {
					cacheResult(gcmGlobalDetails);
				}
				else {
					entityCache.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
						GcmGlobalDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmGlobalDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return gcmGlobalDetails;
	}

	/**
	 * Returns the gcm global details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param gcmGlobalDetailsSid the primary key of the gcm global details
	 * @return the gcm global details, or <code>null</code> if a gcm global details with the primary key could not be found
	 */
	@Override
	public GcmGlobalDetails fetchByPrimaryKey(int gcmGlobalDetailsSid) {
		return fetchByPrimaryKey((Serializable)gcmGlobalDetailsSid);
	}

	@Override
	public Map<Serializable, GcmGlobalDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, GcmGlobalDetails> map = new HashMap<Serializable, GcmGlobalDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			GcmGlobalDetails gcmGlobalDetails = fetchByPrimaryKey(primaryKey);

			if (gcmGlobalDetails != null) {
				map.put(primaryKey, gcmGlobalDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmGlobalDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (GcmGlobalDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_GCMGLOBALDETAILS_WHERE_PKS_IN);

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

			for (GcmGlobalDetails gcmGlobalDetails : (List<GcmGlobalDetails>)q.list()) {
				map.put(gcmGlobalDetails.getPrimaryKeyObj(), gcmGlobalDetails);

				cacheResult(gcmGlobalDetails);

				uncachedPrimaryKeys.remove(gcmGlobalDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(GcmGlobalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					GcmGlobalDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the gcm global detailses.
	 *
	 * @return the gcm global detailses
	 */
	@Override
	public List<GcmGlobalDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the gcm global detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm global detailses
	 * @param end the upper bound of the range of gcm global detailses (not inclusive)
	 * @return the range of gcm global detailses
	 */
	@Override
	public List<GcmGlobalDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the gcm global detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm global detailses
	 * @param end the upper bound of the range of gcm global detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of gcm global detailses
	 */
	@Override
	public List<GcmGlobalDetails> findAll(int start, int end,
		OrderByComparator<GcmGlobalDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the gcm global detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link GcmGlobalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of gcm global detailses
	 * @param end the upper bound of the range of gcm global detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of gcm global detailses
	 */
	@Override
	public List<GcmGlobalDetails> findAll(int start, int end,
		OrderByComparator<GcmGlobalDetails> orderByComparator,
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

		List<GcmGlobalDetails> list = null;

		if (retrieveFromCache) {
			list = (List<GcmGlobalDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_GCMGLOBALDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_GCMGLOBALDETAILS;

				if (pagination) {
					sql = sql.concat(GcmGlobalDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<GcmGlobalDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<GcmGlobalDetails>)QueryUtil.list(q,
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
	 * Removes all the gcm global detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (GcmGlobalDetails gcmGlobalDetails : findAll()) {
			remove(gcmGlobalDetails);
		}
	}

	/**
	 * Returns the number of gcm global detailses.
	 *
	 * @return the number of gcm global detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_GCMGLOBALDETAILS);

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
		return GcmGlobalDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the gcm global details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(GcmGlobalDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_GCMGLOBALDETAILS = "SELECT gcmGlobalDetails FROM GcmGlobalDetails gcmGlobalDetails";
	private static final String _SQL_SELECT_GCMGLOBALDETAILS_WHERE_PKS_IN = "SELECT gcmGlobalDetails FROM GcmGlobalDetails gcmGlobalDetails WHERE GCM_GLOBAL_DETAILS_SID IN (";
	private static final String _SQL_COUNT_GCMGLOBALDETAILS = "SELECT COUNT(gcmGlobalDetails) FROM GcmGlobalDetails gcmGlobalDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "gcmGlobalDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No GcmGlobalDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(GcmGlobalDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemStatus", "formulaMethodId", "moduleName",
				"paymentFrequency", "endDate", "cfpStartDate",
				"priceProtectionStartDate", "tempItemMasterSid", "brandName",
				"modifiedDate", "contractMasterSid", "modifiedBy",
				"subModuleName", "theraputicClass", "gcmGlobalDetailsSid",
				"checkRecord", "paymentMethod", "contractPriceEndDate",
				"psContractSid", "priceProtectionEndDate", "startDate",
				"screenName", "rsContractSid", "itemName", "sessionId",
				"cfpStatus", "rsModelSid", "cfpContractSid", "price",
				"tempEndDate", "itemMasterSid", "itemType", "forecastingType",
				"itemId", "basePrice", "status", "formulaName",
				"workflowMasterSid", "priceTolerance", "createdBy",
				"createdDate", "tempStartDate", "cfpEndDate", "psModelSid",
				"formulaId", "itemNo", "contractPrice", "ifpModelSid",
				"priceToleranceType", "rebateAmount", "userId",
				"projectionMasterSid", "contractPriceStartDate",
				"priceToleranceFrequency", "ifpContractAttachedStatus",
				"rebatePlanSystemId", "rebatePlanName", "calendar",
				"pricingQualifierSid", "tempStatus", "itemRebateEndDate",
				"priceToleranceInterval", "itemRebateStartDate", "operation",
				"cfpModelSid", "itemStatusSid", "ifpContractSid"
			});
}