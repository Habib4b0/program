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
import com.liferay.portal.kernel.dao.orm.QueryPos;
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

import com.stpl.app.exception.NoSuchMedicaidNewNdcException;
import com.stpl.app.model.MedicaidNewNdc;
import com.stpl.app.model.impl.MedicaidNewNdcImpl;
import com.stpl.app.model.impl.MedicaidNewNdcModelImpl;
import com.stpl.app.service.persistence.MedicaidNewNdcPersistence;

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
 * The persistence implementation for the medicaid new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidNewNdcPersistence
 * @see com.stpl.app.service.persistence.MedicaidNewNdcUtil
 * @generated
 */
@ProviderType
public class MedicaidNewNdcPersistenceImpl extends BasePersistenceImpl<MedicaidNewNdc>
	implements MedicaidNewNdcPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MedicaidNewNdcUtil} to access the medicaid new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MedicaidNewNdcImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
			MedicaidNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED,
			MedicaidNewNdcImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MedicaidNewNdcPersistenceImpl() {
		setModelClass(MedicaidNewNdc.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("forecastAmp", "FORECAST_AMP");
			dbColumnNames.put("forecastBestprice", "FORECAST_BESTPRICE");
			dbColumnNames.put("baseYearCpi", "BASE_YEAR_CPI");
			dbColumnNames.put("ndc9", "NDC9");
			dbColumnNames.put("wacPrice", "WAC_PRICE");
			dbColumnNames.put("baseYearAmp", "BASE_YEAR_AMP");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the medicaid new ndc in the entity cache if it is enabled.
	 *
	 * @param medicaidNewNdc the medicaid new ndc
	 */
	@Override
	public void cacheResult(MedicaidNewNdc medicaidNewNdc) {
		entityCache.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey(),
			medicaidNewNdc);

		medicaidNewNdc.resetOriginalValues();
	}

	/**
	 * Caches the medicaid new ndcs in the entity cache if it is enabled.
	 *
	 * @param medicaidNewNdcs the medicaid new ndcs
	 */
	@Override
	public void cacheResult(List<MedicaidNewNdc> medicaidNewNdcs) {
		for (MedicaidNewNdc medicaidNewNdc : medicaidNewNdcs) {
			if (entityCache.getResult(
						MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey()) == null) {
				cacheResult(medicaidNewNdc);
			}
			else {
				medicaidNewNdc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all medicaid new ndcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MedicaidNewNdcImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the medicaid new ndc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MedicaidNewNdc medicaidNewNdc) {
		entityCache.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MedicaidNewNdc> medicaidNewNdcs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MedicaidNewNdc medicaidNewNdc : medicaidNewNdcs) {
			entityCache.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new medicaid new ndc with the primary key. Does not add the medicaid new ndc to the database.
	 *
	 * @param ndc9 the primary key for the new medicaid new ndc
	 * @return the new medicaid new ndc
	 */
	@Override
	public MedicaidNewNdc create(String ndc9) {
		MedicaidNewNdc medicaidNewNdc = new MedicaidNewNdcImpl();

		medicaidNewNdc.setNew(true);
		medicaidNewNdc.setPrimaryKey(ndc9);

		return medicaidNewNdc;
	}

	/**
	 * Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ndc9 the primary key of the medicaid new ndc
	 * @return the medicaid new ndc that was removed
	 * @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc remove(String ndc9)
		throws NoSuchMedicaidNewNdcException {
		return remove((Serializable)ndc9);
	}

	/**
	 * Removes the medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the medicaid new ndc
	 * @return the medicaid new ndc that was removed
	 * @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc remove(Serializable primaryKey)
		throws NoSuchMedicaidNewNdcException {
		Session session = null;

		try {
			session = openSession();

			MedicaidNewNdc medicaidNewNdc = (MedicaidNewNdc)session.get(MedicaidNewNdcImpl.class,
					primaryKey);

			if (medicaidNewNdc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(medicaidNewNdc);
		}
		catch (NoSuchMedicaidNewNdcException nsee) {
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
	protected MedicaidNewNdc removeImpl(MedicaidNewNdc medicaidNewNdc) {
		medicaidNewNdc = toUnwrappedModel(medicaidNewNdc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(medicaidNewNdc)) {
				medicaidNewNdc = (MedicaidNewNdc)session.get(MedicaidNewNdcImpl.class,
						medicaidNewNdc.getPrimaryKeyObj());
			}

			if (medicaidNewNdc != null) {
				session.delete(medicaidNewNdc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (medicaidNewNdc != null) {
			clearCache(medicaidNewNdc);
		}

		return medicaidNewNdc;
	}

	@Override
	public MedicaidNewNdc updateImpl(MedicaidNewNdc medicaidNewNdc) {
		medicaidNewNdc = toUnwrappedModel(medicaidNewNdc);

		boolean isNew = medicaidNewNdc.isNew();

		Session session = null;

		try {
			session = openSession();

			if (medicaidNewNdc.isNew()) {
				session.save(medicaidNewNdc);

				medicaidNewNdc.setNew(false);
			}
			else {
				medicaidNewNdc = (MedicaidNewNdc)session.merge(medicaidNewNdc);
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

		entityCache.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidNewNdcImpl.class, medicaidNewNdc.getPrimaryKey(),
			medicaidNewNdc, false);

		medicaidNewNdc.resetOriginalValues();

		return medicaidNewNdc;
	}

	protected MedicaidNewNdc toUnwrappedModel(MedicaidNewNdc medicaidNewNdc) {
		if (medicaidNewNdc instanceof MedicaidNewNdcImpl) {
			return medicaidNewNdc;
		}

		MedicaidNewNdcImpl medicaidNewNdcImpl = new MedicaidNewNdcImpl();

		medicaidNewNdcImpl.setNew(medicaidNewNdc.isNew());
		medicaidNewNdcImpl.setPrimaryKey(medicaidNewNdc.getPrimaryKey());

		medicaidNewNdcImpl.setForecastAmp(medicaidNewNdc.getForecastAmp());
		medicaidNewNdcImpl.setForecastBestprice(medicaidNewNdc.getForecastBestprice());
		medicaidNewNdcImpl.setBaseYearCpi(medicaidNewNdc.getBaseYearCpi());
		medicaidNewNdcImpl.setNdc9(medicaidNewNdc.getNdc9());
		medicaidNewNdcImpl.setWacPrice(medicaidNewNdc.getWacPrice());
		medicaidNewNdcImpl.setBaseYearAmp(medicaidNewNdc.getBaseYearAmp());

		return medicaidNewNdcImpl;
	}

	/**
	 * Returns the medicaid new ndc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid new ndc
	 * @return the medicaid new ndc
	 * @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMedicaidNewNdcException {
		MedicaidNewNdc medicaidNewNdc = fetchByPrimaryKey(primaryKey);

		if (medicaidNewNdc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMedicaidNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return medicaidNewNdc;
	}

	/**
	 * Returns the medicaid new ndc with the primary key or throws a {@link NoSuchMedicaidNewNdcException} if it could not be found.
	 *
	 * @param ndc9 the primary key of the medicaid new ndc
	 * @return the medicaid new ndc
	 * @throws NoSuchMedicaidNewNdcException if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc findByPrimaryKey(String ndc9)
		throws NoSuchMedicaidNewNdcException {
		return findByPrimaryKey((Serializable)ndc9);
	}

	/**
	 * Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid new ndc
	 * @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidNewNdcImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MedicaidNewNdc medicaidNewNdc = (MedicaidNewNdc)serializable;

		if (medicaidNewNdc == null) {
			Session session = null;

			try {
				session = openSession();

				medicaidNewNdc = (MedicaidNewNdc)session.get(MedicaidNewNdcImpl.class,
						primaryKey);

				if (medicaidNewNdc != null) {
					cacheResult(medicaidNewNdc);
				}
				else {
					entityCache.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidNewNdcImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					MedicaidNewNdcImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return medicaidNewNdc;
	}

	/**
	 * Returns the medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ndc9 the primary key of the medicaid new ndc
	 * @return the medicaid new ndc, or <code>null</code> if a medicaid new ndc with the primary key could not be found
	 */
	@Override
	public MedicaidNewNdc fetchByPrimaryKey(String ndc9) {
		return fetchByPrimaryKey((Serializable)ndc9);
	}

	@Override
	public Map<Serializable, MedicaidNewNdc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MedicaidNewNdc> map = new HashMap<Serializable, MedicaidNewNdc>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MedicaidNewNdc medicaidNewNdc = fetchByPrimaryKey(primaryKey);

			if (medicaidNewNdc != null) {
				map.put(primaryKey, medicaidNewNdc);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					MedicaidNewNdcImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MedicaidNewNdc)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MEDICAIDNEWNDC_WHERE_PKS_IN);

		for (int i = 0; i < uncachedPrimaryKeys.size(); i++) {
			query.append(StringPool.QUESTION);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				qPos.add((String)primaryKey);
			}

			for (MedicaidNewNdc medicaidNewNdc : (List<MedicaidNewNdc>)q.list()) {
				map.put(medicaidNewNdc.getPrimaryKeyObj(), medicaidNewNdc);

				cacheResult(medicaidNewNdc);

				uncachedPrimaryKeys.remove(medicaidNewNdc.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MedicaidNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					MedicaidNewNdcImpl.class, primaryKey, nullModel);
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
	 * Returns all the medicaid new ndcs.
	 *
	 * @return the medicaid new ndcs
	 */
	@Override
	public List<MedicaidNewNdc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid new ndcs
	 * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	 * @return the range of medicaid new ndcs
	 */
	@Override
	public List<MedicaidNewNdc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid new ndcs
	 * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of medicaid new ndcs
	 */
	@Override
	public List<MedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<MedicaidNewNdc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the medicaid new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid new ndcs
	 * @param end the upper bound of the range of medicaid new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of medicaid new ndcs
	 */
	@Override
	public List<MedicaidNewNdc> findAll(int start, int end,
		OrderByComparator<MedicaidNewNdc> orderByComparator,
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

		List<MedicaidNewNdc> list = null;

		if (retrieveFromCache) {
			list = (List<MedicaidNewNdc>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEDICAIDNEWNDC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEDICAIDNEWNDC;

				if (pagination) {
					sql = sql.concat(MedicaidNewNdcModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MedicaidNewNdc>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MedicaidNewNdc>)QueryUtil.list(q,
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
	 * Removes all the medicaid new ndcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MedicaidNewNdc medicaidNewNdc : findAll()) {
			remove(medicaidNewNdc);
		}
	}

	/**
	 * Returns the number of medicaid new ndcs.
	 *
	 * @return the number of medicaid new ndcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEDICAIDNEWNDC);

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
		return MedicaidNewNdcModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the medicaid new ndc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MedicaidNewNdcImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MEDICAIDNEWNDC = "SELECT medicaidNewNdc FROM MedicaidNewNdc medicaidNewNdc";
	private static final String _SQL_SELECT_MEDICAIDNEWNDC_WHERE_PKS_IN = "SELECT medicaidNewNdc FROM MedicaidNewNdc medicaidNewNdc WHERE NDC9 IN (";
	private static final String _SQL_COUNT_MEDICAIDNEWNDC = "SELECT COUNT(medicaidNewNdc) FROM MedicaidNewNdc medicaidNewNdc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidNewNdc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidNewNdc exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MedicaidNewNdcPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"forecastAmp", "forecastBestprice", "baseYearCpi", "ndc9",
				"wacPrice", "baseYearAmp"
			});
}