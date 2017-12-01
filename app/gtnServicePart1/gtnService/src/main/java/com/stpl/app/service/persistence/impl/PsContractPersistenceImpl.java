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

import com.stpl.app.exception.NoSuchPsContractException;
import com.stpl.app.model.PsContract;
import com.stpl.app.model.impl.PsContractImpl;
import com.stpl.app.model.impl.PsContractModelImpl;
import com.stpl.app.service.persistence.PsContractPersistence;

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
 * The persistence implementation for the ps contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractPersistence
 * @see com.stpl.app.service.persistence.PsContractUtil
 * @generated
 */
@ProviderType
public class PsContractPersistenceImpl extends BasePersistenceImpl<PsContract>
	implements PsContractPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PsContractUtil} to access the ps contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PsContractImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractModelImpl.FINDER_CACHE_ENABLED, PsContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractModelImpl.FINDER_CACHE_ENABLED, PsContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PsContractPersistenceImpl() {
		setModelClass(PsContract.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("psName", "PS_NAME");
			dbColumnNames.put("psNo", "PS_NO");
			dbColumnNames.put("cfpContractSid", "CFP_CONTRACT_SID");
			dbColumnNames.put("psContractSid", "PS_CONTRACT_SID");
			dbColumnNames.put("psType", "PS_TYPE");
			dbColumnNames.put("psContractAttachedStatus",
				"PS_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("psCategory", "PS_CATEGORY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("psStatus", "PS_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("parentPsId", "PARENT_PS_ID");
			dbColumnNames.put("psDesignation", "PS_DESIGNATION");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("psModelSid", "PS_MODEL_SID");
			dbColumnNames.put("psContractAttachedDate",
				"PS_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("psEndDate", "PS_END_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("parentPsName", "PARENT_PS_NAME");
			dbColumnNames.put("psStartDate", "PS_START_DATE");
			dbColumnNames.put("ifpContractSid", "IFP_CONTRACT_SID");
			dbColumnNames.put("psTradeClass", "PS_TRADE_CLASS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ps contract in the entity cache if it is enabled.
	 *
	 * @param psContract the ps contract
	 */
	@Override
	public void cacheResult(PsContract psContract) {
		entityCache.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractImpl.class, psContract.getPrimaryKey(), psContract);

		psContract.resetOriginalValues();
	}

	/**
	 * Caches the ps contracts in the entity cache if it is enabled.
	 *
	 * @param psContracts the ps contracts
	 */
	@Override
	public void cacheResult(List<PsContract> psContracts) {
		for (PsContract psContract : psContracts) {
			if (entityCache.getResult(
						PsContractModelImpl.ENTITY_CACHE_ENABLED,
						PsContractImpl.class, psContract.getPrimaryKey()) == null) {
				cacheResult(psContract);
			}
			else {
				psContract.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ps contracts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PsContractImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ps contract.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PsContract psContract) {
		entityCache.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractImpl.class, psContract.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PsContract> psContracts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PsContract psContract : psContracts) {
			entityCache.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
				PsContractImpl.class, psContract.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ps contract with the primary key. Does not add the ps contract to the database.
	 *
	 * @param psContractSid the primary key for the new ps contract
	 * @return the new ps contract
	 */
	@Override
	public PsContract create(int psContractSid) {
		PsContract psContract = new PsContractImpl();

		psContract.setNew(true);
		psContract.setPrimaryKey(psContractSid);

		return psContract;
	}

	/**
	 * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psContractSid the primary key of the ps contract
	 * @return the ps contract that was removed
	 * @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract remove(int psContractSid)
		throws NoSuchPsContractException {
		return remove((Serializable)psContractSid);
	}

	/**
	 * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ps contract
	 * @return the ps contract that was removed
	 * @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract remove(Serializable primaryKey)
		throws NoSuchPsContractException {
		Session session = null;

		try {
			session = openSession();

			PsContract psContract = (PsContract)session.get(PsContractImpl.class,
					primaryKey);

			if (psContract == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(psContract);
		}
		catch (NoSuchPsContractException nsee) {
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
	protected PsContract removeImpl(PsContract psContract) {
		psContract = toUnwrappedModel(psContract);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(psContract)) {
				psContract = (PsContract)session.get(PsContractImpl.class,
						psContract.getPrimaryKeyObj());
			}

			if (psContract != null) {
				session.delete(psContract);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (psContract != null) {
			clearCache(psContract);
		}

		return psContract;
	}

	@Override
	public PsContract updateImpl(PsContract psContract) {
		psContract = toUnwrappedModel(psContract);

		boolean isNew = psContract.isNew();

		Session session = null;

		try {
			session = openSession();

			if (psContract.isNew()) {
				session.save(psContract);

				psContract.setNew(false);
			}
			else {
				psContract = (PsContract)session.merge(psContract);
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

		entityCache.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
			PsContractImpl.class, psContract.getPrimaryKey(), psContract, false);

		psContract.resetOriginalValues();

		return psContract;
	}

	protected PsContract toUnwrappedModel(PsContract psContract) {
		if (psContract instanceof PsContractImpl) {
			return psContract;
		}

		PsContractImpl psContractImpl = new PsContractImpl();

		psContractImpl.setNew(psContract.isNew());
		psContractImpl.setPrimaryKey(psContract.getPrimaryKey());

		psContractImpl.setPsName(psContract.getPsName());
		psContractImpl.setPsNo(psContract.getPsNo());
		psContractImpl.setCfpContractSid(psContract.getCfpContractSid());
		psContractImpl.setPsContractSid(psContract.getPsContractSid());
		psContractImpl.setPsType(psContract.getPsType());
		psContractImpl.setPsContractAttachedStatus(psContract.getPsContractAttachedStatus());
		psContractImpl.setModifiedDate(psContract.getModifiedDate());
		psContractImpl.setPsCategory(psContract.getPsCategory());
		psContractImpl.setRecordLockStatus(psContract.isRecordLockStatus());
		psContractImpl.setPsStatus(psContract.getPsStatus());
		psContractImpl.setCreatedDate(psContract.getCreatedDate());
		psContractImpl.setCreatedBy(psContract.getCreatedBy());
		psContractImpl.setSource(psContract.getSource());
		psContractImpl.setParentPsId(psContract.getParentPsId());
		psContractImpl.setPsDesignation(psContract.getPsDesignation());
		psContractImpl.setBatchId(psContract.getBatchId());
		psContractImpl.setContractMasterSid(psContract.getContractMasterSid());
		psContractImpl.setPsModelSid(psContract.getPsModelSid());
		psContractImpl.setPsContractAttachedDate(psContract.getPsContractAttachedDate());
		psContractImpl.setPsEndDate(psContract.getPsEndDate());
		psContractImpl.setModifiedBy(psContract.getModifiedBy());
		psContractImpl.setInboundStatus(psContract.getInboundStatus());
		psContractImpl.setParentPsName(psContract.getParentPsName());
		psContractImpl.setPsStartDate(psContract.getPsStartDate());
		psContractImpl.setIfpContractSid(psContract.getIfpContractSid());
		psContractImpl.setPsTradeClass(psContract.getPsTradeClass());

		return psContractImpl;
	}

	/**
	 * Returns the ps contract with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps contract
	 * @return the ps contract
	 * @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPsContractException {
		PsContract psContract = fetchByPrimaryKey(primaryKey);

		if (psContract == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return psContract;
	}

	/**
	 * Returns the ps contract with the primary key or throws a {@link NoSuchPsContractException} if it could not be found.
	 *
	 * @param psContractSid the primary key of the ps contract
	 * @return the ps contract
	 * @throws NoSuchPsContractException if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract findByPrimaryKey(int psContractSid)
		throws NoSuchPsContractException {
		return findByPrimaryKey((Serializable)psContractSid);
	}

	/**
	 * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps contract
	 * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
				PsContractImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PsContract psContract = (PsContract)serializable;

		if (psContract == null) {
			Session session = null;

			try {
				session = openSession();

				psContract = (PsContract)session.get(PsContractImpl.class,
						primaryKey);

				if (psContract != null) {
					cacheResult(psContract);
				}
				else {
					entityCache.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
						PsContractImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
					PsContractImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return psContract;
	}

	/**
	 * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psContractSid the primary key of the ps contract
	 * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
	 */
	@Override
	public PsContract fetchByPrimaryKey(int psContractSid) {
		return fetchByPrimaryKey((Serializable)psContractSid);
	}

	@Override
	public Map<Serializable, PsContract> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PsContract> map = new HashMap<Serializable, PsContract>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PsContract psContract = fetchByPrimaryKey(primaryKey);

			if (psContract != null) {
				map.put(primaryKey, psContract);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
					PsContractImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PsContract)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PSCONTRACT_WHERE_PKS_IN);

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

			for (PsContract psContract : (List<PsContract>)q.list()) {
				map.put(psContract.getPrimaryKeyObj(), psContract);

				cacheResult(psContract);

				uncachedPrimaryKeys.remove(psContract.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PsContractModelImpl.ENTITY_CACHE_ENABLED,
					PsContractImpl.class, primaryKey, nullModel);
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
	 * Returns all the ps contracts.
	 *
	 * @return the ps contracts
	 */
	@Override
	public List<PsContract> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contracts
	 * @param end the upper bound of the range of ps contracts (not inclusive)
	 * @return the range of ps contracts
	 */
	@Override
	public List<PsContract> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contracts
	 * @param end the upper bound of the range of ps contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ps contracts
	 */
	@Override
	public List<PsContract> findAll(int start, int end,
		OrderByComparator<PsContract> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contracts
	 * @param end the upper bound of the range of ps contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ps contracts
	 */
	@Override
	public List<PsContract> findAll(int start, int end,
		OrderByComparator<PsContract> orderByComparator,
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

		List<PsContract> list = null;

		if (retrieveFromCache) {
			list = (List<PsContract>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PSCONTRACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PSCONTRACT;

				if (pagination) {
					sql = sql.concat(PsContractModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PsContract>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsContract>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ps contracts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PsContract psContract : findAll()) {
			remove(psContract);
		}
	}

	/**
	 * Returns the number of ps contracts.
	 *
	 * @return the number of ps contracts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PSCONTRACT);

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
		return PsContractModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ps contract persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PsContractImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PSCONTRACT = "SELECT psContract FROM PsContract psContract";
	private static final String _SQL_SELECT_PSCONTRACT_WHERE_PKS_IN = "SELECT psContract FROM PsContract psContract WHERE PS_CONTRACT_SID IN (";
	private static final String _SQL_COUNT_PSCONTRACT = "SELECT COUNT(psContract) FROM PsContract psContract";
	private static final String _ORDER_BY_ENTITY_ALIAS = "psContract.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsContract exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(PsContractPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"psName", "psNo", "cfpContractSid", "psContractSid", "psType",
				"psContractAttachedStatus", "modifiedDate", "psCategory",
				"recordLockStatus", "psStatus", "createdDate", "createdBy",
				"source", "parentPsId", "psDesignation", "batchId",
				"contractMasterSid", "psModelSid", "psContractAttachedDate",
				"psEndDate", "modifiedBy", "inboundStatus", "parentPsName",
				"psStartDate", "ifpContractSid", "psTradeClass"
			});
}