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

import com.stpl.app.exception.NoSuchNationalAssumptionsActualsException;
import com.stpl.app.model.NationalAssumptionsActuals;
import com.stpl.app.model.impl.NationalAssumptionsActualsImpl;
import com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPK;
import com.stpl.app.service.persistence.NationalAssumptionsActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the national assumptions actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsActualsPersistence
 * @see com.stpl.app.service.persistence.NationalAssumptionsActualsUtil
 * @generated
 */
@ProviderType
public class NationalAssumptionsActualsPersistenceImpl
	extends BasePersistenceImpl<NationalAssumptionsActuals>
	implements NationalAssumptionsActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NationalAssumptionsActualsUtil} to access the national assumptions actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NationalAssumptionsActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
			NationalAssumptionsActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public NationalAssumptionsActualsPersistenceImpl() {
		setModelClass(NationalAssumptionsActuals.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("priceType", "PRICE_TYPE");
			dbColumnNames.put("actualPrice", "ACTUAL_PRICE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the national assumptions actuals in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptionsActuals the national assumptions actuals
	 */
	@Override
	public void cacheResult(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		entityCache.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsImpl.class,
			nationalAssumptionsActuals.getPrimaryKey(),
			nationalAssumptionsActuals);

		nationalAssumptionsActuals.resetOriginalValues();
	}

	/**
	 * Caches the national assumptions actualses in the entity cache if it is enabled.
	 *
	 * @param nationalAssumptionsActualses the national assumptions actualses
	 */
	@Override
	public void cacheResult(
		List<NationalAssumptionsActuals> nationalAssumptionsActualses) {
		for (NationalAssumptionsActuals nationalAssumptionsActuals : nationalAssumptionsActualses) {
			if (entityCache.getResult(
						NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsActualsImpl.class,
						nationalAssumptionsActuals.getPrimaryKey()) == null) {
				cacheResult(nationalAssumptionsActuals);
			}
			else {
				nationalAssumptionsActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all national assumptions actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NationalAssumptionsActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the national assumptions actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		entityCache.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsImpl.class,
			nationalAssumptionsActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<NationalAssumptionsActuals> nationalAssumptionsActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NationalAssumptionsActuals nationalAssumptionsActuals : nationalAssumptionsActualses) {
			entityCache.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsActualsImpl.class,
				nationalAssumptionsActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
	 *
	 * @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
	 * @return the new national assumptions actuals
	 */
	@Override
	public NationalAssumptionsActuals create(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		NationalAssumptionsActuals nationalAssumptionsActuals = new NationalAssumptionsActualsImpl();

		nationalAssumptionsActuals.setNew(true);
		nationalAssumptionsActuals.setPrimaryKey(nationalAssumptionsActualsPK);

		return nationalAssumptionsActuals;
	}

	/**
	 * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	 * @return the national assumptions actuals that was removed
	 * @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals remove(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws NoSuchNationalAssumptionsActualsException {
		return remove((Serializable)nationalAssumptionsActualsPK);
	}

	/**
	 * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the national assumptions actuals
	 * @return the national assumptions actuals that was removed
	 * @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals remove(Serializable primaryKey)
		throws NoSuchNationalAssumptionsActualsException {
		Session session = null;

		try {
			session = openSession();

			NationalAssumptionsActuals nationalAssumptionsActuals = (NationalAssumptionsActuals)session.get(NationalAssumptionsActualsImpl.class,
					primaryKey);

			if (nationalAssumptionsActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNationalAssumptionsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(nationalAssumptionsActuals);
		}
		catch (NoSuchNationalAssumptionsActualsException nsee) {
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
	protected NationalAssumptionsActuals removeImpl(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		nationalAssumptionsActuals = toUnwrappedModel(nationalAssumptionsActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(nationalAssumptionsActuals)) {
				nationalAssumptionsActuals = (NationalAssumptionsActuals)session.get(NationalAssumptionsActualsImpl.class,
						nationalAssumptionsActuals.getPrimaryKeyObj());
			}

			if (nationalAssumptionsActuals != null) {
				session.delete(nationalAssumptionsActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (nationalAssumptionsActuals != null) {
			clearCache(nationalAssumptionsActuals);
		}

		return nationalAssumptionsActuals;
	}

	@Override
	public NationalAssumptionsActuals updateImpl(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		nationalAssumptionsActuals = toUnwrappedModel(nationalAssumptionsActuals);

		boolean isNew = nationalAssumptionsActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (nationalAssumptionsActuals.isNew()) {
				session.save(nationalAssumptionsActuals);

				nationalAssumptionsActuals.setNew(false);
			}
			else {
				nationalAssumptionsActuals = (NationalAssumptionsActuals)session.merge(nationalAssumptionsActuals);
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

		entityCache.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
			NationalAssumptionsActualsImpl.class,
			nationalAssumptionsActuals.getPrimaryKey(),
			nationalAssumptionsActuals, false);

		nationalAssumptionsActuals.resetOriginalValues();

		return nationalAssumptionsActuals;
	}

	protected NationalAssumptionsActuals toUnwrappedModel(
		NationalAssumptionsActuals nationalAssumptionsActuals) {
		if (nationalAssumptionsActuals instanceof NationalAssumptionsActualsImpl) {
			return nationalAssumptionsActuals;
		}

		NationalAssumptionsActualsImpl nationalAssumptionsActualsImpl = new NationalAssumptionsActualsImpl();

		nationalAssumptionsActualsImpl.setNew(nationalAssumptionsActuals.isNew());
		nationalAssumptionsActualsImpl.setPrimaryKey(nationalAssumptionsActuals.getPrimaryKey());

		nationalAssumptionsActualsImpl.setPeriodSid(nationalAssumptionsActuals.getPeriodSid());
		nationalAssumptionsActualsImpl.setItemMasterSid(nationalAssumptionsActuals.getItemMasterSid());
		nationalAssumptionsActualsImpl.setPriceType(nationalAssumptionsActuals.getPriceType());
		nationalAssumptionsActualsImpl.setActualPrice(nationalAssumptionsActuals.getActualPrice());

		return nationalAssumptionsActualsImpl;
	}

	/**
	 * Returns the national assumptions actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions actuals
	 * @return the national assumptions actuals
	 * @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNationalAssumptionsActualsException {
		NationalAssumptionsActuals nationalAssumptionsActuals = fetchByPrimaryKey(primaryKey);

		if (nationalAssumptionsActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNationalAssumptionsActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return nationalAssumptionsActuals;
	}

	/**
	 * Returns the national assumptions actuals with the primary key or throws a {@link NoSuchNationalAssumptionsActualsException} if it could not be found.
	 *
	 * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	 * @return the national assumptions actuals
	 * @throws NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals findByPrimaryKey(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
		throws NoSuchNationalAssumptionsActualsException {
		return findByPrimaryKey((Serializable)nationalAssumptionsActualsPK);
	}

	/**
	 * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the national assumptions actuals
	 * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
				NationalAssumptionsActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NationalAssumptionsActuals nationalAssumptionsActuals = (NationalAssumptionsActuals)serializable;

		if (nationalAssumptionsActuals == null) {
			Session session = null;

			try {
				session = openSession();

				nationalAssumptionsActuals = (NationalAssumptionsActuals)session.get(NationalAssumptionsActualsImpl.class,
						primaryKey);

				if (nationalAssumptionsActuals != null) {
					cacheResult(nationalAssumptionsActuals);
				}
				else {
					entityCache.putResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
						NationalAssumptionsActualsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NationalAssumptionsActualsModelImpl.ENTITY_CACHE_ENABLED,
					NationalAssumptionsActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return nationalAssumptionsActuals;
	}

	/**
	 * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
	 * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
	 */
	@Override
	public NationalAssumptionsActuals fetchByPrimaryKey(
		NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
		return fetchByPrimaryKey((Serializable)nationalAssumptionsActualsPK);
	}

	@Override
	public Map<Serializable, NationalAssumptionsActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NationalAssumptionsActuals> map = new HashMap<Serializable, NationalAssumptionsActuals>();

		for (Serializable primaryKey : primaryKeys) {
			NationalAssumptionsActuals nationalAssumptionsActuals = fetchByPrimaryKey(primaryKey);

			if (nationalAssumptionsActuals != null) {
				map.put(primaryKey, nationalAssumptionsActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the national assumptions actualses.
	 *
	 * @return the national assumptions actualses
	 */
	@Override
	public List<NationalAssumptionsActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the national assumptions actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions actualses
	 * @param end the upper bound of the range of national assumptions actualses (not inclusive)
	 * @return the range of national assumptions actualses
	 */
	@Override
	public List<NationalAssumptionsActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the national assumptions actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions actualses
	 * @param end the upper bound of the range of national assumptions actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of national assumptions actualses
	 */
	@Override
	public List<NationalAssumptionsActuals> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the national assumptions actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of national assumptions actualses
	 * @param end the upper bound of the range of national assumptions actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of national assumptions actualses
	 */
	@Override
	public List<NationalAssumptionsActuals> findAll(int start, int end,
		OrderByComparator<NationalAssumptionsActuals> orderByComparator,
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

		List<NationalAssumptionsActuals> list = null;

		if (retrieveFromCache) {
			list = (List<NationalAssumptionsActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NATIONALASSUMPTIONSACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NATIONALASSUMPTIONSACTUALS;

				if (pagination) {
					sql = sql.concat(NationalAssumptionsActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NationalAssumptionsActuals>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NationalAssumptionsActuals>)QueryUtil.list(q,
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
	 * Removes all the national assumptions actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NationalAssumptionsActuals nationalAssumptionsActuals : findAll()) {
			remove(nationalAssumptionsActuals);
		}
	}

	/**
	 * Returns the number of national assumptions actualses.
	 *
	 * @return the number of national assumptions actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NATIONALASSUMPTIONSACTUALS);

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
		return NationalAssumptionsActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the national assumptions actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NationalAssumptionsActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NATIONALASSUMPTIONSACTUALS = "SELECT nationalAssumptionsActuals FROM NationalAssumptionsActuals nationalAssumptionsActuals";
	private static final String _SQL_COUNT_NATIONALASSUMPTIONSACTUALS = "SELECT COUNT(nationalAssumptionsActuals) FROM NationalAssumptionsActuals nationalAssumptionsActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "nationalAssumptionsActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NationalAssumptionsActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NationalAssumptionsActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodSid", "itemMasterSid", "priceType", "actualPrice"
			});
}