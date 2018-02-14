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

import com.stpl.app.parttwo.exception.NoSuchArpOutboundException;
import com.stpl.app.parttwo.model.ArpOutbound;
import com.stpl.app.parttwo.model.impl.ArpOutboundImpl;
import com.stpl.app.parttwo.model.impl.ArpOutboundModelImpl;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPK;
import com.stpl.app.parttwo.service.persistence.ArpOutboundPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the arp outbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ArpOutboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.ArpOutboundUtil
 * @generated
 */
@ProviderType
public class ArpOutboundPersistenceImpl extends BasePersistenceImpl<ArpOutbound>
	implements ArpOutboundPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ArpOutboundUtil} to access the arp outbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ArpOutboundImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundModelImpl.FINDER_CACHE_ENABLED, ArpOutboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundModelImpl.FINDER_CACHE_ENABLED, ArpOutboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ArpOutboundPersistenceImpl() {
		setModelClass(ArpOutbound.class);

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
			dbColumnNames.put("arpMasterSid", "ARP_MASTER_SID");
			dbColumnNames.put("arpCreationDate", "ARP_CREATION_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("arpId", "ARP_ID");
			dbColumnNames.put("account", "ACCOUNT");
			dbColumnNames.put("outboundStatus", "OUTBOUND_STATUS");
			dbColumnNames.put("periodSid", "PERIOD_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the arp outbound in the entity cache if it is enabled.
	 *
	 * @param arpOutbound the arp outbound
	 */
	@Override
	public void cacheResult(ArpOutbound arpOutbound) {
		entityCache.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundImpl.class, arpOutbound.getPrimaryKey(), arpOutbound);

		arpOutbound.resetOriginalValues();
	}

	/**
	 * Caches the arp outbounds in the entity cache if it is enabled.
	 *
	 * @param arpOutbounds the arp outbounds
	 */
	@Override
	public void cacheResult(List<ArpOutbound> arpOutbounds) {
		for (ArpOutbound arpOutbound : arpOutbounds) {
			if (entityCache.getResult(
						ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
						ArpOutboundImpl.class, arpOutbound.getPrimaryKey()) == null) {
				cacheResult(arpOutbound);
			}
			else {
				arpOutbound.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all arp outbounds.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ArpOutboundImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the arp outbound.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ArpOutbound arpOutbound) {
		entityCache.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundImpl.class, arpOutbound.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ArpOutbound> arpOutbounds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ArpOutbound arpOutbound : arpOutbounds) {
			entityCache.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
				ArpOutboundImpl.class, arpOutbound.getPrimaryKey());
		}
	}

	/**
	 * Creates a new arp outbound with the primary key. Does not add the arp outbound to the database.
	 *
	 * @param arpOutboundPK the primary key for the new arp outbound
	 * @return the new arp outbound
	 */
	@Override
	public ArpOutbound create(ArpOutboundPK arpOutboundPK) {
		ArpOutbound arpOutbound = new ArpOutboundImpl();

		arpOutbound.setNew(true);
		arpOutbound.setPrimaryKey(arpOutboundPK);

		return arpOutbound;
	}

	/**
	 * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param arpOutboundPK the primary key of the arp outbound
	 * @return the arp outbound that was removed
	 * @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound remove(ArpOutboundPK arpOutboundPK)
		throws NoSuchArpOutboundException {
		return remove((Serializable)arpOutboundPK);
	}

	/**
	 * Removes the arp outbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the arp outbound
	 * @return the arp outbound that was removed
	 * @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound remove(Serializable primaryKey)
		throws NoSuchArpOutboundException {
		Session session = null;

		try {
			session = openSession();

			ArpOutbound arpOutbound = (ArpOutbound)session.get(ArpOutboundImpl.class,
					primaryKey);

			if (arpOutbound == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(arpOutbound);
		}
		catch (NoSuchArpOutboundException nsee) {
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
	protected ArpOutbound removeImpl(ArpOutbound arpOutbound) {
		arpOutbound = toUnwrappedModel(arpOutbound);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(arpOutbound)) {
				arpOutbound = (ArpOutbound)session.get(ArpOutboundImpl.class,
						arpOutbound.getPrimaryKeyObj());
			}

			if (arpOutbound != null) {
				session.delete(arpOutbound);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (arpOutbound != null) {
			clearCache(arpOutbound);
		}

		return arpOutbound;
	}

	@Override
	public ArpOutbound updateImpl(ArpOutbound arpOutbound) {
		arpOutbound = toUnwrappedModel(arpOutbound);

		boolean isNew = arpOutbound.isNew();

		Session session = null;

		try {
			session = openSession();

			if (arpOutbound.isNew()) {
				session.save(arpOutbound);

				arpOutbound.setNew(false);
			}
			else {
				arpOutbound = (ArpOutbound)session.merge(arpOutbound);
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

		entityCache.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
			ArpOutboundImpl.class, arpOutbound.getPrimaryKey(), arpOutbound,
			false);

		arpOutbound.resetOriginalValues();

		return arpOutbound;
	}

	protected ArpOutbound toUnwrappedModel(ArpOutbound arpOutbound) {
		if (arpOutbound instanceof ArpOutboundImpl) {
			return arpOutbound;
		}

		ArpOutboundImpl arpOutboundImpl = new ArpOutboundImpl();

		arpOutboundImpl.setNew(arpOutbound.isNew());
		arpOutboundImpl.setPrimaryKey(arpOutbound.getPrimaryKey());

		arpOutboundImpl.setSalesUnitsRate(arpOutbound.getSalesUnitsRate());
		arpOutboundImpl.setAccountType(arpOutbound.getAccountType());
		arpOutboundImpl.setOriginalBatchId(arpOutbound.getOriginalBatchId());
		arpOutboundImpl.setCompanyMasterSid(arpOutbound.getCompanyMasterSid());
		arpOutboundImpl.setBrandMasterSid(arpOutbound.getBrandMasterSid());
		arpOutboundImpl.setArpApprovalDate(arpOutbound.getArpApprovalDate());
		arpOutboundImpl.setArpMasterSid(arpOutbound.getArpMasterSid());
		arpOutboundImpl.setArpCreationDate(arpOutbound.getArpCreationDate());
		arpOutboundImpl.setCheckRecord(arpOutbound.isCheckRecord());
		arpOutboundImpl.setArpId(arpOutbound.getArpId());
		arpOutboundImpl.setAccount(arpOutbound.getAccount());
		arpOutboundImpl.setOutboundStatus(arpOutbound.isOutboundStatus());
		arpOutboundImpl.setPeriodSid(arpOutbound.getPeriodSid());
		arpOutboundImpl.setItemMasterSid(arpOutbound.getItemMasterSid());
		arpOutboundImpl.setRsModelSid(arpOutbound.getRsModelSid());

		return arpOutboundImpl;
	}

	/**
	 * Returns the arp outbound with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the arp outbound
	 * @return the arp outbound
	 * @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound findByPrimaryKey(Serializable primaryKey)
		throws NoSuchArpOutboundException {
		ArpOutbound arpOutbound = fetchByPrimaryKey(primaryKey);

		if (arpOutbound == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchArpOutboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return arpOutbound;
	}

	/**
	 * Returns the arp outbound with the primary key or throws a {@link NoSuchArpOutboundException} if it could not be found.
	 *
	 * @param arpOutboundPK the primary key of the arp outbound
	 * @return the arp outbound
	 * @throws NoSuchArpOutboundException if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound findByPrimaryKey(ArpOutboundPK arpOutboundPK)
		throws NoSuchArpOutboundException {
		return findByPrimaryKey((Serializable)arpOutboundPK);
	}

	/**
	 * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the arp outbound
	 * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
				ArpOutboundImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ArpOutbound arpOutbound = (ArpOutbound)serializable;

		if (arpOutbound == null) {
			Session session = null;

			try {
				session = openSession();

				arpOutbound = (ArpOutbound)session.get(ArpOutboundImpl.class,
						primaryKey);

				if (arpOutbound != null) {
					cacheResult(arpOutbound);
				}
				else {
					entityCache.putResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
						ArpOutboundImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ArpOutboundModelImpl.ENTITY_CACHE_ENABLED,
					ArpOutboundImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return arpOutbound;
	}

	/**
	 * Returns the arp outbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param arpOutboundPK the primary key of the arp outbound
	 * @return the arp outbound, or <code>null</code> if a arp outbound with the primary key could not be found
	 */
	@Override
	public ArpOutbound fetchByPrimaryKey(ArpOutboundPK arpOutboundPK) {
		return fetchByPrimaryKey((Serializable)arpOutboundPK);
	}

	@Override
	public Map<Serializable, ArpOutbound> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ArpOutbound> map = new HashMap<Serializable, ArpOutbound>();

		for (Serializable primaryKey : primaryKeys) {
			ArpOutbound arpOutbound = fetchByPrimaryKey(primaryKey);

			if (arpOutbound != null) {
				map.put(primaryKey, arpOutbound);
			}
		}

		return map;
	}

	/**
	 * Returns all the arp outbounds.
	 *
	 * @return the arp outbounds
	 */
	@Override
	public List<ArpOutbound> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of arp outbounds
	 * @param end the upper bound of the range of arp outbounds (not inclusive)
	 * @return the range of arp outbounds
	 */
	@Override
	public List<ArpOutbound> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of arp outbounds
	 * @param end the upper bound of the range of arp outbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of arp outbounds
	 */
	@Override
	public List<ArpOutbound> findAll(int start, int end,
		OrderByComparator<ArpOutbound> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the arp outbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ArpOutboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of arp outbounds
	 * @param end the upper bound of the range of arp outbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of arp outbounds
	 */
	@Override
	public List<ArpOutbound> findAll(int start, int end,
		OrderByComparator<ArpOutbound> orderByComparator,
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

		List<ArpOutbound> list = null;

		if (retrieveFromCache) {
			list = (List<ArpOutbound>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ARPOUTBOUND);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ARPOUTBOUND;

				if (pagination) {
					sql = sql.concat(ArpOutboundModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ArpOutbound>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ArpOutbound>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the arp outbounds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ArpOutbound arpOutbound : findAll()) {
			remove(arpOutbound);
		}
	}

	/**
	 * Returns the number of arp outbounds.
	 *
	 * @return the number of arp outbounds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ARPOUTBOUND);

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
		return ArpOutboundModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the arp outbound persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ArpOutboundImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ARPOUTBOUND = "SELECT arpOutbound FROM ArpOutbound arpOutbound";
	private static final String _SQL_COUNT_ARPOUTBOUND = "SELECT COUNT(arpOutbound) FROM ArpOutbound arpOutbound";
	private static final String _ORDER_BY_ENTITY_ALIAS = "arpOutbound.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ArpOutbound exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ArpOutboundPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"salesUnitsRate", "accountType", "originalBatchId",
				"companyMasterSid", "brandMasterSid", "arpApprovalDate",
				"arpMasterSid", "arpCreationDate", "checkRecord", "arpId",
				"account", "outboundStatus", "periodSid", "itemMasterSid",
				"rsModelSid"
			});
}