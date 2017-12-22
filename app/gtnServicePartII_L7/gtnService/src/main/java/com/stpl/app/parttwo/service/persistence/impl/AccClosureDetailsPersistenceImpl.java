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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchAccClosureDetailsException;
import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.model.impl.AccClosureDetailsImpl;
import com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureDetailsPersistence;

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
 * The persistence implementation for the acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.AccClosureDetailsUtil
 * @generated
 */
@ProviderType
public class AccClosureDetailsPersistenceImpl extends BasePersistenceImpl<AccClosureDetails>
	implements AccClosureDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccClosureDetailsUtil} to access the acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccClosureDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
			AccClosureDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED,
			AccClosureDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccClosureDetailsPersistenceImpl() {
		setModelClass(AccClosureDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("accClosureDetailsSid", "ACC_CLOSURE_DETAILS_SID");
			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the acc closure details in the entity cache if it is enabled.
	 *
	 * @param accClosureDetails the acc closure details
	 */
	@Override
	public void cacheResult(AccClosureDetails accClosureDetails) {
		entityCache.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey(),
			accClosureDetails);

		accClosureDetails.resetOriginalValues();
	}

	/**
	 * Caches the acc closure detailses in the entity cache if it is enabled.
	 *
	 * @param accClosureDetailses the acc closure detailses
	 */
	@Override
	public void cacheResult(List<AccClosureDetails> accClosureDetailses) {
		for (AccClosureDetails accClosureDetails : accClosureDetailses) {
			if (entityCache.getResult(
						AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureDetailsImpl.class,
						accClosureDetails.getPrimaryKey()) == null) {
				cacheResult(accClosureDetails);
			}
			else {
				accClosureDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all acc closure detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccClosureDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the acc closure details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccClosureDetails accClosureDetails) {
		entityCache.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccClosureDetails> accClosureDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccClosureDetails accClosureDetails : accClosureDetailses) {
			entityCache.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new acc closure details with the primary key. Does not add the acc closure details to the database.
	 *
	 * @param accClosureDetailsSid the primary key for the new acc closure details
	 * @return the new acc closure details
	 */
	@Override
	public AccClosureDetails create(int accClosureDetailsSid) {
		AccClosureDetails accClosureDetails = new AccClosureDetailsImpl();

		accClosureDetails.setNew(true);
		accClosureDetails.setPrimaryKey(accClosureDetailsSid);

		return accClosureDetails;
	}

	/**
	 * Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accClosureDetailsSid the primary key of the acc closure details
	 * @return the acc closure details that was removed
	 * @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails remove(int accClosureDetailsSid)
		throws NoSuchAccClosureDetailsException {
		return remove((Serializable)accClosureDetailsSid);
	}

	/**
	 * Removes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the acc closure details
	 * @return the acc closure details that was removed
	 * @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails remove(Serializable primaryKey)
		throws NoSuchAccClosureDetailsException {
		Session session = null;

		try {
			session = openSession();

			AccClosureDetails accClosureDetails = (AccClosureDetails)session.get(AccClosureDetailsImpl.class,
					primaryKey);

			if (accClosureDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accClosureDetails);
		}
		catch (NoSuchAccClosureDetailsException nsee) {
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
	protected AccClosureDetails removeImpl(AccClosureDetails accClosureDetails) {
		accClosureDetails = toUnwrappedModel(accClosureDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accClosureDetails)) {
				accClosureDetails = (AccClosureDetails)session.get(AccClosureDetailsImpl.class,
						accClosureDetails.getPrimaryKeyObj());
			}

			if (accClosureDetails != null) {
				session.delete(accClosureDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accClosureDetails != null) {
			clearCache(accClosureDetails);
		}

		return accClosureDetails;
	}

	@Override
	public AccClosureDetails updateImpl(AccClosureDetails accClosureDetails) {
		accClosureDetails = toUnwrappedModel(accClosureDetails);

		boolean isNew = accClosureDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (accClosureDetails.isNew()) {
				session.save(accClosureDetails);

				accClosureDetails.setNew(false);
			}
			else {
				accClosureDetails = (AccClosureDetails)session.merge(accClosureDetails);
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

		entityCache.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureDetailsImpl.class, accClosureDetails.getPrimaryKey(),
			accClosureDetails, false);

		accClosureDetails.resetOriginalValues();

		return accClosureDetails;
	}

	protected AccClosureDetails toUnwrappedModel(
		AccClosureDetails accClosureDetails) {
		if (accClosureDetails instanceof AccClosureDetailsImpl) {
			return accClosureDetails;
		}

		AccClosureDetailsImpl accClosureDetailsImpl = new AccClosureDetailsImpl();

		accClosureDetailsImpl.setNew(accClosureDetails.isNew());
		accClosureDetailsImpl.setPrimaryKey(accClosureDetails.getPrimaryKey());

		accClosureDetailsImpl.setAccClosureDetailsSid(accClosureDetails.getAccClosureDetailsSid());
		accClosureDetailsImpl.setCcpDetailsSid(accClosureDetails.getCcpDetailsSid());
		accClosureDetailsImpl.setAccClosureMasterSid(accClosureDetails.getAccClosureMasterSid());
		accClosureDetailsImpl.setRsModelSid(accClosureDetails.getRsModelSid());

		return accClosureDetailsImpl;
	}

	/**
	 * Returns the acc closure details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure details
	 * @return the acc closure details
	 * @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccClosureDetailsException {
		AccClosureDetails accClosureDetails = fetchByPrimaryKey(primaryKey);

		if (accClosureDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccClosureDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accClosureDetails;
	}

	/**
	 * Returns the acc closure details with the primary key or throws a {@link NoSuchAccClosureDetailsException} if it could not be found.
	 *
	 * @param accClosureDetailsSid the primary key of the acc closure details
	 * @return the acc closure details
	 * @throws NoSuchAccClosureDetailsException if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails findByPrimaryKey(int accClosureDetailsSid)
		throws NoSuchAccClosureDetailsException {
		return findByPrimaryKey((Serializable)accClosureDetailsSid);
	}

	/**
	 * Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure details
	 * @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccClosureDetails accClosureDetails = (AccClosureDetails)serializable;

		if (accClosureDetails == null) {
			Session session = null;

			try {
				session = openSession();

				accClosureDetails = (AccClosureDetails)session.get(AccClosureDetailsImpl.class,
						primaryKey);

				if (accClosureDetails != null) {
					cacheResult(accClosureDetails);
				}
				else {
					entityCache.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accClosureDetails;
	}

	/**
	 * Returns the acc closure details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accClosureDetailsSid the primary key of the acc closure details
	 * @return the acc closure details, or <code>null</code> if a acc closure details with the primary key could not be found
	 */
	@Override
	public AccClosureDetails fetchByPrimaryKey(int accClosureDetailsSid) {
		return fetchByPrimaryKey((Serializable)accClosureDetailsSid);
	}

	@Override
	public Map<Serializable, AccClosureDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccClosureDetails> map = new HashMap<Serializable, AccClosureDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccClosureDetails accClosureDetails = fetchByPrimaryKey(primaryKey);

			if (accClosureDetails != null) {
				map.put(primaryKey, accClosureDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccClosureDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCCLOSUREDETAILS_WHERE_PKS_IN);

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

			for (AccClosureDetails accClosureDetails : (List<AccClosureDetails>)q.list()) {
				map.put(accClosureDetails.getPrimaryKeyObj(), accClosureDetails);

				cacheResult(accClosureDetails);

				uncachedPrimaryKeys.remove(accClosureDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccClosureDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the acc closure detailses.
	 *
	 * @return the acc closure detailses
	 */
	@Override
	public List<AccClosureDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure detailses
	 * @param end the upper bound of the range of acc closure detailses (not inclusive)
	 * @return the range of acc closure detailses
	 */
	@Override
	public List<AccClosureDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure detailses
	 * @param end the upper bound of the range of acc closure detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of acc closure detailses
	 */
	@Override
	public List<AccClosureDetails> findAll(int start, int end,
		OrderByComparator<AccClosureDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the acc closure detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure detailses
	 * @param end the upper bound of the range of acc closure detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of acc closure detailses
	 */
	@Override
	public List<AccClosureDetails> findAll(int start, int end,
		OrderByComparator<AccClosureDetails> orderByComparator,
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

		List<AccClosureDetails> list = null;

		if (retrieveFromCache) {
			list = (List<AccClosureDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCCLOSUREDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCCLOSUREDETAILS;

				if (pagination) {
					sql = sql.concat(AccClosureDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccClosureDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccClosureDetails>)QueryUtil.list(q,
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
	 * Removes all the acc closure detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccClosureDetails accClosureDetails : findAll()) {
			remove(accClosureDetails);
		}
	}

	/**
	 * Returns the number of acc closure detailses.
	 *
	 * @return the number of acc closure detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREDETAILS);

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
		return AccClosureDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the acc closure details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccClosureDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACCCLOSUREDETAILS = "SELECT accClosureDetails FROM AccClosureDetails accClosureDetails";
	private static final String _SQL_SELECT_ACCCLOSUREDETAILS_WHERE_PKS_IN = "SELECT accClosureDetails FROM AccClosureDetails accClosureDetails WHERE ACC_CLOSURE_DETAILS_SID IN (";
	private static final String _SQL_COUNT_ACCCLOSUREDETAILS = "SELECT COUNT(accClosureDetails) FROM AccClosureDetails accClosureDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccClosureDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"accClosureDetailsSid", "ccpDetailsSid", "accClosureMasterSid",
				"rsModelSid"
			});
}