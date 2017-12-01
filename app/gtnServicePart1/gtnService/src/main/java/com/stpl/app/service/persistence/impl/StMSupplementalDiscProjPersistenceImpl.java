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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchStMSupplementalDiscProjException;
import com.stpl.app.model.StMSupplementalDiscProj;
import com.stpl.app.model.impl.StMSupplementalDiscProjImpl;
import com.stpl.app.model.impl.StMSupplementalDiscProjModelImpl;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPK;
import com.stpl.app.service.persistence.StMSupplementalDiscProjPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscProjPersistence
 * @see com.stpl.app.service.persistence.StMSupplementalDiscProjUtil
 * @generated
 */
@ProviderType
public class StMSupplementalDiscProjPersistenceImpl extends BasePersistenceImpl<StMSupplementalDiscProj>
	implements StMSupplementalDiscProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StMSupplementalDiscProjUtil} to access the st m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StMSupplementalDiscProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
			StMSupplementalDiscProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StMSupplementalDiscProjPersistenceImpl() {
		setModelClass(StMSupplementalDiscProj.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("parityReference", "PARITY_REFERENCE");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("parity", "PARITY");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("discountRate1", "DISCOUNT_RATE_1");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("discountRate2", "DISCOUNT_RATE_2");
			dbColumnNames.put("parityDiscount", "PARITY_DISCOUNT");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("access", "ACCESS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st m supplemental disc proj in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscProj the st m supplemental disc proj
	 */
	@Override
	public void cacheResult(StMSupplementalDiscProj stMSupplementalDiscProj) {
		entityCache.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjImpl.class,
			stMSupplementalDiscProj.getPrimaryKey(), stMSupplementalDiscProj);

		stMSupplementalDiscProj.resetOriginalValues();
	}

	/**
	 * Caches the st m supplemental disc projs in the entity cache if it is enabled.
	 *
	 * @param stMSupplementalDiscProjs the st m supplemental disc projs
	 */
	@Override
	public void cacheResult(
		List<StMSupplementalDiscProj> stMSupplementalDiscProjs) {
		for (StMSupplementalDiscProj stMSupplementalDiscProj : stMSupplementalDiscProjs) {
			if (entityCache.getResult(
						StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscProjImpl.class,
						stMSupplementalDiscProj.getPrimaryKey()) == null) {
				cacheResult(stMSupplementalDiscProj);
			}
			else {
				stMSupplementalDiscProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st m supplemental disc projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StMSupplementalDiscProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st m supplemental disc proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StMSupplementalDiscProj stMSupplementalDiscProj) {
		entityCache.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjImpl.class,
			stMSupplementalDiscProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<StMSupplementalDiscProj> stMSupplementalDiscProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StMSupplementalDiscProj stMSupplementalDiscProj : stMSupplementalDiscProjs) {
			entityCache.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscProjImpl.class,
				stMSupplementalDiscProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st m supplemental disc proj with the primary key. Does not add the st m supplemental disc proj to the database.
	 *
	 * @param stMSupplementalDiscProjPK the primary key for the new st m supplemental disc proj
	 * @return the new st m supplemental disc proj
	 */
	@Override
	public StMSupplementalDiscProj create(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK) {
		StMSupplementalDiscProj stMSupplementalDiscProj = new StMSupplementalDiscProjImpl();

		stMSupplementalDiscProj.setNew(true);
		stMSupplementalDiscProj.setPrimaryKey(stMSupplementalDiscProjPK);

		return stMSupplementalDiscProj;
	}

	/**
	 * Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj that was removed
	 * @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj remove(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
		throws NoSuchStMSupplementalDiscProjException {
		return remove((Serializable)stMSupplementalDiscProjPK);
	}

	/**
	 * Removes the st m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj that was removed
	 * @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj remove(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscProjException {
		Session session = null;

		try {
			session = openSession();

			StMSupplementalDiscProj stMSupplementalDiscProj = (StMSupplementalDiscProj)session.get(StMSupplementalDiscProjImpl.class,
					primaryKey);

			if (stMSupplementalDiscProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stMSupplementalDiscProj);
		}
		catch (NoSuchStMSupplementalDiscProjException nsee) {
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
	protected StMSupplementalDiscProj removeImpl(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		stMSupplementalDiscProj = toUnwrappedModel(stMSupplementalDiscProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stMSupplementalDiscProj)) {
				stMSupplementalDiscProj = (StMSupplementalDiscProj)session.get(StMSupplementalDiscProjImpl.class,
						stMSupplementalDiscProj.getPrimaryKeyObj());
			}

			if (stMSupplementalDiscProj != null) {
				session.delete(stMSupplementalDiscProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stMSupplementalDiscProj != null) {
			clearCache(stMSupplementalDiscProj);
		}

		return stMSupplementalDiscProj;
	}

	@Override
	public StMSupplementalDiscProj updateImpl(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		stMSupplementalDiscProj = toUnwrappedModel(stMSupplementalDiscProj);

		boolean isNew = stMSupplementalDiscProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stMSupplementalDiscProj.isNew()) {
				session.save(stMSupplementalDiscProj);

				stMSupplementalDiscProj.setNew(false);
			}
			else {
				stMSupplementalDiscProj = (StMSupplementalDiscProj)session.merge(stMSupplementalDiscProj);
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

		entityCache.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			StMSupplementalDiscProjImpl.class,
			stMSupplementalDiscProj.getPrimaryKey(), stMSupplementalDiscProj,
			false);

		stMSupplementalDiscProj.resetOriginalValues();

		return stMSupplementalDiscProj;
	}

	protected StMSupplementalDiscProj toUnwrappedModel(
		StMSupplementalDiscProj stMSupplementalDiscProj) {
		if (stMSupplementalDiscProj instanceof StMSupplementalDiscProjImpl) {
			return stMSupplementalDiscProj;
		}

		StMSupplementalDiscProjImpl stMSupplementalDiscProjImpl = new StMSupplementalDiscProjImpl();

		stMSupplementalDiscProjImpl.setNew(stMSupplementalDiscProj.isNew());
		stMSupplementalDiscProjImpl.setPrimaryKey(stMSupplementalDiscProj.getPrimaryKey());

		stMSupplementalDiscProjImpl.setProjectionRate(stMSupplementalDiscProj.getProjectionRate());
		stMSupplementalDiscProjImpl.setUserId(stMSupplementalDiscProj.getUserId());
		stMSupplementalDiscProjImpl.setLastModifiedDate(stMSupplementalDiscProj.getLastModifiedDate());
		stMSupplementalDiscProjImpl.setParityReference(stMSupplementalDiscProj.getParityReference());
		stMSupplementalDiscProjImpl.setProjectionSales(stMSupplementalDiscProj.getProjectionSales());
		stMSupplementalDiscProjImpl.setContractPrice(stMSupplementalDiscProj.getContractPrice());
		stMSupplementalDiscProjImpl.setMethodology(stMSupplementalDiscProj.getMethodology());
		stMSupplementalDiscProjImpl.setParity(stMSupplementalDiscProj.isParity());
		stMSupplementalDiscProjImpl.setPeriodSid(stMSupplementalDiscProj.getPeriodSid());
		stMSupplementalDiscProjImpl.setDiscountRate1(stMSupplementalDiscProj.getDiscountRate1());
		stMSupplementalDiscProjImpl.setProjectionDetailsSid(stMSupplementalDiscProj.getProjectionDetailsSid());
		stMSupplementalDiscProjImpl.setDiscountRate2(stMSupplementalDiscProj.getDiscountRate2());
		stMSupplementalDiscProjImpl.setParityDiscount(stMSupplementalDiscProj.getParityDiscount());
		stMSupplementalDiscProjImpl.setSessionId(stMSupplementalDiscProj.getSessionId());
		stMSupplementalDiscProjImpl.setAccess(stMSupplementalDiscProj.getAccess());

		return stMSupplementalDiscProjImpl;
	}

	/**
	 * Returns the st m supplemental disc proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj
	 * @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStMSupplementalDiscProjException {
		StMSupplementalDiscProj stMSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

		if (stMSupplementalDiscProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stMSupplementalDiscProj;
	}

	/**
	 * Returns the st m supplemental disc proj with the primary key or throws a {@link NoSuchStMSupplementalDiscProjException} if it could not be found.
	 *
	 * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj
	 * @throws NoSuchStMSupplementalDiscProjException if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj findByPrimaryKey(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK)
		throws NoSuchStMSupplementalDiscProjException {
		return findByPrimaryKey((Serializable)stMSupplementalDiscProjPK);
	}

	/**
	 * Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
				StMSupplementalDiscProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StMSupplementalDiscProj stMSupplementalDiscProj = (StMSupplementalDiscProj)serializable;

		if (stMSupplementalDiscProj == null) {
			Session session = null;

			try {
				session = openSession();

				stMSupplementalDiscProj = (StMSupplementalDiscProj)session.get(StMSupplementalDiscProjImpl.class,
						primaryKey);

				if (stMSupplementalDiscProj != null) {
					cacheResult(stMSupplementalDiscProj);
				}
				else {
					entityCache.putResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
						StMSupplementalDiscProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StMSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
					StMSupplementalDiscProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stMSupplementalDiscProj;
	}

	/**
	 * Returns the st m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stMSupplementalDiscProjPK the primary key of the st m supplemental disc proj
	 * @return the st m supplemental disc proj, or <code>null</code> if a st m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public StMSupplementalDiscProj fetchByPrimaryKey(
		StMSupplementalDiscProjPK stMSupplementalDiscProjPK) {
		return fetchByPrimaryKey((Serializable)stMSupplementalDiscProjPK);
	}

	@Override
	public Map<Serializable, StMSupplementalDiscProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StMSupplementalDiscProj> map = new HashMap<Serializable, StMSupplementalDiscProj>();

		for (Serializable primaryKey : primaryKeys) {
			StMSupplementalDiscProj stMSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

			if (stMSupplementalDiscProj != null) {
				map.put(primaryKey, stMSupplementalDiscProj);
			}
		}

		return map;
	}

	/**
	 * Returns all the st m supplemental disc projs.
	 *
	 * @return the st m supplemental disc projs
	 */
	@Override
	public List<StMSupplementalDiscProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc projs
	 * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	 * @return the range of st m supplemental disc projs
	 */
	@Override
	public List<StMSupplementalDiscProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc projs
	 * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st m supplemental disc projs
	 */
	@Override
	public List<StMSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m supplemental disc projs
	 * @param end the upper bound of the range of st m supplemental disc projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st m supplemental disc projs
	 */
	@Override
	public List<StMSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<StMSupplementalDiscProj> orderByComparator,
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

		List<StMSupplementalDiscProj> list = null;

		if (retrieveFromCache) {
			list = (List<StMSupplementalDiscProj>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STMSUPPLEMENTALDISCPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STMSUPPLEMENTALDISCPROJ;

				if (pagination) {
					sql = sql.concat(StMSupplementalDiscProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StMSupplementalDiscProj>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StMSupplementalDiscProj>)QueryUtil.list(q,
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
	 * Removes all the st m supplemental disc projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StMSupplementalDiscProj stMSupplementalDiscProj : findAll()) {
			remove(stMSupplementalDiscProj);
		}
	}

	/**
	 * Returns the number of st m supplemental disc projs.
	 *
	 * @return the number of st m supplemental disc projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STMSUPPLEMENTALDISCPROJ);

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
		return StMSupplementalDiscProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st m supplemental disc proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StMSupplementalDiscProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STMSUPPLEMENTALDISCPROJ = "SELECT stMSupplementalDiscProj FROM StMSupplementalDiscProj stMSupplementalDiscProj";
	private static final String _SQL_COUNT_STMSUPPLEMENTALDISCPROJ = "SELECT COUNT(stMSupplementalDiscProj) FROM StMSupplementalDiscProj stMSupplementalDiscProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stMSupplementalDiscProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMSupplementalDiscProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StMSupplementalDiscProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"projectionRate", "userId", "lastModifiedDate",
				"parityReference", "projectionSales", "contractPrice",
				"methodology", "parity", "periodSid", "discountRate1",
				"projectionDetailsSid", "discountRate2", "parityDiscount",
				"sessionId", "access"
			});
}