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

import com.stpl.app.exception.NoSuchMSupplementalDiscMasterException;
import com.stpl.app.model.MSupplementalDiscMaster;
import com.stpl.app.model.impl.MSupplementalDiscMasterImpl;
import com.stpl.app.model.impl.MSupplementalDiscMasterModelImpl;
import com.stpl.app.service.persistence.MSupplementalDiscMasterPersistence;

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
 * The persistence implementation for the m supplemental disc master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MSupplementalDiscMasterPersistence
 * @see com.stpl.app.service.persistence.MSupplementalDiscMasterUtil
 * @generated
 */
@ProviderType
public class MSupplementalDiscMasterPersistenceImpl extends BasePersistenceImpl<MSupplementalDiscMaster>
	implements MSupplementalDiscMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MSupplementalDiscMasterUtil} to access the m supplemental disc master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MSupplementalDiscMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED,
			MSupplementalDiscMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public MSupplementalDiscMasterPersistenceImpl() {
		setModelClass(MSupplementalDiscMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualDiscountRate2", "ACTUAL_DISCOUNT_RATE2");
			dbColumnNames.put("actualDiscountRate1", "ACTUAL_DISCOUNT_RATE1");
			dbColumnNames.put("marketType", "MARKET_TYPE");
			dbColumnNames.put("actualMethodology", "ACTUAL_METHODOLOGY");
			dbColumnNames.put("actualContractPrice", "ACTUAL_CONTRACT_PRICE");
			dbColumnNames.put("projectionDetailsSid", "PROJECTION_DETAILS_SID");
			dbColumnNames.put("actualDiscount", "ACTUAL_DISCOUNT");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("contractEndDate", "CONTRACT_END_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the m supplemental disc master in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscMaster the m supplemental disc master
	 */
	@Override
	public void cacheResult(MSupplementalDiscMaster mSupplementalDiscMaster) {
		entityCache.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterImpl.class,
			mSupplementalDiscMaster.getPrimaryKey(), mSupplementalDiscMaster);

		mSupplementalDiscMaster.resetOriginalValues();
	}

	/**
	 * Caches the m supplemental disc masters in the entity cache if it is enabled.
	 *
	 * @param mSupplementalDiscMasters the m supplemental disc masters
	 */
	@Override
	public void cacheResult(
		List<MSupplementalDiscMaster> mSupplementalDiscMasters) {
		for (MSupplementalDiscMaster mSupplementalDiscMaster : mSupplementalDiscMasters) {
			if (entityCache.getResult(
						MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscMasterImpl.class,
						mSupplementalDiscMaster.getPrimaryKey()) == null) {
				cacheResult(mSupplementalDiscMaster);
			}
			else {
				mSupplementalDiscMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all m supplemental disc masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MSupplementalDiscMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the m supplemental disc master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MSupplementalDiscMaster mSupplementalDiscMaster) {
		entityCache.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterImpl.class,
			mSupplementalDiscMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<MSupplementalDiscMaster> mSupplementalDiscMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MSupplementalDiscMaster mSupplementalDiscMaster : mSupplementalDiscMasters) {
			entityCache.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscMasterImpl.class,
				mSupplementalDiscMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new m supplemental disc master with the primary key. Does not add the m supplemental disc master to the database.
	 *
	 * @param projectionDetailsSid the primary key for the new m supplemental disc master
	 * @return the new m supplemental disc master
	 */
	@Override
	public MSupplementalDiscMaster create(int projectionDetailsSid) {
		MSupplementalDiscMaster mSupplementalDiscMaster = new MSupplementalDiscMasterImpl();

		mSupplementalDiscMaster.setNew(true);
		mSupplementalDiscMaster.setPrimaryKey(projectionDetailsSid);

		return mSupplementalDiscMaster;
	}

	/**
	 * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc master
	 * @return the m supplemental disc master that was removed
	 * @throws NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster remove(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscMasterException {
		return remove((Serializable)projectionDetailsSid);
	}

	/**
	 * Removes the m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the m supplemental disc master
	 * @return the m supplemental disc master that was removed
	 * @throws NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster remove(Serializable primaryKey)
		throws NoSuchMSupplementalDiscMasterException {
		Session session = null;

		try {
			session = openSession();

			MSupplementalDiscMaster mSupplementalDiscMaster = (MSupplementalDiscMaster)session.get(MSupplementalDiscMasterImpl.class,
					primaryKey);

			if (mSupplementalDiscMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(mSupplementalDiscMaster);
		}
		catch (NoSuchMSupplementalDiscMasterException nsee) {
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
	protected MSupplementalDiscMaster removeImpl(
		MSupplementalDiscMaster mSupplementalDiscMaster) {
		mSupplementalDiscMaster = toUnwrappedModel(mSupplementalDiscMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(mSupplementalDiscMaster)) {
				mSupplementalDiscMaster = (MSupplementalDiscMaster)session.get(MSupplementalDiscMasterImpl.class,
						mSupplementalDiscMaster.getPrimaryKeyObj());
			}

			if (mSupplementalDiscMaster != null) {
				session.delete(mSupplementalDiscMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (mSupplementalDiscMaster != null) {
			clearCache(mSupplementalDiscMaster);
		}

		return mSupplementalDiscMaster;
	}

	@Override
	public MSupplementalDiscMaster updateImpl(
		MSupplementalDiscMaster mSupplementalDiscMaster) {
		mSupplementalDiscMaster = toUnwrappedModel(mSupplementalDiscMaster);

		boolean isNew = mSupplementalDiscMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (mSupplementalDiscMaster.isNew()) {
				session.save(mSupplementalDiscMaster);

				mSupplementalDiscMaster.setNew(false);
			}
			else {
				mSupplementalDiscMaster = (MSupplementalDiscMaster)session.merge(mSupplementalDiscMaster);
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

		entityCache.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
			MSupplementalDiscMasterImpl.class,
			mSupplementalDiscMaster.getPrimaryKey(), mSupplementalDiscMaster,
			false);

		mSupplementalDiscMaster.resetOriginalValues();

		return mSupplementalDiscMaster;
	}

	protected MSupplementalDiscMaster toUnwrappedModel(
		MSupplementalDiscMaster mSupplementalDiscMaster) {
		if (mSupplementalDiscMaster instanceof MSupplementalDiscMasterImpl) {
			return mSupplementalDiscMaster;
		}

		MSupplementalDiscMasterImpl mSupplementalDiscMasterImpl = new MSupplementalDiscMasterImpl();

		mSupplementalDiscMasterImpl.setNew(mSupplementalDiscMaster.isNew());
		mSupplementalDiscMasterImpl.setPrimaryKey(mSupplementalDiscMaster.getPrimaryKey());

		mSupplementalDiscMasterImpl.setActualDiscountRate2(mSupplementalDiscMaster.getActualDiscountRate2());
		mSupplementalDiscMasterImpl.setActualDiscountRate1(mSupplementalDiscMaster.getActualDiscountRate1());
		mSupplementalDiscMasterImpl.setMarketType(mSupplementalDiscMaster.getMarketType());
		mSupplementalDiscMasterImpl.setActualMethodology(mSupplementalDiscMaster.getActualMethodology());
		mSupplementalDiscMasterImpl.setActualContractPrice(mSupplementalDiscMaster.getActualContractPrice());
		mSupplementalDiscMasterImpl.setProjectionDetailsSid(mSupplementalDiscMaster.getProjectionDetailsSid());
		mSupplementalDiscMasterImpl.setActualDiscount(mSupplementalDiscMaster.getActualDiscount());
		mSupplementalDiscMasterImpl.setCheckRecord(mSupplementalDiscMaster.getCheckRecord());
		mSupplementalDiscMasterImpl.setContractEndDate(mSupplementalDiscMaster.getContractEndDate());

		return mSupplementalDiscMasterImpl;
	}

	/**
	 * Returns the m supplemental disc master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc master
	 * @return the m supplemental disc master
	 * @throws NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMSupplementalDiscMasterException {
		MSupplementalDiscMaster mSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

		if (mSupplementalDiscMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMSupplementalDiscMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return mSupplementalDiscMaster;
	}

	/**
	 * Returns the m supplemental disc master with the primary key or throws a {@link NoSuchMSupplementalDiscMasterException} if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc master
	 * @return the m supplemental disc master
	 * @throws NoSuchMSupplementalDiscMasterException if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster findByPrimaryKey(int projectionDetailsSid)
		throws NoSuchMSupplementalDiscMasterException {
		return findByPrimaryKey((Serializable)projectionDetailsSid);
	}

	/**
	 * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the m supplemental disc master
	 * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
				MSupplementalDiscMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MSupplementalDiscMaster mSupplementalDiscMaster = (MSupplementalDiscMaster)serializable;

		if (mSupplementalDiscMaster == null) {
			Session session = null;

			try {
				session = openSession();

				mSupplementalDiscMaster = (MSupplementalDiscMaster)session.get(MSupplementalDiscMasterImpl.class,
						primaryKey);

				if (mSupplementalDiscMaster != null) {
					cacheResult(mSupplementalDiscMaster);
				}
				else {
					entityCache.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
						MSupplementalDiscMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return mSupplementalDiscMaster;
	}

	/**
	 * Returns the m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param projectionDetailsSid the primary key of the m supplemental disc master
	 * @return the m supplemental disc master, or <code>null</code> if a m supplemental disc master with the primary key could not be found
	 */
	@Override
	public MSupplementalDiscMaster fetchByPrimaryKey(int projectionDetailsSid) {
		return fetchByPrimaryKey((Serializable)projectionDetailsSid);
	}

	@Override
	public Map<Serializable, MSupplementalDiscMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MSupplementalDiscMaster> map = new HashMap<Serializable, MSupplementalDiscMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MSupplementalDiscMaster mSupplementalDiscMaster = fetchByPrimaryKey(primaryKey);

			if (mSupplementalDiscMaster != null) {
				map.put(primaryKey, mSupplementalDiscMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MSupplementalDiscMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MSUPPLEMENTALDISCMASTER_WHERE_PKS_IN);

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

			for (MSupplementalDiscMaster mSupplementalDiscMaster : (List<MSupplementalDiscMaster>)q.list()) {
				map.put(mSupplementalDiscMaster.getPrimaryKeyObj(),
					mSupplementalDiscMaster);

				cacheResult(mSupplementalDiscMaster);

				uncachedPrimaryKeys.remove(mSupplementalDiscMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MSupplementalDiscMasterModelImpl.ENTITY_CACHE_ENABLED,
					MSupplementalDiscMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the m supplemental disc masters.
	 *
	 * @return the m supplemental disc masters
	 */
	@Override
	public List<MSupplementalDiscMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc masters
	 * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
	 * @return the range of m supplemental disc masters
	 */
	@Override
	public List<MSupplementalDiscMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc masters
	 * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of m supplemental disc masters
	 */
	@Override
	public List<MSupplementalDiscMaster> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the m supplemental disc masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m supplemental disc masters
	 * @param end the upper bound of the range of m supplemental disc masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of m supplemental disc masters
	 */
	@Override
	public List<MSupplementalDiscMaster> findAll(int start, int end,
		OrderByComparator<MSupplementalDiscMaster> orderByComparator,
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

		List<MSupplementalDiscMaster> list = null;

		if (retrieveFromCache) {
			list = (List<MSupplementalDiscMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MSUPPLEMENTALDISCMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MSUPPLEMENTALDISCMASTER;

				if (pagination) {
					sql = sql.concat(MSupplementalDiscMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MSupplementalDiscMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MSupplementalDiscMaster>)QueryUtil.list(q,
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
	 * Removes all the m supplemental disc masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MSupplementalDiscMaster mSupplementalDiscMaster : findAll()) {
			remove(mSupplementalDiscMaster);
		}
	}

	/**
	 * Returns the number of m supplemental disc masters.
	 *
	 * @return the number of m supplemental disc masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MSUPPLEMENTALDISCMASTER);

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
		return MSupplementalDiscMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the m supplemental disc master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MSupplementalDiscMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_MSUPPLEMENTALDISCMASTER = "SELECT mSupplementalDiscMaster FROM MSupplementalDiscMaster mSupplementalDiscMaster";
	private static final String _SQL_SELECT_MSUPPLEMENTALDISCMASTER_WHERE_PKS_IN =
		"SELECT mSupplementalDiscMaster FROM MSupplementalDiscMaster mSupplementalDiscMaster WHERE PROJECTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_MSUPPLEMENTALDISCMASTER = "SELECT COUNT(mSupplementalDiscMaster) FROM MSupplementalDiscMaster mSupplementalDiscMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "mSupplementalDiscMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MSupplementalDiscMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(MSupplementalDiscMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualDiscountRate2", "actualDiscountRate1", "marketType",
				"actualMethodology", "actualContractPrice",
				"projectionDetailsSid", "actualDiscount", "checkRecord",
				"contractEndDate"
			});
}