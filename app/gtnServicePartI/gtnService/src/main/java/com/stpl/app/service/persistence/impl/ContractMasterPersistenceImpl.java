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

import com.stpl.app.exception.NoSuchContractMasterException;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.impl.ContractMasterImpl;
import com.stpl.app.model.impl.ContractMasterModelImpl;
import com.stpl.app.service.persistence.ContractMasterPersistence;

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
 * The persistence implementation for the contract master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractMasterPersistence
 * @see com.stpl.app.service.persistence.ContractMasterUtil
 * @generated
 */
@ProviderType
public class ContractMasterPersistenceImpl extends BasePersistenceImpl<ContractMaster>
	implements ContractMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ContractMasterUtil} to access the contract master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ContractMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterModelImpl.FINDER_CACHE_ENABLED,
			ContractMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ContractMasterPersistenceImpl() {
		setModelClass(ContractMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("proposalEndDate", "PROPOSAL_END_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("renegotiationEndDate", "RENEGOTIATION_END_DATE");
			dbColumnNames.put("outsideAdditionalName", "OUTSIDE_ADDITIONAL_NAME");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("manfCompanyMasterSid", "MANF_COMPANY_MASTER_SID");
			dbColumnNames.put("renegotiationStartDate",
				"RENEGOTIATION_START_DATE");
			dbColumnNames.put("insideAuthor", "INSIDE_AUTHOR");
			dbColumnNames.put("advanceNoticeDays", "ADVANCE_NOTICE_DAYS");
			dbColumnNames.put("outsideOwner", "OUTSIDE_OWNER");
			dbColumnNames.put("mostFavoredNation", "MOST_FAVORED_NATION");
			dbColumnNames.put("insideAdditionalPhone", "INSIDE_ADDITIONAL_PHONE");
			dbColumnNames.put("originalStartDate", "ORIGINAL_START_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("proposalStartDate", "PROPOSAL_START_DATE");
			dbColumnNames.put("contractTradeClass", "CONTRACT_TRADE_CLASS");
			dbColumnNames.put("outsideAdditional", "OUTSIDE_ADDITIONAL");
			dbColumnNames.put("processStatus", "PROCESS_STATUS");
			dbColumnNames.put("insideAdditionalName", "INSIDE_ADDITIONAL_NAME");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("contractStatus", "CONTRACT_STATUS");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("contractType", "CONTRACT_TYPE");
			dbColumnNames.put("awardStatus", "AWARD_STATUS");
			dbColumnNames.put("insideOwner", "INSIDE_OWNER");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("shippingTerms", "SHIPPING_TERMS");
			dbColumnNames.put("priceEscalationClause", "PRICE_ESCALATION_CLAUSE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("outsideAdditionalPhone",
				"OUTSIDE_ADDITIONAL_PHONE");
			dbColumnNames.put("term", "TERM");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("documentClass", "DOCUMENT_CLASS");
			dbColumnNames.put("originalEndDate", "ORIGINAL_END_DATE");
			dbColumnNames.put("paymentTerms", "PAYMENT_TERMS");
			dbColumnNames.put("insideAdditional", "INSIDE_ADDITIONAL");
			dbColumnNames.put("affiliatedContractInfo",
				"AFFILIATED_CONTRACT_INFO");
			dbColumnNames.put("category", "CATEGORY");
			dbColumnNames.put("outsidePhone", "OUTSIDE_PHONE");
			dbColumnNames.put("priceprotectionStartDate",
				"PRICEPROTECTION_START_DATE");
			dbColumnNames.put("priceprotectionEndDate",
				"PRICEPROTECTION_END_DATE");
			dbColumnNames.put("documentType", "DOCUMENT_TYPE");
			dbColumnNames.put("exemptFromLowPrice", "EXEMPT_FROM_LOW_PRICE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("currency", "CURRENCY");
			dbColumnNames.put("insidePhone", "INSIDE_PHONE");
			dbColumnNames.put("bunitCompanyMasterSid",
				"BUNIT_COMPANY_MASTER_SID");
			dbColumnNames.put("outsideAuthor", "OUTSIDE_AUTHOR");
			dbColumnNames.put("contHoldCompanyMasterSid",
				"CONT_HOLD_COMPANY_MASTER_SID");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("lastUpdatedDate", "LAST_UPDATED_DATE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("priceResetIndicator", "PRICE_RESET_INDICATOR");
			dbColumnNames.put("minimumOrder", "MINIMUM_ORDER");
			dbColumnNames.put("cancellationClause", "CANCELLATION_CLAUSE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("internalNotes", "INTERNAL_NOTES");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the contract master in the entity cache if it is enabled.
	 *
	 * @param contractMaster the contract master
	 */
	@Override
	public void cacheResult(ContractMaster contractMaster) {
		entityCache.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterImpl.class, contractMaster.getPrimaryKey(),
			contractMaster);

		contractMaster.resetOriginalValues();
	}

	/**
	 * Caches the contract masters in the entity cache if it is enabled.
	 *
	 * @param contractMasters the contract masters
	 */
	@Override
	public void cacheResult(List<ContractMaster> contractMasters) {
		for (ContractMaster contractMaster : contractMasters) {
			if (entityCache.getResult(
						ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
						ContractMasterImpl.class, contractMaster.getPrimaryKey()) == null) {
				cacheResult(contractMaster);
			}
			else {
				contractMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all contract masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ContractMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the contract master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ContractMaster contractMaster) {
		entityCache.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterImpl.class, contractMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ContractMaster> contractMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ContractMaster contractMaster : contractMasters) {
			entityCache.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
				ContractMasterImpl.class, contractMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new contract master with the primary key. Does not add the contract master to the database.
	 *
	 * @param contractMasterSid the primary key for the new contract master
	 * @return the new contract master
	 */
	@Override
	public ContractMaster create(int contractMasterSid) {
		ContractMaster contractMaster = new ContractMasterImpl();

		contractMaster.setNew(true);
		contractMaster.setPrimaryKey(contractMasterSid);

		return contractMaster;
	}

	/**
	 * Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param contractMasterSid the primary key of the contract master
	 * @return the contract master that was removed
	 * @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster remove(int contractMasterSid)
		throws NoSuchContractMasterException {
		return remove((Serializable)contractMasterSid);
	}

	/**
	 * Removes the contract master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the contract master
	 * @return the contract master that was removed
	 * @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster remove(Serializable primaryKey)
		throws NoSuchContractMasterException {
		Session session = null;

		try {
			session = openSession();

			ContractMaster contractMaster = (ContractMaster)session.get(ContractMasterImpl.class,
					primaryKey);

			if (contractMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchContractMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(contractMaster);
		}
		catch (NoSuchContractMasterException nsee) {
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
	protected ContractMaster removeImpl(ContractMaster contractMaster) {
		contractMaster = toUnwrappedModel(contractMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(contractMaster)) {
				contractMaster = (ContractMaster)session.get(ContractMasterImpl.class,
						contractMaster.getPrimaryKeyObj());
			}

			if (contractMaster != null) {
				session.delete(contractMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (contractMaster != null) {
			clearCache(contractMaster);
		}

		return contractMaster;
	}

	@Override
	public ContractMaster updateImpl(ContractMaster contractMaster) {
		contractMaster = toUnwrappedModel(contractMaster);

		boolean isNew = contractMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (contractMaster.isNew()) {
				session.save(contractMaster);

				contractMaster.setNew(false);
			}
			else {
				contractMaster = (ContractMaster)session.merge(contractMaster);
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

		entityCache.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
			ContractMasterImpl.class, contractMaster.getPrimaryKey(),
			contractMaster, false);

		contractMaster.resetOriginalValues();

		return contractMaster;
	}

	protected ContractMaster toUnwrappedModel(ContractMaster contractMaster) {
		if (contractMaster instanceof ContractMasterImpl) {
			return contractMaster;
		}

		ContractMasterImpl contractMasterImpl = new ContractMasterImpl();

		contractMasterImpl.setNew(contractMaster.isNew());
		contractMasterImpl.setPrimaryKey(contractMaster.getPrimaryKey());

		contractMasterImpl.setProposalEndDate(contractMaster.getProposalEndDate());
		contractMasterImpl.setCreatedDate(contractMaster.getCreatedDate());
		contractMasterImpl.setRenegotiationEndDate(contractMaster.getRenegotiationEndDate());
		contractMasterImpl.setOutsideAdditionalName(contractMaster.getOutsideAdditionalName());
		contractMasterImpl.setEndDate(contractMaster.getEndDate());
		contractMasterImpl.setManfCompanyMasterSid(contractMaster.getManfCompanyMasterSid());
		contractMasterImpl.setRenegotiationStartDate(contractMaster.getRenegotiationStartDate());
		contractMasterImpl.setInsideAuthor(contractMaster.getInsideAuthor());
		contractMasterImpl.setAdvanceNoticeDays(contractMaster.getAdvanceNoticeDays());
		contractMasterImpl.setOutsideOwner(contractMaster.getOutsideOwner());
		contractMasterImpl.setMostFavoredNation(contractMaster.getMostFavoredNation());
		contractMasterImpl.setInsideAdditionalPhone(contractMaster.getInsideAdditionalPhone());
		contractMasterImpl.setOriginalStartDate(contractMaster.getOriginalStartDate());
		contractMasterImpl.setCreatedBy(contractMaster.getCreatedBy());
		contractMasterImpl.setProposalStartDate(contractMaster.getProposalStartDate());
		contractMasterImpl.setContractTradeClass(contractMaster.getContractTradeClass());
		contractMasterImpl.setOutsideAdditional(contractMaster.getOutsideAdditional());
		contractMasterImpl.setProcessStatus(contractMaster.isProcessStatus());
		contractMasterImpl.setInsideAdditionalName(contractMaster.getInsideAdditionalName());
		contractMasterImpl.setContractMasterSid(contractMaster.getContractMasterSid());
		contractMasterImpl.setContractStatus(contractMaster.getContractStatus());
		contractMasterImpl.setContractId(contractMaster.getContractId());
		contractMasterImpl.setModifiedDate(contractMaster.getModifiedDate());
		contractMasterImpl.setContractType(contractMaster.getContractType());
		contractMasterImpl.setAwardStatus(contractMaster.getAwardStatus());
		contractMasterImpl.setInsideOwner(contractMaster.getInsideOwner());
		contractMasterImpl.setSource(contractMaster.getSource());
		contractMasterImpl.setShippingTerms(contractMaster.getShippingTerms());
		contractMasterImpl.setPriceEscalationClause(contractMaster.getPriceEscalationClause());
		contractMasterImpl.setModifiedBy(contractMaster.getModifiedBy());
		contractMasterImpl.setOutsideAdditionalPhone(contractMaster.getOutsideAdditionalPhone());
		contractMasterImpl.setTerm(contractMaster.getTerm());
		contractMasterImpl.setContractNo(contractMaster.getContractNo());
		contractMasterImpl.setBatchId(contractMaster.getBatchId());
		contractMasterImpl.setDocumentClass(contractMaster.getDocumentClass());
		contractMasterImpl.setOriginalEndDate(contractMaster.getOriginalEndDate());
		contractMasterImpl.setPaymentTerms(contractMaster.getPaymentTerms());
		contractMasterImpl.setInsideAdditional(contractMaster.getInsideAdditional());
		contractMasterImpl.setAffiliatedContractInfo(contractMaster.getAffiliatedContractInfo());
		contractMasterImpl.setCategory(contractMaster.getCategory());
		contractMasterImpl.setOutsidePhone(contractMaster.getOutsidePhone());
		contractMasterImpl.setPriceprotectionStartDate(contractMaster.getPriceprotectionStartDate());
		contractMasterImpl.setPriceprotectionEndDate(contractMaster.getPriceprotectionEndDate());
		contractMasterImpl.setDocumentType(contractMaster.getDocumentType());
		contractMasterImpl.setExemptFromLowPrice(contractMaster.getExemptFromLowPrice());
		contractMasterImpl.setOrganizationKey(contractMaster.getOrganizationKey());
		contractMasterImpl.setCurrency(contractMaster.getCurrency());
		contractMasterImpl.setInsidePhone(contractMaster.getInsidePhone());
		contractMasterImpl.setBunitCompanyMasterSid(contractMaster.getBunitCompanyMasterSid());
		contractMasterImpl.setOutsideAuthor(contractMaster.getOutsideAuthor());
		contractMasterImpl.setContHoldCompanyMasterSid(contractMaster.getContHoldCompanyMasterSid());
		contractMasterImpl.setStartDate(contractMaster.getStartDate());
		contractMasterImpl.setContractName(contractMaster.getContractName());
		contractMasterImpl.setLastUpdatedDate(contractMaster.getLastUpdatedDate());
		contractMasterImpl.setRecordLockStatus(contractMaster.isRecordLockStatus());
		contractMasterImpl.setPriceResetIndicator(contractMaster.getPriceResetIndicator());
		contractMasterImpl.setMinimumOrder(contractMaster.getMinimumOrder());
		contractMasterImpl.setCancellationClause(contractMaster.getCancellationClause());
		contractMasterImpl.setInboundStatus(contractMaster.getInboundStatus());
		contractMasterImpl.setInternalNotes(contractMaster.getInternalNotes());

		return contractMasterImpl;
	}

	/**
	 * Returns the contract master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract master
	 * @return the contract master
	 * @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchContractMasterException {
		ContractMaster contractMaster = fetchByPrimaryKey(primaryKey);

		if (contractMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchContractMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return contractMaster;
	}

	/**
	 * Returns the contract master with the primary key or throws a {@link NoSuchContractMasterException} if it could not be found.
	 *
	 * @param contractMasterSid the primary key of the contract master
	 * @return the contract master
	 * @throws NoSuchContractMasterException if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster findByPrimaryKey(int contractMasterSid)
		throws NoSuchContractMasterException {
		return findByPrimaryKey((Serializable)contractMasterSid);
	}

	/**
	 * Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the contract master
	 * @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
				ContractMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ContractMaster contractMaster = (ContractMaster)serializable;

		if (contractMaster == null) {
			Session session = null;

			try {
				session = openSession();

				contractMaster = (ContractMaster)session.get(ContractMasterImpl.class,
						primaryKey);

				if (contractMaster != null) {
					cacheResult(contractMaster);
				}
				else {
					entityCache.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
						ContractMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return contractMaster;
	}

	/**
	 * Returns the contract master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param contractMasterSid the primary key of the contract master
	 * @return the contract master, or <code>null</code> if a contract master with the primary key could not be found
	 */
	@Override
	public ContractMaster fetchByPrimaryKey(int contractMasterSid) {
		return fetchByPrimaryKey((Serializable)contractMasterSid);
	}

	@Override
	public Map<Serializable, ContractMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ContractMaster> map = new HashMap<Serializable, ContractMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ContractMaster contractMaster = fetchByPrimaryKey(primaryKey);

			if (contractMaster != null) {
				map.put(primaryKey, contractMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ContractMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CONTRACTMASTER_WHERE_PKS_IN);

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

			for (ContractMaster contractMaster : (List<ContractMaster>)q.list()) {
				map.put(contractMaster.getPrimaryKeyObj(), contractMaster);

				cacheResult(contractMaster);

				uncachedPrimaryKeys.remove(contractMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ContractMasterModelImpl.ENTITY_CACHE_ENABLED,
					ContractMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the contract masters.
	 *
	 * @return the contract masters
	 */
	@Override
	public List<ContractMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the contract masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract masters
	 * @param end the upper bound of the range of contract masters (not inclusive)
	 * @return the range of contract masters
	 */
	@Override
	public List<ContractMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the contract masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract masters
	 * @param end the upper bound of the range of contract masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of contract masters
	 */
	@Override
	public List<ContractMaster> findAll(int start, int end,
		OrderByComparator<ContractMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the contract masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of contract masters
	 * @param end the upper bound of the range of contract masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of contract masters
	 */
	@Override
	public List<ContractMaster> findAll(int start, int end,
		OrderByComparator<ContractMaster> orderByComparator,
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

		List<ContractMaster> list = null;

		if (retrieveFromCache) {
			list = (List<ContractMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CONTRACTMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CONTRACTMASTER;

				if (pagination) {
					sql = sql.concat(ContractMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ContractMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ContractMaster>)QueryUtil.list(q,
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
	 * Removes all the contract masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ContractMaster contractMaster : findAll()) {
			remove(contractMaster);
		}
	}

	/**
	 * Returns the number of contract masters.
	 *
	 * @return the number of contract masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CONTRACTMASTER);

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
		return ContractMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the contract master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ContractMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_CONTRACTMASTER = "SELECT contractMaster FROM ContractMaster contractMaster";
	private static final String _SQL_SELECT_CONTRACTMASTER_WHERE_PKS_IN = "SELECT contractMaster FROM ContractMaster contractMaster WHERE CONTRACT_MASTER_SID IN (";
	private static final String _SQL_COUNT_CONTRACTMASTER = "SELECT COUNT(contractMaster) FROM ContractMaster contractMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "contractMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContractMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ContractMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"proposalEndDate", "createdDate", "renegotiationEndDate",
				"outsideAdditionalName", "endDate", "manfCompanyMasterSid",
				"renegotiationStartDate", "insideAuthor", "advanceNoticeDays",
				"outsideOwner", "mostFavoredNation", "insideAdditionalPhone",
				"originalStartDate", "createdBy", "proposalStartDate",
				"contractTradeClass", "outsideAdditional", "processStatus",
				"insideAdditionalName", "contractMasterSid", "contractStatus",
				"contractId", "modifiedDate", "contractType", "awardStatus",
				"insideOwner", "source", "shippingTerms",
				"priceEscalationClause", "modifiedBy", "outsideAdditionalPhone",
				"term", "contractNo", "batchId", "documentClass",
				"originalEndDate", "paymentTerms", "insideAdditional",
				"affiliatedContractInfo", "category", "outsidePhone",
				"priceprotectionStartDate", "priceprotectionEndDate",
				"documentType", "exemptFromLowPrice", "organizationKey",
				"currency", "insidePhone", "bunitCompanyMasterSid",
				"outsideAuthor", "contHoldCompanyMasterSid", "startDate",
				"contractName", "lastUpdatedDate", "recordLockStatus",
				"priceResetIndicator", "minimumOrder", "cancellationClause",
				"inboundStatus", "internalNotes"
			});
}