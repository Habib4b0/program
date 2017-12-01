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

import com.stpl.app.exception.NoSuchIvldActualMasterException;
import com.stpl.app.model.IvldActualMaster;
import com.stpl.app.model.impl.IvldActualMasterImpl;
import com.stpl.app.model.impl.IvldActualMasterModelImpl;
import com.stpl.app.service.persistence.IvldActualMasterPersistence;

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
 * The persistence implementation for the ivld actual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldActualMasterPersistence
 * @see com.stpl.app.service.persistence.IvldActualMasterUtil
 * @generated
 */
@ProviderType
public class IvldActualMasterPersistenceImpl extends BasePersistenceImpl<IvldActualMaster>
	implements IvldActualMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldActualMasterUtil} to access the ivld actual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldActualMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldActualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldActualMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldActualMasterPersistenceImpl() {
		setModelClass(IvldActualMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("actualIntfid", "ACTUAL_INTFID");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("programStateCode", "PROGRAM_STATE_CODE");
			dbColumnNames.put("settlementNo", "SETTLEMENT_NO");
			dbColumnNames.put("accrualActualEndDate", "ACCRUAL_ACTUAL_END_DATE");
			dbColumnNames.put("actualId", "ACTUAL_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("divisionId", "DIVISION_ID");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("quantityInclusion", "QUANTITY_INCLUSION");
			dbColumnNames.put("cashPaidDate", "CASH_PAID_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("analysisCode", "ANALYSIS_CODE");
			dbColumnNames.put("accrualActualStartDate",
				"ACCRUAL_ACTUAL_START_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("salesAmount", "SALES_AMOUNT");
			dbColumnNames.put("quantity", "QUANTITY");
			dbColumnNames.put("sentOut", "SENT_OUT");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("marketId", "MARKET_ID");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("settlementMethodNo", "SETTLEMENT_METHOD_NO");
			dbColumnNames.put("itemIdentifierCodeQualifier",
				"ITEM_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("priceAdjustmentName", "PRICE_ADJUSTMENT_NAME");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("recordSequence", "RECORD_SEQUENCE");
			dbColumnNames.put("mandatedDiscountAmount",
				"MANDATED_DISCOUNT_AMOUNT");
			dbColumnNames.put("parentcomDivmktBrandProdkey",
				"PARENTCOM_DIVMKT_BRAND_PRODKEY");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("dispensedDate", "DISPENSED_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("accrualProcessed", "ACCRUAL_PROCESSED");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("invoiceLineNo", "INVOICE_LINE_NO");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("acctIdentifierCodeQualifier",
				"ACCT_IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("comDivMktBrandProdKey",
				"COM_DIV_MKT_BRAND_PROD_KEY");
			dbColumnNames.put("claimIndicator", "CLAIM_INDICATOR");
			dbColumnNames.put("ivldActualMasterSid", "IVLD_ACTUAL_MASTER_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("provisionClaimIndicator",
				"PROVISION_CLAIM_INDICATOR");
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
	 * Caches the ivld actual master in the entity cache if it is enabled.
	 *
	 * @param ivldActualMaster the ivld actual master
	 */
	@Override
	public void cacheResult(IvldActualMaster ivldActualMaster) {
		entityCache.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey(),
			ivldActualMaster);

		ivldActualMaster.resetOriginalValues();
	}

	/**
	 * Caches the ivld actual masters in the entity cache if it is enabled.
	 *
	 * @param ivldActualMasters the ivld actual masters
	 */
	@Override
	public void cacheResult(List<IvldActualMaster> ivldActualMasters) {
		for (IvldActualMaster ivldActualMaster : ivldActualMasters) {
			if (entityCache.getResult(
						IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldActualMasterImpl.class,
						ivldActualMaster.getPrimaryKey()) == null) {
				cacheResult(ivldActualMaster);
			}
			else {
				ivldActualMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld actual masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldActualMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld actual master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldActualMaster ivldActualMaster) {
		entityCache.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldActualMaster> ivldActualMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldActualMaster ivldActualMaster : ivldActualMasters) {
			entityCache.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld actual master with the primary key. Does not add the ivld actual master to the database.
	 *
	 * @param ivldActualMasterSid the primary key for the new ivld actual master
	 * @return the new ivld actual master
	 */
	@Override
	public IvldActualMaster create(int ivldActualMasterSid) {
		IvldActualMaster ivldActualMaster = new IvldActualMasterImpl();

		ivldActualMaster.setNew(true);
		ivldActualMaster.setPrimaryKey(ivldActualMasterSid);

		return ivldActualMaster;
	}

	/**
	 * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldActualMasterSid the primary key of the ivld actual master
	 * @return the ivld actual master that was removed
	 * @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster remove(int ivldActualMasterSid)
		throws NoSuchIvldActualMasterException {
		return remove((Serializable)ivldActualMasterSid);
	}

	/**
	 * Removes the ivld actual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld actual master
	 * @return the ivld actual master that was removed
	 * @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster remove(Serializable primaryKey)
		throws NoSuchIvldActualMasterException {
		Session session = null;

		try {
			session = openSession();

			IvldActualMaster ivldActualMaster = (IvldActualMaster)session.get(IvldActualMasterImpl.class,
					primaryKey);

			if (ivldActualMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldActualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldActualMaster);
		}
		catch (NoSuchIvldActualMasterException nsee) {
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
	protected IvldActualMaster removeImpl(IvldActualMaster ivldActualMaster) {
		ivldActualMaster = toUnwrappedModel(ivldActualMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldActualMaster)) {
				ivldActualMaster = (IvldActualMaster)session.get(IvldActualMasterImpl.class,
						ivldActualMaster.getPrimaryKeyObj());
			}

			if (ivldActualMaster != null) {
				session.delete(ivldActualMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldActualMaster != null) {
			clearCache(ivldActualMaster);
		}

		return ivldActualMaster;
	}

	@Override
	public IvldActualMaster updateImpl(IvldActualMaster ivldActualMaster) {
		ivldActualMaster = toUnwrappedModel(ivldActualMaster);

		boolean isNew = ivldActualMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldActualMaster.isNew()) {
				session.save(ivldActualMaster);

				ivldActualMaster.setNew(false);
			}
			else {
				ivldActualMaster = (IvldActualMaster)session.merge(ivldActualMaster);
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

		entityCache.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldActualMasterImpl.class, ivldActualMaster.getPrimaryKey(),
			ivldActualMaster, false);

		ivldActualMaster.resetOriginalValues();

		return ivldActualMaster;
	}

	protected IvldActualMaster toUnwrappedModel(
		IvldActualMaster ivldActualMaster) {
		if (ivldActualMaster instanceof IvldActualMasterImpl) {
			return ivldActualMaster;
		}

		IvldActualMasterImpl ivldActualMasterImpl = new IvldActualMasterImpl();

		ivldActualMasterImpl.setNew(ivldActualMaster.isNew());
		ivldActualMasterImpl.setPrimaryKey(ivldActualMaster.getPrimaryKey());

		ivldActualMasterImpl.setActualIntfid(ivldActualMaster.getActualIntfid());
		ivldActualMasterImpl.setAccountId(ivldActualMaster.getAccountId());
		ivldActualMasterImpl.setProgramStateCode(ivldActualMaster.getProgramStateCode());
		ivldActualMasterImpl.setSettlementNo(ivldActualMaster.getSettlementNo());
		ivldActualMasterImpl.setAccrualActualEndDate(ivldActualMaster.getAccrualActualEndDate());
		ivldActualMasterImpl.setActualId(ivldActualMaster.getActualId());
		ivldActualMasterImpl.setModifiedDate(ivldActualMaster.getModifiedDate());
		ivldActualMasterImpl.setDivisionId(ivldActualMaster.getDivisionId());
		ivldActualMasterImpl.setOrganizationKey(ivldActualMaster.getOrganizationKey());
		ivldActualMasterImpl.setQuantityInclusion(ivldActualMaster.getQuantityInclusion());
		ivldActualMasterImpl.setCashPaidDate(ivldActualMaster.getCashPaidDate());
		ivldActualMasterImpl.setSource(ivldActualMaster.getSource());
		ivldActualMasterImpl.setAddChgDelIndicator(ivldActualMaster.getAddChgDelIndicator());
		ivldActualMasterImpl.setAnalysisCode(ivldActualMaster.getAnalysisCode());
		ivldActualMasterImpl.setAccrualActualStartDate(ivldActualMaster.getAccrualActualStartDate());
		ivldActualMasterImpl.setModifiedBy(ivldActualMaster.getModifiedBy());
		ivldActualMasterImpl.setSalesAmount(ivldActualMaster.getSalesAmount());
		ivldActualMasterImpl.setQuantity(ivldActualMaster.getQuantity());
		ivldActualMasterImpl.setSentOut(ivldActualMaster.getSentOut());
		ivldActualMasterImpl.setAccountNo(ivldActualMaster.getAccountNo());
		ivldActualMasterImpl.setReasonForFailure(ivldActualMaster.getReasonForFailure());
		ivldActualMasterImpl.setAccountName(ivldActualMaster.getAccountName());
		ivldActualMasterImpl.setProvisionId(ivldActualMaster.getProvisionId());
		ivldActualMasterImpl.setAmount(ivldActualMaster.getAmount());
		ivldActualMasterImpl.setMarketId(ivldActualMaster.getMarketId());
		ivldActualMasterImpl.setIsActive(ivldActualMaster.getIsActive());
		ivldActualMasterImpl.setSettlementMethodNo(ivldActualMaster.getSettlementMethodNo());
		ivldActualMasterImpl.setItemIdentifierCodeQualifier(ivldActualMaster.getItemIdentifierCodeQualifier());
		ivldActualMasterImpl.setPriceAdjustmentName(ivldActualMaster.getPriceAdjustmentName());
		ivldActualMasterImpl.setErrorField(ivldActualMaster.getErrorField());
		ivldActualMasterImpl.setRecordSequence(ivldActualMaster.getRecordSequence());
		ivldActualMasterImpl.setMandatedDiscountAmount(ivldActualMaster.getMandatedDiscountAmount());
		ivldActualMasterImpl.setParentcomDivmktBrandProdkey(ivldActualMaster.getParentcomDivmktBrandProdkey());
		ivldActualMasterImpl.setPrice(ivldActualMaster.getPrice());
		ivldActualMasterImpl.setDispensedDate(ivldActualMaster.getDispensedDate());
		ivldActualMasterImpl.setItemId(ivldActualMaster.getItemId());
		ivldActualMasterImpl.setAccrualProcessed(ivldActualMaster.getAccrualProcessed());
		ivldActualMasterImpl.setUploadDate(ivldActualMaster.getUploadDate());
		ivldActualMasterImpl.setCreatedBy(ivldActualMaster.getCreatedBy());
		ivldActualMasterImpl.setCreatedDate(ivldActualMaster.getCreatedDate());
		ivldActualMasterImpl.setInvoiceLineNo(ivldActualMaster.getInvoiceLineNo());
		ivldActualMasterImpl.setErrorCode(ivldActualMaster.getErrorCode());
		ivldActualMasterImpl.setIntfInsertedDate(ivldActualMaster.getIntfInsertedDate());
		ivldActualMasterImpl.setItemNo(ivldActualMaster.getItemNo());
		ivldActualMasterImpl.setReprocessedFlag(ivldActualMaster.getReprocessedFlag());
		ivldActualMasterImpl.setAcctIdentifierCodeQualifier(ivldActualMaster.getAcctIdentifierCodeQualifier());
		ivldActualMasterImpl.setContractId(ivldActualMaster.getContractId());
		ivldActualMasterImpl.setBrandId(ivldActualMaster.getBrandId());
		ivldActualMasterImpl.setComDivMktBrandProdKey(ivldActualMaster.getComDivMktBrandProdKey());
		ivldActualMasterImpl.setClaimIndicator(ivldActualMaster.getClaimIndicator());
		ivldActualMasterImpl.setIvldActualMasterSid(ivldActualMaster.getIvldActualMasterSid());
		ivldActualMasterImpl.setBatchId(ivldActualMaster.getBatchId());
		ivldActualMasterImpl.setProvisionClaimIndicator(ivldActualMaster.getProvisionClaimIndicator());
		ivldActualMasterImpl.setCheckRecord(ivldActualMaster.isCheckRecord());

		return ivldActualMasterImpl;
	}

	/**
	 * Returns the ivld actual master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld actual master
	 * @return the ivld actual master
	 * @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldActualMasterException {
		IvldActualMaster ivldActualMaster = fetchByPrimaryKey(primaryKey);

		if (ivldActualMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldActualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldActualMaster;
	}

	/**
	 * Returns the ivld actual master with the primary key or throws a {@link NoSuchIvldActualMasterException} if it could not be found.
	 *
	 * @param ivldActualMasterSid the primary key of the ivld actual master
	 * @return the ivld actual master
	 * @throws NoSuchIvldActualMasterException if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster findByPrimaryKey(int ivldActualMasterSid)
		throws NoSuchIvldActualMasterException {
		return findByPrimaryKey((Serializable)ivldActualMasterSid);
	}

	/**
	 * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld actual master
	 * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldActualMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldActualMaster ivldActualMaster = (IvldActualMaster)serializable;

		if (ivldActualMaster == null) {
			Session session = null;

			try {
				session = openSession();

				ivldActualMaster = (IvldActualMaster)session.get(IvldActualMasterImpl.class,
						primaryKey);

				if (ivldActualMaster != null) {
					cacheResult(ivldActualMaster);
				}
				else {
					entityCache.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldActualMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldActualMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldActualMaster;
	}

	/**
	 * Returns the ivld actual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldActualMasterSid the primary key of the ivld actual master
	 * @return the ivld actual master, or <code>null</code> if a ivld actual master with the primary key could not be found
	 */
	@Override
	public IvldActualMaster fetchByPrimaryKey(int ivldActualMasterSid) {
		return fetchByPrimaryKey((Serializable)ivldActualMasterSid);
	}

	@Override
	public Map<Serializable, IvldActualMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldActualMaster> map = new HashMap<Serializable, IvldActualMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldActualMaster ivldActualMaster = fetchByPrimaryKey(primaryKey);

			if (ivldActualMaster != null) {
				map.put(primaryKey, ivldActualMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldActualMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldActualMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDACTUALMASTER_WHERE_PKS_IN);

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

			for (IvldActualMaster ivldActualMaster : (List<IvldActualMaster>)q.list()) {
				map.put(ivldActualMaster.getPrimaryKeyObj(), ivldActualMaster);

				cacheResult(ivldActualMaster);

				uncachedPrimaryKeys.remove(ivldActualMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldActualMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldActualMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld actual masters.
	 *
	 * @return the ivld actual masters
	 */
	@Override
	public List<IvldActualMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld actual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld actual masters
	 * @param end the upper bound of the range of ivld actual masters (not inclusive)
	 * @return the range of ivld actual masters
	 */
	@Override
	public List<IvldActualMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld actual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld actual masters
	 * @param end the upper bound of the range of ivld actual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld actual masters
	 */
	@Override
	public List<IvldActualMaster> findAll(int start, int end,
		OrderByComparator<IvldActualMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld actual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldActualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld actual masters
	 * @param end the upper bound of the range of ivld actual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld actual masters
	 */
	@Override
	public List<IvldActualMaster> findAll(int start, int end,
		OrderByComparator<IvldActualMaster> orderByComparator,
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

		List<IvldActualMaster> list = null;

		if (retrieveFromCache) {
			list = (List<IvldActualMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDACTUALMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDACTUALMASTER;

				if (pagination) {
					sql = sql.concat(IvldActualMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldActualMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldActualMaster>)QueryUtil.list(q,
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
	 * Removes all the ivld actual masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldActualMaster ivldActualMaster : findAll()) {
			remove(ivldActualMaster);
		}
	}

	/**
	 * Returns the number of ivld actual masters.
	 *
	 * @return the number of ivld actual masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDACTUALMASTER);

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
		return IvldActualMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld actual master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldActualMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDACTUALMASTER = "SELECT ivldActualMaster FROM IvldActualMaster ivldActualMaster";
	private static final String _SQL_SELECT_IVLDACTUALMASTER_WHERE_PKS_IN = "SELECT ivldActualMaster FROM IvldActualMaster ivldActualMaster WHERE IVLD_ACTUAL_MASTER_SID IN (";
	private static final String _SQL_COUNT_IVLDACTUALMASTER = "SELECT COUNT(ivldActualMaster) FROM IvldActualMaster ivldActualMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldActualMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldActualMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldActualMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"actualIntfid", "accountId", "programStateCode", "settlementNo",
				"accrualActualEndDate", "actualId", "modifiedDate", "divisionId",
				"organizationKey", "quantityInclusion", "cashPaidDate", "source",
				"addChgDelIndicator", "analysisCode", "accrualActualStartDate",
				"modifiedBy", "salesAmount", "quantity", "sentOut", "accountNo",
				"reasonForFailure", "accountName", "provisionId", "amount",
				"marketId", "isActive", "settlementMethodNo",
				"itemIdentifierCodeQualifier", "priceAdjustmentName",
				"errorField", "recordSequence", "mandatedDiscountAmount",
				"parentcomDivmktBrandProdkey", "price", "dispensedDate",
				"itemId", "accrualProcessed", "uploadDate", "createdBy",
				"createdDate", "invoiceLineNo", "errorCode", "intfInsertedDate",
				"itemNo", "reprocessedFlag", "acctIdentifierCodeQualifier",
				"contractId", "brandId", "comDivMktBrandProdKey",
				"claimIndicator", "ivldActualMasterSid", "batchId",
				"provisionClaimIndicator", "checkRecord"
			});
}