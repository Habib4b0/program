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

import com.stpl.app.exception.NoSuchMAssumptionsException;
import com.stpl.app.model.MAssumptions;
import com.stpl.app.model.impl.MAssumptionsImpl;
import com.stpl.app.model.impl.MAssumptionsModelImpl;
import com.stpl.app.service.persistence.MAssumptionsPersistence;

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
 * The persistence implementation for the m assumptions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MAssumptionsPersistence
 * @see com.stpl.app.service.persistence.MAssumptionsUtil
 * @generated
 */
@ProviderType
public class MAssumptionsPersistenceImpl extends BasePersistenceImpl<MAssumptions>
	implements MAssumptionsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MAssumptionsUtil} to access the m assumptions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MAssumptionsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsModelImpl.FINDER_CACHE_ENABLED, MAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsModelImpl.FINDER_CACHE_ENABLED, MAssumptionsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MAssumptionsPersistenceImpl() {
		setModelClass(MAssumptions.class);

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
			dbColumnNames.put("grossSalesProjected", "GROSS_SALES_PROJECTED");
			dbColumnNames.put("totalDiscountPercentChange",
				"TOTAL_DISCOUNT_PERCENT_CHANGE");
			dbColumnNames.put("totalDiscountPercentPrior",
				"TOTAL_DISCOUNT_PERCENT_PRIOR");
			dbColumnNames.put("netSalesPercentChange",
				"NET_SALES_PERCENT_CHANGE");
			dbColumnNames.put("parent", "PARENT");
			dbColumnNames.put("projectionPeriod", "PROJECTION_PERIOD");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("netSalesPrior", "NET_SALES_PRIOR");
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
	 * Caches the m assumptions in the entity cache if it is enabled.
	 *
	 * @param mAssumptions the m assumptions
	 */
	@Override
	public void cacheResult(MAssumptions mAssumptions) {
		entityCache.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsImpl.class, mAssumptions.getPrimaryKey(), mAssumptions);

		mAssumptions.resetOriginalValues();
	}

	/**
	 * Caches the m assumptionses in the entity cache if it is enabled.
	 *
	 * @param mAssumptionses the m assumptionses
	 */
	@Override
	public void cacheResult(List<MAssumptions> mAssumptionses) {
		for (MAssumptions mAssumptions : mAssumptionses) {
			if (entityCache.getResult(
						MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						MAssumptionsImpl.class, mAssumptions.getPrimaryKey()) == null) {
				cacheResult(mAssumptions);
			}
			else {
				mAssumptions.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m assumptionses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MAssumptionsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m assumptions.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MAssumptions mAssumptions) {
		entityCache.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsImpl.class, mAssumptions.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MAssumptions> mAssumptionses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MAssumptions mAssumptions : mAssumptionses) {
			entityCache.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				MAssumptionsImpl.class, mAssumptions.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
	 *
	 * @param mAssumptionsSid the primary key for the new m assumptions
	 * @return the new m assumptions
	 */
	@Override
	public MAssumptions create(int mAssumptionsSid) {
		MAssumptions mAssumptions = new MAssumptionsImpl();

		mAssumptions.setNew(true);
		mAssumptions.setPrimaryKey(mAssumptionsSid);

		return mAssumptions;
	}

	/**
	 * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mAssumptionsSid the primary key of the m assumptions
	 * @return the m assumptions that was removed
	 * @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions remove(int mAssumptionsSid)
		throws NoSuchMAssumptionsException {
		return remove((Serializable)mAssumptionsSid);
	}

	/**
	 * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m assumptions
	 * @return the m assumptions that was removed
	 * @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions remove(Serializable primaryKey)
		throws NoSuchMAssumptionsException {
		Session session = null;

		try {
			session = openSession();

			MAssumptions mAssumptions = (MAssumptions)session.get(MAssumptionsImpl.class,
					primaryKey);

			if (mAssumptions == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mAssumptions);
		}
		catch (NoSuchMAssumptionsException nsee) {
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
	protected MAssumptions removeImpl(MAssumptions mAssumptions) {
		mAssumptions = toUnwrappedModel(mAssumptions);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mAssumptions)) {
				mAssumptions = (MAssumptions)session.get(MAssumptionsImpl.class,
						mAssumptions.getPrimaryKeyObj());
			}

			if (mAssumptions != null) {
				session.delete(mAssumptions);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mAssumptions != null) {
			clearCache(mAssumptions);
		}

		return mAssumptions;
	}

	@Override
	public MAssumptions updateImpl(MAssumptions mAssumptions) {
		mAssumptions = toUnwrappedModel(mAssumptions);

		boolean isNew = mAssumptions.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mAssumptions.isNew()) {
				session.save(mAssumptions);

				mAssumptions.setNew(false);
			}
			else {
				mAssumptions = (MAssumptions)session.merge(mAssumptions);
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

		entityCache.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
			MAssumptionsImpl.class, mAssumptions.getPrimaryKey(), mAssumptions,
			false);

		mAssumptions.resetOriginalValues();

		return mAssumptions;
	}

	protected MAssumptions toUnwrappedModel(MAssumptions mAssumptions) {
		if (mAssumptions instanceof MAssumptionsImpl) {
			return mAssumptions;
		}

		MAssumptionsImpl mAssumptionsImpl = new MAssumptionsImpl();

		mAssumptionsImpl.setNew(mAssumptions.isNew());
		mAssumptionsImpl.setPrimaryKey(mAssumptions.getPrimaryKey());

		mAssumptionsImpl.setGrossSalesPercentChange(mAssumptions.getGrossSalesPercentChange());
		mAssumptionsImpl.setGrossSalesPrior(mAssumptions.getGrossSalesPrior());
		mAssumptionsImpl.setProjYear(mAssumptions.getProjYear());
		mAssumptionsImpl.setTotalDiscountPercentProjected(mAssumptions.getTotalDiscountPercentProjected());
		mAssumptionsImpl.setCamId(mAssumptions.getCamId());
		mAssumptionsImpl.setCommentary(mAssumptions.getCommentary());
		mAssumptionsImpl.setGrossSalesProjected(mAssumptions.getGrossSalesProjected());
		mAssumptionsImpl.setTotalDiscountPercentChange(mAssumptions.getTotalDiscountPercentChange());
		mAssumptionsImpl.setTotalDiscountPercentPrior(mAssumptions.getTotalDiscountPercentPrior());
		mAssumptionsImpl.setNetSalesPercentChange(mAssumptions.getNetSalesPercentChange());
		mAssumptionsImpl.setParent(mAssumptions.isParent());
		mAssumptionsImpl.setProjectionPeriod(mAssumptions.getProjectionPeriod());
		mAssumptionsImpl.setProjectionDetailsSid(mAssumptions.getProjectionDetailsSid());
		mAssumptionsImpl.setNetSalesPrior(mAssumptions.getNetSalesPrior());
		mAssumptionsImpl.setNetSalesProjected(mAssumptions.getNetSalesProjected());
		mAssumptionsImpl.setReasonCodes(mAssumptions.getReasonCodes());
		mAssumptionsImpl.setMAssumptionsSid(mAssumptions.getMAssumptionsSid());

		return mAssumptionsImpl;
	}

	/**
	 * Returns the m assumptions with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m assumptions
	 * @return the m assumptions
	 * @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMAssumptionsException {
		MAssumptions mAssumptions = fetchByPrimaryKey(primaryKey);

		if (mAssumptions == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMAssumptionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mAssumptions;
	}

	/**
	 * Returns the m assumptions with the primary key or throws a {@link NoSuchMAssumptionsException} if it could not be found.
	 *
	 * @param mAssumptionsSid the primary key of the m assumptions
	 * @return the m assumptions
	 * @throws NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions findByPrimaryKey(int mAssumptionsSid)
		throws NoSuchMAssumptionsException {
		return findByPrimaryKey((Serializable)mAssumptionsSid);
	}

	/**
	 * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m assumptions
	 * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
				MAssumptionsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MAssumptions mAssumptions = (MAssumptions)serializable;

		if (mAssumptions == null) {
			Session session = null;

			try {
				session = openSession();

				mAssumptions = (MAssumptions)session.get(MAssumptionsImpl.class,
						primaryKey);

				if (mAssumptions != null) {
					cacheResult(mAssumptions);
				}
				else {
					entityCache.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
						MAssumptionsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					MAssumptionsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mAssumptions;
	}

	/**
	 * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param mAssumptionsSid the primary key of the m assumptions
	 * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
	 */
	@Override
	public MAssumptions fetchByPrimaryKey(int mAssumptionsSid) {
		return fetchByPrimaryKey((Serializable)mAssumptionsSid);
	}

	@Override
	public Map<Serializable, MAssumptions> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MAssumptions> map = new HashMap<Serializable, MAssumptions>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MAssumptions mAssumptions = fetchByPrimaryKey(primaryKey);

			if (mAssumptions != null) {
				map.put(primaryKey, mAssumptions);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					MAssumptionsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MAssumptions)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MASSUMPTIONS_WHERE_PKS_IN);

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

			for (MAssumptions mAssumptions : (List<MAssumptions>)q.list()) {
				map.put(mAssumptions.getPrimaryKeyObj(), mAssumptions);

				cacheResult(mAssumptions);

				uncachedPrimaryKeys.remove(mAssumptions.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MAssumptionsModelImpl.ENTITY_CACHE_ENABLED,
					MAssumptionsImpl.class, primaryKey, nullModel);
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
	 * Returns all the m assumptionses.
	 *
	 * @return the m assumptionses
	 */
	@Override
	public List<MAssumptions> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m assumptionses
	 * @param end the upper bound of the range of m assumptionses (not inclusive)
	 * @return the range of m assumptionses
	 */
	@Override
	public List<MAssumptions> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m assumptionses
	 * @param end the upper bound of the range of m assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m assumptionses
	 */
	@Override
	public List<MAssumptions> findAll(int start, int end,
		OrderByComparator<MAssumptions> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m assumptionses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m assumptionses
	 * @param end the upper bound of the range of m assumptionses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m assumptionses
	 */
	@Override
	public List<MAssumptions> findAll(int start, int end,
		OrderByComparator<MAssumptions> orderByComparator,
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

		List<MAssumptions> list = null;

		if (retrieveFromCache) {
			list = (List<MAssumptions>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MASSUMPTIONS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MASSUMPTIONS;

				if (pagination) {
					sql = sql.concat(MAssumptionsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MAssumptions>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MAssumptions>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the m assumptionses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MAssumptions mAssumptions : findAll()) {
			remove(mAssumptions);
		}
	}

	/**
	 * Returns the number of m assumptionses.
	 *
	 * @return the number of m assumptionses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MASSUMPTIONS);

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
		return MAssumptionsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m assumptions persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MAssumptionsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MASSUMPTIONS = "SELECT mAssumptions FROM MAssumptions mAssumptions";
	private static final String _SQL_SELECT_MASSUMPTIONS_WHERE_PKS_IN = "SELECT mAssumptions FROM MAssumptions mAssumptions WHERE M_ASSUMPTIONS_SID IN (";
	private static final String _SQL_COUNT_MASSUMPTIONS = "SELECT COUNT(mAssumptions) FROM MAssumptions mAssumptions";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mAssumptions.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MAssumptions exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MAssumptionsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"grossSalesPercentChange", "grossSalesPrior", "projYear",
				"totalDiscountPercentProjected", "camId", "commentary",
				"grossSalesProjected", "totalDiscountPercentChange",
				"totalDiscountPercentPrior", "netSalesPercentChange", "parent",
				"projectionPeriod", "projectionDetailsSid", "netSalesPrior",
				"netSalesProjected", "reasonCodes", "mAssumptionsSid"
			});
}