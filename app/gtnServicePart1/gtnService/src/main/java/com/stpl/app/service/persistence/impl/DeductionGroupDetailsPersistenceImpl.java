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

import com.stpl.app.exception.NoSuchDeductionGroupDetailsException;
import com.stpl.app.model.DeductionGroupDetails;
import com.stpl.app.model.impl.DeductionGroupDetailsImpl;
import com.stpl.app.model.impl.DeductionGroupDetailsModelImpl;
import com.stpl.app.service.persistence.DeductionGroupDetailsPersistence;

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
 * The persistence implementation for the deduction group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.DeductionGroupDetailsUtil
 * @generated
 */
@ProviderType
public class DeductionGroupDetailsPersistenceImpl extends BasePersistenceImpl<DeductionGroupDetails>
	implements DeductionGroupDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DeductionGroupDetailsUtil} to access the deduction group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DeductionGroupDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			DeductionGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			DeductionGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public DeductionGroupDetailsPersistenceImpl() {
		setModelClass(DeductionGroupDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("deductionGroupDetailsSid",
				"DEDUCTION_GROUP_DETAILS_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionGroupSid", "DEDUCTION_GROUP_SID");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the deduction group details in the entity cache if it is enabled.
	 *
	 * @param deductionGroupDetails the deduction group details
	 */
	@Override
	public void cacheResult(DeductionGroupDetails deductionGroupDetails) {
		entityCache.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsImpl.class,
			deductionGroupDetails.getPrimaryKey(), deductionGroupDetails);

		deductionGroupDetails.resetOriginalValues();
	}

	/**
	 * Caches the deduction group detailses in the entity cache if it is enabled.
	 *
	 * @param deductionGroupDetailses the deduction group detailses
	 */
	@Override
	public void cacheResult(List<DeductionGroupDetails> deductionGroupDetailses) {
		for (DeductionGroupDetails deductionGroupDetails : deductionGroupDetailses) {
			if (entityCache.getResult(
						DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DeductionGroupDetailsImpl.class,
						deductionGroupDetails.getPrimaryKey()) == null) {
				cacheResult(deductionGroupDetails);
			}
			else {
				deductionGroupDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all deduction group detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DeductionGroupDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the deduction group details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DeductionGroupDetails deductionGroupDetails) {
		entityCache.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsImpl.class,
			deductionGroupDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<DeductionGroupDetails> deductionGroupDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DeductionGroupDetails deductionGroupDetails : deductionGroupDetailses) {
			entityCache.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DeductionGroupDetailsImpl.class,
				deductionGroupDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new deduction group details with the primary key. Does not add the deduction group details to the database.
	 *
	 * @param deductionGroupDetailsSid the primary key for the new deduction group details
	 * @return the new deduction group details
	 */
	@Override
	public DeductionGroupDetails create(int deductionGroupDetailsSid) {
		DeductionGroupDetails deductionGroupDetails = new DeductionGroupDetailsImpl();

		deductionGroupDetails.setNew(true);
		deductionGroupDetails.setPrimaryKey(deductionGroupDetailsSid);

		return deductionGroupDetails;
	}

	/**
	 * Removes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param deductionGroupDetailsSid the primary key of the deduction group details
	 * @return the deduction group details that was removed
	 * @throws NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails remove(int deductionGroupDetailsSid)
		throws NoSuchDeductionGroupDetailsException {
		return remove((Serializable)deductionGroupDetailsSid);
	}

	/**
	 * Removes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the deduction group details
	 * @return the deduction group details that was removed
	 * @throws NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails remove(Serializable primaryKey)
		throws NoSuchDeductionGroupDetailsException {
		Session session = null;

		try {
			session = openSession();

			DeductionGroupDetails deductionGroupDetails = (DeductionGroupDetails)session.get(DeductionGroupDetailsImpl.class,
					primaryKey);

			if (deductionGroupDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeductionGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(deductionGroupDetails);
		}
		catch (NoSuchDeductionGroupDetailsException nsee) {
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
	protected DeductionGroupDetails removeImpl(
		DeductionGroupDetails deductionGroupDetails) {
		deductionGroupDetails = toUnwrappedModel(deductionGroupDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(deductionGroupDetails)) {
				deductionGroupDetails = (DeductionGroupDetails)session.get(DeductionGroupDetailsImpl.class,
						deductionGroupDetails.getPrimaryKeyObj());
			}

			if (deductionGroupDetails != null) {
				session.delete(deductionGroupDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (deductionGroupDetails != null) {
			clearCache(deductionGroupDetails);
		}

		return deductionGroupDetails;
	}

	@Override
	public DeductionGroupDetails updateImpl(
		DeductionGroupDetails deductionGroupDetails) {
		deductionGroupDetails = toUnwrappedModel(deductionGroupDetails);

		boolean isNew = deductionGroupDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (deductionGroupDetails.isNew()) {
				session.save(deductionGroupDetails);

				deductionGroupDetails.setNew(false);
			}
			else {
				deductionGroupDetails = (DeductionGroupDetails)session.merge(deductionGroupDetails);
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

		entityCache.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			DeductionGroupDetailsImpl.class,
			deductionGroupDetails.getPrimaryKey(), deductionGroupDetails, false);

		deductionGroupDetails.resetOriginalValues();

		return deductionGroupDetails;
	}

	protected DeductionGroupDetails toUnwrappedModel(
		DeductionGroupDetails deductionGroupDetails) {
		if (deductionGroupDetails instanceof DeductionGroupDetailsImpl) {
			return deductionGroupDetails;
		}

		DeductionGroupDetailsImpl deductionGroupDetailsImpl = new DeductionGroupDetailsImpl();

		deductionGroupDetailsImpl.setNew(deductionGroupDetails.isNew());
		deductionGroupDetailsImpl.setPrimaryKey(deductionGroupDetails.getPrimaryKey());

		deductionGroupDetailsImpl.setDeductionGroupDetailsSid(deductionGroupDetails.getDeductionGroupDetailsSid());
		deductionGroupDetailsImpl.setCreatedDate(deductionGroupDetails.getCreatedDate());
		deductionGroupDetailsImpl.setCreatedBy(deductionGroupDetails.getCreatedBy());
		deductionGroupDetailsImpl.setDeductionGroupSid(deductionGroupDetails.getDeductionGroupSid());
		deductionGroupDetailsImpl.setVersionNo(deductionGroupDetails.getVersionNo());
		deductionGroupDetailsImpl.setModifiedBy(deductionGroupDetails.getModifiedBy());
		deductionGroupDetailsImpl.setRsModelSid(deductionGroupDetails.getRsModelSid());
		deductionGroupDetailsImpl.setModifiedDate(deductionGroupDetails.getModifiedDate());

		return deductionGroupDetailsImpl;
	}

	/**
	 * Returns the deduction group details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction group details
	 * @return the deduction group details
	 * @throws NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeductionGroupDetailsException {
		DeductionGroupDetails deductionGroupDetails = fetchByPrimaryKey(primaryKey);

		if (deductionGroupDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeductionGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return deductionGroupDetails;
	}

	/**
	 * Returns the deduction group details with the primary key or throws a {@link NoSuchDeductionGroupDetailsException} if it could not be found.
	 *
	 * @param deductionGroupDetailsSid the primary key of the deduction group details
	 * @return the deduction group details
	 * @throws NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails findByPrimaryKey(int deductionGroupDetailsSid)
		throws NoSuchDeductionGroupDetailsException {
		return findByPrimaryKey((Serializable)deductionGroupDetailsSid);
	}

	/**
	 * Returns the deduction group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the deduction group details
	 * @return the deduction group details, or <code>null</code> if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				DeductionGroupDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DeductionGroupDetails deductionGroupDetails = (DeductionGroupDetails)serializable;

		if (deductionGroupDetails == null) {
			Session session = null;

			try {
				session = openSession();

				deductionGroupDetails = (DeductionGroupDetails)session.get(DeductionGroupDetailsImpl.class,
						primaryKey);

				if (deductionGroupDetails != null) {
					cacheResult(deductionGroupDetails);
				}
				else {
					entityCache.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						DeductionGroupDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return deductionGroupDetails;
	}

	/**
	 * Returns the deduction group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param deductionGroupDetailsSid the primary key of the deduction group details
	 * @return the deduction group details, or <code>null</code> if a deduction group details with the primary key could not be found
	 */
	@Override
	public DeductionGroupDetails fetchByPrimaryKey(int deductionGroupDetailsSid) {
		return fetchByPrimaryKey((Serializable)deductionGroupDetailsSid);
	}

	@Override
	public Map<Serializable, DeductionGroupDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DeductionGroupDetails> map = new HashMap<Serializable, DeductionGroupDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DeductionGroupDetails deductionGroupDetails = fetchByPrimaryKey(primaryKey);

			if (deductionGroupDetails != null) {
				map.put(primaryKey, deductionGroupDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DeductionGroupDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_DEDUCTIONGROUPDETAILS_WHERE_PKS_IN);

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

			for (DeductionGroupDetails deductionGroupDetails : (List<DeductionGroupDetails>)q.list()) {
				map.put(deductionGroupDetails.getPrimaryKeyObj(),
					deductionGroupDetails);

				cacheResult(deductionGroupDetails);

				uncachedPrimaryKeys.remove(deductionGroupDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(DeductionGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					DeductionGroupDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the deduction group detailses.
	 *
	 * @return the deduction group detailses
	 */
	@Override
	public List<DeductionGroupDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the deduction group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction group detailses
	 * @param end the upper bound of the range of deduction group detailses (not inclusive)
	 * @return the range of deduction group detailses
	 */
	@Override
	public List<DeductionGroupDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the deduction group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction group detailses
	 * @param end the upper bound of the range of deduction group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of deduction group detailses
	 */
	@Override
	public List<DeductionGroupDetails> findAll(int start, int end,
		OrderByComparator<DeductionGroupDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the deduction group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of deduction group detailses
	 * @param end the upper bound of the range of deduction group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of deduction group detailses
	 */
	@Override
	public List<DeductionGroupDetails> findAll(int start, int end,
		OrderByComparator<DeductionGroupDetails> orderByComparator,
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

		List<DeductionGroupDetails> list = null;

		if (retrieveFromCache) {
			list = (List<DeductionGroupDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DEDUCTIONGROUPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DEDUCTIONGROUPDETAILS;

				if (pagination) {
					sql = sql.concat(DeductionGroupDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DeductionGroupDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<DeductionGroupDetails>)QueryUtil.list(q,
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
	 * Removes all the deduction group detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DeductionGroupDetails deductionGroupDetails : findAll()) {
			remove(deductionGroupDetails);
		}
	}

	/**
	 * Returns the number of deduction group detailses.
	 *
	 * @return the number of deduction group detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DEDUCTIONGROUPDETAILS);

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
		return DeductionGroupDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the deduction group details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(DeductionGroupDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_DEDUCTIONGROUPDETAILS = "SELECT deductionGroupDetails FROM DeductionGroupDetails deductionGroupDetails";
	private static final String _SQL_SELECT_DEDUCTIONGROUPDETAILS_WHERE_PKS_IN = "SELECT deductionGroupDetails FROM DeductionGroupDetails deductionGroupDetails WHERE DEDUCTION_GROUP_DETAILS_SID IN (";
	private static final String _SQL_COUNT_DEDUCTIONGROUPDETAILS = "SELECT COUNT(deductionGroupDetails) FROM DeductionGroupDetails deductionGroupDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "deductionGroupDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DeductionGroupDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(DeductionGroupDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"deductionGroupDetailsSid", "createdDate", "createdBy",
				"deductionGroupSid", "versionNo", "modifiedBy", "rsModelSid",
				"modifiedDate"
			});
}