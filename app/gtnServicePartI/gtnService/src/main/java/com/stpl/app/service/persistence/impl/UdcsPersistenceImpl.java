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

import com.stpl.app.exception.NoSuchUdcsException;
import com.stpl.app.model.Udcs;
import com.stpl.app.model.impl.UdcsImpl;
import com.stpl.app.model.impl.UdcsModelImpl;
import com.stpl.app.service.persistence.UdcsPersistence;

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
 * The persistence implementation for the udcs service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UdcsPersistence
 * @see com.stpl.app.service.persistence.UdcsUtil
 * @generated
 */
@ProviderType
public class UdcsPersistenceImpl extends BasePersistenceImpl<Udcs>
	implements UdcsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UdcsUtil} to access the udcs persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = UdcsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsModelImpl.FINDER_CACHE_ENABLED, UdcsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsModelImpl.FINDER_CACHE_ENABLED, UdcsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public UdcsPersistenceImpl() {
		setModelClass(Udcs.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("masterType", "MASTER_TYPE");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("udc12", "UDC12");
			dbColumnNames.put("udc11", "UDC11");
			dbColumnNames.put("udcsSid", "UDCS_SID");
			dbColumnNames.put("masterSid", "MASTER_SID");
			dbColumnNames.put("udc10", "UDC10");
			dbColumnNames.put("udc9", "UDC9");
			dbColumnNames.put("udc8", "UDC8");
			dbColumnNames.put("udc7", "UDC7");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("udc4", "UDC4");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the udcs in the entity cache if it is enabled.
	 *
	 * @param udcs the udcs
	 */
	@Override
	public void cacheResult(Udcs udcs) {
		entityCache.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsImpl.class, udcs.getPrimaryKey(), udcs);

		udcs.resetOriginalValues();
	}

	/**
	 * Caches the udcses in the entity cache if it is enabled.
	 *
	 * @param udcses the udcses
	 */
	@Override
	public void cacheResult(List<Udcs> udcses) {
		for (Udcs udcs : udcses) {
			if (entityCache.getResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
						UdcsImpl.class, udcs.getPrimaryKey()) == null) {
				cacheResult(udcs);
			}
			else {
				udcs.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all udcses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(UdcsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the udcs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Udcs udcs) {
		entityCache.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsImpl.class, udcs.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Udcs> udcses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Udcs udcs : udcses) {
			entityCache.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
				UdcsImpl.class, udcs.getPrimaryKey());
		}
	}

	/**
	 * Creates a new udcs with the primary key. Does not add the udcs to the database.
	 *
	 * @param udcsSid the primary key for the new udcs
	 * @return the new udcs
	 */
	@Override
	public Udcs create(int udcsSid) {
		Udcs udcs = new UdcsImpl();

		udcs.setNew(true);
		udcs.setPrimaryKey(udcsSid);

		return udcs;
	}

	/**
	 * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param udcsSid the primary key of the udcs
	 * @return the udcs that was removed
	 * @throws NoSuchUdcsException if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs remove(int udcsSid) throws NoSuchUdcsException {
		return remove((Serializable)udcsSid);
	}

	/**
	 * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the udcs
	 * @return the udcs that was removed
	 * @throws NoSuchUdcsException if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs remove(Serializable primaryKey) throws NoSuchUdcsException {
		Session session = null;

		try {
			session = openSession();

			Udcs udcs = (Udcs)session.get(UdcsImpl.class, primaryKey);

			if (udcs == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUdcsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(udcs);
		}
		catch (NoSuchUdcsException nsee) {
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
	protected Udcs removeImpl(Udcs udcs) {
		udcs = toUnwrappedModel(udcs);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(udcs)) {
				udcs = (Udcs)session.get(UdcsImpl.class, udcs.getPrimaryKeyObj());
			}

			if (udcs != null) {
				session.delete(udcs);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (udcs != null) {
			clearCache(udcs);
		}

		return udcs;
	}

	@Override
	public Udcs updateImpl(Udcs udcs) {
		udcs = toUnwrappedModel(udcs);

		boolean isNew = udcs.isNew();

		Session session = null;

		try {
			session = openSession();

			if (udcs.isNew()) {
				session.save(udcs);

				udcs.setNew(false);
			}
			else {
				udcs = (Udcs)session.merge(udcs);
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

		entityCache.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
			UdcsImpl.class, udcs.getPrimaryKey(), udcs, false);

		udcs.resetOriginalValues();

		return udcs;
	}

	protected Udcs toUnwrappedModel(Udcs udcs) {
		if (udcs instanceof UdcsImpl) {
			return udcs;
		}

		UdcsImpl udcsImpl = new UdcsImpl();

		udcsImpl.setNew(udcs.isNew());
		udcsImpl.setPrimaryKey(udcs.getPrimaryKey());

		udcsImpl.setUdc1(udcs.getUdc1());
		udcsImpl.setUdc2(udcs.getUdc2());
		udcsImpl.setMasterType(udcs.getMasterType());
		udcsImpl.setUdc3(udcs.getUdc3());
		udcsImpl.setUdc12(udcs.getUdc12());
		udcsImpl.setUdc11(udcs.getUdc11());
		udcsImpl.setUdcsSid(udcs.getUdcsSid());
		udcsImpl.setMasterSid(udcs.getMasterSid());
		udcsImpl.setUdc10(udcs.getUdc10());
		udcsImpl.setUdc9(udcs.getUdc9());
		udcsImpl.setUdc8(udcs.getUdc8());
		udcsImpl.setUdc7(udcs.getUdc7());
		udcsImpl.setUdc6(udcs.getUdc6());
		udcsImpl.setUdc5(udcs.getUdc5());
		udcsImpl.setUdc4(udcs.getUdc4());

		return udcsImpl;
	}

	/**
	 * Returns the udcs with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the udcs
	 * @return the udcs
	 * @throws NoSuchUdcsException if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUdcsException {
		Udcs udcs = fetchByPrimaryKey(primaryKey);

		if (udcs == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUdcsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return udcs;
	}

	/**
	 * Returns the udcs with the primary key or throws a {@link NoSuchUdcsException} if it could not be found.
	 *
	 * @param udcsSid the primary key of the udcs
	 * @return the udcs
	 * @throws NoSuchUdcsException if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs findByPrimaryKey(int udcsSid) throws NoSuchUdcsException {
		return findByPrimaryKey((Serializable)udcsSid);
	}

	/**
	 * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the udcs
	 * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
				UdcsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Udcs udcs = (Udcs)serializable;

		if (udcs == null) {
			Session session = null;

			try {
				session = openSession();

				udcs = (Udcs)session.get(UdcsImpl.class, primaryKey);

				if (udcs != null) {
					cacheResult(udcs);
				}
				else {
					entityCache.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
						UdcsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
					UdcsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return udcs;
	}

	/**
	 * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param udcsSid the primary key of the udcs
	 * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
	 */
	@Override
	public Udcs fetchByPrimaryKey(int udcsSid) {
		return fetchByPrimaryKey((Serializable)udcsSid);
	}

	@Override
	public Map<Serializable, Udcs> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Udcs> map = new HashMap<Serializable, Udcs>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Udcs udcs = fetchByPrimaryKey(primaryKey);

			if (udcs != null) {
				map.put(primaryKey, udcs);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
					UdcsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Udcs)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_UDCS_WHERE_PKS_IN);

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

			for (Udcs udcs : (List<Udcs>)q.list()) {
				map.put(udcs.getPrimaryKeyObj(), udcs);

				cacheResult(udcs);

				uncachedPrimaryKeys.remove(udcs.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(UdcsModelImpl.ENTITY_CACHE_ENABLED,
					UdcsImpl.class, primaryKey, nullModel);
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
	 * Returns all the udcses.
	 *
	 * @return the udcses
	 */
	@Override
	public List<Udcs> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the udcses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of udcses
	 * @param end the upper bound of the range of udcses (not inclusive)
	 * @return the range of udcses
	 */
	@Override
	public List<Udcs> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the udcses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of udcses
	 * @param end the upper bound of the range of udcses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of udcses
	 */
	@Override
	public List<Udcs> findAll(int start, int end,
		OrderByComparator<Udcs> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the udcses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of udcses
	 * @param end the upper bound of the range of udcses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of udcses
	 */
	@Override
	public List<Udcs> findAll(int start, int end,
		OrderByComparator<Udcs> orderByComparator, boolean retrieveFromCache) {
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

		List<Udcs> list = null;

		if (retrieveFromCache) {
			list = (List<Udcs>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_UDCS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_UDCS;

				if (pagination) {
					sql = sql.concat(UdcsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Udcs>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Udcs>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the udcses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Udcs udcs : findAll()) {
			remove(udcs);
		}
	}

	/**
	 * Returns the number of udcses.
	 *
	 * @return the number of udcses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_UDCS);

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
		return UdcsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the udcs persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(UdcsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_UDCS = "SELECT udcs FROM Udcs udcs";
	private static final String _SQL_SELECT_UDCS_WHERE_PKS_IN = "SELECT udcs FROM Udcs udcs WHERE UDCS_SID IN (";
	private static final String _SQL_COUNT_UDCS = "SELECT COUNT(udcs) FROM Udcs udcs";
	private static final String _ORDER_BY_ENTITY_ALIAS = "udcs.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Udcs exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(UdcsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"udc1", "udc2", "masterType", "udc3", "udc12", "udc11",
				"udcsSid", "masterSid", "udc10", "udc9", "udc8", "udc7", "udc6",
				"udc5", "udc4"
			});
}