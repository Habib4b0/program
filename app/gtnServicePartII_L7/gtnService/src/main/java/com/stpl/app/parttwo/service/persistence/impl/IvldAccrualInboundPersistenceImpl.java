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
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.parttwo.exception.NoSuchIvldAccrualInboundException;
import com.stpl.app.parttwo.model.IvldAccrualInbound;
import com.stpl.app.parttwo.model.impl.IvldAccrualInboundImpl;
import com.stpl.app.parttwo.model.impl.IvldAccrualInboundModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldAccrualInboundPersistence;

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
 * The persistence implementation for the ivld accrual inbound service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldAccrualInboundPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldAccrualInboundUtil
 * @generated
 */
@ProviderType
public class IvldAccrualInboundPersistenceImpl extends BasePersistenceImpl<IvldAccrualInbound>
	implements IvldAccrualInboundPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldAccrualInboundUtil} to access the ivld accrual inbound persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldAccrualInboundImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED,
			IvldAccrualInboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED,
			IvldAccrualInboundImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldAccrualInboundPersistenceImpl() {
		setModelClass(IvldAccrualInbound.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("recordCreatedDate", "RECORD_CREATED_DATE");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("postingIndicator", "POSTING_INDICATOR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("accrualPeriodEndDate", "ACCRUAL_PERIOD_END_DATE");
			dbColumnNames.put("itemIdentCodeQualifier",
				"ITEM_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("salesMasterId", "SALES_MASTER_ID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("accrualPeriodStartDate",
				"ACCRUAL_PERIOD_START_DATE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("subLedgerType", "SUB_LEDGER_TYPE");
			dbColumnNames.put("programNo", "PROGRAM_NO");
			dbColumnNames.put("documentType", "DOCUMENT_TYPE");
			dbColumnNames.put("accrualIntfid", "ACCRUAL_INTFID");
			dbColumnNames.put("glCompanyName", "GL_COMPANY_NAME");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("categoryId", "CATEGORY_ID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("compIdentCodeQualifier",
				"COMP_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("acctIdentCodeQualifier",
				"ACCT_IDENT_CODE_QUALIFIER");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("accrualId", "ACCRUAL_ID");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("companyId", "COMPANY_ID");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("accrualType", "ACCRUAL_TYPE");
			dbColumnNames.put("postingDate", "POSTING_DATE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("glDate", "GL_DATE");
			dbColumnNames.put("subLedger", "SUB_LEDGER");
			dbColumnNames.put("companyCostCenter", "COMPANY_COST_CENTER");
			dbColumnNames.put("glAccount", "GL_ACCOUNT");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("programType", "PROGRAM_TYPE");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("ivldAccrualInboundSid",
				"IVLD_ACCRUAL_INBOUND_SID");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("brandName", "BRAND_NAME");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
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
	 * Caches the ivld accrual inbound in the entity cache if it is enabled.
	 *
	 * @param ivldAccrualInbound the ivld accrual inbound
	 */
	@Override
	public void cacheResult(IvldAccrualInbound ivldAccrualInbound) {
		entityCache.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey(),
			ivldAccrualInbound);

		ivldAccrualInbound.resetOriginalValues();
	}

	/**
	 * Caches the ivld accrual inbounds in the entity cache if it is enabled.
	 *
	 * @param ivldAccrualInbounds the ivld accrual inbounds
	 */
	@Override
	public void cacheResult(List<IvldAccrualInbound> ivldAccrualInbounds) {
		for (IvldAccrualInbound ivldAccrualInbound : ivldAccrualInbounds) {
			if (entityCache.getResult(
						IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
						IvldAccrualInboundImpl.class,
						ivldAccrualInbound.getPrimaryKey()) == null) {
				cacheResult(ivldAccrualInbound);
			}
			else {
				ivldAccrualInbound.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld accrual inbounds.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldAccrualInboundImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld accrual inbound.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldAccrualInbound ivldAccrualInbound) {
		entityCache.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldAccrualInbound> ivldAccrualInbounds) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldAccrualInbound ivldAccrualInbound : ivldAccrualInbounds) {
			entityCache.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
				IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld accrual inbound with the primary key. Does not add the ivld accrual inbound to the database.
	 *
	 * @param ivldAccrualInboundSid the primary key for the new ivld accrual inbound
	 * @return the new ivld accrual inbound
	 */
	@Override
	public IvldAccrualInbound create(int ivldAccrualInboundSid) {
		IvldAccrualInbound ivldAccrualInbound = new IvldAccrualInboundImpl();

		ivldAccrualInbound.setNew(true);
		ivldAccrualInbound.setPrimaryKey(ivldAccrualInboundSid);

		ivldAccrualInbound.setCompanyId(companyProvider.getCompanyId());

		return ivldAccrualInbound;
	}

	/**
	 * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound that was removed
	 * @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound remove(int ivldAccrualInboundSid)
		throws NoSuchIvldAccrualInboundException {
		return remove((Serializable)ivldAccrualInboundSid);
	}

	/**
	 * Removes the ivld accrual inbound with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound that was removed
	 * @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound remove(Serializable primaryKey)
		throws NoSuchIvldAccrualInboundException {
		Session session = null;

		try {
			session = openSession();

			IvldAccrualInbound ivldAccrualInbound = (IvldAccrualInbound)session.get(IvldAccrualInboundImpl.class,
					primaryKey);

			if (ivldAccrualInbound == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldAccrualInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldAccrualInbound);
		}
		catch (NoSuchIvldAccrualInboundException nsee) {
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
	protected IvldAccrualInbound removeImpl(
		IvldAccrualInbound ivldAccrualInbound) {
		ivldAccrualInbound = toUnwrappedModel(ivldAccrualInbound);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldAccrualInbound)) {
				ivldAccrualInbound = (IvldAccrualInbound)session.get(IvldAccrualInboundImpl.class,
						ivldAccrualInbound.getPrimaryKeyObj());
			}

			if (ivldAccrualInbound != null) {
				session.delete(ivldAccrualInbound);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldAccrualInbound != null) {
			clearCache(ivldAccrualInbound);
		}

		return ivldAccrualInbound;
	}

	@Override
	public IvldAccrualInbound updateImpl(IvldAccrualInbound ivldAccrualInbound) {
		ivldAccrualInbound = toUnwrappedModel(ivldAccrualInbound);

		boolean isNew = ivldAccrualInbound.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldAccrualInbound.isNew()) {
				session.save(ivldAccrualInbound);

				ivldAccrualInbound.setNew(false);
			}
			else {
				ivldAccrualInbound = (IvldAccrualInbound)session.merge(ivldAccrualInbound);
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

		entityCache.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
			IvldAccrualInboundImpl.class, ivldAccrualInbound.getPrimaryKey(),
			ivldAccrualInbound, false);

		ivldAccrualInbound.resetOriginalValues();

		return ivldAccrualInbound;
	}

	protected IvldAccrualInbound toUnwrappedModel(
		IvldAccrualInbound ivldAccrualInbound) {
		if (ivldAccrualInbound instanceof IvldAccrualInboundImpl) {
			return ivldAccrualInbound;
		}

		IvldAccrualInboundImpl ivldAccrualInboundImpl = new IvldAccrualInboundImpl();

		ivldAccrualInboundImpl.setNew(ivldAccrualInbound.isNew());
		ivldAccrualInboundImpl.setPrimaryKey(ivldAccrualInbound.getPrimaryKey());

		ivldAccrualInboundImpl.setRecordCreatedDate(ivldAccrualInbound.getRecordCreatedDate());
		ivldAccrualInboundImpl.setAccountId(ivldAccrualInbound.getAccountId());
		ivldAccrualInboundImpl.setPostingIndicator(ivldAccrualInbound.getPostingIndicator());
		ivldAccrualInboundImpl.setItemId(ivldAccrualInbound.getItemId());
		ivldAccrualInboundImpl.setModifiedDate(ivldAccrualInbound.getModifiedDate());
		ivldAccrualInboundImpl.setAccrualPeriodEndDate(ivldAccrualInbound.getAccrualPeriodEndDate());
		ivldAccrualInboundImpl.setItemIdentCodeQualifier(ivldAccrualInbound.getItemIdentCodeQualifier());
		ivldAccrualInboundImpl.setGlCompanyMasterSid(ivldAccrualInbound.getGlCompanyMasterSid());
		ivldAccrualInboundImpl.setSalesMasterId(ivldAccrualInbound.getSalesMasterId());
		ivldAccrualInboundImpl.setCreatedDate(ivldAccrualInbound.getCreatedDate());
		ivldAccrualInboundImpl.setCreatedBy(ivldAccrualInbound.getCreatedBy());
		ivldAccrualInboundImpl.setSource(ivldAccrualInbound.getSource());
		ivldAccrualInboundImpl.setAccrualPeriodStartDate(ivldAccrualInbound.getAccrualPeriodStartDate());
		ivldAccrualInboundImpl.setAddChgDelIndicator(ivldAccrualInbound.getAddChgDelIndicator());
		ivldAccrualInboundImpl.setSubLedgerType(ivldAccrualInbound.getSubLedgerType());
		ivldAccrualInboundImpl.setProgramNo(ivldAccrualInbound.getProgramNo());
		ivldAccrualInboundImpl.setDocumentType(ivldAccrualInbound.getDocumentType());
		ivldAccrualInboundImpl.setAccrualIntfid(ivldAccrualInbound.getAccrualIntfid());
		ivldAccrualInboundImpl.setGlCompanyName(ivldAccrualInbound.getGlCompanyName());
		ivldAccrualInboundImpl.setErrorCode(ivldAccrualInbound.getErrorCode());
		ivldAccrualInboundImpl.setIntfInsertedDate(ivldAccrualInbound.getIntfInsertedDate());
		ivldAccrualInboundImpl.setModifiedBy(ivldAccrualInbound.getModifiedBy());
		ivldAccrualInboundImpl.setCategoryId(ivldAccrualInbound.getCategoryId());
		ivldAccrualInboundImpl.setItemNo(ivldAccrualInbound.getItemNo());
		ivldAccrualInboundImpl.setReprocessedFlag(ivldAccrualInbound.getReprocessedFlag());
		ivldAccrualInboundImpl.setCompIdentCodeQualifier(ivldAccrualInbound.getCompIdentCodeQualifier());
		ivldAccrualInboundImpl.setAcctIdentCodeQualifier(ivldAccrualInbound.getAcctIdentCodeQualifier());
		ivldAccrualInboundImpl.setContractId(ivldAccrualInbound.getContractId());
		ivldAccrualInboundImpl.setAccountNo(ivldAccrualInbound.getAccountNo());
		ivldAccrualInboundImpl.setAccrualId(ivldAccrualInbound.getAccrualId());
		ivldAccrualInboundImpl.setReasonForFailure(ivldAccrualInbound.getReasonForFailure());
		ivldAccrualInboundImpl.setCompanyId(ivldAccrualInbound.getCompanyId());
		ivldAccrualInboundImpl.setAccountName(ivldAccrualInbound.getAccountName());
		ivldAccrualInboundImpl.setAccrualType(ivldAccrualInbound.getAccrualType());
		ivldAccrualInboundImpl.setPostingDate(ivldAccrualInbound.getPostingDate());
		ivldAccrualInboundImpl.setBrandId(ivldAccrualInbound.getBrandId());
		ivldAccrualInboundImpl.setProvisionId(ivldAccrualInbound.getProvisionId());
		ivldAccrualInboundImpl.setAmount(ivldAccrualInbound.getAmount());
		ivldAccrualInboundImpl.setGlDate(ivldAccrualInbound.getGlDate());
		ivldAccrualInboundImpl.setSubLedger(ivldAccrualInbound.getSubLedger());
		ivldAccrualInboundImpl.setCompanyCostCenter(ivldAccrualInbound.getCompanyCostCenter());
		ivldAccrualInboundImpl.setGlAccount(ivldAccrualInbound.getGlAccount());
		ivldAccrualInboundImpl.setCompanyNo(ivldAccrualInbound.getCompanyNo());
		ivldAccrualInboundImpl.setBatchId(ivldAccrualInbound.getBatchId());
		ivldAccrualInboundImpl.setProgramType(ivldAccrualInbound.getProgramType());
		ivldAccrualInboundImpl.setItemName(ivldAccrualInbound.getItemName());
		ivldAccrualInboundImpl.setErrorField(ivldAccrualInbound.getErrorField());
		ivldAccrualInboundImpl.setIvldAccrualInboundSid(ivldAccrualInbound.getIvldAccrualInboundSid());
		ivldAccrualInboundImpl.setContractNo(ivldAccrualInbound.getContractNo());
		ivldAccrualInboundImpl.setBrandName(ivldAccrualInbound.getBrandName());
		ivldAccrualInboundImpl.setContractName(ivldAccrualInbound.getContractName());
		ivldAccrualInboundImpl.setCheckRecord(ivldAccrualInbound.isCheckRecord());

		return ivldAccrualInboundImpl;
	}

	/**
	 * Returns the ivld accrual inbound with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound
	 * @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldAccrualInboundException {
		IvldAccrualInbound ivldAccrualInbound = fetchByPrimaryKey(primaryKey);

		if (ivldAccrualInbound == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldAccrualInboundException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldAccrualInbound;
	}

	/**
	 * Returns the ivld accrual inbound with the primary key or throws a {@link NoSuchIvldAccrualInboundException} if it could not be found.
	 *
	 * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound
	 * @throws NoSuchIvldAccrualInboundException if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound findByPrimaryKey(int ivldAccrualInboundSid)
		throws NoSuchIvldAccrualInboundException {
		return findByPrimaryKey((Serializable)ivldAccrualInboundSid);
	}

	/**
	 * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
				IvldAccrualInboundImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldAccrualInbound ivldAccrualInbound = (IvldAccrualInbound)serializable;

		if (ivldAccrualInbound == null) {
			Session session = null;

			try {
				session = openSession();

				ivldAccrualInbound = (IvldAccrualInbound)session.get(IvldAccrualInboundImpl.class,
						primaryKey);

				if (ivldAccrualInbound != null) {
					cacheResult(ivldAccrualInbound);
				}
				else {
					entityCache.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
						IvldAccrualInboundImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
					IvldAccrualInboundImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldAccrualInbound;
	}

	/**
	 * Returns the ivld accrual inbound with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldAccrualInboundSid the primary key of the ivld accrual inbound
	 * @return the ivld accrual inbound, or <code>null</code> if a ivld accrual inbound with the primary key could not be found
	 */
	@Override
	public IvldAccrualInbound fetchByPrimaryKey(int ivldAccrualInboundSid) {
		return fetchByPrimaryKey((Serializable)ivldAccrualInboundSid);
	}

	@Override
	public Map<Serializable, IvldAccrualInbound> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldAccrualInbound> map = new HashMap<Serializable, IvldAccrualInbound>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldAccrualInbound ivldAccrualInbound = fetchByPrimaryKey(primaryKey);

			if (ivldAccrualInbound != null) {
				map.put(primaryKey, ivldAccrualInbound);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
					IvldAccrualInboundImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldAccrualInbound)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDACCRUALINBOUND_WHERE_PKS_IN);

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

			for (IvldAccrualInbound ivldAccrualInbound : (List<IvldAccrualInbound>)q.list()) {
				map.put(ivldAccrualInbound.getPrimaryKeyObj(),
					ivldAccrualInbound);

				cacheResult(ivldAccrualInbound);

				uncachedPrimaryKeys.remove(ivldAccrualInbound.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldAccrualInboundModelImpl.ENTITY_CACHE_ENABLED,
					IvldAccrualInboundImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld accrual inbounds.
	 *
	 * @return the ivld accrual inbounds
	 */
	@Override
	public List<IvldAccrualInbound> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld accrual inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld accrual inbounds
	 * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	 * @return the range of ivld accrual inbounds
	 */
	@Override
	public List<IvldAccrualInbound> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld accrual inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld accrual inbounds
	 * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld accrual inbounds
	 */
	@Override
	public List<IvldAccrualInbound> findAll(int start, int end,
		OrderByComparator<IvldAccrualInbound> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld accrual inbounds.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldAccrualInboundModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld accrual inbounds
	 * @param end the upper bound of the range of ivld accrual inbounds (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld accrual inbounds
	 */
	@Override
	public List<IvldAccrualInbound> findAll(int start, int end,
		OrderByComparator<IvldAccrualInbound> orderByComparator,
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

		List<IvldAccrualInbound> list = null;

		if (retrieveFromCache) {
			list = (List<IvldAccrualInbound>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDACCRUALINBOUND);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDACCRUALINBOUND;

				if (pagination) {
					sql = sql.concat(IvldAccrualInboundModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldAccrualInbound>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldAccrualInbound>)QueryUtil.list(q,
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
	 * Removes all the ivld accrual inbounds from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldAccrualInbound ivldAccrualInbound : findAll()) {
			remove(ivldAccrualInbound);
		}
	}

	/**
	 * Returns the number of ivld accrual inbounds.
	 *
	 * @return the number of ivld accrual inbounds
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDACCRUALINBOUND);

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
		return IvldAccrualInboundModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld accrual inbound persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldAccrualInboundImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDACCRUALINBOUND = "SELECT ivldAccrualInbound FROM IvldAccrualInbound ivldAccrualInbound";
	private static final String _SQL_SELECT_IVLDACCRUALINBOUND_WHERE_PKS_IN = "SELECT ivldAccrualInbound FROM IvldAccrualInbound ivldAccrualInbound WHERE IVLD_ACCRUAL_INBOUND_SID IN (";
	private static final String _SQL_COUNT_IVLDACCRUALINBOUND = "SELECT COUNT(ivldAccrualInbound) FROM IvldAccrualInbound ivldAccrualInbound";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldAccrualInbound.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldAccrualInbound exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldAccrualInboundPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"recordCreatedDate", "accountId", "postingIndicator", "itemId",
				"modifiedDate", "accrualPeriodEndDate", "itemIdentCodeQualifier",
				"glCompanyMasterSid", "salesMasterId", "createdDate",
				"createdBy", "source", "accrualPeriodStartDate",
				"addChgDelIndicator", "subLedgerType", "programNo",
				"documentType", "accrualIntfid", "glCompanyName", "errorCode",
				"intfInsertedDate", "modifiedBy", "categoryId", "itemNo",
				"reprocessedFlag", "compIdentCodeQualifier",
				"acctIdentCodeQualifier", "contractId", "accountNo", "accrualId",
				"reasonForFailure", "companyId", "accountName", "accrualType",
				"postingDate", "brandId", "provisionId", "amount", "glDate",
				"subLedger", "companyCostCenter", "glAccount", "companyNo",
				"batchId", "programType", "itemName", "errorField",
				"ivldAccrualInboundSid", "contractNo", "brandName",
				"contractName", "checkRecord"
			});
}