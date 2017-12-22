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

import com.stpl.app.parttwo.exception.NoSuchAccClosureMasterException;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.model.impl.AccClosureMasterImpl;
import com.stpl.app.parttwo.model.impl.AccClosureMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.AccClosureMasterPersistence;

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
 * The persistence implementation for the acc closure master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccClosureMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.AccClosureMasterUtil
 * @generated
 */
@ProviderType
public class AccClosureMasterPersistenceImpl extends BasePersistenceImpl<AccClosureMaster>
	implements AccClosureMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccClosureMasterUtil} to access the acc closure master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccClosureMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterModelImpl.FINDER_CACHE_ENABLED,
			AccClosureMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterModelImpl.FINDER_CACHE_ENABLED,
			AccClosureMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccClosureMasterPersistenceImpl() {
		setModelClass(AccClosureMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("saveFlag", "SAVE_FLAG");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("toDate", "TO_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("description", "DESCRIPTION");
			dbColumnNames.put("reportName", "REPORT_NAME");
			dbColumnNames.put("rsType", "RS_TYPE");
			dbColumnNames.put("productIdentifier", "PRODUCT_IDENTIFIER");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("workflowStatus", "WORKFLOW_STATUS");
			dbColumnNames.put("moduleType", "MODULE_TYPE");
			dbColumnNames.put("fromDate", "FROM_DATE");
			dbColumnNames.put("contractType", "CONTRACT_TYPE");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("accrualPeriod", "ACCRUAL_PERIOD");
			dbColumnNames.put("companyGroupSid", "COMPANY_GROUP_SID");
			dbColumnNames.put("accClosureMasterSid", "ACC_CLOSURE_MASTER_SID");
			dbColumnNames.put("rsCategory", "RS_CATEGORY");
			dbColumnNames.put("adjustmentType", "ADJUSTMENT_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");
			dbColumnNames.put("rebateProgramType", "REBATE_PROGRAM_TYPE");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the acc closure master in the entity cache if it is enabled.
	 *
	 * @param accClosureMaster the acc closure master
	 */
	@Override
	public void cacheResult(AccClosureMaster accClosureMaster) {
		entityCache.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey(),
			accClosureMaster);

		accClosureMaster.resetOriginalValues();
	}

	/**
	 * Caches the acc closure masters in the entity cache if it is enabled.
	 *
	 * @param accClosureMasters the acc closure masters
	 */
	@Override
	public void cacheResult(List<AccClosureMaster> accClosureMasters) {
		for (AccClosureMaster accClosureMaster : accClosureMasters) {
			if (entityCache.getResult(
						AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureMasterImpl.class,
						accClosureMaster.getPrimaryKey()) == null) {
				cacheResult(accClosureMaster);
			}
			else {
				accClosureMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all acc closure masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccClosureMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the acc closure master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccClosureMaster accClosureMaster) {
		entityCache.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccClosureMaster> accClosureMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccClosureMaster accClosureMaster : accClosureMasters) {
			entityCache.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new acc closure master with the primary key. Does not add the acc closure master to the database.
	 *
	 * @param accClosureMasterSid the primary key for the new acc closure master
	 * @return the new acc closure master
	 */
	@Override
	public AccClosureMaster create(int accClosureMasterSid) {
		AccClosureMaster accClosureMaster = new AccClosureMasterImpl();

		accClosureMaster.setNew(true);
		accClosureMaster.setPrimaryKey(accClosureMasterSid);

		return accClosureMaster;
	}

	/**
	 * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accClosureMasterSid the primary key of the acc closure master
	 * @return the acc closure master that was removed
	 * @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster remove(int accClosureMasterSid)
		throws NoSuchAccClosureMasterException {
		return remove((Serializable)accClosureMasterSid);
	}

	/**
	 * Removes the acc closure master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the acc closure master
	 * @return the acc closure master that was removed
	 * @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster remove(Serializable primaryKey)
		throws NoSuchAccClosureMasterException {
		Session session = null;

		try {
			session = openSession();

			AccClosureMaster accClosureMaster = (AccClosureMaster)session.get(AccClosureMasterImpl.class,
					primaryKey);

			if (accClosureMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccClosureMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accClosureMaster);
		}
		catch (NoSuchAccClosureMasterException nsee) {
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
	protected AccClosureMaster removeImpl(AccClosureMaster accClosureMaster) {
		accClosureMaster = toUnwrappedModel(accClosureMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accClosureMaster)) {
				accClosureMaster = (AccClosureMaster)session.get(AccClosureMasterImpl.class,
						accClosureMaster.getPrimaryKeyObj());
			}

			if (accClosureMaster != null) {
				session.delete(accClosureMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accClosureMaster != null) {
			clearCache(accClosureMaster);
		}

		return accClosureMaster;
	}

	@Override
	public AccClosureMaster updateImpl(AccClosureMaster accClosureMaster) {
		accClosureMaster = toUnwrappedModel(accClosureMaster);

		boolean isNew = accClosureMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (accClosureMaster.isNew()) {
				session.save(accClosureMaster);

				accClosureMaster.setNew(false);
			}
			else {
				accClosureMaster = (AccClosureMaster)session.merge(accClosureMaster);
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

		entityCache.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccClosureMasterImpl.class, accClosureMaster.getPrimaryKey(),
			accClosureMaster, false);

		accClosureMaster.resetOriginalValues();

		return accClosureMaster;
	}

	protected AccClosureMaster toUnwrappedModel(
		AccClosureMaster accClosureMaster) {
		if (accClosureMaster instanceof AccClosureMasterImpl) {
			return accClosureMaster;
		}

		AccClosureMasterImpl accClosureMasterImpl = new AccClosureMasterImpl();

		accClosureMasterImpl.setNew(accClosureMaster.isNew());
		accClosureMasterImpl.setPrimaryKey(accClosureMaster.getPrimaryKey());

		accClosureMasterImpl.setSaveFlag(accClosureMaster.isSaveFlag());
		accClosureMasterImpl.setAccountNo(accClosureMaster.getAccountNo());
		accClosureMasterImpl.setToDate(accClosureMaster.getToDate());
		accClosureMasterImpl.setItemMasterSid(accClosureMaster.getItemMasterSid());
		accClosureMasterImpl.setDescription(accClosureMaster.getDescription());
		accClosureMasterImpl.setReportName(accClosureMaster.getReportName());
		accClosureMasterImpl.setRsType(accClosureMaster.getRsType());
		accClosureMasterImpl.setProductIdentifier(accClosureMaster.getProductIdentifier());
		accClosureMasterImpl.setModifiedDate(accClosureMaster.getModifiedDate());
		accClosureMasterImpl.setWorkflowStatus(accClosureMaster.getWorkflowStatus());
		accClosureMasterImpl.setModuleType(accClosureMaster.getModuleType());
		accClosureMasterImpl.setFromDate(accClosureMaster.getFromDate());
		accClosureMasterImpl.setContractType(accClosureMaster.getContractType());
		accClosureMasterImpl.setGlCompanyMasterSid(accClosureMaster.getGlCompanyMasterSid());
		accClosureMasterImpl.setCreatedDate(accClosureMaster.getCreatedDate());
		accClosureMasterImpl.setCreatedBy(accClosureMaster.getCreatedBy());
		accClosureMasterImpl.setContractMasterSid(accClosureMaster.getContractMasterSid());
		accClosureMasterImpl.setAccrualPeriod(accClosureMaster.getAccrualPeriod());
		accClosureMasterImpl.setCompanyGroupSid(accClosureMaster.getCompanyGroupSid());
		accClosureMasterImpl.setAccClosureMasterSid(accClosureMaster.getAccClosureMasterSid());
		accClosureMasterImpl.setRsCategory(accClosureMaster.getRsCategory());
		accClosureMasterImpl.setAdjustmentType(accClosureMaster.getAdjustmentType());
		accClosureMasterImpl.setModifiedBy(accClosureMaster.getModifiedBy());
		accClosureMasterImpl.setItemGroupSid(accClosureMaster.getItemGroupSid());
		accClosureMasterImpl.setRebateProgramType(accClosureMaster.getRebateProgramType());

		return accClosureMasterImpl;
	}

	/**
	 * Returns the acc closure master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure master
	 * @return the acc closure master
	 * @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccClosureMasterException {
		AccClosureMaster accClosureMaster = fetchByPrimaryKey(primaryKey);

		if (accClosureMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccClosureMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accClosureMaster;
	}

	/**
	 * Returns the acc closure master with the primary key or throws a {@link NoSuchAccClosureMasterException} if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the acc closure master
	 * @return the acc closure master
	 * @throws NoSuchAccClosureMasterException if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster findByPrimaryKey(int accClosureMasterSid)
		throws NoSuchAccClosureMasterException {
		return findByPrimaryKey((Serializable)accClosureMasterSid);
	}

	/**
	 * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the acc closure master
	 * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccClosureMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccClosureMaster accClosureMaster = (AccClosureMaster)serializable;

		if (accClosureMaster == null) {
			Session session = null;

			try {
				session = openSession();

				accClosureMaster = (AccClosureMaster)session.get(AccClosureMasterImpl.class,
						primaryKey);

				if (accClosureMaster != null) {
					cacheResult(accClosureMaster);
				}
				else {
					entityCache.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccClosureMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accClosureMaster;
	}

	/**
	 * Returns the acc closure master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accClosureMasterSid the primary key of the acc closure master
	 * @return the acc closure master, or <code>null</code> if a acc closure master with the primary key could not be found
	 */
	@Override
	public AccClosureMaster fetchByPrimaryKey(int accClosureMasterSid) {
		return fetchByPrimaryKey((Serializable)accClosureMasterSid);
	}

	@Override
	public Map<Serializable, AccClosureMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccClosureMaster> map = new HashMap<Serializable, AccClosureMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccClosureMaster accClosureMaster = fetchByPrimaryKey(primaryKey);

			if (accClosureMaster != null) {
				map.put(primaryKey, accClosureMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccClosureMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCCLOSUREMASTER_WHERE_PKS_IN);

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

			for (AccClosureMaster accClosureMaster : (List<AccClosureMaster>)q.list()) {
				map.put(accClosureMaster.getPrimaryKeyObj(), accClosureMaster);

				cacheResult(accClosureMaster);

				uncachedPrimaryKeys.remove(accClosureMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccClosureMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccClosureMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the acc closure masters.
	 *
	 * @return the acc closure masters
	 */
	@Override
	public List<AccClosureMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the acc closure masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure masters
	 * @param end the upper bound of the range of acc closure masters (not inclusive)
	 * @return the range of acc closure masters
	 */
	@Override
	public List<AccClosureMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the acc closure masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure masters
	 * @param end the upper bound of the range of acc closure masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of acc closure masters
	 */
	@Override
	public List<AccClosureMaster> findAll(int start, int end,
		OrderByComparator<AccClosureMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the acc closure masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccClosureMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of acc closure masters
	 * @param end the upper bound of the range of acc closure masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of acc closure masters
	 */
	@Override
	public List<AccClosureMaster> findAll(int start, int end,
		OrderByComparator<AccClosureMaster> orderByComparator,
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

		List<AccClosureMaster> list = null;

		if (retrieveFromCache) {
			list = (List<AccClosureMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCCLOSUREMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCCLOSUREMASTER;

				if (pagination) {
					sql = sql.concat(AccClosureMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccClosureMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccClosureMaster>)QueryUtil.list(q,
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
	 * Removes all the acc closure masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccClosureMaster accClosureMaster : findAll()) {
			remove(accClosureMaster);
		}
	}

	/**
	 * Returns the number of acc closure masters.
	 *
	 * @return the number of acc closure masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCCLOSUREMASTER);

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
		return AccClosureMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the acc closure master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccClosureMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACCCLOSUREMASTER = "SELECT accClosureMaster FROM AccClosureMaster accClosureMaster";
	private static final String _SQL_SELECT_ACCCLOSUREMASTER_WHERE_PKS_IN = "SELECT accClosureMaster FROM AccClosureMaster accClosureMaster WHERE ACC_CLOSURE_MASTER_SID IN (";
	private static final String _SQL_COUNT_ACCCLOSUREMASTER = "SELECT COUNT(accClosureMaster) FROM AccClosureMaster accClosureMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accClosureMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccClosureMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccClosureMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"saveFlag", "accountNo", "toDate", "itemMasterSid",
				"description", "reportName", "rsType", "productIdentifier",
				"modifiedDate", "workflowStatus", "moduleType", "fromDate",
				"contractType", "glCompanyMasterSid", "createdDate", "createdBy",
				"contractMasterSid", "accrualPeriod", "companyGroupSid",
				"accClosureMasterSid", "rsCategory", "adjustmentType",
				"modifiedBy", "itemGroupSid", "rebateProgramType"
			});
}