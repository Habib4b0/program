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

import com.stpl.app.exception.NoSuchRebateTierFormulaException;
import com.stpl.app.model.RebateTierFormula;
import com.stpl.app.model.impl.RebateTierFormulaImpl;
import com.stpl.app.model.impl.RebateTierFormulaModelImpl;
import com.stpl.app.service.persistence.RebateTierFormulaPersistence;

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
 * The persistence implementation for the rebate tier formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebateTierFormulaPersistence
 * @see com.stpl.app.service.persistence.RebateTierFormulaUtil
 * @generated
 */
@ProviderType
public class RebateTierFormulaPersistenceImpl extends BasePersistenceImpl<RebateTierFormula>
	implements RebateTierFormulaPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RebateTierFormulaUtil} to access the rebate tier formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RebateTierFormulaImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED,
			RebateTierFormulaImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED,
			RebateTierFormulaImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RebateTierFormulaPersistenceImpl() {
		setModelClass(RebateTierFormula.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("rebateTierFormulaNo", "REBATE_TIER_FORMULA_NO");
			dbColumnNames.put("rebateTierFormulaName",
				"REBATE_TIER_FORMULA_NAME");
			dbColumnNames.put("rebatePlanMasterSid", "REBATE_PLAN_MASTER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("rebateTierFormulaId", "REBATE_TIER_FORMULA_ID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("rebateTierFormulaSid", "REBATE_TIER_FORMULA_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rebate tier formula in the entity cache if it is enabled.
	 *
	 * @param rebateTierFormula the rebate tier formula
	 */
	@Override
	public void cacheResult(RebateTierFormula rebateTierFormula) {
		entityCache.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey(),
			rebateTierFormula);

		rebateTierFormula.resetOriginalValues();
	}

	/**
	 * Caches the rebate tier formulas in the entity cache if it is enabled.
	 *
	 * @param rebateTierFormulas the rebate tier formulas
	 */
	@Override
	public void cacheResult(List<RebateTierFormula> rebateTierFormulas) {
		for (RebateTierFormula rebateTierFormula : rebateTierFormulas) {
			if (entityCache.getResult(
						RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
						RebateTierFormulaImpl.class,
						rebateTierFormula.getPrimaryKey()) == null) {
				cacheResult(rebateTierFormula);
			}
			else {
				rebateTierFormula.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rebate tier formulas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RebateTierFormulaImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rebate tier formula.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RebateTierFormula rebateTierFormula) {
		entityCache.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RebateTierFormula> rebateTierFormulas) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RebateTierFormula rebateTierFormula : rebateTierFormulas) {
			entityCache.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
				RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
	 *
	 * @param rebateTierFormulaSid the primary key for the new rebate tier formula
	 * @return the new rebate tier formula
	 */
	@Override
	public RebateTierFormula create(int rebateTierFormulaSid) {
		RebateTierFormula rebateTierFormula = new RebateTierFormulaImpl();

		rebateTierFormula.setNew(true);
		rebateTierFormula.setPrimaryKey(rebateTierFormulaSid);

		return rebateTierFormula;
	}

	/**
	 * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rebateTierFormulaSid the primary key of the rebate tier formula
	 * @return the rebate tier formula that was removed
	 * @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula remove(int rebateTierFormulaSid)
		throws NoSuchRebateTierFormulaException {
		return remove((Serializable)rebateTierFormulaSid);
	}

	/**
	 * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rebate tier formula
	 * @return the rebate tier formula that was removed
	 * @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula remove(Serializable primaryKey)
		throws NoSuchRebateTierFormulaException {
		Session session = null;

		try {
			session = openSession();

			RebateTierFormula rebateTierFormula = (RebateTierFormula)session.get(RebateTierFormulaImpl.class,
					primaryKey);

			if (rebateTierFormula == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRebateTierFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rebateTierFormula);
		}
		catch (NoSuchRebateTierFormulaException nsee) {
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
	protected RebateTierFormula removeImpl(RebateTierFormula rebateTierFormula) {
		rebateTierFormula = toUnwrappedModel(rebateTierFormula);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rebateTierFormula)) {
				rebateTierFormula = (RebateTierFormula)session.get(RebateTierFormulaImpl.class,
						rebateTierFormula.getPrimaryKeyObj());
			}

			if (rebateTierFormula != null) {
				session.delete(rebateTierFormula);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rebateTierFormula != null) {
			clearCache(rebateTierFormula);
		}

		return rebateTierFormula;
	}

	@Override
	public RebateTierFormula updateImpl(RebateTierFormula rebateTierFormula) {
		rebateTierFormula = toUnwrappedModel(rebateTierFormula);

		boolean isNew = rebateTierFormula.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rebateTierFormula.isNew()) {
				session.save(rebateTierFormula);

				rebateTierFormula.setNew(false);
			}
			else {
				rebateTierFormula = (RebateTierFormula)session.merge(rebateTierFormula);
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

		entityCache.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
			RebateTierFormulaImpl.class, rebateTierFormula.getPrimaryKey(),
			rebateTierFormula, false);

		rebateTierFormula.resetOriginalValues();

		return rebateTierFormula;
	}

	protected RebateTierFormula toUnwrappedModel(
		RebateTierFormula rebateTierFormula) {
		if (rebateTierFormula instanceof RebateTierFormulaImpl) {
			return rebateTierFormula;
		}

		RebateTierFormulaImpl rebateTierFormulaImpl = new RebateTierFormulaImpl();

		rebateTierFormulaImpl.setNew(rebateTierFormula.isNew());
		rebateTierFormulaImpl.setPrimaryKey(rebateTierFormula.getPrimaryKey());

		rebateTierFormulaImpl.setRebateTierFormulaNo(rebateTierFormula.getRebateTierFormulaNo());
		rebateTierFormulaImpl.setRebateTierFormulaName(rebateTierFormula.getRebateTierFormulaName());
		rebateTierFormulaImpl.setRebatePlanMasterSid(rebateTierFormula.getRebatePlanMasterSid());
		rebateTierFormulaImpl.setModifiedDate(rebateTierFormula.getModifiedDate());
		rebateTierFormulaImpl.setRecordLockStatus(rebateTierFormula.isRecordLockStatus());
		rebateTierFormulaImpl.setSource(rebateTierFormula.getSource());
		rebateTierFormulaImpl.setCreatedBy(rebateTierFormula.getCreatedBy());
		rebateTierFormulaImpl.setCreatedDate(rebateTierFormula.getCreatedDate());
		rebateTierFormulaImpl.setBatchId(rebateTierFormula.getBatchId());
		rebateTierFormulaImpl.setRebateTierFormulaId(rebateTierFormula.getRebateTierFormulaId());
		rebateTierFormulaImpl.setInboundStatus(rebateTierFormula.getInboundStatus());
		rebateTierFormulaImpl.setModifiedBy(rebateTierFormula.getModifiedBy());
		rebateTierFormulaImpl.setRebateTierFormulaSid(rebateTierFormula.getRebateTierFormulaSid());

		return rebateTierFormulaImpl;
	}

	/**
	 * Returns the rebate tier formula with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rebate tier formula
	 * @return the rebate tier formula
	 * @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRebateTierFormulaException {
		RebateTierFormula rebateTierFormula = fetchByPrimaryKey(primaryKey);

		if (rebateTierFormula == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRebateTierFormulaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rebateTierFormula;
	}

	/**
	 * Returns the rebate tier formula with the primary key or throws a {@link NoSuchRebateTierFormulaException} if it could not be found.
	 *
	 * @param rebateTierFormulaSid the primary key of the rebate tier formula
	 * @return the rebate tier formula
	 * @throws NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula findByPrimaryKey(int rebateTierFormulaSid)
		throws NoSuchRebateTierFormulaException {
		return findByPrimaryKey((Serializable)rebateTierFormulaSid);
	}

	/**
	 * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rebate tier formula
	 * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
				RebateTierFormulaImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RebateTierFormula rebateTierFormula = (RebateTierFormula)serializable;

		if (rebateTierFormula == null) {
			Session session = null;

			try {
				session = openSession();

				rebateTierFormula = (RebateTierFormula)session.get(RebateTierFormulaImpl.class,
						primaryKey);

				if (rebateTierFormula != null) {
					cacheResult(rebateTierFormula);
				}
				else {
					entityCache.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
						RebateTierFormulaImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
					RebateTierFormulaImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rebateTierFormula;
	}

	/**
	 * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rebateTierFormulaSid the primary key of the rebate tier formula
	 * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
	 */
	@Override
	public RebateTierFormula fetchByPrimaryKey(int rebateTierFormulaSid) {
		return fetchByPrimaryKey((Serializable)rebateTierFormulaSid);
	}

	@Override
	public Map<Serializable, RebateTierFormula> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RebateTierFormula> map = new HashMap<Serializable, RebateTierFormula>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RebateTierFormula rebateTierFormula = fetchByPrimaryKey(primaryKey);

			if (rebateTierFormula != null) {
				map.put(primaryKey, rebateTierFormula);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
					RebateTierFormulaImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RebateTierFormula)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REBATETIERFORMULA_WHERE_PKS_IN);

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

			for (RebateTierFormula rebateTierFormula : (List<RebateTierFormula>)q.list()) {
				map.put(rebateTierFormula.getPrimaryKeyObj(), rebateTierFormula);

				cacheResult(rebateTierFormula);

				uncachedPrimaryKeys.remove(rebateTierFormula.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RebateTierFormulaModelImpl.ENTITY_CACHE_ENABLED,
					RebateTierFormulaImpl.class, primaryKey, nullModel);
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
	 * Returns all the rebate tier formulas.
	 *
	 * @return the rebate tier formulas
	 */
	@Override
	public List<RebateTierFormula> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rebate tier formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate tier formulas
	 * @param end the upper bound of the range of rebate tier formulas (not inclusive)
	 * @return the range of rebate tier formulas
	 */
	@Override
	public List<RebateTierFormula> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rebate tier formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate tier formulas
	 * @param end the upper bound of the range of rebate tier formulas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rebate tier formulas
	 */
	@Override
	public List<RebateTierFormula> findAll(int start, int end,
		OrderByComparator<RebateTierFormula> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rebate tier formulas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate tier formulas
	 * @param end the upper bound of the range of rebate tier formulas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rebate tier formulas
	 */
	@Override
	public List<RebateTierFormula> findAll(int start, int end,
		OrderByComparator<RebateTierFormula> orderByComparator,
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

		List<RebateTierFormula> list = null;

		if (retrieveFromCache) {
			list = (List<RebateTierFormula>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REBATETIERFORMULA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REBATETIERFORMULA;

				if (pagination) {
					sql = sql.concat(RebateTierFormulaModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RebateTierFormula>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RebateTierFormula>)QueryUtil.list(q,
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
	 * Removes all the rebate tier formulas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RebateTierFormula rebateTierFormula : findAll()) {
			remove(rebateTierFormula);
		}
	}

	/**
	 * Returns the number of rebate tier formulas.
	 *
	 * @return the number of rebate tier formulas
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REBATETIERFORMULA);

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
		return RebateTierFormulaModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rebate tier formula persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RebateTierFormulaImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REBATETIERFORMULA = "SELECT rebateTierFormula FROM RebateTierFormula rebateTierFormula";
	private static final String _SQL_SELECT_REBATETIERFORMULA_WHERE_PKS_IN = "SELECT rebateTierFormula FROM RebateTierFormula rebateTierFormula WHERE REBATE_TIER_FORMULA_SID IN (";
	private static final String _SQL_COUNT_REBATETIERFORMULA = "SELECT COUNT(rebateTierFormula) FROM RebateTierFormula rebateTierFormula";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rebateTierFormula.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RebateTierFormula exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RebateTierFormulaPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"rebateTierFormulaNo", "rebateTierFormulaName",
				"rebatePlanMasterSid", "modifiedDate", "recordLockStatus",
				"source", "createdBy", "createdDate", "batchId",
				"rebateTierFormulaId", "inboundStatus", "modifiedBy",
				"rebateTierFormulaSid"
			});
}