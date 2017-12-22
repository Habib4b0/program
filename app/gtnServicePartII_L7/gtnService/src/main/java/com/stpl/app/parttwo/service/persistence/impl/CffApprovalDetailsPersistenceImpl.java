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

import com.stpl.app.parttwo.exception.NoSuchCffApprovalDetailsException;
import com.stpl.app.parttwo.model.CffApprovalDetails;
import com.stpl.app.parttwo.model.impl.CffApprovalDetailsImpl;
import com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.CffApprovalDetailsPersistence;

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
 * The persistence implementation for the cff approval details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffApprovalDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.CffApprovalDetailsUtil
 * @generated
 */
@ProviderType
public class CffApprovalDetailsPersistenceImpl extends BasePersistenceImpl<CffApprovalDetails>
	implements CffApprovalDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CffApprovalDetailsUtil} to access the cff approval details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CffApprovalDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffApprovalDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED,
			CffApprovalDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CffApprovalDetailsPersistenceImpl() {
		setModelClass(CffApprovalDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("approvalSequence", "APPROVAL_SEQUENCE");
			dbColumnNames.put("approvedDate", "APPROVED_DATE");
			dbColumnNames.put("approvedBy", "APPROVED_BY");
			dbColumnNames.put("approvalStatus", "APPROVAL_STATUS");
			dbColumnNames.put("cffMasterSid", "CFF_MASTER_SID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("cffApprovalDetailsSid",
				"CFF_APPROVAL_DETAILS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cff approval details in the entity cache if it is enabled.
	 *
	 * @param cffApprovalDetails the cff approval details
	 */
	@Override
	public void cacheResult(CffApprovalDetails cffApprovalDetails) {
		entityCache.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey(),
			cffApprovalDetails);

		cffApprovalDetails.resetOriginalValues();
	}

	/**
	 * Caches the cff approval detailses in the entity cache if it is enabled.
	 *
	 * @param cffApprovalDetailses the cff approval detailses
	 */
	@Override
	public void cacheResult(List<CffApprovalDetails> cffApprovalDetailses) {
		for (CffApprovalDetails cffApprovalDetails : cffApprovalDetailses) {
			if (entityCache.getResult(
						CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffApprovalDetailsImpl.class,
						cffApprovalDetails.getPrimaryKey()) == null) {
				cacheResult(cffApprovalDetails);
			}
			else {
				cffApprovalDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cff approval detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CffApprovalDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cff approval details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CffApprovalDetails cffApprovalDetails) {
		entityCache.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CffApprovalDetails> cffApprovalDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CffApprovalDetails cffApprovalDetails : cffApprovalDetailses) {
			entityCache.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
	 *
	 * @param cffApprovalDetailsSid the primary key for the new cff approval details
	 * @return the new cff approval details
	 */
	@Override
	public CffApprovalDetails create(int cffApprovalDetailsSid) {
		CffApprovalDetails cffApprovalDetails = new CffApprovalDetailsImpl();

		cffApprovalDetails.setNew(true);
		cffApprovalDetails.setPrimaryKey(cffApprovalDetailsSid);

		return cffApprovalDetails;
	}

	/**
	 * Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cffApprovalDetailsSid the primary key of the cff approval details
	 * @return the cff approval details that was removed
	 * @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails remove(int cffApprovalDetailsSid)
		throws NoSuchCffApprovalDetailsException {
		return remove((Serializable)cffApprovalDetailsSid);
	}

	/**
	 * Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cff approval details
	 * @return the cff approval details that was removed
	 * @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails remove(Serializable primaryKey)
		throws NoSuchCffApprovalDetailsException {
		Session session = null;

		try {
			session = openSession();

			CffApprovalDetails cffApprovalDetails = (CffApprovalDetails)session.get(CffApprovalDetailsImpl.class,
					primaryKey);

			if (cffApprovalDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCffApprovalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cffApprovalDetails);
		}
		catch (NoSuchCffApprovalDetailsException nsee) {
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
	protected CffApprovalDetails removeImpl(
		CffApprovalDetails cffApprovalDetails) {
		cffApprovalDetails = toUnwrappedModel(cffApprovalDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cffApprovalDetails)) {
				cffApprovalDetails = (CffApprovalDetails)session.get(CffApprovalDetailsImpl.class,
						cffApprovalDetails.getPrimaryKeyObj());
			}

			if (cffApprovalDetails != null) {
				session.delete(cffApprovalDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cffApprovalDetails != null) {
			clearCache(cffApprovalDetails);
		}

		return cffApprovalDetails;
	}

	@Override
	public CffApprovalDetails updateImpl(CffApprovalDetails cffApprovalDetails) {
		cffApprovalDetails = toUnwrappedModel(cffApprovalDetails);

		boolean isNew = cffApprovalDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cffApprovalDetails.isNew()) {
				session.save(cffApprovalDetails);

				cffApprovalDetails.setNew(false);
			}
			else {
				cffApprovalDetails = (CffApprovalDetails)session.merge(cffApprovalDetails);
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

		entityCache.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
			CffApprovalDetailsImpl.class, cffApprovalDetails.getPrimaryKey(),
			cffApprovalDetails, false);

		cffApprovalDetails.resetOriginalValues();

		return cffApprovalDetails;
	}

	protected CffApprovalDetails toUnwrappedModel(
		CffApprovalDetails cffApprovalDetails) {
		if (cffApprovalDetails instanceof CffApprovalDetailsImpl) {
			return cffApprovalDetails;
		}

		CffApprovalDetailsImpl cffApprovalDetailsImpl = new CffApprovalDetailsImpl();

		cffApprovalDetailsImpl.setNew(cffApprovalDetails.isNew());
		cffApprovalDetailsImpl.setPrimaryKey(cffApprovalDetails.getPrimaryKey());

		cffApprovalDetailsImpl.setApprovalSequence(cffApprovalDetails.getApprovalSequence());
		cffApprovalDetailsImpl.setApprovedDate(cffApprovalDetails.getApprovedDate());
		cffApprovalDetailsImpl.setApprovedBy(cffApprovalDetails.getApprovedBy());
		cffApprovalDetailsImpl.setApprovalStatus(cffApprovalDetails.getApprovalStatus());
		cffApprovalDetailsImpl.setCffMasterSid(cffApprovalDetails.getCffMasterSid());
		cffApprovalDetailsImpl.setInboundStatus(cffApprovalDetails.getInboundStatus());
		cffApprovalDetailsImpl.setCffApprovalDetailsSid(cffApprovalDetails.getCffApprovalDetailsSid());

		return cffApprovalDetailsImpl;
	}

	/**
	 * Returns the cff approval details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff approval details
	 * @return the cff approval details
	 * @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCffApprovalDetailsException {
		CffApprovalDetails cffApprovalDetails = fetchByPrimaryKey(primaryKey);

		if (cffApprovalDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCffApprovalDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cffApprovalDetails;
	}

	/**
	 * Returns the cff approval details with the primary key or throws a {@link NoSuchCffApprovalDetailsException} if it could not be found.
	 *
	 * @param cffApprovalDetailsSid the primary key of the cff approval details
	 * @return the cff approval details
	 * @throws NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails findByPrimaryKey(int cffApprovalDetailsSid)
		throws NoSuchCffApprovalDetailsException {
		return findByPrimaryKey((Serializable)cffApprovalDetailsSid);
	}

	/**
	 * Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cff approval details
	 * @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
				CffApprovalDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CffApprovalDetails cffApprovalDetails = (CffApprovalDetails)serializable;

		if (cffApprovalDetails == null) {
			Session session = null;

			try {
				session = openSession();

				cffApprovalDetails = (CffApprovalDetails)session.get(CffApprovalDetailsImpl.class,
						primaryKey);

				if (cffApprovalDetails != null) {
					cacheResult(cffApprovalDetails);
				}
				else {
					entityCache.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
						CffApprovalDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffApprovalDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cffApprovalDetails;
	}

	/**
	 * Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cffApprovalDetailsSid the primary key of the cff approval details
	 * @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
	 */
	@Override
	public CffApprovalDetails fetchByPrimaryKey(int cffApprovalDetailsSid) {
		return fetchByPrimaryKey((Serializable)cffApprovalDetailsSid);
	}

	@Override
	public Map<Serializable, CffApprovalDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CffApprovalDetails> map = new HashMap<Serializable, CffApprovalDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CffApprovalDetails cffApprovalDetails = fetchByPrimaryKey(primaryKey);

			if (cffApprovalDetails != null) {
				map.put(primaryKey, cffApprovalDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffApprovalDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CffApprovalDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFFAPPROVALDETAILS_WHERE_PKS_IN);

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

			for (CffApprovalDetails cffApprovalDetails : (List<CffApprovalDetails>)q.list()) {
				map.put(cffApprovalDetails.getPrimaryKeyObj(),
					cffApprovalDetails);

				cacheResult(cffApprovalDetails);

				uncachedPrimaryKeys.remove(cffApprovalDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CffApprovalDetailsModelImpl.ENTITY_CACHE_ENABLED,
					CffApprovalDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the cff approval detailses.
	 *
	 * @return the cff approval detailses
	 */
	@Override
	public List<CffApprovalDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cff approval detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff approval detailses
	 * @param end the upper bound of the range of cff approval detailses (not inclusive)
	 * @return the range of cff approval detailses
	 */
	@Override
	public List<CffApprovalDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cff approval detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff approval detailses
	 * @param end the upper bound of the range of cff approval detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cff approval detailses
	 */
	@Override
	public List<CffApprovalDetails> findAll(int start, int end,
		OrderByComparator<CffApprovalDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cff approval detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cff approval detailses
	 * @param end the upper bound of the range of cff approval detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cff approval detailses
	 */
	@Override
	public List<CffApprovalDetails> findAll(int start, int end,
		OrderByComparator<CffApprovalDetails> orderByComparator,
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

		List<CffApprovalDetails> list = null;

		if (retrieveFromCache) {
			list = (List<CffApprovalDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFFAPPROVALDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFFAPPROVALDETAILS;

				if (pagination) {
					sql = sql.concat(CffApprovalDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CffApprovalDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CffApprovalDetails>)QueryUtil.list(q,
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
	 * Removes all the cff approval detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CffApprovalDetails cffApprovalDetails : findAll()) {
			remove(cffApprovalDetails);
		}
	}

	/**
	 * Returns the number of cff approval detailses.
	 *
	 * @return the number of cff approval detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFFAPPROVALDETAILS);

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
		return CffApprovalDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cff approval details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CffApprovalDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFFAPPROVALDETAILS = "SELECT cffApprovalDetails FROM CffApprovalDetails cffApprovalDetails";
	private static final String _SQL_SELECT_CFFAPPROVALDETAILS_WHERE_PKS_IN = "SELECT cffApprovalDetails FROM CffApprovalDetails cffApprovalDetails WHERE CFF_APPROVAL_DETAILS_SID IN (";
	private static final String _SQL_COUNT_CFFAPPROVALDETAILS = "SELECT COUNT(cffApprovalDetails) FROM CffApprovalDetails cffApprovalDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cffApprovalDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CffApprovalDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CffApprovalDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"approvalSequence", "approvedDate", "approvedBy",
				"approvalStatus", "cffMasterSid", "inboundStatus",
				"cffApprovalDetailsSid"
			});
}