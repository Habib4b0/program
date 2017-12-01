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

import com.stpl.app.exception.NoSuchIvldForecastSalesException;
import com.stpl.app.model.IvldForecastSales;
import com.stpl.app.model.impl.IvldForecastSalesImpl;
import com.stpl.app.model.impl.IvldForecastSalesModelImpl;
import com.stpl.app.service.persistence.IvldForecastSalesPersistence;

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
 * The persistence implementation for the ivld forecast sales service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldForecastSalesPersistence
 * @see com.stpl.app.service.persistence.IvldForecastSalesUtil
 * @generated
 */
@ProviderType
public class IvldForecastSalesPersistenceImpl extends BasePersistenceImpl<IvldForecastSales>
	implements IvldForecastSalesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldForecastSalesUtil} to access the ivld forecast sales persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldForecastSalesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED,
			IvldForecastSalesImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED,
			IvldForecastSalesImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldForecastSalesPersistenceImpl() {
		setModelClass(IvldForecastSales.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("forecastDate", "FORECAST_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("forecastValue", "FORECAST_VALUE");
			dbColumnNames.put("forecastIntfid", "FORECAST_INTFID");
			dbColumnNames.put("dollars", "DOLLARS");
			dbColumnNames.put("ndc", "NDC");
			dbColumnNames.put("actualSalesPercentage", "ACTUAL_SALES_PERCENTAGE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("actualSalesPercentageMonth",
				"ACTUAL_SALES_PERCENTAGE_MONTH");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("percentageEstimate", "PERCENTAGE_ESTIMATE");
			dbColumnNames.put("percentageEstimateYear",
				"PERCENTAGE_ESTIMATE_YEAR");
			dbColumnNames.put("units", "UNITS");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("forecastStartDate", "FORECAST_START_DATE");
			dbColumnNames.put("forecastValueType", "FORECAST_VALUE_TYPE");
			dbColumnNames.put("forecastedSalesPercentMonth",
				"FORECASTED_SALES_PERCENT_MONTH");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("product", "PRODUCT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("ivldForecastSalesSid", "IVLD_FORECAST_SALES_SID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("forecastedSalesPercentage",
				"FORECASTED_SALES_PERCENTAGE");
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
	 * Caches the ivld forecast sales in the entity cache if it is enabled.
	 *
	 * @param ivldForecastSales the ivld forecast sales
	 */
	@Override
	public void cacheResult(IvldForecastSales ivldForecastSales) {
		entityCache.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey(),
			ivldForecastSales);

		ivldForecastSales.resetOriginalValues();
	}

	/**
	 * Caches the ivld forecast saleses in the entity cache if it is enabled.
	 *
	 * @param ivldForecastSaleses the ivld forecast saleses
	 */
	@Override
	public void cacheResult(List<IvldForecastSales> ivldForecastSaleses) {
		for (IvldForecastSales ivldForecastSales : ivldForecastSaleses) {
			if (entityCache.getResult(
						IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
						IvldForecastSalesImpl.class,
						ivldForecastSales.getPrimaryKey()) == null) {
				cacheResult(ivldForecastSales);
			}
			else {
				ivldForecastSales.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld forecast saleses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldForecastSalesImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld forecast sales.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldForecastSales ivldForecastSales) {
		entityCache.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldForecastSales> ivldForecastSaleses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldForecastSales ivldForecastSales : ivldForecastSaleses) {
			entityCache.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
				IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld forecast sales with the primary key. Does not add the ivld forecast sales to the database.
	 *
	 * @param ivldForecastSalesSid the primary key for the new ivld forecast sales
	 * @return the new ivld forecast sales
	 */
	@Override
	public IvldForecastSales create(int ivldForecastSalesSid) {
		IvldForecastSales ivldForecastSales = new IvldForecastSalesImpl();

		ivldForecastSales.setNew(true);
		ivldForecastSales.setPrimaryKey(ivldForecastSalesSid);

		return ivldForecastSales;
	}

	/**
	 * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldForecastSalesSid the primary key of the ivld forecast sales
	 * @return the ivld forecast sales that was removed
	 * @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales remove(int ivldForecastSalesSid)
		throws NoSuchIvldForecastSalesException {
		return remove((Serializable)ivldForecastSalesSid);
	}

	/**
	 * Removes the ivld forecast sales with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld forecast sales
	 * @return the ivld forecast sales that was removed
	 * @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales remove(Serializable primaryKey)
		throws NoSuchIvldForecastSalesException {
		Session session = null;

		try {
			session = openSession();

			IvldForecastSales ivldForecastSales = (IvldForecastSales)session.get(IvldForecastSalesImpl.class,
					primaryKey);

			if (ivldForecastSales == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldForecastSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldForecastSales);
		}
		catch (NoSuchIvldForecastSalesException nsee) {
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
	protected IvldForecastSales removeImpl(IvldForecastSales ivldForecastSales) {
		ivldForecastSales = toUnwrappedModel(ivldForecastSales);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldForecastSales)) {
				ivldForecastSales = (IvldForecastSales)session.get(IvldForecastSalesImpl.class,
						ivldForecastSales.getPrimaryKeyObj());
			}

			if (ivldForecastSales != null) {
				session.delete(ivldForecastSales);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldForecastSales != null) {
			clearCache(ivldForecastSales);
		}

		return ivldForecastSales;
	}

	@Override
	public IvldForecastSales updateImpl(IvldForecastSales ivldForecastSales) {
		ivldForecastSales = toUnwrappedModel(ivldForecastSales);

		boolean isNew = ivldForecastSales.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldForecastSales.isNew()) {
				session.save(ivldForecastSales);

				ivldForecastSales.setNew(false);
			}
			else {
				ivldForecastSales = (IvldForecastSales)session.merge(ivldForecastSales);
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

		entityCache.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
			IvldForecastSalesImpl.class, ivldForecastSales.getPrimaryKey(),
			ivldForecastSales, false);

		ivldForecastSales.resetOriginalValues();

		return ivldForecastSales;
	}

	protected IvldForecastSales toUnwrappedModel(
		IvldForecastSales ivldForecastSales) {
		if (ivldForecastSales instanceof IvldForecastSalesImpl) {
			return ivldForecastSales;
		}

		IvldForecastSalesImpl ivldForecastSalesImpl = new IvldForecastSalesImpl();

		ivldForecastSalesImpl.setNew(ivldForecastSales.isNew());
		ivldForecastSalesImpl.setPrimaryKey(ivldForecastSales.getPrimaryKey());

		ivldForecastSalesImpl.setPrice(ivldForecastSales.getPrice());
		ivldForecastSalesImpl.setForecastYear(ivldForecastSales.getForecastYear());
		ivldForecastSalesImpl.setForecastDate(ivldForecastSales.getForecastDate());
		ivldForecastSalesImpl.setModifiedDate(ivldForecastSales.getModifiedDate());
		ivldForecastSalesImpl.setForecastValue(ivldForecastSales.getForecastValue());
		ivldForecastSalesImpl.setForecastIntfid(ivldForecastSales.getForecastIntfid());
		ivldForecastSalesImpl.setDollars(ivldForecastSales.getDollars());
		ivldForecastSalesImpl.setNdc(ivldForecastSales.getNdc());
		ivldForecastSalesImpl.setActualSalesPercentage(ivldForecastSales.getActualSalesPercentage());
		ivldForecastSalesImpl.setSource(ivldForecastSales.getSource());
		ivldForecastSalesImpl.setCreatedDate(ivldForecastSales.getCreatedDate());
		ivldForecastSalesImpl.setCreatedBy(ivldForecastSales.getCreatedBy());
		ivldForecastSalesImpl.setAddChgDelIndicator(ivldForecastSales.getAddChgDelIndicator());
		ivldForecastSalesImpl.setActualSalesPercentageMonth(ivldForecastSales.getActualSalesPercentageMonth());
		ivldForecastSalesImpl.setErrorCode(ivldForecastSales.getErrorCode());
		ivldForecastSalesImpl.setIntfInsertedDate(ivldForecastSales.getIntfInsertedDate());
		ivldForecastSalesImpl.setModifiedBy(ivldForecastSales.getModifiedBy());
		ivldForecastSalesImpl.setReprocessedFlag(ivldForecastSales.getReprocessedFlag());
		ivldForecastSalesImpl.setPercentageEstimate(ivldForecastSales.getPercentageEstimate());
		ivldForecastSalesImpl.setPercentageEstimateYear(ivldForecastSales.getPercentageEstimateYear());
		ivldForecastSalesImpl.setUnits(ivldForecastSales.getUnits());
		ivldForecastSalesImpl.setReasonForFailure(ivldForecastSales.getReasonForFailure());
		ivldForecastSalesImpl.setForecastStartDate(ivldForecastSales.getForecastStartDate());
		ivldForecastSalesImpl.setForecastValueType(ivldForecastSales.getForecastValueType());
		ivldForecastSalesImpl.setForecastedSalesPercentMonth(ivldForecastSales.getForecastedSalesPercentMonth());
		ivldForecastSalesImpl.setCountry(ivldForecastSales.getCountry());
		ivldForecastSalesImpl.setProduct(ivldForecastSales.getProduct());
		ivldForecastSalesImpl.setBatchId(ivldForecastSales.getBatchId());
		ivldForecastSalesImpl.setForecastVer(ivldForecastSales.getForecastVer());
		ivldForecastSalesImpl.setForecastMonth(ivldForecastSales.getForecastMonth());
		ivldForecastSalesImpl.setIvldForecastSalesSid(ivldForecastSales.getIvldForecastSalesSid());
		ivldForecastSalesImpl.setErrorField(ivldForecastSales.getErrorField());
		ivldForecastSalesImpl.setSegment(ivldForecastSales.getSegment());
		ivldForecastSalesImpl.setBrand(ivldForecastSales.getBrand());
		ivldForecastSalesImpl.setForecastedSalesPercentage(ivldForecastSales.getForecastedSalesPercentage());
		ivldForecastSalesImpl.setForecastName(ivldForecastSales.getForecastName());
		ivldForecastSalesImpl.setCheckRecord(ivldForecastSales.isCheckRecord());

		return ivldForecastSalesImpl;
	}

	/**
	 * Returns the ivld forecast sales with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld forecast sales
	 * @return the ivld forecast sales
	 * @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldForecastSalesException {
		IvldForecastSales ivldForecastSales = fetchByPrimaryKey(primaryKey);

		if (ivldForecastSales == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldForecastSalesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldForecastSales;
	}

	/**
	 * Returns the ivld forecast sales with the primary key or throws a {@link NoSuchIvldForecastSalesException} if it could not be found.
	 *
	 * @param ivldForecastSalesSid the primary key of the ivld forecast sales
	 * @return the ivld forecast sales
	 * @throws NoSuchIvldForecastSalesException if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales findByPrimaryKey(int ivldForecastSalesSid)
		throws NoSuchIvldForecastSalesException {
		return findByPrimaryKey((Serializable)ivldForecastSalesSid);
	}

	/**
	 * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld forecast sales
	 * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
				IvldForecastSalesImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldForecastSales ivldForecastSales = (IvldForecastSales)serializable;

		if (ivldForecastSales == null) {
			Session session = null;

			try {
				session = openSession();

				ivldForecastSales = (IvldForecastSales)session.get(IvldForecastSalesImpl.class,
						primaryKey);

				if (ivldForecastSales != null) {
					cacheResult(ivldForecastSales);
				}
				else {
					entityCache.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
						IvldForecastSalesImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
					IvldForecastSalesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldForecastSales;
	}

	/**
	 * Returns the ivld forecast sales with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldForecastSalesSid the primary key of the ivld forecast sales
	 * @return the ivld forecast sales, or <code>null</code> if a ivld forecast sales with the primary key could not be found
	 */
	@Override
	public IvldForecastSales fetchByPrimaryKey(int ivldForecastSalesSid) {
		return fetchByPrimaryKey((Serializable)ivldForecastSalesSid);
	}

	@Override
	public Map<Serializable, IvldForecastSales> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldForecastSales> map = new HashMap<Serializable, IvldForecastSales>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldForecastSales ivldForecastSales = fetchByPrimaryKey(primaryKey);

			if (ivldForecastSales != null) {
				map.put(primaryKey, ivldForecastSales);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
					IvldForecastSalesImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldForecastSales)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDFORECASTSALES_WHERE_PKS_IN);

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

			for (IvldForecastSales ivldForecastSales : (List<IvldForecastSales>)q.list()) {
				map.put(ivldForecastSales.getPrimaryKeyObj(), ivldForecastSales);

				cacheResult(ivldForecastSales);

				uncachedPrimaryKeys.remove(ivldForecastSales.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldForecastSalesModelImpl.ENTITY_CACHE_ENABLED,
					IvldForecastSalesImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld forecast saleses.
	 *
	 * @return the ivld forecast saleses
	 */
	@Override
	public List<IvldForecastSales> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld forecast saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld forecast saleses
	 * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	 * @return the range of ivld forecast saleses
	 */
	@Override
	public List<IvldForecastSales> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld forecast saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld forecast saleses
	 * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld forecast saleses
	 */
	@Override
	public List<IvldForecastSales> findAll(int start, int end,
		OrderByComparator<IvldForecastSales> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld forecast saleses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldForecastSalesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld forecast saleses
	 * @param end the upper bound of the range of ivld forecast saleses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld forecast saleses
	 */
	@Override
	public List<IvldForecastSales> findAll(int start, int end,
		OrderByComparator<IvldForecastSales> orderByComparator,
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

		List<IvldForecastSales> list = null;

		if (retrieveFromCache) {
			list = (List<IvldForecastSales>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDFORECASTSALES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDFORECASTSALES;

				if (pagination) {
					sql = sql.concat(IvldForecastSalesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldForecastSales>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldForecastSales>)QueryUtil.list(q,
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
	 * Removes all the ivld forecast saleses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldForecastSales ivldForecastSales : findAll()) {
			remove(ivldForecastSales);
		}
	}

	/**
	 * Returns the number of ivld forecast saleses.
	 *
	 * @return the number of ivld forecast saleses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDFORECASTSALES);

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
		return IvldForecastSalesModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld forecast sales persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldForecastSalesImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDFORECASTSALES = "SELECT ivldForecastSales FROM IvldForecastSales ivldForecastSales";
	private static final String _SQL_SELECT_IVLDFORECASTSALES_WHERE_PKS_IN = "SELECT ivldForecastSales FROM IvldForecastSales ivldForecastSales WHERE IVLD_FORECAST_SALES_SID IN (";
	private static final String _SQL_COUNT_IVLDFORECASTSALES = "SELECT COUNT(ivldForecastSales) FROM IvldForecastSales ivldForecastSales";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldForecastSales.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldForecastSales exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldForecastSalesPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "forecastYear", "forecastDate", "modifiedDate",
				"forecastValue", "forecastIntfid", "dollars", "ndc",
				"actualSalesPercentage", "source", "createdDate", "createdBy",
				"addChgDelIndicator", "actualSalesPercentageMonth", "errorCode",
				"intfInsertedDate", "modifiedBy", "reprocessedFlag",
				"percentageEstimate", "percentageEstimateYear", "units",
				"reasonForFailure", "forecastStartDate", "forecastValueType",
				"forecastedSalesPercentMonth", "country", "product", "batchId",
				"forecastVer", "forecastMonth", "ivldForecastSalesSid",
				"errorField", "segment", "brand", "forecastedSalesPercentage",
				"forecastName", "checkRecord"
			});
}