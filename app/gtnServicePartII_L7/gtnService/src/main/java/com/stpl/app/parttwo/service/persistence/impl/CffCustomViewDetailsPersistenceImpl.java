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

import com.stpl.app.parttwo.exception.NoSuchCffCustomViewDetailsException;
import com.stpl.app.parttwo.model.CffCustomViewDetails;
import com.stpl.app.parttwo.model.impl.CffCustomViewDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffCustomViewDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsPersistence;

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
 * The persistence implementation for the cff custom view details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffCustomViewDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffCustomViewDetailsUtil
 * @generated
 */
@ProviderType
public class CffCustomViewDetailsPersistenceImpl extends BasePersistenceImpl<CffCustomViewDetails>
	implements CffCustomViewDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffCustomViewDetailsUtil} to access the cff custom view details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffCustomViewDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffCustomViewDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffCustomViewDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffCustomViewDetailsPersistenceImpl() {
		setModelClass(CffCustomViewDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("hierarchyId", "HIERARCHY_ID");
			dbColumnNames.put("hierarchyIndicator", "HIERARCHY_INDICATOR");
			dbColumnNames.put("cffCustomViewDetailsSid",
				"CFF_CUSTOM_VIEW_DETAILS_SID");
			dbColumnNames.put("levelNo", "LEVEL_NO");
			dbColumnNames.put("cffCustomViewMasterSid",
				"CFF_CUSTOM_VIEW_MASTER_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff custom view details in the entity cache if it is enabled.
	 *
	 * @param cffCustomViewDetails the cff custom view details
	 */
	@Override
	public void cacheResult(CffCustomViewDetails cffCustomViewDetails) {
		entityCache.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsImpl.class,
			cffCustomViewDetails.getPrimaryKey(), cffCustomViewDetails);

		cffCustomViewDetails.resetOriginalValues();
	}

	/**
	 * Caches the cff custom view detailses in the entity cache if it is enabled.
	 *
	 * @param cffCustomViewDetailses the cff custom view detailses
	 */
	@Override
	public void cacheResult(List<CffCustomViewDetails> cffCustomViewDetailses) {
		for (CffCustomViewDetails cffCustomViewDetails : cffCustomViewDetailses) {
			if (entityCache.getResult(
						CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffCustomViewDetailsImpl.class,
						cffCustomViewDetails.getPrimaryKey()) == null) {
				cacheResult(cffCustomViewDetails);
			}
			else {
				cffCustomViewDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff custom view detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffCustomViewDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff custom view details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffCustomViewDetails cffCustomViewDetails) {
		entityCache.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsImpl.class, cffCustomViewDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffCustomViewDetails> cffCustomViewDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffCustomViewDetails cffCustomViewDetails : cffCustomViewDetailses) {
			entityCache.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffCustomViewDetailsImpl.class,
				cffCustomViewDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff custom view details with the primary key. Does not add the cff custom view details to the database.
	 *
	 * @param cffCustomViewDetailsSid the primary key for the new cff custom view details
	 * @return the new cff custom view details
	 */
	@Override
	public CffCustomViewDetails create(int cffCustomViewDetailsSid) {
		CffCustomViewDetails cffCustomViewDetails = new CffCustomViewDetailsImpl();

		cffCustomViewDetails.setNew(true);
		cffCustomViewDetails.setPrimaryKey(cffCustomViewDetailsSid);

		return cffCustomViewDetails;
	}

	/**
	 * Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffCustomViewDetailsSid the primary key of the cff custom view details
	 * @return the cff custom view details that was removed
	 * @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails remove(int cffCustomViewDetailsSid)
		throws NoSuchCffCustomViewDetailsException {
		return remove((Serializable)cffCustomViewDetailsSid);
	}

	/**
	 * Removes the cff custom view details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff custom view details
	 * @return the cff custom view details that was removed
	 * @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails remove(Serializable primaryKey)
		throws NoSuchCffCustomViewDetailsException {
		Session session = null;

		try {
			session = openSession();

			CffCustomViewDetails cffCustomViewDetails = (CffCustomViewDetails)session.get(CffCustomViewDetailsImpl.class,
					primaryKey);

			if (cffCustomViewDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffCustomViewDetails);
		}
		catch (NoSuchCffCustomViewDetailsException nsee) {
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
	protected CffCustomViewDetails removeImpl(
		CffCustomViewDetails cffCustomViewDetails) {
		cffCustomViewDetails = toUnwrappedModel(cffCustomViewDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffCustomViewDetails)) {
				cffCustomViewDetails = (CffCustomViewDetails)session.get(CffCustomViewDetailsImpl.class,
						cffCustomViewDetails.getPrimaryKeyObj());
			}

			if (cffCustomViewDetails != null) {
				session.delete(cffCustomViewDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffCustomViewDetails != null) {
			clearCache(cffCustomViewDetails);
		}

		return cffCustomViewDetails;
	}

	@Override
	public CffCustomViewDetails updateImpl(
		CffCustomViewDetails cffCustomViewDetails) {
		cffCustomViewDetails = toUnwrappedModel(cffCustomViewDetails);

		boolean isNew = cffCustomViewDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffCustomViewDetails.isNew()) {
				session.save(cffCustomViewDetails);

				cffCustomViewDetails.setNew(false);
			}
			else {
				cffCustomViewDetails = (CffCustomViewDetails)session.merge(cffCustomViewDetails);
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

		entityCache.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffCustomViewDetailsImpl.class,
			cffCustomViewDetails.getPrimaryKey(), cffCustomViewDetails, false);

		cffCustomViewDetails.resetOriginalValues();

		return cffCustomViewDetails;
	}

	protected CffCustomViewDetails toUnwrappedModel(
		CffCustomViewDetails cffCustomViewDetails) {
		if (cffCustomViewDetails instanceof CffCustomViewDetailsImpl) {
			return cffCustomViewDetails;
		}

		CffCustomViewDetailsImpl cffCustomViewDetailsImpl = new CffCustomViewDetailsImpl();

		cffCustomViewDetailsImpl.setNew(cffCustomViewDetails.isNew());
		cffCustomViewDetailsImpl.setPrimaryKey(cffCustomViewDetails.getPrimaryKey());

		cffCustomViewDetailsImpl.setHierarchyId(cffCustomViewDetails.getHierarchyId());
		cffCustomViewDetailsImpl.setHierarchyIndicator(cffCustomViewDetails.getHierarchyIndicator());
		cffCustomViewDetailsImpl.setCffCustomViewDetailsSid(cffCustomViewDetails.getCffCustomViewDetailsSid());
		cffCustomViewDetailsImpl.setLevelNo(cffCustomViewDetails.getLevelNo());
		cffCustomViewDetailsImpl.setCffCustomViewMasterSid(cffCustomViewDetails.getCffCustomViewMasterSid());

		return cffCustomViewDetailsImpl;
	}

	/**
	 * Returns the cff custom view details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff custom view details
	 * @return the cff custom view details
	 * @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffCustomViewDetailsException {
		CffCustomViewDetails cffCustomViewDetails = fetchByPrimaryKey(primaryKey);

		if (cffCustomViewDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffCustomViewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffCustomViewDetails;
	}

	/**
	 * Returns the cff custom view details with the primary key or throws a {@link NoSuchCffCustomViewDetailsException} if it could not be found.
	 *
	 * @param cffCustomViewDetailsSid the primary key of the cff custom view details
	 * @return the cff custom view details
	 * @throws NoSuchCffCustomViewDetailsException if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails findByPrimaryKey(int cffCustomViewDetailsSid)
		throws NoSuchCffCustomViewDetailsException {
		return findByPrimaryKey((Serializable)cffCustomViewDetailsSid);
	}

	/**
	 * Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff custom view details
	 * @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffCustomViewDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffCustomViewDetails cffCustomViewDetails = (CffCustomViewDetails)serializable;

		if (cffCustomViewDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cffCustomViewDetails = (CffCustomViewDetails)session.get(CffCustomViewDetailsImpl.class,
						primaryKey);

				if (cffCustomViewDetails != null) {
					cacheResult(cffCustomViewDetails);
				}
				else {
					entityCache.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffCustomViewDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffCustomViewDetails;
	}

	/**
	 * Returns the cff custom view details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffCustomViewDetailsSid the primary key of the cff custom view details
	 * @return the cff custom view details, or <code>null</code> if a cff custom view details with the primary key could not be found
	 */
	@Override
	public CffCustomViewDetails fetchByPrimaryKey(int cffCustomViewDetailsSid) {
		return fetchByPrimaryKey((Serializable)cffCustomViewDetailsSid);
	}

	@Override
	public Map<Serializable, CffCustomViewDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffCustomViewDetails> map = new HashMap<Serializable, CffCustomViewDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffCustomViewDetails cffCustomViewDetails = fetchByPrimaryKey(primaryKey);

			if (cffCustomViewDetails != null) {
				map.put(primaryKey, cffCustomViewDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffCustomViewDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFCUSTOMVIEWDETAILS_WHERE_PKS_IN);

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

			for (CffCustomViewDetails cffCustomViewDetails : (List<CffCustomViewDetails>)q.list()) {
				map.put(cffCustomViewDetails.getPrimaryKeyObj(),
					cffCustomViewDetails);

				cacheResult(cffCustomViewDetails);

				uncachedPrimaryKeys.remove(cffCustomViewDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffCustomViewDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffCustomViewDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff custom view detailses.
	 *
	 * @return the cff custom view detailses
	 */
	@Override
	public List<CffCustomViewDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view detailses
	 * @param end the upper bound of the range of cff custom view detailses (not inclusive)
	 * @return the range of cff custom view detailses
	 */
	@Override
	public List<CffCustomViewDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view detailses
	 * @param end the upper bound of the range of cff custom view detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff custom view detailses
	 */
	@Override
	public List<CffCustomViewDetails> findAll(int start, int end,
		OrderByComparator<CffCustomViewDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff custom view detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffCustomViewDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff custom view detailses
	 * @param end the upper bound of the range of cff custom view detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff custom view detailses
	 */
	@Override
	public List<CffCustomViewDetails> findAll(int start, int end,
		OrderByComparator<CffCustomViewDetails> orderByComparator,
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

		List<CffCustomViewDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CffCustomViewDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFCUSTOMVIEWDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFCUSTOMVIEWDETAILS;

				if (pagination) {
					sql = sql.concat(CffCustomViewDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffCustomViewDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffCustomViewDetails>)QueryUtil.list(q,
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
	 * Removes all the cff custom view detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffCustomViewDetails cffCustomViewDetails : findAll()) {
			remove(cffCustomViewDetails);
		}
	}

	/**
	 * Returns the number of cff custom view detailses.
	 *
	 * @return the number of cff custom view detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFCUSTOMVIEWDETAILS);

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
		return CffCustomViewDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff custom view details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffCustomViewDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFCUSTOMVIEWDETAILS = "SELECT cffCustomViewDetails FROM CffCustomViewDetails cffCustomViewDetails";
	private static final String _SQL_SELECT_CFFCUSTOMVIEWDETAILS_WHERE_PKS_IN = "SELECT cffCustomViewDetails FROM CffCustomViewDetails cffCustomViewDetails WHERE CFF_CUSTOM_VIEW_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CFFCUSTOMVIEWDETAILS = "SELECT COUNT(cffCustomViewDetails) FROM CffCustomViewDetails cffCustomViewDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffCustomViewDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffCustomViewDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffCustomViewDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"hierarchyId", "hierarchyIndicator", "cffCustomViewDetailsSid",
				"levelNo", "cffCustomViewMasterSid"
			});
}