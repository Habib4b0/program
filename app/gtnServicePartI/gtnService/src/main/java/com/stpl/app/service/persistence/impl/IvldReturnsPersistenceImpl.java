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

import com.stpl.app.exception.NoSuchIvldReturnsException;
import com.stpl.app.model.IvldReturns;
import com.stpl.app.model.impl.IvldReturnsImpl;
import com.stpl.app.model.impl.IvldReturnsModelImpl;
import com.stpl.app.service.persistence.IvldReturnsPersistence;

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
 * The persistence implementation for the ivld returns service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldReturnsPersistence
 * @see com.stpl.app.service.persistence.IvldReturnsUtil
 * @generated
 */
@ProviderType
public class IvldReturnsPersistenceImpl extends BasePersistenceImpl<IvldReturns>
	implements IvldReturnsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldReturnsUtil} to access the ivld returns persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldReturnsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsModelImpl.FINDER_CACHE_ENABLED, IvldReturnsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsModelImpl.FINDER_CACHE_ENABLED, IvldReturnsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldReturnsPersistenceImpl() {
		setModelClass(IvldReturns.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjValueAtOrigAsp", "ADJ_VALUE_AT_ORIG_ASP");
			dbColumnNames.put("firstReturn", "FIRST_RETURN");
			dbColumnNames.put("asp", "ASP");
			dbColumnNames.put("maxExpiredMonthPlusCutoff",
				"MAX_EXPIRED_MONS_PLUSCUTOFF");
			dbColumnNames.put("posEstimatedReturnUnits",
				"POS_ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("origSaleMonthCutOff", "ORIG_SALE_MONTH_CUT_OFF");
			dbColumnNames.put("calcUsed", "CALC_USED");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("lastReturn", "LAST_RETURN");
			dbColumnNames.put("expectedReturnUnits", "ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("version", "VERSION");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("weightedAvgMonths", "WEIGHTED_AVG_MONTHS");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("ivldReturnsSid", "IVLD_RETURNS_SID");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("pct25th", "PCT_25TH");
			dbColumnNames.put("loadDate", "LOAD_DATE");
			dbColumnNames.put("maxExpiredMonth", "MAX_EXPIRED_MONTH");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("actualReturnRate", "ACTUAL_RETURN_RATE");
			dbColumnNames.put("rreserveId", "RRESERVE_ID");
			dbColumnNames.put("returnComplete", "RETURN_COMPLETE");
			dbColumnNames.put("expectedReturnRate", "EXPECTED_RETURN_RATE");
			dbColumnNames.put("pct50th", "PCT_50TH");
			dbColumnNames.put("within50qrtile", "WITHIN_50QRTILE");
			dbColumnNames.put("rreserveInterfaceId", "RRESERVE_INTERFACE_ID");
			dbColumnNames.put("cumReturnUnits", "CUM_RETURN_UNITS");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("origSaleMonth", "ORIG_SALE_MONTH");
			dbColumnNames.put("description", "DESCRIPTION");
			dbColumnNames.put("sku", "SKU");
			dbColumnNames.put("upperLimit", "UPPER_LIMIT");
			dbColumnNames.put("lowerLimit", "LOWER_LIMIT");
			dbColumnNames.put("valueAtOrigAsp", "VALUE_AT_ORIG_ASP");
			dbColumnNames.put("adjEstimatedReturnUnits",
				"ADJ_ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("pct75th", "PCT_75TH");
			dbColumnNames.put("pastExpiration", "PAST_EXPIRATION");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("maximum", "MAXIMUM");
			dbColumnNames.put("origSaleUnits", "ORIG_SALE_UNITS");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("origSaleDollars", "ORIG_SALE_DOLLARS");
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
	 * Caches the ivld returns in the entity cache if it is enabled.
	 *
	 * @param ivldReturns the ivld returns
	 */
	@Override
	public void cacheResult(IvldReturns ivldReturns) {
		entityCache.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsImpl.class, ivldReturns.getPrimaryKey(), ivldReturns);

		ivldReturns.resetOriginalValues();
	}

	/**
	 * Caches the ivld returnses in the entity cache if it is enabled.
	 *
	 * @param ivldReturnses the ivld returnses
	 */
	@Override
	public void cacheResult(List<IvldReturns> ivldReturnses) {
		for (IvldReturns ivldReturns : ivldReturnses) {
			if (entityCache.getResult(
						IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
						IvldReturnsImpl.class, ivldReturns.getPrimaryKey()) == null) {
				cacheResult(ivldReturns);
			}
			else {
				ivldReturns.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld returnses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldReturnsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld returns.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldReturns ivldReturns) {
		entityCache.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsImpl.class, ivldReturns.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldReturns> ivldReturnses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldReturns ivldReturns : ivldReturnses) {
			entityCache.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
				IvldReturnsImpl.class, ivldReturns.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
	 *
	 * @param ivldReturnsSid the primary key for the new ivld returns
	 * @return the new ivld returns
	 */
	@Override
	public IvldReturns create(int ivldReturnsSid) {
		IvldReturns ivldReturns = new IvldReturnsImpl();

		ivldReturns.setNew(true);
		ivldReturns.setPrimaryKey(ivldReturnsSid);

		return ivldReturns;
	}

	/**
	 * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldReturnsSid the primary key of the ivld returns
	 * @return the ivld returns that was removed
	 * @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns remove(int ivldReturnsSid)
		throws NoSuchIvldReturnsException {
		return remove((Serializable)ivldReturnsSid);
	}

	/**
	 * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld returns
	 * @return the ivld returns that was removed
	 * @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns remove(Serializable primaryKey)
		throws NoSuchIvldReturnsException {
		Session session = null;

		try {
			session = openSession();

			IvldReturns ivldReturns = (IvldReturns)session.get(IvldReturnsImpl.class,
					primaryKey);

			if (ivldReturns == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldReturnsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldReturns);
		}
		catch (NoSuchIvldReturnsException nsee) {
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
	protected IvldReturns removeImpl(IvldReturns ivldReturns) {
		ivldReturns = toUnwrappedModel(ivldReturns);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldReturns)) {
				ivldReturns = (IvldReturns)session.get(IvldReturnsImpl.class,
						ivldReturns.getPrimaryKeyObj());
			}

			if (ivldReturns != null) {
				session.delete(ivldReturns);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldReturns != null) {
			clearCache(ivldReturns);
		}

		return ivldReturns;
	}

	@Override
	public IvldReturns updateImpl(IvldReturns ivldReturns) {
		ivldReturns = toUnwrappedModel(ivldReturns);

		boolean isNew = ivldReturns.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldReturns.isNew()) {
				session.save(ivldReturns);

				ivldReturns.setNew(false);
			}
			else {
				ivldReturns = (IvldReturns)session.merge(ivldReturns);
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

		entityCache.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
			IvldReturnsImpl.class, ivldReturns.getPrimaryKey(), ivldReturns,
			false);

		ivldReturns.resetOriginalValues();

		return ivldReturns;
	}

	protected IvldReturns toUnwrappedModel(IvldReturns ivldReturns) {
		if (ivldReturns instanceof IvldReturnsImpl) {
			return ivldReturns;
		}

		IvldReturnsImpl ivldReturnsImpl = new IvldReturnsImpl();

		ivldReturnsImpl.setNew(ivldReturns.isNew());
		ivldReturnsImpl.setPrimaryKey(ivldReturns.getPrimaryKey());

		ivldReturnsImpl.setAdjValueAtOrigAsp(ivldReturns.getAdjValueAtOrigAsp());
		ivldReturnsImpl.setFirstReturn(ivldReturns.getFirstReturn());
		ivldReturnsImpl.setAsp(ivldReturns.getAsp());
		ivldReturnsImpl.setMaxExpiredMonthPlusCutoff(ivldReturns.getMaxExpiredMonthPlusCutoff());
		ivldReturnsImpl.setPosEstimatedReturnUnits(ivldReturns.getPosEstimatedReturnUnits());
		ivldReturnsImpl.setOrigSaleMonthCutOff(ivldReturns.getOrigSaleMonthCutOff());
		ivldReturnsImpl.setCalcUsed(ivldReturns.getCalcUsed());
		ivldReturnsImpl.setModifiedDate(ivldReturns.getModifiedDate());
		ivldReturnsImpl.setLastReturn(ivldReturns.getLastReturn());
		ivldReturnsImpl.setExpectedReturnUnits(ivldReturns.getExpectedReturnUnits());
		ivldReturnsImpl.setCreatedDate(ivldReturns.getCreatedDate());
		ivldReturnsImpl.setCreatedBy(ivldReturns.getCreatedBy());
		ivldReturnsImpl.setSource(ivldReturns.getSource());
		ivldReturnsImpl.setVersion(ivldReturns.getVersion());
		ivldReturnsImpl.setAddChgDelIndicator(ivldReturns.getAddChgDelIndicator());
		ivldReturnsImpl.setWeightedAvgMonths(ivldReturns.getWeightedAvgMonths());
		ivldReturnsImpl.setErrorCode(ivldReturns.getErrorCode());
		ivldReturnsImpl.setModifiedBy(ivldReturns.getModifiedBy());
		ivldReturnsImpl.setIvldReturnsSid(ivldReturns.getIvldReturnsSid());
		ivldReturnsImpl.setIntfInsertedDate(ivldReturns.getIntfInsertedDate());
		ivldReturnsImpl.setPct25th(ivldReturns.getPct25th());
		ivldReturnsImpl.setLoadDate(ivldReturns.getLoadDate());
		ivldReturnsImpl.setMaxExpiredMonth(ivldReturns.getMaxExpiredMonth());
		ivldReturnsImpl.setReprocessedFlag(ivldReturns.getReprocessedFlag());
		ivldReturnsImpl.setActualReturnRate(ivldReturns.getActualReturnRate());
		ivldReturnsImpl.setRreserveId(ivldReturns.getRreserveId());
		ivldReturnsImpl.setReturnComplete(ivldReturns.getReturnComplete());
		ivldReturnsImpl.setExpectedReturnRate(ivldReturns.getExpectedReturnRate());
		ivldReturnsImpl.setPct50th(ivldReturns.getPct50th());
		ivldReturnsImpl.setWithin50qrtile(ivldReturns.getWithin50qrtile());
		ivldReturnsImpl.setRreserveInterfaceId(ivldReturns.getRreserveInterfaceId());
		ivldReturnsImpl.setCumReturnUnits(ivldReturns.getCumReturnUnits());
		ivldReturnsImpl.setReasonForFailure(ivldReturns.getReasonForFailure());
		ivldReturnsImpl.setOrigSaleMonth(ivldReturns.getOrigSaleMonth());
		ivldReturnsImpl.setDescription(ivldReturns.getDescription());
		ivldReturnsImpl.setSku(ivldReturns.getSku());
		ivldReturnsImpl.setUpperLimit(ivldReturns.getUpperLimit());
		ivldReturnsImpl.setLowerLimit(ivldReturns.getLowerLimit());
		ivldReturnsImpl.setValueAtOrigAsp(ivldReturns.getValueAtOrigAsp());
		ivldReturnsImpl.setAdjEstimatedReturnUnits(ivldReturns.getAdjEstimatedReturnUnits());
		ivldReturnsImpl.setPct75th(ivldReturns.getPct75th());
		ivldReturnsImpl.setPastExpiration(ivldReturns.getPastExpiration());
		ivldReturnsImpl.setBatchId(ivldReturns.getBatchId());
		ivldReturnsImpl.setMaximum(ivldReturns.getMaximum());
		ivldReturnsImpl.setOrigSaleUnits(ivldReturns.getOrigSaleUnits());
		ivldReturnsImpl.setErrorField(ivldReturns.getErrorField());
		ivldReturnsImpl.setBrand(ivldReturns.getBrand());
		ivldReturnsImpl.setOrigSaleDollars(ivldReturns.getOrigSaleDollars());
		ivldReturnsImpl.setCheckRecord(ivldReturns.isCheckRecord());

		return ivldReturnsImpl;
	}

	/**
	 * Returns the ivld returns with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld returns
	 * @return the ivld returns
	 * @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldReturnsException {
		IvldReturns ivldReturns = fetchByPrimaryKey(primaryKey);

		if (ivldReturns == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldReturnsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldReturns;
	}

	/**
	 * Returns the ivld returns with the primary key or throws a {@link NoSuchIvldReturnsException} if it could not be found.
	 *
	 * @param ivldReturnsSid the primary key of the ivld returns
	 * @return the ivld returns
	 * @throws NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns findByPrimaryKey(int ivldReturnsSid)
		throws NoSuchIvldReturnsException {
		return findByPrimaryKey((Serializable)ivldReturnsSid);
	}

	/**
	 * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld returns
	 * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
				IvldReturnsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldReturns ivldReturns = (IvldReturns)serializable;

		if (ivldReturns == null) {
			Session session = null;

			try {
				session = openSession();

				ivldReturns = (IvldReturns)session.get(IvldReturnsImpl.class,
						primaryKey);

				if (ivldReturns != null) {
					cacheResult(ivldReturns);
				}
				else {
					entityCache.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
						IvldReturnsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
					IvldReturnsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldReturns;
	}

	/**
	 * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldReturnsSid the primary key of the ivld returns
	 * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
	 */
	@Override
	public IvldReturns fetchByPrimaryKey(int ivldReturnsSid) {
		return fetchByPrimaryKey((Serializable)ivldReturnsSid);
	}

	@Override
	public Map<Serializable, IvldReturns> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldReturns> map = new HashMap<Serializable, IvldReturns>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldReturns ivldReturns = fetchByPrimaryKey(primaryKey);

			if (ivldReturns != null) {
				map.put(primaryKey, ivldReturns);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
					IvldReturnsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldReturns)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDRETURNS_WHERE_PKS_IN);

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

			for (IvldReturns ivldReturns : (List<IvldReturns>)q.list()) {
				map.put(ivldReturns.getPrimaryKeyObj(), ivldReturns);

				cacheResult(ivldReturns);

				uncachedPrimaryKeys.remove(ivldReturns.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldReturnsModelImpl.ENTITY_CACHE_ENABLED,
					IvldReturnsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld returnses.
	 *
	 * @return the ivld returnses
	 */
	@Override
	public List<IvldReturns> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld returnses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld returnses
	 * @param end the upper bound of the range of ivld returnses (not inclusive)
	 * @return the range of ivld returnses
	 */
	@Override
	public List<IvldReturns> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld returnses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld returnses
	 * @param end the upper bound of the range of ivld returnses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld returnses
	 */
	@Override
	public List<IvldReturns> findAll(int start, int end,
		OrderByComparator<IvldReturns> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld returnses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld returnses
	 * @param end the upper bound of the range of ivld returnses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld returnses
	 */
	@Override
	public List<IvldReturns> findAll(int start, int end,
		OrderByComparator<IvldReturns> orderByComparator,
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

		List<IvldReturns> list = null;

		if (retrieveFromCache) {
			list = (List<IvldReturns>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDRETURNS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDRETURNS;

				if (pagination) {
					sql = sql.concat(IvldReturnsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldReturns>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldReturns>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ivld returnses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldReturns ivldReturns : findAll()) {
			remove(ivldReturns);
		}
	}

	/**
	 * Returns the number of ivld returnses.
	 *
	 * @return the number of ivld returnses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDRETURNS);

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
		return IvldReturnsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld returns persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldReturnsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDRETURNS = "SELECT ivldReturns FROM IvldReturns ivldReturns";
	private static final String _SQL_SELECT_IVLDRETURNS_WHERE_PKS_IN = "SELECT ivldReturns FROM IvldReturns ivldReturns WHERE IVLD_RETURNS_SID IN (";
	private static final String _SQL_COUNT_IVLDRETURNS = "SELECT COUNT(ivldReturns) FROM IvldReturns ivldReturns";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldReturns.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldReturns exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldReturnsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjValueAtOrigAsp", "firstReturn", "asp",
				"maxExpiredMonthPlusCutoff", "posEstimatedReturnUnits",
				"origSaleMonthCutOff", "calcUsed", "modifiedDate", "lastReturn",
				"expectedReturnUnits", "createdDate", "createdBy", "source",
				"version", "addChgDelIndicator", "weightedAvgMonths",
				"errorCode", "modifiedBy", "ivldReturnsSid", "intfInsertedDate",
				"pct25th", "loadDate", "maxExpiredMonth", "reprocessedFlag",
				"actualReturnRate", "rreserveId", "returnComplete",
				"expectedReturnRate", "pct50th", "within50qrtile",
				"rreserveInterfaceId", "cumReturnUnits", "reasonForFailure",
				"origSaleMonth", "description", "sku", "upperLimit",
				"lowerLimit", "valueAtOrigAsp", "adjEstimatedReturnUnits",
				"pct75th", "pastExpiration", "batchId", "maximum",
				"origSaleUnits", "errorField", "brand", "origSaleDollars",
				"checkRecord"
			});
}