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

import com.stpl.app.exception.NoSuchStMedicaidNewNdcException;
import com.stpl.app.model.StMedicaidNewNdc;
import com.stpl.app.model.impl.StMedicaidNewNdcImpl;
import com.stpl.app.model.impl.StMedicaidNewNdcModelImpl;
import com.stpl.app.service.persistence.StMedicaidNewNdcPK;
import com.stpl.app.service.persistence.StMedicaidNewNdcPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMedicaidNewNdcPersistence
 * @see com.stpl.app.service.persistence.StMedicaidNewNdcUtil
 * @generated
 */
@ProviderType
public class StMedicaidNewNdcPersistenceImpl extends BasePersistenceImpl<StMedicaidNewNdc>
	implements StMedicaidNewNdcPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StMedicaidNewNdcUtil} to access the st medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StMedicaidNewNdcImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
			StMedicaidNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
			StMedicaidNewNdcImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StMedicaidNewNdcPersistenceImpl() {
		setModelClass(StMedicaidNewNdc.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastAmp", "FORECAST_AMP");
			dbColumnNames.put("forecastBestprice", "FORECAST_BESTPRICE");
			dbColumnNames.put("baseYearCpi", "BASE_YEAR_CPI");
			dbColumnNames.put("ndc9", "NDC9");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
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
	 * Caches the st medicaid new ndc in the entity cache if it is enabled.
	 *
	 * @param stMedicaidNewNdc the st medicaid new ndc
	 */
	@Override
	public void cacheResult(StMedicaidNewNdc stMedicaidNewNdc) {
		entityCache.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey(),
			stMedicaidNewNdc);

		stMedicaidNewNdc.resetOriginalValues();
	}

	/**
	 * Caches the st medicaid new ndcs in the entity cache if it is enabled.
	 *
	 * @param stMedicaidNewNdcs the st medicaid new ndcs
	 */
	@Override
	public void cacheResult(List<StMedicaidNewNdc> stMedicaidNewNdcs) {
		for (StMedicaidNewNdc stMedicaidNewNdc : stMedicaidNewNdcs) {
			if (entityCache.getResult(
						StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StMedicaidNewNdcImpl.class,
						stMedicaidNewNdc.getPrimaryKey()) == null) {
				cacheResult(stMedicaidNewNdc);
			}
			else {
				stMedicaidNewNdc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st medicaid new ndcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StMedicaidNewNdcImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st medicaid new ndc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StMedicaidNewNdc stMedicaidNewNdc) {
		entityCache.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StMedicaidNewNdc> stMedicaidNewNdcs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StMedicaidNewNdc stMedicaidNewNdc : stMedicaidNewNdcs) {
			entityCache.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
	 *
	 * @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
	 * @return the new st medicaid new ndc
	 */
	@Override
	public StMedicaidNewNdc create(StMedicaidNewNdcPK stMedicaidNewNdcPK) {
		StMedicaidNewNdc stMedicaidNewNdc = new StMedicaidNewNdcImpl();

		stMedicaidNewNdc.setNew(true);
		stMedicaidNewNdc.setPrimaryKey(stMedicaidNewNdcPK);

		return stMedicaidNewNdc;
	}

	/**
	 * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc that was removed
	 * @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc remove(StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws NoSuchStMedicaidNewNdcException {
		return remove((Serializable)stMedicaidNewNdcPK);
	}

	/**
	 * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc that was removed
	 * @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc remove(Serializable primaryKey)
		throws NoSuchStMedicaidNewNdcException {
		Session session = null;

		try {
			session = openSession();

			StMedicaidNewNdc stMedicaidNewNdc = (StMedicaidNewNdc)session.get(StMedicaidNewNdcImpl.class,
					primaryKey);

			if (stMedicaidNewNdc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stMedicaidNewNdc);
		}
		catch (NoSuchStMedicaidNewNdcException nsee) {
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
	protected StMedicaidNewNdc removeImpl(StMedicaidNewNdc stMedicaidNewNdc) {
		stMedicaidNewNdc = toUnwrappedModel(stMedicaidNewNdc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stMedicaidNewNdc)) {
				stMedicaidNewNdc = (StMedicaidNewNdc)session.get(StMedicaidNewNdcImpl.class,
						stMedicaidNewNdc.getPrimaryKeyObj());
			}

			if (stMedicaidNewNdc != null) {
				session.delete(stMedicaidNewNdc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stMedicaidNewNdc != null) {
			clearCache(stMedicaidNewNdc);
		}

		return stMedicaidNewNdc;
	}

	@Override
	public StMedicaidNewNdc updateImpl(StMedicaidNewNdc stMedicaidNewNdc) {
		stMedicaidNewNdc = toUnwrappedModel(stMedicaidNewNdc);

		boolean isNew = stMedicaidNewNdc.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stMedicaidNewNdc.isNew()) {
				session.save(stMedicaidNewNdc);

				stMedicaidNewNdc.setNew(false);
			}
			else {
				stMedicaidNewNdc = (StMedicaidNewNdc)session.merge(stMedicaidNewNdc);
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

		entityCache.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StMedicaidNewNdcImpl.class, stMedicaidNewNdc.getPrimaryKey(),
			stMedicaidNewNdc, false);

		stMedicaidNewNdc.resetOriginalValues();

		return stMedicaidNewNdc;
	}

	protected StMedicaidNewNdc toUnwrappedModel(
		StMedicaidNewNdc stMedicaidNewNdc) {
		if (stMedicaidNewNdc instanceof StMedicaidNewNdcImpl) {
			return stMedicaidNewNdc;
		}

		StMedicaidNewNdcImpl stMedicaidNewNdcImpl = new StMedicaidNewNdcImpl();

		stMedicaidNewNdcImpl.setNew(stMedicaidNewNdc.isNew());
		stMedicaidNewNdcImpl.setPrimaryKey(stMedicaidNewNdc.getPrimaryKey());

		stMedicaidNewNdcImpl.setForecastAmp(stMedicaidNewNdc.getForecastAmp());
		stMedicaidNewNdcImpl.setForecastBestprice(stMedicaidNewNdc.getForecastBestprice());
		stMedicaidNewNdcImpl.setBaseYearCpi(stMedicaidNewNdc.getBaseYearCpi());
		stMedicaidNewNdcImpl.setNdc9(stMedicaidNewNdc.getNdc9());
		stMedicaidNewNdcImpl.setUserId(stMedicaidNewNdc.getUserId());
		stMedicaidNewNdcImpl.setLastModifiedDate(stMedicaidNewNdc.getLastModifiedDate());
		stMedicaidNewNdcImpl.setWacPrice(stMedicaidNewNdc.getWacPrice());
		stMedicaidNewNdcImpl.setBaseYearAmp(stMedicaidNewNdc.getBaseYearAmp());
		stMedicaidNewNdcImpl.setSessionId(stMedicaidNewNdc.getSessionId());

		return stMedicaidNewNdcImpl;
	}

	/**
	 * Returns the st medicaid new ndc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc
	 * @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStMedicaidNewNdcException {
		StMedicaidNewNdc stMedicaidNewNdc = fetchByPrimaryKey(primaryKey);

		if (stMedicaidNewNdc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stMedicaidNewNdc;
	}

	/**
	 * Returns the st medicaid new ndc with the primary key or throws a {@link NoSuchStMedicaidNewNdcException} if it could not be found.
	 *
	 * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc
	 * @throws NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc findByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK)
		throws NoSuchStMedicaidNewNdcException {
		return findByPrimaryKey((Serializable)stMedicaidNewNdcPK);
	}

	/**
	 * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StMedicaidNewNdcImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StMedicaidNewNdc stMedicaidNewNdc = (StMedicaidNewNdc)serializable;

		if (stMedicaidNewNdc == null) {
			Session session = null;

			try {
				session = openSession();

				stMedicaidNewNdc = (StMedicaidNewNdc)session.get(StMedicaidNewNdcImpl.class,
						primaryKey);

				if (stMedicaidNewNdc != null) {
					cacheResult(stMedicaidNewNdc);
				}
				else {
					entityCache.putResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StMedicaidNewNdcImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StMedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					StMedicaidNewNdcImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stMedicaidNewNdc;
	}

	/**
	 * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
	 * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
	 */
	@Override
	public StMedicaidNewNdc fetchByPrimaryKey(
		StMedicaidNewNdcPK stMedicaidNewNdcPK) {
		return fetchByPrimaryKey((Serializable)stMedicaidNewNdcPK);
	}

	@Override
	public Map<Serializable, StMedicaidNewNdc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StMedicaidNewNdc> map = new HashMap<Serializable, StMedicaidNewNdc>();

		for (Serializable primaryKey : primaryKeys) {
			StMedicaidNewNdc stMedicaidNewNdc = fetchByPrimaryKey(primaryKey);

			if (stMedicaidNewNdc != null) {
				map.put(primaryKey, stMedicaidNewNdc);
			}
		}

		return map;
	}

	/**
	 * Returns all the st medicaid new ndcs.
	 *
	 * @return the st medicaid new ndcs
	 */
	@Override
	public List<StMedicaidNewNdc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st medicaid new ndcs
	 * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	 * @return the range of st medicaid new ndcs
	 */
	@Override
	public List<StMedicaidNewNdc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st medicaid new ndcs
	 * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st medicaid new ndcs
	 */
	@Override
	public List<StMedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<StMedicaidNewNdc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st medicaid new ndcs
	 * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st medicaid new ndcs
	 */
	@Override
	public List<StMedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<StMedicaidNewNdc> orderByComparator,
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

		List<StMedicaidNewNdc> list = null;

		if (retrieveFromCache) {
			list = (List<StMedicaidNewNdc>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STMEDICAIDNEWNDC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STMEDICAIDNEWNDC;

				if (pagination) {
					sql = sql.concat(StMedicaidNewNdcModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StMedicaidNewNdc>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StMedicaidNewNdc>)QueryUtil.list(q,
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
	 * Removes all the st medicaid new ndcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StMedicaidNewNdc stMedicaidNewNdc : findAll()) {
			remove(stMedicaidNewNdc);
		}
	}

	/**
	 * Returns the number of st medicaid new ndcs.
	 *
	 * @return the number of st medicaid new ndcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STMEDICAIDNEWNDC);

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
		return StMedicaidNewNdcModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st medicaid new ndc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StMedicaidNewNdcImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STMEDICAIDNEWNDC = "SELECT stMedicaidNewNdc FROM StMedicaidNewNdc stMedicaidNewNdc";
	private static final String _SQL_COUNT_STMEDICAIDNEWNDC = "SELECT COUNT(stMedicaidNewNdc) FROM StMedicaidNewNdc stMedicaidNewNdc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stMedicaidNewNdc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMedicaidNewNdc exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StMedicaidNewNdcPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastAmp", "forecastBestprice", "baseYearCpi", "ndc9",
				"userId", "lastModifiedDate", "wacPrice", "baseYearAmp",
				"sessionId"
			});
}