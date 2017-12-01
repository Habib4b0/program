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

import com.stpl.app.exception.NoSuchMSupplementalDiscProjException;
import com.stpl.app.model.MSupplementalDiscProj;
import com.stpl.app.model.impl.MSupplementalDiscProjImpl;
import com.stpl.app.model.impl.MSupplementalDiscProjModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscProjPersistence;

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
 * The persistence implementation for the m supplemental disc proj service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscProjPersistence
 * @see com.stpl.app.service.persistence.MSupplementalDiscProjUtil
 * @generated
 */
@ProviderType
public class MSupplementalDiscProjPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscProj>
	implements MSupplementalDiscProjPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSupplementalDiscProjUtil} to access the m supplemental disc proj persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscProjImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscProjImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MSupplementalDiscProjPersistenceImpl() {
		setModelClass(MSupplementalDiscProj.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("methodology", "METHODOLOGY");
			dbColumnNames.put("projectionRate", "PROJECTION_RATE");
			dbColumnNames.put("parity", "PARITY");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("discountRate1", "DISCOUNT_RATE_1");
			dbColumnNames.put("parityReference", "PARITY_REFERENCE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("discountRate2", "DISCOUNT_RATE_2");
			dbColumnNames.put("parityDiscount", "PARITY_DISCOUNT");
			dbColumnNames.put("projectionSales", "PROJECTION_SALES");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
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
	 * Caches the m supplemental disc proj in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscProj the m supplemental disc proj
	 */
	@Override
	public void cacheResult(MSupplementalDiscProj mSupplementalDiscProj) {
		entityCache.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjImpl.class,
			mSupplementalDiscProj.getPrimaryKey(), mSupplementalDiscProj);

		mSupplementalDiscProj.resetOriginalValues();
	}

	/**
	 * Caches the m supplemental disc projs in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscProjs the m supplemental disc projs
	 */
	@Override
	public void cacheResult(List<MSupplementalDiscProj> mSupplementalDiscProjs) {
		for (MSupplementalDiscProj mSupplementalDiscProj : mSupplementalDiscProjs) {
			if (entityCache.getResult(
						MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscProjImpl.class,
						mSupplementalDiscProj.getPrimaryKey()) == null) {
				cacheResult(mSupplementalDiscProj);
			}
			else {
				mSupplementalDiscProj.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m supplemental disc projs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSupplementalDiscProjImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m supplemental disc proj.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSupplementalDiscProj mSupplementalDiscProj) {
		entityCache.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjImpl.class,
			mSupplementalDiscProj.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MSupplementalDiscProj> mSupplementalDiscProjs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSupplementalDiscProj mSupplementalDiscProj : mSupplementalDiscProjs) {
			entityCache.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscProjImpl.class,
				mSupplementalDiscProj.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m supplemental disc proj with the primary key. Does not add the m supplemental disc proj to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new m supplemental disc proj
	 * @return the new m supplemental disc proj
	 */
	@Override
	public MSupplementalDiscProj create(int projectionDetailsSid) {
		MSupplementalDiscProj mSupplementalDiscProj = new MSupplementalDiscProjImpl();

		mSupplementalDiscProj.setNew(true);
		mSupplementalDiscProj.setPrimaryKey(projectionDetailsSid);

		return mSupplementalDiscProj;
	}

	/**
	 * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj that was removed
	 * @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj remove(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscProjException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the m supplemental disc proj with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj that was removed
	 * @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj remove(Serializable primaryKey)
		throws NoSuchMSupplementalDiscProjException {
		Session session = null;

		try {
			session = openSession();

			MSupplementalDiscProj mSupplementalDiscProj = (MSupplementalDiscProj)session.get(MSupplementalDiscProjImpl.class,
					primaryKey);

			if (mSupplementalDiscProj == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mSupplementalDiscProj);
		}
		catch (NoSuchMSupplementalDiscProjException nsee) {
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
	protected MSupplementalDiscProj removeImpl(
		MSupplementalDiscProj mSupplementalDiscProj) {
		mSupplementalDiscProj = toUnwrappedModel(mSupplementalDiscProj);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mSupplementalDiscProj)) {
				mSupplementalDiscProj = (MSupplementalDiscProj)session.get(MSupplementalDiscProjImpl.class,
						mSupplementalDiscProj.getPrimaryKeyObj());
			}

			if (mSupplementalDiscProj != null) {
				session.delete(mSupplementalDiscProj);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mSupplementalDiscProj != null) {
			clearCache(mSupplementalDiscProj);
		}

		return mSupplementalDiscProj;
	}

	@Override
	public MSupplementalDiscProj updateImpl(
		MSupplementalDiscProj mSupplementalDiscProj) {
		mSupplementalDiscProj = toUnwrappedModel(mSupplementalDiscProj);

		boolean isNew = mSupplementalDiscProj.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mSupplementalDiscProj.isNew()) {
				session.save(mSupplementalDiscProj);

				mSupplementalDiscProj.setNew(false);
			}
			else {
				mSupplementalDiscProj = (MSupplementalDiscProj)session.merge(mSupplementalDiscProj);
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

		entityCache.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscProjImpl.class,
			mSupplementalDiscProj.getPrimaryKey(), mSupplementalDiscProj, false);

		mSupplementalDiscProj.resetOriginalValues();

		return mSupplementalDiscProj;
	}

	protected MSupplementalDiscProj toUnwrappedModel(
		MSupplementalDiscProj mSupplementalDiscProj) {
		if (mSupplementalDiscProj instanceof MSupplementalDiscProjImpl) {
			return mSupplementalDiscProj;
		}

		MSupplementalDiscProjImpl mSupplementalDiscProjImpl = new MSupplementalDiscProjImpl();

		mSupplementalDiscProjImpl.setNew(mSupplementalDiscProj.isNew());
		mSupplementalDiscProjImpl.setPrimaryKey(mSupplementalDiscProj.getPrimaryKey());

		mSupplementalDiscProjImpl.setMethodology(mSupplementalDiscProj.getMethodology());
		mSupplementalDiscProjImpl.setProjectionRate(mSupplementalDiscProj.getProjectionRate());
		mSupplementalDiscProjImpl.setParity(mSupplementalDiscProj.isParity());
		mSupplementalDiscProjImpl.setPeriodSid(mSupplementalDiscProj.getPeriodSid());
		mSupplementalDiscProjImpl.setDiscountRate1(mSupplementalDiscProj.getDiscountRate1());
		mSupplementalDiscProjImpl.setParityReference(mSupplementalDiscProj.getParityReference());
		mSupplementalDiscProjImpl.setProjectionDetailsSid(mSupplementalDiscProj.getProjectionDetailsSid());
		mSupplementalDiscProjImpl.setDiscountRate2(mSupplementalDiscProj.getDiscountRate2());
		mSupplementalDiscProjImpl.setParityDiscount(mSupplementalDiscProj.getParityDiscount());
		mSupplementalDiscProjImpl.setProjectionSales(mSupplementalDiscProj.getProjectionSales());
		mSupplementalDiscProjImpl.setContractPrice(mSupplementalDiscProj.getContractPrice());
		mSupplementalDiscProjImpl.setAccess(mSupplementalDiscProj.getAccess());

		return mSupplementalDiscProjImpl;
	}

	/**
	 * Returns the m supplemental disc proj with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj
	 * @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMSupplementalDiscProjException {
		MSupplementalDiscProj mSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

		if (mSupplementalDiscProj == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMSupplementalDiscProjException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mSupplementalDiscProj;
	}

	/**
	 * Returns the m supplemental disc proj with the primary key or throws a {@link NoSuchMSupplementalDiscProjException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj
	 * @throws NoSuchMSupplementalDiscProjException if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscProjException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscProjImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSupplementalDiscProj mSupplementalDiscProj = (MSupplementalDiscProj)serializable;

		if (mSupplementalDiscProj == null) {
			Session session = null;

			try {
				session = openSession();

				mSupplementalDiscProj = (MSupplementalDiscProj)session.get(MSupplementalDiscProjImpl.class,
						primaryKey);

				if (mSupplementalDiscProj != null) {
					cacheResult(mSupplementalDiscProj);
				}
				else {
					entityCache.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscProjImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscProjImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mSupplementalDiscProj;
	}

	/**
	 * Returns the m supplemental disc proj with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc proj
	 * @return the m supplemental disc proj, or <code>null</code> if a m supplemental disc proj with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscProj fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, MSupplementalDiscProj> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSupplementalDiscProj> map = new HashMap<Serializable, MSupplementalDiscProj>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MSupplementalDiscProj mSupplementalDiscProj = fetchByPrimaryKey(primaryKey);

			if (mSupplementalDiscProj != null) {
				map.put(primaryKey, mSupplementalDiscProj);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscProjImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MSupplementalDiscProj)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MSUPPLEMENTALDISCPROJ_WHERE_PKS_IN);

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

			for (MSupplementalDiscProj mSupplementalDiscProj : (List<MSupplementalDiscProj>)q.list()) {
				map.put(mSupplementalDiscProj.getPrimaryKeyObj(),
					mSupplementalDiscProj);

				cacheResult(mSupplementalDiscProj);

				uncachedPrimaryKeys.remove(mSupplementalDiscProj.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MSupplementalDiscProjModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscProjImpl.class, primaryKey, nullModel);
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
	 * Returns all the m supplemental disc projs.
	 *
	 * @return the m supplemental disc projs
	 */
	@Override
	public List<MSupplementalDiscProj> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc projs
	 * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	 * @return the range of m supplemental disc projs
	 */
	@Override
	public List<MSupplementalDiscProj> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc projs
	 * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m supplemental disc projs
	 */
	@Override
	public List<MSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscProj> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc projs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc projs
	 * @param end the upper bound of the range of m supplemental disc projs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m supplemental disc projs
	 */
	@Override
	public List<MSupplementalDiscProj> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscProj> orderByComparator,
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

		List<MSupplementalDiscProj> list = null;

		if (retrieveFromCache) {
			list = (List<MSupplementalDiscProj>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSUPPLEMENTALDISCPROJ);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSUPPLEMENTALDISCPROJ;

				if (pagination) {
					sql = sql.concat(MSupplementalDiscProjModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSupplementalDiscProj>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSupplementalDiscProj>)QueryUtil.list(q,
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
	 * Removes all the m supplemental disc projs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSupplementalDiscProj mSupplementalDiscProj : findAll()) {
			remove(mSupplementalDiscProj);
		}
	}

	/**
	 * Returns the number of m supplemental disc projs.
	 *
	 * @return the number of m supplemental disc projs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCPROJ);

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
		return MSupplementalDiscProjModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m supplemental disc proj persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSupplementalDiscProjImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MSUPPLEMENTALDISCPROJ = "SELECT mSupplementalDiscProj FROM MSupplementalDiscProj mSupplementalDiscProj";
	private static final String _SQL_SELECT_MSUPPLEMENTALDISCPROJ_WHERE_PKS_IN = "SELECT mSupplementalDiscProj FROM MSupplementalDiscProj mSupplementalDiscProj WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_MSUPPLEMENTALDISCPROJ = "SELECT COUNT(mSupplementalDiscProj) FROM MSupplementalDiscProj mSupplementalDiscProj";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscProj.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscProj exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MSupplementalDiscProjPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"methodology", "projectionRate", "parity", "periodSid",
				"discountRate1", "parityReference", "projectionDetailsSid",
				"discountRate2", "parityDiscount", "projectionSales",
				"contractPrice", "access"
			});
}