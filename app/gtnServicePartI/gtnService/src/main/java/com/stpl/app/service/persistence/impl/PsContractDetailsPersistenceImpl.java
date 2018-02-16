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

import com.stpl.app.exception.NoSuchPsContractDetailsException;
import com.stpl.app.model.PsContractDetails;
import com.stpl.app.model.impl.PsContractDetailsImpl;
import com.stpl.app.model.impl.PsContractDetailsModelImpl;
import com.stpl.app.service.persistence.PsContractDetailsPersistence;

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
 * The persistence implementation for the ps contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractDetailsPersistence
 * @see com.stpl.app.service.persistence.PsContractDetailsUtil
 * @generated
 */
@ProviderType
public class PsContractDetailsPersistenceImpl extends BasePersistenceImpl<PsContractDetails>
	implements PsContractDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PsContractDetailsUtil} to access the ps contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PsContractDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			PsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsModelImpl.FINDER_CACHE_ENABLED,
			PsContractDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PsContractDetailsPersistenceImpl() {
		setModelClass(PsContractDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("priceProtectionStartDate",
				"PRICE_PROTECTION_START_DATE");
			dbColumnNames.put("basePrice", "BASE_PRICE");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("revisionDate", "REVISION_DATE");
			dbColumnNames.put("priceTolerance", "PRICE_TOLERANCE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("suggestedPrice", "SUGGESTED_PRICE");
			dbColumnNames.put("psContractAttachedDate",
				"PS_CONTRACT_ATTACHED_DATE");
			dbColumnNames.put("psContractDetailsSid", "PS_CONTRACT_DETAILS_SID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");
			dbColumnNames.put("contractPrice", "CONTRACT_PRICE");
			dbColumnNames.put("priceToleranceType", "PRICE_TOLERANCE_TYPE");
			dbColumnNames.put("itemPricingQualifierSid",
				"ITEM_PRICING_QUALIFIER_SID");
			dbColumnNames.put("contractPriceEndDate", "CONTRACT_PRICE_END_DATE");
			dbColumnNames.put("priceToleranceFrequency",
				"PRICE_TOLERANCE_FREQUENCY");
			dbColumnNames.put("contractPriceStartDate",
				"CONTRACT_PRICE_START_DATE");
			dbColumnNames.put("psContractSid", "PS_CONTRACT_SID");
			dbColumnNames.put("priceProtectionEndDate",
				"PRICE_PROTECTION_END_DATE");
			dbColumnNames.put("psContractAttachedStatus",
				"PS_CONTRACT_ATTACHED_STATUS");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("priceToleranceInterval",
				"PRICE_TOLERANCE_INTERVAL");
			dbColumnNames.put("priceRevision", "PRICE_REVISION");
			dbColumnNames.put("brandMasterSid", "BRAND_MASTER_SID");
			dbColumnNames.put("nep", "NEP");
			dbColumnNames.put("priceProtectionStatus", "PRICE_PROTECTION_STATUS");
			dbColumnNames.put("priceProtectionPriceType",
				"PRICE_PROTECTION_PRICE_TYPE");
			dbColumnNames.put("nepFormula", "NEP_FORMULA");
			dbColumnNames.put("maxIncrementalChange", "MAX_INCREMENTAL_CHANGE");
			dbColumnNames.put("resetEligible", "RESET_ELIGIBLE");
			dbColumnNames.put("resetType", "RESET_TYPE");
			dbColumnNames.put("resetDate", "RESET_DATE");
			dbColumnNames.put("resetInterval", "RESET_INTERVAL");
			dbColumnNames.put("resetFrequency", "RESET_FREQUENCY");
			dbColumnNames.put("netPriceType", "NET_PRICE_TYPE");
			dbColumnNames.put("netPriceTypeFormula", "NET_PRICE_TYPE_FORMULA");
			dbColumnNames.put("basePriceType", "BASE_PRICE_TYPE");
			dbColumnNames.put("basePriceEntry", "BASE_PRICE_ENTRY");
			dbColumnNames.put("basePriceDate", "BASE_PRICE_DATE");
			dbColumnNames.put("basePriceDdlb", "BASE_PRICE_DDLB");
			dbColumnNames.put("netBasePrice", "NET_BASE_PRICE");
			dbColumnNames.put("netBasePriceFormulaId",
				"NET_BASE_PRICE_FORMULA_ID");
			dbColumnNames.put("subsequentPeriodPriceType",
				"SUBSEQUENT_PERIOD_PRICE_TYPE");
			dbColumnNames.put("netSubsequentPeriodPrice",
				"NET_SUBSEQUENT_PERIOD_PRICE");
			dbColumnNames.put("netSubsequentPriceFormulaId",
				"NET_SUBSEQUENT_PRICE_FORMULA_ID");
			dbColumnNames.put("resetPriceType", "RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceType", "NET_RESET_PRICE_TYPE");
			dbColumnNames.put("netResetPriceFormulaId",
				"NET_RESET_PRICE_FORMULA_ID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ps contract details in the entity cache if it is enabled.
	 *
	 * @param psContractDetails the ps contract details
	 */
	@Override
	public void cacheResult(PsContractDetails psContractDetails) {
		entityCache.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsImpl.class, psContractDetails.getPrimaryKey(),
			psContractDetails);

		psContractDetails.resetOriginalValues();
	}

	/**
	 * Caches the ps contract detailses in the entity cache if it is enabled.
	 *
	 * @param psContractDetailses the ps contract detailses
	 */
	@Override
	public void cacheResult(List<PsContractDetails> psContractDetailses) {
		for (PsContractDetails psContractDetails : psContractDetailses) {
			if (entityCache.getResult(
						PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PsContractDetailsImpl.class,
						psContractDetails.getPrimaryKey()) == null) {
				cacheResult(psContractDetails);
			}
			else {
				psContractDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ps contract detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PsContractDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ps contract details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PsContractDetails psContractDetails) {
		entityCache.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsImpl.class, psContractDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PsContractDetails> psContractDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PsContractDetails psContractDetails : psContractDetailses) {
			entityCache.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PsContractDetailsImpl.class, psContractDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
	 *
	 * @param psContractDetailsSid the primary key for the new ps contract details
	 * @return the new ps contract details
	 */
	@Override
	public PsContractDetails create(int psContractDetailsSid) {
		PsContractDetails psContractDetails = new PsContractDetailsImpl();

		psContractDetails.setNew(true);
		psContractDetails.setPrimaryKey(psContractDetailsSid);

		return psContractDetails;
	}

	/**
	 * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param psContractDetailsSid the primary key of the ps contract details
	 * @return the ps contract details that was removed
	 * @throws NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails remove(int psContractDetailsSid)
		throws NoSuchPsContractDetailsException {
		return remove((Serializable)psContractDetailsSid);
	}

	/**
	 * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ps contract details
	 * @return the ps contract details that was removed
	 * @throws NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails remove(Serializable primaryKey)
		throws NoSuchPsContractDetailsException {
		Session session = null;

		try {
			session = openSession();

			PsContractDetails psContractDetails = (PsContractDetails)session.get(PsContractDetailsImpl.class,
					primaryKey);

			if (psContractDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(psContractDetails);
		}
		catch (NoSuchPsContractDetailsException nsee) {
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
	protected PsContractDetails removeImpl(PsContractDetails psContractDetails) {
		psContractDetails = toUnwrappedModel(psContractDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(psContractDetails)) {
				psContractDetails = (PsContractDetails)session.get(PsContractDetailsImpl.class,
						psContractDetails.getPrimaryKeyObj());
			}

			if (psContractDetails != null) {
				session.delete(psContractDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (psContractDetails != null) {
			clearCache(psContractDetails);
		}

		return psContractDetails;
	}

	@Override
	public PsContractDetails updateImpl(PsContractDetails psContractDetails) {
		psContractDetails = toUnwrappedModel(psContractDetails);

		boolean isNew = psContractDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (psContractDetails.isNew()) {
				session.save(psContractDetails);

				psContractDetails.setNew(false);
			}
			else {
				psContractDetails = (PsContractDetails)session.merge(psContractDetails);
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

		entityCache.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
			PsContractDetailsImpl.class, psContractDetails.getPrimaryKey(),
			psContractDetails, false);

		psContractDetails.resetOriginalValues();

		return psContractDetails;
	}

	protected PsContractDetails toUnwrappedModel(
		PsContractDetails psContractDetails) {
		if (psContractDetails instanceof PsContractDetailsImpl) {
			return psContractDetails;
		}

		PsContractDetailsImpl psContractDetailsImpl = new PsContractDetailsImpl();

		psContractDetailsImpl.setNew(psContractDetails.isNew());
		psContractDetailsImpl.setPrimaryKey(psContractDetails.getPrimaryKey());

		psContractDetailsImpl.setPrice(psContractDetails.getPrice());
		psContractDetailsImpl.setItemMasterSid(psContractDetails.getItemMasterSid());
		psContractDetailsImpl.setPriceProtectionStartDate(psContractDetails.getPriceProtectionStartDate());
		psContractDetailsImpl.setBasePrice(psContractDetails.getBasePrice());
		psContractDetailsImpl.setModifiedDate(psContractDetails.getModifiedDate());
		psContractDetailsImpl.setRevisionDate(psContractDetails.getRevisionDate());
		psContractDetailsImpl.setPriceTolerance(psContractDetails.getPriceTolerance());
		psContractDetailsImpl.setCreatedDate(psContractDetails.getCreatedDate());
		psContractDetailsImpl.setSource(psContractDetails.getSource());
		psContractDetailsImpl.setCreatedBy(psContractDetails.getCreatedBy());
		psContractDetailsImpl.setSuggestedPrice(psContractDetails.getSuggestedPrice());
		psContractDetailsImpl.setPsContractAttachedDate(psContractDetails.getPsContractAttachedDate());
		psContractDetailsImpl.setPsContractDetailsSid(psContractDetails.getPsContractDetailsSid());
		psContractDetailsImpl.setModifiedBy(psContractDetails.getModifiedBy());
		psContractDetailsImpl.setInboundStatus(psContractDetails.getInboundStatus());
		psContractDetailsImpl.setContractPrice(psContractDetails.getContractPrice());
		psContractDetailsImpl.setPriceToleranceType(psContractDetails.getPriceToleranceType());
		psContractDetailsImpl.setItemPricingQualifierSid(psContractDetails.getItemPricingQualifierSid());
		psContractDetailsImpl.setContractPriceEndDate(psContractDetails.getContractPriceEndDate());
		psContractDetailsImpl.setPriceToleranceFrequency(psContractDetails.getPriceToleranceFrequency());
		psContractDetailsImpl.setContractPriceStartDate(psContractDetails.getContractPriceStartDate());
		psContractDetailsImpl.setPsContractSid(psContractDetails.getPsContractSid());
		psContractDetailsImpl.setPriceProtectionEndDate(psContractDetails.getPriceProtectionEndDate());
		psContractDetailsImpl.setPsContractAttachedStatus(psContractDetails.getPsContractAttachedStatus());
		psContractDetailsImpl.setRecordLockStatus(psContractDetails.isRecordLockStatus());
		psContractDetailsImpl.setBatchId(psContractDetails.getBatchId());
		psContractDetailsImpl.setPriceToleranceInterval(psContractDetails.getPriceToleranceInterval());
		psContractDetailsImpl.setPriceRevision(psContractDetails.getPriceRevision());
		psContractDetailsImpl.setBrandMasterSid(psContractDetails.getBrandMasterSid());
		psContractDetailsImpl.setNep(psContractDetails.getNep());
		psContractDetailsImpl.setPriceProtectionStatus(psContractDetails.getPriceProtectionStatus());
		psContractDetailsImpl.setPriceProtectionPriceType(psContractDetails.getPriceProtectionPriceType());
		psContractDetailsImpl.setNepFormula(psContractDetails.getNepFormula());
		psContractDetailsImpl.setMaxIncrementalChange(psContractDetails.getMaxIncrementalChange());
		psContractDetailsImpl.setResetEligible(psContractDetails.getResetEligible());
		psContractDetailsImpl.setResetType(psContractDetails.getResetType());
		psContractDetailsImpl.setResetDate(psContractDetails.getResetDate());
		psContractDetailsImpl.setResetInterval(psContractDetails.getResetInterval());
		psContractDetailsImpl.setResetFrequency(psContractDetails.getResetFrequency());
		psContractDetailsImpl.setNetPriceType(psContractDetails.getNetPriceType());
		psContractDetailsImpl.setNetPriceTypeFormula(psContractDetails.getNetPriceTypeFormula());
		psContractDetailsImpl.setBasePriceType(psContractDetails.getBasePriceType());
		psContractDetailsImpl.setBasePriceEntry(psContractDetails.getBasePriceEntry());
		psContractDetailsImpl.setBasePriceDate(psContractDetails.getBasePriceDate());
		psContractDetailsImpl.setBasePriceDdlb(psContractDetails.getBasePriceDdlb());
		psContractDetailsImpl.setNetBasePrice(psContractDetails.getNetBasePrice());
		psContractDetailsImpl.setNetBasePriceFormulaId(psContractDetails.getNetBasePriceFormulaId());
		psContractDetailsImpl.setSubsequentPeriodPriceType(psContractDetails.getSubsequentPeriodPriceType());
		psContractDetailsImpl.setNetSubsequentPeriodPrice(psContractDetails.getNetSubsequentPeriodPrice());
		psContractDetailsImpl.setNetSubsequentPriceFormulaId(psContractDetails.getNetSubsequentPriceFormulaId());
		psContractDetailsImpl.setResetPriceType(psContractDetails.getResetPriceType());
		psContractDetailsImpl.setNetResetPriceType(psContractDetails.getNetResetPriceType());
		psContractDetailsImpl.setNetResetPriceFormulaId(psContractDetails.getNetResetPriceFormulaId());

		return psContractDetailsImpl;
	}

	/**
	 * Returns the ps contract details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps contract details
	 * @return the ps contract details
	 * @throws NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPsContractDetailsException {
		PsContractDetails psContractDetails = fetchByPrimaryKey(primaryKey);

		if (psContractDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPsContractDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return psContractDetails;
	}

	/**
	 * Returns the ps contract details with the primary key or throws a {@link NoSuchPsContractDetailsException} if it could not be found.
	 *
	 * @param psContractDetailsSid the primary key of the ps contract details
	 * @return the ps contract details
	 * @throws NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails findByPrimaryKey(int psContractDetailsSid)
		throws NoSuchPsContractDetailsException {
		return findByPrimaryKey((Serializable)psContractDetailsSid);
	}

	/**
	 * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ps contract details
	 * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
				PsContractDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		PsContractDetails psContractDetails = (PsContractDetails)serializable;

		if (psContractDetails == null) {
			Session session = null;

			try {
				session = openSession();

				psContractDetails = (PsContractDetails)session.get(PsContractDetailsImpl.class,
						primaryKey);

				if (psContractDetails != null) {
					cacheResult(psContractDetails);
				}
				else {
					entityCache.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
						PsContractDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsContractDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return psContractDetails;
	}

	/**
	 * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param psContractDetailsSid the primary key of the ps contract details
	 * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
	 */
	@Override
	public PsContractDetails fetchByPrimaryKey(int psContractDetailsSid) {
		return fetchByPrimaryKey((Serializable)psContractDetailsSid);
	}

	@Override
	public Map<Serializable, PsContractDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PsContractDetails> map = new HashMap<Serializable, PsContractDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PsContractDetails psContractDetails = fetchByPrimaryKey(primaryKey);

			if (psContractDetails != null) {
				map.put(primaryKey, psContractDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsContractDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (PsContractDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PSCONTRACTDETAILS_WHERE_PKS_IN);

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

			for (PsContractDetails psContractDetails : (List<PsContractDetails>)q.list()) {
				map.put(psContractDetails.getPrimaryKeyObj(), psContractDetails);

				cacheResult(psContractDetails);

				uncachedPrimaryKeys.remove(psContractDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PsContractDetailsModelImpl.ENTITY_CACHE_ENABLED,
					PsContractDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ps contract detailses.
	 *
	 * @return the ps contract detailses
	 */
	@Override
	public List<PsContractDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ps contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contract detailses
	 * @param end the upper bound of the range of ps contract detailses (not inclusive)
	 * @return the range of ps contract detailses
	 */
	@Override
	public List<PsContractDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ps contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contract detailses
	 * @param end the upper bound of the range of ps contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ps contract detailses
	 */
	@Override
	public List<PsContractDetails> findAll(int start, int end,
		OrderByComparator<PsContractDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ps contract detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ps contract detailses
	 * @param end the upper bound of the range of ps contract detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ps contract detailses
	 */
	@Override
	public List<PsContractDetails> findAll(int start, int end,
		OrderByComparator<PsContractDetails> orderByComparator,
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

		List<PsContractDetails> list = null;

		if (retrieveFromCache) {
			list = (List<PsContractDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PSCONTRACTDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PSCONTRACTDETAILS;

				if (pagination) {
					sql = sql.concat(PsContractDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PsContractDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PsContractDetails>)QueryUtil.list(q,
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
	 * Removes all the ps contract detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PsContractDetails psContractDetails : findAll()) {
			remove(psContractDetails);
		}
	}

	/**
	 * Returns the number of ps contract detailses.
	 *
	 * @return the number of ps contract detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PSCONTRACTDETAILS);

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
		return PsContractDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ps contract details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PsContractDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PSCONTRACTDETAILS = "SELECT psContractDetails FROM PsContractDetails psContractDetails";
	private static final String _SQL_SELECT_PSCONTRACTDETAILS_WHERE_PKS_IN = "SELECT psContractDetails FROM PsContractDetails psContractDetails WHERE PS_CONTRACT_DETAILS_SID IN (";
	private static final String _SQL_COUNT_PSCONTRACTDETAILS = "SELECT COUNT(psContractDetails) FROM PsContractDetails psContractDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "psContractDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PsContractDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(PsContractDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"price", "itemMasterSid", "priceProtectionStartDate",
				"basePrice", "modifiedDate", "revisionDate", "priceTolerance",
				"createdDate", "source", "createdBy", "suggestedPrice",
				"psContractAttachedDate", "psContractDetailsSid", "modifiedBy",
				"inboundStatus", "contractPrice", "priceToleranceType",
				"itemPricingQualifierSid", "contractPriceEndDate",
				"priceToleranceFrequency", "contractPriceStartDate",
				"psContractSid", "priceProtectionEndDate",
				"psContractAttachedStatus", "recordLockStatus", "batchId",
				"priceToleranceInterval", "priceRevision", "brandMasterSid",
				"nep", "priceProtectionStatus", "priceProtectionPriceType",
				"nepFormula", "maxIncrementalChange", "resetEligible",
				"resetType", "resetDate", "resetInterval", "resetFrequency",
				"netPriceType", "netPriceTypeFormula", "basePriceType",
				"basePriceEntry", "basePriceDate", "basePriceDdlb",
				"netBasePrice", "netBasePriceFormulaId",
				"subsequentPeriodPriceType", "netSubsequentPeriodPrice",
				"netSubsequentPriceFormulaId", "resetPriceType",
				"netResetPriceType", "netResetPriceFormulaId"
			});
}