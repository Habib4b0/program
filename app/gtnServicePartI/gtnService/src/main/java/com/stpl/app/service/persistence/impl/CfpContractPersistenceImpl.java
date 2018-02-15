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

import com.stpl.app.exception.NoSuchCfpContractException;
import com.stpl.app.model.CfpContract;
import com.stpl.app.model.impl.CfpContractImpl;
import com.stpl.app.model.impl.CfpContractModelImpl;
import com.stpl.app.service.persistence.CfpContractPersistence;

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
 * The persistence implementation for the cfp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractPersistence
 * @see com.stpl.app.service.persistence.CfpContractUtil
 * @generated
 */
@ProviderType
public class CfpContractPersistenceImpl extends BasePersistenceImpl<CfpContract>
	implements CfpContractPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link CfpContractUtil} to access the cfp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = CfpContractImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractModelImpl.FINDER_CACHE_ENABLED, CfpContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractModelImpl.FINDER_CACHE_ENABLED, CfpContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public CfpContractPersistenceImpl() {
		setModelClass(CfpContract.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("cfpContractSid", "CFP_CONTRACT_SID");
			dbColumnNames.put("cfpType", "CFP_TYPE");
			dbColumnNames.put("cfpTradeClass", "CFP_TRADE_CLASS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("cfpContractAttachedDate",
				"CFP_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("cfpModelSid", "CFP_MODEL_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("cfpDesignation", "CFP_DESIGNATION");
			dbColumnNames.put("cfpName", "CFP_NAME");
			dbColumnNames.put("cfpNo", "CFP_NO");
			dbColumnNames.put("cfpCategory", "CFP_CATEGORY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("cfpStatus", "CFP_STATUS");
			dbColumnNames.put("parentCfpId", "PARENT_CFP_ID");
			dbColumnNames.put("cfpContractAttachedStatus",
				"CFP_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("cfpStartDate", "CFP_START_DATE");
			dbColumnNames.put("cfpEndDate", "CFP_END_DATE");
			dbColumnNames.put("parentCfpName", "PARENT_CFP_NAME");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("salesInclusion", "SALES_INCLUSION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the cfp contract in the entity cache if it is enabled.
	 *
	 * @param cfpContract the cfp contract
	 */
	@Override
	public void cacheResult(CfpContract cfpContract) {
		entityCache.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractImpl.class, cfpContract.getPrimaryKey(), cfpContract);

		cfpContract.resetOriginalValues();
	}

	/**
	 * Caches the cfp contracts in the entity cache if it is enabled.
	 *
	 * @param cfpContracts the cfp contracts
	 */
	@Override
	public void cacheResult(List<CfpContract> cfpContracts) {
		for (CfpContract cfpContract : cfpContracts) {
			if (entityCache.getResult(
						CfpContractModelImpl.ENTITY_CACHE_ENABLED,
						CfpContractImpl.class, cfpContract.getPrimaryKey()) == null) {
				cacheResult(cfpContract);
			}
			else {
				cfpContract.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cfp contracts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CfpContractImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cfp contract.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CfpContract cfpContract) {
		entityCache.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractImpl.class, cfpContract.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<CfpContract> cfpContracts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CfpContract cfpContract : cfpContracts) {
			entityCache.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
				CfpContractImpl.class, cfpContract.getPrimaryKey());
		}
	}

	/**
	 * Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
	 *
	 * @param cfpContractSid the primary key for the new cfp contract
	 * @return the new cfp contract
	 */
	@Override
	public CfpContract create(int cfpContractSid) {
		CfpContract cfpContract = new CfpContractImpl();

		cfpContract.setNew(true);
		cfpContract.setPrimaryKey(cfpContractSid);

		return cfpContract;
	}

	/**
	 * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cfpContractSid the primary key of the cfp contract
	 * @return the cfp contract that was removed
	 * @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract remove(int cfpContractSid)
		throws NoSuchCfpContractException {
		return remove((Serializable)cfpContractSid);
	}

	/**
	 * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cfp contract
	 * @return the cfp contract that was removed
	 * @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract remove(Serializable primaryKey)
		throws NoSuchCfpContractException {
		Session session = null;

		try {
			session = openSession();

			CfpContract cfpContract = (CfpContract)session.get(CfpContractImpl.class,
					primaryKey);

			if (cfpContract == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(cfpContract);
		}
		catch (NoSuchCfpContractException nsee) {
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
	protected CfpContract removeImpl(CfpContract cfpContract) {
		cfpContract = toUnwrappedModel(cfpContract);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cfpContract)) {
				cfpContract = (CfpContract)session.get(CfpContractImpl.class,
						cfpContract.getPrimaryKeyObj());
			}

			if (cfpContract != null) {
				session.delete(cfpContract);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cfpContract != null) {
			clearCache(cfpContract);
		}

		return cfpContract;
	}

	@Override
	public CfpContract updateImpl(CfpContract cfpContract) {
		cfpContract = toUnwrappedModel(cfpContract);

		boolean isNew = cfpContract.isNew();

		Session session = null;

		try {
			session = openSession();

			if (cfpContract.isNew()) {
				session.save(cfpContract);

				cfpContract.setNew(false);
			}
			else {
				cfpContract = (CfpContract)session.merge(cfpContract);
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

		entityCache.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
			CfpContractImpl.class, cfpContract.getPrimaryKey(), cfpContract,
			false);

		cfpContract.resetOriginalValues();

		return cfpContract;
	}

	protected CfpContract toUnwrappedModel(CfpContract cfpContract) {
		if (cfpContract instanceof CfpContractImpl) {
			return cfpContract;
		}

		CfpContractImpl cfpContractImpl = new CfpContractImpl();

		cfpContractImpl.setNew(cfpContract.isNew());
		cfpContractImpl.setPrimaryKey(cfpContract.getPrimaryKey());

		cfpContractImpl.setCreatedBy(cfpContract.getCreatedBy());
		cfpContractImpl.setCfpContractSid(cfpContract.getCfpContractSid());
		cfpContractImpl.setCfpType(cfpContract.getCfpType());
		cfpContractImpl.setCfpTradeClass(cfpContract.getCfpTradeClass());
		cfpContractImpl.setModifiedBy(cfpContract.getModifiedBy());
		cfpContractImpl.setCreatedDate(cfpContract.getCreatedDate());
		cfpContractImpl.setContractMasterSid(cfpContract.getContractMasterSid());
		cfpContractImpl.setCfpContractAttachedDate(cfpContract.getCfpContractAttachedDate());
		cfpContractImpl.setCfpModelSid(cfpContract.getCfpModelSid());
		cfpContractImpl.setBatchId(cfpContract.getBatchId());
		cfpContractImpl.setModifiedDate(cfpContract.getModifiedDate());
		cfpContractImpl.setRecordLockStatus(cfpContract.isRecordLockStatus());
		cfpContractImpl.setCfpDesignation(cfpContract.getCfpDesignation());
		cfpContractImpl.setCfpName(cfpContract.getCfpName());
		cfpContractImpl.setCfpNo(cfpContract.getCfpNo());
		cfpContractImpl.setCfpCategory(cfpContract.getCfpCategory());
		cfpContractImpl.setSource(cfpContract.getSource());
		cfpContractImpl.setCfpStatus(cfpContract.getCfpStatus());
		cfpContractImpl.setParentCfpId(cfpContract.getParentCfpId());
		cfpContractImpl.setCfpContractAttachedStatus(cfpContract.getCfpContractAttachedStatus());
		cfpContractImpl.setCfpStartDate(cfpContract.getCfpStartDate());
		cfpContractImpl.setCfpEndDate(cfpContract.getCfpEndDate());
		cfpContractImpl.setParentCfpName(cfpContract.getParentCfpName());
		cfpContractImpl.setInboundStatus(cfpContract.getInboundStatus());
		cfpContractImpl.setSalesInclusion(cfpContract.getSalesInclusion());

		return cfpContractImpl;
	}

	/**
	 * Returns the cfp contract with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp contract
	 * @return the cfp contract
	 * @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCfpContractException {
		CfpContract cfpContract = fetchByPrimaryKey(primaryKey);

		if (cfpContract == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCfpContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return cfpContract;
	}

	/**
	 * Returns the cfp contract with the primary key or throws a {@link NoSuchCfpContractException} if it could not be found.
	 *
	 * @param cfpContractSid the primary key of the cfp contract
	 * @return the cfp contract
	 * @throws NoSuchCfpContractException if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract findByPrimaryKey(int cfpContractSid)
		throws NoSuchCfpContractException {
		return findByPrimaryKey((Serializable)cfpContractSid);
	}

	/**
	 * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cfp contract
	 * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
				CfpContractImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		CfpContract cfpContract = (CfpContract)serializable;

		if (cfpContract == null) {
			Session session = null;

			try {
				session = openSession();

				cfpContract = (CfpContract)session.get(CfpContractImpl.class,
						primaryKey);

				if (cfpContract != null) {
					cacheResult(cfpContract);
				}
				else {
					entityCache.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
						CfpContractImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
					CfpContractImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return cfpContract;
	}

	/**
	 * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cfpContractSid the primary key of the cfp contract
	 * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
	 */
	@Override
	public CfpContract fetchByPrimaryKey(int cfpContractSid) {
		return fetchByPrimaryKey((Serializable)cfpContractSid);
	}

	@Override
	public Map<Serializable, CfpContract> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, CfpContract> map = new HashMap<Serializable, CfpContract>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			CfpContract cfpContract = fetchByPrimaryKey(primaryKey);

			if (cfpContract != null) {
				map.put(primaryKey, cfpContract);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
					CfpContractImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (CfpContract)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CFPCONTRACT_WHERE_PKS_IN);

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

			for (CfpContract cfpContract : (List<CfpContract>)q.list()) {
				map.put(cfpContract.getPrimaryKeyObj(), cfpContract);

				cacheResult(cfpContract);

				uncachedPrimaryKeys.remove(cfpContract.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(CfpContractModelImpl.ENTITY_CACHE_ENABLED,
					CfpContractImpl.class, primaryKey, nullModel);
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
	 * Returns all the cfp contracts.
	 *
	 * @return the cfp contracts
	 */
	@Override
	public List<CfpContract> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cfp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp contracts
	 * @param end the upper bound of the range of cfp contracts (not inclusive)
	 * @return the range of cfp contracts
	 */
	@Override
	public List<CfpContract> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cfp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp contracts
	 * @param end the upper bound of the range of cfp contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cfp contracts
	 */
	@Override
	public List<CfpContract> findAll(int start, int end,
		OrderByComparator<CfpContract> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cfp contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cfp contracts
	 * @param end the upper bound of the range of cfp contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of cfp contracts
	 */
	@Override
	public List<CfpContract> findAll(int start, int end,
		OrderByComparator<CfpContract> orderByComparator,
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

		List<CfpContract> list = null;

		if (retrieveFromCache) {
			list = (List<CfpContract>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CFPCONTRACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CFPCONTRACT;

				if (pagination) {
					sql = sql.concat(CfpContractModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CfpContract>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CfpContract>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the cfp contracts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CfpContract cfpContract : findAll()) {
			remove(cfpContract);
		}
	}

	/**
	 * Returns the number of cfp contracts.
	 *
	 * @return the number of cfp contracts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CFPCONTRACT);

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
		return CfpContractModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cfp contract persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(CfpContractImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CFPCONTRACT = "SELECT cfpContract FROM CfpContract cfpContract";
	private static final String _SQL_SELECT_CFPCONTRACT_WHERE_PKS_IN = "SELECT cfpContract FROM CfpContract cfpContract WHERE CFP_CONTRACT_SID IN (";
	private static final String _SQL_COUNT_CFPCONTRACT = "SELECT COUNT(cfpContract) FROM CfpContract cfpContract";
	private static final String _ORDER_BY_ENTITY_ALIAS = "cfpContract.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No CfpContract exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(CfpContractPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "cfpContractSid", "cfpType", "cfpTradeClass",
				"modifiedBy", "createdDate", "contractMasterSid",
				"cfpContractAttachedDate", "cfpModelSid", "batchId",
				"modifiedDate", "recordLockStatus", "cfpDesignation", "cfpName",
				"cfpNo", "cfpCategory", "source", "cfpStatus", "parentCfpId",
				"cfpContractAttachedStatus", "cfpStartDate", "cfpEndDate",
				"parentCfpName", "inboundStatus", "salesInclusion"
			});
}