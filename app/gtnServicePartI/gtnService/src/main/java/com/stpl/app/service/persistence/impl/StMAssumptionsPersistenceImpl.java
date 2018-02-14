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

import com.stpl.app.exception.NoSuchStMAssumptionsException;
import com.stpl.app.model.StMAssumptions;
import com.stpl.app.model.impl.StMAssumptionsImpl;
import com.stpl.app.model.impl.StMAssumptionsModelImpl;
import com.stpl.app.service.persistence.StMAssumptionsPK;
import com.stpl.app.service.persistence.StMAssumptionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMAssumptionsPersistence
 * @see com.stpl.app.service.persistence.StMAssumptionsUtil
 * @generated
 */
@ProviderType
public class StMAssumptionsPersistenceImpl extends BasePersistenceImpl<StMAssumptions>
	implements StMAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StMAssumptionsUtil} to access the st m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StMAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StMAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StMAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StMAssumptionsPersistenceImpl() {
		setModelClass(StMAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("grossSalesPercentChange",
				"GROSS_SALES_PERCENT_CHANGE");
			dbColumnNames.put("grossSalesPrior", "GROSS_SALES_PRIOR");
			dbColumnNames.put("projYear", "PROJ_YEAR");
			dbColumnNames.put("totalDiscountPercentProjected",
				"TOTAL_DISCOUNT_PERCENT_PROJECTED");
			dbColumnNames.put("camId", "CAM_ID");
			dbColumnNames.put("commentary", "COMMENTARY");
			dbColumnNames.put("isChecked", "IS_CHECKED");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("grossSalesProjected", "GROSS_SALES_PROJECTED");
			dbColumnNames.put("totalDiscountPercentChange",
				"TOTAL_DISCOUNT_PERCENT_CHANGE");
			dbColumnNames.put("totalDiscountPercentPrior",
				"TOTAL_DISCOUNT_PERCENT_PRIOR");
			dbColumnNames.put("netSalesPercentChange",
				"NET_SALES_PERCENT_CHANGE");
			dbColumnNames.put("parent", "PARENT");
			dbColumnNames.put("stMAssumptionsSid", "ST_M_ASSUMPTIONS_SID");
			dbColumnNames.put("projectionPeriod", "PROJECTION_PERIOD");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("netSalesPrior", "NET_SALES_PRIOR");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("netSalesProjected", "NET_SALES_PROJECTED");
			dbColumnNames.put("reasonCodes", "REASON_CODES");
			dbColumnNames.put("mAssumptionsSid", "M_ASSUMPTIONS_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st m assumptions in the entity cache if it is enabled.
	 *
	 * @param stMAssumptions the st m assumptions
	 */
	@Override
	public void cacheResult(StMAssumptions stMAssumptions) {
		entityCache.putResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsImpl.class, stMAssumptions.getPrimaryKey(),
			stMAssumptions);

		stMAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the st m assumptionses in the entity cache if it is enabled.
	 *
	 * @param stMAssumptionses the st m assumptionses
	 */
	@Override
	public void cacheResult(List<StMAssumptions> stMAssumptionses) {
		for (StMAssumptions stMAssumptions : stMAssumptionses) {
			if (entityCache.getResult(
						StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StMAssumptionsImpl.class, stMAssumptions.getPrimaryKey()) == null) {
				cacheResult(stMAssumptions);
			}
			else {
				stMAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st m assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StMAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st m assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StMAssumptions stMAssumptions) {
		entityCache.removeResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsImpl.class, stMAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StMAssumptions> stMAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StMAssumptions stMAssumptions : stMAssumptionses) {
			entityCache.removeResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StMAssumptionsImpl.class, stMAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st m assumptions with the primary key. Does not add the st m assumptions to the database.
	 *
	 * @param stMAssumptionsPK the primary key for the new st m assumptions
	 * @return the new st m assumptions
	 */
	@Override
	public StMAssumptions create(StMAssumptionsPK stMAssumptionsPK) {
		StMAssumptions stMAssumptions = new StMAssumptionsImpl();

		stMAssumptions.setNew(true);
		stMAssumptions.setPrimaryKey(stMAssumptionsPK);

		return stMAssumptions;
	}

	/**
	 * Removes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stMAssumptionsPK the primary key of the st m assumptions
	 * @return the st m assumptions that was removed
	 * @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions remove(StMAssumptionsPK stMAssumptionsPK)
		throws NoSuchStMAssumptionsException {
		return remove((Serializable)stMAssumptionsPK);
	}

	/**
	 * Removes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st m assumptions
	 * @return the st m assumptions that was removed
	 * @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions remove(Serializable primaryKey)
		throws NoSuchStMAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			StMAssumptions stMAssumptions = (StMAssumptions)session.get(StMAssumptionsImpl.class,
					primaryKey);

			if (stMAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stMAssumptions);
		}
		catch (NoSuchStMAssumptionsException nsee) {
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
	protected StMAssumptions removeImpl(StMAssumptions stMAssumptions) {
		stMAssumptions = toUnwrappedModel(stMAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stMAssumptions)) {
				stMAssumptions = (StMAssumptions)session.get(StMAssumptionsImpl.class,
						stMAssumptions.getPrimaryKeyObj());
			}

			if (stMAssumptions != null) {
				session.delete(stMAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stMAssumptions != null) {
			clearCache(stMAssumptions);
		}

		return stMAssumptions;
	}

	@Override
	public StMAssumptions updateImpl(StMAssumptions stMAssumptions) {
		stMAssumptions = toUnwrappedModel(stMAssumptions);

		boolean isNew = stMAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stMAssumptions.isNew()) {
				session.save(stMAssumptions);

				stMAssumptions.setNew(false);
			}
			else {
				stMAssumptions = (StMAssumptions)session.merge(stMAssumptions);
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

		entityCache.putResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StMAssumptionsImpl.class, stMAssumptions.getPrimaryKey(),
			stMAssumptions, false);

		stMAssumptions.resetOriginalValues();

		return stMAssumptions;
	}

	protected StMAssumptions toUnwrappedModel(StMAssumptions stMAssumptions) {
		if (stMAssumptions instanceof StMAssumptionsImpl) {
			return stMAssumptions;
		}

		StMAssumptionsImpl stMAssumptionsImpl = new StMAssumptionsImpl();

		stMAssumptionsImpl.setNew(stMAssumptions.isNew());
		stMAssumptionsImpl.setPrimaryKey(stMAssumptions.getPrimaryKey());

		stMAssumptionsImpl.setGrossSalesPercentChange(stMAssumptions.getGrossSalesPercentChange());
		stMAssumptionsImpl.setGrossSalesPrior(stMAssumptions.getGrossSalesPrior());
		stMAssumptionsImpl.setProjYear(stMAssumptions.getProjYear());
		stMAssumptionsImpl.setTotalDiscountPercentProjected(stMAssumptions.getTotalDiscountPercentProjected());
		stMAssumptionsImpl.setCamId(stMAssumptions.getCamId());
		stMAssumptionsImpl.setCommentary(stMAssumptions.getCommentary());
		stMAssumptionsImpl.setIsChecked(stMAssumptions.isIsChecked());
		stMAssumptionsImpl.setUserId(stMAssumptions.getUserId());
		stMAssumptionsImpl.setLastModifiedDate(stMAssumptions.getLastModifiedDate());
		stMAssumptionsImpl.setGrossSalesProjected(stMAssumptions.getGrossSalesProjected());
		stMAssumptionsImpl.setTotalDiscountPercentChange(stMAssumptions.getTotalDiscountPercentChange());
		stMAssumptionsImpl.setTotalDiscountPercentPrior(stMAssumptions.getTotalDiscountPercentPrior());
		stMAssumptionsImpl.setNetSalesPercentChange(stMAssumptions.getNetSalesPercentChange());
		stMAssumptionsImpl.setParent(stMAssumptions.isParent());
		stMAssumptionsImpl.setStMAssumptionsSid(stMAssumptions.getStMAssumptionsSid());
		stMAssumptionsImpl.setProjectionPeriod(stMAssumptions.getProjectionPeriod());
		stMAssumptionsImpl.setProjectionDetailsSid(stMAssumptions.getProjectionDetailsSid());
		stMAssumptionsImpl.setNetSalesPrior(stMAssumptions.getNetSalesPrior());
		stMAssumptionsImpl.setSessionId(stMAssumptions.getSessionId());
		stMAssumptionsImpl.setNetSalesProjected(stMAssumptions.getNetSalesProjected());
		stMAssumptionsImpl.setReasonCodes(stMAssumptions.getReasonCodes());
		stMAssumptionsImpl.setMAssumptionsSid(stMAssumptions.getMAssumptionsSid());

		return stMAssumptionsImpl;
	}

	/**
	 * Returns the st m assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m assumptions
	 * @return the st m assumptions
	 * @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStMAssumptionsException {
		StMAssumptions stMAssumptions = fetchByPrimaryKey(primaryKey);

		if (stMAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stMAssumptions;
	}

	/**
	 * Returns the st m assumptions with the primary key or throws a {@link NoSuchStMAssumptionsException} if it could not be found.
	 *
	 * @param stMAssumptionsPK the primary key of the st m assumptions
	 * @return the st m assumptions
	 * @throws NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions findByPrimaryKey(StMAssumptionsPK stMAssumptionsPK)
		throws NoSuchStMAssumptionsException {
		return findByPrimaryKey((Serializable)stMAssumptionsPK);
	}

	/**
	 * Returns the st m assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st m assumptions
	 * @return the st m assumptions, or <code>null</code> if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StMAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StMAssumptions stMAssumptions = (StMAssumptions)serializable;

		if (stMAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				stMAssumptions = (StMAssumptions)session.get(StMAssumptionsImpl.class,
						primaryKey);

				if (stMAssumptions != null) {
					cacheResult(stMAssumptions);
				}
				else {
					entityCache.putResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StMAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StMAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					StMAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stMAssumptions;
	}

	/**
	 * Returns the st m assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stMAssumptionsPK the primary key of the st m assumptions
	 * @return the st m assumptions, or <code>null</code> if a st m assumptions with the primary key could not be found
	 */
	@Override
	public StMAssumptions fetchByPrimaryKey(StMAssumptionsPK stMAssumptionsPK) {
		return fetchByPrimaryKey((Serializable)stMAssumptionsPK);
	}

	@Override
	public Map<Serializable, StMAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StMAssumptions> map = new HashMap<Serializable, StMAssumptions>();

		for (Serializable primaryKey : primaryKeys) {
			StMAssumptions stMAssumptions = fetchByPrimaryKey(primaryKey);

			if (stMAssumptions != null) {
				map.put(primaryKey, stMAssumptions);
			}
		}

		return map;
	}

	/**
	 * Returns all the st m assumptionses.
	 *
	 * @return the st m assumptionses
	 */
	@Override
	public List<StMAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m assumptionses
	 * @param end the upper bound of the range of st m assumptionses (not inclusive)
	 * @return the range of st m assumptionses
	 */
	@Override
	public List<StMAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m assumptionses
	 * @param end the upper bound of the range of st m assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st m assumptionses
	 */
	@Override
	public List<StMAssumptions> findAll(int start, int end,
		OrderByComparator<StMAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st m assumptionses
	 * @param end the upper bound of the range of st m assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st m assumptionses
	 */
	@Override
	public List<StMAssumptions> findAll(int start, int end,
		OrderByComparator<StMAssumptions> orderByComparator,
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

		List<StMAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<StMAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STMASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STMASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(StMAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StMAssumptions>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StMAssumptions>)QueryUtil.list(q,
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
	 * Removes all the st m assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StMAssumptions stMAssumptions : findAll()) {
			remove(stMAssumptions);
		}
	}

	/**
	 * Returns the number of st m assumptionses.
	 *
	 * @return the number of st m assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STMASSUMPTIONS);

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
		return StMAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st m assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StMAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STMASSUMPTIONS = "SELECT stMAssumptions FROM StMAssumptions stMAssumptions";
	private static final String _SQL_COUNT_STMASSUMPTIONS = "SELECT COUNT(stMAssumptions) FROM StMAssumptions stMAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stMAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StMAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StMAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"grossSalesPercentChange", "grossSalesPrior", "projYear",
				"totalDiscountPercentProjected", "camId", "commentary",
				"isChecked", "userId", "lastModifiedDate", "grossSalesProjected",
				"totalDiscountPercentChange", "totalDiscountPercentPrior",
				"netSalesPercentChange", "parent", "stMAssumptionsSid",
				"projectionPeriod", "projectionDetailsSid", "netSalesPrior",
				"sessionId", "netSalesProjected", "reasonCodes",
				"mAssumptionsSid"
			});
}