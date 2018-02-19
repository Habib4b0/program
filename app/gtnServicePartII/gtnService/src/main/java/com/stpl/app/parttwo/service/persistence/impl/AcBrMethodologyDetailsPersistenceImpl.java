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

import com.stpl.app.parttwo.exception.NoSuchAcBrMethodologyDetailsException;
import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsImpl;
import com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl;
import com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsPersistence;

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
 * The persistence implementation for the ac br methodology details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsPersistence
 * @see com.stpl.app.parttwo.service.persistence.AcBrMethodologyDetailsUtil
 * @generated
 */
@ProviderType
public class AcBrMethodologyDetailsPersistenceImpl extends BasePersistenceImpl<AcBrMethodologyDetails>
	implements AcBrMethodologyDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AcBrMethodologyDetailsUtil} to access the ac br methodology details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AcBrMethodologyDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED,
			AcBrMethodologyDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED,
			AcBrMethodologyDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AcBrMethodologyDetailsPersistenceImpl() {
		setModelClass(AcBrMethodologyDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("salesGrowthRate", "SALES_GROWTH_RATE");
			dbColumnNames.put("methodologyStartDate", "METHODOLOGY_START_DATE");
			dbColumnNames.put("frequency", "FREQUENCY");
			dbColumnNames.put("calculationFlag", "CALCULATION_FLAG");
			dbColumnNames.put("provisionGrowthRate", "PROVISION_GROWTH_RATE");
			dbColumnNames.put("salesBasis", "SALES_BASIS");
			dbColumnNames.put("acBrMethodologyDetailsSid",
				"AC_BR_METHODOLOGY_DETAILS_SID");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("methodologyEndDate", "METHODOLOGY_END_DATE");
			dbColumnNames.put("methodologyValue", "METHODOLOGY_VALUE");
			dbColumnNames.put("dampingFactor", "DAMPING_FACTOR");
			dbColumnNames.put("methodologyName", "METHODOLOGY_NAME");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ac br methodology details in the entity cache if it is enabled.
	 *
	 * @param acBrMethodologyDetails the ac br methodology details
	 */
	@Override
	public void cacheResult(AcBrMethodologyDetails acBrMethodologyDetails) {
		entityCache.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsImpl.class,
			acBrMethodologyDetails.getPrimaryKey(), acBrMethodologyDetails);

		acBrMethodologyDetails.resetOriginalValues();
	}

	/**
	 * Caches the ac br methodology detailses in the entity cache if it is enabled.
	 *
	 * @param acBrMethodologyDetailses the ac br methodology detailses
	 */
	@Override
	public void cacheResult(
		List<AcBrMethodologyDetails> acBrMethodologyDetailses) {
		for (AcBrMethodologyDetails acBrMethodologyDetails : acBrMethodologyDetailses) {
			if (entityCache.getResult(
						AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AcBrMethodologyDetailsImpl.class,
						acBrMethodologyDetails.getPrimaryKey()) == null) {
				cacheResult(acBrMethodologyDetails);
			}
			else {
				acBrMethodologyDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ac br methodology detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AcBrMethodologyDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ac br methodology details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AcBrMethodologyDetails acBrMethodologyDetails) {
		entityCache.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsImpl.class,
			acBrMethodologyDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<AcBrMethodologyDetails> acBrMethodologyDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AcBrMethodologyDetails acBrMethodologyDetails : acBrMethodologyDetailses) {
			entityCache.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AcBrMethodologyDetailsImpl.class,
				acBrMethodologyDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
	 *
	 * @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
	 * @return the new ac br methodology details
	 */
	@Override
	public AcBrMethodologyDetails create(int acBrMethodologyDetailsSid) {
		AcBrMethodologyDetails acBrMethodologyDetails = new AcBrMethodologyDetailsImpl();

		acBrMethodologyDetails.setNew(true);
		acBrMethodologyDetails.setPrimaryKey(acBrMethodologyDetailsSid);

		return acBrMethodologyDetails;
	}

	/**
	 * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	 * @return the ac br methodology details that was removed
	 * @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails remove(int acBrMethodologyDetailsSid)
		throws NoSuchAcBrMethodologyDetailsException {
		return remove((Serializable)acBrMethodologyDetailsSid);
	}

	/**
	 * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ac br methodology details
	 * @return the ac br methodology details that was removed
	 * @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails remove(Serializable primaryKey)
		throws NoSuchAcBrMethodologyDetailsException {
		Session session = null;

		try {
			session = openSession();

			AcBrMethodologyDetails acBrMethodologyDetails = (AcBrMethodologyDetails)session.get(AcBrMethodologyDetailsImpl.class,
					primaryKey);

			if (acBrMethodologyDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAcBrMethodologyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(acBrMethodologyDetails);
		}
		catch (NoSuchAcBrMethodologyDetailsException nsee) {
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
	protected AcBrMethodologyDetails removeImpl(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		acBrMethodologyDetails = toUnwrappedModel(acBrMethodologyDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(acBrMethodologyDetails)) {
				acBrMethodologyDetails = (AcBrMethodologyDetails)session.get(AcBrMethodologyDetailsImpl.class,
						acBrMethodologyDetails.getPrimaryKeyObj());
			}

			if (acBrMethodologyDetails != null) {
				session.delete(acBrMethodologyDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (acBrMethodologyDetails != null) {
			clearCache(acBrMethodologyDetails);
		}

		return acBrMethodologyDetails;
	}

	@Override
	public AcBrMethodologyDetails updateImpl(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		acBrMethodologyDetails = toUnwrappedModel(acBrMethodologyDetails);

		boolean isNew = acBrMethodologyDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (acBrMethodologyDetails.isNew()) {
				session.save(acBrMethodologyDetails);

				acBrMethodologyDetails.setNew(false);
			}
			else {
				acBrMethodologyDetails = (AcBrMethodologyDetails)session.merge(acBrMethodologyDetails);
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

		entityCache.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AcBrMethodologyDetailsImpl.class,
			acBrMethodologyDetails.getPrimaryKey(), acBrMethodologyDetails,
			false);

		acBrMethodologyDetails.resetOriginalValues();

		return acBrMethodologyDetails;
	}

	protected AcBrMethodologyDetails toUnwrappedModel(
		AcBrMethodologyDetails acBrMethodologyDetails) {
		if (acBrMethodologyDetails instanceof AcBrMethodologyDetailsImpl) {
			return acBrMethodologyDetails;
		}

		AcBrMethodologyDetailsImpl acBrMethodologyDetailsImpl = new AcBrMethodologyDetailsImpl();

		acBrMethodologyDetailsImpl.setNew(acBrMethodologyDetails.isNew());
		acBrMethodologyDetailsImpl.setPrimaryKey(acBrMethodologyDetails.getPrimaryKey());

		acBrMethodologyDetailsImpl.setSalesGrowthRate(acBrMethodologyDetails.getSalesGrowthRate());
		acBrMethodologyDetailsImpl.setMethodologyStartDate(acBrMethodologyDetails.getMethodologyStartDate());
		acBrMethodologyDetailsImpl.setFrequency(acBrMethodologyDetails.getFrequency());
		acBrMethodologyDetailsImpl.setCalculationFlag(acBrMethodologyDetails.isCalculationFlag());
		acBrMethodologyDetailsImpl.setProvisionGrowthRate(acBrMethodologyDetails.getProvisionGrowthRate());
		acBrMethodologyDetailsImpl.setSalesBasis(acBrMethodologyDetails.getSalesBasis());
		acBrMethodologyDetailsImpl.setAcBrMethodologyDetailsSid(acBrMethodologyDetails.getAcBrMethodologyDetailsSid());
		acBrMethodologyDetailsImpl.setAccClosureMasterSid(acBrMethodologyDetails.getAccClosureMasterSid());
		acBrMethodologyDetailsImpl.setMethodologyEndDate(acBrMethodologyDetails.getMethodologyEndDate());
		acBrMethodologyDetailsImpl.setMethodologyValue(acBrMethodologyDetails.getMethodologyValue());
		acBrMethodologyDetailsImpl.setDampingFactor(acBrMethodologyDetails.getDampingFactor());
		acBrMethodologyDetailsImpl.setMethodologyName(acBrMethodologyDetails.getMethodologyName());

		return acBrMethodologyDetailsImpl;
	}

	/**
	 * Returns the ac br methodology details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac br methodology details
	 * @return the ac br methodology details
	 * @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAcBrMethodologyDetailsException {
		AcBrMethodologyDetails acBrMethodologyDetails = fetchByPrimaryKey(primaryKey);

		if (acBrMethodologyDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAcBrMethodologyDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return acBrMethodologyDetails;
	}

	/**
	 * Returns the ac br methodology details with the primary key or throws a {@link NoSuchAcBrMethodologyDetailsException} if it could not be found.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	 * @return the ac br methodology details
	 * @throws NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails findByPrimaryKey(
		int acBrMethodologyDetailsSid)
		throws NoSuchAcBrMethodologyDetailsException {
		return findByPrimaryKey((Serializable)acBrMethodologyDetailsSid);
	}

	/**
	 * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac br methodology details
	 * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AcBrMethodologyDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AcBrMethodologyDetails acBrMethodologyDetails = (AcBrMethodologyDetails)serializable;

		if (acBrMethodologyDetails == null) {
			Session session = null;

			try {
				session = openSession();

				acBrMethodologyDetails = (AcBrMethodologyDetails)session.get(AcBrMethodologyDetailsImpl.class,
						primaryKey);

				if (acBrMethodologyDetails != null) {
					cacheResult(acBrMethodologyDetails);
				}
				else {
					entityCache.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AcBrMethodologyDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBrMethodologyDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return acBrMethodologyDetails;
	}

	/**
	 * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
	 * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
	 */
	@Override
	public AcBrMethodologyDetails fetchByPrimaryKey(
		int acBrMethodologyDetailsSid) {
		return fetchByPrimaryKey((Serializable)acBrMethodologyDetailsSid);
	}

	@Override
	public Map<Serializable, AcBrMethodologyDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AcBrMethodologyDetails> map = new HashMap<Serializable, AcBrMethodologyDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AcBrMethodologyDetails acBrMethodologyDetails = fetchByPrimaryKey(primaryKey);

			if (acBrMethodologyDetails != null) {
				map.put(primaryKey, acBrMethodologyDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBrMethodologyDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AcBrMethodologyDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACBRMETHODOLOGYDETAILS_WHERE_PKS_IN);

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

			for (AcBrMethodologyDetails acBrMethodologyDetails : (List<AcBrMethodologyDetails>)q.list()) {
				map.put(acBrMethodologyDetails.getPrimaryKeyObj(),
					acBrMethodologyDetails);

				cacheResult(acBrMethodologyDetails);

				uncachedPrimaryKeys.remove(acBrMethodologyDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AcBrMethodologyDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AcBrMethodologyDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ac br methodology detailses.
	 *
	 * @return the ac br methodology detailses
	 */
	@Override
	public List<AcBrMethodologyDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ac br methodology detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac br methodology detailses
	 * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	 * @return the range of ac br methodology detailses
	 */
	@Override
	public List<AcBrMethodologyDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ac br methodology detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac br methodology detailses
	 * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ac br methodology detailses
	 */
	@Override
	public List<AcBrMethodologyDetails> findAll(int start, int end,
		OrderByComparator<AcBrMethodologyDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ac br methodology detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac br methodology detailses
	 * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ac br methodology detailses
	 */
	@Override
	public List<AcBrMethodologyDetails> findAll(int start, int end,
		OrderByComparator<AcBrMethodologyDetails> orderByComparator,
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

		List<AcBrMethodologyDetails> list = null;

		if (retrieveFromCache) {
			list = (List<AcBrMethodologyDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACBRMETHODOLOGYDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACBRMETHODOLOGYDETAILS;

				if (pagination) {
					sql = sql.concat(AcBrMethodologyDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AcBrMethodologyDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AcBrMethodologyDetails>)QueryUtil.list(q,
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
	 * Removes all the ac br methodology detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AcBrMethodologyDetails acBrMethodologyDetails : findAll()) {
			remove(acBrMethodologyDetails);
		}
	}

	/**
	 * Returns the number of ac br methodology detailses.
	 *
	 * @return the number of ac br methodology detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACBRMETHODOLOGYDETAILS);

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
		return AcBrMethodologyDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ac br methodology details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AcBrMethodologyDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACBRMETHODOLOGYDETAILS = "SELECT acBrMethodologyDetails FROM AcBrMethodologyDetails acBrMethodologyDetails";
	private static final String _SQL_SELECT_ACBRMETHODOLOGYDETAILS_WHERE_PKS_IN = "SELECT acBrMethodologyDetails FROM AcBrMethodologyDetails acBrMethodologyDetails WHERE AC_BR_METHODOLOGY_DETAILS_SID IN (";
	private static final String _SQL_COUNT_ACBRMETHODOLOGYDETAILS = "SELECT COUNT(acBrMethodologyDetails) FROM AcBrMethodologyDetails acBrMethodologyDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "acBrMethodologyDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcBrMethodologyDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AcBrMethodologyDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"salesGrowthRate", "methodologyStartDate", "frequency",
				"calculationFlag", "provisionGrowthRate", "salesBasis",
				"acBrMethodologyDetailsSid", "accClosureMasterSid",
				"methodologyEndDate", "methodologyValue", "dampingFactor",
				"methodologyName"
			});
}