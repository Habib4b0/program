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

import com.stpl.app.exception.NoSuchCcpDetailsException;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.impl.CcpDetailsImpl;
import com.stpl.app.model.impl.CcpDetailsModelImpl;
import com.stpl.app.service.persistence.CcpDetailsPersistence;

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
 * The persistence implementation for the ccp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpDetailsPersistence
 * @see com.stpl.app.service.persistence.CcpDetailsUtil
 * @generated
 */
@ProviderType
public class CcpDetailsPersistenceImpl extends BasePersistenceImpl<CcpDetails>
	implements CcpDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CcpDetailsUtil} to access the ccp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CcpDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsModelImpl.FINDER_CACHE_ENABLED, CcpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsModelImpl.FINDER_CACHE_ENABLED, CcpDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CcpDetailsPersistenceImpl() {
		setModelClass(CcpDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("ccpDetailsSid", "CCP_DETAILS_SID");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ccp details in the entity cache if it is enabled.
	 *
	 * @param ccpDetails the ccp details
	 */
	@Override
	public void cacheResult(CcpDetails ccpDetails) {
		entityCache.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsImpl.class, ccpDetails.getPrimaryKey(), ccpDetails);

		ccpDetails.resetOriginalValues();
	}

	/**
	 * Caches the ccp detailses in the entity cache if it is enabled.
	 *
	 * @param ccpDetailses the ccp detailses
	 */
	@Override
	public void cacheResult(List<CcpDetails> ccpDetailses) {
		for (CcpDetails ccpDetails : ccpDetailses) {
			if (entityCache.getResult(
						CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CcpDetailsImpl.class, ccpDetails.getPrimaryKey()) == null) {
				cacheResult(ccpDetails);
			}
			else {
				ccpDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ccp detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CcpDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ccp details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CcpDetails ccpDetails) {
		entityCache.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsImpl.class, ccpDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CcpDetails> ccpDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CcpDetails ccpDetails : ccpDetailses) {
			entityCache.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CcpDetailsImpl.class, ccpDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ccp details with the primary key. Does not add the ccp details to the database.
	 *
	 * @param ccpDetailsSid the primary key for the new ccp details
	 * @return the new ccp details
	 */
	@Override
	public CcpDetails create(int ccpDetailsSid) {
		CcpDetails ccpDetails = new CcpDetailsImpl();

		ccpDetails.setNew(true);
		ccpDetails.setPrimaryKey(ccpDetailsSid);

		return ccpDetails;
	}

	/**
	 * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ccpDetailsSid the primary key of the ccp details
	 * @return the ccp details that was removed
	 * @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails remove(int ccpDetailsSid)
		throws NoSuchCcpDetailsException {
		return remove((Serializable)ccpDetailsSid);
	}

	/**
	 * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ccp details
	 * @return the ccp details that was removed
	 * @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails remove(Serializable primaryKey)
		throws NoSuchCcpDetailsException {
		Session session = null;

		try {
			session = openSession();

			CcpDetails ccpDetails = (CcpDetails)session.get(CcpDetailsImpl.class,
					primaryKey);

			if (ccpDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCcpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ccpDetails);
		}
		catch (NoSuchCcpDetailsException nsee) {
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
	protected CcpDetails removeImpl(CcpDetails ccpDetails) {
		ccpDetails = toUnwrappedModel(ccpDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ccpDetails)) {
				ccpDetails = (CcpDetails)session.get(CcpDetailsImpl.class,
						ccpDetails.getPrimaryKeyObj());
			}

			if (ccpDetails != null) {
				session.delete(ccpDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ccpDetails != null) {
			clearCache(ccpDetails);
		}

		return ccpDetails;
	}

	@Override
	public CcpDetails updateImpl(CcpDetails ccpDetails) {
		ccpDetails = toUnwrappedModel(ccpDetails);

		boolean isNew = ccpDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ccpDetails.isNew()) {
				session.save(ccpDetails);

				ccpDetails.setNew(false);
			}
			else {
				ccpDetails = (CcpDetails)session.merge(ccpDetails);
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

		entityCache.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CcpDetailsImpl.class, ccpDetails.getPrimaryKey(), ccpDetails, false);

		ccpDetails.resetOriginalValues();

		return ccpDetails;
	}

	protected CcpDetails toUnwrappedModel(CcpDetails ccpDetails) {
		if (ccpDetails instanceof CcpDetailsImpl) {
			return ccpDetails;
		}

		CcpDetailsImpl ccpDetailsImpl = new CcpDetailsImpl();

		ccpDetailsImpl.setNew(ccpDetails.isNew());
		ccpDetailsImpl.setPrimaryKey(ccpDetails.getPrimaryKey());

		ccpDetailsImpl.setItemMasterSid(ccpDetails.getItemMasterSid());
		ccpDetailsImpl.setContractMasterSid(ccpDetails.getContractMasterSid());
		ccpDetailsImpl.setCcpDetailsSid(ccpDetails.getCcpDetailsSid());
		ccpDetailsImpl.setCompanyMasterSid(ccpDetails.getCompanyMasterSid());

		return ccpDetailsImpl;
	}

	/**
	 * Returns the ccp details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ccp details
	 * @return the ccp details
	 * @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCcpDetailsException {
		CcpDetails ccpDetails = fetchByPrimaryKey(primaryKey);

		if (ccpDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCcpDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ccpDetails;
	}

	/**
	 * Returns the ccp details with the primary key or throws a {@link NoSuchCcpDetailsException} if it could not be found.
	 *
	 * @param ccpDetailsSid the primary key of the ccp details
	 * @return the ccp details
	 * @throws NoSuchCcpDetailsException if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails findByPrimaryKey(int ccpDetailsSid)
		throws NoSuchCcpDetailsException {
		return findByPrimaryKey((Serializable)ccpDetailsSid);
	}

	/**
	 * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ccp details
	 * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CcpDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CcpDetails ccpDetails = (CcpDetails)serializable;

		if (ccpDetails == null) {
			Session session = null;

			try {
				session = openSession();

				ccpDetails = (CcpDetails)session.get(CcpDetailsImpl.class,
						primaryKey);

				if (ccpDetails != null) {
					cacheResult(ccpDetails);
				}
				else {
					entityCache.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CcpDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CcpDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ccpDetails;
	}

	/**
	 * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ccpDetailsSid the primary key of the ccp details
	 * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
	 */
	@Override
	public CcpDetails fetchByPrimaryKey(int ccpDetailsSid) {
		return fetchByPrimaryKey((Serializable)ccpDetailsSid);
	}

	@Override
	public Map<Serializable, CcpDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CcpDetails> map = new HashMap<Serializable, CcpDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CcpDetails ccpDetails = fetchByPrimaryKey(primaryKey);

			if (ccpDetails != null) {
				map.put(primaryKey, ccpDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CcpDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CcpDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CCPDETAILS_WHERE_PKS_IN);

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

			for (CcpDetails ccpDetails : (List<CcpDetails>)q.list()) {
				map.put(ccpDetails.getPrimaryKeyObj(), ccpDetails);

				cacheResult(ccpDetails);

				uncachedPrimaryKeys.remove(ccpDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CcpDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CcpDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ccp detailses.
	 *
	 * @return the ccp detailses
	 */
	@Override
	public List<CcpDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ccp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp detailses
	 * @param end the upper bound of the range of ccp detailses (not inclusive)
	 * @return the range of ccp detailses
	 */
	@Override
	public List<CcpDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ccp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp detailses
	 * @param end the upper bound of the range of ccp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ccp detailses
	 */
	@Override
	public List<CcpDetails> findAll(int start, int end,
		OrderByComparator<CcpDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ccp detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ccp detailses
	 * @param end the upper bound of the range of ccp detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ccp detailses
	 */
	@Override
	public List<CcpDetails> findAll(int start, int end,
		OrderByComparator<CcpDetails> orderByComparator,
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

		List<CcpDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CcpDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CCPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CCPDETAILS;

				if (pagination) {
					sql = sql.concat(CcpDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CcpDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CcpDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ccp detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CcpDetails ccpDetails : findAll()) {
			remove(ccpDetails);
		}
	}

	/**
	 * Returns the number of ccp detailses.
	 *
	 * @return the number of ccp detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CCPDETAILS);

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
		return CcpDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ccp details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CcpDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CCPDETAILS = "SELECT ccpDetails FROM CcpDetails ccpDetails";
	private static final String _SQL_SELECT_CCPDETAILS_WHERE_PKS_IN = "SELECT ccpDetails FROM CcpDetails ccpDetails WHERE CCP_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CCPDETAILS = "SELECT COUNT(ccpDetails) FROM CcpDetails ccpDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ccpDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CcpDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CcpDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemMasterSid", "contractMasterSid", "ccpDetailsSid",
				"companyMasterSid"
			});
}