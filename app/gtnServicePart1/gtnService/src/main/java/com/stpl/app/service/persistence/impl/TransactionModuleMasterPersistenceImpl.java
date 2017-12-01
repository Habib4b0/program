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

import com.stpl.app.exception.NoSuchTransactionModuleMasterException;
import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.model.impl.TransactionModuleMasterImpl;
import com.stpl.app.model.impl.TransactionModuleMasterModelImpl;
import com.stpl.app.service.persistence.TransactionModuleMasterPersistence;

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
 * The persistence implementation for the transaction module master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleMasterPersistence
 * @see com.stpl.app.service.persistence.TransactionModuleMasterUtil
 * @generated
 */
@ProviderType
public class TransactionModuleMasterPersistenceImpl extends BasePersistenceImpl<TransactionModuleMaster>
	implements TransactionModuleMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TransactionModuleMasterUtil} to access the transaction module master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TransactionModuleMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED,
			TransactionModuleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED,
			TransactionModuleMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public TransactionModuleMasterPersistenceImpl() {
		setModelClass(TransactionModuleMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("transactionModuleMasterSid",
				"TRANSACTION_MODULE_MASTER_SID");
			dbColumnNames.put("invalidTableName", "INVALID_TABLE_NAME");
			dbColumnNames.put("tableName", "TABLE_NAME");
			dbColumnNames.put("moduleName", "MODULE_NAME");
			dbColumnNames.put("tabName", "TAB_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the transaction module master in the entity cache if it is enabled.
	 *
	 * @param transactionModuleMaster the transaction module master
	 */
	@Override
	public void cacheResult(TransactionModuleMaster transactionModuleMaster) {
		entityCache.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterImpl.class,
			transactionModuleMaster.getPrimaryKey(), transactionModuleMaster);

		transactionModuleMaster.resetOriginalValues();
	}

	/**
	 * Caches the transaction module masters in the entity cache if it is enabled.
	 *
	 * @param transactionModuleMasters the transaction module masters
	 */
	@Override
	public void cacheResult(
		List<TransactionModuleMaster> transactionModuleMasters) {
		for (TransactionModuleMaster transactionModuleMaster : transactionModuleMasters) {
			if (entityCache.getResult(
						TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
						TransactionModuleMasterImpl.class,
						transactionModuleMaster.getPrimaryKey()) == null) {
				cacheResult(transactionModuleMaster);
			}
			else {
				transactionModuleMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all transaction module masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TransactionModuleMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the transaction module master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TransactionModuleMaster transactionModuleMaster) {
		entityCache.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterImpl.class,
			transactionModuleMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<TransactionModuleMaster> transactionModuleMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TransactionModuleMaster transactionModuleMaster : transactionModuleMasters) {
			entityCache.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
				TransactionModuleMasterImpl.class,
				transactionModuleMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
	 *
	 * @param transactionModuleMasterSid the primary key for the new transaction module master
	 * @return the new transaction module master
	 */
	@Override
	public TransactionModuleMaster create(int transactionModuleMasterSid) {
		TransactionModuleMaster transactionModuleMaster = new TransactionModuleMasterImpl();

		transactionModuleMaster.setNew(true);
		transactionModuleMaster.setPrimaryKey(transactionModuleMasterSid);

		return transactionModuleMaster;
	}

	/**
	 * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionModuleMasterSid the primary key of the transaction module master
	 * @return the transaction module master that was removed
	 * @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster remove(int transactionModuleMasterSid)
		throws NoSuchTransactionModuleMasterException {
		return remove((Serializable)transactionModuleMasterSid);
	}

	/**
	 * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transaction module master
	 * @return the transaction module master that was removed
	 * @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster remove(Serializable primaryKey)
		throws NoSuchTransactionModuleMasterException {
		Session session = null;

		try {
			session = openSession();

			TransactionModuleMaster transactionModuleMaster = (TransactionModuleMaster)session.get(TransactionModuleMasterImpl.class,
					primaryKey);

			if (transactionModuleMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransactionModuleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(transactionModuleMaster);
		}
		catch (NoSuchTransactionModuleMasterException nsee) {
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
	protected TransactionModuleMaster removeImpl(
		TransactionModuleMaster transactionModuleMaster) {
		transactionModuleMaster = toUnwrappedModel(transactionModuleMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(transactionModuleMaster)) {
				transactionModuleMaster = (TransactionModuleMaster)session.get(TransactionModuleMasterImpl.class,
						transactionModuleMaster.getPrimaryKeyObj());
			}

			if (transactionModuleMaster != null) {
				session.delete(transactionModuleMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (transactionModuleMaster != null) {
			clearCache(transactionModuleMaster);
		}

		return transactionModuleMaster;
	}

	@Override
	public TransactionModuleMaster updateImpl(
		TransactionModuleMaster transactionModuleMaster) {
		transactionModuleMaster = toUnwrappedModel(transactionModuleMaster);

		boolean isNew = transactionModuleMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (transactionModuleMaster.isNew()) {
				session.save(transactionModuleMaster);

				transactionModuleMaster.setNew(false);
			}
			else {
				transactionModuleMaster = (TransactionModuleMaster)session.merge(transactionModuleMaster);
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

		entityCache.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleMasterImpl.class,
			transactionModuleMaster.getPrimaryKey(), transactionModuleMaster,
			false);

		transactionModuleMaster.resetOriginalValues();

		return transactionModuleMaster;
	}

	protected TransactionModuleMaster toUnwrappedModel(
		TransactionModuleMaster transactionModuleMaster) {
		if (transactionModuleMaster instanceof TransactionModuleMasterImpl) {
			return transactionModuleMaster;
		}

		TransactionModuleMasterImpl transactionModuleMasterImpl = new TransactionModuleMasterImpl();

		transactionModuleMasterImpl.setNew(transactionModuleMaster.isNew());
		transactionModuleMasterImpl.setPrimaryKey(transactionModuleMaster.getPrimaryKey());

		transactionModuleMasterImpl.setTransactionModuleMasterSid(transactionModuleMaster.getTransactionModuleMasterSid());
		transactionModuleMasterImpl.setInvalidTableName(transactionModuleMaster.getInvalidTableName());
		transactionModuleMasterImpl.setTableName(transactionModuleMaster.getTableName());
		transactionModuleMasterImpl.setModuleName(transactionModuleMaster.getModuleName());
		transactionModuleMasterImpl.setTabName(transactionModuleMaster.getTabName());

		return transactionModuleMasterImpl;
	}

	/**
	 * Returns the transaction module master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction module master
	 * @return the transaction module master
	 * @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTransactionModuleMasterException {
		TransactionModuleMaster transactionModuleMaster = fetchByPrimaryKey(primaryKey);

		if (transactionModuleMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTransactionModuleMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return transactionModuleMaster;
	}

	/**
	 * Returns the transaction module master with the primary key or throws a {@link NoSuchTransactionModuleMasterException} if it could not be found.
	 *
	 * @param transactionModuleMasterSid the primary key of the transaction module master
	 * @return the transaction module master
	 * @throws NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster findByPrimaryKey(
		int transactionModuleMasterSid)
		throws NoSuchTransactionModuleMasterException {
		return findByPrimaryKey((Serializable)transactionModuleMasterSid);
	}

	/**
	 * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction module master
	 * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
				TransactionModuleMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TransactionModuleMaster transactionModuleMaster = (TransactionModuleMaster)serializable;

		if (transactionModuleMaster == null) {
			Session session = null;

			try {
				session = openSession();

				transactionModuleMaster = (TransactionModuleMaster)session.get(TransactionModuleMasterImpl.class,
						primaryKey);

				if (transactionModuleMaster != null) {
					cacheResult(transactionModuleMaster);
				}
				else {
					entityCache.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
						TransactionModuleMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return transactionModuleMaster;
	}

	/**
	 * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionModuleMasterSid the primary key of the transaction module master
	 * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
	 */
	@Override
	public TransactionModuleMaster fetchByPrimaryKey(
		int transactionModuleMasterSid) {
		return fetchByPrimaryKey((Serializable)transactionModuleMasterSid);
	}

	@Override
	public Map<Serializable, TransactionModuleMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TransactionModuleMaster> map = new HashMap<Serializable, TransactionModuleMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TransactionModuleMaster transactionModuleMaster = fetchByPrimaryKey(primaryKey);

			if (transactionModuleMaster != null) {
				map.put(primaryKey, transactionModuleMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TransactionModuleMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TRANSACTIONMODULEMASTER_WHERE_PKS_IN);

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

			for (TransactionModuleMaster transactionModuleMaster : (List<TransactionModuleMaster>)q.list()) {
				map.put(transactionModuleMaster.getPrimaryKeyObj(),
					transactionModuleMaster);

				cacheResult(transactionModuleMaster);

				uncachedPrimaryKeys.remove(transactionModuleMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TransactionModuleMasterModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the transaction module masters.
	 *
	 * @return the transaction module masters
	 */
	@Override
	public List<TransactionModuleMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction module masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module masters
	 * @param end the upper bound of the range of transaction module masters (not inclusive)
	 * @return the range of transaction module masters
	 */
	@Override
	public List<TransactionModuleMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction module masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module masters
	 * @param end the upper bound of the range of transaction module masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction module masters
	 */
	@Override
	public List<TransactionModuleMaster> findAll(int start, int end,
		OrderByComparator<TransactionModuleMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the transaction module masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module masters
	 * @param end the upper bound of the range of transaction module masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of transaction module masters
	 */
	@Override
	public List<TransactionModuleMaster> findAll(int start, int end,
		OrderByComparator<TransactionModuleMaster> orderByComparator,
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

		List<TransactionModuleMaster> list = null;

		if (retrieveFromCache) {
			list = (List<TransactionModuleMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TRANSACTIONMODULEMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSACTIONMODULEMASTER;

				if (pagination) {
					sql = sql.concat(TransactionModuleMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TransactionModuleMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TransactionModuleMaster>)QueryUtil.list(q,
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
	 * Removes all the transaction module masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TransactionModuleMaster transactionModuleMaster : findAll()) {
			remove(transactionModuleMaster);
		}
	}

	/**
	 * Returns the number of transaction module masters.
	 *
	 * @return the number of transaction module masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRANSACTIONMODULEMASTER);

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
		return TransactionModuleMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the transaction module master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TransactionModuleMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TRANSACTIONMODULEMASTER = "SELECT transactionModuleMaster FROM TransactionModuleMaster transactionModuleMaster";
	private static final String _SQL_SELECT_TRANSACTIONMODULEMASTER_WHERE_PKS_IN =
		"SELECT transactionModuleMaster FROM TransactionModuleMaster transactionModuleMaster WHERE TRANSACTION_MODULE_MASTER_SID IN (";
	private static final String _SQL_COUNT_TRANSACTIONMODULEMASTER = "SELECT COUNT(transactionModuleMaster) FROM TransactionModuleMaster transactionModuleMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "transactionModuleMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionModuleMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(TransactionModuleMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"transactionModuleMasterSid", "invalidTableName", "tableName",
				"moduleName", "tabName"
			});
}