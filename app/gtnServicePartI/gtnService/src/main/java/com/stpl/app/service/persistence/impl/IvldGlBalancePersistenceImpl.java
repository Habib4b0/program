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

import com.stpl.app.exception.NoSuchIvldGlBalanceException;
import com.stpl.app.model.IvldGlBalance;
import com.stpl.app.model.impl.IvldGlBalanceImpl;
import com.stpl.app.model.impl.IvldGlBalanceModelImpl;
import com.stpl.app.service.persistence.IvldGlBalancePersistence;

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
 * The persistence implementation for the ivld gl balance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlBalancePersistence
 * @see com.stpl.app.service.persistence.IvldGlBalanceUtil
 * @generated
 */
@ProviderType
public class IvldGlBalancePersistenceImpl extends BasePersistenceImpl<IvldGlBalance>
	implements IvldGlBalancePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldGlBalanceUtil} to access the ivld gl balance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldGlBalanceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED,
			IvldGlBalanceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED,
			IvldGlBalanceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldGlBalancePersistenceImpl() {
		setModelClass(IvldGlBalance.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("balance", "BALANCE");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("period", "PERIOD");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("closeIndicator", "CLOSE_INDICATOR");
			dbColumnNames.put("insertedDate", "INSERTED_DATE");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("ivldGlBalanceSid", "IVLD_GL_BALANCE_SID");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("glBalanceIntfid", "GL_BALANCE_INTFID");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld gl balance in the entity cache if it is enabled.
	 *
	 * @param ivldGlBalance the ivld gl balance
	 */
	@Override
	public void cacheResult(IvldGlBalance ivldGlBalance) {
		entityCache.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey(),
			ivldGlBalance);

		ivldGlBalance.resetOriginalValues();
	}

	/**
	 * Caches the ivld gl balances in the entity cache if it is enabled.
	 *
	 * @param ivldGlBalances the ivld gl balances
	 */
	@Override
	public void cacheResult(List<IvldGlBalance> ivldGlBalances) {
		for (IvldGlBalance ivldGlBalance : ivldGlBalances) {
			if (entityCache.getResult(
						IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
						IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey()) == null) {
				cacheResult(ivldGlBalance);
			}
			else {
				ivldGlBalance.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld gl balances.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldGlBalanceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld gl balance.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldGlBalance ivldGlBalance) {
		entityCache.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldGlBalance> ivldGlBalances) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldGlBalance ivldGlBalance : ivldGlBalances) {
			entityCache.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
				IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
	 *
	 * @param ivldGlBalanceSid the primary key for the new ivld gl balance
	 * @return the new ivld gl balance
	 */
	@Override
	public IvldGlBalance create(int ivldGlBalanceSid) {
		IvldGlBalance ivldGlBalance = new IvldGlBalanceImpl();

		ivldGlBalance.setNew(true);
		ivldGlBalance.setPrimaryKey(ivldGlBalanceSid);

		return ivldGlBalance;
	}

	/**
	 * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldGlBalanceSid the primary key of the ivld gl balance
	 * @return the ivld gl balance that was removed
	 * @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance remove(int ivldGlBalanceSid)
		throws NoSuchIvldGlBalanceException {
		return remove((Serializable)ivldGlBalanceSid);
	}

	/**
	 * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld gl balance
	 * @return the ivld gl balance that was removed
	 * @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance remove(Serializable primaryKey)
		throws NoSuchIvldGlBalanceException {
		Session session = null;

		try {
			session = openSession();

			IvldGlBalance ivldGlBalance = (IvldGlBalance)session.get(IvldGlBalanceImpl.class,
					primaryKey);

			if (ivldGlBalance == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldGlBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldGlBalance);
		}
		catch (NoSuchIvldGlBalanceException nsee) {
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
	protected IvldGlBalance removeImpl(IvldGlBalance ivldGlBalance) {
		ivldGlBalance = toUnwrappedModel(ivldGlBalance);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldGlBalance)) {
				ivldGlBalance = (IvldGlBalance)session.get(IvldGlBalanceImpl.class,
						ivldGlBalance.getPrimaryKeyObj());
			}

			if (ivldGlBalance != null) {
				session.delete(ivldGlBalance);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldGlBalance != null) {
			clearCache(ivldGlBalance);
		}

		return ivldGlBalance;
	}

	@Override
	public IvldGlBalance updateImpl(IvldGlBalance ivldGlBalance) {
		ivldGlBalance = toUnwrappedModel(ivldGlBalance);

		boolean isNew = ivldGlBalance.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldGlBalance.isNew()) {
				session.save(ivldGlBalance);

				ivldGlBalance.setNew(false);
			}
			else {
				ivldGlBalance = (IvldGlBalance)session.merge(ivldGlBalance);
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

		entityCache.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
			IvldGlBalanceImpl.class, ivldGlBalance.getPrimaryKey(),
			ivldGlBalance, false);

		ivldGlBalance.resetOriginalValues();

		return ivldGlBalance;
	}

	protected IvldGlBalance toUnwrappedModel(IvldGlBalance ivldGlBalance) {
		if (ivldGlBalance instanceof IvldGlBalanceImpl) {
			return ivldGlBalance;
		}

		IvldGlBalanceImpl ivldGlBalanceImpl = new IvldGlBalanceImpl();

		ivldGlBalanceImpl.setNew(ivldGlBalance.isNew());
		ivldGlBalanceImpl.setPrimaryKey(ivldGlBalance.getPrimaryKey());

		ivldGlBalanceImpl.setBalance(ivldGlBalance.getBalance());
		ivldGlBalanceImpl.setAccountNo(ivldGlBalance.getAccountNo());
		ivldGlBalanceImpl.setReasonForFailure(ivldGlBalance.getReasonForFailure());
		ivldGlBalanceImpl.setAccountId(ivldGlBalance.getAccountId());
		ivldGlBalanceImpl.setYear(ivldGlBalance.getYear());
		ivldGlBalanceImpl.setPeriod(ivldGlBalance.getPeriod());
		ivldGlBalanceImpl.setModifiedDate(ivldGlBalance.getModifiedDate());
		ivldGlBalanceImpl.setIsActive(ivldGlBalance.getIsActive());
		ivldGlBalanceImpl.setSource(ivldGlBalance.getSource());
		ivldGlBalanceImpl.setUploadDate(ivldGlBalance.getUploadDate());
		ivldGlBalanceImpl.setCreatedBy(ivldGlBalance.getCreatedBy());
		ivldGlBalanceImpl.setCreatedDate(ivldGlBalance.getCreatedDate());
		ivldGlBalanceImpl.setAddChgDelIndicator(ivldGlBalance.getAddChgDelIndicator());
		ivldGlBalanceImpl.setBatchId(ivldGlBalance.getBatchId());
		ivldGlBalanceImpl.setCloseIndicator(ivldGlBalance.getCloseIndicator());
		ivldGlBalanceImpl.setInsertedDate(ivldGlBalance.getInsertedDate());
		ivldGlBalanceImpl.setErrorField(ivldGlBalance.getErrorField());
		ivldGlBalanceImpl.setIvldGlBalanceSid(ivldGlBalance.getIvldGlBalanceSid());
		ivldGlBalanceImpl.setErrorCode(ivldGlBalance.getErrorCode());
		ivldGlBalanceImpl.setGlBalanceIntfid(ivldGlBalance.getGlBalanceIntfid());
		ivldGlBalanceImpl.setIntfInsertedDate(ivldGlBalance.getIntfInsertedDate());
		ivldGlBalanceImpl.setModifiedBy(ivldGlBalance.getModifiedBy());
		ivldGlBalanceImpl.setReprocessedFlag(ivldGlBalance.getReprocessedFlag());
		ivldGlBalanceImpl.setCheckRecord(ivldGlBalance.isCheckRecord());

		return ivldGlBalanceImpl;
	}

	/**
	 * Returns the ivld gl balance with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld gl balance
	 * @return the ivld gl balance
	 * @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldGlBalanceException {
		IvldGlBalance ivldGlBalance = fetchByPrimaryKey(primaryKey);

		if (ivldGlBalance == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldGlBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldGlBalance;
	}

	/**
	 * Returns the ivld gl balance with the primary key or throws a {@link NoSuchIvldGlBalanceException} if it could not be found.
	 *
	 * @param ivldGlBalanceSid the primary key of the ivld gl balance
	 * @return the ivld gl balance
	 * @throws NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance findByPrimaryKey(int ivldGlBalanceSid)
		throws NoSuchIvldGlBalanceException {
		return findByPrimaryKey((Serializable)ivldGlBalanceSid);
	}

	/**
	 * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld gl balance
	 * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
				IvldGlBalanceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldGlBalance ivldGlBalance = (IvldGlBalance)serializable;

		if (ivldGlBalance == null) {
			Session session = null;

			try {
				session = openSession();

				ivldGlBalance = (IvldGlBalance)session.get(IvldGlBalanceImpl.class,
						primaryKey);

				if (ivldGlBalance != null) {
					cacheResult(ivldGlBalance);
				}
				else {
					entityCache.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
						IvldGlBalanceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlBalanceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldGlBalance;
	}

	/**
	 * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldGlBalanceSid the primary key of the ivld gl balance
	 * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
	 */
	@Override
	public IvldGlBalance fetchByPrimaryKey(int ivldGlBalanceSid) {
		return fetchByPrimaryKey((Serializable)ivldGlBalanceSid);
	}

	@Override
	public Map<Serializable, IvldGlBalance> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldGlBalance> map = new HashMap<Serializable, IvldGlBalance>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldGlBalance ivldGlBalance = fetchByPrimaryKey(primaryKey);

			if (ivldGlBalance != null) {
				map.put(primaryKey, ivldGlBalance);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlBalanceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldGlBalance)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDGLBALANCE_WHERE_PKS_IN);

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

			for (IvldGlBalance ivldGlBalance : (List<IvldGlBalance>)q.list()) {
				map.put(ivldGlBalance.getPrimaryKeyObj(), ivldGlBalance);

				cacheResult(ivldGlBalance);

				uncachedPrimaryKeys.remove(ivldGlBalance.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldGlBalanceModelImpl.ENTITY_CACHE_ENABLED,
					IvldGlBalanceImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld gl balances.
	 *
	 * @return the ivld gl balances
	 */
	@Override
	public List<IvldGlBalance> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld gl balances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl balances
	 * @param end the upper bound of the range of ivld gl balances (not inclusive)
	 * @return the range of ivld gl balances
	 */
	@Override
	public List<IvldGlBalance> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld gl balances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl balances
	 * @param end the upper bound of the range of ivld gl balances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld gl balances
	 */
	@Override
	public List<IvldGlBalance> findAll(int start, int end,
		OrderByComparator<IvldGlBalance> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld gl balances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld gl balances
	 * @param end the upper bound of the range of ivld gl balances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld gl balances
	 */
	@Override
	public List<IvldGlBalance> findAll(int start, int end,
		OrderByComparator<IvldGlBalance> orderByComparator,
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

		List<IvldGlBalance> list = null;

		if (retrieveFromCache) {
			list = (List<IvldGlBalance>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDGLBALANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDGLBALANCE;

				if (pagination) {
					sql = sql.concat(IvldGlBalanceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldGlBalance>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldGlBalance>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the ivld gl balances from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldGlBalance ivldGlBalance : findAll()) {
			remove(ivldGlBalance);
		}
	}

	/**
	 * Returns the number of ivld gl balances.
	 *
	 * @return the number of ivld gl balances
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDGLBALANCE);

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
		return IvldGlBalanceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld gl balance persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldGlBalanceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDGLBALANCE = "SELECT ivldGlBalance FROM IvldGlBalance ivldGlBalance";
	private static final String _SQL_SELECT_IVLDGLBALANCE_WHERE_PKS_IN = "SELECT ivldGlBalance FROM IvldGlBalance ivldGlBalance WHERE IVLD_GL_BALANCE_SID IN (";
	private static final String _SQL_COUNT_IVLDGLBALANCE = "SELECT COUNT(ivldGlBalance) FROM IvldGlBalance ivldGlBalance";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldGlBalance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldGlBalance exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldGlBalancePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"balance", "accountNo", "reasonForFailure", "accountId", "year",
				"period", "modifiedDate", "isActive", "source", "uploadDate",
				"createdBy", "createdDate", "addChgDelIndicator", "batchId",
				"closeIndicator", "insertedDate", "errorField",
				"ivldGlBalanceSid", "errorCode", "glBalanceIntfid",
				"intfInsertedDate", "modifiedBy", "reprocessedFlag",
				"checkRecord"
			});
}