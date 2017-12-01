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

import com.stpl.app.exception.NoSuchStChAssumptionsException;
import com.stpl.app.model.StChAssumptions;
import com.stpl.app.model.impl.StChAssumptionsImpl;
import com.stpl.app.model.impl.StChAssumptionsModelImpl;
import com.stpl.app.service.persistence.StChAssumptionsPK;
import com.stpl.app.service.persistence.StChAssumptionsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChAssumptionsPersistence
 * @see com.stpl.app.service.persistence.StChAssumptionsUtil
 * @generated
 */
@ProviderType
public class StChAssumptionsPersistenceImpl extends BasePersistenceImpl<StChAssumptions>
	implements StChAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StChAssumptionsUtil} to access the st ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StChAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			StChAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StChAssumptionsPersistenceImpl() {
		setModelClass(StChAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("lastModifiedDate", "LAST_MODIFIED_DATE");
			dbColumnNames.put("parent", "PARENT");
			dbColumnNames.put("commentary", "COMMENTARY");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("quarter", "QUARTER");
			dbColumnNames.put("totalDiscountPercentChange",
				"TOTAL_DISCOUNT_PERCENT_CHANGE");
			dbColumnNames.put("reasonCodes", "REASON_CODES");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("totalDiscountPercentProjected",
				"TOTAL_DISCOUNT_PERCENT_PROJECTED");
			dbColumnNames.put("totalDiscountPercentPrior",
				"TOTAL_DISCOUNT_PERCENT_PRIOR");
			dbColumnNames.put("stChAssumptionsSid", "ST_CH_ASSUMPTIONS_SID");
			dbColumnNames.put("chAssumptionsSid", "CH_ASSUMPTIONS_SID");
			dbColumnNames.put("totalDiscountChange", "TOTAL_DISCOUNT_CHANGE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("totalDiscountProjected",
				"TOTAL_DISCOUNT_PROJECTED");
			dbColumnNames.put("isChecked", "IS_CHECKED");
			dbColumnNames.put("camId", "CAM_ID");
			dbColumnNames.put("grossTradeSales", "GROSS_TRADE_SALES");
			dbColumnNames.put("totalDiscountPrior", "TOTAL_DISCOUNT_PRIOR");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st ch assumptions in the entity cache if it is enabled.
	 *
	 * @param stChAssumptions the st ch assumptions
	 */
	@Override
	public void cacheResult(StChAssumptions stChAssumptions) {
		entityCache.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey(),
			stChAssumptions);

		stChAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the st ch assumptionses in the entity cache if it is enabled.
	 *
	 * @param stChAssumptionses the st ch assumptionses
	 */
	@Override
	public void cacheResult(List<StChAssumptions> stChAssumptionses) {
		for (StChAssumptions stChAssumptions : stChAssumptionses) {
			if (entityCache.getResult(
						StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StChAssumptionsImpl.class,
						stChAssumptions.getPrimaryKey()) == null) {
				cacheResult(stChAssumptions);
			}
			else {
				stChAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st ch assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StChAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st ch assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StChAssumptions stChAssumptions) {
		entityCache.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StChAssumptions> stChAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StChAssumptions stChAssumptions : stChAssumptionses) {
			entityCache.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st ch assumptions with the primary key. Does not add the st ch assumptions to the database.
	 *
	 * @param stChAssumptionsPK the primary key for the new st ch assumptions
	 * @return the new st ch assumptions
	 */
	@Override
	public StChAssumptions create(StChAssumptionsPK stChAssumptionsPK) {
		StChAssumptions stChAssumptions = new StChAssumptionsImpl();

		stChAssumptions.setNew(true);
		stChAssumptions.setPrimaryKey(stChAssumptionsPK);

		return stChAssumptions;
	}

	/**
	 * Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stChAssumptionsPK the primary key of the st ch assumptions
	 * @return the st ch assumptions that was removed
	 * @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions remove(StChAssumptionsPK stChAssumptionsPK)
		throws NoSuchStChAssumptionsException {
		return remove((Serializable)stChAssumptionsPK);
	}

	/**
	 * Removes the st ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st ch assumptions
	 * @return the st ch assumptions that was removed
	 * @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions remove(Serializable primaryKey)
		throws NoSuchStChAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			StChAssumptions stChAssumptions = (StChAssumptions)session.get(StChAssumptionsImpl.class,
					primaryKey);

			if (stChAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stChAssumptions);
		}
		catch (NoSuchStChAssumptionsException nsee) {
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
	protected StChAssumptions removeImpl(StChAssumptions stChAssumptions) {
		stChAssumptions = toUnwrappedModel(stChAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stChAssumptions)) {
				stChAssumptions = (StChAssumptions)session.get(StChAssumptionsImpl.class,
						stChAssumptions.getPrimaryKeyObj());
			}

			if (stChAssumptions != null) {
				session.delete(stChAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stChAssumptions != null) {
			clearCache(stChAssumptions);
		}

		return stChAssumptions;
	}

	@Override
	public StChAssumptions updateImpl(StChAssumptions stChAssumptions) {
		stChAssumptions = toUnwrappedModel(stChAssumptions);

		boolean isNew = stChAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stChAssumptions.isNew()) {
				session.save(stChAssumptions);

				stChAssumptions.setNew(false);
			}
			else {
				stChAssumptions = (StChAssumptions)session.merge(stChAssumptions);
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

		entityCache.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			StChAssumptionsImpl.class, stChAssumptions.getPrimaryKey(),
			stChAssumptions, false);

		stChAssumptions.resetOriginalValues();

		return stChAssumptions;
	}

	protected StChAssumptions toUnwrappedModel(StChAssumptions stChAssumptions) {
		if (stChAssumptions instanceof StChAssumptionsImpl) {
			return stChAssumptions;
		}

		StChAssumptionsImpl stChAssumptionsImpl = new StChAssumptionsImpl();

		stChAssumptionsImpl.setNew(stChAssumptions.isNew());
		stChAssumptionsImpl.setPrimaryKey(stChAssumptions.getPrimaryKey());

		stChAssumptionsImpl.setLastModifiedDate(stChAssumptions.getLastModifiedDate());
		stChAssumptionsImpl.setParent(stChAssumptions.isParent());
		stChAssumptionsImpl.setCommentary(stChAssumptions.getCommentary());
		stChAssumptionsImpl.setProjectionDetailsSid(stChAssumptions.getProjectionDetailsSid());
		stChAssumptionsImpl.setUserId(stChAssumptions.getUserId());
		stChAssumptionsImpl.setQuarter(stChAssumptions.getQuarter());
		stChAssumptionsImpl.setTotalDiscountPercentChange(stChAssumptions.getTotalDiscountPercentChange());
		stChAssumptionsImpl.setReasonCodes(stChAssumptions.getReasonCodes());
		stChAssumptionsImpl.setYear(stChAssumptions.getYear());
		stChAssumptionsImpl.setTotalDiscountPercentProjected(stChAssumptions.getTotalDiscountPercentProjected());
		stChAssumptionsImpl.setTotalDiscountPercentPrior(stChAssumptions.getTotalDiscountPercentPrior());
		stChAssumptionsImpl.setStChAssumptionsSid(stChAssumptions.getStChAssumptionsSid());
		stChAssumptionsImpl.setChAssumptionsSid(stChAssumptions.getChAssumptionsSid());
		stChAssumptionsImpl.setTotalDiscountChange(stChAssumptions.getTotalDiscountChange());
		stChAssumptionsImpl.setSessionId(stChAssumptions.getSessionId());
		stChAssumptionsImpl.setTotalDiscountProjected(stChAssumptions.getTotalDiscountProjected());
		stChAssumptionsImpl.setIsChecked(stChAssumptions.isIsChecked());
		stChAssumptionsImpl.setCamId(stChAssumptions.getCamId());
		stChAssumptionsImpl.setGrossTradeSales(stChAssumptions.getGrossTradeSales());
		stChAssumptionsImpl.setTotalDiscountPrior(stChAssumptions.getTotalDiscountPrior());

		return stChAssumptionsImpl;
	}

	/**
	 * Returns the st ch assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch assumptions
	 * @return the st ch assumptions
	 * @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStChAssumptionsException {
		StChAssumptions stChAssumptions = fetchByPrimaryKey(primaryKey);

		if (stChAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stChAssumptions;
	}

	/**
	 * Returns the st ch assumptions with the primary key or throws a {@link NoSuchStChAssumptionsException} if it could not be found.
	 *
	 * @param stChAssumptionsPK the primary key of the st ch assumptions
	 * @return the st ch assumptions
	 * @throws NoSuchStChAssumptionsException if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions findByPrimaryKey(StChAssumptionsPK stChAssumptionsPK)
		throws NoSuchStChAssumptionsException {
		return findByPrimaryKey((Serializable)stChAssumptionsPK);
	}

	/**
	 * Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st ch assumptions
	 * @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				StChAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StChAssumptions stChAssumptions = (StChAssumptions)serializable;

		if (stChAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				stChAssumptions = (StChAssumptions)session.get(StChAssumptionsImpl.class,
						primaryKey);

				if (stChAssumptions != null) {
					cacheResult(stChAssumptions);
				}
				else {
					entityCache.putResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						StChAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					StChAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stChAssumptions;
	}

	/**
	 * Returns the st ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stChAssumptionsPK the primary key of the st ch assumptions
	 * @return the st ch assumptions, or <code>null</code> if a st ch assumptions with the primary key could not be found
	 */
	@Override
	public StChAssumptions fetchByPrimaryKey(
		StChAssumptionsPK stChAssumptionsPK) {
		return fetchByPrimaryKey((Serializable)stChAssumptionsPK);
	}

	@Override
	public Map<Serializable, StChAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StChAssumptions> map = new HashMap<Serializable, StChAssumptions>();

		for (Serializable primaryKey : primaryKeys) {
			StChAssumptions stChAssumptions = fetchByPrimaryKey(primaryKey);

			if (stChAssumptions != null) {
				map.put(primaryKey, stChAssumptions);
			}
		}

		return map;
	}

	/**
	 * Returns all the st ch assumptionses.
	 *
	 * @return the st ch assumptionses
	 */
	@Override
	public List<StChAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch assumptionses
	 * @param end the upper bound of the range of st ch assumptionses (not inclusive)
	 * @return the range of st ch assumptionses
	 */
	@Override
	public List<StChAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch assumptionses
	 * @param end the upper bound of the range of st ch assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st ch assumptionses
	 */
	@Override
	public List<StChAssumptions> findAll(int start, int end,
		OrderByComparator<StChAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st ch assumptionses
	 * @param end the upper bound of the range of st ch assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st ch assumptionses
	 */
	@Override
	public List<StChAssumptions> findAll(int start, int end,
		OrderByComparator<StChAssumptions> orderByComparator,
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

		List<StChAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<StChAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STCHASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STCHASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(StChAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StChAssumptions>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StChAssumptions>)QueryUtil.list(q,
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
	 * Removes all the st ch assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StChAssumptions stChAssumptions : findAll()) {
			remove(stChAssumptions);
		}
	}

	/**
	 * Returns the number of st ch assumptionses.
	 *
	 * @return the number of st ch assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STCHASSUMPTIONS);

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
		return StChAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st ch assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StChAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STCHASSUMPTIONS = "SELECT stChAssumptions FROM StChAssumptions stChAssumptions";
	private static final String _SQL_COUNT_STCHASSUMPTIONS = "SELECT COUNT(stChAssumptions) FROM StChAssumptions stChAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stChAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StChAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StChAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"lastModifiedDate", "parent", "commentary",
				"projectionDetailsSid", "userId", "quarter",
				"totalDiscountPercentChange", "reasonCodes", "year",
				"totalDiscountPercentProjected", "totalDiscountPercentPrior",
				"stChAssumptionsSid", "chAssumptionsSid", "totalDiscountChange",
				"sessionId", "totalDiscountProjected", "isChecked", "camId",
				"grossTradeSales", "totalDiscountPrior"
			});
}