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

import com.stpl.app.parttwo.exception.NoSuchCustomerGtsForecastException;
import com.stpl.app.parttwo.model.CustomerGtsForecast;
import com.stpl.app.parttwo.model.impl.CustomerGtsForecastImpl;
import com.stpl.app.parttwo.model.impl.CustomerGtsForecastModelImpl;
import com.stpl.app.parttwo.service.persistence.CustomerGtsForecastPersistence;

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
 * The persistence implementation for the customer gts forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomerGtsForecastPersistence
 * @see com.stpl.app.parttwo.service.persistence.CustomerGtsForecastUtil
 * @generated
 */
@ProviderType
public class CustomerGtsForecastPersistenceImpl extends BasePersistenceImpl<CustomerGtsForecast>
	implements CustomerGtsForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CustomerGtsForecastUtil} to access the customer gts forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CustomerGtsForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			CustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED,
			CustomerGtsForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CustomerGtsForecastPersistenceImpl() {
		setModelClass(CustomerGtsForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("deductionAmount", "DEDUCTION_AMOUNT");
			dbColumnNames.put("deductionId", "DEDUCTION_ID");
			dbColumnNames.put("forecastDate", "FORECAST_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("salesAmount", "SALES_AMOUNT");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("deductionRate", "DEDUCTION_RATE");
			dbColumnNames.put("customerGtsForecastSid",
				"CUSTOMER_GTS_FORECAST_SID");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyIdString", "COMPANY_ID");
			dbColumnNames.put("forecastValueType", "FORECAST_VALUE_TYPE");
			dbColumnNames.put("deductionCategory", "DEDUCTION_CATEGORY");
			dbColumnNames.put("adjustmentCode", "ADJUSTMENT_CODE");
			dbColumnNames.put("deductionProgramType", "DEDUCTION_PROGRAM_TYPE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
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
	 * Caches the customer gts forecast in the entity cache if it is enabled.
	 *
	 * @param customerGtsForecast the customer gts forecast
	 */
	@Override
	public void cacheResult(CustomerGtsForecast customerGtsForecast) {
		entityCache.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey(),
			customerGtsForecast);

		customerGtsForecast.resetOriginalValues();
	}

	/**
	 * Caches the customer gts forecasts in the entity cache if it is enabled.
	 *
	 * @param customerGtsForecasts the customer gts forecasts
	 */
	@Override
	public void cacheResult(List<CustomerGtsForecast> customerGtsForecasts) {
		for (CustomerGtsForecast customerGtsForecast : customerGtsForecasts) {
			if (entityCache.getResult(
						CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						CustomerGtsForecastImpl.class,
						customerGtsForecast.getPrimaryKey()) == null) {
				cacheResult(customerGtsForecast);
			}
			else {
				customerGtsForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all customer gts forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CustomerGtsForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the customer gts forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CustomerGtsForecast customerGtsForecast) {
		entityCache.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CustomerGtsForecast> customerGtsForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CustomerGtsForecast customerGtsForecast : customerGtsForecasts) {
			entityCache.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				CustomerGtsForecastImpl.class,
				customerGtsForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new customer gts forecast with the primary key. Does not add the customer gts forecast to the database.
	 *
	 * @param customerGtsForecastSid the primary key for the new customer gts forecast
	 * @return the new customer gts forecast
	 */
	@Override
	public CustomerGtsForecast create(int customerGtsForecastSid) {
		CustomerGtsForecast customerGtsForecast = new CustomerGtsForecastImpl();

		customerGtsForecast.setNew(true);
		customerGtsForecast.setPrimaryKey(customerGtsForecastSid);

		return customerGtsForecast;
	}

	/**
	 * Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param customerGtsForecastSid the primary key of the customer gts forecast
	 * @return the customer gts forecast that was removed
	 * @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast remove(int customerGtsForecastSid)
		throws NoSuchCustomerGtsForecastException {
		return remove((Serializable)customerGtsForecastSid);
	}

	/**
	 * Removes the customer gts forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the customer gts forecast
	 * @return the customer gts forecast that was removed
	 * @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast remove(Serializable primaryKey)
		throws NoSuchCustomerGtsForecastException {
		Session session = null;

		try {
			session = openSession();

			CustomerGtsForecast customerGtsForecast = (CustomerGtsForecast)session.get(CustomerGtsForecastImpl.class,
					primaryKey);

			if (customerGtsForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(customerGtsForecast);
		}
		catch (NoSuchCustomerGtsForecastException nsee) {
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
	protected CustomerGtsForecast removeImpl(
		CustomerGtsForecast customerGtsForecast) {
		customerGtsForecast = toUnwrappedModel(customerGtsForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(customerGtsForecast)) {
				customerGtsForecast = (CustomerGtsForecast)session.get(CustomerGtsForecastImpl.class,
						customerGtsForecast.getPrimaryKeyObj());
			}

			if (customerGtsForecast != null) {
				session.delete(customerGtsForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (customerGtsForecast != null) {
			clearCache(customerGtsForecast);
		}

		return customerGtsForecast;
	}

	@Override
	public CustomerGtsForecast updateImpl(
		CustomerGtsForecast customerGtsForecast) {
		customerGtsForecast = toUnwrappedModel(customerGtsForecast);

		boolean isNew = customerGtsForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (customerGtsForecast.isNew()) {
				session.save(customerGtsForecast);

				customerGtsForecast.setNew(false);
			}
			else {
				customerGtsForecast = (CustomerGtsForecast)session.merge(customerGtsForecast);
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

		entityCache.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
			CustomerGtsForecastImpl.class, customerGtsForecast.getPrimaryKey(),
			customerGtsForecast, false);

		customerGtsForecast.resetOriginalValues();

		return customerGtsForecast;
	}

	protected CustomerGtsForecast toUnwrappedModel(
		CustomerGtsForecast customerGtsForecast) {
		if (customerGtsForecast instanceof CustomerGtsForecastImpl) {
			return customerGtsForecast;
		}

		CustomerGtsForecastImpl customerGtsForecastImpl = new CustomerGtsForecastImpl();

		customerGtsForecastImpl.setNew(customerGtsForecast.isNew());
		customerGtsForecastImpl.setPrimaryKey(customerGtsForecast.getPrimaryKey());

		customerGtsForecastImpl.setPrice(customerGtsForecast.getPrice());
		customerGtsForecastImpl.setItemMasterSid(customerGtsForecast.getItemMasterSid());
		customerGtsForecastImpl.setForecastYear(customerGtsForecast.getForecastYear());
		customerGtsForecastImpl.setDeductionAmount(customerGtsForecast.getDeductionAmount());
		customerGtsForecastImpl.setDeductionId(customerGtsForecast.getDeductionId());
		customerGtsForecastImpl.setForecastDate(customerGtsForecast.getForecastDate());
		customerGtsForecastImpl.setItemId(customerGtsForecast.getItemId());
		customerGtsForecastImpl.setModifiedDate(customerGtsForecast.getModifiedDate());
		customerGtsForecastImpl.setBrandMasterSid(customerGtsForecast.getBrandMasterSid());
		customerGtsForecastImpl.setSource(customerGtsForecast.getSource());
		customerGtsForecastImpl.setCreatedDate(customerGtsForecast.getCreatedDate());
		customerGtsForecastImpl.setCreatedBy(customerGtsForecast.getCreatedBy());
		customerGtsForecastImpl.setAddChgDelIndicator(customerGtsForecast.getAddChgDelIndicator());
		customerGtsForecastImpl.setInboundStatus(customerGtsForecast.getInboundStatus());
		customerGtsForecastImpl.setModifiedBy(customerGtsForecast.getModifiedBy());
		customerGtsForecastImpl.setSalesAmount(customerGtsForecast.getSalesAmount());
		customerGtsForecastImpl.setDeductionType(customerGtsForecast.getDeductionType());
		customerGtsForecastImpl.setCompanyMasterSid(customerGtsForecast.getCompanyMasterSid());
		customerGtsForecastImpl.setUnits(customerGtsForecast.getUnits());
		customerGtsForecastImpl.setDeductionRate(customerGtsForecast.getDeductionRate());
		customerGtsForecastImpl.setCustomerGtsForecastSid(customerGtsForecast.getCustomerGtsForecastSid());
		customerGtsForecastImpl.setCountry(customerGtsForecast.getCountry());
		customerGtsForecastImpl.setCompanyIdString(customerGtsForecast.getCompanyIdString());
		customerGtsForecastImpl.setForecastValueType(customerGtsForecast.getForecastValueType());
		customerGtsForecastImpl.setDeductionCategory(customerGtsForecast.getDeductionCategory());
		customerGtsForecastImpl.setAdjustmentCode(customerGtsForecast.getAdjustmentCode());
		customerGtsForecastImpl.setDeductionProgramType(customerGtsForecast.getDeductionProgramType());
		customerGtsForecastImpl.setRecordLockStatus(customerGtsForecast.isRecordLockStatus());
		customerGtsForecastImpl.setSalesInclusion(customerGtsForecast.getSalesInclusion());
		customerGtsForecastImpl.setForecastVer(customerGtsForecast.getForecastVer());
		customerGtsForecastImpl.setBatchId(customerGtsForecast.getBatchId());
		customerGtsForecastImpl.setPriceType(customerGtsForecast.getPriceType());
		customerGtsForecastImpl.setForecastMonth(customerGtsForecast.getForecastMonth());
		customerGtsForecastImpl.setDeductionInclusion(customerGtsForecast.getDeductionInclusion());
		customerGtsForecastImpl.setSegment(customerGtsForecast.getSegment());
		customerGtsForecastImpl.setBrand(customerGtsForecast.getBrand());
		customerGtsForecastImpl.setForecastName(customerGtsForecast.getForecastName());

		return customerGtsForecastImpl;
	}

	/**
	 * Returns the customer gts forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the customer gts forecast
	 * @return the customer gts forecast
	 * @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCustomerGtsForecastException {
		CustomerGtsForecast customerGtsForecast = fetchByPrimaryKey(primaryKey);

		if (customerGtsForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCustomerGtsForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return customerGtsForecast;
	}

	/**
	 * Returns the customer gts forecast with the primary key or throws a {@link NoSuchCustomerGtsForecastException} if it could not be found.
	 *
	 * @param customerGtsForecastSid the primary key of the customer gts forecast
	 * @return the customer gts forecast
	 * @throws NoSuchCustomerGtsForecastException if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast findByPrimaryKey(int customerGtsForecastSid)
		throws NoSuchCustomerGtsForecastException {
		return findByPrimaryKey((Serializable)customerGtsForecastSid);
	}

	/**
	 * Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the customer gts forecast
	 * @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
				CustomerGtsForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CustomerGtsForecast customerGtsForecast = (CustomerGtsForecast)serializable;

		if (customerGtsForecast == null) {
			Session session = null;

			try {
				session = openSession();

				customerGtsForecast = (CustomerGtsForecast)session.get(CustomerGtsForecastImpl.class,
						primaryKey);

				if (customerGtsForecast != null) {
					cacheResult(customerGtsForecast);
				}
				else {
					entityCache.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
						CustomerGtsForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return customerGtsForecast;
	}

	/**
	 * Returns the customer gts forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param customerGtsForecastSid the primary key of the customer gts forecast
	 * @return the customer gts forecast, or <code>null</code> if a customer gts forecast with the primary key could not be found
	 */
	@Override
	public CustomerGtsForecast fetchByPrimaryKey(int customerGtsForecastSid) {
		return fetchByPrimaryKey((Serializable)customerGtsForecastSid);
	}

	@Override
	public Map<Serializable, CustomerGtsForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CustomerGtsForecast> map = new HashMap<Serializable, CustomerGtsForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CustomerGtsForecast customerGtsForecast = fetchByPrimaryKey(primaryKey);

			if (customerGtsForecast != null) {
				map.put(primaryKey, customerGtsForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CustomerGtsForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CUSTOMERGTSFORECAST_WHERE_PKS_IN);

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

			for (CustomerGtsForecast customerGtsForecast : (List<CustomerGtsForecast>)q.list()) {
				map.put(customerGtsForecast.getPrimaryKeyObj(),
					customerGtsForecast);

				cacheResult(customerGtsForecast);

				uncachedPrimaryKeys.remove(customerGtsForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CustomerGtsForecastModelImpl.ENTITY_CACHE_ENABLED,
					CustomerGtsForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the customer gts forecasts.
	 *
	 * @return the customer gts forecasts
	 */
	@Override
	public List<CustomerGtsForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts forecasts
	 * @param end the upper bound of the range of customer gts forecasts (not inclusive)
	 * @return the range of customer gts forecasts
	 */
	@Override
	public List<CustomerGtsForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts forecasts
	 * @param end the upper bound of the range of customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of customer gts forecasts
	 */
	@Override
	public List<CustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<CustomerGtsForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the customer gts forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CustomerGtsForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of customer gts forecasts
	 * @param end the upper bound of the range of customer gts forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of customer gts forecasts
	 */
	@Override
	public List<CustomerGtsForecast> findAll(int start, int end,
		OrderByComparator<CustomerGtsForecast> orderByComparator,
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

		List<CustomerGtsForecast> list = null;

		if (retrieveFromCache) {
			list = (List<CustomerGtsForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CUSTOMERGTSFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CUSTOMERGTSFORECAST;

				if (pagination) {
					sql = sql.concat(CustomerGtsForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CustomerGtsForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CustomerGtsForecast>)QueryUtil.list(q,
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
	 * Removes all the customer gts forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CustomerGtsForecast customerGtsForecast : findAll()) {
			remove(customerGtsForecast);
		}
	}

	/**
	 * Returns the number of customer gts forecasts.
	 *
	 * @return the number of customer gts forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CUSTOMERGTSFORECAST);

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
		return CustomerGtsForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the customer gts forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CustomerGtsForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CUSTOMERGTSFORECAST = "SELECT customerGtsForecast FROM CustomerGtsForecast customerGtsForecast";
	private static final String _SQL_SELECT_CUSTOMERGTSFORECAST_WHERE_PKS_IN = "SELECT customerGtsForecast FROM CustomerGtsForecast customerGtsForecast WHERE CUSTOMER_GTS_FORECAST_SID IN (";
	private static final String _SQL_COUNT_CUSTOMERGTSFORECAST = "SELECT COUNT(customerGtsForecast) FROM CustomerGtsForecast customerGtsForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "customerGtsForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CustomerGtsForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CustomerGtsForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "itemMasterSid", "forecastYear", "deductionAmount",
				"deductionId", "forecastDate", "itemId", "modifiedDate",
				"brandMasterSid", "source", "createdDate", "createdBy",
				"addChgDelIndicator", "inboundStatus", "modifiedBy",
				"salesAmount", "deductionType", "companyMasterSid", "units",
				"deductionRate", "customerGtsForecastSid", "country",
				"companyIdString", "forecastValueType", "deductionCategory",
				"adjustmentCode", "deductionProgramType", "recordLockStatus",
				"salesInclusion", "forecastVer", "batchId", "priceType",
				"forecastMonth", "deductionInclusion", "segment", "brand",
				"forecastName"
			});
}