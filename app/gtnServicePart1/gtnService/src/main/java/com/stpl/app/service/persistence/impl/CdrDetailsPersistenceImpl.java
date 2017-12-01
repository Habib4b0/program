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

import com.stpl.app.exception.NoSuchCdrDetailsException;
import com.stpl.app.model.CdrDetails;
import com.stpl.app.model.impl.CdrDetailsImpl;
import com.stpl.app.model.impl.CdrDetailsModelImpl;
import com.stpl.app.service.persistence.CdrDetailsPersistence;

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
 * The persistence implementation for the cdr details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrDetailsPersistence
 * @see com.stpl.app.service.persistence.CdrDetailsUtil
 * @generated
 */
@ProviderType
public class CdrDetailsPersistenceImpl extends BasePersistenceImpl<CdrDetails>
	implements CdrDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CdrDetailsUtil} to access the cdr details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CdrDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsModelImpl.FINDER_CACHE_ENABLED, CdrDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsModelImpl.FINDER_CACHE_ENABLED, CdrDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CdrDetailsPersistenceImpl() {
		setModelClass(CdrDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("lineType", "LINE_TYPE");
			dbColumnNames.put("keyword", "KEYWORD");
			dbColumnNames.put("itemGroupMsAssociation",
				"ITEM_GROUP_MS_ASSOCIATION");
			dbColumnNames.put("value", "VALUE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("logicalOperator", "LOGICAL_OPERATOR");
			dbColumnNames.put("operator", "OPERATOR");
			dbColumnNames.put("cdrDetailsSid", "CDR_DETAILS_SID");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");
			dbColumnNames.put("comparison", "COMPARISON");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cdr details in the entity cache if it is enabled.
	 *
	 * @param cdrDetails the cdr details
	 */
	@Override
	public void cacheResult(CdrDetails cdrDetails) {
		entityCache.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsImpl.class, cdrDetails.getPrimaryKey(), cdrDetails);

		cdrDetails.resetOriginalValues();
	}

	/**
	 * Caches the cdr detailses in the entity cache if it is enabled.
	 *
	 * @param cdrDetailses the cdr detailses
	 */
	@Override
	public void cacheResult(List<CdrDetails> cdrDetailses) {
		for (CdrDetails cdrDetails : cdrDetailses) {
			if (entityCache.getResult(
						CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CdrDetailsImpl.class, cdrDetails.getPrimaryKey()) == null) {
				cacheResult(cdrDetails);
			}
			else {
				cdrDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cdr detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CdrDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cdr details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CdrDetails cdrDetails) {
		entityCache.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsImpl.class, cdrDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CdrDetails> cdrDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CdrDetails cdrDetails : cdrDetailses) {
			entityCache.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CdrDetailsImpl.class, cdrDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cdr details with the primary key. Does not add the cdr details to the database.
	 *
	 * @param cdrDetailsSid the primary key for the new cdr details
	 * @return the new cdr details
	 */
	@Override
	public CdrDetails create(int cdrDetailsSid) {
		CdrDetails cdrDetails = new CdrDetailsImpl();

		cdrDetails.setNew(true);
		cdrDetails.setPrimaryKey(cdrDetailsSid);

		return cdrDetails;
	}

	/**
	 * Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cdrDetailsSid the primary key of the cdr details
	 * @return the cdr details that was removed
	 * @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails remove(int cdrDetailsSid)
		throws NoSuchCdrDetailsException {
		return remove((Serializable)cdrDetailsSid);
	}

	/**
	 * Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cdr details
	 * @return the cdr details that was removed
	 * @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails remove(Serializable primaryKey)
		throws NoSuchCdrDetailsException {
		Session session = null;

		try {
			session = openSession();

			CdrDetails cdrDetails = (CdrDetails)session.get(CdrDetailsImpl.class,
					primaryKey);

			if (cdrDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCdrDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cdrDetails);
		}
		catch (NoSuchCdrDetailsException nsee) {
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
	protected CdrDetails removeImpl(CdrDetails cdrDetails) {
		cdrDetails = toUnwrappedModel(cdrDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cdrDetails)) {
				cdrDetails = (CdrDetails)session.get(CdrDetailsImpl.class,
						cdrDetails.getPrimaryKeyObj());
			}

			if (cdrDetails != null) {
				session.delete(cdrDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cdrDetails != null) {
			clearCache(cdrDetails);
		}

		return cdrDetails;
	}

	@Override
	public CdrDetails updateImpl(CdrDetails cdrDetails) {
		cdrDetails = toUnwrappedModel(cdrDetails);

		boolean isNew = cdrDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cdrDetails.isNew()) {
				session.save(cdrDetails);

				cdrDetails.setNew(false);
			}
			else {
				cdrDetails = (CdrDetails)session.merge(cdrDetails);
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

		entityCache.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CdrDetailsImpl.class, cdrDetails.getPrimaryKey(), cdrDetails, false);

		cdrDetails.resetOriginalValues();

		return cdrDetails;
	}

	protected CdrDetails toUnwrappedModel(CdrDetails cdrDetails) {
		if (cdrDetails instanceof CdrDetailsImpl) {
			return cdrDetails;
		}

		CdrDetailsImpl cdrDetailsImpl = new CdrDetailsImpl();

		cdrDetailsImpl.setNew(cdrDetails.isNew());
		cdrDetailsImpl.setPrimaryKey(cdrDetails.getPrimaryKey());

		cdrDetailsImpl.setCreatedBy(cdrDetails.getCreatedBy());
		cdrDetailsImpl.setModifiedBy(cdrDetails.getModifiedBy());
		cdrDetailsImpl.setCreatedDate(cdrDetails.getCreatedDate());
		cdrDetailsImpl.setLineType(cdrDetails.getLineType());
		cdrDetailsImpl.setKeyword(cdrDetails.getKeyword());
		cdrDetailsImpl.setItemGroupMsAssociation(cdrDetails.getItemGroupMsAssociation());
		cdrDetailsImpl.setValue(cdrDetails.getValue());
		cdrDetailsImpl.setModifiedDate(cdrDetails.getModifiedDate());
		cdrDetailsImpl.setLogicalOperator(cdrDetails.getLogicalOperator());
		cdrDetailsImpl.setOperator(cdrDetails.getOperator());
		cdrDetailsImpl.setCdrDetailsSid(cdrDetails.getCdrDetailsSid());
		cdrDetailsImpl.setCdrModelSid(cdrDetails.getCdrModelSid());
		cdrDetailsImpl.setComparison(cdrDetails.getComparison());

		return cdrDetailsImpl;
	}

	/**
	 * Returns the cdr details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cdr details
	 * @return the cdr details
	 * @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCdrDetailsException {
		CdrDetails cdrDetails = fetchByPrimaryKey(primaryKey);

		if (cdrDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCdrDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cdrDetails;
	}

	/**
	 * Returns the cdr details with the primary key or throws a {@link NoSuchCdrDetailsException} if it could not be found.
	 *
	 * @param cdrDetailsSid the primary key of the cdr details
	 * @return the cdr details
	 * @throws NoSuchCdrDetailsException if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails findByPrimaryKey(int cdrDetailsSid)
		throws NoSuchCdrDetailsException {
		return findByPrimaryKey((Serializable)cdrDetailsSid);
	}

	/**
	 * Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cdr details
	 * @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CdrDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CdrDetails cdrDetails = (CdrDetails)serializable;

		if (cdrDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cdrDetails = (CdrDetails)session.get(CdrDetailsImpl.class,
						primaryKey);

				if (cdrDetails != null) {
					cacheResult(cdrDetails);
				}
				else {
					entityCache.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CdrDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CdrDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cdrDetails;
	}

	/**
	 * Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cdrDetailsSid the primary key of the cdr details
	 * @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
	 */
	@Override
	public CdrDetails fetchByPrimaryKey(int cdrDetailsSid) {
		return fetchByPrimaryKey((Serializable)cdrDetailsSid);
	}

	@Override
	public Map<Serializable, CdrDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CdrDetails> map = new HashMap<Serializable, CdrDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CdrDetails cdrDetails = fetchByPrimaryKey(primaryKey);

			if (cdrDetails != null) {
				map.put(primaryKey, cdrDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CdrDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CdrDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CDRDETAILS_WHERE_PKS_IN);

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

			for (CdrDetails cdrDetails : (List<CdrDetails>)q.list()) {
				map.put(cdrDetails.getPrimaryKeyObj(), cdrDetails);

				cacheResult(cdrDetails);

				uncachedPrimaryKeys.remove(cdrDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CdrDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CdrDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cdr detailses.
	 *
	 * @return the cdr detailses
	 */
	@Override
	public List<CdrDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cdr detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr detailses
	 * @param end the upper bound of the range of cdr detailses (not inclusive)
	 * @return the range of cdr detailses
	 */
	@Override
	public List<CdrDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cdr detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr detailses
	 * @param end the upper bound of the range of cdr detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cdr detailses
	 */
	@Override
	public List<CdrDetails> findAll(int start, int end,
		OrderByComparator<CdrDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cdr detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cdr detailses
	 * @param end the upper bound of the range of cdr detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cdr detailses
	 */
	@Override
	public List<CdrDetails> findAll(int start, int end,
		OrderByComparator<CdrDetails> orderByComparator,
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

		List<CdrDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CdrDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CDRDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CDRDETAILS;

				if (pagination) {
					sql = sql.concat(CdrDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CdrDetails>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CdrDetails>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cdr detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CdrDetails cdrDetails : findAll()) {
			remove(cdrDetails);
		}
	}

	/**
	 * Returns the number of cdr detailses.
	 *
	 * @return the number of cdr detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CDRDETAILS);

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
		return CdrDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cdr details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CdrDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CDRDETAILS = "SELECT cdrDetails FROM CdrDetails cdrDetails";
	private static final String _SQL_SELECT_CDRDETAILS_WHERE_PKS_IN = "SELECT cdrDetails FROM CdrDetails cdrDetails WHERE CDR_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CDRDETAILS = "SELECT COUNT(cdrDetails) FROM CdrDetails cdrDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cdrDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CdrDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CdrDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "modifiedBy", "createdDate", "lineType", "keyword",
				"itemGroupMsAssociation", "value", "modifiedDate",
				"logicalOperator", "operator", "cdrDetailsSid", "cdrModelSid",
				"comparison"
			});
}