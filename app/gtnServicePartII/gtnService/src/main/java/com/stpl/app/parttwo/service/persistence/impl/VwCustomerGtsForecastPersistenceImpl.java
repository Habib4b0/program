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

import com.stpl.app.parttwo.exception.NoSuchVwCustomerGtsForecastException;
import com.stpl.app.parttwo.model.VwCustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.VwCustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastPersistence;

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
 * The persistence implementation for the vw customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwCustomerGtsForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwCustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public class VwCustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<VwCustomerGtsForecast>
	implements VwCustomerGtsForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwCustomerGtsForecastUtil} to access the vw customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwCustomerGtsForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			VwCustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			VwCustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwCustomerGtsForecastPersistenceImpl() {
		setModelClass(VwCustomerGtsForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("deductionAmount", "DEDUCTION_AMOUNT");
			dbColumnNames.put("deductionId", "DEDUCTION_ID");
			dbColumnNames.put("forecastDate", "FORECAST_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("salesAmount", "SALES_AMOUNT");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("deductionRate", "DEDUCTION_RATE");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("customerGtsForecastSid",
				"CUSTOMER_GTS_FORECAST_SID");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
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
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("forecastName", "FORECAST_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw customer gts forecast in the entity cache if it is enabled.
	 *
	 * @param vwCustomerGtsForecast the vw customer gts forecast
	 */
	@Override
	public void cacheResult(VwCustomerGtsForecast vwCustomerGtsForecast) {
		entityCache.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastImpl.class,
			vwCustomerGtsForecast.getPrimaryKey(), vwCustomerGtsForecast);

		vwCustomerGtsForecast.resetOriginalValues();
	}

	/**
	 * Caches the vw customer gts forecasts in the entity cache if it is enabled.
	 *
	 * @param vwCustomerGtsForecasts the vw customer gts forecasts
	 */
	@Override
	public void cacheResult(List<VwCustomerGtsForecast> vwCustomerGtsForecasts) {
		for (VwCustomerGtsForecast vwCustomerGtsForecast : vwCustomerGtsForecasts) {
			if (entityCache.getResult(
						VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						VwCustomerGtsForecastImpl.class,
						vwCustomerGtsForecast.getPrimaryKey()) == null) {
				cacheResult(vwCustomerGtsForecast);
			}
			else {
				vwCustomerGtsForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw customer gts forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwCustomerGtsForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw customer gts forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwCustomerGtsForecast vwCustomerGtsForecast) {
		entityCache.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastImpl.class,
			vwCustomerGtsForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwCustomerGtsForecast> vwCustomerGtsForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwCustomerGtsForecast vwCustomerGtsForecast : vwCustomerGtsForecasts) {
			entityCache.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				VwCustomerGtsForecastImpl.class,
				vwCustomerGtsForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw customer gts forecast with the primary key. Does not add the vw customer gts forecast to the database.
	 *
	 * @param customerGtsForecastSid the primary key for the new vw customer gts forecast
	 * @return the new vw customer gts forecast
	 */
	@Override
	public VwCustomerGtsForecast create(int customerGtsForecastSid) {
		VwCustomerGtsForecast vwCustomerGtsForecast = new VwCustomerGtsForecastImpl();

		vwCustomerGtsForecast.setNew(true);
		vwCustomerGtsForecast.setPrimaryKey(customerGtsForecastSid);

		return vwCustomerGtsForecast;
	}

	/**
	 * Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customerGtsForecastSid the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast that was removed
	 * @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast remove(int customerGtsForecastSid)
		throws NoSuchVwCustomerGtsForecastException {
		return remove((Serializable)customerGtsForecastSid);
	}

	/**
	 * Removes the vw customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast that was removed
	 * @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast remove(Serializable primaryKey)
		throws NoSuchVwCustomerGtsForecastException {
		Session session = null;

		try {
			session = openSession();

			VwCustomerGtsForecast vwCustomerGtsForecast = (VwCustomerGtsForecast)session.get(VwCustomerGtsForecastImpl.class,
					primaryKey);

			if (vwCustomerGtsForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwCustomerGtsForecast);
		}
		catch (NoSuchVwCustomerGtsForecastException nsee) {
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
	protected VwCustomerGtsForecast removeImpl(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		vwCustomerGtsForecast = toUnwrappedModel(vwCustomerGtsForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwCustomerGtsForecast)) {
				vwCustomerGtsForecast = (VwCustomerGtsForecast)session.get(VwCustomerGtsForecastImpl.class,
						vwCustomerGtsForecast.getPrimaryKeyObj());
			}

			if (vwCustomerGtsForecast != null) {
				session.delete(vwCustomerGtsForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwCustomerGtsForecast != null) {
			clearCache(vwCustomerGtsForecast);
		}

		return vwCustomerGtsForecast;
	}

	@Override
	public VwCustomerGtsForecast updateImpl(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		vwCustomerGtsForecast = toUnwrappedModel(vwCustomerGtsForecast);

		boolean isNew = vwCustomerGtsForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwCustomerGtsForecast.isNew()) {
				session.save(vwCustomerGtsForecast);

				vwCustomerGtsForecast.setNew(false);
			}
			else {
				vwCustomerGtsForecast = (VwCustomerGtsForecast)session.merge(vwCustomerGtsForecast);
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

		entityCache.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			VwCustomerGtsForecastImpl.class,
			vwCustomerGtsForecast.getPrimaryKey(), vwCustomerGtsForecast, false);

		vwCustomerGtsForecast.resetOriginalValues();

		return vwCustomerGtsForecast;
	}

	protected VwCustomerGtsForecast toUnwrappedModel(
		VwCustomerGtsForecast vwCustomerGtsForecast) {
		if (vwCustomerGtsForecast instanceof VwCustomerGtsForecastImpl) {
			return vwCustomerGtsForecast;
		}

		VwCustomerGtsForecastImpl vwCustomerGtsForecastImpl = new VwCustomerGtsForecastImpl();

		vwCustomerGtsForecastImpl.setNew(vwCustomerGtsForecast.isNew());
		vwCustomerGtsForecastImpl.setPrimaryKey(vwCustomerGtsForecast.getPrimaryKey());

		vwCustomerGtsForecastImpl.setPrice(vwCustomerGtsForecast.getPrice());
		vwCustomerGtsForecastImpl.setForecastYear(vwCustomerGtsForecast.getForecastYear());
		vwCustomerGtsForecastImpl.setDeductionAmount(vwCustomerGtsForecast.getDeductionAmount());
		vwCustomerGtsForecastImpl.setDeductionId(vwCustomerGtsForecast.getDeductionId());
		vwCustomerGtsForecastImpl.setForecastDate(vwCustomerGtsForecast.getForecastDate());
		vwCustomerGtsForecastImpl.setItemId(vwCustomerGtsForecast.getItemId());
		vwCustomerGtsForecastImpl.setModifiedDate(vwCustomerGtsForecast.getModifiedDate());
		vwCustomerGtsForecastImpl.setSource(vwCustomerGtsForecast.getSource());
		vwCustomerGtsForecastImpl.setCreatedBy(vwCustomerGtsForecast.getCreatedBy());
		vwCustomerGtsForecastImpl.setCreatedDate(vwCustomerGtsForecast.getCreatedDate());
		vwCustomerGtsForecastImpl.setAddChgDelIndicator(vwCustomerGtsForecast.getAddChgDelIndicator());
		vwCustomerGtsForecastImpl.setModifiedBy(vwCustomerGtsForecast.getModifiedBy());
		vwCustomerGtsForecastImpl.setSalesAmount(vwCustomerGtsForecast.getSalesAmount());
		vwCustomerGtsForecastImpl.setUdc6(vwCustomerGtsForecast.getUdc6());
		vwCustomerGtsForecastImpl.setUdc5(vwCustomerGtsForecast.getUdc5());
		vwCustomerGtsForecastImpl.setDeductionType(vwCustomerGtsForecast.getDeductionType());
		vwCustomerGtsForecastImpl.setUdc4(vwCustomerGtsForecast.getUdc4());
		vwCustomerGtsForecastImpl.setUnits(vwCustomerGtsForecast.getUnits());
		vwCustomerGtsForecastImpl.setDeductionRate(vwCustomerGtsForecast.getDeductionRate());
		vwCustomerGtsForecastImpl.setUdc1(vwCustomerGtsForecast.getUdc1());
		vwCustomerGtsForecastImpl.setCustomerGtsForecastSid(vwCustomerGtsForecast.getCustomerGtsForecastSid());
		vwCustomerGtsForecastImpl.setUdc2(vwCustomerGtsForecast.getUdc2());
		vwCustomerGtsForecastImpl.setUdc3(vwCustomerGtsForecast.getUdc3());
		vwCustomerGtsForecastImpl.setCountry(vwCustomerGtsForecast.getCountry());
		vwCustomerGtsForecastImpl.setCompanyIdString(vwCustomerGtsForecast.getCompanyIdString());
		vwCustomerGtsForecastImpl.setForecastValueType(vwCustomerGtsForecast.getForecastValueType());
		vwCustomerGtsForecastImpl.setDeductionCategory(vwCustomerGtsForecast.getDeductionCategory());
		vwCustomerGtsForecastImpl.setAdjustmentCode(vwCustomerGtsForecast.getAdjustmentCode());
		vwCustomerGtsForecastImpl.setDeductionProgramType(vwCustomerGtsForecast.getDeductionProgramType());
		vwCustomerGtsForecastImpl.setCustomerGtsForecastIntfId(vwCustomerGtsForecast.getCustomerGtsForecastIntfId());
		vwCustomerGtsForecastImpl.setSalesInclusion(vwCustomerGtsForecast.getSalesInclusion());
		vwCustomerGtsForecastImpl.setForecastVer(vwCustomerGtsForecast.getForecastVer());
		vwCustomerGtsForecastImpl.setBatchId(vwCustomerGtsForecast.getBatchId());
		vwCustomerGtsForecastImpl.setPriceType(vwCustomerGtsForecast.getPriceType());
		vwCustomerGtsForecastImpl.setForecastMonth(vwCustomerGtsForecast.getForecastMonth());
		vwCustomerGtsForecastImpl.setDeductionInclusion(vwCustomerGtsForecast.getDeductionInclusion());
		vwCustomerGtsForecastImpl.setSegment(vwCustomerGtsForecast.getSegment());
		vwCustomerGtsForecastImpl.setBrand(vwCustomerGtsForecast.getBrand());
		vwCustomerGtsForecastImpl.setForecastName(vwCustomerGtsForecast.getForecastName());

		return vwCustomerGtsForecastImpl;
	}

	/**
	 * Returns the vw customer gts forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast
	 * @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwCustomerGtsForecastException {
		VwCustomerGtsForecast vwCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

		if (vwCustomerGtsForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwCustomerGtsForecast;
	}

	/**
	 * Returns the vw customer gts forecast with the primary key or throws a {@link NoSuchVwCustomerGtsForecastException} if it could not be found.
	 *
	 * @param customerGtsForecastSid the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast
	 * @throws NoSuchVwCustomerGtsForecastException if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
		throws NoSuchVwCustomerGtsForecastException {
		return findByPrimaryKey((Serializable)customerGtsForecastSid);
	}

	/**
	 * Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				VwCustomerGtsForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwCustomerGtsForecast vwCustomerGtsForecast = (VwCustomerGtsForecast)serializable;

		if (vwCustomerGtsForecast == null) {
			Session session = null;

			try {
				session = openSession();

				vwCustomerGtsForecast = (VwCustomerGtsForecast)session.get(VwCustomerGtsForecastImpl.class,
						primaryKey);

				if (vwCustomerGtsForecast != null) {
					cacheResult(vwCustomerGtsForecast);
				}
				else {
					entityCache.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						VwCustomerGtsForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					VwCustomerGtsForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwCustomerGtsForecast;
	}

	/**
	 * Returns the vw customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customerGtsForecastSid the primary key of the vw customer gts forecast
	 * @return the vw customer gts forecast, or <code>null</code> if a vw customer gts forecast with the primary key could not be found
	 */
	@Override
	public VwCustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid) {
		return fetchByPrimaryKey((Serializable)customerGtsForecastSid);
	}

	@Override
	public Map<Serializable, VwCustomerGtsForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwCustomerGtsForecast> map = new HashMap<Serializable, VwCustomerGtsForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwCustomerGtsForecast vwCustomerGtsForecast = fetchByPrimaryKey(primaryKey);

			if (vwCustomerGtsForecast != null) {
				map.put(primaryKey, vwCustomerGtsForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					VwCustomerGtsForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwCustomerGtsForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWCUSTOMERGTSFORECAST_WHERE_PKS_IN);

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

			for (VwCustomerGtsForecast vwCustomerGtsForecast : (List<VwCustomerGtsForecast>)q.list()) {
				map.put(vwCustomerGtsForecast.getPrimaryKeyObj(),
					vwCustomerGtsForecast);

				cacheResult(vwCustomerGtsForecast);

				uncachedPrimaryKeys.remove(vwCustomerGtsForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwCustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					VwCustomerGtsForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw customer gts forecasts.
	 *
	 * @return the vw customer gts forecasts
	 */
	@Override
	public List<VwCustomerGtsForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw customer gts forecasts
	 * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	 * @return the range of vw customer gts forecasts
	 */
	@Override
	public List<VwCustomerGtsForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw customer gts forecasts
	 * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw customer gts forecasts
	 */
	@Override
	public List<VwCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<VwCustomerGtsForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwCustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw customer gts forecasts
	 * @param end the upper bound of the range of vw customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw customer gts forecasts
	 */
	@Override
	public List<VwCustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<VwCustomerGtsForecast> orderByComparator,
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

		List<VwCustomerGtsForecast> list = null;

		if (retrieveFromCache) {
			list = (List<VwCustomerGtsForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWCUSTOMERGTSFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWCUSTOMERGTSFORECAST;

				if (pagination) {
					sql = sql.concat(VwCustomerGtsForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwCustomerGtsForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwCustomerGtsForecast>)QueryUtil.list(q,
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
	 * Removes all the vw customer gts forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwCustomerGtsForecast vwCustomerGtsForecast : findAll()) {
			remove(vwCustomerGtsForecast);
		}
	}

	/**
	 * Returns the number of vw customer gts forecasts.
	 *
	 * @return the number of vw customer gts forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWCUSTOMERGTSFORECAST);

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
		return VwCustomerGtsForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw customer gts forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwCustomerGtsForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWCUSTOMERGTSFORECAST = "SELECT vwCustomerGtsForecast FROM VwCustomerGtsForecast vwCustomerGtsForecast";
	private static final String _SQL_SELECT_VWCUSTOMERGTSFORECAST_WHERE_PKS_IN = "SELECT vwCustomerGtsForecast FROM VwCustomerGtsForecast vwCustomerGtsForecast WHERE CUSTOMER_GTS_FORECAST_SID IN (";
	private static final String _SQL_COUNT_VWCUSTOMERGTSFORECAST = "SELECT COUNT(vwCustomerGtsForecast) FROM VwCustomerGtsForecast vwCustomerGtsForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwCustomerGtsForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwCustomerGtsForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwCustomerGtsForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "forecastYear", "deductionAmount", "deductionId",
				"forecastDate", "itemId", "modifiedDate", "source", "createdBy",
				"createdDate", "addChgDelIndicator", "modifiedBy", "salesAmount",
				"udc6", "udc5", "deductionType", "udc4", "units",
				"deductionRate", "udc1", "customerGtsForecastSid", "udc2",
				"udc3", "country", "companyIdString", "forecastValueType",
				"deductionCategory", "adjustmentCode", "deductionProgramType",
				"customerGtsForecastIntfId", "salesInclusion", "forecastVer",
				"batchId", "priceType", "forecastMonth", "deductionInclusion",
				"segment", "brand", "forecastName"
			});
}