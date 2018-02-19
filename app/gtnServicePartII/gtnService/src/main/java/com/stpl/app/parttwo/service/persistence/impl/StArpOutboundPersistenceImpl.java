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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchStArpOutboundException;
import com.stpl.app.parttwo.model.StArpOutbound;
import com.stpl.app.parttwo.model.impl.StArpOutboundImpl;
import com.stpl.app.parttwo.model.impl.StArpOutboundModelImpl;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPK;
import com.stpl.app.parttwo.service.persistence.StArpOutboundPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the st arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StArpOutboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.StArpOutboundUtil
 * @generated
 */
@ProviderType
public class StArpOutboundPersistenceImpl extends BasePersistenceImpl<StArpOutbound>
	implements StArpOutboundPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StArpOutboundUtil} to access the st arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StArpOutboundImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundModelImpl.FINDER_CACHE_ENABLED,
			StArpOutboundImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundModelImpl.FINDER_CACHE_ENABLED,
			StArpOutboundImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StArpOutboundPersistenceImpl() {
		setModelClass(StArpOutbound.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("salesUnitsRate", "SALES_UNITS_RATE");
			dbColumnNames.put("accountType", "ACCOUNT_TYPE");
			dbColumnNames.put("originalBatchId", "ORIGINAL_BATCH_ID");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("arpApprovalDate", "ARP_APPROVAL_DATE");
			dbColumnNames.put("userId", "USER_ID");
			dbColumnNames.put("arpMasterSid", "ARP_MASTER_SID");
			dbColumnNames.put("arpCreationDate", "ARP_CREATION_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("arpId", "ARP_ID");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("outboundStatus", "OUTBOUND_STATUS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("sessionId", "SESSION_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the st arp outbound in the entity cache if it is enabled.
	 *
	 * @param stArpOutbound the st arp outbound
	 */
	@Override
	public void cacheResult(StArpOutbound stArpOutbound) {
		entityCache.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundImpl.class, stArpOutbound.getPrimaryKey(),
			stArpOutbound);

		stArpOutbound.resetOriginalValues();
	}

	/**
	 * Caches the st arp outbounds in the entity cache if it is enabled.
	 *
	 * @param stArpOutbounds the st arp outbounds
	 */
	@Override
	public void cacheResult(List<StArpOutbound> stArpOutbounds) {
		for (StArpOutbound stArpOutbound : stArpOutbounds) {
			if (entityCache.getResult(
						StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
						StArpOutboundImpl.class, stArpOutbound.getPrimaryKey()) == null) {
				cacheResult(stArpOutbound);
			}
			else {
				stArpOutbound.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all st arp outbounds.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StArpOutboundImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the st arp outbound.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StArpOutbound stArpOutbound) {
		entityCache.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundImpl.class, stArpOutbound.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StArpOutbound> stArpOutbounds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StArpOutbound stArpOutbound : stArpOutbounds) {
			entityCache.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
				StArpOutboundImpl.class, stArpOutbound.getPrimaryKey());
		}
	}

	/**
	 * Creates a new st arp outbound with the primary key. Does not add the st arp outbound to the database.
	 *
	 * @param stArpOutboundPK the primary key for the new st arp outbound
	 * @return the new st arp outbound
	 */
	@Override
	public StArpOutbound create(StArpOutboundPK stArpOutboundPK) {
		StArpOutbound stArpOutbound = new StArpOutboundImpl();

		stArpOutbound.setNew(true);
		stArpOutbound.setPrimaryKey(stArpOutboundPK);

		return stArpOutbound;
	}

	/**
	 * Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param stArpOutboundPK the primary key of the st arp outbound
	 * @return the st arp outbound that was removed
	 * @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound remove(StArpOutboundPK stArpOutboundPK)
		throws NoSuchStArpOutboundException {
		return remove((Serializable)stArpOutboundPK);
	}

	/**
	 * Removes the st arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the st arp outbound
	 * @return the st arp outbound that was removed
	 * @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound remove(Serializable primaryKey)
		throws NoSuchStArpOutboundException {
		Session session = null;

		try {
			session = openSession();

			StArpOutbound stArpOutbound = (StArpOutbound)session.get(StArpOutboundImpl.class,
					primaryKey);

			if (stArpOutbound == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(stArpOutbound);
		}
		catch (NoSuchStArpOutboundException nsee) {
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
	protected StArpOutbound removeImpl(StArpOutbound stArpOutbound) {
		stArpOutbound = toUnwrappedModel(stArpOutbound);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stArpOutbound)) {
				stArpOutbound = (StArpOutbound)session.get(StArpOutboundImpl.class,
						stArpOutbound.getPrimaryKeyObj());
			}

			if (stArpOutbound != null) {
				session.delete(stArpOutbound);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (stArpOutbound != null) {
			clearCache(stArpOutbound);
		}

		return stArpOutbound;
	}

	@Override
	public StArpOutbound updateImpl(StArpOutbound stArpOutbound) {
		stArpOutbound = toUnwrappedModel(stArpOutbound);

		boolean isNew = stArpOutbound.isNew();

		Session session = null;

		try {
			session = openSession();

			if (stArpOutbound.isNew()) {
				session.save(stArpOutbound);

				stArpOutbound.setNew(false);
			}
			else {
				stArpOutbound = (StArpOutbound)session.merge(stArpOutbound);
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

		entityCache.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			StArpOutboundImpl.class, stArpOutbound.getPrimaryKey(),
			stArpOutbound, false);

		stArpOutbound.resetOriginalValues();

		return stArpOutbound;
	}

	protected StArpOutbound toUnwrappedModel(StArpOutbound stArpOutbound) {
		if (stArpOutbound instanceof StArpOutboundImpl) {
			return stArpOutbound;
		}

		StArpOutboundImpl stArpOutboundImpl = new StArpOutboundImpl();

		stArpOutboundImpl.setNew(stArpOutbound.isNew());
		stArpOutboundImpl.setPrimaryKey(stArpOutbound.getPrimaryKey());

		stArpOutboundImpl.setSalesUnitsRate(stArpOutbound.getSalesUnitsRate());
		stArpOutboundImpl.setAccountType(stArpOutbound.getAccountType());
		stArpOutboundImpl.setOriginalBatchId(stArpOutbound.getOriginalBatchId());
		stArpOutboundImpl.setCompanyMasterSid(stArpOutbound.getCompanyMasterSid());
		stArpOutboundImpl.setBrandMasterSid(stArpOutbound.getBrandMasterSid());
		stArpOutboundImpl.setArpApprovalDate(stArpOutbound.getArpApprovalDate());
		stArpOutboundImpl.setUserId(stArpOutbound.getUserId());
		stArpOutboundImpl.setArpMasterSid(stArpOutbound.getArpMasterSid());
		stArpOutboundImpl.setArpCreationDate(stArpOutbound.getArpCreationDate());
		stArpOutboundImpl.setCheckRecord(stArpOutbound.isCheckRecord());
		stArpOutboundImpl.setArpId(stArpOutbound.getArpId());
		stArpOutboundImpl.setAccount(stArpOutbound.getAccount());
		stArpOutboundImpl.setOutboundStatus(stArpOutbound.isOutboundStatus());
		stArpOutboundImpl.setPeriodSid(stArpOutbound.getPeriodSid());
		stArpOutboundImpl.setItemMasterSid(stArpOutbound.getItemMasterSid());
		stArpOutboundImpl.setRsModelSid(stArpOutbound.getRsModelSid());
		stArpOutboundImpl.setSessionId(stArpOutbound.getSessionId());

		return stArpOutboundImpl;
	}

	/**
	 * Returns the st arp outbound with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the st arp outbound
	 * @return the st arp outbound
	 * @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStArpOutboundException {
		StArpOutbound stArpOutbound = fetchByPrimaryKey(primaryKey);

		if (stArpOutbound == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return stArpOutbound;
	}

	/**
	 * Returns the st arp outbound with the primary key or throws a {@link NoSuchStArpOutboundException} if it could not be found.
	 *
	 * @param stArpOutboundPK the primary key of the st arp outbound
	 * @return the st arp outbound
	 * @throws NoSuchStArpOutboundException if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound findByPrimaryKey(StArpOutboundPK stArpOutboundPK)
		throws NoSuchStArpOutboundException {
		return findByPrimaryKey((Serializable)stArpOutboundPK);
	}

	/**
	 * Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the st arp outbound
	 * @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
				StArpOutboundImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StArpOutbound stArpOutbound = (StArpOutbound)serializable;

		if (stArpOutbound == null) {
			Session session = null;

			try {
				session = openSession();

				stArpOutbound = (StArpOutbound)session.get(StArpOutboundImpl.class,
						primaryKey);

				if (stArpOutbound != null) {
					cacheResult(stArpOutbound);
				}
				else {
					entityCache.putResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
						StArpOutboundImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
					StArpOutboundImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return stArpOutbound;
	}

	/**
	 * Returns the st arp outbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param stArpOutboundPK the primary key of the st arp outbound
	 * @return the st arp outbound, or <code>null</code> if a st arp outbound with the primary key could not be found
	 */
	@Override
	public StArpOutbound fetchByPrimaryKey(StArpOutboundPK stArpOutboundPK) {
		return fetchByPrimaryKey((Serializable)stArpOutboundPK);
	}

	@Override
	public Map<Serializable, StArpOutbound> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StArpOutbound> map = new HashMap<Serializable, StArpOutbound>();

		for (Serializable primaryKey : primaryKeys) {
			StArpOutbound stArpOutbound = fetchByPrimaryKey(primaryKey);

			if (stArpOutbound != null) {
				map.put(primaryKey, stArpOutbound);
			}
		}

		return map;
	}

	/**
	 * Returns all the st arp outbounds.
	 *
	 * @return the st arp outbounds
	 */
	@Override
	public List<StArpOutbound> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the st arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st arp outbounds
	 * @param end the upper bound of the range of st arp outbounds (not inclusive)
	 * @return the range of st arp outbounds
	 */
	@Override
	public List<StArpOutbound> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the st arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st arp outbounds
	 * @param end the upper bound of the range of st arp outbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of st arp outbounds
	 */
	@Override
	public List<StArpOutbound> findAll(int start, int end,
		OrderByComparator<StArpOutbound> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the st arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of st arp outbounds
	 * @param end the upper bound of the range of st arp outbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of st arp outbounds
	 */
	@Override
	public List<StArpOutbound> findAll(int start, int end,
		OrderByComparator<StArpOutbound> orderByComparator,
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

		List<StArpOutbound> list = null;

		if (retrieveFromCache) {
			list = (List<StArpOutbound>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STARPOUTBOUND);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STARPOUTBOUND;

				if (pagination) {
					sql = sql.concat(StArpOutboundModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StArpOutbound>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StArpOutbound>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the st arp outbounds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StArpOutbound stArpOutbound : findAll()) {
			remove(stArpOutbound);
		}
	}

	/**
	 * Returns the number of st arp outbounds.
	 *
	 * @return the number of st arp outbounds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STARPOUTBOUND);

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
		return StArpOutboundModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the st arp outbound persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StArpOutboundImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STARPOUTBOUND = "SELECT stArpOutbound FROM StArpOutbound stArpOutbound";
	private static final String _SQL_COUNT_STARPOUTBOUND = "SELECT COUNT(stArpOutbound) FROM StArpOutbound stArpOutbound";
	private static final String _ORDER_BY_ENTITY_ALIAS = "stArpOutbound.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StArpOutbound exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StArpOutboundPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"salesUnitsRate", "accountType", "originalBatchId",
				"companyMasterSid", "brandMasterSid", "arpApprovalDate",
				"userId", "arpMasterSid", "arpCreationDate", "checkRecord",
				"arpId", "account", "outboundStatus", "periodSid",
				"itemMasterSid", "rsModelSid", "sessionId"
			});
}