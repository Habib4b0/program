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

import com.stpl.app.exception.NoSuchAccrualMasterException;
import com.stpl.app.model.AccrualMaster;
import com.stpl.app.model.impl.AccrualMasterImpl;
import com.stpl.app.model.impl.AccrualMasterModelImpl;
import com.stpl.app.service.persistence.AccrualMasterPersistence;

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
 * The persistence implementation for the accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualMasterPersistence
 * @see com.stpl.app.service.persistence.AccrualMasterUtil
 * @generated
 */
@ProviderType
public class AccrualMasterPersistenceImpl extends BasePersistenceImpl<AccrualMaster>
	implements AccrualMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccrualMasterUtil} to access the accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccrualMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterModelImpl.FINDER_CACHE_ENABLED,
			AccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterModelImpl.FINDER_CACHE_ENABLED,
			AccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccrualMasterPersistenceImpl() {
		setModelClass(AccrualMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("recordCreatedDate", "RECORD_CREATED_DATE");
			dbColumnNames.put("postingIndicator", "POSTING_INDICATOR");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("accrualPeriodEndDate", "ACCRUAL_PERIOD_END_DATE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("salesMasterId", "SALES_MASTER_ID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("documentType", "DOCUMENT_TYPE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("acctIdentCodeQualifier",
				"ACCT_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("compIdentCodeQualifier",
				"COMP_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("subLedger", "SUB_LEDGER");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("accrualMasterSid", "ACCRUAL_MASTER_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("itemIdentCodeQualifier",
				"ITEM_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("accrualPeriodStartDate",
				"ACCRUAL_PERIOD_START_DATE");
			dbColumnNames.put("subLedgerType", "SUB_LEDGER_TYPE");
			dbColumnNames.put("programNo", "PROGRAM_NO");
			dbColumnNames.put("glCompanyName", "GL_COMPANY_NAME");
			dbColumnNames.put("categoryId", "CATEGORY_ID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("accrualId", "ACCRUAL_ID");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("accrualType", "ACCRUAL_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("postingDate", "POSTING_DATE");
			dbColumnNames.put("glDate", "GL_DATE");
			dbColumnNames.put("glAmount", "GL_AMOUNT");
			dbColumnNames.put("companyCostCenter", "COMPANY_COST_CENTER");
			dbColumnNames.put("glAccount", "GL_ACCOUNT");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("programType", "PROGRAM_TYPE");
			dbColumnNames.put("contractNo", "CONTRACT_NO");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the accrual master in the entity cache if it is enabled.
	 *
	 * @param accrualMaster the accrual master
	 */
	@Override
	public void cacheResult(AccrualMaster accrualMaster) {
		entityCache.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterImpl.class, accrualMaster.getPrimaryKey(),
			accrualMaster);

		accrualMaster.resetOriginalValues();
	}

	/**
	 * Caches the accrual masters in the entity cache if it is enabled.
	 *
	 * @param accrualMasters the accrual masters
	 */
	@Override
	public void cacheResult(List<AccrualMaster> accrualMasters) {
		for (AccrualMaster accrualMaster : accrualMasters) {
			if (entityCache.getResult(
						AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccrualMasterImpl.class, accrualMaster.getPrimaryKey()) == null) {
				cacheResult(accrualMaster);
			}
			else {
				accrualMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all accrual masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccrualMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the accrual master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccrualMaster accrualMaster) {
		entityCache.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterImpl.class, accrualMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccrualMaster> accrualMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccrualMaster accrualMaster : accrualMasters) {
			entityCache.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccrualMasterImpl.class, accrualMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new accrual master with the primary key. Does not add the accrual master to the database.
	 *
	 * @param accrualMasterSid the primary key for the new accrual master
	 * @return the new accrual master
	 */
	@Override
	public AccrualMaster create(int accrualMasterSid) {
		AccrualMaster accrualMaster = new AccrualMasterImpl();

		accrualMaster.setNew(true);
		accrualMaster.setPrimaryKey(accrualMasterSid);

		return accrualMaster;
	}

	/**
	 * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accrualMasterSid the primary key of the accrual master
	 * @return the accrual master that was removed
	 * @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster remove(int accrualMasterSid)
		throws NoSuchAccrualMasterException {
		return remove((Serializable)accrualMasterSid);
	}

	/**
	 * Removes the accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the accrual master
	 * @return the accrual master that was removed
	 * @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster remove(Serializable primaryKey)
		throws NoSuchAccrualMasterException {
		Session session = null;

		try {
			session = openSession();

			AccrualMaster accrualMaster = (AccrualMaster)session.get(AccrualMasterImpl.class,
					primaryKey);

			if (accrualMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accrualMaster);
		}
		catch (NoSuchAccrualMasterException nsee) {
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
	protected AccrualMaster removeImpl(AccrualMaster accrualMaster) {
		accrualMaster = toUnwrappedModel(accrualMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accrualMaster)) {
				accrualMaster = (AccrualMaster)session.get(AccrualMasterImpl.class,
						accrualMaster.getPrimaryKeyObj());
			}

			if (accrualMaster != null) {
				session.delete(accrualMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accrualMaster != null) {
			clearCache(accrualMaster);
		}

		return accrualMaster;
	}

	@Override
	public AccrualMaster updateImpl(AccrualMaster accrualMaster) {
		accrualMaster = toUnwrappedModel(accrualMaster);

		boolean isNew = accrualMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (accrualMaster.isNew()) {
				session.save(accrualMaster);

				accrualMaster.setNew(false);
			}
			else {
				accrualMaster = (AccrualMaster)session.merge(accrualMaster);
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

		entityCache.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			AccrualMasterImpl.class, accrualMaster.getPrimaryKey(),
			accrualMaster, false);

		accrualMaster.resetOriginalValues();

		return accrualMaster;
	}

	protected AccrualMaster toUnwrappedModel(AccrualMaster accrualMaster) {
		if (accrualMaster instanceof AccrualMasterImpl) {
			return accrualMaster;
		}

		AccrualMasterImpl accrualMasterImpl = new AccrualMasterImpl();

		accrualMasterImpl.setNew(accrualMaster.isNew());
		accrualMasterImpl.setPrimaryKey(accrualMaster.getPrimaryKey());

		accrualMasterImpl.setAccountId(accrualMaster.getAccountId());
		accrualMasterImpl.setRecordCreatedDate(accrualMaster.getRecordCreatedDate());
		accrualMasterImpl.setPostingIndicator(accrualMaster.getPostingIndicator());
		accrualMasterImpl.setBrandName(accrualMaster.getBrandName());
		accrualMasterImpl.setAccrualPeriodEndDate(accrualMaster.getAccrualPeriodEndDate());
		accrualMasterImpl.setModifiedDate(accrualMaster.getModifiedDate());
		accrualMasterImpl.setSalesMasterId(accrualMaster.getSalesMasterId());
		accrualMasterImpl.setSource(accrualMaster.getSource());
		accrualMasterImpl.setContractMasterSid(accrualMaster.getContractMasterSid());
		accrualMasterImpl.setDocumentType(accrualMaster.getDocumentType());
		accrualMasterImpl.setInboundStatus(accrualMaster.getInboundStatus());
		accrualMasterImpl.setModifiedBy(accrualMaster.getModifiedBy());
		accrualMasterImpl.setAcctIdentCodeQualifier(accrualMaster.getAcctIdentCodeQualifier());
		accrualMasterImpl.setCompIdentCodeQualifier(accrualMaster.getCompIdentCodeQualifier());
		accrualMasterImpl.setCompanyMasterSid(accrualMaster.getCompanyMasterSid());
		accrualMasterImpl.setContractName(accrualMaster.getContractName());
		accrualMasterImpl.setAccountNo(accrualMaster.getAccountNo());
		accrualMasterImpl.setAccountName(accrualMaster.getAccountName());
		accrualMasterImpl.setProvisionId(accrualMaster.getProvisionId());
		accrualMasterImpl.setAmount(accrualMaster.getAmount());
		accrualMasterImpl.setSubLedger(accrualMaster.getSubLedger());
		accrualMasterImpl.setRecordLockStatus(accrualMaster.isRecordLockStatus());
		accrualMasterImpl.setCompanyNo(accrualMaster.getCompanyNo());
		accrualMasterImpl.setItemName(accrualMaster.getItemName());
		accrualMasterImpl.setRsModelSid(accrualMaster.getRsModelSid());
		accrualMasterImpl.setAccrualMasterSid(accrualMaster.getAccrualMasterSid());
		accrualMasterImpl.setItemMasterSid(accrualMaster.getItemMasterSid());
		accrualMasterImpl.setItemId(accrualMaster.getItemId());
		accrualMasterImpl.setBrandMasterSid(accrualMaster.getBrandMasterSid());
		accrualMasterImpl.setItemIdentCodeQualifier(accrualMaster.getItemIdentCodeQualifier());
		accrualMasterImpl.setGlCompanyMasterSid(accrualMaster.getGlCompanyMasterSid());
		accrualMasterImpl.setCreatedBy(accrualMaster.getCreatedBy());
		accrualMasterImpl.setCreatedDate(accrualMaster.getCreatedDate());
		accrualMasterImpl.setAccrualPeriodStartDate(accrualMaster.getAccrualPeriodStartDate());
		accrualMasterImpl.setSubLedgerType(accrualMaster.getSubLedgerType());
		accrualMasterImpl.setProgramNo(accrualMaster.getProgramNo());
		accrualMasterImpl.setGlCompanyName(accrualMaster.getGlCompanyName());
		accrualMasterImpl.setCategoryId(accrualMaster.getCategoryId());
		accrualMasterImpl.setItemNo(accrualMaster.getItemNo());
		accrualMasterImpl.setContractId(accrualMaster.getContractId());
		accrualMasterImpl.setAccrualId(accrualMaster.getAccrualId());
		accrualMasterImpl.setCompanyStringId(accrualMaster.getCompanyStringId());
		accrualMasterImpl.setAccrualType(accrualMaster.getAccrualType());
		accrualMasterImpl.setBrandId(accrualMaster.getBrandId());
		accrualMasterImpl.setPostingDate(accrualMaster.getPostingDate());
		accrualMasterImpl.setGlDate(accrualMaster.getGlDate());
		accrualMasterImpl.setGlAmount(accrualMaster.getGlAmount());
		accrualMasterImpl.setCompanyCostCenter(accrualMaster.getCompanyCostCenter());
		accrualMasterImpl.setGlAccount(accrualMaster.getGlAccount());
		accrualMasterImpl.setBatchId(accrualMaster.getBatchId());
		accrualMasterImpl.setProgramType(accrualMaster.getProgramType());
		accrualMasterImpl.setContractNo(accrualMaster.getContractNo());

		return accrualMasterImpl;
	}

	/**
	 * Returns the accrual master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the accrual master
	 * @return the accrual master
	 * @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccrualMasterException {
		AccrualMaster accrualMaster = fetchByPrimaryKey(primaryKey);

		if (accrualMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accrualMaster;
	}

	/**
	 * Returns the accrual master with the primary key or throws a {@link NoSuchAccrualMasterException} if it could not be found.
	 *
	 * @param accrualMasterSid the primary key of the accrual master
	 * @return the accrual master
	 * @throws NoSuchAccrualMasterException if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster findByPrimaryKey(int accrualMasterSid)
		throws NoSuchAccrualMasterException {
		return findByPrimaryKey((Serializable)accrualMasterSid);
	}

	/**
	 * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the accrual master
	 * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
				AccrualMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccrualMaster accrualMaster = (AccrualMaster)serializable;

		if (accrualMaster == null) {
			Session session = null;

			try {
				session = openSession();

				accrualMaster = (AccrualMaster)session.get(AccrualMasterImpl.class,
						primaryKey);

				if (accrualMaster != null) {
					cacheResult(accrualMaster);
				}
				else {
					entityCache.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
						AccrualMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccrualMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accrualMaster;
	}

	/**
	 * Returns the accrual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accrualMasterSid the primary key of the accrual master
	 * @return the accrual master, or <code>null</code> if a accrual master with the primary key could not be found
	 */
	@Override
	public AccrualMaster fetchByPrimaryKey(int accrualMasterSid) {
		return fetchByPrimaryKey((Serializable)accrualMasterSid);
	}

	@Override
	public Map<Serializable, AccrualMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccrualMaster> map = new HashMap<Serializable, AccrualMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccrualMaster accrualMaster = fetchByPrimaryKey(primaryKey);

			if (accrualMaster != null) {
				map.put(primaryKey, accrualMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccrualMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccrualMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCRUALMASTER_WHERE_PKS_IN);

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

			for (AccrualMaster accrualMaster : (List<AccrualMaster>)q.list()) {
				map.put(accrualMaster.getPrimaryKeyObj(), accrualMaster);

				cacheResult(accrualMaster);

				uncachedPrimaryKeys.remove(accrualMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					AccrualMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the accrual masters.
	 *
	 * @return the accrual masters
	 */
	@Override
	public List<AccrualMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual masters
	 * @param end the upper bound of the range of accrual masters (not inclusive)
	 * @return the range of accrual masters
	 */
	@Override
	public List<AccrualMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual masters
	 * @param end the upper bound of the range of accrual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of accrual masters
	 */
	@Override
	public List<AccrualMaster> findAll(int start, int end,
		OrderByComparator<AccrualMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual masters
	 * @param end the upper bound of the range of accrual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of accrual masters
	 */
	@Override
	public List<AccrualMaster> findAll(int start, int end,
		OrderByComparator<AccrualMaster> orderByComparator,
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

		List<AccrualMaster> list = null;

		if (retrieveFromCache) {
			list = (List<AccrualMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCRUALMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCRUALMASTER;

				if (pagination) {
					sql = sql.concat(AccrualMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccrualMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccrualMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the accrual masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccrualMaster accrualMaster : findAll()) {
			remove(accrualMaster);
		}
	}

	/**
	 * Returns the number of accrual masters.
	 *
	 * @return the number of accrual masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCRUALMASTER);

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
		return AccrualMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the accrual master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccrualMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACCRUALMASTER = "SELECT accrualMaster FROM AccrualMaster accrualMaster";
	private static final String _SQL_SELECT_ACCRUALMASTER_WHERE_PKS_IN = "SELECT accrualMaster FROM AccrualMaster accrualMaster WHERE ACCRUAL_MASTER_SID IN (";
	private static final String _SQL_COUNT_ACCRUALMASTER = "SELECT COUNT(accrualMaster) FROM AccrualMaster accrualMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accrualMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccrualMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccrualMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"accountId", "recordCreatedDate", "postingIndicator",
				"brandName", "accrualPeriodEndDate", "modifiedDate",
				"salesMasterId", "source", "contractMasterSid", "documentType",
				"inboundStatus", "modifiedBy", "acctIdentCodeQualifier",
				"compIdentCodeQualifier", "companyMasterSid", "contractName",
				"accountNo", "accountName", "provisionId", "amount", "subLedger",
				"recordLockStatus", "companyNo", "itemName", "rsModelSid",
				"accrualMasterSid", "itemMasterSid", "itemId", "brandMasterSid",
				"itemIdentCodeQualifier", "glCompanyMasterSid", "createdBy",
				"createdDate", "accrualPeriodStartDate", "subLedgerType",
				"programNo", "glCompanyName", "categoryId", "itemNo",
				"contractId", "accrualId", "companyStringId", "accrualType",
				"brandId", "postingDate", "glDate", "glAmount",
				"companyCostCenter", "glAccount", "batchId", "programType",
				"contractNo"
			});
}