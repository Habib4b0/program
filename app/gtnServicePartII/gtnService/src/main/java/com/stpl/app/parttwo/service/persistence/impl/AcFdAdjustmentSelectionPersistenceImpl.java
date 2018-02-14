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

import com.stpl.app.parttwo.exception.NoSuchAcFdAdjustmentSelectionException;
import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;
import com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionImpl;
import com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl;
import com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionPersistence;

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
 * The persistence implementation for the ac fd adjustment selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelectionPersistence
 * @see com.stpl.app.parttwo.service.persistence.AcFdAdjustmentSelectionUtil
 * @generated
 */
@ProviderType
public class AcFdAdjustmentSelectionPersistenceImpl extends BasePersistenceImpl<AcFdAdjustmentSelection>
	implements AcFdAdjustmentSelectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AcFdAdjustmentSelectionUtil} to access the ac fd adjustment selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AcFdAdjustmentSelectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED,
			AcFdAdjustmentSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED,
			AcFdAdjustmentSelectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AcFdAdjustmentSelectionPersistenceImpl() {
		setModelClass(AcFdAdjustmentSelection.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("methodologyStartDate", "METHODOLOGY_START_DATE");
			dbColumnNames.put("allocationMethod", "ALLOCATION_METHOD");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("totalFixedDollarAdj", "TOTAL_FIXED_DOLLAR_ADJ");
			dbColumnNames.put("calculationFlag", "CALCULATION_FLAG");
			dbColumnNames.put("rateCorrection", "RATE_CORRECTION");
			dbColumnNames.put("businessDays", "BUSINESS_DAYS");
			dbColumnNames.put("glImpactDate", "GL_IMPACT_DATE");
			dbColumnNames.put("salesBasis", "SALES_BASIS");
			dbColumnNames.put("releaseType", "RELEASE_TYPE");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("releaseAmount", "RELEASE_AMOUNT");
			dbColumnNames.put("suggestedAdj", "SUGGESTED_ADJ");
			dbColumnNames.put("methodologyEndDate", "METHODOLOGY_END_DATE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ac fd adjustment selection in the entity cache if it is enabled.
	 *
	 * @param acFdAdjustmentSelection the ac fd adjustment selection
	 */
	@Override
	public void cacheResult(AcFdAdjustmentSelection acFdAdjustmentSelection) {
		entityCache.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionImpl.class,
			acFdAdjustmentSelection.getPrimaryKey(), acFdAdjustmentSelection);

		acFdAdjustmentSelection.resetOriginalValues();
	}

	/**
	 * Caches the ac fd adjustment selections in the entity cache if it is enabled.
	 *
	 * @param acFdAdjustmentSelections the ac fd adjustment selections
	 */
	@Override
	public void cacheResult(
		List<AcFdAdjustmentSelection> acFdAdjustmentSelections) {
		for (AcFdAdjustmentSelection acFdAdjustmentSelection : acFdAdjustmentSelections) {
			if (entityCache.getResult(
						AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
						AcFdAdjustmentSelectionImpl.class,
						acFdAdjustmentSelection.getPrimaryKey()) == null) {
				cacheResult(acFdAdjustmentSelection);
			}
			else {
				acFdAdjustmentSelection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ac fd adjustment selections.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AcFdAdjustmentSelectionImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ac fd adjustment selection.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AcFdAdjustmentSelection acFdAdjustmentSelection) {
		entityCache.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionImpl.class,
			acFdAdjustmentSelection.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<AcFdAdjustmentSelection> acFdAdjustmentSelections) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AcFdAdjustmentSelection acFdAdjustmentSelection : acFdAdjustmentSelections) {
			entityCache.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
				AcFdAdjustmentSelectionImpl.class,
				acFdAdjustmentSelection.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
	 *
	 * @param accClosureMasterSid the primary key for the new ac fd adjustment selection
	 * @return the new ac fd adjustment selection
	 */
	@Override
	public AcFdAdjustmentSelection create(int accClosureMasterSid) {
		AcFdAdjustmentSelection acFdAdjustmentSelection = new AcFdAdjustmentSelectionImpl();

		acFdAdjustmentSelection.setNew(true);
		acFdAdjustmentSelection.setPrimaryKey(accClosureMasterSid);

		return acFdAdjustmentSelection;
	}

	/**
	 * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accClosureMasterSid the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection that was removed
	 * @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection remove(int accClosureMasterSid)
		throws NoSuchAcFdAdjustmentSelectionException {
		return remove((Serializable)accClosureMasterSid);
	}

	/**
	 * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection that was removed
	 * @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection remove(Serializable primaryKey)
		throws NoSuchAcFdAdjustmentSelectionException {
		Session session = null;

		try {
			session = openSession();

			AcFdAdjustmentSelection acFdAdjustmentSelection = (AcFdAdjustmentSelection)session.get(AcFdAdjustmentSelectionImpl.class,
					primaryKey);

			if (acFdAdjustmentSelection == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAcFdAdjustmentSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(acFdAdjustmentSelection);
		}
		catch (NoSuchAcFdAdjustmentSelectionException nsee) {
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
	protected AcFdAdjustmentSelection removeImpl(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		acFdAdjustmentSelection = toUnwrappedModel(acFdAdjustmentSelection);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(acFdAdjustmentSelection)) {
				acFdAdjustmentSelection = (AcFdAdjustmentSelection)session.get(AcFdAdjustmentSelectionImpl.class,
						acFdAdjustmentSelection.getPrimaryKeyObj());
			}

			if (acFdAdjustmentSelection != null) {
				session.delete(acFdAdjustmentSelection);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (acFdAdjustmentSelection != null) {
			clearCache(acFdAdjustmentSelection);
		}

		return acFdAdjustmentSelection;
	}

	@Override
	public AcFdAdjustmentSelection updateImpl(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		acFdAdjustmentSelection = toUnwrappedModel(acFdAdjustmentSelection);

		boolean isNew = acFdAdjustmentSelection.isNew();

		Session session = null;

		try {
			session = openSession();

			if (acFdAdjustmentSelection.isNew()) {
				session.save(acFdAdjustmentSelection);

				acFdAdjustmentSelection.setNew(false);
			}
			else {
				acFdAdjustmentSelection = (AcFdAdjustmentSelection)session.merge(acFdAdjustmentSelection);
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

		entityCache.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
			AcFdAdjustmentSelectionImpl.class,
			acFdAdjustmentSelection.getPrimaryKey(), acFdAdjustmentSelection,
			false);

		acFdAdjustmentSelection.resetOriginalValues();

		return acFdAdjustmentSelection;
	}

	protected AcFdAdjustmentSelection toUnwrappedModel(
		AcFdAdjustmentSelection acFdAdjustmentSelection) {
		if (acFdAdjustmentSelection instanceof AcFdAdjustmentSelectionImpl) {
			return acFdAdjustmentSelection;
		}

		AcFdAdjustmentSelectionImpl acFdAdjustmentSelectionImpl = new AcFdAdjustmentSelectionImpl();

		acFdAdjustmentSelectionImpl.setNew(acFdAdjustmentSelection.isNew());
		acFdAdjustmentSelectionImpl.setPrimaryKey(acFdAdjustmentSelection.getPrimaryKey());

		acFdAdjustmentSelectionImpl.setMethodologyStartDate(acFdAdjustmentSelection.getMethodologyStartDate());
		acFdAdjustmentSelectionImpl.setAllocationMethod(acFdAdjustmentSelection.getAllocationMethod());
		acFdAdjustmentSelectionImpl.setStartDate(acFdAdjustmentSelection.getStartDate());
		acFdAdjustmentSelectionImpl.setTotalFixedDollarAdj(acFdAdjustmentSelection.getTotalFixedDollarAdj());
		acFdAdjustmentSelectionImpl.setCalculationFlag(acFdAdjustmentSelection.isCalculationFlag());
		acFdAdjustmentSelectionImpl.setRateCorrection(acFdAdjustmentSelection.getRateCorrection());
		acFdAdjustmentSelectionImpl.setBusinessDays(acFdAdjustmentSelection.getBusinessDays());
		acFdAdjustmentSelectionImpl.setGlImpactDate(acFdAdjustmentSelection.getGlImpactDate());
		acFdAdjustmentSelectionImpl.setSalesBasis(acFdAdjustmentSelection.getSalesBasis());
		acFdAdjustmentSelectionImpl.setReleaseType(acFdAdjustmentSelection.isReleaseType());
		acFdAdjustmentSelectionImpl.setAccClosureMasterSid(acFdAdjustmentSelection.getAccClosureMasterSid());
		acFdAdjustmentSelectionImpl.setReleaseAmount(acFdAdjustmentSelection.getReleaseAmount());
		acFdAdjustmentSelectionImpl.setSuggestedAdj(acFdAdjustmentSelection.getSuggestedAdj());
		acFdAdjustmentSelectionImpl.setMethodologyEndDate(acFdAdjustmentSelection.getMethodologyEndDate());

		return acFdAdjustmentSelectionImpl;
	}

	/**
	 * Returns the ac fd adjustment selection with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection
	 * @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAcFdAdjustmentSelectionException {
		AcFdAdjustmentSelection acFdAdjustmentSelection = fetchByPrimaryKey(primaryKey);

		if (acFdAdjustmentSelection == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAcFdAdjustmentSelectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return acFdAdjustmentSelection;
	}

	/**
	 * Returns the ac fd adjustment selection with the primary key or throws a {@link NoSuchAcFdAdjustmentSelectionException} if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection
	 * @throws NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection findByPrimaryKey(int accClosureMasterSid)
		throws NoSuchAcFdAdjustmentSelectionException {
		return findByPrimaryKey((Serializable)accClosureMasterSid);
	}

	/**
	 * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
				AcFdAdjustmentSelectionImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AcFdAdjustmentSelection acFdAdjustmentSelection = (AcFdAdjustmentSelection)serializable;

		if (acFdAdjustmentSelection == null) {
			Session session = null;

			try {
				session = openSession();

				acFdAdjustmentSelection = (AcFdAdjustmentSelection)session.get(AcFdAdjustmentSelectionImpl.class,
						primaryKey);

				if (acFdAdjustmentSelection != null) {
					cacheResult(acFdAdjustmentSelection);
				}
				else {
					entityCache.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
						AcFdAdjustmentSelectionImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
					AcFdAdjustmentSelectionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return acFdAdjustmentSelection;
	}

	/**
	 * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the ac fd adjustment selection
	 * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
	 */
	@Override
	public AcFdAdjustmentSelection fetchByPrimaryKey(int accClosureMasterSid) {
		return fetchByPrimaryKey((Serializable)accClosureMasterSid);
	}

	@Override
	public Map<Serializable, AcFdAdjustmentSelection> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AcFdAdjustmentSelection> map = new HashMap<Serializable, AcFdAdjustmentSelection>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AcFdAdjustmentSelection acFdAdjustmentSelection = fetchByPrimaryKey(primaryKey);

			if (acFdAdjustmentSelection != null) {
				map.put(primaryKey, acFdAdjustmentSelection);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
					AcFdAdjustmentSelectionImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AcFdAdjustmentSelection)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACFDADJUSTMENTSELECTION_WHERE_PKS_IN);

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

			for (AcFdAdjustmentSelection acFdAdjustmentSelection : (List<AcFdAdjustmentSelection>)q.list()) {
				map.put(acFdAdjustmentSelection.getPrimaryKeyObj(),
					acFdAdjustmentSelection);

				cacheResult(acFdAdjustmentSelection);

				uncachedPrimaryKeys.remove(acFdAdjustmentSelection.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AcFdAdjustmentSelectionModelImpl.ENTITY_CACHE_ENABLED,
					AcFdAdjustmentSelectionImpl.class, primaryKey, nullModel);
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
	 * Returns all the ac fd adjustment selections.
	 *
	 * @return the ac fd adjustment selections
	 */
	@Override
	public List<AcFdAdjustmentSelection> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ac fd adjustment selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac fd adjustment selections
	 * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	 * @return the range of ac fd adjustment selections
	 */
	@Override
	public List<AcFdAdjustmentSelection> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ac fd adjustment selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac fd adjustment selections
	 * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ac fd adjustment selections
	 */
	@Override
	public List<AcFdAdjustmentSelection> findAll(int start, int end,
		OrderByComparator<AcFdAdjustmentSelection> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ac fd adjustment selections.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ac fd adjustment selections
	 * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ac fd adjustment selections
	 */
	@Override
	public List<AcFdAdjustmentSelection> findAll(int start, int end,
		OrderByComparator<AcFdAdjustmentSelection> orderByComparator,
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

		List<AcFdAdjustmentSelection> list = null;

		if (retrieveFromCache) {
			list = (List<AcFdAdjustmentSelection>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACFDADJUSTMENTSELECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACFDADJUSTMENTSELECTION;

				if (pagination) {
					sql = sql.concat(AcFdAdjustmentSelectionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AcFdAdjustmentSelection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AcFdAdjustmentSelection>)QueryUtil.list(q,
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
	 * Removes all the ac fd adjustment selections from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AcFdAdjustmentSelection acFdAdjustmentSelection : findAll()) {
			remove(acFdAdjustmentSelection);
		}
	}

	/**
	 * Returns the number of ac fd adjustment selections.
	 *
	 * @return the number of ac fd adjustment selections
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACFDADJUSTMENTSELECTION);

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
		return AcFdAdjustmentSelectionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ac fd adjustment selection persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AcFdAdjustmentSelectionImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACFDADJUSTMENTSELECTION = "SELECT acFdAdjustmentSelection FROM AcFdAdjustmentSelection acFdAdjustmentSelection";
	private static final String _SQL_SELECT_ACFDADJUSTMENTSELECTION_WHERE_PKS_IN =
		"SELECT acFdAdjustmentSelection FROM AcFdAdjustmentSelection acFdAdjustmentSelection WHERE ACC_CLOSURE_MASTER_SID IN (";
	private static final String _SQL_COUNT_ACFDADJUSTMENTSELECTION = "SELECT COUNT(acFdAdjustmentSelection) FROM AcFdAdjustmentSelection acFdAdjustmentSelection";
	private static final String _ORDER_BY_ENTITY_ALIAS = "acFdAdjustmentSelection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AcFdAdjustmentSelection exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AcFdAdjustmentSelectionPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"methodologyStartDate", "allocationMethod", "startDate",
				"totalFixedDollarAdj", "calculationFlag", "rateCorrection",
				"businessDays", "glImpactDate", "salesBasis", "releaseType",
				"accClosureMasterSid", "releaseAmount", "suggestedAdj",
				"methodologyEndDate"
			});
}