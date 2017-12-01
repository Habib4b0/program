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

import com.stpl.app.exception.NoSuchIfpContractException;
import com.stpl.app.model.IfpContract;
import com.stpl.app.model.impl.IfpContractImpl;
import com.stpl.app.model.impl.IfpContractModelImpl;
import com.stpl.app.service.persistence.IfpContractPersistence;

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
 * The persistence implementation for the ifp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractPersistence
 * @see com.stpl.app.service.persistence.IfpContractUtil
 * @generated
 */
@ProviderType
public class IfpContractPersistenceImpl extends BasePersistenceImpl<IfpContract>
	implements IfpContractPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IfpContractUtil} to access the ifp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IfpContractImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractModelImpl.FINDER_CACHE_ENABLED, IfpContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractModelImpl.FINDER_CACHE_ENABLED, IfpContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IfpContractPersistenceImpl() {
		setModelClass(IfpContract.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("cfpContractSid", "CFP_CONTRACT_SID");
			dbColumnNames.put("parentIfpName", "PARENT_IFP_NAME");
			dbColumnNames.put("ifpContractAttachedDate",
				"IFP_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("ifpStatus", "IFP_STATUS");
			dbColumnNames.put("ifpStartDate", "IFP_START_DATE");
			dbColumnNames.put("ifpContractAttachedStatus",
				"IFP_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("ifpCategory", "IFP_CATEGORY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("ifpEndDate", "IFP_END_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("ifpDesignation", "IFP_DESIGNATION");
			dbColumnNames.put("parentIfpId", "PARENT_IFP_ID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("ifpType", "IFP_TYPE");
			dbColumnNames.put("ifpName", "IFP_NAME");
			dbColumnNames.put("ifpNo", "IFP_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("ifpContractSid", "IFP_CONTRACT_SID");
			dbColumnNames.put("ifpModelSid", "IFP_MODEL_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ifp contract in the entity cache if it is enabled.
	 *
	 * @param ifpContract the ifp contract
	 */
	@Override
	public void cacheResult(IfpContract ifpContract) {
		entityCache.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractImpl.class, ifpContract.getPrimaryKey(), ifpContract);

		ifpContract.resetOriginalValues();
	}

	/**
	 * Caches the ifp contracts in the entity cache if it is enabled.
	 *
	 * @param ifpContracts the ifp contracts
	 */
	@Override
	public void cacheResult(List<IfpContract> ifpContracts) {
		for (IfpContract ifpContract : ifpContracts) {
			if (entityCache.getResult(
						IfpContractModelImpl.ENTITY_CACHE_ENABLED,
						IfpContractImpl.class, ifpContract.getPrimaryKey()) == null) {
				cacheResult(ifpContract);
			}
			else {
				ifpContract.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ifp contracts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IfpContractImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ifp contract.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IfpContract ifpContract) {
		entityCache.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractImpl.class, ifpContract.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IfpContract> ifpContracts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IfpContract ifpContract : ifpContracts) {
			entityCache.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
				IfpContractImpl.class, ifpContract.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
	 *
	 * @param ifpContractSid the primary key for the new ifp contract
	 * @return the new ifp contract
	 */
	@Override
	public IfpContract create(int ifpContractSid) {
		IfpContract ifpContract = new IfpContractImpl();

		ifpContract.setNew(true);
		ifpContract.setPrimaryKey(ifpContractSid);

		return ifpContract;
	}

	/**
	 * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ifpContractSid the primary key of the ifp contract
	 * @return the ifp contract that was removed
	 * @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract remove(int ifpContractSid)
		throws NoSuchIfpContractException {
		return remove((Serializable)ifpContractSid);
	}

	/**
	 * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ifp contract
	 * @return the ifp contract that was removed
	 * @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract remove(Serializable primaryKey)
		throws NoSuchIfpContractException {
		Session session = null;

		try {
			session = openSession();

			IfpContract ifpContract = (IfpContract)session.get(IfpContractImpl.class,
					primaryKey);

			if (ifpContract == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ifpContract);
		}
		catch (NoSuchIfpContractException nsee) {
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
	protected IfpContract removeImpl(IfpContract ifpContract) {
		ifpContract = toUnwrappedModel(ifpContract);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ifpContract)) {
				ifpContract = (IfpContract)session.get(IfpContractImpl.class,
						ifpContract.getPrimaryKeyObj());
			}

			if (ifpContract != null) {
				session.delete(ifpContract);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ifpContract != null) {
			clearCache(ifpContract);
		}

		return ifpContract;
	}

	@Override
	public IfpContract updateImpl(IfpContract ifpContract) {
		ifpContract = toUnwrappedModel(ifpContract);

		boolean isNew = ifpContract.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ifpContract.isNew()) {
				session.save(ifpContract);

				ifpContract.setNew(false);
			}
			else {
				ifpContract = (IfpContract)session.merge(ifpContract);
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

		entityCache.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
			IfpContractImpl.class, ifpContract.getPrimaryKey(), ifpContract,
			false);

		ifpContract.resetOriginalValues();

		return ifpContract;
	}

	protected IfpContract toUnwrappedModel(IfpContract ifpContract) {
		if (ifpContract instanceof IfpContractImpl) {
			return ifpContract;
		}

		IfpContractImpl ifpContractImpl = new IfpContractImpl();

		ifpContractImpl.setNew(ifpContract.isNew());
		ifpContractImpl.setPrimaryKey(ifpContract.getPrimaryKey());

		ifpContractImpl.setCfpContractSid(ifpContract.getCfpContractSid());
		ifpContractImpl.setParentIfpName(ifpContract.getParentIfpName());
		ifpContractImpl.setIfpContractAttachedDate(ifpContract.getIfpContractAttachedDate());
		ifpContractImpl.setIfpStatus(ifpContract.getIfpStatus());
		ifpContractImpl.setIfpStartDate(ifpContract.getIfpStartDate());
		ifpContractImpl.setIfpContractAttachedStatus(ifpContract.getIfpContractAttachedStatus());
		ifpContractImpl.setModifiedDate(ifpContract.getModifiedDate());
		ifpContractImpl.setIfpCategory(ifpContract.getIfpCategory());
		ifpContractImpl.setRecordLockStatus(ifpContract.isRecordLockStatus());
		ifpContractImpl.setIfpEndDate(ifpContract.getIfpEndDate());
		ifpContractImpl.setCreatedDate(ifpContract.getCreatedDate());
		ifpContractImpl.setCreatedBy(ifpContract.getCreatedBy());
		ifpContractImpl.setSource(ifpContract.getSource());
		ifpContractImpl.setIfpDesignation(ifpContract.getIfpDesignation());
		ifpContractImpl.setParentIfpId(ifpContract.getParentIfpId());
		ifpContractImpl.setBatchId(ifpContract.getBatchId());
		ifpContractImpl.setContractMasterSid(ifpContract.getContractMasterSid());
		ifpContractImpl.setIfpType(ifpContract.getIfpType());
		ifpContractImpl.setIfpName(ifpContract.getIfpName());
		ifpContractImpl.setIfpNo(ifpContract.getIfpNo());
		ifpContractImpl.setModifiedBy(ifpContract.getModifiedBy());
		ifpContractImpl.setInboundStatus(ifpContract.getInboundStatus());
		ifpContractImpl.setIfpContractSid(ifpContract.getIfpContractSid());
		ifpContractImpl.setIfpModelSid(ifpContract.getIfpModelSid());

		return ifpContractImpl;
	}

	/**
	 * Returns the ifp contract with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp contract
	 * @return the ifp contract
	 * @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIfpContractException {
		IfpContract ifpContract = fetchByPrimaryKey(primaryKey);

		if (ifpContract == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ifpContract;
	}

	/**
	 * Returns the ifp contract with the primary key or throws a {@link NoSuchIfpContractException} if it could not be found.
	 *
	 * @param ifpContractSid the primary key of the ifp contract
	 * @return the ifp contract
	 * @throws NoSuchIfpContractException if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract findByPrimaryKey(int ifpContractSid)
		throws NoSuchIfpContractException {
		return findByPrimaryKey((Serializable)ifpContractSid);
	}

	/**
	 * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ifp contract
	 * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
				IfpContractImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IfpContract ifpContract = (IfpContract)serializable;

		if (ifpContract == null) {
			Session session = null;

			try {
				session = openSession();

				ifpContract = (IfpContract)session.get(IfpContractImpl.class,
						primaryKey);

				if (ifpContract != null) {
					cacheResult(ifpContract);
				}
				else {
					entityCache.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
						IfpContractImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
					IfpContractImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ifpContract;
	}

	/**
	 * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ifpContractSid the primary key of the ifp contract
	 * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
	 */
	@Override
	public IfpContract fetchByPrimaryKey(int ifpContractSid) {
		return fetchByPrimaryKey((Serializable)ifpContractSid);
	}

	@Override
	public Map<Serializable, IfpContract> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IfpContract> map = new HashMap<Serializable, IfpContract>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IfpContract ifpContract = fetchByPrimaryKey(primaryKey);

			if (ifpContract != null) {
				map.put(primaryKey, ifpContract);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
					IfpContractImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IfpContract)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IFPCONTRACT_WHERE_PKS_IN);

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

			for (IfpContract ifpContract : (List<IfpContract>)q.list()) {
				map.put(ifpContract.getPrimaryKeyObj(), ifpContract);

				cacheResult(ifpContract);

				uncachedPrimaryKeys.remove(ifpContract.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IfpContractModelImpl.ENTITY_CACHE_ENABLED,
					IfpContractImpl.class, primaryKey, nullModel);
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
	 * Returns all the ifp contracts.
	 *
	 * @return the ifp contracts
	 */
	@Override
	public List<IfpContract> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ifp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp contracts
	 * @param end the upper bound of the range of ifp contracts (not inclusive)
	 * @return the range of ifp contracts
	 */
	@Override
	public List<IfpContract> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ifp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp contracts
	 * @param end the upper bound of the range of ifp contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ifp contracts
	 */
	@Override
	public List<IfpContract> findAll(int start, int end,
		OrderByComparator<IfpContract> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ifp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ifp contracts
	 * @param end the upper bound of the range of ifp contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ifp contracts
	 */
	@Override
	public List<IfpContract> findAll(int start, int end,
		OrderByComparator<IfpContract> orderByComparator,
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

		List<IfpContract> list = null;

		if (retrieveFromCache) {
			list = (List<IfpContract>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IFPCONTRACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IFPCONTRACT;

				if (pagination) {
					sql = sql.concat(IfpContractModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IfpContract>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IfpContract>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ifp contracts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IfpContract ifpContract : findAll()) {
			remove(ifpContract);
		}
	}

	/**
	 * Returns the number of ifp contracts.
	 *
	 * @return the number of ifp contracts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IFPCONTRACT);

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
		return IfpContractModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ifp contract persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IfpContractImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IFPCONTRACT = "SELECT ifpContract FROM IfpContract ifpContract";
	private static final String _SQL_SELECT_IFPCONTRACT_WHERE_PKS_IN = "SELECT ifpContract FROM IfpContract ifpContract WHERE IFP_CONTRACT_SID IN (";
	private static final String _SQL_COUNT_IFPCONTRACT = "SELECT COUNT(ifpContract) FROM IfpContract ifpContract";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ifpContract.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IfpContract exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IfpContractPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"cfpContractSid", "parentIfpName", "ifpContractAttachedDate",
				"ifpStatus", "ifpStartDate", "ifpContractAttachedStatus",
				"modifiedDate", "ifpCategory", "recordLockStatus", "ifpEndDate",
				"createdDate", "createdBy", "source", "ifpDesignation",
				"parentIfpId", "batchId", "contractMasterSid", "ifpType",
				"ifpName", "ifpNo", "modifiedBy", "inboundStatus",
				"ifpContractSid", "ifpModelSid"
			});
}