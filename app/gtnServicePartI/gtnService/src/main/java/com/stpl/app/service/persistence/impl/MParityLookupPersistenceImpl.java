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

import com.stpl.app.exception.NoSuchMParityLookupException;
import com.stpl.app.model.MParityLookup;
import com.stpl.app.model.impl.MParityLookupImpl;
import com.stpl.app.model.impl.MParityLookupModelImpl;
import com.stpl.app.service.persistence.MParityLookupPersistence;

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
 * The persistence implementation for the m parity lookup service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MParityLookupPersistence
 * @see com.stpl.app.service.persistence.MParityLookupUtil
 * @generated
 */
@ProviderType
public class MParityLookupPersistenceImpl extends BasePersistenceImpl<MParityLookup>
	implements MParityLookupPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MParityLookupUtil} to access the m parity lookup persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MParityLookupImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupModelImpl.FINDER_CACHE_ENABLED,
			MParityLookupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupModelImpl.FINDER_CACHE_ENABLED,
			MParityLookupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MParityLookupPersistenceImpl() {
		setModelClass(MParityLookup.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("marketType", "MARKET_TYPE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("mParityLookupSid", "M_PARITY_LOOKUP_SID");
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
	 * Caches the m parity lookup in the entity cache if it is enabled.
	 *
	 * @param mParityLookup the m parity lookup
	 */
	@Override
	public void cacheResult(MParityLookup mParityLookup) {
		entityCache.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupImpl.class, mParityLookup.getPrimaryKey(),
			mParityLookup);

		mParityLookup.resetOriginalValues();
	}

	/**
	 * Caches the m parity lookups in the entity cache if it is enabled.
	 *
	 * @param mParityLookups the m parity lookups
	 */
	@Override
	public void cacheResult(List<MParityLookup> mParityLookups) {
		for (MParityLookup mParityLookup : mParityLookups) {
			if (entityCache.getResult(
						MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
						MParityLookupImpl.class, mParityLookup.getPrimaryKey()) == null) {
				cacheResult(mParityLookup);
			}
			else {
				mParityLookup.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m parity lookups.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MParityLookupImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m parity lookup.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MParityLookup mParityLookup) {
		entityCache.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupImpl.class, mParityLookup.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MParityLookup> mParityLookups) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MParityLookup mParityLookup : mParityLookups) {
			entityCache.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
				MParityLookupImpl.class, mParityLookup.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m parity lookup with the primary key. Does not add the m parity lookup to the database.
	 *
	 * @param mParityLookupSid the primary key for the new m parity lookup
	 * @return the new m parity lookup
	 */
	@Override
	public MParityLookup create(int mParityLookupSid) {
		MParityLookup mParityLookup = new MParityLookupImpl();

		mParityLookup.setNew(true);
		mParityLookup.setPrimaryKey(mParityLookupSid);

		return mParityLookup;
	}

	/**
	 * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mParityLookupSid the primary key of the m parity lookup
	 * @return the m parity lookup that was removed
	 * @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup remove(int mParityLookupSid)
		throws NoSuchMParityLookupException {
		return remove((Serializable)mParityLookupSid);
	}

	/**
	 * Removes the m parity lookup with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m parity lookup
	 * @return the m parity lookup that was removed
	 * @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup remove(Serializable primaryKey)
		throws NoSuchMParityLookupException {
		Session session = null;

		try {
			session = openSession();

			MParityLookup mParityLookup = (MParityLookup)session.get(MParityLookupImpl.class,
					primaryKey);

			if (mParityLookup == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMParityLookupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mParityLookup);
		}
		catch (NoSuchMParityLookupException nsee) {
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
	protected MParityLookup removeImpl(MParityLookup mParityLookup) {
		mParityLookup = toUnwrappedModel(mParityLookup);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mParityLookup)) {
				mParityLookup = (MParityLookup)session.get(MParityLookupImpl.class,
						mParityLookup.getPrimaryKeyObj());
			}

			if (mParityLookup != null) {
				session.delete(mParityLookup);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mParityLookup != null) {
			clearCache(mParityLookup);
		}

		return mParityLookup;
	}

	@Override
	public MParityLookup updateImpl(MParityLookup mParityLookup) {
		mParityLookup = toUnwrappedModel(mParityLookup);

		boolean isNew = mParityLookup.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mParityLookup.isNew()) {
				session.save(mParityLookup);

				mParityLookup.setNew(false);
			}
			else {
				mParityLookup = (MParityLookup)session.merge(mParityLookup);
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

		entityCache.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
			MParityLookupImpl.class, mParityLookup.getPrimaryKey(),
			mParityLookup, false);

		mParityLookup.resetOriginalValues();

		return mParityLookup;
	}

	protected MParityLookup toUnwrappedModel(MParityLookup mParityLookup) {
		if (mParityLookup instanceof MParityLookupImpl) {
			return mParityLookup;
		}

		MParityLookupImpl mParityLookupImpl = new MParityLookupImpl();

		mParityLookupImpl.setNew(mParityLookup.isNew());
		mParityLookupImpl.setPrimaryKey(mParityLookup.getPrimaryKey());

		mParityLookupImpl.setContractMasterSid(mParityLookup.getContractMasterSid());
		mParityLookupImpl.setMarketType(mParityLookup.getMarketType());
		mParityLookupImpl.setItemMasterSid(mParityLookup.getItemMasterSid());
		mParityLookupImpl.setMParityLookupSid(mParityLookup.getMParityLookupSid());
		mParityLookupImpl.setProjectionDetailsSid(mParityLookup.getProjectionDetailsSid());

		return mParityLookupImpl;
	}

	/**
	 * Returns the m parity lookup with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m parity lookup
	 * @return the m parity lookup
	 * @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMParityLookupException {
		MParityLookup mParityLookup = fetchByPrimaryKey(primaryKey);

		if (mParityLookup == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMParityLookupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mParityLookup;
	}

	/**
	 * Returns the m parity lookup with the primary key or throws a {@link NoSuchMParityLookupException} if it could not be found.
	 *
	 * @param mParityLookupSid the primary key of the m parity lookup
	 * @return the m parity lookup
	 * @throws NoSuchMParityLookupException if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup findByPrimaryKey(int mParityLookupSid)
		throws NoSuchMParityLookupException {
		return findByPrimaryKey((Serializable)mParityLookupSid);
	}

	/**
	 * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m parity lookup
	 * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
				MParityLookupImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MParityLookup mParityLookup = (MParityLookup)serializable;

		if (mParityLookup == null) {
			Session session = null;

			try {
				session = openSession();

				mParityLookup = (MParityLookup)session.get(MParityLookupImpl.class,
						primaryKey);

				if (mParityLookup != null) {
					cacheResult(mParityLookup);
				}
				else {
					entityCache.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
						MParityLookupImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
					MParityLookupImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mParityLookup;
	}

	/**
	 * Returns the m parity lookup with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mParityLookupSid the primary key of the m parity lookup
	 * @return the m parity lookup, or <code>null</code> if a m parity lookup with the primary key could not be found
	 */
	@Override
	public MParityLookup fetchByPrimaryKey(int mParityLookupSid) {
		return fetchByPrimaryKey((Serializable)mParityLookupSid);
	}

	@Override
	public Map<Serializable, MParityLookup> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MParityLookup> map = new HashMap<Serializable, MParityLookup>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MParityLookup mParityLookup = fetchByPrimaryKey(primaryKey);

			if (mParityLookup != null) {
				map.put(primaryKey, mParityLookup);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
					MParityLookupImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MParityLookup)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MPARITYLOOKUP_WHERE_PKS_IN);

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

			for (MParityLookup mParityLookup : (List<MParityLookup>)q.list()) {
				map.put(mParityLookup.getPrimaryKeyObj(), mParityLookup);

				cacheResult(mParityLookup);

				uncachedPrimaryKeys.remove(mParityLookup.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MParityLookupModelImpl.ENTITY_CACHE_ENABLED,
					MParityLookupImpl.class, primaryKey, nullModel);
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
	 * Returns all the m parity lookups.
	 *
	 * @return the m parity lookups
	 */
	@Override
	public List<MParityLookup> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m parity lookups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m parity lookups
	 * @param end the upper bound of the range of m parity lookups (not inclusive)
	 * @return the range of m parity lookups
	 */
	@Override
	public List<MParityLookup> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m parity lookups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m parity lookups
	 * @param end the upper bound of the range of m parity lookups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m parity lookups
	 */
	@Override
	public List<MParityLookup> findAll(int start, int end,
		OrderByComparator<MParityLookup> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m parity lookups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MParityLookupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m parity lookups
	 * @param end the upper bound of the range of m parity lookups (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m parity lookups
	 */
	@Override
	public List<MParityLookup> findAll(int start, int end,
		OrderByComparator<MParityLookup> orderByComparator,
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

		List<MParityLookup> list = null;

		if (retrieveFromCache) {
			list = (List<MParityLookup>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MPARITYLOOKUP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MPARITYLOOKUP;

				if (pagination) {
					sql = sql.concat(MParityLookupModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MParityLookup>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MParityLookup>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the m parity lookups from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MParityLookup mParityLookup : findAll()) {
			remove(mParityLookup);
		}
	}

	/**
	 * Returns the number of m parity lookups.
	 *
	 * @return the number of m parity lookups
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MPARITYLOOKUP);

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
		return MParityLookupModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m parity lookup persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MParityLookupImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MPARITYLOOKUP = "SELECT mParityLookup FROM MParityLookup mParityLookup";
	private static final String _SQL_SELECT_MPARITYLOOKUP_WHERE_PKS_IN = "SELECT mParityLookup FROM MParityLookup mParityLookup WHERE M_PARITY_LOOKUP_SID IN (";
	private static final String _SQL_COUNT_MPARITYLOOKUP = "SELECT COUNT(mParityLookup) FROM MParityLookup mParityLookup";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mParityLookup.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MParityLookup exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MParityLookupPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"contractMasterSid", "marketType", "itemMasterSid",
				"mParityLookupSid", "projectionDetailsSid"
			});
}