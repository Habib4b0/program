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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchStNewNdcException;
import com.stpl.app.model.StNewNdc;
import com.stpl.app.model.impl.StNewNdcImpl;
import com.stpl.app.model.impl.StNewNdcModelImpl;
import com.stpl.app.service.persistence.StNewNdcPK;
import com.stpl.app.service.persistence.StNewNdcPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNewNdcPersistence
 * @see com.stpl.app.service.persistence.StNewNdcUtil
 * @generated
 */
@ProviderType
public class StNewNdcPersistenceImpl extends BasePersistenceImpl<StNewNdc>
	implements StNewNdcPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNewNdcUtil} to access the st new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNewNdcImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcModelImpl.FINDER_CACHE_ENABLED, StNewNdcImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcModelImpl.FINDER_CACHE_ENABLED, StNewNdcImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNewNdcPersistenceImpl() {
		setModelClass(StNewNdc.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastAmp", "FORECAST_AMP");
			dbColumnNames.put("forecastBestprice", "FORECAST_BESTPRICE");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");
			dbColumnNames.put("baseYearCpi", "BASE_YEAR_CPI");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("wacPrice", "WAC_PRICE");
			dbColumnNames.put("baseYearAmp", "BASE_YEAR_AMP");
			dbColumnNames.put("sessionId", "SESSION_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st new ndc in the entity cache if it is enabled.
	 *
	 * @param stNewNdc the st new ndc
	 */
	@Override
	public void cacheResult(StNewNdc stNewNdc) {
		entityCache.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcImpl.class, stNewNdc.getPrimaryKey(), stNewNdc);

		stNewNdc.resetOriginalValues();
	}

	/**
	 * Caches the st new ndcs in the entity cache if it is enabled.
	 *
	 * @param stNewNdcs the st new ndcs
	 */
	@Override
	public void cacheResult(List<StNewNdc> stNewNdcs) {
		for (StNewNdc stNewNdc : stNewNdcs) {
			if (entityCache.getResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StNewNdcImpl.class, stNewNdc.getPrimaryKey()) == null) {
				cacheResult(stNewNdc);
			}
			else {
				stNewNdc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st new ndcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNewNdcImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st new ndc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNewNdc stNewNdc) {
		entityCache.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcImpl.class, stNewNdc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNewNdc> stNewNdcs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNewNdc stNewNdc : stNewNdcs) {
			entityCache.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StNewNdcImpl.class, stNewNdc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
	 *
	 * @param stNewNdcPK the primary key for the new st new ndc
	 * @return the new st new ndc
	 */
	@Override
	public StNewNdc create(StNewNdcPK stNewNdcPK) {
		StNewNdc stNewNdc = new StNewNdcImpl();

		stNewNdc.setNew(true);
		stNewNdc.setPrimaryKey(stNewNdcPK);

		return stNewNdc;
	}

	/**
	 * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNewNdcPK the primary key of the st new ndc
	 * @return the st new ndc that was removed
	 * @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc remove(StNewNdcPK stNewNdcPK)
		throws NoSuchStNewNdcException {
		return remove((Serializable)stNewNdcPK);
	}

	/**
	 * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st new ndc
	 * @return the st new ndc that was removed
	 * @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc remove(Serializable primaryKey)
		throws NoSuchStNewNdcException {
		Session session = null;

		try {
			session = openSession();

			StNewNdc stNewNdc = (StNewNdc)session.get(StNewNdcImpl.class,
					primaryKey);

			if (stNewNdc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNewNdc);
		}
		catch (NoSuchStNewNdcException nsee) {
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
	protected StNewNdc removeImpl(StNewNdc stNewNdc) {
		stNewNdc = toUnwrappedModel(stNewNdc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNewNdc)) {
				stNewNdc = (StNewNdc)session.get(StNewNdcImpl.class,
						stNewNdc.getPrimaryKeyObj());
			}

			if (stNewNdc != null) {
				session.delete(stNewNdc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNewNdc != null) {
			clearCache(stNewNdc);
		}

		return stNewNdc;
	}

	@Override
	public StNewNdc updateImpl(StNewNdc stNewNdc) {
		stNewNdc = toUnwrappedModel(stNewNdc);

		boolean isNew = stNewNdc.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNewNdc.isNew()) {
				session.save(stNewNdc);

				stNewNdc.setNew(false);
			}
			else {
				stNewNdc = (StNewNdc)session.merge(stNewNdc);
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

		entityCache.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StNewNdcImpl.class, stNewNdc.getPrimaryKey(), stNewNdc, false);

		stNewNdc.resetOriginalValues();

		return stNewNdc;
	}

	protected StNewNdc toUnwrappedModel(StNewNdc stNewNdc) {
		if (stNewNdc instanceof StNewNdcImpl) {
			return stNewNdc;
		}

		StNewNdcImpl stNewNdcImpl = new StNewNdcImpl();

		stNewNdcImpl.setNew(stNewNdc.isNew());
		stNewNdcImpl.setPrimaryKey(stNewNdc.getPrimaryKey());

		stNewNdcImpl.setForecastAmp(stNewNdc.getForecastAmp());
		stNewNdcImpl.setForecastBestprice(stNewNdc.getForecastBestprice());
		stNewNdcImpl.setNaProjDetailsSid(stNewNdc.getNaProjDetailsSid());
		stNewNdcImpl.setBaseYearCpi(stNewNdc.getBaseYearCpi());
		stNewNdcImpl.setUserId(stNewNdc.getUserId());
		stNewNdcImpl.setLastModifiedDate(stNewNdc.getLastModifiedDate());
		stNewNdcImpl.setItemMasterSid(stNewNdc.getItemMasterSid());
		stNewNdcImpl.setWacPrice(stNewNdc.getWacPrice());
		stNewNdcImpl.setBaseYearAmp(stNewNdc.getBaseYearAmp());
		stNewNdcImpl.setSessionId(stNewNdc.getSessionId());

		return stNewNdcImpl;
	}

	/**
	 * Returns the st new ndc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st new ndc
	 * @return the st new ndc
	 * @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNewNdcException {
		StNewNdc stNewNdc = fetchByPrimaryKey(primaryKey);

		if (stNewNdc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNewNdc;
	}

	/**
	 * Returns the st new ndc with the primary key or throws a {@link NoSuchStNewNdcException} if it could not be found.
	 *
	 * @param stNewNdcPK the primary key of the st new ndc
	 * @return the st new ndc
	 * @throws NoSuchStNewNdcException if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc findByPrimaryKey(StNewNdcPK stNewNdcPK)
		throws NoSuchStNewNdcException {
		return findByPrimaryKey((Serializable)stNewNdcPK);
	}

	/**
	 * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st new ndc
	 * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StNewNdcImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNewNdc stNewNdc = (StNewNdc)serializable;

		if (stNewNdc == null) {
			Session session = null;

			try {
				session = openSession();

				stNewNdc = (StNewNdc)session.get(StNewNdcImpl.class, primaryKey);

				if (stNewNdc != null) {
					cacheResult(stNewNdc);
				}
				else {
					entityCache.putResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StNewNdcImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					StNewNdcImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNewNdc;
	}

	/**
	 * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNewNdcPK the primary key of the st new ndc
	 * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
	 */
	@Override
	public StNewNdc fetchByPrimaryKey(StNewNdcPK stNewNdcPK) {
		return fetchByPrimaryKey((Serializable)stNewNdcPK);
	}

	@Override
	public Map<Serializable, StNewNdc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNewNdc> map = new HashMap<Serializable, StNewNdc>();

		for (Serializable primaryKey : primaryKeys) {
			StNewNdc stNewNdc = fetchByPrimaryKey(primaryKey);

			if (stNewNdc != null) {
				map.put(primaryKey, stNewNdc);
			}
		}

		return map;
	}

	/**
	 * Returns all the st new ndcs.
	 *
	 * @return the st new ndcs
	 */
	@Override
	public List<StNewNdc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st new ndcs
	 * @param end the upper bound of the range of st new ndcs (not inclusive)
	 * @return the range of st new ndcs
	 */
	@Override
	public List<StNewNdc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st new ndcs
	 * @param end the upper bound of the range of st new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st new ndcs
	 */
	@Override
	public List<StNewNdc> findAll(int start, int end,
		OrderByComparator<StNewNdc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st new ndcs
	 * @param end the upper bound of the range of st new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st new ndcs
	 */
	@Override
	public List<StNewNdc> findAll(int start, int end,
		OrderByComparator<StNewNdc> orderByComparator, boolean retrieveFromCache) {
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

		List<StNewNdc> list = null;

		if (retrieveFromCache) {
			list = (List<StNewNdc>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNEWNDC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNEWNDC;

				if (pagination) {
					sql = sql.concat(StNewNdcModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNewNdc>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNewNdc>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the st new ndcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNewNdc stNewNdc : findAll()) {
			remove(stNewNdc);
		}
	}

	/**
	 * Returns the number of st new ndcs.
	 *
	 * @return the number of st new ndcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNEWNDC);

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
		return StNewNdcModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st new ndc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNewNdcImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNEWNDC = "SELECT stNewNdc FROM StNewNdc stNewNdc";
	private static final String _SQL_COUNT_STNEWNDC = "SELECT COUNT(stNewNdc) FROM StNewNdc stNewNdc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNewNdc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNewNdc exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNewNdcPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastAmp", "forecastBestprice", "naProjDetailsSid",
				"baseYearCpi", "userId", "lastModifiedDate", "itemMasterSid",
				"wacPrice", "baseYearAmp", "sessionId"
			});
}