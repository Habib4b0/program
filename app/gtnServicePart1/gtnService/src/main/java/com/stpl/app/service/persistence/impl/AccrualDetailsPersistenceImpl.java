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

import com.stpl.app.exception.NoSuchAccrualDetailsException;
import com.stpl.app.model.AccrualDetails;
import com.stpl.app.model.impl.AccrualDetailsImpl;
import com.stpl.app.model.impl.AccrualDetailsModelImpl;
import com.stpl.app.service.persistence.AccrualDetailsPersistence;

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
 * The persistence implementation for the accrual details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AccrualDetailsPersistence
 * @see com.stpl.app.service.persistence.AccrualDetailsUtil
 * @generated
 */
@ProviderType
public class AccrualDetailsPersistenceImpl extends BasePersistenceImpl<AccrualDetails>
	implements AccrualDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AccrualDetailsUtil} to access the accrual details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AccrualDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsModelImpl.FINDER_CACHE_ENABLED,
			AccrualDetailsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsModelImpl.FINDER_CACHE_ENABLED,
			AccrualDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public AccrualDetailsPersistenceImpl() {
		setModelClass(AccrualDetails.class);

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
			dbColumnNames.put("accrualDetailsSid", "ACCRUAL_DETAILS_SID");
			dbColumnNames.put("documentType", "DOCUMENT_TYPE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("companyMasterSid", "COMPANY_MASTER_SID");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("companyStringIdentifierCodeQualifier",
				"COMPANY_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("subLedger", "SUB_LEDGER");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("companyNo", "COMPANY_NO");
			dbColumnNames.put("postingStatus", "POSTING_STATUS");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("glCompanyMasterSid", "GL_COMPANY_MASTER_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("accrualPeriodStartDate",
				"ACCRUAL_PERIOD_START_DATE");
			dbColumnNames.put("subLedgerType", "SUB_LEDGER_TYPE");
			dbColumnNames.put("programNo", "PROGRAM_NO");
			dbColumnNames.put("categoryId", "CATEGORY_ID");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("deductionSubType", "DEDUCTION_SUB_TYPE");
			dbColumnNames.put("acctIdentifierCodeQualifier",
				"ACCT_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("accrualId", "ACCRUAL_ID");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("accrualType", "ACCRUAL_TYPE");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("postingDate", "POSTING_DATE");
			dbColumnNames.put("glDate", "GL_DATE");
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
	 * Caches the accrual details in the entity cache if it is enabled.
	 *
	 * @param accrualDetails the accrual details
	 */
	@Override
	public void cacheResult(AccrualDetails accrualDetails) {
		entityCache.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsImpl.class, accrualDetails.getPrimaryKey(),
			accrualDetails);

		accrualDetails.resetOriginalValues();
	}

	/**
	 * Caches the accrual detailses in the entity cache if it is enabled.
	 *
	 * @param accrualDetailses the accrual detailses
	 */
	@Override
	public void cacheResult(List<AccrualDetails> accrualDetailses) {
		for (AccrualDetails accrualDetails : accrualDetailses) {
			if (entityCache.getResult(
						AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AccrualDetailsImpl.class, accrualDetails.getPrimaryKey()) == null) {
				cacheResult(accrualDetails);
			}
			else {
				accrualDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all accrual detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AccrualDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the accrual details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AccrualDetails accrualDetails) {
		entityCache.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsImpl.class, accrualDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AccrualDetails> accrualDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AccrualDetails accrualDetails : accrualDetailses) {
			entityCache.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AccrualDetailsImpl.class, accrualDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new accrual details with the primary key. Does not add the accrual details to the database.
	 *
	 * @param accrualDetailsSid the primary key for the new accrual details
	 * @return the new accrual details
	 */
	@Override
	public AccrualDetails create(int accrualDetailsSid) {
		AccrualDetails accrualDetails = new AccrualDetailsImpl();

		accrualDetails.setNew(true);
		accrualDetails.setPrimaryKey(accrualDetailsSid);

		return accrualDetails;
	}

	/**
	 * Removes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accrualDetailsSid the primary key of the accrual details
	 * @return the accrual details that was removed
	 * @throws NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails remove(int accrualDetailsSid)
		throws NoSuchAccrualDetailsException {
		return remove((Serializable)accrualDetailsSid);
	}

	/**
	 * Removes the accrual details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the accrual details
	 * @return the accrual details that was removed
	 * @throws NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails remove(Serializable primaryKey)
		throws NoSuchAccrualDetailsException {
		Session session = null;

		try {
			session = openSession();

			AccrualDetails accrualDetails = (AccrualDetails)session.get(AccrualDetailsImpl.class,
					primaryKey);

			if (accrualDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccrualDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(accrualDetails);
		}
		catch (NoSuchAccrualDetailsException nsee) {
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
	protected AccrualDetails removeImpl(AccrualDetails accrualDetails) {
		accrualDetails = toUnwrappedModel(accrualDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(accrualDetails)) {
				accrualDetails = (AccrualDetails)session.get(AccrualDetailsImpl.class,
						accrualDetails.getPrimaryKeyObj());
			}

			if (accrualDetails != null) {
				session.delete(accrualDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (accrualDetails != null) {
			clearCache(accrualDetails);
		}

		return accrualDetails;
	}

	@Override
	public AccrualDetails updateImpl(AccrualDetails accrualDetails) {
		accrualDetails = toUnwrappedModel(accrualDetails);

		boolean isNew = accrualDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (accrualDetails.isNew()) {
				session.save(accrualDetails);

				accrualDetails.setNew(false);
			}
			else {
				accrualDetails = (AccrualDetails)session.merge(accrualDetails);
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

		entityCache.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
			AccrualDetailsImpl.class, accrualDetails.getPrimaryKey(),
			accrualDetails, false);

		accrualDetails.resetOriginalValues();

		return accrualDetails;
	}

	protected AccrualDetails toUnwrappedModel(AccrualDetails accrualDetails) {
		if (accrualDetails instanceof AccrualDetailsImpl) {
			return accrualDetails;
		}

		AccrualDetailsImpl accrualDetailsImpl = new AccrualDetailsImpl();

		accrualDetailsImpl.setNew(accrualDetails.isNew());
		accrualDetailsImpl.setPrimaryKey(accrualDetails.getPrimaryKey());

		accrualDetailsImpl.setAccountId(accrualDetails.getAccountId());
		accrualDetailsImpl.setRecordCreatedDate(accrualDetails.getRecordCreatedDate());
		accrualDetailsImpl.setPostingIndicator(accrualDetails.getPostingIndicator());
		accrualDetailsImpl.setBrandName(accrualDetails.getBrandName());
		accrualDetailsImpl.setAccrualPeriodEndDate(accrualDetails.getAccrualPeriodEndDate());
		accrualDetailsImpl.setModifiedDate(accrualDetails.getModifiedDate());
		accrualDetailsImpl.setSalesMasterId(accrualDetails.getSalesMasterId());
		accrualDetailsImpl.setSource(accrualDetails.getSource());
		accrualDetailsImpl.setContractMasterSid(accrualDetails.getContractMasterSid());
		accrualDetailsImpl.setAccrualDetailsSid(accrualDetails.getAccrualDetailsSid());
		accrualDetailsImpl.setDocumentType(accrualDetails.getDocumentType());
		accrualDetailsImpl.setInboundStatus(accrualDetails.getInboundStatus());
		accrualDetailsImpl.setModifiedBy(accrualDetails.getModifiedBy());
		accrualDetailsImpl.setDeductionType(accrualDetails.getDeductionType());
		accrualDetailsImpl.setCompanyMasterSid(accrualDetails.getCompanyMasterSid());
		accrualDetailsImpl.setContractName(accrualDetails.getContractName());
		accrualDetailsImpl.setAccountNo(accrualDetails.getAccountNo());
		accrualDetailsImpl.setAccountName(accrualDetails.getAccountName());
		accrualDetailsImpl.setVersionNo(accrualDetails.getVersionNo());
		accrualDetailsImpl.setProvisionId(accrualDetails.getProvisionId());
		accrualDetailsImpl.setCompanyStringIdentifierCodeQualifier(accrualDetails.getCompanyStringIdentifierCodeQualifier());
		accrualDetailsImpl.setAmount(accrualDetails.getAmount());
		accrualDetailsImpl.setSubLedger(accrualDetails.getSubLedger());
		accrualDetailsImpl.setRecordLockStatus(accrualDetails.isRecordLockStatus());
		accrualDetailsImpl.setItemIdentifierCodeQualifier(accrualDetails.getItemIdentifierCodeQualifier());
		accrualDetailsImpl.setCompanyNo(accrualDetails.getCompanyNo());
		accrualDetailsImpl.setPostingStatus(accrualDetails.getPostingStatus());
		accrualDetailsImpl.setItemName(accrualDetails.getItemName());
		accrualDetailsImpl.setRsModelSid(accrualDetails.getRsModelSid());
		accrualDetailsImpl.setItemMasterSid(accrualDetails.getItemMasterSid());
		accrualDetailsImpl.setItemId(accrualDetails.getItemId());
		accrualDetailsImpl.setBrandMasterSid(accrualDetails.getBrandMasterSid());
		accrualDetailsImpl.setGlCompanyMasterSid(accrualDetails.getGlCompanyMasterSid());
		accrualDetailsImpl.setCreatedBy(accrualDetails.getCreatedBy());
		accrualDetailsImpl.setCreatedDate(accrualDetails.getCreatedDate());
		accrualDetailsImpl.setAccrualPeriodStartDate(accrualDetails.getAccrualPeriodStartDate());
		accrualDetailsImpl.setSubLedgerType(accrualDetails.getSubLedgerType());
		accrualDetailsImpl.setProgramNo(accrualDetails.getProgramNo());
		accrualDetailsImpl.setCategoryId(accrualDetails.getCategoryId());
		accrualDetailsImpl.setItemNo(accrualDetails.getItemNo());
		accrualDetailsImpl.setDeductionSubType(accrualDetails.getDeductionSubType());
		accrualDetailsImpl.setAcctIdentifierCodeQualifier(accrualDetails.getAcctIdentifierCodeQualifier());
		accrualDetailsImpl.setContractId(accrualDetails.getContractId());
		accrualDetailsImpl.setAccrualId(accrualDetails.getAccrualId());
		accrualDetailsImpl.setCompanyStringId(accrualDetails.getCompanyStringId());
		accrualDetailsImpl.setAccrualType(accrualDetails.getAccrualType());
		accrualDetailsImpl.setBrandId(accrualDetails.getBrandId());
		accrualDetailsImpl.setPostingDate(accrualDetails.getPostingDate());
		accrualDetailsImpl.setGlDate(accrualDetails.getGlDate());
		accrualDetailsImpl.setCompanyCostCenter(accrualDetails.getCompanyCostCenter());
		accrualDetailsImpl.setGlAccount(accrualDetails.getGlAccount());
		accrualDetailsImpl.setBatchId(accrualDetails.getBatchId());
		accrualDetailsImpl.setProgramType(accrualDetails.getProgramType());
		accrualDetailsImpl.setContractNo(accrualDetails.getContractNo());

		return accrualDetailsImpl;
	}

	/**
	 * Returns the accrual details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the accrual details
	 * @return the accrual details
	 * @throws NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAccrualDetailsException {
		AccrualDetails accrualDetails = fetchByPrimaryKey(primaryKey);

		if (accrualDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAccrualDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return accrualDetails;
	}

	/**
	 * Returns the accrual details with the primary key or throws a {@link NoSuchAccrualDetailsException} if it could not be found.
	 *
	 * @param accrualDetailsSid the primary key of the accrual details
	 * @return the accrual details
	 * @throws NoSuchAccrualDetailsException if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails findByPrimaryKey(int accrualDetailsSid)
		throws NoSuchAccrualDetailsException {
		return findByPrimaryKey((Serializable)accrualDetailsSid);
	}

	/**
	 * Returns the accrual details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the accrual details
	 * @return the accrual details, or <code>null</code> if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
				AccrualDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		AccrualDetails accrualDetails = (AccrualDetails)serializable;

		if (accrualDetails == null) {
			Session session = null;

			try {
				session = openSession();

				accrualDetails = (AccrualDetails)session.get(AccrualDetailsImpl.class,
						primaryKey);

				if (accrualDetails != null) {
					cacheResult(accrualDetails);
				}
				else {
					entityCache.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
						AccrualDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccrualDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return accrualDetails;
	}

	/**
	 * Returns the accrual details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accrualDetailsSid the primary key of the accrual details
	 * @return the accrual details, or <code>null</code> if a accrual details with the primary key could not be found
	 */
	@Override
	public AccrualDetails fetchByPrimaryKey(int accrualDetailsSid) {
		return fetchByPrimaryKey((Serializable)accrualDetailsSid);
	}

	@Override
	public Map<Serializable, AccrualDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AccrualDetails> map = new HashMap<Serializable, AccrualDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AccrualDetails accrualDetails = fetchByPrimaryKey(primaryKey);

			if (accrualDetails != null) {
				map.put(primaryKey, accrualDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccrualDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (AccrualDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ACCRUALDETAILS_WHERE_PKS_IN);

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

			for (AccrualDetails accrualDetails : (List<AccrualDetails>)q.list()) {
				map.put(accrualDetails.getPrimaryKeyObj(), accrualDetails);

				cacheResult(accrualDetails);

				uncachedPrimaryKeys.remove(accrualDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(AccrualDetailsModelImpl.ENTITY_CACHE_ENABLED,
					AccrualDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the accrual detailses.
	 *
	 * @return the accrual detailses
	 */
	@Override
	public List<AccrualDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the accrual detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual detailses
	 * @param end the upper bound of the range of accrual detailses (not inclusive)
	 * @return the range of accrual detailses
	 */
	@Override
	public List<AccrualDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the accrual detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual detailses
	 * @param end the upper bound of the range of accrual detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of accrual detailses
	 */
	@Override
	public List<AccrualDetails> findAll(int start, int end,
		OrderByComparator<AccrualDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the accrual detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccrualDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of accrual detailses
	 * @param end the upper bound of the range of accrual detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of accrual detailses
	 */
	@Override
	public List<AccrualDetails> findAll(int start, int end,
		OrderByComparator<AccrualDetails> orderByComparator,
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

		List<AccrualDetails> list = null;

		if (retrieveFromCache) {
			list = (List<AccrualDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ACCRUALDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ACCRUALDETAILS;

				if (pagination) {
					sql = sql.concat(AccrualDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AccrualDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AccrualDetails>)QueryUtil.list(q,
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
	 * Removes all the accrual detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AccrualDetails accrualDetails : findAll()) {
			remove(accrualDetails);
		}
	}

	/**
	 * Returns the number of accrual detailses.
	 *
	 * @return the number of accrual detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ACCRUALDETAILS);

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
		return AccrualDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the accrual details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(AccrualDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_ACCRUALDETAILS = "SELECT accrualDetails FROM AccrualDetails accrualDetails";
	private static final String _SQL_SELECT_ACCRUALDETAILS_WHERE_PKS_IN = "SELECT accrualDetails FROM AccrualDetails accrualDetails WHERE ACCRUAL_DETAILS_SID IN (";
	private static final String _SQL_COUNT_ACCRUALDETAILS = "SELECT COUNT(accrualDetails) FROM AccrualDetails accrualDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "accrualDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AccrualDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(AccrualDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"accountId", "recordCreatedDate", "postingIndicator",
				"brandName", "accrualPeriodEndDate", "modifiedDate",
				"salesMasterId", "source", "contractMasterSid",
				"accrualDetailsSid", "documentType", "inboundStatus",
				"modifiedBy", "deductionType", "companyMasterSid",
				"contractName", "accountNo", "accountName", "versionNo",
				"provisionId", "companyStringIdentifierCodeQualifier", "amount",
				"subLedger", "recordLockStatus", "itemIdentifierCodeQualifier",
				"companyNo", "postingStatus", "itemName", "rsModelSid",
				"itemMasterSid", "itemId", "brandMasterSid",
				"glCompanyMasterSid", "createdBy", "createdDate",
				"accrualPeriodStartDate", "subLedgerType", "programNo",
				"categoryId", "itemNo", "deductionSubType",
				"acctIdentifierCodeQualifier", "contractId", "accrualId",
				"companyStringId", "accrualType", "brandId", "postingDate",
				"glDate", "companyCostCenter", "glAccount", "batchId",
				"programType", "contractNo"
			});
}