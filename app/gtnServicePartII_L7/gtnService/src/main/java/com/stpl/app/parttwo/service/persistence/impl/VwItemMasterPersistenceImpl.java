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

import com.stpl.app.parttwo.exception.NoSuchVwItemMasterException;
import com.stpl.app.parttwo.model.VwItemMaster;
import com.stpl.app.parttwo.model.impl.VwItemMasterImpl;
import com.stpl.app.parttwo.model.impl.VwItemMasterModelImpl;
import com.stpl.app.parttwo.service.persistence.VwItemMasterPersistence;

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
 * The persistence implementation for the vw item master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemMasterPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwItemMasterUtil
 * @generated
 */
@ProviderType
public class VwItemMasterPersistenceImpl extends BasePersistenceImpl<VwItemMaster>
	implements VwItemMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwItemMasterUtil} to access the vw item master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwItemMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterModelImpl.FINDER_CACHE_ENABLED, VwItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterModelImpl.FINDER_CACHE_ENABLED, VwItemMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwItemMasterPersistenceImpl() {
		setModelClass(VwItemMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("itemDesc", "ITEM_DESC");
			dbColumnNames.put("acquiredAmp", "ACQUIRED_AMP");
			dbColumnNames.put("authorizedGenericStartDate",
				"AUTHORIZED_GENERIC_START_DATE");
			dbColumnNames.put("newFormulationStartDate",
				"NEW_FORMULATION_START_DATE");
			dbColumnNames.put("marketTerminationDate", "MARKET_TERMINATION_DATE");
			dbColumnNames.put("obraBamp", "OBRA_BAMP");
			dbColumnNames.put("brand", "BRAND");
			dbColumnNames.put("manufacturerNo", "MANUFACTURER_NO");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("therapeuticClass", "THERAPEUTIC_CLASS");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("acquiredBamp", "ACQUIRED_BAMP");
			dbColumnNames.put("pediatricExclusiveEndDate",
				"PEDIATRIC_EXCLUSIVE_END_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("newFormulation", "NEW_FORMULATION");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("divestitureDate", "DIVESTITURE_DATE");
			dbColumnNames.put("shelfLife", "SHELF_LIFE");
			dbColumnNames.put("primaryUom", "PRIMARY_UOM");
			dbColumnNames.put("newFormulationEndDate",
				"NEW_FORMULATION_END_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("packageSizeCode", "PACKAGE_SIZE_CODE");
			dbColumnNames.put("secondaryUom", "SECONDARY_UOM");
			dbColumnNames.put("udc6", "UDC6");
			dbColumnNames.put("udc5", "UDC5");
			dbColumnNames.put("discontinuationDate", "DISCONTINUATION_DATE");
			dbColumnNames.put("udc4", "UDC4");
			dbColumnNames.put("udc1", "UDC1");
			dbColumnNames.put("udc2", "UDC2");
			dbColumnNames.put("packageSizeIntroDate", "PACKAGE_SIZE_INTRO_DATE");
			dbColumnNames.put("udc3", "UDC3");
			dbColumnNames.put("itemEndDate", "ITEM_END_DATE");
			dbColumnNames.put("manufacturerId", "MANUFACTURER_ID");
			dbColumnNames.put("itemFamilyId", "ITEM_FAMILY_ID");
			dbColumnNames.put("strength", "STRENGTH");
			dbColumnNames.put("itemCategory", "ITEM_CATEGORY");
			dbColumnNames.put("upps", "UPPS");
			dbColumnNames.put("shelfLifeType", "SHELF_LIFE_TYPE");
			dbColumnNames.put("pediatricExclusiveIndicator",
				"PEDIATRIC_EXCLUSIVE_INDICATOR");
			dbColumnNames.put("itemTypeIndication", "ITEM_TYPE_INDICATION");
			dbColumnNames.put("acquisitionDate", "ACQUISITION_DATE");
			dbColumnNames.put("clottingFactorIndicator",
				"CLOTTING_FACTOR_INDICATOR");
			dbColumnNames.put("form", "FORM");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("manufacturerName", "MANUFACTURER_NAME");
			dbColumnNames.put("pediatricExclusiveStartDate",
				"PEDIATRIC_EXCLUSIVE_START_DATE");
			dbColumnNames.put("firstSaleDate", "FIRST_SALE_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("itemType", "ITEM_TYPE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("baselineAmp", "BASELINE_AMP");
			dbColumnNames.put("dosesPerUnit", "DOSES_PER_UNIT");
			dbColumnNames.put("dualPricingIndicator", "DUAL_PRICING_INDICATOR");
			dbColumnNames.put("baseCpi", "BASE_CPI");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemStartDate", "ITEM_START_DATE");
			dbColumnNames.put("authorizedGeneric", "AUTHORIZED_GENERIC");
			dbColumnNames.put("ndc9", "NDC9");
			dbColumnNames.put("authorizedGenericEndDate",
				"AUTHORIZED_GENERIC_END_DATE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("packageSize", "PACKAGE_SIZE");
			dbColumnNames.put("ndc8", "NDC8");
			dbColumnNames.put("itemClass", "ITEM_CLASS");
			dbColumnNames.put("labelerCode", "LABELER_CODE");
			dbColumnNames.put("displayBrand", "DISPLAY_BRAND");
			dbColumnNames.put("clottingFactorEndDate",
				"CLOTTING_FACTOR_END_DATE");
			dbColumnNames.put("dra", "DRA");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("baseCpiPeriod", "BASE_CPI_PERIOD");
			dbColumnNames.put("newFormulationIndicator",
				"NEW_FORMULATION_INDICATOR");
			dbColumnNames.put("lastLotExpirationDate",
				"LAST_LOT_EXPIRATION_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemCode", "ITEM_CODE");
			dbColumnNames.put("clottingFactorStartDate",
				"CLOTTING_FACTOR_START_DATE");
			dbColumnNames.put("nonFederalExpirationDate",
				"NON_FEDERAL_EXPIRATION_DATE");
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
	 * Caches the vw item master in the entity cache if it is enabled.
	 *
	 * @param vwItemMaster the vw item master
	 */
	@Override
	public void cacheResult(VwItemMaster vwItemMaster) {
		entityCache.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterImpl.class, vwItemMaster.getPrimaryKey(), vwItemMaster);

		vwItemMaster.resetOriginalValues();
	}

	/**
	 * Caches the vw item masters in the entity cache if it is enabled.
	 *
	 * @param vwItemMasters the vw item masters
	 */
	@Override
	public void cacheResult(List<VwItemMaster> vwItemMasters) {
		for (VwItemMaster vwItemMaster : vwItemMasters) {
			if (entityCache.getResult(
						VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwItemMasterImpl.class, vwItemMaster.getPrimaryKey()) == null) {
				cacheResult(vwItemMaster);
			}
			else {
				vwItemMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw item masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwItemMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw item master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwItemMaster vwItemMaster) {
		entityCache.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterImpl.class, vwItemMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwItemMaster> vwItemMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwItemMaster vwItemMaster : vwItemMasters) {
			entityCache.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwItemMasterImpl.class, vwItemMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw item master with the primary key. Does not add the vw item master to the database.
	 *
	 * @param itemMasterSid the primary key for the new vw item master
	 * @return the new vw item master
	 */
	@Override
	public VwItemMaster create(int itemMasterSid) {
		VwItemMaster vwItemMaster = new VwItemMasterImpl();

		vwItemMaster.setNew(true);
		vwItemMaster.setPrimaryKey(itemMasterSid);

		return vwItemMaster;
	}

	/**
	 * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemMasterSid the primary key of the vw item master
	 * @return the vw item master that was removed
	 * @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster remove(int itemMasterSid)
		throws NoSuchVwItemMasterException {
		return remove((Serializable)itemMasterSid);
	}

	/**
	 * Removes the vw item master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw item master
	 * @return the vw item master that was removed
	 * @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster remove(Serializable primaryKey)
		throws NoSuchVwItemMasterException {
		Session session = null;

		try {
			session = openSession();

			VwItemMaster vwItemMaster = (VwItemMaster)session.get(VwItemMasterImpl.class,
					primaryKey);

			if (vwItemMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwItemMaster);
		}
		catch (NoSuchVwItemMasterException nsee) {
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
	protected VwItemMaster removeImpl(VwItemMaster vwItemMaster) {
		vwItemMaster = toUnwrappedModel(vwItemMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwItemMaster)) {
				vwItemMaster = (VwItemMaster)session.get(VwItemMasterImpl.class,
						vwItemMaster.getPrimaryKeyObj());
			}

			if (vwItemMaster != null) {
				session.delete(vwItemMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwItemMaster != null) {
			clearCache(vwItemMaster);
		}

		return vwItemMaster;
	}

	@Override
	public VwItemMaster updateImpl(VwItemMaster vwItemMaster) {
		vwItemMaster = toUnwrappedModel(vwItemMaster);

		boolean isNew = vwItemMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwItemMaster.isNew()) {
				session.save(vwItemMaster);

				vwItemMaster.setNew(false);
			}
			else {
				vwItemMaster = (VwItemMaster)session.merge(vwItemMaster);
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

		entityCache.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwItemMasterImpl.class, vwItemMaster.getPrimaryKey(), vwItemMaster,
			false);

		vwItemMaster.resetOriginalValues();

		return vwItemMaster;
	}

	protected VwItemMaster toUnwrappedModel(VwItemMaster vwItemMaster) {
		if (vwItemMaster instanceof VwItemMasterImpl) {
			return vwItemMaster;
		}

		VwItemMasterImpl vwItemMasterImpl = new VwItemMasterImpl();

		vwItemMasterImpl.setNew(vwItemMaster.isNew());
		vwItemMasterImpl.setPrimaryKey(vwItemMaster.getPrimaryKey());

		vwItemMasterImpl.setItemStatus(vwItemMaster.getItemStatus());
		vwItemMasterImpl.setItemDesc(vwItemMaster.getItemDesc());
		vwItemMasterImpl.setAcquiredAmp(vwItemMaster.getAcquiredAmp());
		vwItemMasterImpl.setAuthorizedGenericStartDate(vwItemMaster.getAuthorizedGenericStartDate());
		vwItemMasterImpl.setNewFormulationStartDate(vwItemMaster.getNewFormulationStartDate());
		vwItemMasterImpl.setMarketTerminationDate(vwItemMaster.getMarketTerminationDate());
		vwItemMasterImpl.setObraBamp(vwItemMaster.getObraBamp());
		vwItemMasterImpl.setBrand(vwItemMaster.getBrand());
		vwItemMasterImpl.setManufacturerNo(vwItemMaster.getManufacturerNo());
		vwItemMasterImpl.setModifiedDate(vwItemMaster.getModifiedDate());
		vwItemMasterImpl.setTherapeuticClass(vwItemMaster.getTherapeuticClass());
		vwItemMasterImpl.setOrganizationKey(vwItemMaster.getOrganizationKey());
		vwItemMasterImpl.setAcquiredBamp(vwItemMaster.getAcquiredBamp());
		vwItemMasterImpl.setPediatricExclusiveEndDate(vwItemMaster.getPediatricExclusiveEndDate());
		vwItemMasterImpl.setSource(vwItemMaster.getSource());
		vwItemMasterImpl.setNewFormulation(vwItemMaster.getNewFormulation());
		vwItemMasterImpl.setAddChgDelIndicator(vwItemMaster.getAddChgDelIndicator());
		vwItemMasterImpl.setDivestitureDate(vwItemMaster.getDivestitureDate());
		vwItemMasterImpl.setShelfLife(vwItemMaster.getShelfLife());
		vwItemMasterImpl.setPrimaryUom(vwItemMaster.getPrimaryUom());
		vwItemMasterImpl.setNewFormulationEndDate(vwItemMaster.getNewFormulationEndDate());
		vwItemMasterImpl.setModifiedBy(vwItemMaster.getModifiedBy());
		vwItemMasterImpl.setPackageSizeCode(vwItemMaster.getPackageSizeCode());
		vwItemMasterImpl.setSecondaryUom(vwItemMaster.getSecondaryUom());
		vwItemMasterImpl.setUdc6(vwItemMaster.getUdc6());
		vwItemMasterImpl.setUdc5(vwItemMaster.getUdc5());
		vwItemMasterImpl.setDiscontinuationDate(vwItemMaster.getDiscontinuationDate());
		vwItemMasterImpl.setUdc4(vwItemMaster.getUdc4());
		vwItemMasterImpl.setUdc1(vwItemMaster.getUdc1());
		vwItemMasterImpl.setUdc2(vwItemMaster.getUdc2());
		vwItemMasterImpl.setPackageSizeIntroDate(vwItemMaster.getPackageSizeIntroDate());
		vwItemMasterImpl.setUdc3(vwItemMaster.getUdc3());
		vwItemMasterImpl.setItemEndDate(vwItemMaster.getItemEndDate());
		vwItemMasterImpl.setManufacturerId(vwItemMaster.getManufacturerId());
		vwItemMasterImpl.setItemFamilyId(vwItemMaster.getItemFamilyId());
		vwItemMasterImpl.setStrength(vwItemMaster.getStrength());
		vwItemMasterImpl.setItemCategory(vwItemMaster.getItemCategory());
		vwItemMasterImpl.setUpps(vwItemMaster.getUpps());
		vwItemMasterImpl.setShelfLifeType(vwItemMaster.getShelfLifeType());
		vwItemMasterImpl.setPediatricExclusiveIndicator(vwItemMaster.getPediatricExclusiveIndicator());
		vwItemMasterImpl.setItemTypeIndication(vwItemMaster.getItemTypeIndication());
		vwItemMasterImpl.setAcquisitionDate(vwItemMaster.getAcquisitionDate());
		vwItemMasterImpl.setClottingFactorIndicator(vwItemMaster.getClottingFactorIndicator());
		vwItemMasterImpl.setForm(vwItemMaster.getForm());
		vwItemMasterImpl.setItemName(vwItemMaster.getItemName());
		vwItemMasterImpl.setManufacturerName(vwItemMaster.getManufacturerName());
		vwItemMasterImpl.setPediatricExclusiveStartDate(vwItemMaster.getPediatricExclusiveStartDate());
		vwItemMasterImpl.setFirstSaleDate(vwItemMaster.getFirstSaleDate());
		vwItemMasterImpl.setItemMasterSid(vwItemMaster.getItemMasterSid());
		vwItemMasterImpl.setItemType(vwItemMaster.getItemType());
		vwItemMasterImpl.setItemId(vwItemMaster.getItemId());
		vwItemMasterImpl.setBaselineAmp(vwItemMaster.getBaselineAmp());
		vwItemMasterImpl.setDosesPerUnit(vwItemMaster.getDosesPerUnit());
		vwItemMasterImpl.setDualPricingIndicator(vwItemMaster.getDualPricingIndicator());
		vwItemMasterImpl.setBaseCpi(vwItemMaster.getBaseCpi());
		vwItemMasterImpl.setCreatedDate(vwItemMaster.getCreatedDate());
		vwItemMasterImpl.setCreatedBy(vwItemMaster.getCreatedBy());
		vwItemMasterImpl.setItemStartDate(vwItemMaster.getItemStartDate());
		vwItemMasterImpl.setAuthorizedGeneric(vwItemMaster.getAuthorizedGeneric());
		vwItemMasterImpl.setNdc9(vwItemMaster.getNdc9());
		vwItemMasterImpl.setAuthorizedGenericEndDate(vwItemMaster.getAuthorizedGenericEndDate());
		vwItemMasterImpl.setItemNo(vwItemMaster.getItemNo());
		vwItemMasterImpl.setPackageSize(vwItemMaster.getPackageSize());
		vwItemMasterImpl.setNdc8(vwItemMaster.getNdc8());
		vwItemMasterImpl.setItemClass(vwItemMaster.getItemClass());
		vwItemMasterImpl.setLabelerCode(vwItemMaster.getLabelerCode());
		vwItemMasterImpl.setDisplayBrand(vwItemMaster.getDisplayBrand());
		vwItemMasterImpl.setClottingFactorEndDate(vwItemMaster.getClottingFactorEndDate());
		vwItemMasterImpl.setDra(vwItemMaster.getDra());
		vwItemMasterImpl.setBrandId(vwItemMaster.getBrandId());
		vwItemMasterImpl.setBaseCpiPeriod(vwItemMaster.getBaseCpiPeriod());
		vwItemMasterImpl.setNewFormulationIndicator(vwItemMaster.getNewFormulationIndicator());
		vwItemMasterImpl.setLastLotExpirationDate(vwItemMaster.getLastLotExpirationDate());
		vwItemMasterImpl.setBatchId(vwItemMaster.getBatchId());
		vwItemMasterImpl.setItemCode(vwItemMaster.getItemCode());
		vwItemMasterImpl.setClottingFactorStartDate(vwItemMaster.getClottingFactorStartDate());
		vwItemMasterImpl.setNonFederalExpirationDate(vwItemMaster.getNonFederalExpirationDate());
		vwItemMasterImpl.setBaseCpiPrecision(vwItemMaster.getBaseCpiPrecision());
		vwItemMasterImpl.setBaselineAmpPrecision(vwItemMaster.getBaselineAmpPrecision());

		return vwItemMasterImpl;
	}

	/**
	 * Returns the vw item master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw item master
	 * @return the vw item master
	 * @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwItemMasterException {
		VwItemMaster vwItemMaster = fetchByPrimaryKey(primaryKey);

		if (vwItemMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwItemMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwItemMaster;
	}

	/**
	 * Returns the vw item master with the primary key or throws a {@link NoSuchVwItemMasterException} if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the vw item master
	 * @return the vw item master
	 * @throws NoSuchVwItemMasterException if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster findByPrimaryKey(int itemMasterSid)
		throws NoSuchVwItemMasterException {
		return findByPrimaryKey((Serializable)itemMasterSid);
	}

	/**
	 * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw item master
	 * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwItemMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwItemMaster vwItemMaster = (VwItemMaster)serializable;

		if (vwItemMaster == null) {
			Session session = null;

			try {
				session = openSession();

				vwItemMaster = (VwItemMaster)session.get(VwItemMasterImpl.class,
						primaryKey);

				if (vwItemMaster != null) {
					cacheResult(vwItemMaster);
				}
				else {
					entityCache.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwItemMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwItemMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwItemMaster;
	}

	/**
	 * Returns the vw item master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemMasterSid the primary key of the vw item master
	 * @return the vw item master, or <code>null</code> if a vw item master with the primary key could not be found
	 */
	@Override
	public VwItemMaster fetchByPrimaryKey(int itemMasterSid) {
		return fetchByPrimaryKey((Serializable)itemMasterSid);
	}

	@Override
	public Map<Serializable, VwItemMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwItemMaster> map = new HashMap<Serializable, VwItemMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwItemMaster vwItemMaster = fetchByPrimaryKey(primaryKey);

			if (vwItemMaster != null) {
				map.put(primaryKey, vwItemMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwItemMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwItemMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWITEMMASTER_WHERE_PKS_IN);

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

			for (VwItemMaster vwItemMaster : (List<VwItemMaster>)q.list()) {
				map.put(vwItemMaster.getPrimaryKeyObj(), vwItemMaster);

				cacheResult(vwItemMaster);

				uncachedPrimaryKeys.remove(vwItemMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwItemMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwItemMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw item masters.
	 *
	 * @return the vw item masters
	 */
	@Override
	public List<VwItemMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item masters
	 * @param end the upper bound of the range of vw item masters (not inclusive)
	 * @return the range of vw item masters
	 */
	@Override
	public List<VwItemMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item masters
	 * @param end the upper bound of the range of vw item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw item masters
	 */
	@Override
	public List<VwItemMaster> findAll(int start, int end,
		OrderByComparator<VwItemMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw item masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item masters
	 * @param end the upper bound of the range of vw item masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw item masters
	 */
	@Override
	public List<VwItemMaster> findAll(int start, int end,
		OrderByComparator<VwItemMaster> orderByComparator,
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

		List<VwItemMaster> list = null;

		if (retrieveFromCache) {
			list = (List<VwItemMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWITEMMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWITEMMASTER;

				if (pagination) {
					sql = sql.concat(VwItemMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwItemMaster>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwItemMaster>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the vw item masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwItemMaster vwItemMaster : findAll()) {
			remove(vwItemMaster);
		}
	}

	/**
	 * Returns the number of vw item masters.
	 *
	 * @return the number of vw item masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWITEMMASTER);

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
		return VwItemMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw item master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwItemMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWITEMMASTER = "SELECT vwItemMaster FROM VwItemMaster vwItemMaster";
	private static final String _SQL_SELECT_VWITEMMASTER_WHERE_PKS_IN = "SELECT vwItemMaster FROM VwItemMaster vwItemMaster WHERE ITEM_MASTER_SID IN (";
	private static final String _SQL_COUNT_VWITEMMASTER = "SELECT COUNT(vwItemMaster) FROM VwItemMaster vwItemMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwItemMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwItemMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwItemMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemStatus", "itemDesc", "acquiredAmp",
				"authorizedGenericStartDate", "newFormulationStartDate",
				"marketTerminationDate", "obraBamp", "brand", "manufacturerNo",
				"modifiedDate", "therapeuticClass", "organizationKey",
				"acquiredBamp", "pediatricExclusiveEndDate", "source",
				"newFormulation", "addChgDelIndicator", "divestitureDate",
				"shelfLife", "primaryUom", "newFormulationEndDate", "modifiedBy",
				"packageSizeCode", "secondaryUom", "udc6", "udc5",
				"discontinuationDate", "udc4", "udc1", "udc2",
				"packageSizeIntroDate", "udc3", "itemEndDate", "manufacturerId",
				"itemFamilyId", "strength", "itemCategory", "upps",
				"shelfLifeType", "pediatricExclusiveIndicator",
				"itemTypeIndication", "acquisitionDate",
				"clottingFactorIndicator", "form", "itemName",
				"manufacturerName", "pediatricExclusiveStartDate",
				"firstSaleDate", "itemMasterSid", "itemType", "itemId",
				"baselineAmp", "dosesPerUnit", "dualPricingIndicator", "baseCpi",
				"createdDate", "createdBy", "itemStartDate", "authorizedGeneric",
				"ndc9", "authorizedGenericEndDate", "itemNo", "packageSize",
				"ndc8", "itemClass", "labelerCode", "displayBrand",
				"clottingFactorEndDate", "dra", "brandId", "baseCpiPeriod",
				"newFormulationIndicator", "lastLotExpirationDate", "batchId",
				"itemCode", "clottingFactorStartDate",
				"nonFederalExpirationDate", "baseCpiPrecision",
				"baselineAmpPrecision"
			});
}