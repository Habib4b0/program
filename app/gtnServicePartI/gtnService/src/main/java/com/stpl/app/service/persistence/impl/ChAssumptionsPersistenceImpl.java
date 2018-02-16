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

import com.stpl.app.exception.NoSuchChAssumptionsException;
import com.stpl.app.model.ChAssumptions;
import com.stpl.app.model.impl.ChAssumptionsImpl;
import com.stpl.app.model.impl.ChAssumptionsModelImpl;
import com.stpl.app.service.persistence.ChAssumptionsPersistence;

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
 * The persistence implementation for the ch assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChAssumptionsPersistence
 * @see com.stpl.app.service.persistence.ChAssumptionsUtil
 * @generated
 */
@ProviderType
public class ChAssumptionsPersistenceImpl extends BasePersistenceImpl<ChAssumptions>
	implements ChAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChAssumptionsUtil} to access the ch assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			ChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsModelImpl.FINDER_CACHE_ENABLED,
			ChAssumptionsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ChAssumptionsPersistenceImpl() {
		setModelClass(ChAssumptions.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("parent", "PARENT");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("commentary", "COMMENTARY");
			dbColumnNames.put("quarter", "QUARTER");
			dbColumnNames.put("totalDiscountPercentChange",
				"TOTAL_DISCOUNT_PERCENT_CHANGE");
			dbColumnNames.put("reasonCodes", "REASON_CODES");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("totalDiscountPercentProjected",
				"TOTAL_DISCOUNT_PERCENT_PROJECTED");
			dbColumnNames.put("totalDiscountPercentPrior",
				"TOTAL_DISCOUNT_PERCENT_PRIOR");
			dbColumnNames.put("chAssumptionsSid", "CH_ASSUMPTIONS_SID");
			dbColumnNames.put("totalDiscountChange", "TOTAL_DISCOUNT_CHANGE");
			dbColumnNames.put("totalDiscountProjected",
				"TOTAL_DISCOUNT_PROJECTED");
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
	 * Caches the ch assumptions in the entity cache if it is enabled.
	 *
	 * @param chAssumptions the ch assumptions
	 */
	@Override
	public void cacheResult(ChAssumptions chAssumptions) {
		entityCache.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsImpl.class, chAssumptions.getPrimaryKey(),
			chAssumptions);

		chAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the ch assumptionses in the entity cache if it is enabled.
	 *
	 * @param chAssumptionses the ch assumptionses
	 */
	@Override
	public void cacheResult(List<ChAssumptions> chAssumptionses) {
		for (ChAssumptions chAssumptions : chAssumptionses) {
			if (entityCache.getResult(
						ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						ChAssumptionsImpl.class, chAssumptions.getPrimaryKey()) == null) {
				cacheResult(chAssumptions);
			}
			else {
				chAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ch assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ChAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ch assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ChAssumptions chAssumptions) {
		entityCache.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsImpl.class, chAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ChAssumptions> chAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ChAssumptions chAssumptions : chAssumptionses) {
			entityCache.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				ChAssumptionsImpl.class, chAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
	 *
	 * @param chAssumptionsSid the primary key for the new ch assumptions
	 * @return the new ch assumptions
	 */
	@Override
	public ChAssumptions create(int chAssumptionsSid) {
		ChAssumptions chAssumptions = new ChAssumptionsImpl();

		chAssumptions.setNew(true);
		chAssumptions.setPrimaryKey(chAssumptionsSid);

		return chAssumptions;
	}

	/**
	 * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chAssumptionsSid the primary key of the ch assumptions
	 * @return the ch assumptions that was removed
	 * @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions remove(int chAssumptionsSid)
		throws NoSuchChAssumptionsException {
		return remove((Serializable)chAssumptionsSid);
	}

	/**
	 * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ch assumptions
	 * @return the ch assumptions that was removed
	 * @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions remove(Serializable primaryKey)
		throws NoSuchChAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			ChAssumptions chAssumptions = (ChAssumptions)session.get(ChAssumptionsImpl.class,
					primaryKey);

			if (chAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chAssumptions);
		}
		catch (NoSuchChAssumptionsException nsee) {
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
	protected ChAssumptions removeImpl(ChAssumptions chAssumptions) {
		chAssumptions = toUnwrappedModel(chAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chAssumptions)) {
				chAssumptions = (ChAssumptions)session.get(ChAssumptionsImpl.class,
						chAssumptions.getPrimaryKeyObj());
			}

			if (chAssumptions != null) {
				session.delete(chAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chAssumptions != null) {
			clearCache(chAssumptions);
		}

		return chAssumptions;
	}

	@Override
	public ChAssumptions updateImpl(ChAssumptions chAssumptions) {
		chAssumptions = toUnwrappedModel(chAssumptions);

		boolean isNew = chAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (chAssumptions.isNew()) {
				session.save(chAssumptions);

				chAssumptions.setNew(false);
			}
			else {
				chAssumptions = (ChAssumptions)session.merge(chAssumptions);
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

		entityCache.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			ChAssumptionsImpl.class, chAssumptions.getPrimaryKey(),
			chAssumptions, false);

		chAssumptions.resetOriginalValues();

		return chAssumptions;
	}

	protected ChAssumptions toUnwrappedModel(ChAssumptions chAssumptions) {
		if (chAssumptions instanceof ChAssumptionsImpl) {
			return chAssumptions;
		}

		ChAssumptionsImpl chAssumptionsImpl = new ChAssumptionsImpl();

		chAssumptionsImpl.setNew(chAssumptions.isNew());
		chAssumptionsImpl.setPrimaryKey(chAssumptions.getPrimaryKey());

		chAssumptionsImpl.setParent(chAssumptions.isParent());
		chAssumptionsImpl.setProjectionDetailsSid(chAssumptions.getProjectionDetailsSid());
		chAssumptionsImpl.setCommentary(chAssumptions.getCommentary());
		chAssumptionsImpl.setQuarter(chAssumptions.getQuarter());
		chAssumptionsImpl.setTotalDiscountPercentChange(chAssumptions.getTotalDiscountPercentChange());
		chAssumptionsImpl.setReasonCodes(chAssumptions.getReasonCodes());
		chAssumptionsImpl.setYear(chAssumptions.getYear());
		chAssumptionsImpl.setTotalDiscountPercentProjected(chAssumptions.getTotalDiscountPercentProjected());
		chAssumptionsImpl.setTotalDiscountPercentPrior(chAssumptions.getTotalDiscountPercentPrior());
		chAssumptionsImpl.setChAssumptionsSid(chAssumptions.getChAssumptionsSid());
		chAssumptionsImpl.setTotalDiscountChange(chAssumptions.getTotalDiscountChange());
		chAssumptionsImpl.setTotalDiscountProjected(chAssumptions.getTotalDiscountProjected());
		chAssumptionsImpl.setCamId(chAssumptions.getCamId());
		chAssumptionsImpl.setGrossTradeSales(chAssumptions.getGrossTradeSales());
		chAssumptionsImpl.setTotalDiscountPrior(chAssumptions.getTotalDiscountPrior());

		return chAssumptionsImpl;
	}

	/**
	 * Returns the ch assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch assumptions
	 * @return the ch assumptions
	 * @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChAssumptionsException {
		ChAssumptions chAssumptions = fetchByPrimaryKey(primaryKey);

		if (chAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chAssumptions;
	}

	/**
	 * Returns the ch assumptions with the primary key or throws a {@link NoSuchChAssumptionsException} if it could not be found.
	 *
	 * @param chAssumptionsSid the primary key of the ch assumptions
	 * @return the ch assumptions
	 * @throws NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions findByPrimaryKey(int chAssumptionsSid)
		throws NoSuchChAssumptionsException {
		return findByPrimaryKey((Serializable)chAssumptionsSid);
	}

	/**
	 * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ch assumptions
	 * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				ChAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ChAssumptions chAssumptions = (ChAssumptions)serializable;

		if (chAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				chAssumptions = (ChAssumptions)session.get(ChAssumptionsImpl.class,
						primaryKey);

				if (chAssumptions != null) {
					cacheResult(chAssumptions);
				}
				else {
					entityCache.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						ChAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					ChAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chAssumptions;
	}

	/**
	 * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chAssumptionsSid the primary key of the ch assumptions
	 * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
	 */
	@Override
	public ChAssumptions fetchByPrimaryKey(int chAssumptionsSid) {
		return fetchByPrimaryKey((Serializable)chAssumptionsSid);
	}

	@Override
	public Map<Serializable, ChAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ChAssumptions> map = new HashMap<Serializable, ChAssumptions>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ChAssumptions chAssumptions = fetchByPrimaryKey(primaryKey);

			if (chAssumptions != null) {
				map.put(primaryKey, chAssumptions);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					ChAssumptionsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ChAssumptions)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CHASSUMPTIONS_WHERE_PKS_IN);

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

			for (ChAssumptions chAssumptions : (List<ChAssumptions>)q.list()) {
				map.put(chAssumptions.getPrimaryKeyObj(), chAssumptions);

				cacheResult(chAssumptions);

				uncachedPrimaryKeys.remove(chAssumptions.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ChAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					ChAssumptionsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ch assumptionses.
	 *
	 * @return the ch assumptionses
	 */
	@Override
	public List<ChAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch assumptionses
	 * @param end the upper bound of the range of ch assumptionses (not inclusive)
	 * @return the range of ch assumptionses
	 */
	@Override
	public List<ChAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch assumptionses
	 * @param end the upper bound of the range of ch assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ch assumptionses
	 */
	@Override
	public List<ChAssumptions> findAll(int start, int end,
		OrderByComparator<ChAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ch assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ch assumptionses
	 * @param end the upper bound of the range of ch assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ch assumptionses
	 */
	@Override
	public List<ChAssumptions> findAll(int start, int end,
		OrderByComparator<ChAssumptions> orderByComparator,
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

		List<ChAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<ChAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CHASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(ChAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ChAssumptions>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ChAssumptions>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ch assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ChAssumptions chAssumptions : findAll()) {
			remove(chAssumptions);
		}
	}

	/**
	 * Returns the number of ch assumptionses.
	 *
	 * @return the number of ch assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHASSUMPTIONS);

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
		return ChAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ch assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ChAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CHASSUMPTIONS = "SELECT chAssumptions FROM ChAssumptions chAssumptions";
	private static final String _SQL_SELECT_CHASSUMPTIONS_WHERE_PKS_IN = "SELECT chAssumptions FROM ChAssumptions chAssumptions WHERE CH_ASSUMPTIONS_SID IN (";
	private static final String _SQL_COUNT_CHASSUMPTIONS = "SELECT COUNT(chAssumptions) FROM ChAssumptions chAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ChAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ChAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"parent", "projectionDetailsSid", "commentary", "quarter",
				"totalDiscountPercentChange", "reasonCodes", "year",
				"totalDiscountPercentProjected", "totalDiscountPercentPrior",
				"chAssumptionsSid", "totalDiscountChange",
				"totalDiscountProjected", "camId", "grossTradeSales",
				"totalDiscountPrior"
			});
}