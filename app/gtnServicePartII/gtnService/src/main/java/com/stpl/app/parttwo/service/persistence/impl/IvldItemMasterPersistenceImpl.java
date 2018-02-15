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

import com.stpl.app.parttwo.exception.NoSuchIvldItemMasterException;
import com.stpl.app.parttwo.model.IvldItemMaster;
import com.stpl.app.parttwo.model.impl.IvldItemMasterImpl;
import com.stpl.app.parttwo.model.impl.IvldItemMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemMasterPersistence;

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
 * The persistence implementation for the ivld item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldItemMasterUtil
 * @generated
 */
@ProviderType
public class IvldItemMasterPersistenceImpl extends BasePersistenceImpl<IvldItemMaster>
	implements IvldItemMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldItemMasterUtil} to access the ivld item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldItemMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldItemMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterModelImpl.FINDER_CACHE_ENABLED,
			IvldItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldItemMasterPersistenceImpl() {
		setModelClass(IvldItemMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("newFormulationIndicator",
				"NEW_FORMULATION_INDICATOR");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("newFormulationEndDate",
				"NEW_FORMULATION_END_DATE");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("clottingFactorStartDate",
				"CLOTTING_FACTOR_START_DATE");
			dbColumnNames.put("secondaryUom", "SECONDARY_UOM");
			dbColumnNames.put("itemDesc", "ITEM_DESC");
			dbColumnNames.put("authorizedGenericEndDate",
				"AUTHORIZED_GENERIC_END_DATE");
			dbColumnNames.put("manufacturerName", "MANUFACTURER_NAME");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("status", "STATUS");
			dbColumnNames.put("baseCpi", "BASE_CPI");
			dbColumnNames.put("baselineAmp", "BASELINE_AMP");
			dbColumnNames.put("authorizedGeneric", "AUTHORIZED_GENERIC");
			dbColumnNames.put("therapeuticClass", "THERAPEUTIC_CLASS");
			dbColumnNames.put("itemFamilyId", "ITEM_FAMILY_ID");
			dbColumnNames.put("pediatricExclusiveStartDate",
				"PEDIATRIC_EXCLUSIVE_START_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("primaryUom", "PRIMARY_UOM");
			dbColumnNames.put("ndc9", "NDC9");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("lastLotExpirationDate",
				"LAST_LOT_EXPIRATION_DATE");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("itemCode", "ITEM_CODE");
			dbColumnNames.put("strength", "STRENGTH");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("ndc8", "NDC8");
			dbColumnNames.put("labelerCode", "LABELER_CODE");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("acquiredAmp", "ACQUIRED_AMP");
			dbColumnNames.put("discontinuationDate", "DISCONTINUATION_DATE");
			dbColumnNames.put("itemMasterIntfid", "ITEM_MASTER_INTFID");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("divestitureDate", "DIVESTITURE_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("baseCpiPeriod", "BASE_CPI_PERIOD");
			dbColumnNames.put("clottingFactorEndDate",
				"CLOTTING_FACTOR_END_DATE");
			dbColumnNames.put("dosesPerUnit", "DOSES_PER_UNIT");
			dbColumnNames.put("manufacturerId", "MANUFACTURER_ID");
			dbColumnNames.put("clottingFactorIndicator",
				"CLOTTING_FACTOR_INDICATOR");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("acquisitionDate", "ACQUISITION_DATE");
			dbColumnNames.put("dualPricingIndicator", "DUAL_PRICING_INDICATOR");
			dbColumnNames.put("nonFederalExpirationDate",
				"NON_FEDERAL_EXPIRATION_DATE");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("newFormulation", "NEW_FORMULATION");
			dbColumnNames.put("obraBamp", "OBRA_BAMP");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("authorizedGenericStartDate",
				"AUTHORIZED_GENERIC_START_DATE");
			dbColumnNames.put("newFormulationStartDate",
				"NEW_FORMULATION_START_DATE");
			dbColumnNames.put("itemCategory", "ITEM_CATEGORY");
			dbColumnNames.put("itemEndDate", "ITEM_END_DATE");
			dbColumnNames.put("itemType", "ITEM_TYPE");
			dbColumnNames.put("pediatricExclusiveEndDate",
				"PEDIATRIC_EXCLUSIVE_END_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("firstSaleDate", "FIRST_SALE_DATE");
			dbColumnNames.put("shelfLifeType", "SHELF_LIFE_TYPE");
			dbColumnNames.put("itemStartDate", "ITEM_START_DATE");
			dbColumnNames.put("itemTypeIndication", "ITEM_TYPE_INDICATION");
			dbColumnNames.put("acquiredBamp", "ACQUIRED_BAMP");
			dbColumnNames.put("form", "FORM");
			dbColumnNames.put("itemClass", "ITEM_CLASS");
			dbColumnNames.put("manufacturerNo", "MANUFACTURER_NO");
			dbColumnNames.put("pediatricExclusiveIndicator",
				"PEDIATRIC_EXCLUSIVE_INDICATOR");
			dbColumnNames.put("packageSizeCode", "PACKAGE_SIZE_CODE");
			dbColumnNames.put("displayBrand", "DISPLAY_BRAND");
			dbColumnNames.put("dra", "DRA");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("packageSizeIntroDate", "PACKAGE_SIZE_INTRO_DATE");
			dbColumnNames.put("upps", "UPPS");
			dbColumnNames.put("ivldItemMasterSid", "IVLD_ITEM_MASTER_SID");
			dbColumnNames.put("packageSize", "PACKAGE_SIZE");
			dbColumnNames.put("shelfLife", "SHELF_LIFE");
			dbColumnNames.put("marketTerminationDate", "MARKET_TERMINATION_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("baseCpiPrecision", "BASE_CPI_PRECISION");
			dbColumnNames.put("baselineAmpPrecision", "BASELINE_AMP_PRECISION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld item master in the entity cache if it is enabled.
	 *
	 * @param ivldItemMaster the ivld item master
	 */
	@Override
	public void cacheResult(IvldItemMaster ivldItemMaster) {
		entityCache.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey(),
			ivldItemMaster);

		ivldItemMaster.resetOriginalValues();
	}

	/**
	 * Caches the ivld item masters in the entity cache if it is enabled.
	 *
	 * @param ivldItemMasters the ivld item masters
	 */
	@Override
	public void cacheResult(List<IvldItemMaster> ivldItemMasters) {
		for (IvldItemMaster ivldItemMaster : ivldItemMasters) {
			if (entityCache.getResult(
						IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey()) == null) {
				cacheResult(ivldItemMaster);
			}
			else {
				ivldItemMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld item masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldItemMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld item master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldItemMaster ivldItemMaster) {
		entityCache.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldItemMaster> ivldItemMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldItemMaster ivldItemMaster : ivldItemMasters) {
			entityCache.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld item master with the primary key. Does not add the ivld item master to the database.
	 *
	 * @param ivldItemMasterSid the primary key for the new ivld item master
	 * @return the new ivld item master
	 */
	@Override
	public IvldItemMaster create(int ivldItemMasterSid) {
		IvldItemMaster ivldItemMaster = new IvldItemMasterImpl();

		ivldItemMaster.setNew(true);
		ivldItemMaster.setPrimaryKey(ivldItemMasterSid);

		return ivldItemMaster;
	}

	/**
	 * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldItemMasterSid the primary key of the ivld item master
	 * @return the ivld item master that was removed
	 * @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster remove(int ivldItemMasterSid)
		throws NoSuchIvldItemMasterException {
		return remove((Serializable)ivldItemMasterSid);
	}

	/**
	 * Removes the ivld item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld item master
	 * @return the ivld item master that was removed
	 * @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster remove(Serializable primaryKey)
		throws NoSuchIvldItemMasterException {
		Session session = null;

		try {
			session = openSession();

			IvldItemMaster ivldItemMaster = (IvldItemMaster)session.get(IvldItemMasterImpl.class,
					primaryKey);

			if (ivldItemMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldItemMaster);
		}
		catch (NoSuchIvldItemMasterException nsee) {
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
	protected IvldItemMaster removeImpl(IvldItemMaster ivldItemMaster) {
		ivldItemMaster = toUnwrappedModel(ivldItemMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldItemMaster)) {
				ivldItemMaster = (IvldItemMaster)session.get(IvldItemMasterImpl.class,
						ivldItemMaster.getPrimaryKeyObj());
			}

			if (ivldItemMaster != null) {
				session.delete(ivldItemMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldItemMaster != null) {
			clearCache(ivldItemMaster);
		}

		return ivldItemMaster;
	}

	@Override
	public IvldItemMaster updateImpl(IvldItemMaster ivldItemMaster) {
		ivldItemMaster = toUnwrappedModel(ivldItemMaster);

		boolean isNew = ivldItemMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldItemMaster.isNew()) {
				session.save(ivldItemMaster);

				ivldItemMaster.setNew(false);
			}
			else {
				ivldItemMaster = (IvldItemMaster)session.merge(ivldItemMaster);
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

		entityCache.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemMasterImpl.class, ivldItemMaster.getPrimaryKey(),
			ivldItemMaster, false);

		ivldItemMaster.resetOriginalValues();

		return ivldItemMaster;
	}

	protected IvldItemMaster toUnwrappedModel(IvldItemMaster ivldItemMaster) {
		if (ivldItemMaster instanceof IvldItemMasterImpl) {
			return ivldItemMaster;
		}

		IvldItemMasterImpl ivldItemMasterImpl = new IvldItemMasterImpl();

		ivldItemMasterImpl.setNew(ivldItemMaster.isNew());
		ivldItemMasterImpl.setPrimaryKey(ivldItemMaster.getPrimaryKey());

		ivldItemMasterImpl.setItemNo(ivldItemMaster.getItemNo());
		ivldItemMasterImpl.setUdc6(ivldItemMaster.getUdc6());
		ivldItemMasterImpl.setCreatedDate(ivldItemMaster.getCreatedDate());
		ivldItemMasterImpl.setNewFormulationIndicator(ivldItemMaster.getNewFormulationIndicator());
		ivldItemMasterImpl.setUdc5(ivldItemMaster.getUdc5());
		ivldItemMasterImpl.setNewFormulationEndDate(ivldItemMaster.getNewFormulationEndDate());
		ivldItemMasterImpl.setUdc4(ivldItemMaster.getUdc4());
		ivldItemMasterImpl.setClottingFactorStartDate(ivldItemMaster.getClottingFactorStartDate());
		ivldItemMasterImpl.setSecondaryUom(ivldItemMaster.getSecondaryUom());
		ivldItemMasterImpl.setItemDesc(ivldItemMaster.getItemDesc());
		ivldItemMasterImpl.setAuthorizedGenericEndDate(ivldItemMaster.getAuthorizedGenericEndDate());
		ivldItemMasterImpl.setManufacturerName(ivldItemMaster.getManufacturerName());
		ivldItemMasterImpl.setItemName(ivldItemMaster.getItemName());
		ivldItemMasterImpl.setReprocessedFlag(ivldItemMaster.getReprocessedFlag());
		ivldItemMasterImpl.setStatus(ivldItemMaster.getStatus());
		ivldItemMasterImpl.setBaseCpi(ivldItemMaster.getBaseCpi());
		ivldItemMasterImpl.setBaselineAmp(ivldItemMaster.getBaselineAmp());
		ivldItemMasterImpl.setAuthorizedGeneric(ivldItemMaster.getAuthorizedGeneric());
		ivldItemMasterImpl.setTherapeuticClass(ivldItemMaster.getTherapeuticClass());
		ivldItemMasterImpl.setItemFamilyId(ivldItemMaster.getItemFamilyId());
		ivldItemMasterImpl.setPediatricExclusiveStartDate(ivldItemMaster.getPediatricExclusiveStartDate());
		ivldItemMasterImpl.setCreatedBy(ivldItemMaster.getCreatedBy());
		ivldItemMasterImpl.setPrimaryUom(ivldItemMaster.getPrimaryUom());
		ivldItemMasterImpl.setNdc9(ivldItemMaster.getNdc9());
		ivldItemMasterImpl.setItemId(ivldItemMaster.getItemId());
		ivldItemMasterImpl.setLastLotExpirationDate(ivldItemMaster.getLastLotExpirationDate());
		ivldItemMasterImpl.setErrorField(ivldItemMaster.getErrorField());
		ivldItemMasterImpl.setItemCode(ivldItemMaster.getItemCode());
		ivldItemMasterImpl.setStrength(ivldItemMaster.getStrength());
		ivldItemMasterImpl.setModifiedDate(ivldItemMaster.getModifiedDate());
		ivldItemMasterImpl.setBrand(ivldItemMaster.getBrand());
		ivldItemMasterImpl.setNdc8(ivldItemMaster.getNdc8());
		ivldItemMasterImpl.setLabelerCode(ivldItemMaster.getLabelerCode());
		ivldItemMasterImpl.setUdc3(ivldItemMaster.getUdc3());
		ivldItemMasterImpl.setSource(ivldItemMaster.getSource());
		ivldItemMasterImpl.setUdc2(ivldItemMaster.getUdc2());
		ivldItemMasterImpl.setAddChgDelIndicator(ivldItemMaster.getAddChgDelIndicator());
		ivldItemMasterImpl.setUdc1(ivldItemMaster.getUdc1());
		ivldItemMasterImpl.setAcquiredAmp(ivldItemMaster.getAcquiredAmp());
		ivldItemMasterImpl.setDiscontinuationDate(ivldItemMaster.getDiscontinuationDate());
		ivldItemMasterImpl.setItemMasterIntfid(ivldItemMaster.getItemMasterIntfid());
		ivldItemMasterImpl.setIntfInsertedDate(ivldItemMaster.getIntfInsertedDate());
		ivldItemMasterImpl.setDivestitureDate(ivldItemMaster.getDivestitureDate());
		ivldItemMasterImpl.setModifiedBy(ivldItemMaster.getModifiedBy());
		ivldItemMasterImpl.setBaseCpiPeriod(ivldItemMaster.getBaseCpiPeriod());
		ivldItemMasterImpl.setClottingFactorEndDate(ivldItemMaster.getClottingFactorEndDate());
		ivldItemMasterImpl.setDosesPerUnit(ivldItemMaster.getDosesPerUnit());
		ivldItemMasterImpl.setManufacturerId(ivldItemMaster.getManufacturerId());
		ivldItemMasterImpl.setClottingFactorIndicator(ivldItemMaster.getClottingFactorIndicator());
		ivldItemMasterImpl.setBatchId(ivldItemMaster.getBatchId());
		ivldItemMasterImpl.setAcquisitionDate(ivldItemMaster.getAcquisitionDate());
		ivldItemMasterImpl.setDualPricingIndicator(ivldItemMaster.getDualPricingIndicator());
		ivldItemMasterImpl.setNonFederalExpirationDate(ivldItemMaster.getNonFederalExpirationDate());
		ivldItemMasterImpl.setErrorCode(ivldItemMaster.getErrorCode());
		ivldItemMasterImpl.setNewFormulation(ivldItemMaster.getNewFormulation());
		ivldItemMasterImpl.setObraBamp(ivldItemMaster.getObraBamp());
		ivldItemMasterImpl.setBrandId(ivldItemMaster.getBrandId());
		ivldItemMasterImpl.setItemStatus(ivldItemMaster.getItemStatus());
		ivldItemMasterImpl.setAuthorizedGenericStartDate(ivldItemMaster.getAuthorizedGenericStartDate());
		ivldItemMasterImpl.setNewFormulationStartDate(ivldItemMaster.getNewFormulationStartDate());
		ivldItemMasterImpl.setItemCategory(ivldItemMaster.getItemCategory());
		ivldItemMasterImpl.setItemEndDate(ivldItemMaster.getItemEndDate());
		ivldItemMasterImpl.setItemType(ivldItemMaster.getItemType());
		ivldItemMasterImpl.setPediatricExclusiveEndDate(ivldItemMaster.getPediatricExclusiveEndDate());
		ivldItemMasterImpl.setOrganizationKey(ivldItemMaster.getOrganizationKey());
		ivldItemMasterImpl.setFirstSaleDate(ivldItemMaster.getFirstSaleDate());
		ivldItemMasterImpl.setShelfLifeType(ivldItemMaster.getShelfLifeType());
		ivldItemMasterImpl.setItemStartDate(ivldItemMaster.getItemStartDate());
		ivldItemMasterImpl.setItemTypeIndication(ivldItemMaster.getItemTypeIndication());
		ivldItemMasterImpl.setAcquiredBamp(ivldItemMaster.getAcquiredBamp());
		ivldItemMasterImpl.setForm(ivldItemMaster.getForm());
		ivldItemMasterImpl.setItemClass(ivldItemMaster.getItemClass());
		ivldItemMasterImpl.setManufacturerNo(ivldItemMaster.getManufacturerNo());
		ivldItemMasterImpl.setPediatricExclusiveIndicator(ivldItemMaster.getPediatricExclusiveIndicator());
		ivldItemMasterImpl.setPackageSizeCode(ivldItemMaster.getPackageSizeCode());
		ivldItemMasterImpl.setDisplayBrand(ivldItemMaster.getDisplayBrand());
		ivldItemMasterImpl.setDra(ivldItemMaster.getDra());
		ivldItemMasterImpl.setReasonForFailure(ivldItemMaster.getReasonForFailure());
		ivldItemMasterImpl.setPackageSizeIntroDate(ivldItemMaster.getPackageSizeIntroDate());
		ivldItemMasterImpl.setUpps(ivldItemMaster.getUpps());
		ivldItemMasterImpl.setIvldItemMasterSid(ivldItemMaster.getIvldItemMasterSid());
		ivldItemMasterImpl.setPackageSize(ivldItemMaster.getPackageSize());
		ivldItemMasterImpl.setShelfLife(ivldItemMaster.getShelfLife());
		ivldItemMasterImpl.setMarketTerminationDate(ivldItemMaster.getMarketTerminationDate());
		ivldItemMasterImpl.setCheckRecord(ivldItemMaster.isCheckRecord());
		ivldItemMasterImpl.setBaseCpiPrecision(ivldItemMaster.getBaseCpiPrecision());
		ivldItemMasterImpl.setBaselineAmpPrecision(ivldItemMaster.getBaselineAmpPrecision());

		return ivldItemMasterImpl;
	}

	/**
	 * Returns the ivld item master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item master
	 * @return the ivld item master
	 * @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldItemMasterException {
		IvldItemMaster ivldItemMaster = fetchByPrimaryKey(primaryKey);

		if (ivldItemMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldItemMaster;
	}

	/**
	 * Returns the ivld item master with the primary key or throws a {@link NoSuchIvldItemMasterException} if it could not be found.
	 *
	 * @param ivldItemMasterSid the primary key of the ivld item master
	 * @return the ivld item master
	 * @throws NoSuchIvldItemMasterException if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster findByPrimaryKey(int ivldItemMasterSid)
		throws NoSuchIvldItemMasterException {
		return findByPrimaryKey((Serializable)ivldItemMasterSid);
	}

	/**
	 * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item master
	 * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldItemMaster ivldItemMaster = (IvldItemMaster)serializable;

		if (ivldItemMaster == null) {
			Session session = null;

			try {
				session = openSession();

				ivldItemMaster = (IvldItemMaster)session.get(IvldItemMasterImpl.class,
						primaryKey);

				if (ivldItemMaster != null) {
					cacheResult(ivldItemMaster);
				}
				else {
					entityCache.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldItemMaster;
	}

	/**
	 * Returns the ivld item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldItemMasterSid the primary key of the ivld item master
	 * @return the ivld item master, or <code>null</code> if a ivld item master with the primary key could not be found
	 */
	@Override
	public IvldItemMaster fetchByPrimaryKey(int ivldItemMasterSid) {
		return fetchByPrimaryKey((Serializable)ivldItemMasterSid);
	}

	@Override
	public Map<Serializable, IvldItemMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldItemMaster> map = new HashMap<Serializable, IvldItemMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldItemMaster ivldItemMaster = fetchByPrimaryKey(primaryKey);

			if (ivldItemMaster != null) {
				map.put(primaryKey, ivldItemMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldItemMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDITEMMASTER_WHERE_PKS_IN);

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

			for (IvldItemMaster ivldItemMaster : (List<IvldItemMaster>)q.list()) {
				map.put(ivldItemMaster.getPrimaryKeyObj(), ivldItemMaster);

				cacheResult(ivldItemMaster);

				uncachedPrimaryKeys.remove(ivldItemMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld item masters.
	 *
	 * @return the ivld item masters
	 */
	@Override
	public List<IvldItemMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item masters
	 * @param end the upper bound of the range of ivld item masters (not inclusive)
	 * @return the range of ivld item masters
	 */
	@Override
	public List<IvldItemMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item masters
	 * @param end the upper bound of the range of ivld item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld item masters
	 */
	@Override
	public List<IvldItemMaster> findAll(int start, int end,
		OrderByComparator<IvldItemMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item masters
	 * @param end the upper bound of the range of ivld item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld item masters
	 */
	@Override
	public List<IvldItemMaster> findAll(int start, int end,
		OrderByComparator<IvldItemMaster> orderByComparator,
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

		List<IvldItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<IvldItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDITEMMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDITEMMASTER;

				if (pagination) {
					sql = sql.concat(IvldItemMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldItemMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldItemMaster>)QueryUtil.list(q,
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
	 * Removes all the ivld item masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldItemMaster ivldItemMaster : findAll()) {
			remove(ivldItemMaster);
		}
	}

	/**
	 * Returns the number of ivld item masters.
	 *
	 * @return the number of ivld item masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDITEMMASTER);

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
		return IvldItemMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld item master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldItemMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDITEMMASTER = "SELECT ivldItemMaster FROM IvldItemMaster ivldItemMaster";
	private static final String _SQL_SELECT_IVLDITEMMASTER_WHERE_PKS_IN = "SELECT ivldItemMaster FROM IvldItemMaster ivldItemMaster WHERE IVLD_ITEM_MASTER_SID IN (";
	private static final String _SQL_COUNT_IVLDITEMMASTER = "SELECT COUNT(ivldItemMaster) FROM IvldItemMaster ivldItemMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldItemMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemNo", "udc6", "createdDate", "newFormulationIndicator",
				"udc5", "newFormulationEndDate", "udc4",
				"clottingFactorStartDate", "secondaryUom", "itemDesc",
				"authorizedGenericEndDate", "manufacturerName", "itemName",
				"reprocessedFlag", "status", "baseCpi", "baselineAmp",
				"authorizedGeneric", "therapeuticClass", "itemFamilyId",
				"pediatricExclusiveStartDate", "createdBy", "primaryUom", "ndc9",
				"itemId", "lastLotExpirationDate", "errorField", "itemCode",
				"strength", "modifiedDate", "brand", "ndc8", "labelerCode",
				"udc3", "source", "udc2", "addChgDelIndicator", "udc1",
				"acquiredAmp", "discontinuationDate", "itemMasterIntfid",
				"intfInsertedDate", "divestitureDate", "modifiedBy",
				"baseCpiPeriod", "clottingFactorEndDate", "dosesPerUnit",
				"manufacturerId", "clottingFactorIndicator", "batchId",
				"acquisitionDate", "dualPricingIndicator",
				"nonFederalExpirationDate", "errorCode", "newFormulation",
				"obraBamp", "brandId", "itemStatus",
				"authorizedGenericStartDate", "newFormulationStartDate",
				"itemCategory", "itemEndDate", "itemType",
				"pediatricExclusiveEndDate", "organizationKey", "firstSaleDate",
				"shelfLifeType", "itemStartDate", "itemTypeIndication",
				"acquiredBamp", "form", "itemClass", "manufacturerNo",
				"pediatricExclusiveIndicator", "packageSizeCode", "displayBrand",
				"dra", "reasonForFailure", "packageSizeIntroDate", "upps",
				"ivldItemMasterSid", "packageSize", "shelfLife",
				"marketTerminationDate", "checkRecord", "baseCpiPrecision",
				"baselineAmpPrecision"
			});
}