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

import com.stpl.app.exception.NoSuchStNmAssumptionsException;
import com.stpl.app.model.StNmAssumptions;
import com.stpl.app.model.impl.StNmAssumptionsImpl;
import com.stpl.app.model.impl.StNmAssumptionsModelImpl;
import com.stpl.app.service.persistence.StNmAssumptionsPK;
import com.stpl.app.service.persistence.StNmAssumptionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st nm assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmAssumptionsPersistence
 * @see com.stpl.app.service.persistence.StNmAssumptionsUtil
 * @generated
 */
@ProviderType
public class StNmAssumptionsPersistenceImpl extends BasePersistenceImpl<StNmAssumptions>
	implements StNmAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StNmAssumptionsUtil} to access the st nm assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StNmAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StNmAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StNmAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StNmAssumptionsPersistenceImpl() {
		setModelClass(StNmAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("parent", "PARENT");
			dbColumnNames.put("projectionPeriod", "PROJECTION_PERIOD");
			dbColumnNames.put("commentary", "COMMENTARY");
			dbColumnNames.put("nmAssumptionsSid", "NM_ASSUMPTIONS_SID");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("netSalesPrior", "NET_SALES_PRIOR");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("grossSalesPercentChange",
				"GROSS_SALES_PERCENT_CHANGE");
			dbColumnNames.put("totalDiscountPercentChange",
				"TOTAL_DISCOUNT_PERCENT_CHANGE");
			dbColumnNames.put("reasonCodes", "REASON_CODES");
			dbColumnNames.put("totalDiscountPercentProjected",
				"TOTAL_DISCOUNT_PERCENT_PROJECTED");
			dbColumnNames.put("totalDiscountPercentPrior",
				"TOTAL_DISCOUNT_PERCENT_PRIOR");
			dbColumnNames.put("netSalesProjected", "NET_SALES_PROJECTED");
			dbColumnNames.put("stNmAssumptionsSid", "ST_NM_ASSUMPTIONS_SID");
			dbColumnNames.put("grossSalesProjected", "GROSS_SALES_PROJECTED");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("grossSalesPrior", "GROSS_SALES_PRIOR");
			dbColumnNames.put("isChecked", "IS_CHECKED");
			dbColumnNames.put("camId", "CAM_ID");
			dbColumnNames.put("netSalesPercentChange",
				"NET_SALES_PERCENT_CHANGE");
			dbColumnNames.put("segment", "SEGMENT");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st nm assumptions in the entity cache if it is enabled.
	 *
	 * @param stNmAssumptions the st nm assumptions
	 */
	@Override
	public void cacheResult(StNmAssumptions stNmAssumptions) {
		entityCache.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey(),
			stNmAssumptions);

		stNmAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the st nm assumptionses in the entity cache if it is enabled.
	 *
	 * @param stNmAssumptionses the st nm assumptionses
	 */
	@Override
	public void cacheResult(List<StNmAssumptions> stNmAssumptionses) {
		for (StNmAssumptions stNmAssumptions : stNmAssumptionses) {
			if (entityCache.getResult(
						StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StNmAssumptionsImpl.class,
						stNmAssumptions.getPrimaryKey()) == null) {
				cacheResult(stNmAssumptions);
			}
			else {
				stNmAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st nm assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StNmAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st nm assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StNmAssumptions stNmAssumptions) {
		entityCache.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StNmAssumptions> stNmAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StNmAssumptions stNmAssumptions : stNmAssumptionses) {
			entityCache.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
	 *
	 * @param stNmAssumptionsPK the primary key for the new st nm assumptions
	 * @return the new st nm assumptions
	 */
	@Override
	public StNmAssumptions create(StNmAssumptionsPK stNmAssumptionsPK) {
		StNmAssumptions stNmAssumptions = new StNmAssumptionsImpl();

		stNmAssumptions.setNew(true);
		stNmAssumptions.setPrimaryKey(stNmAssumptionsPK);

		return stNmAssumptions;
	}

	/**
	 * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stNmAssumptionsPK the primary key of the st nm assumptions
	 * @return the st nm assumptions that was removed
	 * @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions remove(StNmAssumptionsPK stNmAssumptionsPK)
		throws NoSuchStNmAssumptionsException {
		return remove((Serializable)stNmAssumptionsPK);
	}

	/**
	 * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st nm assumptions
	 * @return the st nm assumptions that was removed
	 * @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions remove(Serializable primaryKey)
		throws NoSuchStNmAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			StNmAssumptions stNmAssumptions = (StNmAssumptions)session.get(StNmAssumptionsImpl.class,
					primaryKey);

			if (stNmAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStNmAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stNmAssumptions);
		}
		catch (NoSuchStNmAssumptionsException nsee) {
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
	protected StNmAssumptions removeImpl(StNmAssumptions stNmAssumptions) {
		stNmAssumptions = toUnwrappedModel(stNmAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stNmAssumptions)) {
				stNmAssumptions = (StNmAssumptions)session.get(StNmAssumptionsImpl.class,
						stNmAssumptions.getPrimaryKeyObj());
			}

			if (stNmAssumptions != null) {
				session.delete(stNmAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stNmAssumptions != null) {
			clearCache(stNmAssumptions);
		}

		return stNmAssumptions;
	}

	@Override
	public StNmAssumptions updateImpl(StNmAssumptions stNmAssumptions) {
		stNmAssumptions = toUnwrappedModel(stNmAssumptions);

		boolean isNew = stNmAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stNmAssumptions.isNew()) {
				session.save(stNmAssumptions);

				stNmAssumptions.setNew(false);
			}
			else {
				stNmAssumptions = (StNmAssumptions)session.merge(stNmAssumptions);
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

		entityCache.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StNmAssumptionsImpl.class, stNmAssumptions.getPrimaryKey(),
			stNmAssumptions, false);

		stNmAssumptions.resetOriginalValues();

		return stNmAssumptions;
	}

	protected StNmAssumptions toUnwrappedModel(StNmAssumptions stNmAssumptions) {
		if (stNmAssumptions instanceof StNmAssumptionsImpl) {
			return stNmAssumptions;
		}

		StNmAssumptionsImpl stNmAssumptionsImpl = new StNmAssumptionsImpl();

		stNmAssumptionsImpl.setNew(stNmAssumptions.isNew());
		stNmAssumptionsImpl.setPrimaryKey(stNmAssumptions.getPrimaryKey());

		stNmAssumptionsImpl.setLastModifiedDate(stNmAssumptions.getLastModifiedDate());
		stNmAssumptionsImpl.setParent(stNmAssumptions.isParent());
		stNmAssumptionsImpl.setProjectionPeriod(stNmAssumptions.getProjectionPeriod());
		stNmAssumptionsImpl.setCommentary(stNmAssumptions.getCommentary());
		stNmAssumptionsImpl.setNmAssumptionsSid(stNmAssumptions.getNmAssumptionsSid());
		stNmAssumptionsImpl.setProjectionDetailsSid(stNmAssumptions.getProjectionDetailsSid());
		stNmAssumptionsImpl.setNetSalesPrior(stNmAssumptions.getNetSalesPrior());
		stNmAssumptionsImpl.setUserId(stNmAssumptions.getUserId());
		stNmAssumptionsImpl.setGrossSalesPercentChange(stNmAssumptions.getGrossSalesPercentChange());
		stNmAssumptionsImpl.setTotalDiscountPercentChange(stNmAssumptions.getTotalDiscountPercentChange());
		stNmAssumptionsImpl.setReasonCodes(stNmAssumptions.getReasonCodes());
		stNmAssumptionsImpl.setTotalDiscountPercentProjected(stNmAssumptions.getTotalDiscountPercentProjected());
		stNmAssumptionsImpl.setTotalDiscountPercentPrior(stNmAssumptions.getTotalDiscountPercentPrior());
		stNmAssumptionsImpl.setNetSalesProjected(stNmAssumptions.getNetSalesProjected());
		stNmAssumptionsImpl.setStNmAssumptionsSid(stNmAssumptions.getStNmAssumptionsSid());
		stNmAssumptionsImpl.setGrossSalesProjected(stNmAssumptions.getGrossSalesProjected());
		stNmAssumptionsImpl.setSessionId(stNmAssumptions.getSessionId());
		stNmAssumptionsImpl.setGrossSalesPrior(stNmAssumptions.getGrossSalesPrior());
		stNmAssumptionsImpl.setIsChecked(stNmAssumptions.isIsChecked());
		stNmAssumptionsImpl.setCamId(stNmAssumptions.getCamId());
		stNmAssumptionsImpl.setNetSalesPercentChange(stNmAssumptions.getNetSalesPercentChange());
		stNmAssumptionsImpl.setSegment(stNmAssumptions.getSegment());

		return stNmAssumptionsImpl;
	}

	/**
	 * Returns the st nm assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm assumptions
	 * @return the st nm assumptions
	 * @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStNmAssumptionsException {
		StNmAssumptions stNmAssumptions = fetchByPrimaryKey(primaryKey);

		if (stNmAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStNmAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stNmAssumptions;
	}

	/**
	 * Returns the st nm assumptions with the primary key or throws a {@link NoSuchStNmAssumptionsException} if it could not be found.
	 *
	 * @param stNmAssumptionsPK the primary key of the st nm assumptions
	 * @return the st nm assumptions
	 * @throws NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions findByPrimaryKey(StNmAssumptionsPK stNmAssumptionsPK)
		throws NoSuchStNmAssumptionsException {
		return findByPrimaryKey((Serializable)stNmAssumptionsPK);
	}

	/**
	 * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st nm assumptions
	 * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StNmAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StNmAssumptions stNmAssumptions = (StNmAssumptions)serializable;

		if (stNmAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				stNmAssumptions = (StNmAssumptions)session.get(StNmAssumptionsImpl.class,
						primaryKey);

				if (stNmAssumptions != null) {
					cacheResult(stNmAssumptions);
				}
				else {
					entityCache.putResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StNmAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StNmAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					StNmAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stNmAssumptions;
	}

	/**
	 * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stNmAssumptionsPK the primary key of the st nm assumptions
	 * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
	 */
	@Override
	public StNmAssumptions fetchByPrimaryKey(
		StNmAssumptionsPK stNmAssumptionsPK) {
		return fetchByPrimaryKey((Serializable)stNmAssumptionsPK);
	}

	@Override
	public Map<Serializable, StNmAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StNmAssumptions> map = new HashMap<Serializable, StNmAssumptions>();

		for (Serializable primaryKey : primaryKeys) {
			StNmAssumptions stNmAssumptions = fetchByPrimaryKey(primaryKey);

			if (stNmAssumptions != null) {
				map.put(primaryKey, stNmAssumptions);
			}
		}

		return map;
	}

	/**
	 * Returns all the st nm assumptionses.
	 *
	 * @return the st nm assumptionses
	 */
	@Override
	public List<StNmAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st nm assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm assumptionses
	 * @param end the upper bound of the range of st nm assumptionses (not inclusive)
	 * @return the range of st nm assumptionses
	 */
	@Override
	public List<StNmAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st nm assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm assumptionses
	 * @param end the upper bound of the range of st nm assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st nm assumptionses
	 */
	@Override
	public List<StNmAssumptions> findAll(int start, int end,
		OrderByComparator<StNmAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st nm assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st nm assumptionses
	 * @param end the upper bound of the range of st nm assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st nm assumptionses
	 */
	@Override
	public List<StNmAssumptions> findAll(int start, int end,
		OrderByComparator<StNmAssumptions> orderByComparator,
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

		List<StNmAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<StNmAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STNMASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STNMASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(StNmAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StNmAssumptions>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StNmAssumptions>)QueryUtil.list(q,
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
	 * Removes all the st nm assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StNmAssumptions stNmAssumptions : findAll()) {
			remove(stNmAssumptions);
		}
	}

	/**
	 * Returns the number of st nm assumptionses.
	 *
	 * @return the number of st nm assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STNMASSUMPTIONS);

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
		return StNmAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st nm assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StNmAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STNMASSUMPTIONS = "SELECT stNmAssumptions FROM StNmAssumptions stNmAssumptions";
	private static final String _SQL_COUNT_STNMASSUMPTIONS = "SELECT COUNT(stNmAssumptions) FROM StNmAssumptions stNmAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stNmAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StNmAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StNmAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "parent", "projectionPeriod", "commentary",
				"nmAssumptionsSid", "projectionDetailsSid", "netSalesPrior",
				"userId", "grossSalesPercentChange",
				"totalDiscountPercentChange", "reasonCodes",
				"totalDiscountPercentProjected", "totalDiscountPercentPrior",
				"netSalesProjected", "stNmAssumptionsSid", "grossSalesProjected",
				"sessionId", "grossSalesPrior", "isChecked", "camId",
				"netSalesPercentChange", "segment"
			});
}