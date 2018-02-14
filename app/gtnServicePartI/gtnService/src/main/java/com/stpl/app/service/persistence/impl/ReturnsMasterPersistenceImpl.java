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

import com.stpl.app.exception.NoSuchReturnsMasterException;
import com.stpl.app.model.ReturnsMaster;
import com.stpl.app.model.impl.ReturnsMasterImpl;
import com.stpl.app.model.impl.ReturnsMasterModelImpl;
import com.stpl.app.service.persistence.ReturnsMasterPersistence;

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
 * The persistence implementation for the returns master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ReturnsMasterPersistence
 * @see com.stpl.app.service.persistence.ReturnsMasterUtil
 * @generated
 */
@ProviderType
public class ReturnsMasterPersistenceImpl extends BasePersistenceImpl<ReturnsMaster>
	implements ReturnsMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ReturnsMasterUtil} to access the returns master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ReturnsMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterModelImpl.FINDER_CACHE_ENABLED,
			ReturnsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterModelImpl.FINDER_CACHE_ENABLED,
			ReturnsMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ReturnsMasterPersistenceImpl() {
		setModelClass(ReturnsMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("adjValueAtOrigAsp", "ADJ_VALUE_AT_ORIG_ASP");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("firstReturn", "FIRST_RETURN");
			dbColumnNames.put("asp", "ASP");
			dbColumnNames.put("maxExpiredMonthPlusCutoff",
				"MAX_EXPIRED_MONS_PLUSCUTOFF");
			dbColumnNames.put("posEstimatedReturnUnits",
				"POS_ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("origSaleMonthCutOff", "ORIG_SALE_MONTH_CUT_OFF");
			dbColumnNames.put("calcUsed", "CALC_USED");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("lastReturn", "LAST_RETURN");
			dbColumnNames.put("expectedReturnUnits", "ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("version", "VERSION");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("returnsMasterSid", "RETURNS_MASTER_SID");
			dbColumnNames.put("weightedAvgMonths", "WEIGHTED_AVG_MONTHS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("pct25th", "PCT_25TH");
			dbColumnNames.put("loadDate", "LOAD_DATE");
			dbColumnNames.put("maxExpiredMonth", "MAX_EXPIRED_MONTH");
			dbColumnNames.put("actualReturnRate", "ACTUAL_RETURN_RATE");
			dbColumnNames.put("rreserveId", "RRESERVE_ID");
			dbColumnNames.put("returnComplete", "RETURN_COMPLETE");
			dbColumnNames.put("expectedReturnRate", "EXPECTED_RETURN_RATE");
			dbColumnNames.put("pct50th", "PCT_50TH");
			dbColumnNames.put("within50qrtile", "WITHIN_50QRTILE");
			dbColumnNames.put("cumReturnUnits", "CUM_RETURN_UNITS");
			dbColumnNames.put("origSaleMonth", "ORIG_SALE_MONTH");
			dbColumnNames.put("description", "DESCRIPTION");
			dbColumnNames.put("sku", "SKU");
			dbColumnNames.put("upperLimit", "UPPER_LIMIT");
			dbColumnNames.put("lowerLimit", "LOWER_LIMIT");
			dbColumnNames.put("valueAtOrigAsp", "VALUE_AT_ORIG_ASP");
			dbColumnNames.put("adjEstimatedReturnUnits",
				"ADJ_ESTIMATED_RETURN_UNITS");
			dbColumnNames.put("pct75th", "PCT_75TH");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("pastExpiration", "PAST_EXPIRATION");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("maximum", "MAXIMUM");
			dbColumnNames.put("origSaleUnits", "ORIG_SALE_UNITS");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("origSaleDollars", "ORIG_SALE_DOLLARS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the returns master in the entity cache if it is enabled.
	 *
	 * @param returnsMaster the returns master
	 */
	@Override
	public void cacheResult(ReturnsMaster returnsMaster) {
		entityCache.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterImpl.class, returnsMaster.getPrimaryKey(),
			returnsMaster);

		returnsMaster.resetOriginalValues();
	}

	/**
	 * Caches the returns masters in the entity cache if it is enabled.
	 *
	 * @param returnsMasters the returns masters
	 */
	@Override
	public void cacheResult(List<ReturnsMaster> returnsMasters) {
		for (ReturnsMaster returnsMaster : returnsMasters) {
			if (entityCache.getResult(
						ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
						ReturnsMasterImpl.class, returnsMaster.getPrimaryKey()) == null) {
				cacheResult(returnsMaster);
			}
			else {
				returnsMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all returns masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ReturnsMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the returns master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ReturnsMaster returnsMaster) {
		entityCache.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterImpl.class, returnsMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ReturnsMaster> returnsMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ReturnsMaster returnsMaster : returnsMasters) {
			entityCache.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
				ReturnsMasterImpl.class, returnsMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new returns master with the primary key. Does not add the returns master to the database.
	 *
	 * @param returnsMasterSid the primary key for the new returns master
	 * @return the new returns master
	 */
	@Override
	public ReturnsMaster create(int returnsMasterSid) {
		ReturnsMaster returnsMaster = new ReturnsMasterImpl();

		returnsMaster.setNew(true);
		returnsMaster.setPrimaryKey(returnsMasterSid);

		return returnsMaster;
	}

	/**
	 * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param returnsMasterSid the primary key of the returns master
	 * @return the returns master that was removed
	 * @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster remove(int returnsMasterSid)
		throws NoSuchReturnsMasterException {
		return remove((Serializable)returnsMasterSid);
	}

	/**
	 * Removes the returns master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the returns master
	 * @return the returns master that was removed
	 * @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster remove(Serializable primaryKey)
		throws NoSuchReturnsMasterException {
		Session session = null;

		try {
			session = openSession();

			ReturnsMaster returnsMaster = (ReturnsMaster)session.get(ReturnsMasterImpl.class,
					primaryKey);

			if (returnsMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchReturnsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(returnsMaster);
		}
		catch (NoSuchReturnsMasterException nsee) {
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
	protected ReturnsMaster removeImpl(ReturnsMaster returnsMaster) {
		returnsMaster = toUnwrappedModel(returnsMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(returnsMaster)) {
				returnsMaster = (ReturnsMaster)session.get(ReturnsMasterImpl.class,
						returnsMaster.getPrimaryKeyObj());
			}

			if (returnsMaster != null) {
				session.delete(returnsMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (returnsMaster != null) {
			clearCache(returnsMaster);
		}

		return returnsMaster;
	}

	@Override
	public ReturnsMaster updateImpl(ReturnsMaster returnsMaster) {
		returnsMaster = toUnwrappedModel(returnsMaster);

		boolean isNew = returnsMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (returnsMaster.isNew()) {
				session.save(returnsMaster);

				returnsMaster.setNew(false);
			}
			else {
				returnsMaster = (ReturnsMaster)session.merge(returnsMaster);
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

		entityCache.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
			ReturnsMasterImpl.class, returnsMaster.getPrimaryKey(),
			returnsMaster, false);

		returnsMaster.resetOriginalValues();

		return returnsMaster;
	}

	protected ReturnsMaster toUnwrappedModel(ReturnsMaster returnsMaster) {
		if (returnsMaster instanceof ReturnsMasterImpl) {
			return returnsMaster;
		}

		ReturnsMasterImpl returnsMasterImpl = new ReturnsMasterImpl();

		returnsMasterImpl.setNew(returnsMaster.isNew());
		returnsMasterImpl.setPrimaryKey(returnsMaster.getPrimaryKey());

		returnsMasterImpl.setAdjValueAtOrigAsp(returnsMaster.getAdjValueAtOrigAsp());
		returnsMasterImpl.setItemMasterSid(returnsMaster.getItemMasterSid());
		returnsMasterImpl.setFirstReturn(returnsMaster.getFirstReturn());
		returnsMasterImpl.setAsp(returnsMaster.getAsp());
		returnsMasterImpl.setMaxExpiredMonthPlusCutoff(returnsMaster.getMaxExpiredMonthPlusCutoff());
		returnsMasterImpl.setPosEstimatedReturnUnits(returnsMaster.getPosEstimatedReturnUnits());
		returnsMasterImpl.setOrigSaleMonthCutOff(returnsMaster.getOrigSaleMonthCutOff());
		returnsMasterImpl.setCalcUsed(returnsMaster.getCalcUsed());
		returnsMasterImpl.setModifiedDate(returnsMaster.getModifiedDate());
		returnsMasterImpl.setBrandMasterSid(returnsMaster.getBrandMasterSid());
		returnsMasterImpl.setLastReturn(returnsMaster.getLastReturn());
		returnsMasterImpl.setExpectedReturnUnits(returnsMaster.getExpectedReturnUnits());
		returnsMasterImpl.setCreatedDate(returnsMaster.getCreatedDate());
		returnsMasterImpl.setCreatedBy(returnsMaster.getCreatedBy());
		returnsMasterImpl.setSource(returnsMaster.getSource());
		returnsMasterImpl.setVersion(returnsMaster.getVersion());
		returnsMasterImpl.setAddChgDelIndicator(returnsMaster.getAddChgDelIndicator());
		returnsMasterImpl.setReturnsMasterSid(returnsMaster.getReturnsMasterSid());
		returnsMasterImpl.setWeightedAvgMonths(returnsMaster.getWeightedAvgMonths());
		returnsMasterImpl.setModifiedBy(returnsMaster.getModifiedBy());
		returnsMasterImpl.setInboundStatus(returnsMaster.getInboundStatus());
		returnsMasterImpl.setPct25th(returnsMaster.getPct25th());
		returnsMasterImpl.setLoadDate(returnsMaster.getLoadDate());
		returnsMasterImpl.setMaxExpiredMonth(returnsMaster.getMaxExpiredMonth());
		returnsMasterImpl.setActualReturnRate(returnsMaster.getActualReturnRate());
		returnsMasterImpl.setRreserveId(returnsMaster.getRreserveId());
		returnsMasterImpl.setReturnComplete(returnsMaster.getReturnComplete());
		returnsMasterImpl.setExpectedReturnRate(returnsMaster.getExpectedReturnRate());
		returnsMasterImpl.setPct50th(returnsMaster.getPct50th());
		returnsMasterImpl.setWithin50qrtile(returnsMaster.getWithin50qrtile());
		returnsMasterImpl.setCumReturnUnits(returnsMaster.getCumReturnUnits());
		returnsMasterImpl.setOrigSaleMonth(returnsMaster.getOrigSaleMonth());
		returnsMasterImpl.setDescription(returnsMaster.getDescription());
		returnsMasterImpl.setSku(returnsMaster.getSku());
		returnsMasterImpl.setUpperLimit(returnsMaster.getUpperLimit());
		returnsMasterImpl.setLowerLimit(returnsMaster.getLowerLimit());
		returnsMasterImpl.setValueAtOrigAsp(returnsMaster.getValueAtOrigAsp());
		returnsMasterImpl.setAdjEstimatedReturnUnits(returnsMaster.getAdjEstimatedReturnUnits());
		returnsMasterImpl.setPct75th(returnsMaster.getPct75th());
		returnsMasterImpl.setRecordLockStatus(returnsMaster.isRecordLockStatus());
		returnsMasterImpl.setPastExpiration(returnsMaster.getPastExpiration());
		returnsMasterImpl.setBatchId(returnsMaster.getBatchId());
		returnsMasterImpl.setMaximum(returnsMaster.getMaximum());
		returnsMasterImpl.setOrigSaleUnits(returnsMaster.getOrigSaleUnits());
		returnsMasterImpl.setBrand(returnsMaster.getBrand());
		returnsMasterImpl.setOrigSaleDollars(returnsMaster.getOrigSaleDollars());

		return returnsMasterImpl;
	}

	/**
	 * Returns the returns master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the returns master
	 * @return the returns master
	 * @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchReturnsMasterException {
		ReturnsMaster returnsMaster = fetchByPrimaryKey(primaryKey);

		if (returnsMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchReturnsMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return returnsMaster;
	}

	/**
	 * Returns the returns master with the primary key or throws a {@link NoSuchReturnsMasterException} if it could not be found.
	 *
	 * @param returnsMasterSid the primary key of the returns master
	 * @return the returns master
	 * @throws NoSuchReturnsMasterException if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster findByPrimaryKey(int returnsMasterSid)
		throws NoSuchReturnsMasterException {
		return findByPrimaryKey((Serializable)returnsMasterSid);
	}

	/**
	 * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the returns master
	 * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
				ReturnsMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ReturnsMaster returnsMaster = (ReturnsMaster)serializable;

		if (returnsMaster == null) {
			Session session = null;

			try {
				session = openSession();

				returnsMaster = (ReturnsMaster)session.get(ReturnsMasterImpl.class,
						primaryKey);

				if (returnsMaster != null) {
					cacheResult(returnsMaster);
				}
				else {
					entityCache.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
						ReturnsMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ReturnsMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return returnsMaster;
	}

	/**
	 * Returns the returns master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param returnsMasterSid the primary key of the returns master
	 * @return the returns master, or <code>null</code> if a returns master with the primary key could not be found
	 */
	@Override
	public ReturnsMaster fetchByPrimaryKey(int returnsMasterSid) {
		return fetchByPrimaryKey((Serializable)returnsMasterSid);
	}

	@Override
	public Map<Serializable, ReturnsMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ReturnsMaster> map = new HashMap<Serializable, ReturnsMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ReturnsMaster returnsMaster = fetchByPrimaryKey(primaryKey);

			if (returnsMaster != null) {
				map.put(primaryKey, returnsMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ReturnsMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ReturnsMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RETURNSMASTER_WHERE_PKS_IN);

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

			for (ReturnsMaster returnsMaster : (List<ReturnsMaster>)q.list()) {
				map.put(returnsMaster.getPrimaryKeyObj(), returnsMaster);

				cacheResult(returnsMaster);

				uncachedPrimaryKeys.remove(returnsMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ReturnsMasterModelImpl.ENTITY_CACHE_ENABLED,
					ReturnsMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the returns masters.
	 *
	 * @return the returns masters
	 */
	@Override
	public List<ReturnsMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the returns masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of returns masters
	 * @param end the upper bound of the range of returns masters (not inclusive)
	 * @return the range of returns masters
	 */
	@Override
	public List<ReturnsMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the returns masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of returns masters
	 * @param end the upper bound of the range of returns masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of returns masters
	 */
	@Override
	public List<ReturnsMaster> findAll(int start, int end,
		OrderByComparator<ReturnsMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the returns masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReturnsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of returns masters
	 * @param end the upper bound of the range of returns masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of returns masters
	 */
	@Override
	public List<ReturnsMaster> findAll(int start, int end,
		OrderByComparator<ReturnsMaster> orderByComparator,
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

		List<ReturnsMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ReturnsMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RETURNSMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RETURNSMASTER;

				if (pagination) {
					sql = sql.concat(ReturnsMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ReturnsMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ReturnsMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the returns masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ReturnsMaster returnsMaster : findAll()) {
			remove(returnsMaster);
		}
	}

	/**
	 * Returns the number of returns masters.
	 *
	 * @return the number of returns masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RETURNSMASTER);

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
		return ReturnsMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the returns master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ReturnsMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RETURNSMASTER = "SELECT returnsMaster FROM ReturnsMaster returnsMaster";
	private static final String _SQL_SELECT_RETURNSMASTER_WHERE_PKS_IN = "SELECT returnsMaster FROM ReturnsMaster returnsMaster WHERE RETURNS_MASTER_SID IN (";
	private static final String _SQL_COUNT_RETURNSMASTER = "SELECT COUNT(returnsMaster) FROM ReturnsMaster returnsMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "returnsMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ReturnsMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ReturnsMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"adjValueAtOrigAsp", "itemMasterSid", "firstReturn", "asp",
				"maxExpiredMonthPlusCutoff", "posEstimatedReturnUnits",
				"origSaleMonthCutOff", "calcUsed", "modifiedDate",
				"brandMasterSid", "lastReturn", "expectedReturnUnits",
				"createdDate", "createdBy", "source", "version",
				"addChgDelIndicator", "returnsMasterSid", "weightedAvgMonths",
				"modifiedBy", "inboundStatus", "pct25th", "loadDate",
				"maxExpiredMonth", "actualReturnRate", "rreserveId",
				"returnComplete", "expectedReturnRate", "pct50th",
				"within50qrtile", "cumReturnUnits", "origSaleMonth",
				"description", "sku", "upperLimit", "lowerLimit",
				"valueAtOrigAsp", "adjEstimatedReturnUnits", "pct75th",
				"recordLockStatus", "pastExpiration", "batchId", "maximum",
				"origSaleUnits", "brand", "origSaleDollars"
			});
}