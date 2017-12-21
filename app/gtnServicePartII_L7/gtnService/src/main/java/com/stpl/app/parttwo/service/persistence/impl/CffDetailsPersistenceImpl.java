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

import com.stpl.app.parttwo.exception.NoSuchCffDetailsException;
import com.stpl.app.parttwo.model.CffDetails;
import com.stpl.app.parttwo.model.impl.CffDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffDetailsPersistence;

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
 * The persistence implementation for the cff details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffDetailsUtil
 * @generated
 */
@ProviderType
public class CffDetailsPersistenceImpl extends BasePersistenceImpl<CffDetails>
	implements CffDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffDetailsUtil} to access the cff details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsModelImpl.FINDER_CACHE_ENABLED, CffDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsModelImpl.FINDER_CACHE_ENABLED, CffDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffDetailsPersistenceImpl() {
		setModelClass(CffDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("projectionMasterSid", "PROJECTION_MASTER_SID");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("cffDetailsSid", "CFF_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff details in the entity cache if it is enabled.
	 *
	 * @param cffDetails the cff details
	 */
	@Override
	public void cacheResult(CffDetails cffDetails) {
		entityCache.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsImpl.class, cffDetails.getPrimaryKey(), cffDetails);

		cffDetails.resetOriginalValues();
	}

	/**
	 * Caches the cff detailses in the entity cache if it is enabled.
	 *
	 * @param cffDetailses the cff detailses
	 */
	@Override
	public void cacheResult(List<CffDetails> cffDetailses) {
		for (CffDetails cffDetails : cffDetailses) {
			if (entityCache.getResult(
						CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffDetailsImpl.class, cffDetails.getPrimaryKey()) == null) {
				cacheResult(cffDetails);
			}
			else {
				cffDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffDetails cffDetails) {
		entityCache.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsImpl.class, cffDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffDetails> cffDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffDetails cffDetails : cffDetailses) {
			entityCache.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffDetailsImpl.class, cffDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff details with the primary key. Does not add the cff details to the database.
	 *
	 * @param cffDetailsSid the primary key for the new cff details
	 * @return the new cff details
	 */
	@Override
	public CffDetails create(int cffDetailsSid) {
		CffDetails cffDetails = new CffDetailsImpl();

		cffDetails.setNew(true);
		cffDetails.setPrimaryKey(cffDetailsSid);

		return cffDetails;
	}

	/**
	 * Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffDetailsSid the primary key of the cff details
	 * @return the cff details that was removed
	 * @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails remove(int cffDetailsSid)
		throws NoSuchCffDetailsException {
		return remove((Serializable)cffDetailsSid);
	}

	/**
	 * Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff details
	 * @return the cff details that was removed
	 * @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails remove(Serializable primaryKey)
		throws NoSuchCffDetailsException {
		Session session = null;

		try {
			session = openSession();

			CffDetails cffDetails = (CffDetails)session.get(CffDetailsImpl.class,
					primaryKey);

			if (cffDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffDetails);
		}
		catch (NoSuchCffDetailsException nsee) {
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
	protected CffDetails removeImpl(CffDetails cffDetails) {
		cffDetails = toUnwrappedModel(cffDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffDetails)) {
				cffDetails = (CffDetails)session.get(CffDetailsImpl.class,
						cffDetails.getPrimaryKeyObj());
			}

			if (cffDetails != null) {
				session.delete(cffDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffDetails != null) {
			clearCache(cffDetails);
		}

		return cffDetails;
	}

	@Override
	public CffDetails updateImpl(CffDetails cffDetails) {
		cffDetails = toUnwrappedModel(cffDetails);

		boolean isNew = cffDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffDetails.isNew()) {
				session.save(cffDetails);

				cffDetails.setNew(false);
			}
			else {
				cffDetails = (CffDetails)session.merge(cffDetails);
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

		entityCache.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffDetailsImpl.class, cffDetails.getPrimaryKey(), cffDetails, false);

		cffDetails.resetOriginalValues();

		return cffDetails;
	}

	protected CffDetails toUnwrappedModel(CffDetails cffDetails) {
		if (cffDetails instanceof CffDetailsImpl) {
			return cffDetails;
		}

		CffDetailsImpl cffDetailsImpl = new CffDetailsImpl();

		cffDetailsImpl.setNew(cffDetails.isNew());
		cffDetailsImpl.setPrimaryKey(cffDetails.getPrimaryKey());

		cffDetailsImpl.setCcpDetailsSid(cffDetails.getCcpDetailsSid());
		cffDetailsImpl.setProjectionMasterSid(cffDetails.getProjectionMasterSid());
		cffDetailsImpl.setCffMasterSid(cffDetails.getCffMasterSid());
		cffDetailsImpl.setInboundStatus(cffDetails.getInboundStatus());
		cffDetailsImpl.setCffDetailsSid(cffDetails.getCffDetailsSid());

		return cffDetailsImpl;
	}

	/**
	 * Returns the cff details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff details
	 * @return the cff details
	 * @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffDetailsException {
		CffDetails cffDetails = fetchByPrimaryKey(primaryKey);

		if (cffDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffDetails;
	}

	/**
	 * Returns the cff details with the primary key or throws a {@link NoSuchCffDetailsException} if it could not be found.
	 *
	 * @param cffDetailsSid the primary key of the cff details
	 * @return the cff details
	 * @throws NoSuchCffDetailsException if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails findByPrimaryKey(int cffDetailsSid)
		throws NoSuchCffDetailsException {
		return findByPrimaryKey((Serializable)cffDetailsSid);
	}

	/**
	 * Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff details
	 * @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffDetails cffDetails = (CffDetails)serializable;

		if (cffDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cffDetails = (CffDetails)session.get(CffDetailsImpl.class,
						primaryKey);

				if (cffDetails != null) {
					cacheResult(cffDetails);
				}
				else {
					entityCache.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffDetails;
	}

	/**
	 * Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffDetailsSid the primary key of the cff details
	 * @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
	 */
	@Override
	public CffDetails fetchByPrimaryKey(int cffDetailsSid) {
		return fetchByPrimaryKey((Serializable)cffDetailsSid);
	}

	@Override
	public Map<Serializable, CffDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffDetails> map = new HashMap<Serializable, CffDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffDetails cffDetails = fetchByPrimaryKey(primaryKey);

			if (cffDetails != null) {
				map.put(primaryKey, cffDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFDETAILS_WHERE_PKS_IN);

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

			for (CffDetails cffDetails : (List<CffDetails>)q.list()) {
				map.put(cffDetails.getPrimaryKeyObj(), cffDetails);

				cacheResult(cffDetails);

				uncachedPrimaryKeys.remove(cffDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff detailses.
	 *
	 * @return the cff detailses
	 */
	@Override
	public List<CffDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff detailses
	 * @param end the upper bound of the range of cff detailses (not inclusive)
	 * @return the range of cff detailses
	 */
	@Override
	public List<CffDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff detailses
	 * @param end the upper bound of the range of cff detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff detailses
	 */
	@Override
	public List<CffDetails> findAll(int start, int end,
		OrderByComparator<CffDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff detailses
	 * @param end the upper bound of the range of cff detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff detailses
	 */
	@Override
	public List<CffDetails> findAll(int start, int end,
		OrderByComparator<CffDetails> orderByComparator,
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

		List<CffDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CffDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFDETAILS;

				if (pagination) {
					sql = sql.concat(CffDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cff detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffDetails cffDetails : findAll()) {
			remove(cffDetails);
		}
	}

	/**
	 * Returns the number of cff detailses.
	 *
	 * @return the number of cff detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFDETAILS);

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
		return CffDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFDETAILS = "SELECT cffDetails FROM CffDetails cffDetails";
	private static final String _SQL_SELECT_CFFDETAILS_WHERE_PKS_IN = "SELECT cffDetails FROM CffDetails cffDetails WHERE CFF_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CFFDETAILS = "SELECT COUNT(cffDetails) FROM CffDetails cffDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ccpDetailsSid", "projectionMasterSid", "cffMasterSid",
				"inboundStatus", "cffDetailsSid"
			});
}