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

import com.stpl.app.exception.NoSuchNetSalesFormulaMasterException;
import com.stpl.app.model.NetSalesFormulaMaster;
import com.stpl.app.model.impl.NetSalesFormulaMasterImpl;
import com.stpl.app.model.impl.NetSalesFormulaMasterModelImpl;
import com.stpl.app.service.persistence.NetSalesFormulaMasterPersistence;

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
 * The persistence implementation for the net sales formula master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NetSalesFormulaMasterPersistence
 * @see com.stpl.app.service.persistence.NetSalesFormulaMasterUtil
 * @generated
 */
@ProviderType
public class NetSalesFormulaMasterPersistenceImpl extends BasePersistenceImpl<NetSalesFormulaMaster>
	implements NetSalesFormulaMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link NetSalesFormulaMasterUtil} to access the net sales formula master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = NetSalesFormulaMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED,
			NetSalesFormulaMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED,
			NetSalesFormulaMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public NetSalesFormulaMasterPersistenceImpl() {
		setModelClass(NetSalesFormulaMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("netSalesFormulaName", "NET_SALES_FORMULA_NAME");
			dbColumnNames.put("netSalesFormulaType", "NET_SALES_FORMULA_TYPE");
			dbColumnNames.put("netSalesFormulaCategory",
				"NET_SALES_FORMULA_CATEGORY");
			dbColumnNames.put("contractSelection", "CONTRACT_SELECTION");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("netSalesFormulaId", "NET_SALES_FORMULA_ID");
			dbColumnNames.put("netSalesFormulaNo", "NET_SALES_FORMULA_NO");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the net sales formula master in the entity cache if it is enabled.
	 *
	 * @param netSalesFormulaMaster the net sales formula master
	 */
	@Override
	public void cacheResult(NetSalesFormulaMaster netSalesFormulaMaster) {
		entityCache.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterImpl.class,
			netSalesFormulaMaster.getPrimaryKey(), netSalesFormulaMaster);

		netSalesFormulaMaster.resetOriginalValues();
	}

	/**
	 * Caches the net sales formula masters in the entity cache if it is enabled.
	 *
	 * @param netSalesFormulaMasters the net sales formula masters
	 */
	@Override
	public void cacheResult(List<NetSalesFormulaMaster> netSalesFormulaMasters) {
		for (NetSalesFormulaMaster netSalesFormulaMaster : netSalesFormulaMasters) {
			if (entityCache.getResult(
						NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
						NetSalesFormulaMasterImpl.class,
						netSalesFormulaMaster.getPrimaryKey()) == null) {
				cacheResult(netSalesFormulaMaster);
			}
			else {
				netSalesFormulaMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all net sales formula masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(NetSalesFormulaMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the net sales formula master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(NetSalesFormulaMaster netSalesFormulaMaster) {
		entityCache.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterImpl.class,
			netSalesFormulaMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<NetSalesFormulaMaster> netSalesFormulaMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (NetSalesFormulaMaster netSalesFormulaMaster : netSalesFormulaMasters) {
			entityCache.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
				NetSalesFormulaMasterImpl.class,
				netSalesFormulaMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new net sales formula master with the primary key. Does not add the net sales formula master to the database.
	 *
	 * @param netSalesFormulaMasterSid the primary key for the new net sales formula master
	 * @return the new net sales formula master
	 */
	@Override
	public NetSalesFormulaMaster create(int netSalesFormulaMasterSid) {
		NetSalesFormulaMaster netSalesFormulaMaster = new NetSalesFormulaMasterImpl();

		netSalesFormulaMaster.setNew(true);
		netSalesFormulaMaster.setPrimaryKey(netSalesFormulaMasterSid);

		return netSalesFormulaMaster;
	}

	/**
	 * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param netSalesFormulaMasterSid the primary key of the net sales formula master
	 * @return the net sales formula master that was removed
	 * @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster remove(int netSalesFormulaMasterSid)
		throws NoSuchNetSalesFormulaMasterException {
		return remove((Serializable)netSalesFormulaMasterSid);
	}

	/**
	 * Removes the net sales formula master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the net sales formula master
	 * @return the net sales formula master that was removed
	 * @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster remove(Serializable primaryKey)
		throws NoSuchNetSalesFormulaMasterException {
		Session session = null;

		try {
			session = openSession();

			NetSalesFormulaMaster netSalesFormulaMaster = (NetSalesFormulaMaster)session.get(NetSalesFormulaMasterImpl.class,
					primaryKey);

			if (netSalesFormulaMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchNetSalesFormulaMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(netSalesFormulaMaster);
		}
		catch (NoSuchNetSalesFormulaMasterException nsee) {
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
	protected NetSalesFormulaMaster removeImpl(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		netSalesFormulaMaster = toUnwrappedModel(netSalesFormulaMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(netSalesFormulaMaster)) {
				netSalesFormulaMaster = (NetSalesFormulaMaster)session.get(NetSalesFormulaMasterImpl.class,
						netSalesFormulaMaster.getPrimaryKeyObj());
			}

			if (netSalesFormulaMaster != null) {
				session.delete(netSalesFormulaMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (netSalesFormulaMaster != null) {
			clearCache(netSalesFormulaMaster);
		}

		return netSalesFormulaMaster;
	}

	@Override
	public NetSalesFormulaMaster updateImpl(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		netSalesFormulaMaster = toUnwrappedModel(netSalesFormulaMaster);

		boolean isNew = netSalesFormulaMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (netSalesFormulaMaster.isNew()) {
				session.save(netSalesFormulaMaster);

				netSalesFormulaMaster.setNew(false);
			}
			else {
				netSalesFormulaMaster = (NetSalesFormulaMaster)session.merge(netSalesFormulaMaster);
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

		entityCache.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
			NetSalesFormulaMasterImpl.class,
			netSalesFormulaMaster.getPrimaryKey(), netSalesFormulaMaster, false);

		netSalesFormulaMaster.resetOriginalValues();

		return netSalesFormulaMaster;
	}

	protected NetSalesFormulaMaster toUnwrappedModel(
		NetSalesFormulaMaster netSalesFormulaMaster) {
		if (netSalesFormulaMaster instanceof NetSalesFormulaMasterImpl) {
			return netSalesFormulaMaster;
		}

		NetSalesFormulaMasterImpl netSalesFormulaMasterImpl = new NetSalesFormulaMasterImpl();

		netSalesFormulaMasterImpl.setNew(netSalesFormulaMaster.isNew());
		netSalesFormulaMasterImpl.setPrimaryKey(netSalesFormulaMaster.getPrimaryKey());

		netSalesFormulaMasterImpl.setCreatedBy(netSalesFormulaMaster.getCreatedBy());
		netSalesFormulaMasterImpl.setNetSalesFormulaMasterSid(netSalesFormulaMaster.getNetSalesFormulaMasterSid());
		netSalesFormulaMasterImpl.setModifiedBy(netSalesFormulaMaster.getModifiedBy());
		netSalesFormulaMasterImpl.setCreatedDate(netSalesFormulaMaster.getCreatedDate());
		netSalesFormulaMasterImpl.setNetSalesFormulaName(netSalesFormulaMaster.getNetSalesFormulaName());
		netSalesFormulaMasterImpl.setNetSalesFormulaType(netSalesFormulaMaster.getNetSalesFormulaType());
		netSalesFormulaMasterImpl.setNetSalesFormulaCategory(netSalesFormulaMaster.getNetSalesFormulaCategory());
		netSalesFormulaMasterImpl.setContractSelection(netSalesFormulaMaster.getContractSelection());
		netSalesFormulaMasterImpl.setModifiedDate(netSalesFormulaMaster.getModifiedDate());
		netSalesFormulaMasterImpl.setRecordLockStatus(netSalesFormulaMaster.isRecordLockStatus());
		netSalesFormulaMasterImpl.setSource(netSalesFormulaMaster.getSource());
		netSalesFormulaMasterImpl.setNetSalesFormulaId(netSalesFormulaMaster.getNetSalesFormulaId());
		netSalesFormulaMasterImpl.setNetSalesFormulaNo(netSalesFormulaMaster.getNetSalesFormulaNo());
		netSalesFormulaMasterImpl.setInboundStatus(netSalesFormulaMaster.getInboundStatus());
		netSalesFormulaMasterImpl.setCdrModelSid(netSalesFormulaMaster.getCdrModelSid());

		return netSalesFormulaMasterImpl;
	}

	/**
	 * Returns the net sales formula master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the net sales formula master
	 * @return the net sales formula master
	 * @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchNetSalesFormulaMasterException {
		NetSalesFormulaMaster netSalesFormulaMaster = fetchByPrimaryKey(primaryKey);

		if (netSalesFormulaMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchNetSalesFormulaMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return netSalesFormulaMaster;
	}

	/**
	 * Returns the net sales formula master with the primary key or throws a {@link NoSuchNetSalesFormulaMasterException} if it could not be found.
	 *
	 * @param netSalesFormulaMasterSid the primary key of the net sales formula master
	 * @return the net sales formula master
	 * @throws NoSuchNetSalesFormulaMasterException if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster findByPrimaryKey(int netSalesFormulaMasterSid)
		throws NoSuchNetSalesFormulaMasterException {
		return findByPrimaryKey((Serializable)netSalesFormulaMasterSid);
	}

	/**
	 * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the net sales formula master
	 * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
				NetSalesFormulaMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		NetSalesFormulaMaster netSalesFormulaMaster = (NetSalesFormulaMaster)serializable;

		if (netSalesFormulaMaster == null) {
			Session session = null;

			try {
				session = openSession();

				netSalesFormulaMaster = (NetSalesFormulaMaster)session.get(NetSalesFormulaMasterImpl.class,
						primaryKey);

				if (netSalesFormulaMaster != null) {
					cacheResult(netSalesFormulaMaster);
				}
				else {
					entityCache.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
						NetSalesFormulaMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
					NetSalesFormulaMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return netSalesFormulaMaster;
	}

	/**
	 * Returns the net sales formula master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param netSalesFormulaMasterSid the primary key of the net sales formula master
	 * @return the net sales formula master, or <code>null</code> if a net sales formula master with the primary key could not be found
	 */
	@Override
	public NetSalesFormulaMaster fetchByPrimaryKey(int netSalesFormulaMasterSid) {
		return fetchByPrimaryKey((Serializable)netSalesFormulaMasterSid);
	}

	@Override
	public Map<Serializable, NetSalesFormulaMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, NetSalesFormulaMaster> map = new HashMap<Serializable, NetSalesFormulaMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			NetSalesFormulaMaster netSalesFormulaMaster = fetchByPrimaryKey(primaryKey);

			if (netSalesFormulaMaster != null) {
				map.put(primaryKey, netSalesFormulaMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
					NetSalesFormulaMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (NetSalesFormulaMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_NETSALESFORMULAMASTER_WHERE_PKS_IN);

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

			for (NetSalesFormulaMaster netSalesFormulaMaster : (List<NetSalesFormulaMaster>)q.list()) {
				map.put(netSalesFormulaMaster.getPrimaryKeyObj(),
					netSalesFormulaMaster);

				cacheResult(netSalesFormulaMaster);

				uncachedPrimaryKeys.remove(netSalesFormulaMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(NetSalesFormulaMasterModelImpl.ENTITY_CACHE_ENABLED,
					NetSalesFormulaMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the net sales formula masters.
	 *
	 * @return the net sales formula masters
	 */
	@Override
	public List<NetSalesFormulaMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the net sales formula masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of net sales formula masters
	 * @param end the upper bound of the range of net sales formula masters (not inclusive)
	 * @return the range of net sales formula masters
	 */
	@Override
	public List<NetSalesFormulaMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the net sales formula masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of net sales formula masters
	 * @param end the upper bound of the range of net sales formula masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of net sales formula masters
	 */
	@Override
	public List<NetSalesFormulaMaster> findAll(int start, int end,
		OrderByComparator<NetSalesFormulaMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the net sales formula masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NetSalesFormulaMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of net sales formula masters
	 * @param end the upper bound of the range of net sales formula masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of net sales formula masters
	 */
	@Override
	public List<NetSalesFormulaMaster> findAll(int start, int end,
		OrderByComparator<NetSalesFormulaMaster> orderByComparator,
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

		List<NetSalesFormulaMaster> list = null;

		if (retrieveFromCache) {
			list = (List<NetSalesFormulaMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_NETSALESFORMULAMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_NETSALESFORMULAMASTER;

				if (pagination) {
					sql = sql.concat(NetSalesFormulaMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<NetSalesFormulaMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<NetSalesFormulaMaster>)QueryUtil.list(q,
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
	 * Removes all the net sales formula masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (NetSalesFormulaMaster netSalesFormulaMaster : findAll()) {
			remove(netSalesFormulaMaster);
		}
	}

	/**
	 * Returns the number of net sales formula masters.
	 *
	 * @return the number of net sales formula masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_NETSALESFORMULAMASTER);

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
		return NetSalesFormulaMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the net sales formula master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(NetSalesFormulaMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_NETSALESFORMULAMASTER = "SELECT netSalesFormulaMaster FROM NetSalesFormulaMaster netSalesFormulaMaster";
	private static final String _SQL_SELECT_NETSALESFORMULAMASTER_WHERE_PKS_IN = "SELECT netSalesFormulaMaster FROM NetSalesFormulaMaster netSalesFormulaMaster WHERE NET_SALES_FORMULA_MASTER_SID IN (";
	private static final String _SQL_COUNT_NETSALESFORMULAMASTER = "SELECT COUNT(netSalesFormulaMaster) FROM NetSalesFormulaMaster netSalesFormulaMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "netSalesFormulaMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NetSalesFormulaMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(NetSalesFormulaMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "netSalesFormulaMasterSid", "modifiedBy",
				"createdDate", "netSalesFormulaName", "netSalesFormulaType",
				"netSalesFormulaCategory", "contractSelection", "modifiedDate",
				"recordLockStatus", "source", "netSalesFormulaId",
				"netSalesFormulaNo", "inboundStatus", "cdrModelSid"
			});
}