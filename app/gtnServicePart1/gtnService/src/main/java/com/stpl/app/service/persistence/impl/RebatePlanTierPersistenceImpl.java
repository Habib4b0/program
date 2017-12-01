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

import com.stpl.app.exception.NoSuchRebatePlanTierException;
import com.stpl.app.model.RebatePlanTier;
import com.stpl.app.model.impl.RebatePlanTierImpl;
import com.stpl.app.model.impl.RebatePlanTierModelImpl;
import com.stpl.app.service.persistence.RebatePlanTierPersistence;

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
 * The persistence implementation for the rebate plan tier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebatePlanTierPersistence
 * @see com.stpl.app.service.persistence.RebatePlanTierUtil
 * @generated
 */
@ProviderType
public class RebatePlanTierPersistenceImpl extends BasePersistenceImpl<RebatePlanTier>
	implements RebatePlanTierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RebatePlanTierUtil} to access the rebate plan tier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RebatePlanTierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierModelImpl.FINDER_CACHE_ENABLED,
			RebatePlanTierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierModelImpl.FINDER_CACHE_ENABLED,
			RebatePlanTierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RebatePlanTierPersistenceImpl() {
		setModelClass(RebatePlanTier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("tierValue", "TIER_VALUE");
			dbColumnNames.put("returnRateSid", "RETURN_RATE_SID");
			dbColumnNames.put("rebatePlanMasterSid", "REBATE_PLAN_MASTER_SID");
			dbColumnNames.put("rebatePlanTierSid", "REBATE_PLAN_TIER_SID");
			dbColumnNames.put("itemPricingQualifierSid",
				"ITEM_PRICING_QUALIFIER_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("tierTolerance", "TIER_TOLERANCE");
			dbColumnNames.put("tierFrom", "TIER_FROM");
			dbColumnNames.put("tierOperator", "TIER_OPERATOR");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("tierTo", "TIER_TO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("rebatePlanTierId", "REBATE_PLAN_TIER_ID");
			dbColumnNames.put("freeAmount", "FREE_AMOUNT");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("tierLevel", "TIER_LEVEL");
			dbColumnNames.put("formulaNo", "FORMULA_NO");
			dbColumnNames.put("formulaName", "FORMULA_NAME");
			dbColumnNames.put("secondaryRebatePlanNo",
				"SECONDARY_REBATE_PLAN_NO");
			dbColumnNames.put("secondaryRebatePlanName",
				"SECONDARY_REBATE_PLAN_NAME");
			dbColumnNames.put("secondaryRebatePlanSid",
				"SECONDARY_REBATE_PLAN_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rebate plan tier in the entity cache if it is enabled.
	 *
	 * @param rebatePlanTier the rebate plan tier
	 */
	@Override
	public void cacheResult(RebatePlanTier rebatePlanTier) {
		entityCache.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey(),
			rebatePlanTier);

		rebatePlanTier.resetOriginalValues();
	}

	/**
	 * Caches the rebate plan tiers in the entity cache if it is enabled.
	 *
	 * @param rebatePlanTiers the rebate plan tiers
	 */
	@Override
	public void cacheResult(List<RebatePlanTier> rebatePlanTiers) {
		for (RebatePlanTier rebatePlanTier : rebatePlanTiers) {
			if (entityCache.getResult(
						RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
						RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey()) == null) {
				cacheResult(rebatePlanTier);
			}
			else {
				rebatePlanTier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rebate plan tiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RebatePlanTierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rebate plan tier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RebatePlanTier rebatePlanTier) {
		entityCache.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RebatePlanTier> rebatePlanTiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RebatePlanTier rebatePlanTier : rebatePlanTiers) {
			entityCache.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
				RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rebate plan tier with the primary key. Does not add the rebate plan tier to the database.
	 *
	 * @param rebatePlanTierSid the primary key for the new rebate plan tier
	 * @return the new rebate plan tier
	 */
	@Override
	public RebatePlanTier create(int rebatePlanTierSid) {
		RebatePlanTier rebatePlanTier = new RebatePlanTierImpl();

		rebatePlanTier.setNew(true);
		rebatePlanTier.setPrimaryKey(rebatePlanTierSid);

		return rebatePlanTier;
	}

	/**
	 * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rebatePlanTierSid the primary key of the rebate plan tier
	 * @return the rebate plan tier that was removed
	 * @throws NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier remove(int rebatePlanTierSid)
		throws NoSuchRebatePlanTierException {
		return remove((Serializable)rebatePlanTierSid);
	}

	/**
	 * Removes the rebate plan tier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rebate plan tier
	 * @return the rebate plan tier that was removed
	 * @throws NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier remove(Serializable primaryKey)
		throws NoSuchRebatePlanTierException {
		Session session = null;

		try {
			session = openSession();

			RebatePlanTier rebatePlanTier = (RebatePlanTier)session.get(RebatePlanTierImpl.class,
					primaryKey);

			if (rebatePlanTier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRebatePlanTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rebatePlanTier);
		}
		catch (NoSuchRebatePlanTierException nsee) {
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
	protected RebatePlanTier removeImpl(RebatePlanTier rebatePlanTier) {
		rebatePlanTier = toUnwrappedModel(rebatePlanTier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rebatePlanTier)) {
				rebatePlanTier = (RebatePlanTier)session.get(RebatePlanTierImpl.class,
						rebatePlanTier.getPrimaryKeyObj());
			}

			if (rebatePlanTier != null) {
				session.delete(rebatePlanTier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rebatePlanTier != null) {
			clearCache(rebatePlanTier);
		}

		return rebatePlanTier;
	}

	@Override
	public RebatePlanTier updateImpl(RebatePlanTier rebatePlanTier) {
		rebatePlanTier = toUnwrappedModel(rebatePlanTier);

		boolean isNew = rebatePlanTier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rebatePlanTier.isNew()) {
				session.save(rebatePlanTier);

				rebatePlanTier.setNew(false);
			}
			else {
				rebatePlanTier = (RebatePlanTier)session.merge(rebatePlanTier);
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

		entityCache.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
			RebatePlanTierImpl.class, rebatePlanTier.getPrimaryKey(),
			rebatePlanTier, false);

		rebatePlanTier.resetOriginalValues();

		return rebatePlanTier;
	}

	protected RebatePlanTier toUnwrappedModel(RebatePlanTier rebatePlanTier) {
		if (rebatePlanTier instanceof RebatePlanTierImpl) {
			return rebatePlanTier;
		}

		RebatePlanTierImpl rebatePlanTierImpl = new RebatePlanTierImpl();

		rebatePlanTierImpl.setNew(rebatePlanTier.isNew());
		rebatePlanTierImpl.setPrimaryKey(rebatePlanTier.getPrimaryKey());

		rebatePlanTierImpl.setTierValue(rebatePlanTier.getTierValue());
		rebatePlanTierImpl.setReturnRateSid(rebatePlanTier.getReturnRateSid());
		rebatePlanTierImpl.setRebatePlanMasterSid(rebatePlanTier.getRebatePlanMasterSid());
		rebatePlanTierImpl.setRebatePlanTierSid(rebatePlanTier.getRebatePlanTierSid());
		rebatePlanTierImpl.setItemPricingQualifierSid(rebatePlanTier.getItemPricingQualifierSid());
		rebatePlanTierImpl.setModifiedDate(rebatePlanTier.getModifiedDate());
		rebatePlanTierImpl.setTierTolerance(rebatePlanTier.getTierTolerance());
		rebatePlanTierImpl.setTierFrom(rebatePlanTier.getTierFrom());
		rebatePlanTierImpl.setTierOperator(rebatePlanTier.getTierOperator());
		rebatePlanTierImpl.setRecordLockStatus(rebatePlanTier.isRecordLockStatus());
		rebatePlanTierImpl.setCreatedDate(rebatePlanTier.getCreatedDate());
		rebatePlanTierImpl.setCreatedBy(rebatePlanTier.getCreatedBy());
		rebatePlanTierImpl.setSource(rebatePlanTier.getSource());
		rebatePlanTierImpl.setTierTo(rebatePlanTier.getTierTo());
		rebatePlanTierImpl.setBatchId(rebatePlanTier.getBatchId());
		rebatePlanTierImpl.setRebatePlanTierId(rebatePlanTier.getRebatePlanTierId());
		rebatePlanTierImpl.setFreeAmount(rebatePlanTier.getFreeAmount());
		rebatePlanTierImpl.setModifiedBy(rebatePlanTier.getModifiedBy());
		rebatePlanTierImpl.setInboundStatus(rebatePlanTier.getInboundStatus());
		rebatePlanTierImpl.setTierLevel(rebatePlanTier.getTierLevel());
		rebatePlanTierImpl.setFormulaNo(rebatePlanTier.getFormulaNo());
		rebatePlanTierImpl.setFormulaName(rebatePlanTier.getFormulaName());
		rebatePlanTierImpl.setSecondaryRebatePlanNo(rebatePlanTier.getSecondaryRebatePlanNo());
		rebatePlanTierImpl.setSecondaryRebatePlanName(rebatePlanTier.getSecondaryRebatePlanName());
		rebatePlanTierImpl.setSecondaryRebatePlanSid(rebatePlanTier.getSecondaryRebatePlanSid());

		return rebatePlanTierImpl;
	}

	/**
	 * Returns the rebate plan tier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rebate plan tier
	 * @return the rebate plan tier
	 * @throws NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRebatePlanTierException {
		RebatePlanTier rebatePlanTier = fetchByPrimaryKey(primaryKey);

		if (rebatePlanTier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRebatePlanTierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rebatePlanTier;
	}

	/**
	 * Returns the rebate plan tier with the primary key or throws a {@link NoSuchRebatePlanTierException} if it could not be found.
	 *
	 * @param rebatePlanTierSid the primary key of the rebate plan tier
	 * @return the rebate plan tier
	 * @throws NoSuchRebatePlanTierException if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier findByPrimaryKey(int rebatePlanTierSid)
		throws NoSuchRebatePlanTierException {
		return findByPrimaryKey((Serializable)rebatePlanTierSid);
	}

	/**
	 * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rebate plan tier
	 * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
				RebatePlanTierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RebatePlanTier rebatePlanTier = (RebatePlanTier)serializable;

		if (rebatePlanTier == null) {
			Session session = null;

			try {
				session = openSession();

				rebatePlanTier = (RebatePlanTier)session.get(RebatePlanTierImpl.class,
						primaryKey);

				if (rebatePlanTier != null) {
					cacheResult(rebatePlanTier);
				}
				else {
					entityCache.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
						RebatePlanTierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
					RebatePlanTierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rebatePlanTier;
	}

	/**
	 * Returns the rebate plan tier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rebatePlanTierSid the primary key of the rebate plan tier
	 * @return the rebate plan tier, or <code>null</code> if a rebate plan tier with the primary key could not be found
	 */
	@Override
	public RebatePlanTier fetchByPrimaryKey(int rebatePlanTierSid) {
		return fetchByPrimaryKey((Serializable)rebatePlanTierSid);
	}

	@Override
	public Map<Serializable, RebatePlanTier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RebatePlanTier> map = new HashMap<Serializable, RebatePlanTier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RebatePlanTier rebatePlanTier = fetchByPrimaryKey(primaryKey);

			if (rebatePlanTier != null) {
				map.put(primaryKey, rebatePlanTier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
					RebatePlanTierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RebatePlanTier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_REBATEPLANTIER_WHERE_PKS_IN);

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

			for (RebatePlanTier rebatePlanTier : (List<RebatePlanTier>)q.list()) {
				map.put(rebatePlanTier.getPrimaryKeyObj(), rebatePlanTier);

				cacheResult(rebatePlanTier);

				uncachedPrimaryKeys.remove(rebatePlanTier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RebatePlanTierModelImpl.ENTITY_CACHE_ENABLED,
					RebatePlanTierImpl.class, primaryKey, nullModel);
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
	 * Returns all the rebate plan tiers.
	 *
	 * @return the rebate plan tiers
	 */
	@Override
	public List<RebatePlanTier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rebate plan tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate plan tiers
	 * @param end the upper bound of the range of rebate plan tiers (not inclusive)
	 * @return the range of rebate plan tiers
	 */
	@Override
	public List<RebatePlanTier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rebate plan tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate plan tiers
	 * @param end the upper bound of the range of rebate plan tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rebate plan tiers
	 */
	@Override
	public List<RebatePlanTier> findAll(int start, int end,
		OrderByComparator<RebatePlanTier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rebate plan tiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RebatePlanTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rebate plan tiers
	 * @param end the upper bound of the range of rebate plan tiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rebate plan tiers
	 */
	@Override
	public List<RebatePlanTier> findAll(int start, int end,
		OrderByComparator<RebatePlanTier> orderByComparator,
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

		List<RebatePlanTier> list = null;

		if (retrieveFromCache) {
			list = (List<RebatePlanTier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_REBATEPLANTIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_REBATEPLANTIER;

				if (pagination) {
					sql = sql.concat(RebatePlanTierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RebatePlanTier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RebatePlanTier>)QueryUtil.list(q,
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
	 * Removes all the rebate plan tiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RebatePlanTier rebatePlanTier : findAll()) {
			remove(rebatePlanTier);
		}
	}

	/**
	 * Returns the number of rebate plan tiers.
	 *
	 * @return the number of rebate plan tiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_REBATEPLANTIER);

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
		return RebatePlanTierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rebate plan tier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RebatePlanTierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_REBATEPLANTIER = "SELECT rebatePlanTier FROM RebatePlanTier rebatePlanTier";
	private static final String _SQL_SELECT_REBATEPLANTIER_WHERE_PKS_IN = "SELECT rebatePlanTier FROM RebatePlanTier rebatePlanTier WHERE REBATE_PLAN_TIER_SID IN (";
	private static final String _SQL_COUNT_REBATEPLANTIER = "SELECT COUNT(rebatePlanTier) FROM RebatePlanTier rebatePlanTier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rebatePlanTier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RebatePlanTier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RebatePlanTierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"tierValue", "returnRateSid", "rebatePlanMasterSid",
				"rebatePlanTierSid", "itemPricingQualifierSid", "modifiedDate",
				"tierTolerance", "tierFrom", "tierOperator", "recordLockStatus",
				"createdDate", "createdBy", "source", "tierTo", "batchId",
				"rebatePlanTierId", "freeAmount", "modifiedBy", "inboundStatus",
				"tierLevel", "formulaNo", "formulaName", "secondaryRebatePlanNo",
				"secondaryRebatePlanName", "secondaryRebatePlanSid"
			});
}