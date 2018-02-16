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

import com.stpl.app.exception.NoSuchForecastingFormulaException;
import com.stpl.app.model.ForecastingFormula;
import com.stpl.app.model.impl.ForecastingFormulaImpl;
import com.stpl.app.model.impl.ForecastingFormulaModelImpl;
import com.stpl.app.service.persistence.ForecastingFormulaPersistence;

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
 * The persistence implementation for the forecasting formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ForecastingFormulaPersistence
 * @see com.stpl.app.service.persistence.ForecastingFormulaUtil
 * @generated
 */
@ProviderType
public class ForecastingFormulaPersistenceImpl extends BasePersistenceImpl<ForecastingFormula>
	implements ForecastingFormulaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ForecastingFormulaUtil} to access the forecasting formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ForecastingFormulaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED,
			ForecastingFormulaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED,
			ForecastingFormulaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ForecastingFormulaPersistenceImpl() {
		setModelClass(ForecastingFormula.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("formulaType", "FORMULA_TYPE");
			dbColumnNames.put("forecastingFormulaSid", "FORECASTING_FORMULA_SID");
			dbColumnNames.put("formula", "FORMULA");
			dbColumnNames.put("formulaNo", "FORMULA_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("formulaName", "FORMULA_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the forecasting formula in the entity cache if it is enabled.
	 *
	 * @param forecastingFormula the forecasting formula
	 */
	@Override
	public void cacheResult(ForecastingFormula forecastingFormula) {
		entityCache.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey(),
			forecastingFormula);

		forecastingFormula.resetOriginalValues();
	}

	/**
	 * Caches the forecasting formulas in the entity cache if it is enabled.
	 *
	 * @param forecastingFormulas the forecasting formulas
	 */
	@Override
	public void cacheResult(List<ForecastingFormula> forecastingFormulas) {
		for (ForecastingFormula forecastingFormula : forecastingFormulas) {
			if (entityCache.getResult(
						ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
						ForecastingFormulaImpl.class,
						forecastingFormula.getPrimaryKey()) == null) {
				cacheResult(forecastingFormula);
			}
			else {
				forecastingFormula.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all forecasting formulas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ForecastingFormulaImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the forecasting formula.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ForecastingFormula forecastingFormula) {
		entityCache.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ForecastingFormula> forecastingFormulas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ForecastingFormula forecastingFormula : forecastingFormulas) {
			entityCache.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
				ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey());
		}
	}

	/**
	 * Creates a new forecasting formula with the primary key. Does not add the forecasting formula to the database.
	 *
	 * @param forecastingFormulaSid the primary key for the new forecasting formula
	 * @return the new forecasting formula
	 */
	@Override
	public ForecastingFormula create(int forecastingFormulaSid) {
		ForecastingFormula forecastingFormula = new ForecastingFormulaImpl();

		forecastingFormula.setNew(true);
		forecastingFormula.setPrimaryKey(forecastingFormulaSid);

		return forecastingFormula;
	}

	/**
	 * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param forecastingFormulaSid the primary key of the forecasting formula
	 * @return the forecasting formula that was removed
	 * @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula remove(int forecastingFormulaSid)
		throws NoSuchForecastingFormulaException {
		return remove((Serializable)forecastingFormulaSid);
	}

	/**
	 * Removes the forecasting formula with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the forecasting formula
	 * @return the forecasting formula that was removed
	 * @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula remove(Serializable primaryKey)
		throws NoSuchForecastingFormulaException {
		Session session = null;

		try {
			session = openSession();

			ForecastingFormula forecastingFormula = (ForecastingFormula)session.get(ForecastingFormulaImpl.class,
					primaryKey);

			if (forecastingFormula == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchForecastingFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(forecastingFormula);
		}
		catch (NoSuchForecastingFormulaException nsee) {
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
	protected ForecastingFormula removeImpl(
		ForecastingFormula forecastingFormula) {
		forecastingFormula = toUnwrappedModel(forecastingFormula);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(forecastingFormula)) {
				forecastingFormula = (ForecastingFormula)session.get(ForecastingFormulaImpl.class,
						forecastingFormula.getPrimaryKeyObj());
			}

			if (forecastingFormula != null) {
				session.delete(forecastingFormula);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (forecastingFormula != null) {
			clearCache(forecastingFormula);
		}

		return forecastingFormula;
	}

	@Override
	public ForecastingFormula updateImpl(ForecastingFormula forecastingFormula) {
		forecastingFormula = toUnwrappedModel(forecastingFormula);

		boolean isNew = forecastingFormula.isNew();

		Session session = null;

		try {
			session = openSession();

			if (forecastingFormula.isNew()) {
				session.save(forecastingFormula);

				forecastingFormula.setNew(false);
			}
			else {
				forecastingFormula = (ForecastingFormula)session.merge(forecastingFormula);
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

		entityCache.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
			ForecastingFormulaImpl.class, forecastingFormula.getPrimaryKey(),
			forecastingFormula, false);

		forecastingFormula.resetOriginalValues();

		return forecastingFormula;
	}

	protected ForecastingFormula toUnwrappedModel(
		ForecastingFormula forecastingFormula) {
		if (forecastingFormula instanceof ForecastingFormulaImpl) {
			return forecastingFormula;
		}

		ForecastingFormulaImpl forecastingFormulaImpl = new ForecastingFormulaImpl();

		forecastingFormulaImpl.setNew(forecastingFormula.isNew());
		forecastingFormulaImpl.setPrimaryKey(forecastingFormula.getPrimaryKey());

		forecastingFormulaImpl.setCreatedDate(forecastingFormula.getCreatedDate());
		forecastingFormulaImpl.setFormulaType(forecastingFormula.getFormulaType());
		forecastingFormulaImpl.setForecastingFormulaSid(forecastingFormula.getForecastingFormulaSid());
		forecastingFormulaImpl.setFormula(forecastingFormula.getFormula());
		forecastingFormulaImpl.setFormulaNo(forecastingFormula.getFormulaNo());
		forecastingFormulaImpl.setModifiedDate(forecastingFormula.getModifiedDate());
		forecastingFormulaImpl.setIsActive(forecastingFormula.isIsActive());
		forecastingFormulaImpl.setFormulaName(forecastingFormula.getFormulaName());

		return forecastingFormulaImpl;
	}

	/**
	 * Returns the forecasting formula with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecasting formula
	 * @return the forecasting formula
	 * @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula findByPrimaryKey(Serializable primaryKey)
		throws NoSuchForecastingFormulaException {
		ForecastingFormula forecastingFormula = fetchByPrimaryKey(primaryKey);

		if (forecastingFormula == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchForecastingFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return forecastingFormula;
	}

	/**
	 * Returns the forecasting formula with the primary key or throws a {@link NoSuchForecastingFormulaException} if it could not be found.
	 *
	 * @param forecastingFormulaSid the primary key of the forecasting formula
	 * @return the forecasting formula
	 * @throws NoSuchForecastingFormulaException if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula findByPrimaryKey(int forecastingFormulaSid)
		throws NoSuchForecastingFormulaException {
		return findByPrimaryKey((Serializable)forecastingFormulaSid);
	}

	/**
	 * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the forecasting formula
	 * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
				ForecastingFormulaImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ForecastingFormula forecastingFormula = (ForecastingFormula)serializable;

		if (forecastingFormula == null) {
			Session session = null;

			try {
				session = openSession();

				forecastingFormula = (ForecastingFormula)session.get(ForecastingFormulaImpl.class,
						primaryKey);

				if (forecastingFormula != null) {
					cacheResult(forecastingFormula);
				}
				else {
					entityCache.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
						ForecastingFormulaImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingFormulaImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return forecastingFormula;
	}

	/**
	 * Returns the forecasting formula with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param forecastingFormulaSid the primary key of the forecasting formula
	 * @return the forecasting formula, or <code>null</code> if a forecasting formula with the primary key could not be found
	 */
	@Override
	public ForecastingFormula fetchByPrimaryKey(int forecastingFormulaSid) {
		return fetchByPrimaryKey((Serializable)forecastingFormulaSid);
	}

	@Override
	public Map<Serializable, ForecastingFormula> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ForecastingFormula> map = new HashMap<Serializable, ForecastingFormula>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ForecastingFormula forecastingFormula = fetchByPrimaryKey(primaryKey);

			if (forecastingFormula != null) {
				map.put(primaryKey, forecastingFormula);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingFormulaImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ForecastingFormula)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FORECASTINGFORMULA_WHERE_PKS_IN);

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

			for (ForecastingFormula forecastingFormula : (List<ForecastingFormula>)q.list()) {
				map.put(forecastingFormula.getPrimaryKeyObj(),
					forecastingFormula);

				cacheResult(forecastingFormula);

				uncachedPrimaryKeys.remove(forecastingFormula.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ForecastingFormulaModelImpl.ENTITY_CACHE_ENABLED,
					ForecastingFormulaImpl.class, primaryKey, nullModel);
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
	 * Returns all the forecasting formulas.
	 *
	 * @return the forecasting formulas
	 */
	@Override
	public List<ForecastingFormula> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the forecasting formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting formulas
	 * @param end the upper bound of the range of forecasting formulas (not inclusive)
	 * @return the range of forecasting formulas
	 */
	@Override
	public List<ForecastingFormula> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the forecasting formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting formulas
	 * @param end the upper bound of the range of forecasting formulas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of forecasting formulas
	 */
	@Override
	public List<ForecastingFormula> findAll(int start, int end,
		OrderByComparator<ForecastingFormula> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the forecasting formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ForecastingFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of forecasting formulas
	 * @param end the upper bound of the range of forecasting formulas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of forecasting formulas
	 */
	@Override
	public List<ForecastingFormula> findAll(int start, int end,
		OrderByComparator<ForecastingFormula> orderByComparator,
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

		List<ForecastingFormula> list = null;

		if (retrieveFromCache) {
			list = (List<ForecastingFormula>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FORECASTINGFORMULA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FORECASTINGFORMULA;

				if (pagination) {
					sql = sql.concat(ForecastingFormulaModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ForecastingFormula>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ForecastingFormula>)QueryUtil.list(q,
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
	 * Removes all the forecasting formulas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ForecastingFormula forecastingFormula : findAll()) {
			remove(forecastingFormula);
		}
	}

	/**
	 * Returns the number of forecasting formulas.
	 *
	 * @return the number of forecasting formulas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FORECASTINGFORMULA);

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
		return ForecastingFormulaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the forecasting formula persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ForecastingFormulaImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FORECASTINGFORMULA = "SELECT forecastingFormula FROM ForecastingFormula forecastingFormula";
	private static final String _SQL_SELECT_FORECASTINGFORMULA_WHERE_PKS_IN = "SELECT forecastingFormula FROM ForecastingFormula forecastingFormula WHERE FORECASTING_FORMULA_SID IN (";
	private static final String _SQL_COUNT_FORECASTINGFORMULA = "SELECT COUNT(forecastingFormula) FROM ForecastingFormula forecastingFormula";
	private static final String _ORDER_BY_ENTITY_ALIAS = "forecastingFormula.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ForecastingFormula exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ForecastingFormulaPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdDate", "formulaType", "forecastingFormulaSid", "formula",
				"formulaNo", "modifiedDate", "isActive", "formulaName"
			});
}