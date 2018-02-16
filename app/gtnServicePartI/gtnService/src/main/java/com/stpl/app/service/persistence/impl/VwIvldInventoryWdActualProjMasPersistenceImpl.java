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

import com.stpl.app.exception.NoSuchVwIvldInventoryWdActualProjMasException;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasImpl;
import com.stpl.app.model.impl.VwIvldInventoryWdActualProjMasModelImpl;
import com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasPersistence;

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
 * The persistence implementation for the vw ivld inventory wd actual proj mas service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldInventoryWdActualProjMasPersistence
 * @see com.stpl.app.service.persistence.VwIvldInventoryWdActualProjMasUtil
 * @generated
 */
@ProviderType
public class VwIvldInventoryWdActualProjMasPersistenceImpl
	extends BasePersistenceImpl<VwIvldInventoryWdActualProjMas>
	implements VwIvldInventoryWdActualProjMasPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwIvldInventoryWdActualProjMasUtil} to access the vw ivld inventory wd actual proj mas persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwIvldInventoryWdActualProjMasImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasModelImpl.FINDER_CACHE_ENABLED,
			Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

	public VwIvldInventoryWdActualProjMasPersistenceImpl() {
		setModelClass(VwIvldInventoryWdActualProjMas.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("ivldInventoryWdActualProjMasSid",
				"IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID");
			dbColumnNames.put("quantityOnOrder", "QUANTITY_ON_ORDER");
			dbColumnNames.put("week", "WEEK");
			dbColumnNames.put("price", "PRICE");
			dbColumnNames.put("amountOnHand", "AMOUNT_ON_HAND");
			dbColumnNames.put("isMaster", "IS_MASTER");
			dbColumnNames.put("companyName", "COMPANY_NAME");
			dbColumnNames.put("year", "YEAR");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("organizationKey", "ORGANIZATION_KEY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("day", "DAY");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("unitsOnHand", "UNITS_ON_HAND");
			dbColumnNames.put("amountReceived", "AMOUNT_RECEIVED");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("month", "MONTH");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("amountWithdrawn", "AMOUNT_WITHDRAWN");
			dbColumnNames.put("quantityReceived", "QUANTITY_RECEIVED");
			dbColumnNames.put("unitsWithdrawn", "UNITS_WITHDRAWN");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("country", "COUNTRY");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("isForecast", "IS_FORECAST");
			dbColumnNames.put("inventoryWdActualProjMasIntfId",
				"INVENTORY_WD_ACTUAL_PROJ_MAS_INTF_ID");
			dbColumnNames.put("forecastVer", "FORECAST_VER");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("amountOnOrder", "AMOUNT_ON_ORDER");
			dbColumnNames.put("forecastName", "FORECAST_NAME");
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
	 * Caches the vw ivld inventory wd actual proj mas in the entity cache if it is enabled.
	 *
	 * @param vwIvldInventoryWdActualProjMas the vw ivld inventory wd actual proj mas
	 */
	@Override
	public void cacheResult(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		entityCache.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasImpl.class,
			vwIvldInventoryWdActualProjMas.getPrimaryKey(),
			vwIvldInventoryWdActualProjMas);

		vwIvldInventoryWdActualProjMas.resetOriginalValues();
	}

	/**
	 * Caches the vw ivld inventory wd actual proj mases in the entity cache if it is enabled.
	 *
	 * @param vwIvldInventoryWdActualProjMases the vw ivld inventory wd actual proj mases
	 */
	@Override
	public void cacheResult(
		List<VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases) {
		for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : vwIvldInventoryWdActualProjMases) {
			if (entityCache.getResult(
						VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldInventoryWdActualProjMasImpl.class,
						vwIvldInventoryWdActualProjMas.getPrimaryKey()) == null) {
				cacheResult(vwIvldInventoryWdActualProjMas);
			}
			else {
				vwIvldInventoryWdActualProjMas.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw ivld inventory wd actual proj mases.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwIvldInventoryWdActualProjMasImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw ivld inventory wd actual proj mas.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		entityCache.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasImpl.class,
			vwIvldInventoryWdActualProjMas.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<VwIvldInventoryWdActualProjMas> vwIvldInventoryWdActualProjMases) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : vwIvldInventoryWdActualProjMases) {
			entityCache.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldInventoryWdActualProjMasImpl.class,
				vwIvldInventoryWdActualProjMas.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw ivld inventory wd actual proj mas with the primary key. Does not add the vw ivld inventory wd actual proj mas to the database.
	 *
	 * @param ivldInventoryWdActualProjMasSid the primary key for the new vw ivld inventory wd actual proj mas
	 * @return the new vw ivld inventory wd actual proj mas
	 */
	@Override
	public VwIvldInventoryWdActualProjMas create(
		int ivldInventoryWdActualProjMasSid) {
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = new VwIvldInventoryWdActualProjMasImpl();

		vwIvldInventoryWdActualProjMas.setNew(true);
		vwIvldInventoryWdActualProjMas.setPrimaryKey(ivldInventoryWdActualProjMasSid);

		return vwIvldInventoryWdActualProjMas;
	}

	/**
	 * Removes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas that was removed
	 * @throws NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas remove(
		int ivldInventoryWdActualProjMasSid)
		throws NoSuchVwIvldInventoryWdActualProjMasException {
		return remove((Serializable)ivldInventoryWdActualProjMasSid);
	}

	/**
	 * Removes the vw ivld inventory wd actual proj mas with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas that was removed
	 * @throws NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas remove(Serializable primaryKey)
		throws NoSuchVwIvldInventoryWdActualProjMasException {
		Session session = null;

		try {
			session = openSession();

			VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas)session.get(VwIvldInventoryWdActualProjMasImpl.class,
					primaryKey);

			if (vwIvldInventoryWdActualProjMas == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwIvldInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwIvldInventoryWdActualProjMas);
		}
		catch (NoSuchVwIvldInventoryWdActualProjMasException nsee) {
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
	protected VwIvldInventoryWdActualProjMas removeImpl(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		vwIvldInventoryWdActualProjMas = toUnwrappedModel(vwIvldInventoryWdActualProjMas);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwIvldInventoryWdActualProjMas)) {
				vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas)session.get(VwIvldInventoryWdActualProjMasImpl.class,
						vwIvldInventoryWdActualProjMas.getPrimaryKeyObj());
			}

			if (vwIvldInventoryWdActualProjMas != null) {
				session.delete(vwIvldInventoryWdActualProjMas);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwIvldInventoryWdActualProjMas != null) {
			clearCache(vwIvldInventoryWdActualProjMas);
		}

		return vwIvldInventoryWdActualProjMas;
	}

	@Override
	public VwIvldInventoryWdActualProjMas updateImpl(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		vwIvldInventoryWdActualProjMas = toUnwrappedModel(vwIvldInventoryWdActualProjMas);

		boolean isNew = vwIvldInventoryWdActualProjMas.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwIvldInventoryWdActualProjMas.isNew()) {
				session.save(vwIvldInventoryWdActualProjMas);

				vwIvldInventoryWdActualProjMas.setNew(false);
			}
			else {
				vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas)session.merge(vwIvldInventoryWdActualProjMas);
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

		entityCache.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
			VwIvldInventoryWdActualProjMasImpl.class,
			vwIvldInventoryWdActualProjMas.getPrimaryKey(),
			vwIvldInventoryWdActualProjMas, false);

		vwIvldInventoryWdActualProjMas.resetOriginalValues();

		return vwIvldInventoryWdActualProjMas;
	}

	protected VwIvldInventoryWdActualProjMas toUnwrappedModel(
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
		if (vwIvldInventoryWdActualProjMas instanceof VwIvldInventoryWdActualProjMasImpl) {
			return vwIvldInventoryWdActualProjMas;
		}

		VwIvldInventoryWdActualProjMasImpl vwIvldInventoryWdActualProjMasImpl = new VwIvldInventoryWdActualProjMasImpl();

		vwIvldInventoryWdActualProjMasImpl.setNew(vwIvldInventoryWdActualProjMas.isNew());
		vwIvldInventoryWdActualProjMasImpl.setPrimaryKey(vwIvldInventoryWdActualProjMas.getPrimaryKey());

		vwIvldInventoryWdActualProjMasImpl.setIvldInventoryWdActualProjMasSid(vwIvldInventoryWdActualProjMas.getIvldInventoryWdActualProjMasSid());
		vwIvldInventoryWdActualProjMasImpl.setQuantityOnOrder(vwIvldInventoryWdActualProjMas.getQuantityOnOrder());
		vwIvldInventoryWdActualProjMasImpl.setWeek(vwIvldInventoryWdActualProjMas.getWeek());
		vwIvldInventoryWdActualProjMasImpl.setPrice(vwIvldInventoryWdActualProjMas.getPrice());
		vwIvldInventoryWdActualProjMasImpl.setAmountOnHand(vwIvldInventoryWdActualProjMas.getAmountOnHand());
		vwIvldInventoryWdActualProjMasImpl.setIsMaster(vwIvldInventoryWdActualProjMas.getIsMaster());
		vwIvldInventoryWdActualProjMasImpl.setCompanyName(vwIvldInventoryWdActualProjMas.getCompanyName());
		vwIvldInventoryWdActualProjMasImpl.setYear(vwIvldInventoryWdActualProjMas.getYear());
		vwIvldInventoryWdActualProjMasImpl.setItemId(vwIvldInventoryWdActualProjMas.getItemId());
		vwIvldInventoryWdActualProjMasImpl.setModifiedDate(vwIvldInventoryWdActualProjMas.getModifiedDate());
		vwIvldInventoryWdActualProjMasImpl.setOrganizationKey(vwIvldInventoryWdActualProjMas.getOrganizationKey());
		vwIvldInventoryWdActualProjMasImpl.setSource(vwIvldInventoryWdActualProjMas.getSource());
		vwIvldInventoryWdActualProjMasImpl.setCreatedBy(vwIvldInventoryWdActualProjMas.getCreatedBy());
		vwIvldInventoryWdActualProjMasImpl.setCreatedDate(vwIvldInventoryWdActualProjMas.getCreatedDate());
		vwIvldInventoryWdActualProjMasImpl.setDay(vwIvldInventoryWdActualProjMas.getDay());
		vwIvldInventoryWdActualProjMasImpl.setAddChgDelIndicator(vwIvldInventoryWdActualProjMas.getAddChgDelIndicator());
		vwIvldInventoryWdActualProjMasImpl.setUnitsOnHand(vwIvldInventoryWdActualProjMas.getUnitsOnHand());
		vwIvldInventoryWdActualProjMasImpl.setAmountReceived(vwIvldInventoryWdActualProjMas.getAmountReceived());
		vwIvldInventoryWdActualProjMasImpl.setErrorCode(vwIvldInventoryWdActualProjMas.getErrorCode());
		vwIvldInventoryWdActualProjMasImpl.setIntfInsertedDate(vwIvldInventoryWdActualProjMas.getIntfInsertedDate());
		vwIvldInventoryWdActualProjMasImpl.setModifiedBy(vwIvldInventoryWdActualProjMas.getModifiedBy());
		vwIvldInventoryWdActualProjMasImpl.setMonth(vwIvldInventoryWdActualProjMas.getMonth());
		vwIvldInventoryWdActualProjMasImpl.setReprocessedFlag(vwIvldInventoryWdActualProjMas.getReprocessedFlag());
		vwIvldInventoryWdActualProjMasImpl.setAmountWithdrawn(vwIvldInventoryWdActualProjMas.getAmountWithdrawn());
		vwIvldInventoryWdActualProjMasImpl.setQuantityReceived(vwIvldInventoryWdActualProjMas.getQuantityReceived());
		vwIvldInventoryWdActualProjMasImpl.setUnitsWithdrawn(vwIvldInventoryWdActualProjMas.getUnitsWithdrawn());
		vwIvldInventoryWdActualProjMasImpl.setReasonForFailure(vwIvldInventoryWdActualProjMas.getReasonForFailure());
		vwIvldInventoryWdActualProjMasImpl.setCountry(vwIvldInventoryWdActualProjMas.getCountry());
		vwIvldInventoryWdActualProjMasImpl.setCompanyStringId(vwIvldInventoryWdActualProjMas.getCompanyStringId());
		vwIvldInventoryWdActualProjMasImpl.setIsForecast(vwIvldInventoryWdActualProjMas.getIsForecast());
		vwIvldInventoryWdActualProjMasImpl.setInventoryWdActualProjMasIntfId(vwIvldInventoryWdActualProjMas.getInventoryWdActualProjMasIntfId());
		vwIvldInventoryWdActualProjMasImpl.setForecastVer(vwIvldInventoryWdActualProjMas.getForecastVer());
		vwIvldInventoryWdActualProjMasImpl.setBatchId(vwIvldInventoryWdActualProjMas.getBatchId());
		vwIvldInventoryWdActualProjMasImpl.setItemName(vwIvldInventoryWdActualProjMas.getItemName());
		vwIvldInventoryWdActualProjMasImpl.setErrorField(vwIvldInventoryWdActualProjMas.getErrorField());
		vwIvldInventoryWdActualProjMasImpl.setAmountOnOrder(vwIvldInventoryWdActualProjMas.getAmountOnOrder());
		vwIvldInventoryWdActualProjMasImpl.setForecastName(vwIvldInventoryWdActualProjMas.getForecastName());
		vwIvldInventoryWdActualProjMasImpl.setCheckRecord(vwIvldInventoryWdActualProjMas.isCheckRecord());

		return vwIvldInventoryWdActualProjMasImpl;
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas
	 * @throws NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas findByPrimaryKey(
		Serializable primaryKey)
		throws NoSuchVwIvldInventoryWdActualProjMasException {
		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

		if (vwIvldInventoryWdActualProjMas == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwIvldInventoryWdActualProjMasException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwIvldInventoryWdActualProjMas;
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas with the primary key or throws a {@link NoSuchVwIvldInventoryWdActualProjMasException} if it could not be found.
	 *
	 * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas
	 * @throws NoSuchVwIvldInventoryWdActualProjMasException if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas findByPrimaryKey(
		int ivldInventoryWdActualProjMasSid)
		throws NoSuchVwIvldInventoryWdActualProjMasException {
		return findByPrimaryKey((Serializable)ivldInventoryWdActualProjMasSid);
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas, or <code>null</code> if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas fetchByPrimaryKey(
		Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
				VwIvldInventoryWdActualProjMasImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas)serializable;

		if (vwIvldInventoryWdActualProjMas == null) {
			Session session = null;

			try {
				session = openSession();

				vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMas)session.get(VwIvldInventoryWdActualProjMasImpl.class,
						primaryKey);

				if (vwIvldInventoryWdActualProjMas != null) {
					cacheResult(vwIvldInventoryWdActualProjMas);
				}
				else {
					entityCache.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
						VwIvldInventoryWdActualProjMasImpl.class, primaryKey,
						nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldInventoryWdActualProjMasImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwIvldInventoryWdActualProjMas;
	}

	/**
	 * Returns the vw ivld inventory wd actual proj mas with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldInventoryWdActualProjMasSid the primary key of the vw ivld inventory wd actual proj mas
	 * @return the vw ivld inventory wd actual proj mas, or <code>null</code> if a vw ivld inventory wd actual proj mas with the primary key could not be found
	 */
	@Override
	public VwIvldInventoryWdActualProjMas fetchByPrimaryKey(
		int ivldInventoryWdActualProjMasSid) {
		return fetchByPrimaryKey((Serializable)ivldInventoryWdActualProjMasSid);
	}

	@Override
	public Map<Serializable, VwIvldInventoryWdActualProjMas> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwIvldInventoryWdActualProjMas> map = new HashMap<Serializable, VwIvldInventoryWdActualProjMas>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas = fetchByPrimaryKey(primaryKey);

			if (vwIvldInventoryWdActualProjMas != null) {
				map.put(primaryKey, vwIvldInventoryWdActualProjMas);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldInventoryWdActualProjMasImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey,
						(VwIvldInventoryWdActualProjMas)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS_WHERE_PKS_IN);

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

			for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : (List<VwIvldInventoryWdActualProjMas>)q.list()) {
				map.put(vwIvldInventoryWdActualProjMas.getPrimaryKeyObj(),
					vwIvldInventoryWdActualProjMas);

				cacheResult(vwIvldInventoryWdActualProjMas);

				uncachedPrimaryKeys.remove(vwIvldInventoryWdActualProjMas.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwIvldInventoryWdActualProjMasModelImpl.ENTITY_CACHE_ENABLED,
					VwIvldInventoryWdActualProjMasImpl.class, primaryKey,
					nullModel);
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
	 * Returns all the vw ivld inventory wd actual proj mases.
	 *
	 * @return the vw ivld inventory wd actual proj mases
	 */
	@Override
	public List<VwIvldInventoryWdActualProjMas> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw ivld inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
	 * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
	 * @return the range of vw ivld inventory wd actual proj mases
	 */
	@Override
	public List<VwIvldInventoryWdActualProjMas> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw ivld inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
	 * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw ivld inventory wd actual proj mases
	 */
	@Override
	public List<VwIvldInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwIvldInventoryWdActualProjMas> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw ivld inventory wd actual proj mases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwIvldInventoryWdActualProjMasModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw ivld inventory wd actual proj mases
	 * @param end the upper bound of the range of vw ivld inventory wd actual proj mases (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw ivld inventory wd actual proj mases
	 */
	@Override
	public List<VwIvldInventoryWdActualProjMas> findAll(int start, int end,
		OrderByComparator<VwIvldInventoryWdActualProjMas> orderByComparator,
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

		List<VwIvldInventoryWdActualProjMas> list = null;

		if (retrieveFromCache) {
			list = (List<VwIvldInventoryWdActualProjMas>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS;

				if (pagination) {
					sql = sql.concat(VwIvldInventoryWdActualProjMasModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwIvldInventoryWdActualProjMas>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwIvldInventoryWdActualProjMas>)QueryUtil.list(q,
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
	 * Removes all the vw ivld inventory wd actual proj mases from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas : findAll()) {
			remove(vwIvldInventoryWdActualProjMas);
		}
	}

	/**
	 * Returns the number of vw ivld inventory wd actual proj mases.
	 *
	 * @return the number of vw ivld inventory wd actual proj mases
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWIVLDINVENTORYWDACTUALPROJMAS);

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
		return VwIvldInventoryWdActualProjMasModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw ivld inventory wd actual proj mas persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwIvldInventoryWdActualProjMasImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS = "SELECT vwIvldInventoryWdActualProjMas FROM VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas";
	private static final String _SQL_SELECT_VWIVLDINVENTORYWDACTUALPROJMAS_WHERE_PKS_IN =
		"SELECT vwIvldInventoryWdActualProjMas FROM VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas WHERE IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID IN (";
	private static final String _SQL_COUNT_VWIVLDINVENTORYWDACTUALPROJMAS = "SELECT COUNT(vwIvldInventoryWdActualProjMas) FROM VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwIvldInventoryWdActualProjMas.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwIvldInventoryWdActualProjMas exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwIvldInventoryWdActualProjMasPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"ivldInventoryWdActualProjMasSid", "quantityOnOrder", "week",
				"price", "amountOnHand", "isMaster", "companyName", "year",
				"itemId", "modifiedDate", "organizationKey", "source",
				"createdBy", "createdDate", "day", "addChgDelIndicator",
				"unitsOnHand", "amountReceived", "errorCode", "intfInsertedDate",
				"modifiedBy", "month", "reprocessedFlag", "amountWithdrawn",
				"quantityReceived", "unitsWithdrawn", "reasonForFailure",
				"country", "companyStringId", "isForecast",
				"inventoryWdActualProjMasIntfId", "forecastVer", "batchId",
				"itemName", "errorField", "amountOnOrder", "forecastName",
				"checkRecord"
			});
}