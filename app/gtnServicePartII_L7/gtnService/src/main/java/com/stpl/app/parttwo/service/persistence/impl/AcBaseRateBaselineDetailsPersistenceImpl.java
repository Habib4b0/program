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

import com.stpl.app.parttwo.exception.NoSuchAcBaseRateBaselineDetailsException;
import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;
import com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsImpl;
import com.stpl.app.parttwo.model.impl.AcBaseRateBaselineDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsPersistence;

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
 * The persistence implementation for the ac base rate baseline details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBaseRateBaselineDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.AcBaseRateBaselineDetailsUtil
 * @generated
 */
@ProviderType
public class AcBaseRateBaselineDetailsPersistenceImpl
	extends BasePersistenceImpl<AcBaseRateBaselineDetails>
	implements AcBaseRateBaselineDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AcBaseRateBaselineDetailsUtil} to access the ac base rate baseline details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AcBaseRateBaselineDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
			AcBaseRateBaselineDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
			AcBaseRateBaselineDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public AcBaseRateBaselineDetailsPersistenceImpl() {
		setModelClass(AcBaseRateBaselineDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("periodValue", "PERIOD_VALUE");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("paymentsPeriod", "PAYMENTS_PERIOD");
			dbColumnNames.put("acBrMethodologyDetailsSid",
				"AC_BR_METHODOLOGY_DETAILS_SID");
			dbColumnNames.put("salesPeriod", "SALES_PERIOD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ac base rate baseline details in the entity cache if it is enabled.
	 *
	 * @param acBaseRateBaselineDetails the ac base rate baseline details
	 */
	@Override
	public void cacheResult(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		entityCache.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsImpl.class,
			acBaseRateBaselineDetails.getPrimaryKey(), acBaseRateBaselineDetails);

		acBaseRateBaselineDetails.resetOriginalValues();
	}

	/**
	 * Caches the ac base rate baseline detailses in the entity cache if it is enabled.
	 *
	 * @param acBaseRateBaselineDetailses the ac base rate baseline detailses
	 */
	@Override
	public void cacheResult(
		List<AcBaseRateBaselineDetails> acBaseRateBaselineDetailses) {
		for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : acBaseRateBaselineDetailses) {
			if (entityCache.getResult(
						AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AcBaseRateBaselineDetailsImpl.class,
						acBaseRateBaselineDetails.getPrimaryKey()) == null) {
				cacheResult(acBaseRateBaselineDetails);
			}
			else {
				acBaseRateBaselineDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ac base rate baseline detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AcBaseRateBaselineDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ac base rate baseline details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		entityCache.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsImpl.class,
			acBaseRateBaselineDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<AcBaseRateBaselineDetails> acBaseRateBaselineDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : acBaseRateBaselineDetailses) {
			entityCache.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AcBaseRateBaselineDetailsImpl.class,
				acBaseRateBaselineDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ac base rate baseline details with the primary key. Does not add the ac base rate baseline details to the database.
	 *
	 * @param acBrMethodologyDetailsSid the primary key for the new ac base rate baseline details
	 * @return the new ac base rate baseline details
	 */
	@Override
	public AcBaseRateBaselineDetails create(int acBrMethodologyDetailsSid) {
		AcBaseRateBaselineDetails acBaseRateBaselineDetails = new AcBaseRateBaselineDetailsImpl();

		acBaseRateBaselineDetails.setNew(true);
		acBaseRateBaselineDetails.setPrimaryKey(acBrMethodologyDetailsSid);

		return acBaseRateBaselineDetails;
	}

	/**
	 * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details that was removed
	 * @throws NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails remove(int acBrMethodologyDetailsSid)
		throws NoSuchAcBaseRateBaselineDetailsException {
		return remove((Serializable)acBrMethodologyDetailsSid);
	}

	/**
	 * Removes the ac base rate baseline details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details that was removed
	 * @throws NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails remove(Serializable primaryKey)
		throws NoSuchAcBaseRateBaselineDetailsException {
		Session session = null;

		try {
			session = openSession();

			AcBaseRateBaselineDetails acBaseRateBaselineDetails = (AcBaseRateBaselineDetails)session.get(AcBaseRateBaselineDetailsImpl.class,
					primaryKey);

			if (acBaseRateBaselineDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAcBaseRateBaselineDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(acBaseRateBaselineDetails);
		}
		catch (NoSuchAcBaseRateBaselineDetailsException nsee) {
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
	protected AcBaseRateBaselineDetails removeImpl(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		acBaseRateBaselineDetails = toUnwrappedModel(acBaseRateBaselineDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(acBaseRateBaselineDetails)) {
				acBaseRateBaselineDetails = (AcBaseRateBaselineDetails)session.get(AcBaseRateBaselineDetailsImpl.class,
						acBaseRateBaselineDetails.getPrimaryKeyObj());
			}

			if (acBaseRateBaselineDetails != null) {
				session.delete(acBaseRateBaselineDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (acBaseRateBaselineDetails != null) {
			clearCache(acBaseRateBaselineDetails);
		}

		return acBaseRateBaselineDetails;
	}

	@Override
	public AcBaseRateBaselineDetails updateImpl(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		acBaseRateBaselineDetails = toUnwrappedModel(acBaseRateBaselineDetails);

		boolean isNew = acBaseRateBaselineDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (acBaseRateBaselineDetails.isNew()) {
				session.save(acBaseRateBaselineDetails);

				acBaseRateBaselineDetails.setNew(false);
			}
			else {
				acBaseRateBaselineDetails = (AcBaseRateBaselineDetails)session.merge(acBaseRateBaselineDetails);
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

		entityCache.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBaseRateBaselineDetailsImpl.class,
			acBaseRateBaselineDetails.getPrimaryKey(),
			acBaseRateBaselineDetails, false);

		acBaseRateBaselineDetails.resetOriginalValues();

		return acBaseRateBaselineDetails;
	}

	protected AcBaseRateBaselineDetails toUnwrappedModel(
		AcBaseRateBaselineDetails acBaseRateBaselineDetails) {
		if (acBaseRateBaselineDetails instanceof AcBaseRateBaselineDetailsImpl) {
			return acBaseRateBaselineDetails;
		}

		AcBaseRateBaselineDetailsImpl acBaseRateBaselineDetailsImpl = new AcBaseRateBaselineDetailsImpl();

		acBaseRateBaselineDetailsImpl.setNew(acBaseRateBaselineDetails.isNew());
		acBaseRateBaselineDetailsImpl.setPrimaryKey(acBaseRateBaselineDetails.getPrimaryKey());

		acBaseRateBaselineDetailsImpl.setPeriodValue(acBaseRateBaselineDetails.getPeriodValue());
		acBaseRateBaselineDetailsImpl.setPeriodSid(acBaseRateBaselineDetails.getPeriodSid());
		acBaseRateBaselineDetailsImpl.setPaymentsPeriod(acBaseRateBaselineDetails.isPaymentsPeriod());
		acBaseRateBaselineDetailsImpl.setAcBrMethodologyDetailsSid(acBaseRateBaselineDetails.getAcBrMethodologyDetailsSid());
		acBaseRateBaselineDetailsImpl.setSalesPeriod(acBaseRateBaselineDetails.isSalesPeriod());

		return acBaseRateBaselineDetailsImpl;
	}

	/**
	 * Returns the ac base rate baseline details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details
	 * @throws NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAcBaseRateBaselineDetailsException {
		AcBaseRateBaselineDetails acBaseRateBaselineDetails = fetchByPrimaryKey(primaryKey);

		if (acBaseRateBaselineDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAcBaseRateBaselineDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return acBaseRateBaselineDetails;
	}

	/**
	 * Returns the ac base rate baseline details with the primary key or throws a {@link NoSuchAcBaseRateBaselineDetailsException} if it could not be found.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details
	 * @throws NoSuchAcBaseRateBaselineDetailsException if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails findByPrimaryKey(
		int acBrMethodologyDetailsSid)
		throws NoSuchAcBaseRateBaselineDetailsException {
		return findByPrimaryKey((Serializable)acBrMethodologyDetailsSid);
	}

	/**
	 * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AcBaseRateBaselineDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AcBaseRateBaselineDetails acBaseRateBaselineDetails = (AcBaseRateBaselineDetails)serializable;

		if (acBaseRateBaselineDetails == null) {
			Session session = null;

			try {
				session = openSession();

				acBaseRateBaselineDetails = (AcBaseRateBaselineDetails)session.get(AcBaseRateBaselineDetailsImpl.class,
						primaryKey);

				if (acBaseRateBaselineDetails != null) {
					cacheResult(acBaseRateBaselineDetails);
				}
				else {
					entityCache.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AcBaseRateBaselineDetailsImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBaseRateBaselineDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return acBaseRateBaselineDetails;
	}

	/**
	 * Returns the ac base rate baseline details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac base rate baseline details
	 * @return the ac base rate baseline details, or <code>null</code> if a ac base rate baseline details with the primary key could not be found
	 */
	@Override
	public AcBaseRateBaselineDetails fetchByPrimaryKey(
		int acBrMethodologyDetailsSid) {
		return fetchByPrimaryKey((Serializable)acBrMethodologyDetailsSid);
	}

	@Override
	public Map<Serializable, AcBaseRateBaselineDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AcBaseRateBaselineDetails> map = new HashMap<Serializable, AcBaseRateBaselineDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AcBaseRateBaselineDetails acBaseRateBaselineDetails = fetchByPrimaryKey(primaryKey);

			if (acBaseRateBaselineDetails != null) {
				map.put(primaryKey, acBaseRateBaselineDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBaseRateBaselineDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AcBaseRateBaselineDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACBASERATEBASELINEDETAILS_WHERE_PKS_IN);

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

			for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : (List<AcBaseRateBaselineDetails>)q.list()) {
				map.put(acBaseRateBaselineDetails.getPrimaryKeyObj(),
					acBaseRateBaselineDetails);

				cacheResult(acBaseRateBaselineDetails);

				uncachedPrimaryKeys.remove(acBaseRateBaselineDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AcBaseRateBaselineDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBaseRateBaselineDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ac base rate baseline detailses.
	 *
	 * @return the ac base rate baseline detailses
	 */
	@Override
	public List<AcBaseRateBaselineDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ac base rate baseline detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac base rate baseline detailses
	 * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
	 * @return the range of ac base rate baseline detailses
	 */
	@Override
	public List<AcBaseRateBaselineDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ac base rate baseline detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac base rate baseline detailses
	 * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ac base rate baseline detailses
	 */
	@Override
	public List<AcBaseRateBaselineDetails> findAll(int start, int end,
		OrderByComparator<AcBaseRateBaselineDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ac base rate baseline detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBaseRateBaselineDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac base rate baseline detailses
	 * @param end the upper bound of the range of ac base rate baseline detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ac base rate baseline detailses
	 */
	@Override
	public List<AcBaseRateBaselineDetails> findAll(int start, int end,
		OrderByComparator<AcBaseRateBaselineDetails> orderByComparator,
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

		List<AcBaseRateBaselineDetails> list = null;

		if (retrieveFromCache) {
			list = (List<AcBaseRateBaselineDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACBASERATEBASELINEDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACBASERATEBASELINEDETAILS;

				if (pagination) {
					sql = sql.concat(AcBaseRateBaselineDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AcBaseRateBaselineDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AcBaseRateBaselineDetails>)QueryUtil.list(q,
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
	 * Removes all the ac base rate baseline detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AcBaseRateBaselineDetails acBaseRateBaselineDetails : findAll()) {
			remove(acBaseRateBaselineDetails);
		}
	}

	/**
	 * Returns the number of ac base rate baseline detailses.
	 *
	 * @return the number of ac base rate baseline detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACBASERATEBASELINEDETAILS);

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
		return AcBaseRateBaselineDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ac base rate baseline details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AcBaseRateBaselineDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACBASERATEBASELINEDETAILS = "SELECT acBaseRateBaselineDetails FROM AcBaseRateBaselineDetails acBaseRateBaselineDetails";
	private static final String _SQL_SELECT_ACBASERATEBASELINEDETAILS_WHERE_PKS_IN =
		"SELECT acBaseRateBaselineDetails FROM AcBaseRateBaselineDetails acBaseRateBaselineDetails WHERE AC_BR_METHODOLOGY_DETAILS_SID IN (";
	private static final String _SQL_COUNT_ACBASERATEBASELINEDETAILS = "SELECT COUNT(acBaseRateBaselineDetails) FROM AcBaseRateBaselineDetails acBaseRateBaselineDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "acBaseRateBaselineDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcBaseRateBaselineDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AcBaseRateBaselineDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"periodValue", "periodSid", "paymentsPeriod",
				"acBrMethodologyDetailsSid", "salesPeriod"
			});
}