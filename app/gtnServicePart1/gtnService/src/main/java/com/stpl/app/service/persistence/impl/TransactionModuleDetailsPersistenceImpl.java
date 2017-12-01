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

import com.stpl.app.exception.NoSuchTransactionModuleDetailsException;
import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.model.impl.TransactionModuleDetailsImpl;
import com.stpl.app.model.impl.TransactionModuleDetailsModelImpl;
import com.stpl.app.service.persistence.TransactionModuleDetailsPersistence;

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
 * The persistence implementation for the transaction module details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleDetailsPersistence
 * @see com.stpl.app.service.persistence.TransactionModuleDetailsUtil
 * @generated
 */
@ProviderType
public class TransactionModuleDetailsPersistenceImpl extends BasePersistenceImpl<TransactionModuleDetails>
	implements TransactionModuleDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TransactionModuleDetailsUtil} to access the transaction module details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TransactionModuleDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED,
			TransactionModuleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED,
			TransactionModuleDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public TransactionModuleDetailsPersistenceImpl() {
		setModelClass(TransactionModuleDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("propertyIndex", "PROPERTY_INDEX");
			dbColumnNames.put("displayName", "DISPLAY_NAME");
			dbColumnNames.put("transactionModuleMasterSid",
				"TRANSACTION_MODULE_MASTER_SID");
			dbColumnNames.put("categoryName", "CATEGORY_NAME");
			dbColumnNames.put("validation", "VALIDATION");
			dbColumnNames.put("propertyName", "PROPERTY_NAME");
			dbColumnNames.put("flag", "FLAG");
			dbColumnNames.put("transactionModuleDetailsSid",
				"TRANSACTION_MODULE_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the transaction module details in the entity cache if it is enabled.
	 *
	 * @param transactionModuleDetails the transaction module details
	 */
	@Override
	public void cacheResult(TransactionModuleDetails transactionModuleDetails) {
		entityCache.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsImpl.class,
			transactionModuleDetails.getPrimaryKey(), transactionModuleDetails);

		transactionModuleDetails.resetOriginalValues();
	}

	/**
	 * Caches the transaction module detailses in the entity cache if it is enabled.
	 *
	 * @param transactionModuleDetailses the transaction module detailses
	 */
	@Override
	public void cacheResult(
		List<TransactionModuleDetails> transactionModuleDetailses) {
		for (TransactionModuleDetails transactionModuleDetails : transactionModuleDetailses) {
			if (entityCache.getResult(
						TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
						TransactionModuleDetailsImpl.class,
						transactionModuleDetails.getPrimaryKey()) == null) {
				cacheResult(transactionModuleDetails);
			}
			else {
				transactionModuleDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all transaction module detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(TransactionModuleDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the transaction module details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(TransactionModuleDetails transactionModuleDetails) {
		entityCache.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsImpl.class,
			transactionModuleDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<TransactionModuleDetails> transactionModuleDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (TransactionModuleDetails transactionModuleDetails : transactionModuleDetailses) {
			entityCache.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
				TransactionModuleDetailsImpl.class,
				transactionModuleDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new transaction module details with the primary key. Does not add the transaction module details to the database.
	 *
	 * @param transactionModuleDetailsSid the primary key for the new transaction module details
	 * @return the new transaction module details
	 */
	@Override
	public TransactionModuleDetails create(int transactionModuleDetailsSid) {
		TransactionModuleDetails transactionModuleDetails = new TransactionModuleDetailsImpl();

		transactionModuleDetails.setNew(true);
		transactionModuleDetails.setPrimaryKey(transactionModuleDetailsSid);

		return transactionModuleDetails;
	}

	/**
	 * Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionModuleDetailsSid the primary key of the transaction module details
	 * @return the transaction module details that was removed
	 * @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails remove(int transactionModuleDetailsSid)
		throws NoSuchTransactionModuleDetailsException {
		return remove((Serializable)transactionModuleDetailsSid);
	}

	/**
	 * Removes the transaction module details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the transaction module details
	 * @return the transaction module details that was removed
	 * @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails remove(Serializable primaryKey)
		throws NoSuchTransactionModuleDetailsException {
		Session session = null;

		try {
			session = openSession();

			TransactionModuleDetails transactionModuleDetails = (TransactionModuleDetails)session.get(TransactionModuleDetailsImpl.class,
					primaryKey);

			if (transactionModuleDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTransactionModuleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(transactionModuleDetails);
		}
		catch (NoSuchTransactionModuleDetailsException nsee) {
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
	protected TransactionModuleDetails removeImpl(
		TransactionModuleDetails transactionModuleDetails) {
		transactionModuleDetails = toUnwrappedModel(transactionModuleDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(transactionModuleDetails)) {
				transactionModuleDetails = (TransactionModuleDetails)session.get(TransactionModuleDetailsImpl.class,
						transactionModuleDetails.getPrimaryKeyObj());
			}

			if (transactionModuleDetails != null) {
				session.delete(transactionModuleDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (transactionModuleDetails != null) {
			clearCache(transactionModuleDetails);
		}

		return transactionModuleDetails;
	}

	@Override
	public TransactionModuleDetails updateImpl(
		TransactionModuleDetails transactionModuleDetails) {
		transactionModuleDetails = toUnwrappedModel(transactionModuleDetails);

		boolean isNew = transactionModuleDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (transactionModuleDetails.isNew()) {
				session.save(transactionModuleDetails);

				transactionModuleDetails.setNew(false);
			}
			else {
				transactionModuleDetails = (TransactionModuleDetails)session.merge(transactionModuleDetails);
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

		entityCache.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
			TransactionModuleDetailsImpl.class,
			transactionModuleDetails.getPrimaryKey(), transactionModuleDetails,
			false);

		transactionModuleDetails.resetOriginalValues();

		return transactionModuleDetails;
	}

	protected TransactionModuleDetails toUnwrappedModel(
		TransactionModuleDetails transactionModuleDetails) {
		if (transactionModuleDetails instanceof TransactionModuleDetailsImpl) {
			return transactionModuleDetails;
		}

		TransactionModuleDetailsImpl transactionModuleDetailsImpl = new TransactionModuleDetailsImpl();

		transactionModuleDetailsImpl.setNew(transactionModuleDetails.isNew());
		transactionModuleDetailsImpl.setPrimaryKey(transactionModuleDetails.getPrimaryKey());

		transactionModuleDetailsImpl.setPropertyIndex(transactionModuleDetails.getPropertyIndex());
		transactionModuleDetailsImpl.setDisplayName(transactionModuleDetails.getDisplayName());
		transactionModuleDetailsImpl.setTransactionModuleMasterSid(transactionModuleDetails.getTransactionModuleMasterSid());
		transactionModuleDetailsImpl.setCategoryName(transactionModuleDetails.getCategoryName());
		transactionModuleDetailsImpl.setValidation(transactionModuleDetails.getValidation());
		transactionModuleDetailsImpl.setPropertyName(transactionModuleDetails.getPropertyName());
		transactionModuleDetailsImpl.setFlag(transactionModuleDetails.getFlag());
		transactionModuleDetailsImpl.setTransactionModuleDetailsSid(transactionModuleDetails.getTransactionModuleDetailsSid());

		return transactionModuleDetailsImpl;
	}

	/**
	 * Returns the transaction module details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction module details
	 * @return the transaction module details
	 * @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTransactionModuleDetailsException {
		TransactionModuleDetails transactionModuleDetails = fetchByPrimaryKey(primaryKey);

		if (transactionModuleDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTransactionModuleDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return transactionModuleDetails;
	}

	/**
	 * Returns the transaction module details with the primary key or throws a {@link NoSuchTransactionModuleDetailsException} if it could not be found.
	 *
	 * @param transactionModuleDetailsSid the primary key of the transaction module details
	 * @return the transaction module details
	 * @throws NoSuchTransactionModuleDetailsException if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails findByPrimaryKey(
		int transactionModuleDetailsSid)
		throws NoSuchTransactionModuleDetailsException {
		return findByPrimaryKey((Serializable)transactionModuleDetailsSid);
	}

	/**
	 * Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the transaction module details
	 * @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
				TransactionModuleDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		TransactionModuleDetails transactionModuleDetails = (TransactionModuleDetails)serializable;

		if (transactionModuleDetails == null) {
			Session session = null;

			try {
				session = openSession();

				transactionModuleDetails = (TransactionModuleDetails)session.get(TransactionModuleDetailsImpl.class,
						primaryKey);

				if (transactionModuleDetails != null) {
					cacheResult(transactionModuleDetails);
				}
				else {
					entityCache.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
						TransactionModuleDetailsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return transactionModuleDetails;
	}

	/**
	 * Returns the transaction module details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionModuleDetailsSid the primary key of the transaction module details
	 * @return the transaction module details, or <code>null</code> if a transaction module details with the primary key could not be found
	 */
	@Override
	public TransactionModuleDetails fetchByPrimaryKey(
		int transactionModuleDetailsSid) {
		return fetchByPrimaryKey((Serializable)transactionModuleDetailsSid);
	}

	@Override
	public Map<Serializable, TransactionModuleDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, TransactionModuleDetails> map = new HashMap<Serializable, TransactionModuleDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			TransactionModuleDetails transactionModuleDetails = fetchByPrimaryKey(primaryKey);

			if (transactionModuleDetails != null) {
				map.put(primaryKey, transactionModuleDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (TransactionModuleDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TRANSACTIONMODULEDETAILS_WHERE_PKS_IN);

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

			for (TransactionModuleDetails transactionModuleDetails : (List<TransactionModuleDetails>)q.list()) {
				map.put(transactionModuleDetails.getPrimaryKeyObj(),
					transactionModuleDetails);

				cacheResult(transactionModuleDetails);

				uncachedPrimaryKeys.remove(transactionModuleDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(TransactionModuleDetailsModelImpl.ENTITY_CACHE_ENABLED,
					TransactionModuleDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the transaction module detailses.
	 *
	 * @return the transaction module detailses
	 */
	@Override
	public List<TransactionModuleDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the transaction module detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module detailses
	 * @param end the upper bound of the range of transaction module detailses (not inclusive)
	 * @return the range of transaction module detailses
	 */
	@Override
	public List<TransactionModuleDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the transaction module detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module detailses
	 * @param end the upper bound of the range of transaction module detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of transaction module detailses
	 */
	@Override
	public List<TransactionModuleDetails> findAll(int start, int end,
		OrderByComparator<TransactionModuleDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the transaction module detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TransactionModuleDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of transaction module detailses
	 * @param end the upper bound of the range of transaction module detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of transaction module detailses
	 */
	@Override
	public List<TransactionModuleDetails> findAll(int start, int end,
		OrderByComparator<TransactionModuleDetails> orderByComparator,
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

		List<TransactionModuleDetails> list = null;

		if (retrieveFromCache) {
			list = (List<TransactionModuleDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_TRANSACTIONMODULEDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TRANSACTIONMODULEDETAILS;

				if (pagination) {
					sql = sql.concat(TransactionModuleDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<TransactionModuleDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<TransactionModuleDetails>)QueryUtil.list(q,
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
	 * Removes all the transaction module detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (TransactionModuleDetails transactionModuleDetails : findAll()) {
			remove(transactionModuleDetails);
		}
	}

	/**
	 * Returns the number of transaction module detailses.
	 *
	 * @return the number of transaction module detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TRANSACTIONMODULEDETAILS);

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
		return TransactionModuleDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the transaction module details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(TransactionModuleDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_TRANSACTIONMODULEDETAILS = "SELECT transactionModuleDetails FROM TransactionModuleDetails transactionModuleDetails";
	private static final String _SQL_SELECT_TRANSACTIONMODULEDETAILS_WHERE_PKS_IN =
		"SELECT transactionModuleDetails FROM TransactionModuleDetails transactionModuleDetails WHERE TRANSACTION_MODULE_DETAILS_SID IN (";
	private static final String _SQL_COUNT_TRANSACTIONMODULEDETAILS = "SELECT COUNT(transactionModuleDetails) FROM TransactionModuleDetails transactionModuleDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "transactionModuleDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TransactionModuleDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(TransactionModuleDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"propertyIndex", "displayName", "transactionModuleMasterSid",
				"categoryName", "validation", "propertyName", "flag",
				"transactionModuleDetailsSid"
			});
}