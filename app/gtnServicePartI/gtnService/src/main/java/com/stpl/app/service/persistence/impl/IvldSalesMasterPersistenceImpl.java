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

import com.stpl.app.exception.NoSuchIvldSalesMasterException;
import com.stpl.app.model.IvldSalesMaster;
import com.stpl.app.model.impl.IvldSalesMasterImpl;
import com.stpl.app.model.impl.IvldSalesMasterModelImpl;
import com.stpl.app.service.persistence.IvldSalesMasterPersistence;

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
 * The persistence implementation for the ivld sales master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldSalesMasterPersistence
 * @see com.stpl.app.service.persistence.IvldSalesMasterUtil
 * @generated
 */
@ProviderType
public class IvldSalesMasterPersistenceImpl extends BasePersistenceImpl<IvldSalesMaster>
	implements IvldSalesMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldSalesMasterUtil} to access the ivld sales master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldSalesMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldSalesMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldSalesMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldSalesMasterPersistenceImpl() {
		setModelClass(IvldSalesMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("salesIntfid", "SALES_INTFID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("divisionId", "DIVISION_ID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("analysisCode", "ANALYSIS_CODE");
			dbColumnNames.put("ivldSalesMasterSid", "IVLD_SALES_MASTER_SID");
			dbColumnNames.put("docType", "DOC_TYPE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("lotNo", "LOT_NO");
			dbColumnNames.put("quantity", "QUANTITY");
			dbColumnNames.put("invoiceLineNumber", "INVOICE_LINE_NUMBER");
			dbColumnNames.put("identifierCodeQualifier",
				"IDENTIFIER_CODE_QUALIFIER");
			dbColumnNames.put("accountCodeQualifier", "ACCOUNT_CODE_QUALIFIER");
			dbColumnNames.put("parentItemId", "PARENT_ITEM_ID");
			dbColumnNames.put("accountNo", "ACCOUNT_NO");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("accountName", "ACCOUNT_NAME");
			dbColumnNames.put("provisionId", "PROVISION_ID");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("marketId", "MARKET_ID");
			dbColumnNames.put("isActive", "IS_ACTIVE");
			dbColumnNames.put("wholesaleOwnerId", "WHOLESALE_OWNER_ID");
			dbColumnNames.put("priceAdjustmentName", "PRICE_ADJUSTMENT_NAME");
			dbColumnNames.put("salesId", "SALES_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("recordSequence", "RECORD_SEQUENCE");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("customerSubtype", "CUSTOMER_SUBTYPE");
			dbColumnNames.put("parentItemNo", "PARENT_ITEM_NO");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("orderReceivedDate", "ORDER_RECEIVED_DATE");
			dbColumnNames.put("orderNumber", "ORDER_NUMBER");
			dbColumnNames.put("accountType", "ACCOUNT_TYPE");
			dbColumnNames.put("uploadDate", "UPLOAD_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("itemUom", "ITEM_UOM");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("invoiceNumber", "INVOICE_NUMBER");
			dbColumnNames.put("orderSubtype", "ORDER_SUBTYPE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("customerCompanyCode", "CUSTOMER_COMPANY_CODE");
			dbColumnNames.put("orderType", "ORDER_TYPE");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("invoiceDate", "INVOICE_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
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
	 * Caches the ivld sales master in the entity cache if it is enabled.
	 *
	 * @param ivldSalesMaster the ivld sales master
	 */
	@Override
	public void cacheResult(IvldSalesMaster ivldSalesMaster) {
		entityCache.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey(),
			ivldSalesMaster);

		ivldSalesMaster.resetOriginalValues();
	}

	/**
	 * Caches the ivld sales masters in the entity cache if it is enabled.
	 *
	 * @param ivldSalesMasters the ivld sales masters
	 */
	@Override
	public void cacheResult(List<IvldSalesMaster> ivldSalesMasters) {
		for (IvldSalesMaster ivldSalesMaster : ivldSalesMasters) {
			if (entityCache.getResult(
						IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldSalesMasterImpl.class,
						ivldSalesMaster.getPrimaryKey()) == null) {
				cacheResult(ivldSalesMaster);
			}
			else {
				ivldSalesMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld sales masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldSalesMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld sales master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldSalesMaster ivldSalesMaster) {
		entityCache.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldSalesMaster> ivldSalesMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldSalesMaster ivldSalesMaster : ivldSalesMasters) {
			entityCache.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld sales master with the primary key. Does not add the ivld sales master to the database.
	 *
	 * @param ivldSalesMasterSid the primary key for the new ivld sales master
	 * @return the new ivld sales master
	 */
	@Override
	public IvldSalesMaster create(int ivldSalesMasterSid) {
		IvldSalesMaster ivldSalesMaster = new IvldSalesMasterImpl();

		ivldSalesMaster.setNew(true);
		ivldSalesMaster.setPrimaryKey(ivldSalesMasterSid);

		return ivldSalesMaster;
	}

	/**
	 * Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldSalesMasterSid the primary key of the ivld sales master
	 * @return the ivld sales master that was removed
	 * @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster remove(int ivldSalesMasterSid)
		throws NoSuchIvldSalesMasterException {
		return remove((Serializable)ivldSalesMasterSid);
	}

	/**
	 * Removes the ivld sales master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld sales master
	 * @return the ivld sales master that was removed
	 * @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster remove(Serializable primaryKey)
		throws NoSuchIvldSalesMasterException {
		Session session = null;

		try {
			session = openSession();

			IvldSalesMaster ivldSalesMaster = (IvldSalesMaster)session.get(IvldSalesMasterImpl.class,
					primaryKey);

			if (ivldSalesMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldSalesMaster);
		}
		catch (NoSuchIvldSalesMasterException nsee) {
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
	protected IvldSalesMaster removeImpl(IvldSalesMaster ivldSalesMaster) {
		ivldSalesMaster = toUnwrappedModel(ivldSalesMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldSalesMaster)) {
				ivldSalesMaster = (IvldSalesMaster)session.get(IvldSalesMasterImpl.class,
						ivldSalesMaster.getPrimaryKeyObj());
			}

			if (ivldSalesMaster != null) {
				session.delete(ivldSalesMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldSalesMaster != null) {
			clearCache(ivldSalesMaster);
		}

		return ivldSalesMaster;
	}

	@Override
	public IvldSalesMaster updateImpl(IvldSalesMaster ivldSalesMaster) {
		ivldSalesMaster = toUnwrappedModel(ivldSalesMaster);

		boolean isNew = ivldSalesMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldSalesMaster.isNew()) {
				session.save(ivldSalesMaster);

				ivldSalesMaster.setNew(false);
			}
			else {
				ivldSalesMaster = (IvldSalesMaster)session.merge(ivldSalesMaster);
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

		entityCache.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldSalesMasterImpl.class, ivldSalesMaster.getPrimaryKey(),
			ivldSalesMaster, false);

		ivldSalesMaster.resetOriginalValues();

		return ivldSalesMaster;
	}

	protected IvldSalesMaster toUnwrappedModel(IvldSalesMaster ivldSalesMaster) {
		if (ivldSalesMaster instanceof IvldSalesMasterImpl) {
			return ivldSalesMaster;
		}

		IvldSalesMasterImpl ivldSalesMasterImpl = new IvldSalesMasterImpl();

		ivldSalesMasterImpl.setNew(ivldSalesMaster.isNew());
		ivldSalesMasterImpl.setPrimaryKey(ivldSalesMaster.getPrimaryKey());

		ivldSalesMasterImpl.setAccountId(ivldSalesMaster.getAccountId());
		ivldSalesMasterImpl.setSalesIntfid(ivldSalesMaster.getSalesIntfid());
		ivldSalesMasterImpl.setModifiedDate(ivldSalesMaster.getModifiedDate());
		ivldSalesMasterImpl.setOrganizationKey(ivldSalesMaster.getOrganizationKey());
		ivldSalesMasterImpl.setDivisionId(ivldSalesMaster.getDivisionId());
		ivldSalesMasterImpl.setSource(ivldSalesMaster.getSource());
		ivldSalesMasterImpl.setAddChgDelIndicator(ivldSalesMaster.getAddChgDelIndicator());
		ivldSalesMasterImpl.setAnalysisCode(ivldSalesMaster.getAnalysisCode());
		ivldSalesMasterImpl.setIvldSalesMasterSid(ivldSalesMaster.getIvldSalesMasterSid());
		ivldSalesMasterImpl.setDocType(ivldSalesMaster.getDocType());
		ivldSalesMasterImpl.setModifiedBy(ivldSalesMaster.getModifiedBy());
		ivldSalesMasterImpl.setLotNo(ivldSalesMaster.getLotNo());
		ivldSalesMasterImpl.setQuantity(ivldSalesMaster.getQuantity());
		ivldSalesMasterImpl.setInvoiceLineNumber(ivldSalesMaster.getInvoiceLineNumber());
		ivldSalesMasterImpl.setIdentifierCodeQualifier(ivldSalesMaster.getIdentifierCodeQualifier());
		ivldSalesMasterImpl.setAccountCodeQualifier(ivldSalesMaster.getAccountCodeQualifier());
		ivldSalesMasterImpl.setParentItemId(ivldSalesMaster.getParentItemId());
		ivldSalesMasterImpl.setAccountNo(ivldSalesMaster.getAccountNo());
		ivldSalesMasterImpl.setReasonForFailure(ivldSalesMaster.getReasonForFailure());
		ivldSalesMasterImpl.setAccountName(ivldSalesMaster.getAccountName());
		ivldSalesMasterImpl.setProvisionId(ivldSalesMaster.getProvisionId());
		ivldSalesMasterImpl.setAmount(ivldSalesMaster.getAmount());
		ivldSalesMasterImpl.setMarketId(ivldSalesMaster.getMarketId());
		ivldSalesMasterImpl.setIsActive(ivldSalesMaster.getIsActive());
		ivldSalesMasterImpl.setWholesaleOwnerId(ivldSalesMaster.getWholesaleOwnerId());
		ivldSalesMasterImpl.setPriceAdjustmentName(ivldSalesMaster.getPriceAdjustmentName());
		ivldSalesMasterImpl.setSalesId(ivldSalesMaster.getSalesId());
		ivldSalesMasterImpl.setErrorField(ivldSalesMaster.getErrorField());
		ivldSalesMasterImpl.setRecordSequence(ivldSalesMaster.getRecordSequence());
		ivldSalesMasterImpl.setPrice(ivldSalesMaster.getPrice());
		ivldSalesMasterImpl.setCustomerSubtype(ivldSalesMaster.getCustomerSubtype());
		ivldSalesMasterImpl.setParentItemNo(ivldSalesMaster.getParentItemNo());
		ivldSalesMasterImpl.setItemId(ivldSalesMaster.getItemId());
		ivldSalesMasterImpl.setOrderReceivedDate(ivldSalesMaster.getOrderReceivedDate());
		ivldSalesMasterImpl.setOrderNumber(ivldSalesMaster.getOrderNumber());
		ivldSalesMasterImpl.setAccountType(ivldSalesMaster.getAccountType());
		ivldSalesMasterImpl.setUploadDate(ivldSalesMaster.getUploadDate());
		ivldSalesMasterImpl.setCreatedBy(ivldSalesMaster.getCreatedBy());
		ivldSalesMasterImpl.setCreatedDate(ivldSalesMaster.getCreatedDate());
		ivldSalesMasterImpl.setErrorCode(ivldSalesMaster.getErrorCode());
		ivldSalesMasterImpl.setItemUom(ivldSalesMaster.getItemUom());
		ivldSalesMasterImpl.setIntfInsertedDate(ivldSalesMaster.getIntfInsertedDate());
		ivldSalesMasterImpl.setInvoiceNumber(ivldSalesMaster.getInvoiceNumber());
		ivldSalesMasterImpl.setOrderSubtype(ivldSalesMaster.getOrderSubtype());
		ivldSalesMasterImpl.setItemNo(ivldSalesMaster.getItemNo());
		ivldSalesMasterImpl.setReprocessedFlag(ivldSalesMaster.getReprocessedFlag());
		ivldSalesMasterImpl.setContractId(ivldSalesMaster.getContractId());
		ivldSalesMasterImpl.setCustomerCompanyCode(ivldSalesMaster.getCustomerCompanyCode());
		ivldSalesMasterImpl.setOrderType(ivldSalesMaster.getOrderType());
		ivldSalesMasterImpl.setCompanyStringId(ivldSalesMaster.getCompanyStringId());
		ivldSalesMasterImpl.setBrandId(ivldSalesMaster.getBrandId());
		ivldSalesMasterImpl.setInvoiceDate(ivldSalesMaster.getInvoiceDate());
		ivldSalesMasterImpl.setBatchId(ivldSalesMaster.getBatchId());
		ivldSalesMasterImpl.setContractNo(ivldSalesMaster.getContractNo());
		ivldSalesMasterImpl.setCheckRecord(ivldSalesMaster.isCheckRecord());

		return ivldSalesMasterImpl;
	}

	/**
	 * Returns the ivld sales master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld sales master
	 * @return the ivld sales master
	 * @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldSalesMasterException {
		IvldSalesMaster ivldSalesMaster = fetchByPrimaryKey(primaryKey);

		if (ivldSalesMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldSalesMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldSalesMaster;
	}

	/**
	 * Returns the ivld sales master with the primary key or throws a {@link NoSuchIvldSalesMasterException} if it could not be found.
	 *
	 * @param ivldSalesMasterSid the primary key of the ivld sales master
	 * @return the ivld sales master
	 * @throws NoSuchIvldSalesMasterException if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster findByPrimaryKey(int ivldSalesMasterSid)
		throws NoSuchIvldSalesMasterException {
		return findByPrimaryKey((Serializable)ivldSalesMasterSid);
	}

	/**
	 * Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld sales master
	 * @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldSalesMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldSalesMaster ivldSalesMaster = (IvldSalesMaster)serializable;

		if (ivldSalesMaster == null) {
			Session session = null;

			try {
				session = openSession();

				ivldSalesMaster = (IvldSalesMaster)session.get(IvldSalesMasterImpl.class,
						primaryKey);

				if (ivldSalesMaster != null) {
					cacheResult(ivldSalesMaster);
				}
				else {
					entityCache.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldSalesMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldSalesMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldSalesMaster;
	}

	/**
	 * Returns the ivld sales master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldSalesMasterSid the primary key of the ivld sales master
	 * @return the ivld sales master, or <code>null</code> if a ivld sales master with the primary key could not be found
	 */
	@Override
	public IvldSalesMaster fetchByPrimaryKey(int ivldSalesMasterSid) {
		return fetchByPrimaryKey((Serializable)ivldSalesMasterSid);
	}

	@Override
	public Map<Serializable, IvldSalesMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldSalesMaster> map = new HashMap<Serializable, IvldSalesMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldSalesMaster ivldSalesMaster = fetchByPrimaryKey(primaryKey);

			if (ivldSalesMaster != null) {
				map.put(primaryKey, ivldSalesMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldSalesMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldSalesMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDSALESMASTER_WHERE_PKS_IN);

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

			for (IvldSalesMaster ivldSalesMaster : (List<IvldSalesMaster>)q.list()) {
				map.put(ivldSalesMaster.getPrimaryKeyObj(), ivldSalesMaster);

				cacheResult(ivldSalesMaster);

				uncachedPrimaryKeys.remove(ivldSalesMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldSalesMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldSalesMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld sales masters.
	 *
	 * @return the ivld sales masters
	 */
	@Override
	public List<IvldSalesMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld sales masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld sales masters
	 * @param end the upper bound of the range of ivld sales masters (not inclusive)
	 * @return the range of ivld sales masters
	 */
	@Override
	public List<IvldSalesMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld sales masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld sales masters
	 * @param end the upper bound of the range of ivld sales masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld sales masters
	 */
	@Override
	public List<IvldSalesMaster> findAll(int start, int end,
		OrderByComparator<IvldSalesMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld sales masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldSalesMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld sales masters
	 * @param end the upper bound of the range of ivld sales masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld sales masters
	 */
	@Override
	public List<IvldSalesMaster> findAll(int start, int end,
		OrderByComparator<IvldSalesMaster> orderByComparator,
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

		List<IvldSalesMaster> list = null;

		if (retrieveFromCache) {
			list = (List<IvldSalesMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDSALESMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDSALESMASTER;

				if (pagination) {
					sql = sql.concat(IvldSalesMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldSalesMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldSalesMaster>)QueryUtil.list(q,
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
	 * Removes all the ivld sales masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldSalesMaster ivldSalesMaster : findAll()) {
			remove(ivldSalesMaster);
		}
	}

	/**
	 * Returns the number of ivld sales masters.
	 *
	 * @return the number of ivld sales masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDSALESMASTER);

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
		return IvldSalesMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld sales master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldSalesMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDSALESMASTER = "SELECT ivldSalesMaster FROM IvldSalesMaster ivldSalesMaster";
	private static final String _SQL_SELECT_IVLDSALESMASTER_WHERE_PKS_IN = "SELECT ivldSalesMaster FROM IvldSalesMaster ivldSalesMaster WHERE IVLD_SALES_MASTER_SID IN (";
	private static final String _SQL_COUNT_IVLDSALESMASTER = "SELECT COUNT(ivldSalesMaster) FROM IvldSalesMaster ivldSalesMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldSalesMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldSalesMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldSalesMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"accountId", "salesIntfid", "modifiedDate", "organizationKey",
				"divisionId", "source", "addChgDelIndicator", "analysisCode",
				"ivldSalesMasterSid", "docType", "modifiedBy", "lotNo",
				"quantity", "invoiceLineNumber", "identifierCodeQualifier",
				"accountCodeQualifier", "parentItemId", "accountNo",
				"reasonForFailure", "accountName", "provisionId", "amount",
				"marketId", "isActive", "wholesaleOwnerId",
				"priceAdjustmentName", "salesId", "errorField", "recordSequence",
				"price", "customerSubtype", "parentItemNo", "itemId",
				"orderReceivedDate", "orderNumber", "accountType", "uploadDate",
				"createdBy", "createdDate", "errorCode", "itemUom",
				"intfInsertedDate", "invoiceNumber", "orderSubtype", "itemNo",
				"reprocessedFlag", "contractId", "customerCompanyCode",
				"orderType", "companyStringId", "brandId", "invoiceDate",
				"batchId", "contractNo", "checkRecord"
			});
}