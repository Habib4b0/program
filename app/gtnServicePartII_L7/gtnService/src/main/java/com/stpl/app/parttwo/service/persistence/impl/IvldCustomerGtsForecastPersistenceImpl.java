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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchIvldCustomerGtsForecastException;
import com.stpl.app.parttwo.model.IvldCustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.IvldCustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastPersistence;

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
 * The persistence implementation for the ivld customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldCustomerGtsForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldCustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public class IvldCustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<IvldCustomerGtsForecast>
	implements IvldCustomerGtsForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldCustomerGtsForecastUtil} to access the ivld customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldCustomerGtsForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			IvldCustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			IvldCustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldCustomerGtsForecastPersistenceImpl() {
		setModelClass(IvldCustomerGtsForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("deductionAmount", "DEDUCTION_AMOUNT");
			dbColumnNames.put("ivldCustomerGtsForecastSid",
				"IVLD_CUSTOMER_GTS_FORECAST_SID");
			dbColumnNames.put("deductionId", "DEDUCTION_ID");
			dbColumnNames.put("forecastDate", "FORECAST_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("salesAmount", "SALES_AMOUNT");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("deductionRate", "DEDUCTION_RATE");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("forecastValueType", "FORECAST_VALUE_TYPE");
			dbColumnNames.put("deductionCategory", "DEDUCTION_CATEGORY");
			dbColumnNames.put("adjustmentCode", "ADJUSTMENT_CODE");
			dbColumnNames.put("deductionProgramType", "DEDUCTION_PROGRAM_TYPE");
			dbColumnNames.put("customerGtsForecastIntfId",
				"CUSTOMER_GTS_FORECAST_INTF_ID");
			dbColumnNames.put("salesInclusion", "SALES_INCLUSION");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("deductionInclusion", "DEDUCTION_INCLUSION");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
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
	 * Caches the ivld customer gts forecast in the entity cache if it is enabled.
	 *
	 * @param ivldCustomerGtsForecast the ivld customer gts forecast
	 */
	@Override
	public void cacheResult(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
		entityCache.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastImpl.class,
			ivldCustomerGtsForecast.getPrimaryKey(), ivldCustomerGtsForecast);

		ivldCustomerGtsForecast.resetOriginalValues();
	}

	/**
	 * Caches the ivld customer gts forecasts in the entity cache if it is enabled.
	 *
	 * @param ivldCustomerGtsForecasts the ivld customer gts forecasts
	 */
	@Override
	public void cacheResult(
		List<IvldCustomerGtsForecast> ivldCustomerGtsForecasts) {
		for (IvldCustomerGtsForecast ivldCustomerGtsForecast : ivldCustomerGtsForecasts) {
			if (entityCache.getResult(
						IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						IvldCustomerGtsForecastImpl.class,
						ivldCustomerGtsForecast.getPrimaryKey()) == null) {
				cacheResult(ivldCustomerGtsForecast);
			}
			else {
				ivldCustomerGtsForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld customer gts forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldCustomerGtsForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld customer gts forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldCustomerGtsForecast ivldCustomerGtsForecast) {
		entityCache.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastImpl.class,
			ivldCustomerGtsForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<IvldCustomerGtsForecast> ivldCustomerGtsForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldCustomerGtsForecast ivldCustomerGtsForecast : ivldCustomerGtsForecasts) {
			entityCache.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				IvldCustomerGtsForecastImpl.class,
				ivldCustomerGtsForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld customer gts forecast with the primary key. Does not add the ivld customer gts forecast to the database.
	 *
	 * @param ivldCustomerGtsForecastSid the primary key for the new ivld customer gts forecast
	 * @return the new ivld customer gts forecast
	 */
	@Override
	public IvldCustomerGtsForecast create(int ivldCustomerGtsForecastSid) {
		IvldCustomerGtsForecast ivldCustomerGtsForecast = new IvldCustomerGtsForecastImpl();

		ivldCustomerGtsForecast.setNew(true);
		ivldCustomerGtsForecast.setPrimaryKey(ivldCustomerGtsForecastSid);

		ivldCustomerGtsForecast.setCompanyId(companyProvider.getCompanyId());

		return ivldCustomerGtsForecast;
	}

	/**
	 * Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast that was removed
	 * @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast remove(int ivldCustomerGtsForecastSid)
		throws NoSuchIvldCustomerGtsForecastException {
		return remove((Serializable)ivldCustomerGtsForecastSid);
	}

	/**
	 * Removes the ivld customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast that was removed
	 * @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast remove(Serializable primaryKey)
		throws NoSuchIvldCustomerGtsForecastException {
		Session session = null;

		try {
			session = openSession();

			IvldCustomerGtsForecast ivldCustomerGtsForecast = (IvldCustomerGtsForecast)session.get(IvldCustomerGtsForecastImpl.class,
					primaryKey);

			if (ivldCustomerGtsForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldCustomerGtsForecast);
		}
		catch (NoSuchIvldCustomerGtsForecastException nsee) {
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
	protected IvldCustomerGtsForecast removeImpl(
		IvldCustomerGtsForecast ivldCustomerGtsForecast) {
		ivldCustomerGtsForecast = toUnwrappedModel(ivldCustomerGtsForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldCustomerGtsForecast)) {
				ivldCustomerGtsForecast = (IvldCustomerGtsForecast)session.get(IvldCustomerGtsForecastImpl.class,
						ivldCustomerGtsForecast.getPrimaryKeyObj());
			}

			if (ivldCustomerGtsForecast != null) {
				session.delete(ivldCustomerGtsForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldCustomerGtsForecast != null) {
			clearCache(ivldCustomerGtsForecast);
		}

		return ivldCustomerGtsForecast;
	}

	@Override
	public IvldCustomerGtsForecast updateImpl(
		IvldCustomerGtsForecast ivldCustomerGtsForecast) {
		ivldCustomerGtsForecast = toUnwrappedModel(ivldCustomerGtsForecast);

		boolean isNew = ivldCustomerGtsForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldCustomerGtsForecast.isNew()) {
				session.save(ivldCustomerGtsForecast);

				ivldCustomerGtsForecast.setNew(false);
			}
			else {
				ivldCustomerGtsForecast = (IvldCustomerGtsForecast)session.merge(ivldCustomerGtsForecast);
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

		entityCache.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldCustomerGtsForecastImpl.class,
			ivldCustomerGtsForecast.getPrimaryKey(), ivldCustomerGtsForecast,
			false);

		ivldCustomerGtsForecast.resetOriginalValues();

		return ivldCustomerGtsForecast;
	}

	protected IvldCustomerGtsForecast toUnwrappedModel(
		IvldCustomerGtsForecast ivldCustomerGtsForecast) {
		if (ivldCustomerGtsForecast instanceof IvldCustomerGtsForecastImpl) {
			return ivldCustomerGtsForecast;
		}

		IvldCustomerGtsForecastImpl ivldCustomerGtsForecastImpl = new IvldCustomerGtsForecastImpl();

		ivldCustomerGtsForecastImpl.setNew(ivldCustomerGtsForecast.isNew());
		ivldCustomerGtsForecastImpl.setPrimaryKey(ivldCustomerGtsForecast.getPrimaryKey());

		ivldCustomerGtsForecastImpl.setPrice(ivldCustomerGtsForecast.getPrice());
		ivldCustomerGtsForecastImpl.setForecastYear(ivldCustomerGtsForecast.getForecastYear());
		ivldCustomerGtsForecastImpl.setDeductionAmount(ivldCustomerGtsForecast.getDeductionAmount());
		ivldCustomerGtsForecastImpl.setIvldCustomerGtsForecastSid(ivldCustomerGtsForecast.getIvldCustomerGtsForecastSid());
		ivldCustomerGtsForecastImpl.setDeductionId(ivldCustomerGtsForecast.getDeductionId());
		ivldCustomerGtsForecastImpl.setForecastDate(ivldCustomerGtsForecast.getForecastDate());
		ivldCustomerGtsForecastImpl.setItemId(ivldCustomerGtsForecast.getItemId());
		ivldCustomerGtsForecastImpl.setModifiedDate(ivldCustomerGtsForecast.getModifiedDate());
		ivldCustomerGtsForecastImpl.setSource(ivldCustomerGtsForecast.getSource());
		ivldCustomerGtsForecastImpl.setCreatedDate(ivldCustomerGtsForecast.getCreatedDate());
		ivldCustomerGtsForecastImpl.setCreatedBy(ivldCustomerGtsForecast.getCreatedBy());
		ivldCustomerGtsForecastImpl.setAddChgDelIndicator(ivldCustomerGtsForecast.getAddChgDelIndicator());
		ivldCustomerGtsForecastImpl.setErrorCode(ivldCustomerGtsForecast.getErrorCode());
		ivldCustomerGtsForecastImpl.setIntfInsertedDate(ivldCustomerGtsForecast.getIntfInsertedDate());
		ivldCustomerGtsForecastImpl.setModifiedBy(ivldCustomerGtsForecast.getModifiedBy());
		ivldCustomerGtsForecastImpl.setSalesAmount(ivldCustomerGtsForecast.getSalesAmount());
		ivldCustomerGtsForecastImpl.setReprocessedFlag(ivldCustomerGtsForecast.getReprocessedFlag());
		ivldCustomerGtsForecastImpl.setUdc6(ivldCustomerGtsForecast.getUdc6());
		ivldCustomerGtsForecastImpl.setUdc5(ivldCustomerGtsForecast.getUdc5());
		ivldCustomerGtsForecastImpl.setDeductionType(ivldCustomerGtsForecast.getDeductionType());
		ivldCustomerGtsForecastImpl.setUdc4(ivldCustomerGtsForecast.getUdc4());
		ivldCustomerGtsForecastImpl.setUdc1(ivldCustomerGtsForecast.getUdc1());
		ivldCustomerGtsForecastImpl.setUnits(ivldCustomerGtsForecast.getUnits());
		ivldCustomerGtsForecastImpl.setDeductionRate(ivldCustomerGtsForecast.getDeductionRate());
		ivldCustomerGtsForecastImpl.setUdc2(ivldCustomerGtsForecast.getUdc2());
		ivldCustomerGtsForecastImpl.setUdc3(ivldCustomerGtsForecast.getUdc3());
		ivldCustomerGtsForecastImpl.setReasonForFailure(ivldCustomerGtsForecast.getReasonForFailure());
		ivldCustomerGtsForecastImpl.setCountry(ivldCustomerGtsForecast.getCountry());
		ivldCustomerGtsForecastImpl.setCompanyId(ivldCustomerGtsForecast.getCompanyId());
		ivldCustomerGtsForecastImpl.setForecastValueType(ivldCustomerGtsForecast.getForecastValueType());
		ivldCustomerGtsForecastImpl.setDeductionCategory(ivldCustomerGtsForecast.getDeductionCategory());
		ivldCustomerGtsForecastImpl.setAdjustmentCode(ivldCustomerGtsForecast.getAdjustmentCode());
		ivldCustomerGtsForecastImpl.setDeductionProgramType(ivldCustomerGtsForecast.getDeductionProgramType());
		ivldCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(ivldCustomerGtsForecast.getCustomerGtsForecastIntfId());
		ivldCustomerGtsForecastImpl.setSalesInclusion(ivldCustomerGtsForecast.getSalesInclusion());
		ivldCustomerGtsForecastImpl.setForecastVer(ivldCustomerGtsForecast.getForecastVer());
		ivldCustomerGtsForecastImpl.setBatchId(ivldCustomerGtsForecast.getBatchId());
		ivldCustomerGtsForecastImpl.setPriceType(ivldCustomerGtsForecast.getPriceType());
		ivldCustomerGtsForecastImpl.setForecastMonth(ivldCustomerGtsForecast.getForecastMonth());
		ivldCustomerGtsForecastImpl.setDeductionInclusion(ivldCustomerGtsForecast.getDeductionInclusion());
		ivldCustomerGtsForecastImpl.setErrorField(ivldCustomerGtsForecast.getErrorField());
		ivldCustomerGtsForecastImpl.setSegment(ivldCustomerGtsForecast.getSegment());
		ivldCustomerGtsForecastImpl.setBrand(ivldCustomerGtsForecast.getBrand());
		ivldCustomerGtsForecastImpl.setForecastName(ivldCustomerGtsForecast.getForecastName());
		ivldCustomerGtsForecastImpl.setCheckRecord(ivldCustomerGtsForecast.isCheckRecord());

		return ivldCustomerGtsForecastImpl;
	}

	/**
	 * Returns the ivld customer gts forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast
	 * @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldCustomerGtsForecastException {
		IvldCustomerGtsForecast ivldCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

		if (ivldCustomerGtsForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldCustomerGtsForecast;
	}

	/**
	 * Returns the ivld customer gts forecast with the primary key or throws a {@link NoSuchIvldCustomerGtsForecastException} if it could not be found.
	 *
	 * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast
	 * @throws NoSuchIvldCustomerGtsForecastException if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast findByPrimaryKey(
		int ivldCustomerGtsForecastSid)
		throws NoSuchIvldCustomerGtsForecastException {
		return findByPrimaryKey((Serializable)ivldCustomerGtsForecastSid);
	}

	/**
	 * Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				IvldCustomerGtsForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldCustomerGtsForecast ivldCustomerGtsForecast = (IvldCustomerGtsForecast)serializable;

		if (ivldCustomerGtsForecast == null) {
			Session session = null;

			try {
				session = openSession();

				ivldCustomerGtsForecast = (IvldCustomerGtsForecast)session.get(IvldCustomerGtsForecastImpl.class,
						primaryKey);

				if (ivldCustomerGtsForecast != null) {
					cacheResult(ivldCustomerGtsForecast);
				}
				else {
					entityCache.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						IvldCustomerGtsForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldCustomerGtsForecast;
	}

	/**
	 * Returns the ivld customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldCustomerGtsForecastSid the primary key of the ivld customer gts forecast
	 * @return the ivld customer gts forecast, or <code>null</code> if a ivld customer gts forecast with the primary key could not be found
	 */
	@Override
	public IvldCustomerGtsForecast fetchByPrimaryKey(
		int ivldCustomerGtsForecastSid) {
		return fetchByPrimaryKey((Serializable)ivldCustomerGtsForecastSid);
	}

	@Override
	public Map<Serializable, IvldCustomerGtsForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldCustomerGtsForecast> map = new HashMap<Serializable, IvldCustomerGtsForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldCustomerGtsForecast ivldCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

			if (ivldCustomerGtsForecast != null) {
				map.put(primaryKey, ivldCustomerGtsForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldCustomerGtsForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDCUSTOMERGTSFORECAST_WHERE_PKS_IN);

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

			for (IvldCustomerGtsForecast ivldCustomerGtsForecast : (List<IvldCustomerGtsForecast>)q.list()) {
				map.put(ivldCustomerGtsForecast.getPrimaryKeyObj(),
					ivldCustomerGtsForecast);

				cacheResult(ivldCustomerGtsForecast);

				uncachedPrimaryKeys.remove(ivldCustomerGtsForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldCustomerGtsForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld customer gts forecasts.
	 *
	 * @return the ivld customer gts forecasts
	 */
	@Override
	public List<IvldCustomerGtsForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts forecasts
	 * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	 * @return the range of ivld customer gts forecasts
	 */
	@Override
	public List<IvldCustomerGtsForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts forecasts
	 * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld customer gts forecasts
	 */
	@Override
	public List<IvldCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld customer gts forecasts
	 * @param end the upper bound of the range of ivld customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld customer gts forecasts
	 */
	@Override
	public List<IvldCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<IvldCustomerGtsForecast> orderByComparator,
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

		List<IvldCustomerGtsForecast> list = null;

		if (retrieveFromCache) {
			list = (List<IvldCustomerGtsForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDCUSTOMERGTSFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDCUSTOMERGTSFORECAST;

				if (pagination) {
					sql = sql.concat(IvldCustomerGtsForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldCustomerGtsForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldCustomerGtsForecast>)QueryUtil.list(q,
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
	 * Removes all the ivld customer gts forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldCustomerGtsForecast ivldCustomerGtsForecast : findAll()) {
			remove(ivldCustomerGtsForecast);
		}
	}

	/**
	 * Returns the number of ivld customer gts forecasts.
	 *
	 * @return the number of ivld customer gts forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDCUSTOMERGTSFORECAST);

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
		return IvldCustomerGtsForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld customer gts forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldCustomerGtsForecastImpl.class.getName());
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
	private static final String _SQL_SELECT_IVLDCUSTOMERGTSFORECAST = "SELECT ivldCustomerGtsForecast FROM IvldCustomerGtsForecast ivldCustomerGtsForecast";
	private static final String _SQL_SELECT_IVLDCUSTOMERGTSFORECAST_WHERE_PKS_IN =
		"SELECT ivldCustomerGtsForecast FROM IvldCustomerGtsForecast ivldCustomerGtsForecast WHERE IVLD_CUSTOMER_GTS_FORECAST_SID IN (";
	private static final String _SQL_COUNT_IVLDCUSTOMERGTSFORECAST = "SELECT COUNT(ivldCustomerGtsForecast) FROM IvldCustomerGtsForecast ivldCustomerGtsForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldCustomerGtsForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldCustomerGtsForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldCustomerGtsForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "forecastYear", "deductionAmount",
				"ivldCustomerGtsForecastSid", "deductionId", "forecastDate",
				"itemId", "modifiedDate", "source", "createdDate", "createdBy",
				"addChgDelIndicator", "errorCode", "intfInsertedDate",
				"modifiedBy", "salesAmount", "reprocessedFlag", "udc6", "udc5",
				"deductionType", "udc4", "udc1", "units", "deductionRate",
				"udc2", "udc3", "reasonForFailure", "country", "companyId",
				"forecastValueType", "deductionCategory", "adjustmentCode",
				"deductionProgramType", "customerGtsForecastIntfId",
				"salesInclusion", "forecastVer", "batchId", "priceType",
				"forecastMonth", "deductionInclusion", "errorField", "segment",
				"brand", "forecastName", "checkRecord"
			});
}