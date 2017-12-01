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

import com.stpl.app.exception.NoSuchIvldDemandForecastException;
import com.stpl.app.model.IvldDemandForecast;
import com.stpl.app.model.impl.IvldDemandForecastImpl;
import com.stpl.app.model.impl.IvldDemandForecastModelImpl;
import com.stpl.app.service.persistence.IvldDemandForecastPersistence;

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
 * The persistence implementation for the ivld demand forecast service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandForecastPersistence
 * @see com.stpl.app.service.persistence.IvldDemandForecastUtil
 * @generated
 */
@ProviderType
public class IvldDemandForecastPersistenceImpl extends BasePersistenceImpl<IvldDemandForecast>
	implements IvldDemandForecastPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldDemandForecastUtil} to access the ivld demand forecast persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldDemandForecastImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED,
			IvldDemandForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED,
			IvldDemandForecastImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldDemandForecastPersistenceImpl() {
		setModelClass(IvldDemandForecast.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastYear", "FORECAST_YEAR");
			dbColumnNames.put("grossUnits", "GROSS_UNITS");
			dbColumnNames.put("totalDemandUnits", "TOTAL_DEMAND_UNITS");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("ivldDemandForecastSid",
				"IVLD_DEMAND_FORECAST_SID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("inventoryUnitChange", "INVENTORY_UNIT_CHANGE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("uncapturedUnitsRatio", "UNCAPTURED_UNITS_RATIO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("demandForecastInterfaceId",
				"DEMAND_FORECAST_INTERFACE_ID");
			dbColumnNames.put("uncapturedUnits", "UNCAPTURED_UNITS");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
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
	 * Caches the ivld demand forecast in the entity cache if it is enabled.
	 *
	 * @param ivldDemandForecast the ivld demand forecast
	 */
	@Override
	public void cacheResult(IvldDemandForecast ivldDemandForecast) {
		entityCache.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey(),
			ivldDemandForecast);

		ivldDemandForecast.resetOriginalValues();
	}

	/**
	 * Caches the ivld demand forecasts in the entity cache if it is enabled.
	 *
	 * @param ivldDemandForecasts the ivld demand forecasts
	 */
	@Override
	public void cacheResult(List<IvldDemandForecast> ivldDemandForecasts) {
		for (IvldDemandForecast ivldDemandForecast : ivldDemandForecasts) {
			if (entityCache.getResult(
						IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						IvldDemandForecastImpl.class,
						ivldDemandForecast.getPrimaryKey()) == null) {
				cacheResult(ivldDemandForecast);
			}
			else {
				ivldDemandForecast.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld demand forecasts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldDemandForecastImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld demand forecast.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldDemandForecast ivldDemandForecast) {
		entityCache.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldDemandForecast> ivldDemandForecasts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldDemandForecast ivldDemandForecast : ivldDemandForecasts) {
			entityCache.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld demand forecast with the primary key. Does not add the ivld demand forecast to the database.
	 *
	 * @param ivldDemandForecastSid the primary key for the new ivld demand forecast
	 * @return the new ivld demand forecast
	 */
	@Override
	public IvldDemandForecast create(int ivldDemandForecastSid) {
		IvldDemandForecast ivldDemandForecast = new IvldDemandForecastImpl();

		ivldDemandForecast.setNew(true);
		ivldDemandForecast.setPrimaryKey(ivldDemandForecastSid);

		return ivldDemandForecast;
	}

	/**
	 * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldDemandForecastSid the primary key of the ivld demand forecast
	 * @return the ivld demand forecast that was removed
	 * @throws NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast remove(int ivldDemandForecastSid)
		throws NoSuchIvldDemandForecastException {
		return remove((Serializable)ivldDemandForecastSid);
	}

	/**
	 * Removes the ivld demand forecast with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld demand forecast
	 * @return the ivld demand forecast that was removed
	 * @throws NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast remove(Serializable primaryKey)
		throws NoSuchIvldDemandForecastException {
		Session session = null;

		try {
			session = openSession();

			IvldDemandForecast ivldDemandForecast = (IvldDemandForecast)session.get(IvldDemandForecastImpl.class,
					primaryKey);

			if (ivldDemandForecast == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldDemandForecast);
		}
		catch (NoSuchIvldDemandForecastException nsee) {
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
	protected IvldDemandForecast removeImpl(
		IvldDemandForecast ivldDemandForecast) {
		ivldDemandForecast = toUnwrappedModel(ivldDemandForecast);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldDemandForecast)) {
				ivldDemandForecast = (IvldDemandForecast)session.get(IvldDemandForecastImpl.class,
						ivldDemandForecast.getPrimaryKeyObj());
			}

			if (ivldDemandForecast != null) {
				session.delete(ivldDemandForecast);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldDemandForecast != null) {
			clearCache(ivldDemandForecast);
		}

		return ivldDemandForecast;
	}

	@Override
	public IvldDemandForecast updateImpl(IvldDemandForecast ivldDemandForecast) {
		ivldDemandForecast = toUnwrappedModel(ivldDemandForecast);

		boolean isNew = ivldDemandForecast.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldDemandForecast.isNew()) {
				session.save(ivldDemandForecast);

				ivldDemandForecast.setNew(false);
			}
			else {
				ivldDemandForecast = (IvldDemandForecast)session.merge(ivldDemandForecast);
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

		entityCache.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandForecastImpl.class, ivldDemandForecast.getPrimaryKey(),
			ivldDemandForecast, false);

		ivldDemandForecast.resetOriginalValues();

		return ivldDemandForecast;
	}

	protected IvldDemandForecast toUnwrappedModel(
		IvldDemandForecast ivldDemandForecast) {
		if (ivldDemandForecast instanceof IvldDemandForecastImpl) {
			return ivldDemandForecast;
		}

		IvldDemandForecastImpl ivldDemandForecastImpl = new IvldDemandForecastImpl();

		ivldDemandForecastImpl.setNew(ivldDemandForecast.isNew());
		ivldDemandForecastImpl.setPrimaryKey(ivldDemandForecast.getPrimaryKey());

		ivldDemandForecastImpl.setForecastYear(ivldDemandForecast.getForecastYear());
		ivldDemandForecastImpl.setGrossUnits(ivldDemandForecast.getGrossUnits());
		ivldDemandForecastImpl.setTotalDemandUnits(ivldDemandForecast.getTotalDemandUnits());
		ivldDemandForecastImpl.setItemId(ivldDemandForecast.getItemId());
		ivldDemandForecastImpl.setModifiedDate(ivldDemandForecast.getModifiedDate());
		ivldDemandForecastImpl.setOrganizationKey(ivldDemandForecast.getOrganizationKey());
		ivldDemandForecastImpl.setIvldDemandForecastSid(ivldDemandForecast.getIvldDemandForecastSid());
		ivldDemandForecastImpl.setSource(ivldDemandForecast.getSource());
		ivldDemandForecastImpl.setMarketShareRatio(ivldDemandForecast.getMarketShareRatio());
		ivldDemandForecastImpl.setCreatedBy(ivldDemandForecast.getCreatedBy());
		ivldDemandForecastImpl.setCreatedDate(ivldDemandForecast.getCreatedDate());
		ivldDemandForecastImpl.setAddChgDelIndicator(ivldDemandForecast.getAddChgDelIndicator());
		ivldDemandForecastImpl.setItemIdentifier(ivldDemandForecast.getItemIdentifier());
		ivldDemandForecastImpl.setErrorCode(ivldDemandForecast.getErrorCode());
		ivldDemandForecastImpl.setIntfInsertedDate(ivldDemandForecast.getIntfInsertedDate());
		ivldDemandForecastImpl.setModifiedBy(ivldDemandForecast.getModifiedBy());
		ivldDemandForecastImpl.setMarketShareUnits(ivldDemandForecast.getMarketShareUnits());
		ivldDemandForecastImpl.setInventoryUnitChange(ivldDemandForecast.getInventoryUnitChange());
		ivldDemandForecastImpl.setReprocessedFlag(ivldDemandForecast.getReprocessedFlag());
		ivldDemandForecastImpl.setUncapturedUnitsRatio(ivldDemandForecast.getUncapturedUnitsRatio());
		ivldDemandForecastImpl.setReasonForFailure(ivldDemandForecast.getReasonForFailure());
		ivldDemandForecastImpl.setCountry(ivldDemandForecast.getCountry());
		ivldDemandForecastImpl.setForecastType(ivldDemandForecast.getForecastType());
		ivldDemandForecastImpl.setBrandId(ivldDemandForecast.getBrandId());
		ivldDemandForecastImpl.setDemandForecastInterfaceId(ivldDemandForecast.getDemandForecastInterfaceId());
		ivldDemandForecastImpl.setUncapturedUnits(ivldDemandForecast.getUncapturedUnits());
		ivldDemandForecastImpl.setGrossPrice(ivldDemandForecast.getGrossPrice());
		ivldDemandForecastImpl.setGrossAmount(ivldDemandForecast.getGrossAmount());
		ivldDemandForecastImpl.setItemIdentifierCodeQualifier(ivldDemandForecast.getItemIdentifierCodeQualifier());
		ivldDemandForecastImpl.setForecastVer(ivldDemandForecast.getForecastVer());
		ivldDemandForecastImpl.setBatchId(ivldDemandForecast.getBatchId());
		ivldDemandForecastImpl.setForecastMonth(ivldDemandForecast.getForecastMonth());
		ivldDemandForecastImpl.setErrorField(ivldDemandForecast.getErrorField());
		ivldDemandForecastImpl.setNetSalesPrice(ivldDemandForecast.getNetSalesPrice());
		ivldDemandForecastImpl.setNetSalesAmount(ivldDemandForecast.getNetSalesAmount());
		ivldDemandForecastImpl.setSegment(ivldDemandForecast.getSegment());
		ivldDemandForecastImpl.setTotalDemandAmount(ivldDemandForecast.getTotalDemandAmount());
		ivldDemandForecastImpl.setForecastName(ivldDemandForecast.getForecastName());
		ivldDemandForecastImpl.setMarketSizeUnits(ivldDemandForecast.getMarketSizeUnits());
		ivldDemandForecastImpl.setCheckRecord(ivldDemandForecast.isCheckRecord());

		return ivldDemandForecastImpl;
	}

	/**
	 * Returns the ivld demand forecast with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld demand forecast
	 * @return the ivld demand forecast
	 * @throws NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldDemandForecastException {
		IvldDemandForecast ivldDemandForecast = fetchByPrimaryKey(primaryKey);

		if (ivldDemandForecast == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldDemandForecastException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldDemandForecast;
	}

	/**
	 * Returns the ivld demand forecast with the primary key or throws a {@link NoSuchIvldDemandForecastException} if it could not be found.
	 *
	 * @param ivldDemandForecastSid the primary key of the ivld demand forecast
	 * @return the ivld demand forecast
	 * @throws NoSuchIvldDemandForecastException if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast findByPrimaryKey(int ivldDemandForecastSid)
		throws NoSuchIvldDemandForecastException {
		return findByPrimaryKey((Serializable)ivldDemandForecastSid);
	}

	/**
	 * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld demand forecast
	 * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
				IvldDemandForecastImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldDemandForecast ivldDemandForecast = (IvldDemandForecast)serializable;

		if (ivldDemandForecast == null) {
			Session session = null;

			try {
				session = openSession();

				ivldDemandForecast = (IvldDemandForecast)session.get(IvldDemandForecastImpl.class,
						primaryKey);

				if (ivldDemandForecast != null) {
					cacheResult(ivldDemandForecast);
				}
				else {
					entityCache.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
						IvldDemandForecastImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandForecastImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldDemandForecast;
	}

	/**
	 * Returns the ivld demand forecast with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldDemandForecastSid the primary key of the ivld demand forecast
	 * @return the ivld demand forecast, or <code>null</code> if a ivld demand forecast with the primary key could not be found
	 */
	@Override
	public IvldDemandForecast fetchByPrimaryKey(int ivldDemandForecastSid) {
		return fetchByPrimaryKey((Serializable)ivldDemandForecastSid);
	}

	@Override
	public Map<Serializable, IvldDemandForecast> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldDemandForecast> map = new HashMap<Serializable, IvldDemandForecast>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldDemandForecast ivldDemandForecast = fetchByPrimaryKey(primaryKey);

			if (ivldDemandForecast != null) {
				map.put(primaryKey, ivldDemandForecast);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandForecastImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldDemandForecast)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDDEMANDFORECAST_WHERE_PKS_IN);

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

			for (IvldDemandForecast ivldDemandForecast : (List<IvldDemandForecast>)q.list()) {
				map.put(ivldDemandForecast.getPrimaryKeyObj(),
					ivldDemandForecast);

				cacheResult(ivldDemandForecast);

				uncachedPrimaryKeys.remove(ivldDemandForecast.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldDemandForecastModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandForecastImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld demand forecasts.
	 *
	 * @return the ivld demand forecasts
	 */
	@Override
	public List<IvldDemandForecast> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand forecasts
	 * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
	 * @return the range of ivld demand forecasts
	 */
	@Override
	public List<IvldDemandForecast> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand forecasts
	 * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld demand forecasts
	 */
	@Override
	public List<IvldDemandForecast> findAll(int start, int end,
		OrderByComparator<IvldDemandForecast> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld demand forecasts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandForecastModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand forecasts
	 * @param end the upper bound of the range of ivld demand forecasts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld demand forecasts
	 */
	@Override
	public List<IvldDemandForecast> findAll(int start, int end,
		OrderByComparator<IvldDemandForecast> orderByComparator,
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

		List<IvldDemandForecast> list = null;

		if (retrieveFromCache) {
			list = (List<IvldDemandForecast>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDDEMANDFORECAST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDDEMANDFORECAST;

				if (pagination) {
					sql = sql.concat(IvldDemandForecastModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldDemandForecast>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldDemandForecast>)QueryUtil.list(q,
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
	 * Removes all the ivld demand forecasts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldDemandForecast ivldDemandForecast : findAll()) {
			remove(ivldDemandForecast);
		}
	}

	/**
	 * Returns the number of ivld demand forecasts.
	 *
	 * @return the number of ivld demand forecasts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDDEMANDFORECAST);

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
		return IvldDemandForecastModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld demand forecast persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldDemandForecastImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDDEMANDFORECAST = "SELECT ivldDemandForecast FROM IvldDemandForecast ivldDemandForecast";
	private static final String _SQL_SELECT_IVLDDEMANDFORECAST_WHERE_PKS_IN = "SELECT ivldDemandForecast FROM IvldDemandForecast ivldDemandForecast WHERE IVLD_DEMAND_FORECAST_SID IN (";
	private static final String _SQL_COUNT_IVLDDEMANDFORECAST = "SELECT COUNT(ivldDemandForecast) FROM IvldDemandForecast ivldDemandForecast";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldDemandForecast.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldDemandForecast exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldDemandForecastPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastYear", "grossUnits", "totalDemandUnits", "itemId",
				"modifiedDate", "organizationKey", "ivldDemandForecastSid",
				"source", "marketShareRatio", "createdBy", "createdDate",
				"addChgDelIndicator", "itemIdentifier", "errorCode",
				"intfInsertedDate", "modifiedBy", "marketShareUnits",
				"inventoryUnitChange", "reprocessedFlag", "uncapturedUnitsRatio",
				"reasonForFailure", "country", "forecastType", "brandId",
				"demandForecastInterfaceId", "uncapturedUnits", "grossPrice",
				"grossAmount", "itemIdentifierCodeQualifier", "forecastVer",
				"batchId", "forecastMonth", "errorField", "netSalesPrice",
				"netSalesAmount", "segment", "totalDemandAmount", "forecastName",
				"marketSizeUnits", "checkRecord"
			});
}