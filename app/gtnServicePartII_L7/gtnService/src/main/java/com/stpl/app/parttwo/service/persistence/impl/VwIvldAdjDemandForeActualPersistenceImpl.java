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
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchVwIvldAdjDemandForeActualException;
import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;
import com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualImpl;
import com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl;
import com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualPersistence;

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
 * The persistence implementation for the vw ivld adj demand fore actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActualPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwIvldAdjDemandForeActualUtil
 * @generated
 */
@ProviderType
public class VwIvldAdjDemandForeActualPersistenceImpl
	extends BasePersistenceImpl<VwIvldAdjDemandForeActual>
	implements VwIvldAdjDemandForeActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwIvldAdjDemandForeActualUtil} to access the vw ivld adj demand fore actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwIvldAdjDemandForeActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
			VwIvldAdjDemandForeActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
			VwIvldAdjDemandForeActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public VwIvldAdjDemandForeActualPersistenceImpl() {
		setModelClass(VwIvldAdjDemandForeActual.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastVersion", "FORECAST_VERSION");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("adjustedDemandForecastIntfId",
				"ADJUSTED_DEMAND_FORECAST_INTF_ID");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("totalAdjustedDemandUnits",
				"TOTAL_ADJUSTED_DEMAND_UNITS");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("isForecast", "IS_FORECAST");
			dbColumnNames.put("totalAdjustedDemandAmount",
				"TOTAL_ADJUSTED_DEMAND_AMOUNT");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
			dbColumnNames.put("ivldAdjustedDemandForecastSid",
				"IVLD_ADJUSTED_DEMAND_FORECAST_SID");
			dbColumnNames.put("marketSizeUnits", "MARKET_SIZE_UNITS");
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
	 * Caches the vw ivld adj demand fore actual in the entity cache if it is enabled.
	 *
	 * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
	 */
	@Override
	public void cacheResult(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		entityCache.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualImpl.class,
			vwIvldAdjDemandForeActual.getPrimaryKey(), vwIvldAdjDemandForeActual);

		vwIvldAdjDemandForeActual.resetOriginalValues();
	}

	/**
	 * Caches the vw ivld adj demand fore actuals in the entity cache if it is enabled.
	 *
	 * @param vwIvldAdjDemandForeActuals the vw ivld adj demand fore actuals
	 */
	@Override
	public void cacheResult(
		List<VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
		for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : vwIvldAdjDemandForeActuals) {
			if (entityCache.getResult(
						VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldAdjDemandForeActualImpl.class,
						vwIvldAdjDemandForeActual.getPrimaryKey()) == null) {
				cacheResult(vwIvldAdjDemandForeActual);
			}
			else {
				vwIvldAdjDemandForeActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw ivld adj demand fore actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwIvldAdjDemandForeActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw ivld adj demand fore actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		entityCache.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualImpl.class,
			vwIvldAdjDemandForeActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : vwIvldAdjDemandForeActuals) {
			entityCache.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldAdjDemandForeActualImpl.class,
				vwIvldAdjDemandForeActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
	 *
	 * @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
	 * @return the new vw ivld adj demand fore actual
	 */
	@Override
	public VwIvldAdjDemandForeActual create(int ivldAdjustedDemandForecastSid) {
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = new VwIvldAdjDemandForeActualImpl();

		vwIvldAdjDemandForeActual.setNew(true);
		vwIvldAdjDemandForeActual.setPrimaryKey(ivldAdjustedDemandForecastSid);

		return vwIvldAdjDemandForeActual;
	}

	/**
	 * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual that was removed
	 * @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual remove(int ivldAdjustedDemandForecastSid)
		throws NoSuchVwIvldAdjDemandForeActualException {
		return remove((Serializable)ivldAdjustedDemandForecastSid);
	}

	/**
	 * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual that was removed
	 * @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual remove(Serializable primaryKey)
		throws NoSuchVwIvldAdjDemandForeActualException {
		Session session = null;

		try {
			session = openSession();

			VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual)session.get(VwIvldAdjDemandForeActualImpl.class,
					primaryKey);

			if (vwIvldAdjDemandForeActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwIvldAdjDemandForeActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwIvldAdjDemandForeActual);
		}
		catch (NoSuchVwIvldAdjDemandForeActualException nsee) {
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
	protected VwIvldAdjDemandForeActual removeImpl(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		vwIvldAdjDemandForeActual = toUnwrappedModel(vwIvldAdjDemandForeActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwIvldAdjDemandForeActual)) {
				vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual)session.get(VwIvldAdjDemandForeActualImpl.class,
						vwIvldAdjDemandForeActual.getPrimaryKeyObj());
			}

			if (vwIvldAdjDemandForeActual != null) {
				session.delete(vwIvldAdjDemandForeActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwIvldAdjDemandForeActual != null) {
			clearCache(vwIvldAdjDemandForeActual);
		}

		return vwIvldAdjDemandForeActual;
	}

	@Override
	public VwIvldAdjDemandForeActual updateImpl(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		vwIvldAdjDemandForeActual = toUnwrappedModel(vwIvldAdjDemandForeActual);

		boolean isNew = vwIvldAdjDemandForeActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwIvldAdjDemandForeActual.isNew()) {
				session.save(vwIvldAdjDemandForeActual);

				vwIvldAdjDemandForeActual.setNew(false);
			}
			else {
				vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual)session.merge(vwIvldAdjDemandForeActual);
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

		entityCache.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldAdjDemandForeActualImpl.class,
			vwIvldAdjDemandForeActual.getPrimaryKey(),
			vwIvldAdjDemandForeActual, false);

		vwIvldAdjDemandForeActual.resetOriginalValues();

		return vwIvldAdjDemandForeActual;
	}

	protected VwIvldAdjDemandForeActual toUnwrappedModel(
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
		if (vwIvldAdjDemandForeActual instanceof VwIvldAdjDemandForeActualImpl) {
			return vwIvldAdjDemandForeActual;
		}

		VwIvldAdjDemandForeActualImpl vwIvldAdjDemandForeActualImpl = new VwIvldAdjDemandForeActualImpl();

		vwIvldAdjDemandForeActualImpl.setNew(vwIvldAdjDemandForeActual.isNew());
		vwIvldAdjDemandForeActualImpl.setPrimaryKey(vwIvldAdjDemandForeActual.getPrimaryKey());

		vwIvldAdjDemandForeActualImpl.setForecastVersion(vwIvldAdjDemandForeActual.getForecastVersion());
		vwIvldAdjDemandForeActualImpl.setGrossUnits(vwIvldAdjDemandForeActual.getGrossUnits());
		vwIvldAdjDemandForeActualImpl.setBusinessUnitNo(vwIvldAdjDemandForeActual.getBusinessUnitNo());
		vwIvldAdjDemandForeActualImpl.setYear(vwIvldAdjDemandForeActual.getYear());
		vwIvldAdjDemandForeActualImpl.setItemId(vwIvldAdjDemandForeActual.getItemId());
		vwIvldAdjDemandForeActualImpl.setBrandName(vwIvldAdjDemandForeActual.getBrandName());
		vwIvldAdjDemandForeActualImpl.setModifiedDate(vwIvldAdjDemandForeActual.getModifiedDate());
		vwIvldAdjDemandForeActualImpl.setOrganizationKey(vwIvldAdjDemandForeActual.getOrganizationKey());
		vwIvldAdjDemandForeActualImpl.setCreatedDate(vwIvldAdjDemandForeActual.getCreatedDate());
		vwIvldAdjDemandForeActualImpl.setCreatedBy(vwIvldAdjDemandForeActual.getCreatedBy());
		vwIvldAdjDemandForeActualImpl.setSource(vwIvldAdjDemandForeActual.getSource());
		vwIvldAdjDemandForeActualImpl.setMarketShareRatio(vwIvldAdjDemandForeActual.getMarketShareRatio());
		vwIvldAdjDemandForeActualImpl.setBusinessUnitName(vwIvldAdjDemandForeActual.getBusinessUnitName());
		vwIvldAdjDemandForeActualImpl.setAddChgDelIndicator(vwIvldAdjDemandForeActual.getAddChgDelIndicator());
		vwIvldAdjDemandForeActualImpl.setItemIdentifier(vwIvldAdjDemandForeActual.getItemIdentifier());
		vwIvldAdjDemandForeActualImpl.setErrorCode(vwIvldAdjDemandForeActual.getErrorCode());
		vwIvldAdjDemandForeActualImpl.setModifiedBy(vwIvldAdjDemandForeActual.getModifiedBy());
		vwIvldAdjDemandForeActualImpl.setMarketShareUnits(vwIvldAdjDemandForeActual.getMarketShareUnits());
		vwIvldAdjDemandForeActualImpl.setMonth(vwIvldAdjDemandForeActual.getMonth());
		vwIvldAdjDemandForeActualImpl.setInventoryUnitChange(vwIvldAdjDemandForeActual.getInventoryUnitChange());
		vwIvldAdjDemandForeActualImpl.setReprocessedFlag(vwIvldAdjDemandForeActual.getReprocessedFlag());
		vwIvldAdjDemandForeActualImpl.setUncapturedUnitsRatio(vwIvldAdjDemandForeActual.getUncapturedUnitsRatio());
		vwIvldAdjDemandForeActualImpl.setReasonForFailure(vwIvldAdjDemandForeActual.getReasonForFailure());
		vwIvldAdjDemandForeActualImpl.setAdjustedDemandForecastIntfId(vwIvldAdjDemandForeActual.getAdjustedDemandForecastIntfId());
		vwIvldAdjDemandForeActualImpl.setCountry(vwIvldAdjDemandForeActual.getCountry());
		vwIvldAdjDemandForeActualImpl.setForecastType(vwIvldAdjDemandForeActual.getForecastType());
		vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandUnits(vwIvldAdjDemandForeActual.getTotalAdjustedDemandUnits());
		vwIvldAdjDemandForeActualImpl.setBrandId(vwIvldAdjDemandForeActual.getBrandId());
		vwIvldAdjDemandForeActualImpl.setIsForecast(vwIvldAdjDemandForeActual.getIsForecast());
		vwIvldAdjDemandForeActualImpl.setTotalAdjustedDemandAmount(vwIvldAdjDemandForeActual.getTotalAdjustedDemandAmount());
		vwIvldAdjDemandForeActualImpl.setUncapturedUnits(vwIvldAdjDemandForeActual.getUncapturedUnits());
		vwIvldAdjDemandForeActualImpl.setGrossPrice(vwIvldAdjDemandForeActual.getGrossPrice());
		vwIvldAdjDemandForeActualImpl.setGrossAmount(vwIvldAdjDemandForeActual.getGrossAmount());
		vwIvldAdjDemandForeActualImpl.setItemIdentifierCodeQualifier(vwIvldAdjDemandForeActual.getItemIdentifierCodeQualifier());
		vwIvldAdjDemandForeActualImpl.setBatchId(vwIvldAdjDemandForeActual.getBatchId());
		vwIvldAdjDemandForeActualImpl.setItemName(vwIvldAdjDemandForeActual.getItemName());
		vwIvldAdjDemandForeActualImpl.setErrorField(vwIvldAdjDemandForeActual.getErrorField());
		vwIvldAdjDemandForeActualImpl.setNetSalesPrice(vwIvldAdjDemandForeActual.getNetSalesPrice());
		vwIvldAdjDemandForeActualImpl.setNetSalesAmount(vwIvldAdjDemandForeActual.getNetSalesAmount());
		vwIvldAdjDemandForeActualImpl.setSegment(vwIvldAdjDemandForeActual.getSegment());
		vwIvldAdjDemandForeActualImpl.setForecastName(vwIvldAdjDemandForeActual.getForecastName());
		vwIvldAdjDemandForeActualImpl.setIvldAdjustedDemandForecastSid(vwIvldAdjDemandForeActual.getIvldAdjustedDemandForecastSid());
		vwIvldAdjDemandForeActualImpl.setMarketSizeUnits(vwIvldAdjDemandForeActual.getMarketSizeUnits());
		vwIvldAdjDemandForeActualImpl.setCheckRecord(vwIvldAdjDemandForeActual.isCheckRecord());

		return vwIvldAdjDemandForeActualImpl;
	}

	/**
	 * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual
	 * @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwIvldAdjDemandForeActualException {
		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = fetchByPrimaryKey(primaryKey);

		if (vwIvldAdjDemandForeActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwIvldAdjDemandForeActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwIvldAdjDemandForeActual;
	}

	/**
	 * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link NoSuchVwIvldAdjDemandForeActualException} if it could not be found.
	 *
	 * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual
	 * @throws NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual findByPrimaryKey(
		int ivldAdjustedDemandForecastSid)
		throws NoSuchVwIvldAdjDemandForeActualException {
		return findByPrimaryKey((Serializable)ivldAdjustedDemandForecastSid);
	}

	/**
	 * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldAdjDemandForeActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual)serializable;

		if (vwIvldAdjDemandForeActual == null) {
			Session session = null;

			try {
				session = openSession();

				vwIvldAdjDemandForeActual = (VwIvldAdjDemandForeActual)session.get(VwIvldAdjDemandForeActualImpl.class,
						primaryKey);

				if (vwIvldAdjDemandForeActual != null) {
					cacheResult(vwIvldAdjDemandForeActual);
				}
				else {
					entityCache.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldAdjDemandForeActualImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldAdjDemandForeActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwIvldAdjDemandForeActual;
	}

	/**
	 * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
	 * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
	 */
	@Override
	public VwIvldAdjDemandForeActual fetchByPrimaryKey(
		int ivldAdjustedDemandForecastSid) {
		return fetchByPrimaryKey((Serializable)ivldAdjustedDemandForecastSid);
	}

	@Override
	public Map<Serializable, VwIvldAdjDemandForeActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwIvldAdjDemandForeActual> map = new HashMap<Serializable, VwIvldAdjDemandForeActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual = fetchByPrimaryKey(primaryKey);

			if (vwIvldAdjDemandForeActual != null) {
				map.put(primaryKey, vwIvldAdjDemandForeActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldAdjDemandForeActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwIvldAdjDemandForeActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL_WHERE_PKS_IN);

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

			for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : (List<VwIvldAdjDemandForeActual>)q.list()) {
				map.put(vwIvldAdjDemandForeActual.getPrimaryKeyObj(),
					vwIvldAdjDemandForeActual);

				cacheResult(vwIvldAdjDemandForeActual);

				uncachedPrimaryKeys.remove(vwIvldAdjDemandForeActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwIvldAdjDemandForeActualModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldAdjDemandForeActualImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw ivld adj demand fore actuals.
	 *
	 * @return the vw ivld adj demand fore actuals
	 */
	@Override
	public List<VwIvldAdjDemandForeActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw ivld adj demand fore actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld adj demand fore actuals
	 * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	 * @return the range of vw ivld adj demand fore actuals
	 */
	@Override
	public List<VwIvldAdjDemandForeActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw ivld adj demand fore actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld adj demand fore actuals
	 * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw ivld adj demand fore actuals
	 */
	@Override
	public List<VwIvldAdjDemandForeActual> findAll(int start, int end,
		OrderByComparator<VwIvldAdjDemandForeActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw ivld adj demand fore actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld adj demand fore actuals
	 * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw ivld adj demand fore actuals
	 */
	@Override
	public List<VwIvldAdjDemandForeActual> findAll(int start, int end,
		OrderByComparator<VwIvldAdjDemandForeActual> orderByComparator,
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

		List<VwIvldAdjDemandForeActual> list = null;

		if (retrieveFromCache) {
			list = (List<VwIvldAdjDemandForeActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL;

				if (pagination) {
					sql = sql.concat(VwIvldAdjDemandForeActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwIvldAdjDemandForeActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwIvldAdjDemandForeActual>)QueryUtil.list(q,
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
	 * Removes all the vw ivld adj demand fore actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual : findAll()) {
			remove(vwIvldAdjDemandForeActual);
		}
	}

	/**
	 * Returns the number of vw ivld adj demand fore actuals.
	 *
	 * @return the number of vw ivld adj demand fore actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWIVLDADJDEMANDFOREACTUAL);

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
		return VwIvldAdjDemandForeActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw ivld adj demand fore actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwIvldAdjDemandForeActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL = "SELECT vwIvldAdjDemandForeActual FROM VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual";
	private static final String _SQL_SELECT_VWIVLDADJDEMANDFOREACTUAL_WHERE_PKS_IN =
		"SELECT vwIvldAdjDemandForeActual FROM VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual WHERE IVLD_ADJUSTED_DEMAND_FORECAST_SID IN (";
	private static final String _SQL_COUNT_VWIVLDADJDEMANDFOREACTUAL = "SELECT COUNT(vwIvldAdjDemandForeActual) FROM VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldAdjDemandForeActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldAdjDemandForeActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwIvldAdjDemandForeActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastVersion", "grossUnits", "businessUnitNo", "year",
				"itemId", "brandName", "modifiedDate", "organizationKey",
				"createdDate", "createdBy", "source", "marketShareRatio",
				"businessUnitName", "addChgDelIndicator", "itemIdentifier",
				"errorCode", "modifiedBy", "marketShareUnits", "month",
				"inventoryUnitChange", "reprocessedFlag", "uncapturedUnitsRatio",
				"reasonForFailure", "adjustedDemandForecastIntfId", "country",
				"forecastType", "totalAdjustedDemandUnits", "brandId",
				"isForecast", "totalAdjustedDemandAmount", "uncapturedUnits",
				"grossPrice", "grossAmount", "itemIdentifierCodeQualifier",
				"batchId", "itemName", "errorField", "netSalesPrice",
				"netSalesAmount", "segment", "forecastName",
				"ivldAdjustedDemandForecastSid", "marketSizeUnits",
				"checkRecord"
			});
}