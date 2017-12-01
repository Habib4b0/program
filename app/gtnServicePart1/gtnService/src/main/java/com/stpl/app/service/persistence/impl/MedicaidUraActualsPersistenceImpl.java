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

import com.stpl.app.exception.NoSuchMedicaidUraActualsException;
import com.stpl.app.model.MedicaidUraActuals;
import com.stpl.app.model.impl.MedicaidUraActualsImpl;
import com.stpl.app.model.impl.MedicaidUraActualsModelImpl;
import com.stpl.app.service.persistence.MedicaidUraActualsPK;
import com.stpl.app.service.persistence.MedicaidUraActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the medicaid ura actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraActualsPersistence
 * @see com.stpl.app.service.persistence.MedicaidUraActualsUtil
 * @generated
 */
@ProviderType
public class MedicaidUraActualsPersistenceImpl extends BasePersistenceImpl<MedicaidUraActuals>
	implements MedicaidUraActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MedicaidUraActualsUtil} to access the medicaid ura actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MedicaidUraActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED,
			MedicaidUraActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED,
			MedicaidUraActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MedicaidUraActualsPersistenceImpl() {
		setModelClass(MedicaidUraActuals.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("actualPrice", "ACTUAL_PRICE");
			dbColumnNames.put("notes", "NOTES");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");
			dbColumnNames.put("baseYear", "BASE_YEAR");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the medicaid ura actuals in the entity cache if it is enabled.
	 *
	 * @param medicaidUraActuals the medicaid ura actuals
	 */
	@Override
	public void cacheResult(MedicaidUraActuals medicaidUraActuals) {
		entityCache.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey(),
			medicaidUraActuals);

		medicaidUraActuals.resetOriginalValues();
	}

	/**
	 * Caches the medicaid ura actualses in the entity cache if it is enabled.
	 *
	 * @param medicaidUraActualses the medicaid ura actualses
	 */
	@Override
	public void cacheResult(List<MedicaidUraActuals> medicaidUraActualses) {
		for (MedicaidUraActuals medicaidUraActuals : medicaidUraActualses) {
			if (entityCache.getResult(
						MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidUraActualsImpl.class,
						medicaidUraActuals.getPrimaryKey()) == null) {
				cacheResult(medicaidUraActuals);
			}
			else {
				medicaidUraActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all medicaid ura actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MedicaidUraActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the medicaid ura actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MedicaidUraActuals medicaidUraActuals) {
		entityCache.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MedicaidUraActuals> medicaidUraActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MedicaidUraActuals medicaidUraActuals : medicaidUraActualses) {
			entityCache.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
	 *
	 * @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
	 * @return the new medicaid ura actuals
	 */
	@Override
	public MedicaidUraActuals create(MedicaidUraActualsPK medicaidUraActualsPK) {
		MedicaidUraActuals medicaidUraActuals = new MedicaidUraActualsImpl();

		medicaidUraActuals.setNew(true);
		medicaidUraActuals.setPrimaryKey(medicaidUraActualsPK);

		return medicaidUraActuals;
	}

	/**
	 * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals that was removed
	 * @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals remove(MedicaidUraActualsPK medicaidUraActualsPK)
		throws NoSuchMedicaidUraActualsException {
		return remove((Serializable)medicaidUraActualsPK);
	}

	/**
	 * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals that was removed
	 * @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals remove(Serializable primaryKey)
		throws NoSuchMedicaidUraActualsException {
		Session session = null;

		try {
			session = openSession();

			MedicaidUraActuals medicaidUraActuals = (MedicaidUraActuals)session.get(MedicaidUraActualsImpl.class,
					primaryKey);

			if (medicaidUraActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMedicaidUraActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(medicaidUraActuals);
		}
		catch (NoSuchMedicaidUraActualsException nsee) {
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
	protected MedicaidUraActuals removeImpl(
		MedicaidUraActuals medicaidUraActuals) {
		medicaidUraActuals = toUnwrappedModel(medicaidUraActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(medicaidUraActuals)) {
				medicaidUraActuals = (MedicaidUraActuals)session.get(MedicaidUraActualsImpl.class,
						medicaidUraActuals.getPrimaryKeyObj());
			}

			if (medicaidUraActuals != null) {
				session.delete(medicaidUraActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (medicaidUraActuals != null) {
			clearCache(medicaidUraActuals);
		}

		return medicaidUraActuals;
	}

	@Override
	public MedicaidUraActuals updateImpl(MedicaidUraActuals medicaidUraActuals) {
		medicaidUraActuals = toUnwrappedModel(medicaidUraActuals);

		boolean isNew = medicaidUraActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (medicaidUraActuals.isNew()) {
				session.save(medicaidUraActuals);

				medicaidUraActuals.setNew(false);
			}
			else {
				medicaidUraActuals = (MedicaidUraActuals)session.merge(medicaidUraActuals);
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

		entityCache.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
			MedicaidUraActualsImpl.class, medicaidUraActuals.getPrimaryKey(),
			medicaidUraActuals, false);

		medicaidUraActuals.resetOriginalValues();

		return medicaidUraActuals;
	}

	protected MedicaidUraActuals toUnwrappedModel(
		MedicaidUraActuals medicaidUraActuals) {
		if (medicaidUraActuals instanceof MedicaidUraActualsImpl) {
			return medicaidUraActuals;
		}

		MedicaidUraActualsImpl medicaidUraActualsImpl = new MedicaidUraActualsImpl();

		medicaidUraActualsImpl.setNew(medicaidUraActuals.isNew());
		medicaidUraActualsImpl.setPrimaryKey(medicaidUraActuals.getPrimaryKey());

		medicaidUraActualsImpl.setPeriodSid(medicaidUraActuals.getPeriodSid());
		medicaidUraActualsImpl.setPriceType(medicaidUraActuals.getPriceType());
		medicaidUraActualsImpl.setActualPrice(medicaidUraActuals.getActualPrice());
		medicaidUraActualsImpl.setNotes(medicaidUraActuals.getNotes());
		medicaidUraActualsImpl.setNaProjDetailsSid(medicaidUraActuals.getNaProjDetailsSid());
		medicaidUraActualsImpl.setBaseYear(medicaidUraActuals.getBaseYear());

		return medicaidUraActualsImpl;
	}

	/**
	 * Returns the medicaid ura actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals
	 * @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMedicaidUraActualsException {
		MedicaidUraActuals medicaidUraActuals = fetchByPrimaryKey(primaryKey);

		if (medicaidUraActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMedicaidUraActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return medicaidUraActuals;
	}

	/**
	 * Returns the medicaid ura actuals with the primary key or throws a {@link NoSuchMedicaidUraActualsException} if it could not be found.
	 *
	 * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals
	 * @throws NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals findByPrimaryKey(
		MedicaidUraActualsPK medicaidUraActualsPK)
		throws NoSuchMedicaidUraActualsException {
		return findByPrimaryKey((Serializable)medicaidUraActualsPK);
	}

	/**
	 * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
				MedicaidUraActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MedicaidUraActuals medicaidUraActuals = (MedicaidUraActuals)serializable;

		if (medicaidUraActuals == null) {
			Session session = null;

			try {
				session = openSession();

				medicaidUraActuals = (MedicaidUraActuals)session.get(MedicaidUraActualsImpl.class,
						primaryKey);

				if (medicaidUraActuals != null) {
					cacheResult(medicaidUraActuals);
				}
				else {
					entityCache.putResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
						MedicaidUraActualsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MedicaidUraActualsModelImpl.ENTITY_CACHE_ENABLED,
					MedicaidUraActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return medicaidUraActuals;
	}

	/**
	 * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
	 * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
	 */
	@Override
	public MedicaidUraActuals fetchByPrimaryKey(
		MedicaidUraActualsPK medicaidUraActualsPK) {
		return fetchByPrimaryKey((Serializable)medicaidUraActualsPK);
	}

	@Override
	public Map<Serializable, MedicaidUraActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MedicaidUraActuals> map = new HashMap<Serializable, MedicaidUraActuals>();

		for (Serializable primaryKey : primaryKeys) {
			MedicaidUraActuals medicaidUraActuals = fetchByPrimaryKey(primaryKey);

			if (medicaidUraActuals != null) {
				map.put(primaryKey, medicaidUraActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the medicaid ura actualses.
	 *
	 * @return the medicaid ura actualses
	 */
	@Override
	public List<MedicaidUraActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the medicaid ura actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura actualses
	 * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	 * @return the range of medicaid ura actualses
	 */
	@Override
	public List<MedicaidUraActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the medicaid ura actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura actualses
	 * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of medicaid ura actualses
	 */
	@Override
	public List<MedicaidUraActuals> findAll(int start, int end,
		OrderByComparator<MedicaidUraActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the medicaid ura actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of medicaid ura actualses
	 * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of medicaid ura actualses
	 */
	@Override
	public List<MedicaidUraActuals> findAll(int start, int end,
		OrderByComparator<MedicaidUraActuals> orderByComparator,
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

		List<MedicaidUraActuals> list = null;

		if (retrieveFromCache) {
			list = (List<MedicaidUraActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEDICAIDURAACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEDICAIDURAACTUALS;

				if (pagination) {
					sql = sql.concat(MedicaidUraActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MedicaidUraActuals>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MedicaidUraActuals>)QueryUtil.list(q,
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
	 * Removes all the medicaid ura actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MedicaidUraActuals medicaidUraActuals : findAll()) {
			remove(medicaidUraActuals);
		}
	}

	/**
	 * Returns the number of medicaid ura actualses.
	 *
	 * @return the number of medicaid ura actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEDICAIDURAACTUALS);

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
		return MedicaidUraActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the medicaid ura actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MedicaidUraActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MEDICAIDURAACTUALS = "SELECT medicaidUraActuals FROM MedicaidUraActuals medicaidUraActuals";
	private static final String _SQL_COUNT_MEDICAIDURAACTUALS = "SELECT COUNT(medicaidUraActuals) FROM MedicaidUraActuals medicaidUraActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "medicaidUraActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MedicaidUraActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MedicaidUraActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "priceType", "actualPrice", "notes",
				"naProjDetailsSid", "baseYear"
			});
}