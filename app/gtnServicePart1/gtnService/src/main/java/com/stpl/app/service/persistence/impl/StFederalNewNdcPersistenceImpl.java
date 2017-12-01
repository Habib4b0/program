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

import com.stpl.app.exception.NoSuchStFederalNewNdcException;
import com.stpl.app.model.StFederalNewNdc;
import com.stpl.app.model.impl.StFederalNewNdcImpl;
import com.stpl.app.model.impl.StFederalNewNdcModelImpl;
import com.stpl.app.service.persistence.StFederalNewNdcPK;
import com.stpl.app.service.persistence.StFederalNewNdcPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StFederalNewNdcPersistence
 * @see com.stpl.app.service.persistence.StFederalNewNdcUtil
 * @generated
 */
@ProviderType
public class StFederalNewNdcPersistenceImpl extends BasePersistenceImpl<StFederalNewNdc>
	implements StFederalNewNdcPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StFederalNewNdcUtil} to access the st federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StFederalNewNdcImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
			StFederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
			StFederalNewNdcImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StFederalNewNdcPersistenceImpl() {
		setModelClass(StFederalNewNdc.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("fss", "FSS");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("wacPrice", "WAC_PRICE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("nonFamp", "NON_FAMP");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st federal new ndc in the entity cache if it is enabled.
	 *
	 * @param stFederalNewNdc the st federal new ndc
	 */
	@Override
	public void cacheResult(StFederalNewNdc stFederalNewNdc) {
		entityCache.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey(),
			stFederalNewNdc);

		stFederalNewNdc.resetOriginalValues();
	}

	/**
	 * Caches the st federal new ndcs in the entity cache if it is enabled.
	 *
	 * @param stFederalNewNdcs the st federal new ndcs
	 */
	@Override
	public void cacheResult(List<StFederalNewNdc> stFederalNewNdcs) {
		for (StFederalNewNdc stFederalNewNdc : stFederalNewNdcs) {
			if (entityCache.getResult(
						StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StFederalNewNdcImpl.class,
						stFederalNewNdc.getPrimaryKey()) == null) {
				cacheResult(stFederalNewNdc);
			}
			else {
				stFederalNewNdc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st federal new ndcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StFederalNewNdcImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st federal new ndc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StFederalNewNdc stFederalNewNdc) {
		entityCache.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StFederalNewNdc> stFederalNewNdcs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StFederalNewNdc stFederalNewNdc : stFederalNewNdcs) {
			entityCache.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
	 *
	 * @param stFederalNewNdcPK the primary key for the new st federal new ndc
	 * @return the new st federal new ndc
	 */
	@Override
	public StFederalNewNdc create(StFederalNewNdcPK stFederalNewNdcPK) {
		StFederalNewNdc stFederalNewNdc = new StFederalNewNdcImpl();

		stFederalNewNdc.setNew(true);
		stFederalNewNdc.setPrimaryKey(stFederalNewNdcPK);

		return stFederalNewNdc;
	}

	/**
	 * Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stFederalNewNdcPK the primary key of the st federal new ndc
	 * @return the st federal new ndc that was removed
	 * @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc remove(StFederalNewNdcPK stFederalNewNdcPK)
		throws NoSuchStFederalNewNdcException {
		return remove((Serializable)stFederalNewNdcPK);
	}

	/**
	 * Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st federal new ndc
	 * @return the st federal new ndc that was removed
	 * @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc remove(Serializable primaryKey)
		throws NoSuchStFederalNewNdcException {
		Session session = null;

		try {
			session = openSession();

			StFederalNewNdc stFederalNewNdc = (StFederalNewNdc)session.get(StFederalNewNdcImpl.class,
					primaryKey);

			if (stFederalNewNdc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stFederalNewNdc);
		}
		catch (NoSuchStFederalNewNdcException nsee) {
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
	protected StFederalNewNdc removeImpl(StFederalNewNdc stFederalNewNdc) {
		stFederalNewNdc = toUnwrappedModel(stFederalNewNdc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stFederalNewNdc)) {
				stFederalNewNdc = (StFederalNewNdc)session.get(StFederalNewNdcImpl.class,
						stFederalNewNdc.getPrimaryKeyObj());
			}

			if (stFederalNewNdc != null) {
				session.delete(stFederalNewNdc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stFederalNewNdc != null) {
			clearCache(stFederalNewNdc);
		}

		return stFederalNewNdc;
	}

	@Override
	public StFederalNewNdc updateImpl(StFederalNewNdc stFederalNewNdc) {
		stFederalNewNdc = toUnwrappedModel(stFederalNewNdc);

		boolean isNew = stFederalNewNdc.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stFederalNewNdc.isNew()) {
				session.save(stFederalNewNdc);

				stFederalNewNdc.setNew(false);
			}
			else {
				stFederalNewNdc = (StFederalNewNdc)session.merge(stFederalNewNdc);
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

		entityCache.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			StFederalNewNdcImpl.class, stFederalNewNdc.getPrimaryKey(),
			stFederalNewNdc, false);

		stFederalNewNdc.resetOriginalValues();

		return stFederalNewNdc;
	}

	protected StFederalNewNdc toUnwrappedModel(StFederalNewNdc stFederalNewNdc) {
		if (stFederalNewNdc instanceof StFederalNewNdcImpl) {
			return stFederalNewNdc;
		}

		StFederalNewNdcImpl stFederalNewNdcImpl = new StFederalNewNdcImpl();

		stFederalNewNdcImpl.setNew(stFederalNewNdc.isNew());
		stFederalNewNdcImpl.setPrimaryKey(stFederalNewNdc.getPrimaryKey());

		stFederalNewNdcImpl.setFss(stFederalNewNdc.getFss());
		stFederalNewNdcImpl.setUserId(stFederalNewNdc.getUserId());
		stFederalNewNdcImpl.setLastModifiedDate(stFederalNewNdc.getLastModifiedDate());
		stFederalNewNdcImpl.setItemMasterSid(stFederalNewNdc.getItemMasterSid());
		stFederalNewNdcImpl.setWacPrice(stFederalNewNdc.getWacPrice());
		stFederalNewNdcImpl.setSessionId(stFederalNewNdc.getSessionId());
		stFederalNewNdcImpl.setNonFamp(stFederalNewNdc.getNonFamp());

		return stFederalNewNdcImpl;
	}

	/**
	 * Returns the st federal new ndc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st federal new ndc
	 * @return the st federal new ndc
	 * @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStFederalNewNdcException {
		StFederalNewNdc stFederalNewNdc = fetchByPrimaryKey(primaryKey);

		if (stFederalNewNdc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stFederalNewNdc;
	}

	/**
	 * Returns the st federal new ndc with the primary key or throws a {@link NoSuchStFederalNewNdcException} if it could not be found.
	 *
	 * @param stFederalNewNdcPK the primary key of the st federal new ndc
	 * @return the st federal new ndc
	 * @throws NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc findByPrimaryKey(StFederalNewNdcPK stFederalNewNdcPK)
		throws NoSuchStFederalNewNdcException {
		return findByPrimaryKey((Serializable)stFederalNewNdcPK);
	}

	/**
	 * Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st federal new ndc
	 * @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				StFederalNewNdcImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StFederalNewNdc stFederalNewNdc = (StFederalNewNdc)serializable;

		if (stFederalNewNdc == null) {
			Session session = null;

			try {
				session = openSession();

				stFederalNewNdc = (StFederalNewNdc)session.get(StFederalNewNdcImpl.class,
						primaryKey);

				if (stFederalNewNdc != null) {
					cacheResult(stFederalNewNdc);
				}
				else {
					entityCache.putResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						StFederalNewNdcImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StFederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					StFederalNewNdcImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stFederalNewNdc;
	}

	/**
	 * Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stFederalNewNdcPK the primary key of the st federal new ndc
	 * @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
	 */
	@Override
	public StFederalNewNdc fetchByPrimaryKey(
		StFederalNewNdcPK stFederalNewNdcPK) {
		return fetchByPrimaryKey((Serializable)stFederalNewNdcPK);
	}

	@Override
	public Map<Serializable, StFederalNewNdc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StFederalNewNdc> map = new HashMap<Serializable, StFederalNewNdc>();

		for (Serializable primaryKey : primaryKeys) {
			StFederalNewNdc stFederalNewNdc = fetchByPrimaryKey(primaryKey);

			if (stFederalNewNdc != null) {
				map.put(primaryKey, stFederalNewNdc);
			}
		}

		return map;
	}

	/**
	 * Returns all the st federal new ndcs.
	 *
	 * @return the st federal new ndcs
	 */
	@Override
	public List<StFederalNewNdc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st federal new ndcs
	 * @param end the upper bound of the range of st federal new ndcs (not inclusive)
	 * @return the range of st federal new ndcs
	 */
	@Override
	public List<StFederalNewNdc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st federal new ndcs
	 * @param end the upper bound of the range of st federal new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st federal new ndcs
	 */
	@Override
	public List<StFederalNewNdc> findAll(int start, int end,
		OrderByComparator<StFederalNewNdc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st federal new ndcs
	 * @param end the upper bound of the range of st federal new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st federal new ndcs
	 */
	@Override
	public List<StFederalNewNdc> findAll(int start, int end,
		OrderByComparator<StFederalNewNdc> orderByComparator,
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

		List<StFederalNewNdc> list = null;

		if (retrieveFromCache) {
			list = (List<StFederalNewNdc>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STFEDERALNEWNDC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STFEDERALNEWNDC;

				if (pagination) {
					sql = sql.concat(StFederalNewNdcModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StFederalNewNdc>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StFederalNewNdc>)QueryUtil.list(q,
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
	 * Removes all the st federal new ndcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StFederalNewNdc stFederalNewNdc : findAll()) {
			remove(stFederalNewNdc);
		}
	}

	/**
	 * Returns the number of st federal new ndcs.
	 *
	 * @return the number of st federal new ndcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STFEDERALNEWNDC);

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
		return StFederalNewNdcModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st federal new ndc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StFederalNewNdcImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STFEDERALNEWNDC = "SELECT stFederalNewNdc FROM StFederalNewNdc stFederalNewNdc";
	private static final String _SQL_COUNT_STFEDERALNEWNDC = "SELECT COUNT(stFederalNewNdc) FROM StFederalNewNdc stFederalNewNdc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stFederalNewNdc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StFederalNewNdc exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StFederalNewNdcPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"fss", "userId", "lastModifiedDate", "itemMasterSid", "wacPrice",
				"sessionId", "nonFamp"
			});
}