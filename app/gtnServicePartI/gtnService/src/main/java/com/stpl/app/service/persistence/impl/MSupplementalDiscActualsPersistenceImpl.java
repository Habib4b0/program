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

import com.stpl.app.exception.NoSuchMSupplementalDiscActualsException;
import com.stpl.app.model.MSupplementalDiscActuals;
import com.stpl.app.model.impl.MSupplementalDiscActualsImpl;
import com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPK;
import com.stpl.app.service.persistence.MSupplementalDiscActualsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the m supplemental disc actuals service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscActualsPersistence
 * @see com.stpl.app.service.persistence.MSupplementalDiscActualsUtil
 * @generated
 */
@ProviderType
public class MSupplementalDiscActualsPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscActuals>
	implements MSupplementalDiscActualsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSupplementalDiscActualsUtil} to access the m supplemental disc actuals persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscActualsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscActualsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MSupplementalDiscActualsPersistenceImpl() {
		setModelClass(MSupplementalDiscActuals.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualSales", "ACTUAL_SALES");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("actualRate", "ACTUAL_RATE");
			dbColumnNames.put("actualProjectionSales", "ACTUAL_PROJECTION_SALES");
			dbColumnNames.put("actualProjectionRate", "ACTUAL_PROJECTION_RATE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the m supplemental disc actuals in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscActuals the m supplemental disc actuals
	 */
	@Override
	public void cacheResult(MSupplementalDiscActuals mSupplementalDiscActuals) {
		entityCache.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsImpl.class,
			mSupplementalDiscActuals.getPrimaryKey(), mSupplementalDiscActuals);

		mSupplementalDiscActuals.resetOriginalValues();
	}

	/**
	 * Caches the m supplemental disc actualses in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscActualses the m supplemental disc actualses
	 */
	@Override
	public void cacheResult(
		List<MSupplementalDiscActuals> mSupplementalDiscActualses) {
		for (MSupplementalDiscActuals mSupplementalDiscActuals : mSupplementalDiscActualses) {
			if (entityCache.getResult(
						MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscActualsImpl.class,
						mSupplementalDiscActuals.getPrimaryKey()) == null) {
				cacheResult(mSupplementalDiscActuals);
			}
			else {
				mSupplementalDiscActuals.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m supplemental disc actualses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSupplementalDiscActualsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m supplemental disc actuals.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSupplementalDiscActuals mSupplementalDiscActuals) {
		entityCache.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsImpl.class,
			mSupplementalDiscActuals.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<MSupplementalDiscActuals> mSupplementalDiscActualses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSupplementalDiscActuals mSupplementalDiscActuals : mSupplementalDiscActualses) {
			entityCache.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscActualsImpl.class,
				mSupplementalDiscActuals.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
	 *
	 * @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
	 * @return the new m supplemental disc actuals
	 */
	@Override
	public MSupplementalDiscActuals create(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		MSupplementalDiscActuals mSupplementalDiscActuals = new MSupplementalDiscActualsImpl();

		mSupplementalDiscActuals.setNew(true);
		mSupplementalDiscActuals.setPrimaryKey(mSupplementalDiscActualsPK);

		return mSupplementalDiscActuals;
	}

	/**
	 * Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals that was removed
	 * @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals remove(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws NoSuchMSupplementalDiscActualsException {
		return remove((Serializable)mSupplementalDiscActualsPK);
	}

	/**
	 * Removes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals that was removed
	 * @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals remove(Serializable primaryKey)
		throws NoSuchMSupplementalDiscActualsException {
		Session session = null;

		try {
			session = openSession();

			MSupplementalDiscActuals mSupplementalDiscActuals = (MSupplementalDiscActuals)session.get(MSupplementalDiscActualsImpl.class,
					primaryKey);

			if (mSupplementalDiscActuals == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mSupplementalDiscActuals);
		}
		catch (NoSuchMSupplementalDiscActualsException nsee) {
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
	protected MSupplementalDiscActuals removeImpl(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		mSupplementalDiscActuals = toUnwrappedModel(mSupplementalDiscActuals);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mSupplementalDiscActuals)) {
				mSupplementalDiscActuals = (MSupplementalDiscActuals)session.get(MSupplementalDiscActualsImpl.class,
						mSupplementalDiscActuals.getPrimaryKeyObj());
			}

			if (mSupplementalDiscActuals != null) {
				session.delete(mSupplementalDiscActuals);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mSupplementalDiscActuals != null) {
			clearCache(mSupplementalDiscActuals);
		}

		return mSupplementalDiscActuals;
	}

	@Override
	public MSupplementalDiscActuals updateImpl(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		mSupplementalDiscActuals = toUnwrappedModel(mSupplementalDiscActuals);

		boolean isNew = mSupplementalDiscActuals.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mSupplementalDiscActuals.isNew()) {
				session.save(mSupplementalDiscActuals);

				mSupplementalDiscActuals.setNew(false);
			}
			else {
				mSupplementalDiscActuals = (MSupplementalDiscActuals)session.merge(mSupplementalDiscActuals);
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

		entityCache.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscActualsImpl.class,
			mSupplementalDiscActuals.getPrimaryKey(), mSupplementalDiscActuals,
			false);

		mSupplementalDiscActuals.resetOriginalValues();

		return mSupplementalDiscActuals;
	}

	protected MSupplementalDiscActuals toUnwrappedModel(
		MSupplementalDiscActuals mSupplementalDiscActuals) {
		if (mSupplementalDiscActuals instanceof MSupplementalDiscActualsImpl) {
			return mSupplementalDiscActuals;
		}

		MSupplementalDiscActualsImpl mSupplementalDiscActualsImpl = new MSupplementalDiscActualsImpl();

		mSupplementalDiscActualsImpl.setNew(mSupplementalDiscActuals.isNew());
		mSupplementalDiscActualsImpl.setPrimaryKey(mSupplementalDiscActuals.getPrimaryKey());

		mSupplementalDiscActualsImpl.setActualSales(mSupplementalDiscActuals.getActualSales());
		mSupplementalDiscActualsImpl.setPeriodSid(mSupplementalDiscActuals.getPeriodSid());
		mSupplementalDiscActualsImpl.setActualRate(mSupplementalDiscActuals.getActualRate());
		mSupplementalDiscActualsImpl.setActualProjectionSales(mSupplementalDiscActuals.getActualProjectionSales());
		mSupplementalDiscActualsImpl.setActualProjectionRate(mSupplementalDiscActuals.getActualProjectionRate());
		mSupplementalDiscActualsImpl.setProjectionDetailsSid(mSupplementalDiscActuals.getProjectionDetailsSid());

		return mSupplementalDiscActualsImpl;
	}

	/**
	 * Returns the m supplemental disc actuals with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals
	 * @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMSupplementalDiscActualsException {
		MSupplementalDiscActuals mSupplementalDiscActuals = fetchByPrimaryKey(primaryKey);

		if (mSupplementalDiscActuals == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMSupplementalDiscActualsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mSupplementalDiscActuals;
	}

	/**
	 * Returns the m supplemental disc actuals with the primary key or throws a {@link NoSuchMSupplementalDiscActualsException} if it could not be found.
	 *
	 * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals
	 * @throws NoSuchMSupplementalDiscActualsException if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals findByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
		throws NoSuchMSupplementalDiscActualsException {
		return findByPrimaryKey((Serializable)mSupplementalDiscActualsPK);
	}

	/**
	 * Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscActualsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSupplementalDiscActuals mSupplementalDiscActuals = (MSupplementalDiscActuals)serializable;

		if (mSupplementalDiscActuals == null) {
			Session session = null;

			try {
				session = openSession();

				mSupplementalDiscActuals = (MSupplementalDiscActuals)session.get(MSupplementalDiscActualsImpl.class,
						primaryKey);

				if (mSupplementalDiscActuals != null) {
					cacheResult(mSupplementalDiscActuals);
				}
				else {
					entityCache.putResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscActualsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSupplementalDiscActualsModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscActualsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mSupplementalDiscActuals;
	}

	/**
	 * Returns the m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
	 * @return the m supplemental disc actuals, or <code>null</code> if a m supplemental disc actuals with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscActuals fetchByPrimaryKey(
		MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
		return fetchByPrimaryKey((Serializable)mSupplementalDiscActualsPK);
	}

	@Override
	public Map<Serializable, MSupplementalDiscActuals> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSupplementalDiscActuals> map = new HashMap<Serializable, MSupplementalDiscActuals>();

		for (Serializable primaryKey : primaryKeys) {
			MSupplementalDiscActuals mSupplementalDiscActuals = fetchByPrimaryKey(primaryKey);

			if (mSupplementalDiscActuals != null) {
				map.put(primaryKey, mSupplementalDiscActuals);
			}
		}

		return map;
	}

	/**
	 * Returns all the m supplemental disc actualses.
	 *
	 * @return the m supplemental disc actualses
	 */
	@Override
	public List<MSupplementalDiscActuals> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc actualses
	 * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	 * @return the range of m supplemental disc actualses
	 */
	@Override
	public List<MSupplementalDiscActuals> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc actualses
	 * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m supplemental disc actualses
	 */
	@Override
	public List<MSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscActuals> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc actualses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc actualses
	 * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m supplemental disc actualses
	 */
	@Override
	public List<MSupplementalDiscActuals> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscActuals> orderByComparator,
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

		List<MSupplementalDiscActuals> list = null;

		if (retrieveFromCache) {
			list = (List<MSupplementalDiscActuals>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSUPPLEMENTALDISCACTUALS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSUPPLEMENTALDISCACTUALS;

				if (pagination) {
					sql = sql.concat(MSupplementalDiscActualsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSupplementalDiscActuals>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSupplementalDiscActuals>)QueryUtil.list(q,
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
	 * Removes all the m supplemental disc actualses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSupplementalDiscActuals mSupplementalDiscActuals : findAll()) {
			remove(mSupplementalDiscActuals);
		}
	}

	/**
	 * Returns the number of m supplemental disc actualses.
	 *
	 * @return the number of m supplemental disc actualses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCACTUALS);

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
		return MSupplementalDiscActualsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m supplemental disc actuals persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSupplementalDiscActualsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MSUPPLEMENTALDISCACTUALS = "SELECT mSupplementalDiscActuals FROM MSupplementalDiscActuals mSupplementalDiscActuals";
	private static final String _SQL_COUNT_MSUPPLEMENTALDISCACTUALS = "SELECT COUNT(mSupplementalDiscActuals) FROM MSupplementalDiscActuals mSupplementalDiscActuals";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscActuals.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscActuals exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MSupplementalDiscActualsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualSales", "periodSid", "actualRate",
				"actualProjectionSales", "actualProjectionRate",
				"projectionDetailsSid"
			});
}