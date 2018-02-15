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

import com.stpl.app.exception.NoSuchNaProjDetailsException;
import com.stpl.app.model.NaProjDetails;
import com.stpl.app.model.impl.NaProjDetailsImpl;
import com.stpl.app.model.impl.NaProjDetailsModelImpl;
import com.stpl.app.service.persistence.NaProjDetailsPersistence;

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
 * The persistence implementation for the na proj details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjDetailsPersistence
 * @see com.stpl.app.service.persistence.NaProjDetailsUtil
 * @generated
 */
@ProviderType
public class NaProjDetailsPersistenceImpl extends BasePersistenceImpl<NaProjDetails>
	implements NaProjDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NaProjDetailsUtil} to access the na proj details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NaProjDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsModelImpl.FINDER_CACHE_ENABLED,
			NaProjDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsModelImpl.FINDER_CACHE_ENABLED,
			NaProjDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NaProjDetailsPersistenceImpl() {
		setModelClass(NaProjDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("naProjMasterSid", "NA_PROJ_MASTER_SID");
			dbColumnNames.put("naProjDetailsSid", "NA_PROJ_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the na proj details in the entity cache if it is enabled.
	 *
	 * @param naProjDetails the na proj details
	 */
	@Override
	public void cacheResult(NaProjDetails naProjDetails) {
		entityCache.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsImpl.class, naProjDetails.getPrimaryKey(),
			naProjDetails);

		naProjDetails.resetOriginalValues();
	}

	/**
	 * Caches the na proj detailses in the entity cache if it is enabled.
	 *
	 * @param naProjDetailses the na proj detailses
	 */
	@Override
	public void cacheResult(List<NaProjDetails> naProjDetailses) {
		for (NaProjDetails naProjDetails : naProjDetailses) {
			if (entityCache.getResult(
						NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
						NaProjDetailsImpl.class, naProjDetails.getPrimaryKey()) == null) {
				cacheResult(naProjDetails);
			}
			else {
				naProjDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all na proj detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NaProjDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the na proj details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NaProjDetails naProjDetails) {
		entityCache.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsImpl.class, naProjDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NaProjDetails> naProjDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NaProjDetails naProjDetails : naProjDetailses) {
			entityCache.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
				NaProjDetailsImpl.class, naProjDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new na proj details with the primary key. Does not add the na proj details to the database.
	 *
	 * @param naProjDetailsSid the primary key for the new na proj details
	 * @return the new na proj details
	 */
	@Override
	public NaProjDetails create(int naProjDetailsSid) {
		NaProjDetails naProjDetails = new NaProjDetailsImpl();

		naProjDetails.setNew(true);
		naProjDetails.setPrimaryKey(naProjDetailsSid);

		return naProjDetails;
	}

	/**
	 * Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param naProjDetailsSid the primary key of the na proj details
	 * @return the na proj details that was removed
	 * @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails remove(int naProjDetailsSid)
		throws NoSuchNaProjDetailsException {
		return remove((Serializable)naProjDetailsSid);
	}

	/**
	 * Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the na proj details
	 * @return the na proj details that was removed
	 * @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails remove(Serializable primaryKey)
		throws NoSuchNaProjDetailsException {
		Session session = null;

		try {
			session = openSession();

			NaProjDetails naProjDetails = (NaProjDetails)session.get(NaProjDetailsImpl.class,
					primaryKey);

			if (naProjDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNaProjDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(naProjDetails);
		}
		catch (NoSuchNaProjDetailsException nsee) {
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
	protected NaProjDetails removeImpl(NaProjDetails naProjDetails) {
		naProjDetails = toUnwrappedModel(naProjDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(naProjDetails)) {
				naProjDetails = (NaProjDetails)session.get(NaProjDetailsImpl.class,
						naProjDetails.getPrimaryKeyObj());
			}

			if (naProjDetails != null) {
				session.delete(naProjDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (naProjDetails != null) {
			clearCache(naProjDetails);
		}

		return naProjDetails;
	}

	@Override
	public NaProjDetails updateImpl(NaProjDetails naProjDetails) {
		naProjDetails = toUnwrappedModel(naProjDetails);

		boolean isNew = naProjDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (naProjDetails.isNew()) {
				session.save(naProjDetails);

				naProjDetails.setNew(false);
			}
			else {
				naProjDetails = (NaProjDetails)session.merge(naProjDetails);
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

		entityCache.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
			NaProjDetailsImpl.class, naProjDetails.getPrimaryKey(),
			naProjDetails, false);

		naProjDetails.resetOriginalValues();

		return naProjDetails;
	}

	protected NaProjDetails toUnwrappedModel(NaProjDetails naProjDetails) {
		if (naProjDetails instanceof NaProjDetailsImpl) {
			return naProjDetails;
		}

		NaProjDetailsImpl naProjDetailsImpl = new NaProjDetailsImpl();

		naProjDetailsImpl.setNew(naProjDetails.isNew());
		naProjDetailsImpl.setPrimaryKey(naProjDetails.getPrimaryKey());

		naProjDetailsImpl.setItemMasterSid(naProjDetails.getItemMasterSid());
		naProjDetailsImpl.setNaProjMasterSid(naProjDetails.getNaProjMasterSid());
		naProjDetailsImpl.setNaProjDetailsSid(naProjDetails.getNaProjDetailsSid());

		return naProjDetailsImpl;
	}

	/**
	 * Returns the na proj details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the na proj details
	 * @return the na proj details
	 * @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNaProjDetailsException {
		NaProjDetails naProjDetails = fetchByPrimaryKey(primaryKey);

		if (naProjDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNaProjDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return naProjDetails;
	}

	/**
	 * Returns the na proj details with the primary key or throws a {@link NoSuchNaProjDetailsException} if it could not be found.
	 *
	 * @param naProjDetailsSid the primary key of the na proj details
	 * @return the na proj details
	 * @throws NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails findByPrimaryKey(int naProjDetailsSid)
		throws NoSuchNaProjDetailsException {
		return findByPrimaryKey((Serializable)naProjDetailsSid);
	}

	/**
	 * Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the na proj details
	 * @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
				NaProjDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NaProjDetails naProjDetails = (NaProjDetails)serializable;

		if (naProjDetails == null) {
			Session session = null;

			try {
				session = openSession();

				naProjDetails = (NaProjDetails)session.get(NaProjDetailsImpl.class,
						primaryKey);

				if (naProjDetails != null) {
					cacheResult(naProjDetails);
				}
				else {
					entityCache.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
						NaProjDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
					NaProjDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return naProjDetails;
	}

	/**
	 * Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param naProjDetailsSid the primary key of the na proj details
	 * @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
	 */
	@Override
	public NaProjDetails fetchByPrimaryKey(int naProjDetailsSid) {
		return fetchByPrimaryKey((Serializable)naProjDetailsSid);
	}

	@Override
	public Map<Serializable, NaProjDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NaProjDetails> map = new HashMap<Serializable, NaProjDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NaProjDetails naProjDetails = fetchByPrimaryKey(primaryKey);

			if (naProjDetails != null) {
				map.put(primaryKey, naProjDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
					NaProjDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NaProjDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NAPROJDETAILS_WHERE_PKS_IN);

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

			for (NaProjDetails naProjDetails : (List<NaProjDetails>)q.list()) {
				map.put(naProjDetails.getPrimaryKeyObj(), naProjDetails);

				cacheResult(naProjDetails);

				uncachedPrimaryKeys.remove(naProjDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NaProjDetailsModelImpl.ENTITY_CACHE_ENABLED,
					NaProjDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the na proj detailses.
	 *
	 * @return the na proj detailses
	 */
	@Override
	public List<NaProjDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the na proj detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj detailses
	 * @param end the upper bound of the range of na proj detailses (not inclusive)
	 * @return the range of na proj detailses
	 */
	@Override
	public List<NaProjDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the na proj detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj detailses
	 * @param end the upper bound of the range of na proj detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of na proj detailses
	 */
	@Override
	public List<NaProjDetails> findAll(int start, int end,
		OrderByComparator<NaProjDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the na proj detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of na proj detailses
	 * @param end the upper bound of the range of na proj detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of na proj detailses
	 */
	@Override
	public List<NaProjDetails> findAll(int start, int end,
		OrderByComparator<NaProjDetails> orderByComparator,
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

		List<NaProjDetails> list = null;

		if (retrieveFromCache) {
			list = (List<NaProjDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NAPROJDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NAPROJDETAILS;

				if (pagination) {
					sql = sql.concat(NaProjDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NaProjDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NaProjDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the na proj detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NaProjDetails naProjDetails : findAll()) {
			remove(naProjDetails);
		}
	}

	/**
	 * Returns the number of na proj detailses.
	 *
	 * @return the number of na proj detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NAPROJDETAILS);

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
		return NaProjDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the na proj details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NaProjDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NAPROJDETAILS = "SELECT naProjDetails FROM NaProjDetails naProjDetails";
	private static final String _SQL_SELECT_NAPROJDETAILS_WHERE_PKS_IN = "SELECT naProjDetails FROM NaProjDetails naProjDetails WHERE NA_PROJ_DETAILS_SID IN (";
	private static final String _SQL_COUNT_NAPROJDETAILS = "SELECT COUNT(naProjDetails) FROM NaProjDetails naProjDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "naProjDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NaProjDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NaProjDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "naProjMasterSid", "naProjDetailsSid"
			});
}