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

import com.stpl.app.exception.NoSuchRsContractException;
import com.stpl.app.model.RsContract;
import com.stpl.app.model.impl.RsContractImpl;
import com.stpl.app.model.impl.RsContractModelImpl;
import com.stpl.app.service.persistence.RsContractPersistence;

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
 * The persistence implementation for the rs contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractPersistence
 * @see com.stpl.app.service.persistence.RsContractUtil
 * @generated
 */
@ProviderType
public class RsContractPersistenceImpl extends BasePersistenceImpl<RsContract>
	implements RsContractPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RsContractUtil} to access the rs contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RsContractImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractModelImpl.FINDER_CACHE_ENABLED, RsContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractModelImpl.FINDER_CACHE_ENABLED, RsContractImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public RsContractPersistenceImpl() {
		setModelClass(RsContract.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("cfpContractSid", "CFP_CONTRACT_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("psContractSid", "PS_CONTRACT_SID");
			dbColumnNames.put("rsName", "RS_NAME");
			dbColumnNames.put("rsStatus", "RS_STATUS");
			dbColumnNames.put("rsStartDate", "RS_START_DATE");
			dbColumnNames.put("rsTransRefId", "RS_TRANS_REF_ID");
			dbColumnNames.put("makePayableTo", "MAKE_PAYABLE_TO");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("rsCategory", "RS_CATEGORY");
			dbColumnNames.put("rsTradeClass", "RS_TRADE_CLASS");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("rebateRuleType", "REBATE_RULE_TYPE");
			dbColumnNames.put("paymentMethod", "PAYMENT_METHOD");
			dbColumnNames.put("rsContractAttachedDate",
				"RS_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("rsAlias", "RS_ALIAS");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("rebateProcessingType", "REBATE_PROCESSING_TYPE");
			dbColumnNames.put("rsContractAttachedStatus",
				"RS_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("interestBearingBasis", "INTEREST_BEARING_BASIS");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsTransRefName", "RS_TRANS_REF_NAME");
			dbColumnNames.put("rebateProgramType", "REBATE_PROGRAM_TYPE");
			dbColumnNames.put("rebatePlanLevel", "REBATE_PLAN_LEVEL");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("rsCalendar", "RS_CALENDAR");
			dbColumnNames.put("rsType", "RS_TYPE");
			dbColumnNames.put("address1", "ADDRESS_1");
			dbColumnNames.put("address2", "ADDRESS_2");
			dbColumnNames.put("rsEndDate", "RS_END_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("rsTransRefNo", "RS_TRANS_REF_NO");
			dbColumnNames.put("zipCode", "ZIP_CODE");
			dbColumnNames.put("rebateRuleAssociation", "REBATE_RULE_ASSOCIATION");
			dbColumnNames.put("state", "STATE");
			dbColumnNames.put("rebateFrequency", "REBATE_FREQUENCY");
			dbColumnNames.put("rsDesignation", "RS_DESIGNATION");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("ifpContractSid", "IFP_CONTRACT_SID");
			dbColumnNames.put("rsContractSid", "RS_CONTRACT_SID");
			dbColumnNames.put("paymentTerms", "PAYMENT_TERMS");
			dbColumnNames.put("rsNo", "RS_NO");
			dbColumnNames.put("rsModelSid", "RS_MODEL_SID");
			dbColumnNames.put("rsValidationProfile", "RS_VALIDATION_PROFILE");
			dbColumnNames.put("paymentGracePeriod", "PAYMENT_GRACE_PERIOD");
			dbColumnNames.put("paymentFrequency", "PAYMENT_FREQUENCY");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("rsId", "RS_ID");
			dbColumnNames.put("city", "CITY");
			dbColumnNames.put("parentRsName", "PARENT_RS_NAME");
			dbColumnNames.put("interestBearingIndicator",
				"INTEREST_BEARING_INDICATOR");
			dbColumnNames.put("parentRsId", "PARENT_RS_ID");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("calculationType", "CALCULATION_TYPE");
			dbColumnNames.put("calculationLevel", "CALCULATION_LEVEL");
			dbColumnNames.put("calculationRule", "CALCULATION_RULE");
			dbColumnNames.put("calculationRuleLevel", "CALCULATION_RULE_LEVEL");
			dbColumnNames.put("evaluationRuleType", "EVALUATION_RULE_TYPE");
			dbColumnNames.put("evaluationRuleLevel", "EVALUATION_RULE_LEVEL");
			dbColumnNames.put("evaluationRuleOrAssociation",
				"EVALUATION_RULE_OR_ASSOCIATION");
			dbColumnNames.put("deductionInclusion", "DEDUCTION_INCLUSION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the rs contract in the entity cache if it is enabled.
	 *
	 * @param rsContract the rs contract
	 */
	@Override
	public void cacheResult(RsContract rsContract) {
		entityCache.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractImpl.class, rsContract.getPrimaryKey(), rsContract);

		rsContract.resetOriginalValues();
	}

	/**
	 * Caches the rs contracts in the entity cache if it is enabled.
	 *
	 * @param rsContracts the rs contracts
	 */
	@Override
	public void cacheResult(List<RsContract> rsContracts) {
		for (RsContract rsContract : rsContracts) {
			if (entityCache.getResult(
						RsContractModelImpl.ENTITY_CACHE_ENABLED,
						RsContractImpl.class, rsContract.getPrimaryKey()) == null) {
				cacheResult(rsContract);
			}
			else {
				rsContract.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all rs contracts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RsContractImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the rs contract.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RsContract rsContract) {
		entityCache.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractImpl.class, rsContract.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<RsContract> rsContracts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RsContract rsContract : rsContracts) {
			entityCache.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
				RsContractImpl.class, rsContract.getPrimaryKey());
		}
	}

	/**
	 * Creates a new rs contract with the primary key. Does not add the rs contract to the database.
	 *
	 * @param rsContractSid the primary key for the new rs contract
	 * @return the new rs contract
	 */
	@Override
	public RsContract create(int rsContractSid) {
		RsContract rsContract = new RsContractImpl();

		rsContract.setNew(true);
		rsContract.setPrimaryKey(rsContractSid);

		return rsContract;
	}

	/**
	 * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param rsContractSid the primary key of the rs contract
	 * @return the rs contract that was removed
	 * @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract remove(int rsContractSid)
		throws NoSuchRsContractException {
		return remove((Serializable)rsContractSid);
	}

	/**
	 * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the rs contract
	 * @return the rs contract that was removed
	 * @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract remove(Serializable primaryKey)
		throws NoSuchRsContractException {
		Session session = null;

		try {
			session = openSession();

			RsContract rsContract = (RsContract)session.get(RsContractImpl.class,
					primaryKey);

			if (rsContract == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(rsContract);
		}
		catch (NoSuchRsContractException nsee) {
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
	protected RsContract removeImpl(RsContract rsContract) {
		rsContract = toUnwrappedModel(rsContract);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(rsContract)) {
				rsContract = (RsContract)session.get(RsContractImpl.class,
						rsContract.getPrimaryKeyObj());
			}

			if (rsContract != null) {
				session.delete(rsContract);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (rsContract != null) {
			clearCache(rsContract);
		}

		return rsContract;
	}

	@Override
	public RsContract updateImpl(RsContract rsContract) {
		rsContract = toUnwrappedModel(rsContract);

		boolean isNew = rsContract.isNew();

		Session session = null;

		try {
			session = openSession();

			if (rsContract.isNew()) {
				session.save(rsContract);

				rsContract.setNew(false);
			}
			else {
				rsContract = (RsContract)session.merge(rsContract);
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

		entityCache.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
			RsContractImpl.class, rsContract.getPrimaryKey(), rsContract, false);

		rsContract.resetOriginalValues();

		return rsContract;
	}

	protected RsContract toUnwrappedModel(RsContract rsContract) {
		if (rsContract instanceof RsContractImpl) {
			return rsContract;
		}

		RsContractImpl rsContractImpl = new RsContractImpl();

		rsContractImpl.setNew(rsContract.isNew());
		rsContractImpl.setPrimaryKey(rsContract.getPrimaryKey());

		rsContractImpl.setCfpContractSid(rsContract.getCfpContractSid());
		rsContractImpl.setCreatedDate(rsContract.getCreatedDate());
		rsContractImpl.setPsContractSid(rsContract.getPsContractSid());
		rsContractImpl.setRsName(rsContract.getRsName());
		rsContractImpl.setRsStatus(rsContract.getRsStatus());
		rsContractImpl.setRsStartDate(rsContract.getRsStartDate());
		rsContractImpl.setRsTransRefId(rsContract.getRsTransRefId());
		rsContractImpl.setMakePayableTo(rsContract.getMakePayableTo());
		rsContractImpl.setCreatedBy(rsContract.getCreatedBy());
		rsContractImpl.setRsCategory(rsContract.getRsCategory());
		rsContractImpl.setRsTradeClass(rsContract.getRsTradeClass());
		rsContractImpl.setContractMasterSid(rsContract.getContractMasterSid());
		rsContractImpl.setRebateRuleType(rsContract.getRebateRuleType());
		rsContractImpl.setPaymentMethod(rsContract.getPaymentMethod());
		rsContractImpl.setRsContractAttachedDate(rsContract.getRsContractAttachedDate());
		rsContractImpl.setRsAlias(rsContract.getRsAlias());
		rsContractImpl.setFormulaMethodId(rsContract.getFormulaMethodId());
		rsContractImpl.setRebateProcessingType(rsContract.getRebateProcessingType());
		rsContractImpl.setRsContractAttachedStatus(rsContract.getRsContractAttachedStatus());
		rsContractImpl.setInterestBearingBasis(rsContract.getInterestBearingBasis());
		rsContractImpl.setModifiedDate(rsContract.getModifiedDate());
		rsContractImpl.setRsTransRefName(rsContract.getRsTransRefName());
		rsContractImpl.setRebateProgramType(rsContract.getRebateProgramType());
		rsContractImpl.setRebatePlanLevel(rsContract.getRebatePlanLevel());
		rsContractImpl.setSource(rsContract.getSource());
		rsContractImpl.setRsCalendar(rsContract.getRsCalendar());
		rsContractImpl.setRsType(rsContract.getRsType());
		rsContractImpl.setAddress1(rsContract.getAddress1());
		rsContractImpl.setAddress2(rsContract.getAddress2());
		rsContractImpl.setRsEndDate(rsContract.getRsEndDate());
		rsContractImpl.setModifiedBy(rsContract.getModifiedBy());
		rsContractImpl.setRsTransRefNo(rsContract.getRsTransRefNo());
		rsContractImpl.setZipCode(rsContract.getZipCode());
		rsContractImpl.setRebateRuleAssociation(rsContract.getRebateRuleAssociation());
		rsContractImpl.setState(rsContract.getState());
		rsContractImpl.setRebateFrequency(rsContract.getRebateFrequency());
		rsContractImpl.setRsDesignation(rsContract.getRsDesignation());
		rsContractImpl.setBatchId(rsContract.getBatchId());
		rsContractImpl.setIfpContractSid(rsContract.getIfpContractSid());
		rsContractImpl.setRsContractSid(rsContract.getRsContractSid());
		rsContractImpl.setPaymentTerms(rsContract.getPaymentTerms());
		rsContractImpl.setRsNo(rsContract.getRsNo());
		rsContractImpl.setRsModelSid(rsContract.getRsModelSid());
		rsContractImpl.setRsValidationProfile(rsContract.getRsValidationProfile());
		rsContractImpl.setPaymentGracePeriod(rsContract.getPaymentGracePeriod());
		rsContractImpl.setPaymentFrequency(rsContract.getPaymentFrequency());
		rsContractImpl.setRecordLockStatus(rsContract.isRecordLockStatus());
		rsContractImpl.setRsId(rsContract.getRsId());
		rsContractImpl.setCity(rsContract.getCity());
		rsContractImpl.setParentRsName(rsContract.getParentRsName());
		rsContractImpl.setInterestBearingIndicator(rsContract.getInterestBearingIndicator());
		rsContractImpl.setParentRsId(rsContract.getParentRsId());
		rsContractImpl.setInboundStatus(rsContract.getInboundStatus());
		rsContractImpl.setCalculationType(rsContract.getCalculationType());
		rsContractImpl.setCalculationLevel(rsContract.getCalculationLevel());
		rsContractImpl.setCalculationRule(rsContract.getCalculationRule());
		rsContractImpl.setCalculationRuleLevel(rsContract.getCalculationRuleLevel());
		rsContractImpl.setEvaluationRuleType(rsContract.getEvaluationRuleType());
		rsContractImpl.setEvaluationRuleLevel(rsContract.getEvaluationRuleLevel());
		rsContractImpl.setEvaluationRuleOrAssociation(rsContract.getEvaluationRuleOrAssociation());
		rsContractImpl.setDeductionInclusion(rsContract.getDeductionInclusion());

		return rsContractImpl;
	}

	/**
	 * Returns the rs contract with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract
	 * @return the rs contract
	 * @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRsContractException {
		RsContract rsContract = fetchByPrimaryKey(primaryKey);

		if (rsContract == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRsContractException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return rsContract;
	}

	/**
	 * Returns the rs contract with the primary key or throws a {@link NoSuchRsContractException} if it could not be found.
	 *
	 * @param rsContractSid the primary key of the rs contract
	 * @return the rs contract
	 * @throws NoSuchRsContractException if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract findByPrimaryKey(int rsContractSid)
		throws NoSuchRsContractException {
		return findByPrimaryKey((Serializable)rsContractSid);
	}

	/**
	 * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the rs contract
	 * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
				RsContractImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		RsContract rsContract = (RsContract)serializable;

		if (rsContract == null) {
			Session session = null;

			try {
				session = openSession();

				rsContract = (RsContract)session.get(RsContractImpl.class,
						primaryKey);

				if (rsContract != null) {
					cacheResult(rsContract);
				}
				else {
					entityCache.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
						RsContractImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
					RsContractImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return rsContract;
	}

	/**
	 * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param rsContractSid the primary key of the rs contract
	 * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
	 */
	@Override
	public RsContract fetchByPrimaryKey(int rsContractSid) {
		return fetchByPrimaryKey((Serializable)rsContractSid);
	}

	@Override
	public Map<Serializable, RsContract> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RsContract> map = new HashMap<Serializable, RsContract>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RsContract rsContract = fetchByPrimaryKey(primaryKey);

			if (rsContract != null) {
				map.put(primaryKey, rsContract);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
					RsContractImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (RsContract)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RSCONTRACT_WHERE_PKS_IN);

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

			for (RsContract rsContract : (List<RsContract>)q.list()) {
				map.put(rsContract.getPrimaryKeyObj(), rsContract);

				cacheResult(rsContract);

				uncachedPrimaryKeys.remove(rsContract.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RsContractModelImpl.ENTITY_CACHE_ENABLED,
					RsContractImpl.class, primaryKey, nullModel);
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
	 * Returns all the rs contracts.
	 *
	 * @return the rs contracts
	 */
	@Override
	public List<RsContract> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the rs contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contracts
	 * @param end the upper bound of the range of rs contracts (not inclusive)
	 * @return the range of rs contracts
	 */
	@Override
	public List<RsContract> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the rs contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contracts
	 * @param end the upper bound of the range of rs contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of rs contracts
	 */
	@Override
	public List<RsContract> findAll(int start, int end,
		OrderByComparator<RsContract> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the rs contracts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of rs contracts
	 * @param end the upper bound of the range of rs contracts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of rs contracts
	 */
	@Override
	public List<RsContract> findAll(int start, int end,
		OrderByComparator<RsContract> orderByComparator,
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

		List<RsContract> list = null;

		if (retrieveFromCache) {
			list = (List<RsContract>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_RSCONTRACT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RSCONTRACT;

				if (pagination) {
					sql = sql.concat(RsContractModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RsContract>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RsContract>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the rs contracts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RsContract rsContract : findAll()) {
			remove(rsContract);
		}
	}

	/**
	 * Returns the number of rs contracts.
	 *
	 * @return the number of rs contracts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RSCONTRACT);

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
		return RsContractModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the rs contract persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RsContractImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_RSCONTRACT = "SELECT rsContract FROM RsContract rsContract";
	private static final String _SQL_SELECT_RSCONTRACT_WHERE_PKS_IN = "SELECT rsContract FROM RsContract rsContract WHERE RS_CONTRACT_SID IN (";
	private static final String _SQL_COUNT_RSCONTRACT = "SELECT COUNT(rsContract) FROM RsContract rsContract";
	private static final String _ORDER_BY_ENTITY_ALIAS = "rsContract.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RsContract exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(RsContractPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"cfpContractSid", "createdDate", "psContractSid", "rsName",
				"rsStatus", "rsStartDate", "rsTransRefId", "makePayableTo",
				"createdBy", "rsCategory", "rsTradeClass", "contractMasterSid",
				"rebateRuleType", "paymentMethod", "rsContractAttachedDate",
				"rsAlias", "formulaMethodId", "rebateProcessingType",
				"rsContractAttachedStatus", "interestBearingBasis",
				"modifiedDate", "rsTransRefName", "rebateProgramType",
				"rebatePlanLevel", "source", "rsCalendar", "rsType", "address1",
				"address2", "rsEndDate", "modifiedBy", "rsTransRefNo", "zipCode",
				"rebateRuleAssociation", "state", "rebateFrequency",
				"rsDesignation", "batchId", "ifpContractSid", "rsContractSid",
				"paymentTerms", "rsNo", "rsModelSid", "rsValidationProfile",
				"paymentGracePeriod", "paymentFrequency", "recordLockStatus",
				"rsId", "city", "parentRsName", "interestBearingIndicator",
				"parentRsId", "inboundStatus", "calculationType",
				"calculationLevel", "calculationRule", "calculationRuleLevel",
				"evaluationRuleType", "evaluationRuleLevel",
				"evaluationRuleOrAssociation", "deductionInclusion"
			});
}