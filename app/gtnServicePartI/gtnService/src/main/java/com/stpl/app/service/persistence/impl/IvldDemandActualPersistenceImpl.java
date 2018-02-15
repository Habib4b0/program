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

import com.stpl.app.exception.NoSuchIvldDemandActualException;
import com.stpl.app.model.IvldDemandActual;
import com.stpl.app.model.impl.IvldDemandActualImpl;
import com.stpl.app.model.impl.IvldDemandActualModelImpl;
import com.stpl.app.service.persistence.IvldDemandActualPersistence;

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
 * The persistence implementation for the ivld demand actual service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldDemandActualPersistence
 * @see com.stpl.app.service.persistence.IvldDemandActualUtil
 * @generated
 */
@ProviderType
public class IvldDemandActualPersistenceImpl extends BasePersistenceImpl<IvldDemandActual>
	implements IvldDemandActualPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldDemandActualUtil} to access the ivld demand actual persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldDemandActualImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualModelImpl.FINDER_CACHE_ENABLED,
			IvldDemandActualImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualModelImpl.FINDER_CACHE_ENABLED,
			IvldDemandActualImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldDemandActualPersistenceImpl() {
		setModelClass(IvldDemandActual.class);

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
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("marketShareRatio", "MARKET_SHARE_RATIO");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("demandActualInterfaceId",
				"DEMAND_ACTUAL_INTERFACE_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("marketShareUnits", "MARKET_SHARE_UNITS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("forecastType", "FORECAST_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("grossPrice", "GROSS_PRICE");
			dbColumnNames.put("ivldDemandActualSid", "IVLD_DEMAND_ACTUAL_SID");
			dbColumnNames.put("grossAmount", "GROSS_AMOUNT");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("forecastMonth", "FORECAST_MONTH");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("netSalesPrice", "NET_SALES_PRICE");
			dbColumnNames.put("netSalesAmount", "NET_SALES_AMOUNT");
			dbColumnNames.put("segment", "SEGMENT");
			dbColumnNames.put("totalDemandAmount", "TOTAL_DEMAND_AMOUNT");
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
	 * Caches the ivld demand actual in the entity cache if it is enabled.
	 *
	 * @param ivldDemandActual the ivld demand actual
	 */
	@Override
	public void cacheResult(IvldDemandActual ivldDemandActual) {
		entityCache.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey(),
			ivldDemandActual);

		ivldDemandActual.resetOriginalValues();
	}

	/**
	 * Caches the ivld demand actuals in the entity cache if it is enabled.
	 *
	 * @param ivldDemandActuals the ivld demand actuals
	 */
	@Override
	public void cacheResult(List<IvldDemandActual> ivldDemandActuals) {
		for (IvldDemandActual ivldDemandActual : ivldDemandActuals) {
			if (entityCache.getResult(
						IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
						IvldDemandActualImpl.class,
						ivldDemandActual.getPrimaryKey()) == null) {
				cacheResult(ivldDemandActual);
			}
			else {
				ivldDemandActual.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld demand actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldDemandActualImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld demand actual.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldDemandActual ivldDemandActual) {
		entityCache.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldDemandActual> ivldDemandActuals) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldDemandActual ivldDemandActual : ivldDemandActuals) {
			entityCache.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
				IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
	 *
	 * @param ivldDemandActualSid the primary key for the new ivld demand actual
	 * @return the new ivld demand actual
	 */
	@Override
	public IvldDemandActual create(int ivldDemandActualSid) {
		IvldDemandActual ivldDemandActual = new IvldDemandActualImpl();

		ivldDemandActual.setNew(true);
		ivldDemandActual.setPrimaryKey(ivldDemandActualSid);

		return ivldDemandActual;
	}

	/**
	 * Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldDemandActualSid the primary key of the ivld demand actual
	 * @return the ivld demand actual that was removed
	 * @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual remove(int ivldDemandActualSid)
		throws NoSuchIvldDemandActualException {
		return remove((Serializable)ivldDemandActualSid);
	}

	/**
	 * Removes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld demand actual
	 * @return the ivld demand actual that was removed
	 * @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual remove(Serializable primaryKey)
		throws NoSuchIvldDemandActualException {
		Session session = null;

		try {
			session = openSession();

			IvldDemandActual ivldDemandActual = (IvldDemandActual)session.get(IvldDemandActualImpl.class,
					primaryKey);

			if (ivldDemandActual == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldDemandActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldDemandActual);
		}
		catch (NoSuchIvldDemandActualException nsee) {
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
	protected IvldDemandActual removeImpl(IvldDemandActual ivldDemandActual) {
		ivldDemandActual = toUnwrappedModel(ivldDemandActual);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldDemandActual)) {
				ivldDemandActual = (IvldDemandActual)session.get(IvldDemandActualImpl.class,
						ivldDemandActual.getPrimaryKeyObj());
			}

			if (ivldDemandActual != null) {
				session.delete(ivldDemandActual);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldDemandActual != null) {
			clearCache(ivldDemandActual);
		}

		return ivldDemandActual;
	}

	@Override
	public IvldDemandActual updateImpl(IvldDemandActual ivldDemandActual) {
		ivldDemandActual = toUnwrappedModel(ivldDemandActual);

		boolean isNew = ivldDemandActual.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldDemandActual.isNew()) {
				session.save(ivldDemandActual);

				ivldDemandActual.setNew(false);
			}
			else {
				ivldDemandActual = (IvldDemandActual)session.merge(ivldDemandActual);
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

		entityCache.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
			IvldDemandActualImpl.class, ivldDemandActual.getPrimaryKey(),
			ivldDemandActual, false);

		ivldDemandActual.resetOriginalValues();

		return ivldDemandActual;
	}

	protected IvldDemandActual toUnwrappedModel(
		IvldDemandActual ivldDemandActual) {
		if (ivldDemandActual instanceof IvldDemandActualImpl) {
			return ivldDemandActual;
		}

		IvldDemandActualImpl ivldDemandActualImpl = new IvldDemandActualImpl();

		ivldDemandActualImpl.setNew(ivldDemandActual.isNew());
		ivldDemandActualImpl.setPrimaryKey(ivldDemandActual.getPrimaryKey());

		ivldDemandActualImpl.setForecastYear(ivldDemandActual.getForecastYear());
		ivldDemandActualImpl.setGrossUnits(ivldDemandActual.getGrossUnits());
		ivldDemandActualImpl.setTotalDemandUnits(ivldDemandActual.getTotalDemandUnits());
		ivldDemandActualImpl.setItemId(ivldDemandActual.getItemId());
		ivldDemandActualImpl.setModifiedDate(ivldDemandActual.getModifiedDate());
		ivldDemandActualImpl.setOrganizationKey(ivldDemandActual.getOrganizationKey());
		ivldDemandActualImpl.setSource(ivldDemandActual.getSource());
		ivldDemandActualImpl.setMarketShareRatio(ivldDemandActual.getMarketShareRatio());
		ivldDemandActualImpl.setCreatedBy(ivldDemandActual.getCreatedBy());
		ivldDemandActualImpl.setCreatedDate(ivldDemandActual.getCreatedDate());
		ivldDemandActualImpl.setDemandActualInterfaceId(ivldDemandActual.getDemandActualInterfaceId());
		ivldDemandActualImpl.setAddChgDelIndicator(ivldDemandActual.getAddChgDelIndicator());
		ivldDemandActualImpl.setItemIdentifier(ivldDemandActual.getItemIdentifier());
		ivldDemandActualImpl.setErrorCode(ivldDemandActual.getErrorCode());
		ivldDemandActualImpl.setIntfInsertedDate(ivldDemandActual.getIntfInsertedDate());
		ivldDemandActualImpl.setMarketShareUnits(ivldDemandActual.getMarketShareUnits());
		ivldDemandActualImpl.setModifiedBy(ivldDemandActual.getModifiedBy());
		ivldDemandActualImpl.setReprocessedFlag(ivldDemandActual.getReprocessedFlag());
		ivldDemandActualImpl.setReasonForFailure(ivldDemandActual.getReasonForFailure());
		ivldDemandActualImpl.setCountry(ivldDemandActual.getCountry());
		ivldDemandActualImpl.setForecastType(ivldDemandActual.getForecastType());
		ivldDemandActualImpl.setBrandId(ivldDemandActual.getBrandId());
		ivldDemandActualImpl.setGrossPrice(ivldDemandActual.getGrossPrice());
		ivldDemandActualImpl.setIvldDemandActualSid(ivldDemandActual.getIvldDemandActualSid());
		ivldDemandActualImpl.setGrossAmount(ivldDemandActual.getGrossAmount());
		ivldDemandActualImpl.setItemIdentifierCodeQualifier(ivldDemandActual.getItemIdentifierCodeQualifier());
		ivldDemandActualImpl.setBatchId(ivldDemandActual.getBatchId());
		ivldDemandActualImpl.setForecastMonth(ivldDemandActual.getForecastMonth());
		ivldDemandActualImpl.setErrorField(ivldDemandActual.getErrorField());
		ivldDemandActualImpl.setNetSalesPrice(ivldDemandActual.getNetSalesPrice());
		ivldDemandActualImpl.setNetSalesAmount(ivldDemandActual.getNetSalesAmount());
		ivldDemandActualImpl.setSegment(ivldDemandActual.getSegment());
		ivldDemandActualImpl.setTotalDemandAmount(ivldDemandActual.getTotalDemandAmount());
		ivldDemandActualImpl.setMarketSizeUnits(ivldDemandActual.getMarketSizeUnits());
		ivldDemandActualImpl.setCheckRecord(ivldDemandActual.isCheckRecord());

		return ivldDemandActualImpl;
	}

	/**
	 * Returns the ivld demand actual with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld demand actual
	 * @return the ivld demand actual
	 * @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldDemandActualException {
		IvldDemandActual ivldDemandActual = fetchByPrimaryKey(primaryKey);

		if (ivldDemandActual == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldDemandActualException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldDemandActual;
	}

	/**
	 * Returns the ivld demand actual with the primary key or throws a {@link NoSuchIvldDemandActualException} if it could not be found.
	 *
	 * @param ivldDemandActualSid the primary key of the ivld demand actual
	 * @return the ivld demand actual
	 * @throws NoSuchIvldDemandActualException if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual findByPrimaryKey(int ivldDemandActualSid)
		throws NoSuchIvldDemandActualException {
		return findByPrimaryKey((Serializable)ivldDemandActualSid);
	}

	/**
	 * Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld demand actual
	 * @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
				IvldDemandActualImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldDemandActual ivldDemandActual = (IvldDemandActual)serializable;

		if (ivldDemandActual == null) {
			Session session = null;

			try {
				session = openSession();

				ivldDemandActual = (IvldDemandActual)session.get(IvldDemandActualImpl.class,
						primaryKey);

				if (ivldDemandActual != null) {
					cacheResult(ivldDemandActual);
				}
				else {
					entityCache.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
						IvldDemandActualImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandActualImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldDemandActual;
	}

	/**
	 * Returns the ivld demand actual with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldDemandActualSid the primary key of the ivld demand actual
	 * @return the ivld demand actual, or <code>null</code> if a ivld demand actual with the primary key could not be found
	 */
	@Override
	public IvldDemandActual fetchByPrimaryKey(int ivldDemandActualSid) {
		return fetchByPrimaryKey((Serializable)ivldDemandActualSid);
	}

	@Override
	public Map<Serializable, IvldDemandActual> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldDemandActual> map = new HashMap<Serializable, IvldDemandActual>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldDemandActual ivldDemandActual = fetchByPrimaryKey(primaryKey);

			if (ivldDemandActual != null) {
				map.put(primaryKey, ivldDemandActual);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandActualImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldDemandActual)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDDEMANDACTUAL_WHERE_PKS_IN);

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

			for (IvldDemandActual ivldDemandActual : (List<IvldDemandActual>)q.list()) {
				map.put(ivldDemandActual.getPrimaryKeyObj(), ivldDemandActual);

				cacheResult(ivldDemandActual);

				uncachedPrimaryKeys.remove(ivldDemandActual.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldDemandActualModelImpl.ENTITY_CACHE_ENABLED,
					IvldDemandActualImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld demand actuals.
	 *
	 * @return the ivld demand actuals
	 */
	@Override
	public List<IvldDemandActual> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld demand actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand actuals
	 * @param end the upper bound of the range of ivld demand actuals (not inclusive)
	 * @return the range of ivld demand actuals
	 */
	@Override
	public List<IvldDemandActual> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld demand actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand actuals
	 * @param end the upper bound of the range of ivld demand actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld demand actuals
	 */
	@Override
	public List<IvldDemandActual> findAll(int start, int end,
		OrderByComparator<IvldDemandActual> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld demand actuals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld demand actuals
	 * @param end the upper bound of the range of ivld demand actuals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld demand actuals
	 */
	@Override
	public List<IvldDemandActual> findAll(int start, int end,
		OrderByComparator<IvldDemandActual> orderByComparator,
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

		List<IvldDemandActual> list = null;

		if (retrieveFromCache) {
			list = (List<IvldDemandActual>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDDEMANDACTUAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDDEMANDACTUAL;

				if (pagination) {
					sql = sql.concat(IvldDemandActualModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldDemandActual>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldDemandActual>)QueryUtil.list(q,
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
	 * Removes all the ivld demand actuals from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldDemandActual ivldDemandActual : findAll()) {
			remove(ivldDemandActual);
		}
	}

	/**
	 * Returns the number of ivld demand actuals.
	 *
	 * @return the number of ivld demand actuals
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDDEMANDACTUAL);

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
		return IvldDemandActualModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld demand actual persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldDemandActualImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDDEMANDACTUAL = "SELECT ivldDemandActual FROM IvldDemandActual ivldDemandActual";
	private static final String _SQL_SELECT_IVLDDEMANDACTUAL_WHERE_PKS_IN = "SELECT ivldDemandActual FROM IvldDemandActual ivldDemandActual WHERE IVLD_DEMAND_ACTUAL_SID IN (";
	private static final String _SQL_COUNT_IVLDDEMANDACTUAL = "SELECT COUNT(ivldDemandActual) FROM IvldDemandActual ivldDemandActual";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldDemandActual.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldDemandActual exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldDemandActualPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastYear", "grossUnits", "totalDemandUnits", "itemId",
				"modifiedDate", "organizationKey", "source", "marketShareRatio",
				"createdBy", "createdDate", "demandActualInterfaceId",
				"addChgDelIndicator", "itemIdentifier", "errorCode",
				"intfInsertedDate", "marketShareUnits", "modifiedBy",
				"reprocessedFlag", "reasonForFailure", "country", "forecastType",
				"brandId", "grossPrice", "ivldDemandActualSid", "grossAmount",
				"itemIdentifierCodeQualifier", "batchId", "forecastMonth",
				"errorField", "netSalesPrice", "netSalesAmount", "segment",
				"totalDemandAmount", "marketSizeUnits", "checkRecord"
			});
}