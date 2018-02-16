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

import com.stpl.app.exception.NoSuchFederalNewNdcException;
import com.stpl.app.model.FederalNewNdc;
import com.stpl.app.model.impl.FederalNewNdcImpl;
import com.stpl.app.model.impl.FederalNewNdcModelImpl;
import com.stpl.app.service.persistence.FederalNewNdcPersistence;

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
 * The persistence implementation for the federal new ndc service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FederalNewNdcPersistence
 * @see com.stpl.app.service.persistence.FederalNewNdcUtil
 * @generated
 */
@ProviderType
public class FederalNewNdcPersistenceImpl extends BasePersistenceImpl<FederalNewNdc>
	implements FederalNewNdcPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link FederalNewNdcUtil} to access the federal new ndc persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = FederalNewNdcImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
			FederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcModelImpl.FINDER_CACHE_ENABLED,
			FederalNewNdcImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public FederalNewNdcPersistenceImpl() {
		setModelClass(FederalNewNdc.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("fss", "FSS");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("wacPrice", "WAC_PRICE");
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
	 * Caches the federal new ndc in the entity cache if it is enabled.
	 *
	 * @param federalNewNdc the federal new ndc
	 */
	@Override
	public void cacheResult(FederalNewNdc federalNewNdc) {
		entityCache.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey(),
			federalNewNdc);

		federalNewNdc.resetOriginalValues();
	}

	/**
	 * Caches the federal new ndcs in the entity cache if it is enabled.
	 *
	 * @param federalNewNdcs the federal new ndcs
	 */
	@Override
	public void cacheResult(List<FederalNewNdc> federalNewNdcs) {
		for (FederalNewNdc federalNewNdc : federalNewNdcs) {
			if (entityCache.getResult(
						FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey()) == null) {
				cacheResult(federalNewNdc);
			}
			else {
				federalNewNdc.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all federal new ndcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FederalNewNdcImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the federal new ndc.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FederalNewNdc federalNewNdc) {
		entityCache.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<FederalNewNdc> federalNewNdcs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FederalNewNdc federalNewNdc : federalNewNdcs) {
			entityCache.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey());
		}
	}

	/**
	 * Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
	 *
	 * @param itemMasterSid the primary key for the new federal new ndc
	 * @return the new federal new ndc
	 */
	@Override
	public FederalNewNdc create(int itemMasterSid) {
		FederalNewNdc federalNewNdc = new FederalNewNdcImpl();

		federalNewNdc.setNew(true);
		federalNewNdc.setPrimaryKey(itemMasterSid);

		return federalNewNdc;
	}

	/**
	 * Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemMasterSid the primary key of the federal new ndc
	 * @return the federal new ndc that was removed
	 * @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc remove(int itemMasterSid)
		throws NoSuchFederalNewNdcException {
		return remove((Serializable)itemMasterSid);
	}

	/**
	 * Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the federal new ndc
	 * @return the federal new ndc that was removed
	 * @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc remove(Serializable primaryKey)
		throws NoSuchFederalNewNdcException {
		Session session = null;

		try {
			session = openSession();

			FederalNewNdc federalNewNdc = (FederalNewNdc)session.get(FederalNewNdcImpl.class,
					primaryKey);

			if (federalNewNdc == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(federalNewNdc);
		}
		catch (NoSuchFederalNewNdcException nsee) {
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
	protected FederalNewNdc removeImpl(FederalNewNdc federalNewNdc) {
		federalNewNdc = toUnwrappedModel(federalNewNdc);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(federalNewNdc)) {
				federalNewNdc = (FederalNewNdc)session.get(FederalNewNdcImpl.class,
						federalNewNdc.getPrimaryKeyObj());
			}

			if (federalNewNdc != null) {
				session.delete(federalNewNdc);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (federalNewNdc != null) {
			clearCache(federalNewNdc);
		}

		return federalNewNdc;
	}

	@Override
	public FederalNewNdc updateImpl(FederalNewNdc federalNewNdc) {
		federalNewNdc = toUnwrappedModel(federalNewNdc);

		boolean isNew = federalNewNdc.isNew();

		Session session = null;

		try {
			session = openSession();

			if (federalNewNdc.isNew()) {
				session.save(federalNewNdc);

				federalNewNdc.setNew(false);
			}
			else {
				federalNewNdc = (FederalNewNdc)session.merge(federalNewNdc);
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

		entityCache.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
			FederalNewNdcImpl.class, federalNewNdc.getPrimaryKey(),
			federalNewNdc, false);

		federalNewNdc.resetOriginalValues();

		return federalNewNdc;
	}

	protected FederalNewNdc toUnwrappedModel(FederalNewNdc federalNewNdc) {
		if (federalNewNdc instanceof FederalNewNdcImpl) {
			return federalNewNdc;
		}

		FederalNewNdcImpl federalNewNdcImpl = new FederalNewNdcImpl();

		federalNewNdcImpl.setNew(federalNewNdc.isNew());
		federalNewNdcImpl.setPrimaryKey(federalNewNdc.getPrimaryKey());

		federalNewNdcImpl.setFss(federalNewNdc.getFss());
		federalNewNdcImpl.setItemMasterSid(federalNewNdc.getItemMasterSid());
		federalNewNdcImpl.setWacPrice(federalNewNdc.getWacPrice());
		federalNewNdcImpl.setNonFamp(federalNewNdc.getNonFamp());

		return federalNewNdcImpl;
	}

	/**
	 * Returns the federal new ndc with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the federal new ndc
	 * @return the federal new ndc
	 * @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFederalNewNdcException {
		FederalNewNdc federalNewNdc = fetchByPrimaryKey(primaryKey);

		if (federalNewNdc == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFederalNewNdcException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return federalNewNdc;
	}

	/**
	 * Returns the federal new ndc with the primary key or throws a {@link NoSuchFederalNewNdcException} if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the federal new ndc
	 * @return the federal new ndc
	 * @throws NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc findByPrimaryKey(int itemMasterSid)
		throws NoSuchFederalNewNdcException {
		return findByPrimaryKey((Serializable)itemMasterSid);
	}

	/**
	 * Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the federal new ndc
	 * @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
				FederalNewNdcImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FederalNewNdc federalNewNdc = (FederalNewNdc)serializable;

		if (federalNewNdc == null) {
			Session session = null;

			try {
				session = openSession();

				federalNewNdc = (FederalNewNdc)session.get(FederalNewNdcImpl.class,
						primaryKey);

				if (federalNewNdc != null) {
					cacheResult(federalNewNdc);
				}
				else {
					entityCache.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
						FederalNewNdcImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					FederalNewNdcImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return federalNewNdc;
	}

	/**
	 * Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the federal new ndc
	 * @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
	 */
	@Override
	public FederalNewNdc fetchByPrimaryKey(int itemMasterSid) {
		return fetchByPrimaryKey((Serializable)itemMasterSid);
	}

	@Override
	public Map<Serializable, FederalNewNdc> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FederalNewNdc> map = new HashMap<Serializable, FederalNewNdc>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FederalNewNdc federalNewNdc = fetchByPrimaryKey(primaryKey);

			if (federalNewNdc != null) {
				map.put(primaryKey, federalNewNdc);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					FederalNewNdcImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FederalNewNdc)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_FEDERALNEWNDC_WHERE_PKS_IN);

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

			for (FederalNewNdc federalNewNdc : (List<FederalNewNdc>)q.list()) {
				map.put(federalNewNdc.getPrimaryKeyObj(), federalNewNdc);

				cacheResult(federalNewNdc);

				uncachedPrimaryKeys.remove(federalNewNdc.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(FederalNewNdcModelImpl.ENTITY_CACHE_ENABLED,
					FederalNewNdcImpl.class, primaryKey, nullModel);
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
	 * Returns all the federal new ndcs.
	 *
	 * @return the federal new ndcs
	 */
	@Override
	public List<FederalNewNdc> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of federal new ndcs
	 * @param end the upper bound of the range of federal new ndcs (not inclusive)
	 * @return the range of federal new ndcs
	 */
	@Override
	public List<FederalNewNdc> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of federal new ndcs
	 * @param end the upper bound of the range of federal new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of federal new ndcs
	 */
	@Override
	public List<FederalNewNdc> findAll(int start, int end,
		OrderByComparator<FederalNewNdc> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the federal new ndcs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of federal new ndcs
	 * @param end the upper bound of the range of federal new ndcs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of federal new ndcs
	 */
	@Override
	public List<FederalNewNdc> findAll(int start, int end,
		OrderByComparator<FederalNewNdc> orderByComparator,
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

		List<FederalNewNdc> list = null;

		if (retrieveFromCache) {
			list = (List<FederalNewNdc>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FEDERALNEWNDC);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FEDERALNEWNDC;

				if (pagination) {
					sql = sql.concat(FederalNewNdcModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<FederalNewNdc>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<FederalNewNdc>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the federal new ndcs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FederalNewNdc federalNewNdc : findAll()) {
			remove(federalNewNdc);
		}
	}

	/**
	 * Returns the number of federal new ndcs.
	 *
	 * @return the number of federal new ndcs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FEDERALNEWNDC);

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
		return FederalNewNdcModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the federal new ndc persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(FederalNewNdcImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_FEDERALNEWNDC = "SELECT federalNewNdc FROM FederalNewNdc federalNewNdc";
	private static final String _SQL_SELECT_FEDERALNEWNDC_WHERE_PKS_IN = "SELECT federalNewNdc FROM FederalNewNdc federalNewNdc WHERE ITEM_MASTER_SID IN (";
	private static final String _SQL_COUNT_FEDERALNEWNDC = "SELECT COUNT(federalNewNdc) FROM FederalNewNdc federalNewNdc";
	private static final String _ORDER_BY_ENTITY_ALIAS = "federalNewNdc.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No FederalNewNdc exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(FederalNewNdcPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"fss", "itemMasterSid", "wacPrice", "nonFamp"
			});
}